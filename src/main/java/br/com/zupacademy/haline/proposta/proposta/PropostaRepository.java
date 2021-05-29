package br.com.zupacademy.haline.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.micrometer.core.lang.NonNull;

public interface PropostaRepository extends CrudRepository<Proposta, Long>{

	Optional<Proposta> findByDocumento(String documento);
	
	List<Proposta> findByCartaoIsNullAndPropostaEstadoIs(@NonNull PropostaEstado estado);


}
