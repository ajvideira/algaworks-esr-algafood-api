package br.com.ajvideira.algafood.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNotNull(permissaoRepository.findById(1L));
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertNull(permissaoRepository.findById(10L));
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
        Permissao permissao = permissaoRepository.findById(1L);
        permissao.setNome("ROLE_ADMINISTRADOR");

        permissao = permissaoRepository.save(permissao);

        assertEquals(permissaoRepository.findById(permissao.getId()), permissao);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        Permissao permissao = new Permissao();
        permissao.setId(1L);
        permissao.setNome("ROLE_ADMINISTRADOR");
        permissao.setDescricao("Permissão de administrador no sistema.");

        permissao = permissaoRepository.save(permissao);

        assertEquals(permissaoRepository.findById(permissao.getId()), permissao);
    }

    @Test
    void shouldDeleteManagedEntity() {
        Permissao permissao = permissaoRepository.findById(1L);
        permissaoRepository.delete(permissao);

        assertNull(permissaoRepository.findById(1L));
    }

    @Test
    void shouldDeleteNonManagedEntity() {
        Permissao permissao = new Permissao();
        permissao.setId(1L);
        permissaoRepository.delete(permissao);

        assertNull(permissaoRepository.findById(1L));
    }

}
