package com.programabolsas.estados.controller;

import com.programabolsas.estados.controller.dto.EstadoDto;
import com.programabolsas.estados.controller.form.AtualizacaoEstadoForm;
import com.programabolsas.estados.controller.form.EstadoForm;
import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.repository.EstadoRepository;
import com.programabolsas.estados.service.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadosService service;

    @GetMapping
    public Page<EstadoDto> listar(@RequestParam(required = false) RegiaoDoEstado nomeRegiao, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable paginacao){
        return service.listarTodos(nomeRegiao, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> listarPorId(@PathVariable Long id){
        return service.listarComId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@RequestBody EstadoForm estadoForm, UriComponentsBuilder uriBuilder){
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
