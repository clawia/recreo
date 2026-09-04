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
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-2 puis S28-1, le paquet {@code cp-qlm-etats-eau}, ligne 5 de "Questionner le monde"
 * (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-
 * et-le-prix-des-quatre-lots.md}, section 3.1. Encode le plafond de vingt items, l'espace de
 * reponse ferme aux deux etats que le referentiel nomme ("solide", "liquide" - jamais "gazeux"),
 * et les deux taches que le paquet porte depuis S28-1.
 *
 * <p><b>Deux partitions distinctes dans ce fichier, pour deux questions differentes.</b> L'espace
 * de reponse se lit sur la forme de la bonne reponse : <b>classement</b> (un des deux etats) contre
 * <b>nommage</b> (un des deux changements). FER-3 se lit sur l'operation mentale demandee :
 * <b>reconnaissance directe</b> d'un solide ou d'un liquide statique, contre <b>changement
 * d'etat</b> - qui regroupe les deux items de changement d'origine (classes par etat resultant) et
 * les deux items de nommage neufs a S28-1 (qui nomment le changement lui-meme). Les deux partitions
 * ne se recouvrent pas terme a terme : les items de changement d'etat d'origine sont, selon la
 * question posee, du cote "classement" de la premiere et du cote "changement d'etat" de la
 * seconde.</p>
 */
class PaquetCpQlmEtatsEauTest {

    private static final String PAQUET = "cp-qlm-etats-eau";

    private static final int PLAFOND_ITEMS = 20;

    /** Espace de reponse de la tache de classement. Perimetre tenu au mot pres du referentiel. */
    private static final Set<String> ETATS = Set.of("solide", "liquide");

    /** Espace de reponse de la tache de nommage, neuve a S28-1. */
    private static final Set<String> CHANGEMENTS = Set.of("fondre", "geler");

    /**
     * Distracteurs autorises pour la tache de nommage : les deux changements d'etat, et le verbe
     * "couler" (confusion plausible avec un liquide qui se deplace, sans introduire un troisieme
     * etat). Refus d'avance de nadia-prof (2026-09-03) : ni vapeur, ni evaporation, ni etat
     * gazeux.
     */
    private static final Set<String> DISTRACTEURS_DE_NOMMAGE_AUTORISES = Set.of("fondre", "geler",
            "couler");

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

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

    private static boolean estDeClassement(Question item) {
        return ETATS.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(PaquetCpQlmEtatsEauTest::estDeClassement).toList();
    }

    private static List<Question> itemsDeNommage() {
        return items().stream().filter(item -> !estDeClassement(item)).toList();
    }

    /**
     * FER-3 se compte sur l'operation mentale demandee, pas sur la forme de la bonne reponse : les
     * deux items de changement d'etat d'origine ("Quand la glace fond, ..."), qui classent encore
     * un etat, portent la meme operation - reperer un changement observable - que les deux items de
     * nommage neufs ("Comment appelle-t-on ..."), qui la nomment. "fond" et "gèle" ne figurent dans
     * aucun enonce de reconnaissance directe : ce sont les deux seuls mots qui distinguent un item
     * de changement d'etat d'un item de reconnaissance directe sur un solide ou un liquide statique.
     */
    private static boolean concerneUnChangementDEtat(Question item) {
        return CHANGEMENTS.contains(item.bonneReponse()) || item.enonce().contains("fond")
                || item.enonce().contains("gèle");
    }

    private static List<Question> itemsDeChangementDEtat() {
        return items().stream().filter(PaquetCpQlmEtatsEauTest::concerneUnChangementDEtat).toList();
    }

    private static List<Question> itemsDeReconnaissanceDirecte() {
        return items().stream().filter(item -> !concerneUnChangementDEtat(item)).toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * La competence du referentiel ne nomme que deux etats ("solide", "liquide") : aucun item de
     * classement ni aucun distracteur ne doit introduire un troisieme etat (ex. "gazeux").
     */
    @Test
    void lEspaceDeReponseDesItemsDeClassementEstExactementLesDeuxEtatsDuReferentiel() {
        for (Question item : itemsDeClassement()) {
            assertThat(ETATS)
                    .as("item %s : la bonne reponse doit etre l'un des deux etats du referentiel",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(ETATS)
                    .as("item %s : chaque distracteur doit etre l'un des deux etats du referentiel",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution ecrite dans le commentaire du paquet : parmi les items de
     * classement, chaque etat est reponse sur exactement la moitie. Compte reel, jamais affirme.
     */
    @Test
    void chacunDesDeuxEtatsEstReponseSurLaMoitieDesItemsDeClassement() {
        List<Question> items = itemsDeClassement();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les deux etats doivent toutes etre representes comme reponse")
                .containsExactlyInAnyOrderElementsOf(ETATS);

        int total = items.size();
        assertThat(total % comptes.size())
                .as("une moitie exacte suppose un total pair")
                .isZero();
        int moitie = total / comptes.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("l'etat \"%s\" doit etre reponse sur exactement la moitie des %d items de"
                            + " classement, soit %d", entree.getKey(), total, moitie)
                    .isEqualTo(moitie);
        }
    }

    /**
     * Refus d'avance de nadia-prof (2026-09-03) : aucun item de nommage n'introduit un troisieme
     * etat ni ne parle d'evaporation. L'espace de reponse est exactement les deux changements
     * observables que le referentiel couvre.
     */
    @Test
    void lEspaceDeReponseDesItemsDeNommageEstExactementLesDeuxChangementsDEtat() {
        List<Question> items = itemsDeNommage();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(CHANGEMENTS)
                    .as("item %s : la bonne reponse doit etre l'un des deux changements d'etat",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(DISTRACTEURS_DE_NOMMAGE_AUTORISES)
                    .as("item %s : aucun distracteur ne nomme un etat gazeux, une vapeur ou une"
                            + " evaporation", item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * FER-3 (nadia-prof, 2026-09-03) : au moins deux taches distinctes, la moins servie portant au
     * moins trois items - ici quatre, comme son arbitrage l'exige nommement pour cette ligne.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void auMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int reconnaissance = itemsDeReconnaissanceDirecte().size();
        int changementDEtat = itemsDeChangementDEtat().size();

        assertThat(reconnaissance).as("la reconnaissance directe est servie").isGreaterThan(0);
        assertThat(changementDEtat).as("le changement d'etat est servi").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, changementDEtat))
                .as("FER-3 : la tache la moins servie porte au moins %d items",
                        MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Precision de nadia-prof propre a cette ligne : la moitie "changement d'etat" est portee a
     * quatre items <b>et</b> sous une seconde forme - le nommage neuf s'ajoute au classement
     * d'origine, il ne le remplace pas.
     */
    @Test
    void laMoitieChangementDEtatPorteAuMoinsQuatreItemsSousDeuxFormes() {
        List<Question> changementDEtat = itemsDeChangementDEtat();

        assertThat(changementDEtat.size())
                .as("nadia-prof, 2026-09-03 : la moitie changement d'etat porte au moins 4 items")
                .isGreaterThanOrEqualTo(4);
        assertThat(changementDEtat.stream().filter(PaquetCpQlmEtatsEauTest::estDeClassement).count())
                .as("forme d'origine : classer le resultat du changement (solide ou liquide)")
                .isGreaterThan(0);
        assertThat(changementDEtat.stream().filter(item -> !estDeClassement(item)).count())
                .as("forme neuve a S28-1 : nommer le changement lui-meme (fondre ou geler)")
                .isGreaterThan(0);
    }
}
