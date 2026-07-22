package com.recreo.domain.content;

import java.util.List;
import java.util.Map;

/**
 * Une unite de contenu jouable : les symboles a afficher sur les rouleaux, la
 * consigne (question), la reponse attendue, des distracteurs et des meta-donnees
 * libres (operation, operandes, mot, ...).
 *
 * <p>{@code reelSymbols} et {@code question} sont fournis EXPLICITEMENT par le
 * contenu (cf. ADR-0004) : le front n'interprete plus la structure du contenu, il
 * itere sur {@code reelSymbols} et affiche {@code question}. Ces symboles
 * d'affichage sont distincts des rouleaux de reponses construits par la mecanique
 * a partir de {@code expected}/{@code distractors}.</p>
 */
public record Item(ItemId id, List<String> reelSymbols, String question, Answer expected,
                   List<Answer> distractors, Map<String, String> meta) {
    public Item {
        reelSymbols = List.copyOf(reelSymbols);
        distractors = List.copyOf(distractors);
        meta = Map.copyOf(meta);
    }

    /** Separateur d'unite (U+241F), absent des contenus : joint les symboles sans ambiguite. */
    private static final String KEY_SEPARATOR = "␟";

    /** Separateur (U+241D) entre les symboles et la question, absent des contenus. */
    private static final String KEY_QUESTION_SEPARATOR = "␝";

    /**
     * Cle de contenu deterministe derivee des {@code reelSymbols} ET de la
     * {@code question} (cf. ADR-0006/0007). Deux items affichant les memes symboles
     * ET la meme consigne ont la meme cle : c'est l'identite "item deja vu" utilisee
     * par la mecanique pour ne pas reproposer un item reussi, SANS que la mecanique
     * n'interprete le contenu (la cle reste opaque pour elle).
     *
     * <p>Deriver la cle des DEUX champs est necessaire au support multi-mecaniques :
     * la mecanique "texte a trous" n'utilise pas {@code reelSymbols} (liste vide), sa
     * consigne (la phrase a trou) porte l'identite de l'item. Sans la question, tous
     * ses items partageraient la meme cle (vide) et l'anti-doublon serait casse.</p>
     */
    public String key() {
        return keyOf(reelSymbols, question);
    }

    /**
     * Cle deterministe pour un contenu donne, SANS construire l'{@link Item}. Source
     * unique de la formule de cle, utilisee par les generateurs a liste finie pour
     * filtrer leur vivier de la MEME facon que {@link #key()}.
     */
    public static String keyOf(List<String> reelSymbols, String question) {
        return String.join(KEY_SEPARATOR, reelSymbols) + KEY_QUESTION_SEPARATOR + question;
    }

    /**
     * Encodage canonique d'un arrangement ordonne (mecanique glisser-deposer, cf.
     * ADR-0013). SOURCE UNIQUE de la formule, utilisee par le generateur (pour produire
     * {@code expected}) ET par le moteur (pour comparer l'ordre soumis). Le separateur
     * ", " est lisible car il apparait dans le feedback ("le bon ordre etait ...").
     */
    public static String encodeArrangement(List<String> parts) {
        return String.join(", ", parts);
    }

    /**
     * Encodage canonique d'un ensemble d'associations {@code cle -> valeur} (mecaniques
     * associer/classer et memory, cf. ADR-0016). SOURCE UNIQUE de la formule, utilisee par
     * le generateur (pour produire {@code expected}) ET par le moteur (pour comparer les
     * associations soumises). Les paires sont TRIEES par cle : l'ordre dans lequel l'enfant
     * range ses elements n'a pas d'importance, seule compte l'association elle-meme. Le
     * separateur ", " est lisible (coherent avec {@link #encodeArrangement(List)}).
     */
    public static String encodeAssociations(Map<String, String> associations) {
        return associations.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + "=" + e.getValue())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
