package com.recreo.bootstrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S0-1, deuxieme critere : "l'artefact empaquete, lance via java -jar bootstrap/target/recreo.jar,
 * sert une page accessible depuis un navigateur". Aucun autre test du sprint ne verifie ce point.
 * PartieHttpTest et CatalogueChargeableIT demarrent un contexte Spring dans le processus de test
 * lui-meme (@SpringBootTest) : ils prouvent que le cablage fonctionne, pas que le fat jar produit
 * par la phase 'package' demarre et repond en dehors de Maven. Cette IT lance le jar exactement
 * comme un utilisateur le ferait, en processus separe, et interroge le port qu'il occupe.
 *
 * Convention Failsafe (suffixe IT, cf. ADR-0007 sur CatalogueChargeableIT) : executee en phase
 * integration-test, donc apres que 'package' a produit bootstrap/target/recreo.jar.
 */
class JarRecreoIT {

    private static final Duration DELAI_DEMARRAGE = Duration.ofSeconds(30);

    @Test
    void leJarEmpaqueteSertUnePageHttpAccessibleDepuisUnNavigateur() throws Exception {
        Path jar = Path.of("target", "recreo.jar");
        assertThat(jar)
                .as("le jar doit exister : cette IT s'execute apres la phase 'package'")
                .isRegularFile();

        int port = portLibre();
        Process processus = lancer(jar, port);

        try {
            HttpResponse<String> reponse = attendreEtInterroger(port, processus);

            assertThat(reponse.statusCode()).isEqualTo(200);
            Document page = Jsoup.parse(reponse.body());
            assertThat(page.select("a.chip-niveau"))
                    .as("Q-U1 : le jar empaquete sert l'accueil qui demande le niveau, pas une erreur ni une page vide")
                    .isNotEmpty();
            String premierNiveau = page.select("a.chip-niveau").first().attr("href");

            HttpResponse<String> niveau = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder(URI.create("http://localhost:" + port + premierNiveau))
                            .timeout(Duration.ofSeconds(5)).build(),
                    HttpResponse.BodyHandlers.ofString());
            assertThat(niveau.statusCode()).isEqualTo(200);
            assertThat(Jsoup.parse(niveau.body()).select("button.demarrer"))
                    .as("le jar empaquete sert une page jouable pour un niveau, pas une erreur ni une page vide")
                    .isNotEmpty();
        } finally {
            arreter(processus);
        }
    }

    private static int portLibre() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }

    /** Utilise le JDK qui a lance ce processus de test (JAVA_HOME), jamais le 'java' du PATH. */
    private static Process lancer(Path jar, int port) throws IOException {
        String extension = System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "";
        Path javaBin = Path.of(System.getProperty("java.home"), "bin", "java" + extension);
        Path journal = Path.of("target", "jar-recreo-it.log");
        return new ProcessBuilder(javaBin.toString(), "-jar", jar.toAbsolutePath().toString(),
                "--server.port=" + port)
                .redirectErrorStream(true)
                .redirectOutput(journal.toFile())
                .start();
    }

    private static HttpResponse<String> attendreEtInterroger(int port, Process processus) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requete = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/"))
                .timeout(Duration.ofSeconds(2))
                .build();
        long limite = System.currentTimeMillis() + DELAI_DEMARRAGE.toMillis();

        while (System.currentTimeMillis() < limite) {
            if (!processus.isAlive()) {
                throw new IllegalStateException(
                        "le jar s'est arrete avant de repondre, code de sortie " + processus.exitValue()
                        + " (voir target/jar-recreo-it.log)");
            }
            try {
                return client.send(requete, HttpResponse.BodyHandlers.ofString());
            } catch (IOException pasEncorePret) {
                Thread.sleep(300);
            }
        }
        throw new IllegalStateException(
                "le jar n'a pas repondu sur le port " + port + " dans le delai de " + DELAI_DEMARRAGE
                + " (voir target/jar-recreo-it.log)");
    }

    /**
     * Un processus qui reste vivant garde recreo.jar verrouille sous Windows : un 'mvn package'
     * ulterieur echouerait sur "Unable to rename recreo.jar". D'ou l'attente bloquante ici.
     */
    private static void arreter(Process processus) throws InterruptedException {
        processus.destroy();
        if (!processus.waitFor(10, TimeUnit.SECONDS)) {
            processus.destroyForcibly();
            processus.waitFor(10, TimeUnit.SECONDS);
        }
    }
}
