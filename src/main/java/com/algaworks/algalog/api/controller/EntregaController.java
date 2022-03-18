package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaDTO;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitaEntregaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
  
  @Autowired
  private EntregaRepository entregaRepository;
  private SolicitaEntregaService solicitaEntregaService;
  private EntregaAssembler entregaAssembler;
  private FinalizacaoEntregaService finalizacaoEntregaService;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
    Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
    Entrega entregaSolicitada = solicitaEntregaService.solicitar(novaEntrega);

    return entregaAssembler.toDTO(entregaSolicitada);
  }

  @GetMapping
  public List<EntregaDTO> listar(){
    return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
    return entregaRepository.findById(entregaId)
      .map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{entregaId}")
  public ResponseEntity<Void> remover(@PathVariable Long entregaId) {
    if(!entregaRepository.existsById(entregaId)) {
      return ResponseEntity.notFound().build();
    }

    solicitaEntregaService.excluir(entregaId);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{entregaId}/finalizacao")
  @ResponseStatus(HttpStatus.NO_CONTENT) //código 204
  public void finalizar(@PathVariable Long entregaId) {
    finalizacaoEntregaService.finalizar(entregaId);
  }

}
