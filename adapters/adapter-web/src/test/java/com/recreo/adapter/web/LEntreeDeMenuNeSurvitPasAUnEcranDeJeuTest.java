package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0022 : un element d'habillage permanent qui ne doit pas s'afficher sur tous les ecrans declare
 * son masquage dans la feuille maison, et chaque ecran ou il doit disparaitre porte le marqueur
 * d'etat que ce masquage nomme.
 *
 * <p>Le nom de cette classe dit ce qu'elle verifie et pas plus. Elle ne mesure aucune visibilite :
 * une visibilite est une propriete du rendu, et aucun automate du depot ne rend une page. Ce qui est
 * lu ici est le <b>couple declaratif</b> qui produit cette visibilite - la regle de masquage d'un
 * cote, le marqueur pose par le serveur de l'autre - et la seule chose qu'elle interdit reellement
 * est qu'un des deux existe sans l'autre. Que l'entree de menu soit effectivement invisible sur les
 * cinq etats de l'ecran de jeu reste la propriete de {@code nadia-prof}, qui l'a ecrit elle-meme
 * dans son arbitrage du 2026-08-07.
 *
 * <p>Motif : l'arbitrage de S6-9 demande qu'un element d'un en-tete <b>permanent</b> - jamais efface
 * ni recree par un echange htmx (DEC-0012, S6-1) - cesse d'etre visible sur les cinq etats de
 * l'ecran de jeu. Le serveur ne rend que la zone {@code hx-target} ; il ne peut donc pas decider de
 * l'en-tete, qui en est le frere. Le seul levier qui reste sans JavaScript (ADR-0008, ADR-0014
 * point 7) est un selecteur relationnel keye sur ce que le serveur pose, lui, dans la zone.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les ecrans viennent du repertoire de gabarits, la coquille est
 *       identifiee par le fait qu'elle contient la zone {@code hx-target} - jamais par son nom de
 *       fichier ni par son repertoire. Tout autre gabarit est un fragment insere dans cette zone,
 *       donc un ecran ou l'element masque ne doit pas se montrer. Un fragment neuf entre donc
 *       automatiquement dans le controle ;</li>
 *   <li><b>derivation totale</b> : aucune zone echangee, plusieurs zones distinctes, aucune
 *       coquille, plusieurs coquilles, aucun fragment, ou un masquage dont la classe n'est portee
 *       par aucun element hors de la zone sont des <b>echecs</b>, jamais des cas ignores ;</li>
 *   <li><b>mise a l'epreuve</b> : sur un jeu fautif neuf, un fragment qui oublie le marqueur, et par
 *       mutation reelle du depot (voir ADR-0022).</li>
 * </ul>
 */
class LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest");

    private static final String ZONE_ECHANGEE = "hx-target";

    /**
     * La forme contractuelle d'un masquage, celle que la feuille maison doit ecrire :
     * {@code body:has(.<marqueur>) .<habillage> { display: none }}. Une forme approchante n'est pas
     * lue de travers, elle n'est pas lue du tout - et le garde-fou echoue alors faute de masquage.
     *
     * <p>Elle est lue caractere par caractere et non par expression reguliere : ce module l'interdit
     * (`AssertionsWebTest`, ADR-0008, dette 5 de la version precedente), et la regle a raison de le
     * faire meme ici, ou le sujet est un selecteur CSS plutot que du HTML.</p>
     */
    private static final String DEBUT_DU_MASQUAGE = "body:has(.";

    private static final String PROPRIETE_DE_MASQUAGE = "display";
    private static final String VALEUR_DE_MASQUAGE = "none";

    private static boolean estNomDeClasse(String candidat) {
        if (candidat.isEmpty()) {
            return false;
        }
        for (int position = 0; position < candidat.length(); position++) {
            char caractere = candidat.charAt(position);
            if (!Character.isLetterOrDigit(caractere) && caractere != '-' && caractere != '_') {
                return false;
            }
        }
        return true;
    }

    /** Le masquage que ce selecteur declare, vide si sa forme n'est pas celle du contrat. */
    private static Optional<Masquage> formeDuMasquage(String selecteur) {
        if (!selecteur.startsWith(DEBUT_DU_MASQUAGE)) {
            return Optional.empty();
        }
        int finDuMarqueur = selecteur.indexOf(')', DEBUT_DU_MASQUAGE.length());
        if (finDuMarqueur < 0) {
            return Optional.empty();
        }
        String marqueur = selecteur.substring(DEBUT_DU_MASQUAGE.length(), finDuMarqueur);
        String reste = selecteur.substring(finDuMarqueur + 1).strip();
        if (!reste.startsWith(".")) {
            return Optional.empty();
        }
        String habillage = reste.substring(1);
        if (!estNomDeClasse(marqueur) || !estNomDeClasse(habillage)) {
            return Optional.empty();
        }
        return Optional.of(new Masquage(marqueur, habillage));
    }

    /** Le marqueur d'etat pose par le serveur, et la classe d'habillage que sa presence cache. */
    record Masquage(String marqueur, String habillage) {
    }

    /**
     * Les masquages declares par la feuille maison. Derive du CSS servi, jamais d'une liste ecrite
     * ici : c'est la feuille qui gouverne le rendu, donc c'est d'elle que le perimetre descend
     * (ADR-0015).
     */
    static List<Masquage> masquages(Path racineDesFeuilles) {
        List<Masquage> masquages = new ArrayList<>();
        for (Map.Entry<String, String> bloc : blocsMaison(racineDesFeuilles).entrySet()) {
            Optional<Masquage> forme = formeDuMasquage(bloc.getKey().strip());
            if (forme.isEmpty()) {
                continue;
            }
            int declaration = debutDeDeclaration(bloc.getValue(), PROPRIETE_DE_MASQUAGE);
            if (declaration < 0) {
                continue;
            }
            String valeur = valeurDeclaree(bloc.getValue(), declaration, PROPRIETE_DE_MASQUAGE).strip();
            if (VALEUR_DE_MASQUAGE.equals(valeur)) {
                masquages.add(forme.get());
            }
        }
        return masquages;
    }

    /** Vrai si cet ecran porte le marqueur du masquage, donc si l'habillage y est cache. */
    static boolean masqueSur(Document ecran, Masquage masquage) {
        return !ecran.select("." + masquage.marqueur()).isEmpty();
    }

    /**
     * Les gabarits analyses, indexes par leur chemin relatif. Visible dans le paquet depuis ADR-0031 :
     * {@code UnEcranNeGouvernePasLApparenceDUnAutreTest} derive sa composition des memes documents, et
     * un second balayage du repertoire de gabarits serait une duplication sans motif.
     */
    static Map<String, Document> documents(Path racineDesGabarits) {
        Map<String, Document> documents = new LinkedHashMap<>();
        for (Path gabarit : gabarits(racineDesGabarits)) {
            String source = racineDesGabarits.relativize(gabarit).toString().replace('\\', '/');
            documents.put(source, Jsoup.parse(lire(gabarit), gabarit.toString()));
        }
        return documents;
    }

    /**
     * La zone que les echanges htmx remplacent, unique dans tout le depot. Visible dans le paquet
     * depuis ADR-0031, qui identifie la coquille par la meme derivation : c'est elle qui dit quels
     * jetons sont permanents et lesquels appartiennent a un ecran echange.
     */
    static Optional<String> zoneEchangee(Iterable<Document> documents, Set<String> defauts) {
        Set<String> cibles = new LinkedHashSet<>();
        for (Document document : documents) {
            for (Element porteur : document.select("[" + ZONE_ECHANGEE + "]")) {
                cibles.add(porteur.attr(ZONE_ECHANGEE).strip());
            }
        }
        if (cibles.size() != 1) {
            defauts.add("les gabarits declarent " + cibles.size() + " zone(s) " + ZONE_ECHANGEE + " "
                    + cibles + " ; ce garde-fou ne sait pas dire quel gabarit est la coquille "
                    + "permanente et lesquels sont les ecrans inseres dedans");
            return Optional.empty();
        }
        return cibles.stream().findFirst();
    }

    static void verifierQueLHabillageMasqueDeclareSonMarqueur(Path racineDesGabarits,
                                                             Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, Document> documents = documents(racineDesGabarits);
        List<Masquage> masquages = masquages(racineDesFeuilles);

        assertThat(documents)
                .as("ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racineDesGabarits)
                .isNotEmpty();
        assertThat(masquages)
                .as("ADR-0022 : la feuille maison doit declarer au moins un masquage de la forme "
                        + "body:has(.<marqueur>) .<habillage> { display: none }. Sans lui, l'entree de "
                        + "menu de l'en-tete permanent reste visible sur les ecrans de jeu, ce que "
                        + "l'arbitrage de nadia-prof du 2026-08-07 refuse")
                .isNotEmpty();

        Optional<String> zone = zoneEchangee(documents.values(), defauts);
        if (zone.isPresent()) {
            List<String> coquilles = documents.entrySet().stream()
                    .filter(ecran -> !ecran.getValue().select(zone.get()).isEmpty())
                    .map(Map.Entry::getKey)
                    .toList();
            if (coquilles.size() != 1) {
                defauts.add("la zone " + zone.get() + " est declaree par " + coquilles.size()
                        + " gabarit(s) " + coquilles + " ; il en faut exactement un, sans quoi ce "
                        + "garde-fou ne sait pas quels ecrans sont inseres dans cette zone");
            } else {
                String coquille = coquilles.get(0);
                List<String> fragments = documents.keySet().stream()
                        .filter(ecran -> !ecran.equals(coquille))
                        .toList();
                if (fragments.isEmpty()) {
                    defauts.add("aucun gabarit n'est insere dans " + zone.get() + " : ce garde-fou "
                            + "n'aurait aucun ecran de jeu a juger et passerait a vide");
                }
                for (Masquage masquage : masquages) {
                    for (String fragment : fragments) {
                        if (!masqueSur(documents.get(fragment), masquage)) {
                            defauts.add("ecran " + fragment + " : insere dans " + zone.get()
                                    + ", il ne porte pas le marqueur \"" + masquage.marqueur()
                                    + "\" que la feuille maison exige pour cacher \"."
                                    + masquage.habillage() + "\" ; l'entree de menu de l'en-tete "
                                    + "permanent resterait donc visible sur cet ecran");
                        }
                    }
                    if (masqueSur(documents.get(coquille), masquage)) {
                        defauts.add("la coquille " + coquille + " porte elle-meme le marqueur \""
                                + masquage.marqueur() + "\" : \"." + masquage.habillage()
                                + "\" serait cache partout, y compris sur l'accueil ou il doit se "
                                + "montrer");
                    }
                    boolean portePar = documents.values().stream()
                            .flatMap(document -> document.select("." + masquage.habillage()).stream())
                            .findAny()
                            .isPresent();
                    if (!portePar) {
                        defauts.add("le masquage de \"." + masquage.habillage() + "\" ne cache rien : "
                                + "aucun gabarit ne pose cette classe. Une regle qui masque une classe "
                                + "absente est decorative");
                    }
                }
            }
        }

        assertThat(defauts)
                .as("ADR-0022 : tout ecran insere dans la zone echangee porte le marqueur d'etat que "
                        + "la feuille maison exige pour masquer un habillage permanent")
                .isEmpty();
    }

    /**
     * S14-2 (BARRE-7, DEC-0033) : retiree. <b>Motif ecrit de la suppression</b>, exige par
     * ADR-0013 : ce test exercait {@code verifierQueLHabillageMasqueDeclareSonMarqueur} contre la
     * production reelle, dont la premiere assertion exige qu'au moins un masquage de la forme
     * {@code body:has(.<marqueur>) .<habillage>} soit declare - sans lui, "l'entree de menu de
     * l'en-tete permanent reste visible sur les ecrans de jeu", le defaut que l'arbitrage de
     * nadia-prof du 2026-08-07 refusait. Cette entree - le lien "Les jeux" de l'en-tete - est
     * retiree de tout gabarit par S14-2, jamais remplacee par un comportement neuf (DEC-0033) : il
     * n'y a donc plus rien a masquer, et la production ne declare plus aucun masquage de cette
     * forme. Maintenir ce test l'aurait fait rougir pour un motif qu'il n'a pas choisi de garder -
     * le mecanisme d'ADR-0022 lui-meme n'a plus d'instance en production, pas seulement son
     * epinglage.
     *
     * <p>Ce qui reste garde, et par quoi : la <b>fonction</b> de ce garde-fou survit intacte -
     * {@code verifierQueLHabillageMasqueDeclareSonMarqueur} et son helper {@code masquages} restent
     * exerces par {@code unEcranInsereQuiOublieLeMarqueurFaitEchouerLeGardeFou} et
     * {@code uneFeuilleSansRegleDeMasquageFaitEchouerLeGardeFou} (sur des jeux fautifs, pas sur la
     * production), et {@code masquages}/{@code masqueSur} restent utilises tels quels par
     * {@code AucunArretDeTabulationNEstDestructeurTest} pour deriver l'ordre de tabulation. Seule la
     * clause qui exigeait qu'un masquage existe <b>en production</b> perd son objet.</p>
     *
     * <p><b>Propose pour confirmation ecrite de theo-archi (via sofia-sm), pas tranche seul</b> :
     * le mecanisme de masquage d'ADR-0022 (un habillage permanent masque sur les ecrans de jeu)
     * n'a plus aucune instance en production a la date de ce commit. Ce retrait suit le patron
     * deja etabli dans {@code LActionNeDeclarePasMoinsQueLaSortieTest} pour un sujet disparu, mais
     * la question de savoir si ADR-0022 lui-meme merite un amendement de constat - comme il en a
     * deja recu un pendant S14-1 - reste ouverte et n'est pas tranchee ici.</p>
     */

    /**
     * Epinglage de ce que l'arbitrage a tranche, cote texte : un seul mot de sortie circule sur toute
     * l'application. Si quelqu'un remet "Accueil" sur le lien de menu, la reserve 3 de nadia-prof se
     * rouvre - elle a leve cette reserve par ce geste precis, et pas par un autre.
     */
    @Test
    void leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot() {
        Map<String, Document> documents = documents(RACINE_PRODUCTION);
        List<String> libelles = new ArrayList<>();
        for (Document ecran : documents.values()) {
            for (Element bouton : ecran.select("[data-role=sortie]")) {
                libelles.add(bouton.text().strip());
            }
        }

        assertThat(libelles)
                .as("ADR-0022 : chaque sortie de l'application porte le meme mot, celui que "
                        + "nadia-prof a retenu au CP")
                .isNotEmpty()
                .containsOnly("Les jeux");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un jeu de gabarits que personne n'a inscrit nulle
     * part, ou la feuille declare le masquage et ou l'un des deux ecrans inseres dans la zone oublie
     * le marqueur. C'est exactement la faute qu'un fragment neuf commettra, et le seul perimetre qui
     * puisse la voir est celui qui derive les ecrans du repertoire.
     */
    @Test
    void unEcranInsereQuiOublieLeMarqueurFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("fragment-sans-marqueur");
        assertThatThrownBy(() -> verifierQueLHabillageMasqueDeclareSonMarqueur(racine, racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ecran templates/fragments/bilan.html : insere dans #jeu, il ne "
                        + "porte pas le marqueur \"ecran-de-jeu\"");
    }

    /**
     * L'autre sens de la meme faute, et celui qui produirait un vert silencieux plutot qu'un ecran
     * fautif : la feuille maison perd sa regle de masquage. Les gabarits sont ceux de la production,
     * seule la feuille change - donc c'est bien l'absence du masquage qui est jugee, rien d'autre.
     */
    @Test
    void uneFeuilleSansRegleDeMasquageFaitEchouerLeGardeFou() {
        Path feuilles = CONTRE_EXEMPLES.resolve("feuille-sans-masquage");
        assertThatThrownBy(
                () -> verifierQueLHabillageMasqueDeclareSonMarqueur(RACINE_PRODUCTION, feuilles))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la feuille maison doit declarer au moins un masquage");
    }
}
