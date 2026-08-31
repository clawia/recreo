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
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S25-SEM (DEC-0065, amendement, sections A.2 et A.3) : le paquet "cp-semaine", mathematiques CP,
 * domaine "Grandeurs et mesures".
 *
 * <p>Conditions d'ecriture : {@code SEM-1} a {@code SEM-9} de nadia-prof, rendues le 2026-08-31
 * ({@code ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md},
 * section 2.3). <b>ACCEPTE SOUS CONDITIONS sur la moitie "se reperer dans la journee, la semaine",
 * REFUSE sur la moitie "lire l'heure pleine sur une horloge"</b>, qui rejoint la famille visuelle.
 *
 * <p><b>Les deux unites de compte ne sont pas de ma main.</b> {@code SEM-4} definit un mot comme
 * <i>"une suite de caracteres sans espace"</i> pour le plafond de douze, et donne {@code NAR-3} pour
 * reference du plafond de neuf lettres - <i>"septembre, le mois le plus long, le tient
 * exactement"</i>. Les neuf lettres se comptent donc sur le <b>mot graphique</b>, celui que
 * {@link PaquetCpTexteNarratifTest#motsGraphiques} delimite par les espaces, les apostrophes et les
 * traits d'union : le compter autrement ferait de "septembre" suivi d'un espace insecable et d'un
 * point d'interrogation un mot de onze lettres, c'est-a-dire ferait rougir l'exemple meme par lequel
 * l'autrice a dit ne rien durcir. Les deux methodes de comptage sont reprises de ce fichier-la et
 * non reecrites.
 *
 * <p><b>Ce que ce garde-fou ne juge pas, et rien ne le jugera a sa place.</b> {@code SEM-1} (la
 * pertinence de l'objet), {@code SEM-2} (aucun calcul de duree), {@code SEM-5} (les distracteurs de
 * meme categorie) et {@code SEM-7} (aucune situation qui suppose une famille, un rythme scolaire, un
 * pays) <b>se jugent</b> ; ce fichier n'en tient que la moitie qui se compte. {@code SEM-3} ne se
 * compte que <b>partiellement</b>, par un balayage de vocabulaire : il attrape "aiguille", "cadran",
 * "horloge", il n'attrape pas une periphrase, et nadia-prof l'a ecrit elle-meme. Elle recommande
 * cette condition comme celle qu'elle mettrait le plus volontiers en test - <i>"c'est celle qui
 * reintroduirait la contrefacon sans qu'on s'en apercoive"</i> - et c'est pourquoi elle est ici.
 */
class PaquetCpSemaineTest {

    private static final String PAQUET = "cp-semaine";

    /** Le compte est garde exactement, comme sur tous les paquets de ce depot. SEM-9 en exige vingt. */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 23;

    private static final int SEM_9_ITEMS_MINIMUM = 20;

    /** SEM-4 : douze mots au plus par enonce. */
    private static final int SEM_4_MOTS = 12;

    /** SEM-4 : neuf lettres au plus par mot graphique. */
    private static final int SEM_4_LETTRES = 9;

    /** SEM-8 : huit mots au plus par indice, comme NAR-9. */
    private static final int SEM_8_MOTS = 8;

    /**
     * SEM-3, sa moitie calculable : le vocabulaire de l'horloge, dans les mots de la condition -
     * <i>"aucun item ne decrit une horloge, un cadran ni la position d'une aiguille"</i> - plus les
     * deux mots par lesquels la contrefacon ecartee de la matiere premiere se formule ("grande
     * aiguille", "petite aiguille" designent une position, "heure" nomme ce que le cadran porte).
     * <b>C'est une liste de mots, et elle ne pretend pas etre autre chose</b> : une periphrase
     * lui echappe, l'autrice le dit dans sa propre condition, et c'est elle qui garde le reste.
     */
    private static final List<String> VOCABULAIRE_DE_L_HORLOGE = List.of("horloge", "cadran",
            "aiguille", "pendule", "montre", "heure", "minute", "seconde");

    /**
     * SEM-6, unite de compte : l'item dont la reponse porte sur ce qui vient <b>juste avant</b> ou
     * <b>juste apres</b>. La marque est cherchee dans l'enonce parce que c'est lui qui pose la
     * question ; un item qui demanderait un ordre sans l'ecrire ainsi ne serait pas compte, ce qui
     * rend ce compte un plancher et jamais une surestimation.
     */
    private static final List<String> MARQUES_D_ORDRE = List.of("juste avant", "juste apres");

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

    private static String sansAccent(String texte) {
        return java.text.Normalizer.normalize(texte, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.FRENCH);
    }

    @Test
    void lePaquetCompteExactementVingtTroisItems() {
        List<Question> items = tousLesItems(paquet());
        assertThat(items)
                .as("SEM-9 : au moins %d items, et le compte est garde exactement a %d",
                        SEM_9_ITEMS_MINIMUM, NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(NOMBRE_D_ITEMS_ATTENDU)
                .as("SEM-9 : le compte garde reste au-dessus du plancher de l'autrice")
                .isGreaterThanOrEqualTo(SEM_9_ITEMS_MINIMUM);
    }

    /** SEM-9, seconde moitie : deux items ne posent jamais la meme question. */
    @Test
    void deuxItemsNePosentJamaisLaMemeQuestion() {
        Set<String> questions = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            questions.add(item.enonce());
        }
        assertThat(questions)
                .as("SEM-9 : %d items, %d questions distinctes", NOMBRE_D_ITEMS_ATTENDU,
                        NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** SEM-4 : au plus douze mots par enonce, un mot etant une suite de caracteres sans espace. */
    @Test
    void aucunEnonceNeDepasseDouzeMots() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(PaquetCpTexteNarratifTest.mots(item.enonce()))
                    .as("SEM-4 : l'enonce '%s' compte au plus %d mots", item.enonce(), SEM_4_MOTS)
                    .hasSizeLessThanOrEqualTo(SEM_4_MOTS);
        }
    }

    /** SEM-4 : au plus neuf lettres par mot graphique, le plafond que "septembre" tient exactement. */
    @Test
    void aucunMotGraphiqueNeDepasseNeufLettres() {
        List<String> defauts = new ArrayList<>();
        for (Question item : tousLesItems(paquet())) {
            for (String mot : PaquetCpTexteNarratifTest.motsGraphiques(item.enonce())) {
                if (mot.length() > SEM_4_LETTRES) {
                    defauts.add("'" + item.enonce() + "' : le mot '" + mot + "' fait "
                            + mot.length() + " lettres");
                }
            }
        }
        assertThat(defauts)
                .as("SEM-4 : aucun mot de l'enonce ne depasse %d lettres, l'apostrophe et le trait"
                        + " d'union etant des delimiteurs", SEM_4_LETTRES)
                .isEmpty();
    }

    /**
     * SEM-3, sa moitie calculable : aucun item ne decrit une horloge, un cadran ni la position d'une
     * aiguille. Le controle porte sur tout ce que l'enfant lit - enonce, propositions, indice - et
     * non sur le seul enonce : la condition dit <i>"son enonce et ses propositions"</i>, et un
     * indice qui parlerait d'aiguille servirait la meme contrefacon.
     */
    @Test
    void aucunItemNeDecritUneHorloge() {
        for (Question item : tousLesItems(paquet())) {
            List<String> lus = new ArrayList<>(List.of(item.enonce(), item.bonneReponse()));
            lus.addAll(item.distracteurs());
            item.indice().ifPresent(lus::add);
            for (String texte : lus) {
                for (String mot : VOCABULAIRE_DE_L_HORLOGE) {
                    assertThat(sansAccent(texte))
                            .as("SEM-3 : '%s' emploie '%s'. Cette moitie de la ligne n'est pas"
                                    + " servie et le paquet ne fait pas semblant de la servir",
                                    texte, mot)
                            .doesNotContain(mot);
                }
            }
        }
    }

    /**
     * SEM-6 : au moins un tiers des items demandent ce qui vient juste avant ou juste apres.
     * <i>"Se reperer est un fait d'ordre ; reciter la liste des sept jours n'est pas s'y
     * reperer."</i> Le compte est un plancher : un item qui demanderait un ordre autrement n'est pas
     * compte ici.
     */
    @Test
    void auMoinsUnTiersDesItemsDemandeUnOrdre() {
        List<Question> items = tousLesItems(paquet());
        long ordre = items.stream()
                .filter(item -> MARQUES_D_ORDRE.stream()
                        .anyMatch(marque -> sansAccent(item.enonce()).contains(marque)))
                .count();
        assertThat(ordre * 3)
                .as("SEM-6 : %d items sur %d demandent ce qui vient juste avant ou juste apres, il"
                        + " en faut au moins un tiers", ordre, items.size())
                .isGreaterThanOrEqualTo(items.size());
    }

    /** SEM-8 : chaque item porte un indice, de huit mots au plus, qui ne nomme pas la reponse. */
    @Test
    void chaqueIndiceTientHuitMotsEtNeNommePasLaReponse() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice())
                    .as("SEM-8 : l'item '%s' porte un indice", item.enonce())
                    .isPresent();
            String indice = item.indice().orElseThrow();
            assertThat(PaquetCpTexteNarratifTest.mots(indice))
                    .as("SEM-8 : l'indice de '%s' compte au plus %d mots", item.enonce(),
                            SEM_8_MOTS)
                    .hasSizeLessThanOrEqualTo(SEM_8_MOTS);
            assertThat(sansAccent(indice))
                    .as("SEM-8 : l'indice de '%s' nomme sa bonne reponse - il dit quoi se rappeler,"
                            + " jamais la reponse", item.enonce())
                    .doesNotContain(sansAccent(item.bonneReponse()));
        }
    }

    /**
     * SEM-5, sa moitie calculable : les trois propositions d'un item sont distinctes, et la bonne
     * reponse appartient au meme ensemble de mots que ses distracteurs - un jour repond a une
     * question sur un jour, un mois a une question sur un mois. <b>Que les categories soient les
     * bonnes reste le jugement de nadia-prof</b> ; ce que ce test tient est que les trois
     * propositions d'un item sont tirees d'une seule des quatre familles que SEM-1 autorise.
     */
    @Test
    void lesTroisPropositionsDUnItemSontDeLaMemeFamille() {
        List<Set<String>> familles = List.of(
                Set.of("lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"),
                Set.of("janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "aout",
                        "septembre", "octobre", "novembre", "decembre"),
                Set.of("le printemps", "l'ete", "l'automne", "l'hiver"),
                Set.of("le matin", "le midi", "l'apres-midi", "le soir", "la nuit"));

        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>(List.of(item.bonneReponse()));
            propositions.addAll(item.distracteurs());
            assertThat(new TreeSet<>(propositions))
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);

            boolean memeFamille = familles.stream().anyMatch(famille -> propositions.stream()
                    .allMatch(proposition -> famille.contains(sansAccent(proposition))));
            assertThat(memeFamille)
                    .as("SEM-5 : les propositions de '%s' (%s) ne sont pas toutes de la meme"
                            + " famille - un distracteur d'une autre categorie s'elimine sans"
                            + " savoir", item.enonce(), propositions)
                    .isTrue();
        }
    }
}
