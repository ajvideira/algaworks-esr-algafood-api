package br.com.ajvideira.algafood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @CsvSource(value = {
            "k,1,15,2",
            "w,,,1",
            ",1,10,2",
            ",,,3",
            "dsdsd,1,10,0",
    })
    @ParameterizedTest()
    void shouldFindByParameters(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal,
            Integer expectedSize) {
        var response = restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);

        assertEquals(expectedSize, response.size());
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
