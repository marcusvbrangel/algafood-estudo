package com.mvbr.algafood.jpa;

import com.mvbr.algafood.AlgafoodEstudoApplication;
import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import java.util.List;

public class FormaPagamentoMain {

    public static void main(String[] args) {

        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodEstudoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository formaPagamentoRepository = appContext.getBean(FormaPagamentoRepository.class);

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - listar \n");

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.listar();

        for (FormaPagamento formaPagamento : formaPagamentos) {
            System.out.printf("%d - %s \n", formaPagamento.getId(), formaPagamento.getDescricao());
        }

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - buscar \n");

        FormaPagamento formaPagamento = formaPagamentoRepository.buscar(1L);

        System.out.printf("%d - %s \n", formaPagamento.getId(), formaPagamento.getDescricao());

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - salvar/incluir \n");

        formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao("Cheque");

        formaPagamento = formaPagamentoRepository.salvar(formaPagamento);

        System.out.printf("%d - %s \n", formaPagamento.getId(), formaPagamento.getDescricao());

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - salvar/alterar \n");

        formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);
        formaPagamento.setDescricao("Débito alterado");

        formaPagamento = formaPagamentoRepository.salvar(formaPagamento);

        System.out.printf("%d - %s \n", formaPagamento.getId(), formaPagamento.getDescricao());

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - excluir \n");

        formaPagamento = new FormaPagamento();
        formaPagamento.setId(2L);

        formaPagamentoRepository.remover(formaPagamento);

        System.out.println("\n******************************************************************");
        System.out.println("FormaPagamento - listar (novamente) \n");

        List<FormaPagamento> formaPagamentos2 = formaPagamentoRepository.listar();

        for (FormaPagamento formaPagamento2 : formaPagamentos2) {
            System.out.printf("%d - %s \n", formaPagamento2.getId(), formaPagamento2.getDescricao());
        }

        System.out.println("\n******************************************************************\n");

    }

}
