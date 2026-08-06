package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0017 : sur un ecran ou l'enfant peut encore jouer, aucun bouton de sortie ne declare une
 * composante de boite superieure a celle d'un bouton d'action du meme ecran.
 *
 * <p>Le nom de cette classe dit ce qu'elle verifie et pas plus. Une saillance est un rapport de
 * surfaces sur un rendu ; ce qui est lu ici est declaratif. Un garde-fou nomme "l'action est plus
 * grosse que la sortie" mentirait : la largeur rendue depend du nombre de glyphes du libelle, et
 * rien ici ne mesure un glyphe. Les limites sont enumerees dans ADR-0017, section "Ce que cette
 * regle ne couvre pas", et elles appartiennent a nadia-prof.
 *
 * <p>Motif : trois fois de suite, un defaut de hierarchie visible par un enfant a traverse une CI
 * entierement verte, parce qu'aucun garde-fou du depot ne comparait deux selecteurs entre eux sur un
 * meme ecran. Les regles existantes verifient qu'une declaration est presente ou qu'une valeur
 * depasse un plancher ; aucune ne verifiait qu'un bouton est plus gros qu'un autre.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li>le perimetre derive du repertoire de gabarits et du balisage, jamais d'une liste de
 *       boutons ni d'une table classe -&gt; role ecrite ici ;</li>
 *   <li>la derivation est totale : un bouton sans {@code data-role}, un role inconnu, un role
 *       calcule, une classe illisible ou une composante de boite qu'aucune regle maison ne declare
 *       sont des echecs, jamais des membres ignores ;</li>
 *   <li>la mise a l'epreuve porte sur un objet neuf - un ecran que personne n'a inscrit nulle part -
 *       et, au-dela de ce qu'ADR-0013 exige, sur les trois etats reels du depot qui ont motive cette
 *       regle. Une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine ; l'histoire
 *       du depot, non (lecon d'ADR-0015).</li>
 * </ul>
 *
 * <p>Le garde-fou prend deux racines : celle des gabarits et celle des feuilles. Elles sont la meme
 * en production ; les separer permet de rejouer un etat passe du CSS sous les gabarits d'aujourd'hui
 * sans en recopier aucun.
 */
class LActionNeDeclarePasMoinsQueLaSortieTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LActionNeDeclarePasMoinsQueLaSortieTest");

    /** Attribut inerte : aucune feuille et aucun script ne s'en sert, il ne sert qu'a dire le role. */
    private static final String ATTRIBUT_DE_ROLE = "data-role";

    private static final String ACTION = "action";
    private static final String SORTIE = "sortie";
    private static final Set<String> ROLES = Set.of(ACTION, SORTIE);

    private static final String CLASSE_CALCULEE = "th:class";
    private static final String CLASSE_AJOUTEE = "th:classappend";
    private static final String ATTRIBUTS_CALCULES = "th:attr";
    private static final String RENDU_SI = "th:if";
    private static final String RENDU_SAUF_SI = "th:unless";

    private static final String UNITE = "rem";

    /**
     * Les composantes declarees dont la boite d'un bouton est faite. Le rang designe la composante
     * d'un raccourci CSS : {@code padding: 0.75rem 1.25rem} donne le haut et le bas au rang 0, la
     * gauche et la droite au rang 1.
     *
     * <p>{@code border-width} n'y est pas, et c'est un choix mesure, pas un oubli : l'etat que
     * nadia-prof a accepte en s02 donne a la sortie un contour de 0,125rem que l'action n'a pas.
     * Le contour est un moyen de rester visible en etant moins saillant ; l'inclure ici rendrait
     * rouge un etat qu'elle a valide en jouant. {@code min-width} n'y est pas non plus : la sortie
     * ne le declare pas, sa largeur venant de son libelle et de son rembourrage.
     */
    private record Composante(String nom, String propriete, int rang) {
    }

    private static final List<Composante> COMPOSANTES = List.of(
            new Composante("taille de police", "font-size", 0),
            new Composante("hauteur minimale", "min-height", 0),
            new Composante("rembourrage haut et bas", "padding", 0),
            new Composante("rembourrage gauche et droite", "padding", 1));

    /** Une condition Thymeleaf et la valeur qu'un etat rendu lui impose. */
    private record Contrainte(String condition, boolean attendue) {
    }

    /** Un bouton dans l'un des etats qu'il peut prendre au rendu, sur un ecran donne. */
    private record Etat(String ecran, Element bouton, String role, List<String> classes,
                        List<Contrainte> contraintes) {

        String etiquette() {
            String vues = classes.isEmpty()
                    ? "<" + bouton.tagName() + "> sans classe"
                    : "." + String.join(".", classes);
            return vues + " \"" + bouton.text() + "\"";
        }
    }

    /** Un etat et la valeur lue pour chaque composante, vide quand elle ne se lit pas. */
    private record Boite(Etat etat, List<Optional<Double>> valeurs) {
    }

    private record Branche(List<String> classes, List<Contrainte> contraintes) {
    }

    private static String sansEspaces(String texte) {
        StringBuilder compact = new StringBuilder(texte.length());
        for (int position = 0; position < texte.length(); position++) {
            char caractere = texte.charAt(position);
            if (!Character.isWhitespace(caractere)) {
                compact.append(caractere);
            }
        }
        return compact.toString();
    }

    private static List<String> mots(String texte) {
        return Stream.of(texte.strip().split("\\s+")).filter(mot -> !mot.isEmpty()).toList();
    }

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

    /** Le point d'interrogation du ternaire, celui qui est hors litteral. */
    private static int conditionnel(String expression) {
        boolean dansUnLitteral = false;
        for (int position = 0; position < expression.length(); position++) {
            char caractere = expression.charAt(position);
            if (caractere == '\'') {
                dansUnLitteral = !dansUnLitteral;
            } else if (caractere == '?' && !dansUnLitteral) {
                return position;
            }
        }
        return -1;
    }

    /**
     * Les etats qu'une expression de classe Thymeleaf produit, avec la condition qui gouverne
     * chacun. C'est cette condition qui permet plus bas de n'opposer que des boutons capables de
     * s'afficher ensemble. Une expression qu'on ne sait pas lire est un defaut, jamais un etat
     * ignore.
     */
    private static List<Branche> branches(String origine, String expression, Set<String> defauts) {
        List<String> valeurs = litteraux(expression);
        if (expression.contains("+")) {
            defauts.add(origine + " : expression concatenee, les classes rendues ne se lisent pas ; "
                    + "ecrire chaque etat en litteral");
            return List.of();
        }
        if (valeurs.isEmpty()) {
            defauts.add(origine + " : aucune classe litterale a lire dans cette expression ; une classe "
                    + "entierement calculee ne se compare a rien");
            return List.of();
        }
        int ternaire = conditionnel(expression);
        if (ternaire < 0) {
            return valeurs.stream()
                    .map(valeur -> new Branche(mots(valeur), List.<Contrainte>of()))
                    .toList();
        }
        if (valeurs.size() != 2) {
            defauts.add(origine + " : expression conditionnelle a " + valeurs.size() + " litteraux, ses "
                    + "branches ne s'apparient pas a une condition ; le garde-fou ne saurait pas quels "
                    + "etats s'affichent ensemble");
            return List.of();
        }
        String condition = sansEspaces(expression.substring(0, ternaire));
        return List.of(
                new Branche(mots(valeurs.get(0)), List.of(new Contrainte(condition, true))),
                new Branche(mots(valeurs.get(1)), List.of(new Contrainte(condition, false))));
    }

    /** Les conditions que les ancetres du bouton imposent a son affichage. */
    private static List<Contrainte> contraintesHeritees(Element bouton) {
        List<Contrainte> contraintes = new ArrayList<>();
        for (Element noeud = bouton; noeud != null; noeud = noeud.parent()) {
            if (noeud.hasAttr(RENDU_SI)) {
                contraintes.add(new Contrainte(sansEspaces(noeud.attr(RENDU_SI)), true));
            }
            if (noeud.hasAttr(RENDU_SAUF_SI)) {
                contraintes.add(new Contrainte(sansEspaces(noeud.attr(RENDU_SAUF_SI)), false));
            }
        }
        return contraintes;
    }

    private static String role(String origine, Element bouton, Set<String> defauts) {
        if (bouton.attr(ATTRIBUTS_CALCULES).contains(ATTRIBUT_DE_ROLE)) {
            defauts.add(origine + " : le role d'un bouton ne se calcule pas ; " + ATTRIBUT_DE_ROLE
                    + " se pose en clair sur la balise, sinon la source de verite du role cesse d'etre "
                    + "l'ecran");
            return null;
        }
        String role = bouton.attr(ATTRIBUT_DE_ROLE).strip();
        if (role.isEmpty()) {
            defauts.add(origine + " : ce bouton ne porte pas " + ATTRIBUT_DE_ROLE + "=\"" + ACTION
                    + "\" ou \"" + SORTIE + "\". Sans role, il sortirait du controle en silence, et "
                    + "c'est exactement le vert silencieux que cette regle existe pour fermer "
                    + "(ADR-0013, obligation 2)");
            return null;
        }
        if (!ROLES.contains(role)) {
            defauts.add(origine + " : role \"" + role + "\" inconnu ; les seules valeurs lues sont \""
                    + ACTION + "\" et \"" + SORTIE + "\"");
            return null;
        }
        return role;
    }

    /** ADR-0013 obligation 1 : les ecrans viennent du repertoire, les boutons viennent du balisage. */
    private static List<Etat> etats(Path racineDesGabarits, Set<String> defauts) {
        List<Etat> etats = new ArrayList<>();
        for (Path gabarit : gabarits(racineDesGabarits)) {
            String ecran = racineDesGabarits.relativize(gabarit).toString().replace('\\', '/');
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element bouton : document.select("button, a[href]")) {
                String origine = "ecran " + ecran + ", <" + bouton.tagName() + "> \"" + bouton.text()
                        + "\"";
                String role = role(origine, bouton, defauts);
                if (role == null) {
                    continue;
                }
                List<Branche> parClasse = bouton.hasAttr(CLASSE_CALCULEE)
                        ? branches(origine, bouton.attr(CLASSE_CALCULEE), defauts)
                        : List.of(new Branche(List.copyOf(bouton.classNames()), List.of()));
                List<Branche> parAjout = bouton.hasAttr(CLASSE_AJOUTEE)
                        ? branches(origine, bouton.attr(CLASSE_AJOUTEE), defauts)
                        : List.of(new Branche(List.of(), List.of()));
                List<Contrainte> heritees = contraintesHeritees(bouton);
                for (Branche classe : parClasse) {
                    for (Branche ajout : parAjout) {
                        List<String> classes = new ArrayList<>(classe.classes());
                        classes.addAll(ajout.classes());
                        List<Contrainte> contraintes = new ArrayList<>(heritees);
                        contraintes.addAll(classe.contraintes());
                        contraintes.addAll(ajout.contraintes());
                        etats.add(new Etat(ecran, bouton, role, classes, contraintes));
                    }
                }
            }
        }
        return etats;
    }

    /**
     * Deux etats peuvent s'afficher ensemble tant qu'aucune condition ne les separe. Une condition
     * que le garde-fou ne reconnait pas textuellement laisse les deux etats comparables : le doute
     * produit une comparaison de plus, jamais une de moins.
     */
    private static boolean sAffichentEnsemble(Etat premier, Etat second) {
        for (Contrainte contrainte : premier.contraintes()) {
            for (Contrainte autre : second.contraintes()) {
                if (contrainte.condition().equals(autre.condition())
                        && contrainte.attendue() != autre.attendue()) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Les couples action/sortie a confronter : meme ecran, capables de s'afficher ensemble. */
    private static List<int[]> appariements(List<Etat> etats) {
        List<int[]> couples = new ArrayList<>();
        for (int action = 0; action < etats.size(); action++) {
            if (!ACTION.equals(etats.get(action).role())) {
                continue;
            }
            for (int sortie = 0; sortie < etats.size(); sortie++) {
                Etat candidate = etats.get(sortie);
                if (!SORTIE.equals(candidate.role())
                        || !candidate.ecran().equals(etats.get(action).ecran())
                        || !sAffichentEnsemble(etats.get(action), candidate)) {
                    continue;
                }
                couples.add(new int[] {action, sortie});
            }
        }
        return couples;
    }

    /**
     * ADR-0014, point 6 : une composante de boite qui n'est declaree par aucune regle maison ne
     * garantit rien, parce qu'elle vient alors d'une bibliotheque tierce ou du defaut du navigateur.
     * C'est la forme exacte prise par la regression du lot Bootstrap, et c'est pourquoi l'absence est
     * un defaut plutot qu'une valeur inconnue.
     */
    private static Optional<Double> mesure(Map<String, String> maison, Etat etat,
                                           Composante composante, Set<String> defauts) {
        List<String> applicables = etat.classes().isEmpty()
                ? List.of(etat.bouton().tagName())
                : etat.classes().stream().map(classe -> "." + classe).toList();
        List<String> porteurs = applicables.stream()
                .filter(maison::containsKey)
                .filter(selecteur -> debutDeDeclaration(maison.get(selecteur), composante.propriete()) >= 0)
                .toList();
        String prefixe = "ecran " + etat.ecran() + " : " + etat.etiquette();
        if (porteurs.isEmpty()) {
            defauts.add(prefixe + " ne recoit " + composante.propriete() + " d'aucune regle maison "
                    + "(selecteurs vus : " + applicables + ") ; sa boite depend alors d'une bibliotheque "
                    + "tierce ou du defaut du navigateur, et sa saillance ne se compare a rien "
                    + "(ADR-0014)");
            return Optional.empty();
        }
        if (porteurs.size() > 1) {
            defauts.add(prefixe + " recoit " + composante.propriete() + " de plusieurs regles maison "
                    + porteurs + " ; la valeur rendue depend de la cascade et ne se lit pas ici");
            return Optional.empty();
        }
        String corps = maison.get(porteurs.get(0));
        String declaree = valeurDeclaree(corps,
                debutDeDeclaration(corps, composante.propriete()), composante.propriete());
        List<String> parties = mots(declaree);
        if (parties.isEmpty() || parties.size() > 2) {
            defauts.add(prefixe + " declare " + composante.propriete() + " a " + parties.size()
                    + " valeurs (" + declaree + ") ; ce garde-fou ne lit que les raccourcis a une ou "
                    + "deux valeurs, ou le haut et le bas sont necessairement egaux");
            return Optional.empty();
        }
        String brute = parties.get(composante.rang() < parties.size() ? composante.rang() : 0);
        if (!brute.endsWith(UNITE)) {
            defauts.add(prefixe + " declare " + composante.propriete() + " a \"" + declaree
                    + "\" ; les composantes comparees ici s'expriment en " + UNITE + ", faute de quoi "
                    + "deux boutons ne se comparent pas");
            return Optional.empty();
        }
        return Optional.of(Double.parseDouble(brute.substring(0, brute.length() - UNITE.length())));
    }

    private static String enRem(double valeur) {
        return String.format(Locale.FRENCH, "%.2f", valeur) + UNITE;
    }

    static void verifierQueLActionNeDeclarePasMoinsQueLaSortie(Path racineDesGabarits,
                                                              Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        List<Etat> etats = etats(racineDesGabarits, defauts);

        List<Boite> boites = etats.stream()
                .map(etat -> new Boite(etat, COMPOSANTES.stream()
                        .map(composante -> mesure(maison, etat, composante, defauts))
                        .toList()))
                .toList();

        List<int[]> couples = appariements(etats);
        for (int[] couple : couples) {
            Boite action = boites.get(couple[0]);
            Boite sortie = boites.get(couple[1]);
            for (int rang = 0; rang < COMPOSANTES.size(); rang++) {
                Optional<Double> valeurAction = action.valeurs().get(rang);
                Optional<Double> valeurSortie = sortie.valeurs().get(rang);
                if (valeurAction.isEmpty() || valeurSortie.isEmpty()
                        || valeurAction.get() >= valeurSortie.get()) {
                    continue;
                }
                defauts.add("ecran " + action.etat().ecran() + " : la sortie " + sortie.etat().etiquette()
                        + " declare " + COMPOSANTES.get(rang).nom() + " a " + enRem(valeurSortie.get())
                        + " quand l'action " + action.etat().etiquette() + " se contente de "
                        + enRem(valeurAction.get()) + " ; sur un ecran ou l'enfant peut encore jouer, ce "
                        + "qui appelle la main doit etre ce qui fait avancer le jeu, jamais ce qui en "
                        + "sort");
            }
        }
        if (couples.isEmpty()) {
            defauts.add("aucun couple action/sortie n'a ete confronte : cette regle ne prouverait rien");
        }

        assertThat(defauts)
                .as("ADR-0017 : sur un meme ecran, un bouton de sortie ne declare aucune composante de "
                        + "boite superieure a celle d'un bouton d'action, et toute composante comparee "
                        + "est declaree par une regle maison")
                .isEmpty();
    }

    static List<String> couplesConfrontes(Path racineDesGabarits) {
        List<Etat> etats = etats(racineDesGabarits, new LinkedHashSet<>());
        return appariements(etats).stream()
                .map(couple -> "ecran " + etats.get(couple[0]).ecran() + " : "
                        + etats.get(couple[0]).etiquette() + " contre " + etats.get(couple[1]).etiquette())
                .toList();
    }

    @Test
    void aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran() {
        verifierQueLActionNeDeclarePasMoinsQueLaSortie(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    /**
     * Epinglage du seul point ou l'appariement fait un choix : sur l'ecran de retour, la sortie et
     * "Encore !" dependent du meme {@code ${enSuite}}. Ils sont confrontes quand la partie continue -
     * c'est le couple que le lot Bootstrap a inverse - et ne le sont pas a la fin de partie, ou la
     * sortie devient la seule action possible et doit legitimement dominer (S3-6). Si quelqu'un
     * dissocie ces deux conditions, ce test devient rouge au lieu de laisser l'appariement se
     * defaire en silence.
     */
    @Test
    void surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie() {
        List<String> couples = couplesConfrontes(RACINE_PRODUCTION);

        assertThat(couples)
                .as("le couple que le lot Bootstrap a inverse doit etre confronte")
                .anyMatch(couple -> couple.contains(".btn.encore") && couple.contains(".btn.retour-accueil"));
        assertThat(couples)
                .as("la sortie de fin de partie n'a aucun bouton d'action a concurrencer sur son ecran")
                .noneMatch(couple -> couple.contains("sortie-pleine"));
        assertThat(couples)
                .as("l'ecran de jeu confronte la reponse a la sortie")
                .anyMatch(couple -> couple.contains(".btn.proposition"));
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un ecran que personne n'a inscrit nulle part, avec un
     * bouton qui quitte plus gros que le bouton qui continue. C'est la faute que la regle existe pour
     * interdire, sur un objet qui n'est pas la mutation d'un objet connu.
     */
    @Test
    void unEcranNeufOuLaSortieDepasseLActionFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("ecran-neuf-a-la-hierarchie-inversee");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la sortie .terminer \"Rentrer\" declare rembourrage gauche et droite");
    }

    /** ADR-0013 obligation 2 : un bouton dont le role ne se lit pas est un echec, pas un ignore. */
    @Test
    void unBoutonSansRoleFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("bouton-sans-role");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas data-role");
    }

    /** Un role calcule ferait dependre le perimetre d'une expression que ce garde-fou ne resout pas. */
    @Test
    void unRoleCalculeFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("role-calcule");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le role d'un bouton ne se calcule pas");
    }

    /**
     * Troisieme occurrence, lot Bootstrap (commit 6c83c97). Le rembourrage maison est retire du bloc
     * des boutons pleins au profit de celui de .btn, mais pas de .retour-accueil qui n'etait pas dans
     * ce bloc ; le bouton qui quitte redevient 30 % plus grand que celui qui continue et toute la
     * suite reste verte.
     *
     * <p>La feuille de ce jeu d'artefacts est le fichier de ce commit, octet pour octet. Les gabarits
     * sont ceux de la production : ils n'ont pas change entre ce commit et aujourd'hui, hors
     * l'attribut de role que cette regle introduit. Ce n'est donc pas une fixture qui ressemble a ce
     * que j'imagine, c'est l'etat qui est passe.
     */
    @Test
    void leRejeuDuLotBootstrapFaitEchouerLeGardeFou() {
        Path feuilles = CONTRE_EXEMPLES.resolve("rejeu-lot-bootstrap");
        assertThatThrownBy(
                () -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(RACINE_PRODUCTION, feuilles))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".btn.encore \"Encore !\" ne recoit padding d'aucune regle maison");
    }

    /**
     * Premiere occurrence, refus de S2-1 en s02 (commit 3be5a86) : le bouton de sortie passe a
     * 115 x 47 px pendant que le bouton de reponse reste a 46 x 41 px. Feuille et gabarits sont ceux
     * du commit, a l'attribut de role pres.
     */
    @Test
    void leRejeuDuRefusDeSDeuxFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("rejeu-s02-refuse");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la sortie .retour-accueil \"Les jeux\" declare taille de police "
                        + "a 1,25rem quand l'action .proposition \"\" se contente de 1,10rem");
    }

    /**
     * Deuxieme occurrence, correction acceptee en s02 (commit fd16057) : la sortie fait 0,13 fois la
     * surface d'une reponse et nadia-prof accepte. Une regle de saillance qui rougirait ici serait
     * pire qu'absente - elle serait affaiblie au premier sprint ou elle generait. C'est ce rejeu qui
     * a fait sortir border-width des composantes comparees.
     */
    @Test
    void leRejeuDeLEtatAccepteEnSDeuxResteVert() {
        Path racine = CONTRE_EXEMPLES.resolve("rejeu-s02-accepte");
        assertThatCode(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .doesNotThrowAnyException();
    }
}
