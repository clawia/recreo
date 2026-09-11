package com.recreo.domain.archi;

import com.recreo.domain.content.CadreDeZone;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.designation.Designation;
import com.recreo.domain.mechanics.designation.DesignationMove;
import com.recreo.domain.mechanics.saisie.Saisie;
import com.recreo.domain.mechanics.saisie.SaisieMove;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>ADR-0090, la regle centrale : sur toute mecanique du domaine, une manche resolue apres au moins
 * un essai infructueux n'est pas comptee comme une reussite.</b> C'est l'instruction 1 de
 * {@code DEC-0061}, tranchee en famille A par {@code nadia-prof} le 2026-09-10 : <i>"Quand
 * l'elimination est possible, l'enfant garde ses trois essais, et le score cesse de compter comme
 * trouvee une manche qu'il n'a pas trouvee du premier coup."</i>
 *
 * <p><b>Pourquoi ce n'est pas une regle ArchUnit.</b> La propriete est un comportement, pas une
 * forme : aucun modele de types ne dit si un compteur monte. ArchUnit sert ici a une seule chose, et
 * c'est la seule pour laquelle il est le bon outil - <b>deriver la population</b> du graphe de
 * classes, pour qu'une mecanique neuve entre dans cette regle le jour ou son type est ecrit, et non
 * le jour ou quelqu'un pense a l'ajouter ici (ADR-0013, perimetre derive). Le jugement, lui, est un
 * scenario joue sur la session reelle de chaque mecanique.
 *
 * <p><b>Ce qu'elle interdit reellement</b>, la question que ce depot pose a chaque regle. Elle
 * interdit qu'une mecanique credite une manche que l'enfant n'a pas trouvee du premier coup - le
 * defaut mesure par {@code nadia-prof} et reste ouvert neuf sprints ({@code SCO-1},
 * {@code SCO-2}). Et elle interdit le contournement symetrique, celui qui rendrait la premiere
 * moitie decorative : une mecanique qui ne crediterait plus rien du tout la satisferait sans rien
 * tenir. C'est le motif de la seconde methode ci-dessous ({@code SCO-4}), qui exige qu'une manche
 * trouvee du premier essai <b>reste</b> comptee. Les deux moities se tiennent : sans la premiere le
 * remede n'est pas fait, sans la seconde il est fait a l'exces.
 *
 * <p><b>Ce qu'elle ne tient pas</b>, nomme plutot que laisse decouvrir. {@code SCO-3} est ecrite au
 * niveau du catalogue - <i>le score final d'une strategie aveugle n'est pas parfait</i>. Sous le
 * remede, un score parfait redevient possible pour une strategie aveugle <b>chanceuse</b> (les dix
 * premiers appuis justes) : c'est une propriete de la graine, pas du produit, et une regle qui
 * l'epinglerait serait un faux rouge en attente. La regle ci-dessus rend {@code SCO-3} vraie en
 * esperance, et cela est dit plutot que sous-entendu. {@code SCO-5} (la revelation ne compte pas),
 * {@code SCO-6} (la partie presente {@code nombreDeManches} manches) et {@code SCO-7} (une reponse
 * juste ne coute pas d'essai) sont deja tenues, mecanique par mecanique, par
 * {@code BanditManchotTest}, {@code DesignationTest} et {@code SaisieTest} : elles ne sont pas
 * recopiees ici, ou elles ne gagneraient rien a l'etre.
 */
class UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest {

    private static final String MECANIQUES = "com.recreo.domain.mechanics";

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "sonde de regle");

    /** Deterministe : ni tirage, ni melange, ni graine a choisir. */
    private static final RandomSource TOUJOURS_ZERO = borne -> 0;

    /** Assez de manches pour qu'aucune partie de ce fichier ne se termine avant son assertion. */
    private static final int MANCHES_LARGEMENT_SUFFISANTES = 10;

    /**
     * Un scenario par mecanique : de quoi demarrer une partie, fabriquer un coup, et deux reponses -
     * une fausse et la juste. Rien de plus : le contrat {@link GameMechanic} suffit a jouer, et
     * {@link GameSession#score()} suffit a juger.
     */
    private record Scenario(String mecanique, Supplier<GameMechanic> moteur,
                            Supplier<ContentSource> contenu, Function<String, Move> coup,
                            String faux, String juste) {

        GameSession partie() {
            return moteur.get().start(contenu.get(), TOUJOURS_ZERO, MANCHES_LARGEMENT_SUFFISANTES);
        }

        GameSession jouer(GameSession session, String reponse) {
            return moteur.get().apply(session, coup.apply(reponse));
        }
    }

    private static Zone zone(String identifiant) {
        return new Zone(identifiant, "la zone " + identifiant, new CadreDeZone(0, 0, 10, 10));
    }

    private static List<Scenario> scenarios() {
        return List.of(
                new Scenario("com.recreo.domain.mechanics.banditmanchot.BanditManchot",
                        BanditManchot::new,
                        () -> new ListContentSource(DESCRIPTEUR, List.of(
                                new Question(new ItemId("q1"), "Combien font un et zero ?", "1",
                                        List.of("2", "3")),
                                new Question(new ItemId("q2"), "Combien font un et un ?", "2",
                                        List.of("1", "3")))),
                        BanditManchotMove::new, "2", "1"),
                new Scenario("com.recreo.domain.mechanics.designation.Designation",
                        Designation::new,
                        () -> new ListContentSource(DESCRIPTEUR, List.of(
                                new ZoneADesigner(new ItemId("z1"), "Montre le tableau.",
                                        "plan-classe", "Plan de la classe vue de dessus.",
                                        List.of(zone("tableau"), zone("porte")), "tableau",
                                        Optional.empty(), Optional.empty()),
                                new ZoneADesigner(new ItemId("z2"), "Montre la porte.",
                                        "plan-classe", "Plan de la classe vue de dessus.",
                                        List.of(zone("porte"), zone("tableau")), "porte",
                                        Optional.empty(), Optional.empty()))),
                        DesignationMove::new, "porte", "tableau"),
                new Scenario("com.recreo.domain.mechanics.saisie.Saisie",
                        Saisie::new,
                        () -> new ListContentSource(DESCRIPTEUR, List.of(
                                new ReponseAEcrire(new ItemId("s1"), "Ecris le nombre sept.", "7",
                                        List.of(), Optional.of("Un chiffre."), Optional.empty(),
                                        ExigenceDeForme.FORME_LIBRE),
                                new ReponseAEcrire(new ItemId("s2"), "Ecris le nombre cinq.", "5",
                                        List.of(), Optional.of("Un chiffre."), Optional.empty(),
                                        ExigenceDeForme.FORME_LIBRE))),
                        SaisieMove::new, "3", "7"));
    }

    /**
     * ADR-0013, perimetre derive et derivation totale : la population vient du graphe de classes,
     * jamais d'une liste ecrite ici. Une mecanique neuve sans scenario rend ce test rouge le jour ou
     * son type est ecrit - c'est le seul moyen que cette regle ne se perime pas en silence, le mode
     * de defaillance que ce depot a deja paye plusieurs fois.
     */
    @Test
    void chaqueMecaniqueConcreteDuDomainePorteSonScenario() {
        Set<String> mecaniques = mecaniquesConcretes();

        assertThat(mecaniques)
                .as("ADR-0051 : aucune mecanique concrete sous %s.., cette regle ne prouverait rien",
                        MECANIQUES)
                .isNotEmpty();
        assertThat(scenarios().stream().map(Scenario::mecanique).sorted().toList())
                .as("ADR-0090 : chaque mecanique concrete du domaine est jugee par un scenario de ce"
                        + " fichier, et aucun scenario ne juge une mecanique disparue")
                .containsExactlyElementsOf(mecaniques);
    }

    /**
     * <b>La regle, {@code SCO-1} et {@code SCO-2} generalisees aux trois mecaniques.</b> Un essai
     * faux, puis la bonne reponse : la manche est resolue - elle compte comme presentee - et elle
     * n'est pas une reussite.
     */
    @Test
    void uneMancheTrouveeApresUnEssaiFauxNEstPasComptee() {
        for (Scenario scenario : scenarios()) {
            GameSession apresLeFaux = scenario.jouer(scenario.partie(), scenario.faux());
            GameSession apresLeJuste = scenario.jouer(apresLeFaux, scenario.juste());

            assertThat(apresLeJuste.score().tentatives())
                    .as("%s : la manche est bien resolue, l'enfant garde ses trois essais (M3)",
                            scenario.mecanique())
                    .isEqualTo(1);
            assertThat(apresLeJuste.score().reussites())
                    .as("%s : DEC-0061, instruction 1 - le score cesse de compter comme trouvee une"
                            + " manche que l'enfant n'a pas trouvee du premier coup",
                            scenario.mecanique())
                    .isZero();
        }
    }

    /**
     * <b>{@code SCO-4}, et c'est la morsure qui empeche la regle ci-dessus d'etre decorative.</b>
     * Une mecanique qui cesserait de crediter quoi que ce soit satisferait la premiere methode sans
     * rien tenir ; celle-ci l'interdit. Elle borne le remede par le haut, comme la premiere le borne
     * par le bas.
     */
    @Test
    void uneMancheTrouveeDuPremierEssaiResteUneReussite() {
        for (Scenario scenario : scenarios()) {
            GameSession apresLeJuste = scenario.jouer(scenario.partie(), scenario.juste());

            assertThat(apresLeJuste.score().reussites())
                    .as("%s : SCO-4, une manche trouvee du premier essai reste comptee",
                            scenario.mecanique())
                    .isEqualTo(1);
            assertThat(apresLeJuste.score().tentatives())
                    .as("%s : M3, le score compte des manches resolues, jamais des essais bruts",
                            scenario.mecanique())
                    .isEqualTo(1);
        }
    }

    private static Set<String> mecaniquesConcretes() {
        JavaClasses production = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages(MECANIQUES);
        Set<String> noms = new TreeSet<>();
        for (JavaClass classe : production) {
            if (classe.isAssignableTo(GameMechanic.class) && !classe.isInterface()
                    && !classe.getModifiers().contains(JavaModifier.ABSTRACT)) {
                noms.add(classe.getName());
            }
        }
        return noms;
    }
}
