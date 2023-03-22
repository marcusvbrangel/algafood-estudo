package com.mvbr.algafood.infra.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = appContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Plaza");
        restaurante1.setTaxaFrete(new BigDecimal(5.70));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Caiçara");
        restaurante2.setTaxaFrete(new BigDecimal(3.20));

        restaurante1 = restauranteRepository.salvar(restaurante1);
        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%d - %s \n", restaurante1.getId(), restaurante1.getNome());
        System.out.printf("%d - %s \n", restaurante2.getId(), restaurante2.getNome());

    }
}
