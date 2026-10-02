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
 * S46-MAT-SOUSTRACTION ({@code docs/backlog/backlog.md}, sprint 46), le paquet
 * {@code ce1-soustractions-posees}, ligne {@code MAT-CE1-05} - soustraction posee avec emprunt.
 * Arbitrage pedagogique prealable cite dans la story
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md}, section 5.2),
 * amende le 2026-10-01 par
 * {@code squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md} (section 2) : une
 * quatrieme forme de distracteur (l'emprunt reporte deux fois) et une regle de rang sur le paquet
 * entier, items sans emprunt compris.
 *
 * <p>Les proprietes generiques (indice non vide, aucune proposition egale a une autre, rang de la
 * bonne reponse non predictible <b>sur les items a trois propositions</b>) sont deja gardees par
 * les garde-fous derives du repertoire de contenu ; ce fichier verifie les conditions
 * {@code SOP-CE1-1} a {@code SOP-CE1-6} qu'aucun garde-fou generique ne connait, dont la regle de
 * rang <b>etendue au paquet entier</b> que le garde-fou generique ne lit pas (il ignore les items
 * a deux propositions, voir {@link #laStrategieToujoursLaPlusPetitePropositionNeGagnePasSurPlusDeLaMoitieDuPaquetEntier}).</p>
 *
 * <p><b>{@code SOP-CE1-5}</b> : chaque distracteur est l'une des quatre erreurs nommees, calculee
 * a partir des operandes - {@link #oubliee}, {@link #nonReportee}, {@link #somme} et
 * {@link #reporteeDeuxFois} traduisent litteralement l'arithmetique en colonnes decrite par
 * l'amendement. La declaration dans le paquet se fait par un marqueur en commentaire sur la ligne
 * {@code distracteurs}, relu par {@link #marqueursParEnonce} et compare au calcul : un distracteur
 * dont le marqueur ne correspond pas a la valeur chargee, ou dont aucun marqueur n'est trouve, fait
 * rougir ce test plutot que de passer sans etre verifie. Sur un item marque
 * {@code reportee-deux-fois}, {@link #admissionReporteeDeuxFois} rejoue la regle d'admission : la
 * colonne qui recoit l'emprunt en double ne doit changer de statut d'emprunt ni dans un sens ni
 * dans l'autre par rapport au calcul juste.</p>
 */
class PaquetCe1SoustractionsPoseesTest {

    private static final String PAQUET = "ce1-soustractions-posees";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1SoustractionsPoseesTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1SoustractionsPoseesTest";

    /** SOP-CE1-6 : strictement "a - b = ?", le blanc insecable d'ADR-0067 (TYP-1) devant "?". */
    private static final Pattern ENONCE = Pattern.compile("^(\\d+) - (\\d+) =\\u00A0\\?$");

    /** SOP-CE1-6 : aucune proposition ne porte autre chose que des chiffres. */
    private static final Pattern PROPOSITION_NUMERIQUE = Pattern.compile("^\\d+$");

    /** Une ligne "enonce:" du fichier source, pour associer chaque marqueur a son item. */
    private static final Pattern LIGNE_ENONCE = Pattern.compile("enonce:\\s*\"([^\"]*)\"");

    /** Une ligne "distracteurs:" suivie de son marqueur SOP-CE1-5 en commentaire. */
    private static final Pattern LIGNE_MARQUEURS =
            Pattern.compile("distracteurs:.*#\\s*SOP-CE1-5:\\s*(.+)$");

    /** Un marqueur "reportee-deux-fois(unites)" ou "reportee-deux-fois(dizaines)". */
    private static final Pattern MARQUEUR_REPORTEE_DEUX_FOIS =
            Pattern.compile("^reportee-deux-fois\\((unites|dizaines)\\)$");

    private static final String MARQUEUR_OUBLIEE = "oubliee";

    private static final String MARQUEUR_NON_REPORTEE = "non-reportee";

    private static final String MARQUEUR_SOMME = "somme";

    private enum ColonneEmprunt {
        UNITES, DIZAINES
    }

    private static ColonneEmprunt colonneDe(String texte) {
        return "unites".equals(texte) ? ColonneEmprunt.UNITES : ColonneEmprunt.DIZAINES;
    }

    private static int chiffre(int n, int rang) {
        return (n / pow10(rang)) % 10;
    }

    private static int pow10(int rang) {
        return switch (rang) {
            case 0 -> 1;
            case 1 -> 10;
            default -> 100;
        };
    }

    /**
     * SOP-CE1-5, premiere forme : soustraction colonne par colonne en valeur absolue, sans aucun
     * emprunt (52 - 27 -&gt; 35, l'unite calcule abs(2 - 7) = 5, la dizaine abs(5 - 2) = 3).
     */
    static int oubliee(int a, int b) {
        int total = 0;
        for (int rang = 0; rang < 3; rang++) {
            total += Math.abs(chiffre(a, rang) - chiffre(b, rang)) * pow10(rang);
        }
        return total;
    }

    /**
     * SOP-CE1-5, deuxieme forme : l'emprunt est pris localement sur sa colonne (le chiffre du haut
     * recoit 10 si besoin) mais n'est jamais reporte a la colonne suivante - chaque colonne se
     * calcule donc independamment des autres, sans aucune propagation d'emprunt.
     */
    static int nonReportee(int a, int b) {
        int total = 0;
        for (int rang = 0; rang < 3; rang++) {
            int difference = chiffre(a, rang) - chiffre(b, rang);
            total += Math.floorMod(difference, 10) * pow10(rang);
        }
        return total;
    }

    /** SOP-CE1-5, troisieme forme : l'addition au lieu de la difference. */
    static int somme(int a, int b) {
        return a + b;
    }

    /**
     * SOP-CE1-5, quatrieme forme (amendement du 2026-10-01) : pour un emprunt fait sur la colonne
     * de rang {@code i}, la valeur est {@code bonneReponse - 10^(i+1)}, toujours inferieure a la
     * bonne reponse.
     */
    static int reporteeDeuxFois(int bonneReponse, ColonneEmprunt colonne) {
        return switch (colonne) {
            case UNITES -> bonneReponse - 10;
            case DIZAINES -> bonneReponse - 100;
        };
    }

    /**
     * La chaine d'emprunts du calcul juste, colonne par colonne (0 = unites, 1 = dizaines,
     * 2 = centaines) : {@code emprunts[i]} vaut {@code true} quand la colonne {@code i} a besoin
     * d'emprunter sur la colonne {@code i + 1}.
     */
    private static boolean[] empruntsVrais(int a, int b) {
        boolean[] emprunts = new boolean[3];
        int retenue = 0;
        for (int rang = 0; rang < 3; rang++) {
            emprunts[rang] = chiffre(a, rang) - retenue < chiffre(b, rang);
            retenue = emprunts[rang] ? 1 : 0;
        }
        return emprunts;
    }

    /**
     * SOP-CE1-5, regle d'admission de la quatrieme forme : la forme n'est admise sur la colonne de
     * rang {@code i} que si (1) le calcul juste emprunte reellement sur cette colonne, et (2) la
     * colonne {@code i + 1}, qui recoit l'emprunt en double, ne change pas de statut d'emprunt par
     * rapport au calcul juste - ni emprunt de plus, ni emprunt de moins.
     */
    static boolean admissionReporteeDeuxFois(int a, int b, ColonneEmprunt colonne) {
        int i = colonne == ColonneEmprunt.UNITES ? 0 : 1;
        boolean[] emprunts = empruntsVrais(a, b);
        if (!emprunts[i]) {
            return false;
        }
        int colonneSuivante = i + 1;
        boolean empruntVrai = chiffre(a, colonneSuivante) - 1 < chiffre(b, colonneSuivante);
        boolean empruntAvecDoubleDebit = chiffre(a, colonneSuivante) - 2 < chiffre(b, colonneSuivante);
        return empruntVrai == empruntAvecDoubleDebit;
    }

    private record ItemAnalyse(Question question, int a, int b, int bonne) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement des marqueurs SOP-CE1-5, par enonce : une lecture du fichier source distincte
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
            assertThat(matcher.matches()).as("SOP-CE1-6 : forme de l'enonce '%s'", item.enonce()).isTrue();
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            analyses.add(new ItemAnalyse(item, a, b, a - b));
        }
        return analyses;
    }

    /** FER-2 : au moins dix items servent la ligne. */
    @Test
    void lePaquetCompteAuMoinsDixItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    void chaqueItemPorteAuPlusDeuxDistracteursEtAucuneProportionEgale() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.distracteurs()).as("distracteurs de '%s'", item.enonce())
                    .hasSizeBetween(1, 2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s'", item.enonce())
                    .hasSize(item.distracteurs().size() + 1);
        }
    }

    /** La bonne reponse est la difference exacte des deux operandes, jamais negative. */
    @Test
    void laBonneReponseEstLaDifferenceExacteEtJamaisNegativeEtBInferieurOuEgalAA() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            assertThat(Integer.parseInt(analyse.question().bonneReponse()))
                    .as("bonne reponse de '%s'", analyse.question().enonce())
                    .isEqualTo(analyse.bonne());
            assertThat(analyse.bonne()).as("SOP-CE1-6 : resultat de '%s'", analyse.question().enonce())
                    .isNotNegative();
            assertThat(analyse.b()).as("SOP-CE1-6 : '%s', 0 <= b <= a <= 1000",
                    analyse.question().enonce()).isBetween(0, analyse.a());
            assertThat(analyse.a()).as("SOP-CE1-6 : '%s', a <= 1000", analyse.question().enonce())
                    .isLessThanOrEqualTo(1000);
        }
    }

    /**
     * SOP-CE1-6 : l'enonce reconnait strictement le motif "a - b = ?", sans lettre, sans virgule,
     * sans habillage ni unite (la forme de l'enonce est deja mordue par {@link #itemsAnalyses}, qui
     * echoue sur tout enonce qui ne matche pas {@link #ENONCE}), et aucune proposition ne porte
     * autre chose que des chiffres.
     */
    @Test
    void aucuneLettreNiVirguleEtToutePropositionEstNumerique() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.bonneReponse()).as("SOP-CE1-6 : bonne reponse de '%s'", item.enonce())
                    .matches(PROPOSITION_NUMERIQUE);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur).as("SOP-CE1-6 : distracteur '%s' de '%s'", distracteur,
                        item.enonce()).matches(PROPOSITION_NUMERIQUE);
            }
        }
    }

    /**
     * SOP-CE1-2 : les deux tailles de nombres (deux chiffres, trois chiffres) sont chacune servies
     * par au moins trois items, au moins la moitie des items comportent un emprunt, au moins un
     * item comporte deux emprunts, et au moins deux items n'en comportent aucun.
     */
    @Test
    void lesTaillesEtLesEmpruntsTiennentLesSeuilsDeSop2() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long deuxChiffres = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 2 && chiffresDe(analyse.b()) == 2)
                .count();
        long troisChiffres = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 3 && chiffresDe(analyse.b()) == 3)
                .count();
        long avecEmprunt = analyses.stream().filter(PaquetCe1SoustractionsPoseesTest::aUnEmprunt).count();
        long deuxEmprunts = analyses.stream()
                .filter(analyse -> nombreDEmprunts(analyse) == 2).count();
        long sansEmprunt = analyses.stream()
                .filter(analyse -> nombreDEmprunts(analyse) == 0).count();

        assertThat(deuxChiffres).as("SOP-CE1-2 : au moins trois items aux deux operandes de deux"
                + " chiffres").isGreaterThanOrEqualTo(3);
        assertThat(troisChiffres).as("SOP-CE1-2 : au moins trois items aux deux operandes de trois"
                + " chiffres").isGreaterThanOrEqualTo(3);
        assertThat(avecEmprunt).as("SOP-CE1-2 : au moins la moitie des items comportent un emprunt")
                .isGreaterThanOrEqualTo((long) Math.ceil(analyses.size() / 2.0));
        assertThat(deuxEmprunts).as("SOP-CE1-2 : au moins un item a deux emprunts")
                .isGreaterThanOrEqualTo(1);
        assertThat(sansEmprunt).as("SOP-CE1-2 : au moins deux items sans aucun emprunt")
                .isGreaterThanOrEqualTo(2);
    }

    /**
     * SOP-CE1-3 : au moins les deux tiers des items mettent en jeu un operande de trois chiffres ou
     * un premier terme superieur a 100 - pour ne pas reproduire sous un autre code la matiere deja
     * servie par MAT-CE1-03 ({@code ce1-soustractions-jusqu-a-100}).
     */
    @Test
    void auMoinsLesDeuxTiersDesItemsOntUnOperandeDeTroisChiffresOuUnPremierTermeSuperieurA100() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long qualifiants = analyses.stream()
                .filter(analyse -> chiffresDe(analyse.a()) == 3 || chiffresDe(analyse.b()) == 3
                        || analyse.a() > 100)
                .count();
        int seuil = (int) Math.ceil(2.0 * analyses.size() / 3);
        assertThat(qualifiants).as("SOP-CE1-3 : %d items qualifiants sur %d, seuil %d",
                qualifiants, analyses.size(), seuil).isGreaterThanOrEqualTo(seuil);
    }

    /**
     * SOP-CE1-4 : les propositions ont le meme nombre de chiffres, et au moins un distracteur
     * partage le chiffre des unites avec la bonne reponse. Porte sur tout item, y compris un item
     * sans aucun emprunt qui ne sert que la somme comme unique distracteur : l'amendement du
     * 2026-10-01 le redit explicitement (fiche de conformite, section 2, "Admission sur un item"),
     * et son absence de verification ici a laisse passer deux items qui ne tenaient pas la clause
     * (fiche de conformite, section 4 du bloc de validation pedagogique du 2026-10-02).
     */
    @Test
    void lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            int chiffresBonneReponse = item.bonneReponse().length();
            char uniteBonneReponse = item.bonneReponse().charAt(chiffresBonneReponse - 1);
            boolean chiffreDesUnitesPartage = false;
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.length()).as("SOP-CE1-4 : '%s' (item '%s') n'a pas le meme"
                        + " nombre de chiffres que la bonne reponse '%s'", distracteur, item.enonce(),
                        item.bonneReponse()).isEqualTo(chiffresBonneReponse);
                if (distracteur.charAt(distracteur.length() - 1) == uniteBonneReponse) {
                    chiffreDesUnitesPartage = true;
                }
            }
            assertThat(chiffreDesUnitesPartage).as("SOP-CE1-4 : item '%s', aucun distracteur ne"
                    + " partage le chiffre des unites de la bonne reponse '%s'", item.enonce(),
                    item.bonneReponse()).isTrue();
        }
    }

    /**
     * SOP-CE1-5, le juge : pur, il ne lit ni disque ni classpath et recoit exactement ce que
     * {@link #itemsAnalyses} et {@link #marqueursParEnonce} produisent. C'est ce qui permet de le
     * mordre sur le jeu fautif commite comme sur le paquet servi (ADR-0013 obligation 3).
     */
    private static void verifierMarqueurs(List<ItemAnalyse> analyses, Map<String, List<String>> marqueurs) {
        for (ItemAnalyse analyse : analyses) {
            String enonce = analyse.question().enonce();
            List<String> tokens = marqueurs.get(enonce);
            assertThat(tokens).as("SOP-CE1-5 : aucun marqueur trouve pour '%s'", enonce).isNotNull();
            List<String> distracteurs = analyse.question().distracteurs();
            assertThat(tokens).as("SOP-CE1-5 : '%s', un marqueur par distracteur", enonce)
                    .hasSameSizeAs(distracteurs);

            for (int i = 0; i < distracteurs.size(); i++) {
                String token = tokens.get(i);
                int valeurDeclaree = Integer.parseInt(distracteurs.get(i));
                Integer valeurCalculee = valeurDe(token, analyse.a(), analyse.b(), analyse.bonne());
                assertThat(valeurCalculee).as("SOP-CE1-5 : '%s', marqueur '%s' sans valeur admise sur"
                        + " cet item", enonce, token).isNotNull();
                assertThat(valeurDeclaree).as("SOP-CE1-5 : '%s', distracteur %d ('%s')", enonce, i,
                        token).isEqualTo(valeurCalculee);
                assertThat(valeurDeclaree).as("SOP-CE1-5 : '%s', distracteur positif", enonce)
                        .isPositive();
            }
        }
    }

    /**
     * SOP-CE1-5 : chaque distracteur declare un marqueur, et la valeur qu'il calcule depuis les
     * operandes est celle que le paquet sert - entier positif, distinct des autres propositions.
     */
    @Test
    void chaqueDistracteurEstUneErreurNommeeDeclareeEtCalculeeDepuisLesOperandes() {
        verifierMarqueurs(itemsAnalyses(), marqueursParEnonce(FICHIER_CONTENU));
    }

    /**
     * SOP-CE1-5, regle d'admission de la quatrieme forme : sur chaque item marque
     * {@code reportee-deux-fois}, la colonne qui recoit l'emprunt en double ne comporte ni emprunt
     * de plus ni emprunt de moins que dans le calcul juste.
     */
    @Test
    void chaqueFormeReporteeDeuxFoisTientLaRegleDAdmission() {
        Map<String, List<String>> marqueurs = marqueursParEnonce(FICHIER_CONTENU);
        for (ItemAnalyse analyse : itemsAnalyses()) {
            List<String> tokens = marqueurs.get(analyse.question().enonce());
            for (String token : tokens) {
                Matcher matcher = MARQUEUR_REPORTEE_DEUX_FOIS.matcher(token);
                if (matcher.matches()) {
                    ColonneEmprunt colonne = colonneDe(matcher.group(1));
                    assertThat(admissionReporteeDeuxFois(analyse.a(), analyse.b(), colonne))
                            .as("SOP-CE1-5 : '%s', regle d'admission de reportee-deux-fois(%s)",
                                    analyse.question().enonce(), matcher.group(1)).isTrue();
                }
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou, traverse par la lecture disque, le
     * chargeur de contenu reel et le recensement des marqueurs - pas seulement par un objet mute en
     * memoire. Le premier distracteur de son seul item declare "non-reportee" mais porte 36 au lieu
     * des 35 que l'arithmetique en colonnes calcule depuis 52 et 27.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMarqueurErrone() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve("contenu/ce1-soustractions-posees-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-soustractions-posees-fautif");
        List<ItemAnalyse> analyses = itemsAnalyses(source);
        Map<String, List<String>> marqueurs = marqueursParEnonce(fichierFautif);

        assertThatThrownBy(() -> verifierMarqueurs(analyses, marqueurs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("SOP-CE1-5")
                .hasMessageContaining("52 - 27");
    }

    /**
     * SOP-CE1-5, regle de rang sur le paquet entier, amendement du 2026-10-01 (fiche de
     * conformite, section 2) : items a deux propositions compris (ceux sans aucun emprunt, qui ne
     * servent que la somme), choisir toujours la plus petite proposition ne donne pas la bonne
     * reponse sur plus de la moitie des items. Le garde-fou generique
     * ({@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}) ne lit que les items a
     * trois propositions ; cette clause couvre le paquet entier, ce que ce garde-fou generique
     * declare explicitement ne pas faire.
     */
    @Test
    void laStrategieToujoursLaPlusPetitePropositionNeGagnePasSurPlusDeLaMoitieDuPaquetEntier() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        long victoires = items.stream().filter(PaquetCe1SoustractionsPoseesTest::laPlusPetiteEstLaBonneReponse).count();
        assertThat(victoires).as("SOP-CE1-5 : la strategie 'toujours la plus petite proposition'"
                + " gagne sur %d items sur %d, plus de la moitie du paquet entier", victoires,
                items.size()).isLessThanOrEqualTo(items.size() / 2);
    }

    private static boolean laPlusPetiteEstLaBonneReponse(Question item) {
        int bonne = Integer.parseInt(item.bonneReponse());
        int minimum = bonne;
        for (String distracteur : item.distracteurs()) {
            minimum = Math.min(minimum, Integer.parseInt(distracteur));
        }
        return minimum == bonne;
    }

    private static boolean aUnEmprunt(ItemAnalyse analyse) {
        boolean[] emprunts = empruntsVrais(analyse.a(), analyse.b());
        return emprunts[0] || emprunts[1];
    }

    private static int nombreDEmprunts(ItemAnalyse analyse) {
        boolean[] emprunts = empruntsVrais(analyse.a(), analyse.b());
        return (emprunts[0] ? 1 : 0) + (emprunts[1] ? 1 : 0);
    }

    /** SOP-CE1-1 : la consigne de catalogue demande de poser l'operation, meme si le produit ne verifie jamais la technique posee elle-meme. */
    @Test
    void laConsigneDeCatalogueDemandeDePoser() {
        Map<?, ?> corps = new Yaml().load(lire(CATALOGUE));
        List<?> jeux = (List<?>) corps.get("jeux");
        Map<?, ?> entree = jeux.stream()
                .map(jeu -> (Map<?, ?>) jeu)
                .filter(jeu -> PAQUET.equals(jeu.get("paquet")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("aucune entree de catalogue pour " + PAQUET));
        assertThat(String.valueOf(entree.get("consigne"))).as("SOP-CE1-1 : consigne de catalogue")
                .contains("Pose");
    }

    /** ADR-0081 : ce paquet declare MAT-CE1-05, et uniquement elle. */
    @Test
    void cePaquetDeclareMatCe105EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-05"));
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

    private static Integer valeurDe(String token, int a, int b, int bonne) {
        if (MARQUEUR_OUBLIEE.equals(token)) {
            return oubliee(a, b);
        }
        if (MARQUEUR_NON_REPORTEE.equals(token)) {
            return nonReportee(a, b);
        }
        if (MARQUEUR_SOMME.equals(token)) {
            return somme(a, b);
        }
        Matcher matcher = MARQUEUR_REPORTEE_DEUX_FOIS.matcher(token);
        assertThat(matcher.matches()).as("marqueur SOP-CE1-5 inconnu : '%s'", token).isTrue();
        return reporteeDeuxFois(bonne, colonneDe(matcher.group(1)));
    }
}
