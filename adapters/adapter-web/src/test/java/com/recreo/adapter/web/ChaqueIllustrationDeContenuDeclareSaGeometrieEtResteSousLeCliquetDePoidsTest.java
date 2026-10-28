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
 *       <b>distingue</b> jamais deux formes entre elles a l'interieur d'un meme item, et
 *       <b>HOR-3</b> en donne le test operatoire - <i>"une image lue en niveaux de gris donne la
 *       meme reponse"</i>. Le motif est mesure sur le materiau de reprise :
 *       {@code contenu-repris/associer/cp-formes.yml} associe le glyphe carre orange a la cible
 *       "rectangle" et le glyphe carre bleu a la cible "carre" - la meme forme, deux reponses,
 *       departagees par la couleur ;</li>
 *   <li><b>FIG-4</b> (nadia-prof, meme date) : <i>"le paquet ne confond ni cercle et disque, ni
 *       figure plane et solide"</i>. C'est une clause <b>du paquet des figures planes</b>, ou
 *       <i>cercle</i> est une reponse attendue ;</li>
 *   <li><b>COU-1 a COU-4</b> (nadia-prof, 2026-09-02, arbitrage du titre du jeu d'additions et de la
 *       portee de sa clause sur la couleur), qui <b>retrecissent ce garde-fou</b> - voir le
 *       paragraphe suivant et ADR-0077 ;</li>
 *   <li><b>ADR-0036 clause 5</b> (theo-archi) : <i>"le commit qui livre la premiere image illustree
 *       pose le cliquet de poids, sur la valeur qu'il installe [...]. Tant que ce cliquet n'existe
 *       pas, rien dans ce depot n'empeche une illustration de 4 Mo."</i></li>
 * </ul>
 *
 * <p><b>Ce que ce garde-fou a interdit a tort jusqu'au 2026-09-02, et ce qu'il execute depuis
 * (ADR-0077).</b> Il refusait tout {@code fill} autre que {@code none} sous
 * {@code static/illustrations/}, en citant FIG-3 et FIG-4. nadia-prof est allee relire ses deux
 * clauses : <i>"Il cite deux clauses et n'en applique correctement aucune : il transforme FIG-3 en
 * interdiction de remplissage, et il applique FIG-4 - clause du paquet des figures planes, ou
 * cercle est une reponse attendue - a toute la population des illustrations."</i> FIG-3 est une
 * <b>clause de discrimination</b> : elle interdit a la couleur de <b>decider</b>, et le mot
 * <i>remplissage</i> n'y figure pas. Deux clauses remplacent donc l'interdiction unique, et chacune
 * derive sa population du materiau plutot que de l'enumerer (ADR-0073) :</p>
 * <ol>
 *   <li><b>COU-2, sur toutes les illustrations</b> - <i>"rendu en niveaux de gris, chaque item donne
 *       la meme reponse"</i>. Encodee en retirant la couleur plutot qu'en la convertissant en
 *       luminance : <b>deux dessins qui deviennent le meme dessin une fois la couleur retiree</b>
 *       n'etaient distingues que par une teinte, et l'item qui montre l'un ou l'autre perd sa
 *       reponse en niveaux de gris. Le retrait est le bon encodage plutot que la luminance, parce
 *       que deux teintes de meme clarte sont deja indistinguables en gris et qu'aucune formule de
 *       luminance n'a a decider d'un verdict d'architecture. <b>C'est le defaut de reprise cite
 *       ci-dessus</b> - carre orange contre carre bleu, meme forme, deux reponses - et c'est lui,
 *       et non l'absence de {@code fill}, que nadia-prof demande a un garde-fou d'executer ;</li>
 *   <li><b>FIG-4, sur la population qu'elle vise</b> - un dessin qui ne trace <b>qu'une seule forme
 *       rendue</b> <i>est</i> cette forme : le remplir en fait un autre objet geometrique, un cercle
 *       rempli est un disque. <b>La population n'est ni un nom de paquet ni un prefixe de fichier,
 *       elle est comptee dans le fichier</b> : formes hors {@code <defs>}, plus les instanciations.
 *       Ce que cette derivation vaut, mesure sur les vingt-quatre dessins servis : les douze figures
 *       planes tracent exactement une forme, les douze scenes de monnaie en tracent de cinq a
 *       dix-sept. La separation n'est pas une coincidence, elle tient a MON-1 - <b>la valeur d'un
 *       objet de monnaie n'est lisible que sur l'image</b>, donc un tel objet porte toujours son
 *       chiffre grave en plus de son contour et n'est jamais une forme nue.</li>
 * </ol>
 *
 * <p><b>Ce que ce garde-fou n'encode pas, et le motif de chaque abstention.</b> <b>COU-1</b> - le
 * remplissage est uniforme <b>par type d'objet</b> - n'est pas encodee : le <i>type</i> d'un objet
 * n'est declare nulle part dans le materiau, et un substitut calculable en serait un aveu de
 * critere invente. nadia-prof corrige au passage la formulation d'ines-design, <i>"invariant selon
 * la valeur"</i>, qui serait <b>fausse</b> recopiee ici : MON-2 montre 1 et 2 euros en pieces, 5, 10
 * et 20 en billets, donc le remplissage est correle a l'etendue de valeur - <i>"et la correlation
 * qui reste est la competence, pas une fuite"</i>. <b>COU-3</b> - le chiffre grave reste nettement
 * lisible sur la surface remplie - est <b>d'elle seule et aucun automate ne la voit</b> : un chiffre
 * qui s'efface sur son fond rend l'item <b>irrepondable</b>, elle le verifie a l'oeil item par item.
 * Rien dans ce fichier ne pretend la couvrir.
 *
 * <p><b>Un test de prediction a vecu ici le 2026-09-02 et il est retire le 2026-09-03 ; le motif est
 * ecrit plutot que le retrait laisse nu.</b> {@code lesDouzeDessinsRevisesNeSontRefusesQueParLeCliquetDePoids}
 * reproduisait la revision d'ines-design sur les vingt-quatre dessins servis, d'apres sa regle et
 * non d'apres ses fichiers, pour prouver que le retrecissement laissait passer le correctif
 * <b>avant que le correctif existe</b>. <b>Sa prediction s'est realisee, donc son objet a
 * disparu</b> : les douze dessins revises sont commites, et le corpus reel est juge par
 * {@link #chaqueDessinServiDeclareSaGeometrieEtLeCorpusResteSousLeCliquet()}, qui n'a besoin
 * d'aucune reproduction pour cela. Le fait qu'il epinglait - les 660 octets des 44 attributs - est
 * desormais porte par le cliquet lui-meme, epingle a l'octet pres par
 * {@link #leCliquetVautExactementCeQueCeLotInstalle()}.
 *
 * <p><b>Et il fallait le retirer plutot que le laisser vert</b>, sur deux motifs qu'ines-design a
 * trouves et routes sans les corriger, parce que ce fichier n'est pas son lot. <b>Son recit etait
 * devenu faux</b> - il disait <i>"elle ne les a pas commis"</i> d'un lot commite. Et <b>son
 * mecanisme ne mordait plus rien</b> : applique a des dessins deja remplis, il reecrivait
 * {@code fill=} en double sur la meme balise <b>sans une erreur</b>. Un test dont la narration ment
 * et dont le mecanisme s'applique deux fois en silence est pire qu'un test absent : il occupe la
 * place de la preuve qu'il ne fait plus. Ce que ce retrait garde, en revanche, est le mecanisme
 * lui-meme, corrige : {@link #sceneRemplieAvec(String, String)} <b>pose</b> desormais le remplissage au lieu de
 * l'ajouter, ce qui rend la double application impossible plutot que detectable.
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
     * <b>ADR-0080, garde-fou 3, la lecture DES-3 pour {@code .image-designation} (S33-PAL2-1,
     * s34).</b> {@code recreo.css} porte, depuis ADR-0080, un second plafond de rendu d'image, deux
     * fois plus haut que {@code .image-item} (22rem = 352px contre 11rem = 176px), <b>parce que
     * cette mecanique-la, seule, pose des zones que le contenu positionne sur le dessin</b> - voir
     * le commentaire de {@code .image-designation} dans {@code recreo.css}. Derive de la feuille
     * maison, jamais recopie : {@code TailleDesTextesEtBoutonsTest.proprieteEnRemSiExiste} lit la
     * meme declaration que le garde-fou 3 d'ADR-0080
     * ({@code AucuneZoneDesignableNeDescendSousLaCibleTactileTest}).
     */
    private static final int PLAFOND_DE_RENDU_DESIGNATION_EN_PIXELS = (int) Math.round(
            TailleDesTextesEtBoutonsTest.proprieteEnRemSiExiste(
                    TailleDesTextesEtBoutonsTest.blocsMaison(Path.of("src/main/resources"))
                            .get(".image-designation"),
                    "max-height").orElseThrow(() -> new AssertionError(
                            ".image-designation doit declarer max-height en rem dans recreo.css"))
                    * 16);

    /**
     * <b>Les dessins que la troisieme mecanique sert, et qui suivent donc le plafond de rendu de
     * {@code .image-designation} plutot que celui de {@code .image-item}.</b> Cette liste est
     * ecrite a la main, plutot que derivee d'un paquet de contenu : ce module (adapter-web) ne
     * depend pas d'adapter-content (sens des dependances, CLAUDE.md), et une derivation depuis le
     * contenu servi n'a donc pas sa place ici sans franchir cette frontiere - question de forme
     * technique routee a theo-archi si cette liste devait grandir au point de justifier un mecanisme
     * partage.
     *
     * <p>Elle ne peut ni grandir en silence (un dessin qui declarerait plus de 176 px sans y figurer
     * reste juge par le plafond de {@code .image-item}, donc rouge), ni etre un nom de fichier devine
     * (ADR-0077) : chaque entree nomme le paquet de designation qui reference ce dessin.</p>
     */
    private static final Set<String> DESSINS_DE_DESIGNATION = Set.of(
            // S33-PAL2-1 (s34) : les deux frises de QLM-CP-14.
            "frise-journee-ecole.svg",
            "frise-soir-routine.svg",
            // S35-QLM11-PLANCLASSE (s35) : plan-classe.svg (QLM-CP-11) entre en production dans ce
            // meme lot, avec les deux cadres agrandis par ines-design (armoire, porte).
            "plan-classe.svg",
            // S51-MAT-SYMETRIE (s52) : les onze dessins de ce1-symetrie (MAT-CE1-17), carres de 352 px,
            // figures a lignes candidates et quadrillages. Chaque entree nomme le paquet de designation
            // qui reference ce dessin.
            "symetrie-figure-a.svg",
            "symetrie-figure-b.svg",
            "symetrie-figure-c.svg",
            "symetrie-figure-d.svg",
            "symetrie-figure-e.svg",
            "symetrie-figure-f.svg",
            "symetrie-grille-a.svg",
            "symetrie-grille-b.svg",
            "symetrie-grille-c.svg",
            "symetrie-grille-d.svg",
            "symetrie-grille-e.svg");

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
     *
     * <p><b>2 685 vers 2 775</b>, ines-design, commit qui installe le remplissage de
     * {@code monnaie-meme-somme-e.svg} : six objets de monnaie sur ce dessin, six attributs de
     * remplissage de 15 octets, 90 octets de plus - <b>mesure sur cet arbre, pas recopiee</b>. ADR-0077
     * annoncait que ce cliquet ne bougerait pas et que le plus gros dessin resterait sous 2 685 ;
     * mesure sur le materiau reel, ce n'est pas le cas - <i>"90 octets au plus"</i> etait juste sur le
     * gain, faux sur la conclusion qu'il en tirait, parce que le dessin le plus lourd du corpus est
     * deja a la borne exacte du cliquet avant remplissage. Le fait n'a pas de consequence sur la
     * decision d'ADR-0077 - `theo-archi` a laisse le cliquet ou il etait faute des octets a mesurer
     * dans son propre arbre - mais la valeur qu'il annoncait pour la suite ne tenait pas, et c'est ce
     * commit qui la mesure.
     *
     * <p><b>2 775 vers 3 984</b>, lea-dev, s34, S33-PAL2-1 : {@code frise-journee-ecole.svg}, le plus
     * lourd des deux dessins du palier 2 servis (ADR-0080, {@code cp-frise-de-la-journee.yaml}),
     * trace six pictogrammes d'evenement plus les trois points pleins du gouter (<defs>/<use>,
     * DES-6) - le poids suit la geometrie, pas un choix de forme. {@code plan-classe.svg}
     * (QLM-CP-11, 3 213 octets) n'est pas servi et ne compte pas ici. Mesure sur le fichier commite,
     * commentaires compris, fins de ligne normalisees en LF, comme le reste de ce cliquet.
     *
     * <p><b>3 984 vers 4 633</b>, ines-design, s35, {@code S35-QLM14-FRISE} (`FRI-R2`) :
     * {@code nadia-prof} a refuse le pictogramme de la zone "lever" - <i>"un rectangle large avec
     * un petit rectangle et un trait, plus un trait vertical detache qui deborde au-dessus. Je ne
     * trouve pas le lit."</i> Le trait de tete de lit, une simple ligne detachee qui depassait le
     * sommier par le haut, est remplace par un montant ferme (un second rectangle), accole au
     * sommier sur la meme base : deux formes pleines de plus que la version refusee, 649 octets de
     * plus. {@code frise-journee-ecole.svg} reste le plus lourd du repertoire et cette valeur reste
     * la borne exacte du fichier commite, LF, commentaires compris.
     */
    private static final int POIDS_D_UN_DESSIN_MAXIMUM = 4633;

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
     *       troisieme fois, du commentaire et rien d'autre qui pese ;</li>
     *   <li><b>30 070 vers 30 730</b>, ines-design, le commit qui installe le remplissage retreci par
     *       ADR-0077 (COU-2, FIG-4) sur les douze dessins de {@code cp-monnaie} : 44 attributs de
     *       remplissage de 15 octets sur les 44 objets de monnaie, 660 octets, {@code fill="#e8a33d"}
     *       sur le cercle exterieur nu d'une piece et {@code fill="#cfe3ef"} sur le rectangle
     *       exterieur nu d'un billet, lisere et chiffres graves restant en contour pur. <b>Mesure sur
     *       cet arbre, jamais recopiee de l'annonce qui l'a precedee</b> - ADR-0077 annoncait 30 730,
     *       et cette annonce se confirme au chiffre pres ici, ce qui n'etait pas garanti d'avance et
     *       s'est verifie faux sur l'autre cliquet, ci-dessus.</li>
     *   <li><b>30 730 vers 37 812</b>, lea-dev, s34, S33-PAL2-1 : entree des deux dessins du
     *       palier 2 effectivement servis (ADR-0080, {@code cp-frise-de-la-journee.yaml}) -
     *       {@code frise-journee-ecole.svg} (3 984 octets, voir {@link #POIDS_D_UN_DESSIN_MAXIMUM})
     *       et {@code frise-soir-routine.svg} (3 098 octets), 7 082 octets au total. {@code
     *       plan-classe.svg} (QLM-CP-11, 3 213 octets) n'est pas servi - ses libelles de zone
     *       debordent de leur bouton a l'ecran de manche, constate au jar et route a
     *       {@code sofia-sm} - et n'entre donc pas dans ce total.</li>
     *   <li><b>37 812 vers 38 461</b>, ines-design, s35, {@code S35-QLM14-FRISE} (`FRI-R2`) : le
     *       redessin du pictogramme "lever" ajoute 649 octets a {@code frise-journee-ecole.svg}
     *       (voir {@link #POIDS_D_UN_DESSIN_MAXIMUM}) et rien d'autre dans le repertoire ; le total
     *       monte du meme montant.</li>
     *   <li><b>38 461 vers 42 336</b>, lea-dev, s35, {@code S35-QLM11-PLANCLASSE} : entree de
     *       {@code plan-classe.svg} (QLM-CP-11, 3 875 octets mesures fins de ligne normalisees en
     *       LF), premiere livraison reelle de cette ligne - geometrie livree par ines-design, cadres
     *       {@code armoire} et {@code porte} agrandis. Il ne devient pas le plus gros dessin du
     *       repertoire : {@code frise-journee-ecole.svg} le reste (voir
     *       {@link #POIDS_D_UN_DESSIN_MAXIMUM}, inchange).</li>
     *   <li><b>42 336 vers 51 307</b>, lea-dev, s39, {@code S39-SOLIDES} : quinze dessins neufs sous
     *       {@code solide-*.svg} (cube, pave droit, boule, cylindre, cone, trois exemples chacun),
     *       8 971 octets fins de ligne normalisees en LF, mesure exacte sur l'arbre du lot. Aucun ne
     *       devient le plus gros dessin du repertoire : {@code frise-journee-ecole.svg} le reste
     *       (voir {@link #POIDS_D_UN_DESSIN_MAXIMUM}, inchange).</li>
     *   <li><b>51 307 vers 55 212</b>, lea-dev, s52, {@code S51-MAT-MULTIPLICATION} : trois reseaux
     *       de points neufs sous {@code reseau-points-*.svg} (un {@code <circle>} par point, jamais
     *       un chemin unique : avis de faisabilite de s52, 4.2), 934, 1 226 et 1 745 octets, soit
     *       3 905 octets fins de ligne normalisees en LF, mesure exacte sur l'arbre du lot. Aucun ne
     *       devient le plus gros dessin du repertoire : {@code frise-journee-ecole.svg} le reste
     *       (voir {@link #POIDS_D_UN_DESSIN_MAXIMUM}, inchange).</li>
     *   <li><b>55 212 vers 69 203</b>, lea-dev, s52, {@code S51-MAT-SYMETRIE} : onze dessins neufs
     *       de designation sous {@code symetrie-*.svg} (six figures a lignes candidates de 740 a 751
     *       octets, cinq quadrillages de 1 442 a 2 215 octets), soit 13 991 octets fins de ligne
     *       normalisees en LF, mesure exacte sur l'arbre du lot. Aucun ne devient le plus gros dessin
     *       du repertoire : {@code frise-journee-ecole.svg} le reste (voir
     *       {@link #POIDS_D_UN_DESSIN_MAXIMUM}, inchange).</li>
     *   <li><b>69 203 vers 96 873</b>, lea-dev, s57 palier 5, MAT-CE1-11 : douze cadrans neufs sous
     *       {@code horloge-*.svg}, 2 303 a 2 308 octets chacun, soit 27 670
     *       octets fins de ligne normalisees en LF, mesure exacte sur l'arbre du lot, premier jet.
     *       Aucun ne devient le plus gros dessin du repertoire : {@code frise-journee-ecole.svg} le
     *       reste (voir {@link #POIDS_D_UN_DESSIN_MAXIMUM}, inchange). Ce releve est couvert par
     *       l'accord de l'utilisatrice du 2026-10-28 (DEC-0120, A.284) ; le code l'avait pose
     *       avant cet accord.</li>
     * </ol>
     */
    private static final int POIDS_TOTAL_MAXIMUM = 96873;

    /**
     * Les deux teintes d'ines-design, employees ici <b>comme materiau de mutation</b> et pour
     * aucune autre raison : une piece et un billet, deux valeurs litterales dont la seule propriete
     * utile aux morsures est qu'elles different l'une de l'autre. Aucune clause de ce fichier ne
     * les juge - COU-1 n'est pas encodable et COU-3 est l'oeil de nadia-prof (ADR-0077).
     */
    private static final String TEINTE_DE_PIECE = "#e8a33d";

    private static final String TEINTE_DE_BILLET = "#cfe3ef";

    private static final String VUE = "viewBox";

    private static final String REMPLISSAGE = "fill";

    private static final String TRAIT = "stroke";

    private static final String EPAISSEUR_DE_TRAIT = "stroke-width";

    /** DES-4 : ce qui decouplerait l'epaisseur du trait de l'echelle du dessin. */
    private static final String EFFET_VECTORIEL = "non-scaling-stroke";

    /**
     * FIG-4, population derivee : les <b>sept</b> elements de forme du langage SVG, plus
     * {@code use} qui en instancie un. C'est l'ensemble complet du langage, pas un echantillon du
     * depot - un dessin ne peut tracer une forme par aucun autre moyen -, et c'est ce qui separe
     * cette liste d'un critere enumere : elle ne nomme aucun objet de recreo.
     */
    private static final List<String> FORMES_SVG =
            List.of("circle", "ellipse", "line", "path", "polygon", "polyline", "rect", "use");

    private static final String DEBUT_DE_DEFINITIONS = "<defs";

    private static final String FIN_DE_DEFINITIONS = "</defs>";

    private static final String DEBUT_DE_COMMENTAIRE = "<!--";

    private static final String FIN_DE_COMMENTAIRE = "-->";

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
        verifier(dessins, couleurDuTexte, POIDS_D_UN_DESSIN_MAXIMUM, POIDS_TOTAL_MAXIMUM);
    }

    /**
     * Le meme juge, cliquets passes en parametres. Ils le sont pour qu'une morsure qui <b>ajoute</b>
     * un dessin ou des octets a un corpus reel puisse etre jugee sur <b>toutes les autres
     * clauses</b> : sans cela, la seule chose qu'elle prouverait serait le poids.
     *
     * <p>Le cliquet ne perd rien a etre parametre : {@link #leCliquetVautExactementCeQueCeLotInstalle()}
     * continue de l'epingler a la taille exacte de ce qui est commite, et aucun plafond de confort
     * ne peut donc entrer par ici.
     */
    static void verifier(Map<String, String> dessins, String couleurDuTexte,
                         int poidsDUnDessinMaximum, int poidsTotalMaximum) {
        if (dessins.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun dessin d'item sous " + ILLUSTRATIONS
                    + ", les clauses de geometrie et le cliquet de poids ne portent alors sur rien");
        }
        List<String> fautes = new ArrayList<>();
        Set<String> couleursDeTrait = new LinkedHashSet<>();
        Map<String, String> squelettes = new LinkedHashMap<>();
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
            int plafondApplicable = DESSINS_DE_DESIGNATION.contains(nom)
                    ? PLAFOND_DE_RENDU_DESIGNATION_EN_PIXELS : PLAFOND_DE_RENDU_EN_PIXELS;
            if (hauteur > plafondApplicable) {
                fautes.add(nom + " : hauteur declaree de " + hauteur + " px, au-dessus du plafond"
                        + " de rendu de " + plafondApplicable + " px. La feuille rogne :"
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

            if (formesRendues(source) == 1) {
                for (String remplissage : valeursDeLAttribut(source, REMPLISSAGE)) {
                    if (!"none".equals(remplissage)) {
                        fautes.add(nom + " : le dessin ne trace qu'une seule forme rendue, il est"
                                + " donc cette forme, et la remplir de '" + remplissage + "' en"
                                + " fait un autre objet geometrique - un cercle rempli n'est plus"
                                + " un cercle mais un disque (FIG-4). Population derivee du fichier"
                                + " lui-meme, jamais d'un nom de paquet ni d'un prefixe de nom de"
                                + " fichier (ADR-0077)");
                    }
                }
            }

            String deja = squelettes.putIfAbsent(sansCouleur(source), nom);
            if (deja != null) {
                fautes.add(nom + " et " + deja + " : les deux dessins sont le meme dessin une fois"
                        + " la couleur retiree. Ce qui les distingue est alors une teinte seule, et"
                        + " l'item qui montre l'un ou l'autre cesse de donner la meme reponse lu en"
                        + " niveaux de gris (COU-2, HOR-3, FIG-3)");
            }

            // S33-PAL2-1 (s34) : "none" n'est pas une couleur, c'est l'absence de trait - le motif
            // exact qui exempte deja "none" du cote remplissage, quelques lignes au-dessus (FIG-4).
            // frise-journee-ecole.svg pose stroke="none" sur les trois points pleins du gouter
            // (fill="#1c2733") pour empecher le trait par defaut du groupe de s'y ajouter ; ce n'est
            // pas une seconde teinte de trait au sens de FIG-3/DES-5.
            for (String trait : valeursDeLAttribut(source, TRAIT)) {
                if (!"none".equals(trait)) {
                    couleursDeTrait.add(trait);
                }
            }

            if (poids > poidsDUnDessinMaximum) {
                fautes.add(nom + " : " + poids + " octets, au-dessus du cliquet de "
                        + poidsDUnDessinMaximum + " octets pose par le lot qui a livre le"
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

        if (total > poidsTotalMaximum) {
            fautes.add("les illustrations de contenu pesent " + total + " octets, au-dessus du"
                    + " cliquet de " + poidsTotalMaximum + " octets. Le deplacer demande un motif"
                    + " ecrit et une valeur mesuree, dans le meme commit (ADR-0036 clause 5,"
                    + " critere de sortie 11 du planning de s25)");
        }

        if (!fautes.isEmpty()) {
            throw new AssertionError("dessins d'item : " + fautes);
        }
    }

    /** Ce qui est enleve entre deux marques, autant de fois qu'il y a d'occurrences. */
    private static String sansLesBlocs(String source, String debut, String fin) {
        StringBuilder reste = new StringBuilder(source);
        int ouverture;
        while ((ouverture = reste.indexOf(debut)) >= 0) {
            int fermeture = reste.indexOf(fin, ouverture);
            if (fermeture < 0) {
                return reste.substring(0, ouverture);
            }
            reste.delete(ouverture, fermeture + fin.length());
        }
        return reste.toString();
    }

    /**
     * FIG-4, la population : le nombre de formes que le dessin <b>rend</b>. Les definitions sont
     * retirees d'abord - un glyphe pose en {@code <defs>} n'est pas rendu la ou il est ecrit, il
     * l'est la ou il est instancie - et les commentaires ensuite, pour qu'une prose qui parlerait
     * d'un cercle ne compte pas pour un cercle.
     *
     * <p>Le comptage exige que le nom d'element soit suivi d'un blanc, d'une barre oblique ou d'un
     * chevron fermant : sans quoi {@code <rect} compterait aussi pour {@code <rectangle}, et la
     * derivation dependrait de noms d'elements que le langage n'a pas.
     */
    static int formesRendues(String source) {
        String rendu = sansLesBlocs(
                sansLesBlocs(source, DEBUT_DE_DEFINITIONS, FIN_DE_DEFINITIONS),
                DEBUT_DE_COMMENTAIRE, FIN_DE_COMMENTAIRE);
        int comptees = 0;
        for (String forme : FORMES_SVG) {
            String marque = "<" + forme;
            int position = 0;
            while ((position = rendu.indexOf(marque, position)) >= 0) {
                int apres = position + marque.length();
                if (apres < rendu.length() && (Character.isWhitespace(rendu.charAt(apres))
                        || rendu.charAt(apres) == '/' || rendu.charAt(apres) == '>')) {
                    comptees++;
                }
                position = apres;
            }
        }
        return comptees;
    }

    /**
     * COU-2, le test operatoire de HOR-3 : le dessin prive de sa couleur. Les valeurs de
     * {@code fill} et de {@code stroke} sont videes, les commentaires retires - deux dessins
     * identiques ne cessent pas de l'etre parce que leur en-tete raconte autre chose - et les
     * blancs ramenes a un seul, une indentation n'etant pas un trait.
     *
     * <p><b>La couleur est retiree, pas convertie en luminance</b>, et c'est le choix conservateur :
     * deux teintes de meme clarte sont deja indistinguables en niveaux de gris, et une formule de
     * luminance ecrite ici deciderait d'un verdict d'architecture sur un arrondi.
     */
    static String sansCouleur(String source) {
        String nu = sansLesBlocs(source, DEBUT_DE_COMMENTAIRE, FIN_DE_COMMENTAIRE);
        nu = valeurVidee(valeurVidee(nu, REMPLISSAGE), TRAIT);
        StringBuilder serre = new StringBuilder(nu.length());
        boolean blancPrecedent = false;
        for (int rang = 0; rang < nu.length(); rang++) {
            char lettre = nu.charAt(rang);
            if (Character.isWhitespace(lettre)) {
                blancPrecedent = true;
                continue;
            }
            if (blancPrecedent && serre.length() > 0) {
                serre.append(' ');
            }
            blancPrecedent = false;
            serre.append(lettre);
        }
        return serre.toString();
    }

    /**
     * Les valeurs d'un attribut, videes sur place. Meme convention que
     * {@link #valeursDeLAttribut(String, String)} : l'occurrence n'est retenue que precedee d'un
     * blanc, ce qui evite que {@code stroke} attrape {@code stroke-width}.
     */
    private static String valeurVidee(String fragment, String attribut) {
        StringBuilder vide = new StringBuilder(fragment);
        String marque = attribut + "=\"";
        int position = 0;
        while ((position = vide.indexOf(marque, position)) >= 0) {
            int debut = position + marque.length();
            if (position > 0 && !Character.isWhitespace(vide.charAt(position - 1))) {
                position = debut;
                continue;
            }
            int fin = vide.indexOf("\"", debut);
            if (fin < 0) {
                return vide.toString();
            }
            vide.delete(debut, fin);
            position = debut + 1;
        }
        return vide.toString();
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

    static int poidsTotal(Map<String, String> dessins) {
        int total = 0;
        for (String source : dessins.values()) {
            total += source.getBytes(StandardCharsets.UTF_8).length;
        }
        return total;
    }

    static int poidsDuPlusGros(Map<String, String> dessins) {
        int plusGros = 0;
        for (String source : dessins.values()) {
            plusGros = Math.max(plusGros, source.getBytes(StandardCharsets.UTF_8).length);
        }
        return plusGros;
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
        int total = poidsTotal(dessins);
        int plusGros = poidsDuPlusGros(dessins);
        assertThat(total)
                .as("ADR-0036 clause 5 : le cliquet total vaut exactement le poids installe, fins"
                        + " de ligne normalisees en LF")
                .isEqualTo(POIDS_TOTAL_MAXIMUM);
        assertThat(plusGros)
                .as("ADR-0036 clause 5 : le cliquet par dessin vaut exactement le plus gros dessin"
                        + " installe, fins de ligne normalisees en LF")
                .isEqualTo(POIDS_D_UN_DESSIN_MAXIMUM);
    }

    /**
     * Une scene servie <b>rendue dans une teinte</b> : chaque forme du dessin - hors definitions -
     * porte, apres cet appel, <b>exactement un</b> remplissage, et c'est celui qu'on demande.
     *
     * <p><b>La forme de cette fonction est le correctif d'un defaut reel, et elle merite d'etre
     * lue.</b> Sa premiere ecriture, le 2026-09-02, <b>ajoutait</b> l'attribut sans regarder s'il
     * etait deja la. C'etait exact tant que les dessins servis n'avaient aucun remplissage ; le
     * 2026-09-03, ines-design a commite les douze dessins remplis, et le meme mecanisme s'est mis a
     * produire {@code fill=} <b>en double sur la meme balise, sans une erreur</b>. Elle l'a trouve
     * et route sans le corriger, ce fichier n'etant pas son lot.
     *
     * <p><b>Le remede n'est pas de detecter la double application, c'est de la rendre impossible</b>
     * - une detection aurait ete une branche de plus a ne jamais exercer, et un garde-fou dont la
     * moitie ne s'execute jamais est ce que ce depot appelle decoratif. Cette fonction <b>pose</b>
     * une valeur au lieu de l'ajouter : elle remplace le remplissage present, ou l'ecrit s'il est
     * absent. Elle est donc <b>totale et idempotente</b> - le meme appel deux fois de suite rend le
     * meme texte, sur un dessin nu comme sur un dessin deja rempli - et les morsures qui s'en
     * servent disent la meme chose avant et apres l'arrivee des dessins remplis, au lieu de devenir
     * vraies pour une autre raison que celle qu'elles annoncent.
     */
    static String sceneRemplieAvec(String source, String teinte) {
        int definitions = source.indexOf(FIN_DE_DEFINITIONS);
        int depart = definitions < 0 ? 0 : definitions + FIN_DE_DEFINITIONS.length();
        StringBuilder remplie = new StringBuilder(source.substring(0, depart));
        int position = depart;
        while (position < source.length()) {
            int ouverture = source.indexOf('<', position);
            int fermeture = ouverture < 0 ? -1 : source.indexOf('>', ouverture);
            if (fermeture < 0) {
                remplie.append(source, position, source.length());
                break;
            }
            remplie.append(source, position, ouverture);
            String balise = source.substring(ouverture, fermeture);
            boolean fermetureAuto = balise.endsWith("/");
            String corps = fermetureAuto ? balise.substring(0, balise.length() - 1) : balise;
            remplie.append(estUneForme(corps) ? avecLeRemplissage(corps, teinte) : corps)
                    .append(fermetureAuto ? "/" : "").append('>');
            position = fermeture + 1;
        }
        return remplie.toString();
    }

    /** Une balise de forme au sens du langage, jamais la racine du document ni une instanciation. */
    private static boolean estUneForme(String balise) {
        for (String forme : FORMES_SVG) {
            if (!"use".equals(forme) && balise.startsWith("<" + forme)) {
                return true;
            }
        }
        return false;
    }

    /** Le remplissage <b>pose</b> : la valeur presente est remplacee, l'absente est ecrite. */
    private static String avecLeRemplissage(String balise, String teinte) {
        String marque = REMPLISSAGE + "=\"";
        int declaration = balise.indexOf(marque);
        if (declaration < 0) {
            return balise + " " + marque + teinte + "\"";
        }
        int debut = declaration + marque.length();
        return balise.substring(0, debut) + teinte + balise.substring(balise.indexOf('"', debut));
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
     * Le premier dessin servi qui rend le nombre de formes demande. Aucune morsure de ce fichier ne
     * nomme un fichier d'illustration : la population de FIG-4 est derivee, et les morsures qui la
     * mordent le sont aussi, sans quoi elles prouveraient sur une liste ce que la regle calcule.
     */
    private static Map.Entry<String, String> premierDessinA(Map<String, String> dessins,
                                                            boolean uneSeuleForme) {
        for (Map.Entry<String, String> dessin : dessins.entrySet()) {
            if ((formesRendues(dessin.getValue()) == 1) == uneSeuleForme) {
                return dessin;
            }
        }
        throw new AssertionError("aucun dessin servi ne rend " + (uneSeuleForme ? "une seule forme"
                : "plusieurs formes") + " : la morsure ne prouverait rien (ADR-0051)");
    }

    /**
     * FIG-4 : la clause vaut toujours, et sur la population pour laquelle nadia-prof l'a ecrite.
     * <i>"Remplir un cercle en fait un disque, un autre objet geometrique"</i>, verifie par
     * ines-design en faisant rendre un carre et un cercle, confirme par nadia-prof en COU-4 -
     * <i>"cp-figures-planes n'est pas touche"</i>. Le dessin mordu est <b>choisi par le calcul de la
     * population</b>, pas par son nom.
     */
    @Test
    void unDessinDUneSeuleFormeQuOnRemplitFaitEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        Map.Entry<String, String> forme = premierDessinA(dessins, true);
        dessins.put(forme.getKey(), forme.getValue().replace("fill=\"none\"", "fill=\"#e8a33d\""));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FIG-4")
                .hasMessageContaining("disque");
    }

    /**
     * <b>Le retrecissement fait ce qu'il annonce</b>, et cette morsure est celle qui le prouve : une
     * scene remplie <b>passe</b>. Sans elle, les deux clauses precedentes se contenteraient de
     * refuser moins souvent sans que rien ne dise qu'elles laissent passer le correctif demande.
     */
    @Test
    void uneSceneDePlusieursObjetsQuOnRemplitResteAcceptee() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        Map.Entry<String, String> scene = premierDessinA(dessins, false);
        dessins.put(scene.getKey(), sceneRemplieAvec(scene.getValue(), TEINTE_DE_PIECE));

        verifier(dessins, couleurDuTexte(FEUILLE_MAISON), poidsDuPlusGros(dessins),
                poidsTotal(dessins));
    }

    /**
     * <b>Le mecanisme de mutation est mordu comme le reste, et pas seulement decrit.</b> Une
     * fonction de mutation qu'aucun test n'exerce est un endroit ou un defaut vit sans temoin :
     * celui du 2026-09-02 - l'attribut ajoute au lieu d'etre pose - a survecu parce que les trois
     * morsures qui s'en servaient regardaient le verdict du juge et jamais le texte produit.
     *
     * <p>Ce que ce test tient, sur un dessin servi reel : appliquer la mutation a un dessin
     * <b>deja mute</b> rend <b>exactement le meme texte</b> que l'appliquer une fois. C'est la
     * preuve complete de la propriete et non un indice - une fonction qui ajouterait un second
     * attribut rendrait un texte plus long, donc different. Et la seconde egalite dit l'autre
     * moitie : une autre teinte <b>remplace</b> la premiere au lieu de s'y ajouter, donc la
     * mutation ne depend pas de l'etat dans lequel elle trouve le dessin.
     */
    @Test
    void laMutationDeRemplissagePoseLaTeinteAuLieuDeLAjouter() {
        Map.Entry<String, String> scene = premierDessinA(dessinsDe(ILLUSTRATIONS), false);
        String uneFois = sceneRemplieAvec(scene.getValue(), TEINTE_DE_PIECE);
        String deuxFois = sceneRemplieAvec(uneFois, TEINTE_DE_PIECE);

        assertThat(deuxFois)
                .as("la mutation est idempotente : sur un dessin deja rempli, elle rend le meme"
                        + " texte au lieu d'ajouter un second attribut")
                .isEqualTo(uneFois);
        assertThat(sceneRemplieAvec(uneFois, TEINTE_DE_BILLET))
                .as("et elle reste totale : une seconde teinte remplace la premiere, elle ne s'y"
                        + " ajoute pas")
                .isEqualTo(sceneRemplieAvec(scene.getValue(), TEINTE_DE_BILLET));
    }

    /**
     * COU-2, la clause que nadia-prof demande a un garde-fou d'executer a la place de l'absence de
     * remplissage : <b>ici, la couleur decide reellement</b>. Deux dessins servis que seule une
     * teinte separe sont, en niveaux de gris, le meme dessin - et les deux items qui les montrent
     * cessent d'avoir des reponses distinguables. C'est le defaut de {@code cp-formes.yml} porte sur
     * du materiau reel : la meme forme, deux cibles, departagees par la couleur.
     *
     * <p><b>La mutation a ete corrigee avant d'etre commise, et le motif reste ecrit</b> : elle
     * ajoutait d'abord un remplissage a une scene qui n'en avait pas, et le garde-fou restait vert -
     * <b>a raison</b>. Une surface remplie et une surface vide ne se distinguent pas par une teinte,
     * elles se distinguent en niveaux de gris aussi bien qu'en couleur, et COU-2 n'a rien a en dire.
     * Ce qui decide vraiment est <b>deux fois la meme image dans deux teintes</b> : c'est ce que la
     * mutation fait maintenant, et c'est la forme exacte du defaut de {@code cp-formes.yml}.
     *
     * <p>Les cliquets sont ceux du corpus mute, mesures et non elargis : la morsure doit rougir sur
     * COU-2, pas sur le poids d'un dessin de plus.
     */
    @Test
    void deuxDessinsQueSeuleLaCouleurDistingueFontEchouerLeGardeFou() {
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(ILLUSTRATIONS));
        Map.Entry<String, String> scene = premierDessinA(dessins, false);
        dessins.put(scene.getKey(), sceneRemplieAvec(scene.getValue(), TEINTE_DE_PIECE));
        dessins.put("monnaie-meme-scene-autre-teinte.svg",
                sceneRemplieAvec(scene.getValue(), TEINTE_DE_BILLET));

        assertThatThrownBy(() -> verifier(dessins, couleurDuTexte(FEUILLE_MAISON),
                poidsDuPlusGros(dessins), poidsTotal(dessins)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("COU-2")
                .hasMessageContaining("niveaux de gris");
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
