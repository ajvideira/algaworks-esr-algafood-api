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
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@ExtendWith(MockitoExtension.class)
class EstadoServiceTest {

    @InjectMocks
    private EstadoService estadoService;

    @Mock
    private EstadoRepository estadoRepository;

    @Test
    void shouldSaveSuccessfully() {
        var expected = new Estado(1L, "São Paulo");

        when(estadoRepository.save(new Estado(null, "São Paulo"))).thenReturn(new Estado(1L, "São Paulo"));

        var response = estadoService.save(new Estado(null, "São Paulo"));

        assertEquals(expected, response);
    }

    @Test
    void shouldDeleteSuccessfully() {
        doNothing().when(estadoRepository).delete(4L);

        assertDoesNotThrow(() -> estadoService.delete(4L));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var estadoId = 4L;

        doThrow(DataIntegrityViolationException.class).when(estadoRepository).delete(estadoId);

        assertThrows(EntityInUseException.class, () -> estadoService.delete(estadoId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var estadoId = 1L;

        doThrow(EmptyResultDataAccessException.class).when(estadoRepository).delete(estadoId);

        assertThrows(EntityNotFoundException.class, () -> estadoService.delete(estadoId));
    }

}
