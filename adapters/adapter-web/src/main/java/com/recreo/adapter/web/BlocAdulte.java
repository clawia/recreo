package com.recreo.adapter.web;

/**
 * ADR-0075 section 7, VOI-12 (nadia-prof, exigence bloquante) : le stimulus que l'adulte lit a voix
 * haute et la mise en garde de son jeu forment un seul objet, jamais deux champs de modele
 * independants. Ce type vit ici, jamais dans le domaine : c'est une forme de rendu, sur le meme
 * principe que {@link EnonceSegment}.
 *
 * <p><b>Le trou que ce type ferme, trouve par yanis-qa en mutant le modele</b> (mesure sur
 * {@code fragments/manche.html} : deux {@code th:if} independants, {@code texteAVoixHaute != null}
 * sur le conteneur et {@code consigneAdulte != null} sur la mise en garde, rendaient un
 * {@code .bloc-adulte} avec un stimulus et zero mise en garde des que le second attribut manquait).
 * Seul {@code LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest}, cote contenu, empechait
 * cette combinaison - en gardant un fichier YAML, jamais le document rendu. Un etat de session, une
 * entree de catalogue future ou une route neuve pouvait produire le meme trou sans passer par ce
 * fichier.</p>
 *
 * <p><b>Pourquoi un objet plutot qu'une garde au gabarit.</b> Deux attributs de modele peuvent
 * toujours diverger au prochain point d'appel qui les pose ; un seul objet, construit une fois avec
 * un invariant, ne le peut plus - la porte est fermee au lieu d'etre surveillee (meme argument que
 * theo-archi sur les porteurs de JavaScript d'ADR-0074). {@link #creerOuNull} est le seul point de
 * construction : {@code PartieController} ne pose plus jamais {@code texteAVoixHaute} et
 * {@code consigneAdulte} comme deux attributs separes.</p>
 *
 * <p><b>Pourquoi echouer plutot que masquer en silence.</b> Les deux valeurs viennent de deux
 * sources distinctes - l'item joue, l'entree de catalogue fixee au demarrage de la partie - qui ne
 * peuvent physiquement pas se garder l'une l'autre comme le fait un invariant de record sur un seul
 * objet de contenu (ADR-0073 clause 5). Rendre {@code null} sur un depariement masquerait un defaut
 * de donnee sans laisser de trace : l'enfant ne verrait simplement jamais le stimulus promis, et
 * personne ne le remarquerait. {@link #creerOuNull} leve une {@link IllegalStateException} a la
 * place, que {@link GestionnaireDesErreurs} attrape deja (ADR-0011, ADR-0018) : le defaut part en
 * ERROR avec sa pile et l'enfant voit l'ecran d'erreur générique, jamais un bloc-adulte incomplet -
 * l'etat que VOI-12 interdit purement et simplement.</p>
 *
 * <p>Public pour que Thymeleaf/SpringEL puisse lire {@link #stimulus()} et {@link #miseEnGarde()}
 * par reflexion depuis le gabarit, sur le meme principe que {@code GameDefinition}.</p>
 */
public record BlocAdulte(String stimulus, String miseEnGarde) {

    public BlocAdulte {
        if (stimulus == null || stimulus.isBlank()) {
            throw new IllegalStateException(
                    "BlocAdulte exige un stimulus non vide - VOI-12 : jamais construit a moitie");
        }
        if (miseEnGarde == null || miseEnGarde.isBlank()) {
            throw new IllegalStateException(
                    "BlocAdulte exige une mise en garde non vide - VOI-12 : jamais construit a moitie");
        }
    }

    /**
     * Le seul point de construction. Rend {@code null} quand ni l'un ni l'autre n'est declare - le
     * cas ordinaire, jamais un cas particulier a remplir d'une valeur factice - et leve si l'un est
     * present sans l'autre : voir la Javadoc de classe pour le motif de ce choix.
     */
    static BlocAdulte creerOuNull(String stimulus, String miseEnGarde) {
        boolean stimulusDeclare = stimulus != null;
        boolean miseEnGardeDeclaree = miseEnGarde != null;
        if (!stimulusDeclare && !miseEnGardeDeclaree) {
            return null;
        }
        if (stimulusDeclare != miseEnGardeDeclaree) {
            throw new IllegalStateException("VOI-12 : le stimulus et la mise en garde de son jeu se"
                    + " declarent ensemble ou pas du tout - stimulus="
                    + (stimulusDeclare ? "present" : "absent") + ", miseEnGarde="
                    + (miseEnGardeDeclaree ? "presente" : "absente"));
        }
        return new BlocAdulte(stimulus, miseEnGarde);
    }
}
