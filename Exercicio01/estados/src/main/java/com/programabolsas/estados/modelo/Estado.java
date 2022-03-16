package com.programabolsas.estados.modelo;

import javax.persistence.*;

@Entity
public class Estado{

    public Estado(){}

    public Estado(String nome, RegiaoDoEstado regiao, Long populacao, String capital, Double area) {
        this.nome = nome;
        this.regiao = regiao;
        this.populacao = populacao;
        this.capital = capital;
        this.area = area;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING) //Quero guardar a string e não a ordem do enum
    private RegiaoDoEstado regiao;
    private Long populacao;
    private String capital;
    private Double area;

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

    //Setters


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegiao(RegiaoDoEstado regiao) {
        this.regiao = regiao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
