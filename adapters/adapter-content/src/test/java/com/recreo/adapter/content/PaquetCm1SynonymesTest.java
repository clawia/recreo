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
 * S17-3 (DEC-0039, arbitrage prealable de nadia-prof du 2026-08-19, section 2) : le paquet
 * "cm1-synonymes". Matiere premiere reformulee, pas convertie
 * ({@code contenu-repris/associer/cm1-synonymes.yml}, 12 paires mot/synonyme sans phrase ni
 * distracteur) - ce paquet en porte 20, integralement ecrits pour ce sprint.
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (SYN-1, comptage, forme du
 * descripteur) : la justesse du sens de chaque synonyme et de chaque phrase pour un enfant de CM1
 * reste le jugement de nadia-prof (FICHE-bandit-manchot-synonymes-cm1.md), de meme que le critere
 * "aucun distracteur n'est reellement substituable" (SYN-2) et "aucun item ne presente deux mots
 * interchangeables que dans cette phrase precise" (SYN-3), qui exigent un jugement de sens qu'aucun
 * automate de ce depot ne porte.</p>
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, une seule graphie de matiere) sont
 * deja derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.
 * Ce paquet n'a aucune entree de catalogue a ce jour (Done pedagogique non encore prononce) : il
 * est charge directement, sur le meme principe que "cp-le-bon-son" sous DEC-0009.</p>
 */
class PaquetCm1SynonymesTest {

    private static final String PAQUET = "cm1-synonymes";

    private static final int MINIMUM_D_ITEMS = 20;

    /**
     * SYN-1 : le mot cible est cite deux fois entre guillemets droits dans le meme enonce - une
     * fois dans la phrase qui lui donne son contexte, une fois dans la question qui demande son
     * remplacement - jamais une seule fois isolee. Rendu textuel choisi pour {@code th:text}
     * (fragments/manche.html echappe le HTML, aucun soulignement n'est disponible sans gabarit
     * neuf, hors du perimetre de cette story) : les guillemets tiennent lieu du "mot souligne" que
     * l'arbitrage de nadia-prof illustre.
     */
    private static final Pattern MOT_ENTRE_GUILLEMETS = Pattern.compile("\"([^\"]+)\"");

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("SYN, comptage : au moins %d items", MINIMUM_D_ITEMS)
                .hasSizeGreaterThanOrEqualTo(MINIMUM_D_ITEMS);
    }

    @Test
    void chaqueItemPresenteSonMotCibleDansUnePhraseJamaisIsole() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            List<String> motsCites = new ArrayList<>();
            Matcher trouve = MOT_ENTRE_GUILLEMETS.matcher(enonce);
            int premierIndex = -1;
            while (trouve.find()) {
                if (premierIndex < 0) {
                    premierIndex = trouve.start();
                }
                motsCites.add(trouve.group(1));
            }

            assertThat(motsCites)
                    .as("'%s' (SYN-1) : le mot cible est cite deux fois - dans la phrase, puis dans la"
                            + " question qui demande son remplacement", enonce)
                    .hasSize(2);
            assertThat(motsCites.get(1))
                    .as("'%s' (SYN-1) : la question porte sur le meme mot que celui presente dans la"
                            + " phrase", enonce)
                    .isEqualTo(motsCites.get(0));
            assertThat(enonce.substring(0, premierIndex).strip())
                    .as("'%s' (SYN-1) : du texte precede le mot cite - il est presente en phrase,"
                            + " jamais isole en tete d'enonce", enonce)
                    .isNotEmpty();
            assertThat(enonce)
                    .as("'%s' (SYN-1) : la question demande explicitement un remplacement dans cette"
                            + " phrase, jamais une definition du mot seul", enonce)
                    .contains("Dans cette phrase, quel mot peut remplacer");
        }
    }

    @Test
    void leDescripteurEstFrancaisCm1VocabulaireSynonymesEtAntonymes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere())
                .as("ADR-0049 : la meme graphie ASCII que les douze autres paquets servis - une"
                        + " graphie accentuee ici, seule, ferait echouer"
                        + " UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest")
                .isEqualTo("Francais");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Vocabulaire");
        assertThat(descripteur.competence())
                .contains("synonymes")
                .contains("antonymes");
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
