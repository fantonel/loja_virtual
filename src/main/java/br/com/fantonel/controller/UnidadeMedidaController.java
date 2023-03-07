package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
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
import br.com.fantonel.model.UnidadeMedida;
import br.com.fantonel.service.UnidadeMedidaService;

@RestController
@RequestMapping("api/v1/unidadesdemedida")
public class UnidadeMedidaController {
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	public UnidadeMedidaController() {}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<UnidadeMedida> salvar(@RequestBody @Valid UnidadeMedida unidadeMedida) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (unidadeMedidaService.existsBySigla(unidadeMedida.getSigla()))
			throw new LojaVirtualExceptions("Já existe um unidade com essa sigla");
		
		if (unidadeMedidaService.existsByDescricao(unidadeMedida.getDescricao()))
			throw new LojaVirtualExceptions("Já existe um unidade com essa descrição");		
		
		if (unidadeMedida.getId() == null)
			unidadeMedidaService.save(unidadeMedida);
		else {
			var entity = unidadeMedidaService.findById(unidadeMedida.getId());
			BeanUtils.copyProperties(entity, unidadeMedida);
			unidadeMedidaService.save(unidadeMedida);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(unidadeMedida);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!unidadeMedidaService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "A unidade não foi encontrada, para ser excluída!");
		
		unidadeMedidaService.deleteById(id);				
		return ResponseEntity.status(HttpStatus.OK).body("Unidade de Medida excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<UnidadeMedida>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(unidadeMedidaService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var unidadeMedida = unidadeMedidaService.findById(id);
		if (unidadeMedida.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(unidadeMedida);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Unidade de Medida não encontrada");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorSigla/{sigla}")
	public ResponseEntity<?> buscarPorSigla(@PathVariable String sigla) throws LojaVirtualExceptions{
		var entity = unidadeMedidaService.buscarPorSigla(sigla);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Unidade de Medida não encontrada");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorDescricao/{descricao}")
	public ResponseEntity<?> buscarPorDescricao(@PathVariable String descricao) throws LojaVirtualExceptions{
		var entity = unidadeMedidaService.buscarPorDescricao(descricao);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Unidade de Medida não encontrada");
	}
}