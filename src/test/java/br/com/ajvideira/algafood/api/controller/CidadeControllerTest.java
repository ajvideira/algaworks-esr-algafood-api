package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

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
        when(cidadeRepository.findById(1L)).thenReturn(CidadeMock.mock(1L, 1L));

        var expected = ResponseEntity.ok(CidadeMock.mock(1L, 1L));

        var response = cidadeController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExists() {
        when(cidadeRepository.findById(1L)).thenReturn(null);

        var expected = ResponseEntity.notFound().build();

        var response = cidadeController.getById(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldCreateCidadeSuccessfully() {
        when(cidadeService.save(CidadeMock.mockForInsertWithEstadoId(1L))).thenReturn(CidadeMock.mock(1L, 1L));

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(CidadeMock.mock(1L, 1L));

        var response = cidadeController.create(CidadeMock.mockForInsertWithEstadoId(1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenEstadoNotExistsInCreate() {
        when(cidadeService.save(CidadeMock.mockForInsertWithEstadoId(10L))).thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = cidadeController.create(CidadeMock.mockForInsertWithEstadoId(10L));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateCidadeSuccessfully() {
        when(cidadeRepository.findById(1L)).thenReturn(CidadeMock.mock(1L, 1L));

        when(cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(1L, 1L))).thenReturn(CidadeMock.mock(1L, 1L));

        var expected = ResponseEntity.ok(CidadeMock.mock(1L, 1L));

        var response = cidadeController.update(1L, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInUpdate() {
        when(cidadeRepository.findById(10L)).thenReturn(null);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = cidadeController.update(10L, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnBadRequestWhenEstadoNotExistsInUpdate() {
        when(cidadeRepository.findById(1L)).thenReturn(CidadeMock.mock(1L, 1L));

        when(cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(1L, 10L)))
                .thenThrow(EntityNotFoundException.class);

        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var response = cidadeController.update(1L, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(10L));

        assertEquals(expected, response);
    }

    @Test
    void shouldPartialUpdateCidadeSuccessfully() {
        when(cidadeRepository.findById(1L)).thenReturn(CidadeMock.mock(1L, 1L));

        var cidadeControllerSpy = spy(cidadeController);

        doReturn(ResponseEntity.ok(CidadeMock.mock(1L, 1L))).when(cidadeControllerSpy).update(1L,
                CidadeMock.mockForUpdateWithEstadoId(1L, 1L));

        var expected = ResponseEntity.ok(CidadeMock.mock(1L, 1L));

        var fields = new HashMap<String, Object>();
        fields.put("nome", "Cidade updated");
        fields.put("estado", Map.of("id", 1L));

        var response = cidadeControllerSpy.partialUpdate(1L, fields);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInPartialUpdate() {
        when(cidadeRepository.findById(10L)).thenReturn(null);

        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var response = cidadeController.update(10L, CidadeMock.mockForUpdateWithoutIdAndWithEstadoId(1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteCidadeSuccessfully() {
        doNothing().when(cidadeService).delete(1L);

        var expected = ResponseEntity.noContent().build();

        var response = cidadeController.delete(1L);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCidadeInUseInDelete() {
        doThrow(EntityInUseException.class).when(cidadeService).delete(1L);

        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        var response = cidadeController.delete(1L);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenCidadeNotExistsInDelete() {
        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        doThrow(EntityNotFoundException.class).when(cidadeService).delete(1L);

        var response = cidadeController.delete(1L);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
