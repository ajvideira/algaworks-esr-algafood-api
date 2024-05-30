package br.com.ajvideira.algafood.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.util.mock.CozinhaMock;

@ExtendWith(MockitoExtension.class)
class CozinhaServiceTest {

    @InjectMocks
    private CozinhaService cozinhaService;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Test
    void shouldSaveCozinha() {
        var cozinha = CozinhaMock.mock(1L);

        when(cozinhaRepository.save(cozinha)).thenReturn(cozinha);

        assertDoesNotThrow(() -> cozinhaService.save(cozinha));
    }

    @Test
    void shouldDeleteWithSuccess() {
        var cozinhaId = 3L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        doNothing().when(cozinhaRepository).delete(CozinhaMock.mock(cozinhaId));

        assertDoesNotThrow(() -> cozinhaService.delete(cozinhaId));
    }

    @Test
    void shouldThrowEntityInUseException() {
        var cozinhaId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(CozinhaMock.mock(cozinhaId)));

        doThrow(DataIntegrityViolationException.class).when(cozinhaRepository).delete(CozinhaMock.mock(cozinhaId));

        assertThrows(EntityInUseException.class, () -> cozinhaService.delete(cozinhaId));
    }

    @Test
    void shouldThrowEntityNotFoundException() {
        var cozinhaId = 1L;

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cozinhaService.delete(cozinhaId));
    }

}
