package com.recreo.adapter.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Un navigateur qui poste un formulaire dans un jeu de caracteres hors UTF-8 (windows-1252) remplace
 * tout caractere que ce jeu ne sait pas encoder par sa reference numerique decimale, "&amp;#NNNN;"
 * (Encoding Standard, section Encoders, mode d'erreur "html" : "&amp;", "#", la plus courte suite
 * de chiffres 0 a 9 de la valeur du point de code en base dix, ";"). Le standard ne prevoit pas la
 * forme hexadecimale, qui reste donc inchangee ici.
 *
 * <p>Seule la valeur de proposition postee passe par ce decodage, avant sa comparaison avec la
 * bonne reponse. Les entites nommees ne sont pas decodees. Une reference dont la valeur n'est pas
 * un point de code Unicode valide (zero, substitut, au-dela de U+10FFFF) reste inchangee. Le
 * decodage est fait en un seul passage : le resultat n'est jamais redecode.</p>
 */
final class ReferencesNumeriquesDecimales {

    private static final Pattern REFERENCE = Pattern.compile("&#([0-9]{1,7});");

    private ReferencesNumeriquesDecimales() {
    }

    static String decoder(String valeur) {
        return REFERENCE.matcher(valeur).replaceAll(correspondance -> {
            int pointDeCode = Integer.parseInt(correspondance.group(1));
            boolean valide = pointDeCode > 0
                    && Character.isValidCodePoint(pointDeCode)
                    && !(pointDeCode >= Character.MIN_SURROGATE && pointDeCode <= Character.MAX_SURROGATE);
            return Matcher.quoteReplacement(valide
                    ? new String(Character.toChars(pointDeCode))
                    : correspondance.group());
        });
    }
}
