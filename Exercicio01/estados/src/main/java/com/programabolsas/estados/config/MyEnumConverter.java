package com.programabolsas.estados.config;

import com.programabolsas.estados.modelo.RegiaoDoEstado;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//Essa classe trata o "case sensitive" no momento de filtrar por parâmetro
@Component
public class MyEnumConverter implements Converter<String, RegiaoDoEstado> {

    @Override
    public RegiaoDoEstado convert(String value) {
        return RegiaoDoEstado.valueOf(value.toUpperCase());
    }
}