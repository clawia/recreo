package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0037 : une dependance Maven n'apporte jamais un octet servi au navigateur. Tout ce que
 * l'application sert vient de {@code static/} d'un module de ce depot, donc de l'arbre ou ADR-0014
 * exige une ligne de provenance et une empreinte.
 *
 * <p><b>Ce que ce garde-fou lit.</b> Le livrable lui-meme, {@code target/recreo.jar}, celui que
 * l'utilisateur lance par {@code java -jar} - pas le pom, pas le classpath de test. C'est la seule
 * population exacte : le classpath de test porte les outils de test (JUnit, AssertJ, ArchUnit, jsoup,
 * Nashorn), qui ne sont servis a personne et n'entrent dans aucun livrable ; un garde-fou qui les
 * jugerait accuserait des artefacts qu'aucun navigateur n'atteint jamais.
 *
 * <p><b>Perimetre derive de bout en bout</b> (ADR-0013 obligation 1) :
 * <ul>
 *   <li>les emplacements servis viennent de Spring lui-meme
 *       ({@code WebProperties.Resources#getStaticLocations()}), jamais d'une liste ecrite ici. Le
 *       jour ou le framework en ajoute un, ce garde-fou le controle sans etre modifie ;</li>
 *   <li>ce qui distingue un module de ce depot d'un artefact tiers est le {@code groupId} que chaque
 *       artefact declare dans son propre descripteur Maven, compare a celui du livrable - jamais un
 *       nom de module ni un nom de fichier ecrit ici ;</li>
 *   <li>aucun emplacement servi traduisible, aucun descripteur sur le module de tete, ou plusieurs,
 *       sont des <b>echecs</b> et jamais des cas ignores (obligation 2) : sans eux ce garde-fou ne
 *       sait plus ce qu'il juge.</li>
 * </ul>
 *
 * <p><b>Ce que ce garde-fou ne couvre pas.</b> Il ne dit rien du poids du livrable ni d'une
 * dependance qui y entrerait sans rien servir - une bibliotheque de compilation inutile est une
 * question de qualite, pas de frontiere. Il ne juge pas les octets d'une ressource servie : leur
 * provenance et leur empreinte sont a {@code RessourcesTiercesTest} (ADR-0014). Il suppose enfin que
 * les modules de ce depot continuent d'emporter leur descripteur Maven ; le jour ou le build cesserait
 * de l'ecrire, ce garde-fou passe au rouge - jamais au vert silencieux.
 */
class LeLivrableNeSertQueLesRessourcesDeSesModulesIT {

    /** Le livrable, produit par la phase {@code package} qui precede cette IT (cf. JarRecreoIT). */
    private static final Path LIVRABLE = Path.of("target", "recreo.jar");

    private static final String BIBLIOTHEQUES = "BOOT-INF/lib/";
    private static final String CLASSES = "BOOT-INF/classes/";
    private static final String DESCRIPTEUR_MAVEN = "META-INF/maven/";
    private static final String FIN_DU_DESCRIPTEUR = "/pom.properties";
    private static final String PREFIXE_DE_CLASSPATH = "classpath:/";

    private static final String MOTIF_DE_REFUS =
            "un seul artefact du livrable apporte des ressources servies au navigateur";

    /**
     * ADR-0013 : le jeu fautif de ce garde-fou est le geste qu'ADR-0014 a ecarte sans jamais
     * l'interdire - une bibliotheque front entree par une dependance Maven, servie depuis
     * {@code META-INF/resources/} sans ligne de provenance ni empreinte. Le nom est celui du webjar
     * reellement publie pour la version que ce depot vendorise.
     */
    private static final String WEBJAR_FAUTIF = BIBLIOTHEQUES + "bootstrap-5.3.8.jar";

    /** Un artefact du livrable qui apporte au moins une ressource servie au navigateur. */
    private record Porteur(String nom, String groupId, List<String> ressourcesServies) {
    }

    /** Ce que le livrable contient de servi, et le groupId qui definit "un module de ce depot". */
    private record Inventaire(String groupIdDuDepot, List<Porteur> porteurs) {
    }

    /**
     * Les emplacements du classpath que Spring sert au navigateur, lus dans Spring. Un emplacement
     * que ce garde-fou ne sait pas traduire en prefixe d'entree d'archive est un echec : le laisser de
     * cote reviendrait a ne plus controler ce qui y serait depose.
     */
    static List<String> emplacementsServis() {
        List<String> prefixes = new ArrayList<>();
        for (String emplacement : new WebProperties.Resources().getStaticLocations()) {
            assertThat(emplacement)
                    .as("ADR-0037 : ce garde-fou traduit un emplacement servi en prefixe d'entree "
                            + "d'archive ; il ne sait le faire que pour un emplacement du classpath, "
                            + "et un emplacement qu'il ignorerait serait un trou silencieux")
                    .startsWith(PREFIXE_DE_CLASSPATH);
            prefixes.add(emplacement.substring(PREFIXE_DE_CLASSPATH.length()));
        }
        assertThat(prefixes)
                .as("ADR-0037 : sans emplacement servi lu dans Spring, ce garde-fou ne juge rien")
                .isNotEmpty();
        return prefixes;
    }

    private static boolean estServie(String entree, List<String> emplacements) {
        if (entree.endsWith("/")) {
            return false;
        }
        return emplacements.stream().anyMatch(entree::startsWith);
    }

    /** Ce qu'une archive declare d'elle-meme : son groupId, et ce qu'elle apporte de servi. */
    private record Contenu(String groupId, List<String> ressourcesServies) {
    }

    /**
     * Lit une archive entree par entree. Le groupId vient du descripteur Maven que l'artefact porte
     * lui-meme ; un artefact qui n'en porte aucun n'est pas de ce depot, et c'est le cas normal d'une
     * bibliotheque tierce.
     */
    private static Contenu contenu(InputStream flux, List<String> emplacements) throws IOException {
        String groupId = "";
        List<String> servies = new ArrayList<>();
        ZipInputStream archive = new ZipInputStream(flux);
        for (ZipEntry entree = archive.getNextEntry(); entree != null; entree = archive.getNextEntry()) {
            String nom = entree.getName();
            if (estServie(nom, emplacements)) {
                servies.add(nom);
            }
            if (nom.startsWith(DESCRIPTEUR_MAVEN) && nom.endsWith(FIN_DU_DESCRIPTEUR)) {
                Properties coordonnees = new Properties();
                coordonnees.load(archive);
                groupId = coordonnees.getProperty("groupId", "");
            }
        }
        return new Contenu(groupId, servies);
    }

    /**
     * Le groupId du livrable, lu dans le descripteur Maven de son module de tete - celui que
     * {@code spring-boot-maven-plugin} laisse a la racine de l'archive, les modules embarques portant
     * le leur dans leur propre jar sous {@code BOOT-INF/lib/}. C'est lui qui definit "un module de ce
     * depot" ; aucun nom de module n'est ecrit dans ce fichier.
     */
    private static String groupIdDuLivrable(JarFile livrable, List<String> descripteurs)
            throws IOException {
        assertThat(descripteurs)
                .as("ADR-0037 : le module de tete du livrable doit porter un et un seul descripteur "
                        + "Maven ; c'est de lui que descend la notion de module de ce depot, et sans "
                        + "lui ce garde-fou ne distingue plus le maison du tiers")
                .hasSize(1);
        Properties coordonnees = new Properties();
        try (InputStream flux = livrable.getInputStream(livrable.getEntry(descripteurs.get(0)))) {
            coordonnees.load(flux);
        }
        String groupId = coordonnees.getProperty("groupId", "");
        assertThat(groupId)
                .as("ADR-0037 : le descripteur du module de tete doit nommer son groupId")
                .isNotBlank();
        return groupId;
    }

    /** L'inventaire du livrable : son groupId, et les artefacts qui y apportent du servi. */
    private static Inventaire inventaire(Path livrable) throws IOException {
        List<String> emplacements = emplacementsServis();
        List<Porteur> porteurs = new ArrayList<>();
        try (JarFile ouvert = new JarFile(livrable.toFile())) {
            List<String> descripteursDeTete = new ArrayList<>();
            List<String> serviesDuModuleDeTete = new ArrayList<>();
            List<String> bibliotheques = new ArrayList<>();

            for (Enumeration<JarEntry> entrees = ouvert.entries(); entrees.hasMoreElements(); ) {
                String nom = entrees.nextElement().getName();
                if (nom.startsWith(DESCRIPTEUR_MAVEN) && nom.endsWith(FIN_DU_DESCRIPTEUR)) {
                    descripteursDeTete.add(nom);
                } else if (nom.startsWith(CLASSES)) {
                    String interne = nom.substring(CLASSES.length());
                    if (estServie(interne, emplacements)) {
                        serviesDuModuleDeTete.add(interne);
                    }
                } else if (nom.startsWith(BIBLIOTHEQUES) && nom.endsWith(".jar")) {
                    bibliotheques.add(nom);
                }
            }

            String groupIdDuDepot = groupIdDuLivrable(ouvert, descripteursDeTete);
            if (!serviesDuModuleDeTete.isEmpty()) {
                porteurs.add(new Porteur(CLASSES, groupIdDuDepot, serviesDuModuleDeTete));
            }
            for (String bibliotheque : bibliotheques) {
                try (InputStream flux = ouvert.getInputStream(ouvert.getEntry(bibliotheque))) {
                    Contenu contenu = contenu(flux, emplacements);
                    if (!contenu.ressourcesServies().isEmpty()) {
                        porteurs.add(new Porteur(bibliotheque, contenu.groupId(),
                                contenu.ressourcesServies()));
                    }
                }
            }
            return new Inventaire(groupIdDuDepot, porteurs);
        }
    }

    static void verifierQueSeulUnModuleDeCeDepotSert(Path livrable) throws IOException {
        Inventaire inventaire = inventaire(livrable);

        assertThat(inventaire.porteurs().stream().map(Porteur::nom).toList())
                .as("ADR-0037 : %s, et c'est le module de ce depot dont ADR-0014 lit l'arbre. Un "
                        + "artefact tiers qui en apporte sert des octets sans ligne de provenance ni "
                        + "empreinte : une bibliotheque front entre par vendorisation sous "
                        + "static/vendor/, jamais par une dependance Maven. Un second module de ce "
                        + "depot qui en apporte est refuse aussi - la provenance d'ADR-0014 est "
                        + "derivee d'un seul arbre de module, et ce qui serait servi depuis un autre "
                        + "echapperait a son empreinte", MOTIF_DE_REFUS)
                .hasSize(1);

        Porteur porteur = inventaire.porteurs().get(0);
        assertThat(porteur.groupId())
                .as("ADR-0037 : %s sert %s ; un artefact qui ne declare pas le groupId de ce depot "
                        + "est tiers, et un octet tiers servi au navigateur passe par static/vendor/ "
                        + "avec sa ligne de provenance (ADR-0014)",
                        porteur.nom(), porteur.ressourcesServies())
                .isEqualTo(inventaire.groupIdDuDepot());
    }

    @Test
    void seulUnModuleDeCeDepotApporteDesRessourcesServiesAuNavigateur() throws IOException {
        assertThat(LIVRABLE)
                .as("le livrable doit exister : cette IT s'execute apres la phase 'package'")
                .isRegularFile();

        verifierQueSeulUnModuleDeCeDepotSert(LIVRABLE);
    }

    /**
     * ADR-0013 obligation 3, sur le materiau reel plutot que sur une fixture : le livrable lui-meme,
     * recopie puis modifie par le systeme de fichiers d'archive, plus le seul geste que cette regle
     * refuse. Le webjar fautif porte <b>exactement</b> le nom d'une ressource reellement servie
     * aujourd'hui - lu dans le livrable, jamais invente - de sorte que l'epreuve reproduise le cas ou
     * la meme ressource arrive d'un artefact tiers au lieu du module qui la trace.
     */
    @Test
    void unWebjarAjouteAuLivrableFaitEchouerLeGardeFou() throws Exception {
        List<Porteur> porteurs = inventaire(LIVRABLE).porteurs();
        assertThat(porteurs)
                .as("l'epreuve reprend une ressource reellement servie ; sans aucune, elle ne "
                        + "prouverait rien")
                .isNotEmpty();
        String ressourceReelle = porteurs.get(0).ressourcesServies().get(0);

        Path mute = Files.createTempFile("recreo-livrable-mute", ".jar");
        try {
            Files.copy(LIVRABLE, mute, StandardCopyOption.REPLACE_EXISTING);
            try (FileSystem archive = FileSystems.newFileSystem(mute)) {
                Files.write(archive.getPath(WEBJAR_FAUTIF), webjar(ressourceReelle));
            }

            assertThatThrownBy(() -> verifierQueSeulUnModuleDeCeDepotSert(mute))
                    .as("un webjar ajoute au livrable sert %s sans provenance ni empreinte : ce "
                            + "garde-fou doit le refuser", ressourceReelle)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining(MOTIF_DE_REFUS);
        } finally {
            Files.deleteIfExists(mute);
        }
    }

    /**
     * ADR-0013 obligation 3, seconde clause : la premiere epreuve refuse un porteur de plus, celle-ci
     * refuse un porteur tiers <b>a la place</b> du module qui trace ses octets - le webjar adopte au
     * lieu de la vendorisation, exactement ce qu'ADR-0014 a ecarte. Sans cette epreuve, la clause du
     * groupId serait ecrite sans preuve qu'elle mord.
     */
    @Test
    void unWebjarQuiRemplaceLeModuleQuiSertFaitEchouerLeGardeFou() throws Exception {
        List<Porteur> porteurs = inventaire(LIVRABLE).porteurs();
        assertThat(porteurs).as("sans porteur reel, cette epreuve ne prouverait rien").isNotEmpty();
        Porteur porteur = porteurs.get(0);
        assertThat(porteur.nom())
                .as("cette epreuve retire du livrable le module qui sert, pour ne laisser que le "
                        + "webjar ; elle sait le faire quand ce module y est embarque comme "
                        + "bibliotheque. Si les ressources servies viennent desormais du module de "
                        + "tete, c'est ici qu'il faut re-deriver la mutation")
                .startsWith(BIBLIOTHEQUES);
        String ressourceReelle = porteur.ressourcesServies().get(0);

        Path mute = Files.createTempFile("recreo-livrable-mute", ".jar");
        try {
            Files.copy(LIVRABLE, mute, StandardCopyOption.REPLACE_EXISTING);
            try (FileSystem archive = FileSystems.newFileSystem(mute)) {
                Files.delete(archive.getPath(porteur.nom()));
                Files.write(archive.getPath(WEBJAR_FAUTIF), webjar(ressourceReelle));
            }

            assertThatThrownBy(() -> verifierQueSeulUnModuleDeCeDepotSert(mute))
                    .as("le webjar est alors le seul porteur : ce garde-fou doit refuser qu'un "
                            + "artefact tiers serve %s", ressourceReelle)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("un artefact qui ne declare pas le groupId de ce depot");
        } finally {
            Files.deleteIfExists(mute);
        }
    }

    /** Un artefact tiers qui sert la ressource nommee depuis un emplacement servi par Spring. */
    private static byte[] webjar(String ressource) throws IOException {
        ByteArrayOutputStream octets = new ByteArrayOutputStream();
        try (ZipOutputStream jar = new ZipOutputStream(octets)) {
            jar.putNextEntry(new ZipEntry("META-INF/resources/" + ressource));
            jar.write(".fautif{}".getBytes(StandardCharsets.UTF_8));
            jar.closeEntry();
        }
        return octets.toByteArray();
    }
}
