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
 *
 * <p>ADR-0075 (`DEC-0068`, la voix est celle d'un adulte present) : {@code texteAVoixHaute} porte
 * le texte que l'adulte lit a voix haute a l'enfant, distinct de tout ce que {@code enonce} et les
 * propositions donnent a lire. Sixieme composante optionnelle, meme patron que les cinq
 * precedentes : la mecanique le transporte sans jamais le lire, un paquet qui ne s'appuie pas sur
 * une voix ne remplit aucun champ factice, et la facon dont l'enfant repond ne change pas (critere
 * d'ADR-0058) - c'est un champ d'item, pas une mecanique de plus. Il nomme ce que le texte
 * <b>est</b>, jamais qui le prononce : l'adulte est une premisse de produit (`CLAUDE.md`,
 * `DEC-0046`) qui peut changer de forme sans que ce champ ne change de nom (meme geste que
 * {@code partDesignee}, ADR-0062). {@code VOI-10} (nadia-prof, 2026-09-01) : ce champ ne porte
 * jamais une proposition de son item, ni la bonne reponse - une regle de fermeture, pas une liste
 * d'interdits. ADR-0075, decision 3 : il entre desormais dans l'identite d'ecran d'ADR-0032, parce
 * que l'identite d'un tour est ce qui le distingue pour l'enfant, vu <b>ou entendu</b>.</p>
 *
 * <p>ADR-0099 : {@code tableauSupport} porte le document en <b>grille</b> qu'un item donne a lire,
 * quand la competence visee est d'en extraire une information. Septieme composante optionnelle,
 * meme patron que les six precedentes : la mecanique la transporte sans jamais la lire, et un
 * paquet qui ne montre aucune grille ne remplit aucun champ factice. Elle ne se substitue pas a
 * {@code texteSupport} - un item peut porter l'un, l'autre, ou les deux - parce que ce ne sont pas
 * deux ecritures du meme objet : un paragraphe n'a ni colonnes ni intitules, et
 * {@link TableauSupport} dit pourquoi une grille ecrite en texte n'est pas une grille. C'est la
 * seule composante de ce record qui ne soit pas une chaine, et le motif est celui d'ADR-0062 : la
 * structure est declaree par le contenu, jamais devinee par le gabarit.</p>
 */
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                        Optional<String> indice, Optional<String> explication,
                        Optional<String> partDesignee, Optional<String> texteSupport,
                        Optional<String> illustration,
                        Optional<String> illustrationTexteAlternatif,
                        Optional<String> texteAVoixHaute,
                        Optional<TableauSupport> tableauSupport) implements Item {

    /**
     * ADR-0073 clause 5 : les deux cles de l'illustration se gardent l'une l'autre. Une image qui
     * porte la question de la manche ne parvient pas a un lecteur d'ecran sans son texte
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

    /**
     * ADR-0073 : meme defaut d'ADR-0016 qu'au-dessus, assume pour le meme motif - ne pas rouvrir les
     * sites de construction qui declarent une illustration mais aucune voix. Laisse
     * {@code texteAVoixHaute} vide.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice, Optional<String> explication,
                     Optional<String> partDesignee, Optional<String> texteSupport,
                     Optional<String> illustration, Optional<String> illustrationTexteAlternatif) {
        this(id, enonce, bonneReponse, distracteurs, indice, explication, partDesignee, texteSupport,
                illustration, illustrationTexteAlternatif, Optional.empty());
    }

    /**
     * ADR-0099 : meme defaut d'ADR-0016 qu'au-dessus, assume pour le meme motif - ne pas rouvrir les
     * sites de construction des paquets qui ne montrent aucune grille. Laisse
     * {@code tableauSupport} vide.
     */
    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                     Optional<String> indice, Optional<String> explication,
                     Optional<String> partDesignee, Optional<String> texteSupport,
                     Optional<String> illustration, Optional<String> illustrationTexteAlternatif,
                     Optional<String> texteAVoixHaute) {
        this(id, enonce, bonneReponse, distracteurs, indice, explication, partDesignee, texteSupport,
                illustration, illustrationTexteAlternatif, texteAVoixHaute, Optional.empty());
    }
}
