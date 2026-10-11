package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>B8 de {@code nadia-prof} : l'enonce du paquet {@code cp-rapprocher-deux-oeuvres} est le meme sur
 * tous les items, et il tient en dix mots au plus.</b> Garde-fou propose par {@code theo-archi}
 * (amendement du 2026-10-11 de {@code avis-faisabilite.md} de s56), partiel : <b>le texte litteral n'est
 * pas fige</b>, parce que {@code nadia-prof} accepte <i>"un autre texte qui tient la barre"</i> et
 * qu'un texte fige refuserait un texte recevable. Que l'enonce demande la facon dont l'oeuvre est
 * faite, jamais seulement <i>"ce qui va avec"</i>, reste a elle, a l'audit.
 *
 * <p><b>Pourquoi ce fichier vit dans {@code bootstrap} et non dans le test de paquet d'{@code adapter-content}
 * que l'amendement nomme</b> : le comptage de mots est celui de {@link AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest}
 * (ADR-0101, decision 2 : ne pas recopier la convention de comptage), et il n'est visible que d'ici.
 *
 * <p>ADR-0013 : le perimetre est le fichier du paquet lu par le chargeur de production ; la mise a
 * l'epreuve mute en memoire des items reels du paquet.
 */
class LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest {

    /** Le fichier du paquet {@code cp-rapprocher-deux-oeuvres}. */
    private static final String PAQUET = "cp-rapprocher-deux-oeuvres.yaml";

    /** B8 : dix mots au plus, sous la convention de comptage de nadia-prof. */
    private static final int MOTS_AU_PLUS = 10;

    /** Un item du paquet : son identifiant dans le fichier et son enonce. */
    record ItemDEnonce(String id, String enonce) {
    }

    /**
     * Les items du paquet {@code cp-rapprocher-deux-oeuvres}, lus dans son fichier de contenu par le meme
     * balayage que les garde-fous d'ADR-0104 ({@code LIllustrationDeContenuResoutEtAucuneNeDortTest}), et
     * non dans le catalogue : le paquet peut etre hors catalogue (le retrait d'un jeu est l'absence de son
     * entree, ADR-0061) sans que ce garde-fou cesse de le juger.
     */
    private static List<ItemDEnonce> items() {
        List<ItemDEnonce> items = new ArrayList<>();
        for (LIllustrationDeContenuResoutEtAucuneNeDortTest.ItemDeclare item
                : LIllustrationDeContenuResoutEtAucuneNeDortTest.itemsDuDepot(
                        LesGardeFousSontMisALEpreuveTest.racineDuDepot())) {
            if (item.paquet().equals(PAQUET)) {
                items.add(new ItemDEnonce("item-" + items.size(), String.valueOf(item.champs().get("enonce"))));
            }
        }
        return items;
    }

    private static ItemDEnonce avecLEnonce(ItemDEnonce item, String enonce) {
        return new ItemDEnonce(item.id(), enonce);
    }

    /** Le juge : un seul enonce sur tous les items, de dix mots au plus. Fonction pure sur les items. */
    static List<String> griefs(List<ItemDEnonce> items) {
        List<String> griefs = new ArrayList<>();
        Set<String> enonces = new LinkedHashSet<>();
        items.forEach(item -> enonces.add(item.enonce()));
        if (enonces.size() > 1) {
            griefs.add("B8 : " + enonces.size() + " enonces differents " + enonces + " : le meme enonce sur"
                    + " tous les items, pour qu'aucun ne rende defendable le rapprochement par le sujet");
        }
        for (String enonce : enonces) {
            int mots = AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots(enonce).size();
            if (mots > MOTS_AU_PLUS) {
                griefs.add("B8 : l'enonce \"" + enonce + "\" compte " + mots + " mots, " + MOTS_AU_PLUS
                        + " au plus");
            }
        }
        return griefs;
    }

    @Test
    void lEnonceDuPaquetEstUniqueEtDeDixMotsAuPlus() {
        List<ItemDEnonce> items = items();

        assertThat(items).as("ADR-0003 : ce garde-fou ne prouve rien sans item").isNotEmpty();
        assertThat(griefs(items)).isEmpty();
        assertThat(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots(items.get(0).enonce()))
                .as("le comptage lit l'enonce du paquet, dont le point d'interrogation n'est pas un mot")
                .hasSizeBetween(1, MOTS_AU_PLUS);
    }

    @Test
    void unEnonceDifferentSurUnItemFaitEchouerLeGardeFou() {
        List<ItemDEnonce> items = new ArrayList<>(items());
        items.set(0, avecLEnonce(items.get(0), "Quelle œuvre va avec celle-ci ?"));

        assertThat(griefs(items)).anyMatch(grief -> grief.contains("enonces differents"));
    }

    /** La borne a sa limite : onze mots sur tous les items, un de plus que la barre, font echouer le garde-fou. */
    @Test
    void unEnonceDeOnzeMotsSurTousLesItemsFaitEchouerLeGardeFou() {
        String onzeMots = "Quelle œuvre est faite de la même façon que celle-ci, ici\u00a0?";
        List<ItemDEnonce> items = items().stream().map(item -> avecLEnonce(item, onzeMots)).toList();

        assertThat(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots(onzeMots)).hasSize(MOTS_AU_PLUS + 1);
        assertThat(griefs(items)).hasSize(1).allSatisfy(grief -> assertThat(grief).contains("mots"));
    }

    /** La borne a sa limite, de l'autre cote : dix mots sur tous les items tiennent la barre. */
    @Test
    void unEnonceDeDixMotsSurTousLesItemsNeFaitPasEchouerLeGardeFou() {
        String dixMots = "Quelle œuvre est faite de la même façon que celle-ci\u00a0?";
        List<ItemDEnonce> items = items().stream().map(item -> avecLEnonce(item, dixMots)).toList();

        assertThat(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots(dixMots)).hasSize(MOTS_AU_PLUS);
        assertThat(griefs(items)).isEmpty();
    }
}
