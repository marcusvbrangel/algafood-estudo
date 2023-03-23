package com.mvbr.algafood.domain.exception;

public class EntidadeExistenteException extends RuntimeException {

    public EntidadeExistenteException(String mensagem) {
        super(mensagem);
    }
}
