package br.com.zupacademy.haline.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

@Entity
public class Viagem {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cartao cartao;
	
	@Column(nullable = false)
	private String destino;
	
	@Column(nullable = false)
	private LocalDate dataTermino;
	
	@CreationTimestamp
	private LocalDateTime dataInstante;
	
	@Column(nullable = false)
	private String ip;
	
	@Column(nullable = false)
	private String userAgent;
	
	@Deprecated
	public Viagem() {
	}

	

	public Viagem(Cartao cartao, String destino, LocalDate dataTermino, String ip, String userAgent) {
		this.cartao = cartao;
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ip = ip;
		this.userAgent = userAgent;
	}



	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public LocalDateTime getDataInstante() {
		return dataInstante;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}
	
	
	 
	
}
