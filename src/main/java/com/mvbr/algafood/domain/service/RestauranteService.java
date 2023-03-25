package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
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

        Restaurante restaurante = restauranteRepository.buscar(id);

        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Restaurante de código %d não pode ser encontrado", id));
        }

        return restaurante;

    }

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante criar(Restaurante restaurante) {

        // Aula: 4.30...
        // Todo: validar se o nome do restaurante foi preenchido...
        // Todo: validar se a taxa do frete foi preenchido...
        // Todo: validar se o codigo da cozinha foi preenchido...
        // Todo: validar se o codigo da cozinha existe...

        try {
            return restauranteRepository.salvar(restaurante);

        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeExistenteException(
                String.format("Restaurante de nome %s já existente", restaurante.getNome()));
        }

    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {

        // Aula: 4.30...
        // Todo: validar se o nome do restaurante foi preenchido...
        // Todo: validar se a taxa do frete foi preenchido...
        // Todo: validar se o codigo da cozinha foi preenchido...
        // Todo: validar se o codigo da cozinha existe...

        try {

            Restaurante restauranteAtual = restauranteRepository.buscar(id);

            if (restauranteAtual == null) {
                throw new EntidadeNaoEncontradaException(
                    String.format("Restaurante de código %d não pode ser encontrado", id));
            }

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

            return restauranteRepository.salvar(restauranteAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format("Restaurante de nome %s já existente", restaurante.getNome()));
        }

    }

    public void excluir(Long id) {

        Restaurante restaurante = restauranteRepository.buscar(id);

        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Restaurante de código %d não pode ser encontrado", id));
        }

        restauranteRepository.excluir(restaurante);

    }

}
