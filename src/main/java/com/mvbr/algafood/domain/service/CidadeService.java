package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cidade;
import com.mvbr.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscar(Long id) {

        Cidade cidade = cidadeRepository.buscar(id);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Cidade de código %d não pode ser encontrada", id));
        }

        return cidade;

    }

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade criar(Cidade cidade) {

        // Aula: 4.30...
        // Todo: validar se o nome da Cidade foi preenchido...
        // Todo: validar se o codigo do estado foi preenchido...
        // Todo: validar se o codigo do estado existe...

        try {
            return cidadeRepository.salvar(cidade);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeExistenteException(
                String.format("Cidade de nome %s já existente", cidade.getNome()));
        }

    }

    public Cidade atualizar(Long id, Cidade cidade) {

        // Aula: 4.30...
        // Todo: validar se o nome dd cidade foi preenchido...
        // Todo: validar se o codigo da Cidade foi preenchido...
        // Todo: validar se o codigo da Cidade existe...

        try {

            Cidade cidadeAtual = cidadeRepository.buscar(id);

            if (cidadeAtual == null) {
                throw new EntidadeNaoEncontradaException(
                    String.format("Cidade de código %d não pode ser encontrada", id));
            }

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cidadeRepository.salvar(cidadeAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cidade de nome %s já existente", cidade.getNome()));
        }

    }

    public void excluir(Long id) {

        Cidade cidade = cidadeRepository.buscar(id);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Cidade de código %d não pode ser encontrada", id));
        }

        cidadeRepository.excluir(cidade);

    }

}
