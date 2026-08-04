package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.mechanic.MechanicId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0007 : le test de schema exige qu'un paquet incomplet, dont le type ou le schema est
 * inconnu, produise une erreur qui nomme le fichier et le champ.
 */
class YamlContentPackLoaderTest {

    @Test
    void chargeUnPaquetEnumereEtUnPaquetCombinatoireDepuisLeCatalogue() {
        List<GameDefinition> jeux = new YamlContentPackLoader("/fixtures/valide").games();

        assertThat(jeux).hasSize(2);

        GameDefinition jeuQuestion = jeu(jeux, "jeu-question");
        assertThat(jeuQuestion.titre()).isEqualTo("Jeu question");
        assertThat(jeuQuestion.mecanique()).isEqualTo(new MechanicId("bandit-manchot"));
        assertThat(jeuQuestion.descripteur().matiere()).isEqualTo("Mathematiques");

        ContentSource contenuQuestion = jeuQuestion.contenu();
        Optional<Item> item = contenuQuestion.next(borne -> 0, Set.<ItemId>of());
        assertThat(item).isPresent();
        assertThat(((Question) item.orElseThrow()).enonce()).isNotBlank();

        GameDefinition jeuAdditions = jeu(jeux, "jeu-additions");
        Optional<Item> itemAdditions = jeuAdditions.contenu().next(borne -> 0, Set.<ItemId>of());
        assertThat(itemAdditions).isPresent();
    }

    @Test
    void unChampObligatoireManquantNommeLeFichierEtLeChamp() {
        assertThatThrownBy(() -> new YamlContentPackLoader("/fixtures/champ-manquant").games())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("paquet-incomplet.yaml")
                .hasMessageContaining("competence");
    }

    @Test
    void unTypeInconnuNommeLeFichierEtLeType() {
        assertThatThrownBy(() -> new YamlContentPackLoader("/fixtures/type-inconnu").games())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("paquet-type-inconnu.yaml")
                .hasMessageContaining("puzzle");
    }

    @Test
    void unSchemaInconnuNommeLeFichierEtLeSchema() {
        assertThatThrownBy(() -> new YamlContentPackLoader("/fixtures/schema-inconnu").games())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("paquet-schema-inconnu.yaml")
                .hasMessageContaining("2");
    }

    private static GameDefinition jeu(List<GameDefinition> jeux, String id) {
        return jeux.stream().filter(candidat -> candidat.id().equals(id)).findFirst().orElseThrow();
    }
}
