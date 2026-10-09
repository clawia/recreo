package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0024 : un paquet qui declare la graphie de ses items porte une explication sur chacun de ceux
 * dont la graphie differe de la reponse attendue, et sur aucun autre.
 *
 * <p>Les deux cles se gardent l'une l'autre, et c'est la seule raison pour laquelle ce garde-fou
 * n'est pas contournable par une faute de frappe. {@code graphie} n'est lue par aucune fabrique
 * (ADR-0024 : c'est une declaration d'auteur, pas une donnee de jeu, et la faire entrer dans une
 * fabrique partagee par toutes les matieres obligerait chaque paquet de mathematiques a connaitre un
 * mot de francais) ; le chargeur ignore donc silencieusement une cle mal orthographiee. Mais un
 * paquet entre dans ce controle des que <b>l'une des deux</b> cles apparait quelque part, et le
 * controle exige alors les deux : {@code grafie} partout rend tous les items reguliers, donc leurs
 * explications interdites, donc rouge ; {@code explicaton} partout laisse les items a variation sans
 * explication, donc rouge. Aucune des deux fautes ne produit un vert.</p>
 *
 * <p><b>Amendement du 2026-10-09 (s56).</b> Un paquet qui ne declare aucune graphie et porte une
 * explication n'entre plus dans ce controle : il la porte sur tous ses items ou sur aucun
 * ({@link #verifierLesExplicationsDUnPaquetSansGraphie}). {@code grafie} partout sur un paquet de
 * francais dont les items reguliers n'ont pas d'explication reste rouge par cette regle-la ; {@code explicaton} partout sur un paquet sans graphie
 * passe, et c'est le prix nomme dans l'ADR.</p>
 *
 * <p>ADR-0013 obligation 1 : le perimetre vient du repertoire de contenu, comme celui d'ADR-0016 -
 * un paquet neuf entre dans le controle du jour ou il est deposé, sans etre inscrit ici. Obligation
 * 2 : chaque paquet trouve produit une assertion, y compris ceux qui ne declarent aucune graphie
 * (une declaration posee sur un paquet qui n'enumere pas ses items est un echec, pas un membre
 * ignore). Les items, eux, sont tires par le chargeur reel : c'est en chemin que l'explication
 * pourrait se perdre, exactement comme l'indice se perdait avant ADR-0016.</p>
 *
 * <p><b>Ce que ce garde-fou ne verifie deliberement pas, et pourquoi.</b> Il ne verifie pas que le
 * texte contient la graphie ni le son : ce sont des suites de deux lettres, et un tel controle
 * passerait pour de mauvaises raisons - "an" est dans "Dans", "en" est dans "DENT". C'est la faute
 * mesuree en s05 sur la detection d'un {@code !important}, et une regle qui passe pour une raison
 * etrangere a son objet est pire qu'une regle absente. Ce qu'il verifie a la place se tient sans
 * arbitraire : le texte nomme le mot de son propre item, il est distinct de celui des autres items,
 * et il est present exactement sur les items a variation. Le texte lui-meme reste juge par
 * nadia-prof, comme S6-7 le dit.</p>
 */
class ChaquePaquetExpliqueSesItemsAVariationTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : les jeux fautifs de ce garde-fou, un par bord du critere conditionnel. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ChaquePaquetExpliqueSesItemsAVariationTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/ChaquePaquetExpliqueSesItemsAVariationTest";

    private static final String GRAPHIE = "graphie";

    private static final String EXPLICATION = "explication";

    /** Marge de tirage au-dela du nombre d'items ecrits, pour epuiser un vivier fini sans le supposer. */
    private static final int MARGE_DE_TIRAGE = 10;

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = ChaquePaquetExpliqueSesItemsAVariationTest.class
                .getResourceAsStream(cheminClasspath)) {
            assertThat(flux)
                    .as("%s : introuvable sur le classpath alors que le repertoire le porte",
                            cheminClasspath)
                    .isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return corps;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Map<?, ?>> itemsEcrits(Map<String, Object> corps) {
        if (!(corps.get("items") instanceof List<?> items)) {
            return List.of();
        }
        List<Map<?, ?>> ecrits = new ArrayList<>();
        for (Object item : items) {
            if (item instanceof Map<?, ?> map) {
                ecrits.add(map);
            }
        }
        return ecrits;
    }

    /**
     * Toutes les cles du fichier, a n'importe quelle profondeur. Ce sont bien les <b>cles</b> et non
     * le texte du fichier : chercher la sous-chaine "graphie" dans le corps entier rougirait sur le
     * mot francais "orthographie", qu'une competence de programme peut parfaitement porter. C'est la
     * faute mesuree en s05 sur la detection d'un {@code !important}, et elle est facile a refaire.
     */
    private static Set<String> clesDe(Object noeud) {
        Set<String> cles = new HashSet<>();
        if (noeud instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entree : map.entrySet()) {
                cles.add(String.valueOf(entree.getKey()));
                cles.addAll(clesDe(entree.getValue()));
            }
        } else if (noeud instanceof List<?> liste) {
            for (Object element : liste) {
                cles.addAll(clesDe(element));
            }
        }
        return cles;
    }

    private static Optional<String> texte(Map<?, ?> item, String cle) {
        Object valeur = item.get(cle);
        return valeur == null ? Optional.empty() : Optional.of(String.valueOf(valeur));
    }

    /** Les items que la mecanique recevra reellement, tires par la chaine complete. */
    private static List<Question> itemsTires(ContentSource contenu, int bornes) {
        RandomSource hasard = new Random(5)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < bornes; tirage++) {
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

    private static Question itemTirePourLEnonce(List<Question> tires, String enonce, String paquet) {
        List<Question> candidats = tires.stream()
                .filter(question -> question.enonce().equals(enonce))
                .toList();
        assertThat(candidats)
                .as("paquet %s, item \"%s\" : ecrit dans le fichier mais jamais rendu par le chargeur",
                        paquet, enonce)
                .hasSize(1);
        return candidats.get(0);
    }

    static void verifierQueChaquePaquetExpliqueSesItemsAVariation(Path racine, String racineClasspath) {
        List<String> paquets = ChaquePaquetPorteUnIndiceTest.paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        for (String paquet : paquets) {
            Map<String, Object> corps = lireYaml(racineClasspath + "/contenu/" + paquet + ".yaml");
            List<Map<?, ?>> ecrits = itemsEcrits(corps);
            boolean declare = ecrits.stream()
                    .anyMatch(item -> item.containsKey(GRAPHIE) || item.containsKey(EXPLICATION));

            if (!declare) {
                /*
                 * Obligation 2 : un paquet hors du controle produit tout de meme une assertion. Une
                 * declaration posee ailleurs que sur un item (sur l'enveloppe, sur les parametres d'un
                 * paquet combinatoire) n'atteindrait aucune fabrique et aucun garde-fou : elle doit
                 * rougir ici plutot que de dormir.
                 */
                assertThat(clesDe(corps))
                        .as("paquet %s : il n'enumere aucun item qui declare une graphie ou une"
                                + " explication, mais l'une de ces cles apparait ailleurs dans le"
                                + " fichier - la, personne ne la lit", paquet)
                        .doesNotContain(GRAPHIE, EXPLICATION);
                continue;
            }

            List<Question> tires = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet),
                    ecrits.size() + MARGE_DE_TIRAGE);
            if (ecrits.stream().noneMatch(item -> item.containsKey(GRAPHIE))) {
                verifierLesExplicationsDUnPaquetSansGraphie(paquet, ecrits, tires);
                continue;
            }
            List<String> explicationsVues = new ArrayList<>();

            for (Map<?, ?> ecrit : ecrits) {
                String enonce = String.valueOf(ecrit.get("enonce"));
                String graphie = texte(ecrit, GRAPHIE).orElse("");
                assertThat(graphie)
                        .as("paquet %s, item \"%s\" : ce paquet declare la graphie de ses items, donc"
                                + " tous. Sans elle, rien ne dit si cet item demande une explication,"
                                + " et une cle mal orthographiee passerait inapercue", paquet, enonce)
                        .isNotBlank();

                Question tire = itemTirePourLEnonce(tires, enonce, paquet);
                boolean aVariation = !graphie.strip().equalsIgnoreCase(tire.bonneReponse().strip());

                if (aVariation) {
                    assertThat(tire.explication())
                            .as("paquet %s, item \"%s\" : sa graphie \"%s\" differe de la reponse"
                                    + " attendue \"%s\", c'est donc un item a variation et l'enfant"
                                    + " doit voir les lettres qui font ce son sur l'ecran de retour",
                                    paquet, enonce, graphie, tire.bonneReponse())
                            .isPresent();
                    String explication = tire.explication().orElseThrow();
                    assertThat(explication)
                            .as("paquet %s, item \"%s\" : une explication vide est une explication"
                                    + " absente", paquet, enonce)
                            .isNotBlank();
                    assertThat(explication.toLowerCase(Locale.ROOT))
                            .as("paquet %s, item \"%s\" : l'explication nomme le mot que l'enfant vient"
                                    + " de lire. Un texte qui ne le nomme pas est le meme texte pour"
                                    + " tous les items, et n'explique donc celui-ci pour personne",
                                    paquet, enonce)
                            .contains(enonce.toLowerCase(Locale.ROOT));
                    explicationsVues.add(explication);
                } else {
                    assertThat(tire.explication())
                            .as("paquet %s, item \"%s\" : sa graphie \"%s\" est la reponse attendue"
                                    + " elle-meme, il n'y a donc rien a expliquer. Un texte ici fait"
                                    + " passer un item regulier pour une exception",
                                    paquet, enonce, graphie)
                            .isEmpty();
                }
            }

            assertThat(explicationsVues)
                    .as("paquet %s : chaque item a variation porte son propre texte, distinct des"
                            + " autres", paquet)
                    .doesNotHaveDuplicates();
        }
    }

    /**
     * ADR-0024, amendement du 2026-10-09 (s56) : un paquet qui ne declare aucune graphie et porte une
     * explication la porte sur tous ses items ou sur aucun, et chaque item tire par le chargeur la
     * transporte, non vide. La graphie est une notion du francais ; l'explication, elle, n'a pas de
     * matiere (decision 1). Ce qui est perdu, nomme dans l'ADR : une cle {@code explicaton} mal
     * orthographiee sur tous les items d'un paquet sans graphie ne rougit plus. La comparaison au
     * tirage se fait sur le paquet et non par enonce : un paquet sans graphie peut repeter son enonce
     * d'un item a l'autre (ART-CP-10).
     */
    static void verifierLesExplicationsDUnPaquetSansGraphie(String paquet, List<Map<?, ?>> ecrits,
            List<Question> tires) {
        for (Map<?, ?> ecrit : ecrits) {
            assertThat(ecrit.containsKey(EXPLICATION))
                    .as("paquet %s, item \"%s\" : ce paquet ne declare aucune graphie et porte une"
                            + " explication ; il la porte donc sur tous ses items ou sur aucun"
                            + " (ADR-0024, amendement du 2026-10-09)", paquet, ecrit.get("enonce"))
                    .isTrue();
        }
        for (Question tire : tires) {
            assertThat(tire.explication().filter(texte -> !texte.isBlank()))
                    .as("paquet %s, item \"%s\" : l'explication ecrite n'arrive pas a la mecanique,"
                            + " ou arrive vide", paquet, tire.enonce())
                    .isPresent();
        }
    }

    @Test
    void chaquePaquetDeProductionExpliqueSesItemsAVariation() {
        verifierQueChaquePaquetExpliqueSesItemsAVariation(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3, premier bord du critere : un item a variation sans son explication.
     * C'est le defaut qu'un auteur produit en ecrivant les quinze items avant les quinze textes.
     */
    @Test
    void unItemAVariationSansExplicationFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("variation-sans-explication"),
                CONTRE_EXEMPLE_CLASSPATH + "/variation-sans-explication"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("item a variation");
    }

    /**
     * Second bord, celui qu'un garde-fou ecrit a la hate oublierait : une explication sur un item
     * regulier. "Sur un item regulier il n'y a rien a expliquer" (nadia-prof, avis de conception du
     * 2026-08-06) - un critere qui n'exigerait que la presence laisserait passer les quinze.
     */
    @Test
    void unItemRegulierQuiPorteUneExplicationFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("regulier-avec-explication"),
                CONTRE_EXEMPLE_CLASSPATH + "/regulier-avec-explication"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("rien a expliquer");
    }

    /** Troisieme bord : un texte present, mais le meme pour tous, qui ne nomme aucun mot. */
    @Test
    void uneExplicationQuiNeNommePasSonMotFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("texte-qui-ne-nomme-pas-son-mot"),
                CONTRE_EXEMPLE_CLASSPATH + "/texte-qui-ne-nomme-pas-son-mot"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nomme le mot que l'enfant vient de lire");
    }

    /**
     * Amendement du 2026-10-09, materiau reel : le premier paquet de production qui declare graphie et
     * explication, prive en memoire de toutes ses graphies. Il tombe dans la regle "tous ou aucun",
     * et ses items reguliers, sans explication, la font rougir. C'est aussi la faute de frappe
     * {@code grafie} sur tout un paquet de francais : elle reste refusee.
     */
    @Test
    void unPaquetReelPriveDeSesGraphiesRougitSurLaRegleTousOuAucun() {
        String paquet = ChaquePaquetPorteUnIndiceTest.paquets(CONTENU_DE_PRODUCTION).stream()
                .filter(nom -> itemsEcrits(lireYaml("/contenu/" + nom + ".yaml")).stream()
                        .anyMatch(item -> item.containsKey(GRAPHIE) && item.containsKey(EXPLICATION)))
                .findFirst().orElseThrow(() -> new AssertionError(
                        "ADR-0003 : aucun paquet de production ne declare graphie et explication"));
        List<Map<?, ?>> sansGraphie = new ArrayList<>();
        for (Map<?, ?> item : itemsEcrits(lireYaml("/contenu/" + paquet + ".yaml"))) {
            Map<Object, Object> copie = new LinkedHashMap<>(item);
            copie.remove(GRAPHIE);
            sansGraphie.add(copie);
        }
        assertThat(sansGraphie)
                .as("le materiau doit porter des items avec et sans explication")
                .anyMatch(item -> item.containsKey(EXPLICATION))
                .anyMatch(item -> !item.containsKey(EXPLICATION));

        assertThatThrownBy(() -> verifierLesExplicationsDUnPaquetSansGraphie(paquet, sansGraphie, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tous ses items ou sur aucun");
    }

    /**
     * Amendement du 2026-10-09 : les six items d'ART-CP-10 recopies de
     * CHOIX-OEUVRES-s56-art-cp-10.md, section 3 (enonce repete, explication sur chacun, aucune
     * graphie), passent ; le meme paquet prive d'une seule explication rougit.
     */
    @Test
    void unPaquetSansGraphieQuiExpliqueTousSesItemsPasseEtUnTrouRougit() {
        verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("sans-graphie-explication-partout"),
                CONTRE_EXEMPLE_CLASSPATH + "/sans-graphie-explication-partout");
        assertThatThrownBy(() -> verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("sans-graphie-une-explication-manque"),
                CONTRE_EXEMPLE_CLASSPATH + "/sans-graphie-une-explication-manque"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tous ses items ou sur aucun");
        assertThatThrownBy(() -> verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("sans-graphie-une-explication-vide"),
                CONTRE_EXEMPLE_CLASSPATH + "/sans-graphie-une-explication-vide"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("arrive vide");
    }

    /** Le meme paquet, ses deux defauts corriges, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetCorrigePasseLeGardeFou() {
        verifierQueChaquePaquetExpliqueSesItemsAVariation(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }
}
