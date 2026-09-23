package com.recreo.bootstrap.rendu;

import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.TableauSupport;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0099, decision 3 : dans le document servi d'une manche, une grille est rendue par un
 * {@code <table>} si et seulement si l'item declare {@code tableauSupport} ; ses intitules de
 * colonnes sont des {@code <th scope="col">} qui portent exactement l'en-tete declare, et ses
 * lignes de corps portent exactement les cellules declarees, dans l'ordre.</b>
 *
 * <p>C'est la transposition de {@code LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest}
 * (ADR-0050, decision 6) a une composante structuree, et elle garde une propriete de plus : un
 * texte-support pouvait se verifier par son texte, une grille ne le peut pas. Un tableau dont
 * l'en-tete est rendu en {@code <td>} porte les memes caracteres et ne rattache plus aucune
 * cellule a sa colonne pour une lecture d'ecran ; c'est precisement la difference qui compte pour
 * l'enfant accompagne, et c'est elle que {@code scope} tient.</p>
 *
 * <p><b>Pourquoi ce module</b> : {@code bootstrap} est le seul qui voie a la fois le contenu et le
 * rendu web - meme motif d'emplacement que les quatre garde-fous voisins de ce paquet.</p>
 *
 * <p><b>Ce que ce garde-fou juge, avec sa population, dite plutot que supposee (ADR-0051).</b>
 * Deux cotes, et ils n'ont pas la meme population :</p>
 *
 * <ul>
 *   <li><b>"une grille apparait quand elle est declaree"</b> : un item porteur d'une grille,
 *       construit en memoire, joue a travers le controleur et le gabarit <b>reels</b>. Population
 *       au 2026-09-23 : <b>1 item synthetique, 0 item servi</b> - aucun paquet du repertoire de
 *       contenu ne declarait de grille le jour ou cette regle a ete ecrite. La propriete jugee est
 *       celle du <b>gabarit</b>, qui est le meme pour tout contenu ; un item de ma main y suffit,
 *       et c'est le seul endroit de ce fichier ou une matiere de ma main est admise ;</li>
 *   <li><b>"aucune grille n'apparait sans declaration"</b> : chaque entree du catalogue
 *       <b>reel</b>, decouverte et jamais nommee. Population : tous les jeux servis.</li>
 * </ul>
 *
 * <p>Le jour ou un paquet servi declarera {@code tableauSupport}, il entre dans le premier cote
 * <b>sans qu'une ligne soit ecrite ici</b> : les paquets marques sont decouverts en balayant le
 * repertoire de contenu reel, comme dans le garde-fou du texte-support, et chacun de leurs items
 * est joue.</p>
 *
 * <p><b>Mise a l'epreuve (ADR-0013 obligation 3)</b> : chaque {@code @Test} de mutation ci-dessous
 * mute le <b>document reellement rendu</b> par le gabarit de production, jamais une chaine ecrite
 * a la main.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.JeuxDeTestConfiguration.class)
class LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest {

    /** Prefixe des jeux de preuve construits pour un paquet servi qui declare une grille. */
    private static final String PREFIXE_JEU_DE_PREUVE = "preuve-tableau-support-";

    /** Le jeu de preuve synthetique, seul objet de ce fichier qui ne vienne pas du depot. */
    private static final String JEU_SYNTHETIQUE = "preuve-tableau-support-synthetique";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    /**
     * La grille du jeu synthetique. Trois colonnes et deux lignes : trois, parce que deux colonnes
     * ne distinguent pas un rattachement de cellule a sa colonne d'un simple appariement gauche a
     * droite ; deux lignes, parce qu'une seule ne distinguerait pas un corps d'un en-tete.
     */
    static TableauSupport grilleSynthetique() {
        return new TableauSupport(List.of("Animal", "Pattes", "Milieu"),
                List.of(List.of("le criquet", "six pattes", "la prairie"),
                        List.of("l'araignee", "huit pattes", "le buisson")));
    }

    private static Map<String, Object> lireYaml(String cheminClasspath) {
        try (InputStream flux = LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.class
                .getResourceAsStream(cheminClasspath)) {
            assertThat(flux).as("%s introuvable sur le classpath", cheminClasspath).isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return corps;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> entreesDuCatalogueReel() {
        return (List<Map<String, Object>>) lireYaml("/catalogue.yaml").get("jeux");
    }

    /** ADR-0013 obligation 1 : les paquets sont decouverts, jamais nommes. */
    private static List<String> paquetsDuRepertoireDeContenu() {
        try {
            Resource[] ressources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:contenu/*.yaml");
            List<String> noms = new ArrayList<>();
            for (Resource ressource : ressources) {
                String nomFichier = ressource.getFilename();
                if (nomFichier != null && nomFichier.endsWith(".yaml")) {
                    noms.add(nomFichier.substring(0, nomFichier.length() - ".yaml".length()));
                }
            }
            return noms;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * ADR-0099 : la conversion d'un item YAML, 'tableauSupport' compris. Identique a celle de
     * {@code QuestionContentSourceFactory} sur ce champ, et elle leve comme elle sur une forme
     * inattendue plutot que de rendre une grille vide.
     */
    @SuppressWarnings("unchecked")
    private static Question versQuestion(Map<String, Object> item, int position) {
        Object tableau = item.get("tableauSupport");
        Optional<TableauSupport> grille = Optional.empty();
        if (tableau instanceof Map<?, ?> champs) {
            List<String> enTete = ((List<Object>) champs.get("enTete")).stream()
                    .map(String::valueOf).toList();
            List<List<String>> lignes = new ArrayList<>();
            for (Object ligne : (List<Object>) champs.get("lignes")) {
                lignes.add(((List<Object>) ligne).stream().map(String::valueOf).toList());
            }
            grille = Optional.of(new TableauSupport(enTete, lignes));
        }
        return new Question(new ItemId("item-" + position), String.valueOf(item.get("enonce")),
                String.valueOf(item.get("bonneReponse")),
                ((List<Object>) item.get("distracteurs")).stream().map(String::valueOf).toList(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), grille);
    }

    private static ContentDescriptor descripteurDe(Map<String, Object> corps) {
        @SuppressWarnings("unchecked")
        Map<String, Object> descripteur = (Map<String, Object>) corps.get("descripteur");
        return new ContentDescriptor(String.valueOf(descripteur.get("matiere")),
                String.valueOf(descripteur.get("niveau")), String.valueOf(descripteur.get("domaine")),
                String.valueOf(descripteur.get("competence")));
    }

    private static Document ecran(MockMvc mockMvc, String jeuId, MockHttpSession session) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    /**
     * Les defauts d'un ecran de manche par rapport a la grille que l'item declare. Quatre
     * proprietes, et aucune n'est la presence seule : le nombre de tables, les intitules de
     * colonnes avec leur {@code scope}, le nombre de lignes de corps, et les cellules de chaque
     * ligne dans l'ordre.
     */
    static List<String> manquements(String jeuId, Question item, Document ecran) {
        List<String> defauts = new ArrayList<>();
        Elements grilles = ecran.select("table.tableau-support");
        String ou = "jeu " + jeuId + ", item \"" + item.enonce() + "\" : ";
        if (item.tableauSupport().isEmpty()) {
            if (!grilles.isEmpty()) {
                defauts.add(ou + "une grille apparait alors que cet item n'en declare aucune"
                        + " (ADR-0099, decision 3, seconde moitie)");
            }
            return defauts;
        }
        if (grilles.size() != 1) {
            defauts.add(ou + grilles.size() + " element(s) table.tableau-support, un seul attendu");
            return defauts;
        }
        TableauSupport attendu = item.tableauSupport().orElseThrow();
        Element grille = grilles.get(0);

        List<String> intitules = grille.select("thead th[scope=col]").stream()
                .map(cellule -> cellule.wholeText().strip()).toList();
        if (!attendu.enTete().equals(intitules)) {
            defauts.add(ou + "les intitules de colonnes rendus en th[scope=col] valent " + intitules
                    + ", attendu exactement " + attendu.enTete() + " - sans scope, aucune cellule"
                    + " n'est rattachee a sa colonne pour une lecture d'ecran");
        }
        Elements lignes = grille.select("tbody tr");
        if (lignes.size() != attendu.lignes().size()) {
            defauts.add(ou + lignes.size() + " ligne(s) de corps rendue(s) pour "
                    + attendu.lignes().size() + " declaree(s)");
            return defauts;
        }
        for (int rang = 0; rang < lignes.size(); rang++) {
            List<String> cellules = lignes.get(rang).select("td").stream()
                    .map(cellule -> cellule.wholeText().strip()).toList();
            if (!attendu.lignes().get(rang).equals(cellules)) {
                defauts.add(ou + "la ligne " + (rang + 1) + " rend " + cellules + ", attendu"
                        + " exactement " + attendu.lignes().get(rang));
            }
        }
        return defauts;
    }

    /**
     * Extrait pour que la clause de non-vacuite (ADR-0051) soit elle-meme mise a l'epreuve
     * ci-dessous, sans passer par Spring ni par MockMvc.
     */
    private static void verifierChaqueItemPorteurDUneGrille(MockMvc mockMvc, List<GameDefinition> jeux)
            throws Exception {
        assertThat(jeux)
                .as("ADR-0051 : ce garde-fou ne prouve rien s'il ne joue aucun item porteur d'une"
                        + " grille - le jeu synthetique le garantit tant qu'aucun paquet servi n'en"
                        + " declare, et il s'y ajoute des qu'un paquet en declare")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            List<Question> items = itemsDuJeuDePreuve(jeu);
            MockHttpSession session = new MockHttpSession();
            Document ecranCourant = ecran(mockMvc, jeu.id(), session);
            for (int rang = 0; rang < items.size(); rang++) {
                Question attendu = items.get(rang);
                defauts.addAll(manquements(jeu.id(), attendu, ecranCourant));
                if (rang < items.size() - 1) {
                    ecranCourant = coup(mockMvc, jeu.id(), session, attendu.bonneReponse());
                }
            }
        }

        assertThat(defauts)
                .as("ADR-0099, decision 3, premiere moitie : chaque item declarant une grille la voit"
                        + " atteindre l'ecran, en table, avec ses intitules de colonnes et ses"
                        + " cellules exactes")
                .isEmpty();
    }

    private static Document coup(MockMvc mockMvc, String jeuId, MockHttpSession session, String proposition)
            throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                        .param("propositionChoisie", proposition))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    private static List<Question> itemsDuJeuDePreuve(GameDefinition jeu) {
        if (JEU_SYNTHETIQUE.equals(jeu.id())) {
            return List.of(itemSynthetique());
        }
        String paquet = jeu.id().substring(PREFIXE_JEU_DE_PREUVE.length());
        return itemsDuCorps(lireYaml("/contenu/" + paquet + ".yaml"));
    }

    @SuppressWarnings("unchecked")
    private static List<Question> itemsDuCorps(Map<String, Object> corps) {
        List<Question> questions = new ArrayList<>();
        int position = 0;
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            questions.add(versQuestion(item, position));
            position++;
        }
        return questions;
    }

    static Question itemSynthetique() {
        return new Question(new ItemId("item-0"), "Combien de pattes a le criquet ?", "six pattes",
                List.of("huit pattes", "quatre pattes"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of(grilleSynthetique()));
    }

    private List<GameDefinition> jeuxPorteursDUneGrille() {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList();
    }

    @Test
    void chaqueItemPorteurDUneGrilleLaVoitAtteindreLEcranEnTable() throws Exception {
        verifierChaqueItemPorteurDUneGrille(mockMvc, jeuxPorteursDUneGrille());
    }

    /**
     * <b>Amendement du 2026-09-23, premier paquet a grille servi (`S40-EXTRACTION`).</b> Cette
     * clause comparait l'ecran a <b>rien</b> : elle refusait toute grille sur tout jeu du catalogue
     * reel, au motif qu'aucun n'en declarait. La promesse d'ADR-0099 est *si et seulement si*, et
     * la moitie *"seulement si"* etait ecrite comme un *"jamais"* - vrai tant que la population
     * etait vide, faux le jour ou elle cesse de l'etre, c'est-a-dire le jour ou cette clause sert
     * enfin a quelque chose.
     *
     * <p>Elle compare desormais l'ecran a <b>l'item que ce jeu sert</b>, par le meme
     * {@link #manquements} que l'autre cote : une grille rendue sans declaration reste rouge, une
     * grille rendue conformement a sa declaration est verte, et le premier paquet a grille du depot
     * est juge au lieu d'etre refuse. Le tirage est deterministe ({@code RandomSource} de test a 0,
     * {@code ListContentSource} rend alors le premier item non vu), donc l'item attendu est celui
     * de rang 0 du paquet - meme derivation que le garde-fou du texte-support.</p>
     */
    @Test
    void aucunJeuDuCatalogueReelNAfficheDeGrilleSansDeclaration() throws Exception {
        List<GameDefinition> jeuxReels = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .toList();
        assertThat(jeuxReels)
                .as("ADR-0051 : ce cote ne prouve rien si le catalogue reel ne sert aucun jeu")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxReels) {
            Document ecranDeManche = ecran(mockMvc, jeu.id(), new MockHttpSession());
            Question premierItem = itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeu) + ".yaml")).get(0);
            defauts.addAll(manquements(jeu.id(), premierItem, ecranDeManche));
        }
        assertThat(defauts)
                .as("ADR-0099, decision 3 : sur un jeu du catalogue reel, une grille atteint l'ecran"
                        + " si et seulement si l'item servi en declare une, avec ses intitules et ses"
                        + " cellules exacts")
                .isEmpty();
    }

    /** Le nom du paquet reel derriere une entree du catalogue, lu sur l'entree elle-meme. */
    private static String paquetDe(GameDefinition jeu) {
        return entreesDuCatalogueReel().stream()
                .filter(entree -> jeu.id().equals(String.valueOf(entree.get("id"))))
                .map(entree -> String.valueOf(entree.get("paquet")))
                .findFirst()
                .orElseThrow();
    }

    /** ADR-0051 : la clause de non-vacuite mord, elle aussi. */
    @Test
    void aucunJeuPorteurDUneGrilleFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierChaqueItemPorteurDUneGrille(mockMvc, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve 1 : la grille retiree du document rendu. Sans elle,
     * rien ne prouve que la cle YAML atteint l'ecran plutot que d'etre lue en silence.
     */
    @Test
    void uneGrilleRetireeDuDocumentFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = jeuxPorteursDUneGrille().get(0);
        Question item = itemsDuJeuDePreuve(jeu).get(0);
        Document ecranReel = ecran(mockMvc, jeu.id(), new MockHttpSession());
        assertThat(manquements(jeu.id(), item, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation").isEmpty();

        Document mute = ecranReel.clone();
        mute.select("table.tableau-support").remove();
        assertThat(manquements(jeu.id(), item, mute)).isNotEmpty();
    }

    /**
     * Mise a l'epreuve 2 : les intitules de colonnes rendus en {@code <td>} au lieu de
     * {@code <th scope="col">}. <b>C'est la mutation qui compte le plus dans ce fichier</b> : le
     * document porte alors exactement les memes caracteres, un oeil n'y voit qu'un tableau un peu
     * moins gras, et plus aucune cellule n'est rattachee a sa colonne pour une lecture d'ecran. Une
     * assertion sur le texte rendu resterait verte ici.
     */
    @Test
    void desIntitulesDeColonnesSansScopeFontEchouerLaClause() throws Exception {
        GameDefinition jeu = jeuxPorteursDUneGrille().get(0);
        Question item = itemsDuJeuDePreuve(jeu).get(0);
        Document ecranReel = ecran(mockMvc, jeu.id(), new MockHttpSession());
        assertThat(manquements(jeu.id(), item, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation").isEmpty();

        Document mute = ecranReel.clone();
        mute.select("thead th").forEach(cellule -> {
            cellule.tagName("td");
            cellule.removeAttr("scope");
        });
        assertThat(manquements(jeu.id(), item, mute)).isNotEmpty();
    }

    /** Mise a l'epreuve 3 : une cellule alteree - la presence seule ne suffit pas. */
    @Test
    void uneCelluleQuiDiffereDeLaValeurDeclareeFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = jeuxPorteursDUneGrille().get(0);
        Question item = itemsDuJeuDePreuve(jeu).get(0);
        Document ecranReel = ecran(mockMvc, jeu.id(), new MockHttpSession());

        Document mute = ecranReel.clone();
        Element cellule = mute.selectFirst("table.tableau-support tbody td");
        cellule.text(cellule.text() + " altere");
        assertThat(manquements(jeu.id(), item, mute)).isNotEmpty();
    }

    /** Mise a l'epreuve 4 : une ligne de corps en trop - le compte de lignes est juge. */
    @Test
    void uneLigneDeCorpsEnTropFaitEchouerLaClause() throws Exception {
        GameDefinition jeu = jeuxPorteursDUneGrille().get(0);
        Question item = itemsDuJeuDePreuve(jeu).get(0);
        Document ecranReel = ecran(mockMvc, jeu.id(), new MockHttpSession());

        Document mute = ecranReel.clone();
        Element ligne = mute.selectFirst("table.tableau-support tbody tr");
        ligne.after(ligne.clone());
        assertThat(manquements(jeu.id(), item, mute)).isNotEmpty();
    }

    /**
     * Mise a l'epreuve 5 : une grille posee sur un item qui n'en declare aucune - l'ecart muet
     * symetrique, celui d'un gabarit qui rendrait une table sans donnee.
     */
    @Test
    void uneGrillePoseeSurUnItemQuiNEnDeclareAucuneFaitEchouerLaClause() throws Exception {
        // Un jeu reel dont l'item servi ne declare aucune grille - decouvert et non suppose : depuis
        // S40-EXTRACTION, le catalogue en sert un qui en declare une, et le premier venu ne convient
        // plus.
        GameDefinition jeuReel = partie.jeuxDisponibles().stream()
                .filter(jeu -> !jeu.id().startsWith(PREFIXE_JEU_DE_PREUVE))
                .filter(jeu -> itemsDuCorps(lireYaml("/contenu/" + paquetDe(jeu) + ".yaml")).get(0)
                        .tableauSupport().isEmpty())
                .findFirst().orElseThrow();
        Question sansGrille = new Question(new ItemId("item-0"), "enonce quelconque", "juste",
                List.of("faux"));
        Document ecranReel = ecran(mockMvc, jeuReel.id(), new MockHttpSession());
        assertThat(manquements(jeuReel.id(), sansGrille, ecranReel))
                .as("l'ecran reel doit etre sain avant mutation").isEmpty();

        Document mute = ecranReel.clone();
        mute.selectFirst(".consigne").after("<table class=\"tableau-support\"><tbody><tr>"
                + "<td>intrus</td></tr></tbody></table>");
        assertThat(manquements(jeuReel.id(), sansGrille, mute)).isNotEmpty();
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            List<GameDefinition> jeux = new ArrayList<>();

            for (Map<String, Object> entree : entreesDuCatalogueReel()) {
                Map<String, Object> corps = lireYaml("/contenu/" + entree.get("paquet") + ".yaml");
                if (!"question".equals(String.valueOf(corps.get("type")))) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(String.valueOf(entree.get("id")),
                        String.valueOf(entree.get("titre")), String.valueOf(entree.get("consigne")),
                        BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, itemsDuCorps(corps)), 1));
            }

            // ADR-0013 obligation 1 : les paquets servis porteurs d'une grille entrent d'eux-memes
            // le jour ou ils existent, sans qu'un nom soit ecrit ici.
            for (String paquet : paquetsDuRepertoireDeContenu()) {
                Map<String, Object> corps = lireYaml("/contenu/" + paquet + ".yaml");
                if (!"question".equals(String.valueOf(corps.get("type")))) {
                    continue;
                }
                List<Question> items = itemsDuCorps(corps);
                if (items.stream().noneMatch(item -> item.tableauSupport().isPresent())) {
                    continue;
                }
                ContentDescriptor descripteur = descripteurDe(corps);
                jeux.add(new GameDefinition(PREFIXE_JEU_DE_PREUVE + paquet, "Preuve " + paquet,
                        "Consigne de preuve", BanditManchot.ID, descripteur,
                        new ListContentSource(descripteur, items), items.size()));
            }

            ContentDescriptor descripteur = new ContentDescriptor("Sciences et technologie", "CM1",
                    "Le vivant", "Extraire une information d'un document");
            jeux.add(new GameDefinition(JEU_SYNTHETIQUE, "Preuve synthetique", "Consigne de preuve",
                    BanditManchot.ID, descripteur,
                    new ListContentSource(descripteur, List.of(itemSynthetique())), 1));

            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
