package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.FormaPagamento;
import br.com.fantonel.repository.FormaPagamentoRepository;

@Service
@Transactional
public class FormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamentoService() {}
	
	public Optional<FormaPagamento> findById(UUID id){
		return formaPagamentoRepository.findById(id);
	}
	
	public boolean existsById(UUID id) {
		return formaPagamentoRepository.existsById(id);
	}
	
	public boolean existsByFormaPagamento(String formaPagamento) {
		return formaPagamentoRepository.existsByFormaPagamento(formaPagamento);
	}
	
	public Optional<FormaPagamento> findByFormaPagamento(String formaPagamento) {
		return formaPagamentoRepository.findByFormaPagamento(formaPagamento);		
	}
	
	public List<FormaPagamento> findAll(){
		return formaPagamentoRepository.findAll();
	}
	
	public FormaPagamento save(FormaPagamento bean) {
		return formaPagamentoRepository.save(bean);		
	}
	
	public void delete(FormaPagamento bean) {
		formaPagamentoRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		formaPagamentoRepository.deleteById(id);
	}
}