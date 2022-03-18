package com.programabolsas.estados.service;

import com.programabolsas.estados.controller.dto.EstadoDto;
import com.programabolsas.estados.controller.form.AtualizacaoEstadoForm;
import com.programabolsas.estados.controller.form.EstadoForm;
import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class EstadosService {

    @Autowired
    EstadoRepository estadoRepository;

    public Page<EstadoDto> listarTodos(RegiaoDoEstado nomeRegiao, Pageable paginacao) {
        if(nomeRegiao == null){
            Page<Estado> estados = estadoRepository.findAll(paginacao);
            return EstadoDto.converter(estados);
        } else {
            Page<Estado> estados = estadoRepository.findByRegiao(nomeRegiao, paginacao);
            return EstadoDto.converter(estados);
        }
    }

    public ResponseEntity<EstadoDto> listarComId(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if(estado.isPresent()){
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<EstadoDto> cadastrar(EstadoForm estadoForm, UriComponentsBuilder uriBuilder) {
        Estado estado = estadoForm.converter();
        estadoRepository.save(estado);
        URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    public ResponseEntity<EstadoDto> atualizarPorId(Long id, AtualizacaoEstadoForm form) {
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> excluirPorId(Long id) {
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
