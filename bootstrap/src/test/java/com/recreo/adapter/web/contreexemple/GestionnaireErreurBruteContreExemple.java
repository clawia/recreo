package com.recreo.adapter.web.contreexemple;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ADR-0011 : viole la regle qui interdit a un gestionnaire d'erreur de rendre un corps technique
 * brut. Existe pour etre refusee par GestionDesErreursTest.uneErreurNeRendPasUnCorpsBrut.
 *
 * <p>ADR-0021 : plus de {@code @RestControllerAdvice} sur la classe. Le stereotype en faisait un
 * vrai bean de l'application dans tout @SpringBootTest complet, ou il interceptait
 * IllegalStateException avant GestionnaireDesErreurs et rendait un 500 a corps brut. La regle qui
 * refuse ce contre-exemple porte sur la methode annotee @ExceptionHandler et sur son type de
 * retour, jamais sur l'annotation de classe : elle mord exactement comme avant.</p>
 */
public final class GestionnaireErreurBruteContreExemple {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> erreur() {
        return ResponseEntity.internalServerError().body("erreur brute");
    }
}
