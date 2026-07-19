package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests deterministes des 4 jeux "quiz-aventure" a liste finie (questionner le monde
 * CP, sciences CM1, histoire CM1, geographie CM1). Verifie que chaque generateur
 * produit des items conformes : {@code reelSymbols} VIDE (pas de rouleaux),
 * {@code question} = une question connue de la liste verifiee, la bonne reponse est
 * celle attendue, les distracteurs sont exactement ceux attendus (distincts, sans la
 * bonne reponse). Verifie aussi le mode session (anti-doublon + epuisement) commun a
 * la mecanique "quiz-aventure".
 */
class QuizGeneratorTest {

    private static final int SAMPLES = 400;

    // Reference verifiee : question -> [bonne reponse, distracteur, distracteur, ...].
    private static final Map<String, String[]> QUESTIONNER_LE_MONDE = Map.ofEntries(
            Map.entry("Quel animal fait « miaou » ?", new String[] {"Le chat", "Le chien", "La vache"}),
            Map.entry("Où vivent les poissons ?", new String[] {"Dans l'eau", "Dans les arbres", "Sous la terre"}),
            Map.entry("Combien de pattes a une araignée ?", new String[] {"8", "6", "4"}),
            Map.entry("De quoi une plante a-t-elle besoin pour pousser ?",
                    new String[] {"De l'eau et de la lumière", "Du chocolat", "Du sable"}),
            Map.entry("Quel est le petit de la vache ?", new String[] {"Le veau", "Le poulain", "L'agneau"}),
            Map.entry("Que mange surtout le lapin ?", new String[] {"Des carottes", "De la viande", "Des cailloux"}),
            Map.entry("Quand il neige, il fait...", new String[] {"froid", "chaud", "lourd"}));

    private static final Map<String, String[]> SCIENCES = Map.ofEntries(
            Map.entry("À quelle température l'eau bout-elle ?", new String[] {"100 °C", "50 °C", "0 °C"}),
            Map.entry("Quand l'eau gèle, elle devient...", new String[] {"solide", "gazeuse", "invisible"}),
            Map.entry("Quel organe sert à respirer ?", new String[] {"Les poumons", "L'estomac", "Le foie"}),
            Map.entry("Le Soleil est une...", new String[] {"étoile", "planète", "lune"}),
            Map.entry("Combien de pattes a un insecte ?", new String[] {"6", "8", "4"}),
            Map.entry("Grâce à quoi les plantes fabriquent-elles leur nourriture avec la lumière ?",
                    new String[] {"La photosynthèse", "La digestion", "L'évaporation"}));

    private static final Map<String, String[]> HISTOIRE = Map.ofEntries(
            Map.entry("En quelle année a eu lieu la Révolution française ?",
                    new String[] {"1789", "1515", "1945"}),
            Map.entry("Quel monument parisien date de 1889 ?",
                    new String[] {"La tour Eiffel", "Notre-Dame", "Le château de Versailles"}),
            Map.entry("Qui a fait construire le château de Versailles ?",
                    new String[] {"Louis XIV", "Napoléon", "Charlemagne"}),
            Map.entry("Les Gaulois vivaient il y a environ...",
                    new String[] {"2000 ans", "200 ans", "100 ans"}),
            Map.entry("La Préhistoire, c'est l'époque...",
                    new String[] {"avant l'écriture", "du Moyen Âge", "des rois de France"}));

    private static final Map<String, String[]> GEOGRAPHIE = Map.ofEntries(
            Map.entry("Quelle est la capitale de la France ?", new String[] {"Paris", "Lyon", "Marseille"}),
            Map.entry("Quel fleuve traverse Paris ?", new String[] {"La Seine", "La Loire", "Le Rhône"}),
            Map.entry("Sur quel continent se trouve la France ?", new String[] {"L'Europe", "L'Afrique", "L'Asie"}),
            Map.entry("Quel est le plus haut sommet des Alpes ?",
                    new String[] {"Le mont Blanc", "Le Puy de Dôme", "Le mont Ventoux"}),
            Map.entry("Quel pays se trouve au sud de la France ?",
                    new String[] {"L'Espagne", "L'Allemagne", "La Belgique"}),
            Map.entry("Quel océan borde la France à l'ouest ?",
                    new String[] {"L'océan Atlantique", "L'océan Pacifique", "L'océan Indien"}));

    @Test
    void questionnerLeMondeProducesConformingItems() {
        assertConforming(
                new QuestionnerLeMondeCpGenerator(new ContentDescriptor("Questionner le monde", "CP", "vivant")),
                QUESTIONNER_LE_MONDE);
    }

    @Test
    void sciencesProducesConformingItems() {
        assertConforming(
                new SciencesCm1Generator(new ContentDescriptor("Sciences et technologie", "CM1", "sciences")),
                SCIENCES);
    }

    @Test
    void histoireProducesConformingItems() {
        assertConforming(
                new HistoireCm1Generator(new ContentDescriptor("Histoire", "CM1", "reperes")),
                HISTOIRE);
    }

    @Test
    void geographieProducesConformingItems() {
        assertConforming(
                new GeographieCm1Generator(new ContentDescriptor("Géographie", "CM1", "espace")),
                GEOGRAPHIE);
    }

    private void assertConforming(QuizGenerator generator, Map<String, String[]> reference) {
        RandomSource random = new SeededRandomSource(42L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // reelSymbols VIDE (pas de rouleaux) ; la question est portee par la consigne.
            assertThat(item.reelSymbols()).isEmpty();
            String question = item.question();
            assertThat(question).as("question connue").isIn(reference.keySet());

            String[] expected = reference.get(question);
            String bonne = expected[0];
            assertThat(item.expected().value()).isEqualTo(bonne);

            // Distracteurs = exactement les mauvaises reponses attendues, distinctes et
            // sans la bonne reponse.
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(expected.length - 1);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(bonne);
            for (int d = 1; d < expected.length; d++) {
                assertThat(distractors).contains(expected[d]);
            }
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        GeographieCm1Generator generator =
                new GeographieCm1Generator(new ContentDescriptor("Géographie", "CM1", "espace"));
        RandomSource random = new SeededRandomSource(3L);

        Set<String> mastered = new LinkedHashSet<>();
        int pool = GEOGRAPHIE.size();
        for (int i = 0; i < pool; i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un item doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).as("aucun item reussi ne revient").doesNotContain(key);
            mastered.add(key);
        }
        assertThat(mastered).hasSize(pool);
        // Vivier epuise : plus rien a tirer.
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesTheVerifiedList() {
        assertThat(new QuestionnerLeMondeCpGenerator(desc()).distinctPoolSize())
                .hasValue(QUESTIONNER_LE_MONDE.size());
        assertThat(new SciencesCm1Generator(desc()).distinctPoolSize()).hasValue(SCIENCES.size());
        assertThat(new HistoireCm1Generator(desc()).distinctPoolSize()).hasValue(HISTOIRE.size());
        assertThat(new GeographieCm1Generator(desc()).distinctPoolSize()).hasValue(GEOGRAPHIE.size());
    }

    private static ContentDescriptor desc() {
        return new ContentDescriptor("Questionner le monde", "CP", "quiz");
    }
}
