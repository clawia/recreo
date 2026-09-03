package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.mechanic.MechanicId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            // S27-3 : entree neuve, premier paquet d'histoire-geographie, CM1.
            "bandit-manchot-histoire-geographie-cm1",
            // titres (catalogue.yaml, GameDefinition.titre) : lus par l'enfant sous th:text
            // (accueil.html, le bouton "Jouer" de chaque jeu), deja arbitres au fil des sprints qui
            // ont livre chaque jeu.
            "Calcul CP",
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
            "Vivant ou objet",
            "Le corps et les sens",
            "L'eau solide ou liquide",
            // S27-3 : titre neuf.
            "Histoire et géographie",
            // consignes (catalogue.yaml, GameDefinition.consigne)
            "Combien ça fait ?",
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
            "Lis bien, puis dis si c'est un être vivant ou un objet.",
            // S27-2 et S27-3 partagent "Lis bien la question, puis choisis la bonne
            // réponse." : aucune entree neuve pour S27-3.
            "Lis bien la question, puis choisis la bonne réponse.",
            "Lis bien, puis dis si c'est solide ou liquide.",
            // mecanique (catalogue.yaml, GameDefinition.mecanique().value()) : identifiant technique.
            // S24 : deux valeurs depuis que la saisie est servie, la premiere seconde mecanique du
            // produit (ADR-0058). Comptees sur catalogue.yaml, jamais recopiees.
            "bandit-manchot",
            "saisie",
            // matiere (descripteur de chaque paquet reference par le catalogue).
            // S19-5 (ADR-0049) : graphie accentuee, corrigee depuis S16-5.
            "Mathématiques",
            "Français",
            // S27-2 : matiere neuve du produit.
            "Questionner le monde",
            // S27-3 : matiere neuve, premiere matiere neuve du produit sur ce referentiel.
            "Histoire-géographie",
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
            // competence (descripteur)
            "Calcul mental - additions dans la limite de 10",
            "Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif",
            "Calcul mental - soustractions dans la limite de 10",
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
            // S27-3 : competence neuve, premier paquet de la matiere. Touche plusieurs lignes du
            // referentiel (S27-3 ne pretend fermer aucune ligne) - libelle du descripteur du
            // paquet, mot pour mot.
            "Ouvre plusieurs compétences du référentiel : se repérer dans le temps (grandes "
                    + "périodes, siècles), identifier des traces d'une occupation ancienne, nommer "
                    + "des rois et leur réalisation, situer les événements de la Révolution et de "
                    + "l'Empire, distinguer des types d'espaces et leurs activités, comprendre un "
                    + "document historique (lignes 1, 2, 3, 4, 6, 7, 8, 11, 12, 13, 14 du "
                    + "référentiel CM1)");

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
     * par {@link GameDefinition} et son descripteur) et par {@link
     * PartieUseCase#niveauxDuCatalogue()}, hors niveaux exemptes.
     */
    private static List<ChaineExposee> chainesExposeesParLePortEntrant(List<GameDefinition> jeux,
                                                                        List<String> niveaux) {
        List<ChaineExposee> chaines = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            collecter(jeu.id(), "", jeu, chaines);
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
        List<ChaineExposee> chaines = chainesExposeesParLePortEntrant(jeux, niveaux);
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
        verifierQueToutePorteEstInventoriee(partie.jeuxDisponibles(), partie.niveauxDuCatalogue());
    }

    /**
     * Le versant qui empeche l'inventaire de pourrir (meme lecon qu'ADR-0039 pour
     * TexteAccentueDesGabaritsTest) : une entree que le catalogue reel ne produit plus laisserait
     * croire une chaine gardee alors qu'elle a disparu.
     */
    @Test
    void chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel() {
        List<String> valeursExposees = chainesExposeesParLePortEntrant(
                partie.jeuxDisponibles(), partie.niveauxDuCatalogue())
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
