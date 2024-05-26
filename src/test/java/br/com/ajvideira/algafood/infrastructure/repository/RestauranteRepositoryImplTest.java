package br.com.ajvideira.algafood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;

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
        assertNotNull(restauranteRepository.findById(1L));
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertNull(restauranteRepository.findById(10L));
    }

    @Test
    void shouldAdd() {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Nebraska");
        restaurante.setCozinha(cozinha);
        restaurante.setTaxaFrete(BigDecimal.ZERO);

        restaurante = restauranteRepository.save(restaurante);

        assertNotNull(restaurante.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Restaurante restaurante = restauranteRepository.findById(1L);
        restaurante.setNome("Madagascar");

        restaurante = restauranteRepository.save(restaurante);

        assertEquals(restauranteRepository.findById(restaurante.getId()), restaurante);

    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Peppo");
        restaurante.setTaxaFrete(new BigDecimal("12.90"));
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        restaurante = restauranteRepository.save(restaurante);

        assertEquals(restauranteRepository.findById(restaurante.getId()), restaurante);
    }

    @Test
    void shouldDeleteEntity() {
        restauranteRepository.delete(1L);

        assertNull(restauranteRepository.findById(1L));
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> restauranteRepository.delete(10L));
    }

}
