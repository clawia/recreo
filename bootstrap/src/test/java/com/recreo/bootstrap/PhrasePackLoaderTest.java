package com.recreo.bootstrap;

import com.recreo.bootstrap.config.PhraseContentPack;
import com.recreo.bootstrap.config.PhrasePackLoader;
import com.recreo.domain.content.impl.PhrasePuzzleGenerator.Phrase;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Vérifie l'externalisation du contenu « reconstitue la phrase » (mécanique puzzle, déclinaison
 * « mots », cf. ADR-0017) : les fichiers YAML (content/phrase/*.yml) se chargent, sont bien
 * formés (en-têtes non vides, chaque phrase ≥ 2 mots), et la validation au chargement rejette une
 * phrase à moins de 2 mots. Même patron que {@code MotsPackLoaderTest} / {@code QuizPackLoaderTest}.
 */
class PhrasePackLoaderTest {

    private final PhrasePackLoader loader = new PhrasePackLoader();

    private Map<String, PhraseContentPack> packsById() {
        return loader.loadAll().stream()
                .collect(Collectors.toMap(PhraseContentPack::entryId, Function.identity()));
    }

    @Test
    void loadsTheShippedPhraseGames() {
        // `contains` (pas `containsExactly`) : robuste à l'ajout/retrait d'un fichier.
        assertThat(packsById().keySet()).contains("phrase-cp", "phrase-cm1");
    }

    @Test
    void everyPhraseHasAtLeastTwoWordsAndHeadersAreNonBlank() {
        for (PhraseContentPack pack : loader.loadAll()) {
            assertThat(pack.entryId()).isNotBlank();
            assertThat(pack.title()).isNotBlank();
            assertThat(pack.gameKey()).isNotBlank();
            assertThat(pack.gameLabel()).isNotBlank();
            assertThat(pack.consigne()).isNotBlank();
            assertThat(pack.descriptor().matiere()).isNotBlank();
            assertThat(pack.descriptor().niveau()).isNotBlank();
            assertThat(pack.phrases()).isNotEmpty();
            for (Phrase p : pack.phrases()) {
                assertThat(p.phrase()).isNotBlank();
                // Au moins 2 mots (l'unité rangée est le MOT) : sinon rien à ranger.
                assertThat(p.phrase().trim().split("\\s+"))
                        .as("%s : « %s » doit contenir au moins 2 mots", pack.entryId(), p.phrase())
                        .hasSizeGreaterThanOrEqualTo(2);
                // L'indice est FACULTATIF pour les phrases : jamais null (chaîne vide au minimum).
                assertThat(p.indice()).isNotNull();
            }
        }
    }

    @Test
    void phraseWithLessThanTwoWordsIsRejected() {
        String bad = """
                entryId: bad
                title: "Un seul mot"
                gameKey: phrase
                gameLabel: "Bad"
                matiere: "Français"
                niveau: "CP"
                consigne: "Remets les mots dans l'ordre"
                phrases:
                  - { phrase: "bonjour" }
                """;
        assertThatThrownBy(() -> loader.load(res(bad)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("2 mots");
    }

    private static ByteArrayResource res(String yaml) {
        return new ByteArrayResource(yaml.getBytes(StandardCharsets.UTF_8));
    }
}
