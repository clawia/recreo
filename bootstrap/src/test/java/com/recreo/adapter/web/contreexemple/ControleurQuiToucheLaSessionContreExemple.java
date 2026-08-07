package com.recreo.adapter.web.contreexemple;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ADR-0019 : la route neuve qui refait a la main le cycle lire-calculer-ecrire sur la session HTTP.
 * Elle n'obtient meme pas la session par un parametre de methode - elle la tire de la requete, la
 * forme la plus discrete - et c'est bien celle-la que la regle doit refuser. Existe pour etre
 * refusee par AccesALEtatDeSessionTest.unSeulTypeToucheLaSessionHttp.
 *
 * <p>ADR-0021 : plus de {@code @Controller} sur la classe. Le stereotype enregistrait reellement la
 * route /contre-exemple/compteur dans tout @SpringBootTest complet - un contre-exemple qui servait
 * du HTTP. L'annotation de route reste, elle dit ce que la classe imite ; la regle qui la refuse
 * porte sur sa dependance a HttpSession, pas sur son stereotype.</p>
 */
public final class ControleurQuiToucheLaSessionContreExemple {

    private static final String ATTRIBUT = "recreo.compteur";

    @PostMapping("/contre-exemple/compteur")
    public String compter(HttpServletRequest requete) {
        Integer compteur = (Integer) requete.getSession().getAttribute(ATTRIBUT);
        requete.getSession().setAttribute(ATTRIBUT, compteur == null ? 1 : compteur + 1);
        return "fragments/erreur :: erreur";
    }
}
