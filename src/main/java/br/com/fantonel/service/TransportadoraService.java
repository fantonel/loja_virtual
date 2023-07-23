package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Transportadora;
import br.com.fantonel.repository.TransportadoraRepository;

@Service
public class TransportadoraService {
	@Autowired
	private TransportadoraRepository transportadoraRepository;

	public TransportadoraService() {
	}
	
	public Optional<Transportadora> findById(UUID id){
		return transportadoraRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return transportadoraRepository.existsById(id);
	}
	
	public List<Transportadora> findAll(){
		return transportadoraRepository.findAll();
	}
	
	public Transportadora save(Transportadora bean) {
		return transportadoraRepository.save(bean);		
	}
	
	public void delete(Transportadora bean) {
		transportadoraRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		transportadoraRepository.deleteById(id);
	}
}