package com.mvbr.algafood.infra.api.exceptionhandler;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.exception.NegocioException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
        EntidadeNaoEncontradaException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<?> handleEntidadeExistenteException(EntidadeExistenteException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EXISTENTE;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus statusInternal = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problem problem = createProblemBuilder(statusInternal, problemType, detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                .title(status.toString())
                .status(status.value())
                .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                .title((String) body)
                .status(status.value())
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail);
    }

//    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
//                                                        ProblemType problemType, String detail) {
//
//        return Problem.builder()
//            .status(status.value())
//            .type(problemType.getUri())
//            .title(problemType.getTitle())
//            .detail(detail);
//    }


    //    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
//
//        final HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
//
//        Problema problema = Problema.builder()
//            .dataHora(LocalDateTime.now())
//            .mensagem("O tipo de mídia não é aceito.")
//            .status(httpStatus)
//            .statusCodigo(httpStatus.value())
//            .build();
//
//        return ResponseEntity.status(httpStatus).body(problema);
//    }

//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotAcceptableException e) {
//
//        final HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
//
//        Problema problema = Problema.builder()
//            .dataHora(LocalDateTime.now())
//            .mensagem("O tipo de mídia não é aceito.")
//            .status(httpStatus)
//            .statusCodigo(httpStatus.value())
//            .build();
//
//        return ResponseEntity.status(httpStatus).body(problema);
//    }

}
