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
 * ADR-0045, clause 2 : ce que le catalogue peut se dire a lui-meme.
 *
 * <p>{@code ChaqueEntreeDuCatalogueChargeBienSonPaquetTest} derive le paquet attendu de
 * catalogue.yaml, donc ne peut pas juger le catalogue : une entree qui designe le mauvais paquet lui
 * est invisible (sa Javadoc le dit). Les cinq methodes figees qu'il remplace tenaient cette
 * propriete-la sur cinq des onze entrees. Ce garde-fou-ci en recolte la part qui se derive sans
 * recopier le catalogue dans du Java : ses <b>invariants internes</b>.</p>
 *
 * <p>Deux clauses, et chacune attrape un geste reel. <b>Le couple mecanique + paquet est unique</b> :
 * reassigner par erreur a un jeu le paquet d'un autre jeu (le cas nomme par sofia-sm - le paquet du
 * perimetre au jeu des mesures) cree un doublon, donc un rouge, sans qu'aucun nom de paquet ne soit
 * ecrit ici. <b>L'identifiant de jeu est unique</b> : une entree copiee-collee et modifiee a moitie
 * laisse deux jeux sous la meme cle, et {@code games()} en rend deux que rien ne distingue.</p>
 *
 * <p>C'est le couple et non le paquet seul : ADR-0012 fixe la longueur d'une partie comme un attribut
 * de <i>cette association mecanique + paquet</i>, donc un meme paquet rejoue par une seconde
 * mecanique est une addition legitime, que {@link #leMemePaquetSousDeuxMecaniquesPasseLeGardeFou()}
 * epingle du bon cote.</p>
 *
 * <p><b>Ce que ce garde-fou ne prouve pas</b> (ADR-0045, non-preuves 1 a 3) : l'unicite n'est pas la
 * justesse. Un catalogue entierement mal associe mais injectif est vert ; une entree renvoyee vers le
 * seul paquet hors catalogue reste injective, donc verte ; deux entrees qui echangent leurs paquets
 * restent injectives, donc vertes. La justesse de l'association appartient a nadia-prof et a la fiche
 * de conformite, pas a un test.</p>
 *
 * <p>Il lit le catalogue <b>la ou il vit</b>, sous src/main/resources, et non par le classpath : la
 * propriete jugee est celle du fichier servi, pas celle du chargeur. C'est aussi ce qui le rend
 * visible au recensement de mise a l'epreuve de {@code LesGardeFousSontMisALEpreuveTest}, dont le
 * perimetre est le litteral "src/main/" (ADR-0045, non-preuve 5).</p>
 */
class AucuneEntreeDuCatalogueNEnDoubleUneAutreTest {

    private static final Path CATALOGUE_SERVI = Path.of("src", "main", "resources", "catalogue.yaml");

    /** ADR-0013 obligation 3 : des catalogues fautifs que personne n'a jamais servis. */
    private static final Path CATALOGUES_FAUTIFS = Path.of("src", "test", "resources", "contreexemple",
            "AucuneEntreeDuCatalogueNEnDoubleUneAutreTest");

    private record Entree(String id, String mecanique, String paquet) {

        String association() {
            return mecanique + " + " + paquet;
        }
    }

    private static List<Entree> entrees(Path catalogue) {
        try (InputStream flux = Files.newInputStream(catalogue)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            List<Entree> lues = new ArrayList<>();
            for (Map<String, Object> jeu : jeux) {
                lues.add(new Entree(String.valueOf(jeu.get("id")),
                        String.valueOf(jeu.get("mecanique")),
                        String.valueOf(jeu.get("paquet"))));
            }
            return lues;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static void verifierQuAucuneEntreeNEnDoubleUneAutre(Path catalogue) {
        List<Entree> entrees = entrees(catalogue);

        assertThat(entrees)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucune entree de catalogue (%s)", catalogue)
                .isNotEmpty();

        assertThat(entrees.stream().map(Entree::association).toList())
                .as("ADR-0045 : deux entrees de %s jouent la meme mecanique sur le meme paquet. Si ce"
                        + " n'est pas voulu, une entree designe le paquet d'une autre - c'est une"
                        + " mauvaise association, et c'est le seul defaut de catalogue qu'un test"
                        + " puisse voir sans recopier le catalogue en Java. Si c'est voulu, c'est une"
                        + " decision de camille-po : le critere devient le triplet avec"
                        + " nombreDeManches, ou il se retire", catalogue)
                .doesNotHaveDuplicates();

        assertThat(entrees.stream().map(Entree::id).toList())
                .as("ADR-0045 : deux entrees de %s portent le meme identifiant de jeu. games() en rend"
                        + " deux que rien ne distingue, et la seconde est inatteignable en silence",
                        catalogue)
                .doesNotHaveDuplicates();
    }

    @Test
    void leCatalogueServiNeDoubleNiUneAssociationNiUnIdentifiant() {
        verifierQuAucuneEntreeNEnDoubleUneAutre(CATALOGUE_SERVI);
    }

    /**
     * Le geste exact que sofia-sm a nomme : le paquet du perimetre assigne au jeu des mesures. Dans le
     * catalogue fautif, l'entree des mesures designe cm1-perimetre, que l'entree du perimetre designe
     * deja.
     */
    @Test
    void unCatalogueQuiAssigneAUnJeuLePaquetDUnAutreFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucuneEntreeNEnDoubleUneAutre(
                CATALOGUES_FAUTIFS.resolve("catalogue.yaml")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot + cm1-perimetre");
    }

    @Test
    void unCatalogueQuiDoubleUnIdentifiantDeJeuFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucuneEntreeNEnDoubleUneAutre(
                CATALOGUES_FAUTIFS.resolve(Path.of("identifiant-double", "catalogue.yaml"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot-perimetre-cm1");
    }

    /**
     * L'autre borne, et elle compte autant que la morsure : un meme paquet rejoue par une seconde
     * mecanique passe. Un garde-fou ecrit sur le paquet seul refuserait ce catalogue-la, qui est
     * exactement la promesse d'ADR-0004 et d'ADR-0012 - une mecanique reutilisable sur n'importe quel
     * contenu, et une longueur de partie par association.
     */
    @Test
    void leMemePaquetSousDeuxMecaniquesPasseLeGardeFou() {
        verifierQuAucuneEntreeNEnDoubleUneAutre(
                CATALOGUES_FAUTIFS.resolve(Path.of("corrige", "catalogue.yaml")));
    }
}
