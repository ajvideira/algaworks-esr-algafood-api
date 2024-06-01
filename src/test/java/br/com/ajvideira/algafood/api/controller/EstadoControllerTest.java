package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
import br.com.ajvideira.algafood.domain.service.EstadoService;
import br.com.ajvideira.algafood.util.mock.EstadoMock;

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
        when(estadoRepository.findAll()).thenReturn(EstadoMock.mockList());

        var expected = ResponseEntity.ok(EstadoMock.mockList());

        var response = estadoController.getAll();

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnEstadoWhenExists() {
        var estadoId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.of(EstadoMock.mock(estadoId)));

        var expected = ResponseEntity.ok(EstadoMock.mock(estadoId));

        var response = estadoController.getById(estadoId);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenEstadoNotExists() {
        var estadoId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.empty());

        var expected = ResponseEntity.notFound().build();

        var response = estadoController.getById(estadoId);

        assertEquals(expected, response);
    }

    @Test
    void shouldCreateEstadoSuccessfully() {
        var estadoId = 1L;

        when(estadoService.save(EstadoMock.mockForInsert())).thenReturn(EstadoMock.mock(estadoId));

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(EstadoMock.mock(estadoId));

        var response = estadoController.create(EstadoMock.mockForInsert());

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateEstadoSuccessfully() {
        var estadoId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.of(EstadoMock.mock(estadoId)));
        when(estadoService.save(EstadoMock.mockForUpdate(estadoId))).thenReturn(EstadoMock.mock(estadoId));

        var expected = ResponseEntity.status(HttpStatus.OK).body(EstadoMock.mock(estadoId));

        var response = estadoController.update(estadoId, EstadoMock.mockForUpdateWithoutId());

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCallUpdateWithNonExistentEstadoId() {
        var estadoId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.empty());

        var expected = ResponseEntity.notFound().build();

        var response = estadoController.update(estadoId, EstadoMock.mockForUpdateWithoutId());

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteEstadoSuccessfully() {
        var estadoId = 1L;

        doNothing().when(estadoService).delete(estadoId);

        var expected = ResponseEntity.noContent().build();

        var response = estadoController.delete(estadoId);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCallDeleteWithCozinhaInUse() {
        var estadoId = 1L;

        doThrow(EntityInUseException.class).when(estadoService).delete(estadoId);

        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        var response = estadoController.delete(estadoId);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundtWhenCallDeleteWithNonExistentCozinha() {
        var estadoId = 1L;

        doThrow(EntityNotFoundException.class).when(estadoService).delete(estadoId);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = estadoController.delete(estadoId);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
