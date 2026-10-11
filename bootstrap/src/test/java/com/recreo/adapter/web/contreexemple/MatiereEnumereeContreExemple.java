package com.recreo.adapter.web.contreexemple;

/**
 * ADR-0003, contre-exemple d'ADR-0106. L'ordre d'affichage des matieres ecrit en code, dans
 * l'adaptateur de rendu : {@code Comparator.comparing(MatiereEnumereeContreExemple::ordinal)} trie les
 * groupes en une ligne, et une matiere neuve devient une modification de code (ADR-0006). Les noms sont
 * ceux qu'on ecrirait sans y penser : le segment, le nom de programme replie.
 */
public enum MatiereEnumereeContreExemple {
    FRANCAIS,
    MATHEMATIQUES,
    QUESTIONNER_LE_MONDE
}
