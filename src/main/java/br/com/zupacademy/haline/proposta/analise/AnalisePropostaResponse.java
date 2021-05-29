package br.com.zupacademy.haline.proposta.analise;

public class AnalisePropostaResponse {
	
	private Long idProposta;
	private String documento;
	private String nome;
	private AnaliseProposta analiseProposta;
	
	public AnalisePropostaResponse(Long idProposta, String documento, String nome, AnaliseProposta analiseProposta) {
		this.idProposta = idProposta;
		this.documento = documento;
		this.nome = nome;
		this.analiseProposta = analiseProposta;
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

	public AnaliseProposta getAnaliseProposta() {
		return analiseProposta;
	}
	
	
	

}
