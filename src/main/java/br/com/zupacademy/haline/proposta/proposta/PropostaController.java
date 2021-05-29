package br.com.zupacademy.haline.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.haline.proposta.analise.AnalisePropostaClient;
import br.com.zupacademy.haline.proposta.analise.AnalisePropostaRequest;
import br.com.zupacademy.haline.proposta.metricas.Metricas;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	private PropostaRepository propostaRepository;
	private AnalisePropostaClient analisePropostaClient;
	private Metricas metricas;	
	private final Tracer tracer;


	public PropostaController(PropostaRepository propostaRepository, AnalisePropostaClient analisePropostaClient,
			Metricas metricas, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.analisePropostaClient = analisePropostaClient;
		this.metricas = metricas;
		this.tracer = tracer;
	}

	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@GetMapping("/{id}")
	public ResponseEntity<?> buscaProposta(@PathVariable Long id) {
		Proposta proposta = propostaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Proposta com id= {} não encontrada " + id));

		return ResponseEntity.ok(new PropostaResponse(proposta));

	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder) {
		
	
		Span activeSpan = tracer.buildSpan("").start();
		activeSpan.setTag("user.email", "");
		String userEmail = activeSpan.getBaggageItem("user.email");
		activeSpan.setBaggageItem("user.email", userEmail);
		activeSpan.log("");
		
		activeSpan.finish();


		Proposta novaProposta = request.tranformaProposta();
		Optional<Proposta> props = propostaRepository.findByDocumento(novaProposta.getDocumento());

		
		if (props.isPresent()) {
			return ResponseEntity.status(422).body(" Proposta já existente para  solicitante.");
		}

		
		propostaRepository.save(novaProposta);
		logger.info("Proposta efeitivada com sucesso!");
		var validProposta = propostaRepository.save(novaProposta);
		metricas.contador();

		
		try {
			var validacaoRequest = new AnalisePropostaRequest(validProposta);
			analisePropostaClient.analisaProposta(validacaoRequest);
			validProposta.setPropostaEstado(PropostaEstado.ELEGIVEL);
		} catch (FeignException e) {
			e.printStackTrace();
			validProposta.setPropostaEstado(PropostaEstado.NAO_ELEGIVEL);
		}

		URI enderecoUri = builder.path("proposta/{id}").build(novaProposta.getDocumento());
		return ResponseEntity.created(enderecoUri).build();

	}

}
