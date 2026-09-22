package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S39-SOLIDES (MAT-CE1-16) : le paquet "ce1-solides", mathematiques CE1, premier paquet illustre
 * de solides du produit. Arbitrage prealable de nadia-prof
 * (squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md, section 4, amendee le
 * 2026-09-22 sur la forme de la boule), SOL-CE1-1 a SOL-CE1-4. Reprend la convention de
 * {@code PaquetCpFiguresPlanesTest} (s25) sur le meme registre : ne couvre que ce qui est
 * automatisable, l'exactitude du trace (qu'un cube ne soit pas un pave droit mal dessine, que la
 * boule se lise sans ambiguite) reste le jugement de nadia-prof - la geometrie declaree et le
 * comptage des formes rendues sont verifies par
 * {@code ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest} et
 * {@code LIllustrationDeContenuResoutEtAucuneNeDortTest.chaqueDessinQuiSertUnSolideTraceUne
 * ProjectionEtNonUneFigurePlane}, pas ici.
 */
class PaquetCe1SolidesTest {

    private static final String PAQUET = "ce1-solides";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 15;

    /** SOL-CE1-2 : au moins trois exemples par solide. */
    private static final int EXEMPLES_PAR_SOLIDE = 3;

    private static final List<String> SOLIDES = List.of("un cube", "un pave droit", "une boule",
            "un cylindre", "un cone");

    /** CON-2, instanciee sur cette matiere : ni forme, ni position, ni couleur decrite dans l'enonce. */
    private static final List<String> DESCRIPTIONS_INTERDITES = List.of("face", "arete", "sommet",
            "rond", "pointu", "pointe", "plat", "haut", "bas", "gauche", "droite", "rouge", "bleu",
            "vert", "jaune", "noir", "orange", "couleur", "grand", "petit", "roule");

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
        return Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.FRENCH);
    }

    @Test
    void lePaquetCompteQuinzeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /**
     * SOL-CE1-2 : au moins trois exemples de chaque solide, et FIG-4-comme : le paquet ne propose
     * que les cinq solides de la ligne, aucun de plus.
     */
    @Test
    void chaqueSolideCompteAuMoinsTroisExemples() {
        Map<String, Integer> exemplesParSolide = new TreeMap<>();
        for (Question item : tousLesItems(paquet())) {
            exemplesParSolide.merge(sansAccent(item.bonneReponse()), 1, Integer::sum);
        }
        for (String solide : SOLIDES) {
            assertThat(exemplesParSolide.getOrDefault(sansAccent(solide), 0))
                    .as("SOL-CE1-2 : '%s' compte au moins %d exemples", solide, EXEMPLES_PAR_SOLIDE)
                    .isGreaterThanOrEqualTo(EXEMPLES_PAR_SOLIDE);
        }
        assertThat(exemplesParSolide.keySet())
                .as("le paquet ne propose que les cinq solides de MAT-CE1-16")
                .containsExactlyInAnyOrderElementsOf(
                        SOLIDES.stream().map(PaquetCe1SolidesTest::sansAccent).toList());
    }

    /** SOL-CE1-2, seconde moitie : quinze items, quinze dessins distincts. */
    @Test
    void chaqueItemMontreUnDessinQuiNEstMontreParAucunAutre() {
        List<String> illustrations = tousLesItems(paquet()).stream()
                .map(item -> item.illustration().orElseThrow())
                .toList();
        assertThat(new LinkedHashSet<>(illustrations))
                .as("quinze items, quinze dessins distincts")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** CON-5 : le texte alternatif ne nomme aucun solide, et il est identique sur les quinze items. */
    @Test
    void aucunTexteAlternatifNeNommeUnSolide() {
        Set<String> alternatifs = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            String alternatif = sansAccent(item.illustrationTexteAlternatif().orElseThrow());
            alternatifs.add(alternatif);
            for (String solide : SOLIDES) {
                String nom = sansAccent(solide).replaceFirst("^(un |une )", "");
                assertThat(alternatif)
                        .as("CON-5 : le texte alternatif de '%s' nomme '%s'", item.enonce(), nom)
                        .doesNotContain(nom);
            }
        }
        assertThat(alternatifs).as("CON-5 : un seul texte alternatif sur tout le paquet").hasSize(1);
    }

    /** CON-2 : l'enonce ne decrit ni la forme, ni la position, ni la couleur du solide montre. */
    @Test
    void aucunEnonceNeDecritCeQueLImageMontre() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = sansAccent(item.enonce());
            for (String description : DESCRIPTIONS_INTERDITES) {
                assertThat(enonce)
                        .as("CON-2 : l'enonce '%s' emploie '%s'", item.enonce(), description)
                        .doesNotContain(description);
            }
        }
    }

    /** Chaque item porte un indice qui ne nomme aucun des cinq solides (donc jamais la reponse). */
    @Test
    void chaqueItemPorteUnIndiceQuiNeNommeAucunSolide() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice()).as("'%s' : indice present", item.enonce()).isPresent();
            String indice = sansAccent(item.indice().orElseThrow());
            for (String solide : SOLIDES) {
                String nom = sansAccent(solide).replaceFirst("^(un |une )", "");
                assertThat(indice)
                        .as("'%s' : l'indice nomme '%s'", item.enonce(), nom)
                        .doesNotContain(nom);
            }
        }
    }

    /**
     * ADR-0032 : deux items ne presentent jamais le meme ecran (enonce + jeu de propositions).
     * L'illustration n'entre pas dans cette identite (meme motif que
     * {@code PaquetCpFiguresPlanesTest}), et c'est pourquoi deux enonces distincts sont necessaires
     * pour porter quinze items sur les dix jeux de propositions possibles de cinq solides.
     */
    @Test
    void aucunItemNePresenteLeMemeEcranQuUnAutre() {
        Set<String> ecrans = new TreeSet<>();
        for (Question item : tousLesItems(paquet())) {
            Set<String> propositions = new TreeSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            ecrans.add(item.enonce() + " " + propositions);
        }
        assertThat(ecrans)
                .as("ADR-0032 : quinze items, quinze ecrans distincts")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    /** FIG-4-comme : le paquet ne confond jamais un solide avec une figure plane. */
    @Test
    void leTexteServiNeNommeAucuneFigurePlane() {
        List<String> motsInterdits = List.of("carre", "rectangle", "triangle", "cercle", "disque");
        for (Question item : tousLesItems(paquet())) {
            List<String> lus = new ArrayList<>(List.of(item.enonce(), item.bonneReponse()));
            lus.addAll(item.distracteurs());
            item.indice().ifPresent(lus::add);
            item.illustrationTexteAlternatif().ifPresent(lus::add);
            for (String texte : lus) {
                for (String interdit : motsInterdits) {
                    assertThat(sansAccent(texte))
                            .as("'%s' emploie '%s' - un solide n'est pas une figure plane", texte, interdit)
                            .doesNotContain(interdit);
                }
            }
        }
    }
}
