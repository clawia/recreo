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
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Arbitrage prealable de nadia-prof sur les mathematiques CM1 (s12,
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md}, section 6 point 1) : elle
 * a verifie que <b>ni la fabrique, ni le record {@link Question}, ni aucun garde-fou du depot ne
 * compare un distracteur a la bonne reponse</b>. Trois figures que le contenu de perimetre et de
 * tables de ce sprint produisent naturellement rendent le trou concret : un carre de cote 4 (perimetre
 * 16, aire 16), un rectangle 3 x 6 (perimetre 18, aire 18), 2 x 2 avec la somme des facteurs (4, le
 * produit lui-meme). Un item ou deux propositions sont identiques n'a pas de reponse unique : un
 * enfant qui choisit le doublon n'a rien fait de mal en le choisissant.
 *
 * <p><b>La propriete est generique a toute matiere, pas seulement a celle qui l'a revelee.</b>
 * {@link Item} est scelle a {@link Question} (ADR-0004) : toute mecanique qui presente un choix
 * parmi des propositions traverse ce garde-fou des qu'un paquet neuf existe, quelle que soit la
 * matiere qu'il porte. Rien ici ne nomme le perimetre, une conversion ou une table de
 * multiplication.
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici, et les items viennent du <b>chargeur reel</b> - la chaine que l'enfant
 *       traverse ;</li>
 *   <li><b>derivation totale</b> : chaque item tire produit une comparaison de ses propositions, sans
 *       exception ;</li>
 *   <li><b>mise a l'epreuve</b> : un paquet fautif neuf, jamais livre, dont un item porte deux fois la
 *       meme proposition, et sa version corrigee qui doit redevenir verte.</li>
 * </ul>
 *
 * <p>Le critere lui-meme n'est pas une liste enumeree (ADR-0039) : c'est une propriete calculee sur
 * les propositions de chaque item - la bonne reponse et ses distracteurs ne doivent jamais coincider,
 * quel que soit leur texte.</p>
 */
class AucunePropositionNEstEgaleAUneAutreDansUnItemTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** Le jeu fautif de ce garde-fou, un paquet que personne n'a jamais livre. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunePropositionNEstEgaleAUneAutreDansUnItemTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunePropositionNEstEgaleAUneAutreDansUnItemTest";

    /**
     * Tirages effectues sur chaque paquet. Un paquet enumere s'epuise bien avant ; un paquet
     * combinatoire ne s'epuise jamais, d'ou la borne.
     */
    private static final int TIRAGES = 60;

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
    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < TIRAGES; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    /** Les propositions d'un item, dans l'ordre : la bonne reponse, puis chaque distracteur. */
    private static List<String> propositionsDe(Question item) {
        List<String> propositions = new ArrayList<>();
        propositions.add(item.bonneReponse());
        propositions.addAll(item.distracteurs());
        return propositions;
    }

    private static List<String> doublonsDe(String paquet, Question item) {
        List<String> propositions = propositionsDe(item);
        List<String> defauts = new ArrayList<>();
        Set<String> vues = new HashSet<>();
        for (String proposition : propositions) {
            if (!vues.add(proposition)) {
                defauts.add("paquet " + paquet + ", item " + item.id().value() + " (\"" + item.enonce()
                        + "\") : la proposition \"" + proposition + "\" apparait plus d'une fois parmi"
                        + " [" + String.join(", ", propositions) + "]. Un item ou deux propositions"
                        + " sont identiques n'a pas de reponse unique, et un enfant qui choisit le"
                        + " doublon n'a rien fait de mal en le choisissant");
            }
        }
        return defauts;
    }

    static void verifierQuAucunePropositionNEstEgaleAUneAutre(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets) {
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();
            for (Question item : items) {
                defauts.addAll(doublonsDe(paquet, item));
            }
        }

        assertThat(defauts)
                .as("aucune proposition d'un item ne doit egaler une autre proposition du meme item")
                .isEmpty();
    }

    @Test
    void aucunePropositionDeProductionNEstEgaleAUneAutreDansUnItem() {
        verifierQuAucunePropositionNEstEgaleAUneAutre(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part, dont un
     * item porte deux fois la meme proposition - la forme exacte du carre de cote 4 ou du rectangle
     * 3 x 6 que l'arbitrage de nadia-prof nomme.
     */
    @Test
    void unItemNeufAvecDeuxPropositionsIdentiquesFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunePropositionNEstEgaleAUneAutre(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-avec-doublon")
                .hasMessageContaining("apparait plus d'une fois");
    }

    /** Le meme paquet neuf, une fois ses propositions rendues distinctes, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSesPropositionsDistingueesPasseLeGardeFou() {
        verifierQuAucunePropositionNEstEgaleAUneAutre(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }
}
