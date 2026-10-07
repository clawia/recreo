package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0067, cliquet de la dette de {@code TYP-1}.
 *
 * <p><b>La condition, dans les mots de nadia-prof</b> (`ARBITRAGE-typographie-espace-avant-signe-double.md`,
 * 2026-08-28) : <i>"un signe de ponctuation double (?, !, ;, :) ne doit jamais pouvoir se retrouver
 * seul en debut ou en fin de ligne, separe du mot qu'il accompagne"</i>. Elle ecrit la propriete et
 * laisse le <b>moyen</b> a theo-archi et sofia-sm.
 *
 * <p><b>Ce que ce garde-fou juge, et ce qu'il ne juge pas.</b> Il ne juge pas la propriete de
 * {@code TYP-1}, qui porte sur une <b>ligne rendue</b> a un viewport donne, donc sur le navigateur.
 * Il juge sa <b>condition suffisante</b> dans la donnee : une espace ordinaire immediatement avant un
 * signe double est le seul endroit ou le moteur de rendu a le droit de couper. Aucune espace
 * ordinaire a cet endroit implique aucun orphelin, a tout viewport, sans qu'aucun navigateur ne soit
 * ouvert. L'implication ne vaut que dans ce sens : un texte conforme ici peut encore mal se replier
 * pour une autre raison, et ce garde-fou ne le dira pas.
 *
 * <p><b>Il ne choisit pas le moyen non plus, et c'est delibere</b> : l'espace insecable ordinaire,
 * l'insecable etroite ou le retrait pur et simple de l'espace le rendent vert de la meme facon. Le
 * choix entre les trois reste ouvert (`ADR-0067`, section "Ce qui reste a decider a deux").
 *
 * <p><b>Pourquoi un cliquet et pas une exigence.</b> La dette est de 540 occurrences sur 17 fichiers
 * (mesure ci-dessous), sur des paquets deja juges par nadia-prof, et sa correction appartient aux
 * auteurs de paquets et a camille-po pour l'ordre. Exiger zero aujourd'hui rendrait la porte
 * technique rouge sur un travail que personne n'a commande. Le cliquet fait la seule chose qui soit
 * a la fois utile et gratuite : il <b>arrete la croissance</b> - quatre orphelins neufs sont apparus
 * a s22 - et il rend chaque baisse visible dans un diff.
 *
 * <p><b>En egalite, jamais en plancher</b> (lecon d'ADR-0055 et d'ADR-0063) : un fichier qui descend
 * sous son compte declare est rouge lui aussi, avec le nombre a inscrire dans le message. Un paquet
 * absent de la table doit valoir zero - un paquet neuf n'a aucune dette a heriter.
 */
class AucunSigneDoubleNEstDetachableDeSonMotTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunSigneDoubleNEstDetachableDeSonMotTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunSigneDoubleNEstDetachableDeSonMotTest";

    private static final String SIGNES_DOUBLES = "?!;:";

    private static final String CATALOGUE = "catalogue.yaml";

    /**
     * Etat mesure le 2026-08-30 sur le texte servi par le chargeur reel, chaque paquet enumere lu en
     * entier (ADR-0064). Ce nombre ne remonte jamais ; le baisser demande un diff visible.
     *
     * <p>Methode, parce qu'un chiffre sans methode ne vaut rien : le compte porte sur le <b>texte
     * servi</b> (enonce, bonne reponse, distracteurs, indice, explication, texte support, part
     * designee) et sur les seuls titre et consigne du catalogue - jamais sur les octets du fichier.
     * C'est pourquoi il differe des 511 occurrences sur 17 fichiers que nadia-prof a relevees le
     * 2026-08-28 : son releve lisait les fichiers, commentaires compris, le mien lit ce que l'enfant
     * recoit. Les deux sont exacts, ils ne comptent pas la meme chose.
     *
     * <p><b>Remesure le 2026-08-30</b> (s23, lea-dev, reserve B de {@code FICHE-bandit-manchot-
     * problemes-durees-cm1.md}) : {@code cm1-problemes-durees} baisse de 27 a 3, seule baisse de ce
     * sprint. Le total ({@link #DETTE_TOTALE}) baisse d'autant, de 540 a 516.
     */
    private static final Map<String, Integer> DETTE_PAR_FICHIER = new TreeMap<>(Map.ofEntries(
            Map.entry("cm1-tables-multiplication", 179),
            Map.entry("cp-problemes-additifs", 44),
            Map.entry("cm1-conjugaison-present", 31),
            Map.entry("cm1-homophones", 27),
            Map.entry("cp-soustractions", 27),
            Map.entry("cm1-grands-nombres", 12),
            Map.entry("cm1-accords-groupe-nominal", 25),
            Map.entry("cm1-mesures", 24),
            Map.entry("cp-comparer-nombres", 24),
            Map.entry("cm1-accord-sujet-verbe", 21),
            Map.entry("cm1-perimetre", 21),
            Map.entry("cp-comparer", 18),
            Map.entry("cp-complements-10", 14),
            Map.entry("cp-le-bon-son", 6),
            // cm1-problemes-durees baisse de 27 a 3 le 2026-08-30 (reserve B, s23, lea-dev) : les
            // 24 enonces posent desormais l'espace insecable devant leur "?" final ; seules les
            // 3 occurrences des indices (hors perimetre de la reserve) restent.
            Map.entry("cm1-problemes-durees", 3),
            Map.entry("cm1-antonymes", 3),
            Map.entry(CATALOGUE, 23)));

    /** Le total, ecrit une fois pour que la somme ne puisse pas deriver de la table sans le dire. */
    private static final int DETTE_TOTALE = 502;

    static int occurrencesDe(String valeur) {
        int compte = 0;
        for (int rang = 1; rang < valeur.length(); rang++) {
            if (SIGNES_DOUBLES.indexOf(valeur.charAt(rang)) >= 0
                    && valeur.charAt(rang - 1) == ' ') {
                compte++;
            }
        }
        return compte;
    }

    /**
     * <b>Lot 5a de s24, decision 5 : borne aux chaines que l'enfant lit</b>, meme partage et meme
     * motif qu'{@link AucunMotServiNeSEcarteDeSonAccentTest#textesDesItems}. Un orphelin est un
     * accident de <b>repli a l'ecran</b> : une chaine que rien n'affiche ne se replie nulle part, et
     * une forme acceptee est comparee, jamais rendue. Un paquet neuf n'herite d'aucune dette, y
     * compris sur ces chaines-la.
     */
    static Map<String, Integer> detteMesuree(Path racine, String racineClasspath,
                                             boolean avecCatalogue) {
        Map<String, Integer> parFichier = new TreeMap<>();
        int lues = 0;
        for (LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.TexteServi texte
                : LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.textesServis(
                        racine, racineClasspath)) {
            if (!texte.luParLEnfant()) {
                continue;
            }
            lues++;
            parFichier.merge(texte.paquet(), occurrencesDe(texte.valeur()), Integer::sum);
        }
        assertThat(lues)
                .as("%s : aucune chaine lue par l'enfant sous cette racine, ce cliquet ne mesure"
                        + " alors rien", racine)
                .isPositive();
        if (avecCatalogue) {
            int catalogue = 0;
            for (GameDefinition jeu : new YamlContentPackLoader().games()) {
                catalogue += occurrencesDe(jeu.titre()) + occurrencesDe(jeu.consigne());
            }
            parFichier.put(CATALOGUE, catalogue);
        }
        Map<String, Integer> nonNuls = new TreeMap<>();
        parFichier.forEach((fichier, compte) -> {
            if (compte > 0) {
                nonNuls.put(fichier, compte);
            }
        });
        return nonNuls;
    }

    static List<String> ecartsAuCliquet(Map<String, Integer> mesuree, Map<String, Integer> declaree) {
        List<String> ecarts = new ArrayList<>();
        Map<String, Integer> restants = new LinkedHashMap<>(declaree);
        mesuree.forEach((fichier, compte) -> {
            Integer declare = restants.remove(fichier);
            if (declare == null) {
                ecarts.add(String.format(
                        "%s porte %d espace(s) ordinaire(s) devant un signe double et n'est pas dans"
                                + " la table de dette. Un contenu neuf n'herite d'aucune dette :"
                                + " ecrire l'espace insecable, ou retirer l'espace (TYP-1, ADR-0067)",
                        fichier, compte));
            } else if (compte > declare) {
                ecarts.add(String.format(
                        "%s porte %d occurrences, la table en declare %d. Le cliquet ne remonte pas"
                                + " (TYP-1, ADR-0067)", fichier, compte, declare));
            } else if (compte < declare) {
                ecarts.add(String.format(
                        "%s porte %d occurrences, la table en declare encore %d. La dette a baisse :"
                                + " inscrire %d dans DETTE_PAR_FICHIER, dans le meme commit"
                                + " (TYP-1, ADR-0067)", fichier, compte, declare, compte));
            }
        });
        restants.forEach((fichier, declare) -> ecarts.add(String.format(
                "la table declare %d occurrences pour %s, qui n'en porte plus aucune ou n'existe"
                        + " plus. Retirer cette ligne (TYP-1, ADR-0067)", declare, fichier)));
        return ecarts;
    }

    @Test
    void leCliquetDeLaDetteDeTyp1EstExact() {
        Map<String, Integer> mesuree = detteMesuree(CONTENU_DE_PRODUCTION, "", true);

        assertThat(mesuree)
                .as("ADR-0051 : ce cliquet ne prouve rien s'il ne mesure aucun fichier")
                .isNotEmpty();
        assertThat(ecartsAuCliquet(mesuree, DETTE_PAR_FICHIER))
                .as("ADR-0067 : la dette de TYP-1 est declaree fichier par fichier, en egalite")
                .isEmpty();
        assertThat(mesuree.values().stream().mapToInt(Integer::intValue).sum())
                .as("ADR-0067 : le total declare ne suit plus la table")
                .isEqualTo(DETTE_TOTALE);
    }

    /**
     * ADR-0013 obligation 3, sur du materiau reel, et la mutation porte sur la <b>declaration</b>
     * plutot que sur le contenu : c'est la declaration qui change le jour ou un paquet neuf arrive.
     * Le materiau est le paquet reel qui porte le plus d'occurrences ; l'oter de la table le rend
     * indiscernable d'un paquet neuf fautif.
     *
     * <p><b>Note de mesure, qui vaut d'etre gardee</b> : le paquet historique de {@code be63ce3^},
     * qui sert de contre-exemple a ADR-0065, porte deja l'espace insecable devant ses 25 points
     * d'interrogation. Il ne pouvait donc pas servir ici - un contre-exemple pour une regle n'est pas
     * un contre-exemple pour une autre, et le constater coute moins cher que de le supposer.
     */
    @Test
    void unPaquetNeufHorsDeLaTableFaitEchouerLeCliquet() {
        Map<String, Integer> mesuree = detteMesuree(
                CONTRE_EXEMPLE.resolve("paquet-neuf-fautif"),
                CONTRE_EXEMPLE_CLASSPATH + "/paquet-neuf-fautif", false);

        assertThat(mesuree)
                .as("le paquet fautif doit porter des occurrences reelles, sans quoi cette mise a"
                        + " l'epreuve ne mute rien")
                .isNotEmpty();
        assertThatThrownBy(() -> assertThat(ecartsAuCliquet(mesuree, DETTE_PAR_FICHIER)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans la table de dette");
    }

    /**
     * La meme propriete, prouvee une seconde fois sur du contenu <b>reel</b> plutot que sur le jeu
     * fautif : le paquet de production qui porte le plus d'occurrences, retire de la table, devient
     * indiscernable d'un paquet neuf fautif. Deux materiaux valent mieux qu'un quand le jeu fautif
     * est de ma main (lecon de s05).
     */
    @Test
    void unPaquetReelRetireDeLaTableFaitEchouerLeCliquet() {
        Map<String, Integer> mesuree = detteMesuree(CONTENU_DE_PRODUCTION, "", true);
        String lePlusCharge = mesuree.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
        Map<String, Integer> tableSansLui = new TreeMap<>(DETTE_PAR_FICHIER);
        tableSansLui.remove(lePlusCharge);

        assertThat(ecartsAuCliquet(mesuree, tableSansLui))
                .as("ADR-0067 : un contenu qui porte des occurrences sans figurer dans la table est"
                        + " rouge, et c'est ce qui interdit a un paquet neuf d'en apporter")
                .anyMatch(ecart -> ecart.contains("n'est pas dans la table de dette"));
    }

    /** La morsure dans le sens de la croissance : un fichier qui monte est rouge. */
    @Test
    void uneDetteQuiMonteFaitEchouerLeCliquet() {
        Map<String, Integer> mesuree = detteMesuree(CONTENU_DE_PRODUCTION, "", true);
        Map<String, Integer> tableTropBasse = new TreeMap<>(DETTE_PAR_FICHIER);
        tableTropBasse.computeIfPresent("cp-le-bon-son", (fichier, compte) -> compte - 1);

        assertThat(ecartsAuCliquet(mesuree, tableTropBasse))
                .as("ADR-0067 : le cliquet ne remonte jamais")
                .anyMatch(ecart -> ecart.contains("ne remonte pas"));
    }

    /** La morsure dans l'autre sens : une dette qui baisse sans que la table suive est rouge aussi. */
    @Test
    void uneDetteQuiBaisseSansQueLaTableSuiveFaitEchouerLeCliquet() {
        Map<String, Integer> mesuree = detteMesuree(CONTENU_DE_PRODUCTION, "", true);
        Map<String, Integer> declareeTropHaute = new TreeMap<>(DETTE_PAR_FICHIER);
        declareeTropHaute.computeIfPresent("cp-le-bon-son", (fichier, compte) -> compte + 1);

        assertThat(ecartsAuCliquet(mesuree, declareeTropHaute))
                .as("ADR-0067 : le cliquet est en egalite, pas en plancher")
                .anyMatch(ecart -> ecart.contains("La dette a baisse"));
    }
}
