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
import br.com.fantonel.model.Produto;
import br.com.fantonel.model.ProdutoConfiguracao;
import br.com.fantonel.service.CategoriaService;
import br.com.fantonel.service.MarcaService;
import br.com.fantonel.service.ProdutoService;
import br.com.fantonel.service.UnidadeMedidaService;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private MarcaService marcaService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	public ProdutoController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<Produto> salvar(@RequestBody @Valid Produto produto) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		//Marca do produto
		if (produto.getMarca() == null)
			throw new LojaVirtualExceptions("Informe a marca do produto!");
		var marca = marcaService.findById(produto.getMarca().getId());
		if (!marca.isPresent())
			throw new LojaVirtualExceptions("Informe a marca!");
		produto.setMarca(marca.get());
		
		//Se novo produto, verifica se já existe na base, produto de mesmo nome e marca.
		//Ou se existe algum outro produto com a descrição para a qual se deseja alterar o produto.
		if (produto.getId() == null && produtoService.existePorNomeMarca(produto.getNome(), produto.getMarca().getId()))
			throw new LojaVirtualExceptions("Já existe um produto com esse nome, e com essa marca!");
		else if (produtoService.existeOutroProdutoPorNomeMarca(produto.getId(), produto.getNome(), produto.getMarca().getId()))
			throw new LojaVirtualExceptions("Já existe um outro produto com esse nome, e com essa marca!");
	
		//Categoria do Produto
		if (produto.getProdutoCategorias() == null)
			throw new LojaVirtualExceptions("Informe ao menos uma categoria!");		
		for (Categoria categoria : produto.getProdutoCategorias()) {
			if (categoria.getId() == null)
				throw new LojaVirtualExceptions("Informe uma categoria.");
			
			var optCategoria = categoriaService.findById(categoria.getId());
			if (!optCategoria.isPresent())
				throw new LojaVirtualExceptions("A categoria "+categoria.getCategoria()+" não é valida. Verifique!");
			categoria = optCategoria.get();
		}		
		//Configuração do Produto
		if (produto.getProdutoConfiguracoes() != null) {
			for (ProdutoConfiguracao prodConfig : produto.getProdutoConfiguracoes()){
				prodConfig.setProduto(produto);
				if (prodConfig.getUnidadeMedida() == null || prodConfig.getUnidadeMedida().getId() == null)
					throw new LojaVirtualExceptions("Informe a unidade de medida, de cada configuração do produto!");
				var optUniMed = unidadeMedidaService.findById(prodConfig.getUnidadeMedida().getId());
				if (!optUniMed.isPresent())
					throw new LojaVirtualExceptions("Verifique a unidade de medida, de cada configuração do produto!");
				prodConfig.setUnidadeMedida(optUniMed.get());
			}
		}
		//		
		if (produto.getId() == null)
			produtoService.save(produto);
		else {
			var entity = produtoService.findById(produto.getId());
			BeanUtils.copyProperties(entity, produto);
			produtoService.save(produto);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!produtoService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O produto não foi encontrado, para ser excluído!");
		
		//Verificações necessárias, a medida que o projeto avanã.
		//Não permitir se possui avaliação;
		//Não permitir se possui nota fiscal de compra;
		//Não permitir se possui nota fiscal de venda.
		
		produtoService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Produto excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Produto>> listarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var produto = produtoService.findById(id);
		if (produto.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(produto);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Produto não encontrado");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorCategoria/{id}")
	public ResponseEntity<?> listarPorCategoria(@PathVariable UUID id) throws LojaVirtualExceptions{
		List<Produto> listaPorCategoria = produtoService.listarPorCategoria(id);
		if (listaPorCategoria == null || listaPorCategoria.size() == 0)
			return ResponseEntity.status(HttpStatus.OK).body("Nenhum produto localizado para a categoria informada.");
		
		return ResponseEntity.status(HttpStatus.OK).body(listaPorCategoria);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorNome/{nome}")
	public ResponseEntity<?> listarPorNome(@PathVariable String nome) throws LojaVirtualExceptions{
		List<Produto> listaPorNome = produtoService.listarPorNome(nome);
		if (listaPorNome == null || listaPorNome.size() == 0)
			return ResponseEntity.status(HttpStatus.OK).body("Nenhum produto localizado com o nome informado.");
		
		return ResponseEntity.status(HttpStatus.OK).body(listaPorNome);
	}
}