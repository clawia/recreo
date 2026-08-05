package com.recreo.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * ADR-0011 : toute exception non geree qui atteint la frontiere HTTP de ce module est rendue en
 * fragment HTML francais, jamais en corps technique brut. Sans cette porte, PartieController
 * laisse IllegalStateException (session perdue) et NoSuchElementException (jeu inconnu) remonter
 * jusqu'a la page d'erreur par defaut de Spring Boot, qui rend du JSON pour une requete htmx
 * (motif de refus 3, squad/conformite/FICHE-bandit-manchot-additions-cp.md). Le statut 200 n'est
 * pas cosmetique : htmx ne substitue le fragment dans la zone de jeu que sur une reponse de succes.
 */
@ControllerAdvice
public class GestionnaireDesErreurs {

    @ExceptionHandler({IllegalStateException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.OK)
    public String partieIntrouvable() {
        return "fragments/erreur :: erreur";
    }
}
