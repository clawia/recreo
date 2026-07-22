package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MotPuzzleGenerator.Mot;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Charge les {@link PuzzleContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/puzzle/*.yml}). Chaque fichier = un jeu "reconstitue le mot" (mecanique
 * puzzle, cf. ADR-0017). Le contenu est VALIDE au chargement (demarrage de l'application) :
 * un fichier mal forme fait echouer le boot avec un message explicite, plutot que de servir
 * silencieusement un jeu casse.
 *
 * <p>Ajouter/editer un jeu = editer un fichier YAML, sans toucher au code.</p>
 */
public final class PuzzlePackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu puzzle. */
    public static final String DEFAULT_LOCATION = "classpath*:content/puzzle/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets puzzle, tries par nom de fichier (ordre deterministe). */
    public List<PuzzleContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<PuzzleContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<PuzzleContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu puzzle trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu puzzle : " + locationPattern, e);
        }
    }

    public PuzzleContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu puzzle vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu puzzle : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private PuzzleContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);

        Object motsNode = root.get("mots");
        if (!(motsNode instanceof List<?> rawMots) || rawMots.isEmpty()) {
            throw new IllegalStateException("Contenu puzzle '" + where + "' : au moins un mot est requis");
        }

        List<Mot> mots = new ArrayList<>();
        for (Object raw : rawMots) {
            if (!(raw instanceof Map<?, ?> m)) {
                throw new IllegalStateException("Contenu puzzle '" + where + "' : mot mal formé");
            }
            Map<String, Object> item = (Map<String, Object>) m;
            String mot = requireText(item.get("mot"), "mot", where);
            String indice = requireText(item.get("indice"), "indice", where);
            mots.add(new Mot(mot, indice));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new PuzzleContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                consigne, optionalInt(root.get("jokers")), mots);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu puzzle '" + where + "' : champ '" + field + "' manquant ou vide");
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
