package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.Matiere;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static com.recreo.adapter.content.UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest.replie;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0106 : la donnee de matiere porte une ligne par matiere servie et rien d'autre, chaque valeur
 * qu'une vue lira y est unique et de forme fixe, et aucune teinte n'est un gris.
 *
 * <p><b>Ce que ce garde-fou interdit reellement.</b> Une matiere neuve au catalogue sans ligne (elle
 * serait servie sans libelle arbitre, APM-1) ; une ligne qui survit au retrait de sa matiere ; une ligne
 * recopiee sans son accent, qui ne joindrait plus rien ; deux tuiles sous le meme segment d'adresse ; un
 * libelle court recopie sans accent ; la teinte grise qu'`ines-design` avait proposee pour l'emplacement
 * 10 et que `nadia-prof` a refusee (APM-6), posee sur n'importe quelle autre ligne.
 *
 * <p><b>Perimetre derive.</b> Les matieres servies et les lignes viennent du chargeur reel, la chaine
 * que l'enfant traverse ; aucune matiere n'est ecrite ici. Les morsures mutent ces valeurs reelles en
 * memoire.
 *
 * <p><b>Le gris, et ce que le seuil ne pretend pas.</b> Un gris est une teinte de saturation TSL
 * inferieure a {@link #SATURATION_MINIMALE}, sur le fond ou sur le contour. Mesure sur les onze paires
 * proposees : la paire refusee vaut 0,18 et 0,18 ; la plus faible des dix paires acceptees vaut 0,47 sur
 * le fond et 0,37 sur le contour. Le seuil refuse un gris ; il ne dit pas qu'une teinte saturee ne se lit
 * pas "desactive" : `nadia-prof` le juge en jouant.
 */
class ChaqueMatiereServieASaLigneDeMatiereTest {

    /** Entre la paire refusee (0,18) et la plus faible des paires acceptees (0,37). */
    static final double SATURATION_MINIMALE = 0.25;

    /**
     * APM-6 : la teinte de Histoire des arts est refusee et attend la proposition d'`ines-design`. Cliquet
     * exact : chaque nom doit designer une ligne encore grise, et aucune autre ligne grise n'est admise.
     */
    static final Set<String> TEINTES_GRISES_EN_ATTENTE = Set.of();

    private static final Pattern SEGMENT = Pattern.compile("[a-z0-9]+(-[a-z0-9]+)*");
    private static final Pattern PICTOGRAMME = Pattern.compile("[a-z0-9]+(-[a-z0-9]+)*\\.svg");
    private static final Pattern TEINTE = Pattern.compile("#[0-9a-f]{6}");

    private static final YamlContentPackLoader CHARGEUR = new YamlContentPackLoader();

    static List<String> matieresServies(List<GameDefinition> jeux) {
        return jeux.stream().map(jeu -> jeu.descripteur().matiere()).distinct().toList();
    }

    static double saturation(String teinte) {
        double rouge = Integer.parseInt(teinte.substring(1, 3), 16) / 255.0;
        double vert = Integer.parseInt(teinte.substring(3, 5), 16) / 255.0;
        double bleu = Integer.parseInt(teinte.substring(5, 7), 16) / 255.0;
        double max = Math.max(rouge, Math.max(vert, bleu));
        double min = Math.min(rouge, Math.min(vert, bleu));
        double luminosite = (max + min) / 2;
        return max == min ? 0 : (max - min) / (1 - Math.abs(2 * luminosite - 1));
    }

    static boolean estGrise(Matiere ligne) {
        return saturation(ligne.teinteFond()) < SATURATION_MINIMALE
                || saturation(ligne.teinteContour()) < SATURATION_MINIMALE;
    }

    /** Le juge, pur : il ne lit rien. */
    static List<String> griefs(List<String> servies, List<Matiere> lignes, Set<String> grisesEnAttente) {
        List<String> griefs = new ArrayList<>();
        for (String matiere : servies) {
            long exactes = lignes.stream().filter(ligne -> ligne.nom().equals(matiere)).count();
            if (exactes > 1) {
                griefs.add("la matiere servie \"" + matiere + "\" a " + exactes + " lignes");
            }
            if (exactes == 0) {
                String voisine = lignes.stream().map(Matiere::nom)
                        .filter(nom -> replie(nom).equals(replie(matiere))).findFirst().orElse(null);
                griefs.add(voisine == null
                        ? "la matiere servie \"" + matiere + "\" n'a aucune ligne : elle n'est pas servie"
                                + " sous un libelle de repli, elle revient devant nadia-prof (APM-1)"
                        : "la matiere servie \"" + matiere + "\" n'a aucune ligne ; la ligne \"" + voisine
                                + "\" ne s'en distingue que par l'accent, la casse ou un blanc (repliage"
                                + " d'ADR-0049) et ne joint rien");
            }
        }
        for (Matiere ligne : lignes) {
            if (!servies.contains(ligne.nom())) {
                griefs.add("la ligne \"" + ligne.nom() + "\" ne nomme aucune matiere servie");
            }
            for (String matiere : servies) {
                if (replie(ligne.libelle()).equals(replie(matiere)) && !ligne.libelle().equals(matiere)) {
                    griefs.add("le libelle \"" + ligne.libelle() + "\" est la matiere \"" + matiere
                            + "\" privee de son accent, de sa casse ou de ses blancs");
                }
            }
            verifierLaForme(griefs, ligne, "segment", ligne.segment(), SEGMENT);
            verifierLaForme(griefs, ligne, "pictogramme", ligne.pictogramme(), PICTOGRAMME);
            boolean teintesBienFormees = verifierLaForme(griefs, ligne, "teinteFond", ligne.teinteFond(), TEINTE)
                    & verifierLaForme(griefs, ligne, "teinteContour", ligne.teinteContour(), TEINTE);
            if (teintesBienFormees && estGrise(ligne) && !grisesEnAttente.contains(ligne.nom())) {
                griefs.add("la ligne \"" + ligne.nom() + "\" porte une teinte grise (" + ligne.teinteFond()
                        + ", " + ligne.teinteContour() + ") : APM-6 refuse un gris parmi des couleurs");
            }
        }
        verifierLUnicite(griefs, lignes, "libelle", Matiere::libelle);
        verifierLUnicite(griefs, lignes, "pictogramme", Matiere::pictogramme);
        verifierLUnicite(griefs, lignes, "segment", Matiere::segment);
        for (String enAttente : grisesEnAttente) {
            boolean encoreGrise = lignes.stream()
                    .anyMatch(ligne -> ligne.nom().equals(enAttente) && estGrise(ligne));
            if (!encoreGrise) {
                griefs.add("l'exemption de teinte grise \"" + enAttente + "\" ne designe plus une ligne"
                        + " grise : retire-la de TEINTES_GRISES_EN_ATTENTE dans le meme commit");
            }
        }
        return griefs;
    }

    private static boolean verifierLaForme(List<String> griefs, Matiere ligne, String champ, String valeur,
                                           Pattern forme) {
        if (forme.matcher(valeur).matches()) {
            return true;
        }
        griefs.add("la ligne \"" + ligne.nom() + "\" porte " + champ + " \"" + valeur
                + "\", hors de la forme " + forme.pattern());
        return false;
    }

    private static void verifierLUnicite(List<String> griefs, List<Matiere> lignes, String champ,
                                         java.util.function.Function<Matiere, String> valeur) {
        Map<String, Integer> occurrences = new LinkedHashMap<>();
        lignes.forEach(ligne -> occurrences.merge(valeur.apply(ligne), 1, Integer::sum));
        occurrences.forEach((texte, nombre) -> {
            if (nombre > 1) {
                griefs.add(champ + " \"" + texte + "\" porte par " + nombre + " lignes");
            }
        });
    }

    private static List<String> servies() {
        return matieresServies(CHARGEUR.games());
    }

    private static List<Matiere> lignes() {
        return new ArrayList<>(CHARGEUR.matieres());
    }

    private static Matiere avec(Matiere ligne, String libelle, String teinteFond, String teinteContour,
                                String segment) {
        return new Matiere(ligne.nom(), libelle, ligne.pictogramme(), teinteFond, teinteContour, segment);
    }

    private static Matiere ligneDe(List<Matiere> lignes, String nom) {
        return lignes.stream().filter(ligne -> ligne.nom().equals(nom)).findFirst().orElseThrow();
    }

    @Test
    void laDonneeDeMatiereServieTientToutesSesClauses() {
        List<String> servies = servies();
        List<Matiere> lignes = lignes();

        assertThat(servies).as("ADR-0003 : ce garde-fou ne joint rien sur moins de deux matieres").hasSizeGreaterThan(1);
        assertThat(lignes).as("ADR-0003 : ce garde-fou ne juge rien sans ligne").hasSizeGreaterThan(1);
        assertThat(griefs(servies, lignes, TEINTES_GRISES_EN_ATTENTE)).as("ADR-0106").isEmpty();
    }

    /**
     * N5 de nadia-prof (2026-10-11) : l'ordre est une decision pedagogique, pas un detail du fichier.
     * Le reordonner, ou le trier en passant, coute ce diff visible et un arbitrage, comme la sequence des
     * niveaux (LOrdreDesNiveauxResteUneDonneeTest, DEC-0090).
     */
    @Test
    void lOrdreDesLignesEstCeluiQueNadiaProfATrancheEnN5() {
        assertThat(lignes()).extracting(Matiere::nom).as("APM-4, N5").containsExactly(
                "Français", "Mathématiques", "Questionner le monde", "Sciences et technologie",
                "Histoire-géographie", "Enseignement moral et civique", "Langues vivantes (anglais)",
                "Arts plastiques", "Éducation musicale", "Histoire des arts", "Éducation physique et sportive");
    }

    @Test
    void uneMatiereServiePriveeDeSaLigneFaitEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        Matiere retiree = lignes.remove(lignes.size() - 1);

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("\"" + retiree.nom() + "\" n'a aucune ligne")
                        && grief.contains("APM-1"));
    }

    @Test
    void uneLigneQuiNeNommeAucuneMatiereServieFaitEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        lignes.add(new Matiere("Philosophie", "Philosophie", "philosophie.svg", "#cfe3f6", "#2f6c9f",
                "philosophie"));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("\"Philosophie\" ne nomme aucune matiere servie"));
    }

    /** La ligne d'une matiere servie reelle, recopiee sans son accent : le repliage nomme la voisine. */
    @Test
    void uneLigneRecopieeSansSonAccentEstNommeeParLeRepliage() {
        List<Matiere> lignes = lignes();
        Matiere accentuee = lignes.stream()
                .filter(ligne -> !ligne.nom().equals(sansAccent(ligne.nom())))
                .findFirst().orElseThrow();
        String ascii = sansAccent(accentuee.nom());
        lignes.set(lignes.indexOf(accentuee), new Matiere(ascii, accentuee.libelle(), accentuee.pictogramme(),
                accentuee.teinteFond(), accentuee.teinteContour(), accentuee.segment()));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("\"" + accentuee.nom() + "\" n'a aucune ligne ; la ligne \""
                        + ascii + "\"") && grief.contains("ADR-0049"));
    }

    @Test
    void unLibelleRecopieSansSonAccentFaitEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        Matiere accentuee = lignes.stream()
                .filter(ligne -> !ligne.libelle().equals(sansAccent(ligne.libelle())))
                .findFirst().orElseThrow(() -> new AssertionError("aucun libelle accentue dans la donnee"));
        String ascii = sansAccent(accentuee.libelle());
        lignes.set(lignes.indexOf(accentuee), avec(accentuee, ascii, accentuee.teinteFond(),
                accentuee.teinteContour(), accentuee.segment()));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("le libelle \"" + ascii + "\""));
    }

    @Test
    void deuxLignesSousLeMemeSegmentFontEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        Matiere seconde = lignes.get(1);
        lignes.set(1, avec(seconde, seconde.libelle(), seconde.teinteFond(), seconde.teinteContour(),
                lignes.get(0).segment()));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("segment \"" + lignes.get(0).segment() + "\" porte par 2"));
    }

    /** ADR-0049 point 4 : le segment est une donnee sans accent, jamais la matiere elle-meme. */
    @Test
    void unSegmentQuiPorteUnAccentFaitEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        Matiere premiere = lignes.get(0);
        lignes.set(0, avec(premiere, premiere.libelle(), premiere.teinteFond(), premiere.teinteContour(),
                premiere.nom()));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("porte segment \"" + premiere.nom() + "\""));
    }

    /**
     * APM-6, la paire grise que nadia-prof a refusee pour Histoire des arts (N6, 2026-10-11), ecrite ici
     * parce qu'aucune ligne de la donnee n'est plus grise depuis le magenta du rejugement : c'est le
     * materiau de la morsure, pas une valeur servie.
     */
    private static final String GRIS_REFUSE_FOND = "#d8dde4";
    private static final String GRIS_REFUSE_CONTOUR = "#4a5a6b";

    /** APM-6 : la paire grise refusee, posee sur la premiere ligne, est refusee. */
    @Test
    void laTeinteGriseRefuseePoseeSurUneLigneFaitEchouerLeGardeFou() {
        List<Matiere> lignes = lignes();
        Matiere premiere = lignes.get(0);
        lignes.set(0, avec(premiere, premiere.libelle(), GRIS_REFUSE_FOND, GRIS_REFUSE_CONTOUR,
                premiere.segment()));

        assertThat(griefs(servies(), lignes, TEINTES_GRISES_EN_ATTENTE))
                .anyMatch(grief -> grief.contains("\"" + premiere.nom() + "\" porte une teinte grise"));
    }

    /** Le cliquet reste exact : une ligne grise exemptee par son nom est acceptee, une autre ne l'est pas. */
    @Test
    void uneExemptionNeCouvreQueLaLigneQuElleNomme() {
        List<Matiere> lignes = lignes();
        Matiere grise = ligneDe(lignes, "Histoire des arts");
        lignes.set(lignes.indexOf(grise), avec(grise, grise.libelle(), GRIS_REFUSE_FOND, GRIS_REFUSE_CONTOUR,
                grise.segment()));

        assertThat(griefs(servies(), lignes, Set.of("Histoire des arts"))).isEmpty();
        assertThat(griefs(servies(), lignes, Set.of()))
                .anyMatch(grief -> grief.contains("\"Histoire des arts\" porte une teinte grise"));
    }

    /** La donnee servie ne porte plus aucun gris : le cliquet est vide, et la teinte de Histoire des arts n'est pas grise. */
    @Test
    void aucuneLigneServieNEstGriseEtLeCliquetEstVide() {
        assertThat(TEINTES_GRISES_EN_ATTENTE).isEmpty();
        assertThat(griefs(servies(), lignes(), Set.of())).isEmpty();
    }

    /** Le cliquet ne survit pas a sa dette : une exemption qui ne designe plus une ligne grise rend le garde-fou rouge. */
    @Test
    void uneExemptionQuiNeDesignePlusUnGrisFaitEchouerLeGardeFou() {
        assertThat(griefs(servies(), lignes(), Set.of("Histoire des arts")))
                .anyMatch(grief -> grief.contains("ne designe plus une ligne grise"));
    }

    private static String sansAccent(String texte) {
        return java.text.Normalizer.normalize(texte, java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }
}
