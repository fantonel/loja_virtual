package br.com.fantonel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, UUID> {
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM transportadora tra WHERE tra.pessoajuridica_id = ?1 LIMIT 1")
	boolean existsTransportadora(UUID pessoaJuridicaID);
}