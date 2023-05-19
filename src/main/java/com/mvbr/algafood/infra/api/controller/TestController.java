package com.mvbr.algafood.infra.api.controller;

import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import com.mvbr.algafood.domain.repository.RestauranteRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/teste",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    public TestController(CozinhaRepository cozinhaRepository, RestauranteRepository restauranteRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> listarCozinhaPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findTodasByNome(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaUnicaPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/find")
    public List<Restaurante> find(
        @RequestParam("nome") String nome,
        @RequestParam("taxaInicial") BigDecimal taxaInicial,
        @RequestParam("taxaFinal") BigDecimal taxaFinal) {
        return restauranteRepository.consultar(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return restauranteRepository.consultarComFreteGratis(nome);

    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantePrimeiro() {
        return restauranteRepository.buscarPrimeiroRegistroEncontrado();

    }

}
