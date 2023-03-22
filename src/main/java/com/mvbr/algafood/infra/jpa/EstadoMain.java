package com.mvbr.algafood.infra.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import java.util.List;

public class EstadoMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        EstadoRepository estadoRepository = appContext.getBean(EstadoRepository.class);

        System.out.println("\n******************************************************************");
        System.out.println("Estado - listar \n");

        List<Estado> estados = estadoRepository.listar();

        for (Estado estado : estados) {
            System.out.printf("%d - %s \n", estado.getId(), estado.getNome());
        }

        System.out.println("\n******************************************************************");
        System.out.println("Estado - buscar \n");

        Estado estado = estadoRepository.buscar(1L);

        System.out.printf("%d - %s \n", estado.getId(), estado.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Estado - salvar/incluir \n");

        estado = new Estado();
        estado.setNome("Amazonas");

        estado = estadoRepository.salvar(estado);

        System.out.printf("%d - %s \n", estado.getId(), estado.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Estado - salvar/alterar \n");

        estado = new Estado();
        estado.setId(1L);
        estado.setNome("Rio de Janeiro alterado");

        estado = estadoRepository.salvar(estado);

        System.out.printf("%d - %s \n", estado.getId(), estado.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Estado - excluir \n");

        estado = new Estado();
        estado.setId(5L);

        estadoRepository.remover(estado);

        System.out.println("\n******************************************************************");
        System.out.println("Estado - listar (novamente) \n");

        List<Estado> estados2 = estadoRepository.listar();

        for (Estado estado2 : estados2) {
            System.out.printf("%d - %s \n", estado2.getId(), estado2.getNome());
        }

        System.out.println("\n******************************************************************\n");

    }
}
