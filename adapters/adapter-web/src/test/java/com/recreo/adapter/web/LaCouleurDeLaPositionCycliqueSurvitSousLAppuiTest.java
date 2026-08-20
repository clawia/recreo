package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S15-8 (nadia-prof, remesure sous appui reel, s17) : sous un appui reel, une proposition perdait
 * la couleur de sa position cyclique - position 1 ({@code #bdd2ef}), 2 ({@code #a9dcd8}), 3
 * ({@code #dcc8ed}) devenaient toutes {@code #2e6f9e}, texte blanc. Mot pour mot ce que
 * {@code S15-8} interdit.
 *
 * <p><b>La cause, etablie par nadia-prof en interrogeant la cascade du navigateur.</b> Le premier
 * correctif de {@code S17-D} a ajoute l'ancetre {@code #jeu} (un identifiant) devant
 * {@code .proposition:active}, faisant passer sa specificite au-dessus de celle des quatre regles
 * cycliques de position ({@code .propositions .proposition:nth-child(...)}, plus bas dans
 * {@code recreo.css}). Ces deux jeux de regles etaient <b>volontairement a la meme specificite</b>
 * (trois classes/pseudo-classes chacun) - un commentaire deja present dans le fichier le disait
 * ("cette regle est placee avant les quatre regles de position ci-dessous, a la meme specificite
 * (0,3,0) qu'elles") - et l'ordre de declaration dans le fichier tranchait en faveur des regles
 * cycliques, ecrites apres. Un identifiant bat cette egalite sans espoir de retour, quel que soit
 * l'ordre : {@code S15-8} tombait des que la specificite cessait d'etre une egalite.
 *
 * <p><b>Ce que {@code AucunEtatDePressableNEstAbandonneAuTiersTest} (troisieme amendement
 * d'ADR-0046, theo-archi) ne peut pas voir.</b> Sa clause neuve compare la feuille maison a la
 * feuille tierce ; les quatre regles cycliques ne portent aucune pseudo-classe d'etat
 * ({@code :hover}/{@code :active}/{@code :focus-visible}) - ce sont des regles de position, pas
 * des regles d'etat - et restent donc invisibles a cette derivation, dans un sens comme dans
 * l'autre. La relation qui protege {@code S15-8} est entre deux regles <b>maison</b>, jamais entre
 * la maison et le tiers : c'est cette relation-la, et elle seule, que ce fichier verifie.
 *
 * <p><b>Le correctif</b> : l'ancetre du sujet neutre redevient un ancetre reel <b>sans
 * identifiant</b>, {@code .propositions} - le conteneur direct de chaque proposition, deja
 * l'ancetre litteral des quatre regles cycliques elles-memes. {@code .propositions
 * .proposition:active} vaut trois classes/pseudo-classes, exactement comme avant {@code S17-D} et
 * exactement comme les regles cycliques : l'egalite - et donc l'ordre - est restauree. Les quatre
 * autres sujets du meme groupe ({@code .continuer-partie}, {@code .demarrer}, {@code .recommencer},
 * {@code .sortie-pleine}) n'ont aucune regle cyclique a proteger et gardent {@code #jeu}.
 */
class LaCouleurDeLaPositionCycliqueSurvitSousLAppuiTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/LaCouleurDeLaPositionCycliqueSurvitSousLAppuiTest");

    private static final String SELECTEUR_NEUTRE = ".propositions .proposition:active";

    private static final List<String> SELECTEURS_CYCLIQUES = List.of(
            ".propositions .proposition:nth-child(4n+1)",
            ".propositions .proposition:nth-child(4n+2)",
            ".propositions .proposition:nth-child(4n+3)",
            ".propositions .proposition:nth-child(4n+4)");

    /**
     * Une specificite restreinte a ce dont ce fichier a besoin : porte-t-elle un identifiant, et
     * combien de classes/attributs/pseudo-classes ? Aucun des selecteurs compares ici n'a de
     * pseudo-classe fonctionnelle ({@code :not()}/{@code :has()}) : un comptage litteral des
     * points et deux-points suffit, sans le traitement CSS Selectors Level 4 qu'une forme plus
     * generale exigerait (ADR-0046, troisieme amendement).
     */
    private record Specificite(boolean identifiant, int classes) {
        static Specificite de(String selecteurComplet) {
            boolean identifiant = selecteurComplet.contains("#");
            int classes = 0;
            for (int i = 0; i < selecteurComplet.length(); i++) {
                char c = selecteurComplet.charAt(i);
                if (c == '.' || c == ':') {
                    classes++;
                }
            }
            return new Specificite(identifiant, classes);
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String feuilleMaisonConcatenee(Path racine) {
        StringBuilder tout = new StringBuilder();
        for (Path fichier : TailleDesTextesEtBoutonsTest.feuillesMaison(racine)) {
            tout.append(lire(fichier));
        }
        return tout.toString();
    }

    static void verifierQueLaCouleurCycliqueSurvitSousLAppui(Path racine) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racine);

        assertThat(maison)
                .as("le sujet neutre \"%s\" doit exister dans la feuille maison", SELECTEUR_NEUTRE)
                .containsKey(SELECTEUR_NEUTRE);
        Specificite neutre = Specificite.de(SELECTEUR_NEUTRE);

        for (String cyclique : SELECTEURS_CYCLIQUES) {
            assertThat(maison)
                    .as("la regle cyclique \"%s\" doit exister dans la feuille maison", cyclique)
                    .containsKey(cyclique);
            Specificite specifiteCyclique = Specificite.de(cyclique);
            assertThat(neutre)
                    .as("\"%s\" (%s) doit avoir exactement la meme specificite que \"%s\" (%s) : "
                            + "une egalite tranchee par l'ordre de declaration, jamais une "
                            + "inegalite dans un sens (S15-8 defaite) ni dans l'autre (S17-D "
                            + "redefaite)",
                            SELECTEUR_NEUTRE, neutre, cyclique, specifiteCyclique)
                    .isEqualTo(specifiteCyclique);
        }

        String source = feuilleMaisonConcatenee(racine);
        int positionNeutre = source.indexOf(SELECTEUR_NEUTRE);
        assertThat(positionNeutre).as("le sujet neutre doit etre lisible dans la source").isNotNegative();
        for (String cyclique : SELECTEURS_CYCLIQUES) {
            int positionCyclique = source.indexOf(cyclique);
            assertThat(positionCyclique).as("la regle cyclique doit etre lisible dans la source").isNotNegative();
            assertThat(positionNeutre)
                    .as("le sujet neutre doit rester declare avant \"%s\" : a specificite egale, "
                            + "c'est l'ordre de declaration qui donne la couleur de position a la "
                            + "regle cyclique (S15-8)", cyclique)
                    .isLessThan(positionCyclique);
        }
    }

    @Test
    void laCouleurDePositionSurvitSousUnAppuiReel() {
        verifierQueLaCouleurCycliqueSurvitSousLAppui(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la forme exacte du premier correctif de {@code S17-D}, qui a defait
     * {@code S15-8} - {@code #jeu} devant {@code .proposition:active} plutot que
     * {@code .propositions}.
     */
    @Test
    void lAncetreIdentifiantSurLaPropositionFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLaCouleurCycliqueSurvitSousLAppui(
                CONTRE_EXEMPLES.resolve("ancetre-id-sur-la-proposition")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("doit exister dans la feuille maison");
    }
}
