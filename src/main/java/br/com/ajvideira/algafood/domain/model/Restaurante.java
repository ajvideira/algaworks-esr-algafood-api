package br.com.ajvideira.algafood.domain.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Cozinha cozinha;

    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id", nullable = false))
    @ManyToMany
    private List<FormaPagamento> formasPagamento;

    @Embedded
    private Endereco endereco;

}
