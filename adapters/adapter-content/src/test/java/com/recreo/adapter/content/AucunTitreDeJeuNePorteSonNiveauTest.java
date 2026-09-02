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
import java.util.Set;
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
 */
class AucunTitreDeJeuNePorteSonNiveauTest {

    /** Ecrits sous leur forme a barre oblique, celle que le recensement des garde-fous cherche. */
    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/AucunTitreDeJeuNePorteSonNiveauTest");

    /**
     * <b>La seule entree que ce commit ne peut pas rendre verte, et le motif est ecrit plutot que
     * tu.</b> Le titre <i>"Calcul CP"</i> est fautif, la correction est arbitree - <i>"Les petites
     * additions"</i>, nadia-prof, 2026-09-02 - et elle a ete appliquee ici puis <b>retiree</b> :
     * elle rend {@code CatalogueChargeableIT} rouge sur {@code S1-8}, une clause de nadia-prof de
     * s01 qui refuse au titre de ce jeu le grapheme <i>"ti"</i> prononce /si/, et <i>additions</i>
     * le porte. <b>Resoudre cette contradiction est pedagogique et n'est pas a moi</b> : elle est
     * routee a nadia-prof par sofia-sm.
     *
     * <p><b>Ce que cette exemption est, et ce qu'elle n'est pas.</b> C'est une <b>liste de refus
     * nommes</b>, sur le patron de {@code DETTE_DE_BALAYAGE} : elle ne peut pas grandir (le plafond
     * vaut exactement sa taille), elle ne peut pas <b>pourrir</b> (chaque nom doit designer une
     * entree qui existe <i>et</i> qui est encore fautive - le jour ou le titre est corrige, ce
     * fichier rougit et l'exemption doit sortir), et une entree fautive de plus est <b>rouge</b> au
     * lieu d'entrer en silence. Ce n'est pas un perimetre : un oubli y produit un rouge, jamais un
     * vert.
     */
    private static final Set<String> DETTE_DE_TITRE = Set.of("bandit-manchot-additions-cp");

    /**
     * Etat au 2026-09-02, s27, a la creation de cette regle. Ce nombre ne remonte jamais ; le
     * baisser demande un diff visible. Il vaut <b>exactement</b> la taille de la dette, et pas une
     * place de libre - un plafond au-dessus de sa dette n'est pas un plafond.
     */
    private static final int PLAFOND_DE_DETTE = 1;

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
        verifier(entrees, DETTE_DE_TITRE);
    }

    static void verifier(List<Entree> entrees, Set<String> exemptees) {
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
            if (exemptees.contains(entree.id())) {
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
     * Le cliquet de la dette : elle ne grandit pas, et surtout <b>elle ne pourrit pas</b>. Chaque
     * nom exempte doit designer une entree qui existe encore et qui est <b>encore fautive</b> - sans
     * cette seconde moitie, une exemption survivrait a sa cause et dispenserait de preuve une entree
     * saine, ce qui est la forme la plus courante d'un garde-fou qui ne garde plus rien.
     */
    @Test
    void laDetteDeTitreNeGrandditPasEtChaqueNomExempteDesigneEncoreUneEntreeFautive() {
        List<Entree> entrees = entreesServies(CATALOGUE_SERVI, CONTENU_SERVI);

        assertThat(DETTE_DE_TITRE)
                .as("le plafond vaut exactement la taille de la dette, et pas une place de libre")
                .hasSize(PLAFOND_DE_DETTE);

        for (String exemptee : DETTE_DE_TITRE) {
            Entree nommee = entrees.stream().filter(entree -> entree.id().equals(exemptee))
                    .findFirst().orElse(null);
            assertThat(nommee)
                    .as("l'exemption nomme %s, qui n'est plus une entree du catalogue : une"
                            + " exemption qui ne designe plus rien de reel doit sortir", exemptee)
                    .isNotNull();
            assertThatThrownBy(() -> verifier(List.of(nommee), Set.of()))
                    .as("l'exemption nomme %s, dont le titre n'est plus fautif : la dette est payee"
                            + " et l'exemption doit sortir dans le meme commit", exemptee)
                    .isInstanceOf(AssertionError.class);
        }
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

        assertThatThrownBy(() -> verifier(entrees, Set.of()))
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

        assertThatThrownBy(() -> verifier(entrees, Set.of()))
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

        assertThatThrownBy(() -> verifier(entrees, Set.of()))
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

        assertThatThrownBy(() -> verifier(fautives, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bandit-manchot-fautif-cp")
                .hasMessageContaining("Additions CP")
                .hasMessageNotContaining("bandit-manchot-correct-cp");
    }

    /** Morsure 4 : la clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unCatalogueSansAucuneEntreeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }
}
