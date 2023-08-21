package br.com.fantonel.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.MelhorEnvio;
import br.com.fantonel.repository.MelhorEnvioRepository;

@Service
public class MelhorEnvioService {
	@Autowired
	private MelhorEnvioRepository melhorEnvioRepository;

	public MelhorEnvioService() {
	}
	
	public MelhorEnvio save(MelhorEnvio bean) {
		return melhorEnvioRepository.save(bean);
	}
	
	public void delete(MelhorEnvio entity) {
		melhorEnvioRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		melhorEnvioRepository.deleteById(id);
	}
	
	public Optional<MelhorEnvio> findById(UUID id){
		return melhorEnvioRepository.findById(id);
	}
	
	public Optional<MelhorEnvio> buscarPorFreteInserido(String melhorEnvioInsereFreteId){
		return melhorEnvioRepository.buscarPorFreteInserido(melhorEnvioInsereFreteId);		
	}
	
	public Optional<MelhorEnvio> buscarPorPedido(UUID pedidoID){
		return melhorEnvioRepository.buscarPorPedido(pedidoID);		
	}
}