package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S42-MAT-TEXTUEL, le paquet {@code cp-deplacements-quadrillage}, ligne {@code MAT-CP-10} de
 * mathematiques (CP), moitie textuelle seulement.</b> Referentiel :
 * {@code squad/programme/mathematiques/competences-cp.md}, ligne 31. Arbitrage prealable deja
 * rendu, cite en entier par la story (`ARBITRAGE-PREALABLE-s32...`, section 17.7) : decrire un
 * deplacement n'est pas une designation et se sert en choix textuel ; la moitie "se reperer et se
 * deplacer" (designation sur quadrillage, palier 2) reste hors de portee ici.
 *
 * <p><b>REFUS du Done pedagogique le 2026-09-24 par nadia-prof, instance B</b> (DEC-0098 ;
 * {@code squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md}), sur la version
 * precedente de ce paquet, dont ce test verifiait la forme fautive : la bonne reponse y etait
 * l'etiquette du geste (avancer, reculer, tourner a gauche, tourner a droite), deja donnee dans
 * l'enonce. Correctif : chaque enonce pose une position de depart sur une suite de cases
 * numerotees et demande la case d'arrivee apres un deplacement d'une case ; ce fichier verifie
 * desormais que la bonne reponse est toujours cette case d'arrivee, jamais l'etiquette du geste, et
 * que les quatre formes de deplacement du critere d'acceptation - avancer, reculer, aller a gauche,
 * aller a droite - figurent chacune trois fois dans l'enonce (repartition deja en place, inchangee
 * par le correctif).</p>
 *
 * <p><b>Le plafond de 20 items</b> (nadia-prof, addendum, section 12.2).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : que chaque scene decrite en mots soit plausible et
 * lisible par un enfant de CP reste une lecture de sens, garde de {@code nadia-prof}. Ce paquet
 * n'a aucune reserve de longueur : ses enonces sont mesures conformes a {@code LON-1}/{@code LON-2}
 * (ADR-0089) des leur premier commit.</p>
 */
class PaquetCpDeplacementsQuadrillageTest {

    private static final String PAQUET = "cp-deplacements-quadrillage";

    private static final int PLAFOND_ITEMS = 20;

    /**
     * Les quatre formes de deplacement du critere d'acceptation de la story, telles qu'elles
     * figurent desormais dans l'enonce (et non plus dans la bonne reponse, corrige suite au refus).
     */
    private static final Set<String> FORMES_DE_DEPLACEMENT =
            Set.of("avance", "recule", "va vers la gauche", "va vers la droite");

    /** Case de depart declaree dans l'enonce, sous le patron impose par le correctif. */
    private static final Pattern CASE_DE_DEPART = Pattern.compile("sur la case (\\d+)");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * Repartition equilibree deja en place, inchangee par le correctif : les quatre formes de
     * deplacement du critere d'acceptation figurent chacune dans l'enonce de trois items.
     */
    @Test
    void lesQuatreFormesDeDeplacementFigurentChacuneDansTroisEnonces() {
        for (String forme : FORMES_DE_DEPLACEMENT) {
            long occurrences = items().stream().filter(item -> item.enonce().contains(forme)).count();
            assertThat(occurrences)
                    .as("la forme de deplacement '%s' doit figurer dans l'enonce de trois items",
                            forme)
                    .isEqualTo(3);
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Motif 1 du refus de nadia-prof : la bonne reponse n'est plus jamais l'etiquette du geste deja
     * donnee dans l'enonce (avancer, reculer, tourner a gauche, tourner a droite), mais toujours une
     * case numerotee du quadrillage.
     */
    @Test
    void laBonneReponseNEstJamaisLEtiquetteDuGeste() {
        Pattern caseNumerotee = Pattern.compile("^la case \\d+$");
        for (Question item : items()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse est une case numerotee, jamais l'etiquette du"
                            + " geste (motif 1 du refus de nadia-prof, DEC-0098)", item.id().value())
                    .matches(caseNumerotee.pattern());
        }
    }

    /**
     * Correctif exige par nadia-prof (DEC-0098) : chaque enonce pose une position de depart sur une
     * suite de cases numerotees ; les trois propositions sont les trois cases adjacentes a cette
     * position de depart (la case de depart elle-meme, et celle de part et d'autre), et la bonne
     * reponse est toujours celle du sens annonce dans l'enonce, jamais la case de depart.
     */
    @Test
    void lesPropositionsSontLesTroisCasesAdjacentesALaCaseDeDepartEtLaBonneReponseSuitLeSensAnnonce() {
        for (Question item : items()) {
            Matcher depart = CASE_DE_DEPART.matcher(item.enonce());
            assertThat(depart.find())
                    .as("item %s : l'enonce doit poser une position de depart 'sur la case N'",
                            item.id().value())
                    .isTrue();
            int caseDeDepart = Integer.parseInt(depart.group(1));
            boolean sensPositif = item.enonce().contains("avance")
                    || item.enonce().contains("va vers la droite");
            boolean sensNegatif = item.enonce().contains("recule")
                    || item.enonce().contains("va vers la gauche");
            assertThat(sensPositif ^ sensNegatif)
                    .as("item %s : l'enonce annonce un seul sens de deplacement", item.id().value())
                    .isTrue();
            int caseDArrivee = caseDeDepart + (sensPositif ? 1 : -1);

            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse est la case d'arrivee du sens annonce, jamais"
                            + " la case de depart", item.id().value())
                    .isEqualTo("la case " + caseDArrivee);
            assertThat(item.distracteurs())
                    .as("item %s : les distracteurs sont les deux autres cases adjacentes a la case"
                            + " de depart", item.id().value())
                    .containsExactlyInAnyOrder("la case " + caseDeDepart,
                            "la case " + (sensPositif ? caseDeDepart - 1 : caseDeDepart + 1));
        }
    }

    /**
     * Motif 2 du refus de nadia-prof : chaque enonce pose un etat initial (une case de depart) avant
     * de decrire le deplacement, pour que la reponse se deduise de l'enonce et non d'une hypothese
     * sur un etat qui precederait le texte.
     */
    @Test
    void chaqueEnonceDeclareSonEtatInitialAvantLeDeplacement() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : l'etat initial (case de depart) est pose dans l'enonce, avant le"
                            + " deplacement (motif 2 du refus de nadia-prof, DEC-0098)",
                            item.id().value())
                    .containsPattern(CASE_DE_DEPART.pattern());
        }
    }

    /**
     * Critere d'acceptation et section 7.5 de l'avis de faisabilite : aucun item ne demande une
     * designation d'une case ou d'un point sur une image, palier 2 hors de portee ici. Ce paquet ne
     * porte aucune illustration.
     */
    @Test
    void aucunItemNePorteDIllustration() {
        for (Question item : items()) {
            assertThat(item.illustration())
                    .as("item %s : ce paquet est en choix textuel seul, aucune designation sur"
                            + " une image (palier 2, hors de portee ici)", item.id().value())
                    .isEmpty();
        }
    }

    /**
     * Chaque item declare uniquement MAT-CP-10, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne, et sa moitie textuelle seulement : sous ADR-0081 clause 6, elle est declaree une
     * fois au niveau du paquet et aucun item n'a besoin de porter sa propre cle
     * {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementMatCp10() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpDeplacementsQuadrillageTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("MAT-CP-10");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
