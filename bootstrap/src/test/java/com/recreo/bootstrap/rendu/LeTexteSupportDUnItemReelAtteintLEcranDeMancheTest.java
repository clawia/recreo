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
 * <b>ADR-0050, decision 6 : dans le document servi d'une manche, un element porte le texte-support
 * si et seulement si l'item declare {@code texteSupport}, et le texte de cet element est exactement
 * la valeur declaree.</b>
 *
 * <p>Sur le modele exact de {@code LaPartDesigneeDUnItemServiEstMarqueeALEcranTest} (ADR-0062), qui
 * fait face a la meme situation temporelle : la sequence imposee de {@code S17-2} ecrit ce test
 * <b>avant</b> tout contenu qui declare {@code texteSupport} (avis-faisabilite de theo-archi, s22,
 * section 3.3 - "le test qui traverse la frontiere HTTP", puis "le contenu, en dernier"). Ce fichier
 * est donc <b>rouge a l'ecriture</b>, sur sa clause de non-vacuite, exactement comme
 * {@code LaPartDesigneeDUnItemServiEstMarqueeALEcranTest} l'etait le 2026-08-25 (ADR-0062, en-tete de
 * statut) : il juge des objets qui n'existent pas encore, et son declencheur est le commit qui livre
 * le paquet de contenu de {@code S17-2}. Ce n'est pas un test ecrit pour rien : c'est ce qui empeche
 * la cle YAML `texteSupport:` d'un futur paquet d'etre lue en silence, sans jamais atteindre l'ecran -
 * exactement le risque que la Javadoc de {@code QuestionContentSourceFactory} nomme elle-meme
 * (ADR-0050, decision 6).</p>
 *
 * <p><b>Pourquoi ce module.</b> Ce bootstrap est le seul qui voit a la fois le contenu
 * ({@code adapter-content}) et le rendu web ({@code adapter-web}) - meme motif d'emplacement que
 * {@code LExplicationDUnItemReelAtteintLEcranDeRetourTest} (ADR-0024) et
 * {@code LaPartDesigneeDUnItemServiEstMarqueeALEcranTest} (ADR-0062).</p>
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1), et aucun nom de paquet n'apparait dans ce
 * fichier.</b> Le cote "absent partout ailleurs" parcourt les entrees reelles de
 * {@code catalogue.yaml}. Le cote "present quand declare" decouvre le ou les paquets marques en
 * balayant le <b>repertoire de contenu reel</b> ({@code classpath*:contenu/*.yaml}, la meme source
 * que les garde-fous de contenu de {@code adapter-content}) et en retenant ceux dont au moins un item
 * declare {@code texteSupport} - jamais un nom ecrit a la main.</p>
 *
 * <p><b>Derivation totale (obligation 2).</b> Le cote "absent" verifie une manche par jeu du
 * catalogue reel - la propriete jugee (aucun texte-support sans declaration) est independante du
 * contenu precis de l'item tire, un seul suffit a la mettre a l'epreuve. Le cote "present" joue en
 * revanche <b>chaque item</b> du paquet marque, dans l'ordre du YAML (le {@link RandomSource} de
 * test vaut toujours 0, et {@code ListContentSource.next} tire alors sequentiellement le premier
 * item non deja vu) : aucun n'est ecarte.</p>
 *
 * <p><b>Mise a l'epreuve (obligation 3)</b> : voir les {@code @Test} dedies ci-dessous, chacun mutant
 * le <b>document reellement rendu</b>, jamais une fixture ecrite a la main.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest.JeuxDeTestConfiguration.class)
class LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest {

    /** Prefixe des jeux de preuve synthetiques que ce fichier construit pour un paquet marque. */
    private static final String PREFIXE_JEU_DE_PREUVE = "preuve-texte-support-";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest.class
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

    /** ADR-0050 : conversion identique a QuestionContentSourceFactory, 'texteSupport' compris. */
    @SuppressWarnings("unchecked")
    private static Question versQuestion(Map<String, Object> item, int position) {
        String enonce = String.valueOf(item.get("enonce"));
        String bonneReponse = String.valueOf(item.get("bonneReponse"));
        List<String> distracteurs = ((List<Object>) item.get("distracteurs")).stream()
                .map(String::valueOf).toList();
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        Object partDesignee = item.get("partDesignee");
        Object texteSupport = item.get("texteSupport");
        return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteurs,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                partDesignee == null ? Optional.empty() : Optional.of(String.valueOf(partDesignee)),
                texteSupport == null ? Optional.empty() : Optional.of(String.valueOf(texteSupport)));
    }

    private static ContentDescriptor descripteurDe(Map<String, Object> corps) {
        @SuppressWarnings("unchecked")
        Map<String, Object> descripteur = (Map<String, Object>) corps.get("descripteur");
        return new ContentDescriptor(String.valueOf(descripteur.get("matiere")),
                String.valueOf(descripteur.get("niveau")), String.valueOf(descripteur.get("domaine")),
                String.valueOf(descripteur.get("competence")));
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
        Elements textesSupport = ecran.select(".texte-support");
        if (item.texteSupport().isEmpty()) {
            if (!textesSupport.isEmpty()) {
                defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : un texte-support"
                        + " apparait alors que cet item n'en declare aucun (ADR-0050, decision 6,"
                        + " seconde moitie)");
            }
            return defauts;
        }
        String attendu = item.texteSupport().orElseThrow();
        if (textesSupport.size() != 1) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : " + textesSupport.size()
                    + " element(s) .texte-support, un seul attendu (ADR-0050, decision 6)");
        } else if (!attendu.equals(textesSupport.get(0).text())) {
            defauts.add("jeu " + jeuId + ", item \"" + item.enonce() + "\" : le texte-support rendu"
                    + " vaut \"" + textesSupport.get(0).text() + "\", attendu exactement \"" + attendu
                    + "\" (ADR-0050, decision 6, premiere moitie)");
        }
        return defauts;
    }

    @Test
    void aucunJeuDuCatalogueReelNAfficheDeTexteSupportSansDeclaration() throws Exception {
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
                .as("ADR-0050, decision 6, seconde moitie : aucune entree de catalogue.yaml ne declare"
                        + " de texte-support a ce jour, donc aucun texte-support ne doit atteindre"
                        + " l'ecran")
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
                        + " ne declare de texte-support - c'est le cas tant que le contenu de S17-2"
                        + " n'est pas ecrit, et c'est voulu (voir la Javadoc de classe)")
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
                .as("ADR-0050, decision 6, premiere moitie : chaque item declarant un texte-support le"
                        + " voit atteindre l'ecran, avec exactement son texte")
                .isEmpty();
    }

    @Test
    void chaqueItemDuPaquetMarqueAtteintLEcranAvecSonTexteSupportExact() throws Exception {
        verifierChaqueItemDuPaquetMarque(mockMvc, partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList());
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve de non-vacuite : la forme qu'aurait ce garde-fou si
     * aucun paquet marque n'existait dans le repertoire de contenu - non-vacuite, pas un vert a
     * vide. C'est l'etat reel de ce fichier tant que le contenu de {@code S17-2} n'est pas ecrit.
     */
    @Test
    void aucunPaquetMarqueFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierChaqueItemDuPaquetMarque(mockMvc, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * Le premier jeu de preuve marque, pour les mises a l'epreuve qui mutent un ecran reellement
     * rendu. Echoue avec un message explicite plutot qu'une {@code NoSuchElementException} muette
     * tant qu'aucun paquet du repertoire de contenu ne declare {@code texteSupport} - c'est l'etat
     * de cet arbre avant le contenu de {@code S17-2} (voir la Javadoc de classe).
     */
    private static GameDefinition jeuMarquePourMiseALEpreuve(PartieUseCase partie) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .findFirst()
                .orElseThrow(() -> new AssertionError("aucun paquet du repertoire de contenu ne"
                        + " declare texteSupport a ce jour : cette mise a l'epreuve est due avec le"
                        + " contenu de S17-2 (ADR-0050) et ne peut pas s'executer avant"));
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
     * ADR-0013 obligation 3, mise a l'epreuve 1 : le texte-support retire du document rendu doit
     * rendre la clause rouge sur sa premiere moitie.
     */
    @Test
    void unTexteSupportRetireDuDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition jeuMarque = jeuMarquePourMiseALEpreuve(partie);
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuMarque) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuMarque.id(), new MockHttpSession());
        assertThat(manquements(jeuMarque.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document sansTexteSupport = ecranReel.clone();
        sansTexteSupport.select(".texte-support").remove();
        assertThat(manquements(jeuMarque.id(), premierItem, sansTexteSupport))
                .as("un texte-support retire du document rendu doit rendre cette clause rouge")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 2 : un texte-support pose sur un item qui ne declare rien - la mutation qui
     * simule exactement l'ecart muet refuse par la Javadoc de {@code QuestionContentSourceFactory}
     * (une cle ignoree en silence). Sans cette morsure, rien ne prouve que la seconde moitie de la
     * clause morde vraiment.
     */
    @Test
    void unTexteSupportPoseSurUnItemQuiNeDeclareRienFaitEchouerLaClause() throws Exception {
        GameDefinition jeuReel = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .findFirst().orElseThrow();
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuReel) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuReel.id(), new MockHttpSession());
        assertThat(manquements(jeuReel.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document avecTexteSupportAjoute = ecranReel.clone();
        Element consigne = avecTexteSupportAjoute.selectFirst(".consigne");
        consigne.after("<p class=\"texte-support\">intrus</p>");
        assertThat(manquements(jeuReel.id(), premierItem, avecTexteSupportAjoute))
                .as("un texte-support pose sur un item qui ne declare rien doit rendre cette clause"
                        + " rouge - c'est exactement l'ecart muet que la Javadoc de"
                        + " QuestionContentSourceFactory nomme")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 3 : le texte rendu differe de la valeur declaree - une assertion de simple
     * presence resterait verte, l'egalite de texte exacte ne l'est pas.
     */
    @Test
    void unTexteSupportQuiDiffereDeLaValeurDeclareeFaitEchouerLaClause() throws Exception {
        GameDefinition jeuMarque = jeuMarquePourMiseALEpreuve(partie);
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuMarque) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuMarque.id(), new MockHttpSession());
        assertThat(manquements(jeuMarque.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document texteAltere = ecranReel.clone();
        Element support = texteAltere.selectFirst(".texte-support");
        support.text(support.text() + " altere");
        assertThat(manquements(jeuMarque.id(), premierItem, texteAltere))
                .as("un texte-support qui ne correspond plus exactement a la valeur declaree doit"
                        + " rendre cette clause rouge")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 4 : deux elements .texte-support pour un seul item - un seul est attendu.
     */
    @Test
    void deuxTextesSupportPourUnMemeItemFaitEchouerLaClause() throws Exception {
        GameDefinition jeuMarque = jeuMarquePourMiseALEpreuve(partie);
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuMarque) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuMarque.id(), new MockHttpSession());
        assertThat(manquements(jeuMarque.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document texteDouble = ecranReel.clone();
        Element support = texteDouble.selectFirst(".texte-support");
        support.after(support.clone());
        assertThat(manquements(jeuMarque.id(), premierItem, texteDouble))
                .as("deux elements .texte-support pour le meme item doivent rendre cette clause rouge")
                .isNotEmpty();
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            List<GameDefinition> jeux = new ArrayList<>();

            for (Map<String, Object> entree : entreesDuCatalogueReel()) {
                String paquet = String.valueOf(entree.get("paquet"));
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                if (!estUnPaquetDeQuestions(corps)) {
                    // paquet combinatoire : ses items ne sont jamais lus dans un YAML, donc
                    // structurellement incapables de declarer texteSupport (ADR-0032, meme exclusion).
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(String.valueOf(entree.get("id")), String.valueOf(entree.get("titre")),
                        String.valueOf(entree.get("consigne")), BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, itemsDuCorps(corps)), 1));
            }

            for (String paquet : paquetsDuRepertoireDeContenu()) {
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                if (!estUnPaquetDeQuestions(corps)) {
                    continue;
                }
                List<Question> items = itemsDuCorps(corps);
                if (items.stream().noneMatch(question -> question.texteSupport().isPresent())) {
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
