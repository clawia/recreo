package com.recreo.bootstrap.catalogue;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0024, section "Ce qui reste au lot de lea-dev", point 4 : un test de rendu qui prouve que
 * l'explication atteint l'ecran de retour d'un item reel, en traversant la frontiere HTTP - ce que
 * {@code PartieControllerTest.lEcranDeRetourRecoitLExplicationDeLItemJoueEtRienQuandIlNEnPorteAucune}
 * ne fait pas ("s'arrete au modele", par la Javadoc de son auteur).
 *
 * <p><b>Pourquoi ce test vit ici et pas dans adapter-web.</b> Prouver le rendu avec un item reel
 * demande a la fois le contenu reel (le paquet YAML, dans {@code adapter-content}) et la couche web
 * ({@code PartieController}, dans {@code adapter-web}) : aucun des deux modules ne voit l'autre
 * (Clean Architecture, les adapters ne dependent pas les uns des autres). Seul {@code bootstrap} voit
 * les deux a la fois.</p>
 *
 * <p><b>Pourquoi ce n'est pas {@code PropositionsAccentueesJouablesIT}, qui joue deja le vrai
 * catalogue.</b> {@code DEC-0009} retire {@code bandit-manchot-le-bon-son-cp} de
 * {@code catalogue.yaml} tant que son Done pedagogique n'est pas prononce : un test qui derive du
 * catalogue de production ne verrait donc jamais ce paquet. Ce test construit son propre contexte
 * {@code @WebMvcTest}, sur le modele de {@code PartieControllerTest}, mais nourri par les
 * <b>donnees reelles</b> du paquet {@code cp-le-bon-son.yaml} plutot que par du texte invente -
 * l'amendement du 2026-08-06 a `ADR-0013` ("les donnees d'entree d'un test font partie de son
 * perimetre") l'exige explicitement pour ce garde-fou-la.</p>
 *
 * <p>Le paquet reel est lu par {@link Yaml} directement (le lecteur package-prive de
 * {@code adapter-content}, {@code YamlContentPackLoader.chargerPaquet}, n'est pas visible d'ici),
 * puis converti en {@link Question} par le meme mecanisme que
 * {@code QuestionContentSourceFactory} : lire les cles connues, ignorer {@code graphie}. L'item a
 * variation et l'item regulier ne sont pas nommes ici : ils sont <b>derives</b> du fichier reel en
 * comparant sa {@code graphie} a sa {@code bonneReponse}, exactement comme
 * {@code ChaquePaquetExpliqueSesItemsAVariationTest} le fait (ADR-0013 obligation 1) - si la
 * recomposition change demain les mots choisis, ce test continue de prouver la meme propriete sur
 * les mots qui existeront alors.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LExplicationDUnItemReelAtteintLEcranDeRetourTest.JeuDeTestConfiguration.class)
class LExplicationDUnItemReelAtteintLEcranDeRetourTest {

    private static final String PAQUET_REEL = "/contenu/cp-le-bon-son.yaml";
    private static final String JEU_VARIATION_ID = "test-le-bon-son-variation";
    private static final String JEU_REGULIER_ID = "test-le-bon-son-regulier";

    @Autowired
    private MockMvc mockMvc;

    private static Map<String, Object> lirePaquetReel() {
        try (InputStream flux = LExplicationDUnItemReelAtteintLEcranDeRetourTest.class
                .getResourceAsStream(PAQUET_REEL)) {
            assertThat(flux)
                    .as("%s introuvable sur le classpath : ce test ne peut prouver le rendu sur"
                            + " aucun item reel", PAQUET_REEL)
                    .isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return corps;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsReels() {
        Object items = lirePaquetReel().get("items");
        assertThat(items).as("le paquet reel doit enumerer des items").isInstanceOf(List.class);
        return (List<Map<String, Object>>) items;
    }

    /** ADR-0024 : conversion identique a QuestionContentSourceFactory - 'graphie' n'est pas lue. */
    @SuppressWarnings("unchecked")
    private static Question versQuestion(Map<String, Object> item) {
        String enonce = String.valueOf(item.get("enonce"));
        String bonneReponse = String.valueOf(item.get("bonneReponse"));
        List<String> distracteurs = ((List<Object>) item.get("distracteurs")).stream()
                .map(String::valueOf).toList();
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        return new Question(new ItemId("item-reel-" + enonce), enonce, bonneReponse, distracteurs,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)));
    }

    /** ADR-0013 obligation 1 : l'item a variation est derive du fichier, jamais nomme ici. */
    private static Map<String, Object> premierItemAVariation() {
        return itemsReels().stream()
                .filter(item -> item.get("graphie") != null
                        && !String.valueOf(item.get("graphie"))
                                .equalsIgnoreCase(String.valueOf(item.get("bonneReponse"))))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "le paquet reel doit porter au moins un item a variation, sinon ce test ne"
                                + " prouve rien"));
    }

    private static Map<String, Object> premierItemRegulier() {
        return itemsReels().stream()
                .filter(item -> item.get("graphie") != null
                        && String.valueOf(item.get("graphie"))
                                .equalsIgnoreCase(String.valueOf(item.get("bonneReponse"))))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "le paquet reel doit porter au moins un item regulier, sinon ce test ne"
                                + " prouve rien"));
    }

    @Test
    void lExplicationDUnItemReelAVariationAtteintLEcranDeRetour() throws Exception {
        Map<String, Object> item = premierItemAVariation();
        String explicationAttendue = String.valueOf(item.get("explication"));
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_VARIATION_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_VARIATION_ID)
                        .session(session)
                        .param("propositionChoisie", String.valueOf(item.get("bonneReponse"))))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);
        Element explication = fragment.selectFirst(".explication");
        assertThat(explication)
                .as("l'item reel \"%s\" declare une explication dans cp-le-bon-son.yaml ; aucun"
                        + " element .explication n'atteint l'ecran de retour", item.get("enonce"))
                .isNotNull();

        // s23 (yanis-qa) : lu par wholeText et non par text. Element.text() de jsoup (1.23.1)
        // normalise U+00A0 en U+0020 (mesure ADR-0060) ; ce garde-fou promet "telle quelle" et ne
        // pouvait donc pas voir une espace insecable que l'explication porterait, ni distinguer un
        // rendu qui la perd d'un rendu qui la porte fidelement. Meme defaut et meme correctif que
        // LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest (ADR-0050).
        assertThat(explication.wholeText().strip())
                .as("l'item reel \"%s\" declare une explication dans cp-le-bon-son.yaml ; elle doit"
                        + " atteindre l'ecran de retour telle quelle", item.get("enonce"))
                .isEqualTo(explicationAttendue);
    }

    @Test
    void unItemReelRegulierNAfficheAucuneExplicationSurLEcranDeRetour() throws Exception {
        Map<String, Object> item = premierItemRegulier();
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_REGULIER_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_REGULIER_ID)
                        .session(session)
                        .param("propositionChoisie", String.valueOf(item.get("bonneReponse"))))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".explication"))
                .as("l'item reel \"%s\" est regulier : rien a expliquer, rien a afficher",
                        item.get("enonce"))
                .isEmpty();
    }

    @TestConfiguration
    static class JeuDeTestConfiguration {

        private static final ContentDescriptor DESCRIPTEUR =
                new ContentDescriptor("Francais", "CP", "Lecture et comprehension de l'ecrit",
                        "test de rendu, pas un paquet joue par un enfant");

        @Bean
        PartieUseCase partieUseCase() {
            Question variation = versQuestion(premierItemAVariation());
            Question regulier = versQuestion(premierItemRegulier());
            ContentCatalogPort catalogue = () -> List.of(
                    new GameDefinition(JEU_VARIATION_ID, "Test variation",
                            "Dis le mot. Quel son entends-tu ?", BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(variation)), 1),
                    new GameDefinition(JEU_REGULIER_ID, "Test regulier",
                            "Dis le mot. Quel son entends-tu ?", BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(regulier)), 1));
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
