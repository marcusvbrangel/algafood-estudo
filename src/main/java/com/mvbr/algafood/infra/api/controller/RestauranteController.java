package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @RequestMapping("/{id}")
    public Restaurante buscar(@PathVariable("id") Long id) {
        return restauranteService.buscar(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante criar(@RequestBody Restaurante restaurante) {
        return restauranteService.criar(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        return restauranteService.atualizar(id, restaurante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        restauranteService.excluir(id);
    }

}
