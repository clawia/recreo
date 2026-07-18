package com.recreo.application.port.in;

/** Une operation proposee au reglage : son nom technique, son symbole, son etat coche. */
public record OperationOptionView(String name, String symbol, boolean selected) {
}
