package com.recreo.domain.mechanic;

import java.text.Normalizer;
import java.util.Locale;

/**
 * ADR-0003 : viole ADR-0058. C'est {@code MoteurPartageContreExemple} sous la seule forme qui
 * traversait les deux contrats fermes d'ADR-0004 : une <b>interface</b>, donc acceptee par
 * {@code leContratDeMecaniqueEstFerme}, dont une methode <b>statique</b> porte le comportement que
 * deux mecaniques appelleraient. Ce n'est pas une faute imaginee : c'est exactement le geste le
 * moins cher pour partager la normalisation de saisie d'ADR-0058 entre la mecanique de saisie et
 * celle qui existe deja, et il a ete mesure vert sur l'arbre du 2026-08-24 avant que la regle
 * n'existe.
 *
 * <p>Existe pour etre refuse par
 * {@code DecouplageMecaniqueContenuTest.unContratNeDeclareAucunComportement}.</p>
 */
public interface NormalisationPartageeContreExemple {

    static String replier(String saisie) {
        String decompose = Normalizer.normalize(saisie, Normalizer.Form.NFD);
        StringBuilder sans = new StringBuilder(decompose.length());
        for (int position = 0; position < decompose.length(); position++) {
            char caractere = decompose.charAt(position);
            if (Character.getType(caractere) != Character.NON_SPACING_MARK) {
                sans.append(caractere);
            }
        }
        return sans.toString().toLowerCase(Locale.ROOT).strip();
    }
}
