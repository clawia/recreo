package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Les proprietes d'un dessin d'item que le fichier lui-meme porte, et le cliquet de poids
 * qu'ADR-0036 clause 5 laissait en dette explicite.
 *
 * <p><b>Ce que ce garde-fou ajoute a celui d'ADR-0073.</b>
 * {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} verifie qu'un identifiant declare resout,
 * qu'aucun fichier ne dort, et qu'aucun dessin ne porte de texte. Il ne dit rien de ce que le
 * fichier declare comme geometrie ni de ce qu'il pese. Ces deux-la sont dues au lot qui livre les
 * premiers dessins, et elles ont chacune un mandat ecrit :</p>
 * <ul>
 *   <li><b>FIG-1</b> (nadia-prof, 2026-08-24, reconduite le 2026-08-31) : <i>"chaque figure est
 *       dessinee, et ses proportions sont declarees par le contenu, pas par une police. Un
 *       rectangle est un rectangle a toute taille d'ecran, verifiable autrement que par mon
 *       oeil."</i> Le {@code viewBox} est l'endroit ou ces proportions se declarent ;</li>
 *   <li><b>DV-1</b> (ines-design, proposition de palier 1, sections 3.2 et 11, affectee au lot des
 *       dessins par le planning de s25) : les quatre declarations de la feuille - {@code max-height},
 *       {@code max-width}, {@code height: auto}, {@code width: auto} - <b>ne font jamais grandir un
 *       dessin au-dela de sa taille naturelle</b>. Un fichier declare a 100 x 100 resterait a 100 px
 *       sous un plafond de 176. La taille intrinseque est donc une propriete du fichier, et elle
 *       vaut au moins le plafond de rendu ;</li>
 *   <li><b>FIG-3</b> (nadia-prof, reconduction de DEC-0002 amende le 2026-08-04) : la couleur ne
 *       distingue jamais deux formes. Sa moitie calculable sur le fichier est qu'aucun dessin ne
 *       remplit de surface et qu'ils n'emploient tous qu'une seule couleur de trait. Le motif est
 *       mesure sur le materiau de reprise : {@code contenu-repris/associer/cp-formes.yml} associe le
 *       glyphe carre orange a la cible "rectangle" et le glyphe carre bleu a la cible "carre" - la
 *       meme forme, deux reponses, departagees par la couleur ;</li>
 *   <li><b>ADR-0036 clause 5</b> (theo-archi) : <i>"le commit qui livre la premiere image illustree
 *       pose le cliquet de poids, sur la valeur qu'il installe [...]. Tant que ce cliquet n'existe
 *       pas, rien dans ce depot n'empeche une illustration de 4 Mo."</i></li>
 * </ul>
 *
 * <p><b>La couleur de trait n'est pas ecrite ici</b>, elle est lue dans la feuille maison, a
 * {@code --recreo-texte} : une valeur recopiee dans un test est une seconde source qui derive un
 * jour de la premiere (ADR-0015). Ce que ce garde-fou tient est donc que les dessins n'emploient
 * <b>qu'une</b> couleur de trait et que c'est celle du texte de la page, sans qu'aucun code de
 * couleur ne soit ecrit dans ce fichier.
 *
 * <p><b>Le poids se mesure fins de ligne normalisees en LF</b>, et la methode est ecrite a cote du
 * chiffre parce qu'un chiffre sans methode ne vaut rien. Motif : {@code .gitattributes} ne declare
 * {@code -text} que sur les ressources tierces, donc un poste configure en {@code core.autocrlf=true}
 * recoit ces dessins avec un octet de plus par ligne et un poste Linux sans. Un cliquet qui
 * compterait les octets du disque rendrait deux verdicts differents pour le meme arbre - la famille
 * de defaut que le commentaire de {@code .gitattributes} decrit deja sur une empreinte. La valeur
 * normalisee est aussi celle qui part dans le fat jar construit par la CI.
 *
 * <p><b>Les deux valeurs sont celles que ce lot installe, mesurees et non choisies</b> : douze
 * dessins de figures planes. <b>Elles ne remontent pas en silence.</b> Le sprint s25 livre une
 * seconde chaine visuelle, et le critere de sortie 11 de son planning dit ce qui vaut alors : la
 * premiere paire fusionnee pose le cliquet sur la valeur qu'elle installe, <b>la seconde le
 * respecte ou le deplace dans le meme commit, avec son motif ecrit et sa valeur mesuree</b>. Un
 * cliquet releve sans motif ne cliquette plus rien.
 *
 * <p><b>Ce que ce garde-fou ne prouve pas</b>, ecrit avant qu'on me le reproche : que la figure
 * dessinee soit la bonne. Qu'un carre soit carre, qu'un cercle ne soit pas un ovale, que trois
 * exemplaires d'une meme forme varient assez pour qu'un enfant de six ans la reconnaisse ailleurs -
 * <i>"ce sont des reponses fausses, pas des defauts de forme"</i> (nadia-prof, 2026-08-15), et cela
 * se verifie image par image, item par item. Aucun automate ne le fera a sa place.
 */
class ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest {

    /** ADR-0073 clause 2 : les illustrations de contenu, distinctes du decor de static/images. */
    private static final Path ILLUSTRATIONS = Path.of("src/main/resources/static/illustrations");

    private static final Path FEUILLE_MAISON = Path.of("src/main/resources/static/css/recreo.css");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src/test/resources/contreexemple"
            + "/ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest");

    /**
     * DV-1 : le plafond de rendu retenu par la feuille, en pixels. C'est le {@code max-height} de
     * {@code .image-item} propose par ines-design - 11 rem, soit 176 px, la valeur du confort de
     * lecture qu'elle a mesuree. Un fichier qui declarerait moins ne grandirait jamais jusqu'a lui.
     */
    private static final int PLAFOND_DE_RENDU_EN_PIXELS = 176;

    /**
     * Cliquet de poids, ADR-0036 clause 5 : le plus gros dessin, fins de ligne normalisees en LF.
     *
     * <p><b>Deplace a la fusion des deux paires visuelles par sofia-sm, casquette Scrum Master,
     * 2026-08-31 - critere 11 du planning de s25, execute.</b> Ce cliquet valait 1 112 octets,
     * mesures par lea-dev sur ses douze figures planes ; il vaut desormais {@code 2 685}, la taille
     * du plus lourd des vingt-quatre dessins servis, {@code monnaie-meme-somme-e.svg}. <b>Valeur
     * mesuree par la Scrum Master sur l'arbre fusionne</b>, jamais relayee depuis l'annonce d'un
     * lot, et deplacee dans le commit qui apporte la seconde chaine - c'est la condition exacte que
     * le critere 11 pose : <i>"la seconde le respecte, ou le deplace dans le meme commit avec son
     * motif ecrit et sa valeur mesuree. Un cliquet releve sans motif ne cliquete plus rien."</i>
     *
     * <p><b>Le motif de la hausse, et il n'est pas un relachement de trace</b> : une figure plane
     * est <b>un</b> trace - un {@code rect}, un {@code circle}, un {@code path} - la ou une scene de
     * monnaie porte jusqu'a six objets, dont chacun demande un contour, un contour interieur et une
     * a deux instances de glyphe. Le trace est plus gros par construction. Motif de sofia-sm,
     * casquette dev, verifie par la Scrum Master sur les fichiers avant d'etre repris.
     *
     * <p><b>Ce que ce cliquet mesure, et ines-design demande que ce soit dit</b> : les octets
     * <b>commites</b>, en-tete de commentaire compris, <b>pas les octets de trace</b>. Ses propres
     * fichiers de demonstration pesent 275 octets de trace pour 1 084 commites, et <i>"un seuil pose
     * sur l'un ne veut rien dire sur l'autre, et le cliquet doit dire lequel il mesure"</i>. Les
     * octets commites sont retenus parce que ce sont ceux que l'enfant telecharge.
     */
    private static final int POIDS_D_UN_DESSIN_MAXIMUM = 2685;

    /**
     * Cliquet de poids, ADR-0036 clause 5 : le repertoire entier, fins de ligne normalisees en LF.
     *
     * <p>Meme deplacement, meme motif et meme methode que ci-dessus : 6 206 octets pour les douze
     * figures planes seules, <b>29 118</b> pour les vingt-quatre dessins servis apres fusion.
     */
    private static final int POIDS_TOTAL_MAXIMUM = 29118;

    private static final String VUE = "viewBox";

    private static final String REMPLISSAGE = "fill";

    private static final String TRAIT = "stroke";

    private static final String JETON_DE_TEXTE = "--recreo-texte:";

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les dessins d'un repertoire, indexes par leur nom de fichier, <b>fins de ligne normalisees en
     * LF</b>. La normalisation est faite ici, une fois, pour que le juge pur en dessous compte des
     * octets qui ne dependent pas du poste.
     */
    static Map<String, String> dessinsDe(Path repertoire) {
        Map<String, String> dessins = new LinkedHashMap<>();
        if (!Files.isDirectory(repertoire)) {
            return dessins;
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            arborescence.filter(Files::isRegularFile).sorted().forEach(fichier ->
                    dessins.put(fichier.getFileName().toString(),
                            lire(fichier).replace("\r\n", "\n")));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return dessins;
    }

    /**
     * La balise racine d'un dessin, attributs compris, sans son contenu. C'est la seule ou les
     * dimensions <b>intrinseques</b> se declarent : {@code width} et {@code height} y designent la
     * taille naturelle du fichier, alors que les memes noms, sur une forme a l'interieur, designent
     * la geometrie de cette forme dans le systeme du {@code viewBox}. Les confondre lirait la
     * largeur d'un rectangle comme la taille de l'image.
     */
    private static String baliseRacine(String source) {
        int debut = source.indexOf("<svg");
        if (debut < 0) {
            return "";
        }
        int fin = source.indexOf('>', debut);
        return fin < 0 ? source.substring(debut) : source.substring(debut, fin);
    }

    /**
     * Les valeurs d'un attribut dans un fragment de balisage. Ecrit en balayage de chaine et non en
     * expression reguliere : ADR-0008 interdit {@code java.util.regex} dans ce module, et le motif
     * en vaut la peine - une expression reguliere sur {@code width="..."} attraperait aussi
     * {@code stroke-width="..."}, ce que le caractere precedent, verifie ici, ecarte.
     */
    static List<String> valeursDeLAttribut(String fragment, String attribut) {
        List<String> valeurs = new ArrayList<>();
        String marque = attribut + "=\"";
        int position = 0;
        while ((position = fragment.indexOf(marque, position)) >= 0) {
            int debut = position + marque.length();
            if (position > 0 && !Character.isWhitespace(fragment.charAt(position - 1))) {
                position = debut;
                continue;
            }
            int fin = fragment.indexOf('"', debut);
            if (fin < 0) {
                return valeurs;
            }
            valeurs.add(fragment.substring(debut, fin));
            position = fin + 1;
        }
        return valeurs;
    }

    /** La couleur du texte de la page, lue dans la feuille maison et jamais recopiee ici. */
    static String couleurDuTexte(Path feuille) {
        String feuilleMaison = lire(feuille);
        int declaration = feuilleMaison.indexOf(JETON_DE_TEXTE);
        if (declaration < 0) {
            throw new AssertionError(feuille + " : le jeton " + JETON_DE_TEXTE + " n'y est pas"
                    + " declare, ce garde-fou ne peut alors comparer la couleur des traits a rien");
        }
        int fin = feuilleMaison.indexOf(';', declaration);
        return feuilleMaison.substring(declaration + JETON_DE_TEXTE.length(), fin).strip();
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * ci-dessus produit. C'est ce qui permet de le mordre sur du materiau reel mute en memoire
     * plutot que sur une fixture qui ressemblerait a ce que j'imagine (lecon de s05).
     */
    static void verifier(Map<String, String> dessins, String couleurDuTexte) {
        if (dessins.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun dessin d'item sous " + ILLUSTRATIONS
                    + ", les clauses de geometrie et le cliquet de poids ne portent alors sur rien");
        }
        List<String> fautes = new ArrayList<>();
        Set<String> couleursDeTrait = new LinkedHashSet<>();
        int total = 0;

        for (Map.Entry<String, String> dessin : dessins.entrySet()) {
            String nom = dessin.getKey();
            String source = dessin.getValue();
            int poids = source.getBytes(StandardCharsets.UTF_8).length;
            total += poids;

            String racine = baliseRacine(source);
            if (valeursDeLAttribut(racine, VUE).isEmpty()) {
                fautes.add(nom + " : aucun viewBox. Les proportions de la figure sont alors"
                        + " gouvernees par la boite que la feuille lui donne, et un rectangle cesse"
                        + " d'etre le meme rectangle a toute largeur d'ecran (FIG-1)");
            }

            for (String dimension : List.of("width", "height")) {
                List<String> declarees = valeursDeLAttribut(racine, dimension);
                if (declarees.isEmpty()) {
                    fautes.add(nom + " : aucune dimension intrinseque '" + dimension + "'. La"
                            + " feuille ne fait jamais grandir un dessin au-dela de sa taille"
                            + " naturelle (DV-1)");
                } else if (enPixels(declarees.get(0)) < PLAFOND_DE_RENDU_EN_PIXELS) {
                    fautes.add(nom + " : " + dimension + " intrinseque de " + declarees.get(0)
                            + " px, sous le plafond de rendu de " + PLAFOND_DE_RENDU_EN_PIXELS
                            + " px. Le dessin ne grandira jamais jusqu'a ce plafond et sera servi"
                            + " plus petit que ce que la feuille autorise (DV-1)");
                }
            }

            for (String remplissage : valeursDeLAttribut(source, REMPLISSAGE)) {
                if (!"none".equals(remplissage)) {
                    fautes.add(nom + " : la surface est remplie de '" + remplissage + "'. Une"
                            + " figure lue en niveaux de gris doit donner la meme reponse, et un"
                            + " cercle rempli n'est plus un cercle mais un disque (FIG-3, FIG-4)");
                }
            }

            couleursDeTrait.addAll(valeursDeLAttribut(source, TRAIT));

            if (poids > POIDS_D_UN_DESSIN_MAXIMUM) {
                fautes.add(nom + " : " + poids + " octets, au-dessus du cliquet de "
                        + POIDS_D_UN_DESSIN_MAXIMUM + " octets pose par le lot qui a livre le"
                        + " premier paquet illustre. Le deplacer demande un motif ecrit et une"
                        + " valeur mesuree, dans le meme commit (ADR-0036 clause 5)");
            }
        }

        if (!couleursDeTrait.equals(Set.of(couleurDuTexte))) {
            fautes.add("les dessins emploient les couleurs de trait " + couleursDeTrait + " au lieu"
                    + " de la seule couleur du texte de la page. Deux couleurs de trait rendent"
                    + " possible qu'une couleur porte l'information, ce que FIG-3 interdit");
        }

        if (total > POIDS_TOTAL_MAXIMUM) {
            fautes.add("les illustrations de contenu pesent " + total + " octets, au-dessus du"
                    + " cliquet de " + POIDS_TOTAL_MAXIMUM + " octets. Le deplacer demande un motif"
                    + " ecrit et une valeur mesuree, dans le meme commit (ADR-0036 clause 5,"
                    + " critere de sortie 11 du planning de s25)");
        }

        if (!fautes.isEmpty()) {
            throw new AssertionError("dessins d'item : " + fautes);
        }
    }

    /**
     * Une dimension intrinseque declaree sans unite vaut des pixels. Une valeur ecrite autrement -
     * un pourcentage, une unite - ne se compare pas au plafond de rendu, et elle est rendue nulle
     * plutot que devinee : c'est le sens de DV-1 qu'elle trahit, et le message le dira.
     */
    private static int enPixels(String declaree) {
        try {
            return Integer.parseInt(declaree.strip());
        } catch (NumberFormatException pasUnNombreDePixels) {
            return 0;
        }
    }

    @Test
    void chaqueDessinServiDeclareSaGeometrieEtLeCorpusResteSousLeCliquet() {
        verifier(dessinsDe(ILLUSTRATIONS), couleurDuTexte(FEUILLE_MAISON));
    }

    /**
     * Le cliquet vaut exactement la taille de ce qui est installe, pas une place de libre : sans
     * cette moitie-la, il pourrait etre pose loin au-dessus du materiau et ne rien cliqueter. Meme
     * forme que les plafonds de dette du depot.
     */
    @Test
    void leCliquetVautExactementCeQueCeLotInstalle() {
        Map<String, String> dessins = dessinsDe(ILLUSTRATIONS);
        int total = 0;
        int plusGros = 0;
        for (String source : dessins.values()) {
            int poids = source.getBytes(StandardCharsets.UTF_8).length;
            total += poids;
            plusGros = Math.max(plusGros, poids);
        }
        assertThat(total)
                .as("ADR-0036 clause 5 : le cliquet total vaut exactement le poids installe, fins"
                        + " de ligne normalisees en LF")
                .isEqualTo(POIDS_TOTAL_MAXIMUM);
        assertThat(plusGros)
                .as("ADR-0036 clause 5 : le cliquet par dessin vaut exactement le plus gros dessin"
                        + " installe, fins de ligne normalisees en LF")
                .isEqualTo(POIDS_D_UN_DESSIN_MAXIMUM);
    }

    /** Morsure 1, sur materiau reel mute en memoire : les proportions ne sont plus declarees. */
    @Test
    void unDessinSansViewBoxFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        String source = dessins.get(premier);
        String declaration = VUE + "=\"" + valeursDeLAttribut(baliseRacine(source), VUE).get(0)
                + "\" ";
        dessins.put(premier, source.replace(declaration, ""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("viewBox")
                .hasMessageContaining("FIG-1");
    }

    /** Morsure 2 : un dessin plus petit que le plafond de rendu, donc servi plus petit qu'il ne peut. */
    @Test
    void unDessinPlusPetitQueLePlafondDeRenduFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier)
                .replace("width=\"480\" height=\"480\"", "width=\"120\" height=\"120\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DV-1");
    }

    /** Morsure 3 : une couleur de trait de plus, c'est-a-dire une couleur qui peut porter du sens. */
    @Test
    void unDessinAUneSecondeCouleurDeTraitFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        String couleur = couleurDuTexte(FEUILLE_MAISON);
        dessins.put(premier, dessins.get(premier)
                .replace("stroke=\"" + couleur + "\"", "stroke=\"#c0392b\""));

        assertThatThrownBy(() -> verifier(dessins, couleur))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FIG-3");
    }

    /** Morsure 4 : le cliquet de poids, mordu par un dessin de plus et non par un nombre invente. */
    @Test
    void unDessinDeTropFaitEchouerLeCliquetDePoids() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put("figure-en-trop.svg", dessins.get(premier));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0036 clause 5");
    }

    /** Morsure 5 : la clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unPerimetreDeDessinsVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(Map.of(), couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>, et elle n'est pas une redite
     * des cinq precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse. C'est
     * le seul test du fichier qui prouve que {@link #dessinsDe} sait lire un repertoire de dessins
     * sur disque. Le jeu fautif porte trois fautes de forme d'un seul coup - une figure sans
     * viewBox, une figure trop petite pour le plafond de rendu, une figure remplie d'un aplat.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesTroisFautes() {
        Map<String, String> fautifs = dessinsDe(CONTRE_EXEMPLE.resolve("illustrations-fautives"));

        assertThat(fautifs)
                .as("ADR-0051 : ce jeu fautif ne prouve rien si le recensement n'y lit aucun dessin")
                .hasSize(3);

        assertThatThrownBy(() -> verifier(fautifs, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("figure-sans-proportions.svg")
                .hasMessageContaining("figure-trop-petite.svg")
                .hasMessageContaining("figure-remplie.svg");
    }
}
