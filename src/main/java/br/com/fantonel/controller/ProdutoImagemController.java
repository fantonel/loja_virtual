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
import br.com.fantonel.model.ProdutoImagem;
import br.com.fantonel.service.ProdutoImagemService;
import br.com.fantonel.service.ProdutoService;
import br.com.fantonel.util.ImageConverter;

@RestController
@RequestMapping("api/v1/produtoimagens")
public class ProdutoImagemController {
	@Autowired
	private ProdutoImagemService produtoImagemService;
	@Autowired
	private ProdutoService produtoService;	

	public ProdutoImagemController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<ProdutoImagem> salvar(@RequestBody @Valid ProdutoImagem produtoImagem) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (produtoImagem.getProduto() == null || produtoImagem.getProduto().getId() == null)
			throw new LojaVirtualExceptions("Informe o produto, que terá a imagem vinculada!");
		
		var produto = produtoService.findById(produtoImagem.getProduto().getId());
		if(!produto.isPresent())
			throw new LojaVirtualExceptions("Informe o produto, que terá a imagem vinculada!");
		produtoImagem.setProduto(produto.get());
		
		if (produtoImagem.getImagemOriginal() == null || produtoImagem.getImagemOriginal().trim().length() == 0)
			throw new LojaVirtualExceptions("Informe a imagem que será vinculada ao produto!");
		
		produtoImagem.setImagemMiniatura(ImageConverter.toPngBase64Converter(produtoImagem.getImagemOriginal()));
		
		if (produtoImagem.getId() == null)
			produtoImagemService.save(produtoImagem);
		else {
			var entity = produtoImagemService.findById(produtoImagem.getId());
			BeanUtils.copyProperties(entity, produtoImagem);
			produtoImagemService.save(produtoImagem);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(produtoImagem);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!produtoImagemService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "A imagem do produto não foi encontrada, para ser excluído!");
		
		produtoImagemService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Imagem excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluirTodas/{produtoId}")
	public ResponseEntity<?> excluirTodas(@PathVariable UUID produtoId) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!produtoService.existsById(produtoId))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O produto não foi encontrado, para a exclusão das imagens!");
		
		produtoImagemService.excluirTodas(produtoId);
				
		return ResponseEntity.status(HttpStatus.OK).body("Imagens excluídas com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<ProdutoImagem>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoImagemService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorProduto/{id}")
	public ResponseEntity<List<ProdutoImagem>> listarPorProduto(@PathVariable UUID id){
		List<ProdutoImagem> list = produtoImagemService.findByProduto(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var produtoImagem = produtoImagemService.findById(id);
		if (produtoImagem.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(produtoImagem);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"A imagem do produto não foi encontrada!");
	}
}