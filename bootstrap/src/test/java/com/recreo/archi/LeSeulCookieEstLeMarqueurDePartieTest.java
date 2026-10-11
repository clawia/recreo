package com.recreo.archi;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.codeUnits;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0105, regle 2 : le choix de vue de l'accueil vit dans la session HTTP et disparait avec elle
 * (DEC-0028). <b>Ce que cette regle interdit reellement</b> : le chemin le moins cher pour retenir
 * une preference d'affichage, qui est de recopier les quatre lignes du marqueur de partie
 * ({@code PartieController.marquerPartieEnCours}) sous un autre nom et une autre duree. Le cookie
 * survivrait a la session, donc a la fermeture de l'onglet si sa duree le permet, et rien d'autre
 * dans le depot ne le verrait.
 *
 * <p><b>Le plafond vaut exactement la dette</b> : deux points du code ont le droit de faire naitre
 * un cookie, ceux du marqueur de partie (ADR-0047), nommes par leur classe et leur nom. Un troisieme
 * point, ou une lambda dans l'un des deux, rougit. {@link #lesDeuxPointsExemptesFontEncoreNaitreUnCookie()}
 * rougit si l'exemption ne nomme plus rien de reel.
 *
 * <p><b>Ce qu'elle ne voit pas</b> : un en-tete {@code Set-Cookie} ecrit en clair par
 * {@code setHeader} ou {@code addHeader}. Le nom de l'en-tete est une constante de chaine, et
 * ArchUnit ne lit pas les chaines. Le versant client est garde ailleurs : {@code cookie} est un mot
 * interdit du JavaScript maison ({@code FrontieresDuJavaScriptTest}, ADR-0105 regle 3).
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LeSeulCookieEstLeMarqueurDePartieTest {

    private static final String CONTROLEUR = "com.recreo.adapter.web.PartieController";
    private static final Set<String> POINTS_DU_MARQUEUR = Set.of("marquerPartieEnCours", "effacerMarqueurDePartie");
    private static final String COOKIE_SERVLET = "jakarta.servlet.http.Cookie";
    private static final String COOKIE_SPRING = "org.springframework.http.ResponseCookie";

    private static final DescribedPredicate<JavaCodeUnit> HORS_DU_MARQUEUR_DE_PARTIE =
            DescribedPredicate.describe("hors des deux points du marqueur de partie",
                    unite -> !(unite.getOwner().getName().equals(CONTROLEUR)
                            && POINTS_DU_MARQUEUR.contains(unite.getName())));

    @ArchTest
    static final ArchRule aucunCookieNeNaitHorsDuMarqueurDePartie = codeUnits()
            .that(HORS_DU_MARQUEUR_DE_PARTIE)
            .should(neFaireNaitreAucunCookie())
            .as("ADR-0105 : le seul cookie du produit est le marqueur de partie (ADR-0047) ; "
                    + "un choix d'affichage vit dans la session HTTP (DEC-0028)");

    private static ArchCondition<JavaCodeUnit> neFaireNaitreAucunCookie() {
        return new ArchCondition<>("ne faire naitre aucun cookie") {
            @Override
            public void check(JavaCodeUnit unite, ConditionEvents evenements) {
                unite.getAccessesFromSelf().stream()
                        .filter(LeSeulCookieEstLeMarqueurDePartieTest::faitNaitreUnCookie)
                        .forEach(acces -> evenements.add(SimpleConditionEvent.violated(unite,
                                unite.getFullName() + " fait naitre un cookie par "
                                        + acces.getTarget().getFullName() + " ("
                                        + acces.getSourceCodeLocation() + ")")));
            }
        };
    }

    private static boolean faitNaitreUnCookie(JavaAccess<?> acces) {
        String proprietaire = acces.getTargetOwner().getName();
        boolean constructeurServlet = proprietaire.equals(COOKIE_SERVLET)
                && acces.getTarget().getName().equals("<init>");
        return constructeurServlet || proprietaire.startsWith(COOKIE_SPRING);
    }

    @Test
    void lesDeuxPointsExemptesFontEncoreNaitreUnCookie() {
        JavaClasses production = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.recreo");
        Set<String> pointsReels = new TreeSet<>();
        production.get(CONTROLEUR).getCodeUnits().stream()
                .filter(unite -> unite.getAccessesFromSelf().stream()
                        .anyMatch(LeSeulCookieEstLeMarqueurDePartieTest::faitNaitreUnCookie))
                .forEach(unite -> pointsReels.add(unite.getName()));

        assertThat(pointsReels)
                .as("ADR-0105 : l'exemption nomme exactement les points qui font naitre le cookie "
                        + "du marqueur de partie ; un nom qui ne designe plus rien est une place libre")
                .isEqualTo(new TreeSet<>(POINTS_DU_MARQUEUR));
    }
}
