package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

  // List<Entrega> findByNome(String nome);
  // List<Entrega> findByNomeContaining(String nome);
  // Optional<Entrega> findByEmail(String email);
}
