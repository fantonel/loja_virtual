package br.com.fantonel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fantonel.model.MelhorEnvio;

public interface MelhorEnvioRepository extends JpaRepository<MelhorEnvio, UUID> {
}