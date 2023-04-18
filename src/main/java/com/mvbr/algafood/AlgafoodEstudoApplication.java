package com.mvbr.algafood;

import com.mvbr.algafood.infra.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodEstudoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AlgafoodEstudoApplication.class, args);
	}

}
