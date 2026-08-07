package com.recreo.adapter.web.contreexemple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ADR-0018 : journalise correctement, mais attrape un type du JDK que n'importe quelle couche du
 * serveur peut lever. C'est par ce sous-arbre que
 * org.apache.tomcat.util.http.InvalidParameterException, sous-classe d'IllegalStateException, s'est
 * fait passer pour une session perdue. Existe pour etre refuse par
 * GestionDesErreursTest.unGestionnaireNommeUnTypeDeRecreoOuLaCaptureGenerale.
 *
 * <p>ADR-0021 : plus de {@code @ControllerAdvice} sur la classe, meme motif que
 * GestionnaireErreurBruteContreExemple.</p>
 */
public final class GestionnaireTypeDuJdkContreExemple {

    private static final Logger JOURNAL = LoggerFactory.getLogger(GestionnaireTypeDuJdkContreExemple.class);

    @ExceptionHandler(IllegalStateException.class)
    public String erreur(IllegalStateException cause) {
        JOURNAL.info("session perdue : {}", cause.getMessage());
        return "fragments/erreur :: erreur";
    }
}
