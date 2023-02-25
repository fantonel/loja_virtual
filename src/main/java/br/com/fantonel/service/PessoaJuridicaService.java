package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.PessoaJuridica;
import br.com.fantonel.repository.PessoaJuridicaRepository;

@Service
@Transactional
public class PessoaJuridicaService {
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	public PessoaJuridicaService() {}
	
	public boolean existsById(UUID id) {
		return pessoaJuridicaRepository.existsById(id);
	}
	
	public boolean existsByCnpj(String cnpj) {
		return pessoaJuridicaRepository.existsByCnpj(cnpj);
	}
	
	public boolean existsByEmail(String email) {
		return pessoaJuridicaRepository.existsByEmail(email);
	}
	
	public Optional<PessoaJuridica> findById(UUID id){
		return pessoaJuridicaRepository.findById(id);
	}
	
	public Optional<PessoaJuridica> findByCnpj(String cnpj) {
		return pessoaJuridicaRepository.findByCnpj(cnpj);
	}
	
	public Optional<PessoaJuridica> findByEmailPrincipal(String email) {
		return pessoaJuridicaRepository.findByEmailPrincipal(email);
	}
	
	public List<PessoaJuridica> findAll(){
		return pessoaJuridicaRepository.findAll();
	}
	
	public List<PessoaJuridica> listarExcluidas(){
		return pessoaJuridicaRepository.listarExcluidas();
	}
	
	public PessoaJuridica save(PessoaJuridica bean) {
		return pessoaJuridicaRepository.save(bean);		
	}
	
	public void delete(PessoaJuridica bean) {
		pessoaJuridicaRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		pessoaJuridicaRepository.deleteById(id);
	}
}