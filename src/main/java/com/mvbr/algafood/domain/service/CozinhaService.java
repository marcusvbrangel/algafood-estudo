package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha buscar(Long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Cozinha de código %d não pode ser encontrada", id));
        }

        return cozinha;

    }

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha criar(Cozinha cozinha) {

        // Aula: 4.30...
        // Todo: validar se o nome da cozinha foi preenchido...

        try {
            return cozinhaRepository.salvar(cozinha);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeExistenteException(
                String.format("Cozinha de nome %s já existente", cozinha.getNome()));
        }

    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {

        // Aula: 4.30...
        // Todo: validar se o nome da cozinha foi preenchido...

        try {

            Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

            if (cozinhaAtual == null) {
                throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha de código %d não pode ser encontrada", id));
            }

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            return cozinhaRepository.salvar(cozinhaAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cozinha de nome %s já existente", cozinha.getNome()));
        }

    }

    public void excluir(Long id) {

        try {

            Cozinha cozinha = cozinhaRepository.buscar(id);

            if (cozinha == null) {
                throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha de código %d não pode ser encontrada", id));
            }

            cozinhaRepository.excluir(cozinha);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser excluída, pois está em uso", id));
        }
    }

}
