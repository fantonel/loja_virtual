package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.ProdutoImagem;

@Repository
public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagem, UUID> {	
	@Query("SELECT proI FROM ProdutoImagem proI WHERE proI.produto.id = ?1")
	List<ProdutoImagem> findByProduto(UUID produtoId);
	
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "DELETE FROM produtoimagem WHERE produto_id = ?1")
	void excluirTodas(UUID produtoId);
}