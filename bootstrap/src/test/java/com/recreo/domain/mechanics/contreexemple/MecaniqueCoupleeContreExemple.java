package com.recreo.domain.mechanics.contreexemple;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.source.ListContentSource;

/**
 * ADR-0003 : viole ADR-0004 (3) en dependant d'une implementation de tirage, et ADR-0004 (4) en
 * lisant le descripteur. Existe pour etre refusee par uneMecaniqueNeVoitQueLesContrats et par
 * uneMecaniqueIgnoreLaMatiere.
 */
public class MecaniqueCoupleeContreExemple {

    private final ListContentSource contenu;

    public MecaniqueCoupleeContreExemple(ListContentSource contenu) {
        this.contenu = contenu;
    }

    public ContentDescriptor descripteurLu() {
        return contenu.descriptor();
    }

    public String matiere() {
        return descripteurLu().matiere();
    }
}
