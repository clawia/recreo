package com.recreo.bootstrap;

import com.recreo.adapter.web.GestionnaireDesErreurs;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.TreeSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0021 : un contre-exemple de garde-fou n'est jamais enregistre comme un bean de l'application.
 *
 * <p>ArchUnit ne peut pas garder cette propriete : elle ne porte pas sur une dependance mais sur le
 * contenu du contexte assemble. {@code ScanDeComposantsTest} garde la cause qu'ArchUnit sait voir -
 * aucun stereotype sur une classe de test - et ce test-ci garde la propriete elle-meme, sur le seul
 * objet qui la porte : un contexte Spring complet, celui-la meme que le defaut de s05 avait pollue.
 * Les deux ne se recouvrent pas. Un contre-exemple declare par une methode {@code @Bean} d'une
 * configuration de test, ou tire par un {@code @Import}, n'a aucun stereotype et echapperait a la
 * regle structurelle ; il est ici, dans les beans du contexte, et ce test le voit.</p>
 *
 * <p>ADR-0013, obligation 1 : la population des contre-exemples est <b>derivee du classpath de
 * test</b> par la convention de nom qu'{@code ADR-0003} a etablie et que
 * {@code LesReglesMordentTest.aucunContreExempleEnProduction} garde deja. Aucune liste de classes
 * n'est ecrite ici, sans quoi un contre-exemple neuf sortirait du controle en silence.</p>
 *
 * <p>Obligation 2 : le perimetre se prouve non vide avant d'etre juge. Un contexte ou aucun
 * contre-exemple ne serait present sur le classpath rendrait les deux assertions vraies sans rien
 * verifier - c'est le vert silencieux que ce depot a deja paye deux fois, et le premier test
 * ci-dessous le ferme.</p>
 */
@SpringBootTest
@Import(PartieHttpTest.CatalogueDeTestConfiguration.class)
class AucunContreExempleNEstUnBeanDeLApplicationTest {

    /** ADR-0003 : la convention de nom des faux depots, gardee par LesReglesMordentTest. */
    private static final String SUFFIXE_DE_CONTRE_EXEMPLE = "ContreExemple";

    @Autowired
    private ApplicationContext contexte;

    /**
     * Les contre-exemples reellement presents sur le classpath de test de ce module, derives et non
     * enumeres. C'est la population que le scan de composants de l'application pourrait voir, parce
     * que {@code RecreoApplication} scanne {@code com.recreo} et que ces classes y vivent.
     */
    private static Set<String> contreExemplesSurLeClasspath() {
        Set<String> noms = new TreeSet<>();
        new ClassFileImporter()
                .withImportOption(new ImportOption.OnlyIncludeTests())
                .importPackages("com.recreo")
                .stream()
                .map(JavaClass::getName)
                .filter(nom -> nom.endsWith(SUFFIXE_DE_CONTRE_EXEMPLE))
                .forEach(noms::add);
        return noms;
    }

    /** Les types de tous les beans du contexte, y compris ceux des configurations importees. */
    private List<String> typesDesBeans() {
        return List.of(contexte.getBeanDefinitionNames()).stream()
                .map(nom -> contexte.getType(nom))
                .filter(type -> type != null)
                .map(Class::getName)
                .toList();
    }

    /**
     * ADR-0013 obligation 2 : sans ce test, les deux suivants passeraient sur un ensemble vide le
     * jour ou les contre-exemples demenageraient hors du paquet scanne - et ils passeraient en
     * annoncant garder une propriete qu'ils ne garderaient plus.
     */
    @Test
    void desContreExemplesSontBienSurLeClasspathDeCeContexte() {
        assertThat(contreExemplesSurLeClasspath())
                .as("ADR-0021 : ce garde-fou ne prouve rien si aucun contre-exemple ne partage le "
                        + "classpath de l'application ; c'est precisement ce partage qui est le sujet")
                .isNotEmpty();
    }

    /**
     * Le defaut de s05, mesure : {@code scanBasePackages = "com.recreo"} couvre aussi le classpath
     * de test, et six contre-exemples portaient un stereotype Spring. Le contexte les enregistrait.
     */
    @Test
    void aucunBeanDuContexteNEstUnContreExemple() {
        Set<String> attendusHorsDuContexte = contreExemplesSurLeClasspath();

        assertThat(typesDesBeans())
                .as("ADR-0021 : un contre-exemple existe pour etre refuse par un garde-fou, jamais "
                        + "pour s'executer. Enregistre comme bean, il decide de ce que le produit "
                        + "sous test repond - c'est le faux depot qui prend la place du produit. "
                        + "Contre-exemples presents sur ce classpath : %s", attendusHorsDuContexte)
                .noneSatisfy(type -> assertThat(type).endsWith(SUFFIXE_DE_CONTRE_EXEMPLE));
    }

    /**
     * La forme precise qu'avait pris le defaut, et celle que le second critere de S6-12 nomme : une
     * erreur imprevue doit etre traitee par {@code GestionnaireDesErreurs}, jamais par un
     * contre-exemple. Un {@code @ExceptionHandler} d'une advice enregistree passe **avant** celui
     * d'une autre des lors qu'il nomme un type plus specifique ; il ne suffit donc pas que la bonne
     * advice soit presente, il faut qu'aucune autre ne le soit. C'est ce que cette assertion dit,
     * et elle le dit par egalite plutot que par inclusion.
     */
    @Test
    void laSeuleAdviceDErreurDuContexteEstCelleDeLApplication() {
        assertThat(contexte.getBeansWithAnnotation(ControllerAdvice.class).values())
                .as("ADR-0011 et ADR-0021 : une seconde advice enregistree intercepte avant "
                        + "GestionnaireDesErreurs selon le type qu'elle nomme, et l'enfant recoit "
                        + "alors autre chose que l'ecran d'erreur que le jeu lui promet")
                .hasSize(1)
                .allSatisfy(advice -> assertThat(advice).isInstanceOf(GestionnaireDesErreurs.class));
    }
}
