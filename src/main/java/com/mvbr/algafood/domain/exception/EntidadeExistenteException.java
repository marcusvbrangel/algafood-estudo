package com.mvbr.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeExistenteException extends RuntimeException {

    public EntidadeExistenteException(String mensagem) {
        super(mensagem);
    }
}
