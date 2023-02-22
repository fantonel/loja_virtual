package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import br.com.fantonel.model.CupomDesconto;
import br.com.fantonel.service.CupomDescontoService;

@RestController
@RequestMapping("api/v1/cuponsdedesconto/")
public class CupomDescontoController {
	@Autowired
	private CupomDescontoService cupomDescontoService;
	
	public CupomDescontoController() {	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<CupomDesconto> salvar(@RequestBody @Valid CupomDesconto cupomDesconto) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (cupomDescontoService.existsByCodigo(cupomDesconto.getCodigo()))
			throw new LojaVirtualExceptions("Já existe um cupom cadastrado, com o código informado");
		
		if (cupomDesconto.getPercentual() == null && cupomDesconto.getValor() == null)
			throw new LojaVirtualExceptions("Informe o percentual ou o valor de desconto");
		
		if (cupomDesconto.getPercentual() == BigDecimal.ZERO && cupomDesconto.getValor() == BigDecimal.ZERO)
			throw new LojaVirtualExceptions("Valor do percentual ou do desconto, deve ser maior que 0 (Zero)");
		
		if (cupomDesconto.getPercentual() != null && cupomDesconto.getPercentual().compareTo(BigDecimal.ZERO) == -1)
			throw new LojaVirtualExceptions("O percentual de desconto não pode ser menor que 0 (Zero)");
		
		if (cupomDesconto.getValor() != null && cupomDesconto.getValor().compareTo(BigDecimal.ZERO) == -1)
			throw new LojaVirtualExceptions("O valor de desconto não pode ser menor que 0 (Zero)");
		
		if (cupomDesconto.getValidadeInicial() == null)
			throw new LojaVirtualExceptions("Informe a data em que o cupom tem início");		
		
		if (cupomDesconto.getValidadeFinal() != null && cupomDesconto.getValidadeFinal().before(cupomDesconto.getValidadeInicial()) )
			throw new LojaVirtualExceptions("A data de término do cupom, não pode ser anterior a data de início");	
		
		if (cupomDesconto.getId() == null)
			cupomDescontoService.save(cupomDesconto);
		else {
			var entity = cupomDescontoService.findById(cupomDesconto.getId());
			BeanUtils.copyProperties(entity, cupomDesconto);
			cupomDescontoService.save(cupomDesconto);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(cupomDesconto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!cupomDescontoService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O Cupom de Desconto não foi encontrado, para ser excluído!");
		
		cupomDescontoService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Cupom de Desconto excluído com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodos")
	public ResponseEntity<List<CupomDesconto>> listarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(cupomDescontoService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var cupomDesconto = cupomDescontoService.findById(id);
		if (cupomDesconto.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(cupomDesconto);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Cupom de Desconto não encontrado");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorCodigo/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) throws LojaVirtualExceptions{
		var entity = cupomDescontoService.findByCodigo(codigo);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Cupom de Desconto não encontrado");
	}
}