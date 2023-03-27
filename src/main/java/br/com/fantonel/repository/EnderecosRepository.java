package br.com.fantonel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Endereco;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, UUID> {
	@Query("SELECT ender FROM Endereco ender WHERE ender.id = ?1 AND pessoaFisica.id = ?2 AND tipoendereco='COBRANCA' AND ativo=true")
	Optional<Endereco> findByEnderecoCobrancaPessoaFisica(UUID enderecoId, UUID pessoaID);
	
	@Query("SELECT ender FROM Endereco ender WHERE ender.id = ?1 AND pessoaFisica.id = ?2 AND tipoendereco='ENTREGA' AND ativo=true")
	Optional<Endereco> findByEnderecoEntregaPessoaFisica(UUID enderecoId, UUID pessoaID);
	
	@Query("SELECT ender FROM Endereco ender WHERE id = ?1 AND pessoaJuridica.id = ?2 AND tipoendereco='COBRANCA' AND ativo=true")
	Optional<Endereco> findByEnderecoCobrancaPessoaJuridica(UUID enderecoId, UUID pessoaID);
	
	@Query("SELECT ender FROM Endereco ender WHERE id = ?1 AND pessoaJuridica.id = ?2 AND tipoendereco='ENTREGA' AND ativo=true")
	Optional<Endereco> findByEnderecoEntregaPessoaJuridica(UUID enderecoId, UUID pessoaID);
}