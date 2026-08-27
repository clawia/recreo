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
 * S21-9 (`squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 3,
 * conditions `SEG-1` a `SEG-9`) : le paquet "cp-syllabes". Francais CP, "Denombrer les syllabes
 * orales d'un mot (segmentation syllabique)" - premiere ligne couverte du domaine "Langage oral et
 * conscience phonologique" (`squad/programme/MATRICE-couverture.md`, ligne 485).
 *
 * <p>Ce fichier ne verifie que ce qui est mecanisable. La stabilite de chaque coupe syllabique
 * (`SEG-1`), le dechiffrage effectif par un enfant de CP (`SEG-2`) et la qualite de chaque indice
 * (`SEG-7`) restent la seule garde de `nadia-prof` (arbitrage prealable, section 7) - aucun
 * classifieur ne les remplace ici.</p>
 */
class PaquetCpSyllabesTest {

    private static final String PAQUET = "cp-syllabes";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    private static final Set<String> PLAGE_ATTENDUE = Set.of("1", "2", "3");

    @Test
    void lePaquetCompteAuMoinsSeizeItems() {
        assertThat(tousLesItems(paquet())).hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_ATTENDU);
    }

    /**
     * `SEG-3` : la plage des reponses est 1, 2 et 3, et les trois propositions sont ces trois
     * memes valeurs a chaque item - aucun cas supplementaire, l'etiquette est la reponse.
     */
    @Test
    void chaqueItemProposeExactementLesValeursUnDeuxTrois() {
        for (Question item : tousLesItems(paquet())) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item \"%s\" : les trois seules propositions possibles sont 1, 2 et 3 (SEG-3)",
                            item.enonce())
                    .isEqualTo(PLAGE_ATTENDUE);
        }
    }

    /**
     * `SEG-6` : aucune valeur de reponse n'est correcte sur plus de la moitie des items, et chacune
     * l'est sur au moins un quart. Sur seize items, cela fait entre 4 et 8 items par valeur.
     */
    @Test
    void laRepartitionDesTroisValeursRespecteLeSeuilDUnQuartAUneMoitie() {
        List<Question> items = tousLesItems(paquet());
        int total = items.size();

        for (String valeur : PLAGE_ATTENDUE) {
            long compte = items.stream().filter(item -> valeur.equals(item.bonneReponse())).count();
            assertThat(compte)
                    .as("SEG-6 : la valeur %s est correcte sur %d items sur %d, au moins un quart attendu",
                            valeur, compte, total)
                    .isGreaterThanOrEqualTo((long) Math.ceil(total / 4.0));
            assertThat(compte)
                    .as("SEG-6 : la valeur %s est correcte sur %d items sur %d, pas plus de la moitie attendue",
                            valeur, compte, total)
                    .isLessThanOrEqualTo(total / 2);
        }
    }

    @Test
    void lesEnoncesSontDistincts() {
        List<String> enonces = tousLesItems(paquet()).stream().map(Question::enonce).toList();
        assertThat(Set.copyOf(enonces))
                .as("chaque mot du paquet n'apparait qu'une seule fois")
                .hasSize(enonces.size());
    }

    /**
     * Reserve routee a theo-archi et camille-po (arbitrage prealable, section 8) : ce paquet ne
     * declare ni graphie ni explication - l'enseignement est la segmentation elle-meme, qui ne peut
     * pas se loger dans l'indice sans donner le compte.
     */
    @Test
    void aucunItemNePorteDExplication() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.explication())
                    .as("item \"%s\" : cp-syllabes ne declare pas de graphie, donc pas d'explication"
                            + " (ChaquePaquetExpliqueSesItemsAVariationTest)", item.enonce())
                    .isEmpty();
        }
    }

    @Test
    void leDescripteurEstFrancaisCpLangageOralEtConscienceDesSyllabes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Langage oral et conscience phonologique");
        assertThat(descripteur.competence())
                .isEqualTo("Dénombrer les syllabes orales d'un mot (segmentation syllabique)");
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

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
