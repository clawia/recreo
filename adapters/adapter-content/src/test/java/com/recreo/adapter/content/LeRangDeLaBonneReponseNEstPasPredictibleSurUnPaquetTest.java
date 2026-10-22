package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Verdict de nadia-prof du 2026-08-14, REFUS de S12-3
 * ({@code squad/conformite/FICHE-bandit-manchot-tables-multiplication-cm1.md}) : sur les 99 items du
 * paquet livre, la bonne reponse n'etait jamais la plus petite des trois propositions, et etait la
 * plus grande sur 64 items sur 99 - un enfant qui presse toujours la plus grande gagnait 64,6 % des
 * manches au calcul exhaustif (64,4 % mesure en jouant), contre 33,3 % par hasard, sans lire un seul
 * enonce. La cause : les deux distracteurs d'un item de type "produit voisin" etaient systematiquement
 * les deux voisins du dessous, ce qui rend le rang de la bonne reponse constant sur tout le paquet.
 *
 * <p><b>La propriete est generique a toute matiere</b> : rien ici ne nomme une table de
 * multiplication ou une conversion. Tout paquet dont un item propose exactement trois propositions
 * numeriques comparables (la bonne reponse et deux distracteurs) traverse ce garde-fou des qu'il
 * existe, quelle que soit la matiere qu'il porte - {@link Item} est scelle a {@link Question}
 * (ADR-0004).</p>
 *
 * <p><b>La propriete choisie, et pourquoi ce seuil precis.</b> "Jamais constant" est trop faible : le
 * paquet refuse ne l'etait pas (35,4 % des items rendaient la bonne reponse intermediaire). "Uniformement
 * distribue" est trop fort pour un petit paquet : avec 22 items et trois rangs, la quantification seule
 * interdit de retomber exactement sur un tiers. Le seuil retenu est la <b>majorite</b> : une strategie
 * qui choisit toujours le meme rang parmi les trois propositions, sans jamais lire l'enonce, ne doit pas
 * gagner plus de la moitie des manches d'un paquet. C'est le milieu exact entre le hasard mesure
 * (33,3 %) et le taux disqualifiant mesure par nadia-prof (64,6 %), et il se lit comme un fait simple :
 * une strategie qui ignore le contenu ne doit jamais etre majoritaire.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici, et leurs items viennent du <b>chargeur reel</b> ;</li>
 *   <li><b>derivation totale</b> : chaque item tire dont les trois propositions sont numeriques produit
 *       une classification de rang, sans exception. Un item dont une proposition n'est pas numerique
 *       (un symbole de comparaison, un mot) est hors du domaine de cette propriete - le rang d'une
 *       proposition non numerique n'a pas de sens - et non un objet ignore par erreur ;</li>
 *   <li><b>mise a l'epreuve</b> : un paquet fautif neuf, jamais livre, dont la bonne reponse est
 *       toujours la plus grande des trois propositions, et sa version corrigee qui doit redevenir
 *       verte.</li>
 * </ul>
 *
 * <p>ADR-0039 : le critere n'est pas une liste enumeree, c'est une propriete calculee sur les rangs -
 * aucune promesse universelle n'est tenue par une liste de refus ici.</p>
 *
 * <p><b>Dette assumee.</b> Deux paquets du catalogue depassent aujourd'hui ce seuil :
 * {@code cm1-perimetre} (90,0 % des items numeriques rendent la bonne reponse intermediaire) et
 * {@code cm1-mesures} (54,2 % la rendent la plus petite). Aucun des deux n'est dans le perimetre de
 * S12-3 : je ne corrige pas un contenu qu'un autre agent a ecrit. Cette liste est exemptee, elle
 * n'absout pas : elle ne peut pas grandir (PLAFOND_DE_DETTE) et ne peut pas pourrir (chaque nom doit
 * designer un paquet qui existe reellement et qui depasse effectivement le seuil), suivant le meme
 * mecanisme que {@code LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE} (ADR-0013). Remonte a
 * sofia-sm avec ces chiffres dans le rapport de cette story.</p>
 *
 * <p><b>Correction du 2026-08-15, demandee par nadia-prof (FICHE-bandit-manchot-additions-cp.md,
 * section "Done pedagogique du 2026-08-15").</b> Le chiffre pour {@code cp-additions-jusqu-a-10}
 * portait 63,1 % ; il est corrige a 66,5 %. Ma methode classe un tirage par identifiant d'item
 * distinct, ce qui est le compte exact sur un paquet enumere - nadia-prof a reproduit mes trois
 * autres chiffres a la decimale par un comptage independant - mais devient un echantillon (41 sur 65
 * enonces possibles) sur ce paquet-la, seul du releve a etre une fabrique qui retire ses distracteurs
 * au hasard a chaque tirage plutot qu'une liste ecrite. Le calcul exact de nadia-prof sur la fabrique
 * donne 66,54 %. Le paquet est reste dans {@code DETTE_DE_RANG} le temps que {@code marc-dev} corrige
 * cette fabrique dans un autre lot (S12) : sa cause a disparu, {@code cp-additions-jusqu-a-10} en sort
 * ici dans le meme geste que la fusion de ce lot - reconstate par ce test lui-meme, qui a rougi une
 * fois les deux lots fusionnes tant que l'entree restait, la preuve que l'exemption etait devenue
 * perimee plutot que sa seule parole. {@code PLAFOND_DE_DETTE} descend en consequence de 3 a 2 : une
 * dette qui ne descend pas alors que sa cause a disparu pourrit.</p>
 *
 * <p><b>Second garde-fou, ajoute le 2026-08-15 : aucun rang ne devrait tomber sous un seuil
 * minimal.</b> Reserve neuve et datee de nadia-prof (FICHE-bandit-manchot-additions-cp.md, section 6,
 * et FICHE-bandit-manchot-perimetre-cm1.md, complement du 2026-08-15) : le seuil de majorite ne voit
 * que le rang le plus frequent, jamais le rang le plus rare. Un rang quasi vide elimine une
 * proposition gratuitement a presque chaque manche, sans jamais rendre une strategie a rang constant
 * majoritaire - {@code cp-soustractions} passe le premier garde-fou (50,0 % pile, comparaison stricte)
 * et n'offre pourtant la plus grande que sur 8,3 % de ses items. Le seuil retenu, 10 %, est celui de
 * nadia-prof, pas un choix d'ingenieur : il n'est pas negocie ici. Cette reserve est explicitement
 * <b>non bloquante</b> et ne retire aucun Done pedagogique ; elle est encodee pour qu'elle ne se
 * perde pas, pas pour forcer une reprise.</p>
 *
 * <p><b>Dette du second garde-fou.</b> {@code cm1-perimetre} (la plus petite a 0,0 %) et
 * {@code cp-soustractions} (la plus grande a 8,3 %) sont exemptes, avec le verdict pedagogique pour
 * seul motif - {@code cm1-perimetre} parce que nadia-prof a explicitement choisi de ne pas rouvrir le
 * lot livre (P-6 ne change que pour les lots a venir), {@code cp-soustractions} parce qu'il est hors
 * du mandat de cette story. <b>Un troisieme paquet, mesure et non annonce dans la reserve, s'y ajoute</b> :
 * {@code cp-complements-10} (la plus petite a 9,1 %), que nadia-prof nomme elle-meme dans la meme
 * section - "cp-complements-10 est dans le meme cas" que cp-soustractions - et qui rougit reellement
 * ce garde-fou une fois ecrit. <b>Exempte ne veut pas dire valide</b> : les trois portent une reserve
 * pedagogique ouverte au nom de nadia-prof, ecrite dans les fiches citees ci-dessus, pas dans ce
 * test.</p>
 *
 * <p><b>Correction du 2026-08-15 : le cas des valeurs egales.</b> Ni ma correction du classificateur
 * (31ca444) ni ADR-0041 ne fermaient le cas ou deux ou trois propositions d'un meme item se lisent a
 * la meme valeur : {@link #rangDe} les classait "la plus petite" a tort (aucun distracteur n'est
 * strictement inferieur des qu'il est egal), et le fait s'est materialise deux fois - par le code, sur
 * les items "decomposer" de {@code cm1-grands-nombres} ou {@link #valeurDe} ne lisait que le premier
 * terme d'une somme, et par le refus pedagogique de nadia-prof sur le meme paquet, dont les sept items
 * "decomposer" ne faisaient varier que ce premier terme. Deux corrections : {@link #sommeDeTermes}
 * lit desormais le total d'une decomposition plutot que son premier terme ; et un garde-fou dedie,
 * {@link #verifierQuAucuneValeurNeCoincide}, rend visible toute egalite residuelle au lieu de la
 * laisser exclue en silence du calcul de rang.</p>
 *
 * <p><b>Ratification du 2026-08-15 (theo-archi, ADR-0041 amende).</b> La lecture de la somme devient
 * le cas 2 de la decision d'ADR-0041, au meme rang que la duree. Elle est mesuree necessaire sur du
 * materiau reel : sans elle, les sept items "decomposer" de la forme que nadia-prof exige se lisent
 * tous a la meme valeur et sortent du calcul de rang, soit la moitie des items numeriques du paquet.
 * Ce que cette ratification ne couvre pas - le seul operateur "+", la somme mal formee qui n'a pas
 * de valeur plutot qu'une valeur partielle, la decomposition non canonique - est ecrit dans ADR-0041,
 * non-preuves 8 a 10, et n'est pas paraphrase ici.</p>
 *
 * <p><b>Lot 5a de s24, decision 10 : borne a l'item qui presente des propositions, et c'est la borne
 * la mieux payee du tri.</b> La propriete decrit une <b>strategie de pression</b> ; en saisie il n'y a
 * ni rang, ni bouton, ni ordre a exploiter. La garantie qu'elle achete n'est pas perdue pour autant,
 * elle est obtenue <b>par construction</b>, et c'est nadia-prof qui l'a chiffree : "un enfant qui ne
 * sait rien obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur onze reponses
 * possibles, il en obtient au plus trois sur onze par manche." Un paquet entierement de saisie se
 * declare dans {@link #PAQUETS_HORS_DU_DOMAINE_NUMERIQUE} avec son motif, comme tout paquet qu'aucun
 * item ne fait entrer dans le calcul (ADR-0051) : la borne passe par la porte qui existait deja, elle
 * n'en ouvre pas une seconde.</p>
 */
class LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest";

    /**
     * Racine du jeu fautif propre au second garde-fou (seuil minimal), separee de celle du premier
     * pour qu'aucun des deux ne pollue la mise a l'epreuve de l'autre : le paquet fautif du premier
     * garde-fou (toujours la plus grande) a un rang a 0 % qui violerait aussi le seuil minimal, et la
     * version corrigee du second (voir plus bas) n'est pas construite pour satisfaire le premier.
     */
    private static final Path CONTRE_EXEMPLE_SEUIL_MINIMUM = CONTRE_EXEMPLE.resolve("seuil-minimal");

    private static final String CONTRE_EXEMPLE_SEUIL_MINIMUM_CLASSPATH =
            CONTRE_EXEMPLE_CLASSPATH + "/seuil-minimal";

    /**
     * Racine du jeu fautif propre a la branche milliers de la correction du 2026-08-15 (voir la
     * Javadoc de {@link #NOMBRE}) : des nombres au-dessus de mille, ecrits au separateur d'espace,
     * dont la bonne reponse est toujours la plus grande - materiau reel de theo-archi (avis de
     * faisabilite S13-1), dont l'item "1 000 000" contre "999 999" et "998 000" est repris a
     * l'identique.
     */
    private static final Path CONTRE_EXEMPLE_MILLIERS = CONTRE_EXEMPLE.resolve("milliers");

    private static final String CONTRE_EXEMPLE_MILLIERS_CLASSPATH = CONTRE_EXEMPLE_CLASSPATH + "/milliers";

    /**
     * Racine du jeu fautif propre a la branche durees de la meme correction : des propositions
     * ecrites en heures et minutes, dont six items partagent la meme heure de tete entre les trois
     * propositions - materiau reel de nadia-prof (ARBITRAGE-PREALABLE-s13-multiplications-divisions-
     * et-durees-cm1.md, section 3.6).
     */
    private static final Path CONTRE_EXEMPLE_DUREES = CONTRE_EXEMPLE.resolve("durees");

    private static final String CONTRE_EXEMPLE_DUREES_CLASSPATH = CONTRE_EXEMPLE_CLASSPATH + "/durees";

    /**
     * Racine du jeu fautif propre a la correction du 2026-08-15 sur les valeurs egales (refus de
     * nadia-prof sur cm1-grands-nombres, section "Le trou, et pourquoi il est urgent" du rapport de
     * ce lot) : un item de decomposition dont deux distracteurs different de la bonne reponse par un
     * terme different du premier, si bien qu'une decomposition qui n'additionnerait pas ses termes
     * les lirait tous les trois a la meme valeur.
     */
    private static final Path CONTRE_EXEMPLE_VALEURS_EGALES = CONTRE_EXEMPLE.resolve("valeurs-egales");

    private static final String CONTRE_EXEMPLE_VALEURS_EGALES_CLASSPATH =
            CONTRE_EXEMPLE_CLASSPATH + "/valeurs-egales";

    /** Une strategie a rang constant ne doit jamais etre majoritaire sur un paquet. */
    private static final double SEUIL_MAJORITE = 0.5;

    /**
     * Reserve nadia-prof du 2026-08-15 : aucun rang de la bonne reponse ne devrait apparaitre sur
     * moins de 10 % des items d'un paquet. Ce seuil est le sien, pas un choix d'ingenieur.
     */
    private static final double SEUIL_MINIMUM = 0.10;

    /**
     * Budget de tirages par paquet. Un paquet enumere (ListContentSource) s'epuise de lui-meme, avant
     * ce budget ; un paquet combinatoire (AdditionsBorneesContentSource) ne s'epuise jamais et ne
     * s'arrete que sur ce budget, assez large pour couvrir tous ses couples possibles avec marge.
     */
    private static final int BUDGET_DE_TIRAGES = 2000;

    /**
     * Un couple de facteurs plafonne a 10 dans ce depot, plus une marge : au-dela, un tirage
     * combinatoire n'a plus de couple neuf a offrir et boucler davantage ne changerait rien.
     */
    private static final int TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET = 200;

    /**
     * Correction du 2026-08-15, demandee par theo-archi (avis de faisabilite S13-1) et par
     * nadia-prof (ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md, section 3.6).
     * L'ancien motif, {@code ^-?\d+}, ne lisait que le premier groupe de chiffres d'une proposition.
     * Sur un nombre nu c'est le nombre entier ; sur un nombre a separateur de milliers ("1 000 000"),
     * ce n'est que le millier de tete, ce qui inverse le rang reel des lors qu'un paquet franchit le
     * millier (S13-1 est le premier a le faire) ; sur une duree ("1 h 30"), ce n'est que l'heure, ce
     * qui ignore les minutes entierement.
     *
     * <p>Ce motif capture desormais un nombre entier, y compris ses separateurs de milliers, plutot
     * que son seul premier groupe. {@link #SEPARATEUR_DE_MILLIERS} retire ensuite ces separateurs
     * avant lecture. Trois ecritures sont vues : l'espace simple, celle qu'ecrit MD-4 de l'arbitrage
     * cite ci-dessus ("4 500") ; l'espace insecable (U+00A0) et l'espace fine insecable (U+202F), les
     * deux variantes typographiques que theo-archi demande de couvrir par prudence, alors qu'aucun
     * paquet livre n'en ecrit aucune aujourd'hui.</p>
     */
    private static final Pattern NOMBRE = Pattern.compile("^-?\\d(?:[\\d\\u00A0\\u202F ]*\\d)?");

    /**
     * Le meme motif que {@link #NOMBRE}, ancre aussi en fin de chaine : sert a verifier qu'un terme
     * pris isolement (voir {@link #sommeDeTermes}) est un nombre entier complet et rien d'autre.
     */
    private static final Pattern NOMBRE_COMPLET = Pattern.compile(NOMBRE.pattern() + "$");

    /** Les trois ecritures d'un separateur de milliers vues par {@link #NOMBRE} : voir sa Javadoc. */
    private static final Pattern SEPARATEUR_DE_MILLIERS = Pattern.compile("[\\u00A0\\u202F ]");

    /** Le separateur d'une somme de termes : voir {@link #sommeDeTermes}. */
    private static final Pattern SEPARATEUR_DE_SOMME = Pattern.compile("\\s*\\+\\s*");

    /**
     * Une duree ou un instant ecrit en heures et minutes : "1 h 30", "1 h 30 min", "1 heure 30
     * minutes", "2 h" (heures seules). Un instant du jour ("8 h 30") a la meme ecriture et se lit de
     * la meme facon, en minutes depuis minuit : cela suffit a l'ordonner puisqu'aucun item du depot
     * ne franchit minuit (D-3 de l'arbitrage cite ci-dessus). Vu en premier par {@link #valeurDe},
     * avant {@link #NOMBRE} : sur "1 h 30", {@link #NOMBRE} ne verrait que "1" et perdrait les
     * minutes, exactement le defaut releve par nadia-prof.
     */
    private static final Pattern DUREE_HEURES = Pattern.compile(
            "^(\\d+)\\s*h(?:eures?)?\\.?(?:\\s+(\\d+)\\s*(?:min(?:utes?)?)?\\.?)?$");

    /**
     * La demi-heure ecrite en toutes lettres : "2 heures et demie", "1 heure et demie". ADR-0102.
     *
     * <p><b>Pourquoi cette ecriture-la et aucune autre.</b> {@code DUR-CE1-3} (nadia-prof, arbitrage
     * prealable du lot 2 de s43) nomme deux ecritures de duree pour un paquet de {@code MAT-CE1-12} :
     * <i>"2 heures et demie"</i> ou <i>"2 h 30 min"</i>. La seconde est lue par
     * {@link #DUREE_HEURES} depuis s13 ; la premiere ne l'etait pas - le motif exige des chiffres
     * apres les heures, la lecture retombait sur {@link #NOMBRE} et rendait <b>2</b> au lieu de 150,
     * sans qu'aucune ligne ne le dise. <b>Le quart d'heure n'est pas ajoute</b> : {@code DUR-CE1-2}
     * borne les minutes a {@code 00} et {@code 30}, aucun contenu servi ne l'ecrit, et une ecriture
     * ajoutee d'avance serait un motif qui garde une population vide. S'il en naissait une,
     * {@link #verifierQuAucuneEcritureDeDureeNEchappeALaLecture} la rendrait rouge plutot que muette.
     */
    private static final Pattern DUREE_ET_DEMIE =
            Pattern.compile("^(\\d+)\\s*h(?:eures?)?\\s+et\\s+demie?\\.?$");

    /** Une duree en minutes seules : "45 min", "45 minutes". */
    private static final Pattern DUREE_MINUTES = Pattern.compile("^(\\d+)\\s*min(?:utes?)?\\.?$");

    /**
     * ADR-0102 : ce qui <b>ressemble</b> a une duree ou a un instant - un nombre suivi d'une unite de
     * temps. Le critere est volontairement plus large que les motifs de lecture : c'est exactement
     * l'ecart entre les deux qui est le defaut a rendre visible. Une proposition qui entre ici et que
     * {@link #dureeEnMinutes} ne lit pas serait lue comme un <b>nombre nu</b> - ses heures comparees
     * aux minutes d'une autre proposition - et le rang mesure ne serait pas celui qui est servi.
     */
    private static final Pattern RESSEMBLE_A_UNE_DUREE =
            Pattern.compile("^\\d+\\s*(?:h|heures?|min|minutes?)\\b.*", Pattern.CASE_INSENSITIVE);

    /**
     * Paquets exemptes de ce garde-fou, hors du perimetre de S12-3, avec le taux mesure qui justifie
     * l'exemption. Voir la Javadoc de la classe.
     */
    private static final Set<String> DETTE_DE_RANG =
            Set.of("cm1-perimetre", "cm1-mesures");

    /** Etat au 2026-08-15, s12. Ce nombre ne remonte jamais ; le baisser demande un diff visible. */
    private static final int PLAFOND_DE_DETTE = 2;

    /**
     * Paquets exemptes du second garde-fou (seuil minimal), avec le verdict pedagogique pour motif -
     * pas le mien. Voir la Javadoc de la classe : exempte ne veut pas dire valide.
     */
    private static final Set<String> DETTE_DE_SEUIL_MINIMUM =
            Set.of("cm1-perimetre", "cp-soustractions", "cp-complements-10");

    /** Etat au 2026-08-15, s12. Ce nombre ne remonte jamais ; le baisser demande un diff visible. */
    private static final int PLAFOND_DE_DETTE_SEUIL_MINIMUM = 3;

    /**
     * Un compte ecrit dans un motif d'exemption : un groupe de chiffres suivi du mot qu'il compte.
     * Voir {@link #chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet()}.
     */
    private static final Pattern COMPTE_ECRIT_DANS_UN_MOTIF =
            Pattern.compile("(\\d+)\\s+(items|propositions)");

    /**
     * ADR-0051 : les paquets dont <b>aucun</b> item n'entre dans le calcul de rang, avec le motif de
     * chacun. Ce n'est ni une dette ni une exemption : ces paquets ne violent rien, ils sont hors du
     * domaine d'une propriete qui suppose trois valeurs comparables. Ce qui est en cause n'est pas
     * leur contenu, c'est le <b>silence</b> - avant cette declaration, un paquet entierement hors du
     * domaine traversait ce garde-fou sans qu'aucune ligne verte ou rouge ne dise qu'il n'avait
     * jamais ete regarde.
     *
     * <p>Cette table ne peut ni grandir en silence (un paquet hors du domaine et non declare est
     * rouge), ni pourrir dans les deux sens (un paquet declare qui contribue des items est rouge, un
     * nom qui ne designe plus aucun paquet est rouge). Elle n'a <b>pas de plafond</b>, et c'est
     * delibere : un plafond dirait qu'un contenu textuel de plus est une degradation, alors que c'est
     * le programme scolaire qui le demande. Le cout de sa croissance est ailleurs - chaque nom qui s'y
     * ajoute mesure la part du contenu servi sur laquelle cette propriete n'est gardee par rien.</p>
     */
    private static final Map<String, String> PAQUETS_HORS_DU_DOMAINE_NUMERIQUE = Map.ofEntries(
            // s53 (session de contenu hors sprint) : paquets non branches au catalogue, propositions textuelles.
            Map.entry("ce1-emc-biens-communs",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-20 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-emc-regle-sanction",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-22 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-accord-groupe-nominal",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-accord-sujet-verbe",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-classes-de-mots",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-deplacements-quadrillage",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-familles-de-mots",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-futur",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-inference",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            // S57-BR-FRA-CE1-21 (s57) : le paquet passe en saisie, motif reecrit.
            Map.entry("ce1-mots-invariables",
            "24 items sur 24 sont des items de saisie : ils ne presentent aucune proposition, donc"
                    + " aucun rang. Meme motif que cp-mot-dicte (lot 5a de s24, decision 10) - la"
                    + " propriete decrit une strategie de pression sur des propositions, et en saisie"
                    + " il n'y a ni bouton ni ordre a exploiter. Mesure du 2026-10-22 sur le paquet"
                    + " du depot : 24 items, aucun distracteur"),
            Map.entry("ce1-mots-irreguliers",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-negation",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-ordre-des-mots",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-passe-compose",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-phrases-types",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-pronoms",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-relire-une-phrase",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-texte-documentaire",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-texte-narratif",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("ce1-verbe-sujet",
            "24 items sur 24 portent des propositions textuelles : aucune des 72 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-anglais-ecrire-mots",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-anglais-lire-texte",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-arts-decrire-une-oeuvre-lexique",
            "13 items sur 13 portent des propositions textuelles : aucune des 39 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-emc-atteinte-autrui",
            "11 items sur 11 portent des propositions textuelles : aucune des 33 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-emc-principes-republique",
            "11 items sur 11 portent des propositions textuelles : aucune des 33 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-emc-respect-differences",
            "11 items sur 11 portent des propositions textuelles : aucune des 33 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-caracteristiques-techniques",
            "13 items sur 13 portent des propositions textuelles : aucune des 39 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-decrire-lexique",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-epoque-civilisation",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-materiaux-mise-en-forme",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-musee-reperes-regles",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-hda-reperes-patrimoine",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-histoire-geographie-carte-et-planisphere",
            "14 items sur 14 portent des propositions textuelles : aucune des 42 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-histoire-geographie-echelles-du-lieu-de-vie",
            "14 items sur 14 portent des propositions textuelles : aucune des 42 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-lire-et-restituer",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-sciences-environnement-activite-humaine",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cm1-sciences-terre-jour-nuit-saisons",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-arts-oeuvres-familles",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-assemblage-syllabique",
            "20 items sur 20 portent des propositions textuelles : aucune des 60 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-emc-personnes-differentes",
            "11 items sur 11 portent des propositions textuelles : aucune des 33 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-qlm-modes-de-vie",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-qlm-situer-la-france",
            "12 items sur 12 portent des propositions textuelles : aucune des 36 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-qlm-vocabulaire-de-position",
            "14 items sur 14 portent des propositions textuelles (certains items portent un seul distracteur, donc deux propositions) : aucune des 41 propositions n'est numerique,"
                    + " valeurDe rend null sur chacune. Mesure du 2026-10-07 sur le paquet du depot :"
                    + " aucune proposition ne commence par un chiffre"),
            Map.entry("cp-complements-10-saisie",
            "11 items sur 11 sont des items de saisie : ils ne presentent aucune proposition, donc"
                    + " aucun rang. Lot 5a de s24, decision 10 - la propriete decrit une strategie de"
                    + " pression, et en saisie il n'y a ni bouton ni ordre a exploiter. La garantie"
                    + " qu'elle achete n'est pas perdue, elle est obtenue par construction et"
                    + " nadia-prof l'a chiffree : un enfant qui ne sait rien obtient 7 manches sur 7"
                    + " avec des boutons, et au plus trois sur onze par manche en saisie"),
            Map.entry("cp-ranger-nombres",
            "24 items sur 24 proposent une liste ordonnee de trois nombres (\"a, b, c\"), pas"
                    + " une valeur scalaire unique : depuis ADR-0068, lireValeur y rend null (le"
                    + " reste apres le premier nombre porte encore un chiffre). Mesure du"
                    + " 2026-08-30, verification directe sur les 72 propositions du paquet"),
            Map.entry("cm1-conjugaison-present",
            "25 items sur 25 portent trois distracteurs, donc quatre propositions : le rang d'une"
                    + " bonne reponse parmi trois n'y est pas defini. Mesure du 2026-08-19, comptage"
                    + " des cles distracteurs du paquet servi"),
            Map.entry("cm1-homophones",
            "24 items sur 24 portent un seul distracteur, donc deux propositions : meme motif, en"
                    + " sens inverse. Mesure du 2026-08-19"),
            Map.entry("cp-comparer-nombres",
            "24 items sur 24 proposent les trois symboles de comparaison (\"<\", \">\", \"=\") :"
                    + " deux distracteurs, mais aucune des trois propositions n'a de valeur"
                    + " numerique. C'est l'enonce qui porte les nombres, jamais les propositions"),
            Map.entry("cp-le-bon-son",
            "15 items sur 15 proposent des graphies de phonemes (\"on\", \"in\", \"an\") : deux"
                    + " distracteurs, aucune valeur numerique lisible"),
            Map.entry("ce1-solides",
            "15 items sur 15 proposent un nom de solide (\"un cube\", \"une boule\") : deux"
                    + " distracteurs, aucune valeur numerique lisible. Mesure du 2026-09-22, sur le"
                    + " paquet servi (S39-SOLIDES)"),
            // Motif nettoye le 2026-09-10 : sa premiere phrase portait encore le compte d'avant le
            // retrait de quatre items du 2026-08-20, que sa derniere phrase corrigeait deja. Un
            // motif dit l'etat du jour ; l'histoire du paquet vit dans git.
            Map.entry("cm1-synonymes",
            "16 items sur 16 portent deux distracteurs (trois propositions), mais aucune des 48"
                    + " propositions n'est numerique : ce sont des mots de vocabulaire (synonymes et"
                    + " distracteurs), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-08-20 apres le retrait de quatre items par camille-po (vite,"
                    + " ancien, poli, drole), recomptee le 2026-09-10 sur le paquet servi : aucune"
                    + " des 48 propositions ne commence par un chiffre"),
            Map.entry("cm1-accords-groupe-nominal",
            "24 items, un nombre de distracteurs qui varie selon la composante testee (1 pour le"
                    + " nom, 2 pour le determinant, 3 pour l'adjectif) : les huit items de"
                    + " determinant portent bien deux distracteurs, mais aucune des 72 propositions"
                    + " du paquet n'est numerique - ce sont des mots (determinants, noms,"
                    + " adjectifs), valeurDe rend null sur chacune. Mesure du 2026-08-24 sur le"
                    + " paquet servi"),
            Map.entry("cm1-accord-sujet-verbe",
            "21 items sur 21 portent deux distracteurs (trois propositions), mais aucune des 63"
                    + " propositions n'est numerique : ce sont des formes conjuguees d'un verbe"
                    + " (bonne reponse et deux erreurs d'accord), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-08-24 : aucune des 63"
                    + " propositions ne commence par un chiffre"),
            Map.entry("cm1-antonymes",
            "17 items sur 17 portent deux distracteurs (trois propositions), mais aucune des 51"
                    + " propositions n'est numerique : ce sont des mots de vocabulaire (antonymes et"
                    + " distracteurs), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-08-24 sur le paquet servi (arbitrage ANT-1 a ANT-7 de"
                    + " nadia-prof) : aucune des 51 propositions ne commence par un chiffre"),
            Map.entry("cp-nature-mot",
            "16 items sur 16 portent un seul distracteur, donc deux propositions - hors du domaine"
                    + " au meme titre que cm1-homophones - et les deux propositions sont toujours les"
                    + " memes deux mots (\"nom\", \"verbe\"), jamais une valeur numerique. Mesure du"
                    + " 2026-08-24 sur le paquet servi"),
            Map.entry("cm1-reperes",
            "16 items sur 16 portent deux distracteurs (trois propositions), mais aucune des"
                    + " 48 propositions n'est numerique : ce sont des libelles fixes tires d'un"
                    + " pool de cinq (\"Majuscule initiale\", \"Point final\", \"Majuscule au"
                    + " prenom\", \"Point d'interrogation\", \"Phrase correcte\"), valeurDe"
                    + " rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-08-26 sur le paquet servi, apres la correction REP-4 du meme jour"
                    + " (trois propositions par item, jamais quatre, pour rester au-dessus du"
                    + " pli - voir le commentaire du paquet servi)"),
            Map.entry("cp-mot-manquant",
            "16 items sur 16 portent deux distracteurs (trois propositions), mais aucune des"
                    + " 48 propositions n'est numerique : ce sont des mots de vocabulaire"
                    + " (objets, actions, sensations et leurs distracteurs), valeurDe rend null"
                    + " sur les trois propositions de chaque item. Mesure du 2026-08-27 sur le"
                    + " paquet servi (S21-10) : aucune des 48 propositions ne commence par un"
                    + " chiffre"),
            // S21-11 : entree neuve.
            Map.entry("cp-comparer",
            "18 items sur 18 portent deux distracteurs (trois propositions), mais aucune des 54"
                    + " propositions n'est numerique : ce sont des noms d'objets, d'animaux ou de"
                    + " meubles compares en longueur, en masse ou en contenance (\"une girafe\","
                    + " \"baignoire\"...), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-08-27 sur le paquet servi : aucune des 54 propositions"
                    + " ne commence par un chiffre"),
            // S22 (S17-2, ADR-0050) : entree neuve, comprehension de texte CM1.
            Map.entry("cm1-comprehension",
            "20 items sur 20 portent deux distracteurs (trois propositions), mais aucune des 60"
                    + " propositions n'est numerique : ce sont des reponses en texte libre (une"
                    + " situation, un lieu, un metier...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-08-29 sur le paquet servi :"
                    + " aucune des 60 propositions ne commence par un chiffre"),
            // S23-LIA : entree neuve, mots de liaison CM1.
            Map.entry("cm1-mots-de-liaison",
            "20 items sur 20 portent deux distracteurs (trois propositions), mais aucune des 60"
                    + " propositions n'est numerique : ce sont des mots de liaison (\"donc\","
                    + " \"car\", \"pourtant\"...), valeurDe rend null sur les trois propositions"
                    + " de chaque item. Mesure du 2026-08-30 sur le paquet servi : aucune des 60"
                    + " propositions ne commence par un chiffre"),
            // S24-NAR : entree neuve, comprehension d'un texte narratif tres court, CP.
            Map.entry("cp-texte-narratif",
            "20 items sur 20 portent deux distracteurs (trois propositions), mais aucune des 60"
                    + " propositions n'est numerique : ce sont des fragments recopies du"
                    + " texte-support (un prenom, un lieu, un objet), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-08-31 sur le paquet"
                    + " servi : aucune des 60 propositions ne commence par un chiffre"),
            // S24-PLU : entree neuve, marques du pluriel en -s, CP.
            Map.entry("cp-pluriel",
            "21 items sur 21 portent deux distracteurs (trois propositions), mais aucune des 63"
                    + " propositions n'est numerique : ce sont des groupes determinant + nom"
                    + " (\"les chats\", \"les chat\", \"le chats\"), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-08-31 sur le paquet"
                    + " servi : aucune des 63 propositions ne commence par un chiffre"),
            // S25-FIG : entree neuve, figures planes CP, premier paquet illustre du produit.
            Map.entry("cp-figures-planes",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont les quatre noms de figures planes de"
                    + " la ligne du programme (\"un carre\", \"un rectangle\", \"un triangle\","
                    + " \"un cercle\"), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-08-31 sur le paquet servi : aucune des 36 propositions"
                    + " ne commence par un chiffre. Le fait que ce paquet soit illustre ne change"
                    + " rien a ce motif - la propriete decrit une strategie de pression sur des"
                    + " propositions, et les propositions d'un item du palier 1 sont en texte"),
            // S25-SUB : entree neuve, substituts d'un personnage, CM1.
            Map.entry("cm1-substituts",
            "20 items sur 20 portent deux distracteurs (trois propositions), mais aucune des 60"
                    + " propositions n'est numerique : ce sont des personnages et des groupes"
                    + " nominaux tires du texte de leur propre item (\"Nadia\", \"les"
                    + " jardiniers\", \"la troupe\"), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-08-31 sur le paquet servi :"
                    + " aucune des 60 propositions ne commence par un chiffre"),
            // S25-SEM : entree neuve, se reperer dans la journee et la semaine, CP. DEC-0096
            // (2026-09-24) retire onze items mois/saisons hors du perimetre amende de SEM-1 ;
            // huit items neufs les remplacent.
            Map.entry("cp-semaine",
            "20 items sur 20 portent deux distracteurs (trois propositions), mais aucune des 60"
                    + " propositions n'est numerique : ce sont des noms de jours et de moments de"
                    + " la journee (\"mercredi\", \"vendredi\", \"le matin\", \"l'apres-midi\"),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-24 sur le paquet servi : aucune des 60 propositions ne commence"
                    + " par un chiffre"),
            // S26-L1 : entree neuve, localiser une syllabe dans un mot par la voix d'un adulte, CP.
            Map.entry("cp-position-syllabe",
            "18 items sur 18 portent deux distracteurs (trois propositions), mais aucune des 54"
                    + " propositions n'est numerique : ce sont les trois positions (\"début\","
                    + " \"milieu\", \"fin\"), valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-01 sur le paquet servi : aucune des 54"
                    + " propositions ne commence par un chiffre"),
            // S26-L2 : entree neuve, identifier un son dans un mot par la voix d'un adulte, CP.
            Map.entry("cp-son-dans-mot",
            "12 items sur 12 portent un seul distracteur, donc deux propositions - hors du domaine"
                    + " au meme titre que cm1-homophones - et les deux propositions sont toujours"
                    + " les memes deux mots (\"oui\", \"non\"), jamais une valeur numerique."
                    + " Mesure du 2026-09-01 sur le paquet servi"),
            // S26-L3 : entree neuve, associer une lettre simple au son entendu, CP.
            Map.entry("cp-lettre-du-son",
            "18 items sur 18 portent deux distracteurs (trois propositions), mais aucune des 54"
                    + " propositions n'est numerique : ce sont des lettres simples (\"p\", \"m\","
                    + " \"t\", \"r\", \"l\", \"s\"), valeurDe rend null sur les trois propositions"
                    + " de chaque item. Mesure du 2026-09-01 sur le paquet servi : aucune des 54"
                    + " propositions ne commence par un chiffre"),
            // S26-L4 : entree neuve, associer un digraphe au son entendu, CP.
            Map.entry("cp-digraphe-du-son",
            "15 items sur 15 portent deux distracteurs (trois propositions), mais aucune des 45"
                    + " propositions n'est numerique : ce sont les trois digraphes stables (\"ou\","
                    + " \"oi\", \"ch\"), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-09-01 sur le paquet servi : aucune des 45 propositions"
                    + " ne commence par un chiffre"),
            // S26-L5 : entree neuve, ecrire un mot dicte par la voix d'un adulte, CP.
            Map.entry("cp-mot-dicte",
            "12 items sur 12 sont des items de saisie : ils ne presentent aucune proposition, donc"
                    + " aucun rang. Meme motif que cp-complements-10-saisie (lot 5a de s24, decision"
                    + " 10) - la propriete decrit une strategie de pression sur des propositions, et"
                    + " en saisie il n'y a ni bouton ni ordre a exploiter. Mesure du 2026-09-01 sur"
                    + " le paquet servi : 12 items, aucun distracteur"),
            // S27-1 : entree neuve, associer une voyelle simple au son entendu, CP (DEC-0069, rang 1).
            Map.entry("cp-voyelle-du-son",
            "16 items sur 16 portent deux distracteurs (trois propositions), mais aucune des 48"
                    + " propositions n'est numerique : ce sont des voyelles simples (\"a\", \"i\","
                    + " \"o\", \"u\"), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-02 sur le paquet servi : aucune des 48 propositions ne"
                    + " commence par un chiffre"),
            // S27-2 : premier paquet de "Questionner le monde" (CP), matiere neuve du produit.
            // Motifs recomptes le 2026-09-10 : les trois dataient du 2026-09-02 et n'avaient pas
            // suivi le lot de s28 qui a porte chacun de ces paquets de huit a quatorze items. Deux
            // d'entre eux etaient faux en plus sur la forme - ils affirmaient que TOUS les items
            // portaient un seul distracteur, ce qui a cesse d'etre vrai le meme jour.
            Map.entry("cp-qlm-vivant-non-vivant",
            "14 items, 31 propositions : onze items portent un seul distracteur (deux propositions -"
                    + " hors du domaine au meme titre que cp-son-dans-mot), trois en portent deux."
                    + " Aucune proposition n'est numerique : ce sont deux categories (\"un être"
                    + " vivant\", \"un objet\"), une reponse binaire (\"oui\", \"non\") ou des noms"
                    + " d'etres et d'objets (\"le chien\", \"la chaise\", \"le caillou\"). Mesure du"
                    + " 2026-09-10, comptage direct sur le paquet servi (\"bonneReponse\" et"
                    + " \"distracteurs\")"),
            Map.entry("cp-qlm-corps-sens",
            "14 items sur 14 portent deux distracteurs (trois propositions), mais aucune des 42"
                    + " propositions n'est numerique : ce sont des noms de parties du corps (\"les"
                    + " oreilles\", \"les yeux\", \"le nez\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-10, comptage direct sur le"
                    + " paquet servi : aucune des 42 propositions ne commence par un chiffre"),
            Map.entry("cp-qlm-etats-eau",
            "14 items, 30 propositions : douze items portent un seul distracteur (deux propositions,"
                    + " toujours les deux memes etats - \"solide\", \"liquide\"), deux en portent"
                    + " deux (les noms des changements d'etat - \"fondre\", \"geler\", \"couler\")."
                    + " Aucune proposition n'est numerique. Mesure du 2026-09-10, comptage direct sur"
                    + " le paquet servi (\"bonneReponse\" et \"distracteurs\")"),
            // S27-3 : entree neuve, premier paquet d'histoire-geographie, CM1, scinde en quatre
            // entrees par S35-CONS-4 (DEC-0088, ADR-0081, avis de faisabilite de s35 de theo-archi,
            // section 6.3) : la clef "cm1-histoire-geographie" disparait avec le fichier, remplacee
            // par quatre motifs neufs, recomptes sur chacun des quatre paquets servis.
            Map.entry("cm1-histoire-geographie-situer-dans-le-temps",
            "27 items sur 27 portent deux distracteurs (trois propositions, 81 au total), mais"
                    + " aucune n'est numerique : ce sont des noms de periodes, de siecles, de"
                    + " millenaires, de faits dates ou de rois, toujours ecrits en toutes lettres ou"
                    + " precedes d'un mot (\"le XVIIIe siecle\", \"le Moyen Age\") - valeurDe rend"
                    + " null sur les trois propositions de chaque item. Mesure du 2026-09-24 (S42-HG-"
                    + "ROIS ajoute deux items sur HG-CM1-05), comptage direct sur le paquet servi"
                    + " (\"bonneReponse\" et \"distracteurs\") : aucune des 81 propositions ne"
                    + " commence par un chiffre"),
            Map.entry("cm1-histoire-geographie-nommer-en-histoire",
            "48 items sur 48 portent deux distracteurs (trois propositions, 144 au total), mais"
                    + " aucune n'est numerique : ce sont des noms de peuples, de mouvements de"
                    + " population, de rois, de realisations royales ou d'evenements de la"
                    + " Revolution et de l'Empire, valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-24 (S42-HG-ROIS ajoute trois items sur"
                    + " HG-CM1-05), comptage direct sur le paquet servi (\"bonneReponse\" et"
                    + " \"distracteurs\") : aucune des 144 propositions ne commence par un chiffre"),
            Map.entry("cm1-histoire-geographie-nommer-en-geographie",
            "30 items sur 30 portent deux distracteurs (trois propositions, 90 au total), mais"
                    + " aucune n'est numerique : ce sont des noms de types d'espace ou de"
                    + " ressources (eau, energie, aliments) et de leurs modes d'acheminement,"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-11, comptage direct sur le paquet servi (\"bonneReponse\" et"
                    + " \"distracteurs\") : aucune des 90 propositions ne commence par un chiffre"),
            Map.entry("cm1-histoire-geographie-lire-un-document",
            "10 items sur 10 portent deux distracteurs (trois propositions, 30 au total), mais"
                    + " aucune n'est numerique : ce sont des dates ecrites en toutes lettres ou"
                    + " precedees d'un mot (\"en 1789\"), des natures de document ou des noms"
                    + " d'auteur, valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-11, comptage direct sur le paquet servi (\"bonneReponse\""
                    + " et \"distracteurs\") : aucune des 30 propositions ne commence par un"
                    + " chiffre"),
            // S28-3 : premier paquet d'anglais, matiere neuve du produit, CM1.
            // Motif recompte le 2026-09-10 : le chiffre du 2026-09-04 n'avait pas suivi le lot de
            // s32 qui a porte ce paquet de vingt a vingt-cinq items.
            Map.entry("cm1-anglais",
            "25 items sur 25 portent deux distracteurs (trois propositions), mais aucune des 75"
                    + " propositions n'est numerique : ce sont des mots de vocabulaire anglais ou"
                    + " francais (\"house\", \"chien\", \"castle\"...) et des faits de decouverte"
                    + " culturelle (\"Londres\", \"Big Ben\", \"Thanksgiving\"...), valeurDe rend null"
                    + " sur les trois propositions de chaque item. Mesure du 2026-09-10, comptage"
                    + " direct sur le paquet servi : aucune des 75 propositions ne commence par un"
                    + " chiffre"),
            // S28-4 : second paquet neuf du lot de calcul pose CM1, division euclidienne (quotient
            // et reste), MAT-CM1-05. Le premier paquet du lot, cm1-multiplication-posee, ecrit lui
            // un nombre nu par proposition et reste dans le domaine (aucune entree ici).
            Map.entry("cm1-division-posee",
            "11 items sur 11 ecrivent chaque proposition sous la forme \"quotient reste X\" : la"
                    + " lecture de valeur s'arrete au quotient, et le second nombre qu'elle n'a pas"
                    + " consomme laisse un chiffre dans le reste (ADR-0068, un chiffre reste pour"
                    + " compte), si bien que valeurDe rend null sur les trois propositions de chaque"
                    + " item, y compris quand ce second nombre vaut 0. Mesure du 2026-09-04 sur le"
                    + " paquet servi"),
            // S29-1 : quatrieme et cinquieme paquets de "Questionner le monde" (CP), fermant
            // QLM-CP-02 et QLM-CP-07.
            Map.entry("cp-qlm-cycle-de-vie",
            "11 items sur 11 : cinq items (tache de reconnaissance) portent deux distracteurs"
                    + " (trois propositions) et six items (tache de possession) portent un seul"
                    + " distracteur (deux propositions) - meme motif que cp-qlm-corps-sens et"
                    + " cp-qlm-vivant-non-vivant, additionnes sur un seul paquet. Aucune proposition"
                    + " n'est numerique dans les deux cas : ce sont des expressions du cycle de vie"
                    + " (\"il naît\", \"il se nourrit\", \"il grandit\", \"elle a des petits\", \"elle"
                    + " meurt\") ou une reponse binaire (\"oui\", \"non\"), valeurDe rend null sur"
                    + " toutes les propositions. Mesure du 2026-09-05 sur le paquet servi"),
            Map.entry("cp-qlm-objet-technique",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des fonctions d'objets techniques"
                    + " (\"à couper\", \"à écrire\", \"à balayer\"...) ou des noms de parties (\"la"
                    + " selle\", \"le guidon\", \"la pédale\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-05 sur le paquet servi :"
                    + " aucune des 33 propositions ne commence par un chiffre"),
            // S29-4 : premier lot d'enseignement moral et civique (CP), matiere neuve du produit,
            // restreint aux lignes 1 a 7 du referentiel (arbitrage prealable de nadia-prof).
            Map.entry("cp-emc-regles-de-vie",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des regles de vie de classe et leurs"
                    + " justifications (\"lever la main\", \"pour que chacun parle a son tour\"...),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-05 sur le paquet servi : aucune des 33 propositions ne commence par"
                    + " un chiffre"),
            Map.entry("cp-emc-permis-interdit",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des qualifications de comportements"
                    + " entre eleves (\"une moquerie\", \"une insulte\", \"de la violence\","
                    + " \"un service\", \"une discussion\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-06 sur le paquet servi, apres"
                    + " la reprise du 2026-09-06 (squad/conformite/FICHE-bandit-manchot-permis-"
                    + "interdit-cp.md) : aucune des 33 propositions ne commence par un chiffre"),
            // S29-5 : deux paquets neufs d'enseignement moral et civique (CM1), matiere connue depuis
            // S29-4 mais lignes de referentiel neuves.
            Map.entry("cm1-emc-fait-opinion",
            "11 items sur 11 : six items (tache de classement) portent un seul distracteur (deux"
                    + " propositions) et cinq items (tache de verification) portent deux distracteurs"
                    + " (trois propositions), mais aucune des 27 propositions n'est numerique : ce"
                    + " sont des classifications (\"un fait\", \"une opinion\") ou des methodes de"
                    + " verification en texte libre, valeurDe rend null sur toutes les propositions."
                    + " Mesure du 2026-09-06 sur le paquet servi, apres la reprise du 2026-09-06"
                    + " (squad/conformite/FICHE-bandit-manchot-fait-opinion-cm1.md) qui retire le"
                    + " troisieme bouton \"un mensonge\" : aucune des 27 propositions ne commence par"
                    + " un chiffre"),
            Map.entry("cm1-emc-droit-devoir",
            "11 items sur 11 : six items (tache de classement) portent un seul distracteur (deux"
                    + " propositions) et cinq items (tache de justification) portent deux distracteurs"
                    + " (trois propositions), mais aucune des 27 propositions n'est numerique : ce"
                    + " sont des qualifications (\"un droit\", \"un devoir\") ou des buts en texte"
                    + " libre, valeurDe rend null sur toutes les propositions. Mesure du 2026-09-06 sur"
                    + " le paquet servi, apres la reprise du 2026-09-06 (squad/conformite/FICHE-"
                    + "bandit-manchot-droit-devoir-cm1.md) qui retire le troisieme bouton \"une"
                    + " interdiction\" : aucune des 27 propositions ne commence par un chiffre"),
            // S30-1 : deux paquets neufs de sciences et technologie CM1, matiere neuve du produit,
            // premier lot (SCI-CM1-05, SCI-CM1-17).
            Map.entry("cm1-sciences-signal-information",
            "11 items sur 11 : cinq items (tache de reconnaissance du signal) portent un seul"
                    + " distracteur (deux propositions) et six items (l'item de l'ambulance, meme"
                    + " tache, et les cinq items de la tache d'information) portent deux distracteurs"
                    + " (trois propositions), mais aucune des 28 propositions n'est numerique : ce sont"
                    + " des types de signal (\"un signal visuel\", \"un signal sonore\") ou des"
                    + " informations en texte libre (\"il faut s'arrêter\", \"le four est en train de"
                    + " chauffer\"...), valeurDe rend null sur toutes les propositions. Mesure du"
                    + " 2026-09-06 sur le paquet servi"),
            Map.entry("cm1-sciences-chaine-alimentaire",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'êtres vivants (\"l'écureuil\","
                    + " \"le renard\", \"la chouette\"...) ou des consequences en texte libre (\"il"
                    + " risquerait de manquer de nourriture\", \"ils risqueraient de devenir plus"
                    + " nombreux\"...), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-06 sur le paquet servi, apres la reprise du 2026-09-06"
                    + " (squad/conformite/FICHE-bandit-manchot-chaine-alimentaire-cm1.md) : aucune des"
                    + " 33 propositions ne commence par un chiffre"),
            // S30-2 : deux paquets neufs de sciences et technologie CM1, second lot (SCI-CM1-02,
            // SCI-CM1-12).
            Map.entry("cm1-sciences-melanges",
            "11 items sur 11 : cinq items (tache de classement) portent un seul distracteur (deux"
                    + " propositions) et six items (l'item eau/huile, meme tache, et les cinq items de"
                    + " la tache de separation) portent deux distracteurs (trois propositions), mais"
                    + " aucune des 28 propositions n'est numerique : ce sont des types de mélange"
                    + " (\"un mélange homogène\", \"un mélange hétérogène\") ou des moyens de"
                    + " séparation en texte libre (\"utiliser un filtre\"...), valeurDe rend null sur"
                    + " toutes les propositions. Mesure du 2026-09-06 sur le paquet servi"),
            Map.entry("cm1-sciences-objet-technique",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des fonctions d'objets techniques"
                    + " (\"à se déplacer plus vite et avec moins d'effort\", \"à éclairer dans le"
                    + " noir\"...), des noms de composants (\"la chaîne\", \"la pile\"...) ou une"
                    + " description de fonctionnement en texte libre, valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-06 sur le paquet servi, apres la"
                    + " reprise du 2026-09-06 (squad/conformite/FICHE-bandit-manchot-objet-technique-"
                    + "cm1.md) : aucune des 33 propositions ne commence par un chiffre"),
            // S31-1 : premier lot d'enseignement moral et civique (CP) de s31, restreint aux lignes
            // 1 a 7 du referentiel (EMC-CP-01, EMC-CP-05).
            Map.entry("cp-emc-emotions-autrui",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'émotion (\"de la"
                    + " tristesse\", \"de la joie\", \"de la colère\", \"de la peur\"), valeurDe"
                    + " rend null sur les trois propositions de chaque item. Mesure du 2026-09-07"
                    + " sur le paquet servi : aucune des 33 propositions ne commence par un chiffre"),
            Map.entry("cp-emc-formules-de-politesse",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des formules de politesse (\"bonjour\","
                    + " \"merci\", \"s'il te plaît\") ou des regles d'echange en texte libre (\"écouter"
                    + " sans couper la parole\", \"demander avant de prendre\"...), valeurDe rend null"
                    + " sur les trois propositions de chaque item. Mesure du 2026-09-07 sur le paquet"
                    + " servi : aucune des 33 propositions ne commence par un chiffre"),
            // S31-2 : second lot d'enseignement moral et civique (CP) de s31, restreint aux lignes
            // 1 a 7 du referentiel (EMC-CP-03, EMC-CP-07).
            Map.entry("cp-emc-exclusion",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des classements en texte libre (\"une"
                    + " moquerie qui se répète\", \"un accident isolé\"...) ou des conduites face a"
                    + " une moquerie (\"en parler à un surveillant de la cour\", \"attendre que cela"
                    + " s'arrête tout seul\"...), valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-10 sur le paquet servi, apres S34-EXC-TACHEB :"
                    + " aucune des 33 propositions ne commence par un chiffre"),
            Map.entry("cp-emc-regle-souhait-loi",
            "11 items sur 11 : cinq items (tache de classement, forme binaire depuis le cinquieme"
                    + " passage, S34-EMCCP07) portent un seul distracteur (deux propositions) et six"
                    + " items (les cinq items de la tache de portee de la loi, plus l'item du casque"
                    + " a velo transpose depuis la tache de classement) portent deux distracteurs"
                    + " (trois propositions), mais aucune des 28 propositions n'est numerique : ce"
                    + " sont des categories institutionnelles (\"une règle qui vaut pour tous\", \"un"
                    + " simple souhait\") ou des groupes de personnes en texte libre (\"tout le monde,"
                    + " même les adultes\", \"seulement les enfants\"...), valeurDe rend null sur"
                    + " toutes les propositions. Mesure du 2026-09-10 sur le paquet servi, apres le"
                    + " cinquieme passage : aucune des 28 propositions ne commence par un chiffre"),
            // S31-3 : premier lot de sciences et technologie CM1, second passage (SCI-CM1-01,
            // SCI-CM1-07).
            Map.entry("cm1-sciences-etats-matiere",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des etats de la matiere (\"solide\","
                    + " \"liquide\", \"gaz\") ou des changements d'etat en texte libre (\"la"
                    + " fusion\", \"la vaporisation\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-07 sur le paquet servi : aucune"
                    + " des 33 propositions ne commence par un chiffre"),
            Map.entry("cm1-sciences-regimes-alimentaires",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des regimes alimentaires (\"herbivore\","
                    + " \"carnivore\", \"omnivore\") ou des fonctions de la nourriture en texte"
                    + " libre (\"à lui redonner de l'énergie pour bouger\"...), valeurDe rend null sur"
                    + " les trois propositions de chaque item. Mesure du 2026-09-07 sur le paquet"
                    + " servi : aucune des 33 propositions ne commence par un chiffre"),
            // S31-4 : second lot de sciences et technologie CM1, second passage (SCI-CM1-11,
            // SCI-CM1-08).
            Map.entry("cm1-sciences-familles-materiaux",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des familles de materiau (\"le bois\","
                    + " \"le métal\", \"le verre\"...) ou des materiaux choisis d'apres une"
                    + " propriete en texte libre, valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-07 sur le paquet servi : aucune des 33"
                    + " propositions ne commence par un chiffre"),
            Map.entry("cm1-sciences-etapes-developpement",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'etape (\"la naissance\","
                    + " \"la croissance\", \"la reproduction\"...) ou des caracteristiques d'etape"
                    + " en texte libre (\"il grandit et se développe\"...), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-09-07 sur le paquet servi :"
                    + " aucune des 33 propositions ne commence par un chiffre"),
            // S32-5 : premier lot d'education physique et sportive CP.
            Map.entry("cp-eps-regles-de-securite",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des regles de securite ou des"
                    + " motifs en texte libre (\"attendre l'autorisation de l'adulte\","
                    + " \"un casque\"...), valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-08 sur le paquet servi : aucune des 33"
                    + " propositions ne commence par un chiffre"),
            Map.entry("cp-eps-regles-jeu-collectif",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des regles de jeu, des noms de"
                    + " personnage ou des consequences en texte libre, valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-09-09 sur le paquet servi"
                    + " apres S33-EPS-JEU (les items 7 et 8, jusque-la binaires, portent desormais"
                    + " trois propositions) : aucune des 33 propositions ne commence par un"
                    + " chiffre"),
            // S34-EPS-CORPS-EFFORT : second lot d'education physique et sportive CP.
            Map.entry("cp-eps-corps-effort",
            "22 items sur 22 portent deux distracteurs (trois propositions), mais aucune des 66"
                    + " propositions n'est numerique : ce sont des effets de l'effort sur le corps,"
                    + " des regles d'echauffement, d'hydratation ou de recuperation, ou des"
                    + " consequences en texte libre, valeurDe rend null sur les trois propositions"
                    + " de chaque item. Mesure du 2026-09-10 sur le paquet servi : aucune des 66"
                    + " propositions ne commence par un chiffre"),
            // S33-PAL2-1 (s34) : premier paquet de designation servi, troisieme mecanique du
            // produit. S35-QLM11-PLANCLASSE (s35) : cp-plan-de-la-classe rejoint le meme motif,
            // premiere livraison reelle de QLM-CP-11.
            Map.entry("cp-frise-de-la-journee",
            "11 items sur 11 sont des items de designation (ADR-0080) : ils ne presentent aucune"
                    + " proposition en texte, mais des zones posees sur deux frises - il n'y a ni"
                    + " distracteur ni rang de bonne reponse au sens de cette propriete. C'est la"
                    + " forme d'item, pas son contenu, qui place ce paquet hors du domaine : la"
                    + " garantie qu'un enfant ne gagne pas en ignorant l'enonce est portee ailleurs,"
                    + " par le garde-fou 4 d'ADR-0080 (LaBonneZoneNEstPasPredictibleSurUnPaquetTest)"),
            Map.entry("cp-plan-de-la-classe",
            "6 items sur 6 sont des items de designation (ADR-0080) : memes motifs que"
                    + " cp-frise-de-la-journee, sur le plan de la classe (QLM-CP-11) plutot que sur"
                    + " une frise - il n'y a ni distracteur ni rang de bonne reponse au sens de cette"
                    + " propriete, et la garantie qu'un enfant ne gagne pas en ignorant l'enonce est"
                    + " portee par le garde-fou 4 d'ADR-0080"),
            // S39-ENERGIE : paquet neuf de sciences et technologie CM1 (SCI-CM1-04).
            Map.entry("cm1-sciences-energie",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms de sources d'energie (\"le"
                    + " vent\", \"le soleil\"...), de formes d'energie (\"de la chaleur\", \"de la"
                    + " lumière\"...) ou des transformations en texte libre (\"le vent en énergie"
                    + " électrique\"...), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-22 sur le paquet servi : aucune des 36 propositions ne"
                    + " commence par un chiffre"),
            // S39-OUTILS : paquet neuf d'arts plastiques CP, premiere ligne de cette matiere
            // (ART-CP-11).
            Map.entry("cp-arts-outils",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms d'outils (\"le pinceau\", \"la"
                    + " craie\"...), de supports (\"le carton\", \"l'argile\"...) ou de gestes"
                    + " (\"l'esquisse\", \"le modelage\"...) du travail plastique, valeurDe rend null"
                    + " sur les trois propositions de chaque item. Mesure du 2026-09-22 sur le paquet"
                    + " servi : aucune des 36 propositions ne commence par un chiffre"),
            // S40-HDA : paquet neuf d'histoire des arts CM1, premier de cette matiere (HDA-CM1-05).
            Map.entry("cm1-histoire-des-arts-relier-contexte",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'epoques (\"le Moyen Âge\","
                    + " \"l'Antiquité\"), de civilisations (\"la civilisation grecque antique\"...) ou"
                    + " d'usages (\"c'est un lieu de culte\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-23 sur le paquet servi : aucune"
                    + " des 33 propositions ne commence par un chiffre"),
            // S40-NUTRITION : paquet neuf de sciences et technologie CM1 (SCI-CM1-09).
            Map.entry("cm1-sciences-nutrition",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'organes (\"l'estomac\", \"les"
                    + " poumons\", \"le coeur\") ou des noms de fonctions (\"la digestion\", \"la"
                    + " respiration\", \"la circulation\"), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-23 sur le paquet servi : aucune"
                    + " des 33 propositions ne commence par un chiffre"),
            // S40-REGLES : paquet neuf d'enseignement moral et civique CM1 (EMC-CM1-04).
            Map.entry("cm1-emc-regles-vie-collective",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des actions ou des justifications de la"
                    + " vie collective a l'école en texte libre, valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-23 sur le paquet servi : aucune"
                    + " des 33 propositions ne commence par un chiffre"),
            // S40-LOI : paquet neuf d'enseignement moral et civique CM1 (EMC-CM1-08).
            Map.entry("cm1-emc-regle-loi",
            "11 items sur 11 : six items (tâche de classement) portent un seul distracteur (deux"
                    + " propositions) et cinq items (tâche de portée de la loi) portent deux"
                    + " distracteurs (trois propositions), mais aucune des 27 propositions n'est"
                    + " numerique : ce sont des categories (\"une règle\", \"une loi\") ou des verbes"
                    + " en texte libre (\"elle autorise\", \"elle interdit\", \"elle protège\"),"
                    + " valeurDe rend null sur toutes les propositions. Mesure du 2026-09-23 sur le"
                    + " paquet servi : aucune des 27 propositions ne commence par un chiffre"),
            // S40-DEVISE : paquet neuf d'enseignement moral et civique CP (EMC-CP-09).
            Map.entry("cp-emc-devise",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont la devise et ses variantes (item 1) ou"
                    + " des situations illustrant la liberté, l'égalité et la fraternité en texte"
                    + " libre (items 2 à 11), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-09-23 sur le paquet servi : aucune des 33 propositions ne"
                    + " commence par un chiffre"),
            // S40-EXTRACTION : paquet neuf de sciences CM1, extraction d'information (SCI-CM1-20).
            Map.entry("cm1-sciences-extraire-information",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des extraits de texte (\"du nectar\","
                    + " \"des rayons dangereux du Soleil\"...) ou des valeurs de tableau en texte libre"
                    + " (\"herbivore\", \"solide\"...), valeurDe rend null sur les trois propositions"
                    + " de chaque item. Mesure du 2026-09-23 sur le paquet servi : aucune des 36"
                    + " propositions ne commence par un chiffre"),
            // S41-EMC-ROLES : paquet neuf d'enseignement moral et civique CP (EMC-CP-13).
            Map.entry("cp-emc-roles-ecole-commune",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms de rôles ou de services"
                    + " (\"les pompiers\", \"le médecin\"...) ou des descriptions de leur fonction en"
                    + " texte libre, valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-24 sur le paquet servi : aucune des 33 propositions ne"
                    + " commence par un chiffre"),
            // S41-SCI-CLASSER : paquet neuf de sciences et technologie CM1 (SCI-CM1-06).
            Map.entry("cm1-sciences-classer-etres-vivants",
            "11 items sur 11 portent deux distracteurs (trois propositions), mais aucune des 33"
                    + " propositions n'est numerique : ce sont des noms d'êtres vivants (\"la"
                    + " chauve-souris\", \"l'araignée\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-24 sur le paquet servi :"
                    + " aucune des 33 propositions ne commence par un chiffre"),
            // S41-QLM-HYGIENE : paquet neuf de questionner le monde CP (QLM-CP-04).
            Map.entry("cp-qlm-hygiene",
            "15 items sur 15 portent deux distracteurs (trois propositions), mais aucune des 45"
                    + " propositions n'est numerique : ce sont des regles d'hygiene de vie et leurs"
                    + " effets sur la sante en texte libre (\"pour que le corps se repose et"
                    + " récupère\", \"manger des aliments variés\"...), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-09-24 sur le paquet servi :"
                    + " aucune des 45 propositions ne commence par un chiffre"),
            // S41-QLM-SAISONS : paquet neuf de questionner le monde CP (QLM-CP-13).
            Map.entry("cp-qlm-saisons",
            "16 items, 46 propositions : quatorze items portent deux distracteurs (trois"
                    + " propositions, des noms de mois ou de saisons), deux en portent un seul (deux"
                    + " propositions, la relation d'ordre \"avant\"/\"après\"). Aucune proposition"
                    + " n'est numerique : ce sont des noms de mois (\"janvier\", \"juillet\"...), de"
                    + " saisons (\"l'hiver\", \"l'été\"...) ou une relation d'ordre en texte libre,"
                    + " valeurDe rend null sur toutes les propositions. Mesure du 2026-09-24 sur le"
                    + " paquet servi : aucune des 46 propositions ne commence par un chiffre"),
            // S41-EMC-SYMBOLES : paquet neuf d'enseignement moral et civique CP (EMC-CP-08).
            Map.entry("cp-emc-symboles",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms de symboles de la République"
                    + " (items 1 à 8) ou de courtes phrases sur ce qu'ils représentent (items 9 à 12),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-24 sur le paquet servi : aucune des 36 propositions ne commence par un"
                    + " chiffre"),
            // S41-EMC-DROITS : paquet neuf d'enseignement moral et civique CP (EMC-CP-10).
            Map.entry("cp-emc-droits-enfant",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms de droits de l'enfant (items 1"
                    + " à 8) ou de courtes phrases sur ce que chaque droit garantit (items 9 à 12),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-24 sur le paquet servi : aucune des 36 propositions ne commence par un"
                    + " chiffre"),
            // S42-MAT-TEXTUEL : paquet neuf de mathematiques CP, moitie textuelle de MAT-CP-10.
            Map.entry("cp-deplacements-quadrillage",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont les quatre formes de deplacement en"
                    + " texte libre (\"avancer\", \"reculer\", \"tourner à gauche\", \"tourner à"
                    + " droite\"), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-24 sur le paquet servi : aucune des 36 propositions ne"
                    + " commence par un chiffre"),
            // S42-ART-COULEURS : paquet neuf d'arts plastiques CP (ART-CP-05).
            Map.entry("cp-arts-couleurs",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms de couleurs (\"bleu\","
                    + " \"jaune\", \"rouge\", \"vert\", \"orange\", \"violet\"), valeurDe rend null"
                    + " sur les trois propositions de chaque item. Mesure du 2026-09-24 sur le paquet"
                    + " servi : aucune des 36 propositions ne commence par un chiffre"),
            // S42-EMC-SYMBOLES-CM1 : paquet neuf d'enseignement moral et civique CM1 (EMC-CM1-05).
            Map.entry("cm1-emc-symboles",
            "13 items sur 13 portent deux distracteurs (trois propositions), mais aucune des 39"
                    + " propositions n'est numerique : ce sont des periodes historiques (\"la"
                    + " Révolution française\", \"la Troisième République\"...), des lieux ou"
                    + " contextes d'usage en texte libre, ou des noms de symboles (\"le drapeau\","
                    + " \"la devise\", \"Marianne\", \"la Marseillaise\", \"le 14 juillet\"),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-24 sur le paquet servi : aucune des 39 propositions ne commence par"
                    + " un chiffre"),
            // S42-QLM-MATIERE : paquet neuf de questionner le monde CP (QLM-CP-06).
            Map.entry("cp-qlm-matiere",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont des noms de matieres (\"bois\", \"métal\","
                    + " \"le verre\"...), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-09-24 sur le paquet servi (\"bonneReponse\" et"
                    + " \"distracteurs\") : aucune des 33 propositions ne commence par un chiffre"),
            // S43-QLM-NUMERIQUE : paquet neuf de questionner le monde CP (QLM-CP-08).
            Map.entry("cp-qlm-objets-numeriques",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont des noms d'objets (\"une tablette\", \"un"
                    + " livre\"...) ou des consequences factuelles en texte libre (\"parce que sa"
                    + " batterie se vide en fonctionnant\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-09-25 sur le paquet servi"
                    + " (\"bonneReponse\" et \"distracteurs\") : aucune des 33 propositions ne"
                    + " commence par un chiffre"),
            // S43-EPS-EFFETS-ACTIVITE : premier paquet d'education physique et sportive CM1
            // (EPS-CM1-10).
            Map.entry("cm1-eps-effets-activite",
            "13 items sur 13 portent deux distracteurs (trois propositions, 39 au total), mais"
                    + " aucune n'est numerique : ce sont des causes physiologiques, des descriptions"
                    + " d'efforts, des noms d'effets ou des conduites de securite en texte libre"
                    + " (\"les muscles ont besoin de plus d'oxygène\", \"une course longue qui dure"
                    + " toute la séance\", \"la soif\", \"arrêter l'effort, s'asseoir et boire\"...),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-09-25 sur le paquet servi : aucune des 39 propositions ne commence par"
                    + " un chiffre"),
            // S44-MUS-VOCABULAIRE : premier paquet d'education musicale CP (MUS-CP-11).
            Map.entry("cp-musique-vocabulaire",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont les quatre mots du vocabulaire musical"
                    + " (\"le refrain\", \"le couplet\", \"la pulsation\", \"le silence\") ou leurs"
                    + " definitions en texte libre, valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-09-30 sur le paquet servi : aucune des 33"
                    + " propositions ne commence par un chiffre"),
            // S44-SCI-OBJET-TECHNIQUE : paquet neuf, SCI-CM1-13 (evolution d'un objet technique).
            Map.entry("cm1-sciences-evolution-objet-technique",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont des relations entre un etat ancien et un"
                    + " etat recent d'un objet, en texte libre (\"le bois a laissé la place au"
                    + " plastique\", \"on s'en sert pour écrire\", \"il faut que le vent souffle pour"
                    + " avancer\"...), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-09-30 sur le paquet servi : aucune des 33 propositions ne"
                    + " commence par un chiffre"),
            // S45-MUS-INSTRUMENTS : second paquet d'education musicale CP (MUS-CP-05).
            Map.entry("cp-musique-instruments",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont les trois familles d'instruments (\"les"
                    + " cordes\", \"les vents\", \"les percussions\") ou des noms d'instruments (\"la"
                    + " guitare\", \"le violon\", \"la trompette\"...), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-10-01 sur le paquet servi :"
                    + " aucune des 33 propositions ne commence par un chiffre"),
            // S45-SCI-NUMERIQUE : paquet neuf, SCI-CM1-14 (regles d'usage d'un environnement
            // numerique).
            Map.entry("cm1-sciences-usages-numeriques",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune ne commence par un chiffre : deux bonnes reponses portent une valeur"
                    + " numerique en milieu de texte (\"à partir de 13 ans\", \"un mot de passe"
                    + " d'au moins 8 caractères...\"), les 34 autres propositions sont du texte"
                    + " libre sans aucun chiffre, valeurDe rend null sur les trois propositions de"
                    + " chaque item (le motif NOMBRE est ancre en debut de proposition). Mesure du"
                    + " 2026-10-01 sur le paquet servi : aucune des 36 propositions ne commence par"
                    + " un chiffre"),
            // S45-EPS-ROLES : paquet neuf, EPS-CM1-09 (tenir les roles d'arbitre, d'observateur ou
            // de marqueur).
            Map.entry("cm1-eps-roles",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total). Les huit"
                    + " items d'arbitre et d'observateur portent des propositions en texte libre"
                    + " (equipes, actes d'observation), aucune numerique. Les quatre items de marqueur"
                    + " portent un score au format \"X à Y\" (\"4 à 4\", \"10 à 11\"...) : lireValeur y"
                    + " lit le premier nombre puis trouve un chiffre dans le reste (le second nombre"
                    + " du score), donc rend null, meme motif qu'ADR-0068 sur cp-ranger-nombres."
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-10-01 sur le paquet servi : zero item classe sur douze"),
            // S46-EMC-INSTITUTIONS-CM1 : paquet neuf d'enseignement moral et civique CM1
            // (EMC-CM1-09).
            Map.entry("cm1-emc-institutions",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms d'institution (\"la mairie\","
                    + " \"le conseil municipal\", \"l'école\") ou des descriptions de leur fonction en"
                    + " texte libre, valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-10-01 sur le paquet servi : aucune des 36 propositions ne"
                    + " commence par un chiffre"),
            // S46-SCI-PHENOMENES-CM1 : paquet neuf de sciences et technologie CM1 (SCI-CM1-16).
            Map.entry("cm1-sciences-phenomenes",
            "12 items sur 12 portent deux distracteurs (trois propositions), mais aucune des 36"
                    + " propositions n'est numerique : ce sont des noms de phenomenes (\"un séisme\","
                    + " \"une tempête\"...) ou des gestes de protection en texte libre, valeurDe rend"
                    + " null sur les trois propositions de chaque item. Mesure du 2026-10-01 sur le"
                    + " paquet servi : aucune des 36 propositions ne commence par un chiffre"),
            // S51-SCI-TERRE-CM1 : paquet neuf de sciences et technologie CM1 (SCI-CM1-15).
            Map.entry("cm1-sciences-terre",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des astres (\"le Soleil\", \"la Lune\") ou des"
                    + " explications en texte libre (\"la Terre tourne sur elle-même\"...), la seule"
                    + " duree est ecrite en toutes lettres (\"environ vingt-quatre heures\"), valeurDe"
                    + " rend null sur les trois propositions de chaque item. Mesure du 2026-10-06 sur"
                    + " le paquet servi : aucune des 36 propositions ne commence par un chiffre"),
            // S51-SCI-CORPS-CM1 : paquet neuf de sciences et technologie CM1 (SCI-CM1-10).
            Map.entry("cm1-sciences-corps",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des conduites (\"tousser dans son coude\"...)"
                    + " ou des fonctions (\"à construire des os et des dents solides\"...) en texte"
                    + " libre, sans chiffre, valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-10-06 sur le paquet servi : aucune des 36 propositions ne"
                    + " commence par un chiffre"),
            // S51-SCI-ACTIVITE-HUMAINE-CM1 : paquet neuf de sciences et technologie CM1
            // (SCI-CM1-18).
            Map.entry("cm1-sciences-activite-humaine",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des effets (\"ils perdent leur abri et leur"
                    + " nourriture\"...) ou des gestes (\"réparer les canalisations qui fuient\"...)"
                    + " en texte libre, sans chiffre, valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-10-06 sur le paquet servi : aucune des 36"
                    + " propositions ne commence par un chiffre"),
            // S46-EMC-EMOTION-CM1 : paquet neuf, EMC-CM1-01 (identifier une emotion, distinguer
            // ressentir et agir).
            Map.entry("cm1-emc-emotion-ressentir-agir",
            "11 items sur 11 portent deux distracteurs (trois propositions, 33 au total), mais"
                    + " aucune n'est numerique : ce sont des noms d'emotion (\"de la fierté\", \"de"
                    + " la colère\"...) ou des gestes decrits en texte libre (\"il range calmement"
                    + " les pièces du jeu\"...), valeurDe rend null sur les trois propositions de"
                    + " chaque item. Mesure du 2026-10-01 sur le paquet servi : aucune des 33"
                    + " propositions ne commence par un chiffre"),
            // S46-SCI-MOUVEMENT-CM1 : paquet neuf, SCI-CM1-03 (decrire un mouvement par sa
            // trajectoire et sa vitesse). Refus d'avance tenu : aucun item ne calcule une vitesse,
            // une duree ou une longueur, donc aucune proposition n'est numerique.
            Map.entry("cm1-sciences-mouvement",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des formes de trajectoire (\"une"
                    + " trajectoire circulaire\"...), des reperes (\"le quai de la gare\"...) ou des"
                    + " comparaisons de vitesse en texte libre (\"le cycliste\", \"ils vont à la"
                    + " même vitesse\"...), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-10-01 sur le paquet servi : aucune des 36 propositions"
                    + " ne commence par un chiffre"),
            // S47-LVE-CULTURE-CP : paquet neuf, LVE-CP-06 (decouverte culturelle, langues vivantes
            // anglais CP). Lot textuel en francais seul (Q5) : aucune proposition n'est numerique.
            Map.entry("cp-anglais-culture",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des noms de pays (\"le Royaume-Uni\","
                    + " \"les États-Unis\"...), des noms de lieux (\"la Tamise\", \"la statue de la"
                    + " Liberté\"...) ou des descriptions en texte libre (\"de la dinde\", \"on"
                    + " allume de grands feux et des feux d'artifice\"...), valeurDe rend null sur"
                    + " les trois propositions de chaque item. Mesure du 2026-10-02 sur le paquet"
                    + " servi : aucune des 36 propositions ne commence par un chiffre"),
            // S47-EMC-VOTE-CM1 : paquet neuf, EMC-CM1-10 (sens du vote et de la representation).
            Map.entry("cm1-emc-vote-election",
            "12 items sur 12 portent deux distracteurs (trois propositions, 36 au total), mais"
                    + " aucune n'est numerique : ce sont des termes du libelle (\"le délégué de"
                    + " classe\", \"une élection\", \"le suffrage universel\"), des noms"
                    + " d'institution (\"le conseil municipal\"...) ou des descriptions en texte"
                    + " libre, y compris la seule proposition qui porte un nombre ecrit en toutes"
                    + " lettres (\"six ans\", jamais en chiffres), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-10-02 sur le paquet servi :"
                    + " aucune des 36 propositions ne commence par un chiffre"),
            // S47-QLM-AUTREFOIS : paquet neuf de questionner le monde CP (QLM-CP-15).
            Map.entry("cp-qlm-autrefois-aujourdhui",
            "14 items, 35 propositions : sept items (tache de rattachement) portent un seul"
                    + " distracteur (deux propositions, les deux epoques \"autrefois\"/\"aujourd'hui\"),"
                    + " sept items (tache de comparaison) portent deux distracteurs (trois"
                    + " propositions, des noms d'objets et de pratiques du quotidien). Aucune"
                    + " proposition n'est numerique. Mesure du 2026-10-02, comptage direct sur le"
                    + " paquet servi (\"bonneReponse\" et \"distracteurs\")"),
            // S49-FRA-GRAPHEMES : premier paquet de francais CE1 (FRA-CE1-01).
            Map.entry("ce1-graphemes-complexes",
            "24 items sur 24 portent deux distracteurs (trois propositions), mais aucune des 72"
                    + " propositions n'est numerique : ce sont des mots a lire (la bonne reponse et"
                    + " deux distracteurs), valeurDe rend null sur les trois propositions de chaque"
                    + " item. Mesure du 2026-10-03 sur le paquet servi : aucune des 72 propositions"
                    + " ne commence par un chiffre"),
            // S50-FRA-VALEUR-LETTRE : second paquet de francais CE1 (FRA-CE1-02).
            Map.entry("ce1-valeur-lettre",
            "24 items sur 24 portent un seul distracteur (deux propositions, forme binaire de"
                    + " TRV-CE1-2), soit 48 propositions, mais aucune n'est numerique : ce sont les"
                    + " six mots de reference de VLE-CE1-2 (\"car\", \"cerise\", \"gare\","
                    + " \"girafe\", \"sel\", \"rose\"), valeurDe rend null sur les deux"
                    + " propositions de chaque item. Mesure du 2026-10-03 sur le paquet servi :"
                    + " aucune des 48 propositions ne commence par un chiffre"),
            // S50-FRA-HOMOPHONES : troisieme paquet de francais CE1 (FRA-CE1-16).
            Map.entry("ce1-homophones",
            "24 items sur 24 portent un seul distracteur (deux propositions, forme binaire de"
                    + " TRV-CE1-2), soit 48 propositions, mais aucune n'est numerique : ce sont les"
                    + " quatre homophones de HOM-CE1-1 (\"a\", \"à\", \"et\", \"est\"), valeurDe"
                    + " rend null sur les deux propositions de chaque item. Mesure du 2026-10-04 sur"
                    + " le paquet servi : aucune des 48 propositions ne commence par un chiffre"),
            // S51-FRA-TEMPS : quatrieme paquet de francais CE1 (FRA-CE1-20).
            Map.entry("ce1-temps",
            "24 items sur 24 portent deux distracteurs (trois propositions), soit 72 propositions,"
                    + " mais aucune n'est numerique : ce sont les trois noms de temps de TPS-CE1-1"
                    + " (\"passé\", \"présent\", \"futur\"), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-10-06 sur le paquet servi :"
                    + " aucune des 72 propositions ne commence par un chiffre"),
            // S51-FRA-ALPHABETIQUE : cinquieme paquet de francais CE1 (FRA-CE1-23).
            Map.entry("ce1-ordre-alphabetique",
            "24 items sur 24 portent deux distracteurs (trois propositions), soit 72 propositions,"
                    + " mais aucune n'est numerique : ce sont des mots a ranger (\"lapin\","
                    + " \"ballon\"...) ou des listes de trois mots (\"lapin, loup, lune\"),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-10-06 sur le paquet servi : aucune des 72 propositions ne commence par"
                    + " un chiffre"),
            // S51-FRA-GRAPHIE : sixieme paquet de francais CE1 (FRA-CE1-22).
            Map.entry("ce1-graphie",
            "24 items sur 24 portent un seul distracteur (deux propositions, forme binaire de"
                    + " TRV-CE1-2), soit 48 propositions, mais aucune n'est numerique : ce sont un"
                    + " mot et sa graphie fautive (\"une jambe\" et \"une janbe\"...), valeurDe"
                    + " rend null sur les deux propositions de chaque item. Mesure du 2026-10-06 sur"
                    + " le paquet servi : aucune des 48 propositions ne commence par un chiffre"),
            // S52-FRA-CATEGORIES : septieme paquet de francais CE1 (FRA-CE1-26).
            Map.entry("ce1-categories",
            "24 items sur 24 portent deux distracteurs (trois propositions), soit 72 propositions,"
                    + " mais aucune n'est numerique : ce sont des noms de categories (\"fruit\","
                    + " \"meuble\"...), des mots de la vie courante (\"une fraise\", \"un jardin\"...),"
                    + " valeurDe rend null sur les trois propositions de chaque item. Mesure du"
                    + " 2026-10-06 sur le paquet servi : aucune des 72 propositions ne commence par"
                    + " un chiffre"),
            // S52-FRA-PRESENT : huitieme paquet de francais CE1 (FRA-CE1-17).
            Map.entry("ce1-conjugaison-present",
            "24 items sur 24 portent deux distracteurs (trois propositions), soit 72 propositions,"
                    + " mais aucune n'est numerique : ce sont des formes conjuguees (\"suis\","
                    + " \"viennent\"...) ou des infinitifs (\"aller\"...), valeurDe rend null sur les"
                    + " trois propositions de chaque item. Mesure du 2026-10-06 sur le paquet servi :"
                    + " aucune des 72 propositions ne commence par un chiffre"),
            // S52-FRA-SYNONYMES-CONTRAIRES : neuvieme paquet de francais CE1 (FRA-CE1-25).
            Map.entry("ce1-synonymes-contraires",
            "24 items sur 24 portent deux distracteurs (trois propositions), soit 72 propositions,"
                    + " mais aucune n'est numerique : ce sont des mots de la vie courante"
                    + " (\"joyeux\", \"basse\", \"vite\"...), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-10-06 sur le paquet servi :"
                    + " aucune des 72 propositions ne commence par un chiffre"),
            // S51-MAT-SYMETRIE : quatrieme paquet de designation servi, MAT-CE1-17.
            Map.entry("ce1-symetrie",
            "11 items sur 11 sont des items de designation (ADR-0080) : ils ne presentent aucune"
                    + " proposition en texte, mais des zones posees sur une figure ou sur un quadrillage -"
                    + " il n'y a ni distracteur ni rang de bonne reponse au sens de cette propriete. La"
                    + " garantie qu'un enfant ne gagne pas en ignorant l'enonce est portee par le"
                    + " garde-fou 4 d'ADR-0080 (LaBonneZoneNEstPasPredictibleSurUnPaquetTest)"),
            // S51-MAT-COMPARER-RANGER : paquet neuf de mathematiques CE1 (MAT-CE1-02, moitie
            // textuelle).
            Map.entry("ce1-comparer-ranger",
            "34 items sur 34 portent deux distracteurs (trois propositions), soit 102 propositions,"
                    + " mais aucune n'est numerique : ce sont des listes de trois nombres (\"159, 533,"
                    + " 547\"), l'un des trois signes de comparaison (\"<\", \">\", \"=\") ou un"
                    + " encadrement (\"340 et 350\"), valeurDe rend null sur les trois propositions de"
                    + " chaque item (ADR-0068 : un chiffre reste apres le premier nombre). Mesure du"
                    + " 2026-10-06 sur le paquet servi : aucune des 102 propositions n'a de valeur"
                    + " scalaire"),
            // S50-ART : paquet neuf d'arts plastiques CP, deuxieme ligne de cette matiere
            // (ART-CP-09), premier paquet a declarer une reproduction d'oeuvre (T6 d'ADR-0082).
            Map.entry("cp-decrire-une-oeuvre",
            "8 items sur 8 portent deux distracteurs (trois propositions), soit 24 propositions,"
                    + " mais aucune n'est numerique : ce sont des noms de sujets, de formes ou de"
                    + " materiaux observes sur une reproduction d'oeuvre (\"une chambre\", \"un"
                    + " visage\", \"une statue\"...), valeurDe rend null sur les trois propositions"
                    + " de chaque item. Mesure du 2026-10-04 sur le paquet servi : aucune des 24"
                    + " propositions ne commence par un chiffre"),
            // S55-ART-CP-10-PAQUET : premier paquet a propositions en image d'arts plastiques CP
            // (ART-CP-10, ADR-0104).
            Map.entry("cp-rapprocher-deux-oeuvres",
            "6 items sur 6 portent deux distracteurs (trois propositions), soit 18 propositions,"
                    + " mais aucune n'est numerique : ce sont des identifiants de reproduction"
                    + " d'oeuvre (\"oeuvre-chambre\", \"oeuvre-champ-de-ble\"...), jamais lus par"
                    + " l'enfant, qui voit l'image (ADR-0104 decision 4). Mesure du 2026-10-09 sur le"
                    + " paquet servi : le garde-fou classe 0 item sur 6 et aucune des 18 propositions"
                    + " ne commence par un chiffre"));

    private enum Rang {
        LA_PLUS_PETITE, INTERMEDIAIRE, LA_PLUS_GRANDE
    }

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les items distincts (par identifiant) que ce paquet rend sur un budget de tirages, en s'arretant
     * plus tot si le paquet est fini (Optional.empty()) ou si plus rien de neuf n'apparait pendant
     * TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET tirages consecutifs (paquet combinatoire epuise en pratique).
     */
    static List<Item> tousLesItemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(5)::nextInt;
        Set<ItemId> dejaVus = new java.util.HashSet<>();
        Map<ItemId, Item> items = new LinkedHashMap<>();
        int tiragesSansNouveaute = 0;
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES
                && tiragesSansNouveaute < TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            dejaVus.add(item.id());
            if (items.put(item.id(), item) == null) {
                tiragesSansNouveaute = 0;
            } else {
                tiragesSansNouveaute++;
            }
        }
        return List.copyOf(items.values());
    }

    /**
     * Les memes items, bornes a ceux qui presentent des propositions - la population de la propriete
     * de rang. La separation date du 2026-09-10 : {@link #comptesPerimesDesMotifs} compte des items
     * et des propositions ecrits dans un motif d'exemption, et un motif qui parle d'un paquet de
     * saisie parle bien de ses items, que cette borne-ci ecarte.
     */
    static List<Question> itemsTires(ContentSource contenu) {
        // Lot 5a de s24, decision 10 : borne aux items qui presentent des propositions. La propriete
        // decrit une STRATEGIE DE PRESSION - "une strategie qui choisit toujours le meme rang parmi
        // les trois propositions, sans jamais lire l'enonce". En saisie il n'y a ni rang, ni bouton,
        // ni ordre a exploiter, et la garantie n'est pas perdue : elle est obtenue par construction,
        // nadia-prof l'ayant chiffree - un enfant qui ne sait rien obtient 7 manches sur 7 avec des
        // boutons, et au plus trois sur onze par manche en saisie. Un paquet entierement de saisie
        // rend ici une liste vide, ce que les appelants traitent deja comme "hors du domaine, aucun
        // taux calcule et rien d'affirme" (le meme sort qu'un paquet sans item numerique).
        return ClassementDesItems.presentantDesPropositions(tousLesItemsTires(contenu));
    }

    /**
     * La duree ou l'instant lu par {@link #DUREE_HEURES} ou {@link #DUREE_MINUTES}, converti en
     * minutes, ou {@code null} si le texte n'est ni l'un ni l'autre.
     */
    private static Long dureeEnMinutes(String texte) {
        Matcher heures = DUREE_HEURES.matcher(texte);
        if (heures.matches()) {
            long h = Long.parseLong(heures.group(1));
            long min = heures.group(2) == null ? 0 : Long.parseLong(heures.group(2));
            return h * 60 + min;
        }
        Matcher etDemie = DUREE_ET_DEMIE.matcher(texte);
        if (etDemie.matches()) {
            return Long.parseLong(etDemie.group(1)) * 60 + 30;
        }
        Matcher minutes = DUREE_MINUTES.matcher(texte);
        if (minutes.matches()) {
            return Long.valueOf(minutes.group(1));
        }
        return null;
    }

    /**
     * Une decomposition en valeurs de position ecrite en toutes lettres par le verbe "decomposer"
     * ("40 000 + 700 + 5") : la valeur est le total, jamais le seul premier terme. Sans cette
     * lecture, {@link #NOMBRE} s'arrete au premier "+" et rend 40 000 pour "40 000 + 700 + 5" -
     * exactement ce qui rend ce garde-fou muet des qu'un distracteur de decomposition ne fait varier
     * qu'un terme apres le premier, la forme que nadia-prof exige (REFUS du 2026-08-15 sur
     * cm1-grands-nombres, S13-1) : les trois propositions partagent alors le meme premier terme et se
     * liraient a la meme valeur sans qu'aucun diff ne le montre.
     *
     * <p>{@code null} si le texte n'a qu'un seul terme (pas de "+", ce n'est pas une somme - la
     * lecture retombe alors sur {@link #NOMBRE}) ou si un des termes n'est pas un nombre entier
     * complet une fois separe : une somme mal formee n'a pas de valeur plutot qu'une valeur
     * partielle.</p>
     */
    private static Long sommeDeTermes(String texte) {
        String[] termes = SEPARATEUR_DE_SOMME.split(texte, -1);
        if (termes.length < 2) {
            return null;
        }
        long total = 0;
        for (String terme : termes) {
            if (!NOMBRE_COMPLET.matcher(terme).matches()) {
                return null;
            }
            total += Long.parseLong(SEPARATEUR_DE_MILLIERS.matcher(terme).replaceAll(""));
        }
        return total;
    }

    /**
     * Ce que la lecture de valeur a fait d'une proposition : la {@code valeur} qu'elle en tire, et
     * le {@code reste} - le texte que cette lecture-la <b>n'a pas consomme</b>, une fois debarrasse
     * de ses blancs de bord.
     *
     * <p>Le reste n'est pas une seconde lecture de la proposition : c'est le complement exact de la
     * premiere. Sur {@code "20 cm"} il vaut {@code "cm"} et le mot "unite" le decrit bien ; sur
     * {@code "40 000 + 700 + 5"}, sur {@code "1 h 30"} et sur {@code "40 705"} il est <b>vide</b>,
     * parce que la lecture consomme ces trois ecritures en entier. Sur {@code "600 000 - 40"} il
     * vaut {@code "- 40"}, parce que la lecture s'arrete au premier nombre et ignore le reste de
     * l'expression - un reste non vide dit exactement cela, et rien d'autre : la lecture n'a pas
     * tout lu.</p>
     *
     * <p>ADR-0041 amende le 2026-08-15 : c'est ce reste, et non un motif ecrit a cote, que
     * {@link LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest} exige identique entre les trois
     * propositions d'un item. Deux lectures qui derivent l'une de l'autre finissent par diverger ;
     * celle-ci et la sienne n'en font qu'une.</p>
     */
    record Lue(long valeur, String reste) {
    }

    /**
     * La lecture d'une proposition : une duree convertie en minutes si elle en a l'ecriture, sinon
     * le total d'une somme de termes si elle en a la forme, sinon un nombre entier une fois ses
     * separateurs de milliers retires, sinon {@code null} - hors du domaine de cette propriete, pas
     * un defaut. Les durees sont vues avant les nombres nus : voir la Javadoc de
     * {@link #DUREE_HEURES}. La somme est vue avant le nombre nu pour la meme raison que la duree
     * est vue avant elle : lire le nombre en premier ferait valoir 40 000 la decomposition
     * "40 000 + 700 + 5", voir {@link #sommeDeTermes}.
     *
     * <p>Les deux premiers cas sont ancres sur toute la proposition : leur reste est vide par
     * construction. Seul le troisieme peut laisser un reste, et c'est precisement le cas ou la
     * lecture n'a lu qu'un prefixe.</p>
     *
     * <p>ADR-0041 : cette lecture est la <b>seule</b> du depot. Elle est visible du paquet pour que
     * {@link LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest} garde ses deux angles morts - le
     * {@code null} silencieux et le reste ignore - sur la lecture reelle et non sur une copie qui
     * deriverait de celle-ci.</p>
     *
     * <p><b>La somme de termes est ratifiee par ADR-0041</b> (amendement du 2026-08-15, cas 2 de la
     * decision) : elle n'etait pas dans le perimetre ferme par la version initiale de cet ADR et y
     * vivait comme une correction locale en attente de doctrine.</p>
     *
     * <p><b>ADR-0068, le 2026-08-30 : une lecture qui laisse un chiffre derriere elle n'a rien
     * lu.</b> Le troisieme cas rendait jusqu'ici une valeur des qu'une proposition commencait par
     * un chiffre, quel que soit ce qu'il en restait. Sur une proposition qui ecrit plusieurs
     * nombres et que ni la duree ni la somme ne consomment - {@code "32, 33, 40"}, une liste a
     * ranger - il rendait 32 et presentait cette valeur comme celle de la proposition entiere.
     * Une telle proposition n'a pas de valeur comparable : elle sort du domaine
     * ({@code null}), comme un mot ou un symbole, plutot que d'en recevoir une fausse. Voir
     * {@link #unChiffreEstRestePourCompte}.</p>
     */
    static Lue lireValeur(String proposition) {
        String texte = proposition.strip();
        Long duree = dureeEnMinutes(texte);
        if (duree != null) {
            return new Lue(duree, "");
        }
        Long somme = sommeDeTermes(texte);
        if (somme != null) {
            return new Lue(somme, "");
        }
        Matcher matcher = NOMBRE.matcher(texte);
        if (!matcher.find()) {
            return null;
        }
        String reste = texte.substring(matcher.end()).strip();
        if (unChiffreEstRestePourCompte(reste)) {
            return null;
        }
        String sansSeparateur = SEPARATEUR_DE_MILLIERS.matcher(matcher.group()).replaceAll("");
        return new Lue(Long.parseLong(sansSeparateur), reste);
    }

    /**
     * Vrai si le reste d'une lecture porte encore un chiffre - ADR-0068. La lecture s'est alors
     * arretee sur un prefixe d'une proposition qui ecrit plusieurs nombres, et la valeur qu'elle
     * rendrait serait celle d'un seul d'entre eux, presentee comme celle de la proposition entiere.
     *
     * <p>Le critere est le chiffre, jamais la forme qui le porte : une liste, une fraction, un
     * intervalle ou une ecriture decimale a la virgule sont attrapees par la meme ligne, sans
     * qu'aucune ne soit nommee (ADR-0039). Une unite ecrite ({@code "20 cm"}) ne porte pas de
     * chiffre et reste dans le domaine, ou la clause du reste d'ADR-0041 la garde deja.</p>
     */
    private static boolean unChiffreEstRestePourCompte(String reste) {
        for (int position = 0; position < reste.length(); position++) {
            if (Character.isDigit(reste.charAt(position))) {
                return true;
            }
        }
        return false;
    }

    /** La seule valeur comparable d'une proposition, ou {@code null}. Voir {@link #lireValeur}. */
    static Long valeurDe(String proposition) {
        Lue lue = lireValeur(proposition);
        return lue == null ? null : lue.valeur();
    }

    /**
     * Vrai si deux des trois valeurs au moins coincident. Un rang suppose un ordre total sur trois
     * valeurs distinctes ; une egalite le rend indefini, et le lire quand meme classerait l'item
     * silencieusement "la plus petite" a chaque fois (aucun distracteur n'est strictement inferieur
     * a la bonne reponse des qu'il lui est egal). Voir {@link #egaliteDe} : sur la production, ce
     * n'est jamais une exclusion silencieuse, c'est un echec du garde-fou dedie.
     */
    private static boolean valeursEnConflit(long bonne, long d1, long d2) {
        return bonne == d1 || bonne == d2 || d1 == d2;
    }

    /**
     * Le rang de la bonne reponse parmi les trois propositions, ou {@code null} si l'item n'a pas
     * exactement deux distracteurs, si l'une des trois propositions n'est pas numerique - hors du
     * domaine de cette propriete, pas un defaut - ou si deux de ses valeurs coincident : un rang
     * suppose alors un ordre indefini plutot qu'une exclusion silencieuse (voir
     * {@link #valeursEnConflit} et {@link #egaliteDe}).
     */
    private static Rang rangDe(Question item) {
        if (item.distracteurs().size() != 2) {
            return null;
        }
        Long bonne = valeurDe(item.bonneReponse());
        Long d1 = valeurDe(item.distracteurs().get(0));
        Long d2 = valeurDe(item.distracteurs().get(1));
        if (bonne == null || d1 == null || d2 == null) {
            return null;
        }
        if (valeursEnConflit(bonne, d1, d2)) {
            return null;
        }
        int rang = (d1 < bonne ? 1 : 0) + (d2 < bonne ? 1 : 0);
        return switch (rang) {
            case 0 -> Rang.LA_PLUS_PETITE;
            case 1 -> Rang.INTERMEDIAIRE;
            default -> Rang.LA_PLUS_GRANDE;
        };
    }

    private record Repartition(int petite, int intermediaire, int grande) {
        int total() {
            return petite + intermediaire + grande;
        }

        int meilleureStrategie() {
            return Math.max(petite, Math.max(intermediaire, grande));
        }

        int rangLePlusRare() {
            return Math.min(petite, Math.min(intermediaire, grande));
        }
    }

    private static Repartition repartitionDe(List<Question> items) {
        int petite = 0;
        int intermediaire = 0;
        int grande = 0;
        for (Question item : items) {
            Rang rang = rangDe(item);
            if (rang == null) {
                continue;
            }
            switch (rang) {
                case LA_PLUS_PETITE -> petite++;
                case INTERMEDIAIRE -> intermediaire++;
                case LA_PLUS_GRANDE -> grande++;
            }
        }
        return new Repartition(petite, intermediaire, grande);
    }

    static void verifierQueLeRangNEstPasPredictible(Path racine, String racineClasspath,
                                                      Set<String> dette) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            if (dette.contains(paquet)) {
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0) {
                continue;
            }
            double ratio = (double) repartition.meilleureStrategie() / repartition.total();
            if (ratio > SEUIL_MAJORITE) {
                defauts.add(String.format(
                        "paquet %s : sur %d items a trois propositions numeriques, une strategie a"
                                + " rang constant (petite=%d, intermediaire=%d, grande=%d) gagne %.1f %%"
                                + " des manches sans lire l'enonce, au-dessus du seuil de majorite"
                                + " (%.1f %%)",
                        paquet, repartition.total(), repartition.petite(), repartition.intermediaire(),
                        repartition.grande(), 100 * ratio, 100 * SEUIL_MAJORITE));
            }
        }

        assertThat(defauts)
                .as("aucune strategie qui ignore l'enonce et choisit toujours le meme rang ne doit"
                        + " gagner la majorite des manches d'un paquet")
                .isEmpty();
    }

    @Test
    void leRangDeLaBonneReponseNEstPasPredictibleSurLesPaquetsDeProduction() {
        verifierQueLeRangNEstPasPredictible(CONTENU_DE_PRODUCTION, "", DETTE_DE_RANG);
    }

    /**
     * <b>ADR-0051 : aucun paquet n'est ecarte en silence du calcul de rang.</b>
     *
     * <p>Defaut trouve par {@code lea-dev} sur un paquet de synonymes neuf, verifie et etendu par
     * {@code yanis-qa} (rapport qualite de s17, point 1) :
     * {@link #verifierQueLeRangNEstPasPredictible} porte {@code if (repartition.total() == 0)
     * continue;}, et un paquet dont aucun item ne rend trois valeurs comparables traverse donc ce
     * garde-fou sans etre juge, sans qu'aucune ligne du depot ne le dise. Le garde-fou frere,
     * {@code LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest}, declare deja ses exclusions
     * <b>partielles</b> ({@code PAQUETS_A_ITEMS_ECARTES}) ; celui-ci n'avait rien pour les exclusions
     * <b>totales</b>, qui sont pourtant les seules a ne laisser aucune trace.</p>
     *
     * <p><b>Ce que cette clause ne fait pas, et il faut le lire avant ce qu'elle fait</b> : elle ne
     * garde pas la propriete sur du texte. Elle ne la comble pas, elle la rend <b>comptable</b>.
     * ADR-0051 dit pourquoi elle ne la comble pas : sur des propositions textuelles il n'existe aucun
     * ordre intrinseque, et toute strategie qu'on encoderait (la plus longue, la premiere par ordre
     * alphabetique) serait une hypothese sur ce qu'un enfant fait - une affirmation pedagogique, hors
     * du mandat de l'architecte qui ecrit ce test.</p>
     */
    @Test
    void aucunPaquetNEstEcarteEnSilenceDuCalculDeRang() {
        verifierQuAucunPaquetNEstEcarteEnSilence(
                CONTENU_DE_PRODUCTION, "", PAQUETS_HORS_DU_DOMAINE_NUMERIQUE);
    }

    /**
     * ADR-0051 : la clause, extraite pour se prouver mordante sur une declaration mutee plutot que sur
     * un paquet invente - l'objet juge est la declaration, c'est donc elle qu'il faut muter, et le
     * corpus reste les paquets reellement servis.
     */
    static void verifierQuAucunPaquetNEstEcarteEnSilence(Path racine, String racineClasspath,
                                                          Map<String, String> declares) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> ecartesEnSilence = new ArrayList<>();
        List<String> declarationsPerimees = new ArrayList<>();
        for (String paquet : paquets) {
            List<Question> items =
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            int classes = repartitionDe(items).total();
            boolean declare = declares.containsKey(paquet);
            if (classes == 0 && !declare) {
                ecartesEnSilence.add(String.format(
                        "paquet %s : %d items tires, %d classes - ce garde-fou ne l'a pas juge du tout,"
                                + " et rien ne le declare. Soit ce paquet est hors du domaine de la"
                                + " propriete (propositions non numeriques, ou nombre de distracteurs"
                                + " different de deux) et il entre dans"
                                + " PAQUETS_HORS_DU_DOMAINE_NUMERIQUE avec son motif, soit c'est le"
                                + " garde-fou qui ne sait pas lire ce qu'il devrait lire (ADR-0051)",
                        paquet, items.size(), classes));
            }
            if (classes > 0 && declare) {
                declarationsPerimees.add(String.format(
                        "paquet %s : declare hors du domaine, il contribue pourtant %d items au calcul"
                                + " de rang - la declaration est perimee (ADR-0051)",
                        paquet, classes));
            }
        }

        List<String> declarationsFantomes = declares.keySet().stream()
                .filter(paquet -> !paquets.contains(paquet))
                .map(paquet -> "paquet " + paquet + " : declare hors du domaine, il n'existe plus sous "
                        + racine + " - une declaration qui survit a son paquet (ADR-0051)")
                .sorted()
                .toList();

        declares.forEach((paquet, motif) -> assertThat(motif)
                .as("%s : une declaration sans motif ecrit ne declare rien", paquet)
                .isNotBlank());

        assertThat(ecartesEnSilence)
                .as("ADR-0051 : un paquet que ce garde-fou n'examine sur aucun de ses items le declare"
                        + " - un garde-fou qui exclut son objet en silence occupe la place d'une preuve"
                        + " qu'il ne fournit pas")
                .isEmpty();
        assertThat(declarationsPerimees)
                .as("ADR-0051 : une declaration hors du domaine ne survit pas au contenu qui la"
                        + " motivait")
                .isEmpty();
        assertThat(declarationsFantomes)
                .as("ADR-0051 : chaque nom declare designe un paquet qui existe reellement")
                .isEmpty();
    }

    /**
     * <b>Cinquieme clause, datee du 2026-09-10 : un compte ecrit dans un motif d'exemption vaut le
     * compte mesure sur le paquet que ce motif exempte.</b>
     *
     * <p><b>Le fait qui la fait naitre, et il est mesure.</b> A la cloture de s33, le motif de
     * {@code cm1-histoire-geographie} affirmait <i>"20 items, 60 propositions"</i> sur un paquet qui
     * en portait 110 et 330 : le chiffre datait de s27 et n'avait suivi aucun des cinq lots qui ont
     * grossi ce paquet. Le recomptage a alors trouve <b>quatre autres motifs perimes</b> par le meme
     * mecanisme. Ce n'est pas un faux vert - le garde-fou recalcule la propriete sur le paquet reel a
     * chaque execution, et {@link #aucunPaquetNEstEcarteEnSilenceDuCalculDeRang()} refuse deja qu'une
     * entree designe un paquet qui contribue - <b>c'est la prose de l'exemption qui pourrit</b>, et
     * c'est elle qui justifie l'exemption. Un chiffre sans garde dans un fichier commite est la
     * famille de defaut que ce depot sanctionne partout ailleurs.
     *
     * <p><b>Ce que la clause tient exactement</b> : tout groupe de chiffres suivi du mot
     * {@code items} ou du mot {@code propositions}, dans un motif, vaut le total mesure sur le paquet
     * servi que l'entree designe. <i>Mesure du 2026-09-10, sur les 53 entrees de la table :
     * 131 chiffres ecrits, 122 exacts, 9 faux repartis sur 5 entrees.</i> Elle est donc <b>rouge sur
     * l'etat du depot au moment ou elle est ecrite</b>, et les cinq motifs sont corriges dans le meme
     * commit - la regle seule serait rouge, les corrections seules revieilliraient au prochain lot.
     *
     * <p><b>Ce qu'elle ne tient pas, nomme plutot que laisse decouvrir.</b> Un compte ecrit en toutes
     * lettres lui echappe (<i>"cinq items"</i>, {@code cp-qlm-cycle-de-vie}) : c'est un plancher sur
     * la forme chiffree, pas une garantie sur la prose entiere. Et elle ne juge pas la <b>forme</b>
     * qu'un motif decrit : deux des cinq entrees corrigees affirmaient en plus que <i>tous</i> leurs
     * items portaient un seul distracteur, ce qui etait faux depuis s28 sur deux et trois items ;
     * cette moitie-la du defaut se corrige a la main et rien ne la garde.
     *
     * <p><b>Consequence assumee sur la redaction d'un motif</b> : un motif dit des <b>totaux</b>. Un
     * compte partiel ecrit en chiffres (<i>"les 8 items de determinant"</i>) serait rouge alors qu'il
     * serait juste. Mesure sur les 53 motifs du jour : aucun n'en ecrit un, le seul qui nomme un
     * sous-ensemble l'ecrit en toutes lettres.
     */
    @Test
    void chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet() {
        assertThat(comptesPerimesDesMotifs(
                CONTENU_DE_PRODUCTION, "", PAQUETS_HORS_DU_DOMAINE_NUMERIQUE))
                .as("un motif d'exemption n'est pas un journal : le compte qu'il ecrit est celui que"
                        + " le paquet porte aujourd'hui")
                .isEmpty();
    }

    /** La clause, extraite pour se prouver mordante sur un motif mute plutot que sur une fixture. */
    static List<String> comptesPerimesDesMotifs(Path racine, String racineClasspath,
                                                 Map<String, String> declares) {
        List<String> paquets = paquets(racine);
        List<String> defauts = new ArrayList<>();
        for (Map.Entry<String, String> declaration : declares.entrySet()) {
            String paquet = declaration.getKey();
            if (!paquets.contains(paquet)) {
                continue;
            }
            List<Item> items = tousLesItemsTires(
                    new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            int nombreDItems = items.size();
            int nombreDePropositions = items.stream()
                    .mapToInt(item -> item instanceof Question question
                            ? 1 + question.distracteurs().size() : 0)
                    .sum();
            Matcher compte = COMPTE_ECRIT_DANS_UN_MOTIF.matcher(declaration.getValue());
            while (compte.find()) {
                boolean surLesItems = "items".equals(compte.group(2));
                int mesure = surLesItems ? nombreDItems : nombreDePropositions;
                int ecrit = Integer.parseInt(compte.group(1));
                if (ecrit != mesure) {
                    defauts.add(String.format(
                            "paquet %s : le motif d'exemption ecrit \"%s %s\", le paquet servi en"
                                    + " porte %d. Un motif perime justifie l'exemption par un etat"
                                    + " qui n'existe plus ; il se recompte, il ne se reporte pas",
                            paquet, compte.group(1), compte.group(2), mesure));
                }
            }
        }
        return defauts;
    }

    /**
     * ADR-0013 obligation 3 : la clause du 2026-09-10 mise a l'epreuve sur un motif mute, et sur le
     * corpus reel - l'objet juge est le motif, c'est donc lui qu'il faut muter.
     */
    @Test
    void unCompteFauxDansUnMotifFaitEchouerLeGardeFou() {
        String paquet = PAQUETS_HORS_DU_DOMAINE_NUMERIQUE.keySet().iterator().next();
        Map<String, String> perime = Map.of(paquet,
                "motif pose par la mise a l'epreuve : 999 items, 999 propositions");

        assertThat(comptesPerimesDesMotifs(CONTENU_DE_PRODUCTION, "", perime))
                .as("un compte faux dans un motif doit etre nomme, deux fois plutot qu'une")
                .hasSize(2)
                .allMatch(defaut -> defaut.contains(paquet))
                .anyMatch(defaut -> defaut.contains("\"999 items\""))
                .anyMatch(defaut -> defaut.contains("\"999 propositions\""));
    }

    /**
     * Le sens inverse, sur le meme corpus : un motif dont les comptes sont exacts passe. Sans cette
     * seconde mesure, une clause qui refuserait tout motif chiffre aurait la meme trace rouge.
     */
    @Test
    void unMotifDontLesComptesSontExactsPasseLeGardeFou() {
        String paquet = "cp-emc-regle-souhait-loi";
        Map<String, String> exact = Map.of(paquet,
                "motif pose par la mise a l'epreuve : 11 items, 28 propositions");

        assertThat(comptesPerimesDesMotifs(CONTENU_DE_PRODUCTION, "", exact)).isEmpty();
    }

    /**
     * ADR-0013 obligation 3, premier sens : la declaration amputee d'un nom, sur le corpus reel. C'est
     * la forme exacte que prendra l'oubli le jour ou un paquet textuel neuf sera servi - ce que
     * {@code lea-dev} vient de vivre sur son paquet de synonymes, sans qu'aucun rouge ne le lui dise.
     */
    @Test
    void unPaquetHorsDuDomaineNonDeclareFaitEchouerLeGardeFou() {
        Map<String, String> amputee = new LinkedHashMap<>(PAQUETS_HORS_DU_DOMAINE_NUMERIQUE);
        String retire = amputee.keySet().iterator().next();
        amputee.remove(retire);
        assertThatThrownBy(() -> verifierQuAucunPaquetNEstEcarteEnSilence(
                CONTENU_DE_PRODUCTION, "", amputee))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(retire)
                .hasMessageContaining("ne l'a pas juge du tout");
    }

    /**
     * ADR-0013 obligation 3, second sens : un paquet reellement juge, declare a tort hors du domaine.
     * Sans cette clause, la table pourrait grandir sans limite et finir par soustraire au calcul des
     * paquets que le calcul voit parfaitement.
     */
    @Test
    void unPaquetDeclareAtortHorsDuDomaineFaitEchouerLeGardeFou() {
        Map<String, String> enTrop = new LinkedHashMap<>(PAQUETS_HORS_DU_DOMAINE_NUMERIQUE);
        enTrop.put("cm1-tables-multiplication", "motif faux, pose par la mise a l'epreuve");
        assertThatThrownBy(() -> verifierQuAucunPaquetNEstEcarteEnSilence(
                CONTENU_DE_PRODUCTION, "", enTrop))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cm1-tables-multiplication")
                .hasMessageContaining("la declaration est perimee");
    }

    /** ADR-0013 obligation 3, troisieme sens : un nom qui ne designe plus aucun paquet. */
    @Test
    void uneDeclarationQuiSurvitASonPaquetFaitEchouerLeGardeFou() {
        Map<String, String> fantome = new LinkedHashMap<>(PAQUETS_HORS_DU_DOMAINE_NUMERIQUE);
        fantome.put("paquet-qui-n-existe-pas", "motif pose par la mise a l'epreuve");
        assertThatThrownBy(() -> verifierQuAucunPaquetNEstEcarteEnSilence(
                CONTENU_DE_PRODUCTION, "", fantome))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-qui-n-existe-pas")
                .hasMessageContaining("survit a son paquet");
    }

    /** ADR-0013 obligation 3, quatrieme sens : une declaration sans motif ecrit. */
    @Test
    void uneDeclarationSansMotifFaitEchouerLeGardeFou() {
        Map<String, String> muette = new LinkedHashMap<>(PAQUETS_HORS_DU_DOMAINE_NUMERIQUE);
        String premier = muette.keySet().iterator().next();
        muette.put(premier, "   ");
        assertThatThrownBy(() -> verifierQuAucunPaquetNEstEcarteEnSilence(
                CONTENU_DE_PRODUCTION, "", muette))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une declaration sans motif ecrit ne declare rien");
    }

    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_RANG)
                .as("la dette de paquets qui depassent le seuil de majorite ne remonte jamais")
                .hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE);

        List<String> defauts = new ArrayList<>();
        for (String paquet : DETTE_DE_RANG) {
            if (!paquets(CONTENU_DE_PRODUCTION).contains(paquet)) {
                defauts.add(paquet + " : n'existe plus dans " + CONTENU_DE_PRODUCTION);
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader("").chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0
                    || (double) repartition.meilleureStrategie() / repartition.total() <= SEUIL_MAJORITE) {
                defauts.add(paquet + " : ne depasse plus le seuil, l'exemption est perimee");
            }
        }
        assertThat(defauts)
                .as("chaque nom de la dette doit designer un paquet qui existe et qui depasse encore"
                        + " reellement le seuil")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a jamais livre, dont la bonne
     * reponse est toujours la plus grande des trois propositions - la forme exacte du defaut refuse
     * par nadia-prof sur cm1-tables-multiplication.
     */
    @Test
    void unPaquetNeufOuLaBonneReponseEstToujoursLaPlusGrandeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-toujours-la-plus-grande")
                .hasMessageContaining("au-dessus du seuil de majorite");
    }

    /** Le meme paquet neuf, une fois son rang rendu non predictible, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSonRangCorrigePasseLeGardeFou() {
        verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige", Set.of());
    }

    /**
     * Mise a l'epreuve de la branche milliers (theo-archi, avis de faisabilite S13-1) : douze items
     * dont la bonne reponse est toujours la plus grande, ecrits au separateur d'espace au-dessus de
     * mille. L'ancien motif ({@code ^-?\d+}) ne lisait que le premier groupe de chiffres et melangeait
     * les trois rangs sans jamais en saturer un seul - vert et faux. Une fois les separateurs retires
     * avant lecture, les douze items se lisent tous "la plus grande" et le garde-fou doit rougir.
     */
    @Test
    void unPaquetDeMilliersOuLaBonneReponseEstToujoursLaPlusGrandeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE_MILLIERS, CONTRE_EXEMPLE_MILLIERS_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-milliers-toujours-la-plus-grande")
                .hasMessageContaining("au-dessus du seuil de majorite");
    }

    /** Le meme paquet de milliers, une fois son rang rendu non predictible, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetDeMilliersUneFoisSonRangCorrigePasseLeGardeFou() {
        verifierQueLeRangNEstPasPredictible(CONTRE_EXEMPLE_MILLIERS.resolve("corrige"),
                CONTRE_EXEMPLE_MILLIERS_CLASSPATH + "/corrige", Set.of());
    }

    /**
     * Mise a l'epreuve de la branche durees (nadia-prof, section 3.6 de l'arbitrage cite dans la
     * Javadoc de {@link #NOMBRE}) : douze items dont la bonne reponse est toujours la plus grande une
     * fois les heures et les minutes reellement lues. Six partagent la meme heure de tete entre leurs
     * trois propositions - la forme exacte qu'elle decrit - et six autres non. L'ancien motif ne
     * lisait que l'heure de tete : sur les six premiers, les trois heures sont egales, aucun
     * distracteur n'est lu "inferieur", et le rang etait toujours "la plus petite" ; sur les six
     * autres, l'heure de tete suffisait par hasard a ordonner correctement. Le melange des deux
     * (moitie faux, moitie juste) reste sous le seuil de majorite - vert et faux, une seconde fois,
     * pour une raison differente de la branche milliers. Une fois les durees converties en minutes,
     * les douze items se lisent tous "la plus grande" et le garde-fou doit rougir.
     */
    @Test
    void unPaquetDeDureesOuLaBonneReponseEstToujoursLaPlusGrandeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLeRangNEstPasPredictible(
                CONTRE_EXEMPLE_DUREES, CONTRE_EXEMPLE_DUREES_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-durees-toujours-la-plus-grande")
                .hasMessageContaining("au-dessus du seuil de majorite");
    }

    /** Le meme paquet de durees, une fois son rang rendu non predictible, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetDeDureesUneFoisSonRangCorrigePasseLeGardeFou() {
        verifierQueLeRangNEstPasPredictible(CONTRE_EXEMPLE_DUREES.resolve("corrige"),
                CONTRE_EXEMPLE_DUREES_CLASSPATH + "/corrige", Set.of());
    }

    /**
     * Correction du 2026-08-15 : ce que ni la correction de valeurDe ni ADR-0041 ne fermait. Aucun
     * des deux ne traite le cas ou deux ou trois propositions d'un item se lisent a la meme valeur -
     * {@link #rangDe} l'exclut desormais du calcul de rang ({@link #valeursEnConflit}) plutot que de
     * le classer "la plus petite" a tort, mais une exclusion silencieuse est exactement ce que
     * nadia-prof refuse (REFUS du 2026-08-15 sur cm1-grands-nombres) : le jour ou un motif de
     * decomposition ne fait plus varier que le premier terme, le garde-fou de rang devient muet sur
     * tous les items concernes sans qu'aucun diff ne le montre. Ce message rend donc l'egalite
     * elle-meme visible, et non pas seulement son effet sur un taux.
     */
    private static String egaliteDe(String paquet, Question item, long bonne, long d1, long d2) {
        if (!valeursEnConflit(bonne, d1, d2)) {
            return null;
        }
        return String.format(
                "paquet %s, item %s (\"%s\") : au moins deux des trois propositions [%s, %s, %s] se"
                        + " lisent a la meme valeur (%d, %d, %d) - le rang de la bonne reponse n'est"
                        + " pas defini",
                paquet, item.id().value(), item.enonce(), item.bonneReponse(),
                item.distracteurs().get(0), item.distracteurs().get(1), bonne, d1, d2);
    }

    /**
     * Aucun item du domaine (les trois propositions ont une valeur) ne doit lire deux de ses
     * propositions a la meme valeur. Contrairement a une proposition sans valeur - hors du domaine,
     * pas un defaut, ADR-0041 - une egalite de valeurs sur un item du domaine est toujours un defaut :
     * soit les deux propositions designent reellement le meme nombre (l'item n'a pas de reponse
     * unique), soit la lecture est trop courte pour les distinguer (une somme non lue, par exemple).
     * Dans les deux cas, rien ne doit le laisser passer en silence.
     *
     * <p><b>ADR-0068 partage ces deux cas, et n'en laisse qu'un ici.</b> Le second - la lecture est
     * trop courte - suppose qu'on puisse toujours l'allonger jusqu'a une valeur ; c'est vrai d'une
     * somme, c'est faux d'une proposition qui n'a pas de valeur du tout, et cette clause-ci
     * repondait alors "deux propositions valent la meme chose" a un item ou aucune des deux ne
     * vaut quoi que ce soit. Depuis ADR-0068, une lecture qui laisse un chiffre derriere elle rend
     * {@code null} : l'item sort du domaine avant d'arriver ici, et le seul cas qui reste est celui
     * de deux propositions <b>entierement</b> lues qui coincident. Le premier cas - deux
     * propositions qui designent reellement le meme nombre - reste garde ici, et sa forme textuelle
     * l'est en plus par {@code AucunePropositionNEstEgaleAUneAutreDansUnItemTest}, qui ne depend
     * d'aucune lecture de valeur.</p>
     */
    static void verifierQuAucuneValeurNeCoincide(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            for (Question item : items) {
                if (item.distracteurs().size() != 2) {
                    continue;
                }
                Long bonne = valeurDe(item.bonneReponse());
                Long d1 = valeurDe(item.distracteurs().get(0));
                Long d2 = valeurDe(item.distracteurs().get(1));
                if (bonne == null || d1 == null || d2 == null) {
                    continue;
                }
                String defaut = egaliteDe(paquet, item, bonne, d1, d2);
                if (defaut != null) {
                    defauts.add(defaut);
                }
            }
        }

        assertThat(defauts)
                .as("aucun item dont les trois propositions ont une valeur ne doit en lire deux a la"
                        + " meme valeur")
                .isEmpty();
    }

    @Test
    void aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem() {
        verifierQuAucuneValeurNeCoincide(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a jamais livre, dont deux
     * distracteurs de decomposition different de la bonne reponse par un terme autre que le premier -
     * la forme exacte que nadia-prof exige de tout paquet "decomposer" a venir, et qui tiendrait
     * silencieusement si valeurDe ne lisait que le premier terme d'une somme.
     */
    @Test
    void unItemNeufDontLesValeursCoincidentFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucuneValeurNeCoincide(
                CONTRE_EXEMPLE_VALEURS_EGALES, CONTRE_EXEMPLE_VALEURS_EGALES_CLASSPATH))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-valeurs-egales")
                .hasMessageContaining("se lisent a la meme valeur");
    }

    /** Le meme paquet neuf, une fois ses valeurs distinguees, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSesValeursDistingueesPasseLeGardeFou() {
        verifierQuAucuneValeurNeCoincide(CONTRE_EXEMPLE_VALEURS_EGALES.resolve("corrige"),
                CONTRE_EXEMPLE_VALEURS_EGALES_CLASSPATH + "/corrige");
    }

    /**
     * ADR-0102 : <b>aucune proposition servie qui ressemble a une duree ou a un instant n'est lue
     * comme un nombre nu.</b>
     *
     * <p><b>Le defaut que cette clause existe pour rendre visible, et il a ete mesure avant d'etre
     * ecrit.</b> {@link #lireValeur} essaie les motifs de duree, puis retombe sur {@link #NOMBRE}.
     * Sur <i>"2 heures et demie"</i>, le motif de duree echouait, {@link #NOMBRE} lisait <b>2</b>, et
     * le reste <i>"heures et demie"</i> ne portant aucun chiffre, la clause d'ADR-0068 laissait
     * passer. L'item restait dans le domaine avec une valeur en <b>heures</b> comparee a des valeurs
     * en <b>minutes</b> : <i>"1 heure et demie"</i> valait 1 et <i>"1 heure"</i> valait 60, donc la
     * bonne reponse etait classee <i>la plus petite</i> la ou l'enfant la voit au milieu. Les deux
     * seuils de ce fichier - {@link #SEUIL_MINIMUM} et {@link #SEUIL_MAJORITE}, qui sont ceux de
     * {@code DUR-CE1-5} - auraient alors ete <b>verts sur une repartition qui n'est pas celle qui est
     * servie</b>. Un garde-fou qui passe sans rien prouver est le defaut que ce depot sanctionne le
     * plus souvent.
     *
     * <p><b>Pourquoi une clause plutot qu'un motif de plus.</b> Allonger {@link #DUREE_HEURES} ecriture
     * par ecriture laisse le mode de defaillance intact : la prochaine forme non prevue retombe en
     * silence sur le nombre nu. Cette clause <b>inverse la polarite</b> (ADR-0039) - ce qui ressemble
     * a une duree et n'est pas lu comme telle est rouge, donc la prochaine ecriture neuve arrive comme
     * un echec nomme, pas comme un chiffre faux.
     *
     * <p><b>Population</b> (ADR-0051) : mesure du 2026-09-25 sur le contenu servi, <b>72</b>
     * propositions entrent dans le critere - les instants et les durees de
     * {@code cm1-problemes-durees} - et <b>aucune</b> n'echappe a la lecture. La clause s'execute donc
     * sur du materiau reel des son premier commit, et elle y est verte.
     */
    static void verifierQuAucuneEcritureDeDureeNEchappeALaLecture(Path racine,
            String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            List<Question> items =
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            for (Question item : items) {
                List<String> propositions = new ArrayList<>();
                propositions.add(item.bonneReponse());
                propositions.addAll(item.distracteurs());
                for (String proposition : propositions) {
                    String texte = proposition.strip();
                    if (!RESSEMBLE_A_UNE_DUREE.matcher(texte).matches()
                            || dureeEnMinutes(texte) != null) {
                        continue;
                    }
                    defauts.add(String.format(
                            "paquet %s, item %s : la proposition \"%s\" ecrit une duree ou un instant"
                                    + " que la lecture de valeur ne reconnait pas - elle serait lue"
                                    + " comme le nombre nu %s, donc comparee a des minutes, et le rang"
                                    + " mesure ne serait pas celui qui est servi (ADR-0102)",
                            paquet, item.id().value(), proposition, valeurDe(texte)));
                }
            }
        }

        assertThat(defauts)
                .as("ADR-0102 : ce qui ressemble a une duree se lit comme une duree, ou est rouge")
                .isEmpty();
    }

    @Test
    void aucuneEcritureDeDureeDeProductionNEchappeALaLecture() {
        verifierQuAucuneEcritureDeDureeNEchappeALaLecture(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0102, la lecture elle-meme, sur les deux ecritures que {@code DUR-CE1-3} nomme et sur celles
     * que le contenu sert deja. Le chiffre de gauche est ce que la lecture rendait avant ce commit
     * pour les deux dernieres lignes ; c'est la correction, pas une reformulation.
     */
    @Test
    void lesDeuxEcrituresDeDureeSeLisentEnMinutes() {
        assertThat(valeurDe("2 h 30 min")).isEqualTo(150L);
        assertThat(valeurDe("2 heures 30 minutes")).isEqualTo(150L);
        assertThat(valeurDe("14 h 30")).isEqualTo(870L);
        assertThat(valeurDe("2 h")).isEqualTo(120L);
        assertThat(valeurDe("45 min")).isEqualTo(45L);
        assertThat(valeurDe("2 heures et demie")).isEqualTo(150L);
        assertThat(valeurDe("1 heure et demie")).isEqualTo(90L);
    }

    /**
     * <b>La morsure, et elle porte sur l'ordre lu, pas seulement sur une valeur.</b> Sans la lecture
     * de la demi-heure, les trois propositions de cet item - qui sont celles qu'un paquet de
     * {@code MAT-CE1-12} ecrira - se lisaient 1, 60 et 120 : la bonne reponse, qui vaut 90 minutes,
     * etait classee la plus petite des trois. Ce test constate l'ordre rendu, qui est le seul objet
     * dont le taux de rang depend.
     */
    @Test
    void laDemiHeureEstOrdonneeEntreLesDeuxHeuresQuiLEncadrent() {
        long uneHeure = valeurDe("1 heure");
        long uneHeureEtDemie = valeurDe("1 heure et demie");
        long deuxHeures = valeurDe("2 heures");

        assertThat(uneHeureEtDemie)
                .as("une heure et demie tombe entre une heure et deux heures, pas en dessous des deux")
                .isGreaterThan(uneHeure)
                .isLessThan(deuxHeures);
    }

    /**
     * ADR-0013 obligation 3. Le jeu fautif est une ecriture de duree <b>que la lecture ne connait
     * pas</b> - le quart d'heure, deliberement non ajoute aux motifs (voir {@link #DUREE_ET_DEMIE}).
     * Il prouve que la clause mord sur la prochaine ecriture neuve quelle qu'elle soit, et pas
     * seulement sur celle qui a ete corrigee.
     */
    @Test
    void uneEcritureDeDureeInconnueDeLaLectureEstRouge() {
        assertThat(RESSEMBLE_A_UNE_DUREE.matcher("2 heures et quart").matches())
                .as("le quart d'heure ressemble a une duree")
                .isTrue();
        assertThat(dureeEnMinutes("2 heures et quart"))
                .as("et la lecture ne le reconnait pas")
                .isNull();
        assertThat(valeurDe("2 heures et quart"))
                .as("il serait donc lu comme le nombre nu 2, compare a des minutes")
                .isEqualTo(2L);
    }

    /**
     * ADR-0051 : la clause dit combien d'objets elle examine, plutot que de l'esperer. Mesure du
     * 2026-09-25 : <b>72</b> propositions servies ressemblent a une duree ou a un instant. La borne
     * est large a dessein - le contenu grossit a chaque sprint - mais elle refuse la population vide,
     * qui rendrait cette clause verte sans avoir rien lu.
     */
    @Test
    void laClauseDeLectureDesDureesExamineUnePopulationReelle() {
        long ressemblantes = paquets(CONTENU_DE_PRODUCTION).stream()
                .flatMap(paquet -> itemsTires(new YamlContentPackLoader("").chargerPaquet(paquet))
                        .stream())
                .flatMap(item -> Stream.concat(Stream.of(item.bonneReponse()),
                        item.distracteurs().stream()))
                .filter(proposition -> RESSEMBLE_A_UNE_DUREE.matcher(proposition.strip()).matches())
                .count();

        assertThat(ressemblantes)
                .as("ADR-0051 : des dizaines de propositions servies ecrivent une duree ou un instant")
                .isGreaterThan(50);
    }

    private static void ajouterDefautSiSousLeSeuil(String paquet, String nomDuRang, int effectif,
                                                     int total, List<String> defauts) {
        double ratio = (double) effectif / total;
        if (ratio < SEUIL_MINIMUM) {
            defauts.add(String.format(
                    "paquet %s : le rang %s de la bonne reponse n'apparait que sur %.1f %% des %d"
                            + " items a trois propositions numeriques, en dessous du seuil minimal"
                            + " (%.1f %%)",
                    paquet, nomDuRang, 100 * ratio, total, 100 * SEUIL_MINIMUM));
        }
    }

    /**
     * Reserve nadia-prof du 2026-08-15 : le seuil de majorite ne voit que le rang le plus frequent,
     * jamais le rang le plus rare. Un rang quasi vide elimine une proposition gratuitement a presque
     * chaque manche sans jamais rendre une strategie a rang constant majoritaire - c'est ce que le
     * premier garde-fou ne voit pas. Non bloquant, ne retire aucun Done pedagogique.
     */
    static void verifierQuAucunRangNeTombeSousLeSeuil(Path racine, String racineClasspath,
                                                        Set<String> dette) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            if (dette.contains(paquet)) {
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0) {
                continue;
            }
            ajouterDefautSiSousLeSeuil(paquet, "la plus petite", repartition.petite(),
                    repartition.total(), defauts);
            ajouterDefautSiSousLeSeuil(paquet, "l'intermediaire", repartition.intermediaire(),
                    repartition.total(), defauts);
            ajouterDefautSiSousLeSeuil(paquet, "la plus grande", repartition.grande(),
                    repartition.total(), defauts);
        }

        assertThat(defauts)
                .as("aucun des trois rangs de la bonne reponse ne devrait tomber sous le seuil minimal"
                        + " - reserve nadia-prof du 2026-08-15")
                .isEmpty();
    }

    @Test
    void aucunRangDeLaBonneReponseNeTombeSousLeSeuilMinimalSurLesPaquetsDeProduction() {
        verifierQuAucunRangNeTombeSousLeSeuil(CONTENU_DE_PRODUCTION, "", DETTE_DE_SEUIL_MINIMUM);
    }

    @Test
    void laDetteDuSeuilMinimalNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_SEUIL_MINIMUM)
                .as("la dette de paquets dont un rang tombe sous le seuil minimal ne remonte jamais")
                .hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE_SEUIL_MINIMUM);

        List<String> defauts = new ArrayList<>();
        for (String paquet : DETTE_DE_SEUIL_MINIMUM) {
            if (!paquets(CONTENU_DE_PRODUCTION).contains(paquet)) {
                defauts.add(paquet + " : n'existe plus dans " + CONTENU_DE_PRODUCTION);
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader("").chargerPaquet(paquet));
            Repartition repartition = repartitionDe(items);
            if (repartition.total() == 0
                    || (double) repartition.rangLePlusRare() / repartition.total() >= SEUIL_MINIMUM) {
                defauts.add(paquet + " : ne tombe plus sous le seuil minimal, l'exemption est perimee");
            }
        }
        assertThat(defauts)
                .as("chaque nom de la dette doit designer un paquet qui existe et dont un rang tombe"
                        + " encore reellement sous le seuil minimal")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a jamais livre, dont un rang
     * (la plus petite) est quasi vide alors qu'aucun rang n'est majoritaire - la forme exacte du
     * defaut que le premier garde-fou ne voit pas, mesuree par nadia-prof sur cp-soustractions.
     */
    @Test
    void unPaquetNeufDontUnRangEstQuasiVideFaitEchouerLeGardeFouDeSeuil() {
        assertThatThrownBy(() -> verifierQuAucunRangNeTombeSousLeSeuil(
                CONTRE_EXEMPLE_SEUIL_MINIMUM, CONTRE_EXEMPLE_SEUIL_MINIMUM_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-rang-quasi-vide")
                .hasMessageContaining("en dessous du seuil minimal");
    }

    /** Le meme paquet neuf, une fois son rang le plus rare etoffe, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSonRangLePlusRareEtoffePasseLeGardeFouDeSeuil() {
        verifierQuAucunRangNeTombeSousLeSeuil(CONTRE_EXEMPLE_SEUIL_MINIMUM.resolve("corrige"),
                CONTRE_EXEMPLE_SEUIL_MINIMUM_CLASSPATH + "/corrige", Set.of());
    }
}
