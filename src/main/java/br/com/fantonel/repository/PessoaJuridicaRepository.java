package br.com.fantonel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import br.com.fantonel.model.PessoaJuridica;

@RestController
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM PessoaJuridica pj WHERE pj.id = ?1")
	boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM PessoaJuridica pj WHERE pj.cnpj = ?1")
	boolean existsByCnpj(String cnpj);
	
	@Query("SELECT COUNT(*) = 1 FROM PessoaJuridica pj WHERE pj.emailPrincipal = ?1")
	boolean existsByEmail(String emailPrincipal);
	
	Optional<PessoaJuridica> findByCnpj(String cnpj);
	
	Optional<PessoaJuridica> findByEmailPrincipal(String emailPrincipal);
	
	@Query("SELECT pj FROM PessoaJuridica pj WHERE pj.excluida=true")
	List<PessoaJuridica> listarExcluidas();
}