package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.excepts.LojaVirtualExceptions;
import br.com.fantonel.model.PedidoProdutos;
import br.com.fantonel.service.PedidoProdutosService;
import br.com.fantonel.service.PedidoService;
import br.com.fantonel.service.ProdutoService;

@RestController
@RequestMapping("api/v1/pedidoprodutos/")
public class PedidoProdutosController {
	@Autowired
	private PedidoProdutosService pedidoProdutosService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProdutoService produtoService;
	
	public PedidoProdutosController(){}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/salvar")
	public ResponseEntity<PedidoProdutos> salvar(@RequestBody @Valid PedidoProdutos pedidoProdutos) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (pedidoProdutos == null)
			throw new LojaVirtualExceptions("Verifique as informações do produto do pedido!");
		if (pedidoProdutos.getPedido() == null || pedidoProdutos.getPedido().getId() == null)
			throw new LojaVirtualExceptions("O pedido informado é inválido. Verifique!");
		if (pedidoProdutos.getProduto() == null || pedidoProdutos.getProduto().getId() == null)
			throw new LojaVirtualExceptions("O produto informado é inválido. Verifique!");
		if (pedidoProdutos.getQtde() == null || pedidoProdutos.getQtde().compareTo(BigDecimal.ZERO) == 0 || pedidoProdutos.getQtde().compareTo(BigDecimal.ZERO) == -1)
			throw new LojaVirtualExceptions("A quantidade deve ser maior que 0 (zero). Verifique!");
		if (pedidoProdutos.getValor() == null || pedidoProdutos.getValor().compareTo(BigDecimal.ZERO) == 0 || pedidoProdutos.getValor().compareTo(BigDecimal.ZERO) == -1)
			throw new LojaVirtualExceptions("O valor deve ser maior que 0 (zero). Verifique!");
		//
		pedidoProdutos.setValorFinal(pedidoProdutos.getValor().multiply(pedidoProdutos.getQtde()));
		var pedido  = pedidoService.findById(pedidoProdutos.getPedido().getId()).orElseThrow(() -> new LojaVirtualExceptions("Número de pedido inválido. Verifique!"));
		pedidoProdutos.setPedido(pedido);
		var produto = produtoService.findById(pedidoProdutos.getProduto().getId()).orElseThrow(() -> new LojaVirtualExceptions("Produto inválido. Verifique!"));
		pedidoProdutos.setProduto(produto);
		if (pedidoProdutosService.existsByPedidoIdAndProdutoId(pedido.getId(), produto.getId())) {
			throw new LojaVirtualExceptions("O produto ["+produto.getNome()+"] já foi incluído anteriormente no pedido. Verifique!");
		}
		if (pedidoProdutos.getValorFinal().compareTo(BigDecimal.ZERO) == -1)
			throw new LojaVirtualExceptions("O valor final do produto ["+produto.getNome()+"] não pode ser menor que 0 (Zero). Verifique!");
		//
		//Itens do pedido, existe uma trigger na tabela, para atualização dos valores, descontos, etc no pedido. 
		if (pedidoProdutos.getId() == null) {
			pedidoProdutosService.save(pedidoProdutos);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedidoProdutos);
	}
}