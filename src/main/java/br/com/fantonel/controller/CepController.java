package br.com.fantonel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.model.Cep;
import br.com.fantonel.service.CepService;

@RestController
@RequestMapping("api/v1/ceps")
public class CepController {
	@Autowired
	private CepService cepService;

	public CepController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/buscarPorCepWS/{cep}")
	public ResponseEntity<?> buscarPorCepWS(@PathVariable String cep){
		Cep entity = cepService.consultaCep(cep);
		if (entity != null)		
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cep não encontrado");
	}
	
	public Cep buscarPorCep(String cep){
		return cepService.consultaCep(cep);
	}
}