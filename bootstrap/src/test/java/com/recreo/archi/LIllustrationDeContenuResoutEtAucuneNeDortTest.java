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
 * <p><b>Ce que ce garde-fou examine aujourd'hui, honnetement (ADR-0051).</b> Aucun paquet servi ne
 * declare d'illustration au 2026-08-31, et aucun fichier ne vit sous {@code static/illustrations/} :
 * les quatre premieres clauses portent sur une population <b>vide</b>. Elles ne sont pas decoratives
 * pour autant, et pour deux raisons verifiables. D'abord elles mordent, ici, sur du materiau reel
 * mute en memoire - rien n'est ecrit sur disque. Ensuite
 * {@link #aucunPaquetServiNeDeclareEncoreDIllustration()} <b>epingle le zero</b> : le jour ou un
 * paquet declare une illustration, ce test-la rougit, et celui qui pose ce paquet passe par ici
 * plutot que de decouvrir six mois plus tard qu'il n'etait garde par rien. Le plafond vaut
 * exactement la taille de la population.
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

    /** La cle par laquelle le meme item declare ce qu'une lecture d'ecran annonce (clause 5). */
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
     * l'entree du premier paquet illustre (S25-MON-1, {@code cp-monnaie} : douze items, douze
     * dessins). Ces deux nombres ne sont pas des plafonds de confort : ils valent exactement la
     * population, et un lot qui l'agrandit les releve dans son propre commit.
     */
    private static final int ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION = 12;

    private static final int FICHIERS_D_ILLUSTRATION = 12;

    /**
     * ADR-0036 clause 5 : le cliquet de poids d'une illustration, en octets, fins de ligne
     * normalisees en LF.
     *
     * <p><b>Ce cliquet est un doublon assume, et il est nomme comme tel.</b> {@code lea-dev} a pose
     * le meme le 2026-08-31, quelques heures plus tot, dans
     * {@code ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest}
     * (adapter-web), qu'elle a explicitement nomme <i>foyer commun des deux chaines visuelles</i>.
     * <b>Sa version est celle qui survit a la fusion</b> - elle cliquete en plus le poids total du
     * repertoire, que celle-ci ne regarde pas - et ce test-ci part avec elle. Il existe pour que la
     * dette d'ADR-0036 clause 5 soit payee sur cette branche prise seule, quel que soit l'ordre de
     * fusion des deux paires ; il n'existe pas pour vivre a cote du sien.
     *
     * <p><b>Critere 11 du planning de s25, execute : je deplace, avec motif ecrit et valeur
     * mesuree.</b> Son cliquet vaut 1 112 octets pour un dessin et 6 206 pour le repertoire, mesures
     * sur ses douze figures planes. Mes douze dessins de monnaie ne les tiennent pas, et le motif
     * n'est pas une negligence de trace : une figure plane est <b>un</b> trace - un {@code rect},
     * un {@code circle}, un {@code path} - la ou une scene de monnaie porte jusqu'a six objets, dont
     * chacun demande un contour, un contour interieur et une a deux instances de glyphe. Le trace
     * est plus gros par construction, pas par relachement.
     *
     * <p><b>Les valeurs mesurees, sur les fins de ligne normalisees, methode d'ines-design</b> :
     * mes douze pesent 22 912 octets, le plus lourd {@code monnaie-meme-somme-e.svg} a
     * <b>2 685</b>, le plus leger 1 445. Sur l'arbre fusionne des deux paires : <b>24 fichiers,
     * 29 118 octets, maximum 2 685</b>. Ce sont les deux nombres que son garde-fou prend.
     *
     * <p><b>Ce que ce cliquet mesure, et ines-design demande que ce soit dit</b> : les octets
     * <b>commites</b>, en-tete de commentaire compris, pas les octets de trace. Ses fichiers pesent
     * 275 octets de trace pour 1 084 commites, et <i>"un seuil pose sur l'un ne veut rien dire sur
     * l'autre"</i>. Le choix des octets commites est celui de ce que l'enfant telecharge.
     */
    private static final int PLAFOND_DE_POIDS_EN_OCTETS = 2685;

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
                        + " question ne parvient pas a une lecture d'ecran sans lui (ADR-0073"
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
     * {@code aucunPaquetServiNeDeclareEncoreDIllustration} et exigeait deux populations vides ; il
     * a rougi quand le premier paquet illustre est entre - S25-MON-1, {@code cp-monnaie}, douze
     * items et douze dessins - exactement comme ADR-0073 l'avait ecrit : "le lot qui pose ce paquet
     * lit cet ADR au lieu de le decouvrir six mois plus tard". Il est leve ici par le lot qui l'a
     * fait rougir, et non contourne : la propriete ne disparait pas, elle change de valeur. Le
     * plafond vaut toujours <b>exactement</b> la taille de la population, jamais un chiffre rond ni
     * une marge.
     *
     * <p><b>Ce que le second paquet illustre doit faire, et il est ecrit ici avant qu'il arrive</b>
     * (critere 11 du planning de s25) : ces deux nombres montent d'autant d'items et de fichiers
     * qu'il en apporte, <b>dans le commit qui les apporte</b>, avec sa valeur mesuree. Un cliquet
     * releve sans motif ne cliquete plus rien. La chaine FIG de lea-dev est la seconde ; c'est le
     * seul endroit du depot ou les deux paires se croisent, et il est nomme pour que le conflit se
     * resolve sur un nombre plutot qu'a la fusion.
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

    /**
     * ADR-0036 clause 5, dette explicite ouverte depuis s10 et due au lot qui livre la premiere
     * image illustree : <i>"le commit qui livre la premiere image illustree pose le cliquet de
     * poids, sur la valeur qu'il installe. Tant que ce cliquet n'existe pas, rien dans ce depot
     * n'empeche une illustration de 4 Mo."</i> Le voici, et il est pose par ce commit-la.
     *
     * <p><b>Le plafond porte sur une illustration, pas sur leur somme, et le motif n'est pas un
     * gout.</b> ADR-0036 nomme le risque au singulier - une image de 4 Mo - et refuse par ecrit un
     * nombre absolu comme un plafond relatif. Un plafond sur la somme monterait mecaniquement a
     * chaque paquet sans rien dire du risque qu'il garde, et mettrait deux lots en conflit sur un
     * nombre qui ne signifie rien. La cible de poids <b>total</b> servi a un enfant reste ce
     * qu'ADR-0036 en dit : une cible produit et pedagogique, qui appartient a sofia-sm et a
     * nadia-prof, et qu'aucun developpeur ne fixe.
     *
     * <p><b>La valeur est mesuree, jamais inventee</b> : c'est la taille du plus lourd des douze
     * dessins que ce lot installe, {@code monnaie-meme-somme-e.svg}. Le mecanisme est celui que le
     * planning de s25 fixe - un cliquet se pose sur une valeur mesuree - et le nombre est celui que
     * le lot installe, pas un objectif.
     */
    @Test
    void aucuneIllustrationDeContenuNeDepasseLePoidsQueCeLotInstalle() {
        verifierLePoids(illustrationsDuDepot(LesGardeFousSontMisALEpreuveTest.racineDuDepot()));
    }

    /** Morsure du cliquet de poids, sur materiau reel mute en memoire. */
    @Test
    void uneIllustrationPlusLourdeQueLePlafondFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> illustrations =
                new LinkedHashMap<>(illustrationsDuDepot(racine));
        illustrations.put("mutation-en-memoire", "x".repeat(PLAFOND_DE_POIDS_EN_OCTETS + 1));

        assertThatThrownBy(() -> verifierLePoids(illustrations))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0036 clause 5")
                .hasMessageContaining("mutation-en-memoire");
    }

    /**
     * Le juge du poids, pur : il recoit le contenu des fichiers et n'ouvre rien. Le poids est celui
     * des octets UTF-8 du fichier servi, c'est-a-dire ce que le navigateur telecharge, jamais un
     * nombre de caracteres.
     */
    static void verifierLePoids(Map<String, String> illustrations) {
        List<String> trop = new ArrayList<>();
        for (Map.Entry<String, String> illustration : illustrations.entrySet()) {
            // Fins de ligne normalisees avant de compter : le meme fichier extrait avec des fins de
            // ligne Windows pese une ligne de plus par ligne, et le cliquet dirait alors deux
            // nombres differents pour un contenu identique. C'est la methode de lea-dev, reprise
            // telle quelle pour que les deux chaines visuelles comptent la meme chose.
            int octets = illustration.getValue().replace("\r\n", "\n")
                    .getBytes(StandardCharsets.UTF_8).length;
            if (octets > PLAFOND_DE_POIDS_EN_OCTETS) {
                trop.add(illustration.getKey() + EXTENSION + " pese " + octets + " octets");
            }
        }
        if (!trop.isEmpty()) {
            throw new AssertionError("ADR-0036 clause 5 : une illustration de contenu depasse le"
                    + " cliquet de poids de " + PLAFOND_DE_POIDS_EN_OCTETS + " octets, pose sur la"
                    + " valeur mesuree du lot qui a livre la premiere image illustree. Un lot qui"
                    + " l'alourdit deplace ce plafond dans son propre commit, avec sa valeur"
                    + " mesuree et son motif ecrit : " + trop);
        }
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

    /** Morsure 4 : une image qui porte la question et qu'aucune lecture d'ecran n'annonce. */
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
}
