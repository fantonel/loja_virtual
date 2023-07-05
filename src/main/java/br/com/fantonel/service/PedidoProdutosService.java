package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.PedidoProdutos;
import br.com.fantonel.repository.PedidoProdutosRepository;

@Service
public class PedidoProdutosService {
	@Autowired
	private PedidoProdutosRepository pedidoProdutosRepository;
	
	public Optional<PedidoProdutos> findById(UUID id){
		return pedidoProdutosRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return pedidoProdutosRepository.existsById(id);
	}
	
	public List<PedidoProdutos> findAll(){
		return pedidoProdutosRepository.findAll();
	}
	
	public PedidoProdutos save(PedidoProdutos bean) {
		return pedidoProdutosRepository.save(bean);		
	}
	
	public void delete(PedidoProdutos bean) {
		pedidoProdutosRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		pedidoProdutosRepository.deleteById(id);
	}
	
	public List<PedidoProdutos> findByPedidoID(UUID pedidoID){
		return pedidoProdutosRepository.findByPedidoId(pedidoID);
	}
	
	public List<PedidoProdutos> findByPedidoIDAndProdutoID(UUID pedidoID, UUID produtoID){
		return pedidoProdutosRepository.findByPedidoIdAndProdutoId(pedidoID, produtoID);
	}
	
	public boolean existsByPedidoIdAndProdutoId(UUID pedidoID, UUID produtoId) {
		return pedidoProdutosRepository.existsByPedidoIdAndProdutoId(pedidoID, produtoId);
	}
}