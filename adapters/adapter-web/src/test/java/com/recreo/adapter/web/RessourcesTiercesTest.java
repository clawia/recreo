package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0014 : une bibliotheque front tierce entre dans ce depot par vendorisation, sous
 * static/vendor/, tracee ligne a ligne dans static/vendor/provenance.txt. Ce garde-fou tient les
 * quatre proprietes dont depend cette decision.
 *
 * <p>Perimetre derive, jamais enumere (ADR-0013) : les ressources tierces viennent du contenu du
 * repertoire static/vendor, les feuilles de style et les gabarits viennent du balayage de
 * l'arborescence de ressources. Aucun nom de bibliotheque n'est ecrit ici comme objet a controler.
 * Les seules listes ecrites a la main sont des listes de fautes reprochees, ce que l'ADR-0013
 * autorise : leur mode de defaillance est un rouge, pas un vert silencieux.
 *
 * <p>Ce garde-fou ne juge jamais le contenu d'une feuille tierce a l'aune de nos choix de style.
 * Il ne lui reproche que ce qui vaut pour tout octet servi par l'application, quelle qu'en soit
 * l'origine : ne rien aller chercher sur le reseau a l'execution.
 */
class RessourcesTiercesTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/RessourcesTiercesTest");

    private static final String STATIQUE = "static";
    private static final String TIERS = "vendor";
    private static final String PROVENANCE = "provenance.txt";
    private static final String SEPARATEUR = "|";

    /**
     * Critere enumere, jamais un perimetre (ADR-0013) : le JavaScript de ces bibliotheques ne doit
     * pas entrer, parce que le comportement de jeu vit sur le serveur (ADR-0008). Un nom absent de
     * cette liste n'echappe a rien : il devra de toute facon etre vendorise et trace, donc apparaitre
     * en diff.
     */
    private static final List<String> JAVASCRIPT_TIERS_REFUSE = List.of("bootstrap");

    /** Ce qu'un gabarit ecrit quand il demande une ressource par une expression Thymeleaf. */
    private static final List<String> ATTRIBUTS_THYMELEAF = List.of("th:src=", "th:href=");

    private record Ressource(String chemin, String origine, String cheminSource, String empreinte) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static Path racineStatique(Path racine) {
        return racine.resolve(STATIQUE);
    }

    private static Path racineTierce(Path racine) {
        return racineStatique(racine).resolve(TIERS);
    }

    private static List<Path> fichiers(Path repertoire) {
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile).sorted().toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static boolean estTierce(Path racine, Path fichier) {
        return fichier.toAbsolutePath().normalize()
                .startsWith(racineTierce(racine).toAbsolutePath().normalize());
    }

    /**
     * Les ressources tierces reellement presentes : le contenu du repertoire, pas ce que le fichier
     * de provenance pretend. Le fichier de provenance se retire lui-meme par son propre nom, comme
     * le recensement d'ADR-0013 se retire par son propre nom de classe : il decrit les ressources,
     * il n'en est pas une.
     */
    private static List<Path> ressourcesTierces(Path racine) {
        return fichiers(racineTierce(racine)).stream()
                .filter(fichier -> !fichier.getFileName().toString().equals(PROVENANCE))
                .toList();
    }

    private static Map<String, Ressource> provenanceDeclaree(Path racine) {
        Path fichier = racineTierce(racine).resolve(PROVENANCE);
        Map<String, Ressource> declarees = new LinkedHashMap<>();
        if (!Files.isRegularFile(fichier)) {
            return declarees;
        }
        for (String ligne : lire(fichier).lines().toList()) {
            String contenu = ligne.strip();
            if (contenu.isEmpty() || contenu.startsWith("#")) {
                continue;
            }
            List<String> champs = new ArrayList<>();
            int position = 0;
            while (position <= contenu.length()) {
                int suivant = contenu.indexOf(SEPARATEUR, position);
                if (suivant < 0) {
                    champs.add(contenu.substring(position).strip());
                    break;
                }
                champs.add(contenu.substring(position, suivant).strip());
                position = suivant + SEPARATEUR.length();
            }
            assertThat(champs)
                    .as("%s : une ligne de provenance a quatre champs separes par '%s', lue ici : %s",
                            fichier, SEPARATEUR, contenu)
                    .hasSize(4);
            declarees.put(champs.get(0), new Ressource(champs.get(0), champs.get(1), champs.get(2),
                    champs.get(3)));
        }
        return declarees;
    }

    private static String empreinte(Path fichier) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(sha256.digest(Files.readAllBytes(fichier)));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        } catch (NoSuchAlgorithmException impossible) {
            throw new IllegalStateException("SHA-256 est exige de toute plateforme Java", impossible);
        }
    }

    private static String cheminSousStatique(Path racine, Path fichier) {
        return racineStatique(racine).toAbsolutePath().normalize()
                .relativize(fichier.toAbsolutePath().normalize())
                .toString().replace('\\', '/');
    }

    /**
     * Derivation totale dans les deux sens (ADR-0013 obligation 2) : chaque fichier present produit
     * une assertion, et chaque ligne declaree doit designer un fichier present. Une ressource ajoutee
     * sans etre tracee echoue ; une ligne laissee derriere un fichier supprime echoue aussi.
     */
    static void verifierProvenanceDesRessourcesTierces(Path racine) {
        Map<String, Ressource> declarees = provenanceDeclaree(racine);
        List<String> presentes = new ArrayList<>();

        for (Path fichier : ressourcesTierces(racine)) {
            String chemin = cheminSousStatique(racine, fichier);
            presentes.add(chemin);
            Ressource declaree = declarees.get(chemin);
            assertThat(declaree)
                    .as("ADR-0014 : %s est servi sans etre trace dans %s/%s ; une ressource tierce "
                            + "non tracee n'a ni version connue ni moyen d'etre refabriquee",
                            chemin, TIERS, PROVENANCE)
                    .isNotNull();
            assertThat(declaree.origine())
                    .as("ADR-0014 : %s doit nommer l'origine qui permet de refabriquer le fichier", chemin)
                    .isNotBlank();
            assertThat(empreinte(fichier))
                    .as("ADR-0014 : %s ne correspond plus a l'empreinte declaree ; une copie octet "
                            + "pour octet ne se retouche pas, elle se remplace et se retrace", chemin)
                    .isEqualTo(declaree.empreinte());
        }

        assertThat(declarees.keySet())
                .as("ADR-0014 : une ligne de provenance qui ne designe aucun fichier present est une "
                        + "trace pourrie, elle donne l'illusion d'une tracabilite qui n'existe plus")
                .isSubsetOf(presentes);
    }

    /** La cible d'un url() ou d'un @import, telle qu'elle est ecrite dans la feuille. */
    private static List<String> ciblesDeFeuille(String source) {
        List<String> cibles = new ArrayList<>();
        int position = 0;
        while ((position = source.indexOf("url(", position)) >= 0) {
            int debut = position + "url(".length();
            while (debut < source.length() && Character.isWhitespace(source.charAt(debut))) {
                debut++;
            }
            int fin;
            if (debut < source.length() && (source.charAt(debut) == '"' || source.charAt(debut) == '\'')) {
                char guillemet = source.charAt(debut);
                debut++;
                fin = source.indexOf(guillemet, debut);
            } else {
                fin = source.indexOf(')', debut);
            }
            if (fin < 0) {
                fin = source.length();
            }
            cibles.add(source.substring(debut, fin).strip());
            position = fin;
        }
        position = 0;
        while ((position = source.indexOf("@import", position)) >= 0) {
            int debut = position + "@import".length();
            while (debut < source.length() && Character.isWhitespace(source.charAt(debut))) {
                debut++;
            }
            if (debut < source.length() && (source.charAt(debut) == '"' || source.charAt(debut) == '\'')) {
                char guillemet = source.charAt(debut);
                int fin = source.indexOf(guillemet, debut + 1);
                cibles.add(source.substring(debut + 1, fin < 0 ? source.length() : fin).strip());
            }
            position = debut;
        }
        return cibles;
    }

    private static void verifierCibleLocale(Path racine, Path depuis, String cible, String origine) {
        assertThat(cible)
                .as("ADR-0014 : %s va chercher %s sur le reseau ; l'application doit fonctionner hors "
                        + "ligne, aucune ressource externe a l'execution", origine, cible)
                .doesNotStartWith("http://")
                .doesNotStartWith("https://")
                .doesNotStartWith("//");

        String chemin = cible.split("[?#]")[0];
        Path vise = chemin.startsWith("/")
                ? racineStatique(racine).resolve(chemin.substring(1))
                : depuis.resolveSibling(chemin);
        assertThat(vise)
                .as("ADR-0014 : %s reference %s, qui n'est servi par aucun fichier de %s ; une "
                        + "ressource manquante est un appel reseau rate a l'execution",
                        origine, cible, racineStatique(racine))
                .isRegularFile();
    }

    /**
     * Ce critere vaut pour toute feuille servie, maison ou tierce : ne rien aller chercher sur le
     * reseau n'est pas un choix de style, c'est une condition de deploiement. Il porte sur ce qui
     * declenche reellement une requete - la cible d'un url() ou d'un @import - et jamais sur la
     * presence du texte "https" quelque part, qu'une entete de licence ou un espace de noms SVG
     * encode en data: contiennent legitimement.
     */
    static void verifierAucuneRessourceExterne(Path racine) {
        for (Path feuille : fichiers(racineStatique(racine))) {
            if (!feuille.getFileName().toString().endsWith(".css")) {
                continue;
            }
            for (String cible : ciblesDeFeuille(lire(feuille))) {
                if (cible.startsWith("data:")) {
                    continue;
                }
                verifierCibleLocale(racine, feuille, cible, cheminSousStatique(racine, feuille));
            }
        }

        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element reference : referencesDeRessource(document)) {
                verifierCibleLocale(racine, gabarit, cheminDeGabarit(gabarit, reference), gabarit.toString());
            }
        }
    }

    /**
     * Les elements d'un gabarit qui font aller chercher un fichier de static/, selectionnes par
     * presence d'attribut et non par un selecteur CSS : mesure sur la version de jsoup qu'utilise ce
     * build (1.23.1), {@code script[th\:src]} ne selectionne <b>aucun</b> element, alors que
     * {@code attr("th:src")} rend bien la valeur. La forme echappee du deux-points n'est pas comprise
     * par l'analyseur de selecteurs ; elle l'etait peut-etre dans une version anterieure, elle ne
     * l'est pas ici. Toute la production ecrivant ses cinq references en Thymeleaf, la branche
     * gabarits de ce garde-fou ne regardait donc rien du tout avant ADR-0036 - un vert silencieux, que
     * son propre jeu fautif ne pouvait pas reveler puisqu'il etait ecrit en attribut simple.
     *
     * <p>Le critere porte sur l'attribut et non sur une liste de balises. Un {@code src} designe
     * toujours une ressource que le navigateur va chercher, sur n'importe quel element - image,
     * script, cadre, son, video ; un {@code href} n'en designe une que sur {@code link}, puisque sur
     * {@code a} il designe une route de l'application, qui n'est pas un fichier de static/. Une liste
     * de balises aurait echoue par omission, et elle l'avait deja fait : elle nommait {@code link} et
     * {@code script}, et laissait dehors l'unique {@code img} de la production, l'icone d'en-tete
     * servie depuis static/ depuis S6-1.
     */
    private static List<Element> referencesDeRessource(Document document) {
        List<Element> references = new ArrayList<>();
        for (Element element : document.getAllElements()) {
            boolean ressource = element.hasAttr("src") || element.hasAttr("th:src")
                    || ("link".equals(element.normalName())
                        && (element.hasAttr("href") || element.hasAttr("th:href")));
            if (ressource) {
                references.add(element);
            }
        }
        return references;
    }

    /**
     * Le chemin qu'un gabarit demande au navigateur. Thymeleaf l'ecrit dans th:href ou th:src, sous
     * la forme @{/chemin}. Une expression calculee est refusee plutot qu'ignoree : on ne peut pas
     * prouver hors ligne ce qu'on ne sait pas lire (ADR-0013 obligation 2).
     *
     * <p>ADR-0036 : depuis que le perimetre couvre {@code src}, ce refus porte aussi la condition C5
     * de nadia-prof - le jeu d'images du cabinet ne depend ni de l'item tire, ni du numero de la
     * colonne, ni de la reponse attendue. Tant que les formes du meuble etaient dessinees par des
     * regles statiques, cette propriete etait tenue par construction. Une image designee par un
     * attribut de gabarit, elle, peut varier : c'est exactement ce qu'un chemin calcule ferait, et
     * c'est ici que cela devient rouge.
     */
    private static String cheminDeGabarit(Path gabarit, Element reference) {
        String brut = reference.hasAttr("th:href") ? reference.attr("th:href")
                : reference.hasAttr("th:src") ? reference.attr("th:src")
                : reference.hasAttr("href") ? reference.attr("href")
                : reference.attr("src");
        String valeur = brut.strip();
        if (valeur.startsWith("@{") && valeur.endsWith("}")) {
            valeur = valeur.substring(2, valeur.length() - 1).strip();
        }
        assertThat(valeur)
                .as("ADR-0014 : %s reference une ressource par une expression calculee (%s) ; une "
                        + "ressource de page s'ecrit en chemin litteral, sinon rien ne prouve "
                        + "qu'elle est servie hors ligne", gabarit, brut)
                .doesNotContain("$", "+", "'");
        return valeur;
    }

    private static List<Path> gabarits(Path racine) {
        return fichiers(racine.resolve("templates")).stream()
                .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                .toList();
    }

    /**
     * La feuille maison est toujours chargee en dernier. C'est la seule chose qui fasse qu'une regle
     * maison de meme specificite l'emporte sur la regle tierce : sans cet ordre, les planchers de
     * lisibilite et de cible tactile exiges par nadia-prof seraient declares mais pas rendus.
     */
    static void verifierFeuilleMaisonChargeeEnDernier(Path racine) {
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            List<Element> feuilles = document.select("link[rel=stylesheet]");
            if (feuilles.isEmpty()) {
                continue;
            }
            String derniere = cheminDeGabarit(gabarit, feuilles.get(feuilles.size() - 1));
            assertThat(derniere)
                    .as("ADR-0014 : dans %s, la derniere feuille chargee est %s, une feuille tierce ; "
                            + "la feuille maison doit passer apres, sinon la regle tierce l'emporte "
                            + "sur la regle maison a specificite egale", gabarit, derniere)
                    .doesNotStartWith("/" + TIERS + "/");
        }
    }

    /**
     * ADR-0008 : le comportement de jeu vit sur le serveur. Le JavaScript de composants d'une
     * bibliotheque d'interface - modales, menus, replis - n'a rien a faire ici, et la vendorisation
     * fichier par fichier fait que son absence est l'etat par defaut : il faudrait le copier
     * volontairement pour qu'il entre. Ce critere refuse ce geste-la.
     */
    static void verifierAucunJavaScriptTiersRefuse(Path racine) {
        for (Path fichier : ressourcesTierces(racine)) {
            String nom = fichier.getFileName().toString().toLowerCase(java.util.Locale.ROOT);
            if (!nom.endsWith(".js")) {
                continue;
            }
            String origine = provenanceDeclaree(racine)
                    .getOrDefault(cheminSousStatique(racine, fichier),
                            new Ressource("", "", "", ""))
                    .origine().toLowerCase(java.util.Locale.ROOT);
            for (String refuse : JAVASCRIPT_TIERS_REFUSE) {
                assertThat(nom + " " + origine)
                        .as("ADR-0014 et ADR-0008 : le JavaScript de %s n'entre pas dans ce depot ; "
                                + "seule sa feuille de style est vendorisee, le comportement de jeu "
                                + "reste sur le serveur", refuse)
                        .doesNotContain(refuse);
            }
        }
    }

    @Test
    void laProductionSertAuMoinsUneRessourceTierce() {
        assertThat(ressourcesTierces(RACINE_PRODUCTION))
                .as("ADR-0013 : ce garde-fou ne prouve rien si aucune ressource tierce n'existe")
                .isNotEmpty();
    }

    @Test
    void chaqueRessourceTierceEstTraceeEtIntacte() {
        verifierProvenanceDesRessourcesTierces(RACINE_PRODUCTION);
    }

    @Test
    void aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau() {
        verifierAucuneRessourceExterne(RACINE_PRODUCTION);
    }

    /**
     * ADR-0036, clause anti-vacuite. Elle ne se prouve pas sur une fixture mais sur le materiau reel,
     * et son ancre est independante de ce qu'elle verifie : le <b>texte</b> du gabarit dit qu'une
     * reference Thymeleaf y est ecrite, la selection <b>DOM</b> doit donc en trouver au moins une. La
     * clause precedente pouvait etre verte en ne regardant aucun element ; celle-ci rougit dans ce
     * cas-la.
     */
    @Test
    void aucuneReferenceEcriteEnThymeleafNEchappeALaSelection() {
        List<Path> gabaritsAvecThymeleaf = new ArrayList<>();
        for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
            String source = lire(gabarit);
            if (ATTRIBUTS_THYMELEAF.stream().noneMatch(source::contains)) {
                continue;
            }
            gabaritsAvecThymeleaf.add(gabarit);
            assertThat(referencesDeRessource(Jsoup.parse(source, gabarit.toString())))
                    .as("ADR-0036 : %s ecrit une reference de ressource en Thymeleaf, et la selection "
                            + "de ce garde-fou n'en voit aucune ; un selecteur qui ne comprend pas la "
                            + "forme reellement ecrite rend cette regle verte sans rien verifier",
                            gabarit)
                    .isNotEmpty();
        }

        assertThat(gabaritsAvecThymeleaf)
                .as("ADR-0003 : cette clause ne prouve rien si aucun gabarit de production n'ecrit de "
                        + "reference de ressource en Thymeleaf")
                .isNotEmpty();
    }

    @Test
    void laFeuilleMaisonEstChargeeApresLaFeuilleTierce() {
        verifierFeuilleMaisonChargeeEnDernier(RACINE_PRODUCTION);
    }

    @Test
    void aucunJavaScriptDeBibliothequeDInterfaceNEntreDansLeDepot() {
        verifierAucunJavaScriptTiersRefuse(RACINE_PRODUCTION);
    }

    @Test
    void uneRessourceTierceNonTraceeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierProvenanceDesRessourcesTierces(CONTRE_EXEMPLES.resolve("tiers-non-trace")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sans etre trace");
    }

    @Test
    void uneEmpreinteQuiNeCorrespondPlusFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierProvenanceDesRessourcesTierces(CONTRE_EXEMPLES.resolve("empreinte-fausse")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("empreinte declaree");
    }

    @Test
    void uneTraceQuiNeDesigneAucunFichierFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierProvenanceDesRessourcesTierces(CONTRE_EXEMPLES.resolve("trace-pourrie")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("trace pourrie");
    }

    @Test
    void uneFeuilleQuiVaChercherUnePoliceSurLeReseauFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucuneRessourceExterne(CONTRE_EXEMPLES.resolve("police-sur-le-reseau")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sur le reseau");
    }

    @Test
    void unGabaritQuiChargeUneFeuilleDepuisUnCdnFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucuneRessourceExterne(CONTRE_EXEMPLES.resolve("gabarit-sur-cdn")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sur le reseau");
    }

    @Test
    void uneImageChargeeDepuisUnCdnFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucuneRessourceExterne(CONTRE_EXEMPLES.resolve("image-sur-cdn")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sur le reseau");
    }

    @Test
    void uneImageQueAucunFichierNeSertFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucuneRessourceExterne(CONTRE_EXEMPLES.resolve("image-absente")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est servi par aucun fichier");
    }

    /**
     * ADR-0036 : ce jeu fautif est le seul a mettre a l'epreuve le refus des chemins calcules. Le
     * critere existait depuis ADR-0014 et n'avait jamais rencontre de contre-exemple ; il porte
     * desormais la condition C5, qui cesse d'etre tenue par construction des qu'une image entre.
     */
    @Test
    void uneImageDesigneeParUnCheminCalculeFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucuneRessourceExterne(CONTRE_EXEMPLES.resolve("image-calculee")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("expression calculee");
    }

    @Test
    void unGabaritQuiChargeLaFeuilleMaisonAvantLaFeuilleTierceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierFeuilleMaisonChargeeEnDernier(CONTRE_EXEMPLES.resolve("maison-avant-tiers")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("doit passer apres");
    }

    @Test
    void leJavaScriptDUneBibliothequeDInterfaceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() ->
                verifierAucunJavaScriptTiersRefuse(CONTRE_EXEMPLES.resolve("javascript-de-composants")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'entre pas dans ce depot");
    }
}
