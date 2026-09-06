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
 * S27-3, arbitrage prealable de `nadia-prof` du 2026-09-02
 * (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, branche `matieres-absentes`,
 * commit `fcdc365`, section 5 et section 7.2) : le paquet "cm1-histoire-geographie", premiere
 * matiere neuve du produit sur ce referentiel. Ce paquet OUVRE des lignes de matrice, il n'en FERME
 * aucune (section 8.4 de l'arbitrage).
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (comptage, forme du descripteur, trois
 * propositions distinctes par item, et le plafond de `HIS-1`) : la justesse de chaque fait historique
 * ou geographique (`HIS-3`), l'absence de double reponse defendable (`HIS-2`) et l'absence de
 * jugement de valeur sur un personnage (`BIA-5`) restent un jugement de sens que ce depot confie a
 * `nadia-prof`, en jouant - aucun automate ne les porte, meme limite ecrite pour `COMP-2` sur
 * `PaquetCm1ComprehensionTest`.</p>
 *
 * <p><b>HIS-1 a vecu ici du 2026-09-02 au 2026-09-03, et vit desormais dans
 * {@code UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest} (ADR-0079). Le motif est
 * ecrit plutot que le deplacement laisse nu.</b> Le marqueur "# HIS-1: &lt;type&gt;" pose dans le
 * YAML servi ne change pas d'un caractere, et le mecanisme reste celui que marc-dev a ecrit - meme
 * patron que le marqueur "# COMP-3" de {@code PaquetCm1ComprehensionTest}, invisible a
 * {@code QuestionContentSourceFactory} qui ignore les commentaires.</p>
 *
 * <p><b>Ce qui change est la population, et c'est nadia-prof qui l'a demande</b> le 2026-09-03 :
 * son unite d'audit est le <b>lot de matiere</b> - jusqu'a vingt items d'une matiere et d'un niveau -
 * et non le fichier, parce qu'<i>"un ratio calcule sur huit items ne veut rien dire"</i>. La clause
 * qui vivait ici designait sa population par <b>un chemin de ressource ecrit en dur</b> et son
 * plafond par <b>une constante egale au tiers de vingt</b> : elle etait juste tant que l'histoire
 * tenait en un seul fichier, et <b>un second fichier du meme lot n'aurait ete juge par rien</b>.
 * Le tiers se compte desormais sur le lot, sur une population derivee des descripteurs servis.
 * <b>Ce n'est pas un defaut du travail de marc-dev</b> : c'est un perimetre enumere, juste tant que
 * le monde ressemble a l'enumeration - la meme famille que {@code FIG-4} etendue hors de son paquet
 * la veille (ADR-0077).</p>
 *
 * <p><b>S28-2 (arbitrage prealable de s28, section 3.2 et 5.1 ; DEC-0074, amendement du
 * 2026-09-03 ; docs/backlog/backlog.md, story S28-2) : second paquet, composition B.</b> Le fichier
 * passe de vingt a trente-sept items, sans en ouvrir un fichier de plus - le motif du decoupage est
 * ecrit dans le commentaire du paquet lui-meme. Les tests ci-dessous ajoutent ce que
 * {@code ADR-0081} ne juge pas : {@code FER-1}, {@code FER-2} et {@code FER-3} sur les lignes
 * {@code HG-CM1-11} (types d'espaces) et {@code HG-CM1-13} (eau, energie, aliments), lues sur le
 * YAML brut plutot que sur {@link Question} parce que {@code ligneDeReferentiel} n'entre pas dans ce
 * record - {@code ADR-0081} clause 6 : la cle est ignoree par {@code QuestionContentSourceFactory},
 * jamais par un chargeur de plus.</p>
 *
 * <p><b>S29-2 (arbitrage prealable de s29, sections 3.3, 4 et 5.1 ; DEC-0077 section 8 ;
 * docs/backlog/backlog.md, story S29-2) : troisieme paquet, composition A.</b> Le fichier passe de
 * trente-sept a cinquante-sept items, motif dans le commentaire du paquet. Les tests ajoutes ici
 * verifient {@code FER-1} et {@code FER-2} sur les lignes {@code HG-CM1-06} (rois et realisations),
 * {@code HG-CM1-07} (Revolution) et {@code HG-CM1-14} (comprendre un document), sur le meme patron
 * que la composition B.</p>
 */
class PaquetCm1HistoireGeographieTest {

    private static final String PAQUET = "cm1-histoire-geographie";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 57;

    private static final String LIGNE_TYPES_D_ESPACES = "HG-CM1-11";
    private static final String LIGNE_EAU_ENERGIE_ALIMENTS = "HG-CM1-13";
    private static final String LIGNE_ROIS_ET_REALISATIONS = "HG-CM1-06";
    private static final String LIGNE_REVOLUTION = "HG-CM1-07";
    private static final String LIGNE_COMPRENDRE_UN_DOCUMENT = "HG-CM1-14";

    /** FER-1 sur HG-CM1-06 : les quatre realisations du libelle, chacune en bonne reponse. */
    private static final Set<String> REALISATIONS_ROYALES =
            Set.of("la croisade", "la Renaissance", "l'édit de Nantes", "Versailles");

    /** FER-1 sur HG-CM1-07 : les quatre evenements du libelle, chacun en bonne reponse. */
    private static final Set<String> EVENEMENTS_REVOLUTION = Set.of(
            "en 1789", "l'abolition des privilèges",
            "la Déclaration des droits de l'homme et du citoyen", "la fin de la monarchie");

    /** FER-2 : au moins dix items servent une ligne fermee, tous paquets confondus. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /**
     * FER-3 sur {@code HG-CM1-11} : une bonne reponse qui nomme un des trois types d'espace est une
     * tache d'"identification" (decrire une scene ou comparer un fait, puis nommer le type) ; toute
     * autre bonne reponse est une tache d'"association-caracteristique" (le type est donne, une
     * caracteristique est demandee).
     */
    private static final Set<String> TYPES_D_ESPACE =
            Set.of("un espace urbain", "un espace périurbain", "un espace rural");

    /**
     * FER-3 sur {@code HG-CM1-13} : une bonne reponse qui nomme une etape ou un moyen d'acheminement
     * est une tache d'"acheminement" ; toute autre bonne reponse (lieu ou type d'origine de la
     * ressource) est une tache d'"origine".
     */
    private static final Set<String> REPONSES_ACHEMINEMENT = Set.of(
            "un château d'eau", "le traitement de l'eau", "un circuit court",
            "des lignes à haute tension");

    private static final Set<String> REPONSES_EAU = Set.of(
            "un château d'eau", "d'une rivière ou d'une nappe souterraine", "le traitement de l'eau");

    private static final Set<String> REPONSES_ENERGIE = Set.of(
            "une centrale électrique", "des éoliennes", "une énergie fossile",
            "des lignes à haute tension");

    private static final Set<String> REPONSES_ALIMENTS = Set.of(
            "un circuit court", "un aliment importé", "un aliment de saison");

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

    /**
     * Lecture du YAML brut, pour la seule cle que le domaine ne porte pas :
     * {@code ligneDeReferentiel} (ADR-0081, clause 6 - ignoree par le chargeur de production, lue
     * ici comme un garde-fou la lit). Meme chemin de classpath que
     * {@code YamlContentPackLoader.chargerPaquet}, jamais un chemin de fichier de production : ce
     * n'est pas un balayage au sens d'ADR-0013, seulement la lecture d'un paquet nomme, deja permise
     * sans jeu fautif par tous les {@code Paquet*Test} de ce module.
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsBruts() {
        String texte;
        try (InputStream flux = PaquetCm1HistoireGeographieTest.class
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

    private static List<Map<String, Object>> itemsDeLaLigne(String code) {
        return itemsBruts().stream()
                .filter(item -> code.equals(item.get("ligneDeReferentiel")))
                .toList();
    }

    @Test
    void lePaquetCompteExactementCinquanteSeptItems() {
        assertThat(tousLesItems(paquet()))
                .as("S29-2 : trente-sept items apres s28 plus vingt items neufs (six sur %s, sept sur"
                        + " %s, sept sur %s)",
                        LIGNE_ROIS_ET_REALISATIONS, LIGNE_REVOLUTION, LIGNE_COMPRENDRE_UN_DOCUMENT)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstHistoireGeographieCm1() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Histoire-géographie");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Repères historiques et géographiques");
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

    /**
     * La distribution exacte par ligne, apres S28-2 et S29-2 : les six lignes que ces deux lots ne
     * touchent pas restent a leur compte de s27, et les cinq lignes des compositions B et A
     * atteignent exactement le plancher de dix de {@code FER-2}. Une seule assertion prouve a la
     * fois que le lot ferme sur son perimetre et qu'il n'ouvre aucune ligne neuve de la matiere.
     */
    @Test
    void laDistributionParLigneEstExactementCelleDesCompositionsBEtA() {
        Map<String, Long> comptes = itemsBruts().stream()
                .collect(Collectors.groupingBy(
                        item -> String.valueOf(item.get("ligneDeReferentiel")),
                        Collectors.counting()));

        assertThat(comptes).containsOnly(
                Map.entry("HG-CM1-01", 2L), Map.entry("HG-CM1-02", 1L), Map.entry("HG-CM1-03", 1L),
                Map.entry("HG-CM1-04", 1L), Map.entry(LIGNE_ROIS_ET_REALISATIONS, 10L),
                Map.entry(LIGNE_REVOLUTION, 10L),
                Map.entry("HG-CM1-08", 1L), Map.entry(LIGNE_TYPES_D_ESPACES, 10L),
                Map.entry("HG-CM1-12", 1L), Map.entry(LIGNE_EAU_ENERGIE_ALIMENTS, 10L),
                Map.entry(LIGNE_COMPRENDRE_UN_DOCUMENT, 10L));
    }

    @Test
    void fer2EstTenueSurLesDeuxLignesDeLaCompositionB() {
        assertThat(itemsDeLaLigne(LIGNE_TYPES_D_ESPACES))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_TYPES_D_ESPACES)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_EAU_ENERGIE_ALIMENTS))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_EAU_ENERGIE_ALIMENTS)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    @Test
    void fer2EstTenueSurLesTroisLignesDeLaCompositionA() {
        assertThat(itemsDeLaLigne(LIGNE_ROIS_ET_REALISATIONS))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_ROIS_ET_REALISATIONS)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_REVOLUTION))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_REVOLUTION)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_COMPRENDRE_UN_DOCUMENT))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_COMPRENDRE_UN_DOCUMENT)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 sur HG-CM1-06 : les quatre realisations de la parenthese sont chacune servies. */
    @Test
    void fer1LesQuatreRealisationsRoyalesSontServiesEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_ROIS_ET_REALISATIONS).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : la croisade, la Renaissance, l'édit de Nantes et Versailles sont chacun"
                        + " servis en bonne réponse")
                .containsAll(REALISATIONS_ROYALES);
    }

    /** FER-1 sur HG-CM1-07 : les quatre evenements de la parenthese sont chacun servis. */
    @Test
    void fer1LesQuatreEvenementsDeLaRevolutionSontServisEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_REVOLUTION).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : 1789, l'abolition des privilèges, la Déclaration des droits de l'homme"
                        + " et du citoyen et la fin de la monarchie sont chacun servis en bonne"
                        + " réponse")
                .containsAll(EVENEMENTS_REVOLUTION);
    }

    /**
     * HG-CM1-14 (comprendre un document) : chaque item de la ligne s'appuie sur un texte-support,
     * et la ligne sert bien la nature et l'auteur du document, pas seulement sa date et une
     * information extraite (écart nommé d'avance a l'ouverture de S29-2).
     */
    @Test
    void hgCm114SertLaNatureEtLAuteurDuDocumentEnPlusDeLaDateEtDeLExtraction() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_COMPRENDRE_UN_DOCUMENT);

        assertThat(items).as("chaque item de %s s'appuie sur un texte-support",
                        LIGNE_COMPRENDRE_UN_DOCUMENT)
                .allMatch(item -> item.get("texteSupport") != null);

        List<String> enonces = items.stream().map(item -> String.valueOf(item.get("enonce")))
                .toList();
        assertThat(enonces).as("au moins un item interroge la nature du document")
                .anyMatch(enonce -> enonce.contains("type de document"));
        assertThat(enonces).as("au moins un item interroge l'auteur du document")
                .anyMatch(enonce -> enonce.contains("auteur") || enonce.contains("écrit ce texte"));
    }

    /** FER-1 sur HG-CM1-11 : les trois elements de la parenthese sont chacun en bonne reponse. */
    @Test
    void fer1LesTroisTypesDEspaceSontServisEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_TYPES_D_ESPACES).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : espace urbain, périurbain et rural sont chacun servis en bonne réponse")
                .containsAll(TYPES_D_ESPACE);
    }

    /** FER-1 sur HG-CM1-13 : les trois besoins de la parenthese sont chacun representes. */
    @Test
    void fer1LEauLEnergieEtLesAlimentsSontRepresentesEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_EAU_ENERGIE_ALIMENTS).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses).as("FER-1 : l'eau est représentée").containsAnyElementsOf(REPONSES_EAU);
        assertThat(reponses).as("FER-1 : l'énergie est représentée")
                .containsAnyElementsOf(REPONSES_ENERGIE);
        assertThat(reponses).as("FER-1 : les aliments sont représentés")
                .containsAnyElementsOf(REPONSES_ALIMENTS);
    }

    @Test
    void fer3AuMoinsDeuxTachesSurHgCm111LaMoinsServieAuMoinsTroisItems() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_TYPES_D_ESPACES);
        long identification = items.stream()
                .filter(item -> TYPES_D_ESPACE.contains(item.get("bonneReponse"))).count();
        long association = items.size() - identification;

        assertThat(identification).as("la tâche d'identification est servie").isGreaterThan(0);
        assertThat(association).as("la tâche d'association-caractéristique est servie")
                .isGreaterThan(0);
        assertThat(Math.min(identification, association))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    @Test
    void fer3AuMoinsDeuxTachesSurHgCm113LaMoinsServieAuMoinsTroisItems() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_EAU_ENERGIE_ALIMENTS);
        long acheminement = items.stream()
                .filter(item -> REPONSES_ACHEMINEMENT.contains(item.get("bonneReponse"))).count();
        long origine = items.size() - acheminement;

        assertThat(acheminement).as("la tâche d'acheminement est servie").isGreaterThan(0);
        assertThat(origine).as("la tâche d'origine est servie").isGreaterThan(0);
        assertThat(Math.min(acheminement, origine))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * HG-R3 (nadia-prof, 2026-09-03) : les deux distracteurs absurdes de l'ancien item sur l'espace
     * rural ne doivent plus figurer nulle part dans le paquet.
     */
    @Test
    void hgR3LesAnciensDistracteursAbsurdesOntDisparu() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : HG-R3 corrige l'item sur l'espace rural, ses anciens distracteurs"
                            + " absurdes ne doivent réapparaître sur aucun item", item.enonce())
                    .doesNotContain("la banque et l'assurance", "le tourisme de croisière");
        }
    }

    /**
     * HG-R6 (nadia-prof, 2026-09-06, S30-5) : sur les deux items de nature de HG-CM1-14, le
     * distracteur "une carte de France" s'éliminait sans rien savoir - l'enfant a un texte sous les
     * yeux. Rejeu borné, propriété à tenir, aucun texte imposé : le distracteur a disparu sur les
     * deux items, et au moins un des deux porte sur un support où le mot de la bonne réponse
     * n'apparaît pas.
     */
    @Test
    void hgR6LaCarteDeFranceADisparuEtUnItemNeRecopiePlusLeMotDeLaReponse() {
        List<Map<String, Object>> itemsDeNature = itemsDeLaLigne(LIGNE_COMPRENDRE_UN_DOCUMENT)
                .stream()
                .filter(item -> String.valueOf(item.get("enonce")).contains("type de document"))
                .toList();

        assertThat(itemsDeNature)
                .as("deux items de nature dans la ligne %s", LIGNE_COMPRENDRE_UN_DOCUMENT)
                .hasSize(2);

        for (Map<String, Object> item : itemsDeNature) {
            @SuppressWarnings("unchecked")
            List<String> distracteurs = (List<String>) item.get("distracteurs");
            assertThat(distracteurs)
                    .as("'%s' : la carte de France ne s'élimine plus sans lire le support",
                            item.get("enonce"))
                    .doesNotContain("une carte de France");
        }

        long itemsQuiRecopientLeMot = itemsDeNature.stream()
                .filter(item -> String.valueOf(item.get("texteSupport"))
                        .contains(String.valueOf(item.get("bonneReponse")).replace("un ", "")
                                .replace("une ", "").split(" ")[0]))
                .count();
        assertThat(itemsQuiRecopientLeMot)
                .as("au moins un des deux items ne se répond pas en recopiant un mot du support")
                .isLessThan(itemsDeNature.size());
    }

    /**
     * HG-R7 (nadia-prof, 2026-09-06, S30-5, texte fourni mot pour mot) : le texte-support de l'item
     * sur la prise de la Bastille annonçait que sa chute devenait fête nationale dès l'année
     * suivante - le 14 juillet n'est fête nationale que depuis 1880.
     */
    @Test
    void hgR7LeTexteSupportDeLaBastilleNAffirmePlusUneDateFausse() {
        Map<String, Object> item = itemsBruts().stream()
                .filter(candidat -> "un journal intime".equals(candidat.get("bonneReponse")) == false
                        && String.valueOf(candidat.get("texteSupport"))
                                .contains("prison qui servait aussi"))
                .findFirst()
                .orElseThrow();

        assertThat(item.get("texteSupport")).isEqualTo("En 1789, des habitants de Paris prennent"
                + " d'assaut une vieille prison qui servait aussi à enfermer des opposants au roi."
                + " Sa chute est fêtée dans toute la ville et, l'année suivante, une grande fête"
                + " réunit la France entière pour son anniversaire.");
        assertThat(item.get("bonneReponse")).isEqualTo("le pouvoir absolu et l'autorité du roi");
    }
}
