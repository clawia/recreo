package com.recreo.adapter.content;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Un paquet de questions tel que son YAML l'ecrit, avec les commentaires qui precedent chaque item :
 * les tests de paquet de questionner le monde CE1 y lisent les declarations d'auteur (espece, tache,
 * familles de distracteurs) que SnakeYAML ne voit pas. Ecrit une fois pour les neuf lots, sur le
 * modele de {@code ClassementDesItems} : neuf copies d'un lecteur divergeraient.
 */
final class PaquetLu {

    private PaquetLu() {
    }

    /** Un item tel que le YAML le porte, avec les commentaires indentes de deux espaces qui le precedent. */
    record Item(String enonce, String bonneReponse, List<String> distracteurs, String indice,
                Set<String> cles, List<String> commentaires) {

        List<String> propositions() {
            List<String> propositions = new ArrayList<>(distracteurs);
            propositions.add(bonneReponse);
            return propositions;
        }

        /** Ce que l'enfant lit : enonce, propositions et indice. */
        List<String> champsLus() {
            List<String> champs = new ArrayList<>(propositions());
            champs.add(enonce);
            champs.add(indice);
            return champs;
        }
    }

    /** Un paquet : son descripteur, ses lignes de referentiel, son en-tete et ses items. */
    record Paquet(String fichier, String matiere, String niveau, List<String> lignes, String entete,
                  List<Item> items) {
    }

    static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static Paquet lirePaquet(Path fichier) {
        return lireTexte(fichier.getFileName().toString(), lire(fichier));
    }

    static Paquet lireTexte(String nom, String texte) {
        Map<?, ?> corps = new Yaml().load(texte);
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        List<String> lignes = new ArrayList<>();
        if (corps.get("lignesDeReferentiel") instanceof List<?> declarees) {
            declarees.forEach(ligne -> lignes.add(String.valueOf(ligne)));
        }
        List<?> items = (List<?>) corps.get("items");
        List<List<String>> commentaires = commentairesDesItems(texte);
        if (commentaires.size() != items.size()) {
            throw new IllegalStateException(nom + " : " + commentaires.size()
                    + " lignes '- enonce:' pour " + items.size() + " items");
        }
        List<Item> lus = new ArrayList<>();
        for (int rang = 0; rang < items.size(); rang++) {
            Map<?, ?> champs = (Map<?, ?>) items.get(rang);
            List<String> distracteurs = new ArrayList<>();
            for (Object distracteur : (List<?>) champs.get("distracteurs")) {
                distracteurs.add(String.valueOf(distracteur));
            }
            Set<String> cles = new TreeSet<>();
            champs.keySet().forEach(cle -> cles.add(String.valueOf(cle)));
            lus.add(new Item(String.valueOf(champs.get("enonce")),
                    String.valueOf(champs.get("bonneReponse")), distracteurs,
                    champs.get("indice") == null ? "" : String.valueOf(champs.get("indice")),
                    cles, commentaires.get(rang)));
        }
        return new Paquet(nom, String.valueOf(descripteur.get("matiere")),
                String.valueOf(descripteur.get("niveau")), lignes, entete(texte), lus);
    }

    private static List<List<String>> commentairesDesItems(String texte) {
        List<List<String>> blocs = new ArrayList<>();
        List<String> courant = new ArrayList<>();
        for (String ligne : texte.split("\\R")) {
            if (ligne.startsWith("  # ")) {
                courant.add(ligne.substring(4).strip());
            } else if (ligne.startsWith("  - enonce:")) {
                blocs.add(courant);
                courant = new ArrayList<>();
            }
        }
        return blocs;
    }

    /** Les lignes de l'en-tete du paquet : tout ce qui precede la cle {@code items:}. */
    private static String entete(String texte) {
        int debut = texte.indexOf("\nitems:");
        return debut < 0 ? texte : texte.substring(0, debut);
    }

    /** Les mots d'un texte : les segments separes par un blanc, insecable compris, qui portent une lettre ou un chiffre. */
    static int mots(String texte) {
        return (int) Arrays.stream(texte.strip().split("[\\s\\u00A0]+"))
                .filter(segment -> segment.chars().anyMatch(Character::isLetterOrDigit)).count();
    }

    /** Minuscule, sans accent : la forme sur laquelle un mot exclu est cherche, ecrit avec ou sans accent. */
    static String sansAccent(String texte) {
        String decompose = Normalizer.normalize(texte.toLowerCase(Locale.FRENCH), Normalizer.Form.NFD);
        return decompose.replaceAll("\\p{M}", "").replace("œ", "oe");
    }
}
