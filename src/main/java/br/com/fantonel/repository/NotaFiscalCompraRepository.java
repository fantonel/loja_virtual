package br.com.fantonel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.NotaFiscalCompra;

public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM NotaFiscalCompra nfc WHERE nfc.numero = ?1")
	boolean existsByNumero(String numero);
	
	@Query("SELECT nfc FROM NotaFiscalCompra nfc WHERE nfc.numero = ?1")
	Optional<NotaFiscalCompra> findByNumero(String numero);
	
	@Query("SELECT nfc FROM NotaFiscalCompra nfc WHERE nfc.pessoaJuridica.id = ?1")
	List<NotaFiscalCompra> findByPessoaJuridica(UUID pessoaJuridicaId);
}