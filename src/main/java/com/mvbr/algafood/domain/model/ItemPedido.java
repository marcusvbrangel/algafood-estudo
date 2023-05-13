package com.mvbr.algafood.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private ItemPedidoID itemPedidoID;

    private int quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    private String observacao;

}
