package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cidade;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping("/{cidadeId}")
    public ResponseEntity<?> buscar(@PathVariable("cidadeId") Long id) {

        try {
            Cidade cidade = cidadeService.buscar(id);
            return ResponseEntity.ok(cidade);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cidade buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody Cidade cidade) {

        try {
            cidade = cidadeService.criar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

        } catch (EntidadeExistenteException e) {
            System.out.println("Cidade criar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        // Todo: implementar... ResponseEntity.badRequest().build()... para estado nao existente...

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {

        try {
            cidade = cidadeService.atualizar(id, cidade);
            return ResponseEntity.ok(cidade);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cidade atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeExistenteException e) {
            System.out.println("Cidade atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        // Todo: implementar... ResponseEntity.badRequest().build()... para estado nao existente...

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        try {
            cidadeService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cidade excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}
