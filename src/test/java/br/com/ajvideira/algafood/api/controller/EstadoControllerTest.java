package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
import br.com.ajvideira.algafood.domain.service.EstadoService;

@ExtendWith(MockitoExtension.class)
class EstadoControllerTest {

    @InjectMocks
    private EstadoController estadoController;

    @Mock
    private EstadoRepository estadoRepository;

    @Mock
    private EstadoService estadoService;

    @Test
    void shouldReturnAllEstados() {
        var estadosMock = List.of(new Estado(1L, "São Paulo"), new Estado(2L, "Rio de Janeiro"),
                new Estado(3L, "Minas Gerais"));

        when(estadoRepository.findAll()).thenReturn(estadosMock);

        var expected = ResponseEntity.ok(estadosMock);

        var response = estadoController.getAll();

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnEstadoWhenExists() {
        var expected = ResponseEntity.ok(new Estado(1L, "Rio de Janeiro"));

        when(estadoRepository.findById(1L)).thenReturn(new Estado(1L, "Rio de Janeiro"));

        var response = estadoController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenEstadoNotExists() {
        var expected = ResponseEntity.notFound().build();

        when(estadoRepository.findById(1L)).thenReturn(null);

        var response = estadoController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnCreatedWhenCallCreateWithValidData() {
        var expected = ResponseEntity.status(HttpStatus.CREATED).body(new Estado(1L, "Rio de Janeiro"));

        when(estadoService.save(new Estado(null, "Rio de Janeiro"))).thenReturn(new Estado(1L, "Rio de Janeiro"));

        var response = estadoController.create(new Estado(null, "Rio de Janeiro"));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCallUpdateWithNonExistentCozinhaId() {
        var expected = ResponseEntity.notFound().build();

        when(estadoRepository.findById(1L)).thenReturn(null);

        var response = estadoController.update(1L, new Estado(null, "Rio de Janeiro"));

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteEstadoSuccessfully() {
        var expected = ResponseEntity.noContent().build();

        doNothing().when(estadoService).delete(1L);

        var cozinhaIdRequest = 1L;

        var response = estadoController.delete(cozinhaIdRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCallDeleteWithCozinhaInUse() {
        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        doThrow(EntityInUseException.class).when(estadoService).delete(1L);

        var response = estadoController.delete(1L);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundtWhenCallDeleteWithNonExistentCozinha() {
        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        doThrow(EntityNotFoundException.class).when(estadoService).delete(1L);

        var response = estadoController.delete(1L);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
