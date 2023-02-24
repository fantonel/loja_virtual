package br.com.fantonel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM PessoaFisica pf WHERE pf.id = ?1")
	boolean existsById(UUID id);
	
	@Query("SELECT COUNT(*) = 1 FROM PessoaFisica pf WHERE pf.cpf = ?1")
	boolean existsByCpf(String cpf);
	
	@Query("SELECT COUNT(*) = 1 FROM PessoaFisica pf WHERE pf.email = ?1")
	boolean existsByEmail(String email);
	
	Optional<PessoaFisica> findByCpf(String cpf);
	
	Optional<PessoaFisica> findByEmail(String email);
	
	@Query("SELECT pf FROM PessoaFisica pf WHERE pf.excluida=true")
	List<PessoaFisica> listarExcluidas();
}