package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S2-2 : les ecrans aujourd'hui nus portent chacun au moins une regle de couleur, de fond ou
 * d'espacement, et ces couleurs viennent d'une palette nommee et reutilisee, jamais de valeurs
 * ponctuelles differentes a chaque selecteur. Lecture directe de recreo.css, jamais par expression
 * reguliere (ADR-0008, AssertionsWebTest).
 *
 * <p>Ce garde-fou juge des choix de style propres a ce depot : viser recreo.css par son nom est ici
 * legitime, ce sont ses declarations a lui qui sont en cause. Une seule de ses methodes ne relevait
 * pas de ce registre, {@code aucuneRessourceExterneNiPoliceTelechargeeALExecution} : ne rien aller
 * chercher sur le reseau n'est pas un choix de style, c'est une condition de deploiement qui vaut
 * pour tout octet servi. Restreinte a un fichier nomme, elle promettait par son nom bien plus
 * qu'elle ne verifiait. Elle est supprimee par ADR-0015 au profit de
 * {@code RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau},
 * dont le perimetre derive de static/ entier, gabarits compris, et qui possede deux jeux fautifs.
 */
class HabillageDesEcransTest {

    private static final Path CSS = Path.of("src/main/resources/static/css/recreo.css");

    private static String source() throws IOException {
        return Files.readString(CSS);
    }

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

    private static Map<String, String> blocsParSelecteur(String source) {
        source = sansCommentaires(source);
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

    @Test
    void chaqueEcranNuPorteAuMoinsUneRegleDeCouleurDeFondOuDEspacement() throws IOException {
        Map<String, String> blocs = blocsParSelecteur(source());

        for (String selecteur : new String[] {
                "h1", ".jeux", ".demarrer", ".retour-accueil", ".score", ".fin-de-partie",
                ".indice", ".erreur", ".pas-de-jeu", ".message-reprise", ".recommencer"
        }) {
            String corps = blocs.get(selecteur);
            assertThat(corps).as("le selecteur %s doit exister dans recreo.css", selecteur).isNotNull();
            assertThat(corps.contains("color:") || corps.contains("background")
                    || corps.contains("margin") || corps.contains("padding"))
                    .as("%s doit porter au moins une regle de couleur, de fond ou d'espacement", selecteur)
                    .isTrue();
        }
    }

    @Test
    void lesCouleursProviennentDUnePaletteNommeeEtReutilisee() throws IOException {
        String source = source();
        assertThat(source).contains(":root");

        int debut = source.indexOf(":root");
        int finBloc = source.indexOf('}', debut);
        String racine = source.substring(debut, finBloc);

        int nombreDeVariables = 0;
        int position = 0;
        while ((position = racine.indexOf("--recreo-", position)) >= 0) {
            nombreDeVariables++;
            position += "--recreo-".length();
        }
        assertThat(nombreDeVariables)
                .as("la palette doit nommer plusieurs valeurs reutilisables")
                .isGreaterThanOrEqualTo(4);

        int nombreDUsagesVar = 0;
        position = 0;
        while ((position = source.indexOf("var(--recreo-", position)) >= 0) {
            nombreDUsagesVar++;
            position += "var(--recreo-".length();
        }
        assertThat(nombreDUsagesVar)
                .as("chaque variable doit etre reutilisee a plusieurs endroits, pas juste declaree")
                .isGreaterThan(nombreDeVariables);
    }

    /**
     * S3-6 : sur l'ecran de fin de partie, la sortie devient la seule action possible et doit
     * porter le meme aplat de couleur que les autres boutons d'action, pas le contour discret
     * retenu pour l'ecran de jeu (nadia-prof, FICHE-bandit-manchot-additions-cp.md, reserve 4).
     */
    @Test
    void laSortiePleineEstUnAplatColoreDeLaPalette() throws IOException {
        Map<String, String> blocs = blocsParSelecteur(source());
        String corps = blocs.get(".sortie-pleine");

        assertThat(corps).as("le selecteur .sortie-pleine doit exister dans recreo.css").isNotNull();
        assertThat(corps).contains("background-color: var(--recreo-accent)");
        assertThat(corps).doesNotContain("transparent");
    }

    /**
     * S3-6, 3e critere : sur l'ecran de jeu et sur le retour juste/faux, la sortie reste le
     * contour discret accorde par nadia-prof pour S2-1 - aucune regression sur ce point.
     */
    @Test
    void laSortieResteEnContourSurLEcranDeJeuEtDeRetour() throws IOException {
        Map<String, String> blocs = blocsParSelecteur(source());
        String corps = blocs.get(".retour-accueil");

        assertThat(corps).as("le selecteur .retour-accueil doit exister dans recreo.css").isNotNull();
        assertThat(corps).contains("transparent");
    }

    /**
     * S3-6, 2e critere : sur l'ecran d'erreur, "Recommencer" est aussi la seule action possible et
     * doit rester un aplat de couleur, jamais un lien discret.
     */
    @Test
    void recommencerResteUnAplatColoreSurLEcranDErreur() throws IOException {
        Map<String, String> blocs = blocsParSelecteur(source());
        String corps = blocs.get(".recommencer");

        assertThat(corps).as("le selecteur .recommencer doit exister dans recreo.css").isNotNull();
        assertThat(corps).contains("background-color: var(--recreo-accent)");
    }
}
