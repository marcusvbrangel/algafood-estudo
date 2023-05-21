package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.CidadeNaoEncontradaException;
import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.model.Cidade;
import com.mvbr.algafood.domain.model.Estado;
import com.mvbr.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    private static final String MSG_CIDADE_EXISTENTE = "Cidade de nome %s já existente";
    private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser excluída, pois está em uso";

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id)
            .orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade criar(Cidade cidade) {

        try {
            Long estadoId = cidade.getEstado().getId();

            Estado estado = estadoService.buscar(estadoId);

            cidade.setEstado(estado);

            // todo: tratar exceptions para estado e cidade (bad_request)...
            // 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios

            return cidadeRepository.save(cidade);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_CIDADE_EXISTENTE, cidade.getNome()));
        }

    }

    public Cidade atualizar(Long id, Cidade cidade) {

        try {
            Cidade cidadeAtual = this.buscar(id);

            Estado estado = estadoService.buscar(cidade.getEstado().getId());
            cidade.setEstado(estado);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            // todo: tratar exceptions para estado e cidade (bad_request)...
            // 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios

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

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_CIDADE_EM_USO, id));
        }

    }

}
