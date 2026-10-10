package com.recreo.bootstrap.rendu;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;
import org.thymeleaf.templateresource.StringTemplateResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0104, decision 7, garde-fou 4 : pour tout item servi a propositions-images, aucun ecran
 * rendu - manche fraiche, echec, revelation, fin de partie - ne porte {@code oeuvre-} dans un noeud
 * de texte, un {@code alt}, un {@code aria-label} ou un {@code title}, aucun {@code .reponse-attendue}
 * n'est rendu, et chaque bouton de proposition a un nom accessible non vide.</b> C'est la clause 1 de
 * la decision 4 : un identifiant est de l'ASCII sans accent, et il nomme le sujet, donc la reponse
 * (CON-5).
 *
 * <p><b>Ce que ce fichier juge de plus, parce que les criteres 1 et 2 de S55-ART-CP-10-MECANIQUE le
 * demandent</b> : une balise {@code img} par proposition, dont la source est exactement
 * {@code /oeuvres/<id>.jpg}, et aucun texte dans un bouton de proposition ; l'{@code alt} de ces
 * images est vide (nadia-prof, Q-Y14 : tout {@code alt} qui decrit une oeuvre la designe, le nom du
 * bouton etant le seul texte de la proposition) ; la bonne oeuvre est marquee a la revelation et a
 * elle seule ; le gabarit ne lit jamais le prefixe d'une proposition lui-meme (decision 3).
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1).</b> Les jeux du catalogue reel dont un item a
 * toutes ses propositions prefixees {@code oeuvre-}, joues item par item. <b>Cette population est
 * vide a s55</b> (ADR-0051) : le jeu de preuve est donc fait d'items <b>reels</b>, lus par le chargeur
 * de production, dont seules les propositions et la reference sont mutees en memoire. Le vrai
 * controleur, le vrai gabarit et la vraie traversee HTTP servent ; seul le paquet est synthetique, et
 * il disparait de lui-meme du perimetre des qu'un paquet reel y entre.
 *
 * <p><b>Mises a l'epreuve (obligation 3) sur le gabarit reel mute en memoire</b> : un resolveur de
 * gabarit sert {@code fragments/manche.html} lu sur le classpath, mute ou non, et chaque mutation
 * est jouee par le vrai controleur.
 *
 * <p><b>Critere 6 de S56-ART-CP-10-ECHEC (N5)</b> : sur une manche a trois propositions, apres deux
 * erreurs puis a la revelation, les deux boutons essayes portent la classe {@code deja-essayee}, la
 * bonne porte {@code data-oeuvre-revelee="true"} et pas {@code deja-essayee}. Se juge sur la page
 * rendue, ici ; la regle de la marque elle-meme est lue dans
 * {@code LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest}. Ce que ce fichier ne juge pas : la mise
 * en page, la marque a l'oeil, que la propriete rapprochee se voie a 176 px CSS, et les mots (nadia-prof, a l'ecran).
 */
@WebMvcTest(PartieController.class)
@Import({LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.JeuxDeTestConfiguration.class,
        LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.GabaritMutableConfiguration.class})
class LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest {

    private static final String PREFIXE_DE_REPRODUCTION = "oeuvre-";

    /** Le jeu de preuve, seul objet synthetique de ce fichier (voir la Javadoc de classe). */
    private static final String JEU_DE_PREUVE = "preuve-propositions-en-image";

    /** M5 de nadia-prof (ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md, 3.2), mot pour mot. */
    private static final String NOM_ACCESSIBLE = "Choisir cette œuvre";

    /** M4 du meme arbitrage, mot pour mot : la phrase de revelation d'une manche a images. */
    private static final String PHRASE_DE_REVELATION = "C'est cette œuvre qui va avec. Appuie dessus.";

    private static final String GABARIT = "/templates/fragments/manche.html";

    private static final String IMAGE_DE_PROPOSITION =
            "<img class=\"image-item\" alt=\"\" th:if=\"${propositionsEnImage}\""
                    + " th:src=\"@{'/oeuvres/' + ${proposition} + '.jpg'}\">";

    /** Le gabarit servi par le resolveur ci-dessous : le reel, ou sa mutation en memoire. */
    private static final AtomicReference<String> GABARIT_SERVI = new AtomicReference<>();

    private enum Etat { FRAICHE, ECHEC, REVELATION, FIN }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    @AfterEach
    void rendreLeGabaritReel() {
        GABARIT_SERVI.set(gabaritReel());
    }

    static String gabaritReel() {
        try (InputStream flux = LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.class
                .getResourceAsStream(GABARIT)) {
            assertThat(flux).as("%s introuvable sur le classpath", GABARIT).isNotNull();
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    // ------------------------------------------------------------------------------------------
    // Le materiau : des items reels, mutes en memoire
    // ------------------------------------------------------------------------------------------

    /**
     * ADR-0064 : un paquet enumere est lu jusqu'a ce que sa source soit epuisee (le tirage vide), jamais
     * sur une borne ecrite sous sa taille. Le plafond ne sert qu'aux sources combinatoires, qui ne
     * s'epuisent jamais et dont aucun item ne porte de proposition-image. Calcule une fois par jeu.
     */
    private static final int PLAFOND_POUR_UNE_SOURCE_COMBINATOIRE = 1000;

    private static final Map<String, List<Question>> QUESTIONS_PAR_JEU = new ConcurrentHashMap<>();

    private static List<Question> questionsDe(GameDefinition jeu) {
        return QUESTIONS_PAR_JEU.computeIfAbsent(jeu.id() + "/" + System.identityHashCode(jeu.contenu()),
                cle -> {
                    RandomSource hasard = borne -> 0;
                    Set<ItemId> dejaVus = new HashSet<>();
                    List<Question> questions = new ArrayList<>();
                    for (int tirage = 0; tirage < PLAFOND_POUR_UNE_SOURCE_COMBINATOIRE; tirage++) {
                        Optional<Item> item = jeu.contenu().next(hasard, dejaVus);
                        if (item.isEmpty()) {
                            break;
                        }
                        dejaVus.add(item.get().id());
                        if (item.get() instanceof Question question) {
                            questions.add(question);
                        }
                    }
                    return questions;
                });
    }

    /** Vrai pour un item dont toutes les propositions sont des reproductions (ADR-0104 decision 3). */
    static boolean estEnImage(Question question) {
        return java.util.stream.Stream.concat(java.util.stream.Stream.of(question.bonneReponse()),
                question.distracteurs().stream()).allMatch(
                proposition -> proposition.startsWith(PREFIXE_DE_REPRODUCTION));
    }

    /** Les identifiants de reproduction que des items reels declarent en illustration, sans doublon. */
    private static List<String> reproductionsDeLItemsReels(List<GameDefinition> jeux) {
        Set<String> identifiants = new LinkedHashSet<>();
        for (GameDefinition jeu : jeux) {
            for (Question question : questionsDe(jeu)) {
                question.illustration().filter(id -> id.startsWith(PREFIXE_DE_REPRODUCTION))
                        .ifPresent(identifiants::add);
            }
        }
        return new ArrayList<>(identifiants);
    }

    /**
     * Trois items de preuve : chacun est un item reel (enonce, indice, explication, texte alternatif
     * conserves) dont la reference et les trois propositions sont mutees en reproductions servies,
     * la reference n'etant jamais une proposition.
     */
    private static List<Question> itemsDePreuve(List<GameDefinition> jeuxReels) {
        List<String> reproductions = reproductionsDeLItemsReels(jeuxReels);
        assertThat(reproductions.size())
                .as("ADR-0051 : il faut au moins quatre reproductions reelles pour muter trois items")
                .isGreaterThanOrEqualTo(4);
        Question reel = jeuxReels.stream().flatMap(jeu -> questionsDe(jeu).stream())
                .filter(question -> question.illustration().isPresent()).findFirst().orElseThrow();
        List<Question> items = new ArrayList<>();
        for (int rang = 0; rang < 3; rang++) {
            String reference = reproductions.get(rang);
            String bonne = reproductions.get((rang + 1) % reproductions.size());
            List<String> distracteurs = List.of(reproductions.get((rang + 2) % reproductions.size()),
                    reproductions.get((rang + 3) % reproductions.size()));
            items.add(new Question(new ItemId("preuve-" + rang), reel.enonce(), bonne, distracteurs,
                    reel.indice(), reel.explication(), reel.partDesignee(), reel.texteSupport(),
                    Optional.of(reference), reel.illustrationTexteAlternatif(), reel.texteAVoixHaute(),
                    reel.tableauSupport()));
        }
        return items;
    }

    // ------------------------------------------------------------------------------------------
    // Le juge
    // ------------------------------------------------------------------------------------------

    /** Les defauts d'un ecran rendu pour un item a propositions-images. Fonction pure sur le document. */
    static List<String> defautsDe(String jeuId, Question item, Etat etat, Document ecran) {
        List<String> defauts = new ArrayList<>();
        String ou = "jeu " + jeuId + ", item " + item.id().value() + ", ecran " + etat;

        if (ecran.body().text().contains(PREFIXE_DE_REPRODUCTION)) {
            defauts.add(ou + " : " + PREFIXE_DE_REPRODUCTION + " ecrit dans un noeud de texte (ADR-0104"
                    + " decision 4, clause 1) : " + ecran.body().text());
        }
        for (Element element : ecran.getAllElements()) {
            for (Attribute attribut : element.attributes()) {
                boolean porteurDeTexte = Set.of("alt", "aria-label", "title").contains(attribut.getKey());
                if (porteurDeTexte && attribut.getValue().contains(PREFIXE_DE_REPRODUCTION)) {
                    defauts.add(ou + " : " + PREFIXE_DE_REPRODUCTION + " ecrit dans l'attribut "
                            + attribut.getKey() + " de <" + element.tagName() + "> (clause 1)");
                }
            }
        }
        if (!ecran.select(".reponse-attendue").isEmpty()) {
            defauts.add(ou + " : un element .reponse-attendue est rendu alors que la reponse est une"
                    + " reproduction (decision 4, points 4 et 5)");
        }
        if (etat == Etat.FIN) {
            return defauts;
        }

        List<Element> boutons = ecran.select("form.propositions button.proposition");
        Set<String> attendues = new LinkedHashSet<>();
        attendues.add(item.bonneReponse());
        attendues.addAll(item.distracteurs());
        if (boutons.size() != attendues.size()) {
            defauts.add(ou + " : " + boutons.size() + " bouton(s) de proposition, " + attendues.size()
                    + " attendu(s)");
        }
        Set<String> sources = new LinkedHashSet<>();
        for (Element bouton : boutons) {
            List<Element> images = bouton.select("img");
            if (images.size() != 1) {
                defauts.add(ou + " : un bouton de proposition porte " + images.size()
                        + " image(s), une seule attendue (critere 1)");
                continue;
            }
            sources.add(images.get(0).attr("src"));
            if (!bouton.text().isEmpty()) {
                defauts.add(ou + " : un bouton de proposition porte du texte \"" + bouton.text()
                        + "\" : sa proposition est une image, pas un texte (critere 1)");
            }
            if (bouton.attr("aria-label").isBlank()) {
                defauts.add(ou + " : un bouton de proposition n'a pas de nom accessible (decision 4,"
                        + " clause 2)");
            } else if (!NOM_ACCESSIBLE.equals(bouton.attr("aria-label"))) {
                defauts.add(ou + " : le nom accessible vaut \"" + bouton.attr("aria-label")
                        + "\", attendu exactement le texte de nadia-prof \"" + NOM_ACCESSIBLE + "\" (M5)");
            }
            if (!images.get(0).hasAttr("alt") || !images.get(0).attr("alt").isEmpty()) {
                defauts.add(ou + " : l'alt d'une image de proposition vaut \"" + images.get(0).attr("alt")
                        + "\", attendu vide (nadia-prof, Q-Y14 : un alt qui decrit une oeuvre la designe)");
            }
        }
        Set<String> sourcesAttendues = new LinkedHashSet<>();
        attendues.forEach(id -> sourcesAttendues.add("/oeuvres/" + id + ".jpg"));
        if (!sources.equals(sourcesAttendues)) {
            defauts.add(ou + " : les sources des images valent " + sources + ", attendu exactement "
                    + sourcesAttendues + " (critere 1)");
        }

        if (ecran.select(".ecran-deux-oeuvres").isEmpty()) {
            defauts.add(ou + " : l'ecran ne porte pas .ecran-deux-oeuvres (mise en page d'ines-design)");
        }
        List<Element> marquees = ecran.select("form.propositions button.proposition[data-oeuvre-revelee=true]");
        if (etat == Etat.REVELATION) {
            if (marquees.size() != 1 || !("/oeuvres/" + item.bonneReponse() + ".jpg")
                    .equals(marquees.get(0).select("img").attr("src"))) {
                defauts.add(ou + " : la bonne oeuvre doit etre la seule marquee a la revelation, "
                        + marquees.size() + " marquee(s)");
            }
            if (ecran.select(".reponse-revelee").stream().map(Element::text)
                    .noneMatch(PHRASE_DE_REVELATION::equals)) {
                defauts.add(ou + " : la phrase de revelation de nadia-prof (M4) est absente");
            }
        } else if (!marquees.isEmpty()) {
            defauts.add(ou + " : une oeuvre est marquee comme revelee avant la revelation");
        }
        return defauts;
    }

    /**
     * Critere 6 (N5), fonction pure sur le document : les propositions {@code essayees} portent
     * {@code deja-essayee}, la bonne ne le porte jamais, et elle porte {@code data-oeuvre-revelee} a la
     * revelation et a ce moment seulement. Les propositions sont lues par la source de leur image.
     */
    static List<String> defautsDeLaMarqueDeLEchec(String jeuId, Question item, Etat etat, Document ecran,
            Set<String> essayees) {
        List<String> defauts = new ArrayList<>();
        String ou = "jeu " + jeuId + ", item " + item.id().value() + ", ecran " + etat;
        List<Element> boutons = ecran.select("form.propositions button.proposition");
        if (boutons.isEmpty()) {
            defauts.add(ou + " : aucun bouton de proposition, le critere 6 ne juge rien");
        }
        for (Element bouton : boutons) {
            String source = bouton.select("img").attr("src");
            String proposition = source.substring(source.lastIndexOf('/') + 1).replace(".jpg", "");
            boolean essayee = essayees.contains(proposition);
            boolean estLaBonne = proposition.equals(item.bonneReponse());
            if (essayee && !bouton.hasClass("deja-essayee")) {
                defauts.add(ou + " : " + proposition + " a ete essayee et ne porte pas la classe deja-essayee"
                        + " : l'enfant la presserait une deuxieme fois sans le savoir (N5)");
            }
            if (!essayee && bouton.hasClass("deja-essayee")) {
                defauts.add(ou + " : " + proposition + " n'a pas ete essayee et porte deja-essayee (X6 : aucune"
                        + " difference de presentation avant le geste)");
            }
            if (estLaBonne && bouton.hasClass("deja-essayee")) {
                defauts.add(ou + " : la bonne oeuvre porte deja-essayee : la marque de l'echec dirait \"pas"
                        + " celle-la\" de la bonne reponse (N5)");
            }
            boolean marquee = "true".equals(bouton.attr("data-oeuvre-revelee"));
            if (estLaBonne && etat == Etat.REVELATION && !marquee) {
                defauts.add(ou + " : la bonne oeuvre ne porte pas data-oeuvre-revelee=\"true\" a la revelation (N5)");
            }
            if (!estLaBonne && marquee) {
                defauts.add(ou + " : " + proposition + " porte data-oeuvre-revelee=\"true\" sans etre la bonne");
            }
        }
        return defauts;
    }

    // ------------------------------------------------------------------------------------------
    // Le jeu : la vraie traversee HTTP
    // ------------------------------------------------------------------------------------------

    private Document demarrer(String jeuId, MockHttpSession session) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    private Document coup(String jeuId, MockHttpSession session, String proposition) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                        .param("propositionChoisie", proposition))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    /**
     * Joue chaque item de bout en bout : manche fraiche, deux echecs, la revelation, puis l'appui sur
     * la bonne oeuvre. Le dernier item se termine ainsi sur l'ecran de fin d'une manche resolue par
     * revelation, le seul cas ou la reponse attendue serait ecrite.
     */
    private List<String> joue(String jeuId, List<Question> items) throws Exception {
        List<String> defauts = new ArrayList<>();
        MockHttpSession session = new MockHttpSession();
        Document ecran = demarrer(jeuId, session);
        for (int rang = 0; rang < items.size(); rang++) {
            Question item = items.get(rang);
            defauts.addAll(defautsDe(jeuId, item, Etat.FRAICHE, ecran));
            String mauvaise = item.distracteurs().get(0);
            defauts.addAll(defautsDe(jeuId, item, Etat.ECHEC, coup(jeuId, session, mauvaise)));
            Document secondEchec = coup(jeuId, session, mauvaise);
            defauts.addAll(defautsDe(jeuId, item, Etat.ECHEC, secondEchec));
            Document revelation = coup(jeuId, session, mauvaise);
            defauts.addAll(defautsDe(jeuId, item, Etat.REVELATION, revelation));
            ecran = coup(jeuId, session, item.bonneReponse());
            if (rang == items.size() - 1) {
                defauts.addAll(defautsDe(jeuId, item, Etat.FIN, ecran));
                assertThat(ecran.select(".fin-de-partie"))
                        .as("%s : l'ecran apres le dernier item doit etre l'ecran de fin", jeuId)
                        .isNotEmpty();
            }
        }
        return defauts;
    }

    private List<GameDefinition> jeuxAPropositionsEnImage() {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> questionsDe(jeu).stream().anyMatch(LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest::estEnImage))
                .toList();
    }

    private GameDefinition leJeuDePreuve() {
        return partie.jeuxDisponibles().stream().filter(jeu -> jeu.id().equals(JEU_DE_PREUVE))
                .findFirst().orElseThrow();
    }

    @Test
    void chaqueEcranDeChaqueItemAPropositionsEnImageEstSainEtLePerimetreReelEstVide() throws Exception {
        List<String> defauts = new ArrayList<>();
        List<GameDefinition> reels = jeuxAPropositionsEnImage().stream()
                .filter(jeu -> !jeu.id().equals(JEU_DE_PREUVE)).toList();
        for (GameDefinition jeu : reels) {
            defauts.addAll(joue(jeu.id(), questionsDe(jeu)));
        }
        defauts.addAll(joue(JEU_DE_PREUVE, questionsDe(leJeuDePreuve())));

        assertThat(defauts).as("ADR-0104 decision 7, garde-fou 4").isEmpty();
        assertThat(reels)
                .as("ADR-0051 : aucun jeu reel du catalogue ne sert de propositions-images a s55 ; le"
                        + " jeu de preuve est fait d'items reels mutes. Un paquet reel qui entre est"
                        + " joue ici sans qu'une ligne change, et ce nombre est a relever")
                .isEmpty();
    }

    /** Non-vacuite : le jeu de preuve est bien un jeu a propositions-images, de trois items. */
    @Test
    void leJeuDePreuveEstUnJeuAPropositionsEnImage() {
        List<Question> items = questionsDe(leJeuDePreuve());

        assertThat(items).hasSize(3).allMatch(
                LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest::estEnImage);
        assertThat(items).allSatisfy(item -> assertThat(item.distracteurs())
                .doesNotContain(item.illustration().orElseThrow()));
    }

    // ------------------------------------------------------------------------------------------
    // Les mises a l'epreuve, sur le gabarit reel mute en memoire
    // ------------------------------------------------------------------------------------------

    private List<String> defautsAvecLeGabaritMute(String ancien, String nouveau) throws Exception {
        String reel = gabaritReel();
        assertThat(reel.split(java.util.regex.Pattern.quote(ancien), -1).length - 1)
                .as("l'ancrage de la mutation doit apparaitre exactement une fois dans le gabarit reel : %s",
                        ancien)
                .isEqualTo(1);
        GABARIT_SERVI.set(reel.replace(ancien, nouveau));
        return joue(JEU_DE_PREUVE, questionsDe(leJeuDePreuve()));
    }

    @Test
    void leGabaritNonMuteServiParLeResolveurEstSain() throws Exception {
        GABARIT_SERVI.set(gabaritReel());

        assertThat(joue(JEU_DE_PREUVE, questionsDe(leJeuDePreuve()))).isEmpty();
    }

    /** La mutation nommee par ADR-0104 : le bouton rend l'identifiant en texte. */
    @Test
    void leGabaritMuteEnThTextPropositionFaitEchouerLeGardeFou() throws Exception {
        List<String> defauts = defautsAvecLeGabaritMute(IMAGE_DE_PROPOSITION,
                "<th:block th:if=\"${propositionsEnImage}\" th:text=\"${proposition}\"></th:block>");

        assertThat(defauts)
                .anyMatch(defaut -> defaut.contains("ecrit dans un noeud de texte"))
                .anyMatch(defaut -> defaut.contains("une seule attendue"));
    }

    /** Un alt qui porte l'identifiant : ecrit dans un attribut, il serait lu a voix haute. */
    @Test
    void leGabaritMuteAvecLIdentifiantEnAltFaitEchouerLeGardeFou() throws Exception {
        List<String> defauts = defautsAvecLeGabaritMute("alt=\"\" th:if=\"${propositionsEnImage}\"",
                "th:alt=\"${proposition}\" th:if=\"${propositionsEnImage}\"");

        assertThat(defauts)
                .anyMatch(defaut -> defaut.contains("dans l'attribut alt"))
                .anyMatch(defaut -> defaut.contains("attendu vide"));
    }

    /** Q-Y14 : un alt non vide, meme sans identifiant, decrirait une oeuvre : l'alt vide est imposee. */
    @Test
    void leGabaritMuteAvecUnAltQuiDecritUneOeuvreFaitEchouerLeGardeFou() throws Exception {
        List<String> defauts = defautsAvecLeGabaritMute("alt=\"\" th:if=\"${propositionsEnImage}\"",
                "alt=\"Un paysage\" th:if=\"${propositionsEnImage}\"");

        assertThat(defauts)
                .isNotEmpty()
                .allMatch(defaut -> defaut.contains("l'alt d'une image de proposition vaut \"Un paysage\""));
    }

    /** Le nom accessible retire : un bouton sans nom n'est pas annonce. */
    @Test
    void leGabaritMuteSansNomAccessibleFaitEchouerLeGardeFou() throws Exception {
        List<String> defauts = defautsAvecLeGabaritMute(
                "aria-label=(${propositionsEnImage} ? 'Choisir cette œuvre' : null),", "");

        assertThat(defauts).anyMatch(defaut -> defaut.contains("pas de nom accessible"));
    }

    /** Une bonne reponse en texte a la revelation : le .reponse-attendue est refuse. */
    @Test
    void leGabaritMuteQuiRendLaReponseEnTexteALaRevelationFaitEchouerLeGardeFou() throws Exception {
        List<String> defauts = defautsAvecLeGabaritMute("<p class=\"explication\" th:if=\"${explication != null}\"",
                "<p class=\"reponse-attendue\" th:text=\"${focusCibles}\"></p>\n"
                        + "        <p class=\"explication\" th:if=\"${explication != null}\"");

        assertThat(defauts).anyMatch(defaut -> defaut.contains(".reponse-attendue"));
    }

    /** Decision 3 : le gabarit branche sur le booleen du controleur, jamais sur le prefixe d'une proposition. */
    @Test
    void leGabaritNeLitJamaisLePrefixeDUneProposition() {
        assertThat(gabaritReel())
                .doesNotContain("#strings.startsWith(proposition")
                .doesNotContain("startsWith(proposition");
    }

    /** La mutation qui lirait le prefixe dans le gabarit est elle-meme refusee par la meme clause. */
    @Test
    void unGabaritQuiLitLePrefixeDUnePropositionEstRefuse() {
        String mute = gabaritReel().replace("th:if=\"${propositionsEnImage}\" th:src",
                "th:if=\"${#strings.startsWith(proposition, 'oeuvre-')}\" th:src");

        assertThat(mute).isNotEqualTo(gabaritReel()).contains("#strings.startsWith(proposition");
    }

    // ------------------------------------------------------------------------------------------
    // Critere 6 de S56-ART-CP-10-ECHEC : la marque suit l'essai, la revelation suit la bonne
    // ------------------------------------------------------------------------------------------

    /**
     * Deux erreurs sur deux oeuvres differentes, puis une troisieme erreur qui revele la bonne : les
     * deux boutons essayes sont marques apres la seconde erreur et a la revelation.
     */
    private List<String> joueLaMarque(String jeuId, List<Question> items) throws Exception {
        List<String> defauts = new ArrayList<>();
        MockHttpSession session = new MockHttpSession();
        demarrer(jeuId, session);
        for (Question item : items) {
            String premiere = item.distracteurs().get(0);
            String seconde = item.distracteurs().get(1);
            Set<String> apresLaPremiere = Set.of(premiere);
            Set<String> essayees = Set.of(premiere, seconde);
            defauts.addAll(defautsDeLaMarqueDeLEchec(jeuId, item, Etat.ECHEC,
                    coup(jeuId, session, premiere), apresLaPremiere));
            defauts.addAll(defautsDeLaMarqueDeLEchec(jeuId, item, Etat.ECHEC,
                    coup(jeuId, session, seconde), essayees));
            Document revelation = coup(jeuId, session, premiere);
            assertThat(revelation.select("form.propositions button.proposition[data-oeuvre-revelee=true]"))
                    .as("%s, item %s : la troisieme erreur doit reveler la bonne oeuvre", jeuId, item.id().value())
                    .hasSize(1);
            defauts.addAll(defautsDeLaMarqueDeLEchec(jeuId, item, Etat.REVELATION, revelation, essayees));
            coup(jeuId, session, item.bonneReponse());
        }
        return defauts;
    }

    @Test
    void lesBoutonsEssayesPortentDejaEssayeeEtLaBonneLaRevelationSurLeJeuDePreuveEtLesJeuxReels() throws Exception {
        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxAPropositionsEnImage()) {
            defauts.addAll(joueLaMarque(jeu.id(), questionsDe(jeu)));
        }

        assertThat(jeuxAPropositionsEnImage())
                .as("ADR-0051 : le critere 6 ne prouve rien sans un jeu a propositions-images")
                .isNotEmpty();
        assertThat(defauts).as("S56-ART-CP-10-ECHEC, critere 6 (N5)").isEmpty();
    }

    private List<String> defautsDeLaMarqueAvecLeGabaritMute(String ancien, String nouveau) throws Exception {
        String reel = gabaritReel();
        assertThat(reel.split(java.util.regex.Pattern.quote(ancien), -1).length - 1)
                .as("l'ancrage de la mutation doit apparaitre exactement une fois dans le gabarit reel : %s",
                        ancien)
                .isEqualTo(1);
        GABARIT_SERVI.set(reel.replace(ancien, nouveau));
        return joueLaMarque(JEU_DE_PREUVE, questionsDe(leJeuDePreuve()));
    }

    private static final String CLASSE_ESSAYEE =
            "th:classappend=\"${propositionsEssayees.contains(proposition)} ? 'deja-essayee' : ''\"";

    /** E8 : a la revelation, le gabarit retire deja-essayee des boutons essayes. */
    @Test
    void leGabaritQuiRetireDejaEssayeeALaRevelationFaitEchouerLeCritere6() throws Exception {
        List<String> defauts = defautsDeLaMarqueAvecLeGabaritMute(CLASSE_ESSAYEE,
                "th:classappend=\"${propositionsEssayees.contains(proposition) and mancheRevelee != true}"
                        + " ? 'deja-essayee' : ''\"");

        assertThat(defauts).isNotEmpty()
                .anyMatch(defaut -> defaut.contains("ecran REVELATION")
                        && defaut.contains("ne porte pas la classe deja-essayee"));
    }

    /** La bonne oeuvre ne doit jamais etre marquee comme essayee, meme a la revelation. */
    @Test
    void leGabaritQuiMarqueLaBonneCommeEssayeeALaRevelationFaitEchouerLeCritere6() throws Exception {
        List<String> defauts = defautsDeLaMarqueAvecLeGabaritMute(CLASSE_ESSAYEE,
                "th:classappend=\"${propositionsEssayees.contains(proposition) or"
                        + " (mancheRevelee == true and focusCibles.contains(proposition))}"
                        + " ? 'deja-essayee' : ''\"");

        assertThat(defauts).anyMatch(defaut -> defaut.contains("la bonne oeuvre porte deja-essayee"));
    }

    /** X6 : avant le geste, aucune proposition ne porte la marque. */
    @Test
    void leGabaritQuiMarqueUneOeuvreNonEssayeeFaitEchouerLeCritere6() throws Exception {
        List<String> defauts = defautsDeLaMarqueAvecLeGabaritMute(CLASSE_ESSAYEE,
                "th:classappend=\"'deja-essayee'\"");

        assertThat(defauts).anyMatch(defaut -> defaut.contains("n'a pas ete essayee et porte deja-essayee"));
    }

    // ------------------------------------------------------------------------------------------
    // La configuration
    // ------------------------------------------------------------------------------------------

    @TestConfiguration
    static class GabaritMutableConfiguration {

        @Bean
        StringTemplateResolver gabaritDeMancheMutable() {
            GABARIT_SERVI.set(gabaritReel());
            StringTemplateResolver resolveur = new StringTemplateResolver() {
                @Override
                protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration,
                        String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
                    return new StringTemplateResource(GABARIT_SERVI.get());
                }
            };
            resolveur.setResolvablePatterns(Set.of("fragments/manche"));
            resolveur.setTemplateMode(TemplateMode.HTML);
            resolveur.setCacheable(false);
            resolveur.setOrder(0);
            return resolveur;
        }
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            List<GameDefinition> jeux = new ArrayList<>(new YamlContentPackLoader().games());
            List<GameDefinition> reels = List.copyOf(jeux);
            List<Question> items = itemsDePreuve(reels);
            ContentDescriptor descripteur = new ContentDescriptor("Arts plastiques", "CP",
                    "Preuve d'ADR-0104", "jeu de preuve, jamais servi a un enfant");
            jeux.add(new GameDefinition(JEU_DE_PREUVE, "Preuve", "Consigne de preuve", BanditManchot.ID,
                    descripteur, new ListContentSource(descripteur, items), items.size()));

            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
