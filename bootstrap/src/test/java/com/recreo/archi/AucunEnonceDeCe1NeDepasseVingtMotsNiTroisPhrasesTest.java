package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0101, route par nadia-prof : <b>au CE1, un enonce compte au plus vingt mots et au plus trois
 * phrases, et la derniere est la question.</b>
 *
 * <p><b>La barre n'est pas de moi.</b> Elle est posee le 2026-09-21 par nadia-prof, sous les
 * identifiants {@code LON-CE1-1} et {@code LON-CE1-2}
 * ({@code squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md}, section 2), et elle y ecrit
 * que sa convention de comptage est celle d'ADR-0089 <i>"reprise sans un mot de changement"</i>.
 * Ce fichier ne la recopie donc pas : il appelle
 * {@link AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest#mots(String)} et
 * {@link AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest#phrases(String)}, les deux methodes qui
 * portent deja cette convention. Un second exemplaire d'une convention rancit (ADR-0061), et deux
 * barres qui divergeraient sur ce qu'est un mot seraient pires que pas de barre du tout.
 *
 * <p><b>Ce que ce garde-fou existe pour empecher, et ce n'est pas hypothetique.</b> Entre le
 * 2026-09-21, jour ou la barre est posee, et ce jour, <b>quatre enonces l'ont franchie sans qu'aucun
 * rouge ne le dise</b> : les items 11 a 14 de {@code ce1-longueurs.yaml}, qui comptent 22, 27, 26 et
 * 26 mots, dans un paquet dont le Done pedagogique a ete prononce a s39, soit <b>apres</b>
 * l'arbitrage. La cause est nommee par nadia-prof elle-meme, qui l'a verifiee dans le code plutot que
 * dans un titre : {@code AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest} porte
 * {@code NIVEAU_BORNE = "CP"} et sa population est <i>les enonces des paquets dont le descripteur
 * porte le niveau borne</i>. <b>Un paquet de CE1 n'y etait pas exempte : il n'y etait pas vu.</b>
 *
 * <p><b>Les deux barres sont differentes, et c'est mesure, pas affirme</b> (voir
 * {@link #lesDeuxBarresNeMesurentPasLaMemeChose()}). Sur les enonces de CE1 servis, la barre du CP
 * (15 mots, 2 phrases) rendrait <b>18</b> enonces trop longs et <b>6</b> defauts de forme ; la barre
 * du CE1 en rend <b>4</b> et <b>0</b>. Transposer la barre du CP au CE1 aurait pose un poteau que
 * nadia-prof n'a pas pose, sur une population qu'elle avait comptee autrement.
 *
 * <p><b>Le cliquet plutot que l'interdiction seche.</b> Les quatre enonces de {@code ce1-longueurs}
 * sont deja valides ; nadia-prof a refuse d'avance de rouvrir ce Done - <i>"une barre qu'on releve
 * apres qu'une equipe l'a atteinte fait de ma porte un caprice"</i> (arbitrage du lot 2 de s43,
 * section 8). La dette les <b>enregistre</b>, elle ne les absout pas, et son plafond vaut
 * <b>exactement</b> leur nombre : un enonce trop long de plus est rouge, un de moins l'est aussi
 * (ADR-0067, ADR-0087).
 *
 * <p><b>Perimetre derive</b> (ADR-0013 obligation 1) : les paquets viennent du repertoire de contenu
 * servi de chaque module declare au pom agregateur, et le niveau est lu dans le descripteur
 * (ADR-0078), jamais dans un nom de fichier ni dans le catalogue. Seul le champ {@code enonce} est
 * juge : ni les propositions, ni l'indice, ni la consigne, que nadia-prof n'a pas comptes et sur
 * lesquels elle ne pose rien.
 *
 * <p><b>Ce n'est pas une {@code ArchRule}</b> : ArchUnit lit du bytecode, et la longueur d'un enonce
 * de paquet YAML n'y est pas une propriete lisible. Le compte de regles ArchUnit du depot est
 * inchange.
 */
class AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest {

    /** Le niveau que la barre borne, lu dans le descripteur du paquet (ADR-0078). */
    private static final String NIVEAU_BORNE = "CE1";

    /** LON-CE1-1 : au CE1, un enonce compte au plus vingt mots. */
    private static final int MOTS_MAXIMUM = 20;

    /** LON-CE1-2 : au CE1, un enonce compte au plus trois phrases, la derniere etant la question. */
    private static final int PHRASES_MAXIMUM = 3;

    /**
     * LON-CE1-1, cliquet exact. <b>Etat mesure le 2026-09-25, arbre de {@code 5bd99d74}</b> : sur les
     * 144 enonces de CE1 servis par sept paquets, {@code ce1-longueurs.yaml} en porte quatre au-dela
     * de {@link #MOTS_MAXIMUM} mots - ses items 11 (22 mots), 12 (27), 13 (26) et 14 (26). Aucun autre
     * paquet de CE1 n'en porte, et tout paquet de CE1 absent de cette table est borne a zero.
     *
     * <p>Ce chiffre n'est pas reporte d'un comptage a la main : il est <b>rejoue par le garde-fou
     * lui-meme</b> a chaque execution de {@link #laDetteNePeutNiGrandirNiPourrir()}, qui compare la
     * valeur declaree au nombre reel d'enonces trop longs du paquet nomme. Une dette qui ne nomme plus
     * le nombre reel n'est pas un cliquet.
     */
    private static final Map<String, Integer> DETTE_DE_LONGUEUR = Map.of("ce1-longueurs.yaml", 4);

    /** La somme exacte de la dette du 2026-09-25. Ne remonte jamais sans un diff visible. */
    private static final int PLAFOND_DE_DETTE = 4;

    /**
     * LON-CE1-2 n'a <b>aucune</b> exemption, et ce n'est pas une clause sans objet (ADR-0051) : elle
     * s'execute sur les 144 enonces de CE1 servis et elle en trouve <b>zero</b> fautif. Un cliquet a
     * plafond zero sur une population reelle, pas une clause vide.
     */
    private static final int PLAFOND_DE_DETTE_DE_FORME = 0;

    /** Les enonces de CE1 servis par le depot, perimetre derive comme au CP. */
    private static List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enoncesDuDepot() {
        return enoncesDuNiveau(NIVEAU_BORNE);
    }

    private static List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enoncesDuNiveau(
            String niveau) {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        return AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.enonces(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine),
                niveau);
    }

    private static int mots(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce enonce) {
        return AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots(enonce.texte()).size();
    }

    private static List<String> phrases(
            AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce enonce) {
        return AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.phrases(enonce.texte());
    }

    /**
     * LON-CE1-1. Un paquet est fautif quand le nombre de ses enonces au-dela de {@link #MOTS_MAXIMUM}
     * mots <b>differe</b> de ce que la dette declare pour lui, zero par defaut.
     */
    static List<String> defautsDeLongueur(
            List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enonces,
            Map<String, Integer> dette) {
        Map<String, List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce>> tropLongs =
                new LinkedHashMap<>();
        Map<String, Integer> paquets = new LinkedHashMap<>();
        for (AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce enonce : enonces) {
            paquets.putIfAbsent(enonce.paquet(), 0);
            if (mots(enonce) > MOTS_MAXIMUM) {
                tropLongs.computeIfAbsent(enonce.paquet(), nom -> new ArrayList<>()).add(enonce);
            }
        }
        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets.keySet()) {
            List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> fautifs =
                    tropLongs.getOrDefault(paquet, List.of());
            int tolere = dette.getOrDefault(paquet, 0);
            if (fautifs.size() == tolere) {
                continue;
            }
            List<String> details = fautifs.stream()
                    .map(enonce -> String.format("item %d (%d mots)", enonce.rang(), mots(enonce)))
                    .toList();
            defauts.add(String.format(
                    "%s : %d enonces depassent %d mots la ou la dette en tolere %d - %s. Au CE1, un"
                            + " enonce compte au plus %d mots (LON-CE1-1, nadia-prof, 2026-09-21 ;"
                            + " ADR-0101)",
                    paquet, fautifs.size(), MOTS_MAXIMUM, tolere,
                    details.isEmpty() ? "aucun" : String.join(", ", details), MOTS_MAXIMUM));
        }
        return defauts;
    }

    /**
     * LON-CE1-2. Un enonce est fautif quand il compte plus de {@link #PHRASES_MAXIMUM} phrases, ou
     * que sa derniere phrase n'est pas la question alors qu'il en compte plusieurs.
     */
    static List<String> defautsDeForme(
            List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enonces) {
        List<String> defauts = new ArrayList<>();
        for (AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce enonce : enonces) {
            List<String> phrases = phrases(enonce);
            if (phrases.size() > PHRASES_MAXIMUM) {
                defauts.add(String.format(
                        "%s item %d : %d phrases. Au CE1, un enonce compte au plus %d phrases"
                                + " (LON-CE1-2, nadia-prof, 2026-09-21 ; ADR-0101) - \"%s\"",
                        enonce.paquet(), enonce.rang(), phrases.size(), PHRASES_MAXIMUM,
                        enonce.texte()));
                continue;
            }
            if (phrases.size() > 1 && !phrases.get(phrases.size() - 1).strip().endsWith("?")) {
                defauts.add(String.format(
                        "%s item %d : l'enonce compte %d phrases et la derniere n'est pas la"
                                + " question - \"%s\" (LON-CE1-2, nadia-prof, 2026-09-21 ;"
                                + " ADR-0101)",
                        enonce.paquet(), enonce.rang(), phrases.size(),
                        phrases.get(phrases.size() - 1)));
            }
        }
        return defauts;
    }

    static void verifierLesEnonces(
            List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enonces,
            Map<String, Integer> dette) {
        assertThat(enonces)
                .as("ADR-0051 : sans enonce de %s, ce garde-fou n'examine aucun objet", NIVEAU_BORNE)
                .isNotEmpty();
        List<String> defauts = new ArrayList<>(defautsDeLongueur(enonces, dette));
        defauts.addAll(defautsDeForme(enonces));
        assertThat(defauts)
                .as("ADR-0101 : au %s, un enonce compte au plus %d mots et au plus %d phrases",
                        NIVEAU_BORNE, MOTS_MAXIMUM, PHRASES_MAXIMUM)
                .isEmpty();
    }

    @Test
    void leContenuServiDeCe1EstConforme() {
        verifierLesEnonces(enoncesDuDepot(), DETTE_DE_LONGUEUR);
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Mesure du
     * 2026-09-25, arbre de {@code 5bd99d74}, reacteur complet : <b>sept paquets de CE1, 144
     * enonces</b>. Les bornes sont larges a dessein - le contenu de CE1 grossit a chaque sprint et ce
     * test constate qu'un vert porte sur tous les enonces de CE1 servis, pas sur une poignee de
     * paquets qu'un chargement aurait laisse tomber.
     */
    @Test
    void leBalayageExamineTousLesEnoncesDeCe1() {
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enonces = enoncesDuDepot();
        assertThat(enonces)
                .as("ADR-0101 : le contenu servi porte plus d'une centaine d'enonces de CE1")
                .hasSizeGreaterThan(100);
        assertThat(enonces.stream()
                .map(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce::paquet)
                .distinct().count())
                .as("ADR-0101 : ces enonces viennent de plusieurs paquets de CE1")
                .isGreaterThan(5);
    }

    /**
     * ADR-0067 : le cliquet de LON-CE1-1. La dette ne peut ni grandir - la somme est ecrite et
     * comparee - ni pourrir : chaque paquet nomme doit encore etre un paquet de CE1 servi, et porter
     * <b>exactement</b> le nombre d'enonces trop longs qu'il declare. Une dette payee rend ce test
     * rouge, ce qui est le seul moyen de forcer sa baisse dans le diff qui la paie.
     */
    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_LONGUEUR.values().stream().mapToInt(Integer::intValue).sum())
                .as("ADR-0067 : le plafond vaut exactement la taille de la dette du 2026-09-25")
                .isEqualTo(PLAFOND_DE_DETTE);
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> enonces = enoncesDuDepot();
        for (Map.Entry<String, Integer> entree : DETTE_DE_LONGUEUR.entrySet()) {
            List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> duPaquet =
                    enonces.stream().filter(enonce -> enonce.paquet().equals(entree.getKey()))
                            .toList();
            assertThat(duPaquet)
                    .as("%s porte une dette de longueur et n'est plus un paquet de CE1 servi",
                            entree.getKey())
                    .isNotEmpty();
            assertThat(duPaquet.stream().filter(enonce -> mots(enonce) > MOTS_MAXIMUM).count())
                    .as("%s declare %d enonces trop longs : une dette qui ne nomme plus le nombre"
                            + " reel n'est pas un cliquet", entree.getKey(), entree.getValue())
                    .isEqualTo(entree.getValue().longValue());
        }
        assertThat(defautsDeForme(enonces))
                .as("ADR-0067 : LON-CE1-2 est a plafond %d sur le contenu servi, sans exemption",
                        PLAFOND_DE_DETTE_DE_FORME)
                .hasSize(PLAFOND_DE_DETTE_DE_FORME);
    }

    /**
     * ADR-0013 obligation 3, et <b>la morsure qui compte</b>. Le materiau fautif n'est pas de ma main
     * et n'est pas une fixture : c'est le contenu de CE1 <b>reellement servi</b>, juge sans sa dette.
     * La lecon de s05 est que la fixture de l'auteur d'une regle ressemble a ce qu'il imagine ; ici
     * l'objet mute est la <b>declaration de dette</b>, pas le corpus, et le corpus reste celui de la
     * production.
     *
     * <p>Ce test est aussi la re-verification du chiffre 4 : il ne le lit pas dans la constante, il le
     * fait nommer par le garde-fou, item par item, avec le compte de mots de chacun.
     */
    @Test
    void sansSaDetteLeContenuServiDeCe1FaitRougirLaBarre() {
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> servis = enoncesDuDepot();

        assertThatThrownBy(() -> verifierLesEnonces(servis, Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ce1-longueurs.yaml : 4 enonces depassent 20 mots la ou la"
                        + " dette en tolere 0")
                .hasMessageContaining("item 11 (22 mots)")
                .hasMessageContaining("item 12 (27 mots)")
                .hasMessageContaining("item 13 (26 mots)")
                .hasMessageContaining("item 14 (26 mots)");
    }

    /**
     * ADR-0067, l'autre sens du cliquet : une dette <b>surevaluee</b> est rouge elle aussi. Sans
     * cette moitie, un plafond confortable laisserait entrer des enonces trop longs jusqu'a sa
     * valeur, et le cliquet ne serait qu'un quota.
     */
    @Test
    void uneDetteQuiNeVautPasLeCompteReelEstRouge() {
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> servis = enoncesDuDepot();

        assertThatThrownBy(() -> verifierLesEnonces(servis, Map.of("ce1-longueurs.yaml", 5)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la dette en tolere 5");
        assertThatThrownBy(() -> verifierLesEnonces(servis, Map.of("ce1-longueurs.yaml", 3)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la dette en tolere 3");
    }

    /**
     * ADR-0013 obligation 3. LON-CE1-2 <b>n'a aucun materiau reel</b> : les enonces de CE1 servis la
     * tiennent tous. Ce jeu fautif est donc <b>de ma main, et je le declare comme tel</b> plutot que
     * de laisser cette clause sans preuve qu'elle mord. Les deux enonces sont ecrits sur le patron du
     * contenu de CE1 - une situation posee en deux temps, puis la question.
     */
    @Test
    void unEnonceDeQuatrePhrasesOuDontLaDerniereNInterrogePasEstRouge() {
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> fautifs = List.of(
                new AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce("paquet-fautif.yaml",
                        1, "Lea entre. Elle sort. Elle revient. Quelle heure est-il ?"),
                new AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce("paquet-fautif.yaml",
                        2, "Quelle est la duree ? Tom part a 9 h et arrive a 10 h."));

        List<String> defauts = defautsDeForme(fautifs);
        assertThat(defauts)
                .as("LON-CE1-2 : quatre phrases pour le premier, derniere phrase non interrogative"
                        + " pour le second")
                .hasSize(2);
        assertThat(defautsDeLongueur(fautifs, Map.of()))
                .as("ces deux enonces tiennent LON-CE1-1 : la morsure porte sur la forme, pas sur la"
                        + " longueur")
                .isEmpty();
    }

    /**
     * <b>Pourquoi cette barre n'est pas celle du CP, mesure sur le meme corpus.</b> nadia-prof a pose
     * 20 mots et 3 phrases au CE1 la ou elle avait pose 15 et 2 au CP ; transposer la barre du CP
     * aurait rendu rouges des enonces qu'elle n'a jamais juges trop longs. Ce test compare les deux
     * barres sur les enonces de CE1 reellement servis plutot que d'affirmer la difference dans un
     * commentaire.
     */
    @Test
    void lesDeuxBarresNeMesurentPasLaMemeChose() {
        List<AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce> ce1 = enoncesDuDepot();

        long auDelaDeLaBarreDuCp = ce1.stream().filter(enonce -> mots(enonce) > 15).count();
        long auDelaDeLaBarreDuCe1 = ce1.stream().filter(enonce -> mots(enonce) > MOTS_MAXIMUM)
                .count();

        assertThat(auDelaDeLaBarreDuCp)
                .as("la barre du CP rendrait des dizaines d'enonces de CE1 trop longs")
                .isGreaterThan(auDelaDeLaBarreDuCe1 * 3);
        assertThat(auDelaDeLaBarreDuCe1)
                .as("la barre du CE1 n'en rend que la dette enregistree")
                .isEqualTo(PLAFOND_DE_DETTE);
        assertThat(ce1.stream().filter(enonce -> phrases(enonce).size() > 2).count())
                .as("des enonces de CE1 comptent trois phrases : la barre du CP en ferait des"
                        + " defauts de forme, celle du CE1 les accepte")
                .isPositive();
    }

    /**
     * <b>Le perimetre est prouve par son bord.</b> Les deux barres sont voisines et leurs deux
     * populations doivent rester disjointes : aucun paquet ne peut etre juge par les deux, sans quoi
     * un enonce de 18 mots serait vert ici et rouge la. Le niveau etant lu dans le descripteur
     * (ADR-0078), la disjonction est une propriete du code ; ce test la constate sur le contenu
     * vivant.
     */
    @Test
    void lesPopulationsDuCpEtDuCe1SontDisjointes() {
        List<String> paquetsDeCe1 = enoncesDuDepot().stream()
                .map(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce::paquet).distinct()
                .toList();
        List<String> paquetsDeCp = enoncesDuNiveau("CP").stream()
                .map(AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.Enonce::paquet).distinct()
                .toList();

        assertThat(paquetsDeCp).isNotEmpty();
        assertThat(paquetsDeCe1).isNotEmpty();
        assertThat(paquetsDeCe1)
                .as("un paquet juge par les deux barres serait vert d'un cote et rouge de l'autre")
                .doesNotContainAnyElementsOf(paquetsDeCp);
    }
}
