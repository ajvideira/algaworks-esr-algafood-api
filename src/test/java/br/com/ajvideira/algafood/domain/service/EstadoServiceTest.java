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
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
import br.com.ajvideira.algafood.util.mock.EstadoMock;

@ExtendWith(MockitoExtension.class)
class EstadoServiceTest {

    @InjectMocks
    private EstadoService estadoService;

    @Mock
    private EstadoRepository estadoRepository;

    @Test
    void shouldSaveSuccessfully() {
        var estadoId = 1L;

        when(estadoRepository.save(EstadoMock.mockForInsert())).thenReturn(EstadoMock.mock(estadoId));

        var expected = EstadoMock.mock(estadoId);

        var response = estadoService.save(EstadoMock.mockForInsert());

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteSuccessfully() {
        var estadoId = 1L;

        when(estadoRepository.findById(
                estadoId))
                .thenReturn(Optional.of(EstadoMock.mock(estadoId)));

        doNothing().when(estadoRepository).delete(EstadoMock.mock(estadoId));

        assertDoesNotThrow(() -> estadoService.delete(estadoId));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var estadoId = 1L;

        when(estadoRepository.findById(
                estadoId))
                .thenReturn(Optional.of(EstadoMock.mock(estadoId)));

        doThrow(DataIntegrityViolationException.class).when(estadoRepository).delete(EstadoMock.mock(estadoId));

        assertThrows(EntityInUseException.class, () -> estadoService.delete(estadoId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var estadoId = 1L;

        when(estadoRepository.findById(
                estadoId))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> estadoService.delete(estadoId));
    }

}
