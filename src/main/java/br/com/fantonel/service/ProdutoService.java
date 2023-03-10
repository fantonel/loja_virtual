package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Produto;
import br.com.fantonel.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public ProdutoService() {
	}
	
	public Produto save(Produto bean) {
		return produtoRepository.save(bean);
	}
	
	public void delete(Produto entity) {
		produtoRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		produtoRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return produtoRepository.existsById(id);
	}
	
	public boolean existsByNome(String nome) {
		return produtoRepository.existsByNome(nome);
	}
	
	public boolean existePorNomeMarca(String nome, UUID marcaId) {
		return produtoRepository.existePorNomeMarca(nome, marcaId);
	}
	
	public boolean existeOutroProdutoPorNomeMarca(UUID produtoId, String nome, UUID marcaId) {
		return produtoRepository.existeOutroProdutoPorNomeMarca(produtoId, nome, marcaId);
	}
	
	public Optional<Produto> findById(UUID id){
		return produtoRepository.findById(id);
	}

	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public List<Produto> listarPorCategoria(UUID categoriaId){
		return produtoRepository.listarPorCategoria(categoriaId);
	}
		
	public List<Produto> listarPorNome(String nome){
		return produtoRepository.listarPorNome(nome);
	}
}