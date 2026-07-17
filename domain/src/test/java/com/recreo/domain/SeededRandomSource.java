package com.recreo.domain;

import com.recreo.domain.content.RandomSource;

import java.util.Random;

/** {@link RandomSource} deterministe (seedee) pour des tests reproductibles. */
public final class SeededRandomSource implements RandomSource {

    private final Random random;

    public SeededRandomSource(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
