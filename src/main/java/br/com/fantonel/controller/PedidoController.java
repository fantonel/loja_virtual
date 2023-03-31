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
import br.com.fantonel.model.Pedido;
import br.com.fantonel.service.CupomDescontoService;
import br.com.fantonel.service.EnderecoService;
import br.com.fantonel.service.PedidoService;
import br.com.fantonel.service.PessoaFisicaService;
import br.com.fantonel.service.PessoaJuridicaService;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {
	@Autowired	
	private PedidoService pedidoService;
	@Autowired	
	private PessoaFisicaService pessoaFisicaService;
	@Autowired	
	private PessoaJuridicaService pessoaJuridicaService;	
	@Autowired	
	private EnderecoService enderecoService;
	@Autowired	
	private CupomDescontoService cupoDescontoService;

	public PedidoController() {
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/salvar")
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		//Pessoa / Empresa para vincular ao pedido.
		if (pedido.getPessoaFisica() == null && pedido.getPessaJuridica() == null)
			throw new LojaVirtualExceptions("Não foi encontrado o solicitante do pedido. Verifique!");
		if (pedido.getPessoaFisica().getId() == null && pedido.getPessaJuridica().getId() == null)
			throw new LojaVirtualExceptions("Não foi encontrado o solicitante do pedido. Verifique!");		
		if (pedido.getPessoaFisica() != null && pedido.getPessoaFisica().getId() != null) {
			var pessoaFisica = pessoaFisicaService.findById(pedido.getPessoaFisica().getId()).orElseThrow(() -> new LojaVirtualExceptions("A pessoa a ser vinculada ao pedido, não foi encontrada!"));
			pedido.setPessoaFisica(pessoaFisica);
		}else if (pedido.getPessaJuridica() != null && pedido.getPessaJuridica().getId() != null) {
			var pessoaJuridica = pessoaJuridicaService.findById(pedido.getPessaJuridica().getId()).orElseThrow(() -> new LojaVirtualExceptions("A empresa a ser vinculada ao pedido, não foi encontrada!"));
			pedido.setPessaJuridica(pessoaJuridica);
		}
		//Endereço de Entrega / Cobrança
		if (pedido.getEnderecoCobranca() == null || pedido.getEnderecoEntrega() == null)
			throw new LojaVirtualExceptions("Informe o endereço de Entrega e de Cobrança!");
		if (pedido.getEnderecoCobranca().getId() == null || pedido.getEnderecoEntrega().getId() == null)
			throw new LojaVirtualExceptions("Informe o endereço de Entrega e de Cobrança!");
		if (pedido.getPessoaFisica().getId() != null) {
			var enderecoCobranca = enderecoService.findByEnderecoCobrancaPessoaFisica(pedido.getEnderecoCobranca().getId(), pedido.getPessoaFisica().getId()).orElseThrow(() -> new LojaVirtualExceptions("O endereço de cobrança informado não é valido!"));
			var enderecoEntrega  = enderecoService.findByEnderecoEntregaPessoaFisica(pedido.getEnderecoEntrega().getId(), pedido.getPessoaFisica().getId()).orElseThrow(() -> new LojaVirtualExceptions("O endereço de entrega informado não é valido!"));
			pedido.setEnderecoCobranca(enderecoCobranca);
			pedido.setEnderecoEntrega(enderecoEntrega);
		}else {
			var enderecoCobranca = enderecoService.findByEnderecoCobrancaPessoaJuridica(pedido.getEnderecoCobranca().getId(), pedido.getPessaJuridica().getId()).orElseThrow(() -> new LojaVirtualExceptions("O endereço de cobrança informado não é valido!"));
			var enderecoEntrega  = enderecoService.findByEnderecoEntregaPessoaJuridica(pedido.getEnderecoEntrega().getId(), pedido.getPessaJuridica().getId()).orElseThrow(() -> new LojaVirtualExceptions("O endereço de entrega informado não é valido!"));
			pedido.setEnderecoCobranca(enderecoCobranca);
			pedido.setEnderecoEntrega(enderecoEntrega);
		}		
		//Cupom de Desconto
		pedido.setDesconto(BigDecimal.ZERO);
		if (pedido.getCupomDesconto() != null && pedido.getCupomDesconto().getId() != null) {
			var cupomDesconto = cupoDescontoService.findById(pedido.getCupomDesconto().getId()).orElseThrow(() -> new LojaVirtualExceptions("Cupom de Desconto Inválido!"));
			pedido.setCupomDesconto(cupomDesconto);
			//Se cupom foi setado com valor maior que 0
			//Ou se aplicará o % sobre o total
			if (cupomDesconto.getValor() != null && cupomDesconto.getValor().compareTo(BigDecimal.ZERO) == -1)			
				pedido.setDesconto(cupomDesconto.getValor());
			else if (cupomDesconto.getPercentual() != null && cupomDesconto.getPercentual().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal valor = pedido.getValor();
				BigDecimal perce = cupomDesconto.getPercentual().divide(new BigDecimal(100.00));
				BigDecimal desconto = valor.multiply(perce);
				pedido.setDesconto(desconto);
			}				
		}		
		BigDecimal valorComDesconto   = pedido.getValor().subtract(pedido.getDesconto());
		pedido.setValorComDescto(valorComDesconto);
		BigDecimal valorFinalComFrete = valorComDesconto.add(pedido.getFrete());
		pedido.setValorComFrete(valorFinalComFrete);
		
		if (pedido.getId() == null) {
			pedido.setCodigoRastreio("");
			pedidoService.save(pedido);
		} else {
			var entity = pedidoService.findById(pedido.getId());
			BeanUtils.copyProperties(entity, pedido);
			pedidoService.save(pedido);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedido);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!pedidoService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O pedido não foi encontrado, para ser excluído!");
		
		pedidoService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Pedido excluído com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodos")
	public ResponseEntity<List<Pedido>> listarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorClienteID/{id}")
	public ResponseEntity<List<Pedido>> listarPorClienteID(@PathVariable UUID id) throws LojaVirtualExceptions{
		if (pessoaFisicaService.existsById(id))
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByPessoaFisica(id));
		if (pessoaJuridicaService.existsById(id))
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByPessoaJuridica(id));
		else
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Nenhum pedido foi encontrado!");		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorClienteCpf/{cpf}")
	public ResponseEntity<List<Pedido>> listarPorClienteCpf(@PathVariable String cpf) throws LojaVirtualExceptions{
		if (pessoaFisicaService.existsByCpf(cpf))
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByPessoaFisicaCpf(cpf));
		else
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Nenhum pedido foi encontrado!");		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/listarPorClienteCnpj/{cnpj}")
	public ResponseEntity<List<Pedido>> listarPorClienteCnpj(@PathVariable String cnpj) throws LojaVirtualExceptions{
		if (pessoaJuridicaService.existsByCnpj(cnpj))
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByPessoaJuridica(cnpj));
		else
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Nenhum pedido foi encontrado!");		
	}
}