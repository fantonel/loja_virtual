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
import br.com.fantonel.model.FormaPagamento;
import br.com.fantonel.service.FormaPagamentoService;

@RestController
@RequestMapping("api/v1/formaspagamento/")
public class FormaPagamentoController {
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	public FormaPagamentoController() {	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<FormaPagamento> salvar(@RequestBody @Valid FormaPagamento formaPagamento) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (formaPagamentoService.existsByFormaPagamento(formaPagamento.getFormaPagamento()))
			throw new LojaVirtualExceptions("Já existe um cadastro para a forma de pagamento");
		
		if (formaPagamento.getId() == null)
			formaPagamentoService.save(formaPagamento);
		else {
			var entity = formaPagamentoService.findById(formaPagamento.getId());
			BeanUtils.copyProperties(entity, formaPagamento);
			formaPagamentoService.save(formaPagamento);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(formaPagamento);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!formaPagamentoService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O objeto não foi encontrado, para ser excluído!");
		
		formaPagamentoService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Forma de Pagamento excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<FormaPagamento>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var formaPagamento = formaPagamentoService.findById(id);
		if (formaPagamento.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(formaPagamento);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Forma de Pagamento não encontrada");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorFormaPagamento/{formaPagamento}")
	public ResponseEntity<?> buscarPorFormaPagamento(@PathVariable String formaPagamento) throws LojaVirtualExceptions{
		var entity = formaPagamentoService.findByFormaPagamento(formaPagamento);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Forma de Pagamento não encontrada");
	}
}