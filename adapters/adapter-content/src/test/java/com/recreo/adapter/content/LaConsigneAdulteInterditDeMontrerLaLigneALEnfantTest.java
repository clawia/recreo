package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>VOI-12, cinquieme interdit (nadia-prof, arbitrage prealable, addendum section 14.3 puis
 * section 17.1) : le refus du 2026-09-01 sur les cinq paquets a voix de s26 tenait a une phrase
 * absente - "Ne montrez pas cette ligne à l'enfant." ou une phrase qui dise la meme chose.</b>
 * Mesure sur les cinq, sans exception : un enfant qui lit le bloc adulte gagne sans la competence -
 * il voit "pin" a la fin de "sapin", "an" dans "manteau", le "p" de "pomme", le "ch" de "chat", et
 * sur la dictee il recopie (addendum, section 17.1).
 *
 * <p><b>Pourquoi cette phrase vit dans {@code consigneAdulte} (contenu) et non dans le gabarit.</b>
 * `theo-archi` a deja tranche ce point en ADR-0075 section 7 : "la phrase d'interdit est du
 * contenu, pas un libelle de gabarit" - une phrase ecrite dans le gabarit serait la meme pour tous
 * les paquets, ce qui serait faux par construction sur `VOI-12` point 3 quand les interdits
 * different. Ce cinquieme interdit est le seul cas ou l'identite entre paquets est correcte : il
 * vaut sur `VOI-13`, qui est elle-meme identique sur les cinq paquets, et nadia-prof mesure
 * elle-meme cette phrase unique comme TENUE (addendum, section 13.2, ligne "Les cinq").</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - toute entree du catalogue servi
 * qui declare {@code consigneAdulte}, jamais nommee ici par identifiant de jeu ; <b>derivation
 * totale</b> - chaque entree qui declare une consigneAdulte est jugee ; <b>mise a l'epreuve</b> -
 * une consigneAdulte privee du cinquieme interdit doit rendre la clause rouge.</p>
 */
class LaConsigneAdulteInterditDeMontrerLaLigneALEnfantTest {

    private static final Path CATALOGUE_SERVI = Path.of("src", "main", "resources", "catalogue.yaml");

    /** Phrase candidate rendue par nadia-prof, section 14.3, mesuree TENUE en section 13.2. */
    private static final String CINQUIEME_INTERDIT = "Ne montrez pas cette ligne à l'enfant.";

    /** VOI-12 point 4 (addendum, section 13.1) : jargon illisible sans le depot. */
    private static final List<String> MOTS_INTERDITS = List.of("grapheme", "phoneme", "digraphe",
            "scansion", "segmenter", "segmentation", "item", "paquet", "mecanique", "voi-", "seg-",
            "sai-");

    private static List<String> consignesAdultesDeclarees(Path catalogue) {
        try (InputStream flux = Files.newInputStream(catalogue)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            List<String> consignes = new ArrayList<>();
            for (Map<String, Object> jeu : jeux) {
                Object consigneAdulte = jeu.get("consigneAdulte");
                if (consigneAdulte != null) {
                    consignes.add(String.valueOf(consigneAdulte));
                }
            }
            return consignes;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static void verifierQueChaqueConsigneAdulteInterditDeMontrerLaLigne(List<String> consignesAdultes) {
        assertThat(consignesAdultes)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune consigneAdulte declaree")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (String consigneAdulte : consignesAdultes) {
            if (!consigneAdulte.contains(CINQUIEME_INTERDIT)) {
                defauts.add("\"" + consigneAdulte + "\" ne porte pas le cinquieme interdit de"
                        + " VOI-12 (\"" + CINQUIEME_INTERDIT + "\") - un enfant qui lit cette ligne"
                        + " gagnerait sans la competence (nadia-prof, addendum section 14.2 et"
                        + " 17.1)");
            }
        }
        assertThat(defauts)
                .as("VOI-12, cinquieme interdit : chaque consigneAdulte doit dire a l'adulte de ne"
                        + " pas montrer sa ligne a l'enfant")
                .isEmpty();
    }

    @Test
    void leCatalogueServiPorteLeCinquiemeInterditSurChaqueConsigneAdulte() {
        verifierQueChaqueConsigneAdulteInterditDeMontrerLaLigne(consignesAdultesDeclarees(CATALOGUE_SERVI));
    }

    /** ADR-0013 obligation 3 : la mise a l'epreuve, sur une consigneAdulte privee du cinquieme interdit. */
    @Test
    void uneConsigneAdulteSansLeCinquiemeInterditFaitEchouerLeGardeFou() {
        List<String> mutees = new ArrayList<>();
        for (String consigneAdulte : consignesAdultesDeclarees(CATALOGUE_SERVI)) {
            mutees.add(consigneAdulte.replace(CINQUIEME_INTERDIT, "").strip());
        }
        assertThatThrownBy(() -> verifierQueChaqueConsigneAdulteInterditDeMontrerLaLigne(mutees))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cinquieme interdit");
    }

    /**
     * VOI-12 point 4 (addendum, section 13.1) : "une phrase, un verbe d'action, des mots qu'un
     * parent emploie" - aucun jargon du depot, sur aucune consigneAdulte, pas seulement le
     * cinquieme interdit.
     */
    @Test
    void aucuneConsigneAdulteNeContientDeJargonIllisibleSansLeDepot() {
        List<String> defauts = new ArrayList<>();
        for (String consigneAdulte : consignesAdultesDeclarees(CATALOGUE_SERVI)) {
            String repliee = consigneAdulte.toLowerCase(Locale.ROOT);
            for (String mot : MOTS_INTERDITS) {
                if (repliee.contains(mot)) {
                    defauts.add("\"" + consigneAdulte + "\" contient le mot interdit \"" + mot
                            + "\" (VOI-12 point 4, addendum section 13.1)");
                }
            }
        }
        assertThat(defauts).isEmpty();
    }
}
