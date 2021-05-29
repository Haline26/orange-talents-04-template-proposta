package br.com.zupacademy.haline.proposta.validations.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers(HttpMethod.GET, "/propostas/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.POST, "/propostas/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.GET, "/biometria/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.POST, "/biometria/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.POST, "/bloqueio/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.POST, "/viagens/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.POST, "/carteiras/**")
				.hasAuthority("SCOPE_meu-primeiro-escopo").antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/prometheus").permitAll()
				.anyRequest().authenticated()

		).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

	}

}
