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

    /** Les trois ecritures d'un separateur de milliers vues par {@link #NOMBRE} : voir sa Javadoc. */
    private static final Pattern SEPARATEUR_DE_MILLIERS = Pattern.compile("[\\u00A0\\u202F ]");

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
    private static List<Question> itemsTires(ContentSource contenu) {
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
     * La valeur numerique comparable d'une proposition : une duree convertie en minutes si elle en
     * a l'ecriture, sinon un nombre entier une fois ses separateurs de milliers retires, sinon
     * {@code null} - hors du domaine de cette propriete, pas un defaut. Les durees sont vues avant
     * les nombres nus : voir la Javadoc de {@link #DUREE_HEURES}.
     */
    private static Long valeurDe(String proposition) {
        String texte = proposition.strip();
        Long duree = dureeEnMinutes(texte);
        if (duree != null) {
            return duree;
        }
        Matcher matcher = NOMBRE.matcher(texte);
        if (!matcher.find()) {
            return null;
        }
        String sansSeparateur = SEPARATEUR_DE_MILLIERS.matcher(matcher.group()).replaceAll("");
        return Long.valueOf(sansSeparateur);
    }

    /**
     * Le rang de la bonne reponse parmi les trois propositions, ou {@code null} si l'item n'a pas
     * exactement deux distracteurs ou si l'une des trois propositions n'est pas numerique - hors du
     * domaine de cette propriete, pas un defaut.
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
