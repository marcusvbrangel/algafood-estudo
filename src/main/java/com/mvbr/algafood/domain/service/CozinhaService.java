package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Cozinha;
import com.mvbr.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha de código %d não pode ser encontrada";
    private static final String MSG_COZINHA_EXISTENTE = "Cozinha de nome %s já existente";
    private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser excluída, pois está em uso";

    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha criar(Cozinha cozinha) {

        try {
            return cozinhaRepository.save(cozinha);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_COZINHA_EXISTENTE, cozinha.getNome()));
        }

    }

//    public Cozinha atualizar(Long id, Cozinha cozinha) {
//
//        try {
//
//            Cozinha cozinhaAtual = cozinhaRepository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                    String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
//
//            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
//
//            return cozinhaRepository.save(cozinhaAtual);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeExistenteException(
//                String.format(MSG_COZINHA_EXISTENTE, cozinha.getNome()));
//        }
//
//    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {

        try {
            Cozinha cozinhaAtual = this.buscar(id);
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return cozinhaRepository.save(cozinhaAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_COZINHA_EXISTENTE, cozinha.getNome()));
        }

    }

//    public void excluir(Long id) {
//
//        try {
//
//            cozinhaRepository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                    String.format("Cozinha de código %d não pode ser encontrada", id)));
//
//            cozinhaRepository.deleteById(id);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//                String.format("Cozinha de código %d não pode ser excluída, pois está em uso", id));
//        }
//    }

//    public void excluir(Long id) {
//
//        try {
//            cozinhaRepository.deleteById(id);
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntidadeNaoEncontradaException(
//                String.format("Cozinha de código %d não pode ser encontrada", id));
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//                String.format("Cozinha de código %d não pode ser excluída, pois está em uso", id));
//
//        }
//    }

//    public void excluir(Long id) {
//
//        try {
//
//            Cozinha cozinha = this.buscar(id);
//
//            cozinhaRepository.delete(cozinha);
//
//        } catch (EntidadeNaoEncontradaException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("Cozinha de código %d não pode ser encontrada", id));
//
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                String.format("Cozinha de código %d não pode ser excluída, pois está em uso", id));
//
//        }
//    }

    public void excluir(Long id) {

        try {
            Cozinha cozinha = this.buscar(id);
            cozinhaRepository.delete(cozinha);

        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(MSG_COZINHA_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_COZINHA_EM_USO, id));
        }
    }

}
