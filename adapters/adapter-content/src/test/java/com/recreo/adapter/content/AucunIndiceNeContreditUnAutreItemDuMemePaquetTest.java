package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Motif du refus de nadia-prof sur "cm1-conjugaison-present"
 * ({@code squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md}, section 2,
 * 2026-08-10) : cinq indices sur douze enoncaient une regle de conjugaison generale, et trois
 * d'entre eux etaient dementis par un autre item du meme paquet - a la manche 7, l'indice
 * affirmait "avec 'tu', le verbe se termine par '-es'", et a la manche 10 la bonne reponse etait
 * "finis". <b>Le jeu se contredisait devant l'enfant, dans le seul endroit ou il enseigne.</b>
 *
 * <p>La justesse d'une regle de grammaire n'est pas gardable par un automate - nadia-prof le dit
 * elle-meme dans cette fiche, section 3 : "cette regle de grammaire est-elle vraie" n'est pas une
 * propriete qu'un test peut trancher. Ce qui l'est : qu'aucune regle generale enoncee par un
 * indice de ce paquet ne soit dementie par la bonne reponse d'un autre item du meme paquet portant
 * le meme sujet. C'est exactement la propriete mecanique qui a mordu nadia-prof en jouant.
 *
 * <p>Le paquet distingue deja lui-meme, dans son propre texte, une regle temperee ("se termine
 * <i>presque toujours</i> par", items "lisons" et "mangez") et une regle nommant un verbe precis
 * ("le verbe 'finir' se termine par", items "finis", "courons", "prenez") d'une regle generale non
 * temperee ("le verbe se termine par", sans nommer ni temperer) - c'etait la forme fautive du
 * refus. Ce garde-fou reconnait cette troisieme forme et verifie qu'elle ne dement aucun autre
 * item du paquet ou elle apparait.
 *
 * <p>Perimetre derive du repertoire de contenu (ADR-0013, meme forme que
 * {@link ChaquePaquetPorteUnIndiceTest}), pas d'une liste de paquets : un paquet neuf portant un
 * indice general dementi par un autre de ses items est juge des qu'il existe.</p>
 *
 * <p><b>Correction du 2026-08-12 par theo-archi (retro de s09, point 1), sur la forme de la
 * non-vacuite et non sur la propriete.</b> La clause de production parcourait bien les paquets, mais
 * son assertion etait enfermee dans un {@code if} sur {@link #REGLE_GENERALE_NON_TEMPEREE}, et sa
 * clause de non-vacuite verifiait que la liste des <b>paquets</b> n'etait pas vide - jamais qu'un
 * <b>indice</b> avait ete examine. Elle gardait la mauvaise population : le corps de la comparaison
 * ne s'executait sur aucun contenu reel, et rien dans le fichier ne le disait a l'endroit ou on le
 * lit. Trois fiches de conformite de {@code nadia-prof} ont credite ce garde-fou d'une coherence
 * entre items qu'il ne tenait pas, jusqu'a ce qu'elle lise le fichier de test elle-meme.</p>
 *
 * <p>Trois changements, aucun sur la propriete verifiee :</p>
 * <ul>
 *   <li>{@link #verifierQuAucunIndiceNeContreditUnAutreItem} <b>rend</b> le nombre d'indices
 *       reellement examines. C'est la population de la clause, et c'est desormais elle que les
 *       appelants confrontent a un chiffre ;</li>
 *   <li>le cliquet de l'etat reel est porte par ce nombre-la, dans le meme test que la clause
 *       ({@link #aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet()}) et non plus par un
 *       second parcours qui relisait le meme motif a cote. Deux lectures pouvaient s'ecarter l'une de
 *       l'autre ; il n'y en a plus qu'une. Le plafond vaut exactement la taille de la dette - zero -
 *       et le message dit quoi faire le jour ou ce n'est plus vrai ;</li>
 *   <li>un troisieme jeu d'artefacts ({@code general-tenu}) etablit que ce compteur n'est pas
 *       coince a zero pour une raison de code : sur un paquet qui <b>porte</b> la forme generale sans
 *       la dementir, il vaut 1 et la clause passe. Sans lui, "zero indice examine en production" et
 *       "compteur en panne" seraient le meme vert.</li>
 * </ul>
 *
 * <p>Ce que cette correction ne fait pas, et il faut le dire : elle ne rend pas la clause mordante
 * sur le contenu livre. Elle rend <b>visible et chiffree</b> le fait qu'elle ne l'est pas. Le
 * garde-fou reste ecrit pour la regression - un paquet neuf qui retomberait dans la forme fautive du
 * refus - exactement comme la clause de rognage d'ADR-0030 a ete ecrite avant le cabinet.</p>
 */
class AucunIndiceNeContreditUnAutreItemDuMemePaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, reproduisant la contradiction manche 7 / manche 10. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunIndiceNeContreditUnAutreItemDuMemePaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunIndiceNeContreditUnAutreItemDuMemePaquetTest";

    /**
     * Tirages effectues sur un paquet <b>combinatoire</b>, le seul qui n'enumere aucun item et ne
     * s'epuise donc jamais. ADR-0064 : sur un paquet enumere, la borne n'est pas ecrite ici, elle
     * est derivee de ce que le paquet declare, et une lecture partielle est un echec nomme.
     */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    /**
     * Une regle generale non temperee : "Avec 'tu', le verbe se termine par '-es'". Exclut
     * naturellement les deux formes correctrices deja dans le paquet : temperer insere "presque
     * toujours" entre "termine" et "par" (items "lisons", "mangez") ; nommer le verbe insere son
     * nom entre "verbe" et "se termine" (items "finis", "courons", "prenez", et desormais les cinq
     * indices corriges) - ni l'une ni l'autre ne peut matcher ce motif exact, qui exige "verbe se
     * termine par" mot pour mot.
     */
    private static final Pattern REGLE_GENERALE_NON_TEMPEREE = Pattern.compile(
            "[Aa]vec '([^']+)'(?: ou '([^']+)')?, le verbe se termine par '-([^']+)'");

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les items reellement tires par la chaine de contenu, pas relus dans le YAML. */
    private static List<Item> itemsTires(ContentSource contenu, int tirages) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < tirages; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            items.add(item);
            dejaVus.add(item.id());
        }
        return items;
    }

    /**
     * Determine le sujet grammatical d'un item a partir du pronom sujet ecrit dans sa phrase.
     * Rend null si aucun des six pronoms n'est trouve : un item sur un autre sujet ne peut pas
     * etre confronte a une regle de personne, il est ignore par la comparaison, jamais mis en
     * echec pour cela.
     */
    private static String sujetGrammatical(String enonce) {
        for (String mot : enonce.toLowerCase().split("[^a-zàâäéèêëïîôöùûüçœ]+")) {
            switch (mot) {
                case "je" -> {
                    return "je";
                }
                case "tu" -> {
                    return "tu";
                }
                case "il", "elle" -> {
                    return "il/elle";
                }
                case "nous" -> {
                    return "nous";
                }
                case "vous" -> {
                    return "vous";
                }
                case "ils", "elles" -> {
                    return "ils/elles";
                }
                default -> {
                }
            }
        }
        return null;
    }

    /** Canonicalise un pronom capture dans un indice sur les six sujets reconnus par {@link #sujetGrammatical}. */
    private static String canonicaliserPersonne(String pronom) {
        return switch (pronom.toLowerCase()) {
            case "il", "elle" -> "il/elle";
            case "ils", "elles" -> "ils/elles";
            default -> pronom.toLowerCase();
        };
    }

    /**
     * Rend le nombre d'indices <b>examines</b> : ceux dont le texte porte la forme generale non
     * temperee et qui ont donc ete confrontes a chaque autre item de leur paquet. Ce nombre est la
     * population de cette clause, la seule dont la non-vacuite dit quelque chose sur elle - la liste
     * des paquets, elle, ne dit que ce que le repertoire contient (ADR-0003, correction du 2026-08-12).
     */
    static int verifierQuAucunIndiceNeContreditUnAutreItem(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("le balayage ne trouve aucun paquet sous %s : il n'y a rien a lire, et ce n'est pas "
                        + "la meme chose qu'une verification passee. Ce que cette clause prouve se lit "
                        + "sur le nombre d'indices examines qu'elle rend, pas ici", racine)
                .isNotEmpty();

        int indicesExamines = 0;
        for (String paquet : paquets) {
            // ADR-0064 : la borne vient du paquet, jamais d'un nombre ecrit ici. De quoi
            // l'epuiser, plus un tirage pour constater l'epuisement.
            int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racine, paquet);
            int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
            List<Item> tires =
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet), tirages);
            // Lot 5a de s24, decision 2 : etendu a l'item de saisie. La propriete met en rapport un
            // indice et une reponse juste, et l'item de saisie porte les deux - sa forme de
            // reference est sa bonne reponse. Un paquet de saisie qui affirmerait une regle generale
            // dementie par un autre de ses items se contredirait devant l'enfant de la meme facon.
            List<ClassementDesItems.ItemLu> items = ClassementDesItems.lus(tires);
            if (declares > 0) {
                // ADR-0064 : un paquet enumere est lu EN ENTIER, ou ce garde-fou est rouge et le dit.
                assertThat(items)
                        .as("%s declare %d items sur disque et ce garde-fou n'en a tire que %d : la"
                                + " clause porterait sur un echantillon sans le dire", paquet, declares,
                                items.size())
                        .hasSize(declares);
            }

            for (ClassementDesItems.ItemLu itemPortantLIndice : items) {
                Matcher regle = REGLE_GENERALE_NON_TEMPEREE.matcher(itemPortantLIndice.indice().orElse(""));
                if (!regle.find()) {
                    continue;
                }
                indicesExamines++;
                Set<String> personnesVisees = new HashSet<>();
                personnesVisees.add(canonicaliserPersonne(regle.group(1)));
                if (regle.group(2) != null) {
                    personnesVisees.add(canonicaliserPersonne(regle.group(2)));
                }
                String terminaison = regle.group(3);

                for (ClassementDesItems.ItemLu autreItem : items) {
                    String sujet = sujetGrammatical(autreItem.enonce());
                    if (sujet == null || !personnesVisees.contains(sujet)) {
                        continue;
                    }
                    assertThat(autreItem.reponseJuste())
                            .as("paquet %s : l'indice de \"%s\" enonce une regle generale (terminaison"
                                            + " '-%s') que la bonne reponse \"%s\" de l'item \"%s\" dement",
                                    paquet, itemPortantLIndice.enonce(), terminaison,
                                    autreItem.reponseJuste(), autreItem.enonce())
                            .endsWith(terminaison);
                }
            }
        }
        return indicesExamines;
    }

    /**
     * La clause de production, <b>et son cliquet dans le meme test</b>. Les deux vont ensemble : la
     * premiere assertion dit qu'aucune contradiction n'a ete trouvee, la seconde dit sur combien
     * d'indices elle l'a cherchee. Separees, la premiere se lisait comme une garantie de coherence
     * alors qu'elle ne parcourait aucun indice ; ensemble, elles ne peuvent plus etre lues l'une sans
     * l'autre.
     *
     * <p>Le plafond vaut exactement la taille de la dette - <b>zero</b>, pas "quelques-uns" - parce
     * que la correction de s07 a remplace les cinq indices fautifs par la forme qui nomme le verbe. Le
     * jour ou ce chiffre monte, cette assertion rougit la premiere : retire-la, la clause porte alors
     * sur un objet reel et c'est une bonne nouvelle.
     */
    @Test
    void aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet() {
        int indicesExamines =
                verifierQuAucunIndiceNeContreditUnAutreItem(CONTENU_DE_PRODUCTION, "");

        assertThat(indicesExamines)
                .as("cliquet de l'etat reel (ADR-0003, retro de s09 point 1) : la clause ci-dessus"
                        + " n'examine aujourd'hui aucun indice de production, parce qu'aucun ne porte"
                        + " plus la forme generale non temperee que reconnait"
                        + " REGLE_GENERALE_NON_TEMPEREE. Elle est donc verte sans rien avoir compare,"
                        + " et c'est ce chiffre-la qui le dit - pas la liste des paquets, qui n'est"
                        + " jamais vide. Si cette assertion rougit, retire-la : la clause porte"
                        + " desormais sur un objet reel")
                .isEqualTo(0);
    }

    /** ADR-0013 obligation 3 : l'objet neuf est un paquet reproduisant la contradiction manche 7 / manche 10. */
    @Test
    void unPaquetPortantLaContradictionDuRefusFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunIndiceNeContreditUnAutreItem(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dement");
    }

    /**
     * Le meme paquet, une fois son indice general corrige, ne doit plus rien reprocher - et il n'examine
     * alors plus aucun indice, puisque la correction consiste precisement a quitter la forme generale.
     * Ce vert-la ne prouve donc rien a lui seul : c'est {@link
     * #unPaquetDontLaRegleGeneraleEstTenueEstExamineEtPasse()} qui prouve qu'un indice peut etre examine
     * et passer.
     */
    @Test
    void leMemePaquetUneFoisSonIndiceCorrigePasseLeGardeFou() {
        assertThat(verifierQuAucunIndiceNeContreditUnAutreItem(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige"))
                .as("la correction retire la forme generale : plus aucun indice n'est examine, et c'est"
                        + " exactement pourquoi ce vert ne suffit pas")
                .isZero();
    }

    /**
     * <b>Le cas qui distingue "aucun indice examine" de "compteur en panne"</b> (correction du
     * 2026-08-12, retro de s09 point 1). Le paquet porte la forme generale non temperee - "avec 'tu',
     * le verbe se termine par '-es'" - et <b>aucun</b> de ses items ne la dement. La clause doit donc
     * passer <b>en ayant compare</b>, et le nombre qu'elle rend doit valoir 1.
     *
     * <p>Sans ce jeu d'artefacts, un compteur qui rendrait toujours zero - un {@code if} mal place, un
     * motif casse par une retouche de formulation - satisferait le cliquet de la clause de production
     * aussi bien que la verite. C'est la meme faute que ce depot m'a deja apprise : une fixture qui
     * ressemble a ce que j'imagine, et un garde-fou qui passe sa propre epreuve en echouant dans le
     * monde.
     */
    @Test
    void unPaquetDontLaRegleGeneraleEstTenueEstExamineEtPasse() {
        assertThat(verifierQuAucunIndiceNeContreditUnAutreItem(
                CONTRE_EXEMPLE.resolve("general-tenu"), CONTRE_EXEMPLE_CLASSPATH + "/general-tenu"))
                .as("ce paquet porte la forme generale et ne la dement pas : la clause doit passer"
                        + " apres avoir examine exactement un indice. Si ce nombre vaut zero, ce n'est"
                        + " pas le contenu qui est en cause, c'est la lecture")
                .isEqualTo(1);
    }
}
