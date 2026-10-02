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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
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
 *
 * <p>Amendement de l'ADR-0013 (2026-08-06) - le corps envoye fait partie du perimetre. La premiere
 * version de cette IT pourcent-encodait tout le corps avec {@code URLEncoder.encode(proposition,
 * UTF_8)} : un octet non-ASCII devient alors une sequence percent-encodee toujours valide
 * ("a" -&gt; "%C3%A0"), que le decodeur strict de Tomcat accepte sans jamais lever - la propriete
 * que ce test pretendait garantir n'avait donc jamais ete cassee, ni avant ni apres le correctif.
 * Mesure sur le serveur reel (filtre desactive puis reactive) : ni un octet non-ASCII pose tel quel
 * (UTF-8 valide, non pourcent-encode) ni sa forme pourcent-encodee ne declenchent le defaut - seule
 * une sequence d'octets **invalide** en UTF-8 le fait, exactement ce que documentent deja
 * RequeteFormulaireDecodeeEnUtf8 et son test unitaire (l'octet isole 0xE0). Cette forme-la existe
 * cote client des qu'un navigateur ou un proxy encode le corps en Latin-1/Windows-1252 au lieu
 * d'UTF-8 : chaque caractere accentue du francais y devient un unique octet haut (0xE0 pour "a",
 * par exemple) qui n'est jamais une sequence UTF-8 valide. C'est cette forme que cette IT
 * reproduit : le corps garde son pourcent-encodage sur les seuls octets structurants du format
 * (espace, "&amp;", "=", "+", "%"), mais chaque caractere accentue est pose tel quel et encode en
 * Latin-1 par le publisher du corps - jamais en UTF-8, jamais pourcent-encode. Aucun octet n'est
 * fabrique a la main : chaque proposition vient, comme avant, du catalogue reel.</p>
 *
 * <p>s47 (arbitrage prealable de nadia-prof, A1) : le corps est encode en Windows-1252, comme le
 * poste un navigateur regle sur ce jeu de caracteres, et non plus en Latin-1 strict. Les deux
 * coincident sur U+00A0..U+00FF ; Windows-1252 couvre en plus, sur un octet de 0x80..0x9F,
 * U+0153 (0x9C), U+0152 (0x8C), les guillemets et tirets typographiques. La table est celle du JDK
 * (charset "windows-1252"), jamais une table recopiee ici. Un caractere absent de Windows-1252
 * leve toujours : aucun navigateur ne saurait le poster en un octet, et cette IT ne le
 * reproduit pas.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropositionsAccentueesJouablesIT {

    private static final String ECRAN_ERREUR = "Oups ! Le jeu a un souci.";
    /** application/x-www-form-urlencoded : ces cinq octets restent structurants, tout le reste passe brut. */
    private static final String OCTETS_ASCII_A_ENCODER = " &=+%";
    private static final Charset WINDOWS_1252 = Charset.forName("windows-1252");

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private PartieUseCase partie;

    @Test
    void chaqueRepresentationAccentueeDuCatalogueEstJouableSansPlanterLaManche() throws Exception {
        List<GameDefinition> jeux = partie.jeuxDisponibles();
        Set<String> propositionsVerifiees = new HashSet<>();

        for (GameDefinition jeu : jeux) {
            for (String proposition : propositionsNonAscii(jeu.contenu())) {
                verifierJouable(jeu.id(), proposition);
                propositionsVerifiees.add(proposition);
            }
        }

        assertThat(propositionsVerifiees)
                .as("ADR-0013 : le catalogue reel doit porter au moins une proposition accentuee"
                        + " (cm1-homophones.yaml), sinon cette IT ne verifie rien")
                .isNotEmpty();
        assertThat(propositionsVerifiees)
                .as("s47, A1 : les deux propositions a ligature du catalogue reel (cp-anglais-culture.yaml,"
                        + " cp-eps-corps-effort.yaml) doivent etre jouees, sinon le chemin Windows-1252"
                        + " hors Latin-1 n'est exerce par rien")
                .contains("on cherche des œufs en chocolat dans le jardin",
                        "pour que les muscles et le cœur récupèrent");
    }

    private void verifierJouable(String jeuId, String proposition) throws Exception {
        HttpClient client = HttpClient.newBuilder().cookieHandler(new CookieManager()).build();

        client.send(HttpRequest.newBuilder(URI.create(base() + "/parties/" + jeuId))
                        .POST(HttpRequest.BodyPublishers.noBody())
                        .build(),
                HttpResponse.BodyHandlers.discarding());

        byte[] corps = corpsFormulaireEncodeEnWindows1252(proposition);
        HttpResponse<String> coup = client.send(
                HttpRequest.newBuilder(URI.create(base() + "/parties/" + jeuId + "/coups"))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofByteArray(corps))
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        // ADR-0011 : GestionnaireDesErreurs force le statut 200 sur toute exception, precisement
        // pour que htmx substitue le fragment "Oups" dans la zone de jeu au lieu d'un swap qui
        // echoue. Une reponse en 500 est donc, elle aussi, une manche qui a plante - meme si son
        // corps ne contient pas le texte ECRAN_ERREUR. Necessaire ici : un defaut de perimetre
        // distinct, hors mandat de cette IT, fait que GestionnaireErreurBruteContreExemple
        // (bootstrap/src/test/java/.../contreexemple, un contre-exemple d'ADR-0011 destine a etre
        // refuse par GestionDesErreursTest) se retrouve enregistre comme vrai bean Spring des que
        // le contexte complet demarre ici (scanBasePackages = "com.recreo" sur RecreoApplication
        // couvre aussi le classpath de test) et intercepte l'IllegalStateException de Tomcat avant
        // GestionnaireDesErreurs, avec un statut 500 et un corps brut. Route a sofia-sm.
        assertThat(coup.statusCode())
                .as("%s : la proposition \"%s\" doit rester jouable, jamais planter la manche (statut HTTP)",
                        jeuId, proposition)
                .isEqualTo(200);
        assertThat(coup.body())
                .as("%s : la proposition \"%s\" doit rester jouable, jamais planter la manche", jeuId, proposition)
                .doesNotContain(ECRAN_ERREUR);
    }

    /**
     * Pourcent-encode les seuls octets structurants du format ; encode chaque caractere accentue
     * tel quel en un unique octet Windows-1252 - jamais en UTF-8, jamais pourcent-encode. C'est cet
     * unique octet, invalide en UTF-8, que le decodeur strict de Tomcat refuse. L'encodeur rapporte
     * un caractere non representable au lieu de le remplacer par "?" : sans cela, un caractere hors
     * Windows-1252 partirait en silence comme un autre texte que la proposition.
     */
    private static byte[] corpsFormulaireEncodeEnWindows1252(String proposition) {
        CharsetEncoder windows1252 = WINDOWS_1252.newEncoder();
        StringBuilder corps = new StringBuilder("propositionChoisie=");
        for (int i = 0; i < proposition.length(); i++) {
            char caractere = proposition.charAt(i);
            if (caractere < 128 && OCTETS_ASCII_A_ENCODER.indexOf(caractere) >= 0) {
                corps.append('%').append(String.format("%02X", (int) caractere));
            } else if (windows1252.canEncode(caractere)) {
                corps.append(caractere);
            } else {
                throw new IllegalStateException(
                        "caractere hors Windows-1252, cette IT ne sait pas le reproduire : \"" + proposition + "\"");
            }
        }
        return corps.toString().getBytes(WINDOWS_1252);
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
            // s24, couture scellee d'Item : le contrat de contenu porte depuis ce sprint une
            // seconde forme d'item, celle de la mecanique de saisie. Ce controle porte sur les
            // propositions offertes a l'appui, et un item auquel on repond en ecrivant n'en offre
            // aucune - il n'a donc rien a verser ici. Le cas ne se presente pas tant qu'aucun
            // paquet de saisie n'est au catalogue, et il ne fera rien de faux le jour ou il s'y
            // presentera.
            Item tire = tirage.orElseThrow();
            if (!(tire instanceof Question question)) {
                dejaVus.add(tire.id());
                continue;
            }
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
