package com.mvbr.algafood.domain.service;

import com.mvbr.algafood.domain.exception.EntidadeEmUsoException;
import com.mvbr.algafood.domain.exception.EntidadeExistenteException;
import com.mvbr.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.mvbr.algafood.domain.model.Restaurante;
import com.mvbr.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código %d não pode ser encontrado";
    private static final String MSG_RESTAURANTE_EXISTENTE = "Restaurante de nome %s já existente";
    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser excluído, pois está em uso";

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante criar(Restaurante restaurante) {
        try {
            return restauranteRepository.save(restaurante);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_RESTAURANTE_EXISTENTE, restaurante.getNome()));
        }

    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {

        try {
            Restaurante restauranteAtual = this.buscar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return restauranteRepository.save(restauranteAtual);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeExistenteException(
                String.format(MSG_RESTAURANTE_EXISTENTE, restaurante.getNome()));
        }

    }

    public void excluir(Long id) {

        try {
            Restaurante restaurante = this.buscar(id);
            restauranteRepository.delete(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_RESTAURANTE_EM_USO, id));

        }

    }

}
