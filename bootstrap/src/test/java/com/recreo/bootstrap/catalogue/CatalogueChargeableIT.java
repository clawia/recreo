package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.mechanic.GameMechanic;
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

    @Autowired
    private MechanicRegistryPort mecaniques;

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

            assertThat(jeu.nombreDeManches())
                    .as("ADR-0012 : %s porte une longueur de partie chargee depuis le catalogue."
                            + " Combien de manches est un choix pedagogique ; qu'il y en ait au"
                            + " moins une ne l'est pas", jeu.id())
                    .isPositive();

            GameMechanic mecanique = mecaniques.mecaniquePour(jeu.mecanique());
            assertThat(jeu.nombreDeManches())
                    .as("S3-2, ADR-0013 : %s doit compter assez de manches pour que l'aide de sa"
                            + " mecanique reste atteignable (au moins %d manches)",
                            jeu.id(), mecanique.longueurMinimale())
                    .isGreaterThanOrEqualTo(mecanique.longueurMinimale());

            if ("bandit-manchot-additions-cp".equals(jeu.id())) {
                assertThat(jeu.titre())
                        .as("S1-8 : le libelle d'accueil ne reintroduit pas le grapheme"
                                + " 'ti' prononce /si/ de l'ancien titre 'Additions CP'")
                        .doesNotContain("ti");
            }
        }
    }
}
