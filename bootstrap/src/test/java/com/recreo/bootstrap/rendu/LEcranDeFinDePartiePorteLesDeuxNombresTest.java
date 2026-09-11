package com.recreo.bootstrap.rendu;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.content.RandomSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>SCO-R1 (`verdicts-nadia-instance-A.md`, section 5.2, refus de `S34-SCO1-REMEDE`), leve par
 * `S35-SCO1-ECRAN-FIN` : l'ecran de fin de partie porte deux elements distincts, l'un le nombre de
 * manches trouvees seul (`score.reussites()`), l'autre le nombre de manches jouees
 * (`score.tentatives()`) - jamais un seul nombre reduit qui remplace les deux.</b>
 *
 * <p><b>Le defaut que ce garde-fou aurait attrape.</b> {@code fragments/retour.html} rendait
 * {@code "Score : " + score.reussites()}, une concatenation Thymeleaf jamais visible de
 * {@code TexteAccentueDesGabaritsTest} (dont l'inventaire ne lit que les noeuds de texte litteraux,
 * et un {@code th:text} remplace tout son sous-arbre au rendu). Un enfant qui avait fait dix
 * manches et en avait trouve six seul lisait <i>"Score : 6"</i>, un point final.</p>
 *
 * <p><b>La forme retenue (avis de faisabilite de `theo-archi`, s35, section 1.3, `ADR-0083`)</b> :
 * les mots invariants vivent en clair dans le gabarit, chaque nombre seul et entier dans son propre
 * element, jamais une concatenation. Ce garde-fou ne juge aucun texte - la formulation reste a
 * `nadia-prof` - il refuse qu'un des deux nombres disparaisse ou se retrouve fondu dans l'autre.</p>
 *
 * <p><b>ADR-0013, les trois obligations.</b></p>
 * <ul>
 *   <li><b>Perimetre derive</b> : un jeu reel par mecanique presente dans le catalogue reel
 *       ({@link #unJeuParMecanique}), jamais une liste de trois noms ecrite ici - c'est exactement
 *       la derivation que la section 1.4 de l'avis de faisabilite demande, pour qu'une quatrieme
 *       mecanique soit jugee sans qu'on y pense ;</li>
 *   <li><b>Derivation totale</b> : chaque jeu retenu joue une partie <b>complete</b>
 *       ({@code nombreDeManches} manches, jamais un sous-ensemble), avec exactement une manche
 *       ratee jusqu'a la revelation et toutes les autres reussies du premier coup - de quoi rendre
 *       les deux nombres distincts et prouver que ce garde-fou ne peut pas passer sur un ecran qui
 *       n'en montrerait qu'un par coincidence. Deux scenarios verifies separement, comme le demande
 *       le critere d'acceptation de la story : la manche ratee est la <b>premiere</b> (la partie se
 *       termine sur une reussite) ou la <b>derniere</b> (la partie se termine sur la revelation que
 *       {@code nadia-prof} dit ne pas avoir comptee, section 5.2, dernier point) ;</li>
 *   <li><b>Mise a l'epreuve</b> : un ecran reel mute pour retirer le nombre de manches jouees - la
 *       forme exacte du defaut refuse par `SCO-R1`.</li>
 * </ul>
 *
 * <p><b>Ce que ce garde-fou ne juge pas.</b> Le texte que `nadia-prof` ecrit ou arbitre - c'est
 * {@code TexteAccentueDesGabaritsTest} qui refuse qu'il change en silence. Il ne juge pas non plus
 * si les mots choisis sont lisibles par un enfant de CP : c'est le Done pedagogique, qui reste
 * entier apres ce lot.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(LEcranDeFinDePartiePorteLesDeuxNombresTest.HasardNulConfiguration.class)
class LEcranDeFinDePartiePorteLesDeuxNombresTest {

    /**
     * Meme geste que le garde-fou voisin d'ADR-0084 : le hasard vaut zero, pour que le tirage que la
     * vraie session HTTP effectue soit celui que {@link #itemsDe} predit localement sur le meme
     * {@link ContentSource}, sans quoi la partie jouee par ce fichier ne serait plus celle qu'il
     * croit conduire.
     */
    @TestConfiguration
    static class HasardNulConfiguration {

        @Bean
        RandomSource hasard() {
            return borne -> 0;
        }
    }

    private static final int ESSAIS_AVANT_REVELATION = 3;

    private static final String CHAINE_REFUSEE = "qqqq";

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Un ecran de fin de partie reellement servi, avec les deux nombres que la partie jouee doit
     * lui avoir fait porter - calcules par ce fichier au fil du jeu, jamais relus sur le serveur.
     */
    private record EcranDeFin(String jeuId, int reussitesAttendues, int tentativesAttendues, String html) {
    }

    /** ADR-0013 obligation 1 : un jeu par mecanique, derive du catalogue reel. */
    private static Map<MechanicId, GameDefinition> unJeuParMecanique(PartieUseCase partie) {
        Map<MechanicId, GameDefinition> parMecanique = new LinkedHashMap<>();
        for (GameDefinition jeu : partie.jeuxDisponibles()) {
            parMecanique.putIfAbsent(jeu.mecanique(), jeu);
        }
        return parMecanique;
    }

    /** Meme geste que les garde-fous voisins (REV-3, ADR-0083) : le tirage sequentiel a hasard nul. */
    private static List<Item> itemsDe(ContentSource contenu, int combien) {
        Set<ItemId> dejaVus = new LinkedHashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < combien; tirage++) {
            Optional<Item> tire = contenu.next(borne -> 0, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            items.add(tire.orElseThrow());
            dejaVus.add(tire.orElseThrow().id());
        }
        return items;
    }

    /**
     * Joue une manche jusqu'a sa resolution et rend le corps de la derniere reponse HTTP.
     * {@code reussirDuPremierCoup} choisit, sans connaitre la mecanique a l'avance cote appelant,
     * entre le geste qui reussit du premier coup et les trois echecs suivis du geste qui ferme la
     * manche par revelation - le meme geste que celui deja etabli par
     * {@code AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest} pour les deux premieres formes,
     * etendu ici a la troisieme.
     */
    private String jouerManche(String jeuId, Item item, MockHttpSession session,
                                boolean reussirDuPremierCoup) throws Exception {
        if (item instanceof Question question) {
            if (reussirDuPremierCoup) {
                return coup(jeuId, session, "propositionChoisie", question.bonneReponse());
            }
            String faux = question.distracteurs().get(0);
            for (int essai = 0; essai < ESSAIS_AVANT_REVELATION; essai++) {
                coup(jeuId, session, "propositionChoisie", faux);
            }
            return coup(jeuId, session, "propositionChoisie", question.bonneReponse());
        }
        if (item instanceof ReponseAEcrire aEcrire) {
            if (reussirDuPremierCoup) {
                return coup(jeuId, session, "reponses", "reponseEcrite", aEcrire.formeDeReference());
            }
            for (int essai = 0; essai < ESSAIS_AVANT_REVELATION; essai++) {
                coup(jeuId, session, "reponses", "reponseEcrite", CHAINE_REFUSEE);
            }
            return mockMvc.perform(post("/parties/{jeuId}/manche", jeuId).session(session))
                    .andReturn().getResponse().getContentAsString();
        }
        if (item instanceof ZoneADesigner zone) {
            if (reussirDuPremierCoup) {
                return coup(jeuId, session, "designations", "zoneDesignee", zone.bonneZone());
            }
            String fausseZone = zone.zones().stream().map(Zone::id)
                    .filter(id -> !id.equals(zone.bonneZone())).findFirst().orElseThrow();
            for (int essai = 0; essai < ESSAIS_AVANT_REVELATION; essai++) {
                coup(jeuId, session, "designations", "zoneDesignee", fausseZone);
            }
            return coup(jeuId, session, "designations", "zoneDesignee", zone.bonneZone());
        }
        throw new IllegalStateException("forme d'item qu'aucune manche de ce garde-fou ne sait jouer : "
                + item.getClass().getName() + " (jeu " + jeuId + "). ADR-0013 obligation 2 : une forme"
                + " non jugee est un echec, jamais un silence");
    }

    private String coup(String jeuId, MockHttpSession session, String parametre, String valeur)
            throws Exception {
        return coup(jeuId, session, "coups", parametre, valeur);
    }

    private String coup(String jeuId, MockHttpSession session, String route, String parametre,
                         String valeur) throws Exception {
        return mockMvc.perform(post("/parties/{jeuId}/" + route, jeuId).session(session)
                        .param(parametre, valeur))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * Une partie complete du jeu donne, ratee jusqu'a la revelation sur la manche d'indice
     * {@code rangEchoue} (ou sur la derniere si {@code rangEchoue} est negatif) et reussie du
     * premier coup sur toutes les autres.
     */
    private EcranDeFin jouerPartieComplete(GameDefinition jeu, int rangEchoue) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", jeu.id()).session(session));

        List<Item> items = itemsDe(jeu.contenu(), jeu.nombreDeManches());
        assertThat(items)
                .as("%s : ce garde-fou ne prouve rien si aucune manche n'est jouable", jeu.id())
                .isNotEmpty();
        int rangEchoueResolu = rangEchoue < 0 ? items.size() - 1 : rangEchoue;

        String dernierEcran = null;
        for (int rang = 0; rang < items.size(); rang++) {
            dernierEcran = jouerManche(jeu.id(), items.get(rang), session, rang != rangEchoueResolu);
        }
        int tentativesAttendues = items.size();
        int reussitesAttendues = tentativesAttendues - 1;
        return new EcranDeFin(jeu.id(), reussitesAttendues, tentativesAttendues, dernierEcran);
    }

    private List<EcranDeFin> populationSurToutesLesMecaniques(int rangEchoue) throws Exception {
        Map<MechanicId, GameDefinition> jeuxParMecanique = unJeuParMecanique(partie);
        assertThat(jeuxParMecanique)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucune"
                        + " mecanique")
                .isNotEmpty();

        List<EcranDeFin> population = new ArrayList<>();
        for (GameDefinition jeu : jeuxParMecanique.values()) {
            population.add(jouerPartieComplete(jeu, rangEchoue));
        }
        return population;
    }

    /**
     * Le premier element, hors bouton, dont le texte propre (pas celui d'un descendant) egale
     * exactement cette valeur. Cherche une valeur, jamais une classe CSS - meme motif que le
     * garde-fou voisin d'ADR-0084, pour que le critere ne soit pas epingle au nom qu'un correctif
     * choisit.
     */
    private static Element elementPortantExactement(Document document, String valeur) {
        for (Element element : document.select("*")) {
            if (!"button".equals(element.tagName()) && element.ownText().strip().equals(valeur)) {
                return element;
            }
        }
        return null;
    }

    /** Le critere, ecrit comme une fonction de la population pour que la mise a l'epreuve la mute. */
    static List<String> griefs(List<EcranDeFin> population) {
        List<String> griefs = new ArrayList<>();
        for (EcranDeFin ecran : population) {
            Document document = Jsoup.parseBodyFragment(ecran.html());
            String reussites = String.valueOf(ecran.reussitesAttendues());
            String tentatives = String.valueOf(ecran.tentativesAttendues());
            Element porteurReussites = elementPortantExactement(document, reussites);
            Element porteurTentatives = elementPortantExactement(document, tentatives);

            if (porteurReussites == null) {
                griefs.add(ecran.jeuId() + " : aucun element ne porte, seul et entier, le nombre de"
                        + " manches trouvees seul (\"" + reussites + "\") - SCO-R1");
            }
            if (porteurTentatives == null) {
                griefs.add(ecran.jeuId() + " : aucun element ne porte, seul et entier, le nombre de"
                        + " manches jouees (\"" + tentatives + "\") - SCO-R1");
            }
            if (porteurReussites != null && porteurReussites == porteurTentatives) {
                griefs.add(ecran.jeuId() + " : le meme element porte les deux nombres - SCO-R1 exige"
                        + " deux elements distincts, jamais un nombre reduit qui remplace les deux");
            }
        }
        return griefs;
    }

    @Test
    void chaqueMecaniqueMontreLesDeuxNombresQuandLaPartieSeTermineSurUneReussite() throws Exception {
        List<EcranDeFin> population = populationSurToutesLesMecaniques(0);

        assertThat(population)
                .as("SCO-R1 : ce garde-fou ne prouve rien si aucune mecanique ne distingue les deux"
                        + " nombres sur la partie jouee")
                .allMatch(ecran -> ecran.reussitesAttendues() != ecran.tentativesAttendues());
        assertThat(griefs(population)).isEmpty();
    }

    /**
     * `verdicts-nadia-instance-A.md`, section 5.2 : "l'ecran de fin est aussi celui d'une manche
     * revelee ; je n'ai pas verifie ce que le texte neuf y donnerait, et c'est a verifier au moment
     * ou il sera ecrit." C'est ce cas-la, verifie separement du precedent.
     */
    @Test
    void chaqueMecaniqueMontreLesDeuxNombresQuandLaPartieSeTermineSurUneRevelation() throws Exception {
        List<EcranDeFin> population = populationSurToutesLesMecaniques(-1);

        assertThat(griefs(population)).isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : la morsure, sur l'etat exact du produit avant ce lot - un seul des
     * deux nombres atteint l'ecran de fin de partie.
     */
    @Test
    void unSeulNombreSurLEcranDeFinFaitEchouerLeGardeFou() throws Exception {
        List<EcranDeFin> population = populationSurToutesLesMecaniques(0);
        assertThat(griefs(population)).as("la population reelle doit etre saine avant mutation")
                .isEmpty();

        List<EcranDeFin> mutee = new ArrayList<>();
        for (EcranDeFin ecran : population) {
            Document document = Jsoup.parseBodyFragment(ecran.html());
            Element porteur = elementPortantExactement(document, String.valueOf(ecran.tentativesAttendues()));
            if (porteur != null) {
                porteur.remove();
            }
            mutee.add(new EcranDeFin(ecran.jeuId(), ecran.reussitesAttendues(), ecran.tentativesAttendues(),
                    document.body().html()));
        }

        assertThat(griefs(mutee))
                .as("un ecran de fin de partie qui ne porte plus qu'un seul nombre doit rendre ce"
                        + " garde-fou rouge - c'est exactement le defaut que SCO-R1 refuse")
                .isNotEmpty();
    }
}
