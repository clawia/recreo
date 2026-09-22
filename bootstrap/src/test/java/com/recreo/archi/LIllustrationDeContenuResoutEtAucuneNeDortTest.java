package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0073 : un item declare l'illustration qu'il montre par un <b>identifiant</b>, jamais par un
 * chemin, jamais par des octets, jamais par une adresse. L'adapter web resout cet identifiant en un
 * fichier servi, en un seul endroit.
 *
 * <p><b>Ce que ce garde-fou rachete.</b> ADR-0014 et ADR-0036 exigent qu'une reference de ressource
 * d'un gabarit soit un chemin <b>litteral</b> : c'est ce qui prouve que la cible existe comme
 * fichier de {@code static/} et n'est pas cherchee sur le reseau. Une illustration de contenu ne
 * peut pas tenir ce critere - sa cible depend de l'item tire, donc l'expression est calculee. Mesure
 * du 2026-08-31, sur l'arbre a 130ba64 : un {@code <img th:src="@{'/illustrations/' + ${illustration}
 * + '.svg'}">} pose dans le fragment de manche rend {@code RessourcesTiercesTest} rouge en nommant
 * ADR-0014. La preuve ne disparait pas pour autant : elle <b>change de source</b>. Elle ne se lit
 * plus dans le gabarit, ou l'ensemble des cibles n'est pas enumerable, mais dans la <b>donnee</b>, ou
 * il l'est - c'est ce que ce fichier verifie.
 *
 * <p><b>Perimetre derive, jamais enumere (ADR-0013 obligation 1).</b> Les modules viennent du pom
 * agregateur, par {@link LesGardeFousSontMisALEpreuveTest#modulesDeclares(Path)}. Les paquets
 * viennent du repertoire de contenu de chaque module qui en porte un, par
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu(Path)}, jamais du
 * catalogue : un paquet retire du catalogue mais laisse sur disque reste juge. Les identifiants
 * viennent de SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit ces memes
 * fichiers. Les fichiers viennent des deux repertoires d'images de chaque module, jamais d'une liste
 * de noms.
 *
 * <p><b>Les deux repertoires sont deux populations, et c'est la clause qui porte tout le reste.</b>
 * {@code static/images/} est le <b>decor</b> : son jeu ne depend ni de l'item tire, ni de la colonne,
 * ni de la reponse attendue (condition C5 de nadia-prof), et il garde le critere de chemin litteral
 * d'ADR-0036 sans une virgule de changement. {@code static/illustrations/} est le <b>contenu</b> :
 * son jeu depend de l'item par construction. Confondre les deux rendrait le critere d'ADR-0036
 * inapplicable au decor - c'est-a-dire ferait disparaitre C5 - et c'est pourquoi la separation est
 * verifiee ici sur le materiau reel plutot que recommandee en prose.
 *
 * <p><b>Ce que ce garde-fou examine aujourd'hui, honnetement (ADR-0051).</b> Ce paragraphe disait,
 * au 2026-08-31 et de la main de theo-archi, qu'aucun paquet servi ne declarait d'illustration et
 * qu'aucun fichier ne vivait sous {@code static/illustrations/} : les quatre premieres clauses
 * portaient alors sur une population <b>vide</b>, et
 * {@code aucunPaquetServiNeDeclareEncoreDIllustration} epinglait ce zero pour rougir le jour ou le
 * premier paquet illustre entrerait. <b>Ce jour est arrive dans le meme sprint</b> : S25-FIG-1 pose
 * {@code cp-figures-planes}, douze items qui declarent une illustration et douze dessins sous
 * {@code static/illustrations/}. Le cliquet a rougi, il a fait son travail, et il est <b>retourne</b>
 * par {@link #lesClausesDIllustrationPortentDesormaisSurUnePopulationNonVide()} : ce qui est epingle
 * n'est plus le zero mais le fait que le perimetre ne redevienne pas vide en silence. Les cinq
 * morsures en memoire, elles, n'ont pas bouge d'une ligne.
 *
 * <p><b>Un trou que je m'inflige et que je nomme plutot que de le laisser trouver.</b> Ce fichier
 * ecrit {@code "illustration"} et {@code "illustrationTexteAlternatif"} en litteraux de chaine. Vu
 * d'ADR-0070, ces deux cles sont donc <b>deja nommees par une source du depot</b> : un paquet qui les
 * porterait avant que la fabrique les lise ne serait plus attrape par ADR-0070, dont c'etait
 * precisement l'objet. La propriete n'est pas perdue, elle est deplacee ici : un tel paquet est rouge
 * chez moi, parce que son identifiant ne resout aucun fichier. Elle est deplacee, pas affaiblie -
 * mais elle n'est plus au meme endroit, et quiconque lit ADR-0070 doit le savoir.
 *
 * <p><b>Aucun texte dans une illustration de contenu.</b> Le texte que l'enfant lit est juge par des
 * garde-fous qui derivent leur perimetre soit des paquets ({@code AucunMotServiNeSEcarteDeSonAccentTest},
 * le cliquet d'ADR-0067), soit des gabarits. Du texte pose dans le dessin n'est ni l'un ni l'autre :
 * ce serait un <b>troisieme angle mort</b>, ouvert le jour meme ou le depot en referme deja deux. Un
 * dessin ne porte donc ni {@code <text} ni {@code <tspan}. Ce que cette clause ne decide pas : si une
 * figure legendee est pedagogiquement souhaitable. Le jour ou nadia-prof en demande une, cette clause
 * cede - et les garde-fous de texte apprennent a lire un SVG dans le meme geste, sans quoi le texte
 * de l'enfant sortirait du controle.
 */
class LIllustrationDeContenuResoutEtAucuneNeDortTest {

    /**
     * Ecrits sous leur forme a barre oblique : c'est celle que
     * {@link LesGardeFousSontMisALEpreuveTest} cherche pour recenser un garde-fou de balayage.
     */
    private static final String ILLUSTRATIONS_DE_CONTENU = "src/main/resources/static/illustrations";

    private static final String DECOR = "src/main/resources/static/images";

    /** La cle par laquelle un item declare l'illustration qu'il montre (ADR-0073 clause 1). */
    private static final String CLE_ILLUSTRATION = "illustration";

    /** La cle par laquelle le meme item declare ce qu'un lecteur d'ecran annonce (clause 5). */
    private static final String CLE_TEXTE_ALTERNATIF = "illustrationTexteAlternatif";

    private static final String EXTENSION = ".svg";

    /**
     * La cle a laquelle le comptage textuel partage reconnait un item enumere (ADR-0064, lot 5a de
     * s24). Nommee ici parce que la clause 6 la juge, jamais lue comme une donnee de jeu.
     */
    private static final String CLE_ENONCE = "enonce";

    /** ADR-0013 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LIllustrationDeContenuResoutEtAucuneNeDortTest");

    /**
     * L'alphabet d'un identifiant d'illustration. C'est lui, et non une inspection du chemin
     * resolu, qui ferme {@code ../}, {@code https://} et toute connaissance de l'agencement du
     * serveur dans la donnee : un identifiant qui ne peut contenir ni point ni barre oblique ne
     * peut designer que le repertoire prevu.
     */
    private static final String ALPHABET = "[a-z0-9-]+";

    /**
     * ADR-0051 : la taille exacte des deux populations illustrees du depot au 2026-08-31, apres
     * l'entree des <b>deux</b> paquets illustres du sprint - S25-FIG-1 ({@code cp-figures-planes},
     * douze items et douze dessins) et S25-MON-1 ({@code cp-monnaie}, douze et douze). Ces deux
     * nombres ne sont pas des plafonds de confort : ils valent exactement la population, et un lot
     * qui l'agrandit les releve dans son propre commit.
     *
     * <p><b>Mesures par sofia-sm sur l'arbre fusionne des deux paires</b>, et non recopies depuis
     * l'annonce d'un lot : chaque lot avait mesure 12 et 12 sur sa propre branche, ce qui etait
     * exact chez lui et faux apres fusion.
     *
     * <p><b>24 vers 35, lea-dev, s34, S33-PAL2-1</b> : le premier paquet de designation servi
     * ({@code cp-frise-de-la-journee}) declare onze items, et {@code ZoneADesigner} rend
     * l'illustration <b>obligatoire</b> sur chacun (ADR-0080, decision 1) - les onze items
     * s'ajoutent donc tous, sans exception a chercher.</p>
     *
     * <p><b>35 vers 41, lea-dev, s35, S35-QLM11-PLANCLASSE</b> : {@code cp-plan-de-la-classe}
     * (QLM-CP-11), premiere livraison reelle de cette ligne, declare six items, et
     * {@code ZoneADesigner} rend l'illustration obligatoire sur chacun - les six items s'ajoutent
     * tous.</p>
     *
     * <p><b>41 vers 56, lea-dev, s39, S39-SOLIDES</b> : {@code ce1-solides} (MAT-CE1-16) declare
     * quinze items de type {@code question}, chacun avec une illustration - les quinze items
     * s'ajoutent tous.</p>
     */
    private static final int ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION = 56;

    /**
     * <b>24 vers 26, lea-dev, s34, S33-PAL2-1</b> : les deux dessins du premier paquet de
     * designation servi ({@code frise-journee-ecole.svg}, {@code frise-soir-routine.svg}).
     *
     * <p><b>26 vers 27, lea-dev, s35, S35-QLM11-PLANCLASSE</b> : {@code plan-classe.svg}, seul
     * dessin du second paquet de designation servi ({@code cp-plan-de-la-classe}).</p>
     *
     * <p><b>27 vers 42, lea-dev, s39, S39-SOLIDES</b> : les quinze dessins {@code solide-*.svg}
     * (cube, pave droit, boule, cylindre, cone, trois exemples chacun).</p>
     */
    private static final int FICHIERS_D_ILLUSTRATION = 42;

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Path> repertoires(Path racine, String sousArbre) {
        List<Path> trouves = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path repertoire = racine.resolve(module).resolve(sousArbre);
            if (Files.isDirectory(repertoire)) {
                trouves.add(repertoire);
            }
        }
        return trouves;
    }

    /** Les fichiers d'un repertoire d'images, avec leur nom de base comme cle. */
    static Map<String, String> fichiersIndexesParNomDeBase(List<Path> repertoires) {
        Map<String, String> fichiers = new LinkedHashMap<>();
        for (Path repertoire : repertoires) {
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                arborescence.filter(Files::isRegularFile).sorted().forEach(fichier -> {
                    String nom = fichier.getFileName().toString();
                    int point = nom.lastIndexOf('.');
                    fichiers.putIfAbsent(point < 0 ? nom : nom.substring(0, point), lire(fichier));
                });
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return fichiers;
    }

    /**
     * Les illustrations declarees par les items des paquets servis : l'identifiant, et le paquet qui
     * le porte pour que le message nomme le fichier a corriger. La valeur {@code null} du texte
     * alternatif est conservee telle quelle : c'est elle que la clause 5 juge.
     */
    static List<IllustrationDeclaree> illustrationsDeclarees(List<Path> repertoiresDeContenu) {
        List<IllustrationDeclaree> declarees = new ArrayList<>();
        for (Path repertoire : repertoiresDeContenu) {
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                List<Path> paquets = arborescence.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                        .sorted().toList();
                for (Path paquet : paquets) {
                    ajouterLesIllustrationsDe(paquet, declarees);
                }
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return declarees;
    }

    private static void ajouterLesIllustrationsDe(Path paquet, List<IllustrationDeclaree> declarees) {
        if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)) {
            return;
        }
        if (!(corps.get("items") instanceof List<?> items)) {
            return;
        }
        String nom = paquet.getFileName().toString();
        for (Object item : items) {
            if (item instanceof Map<?, ?> champs
                    && (champs.containsKey(CLE_ILLUSTRATION) || champs.containsKey(CLE_TEXTE_ALTERNATIF))) {
                Object identifiant = champs.get(CLE_ILLUSTRATION);
                Object alternatif = champs.get(CLE_TEXTE_ALTERNATIF);
                String premiereCle = champs.keySet().stream().findFirst()
                        .map(String::valueOf).orElse(null);
                declarees.add(new IllustrationDeclaree(nom,
                        identifiant == null ? null : String.valueOf(identifiant),
                        alternatif == null ? null : String.valueOf(alternatif),
                        premiereCle));
            }
        }
    }

    /**
     * Une illustration telle qu'un item la declare, avant toute resolution. {@code premiereCle} est
     * la premiere cle de l'item dans l'ordre du fichier - SnakeYAML rend les cles dans cet ordre -
     * et c'est la clause 6 qui la juge.
     */
    record IllustrationDeclaree(String paquet, String identifiant, String texteAlternatif,
                                 String premiereCle) {
    }

    /**
     * SOL-CE1-1 et SOL-CE1-4 (nadia-prof, arbitrage prealable du 2026-09-22, section 4) : un solide
     * n'est pas une figure plane. La derivation d'ADR-0077 - un dessin qui ne trace qu'une seule
     * forme rendue <b>est</b> cette forme - dit qu'une boule livree en cercle nu est un cercle, et
     * qu'un cube livre en carre nu est un carre. Aucune autre clause du depot ne le voit : ni
     * {@link #verifier}, dont les quatre clauses portent sur la resolution de l'identifiant et non
     * sur le trace du dessin, ni {@code PaquetCpFiguresPlanesTest}, qui ne lit que le YAML.
     *
     * <p><b>Le perimetre est derive, jamais enumere</b> (ADR-0013 obligation 1) : {@link
     * #LIGNE_DES_SOLIDES} est un code de ligne du referentiel, deja alloue et deja lu par
     * {@code ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest} (ADR-0081) - jamais un nom
     * de paquet, jamais un prefixe de nom de fichier, les deux qu'ADR-0077 interdit.
     *
     * <p><b>Ou elle vit, et pourquoi pas ailleurs</b> (theo-archi, avis de faisabilite s39, section
     * 12) : ici, le seul module qui voie a la fois les paquets de contenu et les fichiers de
     * dessin. {@code adapter-content} ne voit pas les SVG (ressources d'{@code adapter-web}) ;
     * {@code adapter-web} voit les dessins mais ne depend pas d'{@code adapter-content} (sens des
     * dependances). Le compteur de formes ({@link #formesRendues}) est donc duplique depuis
     * {@code ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest}
     * (adapter-web) - environ vingt lignes dupliquees valent mieux qu'un {@code test-jar} entre
     * modules pour un seul appelant.
     */
    private static final String LIGNE_DES_SOLIDES = "MAT-CE1-16";

    /** Un dessin declare par un paquet qui sert {@link #LIGNE_DES_SOLIDES}, avec le paquet qui le porte. */
    record IllustrationDeclareeParLigne(String paquet, String identifiant) {
    }

    /**
     * FIG-4, la meme population que celle d'{@code adapter-web} : les sept elements de forme du
     * langage SVG, plus {@code use} qui en instancie un.
     */
    private static final List<String> FORMES_SVG =
            List.of("circle", "ellipse", "line", "path", "polygon", "polyline", "rect", "use");

    private static final String DEBUT_DE_DEFINITIONS = "<defs";

    private static final String FIN_DE_DEFINITIONS = "</defs>";

    private static final String DEBUT_DE_COMMENTAIRE = "<!--";

    private static final String FIN_DE_COMMENTAIRE = "-->";

    /** Ce qui est enleve entre deux marques, autant de fois qu'il y a d'occurrences. */
    private static String sansLesBlocs(String source, String debut, String fin) {
        StringBuilder reste = new StringBuilder(source);
        int ouverture;
        while ((ouverture = reste.indexOf(debut)) >= 0) {
            int fermeture = reste.indexOf(fin, ouverture);
            if (fermeture < 0) {
                return reste.substring(0, ouverture);
            }
            reste.delete(ouverture, fermeture + fin.length());
        }
        return reste.toString();
    }

    /**
     * Le nombre de formes qu'un dessin <b>rend</b>, meme derivation que dans
     * {@code ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest} :
     * definitions et commentaires retires d'abord, puis les elements de {@link #FORMES_SVG},
     * reconnus seulement suivis d'un blanc, d'une barre oblique ou d'un chevron fermant.
     */
    static int formesRendues(String source) {
        if (source == null) {
            return 0;
        }
        String rendu = sansLesBlocs(
                sansLesBlocs(source, DEBUT_DE_DEFINITIONS, FIN_DE_DEFINITIONS),
                DEBUT_DE_COMMENTAIRE, FIN_DE_COMMENTAIRE);
        int comptees = 0;
        for (String forme : FORMES_SVG) {
            String marque = "<" + forme;
            int position = 0;
            while ((position = rendu.indexOf(marque, position)) >= 0) {
                int apres = position + marque.length();
                if (apres < rendu.length() && (Character.isWhitespace(rendu.charAt(apres))
                        || rendu.charAt(apres) == '/' || rendu.charAt(apres) == '>')) {
                    comptees++;
                }
                position = apres;
            }
        }
        return comptees;
    }

    /**
     * Le recensement des illustrations declarees par les seuls paquets qui servent {@code ligne}
     * dans {@code lignesDeReferentiel} (ADR-0081) - jamais par nom de paquet.
     */
    static List<IllustrationDeclareeParLigne> illustrationsDeclareesParLesPaquetsQuiServent(
            String ligne, List<Path> repertoiresDeContenu) {
        List<IllustrationDeclareeParLigne> declarees = new ArrayList<>();
        for (Path repertoire : repertoiresDeContenu) {
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                List<Path> paquets = arborescence.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                        .sorted().toList();
                for (Path paquet : paquets) {
                    ajouterSiLaLigneEstServie(paquet, ligne, declarees);
                }
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return declarees;
    }

    private static void ajouterSiLaLigneEstServie(Path paquet, String ligne,
            List<IllustrationDeclareeParLigne> declarees) {
        if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)
                || !(corps.get("lignesDeReferentiel") instanceof List<?> lignes)
                || lignes.stream().noneMatch(candidate -> ligne.equals(String.valueOf(candidate)))
                || !(corps.get("items") instanceof List<?> items)) {
            return;
        }
        String nom = paquet.getFileName().toString();
        for (Object item : items) {
            if (item instanceof Map<?, ?> champs
                    && champs.get(CLE_ILLUSTRATION) instanceof String identifiant) {
                declarees.add(new IllustrationDeclareeParLigne(nom, identifiant));
            }
        }
    }

    /**
     * Le juge, pur : mordu sur du materiau reel mute en memoire (morsure ci-dessous) et sur le jeu
     * fautif commite, jamais sur une fixture inventee.
     */
    static List<String> dessinsQuiNeTracentPasDeSolide(List<IllustrationDeclareeParLigne> declarees,
            Map<String, String> dessins) {
        List<String> fautes = new ArrayList<>();
        for (IllustrationDeclareeParLigne declaree : declarees) {
            int formes = formesRendues(dessins.get(declaree.identifiant()));
            if (formes < 2) {
                fautes.add(declaree.identifiant() + " (" + declaree.paquet() + ") : " + formes
                        + " forme rendue. Un dessin d'une seule forme EST cette forme (ADR-0077) :"
                        + " une boule livree en cercle nu est un cercle, et la ligne demande de"
                        + " reconnaitre un solide (SOL-CE1-1, SOL-CE1-4)");
            }
        }
        return fautes;
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath, et recoit exactement ce que les recensements
     * ci-dessus produisent. C'est ce qui permet de le mordre en mutant le materiau reel en memoire,
     * plutot qu'en fabriquant un depot fautif qui ressemblerait a ce que j'imagine (lecon de s05).
     */
    static void verifier(List<IllustrationDeclaree> declarees, Map<String, String> illustrations,
                          Map<String, String> decor) {
        if (decor.isEmpty()) {
            throw new AssertionError("ADR-0051 : le perimetre du decor est vide - la separation des"
                    + " deux populations d'images n'est prouvee sur rien");
        }
        List<String> fautes = new ArrayList<>();

        for (IllustrationDeclaree declaree : declarees) {
            String ou = declaree.paquet() + ", illustration '" + declaree.identifiant() + "'";
            if (declaree.identifiant() == null || !declaree.identifiant().matches(ALPHABET)) {
                fautes.add(ou + " : un identifiant d'illustration s'ecrit " + ALPHABET
                        + ", jamais un chemin ni une adresse. Le contenu declare ce qu'il montre,"
                        + " l'adapter decide ou le fichier est servi (ADR-0073 clause 1)");
                continue;
            }
            if (!illustrations.containsKey(declaree.identifiant())) {
                fautes.add(ou + " : aucun fichier " + declaree.identifiant() + EXTENSION + " sous "
                        + ILLUSTRATIONS_DE_CONTENU + ". L'enfant recoit une image cassee et rien ne"
                        + " le dit (ADR-0073 clause 2)");
            }
            if (!CLE_ENONCE.equals(declaree.premiereCle())) {
                fautes.add(ou + " : sa premiere cle est '" + declaree.premiereCle() + "' et non '"
                        + CLE_ENONCE + "'. Le comptage textuel partage des garde-fous de contenu"
                        + " reconnait un item enumere a sa premiere cle (ADR-0064) : un item illustre"
                        + " qui commence autrement sort du comptage, et les conditions de longueur"
                        + " cessent d'etre executees sur lui sans que rien ne le dise (ADR-0073"
                        + " clause 6)");
            }
            if (declaree.texteAlternatif() == null || declaree.texteAlternatif().isBlank()) {
                fautes.add(ou + " : aucun " + CLE_TEXTE_ALTERNATIF + ". Une image qui porte la"
                        + " question ne parvient pas a un lecteur d'ecran sans lui (ADR-0073"
                        + " clause 5)");
            }
        }

        for (Map.Entry<String, String> illustration : illustrations.entrySet()) {
            boolean declaree = declarees.stream()
                    .anyMatch(candidate -> illustration.getKey().equals(candidate.identifiant()));
            if (!declaree) {
                fautes.add(illustration.getKey() + EXTENSION + " : aucun item d'aucun paquet servi ne"
                        + " le declare. Un dessin que personne ne montre est du poids mort dans le"
                        + " fat jar (ADR-0073 clause 2)");
            }
            if (illustration.getValue().contains("<text") || illustration.getValue().contains("<tspan")) {
                fautes.add(illustration.getKey() + EXTENSION + " : du texte pose dans le dessin"
                        + " echappe a tous les garde-fous qui jugent le texte lu par l'enfant"
                        + " (ADR-0073 clause 4)");
            }
        }

        for (String nomDeDecor : decor.keySet()) {
            boolean confondu = illustrations.containsKey(nomDeDecor);
            if (confondu) {
                fautes.add(nomDeDecor + " : le meme nom de base vit dans le decor et dans les"
                        + " illustrations de contenu. Les deux populations doivent rester"
                        + " distinguables par leur repertoire, sans quoi le critere de chemin"
                        + " litteral d'ADR-0036 - qui porte la condition C5 - ne s'applique plus a"
                        + " rien de sur (ADR-0073 clause 3)");
            }
        }

        if (!fautes.isEmpty()) {
            throw new AssertionError("ADR-0073 : " + fautes);
        }
    }

    private static List<IllustrationDeclaree> declareesDuDepot(Path racine) {
        return illustrationsDeclarees(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
    }

    private static Map<String, String> illustrationsDuDepot(Path racine) {
        return fichiersIndexesParNomDeBase(repertoires(racine, ILLUSTRATIONS_DE_CONTENU));
    }

    private static Map<String, String> decorDuDepot(Path racine) {
        return fichiersIndexesParNomDeBase(repertoires(racine, DECOR));
    }

    @Test
    void chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        verifier(declareesDuDepot(racine), illustrationsDuDepot(racine), decorDuDepot(racine));
    }

    /**
     * La clause de separation, sur le materiau reel, et la seule des cinq dont la population n'est
     * pas vide aujourd'hui : le decor existe, il est servi, et aucun item ne le designe.
     */
    @Test
    void leDecorExisteEtAucunItemNeLeDesigne() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> decor = decorDuDepot(racine);

        assertThat(decor)
                .as("ADR-0051 : sans un seul fichier de decor, la separation des deux populations"
                        + " d'images ne se prouve sur rien")
                .isNotEmpty();

        List<String> designes = declareesDuDepot(racine).stream()
                .map(IllustrationDeclaree::identifiant)
                .filter(decor::containsKey)
                .toList();
        assertThat(designes)
                .as("un item ne designe jamais un fichier du decor : le decor ne depend pas de"
                        + " l'item tire (condition C5, ADR-0036)")
                .isEmpty();
    }

    /**
     * ADR-0051, et le cliquet dont le plafond vaut exactement la taille de la population.
     *
     * <p><b>Le zero est tombe le 2026-08-31, et c'etait le signal voulu.</b> Ce test s'appelait
     * {@code aucunPaquetServiNeDeclareEncoreDIllustration} et exigeait deux populations vides.
     * theo-archi l'avait ecrit pour qu'il rougisse le jour ou le premier paquet illustre entrerait,
     * <i>"c'est ainsi que le lot qui le pose lit ADR-0073 au lieu de le decouvrir apres coup"</i>.
     * Ce jour est le 2026-08-31, et <b>deux</b> lots l'ont fait rougir le meme jour : S25-FIG-1
     * (lea-dev, {@code cp-figures-planes}) et S25-MON-1 (sofia-sm, {@code cp-monnaie}). Le rouge
     * attendu a eu lieu, ADR-0073 a ete lu, et le cliquet est <b>retourne</b> plutot que supprime.
     *
     * <p><b>Pourquoi retourne et non retire</b> - le motif est de lea-dev et il est repris mot pour
     * mot : ADR-0051 demande qu'un garde-fou declare ce qu'il examine. Tant que la population etait
     * vide, le declarer voulait dire epingler le zero ; elle ne l'est plus, et le declarer veut dire
     * epingler qu'elle ne l'est pas. Retirer ce test aurait rendu les quatre clauses de
     * {@link #verifier} muettes le jour ou un paquet illustre sortirait du catalogue ou ou un
     * repertoire d'illustrations disparaitrait : elles passeraient au vert sur une population vide,
     * exactement le silence contre lequel ce test a ete ecrit.
     *
     * <p><b>Resolution de fusion, par sofia-sm, casquette Scrum Master, 2026-08-31.</b> Les deux
     * paires visuelles ont ecrit ce test chacune de son cote : lea-dev exige une population
     * <b>non vide</b>, sofia-sm une <b>taille exacte</b>. C'est l'assertion exacte qui est retenue,
     * et le motif n'est pas l'anciennete : <b>un conflit ne se resout jamais en affaiblissant une
     * assertion</b>, et seule la taille exacte attrape un paquet qui perdrait la moitie de ses
     * illustrations sans en perdre la totalite. Le motif de conservation ci-dessus est celui de
     * lea-dev, l'assertion est celle de sofia-sm : les deux parties survivent par ce qu'elles ont
     * de plus fort.
     *
     * <p><b>Les deux nombres sont mesures par la Scrum Master sur l'arbre fusionne</b>, jamais
     * relayes depuis l'annonce d'un lot : 12 items et 12 dessins par chaine, 24 et 24 apres fusion.
     */
    @Test
    void lesDeuxPopulationsIllustreesValentExactementCeQueLeDepotSert() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();

        assertThat(declareesDuDepot(racine))
                .as("ADR-0051 : le nombre d'items qui declarent une illustration vaut exactement"
                        + " %d. S'il monte, c'est qu'un paquet illustre est entre : relever ce"
                        + " nombre dans le commit qui l'apporte, avec sa valeur mesuree",
                        ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION)
                .hasSize(ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION);
        assertThat(illustrationsDuDepot(racine))
                .as("meme motif, sur l'autre population : %d fichiers sous %s",
                        FICHIERS_D_ILLUSTRATION, ILLUSTRATIONS_DE_CONTENU)
                .hasSize(FICHIERS_D_ILLUSTRATION);
    }

    /** Morsure 1, sur materiau reel mute en memoire : un identifiant qui ne resout aucun fichier. */
    @Test
    void unIdentifiantSansFichierFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IllustrationDeclaree> declarees = new ArrayList<>(declareesDuDepot(racine));
        declarees.add(new IllustrationDeclaree("cp-mutation-en-memoire.yaml", "figure-absente",
                "Une figure geometrique a reconnaitre.", CLE_ENONCE));

        assertThatThrownBy(() -> verifier(declarees, illustrationsDuDepot(racine), decorDuDepot(racine)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-absente")
                .hasMessageContaining("cp-mutation-en-memoire.yaml");
    }

    /** Morsure 2 : un dessin que plus aucun item ne montre, et qui pese quand meme dans le jar. */
    @Test
    void unFichierQueAucunItemNeDeclareFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations = new LinkedHashMap<>(illustrationsDuDepot(racine));
        illustrations.put("figure-oubliee", "<svg xmlns=\"http://www.w3.org/2000/svg\"></svg>");

        assertThatThrownBy(() -> verifier(declareesDuDepot(racine), illustrations, decorDuDepot(racine)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-oubliee");
    }

    /**
     * Morsure 3 : les trois formes qu'un chemin prend quand il s'echappe dans la donnee. Chacune est
     * refusee par l'alphabet, sans qu'aucune inspection de chemin ne soit ecrite nulle part.
     */
    @Test
    void unIdentifiantQuiEstUnCheminOuUneAdresseFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations = illustrationsDuDepot(racine);
        Map<String, String> decor = decorDuDepot(racine);

        for (String echappee : List.of("../images/cabinet-fronton", "https://exemple.org/figure.png",
                "figure-carre.svg")) {
            List<IllustrationDeclaree> declarees = new ArrayList<>(declareesDuDepot(racine));
            declarees.add(new IllustrationDeclaree("cp-mutation-en-memoire.yaml", echappee, "Une figure.", CLE_ENONCE));

            assertThatThrownBy(() -> verifier(declarees, illustrations, decor))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining(echappee)
                    .hasMessageContaining(ALPHABET);
        }
    }

    /** Morsure 4 : une image qui porte la question et qu'aucun lecteur d'ecran n'annonce. */
    @Test
    void uneIllustrationSansTexteAlternatifFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations = new LinkedHashMap<>(illustrationsDuDepot(racine));
        illustrations.put("figure-muette", "<svg xmlns=\"http://www.w3.org/2000/svg\"></svg>");
        List<IllustrationDeclaree> declarees = new ArrayList<>(declareesDuDepot(racine));
        declarees.add(new IllustrationDeclaree("cp-mutation-en-memoire.yaml", "figure-muette", null, CLE_ENONCE));

        assertThatThrownBy(() -> verifier(declarees, illustrations, decorDuDepot(racine)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(CLE_TEXTE_ALTERNATIF);
    }

    /** Morsure 5 : du texte lu par l'enfant, pose la ou aucun garde-fou de texte ne regarde. */
    @Test
    void duTexteDansUnDessinFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations = new LinkedHashMap<>(illustrationsDuDepot(racine));
        illustrations.put("figure-legendee",
                "<svg xmlns=\"http://www.w3.org/2000/svg\"><text x=\"0\" y=\"0\">carre</text></svg>");
        List<IllustrationDeclaree> declarees = new ArrayList<>(declareesDuDepot(racine));
        declarees.add(new IllustrationDeclaree("cp-mutation-en-memoire.yaml", "figure-legendee",
                "Une figure geometrique.", CLE_ENONCE));

        assertThatThrownBy(() -> verifier(declarees, illustrations, decorDuDepot(racine)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-legendee");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>, et elle n'est pas une
     * redite des cinq precedentes : celles-la mutent la sortie des recensements, celle-ci les
     * traverse. C'est le seul test du fichier qui prouve que {@code illustrationsDeclarees} sait
     * lire une declaration dans un YAML et que {@code fichiersIndexesParNomDeBase} sait lire un
     * dessin sur disque. Un juge pur mordu sur des donnees fabriquees a la main resterait vert le
     * jour ou le recensement lirait mal.
     *
     * <p>Le paquet fautif porte cinq fautes distinctes, une par clause : un identifiant qui ne
     * resout pas, une illustration sans texte alternatif, un dessin que personne ne declare, du
     * texte dans un dessin, et un item qui ne commence pas par son enonce. <b>Cette derniere ne
     * peut etre prouvee que d'ici</b> : elle depend de l'ordre des cles tel que SnakeYAML le rend,
     * et aucune mutation en memoire ne l'exerce.</p>
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesCinqFautes() {
        Path jeuFautif = CONTRE_EXEMPLE.resolve("paquet-illustre-fautif");
        List<IllustrationDeclaree> declarees =
                illustrationsDeclarees(List.of(jeuFautif.resolve("contenu")));
        Map<String, String> illustrations = fichiersIndexesParNomDeBase(
                List.of(jeuFautif.resolve("static/illustrations")));

        assertThat(declarees)
                .as("ADR-0051 : ce jeu fautif ne prouve rien si le recensement n'y lit aucune"
                        + " declaration d'illustration")
                .hasSize(3);

        assertThatThrownBy(() -> verifier(declarees, illustrations,
                decorDuDepot(LesGardeFousSontMisALEpreuveTest.racineDuDepot())))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-absente")
                .hasMessageContaining(CLE_TEXTE_ALTERNATIF)
                .hasMessageContaining("figure-legendee")
                .hasMessageContaining("figure-triangle");
    }

    /**
     * Morsure 7, clause 6, et elle vient d'une lecture de <b>nadia-prof</b> que ma mesure ne donnait
     * pas. J'avais mesure qu'une <b>forme d'item</b> dont le premier champ n'est pas {@code enonce}
     * sort du comptage textuel partage - bruyamment, le paquet entier tombant a zero item. Elle en a
     * tire le cas <b>partiel</b>, qui est le dangereux : un paquet dont <b>certains</b> items
     * commencent par {@code illustration} est compte en partie, les conditions de longueur cessent
     * d'etre executees sur les items manquants, et deux des huit garde-fous qui partagent ce
     * comptage ne portent pas la clause {@code hasSize(declares)} qui le rattraperait (routage 2 du
     * rapport qualite de s24). C'est la forme de {@code CPL-3} : une condition ecrite que rien
     * n'executait.
     */
    @Test
    void unItemIllustreQuiNeCommencePasParSonEnonceFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations = new LinkedHashMap<>(illustrationsDuDepot(racine));
        illustrations.put("figure-mal-ordonnee", "<svg xmlns=\"http://www.w3.org/2000/svg\"></svg>");
        List<IllustrationDeclaree> declarees = new ArrayList<>(declareesDuDepot(racine));
        declarees.add(new IllustrationDeclaree("cp-mutation-en-memoire.yaml", "figure-mal-ordonnee",
                "Une figure geometrique.", CLE_ILLUSTRATION));

        assertThatThrownBy(() -> verifier(declarees, illustrations, decorDuDepot(racine)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-mal-ordonnee")
                .hasMessageContaining(CLE_ENONCE);
    }

    /** Morsure 6 : la clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unPerimetreDeDecorVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of(), Map.of(), Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * S39-SOLIDES (theo-archi, avis de faisabilite, section 12) : sur les quinze dessins reellement
     * servis par {@code ce1-solides}, aucun ne trace une seule forme.
     */
    @Test
    void chaqueDessinQuiSertUnSolideTraceUneProjectionEtNonUneFigurePlane() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IllustrationDeclareeParLigne> declarees = illustrationsDeclareesParLesPaquetsQuiServent(
                LIGNE_DES_SOLIDES,
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
        Map<String, String> dessins = fichiersIndexesParNomDeBase(repertoires(racine, ILLUSTRATIONS_DE_CONTENU));

        assertThat(dessinsQuiNeTracentPasDeSolide(declarees, dessins)).isEmpty();
    }

    /**
     * Non-vacuite (ADR-0051) : le recensement atteint bien les quinze items de {@code ce1-solides}.
     * Sans cette assertion, la morsure ci-dessus pourrait etre verte sur une population que le
     * recensement n'a jamais trouvee.
     */
    @Test
    void leRecensementTrouveLesQuinzeDessinsDeLaLigneDesSolides() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(illustrationsDeclareesParLesPaquetsQuiServent(LIGNE_DES_SOLIDES,
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine)))
                .hasSize(15);
    }

    /**
     * Mise a l'epreuve sur du materiau reel mute en memoire, pas sur une fixture : un des quinze
     * dessins servis est remplace par un cercle nu, et le garde-fou doit le nommer. Verifie que la
     * clause rougit reellement avant de la considerer faite (routage de sofia-sm).
     */
    @Test
    void unDessinDeSolideMuteEnCercleNuFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IllustrationDeclareeParLigne> declarees = illustrationsDeclareesParLesPaquetsQuiServent(
                LIGNE_DES_SOLIDES,
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
        Map<String, String> dessins = new LinkedHashMap<>(
                fichiersIndexesParNomDeBase(repertoires(racine, ILLUSTRATIONS_DE_CONTENU)));
        String identifiant = declarees.get(0).identifiant();
        dessins.put(identifiant,
                "<svg xmlns=\"http://www.w3.org/2000/svg\"><circle cx=\"50\" cy=\"50\" r=\"40\""
                        + " fill=\"none\" stroke=\"#1c2733\" stroke-width=\"6\"/></svg>");

        assertThat(dessinsQuiNeTracentPasDeSolide(declarees, dessins))
                .as("un cercle nu ne trace qu'une seule forme rendue, la clause doit le nommer")
                .anyMatch(faute -> faute.contains(identifiant));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif commite, sur disque, pas en memoire. Traverse la lecture
     * disque et le balayage du repertoire de contenu - preuve que le recensement lit
     * {@code lignesDeReferentiel} et resout les dessins d'un jeu qui n'est pas celui de production.
     */
    @Test
    void leJeuFautifDeLaLigneDesSolidesEstRefuse() {
        Path jeuFautif = CONTRE_EXEMPLE.resolve("paquet-solides-fautif");
        List<IllustrationDeclareeParLigne> declarees = illustrationsDeclareesParLesPaquetsQuiServent(
                LIGNE_DES_SOLIDES, List.of(jeuFautif.resolve("contenu")));
        Map<String, String> dessins = fichiersIndexesParNomDeBase(
                List.of(jeuFautif.resolve("static/illustrations")));

        assertThat(declarees)
                .as("ADR-0051 : ce jeu fautif ne prouve rien si le recensement n'y lit aucune"
                        + " declaration sur la ligne des solides")
                .hasSize(1);

        assertThat(dessinsQuiNeTracentPasDeSolide(declarees, dessins))
                .anyMatch(faute -> faute.contains("boule-fautive"));
    }
}
