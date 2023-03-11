package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.ProdutoAvaliacao;

public interface ProdutoAvaliacaoRepository extends JpaRepository<ProdutoAvaliacao, UUID> {
	boolean existsById(UUID id);	
	
	@Query("SELECT pAval FROM ProdutoAvaliacao pAval WHERE pAval.pessoaFisica.id = ?1")
	List<ProdutoAvaliacao> findByPessoaFisica(UUID pessoaId);
	
	@Query("SELECT pAval FROM ProdutoAvaliacao pAval WHERE pAval.produto.id = ?1")
	List<ProdutoAvaliacao> findByProduto(UUID produtoId);
}