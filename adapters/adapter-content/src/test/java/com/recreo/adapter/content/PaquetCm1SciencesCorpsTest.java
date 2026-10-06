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
 * <b>S51-SCI-CORPS-CM1, le paquet {@code cm1-sciences-corps}, ligne {@code SCI-CM1-10} de "Sciences
 * et technologie" (CM1).</b> Conditions : arbitrage prealable de s30, sections 2.2 (ligne 10) et
 * 2.5, {@code squad/conformite/ARBITRAGE-PREALABLE-s30-...md}, et {@code BIA-4}.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, lues par le marqueur {@code # tache:} de chaque item,
 * dans le fichier brut : A, "dire ce qui est bon pour le corps" ; B, "dire a quoi sert un de ces
 * quatre". <b>FER-1</b> se lit sur le marqueur {@code # element:} des items de la tache A, dont la
 * bonne reponse est l'element lui-meme.
 *
 * <p>Ce test ne juge pas l'exactitude d'un fait, ni la frontiere avec les EPS et les paquets voisins
 * au-dela des mots qu'il declare ci-dessous : c'est la garde de {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesCorpsTest {

    private static final String PAQUET = "cm1-sciences-corps";

    private static final int NOMBRE_DE_MANCHES = 10;

    private static final int MINIMUM_PAR_TACHE = 3;

    private static final int DIVISEUR_SCI_3 = 6;

    /** FER-1 : les quatre elements du libelle. */
    private static final Set<String> ELEMENTS_DU_LIBELLE =
            Set.of("alimentation", "activite", "sommeil", "hygiene");

    private static final Pattern MARQUEUR_TACHE = Pattern.compile("# tache: ([AB])\\b");

    private static final Pattern MARQUEUR_ELEMENT = Pattern.compile("# element: (\\w+)");

    /** BIA-4 : jetons d'adresse a l'enfant, en mot entier. */
    private static final Pattern JETONS_D_ADRESSE = Pattern.compile(
            "\\b(tu|ta|ton|tes|toi|te|vous|votre|vos|nous|notre|nos)\\b",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    /** BIA-4 : aucun repas, lit ou routine suppose. */
    private static final Pattern REPAS_LIT_ROUTINE = Pattern.compile(
            "\\b(repas|petit[- ]d[ée]jeuner|d[ée]jeuner|d[îi]ner|go[uû]ter|cantine|lit|chambre"
                    + "|coucher|r[ée]veil|chaque jour|tous les jours|le soir|le matin)\\b",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    /** EPS-9 et frontiere avec cm1-sciences-nutrition : aucun mot des effets de l'effort ni d'organe. */
    private static final Pattern FRONTIERES = Pattern.compile(
            "\\b(endurance|essouffl\\w*|souffle|c[oœ]eur|estomac|poumons?|soif|transpir\\w*"
                    + "|renforc\\w*|progress\\w*|entra[iî]n\\w*)\\b",
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
        try (InputStream flux = PaquetCm1SciencesCorpsTest.class
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

    /** FER-1 : les quatre elements sont chacun la bonne reponse d'au moins un item de la tache A. */
    @Test
    void fer1LesQuatreElementsSontChacunEnBonneReponseDUnItemDeLaTacheA() {
        List<String> taches = marqueurs(MARQUEUR_TACHE);
        List<String> elements = marqueurs(MARQUEUR_ELEMENT);
        assertThat(ELEMENTS_DU_LIBELLE).containsAll(elements);

        Set<String> servisEnTacheA = new HashSet<>();
        for (int i = 0; i < taches.size(); i++) {
            if (taches.get(i).equals("A")) {
                servisEnTacheA.add(elements.get(i));
            }
        }
        assertThat(servisEnTacheA)
                .as("FER-1 : alimentation, activite, sommeil et hygiene sont chacun en bonne"
                        + " reponse d'un item de la tache A")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    @Test
    void fer3LesDeuxTachesPortentChacuneAuMoinsTroisItems() {
        List<String> taches = marqueurs(MARQUEUR_TACHE);
        long a = taches.stream().filter("A"::equals).count();
        long b = taches.stream().filter("B"::equals).count();

        assertThat(Math.min(a, b))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
        assertThat(a + b).as("chaque item porte une tache et une seule").isEqualTo(items().size());
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
    void sci3AuPlusUnSixiemeDesBonnesReponsesPortentUneValeurNumerique() {
        long numeriques = items().stream()
                .filter(item -> VALEUR_NUMERIQUE.matcher(item.bonneReponse()).find()).count();

        assertThat(numeriques)
                .as("SCI-3 : au plus un item sur %d porte une valeur numerique en bonne reponse",
                        DIVISEUR_SCI_3)
                .isLessThanOrEqualTo(items().size() / DIVISEUR_SCI_3);
    }

    /** BIA-4 : aucun jeton d'adresse a l'enfant, sur tout ce que l'enfant lit. */
    @Test
    void bia4AucunTexteLuParLEnfantNeContientUnJetonDAdresse() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(JETONS_D_ADRESSE.matcher(texte).find())
                        .as("item '%s' : BIA-4 interdit tout jeton d'adresse, texte \"%s\"",
                                item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** BIA-4 : aucun repas, lit ou routine suppose. */
    @Test
    void bia4AucunTexteLuParLEnfantNeSupposeUnRepasUnLitOuUneRoutine() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(REPAS_LIT_ROUTINE.matcher(texte).find())
                        .as("item '%s' : BIA-4 interdit de supposer un repas, un lit ou une"
                                + " routine, texte \"%s\"", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** EPS-9 et frontiere avec SCI-CM1-09 : aucun mot d'effet de l'effort ni d'organe. */
    @Test
    void eps9AucunTexteNeSertUnEffetDeLEffortNiUnOrgane() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(FRONTIERES.matcher(texte).find())
                        .as("item '%s' : EPS-9 interdit les effets de l'effort et les organes de"
                                + " nutrition, texte \"%s\"", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** Mise a l'epreuve : les trois motifs mordent bien un texte fautif. */
    @Test
    void lesTroisMotifsMordentUnTexteFautif() {
        assertThat(JETONS_D_ADRESSE.matcher("Que manges-tu ? Ton repas").find()).isTrue();
        assertThat(REPAS_LIT_ROUTINE.matcher("avant de se mettre au lit").find()).isTrue();
        assertThat(REPAS_LIT_ROUTINE.matcher("des aliments variés").find()).isFalse();
        assertThat(FRONTIERES.matcher("le cœur est renforcé").find()).isTrue();
        assertThat(FRONTIERES.matcher("à faire travailler les muscles").find()).isFalse();
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
    void laLigneDeReferentielEstSciCm110EtLeLibelleEstCopieConforme() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);

        assertThat(contenu.descriptor().competence())
                .isEqualTo("Relier l'alimentation, l'activité physique, le sommeil et l'hygiène au"
                        + " bon fonctionnement du corps.");
        assertThat(texteBrutDuPaquet()).contains("lignesDeReferentiel: [\"SCI-CM1-10\"]");
    }
}
