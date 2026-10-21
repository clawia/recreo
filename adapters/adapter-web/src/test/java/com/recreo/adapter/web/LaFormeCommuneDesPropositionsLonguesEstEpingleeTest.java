package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.proprietesDeclarees;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-FORME-COMMUNE-PROPOSITIONS-LONGUES : la forme commune d'ines-design (regles R3-1 a R3-5, sans
 * L1 ni L2) est versee dans la feuille maison telle qu'elle a ete mesuree, et reste ce qu'elle a ete
 * mesuree etre : hors {@code .ecran-deux-oeuvres}, sous {@code @media (min-width: 720px)}.
 *
 * <p>S57, palier 3 (correctif du pli a 744 x 551) : trois regles s'y ajoutent, mesurees sur les cinq
 * paquets CE1 (mesure-ines-palier-3-pli-744x551.md) - la marge verticale de l'en-tete (0,5 rem) et
 * l'interligne de l'enonce et celui des propositions (1,25). L'en-tete n'exclut pas l'ecran a deux
 * oeuvres, parce que c'est la coquille permanente : il entre dans la population par son nom, et
 * seulement sous le seuil de 720 px (la regle de base, hors seuil, n'est pas celle qui a ete mesuree
 * et n'est pas jugee ici).
 *
 * <p><b>Ce que ce fichier garde.</b> Les cotes (bas de {@code .indice} au plus 551 a 744 x 551) ont
 * ete relevees sur ces regles, avec ces valeurs : une valeur qui change, ou une regle qui s'ajoute au
 * meme sujet, invalide la mesure sans qu'aucun rouge le dise. Aucun automate du reacteur ne rend une
 * page : la position de {@code .indice} reste a la mesure par navigateur et a {@code nadia-prof}.
 *
 * <p><b>Ce qu'il ne garde pas.</b> Ni la propriete {@code C1'} sur l'ecran de manche (ce qu'une regle
 * a le droit de nommer comme etat du verdict), ni le non-nommage d'un paquet par la feuille, que
 * {@code AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest} juge deja sur toute la production.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la population jugee est toute regle maison dont le selecteur nomme
 *       l'exclusion de l'ecran a deux oeuvres, relue a chaque appel. Seules les valeurs attendues sont
 *       ecrites ici, parce que ce sont celles de la mesure ;</li>
 *   <li><b>derivation totale</b> : une population vide, une regle attendue absente, une regle de la
 *       population qui n'est pas attendue et une declaration qu'une regle de la forme porte sans
 *       qu'elle ait ete mesuree sont des <b>echecs</b> ;</li>
 *   <li><b>mise a l'epreuve</b> : quatre feuilles fautives - une regle sort du seuil de 720 px, une
 *       valeur mesuree change, une regle s'ajoute a la population, une declaration s'ajoute a une
 *       regle mesuree.</li>
 * </ul>
 */
class LaFormeCommuneDesPropositionsLonguesEstEpingleeTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LaFormeCommuneDesPropositionsLonguesEstEpingleeTest");

    private static final String CONTEXTE = "@media (min-width: 720px)";

    /** Ce qui fait d'une regle une regle de la forme : elle exclut l'ecran a deux oeuvres. */
    private static final String EXCLUSION = ":not(.ecran-deux-oeuvres)";

    /** La coquille permanente : seule regle de la forme qui n'exclut pas l'ecran a deux oeuvres. */
    private static final String ENTETE = ".entete";

    private static final String ECRAN = ".ecran-de-jeu:has(.propositions)" + EXCLUSION;
    private static final String MESSAGE = ECRAN + " > .message-jeu";

    private record Declaration(String selecteur, String propriete, String valeur) {
    }

    /**
     * Les cinq regles R3-1 a R3-5 et les trois du correctif du pli, avec les valeurs sur lesquelles
     * la cote de reference est mesuree.
     */
    private static final List<Declaration> LA_FORME = List.of(
            new Declaration(".zone-jeu:has(> .ecran-de-jeu" + EXCLUSION + " .propositions)",
                    "padding-top", "0"),
            new Declaration(ECRAN + " > .score", "margin", "0"),
            new Declaration(ECRAN + " > .consigne", "margin-bottom", "0"),
            new Declaration(ECRAN + " > .enonce", "margin-bottom", "0"),
            new Declaration(ECRAN + " .propositions", "row-gap", "0.5rem"),
            new Declaration(MESSAGE, "display", "grid"),
            new Declaration(MESSAGE, "grid-template-columns", "15rem minmax(0, 1fr)"),
            new Declaration(MESSAGE, "column-gap", "0.75rem"),
            new Declaration(MESSAGE, "align-items", "start"),
            new Declaration(MESSAGE + " > .signal", "grid-column", "1"),
            new Declaration(MESSAGE + " > .signal", "grid-row", "1 / span 3"),
            new Declaration(MESSAGE + " > :not(.signal)", "grid-column", "2"),
            new Declaration(ECRAN + " .signal .verdict", "margin-bottom", "0"),
            new Declaration(MESSAGE + " > .signal + *", "margin-top", "0"),
            new Declaration(ENTETE, "padding", "0.5rem 0"),
            new Declaration(ECRAN + " .enonce", "line-height", "1.25"),
            new Declaration(ECRAN + " .proposition", "line-height", "1.25"));

    private static Set<String> selecteursAttendus() {
        Set<String> selecteurs = new LinkedHashSet<>();
        for (Declaration declaration : LA_FORME) {
            selecteurs.add(declaration.selecteur());
        }
        return selecteurs;
    }

    static void verifierLaFormeCommune(Path racine) {
        List<Regle> population = reglesMaison(racine).stream()
                .filter(regle -> regle.selecteur().contains(EXCLUSION)
                        || (regle.selecteur().equals(ENTETE) && regle.contexte().equals(CONTEXTE)))
                .toList();
        assertThat(population)
                .as("cette clause ne prouve rien si aucune regle maison de %s n'exclut %s", racine,
                        EXCLUSION)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        Set<String> attendus = selecteursAttendus();
        for (Regle regle : population) {
            if (!regle.contexte().equals(CONTEXTE)) {
                defauts.add(regle.selecteur() + " doit etre ecrite sous " + CONTEXTE
                        + " : elle est ecrite " + (regle.contexte().isEmpty()
                                ? "hors de toute regle-@" : "sous " + regle.contexte()));
            }
            if (!attendus.contains(regle.selecteur())) {
                defauts.add(regle.selecteur() + " n'est pas une regle de la forme mesuree : la cote "
                        + "de reference ne la contient pas");
                continue;
            }
            for (String propriete : proprietesDeclarees(regle.corps())) {
                boolean mesuree = LA_FORME.stream().anyMatch(declaration ->
                        declaration.selecteur().equals(regle.selecteur())
                                && declaration.propriete().equals(propriete));
                if (!mesuree) {
                    defauts.add(regle.selecteur() + " declare " + propriete + ", que la forme "
                            + "mesuree ne declare pas : la cote de reference ne la contient pas");
                }
            }
        }
        for (Declaration attendue : LA_FORME) {
            List<Regle> porteuses = population.stream()
                    .filter(regle -> regle.selecteur().equals(attendue.selecteur()))
                    .filter(regle -> debutDeDeclaration(regle.corps(), attendue.propriete()) >= 0)
                    .toList();
            if (porteuses.isEmpty()) {
                defauts.add(attendue.selecteur() + " doit declarer " + attendue.propriete());
                continue;
            }
            for (Regle porteuse : porteuses) {
                String valeur = valeurDeclaree(porteuse.corps(), attendue.propriete());
                if (!valeur.equals(attendue.valeur())) {
                    defauts.add(attendue.selecteur() + " declare " + attendue.propriete() + " a "
                            + valeur + " : la forme a ete mesuree a " + attendue.valeur());
                }
            }
        }
        assertThat(defauts).as("la forme commune des propositions longues, telle que mesuree").isEmpty();
    }

    @Test
    void laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction() {
        verifierLaFormeCommune(RACINE_PRODUCTION);
    }

    @Test
    void uneRegleDeLaFormeEcriteHorsDuSeuilDe720FaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("regle-hors-seuil");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ECRAN + " .propositions doit etre ecrite sous " + CONTEXTE)
                .hasMessageContaining("hors de toute regle-@");
    }

    @Test
    void uneValeurMesureeQuiChangeFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("valeur-changee");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ECRAN + " .propositions declare row-gap a 1rem")
                .hasMessageContaining("la forme a ete mesuree a 0.5rem");
    }

    @Test
    void uneRegleQueLaMesureNeContientPasFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("regle-ajoutee");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ECRAN + " > .indice n'est pas une regle de la forme mesuree");
    }

    @Test
    void unInterligneQuiChangeFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("interligne-change");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ECRAN + " .proposition declare line-height a 1.5")
                .hasMessageContaining("la forme a ete mesuree a 1.25");
    }

    @Test
    void uneMargeDEnteteQuiChangeSousLeSeuilFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("entete-change");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ENTETE + " declare padding a 1rem 0")
                .hasMessageContaining("la forme a ete mesuree a 0.5rem 0");
    }

    @Test
    void uneDeclarationQueLaMesureNeContientPasFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("declaration-ajoutee");
        assertThatThrownBy(() -> verifierLaFormeCommune(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(ECRAN + " .propositions declare column-gap, que la forme mesuree "
                        + "ne declare pas");
    }
}
