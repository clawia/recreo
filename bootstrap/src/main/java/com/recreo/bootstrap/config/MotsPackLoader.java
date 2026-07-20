package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MotChoixGenerator.Entree;
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
 * Charge les {@link MotsContentPack} depuis les ressources YAML éditables du classpath
 * ({@code content/mots/*.yml}). Chaque fichier = un jeu « mot → réponse » du bandit
 * manchot. Le contenu est VALIDÉ au chargement (démarrage) : un fichier mal formé fait
 * échouer le boot avec un message explicite. Ajouter/éditer un jeu = éditer un YAML,
 * sans toucher au code (cf. DEC-0006 / ADR-0010).
 */
public final class MotsPackLoader {

    public static final String DEFAULT_LOCATION = "classpath*:content/mots/*.yml";

    private final Yaml yaml = new Yaml();

    public List<MotsContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<MotsContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<MotsContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu 'mots' trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu 'mots' : " + locationPattern, e);
        }
    }

    public MotsContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu 'mots' vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu 'mots' : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private MotsContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String question = requireText(root.get("question"), "question", where);

        String mode = requireText(root.get("distractorMode"), "distractorMode", where);
        boolean poolMode = switch (mode) {
            case "pool" -> true;
            case "explicit" -> false;
            default -> throw new IllegalStateException(
                    "Contenu 'mots' '" + where + "' : distractorMode doit être 'explicit' ou 'pool' (reçu : " + mode + ")");
        };
        int distractorCount = poolMode
                ? ((Number) requirePresent(root.get("distractorCount"), "distractorCount", where)).intValue()
                : 0;
        if (poolMode && distractorCount < 1) {
            throw new IllegalStateException("Contenu 'mots' '" + where + "' : distractorCount doit être >= 1");
        }

        Object itemsNode = root.get("items");
        if (!(itemsNode instanceof List<?> rawItems) || rawItems.isEmpty()) {
            throw new IllegalStateException("Contenu 'mots' '" + where + "' : au moins un item est requis");
        }

        List<Entree> entrees = new ArrayList<>();
        Set<String> seenPrompts = new LinkedHashSet<>();
        for (Object raw : rawItems) {
            if (!(raw instanceof Map<?, ?> m)) {
                throw new IllegalStateException("Contenu 'mots' '" + where + "' : item mal formé");
            }
            Map<String, Object> item = (Map<String, Object>) m;
            String prompt = requireText(item.get("prompt"), "prompt", where);
            String bonne = requireText(item.get("bonne"), "bonne", where);
            if (!seenPrompts.add(prompt)) {
                throw new IllegalStateException("Contenu 'mots' '" + where + "' : prompt en double « " + prompt + " »");
            }

            List<String> distracteurs = new ArrayList<>();
            if (!poolMode) {
                Object distNode = item.get("distracteurs");
                if (!(distNode instanceof List<?> rawDist) || rawDist.isEmpty()) {
                    throw new IllegalStateException(
                            "Contenu 'mots' '" + where + "' : distracteurs requis (mode explicit) pour « " + prompt + " »");
                }
                for (Object d : rawDist) {
                    distracteurs.add(requireText(d, "distracteur", where));
                }
                if (distracteurs.contains(bonne)) {
                    throw new IllegalStateException(
                            "Contenu 'mots' '" + where + "' : un distracteur égale la bonne réponse pour « " + prompt + " »");
                }
                if (new LinkedHashSet<>(distracteurs).size() != distracteurs.size()) {
                    throw new IllegalStateException(
                            "Contenu 'mots' '" + where + "' : distracteurs en double pour « " + prompt + " »");
                }
            }
            entrees.add(new Entree(prompt, bonne, distracteurs));
        }

        if (poolMode) {
            long distinctAnswers = entrees.stream().map(Entree::bonne).distinct().count();
            if (distinctAnswers < distractorCount + 1L) {
                throw new IllegalStateException("Contenu 'mots' '" + where + "' : pas assez de réponses distinctes ("
                        + distinctAnswers + ") pour tirer " + distractorCount + " distracteur(s)");
            }
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new MotsContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                question, poolMode, distractorCount, entrees);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static Object requirePresent(Object value, String field, String where) {
        if (value == null) {
            throw new IllegalStateException("Contenu 'mots' '" + where + "' : champ '" + field + "' manquant");
        }
        return value;
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu 'mots' '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }
}
