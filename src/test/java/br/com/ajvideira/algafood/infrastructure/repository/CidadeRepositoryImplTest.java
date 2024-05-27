package br.com.ajvideira.algafood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cidade;
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class CidadeRepositoryImplTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void shouldFindAll() {
        assertEquals(3, cidadeRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertNotNull(cidadeRepository.findById(1L));
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertNull(cidadeRepository.findById(10L));
    }

    @Test
    void shouldAdd() {
        Cidade cidade = new Cidade();
        cidade.setNome("Florianópolis");

        Estado estado = new Estado();
        estado.setId(6L);
        cidade.setEstado(estado);

        cidade = cidadeRepository.save(cidade);
        System.out.println(cidade);
        assertNotNull(cidade.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Cidade cidade = cidadeRepository.findById(1L);
        cidade.setNome("Recife");

        cidade = cidadeRepository.save(cidade);

        assertEquals(cidadeRepository.findById(cidade.getId()), cidade);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Poro Alegre");

        cidade = cidadeRepository.save(cidade);

        assertEquals(cidadeRepository.findById(cidade.getId()), cidade);
    }

    @Test
    void shouldDeleteEntity() {
        cidadeRepository.delete(1L);

        assertNull(cidadeRepository.findById(1L));
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> cidadeRepository.delete(10L));
    }

}
