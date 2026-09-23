package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S40-HDA, le paquet {@code cm1-histoire-des-arts-relier-contexte}, ligne {@code HDA-CM1-05}
 * d'histoire des arts (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s40-
 * relier-une-oeuvre-a-son-contexte-hda-cm1-05.md}, identifiants {@code HDA-1} a {@code HDA-4}.
 *
 * <p>Ce test encode ce qui se compte : la fermeture du vocabulaire de reponse (HDA-2 - periode,
 * civilisation ou usage, jamais autre chose), la diversite du corpus sur au moins deux periodes,
 * deux civilisations et quatre familles de style (HDA-4), et la forme uniforme a trois
 * propositions.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : qu'aucun item ne nomme une oeuvre individuelle (HDA-1),
 * que chaque distracteur reste ecarte par un marqueur specifique croise a un second trait plutot
 * que par un trait partage (HDA-3), et l'exactitude factuelle de chaque fait de contexte cite -
 * cela reste la garde de {@code nadia-prof}.</p>
 */
class PaquetCm1HistoireDesArtsRelierContexteTest {

    private static final String PAQUET = "cm1-histoire-des-arts-relier-contexte";

    /** HDA-2 : les seules formes de reponse admissibles sont periode, civilisation ou usage. */
    private static final Set<String> PERIODES = Set.of("le Moyen Âge", "l'Antiquité", "la Renaissance");

    private static final Set<String> CIVILISATIONS = Set.of("la civilisation grecque antique",
            "la civilisation romaine antique", "la civilisation égyptienne antique");

    private static final Set<String> USAGES = Set.of("c'est un lieu de culte", "c'est un tombeau",
            "c'est une forteresse de défense", "à défendre ses habitants",
            "à rendre un culte religieux", "à abriter un défunt");

    private static final Set<String> STYLES = Set.of("le style roman", "le style gothique",
            "le style classique");

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

    /** HDA-2 : toute proposition (bonne reponse ou distracteur) appartient a un vocabulaire clos. */
    @Test
    void hda2ToutePropositionEstUnePeriodeUneCivilisationUnUsageOuUnStyle() {
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            for (String proposition : propositions) {
                boolean admissible = PERIODES.contains(proposition) || CIVILISATIONS.contains(proposition)
                        || USAGES.contains(proposition) || STYLES.contains(proposition);
                assertThat(admissible)
                        .as("item %s : la proposition \"%s\" doit etre une periode, une civilisation,"
                                + " un usage ou un style etabli (HDA-2)", item.id().value(), proposition)
                        .isTrue();
            }
        }
    }

    /** Chaque item propose exactement deux distracteurs, donc trois propositions. */
    @Test
    void chaqueItemPorteExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .isEqualTo(2);
        }
    }

    /** HDA-4 : le corpus ne tire pas tous ses items d'une seule periode. */
    @Test
    void hda4AuMoinsDeuxPeriodesDistinctesEnBonneReponse() {
        Set<String> periodesVues = items().stream().map(Question::bonneReponse)
                .filter(PERIODES::contains).collect(java.util.stream.Collectors.toSet());

        assertThat(periodesVues)
                .as("HDA-4 : au moins deux periodes distinctes en bonne reponse")
                .hasSizeGreaterThanOrEqualTo(2);
    }

    /** HDA-4 : le corpus ne tire pas tous ses items d'une seule civilisation. */
    @Test
    void hda4AuMoinsDeuxCivilisationsDistinctesEnBonneReponse() {
        Set<String> civilisationsVues = items().stream().map(Question::bonneReponse)
                .filter(CIVILISATIONS::contains).collect(java.util.stream.Collectors.toSet());

        assertThat(civilisationsVues)
                .as("HDA-4 : au moins deux civilisations distinctes en bonne reponse")
                .hasSizeGreaterThanOrEqualTo(2);
    }

    /** HDA-4 : le corpus ne tire pas tous ses items d'un seul type d'edifice. */
    @Test
    void hda4AuMoinsQuatreFamillesDeStyleDistinctes() {
        Set<String> famillesVues = new HashSet<>();
        for (Question item : items()) {
            String enonce = item.enonce();
            if (enonce.contains("vitraux color")) {
                famillesVues.add("gothique");
            } else if (enonce.contains("fenêtres sont petites et étroites")) {
                famillesVues.add("romane");
            } else if (enonce.contains("mythologie grecque")) {
                famillesVues.add("temple-grec");
            } else if (enonce.contains("inscription en latin")) {
                famillesVues.add("temple-romain");
            } else if (enonce.contains("pyramide")) {
                famillesVues.add("funeraire-egyptien");
            } else if (enonce.contains("murailles")) {
                famillesVues.add("chateau-fort");
            }
        }
        assertThat(famillesVues)
                .as("HDA-4 : au moins quatre familles de style distinctes")
                .hasSizeGreaterThanOrEqualTo(4);
    }
}
