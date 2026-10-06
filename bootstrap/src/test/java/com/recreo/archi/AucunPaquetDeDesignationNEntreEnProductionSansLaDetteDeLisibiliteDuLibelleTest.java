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
     * Valeur posee par le diff qui sert le second paquet de designation
     * ({@code cp-plan-de-la-classe}) ; elle valait 1 tant que seul {@code cp-frise-de-la-journee}
     * etait servi.
     *
     * <p><b>Ce compteur compte des paquets servis, jamais des paquets audites</b> (amendement
     * d'ADR-0092 du 2026-09-11, `theo-archi`, a la confirmation de la porte technique de s35). Son
     * nom d'origine, {@code PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL}, etait prescrit par ADR-0092
     * lui-meme et affirmait un audit : au jour ou cette valeur est passee a 2, **2 paquets de
     * designation sont servis et 1 seul a ete audite a l'oeil sur ZON-15**
     * ({@code squad/conformite/FICHE-designation-frise-de-la-journee.md} ; aucune fiche n'existe
     * pour {@code cp-plan-de-la-classe}, dont l'audit est du a la review de s35). Le recensement
     * appele ci-dessous ne sait lire que les paquets servis, donc aucun nom ne peut promettre
     * l'audit sans mentir - ADR-0015, un nom qui promet plus que son perimetre.</p>
     *
     * <p><b>Ce que ce cliquet fait donc reellement</b> : il oblige un diff qui sert un paquet de
     * designation de plus a toucher ce chiffre, ce qui route la question de l'audit. Il ne verifie
     * pas qu'un audit a eu lieu, et il ne bloque pas un paquet dont l'audit est en attente.</p>
     *
     * <p><b>Elle passe a 3 avec {@code ce1-symetrie}</b> (S51-MAT-SYMETRIE, s52, lea-dev) : les libelles
     * de ce paquet se lisent hors de la boite (ADR-0094), dans une legende de trois a cinq lignes, et
     * leur lisibilite a l'ecran reste l'oeil de nadia-prof.</p>
     */
    private static final int PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 3;

    @Test
    void aucunPaquetDeDesignationDePlusNEntreEnProductionSansQueCetteDetteSoitPayee() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corps = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
                .corpsCharges(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                        .repertoiresDeContenu(racine));
        long compte = AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest
                .paquetsDeDesignation(corps);
        assertThat(compte)
                .as("ADR-0092 : %d paquets de designation sont servis, et ce cliquet en portait %d."
                        + " Un paquet de plus entre en production avec une propriete de lisibilite"
                        + " que rien ne garde : soit son libelle est juge a l'ecran par nadia-prof et"
                        + " ce chiffre monte dans le meme diff, soit la propriete cesse de dependre de"
                        + " la police (ADR-0092, decisions 2 et 3). Ce cliquet compte des paquets"
                        + " servis, pas des paquets audites - il route l'audit, il ne le verifie pas",
                        compte, PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE)
                .isEqualTo(PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE);
    }
}
