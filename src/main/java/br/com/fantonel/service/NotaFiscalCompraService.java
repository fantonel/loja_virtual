package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.NotaFiscalCompra;
import br.com.fantonel.repository.NotaFiscalCompraRepository;

@Service
public class NotaFiscalCompraService {
	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;

	public NotaFiscalCompraService() {
	}
	
	public NotaFiscalCompra save(NotaFiscalCompra bean) {
		return notaFiscalCompraRepository.save(bean);
	}
	
	public void delete(NotaFiscalCompra entity) {
		notaFiscalCompraRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		notaFiscalCompraRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return notaFiscalCompraRepository.existsById(id);
	}
	
	public boolean existsByNumero(String numero) {
		return notaFiscalCompraRepository.existsByNumero(numero);
	}
	
	public Optional<NotaFiscalCompra> findById(UUID id){
		return notaFiscalCompraRepository.findById(id);
	}
	
	public Optional<NotaFiscalCompra> findByNumero(String numero){
		return notaFiscalCompraRepository.findByNumero(numero);
	}
	
	public List<NotaFiscalCompra> findByPessoaJuridica(UUID pessoaJuridicaId){
		return notaFiscalCompraRepository.findByPessoaJuridica(pessoaJuridicaId);
	}
	
	public List<NotaFiscalCompra> findAll(){
		return notaFiscalCompraRepository.findAll();
	}
}