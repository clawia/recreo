package com.recreo.adapter.web;

import com.recreo.domain.mechanic.SessionStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0027 : tout ecran de jeu rendu par le serveur declare comment le tirage que ses colonnes
 * montrent est arrive, et seul un tirage qu'un geste de l'enfant a fait arriver porte le marqueur qui
 * autorise un mouvement.
 *
 * <p><b>Le defaut que ce garde-fou ferme, et il etait dans le mecanisme d'ADR-0026 lui-meme.</b>
 * ADR-0026 section 2 fait demarrer l'animation a l'insertion de l'element dans le document, en
 * comptant sur htmx pour fournir le declencheur. C'est insuffisant : un element entre aussi dans le
 * document quand le navigateur analyse une page entiere, et {@code accueil.html} rend le fragment de
 * manche en clair sur la branche de reprise (S1-4), sans echange htmx et sans aucun geste. Un enfant
 * qui recharge sa page verrait donc les colonnes tourner sans avoir rien touche, ce que
 * {@code nadia-prof} refuse nommement (arbitrage du 2026-08-10, section 6 : une machine qui bouge
 * toute seule est l'image dont cet ecran doit se separer).
 *
 * <p><b>Pourquoi le serveur, et pas le CSS.</b> Une fois le fragment insere, le document assemble est
 * exactement le meme dans les deux cas : meme arbre, memes classes, meme place dans
 * {@code <main id="jeu">}. Aucun selecteur CSS ne peut donc les distinguer, et la distinction n'a
 * d'existence possible que si le serveur la pose. Ce n'est pas un choix de conception, c'est une
 * contrainte.
 *
 * <p><b>Amendement du 2026-08-13, condition M10 de {@code nadia-prof} : un geste ne suffit pas, et le
 * sujet du marqueur est le tirage.</b> La clause d'origine exigeait que <i>tout</i> ecran de jeu
 * echange par htmx declare {@code par-un-geste}, sans exception. Elle etait juste tant qu'un echange
 * htmx renouvelait toujours la question ; le cycle de manche de S10 l'a rendue fausse. Un echec avec
 * des essais restants, l'entree en revelation et un appui a cote apres la revelation sont trois
 * echanges demandes par un vrai geste qui rendent <b>le meme tirage</b>. Les colonnes y rejouaient
 * leur defilement, ce que {@code nadia-prof} refuse nommement : le mouvement dit que la machine
 * distribue, et rien n'a ete distribue ; un enfant de CP croit alors que la question a change et relit
 * tout ; le prix est jusqu'a 18 s d'attente ajoutee pour celui qui se trompe le plus. La propriete
 * gardee devient donc un <b>si et seulement si</b> : un ecran declare le marqueur exactement quand un
 * geste de l'enfant a fait arriver un tirage neuf. Ce n'est pas une route qui est exemptee - aucune ne
 * l'est - c'est le sujet de {@code data-arrivee} qui est enfin nomme : ce qui arrive, ou n'arrive pas,
 * est le tirage que les colonnes montrent, pas la reponse HTTP.
 *
 * <p><b>Ce que ce sujet ne peut pas etre.</b> "Le tirage a change" ne se lit pas en comparant l'ecran
 * a celui d'avant : deux manches d'un meme item rendent un enonce et des propositions identiques
 * (c'est ce que fait {@code jeu-repete}), donc la comparaison ne distingue pas une question neuve
 * d'une question inchangee. Ce qui se lit, et qui est exactement la propriete voulue, est <b>la trace
 * d'un essai deja fait sur ce tirage</b> : la marque que le serveur pose sur une proposition deja
 * pressee. Elle est lue dans le gabarit et non ecrite ici.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la liste des routes vient de {@code RequestMappingHandlerMapping}
 *       comme dans {@code ToutEcranEchangeEstUnDocumentJouableTest} ; une route neuve non exercee rend
 *       ce garde-fou rouge. Ce qui distingue un document complet d'un fragment est lu <b>dans la
 *       reponse elle-meme</b> - la presence d'un element {@code <html>} - jamais dans une liste de
 *       routes ecrite ici. C'est le seul critere qui ne se perime pas : un fragment est par definition
 *       ce que htmx echange, un document complet est par definition ce que le navigateur charge ;</li>
 *   <li><b>derivation totale</b> : un ecran de jeu qui ne porte aucun des deux marqueurs, ou l'une de
 *       leurs valeurs, est un echec et non un cas ignore - la comparaison porte sur la valeur exacte
 *       attendue, jamais sur une liste de valeurs tolerees. Le compilateur ferme une moitie du produit :
 *       le parametre {@code gesteDeLEnfant} de {@code PartieController.mancheModele} oblige chaque
 *       point d'appel a se prononcer sur le facteur que lui seul connait. L'autre facteur ne vit a
 *       aucun point d'appel : il est calcule dans ce point de passage unique, donc aucune route
 *       existante ou future ne peut l'oublier ni y echapper ;</li>
 *   <li><b>mise a l'epreuve</b> : sur la sortie reelle du serveur, mutee - un ecran d'echec qui
 *       declare le geste, et une reprise silencieuse qui le declare. Les deux jumeaux corriges sont la
 *       meme sortie telle qu'elle part, et restent verts (voir ADR-0027).</li>
 * </ul>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LEcranDeJeuDeclareCommentIlEstArriveTest {

    /** Deux items, donc deux coups suffisent a terminer la partie (PartieControllerTest). */
    private static final String JEU_ID = "un-jeu";

    /**
     * ADR-0034 : le meme item redonne indefiniment sur une partie de dix manches
     * (PartieControllerTest), seul jeu du catalogue de test sur lequel une reponse fausse laisse la
     * partie en cours.
     */
    private static final String JEU_REPETE_ID = "jeu-repete";

    /** La proposition fausse de l'item de {@code JEU_REPETE_ID} (PartieControllerTest). */
    private static final String PROPOSITION_FAUSSE = "mauvaise";

    /**
     * ADR-0034, amendement : deux manches et un contenu combinatoire (PartieControllerTest), donc une
     * partie que deux reponses fausses terminent sur un verdict faux.
     */
    private static final String JEU_INFINI_ID = "jeu-infini";

    /** Le marqueur d'ecran de jeu d'ADR-0022, qui dit ou l'enfant est. */
    private static final String ECRAN_DE_JEU = "ecran-de-jeu";

    /**
     * Le marqueur d'ADR-0027, qui dit comment l'enfant est arrive sur cet ecran. Un attribut et non
     * une classe, pour la meme raison qu'ADR-0017 a choisi data-role : ce qu'il declare n'est pas un
     * habillage, et une classe aurait exige une regle de style qui ne style rien.
     */
    private static final String ATTRIBUT_D_ARRIVEE = "data-arrivee";
    private static final String PAR_UN_GESTE = "par-un-geste";
    private static final String SANS_GESTE = "sans-geste";

    /** Le gabarit de l'ecran de jeu, donc la source qui gouverne ses marqueurs (ADR-0015). */
    private static final Path GABARIT_DE_MANCHE =
            Path.of("src/main/resources/templates/fragments/manche.html");

    /** ADR-0013 : le jeu fautif de la lecture de la marque d'un essai deja fait. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/LEcranDeJeuDeclareCommentIlEstArriveTest");

    /**
     * L'attribut de modele par lequel le controleur transmet au gabarit les propositions deja
     * essayees sur la manche courante. C'est le seul nom ecrit ici : la marque qui en decoule est lue
     * dans le gabarit.
     */
    private static final String MODELE_DES_ESSAIS = "propositionsEssayees";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestMappingHandlerMapping routes;

    /**
     * Une reponse du serveur telle qu'elle part. {@code documentComplet} est lu dans le corps, jamais
     * deduit de la route : c'est ce qui fait que ce garde-fou n'a pas de liste a tenir a jour.
     */
    private record Reponse(String nom, String route, String corps, boolean documentComplet) {
    }

    private Reponse reponse(String nom, String route, MockHttpServletRequestBuilder requete)
            throws Exception {
        String corps = mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
        boolean complet = !Jsoup.parse(corps).select("html").isEmpty()
                && corps.strip().toLowerCase(java.util.Locale.ROOT).startsWith("<!doctype");
        return new Reponse(nom, route, corps, complet);
    }

    /**
     * Le corpus, produit en jouant une vraie partie de bout en bout. Chaque route du controleur y
     * passe au moins une fois, et la branche de reprise - celle qui porte le defaut - y est exercee
     * avec une partie reellement en cours.
     *
     * <p>S10 : depuis que le cycle de manche absorbe la reponse juste comme la fausse, {@code POST
     * /parties/{jeuId}/coups} rend un ecran de manche tant que la partie continue - juste, faux, ou
     * en revelation - et ne rend l'ecran de retour que sur la fin de partie. {@code GET
     * /parties/{jeuId}/manche} a disparu avec son seul appelant ("Encore !").</p>
     */
    private List<Reponse> reponses() throws Exception {
        List<Reponse> reponses = new ArrayList<>();
        MockHttpSession partie = new MockHttpSession();

        reponses.add(reponse("accueil, aucune partie en cours", "GET /", get("/")));
        reponses.add(reponse("manche 1, demandee par un appui", "POST /parties/{jeuId}",
                post("/parties/{jeuId}", JEU_ID).session(partie)));
        reponses.add(reponse("accueil pendant une partie en cours (reprise silencieuse)", "GET /",
                get("/").session(partie)));
        reponses.add(reponse("manche apres une reponse juste, la partie continue",
                "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "1")));
        reponses.add(reponse("manche apres une reponse fausse, la partie continue",
                "POST /parties/{jeuId}/coups", coupFaux()));
        reponses.add(reponse("manche en revelation, la partie continue",
                "POST /parties/{jeuId}/coups", revelation()));
        reponses.add(reponse("appui a cote apres la revelation, la manche ne bouge pas",
                "POST /parties/{jeuId}/coups", appuiACoteApresLaRevelation()));
        reponses.add(reponse("fin de partie sur une reponse fausse", "POST /parties/{jeuId}/coups",
                finDePartieFaussee()));
        reponses.add(reponse("retour de la derniere manche, fin de partie", "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "2")));
        reponses.add(reponse("retour explicite a la liste des jeux", "POST /retour",
                post("/retour").session(partie)));
        reponses.add(reponse("ecran d'erreur, partie introuvable", "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(new MockHttpSession())
                        .param("propositionChoisie", "1")));

        return reponses;
    }

    /**
     * Une reponse fausse jouee sur une partie qui continue, avec des essais restants (M5) : la
     * manche ne bouge pas, sur sa propre session.
     */
    private MockHttpServletRequestBuilder coupFaux() throws Exception {
        return unEchec(sessionApresDesEchecs(0));
    }

    /** M5 : trois echecs sur la meme manche declenchent la revelation, sur sa propre session. */
    private MockHttpServletRequestBuilder revelation() throws Exception {
        return unEchec(sessionApresDesEchecs(2));
    }

    /**
     * M5 : sur une manche revelee, un appui sur une autre proposition que la reponse nommee ne
     * change rien - ni essai compte, ni etat modifie. C'est le troisieme des trois etats que la
     * condition M10 nomme, et le corpus ne le portait pas : sans lui, une clause qui n'aurait ferme
     * que les deux premiers serait passee verte.
     */
    private MockHttpServletRequestBuilder appuiACoteApresLaRevelation() throws Exception {
        return unEchec(sessionApresDesEchecs(3));
    }

    /**
     * Une session dont la manche courante a deja essuye {@code echecs} echecs, sur le seul jeu du
     * catalogue de test qu'une reponse fausse ne termine pas. Trois echecs la laissent en revelation
     * (M5) ; un quatrieme appui a cote ne change plus rien.
     */
    private MockHttpSession sessionApresDesEchecs(int echecs) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));
        for (int essai = 0; essai < echecs; essai++) {
            mockMvc.perform(unEchec(session));
        }
        return session;
    }

    private static MockHttpServletRequestBuilder unEchec(MockHttpSession session) {
        return post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                .param("propositionChoisie", PROPOSITION_FAUSSE);
    }

    /**
     * ADR-0034, amendement : la derniere manche resolue par revelation, donc une partie qui se
     * termine sur un verdict faux (M11 clause 3). Le jeu infini, a hasard fixe, represente toujours
     * la meme addition (0 + 1) - sa bonne reponse, "1", est connue sans lire aucune proposition
     * rendue. Chacune de ses deux manches est resolue par trois echecs puis la reponse revelee.
     */
    private MockHttpServletRequestBuilder finDePartieFaussee() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_INFINI_ID).session(partie));
        for (int manche = 0; manche < 2; manche++) {
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            if (manche == 1) {
                return post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                        .param("propositionChoisie", "1");
            }
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "1"));
        }
        throw new IllegalStateException("inatteignable");
    }

    /**
     * ADR-0034 : ce corpus ne jouait que des bonnes reponses, et son perimetre derive par route ne
     * pouvait pas le dire - {@code POST /parties/{jeuId}/coups} y figurait deja. Les cinq clauses de
     * cette classe, dont celle qui refuse un mot leurre dans le conteneur de propositions, ne voyaient
     * donc jamais l'ecran que le serveur rend quand l'enfant se trompe.
     *
     * <p>La lecture des deux marqueurs est celle de {@code ToutEcranEchangeEstUnDocumentJouableTest},
     * appelee et non recopiee : deux lectures libres de diverger laisseraient un verdict sortir du
     * corpus d'ici et de celui de la-bas, donc controle par ni l'un ni l'autre (meme raison qu'ADR-0033
     * point 3).</p>
     */
    @Test
    void leCorpusExerceChaqueCoupleDeVerdictEtDeSuite() throws Exception {
        List<Reponse> reponses = reponses();
        String finDePartie = ToutEcranEchangeEstUnDocumentJouableTest.marqueurDeFinDePartie();

        for (String verdict : ToutEcranEchangeEstUnDocumentJouableTest.marqueursDeVerdict()) {
            for (SessionStatus statut : SessionStatus.values()) {
                boolean termineeAttendue = statut == SessionStatus.TERMINEE;
                assertThat(reponses)
                        .as("ADR-0034 : aucune reponse de ce corpus ne joint \"%s\" a un statut %s. Tout "
                                + "ce que le serveur rend dans cet etat echappe alors aux clauses de "
                                + "cette classe", verdict, statut)
                        .anyMatch(reponse -> {
                            Document rendu = Jsoup.parse(reponse.corps());
                            return !rendu.select("." + verdict).isEmpty()
                                    && !rendu.select("." + finDePartie).isEmpty() == termineeAttendue;
                        });
            }
        }
    }

    /** Les ecrans de jeu que cette reponse porte, au sens du marqueur d'ADR-0022. */
    private static List<Element> ecransDeJeu(Reponse reponse) {
        return Jsoup.parse(reponse.corps()).select("." + ECRAN_DE_JEU);
    }

    /**
     * L'ecran de jeu qui peut porter des colonnes, c'est-a-dire celui qui offre des propositions a
     * choisir. L'ecran de retour porte le meme marqueur d'ADR-0022 et n'a rien a animer ; il est donc
     * hors de la population de ce garde-fou, et ADR-0027 dit pourquoi cette exclusion est sure.
     */
    private static boolean offreDesPropositions(Element ecran) {
        return !ecran.select(".propositions").isEmpty();
    }

    private static List<Element> ecransAnimables(Reponse reponse) {
        return ecransDeJeu(reponse).stream()
                .filter(LEcranDeJeuDeclareCommentIlEstArriveTest::offreDesPropositions)
                .toList();
    }

    /** La valeur declaree du marqueur, ou une mention explicite de son absence. */
    private static String arrivee(Element ecran) {
        String valeur = ecran.attr(ATTRIBUT_D_ARRIVEE).strip();
        return valeur.isEmpty() ? "<aucune>" : valeur;
    }

    private static boolean declare(Element ecran, String valeur) {
        return valeur.equals(arrivee(ecran));
    }

    /**
     * La marque que le serveur pose sur une proposition deja pressee sur la manche courante (M9),
     * lue <b>dans le gabarit</b> et non ecrite ici (ADR-0015 : le perimetre se derive de la source
     * qui gouverne le rendu). C'est le seul fait de la sortie du serveur qui dise que l'enfant a deja
     * repondu sur ce tirage, et donc que le geste suivant n'en a pas fait arriver un neuf.
     *
     * <p>Le jour ou cette marque cesse d'exister, ou d'etre gouvernee par {@code
     * propositionsEssayees}, cette lecture rougit avec la marche a suivre plutot que de rendre une
     * marque introuvable - avec laquelle tout ecran passerait pour un tirage neuf et l'amendement de
     * M10 s'annulerait en silence.
     */
    static String marqueurDEssaiDejaFait() throws IOException {
        return marqueurDEssaiDejaFait(GABARIT_DE_MANCHE);
    }

    static String marqueurDEssaiDejaFait(Path chemin) throws IOException {
        Document gabarit = Jsoup.parse(chemin.toFile(), "UTF-8");
        List<String> marqueurs = new ArrayList<>();
        for (Element porteur : gabarit.getAllElements()) {
            for (Attribute attribut : porteur.attributes()) {
                if (!attribut.getValue().contains("${" + MODELE_DES_ESSAIS)) {
                    continue;
                }
                String[] morceaux = attribut.getValue().split("'");
                for (int i = 1; i < morceaux.length; i += 2) {
                    if (!morceaux[i].isBlank()) {
                        marqueurs.add(morceaux[i].strip());
                    }
                }
            }
        }
        assertThat(marqueurs)
                .as("ADR-0027, amendement du 2026-08-13 : la marque d'un essai deja fait se lit dans "
                        + "%s, sur l'attribut qui choisit un litteral selon ${%s}. Une seule est "
                        + "attendue. Si le gabarit exprime desormais cet etat autrement, c'est ici "
                        + "qu'il faut re-deriver la lecture, pas ailleurs qu'il faut ecrire un nom de "
                        + "classe a la main", chemin, MODELE_DES_ESSAIS)
                .hasSize(1);
        return marqueurs.get(0);
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve de la lecture elle-meme : un gabarit qui marque encore
     * les propositions deja essayees, mais dont la classe est calculee dans le modele au lieu d'etre un
     * litteral du gabarit. C'est le mode de defaillance qui compte le plus pour cet amendement : avec
     * une marque introuvable, tout ecran passerait pour un tirage neuf, le "si et seulement si"
     * redeviendrait le "tout echange declare le geste" d'avant, et aucun test ne rougirait.
     */
    @Test
    void unGabaritQuiNExprimePlusLaMarqueDUnEssaiFaitEchouerLaLecture() {
        Path fautif = CONTRE_EXEMPLE
                .resolve("marque-d-essai-sans-litteral/templates/fragments/manche.html");

        assertThatThrownBy(() -> marqueurDEssaiDejaFait(fautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la marque d'un essai deja fait se lit dans");
    }

    /**
     * Le tirage que ces colonnes montrent est-il neuf pour l'enfant ? Vrai quand aucune proposition ne
     * porte la marque d'un essai deja fait.
     */
    private static boolean tirageNeuf(Element ecran, String marqueurDEssai) {
        return ecran.select("." + marqueurDEssai).isEmpty();
    }

    /**
     * ADR-0027, clause amendee : la valeur que le serveur doit declarer sur cet ecran. Un seul endroit
     * la calcule pour les deux populations, parce que les deux clauses ci-dessous ne sont que les deux
     * moities du meme "si et seulement si" - deux calculs libres de diverger laisseraient un ecran juge
     * par ni l'un ni l'autre (meme raison qu'ADR-0033 point 3).
     */
    private static String arriveeAttendue(boolean echangeParHtmx, boolean tirageNeuf) {
        return echangeParHtmx && tirageNeuf ? PAR_UN_GESTE : SANS_GESTE;
    }

    /** Pourquoi cette valeur, dans les mots du refus qui l'exige. */
    private static String motifDArrivee(boolean echangeParHtmx, boolean tirageNeuf) {
        if (!echangeParHtmx) {
            return "Le navigateur a charge une page entiere, sans qu'aucun geste de l'enfant ne l'ait "
                    + "demandee : les colonnes tourneraient devant un enfant qui a seulement recharge "
                    + "sa page (nadia-prof, arbitrage du 2026-08-10, section 6).";
        }
        if (!tirageNeuf) {
            return "Un geste a bien demande cet echange, mais l'enfant a deja essaye une proposition de "
                    + "ce tirage : ce geste ne l'a pas renouvele. Un mouvement dirait ici que la machine "
                    + "vient de distribuer alors que rien n'a ete distribue, un enfant de CP croirait "
                    + "que la question a change et relirait tout, et le rebalayage coute jusqu'a 18 s "
                    + "d'attente a celui qui se trompe le plus (nadia-prof, condition M10).";
        }
        return "Un geste de l'enfant a fait arriver un tirage neuf : c'est le seul cas ou une colonne a "
                + "le droit de bouger, et sans lui aucune ne bougerait jamais.";
    }

    /**
     * Les ecarts entre ce que cette reponse declare et ce que le "si et seulement si" exige, un par
     * ecran fautif. Un marqueur absent ou d'une troisieme valeur est un ecart comme un autre : la
     * comparaison porte sur la valeur exacte attendue, jamais sur une liste de valeurs tolerees.
     */
    private static List<String> ecartsDArrivee(Reponse reponse, String marqueurDEssai) {
        List<String> ecarts = new ArrayList<>();
        boolean echange = !reponse.documentComplet();
        for (Element ecran : ecransAnimables(reponse)) {
            boolean neuf = tirageNeuf(ecran, marqueurDEssai);
            String attendue = arriveeAttendue(echange, neuf);
            if (declare(ecran, attendue)) {
                continue;
            }
            ecarts.add(String.format(
                    "ADR-0027 : sur \"%s\" (%s), cet ecran de jeu declare %s=\"%s\" et doit declarer "
                            + "\"%s\". %s",
                    reponse.nom(), reponse.route(), ATTRIBUT_D_ARRIVEE, arrivee(ecran), attendue,
                    motifDArrivee(echange, neuf)));
        }
        return ecarts;
    }

    /**
     * Clause 1, celle qui ferme le defaut d'origine : un ecran de jeu qui arrive dans un document
     * complet n'a ete demande par aucun geste, donc ne porte jamais le marqueur qui autorise un
     * mouvement.
     */
    @Test
    void aucunEcranDeJeuArrivantDansUnDocumentCompletNAutoriseUnMouvement() throws Exception {
        String marqueurDEssai = marqueurDEssaiDejaFait();
        List<String> juges = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            if (!reponse.documentComplet()) {
                continue;
            }
            ecransAnimables(reponse).forEach(ecran -> juges.add(reponse.nom()));
            ecarts.addAll(ecartsDArrivee(reponse, marqueurDEssai));
        }

        assertThat(ecarts).as("ADR-0027 clause 1").isEmpty();
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de jeu n'est rendu dans un "
                        + "document complet. La branche de reprise de accueil.html en rend un ; si elle "
                        + "disparait, cette clause devient vide et ADR-0027 doit etre repris")
                .isNotEmpty();
    }

    /**
     * Clause 2, l'autre bout, amendee le 2026-08-13 par la condition M10 : un ecran de jeu echange par
     * htmx autorise le mouvement <b>si et seulement si</b> le geste qui l'a demande a fait arriver un
     * tirage neuf. Les deux sens comptent, et chacun ferme un defaut different :
     *
     * <ul>
     *   <li>sans le sens "seulement si", les trois echanges qui laissent l'enfant sur la meme question
     *       rebalaient leurs colonnes - c'est l'etat du depot la veille de cet amendement, et le refus
     *       de {@code nadia-prof} porte precisement dessus ;</li>
     *   <li>sans le sens "si", il suffirait de ne jamais poser le marqueur pour rendre la clause 1
     *       verte a vide, et plus aucun rouleau ne tournerait jamais.</li>
     * </ul>
     */
    @Test
    void unEcranEchangeAutoriseLeMouvementSiEtSeulementSiSonTirageEstNeuf() throws Exception {
        String marqueurDEssai = marqueurDEssaiDejaFait();
        List<String> tiragesNeufs = new ArrayList<>();
        List<String> tiragesDejaEssayes = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            if (reponse.documentComplet()) {
                continue;
            }
            for (Element ecran : ecransAnimables(reponse)) {
                (tirageNeuf(ecran, marqueurDEssai) ? tiragesNeufs : tiragesDejaEssayes)
                        .add(reponse.nom());
            }
            ecarts.addAll(ecartsDArrivee(reponse, marqueurDEssai));
        }

        assertThat(ecarts).as("ADR-0027 clause 2, amendee").isEmpty();
        assertThat(tiragesNeufs)
                .as("ADR-0003 : sans un seul echange qui fait arriver un tirage neuf, le sens \"si\" de "
                        + "cette clause est vide et elle resterait verte sur un depot ou aucune colonne "
                        + "ne bouge jamais")
                .isNotEmpty();
        assertThat(tiragesDejaEssayes)
                .as("ADR-0003 : sans un seul echange qui laisse l'enfant sur une question qu'il a deja "
                        + "essayee, le sens \"seulement si\" est vide et l'amendement du 2026-08-13 ne "
                        + "prouve rien - c'est exactement la population que la clause d'origine "
                        + "acceptait a tort")
                .isNotEmpty();
    }

    /**
     * La meme reponse, avec la valeur du marqueur remplacee sur chacun de ses ecrans de jeu. L'objet
     * mis a l'epreuve est ainsi la sortie reelle du serveur et non une fixture ecrite de ma main
     * (lecon de s05, reprise par ADR-0015).
     */
    private static Reponse declarant(Reponse reponse, String valeur) {
        Document mute = Jsoup.parse(reponse.corps());
        mute.select("." + ECRAN_DE_JEU).forEach(ecran -> ecran.attr(ATTRIBUT_D_ARRIVEE, valeur));
        return new Reponse(reponse.nom(), reponse.route(), mute.outerHtml(), reponse.documentComplet());
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve du sens "seulement si" que l'amendement du 2026-08-13
     * ajoute, sur un objet neuf : l'ecran que le serveur rend reellement sur un echec avec des essais
     * restants, dont le seul marqueur d'arrivee est mute. Son jumeau corrige est cette meme sortie
     * telle qu'elle part, et il reste vert - sans quoi cette epreuve ne dirait pas ce qu'elle croit
     * dire.
     */
    @Test
    void unEcranQuiDeclareLeGesteSurUneQuestionInchangeeFaitEchouerLeGardeFou() throws Exception {
        String marqueurDEssai = marqueurDEssaiDejaFait();
        Reponse jumeauCorrige = reponse("manche apres une reponse fausse, la partie continue",
                "POST /parties/{jeuId}/coups", coupFaux());
        List<Element> ecrans = ecransAnimables(jumeauCorrige);

        assertThat(ecrans).as("l'objet de cette epreuve est un ecran de jeu unique").hasSize(1);
        assertThat(tirageNeuf(ecrans.get(0), marqueurDEssai))
                .as("cet ecran porte bien la marque d'un essai deja fait, sans quoi il n'eprouve pas "
                        + "le sens \"seulement si\"")
                .isFalse();
        assertThat(ecartsDArrivee(jumeauCorrige, marqueurDEssai))
                .as("le jumeau corrige est la sortie telle que le serveur l'envoie : elle reste verte")
                .isEmpty();

        assertThat(ecartsDArrivee(declarant(jumeauCorrige, PAR_UN_GESTE), marqueurDEssai))
                .as("un ecran qui declare le geste sur une question que l'enfant n'a pas changee doit "
                        + "rendre ce garde-fou rouge")
                .hasSize(1)
                .allSatisfy(ecart -> assertThat(ecart)
                        .contains("l'enfant a deja essaye une proposition de ce tirage")
                        .contains("doit declarer \"" + SANS_GESTE + "\""));
    }

    /**
     * ADR-0013 obligation 3, sur le mode de defaillance que la clause d'origine visait : il ne doit pas
     * se rouvrir par l'amendement. L'ecran de la reprise silencieuse, mute pour declarer le geste, est
     * le defaut d'origine repose octet pour octet sur la sortie reelle du serveur - il rougit encore, et
     * pour son propre motif, jamais celui de M10.
     */
    @Test
    void unEcranDeRepriseQuiDeclareLeGesteFaitEchouerEncoreLeGardeFou() throws Exception {
        String marqueurDEssai = marqueurDEssaiDejaFait();
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(partie));
        Reponse reprise = reponse("reprise silencieuse", "GET /", get("/").session(partie));

        assertThat(ecartsDArrivee(reprise, marqueurDEssai))
                .as("le jumeau corrige - la reprise telle que le serveur la rend - reste vert")
                .isEmpty();

        assertThat(ecartsDArrivee(declarant(reprise, PAR_UN_GESTE), marqueurDEssai))
                .as("une reprise silencieuse qui declare le geste doit rendre ce garde-fou rouge, et "
                        + "l'amendement de M10 ne doit pas avoir rouvert ce chemin")
                .hasSize(1)
                .allSatisfy(ecart -> assertThat(ecart)
                        .contains("Le navigateur a charge une page entiere")
                        .contains("doit declarer \"" + SANS_GESTE + "\""));
    }

    /**
     * ADR-0013 obligation 1 : le perimetre vient du controleur, pas du corpus ci-dessus. Une route
     * neuve que ce garde-fou n'exerce pas le rend rouge, au lieu de rendre un ecran de jeu sans que
     * personne n'ait verifie comment il declare son arrivee.
     */
    @Test
    void chaqueRouteDuControleurEstExerceeParCeGardeFou() throws Exception {
        Set<String> exercees = new TreeSet<>();
        reponses().forEach(reponse -> exercees.add(reponse.route()));

        assertThat(exercees)
                .as("ADR-0027 : toute route qui peut rendre un ecran de jeu entre dans ce corpus")
                .containsExactlyInAnyOrderElementsOf(routesDeclarees());
    }

    /**
     * Le corpus distingue bien deux populations. Sans cette assertion, une erreur de detection de
     * document complet - tout classe du meme cote - rendrait l'une des deux clauses vide sans le dire.
     */
    @Test
    void leCorpusPorteALaFoisDesDocumentsCompletsEtDesFragments() throws Exception {
        List<Reponse> reponses = reponses();

        assertThat(reponses).anyMatch(Reponse::documentComplet);
        assertThat(reponses).anyMatch(reponse -> !reponse.documentComplet());
        assertThat(reponses.stream().filter(Reponse::documentComplet).map(Reponse::nom).toList())
                .as("les documents complets sont ceux que le navigateur charge, et eux seuls")
                .allMatch(nom -> nom.startsWith("accueil"));
    }

    /**
     * Epinglage du chemin qui porte le defaut, sur la sortie reelle du serveur. Il nomme les deux
     * faits qui, ensemble, produisaient le mouvement non demande : l'ecran de jeu est bien rendu par
     * la reprise, et il l'est dans un document complet. Si l'un des deux cesse d'etre vrai - par
     * exemple si {@code camille-po} decide que la reprise ne rend plus la manche en clair - ce test
     * devient rouge et son message dit que la clause 1 perd son objet.
     */
    @Test
    void laRepriseSilencieuseRendBienUnEcranDeJeuDansUnDocumentComplet() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(partie));

        Reponse reprise = reponse("reprise", "GET /", get("/").session(partie));

        assertThat(reprise.documentComplet())
                .as("la reprise est un rendu serveur complet, pas un echange htmx")
                .isTrue();
        assertThat(ecransAnimables(reprise))
                .as("ADR-0027 : la reprise rend l'ecran de jeu en clair dans la page. Si ce n'est plus "
                        + "le cas, le defaut que ce garde-fou ferme n'existe plus et ADR-0027 doit etre "
                        + "repris plutot que laisse vert a vide")
                .hasSize(1);
        assertThat(arrivee(ecransAnimables(reprise).get(0))).isEqualTo(SANS_GESTE);
    }

    private Set<String> routesDeclarees() {
        Set<String> declarees = new TreeSet<>();
        routes.getHandlerMethods().forEach((information, methode) -> {
            if (!PartieController.class.equals(methode.getBeanType())) {
                return;
            }
            for (String verbe : verbes(information)) {
                for (String chemin : information.getPathPatternsCondition().getPatternValues()) {
                    declarees.add(verbe + " " + chemin);
                }
            }
        });
        assertThat(declarees)
                .as("le controleur doit declarer au moins une route, sans quoi ce recensement est vide")
                .isNotEmpty();
        return declarees;
    }

    private static Set<String> verbes(RequestMappingInfo information) {
        Set<String> verbes = new TreeSet<>();
        information.getMethodsCondition().getMethods().forEach(methode -> verbes.add(methode.name()));
        assertThat(verbes)
                .as("une route sans verbe declare repond a tous les verbes ; ce garde-fou ne saurait "
                        + "pas lesquels exercer")
                .isNotEmpty();
        return verbes;
    }

    /**
     * ADR-0027, propriete "jamais un autre mot" de {@code nadia-prof} (arbitrage du 2026-08-10,
     * section 2) : une colonne montre soit sa propre proposition, soit aucun texte. La forme classique
     * d'un rouleau - une bande de faces intermediaires - se voit ici, parce que ces faces seraient du
     * texte que le serveur envoie a l'interieur du conteneur de propositions.
     *
     * <p>Ce que cette clause couvre exactement, et ce qu'elle ne couvre pas, est ecrit dans ADR-0027 :
     * elle refuse un mot leurre <b>rendu par le serveur</b>. Un mot peint dans une image reste hors de
     * sa portee et reste a {@code nadia-prof}.
     */
    @Test
    void aucunTexteAutreQueLesPropositionsNEntreDansLeConteneurDePropositions() throws Exception {
        List<String> juges = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            for (Element conteneur : Jsoup.parse(reponse.corps()).select(".propositions")) {
                Set<String> textesDesPropositions = new TreeSet<>();
                conteneur.select(".proposition")
                        .forEach(bouton -> textesDesPropositions.add(bouton.text().strip()));
                Set<String> textesRendus = new TreeSet<>();
                for (Element porteur : conteneur.select("*")) {
                    String propre = porteur.ownText().strip();
                    if (!propre.isEmpty()) {
                        textesRendus.add(propre);
                    }
                }
                juges.add(reponse.nom());
                assertThat(textesRendus)
                        .as("ADR-0027 : sur \"%s\" (%s), le conteneur de propositions porte du texte qui "
                                + "n'est aucune des propositions (%s). Un mot leurre lu par un enfant "
                                + "est refuse : sur un jeu d'homophones, il lui ferait voir la forme "
                                + "fausse plus souvent que la juste (nadia-prof, arbitrage du "
                                + "2026-08-10, section 2)",
                                reponse.nom(), reponse.route(), textesDesPropositions)
                        .isSubsetOf(textesDesPropositions);
            }
        }
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun conteneur de propositions n'est "
                        + "rendu")
                .isNotEmpty();
    }
}
