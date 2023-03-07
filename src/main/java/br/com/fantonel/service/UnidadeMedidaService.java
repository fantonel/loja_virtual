package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.UnidadeMedida;
import br.com.fantonel.repository.UnidadeMedidaRepository;

@Service
@Transactional
public class UnidadeMedidaService {
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public UnidadeMedidaService() {
	}

	public boolean existsById(UUID id) {
		return unidadeMedidaRepository.existsById(id);
	}
	
	public boolean existsBySigla(String sigla) {
		return unidadeMedidaRepository.existsBySigla(sigla);
	}
	
	public boolean existsByDescricao(String descricao) {
		return unidadeMedidaRepository.existsByDescricao(descricao);
	}
	
	public Optional<UnidadeMedida> findById(UUID id){
		return unidadeMedidaRepository.findById(id);
	}
	
	public Optional<UnidadeMedida> buscarPorSigla(String sigla){
		return unidadeMedidaRepository.buscarPorSigla(sigla);
	}
	
	public Optional<UnidadeMedida> buscarPorDescricao(String descricao){
		return unidadeMedidaRepository.buscarPorDescricao(descricao);
	}

	public List<UnidadeMedida> findAll(){
		return unidadeMedidaRepository.findAll();
	}
	
	public UnidadeMedida save(UnidadeMedida bean) {
		return unidadeMedidaRepository.save(bean);		
	}
	
	public void delete(UnidadeMedida bean) {
		unidadeMedidaRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		unidadeMedidaRepository.deleteById(id);
	}
}