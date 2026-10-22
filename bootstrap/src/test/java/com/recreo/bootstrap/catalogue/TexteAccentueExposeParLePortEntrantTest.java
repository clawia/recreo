package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.catalog.Matiere;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S16-5 (nadia-prof, theo-archi, avis de faisabilite du sprint 16). {@code
 * TexteAccentueDesGabaritsTest} saute tout texte pose par {@code th:text}/{@code th:utext}, au
 * motif que ce texte vient du modele et que ses garde-fous "vivent dans adapter-content" (ADR-0039,
 * non-preuve 5). Ce motif est vrai pour le texte d'un paquet ; il est faux pour les libelles de
 * niveau ("CP", "CM1") exposes par {@link PartieUseCase#niveauxDuCatalogue()} et rendus par la puce
 * de niveau et la phrase de filtre d'{@code accueil.html} : rien dans {@code adapter-content} ne
 * juge leur accentuation, alors qu'ils traversent le meme saut sous {@code th:text}.
 *
 * <p><b>ADR-0015</b> : le perimetre descend de la source qui gouverne le rendu. Cette source n'est
 * pas un gabarit ici, c'est une donnee servie - le port entrant. Le perimetre est donc l'ensemble
 * des chaines que {@link PartieUseCase} expose a une vue, derive **par reflexion** des composantes
 * de {@link GameDefinition} et de son descripteur ({@link ContentDescriptor}), jamais d'une liste de
 * cles YAML ni d'une liste de gabarits : le jour ou le filtre par matiere rendra
 * {@code descripteur.matiere()}, cette chaine entre dans ce perimetre sans qu'il faille toucher ce
 * fichier.
 *
 * <p><b>Ce que la reflexion peut structurellement voir, et ce qu'elle ne peut pas.</b> Elle descend
 * dans un objet tant que sa classe reelle est un <i>record</i> - {@link GameDefinition}, {@link
 * MechanicId}, {@link ContentDescriptor} le sont. Elle s'arrete des qu'elle rencontre un objet dont
 * la classe reelle n'en est pas un, en particulier {@code ContentSource} : ses implementations
 * ({@code ListContentSource}, {@code AdditionsBorneesContentSource}) sont des classes ordinaires,
 * jamais des records. C'est cette seule propriete structurelle - et non une liste d'exclusion ecrite
 * ici - qui laisse au texte d'un paquet (enonces, propositions, indices) le garde-fou qui le juge
 * deja dans {@code adapter-content} : la frontiere entre "catalogue" et "paquet" que theo-archi
 * trace dans son avis se lit directement dans la difference entre un record et une classe.
 *
 * <p><b>ADR-0039</b> : la promesse est universelle (toute chaine exposee par le port doit etre
 * connue), donc le critere est total sur son complement - une liste d'autorisation, jamais une
 * liste de refus. Comparaison par egalite, jamais par sous-chaine (ADR-0008).
 */
@SpringBootTest
class TexteAccentueExposeParLePortEntrantTest {

    @Autowired
    private PartieUseCase partie;

    /**
     * ADR-0039, critere enumere assume. "CP" et "CM1" sont exemptes de l'inventaire ci-dessous :
     * liste ecrite a la main, du cote refuse (elle dispense de preuve plutot que d'exiger une
     * entree), parce qu'aucune de leurs lettres n'est accentuable - leur absence de l'inventaire ne
     * prouverait rien sur leur accentuation, a la difference de toute autre chaine exposee.
     *
     * <p>Frontiere ecrite (theo-archi, avis de faisabilite du sprint 16, section 4) : cette
     * exemption cesse de s'appliquer des qu'un niveau porte une lettre accentuable - un treizieme
     * niveau au nom different entrerait dans l'inventaire comme n'importe quelle autre chaine. Le
     * recensement 3 de {@code LesGardeFousSontMisALEpreuveTest} compte et plafonne cet aveu.
     */
    private static final Set<String> NIVEAUX_EXEMPTES_CAR_SANS_LETTRE_ACCENTUABLE = Set.of("CP", "CM1");

    /**
     * L'inventaire des chaines que le catalogue reel expose au port entrant, hors niveaux exemptes
     * ci-dessus. Une entree par valeur distincte reellement produite par {@code catalogue.yaml} et
     * les descripteurs des paquets qu'il reference, comparee par egalite (ADR-0039).
     *
     * <p>S19-5 (ADR-0049) a corrige les cinq chaines que S16-5 avait laissees sans accent :
     * {@code matiere} ("Mathematiques", "Francais") et la partie de {@code domaine}/{@code competence}
     * portee par {@code cp-le-bon-son.yaml} et {@code cp-problemes-additifs.yaml}. Elles portent
     * desormais la graphie accentuee du contenu servi.
     */
    private static final List<String> CHAINES_EXPOSEES_PAR_LE_PORT_ENTRANT = List.of(
            // identifiants de catalogue.yaml (GameDefinition.id) : jamais lus par un enfant, mais
            // exposes au port entrant qui les rend disponibles a une vue (PartieController.jeuOuEchec,
            // l'attribut hx-post de chaque bouton "Jouer").
            "bandit-manchot-additions-cp",
            "bandit-manchot-conjugaison-present-cm1",
            "bandit-manchot-le-bon-son-cp",
            "bandit-manchot-soustractions-cp",
            "bandit-manchot-problemes-additifs-cp",
            "bandit-manchot-comparer-nombres-cp",
            "bandit-manchot-complements-10-cp",
            // S24-SAI-2 : seconde entree sur la meme competence, en saisie. CPL-9a de
            // nadia-prof - un enfant de CP doit distinguer les deux sans les ouvrir.
            "saisie-complements-10-cp",
            "bandit-manchot-tables-multiplication-cm1",
            "bandit-manchot-perimetre-cm1",
            "bandit-manchot-conversions-unites-cm1",
            "bandit-manchot-grands-nombres-cm1",
            // S17-3 (ADR-0052) : entree neuve, consigne generique porteuse de la clause
            // invariante retiree de l'enonce.
            "bandit-manchot-synonymes-cm1",
            // S19-1 : entree neuve, distincte de "Les synonymes" (ADR-0052, DEC-0039).
            "bandit-manchot-antonymes-cm1",
            // S17-4 (DEC-0024, DEC-0041) : entree neuve, restitution de "Le bon petit mot"
            // (paquet deja juge bon, BPM-1 remesuree par nadia-prof).
            "bandit-manchot-homophones-cm1",
            // S19-2 (DEC-0042) : entree neuve, accord dans le groupe nominal.
            "bandit-manchot-accords-groupe-nominal-cm1",
            // S19-3 : entree neuve, accord du verbe avec un sujet eloigne, compose ou nominal.
            "bandit-manchot-accord-sujet-verbe-cm1",
            // DEC-0048 (S20-1) : entree qui revient au catalogue apres NAT-5 levee.
            "bandit-manchot-nature-mot-cp",
            // DEC-0050 (S20, cloture) : entree qui revient au catalogue, refus leve en VALIDE
            // SOUS RESERVE.
            "bandit-manchot-reperes-cm1",
            // S21-9 : entree neuve, premiere ligne couverte du domaine "Langage oral et
            // conscience phonologique".
            "bandit-manchot-syllabes-cp",
            // S21-10 (DEC-0051) : entree neuve, vocabulaire precis (objets, actions, sensations).
            "bandit-manchot-mot-manquant-cp",
            // S21-11 (arbitrage prealable du 2026-08-27, conditions GRA-1 a GRA-10) : entree
            // neuve, comparaison de longueurs, masses et contenances.
            "bandit-manchot-comparer-cp",
            // S22 (S13-2, arbitrage prealable du 2026-08-15, conditions MD-1 a MD-10) : entree
            // neuve, calcul mental des multiplications et divisions par 10 et par 100.
            "bandit-manchot-multiplications-divisions-10-100-cm1",
            // S13-3 (DEC-0053, arbitrage prealable D-1 a D-11) : entree neuve, problemes de
            // durees CM1.
            "bandit-manchot-problemes-durees-cm1",
            // S22 (S17-2, DEC-0053, ADR-0050) : entree neuve, comprehension de texte CM1.
            "bandit-manchot-comprehension-cm1",
            // S23-LIA (arbitrage prealable de nadia-prof du 2026-08-30, conditions LIA-1 a
            // LIA-10) : entree neuve, mots de liaison simples CM1, couverture partielle nommee.
            "bandit-manchot-mots-de-liaison-cm1",
            // S23-RAN (arbitrage prealable s23, RAN-1 a RAN-8) : entree neuve, ranger des
            // nombres jusqu'a 100.
            "bandit-manchot-ranger-nombres-cp",
            // S23-ETA (arbitrage prealable s23, ETA-1 a ETA-10, ETA-4b) : entree neuve,
            // problemes a deux etapes mobilisant les quatre operations.
            "bandit-manchot-problemes-etapes-cm1",
            // S24-NAR (arbitrage prealable de nadia-prof du 2026-08-30, conditions NAR-1 a
            // NAR-10) : entree neuve, comprehension d'un texte narratif tres court, CP.
            "bandit-manchot-texte-narratif-cp",
            // S24-PLU (meme arbitrage, conditions PLU-1 a PLU-9) : entree neuve, marques du
            // pluriel les plus frequentes, CP.
            "bandit-manchot-pluriel-cp",
            // S24-FRA (arbitrage prealable s23, section 8bis, FRA-1 a FRA-9 ; arbitrage de s24,
            // section 5.2, FRA-10 ; amendement du 2026-08-31, famille (e)) : entree neuve, la
            // notion de fraction simple au CM1, moitie "notion" seule.
            "bandit-manchot-fractions-cm1",
            // S25-FIG (DEC-0065 amendement A.3 ; FIG-1 a FIG-4 du 2026-08-24, reconduites le
            // 2026-08-31 ; CON-1 a CON-5) : entree neuve, reconnaitre et nommer quelques figures
            // planes, CP. Les deux entrees ci-dessous sont les deux premiers jeux illustres du
            // produit (ADR-0073) ; elles sont nees le meme jour, et aucune des deux n'est "le
            // premier" - c'est la fusion qui les ordonne, pas leur contenu.
            "bandit-manchot-figures-planes-cp",
            // S25-MON-1 (arbitrage prealable de s25 sur les seize lignes, section 2.2, MON-1 a
            // MON-5 ; arbitrage du palier 1, CON-1 a CON-5) : entree neuve. La ligne de la monnaie
            // avait ete refusee en contenu textuel le meme jour et rouverte sur sa forme illustree
            // - "le paquet textuel nommait les pieces, le paquet illustre les montre".
            "bandit-manchot-monnaie-cp",
            // S25-SUB (arbitrage prealable s23, section 5, SUB-1 a SUB-10) : entree neuve,
            // reperer les substituts d'un personnage dans un texte, CM1.
            "bandit-manchot-substituts-cm1",
            // S25-SEM (DEC-0065 amendement A.2 et A.3 ; SEM-1 a SEM-9 du 2026-08-31) : entree
            // neuve, se reperer dans la journee et la semaine, CP, couverture partielle nommee.
            "bandit-manchot-semaine-cp",
            // S26-L1 a S26-L5 (DEC-0068, la voix est celle d'un adulte present) : cinq entrees
            // neuves, la famille a voix (ADR-0075).
            "bandit-manchot-position-syllabe-cp",
            "bandit-manchot-son-dans-mot-cp",
            "bandit-manchot-lettre-du-son-cp",
            "bandit-manchot-digraphe-du-son-cp",
            "saisie-mot-dicte-cp",
            // S27-1 (DEC-0069, rang 1) : entree neuve, derniere clause de la ligne "associer les
            // graphemes aux phonemes" (voyelles simples).
            "bandit-manchot-voyelle-du-son-cp",
            // S27-2 (backlog S27-2) : trois entrees neuves, premier paquet de "Questionner le monde"
            // (CP), matiere neuve du produit.
            "bandit-manchot-vivant-non-vivant-cp",
            "bandit-manchot-corps-sens-cp",
            "bandit-manchot-etats-eau-cp",
            // S27-3 : entree neuve, premier paquet d'histoire-geographie, CM1. Scindee en quatre
            // entrees par S35-CONS-4 (DEC-0088, ADR-0081) : une entree par tache reellement
            // distincte plutot qu'une seule par matiere.
            "bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1",
            "bandit-manchot-histoire-geographie-nommer-en-histoire-cm1",
            "bandit-manchot-histoire-geographie-nommer-en-geographie-cm1",
            "bandit-manchot-histoire-geographie-lire-un-document-cm1",
            // S28-3 (backlog S28-3) : entree neuve, premier paquet d'anglais, CM1.
            "bandit-manchot-anglais-cm1",
            // S28-4 : deux entrees neuves, calcul pose CM1 (multiplication et division
            // euclidienne, MAT-CM1-04 et MAT-CM1-05).
            "bandit-manchot-multiplication-posee-cm1",
            "bandit-manchot-division-posee-cm1",
            // S29-1 (backlog S29-1) : deux entrees neuves, quatrieme et cinquieme paquets de
            // "Questionner le monde" (CP), fermant QLM-CP-02 et QLM-CP-07.
            "bandit-manchot-cycle-de-vie-cp",
            "bandit-manchot-objet-technique-cp",
            // S29-4 (backlog S29-4) : deux entrees neuves, premier lot d'enseignement moral et
            // civique, CP, restreint aux lignes 1 a 7 du referentiel.
            "bandit-manchot-regles-de-vie-cp",
            "bandit-manchot-permis-interdit-cp",
            // S29-5 (backlog S29-5) : deux entrees neuves, second lot d'enseignement moral et
            // civique, CM1 (fait/opinion, droit/devoir).
            "bandit-manchot-fait-opinion-cm1",
            "bandit-manchot-droit-devoir-cm1",
            // S30-1 (backlog S30-1) : deux entrees neuves, premier lot de sciences et technologie
            // CM1, matiere neuve du produit (signal/information, chaine alimentaire).
            "bandit-manchot-signal-information-cm1",
            "bandit-manchot-chaine-alimentaire-cm1",
            // S39-ENERGIE (s39) : entree neuve, second passage de sciences et technologie CM1
            // (sources et formes d'énergie).
            "bandit-manchot-energie-cm1",
            // S30-2 (backlog S30-2) : deux entrees neuves, second lot de sciences et technologie
            // CM1 (mélanges, objet technique).
            "bandit-manchot-melanges-cm1",
            "bandit-manchot-objet-technique-cm1",
            // S31-1 (backlog S31-1) : deux entrees neuves, premier lot d'enseignement moral et
            // civique de s31, restreint aux lignes 1 a 7 du referentiel (emotions, politesse).
            "bandit-manchot-emotions-autrui-cp",
            "bandit-manchot-formules-de-politesse-cp",
            // S31-2 (backlog S31-2) : deux entrees neuves, second lot d'enseignement moral et
            // civique de s31 (exclusion, regle/souhait/loi).
            "bandit-manchot-exclusion-cp",
            "bandit-manchot-regle-souhait-loi-cp",
            // S31-3 (backlog S31-3) : deux entrees neuves, premier lot de sciences et technologie
            // CM1 en second passage (etats de la matiere, regimes alimentaires).
            "bandit-manchot-etats-matiere-cm1",
            "bandit-manchot-regimes-alimentaires-cm1",
            // S31-4 (backlog S31-4) : deux entrees neuves, second lot de sciences et technologie
            // CM1 en second passage (familles de materiaux, etapes du developpement).
            "bandit-manchot-familles-materiaux-cm1",
            "bandit-manchot-etapes-developpement-cm1",
            // S32-5 : deux entrees neuves, premier lot d'education physique et sportive CP.
            "bandit-manchot-regles-securite-eps-cp",
            "bandit-manchot-regles-jeu-collectif-eps-cp",
            // S34-EPS-CORPS-EFFORT : entree neuve, second lot d'education physique et sportive CP.
            "bandit-manchot-corps-effort-eps-cp",
            // S33-PAL2-1 (s34) : une entree neuve, premier lot de designation, troisieme
            // mecanique du produit (frise de la journee).
            "designation-frise-de-la-journee",
            // S35-QLM11-PLANCLASSE (s35) : seconde entree de designation, premiere livraison
            // reelle de QLM-CP-11 (plan de la classe).
            "designation-plan-de-la-classe",
            // S37-1 (s37) : entree neuve, premier paquet de mathematiques CE1 (MAT-CE1-03,
            // additions dans la limite de 100), reparti par forme selon ADD-CE1-2.
            "bandit-manchot-additions-ce1",
            // S38-COMPLEMENTS-DIZAINE (s38) : entree neuve, complements a la dizaine pris pour
            // eux-memes (MAT-CE1-03).
            "bandit-manchot-complements-dizaine-ce1",
            // S38-MOITIES (s38) : entree neuve, moities (MAT-CE1-03).
            "bandit-manchot-moities-ce1",
            // S38-SOUSTRACTIONS (s38) : entree neuve, second paquet de mathematiques CE1
            // (MAT-CE1-03, soustractions dans la limite de 100), reparti par forme selon
            // SOU-CE1-2 (ADR-0097).
            "bandit-manchot-soustractions-ce1",
            // S39-MONNAIE (s39) : entree neuve, troisieme paquet de mathematiques CE1
            // (MAT-CE1-13, sous-partie "monnaie", ADR-0098).
            "bandit-manchot-monnaie-ce1",
            // S39-OUTILS (s39) : entree neuve, premiere ligne d'arts plastiques du produit
            // (ART-CP-11).
            "bandit-manchot-outils-arts-cp",
            // S39-DEN (s39) : entree neuve, DEN-1 a DEN-8, ligne MAT-CP-01 plafonnee "en cours"
            // (DEN-2 non servi).
            "bandit-manchot-nombres-jusqu-a-100-cp",
            // S39-LONGUEUR (s39) : entree neuve, sous-partie textuelle de MAT-CE1-09 (ADR-0098),
            // ligne plafonnee "en cours" (pas de rendu de regle graduee).
            "bandit-manchot-longueurs-ce1",
            // S39-SOLIDES (s39) : entree neuve, MAT-CE1-16, sous-partie allouee par ADR-0098.
            "bandit-manchot-solides-ce1",
            // S40-HDA (s40) : entree neuve, premier paquet d'histoire des arts (HDA-CM1-05).
            "bandit-manchot-histoire-des-arts-relier-contexte-cm1",
            // S40-NUTRITION (s40) : entree neuve, SCI-CM1-09, fonctions de nutrition du corps
            // humain.
            "bandit-manchot-nutrition-cm1",
            // S40-REGLES (s40) : entree neuve, EMC-CM1-04, regles de la vie collective a l'ecole.
            "bandit-manchot-regles-vie-collective-cm1",
            // S40-LOI (s40) : entree neuve, EMC-CM1-08, distinguer une regle d'une loi.
            "bandit-manchot-regle-loi-cm1",
            // S40-DEVISE (s40) : entree neuve, EMC-CP-09, la devise de la Republique.
            "bandit-manchot-devise-cp",
            // S40-EXTRACTION (s40) : entree neuve, extraction d'information texte et tableau
            "bandit-manchot-sciences-extraire-information-cm1",
            // S41-EMC-ROLES (s41) : entree neuve, EMC-CP-13, roles et services de l'ecole et de la
            // commune.
            "bandit-manchot-roles-ecole-commune-cp",
            // S41-SCI-CLASSER (s41) : entree neuve, SCI-CM1-06, classer des etres vivants d'apres
            // un caractere commun observable.
            "bandit-manchot-classer-etres-vivants-cm1",
            // S41-QLM-HYGIENE (s41) : entree neuve, QLM-CP-04, regles d'hygiene de vie.
            "bandit-manchot-hygiene-cp",
            // S41-QLM-SAISONS (s41) : entree neuve, QLM-CP-13, mois et saisons.
            "bandit-manchot-saisons-cp",
            // S41-EMC-SYMBOLES (s41) : entree neuve, EMC-CP-08, les symboles de la Republique.
            "bandit-manchot-symboles-cp",
            // S41-EMC-DROITS (s41) : entree neuve, EMC-CP-10, les droits de l'enfant.
            "bandit-manchot-droits-enfant-cp",
            // S42-MAT-TEXTUEL (s42) : entree neuve, MAT-CP-10, moitie textuelle (decrire un
            // deplacement sur un quadrillage).
            "bandit-manchot-deplacements-quadrillage-cp",
            // S42-ART-COULEURS (s42) : entree neuve, ART-CP-05, les couleurs et les melanges.
            "bandit-manchot-couleurs-arts-cp",
            // S42-EMC-SYMBOLES-CM1 (s42) : entree neuve, EMC-CM1-05, les symboles de la
            // Republique en CM1.
            "bandit-manchot-symboles-cm1",
            // S42-QLM-MATIERE (s42) : entree neuve, QLM-CP-06, la matiere d'un objet.
            "bandit-manchot-matiere-objets-cp",
            // S43-MAT-DUREE (s43) : entree neuve, MAT-CE1-12, calculer une duree simple en
            // heures entieres ou en demi-heures.
            "bandit-manchot-problemes-durees-ce1",
            // S43-QLM-NUMERIQUE (s43) : entree neuve, QLM-CP-08, les objets numeriques et leur usage.
            "bandit-manchot-objets-numeriques-cp",
            // S43-EPS-EFFETS-ACTIVITE (s43) : entree neuve, EPS-CM1-10, premier paquet
            // d'education physique et sportive CM1.
            "bandit-manchot-effets-activite-eps-cm1",
            // S44-MAT-DIVISION (s44) : entree neuve, MAT-CE1-18, sens de la division (partage et
            // groupement), sous-partie allouee dans SousPartieDeMathematiquesCe1 (ADR-0098).
            "bandit-manchot-division-ce1",
            // S44-MUS-VOCABULAIRE (s44) : entree neuve, MUS-CP-11, premiere ligne d'education
            // musicale du produit, matiere neuve.
            "bandit-manchot-vocabulaire-musical-cp",
            // S44-SCI-OBJET-TECHNIQUE (s44) : entree neuve, SCI-CM1-13, evolution d'un objet
            // technique au cours du temps (fabrication ou usage), distincte de
            // bandit-manchot-objet-technique-cm1 (SCI-CM1-12).
            "bandit-manchot-evolution-objet-technique-cm1",
            // S45-MUS-INSTRUMENTS (s45) : entree neuve, MUS-CP-05, second paquet d'education
            // musicale CP.
            "bandit-manchot-instruments-musicaux-cp",
            // S45-SCI-NUMERIQUE (s45) : entree neuve, SCI-CM1-14, regles d'usage d'un
            // environnement numerique (identification, courriel, recherche d'information,
            // protection des donnees).
            "bandit-manchot-usages-numeriques-cm1",
            // S45-MAT-ADDITION (s45) : entree neuve, MAT-CE1-04, addition posee avec retenue.
            // ADR-0103 : ce paquet declare MAT-CE1-04, pas MAT-CE1-03, et sort donc du perimetre
            // du garde-fou de repartition par forme des additions de CE1.
            "bandit-manchot-additions-posees-ce1",
            // S45-EPS-ROLES (s45) : entree neuve, EPS-CM1-09, tenir les roles d'arbitre,
            // d'observateur ou de marqueur dans un jeu.
            "bandit-manchot-roles-eps-cm1",
            // S46-EMC-INSTITUTIONS-CM1 (s46) : entree neuve, EMC-CM1-09, role de quelques
            // institutions proches (mairie, conseil municipal, ecole).
            "bandit-manchot-institutions-cm1",
            // S46-SCI-PHENOMENES-CM1 (s46) : entree neuve, SCI-CM1-16, phenomenes geologiques et
            // meteorologiques et les risques associes.
            "bandit-manchot-phenomenes-naturels-cm1",
            // S51-SCI-CORPS-CM1 (s51) : entree neuve, SCI-CM1-10, alimentation, activite physique,
            // sommeil et hygiene reliees au fonctionnement du corps.
            "bandit-manchot-corps-cm1",
            // S54-BR-SCI-CM1-18 (s54) : entree neuve, SCI-CM1-18, effet de l'activite humaine sur
            // l'environnement et geste de preservation ; reprise corrigee, identifiant neuf.
            "bandit-manchot-environnement-cm1",
            // S54-BR-SCI-CM1-15 (s54) : entree neuve, SCI-CM1-15, la Terre dans le systeme solaire,
            // le jour, la nuit et les saisons ; reprise corrigee, identifiant neuf.
            "bandit-manchot-jour-nuit-saisons-cm1",
            // S54-BR-ART-CP-10 (s54) : entree neuve, paquet DEGRADE de vocabulaire, ART-CP-10 non
            // fermee (DEC-0114 A.1).
            "bandit-manchot-familles-d-oeuvres-cp",
            // S54-BR-EMC-CM1-02 (s54) : entree neuve, EMC-CM1-02, reconnaitre ce qui porte atteinte
            // a la personne d'autrui et savoir a qui s'adresser.
            "bandit-manchot-atteinte-autrui-cm1",
            // S55-BR-EMC-CM1-03 (s55) : entree neuve, EMC-CM1-03, identifier ce qu'interdit le
            // respect d'autrui dans ses differences.
            "bandit-manchot-respect-differences-cm1",
            // S55-BR-EMC-CM1-06 (s55) : entree neuve, EMC-CM1-06, comprendre le sens des principes
            // de la Republique.
            "bandit-manchot-principes-republique-cm1",
            // S46-EMC-EMOTION-CM1 (s46) : entree neuve, EMC-CM1-01, identifier une emotion et
            // distinguer ce que l'on ressent de ce que l'on fait.
            "bandit-manchot-emotion-ressentir-agir-cm1",
            // S46-SCI-MOUVEMENT-CM1 (s46) : entree neuve, SCI-CM1-03, decrire un mouvement par sa
            // trajectoire et sa vitesse.
            "bandit-manchot-mouvement-cm1",
            // S46-MAT-SOUSTRACTION (s46) : entree neuve, MAT-CE1-05, soustraction posee avec
            // emprunt. Complete ici (fusion de s46) : le lot de marc-dev n'avait mesure que le
            // module adapter-content, pas le reacteur complet, et ne pouvait donc pas voir ce
            // garde-fou du module bootstrap rougir.
            "bandit-manchot-soustractions-posees-ce1",
            // S47-LVE-CULTURE-CP (s47) : entree neuve, LVE-CP-06, decouverte culturelle des pays
            // anglophones.
            "bandit-manchot-culture-anglophone-cp",
            // S47-EMC-VOTE-CM1 (s47) : entree neuve, EMC-CM1-10, sens du vote et de la
            // representation.
            "bandit-manchot-vote-election-cm1",
            // S47-QLM-AUTREFOIS (s47) : entree neuve, QLM-CP-15, comparer des modes de vie
            // d'autrefois et d'aujourd'hui a partir d'objets et d'images du quotidien.
            "bandit-manchot-autrefois-aujourdhui-cp",
            // S47-EMC-ALERTE-CP (s47) : entree neuve, EMC-CP-14, connaitre les gestes d'alerte les
            // plus simples et savoir donner l'alerte.
            "bandit-manchot-alerte-cp",
            // S47-MAT-TABLES (s47) : entree neuve, MAT-CE1-07, tables de multiplication de 2, 5 et
            // 10, puis de 3 et 4.
            "bandit-manchot-tables-multiplication-ce1",
            // S48-MAT-PROBLEMES (s48) : entree neuve, MAT-CE1-08, problemes d'addition, de
            // soustraction et de multiplication a une ou deux etapes.
            "bandit-manchot-problemes-etapes-ce1",
            // S49-FRA-GRAPHEMES (s49) : entree neuve, FRA-CE1-01, premiere ligne du referentiel
            // de francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md,
            // section 3.1).
            "bandit-manchot-graphemes-complexes-ce1",
            // S50-FRA-VALEUR-LETTRE (s50) : entree neuve, FRA-CE1-02, deuxieme ligne du
            // referentiel de francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md,
            // section 3.2).
            "bandit-manchot-valeur-lettre-ce1",
            // S50-FRA-HOMOPHONES (s50) : entree neuve, FRA-CE1-16, troisieme ligne du referentiel
            // de francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md,
            // section 5.7).
            "bandit-manchot-homophones-ce1",
            // S51-FRA-TEMPS (s51) : entree neuve, FRA-CE1-20, quatrieme ligne du referentiel de
            // francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.11).
            "bandit-manchot-temps-ce1",
            // S51-FRA-ALPHABETIQUE (s51) : entree neuve, FRA-CE1-23, cinquieme ligne du referentiel
            // de francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md,
            // section 6.1).
            "bandit-manchot-ordre-alphabetique-ce1",
            // S51-FRA-GRAPHIE (s51) : entree neuve, FRA-CE1-22, sixieme ligne du referentiel de
            // francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.13).
            "bandit-manchot-graphie-ce1",
            // S52-FRA-CATEGORIES (s52) : entree neuve, FRA-CE1-26, septieme ligne du referentiel de
            // francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 6.4).
            "bandit-manchot-categories-ce1",
            // S52-FRA-PRESENT (s52) : entree neuve, FRA-CE1-17, huitieme ligne du referentiel de
            // francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.8).
            "bandit-manchot-conjugaison-present-ce1",
            // S54-BR-FRA-CE1-18 (s54) : entree neuve, FRA-CE1-18, conjuguer au futur
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.9).
            "bandit-manchot-futur-ce1",
            // S54-BR-FRA-CE1-19 (s54) : entree neuve, FRA-CE1-19, conjuguer au passe compose
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.10).
            "bandit-manchot-passe-compose-ce1",
            // S55-BR-FRA-CE1-L1 (s55) : entree neuve, FRA-CE1-14, accorder le verbe avec son sujet
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.5).
            "bandit-manchot-accord-sujet-verbe-ce1",
            // S55-BR-FRA-CE1-L2 (s55) : entree neuve, FRA-CE1-12, identifier la classe d'un mot
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.3).
            "bandit-manchot-classes-de-mots-ce1",
            // S56-BR-FRA-CE1-13 (s56) : entree neuve, FRA-CE1-13, trouver le verbe et son sujet
            // (squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md, section 2).
            "bandit-manchot-verbe-sujet-ce1",
            // S57-BR-FRA-CE1-10 (s57) : entree neuve, FRA-CE1-10, reconnaitre une phrase et son type
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.1).
            "bandit-manchot-phrases-types-ce1",
            // S57-BR-FRA-CE1-15 (s57) : entree neuve, FRA-CE1-15, accorder dans le groupe nominal
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.6).
            "bandit-manchot-accord-groupe-nominal-ce1",
            // S57-BR-FRA-CE1-04 (s57) : entree neuve, FRA-CE1-04, comprendre un texte narratif court
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 3.4).
            "bandit-manchot-texte-narratif-ce1",
            // S57-BR-FRA-CE1-05 (s57) : entree neuve, FRA-CE1-05, identifier a qui renvoie un pronom
            // (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 3.5).
            "bandit-manchot-pronoms-ce1",
            // S57-BR-FRA-CE1-06 (s57) : entree neuve, FRA-CE1-06, faire une inference simple a partir
            // d'un texte court (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 3.6).
            "bandit-manchot-inference-ce1",
            // S57-BR-FRA-CE1-07 (s57) : entree neuve, FRA-CE1-07, comprendre un texte documentaire ou une
            // consigne ecrite (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 3.7).
            "bandit-manchot-texte-documentaire-ce1",
            // S57-BR-FRA-CE1-11 (s57) : entree neuve, FRA-CE1-11, passer d'une phrase affirmative a une
            // phrase negative (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 5.2).
            "bandit-manchot-negation-ce1",
            // S57-BR-MAT-CE1-01 (s57) : entree neuve, MAT-CE1-01, lire, ecrire et decomposer les
            // nombres jusqu'a 1000 (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md,
            // section 2).
            "bandit-manchot-nombres-jusqu-a-1000-ce1",
            // S57-BR-MAT-CE1-10 (s57) : entree neuve, MAT-CE1-10, comparer des masses et des
            // contenances et choisir g, kg ou L (squad/conformite/ARBITRAGE-PREALABLE-s43-
            // mathematiques-ce1-lot-2.md, section 3).
            "bandit-manchot-masses-contenances-ce1",
            // S57-BR-EMC-CE1-01 (s57) : entree neuve, EMC-CE1-01, les biens de chacun et les biens
            // de tous (squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-
            // et-emc-ce1.md, section 4.1).
            "bandit-manchot-biens-communs-ce1",
            // S52-FRA-SYNONYMES-CONTRAIRES (s52) : entree neuve, FRA-CE1-25, neuvieme ligne du
            // referentiel de francais CE1 (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md,
            // section 6.3).
            "bandit-manchot-synonymes-contraires-ce1",
            // S57-BR-FRA-CE1-24 (s57) : entree neuve, FRA-CE1-24, regrouper les mots d'une meme
            // famille (squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md, section 6.2).
            "bandit-manchot-familles-de-mots-ce1",
            // S50-ART (s50) : entree neuve, ART-CP-09, deuxieme ligne du referentiel d'arts
            // plastiques CP (DEC-0109, squad/conformite/ARBITRAGE-PREALABLE-s50-art-cp.md).
            "bandit-manchot-decrire-une-oeuvre-cp",
            // S55-ART-CP-10-PAQUET (s56) : entree neuve, ART-CP-10, premier paquet a propositions en
            // image (ADR-0104, squad/conformite/CHOIX-OEUVRES-s56-art-cp-10.md, B9).
            "bandit-manchot-rapprocher-deux-oeuvres-cp",
            // S51-MAT-COMPARER-RANGER (s52) : entree neuve, MAT-CE1-02, moitie textuelle.
            "bandit-manchot-comparer-ranger-ce1",
            // S51-MAT-MULTIPLICATION (s52) : entree neuve, MAT-CE1-06, sens de la multiplication.
            "bandit-manchot-sens-multiplication-ce1",
            // S51-MAT-SYMETRIE (s52) : entree neuve, MAT-CE1-17, quatrieme paquet de designation.
            "designation-symetrie-ce1",
            // titres (catalogue.yaml, GameDefinition.titre) : lus par l'enfant sous th:text
            // (accueil.html, le bouton "Jouer" de chaque jeu), deja arbitres au fil des sprints qui
            // ont livre chaque jeu.
            // Arbitre par nadia-prof le 2026-09-02 et confirme le 2026-09-03 apres le
            // retrecissement de S1-8 : "Calcul CP" etait le seul des quarante titres a porter
            // son niveau, et il promettait plus large que son contenu ("calcul" couvre aussi
            // la soustraction). ADR-0078 garde la propriete sur les quarante, sans exemption.
            "Les petites additions",
            "Le bon verbe",
            "Le bon son",
            "Les petites soustractions",
            "Petits problèmes",
            "Compare les nombres",
            "Les compléments à 10",
            "Écris le complément à 10",
            "Les tables de multiplication",
            "Le périmètre",
            "Les mesures",
            "Les grands nombres",
            // S17-3 : titre neuf.
            "Les synonymes",
            // S19-1 : titre neuf.
            "Les antonymes",
            // S17-4 : titre neuf.
            "Le bon petit mot",
            // S19-2 : titre neuf.
            "Le bon accord",
            // S19-3 : titre neuf.
            "L'accord du verbe",
            // DEC-0048 : titre restaure.
            "Nom ou verbe ?",
            // DEC-0050 : titre restaure.
            "Majuscule ou point ?",
            // S21-9 : titre neuf.
            "Les syllabes",
            // S21-10 : titre neuf.
            "Le mot qui manque",
            // S21-11 : titre neuf.
            "Compare les grandeurs",
            // S22 (S13-2) : titre neuf, annonce les deux operations (MD-10).
            "Multiplier ou diviser par 10 et 100",
            // S13-3 : titre neuf.
            "Les durées et les horaires",
            // S22 (S17-2) : titre neuf.
            "Lis et comprends",
            // S23-LIA : titre neuf.
            "Le bon mot de liaison",
            // S23-RAN : titre neuf.
            "Range les nombres",
            // S23-ETA : titre neuf.
            "Les problèmes à deux étapes",
            // S24-NAR : titre neuf.
            "Lis et réponds",
            // S24-PLU : titre neuf, espace insecable devant le point d'interrogation (TYP-1).
            "Un ou plusieurs ?",
            // S24-FRA : titre neuf.
            "Les fractions simples",
            // S25-FIG : titre neuf.
            "Les figures",
            // S25-MON-1 : titre neuf.
            "La monnaie",
            // S25-SUB : titre neuf. Sans point d'interrogation, donc sans espace devant un signe
            // double : un contenu neuf n'herite d'aucune dette (TYP-1, ADR-0067).
            "De qui parle le texte",
            // S25-SEM : titre neuf, il nomme la moitie servie de la ligne et pas l'autre.
            "La journée et la semaine",
            // S26-L1 a S26-L5 : cinq titres neufs.
            "La position du son",
            "Le son caché",
            "La bonne lettre",
            "Le bon groupe de lettres",
            "Écris le mot dicté",
            // S27-1 : titre neuf.
            "La bonne voyelle",
            // S27-2 : trois titres neufs.
            // S28-1, correction (nadia-prof, 2026-09-04) : "Vivant ou objet" portait l'erreur
            // que "un objet" repondait aussi a un item de matiere inerte (sable, neige).
            "Vivant ou pas vivant",
            "Le corps et les sens",
            "L'eau solide ou liquide",
            // S27-3 : titre neuf. Scinde en quatre titres par S35-CONS-4 (DEC-0088, ADR-0081).
            "Situer dans le temps",
            "Nommer, en histoire",
            "Nommer, en géographie",
            "Lire un document",
            // S28-3 : titre neuf.
            "Anglais",
            // S28-4 : deux titres neufs.
            "La multiplication posée",
            "La division posée",
            // S29-1 : deux titres neufs. Sans point d'interrogation, comme les titres des lots
            // S29-4 et S29-5 : un contenu neuf n'herite d'aucune dette (TYP-1, ADR-0067).
            "Le cycle de la vie",
            // S30-4 (bloc B, OBJ-R1) : titre corrige de "Fonction d'un objet" a
            // "Les objets de tous les jours", mot pour mot fourni par nadia-prof - le mot
            // "fonction" n'est pas au programme de lecture du CP et le titre promettait plus
            // etroit que le contenu (fonction et parties).
            "Les objets de tous les jours",
            // S29-4 : deux titres neufs. Sans point d'interrogation pour "Permis ou interdit" :
            // un contenu neuf n'herite d'aucune dette (TYP-1, ADR-0067).
            "Les règles de l'école",
            "Permis ou interdit",
            // S29-5 : deux titres neufs. Sans point d'interrogation, comme les titres du lot S29-4 :
            // un contenu neuf n'herite d'aucune dette (TYP-1, ADR-0067).
            "Fait ou opinion",
            "Droits et devoirs",
            // S30-1 : deux titres neufs.
            "Signal et information",
            // S39-ENERGIE (s39) : titre neuf.
            "Les sources et les formes d'énergie",
            "Qui mange qui ?",
            // S30-2 : deux titres neufs.
            "Mélanges et séparations",
            "Comment ça marche ?",
            // S31-1 : deux titres neufs. Sans point d'interrogation, comme les titres des lots
            // S29-4 et S29-5 : un contenu neuf n'herite d'aucune dette (TYP-1, ADR-0067).
            "Ce que ressent l'autre",
            "Les mots de politesse",
            // S31-2 : deux titres neufs, meme motif.
            "Une moquerie qui se répète",
            "Règle, souhait ou loi",
            // S31-3 : deux titres neufs.
            "États et changements d'état",
            "Quel est son régime ?",
            // S31-4 : deux titres neufs.
            "De quoi c'est fait ?",
            "Naître, grandir, vieillir",
            // S32-5 : deux titres neufs, premier lot d'education physique et sportive CP.
            "Les règles de sécurité",
            "Les règles du jeu collectif",
            // S34-EPS-CORPS-EFFORT : titre neuf, second lot d'education physique et sportive CP.
            "Le corps et l'effort",
            // S33-PAL2-1 (s34) : un titre neuf, premier lot de designation.
            "La journée, moment par moment",
            // S35-QLM11-PLANCLASSE (s35) : titre neuf, second lot de designation.
            "Le plan de la classe",
            // S37-1 (s37) : titre neuf, premier paquet CE1. Il ne porte pas son niveau
            // (ADR-0078) et se distingue de "Les petites additions", qui sert le CP.
            "Les grandes additions",
            // S38-COMPLEMENTS-DIZAINE (s38) : titre neuf.
            "Le compte jusqu'à la dizaine",
            // S38-MOITIES (s38) : titre neuf.
            "La moitié",
            // S38-SOUSTRACTIONS (s38) : titre neuf, second paquet CE1. Meme convention que
            // "Les grandes additions" : il ne porte pas son niveau (ADR-0078) et se distingue
            // de "Les petites soustractions", qui sert le CP.
            "Les grandes soustractions",
            // S39-MONNAIE (s39) : titre neuf, troisieme paquet CE1. Distinct de "La monnaie"
            // (CP, item illustre) pour ne pas dupliquer un libelle de bouton entre deux jeux
            // differents ; il ne porte pas son niveau (ADR-0078).
            "La monnaie et le rendu",
            // S39-OUTILS (s39) : titre neuf.
            "Les outils, supports et gestes",
            // S39-DEN (s39) : titre neuf.
            "Les nombres jusqu'à 100",
            // S39-LONGUEUR (s39) : titre neuf.
            "Les longueurs",
            // S39-SOLIDES (s39) : titre neuf.
            "Les solides",
            // S40-HDA (s40) : titre neuf.
            "L'art dans son contexte",
            // S40-NUTRITION (s40) : titre neuf.
            "Le corps qui se nourrit",
            // S40-REGLES (s40) : titre neuf.
            "Les règles de la vie collective",
            // S40-LOI (s40) : titre neuf.
            "Règle ou loi",
            // S40-DEVISE (s40) : titre neuf.
            "La devise de la France",
            // S40-EXTRACTION (s40) : titre neuf.
            "Lis le document scientifique",
            // S41-EMC-ROLES (s41) : titre neuf.
            "Les rôles de l'école et de la commune",
            // S41-SCI-CLASSER (s41) : titre neuf.
            "Classer les êtres vivants",
            // S41-QLM-HYGIENE (s41) : titre neuf.
            "Les règles d'hygiène de vie",
            // S41-QLM-SAISONS (s41) : titre neuf.
            "Les mois et les saisons",
            // S41-EMC-SYMBOLES (s41) : titre neuf.
            "Les symboles de la République",
            // S41-EMC-DROITS (s41) : titre neuf.
            "Les droits de l'enfant",
            // S42-MAT-TEXTUEL (s42) : titre neuf.
            "Décrire un déplacement",
            // S42-ART-COULEURS (s42) : titre neuf.
            "Les couleurs et les mélanges",
            // S42-EMC-SYMBOLES-CM1 (s42) : titre neuf, distinct de "Les symboles de la
            // République" (CP, S41-EMC-SYMBOLES) pour ne pas dupliquer un libelle de bouton
            // entre deux jeux differents.
            "L'histoire des symboles de la République",
            // S42-QLM-MATIERE (s42) : titre neuf.
            "La matière des objets",
            // S43-MAT-DUREE (s43) : titre neuf, distinct de "Les durées et les horaires"
            // (CM1, bandit-manchot-problemes-durees-cm1) pour ne pas dupliquer un libelle de
            // bouton entre deux jeux differents ; il ne porte pas son niveau (ADR-0078).
            "Le temps qui passe",
            // S43-QLM-NUMERIQUE (s43) : titre neuf.
            "Les objets numériques du quotidien",
            // S43-EPS-EFFETS-ACTIVITE (s43) : titre neuf.
            "Les effets de l'activité physique",
            // S44-MAT-DIVISION (s44) : titre neuf.
            "La division",
            // S44-MUS-VOCABULAIRE (s44) : titre neuf.
            "Les mots de la musique",
            // S45-SCI-NUMERIQUE (s45) : titre neuf.
            "Se protéger en ligne",
            // S44-SCI-OBJET-TECHNIQUE (s44) : titre neuf.
            "Comment ça a changé ?",
            // S45-MUS-INSTRUMENTS (s45) : titre neuf.
            "Les instruments de musique",
            // S45-MAT-ADDITION (s45) : titre neuf.
            "Les additions posées",
            // S45-EPS-ROLES (s45) : titre neuf.
            "Arbitre, observateur, marqueur",
            // S46-EMC-INSTITUTIONS-CM1 (s46) : titre neuf.
            "Les institutions de ma commune",
            // S46-SCI-PHENOMENES-CM1 (s46) : titre neuf.
            "Les risques naturels",
            // S51-SCI-CORPS-CM1 (s51) : titre neuf.
            "Prendre soin de son corps",
            // S54-BR-SCI-CM1-18 (s54) : titre neuf, sans niveau (ADR-0078).
            "L'être humain et l'environnement",
            // S54-BR-SCI-CM1-15 (s54) : titre neuf, sans niveau (ADR-0078).
            "Le jour, la nuit et les saisons",
            // S54-BR-ART-CP-10 (s54) : titre neuf, sans niveau (ADR-0078).
            "Les familles d'œuvres",
            // S54-BR-EMC-CM1-02 (s54) : titre neuf, sans niveau (ADR-0078).
            "Ce qui blesse les autres",
            // S55-BR-EMC-CM1-03 (s55) : titre neuf, sans niveau (ADR-0078).
            "Ce que le respect interdit",
            // S55-BR-EMC-CM1-06 (s55) : titre neuf, sans niveau (ADR-0078).
            "Les principes de la République",
            // S46-EMC-EMOTION-CM1 (s46) : titre neuf.
            "Ressentir et agir",
            // S46-SCI-MOUVEMENT-CM1 (s46) : titre neuf.
            "Trajectoire et vitesse",
            // S46-MAT-SOUSTRACTION (s46) : titre neuf. Complete ici, voir le motif pres de l'id.
            "Les soustractions posées",
            // S47-LVE-CULTURE-CP (s47) : titre neuf.
            "Le monde anglophone",
            // S47-EMC-VOTE-CM1 (s47) : titre neuf.
            "Le vote et la représentation",
            // S47-QLM-AUTREFOIS (s47) : titre neuf.
            "Autrefois et aujourd'hui",
            // S47-EMC-ALERTE-CP (s47) : titre neuf.
            "Donner l'alerte",
            // S47-MAT-TABLES (s47) : titre neuf, distinct de celui du CM1 ("Les tables de
            // multiplication") et sans mention de niveau.
            "Mémoriser ses tables de multiplication",
            // S48-MAT-PROBLEMES (s48) : titre neuf.
            "Les problèmes à étapes",
            // S49-FRA-GRAPHEMES (s49) : titre neuf. Il ne porte pas son niveau (ADR-0078) et ne
            // reprend pas "Le bon son" (CP, bandit-manchot-le-bon-son-cp).
            "Le même son",
            // S50-FRA-VALEUR-LETTRE (s50) : titre neuf, sans niveau (ADR-0078).
            "Une lettre, deux sons",
            // S50-FRA-HOMOPHONES (s50) : titre neuf, sans niveau (ADR-0078), distinct de "Le bon
            // petit mot" (CM1, bandit-manchot-homophones-cm1).
            "Les bons mots",
            // S51-FRA-TEMPS (s51) : titre neuf, sans niveau (ADR-0078), avec l'espace insecable
            // d'ADR-0067 devant le point d'interrogation.
            "Quand ça se passe ?",
            // S51-FRA-ALPHABETIQUE (s51) : titre neuf, sans niveau (ADR-0078).
            "Comme dans le dictionnaire",
            // S51-FRA-GRAPHIE (s51) : titre neuf, sans niveau (ADR-0078), avec l'espace insecable
            // d'ADR-0067 devant le point d'interrogation.
            "Comment ça s'écrit ?",
            // S52-FRA-CATEGORIES (s52) : titre neuf, sans niveau (ADR-0078).
            "Les mots d'un même groupe",
            // S52-FRA-PRESENT (s52) : titre neuf, sans niveau (ADR-0078).
            "Les verbes au présent",
            // S54-BR-FRA-CE1-18 (s54) : titre neuf, sans niveau (ADR-0078).
            "Les verbes au futur",
            // S54-BR-FRA-CE1-19 (s54) : titre neuf, sans niveau (ADR-0078).
            "Les verbes au passé composé",
            // S55-BR-FRA-CE1-L1 (s55) : titre neuf, sans niveau (ADR-0078). L'espace avant le point
            // d'interrogation est insecable (ADR-0067).
            "Un ou plusieurs ?",
            // S55-BR-FRA-CE1-L2 (s55) : titre neuf, sans niveau (ADR-0078). L'espace avant le point
            // d'interrogation est insecable (ADR-0067).
            "Quelle sorte de mot ?",
            // S56-BR-FRA-CE1-13 (s56) : titre neuf, sans niveau (ADR-0078), celui que propose
            // nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md,
            // section 2.6).
            "Le verbe et son sujet",
            // S57-BR-FRA-CE1-10 (s57) : titre neuf, sans niveau (ADR-0078).
            "Les types de phrases",
            // S57-BR-FRA-CE1-15 (s57) : titre neuf, sans niveau (ADR-0078).
            "Des mots bien accordés",
            // S57-BR-FRA-CE1-05 (s57) : titre neuf, sans niveau (ADR-0078), sans signe double.
            "Il, elle, ils, elles",
            // S57-BR-FRA-CE1-06 (s57) : titre neuf, sans niveau (ADR-0078), sans signe double.
            "Ce que le texte ne dit pas",
            // S57-BR-FRA-CE1-07 (s57) : titre neuf, sans niveau (ADR-0078), sans signe double.
            "Lis pour savoir",
            // S57-BR-FRA-CE1-11 (s57) : titre neuf, sans niveau (ADR-0078), sans signe double.
            "Des phrases qui disent non",
            // S57-BR-MAT-CE1-01 (s57) : titre neuf, sans niveau (ADR-0078).
            "Les nombres jusqu'à 1000",
            // S57-BR-MAT-CE1-10 (s57) : titre neuf, sans niveau (ADR-0078).
            "Les masses et les contenances",
            // S57-BR-EMC-CE1-01 (s57) : titre neuf, sans niveau (ADR-0078).
            "Les biens de tous",
            // S52-FRA-SYNONYMES-CONTRAIRES (s52) : titre neuf, sans niveau (ADR-0078).
            "Même sens ou sens contraire",
            // S57-BR-FRA-CE1-24 (s57) : titre neuf, sans niveau (ADR-0078).
            "Les familles de mots",
            // S50-ART (s50) : titre neuf, sans niveau (ADR-0078).
            "Décrire une œuvre",
            // S55-ART-CP-10-PAQUET (s56) : titre neuf, sans niveau (ADR-0078), de nadia-prof (B9).
            "Des œuvres faites de la même façon",
            // S51-MAT-COMPARER-RANGER (s52) : titre neuf, sans niveau (ADR-0078).
            "Comparer, ranger, encadrer",
            // S51-MAT-MULTIPLICATION (s52) : titre neuf, sans niveau (ADR-0078).
            "Le sens de la multiplication",
            // S51-MAT-SYMETRIE (s52) : titre neuf, sans niveau (ADR-0078).
            "La symétrie",
            // consignes (catalogue.yaml, GameDefinition.consigne)
            "Combien ça fait ?",
            // S37-1 (s37) : consigne neuve, et elle n'est pas la meme chaine que celle du CP
            // juste au-dessus - celle-ci porte le blanc insecable qu'ADR-0067 (TYP-1) exige
            // d'un contenu neuf, l'autre est dans la table de dette. La comparaison est une
            // egalite (ADR-0039) : deux graphies, deux entrees.
            "Combien ça fait ?",
            "Quel verbe complète la phrase ?",
            "Dis le mot. Quel son entends-tu ?",
            "Combien en reste-t-il ?",
            "Lis bien, puis trouve la réponse !",
            "Quel signe faut-il mettre ?",
            "Combien manque-t-il pour faire 10 ?",
            "Écris le nombre qui manque pour faire 10.",
            "Quel est le résultat ?",
            "Quel est le périmètre ?",
            "Convertis dans la bonne unité !",
            "Lis, écris, décompose ou compare !",
            // S17-3 (ADR-0052) : consigne devenue porteuse, generique, designe le mot
            // cible par sa marque exacte (SYN-6).
            "Quel mot peut remplacer le mot entre guillemets sans changer le sens ?",
            // S19-1 : consigne neuve, meme marque du mot cible (guillemets), verbe distinct
            // ("contraire" plutot que "remplacer... sans changer le sens").
            "Quel mot veut dire le contraire du mot entre guillemets ?",
            // S17-4 : consigne neuve.
            "Quel mot complète la phrase ?",
            // S19-3 : consigne neuve.
            "Quelle est la bonne forme du verbe ?",
            // DEC-0048 : consigne restauree.
            "Le mot entre guillemets est-il un nom ou un verbe ?",
            // DEC-0050 : consigne restauree.
            "Que manque-t-il à cette phrase ?",
            // S21-9 : consigne neuve.
            "Tape dans tes mains. Combien de syllabes ?",
            // S21-10 : consigne neuve.
            "Trouve le mot qui complète la phrase.",
            // S21-11 : consigne neuve.
            "Regarde bien, puis compare.",
            // S13-3 : consigne neuve.
            "Trouve la durée ou l'heure qui manque !",
            // S22 (S17-2) : consigne neuve, generique - ne nomme aucun texte-support d'aucun item.
            "Lis le texte, puis réponds à la question.",
            // S23-LIA : consigne neuve.
            "Quel mot complète le texte ?",
            // S23-RAN : consigne neuve, fixe le sens croissant pour tout le paquet (RAN-1).
            "Choisis la liste rangée du plus petit au plus grand.",
            // S23-ETA : consigne neuve.
            "Lis bien, résous les deux étapes, puis choisis la réponse.",
            // S24-NAR : consigne neuve, generique - ne nomme aucun texte-support d'aucun item.
            "Lis le petit texte, puis réponds à la question.",
            // S24-PLU : consigne neuve.
            "Choisis la bonne écriture pour compléter la phrase.",
            // S24-FRA : consigne neuve.
            "Lis bien la fraction, puis choisis la réponse.",
            // S25-FIG : consigne neuve, generique - elle dit quoi faire du dessin et ne decrit
            // aucune figure (CON-2).
            "Regarde bien le dessin, puis choisis son nom.",
            // S25-MON-1 : consigne neuve. Elle ne nomme aucune valeur et ne decrit ni la position,
            // ni la forme, ni la quantite, ni la couleur de ce que les dessins montrent (MON-1,
            // CON-2) - elle dit seulement ou regarder.
            "Regarde bien l'argent, puis réponds.",
            // S25-SUB : consigne neuve, generique - elle ne nomme aucun texte-support ni aucun
            // personnage d'aucun item.
            "Lis le texte, puis dis de qui on parle.",
            // S25-SEM : consigne neuve.
            "Choisis la bonne réponse.",
            // S26-L1 a S26-L5 : cinq consignes neuves.
            "Écoute l'adulte lire le mot, puis dis si le son cherché est au début, au milieu ou à la fin.",
            "Écoute le mot que l'adulte va dire, puis réponds oui ou non.",
            // Correction du 2026-09-01 (nadia-prof, second motif de son refus, addendum
            // section 17.3) : "choisis la lettre qui fait ce son" ne disait pas a l'enfant que le
            // son cherche est le premier du mot.
            "Écoute l'adulte lire le mot, puis choisis la lettre qui fait le tout premier son.",
            "Écoute l'adulte lire le mot, puis choisis le groupe de lettres qui fait ce son.",
            "Écoute le mot que l'adulte va dire, puis écris-le.",
            // S27-2 : trois consignes neuves. La consigne de cp-voyelle-du-son (S27-1) est reprise
            // a l'identique de "bandit-manchot-lettre-du-son-cp" ci-dessus, aucune entree neuve.
            //
            // S28-1 : les deux consignes specifiques de S27-2 ("Lis bien, puis dis si c'est un
            // être vivant ou un objet." et "Lis bien, puis dis si c'est solide ou liquide.")
            // avaient deja disparu de cette liste - le catalogue reel ne les produisait plus. Les
            // deux paquets portaient chacun une seconde tache depuis S28-1 (FER-3) que ces
            // consignes ne decrivaient plus ; ils partageaient alors la consigne generique.
            //
            // S30-9 (CONS-1, DEC-0078) : "Lis bien la question, puis choisis la bonne réponse."
            // disparaît a son tour de cette liste - le catalogue reel ne la produit plus. Les dix
            // entrees qui la partageaient encore (questionner le monde CP, histoire-geographie
            // CM1, anglais CM1, EMC CP, EMC CM1) portent chacune desormais une consigne propre,
            // listee ici meme si son paquet est ne a un sprint anterieur.
            "Lis bien, puis dis si l'eau est solide ou liquide, ou nomme le changement d'état.",
            "Lis bien, puis dis ce que fait l'être vivant, ou réponds par oui ou par non.",
            "Lis bien, puis dis à quoi sert l'objet, ou nomme sa partie.",
            "Lis bien, puis dis ce qu'il faut faire, ou pourquoi.",
            "Lis bien, puis dis ce que fait la personne, ou pourquoi c'est interdit.",
            // Correction du 2026-09-06 (lea-dev, audit de l'instance A, CONS-1,
            // squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md,
            // section 7.3) : cinq des dix consignes reecrites a S30-9 ne tenaient pas contre le
            // paquet qu'elles introduisent. Reecrites une seconde fois, chacune contre son paquet.
            "Lis bien, puis dis si c'est un être vivant, ou lequel des trois l'est.",
            "Lis bien, puis nomme la partie du corps.",
            "Traduis le mot, ou réponds à la question sur les pays anglophones.",
            "Lis bien, puis dis si c'est un droit ou un devoir, ou à quoi ça sert.",
            // Correction du 2026-09-07 (lea-dev, CONS-1, reaudit de nadia-prof,
            // squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md,
            // complement du 2026-09-06, section 3.3) : la consigne du 2026-09-06 ne nommait que
            // deux taches sur les quatre du paquet (situer dans le temps, associer un roi a son
            // fait) et etait fausse sur 31 des 57 items (toute la geographie, 21 items, et la
            // lecture de document, 10 items). Reecrite une troisieme fois pour nommer les quatre
            // familles du paquet, comptees par nadia-prof : 16 + 10 + 21 + 10 = 57 items couverts.
            // Rejeu borne a s31.
            //
            // S35-CONS-4 (DEC-0088, ADR-0081) : la consigne unique ci-dessus, qui enumerait cinq
            // taches distinctes en une seule clause, est remplacee par une consigne par entree - une
            // seule tache, en une seule clause, par entree issue de la scission.
            "Situe le fait dans le temps, ou remets les faits dans l'ordre.",
            "Nomme ce que l'énoncé décrit.",
            "Lis le document fourni, puis réponds à la question.",
            // S28-4 : deux consignes neuves.
            "Pose l'opération, puis choisis le bon résultat.",
            "Pose l'opération, puis choisis le bon quotient et le bon reste.",
            // Correction du 2026-09-06 (lea-dev, audit de l'instance B, reprise de
            // squad/conformite/FICHE-bandit-manchot-fait-opinion-cm1.md, motif 1 de
            // nadia-prof - la tache n'etait ecrite nulle part sur les six items de
            // classement) : consigne neuve, propre a bandit-manchot-fait-opinion-cm1,
            // remplace la consigne generique partagee jusque-la avec les trois autres
            // paquets d'EMC.
            //
            // S30-7 (FOP-R1, reroutage de sofia-sm en cours de sprint) : cette consigne
            // ne nommait que la tache de classement (six manches sur onze), en silence sur
            // la tache de verification de l'origine d'une information (cinq manches). Elle
            // nomme desormais les deux taches reellement posees, plutot que de revenir a la
            // consigne generique que DEC-0078 interdit desormais aux entrees du catalogue.
            "Lis bien, puis dis si c'est un fait ou une opinion, ou comment vérifier une "
                    + "information.",
            // S30-1 : deux consignes neuves.
            "Lis bien, puis dis quel est le signal, ou ce qu'il veut dire.",
            // S39-ENERGIE (s39) : consigne neuve.
            "Lis bien, puis dis quelle est la source ou la forme, ou comment l'énergie se "
                    + "transforme.",
            "Lis bien, puis dis qui mange qui, ou ce qui pourrait arriver.",
            // S30-2 : deux consignes neuves.
            "Lis bien, puis dis si le mélange est homogène ou hétérogène, ou choisis le bon moyen "
                    + "de le séparer.",
            "Lis bien, puis dis à quoi sert l'objet, ou nomme sa pièce.",
            // S31-1 : deux consignes neuves.
            "Lis bien, puis dis ce que ressent la personne, ou ce que montre son geste.",
            "Lis bien, puis dis quel mot employer, ou quelle règle n'est pas respectée.",
            // S31-2 : deux consignes neuves.
            // S33-EXC-R1 : la consigne de bandit-manchot-exclusion-cp ne nommait plus la tache
            // reellement servie depuis la reecriture de s31 (aucune proposition de la tache A n'est
            // une action) - corrigee en meme temps que le paquet.
            "Lis bien, puis dis ce qui se passe, ou à quel adulte en parler.",
            "Lis bien, puis dis ce que c'est, ou qui doit la respecter.",
            // S31-3 : deux consignes neuves.
            "Lis bien, puis dis dans quel état se trouve la matière, ou nomme le changement d'état.",
            "Lis bien, puis dis quel est le régime de l'animal, ou à quoi sert sa nourriture.",
            // S31-4 : deux consignes neuves.
            "Lis bien, puis dis de quelle famille de matériau il s'agit, ou quel matériau choisir.",
            "Lis bien, puis dis quelle étape vient après, ou ce qui la caractérise.",
            // S32-5 : deux consignes neuves, premier lot d'education physique et sportive CP.
            "Lis bien, puis dis quelle règle de sécurité s'applique, ou pourquoi elle existe.",
            "Lis bien, puis dis quelle est la règle du jeu, ou ce qui se passe dans la situation.",
            // S34-EPS-CORPS-EFFORT : consigne neuve, second lot d'education physique et sportive
            // CP.
            "Lis bien, puis dis ce que l'effort fait au corps, ou quelle règle l'entoure.",
            // S33-PAL2-1 (s34) : une consigne neuve, premier lot de designation.
            "Montre le moment qu'on te décrit.",
            // S35-QLM11-PLANCLASSE (s35) : consigne neuve, second lot de designation.
            "Montre l'endroit qu'on te décrit.",
            // S38-COMPLEMENTS-DIZAINE (s38) : consigne neuve.
            "Quel nombre manque ?",
            // S39-MONNAIE (s39) : consigne neuve, generique - designe les deux taches (composer
            // une somme, trouver un rendu) sans nommer aucun montant d'aucun item.
            "Compte la somme, ou trouve ce qui est rendu.",
            // S39-OUTILS (s39) : consigne neuve.
            "Dis quel outil, quel support ou quel geste convient.",
            // S54-BR-ART-CP-10 (s54) : consigne neuve ; remplacee a s55 par celle-ci (S54-ART-RES-R1),
            // deja inventoriee pour d'autres jeux.
            "Lis bien, puis choisis la bonne réponse.",
            // S38-MOITIES (s38) : consigne neuve.
            "Quelle est la moitié ?",
            // S39-DEN (s39) : consigne neuve.
            "Lis le nombre, écris-le, ou trouve ses dizaines et ses unités.",
            // S39-LONGUEUR (s39) : consigne neuve.
            "Compare les longueurs, ou convertis-les en centimètres et en mètres.",
            // S39-SOLIDES (s39) : consigne reprise de "Regarde bien le dessin, puis choisis son
            // nom." (bandit-manchot-figures-planes-cp), deja dans cet inventaire, aucune entree
            // neuve.
            // S40-HDA (s40) : consigne neuve.
            "Lis bien, puis relie l'œuvre décrite à son époque, sa civilisation ou son usage.",
            // S40-NUTRITION (s40) : consigne neuve.
            "Lis bien, puis dis quel organe, ou quelle fonction du corps.",
            // S40-REGLES (s40) : consigne neuve.
            "Lis bien, puis dis ce qu'il faut faire à l'école, ou pourquoi cette règle existe.",
            // S40-LOI (s40) : consigne neuve.
            "Lis bien, puis dis si c'est une règle ou une loi, ou ce que fait cette loi.",
            // S40-DEVISE (s40) : consigne neuve.
            "Lis bien, puis dis la devise, ou choisis le bon exemple.",
            // S40-EXTRACTION (s40) : consigne neuve.
            "Lis le texte ou le tableau, puis réponds à la question.",
            // S41-EMC-ROLES (s41) : consigne neuve.
            "Lis bien, puis dis à qui s'adresser, ou ce que fait cette personne.",
            // S41-SCI-CLASSER (s41) : consigne neuve.
            "Lis bien, puis dis lequel des trois ne partage pas le même caractère.",
            // S41-QLM-HYGIENE (s41) : consigne neuve.
            "Lis bien, puis dis quelle règle suivre, ou pourquoi elle est bonne pour la santé.",
            // S41-QLM-SAISONS (s41) : consigne neuve.
            "Lis bien, puis nomme le mois ou la saison, ou dis lequel vient avant ou après.",
            // S41-EMC-SYMBOLES (s41) : consigne neuve.
            "Lis bien, puis dis le nom du symbole, ou ce qu'il représente.",
            // S41-EMC-DROITS (s41) : consigne neuve.
            "Lis bien, puis dis quel droit c'est, ou ce qu'il garantit.",
            // S42-MAT-TEXTUEL (s42), corrigee suite au refus nadia-prof (DEC-0098) : la tache
            // demande desormais la case d'arrivee, plus jamais l'etiquette d'un geste.
            "Lis bien, puis dis sur quelle case l'enfant se trouve.",
            // S42-ART-COULEURS (s42) : consigne neuve.
            "Dis le nom de la couleur, ou ce qu'on obtient en mélangeant deux couleurs.",
            // S42-EMC-SYMBOLES-CM1 (s42) : consigne neuve.
            "Lis bien, puis dis d'où vient ce symbole, où on le voit, ou ce que c'est.",
            // S42-QLM-MATIERE (s42) : consigne neuve.
            "Lis bien, puis dis en quelle matière est fait l'objet, ou trie les matières selon "
                    + "leurs propriétés.",
            // S43-MAT-DUREE (s43) : consigne neuve.
            "Trouve combien de temps s'est écoulé.",
            // S43-QLM-NUMERIQUE (s43) : consigne neuve.
            "Lis bien, puis dis quel objet est numérique, ou quelle règle simple d'usage "
                    + "s'applique.",
            // S43-EPS-EFFETS-ACTIVITE (s43) : consigne neuve.
            "Lis bien, puis dis la cause commune, l'effort qui se distingue, ou la conduite de "
                    + "sécurité.",
            // S44-MAT-DIVISION (s44) : consigne neuve.
            "Partage la quantité, ou trouve le nombre de groupes.",
            // S44-MUS-VOCABULAIRE (s44) : consigne neuve.
            "Lis bien, puis dis le mot juste, ou ce qu'il veut dire.",
            // S44-SCI-OBJET-TECHNIQUE (s44) : consigne neuve.
            "Lis bien, puis dis ce qui a changé, ou ce qui n'a pas changé.",
            // S45-MUS-INSTRUMENTS (s45) : consigne neuve.
            "Lis bien, puis dis l'instrument, ou sa famille.",
            // S45-SCI-NUMERIQUE (s45) : consigne neuve.
            "Lis bien, puis dis la bonne pratique, ou pourquoi c'est risqué.",
            // S45-EPS-ROLES (s45) : consigne neuve.
            "Lis bien, puis dis ce que décide l'arbitre, ce que relève l'observateur, ou le score "
                    + "que donne le marqueur.",
            // S46-EMC-INSTITUTIONS-CM1 (s46) : consigne neuve.
            "Lis bien, puis dis à quelle institution s'adresser, ou quel est son rôle.",
            // S46-SCI-PHENOMENES-CM1 (s46) : consigne neuve.
            "Lis bien, puis dis quel phénomène, ou quel geste protège.",
            // S51-SCI-CORPS-CM1 (s51) : consigne neuve.
            "Lis bien, puis dis ce qui est bon pour le corps, ou à quoi cela sert.",
            // S54-BR-SCI-CM1-18 (s54) : consigne neuve.
            "Lis bien, puis dis quel est l'effet, ou quel geste préserve.",
            // S54-BR-SCI-CM1-15 (s54) : consigne neuve.
            "Lis bien, puis dis pourquoi il y a le jour et la nuit, ou les saisons.",
            // S46-EMC-EMOTION-CM1 (s46) : consigne neuve.
            "Lis bien, puis dis quelle émotion ressent la personne, ou distingue ce qu'elle "
                    + "ressent de ce qu'elle fait.",
            // S46-SCI-MOUVEMENT-CM1 (s46) : consigne neuve.
            "Lis bien, puis dis la trajectoire, le repère, ou qui va le plus vite.",
            // S47-LVE-CULTURE-CP (s47) : consigne neuve.
            "Lis bien, puis dis de quel pays on parle, ou réponds à la question.",
            // S47-EMC-VOTE-CM1 (s47) : consigne neuve ; corrigee en s47 (lot F, nadia-prof,
            // FICHE-bandit-manchot-vote-election-cm1.md, section 6 point 4) pour couvrir la tache A.
            "Lis bien, puis trouve le bon mot, ou dis qui vote, qui est élu, ou ce que fait un"
                    + " délégué.",
            // S47-QLM-AUTREFOIS (s47) : consigne neuve.
            "Lis bien, puis dis si c'est autrefois ou aujourd'hui, ou par quoi c'est remplacé.",
            // S47-EMC-ALERTE-CP (s47) : consigne neuve.
            "Lis bien, puis dis le bon numéro, ou ce qu'il faut faire.",
            // S47-MAT-TABLES (s47) : consigne neuve, blanc insecable devant "?" (TYP-1, ADR-0067).
            "Quel est le résultat ?",
            // S48-MAT-PROBLEMES (s48) : consigne neuve, blanc insecable devant "!" (TYP-1,
            // ADR-0067) - distincte de "Lis bien, puis trouve la réponse !"
            // (cp-problemes-additifs), deja dans la table de dette de TYP-1 avec son espace
            // ordinaire.
            "Lis bien, puis trouve la réponse !",
            // S49-FRA-GRAPHEMES (s49) : consigne neuve, generique - elle ne nomme ni le mot de
            // reference ni aucune proposition d'aucun item.
            "Lis bien, puis choisis le mot qui a le même son.",
            // S50-FRA-VALEUR-LETTRE (s50) : consigne neuve, generique - elle ne nomme ni la
            // lettre, ni le mot cible, ni aucun des six mots de reference d'aucun item.
            "Lis bien, puis choisis le bon mot.",
            // S50-FRA-HOMOPHONES (s50) : consigne neuve, generique - elle ne nomme aucun des
            // quatre homophones d'aucun item.
            "Lis bien, puis choisis le mot qui convient.",
            // S51-FRA-TEMPS (s51) : consigne neuve, generique - elle ne nomme aucun des trois
            // temps proposes.
            "Lis bien, puis choisis quand la phrase se passe.",
            // S51-FRA-ALPHABETIQUE (s51) : consigne neuve, generique - elle ne nomme aucun mot
            // d'aucun item.
            "Lis les mots, puis choisis la bonne réponse.",
            // S51-FRA-GRAPHIE (s51) : consigne neuve, generique - elle ne nomme aucun mot.
            "Lis bien, puis choisis le mot bien écrit.",
            // S52-FRA-CATEGORIES (s52) : consigne neuve, generique - elle ne nomme aucune
            // categorie ni aucun mot d'aucun item.
            "Lis bien, puis choisis la bonne réponse.",
            // S52-FRA-PRESENT (s52) : consigne neuve, generique - elle ne nomme aucun verbe ni
            // aucune forme.
            "Lis bien, puis choisis la bonne forme du verbe.",
            // S54-BR-FRA-CE1-18 (s54) : consigne neuve, elle nomme le temps demande et aucun verbe ni
            // aucune forme (squad/conformite/ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md,
            // section 5).
            "Lis bien, puis choisis le verbe au futur.",
            // S55-BR-FRA-CE1-L2 (s55) : consigne neuve, elle nomme "sorte" et non "classe" (au CE1,
            // la classe est d'abord la salle) et aucune des cinq classes
            // (squad/conformite/ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md, section 5, K5).
            "Lis bien, puis dis quelle sorte de mot est entre guillemets.",
            // S50-ART (s50) : consigne neuve, generique - elle ne nomme aucun sujet, aucune forme
            // ni aucun materiau d'aucun item.
            "Regarde l'œuvre, puis dis ce que tu vois.",
            // S55-ART-CP-10-PAQUET (s56) : consigne neuve de nadia-prof (B9) ; elle finit par un point,
            // aucun signe double (ADR-0067).
            "Regarde l'œuvre, puis trouve celle qui est faite de la même façon.",
            // S51-MAT-COMPARER-RANGER (s52) : consigne neuve, generique - elle ne nomme ni la tache
            // ni aucun nombre d'aucun item.
            "Lis bien les nombres, puis choisis la bonne réponse.",
            // S57-BR-MAT-CE1-10 (s57) : consigne neuve, sans signe double.
            "Compare les masses et les contenances, ou choisis la bonne unité.",
            // S51-MAT-MULTIPLICATION (s52) : consigne neuve, generique - elle ne nomme aucun nombre
            // ni aucune forme d'item.
            "Lis bien ou regarde le dessin, puis choisis la bonne écriture.",
            // S51-MAT-SYMETRIE (s52) : consigne neuve, generique - elle ne nomme ni axe ni case.
            "Regarde bien le dessin, puis touche le bon numéro.",
            // mecanique (catalogue.yaml, GameDefinition.mecanique().value()) : identifiant technique.
            // S24 : deux valeurs depuis que la saisie est servie, la premiere seconde mecanique du
            // produit (ADR-0058). Comptees sur catalogue.yaml, jamais recopiees.
            "bandit-manchot",
            "saisie",
            // S33-PAL2-1 (s34) : troisieme valeur, la designation (ADR-0080).
            "designation",
            // matiere (descripteur de chaque paquet reference par le catalogue).
            // S19-5 (ADR-0049) : graphie accentuee, corrigee depuis S16-5.
            "Mathématiques",
            "Français",
            // S27-2 : matiere neuve du produit.
            "Questionner le monde",
            // S27-3 : matiere neuve, premiere matiere neuve du produit sur ce referentiel.
            "Histoire-géographie",
            // S28-3 : matiere neuve, premier paquet d'anglais.
            "Langues vivantes (anglais)",
            // S29-4 : matiere neuve, premier lot d'enseignement moral et civique.
            "Enseignement moral et civique",
            // S30-1 : matiere neuve, sciences et technologie CM1.
            "Sciences et technologie",
            // S32-5 : matiere neuve, premier lot d'education physique et sportive.
            "Éducation physique et sportive",
            // S39-OUTILS (s39) : matiere neuve, treizieme matiere du corpus servi.
            "Arts plastiques",
            // S40-HDA (s40) : matiere neuve, quatorzieme matiere du corpus servi.
            "Histoire des arts",
            // S44-MUS-VOCABULAIRE (s44) : matiere neuve, quinzieme matiere du corpus servi,
            // premiere ligne d'education musicale.
            "Éducation musicale",
            // niveau (descripteur) : "CP" et "CM1" sont exemptes ci-dessus, "CE1" entre ici.
            // S37-1 (s37) : troisieme niveau du produit. Il n'a pas plus de lettre
            // accentuable que les deux autres, mais l'exemption enumeree qui les couvre est
            // un critere enumere assume, plafonne et compte ; l'inventaire est strictement
            // plus fort et ne coute rien ici.
            "CE1",
            // domaine (descripteur)
            "Nombres et calculs",
            "Orthographe et grammaire",
            "Grandeurs et mesures",
            // S17-3 : domaine neuf.
            "Vocabulaire",
            // S19-5 (ADR-0049) : graphie accentuee, corrigee depuis S16-5.
            "Lecture et compréhension de l'écrit",
            // S21-9 : domaine neuf, premiere ligne couverte.
            "Langage oral et conscience phonologique",
            // S25-FIG : domaine neuf, premiere ligne couverte du domaine geometrique du CP.
            "Espace et géométrie",
            // S26-L5 : domaine neuf, premier paquet de ce domaine.
            "Écriture",
            // S27-2 : domaine neuf, premier paquet de "Questionner le monde" (les trois paquets
            // de ce lot partagent ce meme domaine, ligne 1, 3 et 5 du referentiel).
            "Le vivant, la matière et les objets",
            // S27-3 : domaine neuf, premier paquet d'histoire-geographie.
            "Repères historiques et géographiques",
            // S28-3 : domaine neuf, premier paquet d'anglais.
            "Lire et comprendre ; découverte culturelle",
            // S29-4 : deux domaines neufs, premier lot d'enseignement moral et civique (les deux
            // paquets du lot servent chacun une des trois finalites du referentiel).
            "Respecter autrui",
            "Acquérir et partager les valeurs de la République",
            // S29-5 : domaine neuf, second lot d'enseignement moral et civique (ligne fait/opinion,
            // la ligne droit/devoir partage le domaine "Acquérir et partager les valeurs de la
            // République" deja liste ci-dessus).
            "Construire une culture civique",
            // S30-1 : deux domaines neufs, premier lot de sciences et technologie CM1.
            "Matière, mouvement, énergie, information",
            "La planète Terre. Les êtres vivants dans leur environnement",
            // S30-2 : domaine neuf, second lot de sciences et technologie CM1.
            "Matériaux et objets techniques",
            // S31-3 : domaine neuf, partage par les deux paquets du premier lot de sciences et
            // technologie CM1 en second passage (regimes alimentaires, etapes du developpement).
            // "Matière, mouvement, énergie, information" et "Matériaux et objets techniques" sont
            // deja lists ci-dessus (S30-1, S30-2), reutilises par S31-3 et S31-4 sans nouvelle
            // entree.
            "Le vivant, sa diversité et les fonctions qui le caractérisent",
            // S32-5 : deux domaines neufs, premier lot d'education physique et sportive CP
            // (copies conformes du referentiel de nadia-prof,
            // squad/programme/education-physique-et-sportive/competences-cp.md).
            "Adapter ses déplacements à des environnements variés",
            "Conduire et maîtriser un affrontement individuel ou collectif",
            // S34-EPS-CORPS-EFFORT : domaine neuf, second lot d'education physique et sportive CP
            // (copie conforme du referentiel de nadia-prof,
            // squad/programme/education-physique-et-sportive/competences-cp.md).
            "Entretenir sa santé par la pratique",
            // S43-EPS-EFFETS-ACTIVITE (s43) : domaine neuf, premier lot d'education physique et
            // sportive CM1 (copie conforme du referentiel de nadia-prof,
            // squad/programme/education-physique-et-sportive/competences-cm1.md).
            "Apprendre à entretenir sa santé",
            // S33-PAL2-1 (s34) : un domaine neuf, premier lot de designation.
            "Se situer dans le temps",
            // S35-QLM11-PLANCLASSE (s35) : domaine neuf, second lot de designation.
            "Se situer dans l'espace",
            // S39-OUTILS (s39) : domaine neuf, premiere ligne d'arts plastiques du produit
            // (copie conforme du referentiel de nadia-prof,
            // squad/programme/arts-plastiques/competences-cp.md).
            "Se repérer dans les domaines liés aux arts plastiques",
            // S40-HDA (s40) : domaine neuf, premier paquet d'histoire des arts (copie conforme du
            // referentiel de nadia-prof, squad/programme/histoire-des-arts/competences-cm1.md).
            "Situer une œuvre dans le temps et dans son contexte",
            // S42-ART-COULEURS (s42) : domaine neuf, second paquet d'arts plastiques (copie
            // conforme du referentiel de nadia-prof, squad/programme/arts-plastiques/
            // competences-cp.md).
            "L'expression des émotions",
            // S40-EXTRACTION (s40) : domaine neuf, sciences et technologie CM1 (copie conforme du
            // referentiel de nadia-prof, squad/programme/sciences-et-technologie/competences-cm1.md).
            "Démarches et langages scientifiques",
            // S44-MUS-VOCABULAIRE (s44) : domaine neuf, premier paquet d'education musicale (copie
            // conforme du referentiel de nadia-prof,
            // squad/programme/education-musicale/competences-cp.md).
            "Échanger, partager et argumenter",
            // S45-MUS-INSTRUMENTS (s45) : domaine neuf, second paquet d'education musicale (copie
            // conforme du referentiel de nadia-prof,
            // squad/programme/education-musicale/competences-cp.md, ligne MUS-CP-05).
            "Écouter, comparer et commenter",
            // S45-EPS-ROLES (s45) : domaine neuf, second lot d'education physique et sportive CM1
            // (copie conforme du referentiel de nadia-prof,
            // squad/programme/education-physique-et-sportive/competences-cm1.md).
            "Conduire et maîtriser un affrontement collectif ou interindividuel",
            // S47-LVE-CULTURE-CP (s47) : domaine neuf, premier paquet de langues vivantes anglais
            // au CP (le domaine combine de cm1-anglais, "Lire et comprendre ; découverte
            // culturelle", est une chaine distincte, deja listee plus haut).
            "Découverte culturelle",
            // competence (descripteur)
            "Calcul mental - additions dans la limite de 10",
            // S37-1 (s37) : competence neuve, MAT-CE1-03 sous-partie "additions".
            "Calcul mental - additions dans la limite de 100",
            // S38-COMPLEMENTS-DIZAINE (s38) : competence neuve, MAT-CE1-03 sous-partie
            // "complements a la dizaine" (ADR-0097, SousPartieDeMathematiquesCe1).
            "Calcul mental - compléments à la dizaine",
            // S38-MOITIES (s38) : competence neuve, MAT-CE1-03 sous-partie "moities" (ADR-0097,
            // SousPartieDeMathematiquesCe1).
            "Calcul mental - moitiés dans la limite de 100",
            "Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif",
            "Calcul mental - soustractions dans la limite de 10",
            // S38-SOUSTRACTIONS (s38) : competence neuve, MAT-CE1-03 sous-partie
            // "soustractions" (ADR-0097).
            "Calcul mental - soustractions dans la limite de 100",
            "Comparer deux nombres entiers jusqu'à 100 (<, >, =)",
            "Compléments à 10",
            "Calcul mental - tables de multiplication (jusqu'à ×10), rappel automatisé",
            "Calculer un périmètre de figures usuelles (carré, rectangle)",
            "Convertir des unités de longueur, masse, contenance dans le système métrique",
            "Lire, écrire, décomposer et comparer les nombres entiers jusqu'au million",
            // S19-5 (ADR-0049) : graphie accentuee, corrigee depuis S16-5.
            "Associer les graphèmes aux phonèmes (voyelles, consonnes simples, puis digraphes ch, ou, on...)",
            "Résoudre des problèmes simples d'addition/soustraction en situation (1 étape)",
            // S17-3 : competence neuve.
            "Utiliser des synonymes et antonymes pour préciser ou nuancer un propos",
            // S17-4 : competence neuve.
            "Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...)",
            // S19-2 : competence neuve.
            "Accorder en genre et en nombre dans le groupe nominal (déterminant/nom/adjectif)",
            // S19-3 : competence neuve.
            "Accorder le verbe avec son sujet (cas fréquents, temps simples : présent, futur, imparfait)",
            // DEC-0048 : competence restauree.
            "Distinguer les principaux types de mots (nom, verbe) dans une phrase très simple",
            // DEC-0050 : competence restauree.
            "Repérer et corriger la majuscule et la ponctuation dans une phrase donnée "
                    + "(majuscule initiale, point final, majuscule au nom propre, point "
                    + "d'interrogation d'une question)",
            // S21-9 : competence neuve.
            "Dénombrer les syllabes orales d'un mot (segmentation syllabique)",
            // S21-10 : competence neuve.
            "Utiliser des mots précis pour désigner des objets, actions, sensations du quotidien",
            // S21-11 : competence neuve.
            "Comparer des longueurs, des masses, des contenances (sans instrument gradué)",
            // S22 (S13-2) : competence neuve.
            "Calcul mental - multiplications et divisions simples (multiples de 10, 100)",
            // S13-3 : competence neuve.
            "Résoudre des problèmes de durées (calcul d'une durée, d'un horaire d'arrivée)",
            // S22 (S17-2) : competence neuve.
            "Comprendre un texte narratif ou documentaire (questions explicites et implicites, "
                    + "inférences simples)",
            // S23-LIA : competence neuve, couverture partielle nommee (LIA-10).
            "Utiliser à bon escient des mots de liaison simples pour structurer un texte court",
            // S23-RAN : competence neuve, moitie "ranger" de la ligne comparer-et-ranger.
            "Ranger des nombres entiers jusqu'à 100 dans l'ordre croissant",
            // S23-ETA : competence neuve.
            "Résoudre des problèmes à deux étapes mobilisant les quatre opérations",
            // S24-NAR : competence neuve.
            "Comprendre un texte narratif très court (questions explicites : qui, où, quoi)",
            // S24-PLU : competence neuve.
            "Reconnaître et utiliser les marques du pluriel les plus fréquentes (-s)",
            // S24-FRA : competence neuve, couverture partielle nommee (FRA-9). Le libelle est celui
            // du referentiel, la moitie "reperage sur une droite numerique" comprise, parce que
            // c'est la ligne entiere que le descripteur du paquet nomme ; ce que le paquet couvre
            // reellement est la moitie "notion", et la mention exacte que nadia-prof exige sur la
            // matrice reste due le jour ou la ligne bougera.
            "Notion de fraction simple (unités fractionnaires, fractions inférieures à 1) et "
                    + "repérage sur une droite numérique",
            // S25-FIG : competence neuve, ligne entiere.
            "Reconnaître et nommer quelques figures planes (carré, rectangle, triangle, cercle)",
            // S25-MON-1 : competence neuve, et la ligne est entiere - nadia-prof ecrit que "le
            // paquet sert reconnaitre par l'image et utiliser par la question". Le libelle est
            // celui du referentiel, mot pour mot.
            "Reconnaître et utiliser la monnaie (pièces/billets simples) dans des problèmes "
                    + "concrets",
            // S25-SUB : competence neuve, ligne entiere. nadia-prof a verifie avant d'arbitrer
            // qu'elle est distincte de "Comprendre un texte narratif ou documentaire", deja verte.
            "Repérer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte",
            // S25-SEM : competence neuve, couverture partielle nommee (SEM-3). Le libelle est
            // celui du referentiel, la moitie "lire l'heure pleine" comprise, parce que c'est la
            // ligne entiere que le descripteur du paquet nomme ; ce que le paquet couvre
            // reellement est la moitie "se reperer dans la journee, la semaine", et la mention
            // exacte que nadia-prof exige sur la matrice reste due le jour ou la ligne bougera.
            "Se repérer dans la journée, la semaine ; lire l'heure pleine sur une horloge",
            // S26-L1 : competence neuve.
            "Localiser une syllabe dans un mot (début / milieu / fin)",
            // S26-L2 : competence neuve.
            "Identifier un son (phonème) dans un mot",
            // S26-L3 et S26-L4 partagent la competence "Associer les graphèmes aux phonèmes..."
            // deja presente ci-dessus (paquet cp-le-bon-son, S19-5) : aucune entree neuve.
            // S26-L5 : competence neuve, premier paquet du domaine "Écriture".
            "Écrire de manière autonome un mot simple en respectant les correspondances "
                    + "graphème/phonème connues",
            // S27-1 : cp-voyelle-du-son partage la competence "Associer les graphèmes aux
            // phonèmes..." deja presente ci-dessus (paquet cp-le-bon-son, S19-5) : aucune entree
            // neuve.
            // S27-2 : trois competences neuves, copiees conformes du referentiel de nadia-prof
            // (ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md, section 4, lignes
            // 1, 3 et 5).
            "Distinguer les êtres vivants des objets et de la matière inerte sur des exemples "
                    + "du quotidien.",
            "Nommer les principales parties du corps humain et les cinq sens.",
            "Reconnaître les états de l'eau (solide, liquide) et les changements d'état "
                    + "observables.",
            // S27-3 : competence neuve, premier paquet de la matiere. Touchait plusieurs lignes du
            // referentiel (S27-3 ne pretendait fermer aucune ligne) - libelle du descripteur du
            // paquet, mot pour mot. Scindee en quatre competences par S35-CONS-4 (DEC-0088,
            // ADR-0081), une par entree issue de la scission, libelle du descripteur de chaque
            // paquet, mot pour mot.
            // S42-HG-ROIS (s42) : les deux competences ci-dessous sont etendues a HG-CM1-05,
            // ligne conjonctive ("nommer et situer dans le temps") qui tombe dans les deux
            // paquets, meme configuration deja verte que HG-CM1-07 (avis de faisabilite de s42
            // de theo-archi, section 5).
            "Se repérer dans le temps : situer un fait dans le temps (grande période, siècle ou "
                    + "millénaire), ou remettre des faits dans l'ordre chronologique (référentiel "
                    + "CM1, lignes HG-CM1-01, HG-CM1-02, HG-CM1-05, HG-CM1-07)",
            "Nommer ce que l'énoncé décrit, en histoire : un peuple ou un mouvement de population, "
                    + "un roi et sa réalisation ou sa situation dans le temps, un événement de la "
                    + "Révolution ou de l'Empire (référentiel CM1, lignes HG-CM1-03, HG-CM1-04, "
                    + "HG-CM1-05, HG-CM1-06, HG-CM1-07, HG-CM1-08)",
            "Nommer ce que l'énoncé décrit, en géographie : un type d'espace et ses activités, une "
                    + "ressource (eau, énergie, aliment) et son acheminement (référentiel CM1, "
                    + "lignes HG-CM1-11, HG-CM1-12, HG-CM1-13)",
            "Lire un document fourni (texte) et en tirer une réponse : date, nature, auteur ou "
                    + "information extraite (référentiel CM1, ligne HG-CM1-14)",
            // S28-3 : competence neuve, premier paquet d'anglais. Libelle du descripteur du
            // paquet, mot pour mot (cm1-anglais.yaml).
            "Ouvre la ligne lire et comprendre des mots et expressions familiers du référentiel "
                    + "Langues vivantes (anglais) CM1, et sert en français la ligne découverte "
                    + "culturelle (lignes LVE-CM1-04 et LVE-CM1-07)",
            // S28-4 : deux competences neuves, calcul pose CM1. Libelle du referentiel, mot pour
            // mot (MAT-CM1-04 et MAT-CM1-05).
            "Effectuer une multiplication posée d'un nombre à 2-3 chiffres par un nombre à 1-2 "
                    + "chiffres",
            "Effectuer une division euclidienne simple (quotient et reste) par un diviseur à 1 "
                    + "chiffre",
            // S29-1 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/questionner-le-monde/competences-cp.md, lignes QLM-CP-02 et
            // QLM-CP-07).
            "Identifier ce qui caractérise un être vivant : il naît, se nourrit, grandit, a des "
                    + "petits, meurt.",
            "Identifier la fonction d'un objet technique du quotidien et nommer ses parties.",
            // S29-4 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cp.md, lignes EMC-CP-06
            // et EMC-CP-02).
            "Connaître les règles de vie de la classe et de l'école et dire à quoi elles servent.",
            "Distinguer ce qui est permis de ce qui est interdit dans les relations entre élèves "
                    + "(moquerie, insulte, violence).",
            // S29-5 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cm1.md, lignes EMC-CM1-11
            // et EMC-CM1-07).
            "Distinguer un fait d'une opinion, et vérifier l'origine d'une information avant de la "
                    + "partager.",
            "Distinguer un droit d'un devoir, et connaître quelques droits et devoirs de l'élève et "
                    + "de l'enfant.",
            // S30-1 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, lignes SCI-CM1-05 et
            // SCI-CM1-17).
            "Identifier un signal et une information dans quelques objets du quotidien (feu "
                    + "tricolore, panneau, alarme, écran).",
            "Décrire un milieu proche : les êtres vivants qui le peuplent et les relations "
                    + "alimentaires qui les relient (chaîne alimentaire).",
            // S39-ENERGIE (s39) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, ligne SCI-CM1-04).
            "Identifier des sources et des formes d'énergie, et un exemple de conversion d'une "
                    + "forme à une autre.",
            // S30-2 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, lignes SCI-CM1-02 et
            // SCI-CM1-12).
            "Distinguer un mélange homogène d'un mélange hétérogène et identifier un moyen de "
                    + "séparer les constituants d'un mélange.",
            "Identifier la fonction d'un objet technique, nommer ses composants et décrire son "
                    + "fonctionnement.",
            // S31-1 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cp.md, lignes
            // EMC-CP-01 et EMC-CP-05).
            "Identifier et nommer les émotions d'autrui dans une situation simple (joie, "
                    + "tristesse, colère, peur).",
            "Employer les formules de politesse et de respect dans les échanges (saluer, "
                    + "remercier, demander, écouter sans couper).",
            // S31-2 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cp.md, lignes
            // EMC-CP-03 et EMC-CP-07).
            "Reconnaître une situation de moquerie répétée ou d'exclusion et savoir à "
                    + "quel adulte en parler.",
            "Distinguer une règle qui vaut pour tous d'un simple souhait, et reconnaître "
                    + "qu'une loi s'impose à tous.",
            // S31-3 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, lignes SCI-CM1-01 et
            // SCI-CM1-07).
            "Décrire les états de la matière et les changements d'état (solide, liquide, gaz ; "
                    + "fusion, solidification, vaporisation, condensation).",
            "Identifier les besoins des êtres vivants pour se nourrir et distinguer les régimes "
                    + "alimentaires (herbivore, carnivore, omnivore).",
            // S31-4 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, lignes SCI-CM1-11 et
            // SCI-CM1-08).
            "Identifier les principales familles de matériaux et choisir un matériau d'après une "
                    + "propriété (résistance, isolation, souplesse, imperméabilité).",
            "Ordonner les étapes du développement d'un être vivant (naissance, croissance, "
                    + "reproduction, vieillissement, mort).",
            // S32-5 : deux competences neuves, copiees conformes du referentiel de nadia-prof
            // (squad/programme/education-physique-et-sportive/competences-cp.md, lignes
            // EPS-CP-04 et EPS-CP-08).
            "Connaître et respecter les règles de sécurité propres à l'activité et au lieu "
                    + "de pratique.",
            "Connaître et respecter les règles d'un jeu collectif simple.",
            // S34-EPS-CORPS-EFFORT : competence neuve, second lot d'education physique et
            // sportive CP - sert deux lignes du referentiel de nadia-prof (EPS-CP-10, EPS-CP-11),
            // meme motif que S24-SAI-2 et cm1-anglais pour une entree qui en sert plusieurs.
            "Sert la ligne reconnaître les effets de l'effort sur son corps et la ligne connaître "
                    + "quelques règles d'échauffement, d'hydratation et de récupération autour de "
                    + "l'effort (lignes EPS-CP-10 et EPS-CP-11)",
            // S33-PAL2-1 (s34) : une competence neuve, premier lot de designation, copiee
            // conforme du referentiel (squad/programme/questionner-le-monde/competences-cp.md,
            // QLM-CP-14).
            "Situer des événements les uns par rapport aux autres sur une frise chronologique "
                    + "simple (avant, après, en même temps)",
            // S35-QLM11-PLANCLASSE (s35) : competence neuve, second lot de designation, copiee
            // conforme du referentiel (squad/programme/questionner-le-monde/competences-cp.md,
            // QLM-CP-11), premiere livraison reelle de cette ligne.
            "Situer un lieu ou un objet sur un plan simple de la classe, de l'école ou du "
                    + "quartier.",
            // S39-MONNAIE (s39) : competence neuve, MAT-CE1-13 sous-partie "monnaie" (ADR-0098,
            // SousPartieDeMathematiquesCe1.MONNAIE).
            "Utiliser la monnaie en euros et en centimes",
            // S39-OUTILS (s39) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/arts-plastiques/competences-cp.md, ligne ART-CP-11).
            "Nommer quelques outils, supports et gestes du travail plastique (pinceau, craie, "
                    + "argile, esquisse).",
            // S39-DEN (s39) : competence neuve, libelle du referentiel (squad/programme/
            // mathematiques/competences-cp.md, MAT-CP-01). Couverture partielle nommee (DEN-2) :
            // le paquet ne sert pas "dénombrer".
            "Dénombrer, lire, écrire et décomposer les nombres jusqu'à 100",
            // S39-LONGUEUR (s39) : competence neuve, sous-partie allouee dans
            // SousPartieDeMathematiquesCe1 (ADR-0098). Couverture partielle nommee : le paquet ne
            // sert pas "mesurer avec une règle graduée".
            "Mesurer et comparer des longueurs en centimètres et en mètres",
            // S39-SOLIDES (s39) : competence neuve, sous-partie allouee dans
            // SousPartieDeMathematiquesCe1 (ADR-0098).
            "Reconnaître et nommer des solides usuels",
            // S40-HDA (s40) : competence neuve, libelle du descripteur du paquet, mot pour mot
            // (cm1-histoire-des-arts-relier-contexte.yaml, ligne HDA-CM1-05).
            "Relier les caractéristiques d'une œuvre d'art à des usages et au contexte historique "
                    + "et culturel de sa création (référentiel CM1, ligne HDA-CM1-05)",
            // S40-NUTRITION (s40) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/sciences-et-technologie/competences-cm1.md, ligne SCI-CM1-09).
            "Nommer les grandes fonctions de nutrition du corps humain (digestion, respiration, "
                    + "circulation) et l'organe principal de chacune.",
            // S40-REGLES (s40) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne EMC-CM1-04).
            "Connaître les règles de la vie collective à l'école et dire à quoi elles servent.",
            // S40-LOI (s40) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne EMC-CM1-08).
            "Distinguer une règle d'une loi, et identifier ce que la loi autorise, interdit et "
                    + "protège.",
            // S40-DEVISE (s40) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cp.md, ligne EMC-CP-09).
            "Connaître la devise de la République et donner un exemple concret de liberté, "
                    + "d'égalité et de fraternité.",
            // S40-EXTRACTION (s40) : competence neuve, libelle du descripteur du paquet, mot pour
            // mot (cm1-sciences-extraire-information.yaml, ligne SCI-CM1-20).
            "Extraire une information explicitement présente dans un document scientifique (texte "
                    + "ou tableau) et la restituer (référentiel CM1, ligne SCI-CM1-20)",
            // S41-EMC-ROLES (s41) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/enseignement-moral-et-civique/competences-cp.md, ligne EMC-CP-13).
            "Identifier quelques rôles et services de l'école et de la commune (directeur, mairie, "
                    + "pompiers, médecin).",
            // S41-SCI-CLASSER (s41) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-06).
            "Classer des êtres vivants d'après un caractère commun observable, et repérer la "
                    + "diversité des espèces d'un milieu.",
            // S41-QLM-HYGIENE (s41) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/questionner-le-monde/competences-cp.md, ligne QLM-CP-04).
            "Connaître quelques règles d'hygiène de vie (sommeil, alimentation, hygiène du corps) "
                    + "et leur effet sur la santé.",
            // S41-QLM-SAISONS (s41) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/questionner-le-monde/competences-cp.md, ligne QLM-CP-13).
            "Nommer et ordonner les mois de l'année et les saisons.",
            // S41-EMC-SYMBOLES (s41) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/enseignement-moral-et-civique/competences-cp.md, ligne
            // EMC-CP-08).
            "Reconnaître et nommer les symboles de la République française (drapeau tricolore, "
                    + "Marianne, La Marseillaise, la fête nationale).",
            // S41-EMC-DROITS (s41) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/enseignement-moral-et-civique/competences-cp.md, ligne
            // EMC-CP-10).
            "Identifier les principaux droits de l'enfant (être protégé, être soigné, aller à "
                    + "l'école, jouer).",
            // S42-MAT-TEXTUEL (s42) : competence neuve, libelle entier du referentiel de
            // nadia-prof (squad/programme/mathematiques/competences-cp.md, ligne MAT-CP-10) ;
            // couverture partielle nommee, ce paquet ne sert que la moitie "decrire un
            // deplacement".
            "Se repérer et décrire des déplacements sur un quadrillage simple",
            // S42-ART-COULEURS (s42) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/arts-plastiques/competences-cp.md, ligne ART-CP-05).
            "Reconnaître et nommer les couleurs, distinguer les couleurs primaires de celles "
                    + "obtenues par mélange.",
            // S42-EMC-SYMBOLES-CM1 (s42) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-05).
            "Reconnaître les symboles de la République française et dire ce qu'ils représentent "
                    + "(drapeau, devise, Marianne, hymne, fête nationale).",
            // S42-QLM-MATIERE (s42) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/questionner-le-monde/competences-cp.md, ligne
            // QLM-CP-06).
            "Identifier de quelle matière un objet est fait et trier des matériaux selon une "
                    + "propriété simple.",
            // S43-MAT-DUREE (s43) : competence neuve, libelle du referentiel copie mot pour mot
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-12), sous-partie
            // allouee dans SousPartieDeMathematiquesCe1 (ADR-0098).
            "Calculer une durée simple en heures entières ou en demi-heures",
            // S43-QLM-NUMERIQUE (s43) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/questionner-le-monde/competences-cp.md, ligne
            // QLM-CP-08).
            "Reconnaître les objets numériques du quotidien et quelques règles simples de leur "
                    + "usage.",
            // S43-EPS-EFFETS-ACTIVITE (s43) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/education-physique-et-sportive/competences-cm1.md, ligne
            // EPS-CM1-10).
            "Connaître les effets de l'activité physique sur le corps (échauffement, "
                    + "essoufflement, récupération, hydratation) et les règles de sécurité qui "
                    + "l'entourent.",
            // S44-MAT-DIVISION (s44) : competence neuve, libelle du referentiel
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-18) sans la
            // parenthese finale, sous-partie allouee dans SousPartieDeMathematiquesCe1 (ADR-0098).
            "Comprendre le sens de la division en résolvant des problèmes de partage et de "
                    + "groupement",
            // S44-MUS-VOCABULAIRE (s44) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/education-musicale/competences-cp.md, ligne MUS-CP-11).
            "Nommer quelques repères du vocabulaire musical rencontré (refrain, couplet, "
                    + "pulsation, silence).",
            // S44-SCI-OBJET-TECHNIQUE (s44) : competence neuve, libelle du referentiel copie mot
            // pour mot (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-13).
            "Repérer l'évolution d'un objet technique au cours du temps et ce qui a changé dans sa "
                    + "fabrication ou dans son usage.",
            // S45-MUS-INSTRUMENTS (s45) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/education-musicale/competences-cp.md, ligne MUS-CP-05).
            "Identifier quelques instruments de musique et les familles auxquelles ils "
                    + "appartiennent (cordes, vents, percussions).",
            // S45-SCI-NUMERIQUE (s45) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/sciences-et-technologie/competences-cm1.md, ligne SCI-CM1-14).
            "Connaître quelques règles d'usage d'un environnement numérique (identification, "
                    + "courriel, recherche d'une information, protection de ses données).",
            // S45-MAT-ADDITION (s45) : competence neuve, libelle du referentiel
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-04), sous-partie
            // allouee dans SousPartieDeMathematiquesCe1 (ADR-0098).
            "Poser et effectuer une addition avec retenue (nombres à deux ou trois chiffres)",
            // S45-EPS-ROLES (s45) : competence neuve, copiee conforme du referentiel de nadia-prof
            // (squad/programme/education-physique-et-sportive/competences-cm1.md, ligne
            // EPS-CM1-09).
            "Tenir les rôles d'arbitre, d'observateur ou de marqueur dans un jeu, et en appliquer "
                    + "les règles.",
            // S46-EMC-INSTITUTIONS-CM1 (s46) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-09).
            "Identifier le rôle de quelques institutions proches (mairie, conseil municipal, "
                    + "école) et à qui s'adresser selon la situation.",
            // S46-SCI-PHENOMENES-CM1 (s46) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-16).
            "Identifier quelques phénomènes géologiques et météorologiques (séisme, éruption, "
                    + "tempête, inondation) et les risques associés.",
            // S51-SCI-CORPS-CM1 (s51) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-10).
            "Relier l'alimentation, l'activité physique, le sommeil et l'hygiène au bon "
                    + "fonctionnement du corps.",
            // S54-BR-SCI-CM1-18 (s54) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-18).
            "Identifier un effet de l'activité humaine sur l'environnement et un geste de "
                    + "préservation des ressources.",
            // S54-BR-SCI-CM1-15 (s54) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/sciences-et-technologie/competences-cm1.md, ligne
            // SCI-CM1-15).
            "Situer la Terre dans le système solaire et relier les mouvements de la Terre au "
                    + "jour, à la nuit et aux saisons.",
            // S54-BR-ART-CP-10 (s54) : competence du paquet = libelle du referentiel ART-CP-10,
            // parenthese retiree (arbitrage-nadia-c.md, section 5).
            "Reconnaître quelques œuvres déjà rencontrées et les rapprocher les unes des autres.",
            // S54-BR-EMC-CM1-02 (s54) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-02).
            "Reconnaître ce qui porte atteinte à la personne d'autrui (moquerie, insulte, mise à "
                    + "l'écart, harcèlement) et savoir à qui s'adresser.",
            // S55-BR-EMC-CM1-03 (s55) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-03).
            "Identifier ce qu'interdit le respect d'autrui dans ses différences (apparence, "
                    + "origine, convictions, situation de handicap).",
            // S55-BR-EMC-CM1-06 (s55) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-06).
            "Comprendre le sens des principes de la République : liberté, égalité, fraternité, "
                    + "laïcité, refus des discriminations.",
            // S46-EMC-EMOTION-CM1 (s46) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-01).
            "Identifier et nommer l'émotion d'une personne dans une situation donnée, et "
                    + "distinguer ce que l'on ressent de ce que l'on fait.",
            // S46-SCI-MOUVEMENT-CM1 (s46) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/sciences-et-technologie/competences-cm1.md, ligne SCI-CM1-03).
            "Décrire le mouvement d'un objet par sa trajectoire et par sa vitesse, par rapport à "
                    + "un point de repère donné.",
            // S46-MAT-SOUSTRACTION (s46) : competence neuve, libelle du referentiel
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-05). Complete ici,
            // voir le motif pres de l'id.
            "Poser et effectuer une soustraction (nombres à deux ou trois chiffres)",
            // S47-LVE-CULTURE-CP (s47) : competence neuve, libelle du referentiel copie mot pour
            // mot (squad/programme/langues-vivantes/competences-cp.md, ligne LVE-CP-06).
            "Découvrir quelques aspects culturels des pays de la langue étudiée (fêtes, lieux, vie "
                    + "quotidienne).",
            // S47-EMC-VOTE-CM1 (s47) : competence neuve, libelle du referentiel copie mot pour mot
            // (squad/programme/enseignement-moral-et-civique/competences-cm1.md, ligne
            // EMC-CM1-10).
            "Comprendre le sens du vote et de la représentation (délégué de classe, élection, "
                    + "suffrage universel).",
            // S47-QLM-AUTREFOIS (s47) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/questionner-le-monde/competences-cp.md, ligne
            // QLM-CP-15).
            "Comparer des modes de vie d'autrefois et d'aujourd'hui à partir d'objets et "
                    + "d'images du quotidien.",
            // S47-EMC-ALERTE-CP (s47) : competence neuve, copiee conforme du referentiel de
            // nadia-prof (squad/programme/enseignement-moral-et-civique/competences-cp.md, ligne
            // EMC-CP-14).
            "Connaître les gestes d'alerte les plus simples et savoir donner l'alerte "
                    + "(prévenir un adulte, numéro d'urgence).",
            // S47-MAT-TABLES (s47) : competence neuve, MAT-CE1-07, libelle fixe par la story
            // (docs/backlog/backlog.md, section "Sprint 47") et par la fiche de conformite
            // (squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md).
            "Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4",
            // S48-MAT-PROBLEMES (s48) : competence neuve, MAT-CE1-08, libelle du referentiel
            // copie mot pour mot (squad/programme/mathematiques/competences-ce1.md, ligne
            // MAT-CE1-08) sans le point final.
            "Résoudre des problèmes d'addition, de soustraction et de multiplication à une ou deux étapes",
            // S49-FRA-GRAPHEMES (s49) : competence neuve, FRA-CE1-01, libelle du referentiel
            // copie mot pour mot (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-01).
            "Lire des mots comportant des graphèmes complexes (ain/ein, eil/eille, ill, gn, oin, "
                    + "ien...) et les associer au son qu'ils transcrivent.",
            // S50-FRA-VALEUR-LETTRE (s50) : competence neuve, FRA-CE1-02. Le libelle du
            // referentiel (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-02) nomme
            // "c/ç" ; "ç" seul n'est pas servi ici, parce qu'une lettre isolee en texte accentue
            // sans forme ASCII sœur fait rougir AucunMotServiNeSEcarteDeSonAccentTest ("ç" n'est
            // pas une forme reconnue du lexique, alors que "c" l'est) - question non bloquante
            // routee a camille-po/nadia-prof (ce1-valeur-lettre.yaml, en-tete).
            "Déterminer la valeur d'une lettre selon les lettres qui l'entourent (c, g/ge/gu, "
                    + "s/ss entre deux voyelles).",
            // S50-FRA-HOMOPHONES (s50) : competence neuve, FRA-CE1-16, libelle du referentiel
            // copie mot pour mot (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-16),
            // sans les guillemets inverses.
            "Distinguer les homophones grammaticaux a/à et et/est.",
            // S51-FRA-TEMPS (s51) : competence neuve, FRA-CE1-20, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-20) sans les guillemets
            // inverses.
            "Situer une phrase dans le temps (passé, présent, futur) à partir de son verbe et des "
                    + "mots qui indiquent le moment (hier, aujourd'hui, demain).",
            // S51-FRA-ALPHABETIQUE (s51) : competence neuve, FRA-CE1-23, libelle du referentiel
            // copie (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-23).
            "Ranger des mots dans l'ordre alphabétique (première lettre, puis deuxième lettre).",
            // S51-FRA-GRAPHIE (s51) : competence neuve, FRA-CE1-22, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-22), sans les guillemets
            // inverses et avec l'espace insecable devant le point-virgule (ADR-0067).
            "Choisir la graphie d'un son selon la lettre qui suit (m devant m, b, p ; c avec ou sans "
                    + "cédille ; g, ge, gu).",
            // S52-FRA-CATEGORIES (s52) : competence neuve, FRA-CE1-26, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-26) sans les guillemets
            // inverses.
            "Classer des mots par catégorie et trouver le mot qui les désigne tous (fruit pour "
                    + "pomme, poire, cerise).",
            // S52-FRA-PRESENT (s52) : competence neuve, FRA-CE1-17, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-17) sans les guillemets
            // inverses.
            "Conjuguer au présent de l'indicatif être, avoir, les verbes du premier groupe et "
                    + "quelques verbes fréquents (aller, faire, dire, venir).",
            // S54-BR-FRA-CE1-18 (s54) : competence neuve, FRA-CE1-18, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-18) sans les guillemets
            // inverses.
            "Conjuguer au futur être, avoir et les verbes du premier groupe.",
            // S54-BR-FRA-CE1-19 (s54) : competence neuve, FRA-CE1-19, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-19) sans les guillemets
            // inverses.
            "Conjuguer au passé composé être, avoir et les verbes du premier groupe, avec "
                    + "l'auxiliaire avoir.",
            // S55-BR-FRA-CE1-L1 (s55) : competence neuve, FRA-CE1-14, libelle du descripteur du paquet
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-14).
            "Accorder le verbe avec son sujet au présent, quand le sujet est un pronom personnel "
                    + "ou un nom placé juste avant le verbe.",
            // S55-BR-FRA-CE1-L2 (s55) : competence neuve, FRA-CE1-12, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-12).
            "Identifier la classe d'un mot dans une phrase : nom commun, nom propre, verbe, "
                    + "déterminant, adjectif.",
            // S56-BR-FRA-CE1-13 (s56) : competence neuve, FRA-CE1-13, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-13).
            "Identifier le verbe d'une phrase et son sujet (un nom, un groupe nominal court ou un "
                    + "pronom personnel).",
            // S57-BR-FRA-CE1-10 (s57) : competence neuve, FRA-CE1-10, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-10).
            "Reconnaître une phrase (majuscule, point) et identifier son type (déclarative, "
                    + "interrogative, exclamative) par sa ponctuation.",
            // S57-BR-FRA-CE1-15 (s57) : competence neuve, FRA-CE1-15, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-15).
            "Accorder en nombre et en genre dans le groupe nominal (déterminant, nom, adjectif)\u00A0:"
                    + " pluriel en -s et en -x (-eau, -au, -eu), féminin en -e.",
            // S57-BR-FRA-CE1-04 (s57) : competence neuve, FRA-CE1-04, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-04).
            "Comprendre un texte narratif court lu seul : personnages, lieu, moment, ordre "
                    + "chronologique des événements.",
            // S57-BR-FRA-CE1-05 (s57) : competence neuve, FRA-CE1-05, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-05).
            "Identifier à qui renvoie un pronom personnel (il, elle, ils, elles) ou un substitut "
                    + "simple dans un texte court.",
            // S57-BR-FRA-CE1-06 (s57) : competence neuve, FRA-CE1-06, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-06).
            "Faire une inférence simple à partir d'un texte court (une information non écrite\u00A0: un "
                    + "sentiment, un lieu, une cause).",
            // S57-BR-FRA-CE1-07 (s57) : competence neuve, FRA-CE1-07, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-07).
            "Comprendre un texte documentaire court ou une consigne écrite, et y prélever une "
                    + "information.",
            // S57-BR-FRA-CE1-11 (s57) : competence neuve, FRA-CE1-11, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-11).
            "Passer d'une phrase affirmative à une phrase négative (ne... pas) et inversement.",
            // S57-BR-MAT-CE1-01 (s57) : competence neuve, MAT-CE1-01, libelle du referentiel copie
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-01). Couverture
            // partielle nommee (NUM-CE1-2) : le paquet ne sert pas "dénombrer".
            "Dénombrer, lire, écrire et décomposer les nombres jusqu'à 1000 (unités, dizaines, "
                    + "centaines)",
            // S57-BR-EMC-CE1-01 (s57) : competence neuve, EMC-CE1-01, libelle du referentiel copie
            // (squad/programme/enseignement-moral-et-civique/competences-ce1.md, ligne EMC-CE1-01).
            "Respecter les biens personnels et collectifs : reconnaître ce qui appartient à tous "
                    + "(matériel de la classe, livres de la bibliothèque, jeux de la cour) et le geste "
                    + "qui le protège.",
            // S52-FRA-SYNONYMES-CONTRAIRES (s52) : competence neuve, FRA-CE1-25, libelle du
            // referentiel copie (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-25).
            "Trouver un synonyme ou un contraire d'un mot fréquent.",
            // S57-BR-FRA-CE1-24 (s57) : competence neuve, FRA-CE1-24, libelle du referentiel copie
            // (squad/programme/francais/competences-ce1.md, ligne FRA-CE1-24).
            "Regrouper les mots d'une même famille et repérer un préfixe ou un suffixe fréquent "
                    + "(re-, dé-, -eur...).",
            // S50-ART (s50) : competence neuve, ART-CP-09. Le libelle du referentiel
            // (squad/programme/arts-plastiques/competences-cp.md, ligne ART-CP-09) nomme
            // "couleurs" ; aucune couleur n'est servie par ce paquet (ART-1, arbitrage prealable
            // de nadia-prof du 2026-10-04, Q4) et ce champ, lu par l'enfant, ne promet pas une
            // competence que le paquet ne sert pas - meme motif que la correction du 2026-09-02 sur
            // "Calcul CP" citee plus haut. Point signale, non tranche par moi seul.
            // Second passage (DEC-0109 section 11, fiche de nadia-prof, motif 4 du refus du premier
            // passage) : "formes" retiree a son tour, aucun item ne la servant plus apres la
            // reecriture d'oeuvre-quatre-saisons (motif 2).
            "Décrire une œuvre : dire ce qu'on y voit (sujet et matériaux).",
            // S55-ART-CP-10-PAQUET (s56) : competence neuve, ART-CP-10, libelle du referentiel
            // (squad/programme/arts-plastiques/competences-cp.md, ligne ART-CP-10).
            "Reconnaître quelques œuvres déjà rencontrées et les rapprocher les unes des autres.",
            // S51-MAT-COMPARER-RANGER (s52) : competence neuve, MAT-CE1-02. Libelle = la mention
            // d'ORD-CE1-5 (DEC-0111 section 5.4, point 2), accentuee, avec l'espace insecable devant
            // le point-virgule (ADR-0067).
            "comparer, ranger et encadrer seuls ; le verbe 'placer sur une ligne graduée' "
                    + "n'est servi par aucun jeu et ne peut pas l'être par du texte",
            // S51-MAT-MULTIPLICATION (s52) : competence neuve, MAT-CE1-06, libelle du referentiel
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-06) sans le point
            // final.
            "Comprendre le sens de la multiplication (addition réitérée, disposition en lignes et "
                    + "en colonnes)",
            // S51-MAT-SYMETRIE (s52) : competence neuve, MAT-CE1-17, libelle du referentiel
            // (squad/programme/mathematiques/competences-ce1.md, ligne MAT-CE1-17) sans le point
            // final.
            "Reconnaître un axe de symétrie et compléter une figure par symétrie sur quadrillage",
            // Hors sprint accueil, P3 (ADR-0106) : les libelles courts de la donnee de matiere, lus par
            // l'enfant sur une tuile, arbitres par nadia-prof (APM-1, N1). Seuls ceux qui different de la
            // matiere ou d'une chaine deja inventoriee entrent ici ; les autres y figurent deja.
            "Maths",
            "Sciences",
            "Vivre ensemble",
            "Musique",
            "Sport",
            // Hors sprint accueil, P3 (ADR-0106) : noms de fichier de pictogramme, jamais lus par
            // l'enfant (rendus en img alt vide a cote du libelle) ; arbitres par nadia-prof (APM-2, N2),
            // octet pour octet, par LePictogrammeDeMatiereEstUnFichierArbitreTest.
            "francais.svg",
            "mathematiques.svg",
            "questionner-le-monde.svg",
            "sciences.svg",
            "histoire-geographie.svg",
            "emc.svg",
            "langues-vivantes.svg",
            "arts-plastiques.svg",
            "education-musicale.svg",
            "histoire-des-arts.svg",
            "eps.svg",
            // Hors sprint accueil, P3 (ADR-0106) : teintes de fond et de contour de la donnee de
            // matiere, jamais lues par l'enfant : elles n'atteignent le HTML que par deux proprietes
            // personnalisees (APM-5, APM-6, N6).
            "#cfe3f6",
            "#f4e3a1",
            "#d4ecc0",
            "#bfe8e4",
            "#e8d6bf",
            "#f6cde0",
            "#f8d3c2",
            "#e2d4f3",
            "#d2d6f6",
            "#f0baf3",
            "#fbdcb4",
            "#2f6c9f",
            "#7d6208",
            "#4a7a1c",
            "#1f7770",
            "#7a5230",
            "#a03a6b",
            "#a5472a",
            "#6e4ba3",
            "#4a50a8",
            "#85188b",
            "#a35a0b",
            // Hors sprint accueil, P3 (ADR-0106) : segments d'adresse de la donnee de matiere, jamais
            // lus par l'enfant, ecrits a cote de chaque ligne et jamais calcules depuis la matiere
            // (ADR-0049 point 4, ADR-0106 decision 5).
            "francais",
            "maths",
            "questionner-le-monde",
            "sciences",
            "histoire-geographie",
            "vivre-ensemble",
            "anglais",
            "arts-plastiques",
            "musique",
            "histoire-des-arts",
            "sport");

    /** Une chaine exposee par le port entrant, avec l'origine qui l'a produite (pour le message). */
    private record ChaineExposee(String origine, String chemin, String valeur) {
    }

    /**
     * ADR-0015 : descend dans {@code valeur} tant que sa classe reelle est un record - jamais la
     * classe statique declaree - et collecte chaque composante {@link String} rencontree. S'arrete
     * sur toute classe qui n'est pas un record (en particulier les implementations de {@code
     * ContentSource}, qui n'en sont pas) : c'est cette propriete structurelle qui trace la frontiere
     * avec le texte de paquet, jamais une liste ecrite ici.
     */
    private static void collecter(String origine, String chemin, Object valeur, List<ChaineExposee> resultat) {
        if (valeur == null) {
            return;
        }
        if (valeur instanceof String texte) {
            // L'exemption est une propriete de la valeur, pas de son chemin : "CP" n'a pas plus de
            // lettre accentuable qu'il vienne de GameDefinition.descripteur().niveau() ou de
            // niveauxDuCatalogue(). La filtrer ici, au seul point ou une chaine entre dans le
            // resultat, l'applique uniformement aux deux, sans deux mecanismes a maintenir en accord.
            if (!NIVEAUX_EXEMPTES_CAR_SANS_LETTRE_ACCENTUABLE.contains(texte)) {
                resultat.add(new ChaineExposee(origine, chemin, texte));
            }
            return;
        }
        if (valeur instanceof List<?> liste) {
            for (int index = 0; index < liste.size(); index++) {
                collecter(origine, chemin + "[" + index + "]", liste.get(index), resultat);
            }
            return;
        }
        Class<?> type = valeur.getClass();
        if (!type.isRecord()) {
            return;
        }
        for (RecordComponent composante : type.getRecordComponents()) {
            try {
                Object valeurDeLaComposante = composante.getAccessor().invoke(valeur);
                String cheminEnfant = chemin.isEmpty()
                        ? composante.getName()
                        : chemin + "." + composante.getName();
                collecter(origine, cheminEnfant, valeurDeLaComposante, resultat);
            } catch (IllegalAccessException | InvocationTargetException echec) {
                throw new IllegalStateException(echec);
            }
        }
    }

    /**
     * Le perimetre derive : chaque chaine exposee par {@link PartieUseCase#jeuxDisponibles()} (donc
     * par {@link GameDefinition} et son descripteur), par {@link
     * PartieUseCase#niveauxDuCatalogue()} et, depuis ADR-0105, par les groupes de {@link
     * PartieUseCase#matieresDuNiveau(String)}, hors niveaux exemptes.
     */
    private static List<ChaineExposee> chainesExposeesParLePortEntrant(List<GameDefinition> jeux,
                                                                        List<String> niveaux) {
        return chainesExposeesParLePortEntrant(jeux, niveaux, List.of());
    }

    private static List<ChaineExposee> chainesExposeesParLePortEntrant(List<GameDefinition> jeux,
                                                                        List<String> niveaux,
                                                                        List<GroupeDeJeux> groupes) {
        List<ChaineExposee> chaines = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            collecter(jeu.id(), "", jeu, chaines);
        }
        for (GroupeDeJeux groupe : groupes) {
            collecter("matieresDuNiveau()", "", groupe, chaines);
        }
        for (String niveau : niveaux) {
            collecter("niveauxDuCatalogue()", "", niveau, chaines);
        }
        return chaines;
    }

    /**
     * Critere 1 (reecrit par theo-archi) : toute chaine exposee par le port entrant a une vue est
     * dans l'inventaire, avec son motif ; une chaine hors inventaire fait echouer la construction en
     * se nommant. Une entree exemptee (niveau sans lettre accentuable) n'a pas a y figurer.
     */
    static void verifierQueToutePorteEstInventoriee(List<GameDefinition> jeux, List<String> niveaux) {
        verifierQueToutePorteEstInventoriee(jeux, niveaux, List.of());
    }

    static void verifierQueToutePorteEstInventoriee(List<GameDefinition> jeux, List<String> niveaux,
                                                    List<GroupeDeJeux> groupes) {
        List<ChaineExposee> chaines = chainesExposeesParLePortEntrant(jeux, niveaux, groupes);
        assertThat(chaines)
                .as("S16-5 : cette clause ne prouve rien si le port entrant n'expose aucune chaine")
                .isNotEmpty();

        for (ChaineExposee chaine : chaines) {
            assertThat(CHAINES_EXPOSEES_PAR_LE_PORT_ENTRANT)
                    .as("S16-5, ADR-0015 et ADR-0039 : %s (chemin \"%s\") expose \"%s\", une chaine "
                            + "que l'inventaire de ce garde-fou ne porte pas. Une chaine exposee par le "
                            + "port entrant a une vue ne change pas en silence : soit nadia-prof l'a "
                            + "arbitree et l'inventaire s'etend dans le meme commit, soit cette chaine "
                            + "n'aurait pas du entrer",
                            chaine.origine(), chaine.chemin(), chaine.valeur())
                    .contains(chaine.valeur());
        }
    }

    @Test
    void touteChaineExposeeParLeCatalogueReelEstInventoriee() {
        List<GameDefinition> jeuxLus = new ArrayList<>(partie.jeuxDisponibles());
        List<GroupeDeJeux> groupesLus = new ArrayList<>();
        for (String niveau : partie.niveauxDuCatalogue()) {
            jeuxLus.addAll(partie.jeuxDuNiveau(niveau));
            List<GroupeDeJeux> groupes = partie.matieresDuNiveau(niveau);
            groupesLus.addAll(groupes);
            groupes.forEach(groupe -> partie.groupeDeLaMatiere(niveau, groupe.matiere().segment())
                    .ifPresent(resolu -> {
                        groupesLus.add(resolu);
                        jeuxLus.addAll(resolu.jeux());
                    }));
        }
        assertThat(groupesLus)
                .as("ADR-0105 : cette lecture ne prouve rien si l'accueil ne regroupe aucun jeu")
                .isNotEmpty();
        verifierQueToutePorteEstInventoriee(jeuxLus, partie.niveauxDuCatalogue(), groupesLus);
    }

    /**
     * ADR-0105, regle 1 : les methodes du port entrant que cet inventaire lit. Le test ci-dessus les
     * appelle toutes les cinq ; cette liste ne dispense de rien, elle est comparee par egalite a
     * celles que le port declare.
     */
    private static final Set<String> METHODES_DU_PORT_LUES_PAR_L_INVENTAIRE = Set.of(
            "jeuxDisponibles", "niveauxDuCatalogue", "jeuxDuNiveau", "matieresDuNiveau",
            "groupeDeLaMatiere");

    /**
     * ADR-0105, regle 1 : les methodes d'un port entrant qui parlent a une vue, c'est-a-dire toutes
     * sauf celles qui rendent une session de jeu. Le texte d'une session est du texte de paquet,
     * juge dans {@code adapter-content} : c'est la frontiere que la Javadoc de cette classe trace
     * deja entre un record et un {@code ContentSource}, lue ici sur le type de retour.
     */
    static Set<String> methodesQuiParlentAUneVue(Class<?> port) {
        return Arrays.stream(port.getMethods())
                .filter(methode -> !GameSession.class.isAssignableFrom(methode.getReturnType()))
                .map(Method::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static void verifierQueChaqueMethodeDuPortEstLue(Class<?> port) {
        assertThat(methodesQuiParlentAUneVue(port))
                .as("ADR-0105 : une methode du port entrant qui parle a une vue expose des chaines "
                        + "qu'un gabarit rend sous th:text. Cet inventaire ne les voit que s'il "
                        + "l'appelle : appelle-la dans touteChaineExposeeParLeCatalogueReelEstInventoriee "
                        + "et ajoute son nom a METHODES_DU_PORT_LUES_PAR_L_INVENTAIRE dans le meme commit")
                .isEqualTo(new TreeSet<>(METHODES_DU_PORT_LUES_PAR_L_INVENTAIRE));
    }

    @Test
    void chaqueMethodeDuPortEntrantQuiParleAUneVueEstLueParCetInventaire() {
        verifierQueChaqueMethodeDuPortEstLue(PartieUseCase.class);
    }

    /**
     * Le port tel que le chantier de l'accueil en deux vues le ferait grandir : une methode neuve qui
     * rend les matieres d'un niveau. Avant ADR-0105, ses chaines - les libelles de matiere que
     * l'enfant lit sur une tuile - passaient sous th:text sans entrer dans cet inventaire.
     */
    interface PortQuiRendLesMatieresDUnNiveau extends PartieUseCase {
        List<String> libellesDeMatiereDuNiveau(String niveau);
    }

    @Test
    void uneMethodeNeuveDuPortQueLInventaireNeLitPasFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueMethodeDuPortEstLue(PortQuiRendLesMatieresDUnNiveau.class))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0105")
                .hasMessageContaining("libellesDeMatiereDuNiveau");
    }

    /**
     * Le versant qui empeche l'inventaire de pourrir (meme lecon qu'ADR-0039 pour
     * TexteAccentueDesGabaritsTest) : une entree que le catalogue reel ne produit plus laisserait
     * croire une chaine gardee alors qu'elle a disparu.
     */
    @Test
    void chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel() {
        List<GroupeDeJeux> groupes = partie.niveauxDuCatalogue().stream()
                .flatMap(niveau -> partie.matieresDuNiveau(niveau).stream()).toList();
        List<String> valeursExposees = chainesExposeesParLePortEntrant(
                partie.jeuxDisponibles(), partie.niveauxDuCatalogue(), groupes)
                .stream().map(ChaineExposee::valeur).toList();

        assertThat(valeursExposees)
                .as("S16-5 : une entree de l'inventaire ne correspond plus a aucune chaine exposee "
                        + "par le catalogue reel. Si la chaine a disparu, l'entree part dans le meme "
                        + "commit ; un inventaire plus large que ce qu'il garde n'est pas un inventaire")
                .containsAll(CHAINES_EXPOSEES_PAR_LE_PORT_ENTRANT);
    }

    /**
     * Critere 2 (theo-archi : "le mieux ecrit des vingt-et-un criteres du sprint") : "CP" et "CM1"
     * restent verts sans figurer dans l'inventaire, exemption datee, motivee et bornee ci-dessus.
     */
    @Test
    void lesNiveauxSansLettreAccentuableRestentVertsSansFigurerDansLInventaire() {
        assertThat(CHAINES_EXPOSEES_PAR_LE_PORT_ENTRANT)
                .as("l'exemption ne dispense de preuve que pour \"CP\" et \"CM1\" : ils ne doivent "
                        + "pas polluer l'inventaire qu'ils exemptent")
                .doesNotContainAnyElementsOf(NIVEAUX_EXEMPTES_CAR_SANS_LETTRE_ACCENTUABLE);

        verifierQueToutePorteEstInventoriee(partie.jeuxDisponibles(), List.copyOf(
                NIVEAUX_EXEMPTES_CAR_SANS_LETTRE_ACCENTUABLE));
    }

    /**
     * ADR-0013 obligation 3 : mise a l'epreuve sur un objet neuf, absent du catalogue reel - un
     * niveau qui porte une lettre accentuable et qu'aucun arbitrage n'a inventorie. L'exemption ne
     * doit pas s'etendre au-dela de sa frontiere ecrite ("CP", "CM1").
     */
    @Test
    void unNiveauNeufHorsDeLInventaireEtHorsExemptionFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueToutePorteEstInventoriee(
                partie.jeuxDisponibles(), List.of("Théorème")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas")
                .hasMessageContaining("Théorème");
    }

    /**
     * ADR-0013 obligation 3 : mise a l'epreuve sur un objet neuf, absent du catalogue reel - un jeu
     * dont le titre n'a jamais ete arbitre. Construit a la main (comme {@code CatalogueDeTest}),
     * jamais tire d'un fichier YAML : la preuve porte sur la lecture par reflexion, pas sur le
     * chargeur.
     */
    @Test
    void uneMatiereDeGroupeNeuveHorsDeLInventaireFaitEchouerLeGardeFou() {
        GroupeDeJeux groupeFautif = new GroupeDeJeux(
                new Matiere("Matiere jamais arbitree", "Maths", "francais.svg", "#cfe3f6", "#2f6c9f", "maths"),
                List.of());

        assertThatThrownBy(() -> verifierQueToutePorteEstInventoriee(
                List.of(), List.of(), List.of(groupeFautif)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas")
                .hasMessageContaining("matieresDuNiveau()")
                .hasMessageContaining("Matiere jamais arbitree");
    }

    /**
     * ADR-0013 obligation 3 : le meme garde-fou lu sur le groupe que le service rend vraiment. Un
     * jeu du catalogue reel regroupe sous une matiere dont l'accent a ete retire ne passe pas : le
     * libelle de matiere de la methode neuve est lu, pas seulement celui du descripteur des jeux.
     */
    private static String sansAccent(String texte) {
        return java.text.Normalizer.normalize(texte, java.text.Normalizer.Form.NFD)
                .replaceAll("[\\u0300-\\u036f]", "");
    }

    /**
     * ADR-0013 obligation 3, ADR-0106 : chacune des cinq chaines d'une ligne de matiere que l'enfant
     * ou le HTML recoit (libelle, pictogramme, deux teintes, segment) est lue ; une valeur neuve dans
     * l'un de ces champs, sur une ligne reelle, fait echouer le garde-fou en nommant le champ.
     */
    @Test
    void uneValeurNeuveDansChaqueChampDeLaLigneDeMatiereFaitEchouerLeGardeFou() {
        GroupeDeJeux reel = partie.matieresDuNiveau(partie.niveauxDuCatalogue().get(0)).get(0);
        Matiere l = reel.matiere();
        Map<String, Matiere> fautives = Map.of(
                "libelle", new Matiere(l.nom(), "Libelle jamais arbitre", l.pictogramme(), l.teinteFond(),
                        l.teinteContour(), l.segment()),
                "pictogramme", new Matiere(l.nom(), l.libelle(), "jamais-arbitre.svg", l.teinteFond(),
                        l.teinteContour(), l.segment()),
                "teinteFond", new Matiere(l.nom(), l.libelle(), l.pictogramme(), "#123456",
                        l.teinteContour(), l.segment()),
                "teinteContour", new Matiere(l.nom(), l.libelle(), l.pictogramme(), l.teinteFond(),
                        "#654321", l.segment()),
                "segment", new Matiere(l.nom(), l.libelle(), l.pictogramme(), l.teinteFond(),
                        l.teinteContour(), "segment-jamais-arbitre"));

        fautives.forEach((champ, fautive) -> assertThatThrownBy(() -> verifierQueToutePorteEstInventoriee(
                List.of(), List.of(), List.of(new GroupeDeJeux(fautive, reel.jeux()))))
                .as("champ %s", champ)
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("matieresDuNiveau()")
                .hasMessageContaining("matiere." + champ));
    }

    @Test
    void uneMatiereDuCatalogueReelPriveeDeSonAccentFaitEchouerLeGardeFou() {
        String niveau = partie.niveauxDuCatalogue().get(0);
        GroupeDeJeux reel = partie.matieresDuNiveau(niveau).stream()
                .filter(groupe -> !groupe.matiere().nom().equals(sansAccent(groupe.matiere().nom())))
                .findFirst().orElseThrow(() -> new AssertionError(
                        "ADR-0105 : aucune matiere accentuee au niveau " + niveau));
        Matiere ligne = reel.matiere();
        String sansAccent = sansAccent(ligne.nom());

        assertThatThrownBy(() -> verifierQueToutePorteEstInventoriee(
                List.of(), List.of(), List.of(new GroupeDeJeux(new Matiere(sansAccent, ligne.libelle(),
                        ligne.pictogramme(), ligne.teinteFond(), ligne.teinteContour(), ligne.segment()),
                        reel.jeux()))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("matieresDuNiveau()")
                .hasMessageContaining(sansAccent);
    }

    @Test
    void unTitreDeJeuNeufHorsDeLInventaireFaitEchouerLeGardeFou() {
        GameDefinition jeuReel = partie.jeuxDisponibles().get(0);
        // L'identifiant, la mecanique et le descripteur reprennent des valeurs deja inventoriees :
        // seul le titre est neuf, pour que ce soit bien lui que la premiere assertion en defaut nomme
        // (GameDefinition.titre() est la deuxieme composante lue, juste apres l'identifiant).
        GameDefinition jeuFautif = new GameDefinition(
                jeuReel.id(), "Titre jamais arbitre", jeuReel.consigne(), jeuReel.mecanique(),
                jeuReel.descripteur(), jeuReel.contenu(), jeuReel.nombreDeManches());

        assertThatThrownBy(() -> verifierQueToutePorteEstInventoriee(
                List.of(jeuFautif), List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas")
                .hasMessageContaining("Titre jamais arbitre");
    }
}
