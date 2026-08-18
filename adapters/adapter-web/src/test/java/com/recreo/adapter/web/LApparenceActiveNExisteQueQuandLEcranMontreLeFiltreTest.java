package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.sujetDuSelecteur;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S15 (exigences de theo-archi,
 * squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-etat-actif-au-retour.md) : la puce
 * de niveau porte une classe d'etat active, posee par le serveur en {@code th:class}, jamais par
 * une regle CSS par niveau. Deux clauses, aucune troisieme :
 *
 * <ul>
 *   <li><b>Clause E</b> ({@link #verifierQueLaClasseActiveNestDeclareeQueSousLaConditionDuFiltre}) :
 *       aucune regle maison ne declare une classe qu'un point d'entree peut porter
 *       <b>conditionnellement</b> en dehors d'un selecteur conditionne par la marque du filtre
 *       ({@code .filtre-niveau}). <b>Reforme le 2026-08-18</b> : la premiere forme de cette clause
 *       exigeait seulement qu'une regle <i>neutralise</i> l'apparence active sur le marqueur
 *       d'ecran de jeu quelque part - elle laissait donc passer une declaration inconditionnelle
 *       posee <i>a cote</i> de la neutralisation, exactement le defaut que l'utilisatrice a trouve
 *       (CM1 presse puis "Les jeux" : la liste montre les onze jeux et CM1 garde sa couleur, parce
 *       que la route qui rend ce catalogue complet ne porte pas le marqueur d'ecran de jeu et
 *       qu'une regle inconditionnelle restait allumee). La forme reformee est plus stricte : elle
 *       interdit toute declaration hors condition, quelle que soit la route ou l'ecran qui manquerait
 *       a une enumeration ;</li>
 *   <li><b>Clause F</b> ({@link #verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur}) : la regle
 *       maison de chaque classe active declare au moins une composante qui n'est pas une couleur.
 *       Inchangee par la reforme - elle lit la regle de l'apparence active, qui existe toujours,
 *       seulement conditionnee desormais. Elle ne juge pas laquelle, et elle ne peut pas juger si un
 *       enfant la percoit - ce verdict reste a {@code nadia-prof}, sur capture (ADR-0025).</li>
 * </ul>
 *
 * <p><b>Pourquoi une condition plutot qu'une neutralisation, et ce n'est pas une preference de
 * style.</b> Une neutralisation doit enumerer ce qu'elle defait - "cesse quand le jeu demarre",
 * "cesse au catalogue complet" - et un perimetre enumere echoue par omission : c'est exactement le
 * defaut trouve par l'utilisatrice, la seconde instance d'une propriete dont la premiere forme de
 * cette clause n'en gardait qu'une. Une application conditionnelle n'a rien a enumerer : ce qui
 * n'est pas applique n'a pas besoin d'etre defait, et une route ou un ecran de jeu futur qui rendrait
 * le catalogue complet sans filtre est couvert sans que personne y pense.</p>
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les classes conditionnelles viennent des litteraux de
 *       {@code th:class}/{@code th:classappend} portes par un element {@code data-role="entree"},
 *       jamais d'un nom ecrit ici ({@link #classesConditionnellesDesPointsDEntree}) ; la marque du
 *       filtre, elle, est nommee en clair - c'est le seul choix arrete par le mecanisme retenu
 *       (section 1 de l'exigence), pas une derivation ;</li>
 *   <li><b>derivation totale</b> : aucune classe conditionnelle relevee est un echec pour les deux
 *       clauses, jamais un cas ignore (ADR-0003) ;</li>
 *   <li><b>mise a l'epreuve</b> : sur une copie mutee de la production (ADR-0015) pour les deux
 *       clauses, et sur un objet neuf - un point d'entree que personne n'a inscrit nulle part -
 *       pour la clause E.</li>
 * </ul>
 */
class LApparenceActiveNExisteQueQuandLEcranMontreLeFiltreTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/LApparenceActiveNExisteQueQuandLEcranMontreLeFiltreTest");

    private static final String ROLE_ENTREE = "entree";
    private static final String SELECTEUR_ENTREE = "[data-role=" + ROLE_ENTREE + "]";

    /**
     * La marque du filtre (theo-archi, exigences-theo-etat-actif-au-retour.md, section 1) : nommee
     * en clair parce que c'est le seul choix arrete par le mecanisme retenu, pas une derivation. Elle
     * est portee par l'element de la phrase "Pour le CP" lui-meme, jamais par un ancetre (reserve H
     * d'ADR-0035) - c'est ce qui rend le couplage sain : la marque ne peut pas survivre a la
     * disparition du texte qui la porte.
     */
    private static final String MARQUEUR_FILTRE = "filtre-niveau";

    private static final List<String> PORTEURS_DE_CLASSE = List.of("th:class", "th:classappend");

    /**
     * Les seules composantes que la clause F traite comme "une couleur" - exactement le vocabulaire
     * deja partage par ADR-0046 (ses trois proprietes de dette : color, background-color, border).
     */
    private static final Set<String> PROPRIETES_DE_COULEUR =
            Set.of("color", "background-color", "border-color", "outline-color");

    /** Les litteraux entre apostrophes d'une expression Thymeleaf. */
    private static List<String> litteraux(String expression) {
        List<String> valeurs = new ArrayList<>();
        int position = 0;
        while ((position = expression.indexOf('\'', position)) >= 0) {
            int fin = expression.indexOf('\'', position + 1);
            if (fin < 0) {
                break;
            }
            valeurs.add(expression.substring(position + 1, fin).strip());
            position = fin + 1;
        }
        return valeurs;
    }

    private static List<String> motsDe(String litteral) {
        List<String> mots = new ArrayList<>();
        for (String mot : litteral.split("\\s+")) {
            if (!mot.isBlank()) {
                mots.add(mot);
            }
        }
        return mots;
    }

    /**
     * Les classes qu'un point d'entree peut porter conditionnellement, lues dans les litteraux de
     * {@code th:class}/{@code th:classappend} - jamais ecrites ici. Un point d'entree sans aucune
     * classe conditionnelle ne contribue rien : il n'a rien a conditionner.
     */
    static Set<String> classesConditionnellesDesPointsDEntree(Path racineDesGabarits) {
        Set<String> classes = new LinkedHashSet<>();
        for (Path gabarit : gabarits(racineDesGabarits)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.select(SELECTEUR_ENTREE)) {
                for (String porteur : PORTEURS_DE_CLASSE) {
                    if (!element.hasAttr(porteur)) {
                        continue;
                    }
                    for (String litteral : litteraux(element.attr(porteur))) {
                        classes.addAll(motsDe(litteral));
                    }
                }
            }
        }
        return classes;
    }

    /**
     * Les classes que porte le <b>sujet</b> d'un selecteur - son dernier compose, celui sur lequel
     * la regle s'applique reellement - en ignorant tout ce qu'un {@code :has(...)} ancetre peut
     * nommer avant lui. C'est ce qui distingue une classe que l'element <b>porte</b> d'une classe
     * que l'element exige seulement d'un ancetre pour s'appliquer.
     */
    private static Set<String> classesDuSujet(String selecteur) {
        return LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur(sujetDuSelecteur(selecteur));
    }

    /**
     * La regle maison au repos de cette classe : celle dont le sujet se termine par cette classe,
     * sans pseudo-classe ajoutee apres elle. Cherchee sur le <b>sujet</b> et non sur le selecteur
     * entier, pour ne pas confondre un survol/appui (qui allonge le sujet, {@code .est-actif:hover})
     * avec la condition ancetre {@code :has(...)} qu'un selecteur peut porter desormais - celle-ci ne
     * fait plus partie du sujet et ne doit donc jamais empecher ce filtre de reconnaitre le repos.
     */
    private static Optional<Map.Entry<String, String>> reposDeLaClasse(Map<String, String> maison,
                                                                       String classe) {
        String suffixe = "." + classe;
        return maison.entrySet().stream()
                .filter(entree -> sujetDuSelecteur(entree.getKey()).endsWith(suffixe))
                .findFirst();
    }

    /**
     * Clause E : aucune regle maison ne declare une classe conditionnelle d'un point d'entree en
     * dehors d'un selecteur conditionne par la marque du filtre.
     */
    static void verifierQueLaClasseActiveNestDeclareeQueSousLaConditionDuFiltre(Path racineDesGabarits,
                                                                                Path racineDesFeuilles) {
        Set<String> classes = classesConditionnellesDesPointsDEntree(racineDesGabarits);
        assertThat(classes)
                .as("ADR-0003 : cette clause ne prouve rien si aucun point d'entree ne porte de "
                        + "classe conditionnelle sous %s", racineDesGabarits)
                .isNotEmpty();

        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        List<String> defauts = new ArrayList<>();
        for (String classe : classes) {
            for (String selecteur : maison.keySet()) {
                if (!classesDuSujet(selecteur).contains(classe)) {
                    continue;
                }
                if (!selecteur.contains("." + MARQUEUR_FILTRE)) {
                    defauts.add("le selecteur \"" + selecteur + "\" declare la classe conditionnelle "
                            + "\"" + classe + "\" hors de toute condition sur \"." + MARQUEUR_FILTRE
                            + "\" : son apparence active existerait meme quand l'ecran ne montre pas "
                            + "le filtre");
                }
            }
        }
        assertThat(defauts)
                .as("aucune regle maison ne doit declarer une classe conditionnelle d'un point "
                        + "d'entree hors d'un selecteur conditionne par la marque du filtre")
                .isEmpty();
    }

    /**
     * Clause F : la regle maison de chaque classe active declare au moins une composante qui n'est
     * pas une couleur.
     */
    static void verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur(Path racineDesGabarits,
                                                                      Path racineDesFeuilles) {
        Set<String> classes = classesConditionnellesDesPointsDEntree(racineDesGabarits);
        assertThat(classes)
                .as("ADR-0003 : cette clause ne prouve rien si aucun point d'entree ne porte de "
                        + "classe conditionnelle sous %s", racineDesGabarits)
                .isNotEmpty();

        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        List<String> defauts = new ArrayList<>();
        for (String classe : classes) {
            Optional<Map.Entry<String, String>> repos = reposDeLaClasse(maison, classe);
            if (repos.isEmpty()) {
                defauts.add("la classe \"" + classe + "\" n'a aucune regle maison a son repos "
                        + "(un selecteur dont le sujet se termine par \"." + classe
                        + "\", sans pseudo-classe)");
                continue;
            }
            List<String> proprietes =
                    LAnimationRetardeMaisNeDetermineRienTest.proprietesDeclarees(repos.get().getValue());
            boolean auMoinsUneNonChromatique = proprietes.stream()
                    .anyMatch(propriete -> !PROPRIETES_DE_COULEUR.contains(propriete));
            if (!auMoinsUneNonChromatique) {
                defauts.add("la regle \"" + repos.get().getKey() + "\" de la classe \"" + classe
                        + "\" ne declare que des composantes de couleur (" + proprietes + ") : un "
                        + "enfant qui ne distingue pas les couleurs n'y verrait rien changer");
            }
        }
        assertThat(defauts)
                .as("la regle de chaque classe active doit declarer au moins une composante qui "
                        + "n'est pas une couleur")
                .isEmpty();
    }

    /**
     * Copie recursive d'un arbre reel vers une destination, pour muter une copie plutot que le
     * depot (ADR-0015 : materiau reel, jamais une fixture ecrite a la main).
     */
    private static void copierArbreReel(Path source, Path destination) throws IOException {
        try (Stream<Path> arborescence = Files.walk(source)) {
            for (Path chemin : (Iterable<Path>) arborescence::iterator) {
                Path cible = destination.resolve(source.relativize(chemin).toString());
                if (Files.isDirectory(chemin)) {
                    Files.createDirectories(cible);
                } else {
                    Files.createDirectories(cible.getParent());
                    Files.copy(chemin, cible);
                }
            }
        }
    }

    @Test
    void laClasseActiveNestDeclareeQueSousLaConditionDuFiltreEnProduction() {
        verifierQueLaClasseActiveNestDeclareeQueSousLaConditionDuFiltre(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    @Test
    void laClasseActiveNeReposePasSurLaSeuleCouleurEnProduction() {
        verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3, clause E : mise a l'epreuve sur un objet neuf - un point d'entree que
     * personne n'a inscrit nulle part, dont la classe conditionnelle "etat-neuf" est declaree par
     * une regle maison hors de toute condition. Distinct de la preuve sur materiau reel ci-dessous :
     * celle-ci montre que la clause voit un point d'entree qu'elle n'a jamais rencontre, pas
     * seulement une regression sur celui qui existe deja. Le jeu fautif est celui d'hier, valable
     * tel quel (theo-archi) : une declaration inconditionnelle etait deja la faute qu'il nommait.
     */
    @Test
    void unPointDEntreeNeufSansConditionFaitEchouerLaClauseE() {
        Path racine = CONTRE_EXEMPLES.resolve("point-d-entree-sans-condition");
        assertThatThrownBy(() -> verifierQueLaClasseActiveNestDeclareeQueSousLaConditionDuFiltre(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("hors de toute condition");
    }

    /**
     * ADR-0013 obligation 3, clause E : preuve de morsure sur du materiau reel - une copie de la
     * production dont la regle de l'apparence active perd sa condition sur la marque du filtre, sans
     * perdre sa declaration. C'est exactement la defection que l'utilisatrice a trouvee : l'etat
     * actif survivrait au retour au catalogue complet, silencieusement.
     */
    @Test
    void uneConditionRetireeFaitEchouerLaClauseE(@TempDir Path tempDir) throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path feuille = tempDir.resolve("static/css/recreo.css");
        // Comparaison sur les fins de ligne normalisees (le fichier source est en CRLF) : seule
        // l'ecriture qui suit compte pour la mutation, la lecture qui prouve le rouge ne compare
        // que des blocs CSS, jamais un octet de fin de ligne.
        String contenu = Files.readString(feuille).replace("\r\n", "\n");
        // Retire la condition ancetre des trois selecteurs de la regle, sans toucher a ce qu'ils
        // declarent : String.replace remplace toutes les occurrences, donc les trois lignes
        // "body:has(.filtre-niveau) .chip-niveau.est-actif[...]" perdent leur prefixe d'un seul
        // geste, exactement la mutation que la clause E doit desormais attraper.
        String mute = contenu.replace("body:has(." + MARQUEUR_FILTRE + ") ", "");
        assertThat(mute).as("la regle doit porter la condition avant mutation").isNotEqualTo(contenu);
        Files.writeString(feuille, mute);

        assertThatThrownBy(() -> verifierQueLaClasseActiveNestDeclareeQueSousLaConditionDuFiltre(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("hors de toute condition");
    }

    /**
     * ADR-0013 obligation 3, clause F : preuve de morsure sur du materiau reel - une copie de la
     * production dont la regle de repos de {@code .est-actif} perd son contour, ne declarant plus
     * que des couleurs. Un enfant qui ne les distingue pas ne verrait plus rien changer.
     */
    @Test
    void uneRegleActiveReduiteALaCouleurFaitEchouerLaClauseF(@TempDir Path tempDir) throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path feuille = tempDir.resolve("static/css/recreo.css");
        String contenu = Files.readString(feuille).replace("\r\n", "\n");
        String mute = contenu.replace(
                "    background-color: var(--recreo-accent-texte);\n"
                        + "    color: var(--recreo-entete-fond);\n"
                        + "    border: 0.125rem solid var(--recreo-entete-fond);\n"
                        + "}",
                "    background-color: var(--recreo-accent-texte);\n"
                        + "    color: var(--recreo-entete-fond);\n"
                        + "}");
        assertThat(mute).as("la regle active doit porter un contour avant mutation")
                .isNotEqualTo(contenu);
        Files.writeString(feuille, mute);

        assertThatThrownBy(() -> verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare que des composantes de couleur");
    }
}
