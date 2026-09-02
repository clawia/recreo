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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-1, le paquet {@code cp-voyelle-du-son}, derniere clause de la ligne "associer les
 * graphemes aux phonemes" (voyelles simples).</b> DEC-0069 (rang 1) et backlog {@code S27-1} :
 * clauses 2, 3 et 4 de la barre du 2026-08-10 de nadia-prof deja remplies a s26 ; seule la clause 1
 * (voyelles simples et consonnes simples) manquait.
 *
 * <p><b>Meme forme que {@link PaquetCpLettreDuSonTest}</b>, sur le meme champ d'item
 * ({@code ADR-0075}) : plafond de vingt items, condition de distribution - chaque lettre servie
 * est distracteur au moins aussi souvent qu'elle est reponse -, et l'enonce qui nomme "le tout
 * premier son" avant toute reponse (correctif valide par nadia-prof a s26, repris a l'identique).
 *
 * <p><b>Choix de lecture propre a ce paquet</b> : quatre voyelles (a, i, o, u), pas cinq - "e" est
 * ecarte parce qu'au debut d'un mot francais courant il ne porte quasiment jamais le son que son
 * nom suggere a un enfant de CP. Le critere d'acceptation de {@code S27-1} autorise explicitement
 * un sous-ensemble des cinq voyelles simples, couvrant au moins une voyelle simple en bonne reponse
 * par item ; ce test verifie que l'espace de reponse est exactement ce sous-ensemble de quatre.
 */
class PaquetCpVoyelleDuSonTest {

    private static final String PAQUET = "cp-voyelle-du-son";

    private static final int PLAFOND_ITEMS = 20;

    private static final Set<String> VOYELLES_SIMPLES_RETENUES = Set.of("a", "i", "o", "u");

    /** ADR-0064 : aucune borne ecrite a la main - le tirage s'arrete de lui-meme sur un paquet enumere. */
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

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * Espace de reponse : exactement les quatre voyelles simples retenues (S27-1, critere
     * d'acceptation 1, lecture "sous-ensemble" - voir le commentaire du paquet).
     */
    @Test
    void lEspaceDeReponseEstExactementLesQuatreVoyellesRetenues() {
        for (Question item : items()) {
            assertThat(VOYELLES_SIMPLES_RETENUES)
                    .as("item %s : la bonne reponse doit etre l'une des quatre voyelles simples"
                                    + " retenues", item.id().value())
                    .contains(item.bonneReponse());
            assertThat(VOYELLES_SIMPLES_RETENUES)
                    .as("item %s : chaque distracteur doit etre l'une des quatre voyelles simples"
                                    + " retenues", item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution (meme forme que {@link PaquetCpLettreDuSonTest}) : chaque voyelle
     * servie est distracteur au moins aussi souvent qu'elle est reponse. Compte reel, jamais
     * affirme.
     */
    @Test
    void chaqueVoyelleServieEstDistracteurAuMoinsAussiSouventQuElleEstReponse() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> commeReponse = new TreeMap<>();
        Map<String, Integer> commeDistracteur = new TreeMap<>();
        for (Question item : items) {
            commeReponse.merge(item.bonneReponse(), 1, Integer::sum);
            for (String distracteur : item.distracteurs()) {
                commeDistracteur.merge(distracteur, 1, Integer::sum);
            }
        }

        assertThat(commeReponse.keySet())
                .as("les quatre voyelles retenues doivent toutes etre representees comme reponse")
                .containsExactlyInAnyOrderElementsOf(VOYELLES_SIMPLES_RETENUES);

        for (Map.Entry<String, Integer> entree : commeReponse.entrySet()) {
            String lettre = entree.getKey();
            int commeReponseCompte = entree.getValue();
            int commeDistracteurCompte = commeDistracteur.getOrDefault(lettre, 0);
            assertThat(commeDistracteurCompte)
                    .as("la voyelle \"%s\" est reponse %d fois et distracteur %d fois - elle doit"
                                    + " etre distracteur au moins aussi souvent", lettre,
                            commeReponseCompte, commeDistracteurCompte)
                    .isGreaterThanOrEqualTo(commeReponseCompte);
        }
    }

    /**
     * Meme garde-fou que {@link PaquetCpLettreDuSonTest#lEnonceNommeLaPositionDuSonAvantLaPremiereReponse()} :
     * l'enonce doit nommer la position du son avant toute reponse.
     */
    @Test
    void lEnonceNommeLaPositionDuSonAvantLaPremiereReponse() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : l'enonce doit nommer que le son cherche est le premier du mot,"
                            + " avant toute reponse", item.id().value())
                    .contains("premier son");
        }
    }

    /**
     * Verifie que la bonne reponse est bien le tout premier son du mot que l'adulte prononce -
     * meme garde-fou que {@link PaquetCpLettreDuSonTest}.
     */
    @Test
    void laBonneReponseEstBienLePremierSonDuMotEntendu() {
        for (Question item : items()) {
            String mot = motDe(replier(item.texteAVoixHaute().orElseThrow()));
            assertThat(mot.substring(0, 1))
                    .as("item %s : la bonne reponse (\"%s\") doit etre la premiere lettre du mot"
                                    + " (\"%s\")", item.id().value(), item.bonneReponse(), mot)
                    .isEqualTo(replier(item.bonneReponse()));
        }
    }

    /**
     * Verifie qu'aucun mot servi ne commence par une voyelle nasale ni par un digraphe : ces sons
     * appartiennent a d'autres lignes (cp-le-bon-son, cp-digraphe-du-son) et ne doivent pas etre
     * confondus avec une voyelle simple (S27-1, critere d'acceptation 2).
     */
    @Test
    void aucunMotNeCommenceParUneVoyelleNasaleOuUnDigraphe() {
        // "ou" et "oi" sont des digraphes quoi qu'ils precedent (cp-digraphe-du-son) ; "an", "on",
        // "en", "in" et "un" ne nasalisent que suivis d'une consonne ou de rien (ex. "ombre",
        // "uniforme" n'est PAS nasal : "un" y est suivi de la voyelle "i", cp-le-bon-son).
        Set<String> digraphesInterdits = Set.of("ou", "oi");
        Set<String> nasalesInterdites = Set.of("an", "on", "en", "in", "un");
        for (Question item : items()) {
            String mot = motDe(replier(item.texteAVoixHaute().orElseThrow()));
            for (String digraphe : digraphesInterdits) {
                assertThat(mot.startsWith(digraphe))
                        .as("item %s : le mot \"%s\" ne doit pas commencer par le digraphe \"%s\"",
                                item.id().value(), mot, digraphe)
                        .isFalse();
            }
            for (String nasale : nasalesInterdites) {
                if (!mot.startsWith(nasale)) {
                    continue;
                }
                boolean suiviDUneVoyelle = mot.length() > nasale.length()
                        && "aeiouy".indexOf(mot.charAt(nasale.length())) >= 0;
                assertThat(suiviDUneVoyelle)
                        .as("item %s : le mot \"%s\" commence par \"%s\" suivi d'une consonne ou de"
                                + " rien - c'est une voyelle nasale", item.id().value(), mot, nasale)
                        .isTrue();
            }
        }
    }

    private static String motDe(String texteAVoixHauteReplie) {
        // Le mot travaille est le premier mot apres "le mot est " dans le gabarit de ce paquet.
        String prefixe = "le mot est ";
        int debut = texteAVoixHauteReplie.indexOf(prefixe) + prefixe.length();
        int fin = texteAVoixHauteReplie.indexOf('.', debut);
        return texteAVoixHauteReplie.substring(debut, fin < 0 ? texteAVoixHauteReplie.length() : fin);
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
