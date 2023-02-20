package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Marca;
import br.com.fantonel.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {
	@Autowired
	private MarcaRepository marcaRepository;
	
	public MarcaService() {}
	
	public Optional<Marca> findById(UUID id){
		return marcaRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return marcaRepository.existsById(id);
	}
	
	public boolean existsByMarca(String marca) {
		return marcaRepository.existsByMarca(marca);
	}
	
	public Optional<Marca> findByMarca(String marca) {
		return marcaRepository.findByMarca(marca);		
	}
	
	public List<Marca> findAll(){
		return marcaRepository.findAll();
	}
	
	public Marca save(Marca bean) {
		return marcaRepository.save(bean);		
	}
	
	public void delete(Marca bean) {
		marcaRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		marcaRepository.deleteById(id);
	}
}