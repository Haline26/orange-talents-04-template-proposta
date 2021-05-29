package br.com.zupacademy.haline.proposta.analise;



import br.com.zupacademy.haline.proposta.proposta.Proposta;

public class AnalisePropostaRequest {

	private Long idProposta;
	private String documento;
	private String nome;

	public AnalisePropostaRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

}
