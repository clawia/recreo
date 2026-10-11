package com.recreo.archi;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.application.catalog.Matiere;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0106, APM-2 : un pictogramme de matiere est un fichier dessine que nadia-prof a vu, octet pour
 * octet. Un emoji est dessine par le systeme et change d'une tablette a l'autre (son motif, Q7) ; un
 * fichier redessine sans repasser devant elle est la meme faute en plus discret.
 *
 * <p><b>Ce que ce garde-fou interdit reellement.</b> Redessiner un pictogramme accepte, en deposer un
 * que la donnee ne nomme pas, en nommer un qui n'a jamais ete arbitre, ou deposer le pictogramme de
 * Maths refait sans que son arbitrage soit ecrit ici. Chaque empreinte change par un diff visible de ce
 * fichier, qui cite l'arbitrage.
 *
 * <p><b>Perimetre derive.</b> Les noms de fichier viennent de la donnee de matiere, par le chargeur reel ;
 * le repertoire est cherche dans chaque module du pom agregateur, jamais ecrit avec un nom de module.
 * Les empreintes, elles, sont ecrites ici : c'est l'objet meme de la regle, une liste fermee d'images
 * vues.
 */
class LePictogrammeDeMatiereEstUnFichierArbitreTest {

    /** Ecrit sous sa forme a barre oblique : c'est elle que le recensement d'ADR-0013 cherche. */
    private static final String PICTOGRAMMES_SERVIS = "src/main/resources/static/images/matieres";

    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LePictogrammeDeMatiereEstUnFichierArbitreTest", "static", "images", "matieres");

    /**
     * N2 de l'arbitrage prealable de nadia-prof (2026-10-11) : dix pictogrammes acceptes, les fichiers de
     * {@code squad/conformite/releves-accueil/pictos/} au commit e568fc6e, copies sans conversion.
     */
    static final Map<String, String> ARBITRES = Map.of(
            "francais.svg", "87046cf2db96563940957bfea29db53d4612422ae2eb2568eaa16a3f25863097",
            "questionner-le-monde.svg", "7a755f9fcb167ef80816fe886f107af5acc833da6a8e112336e621726821d644",
            "sciences.svg", "0e3340e34c27e511695733fe48d829ee36069a343094b8c5e6c2d695b606d879",
            "histoire-geographie.svg", "74ab6537c53f62401366a4dd0a0e0832ebdd024f351a7066825549abfa622c76",
            "emc.svg", "aa038139a48e9a73fdd1b6427dcb94490a59ba5437565b64ecd77d59b5efe0a5",
            "langues-vivantes.svg", "4829fff6072fa2ed6c72616796fe0215bc97911a8f501ca26682b3f9a518486b",
            "arts-plastiques.svg", "92ed4f681551c3f9d9bef4160529c3bbe2fb5eb2e3ec29ef1c408ba7c4a4080c",
            "education-musicale.svg", "0bb0fa5e372dda75be141b5c1f9b0478255dc51df7e78910655cbf75a3a576f8",
            "histoire-des-arts.svg", "82f7ed2f80f0a946237aee94ca84a3210ab07be83e4893387b0767e43b1dc3dc",
            "eps.svg", "090bd66efa455acd9881ddf26965f7c3ef94ea3fc726bd830267ceec12a905b4");

    /**
     * APM-2 : le pictogramme de Maths est refuse et sera refait. Cliquet exact : chaque nom designe un
     * fichier que la donnee nomme et qui n'est pas depose. Le fichier refait sort d'ici et entre dans
     * {@link #ARBITRES} dans le commit qui cite l'arbitrage de nadia-prof.
     */
    static final Set<String> EN_ATTENTE = Set.of("mathematiques.svg");

    static String empreinte(Path fichier) {
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(fichier)));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        } catch (NoSuchAlgorithmException echec) {
            throw new IllegalStateException(echec);
        }
    }

    static Map<String, String> empreintesDuRepertoire(Path repertoire) {
        Map<String, String> empreintes = new TreeMap<>();
        try (Stream<Path> fichiers = Files.list(repertoire)) {
            fichiers.filter(Files::isRegularFile)
                    .forEach(fichier -> empreintes.put(fichier.getFileName().toString(), empreinte(fichier)));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return empreintes;
    }

    /** Le juge, pur. */
    static List<String> griefs(List<String> nommes, Map<String, String> deposes, Map<String, String> arbitres,
                               Set<String> enAttente) {
        List<String> griefs = new ArrayList<>();
        for (String nom : nommes) {
            if (enAttente.contains(nom)) {
                if (deposes.containsKey(nom)) {
                    griefs.add(nom + " est depose alors qu'il attend l'arbitrage de nadia-prof (APM-2) :"
                            + " son empreinte entre dans ARBITRES et son nom sort de EN_ATTENTE avec l'arbitrage");
                }
            } else if (!arbitres.containsKey(nom)) {
                griefs.add(nom + " est nomme par la donnee de matiere sans avoir ete arbitre (APM-2)");
            } else if (!deposes.containsKey(nom)) {
                griefs.add(nom + " est nomme par la donnee de matiere et n'est pas depose");
            } else if (!deposes.get(nom).equals(arbitres.get(nom))) {
                griefs.add(nom + " differe du fichier arbitre (empreinte " + deposes.get(nom)
                        + ") : un pictogramme modifie revient devant nadia-prof (APM-2)");
            }
        }
        for (String depose : deposes.keySet()) {
            if (!nommes.contains(depose)) {
                griefs.add(depose + " est depose sans qu'aucune ligne de la donnee de matiere le nomme");
            }
        }
        for (String nom : arbitres.keySet()) {
            if (!nommes.contains(nom)) {
                griefs.add("l'empreinte arbitree de " + nom + " ne designe plus aucun pictogramme nomme");
            }
        }
        for (String nom : enAttente) {
            if (!nommes.contains(nom)) {
                griefs.add("l'attente de " + nom + " ne designe plus aucun pictogramme nomme");
            }
        }
        return griefs;
    }

    static void verifier(List<String> nommes, Map<String, String> deposes) {
        List<String> griefs = griefs(nommes, deposes, ARBITRES, EN_ATTENTE);
        if (!griefs.isEmpty()) {
            throw new AssertionError("ADR-0106, APM-2 : " + griefs);
        }
    }

    private static List<String> nommes() {
        return new YamlContentPackLoader().matieres().stream().map(Matiere::pictogramme).toList();
    }

    private static List<Path> repertoiresServis() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        return LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine).stream()
                .map(module -> racine.resolve(module).resolve(PICTOGRAMMES_SERVIS))
                .filter(Files::isDirectory)
                .toList();
    }

    @Test
    void chaquePictogrammeNommeEstLeFichierArbitre() {
        List<Path> repertoires = repertoiresServis();
        assertThat(repertoires).as("ADR-0013 : un et un seul repertoire de pictogrammes servis").hasSize(1);
        List<String> nommes = nommes();
        assertThat(nommes).as("ADR-0003 : ce garde-fou ne juge rien sans pictogramme nomme").hasSizeGreaterThan(1);

        verifier(nommes, empreintesDuRepertoire(repertoires.get(0)));
    }

    /** Sur le jeu fautif commite : francais.svg dont l'encre a change, octets a part, nom identique. */
    @Test
    void unPictogrammeRedessineSansArbitrageFaitEchouerLeGardeFou() {
        Map<String, String> deposes = new TreeMap<>(empreintesDuRepertoire(repertoiresServis().get(0)));
        deposes.putAll(empreintesDuRepertoire(CONTRE_EXEMPLE));

        assertThatThrownBy(() -> verifier(nommes(), deposes))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("francais.svg differe du fichier arbitre");
    }

    /** Le pictogramme de Maths depose avant son arbitrage, ici le fichier refuse lui-meme. */
    @Test
    void lePictogrammeEnAttenteDeposeAvantSonArbitrageFaitEchouerLeGardeFou() {
        Map<String, String> deposes = new TreeMap<>(empreintesDuRepertoire(repertoiresServis().get(0)));
        deposes.put("mathematiques.svg", "89d7f4d9ef83fa5138dd2b67d619e6273b91c0ab0d63ec66dd57846862787486");

        assertThatThrownBy(() -> verifier(nommes(), deposes))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("mathematiques.svg est depose alors qu'il attend");
    }

    @Test
    void unPictogrammeNommeSansArbitrageOuDeposeSansLigneFaitEchouerLeGardeFou() {
        Map<String, String> deposes = new TreeMap<>(empreintesDuRepertoire(repertoiresServis().get(0)));
        deposes.put("orphelin.svg", "0".repeat(64));
        List<String> nommes = new ArrayList<>(nommes());
        nommes.add("philosophie.svg");

        assertThatThrownBy(() -> verifier(nommes, deposes))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("philosophie.svg est nomme par la donnee de matiere sans avoir ete arbitre")
                .hasMessageContaining("orphelin.svg est depose sans qu'aucune ligne");
    }
}
