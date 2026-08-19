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
     * La forme repliee d'une matiere : sans diacritique et sans casse. Ce n'est pas une
     * normalisation du francais, c'est la seule facon de dire "ces deux chaines nomment la meme
     * matiere" sans tenir de table de correspondance.
     */
    private static String replie(String matiere) {
        String decompose = Normalizer.normalize(matiere, Normalizer.Form.NFD);
        StringBuilder sans = new StringBuilder(decompose.length());
        for (int position = 0; position < decompose.length(); position++) {
            char caractere = decompose.charAt(position);
            if (Character.getType(caractere) != Character.NON_SPACING_MARK) {
                sans.append(caractere);
            }
        }
        return sans.toString().toLowerCase(Locale.FRENCH);
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
}
