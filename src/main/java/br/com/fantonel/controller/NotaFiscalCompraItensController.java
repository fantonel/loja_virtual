package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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
import br.com.fantonel.model.NotaFiscalCompraItens;
import br.com.fantonel.service.NotaFiscalCompraItensService;
import br.com.fantonel.service.NotaFiscalCompraService;
import br.com.fantonel.service.ProdutoService;

@RestController
@RequestMapping("api/v1/notafiscalcompraitens")
public class NotaFiscalCompraItensController {
	@Autowired
	private NotaFiscalCompraService notaFiscalCompraService;
	@Autowired
	private NotaFiscalCompraItensService notaFiscalCompraItensService;
	@Autowired
	private ProdutoService produtoService;

	public NotaFiscalCompraItensController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<NotaFiscalCompraItens> salvar(@RequestBody @Valid NotaFiscalCompraItens nfcit) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (nfcit.getNotaFiscalCompra() == null || nfcit.getNotaFiscalCompra().getId() == null)
			throw new LojaVirtualExceptions("Informe qual a Nota Fiscal de Compra, que terá o item vinculado!");
		var nfc = notaFiscalCompraService.findById(nfcit.getNotaFiscalCompra().getId()).orElseThrow(() -> new LojaVirtualExceptions("A Nota Fiscal de Compra informada não foi encontrada. Verifique!"));
		nfcit.setNotaFiscalCompra(nfc);
		
		if (nfcit.getProduto() == null || nfcit.getProduto().getId() == null)
			throw new LojaVirtualExceptions("Informe o produto, que será vinculado a Nota Fiscal de Compra!");
		var produto = produtoService.findById(nfcit.getProduto().getId()).orElseThrow(() -> new LojaVirtualExceptions("O Produto Informado não foi encontrado. Verifique!"));
		nfcit.setProduto(produto);
		
		if (notaFiscalCompraItensService.existsByNotaProduto(nfcit.getNotaFiscalCompra().getId(), produto.getId()))
			throw new LojaVirtualExceptions("O produto já foi incluído na nota!");
		
		if (nfcit.getId() == null) {
			nfcit.setDataHora(new Date());
			notaFiscalCompraItensService.save(nfcit);
		} else {
			var entity = notaFiscalCompraItensService.findById(nfcit.getId());
			BeanUtils.copyProperties(entity, nfcit);
			notaFiscalCompraItensService.save(nfcit);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(nfcit);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!notaFiscalCompraItensService.existsById(id))
			throw new LojaVirtualExceptions("O produto não foi encontrado, para ser excluído!");		
		notaFiscalCompraItensService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Item removido com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<NotaFiscalCompraItens>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(notaFiscalCompraItensService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var categoria = notaFiscalCompraItensService.findById(id);
		if (categoria.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Categoria não encontrada");
	}
	
	/*
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorCategoria/{categoria}")
	public ResponseEntity<?> buscarPorCategoria(@PathVariable String categoria) throws LojaVirtualExceptions{
		var entity = notaFiscalCompraItensService.findByCategoria(categoria);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Categoria não encontrada");
	}
	*/
}