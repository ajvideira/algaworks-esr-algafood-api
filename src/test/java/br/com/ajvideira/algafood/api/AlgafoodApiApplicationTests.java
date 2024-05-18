package br.com.ajvideira.algafood.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ajvideira.algafood.api.controller.HomeController;

@SpringBootTest
class AlgafoodApiApplicationTests {

	@Autowired
	private HomeController homeController;

	@Test
	void contextLoads() {
		assertNotNull(homeController);
	}

}
