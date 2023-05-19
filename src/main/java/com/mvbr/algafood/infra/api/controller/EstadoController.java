package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.service.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/estados",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable("estadoId") Long id) {
        return estadoService.buscar(id);
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado criar(@RequestBody Estado estado) {
        return estadoService.criar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        return estadoService.atualizar(id, estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        estadoService.excluir(id);
    }

}
