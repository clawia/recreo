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
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S49-FRA-GRAPHEMES ({@code docs/backlog/backlog.md}, sprint 49), le paquet
 * {@code ce1-graphemes-complexes}, ligne {@code FRA-CE1-01} - lire des mots comportant des
 * graphemes complexes et les associer au son qu'ils transcrivent. Arbitrage pedagogique prealable
 * de s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 3.1,
 * conditions {@code GRC-CE1-1} a {@code GRC-CE1-3} et sept conditions transverses
 * {@code TRV-CE1-1} a {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/rapports/avis-faisabilite-s49-quatre-stories.md}, section 6, six pieges de
 * fabrication repris ci-dessous sans changement).
 *
 * <p><b>Ce que ce test verifie, et ce qu'il laisse a nadia-prof</b> (avis, tableau de la section
 * 6) : il recalcule la famille (a) depuis la graphie cible declaree et verifie qu'elle n'existe
 * que sur les trois familles nasales ; il ne verifie pas qu'une seule proposition porte reellement
 * le son, ni la prononciation, ni la terminaison verbale {@code -ient} (piege 3) - ce sont des
 * proprietes phonetiques qu'aucun automate de ce depot ne lit, et l'avis les route explicitement a
 * {@code nadia-prof}.
 *
 * <p><b>Chaque item declare sa derivation</b> a cote de l'enonce, dans un commentaire YAML de la
 * forme {@code # GRC-CE1: cible=x;fam=y,z}, relu par ce test plutot que recopie du YAML.
 *
 * <p><b>Piege 1 (avis section 6), evite par construction.</b> {@link #LEXIQUE_TRV_CE1_4} et
 * {@link #LEXIQUE_GRC_CE1_2} sont verifies par un motif {@code \bmot\b} compile avec
 * {@link Pattern#UNICODE_CHARACTER_CLASS}, et les deux lexiques sont ecrits accentues
 * ({@code préfixe}, {@code tranquille}) - le defaut que {@code PaquetCe1ProblemesEtapesTest}
 * documente a s48 ne se reproduit pas ici.
 *
 * <p><b>Piege 2, l'indice compare par mot entier, jamais par sous-chaine.</b>
 * {@link #indiceNeRevelePasLaBonneReponse} compare des mots extraits de l'indice a la bonne
 * reponse, et non des sous-chaines : une bonne reponse {@code "main"} ne ferait pas rougir un
 * indice qui ecrit {@code "demain"}.
 *
 * <p><b>Piege 3, la terminaison verbale {@code -ient}.</b> Aucune assertion ne la couvre : le
 * lexique d'exclusion de {@code GRC-CE1-2} est un perimetre enumere assume ({@code ville},
 * {@code mille}, {@code village}, {@code tranquille}), et {@code nadia-prof} garde la
 * verification de la forme verbale.
 *
 * <p><b>Piege 4, la famille (a) reservee aux cibles nasales.</b>
 * {@link #grc3LaFamilleAEstReserveeAuxCiblesNasalesEtContientLaGraphieCibleSuivieDUneVoyelle}
 * verifie les deux proprietes ensemble : aucun item non nasal ne declare la famille (a), et tout
 * distracteur de famille (a) contient la graphie cible (son coeur sans le {@code n} final) suivi
 * d'au moins un {@code n} puis d'une voyelle - la forme exacte de {@code chienne} aussi bien que
 * de {@code pleine}.
 *
 * <p><b>Piege 5, les guillemets et le point d'interrogation.</b> Verifie ailleurs
 * ({@code LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest}, {@code AucunSigneDoubleNEst
 * DetachableDeSonMotTest}) : ce paquet y entre a zero occurrence detachable, sans dette a
 * declarer.
 *
 * <p><b>Piege 6, le titre.</b> Verifie par {@code AucunTitreDeJeuNePorteSonNiveauTest} : "Le
 * meme son" ne porte pas "CE1" et {@code TexteAccentueExposeParLePortEntrantTest} le distingue de
 * "Le bon son" (CP).
 */
class PaquetCe1GraphemesComplexesTest {

    private static final String PAQUET = "ce1-graphemes-complexes";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1GraphemesComplexesTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1GraphemesComplexesTest";

    /** Une ligne "- enonce:" du fichier source, avec son marqueur GRC-CE1 en commentaire. */
    private static final Pattern LIGNE_ITEM =
            Pattern.compile("- enonce: \"([^\"]*)\"\\s*#\\s*GRC-CE1:\\s*cible=([a-zéè/]+);"
                    + "fam=([a-c]),([a-c])\\s*$");

    /**
     * GRC-CE1-1 : les six familles de la ligne, exactement les six cibles valides du marqueur. Les
     * deux familles doubles ("ain/ein", "eil/eille") sont declarees d'un seul tenant plutot que par
     * sous-variante : un item cible l'une ou l'autre graphie de la paire sans qu'il faille les
     * distinguer, puisque GRC-CE1-1 compte le dixieme sur la famille, jamais sur la sous-variante.
     */
    private static final Set<String> FAMILLES_VALIDES =
            Set.of("ain/ein", "oin", "ien", "eil/eille", "ill", "gn");

    /** GRC-CE1-1 : les graphemes deja servis au CP, jamais la cible d'un item de cette ligne. */
    private static final Set<String> GRAPHEMES_DU_CP = Set.of("an", "en", "on", "in", "ou", "oi", "ch");

    /** Les trois cibles nasales : seules familles ou GRC-CE1-3 autorise la famille (a). */
    private static final Set<String> CIBLES_NASALES = Set.of("ain/ein", "oin", "ien");

    /**
     * Le ou les coeurs de chaque cible nasale, sans leur "n" final, pour recalculer la famille (a).
     * "ain/ein" porte deux coeurs ("ai", "ei") : un distracteur de cette famille peut canceller la
     * nasale de l'une ou l'autre graphie, et GRC-CE1-3 ne distingue pas laquelle.
     */
    private static final Map<String, List<String>> COEURS_SANS_N = Map.of(
            "ain/ein", List.of("ai", "ei"), "oin", List.of("oi"), "ien", List.of("ie"));

    /** Les deux ou une graphie(s) que le mot de reference doit porter, par cible (TRV-CE1-3). */
    private static final Map<String, List<String>> GRAPHIES_PAR_CIBLE = Map.of(
            "ain/ein", List.of("ain", "ein"), "oin", List.of("oin"), "ien", List.of("ien"),
            "eil/eille", List.of("eil", "eille"), "ill", List.of("ill"), "gn", List.of("gn"));

    private static final double SEUIL_FAMILLE_MINIMUM = 0.10;

    private static final double SEUIL_FAMILLE_A_MINIMUM = 1.0 / 3;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    /**
     * TRV-CE1-4 : les mots de grammaire qu'un enfant de CE1 ne lit pas seul, exclus de l'enonce et
     * de l'indice. Ecrits accentues (piege 1 de l'avis).
     */
    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    /**
     * GRC-CE1-2 : les mots ou le grapheme cible n'a pas sa valeur ordinaire, jamais employes comme
     * bonne reponse. Ecrits accentues (piege 1 de l'avis).
     */
    private static final List<String> LEXIQUE_GRC_CE1_2 = List.of(
            "ville", "villes", "mille", "village", "villages", "tranquille", "tranquilles",
            "tranquillement");

    private static Pattern verificateurLexique(List<String> mots) {
        return Pattern.compile("\\b(" + String.join("|", mots) + ")\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
    }

    private static final Pattern VERIFICATEUR_TRV_CE1_4 = verificateurLexique(LEXIQUE_TRV_CE1_4);

    private static final Pattern VERIFICATEUR_GRC_CE1_2 = verificateurLexique(LEXIQUE_GRC_CE1_2);

    private static final Pattern MOT_ENTRE_GUILLEMETS =
            Pattern.compile("« (\\S+) »");

    private record Marqueur(String cible, String fam1, String fam2) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un (enonce -> marqueur) par ligne "- enonce:" du fichier source. */
    private static Map<String, Marqueur> marqueursParEnonce(Path fichier) {
        Map<String, Marqueur> marqueurs = new LinkedHashMap<>();
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher matcher = LIGNE_ITEM.matcher(ligne.strip());
            if (matcher.matches()) {
                marqueurs.put(matcher.group(1),
                        new Marqueur(matcher.group(2), matcher.group(3), matcher.group(4)));
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

    private record ItemAnalyse(Question item, Marqueur marqueur) {
    }

    /**
     * GRC-CE1-3 : la graphie cible contient-elle, suivie d'au moins un "n" puis d'une voyelle, le
     * coeur de la cible nasale ? Couvre a la fois {@code pleine} (ei + n + e) et {@code chienne}
     * (ie + nn + e, le "n" double de la forme feminine).
     */
    private static boolean contientLaGraphieCibleSuivieDUneVoyelle(String cible, String mot) {
        List<String> coeurs = COEURS_SANS_N.get(cible);
        assertThat(coeurs).as("'%s' : cible nasale inconnue (GRC-CE1-3)", cible).isNotNull();
        for (String coeur : coeurs) {
            Pattern motif = Pattern.compile(coeur + "n+[aeiouyàâäéèêëïîôöùûüÿ]",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
            if (motif.matcher(mot).find()) {
                return true;
            }
        }
        return false;
    }

    private static List<ItemAnalyse> itemsAnalyses(ContentSource source, Path fichierMarqueurs) {
        Map<String, Marqueur> marqueurs = marqueursParEnonce(fichierMarqueurs);
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            Marqueur marqueur = marqueurs.get(item.enonce());
            assertThat(marqueur).as("GRC-CE1-3 : aucun marqueur trouve pour '%s'", item.enonce())
                    .isNotNull();

            assertThat(item.distracteurs()).as("'%s' : deux distracteurs (TRV-CE1-2)",
                    item.enonce()).hasSize(2);

            List<String> fams = List.of(marqueur.fam1(), marqueur.fam2());
            for (int rang = 0; rang < fams.size(); rang++) {
                String fam = fams.get(rang);
                String distracteur = item.distracteurs().get(rang);
                if ("a".equals(fam)) {
                    assertThat(CIBLES_NASALES)
                            .as("'%s' : la famille (a) n'existe que sur une cible nasale"
                                    + " (GRC-CE1-3, piege 4), cible declaree '%s'", item.enonce(),
                                    marqueur.cible())
                            .contains(marqueur.cible());
                    assertThat(contientLaGraphieCibleSuivieDUneVoyelle(marqueur.cible(), distracteur))
                            .as("'%s' : le distracteur '%s' declare la famille (a) mais ne porte pas"
                                            + " la graphie '%s' suivie d'un n puis d'une voyelle"
                                            + " (GRC-CE1-3)", item.enonce(), distracteur,
                                    marqueur.cible())
                            .isTrue();
                }
            }

            analyses.add(new ItemAnalyse(item, marqueur));
        }
        return analyses;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET), FICHIER_CONTENU);
    }

    @Test
    void adr0081CePaquetDeclareFraCe101EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-01"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet(PAQUET)))
                .as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueItemDeclareUnMarqueurEtTroisPropositionsDistinctes() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        assertThat(analyses).isNotEmpty();
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.item();
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("'%s' : trois propositions distinctes (TRV-CE1-2)",
                    item.enonce()).hasSize(3);
        }
    }

    @Test
    void grc1ChaqueFamilleEstCibleDauMoinsUnDixiemeDesItemsEtAucuneNEstUnGraphemeDuCp() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        Map<String, Integer> comptes = new TreeMap<>();
        for (ItemAnalyse analyse : analyses) {
            String cible = analyse.marqueur().cible();
            assertThat(GRAPHEMES_DU_CP)
                    .as("'%s' : la cible '%s' est un grapheme deja servi au CP (GRC-CE1-1)",
                            analyse.item().enonce(), cible)
                    .doesNotContain(cible);
            assertThat(FAMILLES_VALIDES)
                    .as("'%s' : cible '%s' hors des six familles de la ligne (GRC-CE1-1)",
                            analyse.item().enonce(), cible)
                    .contains(cible);
            comptes.merge(cible, 1, Integer::sum);
        }
        assertThat(comptes.keySet())
                .as("GRC-CE1-1 : les six familles de la ligne doivent toutes etre servies")
                .containsExactlyInAnyOrder("ain/ein", "oin", "ien", "eil/eille", "ill", "gn");
        comptes.forEach((famille, compte) -> assertThat((double) compte / total)
                .as("GRC-CE1-1 : famille '%s', %d items sur %d", famille, compte, total)
                .isGreaterThanOrEqualTo(SEUIL_FAMILLE_MINIMUM));
    }

    @Test
    void grc2AucuneBonneReponseNAppartientAuLexiqueDExclusion() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_GRC_CE1_2.matcher(item.bonneReponse()).find())
                    .as("'%s' : la bonne reponse '%s' appartient au lexique d'exclusion de"
                            + " GRC-CE1-2 (le grapheme n'y a pas sa valeur ordinaire)",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    @Test
    void grc3LaFamilleAEstReserveeAuxCiblesNasalesEtContientLaGraphieCibleSuivieDUneVoyelle() {
        // La verification elle-meme est faite dans itemsAnalyses() : cet appel suffit a la mordre.
        assertThat(itemsAnalyses()).isNotEmpty();
    }

    @Test
    void grc3LaFamilleAEstPresenteSurAuMoinsUnTiersDesItems() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long avecFamilleA = analyses.stream()
                .filter(a -> "a".equals(a.marqueur().fam1()) || "a".equals(a.marqueur().fam2()))
                .count();
        assertThat((double) avecFamilleA / total)
                .as("GRC-CE1-3 : la famille (a) doit figurer sur au moins un tiers des items")
                .isGreaterThanOrEqualTo(SEUIL_FAMILLE_A_MINIMUM);
    }

    /** GRC-CE1-R1 : reserve levee le 2026-10-03 (fiche de conformite, second passage, section 4). */
    @Test
    void grcR1AucunDistracteurNeFigureSurPlusDeDeuxItemsDuPaquet() {
        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            for (String distracteur : item.distracteurs()) {
                comptes.merge(distracteur, 1, Integer::sum);
            }
        }
        comptes.forEach((distracteur, compte) -> assertThat(compte)
                .as("GRC-CE1-R1 : le distracteur '%s' figure sur %d items du paquet, au plus deux"
                        + " attendus", distracteur, compte)
                .isLessThanOrEqualTo(2));
    }

    @Test
    void trv2LaBonneReponseNestStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long strictementLaPlusLongue = 0;
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.item();
            List<Integer> longueurs = new ArrayList<>(
                    item.distracteurs().stream().map(String::length).toList());
            longueurs.add(item.bonneReponse().length());
            int maximum = longueurs.stream().mapToInt(Integer::intValue).max().orElseThrow();
            long auMaximum = longueurs.stream().filter(longueur -> longueur == maximum).count();
            if (item.bonneReponse().length() == maximum && auMaximum == 1) {
                strictementLaPlusLongue++;
            }
        }
        assertThat((double) strictementLaPlusLongue / total)
                .as("TRV-CE1-2 : la bonne reponse n'est strictement la plus longue (Q2 : longueur"
                        + " comptee en lettres) que sur la moitie des items au plus")
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void trv3UnSeulMotEntreGuillemetsPortantUneGraphieDeLaFamilleCible() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            Question item = analyse.item();
            Matcher matcher = MOT_ENTRE_GUILLEMETS.matcher(item.enonce());
            assertThat(matcher.find())
                    .as("'%s' : aucun mot de reference entre guillemets trouve (TRV-CE1-3)",
                            item.enonce())
                    .isTrue();
            String motDeReference = matcher.group(1);
            assertThat(matcher.find())
                    .as("'%s' : un seul mot entre guillemets attendu, un second a ete trouve"
                            + " (TRV-CE1-3)", item.enonce())
                    .isFalse();
            String cible = analyse.marqueur().cible();
            List<String> graphies = GRAPHIES_PAR_CIBLE.get(cible);
            assertThat(graphies).as("'%s' : cible '%s' inconnue (TRV-CE1-3)", item.enonce(), cible)
                    .isNotNull();
            String motEnMinuscules = motDeReference.toLowerCase(Locale.FRENCH);
            assertThat(graphies.stream().anyMatch(motEnMinuscules::contains))
                    .as("'%s' : le mot de reference '%s' ne porte aucune graphie de la cible '%s'"
                            + " (TRV-CE1-3)", item.enonce(), motDeReference, cible)
                    .isTrue();
        }
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceOuLIndice() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_TRV_CE1_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'enonce", item.enonce())
                    .isFalse();
            item.indice().ifPresent(indice -> assertThat(VERIFICATEUR_TRV_CE1_4.matcher(indice).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'indice", item.enonce())
                    .isFalse());
        }
    }

    @Test
    void trv5ChaqueIndiceEstPresentEtAuPlusDouzeMotsEtNeRevelePasLaBonneReponse() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            int mots = indice.strip().split("\\s+").length;
            assertThat(mots).as("'%s' : indice d'au plus douze mots (TRV-CE1-5)", indice)
                    .isLessThanOrEqualTo(12);
            indiceNeRevelePasLaBonneReponse(indice, item);
        }
        assertThat(indicesDistincts)
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    /** Piege 2 (avis section 6) : comparaison par mot entier, jamais par sous-chaine. */
    private static void indiceNeRevelePasLaBonneReponse(String indice, Question item) {
        Pattern motEntier = Pattern.compile("\\b" + Pattern.quote(item.bonneReponse()) + "\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
        assertThat(motEntier.matcher(indice).find())
                .as("'%s' : l'indice revele la bonne reponse '%s' (TRV-CE1-5)", indice,
                        item.bonneReponse())
                .isFalse();
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(proposition.strip().split("\\s+").length)
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .isLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou, traverse par la lecture disque, le
     * chargeur de contenu reel et le recensement des marqueurs. Son seul item declare la famille
     * (a) pour un distracteur ("chaton") qui ne porte pas la graphie cible "ain" suivie d'un n puis
     * d'une voyelle.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaFamilleADeclareeSansGraphieCible() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve(
                "contenu/ce1-graphemes-complexes-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-graphemes-complexes-fautif");

        assertThatThrownBy(() -> itemsAnalyses(source, fichierFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GRC-CE1-3")
                .hasMessageContaining("chaton");
    }
}
