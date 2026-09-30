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
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S44-MUS-VOCABULAIRE, le paquet {@code cp-musique-vocabulaire}, ligne {@code MUS-CP-11} de
 * "Éducation musicale" (CP), premiere ligne de cette matiere.</b> Referentiel :
 * {@code squad/programme/education-musicale/competences-cp.md}, ligne 11, jugement de faisabilite
 * du 2026-09-03. Onze items, deux taches (FER-3) : huit items "definition -&gt; mot" ou chacun des
 * quatre termes de la parenthese du referentiel (refrain, couplet, pulsation, silence) est servi
 * deux fois en bonne reponse (FER-1), et trois items "mot -&gt; definition", qui ne comptent pas
 * pour FER-1 (arbitrage prealable de nadia-prof du 2026-09-30, section 2.4).
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude et la verifiabilite de chaque definition, et
 * le fond des exigences 1 a 5 de {@code MUS-3} (definition generale, aucun geste, aucun comptage,
 * silence musical, distracteurs non defendables sur le sens) - relecture de {@code nadia-prof} au
 * Done pedagogique. Seule l'exigence 6 de {@code MUS-3}, lexicale, et la liste des distracteurs
 * explicitement exclus par elle sont encodees ici.</p>
 */
class PaquetCpMusiqueVocabulaireTest {

    private static final String PAQUET = "cp-musique-vocabulaire";

    /** ADR-0023 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Les quatre termes de la parenthese du referentiel, chacun attendu en bonne reponse. */
    private static final Set<String> TERMES_DE_LA_PARENTHESE =
            Set.of("le refrain", "le couplet", "la pulsation", "le silence");

    /**
     * MUS-3, exigence 5 : distracteurs exclus sur un item de pulsation ou de silence parce que
     * defendables par un adulte - des reponses vraies ou couramment admises, pas seulement des
     * erreurs plausibles.
     */
    private static final Set<String> DISTRACTEURS_DEFENDABLES_EXCLUS = Set.of(
            "le rythme", "la rythme", "le tempo", "la mesure", "la pause", "le soupir");

    /**
     * MUS-1 et MUS-2, exigence 6 : lexique interdit sur tout texte de tout item (enonce, bonne
     * reponse, distracteurs, indice). "taper" se cherche en mot entier, jamais en sous-chaine
     * ("tape" est dans "étape").
     */
    private static final Set<String> MOTS_INTERDITS_SOUS_CHAINE =
            Set.of("ecout", "entend", "adulte", "fredonn", "frapp");

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

    /** La tache "definition -> mot" : la bonne reponse est l'un des quatre termes du referentiel. */
    private static List<Question> itemsDefinitionVersMot() {
        return items().stream()
                .filter(item -> TERMES_DE_LA_PARENTHESE.contains(item.bonneReponse())).toList();
    }

    /** La tache "mot -> definition" : la bonne reponse est une phrase, pas un terme nu. */
    private static List<Question> itemsMotVersDefinition() {
        return items().stream()
                .filter(item -> !TERMES_DE_LA_PARENTHESE.contains(item.bonneReponse())).toList();
    }

    /** Tous les textes d'un item : enonce, bonne reponse, distracteurs, indice. */
    private static List<String> tousLesTextes(Question item) {
        List<String> textes = new ArrayList<>();
        textes.add(item.enonce());
        textes.add(item.bonneReponse());
        textes.addAll(item.distracteurs());
        item.indice().ifPresent(textes::add);
        return textes;
    }

    /** ADR-0023 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("ADR-0023 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** FER-1 : les quatre termes explicitement cites par le referentiel sont chacun en bonne reponse. */
    @Test
    void fer1LesQuatreTermesDeLaParentheseSontChacunEnBonneReponse() {
        Set<String> reponses = itemsDefinitionVersMot().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre termes doivent être en bonne réponse")
                .containsExactlyInAnyOrderElementsOf(TERMES_DE_LA_PARENTHESE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Distinguees par la forme de la bonne reponse (arbitrage prealable du 2026-09-30, section 2.4) :
     * un terme nu du referentiel pour la premiere, une phrase de definition pour la seconde.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int definitionVersMot = itemsDefinitionVersMot().size();
        int motVersDefinition = itemsMotVersDefinition().size();

        assertThat(definitionVersMot).as("la tache definition -> mot est servie").isGreaterThan(0);
        assertThat(motVersDefinition).as("la tache mot -> definition est servie").isGreaterThan(0);
        assertThat(Math.min(definitionVersMot, motVersDefinition))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** MUS-2 : aucun item ne porte de texte a voix haute - seul canal par lequel un adulte parle. */
    @Test
    void mus2AucunItemNePorteDeTexteAVoixHaute() {
        for (Question item : items()) {
            assertThat(item.texteAVoixHaute())
                    .as("item %s : MUS-2, aucun texte a voix haute", item.id().value())
                    .isEmpty();
        }
    }

    /**
     * MUS-1 et MUS-2, exigence 6 de MUS-3 : aucun texte de l'item ne contient l'un des mots
     * interdits, en sous-chaine, sans tenir compte de la casse.
     */
    @Test
    void mus1EtMus2AucunTexteNePorteLeLexiqueInterdit() {
        for (Question item : items()) {
            for (String texte : tousLesTextes(item)) {
                String normalise = texte.toLowerCase(Locale.FRENCH);
                for (String motInterdit : MOTS_INTERDITS_SOUS_CHAINE) {
                    assertThat(normalise)
                            .as("item %s : MUS-1/MUS-2, aucun texte ne contient \"%s\"",
                                    item.id().value(), motInterdit)
                            .doesNotContain(motInterdit);
                }
            }
        }
    }

    /**
     * MUS-2, exigence 6 : le verbe "taper" est interdit sous toutes ses formes, en mot entier
     * seulement - "tape" est un mot entier dans "il tape", mais une sous-chaine dans "étape".
     */
    @Test
    void mus2AucunTexteNePorteLeVerbeTaper() {
        java.util.regex.Pattern motTaper = java.util.regex.Pattern.compile(
                "(?i)\\btap(e|es|ent|ons|ez|ais|ait|aient|a|èrent|é|ée|ées|és|er|ant)\\b");
        for (Question item : items()) {
            for (String texte : tousLesTextes(item)) {
                assertThat(motTaper.matcher(texte).find())
                        .as("item %s : MUS-2, aucun texte ne contient le verbe \"taper\" - \"%s\"",
                                item.id().value(), texte)
                        .isFalse();
            }
        }
    }

    /**
     * MUS-3, exigence 5 : sur un item dont la bonne reponse est "la pulsation" ou "le silence",
     * aucun distracteur n'est un mot exclu parce que defendable (le rythme, le tempo, la mesure,
     * la pause, le soupir).
     */
    @Test
    void mus3AucunDistracteurDePulsationOuSilenceNEstDefendable() {
        for (Question item : items()) {
            if (!item.bonneReponse().equals("la pulsation") && !item.bonneReponse().equals("le silence")) {
                continue;
            }
            for (String distracteur : item.distracteurs()) {
                assertThat(DISTRACTEURS_DEFENDABLES_EXCLUS)
                        .as("item %s : MUS-3, distracteur \"%s\" exclu parce que defendable",
                                item.id().value(), distracteur)
                        .doesNotContain(distracteur.toLowerCase(Locale.FRENCH));
            }
        }
    }

    /**
     * MUS-3, exigence 5 : sur un item de pulsation ou de silence, les distracteurs sont les autres
     * mots du vocabulaire musical du paquet.
     */
    @Test
    void mus3LesDistracteursDePulsationOuSilenceSontDesTermesDuVocabulaireMusical() {
        for (Question item : items()) {
            if (!item.bonneReponse().equals("la pulsation") && !item.bonneReponse().equals("le silence")) {
                continue;
            }
            assertThat(TERMES_DE_LA_PARENTHESE)
                    .as("item %s : MUS-3, les distracteurs sont des termes du vocabulaire musical",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /** Chaque item porte un indice. */
    @Test
    void chaqueItemPorteUnIndice() {
        for (Question item : items()) {
            assertThat(item.indice())
                    .as("item %s : indice present et non vide", item.id().value())
                    .isPresent();
            assertThat(item.indice().orElseThrow()).isNotBlank();
        }
    }

    /** Ligne de referentiel declaree, MUS-CP-11 uniquement. */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }
}
