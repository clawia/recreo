package com.recreo.adapter.web;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>ADR-0105, decision 2 : le choix de vue de l'accueil vit dans la session HTTP, par le seul porteur.</b>
 * Ce que le porteur doit tenir, et que ces tests mordent :
 * <ul>
 *   <li>valeur absente : la vue par defaut, {@link VueDeLAccueil#V2} (decision de l'utilisatrice) ;</li>
 *   <li>l'ecriture <b>porte la vue visee</b> : ecrire deux fois la meme vue rend deux fois le meme etat (un
 *       double appui), et l'ecriture ne depend pas de la vue courante ;</li>
 *   <li>{@code abandonner()} n'efface pas la vue ;</li>
 *   <li>rien n'est conserve au-dela de la session (DEC-0028) : une session neuve retrouve la vue par defaut,
 *       et le porteur n'ecrit que dans la session, jamais dans une reponse.</li>
 * </ul>
 * Ce que ces tests ne gardent pas : qu'aucun autre type ne touche la session
 * ({@code AccesALEtatDeSessionTest}, ADR-0019), qu'aucun cookie ne naisse hors du marqueur de partie
 * ({@code LeSeulCookieEstLeMarqueurDePartieTest}, ADR-0105 regle 2), la route qui porte la vue (P4).
 */
class VueDeLAccueilEnSessionTest {

    private static EtatDePartieEnSession porteur(MockHttpServletRequest requete) {
        return new EtatDePartieEnSession(requete);
    }

    private static MockHttpServletRequest requete() {
        return new MockHttpServletRequest();
    }

    private static GameSession partie() {
        ContentDescriptor descripteur = new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");
        ContentSource contenu = new ContentSource() {
            @Override
            public ContentDescriptor descriptor() {
                return descripteur;
            }

            @Override
            public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
                return Optional.of(new Question(new ItemId("q"), "un", "bonne", List.of("mauvaise")));
            }

            @Override
            public boolean indicesNommentLaReponse() {
                return false;
            }
        };
        return new BanditManchot().start(contenu, bound -> 0, 1);
    }

    @Test
    void uneSessionQuiNAPasChoisiPorteLaVueParDefautV1() {
        assertThat(porteur(requete()).vue()).isEqualTo(VueDeLAccueil.V1);
        assertThat(VueDeLAccueil.PAR_DEFAUT).isEqualTo(VueDeLAccueil.V1);
    }

    @Test
    void laVueChoisieEstRelueParUnAutrePorteurDeLaMemeSession() {
        MockHttpServletRequest requete = requete();
        porteur(requete).choisirLaVue(VueDeLAccueil.V1);

        assertThat(porteur(requete).vue()).isEqualTo(VueDeLAccueil.V1);
    }

    /** Un double appui : la route porte V1, elle l'ecrit deux fois, l'etat est le meme. */
    @Test
    void ecrireDeuxFoisLaMemeVueRendDeuxFoisLeMemeEtat() {
        MockHttpServletRequest requete = requete();
        EtatDePartieEnSession etat = porteur(requete);

        etat.choisirLaVue(VueDeLAccueil.V1);
        VueDeLAccueil apresLePremierAppui = etat.vue();
        etat.choisirLaVue(VueDeLAccueil.V1);

        assertThat(etat.vue()).isEqualTo(apresLePremierAppui).isEqualTo(VueDeLAccueil.V1);
    }

    /** L'ecriture ne depend pas de l'etat courant : la meme valeur visee donne le meme resultat de V1 comme de V2. */
    @Test
    void lEcritureNeDependPasDeLaVueCourante() {
        for (VueDeLAccueil depart : VueDeLAccueil.values()) {
            EtatDePartieEnSession etat = porteur(requete());
            etat.choisirLaVue(depart);

            etat.choisirLaVue(VueDeLAccueil.V1);

            assertThat(etat.vue()).as("depuis %s, viser V1", depart).isEqualTo(VueDeLAccueil.V1);
        }
    }

    @Test
    void abandonnerNEffacePasLaVue() {
        MockHttpServletRequest requete = requete();
        EtatDePartieEnSession etat = porteur(requete);
        etat.demarrer("un-jeu", "consigne", null, partie());
        etat.choisirLaVue(VueDeLAccueil.V1);

        etat.abandonner();

        assertThat(etat.lire().jeuId()).as("la partie est bien abandonnee").isNull();
        assertThat(etat.vue()).as("quitter une partie n'est pas changer d'affichage").isEqualTo(VueDeLAccueil.V1);
    }

    @Test
    void demarrerUnePartieNeToucheQueLaPartieEtPasLaVue() {
        EtatDePartieEnSession etat = porteur(requete());
        etat.choisirLaVue(VueDeLAccueil.V1);

        etat.demarrer("un-jeu", "consigne", null, partie());

        assertThat(etat.vue()).isEqualTo(VueDeLAccueil.V1);
    }

    /** DEC-0028 : le choix disparait avec la session, rien ne le conserve ailleurs. */
    @Test
    void uneSessionNeuveRetrouveLaVueParDefaut() {
        MockHttpServletRequest premiere = requete();
        porteur(premiere).choisirLaVue(VueDeLAccueil.V2);
        assertThat(porteur(premiere).vue()).isEqualTo(VueDeLAccueil.V2);

        MockHttpServletRequest neuve = requete();
        neuve.setSession(new MockHttpSession());

        assertThat(porteur(neuve).vue()).isEqualTo(VueDeLAccueil.V1);
    }

    /** La vue n'existe que comme attribut de la session : une session invalidee ne la porte plus. */
    @Test
    void uneSessionInvalideeNePorteQueLaVueParDefaut() {
        MockHttpServletRequest requete = requete();
        MockHttpSession session = (MockHttpSession) requete.getSession();
        porteur(requete).choisirLaVue(VueDeLAccueil.V2);
        assertThat(Collections.list(session.getAttributeNames())).contains("recreo.vue");

        session.invalidate();
        MockHttpServletRequest suivante = requete();

        assertThat(porteur(suivante).vue()).isEqualTo(VueDeLAccueil.V1);
    }

    @Test
    void leSegmentDUneRouteDesigneUneVueOuRienDuTout() {
        assertThat(VueDeLAccueil.depuis("V1")).contains(VueDeLAccueil.V1);
        assertThat(VueDeLAccueil.depuis("v2")).contains(VueDeLAccueil.V2);
        assertThat(VueDeLAccueil.depuis("v3")).isEmpty();
        assertThat(VueDeLAccueil.depuis("")).isEmpty();
        assertThat(VueDeLAccueil.depuis(null)).isEmpty();
        assertThat(VueDeLAccueil.depuis("autre")).isEmpty();
    }
}
