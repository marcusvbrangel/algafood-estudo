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

    private static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado de código %d não pode ser encontrado";
    private static final String MSG_ESTADO_EXISTENTE = "Estado de nome %s já existente";
    private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser excluído, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscar(Long id) {
        return estadoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado criar(Estado estado) {
        try {
            return estadoRepository.save(estado);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_ESTADO_EXISTENTE, estado.getNome()));
        }

    }

    public Estado atualizar(Long id, Estado estado) {

        try {
            Estado estadoAtual = this.buscar(id);
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            return estadoRepository.save(estadoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_ESTADO_EXISTENTE, estado.getNome()));
        }

    }

    public void excluir(Long id) {

        try {
            Estado estado = this.buscar(id);
            estadoRepository.delete(estado);

        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(MSG_ESTADO_NAO_ENCONTRADO, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_ESTADO_EM_USO, id));

        }

    }

}
