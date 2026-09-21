package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0094, decision 4.1 : la cle facultative 'emplacementDuLibelleDeZone' d'un paquet de
 * designation refuse toute valeur qu'elle ne reconnait pas, sur le meme patron que
 * 'exigenceDeForme' ({@link SaisieContentSourceFactoryTest#uneExigenceDeFormeInconnueArreteLeChargementEnNommantLaCleEtLaValeur()}).
 * Sans ce garde-fou, une faute de frappe sur cette cle retomberait silencieusement sur le defaut
 * 'dans-la-boite' et rendrait un paquet dans une forme que son auteur croit avoir changee, sans
 * qu'aucun test ne le remarque.
 */
class DesignationContentSourceFactoryTest {

    private static final String RACINE_DE_SONDE = "/fixtures/designation";

    @Test
    void uneValeurInconnueDEmplacementDuLibelleDeZoneArreteLeChargementEnNommantLaCleEtLaValeur() {
        assertThatThrownBy(() -> new YamlContentPackLoader(RACINE_DE_SONDE)
                .chargerPaquet("paquet-designation-emplacement-inconnu"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("emplacementDuLibelleDeZone")
                .hasMessageContaining("au-milieu");
    }
}
