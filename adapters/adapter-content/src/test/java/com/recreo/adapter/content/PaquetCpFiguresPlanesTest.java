package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S25-FIG (DEC-0065, amendement, section A.3) : le paquet "cp-figures-planes", mathematiques CP,
 * <b>premier paquet illustre du produit</b>.
 *
 * <p>Conditions d'ecriture : FIG-1 a FIG-4 de nadia-prof, rendues le 2026-08-24
 * ({@code ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md}, section 2.4) et
 * reconduites sans une virgule le 2026-08-31, plus CON-1 a CON-5 de son arbitrage du palier 1
 * ({@code ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md},
 * sections 3 et 9.6).
 *
 * <p><b>Ce fichier lit le paquet par deux chemins, et ce n'est pas une redondance.</b> Le texte que
 * l'enfant recoit est tire par le <b>chargeur reel</b>, comme sur tous les paquets de ce depot :
 * c'est en chemin qu'un champ se perd, et une declaration lue dans le YAML ne prouve pas qu'elle
 * arrive. Les declarations d'illustration, elles, sont lues <b>dans le YAML</b>, parce que
 * {@code Question} ne les porte pas encore : le champ de contenu du domaine jusqu'a l'ecran est le
 * lot A du sprint et il n'est pas de moi. Le jour ou il entre, ces trois clauses-la se relisent par
 * le chargeur comme les autres, et la traversee jusqu'au HTML servi lui appartient.
 *
 * <p><b>Ce que ce garde-fou ne juge pas, et rien ne le jugera a sa place.</b> CON-1 (<i>"en effacant
 * l'image, la reponse reste-t-elle trouvable ?"</i>), CON-3, CON-4 - le masquage exhaustif item par
 * item - et CON-5 - le texte alternatif ne porte jamais la reponse - sont la main de nadia-prof.
 * CON-2 est la seule automatisable de la famille et elle l'est ici. <b>L'exactitude de ce qui est
 * dessine</b> ne l'est pas davantage : qu'un carre soit carre, qu'un cercle ne soit pas un ovale,
 * que trois exemplaires d'une meme forme varient assez - <i>"ce sont des reponses fausses, pas des
 * defauts de forme"</i> (nadia-prof, 2026-08-15). Ce fichier compte les exemplaires ; il ne les
 * regarde pas.
 */
class PaquetCpFiguresPlanesTest {

    private static final String PAQUET = "cp-figures-planes";

    private static final String CHEMIN_YAML = "/contenu/" + PAQUET + ".yaml";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 12;

    /** FIG-2 : au moins trois exemples de chaque forme. */
    private static final int FIG_2_EXEMPLES_PAR_FORME = 3;

    /**
     * FIG-4 : les quatre formes de la ligne du programme, telles que
     * {@code squad/programme/mathematiques/competences-cp.md} les nomme - "carre, rectangle,
     * triangle, cercle". Le nom sous lequel le paquet les propose porte son article, parce que c'est
     * ce que l'enfant lit.
     */
    private static final List<String> FORMES = List.of("un carre", "un rectangle", "un triangle",
            "un cercle");

    /**
     * FIG-4, seconde moitie : les mots que le paquet ne dit nulle part. "disque" confondrait la
     * surface avec son contour, et les quatre suivants sont des solides, qui n'appartiennent pas a
     * cette ligne. Le contre-exemple est dans le depot : nadia-prof a relevé que
     * {@code contenu-repris/quiz/cp-geometrie.yml} finit sur les faces d'un cube.
     */
    private static final List<String> MOTS_INTERDITS = List.of("disque", "cube", "pave", "boule",
            "sphere", "cylindre", "pyramide");

    /**
     * CON-2 : ce qu'un enonce ne dit jamais. La liste est celle que nadia-prof donne - <i>"la
     * position, la forme, la quantite, la couleur"</i> - instanciee sur la matiere de ce paquet, y
     * compris les mots qu'elle nomme elle-meme dans sa condition ("cotes", "coins"). C'est un
     * <b>critere enumere</b> et il ne pretend pas etre autre chose : une periphrase lui echappe, et
     * c'est pourquoi CON-1 reste entierement chez nadia-prof. Ce qu'il attrape est la famille de
     * defaut qu'elle nomme - celle qui reviendrait sans qu'on s'en apercoive.
     */
    private static final List<String> DESCRIPTIONS_INTERDITES = List.of("cote", "coin", "angle",
            "rond", "droit", "egaux", "egale", "pointe", "haut", "bas", "gauche", "droite",
            "rouge", "bleu", "vert", "jaune", "noir", "orange", "couleur", "quatre", "trois");

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
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

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

    /**
     * Les items tels que le fichier les ecrit, cles comprises et dans leur ordre. SnakeYAML rend les
     * cles dans l'ordre du fichier, et c'est ce qui rend la clause 6 d'ADR-0073 verifiable.
     */
    private static List<Map<String, Object>> itemsEcrits() {
        try (InputStream flux = PaquetCpFiguresPlanesTest.class.getResourceAsStream(CHEMIN_YAML)) {
            assertThat(flux).as("%s est introuvable sur le classpath", CHEMIN_YAML).isNotNull();
            Map<String, Object> corps = new Yaml().load(flux);
            List<Map<String, Object>> items = new ArrayList<>();
            for (Object item : (List<?>) corps.get("items")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> champs = (Map<String, Object>) item;
                items.add(champs);
            }
            return items;
        } catch (java.io.IOException echec) {
            throw new java.io.UncheckedIOException(echec);
        }
    }

    private static String sansAccent(String texte) {
        return java.text.Normalizer.normalize(texte, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.FRENCH);
    }

    @Test
    void lePaquetCompteExactementDouzeItems() {
        assertThat(tousLesItems(paquet()))
                .as("le compte d'items est garde exactement a %d", NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /**
     * FIG-2 : au moins trois exemples de chaque forme. L'unite de compte est l'item, et la forme est
     * lue sur la bonne reponse - c'est elle que l'enfant doit reconnaitre.
     */
    @Test
    void chaqueFormePorteAuMoinsTroisExemples() {
        Map<String, Integer> exemplesParForme = new TreeMap<>();
        for (Question item : tousLesItems(paquet())) {
            exemplesParForme.merge(sansAccent(item.bonneReponse()), 1, Integer::sum);
        }
        for (String forme : FORMES) {
            assertThat(exemplesParForme.getOrDefault(forme, 0))
                    .as("FIG-2 : la forme '%s' compte au moins %d exemples - un paquet a un"
                            + " exemplaire par forme enseigne qu'une figure n'a qu'une apparence",
                            forme, FIG_2_EXEMPLES_PAR_FORME)
                    .isGreaterThanOrEqualTo(FIG_2_EXEMPLES_PAR_FORME);
        }
        assertThat(exemplesParForme.keySet())
                .as("FIG-4 : le paquet ne propose que les quatre figures planes de la ligne")
                .containsExactlyInAnyOrderElementsOf(FORMES.stream().map(PaquetCpFiguresPlanesTest::sansAccent).toList());
    }

    /**
     * FIG-2, sa seconde moitie : trois exemples d'une meme forme ne valent que s'ils sont trois
     * dessins differents. Ce que ce test tient est qu'aucun dessin n'est reemploye ; que les trois
     * varient reellement d'orientation, de taille et de proportions se regarde, et c'est nadia-prof
     * qui regarde.
     */
    @Test
    void chaqueItemMontreUnDessinQuiNEstMontreParAucunAutre() {
        List<String> declarees = itemsEcrits().stream()
                .map(item -> String.valueOf(item.get("illustration")))
                .toList();

        assertThat(new LinkedHashSet<>(declarees))
                .as("FIG-2 : douze items, douze dessins distincts - un dessin reemploye ferait deux"
                        + " exemples d'une meme forme qui n'en sont qu'un")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /**
     * ADR-0073 clause 6, dans les mots de nadia-prof : <i>"que 'enonce' reste le premier champ de
     * chaque item"</i>. Le comptage textuel partage des garde-fous de contenu (ADR-0064) reconnait
     * un item enumere a sa premiere cle ; un item illustre qui commencerait autrement en sortirait,
     * et ses conditions de longueur cesseraient d'etre executees sans que rien ne le dise. La clause
     * est deja tenue pour tout le depot par {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} ;
     * elle est reprise ici parce que c'est ce paquet-ci qui l'a motivee.
     */
    @Test
    void chaqueItemCommenceParSonEnonceEtDeclareSonTexteAlternatif() {
        for (Map<String, Object> item : itemsEcrits()) {
            String premiereCle = item.keySet().iterator().next();
            assertThat(premiereCle)
                    .as("ADR-0073 clause 6 : la premiere cle de l'item '%s' est son enonce",
                            item.get("enonce"))
                    .isEqualTo("enonce");
            assertThat(String.valueOf(item.get("illustrationTexteAlternatif")))
                    .as("ADR-0073 clause 5 : l'item '%s' declare le texte qu'une lecture d'ecran"
                            + " annonce", item.get("enonce"))
                    .isNotBlank();
        }
    }

    /**
     * CON-5 : le texte alternatif decrit ce qu'il y a a regarder, jamais la reponse ni ce qui permet
     * de la deduire. <b>Sa moitie calculable est celle-ci</b> : aucun texte alternatif ne nomme une
     * figure, et les douze sont identiques - toute phrase qui distinguerait un dessin d'un autre
     * nommerait la figure, donc la reponse. Ce que cette clause ne prouve pas, et c'est la moitie
     * qui compte : que la phrase retenue soit acceptable. Elle est de nadia-prof, mot pour mot, et
     * c'est elle qui la juge.
     */
    @Test
    void aucunTexteAlternatifNeNommeLaFigure() {
        Set<String> alternatifs = new TreeSet<>();
        for (Map<String, Object> item : itemsEcrits()) {
            String alternatif = sansAccent(String.valueOf(item.get("illustrationTexteAlternatif")));
            alternatifs.add(alternatif);
            for (String forme : FORMES) {
                String nom = sansAccent(forme).substring("un ".length());
                assertThat(alternatif)
                        .as("CON-5 : le texte alternatif de l'item '%s' nomme '%s'. Le texte"
                                + " alternatif est le seul endroit du produit ou une reponse peut"
                                + " fuir sans que personne s'en apercoive", item.get("enonce"), nom)
                        .doesNotContain(nom);
            }
        }
        assertThat(alternatifs)
                .as("CON-5 : les douze textes alternatifs sont le meme, parce qu'aucune difference"
                        + " entre eux ne pourrait porter autre chose que la reponse")
                .hasSize(1);
    }

    /**
     * CON-2 : l'enonce ne decrit ni la position, ni la forme, ni la quantite, ni la couleur de ce
     * que l'image montre. <b>C'est la seule des cinq conditions de contrefacon qui soit
     * automatisable</b>, et nadia-prof le dit elle-meme ; les quatre autres restent dans sa main.
     * Le critere est enumere et sa Javadoc de champ le declare : une periphrase lui echappe.
     */
    @Test
    void aucunEnonceNeDecritCeQueLImageMontre() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = sansAccent(item.enonce());
            for (String description : DESCRIPTIONS_INTERDITES) {
                assertThat(enonce)
                        .as("CON-2 : l'enonce '%s' emploie '%s'. L'enonce pose la question, l'image"
                                + " porte la donnee", item.enonce(), description)
                        .doesNotContain(description);
            }
        }
    }

    /**
     * FIG-4 : le paquet ne confond ni "cercle" et "disque", ni figure plane et solide. Le controle
     * porte sur tout ce que l'enfant lit - consigne du catalogue exclue, elle est jugee la ou elle
     * vit -, enonces, propositions, indices et textes alternatifs.
     */
    @Test
    void leTexteServiNeNommeNiDisqueNiSolide() {
        for (Question item : tousLesItems(paquet())) {
            List<String> lus = new ArrayList<>(List.of(item.enonce(), item.bonneReponse()));
            lus.addAll(item.distracteurs());
            item.indice().ifPresent(lus::add);
            for (String texte : lus) {
                for (String interdit : MOTS_INTERDITS) {
                    assertThat(sansAccent(texte))
                            .as("FIG-4 : '%s' emploie '%s' - un disque n'est pas un cercle et un"
                                    + " solide n'est pas une figure plane", texte, interdit)
                            .doesNotContain(interdit);
                }
            }
        }
    }

    /**
     * ADR-0032, verifie ici sur ce paquet et pas seulement dans le garde-fou general : l'identite
     * d'ecran connue du depot est l'enonce et le jeu des propositions, et {@code illustration} n'y
     * entre pas encore - le champ n'existe pas sur {@code Question}. Douze couples distincts
     * garantissent donc que ce paquet tient ADR-0032 <b>sans dependre</b> du rang que l'illustration
     * prendra dans cette identite le jour ou le lot A la classera.
     */
    @Test
    void aucunItemNePresenteLeMemeEcranQuUnAutre() {
        Set<String> ecrans = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            Set<String> propositions = new TreeSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            ecrans.add(item.enonce() + " " + propositions);
        }
        assertThat(ecrans)
                .as("ADR-0032 : douze items, douze ecrans distincts sur le seul couple (enonce,"
                        + " propositions)")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** Chaque item porte un indice, et aucun indice ne nomme une figure - donc jamais la reponse. */
    @Test
    void chaqueItemPorteUnIndiceQuiNeNommeAucuneFigure() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice())
                    .as("'%s' : chaque item porte un indice", item.enonce())
                    .isPresent();
            String indice = sansAccent(item.indice().orElseThrow());
            for (String forme : FORMES) {
                assertThat(indice)
                        .as("'%s' : l'indice nomme '%s', donc il peut nommer la reponse",
                                item.enonce(), forme)
                        .doesNotContain(sansAccent(forme).substring("un ".length()));
            }
        }
    }
}
