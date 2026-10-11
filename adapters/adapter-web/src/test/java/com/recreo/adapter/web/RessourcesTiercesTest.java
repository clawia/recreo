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
 *
 * <p><b>ADR-0039, correction d'une affirmation fausse de cette Javadoc.</b> Elle disait que les
 * seules listes ecrites a la main y etaient des listes de fautes reprochees, "dont le mode de
 * defaillance est un rouge, pas un vert silencieux". C'etait faux, et mesure : une liste de refus est
 * verte par defaut sur tout objet qu'elle ne nomme pas, et cette classe est restee verte devant un
 * script tiers reel que sa liste ne nommait pas. Les deux criteres concernes sont desormais totaux
 * sur leur complement - une liste d'autorisation pour le JavaScript tiers, une propriete positive de
 * caracteres pour une cible de ressource litterale.
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
     * ADR-0039 : ce critere etait une liste de refus (un seul nom, "bootstrap"), donc vert par
     * defaut sur tout nom neuf. {@code yanis-qa} l'a mesure en s11 en faisant entrer un
     * {@code alpine.min.js} reel, correctement trace : les seize tests de cette classe sont restes
     * verts. L'enumeration passe donc du cote autorise, ou son mode de defaillance est un rouge.
     *
     * <p>Une entree designe des octets, pas un nom de fichier : l'origine declaree dans
     * {@code provenance.txt} <b>et</b> l'empreinte du fichier servi. Un fichier renomme, une version
     * differente, ou une provenance recopiee au-dessus d'autres octets ne correspondent a aucune
     * entree. Le cout est reel et il est voulu : une mise a jour de la bibliotheque de transport
     * touche ce test, dans le meme commit que {@code provenance.txt}, et la question "est-ce toujours
     * la meme bibliotheque" recoit une reponse humaine.
     *
     * <p>Ce qui autorise cette seule entree : le rendu est servi en fragments HTML (CLAUDE.md,
     * ADR-0008), et le transport hypermedia est ce qui les echange. Il ne porte aucun comportement de
     * jeu. Faire entrer une autre bibliotheque de script demande un ADR, jamais un fichier de plus
     * dans le repertoire.
     */
    private record JavaScriptAutorise(String origineDeclaree, String empreinte) {
    }

    private static final List<JavaScriptAutorise> JAVASCRIPT_TIERS_AUTORISE = List.of(
            new JavaScriptAutorise("npm:htmx.org:2.0.10",
                    "71ea67185bfa8c98c39d31717c6fce5d852370fcdfd129db4543774d3145c0de"));

    /** Ce qu'un gabarit ecrit quand il demande une ressource par une expression Thymeleaf. */
    private static final List<String> ATTRIBUTS_THYMELEAF = List.of("th:src=", "th:href=");

    /**
     * ADR-0039 : ce critere refusait trois caracteres nommes ({@code $}, {@code +}, {@code '}), donc
     * il etait vert par defaut sur toute forme calculee qui n'en emploie aucune - une expression de
     * message {@code #{...}} en est une, et elle designe une ressource differente selon la locale.
     * Il exige desormais la propriete positive : une cible litterale n'est faite que de caracteres
     * d'URI (RFC 3986). Toute expression Thymeleaf porte des accolades ({@code ${...}},
     * {@code #{...}}, {@code ~{...}}, la forme de pretraitement {@code __${...}__}) ou une barre
     * verticale (substitution litterale), et aucun de ces caracteres n'est un caractere d'URI : le
     * complement du critere est donc juge, au lieu d'etre enumere.
     *
     * <p>Ecrit en ensemble de caracteres et non en expression reguliere : la regle ArchUnit d'ADR-0008
     * refuse {@code java.util.regex} dans les tests de ce module, et elle a mordu la premiere ecriture
     * de ce critere. Elle a raison sur le fond meme si ce controle-ci ne porte pas sur du HTML - un
     * ensemble de caracteres se relit, une classe de caracteres echappee se relit mal.
     */
    private static final String CARACTERES_D_UNE_CIBLE_LITTERALE =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:/?#[]@!$&'()*+,;=%";

    private static boolean estUneCibleLitterale(String valeur) {
        return !valeur.isEmpty()
                && valeur.chars().allMatch(c -> CARACTERES_D_UNE_CIBLE_LITTERALE.indexOf(c) >= 0);
    }

    /**
     * ADR-0073 clause 3, <b>et c'est le point le plus dangereux de ce lot</b>. Une illustration de
     * contenu ne peut pas tenir le critere de chemin litteral : sa cible depend de l'item tire, donc
     * l'expression est calculee, et l'ensemble des cibles n'est pas enumerable en lisant le gabarit.
     * La preuve ne disparait pas pour autant, elle <b>change de source</b> - elle se lit desormais
     * sur la donnee, dans {@code LIllustrationDeContenuResoutEtAucuneNeDortTest}, qui verifie dans
     * les deux sens que tout identifiant declare resout et qu'aucun fichier ne dort.
     *
     * <p><b>Le chemin le plus court pour lever ce rouge etait d'elargir ce garde-fou</b>, et
     * elargir ce garde-fou perce la garantie hors ligne d'ADR-0014 pour tout le depot, pas pour
     * l'illustration. ADR-0073 en decide donc la forme avant le lot, et elle est etroite au terme
     * pres : <b>prefixe litteral exactement egal au repertoire d'illustrations de contenu, un unique
     * terme calcule, extension litterale</b>. Une expression qui construirait le repertoire lui-meme,
     * qui porterait deux termes calcules, qui changerait d'extension ou qui viserait un autre
     * repertoire reste refusee - et deux mises a l'epreuve ci-dessous mordent ce refus, sur un
     * gabarit ou la forme admise est deja presente. Sans cette seconde moitie, la morsure prouverait
     * que l'exemption existe, pas qu'elle est etroite.
     */
    private static final String PREFIXE_D_UNE_ILLUSTRATION_DE_CONTENU = "'/illustrations/' + ";

    private static final String SUFFIXE_D_UNE_ILLUSTRATION_DE_CONTENU = " + '.svg'";

    /**
     * Le terme calcule d'une illustration : une variable de modele simple, sans appel, sans operateur
     * et sans concatenation. C'est ce qui fait qu'il y en a exactement un.
     */
    private static final String CARACTERES_D_UN_TERME_CALCULE =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static boolean estUneIllustrationDeContenu(String valeur) {
        if (!valeur.startsWith(PREFIXE_D_UNE_ILLUSTRATION_DE_CONTENU)
                || !valeur.endsWith(SUFFIXE_D_UNE_ILLUSTRATION_DE_CONTENU)) {
            return false;
        }
        String terme = valeur.substring(PREFIXE_D_UNE_ILLUSTRATION_DE_CONTENU.length(),
                valeur.length() - SUFFIXE_D_UNE_ILLUSTRATION_DE_CONTENU.length()).strip();
        if (!terme.startsWith("${") || !terme.endsWith("}")) {
            return false;
        }
        String nom = terme.substring(2, terme.length() - 1);
        return !nom.isEmpty()
                && nom.chars().allMatch(c -> CARACTERES_D_UN_TERME_CALCULE.indexOf(c) >= 0);
    }

    /**
     * ADR-0082 decision 2, T3 (sofia-sm, s49). <b>Seconde exemption etroite, meme forme au caractere
     * pres que celle d'ADR-0073</b> : prefixe litteral exactement egal au repertoire des reproductions,
     * un unique terme calcule, extension litterale. La preuve qu'une reproduction declaree resout et
     * qu'aucun fichier ne dort change de source de la meme maniere qu'une illustration de contenu -
     * elle se lit sur la donnee, dans {@code LIllustrationDeContenuResoutEtAucuneNeDortTest}, jamais
     * ici.
     */
    private static final String PREFIXE_D_UNE_REPRODUCTION_DE_CONTENU = "'/oeuvres/' + ";

    private static final String SUFFIXE_D_UNE_REPRODUCTION_DE_CONTENU = " + '.jpg'";

    /**
     * Hors sprint accueil par matiere, ADR-0106 decision 4 : <b>troisieme exemption etroite, proposee par
     * lea-dev et a ratifier par theo-archi</b> (aucun ADR ne l'ecrit : ADR-0106 decide que le pictogramme est
     * un fichier rendu en {@code <img alt="">} a cote de son libelle, dont le nom vient de la donnee de
     * matiere, et n'a pas vu que ce garde-fou refuse toute reference de gabarit calculee). Meme forme que
     * les deux precedentes, au terme pres : prefixe litteral exactement egal au repertoire des
     * pictogrammes, un unique terme calcule - une variable simple, sans appel ni operateur -, et <b>pas
     * d'extension litterale</b> : le nom de la donnee porte la sienne ({@code francais.svg}), et la preuve
     * que chaque nom designe un fichier arbitre vit sur la donnee, dans
     * {@code LePictogrammeDeMatiereEstUnFichierArbitreTest}, jamais ici.
     */
    private static final String PREFIXE_D_UN_PICTOGRAMME_DE_MATIERE = "'/images/matieres/' + ";

    private static boolean estUnPictogrammeDeMatiere(String valeur) {
        if (!valeur.startsWith(PREFIXE_D_UN_PICTOGRAMME_DE_MATIERE)) {
            return false;
        }
        String terme = valeur.substring(PREFIXE_D_UN_PICTOGRAMME_DE_MATIERE.length()).strip();
        if (!terme.startsWith("${") || !terme.endsWith("}")) {
            return false;
        }
        String nom = terme.substring(2, terme.length() - 1);
        return !nom.isEmpty()
                && nom.chars().allMatch(c -> CARACTERES_D_UN_TERME_CALCULE.indexOf(c) >= 0);
    }

    private static boolean estUneReproductionDeContenu(String valeur) {
        if (!valeur.startsWith(PREFIXE_D_UNE_REPRODUCTION_DE_CONTENU)
                || !valeur.endsWith(SUFFIXE_D_UNE_REPRODUCTION_DE_CONTENU)) {
            return false;
        }
        String terme = valeur.substring(PREFIXE_D_UNE_REPRODUCTION_DE_CONTENU.length(),
                valeur.length() - SUFFIXE_D_UNE_REPRODUCTION_DE_CONTENU.length()).strip();
        if (!terme.startsWith("${") || !terme.endsWith("}")) {
            return false;
        }
        String nom = terme.substring(2, terme.length() - 1);
        return !nom.isEmpty()
                && nom.chars().allMatch(c -> CARACTERES_D_UN_TERME_CALCULE.indexOf(c) >= 0);
    }

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
                String chemin = cheminDeGabarit(gabarit, reference);
                if (chemin == null) {
                    // ADR-0073 clause 3 et ADR-0082 decision 2 : une illustration de contenu ou une
                    // reproduction d'oeuvre ne designe pas un fichier connu du gabarit. Qu'elle
                    // resolve vers un fichier reellement servi, et qu'aucun fichier servi ne dorme,
                    // est verifie sur la donnee par LIllustrationDeContenuResoutEtAucuneNeDortTest,
                    // dans les deux sens, sur les deux populations.
                    continue;
                }
                verifierCibleLocale(racine, gabarit, chemin, gabarit.toString());
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
        if (estUneIllustrationDeContenu(valeur) || estUneReproductionDeContenu(valeur)
                || estUnPictogrammeDeMatiere(valeur)) {
            return null;
        }
        assertThat(estUneCibleLitterale(valeur))
                .as("ADR-0014 : %s reference une ressource par une expression calculee (%s) ; une "
                        + "ressource de page s'ecrit en chemin litteral, sinon rien ne prouve "
                        + "qu'elle est servie hors ligne. Les trois seules exceptions sont "
                        + "l'illustration de contenu d'ADR-0073 (%s${...}%s), la reproduction "
                        + "d'ADR-0082 (%s${...}%s) et le pictogramme de matiere d'ADR-0106 "
                        + "(%s${...}), dont la forme est bornee au terme pres et dont la "
                        + "preuve se lit sur la donnee et non ici", gabarit, brut,
                        PREFIXE_D_UNE_ILLUSTRATION_DE_CONTENU, SUFFIXE_D_UNE_ILLUSTRATION_DE_CONTENU,
                        PREFIXE_D_UNE_REPRODUCTION_DE_CONTENU, SUFFIXE_D_UNE_REPRODUCTION_DE_CONTENU,
                        PREFIXE_D_UN_PICTOGRAMME_DE_MATIERE)
                .isTrue();
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
            if (derniere == null) {
                continue;
            }
            assertThat(derniere)
                    .as("ADR-0014 : dans %s, la derniere feuille chargee est %s, une feuille tierce ; "
                            + "la feuille maison doit passer apres, sinon la regle tierce l'emporte "
                            + "sur la regle maison a specificite egale", gabarit, derniere)
                    .doesNotStartWith("/" + TIERS + "/");
        }
    }

    /**
     * ADR-0008 : le comportement de jeu vit sur le serveur. Aucun JavaScript tiers n'entre dans ce
     * depot, sauf celui qu'une entree de {@code JAVASCRIPT_TIERS_AUTORISE} designe par son origine et
     * ses octets. La vendorisation fichier par fichier fait qu'il faut copier un fichier
     * volontairement pour qu'il entre ; ce critere refuse ce geste-la, quel que soit le nom du
     * fichier copie.
     */
    static void verifierAucunJavaScriptTiersNonAutorise(Path racine) {
        for (Path fichier : ressourcesTierces(racine)) {
            String nom = fichier.getFileName().toString().toLowerCase(java.util.Locale.ROOT);
            if (!nom.endsWith(".js")) {
                continue;
            }
            String chemin = cheminSousStatique(racine, fichier);
            String origine = provenanceDeclaree(racine)
                    .getOrDefault(chemin, new Ressource("", "", "", ""))
                    .origine();
            assertThat(JAVASCRIPT_TIERS_AUTORISE)
                    .as("ADR-0008, ADR-0014 et ADR-0039 : le JavaScript tiers %s n'entre pas dans ce "
                            + "depot. Son origine declaree est \"%s\" et son empreinte %s, qu'aucune "
                            + "entree autorisee ne designe. Le comportement de jeu vit sur le serveur : "
                            + "d'une bibliotheque d'interface, seule sa feuille de style se vendorise. "
                            + "Faire entrer un script tiers de plus demande un ADR et une entree de "
                            + "plus ici, jamais un fichier de plus dans ce repertoire",
                            chemin, origine, empreinte(fichier))
                    .contains(new JavaScriptAutorise(origine, empreinte(fichier)));
        }
    }

    /**
     * ADR-0039, le versant qui empeche l'autorisation de pourrir : une entree qui ne designe aucun
     * fichier servi est un plafond au-dessus de la dette. Elle laisserait croire qu'un script tiers
     * est passe en revue alors que plus rien ne le porte, et elle rouvrirait une place libre pour des
     * octets que personne n'a relus. C'est la lecon du plafond d'ADR-0016, appliquee a une liste
     * d'autorisation.
     */
    static void verifierQueChaqueAutorisationDesigneUnFichierServi(Path racine) {
        List<JavaScriptAutorise> servis = new ArrayList<>();
        for (Path fichier : ressourcesTierces(racine)) {
            if (!fichier.getFileName().toString().toLowerCase(java.util.Locale.ROOT).endsWith(".js")) {
                continue;
            }
            String origine = provenanceDeclaree(racine)
                    .getOrDefault(cheminSousStatique(racine, fichier), new Ressource("", "", "", ""))
                    .origine();
            servis.add(new JavaScriptAutorise(origine, empreinte(fichier)));
        }
        assertThat(servis)
                .as("ADR-0039 : une entree de JAVASCRIPT_TIERS_AUTORISE ne designe aucun fichier "
                        + "reellement servi sous %s/. Soit la bibliotheque a ete retiree et l'entree "
                        + "part dans le meme commit, soit elle a change de version et l'entree se met "
                        + "a jour ; une autorisation qui ne garde plus rien est une place libre",
                        TIERS)
                .containsAll(JAVASCRIPT_TIERS_AUTORISE);
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
    void aucunJavaScriptTiersNonAutoriseNEntreDansLeDepot() {
        verifierAucunJavaScriptTiersNonAutorise(RACINE_PRODUCTION);
    }

    @Test
    void chaqueJavaScriptTiersAutoriseDesigneUnFichierReellementServi() {
        verifierQueChaqueAutorisationDesigneUnFichierServi(RACINE_PRODUCTION);
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

    /**
     * ADR-0073 clause 3, et c'est la premiere des deux moities : l'exemption <b>existe</b> et elle est
     * exercee par la production, pas par une fixture. Le plafond vaut exactement la taille de la
     * population. Un gabarit qui voudrait une illustration de contenu de plus rend ce test rouge, et
     * son auteur lit ADR-0073 au lieu de decouvrir apres coup que le garde-fou a cesse de compter.
     *
     * <p><b>Porte de 1 a 2 (ADR-0080, T4, s34) : fragments/designation.html rejoint
     * fragments/manche.html.</b> C'est la meme decision - l'illustration d'un item, dont le chemin est
     * compose ici et nulle part ailleurs (ADR-0073 clause 1 et 3) -, sur la troisieme mecanique : une
     * designation ne montre rien d'autre que l'image que le contenu declare, et {@code ZoneADesigner}
     * la rend meme obligatoire (ADR-0080, decision 1). Ce n'est pas une decision neuve a tracer ; c'est
     * la meme regle, exercee une seconde fois.
     *
     * <p><b>Porte de 2 a 3 (ADR-0082, T3, sofia-sm, s49) : la seconde branche de
     * fragments/manche.html.</b> Le gabarit porte desormais deux {@code <img>} exclusifs l'un de
     * l'autre pour la meme illustration d'item - un pour {@code static/illustrations/}, un pour
     * {@code static/oeuvres/} -, parce que le gabarit ne peut pas lire un repertoire pour choisir : les
     * deux coexistent dans le balisage, et jsoup en voit deux la ou il n'y en avait qu'un. Ce n'est pas
     * une troisieme reference qui emprunte l'exemption sans decision, c'est la meme reference qui se
     * dedouble en une forme bornee pour chacune des deux populations.
     *
     * <p><b>Porte de 3 a 4 (ADR-0104, decision 7 point 5, s55) : l'image d'une proposition.</b> Quand
     * le controleur derive {@code propositionsEnImage}, chaque bouton de {@code fragments/manche.html}
     * porte une image dont la cible est {@code '/oeuvres/' + ${proposition} + '.jpg'} : la forme deja
     * admise, au terme pres. C'est une quatrieme reference, decidee par l'ADR et nommee par le critere 5
     * de S55-ART-CP-10-MECANIQUE ; la morsure d'etroitesse ci-dessous reste rouge sur deux termes
     * calcules.
     *
     * <p><b>Porte de 4 a 7 (hors sprint accueil par matiere, ADR-0106 decision 4, lea-dev, a ratifier par
     * theo-archi) : le pictogramme de matiere.</b> {@code accueil.html} rend le pictogramme d'une matiere en
     * trois endroits - le bandeau de V2, la tuile de l'etage 1, le bandeau de l'etage 2 - par la forme
     * {@code '/images/matieres/' + ${pictogramme}} : trois references de la meme troisieme exemption,
     * etroite au terme pres comme les deux autres.
     */
    @Test
    void septReferencesDeLaProductionSontDesIllustrationsDesReproductionsOuDesPictogrammes() {
        List<String> admises = new ArrayList<>();
        for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element reference : referencesDeRessource(document)) {
                if (cheminDeGabarit(gabarit, reference) == null) {
                    admises.add(gabarit + " -> " + reference.attr("th:src"));
                }
            }
        }

        assertThat(admises)
                .as("ADR-0051, ADR-0073 clause 3 et ADR-0082 decision 2 : ces exemptions ne prouvent "
                        + "rien tant qu'aucune reference de production ne les empruntent, et elles "
                        + "cessent d'etre etroites des qu'une reference de plus les emprunte sans "
                        + "qu'on l'ait decide")
                .hasSize(7);
    }

    /**
     * Les references d'un gabarit, jugees sur une source donnee plutot que sur le fichier lu : c'est
     * ce qui permet de mordre en mutant le <b>gabarit reel en memoire</b>, plutot qu'en fabriquant un
     * gabarit fautif qui ressemblerait a ce que j'imagine.
     */
    private static void verifierLesReferencesDe(Path gabarit, String source) {
        Document document = Jsoup.parse(source, gabarit.toString());
        for (Element reference : referencesDeRessource(document)) {
            cheminDeGabarit(gabarit, reference);
        }
    }

    /** Le gabarit de production qui porte la forme admise, decouvert et jamais nomme ici. */
    private static Path gabaritQuiPorteUneIllustrationDeContenu() {
        for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element reference : referencesDeRessource(document)) {
                if (cheminDeGabarit(gabarit, reference) == null) {
                    return gabarit;
                }
            }
        }
        throw new AssertionError("ADR-0051 : aucun gabarit de production ne porte d'illustration de "
                + "contenu, les morsures de la clause 3 ne s'exercent alors sur rien");
    }

    /** La valeur brute d'une reference, avant retrait de l'enveloppe @{...} - meme lecture que
     * {@link #cheminDeGabarit(Path, Element)}, extraite pour etre reutilisee sans re-asserter. */
    private static String valeurBruteDeReference(Element reference) {
        String brut = reference.hasAttr("th:href") ? reference.attr("th:href")
                : reference.hasAttr("th:src") ? reference.attr("th:src")
                : reference.hasAttr("href") ? reference.attr("href")
                : reference.attr("src");
        String valeur = brut.strip();
        if (valeur.startsWith("@{") && valeur.endsWith("}")) {
            valeur = valeur.substring(2, valeur.length() - 1).strip();
        }
        return valeur;
    }

    /**
     * ADR-0082 decision 2, T3 : le gabarit de production qui porte la forme admise de reproduction,
     * distinct de celui qui porte l'illustration de contenu des que les deux coexistent dans le meme
     * fichier (fragments/manche.html, depuis T3).
     */
    private static Path gabaritQuiPorteUneReproductionDeContenu() {
        for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element reference : referencesDeRessource(document)) {
                if (estUneReproductionDeContenu(valeurBruteDeReference(reference))) {
                    return gabarit;
                }
            }
        }
        throw new AssertionError("ADR-0082 : aucun gabarit de production ne porte de reproduction de "
                + "contenu, les morsures de la seconde exemption ne s'exercent alors sur rien");
    }

    /**
     * <b>ADR-0073 clause 3, seconde moitie, et c'est elle qui compte.</b> La moitie precedente prouve
     * que l'exemption existe ; celle-ci prouve qu'elle est <b>etroite</b>. Chaque mutation pose une
     * seconde forme calculee <b>dans le meme gabarit</b>, la forme admise restant a sa place, et
     * chacune doit rester rouge : une image de decor dont le nom depend de l'item (c'est la condition
     * C5 d'ADR-0036, reprise mot pour mot), un repertoire lui-meme construit, deux termes calcules la
     * ou un seul est admis, et une extension calculee. Sans cette moitie-la, la morsure prouverait que
     * l'exemption existe, pas qu'elle est etroite - et c'est le chemin que {@code theo-archi} nomme
     * comme le plus dangereux du sprint.
     */
    @Test
    void uneSecondeFormeCalculeePoseeDansLeMemeGabaritResteRefusee() {
        Path gabarit = gabaritQuiPorteUneIllustrationDeContenu();
        String source = lire(gabarit);
        verifierLesReferencesDe(gabarit, source);

        for (String secondeForme : List.of(
                "@{'/images/fronton-' + ${enonce} + '.png'}",
                "@{'/' + ${repertoire} + '/' + ${illustration} + '.svg'}",
                "@{'/illustrations/' + ${famille} + '-' + ${illustration} + '.svg'}",
                "@{'/illustrations/' + ${illustration} + ${extension}}")) {
            String mute = source.replace("</body>",
                    "<img th:src=\"" + secondeForme + "\" alt=\"\"></body>");
            assertThatThrownBy(() -> verifierLesReferencesDe(gabarit, mute))
                    .as("une seconde forme calculee (%s) posee a cote de la forme admise doit rester "
                            + "refusee", secondeForme)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("expression calculee");
        }
    }

    /**
     * <b>ADR-0082 decision 2, T3, seconde exemption, meme motif que la precedente.</b> Chaque mutation
     * pose une seconde forme calculee <b>dans le gabarit qui porte deja la forme admise de
     * reproduction</b>, et chacune doit rester rouge : un repertoire lui-meme construit, deux termes
     * calcules la ou un seul est admis, et une extension calculee. Sans cette morsure, l'exemption de
     * reproduction prouverait qu'elle existe, pas qu'elle est etroite - exactement le risque
     * qu'ADR-0082 nomme pour sa propre decision 2.
     */
    @Test
    void uneSecondeFormeCalculeeDeReproductionPoseeDansLeMemeGabaritResteRefusee() {
        Path gabarit = gabaritQuiPorteUneReproductionDeContenu();
        String source = lire(gabarit);
        verifierLesReferencesDe(gabarit, source);

        for (String secondeForme : List.of(
                "@{'/' + ${repertoire} + '/' + ${illustration} + '.jpg'}",
                "@{'/oeuvres/' + ${famille} + '-' + ${illustration} + '.jpg'}",
                "@{'/oeuvres/' + ${illustration} + ${extension}}")) {
            String mute = source.replace("</body>",
                    "<img th:src=\"" + secondeForme + "\" alt=\"\"></body>");
            assertThatThrownBy(() -> verifierLesReferencesDe(gabarit, mute))
                    .as("une seconde forme calculee de reproduction (%s) posee a cote de la forme "
                            + "admise doit rester refusee", secondeForme)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("expression calculee");
        }
    }

    /**
     * <b>Hors sprint accueil par matiere, troisieme exemption, meme motif que les deux precedentes.</b> Chaque
     * mutation pose une seconde forme calculee dans le gabarit qui porte deja la forme admise de pictogramme, et
     * chacune doit rester rouge : un repertoire lui-meme construit, deux termes calcules, un terme qui est un appel
     * ou une concatenation, et un autre repertoire d'images. Sans cette morsure, l'exemption prouverait qu'elle
     * existe, pas qu'elle est etroite.
     */
    @Test
    void uneSecondeFormeCalculeeDePictogrammePoseeDansLeMemeGabaritResteRefusee() {
        Path gabarit = null;
        for (Path candidat : gabarits(RACINE_PRODUCTION)) {
            Document document = Jsoup.parse(lire(candidat), candidat.toString());
            for (Element reference : referencesDeRessource(document)) {
                if (estUnPictogrammeDeMatiere(valeurBruteDeReference(reference))) {
                    gabarit = candidat;
                }
            }
        }
        assertThat(gabarit).as("ADR-0051 : aucun gabarit de production ne porte de pictogramme de matiere,"
                + " les morsures de la troisieme exemption ne s'exercent alors sur rien").isNotNull();
        Path porteur = gabarit;
        String source = lire(porteur);
        verifierLesReferencesDe(porteur, source);

        for (String secondeForme : List.of(
                "@{'/' + ${repertoire} + '/' + ${pictogramme}}",
                "@{'/images/matieres/' + ${famille} + '-' + ${pictogramme}}",
                "@{'/images/matieres/' + ${groupe.matiere().pictogramme()}}",
                "@{'/images/' + ${pictogramme}}")) {
            String mute = source.replace("</body>",
                    "<img th:src=\"" + secondeForme + "\" alt=\"\"></body>");
            assertThatThrownBy(() -> verifierLesReferencesDe(porteur, mute))
                    .as("une seconde forme calculee de pictogramme (%s) posee a cote de la forme admise doit "
                            + "rester refusee", secondeForme)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("expression calculee");
        }
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
                verifierAucunJavaScriptTiersNonAutorise(
                        CONTRE_EXEMPLES.resolve("javascript-de-composants")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'entre pas dans ce depot");
    }

    /**
     * ADR-0039, la mise a l'epreuve qui manquait : un script tiers <b>correctement trace</b>, sous un
     * nom que personne n'avait inscrit nulle part. C'est le geste exact que {@code yanis-qa} a joue en
     * s11 sur la production, avec un {@code alpine.min.js} reel - et les seize tests de cette classe
     * etaient restes verts. Le jeu fautif porte une ligne de provenance complete et une empreinte
     * exacte : tout ce que la doctrine attend d'un ajout legitime est la, et le refus ne doit rien
     * devoir au nom du fichier.
     */
    @Test
    void unJavaScriptTiersCorrectementTraceMaisNonAutoriseFaitEchouerLeGardeFou() {
        Path jeuFautif = CONTRE_EXEMPLES.resolve("javascript-tiers-trace");

        verifierProvenanceDesRessourcesTierces(jeuFautif);
        assertThatThrownBy(() -> verifierAucunJavaScriptTiersNonAutorise(jeuFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'entre pas dans ce depot");
    }

    /**
     * ADR-0039 : l'autre versant, une autorisation qui ne garde plus rien. Le jeu fautif ne sert aucun
     * JavaScript tiers du tout, donc aucune des entrees autorisees n'y designe de fichier.
     */
    @Test
    void uneAutorisationQuiNeDesigneAucunFichierServiFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueAutorisationDesigneUnFichierServi(
                CONTRE_EXEMPLES.resolve("tiers-non-trace")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("est une place libre");
    }
}
