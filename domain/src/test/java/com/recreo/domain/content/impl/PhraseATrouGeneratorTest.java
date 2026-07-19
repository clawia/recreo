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
 * Tests deterministes des 3 jeux "texte a trous" a liste finie (conjugaison CM1,
 * accords CM1, mot manquant CP). Verifie que chaque generateur produit des items
 * conformes : {@code question} contient le trou {@code ___}, {@code reelSymbols} est
 * VIDE, la bonne reponse est celle de la liste verifiee, les distracteurs sont ceux
 * attendus (distincts, sans la bonne reponse). Verifie aussi le mode session
 * (anti-doublon + epuisement) commun a la mecanique "texte a trous".
 */
class PhraseATrouGeneratorTest {

    private static final int SAMPLES = 400;

    // Reference verifiee : phrase -> [bon, distracteur, distracteur, ...].
    private static final Map<String, String[]> CONJUGAISON = Map.ofEntries(
            Map.entry("Tous les jours, il ___ à l'école.", new String[] {"va", "vais", "vont", "allez"}),
            Map.entry("Nous ___ une histoire.", new String[] {"lisons", "lit", "lisent", "lis"}),
            Map.entry("Vous ___ du chocolat.", new String[] {"mangez", "mange", "mangeons", "mangent"}),
            Map.entry("Je ___ mes devoirs.", new String[] {"fais", "fait", "faisons", "font"}),
            Map.entry("Elles ___ dans le jardin.", new String[] {"jouent", "joue", "jouons", "jouez"}),
            Map.entry("Tu ___ un dessin.", new String[] {"dessines", "dessine", "dessinent", "dessinez"}));

    private static final Map<String, String[]> ACCORDS = Map.ofEntries(
            Map.entry("Les fleurs sont ___.", new String[] {"jolies", "joli", "jolis", "jolie"}),
            Map.entry("Des chats ___.", new String[] {"noirs", "noir", "noire", "noires"}),
            Map.entry("La maison est ___.", new String[] {"grande", "grand", "grands", "grandes"}),
            Map.entry("Les garçons sont ___.", new String[] {"petits", "petit", "petite", "petites"}),
            Map.entry("Une pomme ___.", new String[] {"verte", "vert", "verts", "vertes"}));

    private static final Map<String, String[]> MOT_MANQUANT = Map.ofEntries(
            Map.entry("Le chat boit du ___.", new String[] {"lait", "pain", "vélo", "chien"}),
            Map.entry("Je dors dans mon ___.", new String[] {"lit", "bol", "chat", "arbre"}),
            Map.entry("Le soleil est dans le ___.", new String[] {"ciel", "sac", "pied", "livre"}),
            Map.entry("On écrit avec un ___.", new String[] {"crayon", "ballon", "gâteau", "nuage"}),
            Map.entry("Le poisson nage dans l'___.", new String[] {"eau", "air", "herbe", "feu"}));

    @Test
    void conjugaisonProducesConformingItems() {
        assertConforming(
                new ConjugaisonCm1Generator(new ContentDescriptor("Français", "CM1", "conjugaison")),
                CONJUGAISON);
    }

    @Test
    void accordsProducesConformingItems() {
        assertConforming(
                new AccordsCm1Generator(new ContentDescriptor("Français", "CM1", "accord adjectif")),
                ACCORDS);
    }

    @Test
    void motManquantProducesConformingItems() {
        assertConforming(
                new MotManquantCpGenerator(new ContentDescriptor("Français", "CP", "sens")),
                MOT_MANQUANT);
    }

    private void assertConforming(PhraseATrouGenerator generator, Map<String, String[]> reference) {
        RandomSource random = new SeededRandomSource(42L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // reelSymbols VIDE (pas de rouleaux) ; la phrase est portee par la consigne.
            assertThat(item.reelSymbols()).isEmpty();
            String phrase = item.question();
            assertThat(phrase).as("phrase connue").isIn(reference.keySet());
            // La phrase contient bien le marqueur de trou.
            assertThat(phrase).contains("___");

            String[] expected = reference.get(phrase);
            String bon = expected[0];
            assertThat(item.expected().value()).isEqualTo(bon);

            // Distracteurs = exactement les mots attendus, distincts et sans la bonne reponse.
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(expected.length - 1);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(bon);
            for (int d = 1; d < expected.length; d++) {
                assertThat(distractors).contains(expected[d]);
            }
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        AccordsCm1Generator generator =
                new AccordsCm1Generator(new ContentDescriptor("Français", "CM1", "accord adjectif"));
        RandomSource random = new SeededRandomSource(3L);

        Set<String> mastered = new LinkedHashSet<>();
        int pool = ACCORDS.size();
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
        assertThat(new ConjugaisonCm1Generator(desc()).distinctPoolSize()).hasValue(CONJUGAISON.size());
        assertThat(new AccordsCm1Generator(desc()).distinctPoolSize()).hasValue(ACCORDS.size());
        assertThat(new MotManquantCpGenerator(desc()).distinctPoolSize()).hasValue(MOT_MANQUANT.size());
    }

    private static ContentDescriptor desc() {
        return new ContentDescriptor("Français", "CM1", "texte a trous");
    }
}
