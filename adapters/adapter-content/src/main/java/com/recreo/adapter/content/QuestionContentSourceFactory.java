package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.source.ListContentSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ADR-0007 : paquet enumere, type 'question'. Les items sont ecrits a la main dans le YAML sous
 * 'items' ; l'identifiant de chaque item est derive de sa position, le schema n'en porte pas.
 *
 * <p>ADR-0016 : chaque item porte son propre indice, sous la cle 'indice'. Cette fabrique
 * construisait jusqu'ici ses items avec le constructeur a quatre arguments, celui qui laisse
 * l'indice vide : un paquet 'question' ne pouvait donc porter aucune aide, quoi qu'un auteur ecrive
 * dans son YAML, alors que la mecanique bandit manchot en promet une depuis S3-2. Le champ est lu
 * ici ; qu'il soit present sur tous les items est garde par
 * {@code ChaquePaquetPorteUnIndiceTest}.</p>
 */
final class QuestionContentSourceFactory implements ContentSourceFactory {

    @Override
    public ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps) {
        Object brut = corps.get("items");
        if (!(brut instanceof List<?> items) || items.isEmpty()) {
            throw new IllegalStateException("champ obligatoire manquant ou vide : 'items'");
        }
        List<Question> questions = new ArrayList<>();
        int position = 0;
        for (Object itemBrut : items) {
            questions.add(question(itemBrut, position));
            position++;
        }
        return new ListContentSource(descripteur, questions);
    }

    private Question question(Object itemBrut, int position) {
        if (!(itemBrut instanceof Map<?, ?> item)) {
            throw new IllegalStateException("items[" + position + "] : doit etre une correspondance (map)");
        }
        String enonce = texte(item, "enonce", position);
        String bonneReponse = texte(item, "bonneReponse", position);
        Object distracteursBrut = item.get("distracteurs");
        if (!(distracteursBrut instanceof List<?> distracteurs)) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant 'distracteurs'");
        }
        List<String> distracteursTexte = distracteurs.stream().map(String::valueOf).toList();
        Object indice = item.get("indice");
        return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteursTexte,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)));
    }

    private String texte(Map<?, ?> item, String champ, int position) {
        Object valeur = item.get(champ);
        if (valeur == null) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant '" + champ + "'");
        }
        return String.valueOf(valeur);
    }
}
