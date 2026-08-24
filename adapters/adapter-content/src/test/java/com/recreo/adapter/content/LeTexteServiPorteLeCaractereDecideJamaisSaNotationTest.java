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
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0060. Deux defauts de la meme famille : le texte que l'enfant lit n'est pas fait des
 * caracteres qu'on a decides, et aucune porte ne le voit.
 *
 * <p><b>Le declencheur, s19.</b> marc-dev a ecrit a la main, via un heredoc, la notation d'un espace
 * insecable - la sequence d'echappement de six caracteres ASCII - a la place du caractere. Le YAML
 * reste valide, la compilation passe, les tests passent, le paquet se charge. Il l'a trouve en
 * comparant octet par octet contre un paquet de reference ; rien dans ce depot ne l'aurait vu.
 *
 * <p><b>Ce que la mesure a etabli, et qui n'etait pas su.</b> Sur snakeyaml 2.6, la resolution
 * depend du style du scalaire : dans un scalaire a guillemets doubles - le style de tous les paquets
 * servis - la sequence <i>est</i> resolue, et le texte servi est juste ; dans un scalaire a
 * apostrophes ou nu, elle ne l'est pas et les six caracteres atteignent l'enfant ; une sequence
 * inconnue en guillemets doubles fait echouer le chargement, donc bruyamment. Ce garde-fou juge
 * <b>le texte servi</b>, jamais les octets du fichier : c'est le texte que l'enfant lit qui est
 * l'objet, et juger le fichier produirait un rouge sur un commentaire (le paquet
 * {@code cm1-synonymes.yaml} en porte un) et un rouge sur une notation que le parseur a deja
 * resolue.
 *
 * <p><b>Clause 1 - la notation d'un caractere n'est pas le caractere.</b> Aucun texte servi ne porte
 * une contre-barre, une entite de caractere HTML, ni une notation de point de code. Trois formes,
 * jamais trois noms d'incidents (ADR-0039, s11) : chacune est la grammaire entiere d'une facon
 * d'ecrire un caractere sans l'ecrire.
 *
 * <p><b>Clause 2 - le caractere decide est present.</b> SYN-9 (nadia-prof, s17, bloquante) puis
 * ANT-5 (arbitrage prealable s19) exigent que les espaces a l'interieur des guillemets francais
 * soient insecables. C'est une decision pedagogique deja prise deux fois, appliquee a trois paquets,
 * et qu'aucun automate ne verifiait - un espace ordinaire y est <b>invisible a l'oeil</b>, sur le
 * fichier comme a l'ecran, et ne se voit qu'au retour a la ligne qu'il autorise. C'est le membre
 * silencieux de la famille ; la notation non resolue en est le membre bruyant.
 *
 * <p><b>Perimetre : le texte des items servis, jamais le catalogue.</b> Le perimetre derive du
 * repertoire de contenu (ADR-0013, obligation 1), chaque paquet est charge par le chargeur reel et
 * ses items sont tires par la meme chaine que celle qu'un enfant traverse (obligation 2). Le titre,
 * la consigne et le descripteur sont exclus pour un motif verifiable et non par commodite :
 * {@code TexteAccentueExposeParLePortEntrantTest} les inventorie chaine par chaine et les compare
 * <b>par egalite</b>, si bien qu'une notation y serait deja rouge. Le texte d'un item n'est
 * inventorie nulle part, et c'est exactement la ou le defaut est ne.
 *
 * <p><b>Note d'ecriture, de la meme famille que ce qu'il garde</b> : la notation en cause ne peut pas
 * s'ecrire telle quelle dans un fichier {@code .java}, meme en commentaire - le compilateur resout
 * ces sequences avant de decouper les jetons. Elle est donc nommee en toutes lettres ici, et les
 * caracteres invisibles du code sont ecrits par leur valeur numerique.
 */
class LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest";

    /** Meme borne que les autres garde-fous de paquet : un paquet combinatoire ne s'epuise jamais. */
    private static final int TIRAGES = 60;

    private static final char GUILLEMET_OUVRANT = (char) 0x00AB;
    private static final char GUILLEMET_FERMANT = (char) 0x00BB;
    private static final char ESPACE_INSECABLE = (char) 0x00A0;

    /**
     * Toute contre-barre. Le critere est la contre-barre elle-meme, pas la liste des lettres qui la
     * suivent : une sequence inconnue est aussi fautive qu'une sequence connue, et enumerer les
     * lettres rejouerait la faute de s11 - un critere bati sur des noms d'incidents clos. Aucun texte
     * servi du depot n'en porte, et un contenu qui en aurait besoin un jour amenderait ADR-0060.
     */
    private static final Pattern CONTRE_BARRE = Pattern.compile("\\\\");

    /** Toute entite de caractere HTML, nommee ou numerique. Un fragment de balisage n'est pas du texte. */
    private static final Pattern ENTITE_HTML =
            Pattern.compile("&(?:#[0-9]{1,7}|#[xX][0-9A-Fa-f]{1,6}|[A-Za-z][A-Za-z0-9]{1,31});");

    /** La notation d'un point de code Unicode, celle qu'on ecrit dans une conversation, pas dans un texte. */
    private static final Pattern POINT_DE_CODE = Pattern.compile("[uU]\\+[0-9A-Fa-f]{4,6}");

    private static final List<Pattern> NOTATIONS = List.of(CONTRE_BARRE, ENTITE_HTML, POINT_DE_CODE);

    /** Un texte tel que l'enfant le recoit, avec l'origine qui l'a produit (pour le message). */
    private record TexteServi(String paquet, String champ, String enonceDeLItem, String valeur) {

        String origine() {
            return "paquet " + paquet + ", item \"" + enonceDeLItem + "\", champ " + champ;
        }
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

    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < TIRAGES; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    static List<TexteServi> textesServis(Path racine, String racineClasspath) {
        List<TexteServi> textes = new ArrayList<>();
        for (String paquet : paquets(racine)) {
            ContentSource source = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            for (Question item : itemsTires(source)) {
                textes.add(new TexteServi(paquet, "enonce", item.enonce(), item.enonce()));
                textes.add(new TexteServi(paquet, "bonneReponse", item.enonce(), item.bonneReponse()));
                for (int rang = 0; rang < item.distracteurs().size(); rang++) {
                    textes.add(new TexteServi(paquet, "distracteur " + (rang + 1), item.enonce(),
                            item.distracteurs().get(rang)));
                }
                item.indice().ifPresent(indice ->
                        textes.add(new TexteServi(paquet, "indice", item.enonce(), indice)));
                item.explication().ifPresent(explication ->
                        textes.add(new TexteServi(paquet, "explication", item.enonce(), explication)));
            }
        }
        return textes;
    }

    private static String pointsDeCode(String valeur) {
        StringBuilder rendu = new StringBuilder();
        valeur.codePoints().forEach(point -> rendu.append(String.format("U+%04X ", point)));
        return rendu.toString().trim();
    }

    static void verifierQueChaqueTexteServiPorteSesCaracteres(Path racine, String racineClasspath) {
        List<TexteServi> textes = textesServis(racine, racineClasspath);
        assertThat(textes)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun texte servi sous %s", racine)
                .isNotEmpty();

        List<String> notations = new ArrayList<>();
        List<String> espacesSecables = new ArrayList<>();
        int guillemetsExamines = 0;

        for (TexteServi texte : textes) {
            String valeur = texte.valeur();
            for (Pattern notation : NOTATIONS) {
                Matcher trouve = notation.matcher(valeur);
                while (trouve.find()) {
                    notations.add(String.format(
                            "%s : le fragment \"%s\" est la notation d'un caractere, pas le caractere."
                                    + " Le texte servi vaut %s. Ecrire le caractere lui-meme dans le"
                                    + " paquet (ADR-0060, clause 1)",
                            texte.origine(), trouve.group(), pointsDeCode(valeur)));
                }
            }

            for (int rang = 0; rang < valeur.length(); rang++) {
                char lettre = valeur.charAt(rang);
                if (lettre == GUILLEMET_OUVRANT) {
                    guillemetsExamines++;
                    if (rang + 1 >= valeur.length() || valeur.charAt(rang + 1) != ESPACE_INSECABLE) {
                        espacesSecables.add(String.format(
                                "%s : le guillemet francais ouvrant n'est pas suivi d'un espace"
                                        + " insecable. Le texte servi vaut %s (SYN-9, ANT-5,"
                                        + " ADR-0060, clause 2)",
                                texte.origine(), pointsDeCode(valeur)));
                    }
                }
                if (lettre == GUILLEMET_FERMANT) {
                    guillemetsExamines++;
                    if (rang == 0 || valeur.charAt(rang - 1) != ESPACE_INSECABLE) {
                        espacesSecables.add(String.format(
                                "%s : le guillemet francais fermant n'est pas precede d'un espace"
                                        + " insecable. Le texte servi vaut %s (SYN-9, ANT-5,"
                                        + " ADR-0060, clause 2)",
                                texte.origine(), pointsDeCode(valeur)));
                    }
                }
            }
        }

        assertThat(notations)
                .as("ADR-0060 clause 1 : un texte servi porte le caractere, jamais sa notation - une"
                        + " sequence d'echappement que le parseur n'a pas resolue, une entite HTML ou"
                        + " une notation de point de code atteint l'enfant telle quelle")
                .isEmpty();
        assertThat(espacesSecables)
                .as("ADR-0060 clause 2 : SYN-9 et ANT-5 exigent un espace insecable a l'interieur des"
                        + " guillemets francais - un espace ordinaire y est invisible a l'oeil et"
                        + " autorise un retour a la ligne entre le guillemet et le mot marque")
                .isEmpty();
        assertThat(guillemetsExamines)
                .as("ADR-0051 : aucun texte servi sous %s ne porte de guillemet francais, la clause 2"
                        + " de ce garde-fou n'examine donc aucun objet et ne prouve rien", racine)
                .isPositive();
    }

    @Test
    void chaqueTexteServiEnProductionPorteSesCaracteres() {
        verifierQueChaqueTexteServiPorteSesCaracteres(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part. Son
     * indice est ecrit dans un scalaire a apostrophes, le seul style ou la sequence d'echappement
     * survit au parseur - c'est la forme exacte du defaut de s19, et le seul style qui la laisse
     * atteindre l'enfant. Il porte par ailleurs un item conforme a la clause 2, pour que ce rouge-ci
     * ne puisse pas etre confondu avec celui de la non-vacuite.
     */
    @Test
    void unPaquetNeufDontUnTexteServiPorteUneNotationFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("notation"), CONTRE_EXEMPLE_CLASSPATH + "/notation"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-a-notation-non-resolue")
                .hasMessageContaining("est la notation d'un caractere, pas le caractere");
    }

    /** Le meme defaut, membre silencieux : les guillemets francais y encadrent un espace ordinaire. */
    @Test
    void unPaquetNeufDontLesGuillemetsEncadrentUnEspaceSecableFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("espace-secable"), CONTRE_EXEMPLE_CLASSPATH + "/espace-secable"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-a-espace-secable")
                .hasMessageContaining("n'est pas suivi d'un espace insecable");
    }

    /** Les memes paquets, corriges, ne doivent plus rien reprocher. */
    @Test
    void lesMemesPaquetsCorrigesPassentLeGardeFou() {
        verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }

    /**
     * ADR-0051 : la clause de non-vacuite se prouve elle aussi mordante, sur un arbre dont aucun
     * texte servi ne porte de guillemet francais. Sans elle, un depot qui abandonnerait cette
     * convention rendrait la clause 2 verte sans rien juger.
     */
    @Test
    void unArbreSansAucunGuillemetFrancaisFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("sans-guillemet"), CONTRE_EXEMPLE_CLASSPATH + "/sans-guillemet"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'examine donc aucun objet");
    }
}
