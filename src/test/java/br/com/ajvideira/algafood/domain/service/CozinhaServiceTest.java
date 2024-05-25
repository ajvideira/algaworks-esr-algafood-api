package br.com.ajvideira.algafood.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;

@ExtendWith(MockitoExtension.class)
class CozinhaServiceTest {

    @InjectMocks
    private CozinhaService cozinhaService;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Test
    void shouldSaveCozinha() {
        Cozinha cozinha = new Cozinha(1L, "Espanhola");

        when(cozinhaRepository.save(cozinha)).thenReturn(cozinha);

        assertDoesNotThrow(() -> cozinhaService.save(cozinha));
    }

    @Test
    void shouldDeleteWithSuccess() {
        var cozinhaId = 1L;

        doNothing().when(cozinhaRepository).delete(cozinhaId);

        assertDoesNotThrow(() -> cozinhaService.delete(cozinhaId));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var cozinhaId = 1L;

        doThrow(DataIntegrityViolationException.class).when(cozinhaRepository).delete(cozinhaId);

        assertThrows(EntityInUseException.class, () -> cozinhaService.delete(cozinhaId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var cozinhaId = 1L;

        doThrow(EmptyResultDataAccessException.class).when(cozinhaRepository).delete(cozinhaId);

        assertThrows(EntityNotFoundException.class, () -> cozinhaService.delete(cozinhaId));
    }

}
