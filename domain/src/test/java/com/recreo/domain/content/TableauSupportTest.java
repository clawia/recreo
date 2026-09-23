package com.recreo.domain.content;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0099, decision 2 : la largeur attendue d'une ligne est celle de l'en-tete du tableau juge,
 * jamais un nombre ecrit dans une regle. C'est la transposition d'ADR-0087 du markdown du depot au
 * contenu servi, avec une difference qui est ecrite dans l'ADR : la population de grilles servies
 * etant nulle au jour de cette decision, l'egalite est exigee des deux cotes, sans dette ni
 * cliquet - un surplus n'est affiche par aucun rendu, un deficit laisse une valeur sans son
 * intitule de colonne.
 */
class TableauSupportTest {

    private static final List<String> EN_TETE = List.of("Arbre", "Hauteur");

    @Test
    void uneGrilleBienFormeeSeConstruitEtGardeSesRangeesDansLOrdre() {
        TableauSupport grille = new TableauSupport(EN_TETE,
                List.of(List.of("chene", "25 metres"), List.of("hetre", "30 metres")));

        assertThat(grille.enTete()).containsExactly("Arbre", "Hauteur");
        assertThat(grille.lignes())
                .containsExactly(List.of("chene", "25 metres"), List.of("hetre", "30 metres"));
    }

    @Test
    void uneLigneQuiPortePlusDeCellulesQueSonEnTeteEstRefusee() {
        assertThatThrownBy(() -> new TableauSupport(EN_TETE,
                List.of(List.of("chene", "25 metres", "invisible"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3 cellule(s) pour 2 colonnes");
    }

    @Test
    void uneLigneQuiPorteMoinsDeCellulesQueSonEnTeteEstRefusee() {
        assertThatThrownBy(() -> new TableauSupport(EN_TETE, List.of(List.of("chene"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 cellule(s) pour 2 colonnes");
    }

    @Test
    void laLargeurAttendueEstCelleDeLEnTeteJugeEtNonUnNombreFige() {
        List<String> troisColonnes = List.of("Arbre", "Hauteur", "Age");

        assertThat(new TableauSupport(troisColonnes,
                List.of(List.of("chene", "25 metres", "120 ans"))).lignes()).hasSize(1);
        assertThatThrownBy(() -> new TableauSupport(troisColonnes,
                List.of(List.of("chene", "25 metres"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void uneGrilleAUneSeuleColonneEstRefusee() {
        assertThatThrownBy(() -> new TableauSupport(List.of("Arbre"), List.of(List.of("chene"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("au moins 2 colonnes");
    }

    @Test
    void uneGrilleSansAucuneLigneDeCorpsEstRefusee() {
        assertThatThrownBy(() -> new TableauSupport(EN_TETE, List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("au moins une ligne de corps");
    }

    @Test
    void uneCelluleVideEstRefuseeDansLEnTeteCommeDansLeCorps() {
        assertThatThrownBy(() -> new TableauSupport(List.of("Arbre", "  "),
                List.of(List.of("chene", "25 metres"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("l'en-tete porte une cellule vide en colonne 2");

        assertThatThrownBy(() -> new TableauSupport(EN_TETE, List.of(List.of("chene", ""))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("la ligne 1 porte une cellule vide en colonne 2");
    }

    /**
     * Une cellule absente est refusee comme une cellule vide, et par la meme clause : c'est la
     * forme qu'une valeur manquante prend quand elle vient d'un fichier plutot que d'un appel.
     */
    @Test
    void uneCelluleAbsenteEstRefusee() {
        List<String> ligne = Arrays.asList("chene", null);

        assertThatThrownBy(() -> new TableauSupport(EN_TETE, List.of(ligne)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("cellule vide");
    }

    /** Une grille construite reste ce qu'elle etait : la liste d'origine ne la modifie plus. */
    @Test
    void uneGrilleConstruiteNeSuitPasLesListesQuiLOntFabriquee() {
        List<List<String>> lignes = new ArrayList<>();
        lignes.add(new ArrayList<>(List.of("chene", "25 metres")));
        TableauSupport grille = new TableauSupport(new ArrayList<>(EN_TETE), lignes);

        lignes.clear();

        assertThat(grille.lignes()).containsExactly(List.of("chene", "25 metres"));
    }
}
