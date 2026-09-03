# DEC-0073 - `nombreDeManches: 7` est ratifie pour les trois paquets de questionner le monde CP, sur la regle de derogation de DEC-0064

- **Type** : parametre de produit, derogation ratifiee sous la regle de `DEC-0064`.
- **Date** : 2026-09-03
- **Auteur** : `camille-po`
- **Sprint** : s27
- **Competence programme visee** : **transverse - pas de contenu pedagogique.**
- **Statut** : Acceptee
- **Lien** : `DEC-0064` (regle de derogation), `DEC-0020` (precedent, complements a 10),
  `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md` (`VIV-R1`),
  `squad/conformite/FICHE-bandit-manchot-corps-sens-cp.md` (`COR-R3`), fiche de
  `bandit-manchot-etats-eau-cp` (meme reserve).

---

## 1. Le constat, route par `nadia-prof`

Les trois paquets de questionner le monde CP livres a s27 -
`bandit-manchot-vivant-non-vivant-cp`, `bandit-manchot-etats-eau-cp`, `bandit-manchot-corps-sens-cp`
- portent tous `nombreDeManches: 7` dans `catalogue.yaml`, **sans decision produit ecrite**. La regle
de `DEC-0064` (section 3) l'exige : *"Une entree de catalogue ne porte une autre valeur que 10 que
par une decision produit ecrite, qui nomme son motif."*

`nadia-prof` l'a signale elle-meme en rendant son Done pedagogique, avec son avis pedagogique donne
d'avance pour que la ratification n'ait pas besoin de lui etre redemandee (`VIV-R1`) :

> *"`nombreDeManches: 7` n'est couvert par aucune decision produit ecrite. Ce n'est pas un defaut
> pedagogique - interroge, je reponds que 7 manches pour 8 items est la bonne valeur, une partie de
> 10 en epuiserait le paquet et le ferait tourner en rond. La ratification est donc facile ; elle
> manque. Route a `camille-po`. Vaut identiquement pour les deux autres paquets de cette matiere."*

## 2. Verification, avant de ratifier

**Chaque paquet sert huit items**, verifie sur les trois fiches de conformite
(`FICHE-bandit-manchot-vivant-non-vivant-cp.md`, `FICHE-bandit-manchot-etats-eau-cp.md`,
`FICHE-bandit-manchot-corps-sens-cp.md`) et sur les fichiers de contenu
(`cp-qlm-vivant-non-vivant.yaml`, `cp-qlm-etats-eau.yaml`, `cp-qlm-corps-sens.yaml`).

**Le garde-fou de `DEC-0064` (section 2.c) tient** : `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`
exige strictement plus d'items que de manches. **8 items, 7 manches : la condition est respectee**,
avec la marge la plus etroite possible (un seul item de plus que de manches) - c'est exactement le
motif que `nadia-prof` donne : une partie de 10 manches sur 8 items ferait necessairement revoir un
item, ce que ce garde-fou n'interdit pas mais que le jugement pedagogique ecarte comme mediocre.

**Le precedent est conforme** : `DEC-0020` avait deja abaisse la valeur a `7` pour
`bandit-manchot-complements-10-cp` (onze items), sur le meme motif - une partie de 10 manches
epuiserait ou repeterait presque tout le paquet.

## 3. Ce que je ratifie

> **`nombreDeManches: 7` est ratifie pour `bandit-manchot-vivant-non-vivant-cp`,
> `bandit-manchot-etats-eau-cp` et `bandit-manchot-corps-sens-cp`.** Motif : chacun sert huit items
> ; une partie de dix manches en epuiserait la quasi-totalite et ferait tourner le paquet en rond,
> ce que `nadia-prof` juge inferieur a une partie de sept manches sur huit items. Ratification
> retroactive, au meme titre que `DEC-0064` l'a fait pour `saisie-complements-10-cp`.

**Ce que cette ratification n'est pas** : un jugement sur la longueur ideale d'une partie de
questionner le monde en general. Elle porte sur ces trois paquets precis, a huit items chacun ;
un paquet futur de cette matiere a un nombre d'items different devra a nouveau motiver sa valeur
s'il s'ecarte de `10`, selon la meme regle.

## 4. Ce que cette decision ne fait pas

- Elle ne demande a personne de changer une valeur : `catalogue.yaml` porte deja `7` sur les trois
  entrees, et rien n'y bouge.
- Elle ne prononce aucun Done pedagogique : les trois verdicts (`VALIDE`, `VALIDE`, `VALIDE SOUS
  RESERVE`) sont deja rendus par `nadia-prof`, independamment de ce point de gouvernance.
- Elle ne fait bouger aucune ligne de matrice : les mouvements de statut de ces trois lignes sont
  traces separement, sur le fond pedagogique, dans `squad/programme/MATRICE-couverture.md`.
- Elle ne cree aucune story : il n'y a rien a developper.
- Elle ne tranche rien de technique : le garde-fou de la section 2 existe deja et n'est pas de ma
  main.
