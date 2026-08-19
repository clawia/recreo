package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S17-A (bloquante, {@code FICHE-barre-entree-niveau.md}, sonde post-livraison de {@code S17-1}) :
 * sur la puce de niveau active, l'anneau interieur pose par {@code S17-1} peint en
 * {@code currentColor} exactement la couleur de la bordure et du bandeau qui l'entourent
 * ({@code --recreo-entete-fond}, {@code #1d4b6b}) - {@code nadia-prof} mesure 1,00:1 contre
 * l'exterieur, un effet percu de retrecissement plutot que d'apparition. C'est la seule des dix
 * familles ou {@code currentColor} s'effondre (les neuf autres ont un texte qui differe de son
 * environnement par construction).
 *
 * <p><b>Forme retenue</b> ({@code ines-design},
 * {@code proposition-ines-marque-de-focus-puce-active.md}, section 2, acceptee par
 * {@code nadia-prof}) : la puce active sort du groupe partage de l'anneau interieur et recoit sa
 * propre regle - un anneau <b>exterieur</b> (pas d'{@code inset}), peint sur le bandeau plutot que
 * sur le remplissage blanc, en blanc declare ({@code var(--recreo-accent-texte)}, deja utilise sur
 * ce meme bandeau - aucune teinte neuve). Contraste reverifie par {@code nadia-prof} : 9,25:1
 * (L = 0,063549 pour {@code #1d4b6b}).
 *
 * <p><b>Portee, verifiee et non supposee</b> ({@code nadia-prof} a corrige son propre releve) :
 * cette regle vit sur le meme selecteur que l'etat actif lui-meme,
 * {@code body:has(.filtre-niveau) .chip-niveau.est-actif}, qui n'existe que sur le catalogue
 * filtre (S15, quatrieme arbitrage) - jamais sur une manche ni sur les trois routes qui rendent le
 * catalogue complet sans filtre. Il n'y a donc rien de plus a scoper : le selecteur deja ecrit par
 * {@code ines-design} est deja aussi etroit que l'ecran ou le defaut existe.
 *
 * <p><b>Ce que ce fichier ne reverifie pas</b>, parce que d'autres garde-fous le font deja sans
 * modification : {@code AucunEtatDePressableNEstAbandonneAuTiersTest} (box-shadow reste hors des
 * proprietes comparees a {@code :focus-visible}, ADR-0046 point 4 - inchange par ce lot, rejoue
 * pour verifier que le retrait du selecteur du groupe partage ne casse aucune identite calculee) ;
 * {@code UnPressableFocaliseAuClavierAfficheUnIndicateurVisibleTest} (la puce active differe
 * toujours de son repos, quelle que soit la valeur exacte de {@code box-shadow}) ;
 * {@code UnPressableAppuyeConserveSonContrasteEtSonAnneauDeFocusTest#laPuceActiveDepasseDejaLePlancherSansRegleDePlus}
 * (la specificite du selecteur, inchangee par ce lot, continue de depasser le plancher tiers
 * combine sans regle supplementaire) ; {@code LHabillageNeRogneJamaisUnElementPressableTest}
 * (aucun ancetre du pressable ne declare {@code overflow} ni {@code clip-path} - verifie par
 * nadia-prof sur le rendu et par ce garde-fou sur la declaration, aucun des deux modifie ici).
 */
class LaPuceActiveAfficheUnAnneauExterieurPercuTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/LaPuceActiveAfficheUnAnneauExterieurPercuTest");

    private static final String SELECTEUR_PUCE_ACTIVE =
            "body:has(.filtre-niveau) .chip-niveau.est-actif:focus-visible";

    static void verifierQueLaPuceActiveDeclareUnAnneauExterieur(Path racine) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racine);
        String corps = maison.get(SELECTEUR_PUCE_ACTIVE);
        assertThat(corps)
                .as("la puce active doit porter sa propre regle de focus, \"%s\"", SELECTEUR_PUCE_ACTIVE)
                .isNotNull();

        int position = TailleDesTextesEtBoutonsTest.debutDeDeclaration(corps, "box-shadow");
        assertThat(position)
                .as("\"%s\" doit declarer box-shadow", SELECTEUR_PUCE_ACTIVE)
                .isNotNegative();
        String valeur = TailleDesTextesEtBoutonsTest.valeurDeclaree(corps, position, "box-shadow");

        assertThat(valeur)
                .as("l'anneau de la puce active doit peindre a l'exterieur (pas \"inset\"), sans "
                        + "quoi il retombe sur le remplissage blanc et s'effondre a 1,00:1 (S17-A)")
                .doesNotContain("inset");
        assertThat(valeur)
                .as("l'anneau doit etre en blanc declare (--recreo-accent-texte), jamais "
                        + "currentColor : c'est precisement la valeur qui s'effondre sur cette "
                        + "seule famille (diagnostic d'ines-design, section 0)")
                .doesNotContain("currentColor")
                .contains("var(--recreo-accent-texte)");
        assertThat(valeur)
                .as("meme largeur que les neuf autres familles, 3 px")
                .contains("0.1875rem");
    }

    @Test
    void laPuceActiveDeclareUnAnneauExterieurEnBlancDeclare() {
        verifierQueLaPuceActiveDeclareUnAnneauExterieur(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la forme d'avant ce lot - anneau interieur, {@code currentColor},
     * partage avec les neuf autres familles - est exactement le defaut que {@code nadia-prof} a
     * mesure a 1,00:1.
     */
    @Test
    void laFormeInterieurePartageeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLaPuceActiveDeclareUnAnneauExterieur(
                CONTRE_EXEMPLES.resolve("anneau-interieur-oublie")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("inset");
    }
}
