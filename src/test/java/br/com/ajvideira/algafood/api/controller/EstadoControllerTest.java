package br.com.ajvideira.algafood.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@ExtendWith(MockitoExtension.class)
class EstadoControllerTest {

    @InjectMocks
    private EstadoController estadoController;

    @Mock
    private EstadoRepository estadoRepository;

    @Test
    void shouldReturnAllEstados() {
        var estadosMock = List.of(new Estado(1L, "São Paulo"), new Estado(2L, "Rio de Janeiro"),
                new Estado(3L, "Minas Gerais"));

        when(estadoRepository.findAll()).thenReturn(estadosMock);

        var estados = this.estadoController.getAll();

        assertEquals(estadosMock, estados);
    }

}
