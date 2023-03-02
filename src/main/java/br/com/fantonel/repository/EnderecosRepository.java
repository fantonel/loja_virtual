package br.com.fantonel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Endereco;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, UUID> {
}