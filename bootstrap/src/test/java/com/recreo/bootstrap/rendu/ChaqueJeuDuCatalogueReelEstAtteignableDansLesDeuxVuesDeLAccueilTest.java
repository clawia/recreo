package com.recreo.bootstrap.rendu;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>HS-ACCUEIL-COMPLETUDE (DEC-0117, E2 du rapport qualite du chantier) : chaque jeu du catalogue reel est
 * atteignable depuis l'accueil de son niveau, dans V2 comme dans V1, sans qu'aucun test ne nomme un jeu.</b>
 *
 * <p>{@code AccueilParMatiereTest} (adapter-web) joue les routes sur un catalogue de test de quelques jeux : il
 * prouve la chaine de rendu, pas le materiau servi. Un jeu perdu a cause d'une donnee reelle (une matiere sans
 * ligne, un segment en double, une tuile sans etage 2) n'y rougirait pas. Ce test fait traverser a
 * <b>chaque niveau du catalogue reel</b> le contexte Spring entier, le chargeur reel, le controleur reel et
 * Thymeleaf, puis compare le document rendu au catalogue lu par le port entrant.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : niveaux, jeux et groupes viennent du port ({@code niveauxDuCatalogue},
 *       {@code jeuxDuNiveau}, {@code matieresDuNiveau}). Un jeu neuf, un niveau neuf, une douzieme matiere y
 *       entrent sans que ce fichier change ;</li>
 *   <li><b>derivation totale</b> : chaque niveau et chaque tuile sont joues, aucun n'est ecarte ; un catalogue
 *       sans niveau, un niveau sans jeu et une liste de reference vide font echouer le test au lieu de le rendre
 *       vert a vide ;</li>
 *   <li><b>mise a l'epreuve</b> : sur le document reel que le serveur vient de rendre, un jeu retire, un jeu
 *       double, un groupe retire, un bandeau sans mot, une tuile retiree et un etage 2 vide font chacun
 *       apparaitre un manquement.</li>
 * </ul>
 *
 * <p>Ce que ce test ne dit pas : l'aspect, le pli, le texte et les images de l'accueil ({@code yanis-qa} par
 * {@code outils/mesurer-accueil.py}, {@code nadia-prof}), et le comportement sur une session expiree.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest {

    private static final String PREFIXE_DE_JEU = "/parties/";

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MockMvc mockMvc;

    /** V2 n'est plus la vue par defaut (decision du 2026-10-12) : la session la choisit par la bascule. */
    private Document v2(String niveau) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/niveaux/{niveau}/vue/V2", niveau).session(session));
        return Jsoup.parse(mockMvc.perform(get("/niveaux/{niveau}", niveau).session(session))
                .andReturn().getResponse().getContentAsString());
    }

    private Document etage1(String niveau) throws Exception {
        return Jsoup.parse(mockMvc.perform(post("/niveaux/{niveau}/vue/V1", niveau).session(new MockHttpSession()))
                .andReturn().getResponse().getContentAsString());
    }

    private Document etage2(String adresse) {
        try {
            return Jsoup.parse(mockMvc.perform(get(adresse)).andReturn().getResponse().getContentAsString());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private List<String> jeuxAttendus(String niveau) {
        return partie.jeuxDuNiveau(niveau).stream().map(GameDefinition::id).toList();
    }

    private static List<String> identifiantsDesCartes(Document document) {
        return document.select("button.demarrer").eachAttr("hx-post").stream()
                .map(cible -> cible.startsWith(PREFIXE_DE_JEU) ? cible.substring(PREFIXE_DE_JEU.length()) : cible)
                .toList();
    }

    /** Ce qui manque, ce qui est en trop et ce qui est double entre les cartes rendues et le catalogue du niveau. */
    private static Set<String> ecartsDeCartes(List<String> rendus, List<String> attendus) {
        Set<String> manquements = new TreeSet<>();
        for (String attendu : attendus) {
            if (!rendus.contains(attendu)) {
                manquements.add("jeu inatteignable : " + attendu);
            }
        }
        Set<String> vus = new HashSet<>();
        for (String rendu : rendus) {
            if (!attendus.contains(rendu)) {
                manquements.add("jeu d'un autre niveau ou inconnu : " + rendu);
            } else if (!vus.add(rendu)) {
                manquements.add("jeu rendu deux fois : " + rendu);
            }
        }
        return manquements;
    }

    /** V2 : une page, un bandeau par groupe, toutes les cartes du niveau. */
    private static Set<String> manquementsDeV2(Document page, List<String> attendus, int groupesAttendus) {
        Set<String> manquements = new TreeSet<>(ecartsDeCartes(identifiantsDesCartes(page), attendus));
        List<Element> blocs = page.select(".bloc-matiere");
        if (blocs.size() != groupesAttendus) {
            manquements.add(blocs.size() + " groupes rendus pour " + groupesAttendus + " matieres du niveau");
        }
        for (Element bloc : blocs) {
            if (bloc.select("button.demarrer").isEmpty()) {
                manquements.add("groupe sans carte : " + bloc.select(".entete-matiere").text());
            }
            if (bloc.select(".entete-matiere span").text().isBlank()) {
                manquements.add("groupe dont le bandeau ne nomme pas la matiere en texte");
            }
        }
        return manquements;
    }

    /** V1 : une tuile par matiere, chaque tuile mene a un etage 2 non vide, l'union des etages 2 est le niveau. */
    private static Set<String> manquementsDeV1(Document etage1, Function<String, Document> etage2,
                                               List<String> attendus, int groupesAttendus) {
        Set<String> manquements = new TreeSet<>();
        List<Element> tuiles = etage1.select("a.tuile");
        if (tuiles.size() != groupesAttendus) {
            manquements.add(tuiles.size() + " tuiles rendues pour " + groupesAttendus + " matieres du niveau");
        }
        if (!etage1.select("button.demarrer").isEmpty()) {
            manquements.add("l'etage 1 lance un jeu : une tuile mene a l'etage 2, jamais a un jeu");
        }
        List<String> adresses = etage1.select("a.tuile").eachAttr("href");
        if (new HashSet<>(adresses).size() != adresses.size()) {
            manquements.add("deux tuiles menent a la meme adresse");
        }
        List<String> atteints = new ArrayList<>();
        for (String adresse : adresses) {
            List<String> cartes = identifiantsDesCartes(etage2.apply(adresse));
            if (cartes.isEmpty()) {
                manquements.add("tuile sans jeu : " + adresse);
            }
            atteints.addAll(cartes);
        }
        manquements.addAll(ecartsDeCartes(atteints, attendus));
        return manquements;
    }

    @Test
    void chaqueJeuDuNiveauParaitUneFoisEnV2DansUnGroupeNommeEtAucunGroupeNEstVide() throws Exception {
        List<String> niveaux = partie.niveauxDuCatalogue();
        assertThat(niveaux).as("ADR-0013 : cette clause ne prouve rien sur un catalogue sans niveau").isNotEmpty();

        for (String niveau : niveaux) {
            List<String> attendus = jeuxAttendus(niveau);
            assertThat(attendus).as("le niveau " + niveau + " sert au moins un jeu").isNotEmpty();

            assertThat(manquementsDeV2(v2(niveau), attendus, partie.matieresDuNiveau(niveau).size()))
                    .as("V2, niveau " + niveau).isEmpty();
        }
    }

    @Test
    void chaqueJeuDuNiveauEstAtteignableEnV1ParUneTuileEtUnEtageDeuxNonVide() throws Exception {
        List<String> niveaux = partie.niveauxDuCatalogue();
        assertThat(niveaux).as("ADR-0013 : cette clause ne prouve rien sur un catalogue sans niveau").isNotEmpty();

        for (String niveau : niveaux) {
            List<String> attendus = jeuxAttendus(niveau);
            assertThat(attendus).as("le niveau " + niveau + " sert au moins un jeu").isNotEmpty();

            assertThat(manquementsDeV1(etage1(niveau), this::etage2, attendus, partie.matieresDuNiveau(niveau).size()))
                    .as("V1, niveau " + niveau).isEmpty();
        }
    }

    @Test
    void chaqueJeuDuCatalogueAppartientAUnNiveauQueL_accueilPropose() {
        List<String> tous = partie.jeuxDisponibles().stream().map(GameDefinition::id).toList();
        List<String> parNiveau = new ArrayList<>();
        partie.niveauxDuCatalogue().forEach(niveau -> parNiveau.addAll(jeuxAttendus(niveau)));

        assertThat(tous).as("ADR-0013 : cette clause ne prouve rien sur un catalogue vide").isNotEmpty();
        assertThat(parNiveau).as("un jeu sans niveau, ou dans deux niveaux, n'est atteignable de nulle part")
                .containsExactlyInAnyOrderElementsOf(tous);
    }

    /** ADR-0013, mise a l'epreuve : le document reel, mute, doit faire apparaitre un manquement par defaut. */
    @Test
    void lesJugesMordentSurLeDocumentReelMute() throws Exception {
        String niveau = partie.niveauxDuCatalogue().get(0);
        List<String> attendus = jeuxAttendus(niveau);
        int groupes = partie.matieresDuNiveau(niveau).size();

        assertThat(manquementsDeV2(v2(niveau), attendus, groupes)).as("temoin V2 : le document reel est sain").isEmpty();

        Document unJeuRetire = v2(niveau);
        unJeuRetire.selectFirst("button.demarrer").parent().remove();
        assertThat(manquementsDeV2(unJeuRetire, attendus, groupes))
                .anyMatch(m -> m.startsWith("jeu inatteignable"));

        Document unJeuDouble = v2(niveau);
        Element carte = unJeuDouble.selectFirst("button.demarrer").parent();
        carte.after(carte.clone());
        assertThat(manquementsDeV2(unJeuDouble, attendus, groupes))
                .anyMatch(m -> m.startsWith("jeu rendu deux fois"));

        Document unGroupeRetire = v2(niveau);
        unGroupeRetire.selectFirst(".bloc-matiere").remove();
        assertThat(manquementsDeV2(unGroupeRetire, attendus, groupes))
                .anyMatch(m -> m.contains("groupes rendus pour"))
                .anyMatch(m -> m.startsWith("jeu inatteignable"));

        Document unBandeauSansMot = v2(niveau);
        unBandeauSansMot.selectFirst(".entete-matiere span").text("");
        assertThat(manquementsDeV2(unBandeauSansMot, attendus, groupes))
                .anyMatch(m -> m.contains("ne nomme pas la matiere"));

        assertThat(manquementsDeV1(etage1(niveau), this::etage2, attendus, groupes))
                .as("temoin V1 : l'etage 1 reel est sain").isEmpty();

        Document uneTuileRetiree = etage1(niveau);
        uneTuileRetiree.selectFirst("a.tuile").parent().remove();
        assertThat(manquementsDeV1(uneTuileRetiree, this::etage2, attendus, groupes))
                .anyMatch(m -> m.contains("tuiles rendues pour"))
                .anyMatch(m -> m.startsWith("jeu inatteignable"));

        assertThat(manquementsDeV1(etage1(niveau), adresse -> Jsoup.parse("<div></div>"), attendus, groupes))
                .anyMatch(m -> m.startsWith("tuile sans jeu"));
    }
}
