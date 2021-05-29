package br.com.zupacademy.haline.proposta.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioExternoRespo {
	
	private String resultado;

    public BloqueioExternoRespo(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

}
