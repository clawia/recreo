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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0032 : <b>deux items d'un meme paquet fini ne presentent jamais le meme ecran.</b>
 *
 * <p>Ce garde-fou ne ferme pas un defaut constate, il ferme un <b>trou de morsure</b> dans
 * {@code ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest} (ADR-0023). Cette regle-la compte des
 * {@code ItemId} distincts, et {@code QuestionContentSourceFactory} derive l'identifiant d'un item de
 * sa <b>position</b> dans le YAML : deux items rigoureusement identiques portent donc deux
 * identifiants distincts et comptent pour deux. Un paquet peut ainsi satisfaire l'inegalite stricte
 * d'ADR-0023 en se remplissant de copies, c'est-a-dire verdir la regle sans livrer la propriete pour
 * laquelle elle existe - la variete d'une partie a l'autre.</p>
 *
 * <p><b>Ce que ce trou couterait, et il ne coute pas une preference de forme.</b> {@code nadia-prof}
 * a verifie a la main, en jouant, qu'une partie ne repete jamais un item :
 * {@code squad/conformite/FICHE-bandit-manchot-additions-cp.md}, section "0 bis" point C - vingt-quatre
 * parties jouees sans jamais reussir ni jamais rater, aucune repetition. Cette propriete ne tient que
 * parce que {@code ListContentSource.next} ecarte les {@code ItemId} deja tires. Elle devient fausse,
 * en silence et sans qu'aucun diff ne le montre, des que deux items d'un meme paquet portent le meme
 * ecran : le tirage reste sans remise sur les identifiants et redevient avec remise sur ce que
 * l'enfant lit.</p>
 *
 * <p><b>Le fait qui rend ce trou pressant maintenant.</b> L'entree de catalogue de chacun des quatre
 * jeux declare {@code nombreDeManches: 10}, donc ADR-0023 exige au moins onze items distincts. Une
 * competence d'arithmetique dont l'espace d'items est du meme ordre - "Complements a 10" en admet
 * <b>onze</b> sous la forme canonique {@code a + ? = 10} pour {@code a} de 0 a 10, neuf si les deux
 * cas degeneres sont ecartes - met donc l'auteur de contenu exactement sous la pression que ce
 * garde-fou refuse : completer le paquet par une copie plutot que par une forme d'item de plus. Le
 * chemin vert restant est de faire varier l'item ou de revoir {@code nombreDeManches} (ADR-0012,
 * decision produit), jamais de dupliquer.</p>
 *
 * <p><b>Ce que "le meme ecran" veut dire, et pourquoi pas "le meme enonce".</b> L'ecran de manche
 * montre l'enonce puis les propositions ; deux items de meme enonce mais de propositions differentes
 * sont deux ecrans differents, et cette forme est legitime ("Quel nombre est le plus grand ?" avec
 * deux nombres qui changent). Un critere sur le seul enonce l'aurait refusee. L'identite retenue est
 * donc le couple <b>enonce + jeu de propositions</b>, en ensemble et non en liste parce que la
 * mecanique melange l'ordre a chaque manche ({@code BanditManchotSession.melanger}) : deux items qui
 * ne differeraient que par l'ordre sont le meme ecran. L'indice n'entre pas dans cette identite : il
 * ne s'affiche qu'apres trois echecs consecutifs et ne distingue donc pas deux manches jouees.</p>
 *
 * <p><b>Mesure de l'etat actuel, avec sa methode, parce qu'un garde-fou ecrit avant son defaut doit
 * dire ce qu'il trouve aujourd'hui.</b> Sur les 397 items lisibles des 65 paquets du depot (les 4
 * paquets servis de {@code adapters/adapter-content/src/main/resources/contenu/} et les 61 paquets de
 * reserve de {@code contenu-repris/}), <b>aucun couple enonce + jeu de propositions n'apparait deux
 * fois</b> dans un meme paquet, et meme le critere plus strict de l'enonce seul est tenu partout.
 * Methode : lecture ligne a ligne des cles {@code enonce}/{@code question}, {@code bonneReponse}/{@code bonne}
 * et {@code distracteurs}, le decoupage des listes en ligne respectant les guillemets ; les 429 cles
 * {@code distracteurs} du depot sont toutes ecrites en ligne, donc aucune n'echappe a ce comptage.
 * Cette clause part donc verte sur du materiau reel, jamais sur une fixture ecrite pour elle.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici, et les items viennent du <b>chargeur reel</b> - c'est la chaine que l'enfant
 *       traverse, et l'identifiant positionnel qui ouvre ce trou n'existe qu'a l'interieur d'elle ;</li>
 *   <li><b>derivation totale</b> : un paquet qui ne rend aucun item est un defaut, pas une population
 *       vide toleree. Un paquet <b>combinatoire</b> est hors perimetre, et c'est dit plutot que
 *       silencieux : la distinction de ses tirages est une propriete de son algorithme, tenue par
 *       {@code AdditionsBorneesContentSourceTest} la ou l'algorithme vit, et un tel paquet finit
 *       toujours par rendre deux fois le meme couple des que son espace est presque epuise - ce qui
 *       est licite au-dela de la longueur d'une partie et ferait rougir cette clause pour rien. Le
 *       depart entre les deux n'est pas lu dans une cle du YAML mais <b>constate</b> : une source
 *       finie s'epuise et rend {@code Optional.empty()} avant le budget de tirages ;</li>
 *   <li><b>mise a l'epreuve</b> : trois paquets fautifs neufs, dont un corrige qui doit redevenir
 *       vert, et un cas qui doit rester vert (le meme enonce avec des propositions differentes) sans
 *       lequel cette clause serait une interdiction de reposer une question deguisee en clause de
 *       non-repetition.</li>
 * </ul>
 */
class AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : les paquets fautifs de ce garde-fou, jamais servis a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest";

    /**
     * Budget de tirages par paquet. Il sert a distinguer une source finie, qui s'epuise bien avant
     * (le plus gros paquet du depot en compte 25), d'une source combinatoire, qui ne s'epuise jamais.
     */
    private static final int BUDGET_DE_TIRAGES = 500;

    /** Ce que l'enfant voit d'un item sur l'ecran de manche : son enonce et ses propositions. */
    private record Ecran(String enonce, Set<String> propositions) {
    }

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

    /**
     * Tous les items que ce contenu sait rendre, tires par la chaine reelle avec le tirage deja-vu
     * qui grossit. Rend vide si la source ne s'epuise pas dans le budget : elle est combinatoire, et
     * l'appelant la laisse hors perimetre.
     */
    private static Optional<List<Question>> itemsDUneSourceFinie(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                return Optional.of(items);
            }
            Question question = (Question) tire.orElseThrow();
            items.add(question);
            dejaVus.add(question.id());
        }
        return Optional.empty();
    }

    private static Ecran ecranDe(Question question) {
        Set<String> propositions = new HashSet<>();
        propositions.add(question.bonneReponse());
        propositions.addAll(question.distracteurs());
        return new Ecran(question.enonce(), Set.copyOf(propositions));
    }

    static void verifierQuAucunItemNeRepeteLEcranDUnAutre(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        List<String> paquetsFinisJuges = new ArrayList<>();

        for (String paquet : paquets) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            Optional<List<Question>> finis = itemsDUneSourceFinie(contenu);
            if (finis.isEmpty()) {
                continue;
            }
            List<Question> items = finis.orElseThrow();
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();
            paquetsFinisJuges.add(paquet);

            Map<Ecran, String> premierPorteur = new HashMap<>();
            for (Question item : items) {
                String precedent = premierPorteur.putIfAbsent(ecranDe(item), item.id().value());
                if (precedent != null) {
                    defauts.add("paquet " + paquet + ", item " + item.id().value() + " : il presente le"
                            + " meme ecran que l'item " + precedent + " - meme enonce \"" + item.enonce()
                            + "\" et meme jeu de propositions. Le tirage reste sans remise sur les"
                            + " identifiants, qui sont derives de la position dans le YAML, et redevient"
                            + " donc avec remise sur ce que l'enfant lit : une partie peut poser deux"
                            + " fois cette manche, ce que nadia-prof a verifie a la main comme ne devant"
                            + " jamais arriver. Ces deux items comptent pourtant pour deux dans"
                            + " ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest, qui compte des"
                            + " identifiants : completer un paquet trop court par une copie le verdit"
                            + " sans livrer la variete pour laquelle il existe. Les deux sorties sont de"
                            + " faire varier l'item, ou de revoir nombreDeManches dans le catalogue -"
                            + " decision produit, ADR-0012 (ADR-0032)");
                }
            }
        }

        assertThat(paquetsFinisJuges)
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucun paquet de %s n'est une source"
                        + " finie. Un paquet combinatoire est hors perimetre par construction, et le"
                        + " jour ou le depot n'en servirait plus que de ce genre, cette clause"
                        + " n'examinerait plus rien - c'est ce message qui le dirait", racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0032 : deux items d'un meme paquet fini ne presentent jamais le meme ecran")
                .isEmpty();
    }

    @Test
    void aucunItemDeProductionNeRepeteLEcranDUnAutreItemDeSonPaquet() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet complete par une copie exacte, la forme
     * exacte que prendrait le defaut le jour ou un auteur remplirait un paquet trop court pour son
     * nombre de manches.
     */
    @Test
    void unPaquetCompleteParUneCopieFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("copie-exacte"), CONTRE_EXEMPLE_CLASSPATH + "/copie-exacte"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-complete-par-une-copie")
                .hasMessageContaining("presente le meme ecran que l'item item-1");
    }

    /**
     * La copie ne se voit pas a l'ordre des propositions : la mecanique les melange a chaque manche,
     * deux items qui ne differeraient que par cet ordre sont le meme ecran pour l'enfant. Sans ce cas,
     * une identite ecrite en liste plutot qu'en ensemble laisserait passer la copie la plus facile a
     * ecrire - permuter deux distracteurs.
     */
    @Test
    void unePermutationDesPropositionsNeSuffitPasAFaireUnItemDeplusFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("propositions-permutees"),
                CONTRE_EXEMPLE_CLASSPATH + "/propositions-permutees"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-aux-propositions-permutees");
    }

    /** Le meme paquet, sa copie remplacee par un item de plus, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetAvecUnItemDePlusAuLieuDUneCopiePasseLeGardeFou() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("copie-exacte/corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/copie-exacte/corrige");
    }

    /**
     * Le cas qui doit rester vert, et sans lequel cette clause serait une interdiction de reposer une
     * question deguisee en clause de non-repetition : le meme enonce, des propositions differentes -
     * "Quel nombre est le plus grand ?" pose sur deux couples de nombres. C'est la lecon de la seconde
     * mutation d'ADR-0025, et c'est ce cas qui justifie que l'identite retenue ne soit pas l'enonce
     * seul.
     */
    @Test
    void leMemeEnonceAvecDesPropositionsDifferentesResteVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-propositions-differentes"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-propositions-differentes");
    }

    /**
     * ADR-0003 : l'exclusion des paquets combinatoires est declaree, jamais silencieuse. Un depot qui
     * ne servirait que ce genre de paquet ferait echouer la clause de non-vacuite au lieu d'etre vert
     * sans rien examiner.
     */
    @Test
    void unPerimetreSansAucuneSourceFinieFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("seulement-combinatoire"),
                CONTRE_EXEMPLE_CLASSPATH + "/seulement-combinatoire"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun paquet")
                .hasMessageContaining("n'est une source finie");
    }
}
