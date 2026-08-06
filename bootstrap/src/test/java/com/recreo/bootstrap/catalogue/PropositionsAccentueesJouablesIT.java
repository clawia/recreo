package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.CookieManager;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0013 : le perimetre d'un test se derive du contenu reel, pas d'un exemple choisi a la main.
 *
 * <p>S05 : constat isole en jouant - toute proposition qui porte un caractere accentue
 * (cm1-homophones.yaml : "a", "Ou", "la"...) pouvait faire planter la manche entiere selon la
 * maniere dont le client encodait sa requete, un defaut invisible pour n'importe quel test
 * MockMvc.param() puisque ce dernier depose la valeur directement dans la requete simulee, sans
 * jamais traverser le decodage reel des octets sur lequel le defaut se logeait.</p>
 *
 * <p>Cette IT demarre le contexte Spring complet avec le vrai catalogue (aucun catalogue de test
 * importe, comme CatalogueChargeableIT), et pour chaque proposition non-ASCII trouvee dans un
 * paquet reel, rejoue un coup par une vraie requete HTTP contre le serveur embarque - seul niveau
 * ou le decodage en cause s'exerce.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropositionsAccentueesJouablesIT {

    private static final String ECRAN_ERREUR = "Oups ! Le jeu a un souci.";

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private PartieUseCase partie;

    @Test
    void chaqueRepresentationAccentueeDuCatalogueEstJouableSansPlanterLaManche() throws Exception {
        List<GameDefinition> jeux = partie.jeuxDisponibles();
        int propositionsVerifiees = 0;

        for (GameDefinition jeu : jeux) {
            for (String proposition : propositionsNonAscii(jeu.contenu())) {
                verifierJouable(jeu.id(), proposition);
                propositionsVerifiees++;
            }
        }

        assertThat(propositionsVerifiees)
                .as("ADR-0013 : le catalogue reel doit porter au moins une proposition accentuee"
                        + " (cm1-homophones.yaml), sinon cette IT ne verifie rien")
                .isPositive();
    }

    private void verifierJouable(String jeuId, String proposition) throws Exception {
        HttpClient client = HttpClient.newBuilder().cookieHandler(new CookieManager()).build();

        client.send(HttpRequest.newBuilder(URI.create(base() + "/parties/" + jeuId))
                        .POST(HttpRequest.BodyPublishers.noBody())
                        .build(),
                HttpResponse.BodyHandlers.discarding());

        String corps = "propositionChoisie=" + URLEncoder.encode(proposition, StandardCharsets.UTF_8);
        HttpResponse<String> coup = client.send(
                HttpRequest.newBuilder(URI.create(base() + "/parties/" + jeuId + "/coups"))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(corps))
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        assertThat(coup.body())
                .as("%s : la proposition \"%s\" doit rester jouable, jamais planter la manche", jeuId, proposition)
                .doesNotContain(ECRAN_ERREUR);
    }

    private String base() {
        return "http://localhost:" + port;
    }

    /** Un paquet combinatoire (AdditionsBorneesContentSource) ne renvoie jamais Optional.empty(). */
    private static final int BUDGET_DE_TIRAGE = 200;

    /**
     * Rejoue le tirage de la mecanique (ContentSource.next avec un ensemble de "deja vus"
     * croissant) pour parcourir le paquet, sans exposer d'API supplementaire sur le domaine. Un
     * paquet enumere (ListContentSource) s'epuise avant le budget ; un paquet combinatoire ne
     * s'epuise jamais et s'arrete au budget - sans consequence ici, ses propositions sont
     * numeriques, donc jamais retenues par ajouterSiNonAscii.
     */
    private static Set<String> propositionsNonAscii(ContentSource contenu) {
        RandomSource hasard = borne -> 0;
        Set<ItemId> dejaVus = new HashSet<>();
        Set<String> resultat = new LinkedHashSet<>();
        for (int tentative = 0; tentative < BUDGET_DE_TIRAGE; tentative++) {
            Optional<Item> tirage = contenu.next(hasard, dejaVus);
            if (tirage.isEmpty()) {
                break;
            }
            Question question = switch (tirage.orElseThrow()) {
                case Question q -> q;
            };
            dejaVus.add(question.id());
            ajouterSiNonAscii(resultat, question.bonneReponse());
            question.distracteurs().forEach(distracteur -> ajouterSiNonAscii(resultat, distracteur));
        }
        return resultat;
    }

    private static void ajouterSiNonAscii(Set<String> resultat, String proposition) {
        if (proposition.chars().anyMatch(caractere -> caractere > 127)) {
            resultat.add(proposition);
        }
    }
}
