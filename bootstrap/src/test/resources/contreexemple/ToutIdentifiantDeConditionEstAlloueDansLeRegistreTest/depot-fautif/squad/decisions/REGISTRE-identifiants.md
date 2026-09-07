# Registre des identifiants de condition - depot fautif

Ce registre est le jeu fautif d'ADR-0085. Il porte les quatre defauts a la fois, chacun ecrit pour
faire echouer une clause et une seule.

## 3. Les homonymes ecartes

`ZZZ-8` est ecarte ici alors que plus aucun fichier de ce depot ne l'ecrit : c'est la clause 4.

| Forme | Ce que c'est |
|---|---|
| `ZZZ-8` | un homonyme qui ne designe plus rien de reel |

## 4. Les identifiants alloues

`ZZZ-2` est alloue deux fois - clause 1. `ZZZ-3` est alloue dans un document qui ne l'ecrit pas -
clause 3. `ZZZ-9` est ecrit par le document et n'est alloue nulle part - clause 2.

| Identifiant | Document ou il est ecrit |
|---|---|
| `ZZZ-1` | `squad/conformite/ARBITRAGE-fautif.md` |
| `ZZZ-2` | `squad/conformite/ARBITRAGE-fautif.md` |
| `ZZZ-2` | `squad/conformite/FICHE-fautive.md` |
| `ZZZ-3` | `squad/conformite/FICHE-fautive.md` |
