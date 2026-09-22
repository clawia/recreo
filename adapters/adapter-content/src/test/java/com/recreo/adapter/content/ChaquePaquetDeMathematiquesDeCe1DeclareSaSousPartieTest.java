package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0097 : <b>la sous-partie qu'un paquet de mathematiques de CE1 sert est declaree dans son
 * descripteur, et cette declaration est allouee une seule fois, ici.</b> Un paquet du perimetre qui
 * n'en porte aucune, ou qui en porte une que ce tableau ne connait pas, est rouge.
 *
 * <p><b>Le defaut que cette regle existe pour empecher, et il n'est pas hypothetique.</b>
 * {@code MAT-CE1-03} se sert en quatre paquets - additions, soustractions, complements a la
 * dizaine, moities - et chacun porte sa propre table de repartition, donc son propre garde-fou
 * derive. Pour les additions, le perimetre se derive de la forme des items :
 * {@link ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest} reconnait {@code a + b = ?} par
 * un motif, et un paquet d'additions neuf entre dans sa population le jour ou il entre. <b>Pour les
 * moities, cette derivation n'existe pas</b> : l'enonce est du francais libre - <i>"Quelle est la
 * moitie de 58 ?"</i> - et un motif sur le mot <i>moitie</i> serait un perimetre derive <b>en
 * apparence seulement</b>. Un paquet neuf qui formulerait autrement - <i>"58, c'est le double de
 * quel nombre ?"</i> - sortirait de la population <b>en silence</b>, et son garde-fou passerait au
 * vert sans avoir rien lu. C'est la famille de defaut que ce depot sanctionne le plus souvent, et
 * elle serait ici de la main de l'architecte.
 *
 * <p><b>Ce que ce fichier interdit reellement.</b> Il ne verifie aucune table de repartition - il
 * rend les tables <b>derivables</b>. Le perimetre d'un garde-fou de sous-partie se lit desormais
 * dans {@link SousPartieDeMathematiquesCe1}, pas dans le francais d'un enonce, et le seul moyen
 * qu'un paquet de mathematiques CE1 echappe a ce tableau est un diff qui l'y ajoute - visible - au
 * lieu d'un silence. Sans cette clause, le geste fautif est gratuit et muet.
 *
 * <p><b>Le perimetre est derive, pas enumere</b> (ADR-0013). La population est l'ensemble des
 * paquets du repertoire de contenu dont le descripteur declare le niveau {@code CE1} et la matiere
 * <i>Mathematiques</i>. Aucun nom de paquet n'est ecrit ici. <b>Les deux cles sont deja gardees
 * ailleurs</b>, et c'est ce qui empeche d'en sortir par une faute de frappe : le niveau par
 * {@link ChaqueNiveauServiEstNommeDansLaSequenceTest} (ADR-0095), qui refuse tout niveau servi hors
 * sequence ; la matiere par {@code UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest} (ADR-0049),
 * qui refuse deux graphies d'une meme matiere. La comparaison de matiere est faite sur la forme
 * repliee - sans accent ni casse ni blanc de bord - pour la meme raison qu'ADR-0049 la replie : ce
 * n'est pas une normalisation du francais, c'est la seule facon de dire <i>"ces deux chaines nomment
 * la meme matiere"</i> sans tenir de table de correspondance.
 *
 * <p><b>Polarite</b> (ADR-0039) : ce tableau est du cote qui <b>exige une entree</b>, jamais du cote
 * qui dispense. Un paquet qu'il ne nomme pas est rouge, pas vert. C'est la meme forme que la
 * sequence d'ADR-0095, et l'inverse d'une liste de refus.
 *
 * <p><b>Ce que ce garde-fou ne dit pas.</b> Que la declaration soit <b>vraie</b> - qu'un paquet
 * declarant les moities serve reellement des moities, et non {@code MAT-CE1-04} sous un autre nom.
 * Aucun automate ne lit une declaration pour en juger la verite ; c'est le premier des six points
 * que {@code nadia-prof} garde seule (arbitrage du 2026-09-22, section 5). Ce fichier garde que la
 * declaration <b>existe et soit unique</b>, ce qui est la condition pour que sa table se verifie.
 *
 * <p><b>Ce qu'il ne dit pas non plus, et par refus explicite</b> : le <b>mecanisme</b> du paquet.
 * ADR-0097 decide le paquet statique pour les paquets de {@code MAT-CE1-03}, et ne l'encode pas -
 * encoder le moyen fermerait la reversibilite qu'ADR-0096 decision 3 a gardee ouverte, et
 * n'interdirait rien qu'une table ne verifie deja. Un mecanisme generatif qui tiendrait sa table et
 * declarerait sa sous-partie passerait ici sans qu'une ligne bouge.
 */
class ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest");

    private static final String NIVEAU_GARDE = "CE1";

    /** Forme repliee, celle que la comparaison manipule ; la graphie servie est accentuee. */
    private static final String MATIERE_GARDEE = "mathematiques";

    /**
     * Les sous-parties de mathematiques de CE1 allouees, chacune avec la competence <b>exacte</b>
     * que son descripteur declare. C'est le seul endroit du depot ou ce couple est ecrit : un
     * garde-fou de sous-partie derive sa population d'ici, jamais d'un motif sur un enonce.
     *
     * <p>Les quatre entrees sont celles de {@code MAT-CE1-03} (arbitrage de {@code nadia-prof} du
     * 2026-09-22, sections 0 a 3). La premiere est <b>servie</b> depuis s37 et sa chaine est copiee
     * du paquet reel, pas ecrite de memoire. Les trois autres sont allouees <b>avant</b> leur
     * paquet, et c'est le geste qui donne sa morsure a ce fichier : un paquet de moities ecrit avec
     * une autre formulation est rouge au lieu de sortir en silence du garde-fou qui l'attend.
     *
     * <p><b>Ce que ce tableau n'est pas</b> : une decision de contenu. La competence est une
     * etiquette de descripteur, jamais un texte montre a l'enfant, et sa formulation suit la
     * convention des competences de calcul mental deja servies. Une autre formulation reste
     * possible - elle se change ici et dans le paquet, <b>dans le meme diff</b>, ce qui est
     * exactement la visibilite recherchee.
     */
    enum SousPartieDeMathematiquesCe1 {
        ADDITIONS("Calcul mental - additions dans la limite de 100"),
        SOUSTRACTIONS("Calcul mental - soustractions dans la limite de 100"),
        COMPLEMENTS_A_LA_DIZAINE("Calcul mental - compléments à la dizaine"),
        MOITIES("Calcul mental - moitiés dans la limite de 100");

        private final String competence;

        SousPartieDeMathematiquesCe1(String competence) {
            this.competence = competence;
        }

        String competence() {
            return competence;
        }

        static List<String> competencesAllouees() {
            return Arrays.stream(values()).map(SousPartieDeMathematiquesCe1::competence).toList();
        }
    }

    /** Un paquet reduit a ce que cette regle juge : son identifiant et sa competence declaree. */
    record PaquetDeMathematiquesCe1(String id, String competence) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** ADR-0049 : sans accent, sans casse, sans blanc de bord ni blanc interne redondant. */
    static String replie(String valeur) {
        String decompose = Normalizer.normalize(valeur, Normalizer.Form.NFD);
        StringBuilder sans = new StringBuilder(decompose.length());
        boolean blancEnAttente = false;
        for (int position = 0; position < decompose.length(); position++) {
            char caractere = decompose.charAt(position);
            if (Character.getType(caractere) == Character.NON_SPACING_MARK) {
                continue;
            }
            if (Character.isWhitespace(caractere) || Character.isSpaceChar(caractere)) {
                blancEnAttente = sans.length() > 0;
                continue;
            }
            if (blancEnAttente) {
                sans.append(' ');
                blancEnAttente = false;
            }
            sans.append(caractere);
        }
        return sans.toString().toLowerCase(Locale.FRENCH);
    }

    /**
     * Le recensement : tout paquet du repertoire de contenu qui declare le niveau et la matiere
     * gardes. Le repertoire plutot que le catalogue, pour la raison qu'ADR-0049 a ecrite : un paquet
     * retire du catalogue (DEC-0009) reste la matiere de travail d'une story de retour, et un
     * recensement derive du catalogue ne le verrait plus.
     */
    static List<PaquetDeMathematiquesCe1> paquetsDeMathematiquesDeCe1(Path catalogue,
            Path repertoireDeContenu) {
        if (AucunTitreDeJeuNePorteSonNiveauTest.entreesServies(catalogue, repertoireDeContenu)
                .isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, le recensement de ce"
                    + " garde-fou n'a alors rien lu");
        }
        List<PaquetDeMathematiquesCe1> paquets = new ArrayList<>();
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

    private static void retenir(Path fichier, List<PaquetDeMathematiquesCe1> paquets) {
        if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                || !NIVEAU_GARDE.equals(String.valueOf(descripteur.get("niveau")).strip())
                || !MATIERE_GARDEE.equals(replie(String.valueOf(descripteur.get("matiere"))))) {
            return;
        }
        Object competence = descripteur.get("competence");
        paquets.add(new PaquetDeMathematiquesCe1(String.valueOf(corps.get("id")),
                competence == null ? null : String.valueOf(competence)));
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * produit. C'est ce qui permet de le mordre sur du materiau reel mute en memoire plutot que sur
     * une fixture ecrite de ma main.
     */
    static void verifier(List<PaquetDeMathematiquesCe1> paquets) {
        if (paquets.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun paquet de mathematiques de " + NIVEAU_GARDE
                    + " dans le contenu, cette regle ne porte alors sur rien - un garde-fou qui ne"
                    + " trouve plus sa population le dit au lieu de passer");
        }
        List<String> fautes = new ArrayList<>();
        for (PaquetDeMathematiquesCe1 paquet : paquets) {
            if (paquet.competence() == null || paquet.competence().isBlank()) {
                fautes.add(paquet.id() + " : ce paquet de mathematiques de CE1 ne declare aucune"
                        + " competence. Sa sous-partie de MAT-CE1-03 n'est alors nulle part, et le"
                        + " garde-fou de repartition qui l'attend ne le trouverait pas (ADR-0097)");
                continue;
            }
            if (!SousPartieDeMathematiquesCe1.competencesAllouees()
                    .contains(paquet.competence().strip())) {
                fautes.add(paquet.id() + " : la competence declaree \"" + paquet.competence()
                        + "\" n'est allouee dans aucune sous-partie de"
                        + " SousPartieDeMathematiquesCe1 " + SousPartieDeMathematiquesCe1
                        .competencesAllouees()
                        + ". Un paquet dont la sous-partie n'est pas declaree sort en silence du"
                        + " garde-fou de repartition qui la verifie (ADR-0097) : soit le paquet"
                        + " reprend la formulation allouee, soit la sous-partie est allouee dans le"
                        + " meme diff que le paquet");
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("sous-partie de mathematiques de CE1 (ADR-0097) : " + fautes);
        }
    }

    @Test
    void chaquePaquetDeMathematiquesDeCe1DeclareUneSousPartieAllouee() {
        verifier(paquetsDeMathematiquesDeCe1(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Le recensement atteint bien le materiau reel. Sans cette assertion, les morsures ci-dessous
     * pourraient toutes passer sur une population que le recensement n'a jamais trouvee - et le test
     * principal serait vert sur zero paquet.
     */
    @Test
    void leRecensementTrouveLePaquetDeMathematiquesDeCe1Servi() {
        assertThat(paquetsDeMathematiquesDeCe1(CATALOGUE_SERVI, CONTENU_SERVI))
                .extracting(PaquetDeMathematiquesCe1::id)
                .contains("ce1-additions-jusqu-a-100");
    }

    /**
     * Morsure 1, et c'est <b>la</b> morsure : le geste exact que la section 9 de l'avis de
     * faisabilite de s38 decrit. Un paquet de moities formule sa competence autrement que ce qui est
     * alloue. Sans cette clause, il serait vert ici et <b>invisible</b> pour le garde-fou de
     * repartition de MOI-CE1-2.
     */
    @Test
    void unPaquetDontLaSousPartieNEstPasAlloueeFaitEchouerLeGardeFou() {
        List<PaquetDeMathematiquesCe1> paquets =
                new ArrayList<>(paquetsDeMathematiquesDeCe1(CATALOGUE_SERVI, CONTENU_SERVI));
        paquets.add(new PaquetDeMathematiquesCe1("ce1-moities",
                "Calcul mental - moitiés de nombres jusqu'à 100"));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ce1-moities")
                .hasMessageContaining("n'est allouee dans aucune sous-partie");
    }

    /**
     * Morsure 2 : le paquet muet. C'est le mode de defaillance le plus discret - rien a lire, donc
     * rien a contredire - et c'est celui qui rendrait tous les garde-fous de sous-partie verts sur
     * une population vide.
     */
    @Test
    void unPaquetSansCompetenceDeclareeFaitEchouerLeGardeFou() {
        List<PaquetDeMathematiquesCe1> paquets =
                new ArrayList<>(paquetsDeMathematiquesDeCe1(CATALOGUE_SERVI, CONTENU_SERVI));
        paquets.add(new PaquetDeMathematiquesCe1("ce1-soustractions", null));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare aucune competence");
    }

    /**
     * Morsure 3 : la meme faute dans sa forme la plus discrete, un ecart d'accent sur une chaine par
     * ailleurs identique. La comparaison est volontairement <b>exacte</b> ici, la ou la matiere est
     * repliee : la competence sert de cle a un garde-fou derive, et deux cles qui different d'un
     * accent sont deux cles.
     */
    @Test
    void unEcartDAccentDansLaCompetenceDeclareeFaitEchouerLeGardeFou() {
        List<PaquetDeMathematiquesCe1> paquets =
                new ArrayList<>(paquetsDeMathematiquesDeCe1(CATALOGUE_SERVI, CONTENU_SERVI));
        paquets.add(new PaquetDeMathematiquesCe1("ce1-moities",
                "Calcul mental - moities dans la limite de 100"));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ce1-moities");
    }

    /** Morsure 4 : une population vide ne rend pas ce garde-fou vert (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>. Elle n'est pas une redite des
     * quatre precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse - lecture
     * disque, balayage du repertoire, lecture du descripteur. Le jeu fautif porte quatre paquets et
     * une seule faute : ce qu'il prouve est que la regle <b>discrimine</b>.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif() {
        List<PaquetDeMathematiquesCe1> fautifs = paquetsDeMathematiquesDeCe1(
                CONTRE_EXEMPLE.resolve("catalogue.yaml"), CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux paquets du"
                        + " perimetre, et seulement eux")
                .extracting(PaquetDeMathematiquesCe1::id)
                .containsExactly("paquet-conforme-ce1", "paquet-moities-ce1");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-moities-ce1")
                .hasMessageNotContaining("paquet-conforme-ce1");
    }

    /**
     * Le perimetre derive, prouve dans ses deux dimensions : le jeu fautif porte un paquet de
     * francais de CE1 et un paquet de mathematiques de CM1, tous deux sans competence allouee. Ni
     * l'un ni l'autre n'est juge ici - une regle de {@code MAT-CE1-03} qui rougirait sur un paquet
     * de francais serait une regle qui garde autre chose que ce qu'elle dit.
     */
    @Test
    void unPaquetHorsNiveauOuHorsMatiereSortDuPerimetre() {
        assertThat(paquetsDeMathematiquesDeCe1(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu")))
                .extracting(PaquetDeMathematiquesCe1::id)
                .doesNotContain("paquet-francais-ce1", "paquet-mathematiques-cm1");
    }

    /**
     * La matiere est repliee avant d'etre comparee, et ce n'est pas une precaution de principe : le
     * contenu servi porte {@code "Mathématiques"} accentue, les jeux fautifs du depot l'ecrivent
     * souvent sans accent, et une egalite stricte ferait sortir du perimetre - <b>en silence</b> - le
     * paquet qu'une faute de frappe accentue autrement. Ce que le repliage ne pretend pas est ecrit
     * dans ADR-0049 : {@code "Maths"} resterait une autre matiere.
     */
    @Test
    void leRepliageDeLaMatiereAbsorbeLAccentLaCasseEtLeBlancDeBord() {
        assertThat(replie("Mathématiques")).isEqualTo(MATIERE_GARDEE);
        assertThat(replie(" MATHEMATIQUES ")).isEqualTo(MATIERE_GARDEE);
        assertThat(replie("Maths")).isNotEqualTo(MATIERE_GARDEE);
    }

    /**
     * Les quatre sous-parties de {@code MAT-CE1-03} sont allouees, et leurs competences sont
     * distinctes. Deux entrees de meme competence rendraient la cle ambigue et deux garde-fous
     * derives se disputeraient le meme paquet.
     */
    @Test
    void lesSousPartiesAlloueesSontDistinctes() {
        assertThat(SousPartieDeMathematiquesCe1.competencesAllouees())
                .doesNotHaveDuplicates()
                .hasSize(SousPartieDeMathematiquesCe1.values().length);
    }
}
