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
import java.util.TreeSet;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Paquet "ce1-lire-l-heure" (MAT-CE1-11, moitie "lire l'heure sur une horloge"), premier jet de
 * lea-dev, s57, palier 5. Conditions de nadia-prof : arbitrage de s57 palier 5 section 3.5, et
 * HOR-1 a HOR-8 du CP quand la section 3.5 les reprend (HOR-5 et HOR-6 transposees, HOR-7).
 *
 * <p><b>Ce que ce fichier verifie.</b> Ce qui se compte ou se lit sur le texte : douze items, quatre
 * formes de reponse, aucune heure ecrite dans un enonce, un indice ou un texte alternatif (CON-5),
 * un indice de huit mots au plus qui n'est pas une heure (HOR-7), chaque forme d'une proposition
 * fausse aussi servie comme bonne reponse ailleurs (ADR-0086 clause 2). <b>Ce que le dessin montre</b>
 * - les angles des aiguilles, les douze nombres, jamais deux fois le meme cadran, les heures, les
 * trois propositions qui ne lisent jamais deux fois la meme position - est juge par
 * {@code LesCadransMontrentCeQueLeursItemsAffirmentTest}, dans bootstrap, qui lit les fichiers de
 * dessin.
 *
 * <p><b>Ce qu'aucun test ne dit</b> : que la petite aiguille se lise entre deux nombres a 176 px, que
 * les reponses ecrites "3 h 15" plutot que "3 heures et quart" soient la formulation de la classe,
 * que le texte alternatif decrive assez sans livrer. C'est nadia-prof.
 */
class PaquetCe1LireLHeureTest {

    private static final String PAQUET = "ce1-lire-l-heure";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 12;

    /** HOR-7 : huit mots au plus. */
    private static final int MOTS_MAXIMUM_D_UN_INDICE = 8;

    private static final Pattern UN_CHIFFRE = Pattern.compile("\\d");

    private static final Pattern COMPLEMENT = Pattern.compile("^\\d{1,2} h(?: (15|30|45))?$");

    /**
     * CON-5 : ce que le texte alternatif ne dit pas. Un temoin et non un perimetre : la condition
     * est tenue par le test des chiffres, qui est exhaustif, et par la lecture de nadia-prof.
     */
    private static final Set<String> MOTS_QU_UN_TEXTE_ALTERNATIF_NE_DIT_PAS = Set.of(
            "heure", "heures", "midi", "minuit", "demie", "demi", "quart", "entre", "pointe",
            "montre", "indique", "sur le", "vers le");

    @Test
    void lePaquetCompteExactementDouzeItemsDontChacunMontreUnDessin() {
        List<Question> items = tousLesItems(paquet());

        assertThat(items).hasSize(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(items).allSatisfy(item -> assertThat(item.illustration()).isPresent());
        assertThat(items.stream().map(item -> item.illustration().orElseThrow()).distinct())
                .as("deux items ne montrent jamais le meme dessin")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void aucunEnonceNeDitLHeure() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(UN_CHIFFRE.matcher(item.enonce()).find())
                    .as("l'enonce \"%s\" ecrit un chiffre : l'heure se lit sur le cadran", item.enonce())
                    .isFalse();
        }
    }

    @Test
    void aucunTexteAlternatifNeLivreLHeureNiLaPlaceDUneAiguille() {
        for (Question item : tousLesItems(paquet())) {
            String alternatif = item.illustrationTexteAlternatif().orElseThrow()
                    .toLowerCase(Locale.FRENCH);
            assertThat(UN_CHIFFRE.matcher(alternatif).find())
                    .as("CON-5 : le texte alternatif \"%s\" ecrit un chiffre", alternatif)
                    .isFalse();
            for (String interdit : MOTS_QU_UN_TEXTE_ALTERNATIF_NE_DIT_PAS) {
                assertThat(alternatif.contains(interdit))
                        .as("CON-5 : le texte alternatif \"%s\" contient \"%s\"", alternatif, interdit)
                        .isFalse();
            }
        }
    }

    @Test
    void chaqueIndiceNommeUneAiguilleEtTientEnHuitMotsSansEcrireUneHeure() {
        for (Question item : tousLesItems(paquet())) {
            String indice = item.indice().orElseThrow();
            assertThat(indice.toLowerCase(Locale.FRENCH)).as("HOR-7 : l'indice \"%s\"", indice)
                    .contains("aiguille");
            assertThat(UN_CHIFFRE.matcher(indice).find())
                    .as("HOR-7 : l'indice \"%s\" ecrit un chiffre, il dit quelle aiguille regarder"
                            + " et jamais l'heure", indice)
                    .isFalse();
            assertThat(mots(indice))
                    .as("HOR-7 : l'indice \"%s\" depasse huit mots", indice)
                    .isLessThanOrEqualTo(MOTS_MAXIMUM_D_UN_INDICE);
        }
    }

    @Test
    void chaqueFormeDeReponseFausseEstAussiUneBonneReponseAilleurs() {
        List<Question> items = tousLesItems(paquet());
        Set<String> formesJustes = new TreeSet<>();
        for (Question item : items) {
            formesJustes.add(forme(item.bonneReponse()));
        }
        assertThat(formesJustes).as("les quatre formes sont servies comme bonne reponse")
                .containsExactlyInAnyOrder("", "15", "30", "45");

        for (Question item : items) {
            for (String distracteur : item.distracteurs()) {
                assertThat(formesJustes)
                        .as("ADR-0086 clause 2 : le distracteur \"%s\" de l'item \"%s\" a une forme que"
                                + " aucune bonne reponse du paquet ne porte", distracteur, item.bonneReponse())
                        .contains(forme(distracteur));
            }
        }
    }

    @Test
    void chaquePropositionEstUneLectureDHeureDansLaFormeDuPaquet() {
        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(COMPLEMENT.matcher(proposition).matches())
                        .as("la proposition \"%s\" n'est pas \"N h\" suivi, ou non, de 15, 30 ou 45 minutes",
                                proposition)
                        .isTrue();
            }
        }
    }

    private static int mots(String texte) {
        int mots = 0;
        for (String morceau : texte.split("[\\s ]+")) {
            if (morceau.chars().anyMatch(Character::isLetterOrDigit)) {
                mots++;
            }
        }
        return mots;
    }

    private static String forme(String lecture) {
        var trouve = COMPLEMENT.matcher(lecture);
        assertThat(trouve.matches()).as("lecture \"%s\"", lecture).isTrue();
        return trouve.group(1) == null ? "" : trouve.group(1);
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

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
}
