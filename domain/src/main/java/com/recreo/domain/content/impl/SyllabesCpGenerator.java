package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur concret de francais CP : "denombrer les syllabes d'un mot". Chaque
 * item affiche UN rouleau (le mot en majuscules), pose la question
 * "Combien de syllabes ?" et attend le nombre de syllabes orales.
 *
 * <p>Meme mecanique que le calcul mental (bandit manchot) : ce generateur n'est
 * qu'un autre {@link ContentGenerator}. Place dans {@code content.impl}, hors de
 * portee des mecaniques (garanti par ArchUnit) — cf. ADR-0004.</p>
 */
public final class SyllabesCpGenerator implements ContentGenerator {

    /** Consigne affichee a l'enfant. */
    private static final String QUESTION = "Combien de syllabes ?";

    /** Nombre de distracteurs (CP). */
    private static final int DISTRACTOR_COUNT = 2;

    /**
     * Liste verifiee mot -> nombre de syllabes orales (figee pour eviter toute
     * erreur pedagogique). Ordre d'insertion deterministe.
     */
    private static final Map<String, Integer> MOTS = buildMots();

    private final ContentDescriptor descriptor;
    private final List<Map.Entry<String, Integer>> entries;

    /** Comportement historique : aucun filtre, toute la liste de mots est jouable. */
    public SyllabesCpGenerator(ContentDescriptor descriptor) {
        this(descriptor, null);
    }

    /**
     * Generateur configurable : si {@code maxSyllabes} est non nul, seuls les mots
     * comptant au plus {@code maxSyllabes} syllabes sont tires. {@code null} =
     * aucun filtre (comportement par defaut, identique a la version historique).
     *
     * @throws IllegalArgumentException si le filtre ne laisse aucun mot jouable.
     */
    public SyllabesCpGenerator(ContentDescriptor descriptor, Integer maxSyllabes) {
        this.descriptor = descriptor;
        List<Map.Entry<String, Integer>> filtered = MOTS.entrySet().stream()
                .filter(e -> maxSyllabes == null || e.getValue() <= maxSyllabes)
                .toList();
        if (filtered.isEmpty()) {
            throw new IllegalArgumentException(
                    "Aucun mot ne respecte maxSyllabes=" + maxSyllabes);
        }
        this.entries = List.copyOf(filtered);
    }

    private static Map<String, Integer> buildMots() {
        Map<String, Integer> m = new LinkedHashMap<>();
        m.put("chat", 1);
        m.put("riz", 1);
        m.put("loup", 1);
        m.put("papa", 2);
        m.put("lapin", 2);
        m.put("bateau", 2);
        m.put("cadeau", 2);
        m.put("maison", 2);
        m.put("chocolat", 3);
        m.put("éléphant", 3);
        m.put("parapluie", 3);
        m.put("dinosaure", 3);
        m.put("ordinateur", 4);
        m.put("hélicoptère", 4);
        return m;
    }

    @Override
    public Item next(RandomSource random) {
        Map.Entry<String, Integer> entry = entries.get(random.nextInt(entries.size()));
        String mot = entry.getKey();
        int syllabes = entry.getValue();

        List<String> reelSymbols = List.of(mot.toUpperCase(Locale.FRENCH));
        Answer expected = new Answer(String.valueOf(syllabes));
        List<Answer> distractors = buildDistractors(syllabes);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("mot", mot);
        meta.put("syllabes", String.valueOf(syllabes));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /**
     * Distracteurs plausibles : nombres voisins (expected-1, expected+1, ...),
     * jamais inferieurs a 1, sans doublon et differents de la reponse. On elargit
     * vers l'exterieur tant que le compte n'est pas atteint (deterministe).
     */
    private List<Answer> buildDistractors(int expected) {
        Set<Integer> used = new LinkedHashSet<>();
        used.add(expected);
        List<Answer> distractors = new ArrayList<>();

        int delta = 1;
        while (distractors.size() < DISTRACTOR_COUNT) {
            for (int candidate : new int[] {expected - delta, expected + delta}) {
                if (candidate < 1) {
                    continue;
                }
                if (used.add(candidate)) {
                    distractors.add(new Answer(String.valueOf(candidate)));
                    if (distractors.size() == DISTRACTOR_COUNT) {
                        break;
                    }
                }
            }
            delta++;
        }
        return distractors;
    }
}
