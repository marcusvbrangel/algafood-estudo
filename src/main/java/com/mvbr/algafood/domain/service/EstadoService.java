package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
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

        Estado estado = estadoRepository.buscar(id);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Estado de código %d não pode ser encontrado", id));
        }

        return estado;

    }

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado criar(Estado estado) {

        // Aula: 4.30...
        // Todo: validar se o nome do Estado foi preenchido...

        try {
            return estadoRepository.salvar(estado);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeExistenteException(
                String.format("Estado de nome %s já existente", estado.getNome()));
        }

    }

    public Estado atualizar(Long id, Estado estado) {

        // Aula: 4.30...
        // Todo: validar se o nome do Estado foi preenchido...

        try {

            Estado estadoAtual = estadoRepository.buscar(id);

            BeanUtils.copyProperties(estado, estadoAtual, "id");

            return estadoRepository.salvar(estadoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Estado de nome %s já existente", estado.getNome()));
        }

    }

    public void excluir(Long id) {

        try {

            Estado estado = estadoRepository.buscar(id);

            estadoRepository.excluir(estado);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser excluído, pois está em uso", id));
        }
    }

}
