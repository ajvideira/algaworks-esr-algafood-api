package br.com.ajvideira.algafood.api.di.notificacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ajvideira.algafood.api.di.model.Cliente;

@ExtendWith(MockitoExtension.class)
class NotificadorEmailTest {

    @InjectMocks
    private NotificadorEmail notificadorEmail;

    @Mock
    private NotificadorProperties notificadorProperties;

    @Test
    void testNotificar() {
        Cliente cliente = new Cliente("Jonathan", "jonathan.videira@gmail.com", "51984416170");
        assertDoesNotThrow(() -> {
            notificadorEmail.notificar(cliente, "Teste de mensagem");
        });
    }

}