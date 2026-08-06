package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S05 : ce decodage remplace celui de Tomcat (org.apache.tomcat.util.http.Parameters), qui leve
 * une IllegalStateException sur toute sequence d'octets non valide en UTF-8 - constat isole sur
 * cm1-homophones.yaml, ou une proposition comme "a" ou "Ou" peut declencher ce cas selon la
 * maniere dont le client encode sa requete. java.lang.String, utilise ici, ne leve jamais.
 */
class RequeteFormulaireDecodeeEnUtf8Test {

    @Test
    void decodeUneValeurAsciiOrdinaire() throws Exception {
        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe("propositionChoisie=a".getBytes(StandardCharsets.UTF_8));

        assertThat(requete.getParameter("propositionChoisie")).isEqualTo("a");
    }

    @Test
    void decodeUneValeurAccentueeCorrectementEncodeeEnUtf8() throws Exception {
        byte[] corps = "propositionChoisie=%C3%A0".getBytes(StandardCharsets.US_ASCII);

        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe(corps);

        assertThat(requete.getParameter("propositionChoisie")).isEqualTo("à");
    }

    /** Le "+" vaut espace en application/x-www-form-urlencoded, jamais un signe operatoire. */
    @Test
    void decodeLeSigneDePlusCommeUnEspace() throws Exception {
        byte[] corps = "propositionChoisie=deux+mots".getBytes(StandardCharsets.US_ASCII);

        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe(corps);

        assertThat(requete.getParameter("propositionChoisie")).isEqualTo("deux mots");
    }

    /**
     * Le meme octet isole (0xE0) que le decodeur de Tomcat refuse avec
     * org.apache.tomcat.util.http.InvalidParameterException (constat isole sur le jar de s05).
     */
    @Test
    void neLevePasSurUneSequenceDOctetsNonValideEnUtf8() throws Exception {
        byte[] prefixe = "propositionChoisie=".getBytes(StandardCharsets.US_ASCII);
        byte[] corps = new byte[prefixe.length + 1];
        System.arraycopy(prefixe, 0, corps, 0, prefixe.length);
        corps[prefixe.length] = (byte) 0xE0;

        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe(corps);

        assertThat(requete.getParameter("propositionChoisie")).isNotNull();
    }

    @Test
    void exposeLaValeurParGetParameterValuesEtGetParameterMap() throws Exception {
        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe("propositionChoisie=a".getBytes(StandardCharsets.UTF_8));

        assertThat(requete.getParameterValues("propositionChoisie")).containsExactly("a");
        assertThat(requete.getParameterMap()).containsEntry("propositionChoisie", new String[] {"a"});
        assertThat(requete.getParameterNames().nextElement()).isEqualTo("propositionChoisie");
    }

    @Test
    void rendNullPourUnParametreAbsent() throws Exception {
        RequeteFormulaireDecodeeEnUtf8 requete = enveloppe("propositionChoisie=a".getBytes(StandardCharsets.UTF_8));

        assertThat(requete.getParameter("inconnu")).isNull();
    }

    private static RequeteFormulaireDecodeeEnUtf8 enveloppe(byte[] corps) throws Exception {
        MockHttpServletRequest requeteOrigine = new MockHttpServletRequest("POST", "/parties/un-jeu/coups");
        requeteOrigine.setContent(corps);
        return new RequeteFormulaireDecodeeEnUtf8(requeteOrigine);
    }
}
