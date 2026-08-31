package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DecouplageMecaniqueContenuTest {

    private static final String CONTRAT_CONTENU = "com.recreo.domain.content";
    private static final String CONTRAT_MECANIQUE = "com.recreo.domain.mechanic";
    private static final String MECANIQUES = "com.recreo.domain.mechanics..";
    private static final String ADAPTATEUR_DE_RENDU = "com.recreo.adapter.web..";

    /**
     * ADR-0058, R-A : le seul type d'{@code adapter-web} autorise a nommer une mecanique. Ecrit en
     * nom pleinement qualifie et non en motif : un motif laisserait entrer un second point de
     * descente le jour ou quelqu'un nommerait sa classe de la meme famille, et R-A tient
     * precisement a ce que ce point soit <b>un</b> et qu'il soit nomme.
     */
    private static final String POINT_DE_DESCENTE = "com.recreo.adapter.web.PartieController";

    /** (1) Aucun comportement partage possible dans le contrat de contenu. */
    @ArchTest
    static final ArchRule leContratDeContenuEstFerme = classes()
            .that().resideInAPackage(CONTRAT_CONTENU)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : le contrat de contenu ne contient que des interfaces, records et enums");

    /** (2) Le contournement du SessionEngine : impossible de hisser un moteur dans le contrat. */
    @ArchTest
    static final ArchRule leContratDeMecaniqueEstFerme = classes()
            .that().resideInAPackage(CONTRAT_MECANIQUE)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : aucun moteur de session partage dans le contrat de mecanique");

    /** (3) Liste blanche : un generateur concret est hors de portee, ou qu'il soit range. */
    @ArchTest
    static final ArchRule uneMecaniqueNeVoitQueLesContrats = noClasses()
            .that().resideInAPackage(MECANIQUES)
            .should().dependOnClassesThat(resideOutsideOfPackages(
                    "java..", CONTRAT_CONTENU, CONTRAT_MECANIQUE, MECANIQUES))
            .as("ADR-0004 : une mecanique ne depend que du JDK et des deux contrats");

    /** (4) Interdit le switch sur la matiere ou le niveau. */
    @ArchTest
    static final ArchRule uneMecaniqueIgnoreLaMatiere = noClasses()
            .that().resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .should().dependOnClassesThat()
            .haveFullyQualifiedName(CONTRAT_CONTENU + ".ContentDescriptor")
            .as("ADR-0004 : une mecanique ne lit ni la matiere, ni le niveau, ni la competence");

    /** (5) Le sens que la version precedente ne regardait pas. */
    @ArchTest
    static final ArchRule leContenuIgnoreLesMecaniques = noClasses()
            .that().resideInAPackage(CONTRAT_CONTENU + "..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .as("ADR-0004 : un contenu ne connait pas la mecanique qui le jouera");

    /** (6) Une mecanique = un paquet, sinon l'isolation ne compare rien. */
    @ArchTest
    static final ArchRule uneMecaniqueVitDansSonProprePaquet = classes()
            .that().implement(CONTRAT_MECANIQUE + ".GameMechanic")
            .should().resideInAPackage("com.recreo.domain.mechanics.*")
            .allowEmptyShould(false)
            .as("ADR-0004 : une implementation de GameMechanic vit dans com.recreo.domain.mechanics.<mecanique>");

    /**
     * (8) ADR-0058. Les clauses (1) et (2) ferment les deux contrats a une <b>classe</b> porteuse de
     * comportement - c'est la forme qu'avait le {@code SessionEngine} de la version precedente, et
     * c'est la forme des deux contre-exemples ecrits en s00. Une <b>interface</b> dont une methode
     * est statique, par defaut ou privee y loge exactement le meme moteur partage et reste verte
     * sous (1) comme sous (2) : mesure le 2026-08-24 sur cet arbre, deux sondes posees - une
     * normalisation statique dans {@code domain.mechanic}, un normalisateur d'enonce par defaut dans
     * {@code domain.content} - suite complete <b>verte</b>, 615 tests, 0 echec.
     *
     * <p><b>Ce que cette clause interdit reellement</b>, et pourquoi elle n'est pas decorative : la
     * clause (7), "deux mecaniques ne dependent pas l'une de l'autre", ne prouve rien tant que deux
     * mecaniques peuvent etre deux coquilles qui appellent le meme moteur hisse dans un contrat -
     * elles ne dependraient alors pas l'une de l'autre, et tout le comportement vivrait au meme
     * endroit. Le geste est concret et date : la mecanique de saisie d'ADR-0058 a besoin d'une
     * normalisation, la mecanique existante compare des chaines, et un contrat commun est le lieu le
     * moins cher pour la poser.
     *
     * <p><b>Perimetre.</b> Les seules methodes jugees sont celles declarees par une interface des
     * deux paquets de contrat. Les records et les enums en sont hors, et ce n'est pas une exemption
     * concedee : leurs accesseurs et leurs {@code values()}/{@code valueOf()} sont produits par le
     * compilateur, jamais ecrits, et les refuser ferait rougir {@code Score} et
     * {@code SessionStatus} sans qu'aucun comportement n'ait ete partage.
     */
    @ArchTest
    static final ArchRule unContratNeDeclareAucunComportement = methods()
            .that().areDeclaredInClassesThat().resideInAnyPackage(CONTRAT_CONTENU, CONTRAT_MECANIQUE)
            .and().areDeclaredInClassesThat().areInterfaces()
            .should().haveModifier(JavaModifier.ABSTRACT)
            .allowEmptyShould(false)
            .as("ADR-0058 : une interface de contrat ne declare que des methodes abstraites - ni"
                    + " statique, ni par defaut, ni privee. Un contrat qui porte du comportement est"
                    + " un moteur partage, quelle que soit la forme du type qui le porte");

    /**
     * (9) ADR-0058, R-A, due avec le lot qui ouvre la seconde mecanique. Avant s24, la chaine
     * {@code BanditManchot} figurait sur 20 lignes de 3 fichiers de production de ce module :
     * {@code PartieController}, {@code EtatDePartieEnSession} et {@code EtatDePartie} etaient tous
     * les trois types sur la mecanique existante, et l'adaptateur descendait du contrat vers le type
     * concret des la construction de l'etat de partie.
     *
     * <p><b>Ce qu'elle interdit reellement</b>, et pourquoi elle n'est pas decorative : sans elle,
     * la clause 7 d'ADR-0004 - deux mecaniques ne dependent pas l'une de l'autre - serait respectee
     * dans le domaine et defaite dans l'adaptateur, ou un porteur d'etat type sur une mecanique
     * oblige toute mecanique suivante a passer par elle ou a etre transtypee a cote d'elle.</p>
     *
     * <p><b>Perimetre.</b> Tout type de {@code com.recreo.adapter.web..} sauf le point de descente
     * nomme. La forme retenue est celle que {@code theo-archi} recommande pour deux mecaniques :
     * des branches nommees dans le point de descente, plutot qu'un type par mecanique dans
     * l'adaptateur - une abstraction de dispatch ecrite pour deux cas est une abstraction ecrite
     * pour un usage imagine. Le jour d'une troisieme mecanique, c'est la forme qui change, pas cette
     * regle : elle continue de dire qu'il y a <b>un</b> point de descente et qu'il est nomme.</p>
     */
    @ArchTest
    static final ArchRule adapterWebNeNommeAucuneMecaniqueHorsDeSonPointDeDescente = noClasses()
            .that().resideInAPackage(ADAPTATEUR_DE_RENDU)
            .and().doNotHaveFullyQualifiedName(POINT_DE_DESCENTE)
            .should().dependOnClassesThat().resideInAPackage(MECANIQUES)
            .allowEmptyShould(false)
            .as("ADR-0058 R-A : aucun type de l'adaptateur de rendu ne nomme une mecanique hors de"
                    + " son point de descente. Un porteur d'etat type sur une mecanique defait dans"
                    + " l'adaptateur l'isolation que le domaine tient");

    /** (7) Isolation, desormais adossee a (1), (2), (6) et (8). */
    @ArchTest
    static final ArchRule lesMecaniquesNeSeConnaissentPas = slices()
            .matching("com.recreo.domain.mechanics.(*)..")
            .should().notDependOnEachOther()
            .as("ADR-0004 : deux mecaniques ne dependent pas l'une de l'autre");
}
