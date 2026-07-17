package com.recreo.domain.pedagogy;

/** Niveaux scolaires couverts en Phase 0. */
public enum Niveau {
    CP("Cycle 2"),
    CM1("Cycle 3");

    private final String cycle;

    Niveau(String cycle) {
        this.cycle = cycle;
    }

    public String cycle() {
        return cycle;
    }
}
