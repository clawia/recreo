package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur de contenu "quiz-aventure" a LISTE FINIE (mecanique
 * {@code QuizAventureMechanic}). Chaque item est une question a choix multiple :
 * {@code question} = la question posee, {@code reelSymbols} = liste VIDE (pas de
 * rouleaux), {@code expected} = la bonne reponse, {@code distractors} = les mauvaises
 * reponses. L'ordre des options est melange par la mecanique (le moteur de session),
 * pas ici.
 *
 * <p>Base commune des quatre jeux "quiz" finis : chaque jeu est une sous-classe qui
 * fournit sa LISTE VERIFIEE de {@link Question} (contenu pedagogique du domaine), la
 * logique de tirage/exclusion restant mutualisee ici. Utilisable aussi directement
 * (constructeur public) pour les tests. Place dans {@code content.impl}, hors de
 * portee des mecaniques (ArchUnit 4).</p>
 *
 * <p>En mode session, un item deja reussi est exclu du tirage suivant via sa cle
 * (derivee de la question, cf. {@link Item#keyOf}) ; quand tout le vivier est exclu,
 * {@link #next(RandomSource, Set)} renvoie {@link Optional#empty()} (epuisement).</p>
 */
public class QuizGenerator implements ContentGenerator {

    /** Une question de quiz verifiee : l'enonce, la bonne reponse, les mauvaises reponses. */
    public record Question(String question, String bonne, List<String> distracteurs) {
        public Question {
            distracteurs = List.copyOf(distracteurs);
        }
    }

    private final ContentDescriptor descriptor;
    private final List<Question> pool;

    public QuizGenerator(ContentDescriptor descriptor, List<Question> pool) {
        this.descriptor = descriptor;
        this.pool = List.copyOf(pool);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Cle d'un item = celle derivee du contenu (reelSymbols VIDE + la question).
        List<Question> available = pool.stream()
                .filter(q -> !excludeKeys.contains(Item.keyOf(List.of(), q.question())))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier epuise
        }

        Question question = available.get(random.nextInt(available.size()));

        Answer expected = new Answer(question.bonne());
        List<Answer> distractors = question.distracteurs().stream().map(Answer::new).toList();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("question", question.question());
        meta.put("bon", question.bonne());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        // reelSymbols VIDE : la mecanique "quiz-aventure" n'affiche pas de rouleaux ;
        // la question est portee par la consigne (question).
        return Optional.of(new Item(
                new ItemId(UUID.randomUUID().toString()), List.of(), question.question(), expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(pool.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
