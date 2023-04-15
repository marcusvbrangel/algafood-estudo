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
        return cidadeRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Cidade de código %d não pode ser encontrada", id)));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade criar(Cidade cidade) {

        try {
            return cidadeRepository.save(cidade);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cidade de nome %s já existente", cidade.getNome()));
        }

    }

    public Cidade atualizar(Long id, Cidade cidade) {

        try {

            Cidade cidadeAtual = cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Cidade de código %d não pode ser encontrada", id)));

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cidadeRepository.save(cidadeAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cidade de nome %s já existente", cidade.getNome()));
        }

    }

    public void excluir(Long id) {

        cidadeRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Cidade de código %d não pode ser encontrada", id)));

        cidadeRepository.deleteById(id);

    }

}
