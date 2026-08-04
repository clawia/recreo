package com.recreo.bootstrap.config;

import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.RandomSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cablage du cas d'usage unique du sprint 0. ADR-0002 : PartieService lui-meme ne porte aucune
 * annotation de framework, c'est ici que Spring le construit.
 */
@Configuration
class PartieConfiguration {

    @Bean
    PartieUseCase partieUseCase(ContentCatalogPort catalogue, MechanicRegistryPort mecaniques,
                                 RandomSource hasard) {
        return new PartieService(catalogue, mecaniques, hasard);
    }
}
