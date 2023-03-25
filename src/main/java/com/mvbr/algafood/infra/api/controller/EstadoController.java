package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.EstadoRepository;
import com.mvbr.algafood.domain.service.CozinhaService;
import com.mvbr.algafood.domain.service.EstadoService;
import com.mvbr.algafood.infra.dto.CozinhasXmlWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/estados", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @RequestMapping("/{estadoId}")
    public ResponseEntity<?> buscar(@PathVariable("estadoId") Long id) {

        try {
            Estado estado = estadoService.buscar(id);
            return ResponseEntity.ok(estado);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Estado buscar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> criar(@RequestBody Estado estado) {

        try {
            estado = estadoService.criar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);

        } catch (EntidadeExistenteException e) {
            System.out.println("Estado criar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado) {

        try {
            estado = estadoService.atualizar(id, estado);
            return ResponseEntity.ok(estado);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Estado atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeExistenteException e) {
            System.out.println("Estado atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        try {
            estadoService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Estado excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            System.out.println("Estado excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
