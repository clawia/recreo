package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.SyllabesCpGenerator.Mot;
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
 * Charge les {@link SyllabesContentPack} depuis {@code content/syllabes/*.yml}. Le contenu
 * (liste mot → nombre de syllabes) et les options du réglage sont VALIDÉS au démarrage :
 * un fichier mal formé fait échouer le boot. Éditer/ajouter des mots = éditer un YAML
 * (cf. DEC-0006 / ADR-0011).
 */
public final class SyllabesPackLoader {

    public static final String DEFAULT_LOCATION = "classpath*:content/syllabes/*.yml";

    private final Yaml yaml = new Yaml();

    public List<SyllabesContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<SyllabesContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<SyllabesContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu 'syllabes' trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu 'syllabes' : " + locationPattern, e);
        }
    }

    public SyllabesContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu 'syllabes' vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu 'syllabes' : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private SyllabesContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));

        Object optionsNode = root.get("syllabesOptions");
        if (!(optionsNode instanceof List<?> rawOptions) || rawOptions.isEmpty()) {
            throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : syllabesOptions requis (non vide)");
        }
        List<Integer> options = new ArrayList<>();
        for (Object o : rawOptions) {
            options.add(((Number) o).intValue());
        }
        int minSyllabes = ((Number) requirePresent(root.get("minSyllabes"), "minSyllabes", where)).intValue();
        int maxSyllabes = ((Number) requirePresent(root.get("maxSyllabes"), "maxSyllabes", where)).intValue();
        if (minSyllabes > maxSyllabes) {
            throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : minSyllabes > maxSyllabes");
        }

        Object itemsNode = root.get("items");
        if (!(itemsNode instanceof List<?> rawItems) || rawItems.isEmpty()) {
            throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : au moins un item est requis");
        }
        List<Mot> mots = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        for (Object raw : rawItems) {
            if (!(raw instanceof Map<?, ?> m)) {
                throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : item mal formé");
            }
            Map<String, Object> item = (Map<String, Object>) m;
            String mot = requireText(item.get("mot"), "mot", where);
            int syllabes = ((Number) requirePresent(item.get("syllabes"), "syllabes", where)).intValue();
            if (syllabes < 1) {
                throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : syllabes < 1 pour « " + mot + " »");
            }
            if (!seen.add(mot)) {
                throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : mot en double « " + mot + " »");
            }
            mots.add(new Mot(mot, syllabes));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new SyllabesContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                options, minSyllabes, maxSyllabes, optionalInt(root.get("jokers")), mots);
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

    private static Object requirePresent(Object value, String field, String where) {
        if (value == null) {
            throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : champ '" + field + "' manquant");
        }
        return value;
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu 'syllabes' '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }
}
