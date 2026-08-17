package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage;
import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masqueSur;
import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages;
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

    /**
     * ADR-0043 : le troisieme role, un point d'entree permanent. Il ne fait pas avancer le jeu
     * affiche (donc pas {@code action}) et il n'est pas la sortie de ce jeu (donc pas
     * {@code sortie}) : il mene ailleurs dans l'application, depuis un habillage qui ne disparait
     * pas. Les deux valeurs existantes ont ete essayees avant d'en ouvrir une troisieme, et chacune
     * rend un garde-fou de ce depot rouge - la mesure est dans ADR-0043.
     */
    private static final String ENTREE = "entree";

    /**
     * Les roles domines : ceux dont aucune composante de boite ne peut depasser celle d'une action
     * du meme ecran. La propriete d'ADR-0017 ne parle pas de sortie, elle parle de ce qui appelle la
     * main : sur un ecran ou l'enfant peut encore jouer, c'est ce qui fait avancer le jeu qui doit
     * appeler la main, jamais ce qui mene ailleurs. Un point d'entree mene ailleurs.
     */
    private static final Set<String> ROLES_DOMINES = Set.of(SORTIE, ENTREE);

    private static final Set<String> ROLES = Set.of(ACTION, SORTIE, ENTREE);

    /** Comment un role se nomme dans un message d'echec, pour que le defaut se lise. */
    private static String designerLeRole(String role) {
        return SORTIE.equals(role) ? "la sortie" : "le point d'entree";
    }

    private static final String CLASSE_CALCULEE = "th:class";
    private static final String CLASSE_AJOUTEE = "th:classappend";
    private static final String ATTRIBUTS_CALCULES = "th:attr";
    private static final String RENDU_SI = "th:if";
    private static final String RENDU_SAUF_SI = "th:unless";

    /** L'attribut htmx qui designe la zone remplacee quand l'enfant passe d'un ecran a l'autre. */
    private static final String ZONE_ECHANGEE = "hx-target";

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

    /**
     * Un bouton dans l'un des etats qu'il peut prendre au rendu, sur un ecran donne. {@code source}
     * est le gabarit ou le bouton est ecrit ; il differe d'{@code ecran} pour un bouton d'habillage
     * permanent, qui est ecrit une fois et s'affiche sur tous les ecrans. Les defauts de lecture
     * nomment la source - c'est la qu'on va corriger ; les defauts de comparaison nomment l'ecran -
     * c'est la que l'enfant voit les deux boutons ensemble.
     */
    private record Etat(String ecran, String source, Element bouton, String role, List<String> classes,
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
            defauts.add(origine + " : ce bouton ne porte pas " + ATTRIBUT_DE_ROLE + " parmi "
                    + new TreeSet<>(ROLES) + ". Sans role, il sortirait du controle en silence, et "
                    + "c'est exactement le vert silencieux que cette regle existe pour fermer "
                    + "(ADR-0013, obligation 2)");
            return null;
        }
        if (!ROLES.contains(role)) {
            defauts.add(origine + " : role \"" + role + "\" inconnu ; les seules valeurs lues sont "
                    + new TreeSet<>(ROLES));
            return null;
        }
        return role;
    }

    /**
     * Le selecteur de la zone que htmx echange d'un ecran a l'autre, lu dans les gabarits. Vide
     * quand aucun gabarit ne declare de cible : un depot sans echange de fragment n'a qu'un ecran
     * par fichier, qui est le modele d'origine d'ADR-0017. Plusieurs cibles distinctes sont un
     * defaut et non un choix arbitraire - le garde-fou ne saurait plus dire ce qui survit a un
     * changement d'ecran.
     */
    private static Optional<String> zoneEchangee(Collection<Document> documents, Set<String> defauts) {
        Set<String> cibles = new LinkedHashSet<>();
        for (Document document : documents) {
            for (Element porteur : document.select("[" + ZONE_ECHANGEE + "]")) {
                cibles.add(porteur.attr(ZONE_ECHANGEE).strip());
            }
        }
        if (cibles.size() > 1) {
            defauts.add("les gabarits echangent plusieurs zones " + cibles + " ; le garde-fou ne sait "
                    + "plus quels boutons survivent a un changement d'ecran, donc lesquels accompagnent "
                    + "les boutons de tous les autres ecrans");
            return Optional.empty();
        }
        return cibles.stream().findFirst();
    }

    /** Comparaison d'identite : deux elements jsoup distincts peuvent porter le meme balisage. */
    private static boolean estDans(Element bouton, Elements zone) {
        for (Element noeud = bouton; noeud != null; noeud = noeud.parent()) {
            for (Element candidate : zone) {
                if (noeud == candidate) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ADR-0013 obligation 1 : les ecrans viennent du repertoire, les boutons viennent du balisage.
     *
     * <p>Un ecran n'est plus un fichier de gabarit. Un bouton ecrit hors de la zone echangee, dans
     * le gabarit qui contient cette zone, reste affiche quand le fragment change : il accompagne
     * donc les boutons de tous les ecrans et se compare a eux. C'etait la limite 5 d'ADR-0017,
     * inoffensive tant qu'aucun bouton ne vivait hors de la zone, devenue portante le jour ou
     * l'en-tete permanent a pose un lien de sortie sur tous les ecrans (S6-1).
     *
     * <p>Amendement du 2026-08-07, ADR-0022 : "hors de la zone echangee" ne veut pas dire "visible
     * sur tous les ecrans". Un habillage permanent que la feuille maison masque sur un ecran n'y est
     * pas affiche, donc il n'y accompagne aucun bouton d'action, donc il ne s'y compare a rien. Les
     * ecrans ou il reste visible sont derives des masquages declares par la feuille - la source qui
     * gouverne ce rendu (ADR-0015) - et non d'une liste ecrite ici. Sans cet amendement,
     * l'appariement survivrait a la disparition de son sujet : c'est le symptome que nadia-prof a
     * nomme dans son arbitrage de S6-9, et la famille de defaut que la retro de s05 a sanctionnee
     * deux fois.
     */
    private static List<Etat> etats(Path racineDesGabarits, List<Masquage> masquages,
                                    Set<String> defauts) {
        Map<String, Document> documents = new LinkedHashMap<>();
        for (Path gabarit : gabarits(racineDesGabarits)) {
            String source = racineDesGabarits.relativize(gabarit).toString().replace('\\', '/');
            documents.put(source, Jsoup.parse(lire(gabarit), gabarit.toString()));
        }
        Optional<String> zoneEchangee = zoneEchangee(documents.values(), defauts);

        List<Etat> etats = new ArrayList<>();
        for (Map.Entry<String, Document> gabarit : documents.entrySet()) {
            String source = gabarit.getKey();
            Document document = gabarit.getValue();
            Elements zone = zoneEchangee.map(document::select).orElseGet(Elements::new);
            for (Element bouton : document.select("button, a[href]")) {
                String origine = "ecran " + source + ", <" + bouton.tagName() + "> \"" + bouton.text()
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
                boolean dansLaZone = zone.isEmpty() || estDans(bouton, zone);
                for (Branche classe : parClasse) {
                    for (Branche ajout : parAjout) {
                        List<String> classes = new ArrayList<>(classe.classes());
                        classes.addAll(ajout.classes());
                        List<Contrainte> contraintes = new ArrayList<>(heritees);
                        contraintes.addAll(classe.contraintes());
                        contraintes.addAll(ajout.contraintes());
                        Collection<String> ecrans = dansLaZone
                                ? List.of(source)
                                : ecransOuIlResteVisible(documents, classes, masquages);
                        for (String ecran : ecrans) {
                            etats.add(new Etat(ecran, source, bouton, role, classes, contraintes));
                        }
                    }
                }
            }
        }
        return etats;
    }

    /**
     * Les ecrans ou un habillage ecrit hors de la zone echangee reste effectivement visible.
     *
     * <p>Un masquage declare par la feuille maison retire cet habillage des ecrans qui portent son
     * marqueur d'etat (ADR-0022). Le doute joue dans le sens de la comparaison : un habillage qu'aucun
     * masquage ne nomme reste confronte a tous les ecrans, comme avant cet amendement.
     */
    private static List<String> ecransOuIlResteVisible(Map<String, Document> documents,
                                                      List<String> classes,
                                                      List<Masquage> masquages) {
        List<String> visibles = new ArrayList<>();
        for (Map.Entry<String, Document> ecran : documents.entrySet()) {
            boolean masque = masquages.stream()
                    .anyMatch(masquage -> classes.contains(masquage.habillage())
                            && masqueSur(ecran.getValue(), masquage));
            if (!masque) {
                visibles.add(ecran.getKey());
            }
        }
        return visibles;
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

    /**
     * Les couples a confronter : une action d'un cote, un role domine de l'autre, meme ecran,
     * capables de s'afficher ensemble.
     *
     * <p>ADR-0043 : le cote domine n'est plus le seul {@code sortie}. Un point d'entree permanent y
     * entre par la meme porte et pour la meme raison - il ne fait pas avancer le jeu affiche - et
     * c'est ce qui l'empeche d'etre une valeur de role par laquelle on echappe a ADR-0017.
     */
    private static List<int[]> appariements(List<Etat> etats) {
        List<int[]> couples = new ArrayList<>();
        for (int action = 0; action < etats.size(); action++) {
            if (!ACTION.equals(etats.get(action).role())) {
                continue;
            }
            for (int domine = 0; domine < etats.size(); domine++) {
                Etat candidate = etats.get(domine);
                if (!ROLES_DOMINES.contains(candidate.role())
                        || !candidate.ecran().equals(etats.get(action).ecran())
                        || !sAffichentEnsemble(etats.get(action), candidate)) {
                    continue;
                }
                couples.add(new int[] {action, domine});
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
        String prefixe = "ecran " + etat.source() + " : " + etat.etiquette();
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

    /**
     * ADR-0043, la clause qui empeche le troisieme role d'etre une porte de sortie de cette regle :
     * tout role domine <b>reellement porte par un bouton du depot</b> doit etre confronte au moins
     * une fois. Un role qu'aucun bouton ne porte n'est pas juge - il n'y a rien a juger ; un role
     * porte et jamais confronte est un bouton qui a echappe a ADR-0017 en changeant un attribut,
     * c'est-a-dire exactement le desamorcage qu'ADR-0038 avait nomme comme le moyen le moins cher.
     *
     * <p>La population est derivee du balisage, jamais enumeree ici : un quatrieme role ajoute
     * demain a {@code ROLES_DOMINES} entre dans cette clause sans que personne y pense.
     */
    private static List<String> rolesDominesJamaisConfrontes(List<Etat> etats, List<int[]> couples) {
        Set<String> confrontes = new LinkedHashSet<>();
        for (int[] couple : couples) {
            confrontes.add(etats.get(couple[1]).role());
        }
        List<String> defauts = new ArrayList<>();
        for (String role : new TreeSet<>(ROLES_DOMINES)) {
            boolean porte = etats.stream().anyMatch(etat -> role.equals(etat.role()));
            if (porte && !confrontes.contains(role)) {
                defauts.add("le role \"" + role + "\" est porte par un bouton du depot mais n'est "
                        + "confronte a aucune action : ADR-0017 ne le juge donc pas, et changer un "
                        + "attribut suffirait a sortir n'importe quel bouton de cette regle");
            }
        }
        return defauts;
    }

    private static String enRem(double valeur) {
        return String.format(Locale.FRENCH, "%.2f", valeur) + UNITE;
    }

    static void verifierQueLActionNeDeclarePasMoinsQueLaSortie(Path racineDesGabarits,
                                                              Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        List<Etat> etats = etats(racineDesGabarits, masquages(racineDesFeuilles), defauts);

        List<Boite> boites = etats.stream()
                .map(etat -> new Boite(etat, COMPOSANTES.stream()
                        .map(composante -> mesure(maison, etat, composante, defauts))
                        .toList()))
                .toList();

        List<int[]> couples = appariements(etats);
        for (int[] couple : couples) {
            Boite action = boites.get(couple[0]);
            Boite domine = boites.get(couple[1]);
            for (int rang = 0; rang < COMPOSANTES.size(); rang++) {
                Optional<Double> valeurAction = action.valeurs().get(rang);
                Optional<Double> valeurDominee = domine.valeurs().get(rang);
                if (valeurAction.isEmpty() || valeurDominee.isEmpty()
                        || valeurAction.get() >= valeurDominee.get()) {
                    continue;
                }
                defauts.add("ecran " + action.etat().ecran() + " : "
                        + designerLeRole(domine.etat().role()) + " " + domine.etat().etiquette()
                        + " declare " + COMPOSANTES.get(rang).nom() + " a " + enRem(valeurDominee.get())
                        + " quand l'action " + action.etat().etiquette() + " se contente de "
                        + enRem(valeurAction.get()) + " ; sur un ecran ou l'enfant peut encore jouer, ce "
                        + "qui appelle la main doit etre ce qui fait avancer le jeu, jamais ce qui en "
                        + "sort");
            }
        }
        if (couples.isEmpty()) {
            defauts.add("aucun couple action/sortie n'a ete confronte : cette regle ne prouverait rien");
        }
        defauts.addAll(rolesDominesJamaisConfrontes(etats, couples));

        assertThat(defauts)
                .as("ADR-0017 : sur un meme ecran, un bouton de sortie ne declare aucune composante de "
                        + "boite superieure a celle d'un bouton d'action, et toute composante comparee "
                        + "est declaree par une regle maison")
                .isEmpty();
    }

    static List<String> couplesConfrontes(Path racineDesGabarits, Path racineDesFeuilles) {
        List<Etat> etats = etats(racineDesGabarits, masquages(racineDesFeuilles), new LinkedHashSet<>());
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
     * S10, remplace {@code surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie}
     * (epinglage du couple sortie/"Encore !", supprime le meme jour). <b>Motif ecrit du
     * remplacement</b>, exige par ADR-0013 : la disparition de l'ecran intermediaire (arbitrage de
     * mecanique de nadia-prof) absorbe la reponse juste comme la fausse dans le cycle de manche ;
     * {@code fragments/retour.html} ne rend plus qu'un seul etat, la fin de partie, et "Encore !" a
     * disparu avec {@code GET /parties/{jeuId}/manche}, son seul appelant. L'ecran de retour ne
     * porte donc plus aucun bouton {@code data-role="action"} : le couple que le lot Bootstrap avait
     * inverse ne peut plus etre reconstitue, parce que son second terme n'existe plus. Maintenir
     * l'epinglage sur ce couple aurait exige de mesurer un ecran que l'enfant ne rencontre plus - le
     * defaut que la retro de s05 a sanctionne deux fois.
     *
     * <p>Ce qui reste a garder, et que ce test garde desormais : que l'ecran de retour ne confronte
     * plus jamais rien a sa sortie (elle y est seule, {@code .sortie-pleine}, S3-6), et que l'ecran
     * de jeu, lui, continue de confronter la reponse a la sortie - la preuve que la disparition du
     * premier couple ne vide pas silencieusement la liste entiere.</p>
     */
    @Test
    void surLEcranDeRetourAucuneActionNEstPlusConfronteeALaSortieDepuisLaDisparitionDEncore() {
        List<String> couples = couplesConfrontes(RACINE_PRODUCTION, RACINE_PRODUCTION);

        assertThat(couples)
                .as("l'ecran de retour ne porte plus aucun bouton d'action : sa sortie n'y est "
                        + "confrontee a rien, ni a \"Encore !\" ni a un autre")
                .noneMatch(couple -> couple.contains("templates/fragments/retour.html"));
        assertThat(couples)
                .as("la sortie de fin de partie n'a aucun bouton d'action a concurrencer sur son ecran")
                .noneMatch(couple -> couple.contains("sortie-pleine"));
        assertThat(couples)
                .as("l'ecran de jeu confronte toujours la reponse a la sortie")
                .anyMatch(couple -> couple.contains(".btn.proposition"));
    }

    /**
     * S14-2 (BARRE-7, DEC-0033) : retiree, remplace
     * {@code leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil}. <b>Motif ecrit de la
     * suppression</b>, exige par ADR-0013 : cet epinglage affirmait qu'un couple
     * {@code .btn.demarrer "Jouer"} contre {@code .btn.lien-accueil "Les jeux"} existait sur
     * l'accueil. Le lien de l'en-tete est retire de tout gabarit par S14-2 (le lien "Les jeux" de
     * l'en-tete n'est pas remplace par un comportement neuf) : aucun bouton {@code .lien-accueil}
     * n'existe plus nulle part, donc ce couple ne peut plus se former, et sa seconde assertion
     * (le lien n'accompagne plus les actions des ecrans de jeu) devient vraie a vide, sur un role
     * qu'aucun bouton ne porte. Maintenir l'epinglage aurait exige de mesurer un bouton que
     * l'enfant ne rencontre plus - la meme famille de defaut que la retro de s05 a sanctionne, et
     * que la suppression de 2026-08-07 dans ce meme fichier avait deja fermee une fois pour le
     * lien "Encore !".
     *
     * <p>Ce qui reste garde, et par quoi : le role {@code sortie}, lui, continue d'exister
     * (sortie-pleine, retour-accueil) et reste confronte a des actions ailleurs
     * ({@code aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran}) ; le role {@code entree} des puces
     * de niveau prend desormais seul la place de sujet confronte a {@code .demarrer} sur
     * l'accueil, sans epinglage nomme - {@code rolesDominesJamaisConfrontes} suffit a garantir
     * qu'il ne peut pas se derober silencieusement (ADR-0043).</p>
     *
     * <p><b>Propose pour confirmation ecrite de theo-archi (via sofia-sm), pas tranche seul</b> :
     * ce retrait suit le patron deja etabli deux fois dans ce fichier pour un epinglage dont le
     * sujet a disparu de la production, mais il n'a pas encore ete relu par lui.</p>
     */

    /**
     * L'en-tete permanent de S6-1 pose un lien de sortie hors de la zone echangee : il s'affiche
     * avec les boutons de tous les ecrans, y compris ceux qui vivent dans un autre fichier. Ce jeu
     * fautif est construit pour que la comparaison au sein d'un fichier reste verte - la sortie y
     * est plus petite que l'action ecrite a cote d'elle - et que seule la traversee de la frontiere
     * la rende rouge. Un garde-fou qui n'aurait pas ferme la limite 5 d'ADR-0017 passerait ici.
     */
    @Test
    void uneSortieDHabillageQuiDepasseLActionDUnAutreFichierFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("entete-permanente-plus-grosse");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ecran templates/fragments/etape.html : la sortie .sortir "
                        + "\"Sortir\" declare taille de police a 1,50rem quand l'action .continuer "
                        + "\"Continuer\" se contente de 1,25rem");
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

    /**
     * ADR-0043 : le troisieme role est domine comme la sortie, et il l'est <b>par la comparaison</b>
     * et non par la bonne foi. Le jeu fautif reprend la boite que {@code ines-design} a chiffree pour
     * la barre de niveaux, a son rembourrage pres ; la sortie du meme habillage y reste plus petite
     * que l'action, donc seul le point d'entree est en cause - un garde-fou qui n'aurait etendu que
     * le vocabulaire, sans etendre l'appariement, resterait vert ici.
     */
    @Test
    void unPointDEntreePermanentQuiDepasseLActionFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("entree-permanente-plus-grosse");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le point d'entree .puce-niveau \"CP\" declare rembourrage "
                        + "haut et bas a 1,00rem quand l'action .continuer \"Continuer\" se contente "
                        + "de 0,75rem");
    }

    /**
     * ADR-0043, l'autre bout de la meme propriete : un role domine qui existe dans le balisage et
     * que la regle ne confronte a rien doit etre un echec, sans quoi le troisieme role serait une
     * porte de sortie d'ADR-0017 plutot qu'une entree dedans.
     */
    @Test
    void unPointDEntreeQueRienNeConfronteFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("entree-jamais-confrontee");
        assertThatThrownBy(() -> verifierQueLActionNeDeclarePasMoinsQueLaSortie(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le role \"entree\" est porte par un bouton du depot mais n'est "
                        + "confronte a aucune action");
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
     * S10 : retiree. Troisieme occurrence, lot Bootstrap (commit 6c83c97) - le rembourrage maison
     * etait retire du bloc des boutons pleins au profit de celui de .btn, mais pas de
     * .retour-accueil qui n'etait pas dans ce bloc ; le bouton qui quittait redevenait 30 % plus
     * grand que celui qui continuait. Cette preuve de morsure rejouait la feuille fautive de ce
     * commit contre les <b>gabarits de production</b>, en s'appuyant sur le fait que "Encore !"
     * existait encore dans {@code fragments/retour.html}.
     *
     * <p><b>Motif ecrit de la suppression</b>, exige par ADR-0013 : la disparition de l'ecran
     * intermediaire (arbitrage de mecanique de nadia-prof) retire "Encore !" des gabarits de
     * production - {@code fragments/retour.html} ne rend plus qu'un seul etat, la fin de partie, et
     * ce bouton n'a plus aucun appelant. La feuille fautive de ce commit ne peut donc plus rencontrer
     * l'element qu'elle visait : rejouee contre les gabarits d'aujourd'hui, elle ne reproduit plus la
     * regression historique, elle en reproduirait une autre (le rembourrage manquant de
     * {@code .btn.sortie-pleine} sur l'ecran de retour), qui n'est pas celle que ce test nommait et
     * documentait. Garder l'epinglage tel quel l'aurait laisse rouge pour un motif different de celui
     * qu'il annoncait - une preuve de morsure qui ment sur ce qu'elle prouve.
     *
     * <p>La regle generale ({@code aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran}) reste gardee par
     * les deux autres rejeux de regressions reelles ci-dessous
     * ({@code leRejeuDuRefusDeSDeuxFaitEchouerLeGardeFou},
     * {@code leRejeuDeLEtatAccepteEnSDeuxResteVert}) et par les deux objets neufs. Aucune regression
     * de la forme exacte que ce test nommait n'est possible aujourd'hui, puisque son sujet n'existe
     * plus.</p>
     */

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
