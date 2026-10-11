package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S55-ART-CP-10-PAQUET, le paquet {@code cp-rapprocher-deux-oeuvres}, ligne {@code ART-CP-10} d'arts
 * plastiques (CP).</b> Conditions de {@code nadia-prof}
 * ({@code ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md}, section 1), gardees par les
 * garde-fous que {@code theo-archi} propose dans l'amendement du 2026-10-11 de
 * {@code avis-faisabilite.md} de s56. Ce sont des tests de contenu, pas des regles d'architecture ; un
 * garde-fou par condition, un commit par garde-fou.
 *
 * <p>Les trois invariants de forme d'ADR-0104 (decision 5) sont juges ailleurs
 * ({@code UnItemAPropositionsEnImageTientSesTroisInvariantsTest}). Ce fichier ne juge <b>aucun
 * texte</b> au-dela de ce que dit chaque propriete, ni la plausibilite d'un distracteur, ni la regle 2 de
 * {@code nadia-prof} (le sujet ne suffit jamais) : elles ne se lisent que sur l'image, et elles sont a
 * elle seule, a l'audit.
 *
 * <p>ADR-0013 : le perimetre est le fichier du paquet lu par le chargeur de production, jamais une liste
 * d'items ; la mise a l'epreuve mute en memoire les items reels du paquet, jamais une fixture de ma main.
 */
class PaquetCpRapprocherDeuxOeuvresTest {

    private static final String PAQUET = "cp-rapprocher-deux-oeuvres";

    /** B2 : le texte alternatif de la reference est celui que {@code cp-decrire-une-oeuvre} sert deja. */
    private static final String PAQUET_DE_REFERENCE_DU_TEXTE_ALTERNATIF = "cp-decrire-une-oeuvre";

    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static List<Question> items() {
        return tousLesItems(new YamlContentPackLoader("").chargerPaquet(PAQUET));
    }

    /** Un item reel, ses champs conserves, dont l'illustration, la bonne reponse ou le texte alternatif change. */
    private static Question avec(Question item, Optional<String> illustration, String bonneReponse,
            List<String> distracteurs, Optional<String> texteAlternatif) {
        return new Question(item.id(), item.enonce(), bonneReponse, distracteurs, item.indice(),
                item.explication(), item.partDesignee(), item.texteSupport(), illustration, texteAlternatif,
                item.texteAVoixHaute(), item.tableauSupport());
    }

    private static Question avecLeTexteAlternatif(Question item, String texteAlternatif) {
        return avec(item, item.illustration(), item.bonneReponse(), item.distracteurs(),
                Optional.of(texteAlternatif));
    }

    // ------------------------------------------------------------------------------------------
    // B2 : le texte alternatif de la reference
    // ------------------------------------------------------------------------------------------

    /**
     * Le texte alternatif que {@code cp-decrire-une-oeuvre} sert, lu dans son fichier et non recopie. Une
     * seule valeur sur les huit items de ce paquet : sans quoi la reference n'a pas un texte
     * alternatif mais huit, et la lecture du paquet voisin ne dirait rien.
     */
    private static String texteAlternatifDuPaquetVoisin() {
        Set<String> valeurs = new LinkedHashSet<>();
        for (Question item : tousLesItems(
                new YamlContentPackLoader("").chargerPaquet(PAQUET_DE_REFERENCE_DU_TEXTE_ALTERNATIF))) {
            valeurs.add(item.illustrationTexteAlternatif().orElse(""));
        }
        assertThat(valeurs)
                .as("%s doit servir un seul texte alternatif, celui que B2 reprend",
                        PAQUET_DE_REFERENCE_DU_TEXTE_ALTERNATIF)
                .hasSize(1);
        return valeurs.iterator().next();
    }

    /**
     * B2, le juge : chaque item declare, comme texte alternatif de sa reference, le texte du paquet
     * voisin, a la lettre. Ce qui est interdit reellement : un texte alternatif qui nomme le sujet d'une
     * reference, donc la paire ({@code X1}, {@code CON-5}) - l'egalite a un texte qui ne nomme rien
     * l'exclut sans lire un seul mot.
     */
    static List<String> griefsB2(List<Question> items, String texteAttendu) {
        List<String> griefs = new ArrayList<>();
        for (Question item : items) {
            String texte = item.illustrationTexteAlternatif().orElse("");
            if (!texte.equals(texteAttendu)) {
                griefs.add("B2 : l'item de reference " + item.illustration().orElse("?")
                        + " declare le texte alternatif \"" + texte + "\", et non celui de "
                        + PAQUET_DE_REFERENCE_DU_TEXTE_ALTERNATIF + " \"" + texteAttendu + "\" : un texte"
                        + " alternatif qui change d'un item a l'autre peut nommer la reference, donc la paire");
            }
        }
        return griefs;
    }

    @Test
    void b2LeTexteAlternatifDeChaqueReferenceEstCeluiDuPaquetVoisin() {
        List<Question> items = items();

        assertThat(items).as("ADR-0003 : ce garde-fou ne prouve rien sans item").isNotEmpty();
        assertThat(griefsB2(items, texteAlternatifDuPaquetVoisin())).isEmpty();
    }

    @Test
    void b2UnTexteAlternatifQuiNommeLeSujetFaitEchouerLeGardeFou() {
        List<Question> items = new ArrayList<>(items());
        items.set(0, avecLeTexteAlternatif(items.get(0), "Une chambre"));

        assertThat(griefsB2(items, texteAlternatifDuPaquetVoisin()))
                .hasSize(1)
                .allSatisfy(grief -> assertThat(grief).contains("B2").contains("Une chambre"));
    }

    // ------------------------------------------------------------------------------------------
    // B3 : references, couples et paires dans les deux sens
    // ------------------------------------------------------------------------------------------

    private static String reference(Question item) {
        return item.illustration().orElse("");
    }

    /**
     * B3, le juge : les references sont deux a deux distinctes, les couples (reference, bonne
     * oeuvre) aussi, et l'ensemble des couples est symetrique - chaque paire se joue dans les deux sens.
     * Derive du fichier, sans liste de paires. Ce qui est interdit reellement : un couple qui revient,
     * meme avec d'autres distracteurs - la seconde fois, l'enfant retrouverait la paire de memoire au
     * lieu de regarder - et une paire jouee dans un seul sens.
     */
    static List<String> griefsB3(List<Question> items) {
        List<String> griefs = new ArrayList<>();
        Set<String> references = new HashSet<>();
        Set<String> couples = new HashSet<>();
        for (Question item : items) {
            if (!references.add(reference(item))) {
                griefs.add("B3 : la reference " + reference(item) + " est montree par plus d'un item");
            }
            String couple = reference(item) + " -> " + item.bonneReponse();
            if (!couples.add(couple)) {
                griefs.add("B3 : le couple " + couple + " revient : l'enfant le retrouverait de memoire");
            }
        }
        for (Question item : items) {
            String inverse = item.bonneReponse() + " -> " + reference(item);
            if (!couples.contains(inverse)) {
                griefs.add("B3 : le couple " + reference(item) + " -> " + item.bonneReponse()
                        + " n'a pas son sens inverse " + inverse + " : la paire ne se joue que dans un sens");
            }
        }
        return griefs;
    }

    @Test
    void b3LesReferencesLesCouplesEtLesPairesTiennent() {
        assertThat(griefsB3(items())).isEmpty();
    }

    /** M7 de lea-dev, restee verte avant ce garde-fou : l'item 2 rendu identique a l'item 1. */
    @Test
    void unItemRenduIdentiqueAUnAutreFaitEchouerLeGardeFou() {
        List<Question> items = new ArrayList<>(items());
        Question premier = items.get(0);
        Question second = items.get(1);
        items.set(1, avec(second, premier.illustration(), premier.bonneReponse(), second.distracteurs(),
                second.illustrationTexteAlternatif()));

        assertThat(griefsB3(items))
                .anyMatch(grief -> grief.contains("est montree par plus d'un item"))
                .anyMatch(grief -> grief.contains("revient"));
    }

    @Test
    void uneBonneOeuvreEchangeeContreUnDistracteurRompLaSymetrieDesPaires() {
        List<Question> items = new ArrayList<>(items());
        Question item = items.get(0);
        String distracteur = item.distracteurs().get(0);
        List<String> distracteurs = new ArrayList<>(item.distracteurs());
        distracteurs.set(0, item.bonneReponse());
        items.set(0, avec(item, item.illustration(), distracteur, distracteurs,
                item.illustrationTexteAlternatif()));

        assertThat(griefsB3(items)).anyMatch(grief -> grief.contains("n'a pas son sens inverse"));
    }

    @Test
    void uneSeuleMoitieDeLaPaireFaitEchouerLeGardeFou() {
        List<Question> items = new ArrayList<>(items());
        items.remove(0);

        assertThat(griefsB3(items)).anyMatch(grief -> grief.contains("n'a pas son sens inverse"));
    }
}
