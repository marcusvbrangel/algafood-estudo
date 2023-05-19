package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.service.FormaPagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/formas-pagamento",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping("/{id}")
    public FormaPagamento buscar(@PathVariable("id") Long id) {
        return formaPagamentoService.buscar(id);
    }

    @GetMapping
    public List<FormaPagamento> listar() {
        return formaPagamentoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento criar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.criar(formaPagamento);
    }

    @PutMapping("/{id}")
    public FormaPagamento atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.atualizar(id, formaPagamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
    }

}
