package com.recreo.domain.content;

import java.util.List;
import java.util.Optional;

/**
 * ADR-0006 : que du texte. Aucune borne, aucun reglage de matiere.
 * ADR-0012 / S1-5 : l'indice est optionnel et vient du contenu, jamais de la mecanique, qui se
 * contente de decider quand l'afficher.
 *
 * <p>ADR-0024 : l'explication est du meme genre que l'indice - un texte que seul le contenu ecrit -
 * mais elle s'affiche a un autre moment, sur l'ecran de retour, apres chaque reponse a cet item,
 * juste ou fausse. La mecanique la transporte sans jamais la lire : elle ne decide pas quels items
 * en meritent une, c'est le contenu qui en porte une ou n'en porte pas. Rien ici ne nomme une
 * matiere, et la raison pour laquelle ce champ ne s'appelle pas d'apres le fait de francais qui l'a
 * motive est ecrite dans ADR-0024 : ce record est traverse par toutes les matieres, et un champ de
 * francais y obligerait chaque paquet de mathematiques a porter un mot qui ne le concerne pas.</p>
 */
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                        Optional<String> indice, Optional<String> explication) implements Item {

    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs) {
        this(id, enonce, bonneReponse, distracteurs, Optional.empty());
    }

    /**
     * ADR-0024 : ce constructeur de commodite laisse l'explication vide, exactement comme celui a
     * quatre arguments laisse l'indice vide - et c'est par la que le defaut d'ADR-0016 etait entre.
     * Il est conserve parce que les cinquante-six sites de construction du depot ne decident pas tous
     * d'une explication, et il n'est pas dangereux ici pour une raison verifiable : un paquet qui
     * devait porter une explication et n'en porte pas fait rougir
     * {@code ChaquePaquetExpliqueSesItemsAVariationTest}, qui tire ses items par la chaine reelle.
     * Le silence de ce constructeur est donc rattrape par un garde-fou, pas par la vigilance.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice) {
        this(id, enonce, bonneReponse, distracteurs, indice, Optional.empty());
    }
}
