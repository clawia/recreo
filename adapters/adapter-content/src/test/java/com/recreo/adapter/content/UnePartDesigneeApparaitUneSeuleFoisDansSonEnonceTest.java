package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>ADR-0062, clause 2 : pour chaque item servi qui declare une part designee, cette valeur
 * apparait exactement une fois dans son enonce.</b>
 *
 * <p>C'est l'unique mode de defaillance silencieuse que la derivation du controleur (decision 3 de
 * l'ADR) peut produire : zero occurrence marque le mauvais endroit ou rien du tout, deux occurrences
 * marquent la premiere, qui n'est pas forcement celle que le contenu visait. Dans les deux cas la CI
 * serait verte sans ce garde-fou, et seul un humain qui joue chaque item le verrait.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici (meme balayage qu'{@code AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest},
 *       reutilise plutot que recopie) ;</li>
 *   <li><b>derivation totale</b> : chaque item tire par la chaine reelle qui declare une part
 *       designee est examine ; aucun item declarant nulle part ne rend cette clause vraie a vide
 *       (non-vacuite, ADR-0051) ;</li>
 *   <li><b>mise a l'epreuve</b> : sur un item reel mute pour declarer une part absente de sa phrase,
 *       puis une part qui y figure deux fois, et sur un paquet qui ne declare jamais rien.</li>
 * </ul>
 */
class UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest";

    /** Meme borne que les autres garde-fous de paquet : un paquet combinatoire ne s'epuise jamais. */
    private static final int BUDGET_DE_TIRAGES = 500;

    /** Les items qu'un paquet sait rendre, tires par la chaine reelle. */
    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(23)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Question question = (Question) tire.orElseThrow();
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static int occurrences(String enonce, String valeur) {
        int compte = 0;
        int index = enonce.indexOf(valeur);
        while (index >= 0) {
            compte++;
            index = enonce.indexOf(valeur, index + 1);
        }
        return compte;
    }

    static void verifierQuUnePartDesigneeApparaitUneSeuleFois(Path racine, String racineClasspath) {
        List<String> paquets = AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        int itemsDeclarants = 0;

        for (String paquet : paquets) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            for (Question item : itemsTires(contenu)) {
                Optional<String> partDesignee = item.partDesignee();
                if (partDesignee.isEmpty()) {
                    continue;
                }
                itemsDeclarants++;
                String valeur = partDesignee.orElseThrow();
                int trouvees = occurrences(item.enonce(), valeur);
                if (trouvees != 1) {
                    defauts.add("paquet " + paquet + ", item \"" + item.enonce() + "\" : sa part"
                            + " designee \"" + valeur + "\" apparait " + trouvees + " fois dans son"
                            + " enonce, pas une seule. Le mauvais mot serait marque, ou aucun (ADR-0062,"
                            + " clause 2)");
                }
            }
        }

        assertThat(itemsDeclarants)
                .as("ADR-0051 : cette clause n'examine aucun objet si aucun item servi de %s ne"
                        + " declare de part designee, et elle doit alors echouer plutot que passer"
                        + " verte a vide", racine)
                .isGreaterThan(0);
        assertThat(defauts)
                .as("ADR-0062, clause 2 : une part designee apparait exactement une fois dans son enonce")
                .isEmpty();
    }

    @Test
    void chaquePartDesigneeDeProductionApparaitUneSeuleFoisDansSonEnonce() {
        verifierQuUnePartDesigneeApparaitUneSeuleFois(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : un item reel mute pour declarer une part absente de sa phrase - zero
     * occurrence.
     */
    @Test
    void unePartDesigneeAbsenteDeLEnonceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuUnePartDesigneeApparaitUneSeuleFois(
                CONTRE_EXEMPLE.resolve("part-absente"), CONTRE_EXEMPLE_CLASSPATH + "/part-absente"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("0 fois");
    }

    /**
     * Second bord, celui qu'un garde-fou ecrit a la hate oublierait : une part qui figure deux fois -
     * le mauvais mot serait marque.
     */
    @Test
    void unePartDesigneeRepeteeDansLEnonceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuUnePartDesigneeApparaitUneSeuleFois(
                CONTRE_EXEMPLE.resolve("part-repetee"), CONTRE_EXEMPLE_CLASSPATH + "/part-repetee"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("2 fois");
    }

    /** Le meme paquet, sa part corrigee pour n'apparaitre qu'une fois, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetCorrigePasseLeGardeFou() {
        verifierQuUnePartDesigneeApparaitUneSeuleFois(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }

    /**
     * ADR-0051 : un paquet qui ne declare jamais de part designee - la forme qu'aurait le depot si
     * le seul paquet marque disparaissait du repertoire de contenu. La clause ne doit pas passer
     * verte a vide.
     */
    @Test
    void aucunItemDeclarantFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierQuUnePartDesigneeApparaitUneSeuleFois(
                CONTRE_EXEMPLE.resolve("aucune-declaration"),
                CONTRE_EXEMPLE_CLASSPATH + "/aucune-declaration"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun item servi");
    }
}
