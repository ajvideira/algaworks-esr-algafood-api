package br.com.ajvideira.algafood.util.mock;

import br.com.ajvideira.algafood.domain.model.Estado;

public class EstadoMock {

    public static Estado mock(Long estadoId) {
        return new Estado(estadoId, "Estado " + estadoId);
    }

    public static Estado mockForSaveInOtherEntity(Long estadoId) {
        return new Estado(estadoId, null);
    }

}
