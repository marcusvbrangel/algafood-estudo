package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha buscar(Long id) {

        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                String.format("Cozinha de código %d não pode ser encontrada", id));
        }

        return cozinha.get();

    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha criar(Cozinha cozinha) {

        try {
            return cozinhaRepository.save(cozinha);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cozinha de nome %s já existente", cozinha.getNome()));
        }

    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {

        try {

            Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

            if (cozinhaAtual.isPresent()) {
                BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            } else {
                throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha de código %d não pode ser encontrada", id));
            }

            return cozinhaRepository.save(cozinhaAtual.get());

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Cozinha de nome %s já existente", cozinha.getNome()));
        }

    }

    public void excluir(Long id) {

        try {

            Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

            if (cozinha.isPresent()) {
                cozinhaRepository.deleteById(id);
            } else {
                throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha de código %d não pode ser encontrada", id));
            }

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format("Cozinha de código %d não pode ser excluída, pois está em uso", id));
        }
    }

}
