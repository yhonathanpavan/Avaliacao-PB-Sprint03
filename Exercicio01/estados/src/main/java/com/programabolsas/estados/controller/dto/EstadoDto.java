package com.programabolsas.estados.controller.dto;

import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import org.springframework.data.domain.Page;


public class EstadoDto{

    private Long id;
    private String nome;
    private RegiaoDoEstado regiao;
    private Long populacao;
    private String capital;
    private Double area;

    public EstadoDto(Estado estado){
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.regiao = estado.getRegiao();
        this.populacao = estado.getPopulacao();
        this.capital = estado.getCapital();
        this.area = estado.getArea();
    }

    public Long getId() {
        return id;
    }

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

    public static Page<EstadoDto> converter(Page<Estado> estados){
        return estados.map(EstadoDto::new);
    }


}
