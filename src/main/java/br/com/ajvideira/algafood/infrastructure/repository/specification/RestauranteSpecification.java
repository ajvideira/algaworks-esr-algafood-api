package br.com.ajvideira.algafood.infrastructure.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.ajvideira.algafood.domain.model.Restaurante;

public class RestauranteSpecification {

    private RestauranteSpecification() {
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("nome")),
                "%" + (nome != null ? nome.toLowerCase() : "") + "%");
    }

    public static Specification<Restaurante> comTaxaFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

}
