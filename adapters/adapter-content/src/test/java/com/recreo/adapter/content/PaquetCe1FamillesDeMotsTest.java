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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-24 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-familles-de-mots}, ligne {@code FRA-CE1-24} - regrouper les mots d'une meme
 * famille et reperer un prefixe ou un suffixe frequent. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, conditions
 * {@code FAM-CE1-1} a {@code FAM-CE1-3} et {@code TRV-CE1-1} a {@code TRV-CE1-7}), arbitrage du
 * palier 2 de s57 (6.1 et 6.2, ligne {@code -24}) et critere {@code FM6} de la story.
 *
 * <p>Les proportions de {@code FAM-CE1-1} sont recomptees sur le champ {@code enonce} lu par le
 * chargeur, jamais sur les commentaires du fichier.
 *
 * <p>Ce que ce test ne lit pas, et que {@code nadia-prof} garde seule : {@code FAM-CE1-2}
 * (familles exactes et non savantes : un test voit un mot, pas son etymologie ; seule la reponse
 * {@code librairie}, refusee par {@code S57-FRA-FAMILLES-LIVRE}, est gardee) et l'ordre des deux
 * distracteurs (a) fausse famille, (b) meme theme ; qu'un indice departage apres chaque erreur
 * possible ; qu'un item n'ait qu'une reponse juste. {@code TRV-CE1-6} (equilibre des prenoms) est
 * sans objet : aucun item du paquet ne porte de prenom.
 */
class PaquetCe1FamillesDeMotsTest {

    private static final String PAQUET = "ce1-familles-de-mots";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1FamillesDeMotsTest";

    /** La question est portee par chaque enonce : la consigne est celle que l'enfant lit deja. */
    private static final String CONSIGNE_ATTENDUE = "Lis bien, puis choisis la bonne réponse.";

    /** FM6 : l'indice des items de famille, texte de nadia-prof (3.6), copie sans reformulation. */
    private static final String INDICE_DES_ITEMS_DE_FAMILLE =
            "Le bon mot garde un morceau et parle de la même chose.";

    private static final String DEBUT_ENONCE_DE_FAMILLE = "Quel mot est de la même famille";

    private static final String DEBUT_ENONCE_D_AFFIXE = "Que veut dire";

    /** S57-FRA-FAMILLES-LIVRE : famille savante de {@code livre}, refusee comme bonne reponse. */
    private static final Set<String> REPONSES_SAVANTES_REFUSEES = Set.of("librairie");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static final Pattern VERIFICATEUR_TRV_CE1_4 =
            Pattern.compile("\\b(" + String.join("|", LEXIQUE_TRV_CE1_4) + ")\\b", FLAGS);

    /** FM3 : ces mots, en sous-chaine (comme le grep de la story), sur les quatre champs lus. */
    private static final Pattern NOM_DE_L_AFFIXE =
            Pattern.compile("préfixe|prefixe|suffixe|radical|affixe", FLAGS);

    /** TRV-CE1-5 : un indice ne renvoie ni a l'oreille ni a la lecture a voix haute. */
    private static final Pattern RENVOI_A_L_OREILLE = Pattern.compile(
            "oreille|entends|écoute|dans ta tête|à voix haute|prononce", FLAGS);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1FamillesDeMotsTest.class.getResourceAsStream(chemin)) {
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

    private static List<Question> itemsFautifs(String paquet) {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH).chargerPaquet(paquet));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static boolean estUnItemDeFamille(Question item) {
        return item.enonce().startsWith(DEBUT_ENONCE_DE_FAMILLE);
    }

    private static boolean estUnItemDAffixe(Question item) {
        return item.enonce().startsWith(DEBUT_ENONCE_D_AFFIXE);
    }

    /**
     * FAM-CE1-1 : des items de famille dans au moins la moitie des items, des items d'affixe dans
     * au moins le quart. Chaque item est de l'une des deux formes, sinon il n'est compte nulle part.
     */
    private static void verifierLesProportions(List<Question> items) {
        int total = items.size();
        long moitie = (long) Math.ceil(total / 2.0);
        long quart = (long) Math.ceil(total / 4.0);
        long familles = items.stream().filter(PaquetCe1FamillesDeMotsTest::estUnItemDeFamille).count();
        long affixes = items.stream().filter(PaquetCe1FamillesDeMotsTest::estUnItemDAffixe).count();
        assertThat(familles + affixes)
                .as("FAM-CE1-1 : %d items de famille et %d d'affixe sur %d, chaque item est de l'un des deux",
                        familles, affixes, total)
                .isEqualTo(total);
        assertThat(familles)
                .as("FAM-CE1-1 : %d items de famille sur %d, au moins %d", familles, total, moitie)
                .isGreaterThanOrEqualTo(moitie);
        assertThat(affixes)
                .as("FAM-CE1-1 : %d items d'affixe sur %d, au moins %d", affixes, total, quart)
                .isGreaterThanOrEqualTo(quart);
    }

    /** S57-FRA-FAMILLES-LIVRE : aucune bonne reponse n'est une famille savante refusee. */
    private static void verifierQuAucuneReponseSavanteNEstAttendue(List<Question> items) {
        for (Question item : items) {
            assertThat(REPONSES_SAVANTES_REFUSEES)
                    .as("FAM-CE1-2 : '%s' attend '%s', famille savante refusée (S57-FRA-FAMILLES-LIVRE)",
                            item.enonce(), item.bonneReponse())
                    .doesNotContain(item.bonneReponse().toLowerCase());
        }
    }

    /** FM3 : le nom de l'affixe n'est lu par l'enfant ni dans l'enonce, ni dans une proposition, ni dans l'indice. */
    private static void verifierQueLAffixeNEstJamaisNomme(List<Question> items) {
        for (Question item : items) {
            List<String> champs = new ArrayList<>(propositions(item));
            champs.add(item.enonce());
            item.indice().ifPresent(champs::add);
            for (String champ : champs) {
                assertThat(NOM_DE_L_AFFIXE.matcher(champ).find())
                        .as("FAM-CE1-3 : '%s' - le champ '%s' nomme l'affixe (préfixe, suffixe, radical ou affixe)",
                                item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /** FM6 : chaque item de famille porte l'indice de nadia-prof (3.6), mot pour mot. */
    private static void verifierLIndiceDesItemsDeFamille(List<Question> items) {
        for (Question item : items) {
            if (estUnItemDeFamille(item)) {
                assertThat(item.indice().orElse(""))
                        .as("FM6 : '%s' - l'indice d'un item de famille est celui de la section 3.6",
                                item.enonce())
                        .isEqualTo(INDICE_DES_ITEMS_DE_FAMILLE);
            }
        }
    }

    /** TRV-CE1-5 : aucun indice ne renvoie a l'oreille. */
    private static void verifierQuAucunIndiceNeRenvoieALOreille(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' renvoie à l'oreille", item.enonce(), indice)
                    .isFalse();
        }
    }

    /** TRV-CE1-5 : un indice compte douze mots au plus. */
    private static void verifierQuChaqueIndiceCompteAuPlusDouzeMots(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            int compte = mots(indice).size();
            assertThat(compte)
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' compte %d mots, douze au plus", item.enonce(),
                            indice, compte)
                    .isLessThanOrEqualTo(12);
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
    void adr0081CePaquetDeclareFraCe124EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-24"));
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
    void trv7fm1LePaquetGardeAuMoinsVingtItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items.size()).as("FM1 : au moins vingt items (TRV-CE1-7)").isGreaterThanOrEqualTo(20);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
        assertThat(new HashSet<>(items.stream().map(Question::enonce).toList()))
                .as("TRV-CE1-7 : deux items ne partagent jamais le même énoncé")
                .hasSize(items.size());
    }

    @Test
    void fam1LesProportionsSontRecomptees() {
        verifierLesProportions(items());
    }

    @Test
    void fam2AucuneReponseSavanteRefuseeNEstAttendue() {
        verifierQuAucuneReponseSavanteNEstAttendue(items());
    }

    @Test
    void fam3LeNomDeLAffixeNEstJamaisLuParLEnfant() {
        verifierQueLAffixeNEstJamaisNomme(items());
    }

    @Test
    void fm6ChaqueItemDeFamillePorteLIndiceDeLaSection36() {
        verifierLIndiceDesItemsDeFamille(items());
    }

    @Test
    void trv5AucunIndiceNeRenvoieALOreille() {
        verifierQuAucunIndiceNeRenvoieALOreille(items());
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMots() {
        verifierQuChaqueIndiceCompteAuPlusDouzeMots(items());
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void trv5LIndiceNEstPasIdentiqueSurToutLePaquet() {
        Set<String> distincts = new HashSet<>();
        for (Question item : items()) {
            distincts.add(item.indice().orElseThrow());
        }
        assertThat(distincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
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

    /** ADR-0013 obligation 3 : un seul item de famille sur cinq, alors que FAM-CE1-1 en demande trois. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportionsDeFamille() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAM-CE1-1")
                .hasMessageContaining("1 items de famille sur 5");
    }

    /** ADR-0013 obligation 3 : cinq items de famille et aucun d'affixe. */
    @Test
    void leJeuFautifSansAffixeCommiteEstRefuseSesProportionsDAffixe() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif-sans-affixe");

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAM-CE1-1")
                .hasMessageContaining("0 items d'affixe sur 5");
    }

    /** ADR-0013 obligation 3 : librairie attendu pour livre. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLibrairie() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierQuAucuneReponseSavanteNEstAttendue(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAM-CE1-2")
                .hasMessageContaining("librairie");
    }

    /** ADR-0013 obligation 3 : le mot suffixe dans un indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiNommeLAffixe() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierQueLAffixeNEstJamaisNomme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAM-CE1-3")
                .hasMessageContaining("suffixe");
    }

    /** ADR-0013 obligation 3 : un indice de famille qui n'est pas celui de la section 3.6. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeFamilleDifferent() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierLIndiceDesItemsDeFamille(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FM6")
                .hasMessageContaining("livre");
    }

    /** ADR-0013 obligation 3 : un indice qui renvoie a l'oreille. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiRenvoieALOreille() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierQuAucunIndiceNeRenvoieALOreille(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("renvoie à l'oreille")
                .hasMessageContaining("Écoute le début du mot.");
    }

    /** ADR-0013 obligation 3 : un indice de quinze mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDePlusDeDouzeMots() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierQuChaqueIndiceCompteAuPlusDouzeMots(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("compte 15 mots");
    }

    /** ADR-0013 obligation 3 : un indice qui contient la bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs("ce1-familles-de-mots-fautif");

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("celui qui nage");
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
