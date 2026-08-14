package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Verdict de nadia-prof du 2026-08-14, REFUS de S12-3
 * ({@code squad/conformite/FICHE-bandit-manchot-tables-multiplication-cm1.md}) : sur les 99 items du
 * paquet livre, la bonne reponse n'etait jamais la plus petite des trois propositions, et etait la
 * plus grande sur 64 items sur 99 - un enfant qui presse toujours la plus grande gagnait 64,6 % des
 * manches au calcul exhaustif (64,4 % mesure en jouant), contre 33,3 % par hasard, sans lire un seul
 * enonce. La cause : les deux distracteurs d'un item de type "produit voisin" etaient systematiquement
 * les deux voisins du dessous, ce qui rend le rang de la bonne reponse constant sur tout le paquet.
 *
 * <p><b>La propriete est generique a toute matiere</b> : rien ici ne nomme une table de
 * multiplication ou une conversion. Tout paquet dont un item propose exactement trois propositions
 * numeriques comparables (la bonne reponse et deux distracteurs) traverse ce garde-fou des qu'il
 * existe, quelle que soit la matiere qu'il porte - {@link Item} est scelle a {@link Question}
 * (ADR-0004).</p>
 *
 * <p><b>La propriete choisie, et pourquoi ce seuil precis.</b> "Jamais constant" est trop faible : le
 * paquet refuse ne l'etait pas (35,4 % des items rendaient la bonne reponse intermediaire). "Uniformement
 * distribue" est trop fort pour un petit paquet : avec 22 items et trois rangs, la quantification seule
 * interdit de retomber exactement sur un tiers. Le seuil retenu est la <b>majorite</b> : une strategie
 * qui choisit toujours le meme rang parmi les trois propositions, sans jamais lire l'enonce, ne doit pas
 * gagner plus de la moitie des manches d'un paquet. C'est le milieu exact entre le hasard mesure
 * (33,3 %) et le taux disqualifiant mesure par nadia-prof (64,6 %), et il se lit comme un fait simple :
 * une strategie qui ignore le contenu ne doit jamais etre majoritaire.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici, et leurs items viennent du <b>chargeur reel</b> ;</li>
 *   <li><b>derivation totale</b> : chaque item tire dont les trois propositions sont numeriques produit
 *       une classification de rang, sans exception. Un item dont une proposition n'est pas numerique
 *       (un symbole de comparaison, un mot) est hors du domaine de cette propriete - le rang d'une
 *       proposition non numerique n'a pas de sens - et non un objet ignore par erreur ;</li>
 *   <li><b>mise a l'epreuve</b> : un paquet fautif neuf, jamais livre, dont la bonne reponse est
 *       toujours la plus grande des trois propositions, et sa version corrigee qui doit redevenir
 *       verte.</li>
 * </ul>
 *
 * <p>ADR-0039 : le critere n'est pas une liste enumeree, c'est une propriete calculee sur les rangs -
 * aucune promesse universelle n'est tenue par une liste de refus ici.</p>
 *
 * <p><b>Dette assumee.</b> Trois paquets du catalogue depassent aujourd'hui ce seuil :
 * {@code cm1-perimetre} (90,0 % des items numeriques rendent la bonne reponse intermediaire),
 * {@code cm1-mesures} (54,2 % la rendent la plus petite) et {@code cp-additions-jusqu-a-10} (63,1 %
 * la rendent intermediaire - un paquet combinatoire dont la fabrique tire deux distracteurs parmi les
 * ecarts symetriques autour de la somme ; choisir deux ecarts parmi un jeu equilibre de part et d'autre
 * favorise mecaniquement la combinaison "un de chaque cote", donc le rang intermediaire, bien avant
 * S12). Aucun des trois n'est dans le perimetre de S12-3 : je ne corrige pas un contenu qu'un autre
 * agent a ecrit ou une fabrique de contenu que je n'ai pas ecrite. Cette liste est exemptee, elle
 * n'absout pas : elle ne peut pas grandir (PLAFOND_DE_DETTE) et ne peut pas pourrir (chaque nom doit
 * designer un paquet qui existe reellement et qui depasse effectivement le seuil), suivant le meme
 * mecanisme que {@code LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE} (ADR-0013). Remonte a
 * sofia-sm avec ces trois chiffres dans le rapport de cette story.</p>
 */
class LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest";

    /** Une strategie a rang constant ne doit jamais etre majoritaire sur un paquet. */
    private static final double SEUIL_MAJORITE = 0.5;

    /**
     * Budget de tirages par paquet. Un paquet enumere (ListContentSource) s'epuise de lui-meme, avant
     * ce budget ; un paquet combinatoire (AdditionsBorneesContentSource) ne s'epuise jamais et ne
     * s'arrete que sur ce budget, assez large pour couvrir tous ses couples possibles avec marge.
     */
    private static final int BUDGET_DE_TIRAGES = 2000;

    /**
     * Un couple de facteurs plafonne a 10 dans ce depot, plus une marge : au-dela, un tirage
     * combinatoire n'a plus de couple neuf a offrir et boucler davantage ne changerait rien.
     */
    private static final int TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET = 200;

    private static final Pattern NOMBRE_EN_TETE = Pattern.compile("^-?\\d+");

    /**
     * Paquets exemptes de ce garde-fou, hors du perimetre de S12-3, avec le taux mesure qui justifie
     * l'exemption. Voir la Javadoc de la classe.
     */
    private static final Set<String> DETTE_DE_RANG =
            Set.of("cm1-perimetre", "cm1-mesures", "cp-additions-jusqu-a-10");

    /** Etat au 2026-08-15, s12. Ce nombre ne remonte jamais ; le baisser demande un diff visible. */
    private static final int PLAFOND_DE_DETTE = 3;

    private enum Rang {
        LA_PLUS_PETITE, INTERMEDIAIRE, LA_PLUS_GRANDE
    }

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les items distincts (par identifiant) que ce paquet rend sur un budget de tirages, en s'arretant
     * plus tot si le paquet est fini (Optional.empty()) ou si plus rien de neuf n'apparait pendant
     * TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET tirages consecutifs (paquet combinatoire epuise en pratique).
     */
    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(5)::nextInt;
        Set<ItemId> dejaVus = new java.util.HashSet<>();
        Map<ItemId, Question> items = new LinkedHashMap<>();
        int tiragesSansNouveaute = 0;
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES
                && tiragesSansNouveaute < TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            Question question = (Question) item;
            dejaVus.add(question.id());
            if (items.put(question.id(), question) == null) {
                tiragesSansNouveaute = 0;
            } else {
                tiragesSansNouveaute++;
            }
        }
        return List.copyOf(items.values());
    }

    private static Integer nombreEnTete(String proposition) {
        Matcher matcher = NOMBRE_EN_TETE.matcher(proposition.strip());
        return matcher.find() ? Integer.valueOf(matcher.group()) : null;
    }

    /**
     * Le rang de la bonne reponse parmi les trois propositions, ou {@code null} si l'item n'a pas
     * exactement deux distracteurs ou si l'une des trois propositions n'est pas numerique - hors du
     * domaine de cette propriete, pas un defaut.
     */
    private static Rang rangDe(Question item) {
        if (item.distracteurs().size() != 2) {
            return null;
        }
        Integer bonne = nombreEnTete(item.bonneReponse());
        Integer d1 = nombreEnTete(item.distracteurs().get(0));
        Integer d2 = nombreEnTete(item.distracteurs().get(1));
        if (bonne == null || d1 == null || d2 == null) {
            return null;
        }
        int rang = (d1 < bonne ? 1 : 0) + (d2 < bonne ? 1 : 0);
        return switch (rang) {
            case 0 -> Rang.LA_PLUS_PETITE;
            case 1 -> Rang.INTERMEDIAIRE;
            default -> Rang.LA_PLUS_GRANDE;
        };
    }

    private record Repartition(int petite, int intermediaire, int grande) {
        int total() {
            return petite + intermediaire + grande;
        }

        int meilleureStrategie() {
            return Math.max(petite, Math.max(intermediaire, grande));
        }
    }

    private static Repartition repartitionDe(List<Question> items) {
        int petite = 0;
        int intermediaire = 0;
        int grande = 0;
        for (Question item : items) {
            Rang rang = rangDe(item);
            if (rang == null) {
                continue;
            }
            switch (rang) {
                case LA_PLUS_PETITE -> petite++;
                case INTERMEDIAIRE -> intermediaire++;
                case LA_PLUS_GRANDE -> grande++;
            }
        }
        return new Repartition(petite, intermediaire, grande);
    }

    static void verifierQueLeRangNEstPasPredictible(Path racine, String racineClasspath,
                                                      Set<String> dette) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            if (dette.contains(paquet)) {
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0) {
                continue;
            }
            double ratio = (double) repartition.meilleureStrategie() / repartition.total();
            if (ratio > SEUIL_MAJORITE) {
                defauts.add(String.format(
                        "paquet %s : sur %d items a trois propositions numeriques, une strategie a"
                                + " rang constant (petite=%d, intermediaire=%d, grande=%d) gagne %.1f %%"
                                + " des manches sans lire l'enonce, au-dessus du seuil de majorite"
                                + " (%.1f %%)",
                        paquet, repartition.total(), repartition.petite(), repartition.intermediaire(),
                        repartition.grande(), 100 * ratio, 100 * SEUIL_MAJORITE));
            }
        }

        assertThat(defauts)
                .as("aucune strategie qui ignore l'enonce et choisit toujours le meme rang ne doit"
                        + " gagner la majorite des manches d'un paquet")
                .isEmpty();
    }

    @Test
    void leRangDeLaBonneReponseNEstPasPredictibleSurLesPaquetsDeProduction() {
        verifierQueLeRangNEstPasPredictible(CONTENU_DE_PRODUCTION, "", DETTE_DE_RANG);
    }

    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_RANG)
                .as("la dette de paquets qui depassent le seuil de majorite ne remonte jamais")
                .hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE);

        List<String> defauts = new ArrayList<>();
        for (String paquet : DETTE_DE_RANG) {
            if (!paquets(CONTENU_DE_PRODUCTION).contains(paquet)) {
                defauts.add(paquet + " : n'existe plus dans " + CONTENU_DE_PRODUCTION);
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader("").chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0
                    || (double) repartition.meilleureStrategie() / repartition.total() <= SEUIL_MAJORITE) {
                defauts.add(paquet + " : ne depasse plus le seuil, l'exemption est perimee");
            }
        }
        assertThat(defauts)
                .as("chaque nom de la dette doit designer un paquet qui existe et qui depasse encore"
                        + " reellement le seuil")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a jamais livre, dont la bonne
     * reponse est toujours la plus grande des trois propositions - la forme exacte du defaut refuse
     * par nadia-prof sur cm1-tables-multiplication.
     */
    @Test
    void unPaquetNeufOuLaBonneReponseEstToujoursLaPlusGrandeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-toujours-la-plus-grande")
                .hasMessageContaining("au-dessus du seuil de majorite");
    }

    /** Le meme paquet neuf, une fois son rang rendu non predictible, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSonRangCorrigePasseLeGardeFou() {
        verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige", Set.of());
    }
}
