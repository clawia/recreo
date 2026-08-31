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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S24-NAR (`DEC-0059`, arbitrage prealable de `nadia-prof` du 2026-08-30,
 * `ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`, partie 3,
 * conditions `NAR-1` a `NAR-10`) : le paquet "cp-texte-narratif", francais CP.
 *
 * <p><b>Les deux unites de compte viennent de l'arbitrage prealable de s24, section 5.1</b>, et
 * elles ne sont pas de ma main : un mot est une suite de caracteres sans espace, la ponctuation
 * isolee non comptee (`NAR-2`) ; les neuf lettres se comptent sur le mot graphique delimite par les
 * espaces, les apostrophes et les traits d'union (`NAR-3`) - "range-t-elle" se lit "range", "t",
 * "elle". Sans elles, la meme mesure ne rend pas le meme verdict d'une main a l'autre.
 *
 * <p><b>Ce que ce garde-fou ne juge pas.</b> `NAR-6` - un enfant qui n'a pas lu le texte-support ne
 * fait pas mieux que le hasard - est du type de `COMP-2` : aucun automate de ce depot ne la voit, et
 * `nadia-prof` la porte item par item, texte masque, sur la totalite du paquet. `NAR-4` ne se
 * verifie ici que dans sa forme (l'enonce commence par "Qui", "Ou" ou "Que") ; que la reponse se
 * lise litteralement dans le texte sans aucune inference reste son jugement. `NAR-8` est
 * entierement le sien.
 *
 * <p><b>`NAR-7` et la table de genre.</b> Aucun lexique de ce depot ne porte le genre d'un prenom :
 * la table {@link #GENRE_DES_PRENOMS} est une declaration d'auteur, relue par `nadia-prof` comme le
 * reste. Ce que la clause prouve est donc l'equilibre <b>de cette table</b> a un pres, jamais que la
 * table dit vrai ; et la seconde moitie de `NAR-7` - qu'aucun role, aucune activite, aucune emotion
 * n'est distribuee par genre - n'est pas mecanisable et reste chez `nadia-prof`.
 */
class PaquetCpTexteNarratifTest {

    private static final String PAQUET = "cp-texte-narratif";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** NAR-2 : au plus 18 mots de texte-support, au plus deux phrases, au plus 25 mots au total. */
    private static final int NAR_2_MOTS_DU_TEXTE_SUPPORT = 18;

    private static final int NAR_2_PHRASES = 2;

    private static final int NAR_2_MOTS_AU_TOTAL = 25;

    /** NAR-3 : neuf lettres au plus par mot graphique. */
    private static final int NAR_3_LETTRES = 9;

    /** NAR-9 : huit mots au plus par indice. */
    private static final int NAR_9_MOTS = 8;

    private static final List<String> DEBUTS_ADMIS = List.of("Qui ", "Où ", "Que ");

    /**
     * NAR-7, unite de compte : le prenom distinct. Declaration d'auteur, faute de lexique de
     * prenoms dans ce depot ; chaque prenom nomme ici apparait dans au moins un texte-support.
     */
    private static final Map<String, String> GENRE_DES_PRENOMS = Map.ofEntries(
            Map.entry("Sara", "f"), Map.entry("Nina", "f"), Map.entry("Nour", "f"),
            Map.entry("Iris", "f"), Map.entry("Zoé", "f"), Map.entry("Anna", "f"),
            Map.entry("Elsa", "f"), Map.entry("Maya", "f"), Map.entry("Lila", "f"),
            Map.entry("Alba", "f"),
            Map.entry("Malo", "m"), Map.entry("Tom", "m"), Map.entry("Léo", "m"),
            Map.entry("Rémi", "m"), Map.entry("Hugo", "m"), Map.entry("Ivan", "m"),
            Map.entry("Théo", "m"), Map.entry("Jules", "m"), Map.entry("Ali", "m"),
            Map.entry("Noé", "m"));

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

    /** NAR-2 : un mot est une suite de caracteres sans espace, la ponctuation isolee non comptee. */
    static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String morceau : texte.split(" ")) {
            if (morceau.chars().anyMatch(Character::isLetterOrDigit)) {
                mots.add(morceau);
            }
        }
        return mots;
    }

    /** NAR-3 : le mot graphique, delimite par les espaces, les apostrophes et les traits d'union. */
    static List<String> motsGraphiques(String texte) {
        List<String> mots = new ArrayList<>();
        StringBuilder courant = new StringBuilder();
        for (int rang = 0; rang < texte.length(); rang++) {
            char lettre = texte.charAt(rang);
            if (Character.isLetter(lettre)) {
                courant.append(lettre);
            } else if (courant.length() > 0) {
                mots.add(courant.toString());
                courant.setLength(0);
            }
        }
        if (courant.length() > 0) {
            mots.add(courant.toString());
        }
        return mots;
    }

    @Test
    void lePaquetCompteExactementVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("NAR-10 : au moins vingt items, et le compte est garde exactement a %d",
                        NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCpLectureEtComprehension() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Lecture et compréhension de l'écrit");
        assertThat(descripteur.competence()).contains("Comprendre un texte narratif très court");
    }

    /** NAR-1 : le support est porte par 'texteSupport', l'item par 'enonce'. */
    @Test
    void chaqueItemPorteUnTexteSupportNonVide() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.texteSupport())
                    .as("'%s' : NAR-1, le support est porte par la cle texteSupport", item.enonce())
                    .isPresent();
            assertThat(item.texteSupport().orElseThrow())
                    .as("'%s' : le texte-support ne doit pas etre vide", item.enonce())
                    .isNotBlank();
        }
    }

    @Test
    void chaqueTexteSupportTientDansDixHuitMotsEtDeuxPhrases() {
        for (Question item : tousLesItems(paquet())) {
            String support = item.texteSupport().orElseThrow();
            assertThat(mots(support))
                    .as("'%s' : NAR-2, au plus %d mots de texte-support", item.enonce(),
                            NAR_2_MOTS_DU_TEXTE_SUPPORT)
                    .hasSizeLessThanOrEqualTo(NAR_2_MOTS_DU_TEXTE_SUPPORT);
            long phrases = support.chars().filter(caractere -> caractere == '.').count();
            assertThat(phrases)
                    .as("'%s' : NAR-2, au plus %d phrases", item.enonce(), NAR_2_PHRASES)
                    .isLessThanOrEqualTo(NAR_2_PHRASES);
        }
    }

    @Test
    void leTexteSupportEtLEnonceTiennentDansVingtCinqMots() {
        for (Question item : tousLesItems(paquet())) {
            int total = mots(item.texteSupport().orElseThrow()).size() + mots(item.enonce()).size();
            assertThat(total)
                    .as("'%s' : NAR-2, le texte-support et l'enonce reunis comptent au plus %d mots",
                            item.enonce(), NAR_2_MOTS_AU_TOTAL)
                    .isLessThanOrEqualTo(NAR_2_MOTS_AU_TOTAL);
        }
    }

    @Test
    void aucunMotGraphiqueNeDepasseNeufLettres() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            for (String texte : List.of(item.texteSupport().orElseThrow(), item.enonce())) {
                for (String mot : motsGraphiques(texte)) {
                    if (mot.length() > NAR_3_LETTRES) {
                        defauts.add("'" + item.enonce() + "' : le mot '" + mot + "' fait "
                                + mot.length() + " lettres");
                    }
                }
            }
        }
        assertThat(defauts)
                .as("NAR-3 : aucun mot du texte-support ni de l'enonce ne depasse %d lettres,"
                        + " l'apostrophe et le trait d'union etant des delimiteurs", NAR_3_LETTRES)
                .isEmpty();
    }

    /** NAR-4, sa moitie calculable : la forme de la question. Le reste est le jugement de nadia-prof. */
    @Test
    void chaqueEnonceCommenceParQuiOuOuQue() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(DEBUTS_ADMIS.stream().anyMatch(debut -> item.enonce().startsWith(debut)))
                    .as("'%s' : NAR-4, l'enonce commence par \"Qui\", \"Ou\" ou \"Que\"",
                            item.enonce())
                    .isTrue();
        }
    }

    /**
     * NAR-5, la branche inverse de COMP-1 : les trois propositions figurent toutes les trois, en
     * toutes lettres, dans le texte-support de leur propre item. Au CP, l'enfant apparie des
     * chaines ; une seule proposition presente dans le texte se trouverait sans lire.
     */
    @Test
    void lesTroisPropositionsFigurentEnToutesLettresDansLeTexteSupport() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            String support = item.texteSupport().orElseThrow();
            List<String> propositions = new ArrayList<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            for (String proposition : propositions) {
                if (!support.contains(proposition)) {
                    defauts.add("'" + item.enonce() + "' : la proposition '" + proposition
                            + "' ne figure pas dans le texte-support");
                }
            }
        }
        assertThat(defauts)
                .as("NAR-5 : les trois propositions de chaque item figurent en toutes lettres dans"
                        + " le texte-support de ce meme item")
                .isEmpty();
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            assertThat(propositions)
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void aucunIndiceNeDepasseHuitMots() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice())
                    .as("'%s' : NAR-9, chaque item porte un indice", item.enonce())
                    .isPresent();
            assertThat(mots(item.indice().orElseThrow()))
                    .as("'%s' : NAR-9, l'indice compte au plus %d mots", item.enonce(), NAR_9_MOTS)
                    .hasSizeLessThanOrEqualTo(NAR_9_MOTS);
        }
    }

    /**
     * NAR-10 : deux items ne partagent jamais le meme texte-support - la mecanique tire une manche
     * au hasard et ne sait pas poser deux questions sur un meme texte.
     */
    @Test
    void aucunTexteSupportNEstPartageParDeuxItems() {
        List<Question> items = tousLesItems(paquet());
        Set<String> supports = new TreeSet<>();
        for (Question item : items) {
            supports.add(item.texteSupport().orElseThrow());
        }
        assertThat(supports)
                .as("NAR-10 : deux items ne partagent jamais le meme texte-support")
                .hasSize(items.size());
    }

    /**
     * NAR-7, sa moitie calculable : sur l'ensemble du paquet, les prenoms distincts sont equilibres
     * en genre a un pres. Le genre vient de {@link #GENRE_DES_PRENOMS}, declaration d'auteur ; la
     * clause verifie aussi qu'aucun prenom declare ne dort dans la table sans figurer dans un texte.
     */
    @Test
    void lesPrenomsDistinctsSontEquilibresEnGenreAUnPres() {
        Set<String> rencontres = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            for (String mot : motsGraphiques(item.texteSupport().orElseThrow())) {
                if (GENRE_DES_PRENOMS.containsKey(mot)) {
                    rencontres.add(mot);
                }
            }
        }
        assertThat(rencontres)
                .as("chaque prenom declare dans la table de genre figure dans au moins un"
                        + " texte-support : une table qui pourrit ne prouve plus rien")
                .containsExactlyInAnyOrderElementsOf(GENRE_DES_PRENOMS.keySet());

        long feminins = rencontres.stream()
                .filter(prenom -> "f".equals(GENRE_DES_PRENOMS.get(prenom))).count();
        long masculins = rencontres.size() - feminins;
        assertThat(Math.abs(feminins - masculins))
                .as("NAR-7 : %d prenoms feminins distincts et %d masculins - l'ecart admis est de"
                        + " un au plus", feminins, masculins)
                .isLessThanOrEqualTo(1);
    }
}
