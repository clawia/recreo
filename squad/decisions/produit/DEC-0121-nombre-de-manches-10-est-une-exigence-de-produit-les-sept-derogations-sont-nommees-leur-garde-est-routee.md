# DEC-0121 - `nombreDeManches: 10` est une exigence de produit, les sept derogations du catalogue sont nommees, deux derogations orphelines sont ratifiees, et leur garde est routee

- **Type** : parametre de produit, suite d'une question de `yanis-qa` (`F4`, `Q4`)
- **Date** : 2026-10-15
- **Auteur** : camille-po
- **Sprint** : s57 (palier 1)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.** Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.
- **Statut** : Acceptee
- **Lien** : `DEC-0064` (decision reconduite), `DEC-0020`, `DEC-0088`, `DEC-0109`, `DEC-0111` ; `ADR-0012`, `ADR-0023` ; `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/releve-yanis-palier-1.md` (`F4`, mutation `C8`, `Q4`) ; story `S57-GARDE-NOMBRE-DE-MANCHES` (`docs/backlog/backlog.md`).

## 1. La question

`yanis-qa` a remplace `nombreDeManches: 10` par `5` sur l'entree de `ce1-phrases-types` : 1401 tests d'`adapter-content`, 0 echec (mutation `C8`). Il demande si 10 est une exigence a garder par un test.

## 2. Reponse : oui, et la decision existe deja

`DEC-0064` (2026-08-31) dit : *"`nombreDeManches: 10` est le defaut de produit d'une partie. Toute entree de catalogue le porte, sauf derogation ecrite."* et *"une entree de catalogue ne porte une autre valeur que `10` que par une decision produit ecrite, qui nomme son motif."* Cette decision n'a jamais ete retiree ni remplacee. **Je la reconduis, sans la modifier.**

Motifs, ceux de `DEC-0064` section 2, relus sur l'etat du 2026-10-15 :

- **Exposition** : 142 des 149 entrees de `catalogue.yaml` portent 10 (`grep -c "nombreDeManches: 10"`, `grep -c "^  - id:"`, arbre `13affd6f`). Chacune est un jeu que `nadia-prof` a joue avec cette longueur.
- **Cout du changement** : depuis `DEC-0060` (`Q1`), un catalogue qui change l'ecran d'un jeu le fait rejouer. Une valeur fausse sur une seule entree est donc soit un jeu non rejoue, soit un rejeu non prevu.
- **Ce qui manquait** : `DEC-0064` section 2.c ne gardait que le plancher (`ADR-0023`) et section 3 posait la regle de derogation sans la garder. Le releve de `yanis-qa` le montre : une version fausse de la valeur reste verte.

## 3. Les sept derogations du catalogue a l'arbre `13affd6f`

| Entree | Valeur | Items du paquet | Decision qui la porte |
|---|---|---|---|
| `bandit-manchot-complements-10-cp` | 7 | 11 | `DEC-0020` |
| `saisie-complements-10-cp` | 7 | 11 | `DEC-0064` section 3 (ratification retroactive) |
| `bandit-manchot-histoire-geographie-lire-un-document-cm1` | 7 | 10 | `DEC-0088` |
| `bandit-manchot-decrire-une-oeuvre-cp` | 7 | 8 | `DEC-0109` (*"`nombreDeManches` reste a 7"*, huit items) |
| `designation-symetrie-ce1` | 8 | au moins 10 | `DEC-0111` section 5.4, point 1 |
| `designation-plan-de-la-classe` | 5 | 6 | **aucune retrouvee** : ratifiee ci-dessous |
| `saisie-mot-dicte-cp` | 7 | 12 | **aucune retrouvee** : ratifiee ci-dessous |

**Methode** : `awk` sur `catalogue.yaml` pour les entrees dont la valeur n'est pas 10, puis `grep` du nom de l'entree et de la valeur dans `squad/decisions/produit/` et `squad/conformite/`. Les nombres d'items sont ceux de `grep -c "enonce:"` sur chaque paquet (12 pour `cp-mot-dicte`, 6 pour `cp-plan-de-la-classe`, 8 pour `cp-decrire-une-oeuvre`) ; ceux des complements et de `lire-un-document` sont ceux de leurs decisions. **Une absence de resultat de `grep` n'est pas la preuve qu'aucune decision n'existe** : si l'une des deux est retrouvee, elle prevaut et cette ratification devient un renvoi.

## 4. Deux derogations orphelines, ratifiees sur le patron de `DEC-0064` section 3

- **`designation-plan-de-la-classe` : 5.** Le paquet compte 6 items. `ADR-0023` exige strictement plus d'items que de manches : 5 est le maximum permis. Meme motif arithmetique que `DEC-0020`. La fiche `FICHE-designation-plan-de-la-classe.md` coche la ligne *"Six items, `nombreDeManches: 5`, plus d'items que de manches"*.
- **`saisie-mot-dicte-cp` : 7.** Le paquet compte 12 items : 10 serait permis par `ADR-0023`, **7 n'est donc pas force par l'arithmetique**. Je n'ai retrouve aucun motif ecrit. Je la ratifie sur les deux motifs de `DEC-0064` section 2 (a et b) et non sur un motif pedagogique : `nadia-prof` a joue 7 manches jusqu'a l'ecran de fin (`FICHE-saisie-mot-dicte-cp.md`, ligne *"Le paquet est-il toujours entier ?"*, **TENU**), et passer a 10 ferait rejouer le jeu. **Ce n'est pas un verdict sur la bonne longueur d'une partie de saisie dictee** : cette question reste a `nadia-prof` (`DEC-0064` section 4). Si elle juge que 7 gene un enfant, sa parole ouvre une derogation ecrite par la regle de la section 5 ci-dessous.

## 5. La regle, reconduite et precisee

> Une entree de catalogue ne porte une autre valeur que `10` que si elle figure, avec sa valeur et la `DEC` qui nomme son motif, dans la liste de la section 3. Ajouter, changer ou retirer une ligne de cette liste est une decision produit ecrite.

## 6. Ce qui est route, dans le meme geste

Cette decision implique un changement de code (le garde de la liste). **Proprietaire : `sofia-sm`, par la story `S57-GARDE-NOMBRE-DE-MANCHES`** (`docs/backlog/backlog.md`, meme commit que cette decision). Sans cette story, la decision serait ecrite et la valeur resterait non gardee. Priorite faible, non engagee ; developpeur non designe.

## 7. Ce que cette decision ne fait pas

- Elle ne change aucune valeur du catalogue et ne touche aucun fichier de `adapters/`.
- Elle ne tranche pas la forme du garde : place, nom du test, lecture du catalogue sont a `theo-archi` et au developpeur.
- Elle ne prononce aucun Done pedagogique et ne juge la longueur d'une partie pour aucun jeu.
- Elle ne cree aucun report : premiere inscription du sujet.
