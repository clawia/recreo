package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MemoryGenerator.Paire;
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
 * Charge les {@link MemoryContentPack} depuis les ressources YAML editables du classpath
 * ({@code content/memory/*.yml}). Chaque fichier = un jeu memory. Le contenu est VALIDE au
 * chargement (demarrage de l'application) : un fichier mal forme fait echouer le boot avec
 * un message explicite, plutot que de servir silencieusement un jeu casse.
 *
 * <p>Ajouter/editer un jeu = editer un fichier YAML, sans toucher au code (cf. ADR-0016).</p>
 */
public final class MemoryPackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu memory. */
    public static final String DEFAULT_LOCATION = "classpath*:content/memory/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets memory, tries par nom de fichier (ordre deterministe). */
    public List<MemoryContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<MemoryContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<MemoryContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu memory trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu memory : " + locationPattern, e);
        }
    }

    public MemoryContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu memory vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu memory : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private MemoryContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));
        String consigne = requireText(root.get("consigne"), "consigne", where);

        Object pairesNode = root.get("paires");
        if (!(pairesNode instanceof List<?> rawPaires) || rawPaires.size() < 2) {
            throw new IllegalStateException("Contenu memory '" + where + "' : au moins deux paires sont requises");
        }

        List<Paire> paires = new ArrayList<>();
        Set<String> seenGauche = new LinkedHashSet<>();
        Set<String> seenDroite = new LinkedHashSet<>();
        for (Object rawPaire : rawPaires) {
            if (!(rawPaire instanceof Map<?, ?> p)) {
                throw new IllegalStateException("Contenu memory '" + where + "' : paire mal formée");
            }
            Map<String, Object> paire = (Map<String, Object>) p;
            String gauche = requireText(paire.get("gauche"), "gauche", where);
            String droite = requireText(paire.get("droite"), "droite", where);
            if (!seenGauche.add(gauche)) {
                throw new IllegalStateException(
                        "Contenu memory '" + where + "' : face gauche en double « " + gauche + " »");
            }
            if (!seenDroite.add(droite)) {
                throw new IllegalStateException(
                        "Contenu memory '" + where + "' : face droite en double « " + droite + " »");
            }
            paires.add(new Paire(gauche, droite));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new MemoryContentPack(entryId, title, description, gameKey, gameLabel, descriptor, consigne, paires);
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    private static String requireText(Object value, String field, String where) {
        if (value == null || value.toString().isBlank()) {
            throw new IllegalStateException("Contenu memory '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }
}
