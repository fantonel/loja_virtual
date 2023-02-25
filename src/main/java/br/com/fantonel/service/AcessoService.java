package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Acesso;
import br.com.fantonel.repository.AcessoRepository;

@Service
public class AcessoService {
	@Autowired
	private AcessoRepository acessoRepository;
	 
	 public boolean existeUsuarioVinculado(UUID id) {
		 return acessoRepository.existeUsuarioVinculado(id);
	 }
	 
	 public boolean existsById(UUID id) {
		 return acessoRepository.existsById(id);
	 }
	 
	 public boolean existsByDescricao(String descricao) {
		 return acessoRepository.existsByDescricao(descricao);
	 }
	 
	 public Optional<Acesso> findById(UUID id) {
		 return acessoRepository.findById(id);
	 }
	 
	 public List<Acesso> getAll(){
		 return acessoRepository.findAll();
	 }
	 
	 public Optional<Acesso> buscarPorDescricao(String descricao) {
		 return acessoRepository.buscarPorDescricao(descricao);
	 }
	 
	 public Acesso save(Acesso entity) {
		 return acessoRepository.save(entity);
	 }
	 
	 public void delete(UUID id) {
		 acessoRepository.deleteById(id);
	 }
}