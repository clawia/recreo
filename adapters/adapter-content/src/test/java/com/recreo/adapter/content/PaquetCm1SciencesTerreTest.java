package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S51-SCI-TERRE-CM1, le paquet {@code cm1-sciences-terre-jour-nuit-saisons}, ligne
 * {@code SCI-CM1-15} de "Sciences et technologie" (CM1).</b> Conditions : arbitrage prealable de s30, sections 2.2 (ligne 15) et
 * 2.5, {@code ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-
 * reserves.md}, dont {@code SCI-1} et {@code SCI-4}.
 *
 * <p>Taches lues par le marqueur {@code # tache:} de chaque item, dans le fichier brut : A,
 * "expliquer le jour et la nuit" ; B, "expliquer les saisons" (FER-3, au moins trois items chacune) ;
 * C, "situer la Terre dans le systeme solaire". <b>FER-1</b> se lit sur le marqueur
 * {@code # element:} (systeme, jour, nuit, saisons).
 *
 * <p>Ce test ne juge pas l'exactitude d'un fait ni la coherence avec {@code cp-qlm-saisons}
 * (CYC-1) : c'est la garde de {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesTerreTest {

    private static final String PAQUET = "cm1-sciences-terre-jour-nuit-saisons";

    private static final int NOMBRE_DE_MANCHES = 10;

    private static final int MINIMUM_PAR_TACHE = 3;

    private static final int DIVISEUR_SCI_3 = 6;

    /** FER-1 : les quatre elements du libelle. */
    private static final Set<String> ELEMENTS_DU_LIBELLE =
            Set.of("systeme", "jour", "nuit", "saisons");

    private static final Pattern MARQUEUR_TACHE = Pattern.compile("# tache: ([ABC])\\b");

    private static final Pattern MARQUEUR_ELEMENT = Pattern.compile("# element: (\\w+)");

    /** SCI-3 : une bonne reponse qui demande une duree ou un chiffre. */
    private static final Pattern VALEUR_A_RESTITUER = Pattern.compile(
            "\\d|\\b(heures?|jours?|semaines?|mois|ann[ée]es?|ans)\\b",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    private static final Pattern VALEUR_NUMERIQUE = Pattern.compile("\\d");

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!]");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
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

    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1SciencesTerreTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static List<String> lignesDItem() {
        return texteBrutDuPaquet().lines().filter(ligne -> ligne.contains("- enonce:")).toList();
    }

    private static List<String> marqueurs(Pattern motif) {
        List<String> valeurs = new ArrayList<>();
        for (String ligne : lignesDItem()) {
            Matcher marqueur = motif.matcher(ligne);
            assertThat(marqueur.find()).as("ligne '%s' : porte le marqueur %s", ligne, motif)
                    .isTrue();
            valeurs.add(marqueur.group(1));
        }
        return valeurs;
    }

    private static List<String> textesLusParLEnfant(Question item) {
        List<String> textes = new ArrayList<>();
        textes.add(item.enonce());
        textes.add(item.bonneReponse());
        textes.addAll(item.distracteurs());
        item.indice().ifPresent(textes::add);
        item.explication().ifPresent(textes::add);
        return textes;
    }

    @Test
    void fer2AuMoinsNombreDeManchesPlusUnItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items (nombreDeManches + 1)", NOMBRE_DE_MANCHES + 1)
                .isGreaterThanOrEqualTo(NOMBRE_DE_MANCHES + 1);
    }

    @Test
    void lePaquetPorteExactementDouzeItems() {
        assertThat(items()).hasSize(12);
        assertThat(lignesDItem()).hasSize(12);
    }

    @Test
    void fer1LesQuatreElementsDuLibelleSontServis() {
        List<String> elements = marqueurs(MARQUEUR_ELEMENT);
        assertThat(ELEMENTS_DU_LIBELLE).containsAll(elements);
        assertThat(new HashSet<>(elements)).as("FER-1 : les quatre elements sont servis")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    @Test
    void fer3LesDeuxTachesPortentChacuneAuMoinsTroisItems() {
        List<String> taches = marqueurs(MARQUEUR_TACHE);
        long a = taches.stream().filter("A"::equals).count();
        long b = taches.stream().filter("B"::equals).count();
        long c = taches.stream().filter("C"::equals).count();

        assertThat(Math.min(a, b))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
        assertThat(a + b + c).as("chaque item porte une tache et une seule").isEqualTo(items().size());
    }

    @Test
    void sci4ChaqueItemPorteDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus (SCI-4)", item.enonce())
                    .hasSize(2);
        }
    }

    @Test
    void sci1ChaqueIndiceCompteUnePhraseEtNeNommeNiLaBonneReponseNiTaReponse() {
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(() -> new AssertionError(
                    "item '" + item.enonce() + "' : indice attendu"));
            Matcher fins = FIN_DE_PHRASE.matcher(indice);
            int occurrences = 0;
            while (fins.find()) {
                occurrences++;
            }
            assertThat(occurrences)
                    .as("item '%s' : l'indice compte une seule phrase (SCI-1) - \"%s\"",
                            item.enonce(), indice)
                    .isEqualTo(1);
            assertThat(indice.strip().endsWith("."))
                    .as("item '%s' : l'indice se termine par un point", item.enonce()).isTrue();
            assertThat(indice)
                    .as("item '%s' : l'indice ne nomme pas la bonne reponse (SCI-1)", item.enonce())
                    .doesNotContain(item.bonneReponse());
            assertThat(indice.toLowerCase(Locale.FRENCH))
                    .as("item '%s' : l'indice ne dit jamais \"ta reponse\" (SCI-1)", item.enonce())
                    .doesNotContain("ta réponse")
                    .doesNotContain("ta reponse");
        }
    }

    @Test
    void sci3AuPlusUnSixiemeDesBonnesReponsesRestituentUneValeur() {
        long numeriques = items().stream()
                .filter(item -> VALEUR_A_RESTITUER.matcher(item.bonneReponse()).find()).count();

        assertThat(numeriques)
                .as("SCI-3 : au plus un item sur %d demande une valeur a restituer", DIVISEUR_SCI_3)
                .isLessThanOrEqualTo(items().size() / DIVISEUR_SCI_3);
    }

    /** SCI-1 : la cause des saisons est l'inclinaison de l'axe, jamais la distance au Soleil. */
    @Test
    void sci1LaCauseDesSaisonsEstLInclinaisonDeLAxeJamaisLaDistance() {
        Question cause = items().stream()
                .filter(item -> item.enonce().startsWith("Pourquoi y a-t-il des saisons"))
                .findFirst().orElseThrow(() -> new AssertionError("item de la cause des saisons"));

        assertThat(cause.bonneReponse()).isEqualTo("l'inclinaison de l'axe de la Terre");
        assertThat(cause.distracteurs()).contains("la Terre est plus près du Soleil en été");
        assertThat(cause.distracteurs()).hasSize(2);
        assertThat(cause.indice().orElseThrow())
                .isEqualTo("La distance de la Terre au Soleil change très peu au cours de l'année.");
        for (Question item : items()) {
            assertThat(item.bonneReponse().toLowerCase(Locale.FRENCH))
                    .as("aucune bonne reponse n'attribue les saisons a la distance au Soleil")
                    .doesNotContain("plus près du soleil")
                    .doesNotContain("plus loin du soleil")
                    .doesNotContain("distance");
        }
    }

    @Test
    void his3ChaqueLigneDItemPorteUnMarqueurHis3NonVideEtJamaisHis1() {
        for (String ligne : lignesDItem()) {
            assertThat(ligne).as("ligne '%s' : ne porte jamais le marqueur HIS-1", ligne)
                    .doesNotContain("# HIS-1:");
            int index = ligne.indexOf("# HIS-3:");
            assertThat(index).as("ligne '%s' : porte un commentaire # HIS-3:", ligne)
                    .isGreaterThan(-1);
            assertThat(ligne.substring(index + "# HIS-3:".length()).strip())
                    .as("ligne '%s' : le commentaire HIS-3 porte un texte non vide", ligne)
                    .isNotEmpty();
        }
    }

    @Test
    void laLigneDeReferentielEstSciCm115EtLeLibelleEstCopieConforme() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);

        assertThat(contenu.descriptor().competence())
                .isEqualTo("Situer la Terre dans le système solaire et relier les mouvements de la Terre au"
                        + " jour, à la nuit et aux saisons.");
        assertThat(texteBrutDuPaquet()).contains("lignesDeReferentiel: [\"SCI-CM1-15\"]");
    }
}
