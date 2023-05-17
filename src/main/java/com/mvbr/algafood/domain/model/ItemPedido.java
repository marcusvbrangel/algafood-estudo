package com.mvbr.algafood.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoID itemPedidoID;

    private int quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    private String observacao;

}
