package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;

import java.util.Map;

/**
 * ADR-0007 : une fabrique par 'type' de paquet, enregistree dans le registre de
 * YamlContentPackLoader. Le corps brut du paquet (items ou parametres) est passe tel que lu par
 * SnakeYAML ; chaque fabrique valide les champs qui lui sont propres.
 */
interface ContentSourceFactory {

    ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps);
}
