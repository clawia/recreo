# File d'attente de validation pedagogique - recreo

Prevue par `DEC-0001-perimetre-sprint-0.md` (section Impact), alimentee par `camille-po` a
chaque review de sprint. Cette file liste tout increment dont le Done technique est atteint mais
dont le Done pedagogique n'est pas encore `VALIDE` par `nadia-prof` - qu'il attende son premier
audit, ou qu'il ait ete `REFUSE` et attende sa reprise.

## Regle de gel du contenu

Si cette file compte plus de 2 a 3 increments non valides pedagogiquement en attente au meme
moment, aucune nouvelle story de contenu n'est ouverte au planning suivant : le sprint suivant
est consacre a vider la file, pas a l'allonger. C'est `camille-po` qui constate le depassement au
planning et qui le trace ici et dans le planning du sprint concerne.

## File actuelle

| Increment | Fiche | Verdict | Depuis | Ce qui bloque le deblocage |
|---|---|---|---|---|
| bandit-manchot-additions-cp | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` | REFUSE | 2026-08-04 (sprint 0) | Les trois motifs de refus, transformes en stories S1-1, S1-2, S1-3 de `docs/backlog/backlog.md` (texte accentue, score lisible, ecran d'erreur franchissable). Les sept reserves de la fiche ne bloquent pas le deblocage mais sont aussi mises en story (S1-4 a S1-9) parce que deux d'entre elles touchent au coeur du produit (partie sans fin, traitement de l'erreur). |

**Compte actuel : 1 increment en attente.** Sous le seuil de gel (2-3) : le sprint 1 peut ouvrir
les stories correctives sans que le contenu du produit soit gele. Si un deuxieme increment
entrait en file avant que celui-ci ne soit releve a `VALIDE` ou `VALIDE SOUS RESERVE`, la regle de
gel devrait etre reevaluee au planning suivant.

## Historique

Aucun increment n'est encore sorti de cette file (creee au sprint 1, a la review du sprint 0).
