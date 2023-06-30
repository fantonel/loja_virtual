package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.CupomDesconto;

public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM CupomDesconto cd WHERE cd.id = ?1")
	boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM CupomDesconto cd WHERE cd.codigo = ?1")
	boolean existsByCodigo(String codigo);
	
	@Query(value = "SELECT cd FROM CupomDesconto cd WHERE cd.id = ?1 AND CURRENT_DATE >= cd.validadeInicial AND (cd.validadeFinal IS NULL OR CURRENT_DATE <= cd.validadeFinal)")
	Optional<CupomDesconto> buscarCupomValido(UUID id);
	
	Optional<CupomDesconto> findByCodigo(String codigo);
}