package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Le decodeur ne touche que la reference numerique decimale que le mode d'erreur "html" de
 * l'Encoding Standard produit ; chaque cas fautif nomme ce qui doit rester tel quel. Les
 * caracteres non ASCII sont construits depuis leur point de code, jamais ecrits en clair, pour que
 * le test dise lequel il manipule.
 */
class ReferencesNumeriquesDecimalesTest {

    private static final String TRAIT_D_UNION_INSECABLE = Character.toString(0x2011);
    private static final String E_GRAVE = Character.toString(0xE8);
    private static final String TAS_DE_BOUE = Character.toString(0x1F4A9);

    @Test
    void decodeLeTraitDUnionInsecableEnDecimal() {
        assertThat(ReferencesNumeriquesDecimales.decoder("celle des grands&#8209;parents"))
                .isEqualTo("celle des grands" + TRAIT_D_UNION_INSECABLE + "parents");
    }

    @Test
    void decodeToutesLesReferencesDUneValeur() {
        assertThat(ReferencesNumeriquesDecimales.decoder("arri&#232;re&#8209;grands&#8209;parents"))
                .isEqualTo("arri" + E_GRAVE + "re" + TRAIT_D_UNION_INSECABLE + "grands"
                        + TRAIT_D_UNION_INSECABLE + "parents");
    }

    @Test
    void decodeUnPointDeCodeHorsDuPlanDeBase() {
        assertThat(ReferencesNumeriquesDecimales.decoder("&#128169;")).isEqualTo(TAS_DE_BOUE);
    }

    @Test
    void laisseUneValeurSansReferenceInchangee() {
        assertThat(ReferencesNumeriquesDecimales.decoder("le jardin & le # 1 ;")).isEqualTo("le jardin & le # 1 ;");
    }

    @Test
    void neDecodePasLaFormeHexadecimaleQueLeStandardNePrevoitPas() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#x2011;b")).isEqualTo("a&#x2011;b");
    }

    @Test
    void laisseInchangeeUneReferenceMalformee() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#8209b")).isEqualTo("a&#8209b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&8209;b")).isEqualTo("a&8209;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#;b")).isEqualTo("a&#;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#-8209;b")).isEqualTo("a&#-8209;b");
    }

    @Test
    void laisseInchangeeUneReferenceNonNumerique() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#abc;b")).isEqualTo("a&#abc;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#82x9;b")).isEqualTo("a&#82x9;b");
    }

    @Test
    void laisseInchangeeUneReferenceHorsPlage() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#0;b")).isEqualTo("a&#0;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#55357;b")).isEqualTo("a&#55357;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#1114112;b")).isEqualTo("a&#1114112;b");
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#99999999999;b")).isEqualTo("a&#99999999999;b");
    }

    /** "&#38;#8209;" : un seul passage donne "&#8209;" litteral, jamais le trait d'union. */
    @Test
    void neDecodePasDeuxFoisUneReferenceDontLeResultatEstUneReference() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&#38;#8209;b")).isEqualTo("a&#8209;b");
    }

    @Test
    void neDecodeAucuneEntiteNommee() {
        assertThat(ReferencesNumeriquesDecimales.decoder("a&amp;b &eacute; &lt;")).isEqualTo("a&amp;b &eacute; &lt;");
    }
}
