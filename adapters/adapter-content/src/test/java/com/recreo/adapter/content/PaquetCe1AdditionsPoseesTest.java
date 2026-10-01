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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S45-MAT-ADDITION ({@code docs/backlog/backlog.md}, sprint 45), le paquet
 * {@code ce1-additions-posees}, ligne {@code MAT-CE1-04} - addition posee avec retenue.
 * Arbitrage pedagogique prealable cite dans la story
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md}, section 4) et
 * precise par {@code squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md}, section 2 (la
 * definition des trois erreurs qui fait foi : {@code 48 + 26 -> 64} pour la retenue oubliee, la
 * mauvaise colonne est celle des centaines).
 *
 * <p>Les proprietes generiques (indice non vide, au plus trois propositions, aucune proposition
 * egale a une autre, dix manches contre au moins onze items du chargeur, rang de la bonne reponse
 * non predictible) sont deja gardees par les garde-fous derives du repertoire de contenu ; ce
 * fichier verifie les conditions {@code ADP-CE1-1} a {@code ADP-CE1-6} qu'aucun garde-fou generique
 * ne connait.</p>
 *
 * <p>{@code ADR-0103} : ce paquet declare {@code MAT-CE1-04}, pas {@code MAT-CE1-03} - il sort donc
 * du perimetre de {@link ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest}
 * ({@code ADD-CE1-2}), qui ne porte que sur les paquets qui declarent {@code MAT-CE1-03}. La
 * separation d'avec {@code MAT-CE1-03} ({@code ADP-CE1-3}) et la table des retenues
 * ({@code ADP-CE1-2}) sont donc verifiees ici, et ici seulement.</p>
 *
 * <p><b>{@code ADP-CE1-5}</b> : chaque distracteur est l'une des trois erreurs nommees, calculee a
 * partir des operandes - {@link #retenueOubliee}, {@link #retenueReporteeDeuxFois} et
 * {@link #retenueMauvaiseColonne} traduisent litteralement l'arithmetique en colonnes decrite par
 * l'arbitrage. La declaration dans le paquet se fait par un marqueur en commentaire sur la ligne
 * {@code distracteurs}, relu par {@link #marqueursParEnonce} et compare au calcul : un distracteur
 * dont le marqueur ne correspond pas a la valeur chargee, ou dont aucun marqueur n'est trouve, fait
 * rougir ce test plutot que de passer sans etre verifie.</p>
 */
class PaquetCe1AdditionsPoseesTest {

    private static final String PAQUET = "ce1-additions-posees";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1AdditionsPoseesTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1AdditionsPoseesTest";

    /** ADP-CE1-6 : strictement "a + b = ?", le blanc insecable d'ADR-0067 (TYP-1) devant "?". */
    private static final Pattern ENONCE = Pattern.compile("^(\\d+) \\+ (\\d+) =\\u00A0\\?$");

    /** ADP-CE1-6 : aucune proposition ne porte autre chose que des chiffres. */
    private static final Pattern PROPOSITION_NUMERIQUE = Pattern.compile("^\\d+$");

    /** Une ligne "enonce:" du fichier source, pour associer chaque marqueur a son item. */
    private static final Pattern LIGNE_ENONCE = Pattern.compile("enonce:\\s*\"([^\"]*)\"");

    /** Une ligne "distracteurs:" suivie de son marqueur ADP-CE1-5 en commentaire. */
    private static final Pattern LIGNE_MARQUEURS =
            Pattern.compile("distracteurs:.*#\\s*ADP-CE1-5:\\s*(.+)$");

    /** Un marqueur "oubliee(unites)" ou "reportee(les-deux)" : forme et retenue concernee. */
    private static final Pattern MARQUEUR_AVEC_RETENUE =
            Pattern.compile("^(oubliee|reportee)\\((unites|dizaines|les-deux)\\)$");

    private static final String MARQUEUR_MAUVAISE_COLONNE = "mauvaise-colonne";

    private enum Retenue {
        UNITES, DIZAINES, LES_DEUX
    }

    private static Retenue retenueDe(String texte) {
        return switch (texte) {
            case "unites" -> Retenue.UNITES;
            case "dizaines" -> Retenue.DIZAINES;
            default -> Retenue.LES_DEUX;
        };
    }

    private static int unites(int n) {
        return n % 10;
    }

    private static int dizaines(int n) {
        return (n / 10) % 10;
    }

    private static int centaines(int n) {
        return n / 100;
    }

    /**
     * ADP-CE1-5, premiere forme : la retenue n'est pas ajoutee a la colonne suivante (definition
     * qui fait foi, {@code 48 + 26 -> 64}). Une retenue "forgotten" est simplement absente de la
     * colonne qui la recevrait ; la colonne elle-meme, et tout ce qui la suit, continue de se
     * calculer normalement.
     */
    static int retenueOubliee(int a, int b, Retenue retenue) {
        int s0 = unites(a) + unites(b);
        int d0 = s0 % 10;
        int c0 = s0 / 10;
        int carryIn1 = (retenue == Retenue.UNITES || retenue == Retenue.LES_DEUX) ? 0 : c0;
        int s1 = dizaines(a) + dizaines(b) + carryIn1;
        int d1 = s1 % 10;
        int c1 = s1 / 10;
        int carryIn2 = (retenue == Retenue.DIZAINES || retenue == Retenue.LES_DEUX) ? 0 : c1;
        int s2 = centaines(a) + centaines(b) + carryIn2;
        return s2 * 100 + d1 * 10 + d0;
    }

    /** ADP-CE1-5, deuxieme forme : la retenue est ajoutee deux fois a la colonne suivante. */
    static int retenueReporteeDeuxFois(int a, int b, Retenue retenue) {
        int s0 = unites(a) + unites(b);
        int d0 = s0 % 10;
        int c0 = s0 / 10;
        int carryIn1 = c0 + (retenue == Retenue.UNITES || retenue == Retenue.LES_DEUX ? 1 : 0);
        int s1 = dizaines(a) + dizaines(b) + carryIn1;
        int d1 = s1 % 10;
        int c1 = s1 / 10;
        int carryIn2 = c1 + (retenue == Retenue.DIZAINES || retenue == Retenue.LES_DEUX ? 1 : 0);
        int s2 = centaines(a) + centaines(b) + carryIn2;
        return s2 * 100 + d1 * 10 + d0;
    }

    /**
     * ADP-CE1-5, troisieme forme : la retenue des unites est ajoutee a la colonne des centaines en
     * sautant celle des dizaines. N'existe que si l'addition porte reellement une retenue des
     * unites ; {@code null} sinon, pour qu'un item qui n'en a pas ne declare jamais ce marqueur.
     */
    static Integer retenueMauvaiseColonne(int a, int b) {
        int s0 = unites(a) + unites(b);
        int d0 = s0 % 10;
        int c0 = s0 / 10;
        if (c0 == 0) {
            return null;
        }
        int s1 = dizaines(a) + dizaines(b);
        int d1 = s1 % 10;
        int c1 = s1 / 10;
        int s2 = centaines(a) + centaines(b) + c1 + c0;
        return s2 * 100 + d1 * 10 + d0;
    }

    private record ItemAnalyse(Question question, int a, int b, int bonne, boolean retenueUnites,
                                boolean retenueDizaines) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement des marqueurs ADP-CE1-5, par enonce : une lecture du fichier source distincte
     * du chargeur YAML, parce que le marqueur vit dans un commentaire qu'aucun chargeur de contenu
     * ne lit. L'association par enonce, plutot que par position dans le fichier, est deliberee : le
     * chargeur ne garantit pas de rendre les items dans l'ordre du fichier.
     */
    private static Map<String, List<String>> marqueursParEnonce(Path fichier) {
        Map<String, List<String>> marqueurs = new LinkedHashMap<>();
        String enonceCourant = null;
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher enonce = LIGNE_ENONCE.matcher(ligne);
            if (enonce.find()) {
                enonceCourant = enonce.group(1);
                continue;
            }
            Matcher marqueur = LIGNE_MARQUEURS.matcher(ligne);
            if (marqueur.find()) {
                assertThat(enonceCourant).as("une ligne de marqueurs sans enonce precedent").isNotNull();
                List<String> tokens = new ArrayList<>();
                for (String token : marqueur.group(1).split(",")) {
                    tokens.add(token.strip());
                }
                marqueurs.put(enonceCourant, tokens);
            }
        }
        return marqueurs;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET));
    }

    /** Parametree par la source, pour etre mordue sur le jeu fautif comme sur le paquet servi. */
    private static List<ItemAnalyse> itemsAnalyses(ContentSource source) {
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            Matcher matcher = ENONCE.matcher(item.enonce());
            assertThat(matcher.matches()).as("ADP-CE1-6 : forme de l'enonce '%s'", item.enonce()).isTrue();
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            int s0 = unites(a) + unites(b);
            int c0 = s0 / 10;
            int s1 = dizaines(a) + dizaines(b) + c0;
            int c1 = s1 / 10;
            analyses.add(new ItemAnalyse(item, a, b, a + b, c0 == 1, c1 == 1));
        }
        return analyses;
    }

    /** FER-2 : au moins dix items servent la ligne. */
    @Test
    void lePaquetCompteAuMoinsDixItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsToutesDistinctes() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.distracteurs()).as("distracteurs de '%s'", item.enonce()).hasSize(2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s'", item.enonce()).hasSize(3);
        }
    }

    /** La bonne reponse est la somme exacte des deux operandes. */
    @Test
    void laBonneReponseEstLaSommeExacte() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            assertThat(Integer.parseInt(analyse.question().bonneReponse()))
                    .as("bonne reponse de '%s'", analyse.question().enonce())
                    .isEqualTo(analyse.bonne());
        }
    }

    /**
     * ADP-CE1-6 : l'enonce reconnait strictement le motif "a + b = ?", sans lettre ni virgule, et
     * aucune proposition ne porte autre chose que des chiffres (la forme de l'enonce est deja
     * mordue par {@link #itemsAnalyses}, qui echoue sur tout enonce qui ne matche pas {@link #ENONCE}).
     */
    @Test
    void aucuneLettreNiVirguleEtTouteLePropositionEstNumerique() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.bonneReponse()).as("ADP-CE1-6 : bonne reponse de '%s'", item.enonce())
                    .matches(PROPOSITION_NUMERIQUE);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur).as("ADP-CE1-6 : distracteur '%s' de '%s'", distracteur,
                        item.enonce()).matches(PROPOSITION_NUMERIQUE);
            }
        }
    }

    /** ADP-CE1-2, premiere clause : chaque item comporte au moins une retenue. */
    @Test
    void chaqueItemComporteAuMoinsUneRetenue() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            assertThat(analyse.retenueUnites() || analyse.retenueDizaines())
                    .as("ADP-CE1-2 : '%s' ne porte aucune retenue", analyse.question().enonce())
                    .isTrue();
        }
    }

    /**
     * ADP-CE1-2 : les deux tailles de nombres (deux operandes a deux chiffres, deux operandes a
     * trois chiffres) sont chacune servies par au moins trois items, au moins deux items portent une
     * retenue sur la colonne des dizaines (vers les centaines) et au moins un item porte deux
     * retenues.
     */
    @Test
    void lesTaillesEtLesRetenuesTiennentLesSeuilsDAdpCe12() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long deuxChiffres = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 2 && chiffresDe(analyse.b()) == 2)
                .count();
        long troisChiffres = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 3 && chiffresDe(analyse.b()) == 3)
                .count();
        long retenueDizaines = analyses.stream().filter(ItemAnalyse::retenueDizaines).count();
        long deuxRetenues = analyses.stream()
                .filter(analyse -> analyse.retenueUnites() && analyse.retenueDizaines()).count();

        assertThat(deuxChiffres).as("ADP-CE1-2 : au moins trois items aux deux operandes de deux"
                + " chiffres").isGreaterThanOrEqualTo(3);
        assertThat(troisChiffres).as("ADP-CE1-2 : au moins trois items aux deux operandes de trois"
                + " chiffres").isGreaterThanOrEqualTo(3);
        assertThat(retenueDizaines).as("ADP-CE1-2 : au moins deux items avec retenue des dizaines"
                + " vers les centaines").isGreaterThanOrEqualTo(2);
        assertThat(deuxRetenues).as("ADP-CE1-2 : au moins un item a deux retenues")
                .isGreaterThanOrEqualTo(1);
    }

    /**
     * ADP-CE1-3 : au moins les deux tiers des items mettent en jeu un operande de trois chiffres ou
     * produisent une somme superieure a 100 - pour ne pas reproduire sous un autre code la matiere
     * deja servie par MAT-CE1-03 ({@code ce1-additions-jusqu-a-100}).
     */
    @Test
    void auMoinsLesDeuxTiersDesItemsOntUnOperandeDeTroisChiffresOuUneSommeSuperieureA100() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long qualifiants = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 3 || chiffresDe(analyse.b()) == 3
                        || analyse.bonne() > 100)
                .count();
        int seuil = (int) Math.ceil(2.0 * analyses.size() / 3);
        assertThat(qualifiants).as("ADP-CE1-3 : %d items qualifiants sur %d, seuil %d",
                qualifiants, analyses.size(), seuil).isGreaterThanOrEqualTo(seuil);
    }

    /**
     * ADP-CE1-4 : les trois propositions ont le meme nombre de chiffres, et au moins un distracteur
     * partage le chiffre des unites avec la bonne reponse. Ce dernier point est garanti par
     * construction (aucune des trois formes d'ADP-CE1-5 ne touche la colonne des unites), et verifie
     * ici comme n'importe quelle autre propriete de ce paquet, plutot que suppose.
     */
    @Test
    void lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            int chiffresBonneReponse = item.bonneReponse().length();
            char uniteBonneReponse = item.bonneReponse().charAt(chiffresBonneReponse - 1);
            boolean chiffreDesUnitesPartage = false;
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.length()).as("ADP-CE1-4 : '%s' (item '%s') n'a pas le meme"
                        + " nombre de chiffres que la bonne reponse '%s'", distracteur, item.enonce(),
                        item.bonneReponse()).isEqualTo(chiffresBonneReponse);
                if (distracteur.charAt(distracteur.length() - 1) == uniteBonneReponse) {
                    chiffreDesUnitesPartage = true;
                }
            }
            assertThat(chiffreDesUnitesPartage).as("ADP-CE1-4 : item '%s', aucun distracteur ne"
                    + " partage le chiffre des unites de la bonne reponse '%s'", item.enonce(),
                    item.bonneReponse()).isTrue();
        }
    }

    /**
     * ADP-CE1-5, le juge : pur, il ne lit ni disque ni classpath et recoit exactement ce que
     * {@link #itemsAnalyses} et {@link #marqueursParEnonce} produisent. C'est ce qui permet de le
     * mordre sur le jeu fautif commite comme sur le paquet servi (ADR-0013 obligation 3).
     */
    private static void verifierMarqueurs(List<ItemAnalyse> analyses, Map<String, List<String>> marqueurs) {
        for (ItemAnalyse analyse : analyses) {
            String enonce = analyse.question().enonce();
            List<String> tokens = marqueurs.get(enonce);
            assertThat(tokens).as("ADP-CE1-5 : aucun marqueur trouve pour '%s'", enonce).isNotNull();
            List<String> distracteurs = analyse.question().distracteurs();
            assertThat(tokens).as("ADP-CE1-5 : '%s', un marqueur par distracteur", enonce)
                    .hasSameSizeAs(distracteurs);

            for (int i = 0; i < distracteurs.size(); i++) {
                String token = tokens.get(i);
                int valeurDeclaree = Integer.parseInt(distracteurs.get(i));
                Integer valeurCalculee = valeurDe(token, analyse.a(), analyse.b());
                assertThat(valeurCalculee).as("ADP-CE1-5 : '%s', marqueur '%s' sans valeur (item sans"
                        + " la retenue que ce marqueur suppose)", enonce, token).isNotNull();
                assertThat(valeurDeclaree).as("ADP-CE1-5 : '%s', distracteur %d ('%s')", enonce, i,
                        token).isEqualTo(valeurCalculee);
                assertThat(valeurDeclaree).as("ADP-CE1-5 : '%s', distracteur positif", enonce)
                        .isPositive();
            }
        }
    }

    /**
     * ADP-CE1-5 : chaque distracteur declare un marqueur, et la valeur qu'il calcule depuis les
     * operandes est celle que le paquet sert - entier positif, distinct des deux autres propositions.
     */
    @Test
    void chaqueDistracteurEstUneErreurNommeeDeclareeEtCalculeeDepuisLesOperandes() {
        verifierMarqueurs(itemsAnalyses(), marqueursParEnonce(FICHIER_CONTENU));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou, traverse par la lecture disque, le
     * chargeur de contenu reel et le recensement des marqueurs - pas seulement par un objet mute en
     * memoire. Le premier distracteur de son seul item declare "oubliee(unites)" mais porte 65 au
     * lieu des 64 que l'arithmetique en colonnes calcule depuis 48 et 26.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMarqueurErrone() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve("contenu/ce1-additions-posees-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-additions-posees-fautif");
        List<ItemAnalyse> analyses = itemsAnalyses(source);
        Map<String, List<String>> marqueurs = marqueursParEnonce(fichierFautif);

        assertThatThrownBy(() -> verifierMarqueurs(analyses, marqueurs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADP-CE1-5")
                .hasMessageContaining("48 + 26");
    }

    private static Integer valeurDe(String token, int a, int b) {
        if (MARQUEUR_MAUVAISE_COLONNE.equals(token)) {
            return retenueMauvaiseColonne(a, b);
        }
        Matcher matcher = MARQUEUR_AVEC_RETENUE.matcher(token);
        assertThat(matcher.matches()).as("marqueur ADP-CE1-5 inconnu : '%s'", token).isTrue();
        Retenue retenue = retenueDe(matcher.group(2));
        return "oubliee".equals(matcher.group(1)) ? retenueOubliee(a, b, retenue)
                : retenueReporteeDeuxFois(a, b, retenue);
    }

    /** ADP-CE1-1 : la consigne de catalogue demande de poser l'operation, meme si le produit ne verifie jamais la technique posee elle-meme. */
    @Test
    void laConsigneDeCatalogueDemandeDePoser() {
        Map<?, ?> corps = new Yaml().load(lire(CATALOGUE));
        List<?> jeux = (List<?>) corps.get("jeux");
        Map<?, ?> entree = jeux.stream()
                .map(jeu -> (Map<?, ?>) jeu)
                .filter(jeu -> PAQUET.equals(jeu.get("paquet")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("aucune entree de catalogue pour " + PAQUET));
        assertThat(String.valueOf(entree.get("consigne"))).as("ADP-CE1-1 : consigne de catalogue")
                .contains("Pose");
    }

    /** ADR-0081 : ce paquet declare MAT-CE1-04, et uniquement elle. */
    @Test
    void cePaquetDeclareMatCe104EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-04"));
    }

    private static int chiffresDe(int valeur) {
        return Integer.toString(valeur).length();
    }

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

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
