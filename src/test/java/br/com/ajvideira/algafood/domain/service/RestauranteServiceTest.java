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

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.repository.FormaPagamentoRepository;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.util.mock.CidadeMock;
import br.com.ajvideira.algafood.util.mock.CozinhaMock;
import br.com.ajvideira.algafood.util.mock.FormaPagamentoMock;
import br.com.ajvideira.algafood.util.mock.RestauranteMock;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {

    @InjectMocks
    private RestauranteService restauranteService;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private FormaPagamentoRepository formaPagamentoRepository;

    @Test
    void shouldInsertRestaurante() {
        var cozinhaId = 1L;
        var restauranteId = 1L;
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        when(formaPagamentoRepository.findById(1L)).thenReturn(Optional.of(FormaPagamentoMock.mock(1L)));
        when(formaPagamentoRepository.findById(2L)).thenReturn(Optional.of(FormaPagamentoMock.mock(2L)));
        when(formaPagamentoRepository.findById(3L)).thenReturn(Optional.of(FormaPagamentoMock.mock(3L)));

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
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        when(formaPagamentoRepository.findById(1L)).thenReturn(Optional.of(FormaPagamentoMock.mock(1L)));
        when(formaPagamentoRepository.findById(2L)).thenReturn(Optional.of(FormaPagamentoMock.mock(2L)));
        when(formaPagamentoRepository.findById(3L)).thenReturn(Optional.of(FormaPagamentoMock.mock(3L)));

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
        var cozinhaId = 1L;

        when(restauranteRepository.findById(restauranteId))
                .thenReturn(Optional.of(RestauranteMock.mock(restauranteId, cozinhaId)));

        doNothing().when(restauranteRepository).delete(RestauranteMock.mock(restauranteId, cozinhaId));

        assertDoesNotThrow(() -> restauranteService.delete(restauranteId));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var restauranteId = 1L;
        var cozinhaId = 1L;

        when(restauranteRepository.findById(restauranteId))
                .thenReturn(Optional.of(RestauranteMock.mock(restauranteId, cozinhaId)));

        doThrow(DataIntegrityViolationException.class).when(restauranteRepository).delete(
                RestauranteMock.mock(restauranteId, cozinhaId));

        assertThrows(EntityInUseException.class, () -> restauranteService.delete(restauranteId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var restauranteId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> restauranteService.delete(restauranteId));
    }

}
