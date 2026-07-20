package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.DifficultyConfig;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.CalculMentalGenerator;
import com.recreo.domain.content.impl.SyllabesCpGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.Reel;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BanditManchotMechanicTest {

    /** UNE SEULE mecanique partagee : preuve du decouplage a l'execution. */
    private static final BanditManchotMechanic SHARED = new BanditManchotMechanic();

    static Stream<Arguments> contents() {
        ContentGenerator calcul = new CalculMentalGenerator(
                new DifficultyConfig(0, 10, EnumSet.of(Operation.ADDITION, Operation.SOUSTRACTION), 2),
                new ContentDescriptor("mathematiques", "CP", "Calcul mental"));
        ContentGenerator francais = new SyllabesCpGenerator(
                new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes"),
                java.util.List.of(
                        new SyllabesCpGenerator.Mot("chat", 1),
                        new SyllabesCpGenerator.Mot("lapin", 2),
                        new SyllabesCpGenerator.Mot("chocolat", 3)));
        return Stream.of(
                Arguments.of("calcul-mental-cp", calcul),
                Arguments.of("francais-cp", francais));
    }

    /**
     * La MEME instance {@link BanditManchotMechanic} joue deux contenus differents
     * (maths et francais) sans modification : demarre une session valide, propose
     * la bonne reponse parmi les rouleaux, et avance au round suivant sur un coup
     * correct. Mecanique inchangee, contenus differents.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("contents")
    void sameMechanicServesDifferentContents(String label, ContentGenerator generator) {
        GameConfig config = new GameConfig(BanditManchotMechanic.ID, generator, Map.of());
        RandomSource random = new SeededRandomSource(99L);

        GameSession session = SHARED.start(config, random);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.reels()).hasSize(1);
        assertThat(session.reels().get(0).symbols()).contains(session.currentItem().expected());
        assertThat(session.currentItem().reelSymbols()).isNotEmpty();
        assertThat(session.currentItem().question()).isNotBlank();

        Answer correct = session.currentItem().expected();
        MoveResult result = SHARED.apply(session, new SpinAndPick(correct));
        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
    }

    private GameConfig cpConfig() {
        DifficultyConfig cp = new DifficultyConfig(
                0, 10, EnumSet.of(Operation.ADDITION, Operation.SOUSTRACTION), 2);
        CalculMentalGenerator generator = new CalculMentalGenerator(
                cp, new ContentDescriptor("mathematiques", "CP", "Calcul mental"));
        return new GameConfig(BanditManchotMechanic.ID, generator, Map.of());
    }

    @Test
    void startTiresAnItemAndBuildsShuffledReels() {
        BanditManchotMechanic mechanic = new BanditManchotMechanic();
        RandomSource random = new SeededRandomSource(1L);

        GameSession session = mechanic.start(cpConfig(), random);

        assertThat(session.mechanic()).isEqualTo(BanditManchotMechanic.ID);
        assertThat(session.score()).isZero();
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.currentItem()).isNotNull();
        assertThat(session.reels()).hasSize(1);

        Reel reel = session.reels().get(0);
        // 1 bonne reponse + 2 distracteurs.
        assertThat(reel.symbols()).hasSize(3);
        assertThat(reel.symbols()).contains(session.currentItem().expected());
    }

    @Test
    void correctPickIncrementsScoreAndAdvancesRound() {
        BanditManchotMechanic mechanic = new BanditManchotMechanic();
        RandomSource random = new SeededRandomSource(2L);

        GameSession session = mechanic.start(cpConfig(), random);
        Answer correct = session.currentItem().expected();

        MoveResult result = mechanic.apply(session, new SpinAndPick(correct));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.session().currentItem()).isNotNull();
        assertThat(result.feedback().message()).contains("Bravo");
    }

    @Test
    void wrongPickDoesNotChangeScoreButStillAdvances() {
        BanditManchotMechanic mechanic = new BanditManchotMechanic();
        RandomSource random = new SeededRandomSource(3L);

        GameSession session = mechanic.start(cpConfig(), random);
        Answer wrong = new Answer("this-is-never-correct");

        MoveResult result = mechanic.apply(session, new SpinAndPick(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.feedback().message()).contains("bonne reponse");
    }
}
