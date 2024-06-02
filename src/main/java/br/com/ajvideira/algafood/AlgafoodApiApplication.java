package br.com.ajvideira.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.ajvideira.algafood.infrastructure.repository.CustomJpaRepositoryImpl;

@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
@SpringBootApplication
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
