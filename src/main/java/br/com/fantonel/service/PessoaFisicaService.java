package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.PessoaFisica;
import br.com.fantonel.repository.PessoaFisicaRepository;

@Service
@Transactional
public class PessoaFisicaService {
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	public PessoaFisicaService() {}
	
	public boolean existsById(UUID id) {
		return pessoaFisicaRepository.existsById(id);
	}
	
	public boolean existsByCpf(String cpf) {
		return pessoaFisicaRepository.existsByCpf(cpf);
	}
	
	public boolean existsByEmail(String email) {
		return pessoaFisicaRepository.existsByEmail(email);
	}
	
	public Optional<PessoaFisica> findById(UUID id){
		return pessoaFisicaRepository.findById(id);
	}
	
	public Optional<PessoaFisica> findByCpf(String cpf) {
		return pessoaFisicaRepository.findByCpf(cpf);
	}
	
	public Optional<PessoaFisica> findByEmail(String email) {
		return pessoaFisicaRepository.findByEmail(email);
	}
	
	public List<PessoaFisica> findAll(){
		return pessoaFisicaRepository.findAll();
	}
	
	public List<PessoaFisica> listarExcluidas(){
		return pessoaFisicaRepository.listarExcluidas();
	}
	
	public PessoaFisica save(PessoaFisica bean) {
		return pessoaFisicaRepository.save(bean);		
	}
	
	public void delete(PessoaFisica bean) {
		pessoaFisicaRepository.delete(bean);
	}
	
	public void deleteById(UUID id) {
		pessoaFisicaRepository.deleteById(id);
	}
}