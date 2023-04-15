package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Permissao;
import com.mvbr.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscar(Long id) {
        return permissaoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Permissão de código %d não pode ser encontrada", id)));
    }

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Permissao criar(Permissao permissao) {

        try {
            return permissaoRepository.save(permissao);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Permissão de nome %s já existente", permissao.getNome()));
        }

    }

    public Permissao atualizar(Long id, Permissao permissao) {

        try {

            Permissao permissaoAtual = permissaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Permissão de código %d não pode ser encontrada", id)));

            BeanUtils.copyProperties(permissao, permissaoAtual, "id");

            return permissaoRepository.save(permissaoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Permissão de nome %s já existente", permissao.getNome()));
        }

    }

    public void excluir(Long id) {

        permissaoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Permissão de código %d não pode ser encontrada", id)));

        permissaoRepository.deleteById(id);

    }

}
