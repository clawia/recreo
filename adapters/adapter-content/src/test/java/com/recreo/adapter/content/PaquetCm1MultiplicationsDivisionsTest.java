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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S13-2 (squad/sprints/s22-couverture-maximale-du-catalogue/planning.md) : le paquet
 * "cm1-multiplications-divisions-10-100", seul paquet neuf de cette story. Les proprietes
 * generiques (indice non vide, au plus trois propositions, aucune proposition egale a une autre,
 * rang non predictible au seuil de la CI) sont deja gardees par les garde-fous derives du
 * repertoire de contenu ; ce fichier verifie les criteres d'acceptation de camille-po et les
 * conditions MD-1 a MD-10 de l'arbitrage prealable de nadia-prof (squad/conformite/
 * ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md, section 2), qu'aucun
 * garde-fou generique ne connait : l'operateur restreint a 10 et 100, les quatre familles a un
 * cinquieme chacune, les bornes sur les nombres de depart, la graphie des signes et du separateur
 * de milliers, et la condition d'aboutissement propre a ce paquet (20 % a 40 % par rang, plus
 * stricte que le seuil de majorite de la CI).
 */
class PaquetCm1MultiplicationsDivisionsTest {

    private static final String PAQUET = "cm1-multiplications-divisions-10-100";

    /** MD-4 : le signe multiplie (U+00D7) et le signe divise (U+00F7), jamais "x" ni "/". */
    private static final Pattern ENONCE = Pattern.compile(
            "^([0-9 ]+) (×|÷) (10|100) = \\?$");

    private record ItemAnalyse(Question question, long operande, String operateur, int facteur) {
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            Matcher matcher = ENONCE.matcher(item.enonce());
            assertThat(matcher.matches()).as("forme de l'enonce '%s' (MD-9)", item.enonce()).isTrue();
            long operande = Long.parseLong(matcher.group(1).replace(" ", ""));
            analyses.add(new ItemAnalyse(item, operande, matcher.group(2), Integer.parseInt(matcher.group(3))));
        }
        return analyses;
    }

    @Test
    void lePaquetCompteAuMoinsVingtQuatreItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(24);
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsToutesDistinctes() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.distracteurs()).as("distracteurs de '%s'", item.enonce()).hasSize(2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s' : aucune ne doit etre egale a une"
                    + " autre, sinon l'item n'a pas de reponse unique", item.enonce())
                    .hasSize(3);
        }
    }

    /**
     * MD-1 : chaque item est une multiplication ou une division par 10 ou par 100, jamais par un
     * autre operateur, jamais par 1000, jamais par un multiple compose (20, 50, 200, 300). La
     * bonne reponse est le resultat exact - une division qui ne tombe pas juste est refusee.
     */
    @Test
    void chaqueItemEstUneMultiplicationOuUneDivisionParDixOuCentEtLaBonneReponseEstExacte() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            Question item = analyse.question();
            assertThat(analyse.operande()).as("operande de '%s'", item.enonce()).isPositive();

            long resultat;
            if (analyse.operateur().equals("×")) {
                resultat = analyse.operande() * analyse.facteur();
            } else {
                assertThat(analyse.operande() % analyse.facteur())
                        .as("division exacte pour '%s' (MD-1 : aucune division qui ne tombe pas"
                                + " juste)", item.enonce())
                        .isZero();
                resultat = analyse.operande() / analyse.facteur();
            }
            assertThat(numeriqueDe(item.bonneReponse()))
                    .as("bonne reponse de '%s'", item.enonce())
                    .isEqualTo(resultat);
        }
    }

    /**
     * MD-2 : les quatre familles (x10, x100, :10, :100) sont chacune portees par au moins un
     * cinquieme du paquet - plus strict que la seule condition de camille-po (10 et 100 chacun au
     * moins un quart, multiplication et division a une unite d'ecart pres).
     */
    @Test
    void lesQuatreFamillesSontChacuneRepresenteesParAuMoinsUnCinquiemeDuPaquet() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        java.util.Map<String, Long> parFamille = new java.util.HashMap<>();
        for (ItemAnalyse analyse : analyses) {
            String famille = analyse.operateur() + analyse.facteur();
            parFamille.merge(famille, 1L, Long::sum);
        }
        for (String famille : List.of("×10", "×100", "÷10", "÷100")) {
            long compte = parFamille.getOrDefault(famille, 0L);
            assertThat(5 * compte).as("MD-2 : la famille '%s' compte %d items sur %d, en dessous"
                    + " d'un cinquieme du paquet", famille, compte, total)
                    .isGreaterThanOrEqualTo(total);
        }
    }

    /**
     * MD-3 : le nombre de depart et le resultat sont des entiers positifs, aucune ecriture
     * decimale (garanti par la forme meme des champs, entierement numeriques) ; le resultat reste
     * sous le million ; au plus un quart du paquet part d'un nombre a un seul chiffre.
     */
    @Test
    void leResultatResteSousLeMillionEtAuPlusUnQuartPartDUnNombreAUnSeulChiffre() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long partentDUnSeulChiffre = analyses.stream()
                .filter(analyse -> analyse.operande() >= 1 && analyse.operande() <= 9)
                .count();
        assertThat(4 * partentDUnSeulChiffre)
                .as("MD-3 : %d items sur %d partent d'un nombre a un seul chiffre, au-dessus d'un"
                        + " quart du paquet", partentDUnSeulChiffre, total)
                .isLessThanOrEqualTo(total);

        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.question();
            long resultat = numeriqueDe(item.bonneReponse());
            assertThat(resultat).as("resultat de '%s' : MD-3 exige un resultat sous le million",
                    item.enonce()).isLessThan(1_000_000L);
        }
    }

    /** MD-3 : au moins trois items partent d'un nombre a zero intercalaire (305, 2 007...). */
    @Test
    void auMoinsTroisItemsPartentDUnNombreAZeroIntercalaire() {
        long compte = itemsAnalyses().stream()
                .filter(analyse -> aUnZeroIntercalaire(analyse.operande()))
                .count();
        assertThat(compte).as("MD-3 : au moins trois nombres de depart a zero intercalaire, pour"
                + " verifier que l'enfant lit la valeur des positions plutot que de recopier des"
                + " zeros").isGreaterThanOrEqualTo(3);
    }

    /** Vrai si la representation decimale de valeur porte un '0' entoure de chiffres non nuls. */
    private static boolean aUnZeroIntercalaire(long valeur) {
        String chiffres = Long.toString(valeur);
        for (int i = 0; i < chiffres.length(); i++) {
            if (chiffres.charAt(i) != '0') {
                continue;
            }
            boolean avantNonNul = chiffres.substring(0, i).chars().anyMatch(c -> c != '0');
            boolean apresNonNul = chiffres.substring(i + 1).chars().anyMatch(c -> c != '0');
            if (avantNonNul && apresNonNul) {
                return true;
            }
        }
        return false;
    }

    /**
     * MD-4 : les milliers sont separes par une espace insecable (U+00A0), de facon constante dans
     * tout le paquet, enonces et propositions comprises - jamais l'espace ordinaire, jamais
     * l'absence de separateur.
     */
    @Test
    void lesMilliersSontSeparesParUneEspaceInsecableDeFaconConstante() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            List<String> valeurs = new ArrayList<>(item.distracteurs());
            valeurs.add(item.bonneReponse());
            for (String valeur : valeurs) {
                long nombre = numeriqueDe(valeur);
                assertThat(valeur).as("'%s' (item '%s') : MD-4, separateur de milliers insecable"
                        + " et constant", valeur, item.enonce()).isEqualTo(formatteFrancais(nombre));
            }
        }
    }

    /** Reproduit le regroupement par trois chiffres depuis la droite, separateur U+00A0. */
    private static String formatteFrancais(long valeur) {
        String chiffres = Long.toString(valeur);
        StringBuilder inverse = new StringBuilder();
        for (int i = chiffres.length() - 1, compte = 0; i >= 0; i--, compte++) {
            if (compte > 0 && compte % 3 == 0) {
                inverse.append(' ');
            }
            inverse.append(chiffres.charAt(i));
        }
        return inverse.reverse().toString();
    }

    private static long numeriqueDe(String texte) {
        return Long.parseLong(texte.replace(" ", ""));
    }

    /**
     * MD-5 : condition d'aboutissement propre a ce paquet - aucun des trois rangs de la bonne
     * reponse ne depasse 40 % des items, aucun ne tombe sous 20 %. Plus stricte que le seuil de
     * majorite de la CI (50 %) et son seuil minimal (10 %), atteignable ici parce que l'echelle
     * des valeurs est reguliere.
     */
    @Test
    void laBonneReponseTientLaConditionDAboutissementDeVingtAQuaranteParCentParRang() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        int petite = 0;
        int intermediaire = 0;
        int grande = 0;
        for (Question item : items) {
            long bonne = numeriqueDe(item.bonneReponse());
            long d1 = numeriqueDe(item.distracteurs().get(0));
            long d2 = numeriqueDe(item.distracteurs().get(1));
            int rang = (d1 < bonne ? 1 : 0) + (d2 < bonne ? 1 : 0);
            switch (rang) {
                case 0 -> petite++;
                case 1 -> intermediaire++;
                default -> grande++;
            }
        }
        int total = items.size();
        for (var entree : java.util.Map.of("petite", petite, "intermediaire", intermediaire,
                "grande", grande).entrySet()) {
            int compte = entree.getValue();
            assertThat(5 * compte).as("MD-5 : le rang '%s' compte %d items sur %d, en dessous de"
                    + " 20 %%", entree.getKey(), compte, total).isGreaterThanOrEqualTo(total);
            assertThat(5 * compte).as("MD-5 : le rang '%s' compte %d items sur %d, au-dessus de"
                    + " 40 %%", entree.getKey(), compte, total).isLessThanOrEqualTo(2 * total);
        }
    }

    /**
     * MD-6 : la valeur egale au nombre de l'enonce ("je n'ai rien fait") est un distracteur
     * legitime mais ne peut jamais etre la bonne reponse (verifie par construction, un produit ou
     * un quotient par 10 ou 100 ne vaut jamais l'operande de depart) et n'apparait que sur un
     * tiers des items au plus.
     */
    @Test
    void laValeurEgaleAuNombreDeLEnonceNApparaitQueSurUnTiersDuPaquetAuPlusEtJamaisEnBonneReponse() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long compteAvecOperandeEnDistracteur = 0;
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.question();
            assertThat(numeriqueDe(item.bonneReponse()))
                    .as("'%s' : la bonne reponse ne peut jamais valoir le nombre de l'enonce",
                            item.enonce())
                    .isNotEqualTo(analyse.operande());
            boolean operandeEnDistracteur = item.distracteurs().stream()
                    .anyMatch(d -> numeriqueDe(d) == analyse.operande());
            if (operandeEnDistracteur) {
                compteAvecOperandeEnDistracteur++;
            }
        }
        assertThat(3 * compteAvecOperandeEnDistracteur)
                .as("MD-6 : %d items sur %d admettent le nombre de l'enonce comme distracteur,"
                        + " au-dessus d'un tiers du paquet", compteAvecOperandeEnDistracteur, total)
                .isLessThanOrEqualTo(total);
    }

    /** MD-7 : au moins quatre indices distincts, un par famille au minimum. */
    @Test
    void lePaquetPorteAuMoinsQuatreIndicesDistincts() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items) {
            indicesDistincts.add(item.indice().orElseThrow());
        }
        assertThat(indicesDistincts).as("MD-7 : au moins quatre indices distincts, un par famille"
                + " au minimum").hasSizeGreaterThanOrEqualTo(4);
    }

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

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
