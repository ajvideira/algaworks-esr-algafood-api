package br.com.ajvideira.algafood.util.mock;

import br.com.ajvideira.algafood.domain.model.Endereco;

public class EnderecoMock {

    public static Endereco mock() {
        return new Endereco("99999999", "Endereço 1", "1000", "10", "Centro", CidadeMock.mock(1L, 1L));
    }

}
