package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrontieresDuJavaScriptTest {

    private static final Path STATIQUE = Path.of("src/main/resources/static");
    private static final Path TIERS = STATIQUE.resolve("vendor");
    private static final Path GABARITS = Path.of("src/main/resources/templates");
    private static final long PLAFOND_OCTETS = 4096;
    private static final int PLAFOND_LIGNES = 100;

    /**
     * Amendement ADR-0026 : {@code proposition} et {@code colonne} rejoignent la liste. ADR-0008
     * interdisait le vocabulaire du <b>verdict</b> ; il ne disait rien du vocabulaire du
     * <b>tirage</b>, et un rouleau ecrit en JavaScript n'a besoin d'aucun des onze mots precedents -
     * il lui suffit de relire {@code .propositions} et de decider laquelle arrive dans quelle
     * colonne. Ces deux mots-la nomment exactement ce que le serveur doit avoir decide avant que le
     * fragment ne le quitte, et ils sont absents de {@code recreo.js} au jour de cet amendement.
     *
     * <p><b>Amendement ADR-0080, garde-fou 2 (T4, lot L-a) : sept mots rejoignent la liste.</b>
     * {@code zone}, {@code clientX}, {@code clientY}, {@code offsetX}, {@code offsetY},
     * {@code getBoundingClientRect}, {@code elementFromPoint} nomment ce que le serveur doit avoir
     * decide avant que le fragment ne le quitte sur la mecanique de designation : la resolution d'un
     * point en zone. Une designation n'envoie jamais une coordonnee, elle envoie l'identifiant du
     * bouton presse (ADR-0080, decision 4) ; un script qui lirait une position de clic ou de toucher
     * pour la comparer a un cadre reprendrait cote client une decision que le serveur seul doit
     * rendre. Verifies sur le materiau reel et non sur une fixture, comme le reste de cette liste :
     * aucun des sept ne figure dans {@code recreo.js} au jour de cet amendement.
     *
     * <p><b>ADR-0105, regle 3 : trois mots rejoignent la liste.</b> {@code cookie}, {@code indexedDB}
     * et {@code caches.} nomment les trois stockages du navigateur qui survivent a la session HTTP et
     * que {@code localStorage} ne couvrait pas. Un choix de vue de l'accueil retenu cote client par
     * l'un d'eux survivrait a la session que DEC-0028 laisse seule porter l'etat ; c'est le chemin le
     * moins cher d'une bascule ecrite en script. Absents de {@code recreo.js} au jour de cet
     * amendement, verifie par recherche insensible a la casse sur {@code static/js/}.</p>
     */
    private static final List<String> INTERDITS_JS = List.of(
            "fetch(", "XMLHttpRequest", "JSON.parse", "JSON.stringify",
            "localStorage", "sessionStorage", "eval(",
            "cookie", "indexedDB", "caches.",
            "score", "correct", "bonneReponse", "reponse",
            "proposition", "colonne",
            "zone", "clientX", "clientY", "offsetX", "offsetY",
            "getBoundingClientRect", "elementFromPoint");

    @TempDir
    private Path tempo;

    private static final List<String> MARQUEURS_DE_CORRECTION = List.of(
            "data-correct", "data-bonne", "data-solution", "data-reponse");

    private static Stream<Path> fichiers(Path racine, String suffixe) throws IOException {
        return Files.walk(racine).filter(Files::isRegularFile)
                .filter(fichier -> fichier.getFileName().toString().endsWith(suffixe));
    }

    /**
     * ADR-0014 : ce qui distingue le JavaScript maison du JavaScript tiers n'est plus un prefixe de
     * nom de fichier ecrit ici (htmx), c'est l'emplacement - static/vendor/ est le seul endroit ou
     * une copie tierce a le droit de vivre, et provenance.txt dit ce qu'elle est. Le budget d'ADR-0008
     * n'a jamais eu de sens pour une bibliotheque tierce ; il en a pour tout le reste, y compris pour
     * un fichier maison depose ailleurs que sous js/.
     */
    private static List<Path> javaScriptMaison() throws IOException {
        return javaScriptMaison(STATIQUE);
    }

    /**
     * Le meme perimetre, sur une racine statique quelconque : c'est ce qui permet a une morsure de
     * juger une copie mutee du materiau reel au lieu d'une fixture. Le repertoire tiers est derive de
     * la racine passee, jamais de la constante de production.
     */
    private static List<Path> javaScriptMaison(Path racineStatique) throws IOException {
        Path tiers = racineStatique.resolve(TIERS.getFileName().toString());
        return fichiers(racineStatique, ".js")
                .filter(fichier -> !fichier.startsWith(tiers))
                .toList();
    }

    @Test
    void leJavaScriptDAgrementTientDansSonBudget() throws IOException {
        List<Path> fichiers = javaScriptMaison();
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            assertThat(Files.size(fichier))
                    .as("ADR-0008 : %s depasse le budget de %d octets", fichier, PLAFOND_OCTETS)
                    .isLessThanOrEqualTo(PLAFOND_OCTETS);
            assertThat(Files.readAllLines(fichier))
                    .as("ADR-0008 : %s depasse %d lignes", fichier, PLAFOND_LIGNES)
                    .hasSizeLessThanOrEqualTo(PLAFOND_LIGNES);
        }
    }

    @Test
    void leJavaScriptNePrendAucuneDecisionDeJeu() throws IOException {
        List<Path> fichiers = javaScriptMaison();
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            String source = Files.readString(fichier);
            assertThat(INTERDITS_JS.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s contient du vocabulaire de decision de jeu", fichier)
                    .isEmpty();
        }
    }

    /**
     * S18, S17-C : troisieme critere d'acceptation de la story (backlog), reformule par
     * theo-archi comme une propriete textuelle plutot que comportementale (avis-faisabilite.md,
     * section 4.3, motif 4) - le mecanisme retenu deplace la declaration de {@code tabindex} vers
     * les gabarits, et {@code recreo.js} ne doit plus jamais la poser lui-meme, sur aucune cible,
     * qu'elle soit ou non nativement focalisable.
     */
    /**
     * ADR-0057, clause 1 : le script d'agrement ne rend jamais un element focalisable. Le mecanisme
     * de S17-C deplace la declaration de {@code tabindex} vers les gabarits ; ce que cette clause
     * garde est la moitie negative de la propriete - le script ne la reprend pas.
     *
     * <p><b>Pourquoi la comparaison ignore la casse, et ce qu'il a fallu pour le voir.</b> Cette
     * clause est arrivee dans le lot de {@code marc-dev} sous la forme
     * {@code doesNotContain("tabindex")}, sensible a la casse. Mesure du 2026-08-23 sur l'arbre
     * fusionne : {@code pointDeFocus.tabIndex = -1;} - la propriete DOM, qui est la facon la
     * <b>plus</b> idiomatique d'ecrire ce geste - restaure integralement le defaut que
     * {@code nadia-prof} avait mesure (le bouton nomme a la revelation sort de l'ordre de
     * tabulation) et laisse les 618 tests du reacteur verts. Un garde-fou que l'orthographe usuelle
     * de la faute contourne ne garde rien. La comparaison couvre desormais l'attribut
     * ({@code setAttribute}, {@code removeAttribute}) et la propriete ({@code tabIndex}) d'un seul
     * predicat, parce que les deux s'ecrivent avec les memes lettres.
     */
    @Test
    void leScriptDeFocusNeRendJamaisUnElementFocalisable() throws IOException {
        verifierQueLeScriptNeMuteAucuneFocalisabilite(STATIQUE);
    }

    /**
     * ADR-0013 obligation 3 pour cette clause, sur le materiau reel plutot que sur une fixture :
     * {@code recreo.js} tel qu'il est servi, mute du geste exact que la mesure a trouve invisible.
     * Une fixture ecrite de ma main aurait porte l'orthographe que j'imagine - c'est precisement
     * l'orthographe imaginee qui a laisse passer la version precedente de cette clause.
     *
     * <p>Note de gouvernance : {@code FrontieresDuJavaScriptTest} figure dans
     * {@code DETTE_DE_BALAYAGE} de {@code LesGardeFousSontMisALEpreuveTest}, donc l'obligation de jeu
     * fautif ne lui est pas opposee. <b>Le plafond de la dette n'est pas baisse pour autant</b> : ses
     * trois autres clauses de balayage restent sans morsure, et sortir le fichier de la dette parce
     * qu'un repertoire existe reviendrait a satisfaire le proxy que ce depot a deja critique
     * (ADR-0054, section sur le jeu fautif commite). Cette morsure-ci mute donc une copie.
     */
    @Test
    void uneFocalisabiliteRendueParLeScriptFaitEchouerLeGardeFou() throws IOException {
        Path racine = copieDuStatique(tempo.resolve("focalisabilite-rendue-par-le-script"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("js/recreo.js"),
                "        pointDeFocus.focus();",
                "        pointDeFocus.tabIndex = -1;\n        pointDeFocus.focus();");

        assertThatThrownBy(() -> verifierQueLeScriptNeMuteAucuneFocalisabilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tabIndex");
    }

    static void verifierQueLeScriptNeMuteAucuneFocalisabilite(Path racineStatique)
            throws IOException {
        List<Path> fichiers = javaScriptMaison(racineStatique);
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            String source = Files.readString(fichier);
            assertThat(source)
                    .as("ADR-0057 : %s ne doit rendre aucun element focalisable. Depuis S17-C, "
                            + "tabindex est declare par le gabarit qui rend la cible ; un script qui "
                            + "le pose, le retire, ou ecrit la propriete tabIndex reprend au serveur "
                            + "une decision que la CI ne peut plus verifier sur le document servi",
                            fichier)
                    .doesNotContainIgnoringCase("tabindex");
        }
    }



    @Test
    void aucunGabaritNeMarqueLaBonneReponse() throws IOException {
        List<Path> gabarits = fichiers(GABARITS, ".html").toList();
        assertThat(gabarits)
                .as("ADR-0003 : cette regle ne prouve rien si aucun gabarit n'est passe au crible")
                .isNotEmpty();

        for (Path gabarit : gabarits) {
            String source = Files.readString(gabarit);
            assertThat(MARQUEURS_DE_CORRECTION.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s livre la bonne reponse au client", gabarit)
                    .isEmpty();
        }
    }

    /**
     * <b>ADR-0074, clause 1 : tout JavaScript servi a l'enfant vit dans un fichier sous static/,
     * jamais dans un document.</b>
     *
     * <p>Ce que le budget d'ADR-0008 et sa liste de vocabulaire gardent est un <b>fichier</b> : ils
     * comptent des octets, des lignes et des sous-chaines dans {@code static/**.js}. Un gabarit
     * n'entrait dans leur perimetre que par une seule clause, celle des marqueurs de correction.
     * Mesure du 2026-09-01, worktree s26-theo sur round-two a 153df5a, JDK 21.0.10 rapporte par la
     * machine virtuelle des tests, {@code bash outils/mvn-jdk21.sh -B -o clean test} sur la racine :
     * un element {@code <script>} de dix lignes pose dans {@code fragments/manche.html}, qui lit
     * {@code sessionStorage}, tient un {@code score} et decide du verdict au clic, laisse le
     * reacteur complet a <b>1 012 tests et un seul echec, exactement le meme qu'avant la
     * mutation</b> (ADR-0066, anterieur a ce lot et sans rapport avec lui). Deux mots de
     * {@code INTERDITS_JS} sont alors dans un fichier servi a l'enfant et rien ne rougit : la liste
     * existe, son perimetre ne la porte pas jusque-la. C'est la porte de la dette n. 4 de
     * CLAUDE.md.</p>
     *
     * <p><b>Le remede n'est pas d'etendre la liste de vocabulaire aux gabarits</b> : une liste de
     * mots attrape ce qu'elle nomme, et un bloc en ligne peut tenir un etat de jeu sans ecrire un
     * seul des treize mots. Ce qui est ferme ici est le <b>porteur</b> : un gabarit ne porte de
     * JavaScript que <b>par reference</b> - un element {@code <script>} qui designe un fichier,
     * lequel retombe alors sous le budget et sous la liste. Tout autre porteur est refuse sans
     * budget et sans liste, donc sans rien a tenir a jour.</p>
     *
     * <p><b>Les trois porteurs sont derives de ce que le navigateur execute, jamais d'une liste
     * d'incidents</b> : le texte d'un {@code <script>} sans reference ; un attribut qui lie du
     * JavaScript a un evenement ; une URL de schema {@code javascript:}. Le second est le seul des
     * trois qui demande un critere de nom, et ce critere est une <b>propriete</b> et non une
     * enumeration - un nom local qui commence par {@code on} suivi de lettres, ou qui commence par
     * {@code hx-on}. La seconde moitie n'est pas une precaution : {@code hx-on} est implemente par
     * la copie vendorisee (htmx 2.0.10, {@code vendor/provenance.txt}), donc un auteur peut ecrire
     * aujourd'hui {@code hx-on:click} et loger une decision de jeu dans le document. Le prefixe de
     * gabarit est retire avant le test, sans quoi {@code th:onclick} passerait.</p>
     */
    @Test
    void aucunGabaritNePorteDeJavaScriptAutrementQueParReference() throws IOException {
        verifierQuAucunGabaritNePorteDeJavaScript(GABARITS);
    }

    /**
     * ADR-0013 obligation 3 : les trois morsures mutent le <b>gabarit reel</b>, jamais une fixture
     * ecrite de ma main. Le geste mute ici est celui que la mesure du 2026-09-01 a trouve invisible,
     * recopie tel quel.
     */
    @Test
    void unBlocDeScriptEnLigneDansUnGabaritFaitEchouerLeGardeFou() throws IOException {
        Path racine = copieDesGabarits(tempo.resolve("bloc-de-script-en-ligne"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("fragments/manche.html"),
                "</body>",
                "<script>\n"
                        + "  var score = Number(sessionStorage.getItem('score') || 0);\n"
                        + "</script>\n</body>");

        assertThatThrownBy(() -> verifierQuAucunGabaritNePorteDeJavaScript(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("script")
                .hasMessageContaining("manche.html");
    }

    /**
     * ADR-0074 : les trois ecritures d'un meme mecanisme, et non trois incidents nommes -
     * l'attribut HTML, celui que Thymeleaf traite, celui que la copie vendorisee de htmx traite.
     * {@code onclick} est le rejeu exact de la mutation M1 de s24, mesuree a 0 rouge sur 1 018
     * tests par yanis-qa et rejouee ici.
     */
    @Test
    void unAttributGestionnaireDEvenementDansUnGabaritFaitEchouerLeGardeFou() throws IOException {
        for (String attribut : List.of("onclick", "th:onclick", "hx-on:click")) {
            Path racine =
                    copieDesGabarits(tempo.resolve("gestionnaire-" + attribut.replace(':', '-')));
            LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                    racine.resolve("fragments/manche.html"),
                    "<button type=\"submit\"",
                    "<button " + attribut + "=\"score = score + 1\" type=\"submit\"");

            assertThatThrownBy(() -> verifierQuAucunGabaritNePorteDeJavaScript(racine))
                    .as("ADR-0074 : %s lie du JavaScript a un evenement dans le document servi",
                            attribut)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining(attribut);
        }
    }

    @Test
    void uneUrlDeSchemaJavascriptDansUnGabaritFaitEchouerLeGardeFou() throws IOException {
        Path racine = copieDesGabarits(tempo.resolve("url-de-schema-javascript"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("accueil.html"),
                "<script defer th:src=\"@{/js/recreo.js}\"></script>",
                "<a href=\"javascript:sessionStorage.clear()\">tricher</a>"
                        + "<script defer th:src=\"@{/js/recreo.js}\"></script>");

        assertThatThrownBy(() -> verifierQuAucunGabaritNePorteDeJavaScript(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("javascript:");
    }

    /**
     * <b>yanis-qa, s26, obstacle qualite - un quatrieme porteur, distinct des trois qu'ADR-0074
     * nomme.</b> Le WHATWG URL Standard retire toute tabulation et tout retour a la ligne ASCII
     * <b>partout dans l'URL</b>, pas seulement en tete, avant d'en analyser le schema (etape de
     * suppression des tabulations et retours a la ligne du parseur d'URL) : un navigateur normalise
     * {@code "ja\tvascript:alert(1)"} en {@code "javascript:alert(1)"} et l'execute au clic. La
     * clause 3 d'ADR-0074 ne fait que
     * {@code attribut.getValue().stripLeading().toLowerCase(Locale.ROOT).startsWith("javascript:")}
     * - {@code stripLeading()} ne retire que l'espace en tete, jamais un caractere de controle logé
     * au milieu du schema, donc cette ecriture-la reste invisible.
     *
     * <p><b>Rouge a ce commit</b> : mesure sur le meme materiau que
     * {@code uneUrlDeSchemaJavascriptDansUnGabaritFaitEchouerLeGardeFou} ci-dessus, seul le
     * caractere de tabulation change. {@code verifierQuAucunGabaritNePorteDeJavaScript} ne leve
     * aucune {@code AssertionError} sur ce gabarit mute, donc {@code assertThatThrownBy} echoue -
     * le porteur passe au travers du garde-fou. Route a sofia-sm / theo-archi : le remede (retirer
     * les caracteres de controle avant de comparer, pas seulement en tete) est un choix de forme de
     * la clause 3, pas a moi de trancher.</p>
     */
    @Test
    void uneTabulationALInterieurDuSchemaJavascriptFaitEchouerLeGardeFou() throws IOException {
        Path racine = copieDesGabarits(tempo.resolve("tabulation-dans-le-schema-javascript"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("accueil.html"),
                "<script defer th:src=\"@{/js/recreo.js}\"></script>",
                "<a href=\"ja\tvascript:sessionStorage.clear()\">tricher</a>"
                        + "<script defer th:src=\"@{/js/recreo.js}\"></script>");

        assertThatThrownBy(() -> verifierQuAucunGabaritNePorteDeJavaScript(racine))
                .as("un navigateur retire les tabulations de l'URL avant d'en lire le schema"
                        + " (WHATWG URL Standard) et executerait ce lien comme javascript:"
                        + " sessionStorage.clear() ; ADR-0074 clause 3 le voit depuis que sa"
                        + " normalisation est celle du navigateur dont elle se reclame")
                .isInstanceOf(AssertionError.class);
    }

    /**
     * <b>ADR-0074 clause 3, amendement du 2026-09-01 : ce que le navigateur retire d'une URL avant
     * d'en lire le schema.</b>
     *
     * <p>La clause se reclamait de ce que le navigateur execute, et son encodage suivait une
     * normalisation plus faible que la sienne : {@code stripLeading()} ne retire que l'espace en
     * tete. <b>Le defaut n'etait pas dans la derivation des trois porteurs, il etait dans
     * l'implementation de l'un d'eux</b> - trouve par {@code yanis-qa}, encode rouge a
     * {@code 77e2385}.</p>
     *
     * <p>Les deux etapes ci-dessous sont celles du parseur d'URL du WHATWG URL Standard, dans son
     * ordre, et non une liste de caracteres relevee sur un incident :</p>
     * <ol>
     *   <li><b>suppression de toute tabulation et de tout retour a la ligne ASCII</b> (U+0009,
     *       U+000A, U+000D) <b>partout dans l'entree</b>, pas seulement en tete ;</li>
     *   <li><b>retrait des controles C0 et de l'espace</b> en tete et en queue - soit tout ce qui
     *       vaut U+0020 ou moins, ce que {@code stripLeading()} ne fait pas : il ignore U+0000 a
     *       U+0008 et U+000E a U+001F.</li>
     * </ol>
     *
     * <p>Le schema est ensuite compare <b>sans egard a la casse</b>, comme le standard le prescrit.
     * <b>Ce qui n'est deliberement pas fait, et c'est mesure</b> : aucun decodage de pourcentage.
     * Un navigateur ne pourcent-decode pas un schema, donc {@code %6Aavascript:} n'est pas une URL
     * javascript - le traiter comme telle serait un faux rouge, et le cas est epingle vert par
     * {@link #uneUrlPourcentEncodeeNEstPasUneUrlJavascript}.</p>
     *
     * <p><b>Le decodage des entites HTML n'a pas a etre fait ici</b> : l'analyseur le fait deja.
     * Mesure du 2026-09-01 sur jsoup - {@code ja&#9;vascript:} rend la valeur {@code ja<TAB>vascript:},
     * {@code java&NewLine;script:} rend un U+000A, {@code java&#13;script:} un U+000D. Les quatre
     * ecritures arrivent donc ici sous leur forme decodee et sont couvertes par l'etape 1.</p>
     */
    private static String urlNormaliseeCommeUnNavigateur(String valeur) {
        StringBuilder sansTabulationNiRetour = new StringBuilder(valeur.length());
        for (char signe : valeur.toCharArray()) {
            if (signe != '\t' && signe != '\n' && signe != '\r') {
                sansTabulationNiRetour.append(signe);
            }
        }
        int debut = 0;
        int fin = sansTabulationNiRetour.length();
        while (debut < fin && sansTabulationNiRetour.charAt(debut) <= ' ') {
            debut++;
        }
        while (fin > debut && sansTabulationNiRetour.charAt(fin - 1) <= ' ') {
            fin--;
        }
        return sansTabulationNiRetour.substring(debut, fin).toLowerCase(Locale.ROOT);
    }

    /**
     * ADR-0074 : le prefixe de gabarit est retire avant le test du nom. Les deux ecritures que
     * Thymeleaf accepte sont traitees - celle des documents qui declarent l'espace de noms, celle
     * des documents qui ne le peuvent pas.
     */
    private static String nomLocal(String nomDAttribut) {
        String nom = nomDAttribut.toLowerCase(Locale.ROOT);
        if (nom.startsWith("th:")) {
            return nom.substring("th:".length());
        }
        if (nom.startsWith("data-th-")) {
            return nom.substring("data-th-".length());
        }
        return nom;
    }

    /**
     * ADR-0074 : ce qui fait qu'un attribut lie du JavaScript a un evenement. Propriete du nom,
     * jamais liste de noms - une enumeration des gestionnaires HTML echouerait par omission au
     * premier evenement qu'elle n'aurait pas prevu.
     */
    private static boolean lieDuJavaScriptAUnEvenement(String nomLocal) {
        if (nomLocal.startsWith("hx-on")) {
            return true;
        }
        if (!nomLocal.startsWith("on") || nomLocal.length() <= 2) {
            return false;
        }
        return nomLocal.chars().skip(2).allMatch(lettre -> lettre >= 'a' && lettre <= 'z');
    }

    static void verifierQuAucunGabaritNePorteDeJavaScript(Path racineDesGabarits)
            throws IOException {
        List<Path> gabarits = fichiers(racineDesGabarits, ".html").toList();
        assertThat(gabarits)
                .as("ADR-0003 : cette regle ne prouve rien si aucun gabarit n'est passe au crible")
                .isNotEmpty();

        int elementsExamines = 0;
        int attributsExamines = 0;
        List<String> porteurs = new ArrayList<>();
        for (Path gabarit : gabarits) {
            Document document = Jsoup.parse(Files.readString(gabarit), "", Parser.htmlParser());
            for (Element element : document.getAllElements()) {
                elementsExamines++;
                if ("script".equals(element.normalName())
                        && !element.data().isBlank()
                        && !element.hasAttr("src") && !element.hasAttr("th:src")) {
                    porteurs.add(gabarit + " : un element <script> porte du JavaScript en ligne");
                }
                for (Attribute attribut : element.attributes()) {
                    attributsExamines++;
                    if (lieDuJavaScriptAUnEvenement(nomLocal(attribut.getKey()))) {
                        porteurs.add(gabarit + " : l'attribut " + attribut.getKey()
                                + " lie du JavaScript a un evenement");
                    }
                    if (urlNormaliseeCommeUnNavigateur(attribut.getValue())
                            .startsWith("javascript:")) {
                        porteurs.add(gabarit + " : l'attribut " + attribut.getKey()
                                + " porte une URL de schema javascript:");
                    }
                }
            }
        }

        assertThat(elementsExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun element - la lecture des gabarits ne"
                        + " rend rien, et une clause verte sur une population vide ne prouve rien")
                .isPositive();
        assertThat(attributsExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun attribut - la moitie de son critere"
                        + " porte sur les noms d'attributs, et elle serait verte sans rien lire")
                .isPositive();
        assertThat(porteurs)
                .as("ADR-0074 : un gabarit ne porte de JavaScript que par reference - un element"
                        + " <script> qui designe un fichier sous static/, lequel retombe alors sous"
                        + " le budget et sous la liste de vocabulaire d'ADR-0008. Le texte d'un"
                        + " <script> en ligne, un attribut gestionnaire d'evenement et une URL de"
                        + " schema javascript: sont trois porteurs que ni le budget ni la liste ne"
                        + " voient - ils comptent des octets et des mots dans static/**.js, et le"
                        + " comportement pose ici n'y est pas. C'est la porte de la dette n. 4 de"
                        + " CLAUDE.md")
                .isEmpty();
    }

    private static Path copieDesGabarits(Path destination) {
        return copieDe(GABARITS, destination);
    }

    private static Path copieDuStatique(Path destination) {
        return copieDe(STATIQUE, destination);
    }

    private static Path copieDe(Path source, Path destination) {
        try {
            try (Stream<Path> arborescence = Files.walk(source)) {
                for (Path fichier : arborescence.toList()) {
                    Path cible = destination.resolve(source.relativize(fichier).toString());
                    if (Files.isDirectory(fichier)) {
                        Files.createDirectories(cible);
                    } else {
                        Files.createDirectories(cible.getParent());
                        Files.copy(fichier, cible, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            return destination;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * <b>ADR-0074 clause 3 : les trois autres ecritures de la meme famille, mutees sur le gabarit
     * reel.</b> {@code yanis-qa} n'en avait mute qu'une - la tabulation nue -, et une famille
     * mesuree sur un seul cas est un incident, pas un perimetre.
     *
     * <p>Les trois passent par une etape distincte de la normalisation : le <b>controle C0 en
     * tete</b>, que {@code stripLeading()} ne retire pas ; la <b>tabulation ecrite en entite</b>,
     * qui prouve que l'analyseur decode avant nous ; le <b>retour a la ligne nomme</b>, meme etape 1
     * que la tabulation. Chacune est verifiee rouge separement, sans quoi une seule d'entre elles
     * pourrait porter les trois.
     */
    @Test
    void lesAutresEcrituresDUnSchemaJavascriptFontEchouerLeGardeFou() throws IOException {
        record Ecriture(String nom, String href) {
        }
        List<Ecriture> ecritures = List.of(
                new Ecriture("controle-c0-en-tete", "&#1;javascript:sessionStorage.clear()"),
                new Ecriture("tabulation-en-entite", "ja&#9;vascript:sessionStorage.clear()"),
                new Ecriture("retour-ligne-nomme", "java&NewLine;script:sessionStorage.clear()"));

        for (Ecriture ecriture : ecritures) {
            Path racine = copieDesGabarits(tempo.resolve("schema-" + ecriture.nom()));
            LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                    racine.resolve("accueil.html"),
                    "<script defer th:src=\"@{/js/recreo.js}\"></script>",
                    "<a href=\"" + ecriture.href() + "\">tricher</a>"
                            + "<script defer th:src=\"@{/js/recreo.js}\"></script>");

            assertThatThrownBy(() -> verifierQuAucunGabaritNePorteDeJavaScript(racine))
                    .as("ADR-0074 clause 3 : %s - un navigateur normalise cette URL en javascript:",
                            ecriture.nom())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("javascript:");
        }
    }

    /**
     * <b>ADR-0074 clause 3, le cote qui doit rester vert : un navigateur ne pourcent-decode pas un
     * schema.</b> {@code %6Aavascript:} n'est pas une URL javascript et ne s'execute pas ; la
     * declarer fuitee serait un faux rouge. <b>Une normalisation qui va plus loin que celle du
     * navigateur est aussi fausse qu'une qui va moins loin</b>, et c'est la borne de la correction.
     */
    @Test
    void uneUrlPourcentEncodeeNEstPasUneUrlJavascript() throws IOException {
        Path racine = copieDesGabarits(tempo.resolve("schema-pourcent-encode"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("accueil.html"),
                "<script defer th:src=\"@{/js/recreo.js}\"></script>",
                "<a href=\"%6Aavascript:sessionStorage.clear()\">lien</a>"
                        + "<script defer th:src=\"@{/js/recreo.js}\"></script>");

        verifierQuAucunGabaritNePorteDeJavaScript(racine);
    }

    /**
     * <b>ADR-0074 clause 2 : la meme hypothese de normalisation faible y est cherchee, et elle n'y
     * est pas.</b> La question est de {@code sofia-sm} : la comparaison de <b>nom d'attribut</b>
     * se laisse-t-elle contourner comme la clause 3 se laissait contourner ?
     *
     * <p><b>Mesure du 2026-09-01, et c'est l'analyseur qui repond</b> : une tabulation dans un nom
     * d'attribut ne produit pas un gestionnaire deforme, elle <b>termine le nom</b>. La source
     * {@code on<TAB>click="x()"} rend <b>deux</b> attributs - {@code on}, vide, et {@code click} -
     * dont aucun n'est un gestionnaire d'evenement, ni pour le navigateur ni pour cette clause. La
     * casse, elle, est repliee par l'analyseur avant nous : {@code ONCLICK} arrive en
     * {@code onclick} et {@code hx-on:CLICK} en {@code hx-on:click}.</p>
     *
     * <p><b>Ce test epingle la mesure plutot que de la laisser en prose</b> : si un analyseur futur
     * cessait de scinder ou de replier la casse, il rougirait ici au lieu d'ouvrir un porteur en
     * silence. C'est la difference entre une clause verifiee et une clause supposee - celle qui a
     * coute le quatrieme porteur.</p>
     */
    @Test
    void unNomDAttributNeSeLaissePasDeformerCommeUneUrl() {
        Document coupe = Jsoup.parse("<a on\tclick=\"x()\">t</a>", "", Parser.htmlParser());
        List<String> noms = new ArrayList<>();
        for (Element element : coupe.getAllElements()) {
            for (Attribute attribut : element.attributes()) {
                noms.add(attribut.getKey());
            }
        }
        assertThat(noms)
                .as("une tabulation termine un nom d'attribut : elle ne fabrique aucun gestionnaire")
                .containsExactly("on", "click");
        assertThat(noms.stream().map(FrontieresDuJavaScriptTest::nomLocal)
                .filter(FrontieresDuJavaScriptTest::lieDuJavaScriptAUnEvenement).toList())
                .as("et aucun des deux fragments n'est un gestionnaire d'evenement")
                .isEmpty();

        Document casse = Jsoup.parse("<a ONCLICK=\"x()\" hx-on:CLICK=\"y()\">t</a>", "",
                Parser.htmlParser());
        List<String> gestionnaires = new ArrayList<>();
        for (Element element : casse.getAllElements()) {
            for (Attribute attribut : element.attributes()) {
                if (lieDuJavaScriptAUnEvenement(nomLocal(attribut.getKey()))) {
                    gestionnaires.add(attribut.getKey());
                }
            }
        }
        assertThat(gestionnaires)
                .as("la casse d'un nom d'attribut est repliee par l'analyseur avant cette clause")
                .containsExactlyInAnyOrder("onclick", "hx-on:click");
    }
}
