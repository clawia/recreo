package com.recreo.domain.content;

/**
 * ADR-0004 : le tirage est injecte, donc rejouable a graine fixe en test.
 */
public interface RandomSource {

    int nextInt(int bound);
}
