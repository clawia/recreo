package com.recreo.domain.mechanics.designation;

import com.recreo.domain.content.CadreDeZone;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionStatus;
import org.junit.jupiter.api.Test;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0088 : <b>le verdict d'une partie de designation porte la question qu'il commente, et il ne
 * porte aucune trace de la zone attendue.</b>
 *
 * <p><b>Ce que ce garde-fou ajoute a la clause ArchUnit 12, et pourquoi les deux existent.</b> La
 * clause 12 de {@code DecouplageMecaniqueContenuTest} voit une <b>composante</b> : elle dit que le
 * verdict declare {@code String enonce}, et elle ne saura jamais quelle valeur y voyage - une
 * mecanique qui passerait la chaine vide, ou l'enonce d'une autre manche, la laisserait verte. Ce
 * test-ci juge la <b>valeur</b>, sur une partie reellement jouee jusqu'a sa fin. Et il ferme
 * l'autre moitie, celle qu'aucune regle de types ne peut voir : que rien dans ce verdict ne porte
 * la zone attendue.</p>
 *
 * <p><b>Pourquoi la seconde moitie est une decision et non une precaution.</b>
 * {@code nadia-prof}, 2026-09-08 : l'ecran de fin de partie d'une designation <b>ne montre pas la
 * zone attendue et ne montre rien a sa place</b>. Ni l'identifiant - c'est une valeur de
 * formulaire - ni le libelle : {@code ZON-10} rend les libelles de zone <b>positionnels</b> parce
 * qu'un garde-fou de contenu interdit qu'un enonce nomme le libelle de sa bonne zone, et
 * <i>"le pays en bas a droite"</i> n'apprend rien de la France. La revelation reste sur l'ecran de
 * manche, ou le dessin est encore la, par {@link DesignationSession#bonneZoneRevelee()}.</p>
 *
 * <p><b>Critere derive, jamais enumere.</b> Les composantes du verdict ne sont pas nommees ici :
 * elles sont lues sur le record ({@link Class#getRecordComponents()}) et chacune est confrontee aux
 * valeurs interdites. Une composante ajoutee demain entre dans le critere sans qu'une ligne de ce
 * fichier change - c'est ce qui distingue ce test d'une assertion sur l'absence d'un champ dont on
 * connaissait le nom.</p>
 */
class LeVerdictDUneDesignationPorteSaQuestionEtJamaisSaZoneTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Questionner le monde", "CP", "Se situer dans l'espace",
                    "situer un objet sur un plan simple");

    private static final RandomSource TOUJOURS_ZERO = borne -> 0;

    private static final int MANCHES = 2;

    private static Zone zone(String id, String libelle) {
        return new Zone(id, libelle, new CadreDeZone(0, 0, 40, 40));
    }

    private static ZoneADesigner item(String idItem, String enonce, Zone bonne, Zone autre) {
        return new ZoneADesigner(new ItemId(idItem), enonce, "plan-classe",
                "Plan de la classe vue de dessus.", List.of(bonne, autre), bonne.id(),
                Optional.empty(), Optional.empty());
    }

    private static final Zone TABLEAU = zone("z-nord", "la zone en haut");

    private static final Zone PORTE = zone("z-sud", "la zone en bas");

    private static final ZoneADesigner PREMIERE =
            item("p1", "Montre le tableau.", TABLEAU, PORTE);

    private static final ZoneADesigner DERNIERE =
            item("p2", "Montre la porte.", PORTE, TABLEAU);

    private static ContentSource deuxManches() {
        return new ListContentSource(DESCRIPTEUR, List.of(PREMIERE, DERNIERE));
    }

    /**
     * Une partie jouee <b>mal jusqu'au bout</b> sur les deux manches - trois echecs puis l'appui sur
     * la zone que le jeu vient de nommer -, c'est-a-dire le seul chemin par lequel l'ecran de fin de
     * partie a quelque chose a reveler. Meme geste que le garde-fou de rendu d'ADR-0084.
     */
    private static DesignationSession partieJoueeMalJusquAuBout() {
        Designation mecanique = new Designation();
        DesignationSession session =
                (DesignationSession) mecanique.start(deuxManches(), TOUJOURS_ZERO, MANCHES);
        for (int manche = 0; manche < MANCHES; manche++) {
            ZoneADesigner courante = manche == 0 ? PREMIERE : DERNIERE;
            String fausse = courante.zones().stream()
                    .map(Zone::id)
                    .filter(id -> !id.equals(courante.bonneZone()))
                    .findFirst()
                    .orElseThrow();
            for (int essai = 0; essai < 3; essai++) {
                session = jouer(mecanique, session, fausse);
            }
            session = jouer(mecanique, session, courante.bonneZone());
        }
        return session;
    }

    private static DesignationSession jouer(Designation mecanique, DesignationSession session,
                                             String zoneDesignee) {
        GameSession suivante = mecanique.apply(session, new DesignationMove(zoneDesignee));
        return (DesignationSession) suivante;
    }

    /**
     * Les composantes de ce record dont la valeur porte l'une des chaines interdites, avec leur nom
     * pour que le grief nomme une cause. {@code Optional} est deplie : une zone rangee dans un
     * {@code Optional} serait aussi lisible par un gabarit qu'une zone nue.
     */
    private static List<String> composantesQuiPortent(Record objet, Set<String> interdites) {
        List<String> porteuses = new ArrayList<>();
        for (RecordComponent composante : objet.getClass().getRecordComponents()) {
            Object valeur;
            try {
                valeur = composante.getAccessor().invoke(objet);
            } catch (ReflectiveOperationException echec) {
                throw new IllegalStateException("composante illisible : " + composante.getName(),
                        echec);
            }
            if (valeur instanceof Optional<?> enveloppe) {
                valeur = enveloppe.orElse(null);
            }
            if (valeur != null && interdites.contains(String.valueOf(valeur))) {
                porteuses.add(composante.getName() + " = " + valeur);
            }
        }
        return porteuses;
    }

    /** L'identifiant et le libelle de la bonne zone de l'item joue - les deux formes refusees. */
    private static Set<String> tracesDeLaZoneAttendue(ZoneADesigner item) {
        Set<String> traces = new LinkedHashSet<>();
        traces.add(item.bonneZone());
        item.zones().stream()
                .filter(zone -> zone.id().equals(item.bonneZone()))
                .map(Zone::libelle)
                .forEach(traces::add);
        return traces;
    }

    @Test
    void leVerdictDeFinDePartiePorteLEnonceDeLaMancheQuIlCommente() {
        DesignationSession finie = partieJoueeMalJusquAuBout();

        assertThat(finie.status())
                .as("la partie doit etre reellement terminee, sans quoi ce test ne juge pas l'objet"
                        + " que l'ecran de fin de partie recevra")
                .isEqualTo(SessionStatus.TERMINEE);
        assertThat(finie.dernierVerdict()).isPresent();
        assertThat(finie.dernierVerdict().orElseThrow().enonce())
                .as("ADR-0084 REV-3 : la reponse d'une fin de partie voyage avec la question qu'elle"
                        + " commente, et c'est celle de la derniere manche - pas celle d'une autre")
                .isEqualTo(DERNIERE.enonce());
    }

    @Test
    void aucuneComposanteDuVerdictNePorteLaZoneAttendue() {
        DesignationSession finie = partieJoueeMalJusquAuBout();

        assertThat(composantesQuiPortent(finie.dernierVerdict().orElseThrow(),
                tracesDeLaZoneAttendue(DERNIERE)))
                .as("nadia-prof, 2026-09-08 : l'ecran de fin de partie d'une designation ne montre"
                        + " pas la zone attendue et ne montre rien a sa place. Un identifiant est une"
                        + " valeur de formulaire ; un libelle est positionnel (ZON-10) et n'apprend"
                        + " rien. Ce que le verdict ne porte pas, aucun gabarit ne peut le rendre")
                .isEmpty();
    }

    @Test
    void laZoneAttendueNEstPlusAtteignableSurUnePartieTerminee() {
        DesignationSession finie = partieJoueeMalJusquAuBout();

        assertThat(finie.bonneZoneRevelee())
                .as("la revelation vit sur l'ecran de manche, ou le dessin est encore la ; une partie"
                        + " terminee n'a plus de manche courante et n'a donc plus de zone a montrer")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, et la morsure porte sur du materiau reel plutot que sur une fixture de
     * la main de l'auteur : {@link ZoneADesigner} est le seul type du domaine qui porte la bonne zone
     * <b>a bon droit</b>, et c'est exactement l'objet que le verdict ne doit pas recopier. Le critere
     * doit rougir dessus. S'il n'y rougissait pas, il ne rougirait pas davantage sur un verdict qui
     * remettrait la zone en circulation, et les trois assertions ci-dessus seraient vertes sur rien.
     */
    @Test
    void leCritereMordSurLObjetQuiPorteLaZoneABonDroit() {
        assertThat(composantesQuiPortent(DERNIERE, tracesDeLaZoneAttendue(DERNIERE)))
                .as("un critere qui ne trouve pas la zone la ou elle est ne prouve pas son absence"
                        + " la ou elle ne doit pas etre")
                .isNotEmpty();
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Un verdict a
     * composantes vides passerait les trois assertions ci-dessus sans que rien ne le dise.
     */
    @Test
    void leCritereExamineLesComposantesDuVerdictEtNonUneListeVide() {
        assertThat(DesignationVerdict.class.getRecordComponents())
                .as("ADR-0051 : un critere derive des composantes d'un record ne prouve rien si le"
                        + " record n'en declare aucune")
                .isNotEmpty();
        assertThat(tracesDeLaZoneAttendue(DERNIERE))
                .as("les deux formes refusees sont l'identifiant et le libelle : si l'une des deux"
                        + " manquait, la moitie du critere serait muette")
                .hasSize(2);
    }

    /**
     * Le tirage de la source, pour que les deux items joues soient ceux que ce test nomme.
     *
     * <p><b>La source est videe, jamais bornee, et c'est ADR-0064 qui l'a exige.</b> Le premier jet
     * de ce test bornait sa boucle de tirage a la constante du nombre de manches, et
     * {@code UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest} l'a fait rougir : une borne
     * ecrite a la main dans une boucle qui tire sans remise lit un paquet en partie sans que rien ne
     * le dise. Le grief etait juste sur la <b>forme</b> - la source est ici fabriquee a deux items,
     * donc rien n'etait perdu -, et la sortie n'est pas une exemption : la boucle vide la source
     * jusqu'a l'absence, ce qui est plus fort qu'une borne derivee, et le compte attendu est ensuite
     * assertionne.</p>
     */
    @Test
    void lesDeuxItemsJouesSontBienCeuxQueCeTestNomme() {
        ContentSource contenu = deuxManches();
        Set<ItemId> dejaVus = new LinkedHashSet<>();
        List<Item> tires = new ArrayList<>();
        for (Optional<Item> tire = contenu.next(TOUJOURS_ZERO, dejaVus);
             tire.isPresent();
             tire = contenu.next(TOUJOURS_ZERO, dejaVus)) {
            tires.add(tire.orElseThrow());
            dejaVus.add(tire.orElseThrow().id());
        }

        assertThat(tires).containsExactly(PREMIERE, DERNIERE);
    }
}
