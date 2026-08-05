package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanic.GameSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0007 : le contexte Spring complet demarre avec le vrai chargeur de contenu, sans catalogue
 * de test importe. Verifie ce qu'ArchUnit ne peut pas voir : chaque entree de catalogue reference
 * un paquet et une mecanique qui existent, et demarre effectivement une partie - la compatibilite
 * de forme entre le paquet et la mecanique qui le joue.
 */
@SpringBootTest
class CatalogueChargeableIT {

    @Autowired
    private PartieUseCase partie;

    @Test
    void leCatalogueSeChargeEtChaqueJeuDemarreUnePartie() {
        List<GameDefinition> jeux = partie.jeuxDisponibles();

        assertThat(jeux)
                .as("ADR-0007 : au moins le jeu d'additions CP livre par ce sprint")
                .isNotEmpty();

        for (GameDefinition jeu : jeux) {
            GameSession session = partie.demarrer(jeu.id());
            assertThat(session)
                    .as("ADR-0007 : %s demarre effectivement une partie", jeu.id())
                    .isNotNull();

            if ("bandit-manchot-additions-cp".equals(jeu.id())) {
                assertThat(jeu.titre())
                        .as("S1-8 : le libelle d'accueil ne reintroduit pas le grapheme"
                                + " 'ti' prononce /si/ de l'ancien titre 'Additions CP'")
                        .doesNotContain("ti");
            }
        }
    }
}
