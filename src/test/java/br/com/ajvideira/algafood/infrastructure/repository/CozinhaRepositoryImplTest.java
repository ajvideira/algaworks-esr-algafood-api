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

import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class CozinhaRepositoryImplTest {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Test
    void shouldFindAll() {
        assertEquals(3, cozinhaRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertNotNull(cozinhaRepository.findById(1L));
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertTrue(cozinhaRepository.findById(10L).isEmpty());
    }

    @Test
    void shouldAdd() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha = cozinhaRepository.save(cozinha);

        assertNotNull(cozinha.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Cozinha cozinha = cozinhaRepository.findById(1L).get();
        cozinha.setNome("Japonesa");

        cozinha = cozinhaRepository.save(cozinha);

        assertEquals(cozinha, cozinhaRepository.findById(cozinha.getId()).get());
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Italiana");

        cozinha = cozinhaRepository.save(cozinha);

        assertEquals(cozinha, cozinhaRepository.findById(cozinha.getId()).get());
    }

    @Test
    void shouldDeleteEntity() {
        cozinhaRepository.deleteById(1L);

        assertTrue(cozinhaRepository.findById(1L).isEmpty());
    }
}
