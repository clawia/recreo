package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.AssociationGenerator.Association;
import com.recreo.domain.content.impl.AssociationGenerator.Puzzle;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Charge les {@link AssocContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/associer/*.yml}). Chaque fichier = un jeu associer/classer. Le contenu
 * est VALIDE au chargement (demarrage de l'application) : un fichier mal forme fait
 * echouer le boot avec un message explicite, plutot que de servir silencieusement un jeu
 * casse.
 *
 * <p>Ajouter/editer un jeu = editer un fichier YAML, sans toucher au code (cf. ADR-0016).</p>
 */
public final class AssocPackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu associer. */
    public static final String DEFAULT_LOCATION = "classpath*:content/associer/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets associer, tries par nom de fichier (ordre deterministe). */
    public List<AssocContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<AssocContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<AssocContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu associer trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu associer : " + locationPattern, e);
        }
    }

    public AssocContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu associer vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu associer : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private AssocContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);

        Object puzzlesNode = root.get("puzzles");
        if (!(puzzlesNode instanceof List<?> rawPuzzles) || rawPuzzles.isEmpty()) {
            throw new IllegalStateException("Contenu associer '" + where + "' : au moins un puzzle est requis");
        }

        List<Puzzle> puzzles = new ArrayList<>();
        for (Object rawPuzzle : rawPuzzles) {
            if (!(rawPuzzle instanceof Map<?, ?> p)) {
                throw new IllegalStateException("Contenu associer '" + where + "' : puzzle mal formé");
            }
            Map<String, Object> puzzle = (Map<String, Object>) p;

            Object assocNode = puzzle.get("associations");
            if (!(assocNode instanceof List<?> rawAssocs) || rawAssocs.size() < 2) {
                throw new IllegalStateException(
                        "Contenu associer '" + where + "' : chaque puzzle requiert au moins deux associations");
            }
            List<Association> associations = new ArrayList<>();
            Set<String> seenItems = new LinkedHashSet<>();
            for (Object rawAssoc : rawAssocs) {
                if (!(rawAssoc instanceof Map<?, ?> a)) {
                    throw new IllegalStateException("Contenu associer '" + where + "' : association mal formée");
                }
                Map<String, Object> assoc = (Map<String, Object>) a;
                String item = requireText(assoc.get("item"), "item", where);
                String cible = requireText(assoc.get("cible"), "cible", where);
                if (!seenItems.add(item)) {
                    throw new IllegalStateException(
                            "Contenu associer '" + where + "' : item en double dans un puzzle « " + item + " »");
                }
                associations.add(new Association(item, cible));
            }
            puzzles.add(new Puzzle(associations));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new AssocContentPack(entryId, title, description, gameKey, gameLabel, descriptor, consigne,
                optionalInt(root.get("jokers")), puzzles);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
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

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu associer '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }
}
