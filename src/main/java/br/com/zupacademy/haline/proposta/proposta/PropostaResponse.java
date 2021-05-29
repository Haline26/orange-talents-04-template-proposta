package br.com.zupacademy.haline.proposta.proposta;

import java.math.BigDecimal;


public class PropostaResponse {

	private String nome;
	private String documento;
	private String email;
	private String endereco;
	private BigDecimal salario;
	private PropostaEstado status;

	public PropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getPropostaEstado();
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public PropostaEstado getStatus() {
		return status;
	}

	
}
