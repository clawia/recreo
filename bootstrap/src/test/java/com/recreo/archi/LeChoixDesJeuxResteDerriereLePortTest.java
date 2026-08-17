package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * ADR-0044 : choisir quels jeux du catalogue sont montres est une regle de catalogue, elle vit
 * derriere le port entrant. Un adaptateur demande une liste, il ne la calcule pas.
 *
 * <p><b>Ce que cette regle interdit reellement</b>, et c'est la seule raison pour laquelle elle
 * existe : le chemin le moins cher pour livrer un filtre par niveau est un
 * {@code .filter(jeu -> jeu.descripteur().niveau().equals(niveau))} ecrit dans
 * {@code PartieController}. Il compile, il marche, il passe toutes les autres regles du depot - et
 * il place dans l'adaptateur web deux choses qui n'y ont rien a faire : la connaissance de la
 * facon dont un jeu porte son niveau, et l'enumeration des niveaux qui existent. ADR-0006 pose
 * qu'ajouter un niveau ou une matiere est <b>une donnee</b> ; un adaptateur qui sait lire le
 * descripteur est un adaptateur qu'on modifiera le jour ou un niveau entrera.
 *
 * <p><b>Pourquoi une dependance de type et non un appel de methode.</b> Un appel de methode se
 * contourne par un accesseur intermediaire ; une dependance de type ne se contourne pas sans
 * ajouter un type. {@code GameDefinition.descripteur()} rend un {@code ContentDescriptor} : le
 * seul fait d'appeler cet accesseur cree la dependance, donc la regle attrape la lecture avant
 * qu'elle serve a quelque chose.
 *
 * <p><b>Ce qu'elle ne voit pas</b>, dit avant qu'on le decouvre : un gabarit Thymeleaf n'est pas
 * du bytecode. {@code th:if="${jeu.descripteur().niveau() == niveau}"} echapperait entierement a
 * ArchUnit, et c'est le second chemin le moins cher. Il est ferme ailleurs, par
 * {@code LeChoixDesJeuxNeVitPasDansLaVueTest} dans {@code adapter-web} - la ou les gabarits
 * vivent.
 *
 * <p><b>Perimetre.</b> {@code adapter-content} en est exclu, et ce n'est pas une exemption
 * concedee : c'est lui qui <b>construit</b> le descripteur en lisant le YAML (ADR-0007), il ne
 * peut donc pas ignorer ce type. {@code bootstrap} y entre parce qu'il n'a aucun motif de lire un
 * descripteur : il cable, il ne selectionne pas.
 *
 * <p>Mise a l'epreuve : {@code LesReglesMordentTest.uneVueQuiFiltreLeCatalogueParNiveauEstRefusee}.
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LeChoixDesJeuxResteDerriereLePortTest {

    private static final String DESCRIPTEUR = "com.recreo.domain.content.ContentDescriptor";

    @ArchTest
    static final ArchRule aucunAdaptateurDeRenduNeLitLeDescripteurDeContenu = noClasses()
            .that().resideInAnyPackage("com.recreo.adapter.web..", "com.recreo.bootstrap..")
            .should().dependOnClassesThat()
            .haveFullyQualifiedName(DESCRIPTEUR)
            .as("ADR-0044 : le choix des jeux montres vit derriere le port entrant, jamais dans "
                    + "l'adaptateur de rendu ni dans le cablage");
}
