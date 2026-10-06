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
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S51-FRA-ALPHABETIQUE ({@code docs/backlog/backlog.md}, sprint 51), le paquet
 * {@code ce1-ordre-alphabetique}, ligne {@code FRA-CE1-23} - ranger des mots dans l'ordre
 * alphabetique. Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 6.1, conditions
 * {@code ALP-CE1-1} a {@code ALP-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md}, V3).
 *
 * <p>La decision a la premiere ou a la deuxieme lettre, jamais a la troisieme, s'asserte en
 * comparant les deux premieres lettres en caracteres bruts : {@code ALP-CE1-2} exclut accent,
 * apostrophe, trait d'union et majuscule a ces deux positions. La famille de chaque distracteur de
 * liste est declaree dans un commentaire YAML au-dessus de l'item ({@code ALP-CE1-3}) et relue ici
 * contre la famille recalculee.
 */
class PaquetCe1AlphabetiqueTest {

    private static final String PAQUET = "ce1-ordre-alphabetique";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1AlphabetiqueTest";

    private static final String QUESTION_PREMIER = "Quel mot vient en premier dans l'ordre alphabétique";

    private static final String INVERSE = "inverse";
    private static final String DEUXIEME_LETTRE = "deuxieme-lettre";

    private static final double SEUIL_PREMIER_MINIMUM = 0.50;

    private static final double SEUIL_MEME_LETTRE_MINIMUM = 1.0 / 3;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final Pattern DEUX_LETTRES = Pattern.compile("[a-z]{2}.*");

    /** Un commentaire de famille, puis l'item qu'il declare (lu par sa bonne reponse). */
    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*ALP-CE1-3:\\s*([^\\r\\n]+)\\R\\s*- enonce: \"[^\"]*\"\\R\\s*bonneReponse: \"([^\"]*)\"");

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static final Pattern VERIFICATEUR_TRV_CE1_4 =
            Pattern.compile("\\b(" + String.join("|", LEXIQUE_TRV_CE1_4) + ")\\b", FLAGS);

    private static final Collator ORDRE = Collator.getInstance(Locale.FRENCH);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String token : texte.strip().split(ESPACES + "+")) {
            if (LETTRE_OU_CHIFFRE.matcher(token).find()) {
                mots.add(token);
            }
        }
        return mots;
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

    private static List<Question> items() {
        return tousLesItems(new YamlContentPackLoader().chargerPaquet(PAQUET));
    }

    private static boolean estUnItemPremier(Question item) {
        return item.enonce().startsWith(QUESTION_PREMIER);
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    /** Les mots d'un item : ses trois propositions, ou les trois mots de sa bonne liste. */
    private static List<String> motsDeLItem(Question item) {
        return estUnItemPremier(item) ? propositions(item) : liste(item.bonneReponse());
    }

    private static List<String> liste(String proposition) {
        return Arrays.stream(proposition.split(",")).map(String::strip).toList();
    }

    private static boolean estDansLOrdre(List<String> mots) {
        for (int i = 0; i + 1 < mots.size(); i++) {
            if (ORDRE.compare(mots.get(i), mots.get(i + 1)) >= 0) {
                return false;
            }
        }
        return true;
    }

    /** ALP-CE1-2 : deux mots d'un item different par leurs deux premieres lettres, brutes. */
    private static void verifierLaDecisionAvantLaTroisiemeLettre(List<Question> items) {
        for (Question item : items) {
            List<String> mots = motsDeLItem(item);
            assertThat(mots).as("'%s' : trois mots (ALP-CE1-1)", item.enonce()).hasSize(3);
            Set<String> debuts = new HashSet<>();
            for (String mot : mots) {
                assertThat(DEUX_LETTRES.matcher(mot).matches())
                        .as("'%s' : les deux premieres lettres de '%s' sont des minuscules sans"
                                + " accent (ALP-CE1-2)", item.enonce(), mot)
                        .isTrue();
                assertThat(mot).as("aucune apostrophe ni trait d'union (ALP-CE1-2)")
                        .doesNotContain("'", "-");
                assertThat(debuts.add(mot.substring(0, 2)))
                        .as("'%s' : deux mots partagent leurs deux premieres lettres, l'ordre se"
                                + " decide a la troisieme (ALP-CE1-2) : %s", item.enonce(), mots)
                        .isTrue();
            }
        }
    }

    /** ALP-CE1-3 : la famille d'un distracteur de liste, recalculee. */
    private static String famille(List<String> bonne, List<String> fausse) {
        List<String> inverse = new ArrayList<>(bonne);
        Collections.reverse(inverse);
        if (fausse.equals(inverse)) {
            return INVERSE;
        }
        boolean memePremiereLettre = true;
        for (int i = 0; i < bonne.size(); i++) {
            memePremiereLettre &= bonne.get(i).charAt(0) == fausse.get(i).charAt(0);
        }
        if (memePremiereLettre && !fausse.equals(bonne)) {
            return DEUXIEME_LETTRE;
        }
        return "autre";
    }

    @Test
    void adr0081CePaquetDeclareFraCe123EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-23"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(items()).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void alp1AuMoinsLaMoitieDesItemsDemandentLePremierMotEtLesAutresProposentTroisListes() {
        List<Question> items = items();
        long premier = items.stream().filter(PaquetCe1AlphabetiqueTest::estUnItemPremier).count();
        assertThat((double) premier / items.size())
                .as("ALP-CE1-1 : %d items 'en premier' sur %d", premier, items.size())
                .isGreaterThanOrEqualTo(SEUIL_PREMIER_MINIMUM);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs", item.enonce())
                    .hasSize(2);
            if (estUnItemPremier(item)) {
                List<String> mots = motsDeLItem(item);
                assertThat(new HashSet<>(mots)).as("'%s' : trois mots distincts", item.enonce())
                        .hasSize(3);
                List<String> tries = new ArrayList<>(mots);
                tries.sort(ORDRE);
                assertThat(item.bonneReponse())
                        .as("'%s' : le mot qui vient en premier (ALP-CE1-1)", item.enonce())
                        .isEqualTo(tries.get(0));
            } else {
                List<String> bonne = liste(item.bonneReponse());
                assertThat(estDansLOrdre(bonne))
                        .as("'%s' : la bonne liste '%s' est dans l'ordre alphabetique",
                                item.enonce(), item.bonneReponse())
                        .isTrue();
                for (String distracteur : item.distracteurs()) {
                    List<String> fausse = liste(distracteur);
                    assertThat(new HashSet<>(fausse))
                            .as("'%s' : '%s' porte les trois memes mots (ALP-CE1-1)",
                                    item.enonce(), distracteur)
                            .isEqualTo(new HashSet<>(bonne));
                    assertThat(estDansLOrdre(fausse)).isFalse();
                }
            }
        }
    }

    @Test
    void alp2AuMoinsUnTiersDesItemsPorteDesMotsDeMemePremiereLettre() {
        List<Question> items = items();
        long memeLettre = items.stream().filter(i -> {
            List<String> mots = motsDeLItem(i);
            return mots.stream().map(m -> m.charAt(0)).distinct().count() < mots.size();
        }).count();
        assertThat((double) memeLettre / items.size())
                .as("ALP-CE1-2 : %d items a premiere lettre commune sur %d", memeLettre,
                        items.size())
                .isGreaterThanOrEqualTo(SEUIL_MEME_LETTRE_MINIMUM);
    }

    @Test
    void alp2LaDecisionTombeALaPremiereOuALaDeuxiemeLettreJamaisALaTroisieme() {
        verifierLaDecisionAvantLaTroisiemeLettre(items());
    }

    @Test
    void alp3ChaqueDistracteurDeListeEstUneFamilleDeclareeACoteDeLItem() {
        String source = lire(FICHIER_CONTENU);
        Map<String, List<String>> declarees = new LinkedHashMap<>();
        Matcher declaration = DECLARATION.matcher(source);
        while (declaration.find()) {
            declarees.put(declaration.group(2),
                    Arrays.stream(declaration.group(1).split(",")).map(String::strip).toList());
        }
        List<Question> listes = items().stream().filter(i -> !estUnItemPremier(i)).toList();
        assertThat(declarees.keySet())
                .as("ALP-CE1-3 : chaque item de listes declare ses familles, et eux seuls")
                .containsExactlyInAnyOrderElementsOf(
                        listes.stream().map(Question::bonneReponse).toList());
        for (Question item : listes) {
            List<String> bonne = liste(item.bonneReponse());
            List<String> recalculees = item.distracteurs().stream()
                    .map(d -> famille(bonne, liste(d))).toList();
            assertThat(recalculees).as("'%s' : une liste inverse et une liste juste sur la premiere"
                    + " lettre seulement (ALP-CE1-3)", item.bonneReponse())
                    .containsExactlyInAnyOrder(INVERSE, DEUXIEME_LETTRE);
            assertThat(declarees.get(item.bonneReponse()))
                    .as("'%s' : familles declarees dans l'ordre des distracteurs (ALP-CE1-3)",
                            item.bonneReponse())
                    .isEqualTo(recalculees);
        }
    }

    /**
     * ALP-CE1-R1 : la decision se fait a la deuxieme lettre quand deux mots partagent la premiere
     * lettre qui decide : la plus petite premiere lettre pour un item "en premier", deux mots
     * voisins de la bonne liste pour un item de listes.
     */
    private static boolean decideALaDeuxiemeLettre(Question item) {
        if (estUnItemPremier(item)) {
            List<String> mots = motsDeLItem(item);
            char plusPetite = mots.stream().map(m -> m.charAt(0)).min(Character::compare).orElseThrow();
            return mots.stream().filter(m -> m.charAt(0) == plusPetite).count() > 1;
        }
        List<String> bonne = liste(item.bonneReponse());
        for (int i = 0; i + 1 < bonne.size(); i++) {
            if (bonne.get(i).charAt(0) == bonne.get(i + 1).charAt(0)) {
                return true;
            }
        }
        return false;
    }

    /** ALP-CE1-R1 : "la premiere lettre" au singulier, sans la deuxieme : l'indice s'y arrete. */
    private static final Pattern INDICE_QUI_S_ARRETE_A_LA_PREMIERE = Pattern.compile(
            "^(?!.*deuxième).*la première lettre", FLAGS);

    private static void verifierQueLIndiceMeneALaDeuxiemeLettre(List<Question> items) {
        for (Question item : items) {
            if (decideALaDeuxiemeLettre(item)) {
                String indice = item.indice().orElseThrow();
                assertThat(INDICE_QUI_S_ARRETE_A_LA_PREMIERE.matcher(indice).find())
                        .as("'%s' : la decision se fait a la deuxieme lettre, l'indice '%s' s'arrete"
                                + " a la premiere (ALP-CE1-R1)", item.bonneReponse(), indice)
                        .isFalse();
            }
        }
    }

    @Test
    void alpR1AucunItemDecidantALaDeuxiemeLettreNePorteUnIndiceArreteALaPremiere() {
        verifierQueLIndiceMeneALaDeuxiemeLettre(items());
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSonIndiceQuiArreteALaPremiereLettre() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-ordre-alphabetique-fautif"));

        assertThatThrownBy(() -> verifierQueLIndiceMeneALaDeuxiemeLettre(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ALP-CE1-R1");
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat((double) plusLongue / items.size())
                .as("TRV-CE1-2 : %d items ou la bonne reponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceOuLIndice() {
        for (Question item : items()) {
            assertThat(VERIFICATEUR_TRV_CE1_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu dans l'enonce", item.enonce()).isFalse();
            item.indice().ifPresent(indice -> assertThat(VERIFICATEUR_TRV_CE1_4.matcher(indice).find())
                    .as("'%s' : aucun mot du lexique exclu dans l'indice", item.enonce()).isFalse());
        }
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsEtNeDonnePasLaReponse() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(12);
            for (String mot : liste(item.bonneReponse())) {
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(mot) + "(?!\\p{L})", FLAGS)
                        .matcher(indice).find())
                        .as("'%s' : l'indice ne cite pas '%s' (TRV-CE1-5)", indice, mot).isFalse();
            }
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
                assertThat(proposition).as("une seule phrase").doesNotContain(".", "?", "!");
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. "chat" et "chien" partagent leurs deux
     * premieres lettres : la decision tomberait a la troisieme (ALP-CE1-2).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaDecisionALaTroisiemeLettre() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-ordre-alphabetique-fautif"));

        assertThatThrownBy(() -> verifierLaDecisionAvantLaTroisiemeLettre(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ALP-CE1-2");
    }
}
