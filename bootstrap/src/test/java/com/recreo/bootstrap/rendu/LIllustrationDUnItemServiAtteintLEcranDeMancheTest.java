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
 * <b>ADR-0073 : dans le document servi d'une manche, un element porte l'illustration si et seulement
 * si l'item declare une {@code illustration} ; sa cible est exactement l'identifiant declare, resolu
 * par l'adapter web, et son texte alternatif est exactement celui que l'item declare.</b>
 *
 * <p>Sur le patron des trois tests de traversee par champ deja livres -
 * {@code LExplicationDUnItemReelAtteintLEcranDeRetourTest} (ADR-0024),
 * {@code LaPartDesigneeDUnItemServiEstMarqueeALEcranTest} (ADR-0062) et
 * {@code LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest} (ADR-0050). Meme motif d'emplacement :
 * ce bootstrap est le seul module qui voie a la fois le contenu et le rendu web.</p>
 *
 * <p><b>Ce que ce fichier prouve et que le garde-fou d'ADR-0073 ne prouve pas.</b>
 * {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} verifie sur la <b>donnee</b> qu'un
 * identifiant declare resout vers un fichier servi et qu'aucun fichier ne dort. Son auteur ecrit
 * lui-meme ce qu'il ne prouve pas : <i>"que l'image atteigne l'ecran"</i>. C'est ce qui est prouve
 * ici, et c'est du au lot qui livre le champ, pas a celui qui l'a decide.</p>
 *
 * <p><b>Ce que ce fichier ne prouve pas non plus, et je le dis plutot que de le laisser supposer</b>
 * : que ce que le texte alternatif <i>dit</i> soit juste. Il exige qu'il existe, qu'il ne soit pas
 * vide et qu'il soit exactement celui de l'item. Qu'il decrive ce qu'il y a a regarder sans donner la
 * reponse est CON-5, verifie item par item par {@code nadia-prof}, et aucun automate ne le jugera.</p>
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1), et aucun nom de paquet n'apparait ici.</b> Le cote
 * "absent partout ailleurs" parcourt les entrees reelles de {@code catalogue.yaml}. Le cote "present
 * quand declare" balaye le <b>repertoire de contenu reel</b> ({@code classpath*:contenu/*.yaml}, la
 * meme source que les garde-fous de contenu) et retient les paquets dont au moins un item declare
 * {@code illustration} - jamais un nom ecrit a la main.</p>
 *
 * <p><b>Le jeu de preuve construit en memoire, et pourquoi il n'est pas un contournement
 * (ADR-0051).</b> Ce lot livre le <b>champ</b>, du domaine jusqu'a l'ecran ; les paquets illustres
 * arrivent dans des lots suivants, derriere celui-ci. Le cote "present" porterait donc aujourd'hui
 * sur une population vide, et les trois tests de traversee cites plus haut ont resolu ce meme cas de
 * figure en etant <b>rouges a l'ecriture</b> jusqu'a l'arrivee de leur contenu. Ce lot-ci ne peut pas
 * l'etre : deux chaines de contenu attendent son vert pour demarrer. Un item de preuve est donc
 * construit en memoire, avec le <b>vrai</b> controleur, le <b>vrai</b> gabarit et la <b>vraie</b>
 * traversee HTTP - seul le paquet est synthetique. Le jour ou un paquet illustre entre dans le
 * repertoire de contenu, il est ajoute a cette population <b>sans qu'une ligne d'ici change</b>, et
 * chacun de ses items est joue.</p>
 *
 * <p><b>Derivation totale (obligation 2).</b> Le cote "absent" verifie une manche par jeu du
 * catalogue reel. Le cote "present" joue <b>chaque item</b> de chaque paquet illustre, dans l'ordre
 * du YAML (le {@link RandomSource} de test vaut toujours 0, et {@code ListContentSource.next} tire
 * alors sequentiellement le premier item non deja vu) : aucun n'est ecarte.</p>
 *
 * <p><b>Mise a l'epreuve (obligation 3)</b> : six {@code @Test} dedies, chacun mutant le
 * <b>document reellement rendu</b>, jamais une fixture ecrite a la main.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LIllustrationDUnItemServiAtteintLEcranDeMancheTest.JeuxDeTestConfiguration.class)
class LIllustrationDUnItemServiAtteintLEcranDeMancheTest {

    /** Prefixe des jeux batis pour un paquet illustre, reel ou de preuve. */
    private static final String PREFIXE_JEU_ILLUSTRE = "illustre-";

    /** Le paquet de preuve, seul objet synthetique de ce fichier (voir la Javadoc de classe). */
    private static final String PAQUET_DE_PREUVE = "paquet-de-preuve";

    /**
     * ADR-0073 clauses 1 et 3 : le repertoire et l'extension appartiennent a l'adapter web, jamais au
     * contenu. Ce garde-fou les recompose ici pour verifier que c'est bien lui qui les a poses.
     */
    private static final String REPERTOIRE_SERVI = "/illustrations/";

    private static final String EXTENSION_SERVIE = ".svg";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = LIllustrationDUnItemServiAtteintLEcranDeMancheTest.class
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

    /** ADR-0073 : conversion identique a QuestionContentSourceFactory, les deux cles comprises. */
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
        Object illustration = item.get("illustration");
        Object texteAlternatif = item.get("illustrationTexteAlternatif");
        return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteurs,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                partDesignee == null ? Optional.empty() : Optional.of(String.valueOf(partDesignee)),
                texteSupport == null ? Optional.empty() : Optional.of(String.valueOf(texteSupport)),
                illustration == null ? Optional.empty() : Optional.of(String.valueOf(illustration)),
                texteAlternatif == null ? Optional.empty() : Optional.of(String.valueOf(texteAlternatif)));
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
     * <p><b>Le defaut que ce geste paie, et il etait exactement de cette classe-ci.</b> Cette
     * population etait construite par un {@code continue} sur tout paquet qui n'est pas de type
     * {@code question}. Le chargeur de production enregistre <b>quatre</b> types
     * ({@code YamlContentPackLoader.FABRIQUES}) ; ce garde-fou en connaissait un et ecartait les
     * trois autres sans le dire. Le plus grave est le type {@code designation} :
     * {@code ZoneADesigner} refuse une illustration vide dans son constructeur compact, donc c'est
     * <b>la seule mecanique ou l'illustration est obligatoire</b>, et c'est celle que ce garde-fou
     * aurait ecartee en silence le jour ou un paquet de designation entre au catalogue. Un garde-fou
     * de l'illustration muet sur la mecanique de l'illustration obligatoire est un faux vert, et il
     * avait une date d'echeance : le financement d'un lot de contenu de designation.</p>
     *
     * <p><b>Ce que cette methode decide, et ce qu'elle refuse de decider.</b> Elle laisse passer les
     * trois types qu'ADR-0073 a instruits - {@code question}, dont les items peuvent declarer une
     * illustration ; {@code additions-bornees}, dont les items ne sont jamais lus dans un YAML ;
     * {@code saisie}, dont {@code ReponseAEcrire} ne declare aucune composante d'illustration - et
     * elle <b>leve</b> sur tout autre.</p>
     *
     * <p><b>Decide au 2026-09-10 (S33-PAL2-1, s34) : {@code designation} rejoint la liste des types
     * instruits, sans lui ouvrir de branche de jeu.</b> Le premier paquet reel a rendu ce rouge date,
     * et la question qu'il posait avait deja sa reponse : ce fichier entier construit des
     * {@link GameDefinition} de mecanique {@code BanditManchot} pour rejouer la traversee HTTP du
     * champ illustration - il ne peut pas, et n'a pas a, exercer la troisieme mecanique, dont l'ecran
     * de manche et le gabarit sont entierement distincts (voir {@code PartieController.mancheDeDesignation},
     * {@code fragments/designation.html}). Reconnaitre le type ici fait retomber un paquet de
     * designation dans le filtre existant de {@link #estUnPaquetDeQuestions(Map)}, qui l'ecarte
     * (il ne l'ecartait pas en silence avant cette decision : il faisait lever ce garde-fou, faute
     * d'une decision explicite). La garantie que la designation illustre reellement l'ecran qu'elle
     * sert est tenue ailleurs, par le rendu propre a cette mecanique.</p>
     */
    private static void verifierQueLeTypeEstInstruit(String paquet, Map<String, Object> corps) {
        String type = String.valueOf(corps.get("type"));
        switch (type) {
            case "question", "additions-bornees", "saisie", "designation" -> { }
            default -> throw new IllegalStateException(refusDuType(paquet, type));
        }
    }

    private static boolean estUnPaquetDeQuestions(Map<String, Object> corps) {
        return "question".equals(String.valueOf(corps.get("type")));
    }

    private static String refusDuType(String paquet, String type) {
        return "paquet " + paquet + " : type '" + type + "', qu'ADR-0073 n'a pas instruit et que ce"
                + " garde-fou ne sait donc pas juger. Decider ici si ce type peut declarer une"
                + " illustration - pour la designation, ZoneADesigner la rend obligatoire, donc"
                + " oui - plutot que de l'ecarter en silence (ADR-0013 obligation 2, ADR-0088"
                + " clause C)";
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

    /** ADR-0013 obligation 1 : les paquets sont decouverts sur le classpath, jamais nommes. */
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

    /**
     * Le paquet de preuve : deux items, l'un qui montre un dessin et l'autre non - c'est le couple qui
     * met les deux moities de la clause a l'epreuve dans une seule partie.
     */
    private static List<Question> itemsDePreuve() {
        return List.of(
                new Question(new ItemId("item-0"), "Quelle est cette forme ?", "un carre",
                        List.of("un triangle", "un cercle"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("preuve-forme-carree"),
                        Optional.of("Une forme fermee a quatre cotes de meme longueur.")),
                new Question(new ItemId("item-1"), "Combien de cotes a un triangle ?", "trois",
                        List.of("quatre", "deux")));
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
        String ou = "jeu " + jeuId + ", item \"" + item.enonce() + "\"";
        Elements images = ecran.select(".illustration img");
        if (item.illustration().isEmpty()) {
            if (!images.isEmpty()) {
                defauts.add(ou + " : une illustration apparait alors que cet item n'en declare aucune"
                        + " (ADR-0073, seconde moitie)");
            }
            return defauts;
        }
        if (images.size() != 1) {
            defauts.add(ou + " : " + images.size() + " image(s) d'illustration, une seule attendue"
                    + " (ADR-0073 clause 1)");
            return defauts;
        }
        Element image = images.get(0);
        String cibleAttendue = REPERTOIRE_SERVI + item.illustration().orElseThrow() + EXTENSION_SERVIE;
        if (!cibleAttendue.equals(image.attr("src"))) {
            defauts.add(ou + " : l'image sert \"" + image.attr("src") + "\", attendu exactement \""
                    + cibleAttendue + "\". Le contenu declare un identifiant, l'adapter web pose le"
                    + " repertoire et l'extension (ADR-0073 clauses 1 et 3)");
        }
        String alternatifAttendu = item.illustrationTexteAlternatif().orElseThrow();
        if (!alternatifAttendu.equals(image.attr("alt"))) {
            defauts.add(ou + " : le texte alternatif rendu vaut \"" + image.attr("alt")
                    + "\", attendu exactement \"" + alternatifAttendu + "\". Une image qui porte la"
                    + " question ne parvient pas a un lecteur d'ecran sans lui (ADR-0073 clause 5)");
        }
        return defauts;
    }

    private static List<GameDefinition> jeuxIllustres(PartieUseCase partie) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_ILLUSTRE))
                .toList();
    }

    private static List<Question> itemsDe(GameDefinition jeu) {
        String paquet = jeu.id().substring(PREFIXE_JEU_ILLUSTRE.length());
        return PAQUET_DE_PREUVE.equals(paquet) ? itemsDePreuve()
                : itemsDuCorps(lireYaml("/contenu/" + paquet + ".yaml"));
    }

    /** Le nom du paquet reel derriere une entree du catalogue, lu sur l'entree elle-meme. */
    private static String paquetDe(GameDefinition jeu) {
        return entreesDuCatalogueReel().stream()
                .filter(entree -> jeu.id().equals(String.valueOf(entree.get("id"))))
                .map(entree -> String.valueOf(entree.get("paquet")))
                .findFirst()
                .orElseThrow();
    }

    @Test
    void aucunJeuDuCatalogueReelNAfficheDIllustrationSansDeclaration() throws Exception {
        List<GameDefinition> jeuxReels = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_ILLUSTRE))
                .toList();
        assertThat(jeuxReels)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun jeu de"
                        + " type question")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxReels) {
            Document ecranDeManche = ecran(mockMvc, jeu.id(), new MockHttpSession());
            Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeu) + ".yaml")).get(0);
            defauts.addAll(manquements(jeu.id(), premierItem, ecranDeManche));
        }

        assertThat(defauts)
                .as("ADR-0073, seconde moitie : un item qui ne declare aucune illustration n'en voit"
                        + " apparaitre aucune, et le gabarit ne rend alors aucun bloc vide (ADR-0020)")
                .isEmpty();
    }

    /**
     * Extrait pour que la clause de non-vacuite (ADR-0051) soit elle-meme mise a l'epreuve, sur une
     * liste vide, sans passer par Spring ni par MockMvc.
     */
    private static void verifierChaqueItemDesPaquetsIllustres(MockMvc mockMvc, List<GameDefinition> jeux)
            throws Exception {
        assertThat(jeux)
                .as("ADR-0051 : ce garde-fou ne prouve rien s'il ne joue aucun paquet illustre")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            List<Question> items = itemsDe(jeu);
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
                .as("ADR-0073, premiere moitie : chaque item qui declare une illustration la voit"
                        + " atteindre l'ecran, a la cible que son identifiant resout et avec"
                        + " exactement son texte alternatif")
                .isEmpty();
    }

    @Test
    void chaqueItemDUnPaquetIllustreAtteintLEcranAvecSaCibleEtSonTexteAlternatif() throws Exception {
        verifierChaqueItemDesPaquetsIllustres(mockMvc, jeuxIllustres(partie));
    }

    @Test
    void aucunPaquetIllustreFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierChaqueItemDesPaquetsIllustres(mockMvc, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /** Le premier jeu illustre, pour les mises a l'epreuve qui mutent un ecran reellement rendu. */
    private static GameDefinition premierJeuIllustre(PartieUseCase partie) {
        return jeuxIllustres(partie).stream().findFirst().orElseThrow();
    }

    private Document ecranIllustreSain(GameDefinition jeu, Question item) throws Exception {
        Document ecranReel = ecran(mockMvc, jeu.id(), new MockHttpSession());
        assertThat(manquements(jeu.id(), item, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();
        return ecranReel;
    }

    /** Mise a l'epreuve 1 : l'image retiree du document rendu - la premiere moitie doit rougir. */
    @Test
    void uneIllustrationRetireeDuDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = premierJeuIllustre(partie);
        Question premierItem = itemsDe(jeu).get(0);
        Document ecranReel = ecranIllustreSain(jeu, premierItem);

        Document mute = ecranReel.clone();
        mute.select(".illustration").remove();

        assertThat(manquements(jeu.id(), premierItem, mute))
                .as("une illustration declaree qui n'atteint pas l'ecran doit rendre cette clause"
                        + " rouge : l'enfant recoit la question sans ce qu'elle demande de regarder")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 2 : une image posee sur un item qui ne declare rien - l'ecart muet exact que la
     * Javadoc de {@code QuestionContentSourceFactory} nomme, pris par l'autre bout.
     */
    @Test
    void uneIllustrationPoseeSurUnItemQuiNeDeclareRienFaitEchouerLaClause() throws Exception {
        GameDefinition jeuReel = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_ILLUSTRE))
                .findFirst().orElseThrow();
        Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeuReel) + ".yaml")).get(0);
        Document ecranReel = ecran(mockMvc, jeuReel.id(), new MockHttpSession());
        assertThat(manquements(jeuReel.id(), premierItem, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document mute = ecranReel.clone();
        mute.selectFirst(".consigne").after("<div class=\"illustration\">"
                + "<img class=\"image-item\" src=\"/illustrations/intruse.svg\" alt=\"une intruse\">"
                + "</div>");

        assertThat(manquements(jeuReel.id(), premierItem, mute))
                .as("une image posee sur un item qui n'en declare aucune doit rendre cette clause"
                        + " rouge")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve 3 : la cible ne correspond plus a l'identifiant declare. Une assertion de
     * simple presence resterait verte ; l'egalite exacte ne l'est pas - et c'est elle qui prouve que
     * le repertoire et l'extension sont poses par l'adapter, sur l'identifiant de cet item-la.
     */
    @Test
    void uneCibleQuiNeResoutPlusLIdentifiantDeclareFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = premierJeuIllustre(partie);
        Question premierItem = itemsDe(jeu).get(0);
        Document ecranReel = ecranIllustreSain(jeu, premierItem);
        String identifiant = premierItem.illustration().orElseThrow();

        for (String cibleFautive : List.of("/illustrations/un-autre-dessin.svg",
                "/images/" + identifiant + EXTENSION_SERVIE, REPERTOIRE_SERVI + identifiant,
                identifiant + EXTENSION_SERVIE)) {
            Document mute = ecranReel.clone();
            mute.selectFirst(".illustration img").attr("src", cibleFautive);
            assertThat(manquements(jeu.id(), premierItem, mute))
                    .as("la cible %s ne resout pas l'identifiant declare et doit rendre cette clause"
                            + " rouge", cibleFautive)
                    .isNotEmpty();
        }
    }

    /** Mise a l'epreuve 4 : le texte alternatif altere, puis vide - la clause 5 dans les deux cas. */
    @Test
    void unTexteAlternatifAltereOuVideFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = premierJeuIllustre(partie);
        Question premierItem = itemsDe(jeu).get(0);
        Document ecranReel = ecranIllustreSain(jeu, premierItem);

        for (String alternatifFautif : List.of("", "une image")) {
            Document mute = ecranReel.clone();
            mute.selectFirst(".illustration img").attr("alt", alternatifFautif);
            assertThat(manquements(jeu.id(), premierItem, mute))
                    .as("un texte alternatif qui n'est plus celui de l'item (\"%s\") doit rendre cette"
                            + " clause rouge : un alt vide annonce une image decorative, et une image"
                            + " de contenu n'en est pas une", alternatifFautif)
                    .isNotEmpty();
        }
    }

    /** Mise a l'epreuve 5 : deux images pour un seul item - une seule est attendue. */
    @Test
    void deuxIllustrationsPourUnMemeItemFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = premierJeuIllustre(partie);
        Question premierItem = itemsDe(jeu).get(0);
        Document ecranReel = ecranIllustreSain(jeu, premierItem);

        Document mute = ecranReel.clone();
        Element bloc = mute.selectFirst(".illustration");
        bloc.after(bloc.clone());

        assertThat(manquements(jeu.id(), premierItem, mute))
                .as("deux images pour le meme item doivent rendre cette clause rouge")
                .isNotEmpty();
    }

    /**
     * ADR-0073 et la proposition d'ines-design, section 6.1 : <b>le dessin reste a l'ecran apres un
     * echec et jusqu'a la revelation.</b> Ce n'est pas une preference de mise en page - <i>"un enfant
     * qui lit c'etait un carre sans voir la figure qu'on lui a montree n'apprend rien"</i> -, et c'est
     * une propriete du rendu, donc verifiable ici. Le retour juste/faux, lui, ne change pas d'une
     * ligne : il appartient a la mecanique, pas au contenu.
     */
    @Test
    void leDessinResteALEcranApresUnEchecEtJusquALaRevelation() throws Exception {
        GameDefinition jeu = premierJeuIllustre(partie);
        Question premierItem = itemsDe(jeu).get(0);
        MockHttpSession session = new MockHttpSession();
        Document ecranCourant = ecran(mockMvc, jeu.id(), session);
        assertThat(manquements(jeu.id(), premierItem, ecranCourant))
                .as("l'ecran de manche fraiche doit etre sain")
                .isEmpty();

        String mauvaiseReponse = premierItem.distracteurs().get(0);
        for (int echec = 1; echec <= 3; echec++) {
            ecranCourant = coup(mockMvc, jeu.id(), session, mauvaiseReponse);
            assertThat(manquements(jeu.id(), premierItem, ecranCourant))
                    .as("apres %d echec(s) sur la meme manche, le dessin est toujours la, a la meme"
                            + " cible et avec le meme texte alternatif", echec)
                    .isEmpty();
        }

        assertThat(ecranCourant.select(".message-jeu").text())
                .as("M5 : le troisieme echec ouvre la revelation, et c'est l'etat ou le dessin est le"
                        + " plus necessaire")
                .contains(premierItem.bonneReponse());
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
                jeux.add(new GameDefinition(String.valueOf(entree.get("id")),
                        String.valueOf(entree.get("titre")), String.valueOf(entree.get("consigne")),
                        BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, itemsDuCorps(corps)), 1));
            }

            for (String paquet : paquetsDuRepertoireDeContenu()) {
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                verifierQueLeTypeEstInstruit(paquet, corps);
                if (!estUnPaquetDeQuestions(corps)) {
                    continue;
                }
                List<Question> items = itemsDuCorps(corps);
                if (items.stream().noneMatch(question -> question.illustration().isPresent())) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(PREFIXE_JEU_ILLUSTRE + paquet, "Illustre " + paquet,
                        "Consigne de preuve", BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, items), items.size()));
            }

            ContentDescriptor descripteurDePreuve = new ContentDescriptor("Mathematiques", "CP",
                    "Espace et geometrie", "paquet de preuve d'ADR-0073, jamais servi a un enfant");
            jeux.add(new GameDefinition(PREFIXE_JEU_ILLUSTRE + PAQUET_DE_PREUVE, "Illustre de preuve",
                    "Regarde bien le dessin", BanditManchot.ID, descripteurDePreuve,
                    new ListContentSource(descripteurDePreuve, itemsDePreuve()), itemsDePreuve().size()));

            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
