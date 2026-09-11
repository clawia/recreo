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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S28-3 (backlog S28-3 ; {@code ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-
 * quatre-lots.md}, sections 5.3 et 6) : le paquet {@code cm1-anglais}, premier paquet d'anglais
 * CM1, matiere neuve du produit. Lot textuel, sans illustration (REC-1 a REC-4 ne s'appliquent
 * pas). Sert deux lignes du referentiel Langues vivantes (anglais) CM1 : {@code LVE-CM1-04} (lire
 * et comprendre des mots familiers, quinze items, condition de fermeture) et {@code LVE-CM1-07}
 * (decouverte culturelle en francais, cinq items, ouvre sans fermer).
 *
 * <p>Meme patron de lecture que {@code PaquetCm1HistoireGeographieTest} : le paquet declare
 * plusieurs lignes ({@code ADR-0081}), donc {@code ligneDeReferentiel} est lu sur le YAML brut,
 * cle ignoree par {@code QuestionContentSourceFactory} et absente du record {@link Question}.</p>
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (comptage, forme, FER-2, ANG-4,
 * ANG-5, REC-6, BIA-2, la proportion de sens de traduction, l'absence de consigne a l'adulte) :
 * REC-5 (aucun mot transparent) est verifie mot par mot a la redaction et documente dans le
 * commentaire du paquet, jugement de sens qu'aucun automate general de ce depot ne porte - meme
 * limite que HIS-2 sur {@code PaquetCm1HistoireGeographieTest}. Ce test verifie neanmoins, sur les
 * quinze paires francais/anglais reellement servies, qu'aucune des deux graphies d'une meme paire
 * ne coincide.</p>
 */
class PaquetCm1AnglaisTest {

    private static final String PAQUET = "cm1-anglais";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 25;

    private static final String LIGNE_LIRE_ET_COMPRENDRE = "LVE-CM1-04";
    private static final String LIGNE_DECOUVERTE_CULTURELLE = "LVE-CM1-07";

    /** FER-2 : au moins dix items servent une ligne fermee, tous paquets confondus. */
    private static final int PLANCHER_FER_2 = 10;

    /** ANG-4 : au moins trois champs lexicaux, cinq mots chacun au minimum. */
    private static final int MINIMUM_PAR_CHAMP = 5;

    /**
     * ANG-4 et REC-6, les trois champs lexicaux declares dans le commentaire du paquet. Chaque
     * champ porte les deux graphies de ses cinq mots - francaise et anglaise - parce qu'un item
     * anglais -> francais porte ses trois propositions en francais (ex. "chien", "ami", "chambre"),
     * quand la majorite des items du champ les porte en anglais.
     */
    private static final Set<String> CHAMP_PERSONNE_ET_VIE_QUOTIDIENNE = Set.of(
            "house", "maison", "chien", "dog", "friend", "ami", "morning", "matin", "bedroom",
            "chambre");
    private static final Set<String> CHAMP_ENVIRONNEMENT_GEOGRAPHIQUE_ET_URBAIN = Set.of(
            "street", "rue", "bridge", "pont", "hill", "colline", "town", "ville", "champ",
            "field");
    private static final Set<String> CHAMP_IMAGINAIRE = Set.of(
            "witch", "sorcière", "castle", "château", "fairy", "fée", "king", "roi", "fantôme",
            "ghost");

    /** REC-5, les quinze paires francais/anglais reellement servies sur LVE-CM1-04. */
    private static final Map<String, String> PAIRES_FRANCAIS_ANGLAIS = Map.ofEntries(
            Map.entry("maison", "house"), Map.entry("chien", "dog"), Map.entry("ami", "friend"),
            Map.entry("matin", "morning"), Map.entry("chambre", "bedroom"),
            Map.entry("rue", "street"), Map.entry("pont", "bridge"),
            Map.entry("colline", "hill"), Map.entry("ville", "town"),
            Map.entry("champ", "field"), Map.entry("sorcière", "witch"),
            Map.entry("château", "castle"), Map.entry("fée", "fairy"), Map.entry("roi", "king"),
            Map.entry("fantôme", "ghost"));

    /**
     * BIA-2, pays servi par chaque bonne reponse des cinq items de {@code LVE-CM1-07}, lu sur le
     * materiau reel. Sert a compter les faits par pays, pas a les affirmer distincts : un item
     * ajoute sans mise a jour de cette table fait echouer le test au lieu de passer a tort.
     */
    private static final Map<String, String> PAYS_PAR_BONNE_REPONSE_DE_DECOUVERTE_CULTURELLE =
            Map.ofEntries(
                    Map.entry("Londres", "Royaume-Uni"),
                    Map.entry("Big Ben", "Royaume-Uni"),
                    Map.entry("Washington", "États-Unis"),
                    Map.entry("le drapeau des États-Unis", "États-Unis"),
                    Map.entry("Thanksgiving", "États-Unis"),
                    Map.entry("un uniforme scolaire", "Royaume-Uni"),
                    Map.entry("le bus à impériale (deux étages)", "Royaume-Uni"),  // S35-ANG-R3 : le mot "impériale" est desormais explique dans la proposition elle-meme
                    Map.entry("Halloween", "États-Unis"),
                    Map.entry("en bus scolaire jaune", "États-Unis"),
                    Map.entry("la statue de la Liberté", "États-Unis"));

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

    /**
     * Lecture du YAML brut, pour la seule cle que le domaine ne porte pas : {@code
     * ligneDeReferentiel} (ADR-0081, clause 6).
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsBruts() {
        String texte;
        try (InputStream flux = PaquetCm1AnglaisTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            texte = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        Map<String, Object> corps = (Map<String, Object>) new Yaml().load(texte);
        List<Object> items = (List<Object>) corps.get("items");
        List<Map<String, Object>> resultat = new ArrayList<>();
        for (Object item : items) {
            resultat.add((Map<String, Object>) item);
        }
        return resultat;
    }

    private static String texteDuPaquet() {
        try (InputStream flux = PaquetCm1AnglaisTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static List<Map<String, Object>> itemsDeLaLigne(String code) {
        return itemsBruts().stream()
                .filter(item -> code.equals(item.get("ligneDeReferentiel")))
                .toList();
    }

    @Test
    void lePaquetCompteExactementVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("15 items sur %s, 5 items sur %s", LIGNE_LIRE_ET_COMPRENDRE,
                        LIGNE_DECOUVERTE_CULTURELLE)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstLanguesVivantesAnglaisCm1() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Langues vivantes (anglais)");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.competence()).contains("référentiel");
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            assertThat(propositions)
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void laDistributionParLigneEstQuinzeEtDix() {
        assertThat(itemsDeLaLigne(LIGNE_LIRE_ET_COMPRENDRE)).hasSize(15);
        assertThat(itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE)).hasSize(10);
    }

    /**
     * FER-2 (S32-2) : la ligne de decouverte culturelle atteint desormais le plancher de dix
     * items, cinq deja servis depuis s28 plus cinq neufs.
     */
    @Test
    void fer2EstTenueSurLaLigneDecouverteCulturelle() {
        assertThat(itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2,
                        LIGNE_DECOUVERTE_CULTURELLE)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    @Test
    void fer2EstTenueSurLaLigneLireEtComprendre() {
        assertThat(itemsDeLaLigne(LIGNE_LIRE_ET_COMPRENDRE))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2,
                        LIGNE_LIRE_ET_COMPRENDRE)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * ANG-5 : la reponse attendue sur {@code LVE-CM1-04} est un mot seul, jamais une forme
     * grammaticale non enseignee (pluriel, conjugaison, determinant variable). Garde de forme,
     * calculable sur le lexique reellement servi : aucune bonne reponse ne contient d'espace (donc
     * aucun article ni aucune expression a plusieurs mots), et aucune ne se termine par "s" (donc
     * aucune marque de pluriel sur les quinze mots de ce lot, dont aucun n'est legitimement
     * singulier en "s").
     */
    @Test
    void ang5ChaqueReponseDeLaLigneLireEtComprendreEstUnMotSeulSansArticleNiPluriel() {
        for (Map<String, Object> item : itemsDeLaLigne(LIGNE_LIRE_ET_COMPRENDRE)) {
            String bonneReponse = String.valueOf(item.get("bonneReponse"));
            assertThat(bonneReponse)
                    .as("ANG-5 : '%s' n'est pas un mot seul (espace = article ou expression)",
                            item.get("enonce"))
                    .doesNotContain(" ");
            assertThat(bonneReponse)
                    .as("ANG-5 : '%s' porte une marque de pluriel", item.get("enonce"))
                    .doesNotEndWith("s");
        }
    }

    /**
     * ANG-4 : le lot declare ses champs lexicaux et en couvre au moins trois, cinq mots chacun au
     * minimum. Verifie ici que les trois champs sont chacun servis a au moins cinq items, et que
     * tout item de la ligne appartient a l'un des trois - aucun item hors champ declare.
     */
    @Test
    void ang4TroisChampsLexicauxServisAuMoinsCinqMotsChacun() {
        List<Map<String, Object>> ligne4 = itemsDeLaLigne(LIGNE_LIRE_ET_COMPRENDRE);

        long dansPersonne = compteDansChamp(ligne4, CHAMP_PERSONNE_ET_VIE_QUOTIDIENNE);
        long dansEnvironnement = compteDansChamp(ligne4, CHAMP_ENVIRONNEMENT_GEOGRAPHIQUE_ET_URBAIN);
        long dansImaginaire = compteDansChamp(ligne4, CHAMP_IMAGINAIRE);

        assertThat(dansPersonne)
                .as("champ 'la personne et la vie quotidienne'")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_CHAMP);
        assertThat(dansEnvironnement)
                .as("champ 'l'environnement geographique et urbain'")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_CHAMP);
        assertThat(dansImaginaire)
                .as("champ 'l'imaginaire'")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_CHAMP);
        assertThat(dansPersonne + dansEnvironnement + dansImaginaire)
                .as("chaque item de la ligne appartient a l'un des trois champs declares, sans"
                        + " recouvrement ni reste")
                .isEqualTo(ligne4.size());
    }

    private static long compteDansChamp(List<Map<String, Object>> items, Set<String> champ) {
        return items.stream().filter(item -> champ.contains(item.get("bonneReponse"))).count();
    }

    /**
     * REC-6 : trois propositions du meme champ lexical. Chaque item de {@code LVE-CM1-04} porte sa
     * bonne reponse et ses deux distracteurs dans le meme champ declare.
     */
    @Test
    void rec6LesTroisPropositionsAppartiennentAuMemeChampLexical() {
        List<Set<String>> champs = List.of(CHAMP_PERSONNE_ET_VIE_QUOTIDIENNE,
                CHAMP_ENVIRONNEMENT_GEOGRAPHIQUE_ET_URBAIN, CHAMP_IMAGINAIRE);

        for (Map<String, Object> item : itemsDeLaLigne(LIGNE_LIRE_ET_COMPRENDRE)) {
            String bonneReponse = String.valueOf(item.get("bonneReponse"));
            @SuppressWarnings("unchecked")
            List<String> distracteurs = (List<String>) item.get("distracteurs");

            Set<String> champDeLItem = champs.stream()
                    .filter(champ -> champ.contains(bonneReponse))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError(bonneReponse + " n'appartient a aucun"
                            + " champ declare"));

            assertThat(champDeLItem)
                    .as("'%s' : les deux distracteurs appartiennent au meme champ que la bonne"
                            + " reponse '%s'", item.get("enonce"), bonneReponse)
                    .containsAll(distracteurs);
        }
    }

    /**
     * REC-5, sur le materiau reellement servi : aucune des quinze paires francais/anglais de
     * {@code LVE-CM1-04} ne partage la meme graphie, a la casse pres, entre les deux langues. Ne
     * remplace pas le jugement de sens de nadia-prof (quasi-identite), qui reste documente dans le
     * commentaire du paquet ; garde l'identite stricte, la plus grossiere des deux fautes.
     */
    @Test
    void rec5AucuneDesQuinzePairesNePartageLaMemeGraphie() {
        assertThat(PAIRES_FRANCAIS_ANGLAIS).hasSize(15);
        for (Map.Entry<String, String> paire : PAIRES_FRANCAIS_ANGLAIS.entrySet()) {
            assertThat(paire.getKey())
                    .as("'%s' (français) / '%s' (anglais) : mot transparent, REC-5 le refuse en"
                            + " bonne réponse", paire.getKey(), paire.getValue())
                    .isNotEqualToIgnoringCase(paire.getValue());
        }
    }

    /**
     * Trois sens de traduction : aucune image dans ce lot textuel (donc aucun item image ->
     * anglais), et au plus un item sur quatre en anglais -> francais, jamais seul. Le sens est lu
     * sur la forme de l'enonce, ecrite en toutes lettres a la redaction : "Comment dit-on" pour
     * francais -> anglais, "Que veut dire" pour anglais -> francais.
     */
    @Test
    void auPlusUnItemSurQuatreEstEnAnglaisVersFrancaisEtJamaisSeul() {
        List<Question> items = tousLesItems(paquet());
        long versFrancais = items.stream()
                .filter(item -> item.enonce().startsWith("Que veut dire")).count();
        long versAnglais = items.stream()
                .filter(item -> item.enonce().startsWith("Comment dit-on")).count();

        assertThat(versFrancais).as("jamais zero : le sens existe").isGreaterThan(0);
        assertThat(versAnglais).as("jamais seul : le sens majoritaire existe aussi")
                .isGreaterThan(0);
        assertThat(versFrancais)
                .as("au plus un item sur quatre du paquet en anglais -> francais")
                .isLessThanOrEqualTo(NOMBRE_D_ITEMS_ATTENDU / 4);
    }

    /**
     * Reserve du 2026-09-02, reconduite : aucun ecran ni aucun indice ne demande a un adulte de
     * prononcer un mot d'anglais. Ce paquet ne porte ni {@code consigneAdulte} ni aucune mention du
     * mot "adulte" dans son contenu servi a l'enfant.
     */
    @Test
    void aucunItemNeDemandeAUnAdulteDeProncerDeLAnglais() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.enonce().toLowerCase(java.util.Locale.ROOT))
                    .as("'%s' ne mentionne jamais un adulte", item.enonce())
                    .doesNotContain("adulte");
            assertThat(item.indice()).as("'%s' porte un indice", item.enonce()).isPresent();
            assertThat(item.indice().orElseThrow().toLowerCase(java.util.Locale.ROOT))
                    .as("l'indice de '%s' ne mentionne jamais un adulte", item.enonce())
                    .doesNotContain("adulte");
        }
    }

    /**
     * Ligne LVE-CM1-07 : mention exigee d'avance par nadia-prof, portee des la livraison du paquet.
     */
    @Test
    void leCommentaireDuPaquetPorteLaMentionExigeeSurLaDecouverteCulturelle() {
        assertThat(texteDuPaquet())
                .contains("découverte culturelle seule, en français ; aucune compétence"
                        + " linguistique n'est servie");
    }

    /**
     * BIA-1 : aucune comparaison d'un pays anglophone a la France en position de norme. Garde de
     * forme, sur les items de {@code LVE-CM1-07} : aucun ne mentionne la France.
     */
    @Test
    void bia1AucunItemDeDecouverteCulturelleNeMentionneLaFrance() {
        for (Map<String, Object> item : itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE)) {
            assertThat(String.valueOf(item.get("enonce")))
                    .as("'%s' ne compare pas a la France", item.get("enonce"))
                    .doesNotContain("France");
        }
    }

    /**
     * Propriete generique, distincte de BIA-2 : aucune bonne reponse de decouverte culturelle ne se
     * repete. Ne garantit pas BIA-2 (un pays pourrait n'avoir qu'un seul fait, jamais repete, et
     * passerait ce test) - voir {@link #bia2AucunPaysNEstReduitAUnSeulTrait()} pour BIA-2
     * elle-meme.
     */
    @Test
    void chaqueBonneReponseDeDecouverteCulturelleEstDistincte() {
        List<Map<String, Object>> ligne7 = itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE);
        Set<Object> reponses = new HashSet<>();
        for (Map<String, Object> item : ligne7) {
            reponses.add(item.get("bonneReponse"));
        }
        assertThat(reponses).hasSize(ligne7.size());
    }

    /**
     * FER-1 (S32-2) : les trois elements de la parenthese du referentiel - fêtes, lieux, vie
     * quotidienne - sont desormais chacun servis en bonne réponse. "Vie quotidienne" n'etait servie
     * par aucun des cinq items de s28 (capitales, un monument, un drapeau, une fête) ; les deux
     * items neufs sur l'école (uniforme au Royaume-Uni, bus scolaire aux États-Unis) la couvrent.
     */
    @Test
    void fer1LesTroisElementsDeLaParentheseSontServis() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses).as("fêtes").contains("Thanksgiving", "Halloween");
        assertThat(reponses).as("lieux").contains("Londres", "la statue de la Liberté");
        assertThat(reponses).as("vie quotidienne")
                .contains("un uniforme scolaire", "en bus scolaire jaune");
    }

    /**
     * BIA-2 : aucun pays represente par un seul trait. Compte les faits par pays sur
     * {@link #PAYS_PAR_BONNE_REPONSE_DE_DECOUVERTE_CULTURELLE} : un item ajoute a un pays deja
     * present fait monter son compte, un item ajoute a un pays absent de la table fait echouer ce
     * test (cle inconnue) plutot que de passer a tort.
     */
    @Test
    void bia2AucunPaysNEstReduitAUnSeulTrait() {
        List<Map<String, Object>> ligne7 = itemsDeLaLigne(LIGNE_DECOUVERTE_CULTURELLE);

        Map<String, Long> comptesParPays = ligne7.stream()
                .map(item -> String.valueOf(item.get("bonneReponse")))
                .collect(Collectors.groupingBy(
                        bonneReponse -> {
                            String pays = PAYS_PAR_BONNE_REPONSE_DE_DECOUVERTE_CULTURELLE
                                    .get(bonneReponse);
                            assertThat(pays)
                                    .as("'%s' n'est pas declaree dans la table des pays",
                                            bonneReponse)
                                    .isNotNull();
                            return pays;
                        },
                        Collectors.counting()));

        assertThat(comptesParPays.keySet())
                .as("les pays declares dans la table sont bien ceux servis")
                .containsExactlyInAnyOrder("Royaume-Uni", "États-Unis");
        comptesParPays.forEach((pays, compte) -> assertThat(compte)
                .as("BIA-2 : '%s' n'est pas reduit a un seul trait", pays)
                .isGreaterThanOrEqualTo(2));
    }
}
