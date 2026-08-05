package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S2-1 : aucun texte lu par l'enfant ni aucun bouton actionnable ne doit rester sous la taille de
 * .consigne (1,25rem), reserves 6 et 7 de la fiche bandit-manchot-additions-cp. Lecture directe de
 * recreo.css par decoupage de blocs { }, jamais par expression reguliere (ADR-0008,
 * AssertionsWebTest).
 */
class TailleDesTextesEtBoutonsTest {

    private static final Path CSS = Path.of("src/main/resources/static/css/recreo.css");

    /** Un commentaire CSS peut contenir des virgules ; il ne doit jamais entrer dans un selecteur. */
    private static String sansCommentaires(String source) {
        StringBuilder resultat = new StringBuilder();
        int position = 0;
        while (position < source.length()) {
            int debut = source.indexOf("/*", position);
            if (debut < 0) {
                resultat.append(source, position, source.length());
                break;
            }
            resultat.append(source, position, debut);
            int fin = source.indexOf("*/", debut);
            position = fin < 0 ? source.length() : fin + 2;
        }
        return resultat.toString();
    }

    private static Map<String, String> blocsParSelecteur() throws IOException {
        String source = sansCommentaires(Files.readString(CSS));
        Map<String, String> blocs = new HashMap<>();
        for (String bloc : source.split("\\}")) {
            int accolade = bloc.indexOf('{');
            if (accolade < 0) {
                continue;
            }
            String selecteurs = bloc.substring(0, accolade);
            String corps = bloc.substring(accolade + 1);
            for (String selecteur : selecteurs.split(",")) {
                blocs.merge(selecteur.strip(), corps, (ancien, nouveau) -> ancien + nouveau);
            }
        }
        return blocs;
    }

    private static double tailleEnRem(Map<String, String> blocs, String selecteur) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans recreo.css", selecteur).isNotNull();
        int position = corps.indexOf("font-size:");
        assertThat(position).as("%s doit declarer explicitement font-size", selecteur).isNotNegative();
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + "font-size:".length(), finValeur).strip();
        assertThat(valeur).as("%s : la taille doit etre exprimee en rem", selecteur).endsWith("rem");
        return Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
    }

    @Test
    void aucunTexteNiBoutonNePasseSousLaTailleDeLaConsigne() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        double consigne = tailleEnRem(blocs, ".consigne");
        assertThat(consigne).isEqualTo(1.25);

        for (String selecteur : new String[] {
                ".score", ".indice", ".fin-de-partie", ".erreur", ".pas-de-jeu",
                ".message-reprise", ".retour-accueil", ".demarrer", ".recommencer", ".encore"
        }) {
            assertThat(tailleEnRem(blocs, selecteur))
                    .as("%s ne doit jamais passer sous la consigne (%.2frem)", selecteur, consigne)
                    .isGreaterThanOrEqualTo(consigne);
        }
    }

    @Test
    void toutBoutonActionnableEstAuMoinsAUnRemVingtCinq() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();

        for (String bouton : new String[] {".demarrer", ".encore", ".retour-accueil", ".recommencer"}) {
            assertThat(tailleEnRem(blocs, bouton))
                    .as("%s ne doit jamais retomber au defaut du navigateur (environ 13px)", bouton)
                    .isGreaterThanOrEqualTo(1.25);
        }
    }

    @Test
    void lIndiceEstAuMoinsAussiGrandQueLaConsigne() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        assertThat(tailleEnRem(blocs, ".indice"))
                .isGreaterThanOrEqualTo(tailleEnRem(blocs, ".consigne"));
    }

    @Test
    void h1PorteUneRegleDeTailleExplicite() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        assertThat(tailleEnRem(blocs, "h1")).isGreaterThan(0);
    }
}
