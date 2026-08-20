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
import java.util.Map;
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
 * deja derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 *
 * <p><b>ADR-0052, refus du 2026-08-19 (SYN-5) puis accord de nadia-prof sur le mecanisme.</b> La
 * clause invariante ("Dans cette phrase, quel mot peut remplacer X sans changer le sens ?") quitte
 * l'enonce pour la consigne, generique, de l'entree de catalogue neuve
 * ("bandit-manchot-synonymes-cm1"). Sur instruction expresse de {@code sofia-sm} (routee par
 * {@code nadia-prof}) : <b>seul l'item le plus long des vingt</b> ("triste"/"malheureux", 132
 * caracteres, celui que son tableau designe comme la frontiere) est transforme dans ce lot ; les
 * dix-neuf autres restent sous leur forme d'origine, en attente du jugement sur cet item pilote.
 * Ce fichier distingue donc deux formes valides tant que la transformation n'est pas
 * generalisee :</p>
 * <ul>
 *   <li><b>forme d'origine</b> (dix-neuf items) : mot cite deux fois entre guillemets droits, la
 *       clause invariante ecrite dans l'enonce ;</li>
 *   <li><b>forme pilote</b> (un item, SYN-6) : mot cite une fois entre guillemets francais, aucune
 *       clause invariante dans l'enonce - elle vit dans la consigne du catalogue, verifiee
 *       separement.</li>
 * </ul>
 */
class PaquetCm1SynonymesTest {

    private static final String PAQUET = "cm1-synonymes";

    private static final String ID_DE_CATALOGUE = "bandit-manchot-synonymes-cm1";

    private static final int MINIMUM_D_ITEMS = 20;

    /** SYN-6 : l'item pilote transforme dans ce lot, identifie par sa bonne reponse unique au paquet. */
    private static final String BONNE_REPONSE_DE_L_ITEM_PILOTE = "malheureux";

    private static final int NOMBRE_D_ITEMS_SOUS_FORME_D_ORIGINE = 19;

    /**
     * Forme d'origine : le mot cible cite deux fois entre guillemets droits dans le meme enonce -
     * une fois dans la phrase qui lui donne son contexte, une fois dans la question qui demande son
     * remplacement - jamais une seule fois isolee. Rendu textuel choisi pour {@code th:text}
     * (fragments/manche.html echappe le HTML, aucun soulignement n'est disponible sans gabarit
     * neuf) : les guillemets tenaient lieu du "mot souligne" que l'arbitrage de nadia-prof illustre,
     * jusqu'a sa reserve du 2026-08-19 (le guillemet droit n'est pas une ponctuation francaise).
     */
    private static final Pattern MOT_ENTRE_GUILLEMETS_DROITS = Pattern.compile("\"([^\"]+)\"");

    /** SYN-6 : forme pilote, le mot cible cite une seule fois entre guillemets francais. */
    private static final Pattern MOT_ENTRE_GUILLEMETS_FRANCAIS = Pattern.compile("«\s*([^»]+?)\s*»");

    private static final String CLAUSE_INVARIANTE = "Dans cette phrase, quel mot peut remplacer";

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("SYN, comptage : au moins %d items", MINIMUM_D_ITEMS)
                .hasSizeGreaterThanOrEqualTo(MINIMUM_D_ITEMS);
    }

    @Test
    void lesDixNeufItemsDOrigineGardentLeurClauseInvarianteEtLeursGuillemetsDroits() {
        List<Question> itemsDOrigine = tousLesItems(paquet()).stream()
                .filter(item -> !item.bonneReponse().equals(BONNE_REPONSE_DE_L_ITEM_PILOTE))
                .toList();
        assertThat(itemsDOrigine)
                .as("SYN-8 : seul l'item pilote change de forme dans ce lot, les dix-neuf autres"
                        + " restent inchanges")
                .hasSize(NOMBRE_D_ITEMS_SOUS_FORME_D_ORIGINE);

        for (Question item : itemsDOrigine) {
            String enonce = item.enonce();
            List<String> motsCites = new ArrayList<>();
            Matcher trouve = MOT_ENTRE_GUILLEMETS_DROITS.matcher(enonce);
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
                    .contains(CLAUSE_INVARIANTE);
        }
    }

    /**
     * SYN-6, SYN-7, SYN-8 : l'item pilote ("triste"/"malheureux") marque son mot cible par des
     * guillemets francais, une seule fois, dans une phrase qui ne porte plus la clause invariante -
     * celle-ci est verifiee separement sur la consigne de catalogue
     * ({@link #laConsigneDeCatalogueDesigneLeMotEntreGuillemetsSansNommerAucunItem()}).
     */
    @Test
    void litemPiloteMarqueSonMotCibleParDesGuillemetsFrancaisSansClauseInvarianteDansLaPhrase() {
        Question pilote = tousLesItems(paquet()).stream()
                .filter(item -> item.bonneReponse().equals(BONNE_REPONSE_DE_L_ITEM_PILOTE))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "aucun item du paquet n'a pour bonne reponse '" + BONNE_REPONSE_DE_L_ITEM_PILOTE + "'"));
        String enonce = pilote.enonce();

        assertThat(enonce)
                .as("SYN-8 : la phrase de contexte n'a pas bouge d'un mot")
                .isEqualTo("Le petit garçon est « triste » depuis le départ de son ami.");

        Matcher guillemetsFrancais = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(enonce);
        assertThat(guillemetsFrancais.find())
                .as("'%s' (SYN-6) : le mot cible est marque par des guillemets francais", enonce)
                .isTrue();
        assertThat(guillemetsFrancais.group(1)).isEqualTo("triste");
        assertThat(guillemetsFrancais.find())
                .as("'%s' (SYN-6) : une seule occurrence - la clause invariante ne repete plus le"
                        + " mot cite une seconde fois dans l'enonce", enonce)
                .isFalse();

        assertThat(enonce)
                .as("'%s' (SYN-7) : la clause invariante a quitte l'enonce, elle vit dans la consigne"
                        + " de catalogue", enonce)
                .doesNotContain(CLAUSE_INVARIANTE);
        assertThat(enonce)
                .as("'%s' (SYN-6) : plus aucun guillemet droit sur cet item", enonce)
                .doesNotContain("\"");
    }

    /**
     * ADR-0052, SYN-6, SYN-7 : l'entree de catalogue neuve porte une consigne generique qui designe
     * le mot cible par sa marque exacte ("guillemets"), sans nommer aucun mot d'aucun item - une
     * consigne qui citerait un mot precis figerait le jeu sur cet item.
     */
    @Test
    void laConsigneDeCatalogueDesigneLeMotEntreGuillemetsSansNommerAucunItem() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        Map<String, Object> entree = jeux.stream()
                .filter(jeu -> ID_DE_CATALOGUE.equals(jeu.get("id")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        ID_DE_CATALOGUE + " : aucune entree de ce nom dans catalogue.yaml"));

        assertThat(entree.get("paquet")).isEqualTo(PAQUET);
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(consigne)
                .as("SYN-6 : la consigne designe le mot cible par sa marque exacte")
                .contains("guillemets");

        for (Question item : tousLesItems(paquet())) {
            assertThat(consigne)
                    .as("la consigne ne nomme aucune bonne reponse d'aucun item, elle reste generique"
                            + " pour les vingt", item.bonneReponse())
                    .doesNotContain(item.bonneReponse());
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCm1SynonymesTest.class.getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            return (Map<String, Object>) new org.yaml.snakeyaml.Yaml().load(flux);
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
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
