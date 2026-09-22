package com.recreo.adapter.content;

import com.recreo.application.catalog.OrdreDesNiveaux;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0095 : tout niveau servi par le catalogue est nomme dans {@link OrdreDesNiveaux#SEQUENCE}.
 *
 * <p><b>Pourquoi ce garde-fou existe, et pourquoi il n'est pas une regle ArchUnit.</b> La sequence
 * d'ADR-0095 est une liste ecrite a la main - la seule du mecanisme. Une liste ecrite a la main
 * pourrit : le jour ou un paquet CE2 entre, ou ou un paquet ecrit {@code "ce1"} en minuscules,
 * le tri ne rougit pas, il range simplement ce niveau en dernier et personne ne le voit. C'est
 * exactement le defaut qu'un cliquet doit interdire : <i>un plafond qui depasse la dette n'est pas
 * un cliquet</i>. Ce que ce fichier tient est donc la seule chose qui empeche la sequence d'etre
 * decorative. ArchUnit ne peut pas la tenir : la population est du YAML, pas du bytecode.
 *
 * <p><b>La population est derivee, pas enumeree</b> (ADR-0013). Elle vient du recensement de
 * {@link AucunTitreDeJeuNePorteSonNiveauTest#entreesServies}, qui lit le catalogue servi puis le
 * niveau du paquet que chaque entree designe, indexe par l'identifiant que le paquet declare. Le
 * recensement est reutilise plutot que recopie : deux recensements du meme materiau divergent, et
 * celui-ci a deja ete corrige une fois (blanc final, ADR-0079).
 *
 * <p><b>Ce que ce garde-fou ne dit pas</b> : quel doit etre l'ordre. C'est DEC-0090 qui le fixe, et
 * {@code LOrdreDesNiveauxResteUneDonneeTest.laSequenceEstCelleQueDec0090Fixe} qui l'epingle. Ici,
 * on verifie seulement qu'aucun niveau reellement servi n'echappe a cette sequence.
 */
class ChaqueNiveauServiEstNommeDansLaSequenceTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ChaqueNiveauServiEstNommeDansLaSequenceTest");

    /**
     * Le juge, pur : il ne lit ni disque ni classpath, ce qui permet de le mordre sur du materiau
     * reel mute en memoire plutot que sur une fixture ecrite de ma main.
     */
    static void verifier(List<String> niveauxServis) {
        if (niveauxServis.isEmpty()) {
            throw new AssertionError("ADR-0095 : aucun niveau servi, cette regle ne porte alors sur"
                    + " rien - un garde-fou qui ne trouve plus sa population le dit au lieu de"
                    + " passer");
        }
        List<String> fautes = new ArrayList<>();
        for (String niveau : niveauxServis) {
            if (niveau == null || niveau.isBlank()) {
                fautes.add("un paquet servi ne declare aucun niveau : la sequence d'ADR-0095 n'a"
                        + " alors rien a ranger, et le tri de l'en-tete devient silencieusement"
                        + " arbitraire");
                continue;
            }
            if (!OrdreDesNiveaux.SEQUENCE.contains(niveau.strip())) {
                fautes.add("le niveau \"" + niveau + "\" est servi par le catalogue et n'est pas"
                        + " nomme dans OrdreDesNiveaux.SEQUENCE " + OrdreDesNiveaux.SEQUENCE
                        + ". L'en-tete le rangerait en dernier sans rien signaler (ADR-0095) :"
                        + " soit la sequence l'accueille, apres une decision produit sur sa place,"
                        + " soit le paquet corrige l'orthographe de son niveau");
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("niveaux servis : " + fautes);
        }
    }

    /**
     * {@code List.copyOf} n'est pas employe a dessein : il refuse le {@code null}, et un paquet
     * sans niveau ferait alors remonter une NullPointerException la ou ce garde-fou doit rendre
     * un motif lisible.
     */
    static List<String> niveauxServis() {
        return niveauxDe(CATALOGUE_SERVI, CONTENU_SERVI);
    }

    static List<String> niveauxDe(Path catalogue, Path repertoireDeContenu) {
        Set<String> distincts = new LinkedHashSet<>();
        AucunTitreDeJeuNePorteSonNiveauTest
                .entreesServies(catalogue, repertoireDeContenu)
                .forEach(entree -> distincts.add(entree.niveau()));
        return new ArrayList<>(distincts);
    }

    @Test
    void toutNiveauServiParLeCatalogueEstNommeDansLaSequence() {
        verifier(niveauxServis());
    }

    /**
     * Morsure 1, sur materiau reel mute en memoire : le niveau neuf qui entre sans que la sequence
     * l'accueille. C'est le geste que s37 rend imminent - un paquet CE1, puis un CM2 - et il est
     * mordu ici avant qu'il ne se produise, pas apres.
     */
    @Test
    void unNiveauServiAbsentDeLaSequenceFaitEchouerLeGardeFou() {
        List<String> niveaux = new ArrayList<>(niveauxServis());
        niveaux.add("CE2");

        assertThatThrownBy(() -> verifier(niveaux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CE2");
    }

    /**
     * Morsure 2 : la meme faute dans sa forme la plus discrete, une casse differente de celle que
     * la sequence ecrit. Le tri la rangerait en dernier sans rien dire ; la casse n'est
     * volontairement pas normalisee par {@code OrdreDesNiveaux}, parce qu'elle se corrige dans le
     * paquet et ne doit pas etre absorbee en silence.
     */
    @Test
    void unNiveauServiDansUneAutreCasseFaitEchouerLeGardeFou() {
        List<String> niveaux = new ArrayList<>(niveauxServis());
        niveaux.add("ce1");

        assertThatThrownBy(() -> verifier(niveaux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ce1");
    }

    /** Morsure 3 : un paquet sans niveau. Le garde-fou le dit plutot que de l'ignorer. */
    @Test
    void unPaquetServiSansNiveauFaitEchouerLeGardeFou() {
        List<String> niveaux = new ArrayList<>(niveauxServis());
        niveaux.add("  ");

        assertThatThrownBy(() -> verifier(niveaux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare aucun niveau");
    }

    /** Morsure 4 : une population vide ne rend pas ce garde-fou vert. */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun niveau servi");
    }

    /**
     * Le recensement atteint bien le materiau reel : sans cela, les morsures ne prouveraient rien
     * et le test principal serait vert sur une population vide. L'assertion ne fige pas la liste -
     * un paquet CE1 entre a s37 et ne doit pas la rendre rouge.
     */
    @Test
    void leRecensementAtteintLeCatalogueServi() {
        assertThat(niveauxServis()).contains("CP", "CM1");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>. Elle n'est pas une redite
     * des quatre precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse.
     * C'est le seul test du fichier qui prouve que le recensement sait lire un catalogue et un
     * repertoire de paquets sur disque, et surtout qu'il joint les deux - le niveau vient du
     * paquet que l'entree designe, et une jointure ratee rendrait un niveau nul, donc un rouge
     * pour la mauvaise raison.
     *
     * <p>Le jeu fautif porte deux entrees et une seule faute : ce qu'il prouve est que la regle
     * discrimine, pas seulement qu'elle sait dire non.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaSeuleEntreeFautive() {
        List<String> fautifs = niveauxDe(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux niveaux")
                .containsExactly("CE2", "CP");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CE2")
                .hasMessageNotContaining("\"CP\" est servi");
    }
}
