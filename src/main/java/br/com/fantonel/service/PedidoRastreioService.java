package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.PedidoRastreio;
import br.com.fantonel.repository.PedidoRastreioRepository;

@Service
public class PedidoRastreioService {
	@Autowired
	private PedidoRastreioRepository pedidoRastreioRepository;
	
	public PedidoRastreioService(){}
	
	public Optional<PedidoRastreio> findById(UUID id){
		return pedidoRastreioRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return pedidoRastreioRepository.existsById(id);
	}
	
	public List<PedidoRastreio> findAll(){
		return pedidoRastreioRepository.findAll();
	}
	
	public PedidoRastreio save(PedidoRastreio bean) {
		return pedidoRastreioRepository.save(bean);		
	}
	
	public void delete(PedidoRastreio bean) {
		pedidoRastreioRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		pedidoRastreioRepository.deleteById(id);
	}
	
	public List<PedidoRastreio> findByPedidoID(UUID pedidoID){
		return pedidoRastreioRepository.findByPedidoID(pedidoID);
	}
}