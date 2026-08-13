package com.recreo.adapter.web;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0019 : la preuve d'exclusion mutuelle, deterministe dans les deux sens - la ou
 * DeuxCoupsSimultanesTest reproduit le symptome complet et n'est certain que du cote du vert.
 *
 * <p>Le procede : les deux fils entrent dans le meme calcul de coup, et ce calcul attend un
 * partenaire pendant un delai de grace. Si le verrou tient, le second fil ne peut pas entrer tant
 * que le premier n'a pas ecrit ; le premier attend donc pour rien, puis le second lit l'etat que le
 * premier a ecrit, et les deux tentatives sont comptees. Si le verrou ne tient pas, les deux fils se
 * retrouvent dans le calcul avec le meme etat de depart, sans aucune attente, et une tentative
 * disparait. Le delai n'est jamais dans le chemin du rouge : un depot casse echoue immediatement.</p>
 */
class EtatDePartieEnSessionTest {

    private static final String JEU_ID = "un-jeu";

    /** Fenetre laissee au second fil pour entrer dans le calcul, s'il en a le droit. */
    private static final long DELAI_DE_GRACE_MS = 250;

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

    private static final Question QUESTION =
            new Question(new ItemId("q"), "un", "bonne", List.of("mauvaise"));

    @Test
    void deuxCoupsSimultanesSurLaMemeSessionSontComptesTousLesDeux() throws Exception {
        MockHttpServletRequest requete = requeteAvecPartieEnCours(2);
        CountDownLatch dansLeCalcul = new CountDownLatch(2);

        Thread premier = fil(requete, dansLeCalcul);
        Thread second = fil(requete, dansLeCalcul);
        premier.start();
        second.start();
        premier.join();
        second.join();

        BanditManchotSession finale = new EtatDePartieEnSession(requete).lire().session();
        assertThat(finale.score().tentatives())
                .as("deux coups joues, deux tentatives comptees : aucune ecriture n'en a ecrase une autre")
                .isEqualTo(2);
        assertThat(finale.score().reussites())
                .as("les deux coups etaient justes, aucun point gagne n'a disparu")
                .isEqualTo(2);
    }

    /**
     * Le second appui d'un double appui sur la derniere manche. La mecanique refuse de jouer une
     * manche qui n'existe plus ; l'adaptateur ne doit pas la lui demander, sans quoi l'enfant recoit
     * l'ecran d'erreur au lieu de son ecran de fin de partie.
     */
    @Test
    void unCoupRecuSurUnePartieTermineeNeRejoueRienEtRendLEtatFinal() {
        MockHttpServletRequest requete = requeteAvecPartieEnCours(1);
        EtatDePartieEnSession etat = new EtatDePartieEnSession(requete);
        BanditManchotSession terminee = etat.jouer(JEU_ID, EtatDePartieEnSessionTest::coup).session();
        assertThat(terminee.status()).isEqualTo(SessionStatus.TERMINEE);

        BanditManchotSession apresLeSecondAppui =
                etat.jouer(JEU_ID, EtatDePartieEnSessionTest::coup).session();

        assertThat(apresLeSecondAppui).isSameAs(terminee);
        assertThat(apresLeSecondAppui.score().tentatives()).isEqualTo(1);
        assertThatThrownBy(() -> coup(terminee))
                .as("le refus de la mecanique reste entier : c'est l'adaptateur qui a cesse de lui demander")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void unCoupSurUnJeuQuiNEstPasCeluiDeLaSessionEstRefuse() {
        MockHttpServletRequest requete = requeteAvecPartieEnCours(2);

        assertThatThrownBy(() -> new EtatDePartieEnSession(requete)
                .jouer("un-autre-jeu", EtatDePartieEnSessionTest::coup))
                .isInstanceOf(PartieIndisponible.class);
    }

    private Thread fil(HttpServletRequest requete, CountDownLatch dansLeCalcul) {
        return new Thread(() -> new EtatDePartieEnSession(requete).jouer(JEU_ID, actuelle -> {
            dansLeCalcul.countDown();
            attendreLePartenaire(dansLeCalcul);
            return coup(actuelle);
        }));
    }

    private static void attendreLePartenaire(CountDownLatch dansLeCalcul) {
        try {
            dansLeCalcul.await(DELAI_DE_GRACE_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException interruption) {
            Thread.currentThread().interrupt();
        }
    }

    private static BanditManchotSession coup(BanditManchotSession actuelle) {
        return (BanditManchotSession) new BanditManchot().apply(actuelle, new BanditManchotMove("bonne"));
    }

    private MockHttpServletRequest requeteAvecPartieEnCours(int nombreDeManches) {
        MockHttpServletRequest requete = new MockHttpServletRequest();
        requete.setSession(new MockHttpSession());
        BanditManchotSession session = (BanditManchotSession)
                new BanditManchot().start(contenuRepete(), bound -> 0, nombreDeManches);
        new EtatDePartieEnSession(requete).demarrer(JEU_ID, "Choisis la bonne reponse", session);
        return requete;
    }

    private static ContentSource contenuRepete() {
        return new ContentSource() {
            @Override
            public ContentDescriptor descriptor() {
                return DESCRIPTEUR;
            }

            @Override
            public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
                return Optional.of(QUESTION);
            }
        };
    }
}
