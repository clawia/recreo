package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S17-1 (reserve S16-A de {@code nadia-prof}, {@code FICHE-barre-entree-niveau.md}) : un enfant
 * qui navigue au clavier voit desormais lequel des pressables de l'ecran est actuellement
 * selectionne. Forme tranchee par son arbitrage prealable
 * ({@code ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md}, variante 1 d'{@code ines-design}) :
 * un anneau interieur, sur les memes selecteurs qu'ADR-0046, a {@code :focus-visible} seulement.
 *
 * <p><b>Les deux criteres testables de la porte de sortie</b> ({@code planning.md}, tableau des
 * stories) : une propriete visible qui differe entre le repos et le focus, et cette propriete
 * n'est pas exclusivement une couleur. FOC-4, FOC-5 et FOC-6 (toute la population reellement
 * percue, la lisibilite a 360 px, S15-8/S15-9 intacts) sont mesures par {@code nadia-prof} sur
 * l'increment livre, jamais par ce fichier (arbitrage, section 8 : elle mesure apres, pas avant).
 *
 * <p><b>Perimetre derive, jamais une liste ecrite a la main (ADR-0013).</b> La population des
 * pressables focalisables est celle que la feuille maison porte deja a {@code :focus-visible} -
 * exactement les selecteurs qu'ADR-0046 a etablis comme la population de pressables du depot. Le
 * jour ou un pressable neuf recoit sa regle de focus, il entre dans cette population sans que ce
 * fichier change ; le jour ou l'un d'eux cesse de la recevoir, l'un des deux tests ci-dessous
 * rougit sur un ensemble devenu vide.
 */
class UnPressableFocaliseAuClavierAfficheUnIndicateurVisibleTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/UnPressableFocaliseAuClavierAfficheUnIndicateurVisibleTest");

    private static final String ETAT = ":focus-visible";

    private static final String PROPRIETE = "box-shadow";

    /**
     * Les identites de pressable qui portent deja une regle a {@code :focus-visible} dans la
     * feuille maison - le selecteur, {@code :focus-visible} retire. Derivee des cles de
     * {@link TailleDesTextesEtBoutonsTest#blocsMaison}, jamais d'une liste : c'est la meme
     * population que celle qu'ADR-0046 a etablie deux etats plus tot ({@code :hover},
     * {@code :active}), a laquelle {@code :focus-visible} s'est ajoute en s16.
     */
    private static Set<String> identitesFocalisables(Map<String, String> maison) {
        Set<String> identites = new LinkedHashSet<>();
        for (String selecteur : maison.keySet()) {
            if (selecteur.endsWith(ETAT)) {
                identites.add(selecteur.substring(0, selecteur.length() - ETAT.length()).strip());
            }
        }
        return identites;
    }

    /**
     * Les proprietes qu'un etat garde d'ADR-0046 fait peser sur nos pressables, hors celle que ce
     * meme ADR ecarte nommement de la comparaison a cet etat ({@code box-shadow}, qui porte
     * l'anneau de focus clavier tiers). Lue sur {@code .btn:focus-visible} de la feuille vendorisee
     * reelle, jamais une liste ecrite a la main : une bibliotheque qui ajouterait une propriete a
     * cet etat l'ajoute ici aussi.
     */
    private static Set<String> proprietesDeCouleurExigeesParLeTiers(Map<String, String> tiers) {
        Set<String> proprietes = new LinkedHashSet<>();
        for (String propriete
                : LAnimationRetardeMaisNeDetermineRienTest.proprietesDeclarees(
                        tiers.getOrDefault(".btn" + ETAT, ""))) {
            if (!propriete.startsWith("--") && !propriete.equals(PROPRIETE)) {
                proprietes.add(propriete);
            }
        }
        return proprietes;
    }

    /**
     * Meme relation qu'ADR-0046 point 3 (raccourci/longhand lu sur les noms, jamais une table) :
     * deux proprietes se touchent si elles sont egales, ou si l'une est le prefixe tiret de
     * l'autre.
     */
    private static boolean touche(String proprieteA, String proprieteB) {
        return proprieteB.equals(proprieteA)
                || proprieteB.startsWith(proprieteA + "-")
                || proprieteA.startsWith(proprieteB + "-");
    }

    /**
     * Le premier critere testable : chaque pressable focalisable de {@code racine} affiche une
     * propriete qui differe entre son repos et son focus clavier (FOC-4). ADR-0013 obligation 3 :
     * exposee pour etre executee aussi sur le jeu fautif.
     */
    private static void verifierQueChaquePressableFocaliseDiffereDeSonRepos(Path racine) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racine);
        Set<String> identites = identitesFocalisables(maison);

        assertThat(identites)
                .as("aucun pressable ne porte de regle a :focus-visible : ce garde-fou ne "
                        + "mesurerait rien")
                .isNotEmpty();

        List<String> sansDifference = new ArrayList<>();
        for (String identite : identites) {
            String corpsFocus = maison.get(identite + ETAT);
            int positionFocus = TailleDesTextesEtBoutonsTest.debutDeDeclaration(corpsFocus, PROPRIETE);
            if (positionFocus < 0) {
                sansDifference.add(identite + " ne declare pas " + PROPRIETE + " a " + ETAT);
                continue;
            }
            String valeurFocus =
                    TailleDesTextesEtBoutonsTest.valeurDeclaree(corpsFocus, positionFocus, PROPRIETE);

            String corpsRepos = maison.getOrDefault(identite, "");
            int positionRepos = TailleDesTextesEtBoutonsTest.debutDeDeclaration(corpsRepos, PROPRIETE);
            boolean differe = positionRepos < 0
                    || !TailleDesTextesEtBoutonsTest
                            .valeurDeclaree(corpsRepos, positionRepos, PROPRIETE)
                            .equals(valeurFocus);
            if (!differe) {
                sansDifference.add(identite + " declare la meme valeur de " + PROPRIETE
                        + " au repos et au focus");
            }
        }

        assertThat(sansDifference)
                .as("chaque pressable focalisable doit afficher une propriete qui differe entre "
                        + "le repos et le focus clavier (FOC-4)")
                .isEmpty();
    }

    @Test
    void chaquePressableFocalisableDeclareUneProprieteQuiDiffereEntreLeReposEtLeFocus() {
        verifierQueChaquePressableFocaliseDiffereDeSonRepos(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la morsure se prouve sur un objet neuf. Un pressable qui porte deja
     * {@code :focus-visible} sans qu'aucune propriete n'y differe de son repos - exactement le
     * defaut que la reserve S16-A a mesure avant ce lot.
     */
    @Test
    void unPressableSansAucuneProprieteQuiDiffereFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePressableFocaliseDiffereDeSonRepos(
                CONTRE_EXEMPLES.resolve("aucune-propriete-ne-differe")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".bouton-neuf ne declare pas box-shadow a :focus-visible");
    }

    @Test
    void laProprieteQuiDiffereAuFocusNestPasCelleQuUnEtatTiersExigeCommeCouleur() {
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(RACINE_PRODUCTION);
        Set<String> familleDeCouleur = proprietesDeCouleurExigeesParLeTiers(tiers);

        assertThat(familleDeCouleur)
                .as("aucune propriete de couleur derivee de la feuille tierce : ce garde-fou ne "
                        + "mesurerait rien")
                .isNotEmpty();

        for (String proprieteDeCouleur : familleDeCouleur) {
            assertThat(touche(PROPRIETE, proprieteDeCouleur))
                    .as("%s ne doit pas toucher %s, sans quoi l'indicateur de focus ne serait "
                            + "porte que par une couleur", PROPRIETE, proprieteDeCouleur)
                    .isFalse();
        }
    }
}
