package br.com.zupacademy.haline.proposta.metricas;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

@Component
public class Metricas {

	private final MeterRegistry meterRegistry;

	public Metricas(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	public void contador() {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("emissora", "Mastercard"));
		tags.add(Tag.of("banco", "Itaú"));

		var counterPropostaCriadas = this.meterRegistry.counter("proposta_criada", tags);

	}

}
