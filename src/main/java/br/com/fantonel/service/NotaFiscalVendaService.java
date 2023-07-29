package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.NotaFiscalVenda;
import br.com.fantonel.repository.NotaFiscalVendaRepository;

@Service
public class NotaFiscalVendaService {
	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;

	public NotaFiscalVendaService() {
	}
	
	public NotaFiscalVenda save(NotaFiscalVenda bean) {
		return notaFiscalVendaRepository.save(bean);
	}
	
	public void delete(NotaFiscalVenda entity) {
		notaFiscalVendaRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		notaFiscalVendaRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return notaFiscalVendaRepository.existsById(id);
	}
	
	public boolean existsByNumero(String numero) {
		return notaFiscalVendaRepository.existsByNumero(numero);
	}
	
	public Optional<NotaFiscalVenda> findById(UUID id){
		return notaFiscalVendaRepository.findById(id);
	}
	
	public Optional<NotaFiscalVenda> findByNumero(String numero){
		return notaFiscalVendaRepository.findByNumero(numero);
	}
	
	public List<NotaFiscalVenda> findByPessoaJuridica(UUID pessoaJuridicaId){
		return notaFiscalVendaRepository.findByPessoaJuridica(pessoaJuridicaId);
	}
	
	public List<NotaFiscalVenda> findByPessoaFisica(UUID pessoaFisicaId){
		return notaFiscalVendaRepository.findByPessoaFisica(pessoaFisicaId);
	}
	
	public Optional<NotaFiscalVenda> findByPedido(UUID pedidoId){
		return notaFiscalVendaRepository.findByPedido(pedidoId);
	}
	
	public List<NotaFiscalVenda> findAll(){
		return notaFiscalVendaRepository.findAll();
	}
}