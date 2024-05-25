package br.com.ajvideira.algafood.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.model.Restaurante;

public class MockUtil {
    public static List<Restaurante> mockRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();

        restaurantes.add(mockRestaurante(1L, 1L));
        restaurantes.add(mockRestaurante(2L, 1L));
        restaurantes.add(mockRestaurante(3L, 2L));

        return restaurantes;
    }

    public static Restaurante mockRestaurante(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante " + restauranteId, new BigDecimal("0"),
                mockCozinha(cozinhaId));
    }

    public static Cozinha mockCozinha(Long cozinhaId) {
        return new Cozinha(cozinhaId, "Cozinha " + cozinhaId);
    }
}
