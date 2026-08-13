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
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0030 : <b>aucune regle maison n'installe un contexte de rognage sur un ancetre d'un element
 * pressable.</b>
 *
 * <p>Le defaut que cette clause ferme est etabli, chiffre, et il n'est pas hypothetique. La
 * bibliotheque tierce vendorisee declare, sur tout {@code .btn} :
 * {@code .btn:focus-visible { outline: 0; box-shadow: var(--bs-btn-focus-box-shadow) }}. L'anneau de
 * focus clavier de ce depot n'est donc <b>pas</b> un {@code outline} - il est explicitement supprime -
 * mais une <b>ombre portee</b>, peinte a l'exterieur de la boite du bouton. Une ombre portee est
 * rognee par le rognage d'un ancetre. Or les colonnes de reponses occupent toute la largeur
 * interieure de ce qui les entoure : le budget horizontal est epuise a 360 px, et {@code nadia-prof}
 * a refuse nommement que le cadre se paye sur le {@code gap} ou sur le rembourrage (arbitrage
 * prealable du 2026-08-11, section 3). Il ne reste donc <b>aucun pixel</b> ou l'ombre puisse etre
 * peinte : un {@code overflow: hidden} sur un habillage qui entoure les colonnes rend l'anneau de
 * focus <b>invisible sur la premiere et la derniere colonne</b> - a deux colonnes, sur les deux.
 *
 * <p>Un second effet, distinct et pire : {@code overflow: hidden} fait de l'element un conteneur de
 * defilement. Le navigateur qui donne le focus a un element rogne fait defiler ce conteneur pour le
 * ramener dans le champ, et les colonnes bougent a l'interieur de leur cadre sans qu'aucun geste de
 * l'enfant ne l'ait demande. C'est l'argument par lequel j'avais moi-meme, en s07, ecarte une fenetre
 * en {@code overflow: hidden} au profit d'un pseudo-element ; un habillage qui entoure les colonnes le
 * reintroduit, et c'est pourquoi cette clause existe maintenant.
 *
 * <p><b>Ce que cette clause ne garde pas.</b> Elle ne verifie pas que l'anneau de focus est
 * <b>visible</b> : c'est une propriete du rendu, qu'aucun automate de ce depot ne produit (ADR-0025),
 * et elle appartient a {@code nadia-prof}. Ce qui est garde ici est declaratif et strictement plus
 * etroit : aucune regle maison ne pose, sur un ancetre d'un element pressable, une propriete dont
 * l'effet est de rogner ce qui depasse.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la population des classes d'ancetres vient du balayage des gabarits,
 *       en remontant la chaine des parents de chaque element pressable. Le pressable est derive par le
 *       meme selecteur qu'{@code LActionNeDeclarePasMoinsQueLaSortieTest} - {@code button, a[href]} -
 *       pour que les deux garde-fous s'accordent sur ce qu'est un bouton. Aucun nom de classe
 *       d'habillage n'est ecrit ici : un cabinet, un bandeau, un socle ou tout habillage a venir entre
 *       dans cette population le jour ou le gabarit le pose, sans que ce fichier change ;</li>
 *   <li><b>derivation totale</b> : une valeur de rognage qu'on ne sait pas classer est traitee comme
 *       rognante, et non ignoree. Seul {@code visible} - la valeur qui ne rogne rien - est acceptee ;</li>
 *   <li><b>mise a l'epreuve</b> : deux jeux d'artefacts neufs, la forme proposee pour le cabinet telle
 *       qu'elle est ecrite, et la meme corrigee par le seul retrait du rognage.</li>
 * </ul>
 *
 * <p><b>Ce que cette clause vaut aujourd'hui, dit franchement.</b> Aucune feuille maison ne declare de
 * propriete de rognage a ce jour : sur la production, cette clause ne mord sur rien. Elle mord sur la
 * forme exacte qu'{@code ines-design} propose pour le cabinet, et c'est pour cela qu'elle est ecrite
 * <b>avant</b> l'implementation plutot qu'apres. Sa population d'ancetres, elle, n'est pas vide et est
 * derivee - c'est elle qui fera entrer le cabinet dans le controle sans qu'on y pense.
 */
class LHabillageNeRogneJamaisUnElementPressableTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LHabillageNeRogneJamaisUnElementPressableTest");

    /**
     * Le pressable, derive comme dans LActionNeDeclarePasMoinsQueLaSortieTest.
     *
     * <p>Visible dans le paquet depuis ADR-0033 : {@code LHabillageResteImmobileTest} definit
     * l'habillage comme le <b>complementaire</b> du pressable, et deux garde-fous qui n'appelleraient
     * pas "pressable" la meme chose se contrediraient sans que rien ne le dise - une classe sortie de
     * la population des ancetres ici et absente de l'habillage la-bas ne serait gardee par ni l'un ni
     * l'autre. Un seul litteral, lu par les deux.
     */
    static final String PRESSABLES = "button, a[href]";

    /** Les proprietes dont une valeur peut rogner ce qui depasse d'une boite. */
    private static final List<String> PROPRIETES_DE_ROGNAGE =
            List.of("overflow", "overflow-x", "overflow-y", "clip-path");

    /** La seule valeur d'{@code overflow} qui ne rogne rien. Tout le reste rogne. */
    private static final String SANS_ROGNAGE = "visible";

    /**
     * Les classes portees par un <b>ancetre</b> d'un element pressable, dans les gabarits. L'element
     * lui-meme est exclu : une regle de rognage sur le bouton, ou sur son pseudo-element decoratif, est
     * le voile du rouleau de S7-1, qui est legitime et deja garde ailleurs (ADR-0027 clause 8).
     */
    static Set<String> classesDesAncetresDePressables(Path racine) {
        Set<String> classes = new TreeSet<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element pressable : document.select(PRESSABLES)) {
                for (Element ancetre = pressable.parent(); ancetre != null;
                        ancetre = ancetre.parent()) {
                    classes.addAll(motsDeClasse(ancetre));
                }
            }
        }
        return classes;
    }

    /**
     * Les classes qu'un element peut porter au rendu, en clair ou par Thymeleaf. La surcollecte des
     * expressions Thymeleaf est voulue et sans danger : elle ajoute des verifications, jamais n'en
     * retire, et son mode de defaillance est un rouge.
     *
     * <p>Visible dans le paquet depuis ADR-0033, meme motif que {@link #PRESSABLES} : la clause
     * d'immobilite lit les classes d'un element exactement comme celle-ci, sans quoi les deux
     * populations d'habillage seraient calculees par deux lectures libres de diverger. La phrase
     * ci-dessus sur l'innocuite de la surcollecte ne vaut d'ailleurs que <b>pour ce fichier</b>, ou
     * elle grossit une population interdite : ADR-0033 la reprend en sens inverse, ou elle retranche
     * de l'habillage, et l'y traite comme une limite nommee.
     */
    static Set<String> motsDeClasse(Element element) {
        Set<String> classes = new LinkedHashSet<>(element.classNames());
        for (String porteur : List.of("th:class", "th:classappend")) {
            String valeur = element.attr(porteur);
            StringBuilder mot = new StringBuilder();
            for (int position = 0; position <= valeur.length(); position++) {
                char caractere = position < valeur.length() ? valeur.charAt(position) : ' ';
                if (Character.isLetterOrDigit(caractere) || caractere == '-' || caractere == '_') {
                    mot.append(caractere);
                } else {
                    if (mot.length() > 0) {
                        classes.add(mot.toString());
                    }
                    mot.setLength(0);
                }
            }
        }
        return classes;
    }

    static void verifierQuAucunHabillageNeRogneUnPressable(Path racine) {
        Set<String> ancetres = classesDesAncetresDePressables(racine);
        assertThat(ancetres)
                .as("ADR-0003 : cette clause ne prouve rien si aucun element pressable (%s) des "
                        + "gabarits de %s n'a d'ancetre porteur de classe", PRESSABLES, racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (LAnimationRetardeMaisNeDetermineRienTest.Regle regle
                : LAnimationRetardeMaisNeDetermineRienTest.reglesMaison(racine)) {
            Set<String> visees = classesDuSelecteur(regle.selecteur());
            visees.retainAll(ancetres);
            if (visees.isEmpty()) {
                continue;
            }
            for (String propriete : PROPRIETES_DE_ROGNAGE) {
                int position = debutDeDeclaration(regle.corps(), propriete);
                if (position < 0) {
                    continue;
                }
                String valeur = valeurDeclaree(regle.corps(), position, propriete)
                        .strip().toLowerCase(Locale.ROOT);
                if ("overflow".equals(propriete) || propriete.startsWith("overflow-")) {
                    if (valeur.equals(SANS_ROGNAGE)) {
                        continue;
                    }
                }
                defauts.add(regle.origine() + " : cette regle declare " + propriete + ": " + valeur
                        + " sur " + visees + ", des classes que porte un ancetre d'un element "
                        + "pressable. L'anneau de focus clavier de ce depot est une ombre portee - la "
                        + "bibliotheque vendorisee declare outline: 0 sur .btn:focus-visible - donc il "
                        + "est peint hors de la boite du bouton et un ancetre qui rogne le fait "
                        + "disparaitre. Les colonnes occupant toute la largeur interieure de leur "
                        + "habillage, il ne reste aucun pixel pour cet anneau : un enfant au clavier ne "
                        + "verrait plus ou il est sur la premiere ni sur la derniere colonne. Un "
                        + "overflow different de visible fait de plus de cet element un conteneur de "
                        + "defilement, que le navigateur peut faire defiler pour ramener le focus dans "
                        + "le champ - les colonnes bougeraient alors sans geste de l'enfant (ADR-0030)");
            }
        }
        assertThat(defauts)
                .as("ADR-0030 : aucune regle maison ne rogne un ancetre d'un element pressable")
                .isEmpty();
    }

    /** Les classes qu'un selecteur nomme : tout ce qui suit un point. */
    private static Set<String> classesDuSelecteur(String selecteur) {
        Set<String> classes = new LinkedHashSet<>();
        int position = 0;
        while ((position = selecteur.indexOf('.', position)) >= 0) {
            StringBuilder mot = new StringBuilder();
            for (int index = position + 1; index < selecteur.length(); index++) {
                char caractere = selecteur.charAt(index);
                if (Character.isLetterOrDigit(caractere) || caractere == '-' || caractere == '_') {
                    mot.append(caractere);
                } else {
                    break;
                }
            }
            if (mot.length() > 0) {
                classes.add(mot.toString());
            }
            position++;
        }
        return classes;
    }

    @Test
    void aucuneRegleMaisonNeRogneUnAncetreDePressable() {
        verifierQuAucunHabillageNeRogneUnPressable(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est la forme proposee pour le cabinet, recopiee telle
     * qu'`ines-design` l'ecrit - {@code overflow: hidden} sur le conteneur qui entoure les colonnes,
     * pose la pour que les coins arrondis rognent les enfants.
     */
    @Test
    void unHabillageQuiRogneLesColonnesFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("habillage-qui-rogne");
        assertThatThrownBy(() -> verifierQuAucunHabillageNeRogneUnPressable(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("des classes que porte un ancetre d'un element pressable");
    }

    /**
     * Le meme habillage, sans la propriete de rognage : il doit redevenir vert. Sans ce cas, la clause
     * serait une interdiction du cabinet deguisee en clause de focus, et aucun jeu fautif ne le dirait.
     */
    @Test
    void leMemeHabillageSansRognageResteVert() {
        verifierQuAucunHabillageNeRogneUnPressable(CONTRE_EXEMPLES.resolve("habillage-sans-rognage"));
    }
}
