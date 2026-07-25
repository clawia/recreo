package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.PhrasePuzzleGenerator.Phrase;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Charge les {@link PhraseContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/phrase/*.yml}). Chaque fichier = un jeu "reconstitue la phrase" (mecanique
 * puzzle, declinaison "mots"). Le contenu est VALIDE au chargement (demarrage) : un fichier mal
 * forme fait echouer le boot avec un message explicite, plutot que de servir un jeu casse.
 *
 * <p>Meme patron que {@link PuzzlePackLoader}. Difference : l'item porte une {@code phrase}
 * (au moins 2 mots) et un {@code indice} FACULTATIF. Ajouter/editer un jeu = editer un YAML.</p>
 */
public final class PhrasePackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu phrase. */
    public static final String DEFAULT_LOCATION = "classpath*:content/phrase/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets phrase, tries par nom de fichier (ordre deterministe). */
    public List<PhraseContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<PhraseContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<PhraseContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu phrase trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu phrase : " + locationPattern, e);
        }
    }

    public PhraseContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu phrase vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu phrase : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private PhraseContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);

        Object phrasesNode = root.get("phrases");
        if (!(phrasesNode instanceof List<?> rawPhrases) || rawPhrases.isEmpty()) {
            throw new IllegalStateException("Contenu phrase '" + where + "' : au moins une phrase est requise");
        }

        List<Phrase> phrases = new ArrayList<>();
        for (Object raw : rawPhrases) {
            if (!(raw instanceof Map<?, ?> m)) {
                throw new IllegalStateException("Contenu phrase '" + where + "' : phrase mal formée");
            }
            Map<String, Object> item = (Map<String, Object>) m;
            String phrase = requireText(item.get("phrase"), "phrase", where);
            if (phrase.trim().split("\\s+").length < 2) {
                throw new IllegalStateException(
                        "Contenu phrase '" + where + "' : la phrase « " + phrase + " » doit contenir au moins 2 mots");
            }
            String indice = text(item.get("indice")); // indice FACULTATIF pour les phrases
            phrases.add(new Phrase(phrase, indice));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new PhraseContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                consigne, optionalInt(root.get("jokers")), phrases);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu phrase '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }

    /** Champ d'en-tete optionnel {@code jokers} (capital de coeurs) : absent -> null (cf. DEC-0013/s21). */
    private static Integer optionalInt(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number n) {
            return n.intValue();
        }
        throw new IllegalStateException("champ 'jokers' doit etre un entier (recu : " + value + ")");
    }
}
