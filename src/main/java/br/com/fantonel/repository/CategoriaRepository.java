package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM categoria c WHERE c.id = ?1")
	boolean existsById(UUID id);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM categoria c WHERE c.categoria = ?1")
	boolean existByCategoria(String categoria);
	
	@Query("SELECT c FROM Categoria c WHERE c.categoria = ?1")
	Optional<Categoria> findByCategoria(String categoria);
}