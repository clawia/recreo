package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S2-2 : les ecrans aujourd'hui nus portent chacun au moins une regle de couleur, de fond ou
 * d'espacement, et ces couleurs viennent d'une palette nommee et reutilisee, jamais de valeurs
 * ponctuelles differentes a chaque selecteur. Lecture directe du CSS, jamais par expression
 * reguliere (ADR-0008, AssertionsWebTest).
 *
 * <p>Ce garde-fou juge des choix de style propres a ce depot : viser le CSS maison est ici
 * legitime, ce sont ses declarations a lui qui sont en cause. Une seule de ses methodes ne relevait
 * pas de ce registre, {@code aucuneRessourceExterneNiPoliceTelechargeeALExecution} : ne rien aller
 * chercher sur le reseau n'est pas un choix de style, c'est une condition de deploiement qui vaut
 * pour tout octet servi. Restreinte a un fichier nomme, elle promettait par son nom bien plus
 * qu'elle ne verifiait. Elle est supprimee par ADR-0015 au profit de
 * {@code RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau},
 * dont le perimetre derive de static/ entier, gabarits compris, et qui possede deux jeux fautifs.
 *
 * <p><b>Sortie de la dette d'ADR-0013, s05.</b> Ce garde-fou etait exempte du recensement de
 * {@code LesGardeFousSontMisALEpreuveTest} depuis s02 : il n'avait ni perimetre derive ni jeu
 * fautif. Le traiter par la seule obligation 3 - lui coller une fixture - aurait ete une fausse
 * sortie : une mutation prouve le critere, pas le perimetre, et son perimetre etait une liste de
 * onze selecteurs ecrite a la main. La propriete generale que son nom promettait - un element
 * rendu est habille - est donc portee par un balayage derive des gabarits
 * ({@code aucuneClassePoseeSurUnElementNEstOrpheline}), et la liste des onze selecteurs reste,
 * mais comme ce qu'elle est reellement : l'epinglage de ce que S2-2 a livre. Meme resolution
 * qu'ADR-0015 : la propriete generale demenage dans un garde-fou derive, l'epinglage nomme reste
 * nomme.
 */
class HabillageDesEcransTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, un gabarit qui n'a jamais ete livre. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/HabillageDesEcransTest");

    private static final String STATIQUE = "static";
    private static final String TIERS = "vendor";

    /**
     * Ce que S2-2 a livre, et que cette liste epingle : elle ne promet rien au-dela d'elle-meme.
     * Un ecran neuf n'y entre pas tout seul, et c'est le balayage derive qui le juge.
     */
    private static final List<String> SELECTEURS_HABILLES_PAR_S2_2 = List.of(
            "h1", ".jeux", ".demarrer", ".retour-accueil", ".score", ".fin-de-partie",
            ".indice", ".erreur", ".pas-de-jeu", ".message-reprise", ".recommencer");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un commentaire CSS peut contenir des virgules ; il ne doit jamais entrer dans un selecteur. */
    private static String sansCommentaires(String source) {
        StringBuilder resultat = new StringBuilder();
        int position = 0;
        while (position < source.length()) {
            int debut = source.indexOf("/*", position);
            if (debut < 0) {
                resultat.append(source, position, source.length());
                break;
            }
            resultat.append(source, position, debut);
            int fin = source.indexOf("*/", debut);
            position = fin < 0 ? source.length() : fin + 2;
        }
        return resultat.toString();
    }

    private static List<Path> feuilles(Path racine, boolean tierces) {
        Path statique = racine.resolve(STATIQUE);
        if (!Files.isDirectory(statique)) {
            return List.of();
        }
        Path repertoireTiers = statique.resolve(TIERS);
        try (Stream<Path> arborescence = Files.walk(statique)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".css"))
                    .filter(fichier -> fichier.startsWith(repertoireTiers) == tierces)
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** ADR-0014 : les choix de style de ce depot vivent dans les feuilles hors static/vendor. */
    private static String sourceMaison(Path racine) {
        StringBuilder source = new StringBuilder();
        for (Path feuille : feuilles(racine, false)) {
            source.append(lire(feuille)).append('\n');
        }
        return source.toString();
    }

    /**
     * Tout le CSS reellement servi, commentaires retires. Les commentaires ne sont pas un detail
     * de proprete : ceux de recreo.css citent nommement {@code .retour-accueil} ou {@code .btn}
     * pour expliquer un arbitrage. Les laisser ferait passer pour habillee une classe dont la
     * regle a ete supprimee et dont il ne reste que la mention - exactement le vert silencieux
     * mesure par nadia-prof en s03 sur {@code .sortie-pleine}.
     */
    private static String cssServi(Path racine) {
        StringBuilder source = new StringBuilder();
        for (Path feuille : feuilles(racine, false)) {
            source.append(sansCommentaires(lire(feuille))).append('\n');
        }
        for (Path feuille : feuilles(racine, true)) {
            source.append(sansCommentaires(lire(feuille))).append('\n');
        }
        return source.toString();
    }

    private static Map<String, String> blocsParSelecteur(String source) {
        source = sansCommentaires(source);
        Map<String, String> blocs = new HashMap<>();
        for (String bloc : source.split("\\}")) {
            int accolade = bloc.indexOf('{');
            if (accolade < 0) {
                continue;
            }
            String selecteurs = bloc.substring(0, accolade);
            String corps = bloc.substring(accolade + 1);
            for (String selecteur : selecteurs.split(",")) {
                blocs.merge(selecteur.strip(), corps, (ancien, nouveau) -> ancien + nouveau);
            }
        }
        return blocs;
    }

    private static List<Path> gabarits(Path racine) {
        Path repertoire = racine.resolve("templates");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Une classe posee sur un element rendu, et l'element qui la porte. */
    private record ClasseRendue(String nom, String origine) {
    }

    /**
     * ADR-0015 : les valeurs litterales qu'une expression Thymeleaf peut prendre. Une classe
     * entierement calculee est refusee plutot qu'ignoree - on ne peut pas verifier qu'une classe
     * est habillee quand on ne sait pas laquelle sera rendue (ADR-0013 obligation 2).
     */
    private static List<String> litteraux(Element element, String attribut, String origine) {
        String expression = element.attr(attribut);
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
        assertThat(valeurs)
                .as("%s : aucune classe litterale a lire dans %s=\"%s\" ; une classe calculee ne peut "
                        + "pas etre confrontee au CSS servi", origine, attribut, expression)
                .isNotEmpty();
        return valeurs;
    }

    /**
     * ADR-0013 obligation 1 et 2 : la population vient du balisage - tout element de tout gabarit -
     * et chaque classe qu'un element peut porter au rendu produit une assertion, qu'elle vienne de
     * l'attribut class, d'un th:class ou d'un th:classappend.
     */
    static List<ClasseRendue> classesRendues(Path racine) {
        List<ClasseRendue> classes = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.select("[class], [th\\:class], [th\\:classappend]")) {
                String origine = "<" + element.tagName() + "> de " + gabarit;
                for (String nom : element.classNames()) {
                    classes.add(new ClasseRendue(nom, origine));
                }
                for (String attribut : List.of("th:class", "th:classappend")) {
                    if (!element.hasAttr(attribut)) {
                        continue;
                    }
                    for (String litteral : litteraux(element, attribut, origine)) {
                        for (String mot : litteral.split("\\s+")) {
                            if (!mot.isBlank()) {
                                classes.add(new ClasseRendue(mot, origine));
                            }
                        }
                    }
                }
            }
        }
        return classes;
    }

    /**
     * La classe est-elle nommee par au moins un selecteur du CSS servi ? La recherche exige un nom
     * de classe entier : {@code .btn} ne doit pas etre satisfait par {@code .btn-check}.
     */
    private static boolean estHabillee(String css, String classe) {
        String motif = "." + classe;
        int position = css.indexOf(motif);
        while (position >= 0) {
            int suivant = position + motif.length();
            char apres = suivant < css.length() ? css.charAt(suivant) : ' ';
            if (!Character.isLetterOrDigit(apres) && apres != '-' && apres != '_') {
                return true;
            }
            position = css.indexOf(motif, position + 1);
        }
        return false;
    }

    /**
     * La propriete generale que ce garde-fou promet : un element qui porte une classe est habille
     * par une regle servie. Une classe qu'aucune feuille ne nomme - faute de frappe dans le
     * gabarit, regle supprimee du CSS, classe d'une bibliotheque qu'on croit chargee - laisse
     * l'element au rendu par defaut du navigateur, sans qu'aucun test ne bronche aujourd'hui.
     */
    static void verifierQuAucuneClasseNEstOrpheline(Path racine) {
        String css = cssServi(racine);
        List<ClasseRendue> classes = classesRendues(racine);
        assertThat(classes)
                .as("ce garde-fou ne prouve rien si aucune classe n'est relevee sous %s", racine)
                .isNotEmpty();

        for (ClasseRendue classe : classes) {
            assertThat(estHabillee(css, classe.nom()))
                    .as("la classe '%s', posee sur %s, n'est nommee par aucune feuille servie : "
                            + "l'element retombe au rendu par defaut du navigateur",
                            classe.nom(), classe.origine())
                    .isTrue();
        }
    }

    @Test
    void aucuneClassePoseeSurUnElementNEstOrpheline() {
        verifierQuAucuneClasseNEstOrpheline(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un gabarit que personne n'a inscrit nulle part,
     * portant une classe qu'aucune feuille ne declare. Seul un perimetre derive du repertoire de
     * gabarits peut le voir.
     */
    @Test
    void uneClasseQuAucuneFeuilleNeDeclareFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucuneClasseNEstOrpheline(CONTRE_EXEMPLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandeau-jamais-declare");
    }

    /**
     * Epinglage de ce que S2-2 a livre, pas une promesse generale : ces onze selecteurs-la portent
     * encore une regle de couleur, de fond ou d'espacement. La propriete generale est portee par
     * {@link #aucuneClassePoseeSurUnElementNEstOrpheline()}.
     */
    @Test
    void lesSelecteursHabillesParS2DeuxPortentToujoursCouleurFondOuEspacement() {
        Map<String, String> blocs = blocsParSelecteur(sourceMaison(RACINE_PRODUCTION));

        for (String selecteur : SELECTEURS_HABILLES_PAR_S2_2) {
            String corps = blocs.get(selecteur);
            assertThat(corps).as("le selecteur %s doit exister dans le CSS maison", selecteur).isNotNull();
            assertThat(corps.contains("color:") || corps.contains("background")
                    || corps.contains("margin") || corps.contains("padding"))
                    .as("%s doit porter au moins une regle de couleur, de fond ou d'espacement", selecteur)
                    .isTrue();
        }
    }

    @Test
    void lesCouleursProviennentDUnePaletteNommeeEtReutilisee() {
        String source = sourceMaison(RACINE_PRODUCTION);
        assertThat(source).contains(":root");

        int debut = source.indexOf(":root");
        int finBloc = source.indexOf('}', debut);
        String racine = source.substring(debut, finBloc);

        int nombreDeVariables = 0;
        int position = 0;
        while ((position = racine.indexOf("--recreo-", position)) >= 0) {
            nombreDeVariables++;
            position += "--recreo-".length();
        }
        assertThat(nombreDeVariables)
                .as("la palette doit nommer plusieurs valeurs reutilisables")
                .isGreaterThanOrEqualTo(4);

        int nombreDUsagesVar = 0;
        position = 0;
        while ((position = source.indexOf("var(--recreo-", position)) >= 0) {
            nombreDUsagesVar++;
            position += "var(--recreo-".length();
        }
        assertThat(nombreDUsagesVar)
                .as("chaque variable doit etre reutilisee a plusieurs endroits, pas juste declaree")
                .isGreaterThan(nombreDeVariables);
    }

    /**
     * S3-6 : sur l'ecran de fin de partie, la sortie devient la seule action possible et doit
     * porter le meme aplat de couleur que les autres boutons d'action, pas le contour discret
     * retenu pour l'ecran de jeu (nadia-prof, FICHE-bandit-manchot-additions-cp.md, reserve 4).
     */
    @Test
    void laSortiePleineEstUnAplatColoreDeLaPalette() {
        Map<String, String> blocs = blocsParSelecteur(sourceMaison(RACINE_PRODUCTION));
        String corps = blocs.get(".sortie-pleine");

        assertThat(corps).as("le selecteur .sortie-pleine doit exister dans le CSS maison").isNotNull();
        assertThat(corps).contains("background-color: var(--recreo-accent)");
        assertThat(corps).doesNotContain("transparent");
    }

    /**
     * S3-6, 3e critere : sur l'ecran de jeu et sur le retour juste/faux, la sortie reste le
     * contour discret accorde par nadia-prof pour S2-1 - aucune regression sur ce point.
     */
    @Test
    void laSortieResteEnContourSurLEcranDeJeuEtDeRetour() {
        Map<String, String> blocs = blocsParSelecteur(sourceMaison(RACINE_PRODUCTION));
        String corps = blocs.get(".retour-accueil");

        assertThat(corps).as("le selecteur .retour-accueil doit exister dans le CSS maison").isNotNull();
        assertThat(corps).contains("transparent");
    }

    /**
     * S3-6, 2e critere : sur l'ecran d'erreur, "Recommencer" est aussi la seule action possible et
     * doit rester un aplat de couleur, jamais un lien discret.
     */
    @Test
    void recommencerResteUnAplatColoreSurLEcranDErreur() {
        Map<String, String> blocs = blocsParSelecteur(sourceMaison(RACINE_PRODUCTION));
        String corps = blocs.get(".recommencer");

        assertThat(corps).as("le selecteur .recommencer doit exister dans le CSS maison").isNotNull();
        assertThat(corps).contains("background-color: var(--recreo-accent)");
    }
}
