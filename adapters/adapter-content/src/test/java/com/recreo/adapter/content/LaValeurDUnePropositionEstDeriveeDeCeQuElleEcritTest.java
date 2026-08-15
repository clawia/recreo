package com.recreo.adapter.content;

import com.recreo.domain.content.Question;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0041 : la valeur d'une proposition est derivee de ce que la proposition ecrit, et une
 * proposition qui n'ecrit ni un nombre ni une duree n'a pas de valeur - elle est hors du domaine
 * des proprietes qui comparent des propositions. La lecture elle-meme vit dans
 * {@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest#valeurDe} et ses quatre mises a
 * l'epreuve (milliers, durees, et leurs versions corrigees) sont ecrites la-bas.
 *
 * <p>Ce garde-fou-ci ne juge pas la lecture : il garde ses <b>deux angles morts</b>, ceux par
 * lesquels un paquet reste vert sans etre juge, ou est juge sur un ordre faux.</p>
 *
 * <ul>
 *   <li><b>L'exclusion silencieuse.</b> Un item dont une proposition n'a pas de valeur est ecarte
 *       du calcul de rang. Sur un paquet <b>entierement</b> hors du domaine, aucun taux n'est
 *       calcule et rien n'est affirme. Sur un paquet <b>partiellement</b> hors du domaine, le
 *       garde-fou de rang rend un taux et le presente comme celui du paquet, alors qu'il ne porte
 *       que sur les items qui lui restent. Un paquet ne peut donc etre juge sur une partie de ses
 *       items que si le depot le declare, avec le nombre exact d'items ecartes.</li>
 *   <li><b>L'unite ignoree.</b> La lecture ne lit que le nombre : {@code "20 cm"} vaut 20 et
 *       {@code "1 m"} vaut 1. Trois propositions qui ne portent pas la meme unite sont donc
 *       ordonnees a l'envers sans que rien ne le dise - une valeur existe, elle est simplement
 *       fausse, ce que l'exclusion silencieuse ci-dessus ne peut pas voir. Les trois propositions
 *       d'un item du domaine portent la meme unite.</li>
 * </ul>
 *
 * <p><b>Ce que ces deux clauses ne couvrent pas</b> est ecrit dans ADR-0041, section "Ce que cette
 * definition ne couvre pas", et n'est pas paraphrase ici.</p>
 *
 * <p>ADR-0013, les trois obligations : le perimetre vient du balayage de {@code contenu/} et les
 * items du chargeur reel ; tout item a trois propositions est classe dans le domaine ou hors du
 * domaine, sans troisieme sort ; la morsure se prouve sur deux paquets neufs jamais livres, un par
 * clause, chacun avec sa version corrigee qui doit redevenir verte.</p>
 *
 * <p>ADR-0039 : les deux criteres sont calcules, jamais enumeres. {@link #PAQUETS_A_ITEMS_ECARTES}
 * n'est pas un critere mais un <b>constat exact</b> : la mesure et la declaration doivent etre
 * egales, dans les deux sens. Un paquet melange non declare est rouge, un paquet declare qui ne
 * melange plus est rouge, un nombre faux est rouge. Il n'y a pas de plafond parce qu'il n'y a
 * aucune place libre a plafonner.</p>
 */
class LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest";

    /**
     * Ce que la lecture de valeur consomme en tete d'une proposition : les chiffres, le signe et
     * les trois ecritures du separateur de milliers qu'elle retire. Ce qui reste est l'unite, au
     * sens de ce garde-fou - {@code "cm"} sur {@code "20 cm"}, la chaine vide sur {@code "20"} et
     * sur toute duree, que la lecture consomme entierement.
     */
    private static final Pattern NOMBRE_DE_TETE = Pattern.compile("^[-\\d\\u00A0\\u202F ]+");

    /**
     * Les paquets de production que la lecture de valeur juge sur une partie de leurs items, avec
     * le nombre exact d'items ecartes. Mesure du 2026-08-15 : {@code cm1-perimetre} porte 22 items
     * a trois propositions dont 20 dans le domaine ; les deux autres sont ses deux items
     * conceptuels ("Le perimetre d'une figure, c'est..." et "Comment calcule-t-on le perimetre d'un
     * carre ?"), dont les trois propositions sont du texte.
     *
     * <p>Cette declaration n'absout pas : elle rend visible que le taux de 90,0 % cite par
     * {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest} pour ce paquet porte sur 20
     * items et non sur 22.</p>
     *
     * <p>{@code cm1-grands-nombres} (S13-1) porte 28 items a trois propositions dont 14 dans le
     * domaine (les verbes "ecrire" et "decomposer", dont les trois propositions sont numeriques) et
     * 14 ecartes : les 7 items du verbe "lire" (bonne reponse et distracteurs en designation
     * litterale, par exemple "soixante-dix mille dix-huit") et les 7 items du verbe "comparer"
     * (bonne reponse et distracteurs sont les symboles "&lt;", "&gt;", "="). Ces 14 items ne sont
     * gardes par aucun garde-fou de rang automatique - ni celui-ci, qui les ecarte du domaine par
     * construction, ni {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}, qui ne
     * calcule un taux que sur les items dans le domaine. Ce qui les garde : la relecture
     * pedagogique de {@code nadia-prof} sur chaque distracteur.</p>
     */
    private static final Map<String, Integer> PAQUETS_A_ITEMS_ECARTES =
            Map.of("cm1-perimetre", 2, "cm1-grands-nombres", 14);

    /** Un item a trois propositions, vu par la lecture de valeur. */
    private record Lecture(List<String> propositions, List<Long> valeurs) {

        boolean dansLeDomaine() {
            return valeurs.stream().noneMatch(java.util.Objects::isNull);
        }

        Set<String> unites() {
            Set<String> unites = new LinkedHashSet<>();
            for (String proposition : propositions) {
                unites.add(NOMBRE_DE_TETE.matcher(proposition.strip()).replaceFirst("").strip());
            }
            return unites;
        }
    }

    private static List<Lecture> lecturesDe(String paquet, String racineClasspath) {
        List<Lecture> lectures = new ArrayList<>();
        List<Question> items = LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
                .itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
        for (Question item : items) {
            if (item.distracteurs().size() != 2) {
                continue;
            }
            List<String> propositions = List.of(item.bonneReponse(),
                    item.distracteurs().get(0), item.distracteurs().get(1));
            List<Long> valeurs = propositions.stream()
                    .map(LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest::valeurDe)
                    .toList();
            lectures.add(new Lecture(propositions, valeurs));
        }
        return lectures;
    }

    private static List<String> paquets(Path racine) {
        List<String> paquets = LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();
        return paquets;
    }

    /**
     * Le nombre d'items ecartes par paquet, pour les seuls paquets juges sur une partie de leurs
     * items : au moins un item dans le domaine et au moins un hors du domaine. Un paquet
     * entierement hors du domaine n'y figure pas - il ne fait l'objet d'aucun taux, donc d'aucune
     * affirmation (ADR-0041, non-preuve 1).
     */
    static Map<String, Integer> itemsEcartesParPaquet(Path racine, String racineClasspath) {
        Map<String, Integer> melanges = new LinkedHashMap<>();
        for (String paquet : paquets(racine)) {
            List<Lecture> lectures = lecturesDe(paquet, racineClasspath);
            long dansLeDomaine = lectures.stream().filter(Lecture::dansLeDomaine).count();
            long ecartes = lectures.size() - dansLeDomaine;
            if (dansLeDomaine > 0 && ecartes > 0) {
                melanges.put(paquet, (int) ecartes);
            }
        }
        return melanges;
    }

    @Test
    void aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare() {
        assertThat(itemsEcartesParPaquet(CONTENU_DE_PRODUCTION, ""))
                .as("ADR-0041 : un paquet dont la lecture de valeur ecarte une partie des items est"
                        + " juge sur un sous-ensemble, et le taux rendu par le garde-fou de rang est"
                        + " presente comme celui du paquet entier. Declare le paquet et le nombre"
                        + " exact d'items ecartes dans PAQUETS_A_ITEMS_ECARTES, ou rends le paquet"
                        + " homogene. A l'inverse, un paquet declare qui n'ecarte plus rien, ou dont"
                        + " le nombre a change, doit sortir de cette declaration")
                .containsExactlyInAnyOrderEntriesOf(PAQUETS_A_ITEMS_ECARTES);
    }

    static List<String> itemsAUnitesMelangees(Path racine, String racineClasspath) {
        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets(racine)) {
            for (Lecture lecture : lecturesDe(paquet, racineClasspath)) {
                if (!lecture.dansLeDomaine() || lecture.unites().size() <= 1) {
                    continue;
                }
                defauts.add(String.format(
                        "paquet %s : les trois propositions %s ne portent pas la meme unite (%s), et"
                                + " la lecture de valeur ne lit que leur nombre - l'ordre qu'elle en"
                                + " tire est celui des nombres, pas celui des grandeurs",
                        paquet, lecture.propositions(), lecture.unites()));
            }
        }
        return defauts;
    }

    @Test
    void lesTroisPropositionsDUnItemDuDomainePortentLaMemeUnite() {
        assertThat(itemsAUnitesMelangees(CONTENU_DE_PRODUCTION, ""))
                .as("ADR-0041 : la lecture de valeur ignore l'unite. Trois propositions d'unites"
                        + " differentes recoivent donc un ordre faux, sans qu'aucun garde-fou ne"
                        + " puisse s'en apercevoir - une valeur existe, elle est seulement fausse")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, clause 1 : un paquet neuf qui melange six items numeriques et quatre
     * items conceptuels, sans etre declare. C'est la forme que prendra tout paquet ou une meme
     * competence se decline en questions de definition et en questions de calcul - la forme exacte
     * de {@code cm1-perimetre}, et celle que S13-1 prendra si ses verbes "lire" et "ecrire"
     * cohabitent dans un paquet unique.
     */
    @Test
    void unPaquetNeufJugeSurUnePartieDeSesItemsEtNonDeclareFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> assertThat(itemsEcartesParPaquet(
                CONTRE_EXEMPLE.resolve("melange"), CONTRE_EXEMPLE_CLASSPATH + "/melange"))
                .as("ADR-0041 clause 1")
                .containsExactlyInAnyOrderEntriesOf(Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-melange-non-declare");
    }

    /** Le meme paquet, une fois ses items conceptuels retires, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisRenduHomogenePasseLeGardeFou() {
        assertThat(itemsEcartesParPaquet(CONTRE_EXEMPLE.resolve("melange").resolve("corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/melange/corrige"))
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, clause 2 : un paquet neuf de conversions dont trois propositions
     * portent des unites differentes. Aucun paquet de production n'est dans ce cas au 2026-08-15 -
     * la morsure ne peut donc se prouver que sur un objet neuf, et {@code cm1-mesures} montre que
     * l'objet est a un item de distance.
     */
    @Test
    void unPaquetNeufAUnitesMelangeesFaitEchouerLeGardeFou() {
        assertThat(itemsAUnitesMelangees(CONTRE_EXEMPLE.resolve("unites"),
                CONTRE_EXEMPLE_CLASSPATH + "/unites"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("paquet-neuf-unites-melangees"));
    }

    /** Le meme paquet, une fois ses trois propositions ramenees a une seule unite par item. */
    @Test
    void leMemePaquetUneFoisSesUnitesAccordeesPasseLeGardeFou() {
        assertThat(itemsAUnitesMelangees(CONTRE_EXEMPLE.resolve("unites").resolve("corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/unites/corrige"))
                .isEmpty();
    }

    /**
     * ADR-0003 : les deux clauses ne prouvent rien si la production ne leur offre aucun item du
     * domaine a juger. Ce n'est pas une redite des assertions de non-vacuite ci-dessus, qui ne
     * comptent que les paquets : un depot ou plus aucune proposition n'aurait de valeur les
     * laisserait toutes les deux vertes.
     */
    @Test
    void laProductionOffreDesItemsDansLeDomaine() {
        long dansLeDomaine = paquets(CONTENU_DE_PRODUCTION).stream()
                .flatMap(paquet -> lecturesDe(paquet, "").stream())
                .filter(Lecture::dansLeDomaine)
                .count();
        assertThat(dansLeDomaine)
                .as("aucune proposition de production n'a de valeur : les deux clauses de ce"
                        + " garde-fou ne gardent plus aucun objet")
                .isPositive();
    }
}
