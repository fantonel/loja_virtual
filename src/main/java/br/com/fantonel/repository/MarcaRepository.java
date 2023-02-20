package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM Marca m WHERE m.id = ?1")
	boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM Marca m WHERE m.marca = ?1")
	boolean existsByMarca(String marca);
	
	Optional<Marca> findByMarca(String marca);
}