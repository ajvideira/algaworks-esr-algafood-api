package br.com.ajvideira.algafood.util.mock;

import java.util.List;

import br.com.ajvideira.algafood.domain.model.Cidade;

public class CidadeMock {

    public static Cidade mock(Long cidadeId, Long estadoId) {
        return new Cidade(cidadeId, "Cidade " + cidadeId, EstadoMock.mock(estadoId));
    }

    public static Cidade mockForInsertWithEstadoId(Long estadoId) {
        return new Cidade(null, "New Cidade", EstadoMock.mockForSaveInOtherEntity(estadoId));
    }

    public static Cidade mockForInsertWithFullEstado(Long estadoId) {
        return new Cidade(null, "New Cidade", EstadoMock.mock(estadoId));
    }

    public static Cidade mockForUpdateWithEstadoId(Long cidadeId, Long estadoId) {
        return new Cidade(cidadeId, "Cidade updated", EstadoMock.mockForSaveInOtherEntity(estadoId));
    }

    public static Cidade mockForUpdateWithFullEstado(Long cidadeId, Long estadoId) {
        return new Cidade(cidadeId, "Cidade updated", EstadoMock.mock(estadoId));
    }

    public static Cidade mockForUpdateWithoutIdAndWithEstadoId(Long estadoId) {
        return new Cidade(null, "Cidade updated", EstadoMock.mockForSaveInOtherEntity(estadoId));
    }

    public static List<Cidade> mockList() {
        return List.of(mock(1L, 1L), mock(2L, 2L), mock(3L, 3L));
    }

}
