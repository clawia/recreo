package com.recreo.bootstrap.config;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.Matiere;
import com.recreo.application.port.out.ContentCatalogPort;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Cablage du chargeur de contenu unique (ADR-0007) : adapter-content ne declare aucune dependance
 * Spring et ne peut donc pas se decouvrir lui-meme, c'est ici que le bean est publie. Le catalogue
 * est charge et valide au demarrage, dans ce bean : un paquet invalide fait donc echouer le
 * demarrage du contexte, jamais un repli silencieux.
 * Auto-configuration : ConditionalOnMissingBean n'est fiable face a un bean utilisateur (test qui
 * fournit son propre catalogue) que si ce bean par defaut est traite apres lui, ce que seule
 * l'auto-configuration assure.
 *
 * <p>ADR-0106 : la donnee de matiere est chargee au demarrage avec le catalogue. Le bean redefinit
 * {@code matieres()} : la valeur par defaut du port, une liste vide, ne sert qu'aux doubles de test
 * (ContenuConfigurationTest).
 */
@AutoConfiguration
class ContenuConfiguration {

    @Bean
    @ConditionalOnMissingBean(ContentCatalogPort.class)
    ContentCatalogPort contentCatalogPort() {
        YamlContentPackLoader chargeur = new YamlContentPackLoader();
        List<GameDefinition> jeux = chargeur.games();
        List<Matiere> matieres = chargeur.matieres();
        return new ContentCatalogPort() {

            @Override
            public List<GameDefinition> games() {
                return jeux;
            }

            @Override
            public List<Matiere> matieres() {
                return matieres;
            }
        };
    }
}
