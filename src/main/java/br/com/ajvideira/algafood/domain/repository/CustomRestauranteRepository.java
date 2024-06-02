package br.com.ajvideira.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.ajvideira.algafood.domain.model.Restaurante;

public interface CustomRestauranteRepository {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
