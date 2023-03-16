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
import br.com.fantonel.model.NotaFiscalCompra;
import br.com.fantonel.service.NotaFiscalCompraService;
import br.com.fantonel.service.PessoaJuridicaService;

@RestController
@RequestMapping("api/v1/notafiscalcompra")
public class NotaFiscalController {
	@Autowired
	private NotaFiscalCompraService notaFiscalCompraService;
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	public NotaFiscalController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<NotaFiscalCompra> salvar(@RequestBody @Valid NotaFiscalCompra nfc) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (nfc.getPessoaJuridica() == null || nfc.getPessoaJuridica().getId() == null)
			throw new LojaVirtualExceptions("Informe o fornecedor!");
		else {
			var forn = pessoaJuridicaService.findById(nfc.getPessoaJuridica().getId());
			if (!forn.isPresent())
				throw new LojaVirtualExceptions("Informe um fornecedor válido!");
			nfc.setPessoaJuridica(forn.get());
		}		
		if (nfc.getId() == null && notaFiscalCompraService.existsByNumero(nfc.getNumero()))
			throw new LojaVirtualExceptions("Já existe uma nota fiscal, com o número informado!");
		if (nfc.getDesconto().compareTo(nfc.getValor()) > 0)
			throw new LojaVirtualExceptions("Valor de desconto não pode ser superior ao valor!");
		if (nfc.getDataCompra() == null)
			throw new LojaVirtualExceptions("Informe a data da compra!");
		if (nfc.getDataEmissao() == null)
			throw new LojaVirtualExceptions("Informe a data de emissão!");
		if (nfc.getDataCompra().after(nfc.getDataEmissao()))
			throw new LojaVirtualExceptions("A data da compra não pode ser posterior a de emissão!");
		
		if (nfc.getId() == null)
			notaFiscalCompraService.save(nfc);
		else {
			var entity = notaFiscalCompraService.findById(nfc.getId());
			BeanUtils.copyProperties(entity, nfc);
			notaFiscalCompraService.save(nfc);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(nfc);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!notaFiscalCompraService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Nota Fiscal de Compra não encontrada, para ser excluída!");
		
		notaFiscalCompraService.deleteById(id);
				
		return ResponseEntity.status(HttpStatus.OK).body("Nota Fiscal de Compra excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<NotaFiscalCompra>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(notaFiscalCompraService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var entity = notaFiscalCompraService.findById(id);
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"A Nota Fiscal de Compra não foi encontrada!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarPorFornecedor/{fornecedorId}")
	public ResponseEntity<List<NotaFiscalCompra>> listarPorFornecedor(@PathVariable UUID fornecedorId) throws LojaVirtualExceptions{
		List<NotaFiscalCompra> list = notaFiscalCompraService.findByPessoaJuridica(fornecedorId);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}