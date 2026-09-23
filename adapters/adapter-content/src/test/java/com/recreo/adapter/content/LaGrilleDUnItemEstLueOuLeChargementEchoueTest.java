package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.TableauSupport;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0099, decision 1 : la cle 'tableauSupport' est lue par la fabrique du type 'question', et ce
 * bloc-la est lu <b>strictement</b> - une sous-cle inconnue, une sous-cle manquante ou une forme
 * inattendue font echouer le chargement en nommant le defaut, jamais en ignorant la cle.
 *
 * <p><b>Ce que cette strictesse paie, et pourquoi elle est locale a ce bloc.</b> Le balayage de cle
 * morte d'ADR-0070 ne descend dans un item que par une <i>liste de correspondances</i> ; une
 * correspondance posee directement sur un item lui est invisible, et ce n'est pas un oubli - c'est
 * ce qui empeche de juger les cles d'auteur de 'relationsDistracteurs' comme du schema. Une
 * sous-cle de 'tableauSupport' mal orthographiee tombait donc dans ce trou : ni lue, ni signalee.
 * Elle est ici refusee a la source.</p>
 */
class LaGrilleDUnItemEstLueOuLeChargementEchoueTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Sciences et technologie", "CM1", "Le vivant", "Extraire");

    private static final QuestionContentSourceFactory FABRIQUE = new QuestionContentSourceFactory();

    private static Map<String, Object> corpsAvec(Object tableauSupport) {
        return Map.of("items", List.of(Map.of(
                "enonce", "Quelle est la hauteur du chene ?",
                "bonneReponse", "25 metres",
                "distracteurs", List.of("30 metres", "18 metres"),
                "tableauSupport", tableauSupport)));
    }

    private static Map<String, Object> grilleBrute() {
        return Map.of("enTete", List.of("Arbre", "Hauteur"),
                "lignes", List.of(List.of("chene", "25 metres"), List.of("hetre", "30 metres")));
    }

    private static Question premierItem(Map<String, Object> corps) {
        ContentSource source = FABRIQUE.creer(DESCRIPTEUR, corps);
        Optional<Item> tire = source.next(borne -> 0, Set.of());
        return (Question) tire.orElseThrow();
    }

    @Test
    void uneGrilleDeclareeEstChargeeAvecSonEnTeteEtSesLignes() {
        Question item = premierItem(corpsAvec(grilleBrute()));

        TableauSupport grille = item.tableauSupport().orElseThrow();
        assertThat(grille.enTete()).containsExactly("Arbre", "Hauteur");
        assertThat(grille.lignes())
                .containsExactly(List.of("chene", "25 metres"), List.of("hetre", "30 metres"));
    }

    @Test
    void unItemSansGrilleNEnPorteAucuneEtNeRemplitAucuneValeurFactice() {
        Question item = premierItem(Map.of("items", List.of(Map.of(
                "enonce", "Combien font 2 + 2 ?",
                "bonneReponse", "4",
                "distracteurs", List.of("3", "5")))));

        assertThat(item.tableauSupport()).isEmpty();
    }

    @Test
    void uneSousCleInconnueDuBlocFaitEchouerLeChargement() {
        assertThatThrownBy(() -> premierItem(corpsAvec(Map.of(
                "enTete", List.of("Arbre", "Hauteur"),
                "ligne", List.of(List.of("chene", "25 metres"))))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("sous-cle inconnue 'ligne'");
    }

    @Test
    void unBlocSansSaListeDeLignesFaitEchouerLeChargement() {
        assertThatThrownBy(() -> premierItem(corpsAvec(Map.of("enTete", List.of("Arbre", "Hauteur")))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("doit porter une liste 'lignes'");
    }

    @Test
    void unBlocQuiNEstPasUneCorrespondanceFaitEchouerLeChargement() {
        assertThatThrownBy(() -> premierItem(corpsAvec("Arbre | Hauteur")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("doit etre une correspondance");
    }

    /**
     * L'invariant du domaine remonte tel quel jusqu'au chargement : une ligne qui ne porte pas
     * autant de cellules que son en-tete n'entre pas au produit (ADR-0099, decision 2).
     */
    @Test
    void uneLigneQuiNePortePasLaLargeurDeSonEnTeteFaitEchouerLeChargement() {
        assertThatThrownBy(() -> premierItem(corpsAvec(Map.of(
                "enTete", List.of("Arbre", "Hauteur"),
                "lignes", List.of(List.of("chene", "25 metres", "en trop"))))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3 cellule(s) pour 2 colonnes");
    }

    /** ADR-0007 : le message d'un paquet fautif nomme l'item, pas seulement le defaut. */
    @Test
    void leMessageNommeLaPositionDeLItemFautif() {
        Map<String, Object> corps = Map.of("items", List.of(
                Map.of("enonce", "sain", "bonneReponse", "a", "distracteurs", List.of("b")),
                Map.of("enonce", "fautif", "bonneReponse", "a", "distracteurs", List.of("b"),
                        "tableauSupport", Map.of("enTetes", List.of("x", "y")))));

        assertThatThrownBy(() -> FABRIQUE.creer(DESCRIPTEUR, corps))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("items[1]");
    }

    /** Un item construit hors de tout fichier porte le meme invariant (ADR-0099, decision 2). */
    @Test
    void unItemConstruitEnMemoirePorteLeMemeInvariant() {
        assertThatThrownBy(() -> new Question(new ItemId("item-0"), "e", "a", List.of("b"),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(new TableauSupport(List.of("A", "B"), List.of(List.of("seule"))))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
