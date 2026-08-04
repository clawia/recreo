package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.source.AdditionsBorneesContentSource;

import java.util.Map;

/**
 * ADR-0006, ADR-0007 : paquet combinatoire, type 'additions-bornees'. Les bornes vivent sous
 * 'parametres' dans le paquet YAML et ne sont lues que par cette fabrique.
 */
final class AdditionsBorneesContentSourceFactory implements ContentSourceFactory {

    @Override
    public ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps) {
        Object brut = corps.get("parametres");
        if (!(brut instanceof Map<?, ?> parametres)) {
            throw new IllegalStateException("champ obligatoire manquant : 'parametres'");
        }
        int sommeMaximale = entier(parametres, "sommeMaximale");
        int nombreDePropositions = entier(parametres, "nombreDePropositions");
        return new AdditionsBorneesContentSource(descripteur, sommeMaximale, nombreDePropositions);
    }

    private int entier(Map<?, ?> parametres, String champ) {
        Object valeur = parametres.get(champ);
        if (!(valeur instanceof Integer entier)) {
            throw new IllegalStateException(
                    "champ obligatoire manquant ou non entier : 'parametres." + champ + "'");
        }
        return entier;
    }
}
