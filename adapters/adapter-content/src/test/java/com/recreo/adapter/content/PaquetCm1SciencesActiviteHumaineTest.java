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
 * <b>S51-SCI-ACTIVITE-HUMAINE-CM1, le paquet {@code cm1-sciences-activite-humaine}, ligne
 * {@code SCI-CM1-18} de "Sciences et technologie" (CM1).</b> Referentiel :
 * {@code docs/backlog/backlog.md}, section "Sprint 51" ; conditions : arbitrage prealable de s30,
 * sections 2.2 (ligne 18) et 2.5, {@code ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-
 * reserves.md}.
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : <b>"reconnaitre l'effet d'une activite"</b> (la bonne
 * reponse nomme un effet) et <b>"choisir le geste qui preserve"</b> (la bonne reponse nomme un
 * geste). Elles se lisent par le marqueur {@code # element:} de chaque item, dans le fichier brut.
 *
 * <p><b>SCI-2</b> s'encode ici sur des jetons : la liste des jetons d'adresse a l'enfant est celle
 * que ce test declare ({@link #JETONS_D_ADRESSE}), fixee par
 * {@code nadia-prof} (question Q2 de s51), et non une regle d'architecture. Ce test ne juge pas
 * qu'un item decrive un mecanisme exact ni que son geste soit possible : c'est la garde de
 * {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesActiviteHumaineTest {

    private static final String PAQUET = "cm1-sciences-activite-humaine";

    /** FER-2 : au moins nombreDeManches + 1 items (nombreDeManches = 10, catalogue.yaml). */
    private static final int NOMBRE_DE_MANCHES = 10;

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** SCI-3 : au plus n/6 items dont la bonne reponse porte un chiffre. */
    private static final int DIVISEUR_SCI_3 = 6;

    /** FER-1 : les deux elements du libelle, un effet et un geste. */
    private static final Set<String> ELEMENTS_DU_LIBELLE = Set.of("effet", "geste");

    private static final Pattern MARQUEUR_ELEMENT = Pattern.compile("# element: (\\w+)");

    /**
     * SCI-2 : jetons d'adresse a l'enfant ou a son foyer, en mot entier. Liste de nadia-prof (Q2) : tu, te,
     * toi, ta, ton, tes, l'elision t', vous, votre, vos, nous, notre, nos ; "on" reste admis.
     */
    private static final Pattern JETONS_D_ADRESSE = Pattern.compile(
            "\\b(tu|ta|ton|tes|toi|te|vous|votre|vos|nous|notre|nos)\\b|\\bt['’]",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    /** SCI-2 : les cinq objets que la condition interdit de presenter comme une responsabilite. */
    private static final Pattern OBJETS_DU_FOYER = Pattern.compile(
            "\\b(chauffage|voitures?|voyages?|voyager|d[ée]chets?|courses)\\b",
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
        try (InputStream flux = PaquetCm1SciencesActiviteHumaineTest.class
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

    private static List<String> elementsParItem() {
        List<String> elements = new ArrayList<>();
        for (String ligne : lignesDItem()) {
            Matcher marqueur = MARQUEUR_ELEMENT.matcher(ligne);
            assertThat(marqueur.find()).as("ligne '%s' : porte un marqueur # element:", ligne)
                    .isTrue();
            elements.add(marqueur.group(1));
        }
        return elements;
    }

    /** Tout le texte que l'enfant lit : enonce, propositions, indice, explication. */
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
    void fer1UnEffetEtUnGesteSontChacunEnBonneReponseDAuMoinsUnItem() {
        assertThat(ELEMENTS_DU_LIBELLE).containsAll(elementsParItem());
        assertThat(new HashSet<>(elementsParItem()))
                .as("FER-1 : l'effet et le geste sont tous deux servis")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    @Test
    void fer3LesDeuxTachesPortentChacuneAuMoinsTroisItems() {
        List<String> elements = elementsParItem();
        long effets = elements.stream().filter("effet"::equals).count();
        long gestes = elements.stream().filter("geste"::equals).count();

        assertThat(Math.min(effets, gestes))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
        assertThat(effets + gestes).as("chaque item porte une tache et une seule")
                .isEqualTo(items().size());
    }

    @Test
    void lePaquetPorteExactementDouzeItems() {
        assertThat(items()).hasSize(12);
        assertThat(lignesDItem()).hasSize(12);
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

    /** SCI-2 : aucun jeton d'adresse a l'enfant, sur tout ce que l'enfant lit. */
    @Test
    void sci2AucunTexteLuParLEnfantNeContientUnJetonDAdresse() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                Matcher jeton = JETONS_D_ADRESSE.matcher(texte);
                assertThat(jeton.find())
                        .as("item '%s' : SCI-2 interdit tout jeton d'adresse, texte \"%s\"",
                                item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** SCI-2 : ni chauffage, ni voiture, ni voyages, ni dechets, ni courses. */
    @Test
    void sci2AucunTexteLuParLEnfantNeNommeLesObjetsDuFoyer() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                Matcher objet = OBJETS_DU_FOYER.matcher(texte);
                assertThat(objet.find())
                        .as("item '%s' : SCI-2 interdit de nommer chauffage, voiture, voyages,"
                                + " dechets ou courses, texte \"%s\"", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** Garde de la mise a l'epreuve : les deux motifs mordent bien un texte fautif. */
    @Test
    void sci2LesDeuxMotifsMordentUnTexteFautif() {
        assertThat(JETONS_D_ADRESSE.matcher("Que fais-tu chez toi ?").find()).isTrue();
        assertThat(JETONS_D_ADRESSE.matcher("Ta famille choisit").find()).isTrue();
        assertThat(JETONS_D_ADRESSE.matcher("Que t'a-t-on dit").find()).isTrue();
        assertThat(JETONS_D_ADRESSE.matcher("on peut le faire").find()).isFalse();
        assertThat(JETONS_D_ADRESSE.matcher("une station d'épuration").find()).isFalse();
        assertThat(OBJETS_DU_FOYER.matcher("Les déchets de la maison").find()).isTrue();
        assertThat(OBJETS_DU_FOYER.matcher("réparer les canalisations").find()).isFalse();
    }

    @Test
    void his3ChaqueLigneDItemPorteUnMarqueurHis3NonVideEtJamaisHis1() {
        for (String ligne : lignesDItem()) {
            assertThat(ligne)
                    .as("ligne '%s' : ne porte jamais le marqueur HIS-1", ligne)
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
    void laLigneDeReferentielEstSciCm118EtLeLibelleEstCopieConforme() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);

        assertThat(contenu.descriptor().competence())
                .isEqualTo("Identifier un effet de l'activité humaine sur l'environnement et un"
                        + " geste de préservation des ressources.");
        assertThat(texteBrutDuPaquet()).contains("lignesDeReferentiel: [\"SCI-CM1-18\"]");
    }
}
