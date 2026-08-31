package com.recreo.bootstrap.rendu;

import com.recreo.adapter.content.YamlContentPackLoader;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>ADR-0070 nomme la propriete que son garde-fou ne peut pas tenir : "qu'une cle citee quelque
 * part soit lue au bon endroit". Ce test-ci la tient sur les deux cles de saisie qui coutent le plus
 * cher a perdre.</b>
 *
 * <p>Le pire cas de la mecanique de saisie n'est pas un ecran vide. Une liste de formes acceptees
 * perdue en route ne vide rien : elle <b>refuse une reponse juste</b>, ce qui est exactement ce que
 * {@code SEUIL-2} declare disqualifiant - "si une reponse juste refusee pour une question de forme
 * coute un essai, la saisie ne doit pas exister". La CI serait verte et personne ne verrait rien.
 * C'est pourquoi la traversee est verifiee de bout en bout, du texte YAML au verdict : le chargeur
 * reel lit le paquet, la mecanique reelle juge la reponse, et chaque forme que l'auteur a ecrite
 * doit rendre le verdict juste.</p>
 *
 * <p><b>Pourquoi ce module.</b> Bootstrap est le seul qui voit a la fois le contenu servi et les
 * mecaniques - meme motif d'emplacement que les trois autres tests de traversee de ce paquet.</p>
 *
 * <p><b>ADR-0051 : ce garde-fou declare qu'il n'examine aucun objet, plutot que de passer vert en
 * silence.</b> Aucun paquet de type {@code saisie} n'est servi au moment ou il est ecrit - le lot 1
 * de s24 a depose sa sonde sous les ressources de test d'{@code adapter-content}, jamais dans le
 * repertoire de contenu servi, parce qu'un paquet pose la-bas declencherait a lui seul le tri des
 * treize garde-fous de contenu. Son declencheur est donc le commit qui depose le premier paquet de
 * saisie dans {@code contenu/}, et il devient mordant ce jour-la sans qu'une ligne y soit ajoutee.
 * Le compte des paquets examines est <b>assertionne</b>, jamais suppose : le jour ou il passe de
 * zero a un, la clause de non-vacuite change de branche et la traversee est reellement exercee.</p>
 */
class LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest {

    private static final String TYPE_DE_SAISIE = "saisie";

    private record PaquetServi(String identifiant, ContentSource contenu) {
    }

    /**
     * ADR-0013 obligation 1 : les paquets viennent du repertoire de contenu servi, jamais d'une
     * liste ecrite ici et jamais du catalogue - un paquet retire du catalogue mais laisse sur disque
     * reste juge, comme {@code ChaquePaquetPorteUnIndiceTest} l'a etabli en s05.
     */
    private static List<PaquetServi> paquetsDeSaisieServis() {
        return new YamlContentPackLoader().games().stream()
                .filter(jeu -> Saisie.ID.equals(jeu.mecanique()))
                .map(jeu -> new PaquetServi(jeu.id(), jeu.contenu()))
                .toList();
    }

    /**
     * Les fichiers de contenu servi qui declarent le type de saisie, derives du <b>repertoire</b> et
     * jamais du catalogue. Les deux derivations existent et ne disent pas la meme chose : celle-ci
     * voit un paquet pose sur disque avant qu'une entree de catalogue ne le joue, et c'est cet
     * etat-la - le plus discret - que la clause de declaration ci-dessous epingle.
     */
    private static List<String> fichiersDeSaisieSurDisque() {
        List<String> fichiers = new ArrayList<>();
        for (Resource ressource : ressourcesDeContenu()) {
            String nom = ressource.getFilename();
            if (nom != null && nom.endsWith(".yaml") && TYPE_DE_SAISIE.equals(typeDeclare(ressource))) {
                fichiers.add(nom);
            }
        }
        return fichiers;
    }

    private static Resource[] ressourcesDeContenu() {
        try {
            return new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:contenu/*.yaml");
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String typeDeclare(Resource ressource) {
        try (InputStream flux = ressource.getInputStream()) {
            Map<String, Object> corps = new Yaml().load(flux);
            return String.valueOf(corps.get("type"));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<ReponseAEcrire> itemsDe(ContentSource contenu) {
        RandomSource hasard = borne -> 0;
        Set<ItemId> dejaVus = new HashSet<>();
        List<ReponseAEcrire> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            ReponseAEcrire reponse = (ReponseAEcrire) item;
            items.add(reponse);
            dejaVus.add(reponse.id());
        }
        return items;
    }

    /**
     * SEUIL-2 : chaque forme que l'auteur du paquet a ecrite est reellement acceptee par la
     * mecanique, sur le paquet servi et par le chargeur de production. Une forme qui se perdrait
     * entre le YAML et le verdict couterait un essai a un enfant qui a repondu juste.
     */
    @Test
    void chaqueFormeAccepteeEcriteDansUnPaquetServiRendLeVerdictJuste() {
        List<PaquetServi> paquets = paquetsDeSaisieServis();
        int formesEprouvees = 0;

        for (PaquetServi paquet : paquets) {
            List<ReponseAEcrire> items = itemsDe(paquet.contenu());
            assertThat(items)
                    .as("%s : un paquet servi qui n'enumere aucun item ne se joue pas",
                            paquet.identifiant())
                    .isNotEmpty();
            for (ReponseAEcrire item : items) {
                List<String> formes = new ArrayList<>();
                formes.add(item.formeDeReference());
                formes.addAll(item.formesAcceptees());
                for (String forme : formes) {
                    assertThat(verdictJusteSur(paquet.contenu(), item, forme))
                            .as("%s, item %s : la forme '%s' est ecrite dans le paquet et doit etre"
                                    + " acceptee. SEUIL-2 : une reponse juste refusee pour une"
                                    + " question de forme coute un essai a l'enfant",
                                    paquet.identifiant(), item.id().value(), forme)
                            .isTrue();
                    formesEprouvees++;
                }
            }
        }

        if (paquets.isEmpty()) {
            assertThat(formesEprouvees)
                    .as("ADR-0051 : ce garde-fou n'a examine aucune forme, et il le declare plutot"
                            + " que de passer vert en silence")
                    .isZero();
            return;
        }
        assertThat(formesEprouvees)
                .as("un paquet de type '%s' est servi, donc au moins une forme doit avoir ete"
                        + " eprouvee", TYPE_DE_SAISIE)
                .isPositive();
    }

    /**
     * ADR-0051, seconde moitie : l'etat du perimetre est <b>ecrit</b> et date, pas laisse au
     * silence. Zero paquet de type saisie est servi au 2026-08-31, et le lot 1 de s24 dit pourquoi -
     * sa sonde vit sous les ressources de test d'{@code adapter-content}, jamais dans le repertoire
     * de contenu servi, parce qu'un paquet pose la-bas declencherait a lui seul le tri des treize
     * garde-fous de contenu (planning de s24, section 5.3.c).
     *
     * <p>Ce nombre est le <b>declencheur</b> du test ci-dessus : le jour ou un paquet de saisie est
     * depose, cette assertion rougit, et celui qui la corrige voit dans le meme geste que la clause
     * de traversee vient de cesser d'etre vide. Sans elle, le passage de zero a un se ferait sans
     * qu'aucune ligne du depot ne bouge.</p>
     */
    @Test
    void aucunPaquetDeSaisieNEstServiAuMomentOuCeGardeFouEstEcrit() {
        assertThat(fichiersDeSaisieSurDisque())
                .as("ADR-0051 : si un paquet de type '%s' vient d'etre depose dans le repertoire de"
                        + " contenu servi, mettre ce compte a jour dans le meme commit - et verifier"
                        + " que chaqueFormeAccepteeEcriteDansUnPaquetServiRendLeVerdictJuste"
                        + " l'examine reellement", TYPE_DE_SAISIE)
                .isEmpty();
    }

    /**
     * Rejoue la forme sur une partie neuve du paquet, jusqu'a tomber sur l'item vise - le tirage est
     * deterministe, mais un paquet peut porter plusieurs items et la mecanique ne permet pas de
     * choisir la manche.
     */
    private static boolean verdictJusteSur(ContentSource contenu, ReponseAEcrire item, String forme) {
        Saisie mecanique = new Saisie();
        GameSession session = mecanique.start(contenu, borne -> 0, 1);
        SaisieSession saisie = (SaisieSession) session;
        if (!saisie.enonceManche().map(item.enonce()::equals).orElse(false)) {
            return true;
        }
        SaisieSession apres = (SaisieSession) mecanique.apply(saisie, new SaisieMove(forme));
        return apres.dernierVerdict().map(verdict -> verdict.juste()).orElse(false);
    }
}
