package br.com.zupacademy.haline.proposta.carteira;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.haline.proposta.bloqueio.BloqueioController;
import br.com.zupacademy.haline.proposta.cartao.Cartao;
import br.com.zupacademy.haline.proposta.cartao.CartaoClient;
import br.com.zupacademy.haline.proposta.cartao.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

	@Autowired
	private CarteiraRepository carteiraRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoClient client;

	@PostMapping("/{numeroCartao}")
	@Transactional
	public ResponseEntity<?> associaCarteira(@PathVariable String numeroCartao, 
											 @RequestBody @Valid CarteiraRequest request,
											 UriComponentsBuilder builder) {

		Optional<Cartao> cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

		System.out.println(cartao.get().getNumeroCartao());

		if (cartao.isEmpty()) {
			logger.warn("Cartão não encontrado.");
			return ResponseEntity.notFound().build();
		}

		if (possuiCarteira(request.getCarteira(), cartao.get())) {
			logger.info("Carteira já esta associada ao cartão {}", cartao.get().getId());
			return ResponseEntity.unprocessableEntity().build();
		} else {
			System.out.println("vamos novamente");
			client.associaCarteira(cartao.get().getNumeroCartao(), request);
			Carteira novaCarteira = request.toModel(cartao.get());

			carteiraRepository.save(novaCarteira);
			logger.info("Nova carteria associada ao cartao {}", cartao.get().getId());

			URI uri = builder.path("/carteiras/{id}").buildAndExpand(novaCarteira.getId()).toUri();
			return ResponseEntity.created(uri).build();

		}

	}

	@Transactional
	private boolean possuiCarteira(TipoCarteira carteira, Cartao cartao) {
		return carteiraRepository.existsByCarteiraAndCartao(carteira, cartao);
	}

}
