package com.recreo.adapter.web.contreexemple;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ADR-0011 : viole la regle qui interdit a un gestionnaire d'erreur de rendre un corps technique
 * brut. Existe pour etre refusee par GestionDesErreursTest.uneErreurNeRendPasUnCorpsBrut.
 */
@RestControllerAdvice
public final class GestionnaireErreurBruteContreExemple {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> erreur() {
        return ResponseEntity.internalServerError().body("erreur brute");
    }
}
