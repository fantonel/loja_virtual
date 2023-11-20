package br.com.fantonel.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fantonel.model.TokensApis;
import br.com.fantonel.repository.TokensApisRepository;
import br.com.fantonel.util.ApiIntegracao;

@Service
public class TokensApisService {	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public TokensApisRepository tokensApisRepository;

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
	
	public TokensApis obterTokenApiPgto() {
		final String apiNome = "JUNO"; 
		TokensApis token = buscarTokenAtivo(apiNome);	
		if (token == null || token.hasExpired()) {
			//Informações fictícias. Substituir pelas corretas no momento apropriado.
			String clienteID = "vi7QZerW09C8JG1xd5Dw51e8f45321_";
			String secretID = "$A_+&ksH}&+2<3VM]1MZqc,F_xif_Dxd5Dw51581213";
			
			Client client;
			WebResource webResource;
			try {
				client = new HostIgnoringClient(ApiIntegracao.HOST_API_PGTO).hostIgnoringClient();
				webResource  = client.resource(ApiIntegracao.HOST_API_PGTO+"authorization-server/oauth/token?grant_type=client_credentials");
				
				String chaveBasica = clienteID + ":" + secretID;
				String tokenAutenticao = DatatypeConverter.printBase64Binary(chaveBasica.getBytes());
				
				ClientResponse clientResponse = webResource.
						accept(MediaType.APPLICATION_FORM_URLENCODED)
						.type(MediaType.APPLICATION_FORM_URLENCODED)
						.header("Content-Type", "application/x-www-form-urlencoded")
						.header("Authorization", "Basic " + tokenAutenticao)
						.post(ClientResponse.class);				
				if (clientResponse.getStatus() == 200) {
					tokensApisRepository.deleteByApiNome(apiNome);
					//tokensApisRepository.flush();					
					TokensApis newAccessToken = clientResponse.getEntity(TokensApis.class);
					newAccessToken.setApiAccessToken(secretID);					
					newAccessToken = tokensApisRepository.saveAndFlush(newAccessToken);					
					return newAccessToken;
				}else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return token;
	}
}