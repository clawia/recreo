package com.recreo.bootstrap.rendu;

import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0062, clause 1 : dans le document servi d'une manche, un element porte la marque de
 * designation si et seulement si l'item declare une part designee, et le texte de cet element est
 * exactement la valeur declaree.</b>
 *
 * <p>Sur le modele de {@code LaConsigneDUnJeuServiAtteintLEcranDeMancheTest} (chaine complete
 * jusqu'au document rendu, catalogue reel) et de {@code LExplicationDUnItemReelAtteintLEcranDeRetourTest}
 * (un item reel, lu directement dans son paquet YAML, parce que ce bootstrap est le seul module qui
 * voit a la fois le contenu et le rendu web).</p>
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1), et aucun nom de paquet n'apparait dans ce
 * fichier.</b> Le cote "absent partout ailleurs" parcourt les entrees reelles de {@code
 * catalogue.yaml}. Le cote "present quand declare" ne peut pas se contenter du meme parcours : le
 * seul paquet qui declare une part designee a ce jour est hors catalogue (`DEC-0045`), et nommer son
 * fichier ici recopierait exactement l'association que {@code catalogue.yaml} ne porte plus - la
 * forme qu'{@code ADR-0061} vient de sanctionner. Ce fichier decouvre donc le ou les paquets marques
 * en balayant le <b>repertoire de contenu reel</b> ({@code classpath*:contenu/*.yaml}, la meme
 * source que les garde-fous de contenu de {@code adapter-content}) et en retenant ceux dont au moins
 * un item declare {@code partDesignee} - jamais un nom ecrit a la main.</p>
 *
 * <p><b>Derivation totale (obligation 2).</b> Le cote "absent" verifie une manche par jeu du
 * catalogue reel - la propriete jugee (aucune marque sans declaration) est independante du contenu
 * precis de l'item tire, un seul suffit a la mettre a l'epreuve, comme
 * {@code LaConsigneDUnJeuServiAtteintLEcranDeMancheTest} le fait deja pour la consigne. Le cote
 * "present" joue en revanche <b>chaque item</b> du paquet marque, dans l'ordre du YAML (le
 * {@link RandomSource} de test vaut toujours 0, et {@code ListContentSource.next} tire alors
 * sequentiellement le premier item non deja vu) : aucun n'est ecarte.</p>
 *
 * <p><b>Mise a l'epreuve (obligation 3, les cinq de l'ADR)</b> : voir les cinq {@code @Test} dedies
 * ci-dessous, chacun mutant le <b>document reellement rendu</b>, jamais une fixture ecrite a la
 * main.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LaPartDesigneeDUnItemServiEstMarqueeALEcranTest.JeuxDeTestConfiguration.class)
class LaPartDesigneeDUnItemServiEstMarqueeALEcranTest {

    /** Prefixe des jeux de preuve synthetiques que ce fichier construit pour un paquet marque. */
    private static final String PREFIXE_JEU_DE_PREUVE = "preuve-";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = LaPartDesigneeDUnItemServiEstMarqueeALEcranTest.class
                .getResourceAsStream(cheminClasspath)) {
            assertThat(flux)
                    .as("%s introuvable sur le classpath", cheminClasspath)
                    .isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return corps;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** ADR-0062 : conversion identique a QuestionContentSourceFactory, 'partDesignee' comprise. */
    @SuppressWarnings("unchecked")
    private static Question versQuestion(Map<String, Object> item, int position) {
        String enonce = String.valueOf(item.get("enonce"));
        String bonneReponse = String.valueOf(item.get("bonneReponse"));
        List<String> distracteurs = ((List<Object>) item.get("distracteurs")).stream()
                .map(String::valueOf).toList();
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        Object partDesignee = item.get("partDesignee");
        return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteurs,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                partDesignee == null ? Optional.empty() : Optional.of(String.valueOf(partDesignee)));
    }

    private static ContentDescriptor descripteurDe(Map<String, Object> corps) {
        @SuppressWarnings("unchecked")
        Map<String, Object> descripteur = (Map<String, Object>) corps.get("descripteur");
        return new ContentDescriptor(String.valueOf(descripteur.get("matiere")),
                String.valueOf(descripteur.get("niveau")), String.valueOf(descripteur.get("domaine")),
                String.valueOf(descripteur.get("competence")));
    }

    /**
     * <b>ADR-0013 obligation 2, et ADR-0088 clause C : un type de paquet que ce garde-fou ne sait pas
     * juger est un echec, jamais un silence.</b>
     *
     * <p><b>Le defaut que ce geste paie.</b> Cette population etait construite par un
     * {@code continue} sur tout paquet qui n'est pas de type {@code question}. Le chargeur de
     * production enregistre <b>quatre</b> types ({@code YamlContentPackLoader.FABRIQUES}) ; ce
     * garde-fou en connaissait un et ecartait les trois autres sans le dire, sur une suite verte.
     *
     * <p><b>Ce que ce gate decide, et ce qu'il refuse de decider.</b> Il laisse passer les trois
     * types deja instruits par ce fichier et <b>leve</b> sur tout autre.</p>
     *
     * <p><b>Decide au 2026-09-10 (S33-PAL2-1, s34) : {@code designation} rejoint la liste des types
     * instruits, et cette decision est definitive plutot qu'en attente.</b> {@code ZoneADesigner} ne
     * declare aucune composante {@code partDesignee} dans son contrat (ADR-0080, decision 1) : une
     * designation ne met en evidence aucun mot d'un enonce, elle fait designer un endroit sur une
     * image. Le type est donc reconnu et retombe dans le {@code continue} deja pose pour tout type
     * qui n'est pas {@code question} : c'etait deja le comportement voulu, seulement pas decide
     * explicitement.</p>
     */
    private static void verifierQueLeTypeEstInstruit(String paquet, Map<String, Object> corps) {
        String type = String.valueOf(corps.get("type"));
        switch (type) {
            case "question", "additions-bornees", "saisie", "designation" -> { }
            default -> throw new IllegalStateException(refusDuType(paquet, type));
        }
    }

    private static String refusDuType(String paquet, String type) {
        return "paquet " + paquet + " : type '" + type + "', que ce garde-fou n'a pas instruit et"
                + " qu'il ne sait donc pas juger. Decider ici plutot que d'ecarter ce paquet en"
                + " silence (ADR-0013 obligation 2, ADR-0088 clause C)";
    }

    private static boolean estUnPaquetDeQuestions(Map<String, Object> corps) {
        return "question".equals(String.valueOf(corps.get("type")));
    }

    @SuppressWarnings("unchecked")
    private static List<Question> itemsDuCorps(Map<String, Object> corps) {
        List<Map<String, Object>> items = (List<Map<String, Object>>) corps.get("items");
        List<Question> questions = new ArrayList<>();
        int position = 0;
        for (Map<String, Object> item : items) {
            questions.add(versQuestion(item, position));
            position++;
        }
        return questions;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> entreesDuCatalogueReel() {
        Map<String, Object> corps = lireYaml("/catalogue.yaml");
        return (List<Map<String, Object>>) corps.get("jeux");
    }

    /**
     * ADR-0013 obligation 1 : les paquets marques sont decouverts, jamais nommes. Meme famille de
     * perimetre que les garde-fous de contenu (balayage du repertoire de contenu), transposee au
     * classpath puisque ce module ne voit ce repertoire qu'a travers sa dependance sur
     * {@code adapter-content}, jamais comme un chemin de fichier de son propre arbre.
     */
    private static List<String> paquetsDuRepertoireDeContenu() {
        try {
            Resource[] ressources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:contenu/*.yaml");
            List<String> noms = new ArrayList<>();
            for (Resource ressource : ressources) {
                String nomFichier = ressource.getFilename();
                if (nomFichier != null && nomFichier.endsWith(".yaml")) {
                    noms.add(nomFichier.substring(0, nomFichier.length() - ".yaml".length()));
                }
            }
            return noms;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static Document ecran(MockMvc mockMvc, String jeuId, MockHttpSession session) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    private static Document coup(MockMvc mockMvc, String jeuId, MockHttpSession session, String proposition)
            throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                        .param("propositionChoisie", proposition))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    /** Les defauts d'un ecran de manche par rapport a l'item qu'il est cense montrer. */
    private static List<String> manquements(String jeuId, Question item, Document ecran) {
        List<String> defauts = new ArrayList<>();
        Element enonce = ecran.selectFirst(".enonce");
        if (enonce == null) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : aucun enonce lisible");
            return defauts;
        }
        Elements marques = enonce.select(".mot-designe");
        if (item.partDesignee().isEmpty()) {
            if (!marques.isEmpty()) {
                defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : une marque de"
                        + " designation apparait alors que cet item n'en declare aucune (ADR-0062,"
                        + " clause 1, seconde moitie)");
            }
            return defauts;
        }
        String attendu = item.partDesignee().orElseThrow();
        if (marques.size() != 1) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : " + marques.size()
                    + " element(s) porteur(s) de la marque de designation, un seul attendu"
                    + " (ADR-0062, clause 1)");
        } else if (!attendu.equals(marques.get(0).wholeText())) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : la marque rendue vaut \""
                    + marques.get(0).wholeText() + "\", attendue exactement \"" + attendu
                    + "\" (ADR-0062, clause 1, premiere moitie)");
        }
        if (!item.enonce().equals(enonce.wholeText())) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : le texte total de"
                    + " l'enonce rendu (\"" + enonce.wholeText() + "\") ne correspond plus a l'enonce"
                    + " declare une fois la marque posee - la marque ne doit ni deborder ni tomber a"
                    + " cote");
        }
        return defauts;
    }

    @Test
    void aucunJeuDuCatalogueReelNAfficheDeMarqueSansDeclaration() throws Exception {
        List<GameDefinition> jeuxReels = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList();
        assertThat(jeuxReels)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun jeu"
                        + " de type question")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxReels) {
            Document ecranDeManche = ecran(mockMvc, jeu.id(), new MockHttpSession());
            Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeu) + ".yaml")).get(0);
            defauts.addAll(manquements(jeu.id(), premierItem, ecranDeManche));
        }

        assertThat(defauts)
                .as("ADR-0062, clause 1, seconde moitie : aucune entree de catalogue.yaml ne declare"
                        + " de part designee a ce jour, donc aucune marque ne doit atteindre l'ecran")
                .isEmpty();
    }

    /**
     * Extrait pour que la clause de non-vacuite (ADR-0051) soit elle-meme mise a l'epreuve
     * ci-dessous, sur une liste vide, sans passer par Spring ni par MockMvc.
     */
    private static void verifierChaqueItemDuPaquetMarque(MockMvc mockMvc, List<GameDefinition> jeuxDePreuve)
            throws Exception {
        assertThat(jeuxDePreuve)
                .as("ADR-0051 : ce garde-fou ne prouve rien si aucun paquet du repertoire de contenu"
                        + " ne declare de part designee")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxDePreuve) {
            List<Question> items = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeu) + ".yaml"));
            MockHttpSession session = new MockHttpSession();
            Document ecranCourant = ecran(mockMvc, jeu.id(), session);
            for (int rang = 0; rang < items.size(); rang++) {
                Question attendu = items.get(rang);
                defauts.addAll(manquements(jeu.id(), attendu, ecranCourant));
                if (rang < items.size() - 1) {
                    ecranCourant = coup(mockMvc, jeu.id(), session, attendu.bonneReponse());
                }
            }
        }

        assertThat(defauts)
                .as("ADR-0062, clause 1, premiere moitie : chaque item declarant une part designee la"
                        + " voit atteindre l'ecran, avec exactement son texte")
                .isEmpty();
    }

    @Test
    void chaqueItemDuPaquetMarqueAtteintLEcranAvecSaMarqueExacte() throws Exception {
        verifierChaqueItemDuPaquetMarque(mockMvc, partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList());
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve 5 (moitie clause 1) : la forme qu'aurait ce garde-fou
     * si le dernier paquet marque disparaissait du repertoire de contenu - non-vacuite, pas un vert
     * a vide.
     */
    @Test
    void aucunPaquetMarqueFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierChaqueItemDuPaquetMarque(mockMvc, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /** Le nom du paquet reel derriere une entree, jamais ecrit en dur : lu sur l'entree elle-meme. */
    private static String paquetDe(GameDefinition jeu) {
        return jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE)
                ? jeu.id().substring(PREFIXE_JEU_DE_PREUVE.length())
                : entreesDuCatalogueReel().stream()
                        .filter(entree -> jeu.id().equals(String.valueOf(entree.get("id"))))
                        .map(entree -> String.valueOf(entree.get("paquet")))
                        .findFirst()
                        .orElseThrow();
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve 1 : la marque retiree du document rendu doit rendre la
     * clause rouge sur sa premiere moitie.
     */
    @Test
    void uneMarqueRetireeDuDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition jeuMarque = partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .findFirst().orElseThrow();
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuMarque) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuMarque.id(), new MockHttpSession());
        assertThat(manquements(jeuMarque.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document sansMarque = ecranReel.clone();
        sansMarque.select(".mot-designe").remove();
        assertThat(manquements(jeuMarque.id(), premierItem, sansMarque))
                .as("une marque retiree du document rendu doit rendre cette clause rouge")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 2 : la marque posee sur un item qui ne declare rien - la mutation qui simule
     * exactement le contournement refuse (parcours des guillemets dans le gabarit partage). Sans
     * cette morsure, rien ne prouve que la seconde moitie de la clause morde vraiment.
     */
    @Test
    void uneMarquePoseeSurUnItemQuiNeDeclareRienFaitEchouerLaClause() throws Exception {
        GameDefinition jeuReel = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .findFirst().orElseThrow();
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuReel) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuReel.id(), new MockHttpSession());
        assertThat(manquements(jeuReel.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document avecMarqueAjoutee = ecranReel.clone();
        Element enonce = avecMarqueAjoutee.selectFirst(".enonce");
        enonce.prependElement("span").addClass("mot-designe").text("intrus");
        assertThat(manquements(jeuReel.id(), premierItem, avecMarqueAjoutee))
                .as("une marque posee sur un item qui ne declare aucune part designee doit rendre"
                        + " cette clause rouge - c'est exactement la forme du contournement refuse"
                        + " (parcours des guillemets dans le gabarit partage)")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 3 : la marque posee guillemets compris - une assertion de simple presence
     * resterait verte, l'egalite de texte exacte ne l'est pas.
     */
    @Test
    void uneMarqueQuiDeborneSurLesGuillemetsFaitEchouerLaClause() throws Exception {
        GameDefinition jeuMarque = partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .findFirst().orElseThrow();
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuMarque) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuMarque.id(), new MockHttpSession());
        assertThat(manquements(jeuMarque.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document marqueDebordante = ecranReel.clone();
        Element marque = marqueDebordante.selectFirst(".mot-designe");
        marque.text("« " + marque.text() + " »");
        assertThat(manquements(jeuMarque.id(), premierItem, marqueDebordante))
                .as("une marque qui deborde sur les guillemets doit rendre cette clause rouge : la"
                        + " marque doit porter exactement la valeur declaree, pas plus")
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3, et la morsure porte sur du <b>materiau reel</b> : le corps d'un paquet
     * servi du depot, decouvert et non nomme, dont la seule mutation est la valeur de sa cle
     * {@code type}. Le type fictif ne nomme aucun type reel du depot (question, saisie,
     * additions-bornees, designation sont tous desormais instruits, ADR-0088 clause C) : c'est ce qui
     * prouve que la derivation mord sur un type neuf, jamais sur un nom particulier.
     */
    @Test
    void unPaquetDUnTypeQueCeGardeFouNeSaitPasJugerLeFaitEchouer() {
        String paquet = paquetsDuRepertoireDeContenu().stream().sorted().findFirst().orElseThrow();
        Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
        corps.put("type", "type-fictif-non-instruit");

        assertThatThrownBy(() -> verifierQueLeTypeEstInstruit(paquet, corps))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("type 'type-fictif-non-instruit'")
                .hasMessageContaining("ne sait donc pas juger");
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            List<GameDefinition> jeux = new ArrayList<>();

            for (Map<String, Object> entree : entreesDuCatalogueReel()) {
                String paquet = String.valueOf(entree.get("paquet"));
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                verifierQueLeTypeEstInstruit(paquet, corps);
                if (!estUnPaquetDeQuestions(corps)) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(String.valueOf(entree.get("id")), String.valueOf(entree.get("titre")),
                        String.valueOf(entree.get("consigne")), BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, itemsDuCorps(corps)), 1));
            }

            for (String paquet : paquetsDuRepertoireDeContenu()) {
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                verifierQueLeTypeEstInstruit(paquet, corps);
                if (!estUnPaquetDeQuestions(corps)) {
                    continue;
                }
                List<Question> items = itemsDuCorps(corps);
                if (items.stream().noneMatch(question -> question.partDesignee().isPresent())) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(PREFIXE_JEU_DE_PREUVE + paquet, "Preuve " + paquet,
                        "Consigne de preuve", BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, items), items.size()));
            }

            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
