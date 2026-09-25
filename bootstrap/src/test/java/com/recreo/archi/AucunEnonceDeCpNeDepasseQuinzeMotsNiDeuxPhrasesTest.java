package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0089, route par nadia-prof : <b>au CP, un enonce compte au plus quinze mots et au plus deux
 * phrases, et quand il en compte deux, la seconde est la question.</b>
 *
 * <p><b>La barre n'est pas de moi, et sa convention de comptage non plus.</b> Elle est posee le
 * 2026-09-08 par nadia-prof, sections 16.1 et 16.4 de l'arbitrage prealable de s32, sous les
 * identifiants {@code LON-1} et {@code LON-2}. Elle a ecrit la convention <b>avant</b> le chiffre,
 * pour que la barre soit verifiable, et elle a ecrit que la forme du test m'appartient :
 * <i>"Je dis la propriete ; la forme du test appartient a theo-archi. Tant qu'il n'existe pas,
 * LON-1 et LON-2 sont tenues par moi, en comptant."</i> Ce fichier est ce qui la dispense de
 * compter.
 *
 * <p><b>Le mot, tel qu'elle le definit</b> : une suite de caracteres separee par des espaces et
 * contenant au moins une lettre ou un chiffre ; une forme elidee compte pour un
 * ({@code qu'il}, {@code l'ami}, {@code Qu'est-ce}). Le trait d'union et l'apostrophe ne separent
 * donc rien, et l'espace insecable que {@code TYP-1} pose devant un {@code ?} final en fait un
 * token sans lettre ni chiffre, donc pas un mot.
 *
 * <p><b>La phrase : sa definition litterale est fausse, et c'est mesure.</b> Elle ecrit
 * <i>"un segment termine par un point, un point d'interrogation ou un point d'exclamation"</i>.
 * Applique tel quel, ce critere coupe {@code "0 + ? = 10"} en deux phrases dont la seconde,
 * {@code "= 10"}, n'est pas une question : <b>22 enonces de {@code cp-complements-10} et de
 * {@code cp-complements-10-saisie} deviennent rouges</b>, sur des paquets servis dont aucune reserve
 * de longueur ne parle, et que son propre releve ne compte pas parmi les enonces a deux phrases -
 * elle y nomme quatre paquets, et ni l'un ni l'autre n'y figure. Le {@code ?} y est un <b>trou a
 * completer</b>, pas une ponctuation.
 *
 * <p><b>Le critere retenu, derive et non regle</b> : un {@code .}, un {@code ?} ou un {@code !} ne
 * termine une phrase que s'il <b>termine le texte</b> ou s'il est <b>suivi d'une majuscule</b>. Il
 * ne nomme aucun paquet, aucun signe de calcul et aucune exception : c'est la meme derivation que
 * celle par laquelle {@code ADR-0086} separe une etiquette de categorie d'une phrase reconnue.
 * Verification sur l'arbre ou nadia-prof a compte ({@code 690f0cde}) : ce critere rend
 * <b>0 enonce fautif</b> la ou le critere litteral en rend 22, et il reproduit ses sept chiffres de
 * population.
 *
 * <p><b>Ma mesure reproduit la sienne, chiffre par chiffre, avant de poser quoi que ce soit</b>
 * (arbre {@code 690f0cde}, l'etat sur lequel elle a compte ; JDK que la machine virtuelle des tests
 * rapporte, 21.0.10) : 33 paquets de CP, <b>506 enonces</b>, mediane <b>6</b> mots, 90e centile
 * <b>13</b>, 95e centile <b>15</b>, maximum <b>30</b>, <b>23 enonces au-dela de 15 mots</b>,
 * <b>7 phrases au-dela de 15 mots</b>. Huit chiffres reproduits a l'identique sur huit ; le
 * neuvieme, ses <b>606 phrases</b>, se reproduit sous son critere litteral et vaut 584 sous le
 * critere derive ci-dessus.
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1), et trois poteaux qu'elle n'a pas poses.</b> Les
 * paquets viennent du repertoire de contenu servi de chaque module declare au pom agregateur, par
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu(Path)} ; le niveau
 * est <b>lu dans le descripteur du paquet</b> (ADR-0078), jamais dans le catalogue ni dans un nom de
 * fichier. Ce garde-fou ne juge que le champ {@code enonce} des paquets dont le descripteur porte
 * {@code CP}. Elle dit en toutes lettres que sa barre ne vaut ni pour le CM1, ni pour les
 * propositions, ni pour l'indice, ni pour la consigne, faute d'avoir compte ces populations ; le
 * cout de chaque debordement est mesure et non suppose, sur l'arbre fusionne de s32 :
 *
 * <ul>
 *   <li><b>CM1 : 165 enonces au-dela de 15 mots</b> (maximum 36) et <b>35 au-dela de deux
 *       phrases</b>. Un garde-fou etendu au CM1 serait rouge sur 200 objets qu'aucune barre ne
 *       borne, et {@code HG-R4} resterait qualitative pendant qu'un test prononcerait a sa place.
 *   <li><b>Indice de CP : 48 au-dela de 15 mots</b> (maximum 23, {@code cp-position-syllabe}). Un
 *       debordement sur l'indice serait rouge sur 48 objets d'un coup.
 *   <li><b>Propositions de CP : 0 au-dela de 15 mots.</b> C'est <b>le cas dangereux</b>, et le seul
 *       des trois : etendre la barre aux propositions serait gratuit aujourd'hui, donc invisible,
 *       et poserait un poteau qu'elle n'a pas pose sur une population qu'elle n'a pas comptee. Une
 *       regle qu'on ajoute parce qu'elle ne coute rien est une regle que personne n'a decidee.
 * </ul>
 *
 * <p><b>Ce n'est pas une {@code ArchRule}, et le compte de regles ArchUnit du depot est
 * inchange.</b> ArchUnit lit du bytecode ; la longueur d'un enonce de paquet YAML n'y est pas une
 * propriete lisible.
 */
class AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest {

    /** Le niveau que la barre borne, lu dans le descripteur du paquet (ADR-0078). */
    private static final String NIVEAU_BORNE = "CP";

    /** LON-1 : au CP, un enonce compte au plus quinze mots. */
    private static final int MOTS_MAXIMUM = 15;

    /** LON-2 : au CP, un enonce compte au plus deux phrases. */
    private static final int PHRASES_MAXIMUM = 2;

    /**
     * LON-1, cliquet exact. <b>Les trois paquets nommes portent chacun une reserve de longueur
     * ouverte par nadia-prof le 2026-09-08, non bloquante, et sa barre ne vaut pas retroactivement
     * sur eux</b> - elle l'ecrit : <i>"LON-1 et LON-2 ne valent que pour les enonces ecrits ou
     * reecrits a partir du 2026-09-08"</i>. Cette dette <b>enregistre</b> trois reserves deja
     * ouvertes, elle n'en cree aucune et n'inverse aucun verdict (frontiere d'ADR-0079).
     *
     * <p><b>Le mecanisme est l'egalite, jamais un plafond</b> (ADR-0087, amendement de la cloture de
     * s31) : la valeur est le nombre <b>exact</b> d'enonces au-dela de {@link #MOTS_MAXIMUM} que le
     * paquet porte encore. Un enonce trop long de plus est rouge ; un de moins est rouge aussi, ce
     * qui force a baisser le chiffre dans le diff qui paie. Tout paquet de CP absent de cette table
     * est borne a zero.
     *
     * <p><b>Etat mesure le 2026-09-08, arbre fusionne de s32, apres {@code S32-4} et
     * {@code S32-EMCCP07}</b> : {@code cp-emc-emotions-autrui} 1 ({@code EMO-R1}),
     * {@code cp-emc-exclusion} 1 ({@code EXC-R4}), {@code cp-emc-formules-de-politesse} 3
     * ({@code POL-R1}). Cinq enonces sur 528, contre 23 sur 506 quand la barre a ete posee.
     */
    private static final Map<String, Integer> DETTE_DE_LONGUEUR = Map.of(
            "cp-emc-emotions-autrui.yaml", 1,
            "cp-emc-exclusion.yaml", 1,
            "cp-emc-formules-de-politesse.yaml", 3);

    /** La somme exacte de la dette du 2026-09-08. Ne remonte jamais sans un diff visible. */
    private static final int PLAFOND_DE_DETTE = 5;

    /**
     * LON-2 n'a <b>aucune</b> exemption, et ce n'est pas une regle a population vide (ADR-0051) :
     * elle s'execute sur les <b>528 enonces de CP servis</b>, dont <b>82 comptent exactement deux
     * phrases</b> - la population de sa seconde moitie -, et elle en trouve <b>zero</b> fautif. Un
     * cliquet a plafond zero sur 528 objets, pas une clause sans objet.
     */
    private static final int PLAFOND_DE_DETTE_DE_FORME = 0;

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest");

    /**
     * Les espaces que le contenu servi emploie reellement : l'espace ordinaire et l'espace
     * insecable que {@code TYP-1} pose devant un {@code ?} final. {@code \\s} de Java ne contient
     * pas U+00A0, et s'en tenir a {@code \\s} rendrait {@code "regle ?"} en un seul token.
     */
    private static final String ESPACES = "[\\s\\u00A0]";

    /** Un token est un mot des lors qu'il porte une lettre ou un chiffre (convention 16.1). */
    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    /**
     * Un {@code .}, un {@code ?} ou un {@code !} termine une phrase quand il termine le texte ou
     * qu'une majuscule le suit. Derivation, pas reglage : voir la Javadoc de la classe et les
     * chiffres qui l'ont decidee.
     */
    private static final Pattern FIN_DE_PHRASE = Pattern.compile(
            "[.?!](?=" + ESPACES + "*$)|[.?!](?=" + ESPACES + "+\\p{Lu})");

    /**
     * Un enonce servi, avec de quoi le nommer dans un message d'echec.
     *
     * <p>Visible dans le paquet depuis ADR-0101 : la barre du CE1 lit la meme population par
     * {@link #enonces(List, String)} et compte ses mots par {@link #mots(String)}, plutot que de
     * recopier une convention de comptage qui rancirait en double (ADR-0061).
     */
    record Enonce(String paquet, int rang, String texte) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les mots d'un texte, sous la convention de comptage de nadia-prof (section 16.1). */
    static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String token : texte.strip().split(ESPACES + "+")) {
            if (LETTRE_OU_CHIFFRE.matcher(token).find()) {
                mots.add(token);
            }
        }
        return mots;
    }

    /** Les phrases d'un texte, sous le critere derive de {@link #FIN_DE_PHRASE}. */
    static List<String> phrases(String texte) {
        List<String> phrases = new ArrayList<>();
        Matcher fins = FIN_DE_PHRASE.matcher(texte);
        int debut = 0;
        while (fins.find()) {
            String phrase = texte.substring(debut, fins.end()).strip();
            if (!mots(phrase).isEmpty()) {
                phrases.add(phrase);
            }
            debut = fins.end();
        }
        String reste = texte.substring(debut).strip();
        if (!mots(reste).isEmpty()) {
            phrases.add(reste);
        }
        return phrases;
    }

    /** Les enonces des paquets dont le descripteur porte {@link #NIVEAU_BORNE}. */
    static List<Enonce> enoncesDuNiveauBorne(List<Path> repertoiresDeContenu) {
        return enonces(repertoiresDeContenu, NIVEAU_BORNE);
    }

    /** Les enonces des paquets servis dont le descripteur porte le niveau demande (ADR-0078). */
    static List<Enonce> enonces(List<Path> repertoiresDeContenu, String niveau) {
        List<Enonce> enonces = new ArrayList<>();
        for (Path repertoire : repertoiresDeContenu) {
            List<Path> paquets;
            try (Stream<Path> arbre = Files.walk(repertoire)) {
                paquets = arbre.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                        .sorted().toList();
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
            for (Path paquet : paquets) {
                if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)) {
                    continue;
                }
                if (!(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                        || !niveau.equals(String.valueOf(descripteur.get("niveau")))) {
                    continue;
                }
                if (!(corps.get("items") instanceof List<?> items)) {
                    continue;
                }
                String nom = paquet.getFileName().toString();
                for (int rang = 0; rang < items.size(); rang++) {
                    if (items.get(rang) instanceof Map<?, ?> champs
                            && champs.get("enonce") instanceof String texte) {
                        enonces.add(new Enonce(nom, rang + 1, texte));
                    }
                }
            }
        }
        return enonces;
    }

    /**
     * LON-1. Un paquet est fautif quand le nombre de ses enonces au-dela de {@link #MOTS_MAXIMUM}
     * mots <b>differe</b> de ce que la dette declare pour lui, zero par defaut.
     */
    static List<String> defautsDeLongueur(List<Enonce> enonces, Map<String, Integer> dette) {
        Map<String, List<Enonce>> tropLongs = new LinkedHashMap<>();
        Map<String, Integer> paquets = new LinkedHashMap<>();
        for (Enonce enonce : enonces) {
            paquets.putIfAbsent(enonce.paquet(), 0);
            if (mots(enonce.texte()).size() > MOTS_MAXIMUM) {
                tropLongs.computeIfAbsent(enonce.paquet(), nom -> new ArrayList<>()).add(enonce);
            }
        }
        List<String> defauts = new ArrayList<>();
        for (String paquet : paquets.keySet()) {
            List<Enonce> fautifs = tropLongs.getOrDefault(paquet, List.of());
            int tolere = dette.getOrDefault(paquet, 0);
            if (fautifs.size() == tolere) {
                continue;
            }
            List<String> details = fautifs.stream()
                    .map(enonce -> String.format("item %d (%d mots)", enonce.rang(),
                            mots(enonce.texte()).size()))
                    .toList();
            defauts.add(String.format(
                    "%s : %d enonces depassent %d mots la ou la dette en tolere %d - %s. Au CP, un"
                            + " enonce compte au plus %d mots (LON-1, nadia-prof, 2026-09-08 ;"
                            + " ADR-0089)",
                    paquet, fautifs.size(), MOTS_MAXIMUM, tolere,
                    details.isEmpty() ? "aucun" : String.join(", ", details), MOTS_MAXIMUM));
        }
        return defauts;
    }

    /**
     * LON-2. Un enonce est fautif quand il compte plus de {@link #PHRASES_MAXIMUM} phrases, ou
     * qu'il en compte deux et que la seconde n'est pas une question.
     */
    static List<String> defautsDeForme(List<Enonce> enonces) {
        List<String> defauts = new ArrayList<>();
        for (Enonce enonce : enonces) {
            List<String> phrases = phrases(enonce.texte());
            if (phrases.size() > PHRASES_MAXIMUM) {
                defauts.add(String.format(
                        "%s item %d : %d phrases. Au CP, un enonce compte au plus %d phrases"
                                + " (LON-2, nadia-prof, 2026-09-08 ; ADR-0089) - \"%s\"",
                        enonce.paquet(), enonce.rang(), phrases.size(), PHRASES_MAXIMUM,
                        enonce.texte()));
                continue;
            }
            if (phrases.size() == PHRASES_MAXIMUM && !phrases.get(1).strip().endsWith("?")) {
                defauts.add(String.format(
                        "%s item %d : l'enonce compte deux phrases et la seconde n'est pas la"
                                + " question - \"%s\" (LON-2, nadia-prof, 2026-09-08 ; ADR-0089)",
                        enonce.paquet(), enonce.rang(), phrases.get(1)));
            }
        }
        return defauts;
    }

    static void verifierLesEnonces(List<Enonce> enonces, Map<String, Integer> dette) {
        assertThat(enonces)
                .as("ADR-0051 : sans enonce de %s, ce garde-fou n'examine aucun objet", NIVEAU_BORNE)
                .isNotEmpty();
        List<String> defauts = new ArrayList<>(defautsDeLongueur(enonces, dette));
        defauts.addAll(defautsDeForme(enonces));
        assertThat(defauts)
                .as("ADR-0089 : au %s, un enonce compte au plus %d mots et au plus %d phrases",
                        NIVEAU_BORNE, MOTS_MAXIMUM, PHRASES_MAXIMUM)
                .isEmpty();
    }

    private static List<Enonce> enoncesDuDepot() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        return enoncesDuNiveauBorne(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
    }

    @Test
    void leContenuServiDeCpEstConforme() {
        verifierLesEnonces(enoncesDuDepot(), DETTE_DE_LONGUEUR);
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Mesure du
     * 2026-09-08, arbre fusionne de s32, reacteur complet : <b>36 paquets de CP, 528 enonces, 610
     * phrases, dont 82 enonces a exactement deux phrases</b>. Les bornes sont posees sous cette
     * mesure ; elles constatent qu'un vert porte sur tous les enonces de CP servis et non sur une
     * poignee de paquets qu'un chargement aurait laisse tomber.
     */
    @Test
    void leBalayageExamineTousLesEnoncesDeCp() {
        List<Enonce> enonces = enoncesDuDepot();
        assertThat(enonces)
                .as("ADR-0089 : le contenu servi porte plusieurs centaines d'enonces de CP")
                .hasSizeGreaterThan(500);
        assertThat(enonces.stream().map(Enonce::paquet).distinct().count())
                .as("ADR-0089 : ces enonces viennent de dizaines de paquets de CP")
                .isGreaterThan(30);
        assertThat(enonces.stream().filter(enonce -> phrases(enonce.texte()).size() == 2).count())
                .as("ADR-0089 : la seconde moitie de LON-2 examine des dizaines d'enonces a deux"
                        + " phrases, et non une poignee")
                .isGreaterThan(50);
    }

    /**
     * ADR-0067 : le cliquet de LON-1. La dette ne peut ni grandir - la somme est ecrite et comparee -
     * ni pourrir : chaque paquet nomme doit encore etre un paquet de CP servi, et porter
     * <b>exactement</b> le nombre d'enonces trop longs qu'il declare. Une dette payee rend ce test
     * rouge, ce qui est le seul moyen de forcer sa baisse dans le diff qui la paie.
     *
     * <p>LON-2 n'a pas de moitie ici : elle n'exempte rien, son cliquet est l'egalite a
     * {@link #PLAFOND_DE_DETTE_DE_FORME} portee par {@link #leContenuServiDeCpEstConforme()}, et ce
     * test la constate sur le contenu vivant.
     */
    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_LONGUEUR.values().stream().mapToInt(Integer::intValue).sum())
                .as("ADR-0067 : le plafond vaut exactement la taille de la dette du 2026-09-08")
                .isEqualTo(PLAFOND_DE_DETTE);
        List<Enonce> enonces = enoncesDuDepot();
        for (Map.Entry<String, Integer> entree : DETTE_DE_LONGUEUR.entrySet()) {
            List<Enonce> duPaquet = enonces.stream()
                    .filter(enonce -> enonce.paquet().equals(entree.getKey())).toList();
            assertThat(duPaquet)
                    .as("%s porte une dette de longueur et n'est plus un paquet de CP servi",
                            entree.getKey())
                    .isNotEmpty();
            assertThat(duPaquet.stream()
                    .filter(enonce -> mots(enonce.texte()).size() > MOTS_MAXIMUM).count())
                    .as("%s declare %d enonces trop longs : une dette qui ne nomme plus le nombre"
                            + " reel n'est pas un cliquet", entree.getKey(), entree.getValue())
                    .isEqualTo(entree.getValue().longValue());
        }
        assertThat(defautsDeForme(enonces))
                .as("ADR-0067 : LON-2 est a plafond %d sur le contenu servi, sans exemption",
                        PLAFOND_DE_DETTE_DE_FORME)
                .hasSize(PLAFOND_DE_DETTE_DE_FORME);
    }

    /**
     * ADR-0013 obligation 3, et <b>la morsure qui compte</b>. Le materiau n'est pas de ma main :
     * c'est {@code cp-emc-formules-de-politesse} tel qu'il etait servi a la cloture de s31
     * ({@code 690f0cde}), l'etat sur lequel nadia-prof a ouvert {@code POL-R1} et pose sa barre,
     * recopie octet pour octet pour que la preuve survive au paiement de la reserve. Une fixture
     * ecrite par l'auteur d'une regle ressemble a ce qu'il imagine ; celle-ci ressemble a ce que le
     * depot a reellement servi - la lecon de s05.
     *
     * <p>Sur ce fige, LON-1 nomme <b>neuf</b> enonces et LON-2 en nomme <b>un</b>, l'item 7 en trois
     * phrases. C'est le meme paquet, la meme mesure : nadia-prof en a compte cinq sur la seule
     * tache B, qui sont les cinq plus longs des neuf.
     */
    @Test
    void lEtatQuiPorteLaReserveDeLongueurFaitRougirLesDeuxClauses() {
        List<Enonce> fige = enoncesDuNiveauBorne(
                List.of(CONTRE_EXEMPLE.resolve("etat-reserve-pol-r1")));
        assertThat(fige)
                .as("le jeu fautif est un etat reel de l'historique, lu sur son descripteur de CP")
                .isNotEmpty();
        assertThat(defautsDeLongueur(fige, Map.of()))
                .as("LON-1 doit mordre l'etat sur lequel POL-R1 a ete ouverte")
                .hasSize(1);
        assertThatThrownBy(() -> verifierLesEnonces(fige, Map.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cp-emc-formules-de-politesse.yaml : 9 enonces depassent"
                        + " 15 mots la ou la dette en tolere 0")
                .hasMessageContaining("item 11 (25 mots)")
                .hasMessageContaining("cp-emc-formules-de-politesse.yaml item 7 : 3 phrases");
    }

    /**
     * ADR-0013 obligation 3. La seconde moitie de LON-2 - <i>quand un enonce compte deux phrases, la
     * seconde est la question</i> - <b>n'a aucun materiau reel</b> : les 82 enonces de CP a deux
     * phrases du contenu servi la tiennent tous, et l'historique n'en porte aucun contre-exemple.
     * Ce jeu fautif est donc <b>de ma main, et je le declare comme tel</b> plutot que de laisser
     * cette moitie sans preuve qu'elle mord.
     *
     * <p>Les deux enonces ci-dessous sont ecrits sur le patron reel du contenu de CP - une situation
     * puis une question -, l'un avec les deux phrases inversees, l'autre avec une seconde phrase qui
     * n'interroge pas.
     */
    @Test
    void unEnonceDontLaSecondePhraseNInterrogePasEstRouge() {
        List<Enonce> fautifs = List.of(
                new Enonce("paquet-fautif.yaml", 1,
                        "Que doit-elle dire ? Lea arrive a l'ecole et croise sa maitresse."),
                new Enonce("paquet-fautif.yaml", 2,
                        "Tom recoit l'aide d'un camarade. Il doit dire merci."));

        assertThat(defautsDeForme(fautifs))
                .as("LON-2, seconde moitie : deux phrases dont la seconde n'interroge pas")
                .hasSize(2);
        assertThat(defautsDeLongueur(fautifs, Map.of()))
                .as("ces deux enonces tiennent LON-1 : la morsure porte sur la forme, pas sur la"
                        + " longueur")
                .isEmpty();
    }

    /**
     * <b>La derivation qui remplace un reglage, sur du contenu servi reel.</b> Les onze enonces de
     * {@code cp-complements-10} et les onze de {@code cp-complements-10-saisie} portent un
     * {@code ?} qui est le trou a completer. Sous le critere litteral de nadia-prof, chacun compte
     * deux phrases dont la seconde n'est pas une question, et <b>les 22 sont rouges</b> ; sous le
     * critere derive, chacun compte une phrase. Ce test compare les deux sur le materiau reel plutot
     * que d'affirmer la difference dans un commentaire.
     */
    @Test
    void unEnonceArithmetiqueNeCompteQuUnePhrase() {
        List<Enonce> arithmetiques = enoncesDuDepot().stream()
                .filter(enonce -> enonce.paquet().startsWith("cp-complements-10")).toList();

        assertThat(arithmetiques)
                .as("les deux paquets de complements a 10 sont servis, et leur enonce porte le trou")
                .hasSize(22);
        assertThat(arithmetiques.stream().filter(enonce -> enonce.texte().contains("?")).count())
                .as("le ? de ces enonces est un trou a completer, pas une ponctuation")
                .isEqualTo(22);
        assertThat(defautsDeForme(arithmetiques))
                .as("le critere derive n'en rend aucun fautif")
                .isEmpty();
        assertThat(arithmetiques.stream()
                .filter(enonce -> enonce.texte().split("(?<=[.?!])").length > 1).count())
                .as("le critere litteral, lui, en couperait 22 en deux segments")
                .isEqualTo(22);
    }

    /**
     * <b>Le perimetre est prouve par ses bords, sur le materiau qui le prouve.</b> Ce test charge la
     * population de CM1 avec le meme code, sous le meme critere, et constate ce que la barre
     * couterait si elle debordait : mesure du 2026-09-08, <b>165 enonces de CM1 au-dela de 15 mots</b>
     * (maximum 36) et <b>35 au-dela de deux phrases</b>. nadia-prof n'a compte aucune de ces deux
     * populations et n'y pose aucune barre ; {@code HG-R4} reste qualitative et sous sa garde.
     *
     * <p>Les bornes ci-dessous sont larges a dessein : le contenu de CM1 grossit a chaque sprint, et
     * ce test doit constater qu'une population debordee existe, pas epingler sa taille du jour.
     */
    @Test
    void lesEnoncesDeCm1SontUnePopulationReelleQueLaBarreNeVoitPas() {
        List<Path> repertoires = AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                .repertoiresDeContenu(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        List<Enonce> cm1 = enonces(repertoires, "CM1");

        assertThat(cm1.stream().filter(enonce -> mots(enonce.texte()).size() > MOTS_MAXIMUM).count())
                .as("le CM1 porte des dizaines d'enonces au-dela de %d mots, qu'aucune barre ne"
                        + " borne", MOTS_MAXIMUM)
                .isGreaterThan(100);
        assertThat(cm1.stream()
                .filter(enonce -> phrases(enonce.texte()).size() > PHRASES_MAXIMUM).count())
                .as("le CM1 porte des enonces au-dela de %d phrases, qu'aucune barre ne borne",
                        PHRASES_MAXIMUM)
                .isGreaterThan(20);
        assertThat(enoncesDuDepot()).as("ADR-0078 : le niveau est lu dans le descripteur du paquet,"
                + " et aucun enonce de CM1 n'entre dans la population bornee")
                .doesNotContainAnyElementsOf(cm1);
    }
}
