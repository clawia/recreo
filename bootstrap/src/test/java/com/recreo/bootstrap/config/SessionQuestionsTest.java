package com.recreo.bootstrap.config;

import com.recreo.application.port.in.QuestionsSettingsView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Teste la LOGIQUE de bornage du réglage « nombre de questions » (ADR-0006) en isolation,
 * avec des tailles de vivier SYNTHÉTIQUES — donc sans dépendre du contenu externalisé
 * (cf. `PLAN-refacto-tests-contenu.md`). Remplace les anciennes assertions de caps qui
 * étaient couplées aux fichiers de contenu éditables.
 */
class SessionQuestionsTest {

    @Test
    void maxIsCappedAtFiftyAndByThePool() {
        assertThat(SessionQuestions.maxFor(3)).isEqualTo(3);    // petit vivier fini
        assertThat(SessionQuestions.maxFor(20)).isEqualTo(20);
        assertThat(SessionQuestions.maxFor(50)).isEqualTo(50);
        assertThat(SessionQuestions.maxFor(60)).isEqualTo(50);  // combinatoire / gros vivier
        assertThat(SessionQuestions.maxFor(SessionQuestions.ABSOLUTE_MAX)).isEqualTo(50);
    }

    @Test
    void resolveClampsToBoundsForALargePool() {
        int pool = 60; // max effectif = 50
        assertThat(SessionQuestions.resolve("1", pool)).isEqualTo(SessionQuestions.MIN);   // < min -> 5
        assertThat(SessionQuestions.resolve("999", pool)).isEqualTo(50);                    // > max -> 50
        assertThat(SessionQuestions.resolve("30", pool)).isEqualTo(30);                     // dans les bornes
    }

    @Test
    void resolveDefaultsWhenAbsentOrInvalid() {
        assertThat(SessionQuestions.resolve(null, 60)).isEqualTo(SessionQuestions.DEFAULT); // 20
        assertThat(SessionQuestions.resolve("", 60)).isEqualTo(SessionQuestions.DEFAULT);
        assertThat(SessionQuestions.resolve("abc", 60)).isEqualTo(SessionQuestions.DEFAULT);
    }

    @Test
    void smallFinitePoolLowersDefaultAndFloor() {
        int pool = 3; // vivier < défaut et < plancher
        assertThat(SessionQuestions.resolve(null, pool)).isEqualTo(3); // défaut min(20,3)=3
        assertThat(SessionQuestions.resolve("1", pool)).isEqualTo(3);  // plancher min(5,3)=3
        assertThat(SessionQuestions.resolve("2", pool)).isEqualTo(3);
    }

    @Test
    void viewReflectsTheBounds() {
        QuestionsSettingsView big = SessionQuestions.view(60);
        assertThat(big.min()).isEqualTo(5);
        assertThat(big.max()).isEqualTo(50);
        assertThat(big.defaultValue()).isEqualTo(20);

        QuestionsSettingsView small = SessionQuestions.view(3);
        assertThat(small.min()).isEqualTo(3);
        assertThat(small.max()).isEqualTo(3);
        assertThat(small.defaultValue()).isEqualTo(3);
    }
}
