package br.com.fantonel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.excepts.LojaVirtualExceptions;
import br.com.fantonel.model.Acesso;
import br.com.fantonel.service.AcessoService;

@RestController
@RequestMapping("/api/v1/acessos")
public class AcessoController {
	@Autowired
	private AcessoService acessoService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping(value = "/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso bean) throws LojaVirtualExceptions{
		if (bean == null) {
			throw new LojaVirtualExceptions(HttpStatus.OK, "Objeto inválido");
		}else if (StringUtils.isBlank(bean.getDescricao())) {
			throw new LojaVirtualExceptions(HttpStatus.OK, "Informe a descrição");
		}else if (bean.getDescricao().trim().length() <= 3) {
			throw new LojaVirtualExceptions(HttpStatus.OK, "Descrição deve ter ao menos 3 caracteres!");
		}else if (acessoService.existsByDescricao(bean.getDescricao())) {
			throw new LojaVirtualExceptions(HttpStatus.OK, "Já existe um acesso com a descrição!");
		}
		Acesso entity;
		if (bean.getId() == null) {		
			entity = acessoService.save(bean);
			return ResponseEntity.status(HttpStatus.CREATED).body(entity);
		}else {
			entity = acessoService.save(bean);
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/excluirAcesso/{id}")
	public ResponseEntity<?> excluirAcesso(@PathVariable UUID id) throws LojaVirtualExceptions{
		if (acessoService.existsById(id)) {			
			if (acessoService.existeUsuarioVinculado(id))
				throw new LojaVirtualExceptions(HttpStatus.OK, "Exclusão não permitida. Há usuário(s) vinculados ao tipo de acesso!");
		
			acessoService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
		}		
		throw new LojaVirtualExceptions("Usuário não encontrado!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		Optional<Acesso> entity = acessoService.findById(id);
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions("Usuário não encontrado!");		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/listarTodos")
	public ResponseEntity<List<Acesso>> listarTodos(){
		List<Acesso> list = acessoService.getAll();
		if (!list.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(list);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/buscarPorDescricao/{descricao}")
	public ResponseEntity<?> buscarPorDescricao(@PathVariable String descricao) throws LojaVirtualExceptions{
		Optional<Acesso> entity = acessoService.buscarPorDescricao(descricao);
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		else
			throw new LojaVirtualExceptions("Usuário com descrição "+descricao+" não encontrado!");
	}
}