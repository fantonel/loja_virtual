package br.com.fantonel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.model.Usuario;
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/recuperarSenha")
	public ResponseEntity<?> recuperarSenha(@RequestBody @Valid Usuario usuario){
		var usuLogin = usuarioService.findByLogin(usuario.getLogin());
		if (!usuLogin.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado! Verifique se está correto.");
		}
		//		
		return ResponseEntity.status(HttpStatus.OK).body("A nova senha foi enviada para o seu email. Por favor verifique!");		
	}
}