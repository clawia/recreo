package com.recreo.bootstrap.rendu;

import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.saisie.Saisie;
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
 * <b>ADR-0075, clause 4 - la traversee : le texte que l'adulte lit a voix haute et la mise en garde
 * de son jeu atteignent le document assemble, pour chaque item de chaque paquet reel qui les
 * declare.</b>
 *
 * <p><b>Pourquoi cette clause n'a pas ete ecrite avant ce commit.</b> ADR-0075 l'a posee des le
 * champ : un champ ecrit dans un paquet et jamais rendu serait vert partout ailleurs, y compris sur
 * ADR-0070 qui ne garde que la propriete faible ("personne n'ignore cette cle"). Tant qu'aucun
 * paquet ne declarait {@code texteAVoixHaute}, une traversee ecrite ici aurait juge un ensemble
 * vide - le mode de defaillance qu'ADR-0051 refuse. Le contenu existe depuis {@code s26-contenu}
 * ({@code ab574e2}) et le cablage depuis un commit anterieur de ce lot : les deux prealables sont
 * tenus, et ADR-0013 obligation 1 exige alors de deriver du materiel reellement deploye.</p>
 *
 * <p><b>Pourquoi un jeu de preuve et non l'entree du catalogue reel, sur le patron exact de
 * {@code LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest}.</b> ADR-0023 exige d'une entree de
 * catalogue que {@code nombreDeManches} soit strictement inferieur au nombre d'items du paquet - une
 * partie reelle n'epuise donc jamais son paquet, et rejouer une entree reelle ne peut pas prouver
 * que le <b>dernier</b> item atteint l'ecran. Le jeu de preuve porte {@code nombreDeManches = }
 * nombre d'items : c'est le seul point qui n'est pas celui du catalogue reel. Le paquet, lui, est
 * charge par lecture directe du <b>meme fichier reel</b> - {@code classpath*:contenu/*.yaml}, la
 * source que {@code adapter-content} balaie elle-meme - et la mise en garde du jeu de preuve est
 * copiee de l'entree reelle du catalogue qui sert ce paquet, jamais inventee.</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - les paquets marques sont decouverts
 * en balayant le repertoire de contenu reel et en retenant ceux dont au moins un item declare
 * {@code texteAVoixHaute}, jamais nommes ici ; <b>derivation totale</b> - chaque item du paquet
 * marque est joue, dans l'ordre du fichier, jusqu'a epuisement exact du jeu de preuve ;
 * <b>mise a l'epreuve</b> - le document reellement rendu est mute (bloc retire, stimulus altere),
 * jamais une fixture ecrite a la main.
 */
@WebMvcTest(PartieController.class)
@Import(LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest.JeuxDePreuveConfiguration.class)
class LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest {

    private static final String PREFIXE_JEU_DE_PREUVE = "preuve-voix-";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest.class
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

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> entreesDuCatalogueReel() {
        Map<String, Object> corps = lireYaml("/catalogue.yaml");
        return (List<Map<String, Object>>) corps.get("jeux");
    }

    /** ADR-0013 obligation 1 : les paquets sont decouverts, jamais nommes - meme balayage que les
     * garde-fous de contenu (classpath*:contenu/*.yaml). */
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
     * <b>ADR-0013 obligation 2, et ADR-0088 clause C : un type de paquet que ce garde-fou ne sait pas
     * juger est un echec, jamais un silence.</b>
     *
     * <p><b>Le defaut que ce geste paie.</b> Cette population etait construite par un
     * {@code continue} sur tout paquet qui n'est ni de type {@code question} ni de type
     * {@code saisie}. Le chargeur de production enregistre <b>quatre</b> types
     * ({@code YamlContentPackLoader.FABRIQUES}) ; ce garde-fou en connaissait deux et ecartait les
     * deux autres sans le dire - dont {@code designation}, sur lequel {@code ZoneADesigner} declare
     * bien une composante {@code texteAVoixHaute} : un paquet de designation entre reellement dans
     * ce perimetre, et il en aurait ete ecarte sans qu'aucun diff ne le montre.</p>
     *
     * <p><b>Ce que ce gate decide, et ce qu'il refuse de decider.</b> Il laisse passer les trois
     * types deja instruits par ce fichier et <b>leve</b> sur tout autre. Il ne pose <b>aucune</b>
     * branche pour {@code designation} : une exemption ecrite d'avance pourrirait le jour ou le
     * contrat de son item changerait, alors qu'un rouge pose la question avec le record sous les
     * yeux. Le jour ou T6 sert le premier paquet de designation, ce garde-fou est rouge et sa
     * branche s'ecrit la. Le tri de perimetre reste ou il etait : ce gate ne juge que le fait
     * d'avoir ete instruit.</p>
     */
    private static void verifierQueLeTypeEstInstruit(String paquet, Map<String, Object> corps) {
        String type = String.valueOf(corps.get("type"));
        switch (type) {
            case "question", "additions-bornees", "saisie" -> { }
            default -> throw new IllegalStateException(refusDuType(paquet, type));
        }
    }

    private static String refusDuType(String paquet, String type) {
        return "paquet " + paquet + " : type '" + type + "', que ce garde-fou n'a pas instruit et"
                + " qu'il ne sait donc pas juger. ZoneADesigner declare une composante"
                + " texteAVoixHaute : un paquet de designation entre dans ce perimetre. Decider ici"
                + " plutot que d'ecarter ce paquet en silence (ADR-0013 obligation 2, ADR-0088"
                + " clause C)";
    }

    private static boolean estUnPaquetDeQuestions(Map<String, Object> corps) {
        return "question".equals(String.valueOf(corps.get("type")));
    }

    private static boolean estUnPaquetDeSaisie(Map<String, Object> corps) {
        return "saisie".equals(String.valueOf(corps.get("type")));
    }

    @SuppressWarnings("unchecked")
    private static ContentDescriptor descripteurDe(Map<String, Object> corps) {
        Map<String, Object> descripteur = (Map<String, Object>) corps.get("descripteur");
        return new ContentDescriptor(String.valueOf(descripteur.get("matiere")),
                String.valueOf(descripteur.get("niveau")), String.valueOf(descripteur.get("domaine")),
                String.valueOf(descripteur.get("competence")));
    }

    private static Optional<String> texteOptionnel(Map<?, ?> item, String champ) {
        Object valeur = item.get(champ);
        return valeur == null ? Optional.empty() : Optional.of(String.valueOf(valeur));
    }

    /** Conversion identique a QuestionContentSourceFactory, sur les seules cles que ce test exerce. */
    @SuppressWarnings("unchecked")
    private static List<Question> questionsDuCorps(Map<String, Object> corps) {
        List<Map<String, Object>> items = (List<Map<String, Object>>) corps.get("items");
        List<Question> questions = new ArrayList<>();
        int position = 0;
        for (Map<String, Object> item : items) {
            List<String> distracteurs = ((List<Object>) item.get("distracteurs")).stream()
                    .map(String::valueOf).toList();
            questions.add(new Question(new ItemId("item-" + position), String.valueOf(item.get("enonce")),
                    String.valueOf(item.get("bonneReponse")), distracteurs,
                    texteOptionnel(item, "indice"), texteOptionnel(item, "explication"),
                    texteOptionnel(item, "partDesignee"), texteOptionnel(item, "texteSupport"),
                    texteOptionnel(item, "illustration"), texteOptionnel(item, "illustrationTexteAlternatif"),
                    texteOptionnel(item, "texteAVoixHaute")));
            position++;
        }
        return questions;
    }

    /** Conversion identique a SaisieContentSourceFactory, sur les seules cles que ce test exerce. */
    @SuppressWarnings("unchecked")
    private static List<ReponseAEcrire> reponsesDuCorps(Map<String, Object> corps) {
        List<Map<String, Object>> items = (List<Map<String, Object>>) corps.get("items");
        List<ReponseAEcrire> reponses = new ArrayList<>();
        int position = 0;
        for (Map<String, Object> item : items) {
            List<String> formesAcceptees = ((List<Object>) item.get("formesAcceptees")).stream()
                    .map(String::valueOf).toList();
            reponses.add(new ReponseAEcrire(new ItemId("item-" + position),
                    String.valueOf(item.get("enonce")), String.valueOf(item.get("formeDeReference")),
                    formesAcceptees, texteOptionnel(item, "indice"), texteOptionnel(item, "explication"),
                    ExigenceDeForme.FORME_LIBRE, texteOptionnel(item, "texteAVoixHaute")));
            position++;
        }
        return reponses;
    }

    /** La mise en garde de l'entree reelle de catalogue qui sert ce paquet - jamais inventee. */
    private static Optional<String> consigneAdulteReelleDuPaquet(String paquet) {
        return entreesDuCatalogueReel().stream()
                .filter(entree -> paquet.equals(String.valueOf(entree.get("paquet"))))
                .map(entree -> entree.get("consigneAdulte"))
                .filter(java.util.Objects::nonNull)
                .map(String::valueOf)
                .findFirst();
    }

    private Document ecran(String jeuId, MockHttpSession session) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    private Document coupDeChoix(String jeuId, MockHttpSession session, String bonneReponse) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                        .param("propositionChoisie", bonneReponse))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    private Document coupDeSaisie(String jeuId, MockHttpSession session, String formeDeReference) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}/reponses", jeuId).session(session)
                        .param("reponseEcrite", formeDeReference))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    /** Ce qui manque a un ecran de manche pour porter le bloc-adulte d'un item et de son jeu. */
    private static List<String> manquements(String jeuId, String texteAttendu,
                                             String consigneAdulteAttendue, Document ecran) {
        List<String> defauts = new ArrayList<>();
        Elements blocs = ecran.select(".bloc-adulte");
        if (blocs.size() != 1) {
            defauts.add("jeu " + jeuId + " : " + blocs.size() + " element(s) .bloc-adulte, un seul"
                    + " attendu puisque l'item declare texteAVoixHaute");
            return defauts;
        }
        Element bloc = blocs.first();
        String stimulusRendu = bloc.select(".stimulus-a-lire").text().strip();
        if (!texteAttendu.strip().equals(stimulusRendu)) {
            defauts.add("jeu " + jeuId + " : le stimulus rendu vaut \"" + stimulusRendu
                    + "\", attendu exactement \"" + texteAttendu.strip() + "\"");
        }
        String consigneRendue = bloc.select(".consigne-adulte").text().strip();
        if (!consigneAdulteAttendue.strip().equals(consigneRendue)) {
            defauts.add("jeu " + jeuId + " : la mise en garde rendue vaut \"" + consigneRendue
                    + "\", attendue exactement \"" + consigneAdulteAttendue.strip() + "\"");
        }
        return defauts;
    }

    private static List<GameDefinition> jeuxDePreuve(PartieUseCase partie) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList();
    }

    private List<String> verifierChaquePaquetAVoix(List<GameDefinition> jeuxAVoix) throws Exception {
        assertThat(jeuxAVoix)
                .as("ADR-0051 : ce garde-fou ne prouve rien si aucun paquet du repertoire de contenu"
                        + " reel ne declare texteAVoixHaute sur au moins un item")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxAVoix) {
            String paquet = jeu.id().substring(PREFIXE_JEU_DE_PREUVE.length());
            Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
            String consigneAdulte = jeu.consigneAdulte().orElseThrow();
            boolean deChoix = estUnPaquetDeQuestions(corps);

            List<Object> textesAVoixHaute = new ArrayList<>();
            List<Object> reponsesAttendues = new ArrayList<>();
            if (deChoix) {
                for (Question question : questionsDuCorps(corps)) {
                    textesAVoixHaute.add(question.texteAVoixHaute().orElse(null));
                    reponsesAttendues.add(question.bonneReponse());
                }
            } else {
                for (ReponseAEcrire reponse : reponsesDuCorps(corps)) {
                    textesAVoixHaute.add(reponse.texteAVoixHaute().orElse(null));
                    reponsesAttendues.add(reponse.formeDeReference());
                }
            }

            MockHttpSession session = new MockHttpSession();
            Document ecranCourant = ecran(jeu.id(), session);
            for (int rang = 0; rang < textesAVoixHaute.size(); rang++) {
                Object voixBrute = textesAVoixHaute.get(rang);
                if (voixBrute != null) {
                    defauts.addAll(manquements(jeu.id(), String.valueOf(voixBrute), consigneAdulte,
                            ecranCourant));
                }
                boolean dernierItem = rang == textesAVoixHaute.size() - 1;
                if (!dernierItem) {
                    String reponse = String.valueOf(reponsesAttendues.get(rang));
                    ecranCourant = deChoix
                            ? coupDeChoix(jeu.id(), session, reponse)
                            : coupDeSaisie(jeu.id(), session, reponse);
                }
            }
        }
        return defauts;
    }

    @Test
    void chaqueItemDUnPaquetAVoixAtteintLEcranAvecSonStimulusEtLaMiseEnGardeDeSonJeu() throws Exception {
        assertThat(verifierChaquePaquetAVoix(jeuxDePreuve(partie)))
                .as("ADR-0075 clause 4 : le texte a voix haute d'un item et la mise en garde de son"
                        + " jeu atteignent le document assemble - un champ ecrit dans le paquet et"
                        + " jamais rendu serait vert partout ailleurs (ADR-0070), et c'est exactement"
                        + " ce que cette traversee refuse")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve de non-vacuite : la forme qu'aurait ce garde-fou si
     * aucun paquet marque n'existait dans le repertoire de contenu.
     */
    @Test
    void aucunPaquetAVoixFaitEchouerLaClauseDeNonVacuite() {
        assertThat(org.assertj.core.api.Assertions.catchThrowable(
                        () -> verifierChaquePaquetAVoix(List.of())))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * Regression : un jeu du catalogue reel qui ne declare aucune voix ne rend aucun
     * {@code .bloc-adulte}. Un seul tirage suffit - la propriete jugee ne depend pas de l'item
     * precis, elle depend de l'absence de declaration sur le jeu entier.
     */
    @Test
    void aucunJeuSansVoixNAfficheDeBlocAdulte() throws Exception {
        List<GameDefinition> jeuxSansVoix = partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.consigneAdulte().isEmpty())
                .toList();
        assertThat(jeuxSansVoix)
                .as("ADR-0003 : ce garde-fou ne prouve rien si tous les jeux du catalogue - reels et"
                        + " de preuve - declarent une voix")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxSansVoix) {
            Document ecranDuJeu = ecran(jeu.id(), new MockHttpSession());
            if (!ecranDuJeu.select(".bloc-adulte").isEmpty()) {
                defauts.add("jeu " + jeu.id() + " : un .bloc-adulte apparait alors que cette entree"
                        + " de catalogue ne declare aucune consigneAdulte");
            }
        }
        assertThat(defauts).isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : la morsure sur la sortie reelle du serveur, mutee - un bloc-adulte
     * retire du document doit rendre cette clause rouge.
     */
    @Test
    void unBlocAdulteRetireDuDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition premierJeuAVoix = jeuxDePreuve(partie).get(0);
        String paquet = premierJeuAVoix.id().substring(PREFIXE_JEU_DE_PREUVE.length());
        Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
        String texteAttendu = estUnPaquetDeQuestions(corps)
                ? questionsDuCorps(corps).get(0).texteAVoixHaute().orElseThrow()
                : reponsesDuCorps(corps).get(0).texteAVoixHaute().orElseThrow();
        String consigneAdulte = premierJeuAVoix.consigneAdulte().orElseThrow();

        Document ecranReel = ecran(premierJeuAVoix.id(), new MockHttpSession());
        assertThat(manquements(premierJeuAVoix.id(), texteAttendu, consigneAdulte, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document sansBloc = ecranReel.clone();
        sansBloc.select(".bloc-adulte").remove();
        assertThat(manquements(premierJeuAVoix.id(), texteAttendu, consigneAdulte, sansBloc))
                .as("un bloc-adulte retire du document rendu doit rendre cette clause rouge")
                .isNotEmpty();
    }

    /** Meme obligation, autre mutation : un stimulus altere doit rendre la clause rouge. */
    @Test
    void unStimulusAltereDansLeDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition premierJeuAVoix = jeuxDePreuve(partie).get(0);
        String paquet = premierJeuAVoix.id().substring(PREFIXE_JEU_DE_PREUVE.length());
        Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
        String texteAttendu = estUnPaquetDeQuestions(corps)
                ? questionsDuCorps(corps).get(0).texteAVoixHaute().orElseThrow()
                : reponsesDuCorps(corps).get(0).texteAVoixHaute().orElseThrow();
        String consigneAdulte = premierJeuAVoix.consigneAdulte().orElseThrow();
        Document ecranReel = ecran(premierJeuAVoix.id(), new MockHttpSession());

        Document stimulusAltere = ecranReel.clone();
        Element stimulus = stimulusAltere.selectFirst(".stimulus-a-lire");
        stimulus.text(stimulus.text() + " altere");
        assertThat(manquements(premierJeuAVoix.id(), texteAttendu, consigneAdulte, stimulusAltere))
                .as("un stimulus qui ne correspond plus exactement a l'item declare doit rendre cette"
                        + " clause rouge")
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3, et la morsure porte sur du <b>materiau reel</b> : le corps d'un paquet
     * servi du depot, decouvert et non nomme, dont la seule mutation est la valeur de sa cle
     * {@code type}. C'est l'etat exact qu'aura la population le jour ou T6 servira le premier paquet
     * de designation.
     */
    @Test
    void unPaquetDUnTypeQueCeGardeFouNeSaitPasJugerLeFaitEchouer() {
        String paquet = paquetsDuRepertoireDeContenu().stream().sorted().findFirst().orElseThrow();
        Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
        corps.put("type", "designation");

        assertThatThrownBy(() -> verifierQueLeTypeEstInstruit(paquet, corps))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("type 'designation'")
                .hasMessageContaining("ZoneADesigner declare une composante texteAVoixHaute");
    }

    @TestConfiguration
    static class JeuxDePreuveConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            List<GameDefinition> jeux = new ArrayList<>();

            for (Map<String, Object> entree : entreesDuCatalogueReel()) {
                String paquet = String.valueOf(entree.get("paquet"));
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                verifierQueLeTypeEstInstruit(paquet, corps);
                ContentDescriptor descripteur = descripteurDe(corps);
                ContentSource contenu;
                MechanicId mecanique;
                if (estUnPaquetDeQuestions(corps)) {
                    contenu = new ListContentSource(descripteur, questionsDuCorps(corps));
                    mecanique = BanditManchot.ID;
                } else if (estUnPaquetDeSaisie(corps)) {
                    contenu = new ListContentSource(descripteur, reponsesDuCorps(corps), false);
                    mecanique = Saisie.ID;
                } else {
                    continue;
                }
                Object consigneAdulteBrute = entree.get("consigneAdulte");
                jeux.add(new GameDefinition(String.valueOf(entree.get("id")),
                        String.valueOf(entree.get("titre")), String.valueOf(entree.get("consigne")),
                        mecanique, descripteur, contenu,
                        Integer.parseInt(String.valueOf(entree.get("nombreDeManches"))),
                        consigneAdulteBrute == null
                                ? Optional.empty() : Optional.of(String.valueOf(consigneAdulteBrute))));
            }

            for (String paquet : paquetsDuRepertoireDeContenu()) {
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                verifierQueLeTypeEstInstruit(paquet, corps);
                boolean deChoix = estUnPaquetDeQuestions(corps);
                boolean deSaisie = estUnPaquetDeSaisie(corps);
                if (!deChoix && !deSaisie) {
                    continue;
                }
                boolean declareUneVoix = deChoix
                        ? questionsDuCorps(corps).stream().anyMatch(q -> q.texteAVoixHaute().isPresent())
                        : reponsesDuCorps(corps).stream().anyMatch(r -> r.texteAVoixHaute().isPresent());
                if (!declareUneVoix) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                String consigneAdulte = consigneAdulteReelleDuPaquet(paquet)
                        .orElseThrow(() -> new AssertionError(paquet + " declare texteAVoixHaute sur au"
                                + " moins un item, mais aucune entree de catalogue.yaml qui le sert ne"
                                + " declare consigneAdulte"));
                if (deChoix) {
                    List<Question> items = questionsDuCorps(corps);
                    jeux.add(new GameDefinition(PREFIXE_JEU_DE_PREUVE + paquet, "Preuve " + paquet,
                            "Consigne de preuve", BanditManchot.ID, descripteur,
                            new ListContentSource(descripteur, items), items.size(),
                            Optional.of(consigneAdulte)));
                } else {
                    List<ReponseAEcrire> items = reponsesDuCorps(corps);
                    jeux.add(new GameDefinition(PREFIXE_JEU_DE_PREUVE + paquet, "Preuve " + paquet,
                            "Consigne de preuve", Saisie.ID, descripteur,
                            new ListContentSource(descripteur, items, false), items.size(),
                            Optional.of(consigneAdulte)));
                }
            }

            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> Saisie.ID.equals(id) ? new Saisie() : new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
