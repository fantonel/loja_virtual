package br.com.fantonel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fantonel.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {
}