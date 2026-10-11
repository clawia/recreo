package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.text.Normalizer;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.recreo.adapter.content.AucunePropositionNEstEgaleAUneAutreDansUnItemTest.paquets;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0049 : une matiere s'ecrit d'une seule facon dans tout le contenu servi. La valeur portee par
 * un paquet est celle qu'un enfant lira le jour ou un bouton de matiere l'affichera (DEC-0032
 * point 2), donc deux graphies de la meme matiere sont deux mots differents pour lui - et l'une des
 * deux est fausse.
 *
 * <p><b>Ce que ce garde-fou interdit reellement</b>, et c'est le geste que le sprint 17 est sur le
 * point de faire : la reserve de matiere premiere de {@code contenu-repris/} porte sa matiere
 * <b>accentuee</b> sur ses 61 fichiers, le contenu servi la porte <b>en ASCII</b> sur ses 12
 * paquets. Une conversion recopiee telle quelle depose donc une seconde graphie a cote de la
 * premiere, et rien ne le voyait avant cette clause. Le mode de defaillance n'est pas un echec de
 * chargement - les deux valeurs se chargent - c'est un contenu servi qui porte deux orthographes
 * d'une meme matiere, dont une seule peut etre montree a un enfant.</p>
 *
 * <p><b>Ce qu'il ne fait pas</b>, dit avant ce qu'il fait : il ne dit pas <b>laquelle</b> des deux
 * graphies est la bonne. Aucun dictionnaire n'entre dans ce depot et aucun automate n'y decide de
 * l'orthographe du francais ; ADR-0049 tranche que c'est la forme accentuee, et c'est une decision
 * ecrite, pas une propriete calculee. Ce test garde l'<b>unicite</b>, le seul invariant qui se
 * derive.</p>
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage du repertoire de contenu, jamais
 *       d'une liste ecrite ici, et leur matiere vient du <b>chargeur reel</b>, la chaine que
 *       l'enfant traverse. C'est deliberement le repertoire et non le catalogue : un paquet retire du
 *       catalogue (DEC-0009, DEC-0024) est la matiere de travail d'une story de retour, et un
 *       garde-fou derive du catalogue ne le verrait plus ;</li>
 *   <li><b>derivation totale</b> : aucun paquet releve, et un groupe qui porte plus d'une graphie,
 *       sont des <b>echecs</b>, jamais des cas ignores. Le repliage ne connait aucune liste de mots :
 *       il retire les diacritiques par {@link Normalizer} et compare sans casse, donc une matiere
 *       neuve entre dans le controle sans que ce fichier change ;</li>
 *   <li><b>mise a l'epreuve</b> : un jeu fautif de deux paquets portant les deux graphies de la meme
 *       matiere - la forme exacte qu'une conversion de {@code contenu-repris/} produirait.</li>
 * </ul>
 */
class UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, jamais servi a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest";

    /**
     * La forme repliee d'une matiere : sans diacritique, sans casse, et sans ecart de blancs. Ce
     * n'est pas une normalisation du francais, c'est la seule facon de dire "ces deux chaines
     * nomment la meme matiere" sans tenir de table de correspondance.
     *
     * <p><b>Amendement du 2026-08-19 (s17), defaut mesure par {@code yanis-qa}</b> (rapport qualite
     * de s17, point 2). Ce repliage ne traitait que l'accent et la casse : deux valeurs identiques au
     * caractere pres a un blanc de bord pres - {@code "Francais"} et {@code "Francais "} - tombaient
     * dans deux groupes distincts de taille 1, et la clause d'unicite restait <b>verte alors que le
     * contenu servi portait reellement deux chaines differentes</b>. Il a mute deux paquets de
     * production reels pour l'etablir, pas une fixture. Les blancs sont desormais absorbes : bords
     * retires, suites internes reduites a une seule espace, et {@link #estUnBlanc} compte aussi les
     * espaces <b>insecables</b>, que {@link Character#isWhitespace} ne voit pas - c'est le blanc
     * qu'une copie depuis un traitement de texte depose, et le seul qu'une relecture humaine ne
     * distingue pas d'une espace ordinaire.</p>
     *
     * <p><b>Ce que ce repliage ne pretend pas.</b> Il ne fusionne pas deux graphies qui different
     * autrement que par l'accent, la casse ou les blancs : {@code "Maths"} et
     * {@code "Mathematiques"} restent deux matieres distinctes a ses yeux, et une divergence de ce
     * genre passerait. Elle n'est pas invisible pour autant - elle est visible dans le diff du
     * paquet, la ou un blanc de bord ne l'est pas.</p>
     */
    static String replie(String matiere) {
        String decompose = Normalizer.normalize(matiere, Normalizer.Form.NFD);
        StringBuilder sans = new StringBuilder(decompose.length());
        boolean blancEnAttente = false;
        for (int position = 0; position < decompose.length(); position++) {
            char caractere = decompose.charAt(position);
            if (Character.getType(caractere) == Character.NON_SPACING_MARK) {
                continue;
            }
            if (estUnBlanc(caractere)) {
                blancEnAttente = sans.length() > 0;
                continue;
            }
            if (blancEnAttente) {
                sans.append(' ');
                blancEnAttente = false;
            }
            sans.append(caractere);
        }
        return sans.toString().toLowerCase(Locale.FRENCH);
    }

    /**
     * Tout ce qui separe sans rien dire. {@link Character#isWhitespace} <b>exclut</b> l'espace
     * insecable (U+00A0) par definition - elle ne compte que les blancs qui autorisent un retour a la
     * ligne - et c'est exactement le blanc qu'un copier-coller depose dans un fichier de contenu.
     * {@link Character#isSpaceChar} le compte : les deux ensemble couvrent la tabulation, le retour a
     * la ligne, l'espace ordinaire et l'insecable.
     */
    private static boolean estUnBlanc(char caractere) {
        return Character.isWhitespace(caractere) || Character.isSpaceChar(caractere);
    }

    private static Map<String, Set<String>> graphiesParMatiere(Path racine, String racineClasspath) {
        Map<String, Set<String>> graphies = new LinkedHashMap<>();
        YamlContentPackLoader chargeur = new YamlContentPackLoader(racineClasspath);
        for (String paquet : paquets(racine)) {
            String matiere = chargeur.chargerPaquet(paquet).descriptor().matiere();
            graphies.computeIfAbsent(replie(matiere), inconnue -> new LinkedHashSet<>()).add(matiere);
        }
        return graphies;
    }

    static void verifierQuUneMatiereNAQuUneGraphie(Path racine, String racineClasspath) {
        assertThat(paquets(racine))
                .as("ce garde-fou ne prouve rien s'il ne releve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> ecarts = graphiesParMatiere(racine, racineClasspath).values().stream()
                .filter(graphies -> graphies.size() > 1)
                .map(graphies -> "la meme matiere est ecrite de " + graphies.size()
                        + " facons dans le contenu servi : " + graphies)
                .toList();

        assertThat(ecarts)
                .as("ADR-0049 : une matiere s'ecrit d'une seule facon dans tout le contenu servi. "
                        + "Deux graphies sont deux mots differents pour un enfant, et une seule peut "
                        + "lui etre montree - la forme accentuee (ADR-0049, DEC-0032 point 2)")
                .isEmpty();
    }

    @Test
    void chaqueMatiereDuContenuServiNAQuUneSeuleGraphie() {
        verifierQuUneMatiereNAQuUneGraphie(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0003 : sans cette clause, la precedente serait verte le jour ou le contenu ne porterait
     * plus qu'une seule matiere, et personne ne le saurait. Le message dit quoi faire dans ce cas
     * plutot que de laisser deviner.
     */
    @Test
    void leContenuServiPorteBienPlusieursMatieres() {
        assertThat(graphiesParMatiere(CONTENU_DE_PRODUCTION, "").keySet())
                .as("ADR-0003 : le contenu servi ne porte qu'une matiere, la clause d'unicite ne "
                        + "compare donc rien. Si le catalogue s'est reellement reduit a une matiere, "
                        + "c'est ce garde-fou qu'il faut relire, avec son motif ecrit")
                .hasSizeGreaterThan(1);
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif est la forme exacte qu'une conversion de
     * {@code contenu-repris/} produirait en recopiant la valeur telle quelle - un paquet neuf
     * accentue a cote d'un paquet servi en ASCII. Ce n'est pas une fixture imaginee : les deux
     * graphies sont celles que les deux repertoires portent reellement aujourd'hui.
     */
    @Test
    void deuxGraphiesDeLaMemeMatiereFontEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuUneMatiereNAQuUneGraphie(
                CONTRE_EXEMPLE.resolve("deux-graphies-de-francais"),
                CONTRE_EXEMPLE_CLASSPATH + "/deux-graphies-de-francais"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la meme matiere est ecrite de 2 facons");
    }

    /**
     * ADR-0013 obligation 3, amendement du 2026-08-19 : le jeu fautif du <b>blanc de bord</b>. Il
     * reproduit au caractere pres la mutation que {@code yanis-qa} a mesuree sur deux paquets de
     * production reels ({@code cm1-conjugaison-present} et {@code cm1-homophones}, tous deux
     * {@code matiere: "Francais"}, un seul mute d'une espace de fin) : le garde-fou etait alors
     * <b>vert</b>. L'espace vit a l'interieur des guillemets du YAML, jamais en fin de ligne, pour
     * qu'aucun editeur ni aucun outil de mise en forme ne l'efface sans qu'on le voie.
     */
    @Test
    void unBlancDeBordQuiSepareDeuxGraphiesFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuUneMatiereNAQuUneGraphie(
                CONTRE_EXEMPLE.resolve("deux-graphies-separees-par-un-blanc"),
                CONTRE_EXEMPLE_CLASSPATH + "/deux-graphies-separees-par-un-blanc"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la meme matiere est ecrite de 2 facons");
    }

    /**
     * ADR-0013 obligation 3, amendement du 2026-08-19 : le jeu fautif de l'<b>espace insecable</b>.
     * Sans {@link #estUnBlanc}, un repliage qui n'appellerait que {@link Character#isWhitespace}
     * resterait vert sur ce jeu-la tout en mordant sur le precedent - la forme exacte de la fixture
     * qui ressemble a ce que son auteur imagine. Le caractere est ecrit {@code \u00A0} dans le YAML,
     * echappement que la double quote de YAML interprete : il est donc <b>visible en relecture</b>,
     * contrairement a l'octet lui-meme.
     */
    @Test
    void unEspaceInsecableQuiSepareDeuxGraphiesFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuUneMatiereNAQuUneGraphie(
                CONTRE_EXEMPLE.resolve("deux-graphies-separees-par-un-insecable"),
                CONTRE_EXEMPLE_CLASSPATH + "/deux-graphies-separees-par-un-insecable"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la meme matiere est ecrite de 2 facons");
    }

    /**
     * Le cas qui doit rester <b>vert</b>, et sans lequel les deux clauses ci-dessus seraient une
     * interdiction d'ecrire deux matieres differentes deguisee en clause d'unicite : deux matieres qui
     * ne se replient pas l'une sur l'autre restent deux matieres. {@code "Mathematiques"} et
     * {@code "Questionner le monde"} n'ont ni le meme mot ni le meme nombre de mots.
     */
    @Test
    void deuxMatieresReellementDifferentesRestentVertes() {
        verifierQuUneMatiereNAQuUneGraphie(
                CONTRE_EXEMPLE.resolve("deux-matieres-differentes"),
                CONTRE_EXEMPLE_CLASSPATH + "/deux-matieres-differentes");
    }
}
