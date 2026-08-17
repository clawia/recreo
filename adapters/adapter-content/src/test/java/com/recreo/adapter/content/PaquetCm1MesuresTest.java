package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

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
 * S12-2 (DEC-0029, docs/backlog/backlog.md section "Sprint 12") et l'arbitrage prealable de
 * nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md, section 3, C-1 a
 * C-10) : le paquet "cm1-mesures", joue par "bandit-manchot-conversions-unites-cm1". Ne couvre que
 * les criteres d'acceptation automatisables ; l'exactitude est recalculee ici a partir des unites
 * lues dans l'enonce, jamais recopiee. La plausibilite pedagogique des distracteurs reste le
 * jugement de nadia-prof.
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item) sont deja derivees du repertoire de
 * contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1MesuresTest {

    private static final String PAQUET = "cm1-mesures";

    private static final Pattern ENONCE = Pattern.compile("^(\\d+) (\\S+), combien de (\\S+) \\?$");

    /** C-3 : les seules unites admises, dans leur base propre a chaque grandeur (mm, g, ml = 1). */
    private static final Map<String, Long> BASE_PAR_UNITE = Map.ofEntries(
            Map.entry("mm", 1L), Map.entry("cm", 10L), Map.entry("m", 1000L), Map.entry("km", 1_000_000L),
            Map.entry("g", 1L), Map.entry("kg", 1000L), Map.entry("tonne", 1_000_000L), Map.entry("tonnes", 1_000_000L),
            Map.entry("ml", 1L), Map.entry("cl", 10L), Map.entry("L", 1000L));

    private static final Set<String> GRANDEUR_LONGUEUR = Set.of("mm", "cm", "m", "km");
    private static final Set<String> GRANDEUR_MASSE = Set.of("g", "kg", "tonne", "tonnes");
    private static final Set<String> GRANDEUR_CONTENANCE = Set.of("ml", "cl", "L");

    private record Conversion(Question item, long source, String uniteSource, String uniteCible) {
    }

    private static Conversion conversionDe(Question item) {
        Matcher forme = ENONCE.matcher(item.enonce());
        assertThat(forme.matches()).as("'%s' : enonce autosuffisant (C-8)", item.enonce()).isTrue();
        long source = Long.parseLong(forme.group(1));
        return new Conversion(item, source, forme.group(2), forme.group(3));
    }

    private static String grandeurDe(String unite) {
        if (GRANDEUR_LONGUEUR.contains(unite)) {
            return "longueur";
        }
        if (GRANDEUR_MASSE.contains(unite)) {
            return "masse";
        }
        if (GRANDEUR_CONTENANCE.contains(unite)) {
            return "contenance";
        }
        throw new AssertionError("unite hors des trois grandeurs admises (C-3) : " + unite);
    }

    @Test
    void lePaquetCompteAuMoinsVingtQuatreItems() {
        assertThat(tousLesItems(paquet()))
                .as("au moins 24 items au total")
                .hasSizeGreaterThanOrEqualTo(24);
    }

    @Test
    void chaqueGrandeurEstRepresenteeParAuMoinsHuitItems() {
        Map<String, Long> parGrandeur = tousLesItems(paquet()).stream()
                .map(PaquetCm1MesuresTest::conversionDe)
                .collect(java.util.stream.Collectors.groupingBy(
                        conversion -> grandeurDe(conversion.uniteSource()), java.util.stream.Collectors.counting()));

        for (String grandeur : List.of("longueur", "masse", "contenance")) {
            assertThat(parGrandeur.getOrDefault(grandeur, 0L))
                    .as("grandeur '%s' : au moins 8 items (aucune des trois n'est absente ni reduite)", grandeur)
                    .isGreaterThanOrEqualTo(8L);
        }
    }

    @Test
    void chaqueItemConvertitVersLaMemeGrandeurAvecUnFacteurDeDixCentOuMille() {
        for (Question item : tousLesItems(paquet())) {
            Conversion conversion = conversionDe(item);
            String uniteCibleSingulier = conversion.uniteCible().equals("tonnes") ? "tonne" : conversion.uniteCible();

            assertThat(grandeurDe(conversion.uniteSource()))
                    .as("'%s' : la conversion reste dans la meme grandeur (C-3)", item.enonce())
                    .isEqualTo(grandeurDe(uniteCibleSingulier));

            long baseSource = BASE_PAR_UNITE.get(conversion.uniteSource());
            long baseCible = BASE_PAR_UNITE.get(uniteCibleSingulier);
            long facteur = baseSource >= baseCible ? baseSource / baseCible : baseCible / baseSource;

            assertThat(facteur)
                    .as("'%s' : le facteur entre les deux unites vaut 10, 100 ou 1000 (C-2)", item.enonce())
                    .isIn(10L, 100L, 1000L);
        }
    }

    @Test
    void laBonneReponseEstNumeriquementExacteEtSansDecimale() {
        for (Question item : tousLesItems(paquet())) {
            Conversion conversion = conversionDe(item);
            String uniteCibleSingulier = conversion.uniteCible().equals("tonnes") ? "tonne" : conversion.uniteCible();
            long baseSource = BASE_PAR_UNITE.get(conversion.uniteSource());
            long baseCible = BASE_PAR_UNITE.get(uniteCibleSingulier);

            assertThat(conversion.source() * baseSource % baseCible)
                    .as("'%s' : la conversion tombe juste, sans reste ni virgule (C-1)", item.enonce())
                    .isZero();
            long attendu = conversion.source() * baseSource / baseCible;

            assertThat(item.bonneReponse())
                    .as("'%s' : la bonne reponse recalculee a partir des unites", item.enonce())
                    .isEqualTo(attendu + " " + conversion.uniteCible());
        }
    }

    @Test
    void chaqueDistracteurPorteLUniteCibleEtAucunNombreDecimal() {
        Pattern nombreEntierAvecUnite = Pattern.compile("^\\d+ \\S+$");
        for (Question item : tousLesItems(paquet())) {
            Conversion conversion = conversionDe(item);
            assertThat(item.distracteurs())
                    .as("'%s' : exactement deux distracteurs, ni la bonne reponse ni des doublons",
                            item.enonce())
                    .hasSize(2);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur)
                        .as("'%s' : le distracteur '%s' est un entier suivi de l'unite cible, jamais une"
                                + " virgule (C-1, C-6)", item.enonce(), distracteur)
                        .matches(nombreEntierAvecUnite)
                        .endsWith(" " + conversion.uniteCible());
            }
        }
    }

    @Test
    void aucuneUniteAutreQueCellesDuReferentiel() {
        for (Question item : tousLesItems(paquet())) {
            Conversion conversion = conversionDe(item);
            assertThat(BASE_PAR_UNITE).as("'%s' : unite source admise (C-3)", item.enonce())
                    .containsKey(conversion.uniteSource());
            String uniteCibleSingulier = conversion.uniteCible().equals("tonnes") ? "tonne" : conversion.uniteCible();
            assertThat(BASE_PAR_UNITE).as("'%s' : unite cible admise (C-3)", item.enonce())
                    .containsKey(uniteCibleSingulier);
        }
    }

    @Test
    void leTitreDuJeuAnnonceLesTroisGrandeurs() {
        GameDefinition jeu = jeu("bandit-manchot-conversions-unites-cm1");

        assertThat(jeu.titre())
                .as("C-9 : le titre ne se limite pas a une seule des trois grandeurs")
                .doesNotContainIgnoringCase("longueur")
                .doesNotContainIgnoringCase("masse")
                .doesNotContainIgnoringCase("contenance");
    }

    /*
     * s14, dette technique 1 (avis-faisabilite.md section 8.1) : la verification "l'entree de
     * catalogue charge bien ce paquet" est desormais generique, derivee de catalogue.yaml, dans
     * ChaqueEntreeDuCatalogueChargeBienSonPaquetTest. Elle n'est plus repetee ici.
     */

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static GameDefinition jeu(String id) {
        return new YamlContentPackLoader().games().stream()
                .filter(candidat -> candidat.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
