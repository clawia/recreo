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
import java.util.LinkedHashMap;
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
 * <p><b>S35-CONS-4 (docs/backlog/backlog.md ; DEC-0088 ; avis de faisabilite de s35 de theo-archi,
 * section 6) : le fichier unique est scinde en quatre paquets, un par tache reellement distincte
 * (`S34-CONS-4`, decision de grain rendue conjointement par `camille-po` et `nadia-prof`,
 * `ADR-0081`).</b> Ce fichier de test ne teste donc plus <i>un</i> paquet mais <i>quatre</i>, et ses
 * assertions sont ecrites pour rester justes quel que soit celui des quatre qui porte un item donne
 * : {@link #itemsDeLaLigne(String)} agrege les items d'une ligne de referentiel <b>tous paquets
 * confondus</b>, exactement comme {@code FER-2} se compte deja "tous paquets confondus" ailleurs
 * dans ce depot ({@code ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest}). C'est le cas de
 * {@code HG-CM1-07}, dont les dix items se repartissent entre "cm1-histoire-geographie-situer-dans-
 * le-temps" (cinq items) et "cm1-histoire-geographie-nommer-en-histoire" (cinq items) - repartition
 * laissee au choix de qui execute par `S34-CONS-4`, `nadia-prof` ayant verifie que le grain ne
 * change pas quel que soit cet arbitrage.
 *
 * <p><b>Ce que la scission retire de ce fichier</b> : {@code cons1LaConsigneNommeLaTacheDeNommerUn
 * PeupleOuUnMouvementDePopulation}, dont la premisse (une consigne unique du catalogue doit nommer
 * une phrase precise) ne tient plus des lors que quatre consignes distinctes, une par tache, la
 * remplacent - decision deja rendue par `S34-CONS-4`/`DEC-0088`, pas reouverte ici. Chacune des
 * quatre consignes est desormais auditee par `nadia-prof`, texte devant items, comme `RES-3`
 * l'exige.
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (comptage, forme du descripteur, trois
 * propositions distinctes par item, et le plafond de `HIS-1`) : la justesse de chaque fait historique
 * ou geographique (`HIS-3`), l'absence de double reponse defendable (`HIS-2`) et l'absence de
 * jugement de valeur sur un personnage (`BIA-5`) restent un jugement de sens que ce depot confie a
 * `nadia-prof`, en jouant - aucun automate ne les porte, meme limite ecrite pour `COMP-2` sur
 * `PaquetCm1ComprehensionTest`.</p>
 *
 * <p><b>HIS-1 a vecu ici du 2026-09-02 au 2026-09-03, et vit desormais dans
 * {@code UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest} (ADR-0079).</b> Le
 * marqueur "# HIS-1: &lt;type&gt;" pose dans le YAML servi ne change pas d'un caractere sur aucun
 * des quatre paquets issus de la scission, et le mecanisme reste celui que marc-dev a ecrit - meme
 * patron que le marqueur "# COMP-3" de {@code PaquetCm1ComprehensionTest}, invisible a
 * {@code QuestionContentSourceFactory} qui ignore les commentaires. Le lot de matiere (matiere,
 * niveau) reste inchange par la scission (DEC-0088, section 3) : le tiers de restitution continue de
 * se compter sur les 110 items, quel que soit le nombre de fichiers qui les portent.</p>
 */
class PaquetCm1HistoireGeographieTest {

    /** Les quatre paquets issus de la scission de S35-CONS-4, dans l'ordre du tableau de grain. */
    private static final List<String> PAQUETS = List.of(
            "cm1-histoire-geographie-situer-dans-le-temps",
            "cm1-histoire-geographie-nommer-en-histoire",
            "cm1-histoire-geographie-nommer-en-geographie",
            "cm1-histoire-geographie-lire-un-document");

    /**
     * Le compte est garde exactement, comme sur tous les paquets de ce depot. S42-HG-ROIS ajoute
     * cinq items neufs (trois sur "nommer, en histoire", deux sur "situer dans le temps"), portant
     * la ligne HG-CM1-05, de 110 a 115.
     */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 115;

    private static final String LIGNE_FRISE = "HG-CM1-01";
    private static final String LIGNE_SIECLE_ET_MILLENAIRE = "HG-CM1-02";
    private static final String LIGNE_PEUPLES_ANCIENS = "HG-CM1-03";
    private static final String LIGNE_MOUVEMENTS_DE_POPULATION = "HG-CM1-04";
    private static final String LIGNE_ROIS_DU_ROYAUME = "HG-CM1-05";
    private static final String LIGNE_TYPES_D_ESPACES = "HG-CM1-11";
    private static final String LIGNE_EAU_ENERGIE_ALIMENTS = "HG-CM1-13";
    private static final String LIGNE_ROIS_ET_REALISATIONS = "HG-CM1-06";
    private static final String LIGNE_REVOLUTION = "HG-CM1-07";
    private static final String LIGNE_COMPRENDRE_UN_DOCUMENT = "HG-CM1-14";
    private static final String LIGNE_REVOLUTION_A_L_EMPIRE = "HG-CM1-08";
    private static final String LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE = "HG-CM1-12";

    private static final List<String> TOUTES_LES_LIGNES = List.of(LIGNE_FRISE,
            LIGNE_SIECLE_ET_MILLENAIRE, LIGNE_PEUPLES_ANCIENS, LIGNE_MOUVEMENTS_DE_POPULATION,
            LIGNE_ROIS_ET_REALISATIONS, LIGNE_REVOLUTION, LIGNE_REVOLUTION_A_L_EMPIRE,
            LIGNE_TYPES_D_ESPACES, LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE, LIGNE_EAU_ENERGIE_ALIMENTS,
            LIGNE_COMPRENDRE_UN_DOCUMENT);

    /** HIS-5 (nadia-prof, 2026-09-09) : la cellule deja comptee et fermee sur HG-CM1-11. */
    private static final String REPONSE_DEJA_COMPTEE_SUR_HG_CM1_11 = "des immeubles collectifs";

    /** FER-1 sur HG-CM1-08 : la moitie du libelle qu'aucun item ne servait avant S33-HG-08-12. */
    private static final Set<String> CE_QUE_NAPOLEON_CHANGE = Set.of(
            "le Code civil", "des lycées", "la Légion d'honneur", "un préfet", "la Banque de France");

    /** FER-1 sur HG-CM1-01 : les cinq periodes de la parenthese, chacune servie. */
    private static final Set<String> GRANDES_PERIODES = Set.of(
            "la Préhistoire", "l'Antiquité", "le Moyen Âge", "les Temps modernes",
            "l'époque contemporaine");

    /** FER-1 sur HG-CM1-03 : les trois peuples de la parenthese, chacun servi. */
    private static final Set<String> PEUPLES_ANCIENS = Set.of("les Gaulois", "les Grecs",
            "les Romains");

    /** FER-1 sur HG-CM1-06 : les quatre realisations du libelle, chacune en bonne reponse. */
    private static final Set<String> REALISATIONS_ROYALES =
            Set.of("la croisade", "la Renaissance", "l'édit de Nantes", "Versailles");

    /**
     * S42-HG-ROIS, critere d'acceptation "les quatre rois du libelle sont chacun en bonne reponse
     * au moins une fois" sur HG-CM1-05 : les quatre rois nommes par le libelle de la story.
     */
    private static final Set<String> ROIS_DU_ROYAUME =
            Set.of("Louis IX", "François Ier", "Henri IV", "Louis XIV");

    /**
     * FER-1 sur HG-CM1-07 : les quatre evenements du libelle, chacun en bonne reponse. Depuis
     * S35-CONS-4, les dix items de cette ligne se repartissent entre deux paquets
     * ("cm1-histoire-geographie-situer-dans-le-temps" et "cm1-histoire-geographie-nommer-en-
     * histoire") ; {@link #itemsDeLaLigne(String)} les recompose avant que ce test ne les lise.
     */
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

    /** Enumere tous les items distincts d'un paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
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

    private static ContentSource paquet(String id) {
        return new YamlContentPackLoader().chargerPaquet(id);
    }

    /** Les items des quatre paquets issus de la scission, agreges par le chargeur reel. */
    private static List<Question> tousLesItemsDeTousLesPaquets() {
        List<Question> tous = new ArrayList<>();
        for (String id : PAQUETS) {
            tous.addAll(tousLesItems(paquet(id)));
        }
        return tous;
    }

    /**
     * Lecture du YAML brut d'un paquet nomme, pour les cles que le domaine ne porte pas :
     * {@code lignesDeReferentiel} et {@code ligneDeReferentiel} (ADR-0081, clause 6 - ignorees par
     * le chargeur de production, lues ici comme un garde-fou les lit). Meme chemin de classpath que
     * {@code YamlContentPackLoader.chargerPaquet}, jamais un chemin de fichier de production : ce
     * n'est pas un balayage au sens d'ADR-0013, seulement la lecture d'un paquet nomme, deja permise
     * sans jeu fautif par tous les {@code Paquet*Test} de ce module.
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> corpsBrutDe(String paquetNom) {
        String texte;
        try (InputStream flux = PaquetCm1HistoireGeographieTest.class
                .getResourceAsStream("/contenu/" + paquetNom + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + paquetNom + ".yaml introuvable sur le"
                        + " classpath");
            }
            texte = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return (Map<String, Object>) new Yaml().load(texte);
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsBrutsDe(String paquetNom) {
        List<Object> items = (List<Object>) corpsBrutDe(paquetNom).get("items");
        List<Map<String, Object>> resultat = new ArrayList<>();
        for (Object item : items) {
            resultat.add((Map<String, Object>) item);
        }
        return resultat;
    }

    /** Les items bruts des quatre paquets, sans filtre de ligne. */
    private static List<Map<String, Object>> itemsBruts() {
        List<Map<String, Object>> tous = new ArrayList<>();
        for (String paquetNom : PAQUETS) {
            tous.addAll(itemsBrutsDe(paquetNom));
        }
        return tous;
    }

    /**
     * Les items qui servent une ligne de referentiel donnee, <b>tous paquets confondus</b> - le seul
     * changement de comportement que S35-CONS-4 introduit dans ce fichier. Un paquet qui ne sert
     * qu'une seule ligne (cas de "cm1-histoire-geographie-lire-un-document", HG-CM1-14 seule, DEC-
     * 0088) attribue tous ses items a cette ligne sans qu'aucun d'eux ne porte la cle
     * {@code ligneDeReferentiel} (ADR-0081, clause 6) ; un paquet qui en sert plusieurs lit la ligne
     * de chaque item individuellement. Meme regle que
     * {@code ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.itemsParLigne}.
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsDeLaLigne(String code) {
        List<Map<String, Object>> resultat = new ArrayList<>();
        for (String paquetNom : PAQUETS) {
            Map<String, Object> corps = corpsBrutDe(paquetNom);
            List<Object> lignesDeclarees = (List<Object>) corps.get("lignesDeReferentiel");
            boolean uneSeuleLigne = lignesDeclarees != null && lignesDeclarees.size() == 1;
            for (Map<String, Object> item : itemsBrutsDe(paquetNom)) {
                String ligneDeCetItem = uneSeuleLigne
                        ? String.valueOf(lignesDeclarees.get(0))
                        : String.valueOf(item.get("ligneDeReferentiel"));
                if (code.equals(ligneDeCetItem)) {
                    resultat.add(item);
                }
            }
        }
        return resultat;
    }

    /**
     * Le partage entre les deux taches d'une ligne est epingle, jamais seulement minore. Voir
     * l'original de cette methode, inchange par S35-CONS-4 : la scission ne deplace aucun texte
     * d'item, seulement le fichier qui le porte.
     */
    private static void partageDesTachesEpingle(String ligne, String nomA, long effectifA,
                                                 int attenduA, String nomB, long effectifB,
                                                 int attenduB) {
        assertThat(effectifA)
                .as("%s : la tâche \"%s\" porte exactement %d items", ligne, nomA, attenduA)
                .isEqualTo(attenduA);
        assertThat(effectifB)
                .as("%s : la tâche \"%s\" porte exactement %d items", ligne, nomB, attenduB)
                .isEqualTo(attenduB);
    }

    @Test
    void lesQuatrePaquetsComptentAuTotalCentDixItems() {
        assertThat(tousLesItemsDeTousLesPaquets())
                .as("S35-CONS-4 : les quatre paquets issus de la scission portent ensemble les cent"
                        + " dix items existants, aucun perdu et aucun duplique")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void chaquePaquetADescripteurHistoireGeographieCm1() {
        for (String id : PAQUETS) {
            var descripteur = paquet(id).descriptor();

            assertThat(descripteur.matiere())
                    .as("%s : meme descripteur matiere sur les quatre paquets (DEC-0088)", id)
                    .isEqualTo("Histoire-géographie");
            assertThat(descripteur.niveau())
                    .as("%s : meme descripteur niveau sur les quatre paquets (DEC-0088)", id)
                    .isEqualTo("CM1");
            assertThat(descripteur.domaine()).isEqualTo("Repères historiques et géographiques");
            assertThat(descripteur.competence()).as("%s", id).contains("référentiel");
        }
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItemsDeTousLesPaquets()) {
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
     * La distribution exacte par ligne, apres S28-2, S29-2, S32-1, S33-HG-08-12 et S35-CONS-4 : les
     * onze lignes du paquet atteignent chacune exactement le plancher de dix de {@code FER-2}, tous
     * paquets confondus.
     */
    @Test
    void laDistributionParLigneEstExactementDixItemsSurChacuneDesOnzeLignes() {
        Map<String, Integer> comptes = new LinkedHashMap<>();
        for (String ligne : TOUTES_LES_LIGNES) {
            comptes.put(ligne, itemsDeLaLigne(ligne).size());
        }

        comptes.forEach((ligne, compte) -> assertThat(compte)
                .as("%s : exactement dix items, tous paquets confondus", ligne).isEqualTo(10));
    }

    /** FER-2 (S32-1) : les quatre lignes de la tranche 1 atteignent chacune le plancher de dix. */
    @Test
    void fer2EstTenueSurLesQuatreLignesDeLaTranche1() {
        assertThat(itemsDeLaLigne(LIGNE_FRISE)).hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_SIECLE_ET_MILLENAIRE))
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_PEUPLES_ANCIENS)).hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_MOUVEMENTS_DE_POPULATION))
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 sur HG-CM1-01 : les cinq grandes periodes sont chacune servies en bonne reponse. */
    @Test
    void fer1LesCinqGrandesPeriodesSontServiesEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_FRISE).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : la Préhistoire, l'Antiquité, le Moyen Âge, les Temps modernes et"
                        + " l'époque contemporaine sont chacune servies en bonne réponse")
                .containsAll(GRANDES_PERIODES);
    }

    /** FER-1 sur HG-CM1-03 : les trois peuples anciens sont chacun servis en bonne reponse. */
    @Test
    void fer1LesTroisPeuplesAnciensSontServisEnBonneReponse() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_PEUPLES_ANCIENS).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les Gaulois, les Grecs et les Romains sont chacun servis en bonne"
                        + " réponse")
                .containsAll(PEUPLES_ANCIENS);
    }

    /**
     * FER-3 sur HG-CM1-01 : "ordre" (une periode par item, cinq items) et "identification" (un
     * fait ou un objet decrit, cinq items), chacune au-dessus du plancher.
     */
    @Test
    void fer3AuMoinsDeuxTachesSurHgCm101LaMoinsServieAuMoinsTroisItems() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_FRISE);
        long ordre = items.stream()
                .filter(item -> String.valueOf(item.get("enonce")).startsWith("Quelle grande"
                        + " période vient juste"))
                .count();
        long identification = items.size() - ordre;

        assertThat(ordre).as("la tâche d'ordre est servie").isGreaterThan(0);
        assertThat(identification).as("la tâche d'identification est servie").isGreaterThan(0);
        partageDesTachesEpingle(LIGNE_FRISE, "ordre", ordre, 5, "identification",
                identification, 5);
        assertThat(Math.min(ordre, identification))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FER-3 sur HG-CM1-02 : "situer dans un siecle ou un millenaire" et "ordonner deux faits l'un
     * par rapport a l'autre", chacune au-dessus du plancher.
     */
    @Test
    void fer3AuMoinsDeuxTachesSurHgCm102LaMoinsServieAuMoinsTroisItems() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_SIECLE_ET_MILLENAIRE);
        long ordre = items.stream()
                .filter(item -> String.valueOf(item.get("enonce")).startsWith("Parmi ces trois"))
                .count();
        long situer = items.size() - ordre;

        assertThat(ordre).as("la tâche d'ordre est servie").isGreaterThan(0);
        assertThat(situer).as("la tâche de situer est servie").isGreaterThan(0);
        partageDesTachesEpingle(LIGNE_SIECLE_ET_MILLENAIRE, "ordonner", ordre, 5,
                "situer dans un siècle ou un millénaire", situer, 5);
        assertThat(Math.min(ordre, situer))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FER-3 sur HG-CM1-03 : "nommer le peuple" (trois items, un par peuple - FAB-1a interdit un
     * quatrieme sans repeter une bonne reponse) et "dire ce qu'un peuple a laisse" (sept items).
     */
    @Test
    void fer3AuMoinsDeuxTachesSurHgCm103LaMoinsServieAuMoinsTroisItems() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_PEUPLES_ANCIENS);
        long nommerLePeuple = items.stream()
                .filter(item -> PEUPLES_ANCIENS.contains(item.get("bonneReponse"))).count();
        long directLaisse = items.size() - nommerLePeuple;

        assertThat(nommerLePeuple).as("la tâche \"nommer le peuple\" est servie").isGreaterThan(0);
        assertThat(directLaisse).as("la tâche \"dire ce qu'un peuple a laissé\" est servie")
                .isGreaterThan(0);
        partageDesTachesEpingle(LIGNE_PEUPLES_ANCIENS, "nommer le peuple", nommerLePeuple, 3,
                "dire ce qu'un peuple a laissé", directLaisse, 7);
        assertThat(Math.min(nommerLePeuple, directLaisse))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FER-3 sur HG-CM1-04 : "nommer le mouvement" (trois items, un par peuple/mouvement/origine) et
     * "dire un effet sur le territoire" (sept items). S33-HG-04 (HG-R12) : "les Sarrasins" ne nomme
     * plus l'armée de 732, remplacé par son origine géographique ("d'Espagne").
     */
    @Test
    void fer3AuMoinsDeuxTachesSurHgCm104LaMoinsServieAuMoinsTroisItems() {
        Set<String> mouvements = Set.of("les peuples germaniques", "les Vikings", "d'Espagne");
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_MOUVEMENTS_DE_POPULATION);
        long nommerLeMouvement = items.stream()
                .filter(item -> mouvements.contains(item.get("bonneReponse"))).count();
        long direUnEffet = items.size() - nommerLeMouvement;

        assertThat(nommerLeMouvement).as("la tâche \"nommer le mouvement\" est servie")
                .isGreaterThan(0);
        assertThat(direUnEffet).as("la tâche \"dire un effet sur le territoire\" est servie")
                .isGreaterThan(0);
        partageDesTachesEpingle(LIGNE_MOUVEMENTS_DE_POPULATION, "nommer le mouvement",
                nommerLeMouvement, 3, "dire un effet sur le territoire", direUnEffet, 7);
        assertThat(Math.min(nommerLeMouvement, direUnEffet))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
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

    /**
     * S42-HG-ROIS : les quatre rois du libelle sont chacun en bonne reponse au moins une fois sur
     * HG-CM1-05, recomposes depuis les deux paquets qui se partagent cette ligne (meme
     * configuration que HG-CM1-07, S35-CONS-4).
     */
    @Test
    void lesQuatreRoisDuLibelleSontServisEnBonneReponseSurHgCm105() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_ROIS_DU_ROYAUME).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("les quatre rois du libelle (Louis IX, François Ier, Henri IV, Louis XIV)"
                        + " doivent tous etre representes")
                .containsAll(ROIS_DU_ROYAUME);
    }

    /**
     * S42-HG-ROIS, BIA-5 : chaque item de HG-CM1-05 declare un des quatre rois du libelle, sa
     * datation ou sa situation dans le temps - jamais un jugement de valeur sur le personnage.
     */
    @Test
    void hgCm105NePorteAucunJugementDeValeurSurUnRoi() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_ROIS_DU_ROYAUME);

        assertThat(items).as("HG-CM1-05 est servie par au moins un item").isNotEmpty();
        for (Map<String, Object> item : items) {
            assertThat(ROIS_DU_ROYAUME)
                    .as("'%s' : la bonne réponse doit être un des quatre rois du libellé (BIA-5)",
                            item.get("enonce"))
                    .contains(String.valueOf(item.get("bonneReponse")));
        }
    }

    /**
     * FER-1 sur HG-CM1-07 : les quatre evenements de la parenthese sont chacun servis, recomposes
     * depuis les deux paquets qui se partagent desormais cette ligne (S35-CONS-4).
     */
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
        partageDesTachesEpingle(LIGNE_TYPES_D_ESPACES, "identification", identification, 3,
                "association-caractéristique", association, 7);
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
        partageDesTachesEpingle(LIGNE_EAU_ENERGIE_ALIMENTS, "acheminement", acheminement, 4,
                "origine", origine, 6);
        assertThat(Math.min(acheminement, origine))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * HG-R3 (nadia-prof, 2026-09-03) : les deux distracteurs absurdes de l'ancien item sur l'espace
     * rural ne doivent plus figurer nulle part dans aucun des quatre paquets.
     */
    @Test
    void hgR3LesAnciensDistracteursAbsurdesOntDisparu() {
        for (Question item : tousLesItemsDeTousLesPaquets()) {
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

    /** FER-2 (S33-HG-08-12) : les deux lignes remplies par ce lot atteignent chacune le plancher de dix. */
    @Test
    void fer2EstTenueSurLesDeuxLignesDeS33Hg0812() {
        assertThat(itemsDeLaLigne(LIGNE_REVOLUTION_A_L_EMPIRE))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2, LIGNE_REVOLUTION_A_L_EMPIRE)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
        assertThat(itemsDeLaLigne(LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE))
                .as("FER-2 : au moins %d items servent %s", PLANCHER_FER_2,
                        LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE)
                .hasSizeGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 sur HG-CM1-08 : avant S33-HG-08-12, aucun item ne servait la moitie du libelle "ce que
     * Napoleon Bonaparte change" - l'unique item de s27 ne servait que "l'Empire", la moitie
     * "passage". Au moins un des cinq faits neufs est desormais servi en bonne reponse.
     */
    @Test
    void fer1SurHgCm108LaMoitieCeQueNapoleonChangeEstDesormaisServie() {
        Set<Object> reponses = itemsDeLaLigne(LIGNE_REVOLUTION_A_L_EMPIRE).stream()
                .map(item -> item.get("bonneReponse"))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : au moins un fait sur ce que Napoléon Bonaparte change est servi en"
                        + " bonne réponse, en plus du passage de la Révolution à l'Empire lui-même")
                .containsAnyElementsOf(CE_QUE_NAPOLEON_CHANGE);
    }

    /**
     * FER-1 sur HG-CM1-12 : la pratique "se cultiver", absente jusqu'ici de la ligne, est
     * desormais servie en bonne reponse par au moins un des neuf items neufs.
     */
    @Test
    void fer1SurHgCm112LaPratiqueSeCultiverEstDesormaisServie() {
        List<String> enonces = itemsDeLaLigne(LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE).stream()
                .map(item -> String.valueOf(item.get("enonce")))
                .toList();

        assertThat(enonces)
                .as("FER-1 : au moins un item sert la pratique \"se cultiver\", absente jusqu'ici de"
                        + " %s", LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE)
                .anyMatch(enonce -> enonce.contains("œuvres d'art") || enonce.contains("monuments")
                        || enonce.contains("événement culturel"));
    }

    /**
     * HIS-5 (nadia-prof, 2026-09-09, S33-HG-08-12) : un item ne compte que sur une ligne
     * (ADR-0081, clause 5). La cellule "se loger dans un espace urbain" est deja comptee sur
     * HG-CM1-11 (ligne fermee, "des immeubles collectifs") ; aucun item neuf de HG-CM1-12 ne la
     * reprend.
     */
    @Test
    void hisR5AucunItemDeHgCm112NeReprendLaCelluleDejaCompteeSurHgCm111() {
        List<Map<String, Object>> items = itemsDeLaLigne(LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE);

        assertThat(items)
                .as("aucun item de %s ne reprend la bonne réponse déjà comptée et fermée sur %s",
                        LIGNE_VIVRE_URBAIN_ET_TOURISTIQUE, LIGNE_TYPES_D_ESPACES)
                .noneMatch(item -> REPONSE_DEJA_COMPTEE_SUR_HG_CM1_11.equals(item.get("bonneReponse")));
    }
}
