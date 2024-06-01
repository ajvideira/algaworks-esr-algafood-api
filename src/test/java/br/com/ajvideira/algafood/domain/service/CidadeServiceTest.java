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
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
import br.com.ajvideira.algafood.util.mock.CidadeMock;
import br.com.ajvideira.algafood.util.mock.EstadoMock;

@ExtendWith(MockitoExtension.class)
class CidadeServiceTest {

    @InjectMocks
    private CidadeService cidadeService;

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private EstadoRepository estadoRepository;

    @Test
    void shouldInserCidadeSuccessfully() {
        var estadoId = 1L;
        var cidadeId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.of(EstadoMock.mock(estadoId)));

        when(cidadeRepository.save(CidadeMock.mockForInsertWithFullEstado(estadoId)))
                .thenReturn(CidadeMock.mock(cidadeId, estadoId));

        var expected = CidadeMock.mock(cidadeId, estadoId);

        var response = cidadeService.save(CidadeMock.mockForInsertWithEstadoId(estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateCidadeSuccessfully() {
        var estadoId = 1L;
        var cidadeId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.of(EstadoMock.mock(estadoId)));

        when(cidadeRepository.save(CidadeMock.mockForUpdateWithFullEstado(cidadeId, estadoId)))
                .thenReturn(CidadeMock.mockForUpdateWithFullEstado(cidadeId, estadoId));

        var expected = CidadeMock.mockForUpdateWithFullEstado(cidadeId, estadoId);

        var response = cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(cidadeId, estadoId));

        assertEquals(expected, response);
    }

    @Test
    void shouldThrowEntityNotFoundWhenCidadeNotExists() {
        var estadoId = 1L;

        when(estadoRepository.findById(estadoId)).thenReturn(Optional.empty());

        var request = CidadeMock.mockForInsertWithEstadoId(estadoId);

        assertThrows(EntityNotFoundException.class,
                () -> cidadeService.save(request));
    }

    @Test
    void shouldDeleteWithSuccess() {
        doNothing().when(cidadeRepository).delete(1L);

        assertDoesNotThrow(() -> cidadeService.delete(1L));
    }

    @Test
    void shouldThrowEntityInUseException() {
        doThrow(DataIntegrityViolationException.class).when(cidadeRepository).delete(1L);

        assertThrows(EntityInUseException.class, () -> cidadeService.delete(1L));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        doThrow(EmptyResultDataAccessException.class).when(cidadeRepository).delete(1L);

        assertThrows(EntityNotFoundException.class, () -> cidadeService.delete(1L));
    }

}
