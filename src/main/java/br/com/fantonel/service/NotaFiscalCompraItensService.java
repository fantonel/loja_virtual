package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.NotaFiscalCompraItens;
import br.com.fantonel.repository.NotaFiscalCompraItensRepository;

@Service
@Transactional
public class NotaFiscalCompraItensService {
	@Autowired
	private NotaFiscalCompraItensRepository notaFiscalCompraItensRepository;

	public NotaFiscalCompraItensService() {
	}
	
	public NotaFiscalCompraItens save(NotaFiscalCompraItens bean) {
		return notaFiscalCompraItensRepository.save(bean);
	}
	
	public void delete(NotaFiscalCompraItens entity) {
		notaFiscalCompraItensRepository.delete(entity);
	}
	
	public void deleteById(UUID id) {
		notaFiscalCompraItensRepository.deleteById(id);
	}
	
	public boolean existsById(UUID id) {
		return notaFiscalCompraItensRepository.existsById(id);
	}
	
	public boolean existsByNotaProduto(UUID notaId, UUID produtoId) {
		return notaFiscalCompraItensRepository.existsByNotaProduto(notaId, produtoId);
	}
	
	public Optional<NotaFiscalCompraItens> findById(UUID id){
		return notaFiscalCompraItensRepository.findById(id);
	}
	
	public List<NotaFiscalCompraItens> findAll(){
		return notaFiscalCompraItensRepository.findAll();
	}
	
	public List<NotaFiscalCompraItens> listarPorNumeroNota(String numero){
		return notaFiscalCompraItensRepository.listarPorNumeroNota(numero);
	}
	
	public List<NotaFiscalCompraItens> listarPorProdtuto(UUID produtoId){
		return notaFiscalCompraItensRepository.listarPorProdtuto(produtoId);
	}
}