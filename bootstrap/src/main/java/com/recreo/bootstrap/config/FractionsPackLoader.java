package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.CompareFractionsGenerator.Fraction;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Charge les {@link FractionsContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/fractions/*.yml}). Chaque fichier = un jeu "compare les fractions". Le
 * contenu est VALIDE au chargement (demarrage de l'application) : un fichier mal forme fait
 * echouer le boot avec un message explicite, plutot que de servir silencieusement un jeu casse.
 *
 * <p>Ajouter/editer un jeu = editer un fichier YAML, sans toucher au code.</p>
 */
public final class FractionsPackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu fractions. */
    public static final String DEFAULT_LOCATION = "classpath*:content/fractions/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets fractions, tries par nom de fichier (ordre deterministe). */
    public List<FractionsContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<FractionsContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<FractionsContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu fractions trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu fractions : " + locationPattern, e);
        }
    }

    public FractionsContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu fractions vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu fractions : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private FractionsContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);

        Object fracNode = root.get("fractions");
        if (!(fracNode instanceof List<?> rawFracs) || rawFracs.size() < 2) {
            throw new IllegalStateException("Contenu fractions '" + where + "' : au moins deux fractions sont requises");
        }

        List<Fraction> fractions = new ArrayList<>();
        for (Object raw : rawFracs) {
            if (!(raw instanceof Map<?, ?> f)) {
                throw new IllegalStateException("Contenu fractions '" + where + "' : fraction mal formée");
            }
            Map<String, Object> frac = (Map<String, Object>) f;
            int num = ((Number) requirePresent(frac.get("num"), "num", where)).intValue();
            int den = ((Number) requirePresent(frac.get("den"), "den", where)).intValue();
            if (den == 0) {
                throw new IllegalStateException("Contenu fractions '" + where + "' : dénominateur nul interdit");
            }
            String label = requireText(frac.get("label"), "label", where);
            fractions.add(new Fraction(num, den, label));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new FractionsContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                consigne, optionalInt(root.get("jokers")), fractions);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static Object requirePresent(Object value, String field, String where) {
        if (value == null) {
            throw new IllegalStateException("Contenu fractions '" + where + "' : champ '" + field + "' manquant");
        }
        return value;
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu fractions '" + where + "' : champ '" + field + "' manquant ou vide");
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
