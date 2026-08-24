package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S19-2 (`DEC-0042`, `squad/conformite/FICHE-bandit-manchot-accords-groupe-nominal-cm1.md`) : le
 * paquet "cm1-accords-groupe-nominal". Matiere premiere partiellement reutilisable
 * ({@code contenu-repris/textes-trous/cm1-accords.yml}, 5 items, adjectif seul) : la competence
 * visee nomme le groupe nominal entier (determinant/nom/adjectif), donc le paquet est etendu aux
 * trois composantes plutot que reprises telles quelles.
 *
 * <p><b>Aucun arbitrage prealable de nadia-prof n'etait requis pour cette story</b> (fiche de
 * conformite ci-dessus) : ce fichier couvre donc les criteres d'acceptation automatisables de
 * bout en bout, la justesse pedagogique de chaque phrase restant le Done pedagogique de
 * nadia-prof.</p>
 *
 * <p><b>Un seul trou par item, jamais un groupe nominal a plusieurs trous</b>
 * (avis-faisabilite.md s19, section 2.b) : la mecanique bandit-manchot compare une seule chaine
 * par manche, donc un groupe nominal a plusieurs trous n'aurait ete jouable que si une seule
 * proposition les remplissait tous a la fois. Un trou unique par item evite cette contrainte
 * entierement, au prix de ne jamais faire porter un meme item sur deux composantes a la fois -
 * limite nommee ici plutot que decouverte a l'audit.</p>
 *
 * <p><b>La composante testee par un item se derive du nombre de distracteurs</b>, jamais d'une
 * cle neuve dans le schema (ADR-0007 n'en gagne pas une pour ce paquet) : un item de determinant
 * porte deux distracteurs (trois determinants reels en presence), un item de nom porte un seul
 * distracteur (l'unique erreur de nombre sur ce nom), un item d'adjectif porte trois distracteurs
 * (le patron des quatre formes deja valide sur {@code cm1-accords.yml}). Les trois comptes ne se
 * recoupent jamais dans ce paquet, ce qui rend la derivation totale sur les 24 items.</p>
 *
 * <p><b>Limite de couverture nommee des l'ouverture</b> (camille-po, fiche de conformite,
 * `DEC-0013`) : les trois composantes tiennent toutes dans ce seul paquet, a raison de huit items
 * chacune - aucune limite a nommer sur ce point precis. La limite reelle est celle du paragraphe
 * precedent : un item ne teste jamais l'accord simultane de deux composantes du meme groupe
 * nominal, seulement une a la fois.</p>
 */
class PaquetCm1AccordsGroupeNominalTest {

    private static final String PAQUET = "cm1-accords-groupe-nominal";

    private static final String ID_DE_CATALOGUE = "bandit-manchot-accords-groupe-nominal-cm1";

    private static final int NOMBRE_D_ITEMS_MINIMUM = 20;

    /**
     * Determinants reels du francais employes par ce paquet, bonne reponse et distracteurs
     * confondus. Un distracteur de determinant doit toujours appartenir a cet ensemble - jamais un
     * determinant qui n'existe pas en francais (critere de la fiche de conformite).
     */
    private static final Set<String> DETERMINANTS_REELS = Set.of(
            "le", "la", "les", "un", "une", "des", "ce", "cette", "ces",
            "son", "sa", "ses", "mon", "ma", "mes", "ton", "ta", "tes");

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("le paquet doit compter au moins %d items (checklist d'ouverture, sans"
                        + " condition pedagogique fixee par nadia-prof)", NOMBRE_D_ITEMS_MINIMUM)
                .hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_MINIMUM);
    }

    @Test
    void chacuneDesTroisComposantesEstRepresenteeParAuMoinsUnQuartDesItems() {
        List<Question> items = tousLesItems(paquet());
        long determinants = items.stream().filter(item -> item.distracteurs().size() == 2).count();
        long noms = items.stream().filter(item -> item.distracteurs().size() == 1).count();
        long adjectifs = items.stream().filter(item -> item.distracteurs().size() == 3).count();

        assertThat(determinants + noms + adjectifs)
                .as("chaque item du paquet doit se classer dans une des trois composantes du"
                        + " groupe nominal par son nombre de distracteurs (2, 1 ou 3) : un item hors"
                        + " de ce classement n'est ni compte ni couvert par ce garde-fou")
                .isEqualTo(items.size());

        double quart = items.size() / 4.0;
        assertThat(determinants).as("determinant : au moins un quart des items").isGreaterThanOrEqualTo((long) Math.ceil(quart));
        assertThat(noms).as("nom : au moins un quart des items").isGreaterThanOrEqualTo((long) Math.ceil(quart));
        assertThat(adjectifs).as("adjectif : au moins un quart des items").isGreaterThanOrEqualTo((long) Math.ceil(quart));
    }

    @Test
    void unItemDeDeterminantNePorteQueDesDeterminantsReels() {
        for (Question item : tousLesItems(paquet())) {
            if (item.distracteurs().size() != 2) {
                continue;
            }
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(DETERMINANTS_REELS)
                        .as("'%s' (item \"%s\") : un item de determinant ne propose jamais un"
                                + " determinant qui n'existe pas en francais", proposition, item.enonce())
                        .contains(proposition.toLowerCase());
            }
        }
    }

    @Test
    void unItemDeNomNePorteQuUneSeuleErreurDeNombreSurLeMemeMot() {
        for (Question item : tousLesItems(paquet())) {
            if (item.distracteurs().size() != 1) {
                continue;
            }
            String distracteur = item.distracteurs().get(0);
            assertThat(distracteur)
                    .as("'%s' (item \"%s\") : un item de nom ne porte qu'un seul distracteur,"
                            + " l'unique erreur de nombre sur ce nom", distracteur, item.enonce())
                    .isNotEqualTo(item.bonneReponse());
        }
    }

    @Test
    void unItemDAdjectifPorteLesQuatreFormesDuPatronDejaValide() {
        for (Question item : tousLesItems(paquet())) {
            if (item.distracteurs().size() != 3) {
                continue;
            }
            Set<String> quatreFormes = new HashSet<>(item.distracteurs());
            quatreFormes.add(item.bonneReponse());
            assertThat(quatreFormes)
                    .as("item \"%s\" : le patron deja valide sur cm1-accords.yml porte quatre"
                            + " formes distinctes de l'adjectif, une seule accordee juste",
                            item.enonce())
                    .hasSize(4);
        }
    }

    @Test
    void leDescripteurEstFrancaisCm1OrthographeEtGrammaireAccordsDuGroupeNominal() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere())
                .as("ADR-0049 : la meme graphie accentuee que les autres paquets servis")
                .isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence())
                .isEqualTo("Accorder en genre et en nombre dans le groupe nominal (déterminant/nom/adjectif)");
    }

    @Test
    void lEntreeDeCatalogueDesignerLeBonPaquetSousUneConsigneGenerique() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        Map<String, Object> entree = jeux.stream()
                .filter(jeu -> ID_DE_CATALOGUE.equals(jeu.get("id")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        ID_DE_CATALOGUE + " : aucune entree de ce nom dans catalogue.yaml"));

        assertThat(entree.get("paquet")).isEqualTo(PAQUET);
        assertThat(String.valueOf(entree.get("consigne")))
                .as("consigne generique, deja servie par les autres jeux a trou de ce catalogue")
                .isEqualTo("Quel mot complète la phrase ?");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCm1AccordsGroupeNominalTest.class.getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            return (Map<String, Object>) new org.yaml.snakeyaml.Yaml().load(flux);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
