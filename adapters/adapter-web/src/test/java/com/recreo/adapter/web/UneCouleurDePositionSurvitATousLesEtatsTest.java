package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0053 : <b>une regle maison dont le sujet designe une position ne peut pas etre recouverte, sur
 * une propriete qu'elle declare, par une regle maison a etat interactif qui s'applique au meme
 * element.</b>
 *
 * <p><b>Le defaut que cette clause ferme est mesure, et il est ne d'un correctif vert de bout en
 * bout.</b> {@code S15-8} (condition pedagogique de {@code nadia-prof}, validee en s16) exige que la
 * couleur de fond qui suit la position d'une proposition survive sous le doigt. Le correctif
 * {@code S17-D} a ajoute l'ancetre {@code #jeu} devant {@code .proposition:active} pour battre une
 * regle tierce, et a fait passer cette regle d'etat <b>au-dessus</b> des quatre regles de position :
 * sous un appui reel, {@code #bdd2ef}, {@code #a9dcd8} et {@code #dcc8ed} deviennent toutes
 * {@code #2e6f9e} et le texte passe au blanc. Aucun test du depot ne l'a vu.
 *
 * <p><b>Ce qui tenait la propriete avant cette clause, et c'est le fond du sujet</b> : une relation
 * de cascade entre deux blocs d'un meme fichier, ecrite <b>en commentaire</b> et executee par
 * personne. Le commentaire disait meme "ne jamais deplacer ce bloc apres les quatre regles de
 * position" - mais <b>relever la specificite produit le meme effet</b>, et l'avertissement ne
 * couvrait pas ce geste. Une regle qui n'est pas executable est un souhait ({@code CLAUDE.md}) ; ici
 * elle l'etait deux fois, sous deux formes differentes : une <b>marge</b> de specificite pour
 * {@code .proposition} (0,2,0 contre 0,3,0) et une <b>egalite</b> resolue par l'ordre du fichier pour
 * {@code .proposition.deja-essayee} (0,3,0 contre 0,3,0). Cette clause les couvre toutes les deux
 * parce qu'elle ne compare pas une specificite mais <b>l'issue de la cascade</b>.
 *
 * <p><b>Meme famille que l'amendement 3 d'ADR-0046, autre population.</b> La-bas : declarer ne suffit
 * pas, il faut l'emporter <b>sur la feuille tierce</b>. Ici : la meme phrase, entre <b>deux regles
 * maison</b>. C'est pourquoi cette clause vit dans son propre garde-fou et non dans celui d'ADR-0046,
 * dont le nom, le perimetre et le cliquet de dette parlent de la bibliotheque tierce.
 *
 * <p><b>Ce que cette clause ne voit pas</b>, et ce n'est pas un detail : elle lit des
 * <b>declarations</b>, jamais un etat rendu. Aucun garde-fou de ce depot ne mesure une couleur sous
 * le doigt (ADR-0025, ADR-0028) - c'est precisement pour cela qu'un correctif vert a pu defaire
 * {@code S15-8} sans que rien ne le dise, et c'est {@code nadia-prof} qui l'a trouve en jouant. Ce
 * qui est garde ici est la <b>cause</b> declarative du defaut, jamais son effet visuel.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les regles viennent du balayage des feuilles maison
 *       ({@code reglesEcritesMaison}, dans l'ordre du fichier), les pressables de celui des gabarits
 *       ({@code etatsRendus}) - aucun selecteur n'est nomme ici. Une regle de position neuve, un etat
 *       neuf ou un pressable neuf entrent dans le controle sans que ce fichier change ;</li>
 *   <li><b>derivation totale</b> : une regle dont la specificite n'est pas lisible fait echouer la
 *       lecture ({@code specificite}, ADR-0046) plutot que d'etre ignoree ;</li>
 *   <li><b>mise a l'epreuve</b> : sur la feuille servie reelle, remise dans la forme exacte du defaut
 *       mesure par {@code nadia-prof}.</li>
 * </ul>
 */
class UneCouleurDePositionSurvitATousLesEtatsTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : les jeux fautifs de ce garde-fou, jamais servis a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/UneCouleurDePositionSurvitATousLesEtatsTest");

    /**
     * Le sujet presse d'une proposition dans la feuille corrigee, forme retenue par {@code marc-dev}
     * (commit {@code f66504c}) : l'ancetre est le <b>conteneur direct</b>, celui-la meme que portent
     * les regles cycliques, si bien que les deux jeux de regles retombent a (0,3,0) et que l'ordre du
     * fichier redevient decisif - les regles cycliques etant ecrites apres, elles l'emportent.
     */
    private static final String SUJET_PRESSE_CORRIGE = ".propositions .proposition:active,";

    /**
     * Le sujet presse <b>tel que la feuille l'a reellement servi</b> pendant que {@code S15-8} etait
     * defaite : un identifiant, donc (1,2,0), au-dessus des regles cycliques a (0,3,0). C'est l'etat
     * que {@code nadia-prof} a mesure sous appui reel, pas un cas construit.
     */
    private static final String SUJET_PRESSE_FAUTIF = "#jeu .proposition:active,";

    /**
     * Les pseudo-classes qui font dependre une declaration de la <b>position</b> de l'element parmi
     * ses freres. Cherchees sur le seul <b>sujet</b> du selecteur, jamais sur son prefixe : dans
     * {@code .propositions:has(.proposition:nth-child(4)) .proposition}, la position est une
     * condition portee par un <b>autre</b> element, et ce que la regle habille ne suit pas la
     * position.
     */
    private static final List<String> PSEUDOS_DE_POSITION = List.of(
            ":nth-child(", ":nth-last-child(", ":nth-of-type(", ":nth-last-of-type(",
            ":first-child", ":last-child", ":only-child",
            ":first-of-type", ":last-of-type", ":only-of-type");

    private static boolean designeUnePosition(String selecteur) {
        String sujet = TailleDesTextesEtBoutonsTest.sujetDuSelecteur(selecteur);
        return PSEUDOS_DE_POSITION.stream().anyMatch(sujet::contains);
    }

    /** Une regle maison avec son rang d'ecriture dans la feuille : l'ordre departage les egalites. */
    private record RegleClassee(int rang, String selecteur, String corps) {
    }

    /**
     * La regle qui l'emporte entre deux regles maison applicables au meme element : la plus
     * specifique, et a specificite egale la derniere ecrite (ADR-0014, point 5). C'est l'issue de la
     * cascade, pas une comparaison de specificite - les deux protections que ce depot avait ecrites
     * en commentaire reposaient l'une sur une marge, l'autre sur une egalite plus un ordre.
     */
    private static boolean lEmporteSur(RegleClassee gagnante, RegleClassee perdante) {
        int forceGagnante =
                AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(gagnante.selecteur());
        int forcePerdante =
                AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(perdante.selecteur());
        if (forceGagnante != forcePerdante) {
            return forceGagnante > forcePerdante;
        }
        return gagnante.rang() > perdante.rang();
    }

    private static List<RegleClassee> reglesMaison(Path racine) {
        List<RegleClassee> regles = new ArrayList<>();
        int rang = 0;
        for (TailleDesTextesEtBoutonsTest.RegleEcrite regle
                : TailleDesTextesEtBoutonsTest.reglesEcritesMaison(racine)) {
            regles.add(new RegleClassee(rang, regle.selecteur(), regle.corps()));
            rang++;
        }
        return regles;
    }

    private static boolean sApplique(String selecteur, TailleDesTextesEtBoutonsTest.BoutonRendu bouton) {
        return AucunEtatDePressableNEstAbandonneAuTiersTest.sujetApplicable(
                AucunEtatDePressableNEstAbandonneAuTiersTest.composeDuMemeElement(selecteur), bouton);
    }

    static void verifierQuAucunEtatNeRecouvreUneCouleurDePosition(Path racine) {
        List<RegleClassee> maison = reglesMaison(racine);
        List<String> defauts = new ArrayList<>();
        Set<String> couples = new LinkedHashSet<>();

        for (TailleDesTextesEtBoutonsTest.BoutonRendu bouton
                : TailleDesTextesEtBoutonsTest.etatsRendus(racine)) {
            for (RegleClassee position : maison) {
                if (!designeUnePosition(position.selecteur())
                        || !sApplique(position.selecteur(), bouton)) {
                    continue;
                }
                for (RegleClassee etat : maison) {
                    if (AucunEtatDePressableNEstAbandonneAuTiersTest
                                    .etatsInteractifsDe(etat.selecteur()).isEmpty()
                            || !sApplique(etat.selecteur(), bouton)) {
                        continue;
                    }
                    releverLeCouple(position, etat, couples, defauts);
                }
            }
        }

        assertThat(couples)
                .as("ADR-0003 : aucun couple (regle de position, regle d'etat) sur une meme propriete "
                        + "n'a ete compare sous %s ; cette clause serait verte sans avoir rien "
                        + "examine", racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0053 : une declaration qui suit la position survit a tout etat interactif")
                .isEmpty();
    }

    private static void releverLeCouple(RegleClassee position, RegleClassee etat,
            Set<String> couples, List<String> defauts) {
        for (String proprietePosition
                : AucunEtatDePressableNEstAbandonneAuTiersTest.proprietesRendues(position.corps())) {
            for (String proprieteEtat
                    : AucunEtatDePressableNEstAbandonneAuTiersTest.proprietesRendues(etat.corps())) {
                if (!AucunEtatDePressableNEstAbandonneAuTiersTest.touche(
                        proprietePosition, proprieteEtat)) {
                    continue;
                }
                couples.add(position.selecteur() + " / " + etat.selecteur() + " / "
                        + proprietePosition);
                if (lEmporteSur(etat, position)) {
                    defauts.add(String.format(
                            "la regle de position %s declare %s, et la regle d'etat %s la recouvre "
                                    + "(specificites compactees %d contre %d, rangs d'ecriture %d et "
                                    + "%d) : la couleur cesse de suivre la position des que l'enfant "
                                    + "touche le bouton. C'est S15-8, condition pedagogique de "
                                    + "nadia-prof, et elle ne tient que si la regle de position "
                                    + "l'emporte dans la cascade - par une specificite plus haute, ou "
                                    + "a specificite egale en etant ecrite apres (ADR-0053)",
                            position.selecteur(), proprietePosition, etat.selecteur(),
                            AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(etat.selecteur()),
                            AucunEtatDePressableNEstAbandonneAuTiersTest.specificite(
                                    position.selecteur()),
                            etat.rang(), position.rang()));
                }
            }
        }
    }

    @Test
    void aucunEtatInteractifNeRecouvreUneCouleurDePositionSurLaFeuilleServie() {
        verifierQuAucunEtatNeRecouvreUneCouleurDePosition(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la morsure sur <b>la feuille servie reelle, remise dans l'etat exact du
     * defaut</b> - le sujet presse rendu a son ancetre identifiant, donc (1,2,0) contre (0,3,0) pour
     * les quatre regles cycliques, tel que {@code nadia-prof} l'a mesure sous appui sur l'increment
     * livre. Aucune fixture de ma main : cet etat-la a reellement ete servi.
     *
     * <p><b>La mutation porte sur le sujet presse, pas sur les regles de position</b> : c'est de ce
     * cote-la que le defaut est venu, et la forme fautive est celle qui a ete servie a un enfant. La
     * clause, elle, n'exige aucune forme - elle exige que la regle de position l'emporte, par une
     * specificite plus haute ou par l'ordre du fichier a specificite egale. Elle est verte sur la
     * forme de {@code marc-dev} (egalite a (0,3,0), tranchee par l'ordre) comme elle l'etait sur la
     * forme inverse (regles cycliques relevees a (1,3,0)), et c'est la preuve qu'elle garde une
     * propriete et non une redaction.</p>
     */
    @Test
    void laFormeQuiADefaitS15HuitFaitEchouerLeGardeFou(@TempDir Path racine) throws IOException {
        copierArbreReel(RACINE_PRODUCTION, racine);
        Path feuille = racine.resolve("static/css/recreo.css");
        String servie = Files.readString(feuille).replace("\r\n", "\n");
        assertThat(servie)
                .as("la mutation doit porter sur la forme corrigee que la feuille servie porte "
                        + "vraiment ; si le sujet presse d'une proposition s'ecrit desormais "
                        + "autrement, c'est cette mutation qu'il faut reecrire, pas cette clause")
                .contains(SUJET_PRESSE_CORRIGE);
        Files.writeString(feuille, servie.replace(SUJET_PRESSE_CORRIGE, SUJET_PRESSE_FAUTIF));

        assertThatThrownBy(() -> verifierQuAucunEtatNeRecouvreUneCouleurDePosition(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nth-child(4n+1)")
                .hasMessageContaining("la recouvre");
    }

    /**
     * ADR-0013 obligation 3, <b>second chemin de defaillance</b>, celui que la mutation de la feuille
     * reelle ne couvre pas : ici les deux regles sont a la <b>meme specificite</b> (0,3,0) et c'est
     * l'<b>ordre du fichier</b> qui decide - la regle d'etat est ecrite apres, donc elle recouvre la
     * couleur de position. Aucun identifiant, aucune marge : le defaut tient au seul ordre de
     * redaction, c'est-a-dire exactement ce que le commentaire de la feuille servie demandait de ne
     * jamais faire et que personne n'executait.
     *
     * <p>Ce jeu fautif est un arbre neuf, jamais livre - la clause doit mordre sur un objet qu'elle
     * n'a jamais vu, et pas seulement sur une mutation de ce qu'elle connait.</p>
     */
    @Test
    void unEtatEcritApresLaRegleDePositionAlaMemeSpecificiteFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunEtatNeRecouvreUneCouleurDePosition(
                CONTRE_EXEMPLE.resolve("ordre-inverse")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nth-child(4n+1)")
                .hasMessageContaining("la recouvre");
    }

    /**
     * Le meme jeu, les deux blocs remis dans l'ordre : a specificite egale, la regle de position est
     * ecrite apres, donc elle l'emporte. <b>Sans ce cas vert, la clause serait une interdiction de
     * declarer un etat sur un pressable colore par sa position</b> - elle interdit qu'il la recouvre,
     * jamais qu'il existe. C'est la lecon de la seconde mutation d'ADR-0025.
     */
    @Test
    void leMemeJeuAvecLesDeuxBlocsDansLOrdreResteVert() {
        verifierQuAucunEtatNeRecouvreUneCouleurDePosition(
                CONTRE_EXEMPLE.resolve("ordre-inverse/corrige"));
    }

    /** Copie l'arbre de ressources servi - gabarits et feuilles - pour le muter sans le toucher. */
    private static void copierArbreReel(Path source, Path cible) throws IOException {
        try (Stream<Path> arborescence = Files.walk(source)) {
            for (Path chemin : arborescence.toList()) {
                Path destination = cible.resolve(source.relativize(chemin).toString());
                if (Files.isDirectory(chemin)) {
                    Files.createDirectories(destination);
                } else {
                    Files.createDirectories(destination.getParent());
                    Files.copy(chemin, destination);
                }
            }
        }
    }
}
