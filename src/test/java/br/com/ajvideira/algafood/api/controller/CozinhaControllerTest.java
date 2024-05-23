package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.api.model.CozinhasXmlWrapper;
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

        var expected = ResponseEntity.ok(cozinhasMock);

        when(cozinhaRepository.findAll()).thenReturn(cozinhasMock);

        var cozinhas = cozinhaController.getAll();

        assertEquals(expected, cozinhas);
    }

    @Test
    void shouldReturnAllCozinhasInXML() {
        var cozinhasMock = List.of(new Cozinha(1L, "Tailandesa"), new Cozinha(2L, "Indiana"),
                new Cozinha(3L, "Francesa"));

        var cozinhasXmlWrapperMock = new CozinhasXmlWrapper(cozinhasMock);

        var expected = ResponseEntity.ok(cozinhasXmlWrapperMock);

        when(cozinhaRepository.findAll()).thenReturn(cozinhasMock);

        var cozinhas = cozinhaController.getAllinXML();

        assertEquals(expected, cozinhas);
    }

    @Test
    void shouldReturnCozinhaWhenExists() {
        var cozinhaMock = new Cozinha(1L, "Tailandesa");

        var expected = ResponseEntity.ok(cozinhaMock);

        when(cozinhaRepository.findById(anyLong())).thenReturn(cozinhaMock);

        var cozinha = cozinhaController.getById(anyLong());

        assertEquals(expected, cozinha);
    }

    @Test
    void shouldReturnNotFoundWhenNotExists() {
        Cozinha cozinhaMock = null;

        var expected = ResponseEntity.notFound().build();

        when(cozinhaRepository.findById(anyLong())).thenReturn(cozinhaMock);

        var cozinha = cozinhaController.getById(anyLong());

        assertEquals(expected, cozinha);
    }

}
