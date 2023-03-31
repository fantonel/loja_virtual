package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
	@Query("SELECT COUNT(*) = 1 FROM Pedido p WHERE p.id = ?1")
	boolean existsById(UUID id);

	@Query("SELECT ped FROM Pedido ped WHERE ped.pessoaFisica.id = ?1")
	List<Pedido> findByPessoaFisica(UUID id);
	
	@Query("SELECT ped FROM Pedido ped WHERE ped.pessaJuridica.id = ?1")
	List<Pedido> findByPessoaJuridica(UUID id);
	
	@Query("SELECT ped FROM Pedido ped WHERE ped.pessoaFisica.cpf = ?1")
	List<Pedido> findByPessoaFisicaCpf(String cpf);
	
	@Query("SELECT ped FROM Pedido ped WHERE ped.pessaJuridica.cnpj = ?1")
	List<Pedido> findByPessoaJuridicaCnpj(String cnpj);
}