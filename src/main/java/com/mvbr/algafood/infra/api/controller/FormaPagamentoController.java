package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @RequestMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {

        try {
            FormaPagamento formaPagamento = formaPagamentoService.buscar(id);
            return ResponseEntity.ok(formaPagamento);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Forma pagamento buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FormaPagamento> listar() {
        return formaPagamentoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody FormaPagamento formaPagamento) {

        try {
            formaPagamento = formaPagamentoService.criar(formaPagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamento);

        } catch (EntidadeExistenteException e) {
            System.out.println("Forma pagamento criar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {

        try {
            formaPagamento = formaPagamentoService.atualizar(id, formaPagamento);
            return ResponseEntity.ok(formaPagamento);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Forma pagamento atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeExistenteException e) {
            System.out.println("Forma pagamento atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        try {
            formaPagamentoService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Forma pagamento excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}
