package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @RequestMapping("/{id}")
    public FormaPagamento buscar(@PathVariable("id") Long id) {
        return formaPagamentoService.buscar(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
