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

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
 * doit rendre le verdict juste.
 *
 * <p><b>Correction du 2026-08-31 (sofia-sm, lot 5b de s24), sur le declenchement de ce garde-fou et
 * non sur la propriete qu'il garde.</b> Il a ete ecrit au lot 4 avec un fil-piege : une seconde
 * clause, rouge le jour ou un paquet de saisie serait depose, pour que <i>celui qui l'ajoute verifie
 * dans le meme geste que la clause de traversee cesse d'etre vide</i>. Le fil a saute au depot de
 * {@code cp-complements-10-saisie}. Il a fait son travail, et ce qu'il a montre est que <b>mettre
 * le compte a jour n'aurait rien corrige</b> : les deux clauses ne derivaient pas leur perimetre de
 * la meme source - le fil-piege du <b>repertoire</b>, la traversee du <b>catalogue</b>. L'entree de
 * catalogue etant retenue (condition {@code CPL-9c} de nadia-prof : la consigne compte neuf mots
 * contre huit), la traversee serait restee sur sa branche vide pendant que le fil-piege passait au
 * vert. Un faux vert, sur le garde-fou meme qui existe pour empecher qu'une reponse juste soit
 * refusee en silence.
 *
 * <p>Quatre choses ont donc change, et aucune n'est une exemption :
 * <ul>
 *   <li><b>la traversee derive du repertoire</b>, ce que sa propre Javadoc promettait deja mot pour
 *       mot - <i>"les paquets viennent du repertoire de contenu servi, jamais d'une liste ecrite ici
 *       et jamais du catalogue"</i> - alors que le code lisait {@code games()}, c'est-a-dire le
 *       catalogue. La promesse etait la bonne, c'est la derivation qui ne la tenait pas. Un paquet
 *       pose sur disque est desormais juge avant qu'une entree de catalogue ne le joue : c'est
 *       l'etat le plus discret, donc celui qui merite le garde-fou (ADR-0013 obligation 1, meme
 *       forme qu'en s05) ;</li>
 *   <li><b>la manche visee est atteinte, et son atteinte est assertionnee.</b> Le tirage se faisait
 *       avec une source de hasard constante a zero : sur un paquet de onze items, la mecanique
 *       rendait toujours le premier, et les formes des dix autres sortaient par un
 *       {@code return true} silencieux. Le compteur de formes eprouvees montait quand meme.
 *       <b>Compter une population n'est pas la juger</b> - c'est la meme famille que les deux verts
 *       silencieux fermes par le lot 5b ;</li>
 *   <li><b>les formes attendues sont lues dans le texte du YAML</b>, plus dans l'item charge. C'est
 *       le defaut le plus grave des quatre, et il ne se voit qu'en mutant la chaine : une fabrique
 *       qui perd la liste des formes acceptees d'un item la perd <b>des deux cotes</b> de
 *       l'assertion, si bien que ce garde-fou restait vert sur exactement le defaut que sa premiere
 *       phrase annonce - "une liste de formes acceptees perdue en route". Reproduit le 2026-08-31 :
 *       fabrique amputee du dernier item, ce test vert. Il est rouge depuis, et il nomme l'item et
 *       la forme. La Javadoc disait deja "de bout en bout, du texte YAML au verdict" ; la traversee
 *       commencait au chargeur ;</li>
 *   <li><b>ce module plutot que bootstrap.</b> Le motif ecrit etait <i>"bootstrap est le seul qui
 *       voit a la fois le contenu servi et les mecaniques"</i>. Mesure du 2026-08-31 : il est faux.
 *       {@code adapter-content} depend de {@code recreo-domain}, et {@code PaquetsFrancaisS04Test} y
 *       joue deja {@code BanditManchot.start} et {@code .apply} sur du contenu servi. Ici, le
 *       chargeur par paquet est visible et le balayage du repertoire est celui des quatorze autres
 *       garde-fous de contenu ; la-bas, ni l'un ni l'autre - un test voisin de bootstrap le note
 *       lui-meme, <i>"chargerPaquet n'est pas visible d'ici"</i>. <b>Aucune visibilite de production
 *       n'a ete elargie</b> pour ce deplacement, et aucune entree de catalogue n'a ete forcee pour
 *       faire tomber un compteur.</li>
 * </ul>
 *
 * <p><b>Ce qui n'a pas change</b> : la propriete gardee, son motif {@code SEUIL-2}, et le fait que
 * l'etat du perimetre soit <b>ecrit</b> plutot que laisse au silence (ADR-0051). Le fil-piege n'est
 * pas retire, il est retourne : il declarait "aucun paquet servi" et declare maintenant lesquels le
 * sont, en egalite dans les deux sens.
 */
class LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : les paquets fautifs de ce garde-fou, jamais servis a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest";

    /**
     * ADR-0051 : les paquets de saisie poses dans le repertoire de contenu servi, ecrits et dates.
     *
     * <p>En <b>egalite, jamais en plancher</b>, comme les autres cliquets de ce module : un paquet
     * de saisie depose et non declare est rouge - c'est le fil-piege d'origine, conserve - et un nom
     * qui ne designe plus rien est rouge aussi, faute de quoi la declaration survivrait a son objet.
     * Le jour ou ce compte bouge, la clause de traversee ci-dessous change de population dans le
     * meme diff.
     */
    private static final List<String> PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31 =
            // S26-L5 : cp-mot-dicte ajoute le 2026-09-01 (DEC-0068, premier paquet a reponse
            // alphabetique sur cette mecanique). formesAcceptees y est vide sur chaque item - SAI-1,
            // SAI-3 et SAI-4 sont tenues par NormalisationDeSaisie sans forme alternative a enumerer -
            // et cette clause traverse alors formeDeReference seul, ce que le code ci-dessus fait deja.
            // S57-BR-FRA-CE1-21 : troisieme entree de la liste (critere 11 de la story). Un seul
            // item porte une forme acceptee, "aujourd'hui" en U+2019 a cote de U+0027 (QP1) ; les
            // 23 autres la declarent vide.
            List.of("cp-complements-10-saisie", "cp-mot-dicte", "ce1-mots-invariables");

    /**
     * Les formes qu'un item <b>ecrit dans le YAML</b>, lues independamment de la fabrique.
     *
     * <p><b>C'est ce qui fait de cette clause une traversee</b> et non une comparaison de la
     * mecanique avec elle-meme. Mesure du 2026-08-31 : en lisant les formes attendues dans
     * {@code ReponseAEcrire}, une fabrique qui perd la liste des formes acceptees d'un item les perd
     * <b>des deux cotes</b> de l'assertion, et le garde-fou reste vert - alors que sa raison d'etre
     * ecrite est exactement "une liste de formes acceptees perdue en route". Le defaut a ete
     * reproduit : la fabrique amputee du dernier item laissait ce test vert. Le texte du fichier est
     * la seule source qui ne bouge pas quand la chaine se casse.
     */
    private static List<String> formesEcritesDansLeYaml(Path racine, String paquet, int rang) {
        Path fichier = racine.resolve("contenu").resolve(paquet + ".yaml");
        try (InputStream flux = Files.newInputStream(fichier)) {
            Map<String, Object> corps = new Yaml().load(flux);
            Object items = corps.get("items");
            Map<?, ?> item = (Map<?, ?>) ((List<?>) items).get(rang);
            List<String> formes = new ArrayList<>();
            formes.add(String.valueOf(item.get("formeDeReference")));
            Object acceptees = item.get("formesAcceptees");
            if (acceptees instanceof List<?> liste) {
                liste.forEach(forme -> formes.add(String.valueOf(forme)));
            }
            return formes;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private record PaquetServi(String identifiant, ContentSource contenu, List<ReponseAEcrire> items) {
    }

    /**
     * ADR-0013 obligation 1 : les paquets viennent du <b>repertoire</b> de contenu servi, jamais
     * d'une liste ecrite ici et jamais du catalogue - un paquet pose sur disque avant d'etre joue,
     * ou retire du catalogue mais laisse sur disque, reste juge.
     *
     * <p>Ce qui fait qu'un paquet est un paquet de saisie n'est pas lu dans une cle du YAML : c'est
     * <b>ce que le chargeur reel en tire</b>, classe par {@link ClassementDesItems} - le classement
     * ecrit une fois pour tous les garde-fous de ce module (lot 5a de s24), plutot qu'un second
     * classement invente ici. Un paquet qui declarerait le type de saisie et dont la fabrique
     * rendrait autre chose est vu, la ou une lecture de la cle {@code type} l'aurait cru sur parole.
     */
    private static List<PaquetServi> paquetsDeSaisieServis(Path racine, String racineClasspath) {
        List<PaquetServi> paquets = new ArrayList<>();
        for (String paquet : ChaquePaquetPorteUnIndiceTest.paquets(racine)) {
            int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racine, paquet);
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            List<Item> tires = itemsTires(contenu, declares);
            List<ReponseAEcrire> items = ClassementDesItems.demandantUneReponseEcrite(tires);
            if (items.isEmpty()) {
                continue;
            }
            assertThat(items)
                    .as("%s : ce paquet rend des items de saisie et des items de choix. Ce garde-fou"
                            + " vise chaque manche par son rang dans le paquet, ce qu'un paquet"
                            + " melange rendrait faux sans le dire", paquet)
                    .hasSameSizeAs(tires);
            paquets.add(new PaquetServi(paquet, contenu, items));
        }
        return paquets;
    }

    /**
     * ADR-0064 : la borne vient du paquet, jamais d'un nombre ecrit ici. Un paquet combinatoire, qui
     * n'enumere aucun item, rend zero et n'entre donc pas dans le perimetre - aucune mecanique de
     * saisie n'en sert aujourd'hui, et le jour ou l'une le ferait, la clause de declaration
     * ci-dessous serait le premier endroit ou cela se verrait.
     */
    private static List<Item> itemsTires(ContentSource contenu, int declares) {
        RandomSource hasard = borne -> 0;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < declares; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            items.add(item);
            dejaVus.add(item.id());
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
        verifierQueChaqueFormeEcriteRendLeVerdictJuste(
                CONTENU_DE_PRODUCTION, "", PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31);
    }

    static void verifierQueChaqueFormeEcriteRendLeVerdictJuste(Path racine, String racineClasspath,
                                                               List<String> declares) {
        List<PaquetServi> paquets = paquetsDeSaisieServis(racine, racineClasspath);
        assertThat(paquets)
                .as("ADR-0051 : la population de ce garde-fou est celle que la clause de declaration"
                        + " ecrit. Si elle est vide alors qu'un paquet de saisie est declare, c'est"
                        + " cette derivation-ci qui ne voit pas ce qu'elle devrait voir - le defaut"
                        + " du 2026-08-31, ou la traversee lisait le catalogue pendant que le"
                        + " fil-piege lisait le repertoire")
                .hasSameSizeAs(declares);

        int formesEprouvees = 0;
        for (PaquetServi paquet : paquets) {
            for (int rang = 0; rang < paquet.items().size(); rang++) {
                ReponseAEcrire item = paquet.items().get(rang);
                // Les formes attendues sont lues DANS LE FICHIER, jamais dans l'item charge : c'est
                // la fabrique qui est sur le chemin, et une assertion qui lit ses deux cotes chez
                // elle ne peut pas la prendre en defaut.
                List<String> formes = formesEcritesDansLeYaml(racine, paquet.identifiant(), rang);
                for (String forme : formes) {
                    assertThat(verdictJusteSur(paquet.contenu(), rang, item, forme))
                            .as("%s, item %s : la forme \"%s\" est ecrite dans le paquet et doit etre"
                                    + " acceptee. SEUIL-2 : une reponse juste refusee pour une"
                                    + " question de forme coute un essai a l'enfant",
                                    paquet.identifiant(), item.id().value(), forme)
                            .isTrue();
                    formesEprouvees++;
                }
            }
        }

        assertThat(formesEprouvees)
                .as("un paquet de saisie est servi, donc au moins une forme doit avoir ete eprouvee")
                .isPositive();
    }

    /**
     * ADR-0051, seconde moitie : l'etat du perimetre est <b>ecrit</b> et date, pas laisse au silence.
     *
     * <p>C'est le fil-piege pose au lot 4, retourne plutot que retire. Il disait "aucun paquet de
     * saisie n'est servi" et rougissait au premier depot ; il dit maintenant <b>lesquels</b> le sont
     * et rougit a tout ecart, dans les deux sens. Sa fonction n'a pas bouge d'un cran : un compte ne
     * passe pas d'une valeur a une autre sans qu'une ligne du depot bouge, et celui qui la fait
     * bouger voit dans le meme geste la population que la clause de traversee gagne ou perd.
     */
    @Test
    void lesPaquetsDeSaisieServisSontExactementCeuxQueCeGardeFouDeclare() {
        verifierQueLesPaquetsDeSaisieSontDeclares(
                CONTENU_DE_PRODUCTION, "", PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31);
    }

    static void verifierQueLesPaquetsDeSaisieSontDeclares(Path racine, String racineClasspath,
                                                          List<String> declares) {
        assertThat(paquetsDeSaisieServis(racine, racineClasspath).stream()
                .map(PaquetServi::identifiant).toList())
                .as("ADR-0051 : si un paquet de saisie vient d'etre depose dans le repertoire de"
                        + " contenu servi, ou d'en etre retire, mettre cette declaration a jour dans"
                        + " le meme commit - et verifier que"
                        + " chaqueFormeAccepteeEcriteDansUnPaquetServiRendLeVerdictJuste l'examine"
                        + " reellement")
                .containsExactlyInAnyOrderElementsOf(declares);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet de saisie que personne n'a inscrit nulle
     * part - ni au catalogue, ni dans la declaration ci-dessus. Seul un perimetre derive du
     * repertoire peut le voir, et c'est la forme exacte qu'aurait le prochain paquet de saisie
     * depose sans que la declaration bouge.
     */
    @Test
    void unPaquetDeSaisieNonDeclareFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueLesPaquetsDeSaisieSontDeclares(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-saisie-neuf-non-declare");
    }

    /**
     * Le meme paquet, une fois declare, ne doit plus rien reprocher - et la clause de traversee doit
     * alors <b>l'examiner</b>. Sans ce second appel, "le garde-fou refuse tout paquet neuf" et "le
     * garde-fou refuse celui qui n'est pas declare" seraient le meme rouge.
     */
    @Test
    void leMemePaquetUneFoisDeclareEstExamineParLaTraversee() {
        List<String> declare = List.of("paquet-saisie-neuf-non-declare");
        verifierQueLesPaquetsDeSaisieSontDeclares(CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, declare);
        verifierQueChaqueFormeEcriteRendLeVerdictJuste(CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH,
                declare);
    }

    /**
     * Rejoue la forme sur une partie neuve du paquet, <b>en visant la manche de l'item</b>.
     *
     * <p>La source de hasard rend le rang de l'item : au premier tirage rien n'a encore ete tire, les
     * restants sont donc tout le paquet dans l'ordre du YAML, et {@code ListContentSource} rend
     * exactement celui-la. L'atteinte est ensuite <b>assertionnee</b> : une manche manquee faisait
     * autrement sortir la forme par un chemin silencieux, et le compteur montait sans que rien n'ait
     * ete juge.
     */
    private static boolean verdictJusteSur(ContentSource contenu, int rang, ReponseAEcrire item,
                                            String forme) {
        Saisie mecanique = new Saisie();
        GameSession session = mecanique.start(contenu, borne -> rang < borne ? rang : 0, 1);
        SaisieSession saisie = (SaisieSession) session;
        assertThat(saisie.enonceManche())
                .as("la manche visee n'a pas ete atteinte : ce garde-fou jugerait une autre manche"
                        + " que celle de l'item %s, ou n'en jugerait aucune", item.id().value())
                .contains(item.enonce());
        SaisieSession apres = (SaisieSession) mecanique.apply(saisie, new SaisieMove(forme));
        return apres.dernierVerdict().map(verdict -> verdict.juste()).orElse(false);
    }
}
