package br.com.fantonel.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
import br.com.fantonel.model.Endereco;
import br.com.fantonel.model.PessoaFisica;
import br.com.fantonel.service.PessoaFisicaService;

@RestController
@RequestMapping("api/v1/pessoasfisicas")
public class PessoaFisicaController {
	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	
	public PessoaFisicaController() {}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public ResponseEntity<PessoaFisica> salvar(@RequestBody @Valid PessoaFisica pessoaFisica) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (pessoaFisica.getId() == null && pessoaFisicaService.existsByCpf(pessoaFisica.getCpf()))
			throw new LojaVirtualExceptions(HttpStatus.OK, "Já existe pessoa cadastrada, com o CPF informado!");
		
		if (pessoaFisica.getId() == null && pessoaFisicaService.existsByEmail(pessoaFisica.getEmail()))
			throw new LojaVirtualExceptions(HttpStatus.OK, "Já existe pessoa cadastrada, com o Email informado!");
		
		if (pessoaFisica.getDataNascto() == null)
			throw new LojaVirtualExceptions(HttpStatus.OK, "informe a data de nascimento!");
		
		if (pessoaFisica.getDataNascto().after(new Date()))
				throw new LojaVirtualExceptions(HttpStatus.OK, "A data de nascimento é inválida!");
		
		for (Endereco endereco : pessoaFisica.getEnderecos()) {			
			if (endereco.getPessoaFisica() == null)
				endereco.setPessoaFisica(pessoaFisica);
			validarEndereco(endereco);
		}
		
		if (pessoaFisica.getId() == null)
			pessoaFisica = pessoaFisicaService.save(pessoaFisica);
		else {
			var entity = pessoaFisicaService.findById(pessoaFisica.getId());
			BeanUtils.copyProperties(entity, pessoaFisica);
			pessoaFisica = pessoaFisicaService.save(pessoaFisica);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaFisica);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable UUID id) throws LojaVirtualExceptions, IllegalAccessException, InvocationTargetException{
		if (!pessoaFisicaService.existsById(id))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "O registro não foi encontrado, para ser excluído!");
		
		pessoaFisicaService.deleteById(id);		
				
		return ResponseEntity.status(HttpStatus.OK).body("Registro excluído com sucesso!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarTodas")
	public ResponseEntity<List<PessoaFisica>> listarTodas(){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaFisicaService.findAll());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/listarExcluidas")
	public ResponseEntity<List<PessoaFisica>> listarExcluidas(){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaFisicaService.listarExcluidas());		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id) throws LojaVirtualExceptions{
		var pessoaFisica = pessoaFisicaService.findById(id);
		if (pessoaFisica.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(pessoaFisica);
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado.");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorCpf/{cpf}")
	public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) throws LojaVirtualExceptions{
		var entity = pessoaFisicaService.findByCpf(cpf);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/buscarPorEmail/{email}")
	public ResponseEntity<?> buscarPorEmail(@PathVariable String email) throws LojaVirtualExceptions{
		var entity = pessoaFisicaService.findByEmail(email);		
		if (entity.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(entity.get());
		
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Registro não encontrado");
	}
	
	private void validarEndereco(Endereco endereco) throws LojaVirtualExceptions{
		if (StringUtils.isBlank(endereco.getCep()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o cep do endereço");
		if (endereco.getTipoLogradouro() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o tipo do logradouro");
		if (StringUtils.isBlank(endereco.getLogradouro()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o logradouro");
		if (StringUtils.isBlank(endereco.getNumero()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o número");		
		if (StringUtils.isBlank(endereco.getBairro()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o bairro");
		if (StringUtils.isBlank(endereco.getLocalidade()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe a cidade");
		if (StringUtils.isBlank(endereco.getUf()))
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe a o estado");		
		if (endereco.getTipoEndereco() == null)
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND,"Informe o tipo do endereço");		
	}
}