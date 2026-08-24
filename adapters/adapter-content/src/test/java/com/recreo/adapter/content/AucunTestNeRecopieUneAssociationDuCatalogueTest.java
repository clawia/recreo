package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * `ADR-0061` : aucune classe de test ne recopie en constantes Java l'association
 * (identifiant d'entree, paquet) que porte deja le catalogue servi.
 *
 * <p><b>Ce que ce garde-fou empeche, et qui est arrive deux fois.</b> `ADR-0045` a acte en s14 le
 * retrait de cinq methodes figees, une par entree, qui fixaient le nom du paquet en constante Java
 * a cote de l'identifiant du jeu : <i>"une constante Java par jeu recopie le catalogue dans un
 * second endroit. C'est la famille de la dette 3 de la version precedente, et le couplage exact qui
 * a casse en s13"</i>. Rien n'a garde cette decision. Le motif est revenu en s17, puis quatre fois
 * en s19, sous un autre nom de methode ({@code lEntreeDeCatalogueDesigneLeBonPaquet}), et il a
 * casse une seconde fois a l'identique au retrait de "bandit-manchot-nature-mot-cp" (`DEC-0045`).</p>
 *
 * <p><b>Les deux populations se derivent, aucune ne s'enumere.</b> Les associations viennent du
 * catalogue servi lui-meme ; les objets examines sont toutes les classes compilees de ce module de
 * test, balayees depuis la racine ou elles vivent. Une entree neuve au catalogue entre dans la
 * population sans qu'aucune ligne de ce fichier ne change, et une entree retiree en sort de meme.</p>
 *
 * <p><b>Polarite (`ADR-0039`).</b> Un objet neuf - un paquet de plus, avec son fichier de test
 * ecrit normalement - reste vert ; c'est la copie du couple qui rougit. Le mode de defaillance d'un
 * ajout legitime est donc un vert, celui du geste fautif un rouge qui se nomme.</p>
 *
 * <p><b>Ce que ce garde-fou ne couvre pas</b>, nomme plutot que sous-entendu :</p>
 * <ol>
 *   <li><b>Il ne lit que des constantes, pas des litteraux en ligne.</b> La valeur d'un champ
 *       {@code static final String} se lit exactement dans le bytecode ; un litteral ecrit dans le
 *       corps d'une methode ne se distinguerait d'une reference a une fiche de conformite ou a un
 *       catalogue fabrique qu'en devinant l'intention, et une recherche de sous-chaine attraperait
 *       {@code FICHE-bandit-manchot-additions-cp.md}. Le prix est nomme : un test qui resout une
 *       entree par un identifiant ecrit en ligne echappe a cette clause. C'est le cas de
 *       {@code PaquetCm1MesuresTest.leTitreDuJeuAnnonceLesTroisGrandeurs}, qui n'est pas le meme
 *       defaut - il juge une propriete de l'entree elle-meme, pas l'association, et doit donc
 *       partir avec l'entree.</li>
 *   <li><b>Il ne couvre que les classes de test de ce module.</b> Un test de paquet ecrit ailleurs
 *       lui echapperait ; aucun n'existe, les paquets et le catalogue vivent ici.</li>
 *   <li><b>Il ne juge pas la justesse de l'association</b> ecrite dans le catalogue. C'est
 *       exactement la propriete qu'`ADR-0045` a echangee, chiffre a l'appui, et qui appartient a
 *       `nadia-prof` et a la fiche de conformite.</li>
 * </ol>
 */
class AucunTestNeRecopieUneAssociationDuCatalogueTest {

    /**
     * Le couple juge vient du catalogue servi, jamais d'une liste ecrite ici (`ADR-0013`
     * obligation 1).
     */
    private static Map<String, String> associationsDuCatalogueServi() {
        try (InputStream flux = AucunTestNeRecopieUneAssociationDuCatalogueTest.class
                .getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            Map<String, String> associations = new LinkedHashMap<>();
            for (Map<String, Object> jeu : jeux) {
                associations.put(String.valueOf(jeu.get("id")), String.valueOf(jeu.get("paquet")));
            }
            return associations;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** Toutes les classes compilees de ce module de test, balayees depuis la racine ou elles vivent. */
    private static List<Class<?>> classesDeTestCompilees() {
        Path racine;
        try {
            racine = Path.of(AucunTestNeRecopieUneAssociationDuCatalogueTest.class
                    .getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        try (Stream<Path> arbre = Files.walk(racine)) {
            List<Class<?>> classes = new ArrayList<>();
            for (Path fichier : arbre.filter(chemin -> chemin.toString().endsWith(".class")).toList()) {
                String chemin = racine.relativize(fichier).toString();
                String nomBinaire = chemin.substring(0, chemin.length() - ".class".length())
                        .replace(File.separatorChar, '.')
                        .replace('/', '.');
                try {
                    classes.add(Class.forName(nomBinaire, false,
                            AucunTestNeRecopieUneAssociationDuCatalogueTest.class.getClassLoader()));
                } catch (ClassNotFoundException | LinkageError ignore) {
                    // une classe compilee que ce classpath ne resout pas ne porte aucune constante lisible
                }
            }
            return classes;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Set<String> constantesTextuellesDe(Class<?> classe) {
        Set<String> valeurs = new LinkedHashSet<>();
        for (Field champ : classe.getDeclaredFields()) {
            if (!Modifier.isStatic(champ.getModifiers()) || !Modifier.isFinal(champ.getModifiers())
                    || champ.getType() != String.class) {
                continue;
            }
            try {
                champ.setAccessible(true);
                Object valeur = champ.get(null);
                if (valeur != null) {
                    valeurs.add((String) valeur);
                }
            } catch (IllegalAccessException | RuntimeException ignore) {
                // un champ illisible ne recopie rien de verifiable
            }
        }
        return valeurs;
    }

    static void verifierQuAucuneClasseNeRecopieUneAssociation(Collection<Class<?>> classes,
                                                              Map<String, String> associations) {
        assertThat(associations)
                .as("ADR-0051 : cette clause ne prouve rien si le catalogue servi ne porte aucune association")
                .isNotEmpty();
        assertThat(classes)
                .as("ADR-0051 : cette clause ne prouve rien si elle n'examine aucune classe")
                .isNotEmpty();

        List<String> recopies = new ArrayList<>();
        for (Class<?> classe : classes) {
            Set<String> constantes = constantesTextuellesDe(classe);
            for (Map.Entry<String, String> association : associations.entrySet()) {
                if (constantes.contains(association.getKey())
                        && constantes.contains(association.getValue())) {
                    recopies.add("%s recopie l'association \"%s\" vers \"%s\""
                            .formatted(classe.getName(), association.getKey(), association.getValue()));
                }
            }
        }

        assertThat(recopies)
                .as("ADR-0061 : ces classes recopient en constantes une association que"
                        + " catalogue.yaml porte deja. ADR-0045 a retire ce motif en s14 : il fait"
                        + " rougir le module au premier retrait d'entree et il ne garde rien que le"
                        + " catalogue ne dise deja. Un paquet se charge par son nom, jamais par son"
                        + " entree de catalogue")
                .isEmpty();
    }

    @Test
    void aucuneClasseDeTestDeCeModuleNeRecopieUneAssociationDuCatalogueServi() {
        verifierQuAucuneClasseNeRecopieUneAssociation(
                classesDeTestCompilees(), associationsDuCatalogueServi());
    }

    /**
     * `ADR-0013` obligation 3 : la clause est mise a l'epreuve sur une association que le catalogue
     * servi ne porte pas, donc sur une classe qui reste verte au balayage reel. La preuve sur
     * materiau reel, elle, est datee et vit dans `ADR-0061` : la clause a nomme les cinq classes que
     * s17 et s19 avaient ecrites, avant que le meme commit ne les corrige.
     */
    @Test
    void uneClasseQuiRecopieLeCoupleFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucuneClasseNeRecopieUneAssociation(
                List.of(ContreExempleQuiRecopieLeCouple.class),
                Map.of("jeu-absent-du-catalogue-servi", "paquet-absent-du-catalogue-servi")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0061")
                .hasMessageContaining(ContreExempleQuiRecopieLeCouple.class.getName());
    }

    /**
     * L'autre borne, et elle compte autant que la morsure : nommer le seul paquet passe. C'est ce
     * que fait tout test de paquet legitime, qui charge son contenu par son nom.
     */
    @Test
    void uneClasseQuiNeNommeQueLePaquetPasseLeGardeFou() {
        verifierQuAucuneClasseNeRecopieUneAssociation(
                List.of(ContreExempleQuiNeNommeQueLePaquet.class),
                Map.of("jeu-absent-du-catalogue-servi", "paquet-absent-du-catalogue-servi"));
    }

    /**
     * Ces valeurs sont volontairement absentes du catalogue servi : ces deux classes sont balayees
     * par la clause reelle comme n'importe quelle autre, et y restent vertes.
     */
    private static final class ContreExempleQuiRecopieLeCouple {
        static final String ID = "jeu-absent-du-catalogue-servi";
        static final String PAQUET = "paquet-absent-du-catalogue-servi";
    }

    private static final class ContreExempleQuiNeNommeQueLePaquet {
        static final String PAQUET = "paquet-absent-du-catalogue-servi";
    }
}
