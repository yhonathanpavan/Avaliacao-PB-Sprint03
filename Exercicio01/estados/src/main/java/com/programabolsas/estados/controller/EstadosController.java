package com.programabolsas.estados.controller;

import com.programabolsas.estados.controller.dto.EstadoDto;
import com.programabolsas.estados.controller.form.AtualizacaoEstadoForm;
import com.programabolsas.estados.controller.form.EstadoForm;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.service.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadosService service;

    @GetMapping
    public List<EstadoDto> listar(@RequestParam(required = false) RegiaoDoEstado nomeRegiao, @RequestParam(required = false) String sort){
        return service.listarTodos(nomeRegiao, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> listarPorId(@PathVariable Long id){
        return service.listarComId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm estadoForm, UriComponentsBuilder uriBuilder){
        return service.cadastrar(estadoForm, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form){
        return service.atualizarPorId(id, form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        return service.excluirPorId(id);
    }
}
