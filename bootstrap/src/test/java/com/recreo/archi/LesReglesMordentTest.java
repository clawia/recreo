package com.recreo.archi;

import com.recreo.domain.content.ContratAuNiveauEnumereContreExemple;
import com.recreo.domain.content.NiveauEnumereContreExemple;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleName;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LesReglesMordentTest {

    /** Un contre-exemple qui atterrit dans le code de production casse le build. */
    @ArchTest
    static final ArchRule aucunContreExempleEnProduction = noClasses()
            .that().resideInAPackage("com.recreo..")
            .should().haveSimpleNameEndingWith("ContreExemple")
            .as("ADR-0003 : les contre-exemples restent dans bootstrap/src/test");

    private static JavaClasses contreExemples(String paquet) {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.OnlyIncludeTests())
                .importPackages(paquet);
    }

    /**
     * Le contre-exemple d'une regle d'existence n'est pas une classe fautive mais un monde ou le
     * type annonce n'est pas : c'est allowEmptyShould(false) qui doit alors mordre.
     */
    private static JavaClasses productionSansLeContrat(String paquet) {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages(paquet);
    }

    /**
     * layeredArchitecture() echoue par defaut si une couche declaree est vide, avant meme de
     * regarder la dependance visee : un import restreint au seul contre-exemple laisserait donc
     * "Application", "Web", "Contenu" et "Bootstrap" vides et masquerait la vraie violation
     * derriere une autre. Il faut donc les classes de production de chaque couche, en plus du
     * contre-exemple.
     */
    private static JavaClasses productionEtContreExemples() {
        return new ClassFileImporter().importPackages("com.recreo");
    }

    @Test
    void laPureteDuDomaineEchoueSurUnDomaineQuiUtiliseSpring() {
        assertThatThrownBy(() -> PureteDesCouchesTest.leDomaineNeDependQueDuJdk
                .check(contreExemples("com.recreo.domain.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DomainePollueContreExemple")
                .hasMessageContaining("org.springframework");
    }

    @Test
    void leContratDeMecaniqueRefuseUnMoteurPartage() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme
                .check(contreExemples("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MoteurPartageContreExemple");
    }

    @Test
    void uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ListContentSource");
    }

    @Test
    void uneMecaniqueQuiLitLaMatiereEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ContentDescriptor");
    }

    @Test
    void unContenuQuiConnaitSaMecaniqueEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ContenuQuiSaitJouerContreExemple")
                .hasMessageContaining("GameMechanic");
    }

    /**
     * ADR-0001, critere 4 de S0-1 : avant ce test, aucun contre-exemple ne prouvait que la regle
     * de couches echoue reellement sur une dependance vers l'exterieur.
     */
    @Test
    void leDomaineNeDoitPasDependreDeLApplication() {
        assertThatThrownBy(() -> ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur
                .check(productionEtContreExemples()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DomaineDependantDeLApplicationContreExemple");
    }

    /** ADR-0001 : idem pour l'isolation entre adaptateurs, jamais mise en echec jusqu'ici. */
    @Test
    void deuxAdaptateursNeDoiventPasSeConnaitre() {
        assertThatThrownBy(() -> ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("AdaptateurWebDependantDuContenuContreExemple");
    }

    /**
     * ADR-0058, R-A : l'adaptateur de rendu ne nomme aucune mecanique hors de son point de descente.
     * Le contre-exemple est la forme reelle, pas une fixture : c'est ainsi qu'{@code EtatDePartie}
     * etait ecrit jusqu'a s24, et cette regle est ce qui l'a fait changer.
     *
     * <p>La seconde assertion est celle qui rend la morsure non decorative : le message doit nommer
     * la classe fautive <b>et</b> la mecanique dont elle depend. Une morsure qui se contenterait du
     * nom de la classe serait verte sur une regle qui refuserait n'importe quelle dependance de ce
     * module.</p>
     */
    @Test
    void unPorteurDEtatTypeSurUneMecaniqueHorsDuPointDeDescenteEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest
                .adapterWebNeNommeAucuneMecaniqueHorsDeSonPointDeDescente
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PorteurDEtatTypeSurUneMecaniqueContreExemple")
                .hasMessageContaining("BanditManchotSession");
    }

    /**
     * ADR-0044 : la selection des jeux montres reste derriere le port entrant. Le contre-exemple
     * est ecrit comme le chemin le moins cher l'ecrirait - le catalogue filtre dans l'adaptateur
     * web en lisant le descripteur de contenu - parce que c'est cette forme-la, et pas une faute
     * imaginaire, que la regle existe pour arreter.
     */
    @Test
    void uneVueQuiFiltreLeCatalogueParNiveauEstRefusee() {
        assertThatThrownBy(() -> LeChoixDesJeuxResteDerriereLePortTest
                .aucunAdaptateurDeRenduNeLitLeDescripteurDeContenu
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VueQuiFiltreLeCatalogueContreExemple")
                .hasMessageContaining("ContentDescriptor");
    }

    /** ADR-0002 : la purete de l'application, symetrique de celle du domaine, jamais eprouvee. */
    @Test
    void lApplicationNeDoitPasDependreDUnFramework() {
        assertThatThrownBy(() -> PureteDesCouchesTest.lApplicationNeDependQueDuJdkEtDuDomaine
                .check(contreExemples("com.recreo.application.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.application.contreexemple.ApplicationPollueeContreExemple")
                .hasMessageContaining("org.springframework");
    }

    /** ADR-0003 : la regle qui garde les contre-exemples eux-memes doit mordre aussi. */
    @Test
    void unContreExempleEnProductionEstRefuse() {
        assertThatThrownBy(() -> aucunContreExempleEnProduction
                .check(contreExemples("com.recreo.domain.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.contreexemple.DomainePollueContreExemple");
    }

    /** ADR-0004 (1) : la fermeture du contrat de contenu, pendant de celle du contrat de mecanique. */
    @Test
    void unMoteurDeContenuPartageEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.MoteurDeContenuPartageContreExemple");
    }

    /** ADR-0004 (6) : sans elle, l'isolation entre mecaniques ne decoupe plus rien. */
    @Test
    void uneMecaniqueHorsDeSonPaquetEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet
                .check(contreExemples("com.recreo.domain.mechanics")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.mechanics.MecaniqueHorsPaquetContreExemple");
    }

    /**
     * ADR-0004 (7) : la regle exacte qui est restee verte 25 sprints sur la version precedente sans
     * rien interdire. Elle n'avait, ici non plus, jamais eu a echouer.
     */
    @Test
    void deuxMecaniquesNeDoiventPasSeConnaitre() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.lesMecaniquesNeSeConnaissentPas
                .check(contreExemples("com.recreo.domain.mechanics")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.mechanics.seconde.MecaniqueSecondeContreExemple")
                .hasMessageContaining("com.recreo.domain.mechanics.premiere.MecaniquePremiereContreExemple");
    }

    /**
     * ADR-0058, premiere forme : le moteur partage hisse dans le contrat de <b>mecanique</b> sous
     * une methode <b>statique</b> d'interface. Le contre-exemple passe volontairement
     * {@code leContratDeMecaniqueEstFerme} - c'est une interface - et c'est ce vert-la qui a ete
     * mesure le 2026-08-24 avant que cette regle n'existe.
     */
    @Test
    void unMoteurPartageSousFormeDeMethodeStatiqueDInterfaceEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.unContratNeDeclareAucunComportement
                .check(contreExemples("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NormalisationPartageeContreExemple")
                .hasMessageContaining("replier");

        DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme
                .check(contreExemples("com.recreo.domain.mechanic")
                        .that(simpleName("NormalisationPartageeContreExemple")));
    }

    /**
     * ADR-0058, seconde forme, et sur l'autre contrat : une methode <b>par defaut</b> dans le contrat
     * de <b>contenu</b>. Les deux formes sont verifiees separement parce qu'un predicat qui n'aurait
     * refuse que {@code static} laisserait passer celle-ci, et reciproquement.
     */
    @Test
    void unComportementParDefautDansLeContratDeContenuEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.unContratNeDeclareAucunComportement
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EnonceNormaliseContreExemple")
                .hasMessageContaining("enonceNormalise");

        DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme
                .check(contreExemples("com.recreo.domain.content")
                        .that(simpleName("EnonceNormaliseContreExemple")));
    }

    /** ADR-0005 : le nom brule. Le message doit citer le type fautif, pas seulement le nom brule. */
    @Test
    void unTypeNommeRuleSetEstRefuse() {
        assertThatThrownBy(() -> AbstractionsCiteesTest.aucunTypeNommeRuleSet
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.RuleSet");
    }

    /**
     * ADR-0005 : une regle d'existence ne peut pas avoir de classe fautive - la faute serait une
     * absence. Ce qui doit mordre est allowEmptyShould(false) ; le desactiver rend ce test rouge.
     */
    @Test
    void uneAbstractionCiteeQuiDisparaitEstRefusee() {
        assertThatThrownBy(() -> AbstractionsCiteesTest.contentSourceExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : ContentSource existe et est une interface")
                .hasMessageContaining("failed to check any classes");

        assertThatThrownBy(() -> AbstractionsCiteesTest.itemExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : Item existe et est une interface")
                .hasMessageContaining("failed to check any classes");

        assertThatThrownBy(() -> AbstractionsCiteesTest.gameMechanicExiste
                .check(productionSansLeContrat("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : GameMechanic existe et est une interface")
                .hasMessageContaining("failed to check any classes");
    }

    /** ADR-0006 : les deux orthographes du nom brule, un contre-exemple chacune. */
    @Test
    void unTypeDeDifficulteDansLeDomaineEstRefuse() {
        assertThatThrownBy(() -> DifficulteTest.aucunTypeDifficultyDansLeDomaine
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.DifficultyConfigContreExemple");

        assertThatThrownBy(() -> DifficulteTest.aucunTypeDifficulteDansLeDomaine
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.DifficulteConfigContreExemple");
    }

    /**
     * ADR-0006 : la regle qui tient la propriete de fond, et non seulement un nom. Ces deux
     * contre-exemples ont montre qu'elle ne mordait sur aucune des deux formes reelles de la dette
     * (primitif, argument generique) ; voir l'amendement du 2026-08-04 dans DifficulteTest.
     */
    @Test
    void uneBorneArithmetiqueDansLeContratDeContenuEstRefusee() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageArithmetiqueContreExemple.sommeMaximale")
                .hasMessageContaining("int");
    }

    /**
     * ADR-0006, amendement du 2026-09-04 : le plafond de l'exemption geometrique. L'exemption admet
     * un {@code int} porte par un type dont les champs d'instance sont exactement {@code x},
     * {@code y}, {@code largeur} et {@code hauteur} ; ce contre-exemple garde les quatre et en ajoute
     * une cinquieme, qui est une borne d'arithmetique. C'est la seule route par laquelle la dette 2
     * de CLAUDE.md pourrait rentrer par l'exemption, et elle est fermee.
     *
     * <p>Ce que cette morsure ne prouve pas, dit plutot que suppose : le contre-exemple aurait aussi
     * ete refuse avant l'amendement. Ce qu'elle tient est le plafond - elle echoue le jour ou
     * l'exemption est elargie a un record d'entiers.</p>
     */
    @Test
    void unCadreEtenduDUneBorneArithmetiqueEstRefuse() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.CadreEtenduDUneBorneContreExemple.sommeMaximale")
                .hasMessageContaining("int");
    }

    /** ADR-0006 : la meme regle, sur la borne cachee dans un argument generique. */
    @Test
    void uneBorneArithmetiqueCacheeDansUneCollectionEstRefusee() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageEnCollectionContreExemple.operations")
                .hasMessageContaining("com.recreo.domain.content.contreexemple.OperationContreExemple");

        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageEnCollectionContreExemple.bornes")
                .hasMessageContaining("java.lang.Integer");
    }

    /**
     * ADR-0012 : la moitie "interdiction" de la decision n'a pas de regle a elle - elle s'appuie sur
     * la regle d'ADR-0006. Ce test la pin sur le cas nomme par ADR-0012, pour qu'un assouplissement
     * futur de cette regle au motif d'un "parametre de partie" devienne rouge en citant ADR-0012, et
     * pas seulement la borne arithmetique.
     */
    @Test
    void uneLongueurDePartieDansLeContratDeContenuEstRefusee() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageDeLongueurDePartieContreExemple.nombreDeManches")
                .hasMessageContaining("int");
    }

    /**
     * ADR-0069 : la meme regle, sur un booleen. Les trois morsures ci-dessus portent toutes sur une
     * <b>grandeur</b> - un entier, un entier cache dans un argument generique - et laissaient croire
     * que la regle ne visait que les bornes chiffrees. Elle refuse aussi un mode declare en booleen,
     * et c'est ce que la conception de la saisie a rencontre : la section 1 d'ADR-0058 decidait "un
     * booleen formeExacteExigee" sur l'item, forme mesuree rouge sur l'arbre reel le 2026-08-30.
     * Sans cette morsure, la portee reelle de la regle restait a decouvrir par un lot.
     */
    @Test
    void unModeDeComparaisonEnBooleenDansLeContratDeContenuEstRefuse() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ModeDeSaisieEnBooleenContreExemple.formeExacteExigee")
                .hasMessageContaining("boolean");
    }

    /**
     * ADR-0095 : la morsure est double, et c'est la seconde moitie qui justifie la regle.
     *
     * <p>Le contre-exemple est le chemin le moins cher vers un ordre des niveaux : un
     * {@code enum Niveau} pose dans le contrat de contenu, cite par un descripteur, et
     * {@code Comparator.comparing(Niveau::valueOf)} pour trier. La regle neuve le refuse.
     *
     * <p>La regle d'ADR-0006 qui est censee tenir la meme propriete, elle, <b>reste verte</b> sur
     * exactement ce materiau : son {@code estPermis} admet tout type de
     * {@code com.recreo.domain.content}. C'est mesure ici plutot qu'affirme dans l'ADR, parce
     * qu'une regle neuve dont on ne prouve pas qu'elle ferme quelque chose de neuf est
     * decorative. L'import est restreint aux deux contre-exemples : sur le paquet entier, la regle
     * d'ADR-0006 rougirait de toute facon sur les quatre autres, et la mesure ne dirait rien.
     */
    @Test
    void unNiveauEnumereDansLeContratEstRefuseEtLaRegleDAdr0006LeLaissePasser() {
        JavaClasses lesDeuxContreExemples = new ClassFileImporter().importClasses(
                NiveauEnumereContreExemple.class, ContratAuNiveauEnumereContreExemple.class);

        assertThatThrownBy(() -> LOrdreDesNiveauxResteUneDonneeTest.aucunNiveauNEstUneConstanteDEnumeration
                .check(lesDeuxContreExemples))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.NiveauEnumereContreExemple")
                .hasMessageContaining("CE1");

        DifficulteTest.leContratDeContenuNePorteQueDuTexte.check(lesDeuxContreExemples);
    }

    /**
     * ADR-0095 : la dette 1 de CLAUDE.md - une abstraction citee partout et jamais implementee.
     * Une regle d'existence n'a pas de classe fautive, sa faute est une absence : ce qui doit
     * mordre est {@code allowEmptyShould(false)}, et le desactiver rend ce test rouge.
     */
    @Test
    void uneSequenceDesNiveauxQuiDisparaitEstRefusee() {
        assertThatThrownBy(() -> LOrdreDesNiveauxResteUneDonneeTest.laSequenceDesNiveauxExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0095 : la sequence des niveaux existe dans le code")
                .hasMessageContaining("failed to check any classes");
    }

    /** ADR-0007 : le dixieme chargeur, hors de l'adaptateur de contenu. */
    @Test
    void unChargeurHorsDeLAdaptateurDeContenuEstRefuse() {
        assertThatThrownBy(() -> ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers
                .check(contreExemples("com.recreo.bootstrap.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.bootstrap.contreexemple.ChargeurSauvageContreExemple")
                .hasMessageContaining("java.nio.file");
    }

    /**
     * ADR-0080, clause 10 : la conception par coordonnees se trahit par une dependance directe a
     * {@code CadreDeZone}. Contre-exemple pris a sa forme la moins chere : le predicat de resolution
     * d'un point en zone, ecrit dans une mecanique.
     */
    @Test
    void uneMecaniqueQuiLitLeCadreDUneZoneEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaGeometrie
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueGeometriqueContreExemple")
                .hasMessageContaining("CadreDeZone");
    }

    /**
     * ADR-0104, clause 13 : une mecanique a propositions qui lit le prefixe d'une proposition, ou qui
     * la passe a une expression reguliere. Les deux formes doivent mordre, chacune nommee.
     */
    @Test
    void uneMecaniqueAPropositionsQuiLitUnePropositionEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest
                .uneMecaniqueAPropositionsNeLitJamaisCeQuUnePropositionEcrit
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueQuiLitSesPropositionsContreExemple")
                .hasMessageContaining("String.startsWith")
                .hasMessageContaining("java.util.regex");
    }

    /**
     * ADR-0080, clause 11 : un record du contrat de contenu qui ecrit a la main le predicat que la
     * clause 10 existe pour interdire ailleurs.
     */
    @Test
    void unRecordDuContratDeContenuQuiDeclareUneMethodeEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest
                .aucunRecordDuContratDeContenuNeDeclareDeMethode
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CadreAvecResolutionContreExemple")
                .hasMessageContaining("contient");
    }

    /**
     * ADR-0088, clause 12 : un verdict de mecanique qui juge une manche sans porter son enonce. Le
     * contre-exemple est la forme reelle et non une caricature - c'est exactement l'etat de
     * {@code DesignationVerdict} du 2026-09-04 au 2026-09-08, et celui des deux autres verdicts
     * avant ADR-0084.
     */
    @Test
    void unVerdictDeMecaniqueSansSaQuestionEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.unVerdictDeMecaniquePorteSonEnonce
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VerdictSansSaQuestionContreExemple")
                .hasMessageContaining("juge une manche et ne porte pas son enonce");
    }

    /**
     * ADR-0011 : une regle d'existence n'a pas de classe fautive - la faute serait une absence,
     * meme mecanisme que uneAbstractionCiteeQuiDisparaitEstRefusee (ADR-0005).
     */
    @Test
    void unePorteDErreurAbsenteEstRefusee() {
        assertThatThrownBy(() -> GestionDesErreursTest.uneAdviceDErreurExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0011 : une porte de rendu d'erreur existe dans adapter-web")
                .hasMessageContaining("failed to check any classes");
    }

    /** ADR-0011 : sans elle, un gestionnaire d'erreur pourrait rendre du JSON brut sans qu'aucun test ne le refuse. */
    @Test
    void unGestionnaireDErreurQuiRendUnCorpsBrutEstRefuse() {
        assertThatThrownBy(() -> GestionDesErreursTest.uneErreurNeRendPasUnCorpsBrut
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GestionnaireErreurBruteContreExemple")
                .hasMessageContaining("ResponseEntity");
    }

    /**
     * ADR-0018 : le contre-exemple est correct sur tous les autres criteres et muet sur celui-la.
     * C'est le gestionnaire mesure sur le jar de 099e750, qui rendait l'ecran d'erreur a l'enfant
     * sans ecrire une ligne pour l'equipe.
     */
    @Test
    void unGestionnaireDErreurQuiNeJournalisePasEstRefuse() {
        assertThatThrownBy(() -> GestionDesErreursTest.toutGestionnaireDErreurJournalise
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GestionnaireMuetContreExemple")
                .hasMessageContaining("org.slf4j.Logger");
    }

    /** ADR-0018 : sans elle, un type du JDK peut a nouveau servir de canal a l'attendu et avaler l'inattendu. */
    @Test
    void unGestionnaireQuiAttrapeUnTypeDuJdkEstRefuse() {
        assertThatThrownBy(() -> GestionDesErreursTest.unGestionnaireNommeUnTypeDeRecreoOuLaCaptureGenerale
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GestionnaireTypeDuJdkContreExemple")
                .hasMessageContaining("java.lang.IllegalStateException");
    }

    /**
     * ADR-0019 : la route neuve qui refait le cycle lire-calculer-ecrire a la main, dans sa forme la
     * plus discrete - la session tiree de la requete, jamais declaree en parametre.
     */
    @Test
    void unControleurQuiToucheLaSessionHttpEstRefuse() {
        assertThatThrownBy(() -> AccesALEtatDeSessionTest.unSeulTypeToucheLaSessionHttp
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(
                        "com.recreo.adapter.web.contreexemple.ControleurQuiToucheLaSessionContreExemple")
                .hasMessageContaining("jakarta.servlet.http.HttpSession");
    }

    /**
     * ADR-0019 : une regle d'existence n'a pas de classe fautive - la faute serait une absence, meme
     * mecanisme que uneAbstractionCiteeQuiDisparaitEstRefusee (ADR-0005).
     */
    @Test
    void unPorteurDEtatDeSessionAbsentEstRefuse() {
        assertThatThrownBy(() -> AccesALEtatDeSessionTest.leSeulPorteurDEtatDeSessionExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(
                        "ADR-0019 : EtatDePartieEnSession existe et n'est visible que de son paquet")
                .hasMessageContaining("failed to check any classes");
    }
}
