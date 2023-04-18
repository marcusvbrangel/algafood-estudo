package com.mvbr.algafood.domain.repository;

import com.mvbr.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> consultarComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> consultarComFreteGratis(String nome);

}
