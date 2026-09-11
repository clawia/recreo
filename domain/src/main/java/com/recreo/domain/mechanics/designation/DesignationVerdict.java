package com.recreo.domain.mechanics.designation;

import java.util.Optional;

/**
 * Le resultat d'une manche resolue de designation : ce qui doit survivre a la manche, et rien de
 * plus.
 *
 * <p>Meme forme et meme motif que {@code com.recreo.domain.mechanics.banditmanchot.Verdict}
 * (ADR-0080, decision 3 : la designation ne delegue sa boucle de partie a aucun moteur partage,
 * chaque mecanique porte le sien) : {@code juste} dit si la manche a ete resolue par une
 * designation de la bonne zone, plutot que par une revelation - une manche resolue par revelation
 * (trois echecs puis un appui sur la zone que le jeu vient de nommer) porte {@code false}.
 * {@code explication} transporte celle de l'item joue, sa seule trace une fois la partie terminee
 * ou la manche suivante tiree (ADR-0024).</p>
 *
 * <p>DEC-0061, instruction 1, famille A (S34-SCO1-REMEDE) : {@code juste} ne dit plus a lui seul si
 * la manche compte comme une reussite dans le score. {@code Score#reussites()} ne monte que si, en
 * plus d'une designation juste, aucun echec n'a precede cette designation sur la meme manche -
 * l'enfant l'a trouvee du premier coup (voir {@code DesignationSession#resoudre(boolean)}).</p>
 *
 * <p><b>ADR-0088, clause A : {@code enonce} voyage ici, obligatoire, comme sur les deux mecaniques
 * voisines.</b> ADR-0084 REV-3 avait laisse ce verdict de cote en disant pourquoi - aucun ecran de
 * designation n'etait alors servi, et le modifier pour un ecran inexistant aurait ete une
 * abstraction ecrite pour un usage imagine. Ce motif est perime : un lot de contenu de designation
 * est finance, et {@code nadia-prof} ne valide aucun lot avant que ce champ existe. Le motif est
 * plus fort ici que sur les deux autres mecaniques : <b>sur une designation, la reponse est un
 * endroit sur un dessin, et a la fin de la partie le dessin n'est plus a l'ecran</b> - l'enfant
 * lirait une reponse sans sa question <i>et</i> sans son terrain.</p>
 *
 * <p><b>ADR-0088, clause B : ce verdict ne porte aucune zone, et c'est une decision.</b> Il portait
 * {@code String bonneZone}, c'est-a-dire un {@code Zone.id()} - la valeur que le formulaire renvoie.
 * {@code nadia-prof} a tranche le 2026-09-08 que l'ecran de fin de partie d'une designation
 * <b>ne montre pas la zone attendue et ne montre rien a sa place</b> : ni l'identifiant, qui est une
 * valeur technique, ni le libelle, qui est positionnel par {@code ZON-10} et n'apprend donc rien.
 * La revelation reste ou elle est utile, sur l'ecran de manche ou le dessin est encore la, par
 * {@code DesignationSession.bonneZoneRevelee()}. La composante est donc <b>retiree</b> plutot que
 * laissee a la vigilance d'un gabarit : un adaptateur ne peut pas rendre une valeur qui n'existe
 * pas a l'instant ou il rend cet ecran.</p>
 */
public record DesignationVerdict(boolean juste, Optional<String> explication, String enonce) {
}
