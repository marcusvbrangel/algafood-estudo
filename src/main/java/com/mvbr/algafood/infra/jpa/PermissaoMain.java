package com.mvbr.algafood.infra.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.Permissao;
import com.mvbr.algafood.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import java.util.List;

public class PermissaoMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository permissaoRepository = appContext.getBean(PermissaoRepository.class);

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - listar \n");

        List<Permissao> permissoes = permissaoRepository.listar();

        for (Permissao permissao : permissoes) {
            System.out.printf("%d - %s \n", permissao.getId(), permissao.getNome());
        }

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - buscar \n");

        Permissao permissao = permissaoRepository.buscar(1L);

        System.out.printf("%d - %s \n", permissao.getId(), permissao.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - salvar/incluir \n");

        permissao = new Permissao();
        permissao.setNome("Imprimir");

        permissao = permissaoRepository.salvar(permissao);

        System.out.printf("%d - %s \n", permissao.getId(), permissao.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - salvar/alterar \n");

        permissao = new Permissao();
        permissao.setId(1L);
        permissao.setNome("Incluir alterar");

        permissao = permissaoRepository.salvar(permissao);

        System.out.printf("%d - %s \n", permissao.getId(), permissao.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - excluir \n");

        permissao = new Permissao();
        permissao.setId(2L);

        permissaoRepository.remover(permissao);

        System.out.println("\n******************************************************************");
        System.out.println("Permissão - listar (novamente) \n");

        List<Permissao> permissoes2 = permissaoRepository.listar();

        for (Permissao permissao2 : permissoes2) {
            System.out.printf("%d - %s \n", permissao2.getId(), permissao2.getNome());
        }

        System.out.println("\n******************************************************************\n");

    }

}
