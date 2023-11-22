package br.com.fantonel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Acesso;
import br.com.fantonel.repository.AcessoRepository;

@Service
public class AcessoService {
	@Autowired
	private AcessoRepository acessoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	 
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
	 
	 public boolean temAcessoRole(String userName, String role) {
			String sql;
			sql = "SELECT COUNT(*) = 1 \n";
			sql+= "  FROM usuario usu INNER JOIN usuarioacessos uac ON (uac.usuario_id = usu.id) \n";
			sql+= "                   INNER JOIN acesso ace ON (ace.id = uac.acesso_id) \n";
			sql+= " WHERE LOWER(usu.login) = '"+userName.toLowerCase()+"' AND UPPER(ace.descricao) IN ("+role.toUpperCase()+") \n";
			sql+= "LIMIT 1";
			Query qry = entityManager.createNativeQuery(sql);
			boolean result = Boolean.valueOf(qry.getSingleResult().toString());
			return result;
	 }
}