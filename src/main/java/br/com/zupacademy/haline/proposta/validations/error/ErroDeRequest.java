package br.com.zupacademy.haline.proposta.validations.error;

public class ErroDeRequest {
	
	private String campo;
	private String erro;
	
	public ErroDeRequest(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
	
	

}
