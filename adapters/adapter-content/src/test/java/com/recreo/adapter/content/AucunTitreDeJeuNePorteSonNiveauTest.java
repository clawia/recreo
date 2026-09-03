package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0078 : aucun titre de jeu ne porte le niveau du paquet qu'il sert.
 *
 * <p><b>Le fait, et il n'est pas de moi.</b> L'utilisatrice a joue le produit et releve que
 * <i>"Calcul CP"</i> etait le seul des quarante titres a nommer une classe. nadia-prof l'a tranche
 * le 2026-09-02 : <i>"un enfant n'a pas besoin qu'on lui rappelle sa classe pour jouer"</i>, et elle
 * a compte elle-meme les trente-neuf autres. <b>La decision est pedagogique et elle est prise ;
 * seul son encodage est ici.</b> Ce que ce garde-fou ne dit pas, et ne dira jamais : si un titre est
 * bon. Le registre, la longueur, le vocabulaire, la paire que deux titres forment pour un enfant qui
 * parcourt une liste - tout cela est a nadia-prof et a camille-po.
 *
 * <p><b>Pourquoi une regle pour un seul cas.</b> Le defaut a survecu <b>26 sprints</b> et n'a ete
 * trouve ni par un test ni par un agent, mais par l'utilisatrice devant l'ecran. Rien dans le schema
 * d'une entree de catalogue ne contraint {@code titre} : le prochain jeu peut porter la meme faute
 * le jour ou il entre, et le seul filet est qu'un relecteur y pense. <b>Ce n'est pas une abstraction
 * ecrite pour un usage imagine : le catalogue grandit a chaque sprint, et c'est l'ecriture d'une
 * entree neuve que cette regle garde</b>, pas la correction d'aujourd'hui.
 *
 * <p><b>Le perimetre et le critere sont derives tous les deux (ADR-0013).</b> La population est
 * l'ensemble des entrees du catalogue servi. Le niveau n'est <b>pas ecrit ici</b> : il est lu dans le
 * descripteur du paquet que l'entree designe, et les paquets sont indexes par l'identifiant qu'ils
 * declarent plutot que par leur nom de fichier. Un niveau neuf - un CE1, un CM2 - est donc garde le
 * jour ou il entre, sans qu'une ligne de ce fichier bouge. C'est ce qui separe cette regle de
 * l'exemption enumeree de {@code TexteAccentueExposeParLePortEntrantTest}, qui ecrit <i>"CP"</i> et
 * <i>"CM1"</i> en clair et le declare.
 *
 * <p><b>Un paquet qui ne declarerait aucun niveau est rouge</b>, et ce n'est pas une severite
 * gratuite : c'est le seul moyen que la regle ne devienne pas verte sur tout le catalogue le jour ou
 * la cle du descripteur est renommee. Un garde-fou qui ne trouve plus ce qu'il compare doit le dire,
 * jamais l'ignorer.
 *
 * <p><b>Ce que ce garde-fou ne voit pas, ecrit plutot que laisse trouver.</b> Il compare une chaine
 * a une chaine : un titre qui ecrirait le niveau autrement que le descripteur - <i>"cours
 * preparatoire"</i>, <i>"niveau 1"</i> - passerait. C'est la forme sous laquelle le defaut s'est
 * produit qui est gardee, et c'est la seule forme que le materiau offre ; l'autre serait un
 * vocabulaire ecrit a la main, donc la chose meme que cette Javadoc reproche a l'exemption voisine.
 *
 * <p><b>Cette regle n'a aucune exemption, et elle en a eu une pendant un commit.</b> Le fait est
 * ecrit parce qu'une exemption qui disparait sans laisser de trace laisse croire qu'il n'y en a
 * jamais eu, et parce que la facon dont celle-ci est morte est la seule bonne.
 *
 * <p>Le 2026-09-02, <i>"Calcul CP"</i> etait fautif et sa correction arbitree etait bloquee : <i>"Les
 * petites additions"</i> rendait {@code CatalogueChargeableIT} rouge sur {@code S1-8}, une clause de
 * nadia-prof de s01 qui refusait a ce titre le grapheme <i>ti</i> prononce /si/. J'ai refuse de
 * retrecir {@code S1-8} seul - c'etait du pedagogique - et fait entrer la regle avec une exemption
 * nommee d'exactement une entree, plafonnee et incapable de pourrir.
 *
 * <p>Le 2026-09-03, nadia-prof a rouvert sa clause, compte la population elle-meme - <b>quatre</b>
 * titres servis portent ce grapheme, pas deux - et l'a retrecie : <i>"retirer le grapheme ne rend
 * pas le titre decodable, donc la clause ne repare rien. On ne peut pas nommer un jeu d'additions
 * sans le mot addition."</i> Le titre est corrige dans le meme commit que ce paragraphe, et
 * <b>l'exemption est morte de sa cause, en un commit, sans avoir jamais eu a etre defendue</b>.
 * C'est la duree de vie qu'une exemption doit avoir.
 *
 * <p><b>Ce que cela laisse</b>, et c'est nadia-prof qui l'a demande sous cette forme : une
 * population de <b>quarante</b> titres, <b>zero</b> exemption, et aucune place ou en loger une - il
 * n'y a plus de liste ou un nom pourrait entrer.
 */
class AucunTitreDeJeuNePorteSonNiveauTest {

    /** Ecrits sous leur forme a barre oblique, celle que le recensement des garde-fous cherche. */
    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/AucunTitreDeJeuNePorteSonNiveauTest");

    /** Une entree de catalogue, reduite a ce que cette regle juge. */
    record Entree(String id, String titre, String niveau) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le niveau de chaque paquet, indexe par l'identifiant que le paquet declare et non par son nom
     * de fichier : deriver de la donnee plutot que d'une convention de nommage que rien ne tient.
     */
    static Map<String, String> niveauxDesPaquets(Path repertoireDeContenu) {
        Map<String, String> niveaux = new LinkedHashMap<>();
        try (Stream<Path> arborescence = Files.walk(repertoireDeContenu)) {
            arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted()
                    .forEach(fichier -> {
                        if (new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps
                                && corps.get("descripteur") instanceof Map<?, ?> descripteur) {
                            Object niveau = descripteur.get("niveau");
                            niveaux.put(String.valueOf(corps.get("id")),
                                    niveau == null ? null : String.valueOf(niveau));
                        }
                    });
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return niveaux;
    }

    static List<Entree> entreesServies(Path catalogue, Path repertoireDeContenu) {
        Map<String, String> niveaux = niveauxDesPaquets(repertoireDeContenu);
        List<Entree> entrees = new ArrayList<>();
        if (!(new Yaml().load(lire(catalogue)) instanceof Map<?, ?> corps)
                || !(corps.get("jeux") instanceof List<?> jeux)) {
            return entrees;
        }
        for (Object jeu : jeux) {
            if (jeu instanceof Map<?, ?> champs) {
                entrees.add(new Entree(String.valueOf(champs.get("id")),
                        String.valueOf(champs.get("titre")),
                        niveaux.get(String.valueOf(champs.get("paquet")))));
            }
        }
        return entrees;
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * ci-dessus produit. C'est ce qui permet de le mordre sur du materiau reel mute en memoire
     * plutot que sur une fixture qui ressemblerait a ce que j'imagine.
     */
    static void verifier(List<Entree> entrees) {
        if (entrees.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, cette regle ne porte"
                    + " alors sur rien");
        }
        List<String> fautes = new ArrayList<>();
        for (Entree entree : entrees) {
            if (entree.niveau() == null || entree.niveau().isBlank()) {
                fautes.add(entree.id() + " : le paquet que cette entree designe ne declare aucun"
                        + " niveau. La regle n'a alors rien a comparer au titre, et un garde-fou qui"
                        + " ne trouve plus ce qu'il compare le dit au lieu de passer (ADR-0051)");
                continue;
            }
            if (entree.titre().toLowerCase(Locale.ROOT)
                    .contains(entree.niveau().toLowerCase(Locale.ROOT))) {
                fautes.add(entree.id() + " : le titre \"" + entree.titre() + "\" porte le niveau \""
                        + entree.niveau() + "\" que son paquet declare. Un enfant n'a pas besoin"
                        + " qu'on lui rappelle sa classe pour jouer, et ce titre est le seul de la"
                        + " liste d'accueil a le faire (ADR-0078 ; arbitrage de nadia-prof du"
                        + " 2026-09-02)");
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("titres de jeu : " + fautes);
        }
    }

    @Test
    void aucunTitreDuCatalogueServiNePorteLeNiveauDeSonPaquet() {
        verifier(entreesServies(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Morsure 1, sur materiau reel mute en memoire : le geste exact qui a produit <i>"Calcul CP"</i>
     * et qui a traverse 26 sprints - un titre auquel on accole la classe.
     */
    @Test
    void unTitreAuquelOnAccoleSonNiveauFaitEchouerLeGardeFou() {
        List<Entree> entrees = new ArrayList<>(entreesServies(CATALOGUE_SERVI, CONTENU_SERVI));
        Entree premiere = entrees.get(0);
        entrees.set(0, new Entree(premiere.id(), premiere.titre() + " " + premiere.niveau(),
                premiere.niveau()));

        assertThatThrownBy(() -> verifier(entrees))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(premiere.id())
                .hasMessageContaining(premiere.niveau());
    }

    /** Morsure 2 : la meme faute en minuscules, que la comparaison ne doit pas laisser filer. */
    @Test
    void unNiveauEnMinusculesDansUnTitreFaitEchouerLeGardeFou() {
        List<Entree> entrees = new ArrayList<>(entreesServies(CATALOGUE_SERVI, CONTENU_SERVI));
        Entree premiere = entrees.get(0);
        entrees.set(0, new Entree(premiere.id(),
                premiere.titre() + " " + premiere.niveau().toLowerCase(Locale.ROOT),
                premiere.niveau()));

        assertThatThrownBy(() -> verifier(entrees))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(premiere.id());
    }

    /**
     * Morsure 3 : le paquet cesse de declarer son niveau. C'est le mode de defaillance qui rendrait
     * cette regle verte sur les quarante entrees a la fois, et c'est pour cela qu'il est rouge.
     */
    @Test
    void unPaquetQuiNeDeclarePlusSonNiveauFaitEchouerLeGardeFou() {
        List<Entree> entrees = new ArrayList<>(entreesServies(CATALOGUE_SERVI, CONTENU_SERVI));
        Entree premiere = entrees.get(0);
        entrees.set(0, new Entree(premiere.id(), premiere.titre(), null));

        assertThatThrownBy(() -> verifier(entrees))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare aucun niveau");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>, et elle n'est pas une redite
     * des trois precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse. C'est
     * le seul test du fichier qui prouve que {@link #entreesServies(Path, Path)} sait lire un
     * catalogue et un repertoire de paquets sur disque, et surtout <b>qu'il joint les deux</b> - le
     * titre vient de l'un, le niveau de l'autre, et une jointure ratee rendrait un niveau nul, donc
     * un rouge pour la mauvaise raison.
     *
     * <p>Le jeu fautif porte <b>deux</b> entrees et une seule faute, du meme niveau : ce qu'il
     * prouve est que la regle <b>discrimine</b>, et pas seulement qu'elle sait dire non.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaSeuleEntreeFautive() {
        List<Entree> fautives = entreesServies(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautives)
                .as("ADR-0051 : ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux"
                        + " entrees, ni si l'une d'elles n'a pas trouve son niveau")
                .extracting(Entree::niveau)
                .containsExactly("CP", "CP");

        assertThatThrownBy(() -> verifier(fautives))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot-fautif-cp")
                .hasMessageContaining("Additions CP")
                .hasMessageNotContaining("bandit-manchot-correct-cp");
    }

    /** Morsure 4 : la clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unCatalogueSansAucuneEntreeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }
}
