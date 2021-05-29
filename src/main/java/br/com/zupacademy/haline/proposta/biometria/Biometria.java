package br.com.zupacademy.haline.proposta.biometria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

@Entity
public class Biometria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private byte[] fingerprint;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Cartao cartao;	
	
	public Biometria() {

	}

	public Biometria( byte[] fingerprint, Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}
	public byte[] getFingerprint() {
		return fingerprint;
	}
	public Cartao getCartao() {
		return cartao;
	}

	public void setFingerprint(byte[] fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	

}
