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
 * ADD-CE1-1 et ADD-CE1-2, arbitrage de nadia-prof du 2026-09-21
 * ({@code squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md}) :
 * <b>dans un paquet d'additions de CE1, la repartition des tirages par forme tient huit bandes, et
 * l'operande nul ne depasse pas 5 %.</b>
 *
 * <p><b>Ce que ce garde-fou existe pour empecher, et il ne l'a pas invente.</b> nadia-prof a mesure
 * que le generateur inchange a {@code sommeMaximale = 100} echoue trois de ses huit bandes - somme
 * <= 20 a 3,78 % pour un plancher de 10 %, double a 0,82 % pour un plancher de 5 %, complement a la
 * dizaine a 9,27 % pour un plancher de 10 % - et que le paquet ainsi obtenu <b>sert une autre ligne
 * de referentiel que celle qu'il declare</b> : {@code MAT-CE1-04} sous le nom de
 * {@code MAT-CE1-03}. Elle a ecrit en clair que, tant qu'aucun garde-fou n'execute ses deux clauses,
 * c'est elle qui les tient en comptant (section 6). C'est ce comptage-la qui est encode ici.
 *
 * <p><b>L'ordre de priorite des huit cases fait partie de la clause, et il n'est pas negociable</b>
 * (arbitrage, section 4.3) : un couple appartient a la <b>premiere</b> case dont il remplit la
 * condition, jamais a deux. Sans cet ordre, {@code 25 + 25} serait a la fois un double et une somme
 * ronde et deux personnes compteraient deux choses differentes. C'est l'ordre sous lequel
 * nadia-prof a mesure sa section 1, et {@link Forme} le declare dans cet ordre.
 *
 * <p><b>Le perimetre est derive, jamais enumere</b> (ADR-0013). La population n'est pas une liste de
 * noms de paquets : c'est l'ensemble des paquets <b>servis</b> dont le descripteur declare le niveau
 * {@code CE1} et dont <b>tous</b> les items sont une addition de deux entiers. Un second paquet
 * d'additions CE1 est donc garde le jour ou il entre, sans qu'une ligne de ce fichier bouge ; un
 * paquet CE1 de soustractions, dont les items ne sont pas des additions, en sort de lui-meme, et
 * c'est correct - {@code ADD-CE1-2} porte sur les additions et le dit dans son intitule.
 *
 * <p><b>Ce que ce garde-fou ne dit pas</b>, et nadia-prof l'a ecrit avant moi (section 6) : que le
 * paquet travaille reellement {@code MAT-CE1-03}, que l'ordre des manches d'une partie soit bon, que
 * l'indice aide, que chaque enonce soit exact. La table rend le glissement plus difficile ; elle ne
 * le rend pas impossible, et aucun automate ne lit une declaration de ligne pour en juger la verite.
 *
 * <p><b>Ce que ce garde-fou ne garde pas non plus, et c'est nadia-prof qui s'en est interdit
 * l'exigence</b> (section 4.4) : qu'une partie de dix manches contienne au moins une manche
 * d'entree. Une repartition conforme laisse ce cas arriver environ une fois sur trois. Elle a ecrit
 * qu'elle ne refuserait pas sur ce motif ; ce fichier ne le verifie donc pas, plutot que de le
 * verifier en silence et de durcir sa porte a sa place.
 */
class ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest");

    private static final String NIVEAU_GARDE = "CE1";

    /**
     * Un enonce d'addition, et rien d'autre : deux entiers separes par un plus.
     *
     * <p>Les blancs insecables sont nommes explicitement, et ce n'est pas une precaution de
     * principe : ADR-0067 (TYP-1) <b>exige</b> un blanc insecable devant le point d'interrogation
     * d'un enonce francais, donc tout enonce conforme au depot en porte un. Un {@code \s} de Java
     * ne couvre ni U+00A0 ni U+202F : sans cette classe, ce garde-fou ne reconnaitrait aucun
     * enonce conforme, sa population serait vide et il passerait au vert sur un paquet qu'il
     * n'aurait jamais lu. Mesure du 2026-09-21 : avec {@code \s} seul, le paquet servi sort du
     * recensement, ce que {@code leRecensementTrouveLePaquetDAdditionsServi} rend rouge.
     */
    private static final String BLANC = "[\\s\\u00A0\\u202F]";

    private static final Pattern ADDITION = Pattern.compile(
            "^" + BLANC + "*(\\d+)" + BLANC + "*\\+" + BLANC + "*(\\d+)" + BLANC + "*=?"
                    + BLANC + "*\\??" + BLANC + "*$");

    /**
     * Les huit cases d'ADD-CE1-2, <b>dans l'ordre de priorite de la section 4.3</b>, chacune avec sa
     * bande en pourcentage, bornes incluses. Cet ordre de declaration est la clause : la case d'un
     * couple est la premiere dont la condition est vraie.
     */
    enum Forme {
        OPERANDE_NUL("operande nul", 0, 5),
        SOMME_JUSQUA_20("somme <= 20", 10, 20),
        DOUBLE("double", 5, 15),
        COMPLEMENT_A_LA_DIZAINE("complement a la dizaine", 10, 20),
        AJOUT_D_UN_NOMBRE_ROND("ajout d'un nombre rond", 10, 20),
        DEUX_CHIFFRES_PLUS_UN_CHIFFRE("deux chiffres plus un chiffre", 15, 25),
        SANS_RETENUE("deux a deux chiffres, sans retenue", 10, 25),
        AVEC_RETENUE("deux a deux chiffres, avec retenue", 10, 20);

        private final String libelle;
        private final int bandeBasse;
        private final int bandeHaute;

        Forme(String libelle, int bandeBasse, int bandeHaute) {
            this.libelle = libelle;
            this.bandeBasse = bandeBasse;
            this.bandeHaute = bandeHaute;
        }
    }

    /**
     * Le juge de forme, pur et sans etat : c'est la traduction litterale du tableau de la section
     * 4.3, condition par condition et dans son ordre.
     */
    static Forme formeDe(int a, int b) {
        if (a == 0 || b == 0) {
            return Forme.OPERANDE_NUL;
        }
        if (a + b <= 20) {
            return Forme.SOMME_JUSQUA_20;
        }
        if (a == b) {
            return Forme.DOUBLE;
        }
        if ((a + b) % 10 == 0 && a % 10 != 0 && b % 10 != 0) {
            return Forme.COMPLEMENT_A_LA_DIZAINE;
        }
        if (a % 10 == 0 || b % 10 == 0) {
            return Forme.AJOUT_D_UN_NOMBRE_ROND;
        }
        if (a < 10 || b < 10) {
            return Forme.DEUX_CHIFFRES_PLUS_UN_CHIFFRE;
        }
        if (a % 10 + b % 10 < 10) {
            return Forme.SANS_RETENUE;
        }
        return Forme.AVEC_RETENUE;
    }

    /** Un paquet reduit a ce que cette regle juge : son identifiant et ses couples. */
    record PaquetDAdditions(String id, List<int[]> couples) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement. Un paquet entre dans la population s'il declare le niveau garde et si
     * <b>tous</b> ses items sont une addition : un paquet mixte n'est pas silencieusement reduit a
     * ses additions, il sort du perimetre, et le cas est nomme plutot que laisse arriver.
     */
    static List<PaquetDAdditions> paquetsDAdditionsDuNiveau(Path catalogue, Path repertoireDeContenu) {
        List<String> servis = AucunTitreDeJeuNePorteSonNiveauTest
                .entreesServies(catalogue, repertoireDeContenu).stream()
                .map(AucunTitreDeJeuNePorteSonNiveauTest.Entree::id)
                .toList();
        List<PaquetDAdditions> paquets = new ArrayList<>();
        try (Stream<Path> arborescence = Files.walk(repertoireDeContenu)) {
            arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted()
                    .forEach(fichier -> retenir(fichier, paquets));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        if (servis.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, le recensement de ce"
                    + " garde-fou n'a alors rien lu");
        }
        return paquets;
    }

    private static void retenir(Path fichier, List<PaquetDAdditions> paquets) {
        if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                || !NIVEAU_GARDE.equals(String.valueOf(descripteur.get("niveau")).strip())
                || !(corps.get("items") instanceof List<?> items)
                || items.isEmpty()) {
            return;
        }
        List<int[]> couples = new ArrayList<>();
        for (Object item : items) {
            if (!(item instanceof Map<?, ?> champs)) {
                return;
            }
            Matcher addition = ADDITION.matcher(String.valueOf(champs.get("enonce")));
            if (!addition.matches()) {
                return;
            }
            couples.add(new int[] {Integer.parseInt(addition.group(1)),
                    Integer.parseInt(addition.group(2))});
        }
        paquets.add(new PaquetDAdditions(String.valueOf(corps.get("id")), couples));
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * produit. C'est ce qui permet de le mordre sur du materiau reel mute en memoire plutot que sur
     * une fixture ecrite de ma main.
     */
    static void verifier(List<PaquetDAdditions> paquets) {
        if (paquets.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun paquet d'additions de " + NIVEAU_GARDE
                    + " dans le contenu servi. ADD-CE1-2 ne porte alors sur rien, et un garde-fou"
                    + " qui ne trouve plus sa population le dit au lieu de passer");
        }
        List<String> fautes = new ArrayList<>();
        for (PaquetDAdditions paquet : paquets) {
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
                                    + " %d-%d %% qu'ADD-CE1-2 fixe",
                            paquet.id(), forme.libelle, proportion, compte.get(forme), total,
                            forme.bandeBasse, forme.bandeHaute));
                }
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("repartition par forme (ADD-CE1-2) : " + fautes);
        }
    }

    @Test
    void chaquePaquetDAdditionsDeCe1ServiTientLesHuitBandes() {
        verifier(paquetsDAdditionsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Le recensement atteint bien le materiau reel. Sans cette assertion, les morsures ci-dessous
     * pourraient toutes passer sur une population que le recensement n'a jamais trouvee.
     */
    @Test
    void leRecensementTrouveLePaquetDAdditionsServi() {
        assertThat(paquetsDAdditionsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI))
                .extracting(PaquetDAdditions::id)
                .contains("ce1-additions-jusqu-a-100");
    }

    /**
     * Morsure 1, et c'est <b>la</b> morsure : le tirage que nadia-prof a mesure et refuse. Les
     * proportions sont les siennes, section 1, pas des valeurs de mon invention - operande nul
     * 1,34 %, somme <= 20 3,78 %, double 0,82 %, complement 9,27 %, rond 16,60 %, deux-plus-un
     * 23,05 %, sans retenue 25,52 %, avec retenue 19,63 % - portees sur 10 000 items pour que les
     * arrondis ne decident rien. Trois bandes doivent rougir, et exactement trois.
     */
    @Test
    void leTirageGeneratifInchangeAEchelle100EstRefuseSurSesTroisBandes() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.OPERANDE_NUL, 134);
        ajouter(couples, Forme.SOMME_JUSQUA_20, 378);
        ajouter(couples, Forme.DOUBLE, 82);
        ajouter(couples, Forme.COMPLEMENT_A_LA_DIZAINE, 927);
        ajouter(couples, Forme.AJOUT_D_UN_NOMBRE_ROND, 1660);
        ajouter(couples, Forme.DEUX_CHIFFRES_PLUS_UN_CHIFFRE, 2305);
        ajouter(couples, Forme.SANS_RETENUE, 2552);
        ajouter(couples, Forme.AVEC_RETENUE, 1962);
        List<PaquetDAdditions> tirage = List.of(new PaquetDAdditions("tirage-generatif", couples));

        assertThatThrownBy(() -> verifier(tirage))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("somme <= 20")
                .hasMessageContaining("double")
                .hasMessageContaining("complement a la dizaine")
                .hasMessageNotContaining("operande nul")
                .hasMessageNotContaining("avec retenue");
    }

    /**
     * Morsure 2 : le plafond d'ADD-CE1-1. Ramener le zero dans le tirage pour retrouver la bande du
     * CP est la reponse la moins chere au constat de theo-archi, et c'est celle que nadia-prof
     * interdit explicitement (section 3, <i>"le plafond de 5 % est le plancher du CP retourne"</i>).
     */
    @Test
    void unPaquetQuiRameneLOperandeNulALaBandeDuCpEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.OPERANDE_NUL, 1290);
        ajouter(couples, Forme.SOMME_JUSQUA_20, 1500);
        ajouter(couples, Forme.DOUBLE, 1000);
        ajouter(couples, Forme.COMPLEMENT_A_LA_DIZAINE, 1500);
        ajouter(couples, Forme.AJOUT_D_UN_NOMBRE_ROND, 1500);
        ajouter(couples, Forme.DEUX_CHIFFRES_PLUS_UN_CHIFFRE, 1710);
        ajouter(couples, Forme.SANS_RETENUE, 1000);
        ajouter(couples, Forme.AVEC_RETENUE, 500);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDAdditions("zero-de-cp", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("operande nul")
                .hasMessageContaining("0-5 %");
    }

    /**
     * Morsure 3 : le glissement que la section 2 de l'arbitrage nomme - un paquet qui declare
     * {@code MAT-CE1-03} et sert {@code MAT-CE1-04}, c'est-a-dire l'addition avec retenue. Le
     * plafond de la huitieme bande est ce qui l'attrape, et c'est le seul endroit ou il l'est.
     */
    @Test
    void unPaquetQuiBasculeSurLAdditionAvecRetenueEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.SOMME_JUSQUA_20, 1000);
        ajouter(couples, Forme.DOUBLE, 500);
        ajouter(couples, Forme.COMPLEMENT_A_LA_DIZAINE, 1000);
        ajouter(couples, Forme.AJOUT_D_UN_NOMBRE_ROND, 1000);
        ajouter(couples, Forme.DEUX_CHIFFRES_PLUS_UN_CHIFFRE, 1500);
        ajouter(couples, Forme.SANS_RETENUE, 1000);
        ajouter(couples, Forme.AVEC_RETENUE, 4000);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDAdditions("mat-ce1-04-deguise", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("avec retenue")
                .hasMessageContaining("10-20 %");
    }

    /**
     * Morsure 4 : le paquet de CP reetiquete CE1, que le plafond de la deuxieme bande refuse.
     * nadia-prof borne cette ligne des deux cotes et dit pourquoi : {@code MAT-CP-03} va
     * explicitement jusqu'a 20, donc au-dela de 20 % le paquet redevient un paquet de CP.
     */
    @Test
    void unPaquetDeCpReetiqueteCe1EstRefuse() {
        List<int[]> couples = new ArrayList<>();
        ajouter(couples, Forme.SOMME_JUSQUA_20, 5000);
        ajouter(couples, Forme.DOUBLE, 1000);
        ajouter(couples, Forme.COMPLEMENT_A_LA_DIZAINE, 1000);
        ajouter(couples, Forme.AJOUT_D_UN_NOMBRE_ROND, 1000);
        ajouter(couples, Forme.DEUX_CHIFFRES_PLUS_UN_CHIFFRE, 1000);
        ajouter(couples, Forme.SANS_RETENUE, 1000);

        assertThatThrownBy(() -> verifier(List.of(new PaquetDAdditions("cp-reetiquete", couples))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("somme <= 20")
                .hasMessageContaining("avec retenue");
    }

    /** Morsure 5 : une population vide ne rend pas ce garde-fou vert (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>. Elle n'est pas une redite des
     * precedentes : celles-la fabriquent des couples en memoire, celle-ci traverse la lecture disque,
     * le recensement et l'analyse de l'enonce. Le jeu fautif porte deux paquets de CE1 - l'un hors
     * bandes, l'autre conforme - pour prouver que la regle <b>discrimine</b>, et non qu'elle refuse
     * tout paquet qu'on lui donne.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif() {
        List<PaquetDAdditions> fautifs = paquetsDAdditionsDuNiveau(
                CONTRE_EXEMPLE.resolve("catalogue.yaml"), CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux paquets")
                .extracting(PaquetDAdditions::id)
                .containsExactly("paquet-conforme-ce1", "paquet-fautif-ce1");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-fautif-ce1")
                .hasMessageNotContaining("paquet-conforme-ce1");
    }

    /**
     * Le perimetre derive, prouve dans les deux sens : un paquet de CE1 dont les items ne sont pas
     * des additions sort de la population, et le recensement ne le reduit pas silencieusement a ses
     * additions.
     */
    @Test
    void unPaquetDeCe1QuiNEstPasUnPaquetDAdditionsSortDuPerimetre() {
        assertThat(paquetsDAdditionsDuNiveau(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu")))
                .extracting(PaquetDAdditions::id)
                .doesNotContain("paquet-soustractions-ce1");
    }

    /**
     * La partition est exhaustive et ordonnee : tout couple du domaine recoit une case et une seule.
     * Verifie par balayage exhaustif des 5 151 couples de l'echelle 100, pas sur un echantillon -
     * une partition dont un cas tomberait dans le vide rendrait tous les comptes faux en silence.
     */
    @Test
    void laPartitionOrdonneeCouvreToutCoupleDeLEchelle100() {
        int couverts = 0;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; a + b <= 100; b++) {
                assertThat(formeDe(a, b)).as("%d + %d", a, b).isNotNull();
                couverts++;
            }
        }
        assertThat(couverts).isEqualTo(5151);
        assertThat(formeDe(25, 25)).as("l'ordre decide : un double avant une somme ronde")
                .isEqualTo(Forme.DOUBLE);
        assertThat(formeDe(0, 40)).as("l'ordre decide : l'operande nul avant le nombre rond")
                .isEqualTo(Forme.OPERANDE_NUL);
        assertThat(formeDe(11, 9)).as("l'ordre decide : la somme <= 20 avant le deux-plus-un")
                .isEqualTo(Forme.SOMME_JUSQUA_20);
    }

    /** Un couple de la forme demandee, repete autant de fois que la proportion l'exige. */
    private static void ajouter(List<int[]> couples, Forme forme, int combien) {
        int[] couple = switch (forme) {
            case OPERANDE_NUL -> new int[] {0, 45};
            case SOMME_JUSQUA_20 -> new int[] {8, 5};
            case DOUBLE -> new int[] {18, 18};
            case COMPLEMENT_A_LA_DIZAINE -> new int[] {34, 26};
            case AJOUT_D_UN_NOMBRE_ROND -> new int[] {40, 25};
            case DEUX_CHIFFRES_PLUS_UN_CHIFFRE -> new int[] {34, 7};
            case SANS_RETENUE -> new int[] {34, 25};
            case AVEC_RETENUE -> new int[] {27, 15};
        };
        if (formeDe(couple[0], couple[1]) != forme) {
            throw new IllegalStateException("le couple temoin de " + forme + " n'est pas de sa forme");
        }
        for (int i = 0; i < combien; i++) {
            couples.add(couple);
        }
    }
}
