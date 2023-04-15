package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscar(Long id) {
        return estadoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Estado de código %d não pode ser encontrado", id)));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado criar(Estado estado) {

        try {
            return estadoRepository.save(estado);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Estado de nome %s já existente", estado.getNome()));
        }

    }

    public Estado atualizar(Long id, Estado estado) {

        try {

            Estado estadoAtual = estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Estado de código %d não pode ser encontrado", id)));

            BeanUtils.copyProperties(estado, estadoAtual, "id");

            return estadoRepository.save(estadoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Estado de nome %s já existente", estado.getNome()));
        }

    }

    public void excluir(Long id) {

        try {

            estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Estado de código %d não pode ser encontrado", id)));

            estadoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format("Estado de código %d não pode ser excluído, pois está em uso", id));
        }
    }

}
