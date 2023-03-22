package com.mvbr.algafood.infra.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlteracaoRestauranteMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = appContext.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Ponto Certo 2");

        restaurante = restauranteRepository.salvar(restaurante);

        System.out.printf("%d - %s \n", restaurante.getId(), restaurante.getNome());

    }
}
