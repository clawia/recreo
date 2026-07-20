package com.recreo.bootstrap;

import com.recreo.bootstrap.config.MotsContentPack;
import com.recreo.bootstrap.config.MotsPackLoader;
import com.recreo.domain.content.impl.MotChoixGenerator.Entree;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Vérifie l'externalisation du contenu « mot → réponse » (ADR-0010) : les 5 fichiers YAML
 * (content/mots/*.yml) se chargent, sont bien formés, et la validation au chargement rejette
 * un fichier incohérent (distracteur = bonne réponse, vivier trop petit, mode inconnu).
 */
class MotsPackLoaderTest {

    private final MotsPackLoader loader = new MotsPackLoader();

    private Map<String, MotsContentPack> packsById() {
        return loader.loadAll().stream()
                .collect(Collectors.toMap(MotsContentPack::entryId, Function.identity()));
    }

    @Test
    void loadsTheShippedMotsGames() {
        // `contains` (pas `containsExactly`) : robuste à l'ajout/retrait d'un fichier.
        assertThat(packsById().keySet()).contains(
                "le-bon-son", "homophones", "premiere-lettre", "nature-mot", "anglais-lexique");
    }

    @Test
    void modesAreParsed() {
        Map<String, MotsContentPack> packs = packsById();
        assertThat(packs.get("le-bon-son").poolMode()).isFalse();
        assertThat(packs.get("homophones").poolMode()).isFalse();
        assertThat(packs.get("premiere-lettre").poolMode()).isTrue();
        assertThat(packs.get("nature-mot").poolMode()).isTrue();
        assertThat(packs.get("anglais-lexique").poolMode()).isTrue();
        assertThat(packs.get("anglais-lexique").distractorCount()).isEqualTo(2);
    }

    @Test
    void everyEntryIsWellFormed() {
        for (MotsContentPack pack : loader.loadAll()) {
            assertThat(pack.question()).isNotBlank();
            assertThat(pack.gameKey()).isNotBlank();
            Set<String> prompts = new LinkedHashSet<>();
            for (Entree e : pack.entrees()) {
                assertThat(e.prompt()).isNotBlank();
                assertThat(e.bonne()).isNotBlank();
                assertThat(prompts.add(e.prompt())).as("%s : prompt unique", pack.entryId()).isTrue();
                if (!pack.poolMode()) {
                    assertThat(e.distracteurs()).isNotEmpty();
                    assertThat(e.distracteurs()).doesNotContain(e.bonne());
                    assertThat(e.distracteurs()).doesNotHaveDuplicates();
                }
            }
            if (pack.poolMode()) {
                long distinctAnswers = pack.entrees().stream().map(Entree::bonne).distinct().count();
                assertThat(distinctAnswers).isGreaterThanOrEqualTo(pack.distractorCount() + 1L);
            }
        }
    }

    @Test
    void explicitDistractorEqualToAnswerIsRejected() {
        String bad = """
                entryId: bad
                title: "Mauvais"
                gameKey: bad
                gameLabel: "Bad"
                matiere: "Test"
                niveau: "CP"
                question: "Q ?"
                distractorMode: explicit
                items:
                  - prompt: "MOT"
                    bonne: "A"
                    distracteurs: ["A"]
                """;
        assertThatThrownBy(() -> loader.load(res(bad)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("distracteur");
    }

    @Test
    void poolTooSmallIsRejected() {
        String bad = """
                entryId: bad
                title: "Vivier trop petit"
                gameKey: bad
                gameLabel: "Bad"
                matiere: "Test"
                niveau: "CP"
                question: "Q ?"
                distractorMode: pool
                distractorCount: 2
                items:
                  - prompt: "un"
                    bonne: "A"
                  - prompt: "deux"
                    bonne: "B"
                """;
        assertThatThrownBy(() -> loader.load(res(bad)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("distinctes");
    }

    @Test
    void unknownModeIsRejected() {
        String bad = """
                entryId: bad
                title: "Mode inconnu"
                gameKey: bad
                gameLabel: "Bad"
                matiere: "Test"
                niveau: "CP"
                question: "Q ?"
                distractorMode: magique
                items:
                  - prompt: "un"
                    bonne: "A"
                    distracteurs: ["B"]
                """;
        assertThatThrownBy(() -> loader.load(res(bad)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("distractorMode");
    }

    private static ByteArrayResource res(String yaml) {
        return new ByteArrayResource(yaml.getBytes(StandardCharsets.UTF_8));
    }
}
