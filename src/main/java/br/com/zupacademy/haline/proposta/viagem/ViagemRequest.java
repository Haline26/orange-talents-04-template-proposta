package br.com.zupacademy.haline.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

public class ViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate dataTermino;

	public ViagemRequest(String destino, LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public Viagem toModel(Cartao cartao, String ip, String userAgent) {
		return new Viagem(cartao, this.destino, this.dataTermino, ip, userAgent);
	}

}
