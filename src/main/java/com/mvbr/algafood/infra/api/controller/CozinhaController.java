package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import com.mvbr.algafood.domain.service.CozinhaService;
import com.mvbr.algafood.infra.dto.CozinhasXmlWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @RequestMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {

        try {
            Cozinha cozinha = cozinhaService.buscar(id);
            return ResponseEntity.ok(cozinha);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cozinha buscar: " + e.getMessage());
            return ResponseEntity.notFound().build();

        }

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        return  ResponseEntity.ok(cozinha);
//        return cozinhaRepository.buscar(id);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaService.listar());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cozinha> criar(@RequestBody Cozinha cozinha) {

        try {
            cozinha = cozinhaService.criar(cozinha);
            return ResponseEntity.ok(cozinha);

        } catch (EntidadeExistenteException e) {
            System.out.println("Cozinha criar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

        try {
            cozinha = cozinhaService.atualizar(id, cozinha);
            return ResponseEntity.ok(cozinha);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cozinha atualizar: " + e.getMessage());
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            System.out.println("Cozinha atualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> excluir(@PathVariable Long id) {

        try {
            cozinhaService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Cozinha excluir: " + e.getMessage());
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            System.out.println("Cozinha excluir: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
