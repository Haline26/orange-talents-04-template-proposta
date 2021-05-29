package br.com.zupacademy.haline.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.haline.proposta.bloqueio.BloqueioExternoRequest;
import br.com.zupacademy.haline.proposta.bloqueio.BloqueioExternoRespo;
import br.com.zupacademy.haline.proposta.carteira.CarteiraRequest;
import br.com.zupacademy.haline.proposta.viagem.ViagemRequest;

@Component
@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoClient {

	@GetMapping
	public CartoesResponse getCartao(@RequestParam("idProposta") Long idProposta);

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios", produces = "application/json")
	public BloqueioExternoRespo bloquear(@PathVariable("id") String id, @RequestBody BloqueioExternoRequest request);

	@PostMapping("/{id}/avisos")
	public void informeViagem(@PathVariable String id, @RequestBody ViagemRequest request);

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/carteiras", produces = "application/json")
	public void associaCarteira(@PathVariable("id") String id, @RequestBody CarteiraRequest request);

}
