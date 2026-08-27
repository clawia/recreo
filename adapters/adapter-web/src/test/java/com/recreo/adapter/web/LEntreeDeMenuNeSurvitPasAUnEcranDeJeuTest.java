package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0022 : un element d'habillage permanent qui ne doit pas s'afficher sur certains ecrans
 * declare son masquage dans la feuille maison, et l'ecran, lui, declare son etat par un marqueur
 * que le serveur pose dans la zone echangee.
 *
 * <p>Le nom de cette classe dit ce qu'elle verifie et pas plus. Elle ne mesure aucune visibilite :
 * une visibilite est une propriete du rendu, et aucun automate du depot ne rend une page. Ce qui
 * est lu ici est le <b>couple declaratif</b> qui produit cette visibilite - la regle de masquage
 * d'un cote, le marqueur pose par le serveur de l'autre.
 *
 * <p><b>Troisieme amendement d'ADR-0022 (2026-08-17, apres le refus pedagogique de nadia-prof) :
 * la clause change de forme.</b> La forme d'origine presupposait qu'un masquage vise <b>la famille
 * entiere</b> des ecrans inseres (vrai pour {@code ecran-de-jeu}/{@code .lien-accueil} en s06, qui
 * visait les cinq etats de l'ecran de jeu a la fois) et exigeait donc que <b>tout</b> gabarit
 * insere porte le marqueur. C'est faux des qu'un masquage vise un <b>sous-ensemble</b> d'ecrans -
 * exactement le cas de la barre de niveaux, qui doit disparaitre sur la confirmation et sur
 * l'erreur (marqueur {@code ecran-exclusif}) et rester visible sur la manche comme sur la fin de
 * partie. Deux clauses seulement, aucune troisieme (a l'ouverture d'ADR-0022 - s15 en ajoute deux
 * de plus, voir plus bas) :
 * <ul>
 *   <li><b>Clause A</b> ({@link #verifierQueLeCoupleDeMasquageEstCoherent}) : pour chaque masquage
 *       declare, son marqueur est pose par au moins un ecran insere et sa classe cachee par au
 *       moins un element permanent - <b>verte sur une population vide</b>, aucune assertion
 *       n'exige qu'un masquage existe ;</li>
 *   <li><b>Clause B</b> ({@link #verifierQuAucunActionnablePermanentNeSurvitSurUnEcranExclusif})
 *       : sur tout ecran qui porte le marqueur d'exclusivite ({@code ecran-exclusif}), aucun
 *       element actionnable permanent ne survit aux masquages declares - la propriete que
 *       nadia-prof vient de mesurer par capture, sans jamais ecrire un nombre ;</li>
 *   <li><b>Clause C', aucune, a l'ouverture d'ADR-0022</b> : le jeton qu'aucun ecran ne peut poser
 *       est deja refuse par {@code UnEcranNeGouvernePasLApparenceDUnAutreTest} (ADR-0031), qui a
 *       deja mordu sur la regle morte pendant S14-2. Une troisieme clause pour la meme propriete
 *       serait decorative.</li>
 * </ul>
 *
 * <p>Un ecran est <b>exclusif</b> quand tout chemin qu'il ouvre a l'enfant vit a l'interieur de
 * lui - la confirmation (continuer la partie ou la quitter) et l'erreur (recommencer). Le nom du
 * marqueur est du balisage et revient a {@code ines-design} ; {@code ecran-exclusif} est celui que
 * {@code theo-archi} propose en attendant.
 *
 * <p><b>s15, troisieme arbitrage de l'utilisatrice (DEC-0034, 2026-08-18) : deux clauses de plus,
 * exigees par theo-archi</b> (squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-sortie-en-tete.md).
 * La sortie d'en-tete ({@code .lien-accueil}) reste visible sur la manche desormais (l'utilisatrice
 * renverse l'arbitrage de nadia-prof du 2026-08-07, S6-9) et disparait sur les trois ecrans qui
 * portent deja leur propre sortie ou qui n'en ouvrent aucune. {@code ecran-exclusif} ne peut pas
 * couvrir seul ce sous-ensemble (il manque la fin de partie, et l'y ajouter masquerait aussi les
 * puces de niveau, contre l'arbitrage de s14) : un second marqueur, <b>{@code ecran-porte-sa-sortie}</b>
 * (nom propose par {@code theo-archi}, revient a {@code ines-design}), porte un <b>fait structurel
 * verifiable</b> - "cet ecran rend lui-meme un {@code [data-role=sortie]}" - jamais un jugement
 * pedagogique comme {@code ecran-exclusif}.
 * <ul>
 *   <li><b>Clause C</b> ({@link #verifierQueLeMarqueurDeSortiePropreNeMentJamais}) : un ecran
 *       insere porte {@code ecran-porte-sa-sortie} <b>si et seulement si</b> il rend au moins un
 *       {@code [data-role=sortie]}. Les deux fautes symetriques sont des defauts reels : un ecran
 *       qui gagne une sortie sans le marqueur ferait lire "Les jeux" deux fois a l'enfant ; un
 *       ecran qui porte le marqueur sans sortie masquerait celle de l'en-tete sans rien mettre a
 *       la place - le cul-de-sac que nadia-prof avait refuse en s06 ;</li>
 *   <li><b>Clause D</b> ({@link #verifierQuAuPlusUneSortieResteVisibleParEcran}) : BARRE-5 derivee,
 *       sans aucun nombre - sur tout ecran assemble, masquages appliques, <b>au plus un</b> element
 *       visible porte {@code data-role="sortie"}. C'est le {@code 0/1/1/0/1} que nadia-prof a
 *       mesure par capture, rendu executable sans epingler ni ecran ni compte - <b>non-vacuite
 *       exigee</b>, au moins un ecran doit rendre une sortie visible.</li>
 * </ul>
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les ecrans viennent du repertoire de gabarits, la coquille est
 *       identifiee par le fait qu'elle contient la zone {@code hx-target} - jamais par son nom de
 *       fichier ni par son repertoire ;</li>
 *   <li><b>derivation totale</b> : aucune zone echangee, plusieurs zones distinctes, aucune
 *       coquille, plusieurs coquilles ou aucun fragment insere sont des <b>echecs</b>, jamais des
 *       cas ignores ;</li>
 *   <li><b>mise a l'epreuve</b> : sur du materiau reel mute plutot que sur une fixture ecrite a la
 *       main (lecon de s05, ADR-0015) - une copie de la production dont un marqueur est retire
 *       (clause A) ou dont un masquage nomme le conteneur au lieu de la puce (clause B, et c'est
 *       le piege que l'avis de faisabilite de s14 avait nomme d'avance).</li>
 * </ul>
 */
class LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest");

    private static final String ZONE_ECHANGEE = "hx-target";

    /**
     * Le marqueur d'exclusivite (ADR-0022, troisieme amendement) : un ecran est exclusif quand
     * tout chemin qu'il ouvre a l'enfant vit a l'interieur de lui. Nom propose par theo-archi ;
     * revient a ines-design (c'est du balisage), non tranche ici.
     */
    private static final String MARQUEUR_EXCLUSIF = "ecran-exclusif";

    /**
     * Le marqueur de sortie propre (s15, exigences de theo-archi ci-dessus) : un ecran le porte
     * quand et seulement quand il rend lui-meme un {@code [data-role=sortie]}. Nom propose par
     * theo-archi ; revient a ines-design (c'est du balisage), non tranche ici.
     */
    private static final String MARQUEUR_SORTIE_PROPRE = "ecran-porte-sa-sortie";

    private static final String SELECTEUR_SORTIE = "[data-role=sortie]";

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
            String valeur = valeurDeclaree(bloc.getValue(), PROPRIETE_DE_MASQUAGE).strip();
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

    /** Vrai si `candidat` est `racine` ou l'un de ses descendants. */
    private static boolean estDansLElement(Element candidat, Element racine) {
        for (Element noeud = candidat; noeud != null; noeud = noeud.parent()) {
            if (noeud == racine) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clause A (ADR-0022, troisieme amendement, section 7.2) : coherence du couple de masquage,
     * dans les deux sens, <b>verte sur une population vide</b>. Ne reprend pas "tout ecran insere
     * porte le marqueur" - cette propriete est fausse des qu'un masquage vise un sous-ensemble
     * d'ecrans, exactement le cas de {@code ecran-exclusif}.
     */
    static void verifierQueLeCoupleDeMasquageEstCoherent(Path racineDesGabarits,
                                                        Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, Document> documents = documents(racineDesGabarits);
        List<Masquage> masquages = masquages(racineDesFeuilles);

        assertThat(documents)
                .as("ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racineDesGabarits)
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
                assertThat(fragments)
                        .as("aucun gabarit n'est insere dans %s sous %s : ce garde-fou n'aurait "
                                + "aucun ecran a juger et passerait a vide", zone.get(),
                                racineDesGabarits)
                        .isNotEmpty();

                Element racineDeZone = documents.get(coquille).select(zone.get()).first();
                for (Masquage masquage : masquages) {
                    boolean marqueurPose = fragments.stream()
                            .anyMatch(fragment -> masqueSur(documents.get(fragment), masquage));
                    if (!marqueurPose) {
                        defauts.add("le marqueur \"" + masquage.marqueur() + "\" n'est pose par "
                                + "aucun ecran insere dans " + zone.get() + " : ce masquage ne peut "
                                + "jamais s'appliquer");
                    }
                    boolean habillagePermanent = documents.get(coquille)
                            .select("." + masquage.habillage()).stream()
                            .anyMatch(element -> !estDansLElement(element, racineDeZone));
                    if (!habillagePermanent) {
                        defauts.add("la classe \"" + masquage.habillage() + "\" que ce masquage "
                                + "cache n'est posee par aucun element permanent (hors de "
                                + zone.get() + ") : ce n'est pas un habillage permanent, la regle "
                                + "ne peut rien cacher qui survive a un echange htmx");
                    }
                }
            }
        }

        assertThat(defauts)
                .as("ADR-0022 : pour chaque masquage declare, son marqueur est pose par au moins "
                        + "un ecran insere et sa classe cachee par au moins un element permanent - "
                        + "aucune assertion n'exige qu'un masquage existe")
                .isEmpty();
    }

    /**
     * Clause B (ADR-0022, troisieme amendement, section 7.2) : sur tout ecran qui porte le
     * marqueur d'exclusivite, aucun element actionnable permanent ne survit aux masquages
     * declares. C'est la propriete que nadia-prof vient de mesurer par capture (BARRE-1, BARRE-2),
     * portee ici sans jamais ecrire un nombre - "deux boites" et "une boite" sont les contenus du
     * jour de deux ecrans, pas une propriete.
     */
    static void verifierQuAucunActionnablePermanentNeSurvitSurUnEcranExclusif(Path racineDesGabarits,
                                                                             Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, Document> documents = documents(racineDesGabarits);
        List<Masquage> masquages = masquages(racineDesFeuilles);
        List<String> ecransExclusifs = new ArrayList<>();

        assertThat(documents)
                .as("ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racineDesGabarits)
                .isNotEmpty();

        Optional<String> zone = zoneEchangee(documents.values(), defauts);
        if (zone.isPresent()) {
            List<String> coquilles = documents.entrySet().stream()
                    .filter(ecran -> !ecran.getValue().select(zone.get()).isEmpty())
                    .map(Map.Entry::getKey)
                    .toList();
            if (coquilles.size() != 1) {
                defauts.add("la zone " + zone.get() + " est declaree par " + coquilles.size()
                        + " gabarit(s) " + coquilles + " ; il en faut exactement un");
            } else {
                String coquille = coquilles.get(0);
                Element racineDeZone = documents.get(coquille).select(zone.get()).first();
                List<Element> actionnablesPermanents = documents.get(coquille)
                        .select("button, a[href]").stream()
                        .filter(element -> !estDansLElement(element, racineDeZone))
                        .toList();

                for (Map.Entry<String, Document> ecran : documents.entrySet()) {
                    if (ecran.getKey().equals(coquille)
                            || ecran.getValue().select("." + MARQUEUR_EXCLUSIF).isEmpty()) {
                        continue;
                    }
                    ecransExclusifs.add(ecran.getKey());
                    for (Element actionnable : actionnablesPermanents) {
                        boolean masque = masquages.stream().anyMatch(masquage ->
                                actionnable.hasClass(masquage.habillage())
                                        && masqueSur(ecran.getValue(), masquage));
                        if (!masque) {
                            defauts.add("ecran " + ecran.getKey() + " se declare exclusif (\""
                                    + MARQUEUR_EXCLUSIF + "\") mais l'element actionnable permanent "
                                    + "<" + actionnable.tagName() + " class=\""
                                    + actionnable.className() + "\"> \"" + actionnable.text()
                                    + "\" n'est masque par aucun masquage declare : tout chemin que "
                                    + "cet ecran ouvre a l'enfant doit vivre a l'interieur de lui");
                        }
                    }
                }
            }
        }

        assertThat(ecransExclusifs)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran ne se declare exclusif "
                        + "sous %s", racineDesGabarits)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0022 : sur un ecran exclusif, aucun element actionnable permanent ne "
                        + "survit aux masquages declares")
                .isEmpty();
    }

    /**
     * Clause C (s15, exigences de theo-archi) : le marqueur de sortie propre ne peut pas mentir.
     * Population : les memes ecrans inseres que la clause A. Pour chacun, porter le marqueur
     * equivaut a rendre au moins un {@code [data-role=sortie]}.
     */
    static void verifierQueLeMarqueurDeSortiePropreNeMentJamais(Path racineDesGabarits) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, Document> documents = documents(racineDesGabarits);

        assertThat(documents)
                .as("ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racineDesGabarits)
                .isNotEmpty();

        Optional<String> zone = zoneEchangee(documents.values(), defauts);
        if (zone.isPresent()) {
            List<String> coquilles = documents.entrySet().stream()
                    .filter(ecran -> !ecran.getValue().select(zone.get()).isEmpty())
                    .map(Map.Entry::getKey)
                    .toList();
            if (coquilles.size() != 1) {
                defauts.add("la zone " + zone.get() + " est declaree par " + coquilles.size()
                        + " gabarit(s) " + coquilles + " ; il en faut exactement un");
            } else {
                String coquille = coquilles.get(0);
                for (Map.Entry<String, Document> ecran : documents.entrySet()) {
                    if (ecran.getKey().equals(coquille)) {
                        continue;
                    }
                    boolean porteLeMarqueur = !ecran.getValue()
                            .select("." + MARQUEUR_SORTIE_PROPRE).isEmpty();
                    boolean porteUneSortie = !ecran.getValue().select(SELECTEUR_SORTIE).isEmpty();
                    if (porteLeMarqueur && !porteUneSortie) {
                        defauts.add("ecran " + ecran.getKey() + " porte \"" + MARQUEUR_SORTIE_PROPRE
                                + "\" sans rendre aucune sortie : la sortie d'en-tete y serait "
                                + "masquee sans rien mettre a la place");
                    }
                    if (porteUneSortie && !porteLeMarqueur) {
                        defauts.add("ecran " + ecran.getKey() + " rend une sortie sans porter \""
                                + MARQUEUR_SORTIE_PROPRE + "\" : la sortie d'en-tete y resterait "
                                + "visible a cote de la sienne");
                    }
                }
            }
        }

        assertThat(defauts)
                .as("le marqueur de sortie propre doit etre pose si et seulement si l'ecran rend "
                        + "lui-meme une sortie")
                .isEmpty();
    }

    /**
     * Clause D (s15, exigences de theo-archi) : BARRE-5 derivee, sans aucun nombre. Sur tout ecran
     * assemble - la coquille moins la zone echangee, plus le fragment insere - au plus un element
     * visible porte {@code data-role="sortie"}, une fois les masquages declares appliques.
     * Population : les memes ecrans inseres que la clause A ; "visible" derive des memes masquages
     * que les clauses A et B, jamais d'une mesure de rendu.
     *
     * <p>Une sortie compte de deux facons : rendue par le fragment lui-meme (jamais masquee, un
     * masquage ne visant que l'habillage permanent), ou rendue par la coquille hors de la zone
     * echangee (l'entree de menu de l'en-tete) et non masquee par aucun des masquages que cet
     * ecran declare porter.
     */
    static void verifierQuAuPlusUneSortieResteVisibleParEcran(Path racineDesGabarits,
                                                              Path racineDesFeuilles) {
        Set<String> defauts = new LinkedHashSet<>();
        Map<String, Document> documents = documents(racineDesGabarits);
        List<Masquage> masquages = masquages(racineDesFeuilles);
        boolean auMoinsUneSortieVisible = false;
        List<String> ecransJuges = new ArrayList<>();

        assertThat(documents)
                .as("ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racineDesGabarits)
                .isNotEmpty();

        Optional<String> zone = zoneEchangee(documents.values(), defauts);
        if (zone.isPresent()) {
            List<String> coquilles = documents.entrySet().stream()
                    .filter(ecran -> !ecran.getValue().select(zone.get()).isEmpty())
                    .map(Map.Entry::getKey)
                    .toList();
            if (coquilles.size() != 1) {
                defauts.add("la zone " + zone.get() + " est declaree par " + coquilles.size()
                        + " gabarit(s) " + coquilles + " ; il en faut exactement un");
            } else {
                String coquille = coquilles.get(0);
                Element racineDeZone = documents.get(coquille).select(zone.get()).first();
                List<Element> sortiesPermanentes = documents.get(coquille)
                        .select(SELECTEUR_SORTIE).stream()
                        .filter(element -> !estDansLElement(element, racineDeZone))
                        .toList();

                for (Map.Entry<String, Document> ecran : documents.entrySet()) {
                    if (ecran.getKey().equals(coquille)) {
                        continue;
                    }
                    ecransJuges.add(ecran.getKey());
                    long sortiesLocales = ecran.getValue().select(SELECTEUR_SORTIE).size();
                    long sortiesPermanentesVisibles = sortiesPermanentes.stream()
                            .filter(element -> masquages.stream().noneMatch(masquage ->
                                    element.hasClass(masquage.habillage())
                                            && masqueSur(ecran.getValue(), masquage)))
                            .count();
                    long total = sortiesLocales + sortiesPermanentesVisibles;
                    if (total >= 1) {
                        auMoinsUneSortieVisible = true;
                    }
                    if (total > 1) {
                        defauts.add("ecran " + ecran.getKey() + " rend " + total + " sortie(s) "
                                + "visible(s) a la fois (BARRE-5) : au plus une seule ne doit rester "
                                + "visible");
                    }
                }
            }
        }

        assertThat(ecransJuges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran insere n'a ete juge sous "
                        + "%s", racineDesGabarits)
                .isNotEmpty();
        assertThat(auMoinsUneSortieVisible)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran ne rend de sortie visible")
                .isTrue();
        assertThat(defauts)
                .as("BARRE-5 : au plus une sortie visible par ecran assemble")
                .isEmpty();
    }

    /**
     * Copie recursive d'un arbre reel vers une destination, pour muter une copie plutot que le
     * depot (ADR-0015 : materiau reel, jamais une fixture ecrite a la main). Fonction utilitaire
     * sans logique de garde-fou : elle ne fait que dupliquer des octets.
     */
    private static void copierArbreReel(Path source, Path destination) throws IOException {
        try (Stream<Path> arborescence = Files.walk(source)) {
            for (Path chemin : (Iterable<Path>) arborescence::iterator) {
                Path cible = destination.resolve(source.relativize(chemin).toString());
                if (Files.isDirectory(chemin)) {
                    Files.createDirectories(cible);
                } else {
                    Files.createDirectories(cible.getParent());
                    Files.copy(chemin, cible);
                }
            }
        }
    }

    @Test
    void chaqueMasquageDeclareEstCoherentSurLaProductionReelle() {
        verifierQueLeCoupleDeMasquageEstCoherent(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    @Test
    void aucunActionnablePermanentNeSurvitSurUnEcranExclusifEnProduction() {
        verifierQuAucunActionnablePermanentNeSurvitSurUnEcranExclusif(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    @Test
    void leMarqueurDeSortiePropreNeMentJamaisEnProduction() {
        verifierQueLeMarqueurDeSortiePropreNeMentJamais(RACINE_PRODUCTION);
    }

    @Test
    void auPlusUneSortieResteVisibleParEcranEnProduction() {
        verifierQuAuPlusUneSortieResteVisibleParEcran(RACINE_PRODUCTION, RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3, clauses C et D (s15) : preuve de morsure sur du materiau reel, exigee
     * par theo-archi - une copie de la production dont le marqueur de sortie propre est retire du
     * seul ecran ou cette mutation ne demande aucun fichier neuf, {@code fragments/retour.html}
     * (fin de partie). L'ecran rend toujours sa sortie ({@code .sortie-pleine}) mais ne porte plus
     * le marqueur qui l'annonce : exactement la premiere faute symetrique de la clause C.
     */
    @Test
    void unMarqueurDeSortiePropreRetireDeLaFinDePartieFaitEchouerLaClauseC(@TempDir Path tempDir)
            throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path chemin = tempDir.resolve("templates/fragments/retour.html");
        String contenu = Files.readString(chemin);
        String mute = contenu.replace(" " + MARQUEUR_SORTIE_PROPRE, "");
        assertThat(mute).as("retour.html doit porter le marqueur avant mutation")
                .isNotEqualTo(contenu);
        Files.writeString(chemin, mute);

        assertThatThrownBy(() -> verifierQueLeMarqueurDeSortiePropreNeMentJamais(tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("templates/fragments/retour.html")
                .hasMessageContaining("rend une sortie sans porter");
    }

    /**
     * ADR-0013 obligation 3, clause D (s15) : la meme mutation prive la fin de partie de son seul
     * masquage de la sortie d'en-tete - {@code .lien-accueil} y redevient visible aux cotes de
     * {@code .sortie-pleine}, deux sorties a la fois sur le meme ecran assemble.
     */
    @Test
    void unMarqueurDeSortiePropreRetireDeLaFinDePartieFaitEchouerLaClauseD(@TempDir Path tempDir)
            throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path chemin = tempDir.resolve("templates/fragments/retour.html");
        String contenu = Files.readString(chemin);
        String mute = contenu.replace(" " + MARQUEUR_SORTIE_PROPRE, "");
        assertThat(mute).as("retour.html doit porter le marqueur avant mutation")
                .isNotEqualTo(contenu);
        Files.writeString(chemin, mute);

        assertThatThrownBy(() -> verifierQuAuPlusUneSortieResteVisibleParEcran(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("templates/fragments/retour.html")
                .hasMessageContaining("2 sortie(s) visible(s)");
    }

    /**
     * ADR-0013 obligation 3, clause A : preuve de morsure sur du materiau reel, copie de la
     * production dont le marqueur d'exclusivite est retire des deux seuls ecrans qui le portent -
     * le masquage de la barre de niveaux devient orphelin, son marqueur n'etant plus pose par
     * personne.
     */
    @Test
    void unMasquageDontLeMarqueurNEstPosePasAucunEcranFaitEchouerLaClauseA(@TempDir Path tempDir)
            throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        for (String fragment : List.of("templates/fragments/confirmation.html",
                "templates/fragments/erreur.html")) {
            Path chemin = tempDir.resolve(fragment);
            String contenu = Files.readString(chemin);
            String mute = contenu.replace(" " + MARQUEUR_EXCLUSIF, "");
            assertThat(mute).as("%s doit porter le marqueur avant mutation", fragment)
                    .isNotEqualTo(contenu);
            Files.writeString(chemin, mute);
        }

        assertThatThrownBy(() -> verifierQueLeCoupleDeMasquageEstCoherent(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pose par aucun ecran insere");
    }

    /**
     * ADR-0013 obligation 3, clause B : preuve de morsure sur du materiau reel - une copie de la
     * production dont le masquage nomme {@code .bandeau-niveaux} (le conteneur) au lieu de
     * {@code .chip-niveau} (l'element focalisable). C'est le piege nomme d'avance dans l'avis de
     * faisabilite de s14 (section 2.2, point 2) : un garde-fou qui ne l'attraperait pas laisserait
     * les puces visibles sur un ecran exclusif tout en les retirant de l'ordre de tabulation aux
     * yeux d'{@code AucunArretDeTabulationNEstDestructeurTest}, qui evalue le masquage element par
     * element.
     */
    @Test
    void unMasquageQuiNommeLeConteneurAuLieuDeLaPuceFaitEchouerLaClauseB(@TempDir Path tempDir)
            throws IOException {
        copierArbreReel(RACINE_PRODUCTION, tempDir);
        Path feuille = tempDir.resolve("static/css/recreo.css");
        String contenu = Files.readString(feuille);
        String mute = contenu.replace(
                "body:has(." + MARQUEUR_EXCLUSIF + ") .chip-niveau {",
                "body:has(." + MARQUEUR_EXCLUSIF + ") .bandeau-niveaux {");
        assertThat(mute).as("la regle de masquage doit exister telle quelle avant mutation")
                .isNotEqualTo(contenu);
        Files.writeString(feuille, mute);

        assertThatThrownBy(() -> verifierQuAucunActionnablePermanentNeSurvitSurUnEcranExclusif(tempDir, tempDir))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est masque par aucun masquage declare");
    }

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
     * S14-2 (BARRE-7, DEC-0033), ADR-0022 troisieme amendement, section 7.2 : retiree. <b>Motif
     * ecrit</b>, exige par ADR-0013 : ce test exercait {@code verifierQueLHabillageMasqueDeclareSonMarqueur}
     * sur le fixture {@code fragment-sans-marqueur}, ou un seul des deux ecrans inseres porte le
     * marqueur. Sous la forme d'origine de la clause 2 ("tout ecran insere porte le marqueur"),
     * c'etait un defaut ; sous la forme generalisee (clause A, "au moins un ecran insere porte le
     * marqueur"), ce fixture ne produit plus aucun defaut - etape.html porte bien le marqueur, ce
     * qui suffit desormais. Le fixture reste sur le disque, exercise par aucun test : le retirer
     * n'etait pas demande et n'est pas fait ici.
     */

    /**
     * S14-2 (BARRE-7, DEC-0033), ADR-0022 troisieme amendement, section 7.3 : remplace
     * {@code uneFeuilleSansRegleDeMasquageFaitEchouerLeGardeFou}, qui affirmait l'inverse exact de
     * ce que la clause A etablit desormais. <b>Motif ecrit</b> : l'assertion
     * {@code masquages(...).isNotEmpty()} - "c'est elle, et rien d'autre, qui a produit tout le
     * desordre de ce sprint" (theo-archi) - est retiree de la clause. Une feuille sans aucune
     * regle de masquage n'est plus un defaut : c'est desormais la definition meme d'une population
     * vide, et la clause A doit y rester verte plutot que d'y exiger un mecanisme qu'aucun produit
     * ne demande a cet instant. Meme fixture, assertion inversee.
     */
    @Test
    void uneFeuilleSansAucunMasquagePasseLaClauseA() {
        Path feuilles = CONTRE_EXEMPLES.resolve("feuille-sans-masquage");

        assertThatCode(() -> verifierQueLeCoupleDeMasquageEstCoherent(RACINE_PRODUCTION, feuilles))
                .as("ADR-0022, clause A : aucune assertion n'exige qu'un masquage existe, une "
                        + "feuille qui n'en declare aucun doit donc rester verte")
                .doesNotThrowAnyException();
    }
}
