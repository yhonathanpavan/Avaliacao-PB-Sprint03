package com.programabolsas.estados.controller.form;

import com.programabolsas.estados.controller.dto.EstadoDto;
import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import com.programabolsas.estados.repository.EstadoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EstadoForm {

    //private Long id;
    private String nome;
    private RegiaoDoEstado regiao;
    private Long populacao;
    private String capital;
    private Double area;

    public String getNome() {
        return nome;
    }

    public RegiaoDoEstado getRegiao() {
        return regiao;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public String getCapital() {
        return capital;
    }

    public Double getArea() {
        return area;
    }

    public Estado converter(){
        return new Estado(this.nome, this.regiao, this.populacao, this.capital, this.area);
    }
}
