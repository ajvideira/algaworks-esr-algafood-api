package br.com.ajvideira.algafood.util.mock;

import java.util.List;

import br.com.ajvideira.algafood.domain.model.Cozinha;

public class CozinhaMock {

    public static Cozinha mock(Long cozinhaId) {
        return new Cozinha(cozinhaId, "Cozinha " + cozinhaId);
    }

    public static Cozinha mockForInsert(Long cozinhaId) {
        return new Cozinha(null, "New Cozinha");
    }

    public static Cozinha mockForUpdate(Long cozinhaId) {
        return new Cozinha(cozinhaId, "Cozinha updated");
    }

    public static Cozinha mockForUpdateWithoutId(Long cozinhaId) {
        return new Cozinha(null, "Cozinha updated");
    }

    public static List<Cozinha> mockList() {
        return List.of(mock(1L), mock(2L), mock(3L));
    }

    public static Cozinha mockForSaveInOtherEntity(Long cozinhaId) {
        return new Cozinha(cozinhaId, null);
    }

}
