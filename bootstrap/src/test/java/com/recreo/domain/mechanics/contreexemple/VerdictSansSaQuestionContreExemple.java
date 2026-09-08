package com.recreo.domain.mechanics.contreexemple;

import java.util.Optional;

/**
 * ADR-0003 : viole la clause 12 d'ADR-0088 - un verdict de mecanique qui juge une manche sans porter
 * l'enonce de cette manche.
 *
 * <p><b>Ce n'est pas une fixture inventee, c'est un etat de l'arbre.</b> C'est mot pour mot la forme
 * qu'avait {@code DesignationVerdict} du 2026-09-04 au 2026-09-08, quatre sprints durant, sur une
 * suite verte - et c'est aussi la forme qu'avaient {@code Verdict} et {@code SaisieVerdict} avant
 * ADR-0084. Le contre-exemple garde donc la forme reelle du defaut plutot qu'une caricature de la
 * main de l'auteur de la regle.</p>
 *
 * <p>Existe pour etre refuse par
 * {@code DecouplageMecaniqueContenuTest.unVerdictDeMecaniquePorteSonEnonce}.</p>
 */
public record VerdictSansSaQuestionContreExemple(boolean juste, String bonneReponse,
                                                  Optional<String> explication) {
}
