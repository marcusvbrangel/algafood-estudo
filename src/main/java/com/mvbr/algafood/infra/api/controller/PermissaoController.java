package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Permissao;
import com.mvbr.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @RequestMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {

        try {
            Permissao permissao = permissaoService.buscar(id);
            return ResponseEntity.ok(permissao);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Permissão buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Permissao> listar() {
        return permissaoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody Permissao permissao) {

        try {
            permissao = permissaoService.criar(permissao);
            return ResponseEntity.status(HttpStatus.CREATED).body(permissao);

        } catch (EntidadeExistenteException e) {
            System.out.println("Cidade criar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {

        try {
            permissao = permissaoService.atualizar(id, permissao);
            return ResponseEntity.ok(permissao);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Permissão atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeExistenteException e) {
            System.out.println("Permissão atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        try {
            permissaoService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Permissão excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}