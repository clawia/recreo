package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0092 : la propriete "le libelle d'une zone tient entierement dans son cadre a 1,25rem" n'est
 * tenue par aucun garde-fou et ne peut pas l'etre (police du systeme, ADR-0092 section 2). Elle est
 * tenue par l'oeil de {@code nadia-prof}, paquet par paquet. Ce cliquet empeche que cette dette soit
 * oubliee : il rougit le jour ou un paquet de designation de plus entre en production.
 *
 * <p>Le perimetre et le comptage sont ceux de
 * {@link AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest}, reutilises et
 * non recrits - un second recensement des paquets de designation divergerait un jour du premier.</p>
 *
 * <p>Egalite, jamais plafond (ADR-0087, amendement de la cloture de s31, et ADR-0067) : un paquet de
 * plus est rouge, et un chiffre plus haut que le compte reel est rouge aussi.</p>
 */
class AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest {

    /**
     * Valeur posee par le diff qui sert le second paquet de designation ({@code cp-plan-de-la-classe},
     * audite a l'oeil par {@code nadia-prof} sur {@code ZON-15}) ; elle valait 1 tant que seul
     * {@code cp-frise-de-la-journee} etait servi.
     */
    private static final int PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL = 2;

    @Test
    void aucunPaquetDeDesignationDePlusNEntreEnProductionSansQueCetteDetteSoitPayee() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corps = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
                .corpsCharges(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                        .repertoiresDeContenu(racine));
        long compte = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
                .paquetsDeDesignation(corps);
        assertThat(compte)
                .as("ADR-0092 : %d paquets de designation sont servis et %d ont ete audites a l'oeil"
                        + " sur ZON-15. Un paquet de plus entre en production avec une propriete de"
                        + " lisibilite que rien ne garde : soit son libelle est juge a l'ecran par"
                        + " nadia-prof et ce chiffre monte dans le meme diff, soit la propriete cesse"
                        + " de dependre de la police (ADR-0092, decisions 2 et 3)",
                        compte, PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL)
                .isEqualTo(PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL);
    }
}
