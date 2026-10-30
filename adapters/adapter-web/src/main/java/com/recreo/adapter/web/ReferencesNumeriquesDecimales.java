package com.recreo.adapter.web;

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

    /** Au plus sept chiffres : U+10FFFF s'ecrit en sept chiffres, au-dela la valeur est hors plage. */
    private static final int CHIFFRES_MAX = 7;

    private ReferencesNumeriquesDecimales() {
    }

    static String decoder(String valeur) {
        StringBuilder decodee = new StringBuilder(valeur.length());
        int i = 0;
        while (i < valeur.length()) {
            int fin = finDeReference(valeur, i);
            int pointDeCode = fin < 0 ? -1 : Integer.parseInt(valeur.substring(i + 2, fin));
            if (estUnPointDeCodeValide(pointDeCode)) {
                decodee.appendCodePoint(pointDeCode);
                i = fin + 1;
            } else {
                decodee.append(valeur.charAt(i));
                i++;
            }
        }
        return decodee.toString();
    }

    /** Indice du ";" qui ferme une reference "&#" suivie de un a sept chiffres, ou -1 si ce n'en est pas une. */
    private static int finDeReference(String valeur, int debut) {
        if (!valeur.startsWith("&#", debut)) {
            return -1;
        }
        int fin = debut + 2;
        while (fin < valeur.length() && valeur.charAt(fin) >= '0' && valeur.charAt(fin) <= '9') {
            fin++;
        }
        int chiffres = fin - (debut + 2);
        return chiffres >= 1 && chiffres <= CHIFFRES_MAX && fin < valeur.length() && valeur.charAt(fin) == ';'
                ? fin
                : -1;
    }

    private static boolean estUnPointDeCodeValide(int pointDeCode) {
        return pointDeCode > 0
                && Character.isValidCodePoint(pointDeCode)
                && !(pointDeCode >= Character.MIN_SURROGATE && pointDeCode <= Character.MAX_SURROGATE);
    }
}
