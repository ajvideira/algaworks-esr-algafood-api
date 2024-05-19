package br.com.ajvideira.algafood.api.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Restaurante {

    @Id
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

}
