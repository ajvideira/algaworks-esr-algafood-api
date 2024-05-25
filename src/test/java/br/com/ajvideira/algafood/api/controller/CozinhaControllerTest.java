package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.service.CozinhaService;

@ExtendWith(MockitoExtension.class)
class CozinhaControllerTest {

    @InjectMocks
    private CozinhaController cozinhaController;

    @Mock
    private CozinhaRepository cozinhaRepository;

    @Mock
    private CozinhaService cozinhaService;

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
    void shouldReturnCozinhaWhenExists() {
        var cozinhaMock = new Cozinha(1L, "Tailandesa");

        var expected = ResponseEntity.ok(cozinhaMock);

        when(cozinhaRepository.findById(anyLong())).thenReturn(cozinhaMock);

        var cozinha = cozinhaController.getById(anyLong());

        assertEquals(expected, cozinha);
    }

    @Test
    void shouldReturnNotFoundWhenCozinhaNotExists() {
        Cozinha cozinhaMock = null;

        var expected = ResponseEntity.notFound().build();

        when(cozinhaRepository.findById(anyLong())).thenReturn(cozinhaMock);

        var cozinha = cozinhaController.getById(anyLong());

        assertEquals(expected, cozinha);
    }

    @Test
    void shouldReturnCreatedWhenCallCreateWithValidData() {
        var cozinhaMock = new Cozinha(1L, "Tailandesa");

        var expected = ResponseEntity.status(HttpStatus.CREATED).body(cozinhaMock);

        var cozinhaRequest = new Cozinha();
        cozinhaRequest.setNome("Jamaicana");

        when(cozinhaService.save(cozinhaRequest)).thenReturn(cozinhaMock);

        var response = cozinhaController.create(cozinhaRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnOKWhenCallUpdateWithValidData() {
        var cozinhaMockFromRepository = new Cozinha(1L, "Tailandesa");
        var cozinhaMockFromService = new Cozinha(1L, "Jamaicana");
        var expected = ResponseEntity.ok(cozinhaMockFromService);

        when(cozinhaRepository.findById(1L)).thenReturn(cozinhaMockFromRepository);
        when(cozinhaService.save(cozinhaMockFromService)).thenReturn(cozinhaMockFromService);

        var cozinhaIdRequest = 1L;
        var cozinhaRequest = new Cozinha();
        cozinhaRequest.setNome("Jamaicana");

        var response = cozinhaController.update(cozinhaIdRequest, cozinhaRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNotFoundWhenCallUpdateWithNonExistentCozinha() {
        var expected = ResponseEntity.notFound().build();

        when(cozinhaRepository.findById(1L)).thenReturn(null);

        var cozinhaIdRequest = 1L;
        var cozinhaRequest = new Cozinha();
        cozinhaRequest.setNome("Jamaicana");

        var response = cozinhaController.update(cozinhaIdRequest, cozinhaRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnNoContentWhenCallDeleteWithExistentCozinha() {
        var expected = ResponseEntity.noContent().build();

        doNothing().when(cozinhaService).delete(1L);

        var cozinhaIdRequest = 1L;

        var response = cozinhaController.delete(cozinhaIdRequest);

        assertEquals(expected, response);
    }

    @Test
    void shouldReturnConflictWhenCallDeleteWithCozinhaInUse() {
        var expected = ResponseEntity.status(HttpStatus.CONFLICT).build();

        doThrow(EntityInUseException.class).when(cozinhaService).delete(1L);

        var cozinhaIdRequest = 1L;

        var response = cozinhaController.delete(cozinhaIdRequest);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundtWhenCallDeleteWithNonExistentCozinha() {
        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        doThrow(EntityNotFoundException.class).when(cozinhaService).delete(1L);

        var cozinhaIdRequest = 1L;

        var response = cozinhaController.delete(cozinhaIdRequest);

        assertEquals(expected.getStatusCode(), response.getStatusCode());
    }

}
