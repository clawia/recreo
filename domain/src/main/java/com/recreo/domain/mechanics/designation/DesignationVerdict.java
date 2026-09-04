package com.recreo.domain.mechanics.designation;

import java.util.Optional;

/**
 * Le resultat d'une manche resolue de designation, avec l'identifiant de la bonne zone pour que
 * l'ecran puisse la designer.
 *
 * <p>Meme forme et meme motif que {@code com.recreo.domain.mechanics.banditmanchot.Verdict}
 * (ADR-0080, decision 3 : la designation ne delegue sa boucle de partie a aucun moteur partage,
 * chaque mecanique porte le sien) : {@code juste} dit si la manche <b>compte comme une reussite</b>,
 * pas seulement si le geste a touche la bonne zone - une manche resolue par revelation (trois echecs
 * puis un appui sur la zone que le jeu vient de nommer) porte {@code false}. {@code explication}
 * transporte celle de l'item joue, sa seule trace une fois la partie terminee ou la manche suivante
 * tiree (ADR-0024).</p>
 */
public record DesignationVerdict(boolean juste, String bonneZone, Optional<String> explication) {
}
