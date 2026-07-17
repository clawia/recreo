package com.recreo.bootstrap.config;

import com.recreo.domain.content.RandomSource;

import java.util.Random;

/** Implementation d'infrastructure de {@link RandomSource} basee sur {@link Random}. */
public final class JdkRandomSource implements RandomSource {

    private final Random random = new Random();

    @Override
    public int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
