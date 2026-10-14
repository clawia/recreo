package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S40-EXTRACTION, le paquet {@code cm1-sciences-extraire-information}, ligne {@code SCI-CM1-20}
 * de "Sciences et technologie" (CM1).</b> Premier paquet servi a declarer {@code tableauSupport}
 * (ADR-0099). Encode DEC-0094 - les deux formes de document, texte et tableau, sont exigees sur le
 * paquet, pas sur chaque item - et le critere d'acceptation central de la ligne : l'information
 * demandee est <b>explicitement presente</b> dans le document, jamais deduite ni calculee.
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude scientifique de chaque fait cite (regime
 * alimentaire, etat de la matiere...) reste la garde de {@code nadia-prof}, de meme que la mention
 * de couverture partielle de la ligne ("document textuel et tableau seuls").</p>
 */
class PaquetCm1SciencesExtraireInformationTest {

    private static final String PAQUET = "cm1-sciences-extraire-information";

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

    private static String replier(String texte) {
        return texte.toLowerCase(Locale.ROOT);
    }

    /** DEC-0094 : la moitie texte du paquet est bien servie. */
    @Test
    void dec0094AuMoinsUnItemPorteUnTexteSupport() {
        assertThat(items().stream().filter(item -> item.texteSupport().isPresent()).toList())
                .as("DEC-0094 : au moins un item s'appuie sur un texteSupport")
                .isNotEmpty();
    }

    /** DEC-0094 : la moitie tableau du paquet est bien servie. */
    @Test
    void dec0094AuMoinsUnItemPorteUnTableauSupport() {
        assertThat(items().stream().filter(item -> item.tableauSupport().isPresent()).toList())
                .as("DEC-0094 : au moins un item s'appuie sur un tableauSupport")
                .isNotEmpty();
    }

    /** Chaque item propose exactement deux distracteurs, donc trois propositions. */
    @Test
    void chaqueItemPorteExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .isEqualTo(2);
        }
    }

    /**
     * Le critere central de la ligne : sur un item a texte, la bonne reponse est un extrait
     * recopiable tel quel du texteSupport - jamais deduite - et aucun distracteur ne s'y trouve, ce
     * qui garderait la question resoluble sans lire l'enonce.
     */
    @Test
    void surUnItemATexteLaBonneReponseEstUnExtraitLitteralEtAucunDistracteurNyFigure() {
        List<Question> itemsATexte = items().stream()
                .filter(item -> item.texteSupport().isPresent()).toList();
        assertThat(itemsATexte).isNotEmpty();

        for (Question item : itemsATexte) {
            String texte = replier(item.texteSupport().orElseThrow());
            assertThat(texte)
                    .as("item %s : la bonne reponse \"%s\" doit se lire telle quelle dans le"
                            + " texteSupport", item.id().value(), item.bonneReponse())
                    .contains(replier(item.bonneReponse()));
            for (String distracteur : item.distracteurs()) {
                assertThat(texte)
                        .as("item %s : le distracteur \"%s\" ne doit pas se lire dans le"
                                + " texteSupport, sous peine de rendre l'item resoluble sans lire"
                                + " la question", item.id().value(), distracteur)
                        .doesNotContain(replier(distracteur));
            }
        }
    }

    /**
     * Meme critere sur un item a grille : la bonne reponse est une cellule recopiable telle quelle
     * du tableauSupport, et chaque distracteur en est une autre cellule (ADR-0072 : pris dans le
     * meme document), jamais une valeur hors sujet.
     */
    @Test
    void surUnItemAGrilleLaBonneReponseEtSesDistracteursSontDesCellulesDuTableau() {
        List<Question> itemsAGrille = items().stream()
                .filter(item -> item.tableauSupport().isPresent()).toList();
        assertThat(itemsAGrille).isNotEmpty();

        for (Question item : itemsAGrille) {
            Set<String> cellules = new HashSet<>();
            item.tableauSupport().orElseThrow().lignes()
                    .forEach(ligne -> ligne.forEach(cellule -> cellules.add(replier(cellule))));

            assertThat(cellules)
                    .as("item %s : la bonne reponse \"%s\" doit etre une cellule du tableauSupport",
                            item.id().value(), item.bonneReponse())
                    .contains(replier(item.bonneReponse()));
            for (String distracteur : item.distracteurs()) {
                assertThat(cellules)
                        .as("item %s : le distracteur \"%s\" doit etre une cellule du"
                                + " tableauSupport (ADR-0072 : pris dans le meme document)",
                                item.id().value(), distracteur)
                        .contains(replier(distracteur));
            }
        }
    }

    /**
     * S54-OBS-SCI-CONSIGNE, critere 1 : la consigne du catalogue dit deja "Lis le texte ou le
     * tableau, puis reponds a la question." ; l'enonce ne la redit pas et commence par le document
     * dont il parle. Remettre la premiere phrase dans un item fait echouer ce test.
     */
    @Test
    void sciConsigneChaqueEnonceCommenceParLeDocumentSansRedireLaConsigne() {
        List<Question> items = items();
        assertThat(items).hasSize(12);

        for (Question item : items) {
            assertThat(item.enonce())
                    .as("item %s : l'enonce ne redit pas la consigne du catalogue",
                            item.id().value())
                    .doesNotContain("puis réponds à la question")
                    .matches("^D'après ce (tableau|texte),.*");
        }
    }
}
