package com.recreo.bootstrap.config;

import com.recreo.application.port.out.ContentCatalogPort;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Catalogue de repli, temporaire : aucun paquet de contenu n'est encore charge, l'adaptateur qui
 * lit les paquets YAML arrive avec lea-dev (ADR-0007, story S0-4). Ce bean ne lit aucun fichier et
 * s'efface automatiquement des qu'un vrai ContentCatalogPort est publie, sans avoir a etre retire.
 * Auto-configuration : ConditionalOnMissingBean n'est fiable face a un bean utilisateur (test ou
 * futur adaptateur de contenu) que si ce repli est traite apres lui.
 */
@AutoConfiguration
class CatalogueDeRepliConfiguration {

    @Bean
    @ConditionalOnMissingBean(ContentCatalogPort.class)
    ContentCatalogPort catalogueVide() {
        return List::of;
    }
}
