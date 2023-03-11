package br.com.fantonel.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.ProdutoAvaliacao;
import br.com.fantonel.repository.ProdutoAvaliacaoRepository;

@Service
@Transactional
public class ProdutoAvaliacaoService {
	@Autowired
	private ProdutoAvaliacaoRepository produtoAvaliacaoRepository;

	public ProdutoAvaliacaoService() {
	}
	
	public ProdutoAvaliacao save(ProdutoAvaliacao bean) {
		return produtoAvaliacaoRepository.save(bean);		
	}
	
	public void delete(ProdutoAvaliacao bean) {
		produtoAvaliacaoRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		produtoAvaliacaoRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return produtoAvaliacaoRepository.existsById(id);
	}
	
	public List<ProdutoAvaliacao> findAll(){
		return produtoAvaliacaoRepository.findAll();
	}
	
	public List<ProdutoAvaliacao> findByPessoaFisica(UUID pessoaId){
		return produtoAvaliacaoRepository.findByPessoaFisica(pessoaId);
	}
	
	public List<ProdutoAvaliacao> findByProduto(UUID produtoId){
		return produtoAvaliacaoRepository.findByProduto(produtoId);
	}
}