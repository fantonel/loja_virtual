package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.ProdutoImagem;

@Repository
public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagem, UUID> {
	
	@Query("SELECT proI FROM ProdutoImagem proI WHERE proI.produto.id = ?1")
	List<ProdutoImagem> findByProduto(UUID produtoId);
}