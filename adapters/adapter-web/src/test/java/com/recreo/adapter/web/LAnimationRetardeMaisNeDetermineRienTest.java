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
import java.util.Optional;
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
 *   <li><b>mise a l'epreuve</b> : des jeux fautifs neufs, un par clause. Un epinglage de l'etat
 *       reel du depot ("aucune animation en production") a vecu ici tant que les deux clauses de
 *       mouvement etaient vertes a vide ; il est supprime depuis S7-1, le rouleau de
 *       {@code .proposition::after} leur donnant un objet reel a controler.</li>
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

    /**
     * Amendement du 2026-08-10 a ADR-0027 section 5 : la propriete qui decoupe geometriquement une
     * boite. Elle ne fabrique aucun texte, et c'est pourquoi la clause 7 ne la voit pas - mais
     * appliquee a l'element qui <b>porte</b> le texte, elle en retranche une partie, et un mot
     * retranche peut etre un autre mot. Sur {@code cm1-homophones}, dont les deux propositions
     * mesurees par ADR-0026 sont {@code sont} et {@code son}, une decoupe laterale transforme la bonne
     * reponse en la fausse.
     */
    private static final String PROPRIETE_DE_DECOUPE = "clip-path";

    /** Les pseudo-elements decoratifs : une boite generee a cote du texte, jamais le texte lui-meme. */
    private static final List<String> PSEUDO_ELEMENTS_DECORATIFS = List.of("::before", "::after");

    /** La propriete par laquelle un retard s'ecrit hors du raccourci {@code animation}. */
    private static final String PROPRIETE_DE_RETARD = "animation-delay";

    /**
     * ADR-0030 : les selecteurs d'image finale d'un {@code @keyframes}. {@code to} et {@code 100%}
     * sont la meme chose ; les deux formes sont lues, aucune n'est privilegiee.
     */
    private static final List<String> IMAGE_FINALE = List.of("to", "100%");

    /** ADR-0030 : la propriete de decoupe, seule dont ce depot compare la geometrie et non le texte. */
    private static final String DECOUPE = "clip-path";

    /**
     * Une regle CSS, avec la pile de regles-@ dans laquelle elle est ecrite. Visible dans le paquet
     * depuis ADR-0030 : {@code LHabillageNeRogneJamaisUnElementPressableTest} lit les memes regles, et
     * un second decoupage de blocs CSS dans le meme module serait une duplication sans motif.
     */
    record Regle(String fichier, String contexte, String selecteur, String corps) {

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
    static List<Regle> reglesMaison(Path racine) {
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
     * Le <b>sujet</b> d'un selecteur, c'est-a-dire son dernier compose : l'element sur lequel la regle
     * s'applique reellement. Les combinateurs sont cherches hors des crochets, parce qu'un selecteur
     * d'attribut peut contenir un blanc ou un {@code ~} qui ne coupe rien.
     */
    private static String sujet(String selecteur) {
        int coupure = -1;
        int profondeur = 0;
        for (int position = 0; position < selecteur.length(); position++) {
            char caractere = selecteur.charAt(position);
            if (caractere == '[') {
                profondeur++;
            } else if (caractere == ']') {
                profondeur--;
            } else if (profondeur == 0
                    && (Character.isWhitespace(caractere) || caractere == '>' || caractere == '+'
                            || caractere == '~')) {
                coupure = position;
            }
        }
        return selecteur.substring(coupure + 1);
    }

    /**
     * Vrai si la regle porte sur un pseudo-element decoratif et non sur l'element qui affiche le texte
     * du serveur. Seule la forme a deux deux-points est reconnue : une forme heritee a un seul
     * deux-points est refusee plutot que lue charitablement, comme la forme litterale du repli de
     * mouvement (ADR-0026 clause 2) et pour la meme raison.
     */
    private static boolean porteSurUnPseudoElementDecoratif(String selecteur) {
        String sujet = sujet(selecteur);
        return PSEUDO_ELEMENTS_DECORATIFS.stream().anyMatch(sujet::endsWith);
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
        double dureeMaximale = 0;
        for (Regle regle : animations(racine)) {
            List<Double> temps = secondesDeclarees(regle, defauts);
            if (temps.isEmpty()) {
                continue;
            }
            double duree = temps.get(0);
            double retard = temps.size() > 1 ? temps.get(1) : 0;
            dureeMaximale = Math.max(dureeMaximale, duree);
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
        verifierLesRetardsEcritsAPart(racine, dureeMaximale, defauts);
        assertThat(defauts)
                .as("ADR-0027 : toute animation maison tient entre %ss et %ss, retard compris",
                        DUREE_MINIMALE_S, DUREE_MAXIMALE_S)
                .isEmpty();
    }

    /**
     * Le meme plafond, sur les retards qui ne sont pas ecrits dans la regle qui anime.
     *
     * <p><b>Correction du 2026-08-10, sur le garde-fou de ma main et par la faute que ce depot m'a
     * deja apprise.</b> Le jeu fautif {@code decalage-trop-grand} ecrit son retard dans le raccourci
     * {@code animation}, parce que c'est la forme que j'imaginais en l'ecrivant. Ce n'est pas la forme
     * que le monde ecrit : un decalage <b>par colonne</b> ne peut pas vivre dans un raccourci partage,
     * il s'ecrit dans une regle par colonne, en {@code animation-delay}. Ces regles-la ne declarent pas
     * {@code animation:} et ne sont donc pas dans la population des animations - la clause ne les
     * voyait pas, et {@code animation-delay: 3s} en production passait vert (mesure : mutation reelle
     * de {@code recreo.css}, 149 cas verts sur 149 avant cette correction).
     *
     * <p>Le plafond est confronte a la <b>somme des maximums</b> - duree la plus longue plus retard le
     * plus long, meme s'ils ne sont pas dans la meme regle. C'est une borne superieure : le doute joue
     * dans le sens du controle (ADR-0013 obligation 2), et c'est exactement ce que le chiffre de
     * {@code nadia-prof} veut dire - le temps entre l'appui de l'enfant et l'arret de la derniere
     * colonne.
     */
    private static void verifierLesRetardsEcritsAPart(
            Path racine, double dureeMaximale, List<String> defauts) {
        double retardMaximal = 0;
        for (Regle regle : reglesMaison(racine)) {
            if (regle.dansDesImagesCles() || regle.sousMouvementReduit()) {
                continue;
            }
            int position = debutDeDeclaration(regle.corps(), PROPRIETE_DE_RETARD);
            if (position < 0) {
                continue;
            }
            Double retard = secondes(
                    valeurDeclaree(regle.corps(), position, PROPRIETE_DE_RETARD), regle, defauts);
            if (retard == null) {
                continue;
            }
            retardMaximal = Math.max(retardMaximal, retard);
            if (retard > RETARD_MAXIMAL_S) {
                defauts.add(regle.origine() + " : retard de " + retard + "s, au-dela du decalage maximal "
                        + "de " + RETARD_MAXIMAL_S + "s entre la premiere et la derniere colonne "
                        + "(nadia-prof, arbitrage du 2026-08-10, section 2)");
            }
        }
        if (dureeMaximale + retardMaximal > DUREE_MAXIMALE_S) {
            defauts.add("duree la plus longue + retard le plus long = " + (dureeMaximale + retardMaximal)
                    + "s, au-dela du plafond de " + DUREE_MAXIMALE_S + "s entre l'appui de l'enfant et "
                    + "l'arret de la derniere colonne (ADR-0027)");
        }
    }

    /**
     * Une duree en secondes, ou null apres avoir inscrit le defaut. Une valeur qu'on ne sait pas lire
     * est un defaut et non une valeur ignoree : on ne confronte pas a une barre un chiffre qu'on ne lit
     * pas (ADR-0013 obligation 2).
     */
    private static Double secondes(String valeur, Regle regle, List<String> defauts) {
        String texte = valeur.strip();
        try {
            if (texte.endsWith("ms")) {
                return Double.parseDouble(texte.substring(0, texte.length() - 2)) / 1000;
            }
            if (texte.endsWith("s")) {
                return Double.parseDouble(texte.substring(0, texte.length() - 1));
            }
        } catch (NumberFormatException illisible) {
            defauts.add(regle.origine() + " : \"" + texte + "\" n'est pas un temps lisible ; une valeur "
                    + "calculee ne se confronte a aucune barre (ADR-0027)");
            return null;
        }
        defauts.add(regle.origine() + " : \"" + texte + "\" n'est pas exprime en secondes ; une valeur "
                + "qu'on ne lit pas ne se confronte a aucune barre (ADR-0027)");
        return null;
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

    /**
     * Clause 8 (amendement du 2026-08-10 a ADR-0027 section 5) - <b>aucune regle maison permanente ne
     * retranche une partie du texte que le serveur a envoye.</b>
     *
     * <p>ADR-0027 annoncait "jamais un autre mot" gardee sur ses deux formes realisables, un leurre
     * rendu par le serveur et un mot fabrique par {@code content}. S7-1 en a livre une troisieme que je
     * n'avais pas nommee : le voile du rouleau est confine par {@code clip-path}, et cette propriete
     * appliquee non plus au voile mais au bouton qui <b>porte</b> le mot en retranche une partie. Ce
     * n'est pas une hypothese de laboratoire : sur {@code cm1-homophones}, dont ADR-0026 a mesure les
     * deux propositions {@code sont} et {@code son}, une decoupe laterale rend la bonne reponse
     * identique a la fausse. Un seul caractere separe la production de ce defaut - le {@code ::after}
     * du selecteur - et rien ne le tenait : mutation reelle mesuree, 149 cas verts sur 149.
     *
     * <p>Ce que la clause laisse passer, et c'est voulu : la meme propriete sur un pseudo-element
     * decoratif, qui est la forme retenue par S7-1 et la seule qui decoupe une boite sans texte. Le jeu
     * fautif corrige le prouve, sans quoi la clause serait une interdiction de {@code clip-path}
     * deguisee en clause de lisibilite.
     */
    static void verifierQuAucuneRegleNeRogneLeTexteDUneAction(Path racine) {
        Set<String> classesDActions = classesDesActions(racine);
        assertThat(classesDActions)
                .as("ADR-0003 : cette clause ne prouve rien si aucun element [%s=%s] ne porte de "
                        + "classe dans les gabarits de %s", ATTRIBUT_DE_ROLE, ACTION, racine)
                .isNotEmpty();

        List<Regle> examinees = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (Regle regle : reglesMaison(racine)) {
            if (regle.dansDesImagesCles()
                    || debutDeDeclaration(regle.corps(), PROPRIETE_DE_DECOUPE) < 0) {
                continue;
            }
            Set<String> visees = classesDuSelecteur(regle.selecteur());
            visees.retainAll(classesDActions);
            if (visees.isEmpty()) {
                continue;
            }
            examinees.add(regle);
            if (!porteSurUnPseudoElementDecoratif(regle.selecteur())) {
                defauts.add(regle.origine() + " : cette regle declare " + PROPRIETE_DE_DECOUPE + " sur "
                        + visees + ", des classes que porte un bouton d'action, et son sujet est "
                        + "l'element lui-meme et non un pseudo-element decoratif " + PSEUDO_ELEMENTS_DECORATIFS
                        + ". Elle retranche donc une partie du mot que le serveur a envoye, et un mot "
                        + "retranche peut etre un autre mot : sur cm1-homophones, une decoupe laterale "
                        + "transforme \"sont\" en \"son\", c'est-a-dire la bonne reponse en la fausse "
                        + "(ADR-0027, amendement du 2026-08-10)");
            }
        }
        assertThat(examinees)
                .as("ADR-0003 : cette clause ne prouve rien si aucune regle maison ne declare %s sur une "
                        + "classe d'action. Une le fait aujourd'hui, le voile du rouleau de S7-1 sur "
                        + ".proposition::after ; le jour ou elle disparait, cette clause perd son objet "
                        + "et c'est ce message qui le dit", PROPRIETE_DE_DECOUPE)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0027 : aucune regle maison ne retranche une partie du texte que le serveur a "
                        + "envoye")
                .isEmpty();
    }

    /**
     * Clause 9 (ADR-0030) - <b>une animation s'applique a ce que l'enfant presse, jamais a
     * l'habillage qui l'entoure.</b>
     *
     * <p>C'est la moitie gardable de la condition C3 de {@code nadia-prof} (arbitrage prealable du
     * 2026-08-11) : "aucune animation, aucun clignotement, aucun changement de couleur, aucune lumiere
     * sur le cabinet lui-meme, a aucun moment. Le mouvement appartient aux colonnes et a elles
     * seules." Elle ecrit elle-meme que ce point est le sien parce qu'"un {@code @keyframes} sur une
     * regle d'habillage passerait une CI verte" : ce n'est plus vrai a partir d'ici.
     *
     * <p>La propriete est derivee et non enumeree : le <b>sujet</b> du selecteur anime doit nommer une
     * classe que porte un element {@code [data-role=action]} dans un gabarit. Aucun nom de classe
     * d'habillage n'est ecrit ici - un cabinet, un bandeau, un socle, ou tout autre habillage a venir
     * echoue par le fait qu'aucun element pressable ne porte sa classe, pas par le fait d'etre nomme.
     *
     * <p>Ce que cette clause ne garde pas, et qui reste a {@code nadia-prof} : un changement de couleur
     * obtenu autrement qu'en animant - un etat au survol, une transition, une lumiere peinte dans une
     * image. Sa condition C3 est plus large que sa moitie declarative.
     */
    static void verifierQueChaqueAnimationPorteSurUnElementPressable(Path racine) {
        Set<String> classesDActions = classesDesActions(racine);
        assertThat(classesDActions)
                .as("ADR-0003 : cette clause ne prouve rien si aucun element [%s=%s] ne porte de "
                        + "classe dans les gabarits de %s", ATTRIBUT_DE_ROLE, ACTION, racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (Regle regle : animations(racine)) {
            Set<String> visees = classesDuSelecteur(sujet(regle.selecteur()));
            visees.retainAll(classesDActions);
            if (visees.isEmpty()) {
                defauts.add(regle.origine() + " : le sujet de ce selecteur ne porte aucune classe "
                        + "d'un element pressable. Cette animation anime donc de l'habillage, et le "
                        + "mouvement appartient aux colonnes et a elles seules - une machine qui "
                        + "s'anime toute seule agit, une machine immobile est un meuble que l'enfant "
                        + "actionne (nadia-prof, arbitrage prealable du 2026-08-11, condition C3)");
            }
        }
        assertThat(defauts)
                .as("ADR-0030 : toute animation maison s'applique a un element pressable ou a son "
                        + "pseudo-element decoratif, jamais a un habillage")
                .isEmpty();
    }

    /**
     * Clause 10 (ADR-0030) - <b>la derniere image d'une animation est l'etat de repos de ce qu'elle
     * anime.</b>
     *
     * <p>C'est la famille entiere du defaut que {@code nadia-prof} a trouve en s07 et qui a traverse
     * {@code lea-dev}, moi et {@code yanis-qa} : l'animation du voile terminait sur
     * {@code inset(80% 0 0 0)}, soit un cinquieme de la boite encore couvert, alors que l'etat de
     * repos ne couvre rien. Le voile disparaissait par la <b>fin</b> de l'animation et non par sa
     * progression. Elle avait ecrit la raison pour laquelle personne ne l'avait vu : <b>aucun test ne
     * regarde la derniere image d'une animation</b>. Or cette derniere image est declarative - c'est le
     * bloc {@code to} ou {@code 100%} du {@code @keyframes} - donc lisible ici.
     *
     * <p><b>Pourquoi la comparaison n'est pas textuelle, et c'est tout l'interet de cette clause.</b>
     * {@code inset(100% 0 0 0)} et {@code inset(0 0 100% 0)} sont deux chaines differentes qui rendent
     * la meme image : rien de visible. La premiere est la valeur d'arrivee corrigee, la seconde l'etat
     * de repos ecrit depuis S7-1. Une egalite de chaines aurait donc rougi sur la <b>correction juste</b>,
     * et la seule facon de la verdir aurait ete de reecrire l'etat de repos pour qu'il ressemble a la
     * derniere image - c'est-a-dire de changer ce qu'un enfant voit pour satisfaire un automate, ce
     * qu'ADR-0025 a deja refuse. {@code clip-path: inset()} est donc compare par la <b>geometrie qu'il
     * decrit</b>, calculee sur les nombres declares : deux decoupes sont equivalentes si elles
     * decrivent le meme rectangle, ou si toutes deux sont vides.
     *
     * <p><b>Limite nommee.</b> Pour toute autre propriete, la comparaison est textuelle apres
     * normalisation des blancs. Deux valeurs equivalentes ecrites differemment produiraient donc un
     * rouge ; le remede est de les ecrire a l'identique, ce qui ne coute rien et rend l'egalite lisible.
     * {@code clip-path} recoit un traitement reel parce que ses deux formes de "rien de visible" sont
     * toutes deux naturelles et <b>toutes deux presentes dans ce depot</b> - ce n'est pas une hypothese.
     *
     * <p>Trois autres choses deviennent rouges par la meme lecture, chacune un vert silencieux
     * aujourd'hui : une animation qui nomme des images cles inexistantes, un {@code @keyframes} sans
     * image finale, et une propriete d'image finale dont l'etat de repos n'est declare nulle part -
     * on ne compare pas a un etat qu'on ne lit pas (ADR-0013 obligation 2).
     */
    static void verifierQueLaDerniereImageEgaleLEtatDeRepos(Path racine) {
        List<Regle> regles = reglesMaison(racine);
        Set<String> imagesClesDeclarees = new LinkedHashSet<>();
        for (Regle regle : regles) {
            nomDesImagesCles(regle.contexte()).ifPresent(imagesClesDeclarees::add);
        }

        List<Regle> animees = animations(racine);
        assertThat(animees)
                .as("ADR-0003 : cette clause ne prouve rien si aucune feuille maison de %s ne declare "
                        + "d'animation", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (Regle animation : animees) {
            Optional<String> nomme = nomAnime(animation, imagesClesDeclarees);
            if (nomme.isEmpty()) {
                defauts.add(animation.origine() + " : cette regle anime quelque chose sans nommer "
                        + "d'images cles declarees par une feuille maison " + imagesClesDeclarees
                        + ". Une animation dont les images cles n'existent pas ne joue rien, et rien "
                        + "aujourd'hui ne le dit (ADR-0030)");
                continue;
            }
            String nom = nomme.orElseThrow();
            List<Regle> finales = regles.stream()
                    .filter(regle -> nomDesImagesCles(regle.contexte())
                            .filter(nom::equals).isPresent())
                    .filter(regle -> IMAGE_FINALE.contains(regle.selecteur().strip().toLowerCase(Locale.ROOT)))
                    .toList();
            if (finales.isEmpty()) {
                defauts.add(animation.origine() + " : les images cles \"" + nom + "\" ne declarent "
                        + "aucune image finale (" + IMAGE_FINALE + "). Sans elle, l'etat ou l'animation "
                        + "rend la main n'est ecrit nulle part et ne se compare a rien (ADR-0030)");
                continue;
            }
            String sujetAnime = sujet(animation.selecteur());
            for (Regle finale : finales) {
                for (String propriete : proprietesDeclarees(finale.corps())) {
                    comparerALEtatDeRepos(regles, animation, sujetAnime, nom, propriete,
                            valeurDe(finale.corps(), propriete), defauts);
                }
            }
        }
        assertThat(defauts)
                .as("ADR-0030 : la derniere image de toute animation maison est l'etat de repos de ce "
                        + "qu'elle anime")
                .isEmpty();
    }

    /**
     * L'etat de repos d'une propriete pour un sujet : la <b>derniere</b> declaration de la feuille, hors
     * images cles, hors repli de mouvement, et hors des regles qui animent - une regle qui anime declare
     * le mouvement, pas le repos. La cascade par specificite n'est pas calculee : c'est l'ordre du
     * document qui tranche, limite nommee et sans effet sur la production, ou une seule regle declare la
     * decoupe de repos.
     */
    private static void comparerALEtatDeRepos(List<Regle> regles, Regle animation, String sujetAnime,
                                               String nomDesImagesCles, String propriete,
                                               String valeurFinale, List<String> defauts) {
        String repos = null;
        for (Regle regle : regles) {
            if (regle.dansDesImagesCles() || regle.sousMouvementReduit() || declareUneAnimation(regle)) {
                continue;
            }
            if (!sujet(regle.selecteur()).equals(sujetAnime)) {
                continue;
            }
            if (debutDeDeclaration(regle.corps(), propriete) >= 0) {
                repos = valeurDe(regle.corps(), propriete);
            }
        }
        if (repos == null) {
            defauts.add(animation.origine() + " : l'image finale de \"" + nomDesImagesCles + "\" declare "
                    + propriete + ", et aucune regle maison ne declare l'etat de repos de cette "
                    + "propriete pour \"" + sujetAnime + "\". On ne compare pas une valeur d'arrivee a un "
                    + "etat de repos qu'on ne lit pas : declare-le, ou etends cette clause (ADR-0013 "
                    + "obligation 2, ADR-0030)");
            return;
        }
        if (equivalentes(propriete, valeurFinale, repos)) {
            return;
        }
        defauts.add(animation.origine() + " : l'image finale de \"" + nomDesImagesCles + "\" laisse "
                + propriete + ": " + valeurFinale + " alors que l'etat de repos de \"" + sujetAnime
                + "\" est " + propriete + ": " + repos + ". L'animation rend donc la main sur un autre "
                + "etat que celui qui la suit : ce qui disparait a cet instant disparait par la fin de "
                + "l'animation et non par sa progression, et l'enfant voit une discontinuite "
                + "(nadia-prof, reserve du 2026-08-10 precisee le 2026-08-11 ; ADR-0030)");
    }

    /** Le nom des images cles d'un contexte de regles-@, ou vide si ce n'en est pas un. */
    private static Optional<String> nomDesImagesCles(String contexte) {
        String compact = String.join(" ", mots(contexte));
        if (!compact.toLowerCase(Locale.ROOT).startsWith(IMAGES_CLES)) {
            return Optional.empty();
        }
        List<String> mots = mots(compact.substring(IMAGES_CLES.length()));
        return mots.isEmpty() ? Optional.empty() : Optional.of(mots.get(0));
    }

    /**
     * Le nom d'images cles qu'une regle d'animation declenche. Il n'est pas devine par sa position dans
     * le raccourci - l'ordre y est libre - mais <b>derive</b> : c'est celui de ses mots qui nomme des
     * images cles reellement declarees par une feuille maison.
     */
    private static Optional<String> nomAnime(Regle animation, Set<String> imagesClesDeclarees) {
        for (String propriete : List.of("animation", "animation-name")) {
            int position = debutDeDeclaration(animation.corps(), propriete);
            if (position < 0) {
                continue;
            }
            for (String mot : mots(valeurDeclaree(animation.corps(), position, propriete))) {
                if (imagesClesDeclarees.contains(mot)) {
                    return Optional.of(mot);
                }
            }
        }
        return Optional.empty();
    }

    /** Les noms de proprietes qu'un corps de regle declare, dans l'ordre. */
    private static List<String> proprietesDeclarees(String corps) {
        List<String> proprietes = new ArrayList<>();
        for (String declaration : corps.split(";")) {
            int deuxPoints = declaration.indexOf(':');
            if (deuxPoints < 0) {
                continue;
            }
            String nom = declaration.substring(0, deuxPoints).strip().toLowerCase(Locale.ROOT);
            if (!nom.isEmpty() && !proprietes.contains(nom)) {
                proprietes.add(nom);
            }
        }
        return proprietes;
    }

    private static String valeurDe(String corps, String propriete) {
        int position = debutDeDeclaration(corps, propriete);
        return position < 0 ? "" : String.join(" ", mots(valeurDeclaree(corps, position, propriete)));
    }

    /**
     * Deux valeurs decrivent-elles le meme rendu ? Pour {@code clip-path: inset()}, la comparaison est
     * geometrique et non textuelle (voir la Javadoc de la clause 10) ; pour tout le reste, textuelle
     * apres normalisation des blancs.
     */
    private static boolean equivalentes(String propriete, String finale, String repos) {
        if (DECOUPE.equals(propriete)) {
            Optional<double[]> boiteFinale = rectangleDecoupe(finale);
            Optional<double[]> boiteRepos = rectangleDecoupe(repos);
            if (boiteFinale.isPresent() && boiteRepos.isPresent()) {
                return memeRectangle(boiteFinale.orElseThrow(), boiteRepos.orElseThrow());
            }
        }
        return finale.equals(repos);
    }

    /**
     * Le rectangle visible qu'un {@code inset(haut droite bas gauche)} en pourcentages laisse voir, sous
     * la forme {@code {haut, droite, bas, gauche}} des bords. Vide si la valeur n'est pas un
     * {@code inset()} de quatre pourcentages - dans ce cas la comparaison retombe sur le texte, plutot
     * que de deviner une geometrie.
     */
    private static Optional<double[]> rectangleDecoupe(String valeur) {
        String texte = valeur.strip().toLowerCase(Locale.ROOT);
        if (!texte.startsWith("inset(") || !texte.endsWith(")")) {
            return Optional.empty();
        }
        List<String> composantes = mots(texte.substring("inset(".length(), texte.length() - 1));
        if (composantes.size() != 4) {
            return Optional.empty();
        }
        double[] bords = new double[4];
        for (int rang = 0; rang < 4; rang++) {
            Optional<Double> pourcentage = pourcentage(composantes.get(rang));
            if (pourcentage.isEmpty()) {
                return Optional.empty();
            }
            bords[rang] = pourcentage.orElseThrow();
        }
        return Optional.of(bords);
    }

    /** Un pourcentage, ou {@code 0} ecrit sans unite comme le CSS l'autorise. Vide sinon. */
    private static Optional<Double> pourcentage(String composante) {
        try {
            if (composante.endsWith("%")) {
                return Optional.of(Double.parseDouble(composante.substring(0, composante.length() - 1)));
            }
            return Double.parseDouble(composante) == 0 ? Optional.of(0.0) : Optional.empty();
        } catch (NumberFormatException illisible) {
            return Optional.empty();
        }
    }

    /**
     * Deux decoupes sont equivalentes si elles laissent voir le meme rectangle, ou si toutes deux ne
     * laissent rien voir - une hauteur ou une largeur nulle ne montre rien, quelle que soit la facon de
     * l'ecrire. C'est exactement ce qui distingue {@code inset(100% 0 0 0)} de {@code inset(80% 0 0 0)}
     * face a un repos de {@code inset(0 0 100% 0)}.
     */
    private static boolean memeRectangle(double[] premiere, double[] seconde) {
        boolean videPremiere = premiere[0] + premiere[2] >= 100 || premiere[1] + premiere[3] >= 100;
        boolean videSeconde = seconde[0] + seconde[2] >= 100 || seconde[1] + seconde[3] >= 100;
        if (videPremiere || videSeconde) {
            return videPremiere && videSeconde;
        }
        for (int rang = 0; rang < 4; rang++) {
            if (premiere[rang] != seconde[rang]) {
                return false;
            }
        }
        return true;
    }

    @Test
    void chaqueAnimationPorteSurUnElementPressable() {
        verifierQueChaqueAnimationPorteSurUnElementPressable(RACINE_PRODUCTION);
    }

    @Test
    void derniereImageDeChaqueAnimationEgaleLEtatDeRepos() {
        verifierQueLaDerniereImageEgaleLEtatDeRepos(RACINE_PRODUCTION);
    }

    /**
     * ADR-0030, clause 9. L'objet neuf est un cabinet qui pulse en attendant - la forme exacte que
     * {@code nadia-prof} nomme comme faisant tomber son avis ("le jour ou le cabinet pulse en
     * attendant, l'argument qui sauve la metaphore tombe, et mon avis avec lui").
     */
    @Test
    void uneAnimationSurUnHabillageFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("habillage-qui-s-anime");
        assertThatThrownBy(() -> verifierQueChaqueAnimationPorteSurUnElementPressable(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte aucune classe d'un element pressable");
    }

    /**
     * ADR-0030, clause 10. L'objet neuf n'est pas invente : c'est la feuille <b>telle que S7-1 l'a
     * livree</b>, avec sa valeur d'arrivee a 80 % et son etat de repos a
     * {@code inset(0 0 100% 0)}. C'est le defaut que seul le releve image par image de
     * {@code nadia-prof} avait vu.
     */
    @Test
    void uneDerniereImageHorsDeLEtatDeReposFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("derniere-image-hors-du-repos");
        assertThatThrownBy(() -> verifierQueLaDerniereImageEgaleLEtatDeRepos(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("rend donc la main sur un autre etat que celui qui la suit");
    }

    /**
     * Le meme jeu fautif corrige par la seule valeur d'arrivee, <b>ecrite autrement que l'etat de
     * repos</b> : {@code inset(100% 0 0 0)} contre {@code inset(0 0 100% 0)}. Ce cas est celui qui
     * empeche la clause 10 d'etre une egalite de chaines deguisee en propriete de rendu - sans lui, la
     * correction juste serait refusee et aucun jeu fautif ne le dirait.
     */
    @Test
    void laMemeAnimationCorrigeeEtEcriteAutrementResteVerte() {
        verifierQueLaDerniereImageEgaleLEtatDeRepos(
                CONTRE_EXEMPLES.resolve("derniere-image-ecrite-autrement"));
    }

    /**
     * ADR-0030, clause 10, second vert silencieux ferme : une animation dont les images cles n'existent
     * pas. Elle ne joue rien du tout, et aucune des huit clauses precedentes ne le voyait.
     */
    @Test
    void uneAnimationSansImagesClesFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("images-cles-inexistantes");
        assertThatThrownBy(() -> verifierQueLaDerniereImageEgaleLEtatDeRepos(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sans nommer d'images cles declarees");
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
    void aucuneRegleMaisonNeRogneLeTexteDUneAction() {
        verifierQuAucuneRegleNeRogneLeTexteDUneAction(RACINE_PRODUCTION);
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

    // S7-1 : l'epinglage qui vivait ici ("aucune animation n'est encore declaree en production")
    // est supprime, exactement comme son propre message le prescrivait. Le rouleau de
    // .proposition::after est la premiere animation maison du depot (recreo.css) : les deux
    // clauses de mouvement (chaqueAnimationDeLaFeuilleMaisonSeTermine,
    // chaqueAnimationDeLaFeuilleMaisonAUnRepliDeMouvement) portent desormais sur un objet reel,
    // elles ne sont plus vertes a vide.

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

    /**
     * ADR-0027, clause 6, sur la forme que la production ecrit reellement : le retard n'est pas dans le
     * raccourci mais dans une regle par colonne. C'est le jeu fautif que
     * {@code decalage-trop-grand} aurait du etre et n'etait pas - il ecrivait le retard la ou je
     * l'imaginais, pas la ou un decalage par colonne s'ecrit.
     */
    @Test
    void unRetardEcritHorsDeLaRegleQuiAnimeFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("retard-ecrit-a-part");
        assertThatThrownBy(() -> verifierQueChaqueAnimationTientDansLaBarreDeDuree(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au-dela du decalage maximal de 0.25s");
    }

    /**
     * Amendement du 2026-08-10, clause 8. L'objet neuf est une decoupe permanente posee sur le bouton
     * qui porte le mot - la production a un caractere pres, {@code ::after} retire du selecteur.
     */
    @Test
    void uneDecoupePermanenteDuTexteDUneActionFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("texte-de-l-action-rogne");
        assertThatThrownBy(() -> verifierQuAucuneRegleNeRogneLeTexteDUneAction(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("transforme \"sont\" en \"son\"");
    }

    /**
     * La meme decoupe, deplacee sur le pseudo-element decoratif : c'est la forme que S7-1 a retenue, et
     * elle doit passer. Sans ce cas, la clause 8 serait une interdiction de {@code clip-path} deguisee,
     * et aucun jeu fautif ne le dirait - la lecon de la seconde mutation d'ADR-0025.
     */
    @Test
    void laMemeDecoupeSurUnPseudoElementDecoratifResteVerte() {
        verifierQuAucuneRegleNeRogneLeTexteDUneAction(
                CONTRE_EXEMPLES.resolve("voile-rogne-sans-toucher-le-texte"));
    }

    @Test
    void unBoutonDesactiveDansUnGabaritFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("action-desactivee");
        assertThatThrownBy(() -> verifierQuAucunGabaritNeDesactiveUnBouton(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("porte disabled");
    }
}
