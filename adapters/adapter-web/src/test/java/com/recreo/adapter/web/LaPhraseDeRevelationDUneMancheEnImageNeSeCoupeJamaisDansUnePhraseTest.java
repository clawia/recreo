package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0067, amendement du 2026-10-15 (s57, S57-FORME-C-PHRASE-M4) : la phrase que le gabarit
 * {@code fragments/manche.html} ecrit a la revelation d'une manche a propositions en image
 * ({@code .reponse-revelee}) ne se coupe jamais a l'interieur d'une de ses deux phrases. Les mots
 * d'une phrase sont separes par U+00A0, et les deux phrases par une seule espace ordinaire
 * (reponse-nadia-forme2-palier-1.md, 2.3, {@code R4}).
 *
 * <p>Le texte est lu par {@code wholeText()} de {@code .reponse-revelee}, jamais par {@code text()} :
 * jsoup (1.23.1) normalise U+00A0 en espace ordinaire dans {@code TextNode.text()} comme dans
 * {@code Element.text()}. C'est pourquoi {@link PartieControllerPropositionsEnImageTest}, qui lit cette
 * phrase par {@code eachText()}, et les deux autres tests qui la lisent ne jugent pas la coupure : ils
 * restent verts quelle que soit la nature des espaces.</p>
 *
 * <p>Dans la source du gabarit U+00A0 s'ecrit {@code &nbsp;}, jamais le caractere tape. Dans ce
 * fichier, {@code ~} marque une U+00A0 : le caractere lui-meme ne se distingue pas d'une espace.</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerPropositionsEnImageTest.JeuxDeTestConfiguration.class)
class LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest {

    private static final char ESPACE_INSECABLE = (char) 0x00A0;
    private static final String FINS_DE_PHRASE = ".!?";

    /** La phrase de M4 (nadia-prof), mot pour mot, lue comme le fait un oeil : U+00A0 vaut une espace. */
    private static final String PHRASE_DE_M4 = "Voici la bonne œuvre. Appuie dessus.";

    private static final String PREMIERE_PHRASE = "Voici la bonne œuvre.";
    private static final String SECONDE_PHRASE = "Appuie dessus.";

    @Autowired
    private MockMvc mockMvc;

    private static String insecables(String marquee) {
        return marquee.replace('~', ESPACE_INSECABLE);
    }

    private static String visible(String texte) {
        return texte.replace(ESPACE_INSECABLE, '~');
    }

    private static long nombreDInsecables(String texte) {
        return texte.chars().filter(c -> c == ESPACE_INSECABLE).count();
    }

    /**
     * Les defauts de la phrase, un message par defaut : U+00A0 lue comme une espace, le texte vaut la
     * phrase de M4 ; toute espace ordinaire suit immediatement une fin de phrase ; la premiere phrase
     * est suivie d'une espace ordinaire ; trois U+00A0 dans la premiere phrase, une dans la seconde.
     */
    static List<String> defauts(String phrase) {
        List<String> defauts = new ArrayList<>();
        if (!phrase.replace(ESPACE_INSECABLE, ' ').equals(PHRASE_DE_M4)) {
            defauts.add("le texte lu vaut \"" + visible(phrase) + "\", la phrase de M4 est \"" + PHRASE_DE_M4 + "\"");
            return defauts;
        }
        for (int position = 0; position < phrase.length(); position++) {
            if (phrase.charAt(position) == ' '
                    && (position == 0 || FINS_DE_PHRASE.indexOf(phrase.charAt(position - 1)) < 0)) {
                defauts.add("espace ordinaire qui ne suit pas une fin de phrase, en position " + position
                        + " de \"" + visible(phrase) + "\" (~ = U+00A0)");
            }
        }
        int coupure = PREMIERE_PHRASE.length();
        if (phrase.charAt(coupure) != ' ') {
            defauts.add("la premiere phrase n'est pas suivie d'une espace ordinaire dans \"" + visible(phrase)
                    + "\" (~ = U+00A0)");
        }
        if (nombreDInsecables(phrase.substring(0, coupure)) != 3) {
            defauts.add("la premiere phrase porte " + nombreDInsecables(phrase.substring(0, coupure))
                    + " U+00A0, trois attendues");
        }
        if (nombreDInsecables(phrase.substring(coupure + 1)) != 1) {
            defauts.add("la seconde phrase porte " + nombreDInsecables(phrase.substring(coupure + 1))
                    + " U+00A0, une attendue");
        }
        return defauts;
    }

    private String phraseRendueALaRevelation() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", PartieControllerPropositionsEnImageTest.JEU_EN_IMAGE)
                .session(session));
        String html = null;
        for (int echec = 1; echec <= 3; echec++) {
            html = mockMvc.perform(post("/parties/{jeuId}/coups", PartieControllerPropositionsEnImageTest.JEU_EN_IMAGE)
                            .session(session).param("propositionChoisie", "oeuvre-b"))
                    .andReturn().getResponse().getContentAsString();
        }
        Document revelation = Jsoup.parseBodyFragment(html);
        assertThat(revelation.select("[data-oeuvre-revelee=true]"))
                .as("l'ecran lu est bien celui d'une revelation de manche en image").isNotEmpty();
        Elements phrases = revelation.select(".reponse-revelee");
        assertThat(phrases).as("une seule phrase de revelation").hasSize(1);
        return phrases.first().wholeText();
    }

    @Test
    void laPhraseDeRevelationRendueNeSeCoupeNiDansUnePhraseNiEntreLesDeux() throws Exception {
        String phrase = phraseRendueALaRevelation();

        assertThat(defauts(phrase)).as("phrase rendue : \"%s\" (~ = U+00A0)", visible(phrase)).isEmpty();
    }

    @Test
    void laProprieteMordSurUneEspaceOrdinaireRemiseEtSurUnInsecableEntreLesDeuxPhrases() {
        assertThat(defauts(insecables("Voici~la~bonne~œuvre. Appuie~dessus."))).isEmpty();

        assertThat(defauts(insecables("Voici~la bonne~œuvre. Appuie~dessus.")))
                .as("espace ordinaire entre la et bonne").isNotEmpty();
        assertThat(defauts(insecables("Voici~la~bonne~œuvre.~Appuie~dessus.")))
                .as("U+00A0 entre les deux phrases").isNotEmpty();
        assertThat(defauts(insecables("Voici~la~bonne~œuvre. Appuie dessus.")))
                .as("espace ordinaire entre Appuie et dessus").isNotEmpty();
        assertThat(defauts("Voici la bonne œuvre. Appuie dessus."))
                .as("la phrase de M4 ecrite sans aucune U+00A0").isNotEmpty();
        assertThat(defauts(insecables("Voici~la~bonne~œuvre. Appuie~dessus !")))
                .as("un mot ou un signe change").isNotEmpty();
    }
}
