package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0072 : sur un paquet dont les items portent un texte support, aucune strategie qui ne lit
 * qu'une seule phrase de ce texte ne doit gagner la majorite des items.
 *
 * <p><b>Le defaut, trouve en jouant et non par la CI.</b> {@code lea-dev} a joue son propre paquet
 * {@code cp-texte-narratif} avant de le pousser et a constate qu'un enfant gagnait sans lire le
 * texte entier. Mesure sur l'etat qu'elle avait pousse, repris tel quel comme jeu fautif de ce
 * garde-fou : <b>65 % des items</b> se gagnent en ne lisant que la premiere phrase et en pressant
 * la seule proposition qui s'y trouve, contre 33,3 % par hasard.
 *
 * <p><b>C'est le refus de S12-3 transpose, et le seuil n'est pas de moi.</b>
 * {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest} tient depuis s12 la meme
 * propriete sur une autre strategie : <i>une strategie qui ignore le contenu ne doit jamais etre
 * majoritaire</i>. La strategie y est "presser toujours le meme rang parmi trois valeurs
 * numeriques" ; elle est ici "ne lire qu'une phrase du support". Le seuil de majorite est celui de
 * s12, repris sans le renegocier, et la comparaison est stricte comme la sienne.
 *
 * <p><b>Le domaine, declare et non suppose (lot 5a de s24).</b> Les items qui <b>presentent des
 * propositions</b> - classement partage par {@link ClassementDesItems}, jamais reecrit ici - et qui
 * portent un texte support. Les deux bornes ont chacune leur motif :
 *
 * <ul>
 *   <li><b>un item de saisie est ecarte</b>, et pour une raison plus forte qu'une inapplicabilite :
 *       la strategie jugee ici presse <i>la seule proposition trouvee dans une phrase</i>, et un item
 *       auquel l'enfant repond en ecrivant n'en presente aucune. La donnee que ce critere lit
 *       n'existe pas davantage - {@code ReponseAEcrire} ne porte pas de texte support, ce n'est pas
 *       une composante vide, c'est une composante absente du type ;</li>
 *   <li><b>un item sans aucun support est hors du domaine</b>, exactement comme le rang d'une
 *       proposition non numerique l'est dans le garde-fou de s12.</li>
 * </ul>
 *
 * <p><b>Amendement du 2026-09-23 (ADR-0099, decision 4).</b> Le domaine de ce garde-fou etait
 * "l'item porte un texte support" ; il est desormais "l'item porte un support", c'est-a-dire un
 * texte, une grille, ou les deux, et le fragment qu'une strategie aveugle lit est une phrase de
 * texte <b>ou une rangee de tableau</b> ({@link #fragmentsDeSupport}). Sans cet amendement,
 * {@code tableauSupport} ouvrait une forme de support entierement hors du domaine de ce garde-fou :
 * il serait reste vert sur un paquet de grilles gagnable a 100 % sans lire la question.
 *
 * <p><b>Le nom de cette classe n'a pas change, et c'est un choix</b> : le renommer deplacerait les
 * repertoires de contre-exemple qui portent le materiau reel de s24 - le paquet de {@code lea-dev}
 * avant et apres sa correction -, et ce materiau vaut plus que l'exactitude du nom. Declencheur
 * ecrit : le jour ou un troisieme genre de support existera, le nom sera a reprendre dans le meme
 * geste que lui.</p>
 *
 * <p><b>Et le compte des ecartes est tenu</b> : une borne qui emporterait toute la population servie
 * n'est plus une borne, c'est une exemption generale, et elle est rouge.
 *
 * <p><b>Ce que ce garde-fou ne tient pas, et il faut le lire avant de croire qu'il tient la famille
 * entiere.</b> Il tient <b>une</b> strategie aveugle, celle qui porte sur le decoupage en phrases.
 * Il ne tient pas la propriete generale "une bonne reponse ne doit pas etre reconnaissable a une
 * marque de surface", qui n'est pas formulable - ADR-0072 ecrit pourquoi, avec les mesures des
 * familles de marques essayees et ecartees.
 *
 * <p>ADR-0013, les trois obligations : perimetre derive du repertoire de contenu et items tires par
 * le chargeur reel ; derivation totale sur le domaine ci-dessus ; mise a l'epreuve sur un paquet
 * fautif reel, jamais servi.
 */
class AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest";

    private static final String JEU_FAUTIF = "reponse-toujours-dans-la-premiere-phrase";

    private static final String CAS_VERT = "reponse-repartie-entre-les-deux-phrases";

    private static final String POPULATION_ECARTEE = "population-entierement-ecartee";

    /** ADR-0099 : les deux jeux de la moitie tableau du domaine, fautif puis corrige. */
    private static final String JEU_FAUTIF_TABLEAU = "reponse-toujours-dans-la-meme-rangee-du-tableau";

    private static final String CAS_VERT_TABLEAU = "reponse-repartie-entre-les-rangees-du-tableau";

    /**
     * Le seuil de s12, repris tel quel : une strategie qui ignore le contenu ne doit jamais etre
     * majoritaire. Comparaison stricte, comme celle du garde-fou de rang - un paquet a 50,0 % pile
     * passe.
     */
    private static final double SEUIL_MAJORITE = 0.5;

    /** Tirages sur un paquet combinatoire, le seul qui ne s'epuise jamais (ADR-0064). */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    /**
     * Le repliage de comparaison : la casse seule ne doit pas faire manquer une proposition presente
     * dans le texte - une proposition en debut de phrase y porte une majuscule que le paquet n'ecrit
     * pas dans la proposition. Manquer une occurrence rendrait ce garde-fou plus indulgent qu'il ne
     * doit l'etre, c'est-a-dire vert a tort.
     */
    private static String replier(String texte) {
        return texte.toLowerCase(Locale.ROOT);
    }

    /**
     * Le decoupage en phrases, sur les trois ponctuations fortes. Limite nommee : une abreviation
     * pointee couperait une phrase en deux. Aucun texte support du depot n'en porte, et le jour ou
     * l'un en portera, ce decoupage devra l'apprendre.
     */
    static List<String> phrases(String support) {
        List<String> phrases = new ArrayList<>();
        StringBuilder courante = new StringBuilder();
        for (int position = 0; position < support.length(); position++) {
            char caractere = support.charAt(position);
            courante.append(caractere);
            if (caractere == '.' || caractere == '!' || caractere == '?') {
                phrases.add(courante.toString());
                courante.setLength(0);
            }
        }
        if (!courante.toString().isBlank()) {
            phrases.add(courante.toString());
        }
        return phrases;
    }

    /**
     * <b>ADR-0099, decision 4 : une rangee de tableau est un fragment de support au meme titre
     * qu'une phrase de texte.</b> Les fragments d'un item sont les phrases de son texte-support,
     * puis les rangees de sa grille - dans cet ordre, pour qu'un rang designe le meme objet d'un
     * item a l'autre d'un paquet de meme forme.
     *
     * <p><b>Ce que cette extension empeche, et elle n'est pas decorative.</b> Sans elle, un paquet
     * dont les items ne portent qu'une grille sortait du domaine de ce garde-fou : la strategie
     * "ne lire qu'une rangee et presser la seule proposition qui s'y trouve" aurait pu gagner tous
     * les items, sur une suite verte. C'est exactement la forme d'item que SCI-CM1-20 demande - une
     * information explicitement presente dans le document - et la maniere naturelle de l'ecrire
     * met la bonne reponse dans une rangee qui ne porte aucun distracteur.
     *
     * <p><b>Ce que cette extension coute, dit d'avance</b> : un paquet de grilles dont les items
     * interrogent tous la meme rangee est rouge. Ce n'est pas un faux rouge - un enfant qui lit
     * toujours la meme rangee y gagne sans lire la question - et ce qui le paie est de faire varier
     * la rangee interrogee, jamais de rendre la grille moins lisible.
     */
    static List<String> fragmentsDeSupport(Question item) {
        List<String> fragments = new ArrayList<>();
        item.texteSupport().ifPresent(support -> fragments.addAll(phrases(support)));
        item.tableauSupport().ifPresent(tableau -> {
            // L'en-tete est une rangee comme les autres : il se lit comme elles. Il ne porte
            // ordinairement aucune valeur candidate, donc il ne fait gagner personne - mais
            // l'ecarter d'avance serait decider pour un contenu qui n'existe pas encore.
            fragments.add(String.join(" ", tableau.enTete()));
            tableau.lignes().forEach(ligne -> fragments.add(String.join(" ", ligne)));
        });
        return fragments;
    }

    record Strategie(int itemsAvecSupport, int rangDePhrase, double partGagnee) {
    }

    /**
     * Le juge, pur : la part des items d'un paquet qu'une strategie ne lisant que la phrase de rang
     * donne gagne de facon <b>decisive</b> - cette phrase porte la bonne reponse et aucun
     * distracteur. Rend la part la plus forte, tous rangs de phrase confondus, et le rang qui
     * l'atteint.
     *
     * <p>Une phrase qui ne porte aucune proposition, ou qui en porte plusieurs, ne fait pas gagner :
     * l'enfant y est ramene a un choix. Ce comptage est celui des gains decisifs et non une
     * esperance de gain - modeliser le tiers de chance restant ferait entrer une hypothese de
     * comportement dans un garde-fou.
     */
    static Strategie meilleureStrategie(List<Question> items) {
        List<Question> avecSupport = items.stream()
                .filter(item -> !fragmentsDeSupport(item).isEmpty()).toList();
        if (avecSupport.isEmpty()) {
            return new Strategie(0, 0, 0.0);
        }
        int phrasesMax = avecSupport.stream()
                .mapToInt(item -> fragmentsDeSupport(item).size())
                .max().orElse(0);
        int meilleurRang = 0;
        int meilleursGains = 0;
        for (int rang = 0; rang < phrasesMax; rang++) {
            int gains = 0;
            for (Question item : avecSupport) {
                List<String> phrasesDeLItem = fragmentsDeSupport(item);
                if (rang >= phrasesDeLItem.size()) {
                    continue;
                }
                String phrase = replier(phrasesDeLItem.get(rang));
                boolean porteLaBonneReponse = phrase.contains(replier(item.bonneReponse()));
                boolean sansAucunDistracteur = item.distracteurs().stream()
                        .noneMatch(distracteur -> phrase.contains(replier(distracteur)));
                if (porteLaBonneReponse && sansAucunDistracteur) {
                    gains++;
                }
            }
            if (gains > meilleursGains) {
                meilleursGains = gains;
                meilleurRang = rang;
            }
        }
        return new Strategie(avecSupport.size(), meilleurRang + 1,
                (double) meilleursGains / avecSupport.size());
    }

    /**
     * Les items du paquet, <b>de toutes les formes</b>, tires par le chargeur reel. Le classement
     * vient apres la lecture, jamais pendant : c'est la lecture du paquet qui doit etre entiere, et
     * transtyper pendant le tirage ferait echouer la lecture au lieu de classer ce qu'elle a lu.
     */
    private static List<Item> itemsDe(String racineClasspath, Path racineFichiers, String paquet) {
        ContentSource source = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
        int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racineFichiers, paquet);
        int budget = declares == 0 ? TIRAGES_SUR_UN_PAQUET_COMBINATOIRE : declares;
        Random alea = new Random(20260831L);
        Set<ItemId> vus = new LinkedHashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < budget; tirage++) {
            Optional<Item> tire = source.next(alea::nextInt, vus);
            if (tire.isEmpty()) {
                break;
            }
            Item item = tire.orElseThrow();
            vus.add(item.id());
            items.add(item);
        }
        if (declares > 0 && items.size() != declares) {
            throw new AssertionError("ADR-0064 : lecture partielle de " + paquet + " - " + items.size()
                    + " items tires pour " + declares + " declares sur disque");
        }
        return items;
    }

    static void verifier(String racineClasspath, Path racineFichiers) {
        List<String> fautifs = new ArrayList<>();
        int itemsJuges = 0;
        int itemsAPropositionsClasses = 0;
        int itemsDeSaisieEcartes = 0;
        for (String paquet : ChaquePaquetPorteUnIndiceTest.paquets(racineFichiers)) {
            List<Item> tires = itemsDe(racineClasspath, racineFichiers, paquet);
            // Lot 5a de s24 : le classement est ecrit une fois, le motif reste ici. Le compte des
            // items ecartes est porte plus bas, et une borne qui emporterait toute la population
            // servie est rouge.
            List<Question> aPropositions = ClassementDesItems.presentantDesPropositions(tires);
            itemsAPropositionsClasses += aPropositions.size();
            itemsDeSaisieEcartes += tires.size() - aPropositions.size();
            Strategie strategie = meilleureStrategie(aPropositions);
            itemsJuges += strategie.itemsAvecSupport();
            if (strategie.partGagnee() > SEUIL_MAJORITE) {
                fautifs.add(paquet + " : une strategie qui ne lit que le fragment de support de rang "
                        + strategie.rangDePhrase() + " (phrase de texte ou rangee de tableau) gagne "
                        + Math.round(strategie.partGagnee() * 1000) / 10.0 + " % de ses "
                        + strategie.itemsAvecSupport() + " items a support (texte, tableau, ou les deux)");
            }
        }
        // Lot 5a de s24 : une borne qui emporte toute la population n'est plus une borne. La
        // condition porte sur un ecart REELLEMENT constate - sans elle, un repertoire vide
        // declencherait ce message-ci en parlant d'une exemption qui n'a ecarte personne, et
        // masquerait la clause de non-vacuite qui suit, qui est celle qui dit vrai dans ce cas.
        if (itemsAPropositionsClasses == 0 && itemsDeSaisieEcartes > 0) {
            throw new AssertionError("ADR-0072 : ce garde-fou a ecarte " + itemsDeSaisieEcartes
                    + " item(s) de saisie et n'en a classe aucun qui presente des propositions. Une"
                    + " borne qui emporte toute la population n'est plus une borne, c'est une"
                    + " exemption generale");
        }
        if (itemsJuges == 0) {
            throw new AssertionError("ADR-0051, ADR-0072 : aucun item a support examine - le"
                    + " domaine de ce garde-fou est vide, il ne garde rien");
        }
        if (!fautifs.isEmpty()) {
            throw new AssertionError("ADR-0072 : une strategie qui ignore le contenu ne doit jamais"
                    + " etre majoritaire (seuil de S12-3). Un enfant qui ne lit qu'une phrase du texte"
                    + " support et presse la seule proposition qu'il y trouve gagne la majorite de"
                    + " ces paquets : " + fautifs);
        }
    }

    @Test
    void aucunPaquetServiNeSeGagneEnNeLisantQuUnePhraseDeSonTexteSupport() {
        verifier("", CONTENU_DE_PRODUCTION);
    }

    /** Le domaine reellement examine, sans quoi le cas vert ne prouverait rien (ADR-0051). */
    @Test
    void leDomaineExamineNEstPasVide() {
        int itemsAvecSupport = 0;
        for (String paquet : ChaquePaquetPorteUnIndiceTest.paquets(CONTENU_DE_PRODUCTION)) {
            itemsAvecSupport += meilleureStrategie(ClassementDesItems.presentantDesPropositions(
                    itemsDe("", CONTENU_DE_PRODUCTION, paquet))).itemsAvecSupport();
        }
        assertThat(itemsAvecSupport)
                .as("ADR-0072 : ce garde-fou ne prouve rien s'il n'examine aucun item a texte support")
                .isPositive();
    }

    /**
     * ADR-0013 obligation 3, sur du <b>materiau reel</b> : le paquet que {@code lea-dev} a ecrit,
     * relu et pousse le 2026-08-31, avant la correction qu'elle a livree le meme jour apres l'avoir
     * joue. Ce n'est pas une fixture ecrite par l'auteur de la regle, c'est du contenu ecrit de
     * bonne foi qui passait la CI.
     */
    @Test
    void lePaquetOuLaReponseEstToujoursDansLaPremierePhraseFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE_CLASSPATH + "/" + JEU_FAUTIF,
                CONTRE_EXEMPLE.resolve(JEU_FAUTIF)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cp-texte-narratif-avant-correction")
                .hasMessageContaining("rang 1")
                .hasMessageContaining("65.0 %");
    }


    /**
     * La seconde moitie de la mise a l'epreuve, et sans elle la morsure ne prouverait que la moitie
     * du critere : le <b>meme</b> paquet, par la meme personne, un jour plus tard, apres la seule
     * correction en cause. Un garde-fou qui rougit sur un paquet fautif sans passer sur sa version
     * corrigee ne distingue pas le defaut, il refuse la famille.
     *
     * <p>La valeur exacte est epinglee ici, et elle est inconfortable : <b>50,0 % pile</b>, la borne
     * du seuil atteinte et non depassee. Le paquet corrige passe par comparaison stricte, avec une
     * marge nulle. C'est un fait, il est route a {@code nadia-prof} dans ADR-0072, et ce n'est pas a
     * ce test de deplacer un seuil qui n'est pas le sien.
     */
    @Test
    void lePaquetCorrigeParLeaPasseEtSaMargeEstNulle() {
        Path racineFichiers = CONTRE_EXEMPLE.resolve(CAS_VERT);
        verifier(CONTRE_EXEMPLE_CLASSPATH + "/" + CAS_VERT, racineFichiers);

        Strategie strategie = meilleureStrategie(ClassementDesItems.presentantDesPropositions(
                itemsDe(CONTRE_EXEMPLE_CLASSPATH + "/" + CAS_VERT, racineFichiers,
                        "cp-texte-narratif-apres-correction")));
        assertThat(strategie.partGagnee())
                .as("ADR-0072 : le paquet corrige est exactement a la borne du seuil, marge nulle")
                .isEqualTo(SEUIL_MAJORITE);
    }

    /**
     * ADR-0013 obligation 3 : la borne du domaine mord elle aussi. Un repertoire qui ne porte que
     * des items de saisie voit sa population <b>entierement</b> ecartee par le classement ; le
     * garde-fou serait alors vert sur zero objet juge, ce qui est une exemption generale sous le nom
     * d'une borne. Le paquet du jeu fautif n'est pas invente : c'est celui de la sonde du lot 1,
     * repris tel quel - ce qui est fautif ici n'est pas son contenu, c'est la population qu'il forme.
     */
    @Test
    void unePopulationEntierementEcarteeParLeClassementFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE_CLASSPATH + "/" + POPULATION_ECARTEE,
                CONTRE_EXEMPLE.resolve(POPULATION_ECARTEE)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("exemption generale");
    }

    /**
     * ADR-0099, decision 4, ADR-0013 obligation 3 : la moitie <b>tableau</b> du domaine mord. Un
     * paquet dont les items ne portent aucun texte-support et dont six grilles sur dix se
     * repondent en ne lisant que la premiere ligne du corps est rouge - sans l'extension du
     * domaine, ce meme paquet etait <b>hors du domaine</b> de ce garde-fou, donc vert.
     *
     * <p><b>Ce materiau n'est pas reel, et c'est ecrit plutot que tu, parce que c'est la faiblesse
     * de cette moitie-la</b> (lecon de s05) : aucun paquet ne declarait de grille le 2026-09-23.
     * Ce qui la compense est que le juge, lui, est le meme que celui qui s'execute sur les paquets
     * a texte-support servis et sur le materiau reel de s24.</p>
     */
    @Test
    void lePaquetOuLaReponseEstToujoursDansLaMemeRangeeDeSaGrilleFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE_CLASSPATH + "/" + JEU_FAUTIF_TABLEAU,
                CONTRE_EXEMPLE.resolve(JEU_FAUTIF_TABLEAU)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("extraction-tableau-avant-variation")
                .hasMessageContaining("rang 2")
                .hasMessageContaining("60.0 %");
    }

    /**
     * La seconde moitie de la mise a l'epreuve : le meme paquet, dont la seule difference est la
     * rangee interrogee. Sans elle, la morsure ci-dessus ne prouverait pas que ce garde-fou
     * distingue le defaut de la <b>forme</b> tableau - un garde-fou qui rougirait sur toute grille
     * fermerait SCI-CM1-20 au lieu de la garder.
     */
    @Test
    void lePaquetDeGrillesDontLaRangeeInterrogeeVariePasse() {
        Path racineFichiers = CONTRE_EXEMPLE.resolve(CAS_VERT_TABLEAU);
        verifier(CONTRE_EXEMPLE_CLASSPATH + "/" + CAS_VERT_TABLEAU, racineFichiers);

        Strategie strategie = meilleureStrategie(ClassementDesItems.presentantDesPropositions(
                itemsDe(CONTRE_EXEMPLE_CLASSPATH + "/" + CAS_VERT_TABLEAU, racineFichiers,
                        "extraction-tableau-apres-variation")));
        assertThat(strategie.itemsAvecSupport())
                .as("ADR-0099 : les dix items de grille entrent bien dans le domaine")
                .isEqualTo(10);
        assertThat(strategie.partGagnee())
                .as("ADR-0099 : la meilleure strategie a une rangee reste sous le seuil de S12-3")
                .isLessThan(SEUIL_MAJORITE);
    }

    /** ADR-0051 : la clause de non-vacuite mord, elle aussi. */
    @Test
    void unDomaineVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier("", CONTRE_EXEMPLE.resolve("repertoire-sans-aucun-paquet")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("domaine de ce garde-fou est vide");
    }
}
