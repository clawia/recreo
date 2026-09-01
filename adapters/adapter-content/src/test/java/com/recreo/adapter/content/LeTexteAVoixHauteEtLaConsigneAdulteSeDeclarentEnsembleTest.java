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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>ADR-0075 section 7, trou trouve par `lea-dev` en cablant `.bloc-adulte`</b> (`s26-lea`,
 * `a4b2272`) : le gabarit rend un seul objet visuel qui porte le stimulus
 * ({@code texteAVoixHaute}, sur l'item) et la mise en garde ({@code consigneAdulte}, sur l'entree
 * de catalogue qui sert le paquet - meme patron que {@code consigne}, ADR-0052), derriere
 * <b>deux {@code th:if} independants</b>. Rien ne garde leur appariement : un paquet qui poserait
 * l'un sans l'autre rendrait un {@code .bloc-adulte} incomplet en silence - <b>`VOI-12`, l'unique
 * exigence bloquante de `nadia-prof`, tomberait sans qu'aucun test ne rougisse.</b>
 *
 * <p><b>La regle</b> : sur une entree de catalogue, {@code consigneAdulte} est declaree si et
 * seulement si au moins un item de son paquet declare {@code texteAVoixHaute}. C'est la meme famille
 * que la clause 5 d'ADR-0073 (illustration / texte alternatif, qui se gardent l'un l'autre), transposee
 * d'un item a la paire item/entree parce que {@code consigneAdulte} n'est pas une composante d'item -
 * elle est invariante sur tout le paquet, au meme titre que {@code consigne} (ADR-0075 section 7).</p>
 *
 * <p>La lecture est <b>brute</b>, sur le patron de {@code AucuneEntreeDuCatalogueNEnDoubleUneAutreTest}
 * : ce garde-fou ne construit aucun {@code Item} et ne passe pas par le chargeur reel, parce que la
 * seule chose qu'il juge est la <b>presence d'une cle</b>, jamais une valeur interpretee. Une lecture
 * plus lourde ne prouverait rien de plus sur cette propriete-la.</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - les entrees viennent du catalogue
 * servi, jamais d'une liste ecrite ici ; <b>derivation totale</b> - chaque entree est jugee, qu'elle
 * declare l'un, l'autre, les deux ou aucun ; <b>mise a l'epreuve</b> - un paquet fautif par sens de la
 * fuite, chacun corrige par l'ajout ou le retrait de la seule cle qui manque.</p>
 */
class LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest {

    private static final Path CATALOGUE_SERVI = Path.of("src", "main", "resources", "catalogue.yaml");

    private static final Path RACINE_DE_PRODUCTION = Path.of("src", "main", "resources");

    /** ADR-0013 : les catalogues et paquets fautifs de ce garde-fou, jamais servis a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest");

    private record Entree(String id, String paquet, boolean consigneAdulteDeclaree) {
    }

    private static Map<String, Object> lireYaml(Path fichier) {
        try (InputStream flux = Files.newInputStream(fichier)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return corps;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Entree> entreesDuCatalogue(Path catalogue) {
        Map<String, Object> corps = lireYaml(catalogue);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
        List<Entree> entrees = new ArrayList<>();
        for (Map<String, Object> jeu : jeux) {
            entrees.add(new Entree(String.valueOf(jeu.get("id")), String.valueOf(jeu.get("paquet")),
                    jeu.get("consigneAdulte") != null));
        }
        return entrees;
    }

    /**
     * Vrai des qu'au moins un item du paquet declare {@code texteAVoixHaute} - lecture brute des
     * cles du YAML, jamais du chargeur reel : la seule question posee est "cette cle est-elle
     * ecrite", pas "que vaut l'item construit".
     */
    private static boolean paquetDeclareUneVoix(Path racineDeContenu, String paquet) {
        Path fichier = racineDeContenu.resolve("contenu").resolve(paquet + ".yaml");
        Map<String, Object> corps = lireYaml(fichier);
        if (!(corps.get("items") instanceof List<?> items)) {
            return false;
        }
        for (Object itemBrut : items) {
            if (itemBrut instanceof Map<?, ?> item && item.get("texteAVoixHaute") != null) {
                return true;
            }
        }
        return false;
    }

    static void verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(Path racine) {
        List<Entree> entrees = entreesDuCatalogue(racine.resolve("catalogue.yaml"));
        assertThat(entrees)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune entree de catalogue sous %s",
                        racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (Entree entree : entrees) {
            boolean voixDeclaree = paquetDeclareUneVoix(racine, entree.paquet());
            if (voixDeclaree && !entree.consigneAdulteDeclaree()) {
                defauts.add("jeu " + entree.id() + " (paquet " + entree.paquet() + ") : au moins un"
                        + " item declare texteAVoixHaute, mais l'entree de catalogue ne declare aucune"
                        + " consigneAdulte - .bloc-adulte serait incomplet, VOI-12 tomberait sans que"
                        + " rien ne rougisse (nadia-prof, section 7.3 : \"sans VOI-12 je refuserai les"
                        + " paquets a voix\")");
            } else if (!voixDeclaree && entree.consigneAdulteDeclaree()) {
                defauts.add("jeu " + entree.id() + " (paquet " + entree.paquet() + ") : l'entree de"
                        + " catalogue declare consigneAdulte, mais aucun item de son paquet ne declare"
                        + " texteAVoixHaute - une mise en garde sans rien a garder, morte en silence");
            }
        }

        assertThat(defauts)
                .as("ADR-0075 section 7 : consigneAdulte et texteAVoixHaute se declarent ensemble ou"
                        + " pas du tout, par paquet")
                .isEmpty();
    }

    @Test
    void leCatalogueServiDeclareEnsembleLaVoixEtLaConsigneAdulte() {
        verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(RACINE_DE_PRODUCTION);
    }

    @Test
    void unItemAVoixSansConsigneAdulteFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(
                CONTRE_EXEMPLE.resolve("voix-sans-consigne-adulte")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot-voix-sans-consigne-adulte")
                .hasMessageContaining("VOI-12");
    }

    @Test
    void ajouterLaConsigneAdulteManquanteRendLeGardeFouVert() {
        verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(
                CONTRE_EXEMPLE.resolve(Path.of("voix-sans-consigne-adulte", "corrige")));
    }

    @Test
    void uneConsigneAdulteSansAucunItemAVoixFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(
                CONTRE_EXEMPLE.resolve("consigne-adulte-sans-voix")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot-consigne-adulte-sans-voix")
                .hasMessageContaining("morte en silence");
    }

    @Test
    void retirerLaConsigneAdulteOrphelineRendLeGardeFouVert() {
        verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(
                CONTRE_EXEMPLE.resolve(Path.of("consigne-adulte-sans-voix", "corrige")));
    }

    @Test
    void unPaquetAVoixAvecSaConsigneAdulteEtUnPaquetSansAucuneVoixPassentTousDeuxLeGardeFou() {
        verifierQueLaVoixEtLaConsigneAdulteSeDeclarentEnsemble(CONTRE_EXEMPLE.resolve("sain"));
    }
}
