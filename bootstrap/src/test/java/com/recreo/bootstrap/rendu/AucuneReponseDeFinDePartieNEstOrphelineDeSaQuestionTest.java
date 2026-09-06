package com.recreo.bootstrap.rendu;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0084, REV-3 : sur l'ecran de fin de partie, la reponse nommee n'est jamais orpheline de sa
 * question - et c'est la question de <i>sa</i> manche, pas celle d'une autre manche de la meme
 * partie.</b>
 *
 * <p><b>Le defaut que ce garde-fou aurait attrape.</b> L'ecran de fin de partie affichait <i>"La
 * bonne reponse : non"</i>, <i>"La bonne reponse : 9"</i>, sans porter ni la question ni l'enonce
 * de la manche que cette reponse commente. Un enfant qui vient d'enchainer plusieurs manches lit
 * une valeur qui ne repond a rien de visible. {@code nadia-prof} a decrit le symptome le 2026-09-06
 * et a route la cause : elle est structurelle. Le defaut est anterieur a ADR-0083 - il vivait
 * derriere <i>"Non, c'est 8."</i>, ou la reponse etait deja seule au monde.
 *
 * <p><b>Pourquoi rien ne le mordait.</b> Rien dans le depot n'exigeait qu'un ecran servi porte deux
 * champs du <b>meme</b> item : les garde-fous de rendu existants verifient qu'un champ atteint son
 * ecran ({@code LaConsigneDUnJeuServiAtteintLEcranDeMancheTest},
 * {@code LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest}) ou qu'un texte de mecanique reste
 * invariant ({@code AucunTexteServiNEnchasseLaReponseDansUnePhraseTest}, ADR-0083). Aucun ne
 * verifie un <b>appariement</b>, et un appariement est precisement ce qu'une partie de plusieurs
 * manches peut casser sans que rien ne manque a l'ecran.
 *
 * <p><b>ADR-0013, les trois obligations.</b>
 * <ul>
 *   <li><b>Perimetre derive</b> : la population vient du chargeur de production
 *       ({@link YamlContentPackLoader}, l'appel meme que {@code ContenuConfiguration} fait). Aucun
 *       paquet, aucun item, aucun texte, aucune classe CSS n'est nomme dans le critere. Un paquet
 *       neuf entre dans la population le jour ou il entre au catalogue.</li>
 *   <li><b>Derivation totale</b> : chaque paquet servi produit une partie de <b>deux manches</b>,
 *       jouee mal jusqu'au bout sur les deux, et son ecran de fin de partie produit ses assertions.
 *       Une forme d'item qu'aucun ecran servi ne sait jouer ({@link ZoneADesigner} aujourd'hui) fait
 *       <b>echouer</b> la construction de la population au lieu d'etre ecartee en silence - le jour
 *       ou un paquet de designation est servi, c'est ici que la question de son ecran de fin de
 *       partie se pose, en rouge.</li>
 *   <li><b>Mise a l'epreuve</b> : deux morsures, toutes deux sur la <b>sortie reelle du serveur</b>.
 *       L'une retire la question - c'est l'etat exact du produit avant ce lot. L'autre y met la
 *       question d'une <b>autre</b> manche de la meme partie, ce qu'aucune assertion de presence ne
 *       distinguerait.</li>
 * </ul>
 *
 * <p><b>Pourquoi deux manches et non une.</b> Une partie d'une seule manche rend la seconde
 * propriete indemontrable : il n'existe alors aucune autre question a laquelle la reponse pourrait
 * etre appariee par erreur, et le garde-fou passerait vert sur un produit qui afficherait
 * systematiquement la question de la premiere manche. La population est donc faite de parties de
 * deux manches, et le critere exige a la fois la presence de la question de la <b>derniere</b>
 * manche et l'absence de celle de la premiere.
 *
 * <p><b>Ce que ce garde-fou ne garde pas, dit plutot que tu.</b>
 * <ul>
 *   <li>Il ne parcourt pas tous les items servis, la ou {@code AucunTexteServiNEnchasseLaReponse...}
 *       le fait : il joue les <b>deux premiers items</b> de chaque paquet servi. C'est un choix
 *       assume et son motif est verifiable - l'element qui porte la question ne depend d'aucun champ
 *       facultatif d'item, seulement de {@code juste}, et {@code enonce} est une composante
 *       obligatoire du contrat de contenu. La part qui depend de l'item est la <b>valeur</b>
 *       appariee, et c'est elle qui est verifiee sur deux items reels par paquet.</li>
 *   <li>Il ne juge <b>aucun texte</b>. Que la question doive etre precedee d'un intitule, et lequel,
 *       appartient a {@code nadia-prof} (M12).</li>
 *   <li>Il ne dit rien de l'ecran d'une manche <b>reussie</b> en fin de partie : cet ecran ne nomme
 *       aucune reponse, donc aucune reponse n'y est orpheline.</li>
 *   <li>Il ne couvre pas le texte-support d'un item de comprehension (ADR-0050), que l'ecran de fin
 *       de partie ne porte pas. Une question qui renvoie a un texte absent reste lisible comme
 *       question ; que cela suffise a l'enfant est un jugement pedagogique, route et non tranche
 *       ici.</li>
 * </ul>
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest.DeuxManchesParPaquetServiConfiguration.class)
class AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest {

    /** Le nombre de manches d'une partie de la population - voir "Pourquoi deux manches" ci-dessus. */
    private static final int MANCHES = 2;

    /** Une chaine qu'aucun bareme de saisie n'accepte, identique sur toute la population. */
    private static final String CHAINE_REFUSEE = "qqqq";

    private static final int ESSAIS_AVANT_REVELATION = 3;

    private static final char ESPACE_INSECABLE = (char) 0x00A0;

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Un ecran de fin de partie reellement servi, avec ce qu'il fallait deja savoir pour le juger :
     * la reponse que la derniere manche a revelee, la question de cette manche, et la question de la
     * manche <b>precedente</b> - celle a laquelle la reponse ne doit pas etre appariee. {@code html}
     * est garde tel que le serveur l'a rendu, pour que la mise a l'epreuve mute la sortie reelle.
     */
    private record FinDePartieServie(String jeuServi, String reponse, String questionDeSaManche,
                                     String questionDeLaMancheDAvant, String html) {
    }

    /** Meme normalisation et meme motif que le garde-fou voisin d'ADR-0083. */
    private static String normaliser(String texte) {
        return texte.replace(ESPACE_INSECABLE, ' ').replaceAll("\\s+", " ").strip();
    }

    /**
     * <b>Le critere.</b> Rend la liste des griefs, vide quand la population est saine. Ecrit comme
     * une fonction de la population qu'on lui donne, jamais de la population reelle : c'est ce qui
     * permet a la mise a l'epreuve de lui presenter la meme matiere, mutee.
     *
     * <p>Trois clauses. La reponse est portee seule et entiere par un element (elle l'etait deja,
     * ADR-0083 clause 2, et ce garde-fou en depend) ; la question de la <b>meme</b> manche est
     * portee seule et entiere par un autre element, dans le <b>meme bloc</b> - le critere
     * d'appariement de REV-3, tenu structurellement par un parent commun et non par une proximite de
     * mise en page ; et la question d'une <b>autre</b> manche de la partie n'est portee par aucun
     * element.</p>
     */
    static List<String> griefs(List<FinDePartieServie> population) {
        List<String> griefs = new ArrayList<>();
        for (FinDePartieServie ecran : population) {
            Document document = Jsoup.parseBodyFragment(ecran.html());
            Element porteurDeLaReponse = elementQuiPorte(document, ecran.reponse());
            Element porteurDeLaQuestion = elementQuiPorte(document, ecran.questionDeSaManche());

            if (porteurDeLaReponse == null) {
                griefs.add(ecran.jeuServi() + " : aucun element hors bouton ne porte la reponse \""
                        + ecran.reponse() + "\" seule et entiere - ce garde-fou ne peut alors rien"
                        + " prouver de son appariement (ADR-0083 clause 2)");
                continue;
            }
            if (porteurDeLaQuestion == null) {
                griefs.add(ecran.jeuServi() + " : l'ecran de fin de partie nomme la reponse \""
                        + ecran.reponse() + "\" sans porter la question de la manche qu'elle"
                        + " commente (\"" + ecran.questionDeSaManche() + "\"). L'enfant a enchaine"
                        + " d'autres manches depuis : seule, cette reponse ne repond a rien de"
                        + " visible (REV-3)");
            } else if (porteurDeLaQuestion.parent() != porteurDeLaReponse.parent()) {
                griefs.add(ecran.jeuServi() + " : la question et la reponse qu'elle commente ne sont"
                        + " pas dans le meme bloc - rien ne dit alors a l'enfant qu'elles vont"
                        + " ensemble (REV-3, second critere d'acceptation)");
            }
            if (!ecran.questionDeLaMancheDAvant().equals(ecran.questionDeSaManche())
                    && elementQuiPorte(document, ecran.questionDeLaMancheDAvant()) != null) {
                griefs.add(ecran.jeuServi() + " : l'ecran de fin de partie porte la question d'une"
                        + " autre manche de la meme partie (\"" + ecran.questionDeLaMancheDAvant()
                        + "\"). La reponse affichee est celle de la derniere manche : l'appariement"
                        + " est faux, et il est d'autant plus trompeur qu'il a l'air complet"
                        + " (REV-3)");
            }
        }
        return griefs;
    }

    /**
     * Le premier element qui porte ce texte <b>seul et entier</b>, hors bouton, ou {@code null}.
     * Cherche une valeur, jamais une classe CSS : le critere ne doit pas epingler le nom qu'un
     * correctif a choisi, sans quoi il ne garderait que ce correctif-la.
     */
    private static Element elementQuiPorte(Document document, String valeur) {
        String attendu = normaliser(valeur);
        for (Element element : document.select("*")) {
            if (!"button".equals(element.tagName()) && normaliser(element.ownText()).equals(attendu)) {
                return element;
            }
        }
        return null;
    }

    @Test
    void aucuneReponseDeFinDePartieNEstOrphelineDeSaQuestion() throws Exception {
        List<FinDePartieServie> population = population();

        assertThat(population)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun jeu")
                .isNotEmpty();
        assertThat(population)
                .as("REV-3, second critere : la seconde clause du critere est indemontrable sur une"
                        + " population ou aucune partie ne joue deux questions differentes")
                .anyMatch(ecran -> !ecran.questionDeLaMancheDAvant().equals(ecran.questionDeSaManche()));
        assertThat(griefs(population))
                .as("REV-3 : \"La bonne reponse : non\" ne commente aucune question visible pour un"
                        + " enfant qui a joue plusieurs manches depuis. La reponse et sa question"
                        + " voyagent ensemble ou l'ecran ne dit rien")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, premiere morsure : l'etat exact du produit avant ce lot - la reponse
     * nommee, et aucune question. La mutation porte sur la sortie reelle du serveur, et le rouge
     * nomme des paquets que ce fichier n'enumere pas.
     */
    @Test
    void uneReponseSansSaQuestionFaitRougirCeGardeFou() throws Exception {
        List<FinDePartieServie> population = population();
        assertThat(griefs(population)).as("la population reelle doit etre saine avant mutation")
                .isEmpty();

        List<FinDePartieServie> mutee = new ArrayList<>();
        for (FinDePartieServie ecran : population) {
            Document sansLaQuestion = Jsoup.parseBodyFragment(ecran.html());
            Element porteur = elementQuiPorte(sansLaQuestion, ecran.questionDeSaManche());
            if (porteur != null) {
                porteur.remove();
            }
            mutee.add(avecHtml(ecran, sansLaQuestion.body().html()));
        }

        assertThat(griefs(mutee))
                .as("un ecran de fin de partie qui nomme la reponse sans porter sa question doit"
                        + " rendre ce garde-fou rouge")
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3, seconde morsure, et c'est celle qui distingue ce garde-fou d'une simple
     * assertion de presence : la question <b>d'une autre manche de la meme partie</b>. L'ecran a
     * alors l'air complet - une question, une reponse, dans le meme bloc - et il ment.
     */
    @Test
    void uneQuestionAppariAUneAutreMancheFaitRougirCeGardeFou() throws Exception {
        List<FinDePartieServie> population = population();

        List<FinDePartieServie> mutee = new ArrayList<>();
        for (FinDePartieServie ecran : population) {
            if (ecran.questionDeLaMancheDAvant().equals(ecran.questionDeSaManche())) {
                continue;
            }
            Document mal = Jsoup.parseBodyFragment(ecran.html());
            Element porteur = elementQuiPorte(mal, ecran.questionDeSaManche());
            if (porteur != null) {
                porteur.text(ecran.questionDeLaMancheDAvant());
            }
            mutee.add(avecHtml(ecran, mal.body().html()));
        }

        assertThat(mutee).as("la mutation n'a de sens que sur des parties a deux questions distinctes")
                .isNotEmpty();
        assertThat(griefs(mutee))
                .as("un ecran de fin de partie qui apparie la reponse a la question d'une autre"
                        + " manche doit rendre ce garde-fou rouge, alors meme que rien n'y manque")
                .isNotEmpty();
    }

    private static FinDePartieServie avecHtml(FinDePartieServie ecran, String html) {
        return new FinDePartieServie(ecran.jeuServi(), ecran.reponse(), ecran.questionDeSaManche(),
                ecran.questionDeLaMancheDAvant(), html);
    }

    /**
     * La population : pour chaque paquet servi, une partie de deux manches jouee mal jusqu'au bout
     * sur les deux, et l'ecran de fin de partie qu'elle rend. Les deux items joues sont ceux que la
     * configuration a mis dans le jeu, dans l'ordre ou la source les rend : ce fichier n'a donc pas
     * a demander au domaine quelle manche il a tiree.
     */
    private List<FinDePartieServie> population() throws Exception {
        List<FinDePartieServie> population = new ArrayList<>();
        for (GameDefinition jeu : partie.jeuxDisponibles()) {
            List<Item> items = itemsDe(jeu.contenu(), MANCHES);
            MockHttpSession session = new MockHttpSession();
            mockMvc.perform(post("/parties/{jeuId}", jeu.id()).session(session));

            String finDePartie = null;
            for (Item item : items) {
                finDePartie = jouerMalJusquAuBout(jeu, item, session);
            }
            Item derniere = items.get(items.size() - 1);
            Item precedente = items.get(0);
            population.add(new FinDePartieServie(jeu.id(), reponseDe(derniere), enonceDe(derniere),
                    enonceDe(precedente), finDePartie));
        }
        return population;
    }

    /**
     * Trois erreurs puis le geste qui resout la manche revelee, sur la mecanique de l'item. Les deux
     * mecaniques servies n'ont pas le meme geste de sortie de revelation - un appui sur la reponse
     * nommee d'un cote, un bouton de suite de l'autre - et c'est la seule difference ici.
     */
    private String jouerMalJusquAuBout(GameDefinition jeu, Item item, MockHttpSession session)
            throws Exception {
        if (item instanceof Question question) {
            String faux = question.distracteurs().get(0);
            for (int essai = 0; essai < ESSAIS_AVANT_REVELATION; essai++) {
                mockMvc.perform(post("/parties/{jeuId}/coups", jeu.id())
                        .session(session).param("propositionChoisie", faux));
            }
            return mockMvc.perform(post("/parties/{jeuId}/coups", jeu.id())
                            .session(session).param("propositionChoisie", question.bonneReponse()))
                    .andReturn().getResponse().getContentAsString();
        }
        if (item instanceof ReponseAEcrire) {
            for (int essai = 0; essai < ESSAIS_AVANT_REVELATION; essai++) {
                mockMvc.perform(post("/parties/{jeuId}/reponses", jeu.id())
                        .session(session).param("reponseEcrite", CHAINE_REFUSEE));
            }
            return mockMvc.perform(post("/parties/{jeuId}/manche", jeu.id()).session(session))
                    .andReturn().getResponse().getContentAsString();
        }
        throw new IllegalStateException(refusDeLaForme(item, jeu.id()));
    }

    private static String enonceDe(Item item) {
        if (item instanceof Question question) {
            return question.enonce();
        }
        if (item instanceof ReponseAEcrire aEcrire) {
            return aEcrire.enonce();
        }
        throw new IllegalStateException(refusDeLaForme(item, "?"));
    }

    private static String reponseDe(Item item) {
        if (item instanceof Question question) {
            return question.bonneReponse();
        }
        if (item instanceof ReponseAEcrire aEcrire) {
            return aEcrire.formeDeReference();
        }
        throw new IllegalStateException(refusDeLaForme(item, "?"));
    }

    /** ADR-0013 obligation 2 : une forme non jugee est un echec du garde-fou, jamais un silence. */
    private static String refusDeLaForme(Item item, String jeuId) {
        return "forme d'item qu'aucun ecran de fin de partie ne sait jouer, et que ce garde-fou ne"
                + " sait donc pas juger : " + item.getClass().getName() + " (jeu " + jeuId + ")."
                + " Le jour ou cette forme est servie, l'appariement de son ecran de fin de partie"
                + " se decide ici plutot que de passer vert par omission";
    }

    /**
     * Rejoue le tirage d'une source reelle pour en prendre les premiers items, sans exposer d'API
     * supplementaire sur le domaine - meme geste que {@code PropositionsAccentueesJouablesIT} et que
     * le garde-fou voisin d'ADR-0083. Le hasard vaut zero dans toute cette configuration, donc
     * l'ordre rendu ici est celui que la partie jouera.
     */
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
        if (items.isEmpty()) {
            throw new IllegalStateException("un jeu servi dont la source ne rend aucun item :"
                    + " ADR-0013 obligation 2");
        }
        return items;
    }

    /**
     * <b>Une partie de deux manches par paquet servi, derivee du catalogue de production.</b> Le
     * catalogue reel tire au hasard dans un paquet entier et joue un nombre de manches decide par le
     * contenu : jouer une partie dont on connait les deux items demanderait de connaitre son tirage,
     * ce qu'aucune API du domaine n'expose et qu'il ne faut pas lui demander. Chaque paquet devient
     * donc une partie de deux manches sur ses deux premiers items, qui garde son entree de catalogue
     * - titre, consigne, consigne adulte, mecanique, descripteur - et la clause M21 de sa source.
     *
     * <p>Le catalogue de depart vient de l'appel meme que {@code ContenuConfiguration} fait
     * ({@code new YamlContentPackLoader().games()}), et non d'une liste ecrite ici. Ce bean prend la
     * place du bean de production parce que celui-ci est un {@code ConditionalOnMissingBean}.
     */
    @TestConfiguration
    static class DeuxManchesParPaquetServiConfiguration {

        @Bean
        ContentCatalogPort deuxManchesParPaquetServi() {
            List<GameDefinition> parties = new ArrayList<>();
            for (GameDefinition jeu : new YamlContentPackLoader().games()) {
                List<Item> deux = itemsDe(jeu.contenu(), MANCHES);
                parties.add(new GameDefinition(jeu.id() + "--deux-manches", jeu.titre(),
                        jeu.consigne(), jeu.mecanique(), jeu.descripteur(),
                        new ListContentSource(jeu.contenu().descriptor(), deux,
                                jeu.contenu().indicesNommentLaReponse()),
                        deux.size(), jeu.consigneAdulte()));
            }
            return () -> parties;
        }

        /** Le tirage n'a plus rien a choisir - deux items, deux manches - mais il reste rejouable. */
        @Bean
        RandomSource hasard() {
            return borne -> 0;
        }
    }
}
