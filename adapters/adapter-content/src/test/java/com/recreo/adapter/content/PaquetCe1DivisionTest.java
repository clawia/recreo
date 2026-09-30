package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S44-MAT-DIVISION (MAT-CE1-18, DEC-0099 section 9.6) : le paquet "ce1-division", joue par
 * "bandit-manchot-division-ce1". Arbitrage prealable de nadia-prof, conditions DIV-CE1-1 a
 * DIV-CE1-5 (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md, section 4), et
 * DIV-CE1-6 (meme fichier, addendum du 2026-09-25, section 11.2), qui pose la frontiere avec
 * MAT-CE1-06 (sens de la multiplication).
 *
 * <p>DIV-CE1-5 et DIV-CE1-6 sont testees dans leur forme amendee le 2026-09-25 (section 11.7,
 * reponses de nadia-prof a Q2 et Q3 de theo-archi, meme fichier) : le troisieme cas de DIV-CE1-5
 * est le diviseur recopie ({@code b}), et DIV-CE1-6 s'etend point par point - point 1 au champ
 * {@code tableauSupport}, point 2 a {@code explication}, {@code texteSupport} et
 * {@code texteAVoixHaute} - conformement a la table de routage de la section 11.5 de l'amendement,
 * qui confie au developpeur affecte l'extension du test du paquet dans le meme diff.
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables. L'exactitude arithmetique est
 * recalculee ici a partir des deux nombres lus dans l'enonce, jamais recopiee. Les proprietes
 * generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme paquet, longueur
 * d'enonce au CE1, rang de la bonne reponse) sont deja derivees du repertoire de contenu par les
 * garde-fous existants (ADR-0101, {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest})
 * et ne sont pas repetees ici.</p>
 */
class PaquetCe1DivisionTest {

    private static final String PAQUET = "ce1-division";

    /** DIV-CE1-2 : le partage - le nombre de parts est donne, la valeur d'une part est cherchee. */
    private static final Pattern MARQUE_PARTAGE = Pattern.compile("partagé");

    /** DIV-CE1-2 : le groupement - la valeur d'une part est donnee, le nombre de parts est cherche. */
    private static final Pattern MARQUE_GROUPEMENT = Pattern.compile("peut-on (faire|installer)");

    /**
     * DIV-CE1-3, elargie par DIV-CE1-6 point 2 : aucune ecriture operatoire - un des six signes
     * employe entre deux termes numeriques (un chiffre ou le "?" d'une inconnue).
     */
    private static final Pattern ECRITURE_OPERATOIRE =
            Pattern.compile("[\\d?][\\s\\u00A0]*[+\\-x×:÷][\\s\\u00A0]*[\\d?]");

    private static List<Question> tousLesItems() {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        ContentSource contenu = new YamlContentPackLoader().chargerPaquet(PAQUET);
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    /** Les deux nombres ecrits en chiffres dans l'enonce : {@code a} le plus grand, {@code b} l'autre. */
    private record DividendeEtDiviseur(int a, int b) {
    }

    private static DividendeEtDiviseur dividendeEtDiviseur(Question item) {
        Matcher m = Pattern.compile("\\d+").matcher(item.enonce());
        List<Integer> nombres = new ArrayList<>();
        while (m.find()) {
            nombres.add(Integer.parseInt(m.group()));
        }
        assertThat(nombres)
                .as("'%s' : exactement deux nombres ecrits en chiffres, le dividende et le"
                        + " diviseur", item.enonce())
                .hasSize(2);
        int a = Math.max(nombres.get(0), nombres.get(1));
        int b = Math.min(nombres.get(0), nombres.get(1));
        return new DividendeEtDiviseur(a, b);
    }

    @Test
    void chaqueItemPorteUneDivisionExacteDansLesBornesAttendues() {
        for (Question item : tousLesItems()) {
            DividendeEtDiviseur nombres = dividendeEtDiviseur(item);
            assertThat(nombres.a())
                    .as("'%s' : le dividende est un entier de 4 a 60 (DIV-CE1-1)", item.enonce())
                    .isBetween(4, 60);
            assertThat(nombres.b())
                    .as("'%s' : le diviseur est un entier de 2 a 10 (DIV-CE1-1)", item.enonce())
                    .isBetween(2, 10);
            assertThat(nombres.a() % nombres.b())
                    .as("'%s' : la division tombe juste, sans reste (DIV-CE1-1)", item.enonce())
                    .isZero();
            assertThat(item.bonneReponse())
                    .as("'%s' : la bonne reponse est le quotient exact (DIV-CE1-1)", item.enonce())
                    .isEqualTo(String.valueOf(nombres.a() / nombres.b()));
        }
    }

    @Test
    void chaqueItemPorteExactementUneMarqueDeSensEtChaqueSensAtteintUnTiers() {
        List<Question> items = tousLesItems();
        int partage = 0;
        int groupement = 0;
        for (Question item : items) {
            boolean estPartage = MARQUE_PARTAGE.matcher(item.enonce()).find();
            boolean estGroupement = MARQUE_GROUPEMENT.matcher(item.enonce()).find();
            assertThat(estPartage ^ estGroupement)
                    .as("'%s' : porte exactement une des deux marques de sens, partage ou"
                            + " groupement (DIV-CE1-2)", item.enonce())
                    .isTrue();
            if (estPartage) {
                partage++;
            } else {
                groupement++;
            }
        }
        double ratioPartage = (double) partage / items.size();
        double ratioGroupement = (double) groupement / items.size();
        assertThat(ratioPartage)
                .as("DIV-CE1-2 : le partage atteint au moins un tiers du paquet (%d sur %d)",
                        partage, items.size())
                .isGreaterThanOrEqualTo(1.0 / 3.0);
        assertThat(ratioGroupement)
                .as("DIV-CE1-2 : le groupement atteint au moins un tiers du paquet (%d sur %d)",
                        groupement, items.size())
                .isGreaterThanOrEqualTo(1.0 / 3.0);
    }

    @Test
    void aucuneEcritureOperatoireNiAucunSigneEgalNullePart() {
        for (Question item : tousLesItems()) {
            List<String> champs = new ArrayList<>(item.distracteurs());
            champs.add(item.enonce());
            champs.add(item.bonneReponse());
            item.indice().ifPresent(champs::add);
            // DIV-CE1-6 point 2, elargi le 2026-09-25 (section 11.7) : tout texte de l'item, pas
            // seulement l'enonce, les propositions et l'indice.
            item.explication().ifPresent(champs::add);
            item.texteSupport().ifPresent(champs::add);
            item.texteAVoixHaute().ifPresent(champs::add);
            for (String champ : champs) {
                assertThat(ECRITURE_OPERATOIRE.matcher(champ).find())
                        .as("'%s' (item '%s') : aucune ecriture operatoire entre deux nombres"
                                + " (DIV-CE1-3, DIV-CE1-6 point 2)", champ, item.enonce())
                        .isFalse();
                assertThat(champ)
                        .as("'%s' (item '%s') : jamais le signe \"=\" (DIV-CE1-3, DIV-CE1-6"
                                + " point 2)", champ, item.enonce())
                        .doesNotContain("=");
            }
        }
    }

    @Test
    void leDiviseurDeuxResteMinoritaireEtNeReprendJamaisLesMoities() {
        List<Question> items = tousLesItems();
        long diviseurDeux = 0;
        for (Question item : items) {
            DividendeEtDiviseur nombres = dividendeEtDiviseur(item);
            if (nombres.b() == 2) {
                diviseurDeux++;
            }
            List<String> champs = new ArrayList<>(item.distracteurs());
            champs.add(item.enonce());
            item.indice().ifPresent(champs::add);
            for (String champ : champs) {
                assertThat(champ.toLowerCase(Locale.FRENCH))
                        .as("'%s' (item '%s') : jamais le mot \"moitie\" (DIV-CE1-4)", champ,
                                item.enonce())
                        .doesNotContain("moiti");
            }
        }
        assertThat((double) diviseurDeux / items.size())
                .as("DIV-CE1-4 : le diviseur 2 reste au plus un tiers du paquet (%d sur %d)",
                        diviseurDeux, items.size())
                .isLessThanOrEqualTo(1.0 / 3.0);
    }

    @Test
    void chaqueItemPorteDesDistracteursEntiersPositifsDistinctsDontUnEstUneErreurIdentifiee() {
        for (Question item : tousLesItems()) {
            DividendeEtDiviseur nombres = dividendeEtDiviseur(item);
            int bonneReponse = Integer.parseInt(item.bonneReponse());
            Set<Integer> erreursIdentifiees =
                    Set.of(nombres.a() * nombres.b(), nombres.a() - nombres.b(), nombres.b());

            List<Integer> distracteurs = new ArrayList<>();
            for (String distracteur : item.distracteurs()) {
                int valeur = Integer.parseInt(distracteur);
                assertThat(valeur)
                        .as("'%s' (item '%s') : un distracteur est un entier strictement positif"
                                + " (DIV-CE1-5)", distracteur, item.enonce())
                        .isPositive();
                distracteurs.add(valeur);
            }
            assertThat(distracteurs)
                    .as("item '%s' : les distracteurs sont distincts entre eux et de la bonne"
                            + " reponse (DIV-CE1-5)", item.enonce())
                    .doesNotHaveDuplicates()
                    .doesNotContain(bonneReponse);
            assertThat(distracteurs)
                    .as("item '%s' : au moins un distracteur correspond a une erreur identifiee -"
                            + " le produit, la difference, ou le diviseur recopie (DIV-CE1-5,"
                            + " amende le 2026-09-25 section 11.7)", item.enonce())
                    .anyMatch(erreursIdentifiees::contains);
        }
    }

    @Test
    void aucunItemNePorteDeSupportVisuelEtLeDividendeDesigneUneQuantiteDObjetsNommes() {
        for (Question item : tousLesItems()) {
            assertThat(item.illustration())
                    .as("item '%s' : aucune illustration (DIV-CE1-6 point 1)", item.enonce())
                    .isEmpty();
            // DIV-CE1-6 point 1, elargi le 2026-09-25 (section 11.7) : ni illustration ni
            // tableauSupport, quel que soit le champ qui porterait une grille.
            assertThat(item.tableauSupport())
                    .as("item '%s' : aucun tableauSupport (DIV-CE1-6 point 1)", item.enonce())
                    .isEmpty();

            DividendeEtDiviseur nombres = dividendeEtDiviseur(item);
            Matcher dividendeSuiviDUnMot =
                    Pattern.compile("\\b" + nombres.a() + "\\b\\s+(\\p{L})").matcher(item.enonce());
            assertThat(dividendeSuiviDUnMot.find())
                    .as("'%s' : le dividende est immediatement suivi, sans ponctuation"
                            + " intercalee, du nom de l'objet compte (DIV-CE1-6 point 3)",
                            item.enonce())
                    .isTrue();
        }
    }

    /**
     * Le paquet ne declare que sa ligne, au niveau du paquet (ADR-0081 clause 6) : aucun item n'a
     * besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void lePaquetDeclareUniquementMatCe118() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCe1DivisionTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("MAT-CE1-18");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
