package com.recreo.adapter.content;

import morfologik.stemming.Dictionary;
import morfologik.stemming.DictionaryLookup;
import morfologik.stemming.WordData;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Le lexique morphologique du francais, tel que le porte {@code org.languagetool:french-pos-dict},
 * reduit aux deux seules questions dont ADR-0065 a besoin : cette forme est-elle un mot francais,
 * et le squelette de cette forme (ses lettres, accents retires) porte-t-il d'autres formes ?
 *
 * <p><b>Pourquoi une source exterieure au depot.</b> Un critere derive du corpus juge n'en juge que
 * la coherence interne (ADR-0045). Mesure sur le contenu servi au 2026-08-30 : 78 pour cent des
 * formes accentuees (213 sur 274) n'apparaissent que dans un seul paquet. Un critere qui ne
 * comparerait les paquets qu'entre eux ne verrait donc rien du vocabulaire neuf - exactement celui
 * qu'un paquet neuf apporte, et exactement la ou le defaut de s22 est ne.
 *
 * <p><b>Portee test, jamais le fat jar.</b> La dependance est declaree en portee {@code test} :
 * elle ne rejoint aucun artefact livre. C'est ce qui permet a une donnee sous licence
 * CC BY-SA 4.0 d'etre lue par un garde-fou sans gouverner la licence du produit.
 */
final class LexiqueFrancais {

    private static final String RESSOURCE = "/org/languagetool/resource/fr/french.dict";

    private static LexiqueFrancais instance;

    private final Set<String> formes;
    private final Map<String, Set<String>> formesParSquelette;

    private LexiqueFrancais(Set<String> formes, Map<String, Set<String>> formesParSquelette) {
        this.formes = formes;
        this.formesParSquelette = formesParSquelette;
    }

    /**
     * Le squelette d'un mot : ses lettres, tout signe diacritique retire, en minuscules. Deux formes
     * de meme squelette sont deux facons d'accentuer la meme suite de lettres.
     *
     * <p>Ecrit caractere par caractere plutot qu'en expression reguliere : la classe Unicode des
     * marques non espacantes s'ecrit avec une contre-barre, et ADR-0060 a etabli dans ce module
     * qu'une contre-barre dans une chaine Java est un endroit ou l'on se trompe sans le voir.
     */
    static String squelette(String mot) {
        String decompose = Normalizer.normalize(mot, Normalizer.Form.NFD);
        StringBuilder sansMarque = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char lettre = decompose.charAt(rang);
            if (Character.getType(lettre) != Character.NON_SPACING_MARK) {
                sansMarque.append(lettre);
            }
        }
        return sansMarque.toString().toLowerCase();
    }

    static synchronized LexiqueFrancais charger() {
        if (instance == null) {
            instance = lire();
        }
        return instance;
    }

    private static LexiqueFrancais lire() {
        URL ressource = LexiqueFrancais.class.getResource(RESSOURCE);
        if (ressource == null) {
            throw new IllegalStateException(
                    "ADR-0065 : le lexique francais est introuvable sur le classpath de test ("
                            + RESSOURCE + "). Sans lui, le garde-fou d'accentuation ne juge rien : il"
                            + " echoue ici plutot que de passer au vert sans lexique");
        }
        Set<String> formes = new HashSet<>();
        Map<String, Set<String>> formesParSquelette = new HashMap<>();
        try {
            for (WordData donnee : new DictionaryLookup(Dictionary.read(ressource))) {
                String forme = donnee.getWord().toString().toLowerCase();
                if (formes.add(forme)) {
                    formesParSquelette
                            .computeIfAbsent(squelette(forme), cle -> new TreeSet<>())
                            .add(forme);
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return new LexiqueFrancais(formes, formesParSquelette);
    }

    /** Vrai si {@code mot}, tel qu'il est ecrit, est une forme flechie du francais. */
    boolean estUnMotFrancais(String mot) {
        return formes.contains(mot.toLowerCase());
    }

    /** Les formes francaises qui ont le meme squelette que {@code mot}, y compris {@code mot}. */
    Set<String> formesDuMemeSquelette(String mot) {
        return formesParSquelette.getOrDefault(squelette(mot), Set.of());
    }

    int nombreDeFormes() {
        return formes.size();
    }
}
