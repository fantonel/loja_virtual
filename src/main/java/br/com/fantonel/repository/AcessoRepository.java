package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fantonel.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, UUID>{
	boolean existsById(UUID id);
	
	boolean existsByDescricao(String descricao);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM usuarioacessos usa WHERE usa.acesso_id = ?1")
	boolean existeUsuarioVinculado(UUID id);
	
	@Query("SELECT a FROM Acesso a WHERE a.descricao=?1")
	Optional<Acesso> buscarPorDescricao(String descricao);
}