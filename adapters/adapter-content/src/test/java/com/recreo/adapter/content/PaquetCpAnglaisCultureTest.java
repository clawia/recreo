package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S47-LVE-CULTURE-CP, le paquet {@code cp-anglais-culture}, ligne {@code LVE-CP-06}</b> des
 * langues vivantes (anglais) au CP, domaine "Decouverte culturelle". Referentiel :
 * {@code squad/programme/langues-vivantes/competences-cp.md}. Arbitrage prealable cite par la
 * story ({@code squad/programme/MATRICE-couverture.md}, section "Langues vivantes (anglais) -
 * CP") et precise par {@code nadia-prof} sur la question Q5 de {@code theo-archi}
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md}).
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : tache A, decrire un aspect sans le nommer et
 * demander le pays (bonne reponse un nom de pays) ; tache B, le pays est nomme dans l'enonce et
 * l'item demande un detail (bonne reponse ne nomme jamais un pays).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Q5 (aucun mot ecrit a l'anglaise dans un texte que
 * l'enfant lit) demande une lecture de chaque mot a la redaction ; {@code nadia-prof} l'a
 * elle-meme ecrit non gardee par un garde-fou general de ce depot et reste la seule garde,
 * en jouant. L'exactitude des faits culturels retenus reste egalement sa garde.</p>
 */
class PaquetCpAnglaisCultureTest {

    private static final String PAQUET = "cp-anglais-culture";

    /** FER-2 : au moins onze items (DEC-0064, un paquet de fermeture porte strictement plus
     * d'items que de manches, nombreDeManches = 10 dans catalogue.yaml). */
    private static final int PLANCHER_FER_2 = 11;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** BIA-2 : au moins deux traits distincts par pays. */
    private static final int MINIMUM_PAR_PAYS = 2;

    /** Tache A : la bonne reponse est strictement l'un des deux pays servis. */
    private static final Set<String> PAYS_SERVIS = Set.of("le Royaume-Uni", "les États-Unis");

    /** FER-1 : les trois elements de la parenthese du libelle. */
    private static final Set<String> ELEMENTS_DU_LIBELLE = Set.of("lieu", "fete", "quotidien");

    /** FER-1 : marqueur par item, lu dans le fichier brut. */
    private static final Pattern MARQUEUR_ELEMENT =
            Pattern.compile("# element: (lieu|fete|quotidien)");

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

    private static boolean estDeTacheQuelEstCePays(Question item) {
        return PAYS_SERVIS.contains(item.bonneReponse());
    }

    private static List<Question> itemsQuelEstCePays() {
        return items().stream().filter(PaquetCpAnglaisCultureTest::estDeTacheQuelEstCePays).toList();
    }

    private static List<Question> itemsDetailDuPays() {
        return items().stream().filter(item -> !estDeTacheQuelEstCePays(item)).toList();
    }

    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCpAnglaisCultureTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** FER-2 : au moins onze items servent cette ligne. */
    @Test
    void fer2AuMoinsOnzeItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois elements de la parenthese du libelle (fetes, lieux, vie quotidienne) sont
     * chacun servis, marques par item dans le fichier brut.
     */
    @Test
    void fer1LesTroisElementsSontServis() {
        Set<String> elementsServis = new HashSet<>();
        Matcher marqueurs = MARQUEUR_ELEMENT.matcher(texteBrutDuPaquet());
        while (marqueurs.find()) {
            elementsServis.add(marqueurs.group(1));
        }

        assertThat(ELEMENTS_DU_LIBELLE)
                .as("tout marqueur # element: designe l'un des trois elements du libelle")
                .containsAll(elementsServis);
        assertThat(elementsServis)
                .as("FER-1 : les trois elements de la parenthese du libelle sont tous servis")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int quelEstCePays = itemsQuelEstCePays().size();
        int detailDuPays = itemsDetailDuPays().size();

        assertThat(quelEstCePays).as("la tache 'quel est ce pays' est servie").isGreaterThan(0);
        assertThat(detailDuPays).as("la tache 'detail du pays nomme' est servie").isGreaterThan(0);
        assertThat(Math.min(quelEstCePays, detailDuPays))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * La tache "detail du pays nomme" ne nomme jamais l'un des deux pays servis en bonne reponse.
     */
    @Test
    void laTacheDetailDuPaysNeNommeJamaisUnPaysEnBonneReponse() {
        for (Question item : itemsDetailDuPays()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache 'detail du pays nomme' decrit un fait, jamais l'un des"
                            + " deux pays servis", item.id().value())
                    .isNotIn(PAYS_SERVIS);
        }
    }

    /**
     * BIA-1 : aucune comparaison d'un aspect culturel a un aspect francais en position de norme.
     * Garde de forme : aucun item ne mentionne la France.
     */
    @Test
    void bia1AucunItemNeMentionneLaFrance() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s ne compare pas a la France", item.id().value())
                    .doesNotContain("France");
        }
    }

    /**
     * BIA-2 : un pays n'est jamais represente par un seul trait. Compte les items de la tache
     * "quel est ce pays" par pays : chacun des deux pays servis porte au moins deux traits
     * distincts (deux enonces distincts).
     */
    @Test
    void bia2AucunPaysNEstReduitAUnSeulTrait() {
        List<Question> tacheA = itemsQuelEstCePays();
        Set<String> enonces = tacheA.stream().map(Question::enonce).collect(Collectors.toSet());
        assertThat(enonces)
                .as("chaque item de la tache 'quel est ce pays' decrit un fait distinct")
                .hasSize(tacheA.size());

        Map<String, Long> comptesParPays = tacheA.stream()
                .collect(Collectors.groupingBy(Question::bonneReponse, Collectors.counting()));

        assertThat(comptesParPays.keySet())
                .as("les deux pays servis sont bien ceux declares")
                .containsExactlyInAnyOrderElementsOf(PAYS_SERVIS);
        comptesParPays.forEach((pays, compte) -> assertThat(compte)
                .as("BIA-2 : '%s' n'est pas reduit a un seul trait", pays)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_PAYS));
    }

    /** Chaque item propose exactement deux distracteurs, pour trois propositions au total. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    @Test
    void leDescripteurEstLanguesVivantesAnglaisDecouverteCulturelleCp() {
        var descripteur = new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Langues vivantes (anglais)");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Découverte culturelle");
        assertThat(descripteur.competence()).contains("aspects culturels");
    }

    /** Ligne de referentiel : LVE-CP-06 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementLveCp06() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("Découvrir quelques aspects culturels");
    }

    /**
     * Mention exigee d'avance sur la ligne LVE-CP-06, due dans la matrice avant que la ligne
     * passe a couvert-valide - portee des la livraison du paquet.
     */
    @Test
    void leCommentaireDuPaquetPorteLaMentionExigeeDAvance() {
        assertThat(texteBrutDuPaquet())
                .contains("découverte culturelle seule, en français ; aucune compétence"
                        + " linguistique n'est servie");
    }
}
