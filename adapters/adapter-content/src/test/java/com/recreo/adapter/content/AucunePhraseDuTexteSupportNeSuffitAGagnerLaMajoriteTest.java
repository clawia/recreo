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
 * <p><b>Le domaine.</b> Les items qui portent un texte support, sur tous les paquets du repertoire
 * de contenu. Un item sans texte support n'est pas un objet ignore par erreur : la strategie
 * "ne lire qu'une phrase du support" n'a aucun sens sur un item qui n'en porte pas, exactement
 * comme le rang d'une proposition non numerique n'en a pas dans le garde-fou de s12.
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
        List<Question> avecSupport = items.stream().filter(item -> item.texteSupport().isPresent()).toList();
        if (avecSupport.isEmpty()) {
            return new Strategie(0, 0, 0.0);
        }
        int phrasesMax = avecSupport.stream()
                .mapToInt(item -> phrases(item.texteSupport().orElseThrow()).size())
                .max().orElse(0);
        int meilleurRang = 0;
        int meilleursGains = 0;
        for (int rang = 0; rang < phrasesMax; rang++) {
            int gains = 0;
            for (Question item : avecSupport) {
                List<String> phrasesDeLItem = phrases(item.texteSupport().orElseThrow());
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

    private static List<Question> itemsDe(String racineClasspath, Path racineFichiers, String paquet) {
        ContentSource source = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
        int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racineFichiers, paquet);
        int budget = declares == 0 ? TIRAGES_SUR_UN_PAQUET_COMBINATOIRE : declares;
        Random alea = new Random(20260831L);
        Set<ItemId> vus = new LinkedHashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < budget; tirage++) {
            Optional<Item> tire = source.next(alea::nextInt, vus);
            if (tire.isEmpty()) {
                break;
            }
            Question item = (Question) tire.orElseThrow();
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
        for (String paquet : ChaquePaquetPorteUnIndiceTest.paquets(racineFichiers)) {
            Strategie strategie = meilleureStrategie(itemsDe(racineClasspath, racineFichiers, paquet));
            itemsJuges += strategie.itemsAvecSupport();
            if (strategie.partGagnee() > SEUIL_MAJORITE) {
                fautifs.add(paquet + " : une strategie qui ne lit que la phrase "
                        + strategie.rangDePhrase() + " du texte support gagne "
                        + Math.round(strategie.partGagnee() * 1000) / 10.0 + " % de ses "
                        + strategie.itemsAvecSupport() + " items a texte support");
            }
        }
        if (itemsJuges == 0) {
            throw new AssertionError("ADR-0051, ADR-0072 : aucun item a texte support examine - le"
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
            itemsAvecSupport += meilleureStrategie(itemsDe("", CONTENU_DE_PRODUCTION, paquet))
                    .itemsAvecSupport();
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
                .hasMessageContaining("phrase 1")
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

        Strategie strategie = meilleureStrategie(
                itemsDe(CONTRE_EXEMPLE_CLASSPATH + "/" + CAS_VERT, racineFichiers,
                        "cp-texte-narratif-apres-correction"));
        assertThat(strategie.partGagnee())
                .as("ADR-0072 : le paquet corrige est exactement a la borne du seuil, marge nulle")
                .isEqualTo(SEUIL_MAJORITE);
    }

    /** ADR-0051 : la clause de non-vacuite mord, elle aussi. */
    @Test
    void unDomaineVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier("", CONTRE_EXEMPLE.resolve("repertoire-sans-aucun-paquet")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("domaine de ce garde-fou est vide");
    }
}
