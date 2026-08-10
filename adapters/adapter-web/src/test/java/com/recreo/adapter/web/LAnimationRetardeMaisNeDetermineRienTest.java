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
import java.util.Set;
import java.util.TreeSet;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.feuillesMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.sansCommentaires;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0026 : une animation retarde ce que l'enfant voit ; elle ne determine rien. Ce qui s'affiche,
 * dans quel ordre, laquelle des propositions est juste et le fait que l'enfant puisse repondre sont
 * decides par le serveur avant que le fragment ne le quitte (ADR-0008). La ligne est tracee sur une
 * propriete du document servi : <b>il reste jouable sans CSS et sans JavaScript</b>.
 *
 * <p>Le nom de cette classe dit ce qu'elle verifie et pas plus. Elle ne fait tourner aucune
 * animation et ne mesure aucun mouvement : un mouvement est une propriete du rendu, qu'aucun
 * automate de ce depot ne produit (ADR-0025). Ce qui est lu ici est <b>declaratif</b> - les regles
 * que la feuille maison ecrit et les attributs que les gabarits posent - et les trois seules choses
 * qu'elle interdit reellement sont : une animation qui ne finit jamais, une animation sans repli de
 * mouvement, et un etat permanent qui rend une proposition inactionnable. Que la duree du defilement
 * convienne a un enfant de CP appartient a {@code nadia-prof} ; sa forme visuelle appartient a
 * {@code ines-design}.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les feuilles viennent du balayage de {@code static/} hors
 *       {@code static/vendor/} (ADR-0014), les gabarits du balayage de {@code templates/}, les
 *       classes d'action des attributs des elements {@code [data-role=action]} eux-memes. Aucune
 *       liste de selecteurs ni de classes n'est ecrite ici comme objet a controler ;</li>
 *   <li><b>derivation totale</b> : une animation dont le repli de mouvement n'a pas la forme du
 *       contrat n'est pas lue de travers, elle n'est pas lue du tout, et le garde-fou echoue faute
 *       de repli. Le doute joue toujours dans le sens du controle : une classe surcollectee produit
 *       une verification de plus, jamais une de moins ;</li>
 *   <li><b>mise a l'epreuve</b> : quatre jeux fautifs neufs, un par clause, plus l'epinglage de
 *       l'etat reel du depot au moment ou cette regle est ecrite (aucune animation en production).
 *       Cet epinglage est ce qui empeche les deux clauses d'animation de rester vertes a vide sans
 *       que personne ne le sache : leur population de production est vide aujourd'hui, et le jour ou
 *       elle cesse de l'etre, c'est ce test-la qui le dit.</li>
 * </ul>
 */
class LAnimationRetardeMaisNeDetermineRienTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LAnimationRetardeMaisNeDetermineRienTest");

    private static final String ATTRIBUT_DE_ROLE = "data-role";
    private static final String ACTION = "action";

    /** Les attributs par lesquels une classe arrive au rendu, en clair ou par Thymeleaf. */
    private static final List<String> PORTEURS_DE_CLASSE =
            List.of("class", "th:class", "th:classappend");

    /**
     * Les proprietes dont la valeur, declaree de facon <b>permanente</b>, rend un bouton
     * inactionnable ou invisible. Refusees sur toute regle maison qui s'applique a une classe
     * d'action, hors {@code @keyframes} - une animation a le droit de traverser ces etats, une regle
     * permanente non : elle laisserait la proposition hors d'atteinte quelle que soit l'animation.
     */
    private static final List<String[]> ETATS_INACTIONNABLES = List.of(
            new String[] {"display", "none"},
            new String[] {"visibility", "hidden"},
            new String[] {"pointer-events", "none"},
            new String[] {"opacity", "0"});

    /** Les attributs par lesquels un gabarit retirerait a l'enfant le droit d'appuyer. */
    private static final List<String> ATTRIBUTS_DE_DESACTIVATION = List.of("disabled", "aria-disabled");

    private static final String CONTEXTE_DE_MOUVEMENT_REDUIT = "prefers-reduced-motion";
    private static final String IMAGES_CLES = "@keyframes";

    /** La valeur qu'un repli de mouvement doit declarer, litteralement. */
    private static final String REPLI_ATTENDU = "none";

    /** Les proprietes par lesquelles une regle declare qu'elle anime quelque chose. */
    private static final List<String> DECLARATIONS_D_ANIMATION = List.of(
            "animation", "animation-name", "animation-duration", "animation-iteration-count");

    /**
     * ADR-0027 : le marqueur qu'un mouvement exige. Pose par le serveur sur l'ecran de jeu quand
     * l'enfant a presse quelque chose pour l'obtenir, absent quand l'ecran arrive par un chargement de
     * page. Une animation qui ne le nomme pas tournerait aussi sur la reprise silencieuse de S1-4.
     */
    private static final String MARQUEUR_DE_GESTE = "[data-arrivee=par-un-geste]";

    /**
     * Les bornes de duree de {@code nadia-prof} (arbitrage du 2026-08-10, section 2), en secondes. Ce
     * n'etait pas gardable quand ADR-0026 a ete ecrit, parce qu'aucun chiffre n'existait ; il l'est
     * depuis qu'elle en a donne un. Le plafond porte sur duree + retard, c'est-a-dire sur ce que
     * l'enfant attend entre son appui et l'arret de la derniere colonne.
     */
    private static final double DUREE_MINIMALE_S = 0.3;
    private static final double DUREE_MAXIMALE_S = 1.2;
    private static final double RETARD_MAXIMAL_S = 0.25;

    /**
     * ADR-0027, propriete "jamais un autre mot" : {@code content} donne du texte a lire sur un
     * pseudo-element. La chaine vide est de la decoration pure et reste autorisee - la croix du signal
     * juste/faux l'utilise deja ; une chaine non vide est un mot que l'enfant lit.
     */
    private static final String PROPRIETE_DE_TEXTE = "content";

    /** Une regle CSS, avec la pile de regles-@ dans laquelle elle est ecrite. */
    private record Regle(String fichier, String contexte, String selecteur, String corps) {

        String origine() {
            return fichier + " : " + (contexte.isEmpty() ? selecteur : contexte + " { " + selecteur);
        }

        boolean dansDesImagesCles() {
            return contexte.contains(IMAGES_CLES);
        }

        boolean sousMouvementReduit() {
            return contexte.contains(CONTEXTE_DE_MOUVEMENT_REDUIT);
        }
    }

    /**
     * Les regles de toutes les feuilles maison, avec leur contexte de regles-@. Lecture caractere par
     * caractere avec un compteur de profondeur d'accolades : {@code AssertionsWebTest} interdit
     * l'expression reguliere dans ce module (ADR-0008), et une regle-@ imbriquee n'est de toute facon
     * pas une chose qu'une expression reguliere lit correctement.
     */
    private static List<Regle> reglesMaison(Path racine) {
        List<Regle> regles = new ArrayList<>();
        for (Path feuille : feuillesMaison(racine)) {
            String nom = feuille.getFileName().toString();
            String source = sansCommentaires(lire(feuille));
            List<String> pile = new ArrayList<>();
            StringBuilder prelude = new StringBuilder();
            int position = 0;
            while (position < source.length()) {
                char caractere = source.charAt(position);
                if (caractere == '}') {
                    if (!pile.isEmpty()) {
                        pile.remove(pile.size() - 1);
                    }
                    prelude.setLength(0);
                    position++;
                } else if (caractere != '{') {
                    prelude.append(caractere);
                    position++;
                } else {
                    String entete = prelude.toString().strip();
                    prelude.setLength(0);
                    position++;
                    if (entete.startsWith("@")) {
                        pile.add(entete);
                        continue;
                    }
                    int fin = source.indexOf('}', position);
                    String corps = source.substring(position, fin < 0 ? source.length() : fin);
                    position = fin < 0 ? source.length() : fin + 1;
                    String contexte = String.join(" ", pile);
                    for (String selecteur : entete.split(",")) {
                        regles.add(new Regle(nom, contexte, selecteur.strip(), corps));
                    }
                }
            }
        }
        return regles;
    }

    private static boolean declare(Regle regle, String propriete, String valeurAttendue) {
        int position = debutDeDeclaration(regle.corps(), propriete);
        return position >= 0
                && valeurDeclaree(regle.corps(), position, propriete).strip().equals(valeurAttendue);
    }

    private static boolean declareUneAnimation(Regle regle) {
        return DECLARATIONS_D_ANIMATION.stream()
                .anyMatch(propriete -> debutDeDeclaration(regle.corps(), propriete) >= 0);
    }

    /** Les regles qui animent quelque chose : la population des deux clauses de mouvement. */
    private static List<Regle> animations(Path racine) {
        return reglesMaison(racine).stream()
                .filter(regle -> !regle.dansDesImagesCles())
                .filter(regle -> !regle.sousMouvementReduit())
                .filter(LAnimationRetardeMaisNeDetermineRienTest::declareUneAnimation)
                .toList();
    }

    /**
     * Les classes qu'un element {@code [data-role=action]} peut porter au rendu, relevees dans les
     * gabarits. Les expressions Thymeleaf sont decoupees sur tout ce qui n'est pas un caractere de
     * nom de classe : la surcollecte est voulue et sans danger - elle ajoute des verifications, elle
     * n'en retire aucune, et son mode de defaillance est un rouge, jamais un vert silencieux.
     */
    static Set<String> classesDesActions(Path racine) {
        Set<String> classes = new TreeSet<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element action : document.select("[" + ATTRIBUT_DE_ROLE + "=" + ACTION + "]")) {
                for (String porteur : PORTEURS_DE_CLASSE) {
                    classes.addAll(motsDeClasse(action.attr(porteur)));
                }
            }
        }
        return classes;
    }

    /** La meme chaine sans aucun blanc : un selecteur d'attribut peut etre ecrit espace ou non. */
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

    /** Les mots d'une valeur declaree, separes par des blancs. Jamais par expression reguliere. */
    private static List<String> mots(String valeur) {
        List<String> mots = new ArrayList<>();
        StringBuilder mot = new StringBuilder();
        for (int position = 0; position <= valeur.length(); position++) {
            char caractere = position < valeur.length() ? valeur.charAt(position) : ' ';
            if (Character.isWhitespace(caractere)) {
                if (mot.length() > 0) {
                    mots.add(mot.toString());
                }
                mot.setLength(0);
            } else {
                mot.append(caractere);
            }
        }
        return mots;
    }

    private static List<String> motsDeClasse(String texte) {
        List<String> mots = new ArrayList<>();
        StringBuilder mot = new StringBuilder();
        for (int position = 0; position <= texte.length(); position++) {
            char caractere = position < texte.length() ? texte.charAt(position) : ' ';
            if (Character.isLetterOrDigit(caractere) || caractere == '-' || caractere == '_') {
                mot.append(caractere);
            } else {
                if (mot.length() > 0) {
                    mots.add(mot.toString());
                }
                mot.setLength(0);
            }
        }
        return mots;
    }

    /** Les classes qu'un selecteur nomme, sans juger sa structure : tout ce qui suit un point. */
    private static Set<String> classesDuSelecteur(String selecteur) {
        Set<String> classes = new LinkedHashSet<>();
        int position = 0;
        while ((position = selecteur.indexOf('.', position)) >= 0) {
            List<String> mots = motsDeClasse(selecteur.substring(position + 1));
            if (!mots.isEmpty()) {
                classes.add(mots.get(0));
            }
            position++;
        }
        return classes;
    }

    /**
     * Clause 1 - une animation qui ne s'arrete jamais cesse de retarder et se met a determiner :
     * l'enfant n'atteint jamais l'etat ou la proposition est lisible et choisissable.
     */
    static void verifierQueChaqueAnimationSeTermine(Path racine) {
        List<String> defauts = new ArrayList<>();
        for (Regle regle : animations(racine)) {
            String corps = regle.corps().toLowerCase(Locale.ROOT);
            if (corps.contains("infinite")) {
                defauts.add(regle.origine() + " : cette animation est declaree infinie. Un rouleau qui "
                        + "ne s'arrete jamais n'est plus un retard, c'est un refus de reponse - "
                        + "l'enfant n'atteint jamais l'etat que le serveur a decide (ADR-0026)");
            }
        }
        assertThat(defauts)
                .as("ADR-0026 : toute animation declaree par une feuille maison se termine")
                .isEmpty();
    }

    /**
     * Clause 2 - un enfant qui a besoin de ne pas voir bouger l'ecran doit pouvoir jouer. La forme du
     * repli est contractuelle, comme le masquage d'ADR-0022 : le meme selecteur, sous
     * {@code @media (prefers-reduced-motion: reduce)}, declarant {@code animation: none}. Une forme
     * approchante n'est pas lue de travers, elle n'est pas lue du tout.
     */
    static void verifierQueChaqueAnimationAUnRepliDeMouvement(Path racine) {
        List<Regle> replis = reglesMaison(racine).stream()
                .filter(Regle::sousMouvementReduit)
                .filter(regle -> declare(regle, "animation", REPLI_ATTENDU))
                .toList();
        Set<String> selecteursReplies = new LinkedHashSet<>();
        replis.forEach(regle -> selecteursReplies.add(regle.selecteur()));

        List<String> defauts = new ArrayList<>();
        for (Regle regle : animations(racine)) {
            if (!selecteursReplies.contains(regle.selecteur())) {
                defauts.add(regle.origine() + " : cette animation n'a aucun repli de mouvement. La "
                        + "feuille maison doit ecrire @media (" + CONTEXTE_DE_MOUVEMENT_REDUIT
                        + ": reduce) { " + regle.selecteur() + " { animation: " + REPLI_ATTENDU
                        + " } } - un enfant qui a besoin d'un ecran immobile doit pouvoir jouer "
                        + "(ADR-0026)");
            }
        }
        assertThat(defauts)
                .as("ADR-0026 : toute animation declaree par une feuille maison est neutralisee sous "
                        + CONTEXTE_DE_MOUVEMENT_REDUIT + ": reduce")
                .isEmpty();
    }

    /**
     * Clause 3 - aucune regle permanente ne rend une proposition inactionnable. C'est la clause qui
     * tient la frontiere d'ADR-0008 du cote du CSS : une animation peut traverser un etat cache, une
     * regle qui n'est pas dans des {@code @keyframes} ne le traverse pas, elle y reste.
     */
    static void verifierQuAucuneRegleNeRendUneActionInactionnable(Path racine) {
        Set<String> classesDActions = classesDesActions(racine);
        assertThat(classesDActions)
                .as("ADR-0003 : cette clause ne prouve rien si aucun element [%s=%s] ne porte de "
                        + "classe dans les gabarits de %s", ATTRIBUT_DE_ROLE, ACTION, racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (Regle regle : reglesMaison(racine)) {
            if (regle.dansDesImagesCles()) {
                continue;
            }
            Set<String> visees = classesDuSelecteur(regle.selecteur());
            visees.retainAll(classesDActions);
            if (visees.isEmpty()) {
                continue;
            }
            for (String[] etat : ETATS_INACTIONNABLES) {
                if (declare(regle, etat[0], etat[1])) {
                    defauts.add(regle.origine() + " : cette regle declare " + etat[0] + ": " + etat[1]
                            + " sur " + visees + ", des classes que porte un bouton d'action. Ce n'est "
                            + "pas une etape d'animation, c'est un etat permanent : la proposition "
                            + "resterait hors d'atteinte de l'enfant (ADR-0026)");
                }
            }
        }
        assertThat(defauts)
                .as("ADR-0026 : aucune regle maison permanente ne rend inactionnable un bouton que "
                        + "l'enfant doit pouvoir presser")
                .isEmpty();
    }

    /**
     * Clause 4 - le serveur decide seul quand l'enfant peut repondre, et il ne le lui refuse jamais
     * par un attribut de gabarit. Un ecran qui n'attend plus de reponse ne rend pas un bouton
     * desactive : il ne rend pas ce bouton du tout (ADR-0020, ecran de fin de partie).
     */
    static void verifierQuAucunGabaritNeDesactiveUnBouton(Path racine) {
        List<Element> porteursDeRole = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.select("[" + ATTRIBUT_DE_ROLE + "]")) {
                porteursDeRole.add(element);
                for (String attribut : ATTRIBUTS_DE_DESACTIVATION) {
                    if (element.hasAttr(attribut) || element.hasAttr("th:" + attribut)) {
                        defauts.add(gabarit + " : <" + element.tagName() + "> \"" + element.text()
                                + "\" porte " + attribut + ". Un bouton desactive est un bouton dont "
                                + "le client decide s'il est jouable ; le serveur, lui, ne rend pas "
                                + "un bouton dont ce n'est pas l'heure (ADR-0026)");
                    }
                }
            }
        }
        assertThat(porteursDeRole)
                .as("ADR-0003 : cette clause ne prouve rien si aucun element ne porte %s dans les "
                        + "gabarits de %s", ATTRIBUT_DE_ROLE, racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0026 : aucun gabarit ne desactive un bouton porteur de %s", ATTRIBUT_DE_ROLE)
                .isEmpty();
    }

    /**
     * Clause 5 (ADR-0027) - un mouvement n'est autorise que sur un ecran que l'enfant a demande. Le
     * serveur pose le marqueur ; cette clause verifie que la feuille le lit, c'est-a-dire qu'aucune
     * animation n'est atteignable en dehors de lui. C'est la moitie CSS du garde-fou dont
     * {@code LEcranDeJeuDeclareCommentIlEstArriveTest} tient la moitie serveur.
     */
    static void verifierQueChaqueAnimationExigeLeMarqueurDeGeste(Path racine) {
        List<String> defauts = new ArrayList<>();
        for (Regle regle : animations(racine)) {
            if (!sansEspaces(regle.selecteur()).contains(MARQUEUR_DE_GESTE)) {
                defauts.add(regle.origine() + " : cette animation ne nomme pas \"" + MARQUEUR_DE_GESTE
                        + "\" dans son selecteur, elle tournerait donc aussi quand l'ecran de jeu arrive "
                        + "sans geste de l'enfant - la reprise silencieuse de S1-4, que accueil.html "
                        + "rend sans aucun echange htmx (ADR-0027)");
            }
        }
        assertThat(defauts)
                .as("ADR-0027 : toute animation maison n'est atteignable que sous \"%s\"",
                        MARQUEUR_DE_GESTE)
                .isEmpty();
    }

    /**
     * Clause 6 (ADR-0027) - la duree tient dans la barre chiffree de {@code nadia-prof}. ADR-0026
     * declarait cette propriete non gardable, et il avait raison tant qu'aucun seuil n'existait : une
     * clause devient gardable le jour ou son destinataire donne un chiffre, et c'est ce qui vient
     * d'arriver. Ce qui lui reste est ce qu'une feuille de style ne dit pas - la courbe, le rebond, le
     * lien percu avec le geste.
     */
    static void verifierQueChaqueAnimationTientDansLaBarreDeDuree(Path racine) {
        List<String> defauts = new ArrayList<>();
        for (Regle regle : animations(racine)) {
            List<Double> temps = secondesDeclarees(regle, defauts);
            if (temps.isEmpty()) {
                continue;
            }
            double duree = temps.get(0);
            double retard = temps.size() > 1 ? temps.get(1) : 0;
            if (duree < DUREE_MINIMALE_S) {
                defauts.add(regle.origine() + " : duree de " + duree + "s, sous le plancher de "
                        + DUREE_MINIMALE_S + "s. En dessous ce n'est plus un defilement, c'est un "
                        + "clignotement (nadia-prof, arbitrage du 2026-08-10, section 2)");
            }
            if (retard > RETARD_MAXIMAL_S) {
                defauts.add(regle.origine() + " : retard de " + retard + "s, au-dela du decalage maximal "
                        + "de " + RETARD_MAXIMAL_S + "s entre la premiere et la derniere colonne");
            }
            if (duree + retard > DUREE_MAXIMALE_S) {
                defauts.add(regle.origine() + " : duree + retard = " + (duree + retard) + "s, au-dela du "
                        + "plafond de " + DUREE_MAXIMALE_S + "s entre l'appui de l'enfant et l'arret de "
                        + "la derniere colonne. Une partie compte dix manches : ce temps est paye dix "
                        + "fois (nadia-prof, arbitrage du 2026-08-10, section 2)");
            }
        }
        assertThat(defauts)
                .as("ADR-0027 : toute animation maison tient entre %ss et %ss, retard compris",
                        DUREE_MINIMALE_S, DUREE_MAXIMALE_S)
                .isEmpty();
    }

    /**
     * Les temps declares par une regle d'animation, en secondes, dans l'ordre du raccourci CSS : duree
     * puis retard. Une duree qu'on ne sait pas lire est un defaut et non une valeur ignoree
     * (ADR-0013 obligation 2) : on ne confronte pas a une barre un chiffre qu'on ne lit pas.
     */
    private static List<Double> secondesDeclarees(Regle regle, List<String> defauts) {
        List<Double> temps = new ArrayList<>();
        int position = debutDeDeclaration(regle.corps(), "animation");
        List<String> mots = position >= 0
                ? mots(valeurDeclaree(regle.corps(), position, "animation"))
                : List.of();
        for (String mot : mots) {
            if (mot.endsWith("ms")) {
                temps.add(Double.parseDouble(mot.substring(0, mot.length() - 2)) / 1000);
            } else if (mot.endsWith("s")) {
                temps.add(Double.parseDouble(mot.substring(0, mot.length() - 1)));
            }
        }
        for (String propriete : List.of("animation-duration", "animation-delay")) {
            int explicite = debutDeDeclaration(regle.corps(), propriete);
            if (explicite < 0) {
                continue;
            }
            String valeur = valeurDeclaree(regle.corps(), explicite, propriete).strip();
            double secondes = valeur.endsWith("ms")
                    ? Double.parseDouble(valeur.substring(0, valeur.length() - 2)) / 1000
                    : Double.parseDouble(valeur.substring(0, valeur.length() - 1));
            int rang = "animation-duration".equals(propriete) ? 0 : 1;
            while (temps.size() <= rang) {
                temps.add(0.0);
            }
            temps.set(rang, secondes);
        }
        if (temps.isEmpty()) {
            defauts.add(regle.origine() + " : cette regle anime quelque chose sans declarer de duree "
                    + "lisible ; une duree qu'on ne lit pas ne se confronte a aucune barre (ADR-0027)");
        }
        return temps;
    }

    /**
     * Clause 7 (ADR-0027) - aucune regle maison ne donne a lire un mot que le serveur n'a pas envoye.
     * {@code content} est la seule propriete CSS qui fabrique du texte ; la chaine vide reste
     * autorisee, parce que c'est de la decoration pure et que la croix du signal juste/faux l'emploie
     * deja. Une chaine non vide est un mot, et un mot leurre devant un enfant qui apprend
     * l'orthographe est refuse (nadia-prof, arbitrage du 2026-08-10, section 2).
     */
    static void verifierQuAucuneRegleNeFabriqueDuTexte(Path racine) {
        List<Regle> porteuses = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (Regle regle : reglesMaison(racine)) {
            int position = debutDeDeclaration(regle.corps(), PROPRIETE_DE_TEXTE);
            if (position < 0) {
                continue;
            }
            porteuses.add(regle);
            String valeur = valeurDeclaree(regle.corps(), position, PROPRIETE_DE_TEXTE).strip();
            if (!valeur.equals("\"\"") && !valeur.equals("''") && !valeur.equals("none")) {
                defauts.add(regle.origine() + " : cette regle fabrique du texte (" + PROPRIETE_DE_TEXTE
                        + ": " + valeur + "). Ce que l'enfant lit vient du serveur, jamais d'une feuille "
                        + "de style : sur un jeu d'homophones, un mot leurre lui ferait voir la forme "
                        + "fausse plus souvent que la juste (ADR-0027)");
            }
        }
        assertThat(porteuses)
                .as("ADR-0003 : cette clause ne prouve rien si aucune regle maison ne declare %s. "
                        + "Deux le font aujourd'hui, sur les deux barres de la croix du signal faux",
                        PROPRIETE_DE_TEXTE)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0027 : aucune regle maison ne donne a lire un mot que le serveur n'a pas envoye")
                .isEmpty();
    }

    @Test
    void chaqueAnimationDeLaFeuilleMaisonSeTermine() {
        verifierQueChaqueAnimationSeTermine(RACINE_PRODUCTION);
    }

    @Test
    void chaqueAnimationExigeLeMarqueurDeGeste() {
        verifierQueChaqueAnimationExigeLeMarqueurDeGeste(RACINE_PRODUCTION);
    }

    @Test
    void chaqueAnimationTientDansLaBarreDeDureeDeNadia() {
        verifierQueChaqueAnimationTientDansLaBarreDeDuree(RACINE_PRODUCTION);
    }

    @Test
    void aucuneRegleMaisonNeFabriqueDuTexte() {
        verifierQuAucuneRegleNeFabriqueDuTexte(RACINE_PRODUCTION);
    }

    @Test
    void chaqueAnimationDeLaFeuilleMaisonAUnRepliDeMouvement() {
        verifierQueChaqueAnimationAUnRepliDeMouvement(RACINE_PRODUCTION);
    }

    @Test
    void aucuneRegleMaisonNeRendUneActionInactionnable() {
        verifierQuAucuneRegleNeRendUneActionInactionnable(RACINE_PRODUCTION);
    }

    @Test
    void aucunGabaritNeDesactiveUnBoutonDAction() {
        verifierQuAucunGabaritNeDesactiveUnBouton(RACINE_PRODUCTION);
    }

    /**
     * Epinglage de l'etat reel du depot le jour ou cette regle est ecrite, exige par ADR-0003 : les
     * deux clauses de mouvement ont une population de production <b>vide</b>, puisque aucune feuille
     * maison n'anime encore quoi que ce soit. Elles sont donc vertes a vide, et ce test est ce qui
     * empeche cet etat de passer pour une preuve. Le jour ou la premiere animation entre, ce test
     * devient rouge et son message dit quoi faire : le supprimer, parce que les deux clauses
     * deviennent alors portantes sur un objet reel.
     *
     * <p>Le plafond de cet epinglage vaut exactement la taille de la dette - zero animation, pas
     * "quelques-unes" (lecon du plafond de dette d'ADR-0016).
     */
    @Test
    void aucuneAnimationNEstEncoreDeclareeEnProduction() {
        assertThat(animations(RACINE_PRODUCTION))
                .as("ADR-0026 : la premiere animation maison vient d'entrer dans le depot. Les deux "
                        + "clauses de mouvement de cette classe cessent d'etre vertes a vide : "
                        + "supprime cet epinglage, elles portent desormais sur un objet reel")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est une feuille que personne n'a inscrite nulle part, ou
     * le rouleau tourne sans fin. C'est la forme exacte que prendrait le defaut le jour ou quelqu'un
     * ecrirait le defilement comme une boucle plutot que comme une arrivee.
     */
    @Test
    void uneAnimationInfinieFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-sans-fin");
        assertThatThrownBy(() -> verifierQueChaqueAnimationSeTermine(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cette animation est declaree infinie");
    }

    @Test
    void uneAnimationSansRepliDeMouvementFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-sans-repli");
        assertThatThrownBy(() -> verifierQueChaqueAnimationAUnRepliDeMouvement(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'a aucun repli de mouvement");
    }

    /**
     * Le meme jeu fautif que ci-dessus, corrige par le seul ajout du repli sous la forme du contrat :
     * il prouve que la clause est satisfiable, et que ce qu'elle exige est bien ce que l'ADR ecrit.
     * Sans ce cas, une clause impossible a satisfaire passerait pour une clause exigeante.
     */
    @Test
    void leMemeJeuFautifCorrigeParUnRepliDeMouvementRedevientVert() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-avec-repli");
        verifierQueChaqueAnimationAUnRepliDeMouvement(racine);
        verifierQueChaqueAnimationSeTermine(racine);
    }

    /**
     * L'objet neuf est une regle permanente qui cache la proposition - la forme que prendrait une
     * animation ecrite a l'envers, ou l'etat de depart est pose en dur sur le bouton au lieu de vivre
     * dans les {@code @keyframes}. C'est le defaut qui rendrait l'ecran injouable sans qu'aucun test
     * de comportement serveur ne bronche, puisque le serveur, lui, aurait tout rendu correctement.
     */
    @Test
    void uneRegleQuiCacheLaPropositionFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("proposition-cachee-en-permanence");
        assertThatThrownBy(() -> verifierQuAucuneRegleNeRendUneActionInactionnable(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("c'est un etat permanent");
    }

    /**
     * Le meme etat, mais ecrit dans des {@code @keyframes} : c'est une etape d'animation, et elle est
     * legitime. Ce cas est ce qui empeche la clause 3 d'etre plus large que ce qu'ADR-0026 decide -
     * sans lui, le garde-fou interdirait de faire apparaitre un rouleau, ce qui n'est pas la
     * decision.
     */
    @Test
    void leMemeEtatEcritDansDesImagesClesResteVert() {
        verifierQuAucuneRegleNeRendUneActionInactionnable(
                CONTRE_EXEMPLES.resolve("proposition-cachee-dans-des-images-cles"));
    }

    /**
     * ADR-0027, clause 5. L'objet neuf est une animation ecrite sans le marqueur de geste - la forme
     * exacte que le mecanisme d'ADR-0026 rendait naturelle, puisque son texte faisait demarrer
     * l'animation a l'insertion de l'element sans distinguer par quoi.
     */
    @Test
    void uneAnimationQuiNExigePasLeMarqueurDeGesteFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-sans-marqueur-de-geste");
        assertThatThrownBy(() -> verifierQueChaqueAnimationExigeLeMarqueurDeGeste(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne nomme pas \"[data-arrivee=par-un-geste]\"");
    }

    /**
     * ADR-0027, clause 6, les trois bords de la barre de {@code nadia-prof}. 1,5 s est refuse
     * nommement par son arbitrage ; 0,2 s est sous son plancher ; un retard de 0,7 s depasse le
     * decalage de 0,25 s et fait aussi passer duree + retard au-dela du plafond.
     */
    @Test
    void uneDureeHorsDeLaBarreFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueAnimationTientDansLaBarreDeDuree(
                CONTRE_EXEMPLES.resolve("animation-trop-longue")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au-dela du plafond de 1.2s");
        assertThatThrownBy(() -> verifierQueChaqueAnimationTientDansLaBarreDeDuree(
                CONTRE_EXEMPLES.resolve("animation-trop-courte")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sous le plancher de 0.3s");
        assertThatThrownBy(() -> verifierQueChaqueAnimationTientDansLaBarreDeDuree(
                CONTRE_EXEMPLES.resolve("decalage-trop-grand")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au-dela du decalage maximal de 0.25s");
    }

    /**
     * Relecture adverse des deux bords : la valeur que {@code nadia-prof} <b>conseille</b> doit passer,
     * et c'est ce qui empeche la barre d'etre une interdiction deguisee. Sans ce cas, une inegalite au
     * mauvais sens rejetterait la seule valeur qu'elle recommande, et aucun cas fautif ne le dirait.
     */
    @Test
    void laDureeConseilleeParNadiaEstAcceptee() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-avec-repli");
        verifierQueChaqueAnimationTientDansLaBarreDeDuree(racine);
        assertThat(animations(racine))
                .as("ce jeu declare bien une animation de 0,6s, la valeur conseillee")
                .isNotEmpty();
    }

    /**
     * ADR-0027, clause 7. L'objet neuf est une face de rouleau dont le texte est ecrit dans la feuille
     * de style - la seule facon d'afficher un mot que le serveur n'a pas envoye sans passer par du
     * JavaScript, que la regle 2 d'ADR-0026 ferme deja.
     */
    @Test
    void uneFeuilleQuiFabriqueUnMotFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("feuille-qui-fabrique-du-texte");
        assertThatThrownBy(() -> verifierQuAucuneRegleNeFabriqueDuTexte(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cette regle fabrique du texte");
    }

    @Test
    void unBoutonDesactiveDansUnGabaritFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("action-desactivee");
        assertThatThrownBy(() -> verifierQuAucunGabaritNeDesactiveUnBouton(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("porte disabled");
    }
}
