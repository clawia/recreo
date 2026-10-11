package com.recreo.application.usecase;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.catalog.Matiere;
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

import java.util.ArrayList;
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

    private static GameDefinition jeu(String id, String matiere, String niveau) {
        ContentDescriptor descripteur = new ContentDescriptor(matiere, niveau, "Un domaine", "une competence");
        return new GameDefinition(id, "Titre " + id, "Choisis la bonne reponse", BanditManchot.ID,
                descripteur,
                new ListContentSource(descripteur, List.of(
                        new Question(new ItemId("q1"), "un", "1", List.of("2")))),
                NOMBRE_DE_MANCHES);
    }

    /** ADR-0106 : un catalogue de test qui porte aussi la donnee de matiere. */
    private record CatalogueAvecMatieres(List<GameDefinition> games, List<Matiere> matieres)
            implements ContentCatalogPort {
    }

    private static Matiere ligne(String nom) {
        return new Matiere(nom, nom, "picto.svg", "#cfe3f6", "#2f6c9f", "segment");
    }

    /** Une ligne par matiere des jeux, dans leur ordre d'apparition : les tests de regroupement. */
    private static PartieService serviceSur(GameDefinition... jeux) {
        List<String> matieres = Stream.of(jeux).map(jeu -> jeu.descripteur().matiere()).distinct().toList();
        return serviceSur(matieres, jeux);
    }

    private static PartieService serviceSur(List<String> lignesDeMatiere, GameDefinition... jeux) {
        ContentCatalogPort catalogue = new CatalogueAvecMatieres(List.of(jeux),
                lignesDeMatiere.stream().map(PartieServiceTest::ligne).toList());
        MechanicRegistryPort mecaniques = id -> new BanditManchot();
        return new PartieService(catalogue, mecaniques, TOUJOURS_ZERO);
    }

    /** ADR-0105 : un groupe par matiere, les jeux du niveau dans l'ordre du catalogue. */
    @Test
    void matieresDuNiveauRegroupeLesJeuxDuNiveauParMatiere() {
        GameDefinition a = jeu("a", "Mathematiques", "CP");
        GameDefinition b = jeu("b", "Francais", "CP");
        GameDefinition c = jeu("c", "Mathematiques", "CP");

        assertThat(serviceSur(a, b, c).matieresDuNiveau("CP")).containsExactly(
                new GroupeDeJeux("Mathematiques", List.of(a, c)),
                new GroupeDeJeux("Francais", List.of(b)));
    }

    /**
     * ADR-0106, APM-4 : l'ordre des groupes est celui des lignes de la donnee de matiere, ni l'ordre
     * d'apparition au catalogue (Sciences, Francais, Arts) ni l'alphabet (Arts, Francais, Sciences).
     */
    @Test
    void matieresDuNiveauSuitLOrdreDesLignesDeMatiere() {
        PartieService service = serviceSur(List.of("Francais", "Sciences", "Arts", "Musique"),
                jeu("a", "Sciences", "CP"), jeu("b", "Francais", "CP"), jeu("c", "Arts", "CP"),
                jeu("d", "Francais", "CP"), jeu("e", "Sciences", "CP"));

        assertThat(service.matieresDuNiveau("CP")).extracting(GroupeDeJeux::matiere)
                .containsExactly("Francais", "Sciences", "Arts");
    }

    /** ADR-0106, APM-1 : une matiere servie sans ligne n'est pas servie sous un libelle de repli. */
    @Test
    void uneMatiereServieSansLigneFaitEchouerLeRegroupementEnSeNommant() {
        PartieService service = serviceSur(List.of("Francais"),
                jeu("a", "Francais", "CP"), jeu("b", "Sciences", "CP"));

        assertThatThrownBy(() -> service.matieresDuNiveau("CP"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("ADR-0106")
                .hasMessageContaining("\"Sciences\"");
    }

    /** ADR-0105 : une matiere presente a deux niveaux ne melange pas leurs jeux. */
    @Test
    void matieresDuNiveauNeMelangePasLesNiveaux() {
        GameDefinition cp = jeu("cp", "Francais", "CP");
        GameDefinition cm1 = jeu("cm1", "Francais", "CM1");
        PartieService service = serviceSur(cp, cm1);

        assertThat(service.matieresDuNiveau("CM1"))
                .containsExactly(new GroupeDeJeux("Francais", List.of(cm1)));
    }

    /** ADR-0044 : un niveau sans jeu rend une liste vide, jamais une valeur sentinelle. */
    @Test
    void matieresDuNiveauRendUneListeVidePourUnNiveauSansJeu() {
        assertThat(serviceSur(jeu("a", "Francais", "CP")).matieresDuNiveau("CM2")).isEmpty();
    }

    /**
     * Une partition, pas seulement une inclusion (meme forme que celle de {@code jeuxDuNiveau}) :
     * chaque jeu d'un niveau est dans exactement un groupe, aucun n'est perdu ni compte deux fois.
     */
    @Test
    void lesGroupesPartitionnentLesJeuxDuNiveau() {
        PartieService service = serviceSur(
                jeu("a", "Sciences", "CP"), jeu("b", "Francais", "CP"), jeu("c", "Sciences", "CM1"),
                jeu("d", "Francais", "CP"), jeu("e", "Arts", "CP"));

        List<GameDefinition> reunion = new ArrayList<>();
        service.matieresDuNiveau("CP").forEach(groupe -> reunion.addAll(groupe.jeux()));

        assertThat(reunion).containsExactlyInAnyOrderElementsOf(service.jeuxDuNiveau("CP"));
    }

    /**
     * ADR-0049 : la matiere est comparee telle que le paquet la declare. Deux graphies font deux
     * groupes, visibles a l'ecran, plutot qu'une normalisation silencieuse que rien ne garderait.
     */
    @Test
    void uneGraphieDifferenteDeLaMatiereFaitUnGroupeDeCePlus() {
        PartieService service = serviceSur(jeu("a", "Français", "CP"), jeu("b", "Francais", "CP"));

        assertThat(service.matieresDuNiveau("CP")).extracting(GroupeDeJeux::matiere)
                .containsExactly("Français", "Francais");
    }

    @Test
    void jeuxDeLaMatiereRendLesJeuxDuNiveauEtDeLaMatiereDansLOrdreDuCatalogue() {
        GameDefinition a = jeu("a", "Francais", "CP");
        GameDefinition b = jeu("b", "Mathematiques", "CP");
        GameDefinition c = jeu("c", "Francais", "CP");
        GameDefinition d = jeu("d", "Francais", "CM1");
        PartieService service = serviceSur(a, b, c, d);

        assertThat(service.jeuxDeLaMatiere("CP", "Francais")).containsExactly(a, c);
        assertThat(service.jeuxDeLaMatiere("CM1", "Francais")).containsExactly(d);
    }

    @Test
    void jeuxDeLaMatiereRendUneListeVideQuandAucunJeuNeRepond() {
        PartieService service = serviceSur(jeu("a", "Francais", "CP"));

        assertThat(service.jeuxDeLaMatiere("CP", "Histoire")).isEmpty();
        assertThat(service.jeuxDeLaMatiere("CM2", "Francais")).isEmpty();
    }

    /** Le groupe garde la liste qu'il a recue a sa construction : la modifier apres ne le change pas. */
    @Test
    void unGroupeDeJeuxCopieLaListeQuOnLuiDonne() {
        List<GameDefinition> source = new ArrayList<>(List.of(jeu("a", "Francais", "CP")));
        GroupeDeJeux groupe = new GroupeDeJeux("Francais", source);

        source.add(jeu("b", "Francais", "CP"));

        assertThat(groupe.jeux()).hasSize(1);
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
