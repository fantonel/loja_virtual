package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.ProdutoImagem;
import br.com.fantonel.repository.ProdutoImagemRepository;

@Service
@Transactional
public class ProdutoImagemService {
	@Autowired
	private ProdutoImagemRepository produtoImagemRepository;

	public ProdutoImagemService() {
	}
	
	public ProdutoImagem save(ProdutoImagem bean) {
		return produtoImagemRepository.save(bean);
	}
	
	public void delete(ProdutoImagem entity) {
		produtoImagemRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		produtoImagemRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return produtoImagemRepository.existsById(id);
	}
	
	public Optional<ProdutoImagem> findById(UUID id){
		return produtoImagemRepository.findById(id);
	}
	
	public List<ProdutoImagem> findByProduto(UUID id){
		return produtoImagemRepository.findByProduto(id);
	}
	
	public List<ProdutoImagem> findAll(){
		return produtoImagemRepository.findAll();
	}
}