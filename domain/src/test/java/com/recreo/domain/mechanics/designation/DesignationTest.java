package com.recreo.domain.mechanics.designation;

import com.recreo.domain.content.CadreDeZone;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test de comportement exige par ADR-0008 : le nom exact de la classe est verifie par
 * ToutesLesMecaniquesSontTesteesTest. Graine fixe, sans navigateur.
 *
 * <p>ADR-0080, decision 3 : cette mecanique reprend la boucle de partie de la mecanique voisine
 * (trois essais, revelation, une reponse fausse ne tire pas la manche suivante), donc ce test en
 * reprend le plan - sans jamais dependre d'elle (ADR-0004 clause 7).</p>
 */
class DesignationTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Espace et geometrie",
                    "se reperer sur un plan simple");

    private static final RandomSource TOUJOURS_ZERO = bound -> 0;

    private static final int MANCHES_LARGEMENT_SUFFISANTES = 100;

    private static Zone zone(String id) {
        return new Zone(id, "la zone " + id, new CadreDeZone(0, 0, 10, 10));
    }

    private static ZoneADesigner itemDePlan(String idItem, String enonce, String bonneZone,
                                             String... autresZones) {
        List<Zone> zones = new java.util.ArrayList<>();
        zones.add(zone(bonneZone));
        for (String autre : autresZones) {
            zones.add(zone(autre));
        }
        return new ZoneADesigner(new ItemId(idItem), enonce, "plan-classe", "Plan de la classe vue"
                + " de dessus.", zones, bonneZone, Optional.empty(), Optional.empty());
    }

    private static ContentSource troisManches() {
        return new ListContentSource(DESCRIPTEUR, List.of(
                itemDePlan("z1", "Montre le tableau.", "tableau", "coin-lecture"),
                itemDePlan("z2", "Montre la porte.", "porte", "fenetre"),
                itemDePlan("z3", "Montre la fenetre.", "fenetre", "porte")));
    }

    private static DesignationSession demarrer(ContentSource contenu) {
        return demarrer(contenu, MANCHES_LARGEMENT_SUFFISANTES);
    }

    private static DesignationSession demarrer(ContentSource contenu, int nombreDeManches) {
        Designation mecanique = new Designation();
        GameSession session = mecanique.start(contenu, TOUJOURS_ZERO, nombreDeManches);
        return (DesignationSession) session;
    }

    private static DesignationSession jouer(DesignationSession session, String zoneDesignee) {
        Designation mecanique = new Designation();
        GameSession suivante = mecanique.apply(session, new DesignationMove(zoneDesignee));
        return (DesignationSession) suivante;
    }

    @Test
    void connaitSonIdentifiant() {
        assertThat(new Designation().id()).isEqualTo(new MechanicId("designation"));
    }

    @Test
    void publieSaLongueurMinimalePourQueLAideResteAtteignable() {
        assertThat(new Designation().longueurMinimale()).isEqualTo(1);
    }

    @Test
    void laPremiereMancheEstTireeAuDemarrage() {
        DesignationSession session = demarrer(troisManches());

        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(session.enonceManche()).contains("Montre le tableau.");
        assertThat(session.illustrationManche()).contains("plan-classe");
        assertThat(session.illustrationTexteAlternatifManche())
                .contains("Plan de la classe vue de dessus.");
        assertThat(session.zonesManche()).extracting(Zone::id).containsExactly("tableau", "coin-lecture");
        assertThat(session.score()).isEqualTo(new Score(0, 0));
        assertThat(session.dernierVerdict()).isEmpty();
        assertThat(session.indice()).as("aucun echec encore : pas d'indice").isEmpty();
        assertThat(session.zonesEssayees()).isEmpty();
        assertThat(session.derniereTentative()).isEmpty();
        assertThat(session.revelee()).isFalse();
    }

    /** ADR-0080, decision 3 et 4 : l'ordre des zones n'est jamais melange, contrairement au bandit manchot. */
    @Test
    void lOrdreDesZonesNEstJamaisMelange() {
        DesignationSession session = demarrer(troisManches());

        assertThat(session.zonesManche()).extracting(Zone::id).containsExactly("tableau", "coin-lecture");
    }

    @Test
    void uneDesignationCorrecteEstJugeeJusteEtLeScoreAvance() {
        DesignationSession premiere = demarrer(troisManches());

        DesignationSession suivante = jouer(premiere, "tableau");

        assertThat(suivante.dernierVerdict())
                .contains(new DesignationVerdict(true, Optional.empty(), "Montre le tableau."));
        assertThat(suivante.score()).isEqualTo(new Score(1, 1));
        assertThat(suivante.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(suivante.enonceManche()).contains("Montre la porte.");
        assertThat(suivante.zonesEssayees()).as("manche fraiche : aucun essai encore").isEmpty();
    }

    /**
     * DEC-0061, instruction 1, famille A (S34-SCO1-REMEDE) : l'enfant garde ses trois essais, et
     * une manche resolue apres un echec n'est plus comptee comme trouvee du premier coup.
     */
    @Test
    void uneDesignationJusteApresUnEchecResoutLaMancheSansLaCompterCommeUneReussite() {
        DesignationSession session = demarrer(troisManches());

        DesignationSession apresLEchec = jouer(session, "coin-lecture");
        DesignationSession apresLaBonneDesignation = jouer(apresLEchec, "tableau");

        assertThat(apresLaBonneDesignation.dernierVerdict().orElseThrow().juste())
                .as("la manche est resolue par une designation juste, pas par une revelation")
                .isTrue();
        assertThat(apresLaBonneDesignation.score())
                .as("trouvee apres un echec : elle n'est pas comptee comme une reussite")
                .isEqualTo(new Score(0, 1));
    }

    @Test
    void uneDesignationIncorrecteResteSurLaMemeMancheSansRevelerLaBonneZone() {
        DesignationSession premiere = demarrer(troisManches());

        DesignationSession apresLEchec = jouer(premiere, "coin-lecture");

        assertThat(apresLEchec.enonceManche()).as("la manche n'a pas avance").contains("Montre le tableau.");
        assertThat(apresLEchec.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(apresLEchec.score())
                .as("aucune manche resolue : le score ne bouge pas")
                .isEqualTo(new Score(0, 0));
        assertThat(apresLEchec.zonesEssayees()).containsExactly("coin-lecture");
        assertThat(apresLEchec.derniereTentative()).contains("coin-lecture");
        assertThat(apresLEchec.revelee()).as("un seul echec : pas encore de revelation").isFalse();
        assertThat(apresLEchec.bonneZoneRevelee()).isEmpty();
    }

    @Test
    void troisEchecsRevelentLaBonneZoneSansResoudreLaManche() {
        DesignationSession session = demarrer(troisManches());

        for (int echec = 0; echec < 3; echec++) {
            session = jouer(session, "coin-lecture");
        }

        assertThat(session.revelee()).isTrue();
        assertThat(session.bonneZoneRevelee()).contains("tableau");
        assertThat(session.score()).as("la revelation seule ne resout pas la manche").isEqualTo(new Score(0, 0));
        assertThat(session.enonceManche()).contains("Montre le tableau.");
    }

    /**
     * Meme motif que M11 clause 3 chez la mecanique voisine : designer la bonne zone apres
     * revelation resout la manche sans compter comme une reussite.
     */
    @Test
    void designerLaBonneZoneApresRevelationResoutSansCompterCommeUneReussite() {
        DesignationSession session = demarrer(troisManches());
        for (int echec = 0; echec < 3; echec++) {
            session = jouer(session, "coin-lecture");
        }

        DesignationSession apresRevelation = jouer(session, "tableau");

        assertThat(apresRevelation.dernierVerdict().orElseThrow().juste())
                .as("une manche resolue par revelation n'est pas une reussite")
                .isFalse();
        assertThat(apresRevelation.score()).isEqualTo(new Score(0, 1));
        assertThat(apresRevelation.enonceManche()).contains("Montre la porte.");
    }

    @Test
    void designerUneAutreZoneApresRevelationNeChangeRien() {
        DesignationSession session = demarrer(troisManches());
        for (int echec = 0; echec < 3; echec++) {
            session = jouer(session, "coin-lecture");
        }

        DesignationSession apresAutreEssai = jouer(session, "coin-lecture");

        assertThat(apresAutreEssai.enonceManche()).contains("Montre le tableau.");
        assertThat(apresAutreEssai.score()).isEqualTo(new Score(0, 0));
        assertThat(apresAutreEssai.revelee()).isTrue();
    }

    /**
     * ADR-0080, decision 3 : une zone inconnue (un identifiant qui ne correspond a aucune zone de
     * la manche) est traitee comme n'importe quelle designation fausse, sans chemin d'erreur neuf.
     */
    @Test
    void uneZoneInconnueEstTraiteeCommeUneDesignationFausse() {
        DesignationSession premiere = demarrer(troisManches());

        DesignationSession apres = jouer(premiere, "zone-qui-nexiste-pas");

        assertThat(apres.enonceManche()).contains("Montre le tableau.");
        assertThat(apres.zonesEssayees()).containsExactly("zone-qui-nexiste-pas");
        assertThat(apres.score()).isEqualTo(new Score(0, 0));
    }

    @Test
    void leVerdictPorteLExplicationDeLItemJoueEtRienQuandLItemNEnPorteAucune() {
        String explicationZ1 = "xyzzy 42 - ceci n'est pas une phrase";
        ZoneADesigner z1 = new ZoneADesigner(new ItemId("z1"), "Montre le tableau.", "plan-classe",
                "Plan de la classe vue de dessus.", List.of(zone("tableau"), zone("porte")), "tableau",
                Optional.empty(), Optional.of(explicationZ1));
        ZoneADesigner z2 = itemDePlan("z2", "Montre la porte.", "porte", "tableau");
        ContentSource contenu = new ListContentSource(DESCRIPTEUR, List.of(z1, z2));
        DesignationSession session = demarrer(contenu);

        DesignationSession apresUneReussite = jouer(session, "tableau");
        assertThat(apresUneReussite.dernierVerdict().orElseThrow().explication())
                .as("reussite immediate : l'explication de l'item joue voyage telle quelle")
                .contains(explicationZ1);

        DesignationSession apresTroisEchecs = apresUneReussite;
        for (int echec = 0; echec < 3; echec++) {
            apresTroisEchecs = jouer(apresTroisEchecs, "tableau");
        }
        assertThat(apresTroisEchecs.revelee()).isTrue();

        DesignationSession apresRevelation = jouer(apresTroisEchecs, "porte");
        assertThat(apresRevelation.dernierVerdict().orElseThrow().juste()).isFalse();
        assertThat(apresRevelation.dernierVerdict().orElseThrow().explication())
                .as("un item sans explication n'en fait pas apparaitre une")
                .isEmpty();
    }

    /** M21, transpose : un paquet dont les indices nomment la reponse ne montre jamais d'indice. */
    @Test
    void aucunIndiceQuandLePaquetDeclareQueSesIndicesNommentLaReponse() {
        ContentSource contenu = new ListContentSource(DESCRIPTEUR,
                List.of(itemDePlan("z1", "Montre le tableau.", "tableau", "porte")), true);
        DesignationSession session = demarrer(contenu);

        DesignationSession apresUnEchec = jouer(session, "porte");

        assertThat(apresUnEchec.indice()).isEmpty();
    }

    @Test
    void unePartieAvanceDeManchesJusquATerminaison() {
        DesignationSession session = demarrer(troisManches(), 2);

        DesignationSession premiereResolue = jouer(session, "tableau");
        assertThat(premiereResolue.status()).isEqualTo(SessionStatus.EN_COURS);

        DesignationSession derniereResolue = jouer(premiereResolue, "porte");
        assertThat(derniereResolue.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(derniereResolue.score()).isEqualTo(new Score(2, 2));
        assertThat(derniereResolue.enonceManche()).isEmpty();
        assertThat(derniereResolue.zonesManche()).isEmpty();
    }

    @Test
    void jouerSurUneSessionInconnueEstRefuse() {
        Designation mecanique = new Designation();
        GameSession sessionInconnue = new GameSession() {
            @Override
            public SessionStatus status() {
                return SessionStatus.EN_COURS;
            }

            @Override
            public Score score() {
                return new Score(0, 0);
            }
        };

        assertThatThrownBy(() -> mecanique.apply(sessionInconnue, new DesignationMove("tableau")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("session inconnue");
    }

    @Test
    void jouerUnCoupInconnuEstRefuse() {
        Designation mecanique = new Designation();
        DesignationSession session = demarrer(troisManches());
        Move coupInconnu = new Move() { };

        assertThatThrownBy(() -> mecanique.apply(session, coupInconnu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("coup inconnu");
    }

    /** ADR-0004, couture scellee d'Item : cette mecanique dit son ignorance des deux autres formes. */
    @Test
    void unItemDeChoixEstRefuseParLaMecaniqueDeDesignation() {
        Question question = new Question(new ItemId("q1"), "un", "1", List.of("2"));
        ContentSource contenu = new ListContentSource(DESCRIPTEUR, List.of(question));

        assertThatThrownBy(() -> demarrer(contenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("q1")
                .hasMessageContaining("designation");
    }

    /** Rejoue le meme item deux fois de suite (dejaVus vide) : marche a graine fixe, comme les deux voisines. */
    @Test
    void auMoinsUneMancheEstJouableAvecUnSeulItem() {
        Item item = itemDePlan("z1", "Montre le tableau.", "tableau", "porte");
        ContentSource contenu = new ContentSource() {
            @Override
            public ContentDescriptor descriptor() {
                return DESCRIPTEUR;
            }

            @Override
            public Optional<Item> next(RandomSource hasard, java.util.Set<ItemId> dejaVus) {
                return Optional.of(item);
            }

            @Override
            public boolean indicesNommentLaReponse() {
                return false;
            }
        };
        DesignationSession session = demarrer(contenu, 3);

        DesignationSession apres = jouer(session, "tableau");

        assertThat(apres.score()).isEqualTo(new Score(1, 1));
        assertThat(apres.status()).isEqualTo(SessionStatus.EN_COURS);
    }
}
