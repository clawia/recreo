package com.recreo.adapter.content;

import com.recreo.adapter.content.LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.Lue;
import com.recreo.domain.content.Question;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0041 : la valeur d'une proposition est derivee de ce que la proposition ecrit, et une
 * proposition qui n'ecrit ni un nombre, ni une somme, ni une duree n'a pas de valeur - elle est hors
 * du domaine des proprietes qui comparent des propositions. La lecture elle-meme vit dans
 * {@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest#lireValeur} et ses mises a
 * l'epreuve (milliers, durees, sommes, et leurs versions corrigees) sont ecrites la-bas.
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
 *   <li><b>Le reste ignore.</b> La lecture ne consomme pas toujours toute la proposition :
 *       {@code "20 cm"} vaut 20 et laisse {@code "cm"}, {@code "1 m"} vaut 1 et laisse {@code "m"}.
 *       Ce qu'elle laisse n'entre pas dans la valeur ; si ce reste differe d'une proposition a
 *       l'autre, les trois valeurs ne mesurent pas la meme chose et leur ordre est faux, sans que
 *       rien ne le dise - une valeur existe, elle est simplement fausse, ce que l'exclusion
 *       silencieuse ci-dessus ne peut pas voir. Les trois propositions d'un item du domaine
 *       laissent le meme reste.</li>
 * </ul>
 *
 * <p><b>Pourquoi le reste et non l'unite</b> (ADR-0041, amendement du 2026-08-15). La clause visait
 * l'identite d'une <b>unite</b> et exigeait l'identite d'un <b>texte</b> : ce qui suivait le premier
 * nombre, lu par un motif ecrit ici, a cote de la lecture de valeur. Les deux lectures ont diverge
 * des que la lecture de valeur a su lire une somme : sur {@code "600 000 + 40"} elle consomme tout,
 * le motif d'ici s'arretait au premier nombre et rendait {@code "+ 40"}. Sept items "decomposer"
 * reels devenaient alors des faux positifs d'une clause d'unite, sur un paquet qui n'ecrit aucune
 * unite. Le reste est desormais rendu par la lecture elle-meme, dont il est le complement exact : il
 * n'y a plus deux lectures a faire diverger. Une unite ecrite reste le cas le plus courant de reste
 * non vide, ce n'est pas le seul.</p>
 *
 * <p><b>Ce que ces deux clauses ne couvrent pas</b> est ecrit dans ADR-0041, section "Ce que cette
 * definition ne couvre pas", et n'est pas paraphrase ici.</p>
 *
 * <p>ADR-0013, les trois obligations : le perimetre vient du balayage de {@code contenu/} et les
 * items du chargeur reel ; tout item a trois propositions est classe dans le domaine ou hors du
 * domaine, sans troisieme sort ; la morsure se prouve sur deux paquets neufs jamais livres, un par
 * clause, chacun avec sa version corrigee qui doit redevenir verte, et - pour la clause du reste -
 * sur une mutation de <b>chaque</b> item reel du domaine
 * ({@link #chaqueItemReelDontUnResteEstReecritFaitEchouerLaClause}).</p>
 *
 * <p>ADR-0039 : les deux criteres sont calcules, jamais enumeres. {@link #PAQUETS_A_ITEMS_ECARTES}
 * n'est pas un critere mais un <b>constat exact</b> : la mesure et la declaration doivent etre
 * egales, dans les deux sens. Un paquet melange non declare est rouge, un paquet declare qui ne
 * melange plus est rouge, un nombre faux est rouge. Il n'y a pas de plafond parce qu'il n'y a
 * aucune place libre a plafonner.</p>
 *
 * <p><b>Lot 5a de s24, decision 9 : borne, par la borne du garde-fou qu'il sert.</b> Cette classe
 * est entierement au service de {@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest} : elle
 * ne juge que la fiabilite de son taux de rang. Ce garde-fou-la etant borne a l'item qui presente des
 * propositions (decision 10), celui-ci l'est par la meme raison - l'etendre serait le laisser garder
 * un calcul que plus personne ne fait. Un paquet de saisie n'entre pas dans
 * {@link #PAQUETS_A_ITEMS_ECARTES} : il n'est pas <i>melange</i>, il est hors du domaine en entier,
 * cas qu'ADR-0041 traite deja - aucun taux n'est calcule et rien n'est affirme.</p>
 */
class LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest";

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
     *
     * <p>{@code cm1-fractions} (S24-FRA) porte 24 items a trois propositions dont 6 dans le domaine
     * et 18 ecartes. Les 6 du domaine sont ceux de la forme (d) de FRA-2, "la lecture du
     * denominateur seul", dont les trois propositions sont des nombres nus. Les 18 autres sont les
     * formes (a), (b) et (c) : leurs propositions sont soit une denomination en toutes lettres
     * ("trois huitiemes"), soit une fraction ecrite en chiffres ("3/8"), que la lecture de valeur
     * ecarte depuis ADR-0068 parce que le reste apres le premier nombre porte encore un chiffre.
     * Mesure du 2026-08-31, sur le paquet servi.</p>
     *
     * <p>{@code cp-nombres-jusqu-a-100} (S39-DEN) porte 21 items a trois propositions dont 14 dans
     * le domaine (les verbes "ecrire" et le verbe "decomposer" de la ligne, dont les trois
     * propositions sont numeriques - un nombre nu, ou un nombre suivi de "dizaines"/"unités") et 7
     * ecartes : les 7 items du verbe "lire" (bonne reponse et distracteurs en designation
     * litterale, par exemple "soixante-douze"), memes motif et forme que les items "lire" de
     * {@code cm1-grands-nombres} ci-dessus. Mesure du 2026-09-22, sur le paquet servi.</p>
     *
     * <p>{@code cp-emc-alerte} (S47-EMC-ALERTE-CP) porte 11 items a trois propositions dont 3 dans
     * le domaine (la tache "service nomme" : "15", "17", "18", les trois numeros nus) et 8 ecartes
     * (la tache "situation decrite" : bonne reponse et distracteurs en texte libre, "prévenir un
     * adulte", "continuer à jouer sans rien dire"...). Mesure du 2026-10-02, sur le paquet servi.</p>
     *
     * <p>{@code ce1-sens-multiplication} (S51-MAT-MULTIPLICATION, MAT-CE1-06) porte 16 items a trois
     * propositions dont 6 dans le domaine (les six items "multiplication vers addition reiteree" : trois
     * sommes, "5 + 5 + 5", que la lecture de valeur lit comme leur total) et 10 ecartes : les quatre
     * items "addition reiteree vers multiplication" et les six items de reseau de points, dont les trois
     * propositions sont des produits ecrits ("3 × 5"), ou un chiffre reste apres le premier nombre
     * (ADR-0068). Mesure du 2026-10-06, sur le paquet servi.</p>
     */
    private static final Map<String, Integer> PAQUETS_A_ITEMS_ECARTES =
            Map.of("cm1-perimetre", 2, "cm1-grands-nombres", 14, "cm1-fractions", 18,
                    "cp-nombres-jusqu-a-100", 7, "cp-emc-alerte", 8, "ce1-sens-multiplication", 10);

    /** Un item a trois propositions, vu par la lecture de valeur. */
    private record Lecture(List<String> propositions, List<Lue> lues) {

        boolean dansLeDomaine() {
            return lues.stream().noneMatch(java.util.Objects::isNull);
        }

        /**
         * Ce que la lecture n'a pas consomme, sur chacune des trois propositions. Ce n'est pas une
         * seconde lecture du texte : c'est le complement exact de la premiere, rendu par elle.
         */
        Set<String> restes() {
            Set<String> restes = new LinkedHashSet<>();
            for (Lue lue : lues) {
                restes.add(lue.reste());
            }
            return restes;
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
            List<Lue> lues = new ArrayList<>();
            for (String proposition : propositions) {
                lues.add(LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
                        .lireValeur(proposition));
            }
            lectures.add(new Lecture(propositions, lues));
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

    static List<String> itemsAResteDeLectureDifferent(Path racine, String racineClasspath) {
        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets(racine)) {
            for (Lecture lecture : lecturesDe(paquet, racineClasspath)) {
                if (!lecture.dansLeDomaine() || lecture.restes().size() <= 1) {
                    continue;
                }
                defauts.add(String.format(
                        "paquet %s : les trois propositions %s ne laissent pas le meme reste apres"
                                + " lecture de leur valeur (%s) - la valeur ne porte donc pas sur la"
                                + " meme chose d'une proposition a l'autre, et l'ordre qu'elle en"
                                + " tire n'est pas celui des grandeurs comparees",
                        paquet, lecture.propositions(), lecture.restes()));
            }
        }
        return defauts;
    }

    @Test
    void lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture() {
        assertThat(itemsAResteDeLectureDifferent(CONTENU_DE_PRODUCTION, ""))
                .as("ADR-0041 : ce que la lecture de valeur n'a pas consomme n'entre pas dans la"
                        + " valeur. Si ce reste differe d'une proposition a l'autre, les trois"
                        + " valeurs ne mesurent pas la meme chose et leur ordre est faux, sans"
                        + " qu'aucun autre garde-fou ne puisse s'en apercevoir - une valeur existe,"
                        + " elle est seulement fausse. Une unite ecrite ('cm' face a 'm') est le cas"
                        + " le plus courant de reste different, ce n'est pas le seul")
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
        assertThat(itemsAResteDeLectureDifferent(CONTRE_EXEMPLE.resolve("unites"),
                CONTRE_EXEMPLE_CLASSPATH + "/unites"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("paquet-neuf-unites-melangees"));
    }

    /** Le meme paquet, une fois ses trois propositions ramenees a une seule unite par item. */
    @Test
    void leMemePaquetUneFoisSesUnitesAccordeesPasseLeGardeFou() {
        assertThat(itemsAResteDeLectureDifferent(CONTRE_EXEMPLE.resolve("unites").resolve("corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/unites/corrige"))
                .isEmpty();
    }

    /**
     * Le meme texte, dont le reste que la lecture a laisse est remplace par un autre. Sur
     * {@code "4000 cm"} et un autre reste {@code "m"}, rend {@code "4000 m"} : la mutation M3
     * d'ADR-0041, ecrite comme une operation sur ce que le depot ecrit deja plutot que comme une
     * proposition inventee.
     */
    private static String resteReecrit(String proposition, String reste, String autreReste) {
        String texte = proposition.strip();
        String sansReste = texte.substring(0, texte.length() - reste.length()).strip();
        return autreReste.isEmpty() ? sansReste : sansReste + " " + autreReste;
    }

    /** Tous les restes non vides que la production ecrit reellement, jamais une liste de ma main. */
    private static Set<String> restesEcritsParLaProduction() {
        Set<String> restes = new LinkedHashSet<>();
        for (String paquet : paquets(CONTENU_DE_PRODUCTION)) {
            for (Lecture lecture : lecturesDe(paquet, "")) {
                if (!lecture.dansLeDomaine()) {
                    continue;
                }
                lecture.restes().stream().filter(reste -> !reste.isEmpty()).forEach(restes::add);
            }
        }
        return restes;
    }

    /**
     * ADR-0003, et la lecon de s05 : une fixture ecrite par l'auteur d'une regle ressemble a ce
     * qu'il imagine. Le paquet neuf ci-dessus prouve que la clause mord ; il ne prouve pas qu'elle
     * morde sur ce que le depot ecrit. Cette mise a l'epreuve-ci part de <b>chaque</b> item du
     * domaine de la production, et remplace le reste d'un seul de ses distracteurs par un autre
     * reste <b>que la production ecrit ailleurs</b> - "4000 cm" devient "4000 m", ce qui est la
     * mutation M3 d'ADR-0041 appliquee a tous les items a la fois. Aucune unite n'est nommee ici :
     * l'ensemble des restes est mesure sur le corpus (ADR-0039).
     *
     * <p>Deux faits sont exiges ensemble : la production telle qu'elle est ecrite ne laisse aucun
     * item a restes differents, et <b>chacun</b> de ses items mutes en laisse un. Le second est ce
     * qui distingue une clause qui garde d'une clause qui se tait.</p>
     */
    @Test
    void chaqueItemReelDontUnResteEstReecritFaitEchouerLaClause() {
        Set<String> restesReels = restesEcritsParLaProduction();
        assertThat(restesReels)
                .as("la mutation n'a rien a ecrire si la production n'ecrit aucun reste")
                .isNotEmpty();

        List<String> mutantsRestesMuets = new ArrayList<>();
        int mutants = 0;
        for (String paquet : paquets(CONTENU_DE_PRODUCTION)) {
            for (Lecture lecture : lecturesDe(paquet, "")) {
                if (!lecture.dansLeDomaine()) {
                    continue;
                }
                String reste = lecture.lues().get(2).reste();
                String autreReste = restesReels.stream()
                        .filter(candidat -> !candidat.equals(reste))
                        .findFirst()
                        .orElseThrow();
                List<String> mutees = new ArrayList<>(lecture.propositions());
                mutees.set(2, resteReecrit(mutees.get(2), reste, autreReste));
                mutants++;
                if (restesDe(mutees).size() <= 1) {
                    mutantsRestesMuets.add(paquet + " : " + mutees);
                }
            }
        }

        assertThat(mutants)
                .as("aucun item du domaine a muter : la clause ne garde plus rien")
                .isPositive();
        assertThat(mutantsRestesMuets)
                .as("un item reel dont un seul distracteur change de reste doit etre vu par la"
                        + " clause. S'il ne l'est pas, la clause compare autre chose que ce que la"
                        + " lecture a laisse")
                .isEmpty();
    }

    private static Set<String> restesDe(List<String> propositions) {
        Set<String> restes = new LinkedHashSet<>();
        for (String proposition : propositions) {
            Lue lue = LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
                    .lireValeur(proposition);
            restes.add(lue == null ? null : lue.reste());
        }
        return restes;
    }

    /**
     * <b>ADR-0068, troisieme angle mort : le chiffre laisse dans le reste.</b> La clause du reste
     * ci-dessus compare les trois restes d'un item entre eux ; elle est donc muette sur un item
     * dont les trois propositions laissent le <b>meme</b> reste et dont ce reste porte pourtant un
     * nombre que la lecture a ignore. C'est le cas d'une proposition qui ecrit une liste de
     * nombres : la lecture rend le premier et abandonne les autres, sur les trois propositions a la
     * fois. Depuis ADR-0068, une telle proposition n'a plus de valeur du tout.
     *
     * <p><b>La mutation, sur le corpus reel et non sur une fixture</b> (lecon de s05, et meme geste
     * que {@link #chaqueItemReelDontUnResteEstReecritFaitEchouerLaClause}) : chaque proposition du
     * domaine que la production ecrit se voit coller derriere elle une autre proposition <b>du meme
     * item</b>, separee par la virgule. Le texte obtenu est une liste de nombres que la production
     * ecrit vraiment - la forme exacte qu'un paquet de rangement presente - et aucun nombre n'y est
     * de ma main. Chacune de ces mutations doit cesser d'avoir une valeur.</p>
     *
     * <p>Ce que cette mise a l'epreuve ne prouve pas : que le corpus non mute garde ses valeurs.
     * C'est {@link #aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare} qui l'exige,
     * en comparant a l'egalite exacte le nombre d'items ecartes par paquet - une lecture devenue
     * trop severe la ferait rougir immediatement.</p>
     */
    @Test
    void chaqueValeurReelleCesseDEtreLueDesQuUneAutrePropositionLuiEstCollee() {
        List<String> lecturesQuiSurvivent = new ArrayList<>();
        int mutants = 0;
        for (String paquet : paquets(CONTENU_DE_PRODUCTION)) {
            for (Lecture lecture : lecturesDe(paquet, "")) {
                if (!lecture.dansLeDomaine()) {
                    continue;
                }
                List<String> propositions = lecture.propositions();
                for (int rang = 0; rang < propositions.size(); rang++) {
                    String mutee = propositions.get(rang) + ", "
                            + propositions.get((rang + 1) % propositions.size());
                    mutants++;
                    Lue lue = LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
                            .lireValeur(mutee);
                    if (lue != null) {
                        lecturesQuiSurvivent.add(String.format(
                                "paquet %s : \"%s\" est lue %d et laisse \"%s\"",
                                paquet, mutee, lue.valeur(), lue.reste()));
                    }
                }
            }
        }

        assertThat(mutants)
                .as("aucune proposition du domaine a muter : cette mise a l'epreuve ne prouve rien")
                .isPositive();
        assertThat(lecturesQuiSurvivent)
                .as("ADR-0068 : une proposition qui ecrit plusieurs nombres n'a pas de valeur"
                        + " comparable. Une lecture qui s'arrete au premier et laisse les autres"
                        + " derriere elle rend la valeur d'un morceau en la presentant comme celle"
                        + " de la proposition entiere - et les garde-fous qui comparent ces valeurs"
                        + " jugent alors un objet qui n'existe pas")
                .isEmpty();
    }

    /**
     * <b>ADR-0068, la forme decimale a la virgule - affirmee par sa Javadoc, prouvee par rien
     * jusqu'ici.</b> {@code unChiffreEstRestePourCompte} nomme <i>"une ecriture decimale a la
     * virgule"</i> parmi les formes qu'il attrape, et aucune assertion du depot ne l'exercait : le
     * corpus servi n'en ecrit aucune (mesure du 2026-09-22, aucun fichier de {@code contenu/} ne
     * porte un chiffre suivi d'une virgule puis d'un chiffre). <b>Le premier paquet qui en ecrira
     * une est celui de la monnaie CE1</b>, et sa conception repose entierement sur ce que cette
     * lecture rend. Une affirmation de Javadoc sur laquelle un lot s'appuie est un souhait tant
     * qu'une assertion ne la tient pas.
     *
     * <p>Les deux formes voisines sont la pour la meme raison : un montant en une seule unite reste
     * dans le domaine avec son unite pour reste, un montant en deux unites en sort. Ce test ne dit
     * pas laquelle des deux ecritures le paquet doit choisir - c'est la contrainte de conception de
     * l'avis de faisabilite de s39, section 21, et le choix du libelle appartient a
     * {@code nadia-prof}.</p>
     */
    @Test
    void uneEcritureDecimaleALaVirguleSortDuDomaineEtUnMontantEntierYReste() {
        assertThat(LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.lireValeur("3,50 euros"))
                .as("ADR-0068 : \"3,50 euros\" laisse \",50 euros\" derriere la lecture de 3, et"
                        + " cette valeur-la n'est pas celle de la proposition")
                .isNull();
        assertThat(LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
                .lireValeur("3 euros 50"))
                .as("meme cas sous une autre graphie : le reste porte encore un chiffre")
                .isNull();
        assertThat(LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.lireValeur("350 centimes"))
                .as("un montant ecrit en une seule unite entiere reste dans le domaine, avec son"
                        + " unite pour reste - c'est la clause du reste d'ADR-0041 qui le garde")
                .isEqualTo(new LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.Lue(350,
                        "centimes"));
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
