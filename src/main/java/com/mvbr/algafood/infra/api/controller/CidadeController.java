package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Cidade;
import com.mvbr.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable("cidadeId") Long id) {
        return cidadeService.buscar(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade criar(@RequestBody Cidade cidade) {
        return cidadeService.criar(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        return cidadeService.atualizar(id, cidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cidadeService.excluir(id);
    }

}
