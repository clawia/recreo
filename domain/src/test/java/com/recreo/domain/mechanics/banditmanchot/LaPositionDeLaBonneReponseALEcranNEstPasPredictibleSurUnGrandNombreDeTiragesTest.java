package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameSession;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * yanis-qa, rapport qualite s13 : le quatrieme axe de rang - la position a l'ecran de la bonne
 * reponse parmi les propositions - est enumerable par lecture du Fisher-Yates de
 * {@link BanditManchotSession#melanger}, mais n'etait mesure par rien avant ce test. Seconde
 * instance de la meme famille de defaut que le rang par valeur (ADR-0041) : une mesure creditee
 * d'une propriete qu'elle ne peut pas voir.
 *
 * <p><b>Les bornes sont reprises, pas inventees</b> (avis-faisabilite.md, sprint 14, section 8.2) :
 * {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest} (module adapter-content) mesure le
 * rang de la bonne reponse par <b>valeur</b> avec deux seuils de nadia-prof - aucun rang constant ne
 * doit gagner la majorite des manches (0,5) et aucun rang ne doit etre quasi absent (0,10). Ce test
 * applique les deux memes seuils a un axe disjoint : la position que {@link
 * BanditManchotSession#melanger} attribue a la bonne reponse dans la liste rendue a l'ecran, hors de
 * tout ordre de valeur des propositions.</p>
 *
 * <p><b>Le trou nomme d'avance.</b> La graine declaree ci-dessous mesure l'algorithme de melange
 * (Fisher-Yates), jamais la qualite du generateur pseudo-aleatoire reellement cable en production.
 * Un generateur de production biaise passerait ce test sans que rien ici ne le voie. Ce test est
 * deterministe par construction - jamais instable - et mord sur ce que l'algorithme lui-meme peut
 * casser : un melange qui ne deplacerait jamais le premier element, une borne de {@code nextInt}
 * fausse.</p>
 */
class LaPositionDeLaBonneReponseALEcranNEstPasPredictibleSurUnGrandNombreDeTiragesTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

    private static final String BONNE_REPONSE = "bonne";

    /** Graine declaree, arbitraire et fixe : le test rejoue toujours le meme melange. */
    private static final long GRAINE = 20260817L;

    /** Grand nombre de tirages, tres au-dessus du bruit statistique d'un partage en trois positions. */
    private static final int NOMBRE_DE_TIRAGES = 30_000;

    /** Meme seuil que celui de nadia-prof pour le rang de valeur, applique ici a la position ecran. */
    private static final double SEUIL_MAJORITE = 0.5;

    /** Meme seuil que celui de nadia-prof pour le rang de valeur, applique ici a la position ecran. */
    private static final double SEUIL_MINIMUM = 0.10;

    /**
     * Question a trois propositions rejouee a l'identique : seule la position ou le melange depose
     * la bonne reponse nous interesse, jamais le contenu de la manche. Ignore dejaVus et boucle a
     * l'infini, comme {@code BanditManchotTest.sequenceInfinie}.
     */
    private static ContentSource questionRepetee() {
        Question question = new Question(new ItemId("q1"), "enonce", BONNE_REPONSE,
                List.of("distracteur1", "distracteur2"));
        return new ContentSource() {
            @Override
            public ContentDescriptor descriptor() {
                return DESCRIPTEUR;
            }

            @Override
            public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
                return Optional.of(question);
            }

            @Override
            public boolean indicesNommentLaReponse() {
                return false;
            }
        };
    }

    @Test
    void laPositionDeLaBonneReponseNEstPasPredictibleSurUnGrandNombreDeTirages() {
        RandomSource hasard = new Random(GRAINE)::nextInt;
        BanditManchot mecanique = new BanditManchot();
        GameSession session = mecanique.start(questionRepetee(), hasard, NOMBRE_DE_TIRAGES);

        int[] occurrencesParPosition = new int[3];
        for (int tirage = 0; tirage < NOMBRE_DE_TIRAGES; tirage++) {
            BanditManchotSession manche = (BanditManchotSession) session;
            occurrencesParPosition[manche.propositions().indexOf(BONNE_REPONSE)]++;
            session = mecanique.apply(manche, new BanditManchotMove(BONNE_REPONSE));
        }

        for (int position = 0; position < occurrencesParPosition.length; position++) {
            double ratio = (double) occurrencesParPosition[position] / NOMBRE_DE_TIRAGES;
            assertThat(ratio)
                    .as("position %d : %d occurrences sur %d tirages (%.1f %%), seuil de majorite %.1f %%",
                            position, occurrencesParPosition[position], NOMBRE_DE_TIRAGES, 100 * ratio,
                            100 * SEUIL_MAJORITE)
                    .isLessThanOrEqualTo(SEUIL_MAJORITE);
            assertThat(ratio)
                    .as("position %d : %d occurrences sur %d tirages (%.1f %%), seuil minimal %.1f %%",
                            position, occurrencesParPosition[position], NOMBRE_DE_TIRAGES, 100 * ratio,
                            100 * SEUIL_MINIMUM)
                    .isGreaterThanOrEqualTo(SEUIL_MINIMUM);
        }
    }
}
