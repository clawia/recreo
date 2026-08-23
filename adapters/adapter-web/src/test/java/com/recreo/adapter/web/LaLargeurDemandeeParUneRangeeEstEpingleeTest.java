package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesTierces;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.designes;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.etiquette;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.rangeesRepliables;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.sansPseudoClasses;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.termesDeclares;
import static com.recreo.adapter.web.ToutEcranEchangeEstUnDocumentJouableTest.zoneEchangee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0055, la moitie manquante d'ADR-0054. Le repli d'une rangee est l'issue d'une comparaison
 * entre <b>deux</b> grandeurs : la largeur que la chaine d'ancetres <b>offre</b>, et celle que les
 * colonnes et leurs ecarts <b>demandent</b>. Le tableau sur lequel {@code nadia-prof} appuie
 * {@code SYN-11} est un ecart entre les deux - "les cinq items debordent la rangee de 500 px de 14 a
 * 38 px" (2026-08-22). ADR-0054 a epingle le membre gauche par une egalite ; le membre droit n'etait
 * garde que par des <b>planchers</b>, c'est-a-dire d'un seul cote.
 *
 * <p><b>Ce qu'un plancher laisse passer, mesure et non suppose.</b> Deux planchers existent sur ce
 * membre droit, tous deux gagnes sur un refus pedagogique et tous deux inchanges par ce fichier :
 * l'ecart minimal de 1 rem entre deux boutons de reponse (ADR-0026,
 * {@code TailleDesTextesEtBoutonsTest.lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRemSurLesDeuxAxes})
 * et la taille de police minimale d'une proposition (2 rem, l'entaille de 1,875 rem sous le repli a
 * quatre colonnes etant la seule exception nommee). Un plancher interdit de <b>descendre</b>. Or ce
 * qui defait la mesure de {@code nadia-prof} est de <b>monter</b> : un ecart porte de 1 a 2 rem, une
 * police portee de 2 a 2,5 rem font passer des items d'une rangee a deux, et les deux planchers
 * restent verts. Et une troisieme famille n'avait aucun garde-fou d'aucun cote : le rembourrage
 * lateral, la bordure et la base flexible d'une colonne - c'est-a-dire exactement le levier le moins
 * cher pour faire tenir un item de plus sur une rangee.
 *
 * <p><b>C'est la meme faute que celle qu'ADR-0054 a corrigee, sur l'autre membre.</b> La-bas, un
 * cliquet a plancher serait reste vert sur le geste que {@code nadia-prof} designait comme risque.
 * Ici, deux planchers restent verts sur le geste inverse. Dans les deux cas ce que la mesure appuie
 * n'est pas une inegalite mais un couple (fonction, mesure), et l'egalite est la seule forme qui
 * rende un changement de la fonction visible dans les deux sens.
 *
 * <p><b>Ce qui est garde ici, et ce qui ne l'est pas.</b> La largeur qu'un mot occupe reellement
 * n'est pas une propriete de ce depot : elle depend d'une police que le depot ne contient pas, et
 * c'est la question que {@code nadia-prof} laisse ouverte (mesurer sur un appareil de la cible). Ce
 * fichier ne la tranche pas et ne pretend pas y repondre. Il epingle les <b>declarations</b> dont la
 * largeur demandee est la somme : les ecarts de la rangee, et pour chaque colonne les termes de sa
 * boite, sa base flexible et sa taille de police. Un vert ne dit pas que trois items tiennent sur une
 * rangee ; il dit que rien de ce que le depot declare a ce sujet n'a change depuis la mesure.
 *
 * <p><b>ADR-0013, les trois obligations.</b>
 * <ul>
 *   <li><b>Perimetre derive</b> : la population des rangees est celle d'ADR-0054, partagee et non
 *       recopiee ({@link LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest#rangeesRepliables}) -
 *       deux tables qui decriraient deux ensembles de rangees ne s'additionneraient pas, elles se
 *       contrediraient. Les colonnes sont les enfants de ces rangees dans le document assemble. Ni
 *       {@code propositions}, ni {@code proposition}, ni {@code btn} n'est ecrit comme predicat ici.
 *   <li><b>Derivation totale</b> : aucune rangee, une rangee sans colonne, aucune regle lue, un
 *       selecteur que le moteur de selection ne sait pas evaluer sont des <b>echecs</b>, jamais des
 *       cas ignores.</li>
 *   <li><b>Mise a l'epreuve</b> : cinq mutations du materiau reel - la feuille servie, le document
 *       que le serveur rend - dont trois reproduisent le vert silencieux exact que les deux planchers
 *       existants laissaient passer, <b>plus</b> un jeu fautif commite dont aucun nom ne coincide avec
 *       ceux de la production.</li>
 * </ul>
 *
 * <p><b>Ce que la table ne pinne volontairement pas : le nombre de colonnes.</b> Les lignes sont
 * dedupliquees par nom d'element. Trois propositions rendent trois boutons identiques, et le nombre
 * de propositions d'un ecran est une propriete du <b>contenu</b> - deux, trois ou quatre selon le
 * paquet - pas de la mise en page. L'epingler ici epinglerait le catalogue de test. Une colonne qui
 * porterait une classe de plus a un nom different, donc une ligne de plus : c'est la difference qui
 * compte qui est gardee, pas le cardinal.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LaLargeurDemandeeParUneRangeeEstEpingleeTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** Le jeu du catalogue de test dont une manche rend le cabinet et ses propositions. */
    private static final String JEU_ID = "un-jeu";

    /**
     * Les proprietes par lesquelles une declaration sur la rangee elle-meme change la largeur que ses
     * colonnes demandent : l'espace intercale entre deux colonnes voisines. {@code row-gap} n'y est
     * pas - il coute de la hauteur quand la rangee s'est deja repliee, il n'entre pas dans la
     * comparaison qui decide du repli.
     */
    private static final Set<String> TERMES_D_ECART = Set.of("gap", "column-gap");

    /**
     * Les proprietes par lesquelles une declaration sur une colonne change la largeur qu'elle demande.
     * Trois familles, et chacune est ici pour un geste realiste, pas par prudence : les termes de
     * boite (le rembourrage lateral d'un bouton est le levier le moins cher pour faire tenir un item de
     * plus), la base flexible (elle decide seule si une colonne accepte de partager une rangee), et la
     * taille de police (le seul terme du depot qui gouverne la largeur du texte lui-meme).
     */
    private static final Set<String> TERMES_DE_COLONNE = Set.of(
            "box-sizing",
            "width", "min-width", "max-width",
            "padding", "padding-left", "padding-right",
            "margin", "margin-left", "margin-right",
            "border", "border-width", "border-left", "border-right",
            "border-left-width", "border-right-width",
            "flex", "flex-basis", "flex-grow", "flex-shrink",
            "font-size");

    /**
     * La largeur demandee, epinglee : une ligne par (rangee, ecart declare) et une ligne par (rangee,
     * nom de colonne, regle qui declare un terme de colonne sur elle).
     *
     * <p>Les lignes de {@code form.propositions} sont celles dont {@code nadia-prof} a mesure la
     * consequence le 2026-08-22 : trois colonnes qui demandent 514 a 538 px la ou la rangee en offre
     * 500, sur cinq des seize items de {@code cm1-synonymes}. Celles de la rangee d'en-tete n'ont ete
     * mesurees par personne ; leur presence garde la meme comparaison contre un changement muet, elle
     * n'affirme rien sur ce qu'un enfant y voit.
     */
    private static final List<String> LARGEUR_DEMANDEE_EPINGLEE = List.of(
            "nav.container.entete-contenu (rangee) nav.container.entete-contenu | recreo.css | - | .entete-contenu | gap: 1rem",
            "nav.container.entete-contenu > img.icone-entete | bootstrap.min.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > img.icone-entete | recreo.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > img.icone-entete | recreo.css | - | .icone-entete | width: 2.75rem",
            "nav.container.entete-contenu > h1.nom-site | bootstrap.min.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > h1.nom-site | bootstrap.min.css | - | h1 | font-size: calc(1.375rem + 1.5vw)",
            "nav.container.entete-contenu > h1.nom-site | bootstrap.min.css | @media (min-width:1200px) | h1 | font-size: 2.5rem",
            "nav.container.entete-contenu > h1.nom-site | recreo.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > h1.nom-site | recreo.css | - | h1 | font-size: 2rem; margin: 0 0 1rem",
            "nav.container.entete-contenu > h1.nom-site | recreo.css | - | .nom-site | margin: 0; margin-right: auto",
            "nav.container.entete-contenu > a.btn.chip-niveau | bootstrap.min.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > a.btn.chip-niveau | bootstrap.min.css | - | .btn | padding: var(--bs-btn-padding-y) var(--bs-btn-padding-x); font-size: var(--bs-btn-font-size); border: var(--bs-btn-border-width) solid var(--bs-btn-border-color)",
            "nav.container.entete-contenu > a.btn.chip-niveau | recreo.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > a.btn.chip-niveau | recreo.css | - | .chip-niveau | font-size: 1.25rem; border: 0.125rem solid var(--recreo-accent-texte); padding: 0.5rem 0.9rem",
            "nav.container.entete-contenu > a.btn.chip-niveau | recreo.css | - | .chip-niveau:hover | border: 0.125rem solid var(--recreo-accent-texte)",
            "nav.container.entete-contenu > a.btn.chip-niveau | recreo.css | - | .entete-contenu .chip-niveau:active | border: 0.125rem solid var(--recreo-accent-texte)",
            "nav.container.entete-contenu > a.btn.chip-niveau | recreo.css | - | .chip-niveau:focus-visible | border: 0.125rem solid var(--recreo-accent-texte)",
            "nav.container.entete-contenu > a.btn.lien-accueil | bootstrap.min.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > a.btn.lien-accueil | bootstrap.min.css | - | .btn | padding: var(--bs-btn-padding-y) var(--bs-btn-padding-x); font-size: var(--bs-btn-font-size); border: var(--bs-btn-border-width) solid var(--bs-btn-border-color)",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | .entete-contenu .lien-accueil | margin-left: auto",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | .lien-accueil | font-size: 1.25rem; border: 0.125rem solid var(--recreo-accent-texte); padding: 0.6rem 1.1rem",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | .lien-accueil:hover | border: 0.125rem solid var(--recreo-accent-texte)",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | .entete-contenu .lien-accueil:active | border: 0.125rem solid var(--recreo-accent-texte)",
            "nav.container.entete-contenu > a.btn.lien-accueil | recreo.css | - | .lien-accueil:focus-visible | border: 0.125rem solid var(--recreo-accent-texte)",
            "form.propositions (rangee) form.propositions | recreo.css | - | .propositions | gap: 1rem",
            "form.propositions > button.btn.proposition | bootstrap.min.css | - | * | box-sizing: border-box",
            "form.propositions > button.btn.proposition | bootstrap.min.css | - | button | margin: 0; font-size: inherit",
            "form.propositions > button.btn.proposition | bootstrap.min.css | - | .btn | padding: var(--bs-btn-padding-y) var(--bs-btn-padding-x); font-size: var(--bs-btn-font-size); border: var(--bs-btn-border-width) solid var(--bs-btn-border-color)",
            "form.propositions > button.btn.proposition | recreo.css | - | * | box-sizing: border-box",
            "form.propositions > button.btn.proposition | recreo.css | - | .proposition | flex: 1 1 auto",
            "form.propositions > button.btn.proposition | recreo.css | - | .propositions:has(.proposition:nth-child(4)) .proposition | flex: 1 1 calc(50% - 0.5rem); font-size: 1.875rem",
            "form.propositions > button.btn.proposition | recreo.css | - | .proposition | min-width: 2.75rem; padding: 0.75rem 1.25rem",
            "form.propositions > button.btn.proposition | recreo.css | - | .proposition | font-size: 2rem");

    @Autowired
    private MockMvc mockMvc;

    @TempDir
    private Path tempo;

    private String corps(MockHttpServletRequestBuilder requete) throws Exception {
        return mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
    }

    /** Le document que l'enfant a sous les yeux sur une manche (ADR-0020, meme assemblage qu'ADR-0054). */
    private Document mancheAssemblee() throws Exception {
        String fragment = corps(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession()));
        Document coquille = Jsoup.parse(corps(get("/")));
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }

    /**
     * La largeur que chaque rangee repliable de ce document demande, telle que les declarations la
     * donnent : les ecarts de la rangee, puis les termes de chacune de ses colonnes.
     */
    static List<String> largeurDemandee(Document document, Path racine) {
        List<Regle> maison = reglesMaison(racine);
        List<Regle> tierces = reglesTierces(racine);
        assertThat(maison).as("aucune regle maison lue sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();
        assertThat(tierces).as("aucune regle tierce lue sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();
        List<Regle> toutes = Stream.concat(tierces.stream(), maison.stream()).toList();

        Set<String> lignes = new LinkedHashSet<>();
        for (Element rangee : rangeesRepliables(document, maison)) {
            for (Regle regle : toutes) {
                lignes.addAll(lignesDe(document, rangee, rangee, regle, TERMES_D_ECART));
            }
            List<Element> colonnes = rangee.children().stream().toList();
            assertThat(colonnes)
                    .as("la rangee repliable \"%s\" n'a aucune colonne : la largeur demandee serait "
                            + "nulle, et ce garde-fou ne prouverait rien sur elle", etiquette(rangee))
                    .isNotEmpty();
            for (Element colonne : colonnes) {
                for (Regle regle : toutes) {
                    lignes.addAll(lignesDe(document, rangee, colonne, regle, TERMES_DE_COLONNE));
                }
            }
        }
        return List.copyOf(lignes);
    }

    private static List<String> lignesDe(Document document, Element rangee, Element sujet, Regle regle,
            Set<String> proprietes) {
        List<String> termes = termesDeclares(regle.corps(), proprietes);
        if (termes.isEmpty() || regle.selecteur().contains("::")) {
            return List.of();
        }
        if (!designes(document, sansPseudoClasses(regle.selecteur())).contains(sujet)) {
            return List.of();
        }
        String position = sujet == rangee ? " (rangee) " : " > ";
        return List.of(etiquette(rangee) + position + etiquette(sujet) + " | "
                + regle.fichier() + " | "
                + (regle.contexte().isEmpty() ? "-" : regle.contexte()) + " | "
                + regle.selecteur() + " | " + String.join("; ", termes));
    }

    static void verifierQueLaLargeurDemandeeEstCelleEpinglee(Document document, Path racine,
            List<String> epinglee) {
        assertThat(largeurDemandee(document, racine))
                .as("la largeur que les colonnes d'une rangee demandent a change. Le repli est l'issue "
                        + "d'une comparaison entre cette largeur et celle qu'ADR-0054 epingle : toute "
                        + "mesure de nadia-prof faite contre l'ancienne est muette sur la nouvelle. "
                        + "ADR-0055 exige que cette table soit mise a jour dans le commit qui change "
                        + "la mise en page, et qu'une remesure soit demandee.")
                .containsExactlyElementsOf(epinglee);
    }

    @Test
    void laLargeurDemandeeDeLaProductionEstCelleEpinglee() throws Exception {
        verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), RACINE_PRODUCTION, LARGEUR_DEMANDEE_EPINGLEE);
    }

    private static Path copieDesFeuilles(Path destination) {
        try {
            Path source = RACINE_PRODUCTION;
            try (Stream<Path> arborescence = Files.walk(source)) {
                for (Path fichier : arborescence.toList()) {
                    Path cible = destination.resolve(source.relativize(fichier).toString());
                    if (Files.isDirectory(fichier)) {
                        Files.createDirectories(cible);
                    } else {
                        Files.createDirectories(cible.getParent());
                        Files.copy(fichier, cible, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            return destination;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * ADR-0057 : la mutation vit desormais en un seul endroit pour les trois garde-fous de ce module
     * qui mutent du materiau reel, et l'unicite d'ancrage decidee par ADR-0056 y est prouvee une
     * fois. Ce tour d'appel-ci - une racine et un chemin relatif - reste ici parce que les cinq
     * morsures de ce fichier travaillent sur des copies de l'arborescence servie.
     */
    private static void muter(Path racine, String chemin, String avant, String apres) {
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest
                .muterUneSeuleFois(racine.resolve(chemin), avant, apres);
    }


    @Test
    void unEcartElargiEntreDeuxColonnesFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("ecart-elargi"));
        muter(racine, "static/css/recreo.css",
                "    flex-wrap: wrap;\n    gap: 1rem;\n    margin: 0.75rem 0;",
                "    flex-wrap: wrap;\n    gap: 2rem;\n    margin: 0.75rem 0;");

        TailleDesTextesEtBoutonsTest.verifierEcartEntreBoutonsDeReponse(racine);

        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), racine, LARGEUR_DEMANDEE_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("gap: 2rem");
    }

    @Test
    void unRembourrageLateralRetireDUneColonneFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("colonne-degonflee"));
        muter(racine, "static/css/recreo.css", ".proposition {\n    flex: 1 1 auto;",
                ".proposition {\n    flex: 1 1 auto;\n    padding-left: 0;\n    padding-right: 0;");

        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), racine, LARGEUR_DEMANDEE_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("padding-left: 0");
    }

    @Test
    void uneBaseFlexibleChangeeSurUneColonneFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("base-flexible-changee"));
        muter(racine, "static/css/recreo.css", ".proposition {\n    flex: 1 1 auto;",
                ".proposition {\n    flex: 1 1 100%;");

        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), racine, LARGEUR_DEMANDEE_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("flex: 1 1 100%");
    }

    @Test
    void unRembourrageDeColonneChangeDansLaFeuilleTierceFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("rembourrage-tiers-change"));
        muter(racine, "static/vendor/bootstrap.min.css",
                "padding:var(--bs-btn-padding-y) var(--bs-btn-padding-x)",
                "padding:var(--bs-btn-padding-y) 0rem");

        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), racine, LARGEUR_DEMANDEE_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("0rem");
    }

    @Test
    void uneTailleDePoliceAgrandieSurUneColonneFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("police-agrandie"));
        muter(racine, "static/css/recreo.css", ".proposition {\n    flex: 1 1 auto;",
                ".proposition {\n    flex: 1 1 auto;\n    font-size: 2.5rem;");

        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                mancheAssemblee(), racine, LARGEUR_DEMANDEE_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("font-size: 2.5rem");
    }

    private static final Path JEUX_FAUTIFS =
            Path.of("src/test/resources/contreexemple", "LaLargeurDemandeeParUneRangeeEstEpingleeTest");

    private static final List<String> LARGEUR_DEMANDEE_DU_JEU_FAUTIF = List.of(
            "form.colonnes (rangee) form.colonnes | maison.css | - | .colonnes | gap: 0.75rem",
            "form.colonnes > button.case.touche | bibliotheque.css | - | * | box-sizing: border-box",
            "form.colonnes > button.case.touche | bibliotheque.css | - | .touche | padding: 0.25rem 0.5rem; border: 1px solid #333333",
            "form.colonnes > button.case.touche | maison.css | - | .case | flex: 1 1 auto; font-size: 1.5rem; padding: 0.5rem 1rem");

    private static Document pageDuJeuFautif(String jeu) {
        try {
            Path fichier = JEUX_FAUTIFS.resolve(jeu).resolve("templates/page.html");
            return Jsoup.parse(Files.readString(fichier), fichier.toString());
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    @Test
    void laDerivationTrouveLesColonnesSousDAutresNoms() {
        verifierQueLaLargeurDemandeeEstCelleEpinglee(pageDuJeuFautif("colonnes-renommees"),
                JEUX_FAUTIFS.resolve("colonnes-renommees"), LARGEUR_DEMANDEE_DU_JEU_FAUTIF);
    }

    @Test
    void uneColonneDegonfleeEstRougeSousDAutresNomsAussi() {
        assertThatThrownBy(() -> verifierQueLaLargeurDemandeeEstCelleEpinglee(
                pageDuJeuFautif("colonnes-renommees-degonflees"),
                JEUX_FAUTIFS.resolve("colonnes-renommees-degonflees"), LARGEUR_DEMANDEE_DU_JEU_FAUTIF))
                .isInstanceOf(AssertionError.class);
    }
}
