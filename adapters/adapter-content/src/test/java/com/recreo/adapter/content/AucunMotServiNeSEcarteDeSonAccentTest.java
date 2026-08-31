package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentDescriptor;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0065. La propriete, telle que nadia-prof l'ecrit et telle que DEC-0056 la route :
 * <i>aucune chaine lue par un enfant ne contient un mot francais qui prendrait un accent et ne le
 * porte pas</i>.
 *
 * <p><b>Le declencheur.</b> Le 2026-08-29, les vingt-cinq indices de
 * {@code cm1-multiplications-divisions-10-100.yaml} n'etaient pas accentues. La CI etait verte, le
 * lot etait livre, et le defaut n'a ete trouve que par la lecture a l'oeil de nadia-prof, une fois
 * par sprint. Corrige en {@code be63ce3} ; ce garde-fou le rattrape desormais a chaque
 * {@code verify}.
 *
 * <p><b>Le critere, et pourquoi il ne peut pas venir du depot.</b> Un mot est fautif quand il n'est
 * pas une forme du francais et qu'une autre facon d'accentuer les memes lettres, elle, en est une.
 * Repondre demande un lexique, et ce lexique ne peut pas etre derive du corpus juge : ADR-0045 l'a
 * etabli en principe, la mesure l'etablit en chiffre. Sur le contenu servi au 2026-08-30, 213 des
 * 274 formes accentuees distinctes (78 pour cent) n'apparaissent que dans un seul paquet. Un
 * critere qui comparerait les paquets entre eux ne verrait donc rien du vocabulaire neuf d'un
 * paquet neuf. Mesure a l'appui sur le defaut reel : sur l'arbre de {@code be63ce3^}, la forme
 * "zeros" n'etait rattrapable que par {@code cm1-grands-nombres} et "ecrire" que par
 * {@code cm1-comprehension}, un paquet livre le meme sprint - la detection aurait tenu au hasard du
 * vocabulaire voisin.
 *
 * <p><b>Ce que ce garde-fou ne voit pas, et que nadia-prof continue de porter a la main.</b> Le
 * critere ne peut rien dire d'un mot qui, sans son accent, est <i>lui aussi</i> un mot francais :
 * "a" et "a accent grave", "ou" et "ou accent grave", "la" et "la accent grave", "des" et "des
 * accent grave", "sur" et "sur accent circonflexe", "cote" et ses trois soeurs. Trancher demande la
 * syntaxe de la phrase, qu'aucun lexique ne porte. Sur le contenu servi au 2026-08-30, 232 des 1331
 * mots distincts (17 pour cent) tombent dans cette classe. Elle contient la faute d'accentuation la
 * plus frequente du francais, et elle contient le quatrieme mot du defaut de s22 ("a droite") : ce
 * garde-fou aurait rattrape "ecrire", "zero" et "zeros", jamais "a".
 *
 * <p><b>Perimetre.</b> Tout ce qu'un enfant lit et que ce module sert : le texte des items (par
 * {@link LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest#textesServis}, qui derive les
 * paquets du repertoire et lit chaque paquet enumere en entier, ADR-0013 et ADR-0064) et les six
 * champs du catalogue qu'une vue affiche. L'identifiant d'un jeu et celui d'un paquet en sont
 * exclus pour un motif ecrit : ils ne sont jamais affiches comme du texte, et les conventions de
 * redaction du depot les veulent en ASCII - {@code bandit-manchot-perimetre-cm1} y serait un faux
 * rouge permanent.
 */
class AucunMotServiNeSEcarteDeSonAccentTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest";

    /** Une chaine telle qu'elle est servie, avec l'origine qui l'a produite (pour le message). */
    record ChaineServie(String origine, String valeur) {
    }

    /**
     * Les mots d'une chaine : toute suite maximale de lettres. Les chiffres, la ponctuation et
     * l'apostrophe separent - "aujourd'hui" donne donc deux suites, dont aucune n'est fautive au
     * sens du critere (aucune n'a de soeur accentuee dans le lexique).
     */
    static List<String> motsDe(String valeur) {
        List<String> mots = new ArrayList<>();
        StringBuilder courant = new StringBuilder();
        for (int rang = 0; rang < valeur.length(); rang++) {
            char lettre = valeur.charAt(rang);
            if (Character.isLetter(lettre)) {
                courant.append(lettre);
            } else if (courant.length() > 0) {
                mots.add(courant.toString());
                courant.setLength(0);
            }
        }
        if (courant.length() > 0) {
            mots.add(courant.toString());
        }
        return mots;
    }

    /**
     * Les defauts d'une seule chaine servie. Fonction pure, pour que les mises a l'epreuve
     * ci-dessous s'exercent sur du materiau reel plutot que sur une fixture de ma main (lecon de
     * s05, forme reprise d'ADR-0060).
     */
    static List<String> defautsDe(String origine, String valeur, LexiqueFrancais lexique) {
        List<String> defauts = new ArrayList<>();
        for (String mot : motsDe(valeur)) {
            String minuscule = mot.toLowerCase();
            if (lexique.estUnMotFrancais(minuscule)) {
                continue;
            }
            Set<String> soeurs = lexique.formesDuMemeSquelette(minuscule);
            if (soeurs.isEmpty()) {
                continue;
            }
            defauts.add(String.format(
                    "%s : le mot %s n'est pas une forme du francais, alors que %s en %s une du meme"
                            + " squelette. Le texte lu par un enfant porte le mot accentue (DEC-0002"
                            + " amende le 2026-08-04, DEC-0056, ADR-0065). Chaine servie : %s",
                    origine, guillemets(mot), soeurs, soeurs.size() > 1 ? "sont" : "est",
                    guillemets(valeur)));
        }
        return defauts;
    }

    private static String guillemets(String valeur) {
        return (char) 0x22 + valeur + (char) 0x22;
    }

    /**
     * Le texte des items, tel que l'enfant le recoit.
     *
     * <p><b>Lot 5a de s24, decision 4 : borne aux chaines que l'enfant lit.</b> Un item de saisie
     * fait servir des chaines que personne ne lit - les formes acceptees, le bareme auquel la
     * mecanique compare ce que l'enfant ecrit. {@code CPL-3} y fait figurer l'ecriture en lettres du
     * nombre, et SAI-1 comme SAI-3 tolerent l'absence d'accent : exiger l'accent sur une forme
     * acceptee rougirait sur <b>la tolerance elle-meme</b>, c'est-a-dire sur exactement ce que
     * nadia-prof a demande. Un garde-fou qui rougit sur un mot etranger a son objet est la faute
     * mesuree en s05.</p>
     *
     * <p>La borne n'est pas un trou : ces chaines restent jugees par la clause 1 de
     * {@link LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest} - une notation d'echappement y
     * rend la tolerance morte, et rien ne le dirait.</p>
     */
    static List<ChaineServie> textesDesItems(Path racine, String racineClasspath) {
        List<ChaineServie> textes = new ArrayList<>();
        int compares = 0;
        for (LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.TexteServi texte
                : LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.textesServis(
                        racine, racineClasspath)) {
            if (!texte.luParLEnfant()) {
                compares++;
                continue;
            }
            textes.add(new ChaineServie(texte.origine(), texte.valeur()));
        }
        assertThat(textes)
                .as("%s : ce garde-fou ecarte %d chaine(s) comparee(s) et n'en garde aucune a lire."
                        + " Une borne qui emporte toute la population n'est plus une borne", racine,
                        compares)
                .isNotEmpty();
        return textes;
    }

    /**
     * Les six champs du catalogue qu'une vue affiche. Enumeres, et non derives par reflexion comme
     * le fait {@code TexteAccentueExposeParLePortEntrantTest} : la reflexion ramenerait aussi
     * l'identifiant du jeu, qui est un slug ASCII delibere et non du texte lu.
     */
    static List<ChaineServie> textesDuCatalogue() {
        List<ChaineServie> textes = new ArrayList<>();
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            ContentDescriptor descripteur = jeu.descripteur();
            textes.add(new ChaineServie("catalogue, jeu " + jeu.id() + ", titre", jeu.titre()));
            textes.add(new ChaineServie("catalogue, jeu " + jeu.id() + ", consigne", jeu.consigne()));
            textes.add(new ChaineServie("descripteur de " + jeu.id() + ", matiere",
                    descripteur.matiere()));
            textes.add(new ChaineServie("descripteur de " + jeu.id() + ", niveau",
                    descripteur.niveau()));
            textes.add(new ChaineServie("descripteur de " + jeu.id() + ", domaine",
                    descripteur.domaine()));
            textes.add(new ChaineServie("descripteur de " + jeu.id() + ", competence",
                    descripteur.competence()));
        }
        return textes;
    }

    static void verifierQueChaqueMotServiPorteSonAccent(List<ChaineServie> textes) {
        LexiqueFrancais lexique = LexiqueFrancais.charger();
        assertThat(lexique.nombreDeFormes())
                .as("ADR-0065 : un lexique quasi vide rendrait ce garde-fou vert sans rien juger")
                .isGreaterThan(100_000);
        assertThat(textes)
                .as("ADR-0051 : ce garde-fou ne prouve rien s'il ne trouve aucune chaine servie")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        Set<String> motsExamines = new LinkedHashSet<>();
        for (ChaineServie texte : textes) {
            defauts.addAll(defautsDe(texte.origine(), texte.valeur(), lexique));
            motsExamines.addAll(motsDe(texte.valeur()));
        }

        assertThat(motsExamines)
                .as("ADR-0051 : aucune chaine servie ne porte de mot, ce garde-fou n'examine aucun"
                        + " objet et ne prouve rien")
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0065 : le texte lu par un enfant est integralement accentue")
                .isEmpty();
    }

    @Test
    void chaqueMotServiEnProductionPorteSonAccent() {
        List<ChaineServie> textes = new ArrayList<>(textesDesItems(CONTENU_DE_PRODUCTION, ""));
        textes.addAll(textesDuCatalogue());
        verifierQueChaqueMotServiPorteSonAccent(textes);
    }

    /**
     * ADR-0013 obligation 3, mais sur du materiau reel plutot qu'invente : le paquet du
     * contre-exemple est le fichier tel qu'il etait a {@code be63ce3^}, l'etat exact de l'arbre la
     * veille du refus de nadia-prof. Aucune fixture de ma main - c'est le defaut qui a reellement
     * traverse une CI verte.
     */
    @Test
    void leDefautReelDeS22FaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueMotServiPorteSonAccent(
                textesDesItems(CONTRE_EXEMPLE.resolve("accent-manquant"),
                        CONTRE_EXEMPLE_CLASSPATH + "/accent-manquant")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le mot " + guillemets("ecrire") + " n'est pas une forme")
                .hasMessageContaining("le mot " + guillemets("zero") + " n'est pas une forme")
                .hasMessageContaining("le mot " + guillemets("zeros") + " n'est pas une forme");
    }

    /**
     * Le versant que nadia-prof doit lire avant de compter sur ce garde-fou : sur le meme materiau
     * reel, la quatrieme faute du defaut de s22 - "a droite" pour "a accent grave droite" - n'est
     * <b>pas</b> nommee, et ne peut pas l'etre. "a" est un mot francais ; seule la syntaxe
     * trancherait. Cette clause echoue le jour ou quelqu'un croirait ce trou ferme sans l'avoir
     * ferme.
     */
    @Test
    void laFauteDAccentSurUnMotQuiExisteSansAccentResteInvisible() {
        LexiqueFrancais lexique = LexiqueFrancais.charger();
        List<String> defauts = defautsDe("indice reel de be63ce3^",
                "Multiplier par 10, c'est ecrire un zero de plus a droite du nombre.", lexique);

        assertThat(defauts)
                .as("ADR-0065 : sur cet indice reel, deux mots sur trois fautes sont nommes")
                .hasSize(2);
        assertThat(defauts)
                .as("ADR-0065 : la classe des homographes est le trou assume de ce garde-fou, et"
                        + " nadia-prof le porte a la main")
                .noneMatch(defaut -> defaut.contains("le mot " + guillemets("a") + " "));
    }
}
