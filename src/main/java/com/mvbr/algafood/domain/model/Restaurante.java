package com.mvbr.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = true)
    private BigDecimal taxaFrete;

    @ManyToOne
    private Cozinha cozinha;

}
