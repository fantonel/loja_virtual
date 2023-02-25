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
import br.com.fantonel.model.PessoaJuridica;
import br.com.fantonel.service.PessoaJuridicaService;

@RestController
@RequestMapping("api/v1/pessoasjuridicas")
public class PessoaJuridicaController {
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	public PessoaJuridicaController() {}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<PessoaJuridica> salvar(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (pessoaJuridica.getId() == null && pessoaJuridicaService.existsByCnpj(pessoaJuridica.getCnpj()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Já existe Empresa cadastrada, com o CNPJ informado!");		
		
		if (pessoaJuridica.getId() == null && pessoaJuridicaService.existsByEmail(pessoaJuridica.getEmailPrincipal()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Já existe Empresa cadastrada, com o Email informado!");
		
		if (pessoaJuridica.getId() == null)
			pessoaJuridicaService.save(pessoaJuridica);
		else {
			var entity = pessoaJuridicaService.findById(pessoaJuridica.getId());
			BeanUtils.copyProperties(entity, pessoaJuridica);
			pessoaJuridicaService.save(pessoaJuridica);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridica);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!pessoaJuridicaService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O registro não foi encontrado, para ser excluído!");
		
		pessoaJuridicaService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Registro excluído com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<PessoaJuridica>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridicaService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarExcluidas")
	public ResponseEntity<List<PessoaJuridica>> listarExcluidas(){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridicaService.listarExcluidas());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var pessoaJuridica = pessoaJuridicaService.findById(id);
		if (pessoaJuridica.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridica);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado.");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorCnpj/{cnpj}")
	public ResponseEntity<?> buscarPorCnpj(@PathVariable String cnpj) throws LojaVirtualExceptions{
		var entity = pessoaJuridicaService.findByCnpj(cnpj);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorEmail/{email}")
	public ResponseEntity<?> buscarPorEmail(@PathVariable String email) throws LojaVirtualExceptions{
		var entity = pessoaJuridicaService.findByEmailPrincipal(email);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado");
	}
}