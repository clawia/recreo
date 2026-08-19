package com.recreo.adapter.web;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * ADR-0046 : <b>des que la feuille tierce declare un etat interactif sur un selecteur qu'un element
 * pressable porte, la feuille maison declare ce meme etat pour toute propriete qu'elle gouverne au
 * repos.</b>
 *
 * <p><b>Amendement du 2026-08-19 (s16) : un selecteur maison compose est un sujet distinct.</b> La
 * derivation ne comparait un sujet compose (par exemple {@code .chip-niveau.est-actif}) qu'a la
 * liste des selecteurs simples de {@code selecteursApplicables} - une chaine qu'elle ne produit
 * jamais - et le sujet sortait donc du perimetre dans les deux sens : ni exige, ni compte. Trouve
 * par mutation par {@code sofia-sm} (etats retires de {@code .chip-niveau.est-actif}, sept tests
 * restes verts), chiffre par {@code nadia-prof}. La correction : {@link #sujetApplicable} juge
 * qu'un sujet - simple ou compose - s'applique des que l'element rendu porte au moins toutes les
 * classes qu'il nomme, exactement la semantique CSS d'un selecteur compose - jamais une egalite de
 * chaine avec un selecteur simple ecrit ailleurs.
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
 * Seuls les <b>noms des etats gardes</b> sont ecrits ici, et l'une des proprietes qu'un etat garde
 * pourrait comparer en est ecartee sur mesure, a cet etat seulement - voir {@link #ETATS_GARDES} et
 * {@link #lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtBoxShadowResteHorsDesProprietesCompareesACetEtat}.
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
     * <p><b>Second amendement d'ADR-0046 (s16) : {@code :focus-visible} y entre.</b> L'exclusion
     * initiale etait trop grossiere - elle excluait un <b>etat</b> la ou il fallait exclure une
     * <b>propriete de cet etat</b>. Mesure sur la feuille vendorisee servie : {@code .btn:focus-visible}
     * ecrase {@code color}, {@code background-color}, {@code border-color}, {@code outline} <b>et</b>
     * {@code box-shadow} - pas seulement une ombre portee. Sous focus clavier, un pressable perd donc
     * bien ses couleurs au profit de celles de la bibliotheque, exactement le defaut que cet ADR existe
     * pour fermer ; le critere de S16-3 nommait un defaut reel. Seule {@code box-shadow} reste ecartee,
     * et seulement a cet etat ({@link #PROPRIETES_TIERCES_ECARTEES_PAR_ETAT}) : elle porte l'anneau de
     * focus clavier tiers, sur lequel ce depot s'appuie nommement (ADR-0030 point 2,
     * {@code LHabillageNeRogneJamaisUnElementPressableTest}), et l'exiger a cet etat forcerait le
     * developpeur a l'ecraser ou a la recopier a la main - un garde-fou qui pousse a une regression
     * d'accessibilite. Une propriete ecartee a un etat nomme, pas un etat exclu en bloc : la meme forme
     * que {@code border-width} (ADR-0017) et {@code transform} (ADR-0033). La mesure de ce que cette
     * seule exclusion couvre reste epinglee par
     * {@link #lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtBoxShadowResteHorsDesProprietesCompareesACetEtat},
     * qui rougit le jour ou l'un des trois faits dont elle depend tombe. {@code :focus-within},
     * {@code :target}, {@code :visited} et {@code :link} n'y sont pas non plus : la feuille vendorisee
     * ne les declare sur aucun selecteur qu'un de nos boutons porte, et une exigence sans objet est
     * decorative.
     */
    static final List<String> ETATS_GARDES = List.of(":hover", ":active", ":focus-visible");

    /**
     * Les proprietes tierces ecartees des exigences, a un etat garde donne - jamais globalement.
     * ADR-0046, second amendement (s16) : {@code box-shadow} porte l'anneau de focus clavier tiers
     * (ADR-0030 point 2) et n'est ecartee qu'a {@code :focus-visible}, ou l'exiger forcerait la
     * feuille maison a l'ecraser ou a le recopier a la main. Aucune exclusion a {@code :hover} ni a
     * {@code :active} : une propriete dont la difference est un moyen legitime a un etat donne, pas
     * une domination generale - la meme forme que {@code border-width} (ADR-0017) et {@code transform}
     * (ADR-0033).
     *
     * <p>Appliquee par <b>regle</b>, jamais par bucket d'etat isole : la feuille vendorisee porte des
     * selecteurs qui combinent plusieurs etats gardes a la fois ({@code
     * .btn:first-child:active:focus-visible}), et cette regle-la ne s'applique jamais sans
     * {@code :focus-visible}. Ecarter {@code box-shadow} seulement dans le bucket {@code
     * :focus-visible} laisserait une exigence fantome dans le bucket {@code :active} pour une regle
     * qui ne peut pas s'appliquer au survol ou a l'appui seuls - {@link #exigencesDuTiers} calcule
     * donc l'ecart sur l'union des etats que <b>la regle courante</b> porte, pas sur l'etat en cours
     * d'iteration.
     */
    private static final Map<String, Set<String>> PROPRIETES_TIERCES_ECARTEES_PAR_ETAT =
            Map.of(":focus-visible", Set.of("box-shadow"));

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
     *
     * <p><b>s15 (lot marc-dev, exigence 1 d'ADR-0046) : la dette est payee, le plafond baisse a
     * vide dans le meme commit que le correctif.</b> Les sept exigences ouvertes ci-dessus sont
     * closes par six regles de feuille (un couple {@code :hover, :active} par groupe de boutons
     * partageant deja une regle de repos - {@code .chip-niveau}, {@code .retour-accueil} et les
     * cinq boutons pleins ensemble). Le meme commit reintroduit {@code .lien-accueil} dans
     * l'en-tete (DEC-0034) : cette huitieme exigence est payee des son introduction, jamais
     * laissee ouverte le temps d'un commit. Un plafond qui ne nomme plus rien de reel n'est plus un
     * cliquet (exigence 1) : la carte reste vide plutot que de citer une dette honoree.
     *
     * <p><b>s16 (lot lea-dev, S16-3/S16-4, amendement de l'ADR) : une dette neuve ouverte par la
     * correction de la derivation, payee dans le meme commit.</b> La derivation ne voyait pas les
     * sujets composes ({@code .chip-niveau.est-actif}, {@code .proposition.deja-essayee}) : elle ne
     * les exigeait pas et ne comptait pas non plus leurs propres etats declares - trou trouve par
     * mutation par {@code sofia-sm}. Une fois la derivation corrigee, {@code .chip-niveau.est-actif}
     * declarait deja ses deux etats et passe au vert en etant enfin verifiee ; seule
     * {@code .proposition.deja-essayee} ouvrait une dette reelle (4 exigences : {@code color} et
     * {@code background-color} a {@code :hover} et {@code :active}, mesure par {@code theo-archi}).
     * Fermee dans ce meme commit par les deux regles de feuille correspondantes, forme neutre -
     * S15-8, S15-9. Le plafond reste donc vide.
     */
    private static final Map<String, Set<String>> DETTE_A_L_OUVERTURE = Map.of();

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
     *
     * <p>Le resultat peut etre un sujet compose de plusieurs classes ({@code .chip-niveau.est-actif}) :
     * {@link #sujetApplicable} en juge, jamais une egalite de chaine avec un selecteur simple.
     */
    private static String elementDuCompose(String reste) {
        int deuxPoints = reste.indexOf(':');
        return (deuxPoints < 0 ? reste : reste.substring(0, deuxPoints)).strip();
    }

    /**
     * Un sujet - simple ou compose - s'applique-t-il a ce bouton rendu ? Amendement ADR-0046 (s16) :
     * un selecteur compose est un sujet distinct, jamais un alias de sa premiere classe. Il
     * s'applique des que l'element rendu porte <b>au moins</b> toutes les classes qu'il nomme -
     * exactement la semantique CSS d'un selecteur compose, qu'il soit ecrit {@code .a.b} ou
     * {@code .b.a}. Un sujet sans aucune classe est une balise nue : il ne s'applique qu'a un bouton
     * qui n'a lui-meme aucune classe et qui porte cette balise, {@code selecteursApplicables} ne
     * rendant une balise que dans ce cas precis.
     */
    private static boolean sujetApplicable(String sujet, TailleDesTextesEtBoutonsTest.BoutonRendu bouton) {
        Set<String> classesDuSujet = LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur(sujet);
        if (!classesDuSujet.isEmpty()) {
            return bouton.classes().containsAll(classesDuSujet);
        }
        return bouton.classes().isEmpty() && sujet.equals(bouton.bouton().tagName());
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

    /**
     * Le compose de classes du meme element que ce selecteur gouverne reellement, quel que soit son
     * prefixe : le sujet (dernier compose, {@code sujetDuSelecteur}) depouille de tout etat interactif
     * puis de toute pseudo-classe structurelle restante ({@link #elementDuCompose}). {@code
     * body:has(.filtre-niveau) .chip-niveau.est-actif} et {@code .chip-niveau.est-actif:hover}
     * rendent tous deux {@code .chip-niveau.est-actif} : un sujet compose n'est pas moins un sujet
     * distinct parce qu'une regle qui le porte est elle-meme scopee a une descendance.
     */
    private static String composeDuMemeElement(String selecteur) {
        return elementDuCompose(
                etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(selecteur)).reste());
    }

    /**
     * Les classes que la feuille maison connait, au repos comme a un etat interactif : celles que
     * nomme le compose de classes du meme element de tout selecteur maison, quel que soit son prefixe
     * de descendance. Sert a decouper l'<b>identite</b> d'un bouton rendu de ses classes purement
     * tierces ({@code btn}, que la feuille maison ne nomme jamais seule).
     */
    private static Set<String> classesConnuesDeLaMaison(Map<String, String> maison) {
        Set<String> classes = new LinkedHashSet<>();
        for (String selecteur : maison.keySet()) {
            classes.addAll(
                    LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur(
                            composeDuMemeElement(selecteur)));
        }
        return classes;
    }

    /**
     * L'identite d'un bouton rendu : le sujet compose de ses classes connues de la feuille maison,
     * dans l'ordre du gabarit. Amendement ADR-0046 (s16) - un selecteur compose est un sujet distinct :
     * deux appuis d'une meme classe de base qui declarent des apparences differentes
     * ({@code .proposition} et {@code .proposition.deja-essayee}) sont deux pressables distincts, et
     * l'identite de chacun est ce a quoi ses propres etats doivent etre rattaches - jamais a celle
     * d'un sujet plus simple que le meme element porte aussi. Rendue en {@code .a.b}, jamais {@code
     * .b.a} : l'ordre est celui du gabarit, le meme que celui dans lequel les regles maison composees
     * sont ecrites (verifie sur {@code .chip-niveau.est-actif} et {@code .proposition.deja-essayee}).
     */
    private static String identiteDuBouton(
            Set<String> classesConnues, TailleDesTextesEtBoutonsTest.BoutonRendu bouton) {
        StringBuilder identite = new StringBuilder();
        for (String classe : bouton.classes()) {
            if (classesConnues.contains(classe)) {
                identite.append('.').append(classe);
            }
        }
        return identite.toString();
    }

    /**
     * Les proprietes que la feuille maison declare au repos et qui s'appliquent reellement a ce
     * bouton : celles de chaque compose de classes du meme element ({@link #sujetApplicable}) que le
     * bouton porte au moins - sujet simple ({@code .proposition}) comme sujet compose ({@code
     * .proposition.deja-essayee}), toutes reunies dans un seul ensemble puisqu'elles s'appliquent
     * simultanement au meme element rendu.
     */
    private static Set<String> reposMaison(
            Map<String, String> maison, TailleDesTextesEtBoutonsTest.BoutonRendu bouton) {
        Set<String> repos = new LinkedHashSet<>();
        for (Map.Entry<String, String> regle : maison.entrySet()) {
            String selecteur = regle.getKey();
            boolean porteUnEtatInteractif = !etatsDuCompose(
                    TailleDesTextesEtBoutonsTest.sujetDuSelecteur(selecteur)).etats().isEmpty();
            if (porteUnEtatInteractif || !sujetApplicable(composeDuMemeElement(selecteur), bouton)) {
                continue;
            }
            repos.addAll(proprietesRendues(regle.getValue()));
        }
        return repos;
    }

    /**
     * Les proprietes que la feuille maison declare a chaque etat interactif, par sujet exact et par
     * etat - independant de tout bouton particulier, c'est un fait de la feuille elle-meme.
     *
     * <p>Amendement ADR-0046 (s16) : la cle est desormais le sujet exact, jamais un ensemble
     * d'etats fusionne pour tout sujet applicable. C'est ce qui rend {@link #sujetApplicable} a lui
     * seul insuffisant a fermer le trou trouve par mutation : une declaration sur {@code
     * .proposition:hover} ne satisfait plus une exigence posee sur l'identite {@code
     * .proposition.deja-essayee}, exactement le mode de defaillance que la mutation de
     * {@code sofia-sm} sur {@code .chip-niveau.est-actif} a revele - une declaration sur
     * {@code .chip-niveau:hover} seul ne doit pas non plus suffire a couvrir cette identite-la.
     * Chaque pressable distinct declare ses propres etats, jamais ceux d'un sujet plus simple qu'il
     * porte aussi.
     */
    private static Map<String, Map<String, Set<String>>> etatsMaisonParSujet(Map<String, String> maison) {
        Map<String, Map<String, Set<String>>> declares = new LinkedHashMap<>();
        for (Map.Entry<String, String> regle : maison.entrySet()) {
            EtatsDuCompose lecture =
                    etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(regle.getKey()));
            if (lecture.etats().isEmpty()) {
                continue;
            }
            String sujet = composeDuMemeElement(regle.getKey());
            for (String etat : lecture.etats()) {
                declares.computeIfAbsent(sujet, inconnu -> new LinkedHashMap<>())
                        .computeIfAbsent(etat, inconnu -> new LinkedHashSet<>())
                        .addAll(proprietesRendues(regle.getValue()));
            }
        }
        return declares;
    }

    /**
     * Les exigences que la feuille tierce fait peser sur ce bouton, rattachees a son identite : pour
     * chaque regle tierce dont le sujet est applicable et porteur d'un ou plusieurs etats <b>tous</b>
     * gardes, chaque propriete declaree - hors celles ecartees a cet etat precis par
     * {@link #PROPRIETES_TIERCES_ECARTEES_PAR_ETAT} - qui touche une propriete gouvernee au repos par
     * la maison.
     */
    private static Map<Exigence, Set<String>> exigencesDuTiers(
            Map<String, String> tiers, TailleDesTextesEtBoutonsTest.BoutonRendu bouton,
            String identite, Set<String> repos) {
        Map<Exigence, Set<String>> exigences = new LinkedHashMap<>();
        for (Map.Entry<String, String> regle : tiers.entrySet()) {
            EtatsDuCompose lecture =
                    etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(regle.getKey()));
            if (lecture.etats().isEmpty() || !ETATS_GARDES.containsAll(lecture.etats())
                    || !sujetApplicable(composeDuMemeElement(regle.getKey()), bouton)) {
                continue;
            }
            Set<String> ecarteesParCetteRegle = lecture.etats().stream()
                    .flatMap(etat -> PROPRIETES_TIERCES_ECARTEES_PAR_ETAT
                            .getOrDefault(etat, Set.of()).stream())
                    .collect(Collectors.toSet());
            for (String etat : lecture.etats()) {
                for (String proprieteTierce : proprietesRendues(regle.getValue())) {
                    if (ecarteesParCetteRegle.contains(proprieteTierce)) {
                        continue;
                    }
                    for (String proprieteMaison : repos) {
                        if (touche(proprieteMaison, proprieteTierce)) {
                            exigences.computeIfAbsent(
                                    new Exigence(identite, etat, proprieteMaison),
                                    inconnue -> new LinkedHashSet<>())
                                    .add(regle.getKey());
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
        Set<String> classesConnues = classesConnuesDeLaMaison(maison);
        Map<String, Map<String, Set<String>>> declaresParSujet = etatsMaisonParSujet(maison);
        Map<Exigence, Set<String>> retenues = new LinkedHashMap<>();

        for (TailleDesTextesEtBoutonsTest.BoutonRendu bouton
                : TailleDesTextesEtBoutonsTest.etatsRendus(racineMaison)) {
            String identite = identiteDuBouton(classesConnues, bouton);
            Set<String> repos = reposMaison(maison, bouton);

            for (Map.Entry<Exigence, Set<String>> exigence
                    : exigencesDuTiers(tiers, bouton, identite, repos).entrySet()) {
                Exigence attendue = exigence.getKey();
                Set<String> declarees = declaresParSujet
                        .getOrDefault(attendue.selecteurMaison(), Map.of())
                        .getOrDefault(attendue.etat(), Set.of());
                boolean satisfaite = declarees.stream()
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

    /** Les pseudo-classes qui prennent la specificite de leur argument le plus specifique. */
    private static final Set<String> PSEUDOS_TRANSPARENTS = Set.of("is", "not", "has", "matches");

    /** {@code :where()} ne compte jamais, quel que soit son argument. */
    private static final Set<String> PSEUDOS_SANS_SPECIFICITE = Set.of("where");

    /**
     * <b>ADR-0046, amendement du 2026-08-19 (s17) : declarer ne suffit pas, il faut l'emporter.</b>
     *
     * <p>La specificite d'un selecteur, compactee en un entier comparable
     * ({@code a * 10000 + b * 100 + c}) : identifiants, puis classes, attributs et pseudo-classes,
     * puis elements et pseudo-elements. Aucune retenue n'est atteignable en pratique - la feuille
     * servie ne depasse pas quatre selecteurs simples par compose.</p>
     *
     * <p><b>Une construction que cette lecture ne sait pas lire est un echec, jamais un cas ignore</b>
     * (ADR-0013 obligation 2), et le sens de l'erreur compte ici plus qu'ailleurs : une specificite
     * maison surevaluee est un <b>faux vert</b>, exactement la famille de defaut que cet amendement
     * ferme. Refuser ce qu'on ne sait pas lire est la seule direction sure.</p>
     *
     * <p>Les pseudo-classes fonctionnelles suivent la specification : {@code :is()}, {@code :not()} et
     * {@code :has()} prennent la specificite de leur argument le plus specifique et ne comptent pas
     * pour elles-memes, {@code :where()} vaut zero. Ce n'est pas un raffinement theorique - l'une des
     * deux formes reelles qui ont produit le defaut mesure par {@code nadia-prof} en depend :
     * {@code :not(.btn-check)+.btn:active} vaut (0,3,0) par cette regle, et (0,2,0) pour une lecture
     * qui ignorerait l'argument, c'est-a-dire exactement la valeur qui la ferait passer pour perdante
     * alors qu'elle gagne.</p>
     */
    static int specificite(String selecteur) {
        int[] compte = new int[3];
        lireSpecificite(selecteur, compte);
        return compte[0] * 10000 + compte[1] * 100 + compte[2];
    }

    private static void lireSpecificite(String selecteur, int[] compte) {
        int position = 0;
        while (position < selecteur.length()) {
            char caractere = selecteur.charAt(position);
            if (Character.isWhitespace(caractere) || caractere == '>' || caractere == '+'
                    || caractere == '~' || caractere == ',' || caractere == '*') {
                position++;
            } else if (caractere == '#') {
                position = finDuJeton(selecteur, position + 1);
                compte[0]++;
            } else if (caractere == '.') {
                position = finDuJeton(selecteur, position + 1);
                compte[1]++;
            } else if (caractere == '[') {
                int fin = selecteur.indexOf(']', position);
                if (fin < 0) {
                    fail("selecteur illisible pour la specificite (crochet non ferme) : " + selecteur);
                }
                position = fin + 1;
                compte[1]++;
            } else if (caractere == ':') {
                position = lirePseudo(selecteur, position, compte);
            } else if (Character.isLetter(caractere)) {
                position = finDuJeton(selecteur, position);
                compte[2]++;
            } else {
                fail("caractere illisible pour la specificite (" + caractere + ") dans \"" + selecteur
                        + "\" : une specificite maison surevaluee serait un faux vert, ce garde-fou "
                        + "refuse donc ce qu'il ne sait pas lire (ADR-0046, ADR-0013 obligation 2)");
            }
        }
    }

    private static int lirePseudo(String selecteur, int position, int[] compte) {
        boolean pseudoElement =
                position + 1 < selecteur.length() && selecteur.charAt(position + 1) == ':';
        int debut = position + (pseudoElement ? 2 : 1);
        int fin = finDuJeton(selecteur, debut);
        String nom = selecteur.substring(debut, fin);

        if (fin >= selecteur.length() || selecteur.charAt(fin) != '(') {
            if (pseudoElement) {
                compte[2]++;
            } else {
                compte[1]++;
            }
            return fin;
        }

        int fermante = parentheseFermante(selecteur, fin);
        String argument = selecteur.substring(fin + 1, fermante);
        if (PSEUDOS_TRANSPARENTS.contains(nom)) {
            int meilleure = 0;
            for (String branche : argument.split(",")) {
                int[] partiel = new int[3];
                lireSpecificite(branche, partiel);
                meilleure = Math.max(meilleure, partiel[0] * 10000 + partiel[1] * 100 + partiel[2]);
            }
            compte[0] += meilleure / 10000;
            compte[1] += (meilleure % 10000) / 100;
            compte[2] += meilleure % 100;
        } else if (!PSEUDOS_SANS_SPECIFICITE.contains(nom)) {
            compte[1]++;
        }
        return fermante + 1;
    }

    private static int parentheseFermante(String selecteur, int ouvrante) {
        int profondeur = 0;
        for (int position = ouvrante; position < selecteur.length(); position++) {
            char caractere = selecteur.charAt(position);
            if (caractere == '(') {
                profondeur++;
            } else if (caractere == ')') {
                profondeur--;
                if (profondeur == 0) {
                    return position;
                }
            }
        }
        fail("parenthese non fermee dans le selecteur \"" + selecteur + "\"");
        return selecteur.length();
    }

    private static int finDuJeton(String selecteur, int position) {
        int fin = position;
        while (fin < selecteur.length()
                && (Character.isLetterOrDigit(selecteur.charAt(fin))
                        || selecteur.charAt(fin) == '-' || selecteur.charAt(fin) == '_')) {
            fin++;
        }
        if (fin == position) {
            fail("jeton vide dans le selecteur \"" + selecteur + "\"");
        }
        return fin;
    }

    /**
     * <b>Cette regle tierce peut-elle reellement s'appliquer a ce bouton rendu ?</b> Evaluee sur
     * l'element du gabarit lui-meme, avec le jeu de classes de <b>cet etat rendu</b> - donc prefixes
     * compris : ancetres, freres, pseudo-classes structurelles.
     *
     * <p><b>Pourquoi ce predicat n'existe que pour la clause de specificite, et pourquoi il ne doit
     * surtout pas entrer dans la clause de declaration.</b> La clause de declaration rattache une
     * regle tierce a un bouton par son seul <b>compose du meme element</b> ({@link #sujetApplicable}),
     * donc elle sur-collecte : elle exige une declaration maison a cause de regles qui ne peuvent
     * jamais s'appliquer, comme {@code .btn-check+.btn:hover} - aucun gabarit de ce depot ne porte
     * {@code btn-check}. Cette sur-collecte est <b>sans danger la-bas</b> : elle demande une
     * declaration de plus, et une declaration de plus ne casse rien. Elle serait <b>fausse ici</b> :
     * exiger que la feuille maison batte en specificite une regle qui ne concourt jamais obligerait a
     * prefixer huit selecteurs par un identifiant pour un adversaire imaginaire. Mesure du
     * 2026-08-19 : sans ce predicat, cette clause rend <b>14 motifs sur 8 pressables</b>, tous
     * imputables a la seule regle {@code .btn-check+.btn:hover}.</p>
     *
     * <p>Un selecteur tiers que le moteur de selection ne sait pas evaluer est un <b>echec</b>, jamais
     * un cas ignore (ADR-0013 obligation 2) : le prendre pour non applicable serait un faux vert.</p>
     */
    private static boolean regleTierceSApplique(
            String selecteurTiers, TailleDesTextesEtBoutonsTest.BoutonRendu bouton) {
        String sansEtats = selecteurTiers;
        for (String etat : TOUS_LES_ETATS) {
            sansEtats = sansEtats.replace(etat, "");
        }
        sansEtats = sansEtats.strip();
        if (sansEtats.isEmpty()) {
            return true;
        }
        Element element = bouton.bouton();
        String classesDuGabarit = element.className();
        element.attr("class", String.join(" ", bouton.classes()));
        try {
            return element.is(sansEtats);
        } catch (RuntimeException illisible) {
            fail("selecteur tiers que le moteur de selection ne sait pas evaluer : \"" + selecteurTiers
                    + "\" (" + illisible.getMessage() + "). Le prendre pour non applicable serait un "
                    + "faux vert (ADR-0046, ADR-0013 obligation 2)");
            return false;
        } finally {
            element.attr("class", classesDuGabarit);
        }
    }

    /** Une regle maison telle qu'elle est ecrite : son selecteur complet et ce qu'elle declare. */
    private record RegleMaison(String selecteur, Set<String> proprietes) {
    }

    /**
     * Les regles maison qui portent un etat interactif, indexees par sujet puis par etat, avec leur
     * <b>selecteur complet</b>. {@link #etatsMaisonParSujet} n'en garde que les proprietes : c'est
     * assez pour dire "cet etat est declare", jamais pour dire "cette declaration l'emporte".
     */
    private static Map<String, Map<String, List<RegleMaison>>> reglesMaisonParSujetEtEtat(
            Map<String, String> maison) {
        Map<String, Map<String, List<RegleMaison>>> regles = new LinkedHashMap<>();
        for (Map.Entry<String, String> regle : maison.entrySet()) {
            EtatsDuCompose lecture =
                    etatsDuCompose(TailleDesTextesEtBoutonsTest.sujetDuSelecteur(regle.getKey()));
            if (lecture.etats().isEmpty()) {
                continue;
            }
            String sujet = composeDuMemeElement(regle.getKey());
            Set<String> proprietes = new LinkedHashSet<>(proprietesRendues(regle.getValue()));
            for (String etat : lecture.etats()) {
                regles.computeIfAbsent(sujet, inconnu -> new LinkedHashMap<>())
                        .computeIfAbsent(etat, inconnu -> new ArrayList<>())
                        .add(new RegleMaison(regle.getKey(), proprietes));
            }
        }
        return regles;
    }

    /**
     * <b>ADR-0046, amendement 3 : chaque etat declare par la maison l'emporte sur la regle tierce qui
     * l'imposait.</b>
     *
     * <p>Defaut mesure par {@code nadia-prof} sous un appui reel (s17) : deux pressables tombent de
     * 9,25:1 a <b>1,64:1</b> et {@code .demarrer} perd son aplat, alors que la feuille maison
     * declarait bien l'etat. Cause : {@code .btn:first-child:active} et
     * {@code :not(.btn-check)+.btn:active} valent (0,3,0) contre (0,2,0) pour le sujet maison - la
     * declaration existait, elle ne gagnait pas. {@code ADR-0014} point 5 ne joue qu'a specificite
     * <b>egale</b>, c'est ecrit dans cet ADR depuis son premier amendement, et cette derivation ne le
     * verifiait pas. Troisieme trou de la meme famille sur la meme derivation, apres les deux de s16.
     *
     * <p>La comparaison accepte l'<b>egalite</b> : la feuille maison est chargee apres la feuille
     * tierce (ADR-0014), elle gagne donc les egalites. Seule une specificite strictement inferieure
     * est un defaut.</p>
     *
     * <p><b>Ce qu'elle ne voit pas, et qu'elle n'introduit pas</b> : comme la clause de declaration
     * qu'elle complete, elle rattache une regle maison a un sujet par {@link #composeDuMemeElement},
     * donc <b>sans verifier qu'un prefixe d'ancetre s'applique reellement</b> au bouton rendu. Une
     * regle {@code #jeu .demarrer:active} serait creditee a {@code .demarrer} meme si ce bouton
     * vivait hors de {@code #jeu}. L'angle mort est herite, pas ajoute - mais il devient plus expose
     * depuis que l'ajout d'un ancetre est la sortie recommandee pour gagner de la specificite
     * (S17-D). Dette nommee dans l'amendement d'ADR-0046, avec son declencheur et le motif de ne pas
     * la fermer ici : un ancetre peut etre rendu sous condition par le gabarit, et le fermer de
     * travers produirait des rouges sur des ancetres conditionnels parfaitement legitimes.</p>
     */
    static void verifierQueLaMaisonLEmporteSurChaqueRegleTierce(Path racineMaison, Path racineTiers) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racineMaison);
        Map<String, Map<String, List<RegleMaison>>> reglesMaison = reglesMaisonParSujetEtEtat(maison);
        Map<Exigence, Set<String>> toutesLesExigences = exigences(racineMaison, racineTiers, false);
        Set<String> aEvaluer = toutesLesExigences.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Map<String, Set<String>> applicablesParIdentite =
                reglesTiercesApplicablesParIdentite(racineMaison, aEvaluer);
        List<String> motifs = new ArrayList<>();
        List<String> comparees = new ArrayList<>();

        toutesLesExigences.forEach((exigence, origines) -> {
            List<RegleMaison> declarantes =
                    reglesMaison.getOrDefault(exigence.selecteurMaison(), Map.of())
                            .getOrDefault(exigence.etat(), List.of()).stream()
                            .filter(regle -> regle.proprietes().stream()
                                    .anyMatch(declaree -> touche(exigence.propriete(), declaree)))
                            .toList();
            Set<String> concurrentes = origines.stream()
                    .filter(origine -> applicablesParIdentite
                            .getOrDefault(exigence.selecteurMaison(), Set.of()).contains(origine))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            if (declarantes.isEmpty() || concurrentes.isEmpty()) {
                return;
            }
            int maisonLaPlusForte = declarantes.stream()
                    .mapToInt(regle -> specificite(regle.selecteur())).max().orElseThrow();
            String tiersLePlusFort = concurrentes.stream()
                    .max((gauche, droite) -> Integer.compare(specificite(gauche), specificite(droite)))
                    .orElseThrow();
            comparees.add(exigence.selecteurMaison() + " " + exigence.etat() + " "
                    + exigence.propriete());
            if (maisonLaPlusForte < specificite(tiersLePlusFort)) {
                motifs.add(String.format(
                        "%s declare %s a l'etat %s, mais la regle tierce \"%s\" l'emporte par "
                                + "specificite (%d contre %d, en (a,b,c) compactee) : l'enfant voit la "
                                + "peinture de la bibliotheque, pas celle de la feuille maison. "
                                + "Declarer ne suffit pas - ADR-0014 point 5 ne joue qu'a specificite "
                                + "egale. La sortie est un ancetre reel separe par un combinateur, "
                                + "jamais une classe de plus sur le sujet, qui changerait l'identite "
                                + "calculee de chaque bouton du produit (ADR-0046, amendement du "
                                + "2026-08-19)",
                        exigence.selecteurMaison(), exigence.propriete(), exigence.etat(),
                        tiersLePlusFort, maisonLaPlusForte, specificite(tiersLePlusFort)));
            }
        });

        assertThat(comparees)
                .as("ADR-0003 : aucune exigence declaree n'a ete comparee sous %s ; cette clause "
                        + "serait verte sans avoir rien examine", racineMaison)
                .isNotEmpty();
        assertThat(motifs)
                .as("ADR-0046 : un etat declare par la feuille maison doit l'emporter sur la regle "
                        + "tierce qui l'imposait")
                .isEmpty();
    }

    /**
     * Pour chaque identite de bouton rendu, les selecteurs tiers qui s'y appliquent <b>reellement</b>
     * ({@link #regleTierceSApplique}). Meme population de boutons et meme identite que
     * {@link #exigences} : les deux derivations ne peuvent pas diverger, elles partent des memes
     * {@code etatsRendus} et du meme {@code identiteDuBouton}.
     */
    private static Map<String, Set<String>> reglesTiercesApplicablesParIdentite(
            Path racineMaison, Set<String> aEvaluer) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racineMaison);
        Set<String> classesConnues = classesConnuesDeLaMaison(maison);
        Map<String, Set<String>> applicables = new LinkedHashMap<>();
        for (TailleDesTextesEtBoutonsTest.BoutonRendu bouton
                : TailleDesTextesEtBoutonsTest.etatsRendus(racineMaison)) {
            String identite = identiteDuBouton(classesConnues, bouton);
            for (String selecteurTiers : aEvaluer) {
                if (regleTierceSApplique(selecteurTiers, bouton)) {
                    applicables.computeIfAbsent(identite, inconnue -> new LinkedHashSet<>())
                            .add(selecteurTiers);
                }
            }
        }
        return applicables;
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

    /** ADR-0046, amendement 3 : la clause de specificite sur la production servie. */
    @Test
    void chaqueEtatDeclareParLaMaisonLEmporteSurLaRegleTierceQuiLImpose() {
        verifierQueLaMaisonLEmporteSurChaqueRegleTierce(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la morsure sur <b>la feuille servie reelle, remise dans sa forme
     * d'avant S17-D</b> - jamais une fixture ecrite de ma main. La mutation retire l'ancetre
     * {@code #jeu} que {@code marc-dev} a ajoute pour gagner la specificite : le selecteur redevient
     * {@code .demarrer:active} (0,2,0) face a {@code .btn:first-child:active} (0,3,0), c'est-a-dire
     * exactement l'etat de la feuille au moment ou {@code nadia-prof} a mesure 1,64:1 sous l'appui.
     *
     * <p><b>Et c'est la mesure qui compte le plus dans ce lot</b> : sur cette meme feuille mutee,
     * {@link #verifierQueChaqueEtatTiersEstRedeclareParLaMaison} reste <b>vert</b>. La declaration est
     * la, elle ne gagne pas ; sans cette clause neuve, la CI ne voit rien.</p>
     */
    @Test
    void laFeuilleRemiseDansSaFormeDAvantS17DFaitEchouerLaClauseDeSpecificite(@TempDir Path racine)
            throws IOException {
        copierArbreReel(RACINE_PRODUCTION, racine);
        Path feuille = racine.resolve("static/css/recreo.css");
        String reelle = Files.readString(feuille).replace("\r\n", "\n");
        String avant = "#jeu .demarrer:active";
        String apres = ".demarrer:active";
        assertThat(reelle)
                .as("la mutation doit porter sur une regle que la feuille servie porte vraiment - "
                        + "l'ancetre ajoute par S17-D")
                .contains(avant);
        Files.writeString(feuille, reelle.replace(avant, apres));

        verifierQueChaqueEtatTiersEstRedeclareParLaMaison(racine);

        assertThatThrownBy(() -> verifierQueLaMaisonLEmporteSurChaqueRegleTierce(racine, RACINE_PRODUCTION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".demarrer")
                .hasMessageContaining("l'emporte par specificite");
    }

    /**
     * La lecture de specificite, eprouvee sur les <b>formes reelles des deux feuilles</b> et non sur
     * des selecteurs inventes pour elle. Les deux premieres sont les regles tierces que
     * {@code nadia-prof} a mesurees comme gagnantes ; les deux suivantes sont des regles maison
     * servies. Sans le traitement de {@code :not()} comme transparent, la deuxieme vaudrait (0,2,0) -
     * elle passerait pour perdante alors qu'elle gagne, et la clause serait verte sur le defaut meme
     * qu'elle ferme.
     */
    @Test
    void laSpecificiteEstLueSurLesFormesReellesDesDeuxFeuilles() {
        assertThat(specificite(".btn:first-child:active")).isEqualTo(300);
        assertThat(specificite(":not(.btn-check)+.btn:active")).isEqualTo(300);
        assertThat(specificite(".demarrer:active")).isEqualTo(200);
        assertThat(specificite("#jeu .demarrer:active")).isEqualTo(10200);
        assertThat(specificite("body:has(.filtre-niveau) .chip-niveau.est-actif")).isEqualTo(301);
        assertThat(specificite(":where(.btn) .proposition")).isEqualTo(100);
        assertThat(specificite("*")).isZero();
    }

    /**
     * ADR-0013 obligation 2 : une construction que la lecture ne sait pas lire est un echec. Le sens
     * de cette clause est le point : refuser plutot que sous-evaluer, parce qu'une specificite maison
     * surevaluee - ou une specificite tierce sous-evaluee - serait un faux vert.
     */
    @Test
    void unSelecteurIllisiblePourLaSpecificiteFaitEchouerLaLecture() {
        assertThatThrownBy(() -> specificite(".btn:not(.btn-check"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("parenthese non fermee");
        assertThatThrownBy(() -> specificite(".demarrer%actif"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("caractere illisible");
    }

    /** Copie l'arbre de ressources servi - gabarits et feuilles - pour le muter sans le toucher. */
    private static void copierArbreReel(Path source, Path cible) throws IOException {
        try (Stream<Path> arborescence = Files.walk(source)) {
            for (Path chemin : arborescence.toList()) {
                Path destination = cible.resolve(source.relativize(chemin).toString());
                if (Files.isDirectory(chemin)) {
                    Files.createDirectories(destination);
                } else {
                    Files.createDirectories(destination.getParent());
                    Files.copy(chemin, destination);
                }
            }
        }
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
     *
     * <p>Amendement ADR-0046 (s16) : l'identite lue ici est {@code .chip-niveau.est-actif}, jamais
     * {@code .chip-niveau} seul. {@code th:classappend} n'etant pas une branche conditionnelle pour
     * {@code TailleDesTextesEtBoutonsTest.etatsDe} - il ajoute son literal a l'unique etat rendu, quel
     * que soit le niveau demande - la puce de niveau releve dans les gabarits porte toujours
     * {@code est-actif} dans la population de ce fichier. Avant cet amendement, cette identite
     * composee sortait du perimetre (le meme trou que celui trouve par mutation par {@code sofia-sm})
     * et ce test se lisait par coincidence sur le sujet simple ; il decrit desormais le sujet reel.
     */
    @Test
    void leSurvolTiersEcraseLesTroisProprietesDeLaPuceDeNiveau() {
        Set<String> attendues = new LinkedHashSet<>();
        exigences(RACINE_PRODUCTION, RACINE_PRODUCTION, false).keySet().stream()
                .filter(exigence -> exigence.selecteurMaison().equals(".chip-niveau.est-actif")
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
     * Le motif de l'exclusion de {@code box-shadow} a {@code :focus-visible}, mesure sur la feuille
     * vendorisee reelle plutot qu'argumente - meme geste que l'exclusion de {@code transform} par
     * ADR-0033. Ce test epingle les trois faits dont l'exclusion depend, et rougit le jour ou l'un
     * tombe : la bibliotheque declare bien l'anneau de focus en ombre portee, un bouton maison declare
     * bien une ombre portee au repos, et {@code box-shadow} est bien hors des proprietes comparees a
     * cet etat.
     *
     * <p>Second amendement d'ADR-0046 (s16) : ce test est <b>reformule, jamais supprime</b>. Sa
     * troisieme assertion passe de "cet etat est hors du perimetre garde" - faux depuis que
     * {@code :focus-visible} y entre - a "{@code box-shadow} est hors des proprietes comparees a cet
     * etat", qui reste vrai. Le nom suit ce changement : un nom qui promet autre chose que ce que le
     * test verifie est la meme famille de defaut qu'une reference qui pointe vers rien. Le jour ou la
     * bibliotheque cesse d'utiliser une ombre, l'exclusion n'est plus une exception constatee mais une
     * question ouverte.
     */
    @Test
    void lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtBoxShadowResteHorsDesProprietesCompareesACetEtat() {
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(RACINE_PRODUCTION);
        assertThat(proprietesRendues(tiers.getOrDefault(".btn:focus-visible", "")))
                .as("la bibliotheque vendorisee doit toujours porter l'anneau de focus clavier par "
                        + "une ombre portee sous .btn:focus-visible (ADR-0030 point 2)")
                .contains("box-shadow");

        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(RACINE_PRODUCTION);
        assertThat(proprietesRendues(maison.getOrDefault(".demarrer", "")))
                .as("un bouton maison doit declarer une ombre portee au repos, sans quoi l'exclusion "
                        + "de box-shadow a :focus-visible serait sans objet et sans motif")
                .contains("box-shadow");

        assertThat(PROPRIETES_TIERCES_ECARTEES_PAR_ETAT.getOrDefault(":focus-visible", Set.of()))
                .as("box-shadow doit rester ecartee des proprietes comparees a :focus-visible, sans "
                        + "quoi ce garde-fou exigerait un box-shadow maison a cet etat et pousserait "
                        + "a ecraser l'anneau de focus clavier de la bibliotheque")
                .contains("box-shadow");
    }
}
