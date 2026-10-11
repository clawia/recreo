package com.recreo.adapter.web;

import java.util.Optional;

/**
 * Les deux presentations de l'accueil (ADR-0105, decision 2) : {@link #V1}, a deux etages (les matieres
 * d'un niveau, puis les jeux d'une matiere), et {@link #V2}, une vue unique groupee par matiere.
 * Une presentation de l'adaptateur, pas une donnee de contenu : l'enumeration vit ici, et ADR-0006 ne
 * s'y applique pas.
 *
 * <p>La vue par defaut est {@link #V2} (decision de l'utilisatrice). Le choix vit dans la session HTTP,
 * par le seul porteur ({@link EtatDePartieEnSession}), et disparait avec elle (DEC-0028).</p>
 */
enum VueDeLAccueil {
    V1,
    V2;

    /** La vue d'une session qui n'en a pas choisi. */
    static final VueDeLAccueil PAR_DEFAUT = V2;

    /**
     * La vue qu'une route porte dans son adresse ({@code POST /vue/{vue}}) : la valeur visee, jamais "l'autre
     * vue" calculee depuis l'etat courant. Vide pour tout segment qui n'est pas une vue : l'appelant refuse la
     * requete, il ne choisit pas une vue a la place de l'enfant.
     */
    static Optional<VueDeLAccueil> depuis(String segment) {
        for (VueDeLAccueil vue : values()) {
            if (vue.name().equalsIgnoreCase(segment)) {
                return Optional.of(vue);
            }
        }
        return Optional.empty();
    }
}
