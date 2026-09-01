package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.mechanic.MechanicId;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ADR-0007 : seule implementation de ContentCatalogPort, seule classe du projet autorisee a lire
 * des fichiers. Toutes les matieres, tous les niveaux, toutes les mecaniques passent par la meme
 * enveloppe de schema (schema, id, type, descripteur, puis items ou parametres) et le meme
 * registre de fabriques. Un paquet invalide ou une entree de catalogue incoherente font echouer
 * le chargement, jamais un repli silencieux.
 */
public final class YamlContentPackLoader implements ContentCatalogPort {

    private static final Map<String, ContentSourceFactory> FABRIQUES = Map.of(
            "question", new QuestionContentSourceFactory(),
            "additions-bornees", new AdditionsBorneesContentSourceFactory(),
            "saisie", new SaisieContentSourceFactory());

    private final String racine;

    public YamlContentPackLoader() {
        this("");
    }

    /** Racine du classpath a partir de laquelle catalogue.yaml et contenu/ sont resolus (tests). */
    YamlContentPackLoader(String racine) {
        this.racine = racine;
    }

    @Override
    public List<GameDefinition> games() {
        String catalogue = racine + "/catalogue.yaml";
        Map<String, Object> corps = charger(catalogue);
        Object brut = champObligatoire(corps, "jeux", catalogue);
        if (!(brut instanceof List<?> jeux)) {
            throw new IllegalStateException(catalogue + " : le champ 'jeux' doit etre une liste");
        }
        return jeux.stream().map(jeu -> definitionDeJeu(jeu, catalogue)).toList();
    }

    private GameDefinition definitionDeJeu(Object jeuBrut, String catalogue) {
        if (!(jeuBrut instanceof Map<?, ?> jeu)) {
            throw new IllegalStateException(
                    catalogue + " : chaque entree de 'jeux' doit etre une correspondance (map)");
        }
        String id = texte(jeu, "id", catalogue);
        String titre = texte(jeu, "titre", catalogue);
        String consigne = texte(jeu, "consigne", catalogue);
        String mecanique = texte(jeu, "mecanique", catalogue);
        String paquetId = texte(jeu, "paquet", catalogue);
        int nombreDeManches = entier(jeu, "nombreDeManches", catalogue);
        Optional<String> consigneAdulte = texteFacultatif(jeu, "consigneAdulte");

        ContentSource contenu = chargerPaquet(paquetId);
        return new GameDefinition(id, titre, consigne, new MechanicId(mecanique), contenu.descriptor(),
                contenu, nombreDeManches, consigneAdulte);
    }

    /** Visibilite de paquet : DEC-0009, tests qui doivent charger un paquet retire du catalogue. */
    ContentSource chargerPaquet(String paquetId) {
        String chemin = racine + "/contenu/" + paquetId + ".yaml";
        Map<String, Object> corps = charger(chemin);

        Object schema = champObligatoire(corps, "schema", chemin);
        if (!Integer.valueOf(1).equals(schema)) {
            throw new IllegalStateException(chemin + " : schema inconnu (" + schema + ")");
        }
        String type = texte(corps, "type", chemin);
        Object descripteurBrut = champObligatoire(corps, "descripteur", chemin);
        if (!(descripteurBrut instanceof Map<?, ?> descripteurMap)) {
            throw new IllegalStateException(chemin + " : le champ 'descripteur' doit etre une correspondance (map)");
        }
        ContentDescriptor descripteur = new ContentDescriptor(
                texte(descripteurMap, "matiere", chemin),
                texte(descripteurMap, "niveau", chemin),
                texte(descripteurMap, "domaine", chemin),
                texte(descripteurMap, "competence", chemin));

        ContentSourceFactory fabrique = FABRIQUES.get(type);
        if (fabrique == null) {
            throw new IllegalStateException(chemin + " : type de contenu inconnu '" + type + "'");
        }
        try {
            return fabrique.creer(descripteur, corps);
        } catch (RuntimeException e) {
            throw new IllegalStateException(chemin + " : " + e.getMessage(), e);
        }
    }

    private Map<String, Object> charger(String chemin) {
        try (InputStream flux = getClass().getResourceAsStream(chemin)) {
            if (flux == null) {
                throw new IllegalStateException(chemin + " : fichier introuvable sur le classpath");
            }
            Object contenu = new Yaml().load(flux);
            if (!(contenu instanceof Map<?, ?> map)) {
                throw new IllegalStateException(chemin + " : la racine du fichier doit etre une correspondance (map)");
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) map;
            return corps;
        } catch (IOException e) {
            throw new IllegalStateException(chemin + " : lecture impossible", e);
        }
    }

    private static Object champObligatoire(Map<?, ?> map, String champ, String chemin) {
        Object valeur = map.get(champ);
        if (valeur == null) {
            throw new IllegalStateException(chemin + " : champ obligatoire manquant '" + champ + "'");
        }
        return valeur;
    }

    private static String texte(Map<?, ?> map, String champ, String chemin) {
        return String.valueOf(champObligatoire(map, champ, chemin));
    }

    /**
     * ADR-0075 section 7 : 'consigneAdulte' est la seule cle facultative du niveau catalogue -
     * absente, elle vaut "ce jeu ne s'appuie sur aucune voix", le cas ordinaire, jamais un cas
     * particulier a remplir d'une valeur factice (meme patron que les champs facultatifs d'item,
     * ADR-0016).
     */
    private static Optional<String> texteFacultatif(Map<?, ?> map, String champ) {
        Object valeur = map.get(champ);
        return valeur == null ? Optional.empty() : Optional.of(String.valueOf(valeur));
    }

    private static int entier(Map<?, ?> map, String champ, String chemin) {
        Object valeur = champObligatoire(map, champ, chemin);
        if (!(valeur instanceof Integer entier)) {
            throw new IllegalStateException(chemin + " : le champ '" + champ + "' doit etre un entier");
        }
        return entier;
    }
}
