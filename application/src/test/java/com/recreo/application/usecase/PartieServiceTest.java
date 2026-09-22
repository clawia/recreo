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
import java.util.stream.Stream;

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

    private static final ContentDescriptor DESCRIPTEUR_CM1 =
            new ContentDescriptor("Mathematiques", "CM1", "Nombres et calculs", "denombrer");

    private static final GameDefinition JEU_CM1 = new GameDefinition(
            "un-jeu-cm1", "Un jeu de CM1", "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR_CM1,
            new ListContentSource(DESCRIPTEUR_CM1, List.of(
                    new Question(new ItemId("q1"), "un", "1", List.of("2")))),
            NOMBRE_DE_MANCHES);

    private static PartieService serviceAvecDeuxNiveaux() {
        ContentCatalogPort catalogue = () -> List.of(JEU, JEU_CM1);
        MechanicRegistryPort mecaniques = id -> new BanditManchot();
        return new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);
    }

    @Test
    void exposeLesJeuxDuCatalogue() {
        assertThat(service().jeuxDisponibles()).containsExactly(JEU);
    }

    /** ADR-0044 : le niveau unique du catalogue de test, rendu sans effectif ecrit en dur. */
    @Test
    void niveauxDuCatalogueRendLeNiveauUniqueDuJeuDeTest() {
        assertThat(service().niveauxDuCatalogue()).containsExactly(DESCRIPTEUR.niveau());
    }

    /**
     * ADR-0044 pour les valeurs distinctes, ADR-0095 et DEC-0090 pour leur ordre : ce n'est plus
     * celui du catalogue - jamais un effectif epingle.
     */
    @Test
    void niveauxDuCatalogueRendLesValeursDistinctes() {
        assertThat(serviceAvecDeuxNiveaux().niveauxDuCatalogue()).containsExactly("CP", "CM1");
    }

    private static GameDefinition jeuDeNiveau(String niveau) {
        ContentDescriptor descripteur =
                new ContentDescriptor("Mathematiques", niveau, "Nombres et calculs", "denombrer");
        return new GameDefinition("jeu-" + niveau, "Un jeu", "Choisis la bonne reponse",
                BanditManchot.ID, descripteur,
                new ListContentSource(descripteur,
                        List.of(new Question(new ItemId("q1"), "un", "1", List.of("2")))),
                NOMBRE_DE_MANCHES);
    }

    private static PartieService serviceSurLesNiveaux(String... niveaux) {
        List<GameDefinition> jeux = Stream.of(niveaux).map(PartieServiceTest::jeuDeNiveau).toList();
        return new PartieService(() -> jeux, id -> new BanditManchot(), TOUJOURS_ZERO);
    }

    /**
     * DEC-0090, 1er critere de S36-ORDRE-NIVEAUX : le catalogue est declare a l'envers de la
     * progression scolaire, et l'en-tete la retablit.
     */
    @Test
    void niveauxDuCatalogueSuitLaProgressionScolaireEtNonLOrdreDeDeclaration() {
        assertThat(serviceSurLesNiveaux("CM2", "CM1", "CE1", "CP").niveauxDuCatalogue())
                .containsExactly("CP", "CE1", "CM1", "CM2");
        assertThat(serviceSurLesNiveaux("CM1", "CE1", "CP").niveauxDuCatalogue())
                .containsExactly("CP", "CE1", "CM1");
    }

    /**
     * DEC-0090, 2e critere : sur le catalogue d'aujourd'hui, l'ordre affiche ne change pas. Cette
     * story ne corrige rien d'observable, elle garantit ce qui ne l'etait pas.
     */
    @Test
    void surUnCatalogueCpPuisCm1LOrdreAfficheNeChangePas() {
        assertThat(serviceSurLesNiveaux("CP", "CM1").niveauxDuCatalogue())
                .containsExactly("CP", "CM1");
    }

    /** DEC-0090, 3e critere : un niveau sans contenu n'a pas de puce. */
    @Test
    void unNiveauDeLaSequenceAbsentDuCatalogueNApparaitPas() {
        assertThat(serviceSurLesNiveaux("CM1", "CP").niveauxDuCatalogue())
                .containsExactly("CP", "CM1")
                .doesNotContain("CE1", "CM2");
    }

    /**
     * ADR-0095, le rang de repli : un niveau hors sequence se range apres les niveaux connus, dans
     * l'ordre du catalogue, plutot que de faire echouer l'accueil. Le cas est impossible sur le
     * catalogue servi ({@code ChaqueNiveauServiEstNommeDansLaSequenceTest}) ; il est teste ici
     * parce qu'un comportement de repli non teste est un comportement suppose.
     */
    @Test
    void unNiveauHorsSequenceSeRangeApresLesAutresDansLOrdreDuCatalogue() {
        assertThat(serviceSurLesNiveaux("6e", "CM1", "5e", "CP").niveauxDuCatalogue())
                .containsExactly("CP", "CM1", "6e", "5e");
    }

    @Test
    void jeuxDuNiveauRendUniquementLesJeuxDeCeNiveau() {
        assertThat(serviceAvecDeuxNiveaux().jeuxDuNiveau("CM1")).containsExactly(JEU_CM1);
    }

    /**
     * Critere 7 (avis-faisabilite s14, section 7.7), forme renforcee versee sans etre imposee par
     * theo-archi : une partition, pas seulement une inclusion - elle attrape en plus un jeu dont le
     * niveau ne correspondrait a aucun niveau du catalogue, ce qu'une simple egalite ne voit pas.
     */
    @Test
    void laReunionDesListesFiltreesSurTousLesNiveauxEstExactementJeuxDisponibles() {
        PartieService service = serviceAvecDeuxNiveaux();

        List<GameDefinition> reunion = service.niveauxDuCatalogue().stream()
                .flatMap(niveau -> service.jeuxDuNiveau(niveau).stream())
                .toList();

        assertThat(reunion).containsExactlyInAnyOrderElementsOf(service.jeuxDisponibles());
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

            @Override
            public int longueurMinimale() {
                return new BanditManchot().longueurMinimale();
            }
        };
        ContentCatalogPort catalogue = () -> List.of(JEU);
        MechanicRegistryPort mecaniques = id -> mecaniqueEspion;
        PartieService service = new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);

        service.demarrer("un-jeu");

        assertThat(nombreDeManchesRecu[0]).isEqualTo(NOMBRE_DE_MANCHES);
    }
}
