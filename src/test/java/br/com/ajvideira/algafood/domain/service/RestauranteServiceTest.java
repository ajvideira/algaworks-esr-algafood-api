package br.com.ajvideira.algafood.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.util.MockUtil;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {

    @InjectMocks
    private RestauranteService restauranteService;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Test
    void shouldInsertRestaurante() {
        when(cozinhaRepository.findById(1L)).thenReturn(Optional.of(MockUtil.mockCozinha(1L)));

        when(restauranteRepository.save(MockUtil.mockRestauranteForInsert(1L)))
                .thenReturn(MockUtil.mockRestaurante(1L, 1L));

        var expected = MockUtil.mockRestaurante(1L, 1L);

        var restauranteRequest = MockUtil.mockRestauranteForInsertWithCozinhaId(1L);

        var response = restauranteService.save(restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateRestaurante() {
        var restauranteBeforePreRepositorySave = MockUtil.mockRestaurante(1L, 1L);
        restauranteBeforePreRepositorySave.setNome("Restaurante updated");

        var restauranteMockAfterRepositorySave = MockUtil.mockRestaurante(1L, 1L);
        restauranteMockAfterRepositorySave.setNome("Restaurante updated");

        var expected = MockUtil.mockRestaurante(1L, 1L);
        expected.setNome("Restaurante updated");

        when(cozinhaRepository.findById(1L)).thenReturn(Optional.of(MockUtil.mockCozinha(1L)));

        when(restauranteRepository.save(
                restauranteBeforePreRepositorySave))
                .thenReturn(restauranteMockAfterRepositorySave);

        var restauranteRequest = MockUtil.mockRestauranteForUpdateWithCozinhaId(1L, 1L);
        restauranteRequest.setNome("Restaurante updated");

        var response = restauranteService.save(restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldThrowEntityNotFoundWhenCozinhaNotFound() {
        when(cozinhaRepository.findById(1L)).thenReturn(Optional.empty());

        var restauranteRequest = MockUtil.mockRestauranteForUpdateWithCozinhaId(1L, 1L);
        restauranteRequest.setNome("Restaurante updated");

        assertThrows(EntityNotFoundException.class, () -> restauranteService.save(restauranteRequest));
    }

    @Test
    void shouldDeleteWithSuccess() {
        var restauranteId = 1L;

        doNothing().when(restauranteRepository).delete(restauranteId);

        assertDoesNotThrow(() -> restauranteService.delete(restauranteId));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var restauranteId = 1L;

        doThrow(DataIntegrityViolationException.class).when(restauranteRepository).delete(restauranteId);

        assertThrows(EntityInUseException.class, () -> restauranteService.delete(restauranteId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var restauranteId = 1L;

        doThrow(EmptyResultDataAccessException.class).when(restauranteRepository).delete(restauranteId);

        assertThrows(EntityNotFoundException.class, () -> restauranteService.delete(restauranteId));
    }

}
