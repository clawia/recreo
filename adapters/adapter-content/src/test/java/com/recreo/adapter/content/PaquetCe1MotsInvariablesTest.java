package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanics.saisie.Saisie;
import com.recreo.domain.mechanics.saisie.SaisieMove;
import com.recreo.domain.mechanics.saisie.SaisieSession;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-21 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-mots-invariables} servi en saisie, ligne {@code FRA-CE1-21} - orthographier les
 * mots invariables. Conditions {@code MIV-CE1-1} et {@code MIV-CE1-3} de l'arbitrage prealable de s48,
 * conditions de {@code nadia-prof} sur la saisie ({@code arbitrage-nadia-q05-q10.md}, 4.3, points 1 a
 * 5), amendement de la story du 2026-10-21 (criteres 1 a 14) et {@code QP1} (les deux apostrophes).
 *
 * <p>Les valeurs sont recomptees sur les champs lus par le chargeur reel, jamais sur les commentaires
 * de l'en-tete. Les formes de reference sont lues dans {@code formeDeReference} : le paquet n'a plus
 * de {@code bonneReponse}, et un {@code grep} sur cette cle rend 0 par construction.
 *
 * <p><b>Ce que ce test ne lit pas, et qui reste a nadia-prof</b> (critere 9,
 * {@code arbitrage-nadia-palier-4.md} section 6) : qu'une phrase ne soit fermee que par un seul mot.
 * Aucun test ne lit le sens d'une phrase. Le garde ne verifie que ce qui s'ecrit : le trou, le mot
 * absent de l'ecran, les graphies. Ne sont pas non plus lus : l'exactitude de chaque phrase,
 * {@code TRV-CE1-6} (aucun role distribue par genre), le clavier de la tablette, le pli a 744 x 551.
 */
class PaquetCe1MotsInvariablesTest {

    private static final String PAQUET = "ce1-mots-invariables";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1MotsInvariablesTest";

    private static final String FAUTIF_CHOIX =
            CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-mots-invariables-fautif-choix.yaml";

    /** TRV-CE1-7 : le plancher de vingt items. */
    private static final int NOMBRE_D_ITEMS_MINIMUM = 20;

    /** LON-CE1-1 : un enonce de vingt mots au plus. */
    private static final int LON_1_MOTS_DE_L_ENONCE = 20;

    /** TRV-CE1-5 : un indice de douze mots au plus. */
    private static final int TRV_5_MOTS_DE_L_INDICE = 12;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final String TROU = "___";

    private static final char APOSTROPHE_DROITE = '\'';

    private static final char APOSTROPHE_TYPOGRAPHIQUE = '\u2019';

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

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1MotsInvariablesTest.class.getResourceAsStream(chemin)) {
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

    /** Sans accent, en minuscules, avec une seule apostrophe : la forme que la saisie compare. */
    private static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            replie.append(signe == APOSTROPHE_TYPOGRAPHIQUE ? APOSTROPHE_DROITE : signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }

    private static List<ReponseAEcrire> tousLesItems(ContentSource source) {
        RandomSource hasard = borne -> 0;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> tires = new ArrayList<>();
        for (Item item = source.next(hasard, dejaVus).orElse(null); item != null;
                item = source.next(hasard, dejaVus).orElse(null)) {
            tires.add(item);
            dejaVus.add(item.id());
        }
        return ClassementDesItems.demandantUneReponseEcrite(tires);
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

    private static List<ReponseAEcrire> items() {
        return tousLesItems(paquet());
    }

    private static List<ReponseAEcrire> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-mots-invariables-fautif"));
    }

    private static List<String> motsDeCpMotDicte() {
        List<String> mots = tousLesItems(new YamlContentPackLoader().chargerPaquet("cp-mot-dicte"))
                .stream().map(ReponseAEcrire::formeDeReference).toList();
        assertThat(mots).as("le paquet de CP est lu, sinon la clause ne juge rien").isNotEmpty();
        return mots;
    }

    private static String indice(ReponseAEcrire item) {
        return item.indice().orElseThrow(
                () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
    }

    /** Un mot entier dans un texte, sans accent ni casse, apostrophes confondues. */
    private static boolean contientLeMot(String texte, String mot) {
        Pattern entier = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(replier(mot))
                + "(?![\\p{L}\\p{N}])", FLAGS);
        return entier.matcher(replier(texte)).find();
    }

    /** Critere 1 et TRV-CE1-1 : de type saisie, les quatre champs de l'item et aucun autre. */
    private static void verifierLesChampsDeChaqueItem(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        assertThat(corps.get("type")).as("critère 1 : le paquet est de type saisie").isEqualTo("saisie");
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : '%s' - enonce, formeDeReference, formesAcceptees et indice seuls,"
                            + " ni proposition, ni texte support, ni champ à voix haute, ni illustration",
                            champs.get("enonce"))
                    .containsExactlyInAnyOrder("enonce", "formeDeReference", "formesAcceptees", "indice");
        }
    }

    /** Critere 5 : plus de ligne DEGRADE, et les deux phrases d'en-tete du type question sont reecrites. */
    private static void verifierQueLEnteteNeDitPlusLeTypeQuestion(String contenu) {
        assertThat(contenu.lines().filter(ligne -> ligne.startsWith("# DEGRADE")).count())
                .as("critère 5 : aucune ligne # DEGRADE").isZero();
        assertThat(contenu)
                .as("critère 5 : l'en-tête ne dit plus que la saisie n'est pas arbitrée")
                .doesNotContain("n'est pas arbitree");
        assertThat(contenu)
                .as("critère 5 : l'en-tête ne dit plus que la consigne porte la question")
                .doesNotContain("La question est portee par la consigne du jeu quand l'enonce ne la porte pas");
    }

    /** Un seul trou par enonce : la phrase a completer, et le mot a ecrire a sa place. */
    private static void verifierLeTrou(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            int trous = item.enonce().split(Pattern.quote(TROU), -1).length - 1;
            assertThat(trous)
                    .as("critère 9 : '%s' porte %d trous, un seul attendu", item.enonce(), trous)
                    .isEqualTo(1);
        }
    }

    /**
     * Critere 2 et MIV-CE1-3 : le mot n'est ecrit nulle part a l'ecran avant la reponse, ni dans son
     * enonce ni dans son indice, et aucun mot cible n'est ecrit dans l'enonce ou l'indice d'un
     * autre item, ou il servirait de modele.
     */
    private static void verifierQueLeMotNEstPasAffiche(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            for (ReponseAEcrire cible : items) {
                String mot = cible.formeDeReference();
                String de = cible == item ? "son mot" : "le mot d'un autre item";
                assertThat(contientLeMot(item.enonce(), mot))
                        .as("critère 2 : '%s' - l'énoncé écrit %s : %s", item.enonce(), de, mot)
                        .isFalse();
                assertThat(contientLeMot(indice(item), mot))
                        .as("critère 2 : '%s' - l'indice '%s' écrit %s : %s", item.enonce(), indice(item),
                                de, mot)
                        .isFalse();
            }
        }
    }

    /** Critere 3 : aucun accent dans le mot attendu, tant que SAI-1 tolere l'accent. */
    private static void verifierQueLeMotAttenduNePorteAucunAccent(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            String mot = item.formeDeReference();
            assertThat(Normalizer.normalize(mot, Normalizer.Form.NFD).chars()
                    .anyMatch(signe -> Character.getType(signe) == Character.NON_SPACING_MARK))
                    .as("critère 3 : le mot attendu '%s' ('%s') porte un accent", mot, item.enonce())
                    .isFalse();
        }
    }

    /**
     * Critere 4 : une forme acceptee n'est jamais une faute d'orthographe du mot (SAI-6, SAI-7) : a
     * l'accent, a la casse et a l'apostrophe pres, c'est le mot lui-meme.
     */
    private static void verifierQueChaqueFormeAccepteeEstLeMot(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            for (String forme : item.formesAcceptees()) {
                assertThat(replier(forme))
                        .as("critère 4 : '%s' - la forme acceptée '%s' n'est pas le mot '%s'",
                                item.enonce(), forme, item.formeDeReference())
                        .isEqualTo(replier(item.formeDeReference()));
            }
        }
    }

    /** QP1 : un mot a apostrophe porte les deux graphies, U+0027 et U+2019, aucune n'etant une faute. */
    private static void verifierLesDeuxApostrophes(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            List<String> graphies = new ArrayList<>(item.formesAcceptees());
            graphies.add(item.formeDeReference());
            if (graphies.stream().noneMatch(g -> g.indexOf(APOSTROPHE_DROITE) >= 0
                    || g.indexOf(APOSTROPHE_TYPOGRAPHIQUE) >= 0)) {
                continue;
            }
            String droite = item.formeDeReference().replace(APOSTROPHE_TYPOGRAPHIQUE, APOSTROPHE_DROITE);
            String typographique = droite.replace(APOSTROPHE_DROITE, APOSTROPHE_TYPOGRAPHIQUE);
            assertThat(graphies)
                    .as("QP1 : '%s' - le mot '%s' porte une apostrophe, U+0027 et U+2019 doivent"
                            + " être jugées justes", item.enonce(), item.formeDeReference())
                    .contains(droite, typographique);
        }
    }

    /** Critere 6, NRC-CE1-1 : aucun mot attendu n'est un mot de cp-mot-dicte (FRA-CP-09). */
    private static void verifierQuAucunMotNEstRepris(List<ReponseAEcrire> items, List<String> motsDuCp) {
        List<String> motsReplies = motsDuCp.stream().map(PaquetCe1MotsInvariablesTest::replier).toList();
        for (ReponseAEcrire item : items) {
            assertThat(motsReplies)
                    .as("critère 6 : '%s' - le mot '%s' est un mot de cp-mot-dicte", item.enonce(),
                            item.formeDeReference())
                    .doesNotContain(replier(item.formeDeReference()));
        }
    }

    /** LON-CE1-1 et TRV-CE1-5 : un enonce de vingt mots au plus, un indice de douze, sans renvoi a l'oreille. */
    private static void verifierLaForme(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            assertThat(mots(item.enonce()))
                    .as("LON-CE1-1 : '%s' compte plus de %d mots", item.enonce(), LON_1_MOTS_DE_L_ENONCE)
                    .hasSizeLessThanOrEqualTo(LON_1_MOTS_DE_L_ENONCE);
            String indice = indice(item);
            assertThat(mots(indice))
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' compte plus de %d mots", item.enonce(), indice,
                            TRV_5_MOTS_DE_L_INDICE)
                    .hasSizeLessThanOrEqualTo(TRV_5_MOTS_DE_L_INDICE);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' renvoie à l'oreille", item.enonce(), indice)
                    .isFalse();
        }
    }

    /** TRV-CE1-4 : aucun mot du lexique exclu dans ce que l'enfant lit, soit l'enonce et l'indice. */
    private static void verifierLeLexiqueExclu(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            for (String texte : List.of(item.enonce(), indice(item))) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("TRV-CE1-4 : '%s' - '%s' contient un mot du lexique exclu", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /**
     * TYP-1 (ADR-0067) : l'espace apres une ouverture de chevron, avant une fermeture et avant ?, :, ;
     * ou ! est insecable. Une espace ordinaire laisse le signe seul en debut de ligne. Mutation L10 de
     * yanis-qa : une espace ordinaire dans un chevron ne rougissait aucun garde de la saisie.
     */
    private static void verifierLesEspacesInsecables(List<ReponseAEcrire> items) {
        String nbsp = String.valueOf((char) 160);
        String ouvrant = String.valueOf((char) 171);
        String fermant = String.valueOf((char) 187);
        Pattern ordinaire = Pattern.compile(ouvrant + "[^" + nbsp + "]|[^" + nbsp + "]" + fermant
                + "|[^" + nbsp + "][?:;!]");
        for (ReponseAEcrire item : items) {
            for (String texte : List.of(item.enonce(), indice(item))) {
                assertThat(ordinaire.matcher(texte).find())
                        .as("TYP-1 : '%s' - '%s' porte une espace ordinaire ou aucune espace dans un chevron ou"
                                + " devant un signe double", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /** MIV-CE1-1 : aucun mot decide par la regle de FRA-CE1-22 (m devant b ou p : combien, jambe). */
    private static void verifierQuAucunMotNEstDecideParLaNasale(List<ReponseAEcrire> items) {
        for (ReponseAEcrire item : items) {
            assertThat(replier(item.formeDeReference()))
                    .as("MIV-CE1-1 : '%s' - le mot '%s' est decide par la regle de FRA-CE1-22 (m devant b ou p)",
                            item.enonce(), item.formeDeReference())
                    .doesNotContainPattern("m[bp]");
        }
    }

    /** MIV-CE1-1 : la liste des mots cibles est ecrite en tete du fichier, pas seulement leur nombre. */
    private static void verifierQueLEnteteListeLesMots(String contenu, List<String> mots) {
        String entete = String.join(" ", contenu.lines().takeWhile(ligne -> !ligne.startsWith("schema:")).toList());
        for (String mot : mots) {
            assertThat(contientLeMot(entete, mot))
                    .as("MIV-CE1-1 : le mot '%s' n'est pas dans la liste declaree en tete du fichier", mot)
                    .isTrue();
        }
    }

    /**
     * L'entree du catalogue qui sert le paquet est unique, de mecanique saisie, son titre ne porte pas
     * le niveau (ADR-0078), l'espace avant un signe double est insecable et le jeu se joue en dix
     * manches (DEC-0121, aucune derogation pour ce paquet).
     */
    private static void verifierLEntreeDuCatalogue(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<Map<?, ?>> entrees = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                entrees.add(jeu);
            }
        }
        assertThat(entrees).as("une seule entrée du catalogue sert le paquet %s", PAQUET).hasSize(1);
        Map<?, ?> entree = entrees.get(0);
        String titre = String.valueOf(entree.get("titre"));
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(entree.get("mecanique")).as("la mécanique est saisie").isEqualTo("saisie");
        assertThat(entree.get("nombreDeManches")).as("dix manches (DEC-0121)").isEqualTo(10);
        assertThat(titre).as("le titre ne porte pas le niveau (ADR-0078)")
                .doesNotContainPattern("(?i)\\b(CP|CE1|CE2|CM1|CM2)\\b");
        assertThat(titre + " " + consigne)
                .as("l'espace avant ?, :, ; ou ! d'un titre ou d'une consigne est insécable (U+00A0)")
                .doesNotContainPattern("[^\\u00A0] [?:;!]");
        List<Object> identifiants = new ArrayList<>();
        for (Object jeu : (List<?>) corps.get("jeux")) {
            identifiants.add(((Map<?, ?>) jeu).get("id"));
        }
        assertThat(identifiants).as("l'identifiant de l'entrée est unique au catalogue")
                .doesNotHaveDuplicates();
    }

    /** Joue {@code forme} sur la manche de rang {@code rang}, sur une partie neuve de la mecanique reelle. */
    private static boolean verdictJusteSur(int rang, ReponseAEcrire item, String forme) {
        Saisie mecanique = new Saisie();
        SaisieSession saisie =
                (SaisieSession) mecanique.start(paquet(), borne -> rang < borne ? rang : 0, 1);
        assertThat(saisie.enonceManche())
                .as("la manche visée n'a pas été atteinte pour l'item %s", item.id().value())
                .contains(item.enonce());
        GameSession apres = mecanique.apply(saisie, new SaisieMove(forme));
        return ((SaisieSession) apres).dernierVerdict().map(verdict -> verdict.juste()).orElse(false);
    }

    @Test
    void adr0081CePaquetDeclareFraCe121EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-21"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void c1LePaquetEstDeTypeSaisieEtChaqueItemPorteSesQuatreChampsSeuls() {
        verifierLesChampsDeChaqueItem(lire(FICHIER_CONTENU));
        assertThat(lire(FICHIER_CONTENU).lines().filter(ligne -> ligne.startsWith("type: saisie")).count())
                .as("critère 1 : une seule ligne type: saisie").isEqualTo(1);
    }

    @Test
    void c5LEnteteNeDitPlusLeTypeQuestionNiLaLigneDegrade() {
        verifierQueLEnteteNeDitPlusLeTypeQuestion(lire(FICHIER_CONTENU));
    }

    @Test
    void miv1LePaquetGardeAuMoinsVingtItemsChacunPourUnMotDistinct() {
        List<ReponseAEcrire> items = items();
        assertThat(items).as("TRV-CE1-7 : 20 items au moins")
                .hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_MINIMUM);
        assertThat(items.stream().map(item -> replier(item.formeDeReference())).toList())
                .as("MIV-CE1-3 : chaque mot cible est la réponse d'un seul item")
                .doesNotHaveDuplicates();
        assertThat(items.stream().map(ReponseAEcrire::enonce).toList())
                .as("aucun énoncé n'est repris d'un autre item").doesNotHaveDuplicates();
    }

    @Test
    void c9ChaqueEnonceNePorteQuUnTrouASaPlace() {
        verifierLeTrou(items());
    }

    @Test
    void c2LeMotNEstEcritNullePartALEcranAvantLaReponse() {
        verifierQueLeMotNEstPasAffiche(items());
    }

    @Test
    void c3AucunMotAttenduNePorteUnAccent() {
        verifierQueLeMotAttenduNePorteAucunAccent(items());
    }

    @Test
    void c4AucuneFormeAccepteeNEstUneFauteEtSeuleLApostropheEnPorte() {
        List<ReponseAEcrire> items = items();
        verifierQueChaqueFormeAccepteeEstLeMot(items);
        List<String> avecFormes = items.stream().filter(item -> !item.formesAcceptees().isEmpty())
                .map(ReponseAEcrire::formeDeReference).toList();
        assertThat(avecFormes)
                .as("critère 4 : seul le mot à apostrophe porte une forme acceptée, tout autre item la"
                        + " déclare vide")
                .containsExactly("aujourd'hui");
    }

    @Test
    void qp1LesDeuxApostrophesSontJugeesJustesParLaMecaniqueReelle() {
        List<ReponseAEcrire> items = items();
        verifierLesDeuxApostrophes(items);
        int rang = -1;
        for (int i = 0; i < items.size(); i++) {
            if (replier(items.get(i).formeDeReference()).equals("aujourd'hui")) {
                rang = i;
            }
        }
        assertThat(rang).as("le mot à apostrophe est dans le paquet").isGreaterThanOrEqualTo(0);
        ReponseAEcrire item = items.get(rang);
        for (String forme : List.of("Aujourd'hui", "Aujourd\u2019hui", "aujourd'hui", "AUJOURD'HUI", "  aujourd\u2019hui  ")) {
            assertThat(verdictJusteSur(rang, item, forme))
                    .as("critère 10 : la graphie '%s' doit être jugée juste", forme).isTrue();
        }
        assertThat(verdictJusteSur(rang, item, "aujourdhui"))
                .as("l'apostrophe est une lettre du mot : sans elle, la réponse est refusée").isFalse();
    }

    @Test
    void sai1Sai3Sai4ChaqueMotEstAccepteEnMajusculesEtAvecDesBlancsAutour() {
        List<ReponseAEcrire> items = items();
        for (int rang = 0; rang < items.size(); rang++) {
            ReponseAEcrire item = items.get(rang);
            for (String forme : List.of(
                    item.formeDeReference(),
                    item.formeDeReference().toUpperCase(Locale.FRENCH),
                    "  " + item.formeDeReference() + "  ")) {
                assertThat(verdictJusteSur(rang, item, forme))
                        .as("item %s (\"%s\") : la forme \"%s\" doit être acceptée", item.id().value(),
                                item.formeDeReference(), forme)
                        .isTrue();
            }
        }
    }

    @Test
    void sai6UneFauteSurLeMotEstRefuseeSurChaqueItem() {
        List<ReponseAEcrire> items = items();
        for (int rang = 0; rang < items.size(); rang++) {
            ReponseAEcrire item = items.get(rang);
            String faute = item.formeDeReference().substring(0, item.formeDeReference().length() - 1);
            assertThat(verdictJusteSur(rang, item, faute))
                    .as("item %s (\"%s\") : la forme tronquée \"%s\" doit être refusée", item.id().value(),
                            item.formeDeReference(), faute)
                    .isFalse();
        }
    }

    @Test
    void nrcCe11AucunMotAttenduNEstUnMotDeCpMotDicte() {
        verifierQuAucunMotNEstRepris(items(), motsDeCpMotDicte());
    }

    @Test
    void trv5ChaqueEnonceTientEnVingtMotsChaqueIndiceEnDouzeSansRenvoiALOreilleEtNEstPasUniqueSurLePaquet() {
        List<ReponseAEcrire> items = items();
        verifierLaForme(items);
        assertThat(items.stream().map(PaquetCe1MotsInvariablesTest::indice).distinct().count())
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet").isGreaterThan(1);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit() {
        verifierLeLexiqueExclu(items());
    }

    @Test
    void typ1LesChevronsEtLesSignesDoublesPortentUneEspaceInsecable() {
        verifierLesEspacesInsecables(items());
    }

    @Test
    void miv1AucunMotNEstDecideParLaRegleDeLaNasale() {
        verifierQuAucunMotNEstDecideParLaNasale(items());
    }

    @Test
    void miv1LEnteteListeLesMotsCibles() {
        verifierQueLEnteteListeLesMots(lire(FICHIER_CONTENU),
                items().stream().map(ReponseAEcrire::formeDeReference).toList());
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueSaMecaniqueEstSaisieSonTitreNePorteNiNiveauNiEspaceOrdinaire() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-1, un champ de choix et un champ a voix haute. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnChampDeChoix() {
        String contenu = lireRessource(FAUTIF_CHOIX).replace("type: question", "type: saisie");

        assertThatThrownBy(() -> verifierLesChampsDeChaqueItem(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("Le tapis est ___ le chat.");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 1, un paquet de type question. */
    @Test
    void leJeuFautifCommiteEstRefuseSousLeTypeQuestion() {
        String contenu = lireRessource(FAUTIF_CHOIX);

        assertThatThrownBy(() -> verifierLesChampsDeChaqueItem(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 1");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 5, un en-tete qui garde DEGRADE. */
    @Test
    void leJeuFautifCommiteEstRefuseSaLigneDegrade() {
        String contenu = lireRessource(FAUTIF_CHOIX);

        assertThatThrownBy(() -> verifierQueLEnteteNeDitPlusLeTypeQuestion(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 5");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 2, le mot dans l'indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotDansLIndice() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLeMotNEstPasAffiche(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 2")
                .hasMessageContaining("Le mot sous est tout petit.");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 2, le mot dans l'enonce. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotDansLEnonce() {
        List<ReponseAEcrire> items = itemsFautifs().subList(1, 2);

        assertThatThrownBy(() -> verifierQueLeMotNEstPasAffiche(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 2")
                .hasMessageContaining("l'énoncé écrit son mot : souvent");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 3, un mot attendu accentue. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotAccentue() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLeMotAttenduNePorteAucunAccent(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 3")
                .hasMessageContaining("très");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 4, une forme acceptee qui est une faute. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUneFormeAccepteeFautive() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaqueFormeAccepteeEstLeMot(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 4")
                .hasMessageContaining("pandant");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de QP1, une apostrophe sans sa graphie typographique. */
    @Test
    void leJeuFautifCommiteEstRefuseUneApostropheSansSaGraphieTypographique() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesDeuxApostrophes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("QP1")
                .hasMessageContaining("aujourd'hui");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 6, un mot de cp-mot-dicte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDeCpMotDicte() {
        List<ReponseAEcrire> items = itemsFautifs();
        List<String> motsDuCp = motsDeCpMotDicte();

        assertThatThrownBy(() -> verifierQuAucunMotNEstRepris(items, motsDuCp))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 6")
                .hasMessageContaining("lune");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du critere 9, un enonce sans trou. */
    @Test
    void leJeuFautifCommiteEstRefuseUnEnonceSansTrou() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLeTrou(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("critère 9")
                .hasMessageContaining("Les enfants jouent dans la cour.")
                .hasMessageContaining("0 trous");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice de seize mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeSeizeMots() {
        List<ReponseAEcrire> items = itemsFautifs().subList(7, 8);

        assertThatThrownBy(() -> verifierLaForme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("Relis bien toute la phrase");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un renvoi a l'oreille. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnRenvoiALOreille() {
        List<ReponseAEcrire> items = itemsFautifs().subList(8, 9);

        assertThatThrownBy(() -> verifierLaForme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("renvoie à l'oreille")
                .hasMessageContaining("Ouvre bien l'oreille");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-1, un enonce de vingt-quatre mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeVingtQuatreMots() {
        List<ReponseAEcrire> items = itemsFautifs().subList(10, 11);

        assertThatThrownBy(() -> verifierLaForme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1")
                .hasMessageContaining("Le petit chat noir et blanc");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-4, le mot complement dans l'indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuLexiqueExclu() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLeLexiqueExclu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-4")
                .hasMessageContaining("Cherche le complément");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TYP-1, une espace ordinaire dans un chevron et devant un point d'exclamation. */
    @Test
    void leJeuFautifCommiteEstRefuseUneEspaceOrdinaireDansUnChevron() {
        List<ReponseAEcrire> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-mots-invariables-fautif-espaces"));

        assertThatThrownBy(() -> verifierLesEspacesInsecables(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1")
                .hasMessageContaining("Non, ___");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de MIV-CE1-1, un mot decide par la regle de FRA-CE1-22. */
    @Test
    void leJeuFautifCommiteEstRefuseUnMotDecideParLaNasale() {
        List<ReponseAEcrire> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunMotNEstDecideParLaNasale(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MIV-CE1-1")
                .hasMessageContaining("Combien");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de MIV-CE1-1, un en-tete qui n'ecrit pas la liste des mots. */
    @Test
    void leJeuFautifCommiteEstRefuseUnEnteteSansLaListeDesMots() {
        String contenu = lireRessource(FAUTIF_CHOIX);

        assertThatThrownBy(() -> verifierQueLEnteteListeLesMots(contenu, List.of("toujours", "sous")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MIV-CE1-1")
                .hasMessageContaining("toujours");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un titre qui porte le niveau. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonTitreQuiPorteLeNiveau() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-titre-avec-niveau.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("niveau");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, une espace ordinaire devant le point d'interrogation. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonEspaceOrdinaire() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-espace-ordinaire.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("insécable");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, deux entrees pour le meme paquet. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSesDeuxEntrees() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-deux-entrees.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une seule entrée");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un nombre de manches different de dix. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonNombreDeManches() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-manches.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dix manches");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un paquet de saisie servi par le bandit manchot. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSaMecanique() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-mecanique.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("saisie");
    }
}
