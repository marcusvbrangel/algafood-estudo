package com.mvbr.algafood.infra.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
        EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<?> handleEntidadeExistenteException(EntidadeExistenteException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EXISTENTE;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        HttpStatus statusInternal = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problem problem = createProblemBuilder(statusInternal, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = joinPath(ex.getPath());

        HttpStatus statusInternal = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. "
            + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problem problem = createProblemBuilder(statusInternal, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    private ResponseEntity<Object> handleInvalidFormatException(
        InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus statusInternal = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String path = joinPath(ex.getPath());

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', " +
            "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
            path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(statusInternal, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);

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

    private String joinPath(List<Reference> references) {
        return references.stream()
            .map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));
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
