package com.recreo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entree du fat jar recreo.jar (ADR-0001). Le cablage vit dans com.recreo.bootstrap.config,
 * c'est le seul endroit du reacteur qui voit tous les adaptateurs. La portee de scan est elargie a
 * com.recreo : le controleur d'adapter-web n'est pas sous com.recreo.bootstrap.
 */
@SpringBootApplication(scanBasePackages = "com.recreo")
public class RecreoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecreoApplication.class, args);
    }
}
