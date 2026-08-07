package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0021 : un contre-exemple de garde-fou ne modifie jamais le comportement du produit qu'il sert
 * a prouver.
 *
 * <p>Le defaut mesure : {@code scanBasePackages = "com.recreo"} couvre aussi le classpath de test
 * de bootstrap, ou vivent les contre-exemples. <b>Six</b> classes de test portaient un stereotype
 * Spring et devenaient donc de vrais beans dans tout {@code @SpringBootTest} complet - compte releve
 * sur le commit 095547c par git grep des huit stereotypes en debut de ligne, sur les sources de test
 * des cinq modules du pom agregateur, hors faux depot de src/test/resources. Le plus visible,
 * {@code GestionnaireErreurBruteContreExemple}, interceptait {@code IllegalStateException} avant
 * {@code GestionnaireDesErreurs} et rendait un 500 a corps brut - le faux depot destine a prouver
 * qu'une regle mord se mettait a decider ce que le produit sous test repondait.
 * {@code ControleurQuiToucheLaSessionContreExemple}, lui, servait reellement du HTTP sur
 * /contre-exemple/compteur.</p>
 *
 * <p>Ce que cette regle interdit reellement : un contre-exemple - ou n'importe quelle classe de test
 * de ce module - qui porte un stereotype Spring et devient donc candidat au scan de composants de
 * l'application. C'est la cause, pas le symptome : sans stereotype, aucun scan ne peut enregistrer
 * la classe, quel que soit le perimetre du scan.</p>
 *
 * <p>Perimetre, et ce qu'il ne couvre pas : l'import derive du classpath de test de ce module, seul
 * endroit du depot ou un contexte Spring complet demarre ({@code @SpringBootTest}). Les sources de
 * test des autres modules ne sont pas sur ce classpath et echappent donc a cette regle - c'est la
 * meme limite que {@code LesGardeFousSontMisALEpreuveTest} nomme pour lui-meme. Ce qui les couvre
 * partiellement : {@code @WebMvcTest} n'enregistre que la couche web, et son propre filtre exclut
 * tout ce qui n'est ni controleur, ni advice, ni convertisseur. Une classe de test d'adapter-web
 * annotee {@code @ControllerAdvice} y echapperait ; il n'en existe aucune a ce jour, et la regle
 * n'est pas dupliquee pour un cas qui n'existe pas.</p>
 *
 * <p>{@code @TestComponent} - donc aussi {@code @TestConfiguration}, qui en herite - est exempte :
 * le {@code TypeExcludeFilter} que Spring Boot pose lui-meme sur le scan de l'application le retire
 * deja. L'exempter n'est pas une tolerance, c'est la frontiere exacte de ce que le scan voit.</p>
 */
class ScanDeComposantsTest {

    private static final String COMPOSANT = "org.springframework.stereotype.Component";
    private static final String COMPOSANT_DE_TEST = "org.springframework.boot.test.context.TestComponent";

    /**
     * Deux temoins de ce que l'import doit contenir. Sans eux, un {@code ImportOption} qui cesserait
     * de reconnaitre le repertoire des classes de test rendrait cette regle verte sur un ensemble
     * vide - le vert silencieux exact que ce depot a deja paye deux fois.
     */
    private static final String TEMOIN_CONTRE_EXEMPLE =
            "com.recreo.adapter.web.contreexemple.GestionnaireErreurBruteContreExemple";
    private static final String TEMOIN_OUTIL_DE_TEST = "com.recreo.bootstrap.catalogue.CatalogueDeTest";

    static final ArchRule aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype = noClasses()
            .that().resideInAPackage("com.recreo..")
            .and().areNotMetaAnnotatedWith(COMPOSANT_DE_TEST)
            .should().beMetaAnnotatedWith(COMPOSANT)
            .as("ADR-0021 : aucune classe des sources de test de ce module n'est candidate au scan de "
                    + "composants de l'application");

    private static JavaClasses sourcesDeTest() {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.OnlyIncludeTests())
                .importPackages("com.recreo");
    }

    private static JavaClasses production() {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.recreo");
    }

    @Test
    void aucuneClasseDeTestNeSeraEnregistreeParLeScanDeComposants() {
        JavaClasses sources = sourcesDeTest();

        assertThat(sources.stream().map(JavaClass::getName))
                .as("le perimetre de cette regle est le classpath de test de ce module ; s'il ne "
                        + "contient plus ces deux temoins, il ne contient probablement plus rien")
                .contains(TEMOIN_CONTRE_EXEMPLE, TEMOIN_OUTIL_DE_TEST);

        aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype.check(sources);
    }

    /**
     * Mise a l'epreuve du critere sur du materiau reel, et non sur une fixture de ma main : les
     * classes de production de ce depot portent, elles, de vrais stereotypes - directs
     * ({@code @Component} sur le filtre), ou obtenus par meta-annotation ({@code @Controller},
     * {@code @ControllerAdvice}, {@code @Configuration}). Un critere qui ne saurait pas les voir ne
     * verrait pas non plus un stereotype pose sur une classe de test. C'est l'inegalite que cette
     * regle verifie, mise a l'epreuve dans le sens ou elle doit rougir.
     *
     * <p>A executer sur le reacteur complet. Un {@code mvn -pl bootstrap} isole resout ses modules
     * freres depuis le depot Maven local, dont l'artefact peut etre anterieur a l'une des quatre
     * classes nommees ici : ce test devient alors rouge sans qu'aucun defaut n'existe. Le mode de
     * defaillance est un faux rouge, jamais un faux vert (ADR-0021).</p>
     */
    @Test
    void unStereotypeSpringSurUneClasseDuPerimetreEstRefuse() {
        assertThatThrownBy(() ->
                aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype.check(production()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.adapter.web.PartieController")
                .hasMessageContaining("com.recreo.adapter.web.GestionnaireDesErreurs")
                .hasMessageContaining("com.recreo.adapter.web.CorpsFormulaireEnUtf8ToleranteFilter")
                .hasMessageContaining("com.recreo.bootstrap.config.PartieConfiguration");
    }
}
