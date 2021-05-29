package br.com.zupacademy.haline.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private LocalDateTime instanteBloqueio;

	@NotBlank
	private String ip;

	@NotBlank
	private String userAgent;

	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Cartao cartao;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String ip, String userAgent, Cartao cartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}



	


}
