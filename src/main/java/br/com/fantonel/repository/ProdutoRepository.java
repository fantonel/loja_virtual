package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
	@Query(nativeQuery = true, value = "SELECT COUNT(*) = 1 FROM Produto pro WHERE pro.nome = ?1")
	boolean existsByNome(String nome);
	
	@Query("SELECT COUNT(*) >= 1 FROM Produto pro WHERE pro.nome=?1 AND pro.marca.id=?2")
	boolean existePorNomeMarca(String nome, UUID marcaId);
	
	@Query("SELECT COUNT(*) >= 1 FROM Produto pro WHERE pro.id <> ?1 AND pro.nome=?2 AND pro.marca.id=?3")
	boolean existeOutroProdutoPorNomeMarca(UUID produtoId, String nome, UUID marcaId);
	
	@Query(nativeQuery = true, value = "SELECT pro.* FROM produtocategoria_view pro WHERE pro.categoria_id = ?1")
	List<Produto> listarPorCategoria(UUID categoriaId);
	
	//ILIKE, ignora case sensitive
	@Query(nativeQuery = true, value = "SELECT pro.* FROM produto pro WHERE pro.nome ILIKE %?1%")
	List<Produto> listarPorNome(String nome);
}