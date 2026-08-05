package com.recreo.application.usecase;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PartieServiceTest {

    private static final RandomSource TOUJOURS_ZERO = bound -> 0;

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

    private static final int NOMBRE_DE_MANCHES = 10;

    private static final GameDefinition JEU = new GameDefinition(
            "un-jeu", "Un jeu", "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR,
            new ListContentSource(DESCRIPTEUR, List.of(
                    new Question(new ItemId("q1"), "un", "1", List.of("2")))),
            NOMBRE_DE_MANCHES);

    private static PartieService service() {
        ContentCatalogPort catalogue = () -> List.of(JEU);
        MechanicRegistryPort mecaniques = id -> id.equals(BanditManchot.ID) ? new BanditManchot() : null;
        return new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);
    }

    @Test
    void exposeLesJeuxDuCatalogue() {
        assertThat(service().jeuxDisponibles()).containsExactly(JEU);
    }

    @Test
    void demarreUnePartieEnResolvantLaMecaniqueDuJeu() {
        GameSession session = service().demarrer("un-jeu");

        assertThat(session).isInstanceOf(BanditManchotSession.class);
        assertThat(((BanditManchotSession) session).enonceManche()).contains("un");
        assertThat(session.score()).isEqualTo(new Score(0, 0));
    }

    @Test
    void demarrerUnJeuInconnuEchoue() {
        assertThatThrownBy(() -> service().demarrer("jeu-absent"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void joueUnCoupEnResolvantLaMecaniqueDuJeu() {
        PartieService service = service();
        GameSession session = service.demarrer("un-jeu");

        GameSession suivante = service.jouer("un-jeu", session, new BanditManchotMove("1"));

        assertThat(suivante.score()).isEqualTo(new Score(1, 1));
    }

    @Test
    void jouerSurUnJeuInconnuEchoue() {
        PartieService service = service();
        GameSession session = service.demarrer("un-jeu");

        assertThatThrownBy(() -> service.jouer("jeu-absent", session, new BanditManchotMove("1")))
                .isInstanceOf(NoSuchElementException.class);
    }

    /** Verifie que la resolution passe bien par le port, pas par une reference en dur. */
    @Test
    void neConnaitPasLaMecaniqueSansPasserParLeRegistre() {
        MechanicRegistryPort mecaniques = id -> {
            assertThat(id).isEqualTo(BanditManchot.ID);
            return new BanditManchot();
        };
        ContentCatalogPort catalogue = () -> List.of(JEU);
        PartieService service = new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);

        assertThat(service.demarrer("un-jeu")).isNotNull();
    }

    /** ADR-0012 : demarrer() transmet GameDefinition.nombreDeManches() a GameMechanic.start. */
    @Test
    void demarrerTransmetLeNombreDeManchesDuJeuALaMecanique() {
        int[] nombreDeManchesRecu = new int[1];
        GameMechanic mecaniqueEspion = new GameMechanic() {
            @Override
            public MechanicId id() {
                return BanditManchot.ID;
            }

            @Override
            public GameSession start(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
                nombreDeManchesRecu[0] = nombreDeManches;
                return new BanditManchot().start(contenu, hasard, nombreDeManches);
            }

            @Override
            public GameSession apply(GameSession session, Move coup) {
                return new BanditManchot().apply(session, coup);
            }
        };
        ContentCatalogPort catalogue = () -> List.of(JEU);
        MechanicRegistryPort mecaniques = id -> mecaniqueEspion;
        PartieService service = new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);

        service.demarrer("un-jeu");

        assertThat(nombreDeManchesRecu[0]).isEqualTo(NOMBRE_DE_MANCHES);
    }
}
