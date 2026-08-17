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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S9-1 (DEC-0019, docs/backlog/backlog.md section "Sprint 9") : le paquet neuf "cp-soustractions",
 * joue par "bandit-manchot-soustractions-cp". Ne couvre que les criteres d'acceptation
 * automatisables de la story ; la plausibilite pedagogique des distracteurs et des indices reste
 * exclusivement le jugement de nadia-prof (le critere le dit explicitement).
 *
 * <p>Les proprietes generiques (indice non vide, au moins un item de plus que de manches, aucun
 * item ne repete l'ecran d'un autre du meme paquet, plafond de quatre propositions) sont deja
 * derivees du repertoire ou du catalogue par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCpSoustractionsS9Test {

    private static final Pattern FORME_ATTENDUE = Pattern.compile("^(\\d+) - (\\d+) = \\?$");

    @Test
    void chaqueEnonceEstUneSoustractionDeTermesEtDeResultatEntre0Et10SansResultatNegatif() {
        for (Question item : tousLesItems(paquet())) {
            Matcher forme = FORME_ATTENDUE.matcher(item.enonce());
            assertThat(forme.matches())
                    .as("l'enonce '%s' est ecrit sous la forme 'a - b = ?'", item.enonce())
                    .isTrue();

            int premierTerme = Integer.parseInt(forme.group(1));
            int secondTerme = Integer.parseInt(forme.group(2));
            int resultat = Integer.parseInt(item.bonneReponse());

            assertThat(premierTerme).as("premier terme de '%s' entre 0 et 10", item.enonce())
                    .isBetween(0, 10);
            assertThat(secondTerme).as("second terme de '%s' entre 0 et 10", item.enonce())
                    .isBetween(0, 10);
            assertThat(secondTerme).as("'%s' : le second terme n'est jamais superieur au premier"
                    + " (aucun resultat negatif)", item.enonce())
                    .isLessThanOrEqualTo(premierTerme);
            assertThat(resultat).as("'%s' : la bonne reponse est le resultat exact de la soustraction",
                    item.enonce())
                    .isEqualTo(premierTerme - secondTerme);
        }
    }

    @Test
    void chaqueValeurEntiereDe0A10ApparaitAuMoinsUneFoisParmiLesResultats() {
        Set<Integer> resultatsCouverts = new HashSet<>();
        for (Question item : tousLesItems(paquet())) {
            resultatsCouverts.add(Integer.parseInt(item.bonneReponse()));
        }

        assertThat(resultatsCouverts)
                .as("DEC-0007 : aucune borne de l'intervalle 0-10 n'est absente des resultats")
                .containsExactlyInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void chaqueItemAExactementTroisPropositionsNumeriquesDistinctesDansLIntervalle0A10() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : exactement deux distracteurs, en plus de la bonne reponse", item.enonce())
                    .hasSize(2)
                    .doesNotHaveDuplicates()
                    .doesNotContain(item.bonneReponse());

            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(Integer.parseInt(proposition))
                        .as("'%s' : la proposition '%s' est un nombre entier entre 0 et 10", item.enonce(),
                                proposition)
                        .isBetween(0, 10);
            }
        }
    }

    @Test
    void lePaquetCompteAuMoins20Items() {
        assertThat(tousLesItems(paquet()))
                .as("S9-1 : au moins 20 items, pour eviter le defaut deja releve sur 'Le bon son'")
                .hasSizeGreaterThanOrEqualTo(20);
    }

    /*
     * s14, dette technique 1 (avis-faisabilite.md section 8.1) : la verification "l'entree de
     * catalogue charge bien ce paquet" est desormais generique, derivee de catalogue.yaml, dans
     * ChaqueEntreeDuCatalogueChargeBienSonPaquetTest. Elle n'est plus repetee ici.
     */

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
        return new YamlContentPackLoader().chargerPaquet("cp-soustractions");
    }
}
