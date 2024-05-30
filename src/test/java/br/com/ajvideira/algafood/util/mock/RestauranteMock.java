package br.com.ajvideira.algafood.util.mock;

import java.math.BigDecimal;
import java.util.List;

import br.com.ajvideira.algafood.domain.model.Restaurante;

public class RestauranteMock {

    public static Restaurante mock(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante " + restauranteId,
                BigDecimal.ONE, CozinhaMock.mock(cozinhaId));
    }

    public static Restaurante mockForInsertWithCozinhaId(Long cozinhaId) {
        return new Restaurante(null, "New Restaurante",
                BigDecimal.ONE, CozinhaMock.mockForSaveInOtherEntity(cozinhaId));
    }

    public static Restaurante mockForInsertWithFullCozinha(Long cozinhaId) {
        return new Restaurante(null, "New Restaurante", BigDecimal.ONE, CozinhaMock.mock(cozinhaId));
    }

    public static Restaurante mockForUpdateWithCozinhaId(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante updated", BigDecimal.ONE,
                CozinhaMock.mockForSaveInOtherEntity(cozinhaId));
    }

    public static Restaurante mockForUpdateWithFullCozinha(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante updated", BigDecimal.ONE, CozinhaMock.mock(cozinhaId));
    }

    public static Restaurante mockForUpdateWithoutIdAndWithCozinhaId(Long cozinhaId) {
        return new Restaurante(null, "Restaurante updated",
                BigDecimal.ONE, CozinhaMock.mockForSaveInOtherEntity(cozinhaId));
    }

    public static List<Restaurante> mockList() {
        return List.of(mock(1L, 1L), mock(2L, 2L), mock(3L, 3L));
    }

}
