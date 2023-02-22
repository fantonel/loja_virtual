package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.CupomDesconto;
import br.com.fantonel.repository.CupomDescontoRepository;

@Service
@Transactional
public class CupomDescontoService {
	@Autowired
	private CupomDescontoRepository cupomDescontoRepository;
	
	public CupomDescontoService() {}
	
	public Optional<CupomDesconto> findById(UUID id){
		return cupomDescontoRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return cupomDescontoRepository.existsById(id);
	}
	
	public boolean existsByCodigo(String codigo) {
		return cupomDescontoRepository.existsByCodigo(codigo);
	}
	
	public Optional<CupomDesconto> findByCodigo(String codigo) {
		return cupomDescontoRepository.findByCodigo(codigo);
	}
	
	public List<CupomDesconto> findAll(){
		return cupomDescontoRepository.findAll();
	}
	
	public CupomDesconto save(CupomDesconto bean) {
		return cupomDescontoRepository.save(bean);		
	}
	
	public void delete(CupomDesconto bean) {
		cupomDescontoRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		cupomDescontoRepository.deleteById(id);
	}
}