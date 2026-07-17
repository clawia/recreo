package com.recreo.domain.content;

/** Operations arithmetiques supportees par le contenu de calcul mental. */
public enum Operation {
    ADDITION("+"),
    SOUSTRACTION("-"),
    MULTIPLICATION("×");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public int apply(int a, int b) {
        return switch (this) {
            case ADDITION -> a + b;
            case SOUSTRACTION -> a - b;
            case MULTIPLICATION -> a * b;
        };
    }
}
