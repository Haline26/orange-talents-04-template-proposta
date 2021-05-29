package br.com.zupacademy.haline.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;

	@NotNull
	private TipoCarteira carteira;

	public CarteiraRequest(String email, TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(cartao, this.email, this.carteira);

	}

}
