package com.recreo.bootstrap.config;

import com.recreo.adapter.content.YamlContentPackLoader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0106 : le bean publie porte la donnee de matiere du chargeur. Le port donne a {@code matieres()}
 * une valeur par defaut vide pour les doubles de test ; un bean qui ne la redefinirait pas servirait
 * l'accueil sans aucune matiere, et c'est le defaut que la premiere version de ce lot portait (mesure :
 * TexteAccentueExposeParLePortEntrantTest rouge, "Mathematiques est servie sans ligne").
 */
class ContenuConfigurationTest {

    @Test
    void leBeanPublieServLaDonneeDeMatiereDuChargeur() {
        assertThat(new ContenuConfiguration().contentCatalogPort().matieres())
                .isNotEmpty()
                .isEqualTo(new YamlContentPackLoader().matieres());
    }
}
