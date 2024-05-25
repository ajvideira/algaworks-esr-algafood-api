package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.util.MockUtil;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @InjectMocks
    private RestauranteController restauranteController;

    @Mock
    private RestauranteRepository restauranteRepository;

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

}
