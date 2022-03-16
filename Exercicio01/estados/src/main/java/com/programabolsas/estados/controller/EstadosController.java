package com.programabolsas.estados.controller;

import com.programabolsas.estados.controller.dto.EstadoDto;
import com.programabolsas.estados.controller.form.AtualizacaoEstadoForm;
import com.programabolsas.estados.controller.form.EstadoForm;
import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<EstadoDto> listar(RegiaoDoEstado nomeRegiao){
        if(nomeRegiao == null){
            List<Estado> estados = estadoRepository.findAll();
            return EstadoDto.converter(estados);
        } else {
            List<Estado> estados = estadoRepository.findByRegiao(nomeRegiao);
            return EstadoDto.converter(estados);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> listarPorId(@PathVariable Long id){
        Optional<Estado> estado = estadoRepository.findById(id);
        if(estado.isPresent()){
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }
        return ResponseEntity.notFound().build(); //Retorna 404
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@RequestBody EstadoForm estadoForm, UriComponentsBuilder uriBuilder){
        Estado estado = estadoForm.converter();
        estadoRepository.save(estado);
        URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
