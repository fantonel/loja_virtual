package br.com.fantonel.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.fantonel.model.TokensApis;

@Service
public class TokensApisService {	
	@PersistenceContext
	private EntityManager entityManager;

	public TokensApisService() {
	}
	
	public TokensApis buscarTokenAtivo(String apiNome) {
		String sql = "SELECT a FROM TokensApis WHERE apinome = ?1";
		try {
			return entityManager.createQuery(sql, TokensApis.class).setMaxResults(1).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
}