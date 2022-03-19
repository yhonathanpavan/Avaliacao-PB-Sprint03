package com.programabolsas.estados.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHttp {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public String handle(){

            return "Erro 400 - Problemas na requisição: Verifique se os inputs foram preenchidos corretamente.";
        }
}
