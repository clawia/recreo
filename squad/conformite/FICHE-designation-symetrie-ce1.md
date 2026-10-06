# Fiche de conformité - designation-symetrie-ce1

- **Mécanique** : désignation (`designation`, forme d'item `ZoneADesigner`) ; aucune mécanique ni forme
  d'item neuve.
- **Contenu(s)** : à créer, avec `illustration` et `illustrationTexteAlternatif` non vide sur chaque item.
  Story `S51-MAT-SYMETRIE` (`docs/backlog/backlog.md`), décisions `DEC-0110` (sections 1 et 5) et `DEC-0111`
  (section 5). Développeur de paquet et de dessin : `lea-dev` (planning de s52). Nom de fichier de contenu et
  identifiant d'entrée de catalogue fixés par le développeur au premier commit ; le nom de cette fiche est
  proposé par `camille-po` et renommé si l'identifiant diffère.
- **Statut global** : en construction - **engagée à s52** : `MAT-CE1-17` est re-engagée à 1,5 par
  l'amendement du 2026-10-06 (U4, sections A.5 et A.6) de `DEC-0111`, les trois conditions de la section 5.2
  étant remplies. À l'ouverture de cette fiche elle était non engagée (budget libéré par le planning de
  `squad/sprints/s52-deux-instances-ce1-francais-maths/planning.md`, destination s53, annulée).
  Aucun paquet n'existe encore ; bloc de validation vide.
- **Dernière mise à jour** : 2026-10-06 (re-engagement, `camille-po`, s52, U4).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Espace et géométrie | Reconnaître un axe de symétrie et compléter une figure par symétrie sur quadrillage | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-17` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 3,
verdict **accepté sous conditions `SYM-CE1-1` à `SYM-CE1-3`** ; `couvert-valide` atteignable avec une
mention. Bornes de grille `DEP-CE1-2` (même fichier, section 1).

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `designation`, forme d'item `ZoneADesigner` ; `nombreDeManches` : 8 (`DEC-0111` section
  5.4).
- [ ] Item de reconnaissance d'axe : l'énoncé ne décrit pas la propriété (*"qui partage la figure en deux
  parties superposables"*) ; la réponse est entièrement dans le dessin (`SYM-CE1-1`).
- [ ] Au moins la moitié des items de reconnaissance d'axe portent une ligne candidate qui coupe la figure en
  deux parties de même aire sans être un axe (`SYM-CE1-2`).
- [ ] Item de complétion : axe tracé sur un quadrillage de 4 x 4 à 6 x 6 cases de même taille, toutes
  désignables, exactement une case manquante de l'autre côté de l'axe, et la case attendue n'est pas la
  seule case vide touchant la figure (`SYM-CE1-3`, `DEP-CE1-2`).
- [ ] *Reconnaître un axe* et *compléter par symétrie* portent chacun au moins trois items ; au moins dix
  items servent la ligne ; le paquet porte strictement plus d'items que de manches (`FER-2`, `FER-3`,
  `DEC-0064`).
- [ ] Chaque item porte au moins trois zones (`ZON-8`) ; deux zones d'un même item ne se chevauchent pas
  (`ZON-13`) ; libellés de zone de la même forme, aucun ne nomme la cible (`ZON-10`) ; l'énoncé demande une
  seule désignation (`ZON-14`).
- [ ] La bonne zone n'est ni toujours la première ni toujours la dernière déclarée ; ni l'énoncé ni
  l'indice ne nomment le libellé de la bonne zone.
- [ ] Zones de désignation rendues à 44 px au moins à 296 px de large (320 px de fenêtre) : c'est la barre de
  refus de `nadia-prof` ; 16 unités sur un carré de 100 est la marge de dessin d'`ines-design` et
  d'`ADR-0080` A2bis (`DEC-0111`, amendement A.7).
- [ ] Mention de couverture reprise mot pour mot dans la matrice : *"reconnaitre un axe de symetrie, et
  completer une figure par symetrie a raison d'une case par item ; completer une figure a plusieurs cases
  reste non couvert, aucune mecanique ne recoit une suite de gestes dans une meme manche"* (graphie de la
  source, `docs/backlog/backlog.md`).
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.
