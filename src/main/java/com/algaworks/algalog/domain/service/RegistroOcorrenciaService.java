package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
  
  private BuscaEntregaService buscaEnregaService;

  @Transactional
  public Ocorrencia registrar(Long entregaId, String descricao) {
    Entrega entrega = buscaEnregaService.buscar(entregaId);

    return entrega.adicionarOcorrencia(descricao);
  }
}
