package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S52-RETRAIT-SCIENCES-CM1 (DEC-0111 section 3) : les deux jeux de sciences CM1 que nadia-prof a
 * refuses pour une cle de reponse fausse ne sont plus servis. Le catalogue est lu par le port
 * entrant, tel que l'accueil le liste, sur le vrai catalogue.
 *
 * <p>Ce test ne nomme que des identifiants et des titres de jeu, jamais le paquet qu'une entree
 * sert : ADR-0061 interdit de recopier l'association (entree, paquet) en constantes. Le retrait est
 * l'absence d'entree, sans champ de statut.
 */
@SpringBootTest
class LesJeuxRetiresDuCatalogueNeSontPasServisTest {

    @Autowired
    private PartieUseCase partie;

    @Test
    void lesDeuxJeuxDeSciencesRefusesNeFigurentPasAuCatalogueServi() {
        List<GameDefinition> jeux = partie.jeuxDisponibles();

        assertThat(jeux)
                .as("DEC-0111 section 3 : cette clause ne prouve rien sur un catalogue vide")
                .isNotEmpty();
        assertThat(jeux).extracting(GameDefinition::id)
                .as("DEC-0111 section 3 : identifiants retires du catalogue servi")
                .doesNotContain("bandit-manchot-activite-humaine-cm1", "bandit-manchot-terre-cm1");
        assertThat(jeux).extracting(GameDefinition::titre)
                .as("DEC-0111 section 3 : titres retires de la liste de l'accueil")
                .doesNotContain("L'activité humaine et la nature", "La Terre, le jour et les saisons");
    }

    @Test
    void leRetraitNeToucheAucunAutreJeuDeSciences() {
        assertThat(partie.jeuxDisponibles()).extracting(GameDefinition::id)
                .as("DEC-0111 section 3 : le retrait ne touche aucun autre jeu")
                .contains("bandit-manchot-corps-cm1");
    }
}
