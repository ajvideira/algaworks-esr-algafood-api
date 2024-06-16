package br.com.ajvideira.algafood.util.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.ajvideira.algafood.domain.model.Restaurante;

public class RestauranteMock {

    public static Restaurante mock(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante " + restauranteId,
                BigDecimal.ONE, CozinhaMock.mock(cozinhaId), FormaPagamentoMock.mockList(), EnderecoMock.mock(),
                LocalDateTime.of(2024, 1, 1, 0, 0, 0), LocalDateTime.of(2024, 1, 1, 0, 0, 0));
    }

    public static Restaurante mockForInsertWithOtherEntitiesIds(Long cozinhaId) {
        return new Restaurante(null, "New Restaurante",
                BigDecimal.ONE, CozinhaMock.mockForSaveInOtherEntity(cozinhaId),
                FormaPagamentoMock.mockListForSaveInOtherEntity(),
                EnderecoMock.mock(), null, null);
    }

    public static Restaurante mockForInsertWithFullEntities(Long cozinhaId) {
        return new Restaurante(null, "New Restaurante", BigDecimal.ONE, CozinhaMock.mock(cozinhaId),
                FormaPagamentoMock.mockList(), EnderecoMock.mock(), null, null);
    }

    public static Restaurante mockForUpdateWithOtherEntitiesIds(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante updated", BigDecimal.ONE,
                CozinhaMock.mockForSaveInOtherEntity(cozinhaId), FormaPagamentoMock.mockListForSaveInOtherEntity(),
                EnderecoMock.mock(), LocalDateTime.of(2024, 1, 1, 0, 0, 0), null);
    }

    public static Restaurante mockForUpdateWithFullEntities(Long restauranteId, Long cozinhaId) {
        return new Restaurante(restauranteId, "Restaurante updated", BigDecimal.ONE, CozinhaMock.mock(cozinhaId),
                FormaPagamentoMock.mockList(), EnderecoMock.mock(), LocalDateTime.of(2024, 1, 1, 0, 0, 0), null);
    }

    public static Restaurante mockForUpdateWithoutIdAndWithOtherEntitiesIds(Long cozinhaId) {
        return new Restaurante(null, "Restaurante updated",
                BigDecimal.ONE, CozinhaMock.mockForSaveInOtherEntity(cozinhaId),
                FormaPagamentoMock.mockListForSaveInOtherEntity(),
                EnderecoMock.mock(), null, null);
    }

    public static List<Restaurante> mockList() {
        return List.of(mock(1L, 1L), mock(2L, 2L), mock(3L, 3L));
    }

}
