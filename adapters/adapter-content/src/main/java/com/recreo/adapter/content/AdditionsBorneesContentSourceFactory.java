package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.source.AdditionsBorneesContentSource;

import java.util.Map;

/**
 * ADR-0006, ADR-0007 : paquet combinatoire, type 'additions-bornees'. Les bornes vivent sous
 * 'parametres' dans le paquet YAML et ne sont lues que par cette fabrique. DEC-0005, S3-3 : le
 * texte de l'indice est un parametre du meme genre, jamais une constante du domaine.
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
        String indice = texte(parametres, "indice");
        return new AdditionsBorneesContentSource(descripteur, sommeMaximale, nombreDePropositions, indice);
    }

    private int entier(Map<?, ?> parametres, String champ) {
        Object valeur = parametres.get(champ);
        if (!(valeur instanceof Integer entier)) {
            throw new IllegalStateException(
                    "champ obligatoire manquant ou non entier : 'parametres." + champ + "'");
        }
        return entier;
    }

    private String texte(Map<?, ?> parametres, String champ) {
        Object valeur = parametres.get(champ);
        if (!(valeur instanceof String texte)) {
            throw new IllegalStateException(
                    "champ obligatoire manquant ou non texte : 'parametres." + champ + "'");
        }
        return texte;
    }
}
