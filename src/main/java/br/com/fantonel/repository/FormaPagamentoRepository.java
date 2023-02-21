package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM FormaPagamento fp WHERE fp.id = ?1")
	boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM FormaPagamento fp WHERE fp.formaPagamento = ?1")
	boolean existsByFormaPagamento(String formaPagamento);
	
	Optional<FormaPagamento> findByFormaPagamento(String formaPagamento);
}