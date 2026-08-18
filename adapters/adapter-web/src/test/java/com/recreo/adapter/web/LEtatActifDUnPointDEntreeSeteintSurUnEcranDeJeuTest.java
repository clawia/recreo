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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S15 (exigences de theo-archi,
 * squad/sprints/s15-la-sortie-vit-dans-l-en-tete/exigences-theo-etat-actif.md, section 2) : la
 * puce de niveau porte une classe d'etat active, posee par le serveur en {@code th:class}, jamais
 * par une regle CSS par niveau. Deux clauses, aucune troisieme :
 *
 * <ul>
 *   <li><b>Clause E</b> ({@link #verifierQueLEtatActifEstNeutraliseSurUnEcranDeJeu}) : toute classe
 *       qu'un point d'entree peut porter <b>conditionnellement</b> est nommee par une regle maison
 *       qui la neutralise sur le marqueur d'ecran de jeu. Ce qu'elle interdit reellement : que la
 *       regle de neutralisation soit retiree ou renommee pendant que la classe reste posee - l'etat
 *       actif persisterait alors pendant une partie, silencieusement. Le sens inverse - la classe
 *       renommee, la regle laissee - est deja ferme par {@code UnEcranNeGouvernePasLApparenceDUnAutreTest}
 *       (ADR-0031), qui refuse un jeton qu'aucun ecran ne peut poser ;</li>
 *   <li><b>Clause F</b> ({@link #verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur}) : la regle
 *       maison de chaque classe active declare au moins une composante qui n'est pas une couleur.
 *       Elle ne juge pas laquelle, et elle ne peut pas juger si un enfant la percoit - ce verdict
 *       reste a {@code nadia-prof}, sur capture (ADR-0025).</li>
 * </ul>
 *
 * <p><b>Ce que la neutralisation n'est pas, et pourquoi ce fichier ne lit jamais
 * {@code LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest#masquages}.</b> La regle qui neutralise une
 * apparence active ne prend jamais la forme contractuelle du masquage d'ADR-0022
 * ({@code display: none}) : elle change des couleurs et un contour, jamais une visibilite. Trois
 * garde-fous lisent {@code masquages()} en y cherchant "ce qui est invisible" -
 * {@code AucunArretDeTabulationNEstDestructeurTest}, {@code LActionNeDeclarePasMoinsQueLaSortieTest},
 * la clause D de {@code LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest} - et y faire entrer une
 * neutralisation d'apparence leur ferait calculer un document faux en restant verts. Ce fichier
 * derive donc sa propre population, independante de {@code masquages()}.</p>
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les classes conditionnelles viennent des litteraux de
 *       {@code th:class}/{@code th:classappend} portes par un element {@code data-role="entree"},
 *       jamais d'un nom ecrit ici ({@link #classesConditionnellesDesPointsDEntree}) ; le marqueur
 *       d'ecran de jeu, lui, est nomme en clair - c'est le seul choix arrete par le mecanisme retenu
 *       (section 1.2 de l'exigence), pas une derivation ;</li>
 *   <li><b>derivation totale</b> : aucune classe conditionnelle relevee est un echec pour les deux
 *       clauses, jamais un cas ignore (ADR-0003) ;</li>
 *   <li><b>mise a l'epreuve</b> : sur une copie mutee de la production (ADR-0015) pour les deux
 *       clauses, et sur un objet neuf - un point d'entree que personne n'a inscrit nulle part -
 *       pour la clause E.</li>
 * </ul>
 */
class LEtatActifDUnPointDEntreeSeteintSurUnEcranDeJeuTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/LEtatActifDUnPointDEntreeSeteintSurUnEcranDeJeuTest");

    private static final String ROLE_ENTREE = "entree";
    private static final String SELECTEUR_ENTREE = "[data-role=" + ROLE_ENTREE + "]";

    /**
     * Le marqueur d'ecran de jeu (ADR-0022) : nomme en clair parce que c'est la seule chose que le
     * mecanisme de neutralisation retenu (exigences-theo-etat-actif.md, section 1.2) ne derive pas -
     * il est choisi, pas lu dans une source qui varierait.
     */
    private static final String MARQUEUR_ECRAN_DE_JEU = "ecran-de-jeu";

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
     * classe conditionnelle ne contribue rien : il n'a rien a neutraliser.
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

    /** La regle maison au repos de cette classe : un selecteur qui s'y termine, sans pseudo-classe. */
    private static Optional<Map.Entry<String, String>> reposDeLaClasse(Map<String, String> maison,
                                                                       String classe) {
        String suffixe = "." + classe;
        return maison.entrySet().stream()
                .filter(entree -> entree.getKey().endsWith(suffixe) && !entree.getKey().contains(":"))
                .findFirst();
    }

    /**
     * Clause E : toute classe conditionnelle d'un point d'entree est neutralisee par une regle
     * maison sur le marqueur d'ecran de jeu.
     */
    static void verifierQueLEtatActifEstNeutraliseSurUnEcranDeJeu(Path racineDesGabarits,
                                                                  Path racineDesFeuilles) {
        Set<String> classes = classesConditionnellesDesPointsDEntree(racineDesGabarits);
        assertThat(classes)
                .as("ADR-0003 : cette clause ne prouve rien si aucun point d'entree ne porte de "
                        + "classe conditionnelle sous %s", racineDesGabarits)
                .isNotEmpty();

        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        List<String> defauts = new ArrayList<>();
        for (String classe : classes) {
            String suffixe = "." + classe;
            boolean neutralisee = maison.keySet().stream()
                    .anyMatch(selecteur -> selecteur.contains(MARQUEUR_ECRAN_DE_JEU)
                            && selecteur.endsWith(suffixe));
            if (!neutralisee) {
                defauts.add("la classe \"" + classe + "\" est posee conditionnellement par un point "
                        + "d'entree, mais aucune regle maison ne la neutralise sur le marqueur \""
                        + MARQUEUR_ECRAN_DE_JEU + "\" : l'etat actif persisterait pendant une partie, "
                        + "silencieusement");
            }
        }
        assertThat(defauts)
                .as("chaque classe d'etat conditionnelle d'un point d'entree doit etre neutralisee "
                        + "sur un ecran de jeu")
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
                        + "(un selecteur qui se termine par \"." + classe + "\", sans pseudo-classe)");
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
    void laClasseActiveEstNeutraliseeSurUnEcranDeJeuEnProduction() {
        verifierQueLEtatActifEstNeutraliseSurUnEcranDeJeu(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    @Test
    void laClasseActiveNeReposePasSurLaSeuleCouleurEnProduction() {
        verifierQueLaClasseActiveNeReposePasSurLaSeuleCouleur(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3, clause E : mise a l'epreuve sur un objet neuf - un point d'entree que
     * personne n'a inscrit nulle part, dont la classe conditionnelle "etat-neuf" n'est neutralisee
     * par aucune regle maison. Distinct de la preuve sur materiau reel ci-dessous : celle-ci montre
     * que la clause voit un point d'entree qu'elle n'a jamais rencontre, pas seulement une
     * regression sur celui qui existe deja.
     */
    @Test
    void unPointDEntreeNeufSansNeutralisationFaitEchouerLaClauseE() {
        Path racine = CONTRE_EXEMPLES.resolve("point-d-entree-sans-neutralisation");
        assertThatThrownBy(() -> verifierQueLEtatActifEstNeutraliseSurUnEcranDeJeu(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison ne la neutralise");
    }

    /**
     * ADR-0013 obligation 3, clause E : preuve de morsure sur du materiau reel - une copie de la
     * production dont la regle de neutralisation de {@code .est-actif} est retiree pendant que la
     * classe reste posee par le gabarit. C'est exactement la defection silencieuse que la clause
     * existe pour fermer : l'etat actif persisterait pendant une partie sans qu'aucun autre
     * garde-fou ne le voie (la classe reste visible et focalisable, ADR-0038/ADR-0017).
     */
    @Test
    void uneRegleDeNeutralisationRetireeFaitEchouerLaClauseE(@TempDir Path tempDir) throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path feuille = tempDir.resolve("static/css/recreo.css");
        // Comparaison sur les fins de ligne normalisees (le fichier source est en CRLF) : seule
        // l'ecriture qui suit compte pour la mutation, la lecture qui prouve le rouge ne compare
        // que des blocs CSS, jamais un octet de fin de ligne.
        String contenu = Files.readString(feuille).replace("\r\n", "\n");
        String mute = contenu.replace(
                "body:has(." + MARQUEUR_ECRAN_DE_JEU + ") .chip-niveau.est-actif {\n"
                        + "    background-color: transparent;\n"
                        + "    color: var(--recreo-accent-texte);\n"
                        + "    border: 0.125rem solid var(--recreo-accent-texte);\n"
                        + "}",
                "");
        assertThat(mute).as("la regle de neutralisation doit exister telle quelle avant mutation")
                .isNotEqualTo(contenu);
        Files.writeString(feuille, mute);

        assertThatThrownBy(() -> verifierQueLEtatActifEstNeutraliseSurUnEcranDeJeu(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison ne la neutralise");
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
