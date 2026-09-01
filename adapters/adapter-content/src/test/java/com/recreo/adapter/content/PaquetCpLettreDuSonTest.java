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
 * <b>S26-L3, le paquet {@code cp-lettre-du-son}, clause 1 de la ligne "associer les graphemes aux
 * phonemes" (voyelles et consonnes simples).</b> Encode ce qui est calculable sur la fiche de
 * conformite (`squad/conformite/FICHE-bandit-manchot-lettre-du-son-cp.md`) et l'addendum de
 * nadia-prof, section 11.4 : le plafond de vingt items et la condition de distribution -
 * <i>"chaque lettre servie est distracteur au moins aussi souvent qu'elle est reponse"</i>.
 *
 * <p><b>Ce que cette classe ne juge pas, et pourquoi c'est ecrit ici plutot que suppose</b> :
 * {@code VOI-10} comparait {@code texteAVoixHaute} aux propositions par sous-chaine repliee.
 * <b>Amendement du 2026-09-01</b> : cet operateur est remplace par l'enonciation - une suite
 * contigue de jetons - en {@code VOI-14} point 1, encodee par
 * {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest} et actee par ADR-0076. Sur ce paquet, la proposition
 * est une <b>lettre unique</b> (l'initiale du mot que l'adulte doit prononcer en entier, VOI-7) :
 * elle est donc mecaniquement une sous-chaine du mot lui-meme, par construction et non par defaut
 * d'ecriture - "papa" contient "p" quel que soit le mot choisi pour cette lettre. Le crochet
 * partage est branche sur le contenu de production et ce paquet y passe.</p>
 */
class PaquetCpLettreDuSonTest {

    private static final String PAQUET = "cp-lettre-du-son";

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
     * Espace de reponse : une lettre simple, jamais un digraphe (S26-L3, critere d'acceptation 1).
     */
    @Test
    void chaquePropositionEstUneLettreSimple() {
        for (Question item : items()) {
            assertThat(item.bonneReponse().length())
                    .as("item %s : la bonne reponse doit etre une lettre simple", item.id().value())
                    .isEqualTo(1);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.length())
                        .as("item %s : chaque distracteur doit etre une lettre simple", item.id().value())
                        .isEqualTo(1);
            }
        }
    }

    /**
     * Condition de distribution (addendum, section 11.4, citee mot pour mot) : chaque lettre
     * servie est distracteur au moins aussi souvent qu'elle est reponse. Compte reel, jamais
     * affirme.
     */
    @Test
    void chaqueLettreServieEstDistracteurAuMoinsAussiSouventQuElleEstReponse() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> commeReponse = new TreeMap<>();
        Map<String, Integer> commeDistracteur = new TreeMap<>();
        for (Question item : items) {
            commeReponse.merge(item.bonneReponse(), 1, Integer::sum);
            for (String distracteur : item.distracteurs()) {
                commeDistracteur.merge(distracteur, 1, Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> entree : commeReponse.entrySet()) {
            String lettre = entree.getKey();
            int commeReponseCompte = entree.getValue();
            int commeDistracteurCompte = commeDistracteur.getOrDefault(lettre, 0);
            assertThat(commeDistracteurCompte)
                    .as("addendum 11.4 : la lettre \"%s\" est reponse %d fois et distracteur %d fois"
                                    + " - elle doit etre distracteur au moins aussi souvent",
                            lettre, commeReponseCompte, commeDistracteurCompte)
                    .isGreaterThanOrEqualTo(commeReponseCompte);
        }
    }

    /**
     * Second motif du refus de nadia-prof (addendum, section 17.3), propre a ce paquet et non leve
     * par VOI-12 : sur 5 items sur 18 (pomme/m, moto/t, table/l, tortue/r, lapin/p), un distracteur
     * est aussi une lettre dont le son est present dans le mot entendu - 27,8 % du paquet. Rien ne
     * disait a l'enfant, avant son premier essai, que le son cherche est le PREMIER du mot ; seul
     * l'indice, affiche apres une erreur, le precisait. Correctif : l'enonce nomme la position
     * avant toute reponse, sans qu'aucune lettre supplementaire n'atteigne l'ecran.
     */
    @Test
    void lEnonceNommeLaPositionDuSonAvantLaPremiereReponse() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : l'enonce doit nommer que le son cherche est le premier du mot,"
                                    + " avant toute reponse (nadia-prof, addendum section 17.3)",
                            item.id().value())
                    .contains("premier son");
        }
    }

    /**
     * Verifie que le correctif ci-dessus est reellement vrai du materiel : la bonne reponse est
     * bien le tout premier son du mot que l'adulte prononce, jamais un son present ailleurs dans le
     * mot. Sans cette propriete, dire "le tout premier son" a l'enfant induirait en erreur au lieu
     * de lever l'ambiguite que nadia-prof a mesuree.
     */
    @Test
    void laBonneReponseEstBienLePremierSonDuMotEntendu() {
        for (Question item : items()) {
            String mot = motDe(replier(item.texteAVoixHaute().orElseThrow()));
            assertThat(mot.substring(0, 1))
                    .as("item %s : la bonne reponse (\"%s\") doit etre la premiere lettre du mot"
                                    + " (\"%s\")", item.id().value(), item.bonneReponse(), mot)
                    .isEqualTo(replier(item.bonneReponse()));
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
