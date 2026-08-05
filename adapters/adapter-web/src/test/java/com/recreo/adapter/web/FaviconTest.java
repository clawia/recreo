package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * S2-3 : l'application a une icone reconnaissable dans l'onglet du navigateur, servie hors ligne
 * comme une ressource statique, et referencee explicitement par accueil.html plutot que de
 * dependre du seul comportement implicite /favicon.ico.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class FaviconTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void laRequeteFaviconRepond200AvecUnTypeDImage() throws Exception {
        var reponse = mockMvc.perform(get("/favicon.ico")).andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(reponse.getContentType()).as("le type doit annoncer une image, jamais une page HTML")
                .isNotNull()
                .startsWith("image/");
    }

    @Test
    void lIconeExisteCommeRessourceStatiqueDAdapterWeb() {
        assertThat(Path.of("src/main/resources/static/favicon.ico")).isRegularFile();
    }

    @Test
    void accueilReferenceExplicitementLIcone() throws IOException {
        String html = Files.readString(Path.of("src/main/resources/templates/accueil.html"));
        Document page = Jsoup.parse(html);

        assertThat(page.select("link[rel=icon]")).isNotEmpty();
    }

    /**
     * S3-7 : la vignette 16px doit etre dessinee pour cette taille et non obtenue par reduction du
     * grand dessin. nadia-prof a mesure 122 couleurs distinctes sur la vignette du monogramme,
     * signe d'une reduction floue par anticrenelage ; une icone dessinee a plat pour 16px ne
     * demande qu'une poignee de couleurs unies. Decodage direct de l'entree 16x16 du fichier
     * .ico (un PNG embarque, cf. avis de faisabilite de theo-archi), sans dependance nouvelle.
     */
    @Test
    void laVignetteSeizePixelsEstDessineeAPlatEtNonReduiteDUnGrandDessin() throws IOException {
        byte[] ico = Files.readAllBytes(Path.of("src/main/resources/static/favicon.ico"));
        BufferedImage vignette = entreeIco(ico, 16);

        Set<Integer> couleurs = new HashSet<>();
        for (int y = 0; y < vignette.getHeight(); y++) {
            for (int x = 0; x < vignette.getWidth(); x++) {
                couleurs.add(vignette.getRGB(x, y));
            }
        }

        assertThat(couleurs.size())
                .as("une vignette dessinee a plat pour 16px se contente de quelques couleurs unies, "
                        + "loin des 122 couleurs mesurees par nadia-prof sur une reduction floue")
                .isLessThanOrEqualTo(16);
    }

    /** Lit l'entree carree de la taille demandee dans un fichier .ico (format Microsoft ICONDIR). */
    private static BufferedImage entreeIco(byte[] ico, int taille) throws IOException {
        int nombreEntrees = entierPetitBoutienSurDeuxOctets(ico, 4);
        for (int i = 0; i < nombreEntrees; i++) {
            int base = 6 + i * 16;
            int largeur = normaliserTailleIco(Byte.toUnsignedInt(ico[base]));
            int hauteur = normaliserTailleIco(Byte.toUnsignedInt(ico[base + 1]));
            if (largeur == taille && hauteur == taille) {
                int tailleBloc = entierPetitBoutienSurQuatreOctets(ico, base + 8);
                int decalage = entierPetitBoutienSurQuatreOctets(ico, base + 12);
                byte[] bloc = Arrays.copyOfRange(ico, decalage, decalage + tailleBloc);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bloc));
                assertThat(image)
                        .as("l'entree %dx%d de favicon.ico doit etre un PNG decodable", taille, taille)
                        .isNotNull();
                return image;
            }
        }
        throw new AssertionError("favicon.ico ne contient aucune entree " + taille + "x" + taille);
    }

    private static int normaliserTailleIco(int valeurBrute) {
        return valeurBrute == 0 ? 256 : valeurBrute;
    }

    private static int entierPetitBoutienSurDeuxOctets(byte[] donnees, int decalage) {
        return Byte.toUnsignedInt(donnees[decalage]) | (Byte.toUnsignedInt(donnees[decalage + 1]) << 8);
    }

    private static int entierPetitBoutienSurQuatreOctets(byte[] donnees, int decalage) {
        return Byte.toUnsignedInt(donnees[decalage])
                | (Byte.toUnsignedInt(donnees[decalage + 1]) << 8)
                | (Byte.toUnsignedInt(donnees[decalage + 2]) << 16)
                | (Byte.toUnsignedInt(donnees[decalage + 3]) << 24);
    }
}
