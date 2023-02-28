package br.com.fantonel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fantonel.model.Cep;

@Service
public class CepService {
	
	public CepService() {}	
	
	public Cep consultaCep(String cep){
		var cepAux = cep.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");		
		Cep entity = new RestTemplate().getForEntity("https://viacep.com.br/ws/"+cepAux+"/json/", Cep.class).getBody();
		return entity;		
	}
}