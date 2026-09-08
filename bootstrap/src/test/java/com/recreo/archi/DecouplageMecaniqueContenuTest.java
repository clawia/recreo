package com.recreo.archi;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * (10) ADR-0080, decision 6 : la regle centrale de l'ADR de la mecanique de designation. Ce
     * qu'elle interdit reellement : la conception par coordonnees - le client envoie {@code (x, y)},
     * le serveur cherche quelle zone contient ce point - qui n'est pas jouable au clavier et qui
     * ferait entrer la geometrie dans une mecanique. Pour l'ecrire, il faut lire un
     * {@code CadreDeZone} dans la mecanique ; c'est le seul point du systeme ou cette conception se
     * trahit sans ambiguite. Calquee sur la clause (4).
     *
     * <p>Elle ne garde pas l'adaptateur web - un {@code @RequestParam int x} sur un controleur est un
     * entier pour ArchUnit, et aucun modele de types ne dira jamais qu'il porte une abscisse. Cette
     * moitie-la est tenue hors d'ArchUnit, par un garde-fou de balayage sur le document servi et par
     * la liste d'interdits du JavaScript maison (ADR-0080, garde-fous 1 et 2).</p>
     *
     * <p>Due avec la tache qui cree {@code CadreDeZone} : l'ecrire avant serait une regle a
     * population vide (ADR-0051).</p>
     */
    @ArchTest
    static final ArchRule uneMecaniqueIgnoreLaGeometrie = noClasses()
            .that().resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .should().dependOnClassesThat()
            .haveFullyQualifiedName(CONTRAT_CONTENU + ".CadreDeZone")
            .allowEmptyShould(false)
            .as("ADR-0080 : une mecanique transporte une zone et ne lit jamais son cadre. Une mecanique"
                    + " qui touche a la geometrie est une mecanique qui resout un point en zone, donc"
                    + " un verdict qui depend d'une coordonnee envoyee par le navigateur");

    /**
     * (11) ADR-0080, decision 6 : ferme un trou de la clause (8). Celle-ci refuse a une
     * <b>interface</b> des deux paquets de contrat toute methode statique, par defaut ou privee, et
     * exclut explicitement les records - leurs accesseurs sont produits par le compilateur, et les
     * refuser ferait rougir {@code Score} et {@code SessionStatus} sans qu'aucun comportement n'ait
     * ete partage. Mais un <b>record</b> peut declarer une methode ecrite a la main -
     * {@code CadreDeZone.contient(int x, int y)} en serait l'exemple exact, le predicat de
     * resolution d'un point en zone pose dans le contrat de contenu, ou la clause (8) ne le regarde
     * pas. Sans cette clause, la propriete de la clause (10) ne tiendrait plus que par un seul fil -
     * le fait qu'une mecanique doive quand meme nommer le type pour appeler la methode.
     *
     * <p>Forme : sur chaque record des deux paquets de contrat, toute methode declaree porte le nom
     * d'un de ses champs (l'accesseur genere par le compilateur, ou un accesseur ecrit a la main sous
     * le meme nom), ou est {@code equals}, {@code hashCode} ou {@code toString}. Les constructeurs ne
     * sont pas des methodes et ne sont pas juges ici.</p>
     *
     * <p>Population et etat au 2026-09-03, verifies par lecture et non supposes : les quatre records
     * de {@code com.recreo.domain.content} ({@code Question}, {@code ReponseAEcrire},
     * {@code ContentDescriptor}, {@code ItemId}) ne declarent aucune methode - cette clause est donc
     * verte aujourd'hui sur une population non vide, et c'est la seule des deux regles de ce lot qui
     * peut etre ecrite avant le reste : une regle ecrite le jour ou elle gene est une regle qu'on
     * negocie.</p>
     */
    @ArchTest
    static final ArchRule aucunRecordDuContratDeContenuNeDeclareDeMethode = methods()
            .that().areDeclaredInClassesThat().resideInAnyPackage(CONTRAT_CONTENU, CONTRAT_MECANIQUE)
            .and().areDeclaredInClassesThat().areRecords()
            .should(nePorterQueLeNomDUnChampOuEtreDObjet())
            .allowEmptyShould(false)
            .as("ADR-0080 : un record du contrat de contenu ou de mecanique ne declare aucune methode"
                    + " ecrite a la main hors de ses accesseurs et d'equals/hashCode/toString - un tel"
                    + " record loge un comportement que la clause (8) ne voit pas");

    /**
     * (12) ADR-0088, clause A : <b>le verdict d'une mecanique porte l'enonce de la manche qu'il
     * juge.</b>
     *
     * <p><b>Le fait, et il est de quatre sprints.</b> ADR-0084 REV-3 a pose que la reponse d'une fin
     * de partie voyage avec la question qu'elle commente, et l'a livre sur les deux mecaniques alors
     * servies. Il a laisse {@code DesignationVerdict} de cote <b>en ecrivant pourquoi</b> - aucun
     * ecran de designation n'existait. Le motif etait juste ; ce qui manquait est ce qui allait
     * rappeler la question le jour ou l'ecran arriverait. Rien ne l'a rappelee : la troisieme
     * mecanique est dans l'arbre depuis s28 et son verdict n'a pas d'enonce, sur une suite verte.
     *
     * <p><b>Ce qu'elle interdit reellement</b>, et c'est le contraire d'une regle decorative : elle
     * <b>etait rouge</b> sur l'arbre du 2026-09-08 avant le diff qui l'accompagne, et le seul geste
     * qui la rend verte est d'ajouter la composante au troisieme verdict. Elle interdit ensuite
     * exactement ce que ce depot vient de faire une fois : livrer une quatrieme mecanique dont le
     * verdict ne porte pas sa question. La faute est indetectable a la relecture - chaque mecanique
     * porte sa propre boucle de partie par ADR-0004 clause 3, donc son propre verdict, et rien dans
     * le fichier neuf ne renvoie a l'ancien.
     *
     * <p><b>Perimetre derive, jamais enumere, et c'est le point delicat de cette clause.</b> Aucun
     * nom de type n'est ecrit ici et aucun suffixe {@code Verdict} n'est cherche : un critere sur le
     * nom se contourne en renommant. Ce qui designe un verdict est <b>ce qu'il juge</b> - une
     * composante {@code boolean juste}, la propriete que les trois mecaniques servies ecrivent
     * identiquement et dont la Javadoc de chacune dit le sens (la manche compte-t-elle comme une
     * reussite). Population au 2026-09-08 : <b>3 classes</b> - les trois verdicts, et eux seuls.
     * {@code boolean juste} apparait par ailleurs sur les trois sessions, comme <b>parametre</b> de
     * {@code resoudre} ; un parametre n'est pas un champ et ArchUnit ne le confond pas.
     * {@code allowEmptyShould(false)} garde ce perimetre : le jour ou la marque disparaitrait des
     * trois verdicts, cette clause le dirait au lieu de passer verte sur rien (ADR-0051).
     *
     * <p><b>Ce qu'elle ne garde pas, et qui le garde.</b> Elle voit la composante, jamais la valeur
     * qui y voyage ni l'element de document qui la rend. Qu'un verdict porte reellement l'enonce de
     * la manche jouee est verifie sur une partie reelle par
     * {@code LeVerdictDUneDesignationPorteSaQuestionEtJamaisSaZoneTest} ({@code domain}) ; que
     * l'ecran servi apparie la reponse a sa question l'est par
     * {@code AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest} ({@code bootstrap}), dont la
     * derivation totale rougit deja sur une forme d'item qu'aucun ecran de fin de partie ne sait
     * jouer. Une propriete gardee a moitie qui se croit gardee en entier est la faute que ce depot a
     * sanctionnee plusieurs fois ; elle est decoupee, et chaque moitie porte le nom de son garde.
     */
    @ArchTest
    static final ArchRule unVerdictDeMecaniquePorteSonEnonce = classes()
            .that().resideInAPackage(MECANIQUES)
            .and(jugentUneManche())
            .should(declarerLEnonceDeLaMancheJugee())
            .allowEmptyShould(false)
            .as("ADR-0088 : le verdict d'une mecanique porte l'enonce de la manche qu'il juge. A la"
                    + " fin d'une partie la manche courante n'existe plus, et ce verdict est la seule"
                    + " trace de l'item joue : sans enonce, l'ecran de fin nomme une reponse sans la"
                    + " question qu'elle commente");

    /** Un champ de ce nom et de ce type, ou rien. */
    private static boolean declare(JavaClass classe, String nom, String typeAttendu) {
        return classe.getFields().stream()
                .anyMatch(champ -> champ.getName().equals(nom)
                        && champ.getRawType().getName().equals(typeAttendu));
    }

    /**
     * La marque d'un verdict : il dit si la manche compte comme une reussite. C'est ce que les trois
     * mecaniques servies ecrivent identiquement, et c'est une propriete de ce que le type <b>fait</b>
     * - un nom de type ou un suffixe se contourne en renommant.
     */
    private static DescribedPredicate<JavaClass> jugentUneManche() {
        return DescribedPredicate.describe("jugent une manche (composante boolean juste)",
                classe -> declare(classe, "juste", "boolean"));
    }

    private static ArchCondition<JavaClass> declarerLEnonceDeLaMancheJugee() {
        return new ArchCondition<>("declarer une composante String enonce, obligatoire") {

            @Override
            public void check(JavaClass classe, ConditionEvents evenements) {
                if (!declare(classe, "enonce", "java.lang.String")) {
                    evenements.add(SimpleConditionEvent.violated(classe,
                            classe.getName() + " juge une manche et ne porte pas son enonce."
                                    + " ADR-0084 REV-3 : a l'instant de la fin de partie, la session"
                                    + " n'a plus de manche courante et ce verdict est la seule trace"
                                    + " de l'item joue - il n'y a rien a recomposer ailleurs"));
                }
            }
        };
    }

    private static ArchCondition<JavaMethod> nePorterQueLeNomDUnChampOuEtreDObjet() {
        return new ArchCondition<>(
                "porter le nom d'un champ de son record, ou etre equals, hashCode ou toString") {

            @Override
            public void check(JavaMethod methode, ConditionEvents evenements) {
                Set<String> nomsDeChamps = methode.getOwner().getFields().stream()
                        .map(JavaField::getName)
                        .collect(Collectors.toSet());
                String nom = methode.getName();
                boolean autorisee = nomsDeChamps.contains(nom)
                        || nom.equals("equals") || nom.equals("hashCode") || nom.equals("toString");
                if (!autorisee) {
                    evenements.add(SimpleConditionEvent.violated(methode,
                            methode.getFullName() + " n'est ni l'accesseur d'un champ de "
                                    + methode.getOwner().getSimpleName()
                                    + ", ni equals, hashCode ou toString"));
                }
            }
        };
    }
}
