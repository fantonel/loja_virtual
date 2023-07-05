package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.PedidoProdutos;

public interface PedidoProdutosRepository extends JpaRepository<PedidoProdutos, UUID> {
	@Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM pedidoprodutos pp WHERE pp.pedido_id = ?1 AND pp.produto_id = ?2")
	boolean existsByPedidoIdAndProdutoId(UUID pedidoID, UUID produtoId);
	
	@Query(value = "SELECT pp FROM PedidoProdutos pp WHERE pp.pedido.id = ?1")
	List<PedidoProdutos> findByPedidoId(UUID pedidoId);
	
	@Query(value = "SELECT pp FROM PedidoProdutos pp WHERE pp.pedido.id = ?1 AND pp.produto.id = ?2")
	List<PedidoProdutos> findByPedidoIdAndProdutoId(UUID pedidoId, UUID produtoID);
}