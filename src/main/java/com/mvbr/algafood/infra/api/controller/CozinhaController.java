package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import com.mvbr.algafood.infra.dto.CozinhasXmlWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @RequestMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
            return ResponseEntity.ok(cozinha);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();

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

}
