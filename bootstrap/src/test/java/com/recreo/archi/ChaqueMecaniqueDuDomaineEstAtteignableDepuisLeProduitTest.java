package com.recreo.archi;

import com.recreo.domain.mechanic.GameMechanic;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>Une mecanique qui existe dans {@code domain} et que rien de {@code bootstrap} ne cable n'est
 * jouable par personne, et aucun automate du depot ne le disait.</b> C'est la dette 1 de
 * {@code CLAUDE.md} par son autre face : celle-la nommait une abstraction documentee partout et
 * jamais implementee, celle-ci nomme une mecanique implementee, testee unitairement, et
 * inatteignable depuis le produit. Les deux ont le meme mode de defaillance - <b>tout est vert</b> -
 * et le meme prix : on croit avoir livre.
 *
 * <p><b>Le fait qui rend cette regle non decorative, et il est mesure et date.</b>
 * {@code com.recreo.domain.mechanics} porte trois tranches - {@code banditmanchot}, {@code saisie},
 * {@code designation} - et {@code MecaniquesConfiguration} publie deux beans. {@code Designation},
 * {@code DesignationSession}, {@code DesignationMove} et {@code DesignationVerdict} sont dans
 * l'arbre depuis s28 (2026-09-04) ; a la cloture de s31 (2026-09-08), quatre sprints plus tard,
 * aucune ligne de {@code bootstrap} ne les nomme. Les 1396 tests de surefire etaient verts pendant
 * ces quatre sprints, la clause 7 d'{@code ADR-0004} ("les mecaniques ne se connaissent pas") etait
 * verte, la clause 10 d'{@code ADR-0080} etait verte, et le constat a du etre fait a la main par
 * {@code nadia-prof} en comptant le catalogue servi. <b>Ce garde-fou est rouge le jour ou il est
 * ecrit</b>, et c'est la preuve la plus forte qu'il interdit quelque chose de reel : il ne borne pas
 * un risque imagine, il decrit l'etat du depot.
 *
 * <p><b>Pourquoi un cliquet et non une regle seche.</b> Une regle seche serait rouge aujourd'hui, et
 * une regle qu'on ne peut pas commiter est une regle qu'on ecrira "plus tard" - c'est-a-dire le jour
 * ou elle gene, donc une regle qu'on negocie. Le plafond vaut donc <b>exactement</b> la taille de la
 * dette (une mecanique non cablee, mesuree le 2026-09-08), il est refuse en egalite dans les deux
 * sens comme {@code ADR-0067} l'exige, et il descend a zero dans le meme diff que le bean qui cable
 * la troisieme mecanique. Meme mecanisme, meme motif et meme forme que
 * {@link AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest}.
 *
 * <p><b>Ce que ce garde-fou ne dit pas.</b> Un bean de mecanique ne rend pas la mecanique jouable :
 * il faut encore une branche du point de descente d'{@code adapter-web}, un gabarit, une route et une
 * entree de catalogue. Cette moitie-la n'est pas gardee ici et elle est gardee ailleurs - par
 * {@link ChaqueMecaniqueNommeeParLeCatalogueResoutTest} pour l'entree de catalogue, par
 * {@code ToutEcranEchangeEstUnDocumentJouableTest} pour la route, et par
 * {@code LaConsigneDUnJeuServiAtteintLEcranDeMancheTest} pour l'ecran. <b>Une propriete gardee a
 * moitie qui se croit gardee en entier est la faute que ce depot a sanctionnee plusieurs fois</b> ;
 * elle est donc decoupee, et chaque moitie est nommee avec son garde.
 *
 * <p>{@code ADR-0013}, les trois obligations : <b>perimetre derive</b> - les mecaniques viennent de
 * l'implantation de {@link GameMechanic} sous {@code com.recreo.domain.mechanics..}, jamais d'une
 * liste ecrite ici, et le cablage vient des dependances reelles vers chaque type, jamais du nom d'un
 * fichier de configuration ; <b>derivation totale</b> - toute mecanique concrete est jugee, aucune
 * n'est exemptee ; <b>mise a l'epreuve</b> - le juge est pur et mord dans les deux sens du plafond,
 * plus une morsure sur le graphe reel qui verifie que le predicat de cablage distingue vraiment une
 * mecanique cablee d'une mecanique qui ne l'est pas.
 */
class ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest {

    private static final String MECANIQUES = "com.recreo.domain.mechanics.";

    /** La couche qui a le droit - et le devoir - de nommer toutes les mecaniques (ADR-0004). */
    private static final String CABLAGE = "com.recreo.bootstrap.";

    /**
     * Le plafond vaut exactement la dette. Il valait <b>1</b> du 2026-09-08 (mesure sur l'arbre de
     * {@code round-two} a {@code f503b4e5}) au s34, lot L-a : {@code Designation} n'avait alors aucun
     * bean dans {@code MecaniquesConfiguration}. Il descend a <b>0</b> dans le meme diff que ce bean
     * (ADR-0080, T4), et il ne remonte jamais sans qu'une mecanique neuve non cablee l'accompagne -
     * ce que ce garde-fou refuse justement de laisser passer.
     */
    private static final int PLAFOND_DE_MECANIQUES_NON_CABLEES = 0;

    /**
     * Les classes de production des cinq modules, telles que le classpath de test de {@code
     * bootstrap} les rend. Meme perimetre et meme option que {@code DecouplageMecaniqueContenuTest}.
     */
    private static JavaClasses classesDeProduction() {
        return new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.recreo");
    }

    /**
     * Les mecaniques concretes du domaine, derivees du contrat qu'elles implantent et de la tranche
     * ou elles vivent - jamais d'une liste de noms. Une mecanique neuve entre dans cette population
     * le jour ou son type est ecrit, sans qu'une ligne de ce fichier change.
     */
    static Set<String> mecaniquesConcretes(JavaClasses classes) {
        Set<String> noms = new TreeSet<>();
        for (JavaClass classe : classes) {
            if (classe.getName().startsWith(MECANIQUES)
                    && classe.isAssignableTo(GameMechanic.class)
                    && !classe.isInterface()
                    && !classe.getModifiers().contains(com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT)) {
                noms.add(classe.getName());
            }
        }
        return noms;
    }

    /**
     * Vrai quand au moins un type de {@code bootstrap} depend directement de cette mecanique. Le
     * critere est la dependance reelle du graphe, pas la presence d'un nom dans un fichier de
     * configuration : un bean commente, un import laisse en place ou une classe de configuration
     * renommee ne trompent pas ce predicat.
     */
    static boolean estCablee(JavaClass mecanique) {
        return mecanique.getDirectDependenciesToSelf().stream()
                .anyMatch(dependance ->
                        dependance.getOriginClass().getName().startsWith(CABLAGE));
    }

    static Set<String> mecaniquesNonCablees(JavaClasses classes) {
        Set<String> noms = new TreeSet<>();
        for (String nom : mecaniquesConcretes(classes)) {
            if (!estCablee(classes.get(nom))) {
                noms.add(nom);
            }
        }
        return noms;
    }

    /**
     * Le juge, pur : ni disque, ni classpath. Le plafond est un parametre plutot qu'une constante
     * lue directement, pour que la mise a l'epreuve puisse mordre dans les deux sens - le compte qui
     * monte, et un plafond que rien ne justifie plus.
     */
    static void verifier(Set<String> mecaniquesConcretes, Set<String> nonCablees, int plafond) {
        assertThat(mecaniquesConcretes)
                .as("ADR-0051 : aucune mecanique concrete sous " + MECANIQUES + ".., ce garde-fou ne"
                        + " prouverait rien")
                .isNotEmpty();

        int compte = nonCablees.size();
        if (compte > plafond) {
            throw new AssertionError(compte + " mecanique(s) du domaine que rien de " + CABLAGE
                    + ".. ne cable, contre " + plafond + " toleree(s) : " + nonCablees
                    + ". Une mecanique que bootstrap ne nomme pas n'est jouable par personne, quels"
                    + " que soient ses tests unitaires (dette 1 de CLAUDE.md, autre face). Publier"
                    + " son bean dans MecaniquesConfiguration, puis baisser"
                    + " PLAFOND_DE_MECANIQUES_NON_CABLEES a " + compte + " dans le meme diff.");
        }
        if (compte < plafond) {
            throw new AssertionError("PLAFOND_DE_MECANIQUES_NON_CABLEES vaut " + plafond
                    + " mais seulement " + compte + " mecanique(s) ne sont pas cablees : un plafond"
                    + " superieur au compte reel est une place libre, pas un cliquet (ADR-0013)."
                    + " Baisser PLAFOND_DE_MECANIQUES_NON_CABLEES a " + compte + ".");
        }
    }

    /**
     * Le garde-fou tel qu'il tourne en CI : le graphe reel, le plafond reel. Vert depuis s34 parce
     * que le compte et le plafond valent tous les deux zero (le bean de {@code Designation} est
     * publie, ADR-0080 T4) ; rouge le jour ou une quatrieme mecanique entre sans son bean.
     */
    @Test
    void chaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduit() {
        JavaClasses classes = classesDeProduction();
        verifier(mecaniquesConcretes(classes), mecaniquesNonCablees(classes),
                PLAFOND_DE_MECANIQUES_NON_CABLEES);
    }

    /**
     * <b>Le constat, epingle plutot que raconte.</b> Trois mecaniques concretes dans le domaine,
     * <b>toutes cablees</b> depuis que {@code MecaniquesConfiguration} publie le bean de
     * {@code Designation} (ADR-0080, T4, lot L-a). Avant ce lot, exactement une echappait au
     * cablage ; si ce test devient faux, c'est que l'etat du depot a change et que le plafond doit
     * bouger - c'est precisement ce que le cliquet ci-dessus dit en rouge.
     */
    @Test
    void leDepotPorteTroisMecaniquesConcretesToutesCablees() {
        JavaClasses classes = classesDeProduction();

        assertThat(mecaniquesConcretes(classes))
                .as("les trois tranches de com.recreo.domain.mechanics portent chacune sa mecanique")
                .containsExactly(
                        "com.recreo.domain.mechanics.banditmanchot.BanditManchot",
                        "com.recreo.domain.mechanics.designation.Designation",
                        "com.recreo.domain.mechanics.saisie.Saisie");

        assertThat(mecaniquesNonCablees(classes))
                .as("ADR-0080, T4 : les trois mecaniques du domaine sont desormais publiees par"
                        + " MecaniquesConfiguration")
                .isEmpty();
    }

    /**
     * <b>Morsure 1.</b> Le predicat de cablage vaut vrai sur les trois mecaniques reelles,
     * <b>sur le graphe reel et non sur une fixture</b> : les trois beans que
     * {@code MecaniquesConfiguration} publie desormais sont tous vus comme cables.
     *
     * <p><b>Ce que cette morsure ne prouve plus, dit plutot que tu.</b> Avant le bean de
     * {@code Designation} (ADR-0080, T4), cette meme methode distinguait deux mecaniques cablees
     * d'une troisieme qui ne l'etait pas, sur le graphe reel. Ce cas negatif reel a disparu avec la
     * dette qu'il montrait : aucune mecanique concrete du domaine n'est plus non cablee aujourd'hui,
     * et en fabriquer une pour la seule preuve de ce predicat serait du code mort. La capacite du
     * predicat a distinguer un nom cable d'un nom qui ne l'est pas reste verifiee par les morsures 2
     * et 3 ci-dessous, sur des ensembles fabriques - c'est ce que {@code verifier} sait juger sans
     * dependre d'une mecanique domaine reellement non cablee.
     */
    @Test
    void lePredicatDeCablageEstVraiSurLesTroisMecaniquesReelles() {
        JavaClasses classes = classesDeProduction();

        assertThat(estCablee(classes.get("com.recreo.domain.mechanics.banditmanchot.BanditManchot")))
                .as("BanditManchot est publie par MecaniquesConfiguration")
                .isTrue();
        assertThat(estCablee(classes.get("com.recreo.domain.mechanics.saisie.Saisie")))
                .as("Saisie est publiee par MecaniquesConfiguration")
                .isTrue();
        assertThat(estCablee(classes.get("com.recreo.domain.mechanics.designation.Designation")))
                .as("Designation est publiee par MecaniquesConfiguration depuis ADR-0080, T4")
                .isTrue();
    }

    /** <b>Morsure 2.</b> Une mecanique non cablee de plus est rouge, et le message dit quoi faire. */
    @Test
    void uneMecaniqueNonCableeDePlusFaitEchouerLeGardeFou() {
        Set<String> concretes = new TreeSet<>(List.of("A", "B", "C", "D"));
        Set<String> nonCablees = new TreeSet<>(List.of("C", "D"));

        assertThatThrownBy(() -> verifier(concretes, nonCablees, PLAFOND_DE_MECANIQUES_NON_CABLEES))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("2 mecanique(s)")
                .hasMessageContaining("MecaniquesConfiguration")
                .hasMessageContaining("PLAFOND_DE_MECANIQUES_NON_CABLEES");
    }

    /**
     * <b>Morsure 3.</b> L'autre borne : un plafond que le compte reel ne justifie plus est rouge lui
     * aussi. C'est ce qui force le plafond a descendre dans le diff qui cable la troisieme mecanique,
     * au lieu de laisser une place libre pour la quatrieme (ADR-0067, "en egalite, jamais en
     * plancher").
     */
    @Test
    void unPlafondSuperieurAuCompteReelFaitEchouerLeGardeFou() {
        Set<String> concretes = new TreeSet<>(List.of("A", "B", "C"));

        assertThatThrownBy(() -> verifier(concretes, Set.of(), PLAFOND_DE_MECANIQUES_NON_CABLEES + 1))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("place libre");
    }

    /** La clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(Set.of(), Set.of(), PLAFOND_DE_MECANIQUES_NON_CABLEES))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }
}
