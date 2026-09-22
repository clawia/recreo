package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S39-DEN (MAT-CP-01) : le paquet "cp-nombres-jusqu-a-100", mathematiques CP. Arbitrage prealable
 * de nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md,
 * section 1.2 ; reconduit sans amendement le 2026-08-30 et le 2026-09-22), DEN-1 a DEN-8. Ne
 * couvre que les criteres automatisables ; la plausibilite pedagogique des distracteurs reste le
 * jugement de nadia-prof.
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, au plus trois propositions) sont deja
 * derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCpNombresJusquA100Test {

    private static final String PAQUET = "cp-nombres-jusqu-a-100";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 21;

    /**
     * DEN-3 : les designations litterales exactes que ce paquet emploie, avec leur valeur. Un
     * dictionnaire ecrit a la main plutot qu'un parseur general - le vocabulaire du paquet est fini
     * et connu, et un dictionnaire faux se voit a la premiere lecture, la ou un parseur general
     * masque une erreur d'algorithme derriere une apparence de generalite.
     */
    private static final Map<String, Integer> VALEUR_DU_MOT = Map.ofEntries(
            Map.entry("dix", 10), Map.entry("treize", 13), Map.entry("quatorze", 14),
            Map.entry("quinze", 15), Map.entry("seize", 16),
            Map.entry("vingt-huit", 28), Map.entry("trente-huit", 38), Map.entry("quarante", 40),
            Map.entry("quarante-huit", 48), Map.entry("cinquante", 50), Map.entry("soixante", 60),
            Map.entry("soixante-deux", 62), Map.entry("soixante-dix", 70),
            Map.entry("soixante-onze", 71), Map.entry("soixante-douze", 72),
            Map.entry("quatre-vingts", 80), Map.entry("quatre-vingt-un", 81),
            Map.entry("quatre-vingt-onze", 91), Map.entry("quatre-vingt-douze", 92),
            Map.entry("quatre-vingt-dix", 90), Map.entry("cent", 100));

    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }

    private static boolean estUnMotNombre(String proposition) {
        return VALEUR_DU_MOT.containsKey(proposition);
    }

    private static int valeur(String proposition) {
        if (VALEUR_DU_MOT.containsKey(proposition)) {
            return VALEUR_DU_MOT.get(proposition);
        }
        // "4 dizaines", "6 unités", "8" : le premier nombre de la proposition.
        StringBuilder chiffres = new StringBuilder();
        for (int rang = 0; rang < proposition.length() && Character.isDigit(proposition.charAt(rang)); rang++) {
            chiffres.append(proposition.charAt(rang));
        }
        return Integer.parseInt(chiffres.toString());
    }

    /** Nombre de "mots" d'une proposition, au sens de DEN-8 : les tirets separent aussi. */
    private static int nombreDeMots(String proposition) {
        return proposition.strip().split("[ -]+").length;
    }

    private static boolean estUnItemLire(Question item) {
        return item.enonce().startsWith("Lis ce nombre");
    }

    private static boolean estUnItemEcrire(Question item) {
        return item.enonce().startsWith("Écris en chiffres");
    }

    private static boolean estUnItemDecomposer(Question item) {
        return !estUnItemLire(item) && !estUnItemEcrire(item);
    }

    /**
     * DEN-6/DEN-7 portent sur le nombre que l'item fait lire, ecrire ou decomposer - pas sur la
     * bonne reponse elle-meme quand elle n'en est qu'une fonction (un compte de dizaines, un
     * chiffre manquant). Pour "lire" et "ecrire", la bonne reponse EST ce nombre ; pour
     * "decomposer", il est le premier nombre de l'enonce (47 dans "47, combien de dizaines ?", 58
     * dans "58 = 50 + ?").
     */
    private static int numeroTravaille(Question item) {
        if (estUnItemLire(item)) {
            return valeur(item.bonneReponse());
        }
        if (estUnItemEcrire(item)) {
            return Integer.parseInt(item.bonneReponse());
        }
        String enonce = item.enonce().strip();
        int fin = 0;
        while (fin < enonce.length() && Character.isDigit(enonce.charAt(fin))) {
            fin++;
        }
        assertThat(fin).as("'%s' : l'enonce commence par le nombre travaille", item.enonce()).isPositive();
        return Integer.parseInt(enonce.substring(0, fin));
    }

    private static String sansAccent(String texte) {
        return Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.FRENCH);
    }

    @Test
    void lePaquetCompteVingtEtUnItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** DEN-3 : les trois verbes sont representes, sept items chacun. */
    @Test
    void lesTroisVerbesSontRepresentesParSeptItemsChacun() {
        List<Question> items = tousLesItems(paquet());
        assertThat(items.stream().filter(PaquetCpNombresJusquA100Test::estUnItemLire).count())
                .as("verbe 'lire'").isEqualTo(7);
        assertThat(items.stream().filter(PaquetCpNombresJusquA100Test::estUnItemEcrire).count())
                .as("verbe 'ecrire'").isEqualTo(7);
        assertThat(items.stream().filter(PaquetCpNombresJusquA100Test::estUnItemDecomposer).count())
                .as("verbe 'decomposer'").isEqualTo(7);
    }

    /**
     * DEN-1 : aucun item ne demande de compter une quantite affichee - ce paquet ne declare aucune
     * illustration, condition necessaire et verifiable ici.
     */
    @Test
    void aucunItemNeDeclareDIllustration() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.illustration()).as("'%s'", item.enonce()).isEmpty();
        }
    }

    /**
     * DEN-5 : le mot "decompose"/"decomposer" n'apparait jamais dans ce que l'enfant lit - ni les
     * enonces, ni la consigne du catalogue.
     */
    @Test
    void leMotDecomposerNApparaitJamaisDansCeQueLEnfantLit() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(sansAccent(item.enonce())).doesNotContain("decompos");
            item.indice().ifPresent(indice -> assertThat(sansAccent(indice)).doesNotContain("decompos"));
        }
        assertThat(sansAccent(jeu().consigne())).doesNotContain("decompos");
    }

    /**
     * GN-1 : dans le sens "lire", les trois propositions designent trois valeurs distinctes -
     * jamais une variante orthographique de la meme valeur.
     */
    @Test
    void leSensLireDesigneTroisValeursDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            if (!estUnItemLire(item)) {
                continue;
            }
            assertThat(estUnMotNombre(item.bonneReponse()))
                    .as("'%s' : bonne reponse absente du dictionnaire de mots-nombres", item.enonce())
                    .isTrue();
            Set<Integer> valeurs = new HashSet<>();
            valeurs.add(valeur(item.bonneReponse()));
            for (String distracteur : item.distracteurs()) {
                assertThat(estUnMotNombre(distracteur))
                        .as("'%s' : distracteur '%s' absent du dictionnaire", item.enonce(), distracteur)
                        .isTrue();
                valeurs.add(valeur(distracteur));
            }
            assertThat(valeurs)
                    .as("GN-1 : '%s' - les trois propositions designent trois valeurs distinctes",
                            item.enonce())
                    .hasSize(3);
        }
    }

    /**
     * DEN-6 : au moins un tiers des vingt-et-un items porte sur un nombre entre 60 et 99, en
     * position de bonne reponse.
     */
    @Test
    void auMoinsUnTiersDesItemsPorteSurUnNombreEntreSoixanteEtQuatreVingtDixNeuf() {
        List<Question> items = tousLesItems(paquet());
        long dansLaTranche = items.stream()
                .filter(item -> numeroTravaille(item) >= 60 && numeroTravaille(item) <= 99)
                .count();
        assertThat(dansLaTranche * 3)
                .as("DEN-6 : %d items sur %d dans la tranche 60-99", dansLaTranche, items.size())
                .isGreaterThanOrEqualTo(items.size());
    }

    /**
     * DEN-7 : au moins un nombre entre 11 et 16 travaille, et au moins un multiple de dix ecrit
     * sans unite ("soixante", "quatre-vingts") en bonne reponse.
     */
    @Test
    void laTrancheOnzeSeizeEtUnMultipleDeDixSansUniteSontRepresentes() {
        List<Question> items = tousLesItems(paquet());
        assertThat(items.stream().anyMatch(item -> numeroTravaille(item) >= 11 && numeroTravaille(item) <= 16))
                .as("DEN-7 : au moins un nombre entre 11 et 16 travaille")
                .isTrue();
        assertThat(items.stream()
                .map(Question::bonneReponse)
                .anyMatch(Set.of("vingt", "trente", "quarante", "cinquante", "soixante",
                        "quatre-vingts")::contains))
                .as("DEN-7 : au moins un multiple de dix ecrit sans unite")
                .isTrue();
    }

    /**
     * DEN-4 : au moins la moitie des sept items de decomposition emploie le vocabulaire
     * dizaines/unites.
     */
    @Test
    void auMoinsLaMoitieDesItemsDeDecompositionEmploieLeVocabulaireDizainesUnites() {
        List<Question> decomposition = tousLesItems(paquet()).stream()
                .filter(PaquetCpNombresJusquA100Test::estUnItemDecomposer)
                .toList();
        long avecVocabulaire = decomposition.stream()
                .filter(item -> item.bonneReponse().contains("dizaine")
                        || item.bonneReponse().contains("unité"))
                .count();
        assertThat(avecVocabulaire * 2)
                .as("DEN-4 : %d items sur %d emploient dizaines/unites", avecVocabulaire,
                        decomposition.size())
                .isGreaterThanOrEqualTo(decomposition.size());
    }

    /**
     * DEN-8 : les trois propositions d'un meme item ont le meme nombre de chiffres (items
     * numeriques) ou une longueur litterale comparable a un mot pres (items "lire").
     */
    @Test
    void lesTroisPropositionsDUnItemOntUneLongueurComparable() {
        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            List<Integer> mots = propositions.stream()
                    .map(PaquetCpNombresJusquA100Test::nombreDeMots).toList();
            int ecart = java.util.Collections.max(mots) - java.util.Collections.min(mots);
            assertThat(ecart)
                    .as("DEN-8 : '%s' - propositions %s, longueur comparable a un mot pres",
                            item.enonce(), propositions)
                    .isLessThanOrEqualTo(1);
        }
    }

    private static GameDefinition jeu() {
        return new YamlContentPackLoader().games().stream()
                .filter(candidat -> candidat.id().equals("bandit-manchot-nombres-jusqu-a-100-cp"))
                .findFirst()
                .orElseThrow();
    }
}
