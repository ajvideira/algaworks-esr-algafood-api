package br.com.ajvideira.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonRootName("cozinha")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

}
