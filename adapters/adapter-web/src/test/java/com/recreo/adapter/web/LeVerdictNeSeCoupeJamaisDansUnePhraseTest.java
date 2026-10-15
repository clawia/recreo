package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.designation.Designation;
import com.recreo.domain.mechanics.saisie.Saisie;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0067, amendement du 2026-10-14 (s57, S57-FORME-C-VERDICT) : le verdict que le controleur ecrit
 * lui-meme ne se coupe jamais a l'interieur d'une de ses phrases. Les mots d'une phrase sont separes
 * par U+00A0, un signe double est precede de U+00A0, deux phrases sont separees par une seule espace
 * ordinaire.
 *
 * <p>Le perimetre est derive du comportement : les verdicts rendus sur les trois issues (juste,
 * echec avec essais restants, revelation) de chaque mecanique que le catalogue de test sert, et non
 * d'une liste de constantes de {@code PartieController}, a laquelle une quatrieme echapperait. Une
 * mecanique servie sans parcours ici rend ce test rouge.</p>
 *
 * <p>Le verdict est lu par {@code wholeText()} de {@code .verdict}, jamais par {@code text()} : jsoup
 * normalise U+00A0 en espace ordinaire dans {@code text()}, et c'est pourquoi les assertions de
 * {@link PartieControllerTest} qui lisent {@code .text()} ne jugent pas la coupure.</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LeVerdictNeSeCoupeJamaisDansUnePhraseTest {

    private static final char ESPACE_INSECABLE = '\u00A0';
    private static final String SIGNES_DOUBLES = "?!;:";
    private static final String FINS_DE_PHRASE = ".!?";

    private static final String TEXTE_JUSTE = "Bravo !";
    private static final String TEXTE_ECHEC = "Ce n'est pas ça. Essaie encore !";
    private static final String TEXTE_REVELATION = "Ce n'est pas ça.";

    /** Comment jouer une manche du jeu d'une mecanique : la route du geste, son parametre, une reponse juste, une fausse. */
    private record Parcours(String jeuId, String route, String parametre, String juste, String fausse) {
    }

    /** Une entree par mecanique servie par le catalogue de test (JeuDeTestConfiguration). */
    private static final Map<MechanicId, Parcours> PARCOURS = Map.of(
            BanditManchot.ID, new Parcours(PartieControllerTest.JEU_ID,
                    "/parties/{jeuId}/coups", "propositionChoisie", "1", "2"),
            Saisie.ID, new Parcours(PartieControllerTest.JEU_SAISIE_ID,
                    "/parties/{jeuId}/reponses", "reponseEcrite", "7", "0"),
            Designation.ID, new Parcours(PartieControllerTest.JEU_DESIGNATION_ID,
                    "/parties/{jeuId}/designations", "zoneDesignee", "tableau", "porte"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partieUseCase;

    /**
     * Les defauts d'un verdict, un message par defaut. Une espace ordinaire est permise seulement
     * apres une fin de phrase, et jamais devant un signe double.
     */
    static List<String> defauts(String verdict) {
        List<String> defauts = new ArrayList<>();
        for (int position = 0; position < verdict.length(); position++) {
            if (verdict.charAt(position) != ' ') {
                continue;
            }
            if (position + 1 < verdict.length()
                    && SIGNES_DOUBLES.indexOf(verdict.charAt(position + 1)) >= 0) {
                defauts.add("espace ordinaire devant '" + verdict.charAt(position + 1)
                        + "' en position " + position);
            }
            if (position == 0 || FINS_DE_PHRASE.indexOf(verdict.charAt(position - 1)) < 0) {
                defauts.add("espace ordinaire qui ne suit pas une fin de phrase, en position " + position);
            }
        }
        return defauts;
    }

    private String verdictRendu(String html) {
        Elements verdicts = Jsoup.parse(html).select(".verdict");
        assertThat(verdicts).as("l'ecran rendu doit porter un seul .verdict").hasSize(1);
        return verdicts.first().wholeText();
    }

    private String geste(Parcours parcours, MockHttpSession session, String valeur) throws Exception {
        return mockMvc.perform(post(parcours.route(), parcours.jeuId())
                        .session(session).param(parcours.parametre(), valeur))
                .andReturn().getResponse().getContentAsString();
    }

    /** Un verdict par issue, dans l'ordre : juste, echec avec essais restants, revelation. */
    private Map<String, String> verdictsDe(Parcours parcours) throws Exception {
        Map<String, String> verdicts = new LinkedHashMap<>();

        MockHttpSession pourLeJuste = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", parcours.jeuId()).session(pourLeJuste));
        verdicts.put("juste", verdictRendu(geste(parcours, pourLeJuste, parcours.juste())));

        MockHttpSession pourLEchec = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", parcours.jeuId()).session(pourLEchec));
        verdicts.put("echec", verdictRendu(geste(parcours, pourLEchec, parcours.fausse())));

        MockHttpSession pourLaRevelation = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", parcours.jeuId()).session(pourLaRevelation));
        String dernier = null;
        for (int essai = 0; essai < 3; essai++) {
            dernier = geste(parcours, pourLaRevelation, parcours.fausse());
        }
        verdicts.put("revelation", verdictRendu(dernier));
        return verdicts;
    }

    @Test
    void toutesLesMecaniquesServiesOntUnParcours() {
        Set<MechanicId> servies = partieUseCase.jeuxDuNiveau("CP").stream()
                .map(GameDefinition::mecanique)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        assertThat(servies)
                .as("une mecanique servie sans parcours ici echapperait a la propriete de coupure")
                .containsExactlyInAnyOrderElementsOf(PARCOURS.keySet());
        PARCOURS.forEach((mecanique, parcours) -> assertThat(partieUseCase.jeuxDuNiveau("CP"))
                .as("le jeu du parcours de %s doit etre servi par cette mecanique", mecanique.value())
                .anyMatch(jeu -> jeu.id().equals(parcours.jeuId()) && jeu.mecanique().equals(mecanique)));
    }

    @Test
    void aucunVerdictRenduNEstCoupableDansUnePhraseNiDevantUnSigneDouble() throws Exception {
        List<String> constats = new ArrayList<>();
        int verdictsLus = 0;
        for (Map.Entry<MechanicId, Parcours> entree : PARCOURS.entrySet()) {
            for (Map.Entry<String, String> verdict : verdictsDe(entree.getValue()).entrySet()) {
                verdictsLus++;
                for (String defaut : defauts(verdict.getValue())) {
                    constats.add(entree.getKey().value() + ", " + verdict.getKey() + " : " + defaut
                            + " dans \"" + verdict.getValue().replace(ESPACE_INSECABLE, '~') + "\" (~ = U+00A0)");
                }
            }
        }

        assertThat(verdictsLus)
                .as("trois issues pour chacune des trois mecaniques : une propriete lue sur rien ne prouve rien")
                .isEqualTo(9);
        assertThat(constats).isEmpty();
    }

    /** Condition de nadia-prof (2.4) : seules les espaces changent, jamais un mot ni un signe. */
    @Test
    void leTexteLuParLEnfantNeChangeNiUnMotNiUnSigne() throws Exception {
        for (Map.Entry<MechanicId, Parcours> entree : PARCOURS.entrySet()) {
            Map<String, String> verdicts = verdictsDe(entree.getValue());

            assertThat(verdicts.get("juste").replace(ESPACE_INSECABLE, ' '))
                    .as("%s, juste", entree.getKey().value()).isEqualTo(TEXTE_JUSTE);
            assertThat(verdicts.get("echec").replace(ESPACE_INSECABLE, ' '))
                    .as("%s, echec", entree.getKey().value()).isEqualTo(TEXTE_ECHEC);
            assertThat(verdicts.get("revelation").replace(ESPACE_INSECABLE, ' '))
                    .as("%s, revelation", entree.getKey().value()).isEqualTo(TEXTE_REVELATION);
            assertThat(verdicts.get("echec").chars().filter(c -> c == ' ').count())
                    .as("%s : une seule espace ordinaire entre les deux phrases", entree.getKey().value())
                    .isEqualTo(1);
        }
    }

    @Test
    void laProprieteMordSurUneEspaceOrdinaireRemise() {
        assertThat(defauts("Ce\u00A0n'est\u00A0pas\u00A0ça. Essaie\u00A0encore\u00A0!")).isEmpty();
        assertThat(defauts("Bravo\u00A0!")).isEmpty();
        assertThat(defauts("Ce\u00A0n'est\u00A0pas\u00A0ça.")).isEmpty();

        assertThat(defauts("Ce n'est\u00A0pas\u00A0ça. Essaie\u00A0encore\u00A0!"))
                .as("espace ordinaire entre deux mots d'une phrase")
                .isNotEmpty();
        assertThat(defauts("Bravo !")).as("espace ordinaire devant le !").hasSize(2);
    }
}
