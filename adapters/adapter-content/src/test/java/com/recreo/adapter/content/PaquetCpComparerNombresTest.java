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
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S9-3 (docs/backlog/backlog.md, section "Sprint 9") : le paquet "cp-comparer-nombres", seul
 * paquet neuf de cette story. Les proprietes generiques (indice non vide, trou homogene, aucun
 * item ne repete l'ecran d'un autre, au plus quatre propositions) sont deja gardees par les
 * garde-fous derives du repertoire de contenu ; ce fichier verifie les criteres d'acceptation
 * propres a S9-3, qu'aucun garde-fou generique ne connait : la forme de l'enonce, la justesse du
 * signe attendu, la repartition entre les trois signes, et la part d'ecarts petits.
 */
class PaquetCpComparerNombresTest {

    private static final String PAQUET = "cp-comparer-nombres";

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueEnonceOpposeDeuxEntiersDeZeroACentSeparesParLeMarqueurDeTrou() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            int[] nombres = nombresDe(item.enonce());
            assertThat(nombres[0]).as("premier nombre de '%s'", item.enonce()).isBetween(0, 100);
            assertThat(nombres[1]).as("second nombre de '%s'", item.enonce()).isBetween(0, 100);
        }
    }

    @Test
    void chaqueItemPropositionExactementLesTroisSignesJamaisUnNombre() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s'", item.enonce())
                    .containsExactlyInAnyOrder("<", ">", "=");
        }
    }

    @Test
    void leSigneDeChaqueItemEstLeSigneMathematiquementCorrect() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            int[] nombres = nombresDe(item.enonce());
            String attendu = nombres[0] < nombres[1] ? "<" : nombres[0] > nombres[1] ? ">" : "=";
            assertThat(item.bonneReponse()).as("signe attendu pour '%s'", item.enonce()).isEqualTo(attendu);
        }
    }

    @Test
    void chacunDesTroisSignesEstLaBonneReponseDAuMoinsUnTiersDesItems() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        int total = items.size();
        for (String signe : List.of("<", ">", "=")) {
            long compte = items.stream().filter(item -> item.bonneReponse().equals(signe)).count();
            assertThat(3 * compte).as("occurrences de '%s' sur %d items", signe, total).isGreaterThanOrEqualTo(total);
        }
    }

    @Test
    void lesItemsDontLaBonneReponseEstEgaliteRepresentesAuMoinsVingtPourCent() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        long egalites = items.stream().filter(item -> item.bonneReponse().equals("=")).count();
        assertThat(5 * egalites).as("part des egalites sur %d items", items.size())
                .isGreaterThanOrEqualTo(items.size());
    }

    @Test
    void auMoinsUnQuartDesComparaisonsStrictesOpposeDeuxNombresSepareesDeDixOuMoins() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        List<Question> comparaisonsStrictes = items.stream()
                .filter(item -> !item.bonneReponse().equals("=")).toList();
        long ecartsPetits = comparaisonsStrictes.stream().filter(item -> {
            int[] nombres = nombresDe(item.enonce());
            return Math.abs(nombres[0] - nombres[1]) <= 10;
        }).count();
        assertThat(4 * ecartsPetits).as("ecarts <= 10 sur %d comparaisons strictes", comparaisonsStrictes.size())
                .isGreaterThanOrEqualTo(comparaisonsStrictes.size());
    }

    private static int[] nombresDe(String enonce) {
        String[] parts = enonce.split(" ___ ");
        assertThat(parts).as("forme de l'enonce '%s'", enonce).hasSize(2);
        return new int[] {Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim())};
    }

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

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
