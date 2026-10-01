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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S45-EPS-ROLES, le paquet {@code cm1-eps-roles}, ligne {@code EPS-CM1-09}</b> d'education
 * physique et sportive (CM1). Referentiel : {@code
 * squad/programme/education-physique-et-sportive/competences-cm1.md}, ligne 9, jugement de
 * faisabilite du 2026-09-03.
 *
 * <p><b>EPS-10 (nadia-prof, discriminant CM1/CP pose avant l'ecriture du paquet, section 4.2 de
 * l'arbitrage prealable de s45).</b> Quatre exigences : (1) aucun item ne porte sur un des six jeux
 * de {@code cp-eps-regles-jeu-collectif} ; (2) la bonne reponse est toujours l'acte du role, jamais
 * ce que devient un joueur ; (3) chacun des trois roles porte au moins trois items ; (4) {@code
 * CYC-1} dans les deux sens. Ce test verifie les clauses 1, 3 et 4 mecaniquement. La clause 2 est un
 * jugement de sens (la bonne reponse est-elle vraiment l'acte du role, ou une consequence pour un
 * joueur deguisee) que seule {@code nadia-prof} tranche a l'audit : l'exemple meme de nadia-prof sur
 * l'observateur nomme les joueurs dans l'enonce (section 4.4 de l'arbitrage), donc nommer un joueur
 * n'est pas en soi la faute que la clause 2 exclut - c'est la position de bonne reponse qui compte,
 * pas la presence d'un prenom dans le texte.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : que chaque decision d'arbitre, chaque releve d'observateur
 * et chaque score de marqueur soit effectivement l'acte du role et non une consequence pour un
 * joueur (EPS-10 clause 2, relecture de sens) ; l'exactitude de chaque regle sportive citee -
 * relecture de {@code nadia-prof} au Done pedagogique.</p>
 */
class PaquetCm1EpsRolesTest {

    private static final String PAQUET = "cm1-eps-roles";

    /** DEC-0064 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /**
     * EPS-10, clause 3 : chaque role est classe par un marqueur textuel propre - le mot du role
     * lui-meme, porte par l'enonce (FER-1). {@link ContentSource#next} tire dans un ordre gouverne
     * par le hasard, jamais par l'ordre de declaration du YAML : la classification se fait donc par
     * un marqueur textuel, jamais par un index, pour rester vraie quel que soit l'ordre de tirage.
     */
    private static final String MARQUEUR_ARBITRE = "l'arbitre";
    private static final String MARQUEUR_OBSERVATEUR = "l'observateur";
    private static final String MARQUEUR_MARQUEUR = "le marqueur";

    /** EPS-10, clause 3 : aucun role ne porte moins de trois items. */
    private static final int PLANCHER_PAR_ROLE = 3;

    /**
     * EPS-10, clause 1 : patrons des six jeux deja servis par cp-eps-regles-jeu-collectif, absents
     * de ce paquet. Verifie sur le nom du jeu, forme la plus simple a mordre sans lire le sens.
     */
    private static final List<String> JEUX_DEJA_SERVIS_AU_CP = List.of(
            "1, 2, 3, soleil", "chat perché", "épervier", "balle au prisonnier",
            "loup et des moutons", "déménageurs");

    /**
     * EPS-10, clause 4 (CYC-1) : les bonnes reponses des onze items de cp-eps-regles-jeu-collectif,
     * toutes des consequences pour un joueur nomme.
     */
    private static final Set<String> BONNES_REPONSES_DU_CP = Set.of(
            "quand le meneur se retourne", "en montant sur un point en hauteur",
            "il devient épervier à son tour", "il va dans le camp adverse",
            "il peut toucher d'autres moutons", "un seul objet", "rien, Hugo est perché",
            "Malo devient épervier à son tour", "Aya est attrapée",
            "Hugo doit reposer un des deux objets", "Hugo doit retourner au départ");

    /** EPS-10, clause 4 (CYC-1) : les vingt-deux distracteurs des onze items du CP. */
    private static final Set<String> DISTRACTEURS_DU_CP = Set.of(
            "quand on touche le mur", "quand un joueur crie stop", "en fermant les yeux",
            "en criant le plus fort", "il est éliminé du jeu", "il doit recommencer de l'autre côté",
            "il gagne un point", "il devient arbitre du jeu", "il doit attendre la fin de la partie",
            "il redevient mouton peu après", "deux objets à la fois",
            "autant d'objets que l'on veut", "Hugo est attrapé", "Hugo devient chat",
            "Malo est éliminé du jeu", "Malo doit attendre le prochain tour", "Aya devient meneuse",
            "rien ne se passe", "Hugo doit recommencer au départ",
            "Hugo échange sa place avec un camarade", "Hugo doit rester immobile pendant un tour",
            "Hugo change de place avec un autre joueur");

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

    /** DEC-0064 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("DEC-0064 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /**
     * Criteres d'acceptation de la story et EPS-10, clause 3 : les trois roles sont chacun
     * representes, et aucun n'en porte moins de trois items. Chaque item est classe par le mot de
     * son role, porte par l'enonce ; un item qui ne porterait aucun des trois marqueurs (ou plus
     * d'un) ferait echouer la somme des trois lots, qui doit epuiser {@code items().size()}.
     */
    @Test
    void lesTroisRolesSontChacunRepresentesParAuMoinsTroisItems() {
        List<Question> items = items();
        int total = items.size();

        long arbitre = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_ARBITRE))
                .count();
        long observateur = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_OBSERVATEUR))
                .count();
        long marqueur = items.stream()
                .filter(item -> item.enonce().toLowerCase(Locale.FRENCH).contains(MARQUEUR_MARQUEUR))
                .count();

        assertThat(arbitre + observateur + marqueur)
                .as("chaque item doit porter exactement le mot d'un seul role, sans chevauchement ni"
                        + " item hors des trois roles")
                .isEqualTo(total);

        assertThat(arbitre)
                .as("critere d'acceptation : le role d'arbitre porte au moins trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_ROLE);
        assertThat(observateur)
                .as("critere d'acceptation : le role d'observateur porte au moins trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_ROLE);
        assertThat(marqueur)
                .as("critere d'acceptation : le role de marqueur porte au moins trois items")
                .isGreaterThanOrEqualTo(PLANCHER_PAR_ROLE);
    }

    /**
     * EPS-10, clause 1 : aucun item ne reprend un des six jeux deja servis par
     * cp-eps-regles-jeu-collectif.
     */
    @Test
    void eps10Clause1AucunItemNePorteSurUnJeuDejaServiAuCp() {
        for (Question item : items()) {
            String enonce = item.enonce().toLowerCase(Locale.FRENCH);
            for (String jeu : JEUX_DEJA_SERVIS_AU_CP) {
                assertThat(enonce)
                        .as("item %s : ce jeu est deja servi par cp-eps-regles-jeu-collectif, ce"
                                + " paquet de CM1 doit porter un autre jeu (EPS-10, clause 1)",
                                item.id().value())
                        .doesNotContain(jeu);
            }
        }
    }

    /**
     * EPS-10, clause 4 (CYC-1) : aucune bonne reponse de cp-eps-regles-jeu-collectif ne devient un
     * distracteur ici.
     */
    @Test
    void cyc1AucuneBonneReponseDuCpNeDevientUnDistracteurIci() {
        for (Question item : items()) {
            for (String distracteur : item.distracteurs()) {
                assertThat(BONNES_REPONSES_DU_CP)
                        .as("item %s : le distracteur \"%s\" est une bonne reponse d'un item du CP"
                                + " (EPS-10 clause 4, CYC-1)", item.id().value(), distracteur)
                        .doesNotContain(distracteur);
            }
        }
    }

    /**
     * EPS-10, clause 4 (CYC-1), sens inverse : aucune bonne reponse de ce paquet ne devient un
     * distracteur de cp-eps-regles-jeu-collectif.
     */
    @Test
    void cyc1AucuneBonneReponseDIciNeDevientUnDistracteurDuCp() {
        for (Question item : items()) {
            assertThat(DISTRACTEURS_DU_CP)
                    .as("item %s : la bonne reponse \"%s\" est un distracteur d'un item du CP"
                            + " (EPS-10 clause 4, CYC-1)", item.id().value(), item.bonneReponse())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /**
     * EPS-10, clause 2 (indice partiel, pas une preuve complete) : la bonne reponse ne nomme jamais
     * un joueur - a la difference de la tache B du CP, dont la bonne reponse est systematiquement
     * une consequence pour un joueur nomme ("Hugo doit...", "Aya est attrapee"). L'enonce peut en
     * revanche nommer des joueurs (l'exemple meme de nadia-prof sur l'observateur le fait), donc ce
     * test ne porte que sur la bonne reponse, jamais sur l'enonce ou les distracteurs.
     */
    @Test
    void eps10Clause2LaBonneReponseNeNommeJamaisUnJoueur() {
        List<String> prenoms = List.of("Léa", "Hugo", "Aya", "Malo");
        for (Question item : items()) {
            for (String prenom : prenoms) {
                assertThat(item.bonneReponse())
                        .as("item %s : la bonne reponse ne doit jamais nommer un joueur, elle doit"
                                + " etre l'acte du role (EPS-10 clause 2)", item.id().value())
                        .doesNotContain(prenom);
            }
        }
    }

    /** EPS-3 : aucun enonce ne contient "que fais-tu" ni "que dois-tu". */
    @Test
    void eps3AucunEnonceNeContientQueFaisTuNiQueDoisTu() {
        for (Question item : items()) {
            String enonce = item.enonce().toLowerCase(Locale.FRENCH);
            assertThat(enonce)
                    .as("item %s : EPS-3 interdit \"que fais-tu\"", item.id().value())
                    .doesNotContain("que fais-tu");
            assertThat(enonce)
                    .as("item %s : EPS-3 interdit \"que dois-tu\"", item.id().value())
                    .doesNotContain("que dois-tu");
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * EPS-1 : chaque ligne {@code - enonce:} du fichier brut porte un marqueur {@code # EPS-1:
     * rappelee} ou {@code # EPS-1: universelle}, lu dans le fichier brut comme les marqueurs
     * {@code HIS-3} (avis de faisabilite de s45, section 6.2).
     */
    @Test
    void eps1ChaqueLigneDItemPorteUnMarqueurRappeleeOuUniverselle() {
        List<String> lignesDItem = texteBrutDuPaquet().lines()
                .filter(ligne -> ligne.contains("- enonce:")).toList();

        assertThat(lignesDItem).as("le paquet porte bien des lignes d'item").hasSize(12);

        for (String ligne : lignesDItem) {
            boolean rappelee = ligne.contains("# EPS-1: rappelee");
            boolean universelle = ligne.contains("# EPS-1: universelle");
            assertThat(rappelee || universelle)
                    .as("ligne '%s' : porte un marqueur # EPS-1: rappelee ou # EPS-1: universelle",
                            ligne)
                    .isTrue();
        }
    }

    /**
     * Chaque item declare uniquement EPS-CM1-09, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et aucun
     * item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementEpsCm109() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCm1EpsRolesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("EPS-CM1-09");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1EpsRolesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
