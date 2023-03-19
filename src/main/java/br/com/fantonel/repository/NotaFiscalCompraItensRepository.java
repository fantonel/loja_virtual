package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.NotaFiscalCompraItens;

@Repository
public interface NotaFiscalCompraItensRepository extends JpaRepository<NotaFiscalCompraItens, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM NotaFiscalCompraItens nfit WHERE nfit.id = ?1")
	 boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM NotaFiscalCompraItens nfit WHERE nfit.notaFiscalCompra.id = ?1 AND nfit.produto.id = ?2")
	boolean existsByNotaProduto(UUID notaId, UUID produtoId);
	
	@Query("SELECT nfit FROM NotaFiscalCompraItens nfit WHERE nfit.notaFiscalCompra.numero = ?1")
	List<NotaFiscalCompraItens> listarPorNumeroNota(String numero);
	
	@Query("SELECT nfit FROM NotaFiscalCompraItens nfit WHERE nfit.produto.id = ?1")
	List<NotaFiscalCompraItens> listarPorProdtuto(UUID produtoId);
	
	//@Modifying(clearAutomatically = true)
	//@Query(value = "delete from NotaFiscalCompraItens nfcit WHERE nfcit.id=?1")
	void deleteById(UUID id);
}