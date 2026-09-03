package com.recreo.bootstrap.catalogue;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0007 : le contexte Spring complet demarre avec le vrai chargeur de contenu, sans catalogue
 * de test importe. Verifie ce qu'ArchUnit ne peut pas voir : chaque entree de catalogue reference
 * un paquet et une mecanique qui existent, et demarre effectivement une partie - la compatibilite
 * de forme entre le paquet et la mecanique qui le joue.
 *
 * <p><b>Une assertion de titre a vecu ici de s01 au 2026-09-03, et elle est retiree ; le motif est
 * ecrit plutot que le retrait laisse nu (ADR-0078, amendement).</b> Elle refusait a
 * {@code bandit-manchot-additions-cp} la sous-chaine {@code "ti"}, sous le nom de {@code S1-8}.
 * <b>nadia-prof a rouvert sa clause et l'a retrecie</b> le 2026-09-03, apres avoir compte la
 * population elle-meme : <b>quatre</b> titres servis et valides par elle portent le grapheme
 * <i>ti</i> prononce /sj/ - <i>Les petites soustractions</i>, <i>Les tables de multiplication</i>,
 * <i>Les fractions simples</i>, <i>La position du son</i> - et cette assertion l'interdisait a un
 * cinquieme. <i>"Je ne peux pas opposer a un cinquieme titre une barre que j'ai levee quatre fois
 * sans meme la voir."</i>
 *
 * <p>Elle separe deux defauts que mon rapport avait reunis en un. <b>Le codage</b> : sa clause dit
 * <i>"le grapheme 'ti' <b>prononce /si/</b>"</i>, et l'assertion avait laisse tomber la
 * prononciation, donc elle rougissait aussi sur <i>petites</i>, ou le grapheme se dit /ti/ et ou la
 * clause n'a rien a dire. <b>Et le sien, qu'elle nomme elle-meme</b> : <i>"le fait que ma clause
 * enonce est exact ; la consequence que j'en tirais est fausse"</i> - <i>"retirer le grapheme ne
 * rend pas le titre decodable, donc la clause ne repare rien. On ne peut pas nommer un jeu
 * d'additions sans le mot addition."</i>
 *
 * <p><b>Ce qui est garde a la place, et c'est elle qui l'a demande sous cette forme</b> : la
 * propriete que l'utilisatrice a trouvee - <i>aucun titre ne porte le niveau de l'enfant</i> - sur
 * une population de quarante titres et avec zero exemption, dans
 * {@code AucunTitreDeJeuNePorteSonNiveauTest}. <b>Ce retrait ne rend pas de terrain sans
 * contrepartie</b> : il echange une assertion sur un identifiant ecrit en dur contre une regle qui
 * porte sur les quarante entrees et sur toutes les suivantes.
 */
@SpringBootTest
class CatalogueChargeableIT {

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MechanicRegistryPort mecaniques;

    @Test
    void leCatalogueSeChargeEtChaqueJeuDemarreUnePartie() {
        List<GameDefinition> jeux = partie.jeuxDisponibles();

        assertThat(jeux)
                .as("ADR-0007 : au moins le jeu d'additions CP livre par ce sprint")
                .isNotEmpty();

        for (GameDefinition jeu : jeux) {
            GameSession session = partie.demarrer(jeu.id());
            assertThat(session)
                    .as("ADR-0007 : %s demarre effectivement une partie", jeu.id())
                    .isNotNull();

            assertThat(jeu.nombreDeManches())
                    .as("ADR-0012 : %s porte une longueur de partie chargee depuis le catalogue."
                            + " Combien de manches est un choix pedagogique ; qu'il y en ait au"
                            + " moins une ne l'est pas", jeu.id())
                    .isPositive();

            GameMechanic mecanique = mecaniques.mecaniquePour(jeu.mecanique());
            assertThat(jeu.nombreDeManches())
                    .as("S3-2, ADR-0013 : %s doit compter assez de manches pour que l'aide de sa"
                            + " mecanique reste atteignable (au moins %d manches)",
                            jeu.id(), mecanique.longueurMinimale())
                    .isGreaterThanOrEqualTo(mecanique.longueurMinimale());
        }
    }
}
