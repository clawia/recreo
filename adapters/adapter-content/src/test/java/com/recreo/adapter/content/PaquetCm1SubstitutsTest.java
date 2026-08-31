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
}
