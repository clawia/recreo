package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>ADR-0075, clause 2 - VOI-10 (nadia-prof, 2026-09-01, section 5.1 de son arbitrage) : le texte
 * que l'adulte prononce ne contient jamais une proposition de son item, ni la bonne reponse.</b>
 *
 * <p>C'est la condition que nadia-prof demande d'ecrire en test <b>avant toutes les autres</b>,
 * parce qu'elle referme le canal de fuite par construction, la ou une liste d'interdits serait une
 * liste qu'on oublie de tenir a jour (`VOI-1`) : le danger n'est pas que l'adulte parle, c'est ce
 * qu'on lui donne a dire.</p>
 *
 * <p><b>Ce qu'une proposition veut dire ici, selon la forme de l'item</b> : sur un item de choix
 * ({@link Question}), la bonne reponse et chaque distracteur ; sur un item de saisie
 * ({@link ReponseAEcrire}), la forme de reference et chaque forme acceptee - c'est le meme
 * inventaire que celui que l'enfant pourrait produire ou choisir, jamais celui qu'il lit deja par
 * ailleurs (l'enonce, la consigne).</p>
 *
 * <p><b>La comparaison se fait sur une forme repliee</b> - casse et signes diacritiques, sur le
 * patron de {@code NormalisationDeSaisie} qui existe deja dans le domaine - pour qu'une simple
 * variation de casse ou d'accent ne masque pas la fuite. Elle reste une comparaison de
 * sous-chaines, et ce qu'elle ne voit pas est nomme : un mot recompose autrement (syllabes
 * separees, periphrase) lui echappe. Elle attrape la faute reelle - la proposition recopiee dans le
 * texte de l'adulte -, pas la faute imaginative.</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - les paquets viennent du balayage de
 * {@code contenu/}, les items du chargeur reel, exactement comme
 * {@link AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest} ; <b>derivation totale</b> - chaque
 * item de chaque paquet est examine, qu'il declare {@code texteAVoixHaute} ou non (l'absence est
 * ignoree, elle n'a rien a fuir) ; <b>mise a l'epreuve</b> - deux paquets fautifs neufs, un par forme
 * d'item, jamais servis a un enfant.
 */
class AucunTexteAVoixHauteNePorteUnePropositionDeSonItemTest {

    /**
     * <b>Pourquoi ce garde-fou n'est pas encore branche sur le repertoire de contenu reel, ecrit le
     * 2026-09-01 et non tu.</b> Aucun paquet servi ne declare encore {@code texteAVoixHaute} - ce lot
     * pose le champ et la regle, il n'ecrit aucun paquet de la famille a voix
     * (`squad/sprints/s26-la-voix-et-la-dette/planning.md`, addendum du 2026-09-01 : la redaction des
     * cinq paquets est le lot de {@code camille-po}). Brancher ici un {@code @Test} qui balaierait
     * {@code src/main/resources} ferait donc echouer sa propre clause de non-vacuite (ADR-0051) -
     * exactement le mode de defaillance que cette classe existe pour eviter d'ecrire en silence.
     * {@link #ecartsDeFuite(Path, String)} est ecrite, testee et prete : le premier paquet a voix qui
     * atterrit sur {@code contenu/} n'a besoin que d'un {@code @Test} de plus, appelant
     * {@code ecartsDeFuite(Path.of("src/main/resources"), "")}, dans le meme commit que ce paquet.
     */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunTexteAVoixHauteNePorteUnePropositionDeSonItemTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunTexteAVoixHauteNePorteUnePropositionDeSonItemTest";

    /** Meme budget que {@link AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest}, meme motif. */
    private static final int BUDGET_DE_TIRAGES = 500;

    /** ADR-0075 : repliage casse + signes diacritiques, sur le patron de NormalisationDeSaisie. */
    private static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            replie.append(signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }

    private static Optional<String> texteAVoixHauteDe(Item item) {
        return switch (item) {
            case Question question -> question.texteAVoixHaute();
            case ReponseAEcrire reponse -> reponse.texteAVoixHaute();
        };
    }

    /**
     * L'inventaire des propositions d'un item, selon sa forme (VOI-10) : ce que l'enfant choisit ou
     * produit, jamais ce qu'il lit deja par ailleurs.
     */
    private static Set<String> propositionsDe(Item item) {
        return switch (item) {
            case Question question -> {
                Set<String> propositions = new HashSet<>();
                propositions.add(question.bonneReponse());
                propositions.addAll(question.distracteurs());
                yield propositions;
            }
            case ReponseAEcrire reponse -> {
                Set<String> propositions = new HashSet<>();
                propositions.add(reponse.formeDeReference());
                propositions.addAll(reponse.formesAcceptees());
                yield propositions;
            }
        };
    }

    /** Tous les items que ce paquet sait rendre, tires par la chaine reelle - meme methode que ADR-0032. */
    private static List<Item> itemsDuPaquet(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Item item = tire.orElseThrow();
            items.add(item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static List<Item> itemsDesPaquetsServis(Path racine, String racineClasspath) {
        List<Item> items = new ArrayList<>();
        for (String paquet : AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.paquets(racine)) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            items.addAll(itemsDuPaquet(contenu));
        }
        return items;
    }

    static List<String> ecartsDeFuite(Path racine, String racineClasspath) {
        List<Item> items = itemsDesPaquetsServis(racine, racineClasspath);
        assertThat(items)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun item sous %s", racine)
                .isNotEmpty();

        List<String> avecVoix = new ArrayList<>();
        List<String> defauts = new ArrayList<>();
        for (Item item : items) {
            Optional<String> voix = texteAVoixHauteDe(item);
            if (voix.isEmpty()) {
                continue;
            }
            avecVoix.add(item.id().value());
            String replie = replier(voix.orElseThrow());
            for (String proposition : propositionsDe(item)) {
                if (replie.contains(replier(proposition))) {
                    defauts.add("item " + item.id().value() + " : le texte a voix haute \""
                            + voix.orElseThrow() + "\" contient la proposition \"" + proposition
                            + "\" - VOI-10 (nadia-prof, 2026-09-01) : l'adulte prononce le stimulus,"
                            + " il ne prononce jamais une proposition");
                }
            }
        }
        assertThat(avecVoix)
                .as("ADR-0051 : ce garde-fou ne prouve rien si aucun item ne declare texteAVoixHaute"
                        + " sous %s - la population qu'il juge serait vide", racine)
                .isNotEmpty();
        return defauts;
    }

    @Test
    void unTexteAVoixHauteQuiRecopieLaBonneReponseDUneQuestionFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("question-fuite"), CONTRE_EXEMPLE_CLASSPATH + "/question-fuite"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("au debut"));
    }

    @Test
    void unTexteAVoixHauteQuiRecopieLaFormeDeReferenceDUneSaisieFaitEchouerLeGardeFou() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("saisie-fuite"), CONTRE_EXEMPLE_CLASSPATH + "/saisie-fuite"))
                .isNotEmpty()
                .anyMatch(defaut -> defaut.contains("item-0") && defaut.contains("velo"));
    }

    @Test
    void unPaquetSainNeRemonteAucunEcartMemeAvecUneVoix() {
        assertThat(ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("sain"), CONTRE_EXEMPLE_CLASSPATH + "/sain"))
                .isEmpty();
    }

    @Test
    void unPerimetreSansAucunTexteAVoixHauteFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> ecartsDeFuite(
                CONTRE_EXEMPLE.resolve("aucune-voix"), CONTRE_EXEMPLE_CLASSPATH + "/aucune-voix"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }
}
