package com.recreo.bootstrap.rendu;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0029 : <b>toute colonne que le contenu servi peut produire porte un decalage declare.</b>
 *
 * <p>Le decalage inter-colonnes du rouleau (S7-1) est cumulatif : la feuille maison declare
 * {@code animation-delay} colonne par colonne, et la valeur croit avec le rang. La borne de
 * {@code nadia-prof} porte sur le retard de la derniere colonne ; elle est deja gardee, une seule
 * fois et a un seul endroit, par la clause 6 de {@code LAnimationRetardeMaisNeDetermineRienTest}
 * dans {@code adapter-web}. Ce garde-fou-ci ne la reverifie pas et ne recopie aucun seuil : il ferme
 * l'autre moitie du trou, celle qu'aucune population ne collectait - <b>le nombre de colonnes que le
 * contenu peut produire</b>. Les deux forment une pince :</p>
 *
 * <ul>
 *   <li>une colonne de plus sans regle de decalage : ce test rougit, parce que la colonne neuve
 *       demarrerait avec la premiere et que le mouvement livre cesserait d'exister pour elle sans
 *       qu'aucun diff ne le montre ;</li>
 *   <li>une colonne de plus avec sa regle, obtenue en prolongeant la progression declaree : la
 *       clause 6 d'{@code adapter-web} rougit sur la borne de {@code nadia-prof}, comme le prouve
 *       deja son jeu fautif {@code retard-ecrit-a-part}, qui est exactement cette forme - un retard
 *       ecrit dans une regle par colonne, hors du raccourci {@code animation}.</li>
 * </ul>
 *
 * <p>Le seul chemin vert d'une colonne de plus est donc de <b>revoir</b> le decalage pour que la
 * derniere colonne reste dans la borne, et non de prolonger la progression. Ce choix est de forme
 * visuelle : il appartient a {@code ines-design} sous l'arbitrage de {@code nadia-prof}, et ce test
 * ne le fait pas a leur place - il refuse seulement qu'il soit escamote.</p>
 *
 * <p><b>Pourquoi ce test vit dans bootstrap.</b> Il a besoin des deux sources a la fois : les paquets
 * de contenu ({@code adapter-content}) et les feuilles maison ({@code adapter-web}). Aucun des deux
 * modules ne voit l'autre (Clean Architecture, les adapters ne dependent pas les uns des autres),
 * seul {@code bootstrap} voit les deux - meme motif que
 * {@code LExplicationDUnItemReelAtteintLEcranDeRetourTest}.</p>
 *
 * <p><b>Pourquoi les sources sont lues dans l'arborescence du depot et non sur le classpath, et c'est
 * une correction mesuree, pas une preference.</b> La premiere ecriture de ce garde-fou balayait le
 * classpath, ce qui semblait plus juste - lire ce que l'application embarque. Mesure : sur un
 * {@code mvn clean verify} complet, la population de feuilles etait <b>vide de tout
 * {@code animation-delay}</b>, parce que la resolution ramenait un artefact <b>perime</b> du depot
 * local (12 711 octets sans aucun retard declare, la ou la feuille du depot en fait 18 407 avec
 * trois). Un garde-fou dont la population depend de ce qui se trouve installe sur le poste n'est pas
 * un garde-fou : il aurait pu etre vert par accident tout autant que rouge. Le perimetre vient donc
 * du <b>pom agregateur</b>, seul endroit du depot qui dise quels modules existent, exactement comme
 * {@code LesGardeFousSontMisALEpreuveTest} le fait deja pour balayer les modules voisins. C'est la
 * clause de non-vacuite d'ADR-0003 qui a rendu ce defaut visible, et elle est la raison pour laquelle
 * il n'est pas passe.</p>
 *
 * <p><b>Cout assume.</b> Le decoupage de blocs CSS est ecrit une seconde fois ici : celui
 * d'{@code adapter-web} vit dans les sources de test de son module, que ce module-ci ne voit pas, et
 * publier un artefact de test pour trente lignes couterait plus que de les ecrire. Le jour ou un
 * troisieme module en aurait besoin, ce calcul change.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les modules viennent du pom agregateur, les paquets du balayage de
 *       {@code contenu/} sous les ressources de production de ces modules, les feuilles du balayage
 *       de {@code static/} hors {@code static/vendor/} (ADR-0014), les rangs de colonne des
 *       selecteurs eux-memes. Aucun nom de module, de paquet, de feuille ni de rang n'est ecrit ici
 *       comme objet a controler. Le perimetre est volontairement <b>plus large que le catalogue</b> :
 *       l'appartenance d'un jeu au catalogue est une decision produit qui a change quatre fois dans
 *       ce depot (DEC-0009, DEC-0014, DEC-0015), et un paquet hors catalogue y revient par deux
 *       lignes de YAML. {@code ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest} choisit l'inverse,
 *       et pour une raison ecrite : {@code nombreDeManches} n'existe que dans l'entree de catalogue,
 *       alors qu'un nombre de propositions existe des que le paquet existe ;</li>
 *   <li><b>derivation totale</b> : un paquet dont on ne sait pas lire le nombre de propositions est
 *       un defaut, pas une valeur ignoree ; un rang de colonne qui n'est pas un entier litteral ne
 *       compte pas comme couvert ; un retard declare sous {@code @keyframes} ou sous
 *       {@code prefers-reduced-motion} ne couvre rien, parce qu'aucune animation n'y tourne. Le doute
 *       joue dans le sens du controle ;</li>
 *   <li><b>mise a l'epreuve</b> : trois jeux fautifs neufs, dont un corrige qui doit redevenir vert
 *       - sans lui, la clause serait une interdiction de la cinquieme colonne deguisee en clause de
 *       couverture.</li>
 * </ul>
 */
class ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest {

    private static final Path CONTRE_EXEMPLES = Path.of("src", "test", "resources", "contreexemple",
            "ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest");

    /** Ou vivent les ressources qu'un module sert. */
    private static final Path RESSOURCES_DE_PRODUCTION = Path.of("src", "main", "resources");

    private static final String REPERTOIRE_DE_CONTENU = "contenu";
    private static final String REPERTOIRE_SERVI = "static";

    /** La propriete par laquelle une feuille retarde le depart d'une colonne. */
    private static final String PROPRIETE_DE_RETARD = "animation-delay";

    /** Le pseudo-selecteur par lequel une regle designe le rang d'une colonne. */
    private static final String RANG = ":nth-child(";

    private static final String IMAGES_CLES = "@keyframes";
    private static final String CONTEXTE_DE_MOUVEMENT_REDUIT = "prefers-reduced-motion";

    /** Un habillage tiers n'est pas une feuille maison (ADR-0014). */
    private static final String VENDOR = "vendor";

    /** Une regle CSS, avec la pile de regles-@ dans laquelle elle est ecrite. */
    private record Regle(String contexte, String selecteur, String corps) {

        boolean dansDesImagesCles() {
            return contexte.contains(IMAGES_CLES);
        }

        boolean sousMouvementReduit() {
            return contexte.contains(CONTEXTE_DE_MOUVEMENT_REDUIT);
        }
    }

    /** Le nombre de colonnes qu'un paquet peut produire, et le paquet qui le porte. */
    private record Plafond(String paquet, int colonnes) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Path> fichiers(Path racine, String extension) {
        if (!Files.isDirectory(racine)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(extension))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le pom agregateur, seul endroit du depot qui dise quels modules existent vraiment. */
    private static Path racineDuDepot() {
        Path candidat = Path.of("").toAbsolutePath();
        while (candidat != null) {
            Path pom = candidat.resolve("pom.xml");
            if (Files.isRegularFile(pom) && lire(pom).contains("<modules>")) {
                return candidat;
            }
            candidat = candidat.getParent();
        }
        throw new IllegalStateException("aucun pom.xml agregateur au-dessus du repertoire courant");
    }

    /** Les racines de ressources servies, une par module declare qui en possede. */
    static List<Path> racinesDeProduction() {
        Path racine = racineDuDepot();
        String pom = lire(racine.resolve("pom.xml"));
        List<Path> racines = new ArrayList<>();
        int position = 0;
        while ((position = pom.indexOf("<module>", position)) >= 0) {
            int fin = pom.indexOf("</module>", position);
            Path ressources = racine.resolve(pom.substring(position + "<module>".length(), fin).strip())
                    .resolve(RESSOURCES_DE_PRODUCTION);
            if (Files.isDirectory(ressources)) {
                racines.add(ressources);
            }
            position = fin;
        }
        return racines;
    }

    /**
     * <b>ADR-0088, clause C : un type de paquet que ce garde-fou n'a pas instruit est un grief,
     * jamais un silence.</b>
     *
     * <p><b>Ce que ce gate corrigeait, et ce qu'il decide desormais (S33-PAL2-1, s34).</b> La
     * Javadoc de {@link #colonnes(Map)} annoncait qu'<i>"une troisieme forme, le jour ou elle
     * arrivera, fera rougir ce garde-fou au lieu de passer en silence"</i>. Mesure du 2026-09-08 :
     * la promesse ne tenait que pour un paquet dont la <b>forme</b> est illisible, pas pour un
     * paquet d'un <b>type</b> neuf dont la forme se lit par accident - un paquet de type
     * {@code designation} declare des {@code items} et aucun {@code distracteurs}, ce qui rendait
     * silencieusement {@code Optional.of(1)}. Le premier paquet reel servi
     * ({@code cp-frise-de-la-journee}, S33-PAL2-1) a rendu ce rouge date reel : la decision, prise
     * ici, est que ce type produit
     * <b>zero</b> colonne - une designation ne rend jamais de rouleau de propositions (ADR-0029),
     * elle pose des boutons de zone sur une image. {@link #colonnes(Map)} le decide desormais
     * explicitement plutot que de le lire par accident sur l'absence de {@code distracteurs}.</p>
     *
     * <p>Le grief reste ajoute pour tout type non reconnu, et le balayage <b>continue</b> : un
     * paquet peut porter les deux defauts a la fois - un type non instruit et une forme illisible -
     * et n'en montrer qu'un masquerait l'autre.</p>
     */
    private static Optional<String> defautDuTypeNonInstruit(String nom, Map<?, ?> paquet) {
        String type = String.valueOf(paquet.get("type"));
        return switch (type) {
            case "question", "additions-bornees", "saisie", "designation" -> Optional.empty();
            default -> Optional.of(nom + " : type '" + type + "', que ce garde-fou n'a pas instruit."
                    + " Son nombre de colonnes est alors lu par la forme generique, qui peut le"
                    + " rendre par accident. Decider ici combien de colonnes ce type peut produire"
                    + " (ADR-0029, ADR-0088 clause C)");
        };
    }

    /**
     * Le nombre de propositions qu'un item de ce paquet peut afficher, au plus.
     *
     * <p>Deux formes, parce que le depot en sert deux (ADR-0007) : un paquet enumere ses items et le
     * nombre de colonnes vaut la bonne reponse plus ses distracteurs ({@code
     * BanditManchotSession.melanger}) ; un paquet combinatoire declare {@code nombreDePropositions}
     * dans ses parametres, et le generateur en produit au plus autant. Dans les deux cas c'est une
     * borne superieure, ce qui est le bon sens de l'approximation.</p>
     *
     * <p><b>Amendement du 2026-09-08 (ADR-0088, clause C), decide au 2026-09-10 (S33-PAL2-1, s34).</b>
     * Cette Javadoc disait <i>"une troisieme forme, le jour ou elle arrivera, fera rougir ce
     * garde-fou au lieu de passer en silence"</i>. C'est vrai d'une forme <b>illisible</b> et faux
     * d'un <b>type</b> neuf dont la forme se lit par accident : un paquet de designation declarait
     * des {@code items} sans {@code distracteurs} et valait 1 par accident, sans grief. Decide
     * desormais explicitement a zero, pour le motif documente par
     * {@link #defautDuTypeNonInstruit(String, Map)}.</p>
     */
    private static Optional<Integer> colonnes(Map<?, ?> paquet) {
        if ("designation".equals(String.valueOf(paquet.get("type")))) {
            return Optional.of(0);
        }
        Object items = paquet.get("items");
        if (items instanceof List<?> liste && !liste.isEmpty()) {
            int maximum = 0;
            for (Object item : liste) {
                if (!(item instanceof Map<?, ?> champs)) {
                    return Optional.empty();
                }
                int distracteurs = champs.get("distracteurs") instanceof List<?> lus ? lus.size() : 0;
                maximum = Math.max(maximum, 1 + distracteurs);
            }
            return Optional.of(maximum);
        }
        if (paquet.get("parametres") instanceof Map<?, ?> parametres
                && parametres.get("nombreDePropositions") instanceof Integer declare) {
            return Optional.of(declare);
        }
        return Optional.empty();
    }

    /**
     * Le plus grand nombre de colonnes que le contenu du depot peut produire, avec le paquet qui
     * l'atteint pour que le message nomme une cause et pas seulement un chiffre.
     */
    static Plafond plafondDeColonnes(List<Path> racines, List<String> defauts) {
        Plafond plafond = new Plafond("(aucun paquet)", 0);
        for (Path racine : racines) {
            for (Path paquet : fichiers(racine.resolve(REPERTOIRE_DE_CONTENU), ".yaml")) {
                String nom = paquet.getFileName().toString();
                if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> champs)) {
                    defauts.add(nom + " : ce paquet n'est pas une table YAML, son nombre de "
                            + "propositions n'est pas lisible ; une valeur qu'on ne lit pas ne se "
                            + "confronte a rien (ADR-0013 obligation 2)");
                    continue;
                }
                defautDuTypeNonInstruit(nom, champs).ifPresent(defauts::add);
                Optional<Integer> lues = colonnes(champs);
                if (lues.isEmpty()) {
                    defauts.add(nom + " : ce paquet ne declare ni items lisibles ni "
                            + "parametres.nombreDePropositions. Le nombre de colonnes qu'il peut "
                            + "afficher est donc inconnu, et un decalage inter-colonnes ne peut pas "
                            + "etre confronte a un nombre de colonnes inconnu (ADR-0029)");
                    continue;
                }
                if (lues.orElseThrow() > plafond.colonnes()) {
                    plafond = new Plafond(nom, lues.orElseThrow());
                }
            }
        }
        return plafond;
    }

    /**
     * Les regles de toutes les feuilles maison, avec leur contexte de regles-@. Lecture caractere par
     * caractere avec un compteur de profondeur d'accolades, comme dans {@code adapter-web} : une
     * regle-@ imbriquee n'est pas une chose qu'une expression reguliere lit correctement.
     */
    private static List<Regle> reglesMaison(List<Path> racines) {
        List<Regle> regles = new ArrayList<>();
        for (Path racine : racines) {
            for (Path feuille : fichiers(racine.resolve(REPERTOIRE_SERVI), ".css")) {
                if (feuille.getParent().getFileName().toString().equals(VENDOR)) {
                    continue;
                }
                String source = sansCommentaires(lire(feuille));
                List<String> pile = new ArrayList<>();
                StringBuilder prelude = new StringBuilder();
                int position = 0;
                while (position < source.length()) {
                    char caractere = source.charAt(position);
                    if (caractere == '}') {
                        if (!pile.isEmpty()) {
                            pile.remove(pile.size() - 1);
                        }
                        prelude.setLength(0);
                        position++;
                    } else if (caractere != '{') {
                        prelude.append(caractere);
                        position++;
                    } else {
                        String entete = prelude.toString().strip();
                        prelude.setLength(0);
                        position++;
                        if (entete.startsWith("@")) {
                            pile.add(entete);
                            continue;
                        }
                        int fin = source.indexOf('}', position);
                        String corps = source.substring(position, fin < 0 ? source.length() : fin);
                        position = fin < 0 ? source.length() : fin + 1;
                        String contexte = String.join(" ", pile);
                        for (String selecteur : entete.split(",")) {
                            regles.add(new Regle(contexte, selecteur.strip(), corps));
                        }
                    }
                }
            }
        }
        return regles;
    }

    private static String sansCommentaires(String source) {
        StringBuilder net = new StringBuilder(source.length());
        int position = 0;
        while (position < source.length()) {
            if (source.startsWith("/*", position)) {
                int fin = source.indexOf("*/", position + 2);
                position = fin < 0 ? source.length() : fin + 2;
            } else {
                net.append(source.charAt(position));
                position++;
            }
        }
        return net.toString();
    }

    /** La valeur declaree pour une propriete, telle qu'elle est ecrite, ou vide. */
    private static Optional<String> valeurDeclaree(String corps, String propriete) {
        String texte = corps.toLowerCase(Locale.ROOT);
        int position = texte.indexOf(propriete);
        if (position < 0) {
            return Optional.empty();
        }
        int deuxPoints = texte.indexOf(':', position + propriete.length());
        if (deuxPoints < 0) {
            return Optional.empty();
        }
        int fin = texte.indexOf(';', deuxPoints);
        return Optional.of(corps.substring(deuxPoints + 1, fin < 0 ? corps.length() : fin).strip());
    }

    /**
     * Le rang de colonne qu'un selecteur designe, ou vide. Un argument qui n'est pas un entier
     * litteral - {@code 2n+1}, {@code odd} - ne nomme pas une colonne et ne compte donc pas comme
     * couvert : le doute joue dans le sens du controle.
     */
    private static Optional<Integer> rangDeColonne(String selecteur) {
        int position = selecteur.indexOf(RANG);
        if (position < 0) {
            return Optional.empty();
        }
        int debut = position + RANG.length();
        int fin = selecteur.indexOf(')', debut);
        if (fin < 0) {
            return Optional.empty();
        }
        String argument = selecteur.substring(debut, fin).strip();
        for (int index = 0; index < argument.length(); index++) {
            if (!Character.isDigit(argument.charAt(index))) {
                return Optional.empty();
            }
        }
        return argument.isEmpty() ? Optional.empty() : Optional.of(Integer.parseInt(argument));
    }

    /** Les retards declares par rang de colonne, tels qu'ils sont ecrits dans la feuille. */
    static Map<Integer, String> retardsParColonne(List<Path> racines) {
        Map<Integer, String> retards = new TreeMap<>();
        for (Regle regle : reglesMaison(racines)) {
            if (regle.dansDesImagesCles() || regle.sousMouvementReduit()) {
                continue;
            }
            Optional<String> retard = valeurDeclaree(regle.corps(), PROPRIETE_DE_RETARD);
            Optional<Integer> rang = rangDeColonne(regle.selecteur());
            if (retard.isPresent() && rang.isPresent()) {
                retards.put(rang.orElseThrow(), retard.orElseThrow());
            }
        }
        return retards;
    }

    static void verifierQueChaqueColonnePorteSonDecalage(List<Path> racines) {
        List<String> defauts = new ArrayList<>();
        Plafond plafond = plafondDeColonnes(racines, defauts);
        Map<Integer, String> retards = retardsParColonne(racines);

        assertThat(plafond.colonnes())
                .as("ADR-0003 : ce garde-fou ne prouve rien s'il ne trouve, sous %s/, aucun paquet "
                        + "capable d'afficher au moins deux colonnes", REPERTOIRE_DE_CONTENU)
                .isGreaterThanOrEqualTo(2);
        assertThat(retards)
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucune feuille maison de %s/ ne "
                        + "declare de %s sur un rang de colonne. Le jour ou le rouleau de S7-1 "
                        + "disparait, cette clause perd son objet et c'est ce message qui le dit ; "
                        + "c'est aussi elle qui a rendu visible une premiere version de ce garde-fou "
                        + "qui lisait un artefact perime au lieu du depot",
                        REPERTOIRE_SERVI, PROPRIETE_DE_RETARD)
                .isNotEmpty();

        int dernierCouvert = retards.keySet().stream().mapToInt(Integer::intValue).max().orElse(1);
        for (int colonne = 2; colonne <= plafond.colonnes(); colonne++) {
            if (!retards.containsKey(colonne)) {
                defauts.add("colonne " + colonne + " : aucun " + PROPRIETE_DE_RETARD + " ne la nomme, "
                        + "alors que le paquet " + plafond.paquet() + " peut afficher "
                        + plafond.colonnes() + " colonnes. Le dernier rang couvert est le "
                        + dernierCouvert + ", a " + retards.get(dernierCouvert) + " : cette colonne-ci "
                        + "demarrerait donc avec la premiere, et le decalage livre cesserait d'exister "
                        + "pour elle sans qu'aucun diff ne le montre. Prolonger la progression n'est "
                        + "pas la sortie : le retard de la derniere colonne creverait la borne de "
                        + "nadia-prof, que la clause 6 de LAnimationRetardeMaisNeDetermineRienTest "
                        + "verifie. La sortie est de revoir le decalage pour qu'il tienne a "
                        + plafond.colonnes() + " colonnes - forme visuelle, donc ines-design sous "
                        + "arbitrage de nadia-prof (ADR-0029)");
            }
        }

        assertThat(defauts)
                .as("ADR-0029 : chaque colonne que le contenu de %s/ peut produire porte un %s "
                        + "declare par une feuille maison", REPERTOIRE_DE_CONTENU, PROPRIETE_DE_RETARD)
                .isEmpty();
    }

    @Test
    void chaqueColonneQueLeContenuDuDepotPeutProduirePorteSonDecalage() {
        verifierQueChaqueColonnePorteSonDecalage(racinesDeProduction());
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet a cinq propositions, servi avec la feuille
     * telle que S7-1 l'a livree. C'est la forme exacte que prendrait le defaut le jour ou un paquet
     * offrirait une colonne de plus - le trou que le sprint 7 a laisse ouvert, et que rien ne voyait.
     */
    @Test
    void unPaquetAUneColonneDePlusQueLaFeuilleFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueColonnePorteSonDecalage(
                List.of(CONTRE_EXEMPLES.resolve("cinq-colonnes"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("colonne 5 : aucun animation-delay ne la nomme")
                .hasMessageContaining("Le dernier rang couvert est le 4, a 0.24s");
    }

    /**
     * Le meme paquet, avec une feuille dont le decalage a ete revu pour tenir a cinq colonnes : il
     * doit redevenir vert. Sans ce cas, la clause serait une interdiction de la cinquieme colonne
     * deguisee en clause de couverture, et aucun jeu fautif ne le dirait - la lecon de la seconde
     * mutation d'ADR-0025.
     */
    @Test
    void leMemePaquetAvecUnDecalageRevuPourCinqColonnesRedevientVert() {
        verifierQueChaqueColonnePorteSonDecalage(
                List.of(CONTRE_EXEMPLES.resolve("cinq-colonnes-corrigee")));
    }

    /**
     * ADR-0013 obligation 2 : un paquet dont le nombre de propositions n'est pas lisible est un
     * defaut et non une valeur ignoree. Sans ce cas, une troisieme forme de paquet entrerait dans le
     * depot en faisant sortir sa population du controle en silence.
     *
     * <p><b>Deux griefs sur le meme paquet fautif, et le second n'etait pas assere jusqu'au
     * 2026-09-08.</b> Ce jeu d'artefacts portait deja un {@code type} qu'aucun chargeur n'enregistre
     * - il avait ete choisi pour la couleur du fautif - et personne ne verifiait que ce type-la
     * produise un grief. Il en produit un depuis {@link #defautDuTypeNonInstruit(String, Map)}, et
     * les deux sont exiges ici : une forme illisible et un type non instruit sont deux defauts
     * distincts, et un paquet peut n'avoir que le second - c'est exactement le cas d'un paquet de
     * designation.</p>
     */
    @Test
    void unPaquetDontLeNombreDePropositionsEstIllisibleFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueColonnePorteSonDecalage(
                List.of(CONTRE_EXEMPLES.resolve("paquet-illisible"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare ni items lisibles ni parametres.nombreDePropositions")
                .hasMessageContaining("que ce garde-fou n'a pas instruit");
    }
}
