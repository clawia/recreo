package com.recreo.adapter.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ADR-0011 : toute exception non geree qui atteint la frontiere HTTP de ce module est rendue en
 * fragment HTML francais, jamais en corps technique brut. Le statut 200 n'est pas cosmetique :
 * htmx ne substitue le fragment dans la zone de jeu que sur une reponse de succes.
 *
 * <p>ADR-0018 : l'enfant voit le meme ecran dans les deux cas, le journal les distingue. Une
 * situation prevue est nommee par un type que recreo est seul a lever et vaut une ligne INFO ; tout
 * le reste est un defaut, part en ERROR avec sa pile, et ne peut plus se faire passer pour une
 * session perdue. La version precedente attrapait {@code IllegalStateException} et
 * {@code NoSuchElementException} par leur type et ne journalisait rien : un defaut de decodage leve
 * par le conteneur, sous-classe du premier, cassait une manche sur deux jeux sans laisser une seule
 * ligne dans le journal.</p>
 */
@ControllerAdvice
public class GestionnaireDesErreurs {

    private static final Logger JOURNAL = LoggerFactory.getLogger(GestionnaireDesErreurs.class);
    private static final String VUE_ERREUR = "fragments/erreur :: erreur";

    @ExceptionHandler(PartieIndisponible.class)
    @ResponseStatus(HttpStatus.OK)
    public String partieIndisponible(PartieIndisponible cause, HttpServletRequest requete) {
        JOURNAL.info("partie indisponible sur {} {} : {}",
                requete.getMethod(), requete.getRequestURI(), cause.getMessage());
        return VUE_ERREUR;
    }

    /**
     * Volontairement large. Le danger n'a jamais ete d'attraper large : il a ete d'attraper large
     * en ayant l'air d'attraper etroit, et sans rien dire. La pile est journalisee parce que le
     * message seul ne suffit pas a situer un defaut qu'on n'attendait pas.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public String defautImprevu(RuntimeException cause, HttpServletRequest requete) {
        JOURNAL.error("defaut imprevu sur {} {}", requete.getMethod(), requete.getRequestURI(), cause);
        return VUE_ERREUR;
    }
}
