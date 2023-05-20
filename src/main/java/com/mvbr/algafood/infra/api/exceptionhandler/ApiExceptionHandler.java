package com.mvbr.algafood.infra.api.exceptionhandler;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .status(httpStatus)
            .statusCodigo(httpStatus.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<?> tratarEntidadeExistenteException(EntidadeExistenteException e) {

        final HttpStatus httpStatus = HttpStatus.CONFLICT;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .status(httpStatus)
            .statusCodigo(httpStatus.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {

        final HttpStatus httpStatus = HttpStatus.CONFLICT;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .status(httpStatus)
            .statusCodigo(httpStatus.CONFLICT.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {

        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .status(httpStatus)
            .statusCodigo(httpStatus.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {

        final HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem("O tipo de mídia não é aceito.")
            .status(httpStatus)
            .statusCodigo(httpStatus.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotAcceptableException e) {

        final HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem("O tipo de mídia não é aceito.")
            .status(httpStatus)
            .statusCodigo(httpStatus.value())
            .build();

        return ResponseEntity.status(httpStatus).body(problema);
    }

}
