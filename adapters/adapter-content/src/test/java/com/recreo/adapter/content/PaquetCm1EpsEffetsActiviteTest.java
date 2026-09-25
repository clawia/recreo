package com.recreo.adapter.content;

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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S43-EPS-EFFETS-ACTIVITE, le paquet {@code cm1-eps-effets-activite}, ligne {@code
 * EPS-CM1-10}</b> d'education physique et sportive (CM1). Referentiel : {@code
 * squad/programme/education-physique-et-sportive/competences-cm1.md}, ligne 10, jugement de
 * faisabilite du 2026-09-03.
 *
 * <p><b>EPS-9 (nadia-prof, discriminant CM1/CP pose avant l'ecriture du paquet, docs/backlog/
 * backlog.md, sprint 43).</b> Trois taches, chacune absente de {@code cp-eps-corps-effort.yaml} et
 * de {@code cp-eps-regles-de-securite.yaml} : relier deux effets a une cause unique (items 1 a 4) ;
 * distinguer les effets selon le type d'effort, sans aucun nombre (items 5 a 9) ; reconnaitre un
 * signal d'alerte du corps pendant l'effort et la conduite qui y repond (items 10 a 13). Ce test ne
 * juge pas le fond de cette difference (lecture de sens, garde de {@code nadia-prof}), mais verifie
 * que les trois taches existent bien dans les proportions exigees et qu'aucun item ne reprend un
 * patron d'enonce deja epuise au CP.</p>
 *
 * <p><b>FER-1</b> : plusieurs effets du libelle sont en bonne reponse au moins une fois ("la soif",
 * "l'essoufflement", tache 2) et plusieurs regles de securite du libelle sont en bonne reponse au
 * moins une fois (les quatre conduites de la tache 3).</p>
 *
 * <p><b>EPS-2</b> : aucune performance chiffree n'est presentee comme une norme - verifie sur la
 * forme la plus simple a mordre sans lire le sens, aucun chiffre nulle part dans le paquet.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude de chaque fait de physiologie de l'effort
 * (BIA-1, BIA-2) et qu'aucune conduite de securite ne soit discutable - relecture de {@code
 * nadia-prof} au Done pedagogique.</p>
 */
class PaquetCm1EpsEffetsActiviteTest {

    private static final String PAQUET = "cm1-eps-effets-activite";

    /** DEC-0064 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** FER-1 : les effets du libelle attendus en bonne reponse sur la tache 2. */
    private static final Set<String> EFFETS_ATTENDUS_EN_BONNE_REPONSE = Set.of("la soif", "l'essoufflement");

    /**
     * FER-1 : les quatre conduites de securite de la tache 3, chacune attendue en bonne reponse.
     */
    private static final Set<String> CONDUITES_DE_SECURITE_ATTENDUES = Set.of(
            "arrêter l'effort, s'asseoir et boire",
            "ralentir l'allure ou s'arrêter jusqu'à ce que la douleur disparaisse",
            "arrêter l'effort et étirer doucement le muscle",
            "arrêter l'effort et prévenir un adulte");

    /**
     * EPS-9, clause 1 : les trois taches discriminantes doivent porter ensemble au moins deux tiers
     * des items, et aucune n'en porte moins de trois. {@link ContentSource#next} tire dans un ordre
     * gouverne par le hasard (ListContentSource), jamais par l'ordre de declaration du YAML : la
     * classification se fait donc par un marqueur textuel propre a chaque tache, jamais par un
     * index, pour rester vraie quel que soit l'ordre de tirage.
     */
    private static final String MARQUEUR_TACHE_1 = "ont la même cause";
    private static final String MARQUEUR_TACHE_2 = "lequel de ces ";
    private static final String MARQUEUR_TACHE_3 = "que doit-on faire";

    /** Seuil du plancher EPS-9 clause 1 : au moins deux tiers des items sur les trois taches. */
    private static final double SEUIL_DEUX_TIERS = 2.0 / 3.0;

    /** Plancher EPS-9 clause 1 : aucune tache discriminante ne porte moins de trois items. */
    private static final int PLANCHER_PAR_TACHE = 3;

    /**
     * EPS-9, clause 2 : patrons de tache deja epuises au CP par cp-eps-corps-effort.yaml et
     * cp-eps-regles-de-securite.yaml, absents de ce paquet. Verifie sur la forme la plus simple a
     * mordre - le debut exact de l'enonce que le CP emploie pour chacune de ses quatre taches.
     */
    private static final List<String> PATRONS_DEJA_SERVIS_AU_CP = List.of(
            "pendant un effort physique, que",
            "pendant un effort physique intense, que",
            "pourquoi respire-t-on", "pourquoi le cœur bat-il", "pourquoi a-t-on",
            "quand doit-on échauffer", "quand doit-on boire",
            "que risque-t-on si l'on commence", "que risque-t-on si l'on ne boit");

    /** EPS-9, clause 3 : aucune bonne reponse du CP ne doit devenir un distracteur de ce paquet. */
    private static final Set<String> BONNES_REPONSES_DU_CP = Set.of(
            "essoufflé", "il bat plus vite", "on a chaud", "on a soif",
            "encore rapide, puis elle ralentit", "il devient rouge",
            "pour apporter plus d'air aux muscles", "pour envoyer plus de sang aux muscles",
            "parce que les muscles produisent de la chaleur en travaillant",
            "parce que le corps perd de l'eau en transpirant",
            "le corps a encore besoin de beaucoup d'air pour se calmer",
            "les échauffer par des mouvements légers", "boire un peu d'eau",
            "d'un temps de repos", "avant de commencer un effort intense",
            "avant, pendant et après l'effort", "un peu fatigués",
            "pour éviter de se blesser", "pour remplacer l'eau perdue en transpirant",
            "pour que les muscles et le coeur récupèrent", "de se blesser plus facilement",
            "de se sentir mal ou d'avoir des vertiges",
            "attendre l'autorisation de l'adulte", "un casque",
            "qu'aucun camarade n'est en dessous", "que le camarade devant soit descendu",
            "la profondeur de l'eau", "attendre son tour",
            "pour protéger la tête en cas de chute", "pour ne pas lui tomber dessus",
            "pour porter secours en cas de danger", "pour éviter que deux enfants se heurtent",
            "pour éviter de se blesser en touchant le fond");

    /** Un chiffre, sous quelque forme que ce soit (EPS-2 : aucune performance chiffree en norme). */
    private static final Pattern UN_CHIFFRE = Pattern.compile("\\d");

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

    /** DEC-0064 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("DEC-0064 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** FER-1 : les effets attendus sont en bonne reponse sur la tache de distinction des efforts. */
    @Test
    void fer1LesEffetsAttendusSontEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : plusieurs effets du libelle doivent etre representes en bonne reponse")
                .containsAll(EFFETS_ATTENDUS_EN_BONNE_REPONSE);
    }

    /** FER-1 : les quatre conduites de securite sont chacune en bonne reponse. */
    @Test
    void fer1LesConduitesDeSecuriteSontEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : plusieurs regles de securite du libelle doivent etre representees en"
                        + " bonne reponse")
                .containsAll(CONDUITES_DE_SECURITE_ATTENDUES);
    }

    /**
     * EPS-9, clause 1 : les trois taches discriminantes portent ensemble au moins deux tiers des
     * items du paquet, et aucune n'en porte moins de trois. Chaque item est classe par un marqueur
     * textuel propre a sa tache ; un item qui ne porterait aucun des trois marqueurs (ou plus d'un)
     * ferait echouer la somme des trois lots, qui doit epuiser {@code items().size()}.
     */
    @Test
    void eps9Clause1LesTroisTachesDiscriminantesPortentAuMoinsDeuxTiersDesItems() {
        List<Question> items = items();
        int total = items.size();

        long tache1 = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_TACHE_1))
                .count();
        long tache2 = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_TACHE_2))
                .count();
        long tache3 = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_TACHE_3))
                .count();

        assertThat(tache1 + tache2 + tache3)
                .as("chaque item doit porter exactement un des trois marqueurs de tache, sans"
                        + " chevauchement ni item hors des trois taches discriminantes")
                .isEqualTo(total);

        double partDiscriminante = (double) (tache1 + tache2 + tache3) / total;
        assertThat(partDiscriminante)
                .as("EPS-9, clause 1 : les trois taches discriminantes portent au moins deux tiers"
                        + " des items")
                .isGreaterThanOrEqualTo(SEUIL_DEUX_TIERS);

        assertThat(tache1)
                .as("EPS-9, clause 1 : la tache 1 (relier deux effets a une cause unique) porte au"
                        + " moins trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_TACHE);
        assertThat(tache2)
                .as("EPS-9, clause 1 : la tache 2 (distinguer les effets selon le type d'effort)"
                        + " porte au moins trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_TACHE);
        assertThat(tache3)
                .as("EPS-9, clause 1 : la tache 3 (signal d'alerte et conduite) porte au moins"
                        + " trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_TACHE);
    }

    /**
     * EPS-9, clause 2 : aucun enonce de ce paquet ne reprend un patron de question deja epuise au CP
     * par cp-eps-corps-effort et cp-eps-regles-de-securite.
     */
    @Test
    void eps9Clause2AucunEnonceNeReprendUnPatronDejaServiAuCp() {
        for (Question item : items()) {
            String enonce = item.enonce().toLowerCase(Locale.FRENCH);
            for (String patron : PATRONS_DEJA_SERVIS_AU_CP) {
                assertThat(enonce)
                        .as("item %s : ce patron de question est deja epuise au CP, ce paquet de"
                                + " CM1 doit poser une autre tache (EPS-9, clause 2)",
                                item.id().value())
                        .doesNotStartWith(patron);
            }
        }
    }

    /**
     * EPS-9, clause 3 : aucune bonne reponse d'un item du CP ne devient un distracteur d'un item de
     * ce paquet.
     */
    @Test
    void eps9Clause3AucuneBonneReponseDuCpNeDevientUnDistracteurIci() {
        for (Question item : items()) {
            for (String distracteur : item.distracteurs()) {
                assertThat(BONNES_REPONSES_DU_CP)
                        .as("item %s : le distracteur \"%s\" est une bonne reponse d'un item du CP"
                                + " (EPS-9, clause 3, CYC-1)", item.id().value(), distracteur)
                        .doesNotContain(distracteur);
            }
        }
    }

    /**
     * EPS-9, clause 3 : aucun item de ce paquet ne reprend cm1-sciences-nutrition, item 5
     * ("Quand on court vite, quel organe se remplit d'air plus vite ?", SCI-CM1-09, deja servi) -
     * verifie sur la forme la plus simple a mordre, aucun item ne demande de nommer un organe. Le
     * cœur reste nommable comme moteur d'un effet ("le cœur qui bat plus vite") : c'est la question
     * "quel organe" qui est interdite, pas le mot.
     */
    @Test
    void eps9Clause3AucunItemNeDemandeDeNommerUnOrgane() {
        for (Question item : items()) {
            assertThat(item.enonce().toLowerCase(Locale.FRENCH))
                    .as("item %s : aucun item ne doit demander de nommer un organe, deja servi par"
                            + " cm1-sciences-nutrition (SCI-CM1-09)", item.id().value())
                    .doesNotContain("quel organe");
        }
    }

    /**
     * EPS-2 : aucune performance chiffree presentee comme une norme. Verifie sur la forme la plus
     * simple a mordre sans lire le sens - aucun chiffre n'apparait nulle part dans le paquet.
     */
    @Test
    void eps2AucunChiffreDansLePaquet() {
        for (Question item : items()) {
            assertThat(UN_CHIFFRE.matcher(item.enonce()).find())
                    .as("item %s : l'enonce ne doit porter aucun chiffre (EPS-2)", item.id().value())
                    .isFalse();
            assertThat(UN_CHIFFRE.matcher(item.bonneReponse()).find())
                    .as("item %s : la bonne reponse ne doit porter aucun chiffre (EPS-2)",
                            item.id().value())
                    .isFalse();
            for (String distracteur : item.distracteurs()) {
                assertThat(UN_CHIFFRE.matcher(distracteur).find())
                        .as("item %s : un distracteur ne doit porter aucun chiffre (EPS-2)",
                                item.id().value())
                        .isFalse();
            }
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Chaque item declare uniquement EPS-CM1-10, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et aucun
     * item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementEpsCm110() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCm1EpsEffetsActiviteTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("EPS-CM1-10");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
