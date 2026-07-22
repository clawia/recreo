package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.FriseChronologiqueGenerator.Evenement;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Charge les {@link FriseContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/frise/*.yml}). Chaque fichier = un jeu "frise chronologique". Le contenu
 * est VALIDE au chargement (demarrage de l'application) : un fichier mal forme fait echouer
 * le boot avec un message explicite, plutot que de servir silencieusement un jeu casse.
 *
 * <p>Ajouter/editer un jeu = editer un fichier YAML, sans toucher au code.</p>
 */
public final class FrisePackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu frise. */
    public static final String DEFAULT_LOCATION = "classpath*:content/frise/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets frise, tries par nom de fichier (ordre deterministe). */
    public List<FriseContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<FriseContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<FriseContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu frise trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu frise : " + locationPattern, e);
        }
    }

    public FriseContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu frise vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu frise : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private FriseContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);
        int count = ((Number) requirePresent(root.get("count"), "count", where)).intValue();
        if (count < 2) {
            throw new IllegalStateException("Contenu frise '" + where + "' : count doit être >= 2");
        }

        Object evtNode = root.get("evenements");
        if (!(evtNode instanceof List<?> rawEvts) || rawEvts.isEmpty()) {
            throw new IllegalStateException("Contenu frise '" + where + "' : au moins un événement est requis");
        }

        List<Evenement> evenements = new ArrayList<>();
        for (Object raw : rawEvts) {
            if (!(raw instanceof Map<?, ?> e)) {
                throw new IllegalStateException("Contenu frise '" + where + "' : événement mal formé");
            }
            Map<String, Object> evt = (Map<String, Object>) e;
            String label = requireText(evt.get("label"), "label", where);
            int rang = ((Number) requirePresent(evt.get("rang"), "rang", where)).intValue();
            evenements.add(new Evenement(label, rang));
        }

        if (evenements.size() < count) {
            throw new IllegalStateException("Contenu frise '" + where + "' : au moins " + count
                    + " événements requis (trouvés : " + evenements.size() + ")");
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new FriseContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                consigne, count, optionalInt(root.get("jokers")), evenements);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static Object requirePresent(Object value, String field, String where) {
        if (value == null) {
            throw new IllegalStateException("Contenu frise '" + where + "' : champ '" + field + "' manquant");
        }
        return value;
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu frise '" + where + "' : champ '" + field + "' manquant ou vide");
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
