package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S24-PLU (`DEC-0059`, arbitrage prealable de `nadia-prof` du 2026-08-30,
 * `ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`, partie 4,
 * conditions `PLU-1` a `PLU-9`) : le paquet "cp-pluriel", francais CP.
 *
 * <p><b>Ce que ce garde-fou juge, et pourquoi il peut le juger.</b> `nadia-prof` ecrit elle-meme que
 * sur ce paquet il ne lui reste "presque rien" : `PLU-2` a `PLU-7` et `PLU-9` se calculent a partir
 * du fichier. La clause centrale est {@link #lesDeuxDistracteursSontDerivesDeLaBonneReponse()} : les
 * deux distracteurs de chaque item sont <b>recalcules</b> a partir de la seule bonne reponse et de
 * la table de `PLU-2`, jamais compares a une liste ecrite ici.
 *
 * <p><b>La seule ambiguite de la derivation, nommee plutot que contournee.</b> La famille (b) fait
 * passer le determinant au nombre inverse. De "un" ou "une" vers le pluriel, la table de `PLU-4` ne
 * laisse qu'une image ("des") ; de "le" ou "la", une seule aussi ("les"). Dans l'autre sens, "les"
 * et "des" ont chacun deux singuliers admissibles selon le genre du nom - et le genre d'un nom
 * pluriel ne se lit pas dans la chaine. La clause exige donc que le distracteur (b) porte le nom
 * inchange et un determinant appartenant a l'<b>ensemble</b> des images admissibles, ce qui est
 * exactement ce qu'un tiers peut recalculer sans rien savoir de plus que le fichier.
 *
 * <p><b>Ce qui reste chez `nadia-prof`.</b> `PLU-1` - que la phrase ne porte aucun autre indice de
 * nombre pour le groupe - est un jugement de langue ; ce paquet le tient en ne mettant jamais le
 * groupe a trou en position de sujet, mais aucune clause d'ici ne le prouve. `PLU-8`, ce que dit
 * l'indice, et le niveau sont egalement les siens.
 */
class PaquetCpPlurielTest {

    private static final String PAQUET = "cp-pluriel";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 21;

    /** PLU-2 : population fermee et enumeree des determinants admis. Six elements, aucun autre. */
    private static final Set<String> DETERMINANTS_ADMIS =
            Set.of("un", "une", "le", "la", "les", "des");

    private static final Set<String> DETERMINANTS_SINGULIERS = Set.of("un", "une", "le", "la");

    private static final Set<String> DETERMINANTS_PLURIELS = Set.of("les", "des");

    /** PLU-6 : au plus 10 mots de phrase support, au plus 15 caracteres par proposition. */
    private static final int PLU_6_MOTS_DE_LA_PHRASE = 10;

    private static final int PLU_6_CARACTERES_D_UNE_PROPOSITION = 15;

    /** PLU-7 : neuf lettres au plus, apostrophe non comptee. */
    private static final int PLU_7_LETTRES = 9;

    /** PLU-8 : huit mots au plus par indice. */
    private static final int PLU_8_MOTS = 8;

    /** PLU-9 : un nom n'est pas utilise plus de deux fois dans le paquet. */
    private static final int PLU_9_USAGES_PAR_NOM = 2;

    private static final String MARQUEUR_DE_TROU = "___";

    /**
     * PLU-3 : les terminaisons dont le pluriel n'est pas en -s, et les sept noms en -ou qui prennent
     * -x (liste fermee et complete de l'arbitrage).
     */
    private static final List<String> TERMINAISONS_EXCLUES =
            List.of("eau", "au", "eu", "al", "ail", "s", "x", "z");

    private static final Set<String> NOMS_EN_OU_EXCLUS =
            Set.of("bijou", "caillou", "chou", "genou", "hibou", "joujou", "pou");

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
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

    private static String determinantDe(String proposition) {
        return proposition.split(" ")[0];
    }

    private static String nomDe(String proposition) {
        return proposition.substring(proposition.indexOf(' ') + 1);
    }

    /** Le nom au singulier : la marque du pluriel de ce paquet est le seul -s final. */
    private static String auSingulier(String nom) {
        return nom.endsWith("s") ? nom.substring(0, nom.length() - 1) : nom;
    }

    private static String auPluriel(String nom) {
        return nom.endsWith("s") ? nom : nom + "s";
    }

    /** PLU-4, table de derivation : les images admissibles du determinant passe au nombre inverse. */
    private static Set<String> determinantsAuNombreInverse(String determinant) {
        if ("un".equals(determinant) || "une".equals(determinant)) {
            return Set.of("des");
        }
        if ("le".equals(determinant) || "la".equals(determinant)) {
            return Set.of("les");
        }
        if ("les".equals(determinant)) {
            return Set.of("le", "la");
        }
        return Set.of("un", "une");
    }

    @Test
    void lePaquetCompteExactementVingtEtUnItems() {
        assertThat(tousLesItems(paquet()))
                .as("PLU-9 : au moins vingt items, et le compte est garde exactement a %d",
                        NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCpOrthographeEtGrammaire() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence()).contains("marques du pluriel");
    }

    /** PLU-1, sa moitie de forme : la phrase presente un groupe remplace par un trou. */
    @Test
    void chaqueEnonceEstUnePhraseATrou() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.enonce())
                    .as("PLU-1 : la phrase presente un groupe remplace par un trou")
                    .contains(MARQUEUR_DE_TROU);
        }
    }

    /**
     * PLU-1 et PLU-2 : les trois propositions d'un item sont trois ecritures du meme groupe
     * determinant + nom, et le determinant appartient a la population fermee des six.
     */
    @Test
    void chaquePropositionEstUnGroupeDeterminantPlusNomAdmis() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            for (String proposition : propositionsDe(item)) {
                if (proposition.split(" ").length != 2) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' n'est pas un groupe determinant + nom");
                    continue;
                }
                if (!DETERMINANTS_ADMIS.contains(determinantDe(proposition))) {
                    defauts.add("'" + item.enonce() + "' : le determinant de '" + proposition
                            + "' est hors de la population fermee de PLU-2");
                }
            }
        }
        assertThat(defauts)
                .as("PLU-2 : les six determinants admis sont %s, et aucun autre", DETERMINANTS_ADMIS)
                .isEmpty();
    }

    /** PLU-2 : les six determinants sont employes, faute de quoi la population n'est pas celle-la. */
    @Test
    void lesSixDeterminantsAdmisSontEmployes() {
        Set<String> employes = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            employes.add(determinantDe(item.bonneReponse()));
        }
        assertThat(employes)
                .as("PLU-2 : les six determinants de la population fermee sont employes par une"
                        + " bonne reponse au moins")
                .containsExactlyInAnyOrderElementsOf(DETERMINANTS_ADMIS);
    }

    /** PLU-3 : le nom de la bonne reponse n'est pas un nom dont le pluriel n'est pas en -s. */
    @Test
    void aucunNomN_EstExcluParLaPopulationDePlu3() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String nom = auSingulier(nomDe(item.bonneReponse()));
            if (NOMS_EN_OU_EXCLUS.contains(nom)) {
                defauts.add("'" + item.enonce() + "' : '" + nom + "' est un des sept noms en -ou"
                        + " qui prennent -x");
            }
            for (String terminaison : TERMINAISONS_EXCLUES) {
                if (nom.endsWith(terminaison)) {
                    defauts.add("'" + item.enonce() + "' : '" + nom + "' se termine par -"
                            + terminaison + ", exclu par PLU-3");
                }
            }
        }
        assertThat(defauts)
                .as("PLU-3 : la ligne porte sur les marques du pluriel en -s, et un nom dont le"
                        + " pluriel n'est pas en -s rendrait la reponse attendue fausse")
                .isEmpty();
    }

    /**
     * PLU-4 : les deux distracteurs sont derives de la bonne reponse et recalculables par un tiers.
     * Famille (a) - le nom porte la marque inverse, le determinant reste celui de la bonne reponse.
     * Famille (b) - le determinant passe au nombre inverse, le nom reste celui de la bonne reponse.
     */
    @Test
    void lesDeuxDistracteursSontDerivesDeLaBonneReponse() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String determinant = determinantDe(item.bonneReponse());
            String nom = nomDe(item.bonneReponse());
            boolean pluriel = DETERMINANTS_PLURIELS.contains(determinant);
            String nomInverse = pluriel ? auSingulier(nom) : auPluriel(nom);

            String attenduA = determinant + " " + nomInverse;
            List<String> distracteurs = item.distracteurs();
            if (!distracteurs.contains(attenduA)) {
                defauts.add("'" + item.enonce() + "' : la famille (a) derivee de '"
                        + item.bonneReponse() + "' est '" + attenduA + "', absente de "
                        + distracteurs);
            }
            Set<String> attendusB = new TreeSet<>();
            for (String image : determinantsAuNombreInverse(determinant)) {
                attendusB.add(image + " " + nom);
            }
            if (distracteurs.stream().noneMatch(attendusB::contains)) {
                defauts.add("'" + item.enonce() + "' : la famille (b) derivee de '"
                        + item.bonneReponse() + "' est l'une de " + attendusB + ", absente de "
                        + distracteurs);
            }
        }
        assertThat(defauts)
                .as("PLU-4 : un tiers recalcule les deux distracteurs a partir de la bonne reponse"
                        + " et de la table de PLU-2, sans autre information que le fichier")
                .isEmpty();
    }

    /**
     * PLU-5 : le -s ne decide pas. Sans cette clause, un enfant qui choisit toujours la forme la
     * plus longue reussit tout le paquet.
     */
    @Test
    void laMarqueDuPlurielNeDecidePasSurLePaquet() {
        List<Question> items = tousLesItems(paquet());
        long avecMarque = items.stream()
                .filter(item -> DETERMINANTS_PLURIELS.contains(determinantDe(item.bonneReponse())))
                .count();
        long sansMarque = items.size() - avecMarque;
        int plancher = items.size() / 3;

        assertThat(avecMarque)
                .as("PLU-5 : la bonne reponse porte la marque -s sur au moins %d items", plancher)
                .isGreaterThanOrEqualTo(plancher);
        assertThat(sansMarque)
                .as("PLU-5 : la bonne reponse ne porte pas la marque -s sur au moins %d items",
                        plancher)
                .isGreaterThanOrEqualTo(plancher);
    }

    @Test
    void laPhraseEtLesPropositionsTiennentDansLeursBornes() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            int motsDeLaPhrase = PaquetCpTexteNarratifTest.mots(item.enonce()).size();
            if (motsDeLaPhrase > PLU_6_MOTS_DE_LA_PHRASE) {
                defauts.add("'" + item.enonce() + "' : " + motsDeLaPhrase + " mots de phrase");
            }
            for (String proposition : propositionsDe(item)) {
                if (proposition.length() > PLU_6_CARACTERES_D_UNE_PROPOSITION) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' fait " + proposition.length() + " caracteres");
                }
            }
        }
        assertThat(defauts)
                .as("PLU-6 : au plus %d mots de phrase, au plus %d caracteres par proposition"
                        + " (espaces comprises)", PLU_6_MOTS_DE_LA_PHRASE,
                        PLU_6_CARACTERES_D_UNE_PROPOSITION)
                .isEmpty();
    }

    @Test
    void aucunMotGraphiqueNeDepasseNeufLettres() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            List<String> aLire = new ArrayList<>(propositionsDe(item));
            aLire.add(item.enonce());
            for (String texte : aLire) {
                for (String mot : PaquetCpTexteNarratifTest.motsGraphiques(texte)) {
                    if (mot.length() > PLU_7_LETTRES) {
                        defauts.add("'" + item.enonce() + "' : le mot '" + mot + "' fait "
                                + mot.length() + " lettres");
                    }
                }
            }
        }
        assertThat(defauts)
                .as("PLU-7 : aucun mot de la phrase ni d'une proposition ne depasse %d lettres,"
                        + " apostrophe non comptee", PLU_7_LETTRES)
                .isEmpty();
    }

    @Test
    void aucunIndiceNeDepasseHuitMots() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice())
                    .as("'%s' : PLU-8, chaque item porte un indice", item.enonce())
                    .isPresent();
            assertThat(PaquetCpTexteNarratifTest.mots(item.indice().orElseThrow()))
                    .as("'%s' : PLU-8, l'indice compte au plus %d mots", item.enonce(), PLU_8_MOTS)
                    .hasSizeLessThanOrEqualTo(PLU_8_MOTS);
        }
    }

    @Test
    void aucunNomN_EstUtilisePlusDeDeuxFois() {
        TreeMap<String, Integer> usages = new TreeMap<>();
        for (Question item : tousLesItems(paquet())) {
            usages.merge(auSingulier(nomDe(item.bonneReponse())), 1, Integer::sum);
        }
        List<String> defauts = new ArrayList<>();
        usages.forEach((nom, compte) -> {
            if (compte > PLU_9_USAGES_PAR_NOM) {
                defauts.add("'" + nom + "' est utilise " + compte + " fois");
            }
        });
        assertThat(defauts)
                .as("PLU-9 : aucun nom n'est utilise plus de %d fois dans le paquet",
                        PLU_9_USAGES_PAR_NOM)
                .isEmpty();
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            assertThat(new HashSet<>(propositionsDe(item)))
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    /** Un determinant singulier va avec un nom sans marque, un pluriel avec un nom marque. */
    @Test
    void chaqueBonneReponseEstAccordeeAL_InterieurDuGroupe() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String determinant = determinantDe(item.bonneReponse());
            String nom = nomDe(item.bonneReponse());
            boolean nomMarque = nom.endsWith("s");
            if (DETERMINANTS_SINGULIERS.contains(determinant) && nomMarque) {
                defauts.add("'" + item.bonneReponse() + "' : determinant singulier, nom marque");
            }
            if (DETERMINANTS_PLURIELS.contains(determinant) && !nomMarque) {
                defauts.add("'" + item.bonneReponse() + "' : determinant pluriel, nom non marque");
            }
        }
        assertThat(defauts)
                .as("la bonne reponse est la seule des trois ecritures accordee a l'interieur du"
                        + " groupe - c'est ce que l'item demande de reconnaitre")
                .isEmpty();
    }

    private static List<String> propositionsDe(Question item) {
        List<String> propositions = new ArrayList<>();
        propositions.add(item.bonneReponse());
        propositions.addAll(item.distracteurs());
        return propositions;
    }
}
