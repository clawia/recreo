package com.recreo.adapter.web.contreexemple;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ADR-0019 : la route neuve qui refait a la main le cycle lire-calculer-ecrire sur la session HTTP.
 * Elle n'obtient meme pas la session par un parametre de methode - elle la tire de la requete, la
 * forme la plus discrete - et c'est bien celle-la que la regle doit refuser. Existe pour etre
 * refusee par AccesALEtatDeSessionTest.unSeulTypeToucheLaSessionHttp.
 */
@Controller
public final class ControleurQuiToucheLaSessionContreExemple {

    private static final String ATTRIBUT = "recreo.compteur";

    @PostMapping("/contre-exemple/compteur")
    public String compter(HttpServletRequest requete) {
        Integer compteur = (Integer) requete.getSession().getAttribute(ATTRIBUT);
        requete.getSession().setAttribute(ATTRIBUT, compteur == null ? 1 : compteur + 1);
        return "fragments/erreur :: erreur";
    }
}
