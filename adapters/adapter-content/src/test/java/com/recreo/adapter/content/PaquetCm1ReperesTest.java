package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S20-2 (`DEC-0046`, section 2 ; `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`) : le
 * paquet "cm1-reperes". Competence detachee de "Relire et corriger son texte a l'aide de reperes
 * simples" par nadia-prof (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`,
 * section B), forme B (`REP-1` a `REP-4`) : une phrase unique par item, des propositions courtes
 * qui nomment ce qui manque.
 *
 * <p><b>Correction du 2026-08-26, apres REFUS de nadia-prof</b> (verdict du meme jour, commit
 * `9c8aa7b` sur `hs-nadia-arbitrages`) : deux motifs, deux causes dans sa propre specification
 * `B.3`. Motif 1, le pli - quatre libelles allant jusqu'a 34 caracteres poussaient le plus grand
 * bouton a 161 px (trois lignes) et le verdict/l'indice entierement sous le pli sur 16 items sur
 * 16 a 744 x 551. Motif 2, `REP-4` non tenue - quatre libelles ne pouvaient pas porter quatre
 * reperes plus le cas correct, il en fallait cinq.
 *
 * <p><b>Cinq libelles existent desormais, mais aucun item n'en montre plus de quatre a la fois</b> :
 * `ADR-0029` (`bootstrap`, `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`) exige un
 * `animation-delay` declare par colonne, deja borne a quatre par la clause 6 de
 * `LAnimationRetardeMaisNeDetermineRienTest` (`adapter-web`) - une cinquieme colonne est une
 * decision de forme visuelle qui appartient a `ines-design` sous l'arbitrage de `nadia-prof`, pas
 * a ce paquet. Le sous-ensemble de quatre montre par item reste coherent avec la phrase (jamais un
 * distracteur "majuscule au prenom" sur une phrase sans nom propre, jamais "point d'interrogation"
 * sur une phrase qui n'est pas une question), et "Phrase correcte" figure sur les seize items sans
 * exception - son absence serait elle-meme un indice d'erreur, la meme famille de fuite qu'ADR-0032
 * refuse ailleurs. Voir le commentaire du paquet servi pour le detail des quatre compositions.</p>
 *
 * <p>Le cinquieme libelle rend `REP-4` <b>mecaniquement verifiable</b> sur `bonneReponse` seul,
 * pour la premiere fois : les cinq propositions sont desormais des libelles atomiques (un par
 * repere, plus le cas correct). <b>`REP-3`</b> (un seul repere en jeu par item fautif) reste hors
 * de portee d'un test : verifier qu'une phrase ne cumule pas deux ecarts demande de la lire, pas
 * seulement de compter des caracteres - meme famille que `DEN-1` a `DEN-8` et `NAT-1` a `NAT-8`.</p>
 */
class PaquetCm1ReperesTest {

    private static final String PAQUET = "cm1-reperes";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    private static final String LIBELLE_MAJUSCULE_INITIALE = "Majuscule initiale";
    private static final String LIBELLE_POINT_FINAL = "Point final";
    private static final String LIBELLE_MAJUSCULE_PRENOM = "Majuscule au prénom";
    private static final String LIBELLE_POINT_INTERROGATION = "Point d'interrogation";
    private static final String LIBELLE_CORRECTE = "Phrase correcte";

    /** Le pool complet des cinq libelles possibles - jamais montres tous a la fois sur un item. */
    private static final Set<String> POOL_DE_LIBELLES = Set.of(LIBELLE_MAJUSCULE_INITIALE,
            LIBELLE_POINT_FINAL, LIBELLE_MAJUSCULE_PRENOM, LIBELLE_POINT_INTERROGATION,
            LIBELLE_CORRECTE);

    /** Les quatre reperes de l'intitule, hors le cas "phrase correcte" (`REP-4`). */
    private static final Set<String> LIBELLES_DE_REPERE = Set.of(LIBELLE_MAJUSCULE_INITIALE,
            LIBELLE_POINT_FINAL, LIBELLE_MAJUSCULE_PRENOM, LIBELLE_POINT_INTERROGATION);

    /**
     * ADR-0029 et le pli (verdict du 2026-08-26) : trois propositions par item, jamais quatre.
     * A quatre, mesure faite (`auditer-paquet.py`), la regle CSS du repli a quatre colonnes pousse
     * le bas du cabinet a 492 px sur 551, laissant verdict et indice sous le pli sur 16 items sur
     * 16 quels que soient les libelles. A trois, la regle generale s'applique (une seule rangee).
     */
    private static final int PROPOSITIONS_PAR_ITEM = 3;

    @Test
    void lePaquetCompteExactementSeizeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void aucuneVirguleDansAucunEnonce() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.enonce())
                    .as("REP-1 : la phrase de l'item \"%s\" ne contient aucune virgule", item.enonce())
                    .doesNotContain(",");
        }
    }

    @Test
    void auMoinsUnQuartDesItemsNePorteAucuneErreur() {
        List<Question> items = tousLesItems(paquet());
        long sansErreur = items.stream()
                .filter(item -> LIBELLE_CORRECTE.equals(item.bonneReponse()))
                .count();

        assertThat(sansErreur)
                .as("REP-2 : au moins un quart des %d items du paquet est marque \"%s\"",
                        items.size(), LIBELLE_CORRECTE)
                .isGreaterThanOrEqualTo((items.size() + 3) / 4);
    }

    /**
     * `REP-4` : les quatre reperes de l'intitule sont chacun representes, aucun ne depassant la
     * moitie des items. Mecaniquement verifiable depuis la correction du 2026-08-26 : chacun des
     * cinq libelles est desormais atomique (un seul repere, ou le cas correct), donc
     * {@code bonneReponse} seul classe l'item, sans lecture de la phrase.
     */
    @Test
    void chaqueRepereEstRepresenteSansEnDepasserLaMoitie() {
        List<Question> items = tousLesItems(paquet());
        Map<String, Long> parRepere = LIBELLES_DE_REPERE.stream()
                .collect(Collectors.toMap(libelle -> libelle,
                        libelle -> items.stream().filter(i -> libelle.equals(i.bonneReponse())).count()));

        parRepere.forEach((repere, compte) -> {
            assertThat(compte)
                    .as("REP-4 : le repere \"%s\" est represente par au moins un item", repere)
                    .isGreaterThanOrEqualTo(1);
            assertThat(compte)
                    .as("REP-4 : le repere \"%s\" ne depasse pas la moitie des %d items", repere,
                            items.size())
                    .isLessThanOrEqualTo(items.size() / 2);
        });
    }

    /**
     * Le coeur du motif 2 du refus : au moins trois items doivent presenter "Point final" et
     * "Point d'interrogation" ensemble, pour qu'un enfant qui ignore l'existence du second ne
     * puisse plus resoudre ces items par un raccourci ("le signe manquant est toujours le meme").
     */
    @Test
    void auMoinsTroisItemsExigentLaDistinctionEntrePointFinalEtPointDInterrogation() {
        long compte = tousLesItems(paquet()).stream()
                .filter(item -> {
                    Set<String> propositions = new HashSet<>(item.distracteurs());
                    propositions.add(item.bonneReponse());
                    return propositions.contains(LIBELLE_POINT_FINAL)
                            && propositions.contains(LIBELLE_POINT_INTERROGATION);
                })
                .count();

        assertThat(compte)
                .as("REP-4 : au moins trois items presentent les deux libelles de point ensemble,"
                        + " pour forcer la distinction que le refus du 2026-08-26 a mesuree absente")
                .isGreaterThanOrEqualTo(3);
    }

    @Test
    void lePointFinalEtLePointDInterrogationSontDeuxLibellesDistincts() {
        assertThat(LIBELLE_POINT_FINAL)
                .as("REP-4 : le motif du refus du 2026-08-26 ne doit pas revenir - les deux"
                        + " reperes de ponctuation finale ont chacun leur libelle")
                .isNotEqualTo(LIBELLE_POINT_INTERROGATION);
    }

    /**
     * ADR-0029 : jamais plus de quatre propositions sur un item, jamais moins que le pool complet
     * n'en offrirait (garde-fou generique de coherence). "Phrase correcte" figure sur les seize
     * items sans exception - son absence serait elle-meme un indice d'erreur.
     */
    @Test
    void chaqueItemProposeExactementTroisPropositionsDontToujoursLaPhraseCorrecte() {
        for (Question item : tousLesItems(paquet())) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());

            assertThat(propositions)
                    .as("item \"%s\" : toutes les propositions viennent du pool de cinq libelles",
                            item.enonce())
                    .isSubsetOf(POOL_DE_LIBELLES);
            assertThat(propositions)
                    .as("item \"%s\" : trois propositions exactement, jamais quatre (pli, verdict"
                            + " du 2026-08-26)", item.enonce())
                    .hasSize(PROPOSITIONS_PAR_ITEM);
            assertThat(propositions)
                    .as("item \"%s\" : \"Phrase correcte\" figure sur tous les items, jamais"
                            + " seulement sur ceux qui le sont - son absence serait deja une reponse",
                            item.enonce())
                    .contains(LIBELLE_CORRECTE);
            assertThat(item.distracteurs())
                    .as("item \"%s\" : deux distracteurs", item.enonce())
                    .hasSize(2);
        }
    }

    @Test
    void lesSeizeEnoncesSontDistincts() {
        List<String> enonces = tousLesItems(paquet()).stream().map(Question::enonce).toList();
        assertThat(Set.copyOf(enonces))
                .as("seize phrases distinctes")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCm1OrthographeEtGrammaireReperes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence())
                .isEqualTo("Repérer et corriger la majuscule et la ponctuation dans une phrase"
                        + " donnée (majuscule initiale, point final, majuscule au nom propre, point"
                        + " d'interrogation d'une question)");
    }

    /**
     * REP-6 : l'item donne pour correct dont l'enonce porte un point d'interrogation emploie une
     * espace insecable devant, comme le fait deja `cp-nature-mot` sur ses guillemets francais -
     * jamais une espace ordinaire, invisible a l'oeil et qui autoriserait une coupure de ligne en
     * plein signe.
     */
    @Test
    void toutPointDInterrogationDUnEnonceEstPrecedeDUneEspaceInsecable() {
        char espaceInsecable = '\u00A0';
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            int position = enonce.indexOf('?');
            if (position < 0) {
                continue;
            }
            assertThat(position)
                    .as("item \"%s\" : un point d'interrogation ne commence pas la phrase", enonce)
                    .isGreaterThan(0);
            assertThat(enonce.charAt(position - 1))
                    .as("REP-6 : '%s' - le point d'interrogation est precede d'une espace insecable,"
                            + " jamais ordinaire", enonce)
                    .isEqualTo(espaceInsecable);
        }
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
