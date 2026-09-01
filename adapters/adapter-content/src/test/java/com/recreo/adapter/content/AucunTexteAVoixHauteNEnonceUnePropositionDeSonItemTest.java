package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>ADR-0075 - VOI-14 (nadia-prof, 2026-09-01, addendum section 16.4) : le texte que l'adulte
 * prononce ne doit jamais ENONCER une proposition. Qu'il la CONTIENNE est normal, et sur trois des
 * cinq lignes de la famille a voix c'est la definition meme de l'exercice.</b>
 *
 * <p><b>Ce que cette classe portait ce matin, et pourquoi ce n'est plus ce qu'elle porte -
 * l'histoire n'est pas effacee, elle est amendee.</b> La premiere version de ce garde-fou
 * encodait {@code VOI-10} telle que {@code nadia-prof} l'avait ecrite le 2026-09-01 au matin :
 * "aucune chaine du champ 1 ne contient une chaine du jeu de propositions de son item" - un
 * operateur de <b>sous-chaine</b>. Mesuree l'apres-midi meme sur les cinq paquets reels de
 * {@code lea-dev} ({@code s26-contenu}, {@code ab574e2}), cette clause rougissait sur trois
 * paquets sur cinq, 45 items sur 45 : dire "Le mot est papa." contient la lettre {@code p}, dire
 * "Le mot est velo." contient chacune des lettres de sa propre orthographe. {@code nadia-prof} a
 * relu sa clause et l'a reprise a son compte - "le huitieme cas est a moi", {@code VOI-2} le matin
 * puis {@code VOI-10} l'apres-midi, la meme forme les deux fois : juste en intention, fausse en
 * extension, l'operateur ecrit au fil de la plume sans etre mesure sur un item reel. {@code VOI-10}
 * <b>reste lisible ici avec sa date</b>, motif juste et operateur faux : une clause qui interdit au
 * stimulus de contenir la reponse interdit la competence elle-meme - extraire {@code ch} de
 * {@code chat} est la tache, pas une fuite.</p>
 *
 * <p><b>{@code VOI-14}, datee du meme jour, la remplace</b> par une frontiere qui distingue
 * contenir d'enoncer : le texte de l'adulte est decoupe en <b>jetons</b> - des suites de lettres,
 * tout le reste etant separateur -, sur une forme repliee (casse et diacritiques, comme
 * {@code VOI-10}). <b>Point 1, sur un item de choix</b> : une proposition fuit si et seulement si
 * la suite de ses propres jetons apparait comme suite <b>contigue</b> de jetons du texte de
 * l'adulte - un jeton entier, jamais une sous-chaine a l'interieur d'un jeton plus long.
 * <b>Point 2, sur un item de saisie</b> : le point 1 ne s'applique pas, il n'y a pas de proposition
 * - a sa place, les lettres de la forme de reference ne doivent pas apparaitre comme suite contigue
 * de jetons <b>d'une seule lettre chacun</b> - l'epellation, moitie calculable de {@code VOI-8}.</p>
 *
 * <p><b>Ce que cette frontiere ne remplace pas.</b> {@code VOI-7} (l'adulte dit le mot entier,
 * jamais le grapheme isole) reste entiere et distincte ; le point 1 la sert, puisqu'un texte reduit
 * au grapheme seul en fait justement un jeton isole. Et la branche saisie de l'ancienne version -
 * la fixture qui comparait le texte de l'adulte a la forme de reference elle-meme - disparait
 * plutot que de s'assouplir : sur un paquet de saisie, {@code VOI-1} n'a jamais eu d'objet, parce
 * que sa phrase entiere gouverne "ce parmi quoi l'enfant doit choisir", et dans une dictee l'enfant
 * ne choisit pas, il produit. Dire le mot entier y est l'exercice, pas une fuite.</p>
 *
 * <p><b>Ce qui reste hors de portee d'un operateur, dit sans detour ({@code nadia-prof}, section
 * 16.7) : la liste des conditions sans garde ne diminue pas d'une unite avec {@code VOI-14}. Elle
 * reste a quatre</b> - {@code VOI-3}, {@code VOI-5}, {@code VOI-7} et {@code VOI-8} a l'oral,
 * c'est-a-dire ce qui sort effectivement de la bouche d'un adulte plutot que ce qui est ecrit dans
 * le champ qu'il lit. <b>{@code VOI-14} verte ne dit pas que l'adulte n'a pas prononce la
 * reponse ; elle dit qu'on ne la lui a pas donnee a lire.</b> C'est tout, et {@code nadia-prof}
 * demande explicitement que cette classe ne le laisse pas croire autrement.</p>
 *
 * <p><b>Ce qu'une proposition veut dire ici, selon la forme de l'item</b> : sur un item de choix
 * ({@link Question}), la bonne reponse et chaque distracteur ; sur un item de saisie
 * ({@link ReponseAEcrire}), les lettres de la forme de reference seules - jamais les formes
 * acceptees, qui ne sont d'ailleurs jamais montrees a l'enfant non plus.</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - les paquets viennent du balayage
 * de {@code contenu/}, les items du chargeur reel, exactement comme
 * {@link AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest} ; <b>derivation totale</b> - chaque
 * item de chaque paquet est examine, qu'il declare {@code texteAVoixHaute} ou non (l'absence est
 * ignoree, elle n'a rien a fuir) ; <b>mise a l'epreuve</b> - les douze contre-tests de
 * {@code nadia-prof} (section 16.6 de son addendum), repris comme cahier des charges plutot que
 * reinventes, plus la mesure sur les cinq paquets reels qui motivent la frontiere.</p>
 */
class AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest";

    /** Meme budget que {@link AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest}, meme motif. */
    private static final int BUDGET_DE_TIRAGES = 500;

    /** Repliage casse + signes diacritiques, sur le patron de NormalisationDeSaisie - inchange. */
    private static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            replie.append(signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }

    /**
     * VOI-14 : decoupe un texte replie en jetons - des suites maximales de lettres, tout ce qui
     * n'est pas une lettre etant separateur.
     */
    private static List<String> jetons(String texte) {
        String replie = replier(texte);
        List<String> jetons = new ArrayList<>();
        StringBuilder courant = new StringBuilder();
        for (int rang = 0; rang < replie.length(); rang++) {
            char signe = replie.charAt(rang);
            if (Character.isLetter(signe)) {
                courant.append(signe);
            } else if (!courant.isEmpty()) {
                jetons.add(courant.toString());
                courant.setLength(0);
            }
        }
        if (!courant.isEmpty()) {
            jetons.add(courant.toString());
        }
        return jetons;
    }

    /** VOI-14 point 2 : les lettres de la forme de reference, une a une, forme repliee. */
    private static List<String> lettresDe(String formeDeReference) {
        String replie = replier(formeDeReference);
        List<String> lettres = new ArrayList<>();
        for (int rang = 0; rang < replie.length(); rang++) {
            char signe = replie.charAt(rang);
            if (Character.isLetter(signe)) {
                lettres.add(String.valueOf(signe));
            }
        }
        return lettres;
    }

    /**
     * VOI-14 : {@code jetonsCibles} apparait comme suite contigue de {@code jetonsTexte} - une
     * comparaison de jetons entiers, jamais de sous-chaines a l'interieur d'un jeton plus long.
     * Une liste de cibles vide ne peut jamais fuir : {@code Question.bonneReponse()} n'est jamais
     * vide en pratique, et une forme de reference sans lettre ne serait pas un mot.
     */
    private static boolean contientCommeJetonsContigus(List<String> jetonsCibles,
                                                         List<String> jetonsTexte) {
        if (jetonsCibles.isEmpty()) {
            return false;
        }
        int taille = jetonsCibles.size();
        for (int depart = 0; depart + taille <= jetonsTexte.size(); depart++) {
            if (jetonsTexte.subList(depart, depart + taille).equals(jetonsCibles)) {
                return true;
            }
        }
        return false;
    }

    private static Optional<String> texteAVoixHauteDe(Item item) {
        return switch (item) {
            case Question question -> question.texteAVoixHaute();
            case ReponseAEcrire reponse -> reponse.texteAVoixHaute();
        };
    }

    /** VOI-14 point 1 : l'inventaire des propositions d'un item de choix. */
    private static Set<String> propositionsDe(Question question) {
        Set<String> propositions = new HashSet<>();
        propositions.add(question.bonneReponse());
        propositions.addAll(question.distracteurs());
        return propositions;
    }

    /** Tous les items que ce paquet sait rendre, tires par la chaine reelle - meme methode que ADR-0032. */
    private static List<Item> itemsDuPaquet(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Item item = tire.orElseThrow();
            items.add(item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static List<Item> itemsDesPaquetsServis(Path racine, String racineClasspath) {
        List<Item> items = new ArrayList<>();
        for (String paquet : AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.paquets(racine)) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            items.addAll(itemsDuPaquet(contenu));
        }
        return items;
    }

    static List<String> ecartsDeFuite(Path racine, String racineClasspath) {
        List<Item> items = itemsDesPaquetsServis(racine, racineClasspath);
        assertThat(items)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun item sous %s", racine)
                .isNotEmpty();

        List<String> avecVoix = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (Item item : items) {
            Optional<String> voix = texteAVoixHauteDe(item);
            if (voix.isEmpty()) {
                continue;
            }
            avecVoix.add(item.id().value());
            List<String> jetonsDuTexte = jetons(voix.orElseThrow());

            switch (item) {
                case Question question -> {
                    for (String proposition : propositionsDe(question)) {
                        if (contientCommeJetonsContigus(jetons(proposition), jetonsDuTexte)) {
                            defauts.add("item " + item.id().value() + " : le texte a voix haute \""
                                    + voix.orElseThrow() + "\" enonce la proposition \"" + proposition
                                    + "\" comme suite contigue de jetons - VOI-14 point 1 (nadia-prof,"
                                    + " 2026-09-01, section 16.4) : l'adulte peut dire un mot qui"
                                    + " contient la reponse, il ne peut pas l'enoncer comme une unite"
                                    + " autonome de parole");
                        }
                    }
                }
                case ReponseAEcrire reponse -> {
                    List<String> lettres = lettresDe(reponse.formeDeReference());
                    if (contientCommeJetonsContigus(lettres, jetonsDuTexte)) {
                        defauts.add("item " + item.id().value() + " : le texte a voix haute \""
                                + voix.orElseThrow() + "\" epelle la forme de reference \""
                                + reponse.formeDeReference() + "\" lettre par lettre - VOI-14 point 2"
                                + " (nadia-prof, 2026-09-01, section 16.4) : dire le mot entier est"
                                + " l'exercice d'une dictee, l'epeler fait l'exercice a la place de"
                                + " l'enfant");
                    }
                }
            }
        }
        assertThat(avecVoix)
                .as("ADR-0051 : ce garde-fou ne prouve rien si aucun item ne declare texteAVoixHaute"
                        + " sous %s - la population qu'il juge serait vide", racine)
                .isNotEmpty();
        return defauts;
    }

    /**
     * ADR-0051 : cable sur le contenu de production depuis que les cinq paquets de la famille a
     * voix existent ({@code s26-contenu}, {@code ab574e2}) - la clause de non-vacuite est
     * desormais tenue par du materiel reellement deploye, pas par une fixture. Mesure reproduite de
     * {@code nadia-prof} (addendum, section 16.5) : 0 defaut sur les 75 items des cinq paquets.
     */
    @Test
    void aucunTexteAVoixHauteDeProductionNEnoncePasUnePropositionOuUneEpellation() {
        assertThat(ecartsDeFuite(CONTENU_DE_PRODUCTION, ""))
                .as("VOI-14 : le texte que l'adulte prononce ne doit jamais enoncer une proposition"
                        + " comme une unite autonome de parole, ni epeler une forme de reference")
                .isEmpty();
    }

    /**
     * Contre-test de nadia-prof (section 16.6, ligne 1) : la fixture fautive de la premiere version
     * de ce garde-fou reste rouge sous VOI-14, mais pour le motif de VOI-14 - "au debut" est enonce
     * comme une suite contigue de deux jetons entiers, pas comme une sous-chaine.
     */
    @Test
    void laFixtureQuestionFuiteResteRougeSousVoi14() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("question-fuite"), CONTRE_EXEMPLE_CLASSPATH + "/question-fuite"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("au debut"));
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 5) : l'adulte dit le grapheme seul. */
    @Test
    void unAdulteQuiDitLeGraphemeSeulFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("grapheme-seul"), CONTRE_EXEMPLE_CLASSPATH + "/grapheme-seul"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("\"ch\""));
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 6) : l'adulte lit les trois propositions. */
    @Test
    void unAdulteQuiLitLesTroisPropositionsFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("trois-propositions-lues"),
                CONTRE_EXEMPLE_CLASSPATH + "/trois-propositions-lues"))
                .isNotEmpty();
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 7) : l'adulte designe la reponse. */
    @Test
    void unAdulteQuiDesigneLaReponseFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("reponse-designee"), CONTRE_EXEMPLE_CLASSPATH + "/reponse-designee"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("au milieu"));
    }

    /**
     * Contre-test de nadia-prof (section 16.6, lignes 2 a 4) : le paquet sain, deux items d'origine
     * plus les deux exemples reels qu'elle a verifies elle-meme sur le materiel - "chat" avec
     * ch/ou/oi, "papa" avec p/m/t - restent verts. Un mot qui contient une proposition sans
     * l'enoncer comme jeton autonome ne fuit pas.
     */
    @Test
    void unPaquetSainNeRemonteAucunEcartMemeAvecUneVoix() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("sain"), CONTRE_EXEMPLE_CLASSPATH + "/sain"))
                .isEmpty();
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 9) : l'epellation, forme virgules. */
    @Test
    void uneEpellationAVirgulesFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("epellation-virgules"),
                CONTRE_EXEMPLE_CLASSPATH + "/epellation-virgules"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("velo"));
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 10) : l'epellation, forme tirets. */
    @Test
    void uneEpellationATiretsFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("epellation-tirets"),
                CONTRE_EXEMPLE_CLASSPATH + "/epellation-tirets"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("velo"));
    }

    /**
     * Contre-test de nadia-prof (section 16.6, ligne 8, et 16.3) : une dictee normale - l'adulte dit
     * le mot entier - reste verte. C'est la fixture qui fuyait sous l'ancienne branche saisie de ce
     * garde-fou (comparaison a l'identite de la forme de reference) ; elle disparait avec cette
     * branche, remplacee par l'epellation.
     */
    @Test
    void uneDicteeNormaleResteVerte() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("dictee-normale"), CONTRE_EXEMPLE_CLASSPATH + "/dictee-normale"))
                .isEmpty();
    }

    /** Contre-test de nadia-prof (section 16.6, ligne 11) : l'adulte epelle un autre mot. */
    @Test
    void unAdulteQuiEpelleUnAutreMotResteVert() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("epelle-un-autre-mot"),
                CONTRE_EXEMPLE_CLASSPATH + "/epelle-un-autre-mot"))
                .isEmpty();
    }

    @Test
    void unPerimetreSansAucunTexteAVoixHauteFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("aucune-voix"), CONTRE_EXEMPLE_CLASSPATH + "/aucune-voix"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }
}
