package br.com.fantonel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.TokensApis;

@Repository
public interface TokensApisRepository extends JpaRepository<TokensApis, UUID> {
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM TokensApis api WHERE api.apiNome = ?1")
	void deleteByApiNome(String apinome);
}