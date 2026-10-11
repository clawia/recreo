package com.recreo.archi;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>B5 de {@code nadia-prof} : le format d'une oeuvre ne designe pas la bonne reponse, ni par la forme ni
 * par la taille.</b> Garde-fou propose par {@code theo-archi} (amendement du 2026-10-11 de
 * {@code avis-faisabilite.md} de s56). Dans une case carree egale, une image en hauteur se rend plus
 * grande qu'une image en largeur (motif de M1 : <i>"une image plus grande serait un indice"</i>) ; deux
 * clauses, pour chaque item du paquet {@code cp-rapprocher-deux-oeuvres} :
 * <ol>
 *   <li>la bonne oeuvre n'est pas la seule de son format parmi les trois propositions ;</li>
 *   <li>quand la bonne oeuvre partage le format de la reference, un distracteur le partage aussi (regle 3
 *       de {@code nadia-prof} : aucune bonne oeuvre n'est la seule a partager le format de la
 *       reference).</li>
 * </ol>
 *
 * <p><b>Le format se lit dans le segment {@code SOFn} du JPEG servi</b>
 * ({@link ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest#dimensionsJpeg(byte[])}),
 * jamais dans une table ecrite a la main : en largeur si la largeur depasse la hauteur, en hauteur dans
 * l'autre cas. <b>Une image carree n'est classee ni en largeur ni en hauteur</b> : elle fait rougir ce test
 * plutot que d'etre rangee d'office - aucune n'existe aujourd'hui, et son rendu dans la case egale est a
 * {@code ines-design} et a {@code nadia-prof}. Ce que ce test ne juge pas : que la propriete rapprochee se
 * voie, et que la case ne designe pas elle-meme la bonne oeuvre par un autre moyen que le format (a
 * l'audit).
 *
 * <p>ADR-0013 : le perimetre est le paquet servi par le catalogue reel et les fichiers reels de
 * {@code static/oeuvres/} ; la mise a l'epreuve mute en memoire des items reels, et un jeu fautif commite
 * porte des fichiers de formats choisis (en largeur, en hauteur, carre).
 */
class LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest {

    /** Le jeu du catalogue reel qui sert le paquet {@code cp-rapprocher-deux-oeuvres}. */
    private static final String JEU = "bandit-manchot-rapprocher-deux-oeuvres-cp";

    private static final String REPRODUCTIONS = "src/main/resources/static/oeuvres";

    /** ADR-0013 obligation 3 : le jeu fautif commite de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest");

    private static final String LARGEUR = "en largeur";
    private static final String HAUTEUR = "en hauteur";
    private static final String CARRE = "carre";

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader().games().stream()
                .filter(jeu -> jeu.id().equals(JEU)).findFirst().orElseThrow().contenu();
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static Question avec(Question item, String reference, String bonneReponse, List<String> distracteurs) {
        return new Question(item.id(), item.enonce(), bonneReponse, distracteurs, item.indice(),
                item.explication(), item.partDesignee(), item.texteSupport(), Optional.of(reference),
                item.illustrationTexteAlternatif(), item.texteAVoixHaute(), item.tableauSupport());
    }

    /** Les formats des fichiers de ce repertoire, par identifiant de reproduction (nom sans extension). */
    static Map<String, String> formatsDe(List<Path> repertoires) {
        Map<String, String> formats = new LinkedHashMap<>();
        Map<String, byte[]> fichiers =
                ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest
                        .fichiersIndexesParNom(repertoires);
        fichiers.forEach((nom, octets) -> {
            int[] dimensions = ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest
                    .dimensionsJpeg(octets);
            if (dimensions.length == 2) {
                formats.put(nom.substring(0, nom.lastIndexOf('.')),
                        dimensions[0] > dimensions[1] ? LARGEUR : dimensions[0] < dimensions[1] ? HAUTEUR : CARRE);
            }
        });
        return formats;
    }

    private static List<Path> repertoiresDeReproductions() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Path> trouves = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path repertoire = racine.resolve(module).resolve(REPRODUCTIONS);
            if (Files.isDirectory(repertoire)) {
                trouves.add(repertoire);
            }
        }
        return trouves;
    }

    /** Le juge, pur : les formats lus et les items. Rend la liste des griefs, vide quand B5 tient. */
    static List<String> griefs(List<Question> items, Map<String, String> formats) {
        List<String> griefs = new ArrayList<>();
        for (Question item : items) {
            String reference = item.illustration().orElse("");
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            List<String> inconnues = new ArrayList<>();
            for (String oeuvre : propositions) {
                if (!formats.containsKey(oeuvre)) {
                    inconnues.add(oeuvre);
                }
            }
            if (!formats.containsKey(reference)) {
                inconnues.add(reference);
            }
            if (!inconnues.isEmpty()) {
                griefs.add("B5 : item " + item.id().value() + " : le format de " + inconnues
                        + " ne se lit dans aucun fichier de reproduction");
                continue;
            }
            for (String oeuvre : propositions) {
                if (formats.get(oeuvre).equals(CARRE)) {
                    griefs.add("B5 : item " + item.id().value() + " : " + oeuvre + " est carree, ni en largeur"
                            + " ni en hauteur : ce test ne sait pas la classer, son rendu dans la case egale"
                            + " est a ines-design et nadia-prof");
                }
            }
            String formatDeLaBonne = formats.get(item.bonneReponse());
            long memeFormat = propositions.stream().filter(oeuvre -> formats.get(oeuvre).equals(formatDeLaBonne)).count();
            if (memeFormat < 2) {
                griefs.add("B5 : item " + item.id().value() + " : la bonne oeuvre " + item.bonneReponse()
                        + " est la seule des trois propositions " + formatDeLaBonne + " : dans une case"
                        + " carree egale, son format la designerait");
            }
            if (formatDeLaBonne.equals(formats.get(reference))) {
                boolean unDistracteurLePartage = item.distracteurs().stream()
                        .anyMatch(oeuvre -> formats.get(oeuvre).equals(formats.get(reference)));
                if (!unDistracteurLePartage) {
                    griefs.add("B5 : item " + item.id().value() + " : la bonne oeuvre " + item.bonneReponse()
                            + " est la seule a partager le format de la reference " + reference + " ("
                            + formats.get(reference) + ") : regle 3 de nadia-prof");
                }
            }
        }
        return griefs;
    }

    @Test
    void aucuneBonneOeuvreNEstDesigneeParSonFormatSurLePaquetEtLesFichiersReels() {
        List<Question> items = items();
        Map<String, String> formats = formatsDe(repertoiresDeReproductions());

        assertThat(items).as("ADR-0003 : ce garde-fou ne prouve rien sans item").isNotEmpty();
        assertThat(formats.values()).as("les deux formats doivent exister parmi les reproductions reelles")
                .contains(LARGEUR, HAUTEUR);
        assertThat(griefs(items, formats)).isEmpty();
    }

    /** Un distracteur de l'item 3 echange contre une oeuvre de l'autre format : la bonne oeuvre est seule. */
    @Test
    void unDistracteurEchangeContreUneOeuvreDeLAutreFormatFaitEchouerLeGardeFou() {
        List<Question> items = items();
        Map<String, String> formats = formatsDe(repertoiresDeReproductions());
        Question item = items.stream()
                .filter(candidat -> {
                    String formatDeLaBonne = formats.get(candidat.bonneReponse());
                    long memeFormat = candidat.distracteurs().stream()
                            .filter(oeuvre -> formats.get(oeuvre).equals(formatDeLaBonne)).count();
                    return memeFormat == 1;
                })
                .findFirst().orElseThrow();
        String formatDeLaBonne = formats.get(item.bonneReponse());
        String partageur = item.distracteurs().stream()
                .filter(oeuvre -> formats.get(oeuvre).equals(formatDeLaBonne)).findFirst().orElseThrow();
        String autreFormat = formats.entrySet().stream()
                .filter(entree -> !entree.getValue().equals(formatDeLaBonne))
                .map(Map.Entry::getKey)
                .filter(oeuvre -> !oeuvre.equals(item.illustration().orElse("")))
                .filter(oeuvre -> !item.distracteurs().contains(oeuvre) && !oeuvre.equals(item.bonneReponse()))
                .findFirst().orElseThrow();
        List<String> distracteurs = new ArrayList<>(item.distracteurs());
        distracteurs.set(distracteurs.indexOf(partageur), autreFormat);
        List<Question> mutes = new ArrayList<>(items);
        mutes.set(mutes.indexOf(item), avec(item, item.illustration().orElseThrow(), item.bonneReponse(), distracteurs));

        assertThat(griefs(mutes, formats)).anyMatch(grief -> grief.contains("la seule des trois propositions"));
    }

    /** Un seul item mute : la bonne oeuvre devient la seule a partager le format de la reference. */
    @Test
    void uneBonneOeuvreSeuleAPartagerLeFormatDeLaReferenceFaitEchouerLeGardeFou() {
        Map<String, String> formats = formatsDe(List.of(CONTRE_EXEMPLE.resolve("oeuvres")));
        List<Question> items = items();
        Question item = items.get(0);
        Question mute = avec(item, "oeuvre-fautif-large-un", "oeuvre-fautif-large-deux",
                List.of("oeuvre-fautif-haute-un", "oeuvre-fautif-haute-deux"));

        assertThat(griefs(List.of(mute), formats))
                .anyMatch(grief -> grief.contains("la seule a partager le format de la reference"))
                .anyMatch(grief -> grief.contains("la seule des trois propositions"));
    }

    /** Une image carree n'est classee nulle part : elle fait rougir le test. */
    @Test
    void uneImageCarreeFaitEchouerLeGardeFouPlutotQuEtreClassee() {
        Map<String, String> formats = formatsDe(List.of(CONTRE_EXEMPLE.resolve("oeuvres")));
        Question item = items().get(0);
        Question mute = avec(item, "oeuvre-fautif-large-un", "oeuvre-fautif-large-deux",
                List.of("oeuvre-fautif-carree", "oeuvre-fautif-large-un"));

        assertThatThrownBy(() -> assertThat(griefs(List.of(mute), formats)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("est carree");
    }

    /** ADR-0013 obligation 3 : les formats du jeu fautif commite sont ceux que ses noms annoncent. */
    @Test
    void leJeuFautifCommitePorteUnFichierEnLargeurUnEnHauteurEtUnCarre() {
        Map<String, String> formats = formatsDe(List.of(CONTRE_EXEMPLE.resolve("oeuvres")));

        assertThat(formats)
                .containsEntry("oeuvre-fautif-large-un", LARGEUR)
                .containsEntry("oeuvre-fautif-large-deux", LARGEUR)
                .containsEntry("oeuvre-fautif-haute-un", HAUTEUR)
                .containsEntry("oeuvre-fautif-haute-deux", HAUTEUR)
                .containsEntry("oeuvre-fautif-carree", CARRE);
    }
}
