package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurantes",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable("id") Long id) {
        return restauranteService.buscar(id);
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante criar(@RequestBody @Valid Restaurante restaurante) {
        return restauranteService.criar(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
        return restauranteService.atualizar(id, restaurante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        restauranteService.excluir(id);
    }

}
