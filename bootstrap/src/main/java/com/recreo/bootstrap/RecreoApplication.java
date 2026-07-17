package com.recreo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Point d'entree Spring Boot. Seule couche qui voit Spring et tous les adapters.
 * Produit le fat jar executable {@code recreo.jar}.
 */
@SpringBootApplication(scanBasePackages = "com.recreo")
@EntityScan("com.recreo.adapter.persistence.entity")
@EnableJpaRepositories("com.recreo.adapter.persistence.repository")
public class RecreoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecreoApplication.class, args);
    }
}
