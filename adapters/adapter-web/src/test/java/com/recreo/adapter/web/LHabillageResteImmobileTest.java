package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.proprietesDeclarees;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.sujet;
import static com.recreo.adapter.web.LHabillageNeRogneJamaisUnElementPressableTest.PRESSABLES;
import static com.recreo.adapter.web.LHabillageNeRogneJamaisUnElementPressableTest.motsDeClasse;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0033 : <b>un habillage ne change pas d'etat sous le doigt de l'enfant.</b> Aucune regle maison
 * dont le sujet nomme une classe d'habillage ne declare {@code transition*} ni {@code animation*}, et
 * aucune n'est conditionnee par une pseudo-classe interactive.
 *
 * <p>C'est la moitie manquante de la condition C3 de {@code nadia-prof} (arbitrage prealable du
 * 2026-08-11) : "aucune animation, aucun clignotement, aucun changement de couleur, aucune lumiere
 * sur le cabinet lui-meme, a aucun moment". La clause 9 d'ADR-0030 en a pris ce qui s'anime ; ce qui
 * restait dehors, et qu'ADR-0030 nomme lui-meme comme laisse a {@code nadia-prof}, ce sont
 * {@code transition} et les etats interactifs. Un
 * {@code .cabinet-bandeau:hover::after { background-color: ...; transition: background-color 0.3s }}
 * ecrit dans six mois passerait <b>toute</b> la CI verte avant cette classe. Mesure, pas hypothese :
 * la meme regle ajoutee a {@code recreo.css} fait rougir cette classe seule, les 26 autres classes de
 * test du module restant vertes (ADR-0033, tableau de morsure).
 *
 * <p><b>Mesure, avec sa methode.</b> Perimetre : les feuilles maison, c'est-a-dire {@code static/}
 * hors {@code static/vendor/} (ADR-0014), soit un seul fichier, {@code css/recreo.css}. Zero
 * occurrence de {@code transition}. Une seule occurrence litterale de {@code :focus}, et elle est
 * <b>dans un commentaire</b> - celui qui cite {@code .btn:focus-visible} de la bibliotheque : ce que
 * lit cette classe est le flux dont les commentaires ont ete retires, donc zero. Aucune occurrence de
 * {@code :hover}, {@code :active} ni {@code :target}. La feuille vendorisee, elle, en porte 47 et 12
 * respectivement pour {@code :hover} et {@code :focus-visible}, et cette classe n'en lit aucune - un
 * garde-fou maison ne juge pas le CSS tiers (ADR-0014).
 *
 * <p><b>Pourquoi maintenant et pas a ADR-0030.</b> ADR-0030 avait refuse cette clause, et le motif
 * ecrit etait faux : "la partie non animee de C3 n'a aujourd'hui aucune population dans la feuille
 * maison". La population d'une interdiction n'est pas l'ensemble de ses violations, c'est l'ensemble
 * des sujets qu'elle gouverne - et cet ensemble n'est pas vide : <b>33</b> regles maison ont pour
 * sujet une classe d'habillage, sur <b>29</b> classes d'habillage derivees de <b>38</b> classes
 * posees moins <b>9</b> classes pressables (methode : sonde jetable non commitee sur
 * {@code src/main/resources}, comptage des couples selecteur-corps rendus par {@code reglesMaison} -
 * une regle ecrite avec deux selecteurs compte pour deux). ADR-0030 avait accepte sa propre clause de
 * rognage sur exactement ce critere
 * ("sur la feuille livree elle ne mord sur rien [...] sa population d'ancetres est reelle et
 * derivee") et refuse celle-ci sur l'autre : c'est la meme mesure appliquee dans deux sens.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : l'habillage est le <b>complementaire</b> du pressable - toute classe
 *       posee dans un gabarit, moins toute classe portee par un element {@link
 *       LHabillageNeRogneJamaisUnElementPressableTest#PRESSABLES}. Aucun nom d'habillage n'est ecrit
 *       ici : un manche, un fronton, un socle, une bande future y entrent le jour ou le gabarit les
 *       pose. Le pressable est derive par le meme litteral qu'ADR-0017, et par la meme lecture de
 *       classes, pour que les deux garde-fous d'habillage ne se contredisent pas ;</li>
 *   <li><b>derivation totale</b> : la liste des proprietes interdites n'est pas ecrite mais
 *       <b>prefixee</b> - tout nom declare qui commence par {@code transition} ou {@code animation}
 *       est refuse, y compris ceux que je ne connais pas. Et la population n'exclut <b>pas</b> les
 *       regles ecrites sous {@code prefers-reduced-motion}, a la difference de la clause 9 : c'est le
 *       trou que le jeu fautif {@code animation-sous-mouvement-reduit} mesure ci-dessous ;</li>
 *   <li><b>mise a l'epreuve</b> : trois jeux d'artefacts neufs, dont le manche dessine tel que le
 *       sprint 10 le pose et son jumeau fautif au seul survol pres.</li>
 * </ul>
 *
 * <p><b>Limite nommee, et elle joue dans le mauvais sens.</b> La surcollecte des expressions
 * Thymeleaf est inoffensive quand elle grossit une population interdite ; ici elle grossit le
 * pressable, donc elle <b>retranche</b> de l'habillage. Mesure : sur les gabarits livres, elle ne
 * retranche qu'un jeton, {@code enSuite}, tokenise depuis
 * {@code th:class="${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'"} - un nom de variable de
 * modele, qu'aucune regle maison ne nomme et qui n'est la classe de rien. Le jour ou une variable de
 * modele porte le nom d'une classe d'habillage reelle, cette classe sort silencieusement du controle.
 * Le remede n'est pas de deviner les expressions : c'est que le nom d'une classe d'habillage ne soit
 * pas aussi un nom de variable, ce que {@code HabillageDesEcransTest} rend deja visible en exigeant
 * qu'une classe posee soit nommee par une regle servie.
 *
 * <p><b>Ce que cette clause ne garde pas.</b> Un changement d'etat decide par le <b>serveur</b> reste
 * autorise, et c'est voulu : {@code .signal.est-juste} et {@code .signal.est-faux} changent l'aplat du
 * bandeau de verdict selon une classe que le serveur pose (ADR-0008). Ce que la clause refuse est le
 * changement decide par le <b>client</b>, sans que le serveur en sache rien - c'est la meme frontiere
 * qu'ADR-0008, lue du cote du CSS. Et elle ne garde pas ce que peint une image : une lumiere dessinee
 * dans un degrade reste a {@code nadia-prof} (ADR-0025).
 */
class LHabillageResteImmobileTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LHabillageResteImmobileTest");

    /**
     * Les prefixes de nom de propriete par lesquels une regle declare que quelque chose change dans le
     * temps. {@code transform} n'y est pas, et ce n'est pas un oubli : voir
     * {@link #laCroixDuSignalFauxDeclareTransformSurUnHabillageEtResteVerte}.
     */
    private static final List<String> PREFIXES_DE_CHANGEMENT_D_ETAT = List.of("transition", "animation");

    /**
     * Les pseudo-classes par lesquelles le <b>client</b> decide qu'une regle s'applique. {@code :focus}
     * couvre {@code :focus-visible} et {@code :focus-within} par prefixe - les deux sont refusees sur
     * un habillage pour la meme raison que {@code :focus} tout court, et il n'existe pas de
     * pseudo-classe innocente dont le nom commence par ces quatre-la.
     */
    private static final List<String> PSEUDO_CLASSES_INTERACTIVES =
            List.of(":hover", ":active", ":focus", ":target");

    /**
     * Les classes d'habillage : toute classe posee dans un gabarit, moins toute classe portee par un
     * element pressable. Derivation par complementaire et non par enumeration - c'est ce qui fait
     * entrer un habillage neuf sans que ce fichier change.
     */
    static Set<String> classesDHabillage(Path racine) {
        Set<String> posees = new TreeSet<>();
        Set<String> pressables = new TreeSet<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.getAllElements()) {
                posees.addAll(motsDeClasse(element));
            }
            for (Element pressable : document.select(PRESSABLES)) {
                pressables.addAll(motsDeClasse(pressable));
            }
        }
        posees.removeAll(pressables);
        return posees;
    }

    private static boolean declareUnChangementDEtat(String propriete) {
        return PREFIXES_DE_CHANGEMENT_D_ETAT.stream().anyMatch(propriete::startsWith);
    }

    static void verifierQueLHabillageResteImmobile(Path racine) {
        Set<String> habillage = classesDHabillage(racine);
        assertThat(habillage)
                .as("ADR-0003 : cette clause ne prouve rien si aucune classe des gabarits de %s n'est "
                        + "de l'habillage, c'est-a-dire posee sans etre portee par un %s", racine,
                        PRESSABLES)
                .isNotEmpty();

        List<String> examinees = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (LAnimationRetardeMaisNeDetermineRienTest.Regle regle : reglesMaison(racine)) {
            Set<String> visees = new TreeSet<>(classesDuSelecteur(sujet(regle.selecteur())));
            visees.retainAll(habillage);
            if (visees.isEmpty()) {
                continue;
            }
            examinees.add(regle.origine());
            for (String propriete : proprietesDeclarees(regle.corps())) {
                if (declareUnChangementDEtat(propriete)) {
                    defauts.add(regle.origine() + " : cette regle declare " + propriete + " sur "
                            + visees + ", des classes d'habillage - aucun element pressable ne les "
                            + "porte. Un habillage qui change dans le temps agit ; une machine "
                            + "immobile est un meuble que l'enfant actionne. \"Aucune animation, aucun "
                            + "clignotement, aucun changement de couleur, aucune lumiere sur le cabinet "
                            + "lui-meme, a aucun moment\" (nadia-prof, arbitrage prealable du "
                            + "2026-08-11, condition C3 ; ADR-0033)");
                }
            }
            for (String pseudo : PSEUDO_CLASSES_INTERACTIVES) {
                if (regle.selecteur().toLowerCase(Locale.ROOT).contains(pseudo)) {
                    defauts.add(regle.origine() + " : cette regle est conditionnee par \"" + pseudo
                            + "\" et son sujet nomme " + visees + ", des classes d'habillage. C'est le "
                            + "client qui decide alors de quoi le cabinet a l'air, sans que le serveur "
                            + "en sache rien : un habillage qui s'allume sous le doigt est un "
                            + "changement d'etat sur le cabinet lui-meme, refuse par la condition C3. "
                            + "Un etat decide par le serveur, lui, reste autorise - c'est ce que font "
                            + ".signal.est-juste et .signal.est-faux (ADR-0008, ADR-0033)");
                }
            }
        }
        assertThat(examinees)
                .as("ADR-0003 : cette clause ne prouve rien si aucune regle maison de %s n'a pour "
                        + "sujet une classe d'habillage. C'est cette population, et non le nombre de "
                        + "violations, qui dit si l'interdiction porte sur quelque chose", racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0033 : aucune regle maison d'habillage ne declare de changement d'etat ni "
                        + "n'est conditionnee par une pseudo-classe interactive")
                .isEmpty();
    }

    @Test
    void aucunHabillageDeProductionNeChangeDEtat() {
        verifierQueLHabillageResteImmobile(RACINE_PRODUCTION);
    }

    /**
     * <b>L'exclusion mesuree de {@code transform}, et le cliquet qui la tient.</b> {@code transform}
     * n'entre pas dans les prefixes interdits, et ce n'est pas une commodite : la croix du signal faux
     * est dessinee par deux regles qui declarent {@code transform: rotate(45deg)} sur
     * {@code .icone-signal::before} et {@code ::after}, et {@code icone-signal} <b>est</b> une classe
     * d'habillage au sens derive - aucun bouton ne la porte. L'inclure rougirait un decor statique
     * legitime.
     *
     * <p>La clause interdit le changement d'etat, pas la geometrie : une rotation declaree une fois
     * pour toutes ne change jamais, elle est. Ce test epingle les deux faits sur lesquels repose
     * l'exclusion, pour que le jour ou quelqu'un ajoute {@code transform} a la liste "par symetrie",
     * ce soit ici que la CI le lui dise, avec le motif.
     */
    @Test
    void laCroixDuSignalFauxDeclareTransformSurUnHabillageEtResteVerte() {
        Set<String> habillage = classesDHabillage(RACINE_PRODUCTION);
        List<String> habillagesQuiTransforment = new ArrayList<>();
        for (LAnimationRetardeMaisNeDetermineRienTest.Regle regle : reglesMaison(RACINE_PRODUCTION)) {
            Set<String> visees = new TreeSet<>(classesDuSelecteur(sujet(regle.selecteur())));
            visees.retainAll(habillage);
            if (!visees.isEmpty() && proprietesDeclarees(regle.corps()).contains("transform")) {
                habillagesQuiTransforment.add(regle.origine());
            }
        }
        assertThat(habillagesQuiTransforment)
                .as("l'exclusion de transform n'est mesuree que s'il existe reellement un habillage qui "
                        + "en declare un : les deux barres de la croix du signal faux. Si cette liste "
                        + "devient vide, l'exclusion cesse d'etre une exception constatee et redevient "
                        + "une question ouverte (ADR-0033)")
                .isNotEmpty();

        verifierQueLHabillageResteImmobile(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3, l'objet neuf. Le manche dessine du sprint 10, sur
     * {@code .cabinet-bandeau::after} en {@code position: absolute}, augmente d'une seule regle : il
     * s'eclaire au survol, avec la transition qui adoucit l'eclairage. C'est nommement le cas dont
     * {@code nadia-prof} ecrit qu'il fait tomber son avis avec lui, et il passait toute la CI verte -
     * la clause 9 d'ADR-0030 ne voit que ce qui declare une animation.
     */
    @Test
    void unMancheQuiSAllumeAuSurvolFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("manche-qui-s-allume");
        assertThatThrownBy(() -> verifierQueLHabillageResteImmobile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cette regle declare transition")
                .hasMessageContaining("est conditionnee par \":hover\"");
    }

    /**
     * Le meme manche, corrige par le seul retrait de la regle de survol : il doit redevenir vert, avec
     * son {@code content}, sa decoupe, sa couleur et sa position absolue intacts. Sans ce cas, la
     * clause serait une interdiction du manche deguisee en clause d'immobilite, et aucun jeu fautif ne
     * le dirait - la lecon de la seconde mutation d'ADR-0025.
     */
    @Test
    void leMemeMancheSansSurvolNiTransitionResteVert() {
        verifierQueLHabillageResteImmobile(CONTRE_EXEMPLES.resolve("manche-immobile"));
    }

    /**
     * <b>Le trou de la clause 9, mesure plutot qu'affirme.</b> La population des animations d'ADR-0030
     * ecarte les regles ecrites sous {@code prefers-reduced-motion} - il fallait bien qu'elle les
     * ecarte, c'est la qu'un repli declare {@code animation: none}. Consequence non voulue : un
     * habillage qui clignote <b>uniquement</b> pour l'enfant qui a demande un ecran immobile echappe a
     * la clause 9 entierement.
     *
     * <p>Ce test l'etablit dans les deux sens sur le meme jeu d'artefacts : la clause 9 reste
     * <b>verte</b>, celle-ci rougit. C'est ce qui repond a "qu'est-ce que cette clause interdirait
     * reellement qu'une autre n'interdit pas".
     */
    @Test
    void uneAnimationDHabillageSousMouvementReduitEchappeALaClause9EtNonACelleCi() {
        Path racine = CONTRE_EXEMPLES.resolve("animation-sous-mouvement-reduit");

        LAnimationRetardeMaisNeDetermineRienTest
                .verifierQueChaqueAnimationPorteSurUnElementPressable(racine);

        assertThatThrownBy(() -> verifierQueLHabillageResteImmobile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cette regle declare animation");
    }
}
