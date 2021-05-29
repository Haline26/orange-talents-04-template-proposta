package br.com.zupacademy.haline.proposta.proposta;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.haline.proposta.cartao.Cartao;
import br.com.zupacademy.haline.proposta.validations.criptografia.CriptografarDados;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	@Convert(converter = CriptografarDados.class)
	private String documento;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	

	@Enumerated(EnumType.STRING)
	private PropostaEstado propostaEstado;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public PropostaEstado getPropostaEstado() {
		return propostaEstado;
	}

	public void setPropostaEstado(PropostaEstado propostaEstado) {
		this.propostaEstado = propostaEstado;
	}

	public Cartao getCartao() {
		return cartao;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + ", propostaEstado=" + propostaEstado + "]";
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
