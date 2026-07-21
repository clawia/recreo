package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.in.MathSettingsView;
import com.recreo.application.port.in.OperationOptionView;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.DifficultyConfig;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.impl.CalculMentalGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Fabrique le generateur de calcul mental : applique les reglages
 * ({@code minOperand}, {@code maxOperand}, {@code operations}) sur la
 * {@link DifficultyConfig} de base, en les bornant via {@link MathBounds} pour
 * rester dans le programme. Aucun parametre = config de base (comportement s02).
 */
final class MathGameFactory implements GameFactory {

    private final String entryId;
    private final String title;
    private final String gameKey;
    private final MechanicId mechanicId;
    private final ContentDescriptor descriptor;
    private final DifficultyConfig base;
    private final MathBounds bounds;

    MathGameFactory(String entryId, String title, String gameKey, MechanicId mechanicId,
                    ContentDescriptor descriptor, DifficultyConfig base, MathBounds bounds) {
        this.entryId = entryId;
        this.title = title;
        this.gameKey = gameKey;
        this.mechanicId = mechanicId;
        this.descriptor = descriptor;
        this.base = base;
        this.bounds = bounds;
    }

    @Override
    public GameConfig create(Map<String, String> parameters) {
        int min = clamp(parseInt(parameters.get("minOperand"), base.minOperand()));
        int max = clamp(parseInt(parameters.get("maxOperand"), base.maxOperand()));
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        Set<Operation> ops = parseOperations(parameters.get("operations"));
        ops.retainAll(bounds.allowedOperations());
        if (ops.isEmpty()) {
            // Aucune operation valide demandee : on retombe sur la config de base.
            ops = EnumSet.copyOf(base.operations());
        }

        DifficultyConfig config = new DifficultyConfig(min, max, ops, base.distractorCount());
        CalculMentalGenerator generator = new CalculMentalGenerator(config, descriptor);
        // Calcul mental : generateur combinatoire -> plafond de session = 50.
        int poolCap = generator.distinctPoolSize().orElse(SessionQuestions.ABSOLUTE_MAX);

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("niveau", descriptor.niveau());
        settings.put("questionsTarget", SessionQuestions.resolve(parameters.get("questionsTarget"), poolCap));
        return new GameConfig(mechanicId, generator, settings);
    }

    @Override
    public GameSettingsView settings() {
        List<OperationOptionView> operationOptions = bounds.allowedOperations().stream()
                .sorted(Comparator.comparingInt(Enum::ordinal))
                .map(op -> new OperationOptionView(op.name(), op.symbol(), base.operations().contains(op)))
                .toList();
        MathSettingsView math = new MathSettingsView(
                base.minOperand(), base.maxOperand(),
                bounds.operandFloor(), bounds.operandCeiling(),
                operationOptions);
        int poolCap = SessionQuestions.ABSOLUTE_MAX; // combinatoire
        return new GameSettingsView(entryId, title, gameKey, descriptor.niveau(),
                math, null, SessionQuestions.view(poolCap));
    }

    private int clamp(int value) {
        return Math.max(bounds.operandFloor(), Math.min(bounds.operandCeiling(), value));
    }

    private static int parseInt(String raw, int fallback) {
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private static Set<Operation> parseOperations(String raw) {
        Set<Operation> ops = EnumSet.noneOf(Operation.class);
        if (raw == null || raw.isBlank()) {
            return ops;
        }
        for (String name : raw.split(",")) {
            String trimmed = name.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            try {
                ops.add(Operation.valueOf(trimmed.toUpperCase(java.util.Locale.ROOT)));
            } catch (IllegalArgumentException ignored) {
                // Operation inconnue : ignoree (le reglage ne peut pas sortir du programme).
            }
        }
        return ops;
    }
}
