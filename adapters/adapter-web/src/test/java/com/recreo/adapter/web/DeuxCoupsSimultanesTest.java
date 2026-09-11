package com.recreo.adapter.web;

import com.recreo.domain.mechanic.GameSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0019 : le defaut signale par l'utilisatrice ("le score ne s'incremente pas toujours"),
 * reproduit par yanis-qa a 14 pertes sur 40 essais. Un double appui envoie deux POST qui se
 * recouvrent sur la meme session ; le cycle lire-calculer-ecrire non protege faisait calculer aux
 * deux requetes leur etat suivant a partir du meme etat de depart, et la derniere ecriture ecrasait
 * l'autre. L'enfant voyait "Bravo, c'est juste !" et le point disparaissait de l'etat qui fait foi.
 *
 * <p>Un test sequentiel ne peut pas voir ce defaut : son perimetre exclut la concurrence, comme les
 * donnees d'entree d'un test font partie de son perimetre (ADR-0013, amendement du 2026-08-06).
 * Celui-ci envoie deux requetes reellement simultanees, synchronisees par une barriere.</p>
 *
 * <p>Il est probabiliste dans le sens du rouge, jamais dans celui du vert : une correction absente
 * ne perd pas le point a tous les essais, une correction presente ne le perd a aucun. Le nombre de
 * repetitions est donc dimensionne pour qu'un depot casse ne puisse pas passer par chance - voir
 * REPETITIONS. La preuve deterministe de l'exclusion mutuelle, elle, est dans
 * EtatDePartieEnSessionTest.</p>
 *
 * <p><b>DEC-0061, instruction 1, famille A (S34-SCO1-REMEDE) : le temoin d'origine cesse d'en
 * etre un, et il est remplace.</b> Le temoin d'origine lisait {@code score.reussites()} et exigeait
 * qu'il vaille toujours 1, quel que soit l'ordre de traitement des deux coups. Sous le remede,
 * {@code score.reussites()} depend desormais de cet ordre : si la mauvaise reponse est traitee la
 * premiere, la manche a coute un echec, et la bonne reponse qui la resout ensuite n'est plus
 * comptee comme trouvee du premier coup (c'est exactement ce que la decision de nadia-prof
 * demande) - {@code reussites()} peut alors valoir 0. Ce rouge n'est pas une regression d'ADR-0019
 * ni de l'exclusion mutuelle qu'il protege : la propriete que ADR-0019 garde - aucune ecriture
 * n'en ecrase une autre - reste vraie. Le temoin de remplacement est {@code score.tentatives()} :
 * sous verrou il vaut exactement 1 apres les deux appuis simultanes, quel que soit leur ordre,
 * puisqu'une seule manche est resolue ; sans verrou, la derniere ecriture ecrase l'autre et il peut
 * valoir 0 (theo-archi, avis de s34, section 2.3).</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class DeuxCoupsSimultanesTest {

    /** Le jeu au contenu repete : chaque manche pose la meme question, la bonne reponse est connue. */
    private static final String JEU_ID = "jeu-repete";

    private static final String BONNE_REPONSE = "bonne";
    private static final String MAUVAISE_REPONSE = "mauvaise";

    /**
     * Mesure sur ce depot, correction retiree (verrou remplace par un objet neuf a chaque appel) :
     * 19 pertes sur 60 essais, soit 32 % - du meme ordre que les 35 % mesures par yanis-qa sur le
     * jar. A ce taux, la probabilite qu'un depot casse traverse 60 essais sans une seule perte est
     * de 0,68 puissance 60, moins d'une chance sur un milliard. Baisser ce nombre rendrait le test
     * decoratif ; l'augmenter n'achete plus rien.
     */
    private static final int REPETITIONS = 60;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deuxCoupsSimultanesNeFontJamaisDisparaitreUneTentative() throws Exception {
        ExecutorService fils = Executors.newFixedThreadPool(2);
        List<Integer> tentativesObservees = new ArrayList<>();
        try {
            for (int essai = 0; essai < REPETITIONS; essai++) {
                MockHttpSession session = new MockHttpSession();
                mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

                CyclicBarrier depart = new CyclicBarrier(2);
                Future<?> juste = fils.submit(coup(session, depart, BONNE_REPONSE));
                Future<?> faux = fils.submit(coup(session, depart, MAUVAISE_REPONSE));
                juste.get();
                faux.get();

                tentativesObservees.add(tentativesQuiFontFoi(session));
            }
        } finally {
            fils.shutdownNow();
        }

        assertThat(tentativesObservees)
                .as("une bonne reponse et une mauvaise jouees ensemble : une seule manche est"
                        + " resolue, quel que soit l'ordre dans lequel le serveur les traite -"
                        + " aucune ecriture n'en a ecrase une autre")
                .containsOnly(1);
    }

    private Callable<Void> coup(MockHttpSession session, CyclicBarrier depart, String proposition) {
        return () -> {
            depart.await();
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                    .session(session).param("propositionChoisie", proposition));
            return null;
        };
    }

    /**
     * Le nombre de tentatives qui fait foi, relu directement sur l'etat de session, et non sur
     * l'ecran rendu au moment de la reponse - {@code EtatDePartieEnSession} est package-private,
     * accessible depuis ce test parce qu'il vit dans le meme package (meme technique que
     * EtatDePartieEnSessionTest).
     */
    private int tentativesQuiFontFoi(MockHttpSession session) {
        MockHttpServletRequest requete = new MockHttpServletRequest();
        requete.setSession(session);
        GameSession finale = new EtatDePartieEnSession(requete).lire().session();
        return finale.score().tentatives();
    }
}
