package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * ADR-0019 : l'etat de partie vit dans la session HTTP (ADR-0008) et deux requetes d'une meme
 * session peuvent se recouvrir. Un cycle lire-calculer-ecrire ecrit a la main perd une ecriture sur
 * trois sous un double appui - mesure, pas suppose (yanis-qa, 14 pertes sur 40).
 *
 * <p>La regle ne verifie pas qu'un verrou est pris : ArchUnit ne voit pas un bloc synchronized.
 * Elle verifie ce qu'il est possible de verifier structurellement et qui suffit - qu'un seul type
 * du depot peut toucher la session HTTP. Ce type-la prend le verrou, et c'est un test de
 * comportement qui le prouve (EtatDePartieEnSessionTest, deux fils, deterministe). Ce que cette
 * regle interdit reellement : un second controleur, ou une nouvelle route de celui-ci, qui refasse
 * le cycle a la main. C'est exactement la faute qui a produit le defaut.</p>
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class AccesALEtatDeSessionTest {

    private static final String SESSION_HTTP = "jakarta.servlet.http.HttpSession";
    private static final String PORTEUR = "com.recreo.adapter.web.EtatDePartieEnSession";

    /**
     * Le porteur existe et est final. Sans cette regle, l'exemption de la regle suivante pourrait
     * nommer un type disparu, et l'interdiction deviendrait absolue au lieu d'etre canalisee : elle
     * passerait au vert le jour ou plus personne ne toucherait la session, c'est-a-dire le jour ou
     * l'etat de partie aurait demenage sans que cet ADR soit relu.
     */
    @ArchTest
    static final ArchRule leSeulPorteurDEtatDeSessionExiste = classes()
            .that().haveFullyQualifiedName(PORTEUR)
            .should().haveOnlyFinalFields()
            .andShould().bePackagePrivate()
            .allowEmptyShould(false)
            .as("ADR-0019 : EtatDePartieEnSession existe et n'est visible que de son paquet");

    /**
     * Un appel de methode sur HttpSession, y compris obtenu par HttpServletRequest.getSession(),
     * est une dependance vers ce type : la regle attrape les deux formes, pas seulement la
     * declaration d'un parametre de controleur.
     */
    @ArchTest
    static final ArchRule unSeulTypeToucheLaSessionHttp = noClasses()
            .that().doNotHaveFullyQualifiedName(PORTEUR)
            .should().dependOnClassesThat().haveFullyQualifiedName(SESSION_HTTP)
            .as("ADR-0019 : seul EtatDePartieEnSession touche la session HTTP, et il le fait sous verrou");
}
