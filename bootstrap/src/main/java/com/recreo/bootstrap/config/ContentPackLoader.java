package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.DifficultyConfig;
import com.recreo.domain.content.Operation;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/** Charge des {@link ContentPack} depuis des ressources YAML du classpath. */
public final class ContentPackLoader {

    private final Yaml yaml = new Yaml();

    public ContentPack load(String classpathLocation) {
        ClassPathResource resource = new ClassPathResource(classpathLocation);
        try (InputStream in = resource.getInputStream()) {
            Map<String, Object> root = yaml.load(in);
            if (root == null) {
                throw new IllegalStateException("Fichier de contenu vide : " + classpathLocation);
            }
            return toPack(root);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger le contenu : " + classpathLocation, e);
        }
    }

    @SuppressWarnings("unchecked")
    private ContentPack toPack(Map<String, Object> root) {
        String entryId = (String) root.get("entryId");
        String title = (String) root.get("title");
        String description = (String) root.get("description");
        String mechanic = (String) root.get("mechanic");
        String gameKey = (String) root.get("gameKey");
        String gameLabel = (String) root.get("gameLabel");

        ContentDescriptor descriptor = new ContentDescriptor(
                (String) root.get("matiere"),
                (String) root.get("niveau"),
                (String) root.get("domaine"));

        Map<String, Object> difficulty = (Map<String, Object>) root.get("difficulty");
        int minOperand = ((Number) difficulty.get("minOperand")).intValue();
        int maxOperand = ((Number) difficulty.get("maxOperand")).intValue();
        int distractorCount = ((Number) difficulty.get("distractorCount")).intValue();
        EnumSet<Operation> operations = readOperations((List<String>) difficulty.get("operations"));
        DifficultyConfig config = new DifficultyConfig(minOperand, maxOperand, operations, distractorCount);

        Map<String, Object> boundsNode = (Map<String, Object>) root.get("bounds");
        int operandFloor = ((Number) boundsNode.get("operandFloor")).intValue();
        int operandCeiling = ((Number) boundsNode.get("operandCeiling")).intValue();
        EnumSet<Operation> allowed = readOperations((List<String>) boundsNode.get("allowedOperations"));
        MathBounds bounds = new MathBounds(operandFloor, operandCeiling, allowed);

        return new ContentPack(entryId, title, description, mechanic, gameKey, gameLabel, descriptor, config, bounds);
    }

    private EnumSet<Operation> readOperations(List<String> names) {
        EnumSet<Operation> operations = EnumSet.noneOf(Operation.class);
        for (String name : names) {
            operations.add(Operation.valueOf(name));
        }
        return operations;
    }
}
