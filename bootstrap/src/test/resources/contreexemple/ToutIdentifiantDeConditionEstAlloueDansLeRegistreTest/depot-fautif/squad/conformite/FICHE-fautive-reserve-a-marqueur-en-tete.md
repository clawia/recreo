# Fiche fautive - une reserve dont le marqueur est en tete

Cette fiche ouvre une reserve sous la forme que la clause 7 d'`ADR-0100` refuse : le marqueur `R`
y est le premier segment, au lieu de vivre dans le segment de numero.

`R-ZZZ-CP-01-1`. Aucun des deux motifs d'allocation ne lit ce jeton, et le registre ne sait pas
ecrire une ligne pour lui : sans le compteur du cliquet, il ne serait ni rouge ni vert - il serait
invisible.

`ZZZ-R9-9`, la seconde place : le marqueur y est au milieu, comme dans une notation d'intervalle
collee. Avant `ADR-0100`, cette forme etait lue par le motif a trois segments **et allouable**,
donc elle produisait un faux rouge qu'une ligne de registre aurait eteint - en faisant entrer une
notation dans l'espace des identifiants.

La forme conforme du meme besoin serait `ZZZ-CP-R1` : elle est lue par la clause 2, donc elle rougit
ici tant qu'elle n'est pas allouee, et c'est exactement ce qu'on lui demande.
