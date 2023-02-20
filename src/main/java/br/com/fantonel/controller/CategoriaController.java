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
import br.com.fantonel.model.Categoria;
import br.com.fantonel.service.CategoriaService;

@RestController
@RequestMapping("api/v1/categorias/")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	
	public CategoriaController() {}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (categoriaService.existByCategoria(categoria.getCategoria()))
			throw new LojaVirtualExceptions("Já existe um cadastro para essa categoria");		
		
		if (categoria.getId() == null)
			categoriaService.save(categoria);
		else {
			var entity = categoriaService.findById(categoria.getId());
			BeanUtils.copyProperties(entity, categoria);
			categoriaService.save(categoria);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!categoriaService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O objeto não foi encontrado, para ser excluído!");
		
		categoriaService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Categoria excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<Categoria>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var categoria = categoriaService.findById(id);
		if (categoria.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Categoria não encontrada");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorCategoria/{categoria}")
	public ResponseEntity<?> buscarPorCategoria(@PathVariable String categoria) throws LojaVirtualExceptions{
		var entity = categoriaService.findByCategoria(categoria);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Categoria não encontrada");
	}

}