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
 * S19-4 (`DEC-0042`, `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`) : le paquet
 * "cp-nature-mot". Adaptation reelle, pas une reprise ({@code contenu-repris/mots/
 * cm1-nature-mot.yml} classe des mots isoles de CM1 en quatre natures ; la competence CP en vise
 * deux, dans une phrase, jamais un mot isole).
 *
 * <p><b>Arbitrage prealable de nadia-prof rendu le 2026-08-24</b>
 * (`squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md`, partie B) : la
 * forme est acceptee sous conditions, le premier brouillon de seize phrases
 * (`squad/sprints/s19-.../proposition-s19-4-lea-dev.md`) est refuse en l'etat pour un seul motif,
 * mesure et non suppose - les huit noms etaient tous precedes d'un determinant et aucun des huit
 * verbes ne l'etait, ce qui resout les seize items sans jamais lire le mot cible (`NAT-1`). Ce
 * paquet-ci est la reecriture demandee, sous les conditions bloquantes `NAT-1` a `NAT-5` et non
 * bloquantes `NAT-6` a `NAT-8`.</p>
 *
 * <p><b>`NAT-1` n'est verifie par aucun automate de ce depot</b> (nadia-prof le signale elle-meme
 * a `theo-archi` comme la seule condition qui lui semble mecanisable, sans demander qu'elle soit
 * ecrite maintenant) : ce fichier documente la repartition obtenue, ne la recalcule pas, et reste
 * donc une preuve de composition, pas une preuve automatique de l'absence de signal de position.
 * Le detail mot-par-mot est dans le commentaire du paquet servi.</p>
 *
 * <p><b>Ce que ce fichier ne couvre pas</b> : la lisibilite reelle des phrases pour un enfant de
 * CP, la lecture des mots "nom"/"verbe" sur les boutons et le renfort visuel du mot cible au-dela
 * des guillemets (`NAT-4`, `NAT-5`, `AUD-3`) restent la porte pedagogique de `nadia-prof`, et le
 * renfort visuel lui-meme est a proposer par `ines-design` avant qu'elle ne tranche.</p>
 */
class PaquetCpNatureMotTest {

    private static final String PAQUET = "cp-nature-mot";

    private static final String ID_DE_CATALOGUE = "bandit-manchot-nature-mot-cp";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    /**
     * `NAT-2` : les deux homographes nom/verbe qu'un CP connait dans les deux emplois (`joue` -
     * la joue, `lit` - le lit) ne doivent plus jamais figurer comme mot cible. `nage` et `court`
     * restent autorises (nadia-prof, partie B.3 : le nom homographe n'est pas dans le vocabulaire
     * actif d'un CP, ou l'autre emploi n'est pas un nom).
     */
    private static final Set<String> HOMOGRAPHES_EXCLUS = Set.of("joue", "lit");

    /** `NAT-7` : au plus un mot cible portant un -ent muet de pluriel. */
    private static final Pattern MOT_CIBLE_MOT_EN_ENT_MUET = Pattern.compile("(?i)^[a-zàâäéèêëïîôöùûüçœ]+ent$");

    private static final Pattern MOT_ENTRE_GUILLEMETS_FRANCAIS =
            Pattern.compile("« ([^»]+?) »");

    @Test
    void lePaquetCompteExactementSeizeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void laRepartitionNomVerbeEstEquilibree() {
        List<Question> items = tousLesItems(paquet());
        long noms = items.stream().filter(item -> "nom".equals(item.bonneReponse())).count();
        long verbes = items.stream().filter(item -> "verbe".equals(item.bonneReponse())).count();

        assertThat(noms).as("NAT-8 : repartition huit noms / huit verbes conservee").isEqualTo(8);
        assertThat(verbes).as("NAT-8 : repartition huit noms / huit verbes conservee").isEqualTo(8);
    }

    @Test
    void chaqueItemProposeExactementNomEtVerbe() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(Set.copyOf(item.distracteurs())).hasSize(1);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item \"%s\" : les deux seules propositions possibles sont 'nom' et 'verbe'",
                            item.enonce())
                    .containsExactlyInAnyOrder("nom", "verbe");
        }
    }

    @Test
    void aucunMotCibleNEstUnHomographeExclu() {
        for (Question item : tousLesItems(paquet())) {
            String motCible = motCibleDe(item.enonce());
            assertThat(HOMOGRAPHES_EXCLUS)
                    .as("NAT-2 : '%s' (item \"%s\") est un homographe nom/verbe qu'un CP connait"
                            + " dans les deux emplois - exclu par l'arbitrage prealable",
                            motCible, item.enonce())
                    .doesNotContain(motCible.toLowerCase());
        }
    }

    @Test
    void auPlusUnMotCiblePorteUnMoinsEntMuetDePluriel() {
        long compte = tousLesItems(paquet()).stream()
                .map(item -> motCibleDe(item.enonce()))
                .filter(motCible -> MOT_CIBLE_MOT_EN_ENT_MUET.matcher(motCible).matches())
                .count();

        assertThat(compte)
                .as("NAT-7 : au plus un mot cible avec un -ent muet de pluriel dans ce paquet")
                .isLessThanOrEqualTo(1);
    }

    @Test
    void chaqueMotCibleEstMarqueParDesGuillemetsFrancaisAvecDesEspacesInsecables() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            Matcher trouve = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(enonce);
            assertThat(trouve.find())
                    .as("'%s' (NAT-5, convention reprise de SYN-6/SYN-9) : le mot cible est marque"
                            + " par des guillemets francais avec des espaces insecables", enonce)
                    .isTrue();
            assertThat(trouve.find())
                    .as("'%s' : un seul mot cible marque par item", enonce)
                    .isFalse();
        }
    }

    @Test
    void lesSeizeEnoncesSontDistincts() {
        List<String> enonces = tousLesItems(paquet()).stream().map(Question::enonce).toList();
        assertThat(Set.copyOf(enonces))
                .as("NAT-8 : seize phrases distinctes, pas huit phrases vues deux fois sous deux"
                        + " mots cibles")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCpOrthographeEtGrammaireNatureDuMot() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence())
                .isEqualTo("Distinguer les principaux types de mots (nom, verbe) dans une phrase très simple");
    }

    @Test
    void lEntreeDeCatalogueDesigneLeBonPaquet() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        Map<String, Object> entree = jeux.stream()
                .filter(jeu -> ID_DE_CATALOGUE.equals(jeu.get("id")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        ID_DE_CATALOGUE + " : aucune entree de ce nom dans catalogue.yaml"));

        assertThat(entree.get("paquet")).isEqualTo(PAQUET);
    }

    private static String motCibleDe(String enonce) {
        Matcher trouve = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(enonce);
        if (!trouve.find()) {
            throw new AssertionError("'" + enonce + "' : aucun mot entre guillemets francais");
        }
        return trouve.group(1);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCpNatureMotTest.class.getResourceAsStream("/catalogue.yaml")) {
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
