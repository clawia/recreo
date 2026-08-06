package com.recreo.adapter.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * S05 : seules les requetes POST application/x-www-form-urlencoded traversent le decodage
 * tolerant de RequeteFormulaireDecodeeEnUtf8. Les autres passent inchangees.
 */
class CorpsFormulaireEnUtf8ToleranteFilterTest {

    private final CorpsFormulaireEnUtf8ToleranteFilter filtre = new CorpsFormulaireEnUtf8ToleranteFilter();

    @Test
    void enveloppeUneRequetePostDeFormulaire() throws Exception {
        MockHttpServletRequest requete = new MockHttpServletRequest("POST", "/parties/un-jeu/coups");
        requete.setContentType("application/x-www-form-urlencoded");
        requete.setContent("propositionChoisie=a".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse reponse = new MockHttpServletResponse();
        FilterChain chaine = mock(FilterChain.class);

        filtre.doFilter(requete, reponse, chaine);

        ArgumentCaptor<HttpServletRequest> capture = ArgumentCaptor.forClass(HttpServletRequest.class);
        verify(chaine).doFilter(capture.capture(), eq(reponse));
        assertThat(capture.getValue()).isInstanceOf(RequeteFormulaireDecodeeEnUtf8.class);
    }

    @Test
    void laisseInchangeeUneRequeteQuiNEstPasUnFormulaire() throws Exception {
        MockHttpServletRequest requete = new MockHttpServletRequest("GET", "/");
        MockHttpServletResponse reponse = new MockHttpServletResponse();
        FilterChain chaine = mock(FilterChain.class);

        filtre.doFilter(requete, reponse, chaine);

        verify(chaine).doFilter(requete, reponse);
    }

    @Test
    void laisseInchangeeUneRequetePostSansCorpsDeFormulaire() throws Exception {
        MockHttpServletRequest requete = new MockHttpServletRequest("POST", "/retour");
        MockHttpServletResponse reponse = new MockHttpServletResponse();
        FilterChain chaine = mock(FilterChain.class);

        filtre.doFilter(requete, reponse, chaine);

        verify(chaine).doFilter(requete, reponse);
    }
}
