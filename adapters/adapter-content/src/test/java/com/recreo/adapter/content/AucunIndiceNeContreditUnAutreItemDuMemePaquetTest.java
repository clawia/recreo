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
 * <p>Dette ouverte par theo-archi a la porte technique de s07
 * ({@code squad/sprints/s07-machine-a-sous/confirmation-archunit.md}, section 4) : la correction
 * du meme commit a fait disparaitre la forme fautive des cinq indices qu'elle visait, si bien que
 * la clause de production ci-dessous balaie desormais une population vide sur les quatre paquets
 * du depot, sans qu'aucun signal ne distingue "aucune contradiction trouvee" de "aucun indice
 * examine" (ADR-0003). {@link #aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui()}
 * ferme ce trou par un epinglage de l'etat reel, sur le modele que theo-archi avait ecrit pour les
 * clauses 1 et 2 d'ADR-0026 ({@code aucuneAnimationNEstEncoreDeclareeEnProduction}) : le plafond
 * vaut exactement la taille de la dette, zero, et le message dit de le supprimer le jour ou ce
 * n'est plus vrai.</p>
 */
class AucunIndiceNeContreditUnAutreItemDuMemePaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, reproduisant la contradiction manche 7 / manche 10. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunIndiceNeContreditUnAutreItemDuMemePaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunIndiceNeContreditUnAutreItemDuMemePaquetTest";

    /**
     * Tirages effectues sur chaque paquet. Un paquet enumere s'epuise bien avant ; un paquet
     * combinatoire ne s'epuise jamais, d'ou la borne.
     */
    private static final int TIRAGES = 60;

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

    static void verifierQuAucunIndiceNeContreditUnAutreItem(Path racine, String racineClasspath, Set<String> dette) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        for (String paquet : paquets) {
            if (dette.contains(paquet)) {
                continue;
            }
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));

            for (Question itemPortantLIndice : items) {
                Matcher regle = REGLE_GENERALE_NON_TEMPEREE.matcher(itemPortantLIndice.indice().orElse(""));
                if (!regle.find()) {
                    continue;
                }
                Set<String> personnesVisees = new HashSet<>();
                personnesVisees.add(canonicaliserPersonne(regle.group(1)));
                if (regle.group(2) != null) {
                    personnesVisees.add(canonicaliserPersonne(regle.group(2)));
                }
                String terminaison = regle.group(3);

                for (Question autreItem : items) {
                    String sujet = sujetGrammatical(autreItem.enonce());
                    if (sujet == null || !personnesVisees.contains(sujet)) {
                        continue;
                    }
                    assertThat(autreItem.bonneReponse())
                            .as("paquet %s : l'indice de \"%s\" enonce une regle generale (terminaison"
                                            + " '-%s') que la bonne reponse \"%s\" de l'item \"%s\" dement",
                                    paquet, itemPortantLIndice.enonce(), terminaison,
                                    autreItem.bonneReponse(), autreItem.enonce())
                            .endsWith(terminaison);
                }
            }
        }
    }

    @Test
    void aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet() {
        verifierQuAucunIndiceNeContreditUnAutreItem(CONTENU_DE_PRODUCTION, "", Set.of());
    }

    /** ADR-0013 obligation 3 : l'objet neuf est un paquet reproduisant la contradiction manche 7 / manche 10. */
    @Test
    void unPaquetPortantLaContradictionDuRefusFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunIndiceNeContreditUnAutreItem(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dement");
    }

    /** Le meme paquet, une fois son indice general corrige, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSonIndiceCorrigePasseLeGardeFou() {
        verifierQuAucunIndiceNeContreditUnAutreItem(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige", Set.of());
    }

    /** Le nombre d'indices de production dont le texte matche la forme generale non temperee. */
    private static int nombreDIndicesReconnusParLaFormeGenerale(Path racine, String racineClasspath) {
        int compte = 0;
        for (String paquet : paquets(racine)) {
            for (Question item : itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet))) {
                if (REGLE_GENERALE_NON_TEMPEREE.matcher(item.indice().orElse("")).find()) {
                    compte++;
                }
            }
        }
        return compte;
    }

    /**
     * Epinglage de l'etat reel (ADR-0026, ADR-0003) : {@code aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet}
     * balaie une population qui vaut aujourd'hui zero sur les quatre paquets du depot, puisque la
     * correction du meme commit a remplace les cinq indices qui matchaient {@code
     * REGLE_GENERALE_NON_TEMPEREE} par la forme qui nomme le verbe. Sans cet epinglage, cette clause
     * serait verte a vide et rien ne distinguerait "aucune contradiction trouvee" de "aucun indice
     * examine".
     *
     * <p>Le plafond vaut exactement la taille de la dette : zero, pas "quelques-uns". Le jour ou un
     * indice de production correspond de nouveau a cette forme - une regression, ou un paquet neuf
     * qui y retombe puis passe la clause principale - cette assertion rougit la premiere : supprime
     * ce test, la clause de production porte alors sur un objet reel.</p>
     */
    @Test
    void aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui() {
        assertThat(nombreDIndicesReconnusParLaFormeGenerale(CONTENU_DE_PRODUCTION, ""))
                .as("epinglage de l'etat reel (ADR-0026, ADR-0003) : la forme generale non temperee"
                        + " que reconnait REGLE_GENERALE_NON_TEMPEREE ne doit correspondre a aucun"
                        + " indice de production aujourd'hui, la correction du meme commit l'ayant"
                        + " fait disparaitre des cinq indices fautifs. Si cette assertion rougit,"
                        + " supprime ce test : aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet"
                        + " porte desormais sur un objet reel")
                .isEqualTo(0);
    }
}
