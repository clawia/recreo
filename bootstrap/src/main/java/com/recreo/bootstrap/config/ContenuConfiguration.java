package com.recreo.bootstrap.config;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.out.ContentCatalogPort;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Cablage du chargeur de contenu unique (ADR-0007) : adapter-content ne declare aucune dependance
 * Spring et ne peut donc pas se decouvrir lui-meme, c'est ici que le bean est publie. Le catalogue
 * est charge et valide au demarrage, dans ce bean : un paquet invalide fait donc echouer le
 * demarrage du contexte, jamais un repli silencieux (remplace CatalogueDeRepliConfiguration, dont
 * le role de secours n'a plus lieu d'etre une fois ce chargeur en place).
 * Auto-configuration : ConditionalOnMissingBean n'est fiable face a un bean utilisateur (test qui
 * fournit son propre catalogue) que si ce bean par defaut est traite apres lui, ce que seule
 * l'auto-configuration assure.
 */
@AutoConfiguration
class ContenuConfiguration {

    @Bean
    @ConditionalOnMissingBean(ContentCatalogPort.class)
    ContentCatalogPort contentCatalogPort() {
        List<GameDefinition> jeux = new YamlContentPackLoader().games();
        return () -> jeux;
    }
}
