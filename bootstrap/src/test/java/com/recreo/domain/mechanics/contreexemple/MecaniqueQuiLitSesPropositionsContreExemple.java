package com.recreo.domain.mechanics.contreexemple;

import java.util.regex.Pattern;

/**
 * ADR-0003 : viole la clause 13 d'ADR-0104 sous ses deux formes. La premiere est la tentation exacte
 * de cet ADR - une session qui distingue une proposition-image d'une proposition-texte par son
 * prefixe, pour la reveler ou la compter autrement. La seconde est le meme geste par une expression
 * reguliere.
 *
 * <p>Existe pour etre refusee par
 * {@code DecouplageMecaniqueContenuTest.uneMecaniqueAPropositionsNeLitJamaisCeQuUnePropositionEcrit}.</p>
 */
public class MecaniqueQuiLitSesPropositionsContreExemple {

    private static final Pattern IDENTIFIANT_D_OEUVRE = Pattern.compile("oeuvre-[a-z0-9-]+");

    public boolean estUneOeuvre(String proposition) {
        return proposition.startsWith("oeuvre-");
    }

    public boolean estUneOeuvreParExpression(String proposition) {
        return IDENTIFIANT_D_OEUVRE.matcher(proposition).matches();
    }
}
