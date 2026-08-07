package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.util.HttpSessionMutexListener;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0019 : EtatDePartieEnSession verrouille sur l'objet que WebUtils.getSessionMutex designe.
 * Sans HttpSessionMutexListener, cet objet est la session HTTP elle-meme, dont le conteneur ne
 * garantit pas de rendre la meme instance a deux requetes - et deux requetes qui verrouillent deux
 * objets differents ne se serialisent pas.
 *
 * <p>ArchUnit ne peut pas garder cette propriete : elle ne porte pas sur une dependance mais sur la
 * presence d'un bean dans le contexte assemble. La panne serait silencieuse - aucune ligne de code
 * ne changerait, et le verrou ne verrouillerait plus rien. C'est donc ce test de contexte qui la
 * tient.</p>
 */
@SpringBootTest
@Import(PartieHttpTest.CatalogueDeTestConfiguration.class)
class VerrouDeSessionEnregistreTest {

    @Autowired
    private ApplicationContext contexte;

    @Test
    void leVerrouDeSessionEstEnregistreCommeEcouteurDuConteneur() {
        assertThat(contexte.getBeansOfType(ServletListenerRegistrationBean.class).values())
                .as("ADR-0019 : sans cet ecouteur, le verrou de session porte sur un objet que le"
                        + " conteneur n'est pas tenu de conserver d'une requete a l'autre")
                .anySatisfy(enregistrement -> assertThat(enregistrement.getListener())
                        .isInstanceOf(HttpSessionMutexListener.class));
    }
}
