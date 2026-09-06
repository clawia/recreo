package com.recreo.adapter.web;

import com.recreo.domain.mechanic.Score;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * ADR-0075 section 7, VOI-12 (nadia-prof, {@code ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md},
 * section 13) : ce que {@code fragments/manche.html} et {@code fragments/saisie.html} doivent
 * rendre pour que {@code .bloc-adulte} soit possible.
 *
 * <p><b>Ce que ce fichier n'a pas le droit de pretendre couvrir, et pourquoi un controleur de
 * test.</b> Au commit d'origine de ce fichier, {@code texteAVoixHaute} n'existait sur aucun type du
 * domaine (ADR-0075 le posait comme un champ d'item, dans un lot distinct de celui-ci, dont le
 * perimetre etait le gabarit et la feuille de style). Le controleur ci-dessous est un double de
 * test, propre a ce fichier : il rend les deux memes fragments que la production, avec le meme
 * moteur Thymeleaf (autoconfigure par {@code @WebMvcTest}). Il ne prouve rien du contenu d'un
 * paquet (VOI-12 points 3, 4 et 5) : nadia-prof le nomme elle-meme comme sa propre garde
 * (section 13.4), et ce fichier ne pretend pas la remplacer.</p>
 *
 * <p><b>Amendement (marc-dev) : le trou trouve par yanis-qa en mutant ce meme controleur.</b>
 * {@code fragments/manche.html} portait deux {@code th:if} independants sur
 * {@code texteAVoixHaute} et {@code consigneAdulte} ; rendu avec le premier seul, le gabarit
 * produisait un {@code .bloc-adulte} incomplet - le stimulus sans la mise en garde que VOI-12 exige
 * toujours attachee. Seul un garde-fou cote contenu ({@code
 * LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest}, qui garde un fichier YAML) empechait
 * cette combinaison, jamais le gabarit ni le controleur. {@link BlocAdulte} ferme ce chemin au
 * niveau du modele : {@code PartieController} et le controleur de test ci-dessous ne posent plus
 * qu'un seul attribut, {@code blocAdulte}, construit par {@link BlocAdulte#creerOuNull} qui leve
 * plutot que de rendre l'un sans l'autre. {@link #unTexteAVoixHauteSansConsigneAdulteNAtteintJamaisLEcranEnEtatIncomplet()}
 * est le test rouge de {@code yanis-qa} (branche {@code s26-yanis}, commit {@code 77e2385}), repris
 * tel quel.</p>
 */
@WebMvcTest(LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest.ControleurDeGabaritDeTest.class)
@Import(LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest.ControleurDeGabaritDeTest.class)
class LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest {

    /**
     * Rend les deux fragments reels avec un modele construit a la main, sur le patron exact des
     * attributs que {@code PartieController} pose deja (mancheDeBanditManchot, mancheDeSaisie),
     * {@code blocAdulte} compris - meme construction par {@link BlocAdulte#creerOuNull}, pour que
     * ce double de test retombe dans la meme erreur que la production si les deux parametres HTTP
     * ne sont pas fournis ensemble. Ne vit que dans ce fichier de test : aucune route de production
     * n'est ajoutee, aucun type de domaine n'est touche.
     */
    @Controller
    static class ControleurDeGabaritDeTest {

        @GetMapping("/test-voix/manche")
        String manche(@RequestParam(required = false) String texteAVoixHaute,
                      @RequestParam(required = false) String consigneAdulte,
                      @RequestParam(required = false) String message,
                      Model modele) {
            modele.addAttribute("jeuId", "jeu-test");
            modele.addAttribute("consigne", "Choisis la bonne reponse.");
            modele.addAttribute("texteSupport", null);
            modele.addAttribute("illustration", null);
            modele.addAttribute("illustrationTexteAlternatif", null);
            modele.addAttribute("segmentsEnonce", List.of(new EnonceSegment("Un enonce.", false)));
            modele.addAttribute("propositions", List.of("un", "deux", "trois"));
            modele.addAttribute("score", new Score(0, 0));
            modele.addAttribute("indice", null);
            modele.addAttribute("arriveeParUnGeste", false);
            modele.addAttribute("propositionsEssayees", Set.of());
            modele.addAttribute("message", message);
            modele.addAttribute("signalJuste", false);
            modele.addAttribute("explication", null);
            modele.addAttribute("focus", "enonce");
            modele.addAttribute("focusCibles", Set.of());
            modele.addAttribute("blocAdulte", BlocAdulte.creerOuNull(texteAVoixHaute, consigneAdulte));
            return "fragments/manche :: manche";
        }

        @GetMapping("/test-voix/saisie")
        String saisie(@RequestParam(required = false) String texteAVoixHaute,
                      @RequestParam(required = false) String consigneAdulte,
                      Model modele) {
            modele.addAttribute("jeuId", "jeu-test");
            modele.addAttribute("consigne", "Écris le mot que tu entends.");
            modele.addAttribute("enonce", "velo");
            modele.addAttribute("score", new Score(0, 0));
            modele.addAttribute("tentatives", List.of());
            modele.addAttribute("mancheResolue", false);
            modele.addAttribute("brouillon", null);
            modele.addAttribute("message", null);
            modele.addAttribute("signalJuste", false);
            modele.addAttribute("chaineJugee", null);
            modele.addAttribute("formeDeReference", null);
            modele.addAttribute("explication", null);
            modele.addAttribute("indice", null);
            modele.addAttribute("focus", "enonce");
            modele.addAttribute("focusSurLeChamp", false);
            modele.addAttribute("focusSurLaSuite", false);
            modele.addAttribute("blocAdulte", BlocAdulte.creerOuNull(texteAVoixHaute, consigneAdulte));
            return "fragments/saisie :: saisie";
        }
    }

    @Autowired
    private MockMvc mockMvc;

    private Document rendre(String chemin, String... parametres) throws Exception {
        var requete = get(chemin);
        for (int i = 0; i + 1 < parametres.length; i += 2) {
            requete = requete.param(parametres[i], parametres[i + 1]);
        }
        return Jsoup.parse(mockMvc.perform(requete).andReturn().getResponse().getContentAsString());
    }

    /**
     * Regression : les 27 paquets servis aujourd'hui ne portent pas {@code texteAVoixHaute}. Le
     * gabarit doit rendre exactement comme avant - aucune exception sur une variable de modele
     * absente, et aucun bloc vide (ADR-0020) - tant que ce champ n'existe pas.
     */
    @Test
    void sansTexteAVoixHauteAucunBlocAdulteNApparaitSurAucunFragment() throws Exception {
        assertThat(rendre("/test-voix/manche").select(".bloc-adulte")).isEmpty();
        assertThat(rendre("/test-voix/saisie").select(".bloc-adulte")).isEmpty();
    }

    /**
     * VOI-12 point 1, "presence sans geste" : le stimulus et la mise en garde sont dans le meme
     * objet visuel, sans qu'aucune interaction ne soit necessaire pour l'atteindre.
     */
    @Test
    void leBlocAdulteEstUnSeulObjetSansElementInteractif() throws Exception {
        Document ecran = rendre("/test-voix/manche",
                "texteAVoixHaute", "château",
                "consigneAdulte", "Dites le mot normalement, sans le couper.");

        Elements blocs = ecran.select(".bloc-adulte");
        assertThat(blocs).hasSize(1);
        Element bloc = blocs.first();

        assertThat(bloc.select(".etiquette-adulte").text()).isEqualTo("Adulte, à voix haute :");
        assertThat(bloc.select(".stimulus-a-lire").text()).isEqualTo("château");
        assertThat(bloc.select(".consigne-adulte").text())
                .isEqualTo("Dites le mot normalement, sans le couper.");

        assertThat(bloc.select("a, button, input, details, summary, [tabindex]:not(.bloc-adulte)"))
                .as("VOI-12 point 1 : rien dans ce bloc ne doit demander un geste pour etre atteint")
                .isEmpty();
    }

    /**
     * VOI-12 point 1, "immediatement contigue" : sur l'ecran a choix, {@code .bloc-adulte} precede
     * {@code .consigne} sans qu'aucun element destine a l'enfant ne s'intercale.
     */
    @Test
    void leBlocAdultePrecedeImmediatementLaConsigneSurLEcranAChoix() throws Exception {
        Document ecran = rendre("/test-voix/manche",
                "texteAVoixHaute", "chocolat",
                "consigneAdulte", "Dites le mot normalement, sans appuyer sur aucun son.");

        Element ecranDeJeu = ecran.selectFirst(".ecran-de-jeu");
        List<Element> enfants = ecranDeJeu.children();
        int indexBloc = enfants.indexOf(ecran.selectFirst(".bloc-adulte"));
        int indexConsigne = enfants.indexOf(ecran.selectFirst(".consigne"));

        assertThat(indexBloc).isGreaterThanOrEqualTo(0);
        assertThat(indexConsigne).isGreaterThan(indexBloc);
        assertThat(indexConsigne - indexBloc)
                .as("aucun element ne s'intercale entre .bloc-adulte et .consigne")
                .isEqualTo(1);
    }

    /**
     * Meme clause que ci-dessus, sur l'ecran de saisie (ADR-0075 section 5 : le champ vit sur les
     * deux formes d'item).
     */
    @Test
    void leBlocAdultePrecedeImmediatementLaConsigneSurLEcranDeSaisie() throws Exception {
        Document ecran = rendre("/test-voix/saisie",
                "texteAVoixHaute", "vélo",
                "consigneAdulte",
                "Redites le mot autant de fois qu'il le demande. Ne l'épelez pas.");

        Element ecranDeJeu = ecran.selectFirst(".ecran-de-jeu");
        List<Element> enfants = ecranDeJeu.children();
        int indexBloc = enfants.indexOf(ecran.selectFirst(".bloc-adulte"));
        int indexConsigne = enfants.indexOf(ecran.selectFirst("label.consigne"));

        assertThat(indexBloc).isGreaterThanOrEqualTo(0);
        assertThat(indexConsigne).isGreaterThan(indexBloc);
        assertThat(indexConsigne - indexBloc)
                .as("aucun element ne s'intercale entre .bloc-adulte et le label de consigne")
                .isEqualTo(1);
    }

    /**
     * VOI-12 point 1, "chaque ecran ou un texte destine a l'adulte est affiche - item, erreur, et
     * revelation" : theo-archi mesure que la revelation vit sur {@code manche.html} dans un autre
     * etat de {@code .message-jeu}, jamais sur {@code retour.html} (ADR-0075, "le motif technique
     * de sa branche mecanique"). Ce test verifie que {@code .bloc-adulte} ne disparait pas quand
     * {@code .message-jeu} apparait, sur les trois etats que ce fragment peut prendre.
     */
    @Test
    void leBlocAdulteResteAffichePendantLerreurEtLaRevelation() throws Exception {
        String stimulus = "chat";
        String consigneAdulte = "Dites le mot entier, jamais la lettre seule.";

        Document itemFrais = rendre("/test-voix/manche",
                "texteAVoixHaute", stimulus, "consigneAdulte", consigneAdulte);
        Document echec = rendre("/test-voix/manche",
                "texteAVoixHaute", stimulus, "consigneAdulte", consigneAdulte,
                "message", "Ce n'est pas ça. Essaie encore.");
        Document revelation = rendre("/test-voix/manche",
                "texteAVoixHaute", stimulus, "consigneAdulte", consigneAdulte,
                "message", "Ce n'est pas ça.", "reponseRevelee", "ch");

        for (Document ecran : List.of(itemFrais, echec, revelation)) {
            assertThat(ecran.select(".bloc-adulte")).hasSize(1);
            assertThat(ecran.select(".bloc-adulte .stimulus-a-lire").text()).isEqualTo(stimulus);
        }
        assertThat(echec.select(".message-jeu")).hasSize(1);
        assertThat(revelation.select(".message-jeu")).hasSize(1);
    }

    /**
     * ADR-0074 : aucun gabarit ne porte de JavaScript autrement que par reference. {@code
     * .bloc-adulte} n'introduit ni {@code <script>} en ligne, ni gestionnaire d'evenement, ni URL
     * {@code javascript:} - verifie ici sur le rendu reel plutot que suppose de la lecture du
     * gabarit, sur le meme materiau que {@code FrontieresDuJavaScriptTest}.
     */
    @Test
    void leBlocAdulteNePorteAucunJavaScript() throws Exception {
        Document ecran = rendre("/test-voix/manche",
                "texteAVoixHaute", "château", "consigneAdulte", "Dites le mot normalement.");
        Element bloc = ecran.selectFirst(".bloc-adulte");

        assertThat(bloc.select("script")).isEmpty();
        for (var attribut : bloc.attributes()) {
            assertThat(attribut.getKey().toLowerCase(java.util.Locale.ROOT))
                    .as("attribut %s sur .bloc-adulte", attribut.getKey())
                    .doesNotMatch("^(on[a-z]+|hx-on.*)$");
        }
    }

    /**
     * <b>yanis-qa, s26, obstacle qualite - la clause 4 de traversee ({@code
     * LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest}, ADR-0075) prouve que le bloc-adulte
     * atteint l'ecran sur le catalogue reel d'aujourd'hui ; elle ne prouve pas qu'il ne peut pas
     * l'atteindre a moitie.</b>
     *
     * <p>{@code fragments/manche.html} porte deux {@code th:if} independants sur
     * {@code .bloc-adulte} : {@code texteAVoixHaute != null} sur le conteneur,
     * {@code consigneAdulte != null} sur la mise en garde qu'il contient. Rien au niveau du
     * gabarit ou du controleur (qui ne fait que transmettre les deux valeurs, cf. le commentaire
     * de {@code PartieController.mancheDeBanditManchot}) n'empeche l'un sans l'autre : c'est
     * uniquement {@code LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest}, cote contenu,
     * qui garantit aujourd'hui que cette combinaison ne se produit jamais sur
     * {@code catalogue.yaml}. Mute directement au niveau du modele, sur le meme patron que
     * {@code leBlocAdultePrecedeImmediatementLaConsigneSurLEcranAChoix} ci-dessus (sans passer par
     * le garde-fou de contenu, qui n'est pas dans le perimetre de ce gabarit) : le gabarit rend un
     * {@code .bloc-adulte} incomplet - le stimulus, sans la mise en garde que VOI-12 exige d'y
     * trouver toujours attachee.</p>
     *
     * <p><b>Rouge a ce commit</b> : ce test documente un chemin non ferme au niveau du gabarit /
     * controleur, qui ne tient que par un garde-fou pose ailleurs, sur un objet different (un
     * fichier YAML plutot qu'un document rendu). Route a sofia-sm : la fermeture (fusionner les
     * deux conditions en une seule, ou faire echouer tot si l'un est present sans l'autre) est un
     * choix de gabarit ou de controleur qui n'est pas a moi de trancher.</p>
     *
     * <p><b>Vert depuis {@link BlocAdulte}</b> (marc-dev) : le controleur de test construit
     * desormais {@code blocAdulte} par {@link BlocAdulte#creerOuNull}, qui leve une
     * {@code IllegalStateException} des que le stimulus est fourni sans la mise en garde -
     * {@link GestionnaireDesErreurs} l'attrape et rend l'ecran d'erreur generique (ADR-0011,
     * ADR-0018). Le document rendu ne porte alors aucun {@code .bloc-adulte} du tout : la garde
     * {@code if (!blocs.isEmpty())} ci-dessous ne s'execute jamais dans ce cas, et c'est exactement
     * l'etat incomplet que VOI-12 interdit qui a disparu, pas seulement l'assertion qui le
     * detectait.</p>
     */
    @Test
    void unTexteAVoixHauteSansConsigneAdulteNAtteintJamaisLEcranEnEtatIncomplet() throws Exception {
        Document ecran = rendre("/test-voix/manche", "texteAVoixHaute", "chat");

        Elements blocs = ecran.select(".bloc-adulte");
        if (!blocs.isEmpty()) {
            assertThat(blocs.first().select(".consigne-adulte").text())
                    .as("VOI-12 : un .bloc-adulte qui porte un stimulus doit toujours porter, dans"
                            + " le meme geste, la mise en garde de son jeu - un stimulus seul est"
                            + " exactement l'etat incomplet que VOI-12 interdit")
                    .isNotBlank();
        }
    }
}
