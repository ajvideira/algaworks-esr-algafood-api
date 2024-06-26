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

import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class EstadoRepositoryImplTest {

    @Autowired
    private EstadoRepository estadoRepository;

    @Test
    void shouldFindAll() {
        assertEquals(7, estadoRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertTrue(estadoRepository.findById(1L).isPresent());
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertTrue(estadoRepository.findById(10L).isEmpty());
    }

    @Test
    void shouldAdd() {
        Estado estado = new Estado();
        estado.setNome("Amapá");
        estado = estadoRepository.save(estado);

        assertNotNull(estado.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Estado estado = estadoRepository.findById(1L).get();
        estado.setNome("Alagoas");

        estado = estadoRepository.save(estado);

        assertEquals(estadoRepository.findById(estado.getId()).get(), estado);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Estado estado = new Estado();
        estado.setId(1L);
        estado.setNome("Espírito Santo");

        estado = estadoRepository.save(estado);

        assertEquals(estadoRepository.findById(estado.getId()).get(), estado);
    }

    @Test
    void shouldDeleteEntity() {
        estadoRepository.deleteById(4L);

        assertTrue(estadoRepository.findById(4L).isEmpty());
    }
}
