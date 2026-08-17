package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * s14, dette technique 1 (avis-faisabilite.md, section 8.1) : remplace cinq methodes figees, une
 * par paquet ({@code PaquetCm1MesuresTest}, {@code PaquetCm1PerimetreTest},
 * {@code PaquetCpComplements10S9Test}, {@code PaquetCpSoustractionsS9Test},
 * {@code PaquetCm1GrandsNombresTest}, methodes {@code leJeuDuCatalogue...ChargeBienLePaquet...}),
 * retirees dans le meme commit. En s13, le retrait de "bandit-manchot-grands-nombres-cm1" du
 * catalogue (DEC-0031) a laisse sa methode de test derriere elle, corrigee a la main dans un
 * commit separe : rien ne liait le couple retrait-du-catalogue / methode-de-test.
 *
 * <p>ADR-0039 refuse la derivation paquets -> entrees (elle rougirait a chaque retrait volontaire,
 * DEC-0009, DEC-0014, DEC-0031). Celle-ci va dans l'autre sens, entrees -> paquets, qui ne rougit
 * jamais sur un retrait : la population de ce garde-fou est {@code YamlContentPackLoader.games()},
 * derivee du catalogue reel (ADR-0013 obligation 1). Une entree retiree de catalogue.yaml
 * disparait donc de la boucle sans qu'aucune ligne de ce fichier ne change ; sa restitution la
 * restitue dans le meme geste.</p>
 *
 * <p>Le paquet attendu pour chaque entree est lu directement dans catalogue.yaml (le champ
 * "paquet"), jamais recopie ici : c'est ce que l'entree designe, pas ce qu'un auteur de test croit
 * qu'elle designe. La comparaison exerce deux chemins de chargement distincts du meme chargeur
 * reel - {@code games()}, qui charge tout le catalogue d'un coup, et {@code chargerPaquet(id)},
 * appele isolement pour ce seul paquet - et prouve qu'ils rendent les memes items pour la meme
 * entree.</p>
 *
 * <p><b>Ce que cette clause ne couvre pas</b>, par rapport aux cinq methodes qu'elle remplace.
 * Chacune fixait, en plus de l'id du jeu, le nom du paquet attendu comme une constante Java
 * independante de catalogue.yaml ; un catalogue qui aurait associe par erreur le mauvais paquet a
 * un jeu (par exemple "cm1-perimetre" au jeu des mesures) les aurait fait echouer. Cette clause-ci
 * derive le paquet attendu de catalogue.yaml lui-meme : elle ne peut donc jamais detecter une
 * mauvaise association ecrite dans le catalogue, seulement une divergence entre les deux chemins
 * de chargement du chargeur reel. Elle ne verifie pas non plus qu'un paquet du repertoire DOIVE
 * avoir une entree de catalogue - une decision produit non encodee (avis-faisabilite.md section
 * 8.1) : un paquet volontairement hors catalogue (DEC-0009, DEC-0014, DEC-0031 : "le bon son", "le
 * bon verbe", "les grands nombres" ont chacun connu ce statut ; "cm1-homophones" aujourd'hui) reste
 * hors de sa portee, gardee par {@code ChaquePaquetPorteUnIndiceTest} et consorts qui derivent du
 * repertoire. Elle ne verifie pas non plus le nombre de manches declare
 * ({@code YamlContentPackLoaderTest}, et pour "cp-complements-10",
 * {@code PaquetCpComplements10S9Test}).</p>
 */
class ChaqueEntreeDuCatalogueChargeBienSonPaquetTest {

    /**
     * "cp-additions-jusqu-a-10" (type 'additions-bornees') est combinatoire et ne rend jamais
     * {@code Optional.empty()} (voir {@code AdditionsBorneesContentSource.next}) : une boucle
     * "jusqu'a epuisement" comme celle des cinq methodes remplacees ne termine pas sur ce paquet.
     * Le tirage est donc borne : au-dela du plus grand paquet enumere connu du depot
     * (cm1-tables-multiplication, 99 items), la marge suffit a epuiser tout paquet enumere et a
     * comparer deterministiquement, sur un echantillon fixe, un paquet combinatoire sans
     * l'enumerer.
     */
    private static final int TIRAGES = 150;

    /** ADR-0013 obligation 1 : le paquet attendu vient du catalogue reel, jamais d'une liste ecrite ici. */
    private static Map<String, String> paquetParEntreeDuCatalogue() {
        try (InputStream flux = ChaqueEntreeDuCatalogueChargeBienSonPaquetTest.class
                .getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            Map<String, String> parEntree = new LinkedHashMap<>();
            for (Map<String, Object> jeu : jeux) {
                parEntree.put(String.valueOf(jeu.get("id")), String.valueOf(jeu.get("paquet")));
            }
            return parEntree;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void chaqueEntreeDuCatalogueDeProductionChargeBienLePaquetQuElleDesigne() {
        YamlContentPackLoader chargeur = new YamlContentPackLoader();
        List<GameDefinition> jeux = chargeur.games();
        Map<String, String> paquetAttendu = paquetParEntreeDuCatalogue();

        assertThat(jeux)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune entree de catalogue")
                .isNotEmpty();

        for (GameDefinition jeu : jeux) {
            String paquetId = paquetAttendu.get(jeu.id());
            assertThat(paquetId)
                    .as("entree de catalogue '%s' : champ 'paquet' lu dans catalogue.yaml", jeu.id())
                    .isNotNull();

            assertThat(itemsTires(jeu.contenu()))
                    .as("l'entree de catalogue '%s' charge bien le paquet qu'elle designe ('%s')",
                            jeu.id(), paquetId)
                    .isEqualTo(itemsTires(chargeur.chargerPaquet(paquetId)));
        }
    }

    /**
     * Tire jusqu'a TIRAGES items, en s'arretant plus tot si le paquet est enumere et s'epuise
     * (Optional.empty()). Un paquet combinatoire, qui ne s'epuise jamais, est ainsi compare sur un
     * echantillon borne plutot que jamais atteint.
     */
    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Question question = (Question) tire.orElseThrow();
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }
}
