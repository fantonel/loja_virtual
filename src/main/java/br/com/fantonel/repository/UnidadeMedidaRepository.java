package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, UUID> {
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM UnidadeMedida uni WHERE uni.sigla = ?1")
	public boolean existsBySigla(String sigla);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM UnidadeMedida uni WHERE uni.descricao = ?1")
	public boolean existsByDescricao(String descricao);	

	@Query("SELECT uni FROM UnidadeMedida uni WHERE uni.sigla = ?1")
	Optional<UnidadeMedida> buscarPorSigla(String sigla);
	
	@Query("SELECT uni FROM UnidadeMedida uni WHERE uni.descricao = ?1")
	Optional<UnidadeMedida> buscarPorDescricao(String descricao);
}