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
import br.com.fantonel.model.Marca;
import br.com.fantonel.service.MarcaService;

@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {
	@Autowired
	private MarcaService marcaService;
	
	public MarcaController() {	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<Marca> salvar(@RequestBody @Valid Marca marca) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (marcaService.existsByMarca(marca.getMarca()))
			throw new LojaVirtualExceptions("Já existe um cadastro para essa marca");		
		
		if (marca.getId() == null)
			marcaService.save(marca);
		else {
			var entity = marcaService.findById(marca.getId());
			BeanUtils.copyProperties(entity, marca);
			marcaService.save(marca);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(marca);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!marcaService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O objeto não foi encontrado, para ser excluído!");
		
		marcaService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Marca excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<Marca>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(marcaService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var marca = marcaService.findById(id);
		if (marca.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(marca);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Marca não encontrada");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorMarca/{marca}")
	public ResponseEntity<?> buscarPorMarca(@PathVariable String marca) throws LojaVirtualExceptions{
		var entity = marcaService.findByMarca(marca);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Marca não encontrada");
	}
}