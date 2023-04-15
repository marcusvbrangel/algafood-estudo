package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento buscar(Long id) {
        return formaPagamentoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Forma de pagamento de código %d não pode ser encontrada", id)));
    }

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento criar(FormaPagamento formaPagamento) {

        try {
            return formaPagamentoRepository.save(formaPagamento);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Forma de pagamento de nome %s já existente", formaPagamento.getDescricao()));
        }

    }

    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {

        try {

            FormaPagamento formaPagamentoAtual = formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Forma de pagamento de código %d não pode ser encontrada", id)));

            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");

            return formaPagamentoRepository.save(formaPagamentoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Forma de pagamento de nome %s já existente", formaPagamento.getDescricao()));
        }

    }

    public void excluir(Long id) {

        formaPagamentoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Forma de pagamento de código %d não pode ser encontrada", id)));

        formaPagamentoRepository.deleteById(id);

    }

}
