package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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
import br.com.fantonel.model.ProdutoAvaliacao;
import br.com.fantonel.service.PessoaFisicaService;
import br.com.fantonel.service.ProdutoAvaliacaoService;
import br.com.fantonel.service.ProdutoService;

@RestController
@RequestMapping("api/v1/produtoavaliacao")
public class ProdutoAvaliacaoController {
	@Autowired
	private ProdutoAvaliacaoService produtoAvaliacaoService;
	@Autowired 
	private ProdutoService produtoService;
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	public ProdutoAvaliacaoController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/salvar")
	public ResponseEntity<ProdutoAvaliacao> salvar(@RequestBody @Valid ProdutoAvaliacao produtoAvaliacao) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (produtoAvaliacao.getPessoaFisica() == null || produtoAvaliacao.getPessoaFisica().getId() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Informe a pessoa que está avaliando o produto!");
		
		if (produtoAvaliacao.getProduto() == null || produtoAvaliacao.getProduto().getId() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Informe o produto a ser avaliado!");
		
		if (produtoAvaliacao.getId() == null) {
			var produto = produtoService.findById(produtoAvaliacao.getProduto().getId());
			produtoAvaliacao.setProduto(produto.get());
			var pessoaFisica = pessoaFisicaService.findById(produtoAvaliacao.getPessoaFisica().getId());
			produtoAvaliacao.setPessoaFisica(pessoaFisica.get());
			
			produtoAvaliacao.setDataHora(new Date());
			produtoAvaliacaoService.save(produtoAvaliacao);
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoAvaliacao);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<ProdutoAvaliacao>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoAvaliacaoService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorProduto/{id}")
	public ResponseEntity<?> listarPorProduto(@PathVariable UUID id) throws LojaVirtualExceptions{
		List<ProdutoAvaliacao> listaPorCategoria = produtoAvaliacaoService.findByProduto(id);
		if (listaPorCategoria == null || listaPorCategoria.size() == 0)
			return ResponseEntity.status(HttpStatus.OK).body("Nenhum produto localizado para a categoria informada.");
		
		return ResponseEntity.status(HttpStatus.OK).body(listaPorCategoria);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!produtoAvaliacaoService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "A avaliação não foi encontrada, para ser excluído!");
		
		produtoAvaliacaoService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("A avaliação do Produto excluída com sucesso!");
	}
}