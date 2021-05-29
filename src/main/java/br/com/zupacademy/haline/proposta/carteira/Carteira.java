package br.com.zupacademy.haline.proposta.carteira;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@Column(nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCarteira carteira;

	@ManyToOne
	private Cartao cartao;

	public Carteira(Cartao cartao, String email, TipoCarteira carteira) {
		this.cartao = cartao;
		this.email = email;
		this.carteira = carteira;

	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
