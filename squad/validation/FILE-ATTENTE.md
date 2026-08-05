# File d'attente de validation pedagogique - recreo

Prevue par `DEC-0001-perimetre-sprint-0.md` (section Impact), alimentee par `camille-po` a
chaque review de sprint. Cette file liste tout increment dont le Done technique est atteint mais
dont le Done pedagogique n'est pas encore `VALIDE` par `nadia-prof` - qu'il attende son premier
audit, ou qu'il ait ete `REFUSE` et attende sa reprise.

## Correction de s03 - ce fichier etait perime depuis deux sprints

Ce fichier est reste sur le verdict `REFUSE` de s00 (`2026-08-04`) pendant tout s01 et tout s02,
alors que `nadia-prof` a accorde le Done pedagogique de `bandit-manchot-additions-cp` le
2026-08-05, a la cloture de s01 (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict
`VALIDE SOUS RESERVE`). La review de s01 avait deja route cet obstacle vers moi
(`squad/sprints/s01-lever-refus-pedagogique/review.md`, tableau des obstacles : "`FILE-ATTENTE.md`
ne reflete pas encore le verdict [...] (porte encore 'REFUSE, depuis s00')") ; s02 ne l'a pas
traite. Je le corrige a l'ouverture de s03, en le disant explicitement plutot qu'en silence : un
fichier qui n'est pas la seule memoire fiable de son propre etat pendant deux sprints est
exactement le defaut que la gouvernance de `recreo` cherche a empecher.

## Regle de gel du contenu

Si cette file compte plus de 2 a 3 increments non valides pedagogiquement en attente au meme
moment, aucune nouvelle story de contenu n'est ouverte au planning suivant : le sprint suivant
est consacre a vider la file, pas a l'allonger. C'est `camille-po` qui constate le depassement au
planning et qui le trace ici et dans le planning du sprint concerne.

## File actuelle

**Compte actuel : 0 increment en attente.** Le seul increment qui a jamais figure dans cette file,
`bandit-manchot-additions-cp`, a recu le Done pedagogique (`VALIDE SOUS RESERVE`, ce qui est un
Done accorde et non une attente - voir la fiche, "Statut global" : "Done pedagogique du jeu
accorde le 2026-08-05 au titre de s01, maintenu et non retire") ; il n'y a donc plus lieu de le
compter comme en attente. Ses reserves non bloquantes (repartition des sommes, indice
inatteignable, et les reserves visuelles de s02) ne sont pas un motif de reinscription dans cette
file : elles sont suivies comme du backlog ordinaire dans `docs/backlog/backlog.md` (sprint 3,
`DEC-0007`) et comme condition ecrite dans `squad/programme/MATRICE-couverture.md`, pas comme un
Done pedagogique en souffrance.

Aucun increment n'est actuellement en attente d'un premier audit ou d'une reprise apres refus.
Bien sous le seuil de gel (2-3) : rien n'empeche d'ouvrir de nouvelles stories de contenu au
prochain planning, sous reserve de la decision de perimetre de `DEC-0007` (s03 complete la
competence deja engagee avant d'en ouvrir une nouvelle).

## Historique

| Increment | Fiche | Verdict final trace ici | Entre le | Sorti le |
|---|---|---|---|---|
| bandit-manchot-additions-cp | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` | VALIDE SOUS RESERVE, Done pedagogique ACCORDE (leve depuis REFUSE) | 2026-08-04 (sprint 0, verdict REFUSE) | 2026-08-05 (sprint 1, refus leve) |

Le chantier d'identite visuelle de s02 (S2-1, S2-2, S2-3) n'est pas entre dans cette file : il ne
constitue pas un increment de contenu nouveau, `DEC-0006` l'a deja pose, et son propre refus
initial (S2-1 sur le commit `3be5a86`) a ete leve dans le meme sprint (`fd16057`), avant meme
qu'une review n'ait a en tracer l'attente ici.
