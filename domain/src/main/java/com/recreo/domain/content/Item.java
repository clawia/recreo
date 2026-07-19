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
}
