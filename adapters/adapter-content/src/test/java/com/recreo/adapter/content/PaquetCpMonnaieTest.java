package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S25-MON-1, paquet illustre "cp-monnaie" (ADR-0073). Conditions de nadia-prof du 2026-08-31 :
 * MON-1 a MON-5 (arbitrage des seize lignes, section 2.2) et CON-1 a CON-5 (arbitrage du palier 1,
 * section 3 et son amendement du meme jour).
 *
 * <p><b>Ce que ce fichier verifie mecaniquement, et ce qu'il ne verifie pas.</b> Deux conditions se
 * comptent sur le contenu servi et sont testees ici : MON-2 (les valeurs montrees sont celles qui
 * existent) et MON-4 (au plus 20 euros, aucun centime). Deux autres se lisent sur le texte et sont
 * testees ici parce qu'elles sont exactement verifiables sans heuristique : MON-1 - aucune valeur
 * n'est ecrite dans un enonce - et sa cousine CON-2, dont nadia-prof ecrit qu'elle est <b>la seule
 * des quatre a etre automatisable</b>, "la presence dans un enonce d'un mot d'une liste declaree -
 * piece de, un nom de couleur - se cherche". MON-3 est comptee sur une classification declaree ici,
 * pas devinee : le compte est mecanique, l'appartenance d'un item a la famille "composer" est un
 * jugement, et il est ecrit plutot que deduit.
 *
 * <p><b>Ce qui reste dans la seule main de nadia-prof</b>, et qu'aucun automate ne prendra :
 * MON-5 (la situation est-elle celle d'un enfant de six ans), CON-1 (en effacant l'image, la
 * reponse reste-t-elle trouvable), CON-3 (la donnee est-elle dans l'image et dans le texte a la
 * fois), CON-4 (le controle exhaustif par masquage, item par item) et CON-5 (le texte alternatif
 * livre-t-il la reponse). Elle l'ecrit elle-meme : "CON-1, CON-3 et CON-4 restent dans ma main", et
 * CON-5 "n'est pas automatisable". Ce fichier ne fait pas semblant de les couvrir.
 *
 * <p><b>Ce qu'aucun test ne peut dire non plus</b> : qu'un enfant de CP reconnaisse les disques et
 * les rectangles traces comme des pieces et des billets, et les chiffres graves comme les chiffres
 * qu'on lui apprend a ecrire. C'est un jugement pedagogique, ines-design l'a route a nadia-prof
 * dans les memes termes pour ses glyphes, et il n'est pas repris ici.
 */
class PaquetCpMonnaieTest {

    private static final String PAQUET = "cp-monnaie";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 12;

    /**
     * MON-2 : les valeurs qui existent reellement, dans les mots de nadia-prof - "1, 2, 5, 10, 20
     * centimes puis 1 et 2 euros en pieces ; 5, 10, 20 euros en billets". MON-4 retire les centimes
     * pour ce paquet-ci : il n'en reste que cinq, et c'est cette intersection que le paquet sert.
     */
    private static final Set<Integer> VALEURS_QUI_EXISTENT_SANS_CENTIME = Set.of(1, 2, 5, 10, 20);

    /** MON-4 : "au plus 20 euros, sans centimes". */
    private static final int SOMME_MAXIMALE = 20;

    /**
     * MON-3 : les items dont la reponse demande de composer une somme avec les valeurs montrees.
     * L'appartenance est declaree par l'enonce plutot que devinee sur la forme de la reponse : une
     * reponse qui s'ecrit comme une somme n'est pas la meme chose qu'une reponse qui demande d'en
     * composer une, et confondre les deux ferait passer pour mecanique un jugement qui ne l'est pas.
     */
    /**
     * L'espace insecable que les enonces de ce paquet posent devant leur point d'interrogation
     * (TYP-1, comme cm1-problemes-durees depuis s23). Elle est ecrite en echappement et non en
     * caractere : un caractere invisible dans une source Java se compare mal a l'oeil, et c'est
     * exactement ce que ce garde-fou a trouve en rougissant sur zero item la premiere fois.
     */
    private static final String INSECABLE = "\u00a0";

    private static final Set<String> ENONCES_QUI_DEMANDENT_DE_COMPOSER = Set.of(
            "Comment faire la même somme que le billet" + INSECABLE + "?",
            "Comment faire la même somme que la pièce" + INSECABLE + "?");

    /**
     * MON-1 et CON-2 : les mots qu'un enonce ne prononce jamais. Les six premiers nomment une valeur
     * ou une unite - les ecrire ferait la reconnaissance a la place de l'enfant. Les suivants
     * decrivent une quantite ou une couleur de ce que l'image montre, ce que CON-2 interdit.
     * Cette liste n'est pas un perimetre, c'est un jeu de temoins : MON-1 est tenue par le test des
     * chiffres, qui lui est exhaustif.
     */
    private static final Set<String> MOTS_INTERDITS_DANS_UN_ENONCE = Set.of(
            "euro", "euros", "centime", "centimes", "piece de", "billet de",
            "deux", "trois", "quatre", "cinq", "dix", "vingt",
            "jaune", "jaunes", "dore", "doree", "argente", "argentee", "gris", "grise",
            "rond", "ronde", "ronds", "rondes", "rectangle", "rectangles");

    /** Toute suite de chiffres, ou qu'elle soit dans la chaine. */
    private static final Pattern UN_CHIFFRE = Pattern.compile("\\d");

    /** Un terme d'une somme ecrite en proposition : "5 + 2 + 2 + 1". */
    private static final Pattern TERME = Pattern.compile("\\d+");

    /** Une virgule decimale, donc des centimes. La virgule de ponctuation n'en est pas une. */
    private static final Pattern VIRGULE_DECIMALE = Pattern.compile("\\d\\s*,\\s*\\d");

    /**
     * MON-1, et c'est la condition bloquante du paquet : aucun enonce n'ecrit de valeur. Le test est
     * plus large que la condition et volontairement : il refuse <b>tout</b> chiffre, parce qu'un
     * enonce qui en porterait un dirait deja quelque chose que l'enfant doit lire sur l'image.
     */
    @Test
    void aucunEnonceNEcritLeMoindreChiffre() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(UN_CHIFFRE.matcher(item.enonce()).find())
                    .as("MON-1 : l'enonce \"%s\" ecrit un chiffre. La valeur se lit sur l'image, et"
                            + " nulle part ailleurs - un enonce qui l'ecrit a deja fait la"
                            + " reconnaissance a la place de l'enfant", item.enonce())
                    .isFalse();
        }
    }

    /**
     * MON-1 et CON-2 : un enonce ne nomme pas davantage une valeur en toutes lettres, ni une
     * quantite, ni une couleur de ce que l'image montre.
     */
    @Test
    void aucunEnonceNeNommeUneValeurUneQuantiteOuUneCouleur() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce().toLowerCase(Locale.FRENCH);
            for (String interdit : MOTS_INTERDITS_DANS_UN_ENONCE) {
                assertThat(enonce.contains(interdit))
                        .as("MON-1 et CON-2 : l'enonce \"%s\" contient \"%s\". L'enonce pose la"
                                + " question ; l'image porte la donnee", item.enonce(), interdit)
                        .isFalse();
            }
        }
    }

    /**
     * MON-2 : chaque valeur montree existe. Le paquet ne dit pas ce qu'il dessine - c'est le dessin
     * qui le dit -, mais toute valeur qui apparait dans une proposition est une valeur que l'image
     * montre ou une somme de valeurs montrees. Ce test lit donc les <b>termes</b> des propositions,
     * qui sont les pieces et les billets nommes un par un, et jamais leur total.
     */
    @Test
    void chaqueTermeDUnePropositionEstUneValeurQuiExiste() {
        for (Question item : tousLesItems(paquet())) {
            if (!ENONCES_QUI_DEMANDENT_DE_COMPOSER.contains(item.enonce())) {
                continue;
            }
            for (String proposition : propositions(item)) {
                for (int terme : termes(proposition)) {
                    assertThat(VALEURS_QUI_EXISTENT_SANS_CENTIME)
                            .as("MON-2 : la proposition \"%s\" nomme %d, qui n'est ni une piece ni"
                                    + " un billet existant. Aucune valeur inventee, jamais",
                                    proposition, terme)
                            .contains(terme);
                }
            }
        }
    }

    /**
     * MON-4 : aucune somme ne depasse 20 euros. Le test porte sur les trois propositions et non sur
     * la seule bonne reponse - un distracteur au-dessus de la borne demanderait a l'enfant de
     * calculer au-dela de ce qu'il sait faire pour l'ecarter.
     */
    @Test
    void aucunePropositionNeDepasseVingtEuros() {
        for (Question item : tousLesItems(paquet())) {
            for (String proposition : propositions(item)) {
                int somme = termes(proposition).stream().mapToInt(Integer::intValue).sum();
                assertThat(somme)
                        .as("MON-4 : la proposition \"%s\" de l'item \"%s\" vaut %d, au-dela de la"
                                + " limite que le CP sait calculer", proposition, item.enonce(), somme)
                        .isLessThanOrEqualTo(SOMME_MAXIMALE);
            }
        }
    }

    /**
     * MON-4 : aucun centime nulle part, ni dans un enonce, ni dans une proposition, ni dans un
     * indice, ni dans un texte alternatif. Deux formes sont refusees separement : le mot, et la
     * virgule decimale entre deux chiffres - c'est celle-la qui passerait sans qu'on la remarque.
     * La virgule de ponctuation d'une phrase n'est pas concernee, et c'est pourquoi le motif exige
     * un chiffre de chaque cote.
     */
    @Test
    void aucunTexteServiNeMentionneUnCentime() {
        for (Question item : tousLesItems(paquet())) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            item.indice().ifPresent(textes::add);
            item.illustrationTexteAlternatif().ifPresent(textes::add);
            for (String texte : textes) {
                assertThat(texte.toLowerCase(Locale.FRENCH))
                        .as("MON-4 : \"%s\" mentionne un centime", texte)
                        .doesNotContain("centime");
                assertThat(VIRGULE_DECIMALE.matcher(texte).find())
                        .as("MON-4 : \"%s\" porte une virgule decimale, donc des centimes", texte)
                        .isFalse();
            }
        }
    }

    /**
     * MON-3, la condition sur laquelle nadia-prof a rouvert cette ligne : au moins un tiers des
     * items demandent de composer une somme avec les valeurs montrees. Unite de compte : l'item.
     */
    @Test
    void auMoinsUnTiersDesItemsDemandeDeComposerUneSomme() {
        List<Question> items = tousLesItems(paquet());
        long composent = items.stream()
                .filter(item -> ENONCES_QUI_DEMANDENT_DE_COMPOSER.contains(item.enonce()))
                .count();
        assertThat(composent * 3)
                .as("MON-3 : %d items sur %d demandent de composer une somme, sous le plancher d'un"
                        + " tiers. C'est la condition qui distingue ce paquet d'un jeu de calcul",
                        composent, items.size())
                .isGreaterThanOrEqualTo(items.size());
    }

    /**
     * MON-3, seconde moitie : "et non de faire une addition qui serait juste avec n'importe quels
     * nombres". Un item qui demande de composer ne dit pas quelle somme atteindre - elle se lit sur
     * l'image. C'est ce qui empeche la reponse d'etre trouvable sans regarder, et c'est aussi la
     * forme que CON-1 exige.
     */
    @Test
    void aucunItemQuiDemandeDeComposerNEcritLaSommeAAtteindre() {
        for (Question item : tousLesItems(paquet())) {
            if (!ENONCES_QUI_DEMANDENT_DE_COMPOSER.contains(item.enonce())) {
                continue;
            }
            assertThat(UN_CHIFFRE.matcher(item.enonce()).find())
                    .as("MON-3 : \"%s\" ecrit la somme a atteindre. Elle doit se lire sur l'image,"
                            + " sinon l'addition serait juste avec n'importe quels nombres",
                            item.enonce())
                    .isFalse();
        }
    }

    /**
     * ADR-0073 clauses 1 et 5, portees ici sur ce paquet-ci : chaque item declare son illustration
     * et son texte alternatif. Le garde-fou de balayage
     * {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} le verifie sur tous les paquets du
     * depot ; ce test-ci l'exige sur celui-la, parce qu'un paquet illustre dont un item perdrait sa
     * cle deviendrait un item sans image sans que sa ligne cesse d'etre annoncee couverte.
     */
    @Test
    void chaqueItemDeclareSonIllustrationEtSonTexteAlternatif() {
        List<String> identifiants = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.illustration())
                    .as("ADR-0073 clause 1 : l'item \"%s\" ne declare aucune illustration, et le"
                            + " paquet entier ne vaut que par ce que l'enfant regarde", item.enonce())
                    .isPresent();
            assertThat(item.illustrationTexteAlternatif())
                    .as("ADR-0073 clause 5 : l'item \"%s\" ne declare aucun texte alternatif",
                            item.enonce())
                    .isPresent();
            identifiants.add(item.illustration().orElseThrow());
        }
        assertThat(identifiants)
                .as("CON-3 : deux items qui montrent le meme dessin posent la meme donnee deux fois")
                .doesNotHaveDuplicates();
    }

    /**
     * `CON-6`, ecrite par nadia-prof le 2026-08-31 sur la fiche de {@code cp-figures-planes}, apres
     * avoir mesure que l'indice y designait la reponse sur 8 items sur 12 : <i>"sur un paquet
     * illustre, l'indice est choisi d'apres les propositions de l'item, jamais d'apres sa bonne
     * reponse. Verification : sur au moins la moitie des items, l'indice de l'item est aussi porte
     * par un item dont la bonne reponse est differente."</i> Son motif : l'indice n'apparait
     * qu'apres un echec, et un indice qui ne va qu'avec une seule reponse devient a ce moment-la une
     * designation et non une aide.
     *
     * <p>Elle est <b>neuve et non retroactive</b>, donc elle ne conditionnait pas le Done de ce
     * paquet. Elle est encodee ici quand meme, et sur ce paquet-ci seulement : nadia-prof recommande
     * qu'elle devienne un garde-fou, et le perimetre d'un garde-fou de depot est a theo-archi et
     * yanis-qa. Ce test ne prejuge pas du leur.
     *
     * <p><b>Ce que ce test ne voit pas, et il faut le dire ici parce que le chiffre est trompeur</b>
     * : un paquet qui donnerait <b>le meme indice a ses douze items</b> passe ce critere a 100 %.
     * La condition attrape l'indice taille pour une reponse ; elle ne distingue pas l'indice
     * partage de l'indice si general qu'il n'aide plus personne - la parade que lea-dev avait
     * trouvee pour les textes alternatifs, transposee aux indices. Mesure faite sur ce paquet le
     * 2026-08-31 : avant reecriture, deux indices pour douze items, <b>12/12</b> ; apres
     * reecriture, trois indices distribues par scene, <b>12/12</b>. Le chiffre ne bouge pas, et
     * c'est ce qui doit remonter a son autrice.
     */
    @Test
    void chaqueIndiceEstPorteParUnItemDontLaReponseEstDifferente() {
        List<Question> items = tousLesItems(paquet());
        List<Question> conformes = items.stream()
                .filter(item -> items.stream().anyMatch(autre -> autre != item
                        && autre.indice().equals(item.indice())
                        && !autre.bonneReponse().equals(item.bonneReponse())))
                .toList();

        assertThat(conformes.size() * 2)
                .as("CON-6 : %d items sur %d portent un indice qu'aucun item de reponse differente"
                        + " ne partage, donc leur indice ne va qu'avec leur propre reponse. L'indice"
                        + " s'affiche apres un echec : a ce moment-la il designe au lieu d'aider",
                        items.size() - conformes.size(), items.size())
                .isGreaterThanOrEqualTo(items.size());
    }

    @Test
    void lePaquetCompteExactementDouzeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static List<Integer> termes(String proposition) {
        List<Integer> termes = new ArrayList<>();
        Matcher trouve = TERME.matcher(proposition);
        while (trouve.find()) {
            termes.add(Integer.parseInt(trouve.group()));
        }
        return termes;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }
}
