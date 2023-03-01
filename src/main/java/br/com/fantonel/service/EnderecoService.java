package br.com.fantonel.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fantonel.model.Endereco;
import br.com.fantonel.repository.EnderecosRepository;

@Service
@Transactional
public class EnderecoService {
	@Autowired
	private EnderecosRepository enderecosRepository;
	
	public Endereco save(Endereco bean) {
		return enderecosRepository.save(bean);
	}
	
	public void deleteById(UUID id) {
		enderecosRepository.deleteById(id);
	}
}