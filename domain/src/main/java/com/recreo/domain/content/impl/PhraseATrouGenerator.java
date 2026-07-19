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
 * Generateur de contenu "texte a trous" a LISTE FINIE (mecanique
 * {@code TexteATrouMechanic}). Chaque item est une phrase a completer :
 * {@code question} = la phrase contenant le marqueur {@code ___}, {@code reelSymbols}
 * = liste VIDE (pas de rouleaux), {@code expected} = le bon mot, {@code distractors}
 * = les autres mots proposes. L'ordre des options est melange par la mecanique (le
 * moteur de session), pas ici.
 *
 * <p>Base commune des trois jeux "texte a trous" finis : chaque jeu est une
 * sous-classe qui fournit sa LISTE VERIFIEE de {@link Trou} (contenu pedagogique du
 * domaine), la logique de tirage/exclusion restant mutualisee ici. Utilisable aussi
 * directement (constructeur public) pour les tests. Place dans {@code content.impl},
 * hors de portee des mecaniques (ArchUnit 4).</p>
 *
 * <p>En mode session, un item deja reussi est exclu du tirage suivant via sa cle
 * (derivee de la phrase, cf. {@link Item#keyOf}) ; quand tout le vivier est exclu,
 * {@link #next(RandomSource, Set)} renvoie {@link Optional#empty()} (epuisement).</p>
 */
public class PhraseATrouGenerator implements ContentGenerator {

    /** Une phrase a trou verifiee : le texte (avec {@code ___}), le bon mot, les distracteurs. */
    public record Trou(String phrase, String bon, List<String> distracteurs) {
        public Trou {
            distracteurs = List.copyOf(distracteurs);
        }
    }

    private final ContentDescriptor descriptor;
    private final List<Trou> pool;

    public PhraseATrouGenerator(ContentDescriptor descriptor, List<Trou> pool) {
        this.descriptor = descriptor;
        this.pool = List.copyOf(pool);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Cle d'un item = celle derivee du contenu (reelSymbols VIDE + la phrase).
        List<Trou> available = pool.stream()
                .filter(t -> !excludeKeys.contains(Item.keyOf(List.of(), t.phrase())))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier epuise
        }

        Trou trou = available.get(random.nextInt(available.size()));

        Answer expected = new Answer(trou.bon());
        List<Answer> distractors = trou.distracteurs().stream().map(Answer::new).toList();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("phrase", trou.phrase());
        meta.put("bon", trou.bon());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        // reelSymbols VIDE : la mecanique "texte a trous" n'affiche pas de rouleaux ;
        // la phrase a completer est portee par la consigne (question).
        return Optional.of(new Item(
                new ItemId(UUID.randomUUID().toString()), List.of(), trou.phrase(), expected, distractors, meta));
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
