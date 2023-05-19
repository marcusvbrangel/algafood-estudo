package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
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

    private static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade de código %d não pode ser encontrada";
    private static final String MSG_CIDADE_EXISTENTE = "Cidade de nome %s já existente";
    private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser excluída, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade criar(Cidade cidade) {
        try {
            return cidadeRepository.save(cidade);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_CIDADE_EXISTENTE, cidade.getNome()));
        }

    }

    public Cidade atualizar(Long id, Cidade cidade) {

        try {
            Cidade cidadeAtual = this.buscar(id);
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return cidadeRepository.save(cidadeAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_CIDADE_EXISTENTE, cidade.getNome()));
        }

    }

    public void excluir(Long id) {

        try {
            Cidade cidade = this.buscar(id);
            cidadeRepository.delete(cidade);

        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(MSG_CIDADE_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_CIDADE_EM_USO, id));

        }

    }

}
