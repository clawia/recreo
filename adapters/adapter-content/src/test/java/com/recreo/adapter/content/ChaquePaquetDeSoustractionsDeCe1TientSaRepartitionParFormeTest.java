package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * SOU-CE1-1 et SOU-CE1-2, arbitrage de nadia-prof du 2026-09-22
 * ({@code squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md},
 * section 1) : <b>dans un paquet de soustractions de CE1, la repartition des tirages tient sept
 * bandes, et le soustracteur nul comme le resultat nul ne depassent pas 5 % chacun.</b>
 *
 * <p><b>Le perimetre est derive de la competence declaree, pas de la forme de l'enonce</b>
 * (ADR-0097, decision 2). C'est la difference avec {@link
 * ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest}, qui derive son perimetre d'un motif
 * sur {@code a + b = ?} : ce motif fonctionne pour les additions parce qu'aucun autre paquet de CE1
 * ne prendrait cette forme par accident, mais l'ADR retient une seule regle de perimetre pour les
 * trois sous-parties neuves de {@code MAT-CE1-03}, et c'est celle-ci. La population de ce
 * garde-fou est donc l'ensemble des paquets qui declarent le niveau {@code CE1}, la matiere
 * <i>Mathematiques</i> et la competence exacte allouee a
 * {@link ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.SousPartieDeMathematiquesCe1#SOUSTRACTIONS}
 * - reutilisee ici plutot que redefinie, pour qu'une seule allocation gouverne les deux
 * garde-fous.
 *
 * <p><b>L'ordre de priorite des sept cases fait partie de la clause</b> (arbitrage, section 1.3) :
 * un couple appartient a la <b>premiere</b> case dont il remplit la condition, jamais a deux. Sans
 * cet ordre, {@code 78 - 60} serait a la fois une "difference facile" (18 <= 20) et une
 * "soustraction d'un nombre rond" (60 est un multiple de dix), et deux personnes compteraient deux
 * choses differentes. {@link Forme} le declare dans cet ordre.
 *
 * <p><b>Ce que ce garde-fou ne dit pas</b> : que le paquet travaille reellement {@code MAT-CE1-03},
 * que l'ordre des manches soit bon, que l'indice aide, que chaque enonce soit exact, que les
 * distracteurs correspondent a une erreur identifiable (arbitrage, section 1.4). Aucun de ces
 * points n'est automatise (avis de faisabilite de s38, section 12) ; ils restent gardes par
 * {@code nadia-prof} a l'audit.
 */
class ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest");

    private static final String NIVEAU_GARDE = "CE1";

    /** Forme repliee ; la comparaison de matiere suit le meme repliage qu'ADR-0097. */
    private static final String MATIERE_GARDEE = "mathematiques";

    private static final String COMPETENCE_GARDEE = ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
            .SousPartieDeMathematiquesCe1.SOUSTRACTIONS.competence();

    /**
     * Un enonce de soustraction, et rien d'autre : deux entiers separes par un moins. Meme classe
     * de blancs qu'{@link ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest} (ADR-0067,
     * TYP-1) : un enonce conforme du depot porte un blanc insecable devant le point
     * d'interrogation, qu'un {@code \s} de Java ne reconnait pas.
     */
    private static final String BLANC = "[\\s\\u00A0\\u202F]";

    private static final Pattern SOUSTRACTION = Pattern.compile(
            "^" + BLANC + "*(\\d+)" + BLANC + "*-" + BLANC + "*(\\d+)" + BLANC + "*=?"
                    + BLANC + "*\\??" + BLANC + "*$");

    /**
     * Les sept cases de SOU-CE1-2, <b>dans l'ordre de priorite de la section 1.3</b>, chacune avec
     * sa bande en pourcentage, bornes incluses.
     */
    enum Forme {
        SOUSTRACTEUR_NUL("soustracteur nul", 0, 5),
        RESULTAT_NUL("resultat nul", 0, 5),
        DIFFERENCE_FACILE("difference facile", 10, 25),
        NOMBRE_ROND("soustraction d'un nombre rond", 10, 20),
        UN_CHIFFRE_SOUSTRAIT("un chiffre soustrait", 15, 30),
        SANS_EMPRUNT("deux a deux chiffres, sans emprunt", 10, 30),
        AVEC_EMPRUNT("deux a deux chiffres, avec emprunt", 15, 30);

        private final String libelle;
        private final int bandeBasse;
        private final int bandeHaute;

        Forme(String libelle, int bandeBasse, int bandeHaute) {
            this.libelle = libelle;
            this.bandeBasse = bandeBasse;
            this.bandeHaute = bandeHaute;
        }
    }

    /** Le juge de forme, pur et sans etat : traduction litterale du tableau de la section 1.3. */
    static Forme formeDe(int a, int b) {
        if (b == 0) {
            return Forme.SOUSTRACTEUR_NUL;
        }
        if (a == b) {
            return Forme.RESULTAT_NUL;
        }
        if (a - b <= 20) {
            return Forme.DIFFERENCE_FACILE;
        }
        if (b % 10 == 0) {
            return Forme.NOMBRE_ROND;
        }
        if (b < 10) {
            return Forme.UN_CHIFFRE_SOUSTRAIT;
        }
        if (a % 10 >= b % 10) {
            return Forme.SANS_EMPRUNT;
        }
        return Forme.AVEC_EMPRUNT;
    }

    /** Un paquet reduit a ce que cette regle juge : son identifiant et ses couples. */
    record PaquetDeSoustractions(String id, List<int[]> couples) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement. Un paquet entre dans la population s'il declare le niveau, la matiere et la
     * competence gardes (ADR-0097), et si <b>tous</b> ses items sont une soustraction : un paquet
     * qui declarerait la competence sans porter des soustractions sortirait du perimetre plutot que
     * d'y entrer a moitie.
     */
    static List<PaquetDeSoustractions> paquetsDeSoustractionsDuNiveau(Path catalogue,
            Path repertoireDeContenu) {
        if (AucunTitreDeJeuNePorteSonNiveauTest.entreesServies(catalogue, repertoireDeContenu)
                .isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, le recensement de ce"
                    + " garde-fou n'a alors rien lu");
        }
        List<PaquetDeSoustractions> paquets = new ArrayList<>();
        try (Stream<Path> arborescence = Files.walk(repertoireDeContenu)) {
            arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted()
                    .forEach(fichier -> retenir(fichier, paquets));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return paquets;
    }

    private static void retenir(Path fichier, List<PaquetDeSoustractions> paquets) {
        if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                || !NIVEAU_GARDE.equals(String.valueOf(descripteur.get("niveau")).strip())
                || !MATIERE_GARDEE.equals(ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
                        .replie(String.valueOf(descripteur.get("matiere"))))
                || !COMPETENCE_GARDEE.equals(String.valueOf(descripteur.get("competence")).strip())
                || !(corps.get("items") instanceof List<?> items)
                || items.isEmpty()) {
            return;
        }
        List<int[]> couples = new ArrayList<>();
        for (Object item : items) {
            if (!(item instanceof Map<?, ?> champs)) {
                return;
            }
            Matcher soustraction = SOUSTRACTION.matcher(String.valueOf(champs.get("enonce")));
            if (!soustraction.matches()) {
                return;
            }
            couples.add(new int[] {Integer.parseInt(soustraction.group(1)),
                    Integer.parseInt(soustraction.group(2))});
        }
        paquets.add(new PaquetDeSoustractions(String.valueOf(corps.get("id")), couples));
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * produit.
     */
    static void verifier(List<PaquetDeSoustractions> paquets) {
        if (paquets.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun paquet de soustractions de " + NIVEAU_GARDE
                    + " dans le contenu servi. SOU-CE1-2 ne porte alors sur rien, et un garde-fou"
                    + " qui ne trouve plus sa population le dit au lieu de passer");
        }
        List<String> fautes = new ArrayList<>();
        for (PaquetDeSoustractions paquet : paquets) {
            Map<Forme, Integer> compte = new EnumMap<>(Forme.class);
            for (Forme forme : Forme.values()) {
                compte.put(forme, 0);
            }
            for (int[] couple : paquet.couples()) {
                Forme forme = formeDe(couple[0], couple[1]);
                compte.put(forme, compte.get(forme) + 1);
            }
            int total = paquet.couples().size();
            for (Forme forme : Forme.values()) {
                double proportion = 100.0 * compte.get(forme) / total;
                if (proportion < forme.bandeBasse || proportion > forme.bandeHaute) {
                    fautes.add(String.format(java.util.Locale.ROOT,
                            "%s : forme \"%s\" a %.2f %% (%d items sur %d), hors de la bande"
                                    + " %d-%d %% que SOU-CE1-2 fixe",
                            paquet.id(), forme.libelle, proportion, compte.get(forme), total,
                            forme.bandeBasse, forme.bandeHaute));
                }
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("repartition par forme (SOU-CE1-2) : " + fautes);
        }
    }

    @Test
    void chaquePaquetDeSoustractionsDeCe1ServiTientLesSeptBandes() {
        verifier(paquetsDeSoustractionsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Le recensement atteint bien le materiau reel. Sans cette assertion, les morsures ci-dessous
     * pourraient toutes passer sur une population que le recensement n'a jamais trouvee.
     */
    @Test
    void leRecensementTrouveLePaquetDeSoustractionsServi() {
        assertThat(paquetsDeSoustractionsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI))
                .extracting(PaquetDeSoustractions::id)
                .contains("ce1-soustractions-jusqu-a-100");
    }

    /** Morsure 1 : le plafond de SOU-CE1-1 sur le soustracteur nul. */
    @Test
    void unPaquetQuiDepasseLePlafondDuSoustracteurNulEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.SOUSTRACTEUR_NUL, 1000);
        ajouter(couples, Forme.RESULTAT_NUL, 300);
        ajouter(couples, Forme.DIFFERENCE_FACILE, 1700);
        ajouter(couples, Forme.NOMBRE_ROND, 1500);
        ajouter(couples, Forme.UN_CHIFFRE_SOUSTRAIT, 2000);
        ajouter(couples, Forme.SANS_EMPRUNT, 1500);
        ajouter(couples, Forme.AVEC_EMPRUNT, 2000);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDeSoustractions("zero-en-trop", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("soustracteur nul")
                .hasMessageContaining("0-5 %");
    }

    /** Morsure 2 : le plafond de SOU-CE1-1 sur le resultat nul, distinct du soustracteur nul. */
    @Test
    void unPaquetQuiDepasseLePlafondDuResultatNulEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.SOUSTRACTEUR_NUL, 200);
        ajouter(couples, Forme.RESULTAT_NUL, 1000);
        ajouter(couples, Forme.DIFFERENCE_FACILE, 1800);
        ajouter(couples, Forme.NOMBRE_ROND, 1500);
        ajouter(couples, Forme.UN_CHIFFRE_SOUSTRAIT, 2000);
        ajouter(couples, Forme.SANS_EMPRUNT, 1500);
        ajouter(couples, Forme.AVEC_EMPRUNT, 2000);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDeSoustractions("resultat-nul-en-trop", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("resultat nul")
                .hasMessageContaining("0-5 %");
    }

    /** Morsure 3 : le plancher de la ligne 7 (avec emprunt), celle ou la strategie du complement a
     * la dizaine se rejoue reellement dans la soustraction (arbitrage, section 1.3). */
    @Test
    void unPaquetSansAssezDEmpruntEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.DIFFERENCE_FACILE, 2500);
        ajouter(couples, Forme.NOMBRE_ROND, 2000);
        ajouter(couples, Forme.UN_CHIFFRE_SOUSTRAIT, 3000);
        ajouter(couples, Forme.SANS_EMPRUNT, 2000);
        ajouter(couples, Forme.AVEC_EMPRUNT, 500);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDeSoustractions("sans-emprunt", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("avec emprunt")
                .hasMessageContaining("15-30 %");
    }

    /** Morsure 4 : une population vide ne rend pas ce garde-fou vert (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>. Le jeu fautif porte deux
     * paquets qui declarent la competence des soustractions - l'un conforme, l'autre hors bandes -
     * pour prouver que la regle <b>discrimine</b>, et non qu'elle refuse tout paquet qu'on lui
     * donne.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif() {
        List<PaquetDeSoustractions> fautifs = paquetsDeSoustractionsDuNiveau(
                CONTRE_EXEMPLE.resolve("catalogue.yaml"), CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux paquets")
                .extracting(PaquetDeSoustractions::id)
                .containsExactly("paquet-conforme-ce1", "paquet-fautif-ce1");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-fautif-ce1")
                .hasMessageNotContaining("paquet-conforme-ce1");
    }

    /**
     * Le perimetre derive de la competence, prouve dans les deux sens : un paquet de CE1 qui
     * declare la competence des additions - meme s'il porte des items en {@code a - b = ?} - sort
     * de la population des soustractions, et le recensement ne le confond pas avec elle.
     */
    @Test
    void unPaquetDontLaCompetenceNEstPasCelleDesSoustractionsSortDuPerimetre() {
        assertThat(paquetsDeSoustractionsDuNiveau(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu")))
                .extracting(PaquetDeSoustractions::id)
                .doesNotContain("paquet-additions-ce1");
    }

    /**
     * La partition est exhaustive et ordonnee : tout couple du domaine (0 a 100, soustracteur au
     * plus egal au premier terme) recoit une case et une seule. Verifie par balayage exhaustif, pas
     * sur un echantillon.
     */
    @Test
    void laPartitionOrdonneeCouvreToutCoupleDeLEchelle100() {
        int couverts = 0;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= a; b++) {
                assertThat(formeDe(a, b)).as("%d - %d", a, b).isNotNull();
                couverts++;
            }
        }
        assertThat(couverts).isEqualTo(5151);
        assertThat(formeDe(10, 0)).as("l'ordre decide : le soustracteur nul avant le resultat nul"
                + " quand a et b valent tous deux 0").isEqualTo(Forme.SOUSTRACTEUR_NUL);
        assertThat(formeDe(0, 0)).as("l'ordre decide : le soustracteur nul avant le resultat nul")
                .isEqualTo(Forme.SOUSTRACTEUR_NUL);
        assertThat(formeDe(78, 60)).as("l'ordre decide : la difference facile avant le nombre rond")
                .isEqualTo(Forme.DIFFERENCE_FACILE);
        assertThat(formeDe(50, 20)).as("l'ordre decide : le nombre rond avant sans-emprunt/avec-emprunt")
                .isEqualTo(Forme.NOMBRE_ROND);
    }

    /** Un couple de la forme demandee, repete autant de fois que la proportion l'exige. */
    private static void ajouter(List<int[]> couples, Forme forme, int combien) {
        int[] couple = switch (forme) {
            case SOUSTRACTEUR_NUL -> new int[] {45, 0};
            case RESULTAT_NUL -> new int[] {34, 34};
            case DIFFERENCE_FACILE -> new int[] {23, 15};
            case NOMBRE_ROND -> new int[] {87, 30};
            case UN_CHIFFRE_SOUSTRAIT -> new int[] {45, 3};
            case SANS_EMPRUNT -> new int[] {58, 13};
            case AVEC_EMPRUNT -> new int[] {52, 27};
        };
        if (formeDe(couple[0], couple[1]) != forme) {
            throw new IllegalStateException("le couple temoin de " + forme + " n'est pas de sa forme");
        }
        for (int i = 0; i < combien; i++) {
            couples.add(couple);
        }
    }
}
