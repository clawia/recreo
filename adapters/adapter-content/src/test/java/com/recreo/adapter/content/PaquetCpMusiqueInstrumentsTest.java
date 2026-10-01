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
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S45-MUS-INSTRUMENTS, le paquet {@code cp-musique-instruments}, ligne {@code MUS-CP-05} de
 * "Éducation musicale" (CP), seconde ligne de cette matiere.</b> Referentiel :
 * {@code squad/programme/education-musicale/competences-cp.md}, ligne 5, jugement de faisabilite
 * du 2026-09-03, corrige par l'arbitrage prealable de s45 (2026-10-01, section 3) : "familles
 * d'instruments sur le nom ecrit seul ; la reconnaissance auditive reste non couverte". Onze
 * items, deux taches (FER-3) : six items "instrument -&gt; famille" (items 1 a 6), ou chacune des
 * trois familles du referentiel (cordes, vents, percussions) est servie deux fois en bonne reponse
 * par deux instruments distincts (FER-1), et cinq items "famille -&gt; instrument" (items 7 a 11),
 * qui ne comptent pas pour FER-1 puisque la bonne reponse n'y est jamais un nom de famille.
 *
 * <p><b>MUS-CP-R1, reserve de s44 rendue executable sur ce paquet</b> (avis de faisabilite de s45
 * de {@code theo-archi}, section 5 ; arbitrage prealable de {@code nadia-prof} du 2026-10-01,
 * section 3, en reponse a Q3) : la forme "La guitare est-elle un instrument a cordes ou a vent ?",
 * qui nomme deux des trois propositions dans l'enonce, n'est pas admise. Aucun enonce de ce paquet
 * ne contient le texte d'une de ses propres propositions.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude de la famille de chaque instrument -
 * relecture de {@code nadia-prof} au Done pedagogique (arbitrage prealable de s45, section 7).</p>
 */
class PaquetCpMusiqueInstrumentsTest {

    private static final String PAQUET = "cp-musique-instruments";

    /** FER-2 (DEC-0064) : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Les trois familles du referentiel, chacune attendue en bonne reponse au moins une fois (FER-1). */
    private static final Set<String> FAMILLES = Set.of("les cordes", "les vents", "les percussions");

    /**
     * MUS-1 et MUS-2 : lexique interdit sur tout texte de tout item (enonce, bonne reponse,
     * distracteurs, indice). Contrairement au lexique de s44 ({@code cp-musique-vocabulaire}),
     * "frapp" et "taper" ne sont PAS interdits ici : une percussion se definit comme un instrument
     * que l'on frappe, et les interdire rendrait a tort rouge tout indice exact sur cette famille
     * (avis de faisabilite de s45, section 5, risque R4).
     */
    private static final Set<String> MOTS_INTERDITS_SOUS_CHAINE =
            Set.of("ecout", "entend", "adulte", "fredonn");

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

    /** La tache "instrument -> famille" : la bonne reponse est l'une des trois familles. */
    private static List<Question> itemsInstrumentVersFamille() {
        return items().stream().filter(item -> FAMILLES.contains(item.bonneReponse())).toList();
    }

    /** La tache "famille -> instrument" : la bonne reponse est un nom d'instrument, jamais une famille. */
    private static List<Question> itemsFamilleVersInstrument() {
        return items().stream().filter(item -> !FAMILLES.contains(item.bonneReponse())).toList();
    }

    /**
     * Minuscules et accents replies, sur le modele de {@code PaquetCm1HistoireGeographieTest} : le
     * lexique interdit est ecrit sans accent, le contenu servi est integralement accentue
     * ("Écoute" ne contient pas "ecout" tant que l'accent reste).
     */
    private static String replier(String texte) {
        return Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.FRENCH);
    }

    /** Tous les textes d'un item : enonce, bonne reponse, distracteurs, indice. */
    private static List<String> tousLesTextes(Question item) {
        List<String> textes = new ArrayList<>();
        textes.add(item.enonce());
        textes.add(item.bonneReponse());
        textes.addAll(item.distracteurs());
        item.indice().ifPresent(textes::add);
        return textes;
    }

    /** Toutes les propositions d'un item : bonne reponse et distracteurs. */
    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>();
        propositions.add(item.bonneReponse());
        propositions.addAll(item.distracteurs());
        return propositions;
    }

    /** FER-2 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("FER-2 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** FER-1 : les trois familles sont chacune en bonne reponse au moins une fois. */
    @Test
    void fer1LesTroisFamillesSontChacuneEnBonneReponse() {
        Set<String> reponses = itemsInstrumentVersFamille().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois familles doivent être en bonne réponse")
                .containsExactlyInAnyOrderElementsOf(FAMILLES);
    }

    /**
     * FER-1 : au moins deux instruments distincts par famille sont cites dans les enonces des
     * items "instrument -> famille" - l'instrument y est toujours le sujet de l'enonce, jamais une
     * proposition.
     */
    @Test
    void fer1AuMoinsDeuxInstrumentsDistinctsParFamilleDansLesEnonces() {
        java.util.Map<String, Set<String>> instrumentsParFamille = new java.util.HashMap<>();
        for (Question item : itemsInstrumentVersFamille()) {
            instrumentsParFamille
                    .computeIfAbsent(item.bonneReponse(), cle -> new HashSet<>())
                    .add(item.enonce());
        }
        for (String famille : FAMILLES) {
            assertThat(instrumentsParFamille.getOrDefault(famille, Set.of()))
                    .as("FER-1 : au moins deux instruments distincts cites en enonce pour %s", famille)
                    .hasSizeGreaterThanOrEqualTo(2);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Distinguees par la forme de la bonne reponse (avis de faisabilite de s45, section 5) : une
     * famille pour la premiere, un nom d'instrument pour la seconde.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int instrumentVersFamille = itemsInstrumentVersFamille().size();
        int familleVersInstrument = itemsFamilleVersInstrument().size();

        assertThat(instrumentVersFamille).as("la tache instrument -> famille est servie").isGreaterThan(0);
        assertThat(familleVersInstrument).as("la tache famille -> instrument est servie").isGreaterThan(0);
        assertThat(Math.min(instrumentVersFamille, familleVersInstrument))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** MUS-2 : aucun item ne porte de texte a voix haute - seul canal par lequel un adulte parle. */
    @Test
    void mus2AucunItemNePorteDeTexteAVoixHaute() {
        for (Question item : items()) {
            assertThat(item.texteAVoixHaute())
                    .as("item %s : MUS-2, aucun texte a voix haute", item.id().value())
                    .isEmpty();
        }
    }

    /**
     * MUS-1 et MUS-2 : aucun texte de l'item ne contient l'un des mots interdits, en sous-chaine,
     * sans tenir compte de la casse ni des accents. "frapp" et "taper" ne sont pas dans cette
     * liste (voir Javadoc de {@link #MOTS_INTERDITS_SOUS_CHAINE}).
     */
    @Test
    void mus1EtMus2AucunTexteNePorteLeLexiqueInterdit() {
        for (Question item : items()) {
            for (String texte : tousLesTextes(item)) {
                String normalise = replier(texte);
                for (String motInterdit : MOTS_INTERDITS_SOUS_CHAINE) {
                    assertThat(normalise)
                            .as("item %s : MUS-1/MUS-2, aucun texte ne contient \"%s\"",
                                    item.id().value(), motInterdit)
                            .doesNotContain(motInterdit);
                }
            }
        }
    }

    /**
     * MUS-CP-R1 : aucun enonce ne contient le texte d'une de ses propres propositions (bonne
     * reponse ou distracteur). C'est la famille de forme que l'arbitrage prealable de s45 refuse
     * explicitement ("La guitare est-elle un instrument a cordes ou a vent ?", qui nomme deux des
     * trois propositions).
     *
     * <p>Une proposition est nommee par son nom, sans son article et au singulier comme au pluriel :
     * "les cordes" est nomme par "a cordes", "les vents" par "a vent". Chercher la proposition
     * entiere laissait passer l'exemple meme que l'arbitrage refuse (MUS-CP-R3).</p>
     */
    @Test
    void musCpR1AucunEnonceNeContientLeTexteDUneDeSesPropresPropositions() {
        for (Question item : items()) {
            String enonceReplie = replier(item.enonce());
            for (String proposition : propositions(item)) {
                assertThat(nomDeLaProposition(proposition).matcher(enonceReplie).find())
                        .as("item %s : MUS-CP-R1, l'enonce \"%s\" nomme sa propre proposition \"%s\"",
                                item.id().value(), item.enonce(), proposition)
                        .isFalse();
            }
        }
    }

    /** Le nom d'une proposition, article retire, en mot entier, singulier ou pluriel. */
    private static Pattern nomDeLaProposition(String proposition) {
        String nom = replier(proposition).replaceFirst("^(le |la |les |l'|un |une |des )", "");
        String singulier = nom.endsWith("s") ? nom.substring(0, nom.length() - 1) : nom;
        return Pattern.compile("\\b" + Pattern.quote(singulier) + "s?\\b");
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

    /** Chaque item porte un indice. */
    @Test
    void chaqueItemPorteUnIndice() {
        for (Question item : items()) {
            assertThat(item.indice())
                    .as("item %s : indice present et non vide", item.id().value())
                    .isPresent();
            assertThat(item.indice().orElseThrow()).isNotBlank();
        }
    }

    /** Ligne de referentiel declaree, MUS-CP-05 uniquement. */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }
}
