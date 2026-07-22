package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.QuizGenerator.Question;
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
 * Charge les {@link QuizContentPack} depuis les ressources YAML éditables du classpath
 * ({@code content/quiz/*.yml}). Chaque fichier = un jeu quiz. Le contenu est VALIDÉ au
 * chargement (démarrage de l'application) : un fichier mal formé fait échouer le boot
 * avec un message explicite, plutôt que de servir silencieusement un jeu cassé.
 *
 * <p>Ajouter/éditer un quiz = éditer un fichier YAML, sans toucher au code (cf. DEC-0006).</p>
 */
public final class QuizPackLoader {

    /** Emplacement (glob classpath) des fichiers de contenu quiz. */
    public static final String DEFAULT_LOCATION = "classpath*:content/quiz/*.yml";

    private final Yaml yaml = new Yaml();

    /** Charge tous les paquets quiz, triés par nom de fichier (ordre déterministe). */
    public List<QuizContentPack> loadAll() {
        return loadAll(DEFAULT_LOCATION);
    }

    public List<QuizContentPack> loadAll(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            List<Resource> sorted = new ArrayList<>(List.of(resources));
            sorted.sort(Comparator.comparing(r -> r.getFilename() == null ? "" : r.getFilename()));
            List<QuizContentPack> packs = new ArrayList<>();
            for (Resource resource : sorted) {
                packs.add(load(resource));
            }
            if (packs.isEmpty()) {
                throw new IllegalStateException("Aucun contenu quiz trouvé : " + locationPattern);
            }
            return packs;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de lister le contenu quiz : " + locationPattern, e);
        }
    }

    public QuizContentPack load(Resource resource) {
        String where = resource.getFilename() == null ? resource.toString() : resource.getFilename();
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu quiz vide : " + where);
            }
            return toPack(root, where);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu quiz : " + where, e);
        }
    }

    @SuppressWarnings("unchecked")
    private QuizContentPack toPack(Map<String, Object> root, String where) {
        String entryId = requireText(root.get("entryId"), "entryId", where);
        String title = requireText(root.get("title"), "title", where);
        String description = text(root.get("description"));
        String gameKey = requireText(root.get("gameKey"), "gameKey", where);
        String gameLabel = requireText(root.get("gameLabel"), "gameLabel", where);
        String matiere = requireText(root.get("matiere"), "matiere", where);
        String niveau = requireText(root.get("niveau"), "niveau", where);
        String domaine = text(root.get("domaine"));

        Object questionsNode = root.get("questions");
        if (!(questionsNode instanceof List<?> rawQuestions) || rawQuestions.isEmpty()) {
            throw new IllegalStateException("Contenu quiz '" + where + "' : au moins une question est requise");
        }

        List<Question> questions = new ArrayList<>();
        Set<String> seenQuestions = new LinkedHashSet<>();
        for (Object raw : rawQuestions) {
            if (!(raw instanceof Map<?, ?> q)) {
                throw new IllegalStateException("Contenu quiz '" + where + "' : question mal formée");
            }
            Map<String, Object> question = (Map<String, Object>) q;
            String enonce = requireText(question.get("question"), "question", where);
            String bonne = requireText(question.get("bonne"), "bonne", where);

            Object distNode = question.get("distracteurs");
            if (!(distNode instanceof List<?> rawDist) || rawDist.isEmpty()) {
                throw new IllegalStateException(
                        "Contenu quiz '" + where + "' : au moins un distracteur pour « " + enonce + " »");
            }
            List<String> distracteurs = new ArrayList<>();
            for (Object d : rawDist) {
                distracteurs.add(requireText(d, "distracteur", where));
            }

            // Invariants pédagogiques : distracteurs distincts et différents de la bonne réponse.
            if (distracteurs.contains(bonne)) {
                throw new IllegalStateException(
                        "Contenu quiz '" + where + "' : un distracteur égale la bonne réponse pour « " + enonce + " »");
            }
            if (new LinkedHashSet<>(distracteurs).size() != distracteurs.size()) {
                throw new IllegalStateException(
                        "Contenu quiz '" + where + "' : distracteurs en double pour « " + enonce + " »");
            }
            if (!seenQuestions.add(enonce)) {
                throw new IllegalStateException("Contenu quiz '" + where + "' : question en double « " + enonce + " »");
            }
            questions.add(new Question(enonce, bonne, distracteurs));
        }

        ContentDescriptor descriptor = new ContentDescriptor(matiere, niveau, domaine);
        return new QuizContentPack(entryId, title, description, gameKey, gameLabel, descriptor,
                optionalInt(root.get("jokers")), questions);
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
            throw new IllegalStateException("Contenu quiz '" + where + "' : champ '" + field + "' manquant ou vide");
        }
        return value.toString();
    }
}
