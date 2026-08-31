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
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>{@code S24-SAI-2}, le paquet {@code cp-complements-10-saisie}. Cette classe encode
 * {@code CPL-3}, qui etait une condition ecrite qu'aucune regle n'executait.</b>
 *
 * <p><b>Le defaut qu'elle ferme, et il a coute un refus.</b> {@code CPL-3} enumere depuis le
 * 2026-08-30 ce que ce paquet accepte, <i>"l'ecriture en chiffres du nombre, zero initial tolere
 * (07 vaut 7)"</i> comprise. Le paquet livre ne declarait pas cette ecriture-la. La porte technique
 * etait verte - 923 tests - et nadia-prof l'a trouve en <b>jouant</b> : huit manches, huit refus, et
 * le refus coute un essai. Trois {@code 03} de suite sur un item dont la reponse est {@code 3}
 * brulent la manche, et la revelation apprend a l'enfant qu'il avait tort alors qu'il avait eu
 * raison trois fois. C'est {@code SEUIL-2} franchi <b>sur le paquet et non sur la mecanique</b> :
 * elle accepte sans reserve toute forme que le paquet declare, la sonde de nadia-prof l'etablit.
 *
 * <p><b>Ce qu'elle garde et que la lecture du fichier ne garde pas.</b> Les formes attendues sont
 * <b>calculees a partir de l'enonce</b> - le complement de {@code a} a 10 - jamais lues dans le
 * paquet. C'est ce qui la rend capable de voir une declaration <b>manquante</b> : une clause qui
 * lirait le fichier des deux cotes verrait un paquet coherent avec lui-meme, ce qu'il etait deja.
 * Meme lecon que la traversee des formes acceptees, corrigee le meme jour pour la meme raison.
 *
 * <p><b>Et elle garde un piege de notation qui ne se voit pas a l'oeil</b> : mesure du 2026-08-31,
 * {@code 010} ecrit sans guillemets est lu <b>en octal</b> et sert {@code 8}. Un paquet qui perdrait
 * ses guillemets resterait un YAML valide, se chargerait, et servirait un bareme faux. Les formes
 * attendues etant calculees ici, ce cas est rouge.
 *
 * <p><b>Ce que cette classe ne juge pas</b> : que les enonces, les reponses et les indices soient
 * justes et au niveau. C'est le jugement de {@code nadia-prof}, rendu le 2026-08-31 - les onze
 * indices declares neufs, conduisant sans nommer la reponse - et aucun automate ne le refait.
 */
class PaquetCpComplements10SaisieTest {

    private static final String PAQUET = "cp-complements-10-saisie";

    /**
     * La forme canonique de CPL-1. Dans ce paquet, le point d'interrogation est precede d'un
     * espace <b>insecable</b> (ADR-0067, TYP-1), que la classe abregee des blancs ne couvre pas
     * en Java. Il est nomme par sa valeur numerique plutot qu'ecrit, comme le depot le fait
     * partout ou un caractere invisible entre dans du code.
     */
    private static final Pattern FORME_DE_L_ENONCE =
            Pattern.compile("^(\\d+) \\+[\\s" + (char) 0x00A0 + "]*\\? = 10$");

    private static final int ITEMS_ATTENDUS = 11;

    /** L'ecriture en lettres de chaque complement, celle que CPL-3 enumere nommement. */
    private static final Map<Integer, String> EN_LETTRES = Map.ofEntries(
            Map.entry(0, "zéro"), Map.entry(1, "un"), Map.entry(2, "deux"),
            Map.entry(3, "trois"), Map.entry(4, "quatre"), Map.entry(5, "cinq"),
            Map.entry(6, "six"), Map.entry(7, "sept"), Map.entry(8, "huit"),
            Map.entry(9, "neuf"), Map.entry(10, "dix"));

    /**
     * CPL-3 : pour chaque item, ce que ce paquet doit accepter. Calcule a partir de l'enonce, jamais
     * lu dans le paquet - une declaration manquante est precisement ce qu'on cherche.
     *
     * <p>Les variantes de casse, d'accent et de blancs relevent de SAI-1 et SAI-3, que la mecanique
     * possede en propre ; elles sont eprouvees ici parce que CPL-3 les enumere, et parce que leur
     * regression couterait a l'enfant exactement ce que la declaration manquante lui a coute.
     */
    private static List<String> formesQueCpl3Exige(int complement) {
        String chiffres = String.valueOf(complement);
        String lettres = EN_LETTRES.get(complement);
        return List.of(
                chiffres,
                "0" + chiffres,
                "  " + chiffres + "  ",
                lettres,
                lettres.toUpperCase(Locale.FRENCH),
                Normalizer.normalize(lettres, Normalizer.Form.NFD).replaceAll("\\p{M}", ""),
                " " + lettres + " ");
    }

    @Test
    void chaqueFormeQueCpl3EnumereEstAccepteeParLaMecanique() {
        List<ReponseAEcrire> items = items();
        assertThat(items)
                .as("ce garde-fou ne prouve rien si le paquet ne rend pas ses items")
                .hasSize(ITEMS_ATTENDUS);

        int formesEprouvees = 0;
        for (int rang = 0; rang < items.size(); rang++) {
            ReponseAEcrire item = items.get(rang);
            int complement = complementAttendu(item.enonce());
            for (String forme : formesQueCpl3Exige(complement)) {
                assertThat(verdictJusteSur(rang, item, forme))
                        .as("item %s (\"%s\") : CPL-3 enumere l'ecriture \"%s\" et la mecanique doit"
                                + " l'accepter. SEUIL-2 : une reponse juste refusee sur une question"
                                + " de forme coute un essai a l'enfant, et trois de ces refus brulent"
                                + " la manche en lui apprenant qu'il avait tort",
                                item.id().value(), item.enonce(), forme)
                        .isTrue();
                formesEprouvees++;
            }
        }
        assertThat(formesEprouvees)
                .as("onze items, sept ecritures enumerees par CPL-3 sur chacun")
                .isEqualTo(ITEMS_ATTENDUS * 7);
    }

    /**
     * CPL-3, seconde moitie : <i>"aucune autre forme n'est acceptee : ni operation ecrite, ni
     * phrase"</i>. Sans cette clause, un paquet qui accepterait tout passerait la premiere.
     */
    @Test
    void niLOperationEcriteNiUnePhraseNeSontAcceptees() {
        List<ReponseAEcrire> items = items();
        int formesRefusees = 0;
        for (int rang = 0; rang < items.size(); rang++) {
            ReponseAEcrire item = items.get(rang);
            int complement = complementAttendu(item.enonce());
            for (String forme : List.of("10-" + (10 - complement), "il manque " + complement)) {
                assertThat(verdictJusteSur(rang, item, forme))
                        .as("item %s : CPL-3 refuse l'operation ecrite et la phrase ; \"%s\" ne doit"
                                + " pas etre acceptee", item.id().value(), forme)
                        .isFalse();
                formesRefusees++;
            }
        }
        assertThat(formesRefusees)
                .as("onze items, deux formes refusees sur chacun")
                .isEqualTo(ITEMS_ATTENDUS * 2);
    }

    /**
     * CPL-2 : toute bonne reponse est un entier de 0 a 10. Le complement est calcule a partir de
     * l'enonce puis confronte a ce que le paquet declare : c'est la seule clause de cette classe qui
     * lise la forme de reference, et elle la lit pour la <b>contredire</b>, jamais pour s'en servir
     * d'attendu.
     */
    @Test
    void laFormeDeReferenceDeChaqueItemEstLeComplementA10DeSonEnonce() {
        for (ReponseAEcrire item : items()) {
            assertThat(item.formeDeReference())
                    .as("item %s (\"%s\") : la forme de reference doit etre le complement a 10 de"
                            + " son enonce", item.id().value(), item.enonce())
                    .isEqualTo(String.valueOf(complementAttendu(item.enonce())));
        }
    }

    private static int complementAttendu(String enonce) {
        Matcher forme = FORME_DE_L_ENONCE.matcher(enonce);
        assertThat(forme.matches())
                .as("l'enonce \"%s\" est ecrit sous la forme canonique attendue par CPL-1", enonce)
                .isTrue();
        int terme = Integer.parseInt(forme.group(1));
        assertThat(terme)
                .as("CPL-2 : le terme connu de \"%s\" va de 0 a 10", enonce)
                .isBetween(0, 10);
        return 10 - terme;
    }

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

    /**
     * Rejoue la forme sur une partie neuve, en visant la manche de l'item par son rang - meme
     * mecanisme et meme motif que la traversee des formes acceptees : un tirage constant ne jugerait
     * que le premier item et laisserait les dix autres sortir en silence.
     */
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
}
