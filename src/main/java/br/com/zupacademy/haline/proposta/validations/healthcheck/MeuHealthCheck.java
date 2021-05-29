package br.com.zupacademy.haline.proposta.validations.healthcheck;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MeuHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
    	Map<String, Object> details = new HashMap<>();
        details.put("versão", "2.3.1");
        details.put("descrição", " Health Check customizado");
        details.put("endereço", "456.306");
        
        return Health.status(Status.UP).withDetails(details).build();
    }
}
