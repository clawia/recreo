package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanics.saisie.Saisie;
import com.recreo.domain.mechanics.saisie.SaisieMove;
import com.recreo.domain.mechanics.saisie.SaisieSession;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S26-L5, le paquet {@code cp-mot-dicte}.</b> Encode ce qui est calculable sur la fiche de
 * conformite (`squad/conformite/FICHE-saisie-mot-dicte-cp.md`) et l'arbitrage de nadia-prof
 * (section 4.5) : le plafond de vingt items, l'enonce invariant (VOI-13), et la tolerance de forme
 * que SAI-1, SAI-3 et SAI-4 promettent deja a la mecanique de saisie.
 *
 * <p><b>Ce que cette classe ne juge pas</b> : la regularite reelle des mots (VOI-9) reste jugee par
 * nadia-prof - "un mot dont l'ecriture ne se deduit pas du son n'evalue pas cette ligne". `VOI-8`
 * (l'adulte ne l'epelle jamais) n'a aucune trace testable, nadia-prof l'ecrit elle-meme.</p>
 *
 * <p><b>Pourquoi {@code VOI-10} n'est pas verifie ici, et ce n'est pas un oubli</b> : cette ligne
 * est une dictee - {@code texteAVoixHaute} doit necessairement porter le mot que l'enfant doit
 * ecrire (VOI-8), qui est aussi {@code formeDeReference}. La fiche le dit elle-meme : le champ "ne
 * contient jamais la reponse ecrite attendue en toutes lettres <b>au-dela du mot lui-meme</b>" - le
 * mot lui-meme y est attendu. <b>Amendement du 2026-09-01</b> : {@code VOI-14} point 2
 * (nadia-prof, section 16.3) etablit que {@code VOI-1} n'a jamais eu d'objet sur un paquet de
 * saisie - l'enfant n'y choisit pas, il produit - et lui substitue l'interdiction de l'<b>epellation</b>.
 * {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest} est branche sur le contenu de production et
 * <b>ce paquet y passe</b> : dire le mot est l'exercice, l'epeler est le defaut (ADR-0076).</p>
 */
class PaquetCpMotDicteTest {

    private static final String PAQUET = "cp-mot-dicte";

    private static final int PLAFOND_ITEMS = 20;

    private static ContentSource paquet() {
        return new YamlContentPackLoader("").chargerPaquet(PAQUET);
    }

    private static List<ReponseAEcrire> items() {
        ContentSource contenu = paquet();
        RandomSource hasard = borne -> 0;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> tires = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            tires.add(item);
            dejaVus.add(item.id());
        }
        return ClassementDesItems.demandantUneReponseEcrite(tires);
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /** VOI-13 : l'enonce que l'enfant lit est invariant et ne nomme jamais le mot a ecrire. */
    @Test
    void lEnonceEstInvariantEtNeNommeJamaisLeMotAEcrire() {
        List<ReponseAEcrire> items = items();
        assertThat(items).isNotEmpty();
        Set<String> enonces = new HashSet<>();
        for (ReponseAEcrire item : items) {
            enonces.add(item.enonce());
            assertThat(replier(item.enonce()))
                    .as("item %s : l'enonce ne doit pas nommer le mot a ecrire", item.id().value())
                    .doesNotContain(replier(item.formeDeReference()));
            assertThat(replier(item.indice().orElseThrow()))
                    .as("item %s : l'indice ne doit pas nommer le mot a ecrire", item.id().value())
                    .doesNotContain(replier(item.formeDeReference()));
        }
        assertThat(enonces)
                .as("VOI-13 : l'enonce est invariant sur tout le paquet, c'est texteAVoixHaute qui"
                        + " distingue les tours (ADR-0075, decision 3)")
                .hasSize(1);
    }

    /**
     * SAI-1, SAI-3, SAI-4 : la mecanique de saisie accepte deja une reponse sans accent, en
     * majuscules, et entouree de blancs. Rejoue chaque item sur une partie neuve, comme
     * {@code PaquetCpComplements10SaisieTest}.
     */
    @Test
    void chaqueMotEstAccepteSansAccentEnMajusculesEtAvecDesBlancsAutour() {
        List<ReponseAEcrire> items = items();
        for (int rang = 0; rang < items.size(); rang++) {
            ReponseAEcrire item = items.get(rang);
            String motSansAccent = Normalizer.normalize(item.formeDeReference(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "");
            for (String forme : List.of(
                    item.formeDeReference(),
                    motSansAccent,
                    item.formeDeReference().toUpperCase(Locale.FRENCH),
                    "  " + item.formeDeReference() + "  ")) {
                assertThat(verdictJusteSur(rang, item, forme))
                        .as("item %s (\"%s\") : la forme \"%s\" doit etre acceptee (SAI-1, SAI-3,"
                                        + " SAI-4)",
                                item.id().value(), item.formeDeReference(), forme)
                        .isTrue();
            }
        }
    }

    private static boolean verdictJusteSur(int rang, ReponseAEcrire item, String forme) {
        Saisie mecanique = new Saisie();
        SaisieSession saisie =
                (SaisieSession) mecanique.start(paquet(), borne -> rang < borne ? rang : 0, 1);
        assertThat(saisie.enonceManche())
                .as("la manche visee n'a pas ete atteinte pour l'item %s", item.id().value())
                .contains(item.enonce());
        GameSession apres = mecanique.apply(saisie, new SaisieMove(forme));
        return ((SaisieSession) apres).dernierVerdict().map(verdict -> verdict.juste()).orElse(false);
    }

    private static String replier(String texte) {
        String decompose = Normalizer.normalize(texte, Normalizer.Form.NFD);
        StringBuilder replie = new StringBuilder(decompose.length());
        for (int rang = 0; rang < decompose.length(); rang++) {
            char signe = decompose.charAt(rang);
            if (Character.getType(signe) == Character.NON_SPACING_MARK) {
                continue;
            }
            replie.append(signe);
        }
        return replie.toString().toLowerCase(Locale.ROOT);
    }
}
