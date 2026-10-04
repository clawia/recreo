package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S50-ART ({@code docs/backlog/backlog.md}, story "S50-ART : decrire une oeuvre (ART-CP-09)"), le
 * paquet {@code cp-decrire-une-oeuvre}, ligne {@code ART-CP-09} - decrire une oeuvre par son sujet,
 * ses formes et ses materiaux. Arbitrage pedagogique prealable de nadia-prof
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s50-art-cp.md}, Q4 : aucune couleur ; Q2 : le texte
 * visible d'une estampe ne se nomme nulle part) et {@code DEC-0109} (section 10, perimetre reduit a
 * {@code ART-CP-09} seule, vivier final a huit oeuvres).
 *
 * <p><b>Ce que ce test verifie, a la difference de {@code LIllustrationDeContenuResoutEtAucuneNeDortTest}
 * et de {@code ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest}.</b> Ces deux
 * garde-fous d'archi prouvent qu'une illustration declaree resout vers un fichier reel et que ce
 * fichier porte sa preuve de licence ; ils ne lisent jamais le contenu des huit items eux-memes. Ce
 * test-ci porte exactement ce que l'arbitrage prealable route au developpeur et qu'aucun garde-fou
 * transverse ne pouvait deja attraper : le vivier exact des huit oeuvres (toutes utilisees, chacune
 * une seule fois), l'absence de tout mot de couleur (Q4, {@code ART-1}), l'absence de toute mention
 * du texte visible sur les deux estampes (Q2), et que chaque item commence par {@code enonce}
 * (ADR-0073 clause 6, deja mordue ailleurs mais reverifiee ici item par item sur ce paquet precis).
 */
class PaquetCpDecrireUneOeuvreTest {

    private static final String PAQUET = "cp-decrire-une-oeuvre";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCpDecrireUneOeuvreTest";

    /**
     * Le vivier final d'ART-CP-09 (docs/backlog/backlog.md, section "Contexte" de la story ;
     * DEC-0109 section 10.3) : huit identifiants, chacun utilise exactement une fois par ce paquet.
     */
    private static final Set<String> VIVIER = Set.of(
            "oeuvre-hippopotame", "oeuvre-moissonneurs", "oeuvre-chambre", "oeuvre-quatre-saisons",
            "oeuvre-grande-vague", "oeuvre-marche-aux-chevaux", "oeuvre-rue-sous-la-pluie",
            "oeuvre-femme-au-luth");

    /**
     * Q4 de l'arbitrage prealable ({@code ART-1}) : aucune des huit oeuvres ne se decrit par une
     * couleur. Liste de mots de couleur courants du francais, formes masculines, feminines et
     * plurielles - une liste d'autorisation du cote refuse (ADR-0039) : elle n'a pas a etre
     * exhaustive sur tout le lexique francais, seulement sur les mots qu'un redacteur de contenu
     * ecrirait pour nommer une couleur.
     */
    private static final List<String> MOTS_DE_COULEUR = List.of(
            "rouge", "rouges", "bleu", "bleue", "bleus", "bleues", "vert", "verte", "verts", "vertes",
            "jaune", "jaunes", "orange", "oranges", "violet", "violette", "violets", "violettes",
            "rose", "roses", "marron", "marrons", "noir", "noire", "noirs", "noires", "blanc",
            "blanche", "blancs", "blanches", "gris", "grise", "grises", "beige", "beiges", "doré",
            "dorée", "dorés", "dorées", "argenté", "argentée", "argentés", "argentées", "turquoise",
            "turquoises", "pourpre", "pourpres", "mauve", "mauves", "kaki", "kakis", "ocre", "ocres",
            "ivoire", "ivoires", "écarlate", "écarlates", "multicolore", "multicolores");

    private static final Pattern VERIFICATEUR_COULEUR = Pattern.compile(
            "\\b(" + String.join("|", MOTS_DE_COULEUR) + ")\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

    /**
     * Q2 de l'arbitrage prealable : oeuvre-grande-vague et oeuvre-rue-sous-la-pluie portent un texte
     * visible (cartouche japonais, trace de signature), recevable a la seule condition qu'aucun
     * champ du paquet n'en parle. Liste d'autorisation, meme motif que {@link #MOTS_DE_COULEUR}.
     */
    private static final List<String> MOTS_DE_TEXTE_VISIBLE = List.of(
            "cartouche", "cartouches", "signature", "signatures", "écriture", "écritures",
            "inscription", "inscriptions", "cachet", "cachets", "japonais", "japonaise", "japonaises",
            "latin", "latine", "latines", "lettre", "lettres", "lire", "lis", "écrit", "écrits",
            "écrite", "écrites");

    private static final Pattern VERIFICATEUR_TEXTE_VISIBLE = Pattern.compile(
            "\\b(" + String.join("|", MOTS_DE_TEXTE_VISIBLE) + ")\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static ContentSource paquet(String racineClasspath, String paquetId) {
        return new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
    }

    private static List<Question> tousLesItems(ContentSource source) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = source.next(hasard, dejaVus).orElse(null); item != null;
                item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    /** Tous les champs de texte lus par l'enfant (ou l'adulte) sur un item : enonce, propositions,
     * indice, texte alternatif. */
    private static List<String> champsDeTexte(Question item) {
        List<String> champs = new ArrayList<>();
        champs.add(item.enonce());
        champs.add(item.bonneReponse());
        champs.addAll(item.distracteurs());
        item.indice().ifPresent(champs::add);
        item.illustrationTexteAlternatif().ifPresent(champs::add);
        return champs;
    }

    private static void verifierAucuneCouleur(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsDeTexte(item)) {
                assertThat(VERIFICATEUR_COULEUR.matcher(champ).find())
                        .as("'%s' : aucun mot de couleur n'est servi (ART-1, Q4 de l'arbitrage"
                                + " prealable du 2026-10-04)", champ)
                        .isFalse();
            }
        }
    }

    private static void verifierAucuneMentionDuTexteVisible(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsDeTexte(item)) {
                assertThat(VERIFICATEUR_TEXTE_VISIBLE.matcher(champ).find())
                        .as("'%s' : aucune mention du texte visible sur une estampe (Q2 de"
                                + " l'arbitrage prealable du 2026-10-04)", champ)
                        .isFalse();
            }
        }
    }

    @Test
    void adr0081CePaquetDeclareArtCp09EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("ART-CP-09"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CP");
    }

    @Test
    void huitItemsHuitIdentifiantsDuVivierTousDistinctsEtTousUtilises() {
        List<Question> items = tousLesItems(paquet("", PAQUET));
        assertThat(items).as("huit items, un par oeuvre du vivier").hasSize(8);

        Set<String> identifiants = new HashSet<>();
        for (Question item : items) {
            String identifiant = item.illustration().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : illustration manquante"));
            assertThat(identifiants.add(identifiant))
                    .as("'%s' est declaree par plus d'un item : chaque oeuvre du vivier n'est"
                            + " utilisee qu'une seule fois", identifiant)
                    .isTrue();
        }
        assertThat(identifiants)
                .as("les huit identifiants utilises sont exactement ceux du vivier final"
                        + " d'ART-CP-09 (DEC-0109 section 10.3)")
                .isEqualTo(VIVIER);
    }

    @Test
    void chaqueItemDeclareUnTexteAlternatifNonVide() {
        for (Question item : tousLesItems(paquet("", PAQUET))) {
            assertThat(item.illustrationTexteAlternatif())
                    .as("'%s' : texte alternatif present (ADR-0073 clause 5)", item.enonce())
                    .isPresent();
            assertThat(item.illustrationTexteAlternatif().orElseThrow()).isNotBlank();
        }
    }

    @Test
    void enonceEstToujoursLaPremiereCleDeChaqueItem() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        List<?> items = (List<?>) corps.get("items");
        assertThat(items).hasSize(8);
        for (Object itemBrut : items) {
            Map<?, ?> champs = (Map<?, ?>) itemBrut;
            String premiereCle = String.valueOf(champs.keySet().stream().findFirst().orElseThrow());
            assertThat(premiereCle)
                    .as("un item illustre qui ne commence pas par 'enonce' sort du comptage"
                            + " textuel partage (ADR-0073 clause 6)")
                    .isEqualTo("enonce");
        }
    }

    @Test
    void aucunMotDeCouleurNApparaitDansAucunChampDAucunItem() {
        verifierAucuneCouleur(tousLesItems(paquet("", PAQUET)));
    }

    @Test
    void aucunChampNeMentionneLeTexteVisibleSurUneEstampe() {
        verifierAucuneMentionDuTexteVisible(tousLesItems(paquet("", PAQUET)));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item porte un mot de couleur
     * ("bleu") en bonne reponse - exactement ce que Q4 de l'arbitrage prealable refuse.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMotDeCouleur() {
        ContentSource source = paquet(CONTRE_EXEMPLE_CLASSPATH, "cp-decrire-une-oeuvre-fautif");

        assertThatThrownBy(() -> verifierAucuneCouleur(tousLesItems(source)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ART-1");
    }
}
