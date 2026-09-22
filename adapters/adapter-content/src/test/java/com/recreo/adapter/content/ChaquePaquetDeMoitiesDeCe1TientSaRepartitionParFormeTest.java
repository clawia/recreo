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
 * MOI-CE1-1 et MOI-CE1-2, arbitrage de nadia-prof du 2026-09-22
 * ({@code squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md},
 * section 3) : <b>dans un paquet de moities de CE1, {@code n} est un entier pair entre 0 et 100, la
 * proportion de tirages a {@code n = 0} est au plus 5 % sans plancher, et la repartition des tirages
 * par forme (repertoire de base, nombre rond, deux chiffres general) tient les quatre bandes de la
 * section 3.3.</b>
 *
 * <p><b>Le perimetre est derive de la competence declaree, pas de la forme de l'enonce</b>
 * (ADR-0097, decision 2 - et c'est le cas que sa Javadoc cite nommement : <i>"Quelle est la moitie de
 * 58 ?"</i> ne se reconnait par aucun motif fiable). Ce garde-fou selectionne d'abord les paquets dont
 * le descripteur declare le niveau {@code CE1}, la matiere <i>Mathematiques</i> et la competence
 * {@link ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.SousPartieDeMathematiquesCe1#MOITIES},
 * puis lit la valeur de {@code n} <b>dans les items</b> du paquet ainsi selectionne.
 *
 * <p><b>Derivation totale</b> (ADR-0013, obligation 2) : un paquet qui declare la competence gardee
 * mais dont un item ne s'ecrit pas sous la forme retenue par {@code ce1-moities.yaml} est un
 * <b>echec</b> de ce garde-fou, jamais un paquet silencieusement retire de sa population.
 */
class ChaquePaquetDeMoitiesDeCe1TientSaRepartitionParFormeTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ChaquePaquetDeMoitiesDeCe1TientSaRepartitionParFormeTest");

    private static final String NIVEAU_GARDE = "CE1";

    private static final String MATIERE_GARDEE = "mathematiques";

    private static final String COMPETENCE_GARDEE = ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
            .SousPartieDeMathematiquesCe1.MOITIES.competence();

    /** Meme classe de blancs que les garde-fous voisins (ADR-0067, TYP-1). */
    private static final String BLANC = "[\\s\\u00A0\\u202F]";

    private static final Pattern MOITIE = Pattern.compile(
            "^Moitié" + BLANC + "+de" + BLANC + "+(\\d+)" + BLANC + "*=" + BLANC + "*\\?"
                    + BLANC + "*$");

    /**
     * Les quatre cases de MOI-CE1-2, dans l'ordre de priorite de la section 3.3 : un {@code n}
     * appartient a la premiere case dont il remplit la condition.
     */
    enum FormeMoitie {
        DEGENERE("degenere", 0, 5),
        REPERTOIRE_DE_BASE("repertoire de base", 30, 50),
        NOMBRE_ROND("nombre rond", 15, 30),
        DEUX_CHIFFRES_GENERAL("deux chiffres general", 15, 35);

        private final String libelle;
        private final int bandeBasse;
        private final int bandeHaute;

        FormeMoitie(String libelle, int bandeBasse, int bandeHaute) {
            this.libelle = libelle;
            this.bandeBasse = bandeBasse;
            this.bandeHaute = bandeHaute;
        }
    }

    /** Traduction litterale du tableau de la section 3.3. */
    static FormeMoitie formeDe(int n) {
        if (n == 0) {
            return FormeMoitie.DEGENERE;
        }
        if (n >= 2 && n <= 20) {
            return FormeMoitie.REPERTOIRE_DE_BASE;
        }
        if (n % 10 == 0) {
            return FormeMoitie.NOMBRE_ROND;
        }
        return FormeMoitie.DEUX_CHIFFRES_GENERAL;
    }

    /** Un paquet reduit a ce que cette regle juge : son identifiant et ses valeurs de n. */
    record PaquetDeMoities(String id, List<Integer> valeurs) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement : tout paquet servi dont le descripteur declare le niveau, la matiere et la
     * competence gardes. Un item qui ne s'ecrit pas sous la forme retenue entre avec la valeur
     * {@code null} plutot que d'en sortir en silence : c'est {@link #verifier} qui le refuse.
     */
    static List<PaquetDeMoities> paquetsDeMoitiesDuNiveau(Path catalogue, Path repertoireDeContenu) {
        if (AucunTitreDeJeuNePorteSonNiveauTest.entreesServies(catalogue, repertoireDeContenu)
                .isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, le recensement de ce"
                    + " garde-fou n'a alors rien lu");
        }
        List<PaquetDeMoities> paquets = new ArrayList<>();
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

    private static void retenir(Path fichier, List<PaquetDeMoities> paquets) {
        if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                || !NIVEAU_GARDE.equals(String.valueOf(descripteur.get("niveau")).strip())
                || !MATIERE_GARDEE.equals(ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
                        .replie(String.valueOf(descripteur.get("matiere"))))
                || !COMPETENCE_GARDEE.equals(String.valueOf(descripteur.get("competence")).strip())
                || !(corps.get("items") instanceof List<?> items)) {
            return;
        }
        List<Integer> valeurs = new ArrayList<>();
        for (Object item : items) {
            if (!(item instanceof Map<?, ?> champs)) {
                valeurs.add(null);
                continue;
            }
            Matcher moitie = MOITIE.matcher(String.valueOf(champs.get("enonce")));
            valeurs.add(moitie.matches() ? Integer.valueOf(moitie.group(1)) : null);
        }
        paquets.add(new PaquetDeMoities(String.valueOf(corps.get("id")), valeurs));
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * produit.
     */
    static void verifier(List<PaquetDeMoities> paquets) {
        if (paquets.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun paquet de moities de " + NIVEAU_GARDE
                    + " dans le contenu servi. MOI-CE1-1 et MOI-CE1-2 ne portent alors sur rien, et"
                    + " un garde-fou qui ne trouve plus sa population le dit au lieu de passer");
        }
        List<String> fautes = new ArrayList<>();
        for (PaquetDeMoities paquet : paquets) {
            Map<FormeMoitie, Integer> compte = new EnumMap<>(FormeMoitie.class);
            for (FormeMoitie forme : FormeMoitie.values()) {
                compte.put(forme, 0);
            }
            int total = paquet.valeurs().size();
            for (int rang = 0; rang < total; rang++) {
                Integer n = paquet.valeurs().get(rang);
                if (n == null) {
                    fautes.add(paquet.id() + " (item " + rang + ") : cet item ne s'ecrit pas sous"
                            + " la forme retenue pour une moitie, et il appartient a un paquet qui"
                            + " declare la competence \"" + COMPETENCE_GARDEE + "\" (ADR-0097) - un"
                            + " membre de la population qui ne porte pas ce dont la derivation a"
                            + " besoin est un echec, jamais un item ignore (ADR-0013)");
                    continue;
                }
                if (n < 0 || n > 100 || n % 2 != 0) {
                    fautes.add(paquet.id() + " (item " + rang + ") : n = " + n + " hors des bornes"
                            + " de MOI-CE1-1 (entier pair, 0 a 100)");
                    continue;
                }
                compte.put(formeDe(n), compte.get(formeDe(n)) + 1);
            }
            for (FormeMoitie forme : FormeMoitie.values()) {
                double proportion = 100.0 * compte.get(forme) / total;
                if (proportion < forme.bandeBasse || proportion > forme.bandeHaute) {
                    fautes.add(String.format(java.util.Locale.ROOT,
                            "%s : forme \"%s\" a %.2f %% (%d items sur %d), hors de la bande %d-%d"
                                    + " %% que MOI-CE1-2 fixe",
                            paquet.id(), forme.libelle, proportion, compte.get(forme), total,
                            forme.bandeBasse, forme.bandeHaute));
                }
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("repartition des moities (MOI-CE1-1, MOI-CE1-2) : " + fautes);
        }
    }

    @Test
    void chaquePaquetDeMoitiesDeCe1ServiTientSaRepartition() {
        verifier(paquetsDeMoitiesDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Le recensement atteint bien le materiau reel. Sans cette assertion, les morsures ci-dessous
     * pourraient toutes passer sur une population que le recensement n'a jamais trouvee.
     */
    @Test
    void leRecensementTrouveLePaquetDeMoitiesServi() {
        assertThat(paquetsDeMoitiesDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI))
                .extracting(PaquetDeMoities::id)
                .contains("ce1-moities");
    }

    /** Morsure 1 : un n impair, hors des bornes de MOI-CE1-1. */
    @Test
    void unNImpairEstRefuse() {
        List<Integer> valeurs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            valeurs.add(2 * (i + 1));
        }
        valeurs.set(0, 7);
        List<PaquetDeMoities> paquets = List.of(new PaquetDeMoities("n-impair", valeurs));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n = 7")
                .hasMessageContaining("MOI-CE1-1");
    }

    /** Morsure 2 : un paquet ou le repertoire de base est sous son plancher (MOI-CE1-2). */
    @Test
    void unPaquetSousLePlancherDuRepertoireDeBaseEstRefuse() {
        List<Integer> valeurs = new ArrayList<>();
        valeurs.add(6);
        for (int i = 0; i < 9; i++) {
            valeurs.add(30 + 10 * (i % 8));
        }
        List<PaquetDeMoities> paquets = List.of(new PaquetDeMoities("sans-repertoire", valeurs));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("repertoire de base")
                .hasMessageContaining("30-50 %");
    }

    /** Morsure 3 : le membre de la population qui ne porte pas ce dont la derivation a besoin. */
    @Test
    void unItemQuiNeSEcritPasCommeUneMoitieFaitEchouerLeGardeFou() {
        List<Integer> valeurs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            valeurs.add(2 * (i + 1));
        }
        valeurs.set(0, null);
        List<PaquetDeMoities> paquets = List.of(new PaquetDeMoities("item-non-conforme", valeurs));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne s'ecrit pas sous la forme retenue");
    }

    /** Morsure 4 : une population vide ne rend pas ce garde-fou vert (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b> : un objet absent de la
     * production, distinct des mutations en memoire ci-dessus.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif() {
        List<PaquetDeMoities> fautifs = paquetsDeMoitiesDuNiveau(
                CONTRE_EXEMPLE.resolve("catalogue.yaml"), CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux paquets du"
                        + " perimetre, et seulement eux")
                .extracting(PaquetDeMoities::id)
                .containsExactly("paquet-moities-conforme", "paquet-moities-fautif");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-moities-fautif")
                .hasMessageNotContaining("paquet-moities-conforme");
    }

    /**
     * Le perimetre derive de la competence, prouve dans les deux sens : un paquet de CE1 qui declare
     * une autre competence de mathematiques (ici les additions) sort de la population.
     */
    @Test
    void unPaquetDeCe1QuiDeclareUneAutreCompetenceSortDuPerimetre() {
        assertThat(paquetsDeMoitiesDuNiveau(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu")))
                .extracting(PaquetDeMoities::id)
                .doesNotContain("paquet-autre-competence");
    }
}
