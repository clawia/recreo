package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S26-L1, le paquet {@code cp-position-syllabe}.</b> Encode ce que la fiche de conformite
 * (`squad/conformite/FICHE-bandit-manchot-position-syllabe-cp.md`) et l'arbitrage de nadia-prof
 * (section 4.1) posent comme calculable : le plafond de vingt items (addendum, section 12.2) et
 * `VOI-4` - les trois positions occupent l'ensemble du paquet, aucune n'etant majoritaire.
 *
 * <p><b>Ce que cette classe ne juge pas</b> : `VOI-3` (aucune scansion syllabique), `VOI-5` et les
 * autres conditions tenues par un humain dans la piece - nadia-prof l'ecrit elle-meme, aucun test
 * ne peut les verifier (arbitrage, section 7.1).</p>
 */
class PaquetCpPositionSyllabeTest {

    private static final String PAQUET = "cp-position-syllabe";

    private static final int PLAFOND_ITEMS = 20;

    /** ADR-0064 : aucune borne ecrite a la main - le tirage s'arrete de lui-meme sur un paquet enumere. */
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

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * VOI-4 : les mots comptent deux ou trois syllabes, et la syllabe cherchee occupe les trois
     * positions sur l'ensemble du paquet, aucune n'etant majoritaire. Compte par position, calcule
     * sur les items reellement tires par le chargeur - jamais affirme.
     */
    @Test
    void lesTroisPositionsSontRepresenteesEtAucuneNEstMajoritaire() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("VOI-4 : les trois positions doivent etre representees")
                .containsExactlyInAnyOrder("début", "milieu", "fin");

        int total = items.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("VOI-4 : la position \"%s\" (%d/%d items) ne doit pas etre majoritaire",
                            entree.getKey(), entree.getValue(), total)
                    .isLessThanOrEqualTo(total / 2);
        }
    }

    /**
     * VOI-13 (moitie calculable) : le mot travaille n'apparait dans aucun texte que l'enfant lit -
     * enonce, propositions, indice. Seul texteAVoixHaute le porte.
     */
    @Test
    void leMotTravailleNApparaitQueDansLeTexteAVoixHaute() {
        for (Question item : items()) {
            String voix = replier(item.texteAVoixHaute().orElseThrow());
            assertThat(replier(item.enonce())).as("item %s : l'enonce", item.id().value())
                    .doesNotContain(motDe(voix));
            assertThat(replier(item.indice().orElseThrow())).as("item %s : l'indice", item.id().value())
                    .doesNotContain(motDe(voix));
        }
    }

    /**
     * VOI-10, verifie localement sur ce paquet : les propositions ("début", "milieu", "fin") ne
     * sont ni le mot ni un fragment que texteAVoixHaute recopierait. <b>Le crochet partage
     * ({@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest}) est desormais branche sur le contenu
     * de production</b>, sous l'operateur d'enonciation de VOI-14 point 1 (ADR-0076) ; cette
     * verification locale le double sur ce paquet plutot que d'en tenir lieu.
     */
    @Test
    void texteAVoixHauteNeContientAucuneDesTroisPropositions() {
        for (Question item : items()) {
            String voix = replier(item.texteAVoixHaute().orElseThrow());
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(voix)
                        .as("item %s : texteAVoixHaute ne doit pas contenir la proposition \"%s\""
                                + " (VOI-10)", item.id().value(), proposition)
                        .doesNotContain(replier(proposition));
            }
        }
    }

    private static String motDe(String texteAVoixHauteReplie) {
        // Le mot travaille est le premier mot apres "le mot est " dans le gabarit de ce paquet.
        String prefixe = "le mot est ";
        int debut = texteAVoixHauteReplie.indexOf(prefixe) + prefixe.length();
        int fin = texteAVoixHauteReplie.indexOf('.', debut);
        return texteAVoixHauteReplie.substring(debut, fin < 0 ? texteAVoixHauteReplie.length() : fin);
    }

    private static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            replie.append(signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }
}
