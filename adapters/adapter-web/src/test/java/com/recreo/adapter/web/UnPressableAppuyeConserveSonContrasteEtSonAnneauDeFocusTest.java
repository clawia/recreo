package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S17-D (nadia-prof, sonde clavier+pointeur reelle, s17) : un troisieme trou dans la derivation
 * d'ADR-0046, de la meme famille que les deux amendements de s16 - elle verifie qu'un etat est
 * <b>declare</b> par la feuille maison, jamais qu'il l'<b>emporte</b> dans la cascade. La feuille
 * vendorisee porte deux selecteurs d'appui a specificite (0,3,0)
 * ({@code .btn:first-child:active}, {@code :not(.btn-check)+.btn:active}) et deux selecteurs pour
 * l'appui combine au focus clavier a (0,4,0) ({@code .btn:first-child:active:focus-visible},
 * {@code :not(.btn-check)+.btn:active:focus-visible}) - plus hauts qu'une regle maison sur un
 * sujet seul ((0,2,0) ou (0,3,0)), donc l'ordre de chargement (ADR-0014 point 5) ne suffit plus.
 * Mesure par {@code nadia-prof} en pilotant un navigateur (touches reelles, appui reel) : la puce
 * et la sortie d'en-tete tombent de 9,25:1 a 1,64:1 sous le doigt, {@code .demarrer} perd son
 * aplat, et l'anneau de focus pose par {@code S17-1} disparait quand un bouton est focalise
 * <b>et</b> presse a la fois.
 *
 * <p><b>Ce que ce fichier corrige, et ce qu'il ne decide pas.</b> Le correctif ajoute un ancetre
 * reel au sujet ({@code #jeu} ou {@code .entete-contenu}, deja l'ancetre de chaque pressable
 * concerne) plutot qu'une classe au sujet lui-meme : un premier essai de ce lot ajoutait
 * {@code .btn} au sujet et cassait silencieusement la derivation d'ADR-0046 - {@code "btn"}
 * devenait une classe connue de la feuille maison, et l'identite calculee de <b>chaque</b> bouton
 * reel en changeait, pas seulement celle visee (mesure sur ce meme lot, revert avant ce commit).
 * Un ancetre separe par un combinateur est invisible a cette derivation ({@code sujetDuSelecteur}
 * n'en lit que le dernier compose) - la meme forme que {@code body:has(.filtre-niveau)} deja
 * ecrite dans la feuille pour la puce active. <b>La correction de la derivation elle-meme (lui
 * apprendre a comparer une specificite) reste a {@code theo-archi}</b>, route separement ; ce
 * fichier ne fait que prouver que la feuille de production gagne desormais la cascade sur les
 * selecteurs precis que {@code nadia-prof} a mesures.
 *
 * <p><b>La specificite n'est pas calculee en general ici.</b> {@link Specificite} et
 * {@link #specificite} ne lisent que trois colonnes (identifiants, puis classes/pseudo-classes/
 * attributs, puis types/pseudo-elements), aucun style en ligne, {@code :not()}/{@code :has()}
 * traites par la specificite de leur argument (CSS Selectors Level 4) et rien de plus - pas un
 * moteur de cascade, seulement de quoi comparer les selecteurs precis nommes ci-dessus a ceux que
 * ce lot ecrit (l'identifiant {@code #jeu} y entre pour cette seule raison : {@code #jeu
 * .demarrer:active} y gagne sa cascade par un identifiant reel, jamais par une classe ajoutee au
 * sujet). Que ADR-0046 doive un jour calculer une specificite pour toute regle du depot est une
 * question pour {@code theo-archi}, pas tranchee par ce fichier.
 */
class UnPressableAppuyeConserveSonContrasteEtSonAnneauDeFocusTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of(
            "src/test/resources/contreexemple/UnPressableAppuyeConserveSonContrasteEtSonAnneauDeFocusTest");

    /** Les deux selecteurs tiers d'appui simple, mesures par nadia-prof sur la feuille vendorisee
     * reelle - jamais recopies dans une fixture, lus a chaque execution (ADR-0046, "la moitie
     * tierce n'est jamais une fixture de ma main"). */
    private static final String TIERS_ACTIF_1 = ".btn:first-child:active";
    private static final String TIERS_ACTIF_2 = ":not(.btn-check)+.btn:active";

    /** Les deux selecteurs tiers pour l'appui combine au focus clavier - meme feuille, meme
     * methode. */
    private static final String TIERS_ACTIF_FOCUS_1 = ".btn:first-child:active:focus-visible";
    private static final String TIERS_ACTIF_FOCUS_2 = ":not(.btn-check)+.btn:active:focus-visible";

    /**
     * Specificite restreinte a trois colonnes : {@code ids} pour les identifiants,
     * {@code classes} pour les classes, attributs et pseudo-classes, {@code elements} pour les
     * selecteurs de type et les pseudo-elements. La comparaison respecte l'ordre reel des
     * colonnes CSS (identifiants avant classes avant types) ; a classes et identifiants egaux, la
     * regle la plus proche du chargement de la feuille maison l'emporte deja par construction
     * (ADR-0014 point 5), d'ou l'egalite acceptee ici.
     */
    private record Specificite(int ids, int classes, int elements) {
        boolean auMoinsAussiForteQue(Specificite tiers) {
            if (ids != tiers.ids()) {
                return ids > tiers.ids();
            }
            return classes != tiers.classes() ? classes > tiers.classes() : elements >= tiers.elements();
        }

        Specificite plus(Specificite autre) {
            return new Specificite(ids + autre.ids(), classes + autre.classes(), elements + autre.elements());
        }
    }

    /**
     * {@code :not(X)} et {@code :has(X)} : leur specificite est remplacee par celle de leur
     * argument, jamais additionnee (CSS Selectors Level 4). Les deux seuls cas rencontres ici ne
     * s'imbriquent pas.
     */
    private static String sansNotNiHas(String compose) {
        return compose.replaceAll(":(not|has)\\(([^)]*)\\)", "$2");
    }

    /** Un compose complet, un ancetre ou un sujet, decoupe en sa part de type et sa part de
     * classes/pseudo-classes. */
    private static Specificite specificiteDuCompose(String compose) {
        String defonce = sansNotNiHas(compose);
        int debutSujet = 0;
        while (debutSujet < defonce.length()
                && defonce.charAt(debutSujet) != '.' && defonce.charAt(debutSujet) != ':') {
            debutSujet++;
        }
        String tete = defonce.substring(0, debutSujet);
        int ids = tete.startsWith("#") ? 1 : 0;
        int elements = (!tete.isEmpty() && ids == 0) ? 1 : 0;
        int classes = 0;
        int position = debutSujet;
        while (position < defonce.length()) {
            char caractere = defonce.charAt(position);
            if (caractere == '.' || caractere == ':') {
                classes++;
                position++;
                while (position < defonce.length()
                        && defonce.charAt(position) != '.' && defonce.charAt(position) != ':') {
                    position++;
                }
            } else {
                position++;
            }
        }
        return new Specificite(ids, classes, elements);
    }

    /**
     * Decoupe un selecteur complexe en composes, aux memes combinateurs et a la meme regle de
     * profondeur de parentheses que {@link TailleDesTextesEtBoutonsTest#sujetDuSelecteur} - mais
     * garde tous les composes plutot que le dernier seul : la specificite totale d'un selecteur
     * complexe est la somme de celle de chacun de ses composes, ancetres compris.
     */
    private static List<String> composes(String selecteurComplet) {
        List<String> composes = new ArrayList<>();
        int profondeur = 0;
        int debut = 0;
        for (int position = 0; position < selecteurComplet.length(); position++) {
            char caractere = selecteurComplet.charAt(position);
            if (caractere == '(') {
                profondeur++;
            } else if (caractere == ')') {
                profondeur--;
            } else if (profondeur == 0 && (Character.isWhitespace(caractere)
                    || caractere == '>' || caractere == '+' || caractere == '~')) {
                if (position > debut) {
                    composes.add(selecteurComplet.substring(debut, position).strip());
                }
                debut = position + 1;
            }
        }
        if (debut < selecteurComplet.length()) {
            composes.add(selecteurComplet.substring(debut).strip());
        }
        return composes.stream().filter(s -> !s.isBlank()).toList();
    }

    private static Specificite specificite(String selecteurComplet) {
        Specificite totale = new Specificite(0, 0, 0);
        for (String compose : composes(selecteurComplet)) {
            totale = totale.plus(specificiteDuCompose(compose));
        }
        return totale;
    }

    /** Le plus fort des deux selecteurs tiers nommes : celui qu'une regle maison doit egaler ou
     * depasser pour gagner la cascade sur les deux, quel que soit celui qui s'applique. */
    private static Specificite plancherTiers(Map<String, String> tiers, String selecteur1, String selecteur2) {
        assertThat(tiers)
                .as("le selecteur tiers mesure par nadia-prof doit exister dans la feuille vendorisee "
                        + "reelle, sans quoi ce plancher ne decrit plus rien")
                .containsKeys(selecteur1, selecteur2);
        Specificite s1 = specificite(selecteur1);
        Specificite s2 = specificite(selecteur2);
        return s1.auMoinsAussiForteQue(s2) ? s1 : s2;
    }

    /** Une identite de pressable et les deux selecteurs maison qui doivent tenir le plancher :
     * l'appui simple (contraste) et l'appui combine au focus clavier (anneau). */
    private record Identite(String nom, String selecteurActif, String selecteurActifFocus) {
    }

    /**
     * La population que {@code nadia-prof} a mesuree comme perdante - les memes identites que
     * S17-1 a dotees d'un anneau, moins la puce active dont la specificite propre
     * ({@code body:has(.filtre-niveau) .chip-niveau.est-actif...}) depasse deja le plancher sans
     * aucune regle de plus (verifie a part, {@link #laPuceActiveDepasseDejaLePlancherSansRegleDePlus}).
     */
    private static final List<Identite> POPULATION = List.of(
            new Identite("continuer-partie",
                    "#jeu .continuer-partie:active", "#jeu .continuer-partie:active:focus-visible"),
            new Identite("demarrer",
                    "#jeu .demarrer:active", "#jeu .demarrer:active:focus-visible"),
            new Identite("proposition",
                    "#jeu .proposition:active", "#jeu .proposition:active:focus-visible"),
            new Identite("recommencer",
                    "#jeu .recommencer:active", "#jeu .recommencer:active:focus-visible"),
            new Identite("sortie-pleine",
                    "#jeu .sortie-pleine:active", "#jeu .sortie-pleine:active:focus-visible"),
            new Identite("retour-accueil",
                    "#jeu .retour-accueil:active", "#jeu .retour-accueil:active:focus-visible"),
            new Identite("lien-accueil",
                    ".entete-contenu .lien-accueil:active", ".entete-contenu .lien-accueil:active:focus-visible"),
            new Identite("chip-niveau",
                    ".entete-contenu .chip-niveau:active", ".entete-contenu .chip-niveau:active:focus-visible"),
            new Identite("proposition.deja-essayee",
                    ".proposition.deja-essayee:active", ".proposition.deja-essayee:active:focus-visible")
    );

    private static void verifierUnSelecteur(
            Map<String, String> maison, String nom, String selecteur, Specificite plancher,
            List<String> insuffisants) {
        String corps = maison.get(selecteur);
        if (corps == null) {
            insuffisants.add(nom + " : aucune regle maison pour \"" + selecteur + "\"");
            return;
        }
        Specificite mesuree = specificite(selecteur);
        if (!mesuree.auMoinsAussiForteQue(plancher)) {
            insuffisants.add(String.format(
                    "%s : \"%s\" vaut (ids=%d, classes=%d, elements=%d) contre le plancher "
                            + "tiers (ids=%d, classes=%d, elements=%d)",
                    nom, selecteur, mesuree.ids(), mesuree.classes(), mesuree.elements(),
                    plancher.ids(), plancher.classes(), plancher.elements()));
        }
    }

    static void verifierQueChaquePressablePresseGardeSaSpecificite(
            Path racineMaison, Path racineTiers, List<Identite> population) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racineMaison);
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(racineTiers);

        assertThat(population)
                .as("population vide : ce garde-fou ne mesurerait rien")
                .isNotEmpty();

        Specificite plancherActif = plancherTiers(tiers, TIERS_ACTIF_1, TIERS_ACTIF_2);
        Specificite plancherActifFocus = plancherTiers(tiers, TIERS_ACTIF_FOCUS_1, TIERS_ACTIF_FOCUS_2);

        List<String> insuffisants = new ArrayList<>();
        for (Identite identite : population) {
            verifierUnSelecteur(maison, identite.nom(), identite.selecteurActif(), plancherActif, insuffisants);
            verifierUnSelecteur(
                    maison, identite.nom(), identite.selecteurActifFocus(), plancherActifFocus, insuffisants);
        }

        assertThat(insuffisants)
                .as("un pressable presse doit garder son contraste et son anneau de focus : sa "
                        + "specificite doit au moins egaler celle des selecteurs tiers d'appui "
                        + "mesures par nadia-prof")
                .isEmpty();
    }

    @Test
    void chaquePressablePresseGardeSonContrasteEtSonAnneauDeFocus() {
        verifierQueChaquePressablePresseGardeSaSpecificite(RACINE_PRODUCTION, RACINE_PRODUCTION, POPULATION);
    }

    /**
     * La puce active n'a recu aucune regle de plus dans ce lot : son propre selecteur,
     * {@code body:has(.filtre-niveau) .chip-niveau.est-actif:focus-visible}, porte deja un
     * selecteur de type ({@code body}) qui la place au-dessus du plancher tiers combine sans
     * ajout. Verifie ici pour que le motif ecrit dans {@code recreo.css} soit prouve, pas affirme.
     */
    @Test
    void laPuceActiveDepasseDejaLePlancherSansRegleDePlus() {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(RACINE_PRODUCTION);
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(RACINE_PRODUCTION);
        String selecteur = "body:has(.filtre-niveau) .chip-niveau.est-actif:focus-visible";

        assertThat(maison).as("la regle de l'anneau de la puce active doit exister").containsKey(selecteur);
        Specificite plancher = plancherTiers(tiers, TIERS_ACTIF_FOCUS_1, TIERS_ACTIF_FOCUS_2);
        assertThat(specificite(selecteur).auMoinsAussiForteQue(plancher))
                .as("la puce active devrait deja depasser le plancher tiers combine sans regle "
                        + "supplementaire")
                .isTrue();
        assertThat(maison)
                .as("aucune regle supplementaire n'est due ici : une regle honoree qui n'ajoute "
                        + "rien ne se justifie pas")
                .doesNotContainKey("body:has(.filtre-niveau) .chip-niveau.est-actif:active:focus-visible");
    }

    /**
     * Preuve de morsure sans passer par un jeu fautif sur disque : la forme d'avant ce lot
     * ({@code .chip-niveau:active}, sans ancetre) est exactement le defaut que nadia-prof a
     * mesure, et ce meme calcul de specificite l'aurait refusee.
     */
    @Test
    void laFormeDavantCeLotNAuraitPasSuffiAuMemeCalcul() {
        Map<String, String> tiers = TailleDesTextesEtBoutonsTest.blocsTiers(RACINE_PRODUCTION);
        Specificite plancher = plancherTiers(tiers, TIERS_ACTIF_1, TIERS_ACTIF_2);
        assertThat(specificite(".chip-niveau:active").auMoinsAussiForteQue(plancher))
                .as("la forme sans ancetre valait (0,2,0) contre (0,3,0) tiers : elle ne devrait "
                        + "pas suffire")
                .isFalse();
    }

    /**
     * ADR-0013 obligation 3 : un pressable neuf, absent de tout gabarit de production, dont
     * {@code :active} est declare sur le sujet seul - exactement la forme que ce lot remplace.
     */
    @Test
    void unPressableSansAncetreFaitEchouerLeGardeFou() {
        List<Identite> populationDuJeuFautif = List.of(
                new Identite("bouton-neuf", ".bouton-neuf:active", ".bouton-neuf:active"));
        assertThatThrownBy(() -> verifierQueChaquePressablePresseGardeSaSpecificite(
                CONTRE_EXEMPLES.resolve("sans-ancetre"), RACINE_PRODUCTION, populationDuJeuFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("bouton-neuf");
    }
}
