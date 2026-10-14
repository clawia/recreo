package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-15 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-accord-groupe-nominal}, ligne {@code FRA-CE1-15} - accorder en nombre et en
 * genre dans le groupe nominal. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.6, conditions
 * {@code GNA-CE1-1} a {@code GNA-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}) et arbitrage du palier 1 de s57 (point d'audit 6.2).
 *
 * <p>Les proportions de {@code GNA-CE1-1} sont recomptees ici sur le champ {@code bonneReponse}
 * lu par le chargeur, jamais sur les commentaires {@code # GNA-CE1-1} du fichier. Les commandes
 * de comptage de la story ({@code awk} sur le fichier) sont celles de {@code camille-po} ; ce
 * test se lit contre {@code GNA-CE1-1}, pas contre elles : un groupe a adjectif compte trois mots,
 * le pluriel en -x se lit a un mot en -eaux ou -eux, le feminin a un determinant feminin singulier.
 *
 * <p>Ce que ce test ne lit pas, et que {@code nadia-prof} garde seule : que chaque marque soit
 * exacte au sens de {@code GNA-CE1-2} pour un mot que les terminaisons ne decident pas, qu'un
 * adjectif feminin ne soit pas deja termine par -e au masculin (le test voit le -e retire, pas
 * l'adjectif), qu'un feminin soit sans changement autre que le -e, qu'il n'y ait qu'une reponse
 * juste par item, la lisibilite pour un enfant de CE1. {@code TRV-CE1-6} (equilibre des prenoms)
 * est sans objet : aucun groupe du paquet ne porte de prenom.
 */
class PaquetCe1AccordGroupeNominalTest {

    private static final String PAQUET = "ce1-accord-groupe-nominal";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1AccordGroupeNominalTest";

    /** La question est portee par chaque enonce : la consigne est celle que l'enfant lit deja. */
    private static final String CONSIGNE_ATTENDUE = "Lis bien, puis choisis la bonne réponse.";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final Set<String> DETERMINANTS = Set.of("le", "la", "les", "un", "une", "des");

    private static final Set<String> DETERMINANTS_PLURIELS = Set.of("les", "des");

    private static final Set<String> DETERMINANTS_FEMININS_SINGULIERS = Set.of("la", "une");

    /** GNA-CE1-2 : les mots en -eau, -au, -eu qui prennent un -s et non un -x. */
    private static final Set<String> EXCEPTIONS_EN_S = Set.of("bleu", "pneu", "landau");

    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static final Pattern VERIFICATEUR_TRV_CE1_4 =
            Pattern.compile("\\b(" + String.join("|", LEXIQUE_TRV_CE1_4) + ")\\b", FLAGS);

    /** TRV-CE1-5 : un indice ne renvoie ni a l'oreille ni a la lecture a voix haute. */
    private static final Pattern RENVOI_A_L_OREILLE = Pattern.compile(
            "oreille|entends|écoute|dans ta tête|à voix haute|prononce", FLAGS);

    /** Les trois marques que GNA-CE1-1 compte, chacune dans au moins un cinquieme des items. */
    private enum Marque {
        PLURIEL_EN_S("pluriel en -s"), PLURIEL_EN_X("pluriel en -x"), FEMININ_EN_E("féminin en -e");

        private final String libelle;

        Marque(String libelle) {
            this.libelle = libelle;
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1AccordGroupeNominalTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
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

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-accord-groupe-nominal-fautif"));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    /**
     * GNA-CE1-1 : la marque que porte la bonne reponse. Un determinant pluriel avec un mot en -x
     * est un pluriel en -x, avec un autre mot un pluriel en -s ; un determinant feminin singulier
     * est un feminin en -e. Tout autre groupe fait echouer le comptage en se nommant.
     */
    private static Marque marqueDe(String bonneReponse) {
        List<String> mots = mots(bonneReponse);
        String determinant = mots.get(0);
        if (DETERMINANTS_PLURIELS.contains(determinant)) {
            return mots.stream().anyMatch(mot -> mot.endsWith("x"))
                    ? Marque.PLURIEL_EN_X : Marque.PLURIEL_EN_S;
        }
        if (DETERMINANTS_FEMININS_SINGULIERS.contains(determinant)) {
            return Marque.FEMININ_EN_E;
        }
        throw new AssertionError("GNA-CE1-1 : '" + bonneReponse + "' n'est ni un pluriel en -s,"
                + " ni un pluriel en -x, ni un féminin en -e (déterminant '" + determinant + "')");
    }

    /**
     * GNA-CE1-1, point d'audit 6.2 : les proportions sont recomptees sur le champ
     * {@code bonneReponse}. Un adjectif (un groupe de trois mots) dans au moins la moitie des
     * items ; chaque marque dans au moins un cinquieme.
     */
    private static void verifierLesProportions(List<Question> items) {
        int total = items.size();
        long moitie = (long) Math.ceil(total / 2.0);
        long cinquieme = (long) Math.ceil(total / 5.0);
        long avecAdjectif = items.stream().filter(item -> mots(item.bonneReponse()).size() == 3).count();
        Map<Marque, Long> parMarque = new EnumMap<>(Marque.class);
        for (Question item : items) {
            parMarque.merge(marqueDe(item.bonneReponse()), 1L, Long::sum);
        }
        assertThat(avecAdjectif)
                .as("GNA-CE1-1 : %d groupes avec adjectif sur %d, au moins %d", avecAdjectif, total, moitie)
                .isGreaterThanOrEqualTo(moitie);
        for (Marque marque : Marque.values()) {
            long compte = parMarque.getOrDefault(marque, 0L);
            assertThat(compte)
                    .as("GNA-CE1-1 : %d items en %s sur %d, au moins %d", compte, marque.libelle,
                            total, cinquieme)
                    .isGreaterThanOrEqualTo(cinquieme);
        }
    }

    /**
     * GNA-CE1-2 : le -x apres -eau ou -eu seulement, et un mot en -eau, -au ou -eu qui prend un
     * -s est une faute hors des exceptions. Un -x apres -aux ou -oux n'est pas servi par ce paquet :
     * s'il le devient, ce test s'amende avec nadia-prof, pas en silence.
     */
    private static void verifierLeXApresEauOuEu(List<Question> items) {
        for (Question item : items) {
            for (String mot : mots(item.bonneReponse())) {
                if (mot.endsWith("x")) {
                    assertThat(mot.endsWith("eaux") || mot.endsWith("eux"))
                            .as("GNA-CE1-2 : '%s' - le mot '%s' porte un -x ailleurs qu'après -eau ou -eu",
                                    item.bonneReponse(), mot)
                            .isTrue();
                } else if (mot.endsWith("s")) {
                    String singulier = mot.substring(0, mot.length() - 1);
                    boolean prendUnX = (singulier.endsWith("eau") || singulier.endsWith("au")
                            || singulier.endsWith("eu")) && !EXCEPTIONS_EN_S.contains(singulier);
                    assertThat(prendUnX)
                            .as("GNA-CE1-2 : '%s' - le mot '%s' devrait prendre un -x et non un -s",
                                    item.bonneReponse(), mot)
                            .isFalse();
                }
            }
        }
    }

    /** GNA-CE1-2 : bleu, pneu et landau prennent un -s, jamais un -x. */
    private static void verifierLesExceptionsEnS(List<Question> items) {
        for (Question item : items) {
            for (String mot : mots(item.bonneReponse())) {
                assertThat(mot.endsWith("x") && EXCEPTIONS_EN_S.contains(mot.substring(0, mot.length() - 1)))
                        .as("GNA-CE1-2 : '%s' - le mot '%s' est une exception (bleu, pneu, landau) : -s, jamais -x",
                                item.bonneReponse(), mot)
                        .isFalse();
            }
        }
    }

    /**
     * GNA-CE1-2 : le masculin d'un feminin en -e est le feminin sans son -e final sur un mot qui
     * n'est pas le determinant (un/une change le determinant, pas l'adjectif).
     */
    private static boolean estLeMasculinDe(List<String> feminin, List<String> proposition) {
        if (feminin.size() != proposition.size()) {
            return false;
        }
        int ecarts = 0;
        for (int rang = 0; rang < feminin.size(); rang++) {
            String f = feminin.get(rang);
            String m = proposition.get(rang);
            if (!f.equals(m)) {
                ecarts++;
                if (rang == 0 || !(f.equals(m + "e") && !m.endsWith("e"))) {
                    return false;
                }
            }
        }
        return ecarts == 1;
    }

    /**
     * GNA-CE1-2 : sur un item au feminin, un distracteur est le meme groupe au masculin, le -e du
     * feminin simplement retire : le feminin se fait par ajout d'un -e sans autre changement.
     */
    private static void verifierLeFemininParAjoutDUnE(List<Question> items) {
        for (Question item : items) {
            if (marqueDe(item.bonneReponse()) != Marque.FEMININ_EN_E) {
                continue;
            }
            List<String> feminin = mots(item.bonneReponse());
            boolean masculinPropose = item.distracteurs().stream()
                    .anyMatch(distracteur -> estLeMasculinDe(feminin, mots(distracteur)));
            assertThat(masculinPropose)
                    .as("GNA-CE1-2 : '%s' - aucun distracteur n'est le groupe au masculin (le -e retiré)",
                            item.bonneReponse())
                    .isTrue();
        }
    }

    /**
     * GNA-CE1-3 : les familles d'un distracteur, lues en comparant au groupe juste. Le groupe
     * entier est propose et un seul mot differe. (a) marque oubliee : le -s, -x ou -e final
     * retire ; (b) marque sur le mauvais mot : un determinant d'un autre genre ou nombre, ou un
     * -s en trop sur un autre mot ; (c) -s a la place de -x.
     */
    private static Set<Character> famillesDe(List<String> juste, List<String> distracteur) {
        Set<Character> familles = new TreeSet<>();
        if (juste.size() != distracteur.size()) {
            return familles;
        }
        int rangDifferent = -1;
        for (int rang = 0; rang < juste.size(); rang++) {
            if (!juste.get(rang).equals(distracteur.get(rang))) {
                if (rangDifferent >= 0) {
                    return familles;
                }
                rangDifferent = rang;
            }
        }
        if (rangDifferent < 0) {
            return familles;
        }
        String j = juste.get(rangDifferent);
        String d = distracteur.get(rangDifferent);
        if (j.endsWith("x") && d.equals(j.substring(0, j.length() - 1) + "s")) {
            familles.add('c');
        }
        if ((j.endsWith("s") || j.endsWith("x") || j.endsWith("e"))
                && d.equals(j.substring(0, j.length() - 1))) {
            familles.add('a');
        }
        if (rangDifferent == 0 && DETERMINANTS.contains(j) && DETERMINANTS.contains(d)) {
            familles.add('b');
        }
        if (rangDifferent > 0 && d.equals(j + "s")) {
            familles.add('b');
        }
        return familles;
    }

    private static void verifierLesFamillesDesDistracteurs(List<Question> items) {
        for (Question item : items) {
            List<String> juste = mots(item.bonneReponse());
            assertThat(DETERMINANTS)
                    .as("GNA-CE1-3 : '%s' - la bonne réponse commence par un déterminant", item.bonneReponse())
                    .contains(juste.get(0));
            for (String distracteur : item.distracteurs()) {
                assertThat(famillesDe(juste, mots(distracteur)))
                        .as("GNA-CE1-3 : '%s' - le distracteur '%s' n'est ni une marque oubliée (a),"
                                + " ni une marque sur le mauvais mot (b), ni un -s à la place du -x (c)",
                                item.bonneReponse(), distracteur)
                        .isNotEmpty();
            }
        }
    }

    /** TRV-CE1-5 : aucun indice ne contient une proposition de son item, mot entier, casse ignoree. */
    private static void verifierQuAucunIndiceNeContientUneProposition(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            for (String proposition : propositions(item)) {
                Pattern mot = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(proposition)
                        + "(?![\\p{L}\\p{N}])", FLAGS);
                assertThat(mot.matcher(indice).find())
                        .as("TRV-CE1-5 : '%s' - l'indice '%s' contient une proposition de son item : '%s'",
                                item.enonce(), indice, proposition)
                        .isFalse();
            }
        }
    }

    /**
     * La consigne du jeu, lue au catalogue, est celle que l'enfant lit deja sur d'autres entrees :
     * la question est portee par chaque enonce.
     */
    private static void verifierLaConsigneDuJeu(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<String> consignes = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                consignes.add(String.valueOf(jeu.get("consigne")));
            }
        }
        assertThat(consignes).as("une seule entrée du catalogue sert le paquet %s", PAQUET)
                .hasSize(1);
        assertThat(consignes.get(0))
                .as("la consigne est celle des entrées existantes, mot pour mot")
                .isEqualTo(CONSIGNE_ATTENDUE);
    }

    @Test
    void adr0081CePaquetDeclareFraCe115EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-15"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1AucunItemNePorteDeTexteSupportDIllustrationNiDeVoixHaute() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, bonneReponse, distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    @Test
    void trv7LePaquetGardeSesVingtQuatreItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("G1 : 24 items (TRV-CE1-7 : vingt au moins)").hasSize(24);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void gna1LesProportionsSontRecomptees() {
        verifierLesProportions(items());
    }

    @Test
    void gna2LeXNeSertQuApresEauOuEuEtLesMotsEnEauAuEuPrennentUnXHorsExceptions() {
        verifierLeXApresEauOuEu(items());
    }

    @Test
    void gna2BleuPneuEtLandauNePrennentJamaisUnX() {
        verifierLesExceptionsEnS(items());
    }

    @Test
    void gna2UnFemininAUnDistracteurAuMasculinLeEEnMoins() {
        verifierLeFemininParAjoutDUnE(items());
    }

    @Test
    void gna3ChaqueDistracteurEstUneMarqueOublieeUneMarqueSurLeMauvaisMotOuUnSAlaPlaceDuX() {
        verifierLesFamillesDesDistracteurs(items());
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat(plusLongue)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(items.size() / 2);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceLesPropositionsOuLIndice() {
        for (Question item : items()) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            item.indice().ifPresent(textes::add);
            for (String texte : textes) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("'%s' : aucun mot du lexique exclu (TRV-CE1-4)", texte).isFalse();
            }
        }
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(12);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("'%s' : l'indice ne renvoie pas à l'oreille (TRV-CE1-5)", indice)
                    .isFalse();
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void lon5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    @Test
    void laConsigneDuJeuEstCelleDesEntreesExistantes() {
        verifierLaConsigneDuJeu(lire(FICHIER_CATALOGUE));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif des proportions. Il compte deux groupes a adjectif sur
     * cinq, alors que son en-tete en annonce davantage : le compte est celui des champs.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportionsRecomptees() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GNA-CE1-1")
                .hasMessageContaining("2 groupes avec adjectif sur 5");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de GNA-CE1-2, un -s sur un mot en -eau. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnSAlaPlaceDuX() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLeXApresEauOuEu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GNA-CE1-2")
                .hasMessageContaining("gâteaus");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de GNA-CE1-2, un -x sur une exception. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnXSurUneException() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesExceptionsEnS(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GNA-CE1-2")
                .hasMessageContaining("pneux");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de GNA-CE1-2, un feminin sans son masculin propose. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnFemininSansMasculinPropose() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLeFemininParAjoutDUnE(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GNA-CE1-2")
                .hasMessageContaining("une porte verte");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de GNA-CE1-3, un distracteur d'aucune famille. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurDAucuneFamille() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesFamillesDesDistracteurs(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GNA-CE1-3")
                .hasMessageContaining("les chtas noirs");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("des sacs");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif. Sa consigne n'est pas celle des entrees existantes. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierLaConsigneDuJeu(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("consigne");
    }
}
