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
 * <p><b>HIS-1 a vecu ici du 2026-09-02 au 2026-09-03, et vit desormais dans
 * {@code UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest} (ADR-0079). Le motif est
 * ecrit plutot que le deplacement laisse nu.</b> Le marqueur "# HIS-1: &lt;type&gt;" pose dans le
 * YAML servi ne change pas d'un caractere, et le mecanisme reste celui que marc-dev a ecrit - meme
 * patron que le marqueur "# COMP-3" de {@code PaquetCm1ComprehensionTest}, invisible a
 * {@code QuestionContentSourceFactory} qui ignore les commentaires.</p>
 *
 * <p><b>Ce qui change est la population, et c'est nadia-prof qui l'a demande</b> le 2026-09-03 :
 * son unite d'audit est le <b>lot de matiere</b> - jusqu'a vingt items d'une matiere et d'un niveau -
 * et non le fichier, parce qu'<i>"un ratio calcule sur huit items ne veut rien dire"</i>. La clause
 * qui vivait ici designait sa population par <b>un chemin de ressource ecrit en dur</b> et son
 * plafond par <b>une constante egale au tiers de vingt</b> : elle etait juste tant que l'histoire
 * tenait en un seul fichier, et <b>un second fichier du meme lot n'aurait ete juge par rien</b>.
 * Le tiers se compte desormais sur le lot, sur une population derivee des descripteurs servis.
 * <b>Ce n'est pas un defaut du travail de marc-dev</b> : c'est un perimetre enumere, juste tant que
 * le monde ressemble a l'enumeration - la meme famille que {@code FIG-4} etendue hors de son paquet
 * la veille (ADR-0077).</p>
 */
class PaquetCm1HistoireGeographieTest {

    private static final String PAQUET = "cm1-histoire-geographie";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

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
}
