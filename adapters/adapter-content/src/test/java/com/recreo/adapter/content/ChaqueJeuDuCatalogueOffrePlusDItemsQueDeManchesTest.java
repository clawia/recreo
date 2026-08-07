package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0023 : tout jeu du catalogue offre strictement plus d'items que sa partie n'a de manches.
 *
 * <p>En dessous, deux defauts silencieux. Un paquet plus court que la partie epuise
 * {@code ListContentSource} en cours de route : {@code BanditManchotSession.tirerManche} rend une
 * session TERMINEE, la partie s'arrete avant l'heure, sans erreur et sans message. Un paquet aussi
 * long que la partie ne produit aucune variete d'une partie a l'autre - c'est la reserve que
 * nadia-prof a posee en s04 sur les homophones, dix items pour dix manches. L'egalite est donc
 * refusee comme le defaut : l'inegalite est stricte, et les deux jeux d'artefacts de ce test
 * l'epinglent des deux cotes (voir {@link #uneEntreeDeCatalogueAussiCourteQueSaPartieFaitEchouerLeGardeFou}
 * et {@link #laMemeEntreeAvecUnItemDePlusPasseLeGardeFou}).</p>
 *
 * <p>ADR-0013 obligation 1, et c'est la raison d'etre de ce test : le perimetre est le
 * <b>catalogue</b>, lu par le chargeur reel, jamais une liste de paquets recopiee ici. La propriete
 * etait auparavant verifiee par deux methodes de {@code PaquetsFrancaisS04Test} qui nommaient chacune
 * leur paquet dans leur titre ; une entree neuve sortait donc du controle en silence, et c'est
 * exactement ce qui est arrive a bandit-manchot-conjugaison-present-cm1. Le catalogue est aussi la
 * seule source qui gouverne : {@code nombreDeManches} est un attribut de l'association mecanique +
 * paquet (ADR-0012), il n'existe pas ailleurs. Aucun paquet hors catalogue n'est juge ici, et c'est
 * voulu : sans entree de catalogue, il n'y a pas de nombre de manches auquel le comparer.</p>
 *
 * <p>ADR-0013 obligation 2 : la derivation est totale. Chaque entree du catalogue produit une
 * assertion, y compris une entree dont le paquet ne rend aucun item.</p>
 */
class ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest {

    /** ADR-0013 : le jeu fautif de ce garde-fou, un catalogue que personne n'a jamais servi. */
    private static final String CONTRE_EXEMPLE =
            "/contreexemple/ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest";

    /**
     * Budget de tirages par item vise. Un paquet enumere s'epuise de lui-meme et rend
     * {@code Optional.empty()} ; un paquet combinatoire ne s'epuise jamais et ne s'arrete que sur ce
     * budget. Sans lui, un paquet combinatoire trop pauvre ferait boucler le test au lieu de le faire
     * rougir.
     */
    private static final int TIRAGES_PAR_ITEM_VISE = 20;

    /**
     * Compte les items distincts que ce contenu sait rendre, en s'arretant des que
     * {@code vises} sont atteints : la propriete a prouver est un minimum, pas la taille du paquet,
     * et un paquet combinatoire n'a pas de taille. Le tirage passe par la chaine reelle
     * ({@code ContentSource.next} avec le tirage deja-vu qui grossit), celle qu'un enfant traverse.
     */
    private static int itemsDistincts(ContentSource contenu, int vises) {
        RandomSource hasard = new Random(11)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        int budget = vises * TIRAGES_PAR_ITEM_VISE;
        for (int tirage = 0; tirage < budget && dejaVus.size() < vises; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            dejaVus.add(tire.orElseThrow().id());
        }
        return dejaVus.size();
    }

    static void verifierQueChaqueJeuOffrePlusDItemsQueDeManches(String racineClasspath) {
        List<GameDefinition> jeux = new YamlContentPackLoader(racineClasspath).games();

        assertThat(jeux)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune entree de catalogue")
                .isNotEmpty();

        for (GameDefinition jeu : jeux) {
            int vises = jeu.nombreDeManches() + 1;
            assertThat(itemsDistincts(jeu.contenu(), vises))
                    .as("jeu %s : sa partie compte %d manches, son paquet doit donc savoir rendre au"
                            + " moins %d items distincts. Aussi court, la partie s'arrete avant"
                            + " l'heure sans erreur ni message ; aussi long, deux parties presentent"
                            + " toujours les memes items. Le comptage s'arrete a %d : il mesure ce"
                            + " minimum, pas la taille du paquet",
                            jeu.id(), jeu.nombreDeManches(), vises, vises)
                    .isGreaterThan(jeu.nombreDeManches());
        }
    }

    @Test
    void chaqueJeuDuCatalogueDeProductionOffrePlusDItemsQueDeManches() {
        verifierQueChaqueJeuOffrePlusDItemsQueDeManches("");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est une entree de catalogue que personne n'a inscrite
     * dans un test. Elle porte le cas limite exact - trois items pour trois manches - parce que c'est
     * lui qui distingue {@code >} de {@code >=} : une inegalite large laisserait passer ce jeu fautif
     * sans qu'aucun diff ne le montre.
     */
    @Test
    void uneEntreeDeCatalogueAussiCourteQueSaPartieFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueJeuOffrePlusDItemsQueDeManches(CONTRE_EXEMPLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("jeu-aussi-court-que-sa-partie")
                .hasMessageContaining("au moins 4 items distincts");
    }

    /**
     * Le meme catalogue, avec un item de plus et le meme nombre de manches, passe. Cette moitie-ci
     * epingle l'autre borne de l'inegalite : un garde-fou ecrit avec une marge (deux items de plus
     * qu'il ne faut, par exemple) refuserait ce corrige, et le message de l'autre test ne le dirait
     * pas.
     */
    @Test
    void laMemeEntreeAvecUnItemDePlusPasseLeGardeFou() {
        verifierQueChaqueJeuOffrePlusDItemsQueDeManches(CONTRE_EXEMPLE + "/corrige");
    }
}
