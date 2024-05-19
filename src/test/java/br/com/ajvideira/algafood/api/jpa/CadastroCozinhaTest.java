package br.com.ajvideira.algafood.api.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class CadastroCozinhaTest {

    @InjectMocks
    private CadastroCozinha cadastroCozinha;

    @Mock
    private EntityManager entityManager;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    void shouldListAllCozinhas() {

        TypedQuery typedQuery = mock(TypedQuery.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Indiana");

        List<Cozinha> cozinhas = Arrays.asList(cozinha1, cozinha2);

        when(entityManager.createQuery("FROM Cozinha", Cozinha.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(cozinhas);

        assertEquals(cozinhas, cadastroCozinha.listar());
    }

}
