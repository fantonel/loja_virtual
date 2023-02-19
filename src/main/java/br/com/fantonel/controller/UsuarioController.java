package br.com.fantonel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/findByLogin/{login}")
	public ResponseEntity<?> findByLogin(@PathVariable String login){
		var usuario = usuarioService.findByLogin(login);
		if (usuario.isPresent())
			return ResponseEntity.status(HttpStatus.FOUND).body(usuario.get());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	}	
}