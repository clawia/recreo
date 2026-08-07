package com.recreo.adapter.web.contreexemple;

import com.recreo.adapter.web.PartieIndisponible;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ADR-0018 : nomme un type de recreo, rend bien une vue, et ne journalise rien. C'est la forme
 * exacte du gestionnaire mesure sur le jar de 099e750 - correct sur tous les autres criteres, muet
 * sur celui-la. Existe pour etre refuse par
 * GestionDesErreursTest.toutGestionnaireDErreurJournalise.
 *
 * <p>ADR-0021 : plus de {@code @ControllerAdvice} sur la classe, meme motif que
 * GestionnaireErreurBruteContreExemple - la regle porte sur la methode annotee @ExceptionHandler.</p>
 */
public final class GestionnaireMuetContreExemple {

    @ExceptionHandler(PartieIndisponible.class)
    public String erreur() {
        return "fragments/erreur :: erreur";
    }
}
