package br.com.fantonel.controller;

import java.io.IOException;
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

import br.com.fantonel.dto.InsereFreteFromDto;
import br.com.fantonel.dto.InsereFreteOptionsDto;
import br.com.fantonel.dto.InsereFreteProductsDto;
import br.com.fantonel.dto.InsereFreteToDto;
import br.com.fantonel.dto.InvoiceDto;
import br.com.fantonel.dto.MelhorEnvioCompraFreteOrdersDto;
import br.com.fantonel.dto.MelhorEnvioInsereFreteRequestDto;
import br.com.fantonel.dto.MelhorEnvioInsereFreteResponseDto;
import br.com.fantonel.dto.MelhorEnvioPurchaseResponseDto;
import br.com.fantonel.dto.TagsDto;
import br.com.fantonel.dto.VolumesDto;
import br.com.fantonel.excepts.LojaVirtualExceptions;
import br.com.fantonel.model.MelhorEnvio;
import br.com.fantonel.model.Pedido;
import br.com.fantonel.model.PedidoProdutos;
import br.com.fantonel.model.Produto;
import br.com.fantonel.model.ProdutoConfiguracao;
import br.com.fantonel.service.CupomDescontoService;
import br.com.fantonel.service.EnderecoService;
import br.com.fantonel.service.MelhorEnvioService;
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
	@Autowired
	private MelhorEnvioService melhorEnvioService;
	
	@Autowired	
	private FreteController freteController;	

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
		pedido.setValor(BigDecimal.ZERO);
		pedido.setDesconto(BigDecimal.ZERO);
		pedido.setValorComDescto(BigDecimal.ZERO);
		pedido.setValorComFrete(BigDecimal.ZERO);
		if (pedido.getCupomDesconto() != null && pedido.getCupomDesconto().getId() != null) {
			var cupomDesconto = cupoDescontoService.findById(pedido.getCupomDesconto().getId()).orElseThrow(() -> new LojaVirtualExceptions("Cupom de Desconto Inválido!"));
			pedido.setCupomDesconto(cupomDesconto);
		}
		//Vinculando o pedido aos itens do pedido.
		//Importante, ao salvar o pedido, havendo itens, acionará trigger de banco de dados, para atualizar os totais no pedido.
		if (pedido.getPedidoProdutos() != null)
			pedido.getPedidoProdutos().stream().forEach(itens -> itens.setPedido(pedido));
		
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/comprarfrete/{pedidoID}")
	public ResponseEntity<?> pedidoComprarFrete(@PathVariable UUID pedidoID) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException, IOException{
		var pedido = pedidoService.findById(pedidoID).orElseThrow(() -> new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"O pedido "+pedidoID+" não foi encontrado!"));
		if (pedido.getPessaJuridica() == null || pedido.getPessaJuridica().getId() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Verifique a empresa vinculada ao pedido "+pedidoID+"!");
		if (pedido.getPedidoProdutos() == null || pedido.getPedidoProdutos().isEmpty())
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Não foram encontrados produtos, vinculados ao pedido "+pedidoID+". Verifique!");
		if (pedido.getMelhorEnvio() == null || pedido.getMelhorEnvio().getMelhorEnvioFreteService() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe a opção de frete desejada, para o envio dos produtos.!");
		if(pedido.getNotaFiscalVenda() == null || pedido.getNotaFiscalVenda().getId() == null)
			throw new LojaVirtualExceptions("A Nota Fiscal de Venda vinculada ao Pedido, não foi encontrada. Verifique!");
		//
		var empresa = pedido.getEmpresa();
		if (empresa.getPessoaJuridica().getEnderecos() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"O endereço da Empresa vinculada ao Pedido, está incompleto. Verifique!");
		var enderecoEmpresa = empresa.getPessoaJuridica().getEndereco("Envio"); 
		if (enderecoEmpresa == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"O endereço de envio da Empresa vinculada ao Pedido, não foi localizado. Verifique!");		
		var cliente = pessoaFisicaService.findById(pedido.getPessoaFisica().getId()).orElseThrow(() -> new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"O cliente do produto não foi encontrado. Verifique!"));
		var enderecoCliente = cliente.getEndereco("Entrega");
		if (enderecoCliente == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"O endereço de entrega da Cliente vinculado ao Pedido, não foi localizado. Verifique!");
		//
		MelhorEnvioInsereFreteRequestDto meFreteDto = new MelhorEnvioInsereFreteRequestDto();
		meFreteDto.setService(pedido.getMelhorEnvio().getMelhorEnvioFreteService());
		meFreteDto.setAgency(pedido.getMelhorEnvio().getMelhorEnvioFreteAgency());
		InsereFreteFromDto remetente = new InsereFreteFromDto();
		remetente.setName(empresa.getPessoaJuridica().getRazaoSocial());
		remetente.setPhone(empresa.getPessoaJuridica().getTelefonePrincipal());
		remetente.setEmail(empresa.getPessoaJuridica().getEmailPrincipal());
		remetente.setDocument("");
		remetente.setCompanyDocument(empresa.getPessoaJuridica().getCnpj());
		remetente.setStateRegister(empresa.getPessoaJuridica().getIe());		
		remetente.setAddress(enderecoEmpresa.getTipoLogradouro()+" "+enderecoEmpresa.getLogradouro());
		remetente.setNumber(enderecoEmpresa.getNumero());
		remetente.setDistrict(enderecoEmpresa.getBairro());
		remetente.setCity(enderecoEmpresa.getLocalidade());
		remetente.setCountryId("BR");
		remetente.setPostalCode(enderecoEmpresa.getCep());
		remetente.setNote("");
		meFreteDto.setFrom(remetente);
		//
		InsereFreteToDto destinatario = new InsereFreteToDto();
		destinatario.setName(cliente.getNome());
		destinatario.setPhone(cliente.getTelefonePrincipal());
		destinatario.setEmail(cliente.getEmail());
		destinatario.setDocument(cliente.getCpf());
		destinatario.setCompanyDocument("");
		destinatario.setStateRegister("");
		destinatario.setAddress(enderecoCliente.getTipoLogradouro()+" "+enderecoCliente.getLogradouro());
		destinatario.setNumber(enderecoCliente.getNumero());
		destinatario.setDistrict(enderecoCliente.getBairro());
		destinatario.setCity(enderecoCliente.getLocalidade());
		destinatario.setStateAbbr(enderecoCliente.getUf());
		destinatario.setCountryId("BR");
		destinatario.setPostalCode(enderecoCliente.getCep());
		destinatario.setNote("");
		meFreteDto.setTo(destinatario);
		//
		int index = 0;
		InsereFreteProductsDto[] products = new InsereFreteProductsDto[pedido.getPedidoProdutos().size()];
		VolumesDto[] volumes = new VolumesDto[pedido.getPedidoProdutos().size()];
		for (PedidoProdutos pedidoProduto : pedido.getPedidoProdutos()) {
			Produto produto = pedidoProduto.getProduto();			
			//
			InsereFreteProductsDto product = new InsereFreteProductsDto();
			product.setName(produto.getNome());
			product.setQuantity(pedidoProduto.getQtde());
			product.setUnitaryValue(pedidoProduto.getValorFinal());
			products[index] = product;			
			//
			ProdutoConfiguracao configuracao = pedidoProduto.getProdutoConfiguracao();
			VolumesDto volume = new VolumesDto();
			volume.setHeight(BigDecimal.valueOf(configuracao.getAltura()));			
			volume.setWidth(BigDecimal.valueOf(configuracao.getLargura()));
			volume.setLength(BigDecimal.valueOf(configuracao.getProfundidade()));
			volume.setWeight(BigDecimal.valueOf(configuracao.getPeso()));
			volumes[index] = volume;
			index++;
		}
		meFreteDto.setProducts(products);
		meFreteDto.setVolumes(volumes);
		//		
		InsereFreteOptionsDto options = new InsereFreteOptionsDto();
		options.setInsuranceValue(pedido.getValorComFrete());
		options.setReceipt(false);
		options.setOwnHand(false);
		options.setReverse(false);
		options.setNonCommercial(false);
		options.setPlataform(pedido.getEmpresa().getPessoaJuridica().getNomeFantasia());
		InvoiceDto invoice = new InvoiceDto(pedido.getNotaFiscalVenda().getNumero());
		options.setInvoice(invoice);
		TagsDto[] tags = new TagsDto[]{new TagsDto(pedido.getId()+"", null)};
		options.setTags(tags);
		meFreteDto.setOptions(options);
		//
		ResponseEntity<?> response = freteController.inserirFreteProduto(meFreteDto);
		if (response.getStatusCode() == HttpStatus.OK) {
			MelhorEnvioInsereFreteResponseDto responseDto = (MelhorEnvioInsereFreteResponseDto) response.getBody();
			MelhorEnvio me = pedido.getMelhorEnvio();
			me.setMelhorEnvioInserirFreteId(responseDto.getId());
			melhorEnvioService.save(me);
			//			
			MelhorEnvioCompraFreteOrdersDto requestOrders = new MelhorEnvioCompraFreteOrdersDto();
			requestOrders.setOrder(new String[]{responseDto.getId()});
			ResponseEntity<?> responseCompraFrete = freteController.comprarFrete(requestOrders);
			if (responseCompraFrete.getStatusCode() == HttpStatus.OK) {
				MelhorEnvioPurchaseResponseDto purchaseDto = (MelhorEnvioPurchaseResponseDto) responseCompraFrete.getBody();
				me.setMelhorEnvioComprarFreteId(purchaseDto.getPurchase().getId());
				melhorEnvioService.save(me);
			}else {
				throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Ocorreu um problema ao contratar o Frete para o Pedido. Verifique!");				
			}
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}else {
			System.out.println(response.toString());
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Ocorreu um problema ao inserir o Frete para o Pedido. Verifique!");
		}
	}
}