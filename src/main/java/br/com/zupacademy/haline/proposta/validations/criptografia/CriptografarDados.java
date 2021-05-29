package br.com.zupacademy.haline.proposta.validations.criptografia;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.encrypt.Encryptors;

@Converter
public class CriptografarDados implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String dado) {
		return Encryptors.text("${criptografia.secret}", "574bed3ab0c9c7").encrypt(dado);
	}

	@Override
	public String convertToEntityAttribute(String dado) {
		 return Encryptors.text("${criptografia.secret}", "574bed3ab0c9c7").decrypt(dado);
	}

}
