package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Permissao;
import com.mvbr.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    private static final String MSG_PERMISSAO_NAO_ENCONTRADA = "Permissão de código %d não pode ser encontrada";
    private static final String MSG_PERMISSAO_EXISTENTE = "Permissão de nome %s já existente";
    private static final String MSG_PERMISSAO_EM_USO = "Permissão de código %d não pode ser excluída, pois está em uso";

    public Permissao buscar(Long id) {
        return permissaoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PERMISSAO_NAO_ENCONTRADA, id)));
    }

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Permissao criar(Permissao permissao) {
        try {
            return permissaoRepository.save(permissao);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_PERMISSAO_EXISTENTE, permissao.getNome()));
        }

    }

    public Permissao atualizar(Long id, Permissao permissao) {

        try {
            Permissao permissaoAtual = this.buscar(id);
            BeanUtils.copyProperties(permissao, permissaoAtual, "id");
            return permissaoRepository.save(permissaoAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_PERMISSAO_EXISTENTE, permissao.getNome()));
        }

    }

    public void excluir(Long id) {

        try {
            Permissao permissao = this.buscar(id);
            permissaoRepository.delete(permissao);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_PERMISSAO_EM_USO, id));

        }

    }

}
