package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

  private EntregaRepository entregaRepository;
  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public void finalizar(long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    entrega.finalizar();
    
    entregaRepository.save(entrega);
  }
  
}
