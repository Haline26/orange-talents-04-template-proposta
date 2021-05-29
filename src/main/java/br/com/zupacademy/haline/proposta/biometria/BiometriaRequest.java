package br.com.zupacademy.haline.proposta.biometria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

import java.util.Base64;

public class BiometriaRequest {

	@NotBlank
	private String fingerprint;

	
	

	public String getFingerprint() {
		return fingerprint;
	}




	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}




	public Biometria toModel(Cartao cartao) {
		byte[] fingerprintDecoded = Base64.getDecoder().decode(this.fingerprint.getBytes());
		return new Biometria(fingerprintDecoded, cartao);

	}

}
