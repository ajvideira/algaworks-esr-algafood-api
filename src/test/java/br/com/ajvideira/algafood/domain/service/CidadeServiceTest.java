package br.com.ajvideira.algafood.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
        when(estadoRepository.findById(1L)).thenReturn(EstadoMock.mock(1L));

        when(cidadeRepository.save(CidadeMock.mockForInsertWithFullEstado(1L)))
                .thenReturn(CidadeMock.mock(1L, 1L));

        var expected = CidadeMock.mock(1L, 1L);

        var response = cidadeService.save(CidadeMock.mockForInsertWithEstadoId(1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldUpdateCidadeSuccessfully() {
        when(estadoRepository.findById(1L)).thenReturn(EstadoMock.mock(1L));

        when(cidadeRepository.save(
                CidadeMock.mockForUpdateWithFullEstado(1L, 1L)))
                .thenReturn(CidadeMock.mockForUpdateWithFullEstado(1L, 1L));

        var expected = CidadeMock.mockForUpdateWithFullEstado(1L, 1L);

        var response = cidadeService.save(CidadeMock.mockForUpdateWithEstadoId(1L, 1L));

        assertEquals(expected, response);
    }

    @Test
    void shouldThrowEntityNotFoundWhenCidadeNotExists() {
        when(estadoRepository.findById(10L)).thenReturn(null);

        var request = CidadeMock.mockForInsertWithEstadoId(10L);

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
