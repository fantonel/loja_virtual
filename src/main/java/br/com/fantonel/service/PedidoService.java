package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Pedido;
import br.com.fantonel.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	public PedidoService() {
	}
	
	public Pedido save(Pedido bean) {
		return pedidoRepository.save(bean);
	}
	
	public void delete(Pedido entity) {
		pedidoRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		pedidoRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return pedidoRepository.existsById(id);
	}
	
	public Optional<Pedido> findById(UUID id){
		return pedidoRepository.findById(id);
	}
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public List<Pedido> findByPessoaFisica(UUID id){
		return pedidoRepository.findByPessoaFisica(id);
	}
	
	public List<Pedido> findByPessoaJuridica(UUID id){
		return pedidoRepository.findByPessoaJuridica(id);
	}
	
	public List<Pedido> findByPessoaFisicaCpf(String cpf){
		return pedidoRepository.findByPessoaFisicaCpf(cpf);
	}
	
	public List<Pedido> findByPessoaJuridica(String cnpj){
		return pedidoRepository.findByPessoaJuridicaCnpj(cnpj);
	}
}