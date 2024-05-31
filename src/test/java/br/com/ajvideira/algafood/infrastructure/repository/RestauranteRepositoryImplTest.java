package br.com.ajvideira.algafood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.util.mock.RestauranteMock;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class RestauranteRepositoryImplTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void shouldFindAll() {
        assertEquals(3, restauranteRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertTrue(restauranteRepository.findById(1L).isPresent());
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertTrue(restauranteRepository.findById(10L).isEmpty());
    }

    @Test
    void shouldAdd() {
        var cozinhaId = 1L;

        var restaurante = RestauranteMock.mockForInsertWithFullCozinha(cozinhaId);

        restaurante = restauranteRepository.save(restaurante);

        assertNotNull(restaurante.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        var restaurante = RestauranteMock.mockForUpdateWithFullCozinha(restauranteId, cozinhaId);

        restaurante = restauranteRepository.save(restaurante);

        assertEquals(restauranteRepository.findById(restaurante.getId()).get(), restaurante);

    }

    @Test
    void shouldUpdateNonManagedEntity() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        var restaurante = RestauranteMock.mockForUpdateWithFullCozinha(restauranteId, cozinhaId);

        restaurante = restauranteRepository.save(restaurante);

        assertEquals(restauranteRepository.findById(restaurante.getId()).get(), restaurante);
    }

    @Test
    void shouldDeleteEntity() {
        restauranteRepository.deleteById(1L);

        assertTrue(restauranteRepository.findById(1L).isEmpty());
    }
}
