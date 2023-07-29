package br.com.fantonel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.NotaFiscalVenda;

public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM NotaFiscalVenda nfc WHERE nfc.numero = ?1")
	boolean existsByNumero(String numero);
	
	@Query("SELECT nfc FROM NotaFiscalVenda nfc WHERE nfc.numero = ?1")
	Optional<NotaFiscalVenda> findByNumero(String numero);
	
	@Query("SELECT nfc FROM NotaFiscalVenda nfc WHERE nfc.pessoaFisica.id = ?1")
	List<NotaFiscalVenda> findByPessoaFisica(UUID pessoaJuridicaId);
	
	@Query("SELECT nfc FROM NotaFiscalVenda nfc WHERE nfc.pessoaJuridica.id = ?1")
	List<NotaFiscalVenda> findByPessoaJuridica(UUID pessoaJuridicaId);
	
	@Query("SELECT nfc FROM NotaFiscalVenda nfc WHERE nfc.pedido.id = ?1")
	Optional<NotaFiscalVenda> findByPedido(UUID pedidoId);
}