package com.programabolsas.estados.controller;

import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> lista(RegiaoDoEstado nomeRegiao){
        System.out.println(nomeRegiao);
        if(nomeRegiao == null){
            List<Estado> estado = estadoRepository.findAll();
            return new ArrayList<>(estado);
        } else {
            List<Estado> estado = estadoRepository.findByRegiao(nomeRegiao);
            return new ArrayList<>(estado);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Estado> cadastrar(@RequestBody @Valid Estado estado, UriComponentsBuilder uriBuilder){ //Digo que esse parâmetro eu quero pegar do corpo da requisição
        estadoRepository.save(estado);
        URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(estado);
    }

}
