package com.recreo.bootstrap;

import com.recreo.bootstrap.config.QuizContentPack;
import com.recreo.bootstrap.config.QuizPackLoader;
import com.recreo.domain.content.impl.QuizGenerator.Question;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Vérifie l'externalisation du contenu quiz (ADR-0009) : les 7 fichiers YAML éditables
 * (content/quiz/*.yml) se chargent, sont bien formés, et la validation au chargement
 * rejette un fichier incohérent (le boot échouerait plutôt que de servir un jeu cassé).
 */
class QuizPackLoaderTest {

    private final QuizPackLoader loader = new QuizPackLoader();

    private Map<String, QuizContentPack> packsById() {
        return loader.loadAll().stream()
                .collect(Collectors.toMap(QuizContentPack::entryId, Function.identity()));
    }

    @Test
    void loadsTheSevenQuizFiles() {
        Map<String, QuizContentPack> packs = packsById();
        assertThat(packs.keySet()).containsExactlyInAnyOrder(
                "questionner-le-monde-cp", "sciences-cm1", "histoire-cm1", "geographie-cm1",
                "emc-cp", "emc-cm1", "vrai-faux-sciences-cm1");
    }

    @Test
    void everyQuestionIsWellFormed() {
        for (QuizContentPack pack : loader.loadAll()) {
            assertThat(pack.questions()).as("%s a des questions", pack.entryId()).isNotEmpty();
            assertThat(pack.gameKey()).isNotBlank();
            assertThat(pack.gameLabel()).isNotBlank();
            assertThat(pack.descriptor().matiere()).isNotBlank();
            assertThat(pack.descriptor().niveau()).isNotBlank();

            Set<String> seen = new LinkedHashSet<>();
            for (Question q : pack.questions()) {
                assertThat(q.question()).as("%s : énoncé non vide", pack.entryId()).isNotBlank();
                assertThat(q.bonne()).as("%s : bonne réponse non vide", pack.entryId()).isNotBlank();
                assertThat(q.distracteurs()).as("%s : au moins 1 distracteur", pack.entryId()).isNotEmpty();
                assertThat(q.distracteurs()).doesNotContain(q.bonne());
                assertThat(q.distracteurs()).doesNotHaveDuplicates();
                assertThat(seen.add(q.question())).as("%s : question unique", pack.entryId()).isTrue();
            }
        }
    }

    @Test
    void poolSizesMatchTheVerifiedContent() {
        Map<String, QuizContentPack> packs = packsById();
        assertThat(packs.get("questionner-le-monde-cp").questions()).hasSize(14);
        assertThat(packs.get("sciences-cm1").questions()).hasSize(17);
        assertThat(packs.get("histoire-cm1").questions()).hasSize(10);
        assertThat(packs.get("geographie-cm1").questions()).hasSize(12);
        assertThat(packs.get("emc-cp").questions()).hasSize(10);
        assertThat(packs.get("emc-cm1").questions()).hasSize(10);
        assertThat(packs.get("vrai-faux-sciences-cm1").questions()).hasSize(10);
    }

    @Test
    void vraiFauxHasExactlyTwoOptions() {
        QuizContentPack vraiFaux = packsById().get("vrai-faux-sciences-cm1");
        for (Question q : vraiFaux.questions()) {
            assertThat(q.distracteurs()).hasSize(1);
            assertThat(q.bonne()).isIn("Vrai", "Faux");
            assertThat(q.distracteurs().get(0)).isIn("Vrai", "Faux");
        }
    }

    @Test
    void gameKeysArePreserved() {
        Map<String, QuizContentPack> packs = packsById();
        // Les 4 quiz s07 partagent la tuile "quiz-aventure" ; les s08 ont leur propre clé.
        assertThat(packs.get("histoire-cm1").gameKey()).isEqualTo("quiz-aventure");
        assertThat(packs.get("emc-cp").gameKey()).isEqualTo("vivre-ensemble");
        assertThat(packs.get("emc-cm1").gameKey()).isEqualTo("citoyennete");
        assertThat(packs.get("vrai-faux-sciences-cm1").gameKey()).isEqualTo("vrai-faux-sciences");
    }

    @Test
    void malformedContentIsRejectedAtLoad() {
        // Un distracteur égal à la bonne réponse : fichier invalide -> exception au chargement.
        String bad = """
                entryId: bad
                title: "Mauvais"
                gameKey: bad
                gameLabel: "Bad"
                matiere: "Test"
                niveau: "CP"
                questions:
                  - question: "2 + 2 ?"
                    bonne: "4"
                    distracteurs: ["4"]
                """;
        ByteArrayResource resource = new ByteArrayResource(bad.getBytes(StandardCharsets.UTF_8));
        assertThatThrownBy(() -> loader.load(resource))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("distracteur");
    }

    @Test
    void missingRequiredFieldIsRejected() {
        String bad = """
                entryId: bad
                title: "Sans gameKey"
                matiere: "Test"
                niveau: "CP"
                questions:
                  - question: "Q ?"
                    bonne: "A"
                    distracteurs: ["B"]
                """;
        ByteArrayResource resource = new ByteArrayResource(bad.getBytes(StandardCharsets.UTF_8));
        assertThatThrownBy(() -> loader.load(resource))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("gameKey");
    }
}
