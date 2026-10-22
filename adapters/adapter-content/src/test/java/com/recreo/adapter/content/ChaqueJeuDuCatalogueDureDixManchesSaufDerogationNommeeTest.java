package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * DEC-0121 (reconduction de DEC-0064) : {@code nombreDeManches: 10} est une exigence de produit.
 * Une entree de catalogue ne porte une autre valeur que si elle figure, avec sa valeur exacte et la
 * decision qui nomme son motif, dans {@link #DEROGATIONS}.
 *
 * <p>ADR-0012 fait de la longueur de partie une donnee de l'entree de catalogue. Avant ce test,
 * {@code ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest} (ADR-0023) ne gardait que le plancher :
 * 5 a la place de 10 sur {@code ce1-phrases-types} laissait 1401 tests verts (mutation {@code C8},
 * releve-yanis-palier-1.md).</p>
 *
 * <p>ADR-0013 obligation 1 : le perimetre est le catalogue servi, lu la ou il vit ; chaque entree
 * produit une assertion (obligation 2), y compris une entree sans {@code nombreDeManches}. La liste
 * des derogations n'est pas un perimetre : c'est une liste d'exemptions qui se ferme dans les deux
 * sens. Une entree qui s'ecarte de 10 sans figurer dans la liste est rouge, une entree de la liste
 * dont la valeur change est rouge, une entree de la liste qui porte 10 ou qui n'existe plus est
 * rouge. La liste ne peut donc ni grandir ni pourrir sans diff visible ; ajouter, changer ou retirer
 * une ligne est une decision produit ecrite (DEC-0121 section 5).</p>
 *
 * <p>Il lit le catalogue <b>la ou il vit</b>, sous src/main/resources, et non par le classpath. C'est
 * aussi ce qui le rend visible au recensement de mise a l'epreuve de
 * {@code LesGardeFousSontMisALEpreuveTest}, dont le perimetre est le litteral "src/main/" dans la
 * source du test : le chemin construit par {@code Path.of} en morceaux ne le porte pas, cette phrase
 * le porte.</p>
 *
 * <p>Ce que ce garde-fou ne juge pas : que 7, 8 ou 10 soit la bonne longueur pour un enfant
 * (nadia-prof, DEC-0064 section 4), ni que la decision citee par une ligne existe et nomme bien cette
 * valeur.</p>
 */
class ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest {

    private static final int MANCHES_PAR_DEFAUT = 10;

    private static final Path CATALOGUE_SERVI = Path.of("src", "main", "resources", "catalogue.yaml");

    /** ADR-0013 obligation 3 : des catalogues fautifs que personne n'a jamais servis. */
    private static final Path CATALOGUES_FAUTIFS = Path.of("src", "test", "resources", "contreexemple",
            "ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest");

    record Derogation(int valeur, String decision) {
    }

    /**
     * Liste de DEC-0121 section 3, amendee en section 8 (2026-10-19) : huit entrees. Ajouter,
     * changer ou retirer une ligne exige une decision produit ecrite (DEC-0121 section 5).
     */
    private static final Map<String, Derogation> DEROGATIONS = Map.of(
            "bandit-manchot-complements-10-cp", new Derogation(7, "DEC-0020"),
            "saisie-complements-10-cp", new Derogation(7, "DEC-0064"),
            "bandit-manchot-histoire-geographie-lire-un-document-cm1", new Derogation(7, "DEC-0088"),
            "bandit-manchot-decrire-une-oeuvre-cp", new Derogation(7, "DEC-0109"),
            "designation-symetrie-ce1", new Derogation(8, "DEC-0111"),
            "designation-plan-de-la-classe", new Derogation(5, "DEC-0121"),
            "saisie-mot-dicte-cp", new Derogation(7, "DEC-0121"),
            "bandit-manchot-rapprocher-deux-oeuvres-cp", new Derogation(5, "DEC-0121"));

    /** Liste des catalogues fautifs : une seule derogation, fictive. */
    private static final Map<String, Derogation> DEROGATIONS_FICTIVES = Map.of(
            "jeu-fictif-derogatoire", new Derogation(7, "DEC-0020"));

    /** Identifiant de jeu vers valeur lue, telle que le fichier l'ecrit ({@code null} si absente). */
    private static Map<String, String> manchesParJeu(Path catalogue) {
        try (InputStream flux = Files.newInputStream(catalogue)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            Map<String, String> lues = new LinkedHashMap<>();
            for (Map<String, Object> jeu : jeux) {
                Object manches = jeu.get("nombreDeManches");
                lues.put(String.valueOf(jeu.get("id")), manches == null ? null : String.valueOf(manches));
            }
            return lues;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static void verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(Path catalogue,
                                                                     Map<String, Derogation> derogations) {
        Map<String, String> manches = manchesParJeu(catalogue);

        assertThat(manches)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune entree de catalogue (%s)", catalogue)
                .isNotEmpty();

        List<String> sansValeur = new ArrayList<>();
        List<String> horsListe = new ArrayList<>();
        List<String> valeurChangee = new ArrayList<>();
        List<String> derogationQuiNEnEstPlusUne = new ArrayList<>();
        for (Map.Entry<String, String> jeu : manches.entrySet()) {
            Derogation derogation = derogations.get(jeu.getKey());
            if (jeu.getValue() == null) {
                sansValeur.add(jeu.getKey());
            } else if (derogation == null && !jeu.getValue().equals(String.valueOf(MANCHES_PAR_DEFAUT))) {
                horsListe.add("%s porte %s".formatted(jeu.getKey(), jeu.getValue()));
            } else if (derogation != null && jeu.getValue().equals(String.valueOf(MANCHES_PAR_DEFAUT))) {
                derogationQuiNEnEstPlusUne.add("%s porte %d et la liste le nomme (%s)"
                        .formatted(jeu.getKey(), MANCHES_PAR_DEFAUT, derogation.decision()));
            } else if (derogation != null && !jeu.getValue().equals(String.valueOf(derogation.valeur()))) {
                valeurChangee.add("%s porte %s, la liste (%s) en nomme %d"
                        .formatted(jeu.getKey(), jeu.getValue(), derogation.decision(), derogation.valeur()));
            }
        }
        List<String> disparues = derogations.keySet().stream()
                .filter(id -> !manches.containsKey(id))
                .sorted()
                .toList();

        assertThat(sansValeur)
                .as("DEC-0121 : ces entrees de %s ne portent pas nombreDeManches", catalogue)
                .isEmpty();
        assertThat(horsListe)
                .as("DEC-0121 : ces entrees de %s ne portent pas %d et ne figurent pas dans la liste des"
                        + " derogations. Une autre valeur que %d exige une decision produit ecrite qui"
                        + " nomme son motif (DEC-0064, DEC-0121 section 5), puis une ligne de la liste",
                        catalogue, MANCHES_PAR_DEFAUT, MANCHES_PAR_DEFAUT)
                .isEmpty();
        assertThat(valeurChangee)
                .as("DEC-0121 : la valeur de ces derogations de %s a change. Changer une ligne de la"
                        + " liste est une decision produit ecrite", catalogue)
                .isEmpty();
        assertThat(derogationQuiNEnEstPlusUne)
                .as("DEC-0121 : ces derogations de %s portent %d, la valeur par defaut. Retirer une"
                        + " ligne de la liste est une decision produit ecrite", catalogue, MANCHES_PAR_DEFAUT)
                .isEmpty();
        assertThat(disparues)
                .as("DEC-0121 : ces derogations ne designent plus aucune entree de %s. Retirer une"
                        + " ligne de la liste est une decision produit ecrite", catalogue)
                .isEmpty();
    }

    @Test
    void chaqueJeuDuCatalogueDeProductionDureDixMancheSaufDerogationNommee() {
        verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(CATALOGUE_SERVI, DEROGATIONS);
    }

    /** Le cas de la mutation C8 : 5 a la place de 10, sur une entree que la liste ne nomme pas. */
    @Test
    void uneEntreeAutreQueDixAbsenteDeLaListeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve("catalogue.yaml"), Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("jeu-fictif-a-cinq-manches-hors-liste porte 5")
                .hasMessageContaining("ne figurent pas dans la liste");
    }

    @Test
    void uneDerogationDontLaValeurChangeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve(Path.of("valeur-changee", "catalogue.yaml")),
                DEROGATIONS_FICTIVES))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("jeu-fictif-derogatoire porte 6, la liste (DEC-0020) en nomme 7");
    }

    @Test
    void uneDerogationQuiPorteDixFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve(Path.of("derogation-a-dix", "catalogue.yaml")),
                DEROGATIONS_FICTIVES))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("jeu-fictif-derogatoire porte 10 et la liste le nomme (DEC-0020)");
    }

    @Test
    void uneDerogationQuiNeDesigneAucuneEntreeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve(Path.of("corrige", "catalogue.yaml")),
                Map.of("jeu-fictif-derogatoire", new Derogation(7, "DEC-0020"),
                        "jeu-fictif-disparu", new Derogation(5, "DEC-0121"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne designent plus aucune entree")
                .hasMessageContaining("jeu-fictif-disparu");
    }

    @Test
    void uneEntreeSansNombreDeManchesFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve(Path.of("sans-valeur", "catalogue.yaml")), Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("jeu-fictif-sans-valeur")
                .hasMessageContaining("ne portent pas nombreDeManches");
    }

    /**
     * L'autre borne, et elle compte autant que les morsures : un catalogue dont chaque entree vaut 10
     * ou la valeur que la liste nomme passe. Un garde-fou qui refuserait toute valeur autre que 10
     * rougirait ici.
     */
    @Test
    void uneDerogationALaValeurNommeePasseLeGardeFou() {
        verifierQueChaqueJeuDureDixMancheSaufDerogationNommee(
                CATALOGUES_FAUTIFS.resolve(Path.of("corrige", "catalogue.yaml")), DEROGATIONS_FICTIVES);
    }
}
