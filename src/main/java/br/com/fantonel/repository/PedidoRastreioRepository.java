package br.com.fantonel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fantonel.model.PedidoRastreio;

public interface PedidoRastreioRepository extends JpaRepository<PedidoRastreio, UUID> {
	@Query(value = "SELECT pr FROM PedidoRastreio pr WHERE pr.pedido.id = ?1")
	List<PedidoRastreio> findByPedidoID(UUID pedidoID);
}