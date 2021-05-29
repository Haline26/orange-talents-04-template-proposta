package br.com.zupacademy.haline.proposta.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.haline.proposta.cartao.Cartao;

public interface CarteiraRepository extends JpaRepository<Carteira, Long>{
	
	boolean existsByCarteiraAndCartao(TipoCarteira tipoCarteira, Cartao cartao);

}
