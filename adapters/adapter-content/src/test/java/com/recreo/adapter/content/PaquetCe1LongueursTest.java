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
 * S39-LONGUEUR (MAT-CE1-09) : le paquet "ce1-longueurs", mathematiques CE1. Arbitrage prealable de
 * nadia-prof, MES-CE1-1 a MES-CE1-3
 * (squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md, section 2, MES-CE1-3
 * amendee le 2026-09-22 apres l'avis de faisabilite de theo-archi sur la collision avec ADR-0041).
 * Ne sert que la part textuelle de la ligne (comparer, utiliser cm/m) - le verbe "mesurer avec une
 * regle graduee" exige un rendu visuel absent du depot et n'est pas servi ici : la ligne reste "en
 * cours", jamais "couvert-valide".
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, au plus trois propositions, meme
 * unite dans les trois propositions d'un item) sont deja derivees du repertoire de contenu par les
 * garde-fous existants (dont {@code LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest}, clause
 * du reste identique) et ne sont pas repetees ici.</p>
 */
class PaquetCe1LongueursTest {

    private static final String PAQUET = "ce1-longueurs";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 14;

    /** MES-CE1-1 : bornes de tirage, 1 cm a 500 cm (5 m). */
    private static final int BORNE_BASSE_CM = 1;

    private static final int BORNE_HAUTE_CM = 500;

    private static final Pattern LONGUEUR = Pattern.compile("(\\d+) (cm|m)");

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

    /** Une longueur exprimee en cm, quelle que soit l'unite ecrite. */
    private static int enCm(String proposition) {
        Matcher forme = LONGUEUR.matcher(proposition);
        assertThat(forme.find()).as("'%s' : longueur reconnaissable (\\d+ cm|m)", proposition).isTrue();
        int valeur = Integer.parseInt(forme.group(1));
        return "m".equals(forme.group(2)) ? valeur * 100 : valeur;
    }

    private static boolean estUneConversion(Question item) {
        return item.enonce().contains("combien de");
    }

    @Test
    void lePaquetCompteQuatorzeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** MES-CE1-1 : toutes les longueurs tirees, enonce et propositions compris, restent bornees. */
    @Test
    void touteLongueurResteEntreUnEtCinqCentsCentimetres() {
        for (Question item : tousLesItems(paquet())) {
            // MES-CE1-1 borne le TIRAGE - la longueur source d'un item de conversion et les trois
            // longueurs d'objets d'un item de comparaison - jamais un distracteur de conversion :
            // celui-ci est delibérement une erreur de facteur (MES-CE1-3) et peut depasser la borne
            // par construction (diviser par 10 au lieu de 100 grossit la longueur d'un facteur 10).
            List<String> lus = new ArrayList<>();
            if (estUneConversion(item)) {
                Matcher dansLEnonce = LONGUEUR.matcher(item.enonce());
                assertThat(dansLEnonce.find()).as("'%s' : longueur source", item.enonce()).isTrue();
                lus.add(dansLEnonce.group());
                lus.add(item.bonneReponse());
            } else {
                lus.add(item.bonneReponse());
                lus.addAll(item.distracteurs());
            }
            for (String texte : lus) {
                int cm = enCm(texte);
                assertThat(cm)
                        .as("MES-CE1-1 : '%s' dans '%s'", texte, item.enonce())
                        .isBetween(BORNE_BASSE_CM, BORNE_HAUTE_CM);
            }
        }
    }

    /**
     * MES-CE1-2 : les conversions cm/m ne portent que sur des cas exacts, un nombre entier de
     * metres (multiple de 100 cm).
     */
    @Test
    void chaqueConversionEstUnMultipleExactDeCentCentimetres() {
        for (Question item : tousLesItems(paquet())) {
            if (!estUneConversion(item)) {
                continue;
            }
            Matcher dansLEnonce = LONGUEUR.matcher(item.enonce());
            assertThat(dansLEnonce.find()).as("'%s' : longueur source", item.enonce()).isTrue();
            int source = enCm(dansLEnonce.group());
            assertThat(source % 100)
                    .as("MES-CE1-2 : '%s' - %d cm n'est pas un multiple exact de 100", item.enonce(), source)
                    .isZero();
        }
    }

    /**
     * MES-CE1-3 (version amendee) : les distracteurs de conversion sont une erreur de facteur -
     * diviser ou multiplier par 10, par 1 (pas de conversion), ou par un facteur different de 100 -
     * jamais une longueur negative.
     */
    @Test
    void lesDistracteursDeConversionSontDesErreursDeFacteur() {
        for (Question item : tousLesItems(paquet())) {
            if (!estUneConversion(item)) {
                continue;
            }
            Matcher dansLEnonce = LONGUEUR.matcher(item.enonce());
            assertThat(dansLEnonce.find()).isTrue();
            int source = enCm(dansLEnonce.group());
            int attendu = enCm(item.bonneReponse());
            assertThat(source).as("'%s' : la conversion tombe juste", item.enonce()).isEqualTo(attendu);

            for (String distracteur : item.distracteurs()) {
                int propose = enCm(distracteur);
                assertThat(propose)
                        .as("MES-CE1-3 : '%s' - le distracteur '%s' n'est pas une longueur negative",
                                item.enonce(), distracteur)
                        .isPositive();
                assertThat(propose)
                        .as("MES-CE1-3 : '%s' - le distracteur '%s' egale la bonne reponse",
                                item.enonce(), distracteur)
                        .isNotEqualTo(attendu);
            }
        }
    }

    /** MES-CE1-3 : les trois propositions d'un item de conversion partagent la meme unite ecrite. */
    @Test
    void lesTroisPropositionsDUnItemDeConversionPartagentLaMemeUnite() {
        for (Question item : tousLesItems(paquet())) {
            if (!estUneConversion(item)) {
                continue;
            }
            String uniteDeLaBonneReponse = item.bonneReponse().replaceAll("[0-9 ]", "");
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.replaceAll("[0-9 ]", ""))
                        .as("MES-CE1-3 : '%s' - '%s' et '%s' ne sont pas dans la meme unite",
                                item.enonce(), item.bonneReponse(), distracteur)
                        .isEqualTo(uniteDeLaBonneReponse);
            }
        }
    }

    /** Les items de comparaison sont bien distincts des items de conversion (les deux verbes servis). */
    @Test
    void lesDeuxVerbesSontRepresentes() {
        List<Question> items = tousLesItems(paquet());
        long conversions = items.stream().filter(PaquetCe1LongueursTest::estUneConversion).count();
        assertThat(conversions).as("verbe 'utiliser cm et m'").isEqualTo(7);
        assertThat(items.size() - conversions).as("verbe 'comparer'").isEqualTo(7);
    }
}
