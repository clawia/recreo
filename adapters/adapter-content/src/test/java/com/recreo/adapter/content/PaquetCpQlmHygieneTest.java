package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S41-QLM-HYGIENE, le paquet {@code cp-qlm-hygiene}, ligne {@code QLM-CP-04} de "Questionner le
 * monde" (CP).</b> Referentiel : {@code squad/programme/questionner-le-monde/competences-cp.md}.
 * Arbitrage prealable deja rendu (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
 * plafond-a-huit-lignes.md, ligne 370 ; precise par ARBITRAGE-PREALABLE-s27-les-matieres-absentes-
 * du-programme.md, ligne 368) : "moyenne, sous BIA-4. Terrain sur si aucun item n'interroge l'enfant
 * sur sa propre vie."
 *
 * <p><b>Les trois domaines du libelle</b> (sommeil, alimentation, hygiene du corps) doivent chacun
 * etre en bonne reponse au moins une fois.</p>
 *
 * <p><b>BIA-4</b> : aucun item ne s'adresse a l'enfant a la deuxieme personne ("que manges-tu ?",
 * "as-tu bien dormi ?") - chaque enonce reste impersonnel.</p>
 */
class PaquetCpQlmHygieneTest {

    private static final String PAQUET = "cp-qlm-hygiene";

    /** FER-2 : au moins dix items servent une ligne fermee (DEC-0064). */
    private static final int PLANCHER_FER_2 = 10;

    private enum Domaine { SOMMEIL, ALIMENTATION, HYGIENE_DU_CORPS }

    /**
     * Mots distinctifs de chaque domaine, verifies en ordre de priorite : "laver" apparait dans
     * l'item "se laver les mains avant de manger", qui porte l'hygiene du corps et non
     * l'alimentation malgre le mot "manger" qu'il contient aussi.
     */
    private static final Set<String> MOTS_HYGIENE_DU_CORPS =
            Set.of("laver", "brosse", "douche", "bain", "ongles", "toilettes");

    private static final Set<String> MOTS_ALIMENTATION =
            Set.of("manger", "mange", "nourrir", "sucreries", "fruits");

    private static final Set<String> MOTS_SOMMEIL =
            Set.of("dormir", "dormi", "endormir", "sommeil", "dort");

    /** BIA-4 : aucun enonce ne s'adresse a l'enfant a la deuxieme personne du singulier. */
    private static final Pattern ADRESSE_PERSONNELLE = Pattern.compile("\\btu\\b|\\btes\\b|\\bton\\b|\\bta\\b");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static Domaine domaineDe(Question item) {
        String enonce = item.enonce();
        if (MOTS_HYGIENE_DU_CORPS.stream().anyMatch(enonce::contains)) {
            return Domaine.HYGIENE_DU_CORPS;
        }
        if (MOTS_ALIMENTATION.stream().anyMatch(enonce::contains)) {
            return Domaine.ALIMENTATION;
        }
        if (MOTS_SOMMEIL.stream().anyMatch(enonce::contains)) {
            return Domaine.SOMMEIL;
        }
        throw new IllegalStateException(
                "item " + item.id().value() + " ne correspond a aucun des trois domaines connus : "
                        + enonce);
    }

    /** FER-2 : au moins dix items servent cette ligne (quinze sur cp-qlm-hygiene). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * Les trois domaines du libelle (sommeil, alimentation, hygiene du corps) sont chacun en bonne
     * reponse au moins une fois. Chaque item resout vers exactement un des trois domaines
     * ({@link #domaineDe}), et cette partition n'omet aucun item.
     */
    @Test
    void lesTroisDomainesDuLibelleSontChacunServis() {
        List<Question> items = items();
        Map<Domaine, Integer> comptes = new EnumMap<>(Domaine.class);
        for (Question item : items) {
            comptes.merge(domaineDe(item), 1, Integer::sum);
        }

        assertThat(comptes.values().stream().mapToInt(Integer::intValue).sum())
                .as("chaque item du paquet resout vers exactement un domaine")
                .isEqualTo(items.size());
        for (Domaine domaine : Domaine.values()) {
            assertThat(comptes.getOrDefault(domaine, 0))
                    .as("le domaine %s doit etre servi par au moins un item", domaine)
                    .isGreaterThan(0);
        }
    }

    /**
     * BIA-4 : aucun item n'interroge l'enfant sur sa propre vie - aucun enonce ne s'adresse a lui a
     * la deuxieme personne du singulier.
     */
    @Test
    void bia4AucunEnonceNeSAdresseALEnfantALaDeuxiemePersonne() {
        for (Question item : items()) {
            assertThat(ADRESSE_PERSONNELLE.matcher(item.enonce()).find())
                    .as("item %s : BIA-4, l'enonce ne doit jamais s'adresser a l'enfant a la"
                            + " deuxieme personne (\"%s\")", item.id().value(), item.enonce())
                    .isFalse();
        }
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }
}
