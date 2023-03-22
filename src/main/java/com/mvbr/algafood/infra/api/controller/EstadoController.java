package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/estados", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {

        Estado estado = estadoRepository.buscar(id);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity.notFound().build();
    }

}
