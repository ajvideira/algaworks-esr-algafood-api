package br.com.ajvideira.algafood.domain.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Grupo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "permissao_id", nullable = false))
    @ManyToMany
    private List<Permissao> permissoes;

}
