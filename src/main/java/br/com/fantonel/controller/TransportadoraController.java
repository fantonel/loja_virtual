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
import br.com.fantonel.model.Transportadora;
import br.com.fantonel.service.PedidoService;
import br.com.fantonel.service.PessoaJuridicaService;
import br.com.fantonel.service.TransportadoraService;

@RestController
@RequestMapping("api/v1/transportadora/")
public class TransportadoraController {
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;	
	@Autowired
	private TransportadoraService transportadoraService;

	public TransportadoraController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<Transportadora> salvar(@RequestBody @Valid Transportadora transportadora) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (transportadora.getPessoaJuridica() == null || transportadora.getPessoaJuridica().getId() == null)
			throw new LojaVirtualExceptions("Empresa vinculada a transportadora inválida. Verifique!");
		var empresa = pessoaJuridicaService.findById(transportadora.getPessoaJuridica().getId()).orElseThrow(() -> new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"A Empresa vinculada a Transportadora é inválida. Verifique!"));
		transportadora.setPessoaJuridica(empresa);
		//
		if (transportadora.getId() == null) {
			transportadora.setAtiva(true);
			transportadoraService.save(transportadora);
		}else {
			var entity = transportadoraService.findById(transportadora.getId()).orElseThrow(() -> new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"A transportadora informada não foi encontrada. Verifique!"));
			//Pedidos são vinculados a TRANSPORTADORA, que por sua vez é vinculada a uma PESSOA JURÍDICA.
			//Se o usuário alterou a PESSOA JURÍDICA da TRANSPORTADORA, preciso ver se tem pedidos vinculados a ela.
			//Se sim, não permito a alteração da PESSOA JURÍDICA original, pois afetaria os pedidos vinculados a TRANSPORTADORA.
			if (empresa.getId() != entity.getPessoaJuridica().getId()) {
				boolean exists = pedidoService.existsPedidoTransportadora(entity.getId());
				if (exists) {
					throw new LojaVirtualExceptions("A empresa vinculada a transportadora não pode ser trocada,pois existem Pedidos dependentes!");
				}
			}			
			BeanUtils.copyProperties(entity, transportadora);
			transportadoraService.save(transportadora);
		}		
		return ResponseEntity.status(HttpStatus.OK).body(transportadora);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!transportadoraService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "A Transportadora não foi encontrada, para ser excluída!");
		//Se há pedidos vinculados a transportadora
		boolean exists = pedidoService.existsPedidoTransportadora(id);
		if (exists) {
			throw new LojaVirtualExceptions("A Transportadora não pode ser excluída. Há pedidos vinculados a ela!");
		}		
		transportadoraService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("A Transportadora foi excluída com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<Transportadora>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(transportadoraService.findAll());		
	}
}