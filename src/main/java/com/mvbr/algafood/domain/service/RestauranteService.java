package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Restaurante de código %d não pode ser encontrado", id)));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante criar(Restaurante restaurante) {

        try {
            return restauranteRepository.save(restaurante);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Restaurante de nome %s já existente", restaurante.getNome()));
        }

    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {

        try {

            Restaurante restauranteAtual = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Restaurante de código %d não pode ser encontrado", id)));

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                "id", "formasPagamentto", "endereco", "dataCadastro");

            return restauranteRepository.save(restauranteAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Restaurante de nome %s já existente", restaurante.getNome()));
        }

    }

    public void excluir(Long id) {

        restauranteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Restaurante de código %d não pode ser encontrado", id)));

        restauranteRepository.deleteById(id);

    }

}
