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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Regle 1 du contrat de forme des paquets, decidee par {@code ADR-0063} : <b>un item ne presente
 * jamais plus de trois propositions</b>.
 *
 * <p><b>Ce que cette regle interdit reellement.</b> Elle interdit qu'un paquet neuf entre au
 * catalogue avec quatre propositions, donc qu'il tombe dans le repli a quatre colonnes qui, a lui
 * seul, a pousse le bas du cabinet a 492 px sur 551 sur {@code cm1-reperes}
 * ({@code FICHE-bandit-manchot-reperes-cm1.md}, correction du 2026-08-26). Le cas rouge est reel et
 * deja constate, ce n'est pas une precaution imaginee.
 *
 * <p><b>Ce qu'elle n'interdit pas, et il faut le dire ici plutot que de laisser croire l'inverse.</b>
 * Elle n'interdit pas qu'un item passe sous le pli pour une autre cause. Les pires items du
 * catalogue au 2026-08-27 la respectent deja : {@code cm1-grands-nombres}, le plus enfonce du
 * releve de {@code REP-8}, n'a jamais plus de trois propositions et doit ses trois rangees a la
 * longueur de ses libelles. Le pli reste declare non gardable par {@code ADR-0028}.
 *
 * <p><b>Ce que cette regle n'encode pas, et pourquoi.</b> Les regles 2 et 3 du contrat de forme
 * proposees par {@code ines-design} - un libelle de dix caracteres au plus, un enonce qui tient sur
 * une seule ligne - sont refusees comme veto par {@code nadia-prof} le 2026-08-27, chiffres a
 * l'appui sur les 428 items des 17 paquets servis : la premiere ecarterait 35 items dont
 * {@code cm1-reperes} 16 sur 16, la seconde 80 items dont {@code cp-problemes-additifs} 22 sur 22.
 * Elles ne sont pas ecrites ici, et {@code ADR-0063} porte le motif de chacune.
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais
 *       d'une liste ecrite ici - un paquet retire du catalogue mais laisse sur disque reste vu, le
 *       motif que porte {@code ChaquePaquetPorteUnIndiceTest} - et les items viennent du
 *       <b>chargeur reel</b>, pour qu'un type de contenu dont la fabrique ajouterait une
 *       proposition en route rougisse aussi ;</li>
 *   <li><b>derivation totale</b> : chaque item tire est compte, sans exception ;</li>
 *   <li><b>mise a l'epreuve</b> : un paquet fautif neuf, jamais livre, dont un item porte quatre
 *       propositions, et sa version corrigee qui doit redevenir verte.</li>
 * </ul>
 *
 * <p><b>Le cliquet, et pourquoi il est ecrit en egalite.</b> Deux paquets servis depassent la regle
 * au 2026-08-27 : {@code cm1-conjugaison-present} sur 25 items et
 * {@code cm1-accords-groupe-nominal} sur 8, soit 33 items sur 428 - mesure du 2026-08-27, lecture
 * des 18 fichiers de {@code contenu/}, confirmee item pour item par une lecture independante de
 * {@code nadia-prof} le meme jour. Le plafond vaut <b>exactement</b> la taille de la dette, jamais
 * un plancher : un cliquet dont le plafond depasse la dette n'est pas un cliquet. Quatre clauses en
 * decoulent, et chacune est mise a l'epreuve par un test de cette classe :</p>
 * <ul>
 *   <li>un item a plus de trois propositions <b>hors</b> des paquets exemptes est refuse, et
 *       nomme ;</li>
 *   <li>un paquet exempte dont le compte <b>monte</b> est refuse : la dette ne grandit pas ;</li>
 *   <li>un paquet exempte dont le compte <b>descend</b> est refuse aussi, avec la valeur a
 *       inscrire : une exemption qui vaut plus que ce qu'elle exempte laisse rentrer un item
 *       fautif sans que rien ne le dise ;</li>
 *   <li>un paquet exempte qui <b>n'existe plus</b> sous {@code contenu/} est refuse : une exemption
 *       qui ne nomme plus rien de reel autorise un nom, et ce nom peut revenir porte par un contenu
 *       neuf.</li>
 * </ul>
 *
 * <p><b>Ce que la dette n'est pas.</b> Ces 33 items ne sont pas un defaut pedagogique constate :
 * {@code nadia-prof} n'a prononce aucun refus dessus, et leur correction eventuelle est une
 * question de contenu qui ne m'appartient pas. Le cliquet dit seulement qu'ils existent, combien
 * ils sont, et qu'ils ne se multiplieront pas en silence.</p>
 */
class AuPlusTroisPropositionsParItemTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** Le jeu fautif de ce garde-fou : un paquet que personne n'a jamais livre. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AuPlusTroisPropositionsParItemTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AuPlusTroisPropositionsParItemTest";

    /** Le nombre maximal de propositions qu'un item presente a l'enfant. */
    private static final int PROPOSITIONS_MAXIMUM = 3;

    /**
     * Tirages effectues sur un paquet <b>combinatoire</b>, le seul qui ne s'epuise jamais. Sur un
     * tel paquet, cette borne rend la lecture <b>partielle par construction</b>, et le garde-fou le
     * declare plutot que de le taire (ADR-0051).
     *
     * <p><b>Correction du 2026-08-28 (s21, cloture), trouvee en verifiant la population de ce
     * cliquet a la demande du relecteur.</b> Une borne unique de 60 tirages servait a <b>tous</b>
     * les paquets, y compris enumeres. Or {@code cm1-tables-multiplication} en declare <b>99</b> :
     * ce garde-fou n'en voyait que 60, et <b>39 items n'etaient examines par personne</b>, en
     * silence. Aucun defaut ne s'y cachait - une lecture exacte des 21 fichiers de {@code contenu/}
     * le 2026-08-28 rend 33 items a plus de trois propositions, tous dans les deux paquets exemptes
     * - mais la clause 1 etait verifiee sur un echantillon pour ce paquet-la, et personne ne
     * l'aurait su. C'est le defaut meme que ce sprint a instruit six fois : une mesure qui porte sur
     * moins que ce qu'elle annonce, sans le dire.
     */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    /**
     * La dette existante, au paquet et a l'item pres, mesuree le 2026-08-27 sur les 18 fichiers de
     * {@code contenu/}. Cette table ne grandit pas : un paquet neuf n'y entre jamais.
     */
    private static final Map<String, Integer> DETTE_AU_2026_08_27 = Map.of(
            "cm1-conjugaison-present", 25,
            "cm1-accords-groupe-nominal", 8);

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    private static List<String> paquets(Path racine) {
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
     * Le nombre d'items qu'un paquet declare sur disque, 0 pour un paquet combinatoire qui n'en
     * enumere aucun. Lu dans le fichier <b>uniquement pour borner les tirages</b> : les valeurs
     * jugees, elles, viennent toujours du chargeur reel (ADR-0013, obligation 1). Sans cette
     * derivation, la borne serait un nombre ecrit a la main, et un paquet plus grand qu'elle serait
     * lu en partie sans que rien ne le signale.
     */
    private static int itemsDeclares(Path racine, String paquet) {
        Path fichier = racine.resolve("contenu").resolve(paquet + ".yaml");
        try {
            return (int) Files.readAllLines(fichier).stream()
                    .filter(ligne -> ligne.strip().startsWith("- enonce:"))
                    .count();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les items reellement tires par la chaine de contenu, pas relus dans le YAML. */
    private static List<Question> itemsTires(ContentSource contenu, int tirages) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < tirages; tirage++) {
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

    /** Le nombre de propositions presentees : la bonne reponse, plus ses distracteurs. */
    private static int nombreDePropositions(Question item) {
        return 1 + item.distracteurs().size();
    }

    /**
     * La mesure, separee du verdict : pour chaque paquet du repertoire, le nombre d'items qui
     * presentent plus de trois propositions, et un exemple nomme de chacun.
     */
    private static Map<String, List<String>> itemsTropNombreux(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        Map<String, List<String>> depassements = new LinkedHashMap<>();
        for (String paquet : paquets) {
            // La borne est derivee du paquet, jamais ecrite a la main : de quoi l'epuiser, plus un
            // tirage pour constater l'epuisement.
            int declares = itemsDeclares(racine, paquet);
            int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
            List<Question> items =
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet), tirages);
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();
            if (declares > 0) {
                // Le garde-fou dit sur quoi il a porte. Un paquet enumere est lu EN ENTIER ou le
                // test est rouge : une clause verifiee sur une partie des items, sans le dire, est
                // exactement le defaut que ce sprint a instruit six fois.
                assertThat(items)
                        .as("%s declare %d items sur disque et ce garde-fou n'en a tire que %d : la"
                                + " clause porterait sur un echantillon sans le dire", paquet, declares,
                                items.size())
                        .hasSize(declares);
            }
            for (Question item : items) {
                int propositions = nombreDePropositions(item);
                if (propositions > PROPOSITIONS_MAXIMUM) {
                    depassements.computeIfAbsent(paquet, cle -> new ArrayList<>())
                            .add("item " + item.id().value() + " (\"" + item.enonce() + "\") : "
                                    + propositions + " propositions");
                }
            }
        }
        return depassements;
    }

    /**
     * Le verdict. Les exemptions sont un parametre et non une constante lue ici : c'est ce qui rend
     * chacune des quatre clauses eprouvable separement, sur du contenu reel, sans avoir a fabriquer
     * un faux catalogue pour chacune.
     */
    static void verifier(Path racine, String racineClasspath, Map<String, Integer> exemptions) {
        Map<String, List<String>> depassements = itemsTropNombreux(racine, racineClasspath);
        List<String> paquets = paquets(racine);
        List<String> defauts = new ArrayList<>();

        // Clause 1 : hors des paquets exemptes, aucun item ne depasse. Un paquet neuf tombe ici.
        for (Map.Entry<String, List<String>> depassement : new TreeMap<>(depassements).entrySet()) {
            String paquet = depassement.getKey();
            if (!exemptions.containsKey(paquet)) {
                defauts.add("paquet " + paquet + " : " + depassement.getValue().size()
                        + " item(s) presentent plus de " + PROPOSITIONS_MAXIMUM + " propositions, alors"
                        + " que ce paquet n'est pas dans la dette du 2026-08-27. Un item a quatre"
                        + " propositions tombe dans le repli a quatre colonnes (ADR-0063). Detail : "
                        + String.join(" | ", depassement.getValue()));
            }
        }

        // Clauses 2, 3 et 4 : le cliquet vaut exactement la dette, et chaque exemption nomme
        // encore un paquet reel.
        for (Map.Entry<String, Integer> exemption : new TreeMap<>(exemptions).entrySet()) {
            String paquet = exemption.getKey();
            int attendu = exemption.getValue();
            if (!paquets.contains(paquet)) {
                defauts.add("exemption morte : le paquet " + paquet + " est inscrit au cliquet du"
                        + " 2026-08-27 mais n'existe pas sous contenu/. Une exemption qui ne nomme plus"
                        + " rien de reel autorise un nom, et ce nom peut revenir porte par un contenu"
                        + " neuf : retirer cette ligne de DETTE_AU_2026_08_27");
                continue;
            }
            int reel = depassements.getOrDefault(paquet, List.of()).size();
            if (reel > attendu) {
                defauts.add("paquet exempte " + paquet + " : " + reel + " item(s) a plus de "
                        + PROPOSITIONS_MAXIMUM + " propositions, alors que le cliquet du 2026-08-27 en"
                        + " inscrit " + attendu + ". La dette ne grandit pas. Detail : "
                        + String.join(" | ", depassements.getOrDefault(paquet, List.of())));
            } else if (reel < attendu) {
                defauts.add("paquet exempte " + paquet + " : " + reel + " item(s) a plus de "
                        + PROPOSITIONS_MAXIMUM + " propositions, alors que le cliquet du 2026-08-27 en"
                        + " inscrit " + attendu + ". La dette a diminue, ce qui est une bonne nouvelle :"
                        + " porter DETTE_AU_2026_08_27 a " + reel + " pour ce paquet, sans quoi"
                        + " l'exemption vaut plus que ce qu'elle exempte et laisse rentrer un item"
                        + " fautif sans que rien ne le dise");
            }
        }

        assertThat(defauts)
                .as("aucun item ne presente plus de %d propositions, hors la dette exacte du 2026-08-27",
                        PROPOSITIONS_MAXIMUM)
                .isEmpty();
    }

    private static Map<String, Integer> exemptionsAvec(String paquet, int valeur) {
        Map<String, Integer> modifiees = new HashMap<>(DETTE_AU_2026_08_27);
        modifiees.put(paquet, valeur);
        return modifiees;
    }

    @Test
    void aucunItemDeProductionNePresentePlusDeTroisPropositionsHorsLaDetteExacte() {
        verifier(CONTENU_DE_PRODUCTION, "", DETTE_AU_2026_08_27);
    }

    /**
     * La dette est un chiffre lu sur le contenu servi, pas une valeur recopiee dans une table. Ce
     * test retire toute exemption et exige que le garde-fou nomme les deux paquets et leurs comptes
     * exacts : c'est la preuve que les 25 et les 8 existent, et que la clause 1 les verrait si elles
     * n'etaient pas inscrites.
     */
    @Test
    void sansAucuneExemptionLeGardeFouNommeLesDeuxPaquetsDeLaDetteEtLeursComptes() {
        assertThatThrownBy(() -> verifier(CONTENU_DE_PRODUCTION, "", Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet cm1-accords-groupe-nominal : 8 item(s)")
                .hasMessageContaining("paquet cm1-conjugaison-present : 25 item(s)");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part, dont
     * un item porte quatre propositions.
     */
    @Test
    void unPaquetNeufAQuatrePropositionsFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-a-quatre-propositions")
                .hasMessageContaining("4 propositions")
                .hasMessageContaining("n'est pas dans la dette du 2026-08-27");
    }

    /** Le meme paquet neuf, ramene a trois propositions, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetRameneATroisPropositionsPasseLeGardeFou() {
        verifier(CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige", Map.of());
    }

    /** Clause 2 : un paquet exempte dont le compte monte est refuse, la dette ne grandit pas. */
    @Test
    void unPaquetExempteDontLeCompteMonteFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTENU_DE_PRODUCTION, "",
                exemptionsAvec("cm1-conjugaison-present", 24)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet exempte cm1-conjugaison-present : 25 item(s)")
                .hasMessageContaining("La dette ne grandit pas");
    }

    /**
     * Clause 3 : un paquet exempte dont le compte descend est refuse aussi, et le message donne la
     * valeur a inscrire. C'est la clause qui empeche un plafond de depasser sa dette.
     */
    @Test
    void unPaquetExempteDontLeCompteDescendFaitEchouerLeGardeFouEtDonneLaValeurAInscrire() {
        assertThatThrownBy(() -> verifier(CONTENU_DE_PRODUCTION, "",
                exemptionsAvec("cm1-conjugaison-present", 26)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("La dette a diminue")
                .hasMessageContaining("porter DETTE_AU_2026_08_27 a 25 pour ce paquet");
    }

    /** Clause 4 : une exemption qui ne nomme plus un paquet reel est refusee. */
    @Test
    void uneExemptionQuiNeNommePlusUnPaquetReelFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTENU_DE_PRODUCTION, "",
                exemptionsAvec("cm1-paquet-retire-du-disque", 3)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("exemption morte")
                .hasMessageContaining("cm1-paquet-retire-du-disque");
    }
}
