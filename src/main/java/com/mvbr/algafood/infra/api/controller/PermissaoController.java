package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Permissao;
import com.mvbr.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @RequestMapping("/{id}")
    public Permissao buscar(@PathVariable("id") Long id) {
        return permissaoService.buscar(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Permissao> listar() {
        return permissaoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissao criar(@RequestBody Permissao permissao) {
        return permissaoService.criar(permissao);
    }

    @PutMapping("/{id}")
    public Permissao atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {
        return permissaoService.atualizar(id, permissao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        permissaoService.excluir(id);
    }

}
