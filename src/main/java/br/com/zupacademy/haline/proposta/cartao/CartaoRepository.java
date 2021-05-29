package br.com.zupacademy.haline.proposta.cartao;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	Optional<Cartao> findByNumeroCartao(String numeroCartao);
	
	
	 

}
