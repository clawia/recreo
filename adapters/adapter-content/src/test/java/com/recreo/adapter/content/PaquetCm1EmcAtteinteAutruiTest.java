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
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S54-BR-EMC-CM1-02, le paquet {@code cm1-emc-atteinte-autrui}, ligne {@code EMC-CM1-02} de
 * l'enseignement moral et civique (CM1).</b> Referentiel :
 * {@code squad/programme/enseignement-moral-et-civique/competences-cm1.md}, ligne 2. Conditions :
 * l'en-tete du paquet et {@code squad/conformite/ARBITRAGE-PREALABLE-s54-instance-b-sciences-emc-cm1.md},
 * section 5.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, lues sur la forme de la question : "nommer un
 * comportement decrit" ({@code Comment appelle-t-on ...}) et "dire a qui il est important de
 * s'adresser" ({@code A qui est-il important d'en parler}).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-5} (une seule reponse defendable), {@code EMC-9}
 * sur l'ecran entier (retour juste, revelation, fin de partie, que la mecanique ecrit) et
 * {@code CYC-1} demandent une lecture : garde de {@code nadia-prof}, en jouant. {@code EMC-9} n'est
 * gardee ci-dessous que sur la forme : aucun texte du paquet ne porte un participe passe d'alerte.</p>
 */
class PaquetCm1EmcAtteinteAutruiTest {

    private static final String PAQUET = "cm1-emc-atteinte-autrui";

    private static final int NOMBRE_DE_MANCHES = 10;

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    private static final String TACHE_NOMMER = "Comment appelle-t-on";

    private static final String TACHE_S_ADRESSER = "À qui est-il important d'en parler";

    /** FER-1 : les quatre termes de la parenthese du libelle, chacun en bonne reponse. */
    private static final Set<String> TERMES_DU_LIBELLE =
            Set.of("une moquerie", "une insulte", "une mise à l'écart", "du harcèlement");

    /** EMC-3 : la bonne reponse d'un item "a qui" nomme un adulte, jamais une solution seule. */
    private static final Pattern UN_ADULTE = Pattern.compile("\\badulte\\b|\\benseignant\\b");

    /** EMC-1, EMC-8 : aucune adresse a l'enfant, en mot entier. */
    private static final Pattern ADRESSE_A_L_ENFANT = Pattern.compile(
            "\\b(tu|ton|ta|tes|toi|te|vous|votre|vos)\\b|\\bt['\\u2019]",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    /** EMC-9 : aucun texte ne dit que l'enfant vient d'alerter quelqu'un. */
    private static final Pattern ALERTE_ACCOMPLIE = Pattern.compile(
            "\\b(pr[ée]venue?s?|alert[ée]e?s?|signal[ée]e?s?)\\b|\\bbien (fait|jou[ée])\\b|\\bmerci\\b|\\bbravo\\b",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    private static final Pattern UN_CHIFFRE = Pattern.compile("\\d");

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

    private static List<Question> itemsDeTache(String forme) {
        return items().stream().filter(item -> item.enonce().contains(forme)).toList();
    }

    private static List<String> textesLusParLEnfant(Question item) {
        List<String> textes = new ArrayList<>();
        textes.add(item.enonce());
        textes.add(item.bonneReponse());
        textes.addAll(item.distracteurs());
        item.indice().ifPresent(textes::add);
        item.explication().ifPresent(textes::add);
        return textes;
    }

    @Test
    void fer2AuMoinsNombreDeManchesPlusUnItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items (nombreDeManches + 1)", NOMBRE_DE_MANCHES + 1)
                .isGreaterThanOrEqualTo(NOMBRE_DE_MANCHES + 1);
    }

    @Test
    void lePaquetPorteExactementOnzeItemsRepartisEntreLesDeuxTaches() {
        assertThat(items()).hasSize(11);
        assertThat(itemsDeTache(TACHE_NOMMER).size() + itemsDeTache(TACHE_S_ADRESSER).size())
                .as("chaque item appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(11);
    }

    @Test
    void fer3LesDeuxTachesPortentChacuneAuMoinsTroisItems() {
        assertThat(Math.min(itemsDeTache(TACHE_NOMMER).size(), itemsDeTache(TACHE_S_ADRESSER).size()))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    @Test
    void fer1LesQuatreTermesDuLibelleSontChacunEnBonneReponse() {
        Set<String> bonnesReponses = new HashSet<>();
        for (Question item : itemsDeTache(TACHE_NOMMER)) {
            bonnesReponses.add(item.bonneReponse());
        }
        assertThat(bonnesReponses).containsAll(TERMES_DU_LIBELLE);
    }

    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus", item.enonce())
                    .hasSize(2);
        }
    }

    @Test
    void emc3LaBonneReponseDesItemsAQuiNommeToujoursUnAdulte() {
        for (Question item : itemsDeTache(TACHE_S_ADRESSER)) {
            assertThat(UN_ADULTE.matcher(item.bonneReponse()).find())
                    .as("item '%s' : la bonne reponse nomme un adulte (EMC-3) - \"%s\"",
                            item.enonce(), item.bonneReponse())
                    .isTrue();
        }
    }

    @Test
    void emc1Emc8AucunTexteNAdresseLEnfantALaDeuxiemePersonne() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(ADRESSE_A_L_ENFANT.matcher(texte).find())
                        .as("texte '%s' : EMC-1 et EMC-8 interdisent d'adresser l'enfant", texte)
                        .isFalse();
            }
        }
    }

    @Test
    void emc9AucunTexteNeDitQueLEnfantVientDAlerterQuelquUn() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(ALERTE_ACCOMPLIE.matcher(texte).find())
                        .as("texte '%s' : EMC-9 interdit de laisser croire qu'une alerte est faite",
                                texte)
                        .isFalse();
            }
        }
    }

    @Test
    void emc7AucunTexteNeCiteDeNumeroNiDeChiffre() {
        for (Question item : items()) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(UN_CHIFFRE.matcher(texte).find())
                        .as("texte '%s' : EMC-7 interdit un numero d'urgence ou d'ecoute", texte)
                        .isFalse();
            }
        }
    }

    @Test
    void laCompetenceEstLeLibelleDeEmcCm102() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .isEqualTo("Reconnaître ce qui porte atteinte à la personne d'autrui (moquerie, insulte,"
                        + " mise à l'écart, harcèlement) et savoir à qui s'adresser.");
    }

    @Test
    void lElisionDeTuEstReconnueAvecLApostropheDroiteEtLApostropheTypographique() {
        assertThat(ADRESSE_A_L_ENFANT.matcher("Quand t'appelle-t-on ?").find()).isTrue();
        assertThat(ADRESSE_A_L_ENFANT.matcher("Quand t\u2019appelle-t-on ?").find()).isTrue();
    }
}
