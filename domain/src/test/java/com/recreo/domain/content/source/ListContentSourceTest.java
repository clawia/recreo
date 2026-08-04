package com.recreo.domain.content.source;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ListContentSourceTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

    @Test
    void rendLeDescripteurDuPaquet() {
        assertThat(source("a").descriptor()).isEqualTo(DESCRIPTEUR);
    }

    @Test
    void tireUnItemDuVivier() {
        Optional<Item> tire = source("a", "b").next(graine(1L), Set.of());

        assertThat(tire).isPresent();
        assertThat(tire.orElseThrow().id()).isIn(new ItemId("a"), new ItemId("b"));
    }

    @Test
    void rendVideQuandLeVivierEstEpuise() {
        Optional<Item> tire = source("a", "b")
                .next(graine(1L), Set.of(new ItemId("a"), new ItemId("b")));

        assertThat(tire).isEmpty();
    }

    @Test
    void rendVideQuandLePaquetEstVide() {
        ListContentSource vide = new ListContentSource(DESCRIPTEUR, List.of());

        assertThat(vide.next(graine(1L), Set.of())).isEmpty();
    }

    @Test
    void exclutLesItemsDejaVus() {
        ListContentSource source = source("a", "b", "c");
        Set<ItemId> dejaVus = Set.of(new ItemId("a"), new ItemId("c"));

        List<ItemId> tires = IntStream.range(0, 20)
                .mapToObj(seed -> source.next(graine(seed), dejaVus).orElseThrow().id())
                .distinct()
                .toList();

        assertThat(tires).containsExactly(new ItemId("b"));
    }

    @Test
    void leTirageIndexeLeVivierRestantEtPasLeVivierComplet() {
        ListContentSource source = source("a", "b", "c");
        RandomSource premierRestant = bound -> 0;

        Optional<Item> tire = source.next(premierRestant, Set.of(new ItemId("a")));

        assertThat(tire.orElseThrow().id()).isEqualTo(new ItemId("b"));
    }

    @Test
    void leTirageEstRejouableAGraineFixe() {
        assertThat(dixTirages(graine(7L))).isEqualTo(dixTirages(graine(7L)));
    }

    @Test
    void leTirageNeRendJamaisDeuxFoisLeMemeItem() {
        assertThat(dixTirages(graine(7L))).doesNotHaveDuplicates().hasSize(10);
    }

    private static List<ItemId> dixTirages(RandomSource hasard) {
        ListContentSource source = source("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        List<ItemId> vus = new ArrayList<>();
        for (int manche = 0; manche < 10; manche++) {
            vus.add(source.next(hasard, Set.copyOf(vus)).orElseThrow().id());
        }
        return vus;
    }

    private static ListContentSource source(String... ids) {
        return new ListContentSource(DESCRIPTEUR,
                Arrays.stream(ids).map(ListContentSourceTest::question).toList());
    }

    private static Question question(String id) {
        return new Question(new ItemId(id), "enonce " + id, "juste", List.of("faux"));
    }

    private static RandomSource graine(long valeur) {
        return new Random(valeur)::nextInt;
    }
}
