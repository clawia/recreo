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

    /** Une duree en minutes seules : "45 min", "45 minutes". */
    private static final Pattern DUREE_MINUTES = Pattern.compile("^(\\d+)\\s*min(?:utes?)?\\.?$");

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
    private static final Map<String, String> PAQUETS_HORS_DU_DOMAINE_NUMERIQUE = Map.of(
            "cm1-conjugaison-present",
            "25 items sur 25 portent trois distracteurs, donc quatre propositions : le rang d'une"
                    + " bonne reponse parmi trois n'y est pas defini. Mesure du 2026-08-19, comptage"
                    + " des cles distracteurs du paquet servi",
            "cm1-homophones",
            "24 items sur 24 portent un seul distracteur, donc deux propositions : meme motif, en"
                    + " sens inverse. Mesure du 2026-08-19",
            "cp-comparer-nombres",
            "24 items sur 24 proposent les trois symboles de comparaison (\"<\", \">\", \"=\") :"
                    + " deux distracteurs, mais aucune des trois propositions n'a de valeur"
                    + " numerique. C'est l'enonce qui porte les nombres, jamais les propositions",
            "cp-le-bon-son",
            "15 items sur 15 proposent des graphies de phonemes (\"on\", \"in\", \"an\") : deux"
                    + " distracteurs, aucune valeur numerique lisible",
            "cm1-synonymes",
            "16 items sur 16 portent deux distracteurs (trois propositions), mais aucune des 48"
                    + " propositions n'est numerique : ce sont des mots de vocabulaire (synonymes et"
                    + " distracteurs), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-08-19, verification directe : aucune des 60 propositions ne"
                    + " commence par un chiffre - remesure du 2026-08-20 apres le retrait de quatre"
                    + " items par camille-po (vite, ancien, poli, drole), meme motif inchange sur"
                    + " les 16 restants et leurs 48 propositions",
            "cm1-accords-groupe-nominal",
            "24 items, un nombre de distracteurs qui varie selon la composante testee (1 pour le"
                    + " nom, 2 pour le determinant, 3 pour l'adjectif) : les huit items de"
                    + " determinant portent bien deux distracteurs, mais aucune des 72 propositions"
                    + " du paquet n'est numerique - ce sont des mots (determinants, noms,"
                    + " adjectifs), valeurDe rend null sur chacune. Mesure du 2026-08-24 sur le"
                    + " paquet servi",
            "cm1-accord-sujet-verbe",
            "21 items sur 21 portent deux distracteurs (trois propositions), mais aucune des 63"
                    + " propositions n'est numerique : ce sont des formes conjuguees d'un verbe"
                    + " (bonne reponse et deux erreurs d'accord), valeurDe rend null sur les trois"
                    + " propositions de chaque item. Mesure du 2026-08-24 : aucune des 63"
                    + " propositions ne commence par un chiffre",
            "cm1-antonymes",
            "17 items sur 17 portent deux distracteurs (trois propositions), mais aucune des 51"
                    + " propositions n'est numerique : ce sont des mots de vocabulaire (antonymes et"
                    + " distracteurs), valeurDe rend null sur les trois propositions de chaque item."
                    + " Mesure du 2026-08-24 sur le paquet servi (arbitrage ANT-1 a ANT-7 de"
                    + " nadia-prof) : aucune des 51 propositions ne commence par un chiffre");

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
    static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(5)::nextInt;
        Set<ItemId> dejaVus = new java.util.HashSet<>();
        Map<ItemId, Question> items = new LinkedHashMap<>();
        int tiragesSansNouveaute = 0;
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES
                && tiragesSansNouveaute < TIRAGES_SANS_NOUVEAUTE_AVANT_ARRET; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            Question question = (Question) item;
            dejaVus.add(question.id());
            if (items.put(question.id(), question) == null) {
                tiragesSansNouveaute = 0;
            } else {
                tiragesSansNouveaute++;
            }
        }
        return List.copyOf(items.values());
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
        String sansSeparateur = SEPARATEUR_DE_MILLIERS.matcher(matcher.group()).replaceAll("");
        return new Lue(Long.parseLong(sansSeparateur), texte.substring(matcher.end()).strip());
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
