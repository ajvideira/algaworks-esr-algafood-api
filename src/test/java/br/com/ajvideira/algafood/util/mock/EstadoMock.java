package br.com.ajvideira.algafood.util.mock;

import java.util.List;

import br.com.ajvideira.algafood.domain.model.Estado;

public class EstadoMock {

    public static Estado mock(Long estadoId) {
        return new Estado(estadoId, "Estado " + estadoId);
    }

    public static Estado mockForInsert() {
        return new Estado(null, "New Estado");
    }

    public static Estado mockForUpdate(Long estadoId) {
        return new Estado(estadoId, "Estado updated");
    }

    public static Estado mockForUpdateWithoutId() {
        return new Estado(null, "Estado updated");
    }

    public static List<Estado> mockList() {
        return List.of(mock(1L), mock(2L), mock(3L));
    }

    public static Estado mockForSaveInOtherEntity(Long estadoId) {
        return new Estado(estadoId, null);
    }

}
