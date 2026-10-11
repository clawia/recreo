package com.recreo.adapter.web.contreexemple;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ADR-0105 : la bascule de vue ecrite comme le chemin le moins cher l'ecrirait, en recopiant le
 * marqueur de partie de PartieController sous un autre nom et une duree d'un an. Existe pour etre
 * refusee par LeSeulCookieEstLeMarqueurDePartieTest.aucunCookieNeNaitHorsDuMarqueurDePartie.
 * Pas de {@code @Controller}, pour la raison qu'ADR-0021 donne sur les contre-exemples de route.
 */
public final class PreferenceDeVueEnCookieContreExemple {

    @PostMapping("/contre-exemple/vue/{vue}")
    public String basculer(@PathVariable String vue, HttpServletResponse reponse) {
        Cookie cookie = new Cookie("recreo-vue", vue);
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24 * 3600);
        reponse.addCookie(cookie);
        return "accueil :: zoneAccueil";
    }
}
