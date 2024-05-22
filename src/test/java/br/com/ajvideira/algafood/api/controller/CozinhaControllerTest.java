package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;

@ExtendWith(MockitoExtension.class)
class CozinhaControllerTest {

    @InjectMocks
    private CozinhaController cozinhaController;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Test
    void shouldReturnAllCozinhas() {

        var cozinhasMock = List.of(new Cozinha(1L, "Tailandesa"), new Cozinha(2L, "Indiana"),
                new Cozinha(3L, "Francesa"));

        when(cozinhaRepository.findAll()).thenReturn(cozinhasMock);

        var cozinhas = cozinhaController.getAll();

        assertEquals(cozinhasMock, cozinhas);
    }

}
