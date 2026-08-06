package com.recreo.adapter.web.contreexemple;

import com.recreo.adapter.web.PartieIndisponible;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ADR-0018 : nomme un type de recreo, rend bien une vue, et ne journalise rien. C'est la forme
 * exacte du gestionnaire mesure sur le jar de 099e750 - correct sur tous les autres criteres, muet
 * sur celui-la. Existe pour etre refuse par
 * GestionDesErreursTest.toutGestionnaireDErreurJournalise.
 */
@ControllerAdvice
public final class GestionnaireMuetContreExemple {

    @ExceptionHandler(PartieIndisponible.class)
    public String erreur() {
        return "fragments/erreur :: erreur";
    }
}
