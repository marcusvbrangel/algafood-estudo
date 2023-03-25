package com.mvbr.algafood.infra.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.Cidade;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.CidadeRepository;
import com.mvbr.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import java.util.List;

public class CidadeMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CidadeRepository cidadeRepository = appContext.getBean(CidadeRepository.class);

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - listar \n");

        List<Cidade> cidades = cidadeRepository.listar();

        for (Cidade cidade : cidades) {
            System.out.printf("%d - %s - %s \n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());
        }

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - buscar \n");

        Cidade cidade = cidadeRepository.buscar(1L);

        System.out.printf("%d - %s \n", cidade.getId(), cidade.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - salvar/incluir \n");

        EstadoRepository estadoRepository = appContext.getBean(EstadoRepository.class);
        Estado estado = estadoRepository.buscar(3L);

        cidade = new Cidade();
        cidade.setNome("Ouro Preto");
        cidade.setEstado(estado);

        cidade = cidadeRepository.salvar(cidade);

        System.out.printf("%d - %s - %s \n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - salvar/alterar \n");

        estado = estadoRepository.buscar(1L);

        cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Rio de Janeiro alterado");
        cidade.setEstado(estado);

        cidade = cidadeRepository.salvar(cidade);

        System.out.printf("%d - %s \n", cidade.getId(), cidade.getNome());

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - excluir \n");

        cidade = new Cidade();
        cidade.setId(2L);

        cidadeRepository.excluir(cidade);

        System.out.println("\n******************************************************************");
        System.out.println("Cidade - listar (novamente) \n");

        List<Cidade> cidades2 = cidadeRepository.listar();

        for (Cidade cidade2 : cidades2) {
            System.out.printf("%d - %s - %s \n", cidade2.getId(), cidade2.getNome(), cidade2.getEstado().getNome());
        }

        System.out.println("\n******************************************************************\n");

    }

}
