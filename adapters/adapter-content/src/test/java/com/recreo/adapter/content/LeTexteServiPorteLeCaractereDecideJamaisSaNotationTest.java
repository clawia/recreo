package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0060. Deux defauts de la meme famille : le texte que l'enfant lit n'est pas fait des
 * caracteres qu'on a decides, et aucune porte ne le voit.
 *
 * <p><b>Le declencheur, s19.</b> marc-dev a ecrit a la main, via un heredoc, la notation d'un espace
 * insecable - la sequence d'echappement de six caracteres ASCII - a la place du caractere. Le YAML
 * reste valide, la compilation passe, les tests passent, le paquet se charge. Il l'a trouve en
 * comparant octet par octet contre un paquet de reference ; rien dans ce depot ne l'aurait vu.
 *
 * <p><b>Ce que la mesure a etabli, et qui n'etait pas su.</b> Sur snakeyaml 2.6, la resolution
 * depend du style du scalaire : dans un scalaire a guillemets doubles - le style de tous les paquets
 * servis - la sequence <i>est</i> resolue, et le texte servi est juste ; dans un scalaire a
 * apostrophes ou nu, elle ne l'est pas et les six caracteres atteignent l'enfant ; une sequence
 * inconnue en guillemets doubles fait echouer le chargement, donc bruyamment. Ce garde-fou juge
 * <b>le texte servi</b>, jamais les octets du fichier : c'est le texte que l'enfant lit qui est
 * l'objet, et juger le fichier produirait un rouge sur un commentaire (le paquet
 * {@code cm1-synonymes.yaml} en porte un) et un rouge sur une notation que le parseur a deja
 * resolue.
 *
 * <p><b>Clause 1 - la notation d'un caractere n'est pas le caractere.</b> Aucun texte servi ne porte
 * une contre-barre, une entite de caractere HTML, ni une notation de point de code. Trois formes,
 * jamais trois noms d'incidents (ADR-0039, s11) : chacune est la grammaire entiere d'une facon
 * d'ecrire un caractere sans l'ecrire.
 *
 * <p><b>Clause 2 - le caractere decide est present.</b> SYN-9 (nadia-prof, s17, bloquante) puis
 * ANT-5 (arbitrage prealable s19) exigent que les espaces a l'interieur des guillemets francais
 * soient insecables. C'est une decision pedagogique deja prise deux fois, appliquee a trois paquets,
 * et qu'aucun automate ne verifiait - un espace ordinaire y est <b>invisible a l'oeil</b>, sur le
 * fichier comme a l'ecran, et ne se voit qu'au retour a la ligne qu'il autorise. C'est le membre
 * silencieux de la famille ; la notation non resolue en est le membre bruyant.
 *
 * <p><b>Perimetre : le texte des items servis, jamais le catalogue.</b> Le perimetre derive du
 * repertoire de contenu (ADR-0013, obligation 1), chaque paquet est charge par le chargeur reel et
 * ses items sont tires par la meme chaine que celle qu'un enfant traverse (obligation 2). Le titre,
 * la consigne et le descripteur sont exclus pour un motif verifiable et non par commodite :
 * {@code TexteAccentueExposeParLePortEntrantTest} les inventorie chaine par chaine et les compare
 * <b>par egalite</b>, si bien qu'une notation y serait deja rouge. Le texte d'un item n'est
 * inventorie nulle part, et c'est exactement la ou le defaut est ne.
 *
 * <p><b>Note d'ecriture, de la meme famille que ce qu'il garde</b> : la notation en cause ne peut pas
 * s'ecrire telle quelle dans un fichier {@code .java}, meme en commentaire - le compilateur resout
 * ces sequences avant de decouper les jetons. Elle est donc nommee en toutes lettres ici, et les
 * caracteres invisibles du code sont ecrits par leur valeur numerique.
 */
class LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest";

    /**
     * Tirages effectues sur un paquet <b>combinatoire</b>, le seul qui n'enumere aucun item et ne
     * s'epuise donc jamais. ADR-0064 : sur un paquet enumere, la borne n'est pas ecrite ici, elle
     * est derivee de ce que le paquet declare, et une lecture partielle est un echec nomme.
     */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    private static final char GUILLEMET_OUVRANT = (char) 0x00AB;
    private static final char GUILLEMET_FERMANT = (char) 0x00BB;
    private static final char ESPACE_INSECABLE = (char) 0x00A0;

    /**
     * Toute contre-barre. Le critere est la contre-barre elle-meme, pas la liste des lettres qui la
     * suivent : une sequence inconnue est aussi fautive qu'une sequence connue, et enumerer les
     * lettres rejouerait la faute de s11 - un critere bati sur des noms d'incidents clos. Aucun texte
     * servi du depot n'en porte, et un contenu qui en aurait besoin un jour amenderait ADR-0060.
     */
    private static final Pattern CONTRE_BARRE = Pattern.compile("\\\\");

    /** Toute entite de caractere HTML, nommee ou numerique. Un fragment de balisage n'est pas du texte. */
    private static final Pattern ENTITE_HTML =
            Pattern.compile("&(?:#[0-9]{1,7}|#[xX][0-9A-Fa-f]{1,6}|[A-Za-z][A-Za-z0-9]{1,31});");

    /** La notation d'un point de code Unicode, celle qu'on ecrit dans une conversation, pas dans un texte. */
    private static final Pattern POINT_DE_CODE = Pattern.compile("[uU]\\+[0-9A-Fa-f]{4,6}");

    private static final List<Pattern> NOTATIONS = List.of(CONTRE_BARRE, ENTITE_HTML, POINT_DE_CODE);

    /**
     * Un texte que ce module sert, avec l'origine qui l'a produit (pour le message) et le fait de
     * savoir si l'enfant le <b>lit</b> ou si la mecanique le <b>compare</b>.
     *
     * <p><b>Lot 5a de s24, decisions 4, 5 et 11</b> : l'item de saisie a introduit des chaines que ce
     * module sert sans que personne ne les lise - les formes acceptees, qui sont le bareme auquel la
     * mecanique compare ce que l'enfant ecrit. Elles doivent traverser la clause 1 de ce garde-fou
     * (une notation d'echappement y rend la tolerance morte, et personne ne le verrait) et rester
     * hors des garde-fous d'accent et de signe double (une regle qui exigerait l'accent sur une forme
     * acceptee rougirait sur la tolerance que nadia-prof a demandee).</p>
     *
     * <p>Le fait est <b>declare a la construction</b>, jamais deduit du nom du champ : c'est le
     * producteur qui sait si l'enfant lit ce qu'il ajoute, et un jour ou l'autre un nom de champ
     * ressemblerait a un autre.</p>
     */
    record TexteServi(String paquet, String champ, String enonceDeLItem, String valeur,
                       boolean luParLEnfant) {

        /** Le cas ordinaire : tout ce que ce module servait avant la saisie est lu par l'enfant. */
        TexteServi(String paquet, String champ, String enonceDeLItem, String valeur) {
            this(paquet, champ, enonceDeLItem, valeur, true);
        }

        String origine() {
            return "paquet " + paquet + ", item \"" + enonceDeLItem + "\", champ " + champ;
        }
    }

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Item> itemsTires(ContentSource contenu, int tirages) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < tirages; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            items.add(item);
            dejaVus.add(item.id());
        }
        return items;
    }

    static List<TexteServi> textesServis(Path racine, String racineClasspath) {
        List<TexteServi> textes = new ArrayList<>();
        for (String paquet : paquets(racine)) {
            ContentSource source = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            // ADR-0064 : la borne vient du paquet, jamais d'un nombre ecrit ici. De quoi
            // l'epuiser, plus un tirage pour constater l'epuisement.
            int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racine, paquet);
            int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
            List<Item> items = itemsTires(source, tirages);
            if (declares > 0) {
                // ADR-0064 : un paquet enumere est lu EN ENTIER, ou ce garde-fou est rouge et le dit.
                assertThat(items)
                        .as("%s declare %d items sur disque et ce garde-fou n'en a tire que %d : la"
                                + " clause porterait sur un echantillon sans le dire", paquet, declares,
                                items.size())
                        .hasSize(declares);
            }
            for (Item itemTire : items) {
                if (itemTire instanceof ReponseAEcrire reponse) {
                    textes.addAll(textesDUnItemDeSaisie(paquet, reponse));
                    continue;
                }
                if (itemTire instanceof ZoneADesigner zone) {
                    textes.addAll(textesDUnItemDeDesignation(paquet, zone));
                    continue;
                }
                textes.addAll(textesDUneQuestion(paquet, (Question) itemTire));
            }
        }
        return textes;
    }

    /** ADR-0082 decision 2, etendue aux propositions par ADR-0104 decision 3 : la population se lit sur le prefixe. */
    private static final String PREFIXE_DE_REPRODUCTION = "oeuvre-";

    /**
     * ADR-0104 decision 6 : toutes les propositions de l'item sont des identifiants de reproduction.
     * La meme condition que {@code propositionsEnImage} de {@code PartieController} : ce que l'enfant
     * voit alors n'est pas un mot mais une image.
     */
    static boolean propositionsEnImage(Question item) {
        return Stream.concat(Stream.of(item.bonneReponse()), item.distracteurs().stream())
                .allMatch(proposition -> proposition.startsWith(PREFIXE_DE_REPRODUCTION));
    }

    /**
     * Les chaines qu'une question fait servir a ce module. <b>ADR-0104 decision 6 : les propositions
     * d'un item a propositions-images sont declarees {@code luParLEnfant = false}.</b> L'enfant n'y lit
     * rien, il regarde des images ; l'identifiant est de l'ASCII sans accent ({@code oeuvre-champ-de-ble}),
     * donc un faux rouge sur le garde-fou d'accent, qui lit chaque proposition comme un mot. Elles
     * restent jugees par la clause 1 de ce garde-fou : une notation d'echappement y serait fautive.
     * C'est le seul endroit ou cette exemption est ecrite, et les garde-fous qui consomment
     * {@code luParLEnfant} la suivent sans etre touches.
     */
    static List<TexteServi> textesDUneQuestion(String paquet, Question item) {
        boolean lues = !propositionsEnImage(item);
        List<TexteServi> textes = new ArrayList<>();
        textes.add(new TexteServi(paquet, "enonce", item.enonce(), item.enonce()));
        textes.add(new TexteServi(paquet, "bonneReponse", item.enonce(), item.bonneReponse(), lues));
        for (int rang = 0; rang < item.distracteurs().size(); rang++) {
            textes.add(new TexteServi(paquet, "distracteur " + (rang + 1), item.enonce(),
                    item.distracteurs().get(rang), lues));
        }
        item.indice().ifPresent(indice ->
                textes.add(new TexteServi(paquet, "indice", item.enonce(), indice)));
        item.explication().ifPresent(explication ->
                textes.add(new TexteServi(paquet, "explication", item.enonce(), explication)));
        // s23 : deux champs lus par l'enfant que ce perimetre ne ramassait pas. texteSupport
        // (ADR-0050, s22) est le plus long texte servi du depot ; partDesignee (ADR-0062) est
        // le fragment de l'enonce que l'ecran met en evidence. Les deux traversaient les deux
        // clauses de ce garde-fou sans etre lus - un perimetre qui a cesse de suivre son objet
        // quand l'objet s'est etendu.
        item.texteSupport().ifPresent(texte ->
                textes.add(new TexteServi(paquet, "texteSupport", item.enonce(), texte)));
        item.partDesignee().ifPresent(part ->
                textes.add(new TexteServi(paquet, "partDesignee", item.enonce(), part)));
        return textes;
    }

    /**
     * Les chaines qu'un item de saisie fait servir a ce module, et pour chacune si l'enfant la lit.
     *
     * <p>L'enonce et la forme de reference sont lus - le second au verdict, quand la mecanique montre
     * la forme attendue (SAI-8). L'indice et l'explication le sont comme sur un item de choix. <b>Les
     * formes acceptees ne le sont jamais</b> : elles sont le bareme auquel la mecanique compare ce que
     * l'enfant ecrit, et {@code CPL-3} y fait figurer des ecritures que SAI-1 et SAI-3 tolerent
     * precisement parce qu'elles ne sont pas irreprochables.</p>
     */
    private static List<TexteServi> textesDUnItemDeSaisie(String paquet, ReponseAEcrire item) {
        List<TexteServi> textes = new ArrayList<>();
        textes.add(new TexteServi(paquet, "enonce", item.enonce(), item.enonce()));
        textes.add(new TexteServi(paquet, "formeDeReference", item.enonce(), item.formeDeReference()));
        for (int rang = 0; rang < item.formesAcceptees().size(); rang++) {
            textes.add(new TexteServi(paquet, "forme acceptee " + (rang + 1), item.enonce(),
                    item.formesAcceptees().get(rang), false));
        }
        item.indice().ifPresent(indice ->
                textes.add(new TexteServi(paquet, "indice", item.enonce(), indice)));
        item.explication().ifPresent(explication ->
                textes.add(new TexteServi(paquet, "explication", item.enonce(), explication)));
        return textes;
    }

    /**
     * ADR-0080 : les chaines qu'un item de designation fait servir a ce module - toutes lues par
     * l'enfant. {@code bonneZone} en est exclue, comme {@code id} d'une {@code Zone} : ce sont des
     * identifiants compares, jamais du texte que l'enfant lit (meme motif que l'exclusion des formes
     * acceptees d'un item de saisie, pour la raison inverse - ici rien n'est jamais compare, tout est
     * lu).
     */
    private static List<TexteServi> textesDUnItemDeDesignation(String paquet, ZoneADesigner item) {
        List<TexteServi> textes = new ArrayList<>();
        textes.add(new TexteServi(paquet, "enonce", item.enonce(), item.enonce()));
        textes.add(new TexteServi(paquet, "illustrationTexteAlternatif", item.enonce(),
                item.illustrationTexteAlternatif()));
        for (Zone zone : item.zones()) {
            textes.add(new TexteServi(paquet, "libelle de la zone " + zone.id(), item.enonce(),
                    zone.libelle()));
        }
        item.indice().ifPresent(indice ->
                textes.add(new TexteServi(paquet, "indice", item.enonce(), indice)));
        item.explication().ifPresent(explication ->
                textes.add(new TexteServi(paquet, "explication", item.enonce(), explication)));
        item.texteAVoixHaute().ifPresent(texte ->
                textes.add(new TexteServi(paquet, "texteAVoixHaute", item.enonce(), texte)));
        return textes;
    }

    private static String pointsDeCode(String valeur) {
        StringBuilder rendu = new StringBuilder();
        valeur.codePoints().forEach(point -> rendu.append(String.format("U+%04X ", point)));
        return rendu.toString().trim();
    }

    /**
     * Les defauts d'un seul texte servi, clauses 1 et 2. Fonction pure, extraite pour que les mises a
     * l'epreuve par mutation ci-dessous s'exercent sur une chaine reellement servie plutot que sur une
     * fixture de ma main - la forme que yanis-qa a donnee a son garde-fou du meme sprint, et qui est
     * meilleure que la preuve manuelle et datee que j'avais ecrite dans ADR-0060.
     */
    static List<String> defautsDe(String origine, String valeur) {
        List<String> defauts = new ArrayList<>();
        for (Pattern notation : NOTATIONS) {
            Matcher trouve = notation.matcher(valeur);
            while (trouve.find()) {
                defauts.add(String.format(
                        "%s : le fragment \"%s\" est la notation d'un caractere, pas le caractere."
                                + " Le texte servi vaut %s. Ecrire le caractere lui-meme dans le"
                                + " paquet (ADR-0060, clause 1)",
                        origine, trouve.group(), pointsDeCode(valeur)));
            }
        }
        for (int rang = 0; rang < valeur.length(); rang++) {
            char lettre = valeur.charAt(rang);
            if (lettre == GUILLEMET_OUVRANT
                    && (rang + 1 >= valeur.length() || valeur.charAt(rang + 1) != ESPACE_INSECABLE)) {
                defauts.add(String.format(
                        "%s : le guillemet francais ouvrant n'est pas suivi d'un espace insecable."
                                + " Le texte servi vaut %s (SYN-9, ANT-5, ADR-0060, clause 2)",
                        origine, pointsDeCode(valeur)));
            }
            if (lettre == GUILLEMET_FERMANT
                    && (rang == 0 || valeur.charAt(rang - 1) != ESPACE_INSECABLE)) {
                defauts.add(String.format(
                        "%s : le guillemet francais fermant n'est pas precede d'un espace insecable."
                                + " Le texte servi vaut %s (SYN-9, ANT-5, ADR-0060, clause 2)",
                        origine, pointsDeCode(valeur)));
            }
        }
        return defauts;
    }

    private static int nombreDeGuillemetsFrancais(String valeur) {
        int compte = 0;
        for (int rang = 0; rang < valeur.length(); rang++) {
            char lettre = valeur.charAt(rang);
            if (lettre == GUILLEMET_OUVRANT || lettre == GUILLEMET_FERMANT) {
                compte++;
            }
        }
        return compte;
    }

    static void verifierQueChaqueTexteServiPorteSesCaracteres(Path racine, String racineClasspath) {
        List<TexteServi> textes = textesServis(racine, racineClasspath);
        assertThat(textes)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun texte servi sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        int guillemetsExamines = 0;
        for (TexteServi texte : textes) {
            defauts.addAll(defautsDe(texte.origine(), texte.valeur()));
            guillemetsExamines += nombreDeGuillemetsFrancais(texte.valeur());
        }

        assertThat(defauts)
                .as("ADR-0060 : un texte servi porte le caractere decide, jamais sa notation"
                        + " (clause 1), et l'espace interieur d'un guillemet francais est insecable"
                        + " (clause 2, SYN-9 et ANT-5)")
                .isEmpty();
        assertThat(guillemetsExamines)
                .as("ADR-0051 : aucun texte servi sous %s ne porte de guillemet francais, la clause 2"
                        + " de ce garde-fou n'examine donc aucun objet et ne prouve rien", racine)
                .isPositive();
    }

    /**
     * Un texte reellement servi par la production, porteur d'un guillemet francais et donc d'un
     * espace insecable : le materiau des quatre mises a l'epreuve par mutation. Derive du repertoire
     * comme le reste, jamais d'un paquet nomme, et s'invalide de lui-meme si la production cesse d'en
     * servir - une mise a l'epreuve qui ne mute plus rien de reel ne prouve plus rien.
     */
    private static String unTexteReelPorteurDUnGuillemet() {
        return textesServis(CONTENU_DE_PRODUCTION, "").stream()
                .map(TexteServi::valeur)
                .filter(valeur -> nombreDeGuillemetsFrancais(valeur) > 0)
                .filter(valeur -> valeur.indexOf(ESPACE_INSECABLE) >= 0)
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "plus aucun texte servi ne porte un guillemet francais flanque d'un espace"
                                + " insecable : ces mises a l'epreuve ne mutent plus rien de reel"));
    }

    @Test
    void chaqueTexteServiEnProductionPorteSesCaracteres() {
        verifierQueChaqueTexteServiPorteSesCaracteres(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part. Son
     * indice est ecrit dans un scalaire a apostrophes, le seul style ou la sequence d'echappement
     * survit au parseur - c'est la forme exacte du defaut de s19, et le seul style qui la laisse
     * atteindre l'enfant. Il porte par ailleurs un item conforme a la clause 2, pour que ce rouge-ci
     * ne puisse pas etre confondu avec celui de la non-vacuite.
     */
    @Test
    void unPaquetNeufDontUnTexteServiPorteUneNotationFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("notation"), CONTRE_EXEMPLE_CLASSPATH + "/notation"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-a-notation-non-resolue")
                .hasMessageContaining("est la notation d'un caractere, pas le caractere");
    }

    /** Le meme defaut, membre silencieux : les guillemets francais y encadrent un espace ordinaire. */
    @Test
    void unPaquetNeufDontLesGuillemetsEncadrentUnEspaceSecableFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("espace-secable"), CONTRE_EXEMPLE_CLASSPATH + "/espace-secable"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-a-espace-secable")
                .hasMessageContaining("n'est pas suivi d'un espace insecable");
    }

    /** Les memes paquets, corriges, ne doivent plus rien reprocher. */
    @Test
    void lesMemesPaquetsCorrigesPassentLeGardeFou() {
        verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }

    /**
     * ADR-0051 : la clause de non-vacuite se prouve elle aussi mordante, sur un arbre dont aucun
     * texte servi ne porte de guillemet francais. Sans elle, un depot qui abandonnerait cette
     * convention rendrait la clause 2 verte sans rien juger.
     */
    @Test
    void unArbreSansAucunGuillemetFrancaisFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierQueChaqueTexteServiPorteSesCaracteres(
                CONTRE_EXEMPLE.resolve("sans-guillemet"), CONTRE_EXEMPLE_CLASSPATH + "/sans-guillemet"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'examine donc aucun objet");
    }

    /**
     * Mises a l'epreuve par mutation d'une chaine <b>reellement servie</b>, absorbees du garde-fou
     * qu'a ecrit {@code yanis-qa} le meme jour sur la meme famille de defaut
     * ({@code AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest}, branche {@code s19-yanis}).
     * Sa forme est meilleure que la mienne sur ce point precis et je la reprends telle quelle plutot
     * que de m'en tenir a la preuve manuelle et datee que porte {@code ADR-0060} : une mutation
     * rejouee par la CI a chaque build vaut mieux qu'une mutation rejouee une fois par son auteur.
     *
     * <p>La graphie de l'artefact est ici <b>minuscule</b>, et ce n'est pas indifferent : c'est
     * exactement la graphie que le critere par sous-chaine de {@code yanis-qa} laisse passer.
     * Mesure du 2026-08-24, sa classe executee sans modification sur un indice de
     * {@code cm1-antonymes} mute en scalaire a apostrophes, loin de tout guillemet - graphie
     * minuscule : 7 tests sur 7 verts ; meme mutation, seule la casse hexadecimale changee :
     * 1 echec sur 7. Le present garde-fou est rouge dans les deux cas, parce que son critere est la
     * contre-barre et non le nom d'un incident.
     */
    @Test
    void uneChaineReelleOuLEspaceInsecableDevientLaNotationEnMinusculeEstUnDefaut() {
        String reel = unTexteReelPorteurDUnGuillemet();
        String mute = reel.replace(String.valueOf(ESPACE_INSECABLE), "\\" + "u00a0");

        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(reel);
        assertThat(defautsDe("production / mutation", mute))
                .as("clause 1 : la notation en graphie minuscule est un defaut au meme titre que la"
                        + " graphie majuscule")
                .isNotEmpty();
    }

    /** Meme mutation, second membre de la categorie : l'entite HTML, jamais resolue par aucun style. */
    @Test
    void uneChaineReelleOuLEspaceInsecableDevientUneEntiteHtmlEstUnDefaut() {
        String reel = unTexteReelPorteurDUnGuillemet();
        String mute = reel.replace(String.valueOf(ESPACE_INSECABLE), "&nbsp;");

        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(reel);
        assertThat(defautsDe("production / mutation", mute))
                .as("clause 1 : une entite de caractere HTML dans un texte servi est un defaut")
                .isNotEmpty();
    }

    /**
     * Le membre silencieux, sur du materiau reel : le geste d'un editeur qui "normalise les espaces",
     * ou d'une main qui tape la barre d'espace. Rien ne le distingue a l'oeil.
     */
    @Test
    void uneChaineReelleOuLEspaceInsecableDevientUnEspaceOrdinaireEstUnDefaut() {
        String reel = unTexteReelPorteurDUnGuillemet();
        String mute = reel.replace(String.valueOf(ESPACE_INSECABLE), " ");

        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(reel);
        assertThat(defautsDe("production / mutation", mute))
                .as("clause 2 : un guillemet francais flanque d'un espace ordinaire est un defaut")
                .isNotEmpty();
    }

    /** Le temoin des trois mutations : la meme chaine reelle, non mutee, ne reproche rien. */
    @Test
    void laMemeChaineReelleNonMuteeNeReprocheRien() {
        assertThat(defautsDe("production / temoin", unTexteReelPorteurDUnGuillemet())).isEmpty();
    }

    /**
     * ADR-0104 decision 7, garde-fou 3 : materiau reel, le premier item servi qui montre une
     * reproduction (aujourd'hui un item de {@code cp-decrire-une-oeuvre}). Derive du depot et jamais
     * nomme, et s'invalide de lui-meme si le depot n'en sert plus.
     */
    private static Question unItemReelQuiMontreUneReproduction() {
        for (String paquet : paquets(CONTENU_DE_PRODUCTION)) {
            ContentSource source = new YamlContentPackLoader("").chargerPaquet(paquet);
            int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(CONTENU_DE_PRODUCTION, paquet);
            for (Item item : itemsTires(source, declares + 1)) {
                if (item instanceof Question question
                        && question.illustration().filter(
                                illustration -> illustration.startsWith(PREFIXE_DE_REPRODUCTION)).isPresent()) {
                    return question;
                }
            }
        }
        throw new AssertionError("plus aucun item servi ne montre une reproduction : ces mises a"
                + " l'epreuve ne mutent plus rien de reel");
    }

    private static Question avecLesPropositions(Question reel, String bonneReponse,
                                                List<String> distracteurs) {
        return new Question(reel.id(), reel.enonce(), bonneReponse, distracteurs, reel.indice(),
                reel.explication(), reel.partDesignee(), reel.texteSupport(), reel.illustration(),
                reel.illustrationTexteAlternatif(), reel.texteAVoixHaute(), reel.tableauSupport());
    }

    private static TexteServi lePremier(List<TexteServi> textes, String champ) {
        return textes.stream().filter(texte -> texte.champ().equals(champ)).findFirst().orElseThrow();
    }

    /**
     * Garde-fou 3 : l'item reel, mute pour proposer {@code oeuvre-champ-de-ble}, ne fait plus lire
     * ses propositions a l'enfant. L'enonce et l'indice, eux, le restent.
     */
    @Test
    void lesPropositionsDUnItemEnImageNeSontPasLuesParLEnfant() {
        Question mute = avecLesPropositions(unItemReelQuiMontreUneReproduction(), "oeuvre-champ-de-ble",
                List.of("oeuvre-chambre", "oeuvre-grande-vague"));
        List<TexteServi> textes = textesDUneQuestion("paquet-mute", mute);

        assertThat(propositionsEnImage(mute)).isTrue();
        assertThat(textes).filteredOn(texte -> texte.champ().equals("bonneReponse")
                        || texte.champ().startsWith("distracteur "))
                .hasSize(3)
                .allMatch(texte -> !texte.luParLEnfant());
        assertThat(lePremier(textes, "enonce").luParLEnfant()).isTrue();
        assertThat(textes).filteredOn(texte -> texte.champ().equals("indice"))
                .allMatch(TexteServi::luParLEnfant);
    }

    /**
     * Morsure du garde-fou 3 : sans l'exemption, un identifiant lu par l'enfant fait rougir le
     * garde-fou d'accent sur {@code oeuvre-champ-de-ble} ({@code ble} n'est pas une forme du
     * francais, {@code blé} l'est). C'est la seule preuve que l'exemption a un effet : le faux rouge
     * mesure par ADR-0104 decision 6.
     */
    @Test
    void unIdentifiantDOeuvreLuParLEnfantFaitRougirLeGardeFouDAccent() {
        Question mute = avecLesPropositions(unItemReelQuiMontreUneReproduction(), "oeuvre-champ-de-ble",
                List.of("oeuvre-chambre", "oeuvre-grande-vague"));
        TexteServi propose = lePremier(textesDUneQuestion("paquet-mute", mute), "bonneReponse");
        assertThat(propose.valeur()).isEqualTo("oeuvre-champ-de-ble");

        TexteServi declareLu = new TexteServi(propose.paquet(), propose.champ(), propose.enonceDeLItem(),
                propose.valeur(), true);

        assertThat(AucunMotServiNeSEcarteDeSonAccentTest.defautsDe(declareLu.origine(), declareLu.valeur(),
                LexiqueFrancais.charger()))
                .as("un identifiant d'oeuvre lu par l'enfant doit rougir le garde-fou d'accent")
                .anyMatch(defaut -> defaut.contains("\"ble\""));
    }

    /**
     * L'exemption est etroite : un item dont une seule proposition est un texte reste lu en entier,
     * et c'est l'invariant 1 d'ADR-0104 qui le refuse, pas cette exemption qui l'absout.
     */
    @Test
    void unItemDontUneSeulePropositionEstUnTexteResteLuEnEntier() {
        Question mute = avecLesPropositions(unItemReelQuiMontreUneReproduction(), "oeuvre-champ-de-ble",
                List.of("oeuvre-chambre", "une peinture"));

        assertThat(propositionsEnImage(mute)).isFalse();
        assertThat(textesDUneQuestion("paquet-mute", mute))
                .filteredOn(texte -> texte.champ().equals("bonneReponse")
                        || texte.champ().startsWith("distracteur "))
                .hasSize(3)
                .allMatch(TexteServi::luParLEnfant);
    }

    /**
     * Garde-fou 3 de bout en bout, sur un paquet commite et lu par le chargeur reel : les trois
     * propositions-images n'atteignent pas le garde-fou d'accent, et le paquet le passe.
     */
    @Test
    void unPaquetCommiteAPropositionsEnImagePasseLeGardeFouDAccent() {
        Path racine = CONTRE_EXEMPLE.resolve("images-en-proposition");
        String classpath = CONTRE_EXEMPLE_CLASSPATH + "/images-en-proposition";

        assertThat(textesServis(racine, classpath))
                .filteredOn(texte -> texte.champ().equals("bonneReponse")
                        || texte.champ().startsWith("distracteur "))
                .hasSize(3)
                .allMatch(texte -> !texte.luParLEnfant());
        AucunMotServiNeSEcarteDeSonAccentTest.verifierQueChaqueMotServiPorteSonAccent(
                AucunMotServiNeSEcarteDeSonAccentTest.textesDesItems(racine, classpath));
    }
}
