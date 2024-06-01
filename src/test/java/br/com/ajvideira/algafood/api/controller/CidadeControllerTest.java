package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
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
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.service.CidadeService;
import br.com.ajvideira.algafood.util.mock.CidadeMock;

@ExtendWith(MockitoExtension.class)
class CidadeControllerTest {

    @InjectMocks
    private CidadeController cidadeController;

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private CidadeService cidadeService;

    @Test
    void shouldReturnAllCidades() {
        when(cidadeRepository.findAll()).thenReturn(CidadeMock.mockList());

        var expected = ResponseEntity.ok(CidadeMock.mockList());

        var response = cidadeController.getAll();

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnCidadeWhenExists() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        var expected = ResponseEntity.ok(CidadeMock.mock(cidadeId, estadoId));

        var response = cidadeController.getById(cidadeId);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExists() {
        var cidadeId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.empty());

        var expected = ResponseEntity.notFound().build();

        var response = cidadeController.getById(cidadeId);

        assertEquals(expected, response);
    }

    @Test
    void shouldCreateCidadeSuccessfully() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeService.save(CidadeMock.mockForInsertWithEstadoId(estadoId)))
                .thenReturn(CidadeMock.mock(cidadeId, estadoId));

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(CidadeMock.mock(cidadeId, estadoId));

        var response = cidadeController.create(CidadeMock.mockForInsertWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenEstadoNotExistsInCreate() {
        var estadoId = 1L;

        when(cidadeService.save(CidadeMock.mockForInsertWithEstadoId(estadoId)))
                .thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = cidadeController.create(CidadeMock.mockForInsertWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateCidadeSuccessfully() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        when(cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(cidadeId, estadoId)))
                .thenReturn(CidadeMock.mock(cidadeId, estadoId));

        var expected = ResponseEntity.ok(CidadeMock.mock(cidadeId, estadoId));

        var response = cidadeController.update(cidadeId, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInUpdate() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.empty());

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = cidadeController.update(cidadeId, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenEstadoNotExistsInUpdate() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        when(cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(cidadeId, estadoId)))
                .thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = cidadeController.update(cidadeId, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldPartialUpdateCidadeSuccessfully() {
        var cidadeId = 1L;
        var estadoId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.of(CidadeMock.mock(cidadeId, estadoId)));

        var cidadeControllerSpy = spy(cidadeController);

        doReturn(ResponseEntity.ok(CidadeMock.mock(cidadeId, estadoId))).when(cidadeControllerSpy).update(cidadeId,
                CidadeMock.mockForUpdateWithEstadoId(cidadeId, cidadeId));

        var expected = ResponseEntity.ok(CidadeMock.mock(cidadeId, estadoId));

        var fields = new HashMap<String, Object>();
        fields.put("nome", "Cidade updated");
        fields.put("estado", Map.of("id", estadoId));

        var response = cidadeControllerSpy.partialUpdate(cidadeId, fields);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInPartialUpdate() {
        var cidadeId = 1L;

        when(cidadeRepository.findById(cidadeId)).thenReturn(Optional.empty());

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = cidadeController.partialUpdate(cidadeId, Map.of("nome", "Cidade updated"));

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteCidadeSuccessfully() {
        var cidadeId = 1L;

        doNothing().when(cidadeService).delete(cidadeId);

        var expected = ResponseEntity.noContent().build();

        var response = cidadeController.delete(cidadeId);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCidadeInUseInDelete() {
        var cidadeId = 1L;

        doThrow(EntityInUseException.class).when(cidadeService).delete(cidadeId);

        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        var response = cidadeController.delete(cidadeId);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInDelete() {
        var cidadeId = 1L;

        doThrow(EntityNotFoundException.class).when(cidadeService).delete(cidadeId);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = cidadeController.delete(cidadeId);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
