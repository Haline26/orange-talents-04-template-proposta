package br.com.zupacademy.haline.proposta.cartao;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.haline.proposta.proposta.Proposta;
import br.com.zupacademy.haline.proposta.proposta.PropostaEstado;
import br.com.zupacademy.haline.proposta.proposta.PropostaRepository;
import feign.FeignException;

@Component
public class AsociCartao {

	private final Logger logger = LoggerFactory.getLogger(AsociCartao.class);
	private final PropostaRepository propostaRepository;
	private final CartaoClient cartaoClient;

	@Autowired
	public AsociCartao(PropostaRepository propostaRepository, CartaoClient cartaoClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoClient = cartaoClient;
	}
	
	
	@Scheduled(fixedRateString = "60000")
	@Transactional
	public void associarCartao() {
		logger.info("Verificando cartões");
		List<Proposta> propostaCartao = propostaRepository.findByCartaoIsNullAndPropostaEstadoIs(PropostaEstado.ELEGIVEL)
				.parallelStream().peek(proposta -> {
					try {
						CartoesResponse cartao = cartaoClient.getCartao(proposta.getId());
						proposta.setCartao(cartao.toModel(proposta));
						logger.info("Proposta id={} atualizada ", proposta.getId());
					} catch (FeignException e) {
						logger.info("Proposta id={} não possui cartão", proposta.getId());
						throw e;
						
					}

				}).collect(Collectors.filtering(proposta -> proposta.getCartao() != null, Collectors.toList()));
		propostaRepository.saveAll(propostaCartao);
	}

}
