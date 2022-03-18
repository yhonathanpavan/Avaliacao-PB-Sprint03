package com.programabolsas.estados.controller.form;

import com.programabolsas.estados.modelo.Estado;
import com.programabolsas.estados.modelo.RegiaoDoEstado;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EstadoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String nome;
    @NotNull
    private RegiaoDoEstado regiao;
    @NotNull
    private Long populacao;
    @NotNull @NotEmpty
    private String capital;
    @NotNull
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
