package com.mvbr.algafood.domain.repository;

import com.mvbr.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void excluir(Estado estado);
}
