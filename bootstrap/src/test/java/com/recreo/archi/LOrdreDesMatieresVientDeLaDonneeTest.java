package com.recreo.archi;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.adapter.web.contreexemple.MatiereEnumereeContreExemple;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.catalog.Matiere;
import com.recreo.application.usecase.PartieService;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0106, APM-4 : l'ordre des matieres est ecrit une fois, dans la donnee de matiere, et l'accueil le
 * lit la.
 *
 * <p><b>Ce que ces deux regles interdisent reellement.</b> La regle ArchUnit refuse le chemin le moins
 * cher d'un ordre ecrit en code : une enumeration des matieres, triee par son rang. La clause sur le
 * catalogue reel refuse tout autre ordre que celui du fichier - l'ordre d'apparition au catalogue (le
 * repli d'avant ADR-0106, qui mettait Mathematiques avant Francais au CP), l'alphabet, un tri ecrit dans
 * le service.
 *
 * <p><b>Perimetre derive.</b> Les noms interdits sont lus dans la donnee de matiere par le chargeur reel :
 * la matiere, le libelle, le segment et le nom du pictogramme, replies en identifiant Java (accents
 * retires, tout autre signe en souligne, sans casse). Une matiere neuve est gardee le jour ou sa ligne
 * entre, sans que ce fichier bouge.
 *
 * <p><b>Ce que la regle ArchUnit ne voit pas.</b> Une enumeration dont les constantes ne reprennent
 * aucun de ces quatre noms ({@code M1, M2}), et un tableau de chaines : la clause sur le catalogue reel
 * les attrape par leur effet, pas par leur forme.
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LOrdreDesMatieresVientDeLaDonneeTest {

    private static final YamlContentPackLoader CHARGEUR = new YamlContentPackLoader();

    static final Set<String> NOMS_DE_MATIERE = nomsDeMatiere(CHARGEUR.matieres());

    @ArchTest
    static final ArchRule aucuneMatiereNEstUneConstanteDEnumeration = classes()
            .that().resideInAPackage("com.recreo..")
            .should(neJamaisEnumererUneMatiere())
            .as("ADR-0106 : la matiere et son ordre restent une donnee, jamais une constante d'enumeration");

    static Set<String> nomsDeMatiere(List<Matiere> lignes) {
        Set<String> noms = new TreeSet<>();
        for (Matiere ligne : lignes) {
            Stream.of(ligne.nom(), ligne.libelle(), ligne.segment(),
                            ligne.pictogramme().replaceFirst("\\.svg$", ""))
                    .map(LOrdreDesMatieresVientDeLaDonneeTest::enIdentifiant)
                    .forEach(noms::add);
        }
        return noms;
    }

    static String enIdentifiant(String texte) {
        String sansAccent = Normalizer.normalize(texte, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sansAccent.toUpperCase(Locale.ROOT).replaceAll("[^A-Z0-9]+", "_").replaceAll("^_+|_+$", "");
    }

    private static ArchCondition<JavaClass> neJamaisEnumererUneMatiere() {
        return new ArchCondition<>("ne jamais enumerer une matiere de la donnee de matiere") {

            @Override
            public void check(JavaClass type, ConditionEvents evenements) {
                if (!type.isEnum()) {
                    return;
                }
                for (JavaField champ : type.getFields()) {
                    boolean constante = champ.getModifiers().contains(JavaModifier.STATIC)
                            && champ.getRawType().getName().equals(type.getName());
                    if (constante && NOMS_DE_MATIERE.contains(champ.getName().toUpperCase(Locale.ROOT))) {
                        evenements.add(SimpleConditionEvent.violated(type, type.getName()
                                + " declare la constante " + champ.getName() + ", qui nomme une matiere de"
                                + " la donnee de matiere. L'ordre et la liste des matieres vivent dans"
                                + " matieres.yaml (ADR-0106, APM-4), une matiere neuve est une ligne"
                                + " (ADR-0006)"));
                    }
                }
            }
        };
    }

    @Test
    void lesNomsInterditsSontDerivesDeLaDonnee() {
        assertThat(NOMS_DE_MATIERE)
                .as("ADR-0003 : la regle ne refuse rien si la donnee ne fournit aucun nom")
                .contains("FRANCAIS", "MATHEMATIQUES", "MATHS", "QUESTIONNER_LE_MONDE", "EMC", "EPS");
    }

    @Test
    void uneEnumerationDesMatieresEstRefusee() {
        assertThatThrownBy(() -> aucuneMatiereNEstUneConstanteDEnumeration
                .check(new ClassFileImporter().importClasses(MatiereEnumereeContreExemple.class)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MatiereEnumereeContreExemple")
                .hasMessageContaining("FRANCAIS")
                .hasMessageContaining("QUESTIONNER_LE_MONDE");
    }

    /**
     * APM-4 sur le catalogue reel, a chaque niveau : l'ordre des groupes est celui des lignes, une
     * matiere absente du niveau etant sautee. Ce que rendrait l'ordre d'apparition au catalogue differe
     * a chacun des trois niveaux servis (mesure du 2026-10-11, rapport de marc-dev section 2).
     */
    @Test
    void lesGroupesDeChaqueNiveauSuiventLOrdreDesLignes() {
        PartieService service = new PartieService(CHARGEUR, id -> null, borne -> 0);
        List<String> ordreDuFichier = CHARGEUR.matieres().stream().map(Matiere::nom).toList();
        List<String> niveaux = service.niveauxDuCatalogue();
        assertThat(niveaux).as("ADR-0003 : aucun niveau, aucun ordre a juger").hasSizeGreaterThan(1);

        for (String niveau : niveaux) {
            List<String> rendu = service.matieresDuNiveau(niveau).stream().map(GroupeDeJeux::matiere).toList();
            assertThat(rendu).as("ADR-0106, APM-4 : les matieres du niveau %s", niveau)
                    .hasSizeGreaterThan(1)
                    .isEqualTo(ordreDuFichier.stream().filter(rendu::contains).toList());
        }
    }
}
