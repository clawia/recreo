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
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S17-2 (`DEC-0053`, arbitrage prealable de `nadia-prof` du 2026-08-19,
 * `ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`) : le paquet "cm1-comprehension".
 * Matiere premiere refusee (`contenu-repris/quiz/cm1-comprehension.yml`, huit items dont la bonne
 * reponse etait recopiee mot pour mot du texte) - ce paquet est integralement neuf.
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (comptage, forme du descripteur,
 * absence de recopie litterale dans les propositions - COMP-1 - et le plancher de `COMP-3`) : la
 * justesse du sens de chaque texte-support, la plausibilite des distracteurs sans avoir lu le texte
 * (COMP-2) et la charge de lecture d'un CM1 restent le jugement de `nadia-prof`
 * (`FICHE-bandit-manchot-comprehension-cm1.md`), en jouant - aucun automate de ce depot ne les porte.</p>
 *
 * <p><b>COMP-1 (bloquante).</b> "Toutes les propositions d'un item emploient des mots presents dans
 * le texte, ou aucune ne le fait - jamais une seule." Ce paquet choisit systematiquement la seconde
 * branche : chaque bonne reponse et chaque distracteur est une reformulation, jamais un mot recopie
 * du texte-support. {@link #aucuneCompositionNeRecopieUnMotDuTexteSupport()} le verifie sur les
 * vingt items reels, mot par mot (quatre lettres et plus, hors mots-outils), pour que cette
 * propriete reste gardee si un item est modifie plus tard.</p>
 *
 * <p><b>COMP-3 (bloquante).</b> "Au moins un tiers des items exigent de relier deux informations
 * distinctes du texte, et pour ceux-la la bonne reponse n'est pas un fragment recopie du support."
 * Le plancher exact sur vingt items est sept (arrondi superieur d'un tiers). Ce que ce garde-fou
 * peut prouver : le paquet servi marque bien vingt items "# COMP-3" dans le YAML - un marqueur
 * qu'aucun code de production ne lit, pose pour que `nadia-prof` retrouve directement, en jouant,
 * quels items sont concus pour cette condition et les deux informations que chacun relie (le
 * commentaire porte les deux). Ce que ce garde-fou ne peut pas prouver : que la liaison entre les
 * deux informations est reelle et que la bonne reponse n'est pas devinable sans elle - c'est un
 * jugement de sens, donc le sien.</p>
 */
class PaquetCm1ComprehensionTest {

    private static final String PAQUET = "cm1-comprehension";
    private static final String RESSOURCE_YAML = "/contenu/cm1-comprehension.yaml";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** COMP-3 : arrondi superieur d'un tiers de vingt items. */
    private static final int COMP_3_PLANCHER = 7;

    private static final Set<String> MOTS_OUTILS = Set.of(
            "le", "la", "les", "un", "une", "des", "de", "du", "au", "aux", "et", "ou", "il", "elle",
            "ils", "elles", "son", "sa", "ses", "sur", "dans", "avant", "apres", "que", "qui", "pour",
            "avec", "en", "l", "d", "s", "se", "ce", "cette", "ces", "toute", "tout", "tous", "toutes",
            "pres", "juste", "encore", "depuis", "chez", "comme", "sans", "plus", "tres",
            "probablement", "quel", "quelle", "va", "etre", "est", "sont", "ont");

    private static final Pattern MOT = Pattern.compile("\\p{L}+");

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

    @Test
    void lePaquetCompteExactementVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("le compte est garde exactement a %d", NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCm1LectureEtComprehension() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Lecture et compréhension de l'écrit");
        assertThat(descripteur.competence())
                .contains("Comprendre un texte narratif ou documentaire");
    }

    @Test
    void chaqueItemPorteUnTexteSupportNonVide() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.texteSupport())
                    .as("'%s' : ce paquet promet un texte-support sur chacun de ses vingt items"
                            + " (S17-2, ADR-0050) - c'est sa raison d'etre", item.enonce())
                    .isPresent();
            assertThat(item.texteSupport().orElseThrow())
                    .as("'%s' : le texte-support ne doit pas etre vide", item.enonce())
                    .isNotBlank();
        }
    }

    /** Les mots significatifs (4 lettres et plus, hors mots-outils) d'un texte, en minuscules. */
    private static Set<String> motsSignificatifs(String texte) {
        Set<String> mots = new TreeSet<>();
        Matcher trouve = MOT.matcher(texte.toLowerCase());
        while (trouve.find()) {
            String mot = trouve.group();
            if (mot.length() >= 4 && !MOTS_OUTILS.contains(mot)) {
                mots.add(mot);
            }
        }
        return mots;
    }

    /**
     * COMP-1 : "toutes les propositions d'un item emploient des mots presents dans le texte, ou
     * aucune ne le fait - jamais une seule". Ce paquet choisit la seconde branche pour ses vingt
     * items ; cette clause verifie qu'aucune proposition (bonne reponse ou distracteur) ne recopie
     * un mot significatif du texte-support de son propre item.
     */
    @Test
    void aucuneCompositionNeRecopieUnMotDuTexteSupport() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            Set<String> motsDuTexte = motsSignificatifs(item.texteSupport().orElseThrow());
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            for (String proposition : propositions) {
                Set<String> partages = new TreeSet<>(motsSignificatifs(proposition));
                partages.retainAll(motsDuTexte);
                if (!partages.isEmpty()) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' recopie " + partages + " du texte-support (COMP-1)");
                }
            }
        }
        assertThat(defauts)
                .as("COMP-1 : aucune proposition ne doit recopier un mot du texte-support"
                        + " qu'aucune autre proposition du meme item ne recopie aussi")
                .isEmpty();
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            assertThat(propositions)
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    /**
     * COMP-3 : le marqueur "# COMP-3" est pose dans le YAML servi, a cote de l'enonce de chaque
     * item concu pour relier deux informations distinctes du texte - jamais lu par
     * {@code QuestionContentSourceFactory}, qui ignore les commentaires YAML comme toute cle
     * qu'elle ne connait pas. Cette clause lit le fichier reel, pas une copie, pour que le compte
     * ne puisse pas dater d'avant la derniere modification du paquet.
     */
    @Test
    void auMoinsSeptItemsSurVingtSontMarquesCOMP3() throws IOException {
        String yaml;
        try (InputStream flux = PaquetCm1ComprehensionTest.class.getResourceAsStream(RESSOURCE_YAML)) {
            assertThat(flux).as("%s introuvable sur le classpath", RESSOURCE_YAML).isNotNull();
            yaml = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        }
        long marques = yaml.lines().filter(ligne -> ligne.contains("# COMP-3")).count();

        assertThat(marques)
                .as("COMP-3 (nadia-prof, plancher d'un tiers) : au moins %d items sur %d doivent"
                        + " relier deux informations distinctes du texte, marques '# COMP-3' dans"
                        + " le YAML servi", COMP_3_PLANCHER, NOMBRE_D_ITEMS_ATTENDU)
                .isGreaterThanOrEqualTo(COMP_3_PLANCHER);
    }
}
