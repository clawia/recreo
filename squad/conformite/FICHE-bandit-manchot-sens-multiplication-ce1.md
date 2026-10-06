# Fiche de conformité - bandit-manchot-sens-multiplication-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, avec `illustration` et `illustrationTexteAlternatif` sur les
  items à disposition dessinée (même forme que `ce1-solides`, `ADR-0073`). Story `S51-MAT-MULTIPLICATION`
  (`docs/backlog/backlog.md`), décision `DEC-0110` (sections 1 et 5) et `DEC-0111` (section 5). Développeur
  de paquet et de dessin : `lea-dev` (planning de s52) ; contraintes de dessin des réseaux de points :
  `squad/design/` (`ines-design`, section 3, commit `7350fb07`). Nom de fichier de contenu et identifiant
  d'entrée de catalogue fixés par le développeur au premier commit ; le nom de cette fiche est proposé par
  `camille-po` et renommé si l'identifiant diffère. Distinct de `bandit-manchot-tables-multiplication-ce1`
  (`MAT-CE1-07`).
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Nombres et calculs | Comprendre le sens de la multiplication (addition réitérée, disposition en lignes et en colonnes) | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-06` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 6,
verdict **accepté sous conditions `MUL-CE1-1` à `MUL-CE1-7`** ; `couvert-valide` atteignable avec les
dessins de `MUL-CE1-5`, `en cours` sinon.

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question` ; aucune mécanique ni forme d'item neuve.
- [ ] Les deux facteurs sont des entiers de 2 à 10 de produit au plus 100 (`MUL-CE1-1`).
- [ ] Aucun énoncé n'est un produit nu `a x b = ?` : chaque item montre l'addition réitérée écrite ou une
  disposition dessinée (`MUL-CE1-2`) ; aucun énoncé de la forme `3 x ? = 12`, ni partage ou groupement
  posé en mots, ni *"Combien de fois 3 dans 12 ?"* ; aucun item n'est un rappel de table
  (`MAT-CE1-07`) sous un autre habillage.
- [ ] Au moins trois items vont de la somme répétée vers le produit et au moins trois du produit vers la
  somme répétée (`MUL-CE1-3`).
- [ ] Au moins deux items portent sur une même disposition lue dans les deux sens et disent que les deux
  comptes donnent le même total (`MUL-CE1-4`).
- [ ] Un dessin de disposition : ronds pleins ou points identiques, alignés en réseau régulier de au plus dix
  rangées et dix colonnes, distinguables à la taille de rendu ; l'énoncé ne donne ni le nombre de rangées ni
  celui de colonnes (`MUL-CE1-5`).
- [ ] Deux distracteurs par item, chacun d'une forme admise (`a + b`, `a x (b - 1)`, `a x (b + 1)`,
  `(a + 1) x b`, `a x (b + 2)` si `a = b`), positifs, distincts entre eux et de la bonne réponse, famille
  déclarée à côté de l'item ; `a + b` interdit quand `a = b = 2` ; la bonne réponse occupe chacun des trois
  rangs sur le paquet (`MUL-CE1-6`).
- [ ] Au moins dix items servent la ligne ; le paquet porte strictement plus d'items que de manches
  (`FER-2`, `FER-3`) ; chaque énoncé respecte `LON-CE1-1` et `LON-CE1-2`.
- [ ] `LIllustrationDeContenuResoutEtAucuneNeDortTest` vert avec le paquet.
- [ ] Mention de couverture (`MUL-CE1-7`) : si aucune disposition n'est dessinée, la matrice porte
  *"addition reiteree seule ; la disposition en lignes et en colonnes n'est servie par aucun jeu et ne peut
  pas l'etre par du texte"* (graphie de la source) et la ligne reste `en cours` ; si les dispositions sont dessinées et que
  `MUL-CE1-4` et `MUL-CE1-5` sont tenues, aucune mention n'est due.
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.
