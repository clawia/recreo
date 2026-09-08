package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0087. <b>Une ligne de tableau markdown ne porte jamais plus de cellules que son en-tete</b> :
 * la cellule en trop est ignoree par le rendu, donc son texte est ecrit, commite, relu par personne
 * et affiche a personne.
 *
 * <p><b>Le fait est de {@code nadia-prof}, en cloture de s31</b>, verifie par {@code sofia-sm} avant
 * routage : deux lignes de {@code squad/programme/MATRICE-couverture.md} - celles de
 * {@code bandit-manchot-melanges-cm1} et de {@code bandit-manchot-objet-technique-cm1} - portent
 * huit cellules pour sept colonnes, une barre verticale non echappee ouvrant une colonne absente de
 * l'en-tete. <b>Les deux seules des 209.</b> La huitieme cellule porte le resultat de deux des six
 * stories du sprint (les reaudits du 2026-09-07 levant {@code MEL-R1}, {@code MEL-R2},
 * {@code MEL-R3}, {@code OBT-R1} et {@code OBT-R2}) : le texte est juste, il est dans le fichier, et
 * un rendu markdown ne l'affiche pas.
 *
 * <p><b>Le critere est derive de l'en-tete de chaque tableau, jamais d'une largeur ecrite ici</b>, et
 * c'est ce qui le rend applicable a tout le depot : {@code MATRICE-couverture.md} porte des tableaux
 * de programme a sept colonnes et des tableaux de synthese, de bilan et de ceremonie a deux, trois,
 * quatre ou cinq colonnes. Un motif qui exigerait sept cellules partout serait rouge sur des lignes
 * legitimes ; une largeur derivee ne peut pas l'etre. Mesure a l'appui, sur le perimetre exact de ce
 * garde-fou et sur l'arbre de {@code 7a3c8cc8} : <b>639 fichiers, 2 159 tableaux, 12 492 lignes de
 * corps, 3 lignes en surplus</b> - deux implementations independantes rendent les memes quatre
 * chiffres.
 *
 * <p><b>Ce que cette regle interdit reellement</b>, ecrit avant qu'on demande a quoi elle sert : que
 * du texte entre au depot dans une cellule qu'aucun lecteur ne verra. Elle a d'ailleurs trouve un
 * troisieme cas que personne n'avait signale, dans un fichier que je tiens moi-meme :
 * {@code ADR-index.md} cachait de la meme facon tout l'amendement du 2026-08-29 de la ligne
 * {@code ADR-0063} - corrige dans le commit qui cree ce garde-fou.
 *
 * <p><b>Ce qu'elle ne juge pas, avec son chiffre.</b> Une ligne qui porte <b>moins</b> de cellules
 * que son en-tete n'est pas refusee : le rendu la complete de cellules vides, aucun texte ne
 * disparait. Le depot en porte <b>9</b> a {@code 7a3c8cc8}, dans six fichiers, dont un cas de ligne
 * repliee sur deux lignes source ({@code proposition-ines-etat-actif-et-rang-unique.md}, lignes 55
 * et 56) - la seule ligne de corps des 12 492 qui n'ouvre pas par une barre verticale, et donc la
 * seule que le balayage ci-dessous ne lit pas. Dette nommee et chiffree dans ADR-0087, non encodee.
 *
 * <p><b>Perimetre, derive et non enumere</b> : tout fichier {@code .md} sous la racine derivee du pom
 * agregateur, sauf les repertoires de sortie de build ({@code target}), les arbres de contre-exemple
 * ({@code contreexemple}, dont la raison d'etre est de porter des defauts) et {@code .git}. Meme
 * derivation que {@link ToutesLesReferencesDeDecisionResolventTest}.
 */
class AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest {

    /**
     * La ligne de delimitation qui, en markdown, fait d'une ligne de texte l'en-tete d'un tableau.
     * Sans elle, une ligne pleine de barres verticales est un paragraphe et rien n'est en surplus.
     */
    private static final Pattern DELIMITATION =
            Pattern.compile("^\\|?\\s*:?-+:?\\s*(\\|\\s*:?-+:?\\s*)*\\|?\\s*$");

    /** Ce qui separe deux cellules : une barre verticale que rien n'echappe. */
    private static final Pattern SEPARATEUR = Pattern.compile("(?<!\\\\)\\|");

    /** ADR-0013 obligation 3 : le materiau fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest");

    /**
     * Les lignes en surplus vivantes du depot, designees par le nom de jeu qu'elles portent.
     * <b>Vide depuis la cloture de s31 : le depot ne porte plus aucune ligne en surplus.</b> Cette
     * liste exempte, elle n'absout pas.
     *
     * <p>Meme mecanique que {@code DETTE_DE_BALAYAGE} d'ADR-0013 et que le cliquet d'ADR-0067 : elle
     * ne peut pas grandir - une ligne en surplus de plus est rouge -, elle ne peut pas pourrir - un
     * nom qui ne designe plus une ligne en surplus est rouge -, et la vider oblige a baisser le
     * plafond dans le meme diff. Le detail de ce paiement est sous {@link #PLAFOND_DE_DETTE}.
     *
     * <p><i>Ce commentaire decrivait, jusqu'a la cloture de s31, les <b>deux</b> lignes de
     * {@code MATRICE-couverture.md} que {@code theo-archi} avait exemptees plutot que corrigees,
     * parce que leur contenu appartenait a {@code camille-po} - et il annoncait que la correction de
     * celle-ci rendrait ce fichier rouge. Elle est arrivee, le rouge est venu, {@code sofia-sm} l'a
     * paye en vidant l'ensemble - <b>et a laisse ce commentaire decrire deux noms au-dessus d'un
     * ensemble vide.</b> Corrige ici par elle, une heure plus tard : changer une valeur sans toucher
     * la Javadoc qui la decrit est de la meme famille qu'un chiffre sans methode, et ce sprint en a
     * deja compte trois.</i>
     */
    private static final Set<String> DETTE_DE_CELLULES = Set.of();

    /**
     * Les noms par lesquels {@link #lExemptionEstUneExemptionEtNonUnPerimetre()} exempte les lignes
     * du contre-exemple. <b>Ils ne sont pas la dette vivante, et c'est la decision du 2026-09-08
     * (cloture s31)</b> : {@code DETTE_DE_CELLULES} dit quel defaut le depot porte encore
     * aujourd'hui, ce jeu-ci dit ce que le <b>mecanisme</b> d'exemption fait d'un nom qu'on lui
     * donne. Deux proprietes, deux clauses, deux materiaux.
     *
     * <p><b>Motif, et il vient d'un rouge reel.</b> Cette preuve s'appuyait sur
     * {@code DETTE_DE_CELLULES}. Le jour ou {@code camille-po} a corrige les deux lignes de la
     * matrice et ou {@code sofia-sm} a paye la dette, la clause est devenue rouge <b>sans avoir rien
     * detecte</b> : elle avait seulement perdu l'entree qui lui donnait un sens. Une preuve de
     * mecanisme qui ne vaut que pendant que le depot est en dette ne vaut plus rien le jour ou il
     * n'y est plus - c'est-a-dire exactement quand le garde-fou doit encore etre digne de confiance.
     *
     * <p><b>Ce jeu de noms ne peut pas pourrir</b> : sa clause exige que chacun designe encore une
     * ligne en surplus <b>du contre-exemple</b>. Il est fige parce que son materiau est fige - un
     * extrait de l'historique, recopie octet pour octet - et il n'a aucune raison de suivre l'etat
     * du depot.
     */
    private static final Set<String> NOMS_DU_CONTRE_EXEMPLE = Set.of(
            "bandit-manchot-melanges-cm1",
            "bandit-manchot-objet-technique-cm1");

    /**
     * Etat au 2026-09-08, s31, cloture : <b>0</b>. La dette est <b>vide</b>, donc toute ligne en
     * surplus qui apparaitrait desormais est rouge sans exemption possible.
     *
     * <p>Ce nombre valait <b>2</b> quand {@code theo-archi} a ecrit ce garde-fou, soit exactement
     * les deux lignes de la matrice que {@code nadia-prof} avait trouvees. Il les a ecrites en
     * exemption plutot qu'en les corrigeant, parce que le contenu de ces deux lignes appartient a
     * {@code camille-po}, et il a annonce dans le meme geste que la correction de celle-ci rendrait
     * son fichier rouge. <b>Elle est arrivee, le rouge est venu, et c'est ici qu'il se paye</b> :
     * {@code sofia-sm} a l'assemblage, en fusionnant en second. Les deux noms ne designaient plus
     * aucune ligne en surplus - verifie par le balayage lui-meme, qui refuse une exemption qui ne
     * nomme plus rien de reel.
     *
     * <p><b>La dette n'est pas reduite, elle est payee.</b> Le troisieme cas du depot,
     * {@code ADR-index.md}, avait ete corrige par {@code theo-archi} dans son propre commit - son
     * garde-fou l'a mordu avant de mordre quiconque. Ce nombre ne remonte jamais ; le remonter
     * demanderait un diff aussi visible que celui-ci.
     */
    private static final int PLAFOND_DE_DETTE = 0;

    /** Une ligne de corps qui porte plus de cellules que l'en-tete de son tableau. */
    private record LigneEnSurplus(Path fichier, int numero, int cellules, int enTete, String invisible,
                                  String texte) {

        String defaut() {
            return String.format(
                    "%s:%d porte %d cellules pour un en-tete de %d colonnes : le texte de la cellule"
                            + " en trop n'est affiche par aucun rendu markdown. Invisible : \"%s\""
                            + " (ADR-0087)",
                    fichier, numero, cellules, enTete, invisible);
        }
    }

    /** Ce qu'un balayage a lu, et ce qu'il a trouve. ADR-0051 : les deux sont dits, pas espere. */
    private record Balayage(int fichiers, int tableaux, int lignes, List<LigneEnSurplus> surplus) {
    }

    private static String lire(Path fichier) {
        try {
            return new String(Files.readAllBytes(fichier), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static boolean estDansLePerimetre(Path racine, Path fichier) {
        for (Path partie : racine.relativize(fichier)) {
            String nom = partie.toString();
            if (nom.equals("target") || nom.equals("contreexemple") || nom.equals(".git")) {
                return false;
            }
        }
        return true;
    }

    private static List<Path> fichiersMarkdown(Path racine) {
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".md"))
                    .filter(fichier -> estDansLePerimetre(racine, fichier))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les cellules d'une ligne, comptees comme un rendu markdown les compte : les barres verticales
     * d'ouverture et de fermeture ne comptent pas, une barre echappee est du texte.
     */
    private static List<String> cellules(String ligne) {
        String reste = ligne.strip();
        if (reste.startsWith("|")) {
            reste = reste.substring(1);
        }
        if (reste.endsWith("|") && !reste.endsWith("\\|")) {
            reste = reste.substring(0, reste.length() - 1);
        }
        return List.of(SEPARATEUR.split(reste, -1));
    }

    private static boolean estUneLigneDeCorps(String ligne) {
        return ligne.strip().startsWith("|");
    }

    static Balayage balayer(Path racine) {
        List<Path> fichiers = fichiersMarkdown(racine);
        List<LigneEnSurplus> surplus = new ArrayList<>();
        int tableaux = 0;
        int lignesDeCorps = 0;
        for (Path fichier : fichiers) {
            List<String> lignes = List.of(lire(fichier).split("\n", -1));
            Path relatif = racine.relativize(fichier);
            int position = 0;
            while (position < lignes.size() - 1) {
                String candidat = lignes.get(position);
                String delimitation = lignes.get(position + 1);
                if (!candidat.contains("|") || !delimitation.contains("|")
                        || !DELIMITATION.matcher(delimitation.strip()).matches()) {
                    position++;
                    continue;
                }
                int enTete = cellules(candidat).size();
                tableaux++;
                int corps = position + 2;
                while (corps < lignes.size() && estUneLigneDeCorps(lignes.get(corps))) {
                    lignesDeCorps++;
                    List<String> cellules = cellules(lignes.get(corps));
                    if (cellules.size() > enTete) {
                        String invisible = String.join(" | ",
                                cellules.subList(enTete, cellules.size())).strip();
                        surplus.add(new LigneEnSurplus(relatif, corps + 1, cellules.size(), enTete,
                                invisible.length() > 120 ? invisible.substring(0, 120) + "..." : invisible,
                                lignes.get(corps)));
                    }
                    corps++;
                }
                position = corps;
            }
        }
        return new Balayage(fichiers.size(), tableaux, lignesDeCorps, List.copyOf(surplus));
    }

    static void verifierQueRienNEstInvisible(Path racine, Set<String> dette) {
        Balayage balayage = balayer(racine);
        assertThat(balayage.tableaux())
                .as("ADR-0051 : sans tableau markdown sous %s, ce garde-fou n'examine aucun objet",
                        racine)
                .isPositive();

        Set<String> defauts = new TreeSet<>(balayage.surplus().stream()
                .filter(ligne -> dette.stream().noneMatch(nom -> ligne.texte().contains(nom)))
                .map(LigneEnSurplus::defaut)
                .toList());
        assertThat(defauts)
                .as("ADR-0087 : aucune ligne de tableau ne porte plus de cellules que son en-tete")
                .isEmpty();
    }

    /** La clause vivante, sur le depot reel, dette exemptee. */
    @Test
    void leDepotReelNAffichePasMoinsQuIlNEcrit() {
        verifierQueRienNEstInvisible(LesGardeFousSontMisALEpreuveTest.racineDuDepot(),
                DETTE_DE_CELLULES);
    }

    /**
     * Le cliquet, en egalite et non en plancher (ADR-0055, ADR-0063, ADR-0067) : rouge si une ligne
     * en surplus s'ajoute, rouge si un nom de la dette ne designe plus rien, rouge si le plafond
     * cesse de valoir la taille de la dette.
     *
     * <p><b>Ce qu'il examine depuis que la dette est payee, dit plutot que suppose (ADR-0051)</b> :
     * la moitie "une exemption ne pourrit pas" porte sur <b>zero objet</b>, la dette etant vide, et
     * elle y restera tant qu'aucune exemption ne sera ecrite. Ce qui continue de mordre est
     * l'egalite elle-meme - <b>le depot porte exactement zero ligne en surplus</b> -, et c'est la
     * clause la plus forte des trois : sans exemption possible, la premiere ligne invisible qui
     * entre est rouge.
     *
     * <p><b>Un defaut de sa premiere ecriture, trouve par {@code sofia-sm} en payant la dette</b> :
     * le message prescrivait {@code PLAFOND_DE_DETTE - 1}, calcule nom par nom dans une boucle qui
     * s'arretait au premier echec. Quand les deux noms tombent ensemble - ce qui est arrive - il
     * prescrivait 1 la ou la reponse etait 0. L'assertion porte desormais sur l'ensemble des noms
     * encore vivants, donc la valeur prescrite est juste quel que soit le nombre de noms qui
     * tombent.
     */
    @Test
    void laDetteNommeExactementLesLignesEnSurplusQuiRestent() {
        Balayage balayage = balayer(LesGardeFousSontMisALEpreuveTest.racineDuDepot());

        assertThat(DETTE_DE_CELLULES)
                .as("ADR-0013 : le plafond vaut exactement la taille de la dette, sans une place de"
                        + " libre")
                .hasSize(PLAFOND_DE_DETTE);

        Set<String> vivants = new TreeSet<>(DETTE_DE_CELLULES.stream()
                .filter(nom -> balayage.surplus().stream()
                        .anyMatch(ligne -> ligne.texte().contains(nom)))
                .toList());
        assertThat(vivants)
                .as("ADR-0087 : chaque nom de DETTE_DE_CELLULES designe encore une ligne en surplus"
                        + " reelle. Retire les noms manquants ci-dessous et ecris PLAFOND_DE_DETTE ="
                        + " %d dans le meme diff - une exemption qui ne nomme plus rien de reel"
                        + " n'est pas un cliquet", vivants.size())
                .containsExactlyElementsOf(new TreeSet<>(DETTE_DE_CELLULES));

        assertThat(balayage.surplus())
                .as("ADR-0087 : le depot porte exactement les %d lignes en surplus de la dette",
                        PLAFOND_DE_DETTE)
                .hasSize(PLAFOND_DE_DETTE);
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Un vert obtenu sur
     * un balayage qui ne lit plus rien est le faux vert que ce depot combat depuis ADR-0013.
     */
    @Test
    void leBalayageLitLesTableauxReelsDuDepot() {
        Balayage balayage = balayer(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        assertThat(balayage.fichiers())
                .as("ADR-0087 : le depot porte des centaines de fichiers markdown")
                .isGreaterThan(300);
        assertThat(balayage.tableaux())
                .as("ADR-0087 : le depot porte des milliers de tableaux ; un balayage qui en"
                        + " trouverait une poignee ne lit pas ce qu'il croit lire")
                .isGreaterThan(1_000);
        assertThat(balayage.lignes())
                .as("ADR-0087 : et des milliers de lignes de corps")
                .isGreaterThan(10_000);
    }

    /**
     * ADR-0013 obligation 3, sur du materiau reel plutot que sur une fixture de ma main - la lecon de
     * s05. Le contre-exemple est l'extrait de {@code MATRICE-couverture.md} a {@code 7a3c8cc8},
     * recopie octet pour octet : l'en-tete de sept colonnes et les deux lignes de huit cellules
     * telles que {@code nadia-prof} les a trouvees, avant la correction de {@code camille-po}. La
     * preuve survit donc a cette correction.
     */
    @Test
    void lesDeuxLignesReellesDeLaMatriceFontEchouerLaRegle() {
        assertThatThrownBy(() -> verifierQueRienNEstInvisible(CONTRE_EXEMPLE, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("extrait-matrice.md:14 porte 8 cellules pour un en-tete de 7")
                .hasMessageContaining("extrait-matrice.md:15 porte 8 cellules pour un en-tete de 7")
                .hasMessageContaining("Reaudit du 2026-09-07");
    }

    /**
     * <b>Une exemption est une exemption et non un perimetre</b> : nomme, un defaut passe ; pas
     * nomme, il echoue. Sans cette clause, un vert de la clause vivante ne dirait pas si le balayage
     * a lu les lignes exemptees ou s'il les a manquees.
     *
     * <p><b>La preuve est le couple de cette clause et de
     * {@link #lesDeuxLignesReellesDeLaMatriceFontEchouerLaRegle()}</b>, sur le <b>meme</b> materiau
     * et avec deux jeux de noms : vide, il est rouge en nommant les deux lignes ; avec les deux
     * noms, il est vert. Aucune des deux moities ne passe si le mecanisme casse - que l'argument
     * d'exemption cesse d'etre honore (cette moitie-ci rougit), que le critere cesse de voir un
     * surplus (l'autre rougit), ou que le materiau cesse de porter le defaut (l'autre rougit aussi).
     * C'est ce couple qui rend la clause mordante, et non l'origine de ses noms.
     *
     * <p><b>Ses noms ne viennent donc plus de la dette vivante</b> (voir
     * {@link #NOMS_DU_CONTRE_EXEMPLE}), et les assertions ci-dessous interdisent qu'ils
     * pourrissent : chacun doit designer une ligne en surplus reelle de l'extrait.
     */
    @Test
    void lExemptionEstUneExemptionEtNonUnPerimetre() {
        Balayage fautif = balayer(CONTRE_EXEMPLE);

        assertThat(fautif.surplus())
                .as("ADR-0087 : l'extrait de l'historique porte exactement %d ligne(s) en surplus,"
                        + " une par nom ; s'il n'en porte plus, cette preuve ne prouve rien",
                        NOMS_DU_CONTRE_EXEMPLE.size())
                .hasSize(NOMS_DU_CONTRE_EXEMPLE.size());
        for (String nom : new TreeSet<>(NOMS_DU_CONTRE_EXEMPLE)) {
            assertThat(fautif.surplus().stream()
                    .filter(ligne -> ligne.texte().contains(nom)).toList())
                    .as("ADR-0087 : NOMS_DU_CONTRE_EXEMPLE nomme \"%s\", qui ne designe aucune"
                            + " ligne en surplus de l'extrait", nom)
                    .isNotEmpty();
        }

        verifierQueRienNEstInvisible(CONTRE_EXEMPLE, NOMS_DU_CONTRE_EXEMPLE);
    }
}
