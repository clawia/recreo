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
 *   <li><b>DES-1 a DES-7</b> (ines-design, {@code contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md},
 *       2026-08-31), qui <b>remplacent la dependance DV-1</b> et en corrigent le sens. DV-1 disait
 *       <i>"des dimensions au moins egales au plafond retenu"</i>, et ce fichier l'avait encode
 *       ainsi. <b>Sa mesure sur les quatre declarations reellement servies dit l'inverse</b> : un
 *       fichier qui ne porte qu'un cadre de vue sort a 176 px quoi qu'il soit - c'est la feuille qui
 *       decide -, et un fichier qui declare 480 sort a 176 lui aussi, parce que {@code max-height}
 *       est un plafond et jamais une taille. <b>Declarer plus que le plafond est donc sans effet et
 *       trompeur</b>, et le seuil se lit dans l'autre sens ;</li>
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
     * DES-3, plafond : le {@code max-height} de {@code .image-item}, en pixels. 11 rem, soit 176 px,
     * valeur posee par marc-dev et <b>confirmee et tranchee</b> par ines-design le 2026-08-31 -
     * <i>"tranchee, plus a trancher"</i>, nadia-prof gardant son veto. Elle passe le plancher de
     * lisibilite du dessin le plus exigeant du palier 1 - un cadran a douze nombres, 123 px - avec
     * 53 px de marge, et reste sous le niveau de confort de 202 px.
     *
     * <p>Un dessin qui declarerait davantage serait rogne par la feuille : sa declaration n'aurait
     * aucun effet et annoncerait une taille que rien ne rend. C'est pour cela que ce nombre est un
     * <b>plafond</b> et non un plancher, et c'est la correction que DES-3 apporte a DV-1.
     */
    private static final int PLAFOND_DE_RENDU_EN_PIXELS = 176;

    /**
     * DES-3, plancher de trait : <i>"le trait rend au moins 2 px"</i>, la plus fine ligne que recreo
     * dessine deja en lui faisant porter un sens - la bordure de {@code 0.125rem} de
     * {@code .champ-reponse} et des deux sorties. Une epaisseur qui tombe sous le pixel disparait.
     *
     * <p>C'est le seul plancher qui tienne une figure plane : ines-design ecrit qu'<i>"une figure
     * plane sans chiffre n'est tenue que par le plancher de trait"</i>. Le second plancher qu'elle
     * pose - <b>l'encre d'un chiffre trace rend au moins 14 px</b> - ne s'applique qu'a un dessin qui
     * porte un chiffre, et <b>ce garde-fou ne le verifie pas</b> : il faudrait savoir lesquelles des
     * commandes de trace d'un fichier sont un glyphe, et aucune propriete du fichier ne le dit. Il
     * est nomme ici plutot que passe sous silence ; il mordra sur la chaine de la monnaie, et il
     * reste dans la main d'ines-design et de nadia-prof.
     */
    private static final int PLANCHER_DE_TRAIT_EN_PIXELS = 2;

    /**
     * Cliquet de poids, ADR-0036 clause 5 : le plus gros dessin.
     *
     * <p><b>Ce que ce cliquet mesure, ecrit explicitement parce qu'ines-design a montre que la
     * question se pose</b> : <b>le fichier commite, commentaires compris</b>, fins de ligne
     * normalisees en LF - jamais les seules commandes de trace. Sa mesure du 2026-08-31 : une
     * figure plane nue pese <b>275 octets de trace pour 1 084 octets commites</b> une fois commentee
     * comme ce depot commente ses fichiers, et <i>"un seuil pose sur l'un ne veut rien dire sur
     * l'autre"</i>. C'est donc le fichier ; qui deplacera ce cliquet deplacera la meme chose.
     */
    private static final int POIDS_D_UN_DESSIN_MAXIMUM = 2685;

    /**
     * Cliquet de poids, ADR-0036 clause 5 : le repertoire entier, meme perimetre et meme methode que
     * ci-dessus.
     *
     * <p><b>Ces deux valeurs ont monte une fois, et le motif est ecrit plutot que tu</b> : 1 112 a
     * 1 430 octets pour le plus gros dessin, 6 206 a 6 524 pour le repertoire, quand le commentaire
     * de {@code figure-carre-droit.svg} a ete reecrit pour porter DES-1 a DES-3 a la place de DV-1.
     * <b>La taille declaree n'y est pour rien</b> : passer de {@code width="480"} a
     * {@code width="176"} n'a coute aucun octet, ce qui verifie sur ce corpus ce qu'ines-design pose
     * - <i>"les dimensions declarees : effet nul"</i>. Ce sont les commentaires qui pesent,
     * <i>"a la hausse, et je l'assume"</i>.
     *
     * <p><b>Amendement du 2026-08-31 : ce cliquet a bouge trois fois, et le paragraphe ci-dessus,
     * qui dit "une fois", ne le disait plus.</b> Il n'est pas reecrit - il etait exact quand il a
     * ete ecrit - mais il est complete, parce qu'un compte perime dans le fichier que tout le monde
     * lit est de la meme famille qu'un chiffre sans methode. Les trois mouvements, chacun avec la
     * main qui l'a fait :</p>
     * <ol>
     *   <li><b>1 112 / 6 206 vers 1 430 / 6 524</b>, lea-dev, commentaire de DES-1 a DES-3 ;</li>
     *   <li><b>1 430 / 6 524 vers 2 685 / 29 436</b>, sofia-sm, entree de la chaine de la monnaie -
     *       critere 11 du planning execute, motif ecrit dans son commit : <i>"une scene de monnaie
     *       porte jusqu'a six objets contre un trace unique pour une figure, donc le trace est plus
     *       gros par construction"</i>, en-tetes ramenes de neuf a six lignes pour le contenir ;</li>
     *   <li><b>29 436 vers 30 070</b>, lea-dev, le seul mouvement du present commit. <b>Le plus
     *       gros dessin ne bouge pas</b> - il reste celui de la monnaie a 2 685 octets. La cause est
     *       la correction de commentaire que nadia-prof route en section 7 de sa fiche :
     *       {@code figure-triangle-pose.svg} annoncait "trois cotes egaux" pour un triangle dont ses
     *       sommets declares donnent une base de 78 et deux cotes de 83,65. <b>Le dessin n'est pas
     *       touche</b>, elle le route comme une correction de commentaire ; c'est donc, une
     *       troisieme fois, du commentaire et rien d'autre qui pese.</li>
     * </ol>
     */
    private static final int POIDS_TOTAL_MAXIMUM = 30070;

    private static final String VUE = "viewBox";

    private static final String REMPLISSAGE = "fill";

    private static final String TRAIT = "stroke";

    private static final String EPAISSEUR_DE_TRAIT = "stroke-width";

    /** DES-4 : ce qui decouplerait l'epaisseur du trait de l'echelle du dessin. */
    private static final String EFFET_VECTORIEL = "non-scaling-stroke";

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
            List<String> vue = valeursDeLAttribut(racine, VUE);
            if (vue.isEmpty()) {
                fautes.add(nom + " : aucun viewBox. Les proportions de la figure sont alors"
                        + " gouvernees par la boite que la feuille lui donne, et un rectangle cesse"
                        + " d'etre le meme rectangle a toute largeur d'ecran (FIG-1, DES-1)");
            }

            List<String> largeurs = valeursDeLAttribut(racine, "width");
            List<String> hauteurs = valeursDeLAttribut(racine, "height");
            if (largeurs.isEmpty() || hauteurs.isEmpty()) {
                fautes.add(nom + " : le dessin ne declare pas sa taille. Un fichier qui ne porte"
                        + " qu'un cadre de vue sort exactement au plafond de la feuille, quel qu'il"
                        + " soit - c'est la feuille qui decide de sa taille, pas lui (DES-1)");
                continue;
            }
            int largeur = enUnitesUtilisateur(largeurs.get(0));
            int hauteur = enUnitesUtilisateur(hauteurs.get(0));
            if (largeur == 0 || hauteur == 0) {
                fautes.add(nom + " : la taille declaree ('" + largeurs.get(0) + "' x '"
                        + hauteurs.get(0) + "') n'est pas en unites utilisateur. Un pourcentage ou"
                        + " une unite rend la boite au plafond de la feuille, donc rend la"
                        + " declaration muette (DES-1)");
                continue;
            }
            if (hauteur > PLAFOND_DE_RENDU_EN_PIXELS) {
                fautes.add(nom + " : hauteur declaree de " + hauteur + " px, au-dessus du plafond"
                        + " de rendu de " + PLAFOND_DE_RENDU_EN_PIXELS + " px. La feuille rogne :"
                        + " la declaration est sans effet et annonce une taille que rien ne rend"
                        + " (DES-3)");
            }
            if (!vue.isEmpty() && !memeRapport(vue.get(0), largeur, hauteur)) {
                fautes.add(nom + " : la taille declaree (" + largeur + " x " + hauteur + ") n'est"
                        + " pas dans le rapport du cadre de vue ('" + vue.get(0) + "'). Le fichier"
                        + " declarerait un rapport qu'il ne rend pas, et FIG-1 cesserait d'etre"
                        + " verifiable autrement que par un oeil (DES-2)");
            }
            int traitRendu = traitRenduEnPixels(source, vue.isEmpty() ? null : vue.get(0), hauteur);
            if (traitRendu < PLANCHER_DE_TRAIT_EN_PIXELS) {
                fautes.add(nom + " : le trait le plus fin rend " + traitRendu + " px a la hauteur"
                        + " declaree, sous le plancher de " + PLANCHER_DE_TRAIT_EN_PIXELS + " px."
                        + " Une epaisseur qui tombe sous le pixel disparait (DES-3)");
            }

            if (source.contains(EFFET_VECTORIEL)) {
                fautes.add(nom + " : le dessin declare " + EFFET_VECTORIEL + ". L'epaisseur du"
                        + " trait cesse alors de suivre l'echelle du dessin, et le plancher de trait"
                        + " ci-dessus, qui se calcule sur cette echelle, cesse de dire vrai"
                        + " (DES-4)");
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
                    + " de la seule couleur du texte de la page, ecrite en clair. Deux couleurs de"
                    + " trait rendent possible qu'une couleur porte l'information (FIG-3) ; et une"
                    + " teinte qui n'est pas une valeur litterale - currentColor, une variable de"
                    + " feuille - n'est gouvernee par personne, parce qu'un dessin charge comme"
                    + " image est un document isole ou la couleur de la page n'entre pas (DES-5)");
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
     * DES-1 : une dimension declaree en <b>unites utilisateur</b> est un nombre nu. Un pourcentage
     * ou une unite rend zero plutot qu'une valeur devinee - c'est exactement le cas qu'ines-design a
     * mesure comme rendant la boite au plafond de la feuille, donc comme rendant la declaration
     * muette -, et l'appelant le nomme.
     */
    private static int enUnitesUtilisateur(String declaree) {
        try {
            return Integer.parseInt(declaree.strip());
        } catch (NumberFormatException pasUneUniteUtilisateur) {
            return 0;
        }
    }

    /** Les quatre nombres d'un cadre de vue, ou un tableau vide si la forme n'est pas celle-la. */
    private static double[] cadreDeVue(String vue) {
        String[] morceaux = vue.strip().split("[ ,]+");
        if (morceaux.length != 4) {
            return new double[0];
        }
        double[] nombres = new double[4];
        for (int rang = 0; rang < 4; rang++) {
            try {
                nombres[rang] = Double.parseDouble(morceaux[rang]);
            } catch (NumberFormatException pasUnCadreDeVue) {
                return new double[0];
            }
        }
        return nombres;
    }

    /**
     * DES-2 : la taille declaree est dans le rapport du cadre de vue. La comparaison est faite en
     * produit croise et non en division, pour qu'aucun arrondi ne decide du verdict ; la tolerance
     * d'un demi-pixel absorbe une taille entiere posee sur un rapport qui ne tombe pas juste, et
     * rien de plus - a 176 px de haut, un demi-pixel de largeur ne se voit pas, un pixel se voit.
     */
    private static boolean memeRapport(String vue, int largeur, int hauteur) {
        double[] cadre = cadreDeVue(vue);
        if (cadre.length != 4 || cadre[2] <= 0 || cadre[3] <= 0) {
            return true;
        }
        return Math.abs(largeur * cadre[3] - hauteur * cadre[2]) <= cadre[3] / 2;
    }

    /**
     * DES-3, plancher de trait : l'epaisseur rendue du trait <b>le plus fin</b> du dessin, a la
     * hauteur qu'il declare. Le plus fin et non le premier : c'est celui qui disparait en premier,
     * et un dessin n'a pas a n'avoir qu'une epaisseur.
     *
     * <p>Le calcul est celui du rendu : une epaisseur declaree dans le systeme du cadre de vue est
     * mise a l'echelle avec lui. DES-4 est ce qui rend ce calcul juste, et c'est pourquoi elle
     * interdit {@code vector-effect: non-scaling-stroke} - qui decouplerait l'epaisseur de l'echelle
     * et ferait mentir ce plancher.
     */
    private static int traitRenduEnPixels(String source, String vue, int hauteurDeclaree) {
        double plusFin = Double.MAX_VALUE;
        for (String epaisseur : valeursDeLAttribut(source, EPAISSEUR_DE_TRAIT)) {
            try {
                plusFin = Math.min(plusFin, Double.parseDouble(epaisseur.strip()));
            } catch (NumberFormatException pasUneEpaisseurEnUnitesUtilisateur) {
                return 0;
            }
        }
        if (plusFin == Double.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        double[] cadre = vue == null ? new double[0] : cadreDeVue(vue);
        double hauteurDuCadre = cadre.length == 4 && cadre[3] > 0 ? cadre[3] : hauteurDeclaree;
        return (int) Math.floor(plusFin * hauteurDeclaree / hauteurDuCadre);
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

    /**
     * Morsure 2 : un dessin qui declare plus que le plafond de rendu. C'est le cas qu'ines-design a
     * mesure - un fichier a 480 sort a 176 comme un fichier sans taille du tout -, et c'est celui
     * que ce garde-fou refusait dans l'autre sens avant DES-3.
     */
    @Test
    void unDessinQuiDeclarePlusQueLePlafondDeRenduFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier)
                .replace("width=\"176\" height=\"176\"", "width=\"480\" height=\"480\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-3");
    }

    /**
     * Morsure 3 : un dessin qui ne porte qu'un cadre de vue. <b>C'est le defaut que le paquet de
     * contre-exemple d'ADR-0073 porte</b> - ines-design le signale nommement -, donc celui dont tout
     * dessin copie sur ce modele herite : il sort au plafond de la feuille quoi qu'il soit, et une
     * rangee de pieces qui n'a besoin que de 43 px en occuperait 176.
     */
    @Test
    void unDessinSansTailleDeclareeFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier).replace(" width=\"176\" height=\"176\"", ""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-1");
    }

    /**
     * Morsure 4 : une taille declaree qui n'est pas dans le rapport du cadre de vue. Le fichier
     * annoncerait alors un rapport qu'il ne rend pas, et FIG-1 - <i>"un rectangle est un rectangle a
     * toute taille d'ecran, verifiable autrement que par mon oeil"</i> - cesserait d'etre verifiable
     * sur la declaration.
     */
    @Test
    void uneTailleHorsDuRapportDuCadreDeVueFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier)
                .replace("width=\"176\" height=\"176\"", "width=\"120\" height=\"176\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-2");
    }

    /**
     * Morsure 5 : une teinte de trait qui n'est pas une valeur litterale. <b>Ce n'est pas un cas
     * imagine</b> : ines-design a mesure au pixel qu'un dessin charge comme image est un document
     * isole, ou {@code currentColor} rend du noir et ou une variable de feuille n'est pas definie du
     * tout - et ses deux propres demonstrations portaient ce defaut avant qu'elle ne le corrige.
     *
     * <p>Elle vaut aussi comme reponse a la question posee sur le mecanisme de ce garde-fou : il
     * <b>lit</b> la teinte du texte dans la feuille maison et la compare a ce que le dessin
     * <b>ecrit</b>. Il n'a jamais suppose qu'un dessin heritait de la couleur de la page - ce qui
     * serait faux -, il exige que le dessin ecrive la meme valeur que le jeton. Le fait du document
     * isole ne le casse donc pas : il est exactement ce qui rend DES-5 necessaire, et cette morsure
     * montre que la clause l'attrape.
     */
    @Test
    void uneTeinteHeriteeAuLieuDUneValeurLitteraleFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        String couleur = couleurDuTexte(FEUILLE_MAISON);
        dessins.put(premier, dessins.get(premier)
                .replace("stroke=\"" + couleur + "\"", "stroke=\"currentColor\""));

        assertThatThrownBy(() -> verifier(dessins, couleur))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-5")
                .hasMessageContaining("currentColor");
    }

    /**
     * Morsure 6 : l'effet vectoriel qui decouple l'epaisseur du trait de l'echelle du dessin, et qui
     * ferait donc mentir le plancher de trait calcule au-dessus.
     */
    @Test
    void unTraitAEchelleFixeFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier).replace("stroke-width=\"6\"",
                "stroke-width=\"6\" vector-effect=\"non-scaling-stroke\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-4");
    }

    /**
     * Morsure 7 : un trait trop fin pour la hauteur que le dessin declare. Le plancher de DES-3 est
     * mordu par une epaisseur reelle mise a l'echelle, jamais par un nombre invente.
     */
    @Test
    void unTraitSousLePlancherFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put(premier, dessins.get(premier).replace("stroke-width=\"6\"", "stroke-width=\"1\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DES-3");
    }

    /** Morsure 8 : une couleur de trait de plus, c'est-a-dire une couleur qui peut porter du sens. */
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

    /** Morsure 9 : le cliquet de poids, mordu par un dessin de plus et non par un nombre invente. */
    @Test
    void unDessinDeTropFaitEchouerLeCliquetDePoids() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        String premier = dessins.keySet().iterator().next();
        dessins.put("figure-en-trop.svg", dessins.get(premier));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0036 clause 5");
    }

    /** Morsure 10 : la clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unPerimetreDeDessinsVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(Map.of(), couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>, et elle n'est pas une redite
     * des dix precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse. C'est
     * le seul test du fichier qui prouve que {@link #dessinsDe} sait lire un repertoire de dessins
     * sur disque. Le jeu fautif porte trois fautes de forme, une par fichier - une figure sans
     * cadre de vue, une figure qui ne declare pas sa taille et sort donc au plafond de la
     * feuille quoi qu'elle soit, une figure remplie d'un aplat.
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
                .hasMessageContaining("figure-sans-taille-declaree.svg")
                .hasMessageContaining("figure-remplie.svg");
    }
}
