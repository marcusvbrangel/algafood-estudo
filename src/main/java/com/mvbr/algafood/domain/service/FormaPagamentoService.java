package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.FormaPagamento;
import com.mvbr.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    private static final String MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA = "Forma de Pagamento de código %d não pode ser encontrada";
    private static final String MSG_FORMA_DE_PAGAMENTO_EXISTENTE = "Forma de Pagamento de nome %s já existente";
    private static final String MSG_FORMA_DE_PAGAMENTO_EM_USO = "Forma de Pagamento de código %d não pode ser excluída, pois está em uso";

    public FormaPagamento buscar(Long id) {
        return formaPagamentoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA, id)));
    }

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento criar(FormaPagamento formaPagamento) {
        try {
            return formaPagamentoRepository.save(formaPagamento);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_FORMA_DE_PAGAMENTO_EXISTENTE, formaPagamento.getDescricao()));
        }

    }

    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {

        try {
            FormaPagamento formaPagamentoAtual = this.buscar(id);
            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
            return formaPagamentoRepository.save(formaPagamentoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_FORMA_DE_PAGAMENTO_EXISTENTE, formaPagamento.getDescricao()));
        }

    }

    public void excluir(Long id) {

        try {
            FormaPagamento formaPagamento = this.buscar(id);
            formaPagamentoRepository.delete(formaPagamento);

        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_FORMA_DE_PAGAMENTO_EM_USO, id));

        }

    }

}
