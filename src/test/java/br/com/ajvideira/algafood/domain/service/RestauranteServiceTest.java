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
import br.com.ajvideira.algafood.util.mock.CozinhaMock;
import br.com.ajvideira.algafood.util.mock.RestauranteMock;

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
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        when(restauranteRepository.save(RestauranteMock.mockForInsertWithFullCozinha(cozinhaId)))
                .thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        var expected = RestauranteMock.mock(cozinhaId, restauranteId);

        var response = restauranteService.save(RestauranteMock.mockForInsertWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateRestaurante() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        when(restauranteRepository.save(
                RestauranteMock.mockForUpdateWithFullCozinha(restauranteId, cozinhaId)))
                .thenReturn(RestauranteMock.mock(1L, cozinhaId));

        var expected = RestauranteMock.mock(restauranteId, cozinhaId);

        var response = restauranteService.save(RestauranteMock.mockForUpdateWithCozinhaId(restauranteId, cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldThrowEntityNotFoundWhenCozinhaNotFound() {
        var cozinhaId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.empty());

        var restauranteRequest = RestauranteMock.mockForUpdateWithoutIdAndWithCozinhaId(cozinhaId);

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
