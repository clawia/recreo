package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S48-MAT-PROBLEMES ({@code docs/backlog/backlog.md}, sprint 48), le paquet
 * {@code ce1-problemes-etapes}, ligne {@code MAT-CE1-08} - resoudre des problemes d'addition, de
 * soustraction et de multiplication a une ou deux etapes. Arbitrage pedagogique prealable de s43,
 * lot 2 ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md}, section 2,
 * conditions {@code PRO-CE1-1} a {@code PRO-CE1-7}), et avis de faisabilite de theo-archi
 * ({@code squad/rapports/avis-faisabilite-s48-sept-stories.md}, section 3, quatre pieges de
 * fabrication repris ci-dessous sans changement).
 *
 * <p><b>Chaque item declare sa derivation</b> a cote de l'enonce, dans un commentaire YAML de la
 * forme {@code # PRO-CE1: op1(x1,y1);op2(x2,y2);fam=f1,f2} (une seule etape pour un item a une
 * etape). {@code R} designe le resultat de l'etape precedente. Ce test relit ce commentaire,
 * recalcule integralement chaque etape et les deux distracteurs depuis le fait declare - rien
 * n'est recopie du YAML pour le verifier.
 *
 * <p><b>Piege 1 (avis section 3), evite par construction.</b> Le patron du CM1
 * ({@link PaquetCm1ProblemesEtapesTest}) extrait les trois nombres de l'enonce dans leur ordre
 * d'apparition et les applique dans cet ordre - une forme ou l'ordre d'ecriture n'est pas l'ordre
 * des etapes (*"3 sachets de 5 billes, puis 12 billes"*, qui calcule {@code 3 x 5} avant
 * {@code 12 + 15}) rendrait ce patron faux. Ce test ne lit donc jamais les nombres de l'enonce
 * dans leur ordre d'ecriture : le marqueur porte les operandes de chaque etape explicitement, et
 * {@link #operandeFigureDansLEnonce} verifie seulement que chaque operande litteral declare est un
 * nombre ecrit en chiffres quelque part dans l'enonce.
 *
 * <p><b>Piege 2, le lexique interdit en mots entiers.</b> {@link #LEXIQUE_PRO_CE1_4} et
 * {@link #LEXIQUE_PRO_CE1_6} sont verifies par {@link #VERIFICATEUR_LEXIQUE}, un motif
 * {@code \bmot\b} compile avec {@link Pattern#UNICODE_CHARACTER_CLASS} : sans ce drapeau, le
 * {@code \b} de Java est ASCII et un mot comme {@code "dur"} trouverait une correspondance dans
 * {@code "la duree"} (verifie sur 21.0.10 par l'avis, section 12) - l'accent n'est pas vu comme un
 * caractere de mot sous le {@code \b} par defaut.
 *
 * <p><b>Piege 3, l'indice verifie par nombre extrait, jamais par sous-chaine.</b>
 * {@link #indiceNeRevelePasLesPropositions} compare des entiers extraits de l'indice aux trois
 * propositions numeriques de l'item, et non des sous-chaines : une bonne reponse {@code "2"} ne
 * ferait pas rougir un indice qui ecrit {@code "12"}.
 *
 * <p><b>Piege 4, le rang.</b> {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest} n'a
 * aucune exemption disponible pour ce paquet neuf (ses deux dettes sont a leur plafond exact,
 * avis section 3) : ce paquet tient donc le seuil de rang par construction, pas par exemption -
 * {@link #pro5ChaqueRangDeLaBonneReponsePorteEntreDixEtCinquantePourCentDesItems} le remesure ici.
 */
class PaquetCe1ProblemesEtapesTest {

    private static final String PAQUET = "ce1-problemes-etapes";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1ProblemesEtapesTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1ProblemesEtapesTest";

    /** Une ligne "- enonce:" du fichier source, avec son marqueur PRO-CE1 en commentaire. */
    private static final Pattern LIGNE_ITEM =
            Pattern.compile("- enonce: \"([^\"]*)\"\\s*#\\s*PRO-CE1:\\s*(.+)\\s*$");

    private static final Pattern ETAPE =
            Pattern.compile("(add|sub|mul)\\((R|\\d+),(\\d+)\\)");

    private static final Pattern FAM = Pattern.compile("fam=([a-d]),([a-d])");

    private static final Pattern NOMBRE = Pattern.compile("\\d+");

    private static final double SEUIL_RANG_MIN = 0.10;

    private static final double SEUIL_RANG_MAX = 0.50;

    /**
     * PRO-CE1-4 : lexique exclu, en mots entiers. L'achat, la monnaie, le partage, le groupement et
     * la division sont des formes d'une autre ligne (section 2.1 et 2.3 de l'arbitrage).
     */
    private static final List<String> LEXIQUE_PRO_CE1_4 = List.of(
            "achat", "achats", "acheter", "acheté", "achetée", "achetées", "achetés",
            "prix", "pièce", "pièces", "billet", "billets", "euro", "euros", "centime",
            "centimes", "partage", "partager", "partageant", "partagé", "partagée", "partagées",
            "partagés", "groupement", "groupements", "grouper", "groupe", "groupes",
            "division", "diviser", "divise", "divisé", "divisée", "divisées", "divisés",
            "magasin", "magasins", "vente", "ventes", "vendre", "vend", "vendent", "vendu",
            "vendue", "vendus", "vendues");

    /**
     * PRO-CE1-6 : aucune situation ne suppose un jardin, une piscine, une chambre a soi, un
     * animal, des vacances, de l'argent de poche ni une voiture (choix non bloquant sur Q2 de
     * l'avis de faisabilite de s48, documente dans l'en-tete du paquet).
     */
    private static final List<String> LEXIQUE_PRO_CE1_6 = List.of(
            "jardin", "jardins", "piscine", "piscines", "chambre", "chambres",
            "animal", "animaux", "chat", "chats", "chien", "chiens",
            "vacances", "voiture", "voitures");

    private static Pattern verificateurLexique(List<String> mots) {
        return Pattern.compile("\\b(" + String.join("|", mots) + ")\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
    }

    private static final Pattern VERIFICATEUR_LEXIQUE_4 = verificateurLexique(LEXIQUE_PRO_CE1_4);

    private static final Pattern VERIFICATEUR_LEXIQUE_6 = verificateurLexique(LEXIQUE_PRO_CE1_6);

    /** Une etape resolue : son operation et ses deux operandes, {@code R} deja substitue. */
    private record EtapeResolue(String op, long x, long y, long resultat, boolean xLitteral,
            boolean yLitteral) {
    }

    private record Derivation(List<EtapeResolue> etapes, String fam1, String fam2) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static long appliquer(String op, long x, long y) {
        return switch (op) {
            case "add" -> x + y;
            case "sub" -> x - y;
            case "mul" -> x * y;
            default -> throw new IllegalArgumentException(op);
        };
    }

    private static String inverse(String op) {
        return switch (op) {
            case "add" -> "sub";
            case "sub" -> "add";
            default -> throw new IllegalArgumentException(
                    "aucune inversion definie pour '" + op + "' (PRO-CE1-5)");
        };
    }

    /** Recalcule chaque etape dans l'ordre declare, {@code R} designant le resultat precedent. */
    private static Derivation resoudre(String marqueur) {
        Matcher famMatcher = FAM.matcher(marqueur);
        assertThat(famMatcher.find()).as("'%s' : marqueur de familles 'fam=x,y' (PRO-CE1-5)",
                marqueur).isTrue();
        String fam1 = famMatcher.group(1);
        String fam2 = famMatcher.group(2);

        String partieEtapes = marqueur.substring(0, famMatcher.start());
        Matcher etapeMatcher = ETAPE.matcher(partieEtapes);
        List<EtapeResolue> etapes = new ArrayList<>();
        long previous = Long.MIN_VALUE;
        while (etapeMatcher.find()) {
            String op = etapeMatcher.group(1);
            String xRaw = etapeMatcher.group(2);
            String yRaw = etapeMatcher.group(3);
            boolean xLitteral = !"R".equals(xRaw);
            boolean yLitteral = !"R".equals(yRaw);
            long x = xLitteral ? Long.parseLong(xRaw) : previous;
            long y = yLitteral ? Long.parseLong(yRaw) : previous;
            long resultat = appliquer(op, x, y);
            etapes.add(new EtapeResolue(op, x, y, resultat, xLitteral, yLitteral));
            previous = resultat;
        }
        assertThat(etapes).as("'%s' : au moins une etape declaree (PRO-CE1-1)", marqueur)
                .isNotEmpty();
        assertThat(etapes.size()).as("'%s' : au plus deux etapes (PRO-CE1-1)", marqueur)
                .isLessThanOrEqualTo(2);
        return new Derivation(etapes, fam1, fam2);
    }

    /**
     * PRO-CE1-5 : la valeur d'une famille de distracteur, recalculee depuis les etapes resolues.
     * (a) le resultat intermediaire (deux etapes seulement) ; (b) l'inversion de l'operation de la
     * derniere etape ; (c) l'inversion de la premiere etape, suivie de la seconde etape correcte
     * (deux etapes seulement) ; (d) le second operande litteral de l'unique etape, recopie sans
     * calcul (une etape seulement).
     */
    private static long valeurDeLaFamille(String famille, List<EtapeResolue> etapes) {
        return switch (famille) {
            case "a" -> {
                assertThat(etapes).as("famille (a) : deux etapes (PRO-CE1-5)").hasSize(2);
                yield etapes.get(0).resultat();
            }
            case "b" -> {
                EtapeResolue derniere = etapes.get(etapes.size() - 1);
                yield appliquer(inverse(derniere.op()), derniere.x(), derniere.y());
            }
            case "c" -> {
                assertThat(etapes).as("famille (c) : deux etapes (PRO-CE1-5)").hasSize(2);
                EtapeResolue premiere = etapes.get(0);
                long rPrime = appliquer(inverse(premiere.op()), premiere.x(), premiere.y());
                EtapeResolue seconde = etapes.get(1);
                yield appliquer(seconde.op(), rPrime, seconde.y());
            }
            case "d" -> {
                assertThat(etapes).as("famille (d) : une etape (PRO-CE1-5)").hasSize(1);
                yield etapes.get(0).y();
            }
            default -> throw new IllegalArgumentException("famille inconnue '" + famille + "'");
        };
    }

    private static boolean operandeFigureDansLEnonce(String enonce, long operande) {
        Matcher matcher = NOMBRE.matcher(enonce);
        while (matcher.find()) {
            if (Long.parseLong(matcher.group()) == operande) {
                return true;
            }
        }
        return false;
    }

    /** Un (enonce -> reste du marqueur) par ligne "- enonce:" du fichier source. */
    private static Map<String, String> marqueursParEnonce(Path fichier) {
        Map<String, String> marqueurs = new LinkedHashMap<>();
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher matcher = LIGNE_ITEM.matcher(ligne.strip());
            if (matcher.matches()) {
                marqueurs.put(matcher.group(1), matcher.group(2).strip());
            }
        }
        return marqueurs;
    }

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }

    private static List<Question> tousLesItems(ContentSource source) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = source.next(hasard, dejaVus).orElse(null); item != null;
                item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private record ItemAnalyse(Question item, Derivation derivation, long bonne, long d1,
            long d2) {
    }

    private static List<ItemAnalyse> itemsAnalyses(ContentSource source, Path fichierMarqueurs) {
        Map<String, String> marqueurs = marqueursParEnonce(fichierMarqueurs);
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            String marqueur = marqueurs.get(item.enonce());
            assertThat(marqueur).as("PRO-CE1-5 : aucun marqueur trouve pour '%s'", item.enonce())
                    .isNotNull();
            Derivation derivation = resoudre(marqueur);

            for (EtapeResolue etape : derivation.etapes()) {
                if (etape.xLitteral()) {
                    assertThat(operandeFigureDansLEnonce(item.enonce(), etape.x()))
                            .as("'%s' : l'operande %d n'est pas un nombre de l'enonce (PRO-CE1-5)",
                                    item.enonce(), etape.x()).isTrue();
                }
                if (etape.yLitteral()) {
                    assertThat(operandeFigureDansLEnonce(item.enonce(), etape.y()))
                            .as("'%s' : l'operande %d n'est pas un nombre de l'enonce (PRO-CE1-5)",
                                    item.enonce(), etape.y()).isTrue();
                }
            }

            long bonneCalculee = derivation.etapes().get(derivation.etapes().size() - 1).resultat();
            assertThat(String.valueOf(bonneCalculee))
                    .as("'%s' : bonne reponse recalculee depuis la derivation (PRO-CE1-5)",
                            item.enonce())
                    .isEqualTo(item.bonneReponse());

            assertThat(item.distracteurs()).as("'%s' : deux distracteurs (PRO-CE1-5)",
                    item.enonce()).hasSize(2);
            long d1 = valeurDeLaFamille(derivation.fam1(), derivation.etapes());
            long d2 = valeurDeLaFamille(derivation.fam2(), derivation.etapes());
            assertThat(item.distracteurs().get(0))
                    .as("'%s' : premier distracteur, famille (%s) (PRO-CE1-5)", item.enonce(),
                            derivation.fam1())
                    .isEqualTo(String.valueOf(d1));
            assertThat(item.distracteurs().get(1))
                    .as("'%s' : second distracteur, famille (%s) (PRO-CE1-5)", item.enonce(),
                            derivation.fam2())
                    .isEqualTo(String.valueOf(d2));

            Set<Long> propositions = new TreeSet<>(Set.of(bonneCalculee, d1, d2));
            assertThat(propositions).as("'%s' : trois propositions distinctes (PRO-CE1-5)",
                    item.enonce()).hasSize(3);
            assertThat(propositions).as("'%s' : trois propositions positives (PRO-CE1-5)",
                    item.enonce()).allMatch(valeur -> valeur > 0);

            analyses.add(new ItemAnalyse(item, derivation, bonneCalculee, d1, d2));
        }
        return analyses;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET), FICHIER_CONTENU);
    }

    @Test
    void adr0081CePaquetDeclareMatCe108EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-08"));
    }

    @Test
    void pro7LePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet(PAQUET)))
                .as("PRO-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void pro5ChaqueItemRecalculeSaBonneReponseEtSesDeuxDistracteursDepuisSaDerivationDeclaree() {
        assertThat(itemsAnalyses()).isNotEmpty();
    }

    @Test
    void pro1LesEtapesEtLesPropertionsSontTenues() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long deuxEtapes = analyses.stream().filter(a -> a.derivation().etapes().size() == 2).count();
        long uneEtape = analyses.stream().filter(a -> a.derivation().etapes().size() == 1).count();
        long troisEtapesOuPlus = analyses.stream()
                .filter(a -> a.derivation().etapes().size() > 2).count();

        assertThat(troisEtapesOuPlus).as("PRO-CE1-1 : aucun item a trois etapes").isZero();
        assertThat((double) deuxEtapes / total)
                .as("PRO-CE1-1 : deux etapes, au moins un tiers du paquet")
                .isGreaterThanOrEqualTo(1.0 / 3);
        assertThat((double) uneEtape / total)
                .as("PRO-CE1-1 : une etape, au moins un quart du paquet")
                .isGreaterThanOrEqualTo(1.0 / 4);
    }

    @Test
    void pro2ChaqueOperationEstUneEtapeDAuMoinsUnCinquiemeDesItems() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        for (String op : List.of("add", "sub", "mul")) {
            long items = analyses.stream()
                    .filter(a -> a.derivation().etapes().stream().anyMatch(e -> e.op().equals(op)))
                    .count();
            assertThat((double) items / total)
                    .as("PRO-CE1-2 : '%s' est une etape d'au moins un cinquieme des items", op)
                    .isGreaterThanOrEqualTo(0.20);
        }
    }

    @Test
    void pro3LaMultiplicationNEstJamaisLUniqueEtapeDUnItemAUneEtape() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        for (ItemAnalyse analyse : analyses) {
            List<EtapeResolue> etapes = analyse.derivation().etapes();
            if (etapes.size() == 1) {
                assertThat(etapes.get(0).op())
                        .as("'%s' : l'unique etape n'est pas une multiplication (PRO-CE1-3)",
                                analyse.item().enonce())
                        .isNotEqualTo("mul");
            }
        }
    }

    @Test
    void pro4AucunNombreAuDelaDeMilleEtAucuneDivision() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        for (ItemAnalyse analyse : analyses) {
            for (EtapeResolue etape : analyse.derivation().etapes()) {
                assertThat(etape.op()).as("'%s' : aucune division (PRO-CE1-4)",
                        analyse.item().enonce()).isNotEqualTo("div");
                assertThat(Math.abs(etape.resultat()))
                        .as("'%s' : aucun resultat intermediaire au-dela de 1000 (PRO-CE1-4)",
                                analyse.item().enonce())
                        .isLessThanOrEqualTo(1000);
            }
            Matcher matcher = NOMBRE.matcher(analyse.item().enonce());
            while (matcher.find()) {
                assertThat(Long.parseLong(matcher.group()))
                        .as("'%s' : aucun nombre au-dela de 1000 (PRO-CE1-4)",
                                analyse.item().enonce())
                        .isLessThanOrEqualTo(1000);
            }
        }
    }

    @Test
    void pro4AucunMotDuLexiqueExcluNEstServi() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_LEXIQUE_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu de PRO-CE1-4", item.enonce()).isFalse();
        }
    }

    @Test
    void pro6AucunPresupposeSocialInterditNEstServi() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_LEXIQUE_6.matcher(item.enonce()).find())
                    .as("'%s' : aucun presuppose social interdit par PRO-CE1-6", item.enonce())
                    .isFalse();
            assertThat(item.enonce().toLowerCase(java.util.Locale.FRENCH))
                    .as("'%s' : aucune mention d'argent de poche (PRO-CE1-6)", item.enonce())
                    .doesNotContain("argent de poche");
        }
    }

    @Test
    void pro5ChaqueRangDeLaBonneReponsePorteEntreDixEtCinquantePourCentDesItems() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        Map<String, Integer> tally = new LinkedHashMap<>(
                Map.of("plus petite", 0, "mediane", 0, "plus grande", 0));
        for (ItemAnalyse analyse : analyses) {
            List<Long> valeurs = new ArrayList<>(
                    List.of(analyse.bonne(), analyse.d1(), analyse.d2()));
            valeurs.sort(Long::compareTo);
            int rang = valeurs.indexOf(analyse.bonne());
            String cle = switch (rang) {
                case 0 -> "plus petite";
                case 1 -> "mediane";
                default -> "plus grande";
            };
            tally.merge(cle, 1, Integer::sum);
        }
        int total = analyses.size();
        tally.forEach((rang, compte) -> assertThat((double) compte / total)
                .as("PRO-CE1-5 : rang '%s', %d items sur %d", rang, compte, total)
                .isBetween(SEUIL_RANG_MIN, SEUIL_RANG_MAX));
    }

    @Test
    void pro7ChaqueIndiceEstPresentEtAuPlusDouzeMotsEtNeRevelePasLesPropositions() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        Set<String> indicesDistincts = new HashSet<>();
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.item();
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (PRO-CE1-7)"));
            indicesDistincts.add(indice);
            int mots = indice.strip().split("\\s+").length;
            assertThat(mots).as("'%s' : indice d'au plus douze mots (PRO-CE1-7)", indice)
                    .isLessThanOrEqualTo(12);
            indiceNeRevelePasLesPropositions(indice, analyse);
        }
        assertThat(indicesDistincts)
                .as("PRO-CE1-7 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    /** Piege 3 (avis section 3) : comparaison par nombre extrait, jamais par sous-chaine. */
    private static void indiceNeRevelePasLesPropositions(String indice, ItemAnalyse analyse) {
        Set<Long> propositions = Set.of(analyse.bonne(), analyse.d1(), analyse.d2());
        Matcher matcher = NOMBRE.matcher(indice);
        while (matcher.find()) {
            long nombre = Long.parseLong(matcher.group());
            assertThat(propositions)
                    .as("'%s' : l'indice revele une proposition (%d) (PRO-CE1-7)", indice, nombre)
                    .doesNotContain(nombre);
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou, traverse par la lecture disque, le
     * chargeur de contenu reel et le recensement des marqueurs. Son seul item declare la famille
     * (b) pour son premier distracteur avec une valeur fausse : sur l'etape unique {@code sub(32,9)},
     * la famille (b) (inversion de l'operation) vaut {@code 32 + 9 = 41}, jamais {@code 99}.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMarqueurErrone() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve(
                "contenu/ce1-problemes-etapes-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-problemes-etapes-fautif");

        assertThatThrownBy(() -> itemsAnalyses(source, fichierFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRO-CE1-5")
                .hasMessageContaining("41");
    }
}
