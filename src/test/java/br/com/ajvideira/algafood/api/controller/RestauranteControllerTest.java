package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.domain.service.RestauranteService;
import br.com.ajvideira.algafood.util.mock.RestauranteMock;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @InjectMocks
    private RestauranteController restauranteController;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteService restauranteService;

    @Test
    void shouldReturnAllRestaurantes() {
        when(restauranteRepository.findAll()).thenReturn(RestauranteMock.mockList());

        var expected = ResponseEntity.ok(RestauranteMock.mockList());

        var response = restauranteController.getAll();

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnRestauranteWhenExists() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        var expected = ResponseEntity.ok(RestauranteMock.mock(restauranteId, cozinhaId));

        var response = restauranteController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenRestauranteNotExists() {
        when(restauranteRepository.findById(1L)).thenReturn(null);

        var expected = ResponseEntity.notFound().build();

        var response = restauranteController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldCreateRestauranteSuccessfully() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(restauranteService.save(RestauranteMock.mockForInsertWithCozinhaId(cozinhaId)))
                .thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(RestauranteMock.mock(restauranteId, cozinhaId));

        var response = restauranteController.create(RestauranteMock.mockForInsertWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenCozinhaNotExistsInCreate() {
        var cozinhaId = 1L;

        when(restauranteService.save(RestauranteMock.mockForInsertWithCozinhaId(cozinhaId)))
                .thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = restauranteController.create(RestauranteMock.mockForInsertWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateRestauranteSuccessfully() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(restauranteRepository.findById(1L)).thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        when(restauranteService.save(RestauranteMock.mockForUpdateWithCozinhaId(restauranteId, cozinhaId)))
                .thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        var expected = ResponseEntity.ok(RestauranteMock.mock(restauranteId, cozinhaId));

        var response = restauranteController.update(restauranteId,
                RestauranteMock.mockForUpdateWithoutIdAndWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenRestauranteNotExistsInUpdate() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(null);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = restauranteController.update(restauranteId,
                RestauranteMock.mockForUpdateWithoutIdAndWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenCozinhaNotExistsInUpdate() {
        var cozinhaId = 1L;
        var restauranteId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(RestauranteMock.mock(restauranteId, cozinhaId));

        when(restauranteService.save(RestauranteMock.mockForUpdateWithCozinhaId(restauranteId, cozinhaId)))
                .thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = restauranteController.update(restauranteId,
                RestauranteMock.mockForUpdateWithoutIdAndWithCozinhaId(cozinhaId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNoContentWhenCallDeleteWithExistentCozinha() {
        var expected = ResponseEntity.noContent().build();

        doNothing().when(restauranteService).delete(1L);

        var restauranteIdRequest = 1L;

        var response = restauranteController.delete(restauranteIdRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCallDeleteWithCozinhaInUse() {
        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        doThrow(EntityInUseException.class).when(restauranteService).delete(1L);

        var restauranteIdRequest = 1L;

        var response = restauranteController.delete(restauranteIdRequest);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundtWhenCallDeleteWithNonExistentCozinha() {
        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        doThrow(EntityNotFoundException.class).when(restauranteService).delete(1L);

        var restauranteIdRequest = 1L;

        var response = restauranteController.delete(restauranteIdRequest);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
