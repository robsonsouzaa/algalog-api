package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaDTO;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
  
  private RegistroOcorrenciaService registroOcorrenciaService;
  private OcorrenciaAssembler ocorrenciaAssembler;
  private BuscaEntregaService buscaEntregaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
    Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

    return ocorrenciaAssembler.toDTO(ocorrenciaRegistrada);
  }

  @GetMapping
  public List<OcorrenciaDTO> listar(@PathVariable Long entregaId){
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    return ocorrenciaAssembler.toCollectionDTO(entrega.getOcorrencias());
  }
}