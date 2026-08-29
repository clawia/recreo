package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0064 : <b>un garde-fou qui tire des items sans remise ne borne jamais ses tirages sous ce que
 * le plus grand paquet enumere du catalogue reel declare.</b>
 *
 * <p><b>Le defaut reel, mesure et pas imagine.</b> Le 2026-08-28, cinq garde-fous de contenu
 * bornaient leurs tirages a {@code 60}, valeur ecrite a la main, alors que
 * {@code cm1-tables-multiplication} declare {@code 99} items : <b>39 items par garde-fou,
 * cinq fois, n'etaient examines par personne</b>, et rien ne le disait. Le mode de defaillance
 * n'est pas l'erreur, c'est le silence - la meme famille qu'ADR-0051, un cran plus dangereux :
 * un garde-fou qui n'examine rien finit par se remarquer, un garde-fou qui examine les trois
 * cinquiemes de ses objets ne se remarque jamais.
 *
 * <p><b>Ce que ce recensement interdit reellement.</b> Ecrire, dans un garde-fou de contenu neuf,
 * {@code for (int tirage = 0; tirage < 60; tirage++)}, ou reprendre par copie le patron d'un
 * voisin. Et - c'est la partie qui travaille apres aujourd'hui - il rougit <b>le jour ou le
 * catalogue rattrape une borne ecrite a la main</b>, avant que cette borne n'ait pu lire un paquet
 * en partie. Quatre bornes ecrites a la main subsistent dans le depot au 2026-08-29 (150, 500, 500,
 * 2000) ; aucune n'est partielle aujourd'hui, et aucune ne pourra le devenir en silence.
 *
 * <p><b>Ce n'est pas une regle ArchUnit, et le compte de regles ArchUnit du depot est inchange.</b>
 * ArchUnit lit du bytecode ; une borne litterale dans une boucle de test n'y est pas une propriete
 * lisible. Le mecanisme est un balayage de sources, sur le precedent de
 * {@link LesGardeFousSontMisALEpreuveTest}, dont il reprend la derivation de perimetre plutot que
 * d'en ecrire une seconde.
 *
 * <p><b>Le critere est numerique et se calcule sur du materiau reel</b> - les fichiers de contenu
 * servis, pas une fixture ecrite ici. C'est la lecon de s05 : une fixture ecrite par l'auteur d'une
 * regle ressemble a ce qu'il imagine. La fixture n'intervient que pour prouver la morsure, sur un
 * depot fictif complet.
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les modules viennent du pom agregateur, les sources de test du
 *       balayage de ces modules, les paquets du balayage de leurs ressources ; aucune liste ecrite
 *       ici ;</li>
 *   <li><b>derivation totale</b> : chaque boucle de tirage trouvee produit un verdict, et le
 *       recensement declare rouge s'il ne trouve aucune boucle ou aucun paquet enumere ;</li>
 *   <li><b>mise a l'epreuve</b> : un depot fictif, jamais construit, qui porte deux tireurs - l'un
 *       borne a la main sous son paquet, l'autre derive - dont seul le premier doit etre nomme.</li>
 * </ul>
 *
 * <p><b>Ce que ce recensement ne voit pas, ecrit ici plutot que laisse a deviner.</b> Il resout une
 * borne ecrite en clair dans la condition ({@code tirage < 60}) ou declaree comme entier litteral
 * dans le meme fichier ({@code int NOM = 60;}, champ ou variable locale). Une borne calculee
 * ({@code declares + 1}, {@code vises * 20}) est tenue pour derivee, ce qui est l'intention ; une borne importee d'une autre classe
 * ({@code Autre.TIRAGES}) ou assemblee ({@code 30 + 30}) lui echappe. Ce sont des formes qu'aucun
 * garde-fou du depot n'emploie et qui demandent d'ecrire autre chose que ce qu'on copie chez le
 * voisin.</p>
 */
class UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest {

    /** Ce qui fait d'une boucle une boucle de tirage : son corps demande l'item suivant. */
    private static final String APPEL_DE_TIRAGE = ".next(";

    /** Le repertoire de contenu servi d'un module, relatif a ce module. */
    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources/contenu");

    /** Ce qui ouvre un item enumere dans un paquet YAML. */
    private static final String ITEM_ENUMERE = "- enonce:";

    /** Fenetre de lecture du corps d'une boucle, en caracteres, pour y chercher l'appel de tirage. */
    private static final int FENETRE_DU_CORPS = 600;

    /** Depot fictif complet, avec son pom agregateur, sur lequel ce recensement se prouve mordant. */
    private static final Path DEPOT_FAUTIF = Path.of(
            "src", "test", "resources", "contreexemple",
            "UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String nomDeClasse(Path fichier) {
        String nom = fichier.getFileName().toString();
        return nom.substring(0, nom.length() - ".java".length());
    }

    /**
     * Le nombre d'items qu'un paquet enumere declare sur disque, 0 pour un paquet combinatoire qui
     * n'en enumere aucun. Meme comptage que celui dont les garde-fous de contenu derivent leur
     * borne : c'est bien la meme grandeur qui est comparee des deux cotes.
     */
    private static int itemsDeclares(Path paquet) {
        return (int) lire(paquet).lines().filter(ligne -> ligne.strip().startsWith(ITEM_ENUMERE)).count();
    }

    /**
     * Le plus grand paquet enumere du catalogue reel, tous modules confondus. Population derivee :
     * les modules viennent du pom agregateur, les paquets du repertoire de contenu de chacun.
     */
    static int plusGrandPaquetEnumere(Path racine) {
        int plusGrand = 0;
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path contenu = racine.resolve(module).resolve(CONTENU_DE_PRODUCTION);
            if (!Files.isDirectory(contenu)) {
                continue;
            }
            try (Stream<Path> paquets = Files.walk(contenu)) {
                for (Path paquet : paquets.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                        .toList()) {
                    plusGrand = Math.max(plusGrand, itemsDeclares(paquet));
                }
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return plusGrand;
    }

    /** Une boucle de tirage : le fichier qui la porte et le jeton qui la borne. */
    private record BoucleDeTirage(Path fichier, String borne) {
    }

    /** Le premier jeton compare dans une condition de boucle, celui qui compte les tirages. */
    private static String jetonBorne(String condition) {
        int inferieur = condition.indexOf('<');
        if (inferieur < 0) {
            return "";
        }
        int debut = inferieur + 1;
        while (debut < condition.length() && Character.isWhitespace(condition.charAt(debut))) {
            debut++;
        }
        int fin = debut;
        while (fin < condition.length() && Character.isJavaIdentifierPart(condition.charAt(fin))) {
            fin++;
        }
        return condition.substring(debut, fin);
    }

    /**
     * Le second argument d'un appel de tirage, quand c'est un simple identifiant : l'ensemble des
     * items deja vus. Chaine vide sinon.
     */
    private static String ensembleDesDejaVus(String corps) {
        int appel = corps.indexOf(APPEL_DE_TIRAGE);
        if (appel < 0) {
            return "";
        }
        int virgule = corps.indexOf(',', appel);
        int fermante = corps.indexOf(')', appel);
        if (virgule < 0 || fermante < 0 || virgule > fermante) {
            return "";
        }
        String argument = corps.substring(virgule + 1, fermante).strip();
        if (argument.isEmpty()) {
            return "";
        }
        for (int position = 0; position < argument.length(); position++) {
            if (!Character.isJavaIdentifierPart(argument.charAt(position))) {
                return "";
            }
        }
        return argument;
    }

    /**
     * Les boucles de tirage <b>sans remise</b> de tout le depot. Une boucle compte si son en-tete
     * est une boucle classique a trois membres, si son corps demande l'item suivant, et si
     * l'ensemble des deja-vus qu'elle passe au tirage est un identifiant que ce meme corps
     * alimente : c'est la <b>forme</b> qui la designe, jamais le nom de sa variable de comptage ni
     * le nom du fichier qui la porte.
     *
     * <p>Cette derniere condition n'est pas une precaution : {@code PaquetsFrancaisS04Test} tire
     * vingt fois <b>avec remise</b> - un ensemble vide et immuable passe a chaque tour - pour
     * constater une variete, et sa borne de vingt ne lit aucun paquet en partie. Une regle qui la
     * nommerait echouerait par inclusion, ce qui est aussi grave qu'echouer par omission.</p>
     */
    static List<BoucleDeTirage> bouclesDeTirage(Path racine) {
        List<BoucleDeTirage> boucles = new ArrayList<>();
        for (Path fichier : LesGardeFousSontMisALEpreuveTest.fichiersDeTest(racine)) {
            String source = lire(fichier);
            int position = 0;
            while ((position = source.indexOf("for (", position)) >= 0) {
                int accolade = source.indexOf('{', position);
                position += "for (".length();
                if (accolade < 0) {
                    continue;
                }
                String[] membres = source.substring(position, accolade).split(";");
                if (membres.length != 3) {
                    continue;
                }
                String corps = source.substring(accolade,
                        Math.min(source.length(), accolade + FENETRE_DU_CORPS));
                String dejaVus = ensembleDesDejaVus(corps);
                if (dejaVus.isEmpty() || !corps.contains(dejaVus + ".add(")) {
                    continue;
                }
                String jeton = jetonBorne(membres[1]);
                if (!jeton.isEmpty()) {
                    boucles.add(new BoucleDeTirage(fichier, jeton));
                }
            }
        }
        return boucles;
    }

    /**
     * La valeur d'une borne <b>ecrite a la main</b> : un entier litteral declare dans le meme
     * fichier, champ ou variable locale. {@code -1} quand la borne est calculee, donc derivee de ce
     * que le paquet declare, ce qui est precisement la forme demandee.
     */
    private static int valeurEcriteALaMain(String source, String jeton) {
        if (jeton.chars().allMatch(Character::isDigit)) {
            return Integer.parseInt(jeton);
        }
        String marque = "int " + jeton;
        int position = 0;
        while ((position = source.indexOf(marque, position)) >= 0) {
            int apres = position + marque.length();
            position = apres;
            if (apres >= source.length() || Character.isJavaIdentifierPart(source.charAt(apres))) {
                continue;
            }
            int curseur = apres;
            while (curseur < source.length() && Character.isWhitespace(source.charAt(curseur))) {
                curseur++;
            }
            if (curseur >= source.length() || source.charAt(curseur) != '=') {
                continue;
            }
            curseur++;
            while (curseur < source.length() && Character.isWhitespace(source.charAt(curseur))) {
                curseur++;
            }
            int debut = curseur;
            while (curseur < source.length() && Character.isDigit(source.charAt(curseur))) {
                curseur++;
            }
            if (curseur == debut) {
                continue;
            }
            while (curseur < source.length() && Character.isWhitespace(source.charAt(curseur))) {
                curseur++;
            }
            if (curseur < source.length() && source.charAt(curseur) == ';') {
                return Integer.parseInt(source.substring(debut, curseur).strip());
            }
        }
        return -1;
    }

    /**
     * Les bornes ecrites a la main que le catalogue reel a deja rattrapees. Une ligne par defaut,
     * nommant le garde-fou, sa borne et le paquet qui la depasse.
     */
    static List<String> bornesSousLePlusGrandPaquet(Path racine) {
        int plusGrand = plusGrandPaquetEnumere(racine);
        List<String> defauts = new ArrayList<>();
        for (BoucleDeTirage boucle : bouclesDeTirage(racine)) {
            int valeur = valeurEcriteALaMain(lire(boucle.fichier()), boucle.borne());
            if (valeur >= 0 && valeur < plusGrand) {
                String borne = boucle.borne().equals(String.valueOf(valeur))
                        ? "borne de tirage ecrite en clair : " + valeur
                        : "borne de tirage " + boucle.borne() + " = " + valeur;
                defauts.add(nomDeClasse(boucle.fichier()) + " : " + borne
                        + ", alors que le plus grand paquet enumere du catalogue en"
                        + " declare " + plusGrand + ". Ce garde-fou lit " + (plusGrand - valeur)
                        + " items de moins que ce paquet ne contient, et ne le dit pas");
            }
        }
        return defauts;
    }

    static void verifierLesBornesDeTirage(Path racine) {
        assertThat(plusGrandPaquetEnumere(racine))
                .as("ADR-0051 : ce recensement ne prouve rien s'il ne trouve aucun paquet enumere"
                        + " sous %s - le critere se calcule sur le catalogue reel, et un catalogue"
                        + " vide rendrait toute borne acceptable", racine)
                .isPositive();
        assertThat(bouclesDeTirage(racine))
                .as("ADR-0051 : ce recensement ne prouve rien s'il ne trouve aucune boucle de tirage"
                        + " sous %s", racine)
                .isNotEmpty();
        assertThat(bornesSousLePlusGrandPaquet(racine))
                .as("ADR-0064 : une borne de tirage ecrite a la main sous la taille du plus grand"
                        + " paquet enumere fait lire ce paquet en partie, sans que rien ne le dise."
                        + " Derive la borne de ce que le paquet declare (declares + 1, un tirage"
                        + " d'echantillon pour un paquet combinatoire) et assertionne que le paquet"
                        + " enumere a bien ete lu en entier")
                .isEmpty();
    }

    @Test
    void aucunGardeFouDuDepotNeBorneSesTiragesSousLePlusGrandPaquetEnumere() {
        verifierLesBornesDeTirage(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
    }

    /**
     * ADR-0013 obligation 3 : la morsure se prouve sur un objet neuf, absent du depot reel. Le depot
     * fictif porte deux tireurs sur le meme paquet de cinq items - l'un borne a trois a la main,
     * l'autre derive du paquet - et seul le premier doit etre nomme. Une regle qui nommerait les
     * deux echouerait par inclusion, ce qui est aussi grave qu'echouer par omission.
     */
    @Test
    void seulLeTireurBorneSousSonPaquetEstNommeDansLeDepotFautif() {
        assertThat(bornesSousLePlusGrandPaquet(DEPOT_FAUTIF))
                .containsExactly("TireurBorneALaMainContreExemple : borne de tirage TIRAGES = 3,"
                        + " alors que le plus grand paquet enumere du catalogue en declare 5. Ce"
                        + " garde-fou lit 2 items de moins que ce paquet ne contient, et ne le dit"
                        + " pas");
    }

    @Test
    void unDepotQuiPorteUneBorneEcriteALaMainSousSonPaquetEstRefuse() {
        assertThatThrownBy(() -> verifierLesBornesDeTirage(DEPOT_FAUTIF))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TireurBorneALaMainContreExemple")
                .hasMessageContaining("ADR-0064");
    }

    /**
     * Le recensement voit bien les deux tireurs du depot fictif : sans cela, le test ci-dessus
     * passerait aussi si la boucle derivee n'etait jamais lue, et ne prouverait alors que la moitie
     * de ce qu'il annonce.
     */
    @Test
    void lesDeuxTireursDuDepotFautifSontBienRecenses() {
        assertThat(bouclesDeTirage(DEPOT_FAUTIF).stream()
                .map(boucle -> nomDeClasse(boucle.fichier()) + " borne par " + boucle.borne())
                .toList())
                .containsExactlyInAnyOrder(
                        "TireurBorneALaMainContreExemple borne par TIRAGES",
                        "TireurDeriveContreExemple borne par tirages");
    }
}
