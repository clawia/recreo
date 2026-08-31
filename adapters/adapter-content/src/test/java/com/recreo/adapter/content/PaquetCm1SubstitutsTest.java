package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S25-SUB-1 : le paquet "cm1-substituts". Arbitrage prealable de {@code nadia-prof} du 2026-08-30,
 * ACCEPTE sous conditions SUB-1 a SUB-10
 * ({@code ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md},
 * section 5). Paquet integralement neuf : aucune matiere premiere de {@code contenu-repris/} sur
 * cette ligne.
 *
 * <p><b>Ce fichier ne couvre que les conditions calculables</b>, et il nomme celles qui ne le sont
 * pas plutot que de laisser croire qu'elles le sont. {@code nadia-prof} ecrit elle-meme lesquelles
 * elle porte a la main, item par item :</p>
 *
 * <ul>
 *   <li><b>SUB-4</b> - au moins deux propositions sur trois s'accordent en genre et en nombre avec
 *       le substitut. <i>"L'accord en genre et en nombre d'un groupe nominal francais n'est pas
 *       decidable par un automate sans lexique morphologique, et ce depot n'en a pas."</i></li>
 *   <li><b>SUB-5</b> - sur au moins un tiers des items, le referent n'est pas le groupe nominal
 *       compatible le plus proche a gauche. La distance en mots se compte, la compatibilite non :
 *       elle bute sur le meme lexique. Ce qui est verifie ici est donc le <b>marquage</b> du
 *       fichier servi, jamais la propriete.</li>
 *   <li><b>SUB-6</b> - texte masque, la question ne se repond pas. <i>"Aucun automate ne voit cette
 *       propriete. C'est COMP-2."</i></li>
 * </ul>
 *
 * <p><b>Et une condition que ce fichier ne borne pas parce que personne ne l'a bornee.</b>
 * {@code nadia-prof} releve dans ses propres conditions que SUB-2 borne le texte support et
 * l'enonce, et que <b>rien chez elle ne borne la longueur des propositions</b> - <i>"un manque de
 * ma part"</i>. La borne est instruite par {@code camille-po} (obstacle 11 du planning de s25, DV-4)
 * et n'existe pas encore. Ce paquet ne l'anticipe pas et n'en invente pas : ses propositions sont
 * des personnages ou des groupes nominaux courts du texte, et {@link #aucunePropositionNeDepasseLa
 * LongueurDuPlusLongPersonnage()} epingle ce qu'il livre, sans promettre au-dela de lui-meme.</p>
 */
class PaquetCm1SubstitutsTest {

    private static final String PAQUET = "cm1-substituts";
    private static final String RESSOURCE_YAML = "/contenu/cm1-substituts.yaml";

    /** SUB-10 : "au moins vingt". Le compte est garde exactement, comme sur tous les paquets. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** SUB-3 et SUB-5 : arrondi inferieur d'un tiers de vingt items. */
    private static final int PLANCHER_D_UN_TIERS = 6;

    /** SUB-2 : les deux bornes de longueur, dans les mots de nadia-prof. */
    private static final int MOTS_MAXIMUM_DU_TEXTE_SUPPORT = 45;
    private static final int PHRASES_MAXIMUM_DU_TEXTE_SUPPORT = 3;
    private static final int MOTS_MAXIMUM_DE_L_ENONCE = 12;

    /** SUB-9 : "en dix mots au plus". */
    private static final int MOTS_MAXIMUM_DE_L_INDICE = 10;

    /**
     * Ce que ce paquet livre, epingle et non promis : la plus longue de ses soixante propositions.
     * Voir la Javadoc de classe - la borne de la famille n'existe pas encore et n'est pas de moi.
     */
    private static final int MOTS_DE_LA_PLUS_LONGUE_PROPOSITION = 4;

    /** SUB-12 : ce qui fait d'une proposition un couple, dans les mots de nadia-prof - "X et Y". */
    private static final String MARQUE_DU_COUPLE = " et ";

    /** ADR-0060 clause 2 : le caractere decide a l'interieur des guillemets francais. */
    private static final char INSECABLE = 160;

    private static final char GUILLEMET_OUVRANT = 171;

    private static final char GUILLEMET_FERMANT = 187;

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu. */
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

    private static String yamlServi() {
        try (InputStream flux = PaquetCm1SubstitutsTest.class.getResourceAsStream(RESSOURCE_YAML)) {
            assertThat(flux).as("%s introuvable sur le classpath", RESSOURCE_YAML).isNotNull();
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** SUB-2 : "un mot est une suite de caracteres sans espace", dans les mots de nadia-prof. */
    private static int mots(String texte) {
        int compte = 0;
        for (String morceau : texte.split(" ")) {
            if (!morceau.isBlank()) {
                compte++;
            }
        }
        return compte;
    }

    /** Le decoupage en phrases sur les trois ponctuations fortes, celui d'ADR-0072. */
    private static int phrases(String texte) {
        int compte = 0;
        boolean enCours = false;
        for (int rang = 0; rang < texte.length(); rang++) {
            char caractere = texte.charAt(rang);
            if (caractere == '.' || caractere == '!' || caractere == '?') {
                compte++;
                enCours = false;
            } else if (!Character.isWhitespace(caractere)) {
                enCours = true;
            }
        }
        return enCours ? compte + 1 : compte;
    }

    /** Le mot cite entre guillemets francais dans l'enonce : le substitut que l'item interroge. */
    private static String substitutCite(String enonce) {
        int ouvrant = enonce.indexOf(GUILLEMET_OUVRANT);
        int fermant = enonce.indexOf(GUILLEMET_FERMANT);
        if (ouvrant < 0 || fermant < ouvrant) {
            return null;
        }
        return enonce.substring(ouvrant + 1, fermant).replace(String.valueOf(INSECABLE), "").strip();
    }

    @Test
    void lePaquetCompteExactementVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("SUB-10 : au moins vingt items, garde exactement a %d", NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCm1LectureEtComprehension() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Lecture et compréhension de l'écrit");
        assertThat(descripteur.competence())
                .as("la ligne visee est distincte de \"Comprendre un texte narratif ou"
                        + " documentaire\", deja verte - nadia-prof l'a verifie avant d'arbitrer")
                .contains("Repérer les substituts d'un personnage");
    }

    /**
     * SUB-1, premiere moitie : chaque item porte son texte, et son enonce cite entre guillemets
     * francais le mot dont on demande a qui il renvoie.
     */
    @Test
    void chaqueItemPorteUnTexteSupportEtCiteSonSubstitutEntreGuillemets() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            if (item.texteSupport().isEmpty() || item.texteSupport().orElseThrow().isBlank()) {
                defauts.add("'" + item.enonce() + "' : ce paquet promet un texte-support sur chacun"
                        + " de ses items (SUB-1) - c'est sa raison d'etre");
                continue;
            }
            if (substitutCite(item.enonce()) == null) {
                defauts.add("'" + item.enonce() + "' : l'enonce doit citer entre guillemets francais"
                        + " le mot dont on demande a qui il renvoie (SUB-1)");
            }
        }
        assertThat(defauts).as("SUB-1 : la forme de chaque item").isEmpty();
    }

    /**
     * SUB-1, seconde moitie : les trois propositions sont trois personnages ou groupes nominaux
     * <b>presents dans le texte de leur propre item</b>. C'est la condition qui empeche un
     * distracteur invente, qu'un enfant ecarterait sans lire.
     */
    @Test
    void lesTroisPropositionsDeChaqueItemSontPresentesDansSonTexte() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String texte = item.texteSupport().orElseThrow().toLowerCase(Locale.ROOT);
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            for (String proposition : propositions) {
                if (!texte.contains(proposition.toLowerCase(Locale.ROOT))) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' n'apparait pas dans le texte de son item (SUB-1)");
                }
            }
        }
        assertThat(defauts).as("SUB-1 : les trois propositions viennent du texte").isEmpty();
    }

    /** SUB-2 : au plus 45 mots et trois phrases pour le texte, au plus 12 mots pour l'enonce. */
    @Test
    void chaqueItemTientLesBornesDeLongueurDeSub2() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String texte = item.texteSupport().orElseThrow();
            if (mots(texte) > MOTS_MAXIMUM_DU_TEXTE_SUPPORT) {
                defauts.add("'" + item.enonce() + "' : texte-support de " + mots(texte) + " mots,"
                        + " au plus " + MOTS_MAXIMUM_DU_TEXTE_SUPPORT + " (SUB-2)");
            }
            if (phrases(texte) > PHRASES_MAXIMUM_DU_TEXTE_SUPPORT) {
                defauts.add("'" + item.enonce() + "' : texte-support de " + phrases(texte)
                        + " phrases, au plus " + PHRASES_MAXIMUM_DU_TEXTE_SUPPORT + " (SUB-2)");
            }
            if (mots(item.enonce()) > MOTS_MAXIMUM_DE_L_ENONCE) {
                defauts.add("'" + item.enonce() + "' : enonce de " + mots(item.enonce()) + " mots,"
                        + " au plus " + MOTS_MAXIMUM_DE_L_ENONCE + " (SUB-2)");
            }
        }
        assertThat(defauts).as("SUB-2 : les bornes de longueur").isEmpty();
    }

    /**
     * SUB-7 : un seul substitut par item, et il ne figure qu'une fois dans le texte. Motif de
     * {@code nadia-prof} : si le mot y figure deux fois, l'enfant ne sait pas duquel on lui parle,
     * et l'item n'a plus de reponse unique.
     */
    @Test
    void leSubstitutDeChaqueItemNeFigureQuUneFoisDansSonTexte() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String substitut = substitutCite(item.enonce()).toLowerCase(Locale.ROOT);
            String texte = item.texteSupport().orElseThrow().toLowerCase(Locale.ROOT);
            int occurrences = 0;
            int depuis = texte.indexOf(substitut);
            while (depuis >= 0) {
                occurrences++;
                depuis = texte.indexOf(substitut, depuis + 1);
            }
            if (occurrences != 1) {
                defauts.add("'" + item.enonce() + "' : le substitut '" + substitut + "' figure "
                        + occurrences + " fois dans son texte, une seule attendue (SUB-7)");
            }
        }
        assertThat(defauts).as("SUB-7 : un substitut, une occurrence").isEmpty();
    }

    /** SUB-9 : l'indice dit quelle partie relire, en dix mots au plus, et ne nomme pas le referent. */
    @Test
    void chaqueIndiceTientEnDixMotsEtNeNommeJamaisLeReferent() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String indice = item.indice().orElse(null);
            if (indice == null || indice.isBlank()) {
                defauts.add("'" + item.enonce() + "' : aucun indice (SUB-9)");
                continue;
            }
            if (mots(indice) > MOTS_MAXIMUM_DE_L_INDICE) {
                defauts.add("'" + item.enonce() + "' : indice de " + mots(indice) + " mots, au plus "
                        + MOTS_MAXIMUM_DE_L_INDICE + " (SUB-9)");
            }
            if (indice.toLowerCase(Locale.ROOT).contains(item.bonneReponse().toLowerCase(Locale.ROOT))) {
                defauts.add("'" + item.enonce() + "' : l'indice nomme le referent, il doit seulement"
                        + " dire quelle partie du texte relire (SUB-9)");
            }
        }
        assertThat(defauts).as("SUB-9 : la forme des indices").isEmpty();
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            assertThat(propositions)
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    /** SUB-10, seconde moitie : deux items ne partagent jamais le meme texte-support. */
    @Test
    void deuxItemsNePartagentJamaisLeMemeTexteSupport() {
        List<Question> items = tousLesItems(paquet());
        Set<String> supports = new HashSet<>();
        for (Question item : items) {
            supports.add(item.texteSupport().orElseThrow());
        }
        assertThat(supports)
                .as("SUB-10 : chacun des %d items porte son propre texte", items.size())
                .hasSize(items.size());
    }

    /**
     * SUB-3 : les deux familles de la ligne sont servies. Le substitut interroge est marque
     * '# pronom' ou '# groupe nominal' a cote de son enonce dans le YAML servi - un marqueur
     * qu'aucun code de production ne lit, et cette clause lit le fichier reel pour que le compte ne
     * puisse pas dater d'avant la derniere modification du paquet.
     *
     * <p>Ce que cette clause prouve : le paquet declare au moins six items de chaque famille. Ce
     * qu'elle ne prouve pas : que le mot marque "pronom" en soit un - c'est une lecture de langue,
     * donc celle de {@code nadia-prof}.</p>
     */
    @Test
    void lesDeuxFamillesDeLaLigneSontServiesSurAuMoinsUnTiersDesItems() {
        List<String> lignesDItem = yamlServi().lines()
                .filter(ligne -> ligne.startsWith("  - enonce:"))
                .toList();

        assertThat(lignesDItem)
                .as("ADR-0051 : cette clause ne compte rien si le YAML servi ne porte aucun item")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);

        long pronoms = lignesDItem.stream().filter(ligne -> ligne.contains("# pronom")).count();
        long groupesNominaux = lignesDItem.stream()
                .filter(ligne -> ligne.contains("# groupe nominal")).count();

        assertThat(pronoms)
                .as("SUB-3 : au moins %d items sur %d interrogent un pronom", PLANCHER_D_UN_TIERS,
                        NOMBRE_D_ITEMS_ATTENDU)
                .isGreaterThanOrEqualTo(PLANCHER_D_UN_TIERS);
        assertThat(groupesNominaux)
                .as("SUB-3 : au moins %d items sur %d interrogent un groupe nominal",
                        PLANCHER_D_UN_TIERS, NOMBRE_D_ITEMS_ATTENDU)
                .isGreaterThanOrEqualTo(PLANCHER_D_UN_TIERS);
        assertThat(pronoms + groupesNominaux)
                .as("SUB-3 : chaque item declare la famille de son substitut, aucun ne reste"
                        + " non classe")
                .isEqualTo(NOMBRE_D_ITEMS_ATTENDU);
    }

    /**
     * SUB-5 : le marquage, jamais la propriete - et la difference est ecrite plutot que laissee a
     * deviner. La distance en mots se compte, mais la compatibilite en genre et en nombre d'un
     * groupe nominal francais demande un lexique morphologique que ce depot n'a pas.
     * {@code nadia-prof} porte donc la propriete a la main ; ce que cette clause tient est que le
     * fichier servi <b>designe</b> au moins six items comme tels, pour qu'elle sache lesquels
     * verifier sans les chercher.
     */
    @Test
    void auMoinsUnTiersDesItemsSontMarquesSub5() {
        long marques = yamlServi().lines()
                .filter(ligne -> ligne.startsWith("  - enonce:"))
                .filter(ligne -> ligne.contains("SUB-5"))
                .count();

        assertThat(marques)
                .as("SUB-5 (nadia-prof, la condition la plus utile du lot) : au moins %d items sur"
                        + " %d doivent porter un referent qui n'est pas le groupe nominal compatible"
                        + " le plus proche a gauche, marques 'SUB-5' dans le YAML servi",
                        PLANCHER_D_UN_TIERS, NOMBRE_D_ITEMS_ATTENDU)
                .isGreaterThanOrEqualTo(PLANCHER_D_UN_TIERS);
    }

    /**
     * ADR-0060 clause 2, et c'est la condition que SUB-1 rend obligatoire ici : l'enonce cite le
     * substitut entre guillemets francais, et les espaces a l'interieur de ces guillemets sont
     * <b>insecables</b>. Un espace ordinaire y est invisible a l'oeil, sur le fichier comme a
     * l'ecran, et ne se voit qu'au retour a la ligne qu'il autorise - devant un enfant qui doit
     * justement reperer ce mot-la.
     */
    @Test
    void lesEspacesALInterieurDesGuillemetsSontInsecables() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            int ouvrant = enonce.indexOf(GUILLEMET_OUVRANT);
            int fermant = enonce.indexOf(GUILLEMET_FERMANT);
            if (ouvrant < 0 || fermant <= ouvrant) {
                defauts.add("'" + enonce + "' : aucun mot cite entre guillemets francais");
                continue;
            }
            if (enonce.charAt(ouvrant + 1) != INSECABLE || enonce.charAt(fermant - 1) != INSECABLE) {
                defauts.add("'" + enonce + "' : les espaces a l'interieur des guillemets francais"
                        + " doivent etre insecables (ADR-0060 clause 2, SYN-9, ANT-5)");
            }
        }
        assertThat(defauts).as("ADR-0060 clause 2 : le caractere decide est present").isEmpty();
    }

    /**
     * Epinglage, jamais une borne : la plus longue proposition de ce paquet. Voir la Javadoc de
     * classe - {@code nadia-prof} releve elle-meme que rien dans ses conditions ne borne la longueur
     * d'une proposition, {@code camille-po} instruit la borne, et elle n'existe pas encore. Ce test
     * dit ce que ce paquet livre et ne promet rien pour la famille ; le jour ou la borne sera
     * rendue, c'est elle qui gouvernera, et cette valeur-ci sera comparee a elle.
     */
    @Test
    void aucunePropositionNeDepasseLaLongueurDuPlusLongPersonnage() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            for (String proposition : propositions) {
                if (mots(proposition) > MOTS_DE_LA_PLUS_LONGUE_PROPOSITION) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' compte " + mots(proposition) + " mots, ce paquet en livre au plus "
                            + MOTS_DE_LA_PLUS_LONGUE_PROPOSITION);
                }
            }
        }
        assertThat(defauts)
                .as("epinglage de ce que ce paquet livre, la borne de la famille n'etant pas rendue")
                .isEmpty();
    }

    /**
     * <b>SUB-11 et SUB-12, et le motif du refus du 2026-08-31.</b> {@code nadia-prof} a joue 25
     * parties, 210 manches, les vingt textes rencontres, en n'appliquant qu'une seule regle -
     * <i>choisir la proposition qui contient le premier prenom du texte</i> - et a gagne <b>199
     * fois sur 210, soit 94,8 %</b>, contre 33,3 % de hasard. Sa verification independante sur le
     * fichier rend le meme fait : <b>19 items sur 20</b>. Le raccourci recompense etait
     * <i>"l'histoire parle de celui qu'on nomme en premier"</i>, c'est-a-dire exactement la
     * representation que cette ligne du programme existe pour remplacer.
     *
     * <p><b>Le mecanisme, et il vaut d'etre lu avant de corriger un autre paquet de cette
     * famille.</b> {@code SUB-5} demande que le referent ne soit pas le groupe compatible le plus
     * proche a gauche ; la facon la plus simple de la tenir est de placer le referent loin, donc au
     * debut. <b>Fermer tres soigneusement le raccourci de proximite a ouvert celui
     * d'anteriorite.</b> Aucune des dix conditions ne portait cette propriete, et la porte technique
     * etait verte.
     *
     * <p><b>Ce que la position se mesure sans aucune liste ecrite a la main</b> : les propositions
     * d'un item de cette famille sont des extraits de son propre texte ({@code SUB-1}), donc chacune
     * a une position dans ce texte. La strategie jugee ici est <i>prendre la proposition qui
     * apparait la premiere</i> - elle n'ouvre aucune phrase, ne lit aucun accord, et ne demande ni
     * lexique ni liste de prenoms. C'est la meme famille que la strategie d'{@code ADR-0072}, prise
     * par un autre bout : celle-la lit <b>une phrase</b>, celle-ci lit <b>un rang</b>.
     */
    private record RangsDuReferent(int premiere, int derniere, int milieu, int juges) {
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit les items deja tires. C'est ce qui
     * permet de le mordre en mutant le materiau reel en memoire plutot qu'en fabriquant un paquet
     * fautif qui ressemblerait a ce que j'imagine (lecon de s05).
     *
     * <p>Un item dont une proposition n'apparait pas dans son texte est <b>hors du domaine</b> : la
     * position n'y est pas definie. Sur ce paquet le domaine est plein - {@code SUB-1} l'exige - et
     * la clause de non-vacuite ci-dessous le verifie plutot que de le supposer.</p>
     */
    static RangsDuReferent rangsDuReferent(List<Question> items) {
        int premiere = 0;
        int derniere = 0;
        int milieu = 0;
        int juges = 0;
        for (Question item : items) {
            if (item.texteSupport().isEmpty()) {
                continue;
            }
            String texte = item.texteSupport().orElseThrow().toLowerCase(Locale.ROOT);
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            List<String> parPosition = propositions.stream()
                    .filter(proposition -> texte.contains(proposition.toLowerCase(Locale.ROOT)))
                    .sorted((gauche, droite) -> Integer.compare(
                            texte.indexOf(gauche.toLowerCase(Locale.ROOT)),
                            texte.indexOf(droite.toLowerCase(Locale.ROOT))))
                    .toList();
            if (parPosition.size() != propositions.size()) {
                continue;
            }
            juges++;
            int rang = parPosition.indexOf(item.bonneReponse());
            if (rang == 0) {
                premiere++;
            }
            if (rang == parPosition.size() - 1) {
                derniere++;
            }
            if (rang > 0 && rang < parPosition.size() - 1) {
                milieu++;
            }
        }
        return new RangsDuReferent(premiere, derniere, milieu, juges);
    }

    /**
     * <b>SUB-11, la condition opposable</b> : <i>"le referent n'est pas le premier personnage nomme
     * du texte sur au moins la moitie des items"</i>. Unite de compte : l'item.
     */
    @Test
    void leReferentNEstPasLaPremierePropositionDuTexteSurAuMoinsLaMoitieDesItems() {
        RangsDuReferent rangs = rangsDuReferent(tousLesItems(paquet()));

        assertThat(rangs.juges())
                .as("ADR-0051 : cette clause ne juge rien si aucun item ne porte ses trois"
                        + " propositions dans son texte - SUB-1 l'exige, et le domaine est donc plein")
                .isEqualTo(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(rangs.premiere())
                .as("SUB-11 : le referent est la premiere proposition du texte sur %d items sur %d."
                        + " Au-dela de la moitie, un enfant gagne en prenant celui qu'on nomme en"
                        + " premier, sans lire la phrase du substitut - et c'est la representation"
                        + " que cette ligne du programme existe pour remplacer",
                        rangs.premiere(), rangs.juges())
                .isLessThanOrEqualTo(NOMBRE_D_ITEMS_ATTENDU / 2);
    }

    /**
     * <b>Ce que la correction a du ajouter, et ce n'est pas une condition de {@code nadia-prof} :
     * c'est le constat que sa condition, prise seule, est satisfiable par le raccourci
     * symetrique.</b>
     *
     * <p>Un paquet ou le referent serait <b>toujours le dernier</b> nomme tiendrait {@code SUB-11}
     * a 0 % et rendrait pourtant le jeu gagnable par <i>"prendre celui qu'on nomme en dernier"</i>.
     * La premiere reecriture de ce paquet est passee par la : {@code SUB-11} y valait deja 35 %,
     * mais la position <i>derniere</i> valait <b>55 %</b>. <b>Aucune position ne doit etre
     * majoritaire</b>, et c'est le seuil d'{@code ADR-0072}, repris tel quel plutot que renegocie.
     *
     * <p>Je le remonte comme un fait plutot que comme une regle : la condition qui gouverne est
     * celle de {@code nadia-prof}, et c'est a elle de dire si elle veut ce complement. Ici il est
     * epingle sur ce paquet, pas oppose a la famille.</p>
     */
    @Test
    void aucunePositionDeLaBonneReponseDansLeTexteNEstMajoritaire() {
        RangsDuReferent rangs = rangsDuReferent(tousLesItems(paquet()));
        int majorite = NOMBRE_D_ITEMS_ATTENDU / 2;

        assertThat(rangs.premiere())
                .as("prendre la premiere proposition du texte gagne %d items sur %d",
                        rangs.premiere(), rangs.juges())
                .isLessThanOrEqualTo(majorite);
        assertThat(rangs.derniere())
                .as("prendre la derniere proposition du texte gagne %d items sur %d - le raccourci"
                        + " symetrique de celui qui a coute ce sprint", rangs.derniere(), rangs.juges())
                .isLessThanOrEqualTo(majorite);
        assertThat(rangs.milieu())
                .as("prendre celle du milieu gagne %d items sur %d", rangs.milieu(), rangs.juges())
                .isLessThanOrEqualTo(majorite);
    }

    /**
     * ADR-0013 obligation 3 : le materiau reel mute en memoire. La bonne reponse est recopiee en
     * tete du texte de chaque item, ce qui la place en premiere position sans rien changer d'autre -
     * c'est la forme exacte du paquet refuse le 2026-08-31, ou 19 items sur 20 la portaient deja.
     */
    @Test
    void unReferentRemisEnTeteDuTexteFaitEchouerLaClause() {
        List<Question> mutes = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            mutes.add(new Question(item.id(), item.enonce(), item.bonneReponse(), item.distracteurs(),
                    item.indice(), item.explication(), item.partDesignee(),
                    java.util.Optional.of(item.bonneReponse() + " arrive. "
                            + item.texteSupport().orElseThrow())));
        }

        RangsDuReferent rangs = rangsDuReferent(mutes);

        assertThat(rangs.premiere())
                .as("le referent remis en tete de chaque texte doit faire remonter la position"
                        + " premiere au-dela de la moitie, sans quoi cette clause ne mord pas")
                .isGreaterThan(NOMBRE_D_ITEMS_ATTENDU / 2);
    }

    /**
     * <b>SUB-12</b> : <i>"aucune propriete de forme ne distingue la bonne reponse de ses
     * distracteurs : si la bonne reponse est un couple X et Y, au moins un distracteur en est un
     * aussi"</i>. Sur le paquet refuse, les cinq items a couple portaient deux distracteurs en
     * <i>"les ..."</i>, et la regle <i>"prendre celle qui contient et"</i> gagnait <b>cinq fois sur
     * cinq</b> sans ouvrir le texte.
     */
    @Test
    void aucunePropositionEnCoupleNEstSeuleDeSonEspeceDansSonItem() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            if (!item.bonneReponse().contains(MARQUE_DU_COUPLE)) {
                continue;
            }
            boolean unDistracteurEnCouple = item.distracteurs().stream()
                    .anyMatch(distracteur -> distracteur.contains(MARQUE_DU_COUPLE));
            if (!unDistracteurEnCouple) {
                defauts.add("'" + item.enonce() + "' : la bonne reponse '" + item.bonneReponse()
                        + "' est le seul couple de son item, et la regle 'prendre celle qui"
                        + " contient et' gagne sans ouvrir le texte (SUB-12)");
            }
        }
        assertThat(defauts)
                .as("SUB-12 : aucune propriete de forme ne distingue la bonne reponse de ses"
                        + " distracteurs")
                .isEmpty();
    }

    /**
     * <b>Epinglage, et surtout pas une condition : personne ne me l'a demande.</b> L'indice ne
     * s'affiche qu'apres un premier echec, donc il ne fait jamais gagner du premier coup et ne
     * relevait d'aucune des mesures de {@code nadia-prof} sur ce paquet. Mais elle a releve <b>le
     * meme jour, sur un autre paquet</b>, qu'un indice qui designe la reponse est une marque de
     * surface, et elle n'a pas refuse faute d'une condition qui la gouverne.
     *
     * <p>Sur la version refusee, l'indice designait une phrase ou <b>un seul candidat figurait</b>
     * sur 12 items sur 20. Ce paquet-ci en porte zero : chaque indice designe une portion de texte
     * ou au moins deux des trois propositions se trouvent, donc il dit ou relire ({@code SUB-9})
     * sans dire quoi repondre. <b>Ce que ce test tient est ce chiffre-la, sur ce paquet ; il
     * n'oppose rien a la famille</b>, et c'est a {@code nadia-prof} de decider s'il lui faut une
     * condition.</p>
     */
    @Test
    void aucunIndiceNeDesigneUnePortionDeTexteOuLaBonneReponseEstSeule() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String portion = portionDesigneeParLIndice(item);
            if (portion == null) {
                continue;
            }
            List<String> presentes = new ArrayList<>();
            for (String proposition : propositionsDe(item)) {
                if (portion.toLowerCase(Locale.ROOT).contains(proposition.toLowerCase(Locale.ROOT))) {
                    presentes.add(proposition);
                }
            }
            if (presentes.size() == 1 && presentes.get(0).equals(item.bonneReponse())) {
                defauts.add("'" + item.enonce() + "' : l'indice designe une portion de texte ou la"
                        + " bonne reponse est le seul candidat, il donne donc la reponse au lieu de"
                        + " dire quoi relire");
            }
        }
        assertThat(defauts)
                .as("epinglage de ce que ce paquet livre - zero - et non une condition opposable")
                .isEmpty();
    }

    private static List<String> propositionsDe(Question item) {
        List<String> propositions = new ArrayList<>();
        propositions.add(item.bonneReponse());
        propositions.addAll(item.distracteurs());
        return propositions;
    }

    /**
     * La portion de texte que l'indice designe, quand il en designe une. Les quatre libelles employes
     * par ce paquet sont lus ici ; un libelle qui n'en serait aucun rend {@code null} et sort du
     * comptage plutot que d'etre devine.
     */
    private static String portionDesigneeParLIndice(Question item) {
        String texte = item.texteSupport().orElseThrow();
        List<String> phrases = phrasesDe(texte);
        String libelle = item.indice().orElse("").toLowerCase(Locale.ROOT);
        if (libelle.contains("deux premi")) {
            return String.join(" ", phrases.subList(0, Math.min(2, phrases.size())));
        }
        if (libelle.contains("deux derni")) {
            return String.join(" ", phrases.subList(Math.max(0, phrases.size() - 2), phrases.size()));
        }
        if (libelle.contains("premi")) {
            return phrases.get(0);
        }
        if (libelle.contains("milieu")) {
            return phrases.size() > 1 ? phrases.get(1) : null;
        }
        if (libelle.contains("derni")) {
            return phrases.get(phrases.size() - 1);
        }
        return null;
    }

    /** Le decoupage en phrases sur les trois ponctuations fortes, celui d'ADR-0072. */
    private static List<String> phrasesDe(String texte) {
        List<String> phrases = new ArrayList<>();
        StringBuilder courante = new StringBuilder();
        for (int rang = 0; rang < texte.length(); rang++) {
            char caractere = texte.charAt(rang);
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
}
