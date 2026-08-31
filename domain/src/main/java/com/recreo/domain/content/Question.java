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
 *
 * <p>ADR-0062 : {@code partDesignee} declare la sous-chaine de {@code enonce} qu'une mecanique de
 * rendu doit distinguer visuellement, quand le contenu en a une a designer. {@code enonce} ne
 * change ni de type ni de valeur : il reste la phrase entiere, et {@code partDesignee} n'est jamais
 * deduite d'une convention de ponctuation (guillemets) - c'est le contenu qui declare, jamais le
 * gabarit qui devine. Facultative veut dire "l'item n'a rien designe", pas "il manque une valeur" :
 * un paquet qui ne designe rien ne remplit aucun champ factice, sur le modele deja ratifie pour
 * {@link #explication()} (ADR-0024). Le nom ne privilegie aucune matiere : une mecanique qui
 * designe une portion d'un enonce peut un jour designer autre chose qu'un mot.</p>
 *
 * <p>ADR-0050 : {@code texteSupport} porte un texte distinct de {@code enonce} - le support qu'une
 * question de comprehension lit avant de repondre (S17-2) - rendu par le gabarit dans un style
 * propre, plus petit que celui de la question. Meme genre que {@code indice} et {@code explication} :
 * un champ de contenu optionnel, jamais lu par la mecanique, jamais rempli d'une valeur factice par
 * un paquet qui n'en porte pas.</p>
 *
 * <p>ADR-0073 : {@code illustration} declare, par un <b>identifiant</b> et jamais par un chemin,
 * l'image fixe que l'item montre ; {@code illustrationTexteAlternatif} declare ce qu'une lecture
 * d'ecran en annonce. Quatrieme et cinquieme composantes optionnelles de ce record, sur le patron
 * exact des trois precedentes : la mecanique les transporte sans jamais les lire, et un paquet qui
 * n'illustre rien ne remplit aucun champ factice. La facon dont l'enfant repond ne change pas -
 * c'est le critere d'ADR-0058, et c'est pourquoi le palier 1 du visuel n'est ni une forme d'item de
 * plus ni une mecanique de plus. Ou le fichier est servi, sous quel nom et avec quelle extension
 * n'est pas ici : le contenu declare ce qu'il montre, l'adapter web decide ou il le prend.</p>
 */
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                        Optional<String> indice, Optional<String> explication,
                        Optional<String> partDesignee, Optional<String> texteSupport,
                        Optional<String> illustration,
                        Optional<String> illustrationTexteAlternatif) implements Item {

    /**
     * ADR-0073 clause 5 : les deux cles de l'illustration se gardent l'une l'autre. Une image qui
     * porte la question de la manche ne parvient pas a une lecture d'ecran sans son texte
     * alternatif, et un texte alternatif sans image ne decrit rien. L'invariant est pose ici plutot
     * que dans la seule fabrique de contenu : il vaut pour tout site de construction, y compris ceux
     * qui n'ont jamais lu un fichier.
     */
    public Question {
        if (illustration.isPresent() != illustrationTexteAlternatif.isPresent()) {
            throw new IllegalArgumentException(
                    "ADR-0073 clause 5 : une illustration et son texte alternatif se declarent"
                            + " ensemble ou pas du tout - item " + id.value());
        }
    }

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

    /**
     * ADR-0062 : meme defaut d'ADR-0016 qu'au-dessus, assume pour le meme motif - ne pas rouvrir les
     * sites de construction des paquets qui ne designent rien. Laisse {@code partDesignee} vide.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice, Optional<String> explication) {
        this(id, enonce, bonneReponse, distracteurs, indice, explication, Optional.empty());
    }

    /**
     * ADR-0050 : meme defaut d'ADR-0016 qu'au-dessus, assume pour le meme motif - ne pas rouvrir les
     * sites de construction des paquets qui ne portent pas de texte-support. Laisse
     * {@code texteSupport} vide.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice, Optional<String> explication,
                     Optional<String> partDesignee) {
        this(id, enonce, bonneReponse, distracteurs, indice, explication, partDesignee, Optional.empty());
    }

    /**
     * ADR-0073 : meme defaut d'ADR-0016 qu'au-dessus, assume pour le meme motif - ne pas rouvrir les
     * sites de construction des paquets qui ne montrent aucune image. Laisse les deux composantes
     * de l'illustration vides, donc ensemble, donc conformes a la clause 5.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice, Optional<String> explication,
                     Optional<String> partDesignee, Optional<String> texteSupport) {
        this(id, enonce, bonneReponse, distracteurs, indice, explication, partDesignee, texteSupport,
                Optional.empty(), Optional.empty());
    }
}
