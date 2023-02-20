package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Categoria;
import br.com.fantonel.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService() {}
	
	public Categoria save(Categoria bean) {
		return categoriaRepository.save(bean);
	}
	
	public void delete(Categoria entity) {
		categoriaRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		categoriaRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return categoriaRepository.existsById(id);
	}
	
	public Optional<Categoria> findById(UUID id){
		return categoriaRepository.findById(id);
	}
	
	public boolean existByCategoria(String categoria) {
		return categoriaRepository.existByCategoria(categoria);
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> findByCategoria(String categoria){
		return categoriaRepository.findByCategoria(categoria);
	}
}