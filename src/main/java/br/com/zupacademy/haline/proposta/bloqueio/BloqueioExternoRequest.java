package br.com.zupacademy.haline.proposta.bloqueio;


public class BloqueioExternoRequest {

	private String sistemaResp;
	
	@Deprecated
	public BloqueioExternoRequest() {
	}

	public BloqueioExternoRequest(String sistemaResponsavel) {
		this.sistemaResp = sistemaResponsavel;
	}

	public String getSistemaResp() {
		return sistemaResp;
	}	

}
