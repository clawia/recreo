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
 * <b>S26-L2, le paquet {@code cp-son-dans-mot}.</b> Encode ce qui est calculable sur la fiche de
 * conformite (`squad/conformite/FICHE-bandit-manchot-son-dans-mot-cp.md`) et l'arbitrage de
 * nadia-prof (section 4.2) : le plafond de vingt items et `VOI-6` - oui et non ne sont ni l'un ni
 * l'autre majoritaires.
 *
 * <p><b>Ce que cette classe ne juge pas</b> : `VOI-5` (le son cherche n'est pas appuye), tenue par
 * un humain dans la piece, aucun test ne peut la verifier.</p>
 */
class PaquetCpSonDansMotTest {

    private static final String PAQUET = "cp-son-dans-mot";

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
     * VOI-6 : le son cherche est present sur une part des items et absent sur une autre, aucune des
     * deux n'etant majoritaire.
     */
    @Test
    void ouiEtNonNeSontNiLUnNiLAutreMajoritaires() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("VOI-6 : oui et non doivent tous deux etre representes")
                .containsExactlyInAnyOrder("oui", "non");

        int total = items.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("VOI-6 : la reponse \"%s\" (%d/%d items) ne doit pas etre majoritaire",
                            entree.getKey(), entree.getValue(), total)
                    .isLessThanOrEqualTo(total / 2);
        }
    }

    /**
     * VOI-13 (moitie calculable) : le mot travaille n'apparait dans aucun texte que l'enfant lit -
     * enonce, indice. Seul texteAVoixHaute le porte. Point ouvert (lecture prudente par defaut) :
     * aucune lettre du son cherche n'apparait non plus dans l'enonce ou l'indice, qui restent
     * generiques.
     */
    @Test
    void leMotTravailleNApparaitQueDansLeTexteAVoixHaute() {
        for (Question item : items()) {
            String voix = replier(item.texteAVoixHaute().orElseThrow());
            String mot = motDe(voix);
            assertThat(replier(item.enonce())).as("item %s : l'enonce", item.id().value())
                    .doesNotContain(mot);
            assertThat(replier(item.indice().orElseThrow())).as("item %s : l'indice", item.id().value())
                    .doesNotContain(mot);
        }
    }

    /**
     * VOI-10, verifie localement sur ce paquet : "oui" et "non" ne sont jamais recopies dans
     * texteAVoixHaute. <b>Le crochet partage
     * ({@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest}) est desormais branche sur le contenu
     * de production</b>, sous l'operateur d'enonciation de VOI-14 point 1 (ADR-0076) ; cette
     * verification locale le double sur ce paquet plutot que d'en tenir lieu.
     */
    @Test
    void texteAVoixHauteNeContientNiOuiNiNon() {
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
