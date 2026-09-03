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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S27-3, arbitrage prealable de `nadia-prof` du 2026-09-02
 * (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, branche `matieres-absentes`,
 * commit `fcdc365`, section 5 et section 7.2) : le paquet "cm1-histoire-geographie", premiere
 * matiere neuve du produit sur ce referentiel. Ce paquet OUVRE des lignes de matrice, il n'en FERME
 * aucune (section 8.4 de l'arbitrage).
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (comptage, forme du descripteur, trois
 * propositions distinctes par item, et le plafond de `HIS-1`) : la justesse de chaque fait historique
 * ou geographique (`HIS-3`), l'absence de double reponse defendable (`HIS-2`) et l'absence de
 * jugement de valeur sur un personnage (`BIA-5`) restent un jugement de sens que ce depot confie a
 * `nadia-prof`, en jouant - aucun automate ne les porte, meme limite ecrite pour `COMP-2` sur
 * `PaquetCm1ComprehensionTest`.</p>
 *
 * <p><b>HIS-1 (bloquante).</b> "Au plus un tiers des items demande une date, un nom ou un chiffre a
 * restituer." Le marqueur "# HIS-1: restitution" est pose dans le YAML servi, a cote de l'enonce de
 * chaque item concu pour cette categorie - jamais lu par {@code QuestionContentSourceFactory}, qui
 * ignore les commentaires YAML comme toute cle qu'elle ne connait pas. Meme patron que le marqueur
 * "# COMP-3" de {@code PaquetCm1ComprehensionTest}. Ce garde-fou verifie que le paquet servi marque
 * au plus six items sur vingt (arrondi inferieur d'un tiers) et que chaque item porte exactement un
 * marqueur "# HIS-1: &lt;type&gt;", pour que le compte ne puisse pas dater d'avant la derniere
 * modification du paquet et qu'aucun item ne reste hors classement.</p>
 */
class PaquetCm1HistoireGeographieTest {

    private static final String PAQUET = "cm1-histoire-geographie";
    private static final String RESSOURCE_YAML = "/contenu/cm1-histoire-geographie.yaml";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** HIS-1 : arrondi inferieur d'un tiers de vingt items. */
    private static final int HIS_1_PLAFOND_RESTITUTION = 6;

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
                .as("le compte est garde exactement a %d, plafond de nadia-prof pour un premier"
                        + " paquet de matiere neuve", NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstHistoireGeographieCm1() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Histoire-géographie");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Repères historiques et géographiques");
        assertThat(descripteur.competence()).contains("référentiel");
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
     * HIS-1 : le marqueur "# HIS-1: &lt;type&gt;" est pose dans le YAML servi, a cote de l'enonce de
     * chaque item - jamais lu par {@code QuestionContentSourceFactory}. Cette clause lit le fichier
     * reel, pas une copie, pour que le compte ne puisse pas dater d'avant la derniere modification du
     * paquet.
     */
    @Test
    void auPlusUnTiersDesItemsSontDeLaRestitution() throws IOException {
        List<String> lignesEnonce = ligneDesEnonces();

        long marquesRestitution = lignesEnonce.stream()
                .filter(ligne -> ligne.contains("# HIS-1: restitution")).count();
        long nonClasses = lignesEnonce.stream()
                .filter(ligne -> !ligne.contains("# HIS-1:")).count();

        assertThat(nonClasses)
                .as("HIS-1 : chaque item du paquet servi doit porter un marqueur '# HIS-1: <type>'"
                        + " pour que le compte reste verifiable")
                .isZero();
        assertThat(marquesRestitution)
                .as("HIS-1 (nadia-prof) : au plus un tiers des items d'un paquet d'histoire peut"
                        + " demander une date, un nom ou un chiffre a restituer - soit au plus %d sur"
                        + " %d ici, marques '# HIS-1: restitution' dans le YAML servi",
                        HIS_1_PLAFOND_RESTITUTION, NOMBRE_D_ITEMS_ATTENDU)
                .isLessThanOrEqualTo(HIS_1_PLAFOND_RESTITUTION);
    }

    /** Les lignes du YAML servi qui declarent un enonce d'item, une par item. */
    private static List<String> ligneDesEnonces() throws IOException {
        String yaml;
        try (InputStream flux = PaquetCm1HistoireGeographieTest.class.getResourceAsStream(RESSOURCE_YAML)) {
            assertThat(flux).as("%s introuvable sur le classpath", RESSOURCE_YAML).isNotNull();
            yaml = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (UncheckedIOException echec) {
            throw echec.getCause();
        }
        return yaml.lines().filter(ligne -> ligne.contains("- enonce:")).toList();
    }
}
