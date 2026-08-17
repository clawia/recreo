package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0046 : <b>des que la feuille tierce declare un etat interactif sur un selecteur qu'un element
 * pressable porte, la feuille maison declare ce meme etat pour toute propriete qu'elle gouverne au
 * repos.</b>
 *
 * <p><b>Le defaut que cette clause ferme a ete vu par l'utilisatrice, pas par la CI.</b> Les puces de
 * niveau de l'en-tete disparaissent au survol de la souris. Le mecanisme est entierement declaratif et
 * verifiable par lecture des deux feuilles :
 * <ul>
 *   <li>{@code .chip-niveau} declare {@code color}, {@code background-color: transparent} et
 *       {@code border: 0.125rem solid var(--recreo-accent-texte)}, et <b>aucun etat de survol</b> ;</li>
 *   <li>la feuille vendorisee declare {@code .btn:hover} avec {@code color: var(--bs-btn-color)},
 *       {@code background-color: var(--bs-btn-bg)} et {@code border-color: var(--bs-btn-border-color)},
 *       et la base {@code .btn} pose ces trois variables a des valeurs de bouton nu - texte sombre,
 *       fond transparent, bordure transparente ;</li>
 *   <li>{@code .btn:hover} vaut (0,2,0) de specificite contre (0,1,0) pour {@code .chip-niveau}. Sur la
 *       bande volontairement sombre du bandeau de niveaux, la puce recoit donc du texte sombre sans
 *       aucun contour : elle s'efface.</li>
 * </ul>
 *
 * <p><b>Ce que ce defaut dit d'ADR-0014, et c'est le vrai sujet.</b> Son point 5 fonde la garantie de
 * rendu sur l'ordre de chargement : la feuille maison passe en dernier, <i>"c'est ce qui fait qu'une
 * regle maison de meme specificite l'emporte sur la regle tierce, donc que les planchers exiges par
 * nadia-prof sont ceux que l'enfant voit"</i>. L'hypothese de <b>meme specificite</b> ne tient pas des
 * que la regle tierce porte une pseudo-classe, et ADR-0014 avait nomme cette limite en l'illustrant par
 * un selecteur d'identifiant en ajoutant <i>"Bootstrap n'en contient pas"</i>. La mesure dit autre chose :
 * il n'y faut aucun identifiant, une pseudo-classe suffit, et la feuille vendorisee en porte
 * <b>139 selecteurs</b>.
 *
 * <p><b>Perimetre et derivation (ADR-0013).</b> Rien n'est enumere de ce qui peut varier :
 * <ul>
 *   <li>les <b>pressables</b> et leurs classes viennent de {@code TailleDesTextesEtBoutonsTest},
 *       balayage des gabarits, branches de {@code th:class} comprises - la meme population et les memes
 *       selecteurs applicables que les planchers de taille et de cible tactile ;</li>
 *   <li>les <b>etats</b> et les <b>proprietes</b> viennent de la feuille vendorisee elle-meme : ce
 *       fichier ne nomme aucune propriete de Bootstrap. Une montee de version qui ajouterait une
 *       propriete a {@code .btn:hover} allonge l'exigence sans que ce fichier change ;</li>
 *   <li>le <b>cote maison</b> vient de ce que la feuille maison declare reellement au repos : une
 *       propriete qu'elle ne gouverne pas n'est jamais exigee a un etat.</li>
 * </ul>
 * Seuls les <b>noms des etats gardes</b> sont ecrits ici, et l'un de ceux qui pourraient y figurer en
 * est exclu sur mesure - voir {@link #ETATS_GARDES} et
 * {@link #lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre}.
 *
 * <p><b>Ce que cette clause ne voit pas, dit avant qu'on le decouvre.</b>
 * <ul>
 *   <li><b>Un etat declare sur une balise nue.</b> Les selecteurs applicables d'un bouton sont ses
 *       classes des qu'il en porte une, et tous en portent. Mesure sur la feuille vendorisee : la seule
 *       regle d'etat de balise nue qui pourrait atteindre un de nos boutons est {@code a:hover}, et elle
 *       ne declare qu'une propriete personnalisee ({@code --bs-link-color-rgb}), donc rien qui ecrase
 *       une declaration maison. Le jour ou une bibliotheque declare une vraie propriete sur
 *       {@code button:hover}, cette clause ne la voit pas.</li>
 *   <li><b>La condition structurelle d'une regle tierce n'est pas evaluee.</b>
 *       {@code .btn:first-child:active} et {@code .btn-check+.btn:hover} sont traites comme atteignables
 *       parce que leur sujet est un {@code .btn}. C'est volontaire et c'est le sens d'ADR-0013 obligation
 *       2 - un etat qu'on ne sait pas classer est traite comme atteignable - mais ici la surcollecte
 *       grossit une <b>obligation</b>, donc son mode de defaillance est un rouge, pas un vert.
 *       Mesure sur la feuille livree : elle n'ajoute <b>aucune</b> exigence, les proprietes que ces
 *       regles-la declarent en plus ({@code z-index}) n'etant gouvernees par aucune regle maison au
 *       repos.</li>
 *   <li><b>Ce que l'enfant voit reellement a un etat.</b> Aucune valeur n'est comparee, aucun contraste
 *       n'est calcule : la clause exige que la feuille maison <b>declare</b> l'etat, jamais qu'elle le
 *       declare bien. Un {@code color: #1c2733} ecrit a la main sur fond sombre passerait. Le verdict
 *       sur le rendu reste a nadia-prof (ADR-0025), et il n'est pas abrege par cette clause.</li>
 *   <li><b>Le survol lui-meme n'est atteignable par aucune mesure de ce depot.</b>
 *       {@code outils/mesurer-rendu.sh} prend une URL et une largeur ; il ne simule aucun pointeur. Ce
 *       defaut a ete vu par une main sur une souris, et c'est la seule facon dont il pouvait l'etre.</li>
 * </ul>
 */
class AucunEtatDePressableNEstAbandonneAuTiersTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/AucunEtatDePressableNEstAbandonneAuTiersTest");

    /**
     * Les etats que cette clause garde : ceux qu'un pointeur, un doigt ou un appui posent et retirent
     * sans que le serveur en sache rien.
     *
     * <p>{@code :focus-visible} n'y est pas, et l'exclusion est mesuree plutot qu'argumentee : la
     * bibliotheque vendorisee y declare l'anneau de focus clavier sous forme d'ombre portee, sur lequel
     * ce depot s'appuie nommement (ADR-0030 point 2,
     * {@code LHabillageNeRogneJamaisUnElementPressableTest}). Exiger que la feuille maison redeclare
     * {@code box-shadow} a cet etat forcerait le developpeur a ecraser cet anneau ou a le recopier - un
     * garde-fou qui pousse a une regression d'accessibilite. La mesure de ce qu'il en couterait est
     * epinglee par
     * {@link #lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre}, qui rougit le
     * jour ou ce motif disparait. {@code :focus-within}, {@code :target}, {@code :visited} et
     * {@code :link} n'y sont pas non plus : la feuille vendorisee ne les declare sur aucun selecteur
     * qu'un de nos boutons porte, et une exigence sans objet est decorative.
     */
    static final List<String> ETATS_GARDES = List.of(":hover", ":active");

    /**
     * Toutes les pseudo-classes que le navigateur, et non le serveur, met et retire. Cette liste-ci
     * n'est pas le perimetre garde : elle sert a <b>reconnaitre</b> l'etat porte par un selecteur, et
     * donc a ecarter une regle tierce qui melange un etat garde et un etat exclu -
     * {@code .btn:first-child:active:focus-visible} ne s'applique que si les deux tiennent, elle
     * appartient a la famille du focus et non a celle de l'appui.
     */
    private static final List<String> TOUS_LES_ETATS = List.of(
            ":focus-visible", ":focus-within", ":hover", ":active", ":focus", ":target", ":visited",
            ":link");

    /**
     * La dette mesuree sur la production a l'ouverture de cet ADR, et le plafond exact de ce que la
     * feuille maison a le droit de laisser au tiers. Un plafond qui depasse la dette n'est pas un
     * cliquet : {@link #verifierQueLaDetteDEtatsNeDepasseJamaisSonPlafond} compare
     * <b>exactement</b> l'ensemble mesure a cet ensemble-ci, dans les deux sens. Une exigence neuve -
     * un pressable de plus, une propriete de plus au repos, une montee de version de la bibliotheque -
     * rougit ; une exigence honoree qui resterait declaree ici rougit aussi, ce qui oblige le lot qui
     * corrige a faire baisser le plafond.
     *
     * <p>La cle est le selecteur maison qui declare la propriete au repos, la valeur les proprietes
     * qu'un etat garde y ecrase. Le produit par {@link #ETATS_GARDES} est fait par le test : le jour ou
     * un etat entre dans le perimetre, l'ensemble attendu change de taille et le plafond doit etre
     * rouvert explicitement.
     */
    private static final Map<String, Set<String>> DETTE_A_L_OUVERTURE = Map.of(
            ".chip-niveau", Set.of("color", "background-color", "border"),
            ".retour-accueil", Set.of("color", "background-color", "border"),
            ".continuer-partie", Set.of("color", "background-color"),
            ".demarrer", Set.of("color", "background-color"),
            ".proposition", Set.of("color", "background-color"),
            ".recommencer", Set.of("color", "background-color"),
            ".sortie-pleine", Set.of("color", "background-color"));

    /**
     * Une exigence : la feuille maison declare {@code propriete} au repos sur {@code selecteurMaison},
     * la feuille tierce l'ecrase a l'etat {@code etat}, donc la feuille maison doit declarer cet etat.
     * {@code origine} est le selecteur tiers qui la produit - il entre dans le message, jamais dans
     * l'egalite : deux regles tierces peuvent produire la meme exigence.
     */
    private record Exigence(String selecteurMaison, String etat, String propriete) {
    }

    /**
     * Les etats interactifs qu'un compose de selecteur porte, et ce qu'il reste du compose une fois
     * ceux-la retires. Les plus longs d'abord, sans quoi {@code :focus} serait trouve dans
     * {@code :focus-visible} - la meme famille d'erreur que la sous-chaine {@code font-size:} trouvee
     * dans une variable Bootstrap (ADR-0015).
     */
    private record EtatsDuCompose(Set<String> etats, String reste) {
    }

    private static EtatsDuCompose etatsDuCompose(String compose) {
        Set<String> etats = new LinkedHashSet<>();
        String reste = compose;
        for (String etat : TOUS_LES_ETATS) {
            while (reste.contains(etat)) {
                int position = reste.indexOf(etat);
                reste = reste.substring(0, position) + reste.substring(position + etat.length());
                etats.add(etat);
            }
        }
        return new EtatsDuCompose(etats, reste);
    }

    /**
     * Le compose depouille de tout ce qui n'est pas son element : les etats interactifs sont deja
     * retires, et la troncature au premier {@code :} restant emporte les pseudo-classes structurelles
     * ({@code :first-child}, {@code :not(...)}) comme les pseudo-elements. Une pseudo-classe
     * structurelle est ainsi traitee comme satisfaite - la surcollecte assumee, nommee dans la Javadoc
     * de la classe et mesuree comme n'ajoutant aucune exigence sur la feuille livree.
     */
    private static String elementDuCompose(String reste) {
        int deuxPoints = reste.indexOf(':');
        return (deuxPoints < 0 ? reste : reste.substring(0, deuxPoints)).strip();
    }

    /**
     * Cette propriete tierce touche-t-elle celle que la maison declare ? L'egalite ne suffit pas :
     * {@code .chip-niveau} declare le raccourci {@code border} et {@code .btn:hover} declare la
     * longhand {@code border-color}, et c'est precisement par ce couple-la que le contour de la puce
     * disparait. La relation est lue sur les noms, dans les deux sens, sans table de raccourcis a
     * tenir a jour.
     */
    private static boolean touche(String proprieteMaison, String proprieteTierce) {
        return proprieteTierce.equals(proprieteMaison)
                || proprieteTierce.startsWith(proprieteMaison + "-")
                || proprieteMaison.startsWith(proprieteTierce + "-");
    }

    /**
     * Les noms de proprietes reellement rendues que ce corps declare. Les proprietes personnalisees
     * sont ecartees : {@code a:hover} de la bibliotheque ne declare que
     * {@code --bs-link-color-rgb}, et la prendre pour une propriete rendue est la forme exacte du
     * defaut qu'ADR-0015 a corrige sur {@code --bs-btn-font-size}.
     */
    private static List<String> proprietesRendues(String corps) {
        return LAnimationRetardeMaisNeDetermineRienTest.proprietesDeclarees(corps).stream()
                .filter(nom -> !nom.startsWith("--"))
                .toList();
    }

    /** Les proprietes que la feuille maison declare au repos sur cet etat de bouton, par selecteur. */
    private static Map<String, Set<String>> reposMaison(
            Map<String, String> maison, List<String> applicables) {
        Map<String, Set<String>> repos = new LinkedHashMap<>();
        for (String selecteur : applicables) {
            String corps = maison.get(selecteur);
            if (corps == null) {
                continue;
            }
            repos.put(selecteur, new LinkedHashSet<>(proprietesRendues(corps)));
        }
        return repos;
    }

    /** Les proprietes que la feuille maison declare a chaque etat interactif de cet etat de bouton. */
    private static Map<String, Set<String>> etatsMaison(
            Map<String, String> maison, List<String> applicables) {
        Map<String, Set<String>> declares = new LinkedHashMap<>();
        for (Map.Entry<String, String> regle : maison.entrySet()) {
            EtatsDuCompose lecture =
                    etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(regle.getKey()));
            if (lecture.etats().isEmpty()
                    || !applicables.contains(elementDuCompose(lecture.reste()))) {
                continue;
            }
            for (String etat : lecture.etats()) {
                declares.computeIfAbsent(etat, inconnu -> new LinkedHashSet<>())
                        .addAll(proprietesRendues(regle.getValue()));
            }
        }
        return declares;
    }

    /**
     * Les exigences que la feuille tierce fait peser sur cet etat de bouton : pour chaque regle tierce
     * dont le sujet est un selecteur applicable porteur d'un ou plusieurs etats <b>tous</b> gardes,
     * chaque propriete declaree qui touche une propriete gouvernee au repos par la maison.
     */
    private static Map<Exigence, Set<String>> exigencesDuTiers(
            Map<String, String> tiers, List<String> applicables, Map<String, Set<String>> repos) {
        Map<Exigence, Set<String>> exigences = new LinkedHashMap<>();
        for (Map.Entry<String, String> regle : tiers.entrySet()) {
            EtatsDuCompose lecture =
                    etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(regle.getKey()));
            if (lecture.etats().isEmpty() || !ETATS_GARDES.containsAll(lecture.etats())
                    || !applicables.contains(elementDuCompose(lecture.reste()))) {
                continue;
            }
            for (String etat : lecture.etats()) {
                for (String proprieteTierce : proprietesRendues(regle.getValue())) {
                    for (Map.Entry<String, Set<String>> declarees : repos.entrySet()) {
                        for (String proprieteMaison : declarees.getValue()) {
                            if (touche(proprieteMaison, proprieteTierce)) {
                                exigences.computeIfAbsent(
                                        new Exigence(declarees.getKey(), etat, proprieteMaison),
                                        inconnue -> new LinkedHashSet<>())
                                        .add(regle.getKey());
                            }
                        }
                    }
                }
            }
        }
        return exigences;
    }

    /**
     * Les exigences que la feuille maison ne satisfait pas. Les gabarits et la feuille maison viennent
     * de {@code racineMaison} ; la feuille tierce vient toujours de {@code racineTiers}, et les jeux
     * fautifs y passent la <b>racine de production</b>.
     *
     * <p>Ce n'est pas une commodite, c'est la lecon de s05 encodee dans la signature. La moitie tierce
     * de ce mecanisme est ce qu'un garde-fou peut le plus facilement se tromper a imaginer : une
     * fixture ecrite de ma main ressemblerait a ce que je crois que Bootstrap ecrit, et la clause
     * passerait sa propre epreuve en echouant dans le monde. En prenant la feuille tierce a la racine
     * de production, <b>aucun</b> jeu fautif ne peut etre prouve contre une bibliotheque inventee, et
     * une montee de version de la bibliotheque rejoue tous les jeux fautifs sans qu'on y touche.
     */
    private static Map<Exigence, Set<String>> exigencesNonSatisfaites(
            Path racineMaison, Path racineTiers) {
        return exigences(racineMaison, racineTiers, true);
    }

    /**
     * Toutes les exigences que la feuille tierce fait peser sur cette production, satisfaites ou non.
     * Lue par le test qui epingle le mecanisme du defaut du jour : il doit rester vert apres le
     * correctif, et ne rougir que si la bibliotheque ou la declaration maison au repos changent.
     */
    private static Map<Exigence, Set<String>> exigences(
            Path racineMaison, Path racineTiers, boolean seulementNonSatisfaites) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racineMaison);
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(racineTiers);
        Map<Exigence, Set<String>> retenues = new LinkedHashMap<>();

        for (TailleDesTextesEtBoutonsTest.BoutonRendu bouton
                : TailleDesTextesEtBoutonsTest.etatsRendus(racineMaison)) {
            List<String> applicables = TailleDesTextesEtBoutonsTest.selecteursApplicables(bouton);
            Map<String, Set<String>> repos = reposMaison(maison, applicables);
            Map<String, Set<String>> declares = etatsMaison(maison, applicables);

            for (Map.Entry<Exigence, Set<String>> exigence
                    : exigencesDuTiers(tiers, applicables, repos).entrySet()) {
                Exigence attendue = exigence.getKey();
                boolean satisfaite = declares.getOrDefault(attendue.etat(), Set.of()).stream()
                        .anyMatch(declaree -> touche(attendue.propriete(), declaree));
                if (!satisfaite || !seulementNonSatisfaites) {
                    retenues.computeIfAbsent(attendue, inconnue -> new LinkedHashSet<>())
                            .addAll(exigence.getValue());
                }
            }
        }
        return retenues;
    }

    private static String motif(Exigence exigence, Set<String> origines) {
        return String.format(
                "%s declare %s au repos, et la feuille tierce l'ecrase a l'etat %s (%s) : la feuille "
                        + "maison doit declarer %s%s. ADR-0046 - l'ordre de chargement ne suffit pas, "
                        + "une regle tierce a pseudo-classe l'emporte par specificite",
                exigence.selecteurMaison(), exigence.propriete(), exigence.etat(),
                String.join(", ", origines), exigence.selecteurMaison(), exigence.etat());
    }

    /**
     * La clause. Elle rassemble <b>toutes</b> les exigences manquantes avant d'echouer, jamais la
     * premiere rencontree : la lecon du point 7.a d'ADR-0033, ou une boucle qui echouait au premier
     * fautif rendait le resultat dependant d'un ordre.
     */
    static void verifierQueChaqueEtatTiersEstRedeclareParLaMaison(Path racineMaison) {
        Map<Exigence, Set<String>> manquantes =
                exigencesNonSatisfaites(racineMaison, RACINE_PRODUCTION);
        List<String> motifs = new ArrayList<>();
        manquantes.forEach((exigence, origines) -> motifs.add(motif(exigence, origines)));
        assertThat(motifs)
                .as("des etats interactifs de pressables sont laisses a la bibliotheque tierce")
                .isEmpty();
    }

    /**
     * Le cliquet, tant que la dette de production n'est pas payee. Il compare exactement l'ensemble
     * mesure au plafond declare : une exigence neuve rougit, et une exigence honoree qui reste
     * declaree rougit aussi. C'est cette seconde direction qui fait de cet ensemble un cliquet et non
     * une liste d'exemptions qui survivrait a son objet.
     */
    static void verifierQueLaDetteDEtatsNeDepasseJamaisSonPlafond(Path racine) {
        Set<Exigence> plafond = new LinkedHashSet<>();
        DETTE_A_L_OUVERTURE.forEach((selecteur, proprietes) -> {
            for (String etat : ETATS_GARDES) {
                for (String propriete : proprietes) {
                    plafond.add(new Exigence(selecteur, etat, propriete));
                }
            }
        });

        assertThat(exigencesNonSatisfaites(racine, racine).keySet())
                .as("ADR-0046 : la dette d'etats interactifs doit valoir exactement son plafond. Une "
                        + "exigence en plus est une regression ; une exigence en moins veut dire "
                        + "qu'un etat a ete declare sans que le plafond baisse d'autant, et un "
                        + "plafond qui ne nomme plus quelque chose de reel n'est plus un cliquet")
                .containsExactlyInAnyOrderElementsOf(plafond);
    }

    @Test
    void laDetteDEtatsDeLaProductionVautExactementSonPlafond() {
        verifierQueLaDetteDEtatsNeDepasseJamaisSonPlafond(RACINE_PRODUCTION);
    }

    /** Un garde-fou qui ne mesure rien ne prouve rien : la population doit etre non vide. */
    @Test
    void auMoinsUneExigenceEstDeriveeDeLaFeuilleVendoriseeReelle() {
        assertThat(exigences(RACINE_PRODUCTION, RACINE_PRODUCTION, false))
                .as("aucune exigence derivee de la feuille tierce reelle : la derivation ne lit rien")
                .isNotEmpty();
    }

    /**
     * Le mecanisme du defaut vu par l'utilisatrice, epingle sur la production : au survol, la feuille
     * vendorisee ecrase les trois proprietes de {@code .chip-niveau} dont depend sa lisibilite sur la
     * bande sombre - le texte, l'aplat et le contour.
     *
     * <p>Ce test lit les exigences <b>et non les manquements</b>, pour rester vrai apres le correctif :
     * il epingle le mecanisme, jamais l'etat du jour. Il rougit le jour ou la bibliotheque cesse de
     * declarer ces proprietes au survol, ou ou la puce cesse de les declarer au repos - c'est-a-dire le
     * jour ou l'une des deux moities du mecanisme disparait, et ou ce qui est ecrit ici cesse de
     * decrire le depot.
     */
    @Test
    void leSurvolTiersEcraseLesTroisProprietesDeLaPuceDeNiveau() {
        Set<String> attendues = new LinkedHashSet<>();
        exigences(RACINE_PRODUCTION, RACINE_PRODUCTION, false).keySet().stream()
                .filter(exigence -> exigence.selecteurMaison().equals(".chip-niveau")
                        && exigence.etat().equals(":hover"))
                .forEach(exigence -> attendues.add(exigence.propriete()));

        assertThat(attendues)
                .as("les trois proprietes par lesquelles la puce s'efface au survol : le texte, "
                        + "l'aplat et le contour")
                .containsExactlyInAnyOrder("color", "background-color", "border");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un pressable qui n'existe dans aucun gabarit de
     * production, habille par une regle maison complete au repos et par aucun etat. C'est la forme
     * que prendra le defaut le jour ou quelqu'un ajoutera un bouton de contour a l'application.
     */
    @Test
    void unPressableSansAucunEtatDeclareFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueEtatTiersEstRedeclareParLaMaison(
                CONTRE_EXEMPLES.resolve("etat-jamais-declare")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".bouton-neuf declare color au repos");
    }

    /**
     * Le meme objet, corrige par le seul ajout des deux regles d'etat. Sans ce jeu, la clause serait
     * une interdiction du bouton de contour deguisee en clause d'etat, et aucun jeu fautif ne le
     * dirait - la lecon de la seconde mutation d'ADR-0025, reprise par ADR-0033.
     */
    @Test
    void leMemePressableAvecSesDeuxEtatsDeclaresEstVert() {
        verifierQueChaqueEtatTiersEstRedeclareParLaMaison(
                CONTRE_EXEMPLES.resolve("etat-declare-des-deux-cotes"));
    }

    /**
     * Le piege du correctif a moitie fait, et c'est celui qui compte : le jeu declare
     * {@code :hover} et {@code :active} avec {@code color} et {@code background-color}, et oublie le
     * contour. Le texte redevient lisible, le bouton perd toujours sa bordure au survol - exactement
     * ce que l'utilisatrice a vu. Il faut la relation raccourci/longhand ({@code border} maison contre
     * {@code border-color} tierce) pour que ce cas rougisse.
     */
    @Test
    void unEtatQuiOublieLeContourFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueEtatTiersEstRedeclareParLaMaison(
                CONTRE_EXEMPLES.resolve("contour-oublie-au-survol")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".bouton-neuf declare border au repos");
    }

    /**
     * Le motif de l'exclusion de {@code :focus-visible}, mesure sur la feuille vendorisee reelle
     * plutot qu'argumente - meme geste que l'exclusion de {@code transform} par ADR-0033. Ce test
     * epingle les trois faits dont l'exclusion depend, et rougit le jour ou l'un tombe : la
     * bibliotheque declare bien l'anneau de focus en ombre portee, un bouton maison declare bien une
     * ombre portee au repos, et cet etat est bien hors du perimetre garde. Le jour ou la bibliotheque
     * cesse d'utiliser une ombre, l'exclusion n'est plus une exception constatee mais une question
     * ouverte.
     */
    @Test
    void lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre() {
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(RACINE_PRODUCTION);
        assertThat(proprietesRendues(tiers.getOrDefault(".btn:focus-visible", "")))
                .as("la bibliotheque vendorisee doit toujours porter l'anneau de focus clavier par "
                        + "une ombre portee sous .btn:focus-visible (ADR-0030 point 2)")
                .contains("box-shadow");

        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(RACINE_PRODUCTION);
        assertThat(proprietesRendues(maison.getOrDefault(".demarrer", "")))
                .as("un bouton maison doit declarer une ombre portee au repos, sans quoi l'exigence "
                        + "sur box-shadow a l'etat de focus serait sans objet et l'exclusion sans "
                        + "motif")
                .contains("box-shadow");

        assertThat(ETATS_GARDES)
                .as("inclure :focus-visible ferait exiger un box-shadow maison a cet etat, donc "
                        + "pousserait a ecraser l'anneau de focus clavier de la bibliotheque")
                .doesNotContain(":focus-visible");
    }
}
