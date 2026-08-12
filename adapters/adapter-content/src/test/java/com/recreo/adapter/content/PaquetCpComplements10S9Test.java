package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
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
 * S9-2 (DEC-0020, docs/backlog/backlog.md section "Sprint 9") : le paquet neuf "cp-complements-10",
 * joue par "bandit-manchot-complements-10-cp". Ne couvre que les criteres d'acceptation
 * automatisables de la story ; la plausibilite pedagogique des distracteurs et des indices reste
 * exclusivement le jugement de nadia-prof (le critere le dit explicitement).
 *
 * <p>Les proprietes generiques (indice non vide, au moins un item de plus que de manches, aucun
 * item ne repete l'ecran d'un autre du meme paquet, plafond de quatre propositions) sont deja
 * derivees du repertoire ou du catalogue par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCpComplements10S9Test {

    private static final Pattern FORME_ATTENDUE = Pattern.compile("^(\\d+) \\+ \\? = 10$");

    @Test
    void chaqueEnonceDemandeLeComplementA10DUnNombreEntre0Et10Inclus() {
        for (Question item : tousLesItems(paquet())) {
            Matcher forme = FORME_ATTENDUE.matcher(item.enonce());
            assertThat(forme.matches())
                    .as("l'enonce '%s' est ecrit sous la forme canonique 'a + ? = 10'", item.enonce())
                    .isTrue();

            int nombreDeDepart = Integer.parseInt(forme.group(1));
            int bonneReponse = Integer.parseInt(item.bonneReponse());

            assertThat(nombreDeDepart).as("nombre de depart de '%s' entre 0 et 10", item.enonce())
                    .isBetween(0, 10);
            assertThat(nombreDeDepart + bonneReponse)
                    .as("'%s' : le nombre de depart et la bonne reponse font exactement 10", item.enonce())
                    .isEqualTo(10);
        }
    }

    @Test
    void chaqueValeurEntiereDe0A10ApparaitAuMoinsUneFoisCommeNombreDeDepart() {
        Set<Integer> departsCouverts = new HashSet<>();
        for (Question item : tousLesItems(paquet())) {
            Matcher forme = FORME_ATTENDUE.matcher(item.enonce());
            forme.matches();
            departsCouverts.add(Integer.parseInt(forme.group(1)));
        }

        assertThat(departsCouverts)
                .as("couverture complete de la table des complements a 10, pas seulement ses valeurs"
                        + " les plus faciles")
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
    void auMoinsUnDistracteurParItemEstUneErreurFrequenteIdentifiable() {
        for (Question item : tousLesItems(paquet())) {
            Matcher forme = FORME_ATTENDUE.matcher(item.enonce());
            forme.matches();
            int nombreDeDepart = Integer.parseInt(forme.group(1));

            String nombreDeDepartRecopie = String.valueOf(nombreDeDepart);
            String complementA9 = nombreDeDepart <= 9 ? String.valueOf(9 - nombreDeDepart) : null;

            assertThat(item.distracteurs())
                    .as("'%s' : au moins un distracteur recopie le nombre de depart (%s) ou pose le"
                            + " complement a 9 (%s)", item.enonce(), nombreDeDepartRecopie, complementA9)
                    .anyMatch(distracteur -> distracteur.equals(nombreDeDepartRecopie)
                            || distracteur.equals(complementA9));
        }
    }

    @Test
    void lePaquetCompteAuMoins9Items() {
        assertThat(tousLesItems(paquet()))
                .as("DEC-0020 : au moins 9 items, strictement plus que les 7 manches declarees au"
                        + " catalogue pour ce jeu, avec une marge sur le plancher exige par ADR-0023")
                .hasSizeGreaterThanOrEqualTo(9);
    }

    @Test
    void leJeuDuCatalogueDeclareSeptManchesEtJoueBienLePaquetCpComplements10() {
        GameDefinition jeu = jeu("bandit-manchot-complements-10-cp");

        assertThat(jeu.nombreDeManches())
                .as("DEC-0020 : ce jeu declare 7 manches, et non 10 comme les autres jeux du catalogue")
                .isEqualTo(7);
        assertThat(tousLesItems(jeu.contenu()))
                .as("l'entree de catalogue charge bien le paquet 'cp-complements-10'")
                .isEqualTo(tousLesItems(paquet()));
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

    private static GameDefinition jeu(String id) {
        return new YamlContentPackLoader().games().stream()
                .filter(candidat -> candidat.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet("cp-complements-10");
    }
}
