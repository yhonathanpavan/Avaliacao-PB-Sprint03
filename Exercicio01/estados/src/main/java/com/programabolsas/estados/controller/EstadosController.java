package com.programabolsas.estados.controller;

import com.programabolsas.estados.controller.form.AtualizacaoEstadoForm;
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
    public List<Estado> listar(RegiaoDoEstado nomeRegiao){
        System.out.println(nomeRegiao);
        if(nomeRegiao == null){
            List<Estado> estado = estadoRepository.findAll();
            return new ArrayList<>(estado);
        } else {
            List<Estado> estado = estadoRepository.findByRegiao(nomeRegiao);
            return new ArrayList<>(estado);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> listarPorId(@PathVariable Long id){ //Isto é, preciso avisar para o Spring que o parâmetro Long id não virá numa interrogação e sim no barra ("/"), na URL. Para dizer isso pro Spring, existe uma anotação, @PathVariable, que se trata de uma variável do Path, da URL. E aí o Spring por padrão perceberá que o nome do parâmetro do método se chama id (@PathVariable Long id) e a parte dinâmica da URL se chama id ("/{id}"), então ele vai associar, saberá que é para pegar o que veio na URL e jogar no parâmetro.
        Optional<Estado> estado = estadoRepository.findById(id); //Tipo Optional pois pode ter um registro ou não...
        if(estado.isPresent()){
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build(); //Retorna 404
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Estado> cadastrar(@RequestBody @Valid Estado estado, UriComponentsBuilder uriBuilder){ //Digo que esse parâmetro eu quero pegar do corpo da requisição
        estadoRepository.save(estado);
        URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(estado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

}
