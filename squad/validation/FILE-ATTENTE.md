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

**Compte actuel : 0 increment en attente.** Ce fichier portait encore, a l'ouverture du sprint 8,
le REFUSE du 2026-08-10 sur `bandit-manchot-conjugaison-present-cm1` sans la levee prononcee au
sprint 7 : correction faite ci-dessous.

`bandit-manchot-conjugaison-present-cm1` a ete refuse par `nadia-prof` le 2026-08-10 (premier
audit) sur un motif unique et suffisant (cinq indices sur douze enoncent une regle de conjugaison
fausse, trois dementies par un autre item du meme paquet)
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`). **Ce refus est leve au
sprint 7** : sur le commit `10dd74d` (`HEAD` de `round-two`), `nadia-prof` prononce **VALIDE SOUS
RESERVE**, Done pedagogique **accorde** (meme fiche, verdict "second audit, increment livre en
s07"). Il sort donc de cette file au titre du Done pedagogique. Le jeu reste retire du catalogue
jouable (`DEC-0014-retrait-le-bon-verbe-du-catalogue.md`) : sa remise au catalogue est une
decision distincte, tranchee par `DEC-0017`, qui ne porte pas sur cette file.

`bandit-manchot-le-bon-son-cp` a recu un Done pedagogique accorde (`VALIDE SOUS RESERVE`) le
2026-08-10 : le REFUSE du 2026-08-06 est leve sur ses cinq motifs
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`). Il sort donc de cette file. Ses
reserves restantes et la barre qu'elle a ecrite pour `couvert-valide` sont suivies comme backlog
ordinaire et comme note de `squad/programme/MATRICE-couverture.md`, pas comme un Done pedagogique
en souffrance.

`bandit-manchot-homophones-cm1` a recu un verdict **VALIDE** sans reserve bloquante le 2026-08-10,
sa condition de matrice etant elle-meme levee (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`) :
comme pour `bandit-manchot-additions-cp`, ce n'etait pas une attente et il n'est jamais entre dans
cette file au titre du Done pedagogique.

Bien sous le seuil de gel (2-3), et la file est vide : rien n'empeche d'ouvrir de nouvelles
stories de contenu au prochain planning, et aucune reprise de Done pedagogique n'est due en
priorite pour ce seul motif.

## Historique

| Increment | Fiche | Verdict final trace ici | Entre le | Sorti le |
|---|---|---|---|---|
| bandit-manchot-additions-cp | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` | VALIDE SOUS RESERVE, Done pedagogique ACCORDE (leve depuis REFUSE) | 2026-08-04 (sprint 0, verdict REFUSE) | 2026-08-05 (sprint 1, refus leve) |
| bandit-manchot-homophones-cm1 | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` | VALIDE SOUS RESERVE, Done pedagogique ACCORDE | 2026-08-06 (sprint 4, premier audit) | 2026-08-06 (accorde au premier audit) |
| bandit-manchot-le-bon-son-cp | `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md` | VALIDE SOUS RESERVE, Done pedagogique ACCORDE (leve depuis REFUSE) | 2026-08-06 (sprint 4, premier audit) | 2026-08-10 (sprint 6, refus leve apres recomposition) |
| bandit-manchot-conjugaison-present-cm1 | `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md` | VALIDE SOUS RESERVE, Done pedagogique ACCORDE (leve depuis REFUSE) | 2026-08-10 (sprint 6, premier audit) | 2026-08-10 (sprint 7, second audit, refus leve) |

**Correction de s08 - ce fichier etait perime depuis sa cloture de sprint 7.** Le sprint 7 a levé
le refus ci-dessus le 2026-08-10 (meme jour que l'ouverture du refus, sur un increment livre
ulterieurement dans le sprint) ; ce fichier n'a ete mis a jour qu'a l'ouverture du sprint 8, comme
constate au recensement de `camille-po`. Meme defaut que la correction de s03 plus haut : un
fichier qui n'est pas tenu a jour a chaque review n'est pas la memoire fiable qu'il pretend etre.

Le chantier d'identite visuelle de s02 (S2-1, S2-2, S2-3) n'est pas entre dans cette file : il ne
constitue pas un increment de contenu nouveau, `DEC-0006` l'a deja pose, et son propre refus
initial (S2-1 sur le commit `3be5a86`) a ete leve dans le meme sprint (`fd16057`), avant meme
qu'une review n'ait a en tracer l'attente ici.
