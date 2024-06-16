package br.com.ajvideira.algafood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cidade;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.util.mock.CidadeMock;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class CidadeRepositoryImplTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void shouldFindAll() {
        assertEquals(4, cidadeRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertTrue(cidadeRepository.findById(1L).isPresent());
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertTrue(cidadeRepository.findById(10L).isEmpty());
    }

    @Test
    void shouldAdd() {
        var estadoId = 1L;

        var cidade = CidadeMock.mockForInsertWithEstadoId(estadoId);

        cidade = cidadeRepository.save(cidade);

        assertNotNull(cidade.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Cidade cidade = cidadeRepository.findById(1L).get();
        cidade.setNome("Recife");

        cidade = cidadeRepository.save(cidade);

        assertEquals(cidadeRepository.findById(cidade.getId()).get(), cidade);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Poro Alegre");

        cidade = cidadeRepository.save(cidade);

        assertEquals(cidadeRepository.findById(cidade.getId()).get(), cidade);
    }

    @Test
    void shouldDeleteEntity() {
        cidadeRepository.deleteById(1L);

        assertTrue(cidadeRepository.findById(1L).isEmpty());
    }
}
