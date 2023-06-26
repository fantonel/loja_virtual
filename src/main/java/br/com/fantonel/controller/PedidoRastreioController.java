package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
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
import br.com.fantonel.model.PedidoRastreio;
import br.com.fantonel.service.PedidoRastreioService;
import br.com.fantonel.service.PedidoService;

@RestController
@RequestMapping("api/v1/pedidorastreio")
public class PedidoRastreioController {
	@Autowired
	private PedidoRastreioService pedidoRastreioService;
	@Autowired
	private PedidoService pedidoService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/salvar")
	public ResponseEntity<PedidoRastreio> salvar(@RequestBody @Valid PedidoRastreio pedidoRastreio) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (pedidoRastreio.getPedido() == null || pedidoRastreio.getPedido().getId() == null)
			throw new LojaVirtualExceptions("Informe o pedido!");
		
		var pedido = pedidoService.findById(pedidoRastreio.getPedido().getId()).orElseThrow(() -> new LojaVirtualExceptions("A Nota Fiscal de Compra informada não foi encontrada. Verifique!"));
		pedidoRastreio.setPedido(pedido);		
		
		pedidoRastreioService.save(pedidoRastreio);		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoRastreio);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!pedidoRastreioService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O objeto não foi encontrado, para ser excluído!");
		
		pedidoRastreioService.deleteById(id);				
		return ResponseEntity.status(HttpStatus.OK).body("Item de rastreio excluído com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/rastrearPedidoID/{pedidoID}")
	public ResponseEntity<List<PedidoRastreio>> listarTodos(@PathVariable UUID pedidoID){		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoRastreioService.findByPedidoID(pedidoID));		
	}
}