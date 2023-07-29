package br.com.fantonel.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.fantonel.model.Empresa;
import br.com.fantonel.repository.EmpresaRepository;

@Service
public class EmpresaService {
	private EmpresaRepository empresaRepository;

	public EmpresaService() {
	}
	
	public Optional<Empresa> findById(UUID id){
		return empresaRepository.findById(id);
	}
}