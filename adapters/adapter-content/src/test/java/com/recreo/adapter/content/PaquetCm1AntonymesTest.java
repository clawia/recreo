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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S19-1 (FICHE-bandit-manchot-antonymes-cm1.md, docs/backlog/backlog.md) : le paquet
 * "cm1-antonymes". Arbitrage prealable rendu par nadia-prof
 * (ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md, partie A) sur la proposition de
 * marc-dev (squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/
 * proposition-marc-antonymes-cm1.md) : ACCEPTE SOUS CONDITIONS, ANT-1 a ANT-7.
 *
 * <p>Ne couvre que les criteres automatisables. ANT-1 (la substitution produit une phrase francaise
 * correcte qui dit le contraire, sur la bonne reponse comme sur chaque distracteur) et ANT-3
 * (aucune erreur de fait) exigent un jugement de sens qu'aucun automate de ce depot ne porte -
 * exactement SYN-2/SYN-3 pour "Les synonymes".</p>
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, une seule graphie de matiere, l'entree
 * de catalogue charge bien ce paquet) sont deja derivees du repertoire de contenu par les
 * garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1AntonymesTest {

    private static final String PAQUET = "cm1-antonymes";

    /**
     * ANT-2, ANT-4 : dix-huit items proposes, cinq repris ou retires (item "jour"/"nuit" retire,
     * les quatre autres corriges) - dix-sept items livres, au-dessus du plancher de seize.
     */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 17;

    /**
     * ANT-5 : le mot cible est marque par des guillemets francais, avec un espace insecable
     * (U+00A0, jamais l'espace ordinaire U+0020) immediatement a l'interieur de chaque guillemet -
     * meme convention que SYN-6/SYN-9 sur "Les synonymes".
     */
    private static final Pattern MOT_ENTRE_GUILLEMETS_FRANCAIS =
            Pattern.compile("« ([^»]+?) »");

    /**
     * ANT-6 : au plus un antonyme forme par un prefixe de negation courant (in-, im-, de-, mal-)
     * ajoute au mot cible - le paquet en compte exactement un aujourd'hui ("poli"/"impoli"), et
     * cette condition borne ce qui serait ajoute plus tard sans le refuser.
     */
    private static final List<String> PREFIXES_DE_NEGATION = List.of("in", "im", "de", "mal");

    @Test
    void lePaquetCompteExactementDixSeptItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void chaqueItemMarqueSonMotCibleParDesGuillemetsFrancaisAvecDesEspacesInsecables() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            Matcher trouve = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(enonce);

            assertThat(trouve.find())
                    .as("'%s' (ANT-5) : le mot cible est marque par des guillemets francais avec"
                            + " des espaces insecables immediatement a l'interieur", enonce)
                    .isTrue();
            assertThat(trouve.find())
                    .as("'%s' : le mot cible n'est cite qu'une seule fois", enonce)
                    .isFalse();
            assertThat(enonce)
                    .as("'%s' : aucun guillemet droit", enonce)
                    .doesNotContain("\"");
        }
    }

    /** ANT-6 : au plus un item ne se distingue du mot cible que par un prefixe de negation. */
    @Test
    void auPlusUnAntonymeEstFormeParUnPrefixeDeNegation() {
        List<Question> items = tousLesItems(paquet());
        long comptePrefixe = items.stream()
                .filter(PaquetCm1AntonymesTest::estFormeParUnPrefixeDeNegation)
                .count();

        assertThat(comptePrefixe)
                .as("ANT-6 : le paquet en compte exactement un aujourd'hui (\"poli\"/\"impoli\") -"
                        + " un item de plus formerait un contraire par simple reconnaissance du"
                        + " radical, sans passer par le sens")
                .isEqualTo(1L);
    }

    private static boolean estFormeParUnPrefixeDeNegation(Question item) {
        Matcher motCible = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(item.enonce());
        if (!motCible.find()) {
            return false;
        }
        String cible = motCible.group(1);
        String bonneReponse = item.bonneReponse();
        return PREFIXES_DE_NEGATION.stream().anyMatch(prefixe -> bonneReponse.equals(prefixe + cible));
    }

    /**
     * ADR-0052, sur le modele de "Les synonymes" : l'entree de catalogue porte une consigne
     * generique qui designe le mot cible par sa marque exacte ("guillemets"), sans nommer aucun
     * mot d'aucun item.
     */
    @Test
    void laConsigneDeCatalogueEstGeneriqueEtNeNommeAucuneBonneReponse() {
        Map<String, Object> entree = entreeQuiSertLePaquet();
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(consigne)
                .as("la consigne designe le mot cible par sa marque exacte")
                .contains("guillemets");
        assertThat(consigne)
                .as("le mot 'contraire' porte toute la distinction de tache avec les synonymes")
                .contains("contraire");

        for (Question item : tousLesItems(paquet())) {
            assertThat(consigne)
                    .as("la consigne ne nomme aucune bonne reponse d'aucun item, elle reste"
                            + " generique pour les dix-sept", item.bonneReponse())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /**
     * `ADR-0061` : l'entree est trouvee par le paquet qu'elle sert, jamais par un identifiant de jeu
     * recopie en constante ici - le couple (entree, paquet) vit dans catalogue.yaml et nulle part
     * ailleurs. Une clause qui juge la consigne d'une entree exposee n'a plus d'objet le jour ou le
     * jeu quitte le catalogue : elle part alors avec lui, dans le meme commit que le retrait.
     */
    private static Map<String, Object> entreeQuiSertLePaquet() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        return jeux.stream()
                .filter(jeu -> PAQUET.equals(jeu.get("paquet")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "aucune entree de catalogue ne sert le paquet \"" + PAQUET + "\" : si le jeu"
                                + " a quitte le catalogue, cette clause de consigne part dans le meme"
                                + " commit que le retrait (ADR-0061)"));
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCm1AntonymesTest.class.getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            return (Map<String, Object>) new org.yaml.snakeyaml.Yaml().load(flux);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void leDescripteurEstFrancaisCm1VocabulaireSynonymesEtAntonymes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
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
