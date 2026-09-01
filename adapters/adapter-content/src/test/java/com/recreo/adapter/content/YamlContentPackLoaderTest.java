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
        assertThat(jeuQuestion.nombreDeManches())
                .as("ADR-0012 : la valeur ecrite en YAML est bien celle rendue")
                .isEqualTo(10);

        ContentSource contenuQuestion = jeuQuestion.contenu();
        Optional<Item> item = contenuQuestion.next(borne -> 0, Set.<ItemId>of());
        assertThat(item).isPresent();
        assertThat(((Question) item.orElseThrow()).enonce()).isNotBlank();
        assertThat(contenuQuestion.indicesNommentLaReponse())
                .as("M21 : 'indicesNommentLaReponse: true' du paquet doit atteindre le ContentSource")
                .isTrue();

        GameDefinition jeuAdditions = jeu(jeux, "jeu-additions");
        Optional<Item> itemAdditions = jeuAdditions.contenu().next(borne -> 0, Set.<ItemId>of());
        assertThat(itemAdditions).isPresent();
        assertThat(((Question) itemAdditions.orElseThrow()).indice())
                .as("S3-3 (DEC-0005) : l'indice suit le texte du paquet, pas une constante Java")
                .contains("Astuce de la fixture, jamais affichee a un enfant.");
        assertThat(jeuAdditions.contenu().indicesNommentLaReponse())
                .as("M21 : sans la cle, le paquet ne declare rien - faux par defaut")
                .isFalse();
    }

    /**
     * M21 (arbitrage de nadia-prof du 2026-08-13) : le paquet reel derriere "Le bon verbe" nomme la
     * conjugaison attendue dans ses vingt-cinq indices sur vingt-cinq ("c'est 'finira'"). C'est le
     * cas reel qui a fait trancher M21 ; ce paquet doit le declarer pour que la mecanique ne montre
     * plus jamais ces indices.
     */
    @Test
    void lePaquetConjugaisonPresentDeclareQueSesIndicesNommentLaReponse() {
        List<GameDefinition> jeux = new YamlContentPackLoader().games();

        GameDefinition jeu = jeu(jeux, "bandit-manchot-conjugaison-present-cm1");

        assertThat(jeu.contenu().indicesNommentLaReponse()).isTrue();
    }

    /**
     * S3-3 (DEC-0005), 3e critere : le texte reellement affiche a l'enfant par le paquet reel
     * livre en production reste identique, caractere pour caractere, a celui de la constante Java
     * qu'il remplace.
     */
    @Test
    void lIndiceDuPaquetReelDesAdditionsCPResteIdentiqueApresSonDeplacementVersLeYaml() {
        List<GameDefinition> jeux = new YamlContentPackLoader().games();

        GameDefinition jeu = jeu(jeux, "bandit-manchot-additions-cp");
        Question question = (Question) jeu.contenu().next(borne -> 0, Set.<ItemId>of()).orElseThrow();

        assertThat(question.indice()).contains("Compte sur tes doigts.");
    }

    /**
     * ADR-0073 : les deux cles de l'illustration traversent le chargeur jusqu'a l'item, telles
     * qu'elles sont ecrites - un identifiant, jamais un chemin, jamais une extension. Sans cette
     * lecture, une cle ecrite par un auteur de contenu serait ignoree en silence et n'atteindrait
     * jamais l'ecran : c'est exactement l'ecart muet que la Javadoc de
     * {@link QuestionContentSourceFactory} nomme.
     */
    @Test
    void lesDeuxClesDeLIllustrationTraversentLeChargeurJusquALItem() {
        List<GameDefinition> jeux = new YamlContentPackLoader("/fixtures/illustre").games();
        ContentSource contenu = jeu(jeux, "jeu-illustre").contenu();

        Question premier = (Question) contenu.next(borne -> 0, Set.<ItemId>of()).orElseThrow();
        assertThat(premier.illustration())
                .as("l'identifiant declare par l'item arrive tel quel, sans repertoire ni extension")
                .contains("fixture-forme-a");
        assertThat(premier.illustrationTexteAlternatif())
                .as("ADR-0073 clause 5 : le texte alternatif declare par le meme item arrive avec lui")
                .contains("Une forme fermee a quatre cotes egaux.");

        Question second = (Question) contenu.next(borne -> 0, Set.of(premier.id())).orElseThrow();
        assertThat(second.illustration())
                .as("un item qui ne montre rien ne porte aucune valeur factice")
                .isEmpty();
        assertThat(second.illustrationTexteAlternatif()).isEmpty();
    }

    /**
     * ADR-0075 : la cle 'texteAVoixHaute' traverse le chargeur jusqu'a l'item, telle qu'elle est
     * ecrite - sur le meme patron que les deux cles de l'illustration ci-dessus. Sans cette lecture,
     * le texte que l'adulte doit lire a voix haute serait ignore en silence et n'atteindrait jamais
     * l'ecran.
     */
    @Test
    void leTexteAVoixHauteTraverseLeChargeurJusquALItem() {
        List<GameDefinition> jeux = new YamlContentPackLoader("/fixtures/voix").games();
        ContentSource contenu = jeu(jeux, "jeu-a-voix").contenu();

        Question premier = (Question) contenu.next(borne -> 0, Set.<ItemId>of()).orElseThrow();
        assertThat(premier.texteAVoixHaute())
                .as("le texte declare par l'item arrive tel quel")
                .contains("Le mot est chapeau.");

        Question second = (Question) contenu.next(borne -> 0, Set.of(premier.id())).orElseThrow();
        assertThat(second.texteAVoixHaute())
                .as("un item qui ne s'appuie sur aucune voix ne porte aucune valeur factice")
                .isEmpty();
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

    /** ADR-0012 : champ de catalogue obligatoire, comme tout champ du schema (ADR-0007). */
    @Test
    void unNombreDeManchesManquantNommeLeFichierEtLeChamp() {
        assertThatThrownBy(() -> new YamlContentPackLoader("/fixtures/nombre-de-manches-manquant").games())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("catalogue.yaml")
                .hasMessageContaining("nombreDeManches");
    }

    private static GameDefinition jeu(List<GameDefinition> jeux, String id) {
        return jeux.stream().filter(candidat -> candidat.id().equals(id)).findFirst().orElseThrow();
    }
}
