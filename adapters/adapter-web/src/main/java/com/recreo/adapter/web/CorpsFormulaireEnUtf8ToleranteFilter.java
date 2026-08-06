package com.recreo.adapter.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * S05 : Tomcat decode les parametres d'un corps application/x-www-form-urlencoded avec un
 * decodeur UTF-8 strict, qui leve org.apache.tomcat.util.http.InvalidParameterException (une
 * IllegalStateException) sur toute sequence d'octets qui n'est pas un UTF-8 valide. Constat
 * isole : n'importe quelle proposition accentuee de cm1-homophones.yaml ("a", "Ou", "la"...)
 * peut declencher ce cas selon comment le client encode sa requete, et l'exception, une fois
 * levee, est attrapee par GestionnaireDesErreurs comme une session perdue - la manche entiere
 * plante au lieu qu'une reponse mal encodee soit simplement traitee comme une reponse fausse.
 *
 * <p>Ce filtre remplace, pour ces requetes, le decodage de Tomcat par celui de
 * RequeteFormulaireDecodeeEnUtf8, qui ne leve jamais.</p>
 */
@Component
public class CorpsFormulaireEnUtf8ToleranteFilter extends OncePerRequestFilter {

    private static final String CONTENU_FORMULAIRE = "application/x-www-form-urlencoded";

    @Override
    protected void doFilterInternal(HttpServletRequest requete, HttpServletResponse reponse, FilterChain chaine)
            throws ServletException, IOException {
        if (estUnFormulairePost(requete)) {
            chaine.doFilter(new RequeteFormulaireDecodeeEnUtf8(requete), reponse);
        } else {
            chaine.doFilter(requete, reponse);
        }
    }

    private static boolean estUnFormulairePost(HttpServletRequest requete) {
        String contenu = requete.getContentType();
        return "POST".equalsIgnoreCase(requete.getMethod())
                && contenu != null && contenu.startsWith(CONTENU_FORMULAIRE);
    }
}
