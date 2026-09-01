package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S24, lot 1 (ADR-0058 sections 1 et 2, ADR-0069, ADR-0070) : le type de paquet 'saisie' est charge
 * par sa propre fabrique, et la declaration de SAI-2a traverse le chargement.
 *
 * <p>Le paquet de sonde vit sous les ressources de test de ce module et <b>jamais</b> dans le
 * repertoire de contenu servi : un paquet pose la-bas, meme hors catalogue, declencherait a lui
 * seul le lot 5 du sprint, les treize garde-fous de contenu derivant leur perimetre du repertoire
 * et non du catalogue. C'est un critere de sortie de ce lot (planning de s24, section 5.3.c).</p>
 *
 * <p>Cette classe ne balaie aucun artefact de production et n'est donc pas un garde-fou au sens
 * d'ADR-0013 : elle charge une sonde de test par le chargeur reel. Le recensement de
 * {@code LesGardeFousSontMisALEpreuveTest} la reconnait a la seule presence d'un chemin de sources
 * de production dans son texte, ce qui est deliberement grossier ; la phrase ci-dessus nomme donc
 * le repertoire servi plutot que de l'ecrire, pour ne pas faire entrer au recensement un test qui
 * n'y a pas sa place.</p>
 */
class SaisieContentSourceFactoryTest {

    private static final String RACINE_DE_SONDE = "/fixtures/saisie";

    private static final String PAQUET_DE_SONDE = "paquet-saisie";

    @Test
    void unPaquetDeSaisieEstChargeEnItemsAReponseEcrite() {
        List<ReponseAEcrire> items = tousLesItems(paquetDeSonde());

        assertThat(items).hasSize(3);
        ReponseAEcrire premier = items.get(0);
        assertThat(premier.enonce()).isEqualTo("3 + ? = 10");
        assertThat(premier.formeDeReference()).isEqualTo("7");
        assertThat(premier.formesAcceptees())
                .as("SAI-6 : la liste des formes acceptees vient du paquet, jamais du code")
                .containsExactly("sept");
        assertThat(premier.indice()).contains("Compte ceux qui manquent.");
        assertThat(premier.explication()).isEmpty();
        assertThat(items.get(1).explication()).contains("La sonde n'explique rien a personne.");
        assertThat(items.get(1).indice()).isEmpty();
    }

    /**
     * ADR-0075 : la cle facultative 'texteAVoixHaute' est lue sur cette fabrique aussi, meme patron
     * que 'indice' et 'explication' - absente sur un item, presente sur un autre.
     */
    @Test
    void laVoixEstLueQuandLePaquetLaDeclareEtAbsenteSinon() {
        List<ReponseAEcrire> items = tousLesItems(paquetDeSonde());

        assertThat(items.get(0).texteAVoixHaute())
                .as("le premier item ne declare aucune voix")
                .isEmpty();
        assertThat(items.get(1).texteAVoixHaute())
                .as("le second item declare sa voix, transmise telle quelle")
                .contains("Le mot est eleve.");
    }

    @Test
    void laFormeDeReferenceNAPasAFigurerDansLesFormesAcceptees() {
        assertThat(tousLesItems(paquetDeSonde()).get(1).formesAcceptees())
                .as("un item strict n'accepte que son ecriture exacte, et la liste reste vide")
                .isEmpty();
    }

    /**
     * SAI-2a : la commodite "au niveau du paquet si le paquet entier est concerne, au niveau de
     * l'item si le paquet melange les deux cas" est un fait de schema, lu par cette fabrique. Les
     * trois etats sont exerces par la sonde, dans cet ordre : rien de declare sur l'item, donc le
     * defaut du paquet ; l'item declare et l'emporte ; l'item redeclare le defaut du paquet.
     */
    @Test
    void laDeclarationDeSai2aEstLueAuNiveauDuPaquetEtDeLItemLItemLEmportant() {
        List<ReponseAEcrire> items = tousLesItems(paquetDeSonde());

        assertThat(items.get(0).exigenceDeForme())
                .as("l'item ne declare rien : il prend ce que le paquet declare")
                .isEqualTo(ExigenceDeForme.FORME_LIBRE);
        assertThat(items.get(1).exigenceDeForme())
                .as("l'item declare 'exacte' contre un paquet 'libre' : l'item l'emporte")
                .isEqualTo(ExigenceDeForme.FORME_EXACTE);
        assertThat(items.get(2).exigenceDeForme())
                .as("l'item redeclare le defaut du paquet, ce qui ne change rien")
                .isEqualTo(ExigenceDeForme.FORME_LIBRE);
    }

    /**
     * SAI-2a : un paquet qui ne declare rien du tout est tolerant, et ce defaut protege l'enfant.
     * La mesure porte sur un paquet reellement charge, jamais sur un objet construit a la main.
     */
    @Test
    void unPaquetQuiNeDeclareRienEstEnFormeLibre() {
        ContentSource sansDeclaration = new YamlContentPackLoader(RACINE_DE_SONDE)
                .chargerPaquet("paquet-saisie-sans-declaration");

        assertThat(tousLesItems(sansDeclaration))
                .as("SAI-2a : absente des deux niveaux, la declaration vaut forme libre")
                .allSatisfy(item -> assertThat(item.exigenceDeForme())
                        .isEqualTo(ExigenceDeForme.FORME_LIBRE));
    }

    /**
     * Une valeur inconnue arrete le chargement au lieu de retomber sur le defaut. Sans cela, une
     * faute de frappe sur 'exacte' rendrait un paquet tolerant que son auteur croit strict, et la
     * CI resterait verte - le mode de defaillance qu'ADR-0070 ferme sur les cles et que celui-ci
     * ferme sur les valeurs.
     */
    @Test
    void uneExigenceDeFormeInconnueArreteLeChargementEnNommantLaCleEtLaValeur() {
        assertThatThrownBy(() -> new YamlContentPackLoader(RACINE_DE_SONDE)
                .chargerPaquet("paquet-saisie-exigence-inconnue"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("exigenceDeForme")
                .hasMessageContaining("exact");
    }

    @Test
    void unItemSansFormeDeReferenceArreteLeChargementEnNommantLeChamp() {
        assertThatThrownBy(() -> new YamlContentPackLoader(RACINE_DE_SONDE)
                .chargerPaquet("paquet-saisie-sans-forme-de-reference"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("formeDeReference");
    }

    /**
     * Avis de faisabilite de s24, section 2.1 point 5 : {@code ChaquePaquetPorteUnIndiceTest} borne
     * ses tirages en comptant les lignes du YAML qui commencent par "- enonce:". Un item de saisie
     * dont le premier champ differerait ferait rendre zero a ce comptage, et un garde-fou qui borne
     * a zero ne tire rien - un vert silencieux, pas un rouge. Ce lot decide que 'enonce' est le
     * premier champ d'un item de saisie, et le controle porte sur le <b>texte</b> du paquet : la
     * carte que SnakeYAML en tire ne porte plus l'ordre des cles.
     */
    @Test
    void chaqueItemDeSaisieOuvreParSonEnonce() {
        List<String> premieresLignesDItem = new ArrayList<>();
        for (String ligne : texteDuPaquetDeSonde().split("\\R")) {
            if (ligne.stripTrailing().startsWith("  - ")) {
                premieresLignesDItem.add(ligne.strip());
            }
        }

        assertThat(premieresLignesDItem)
                .as("ce controle ne prouve rien s'il ne trouve aucun item")
                .hasSize(3);
        assertThat(premieresLignesDItem)
                .as("le comptage d'items de ChaquePaquetPorteUnIndiceTest cherche '- enonce:'")
                .allMatch(ligne -> ligne.startsWith("- enonce:"));
    }

    private static ContentSource paquetDeSonde() {
        return new YamlContentPackLoader(RACINE_DE_SONDE).chargerPaquet(PAQUET_DE_SONDE);
    }

    private static String texteDuPaquetDeSonde() {
        String chemin = RACINE_DE_SONDE + "/contenu/" + PAQUET_DE_SONDE + ".yaml";
        try (InputStream flux = SaisieContentSourceFactoryTest.class.getResourceAsStream(chemin)) {
            assertThat(flux).as("%s introuvable sur le classpath", chemin).isNotNull();
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Enumere tous les items distincts du paquet, dans l'ordre de leur position dans le fichier. */
    private static List<ReponseAEcrire> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        Map<Integer, ReponseAEcrire> parRang = new java.util.TreeMap<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            ReponseAEcrire reponse = (ReponseAEcrire) item;
            parRang.put(Integer.parseInt(reponse.id().value().substring("item-".length())), reponse);
            dejaVus.add(reponse.id());
        }
        return new ArrayList<>(parRang.values());
    }
}
