package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.domain.service.RestauranteService;
import br.com.ajvideira.algafood.util.MockUtil;

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
        when(restauranteRepository.findAll()).thenReturn(MockUtil.mockRestaurantes());

        var expected = ResponseEntity.ok(MockUtil.mockRestaurantes());

        var response = restauranteController.getAll();

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnRestauranteWhenExists() {
        when(restauranteRepository.findById(1L)).thenReturn(MockUtil.mockRestaurante(1L, 1L));

        var expected = ResponseEntity.ok(MockUtil.mockRestaurante(1L, 1L));

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
        var restauranteMockBeforeServiceSave = MockUtil.mockRestauranteForInsert(1L);
        var restauranteMockAfterServiceSave = MockUtil.mockRestaurante(4L, 1L);
        when(restauranteService.save(restauranteMockBeforeServiceSave)).thenReturn(restauranteMockAfterServiceSave);

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(MockUtil.mockRestaurante(4L, 1L));

        var restauranteRequest = MockUtil.mockRestauranteForInsert(1L);

        var response = restauranteController.create(restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenCozinhaNotExistsInCreate() {
        var restauranteMockBeforeServiceSave = MockUtil.mockRestauranteForInsertWithCozinhaId(10L);
        when(restauranteService.save(restauranteMockBeforeServiceSave)).thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var restauranteRequest = MockUtil.mockRestauranteForInsertWithCozinhaId(10L);

        var response = restauranteController.create(restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateRestauranteSuccessfully() {
        when(restauranteRepository.findById(1L)).thenReturn(MockUtil.mockRestaurante(1L, 1L));

        var restauranteMockBeforeServiceSave = MockUtil.mockRestaurante(1L, 1L);
        restauranteMockBeforeServiceSave.setNome("Restaurante Updated");

        var restauranteMockAfterServiceSave = MockUtil.mockRestaurante(1L, 1L);
        restauranteMockAfterServiceSave.setNome("Restaurante Updated");

        when(restauranteService.save(restauranteMockBeforeServiceSave)).thenReturn(restauranteMockAfterServiceSave);

        var restauranteExpected = MockUtil.mockRestaurante(1L, 1L);
        restauranteExpected.setNome("Restaurante Updated");

        var expected = ResponseEntity.ok(restauranteExpected);

        var restauranteRequest = MockUtil.mockRestauranteForInsertWithCozinhaId(1L);
        restauranteRequest.setNome("Restaurante Updated");

        var response = restauranteController.update(1L, restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenRestauranteNotExistsInUpdate() {
        when(restauranteRepository.findById(10L)).thenReturn(null);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var restauranteRequest = MockUtil.mockRestauranteForInsertWithCozinhaId(1L);

        var response = restauranteController.update(10L, restauranteRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenCozinhaNotExistsInUpdate() {
        when(restauranteRepository.findById(1L)).thenReturn(MockUtil.mockRestaurante(1L, 1L));

        var restauranteMockBeforeServiceSave = MockUtil.mockRestauranteForUpdateWithCozinhaId(1L, 10L);
        restauranteMockBeforeServiceSave.setNome("Restaurante updated");
        when(restauranteService.save(restauranteMockBeforeServiceSave)).thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var restauranteRequest = MockUtil.mockRestauranteForInsertWithCozinhaId(10L);
        restauranteRequest.setNome("Restaurante updated");

        var response = restauranteController.update(1L, restauranteRequest);

        assertEquals(expected, response);
    }

}
