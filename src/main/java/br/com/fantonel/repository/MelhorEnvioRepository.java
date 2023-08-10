package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.MelhorEnvio;

@Repository
public interface MelhorEnvioRepository extends JpaRepository<MelhorEnvio, UUID> {
	@Query(nativeQuery = true, value = "SELECT * FROM melhorenvio me WHERE me.melhorenvio_inserirfreteid = ?")
	Optional<MelhorEnvio> buscarPorFreteInserido(String melhorEnvioInsereFreteId);
}