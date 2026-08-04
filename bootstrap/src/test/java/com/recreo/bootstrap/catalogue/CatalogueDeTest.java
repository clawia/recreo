package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;

import java.util.List;

/**
 * Catalogue de test pour le parcours HTTP de bout en bout : ni chargeur, ni contenu pedagogique
 * reel (celui-ci arrive avec lea-dev, ADR-0007). Nom volontairement hors des suffixes surveilles
 * par ilNYAQuUnSeulChargeurDeContenu.
 */
public final class CatalogueDeTest implements ContentCatalogPort {

    public static final String JEU_ID = "jeu-de-test";

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Test", "Test", "Test", "verifier le parcours HTTP");

    @Override
    public List<GameDefinition> games() {
        return List.of(new GameDefinition(
                JEU_ID, "Jeu de test", "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR,
                new ListContentSource(DESCRIPTEUR, List.of(
                        new Question(new ItemId("q1"), "un", "1", List.of("2")),
                        new Question(new ItemId("q2"), "deux", "2", List.of("3"))))));
    }
}
