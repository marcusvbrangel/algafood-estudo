package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.service.CozinhaService;
import com.mvbr.algafood.infra.dto.CozinhasXmlWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/cozinhas",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

//    @RequestMapping("/{cozinhaId}")
//    public ResponseEntity<?> buscar(@PathVariable("cozinhaId") Long id) {
//
//        try {
//            Cozinha cozinha = cozinhaService.buscar(id);
//            return ResponseEntity.ok(cozinha);
//
//        } catch (EntidadeNaoEncontradaException e) {
//            System.out.println("Cozinha buscar: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//
////        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
////        return  ResponseEntity.ok(cozinha);
////        return cozinhaRepository.buscar(id);
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
////        return ResponseEntity
////                .status(HttpStatus.FOUND)
////                .headers(headers)
////                .build();
//
//    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
        return cozinhaService.buscar(id);
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaService.listar());
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> criar(@RequestBody Cozinha cozinha) {
//
//        try {
//            cozinha = cozinhaService.criar(cozinha);
//            return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
//
//        } catch (EntidadeExistenteException e) {
//            System.out.println("Cozinha criar: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha criar(@RequestBody Cozinha cozinha) {
        return cozinhaService.criar(cozinha);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
//
//        try {
//            cozinha = cozinhaService.atualizar(id, cozinha);
//            return ResponseEntity.ok(cozinha);
//
//        } catch (EntidadeNaoEncontradaException e) {
//            System.out.println("Cozinha atualizar: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//
//        } catch (EntidadeExistenteException e) {
//            System.out.println("Cozinha atualizar: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//
//    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        return cozinhaService.atualizar(id, cozinha);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> excluir(@PathVariable Long id) {
//
//        try {
//            cozinhaService.excluir(id);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            System.out.println("Cozinha excluir: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//
//        } catch (EntidadeEmUsoException e) {
//            System.out.println("Cozinha excluir: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void excluir(@PathVariable Long id) {
//        cozinhaService.excluir(id);
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void excluir(@PathVariable Long id) {
//        try {
//            cozinhaService.excluir(id);
//
//        } catch (EntidadeNaoEncontradaException e) {
//            System.out.println("Cozinha excluir: " + e.getMessage());
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//
//        } catch (EntidadeEmUsoException e) {
//            System.out.println("Cozinha excluir: " + e.getMessage());
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        }
//
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cozinhaService.excluir(id);
    }


}
