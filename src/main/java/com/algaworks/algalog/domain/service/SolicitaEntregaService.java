package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitaEntregaService {
  
  private EntregaRepository entregaRepository;
  private CatalogoClienteService catalogoClienteService;

  @Transactional
  public Entrega solicitar(Entrega entrega) {
    
    Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
    
    entrega.setCliente(cliente);
    entrega.setStatus(StatusEntrega.PENDENTE);
    entrega.setDataPedido(OffsetDateTime.now());

    return entregaRepository.save(entrega);
  }

  @Transactional
  public void excluir(Long enregaId) {
    entregaRepository.deleteById(enregaId);
  }
}
