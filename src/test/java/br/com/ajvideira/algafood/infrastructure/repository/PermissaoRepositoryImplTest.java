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

import br.com.ajvideira.algafood.domain.model.Permissao;
import br.com.ajvideira.algafood.domain.repository.PermissaoRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class PermissaoRepositoryImplTest {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Test
    void shouldFindAll() {
        assertEquals(2, permissaoRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertTrue(permissaoRepository.findById(1L).isPresent());
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertTrue(permissaoRepository.findById(10L).isEmpty());
    }

    @Test
    void shouldAdd() {
        Permissao permissao = new Permissao();
        permissao.setNome("ROLE_CLIENTE");
        permissao.setDescricao("Permissão para visualizar clientes.");
        permissao = permissaoRepository.save(permissao);

        assertNotNull(permissao.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        Permissao permissao = permissaoRepository.findById(1L).get();
        permissao.setNome("ROLE_ADMINISTRADOR");

        permissao = permissaoRepository.save(permissao);

        assertEquals(permissaoRepository.findById(permissao.getId()).get(), permissao);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Permissao permissao = new Permissao();
        permissao.setId(1L);
        permissao.setNome("ROLE_ADMINISTRADOR");
        permissao.setDescricao("Permissão de administrador no sistema.");

        permissao = permissaoRepository.save(permissao);

        assertEquals(permissaoRepository.findById(permissao.getId()).get(), permissao);
    }

    @Test
    void shouldDeleteManagedEntity() {
        Permissao permissao = permissaoRepository.findById(1L).get();
        permissaoRepository.delete(permissao);

        assertTrue(permissaoRepository.findById(1L).isEmpty());
    }

}
