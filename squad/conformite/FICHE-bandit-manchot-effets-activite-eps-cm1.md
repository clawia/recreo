# Fiche de conformite - bandit-manchot-effets-activite-eps-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-eps-effets-activite.yaml`
  (13 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-effets-activite-eps-cm1`, titre servi *"Les effets de
  l'activite physique"*, consigne servie *"Lis bien, puis dis la cause commune, l'effort qui se
  distingue, ou la conduite de securite."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EPS-CM1-10`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `e11a9325`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof`
  le remplisse. Premier paquet d'education physique et sportive du produit sur le niveau CM1.
- **Story** : `S43-EPS-EFFETS-ACTIVITE` - Connaitre les effets de l'activite physique sur le corps
  et les regles de securite qui l'entourent (`docs/backlog/backlog.md`).
- **Competence programme visee** : education physique et sportive, CM1 (cycle 3), domaine
  "Apprendre a entretenir sa sante" -
  `squad/programme/education-physique-et-sportive/competences-cm1.md`, `EPS-CM1-10`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1941) au moment d'ouverture
  de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router pour les conditions generales de la matiere**
  (`squad/programme/education-physique-et-sportive/competences-cm1.md`, Jugement de faisabilite,
  `nadia-prof`, 2026-09-03, ligne 10) : *"10 | Effets de l'activite physique et regles de securite
  | non couvert - jouable au choix textuel, sous `EPS-2` et `BIA-4` | 'Pourquoi s'echauffe-t-on
  avant de courir ?' est une connaissance exacte, utile, du niveau du CM1. Frontiere a tenir avec
  les sciences"*, avec les conditions valables sur toute la matiere (`EPS-1`, `EPS-2`, `EPS-3`,
  `BIA-4`) et la frontiere de recouvrement avec `SCI-CM1-09` deja servi, toutes citees mot pour
  mot dans la story.
- **Arbitrage prealable neuf, ajoute le 2026-09-25 par `nadia-prof` avant l'ecriture du paquet,
  discriminant CM1/CP** : `EPS-9`, cinq clauses (trois taches absentes du CP portant ensemble au
  moins deux tiers des items et chacune au moins trois items ; interdiction de repeter les taches
  deja servies au CP ; `CYC-1` dans les deux sens entre les bonnes reponses du CP et les
  distracteurs du CM1), cite en entier dans la story. Ce bloc pose une barre avant le premier item
  et ne prononce aucun Done pedagogique.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-EPS-EFFETS-ACTIVITE`)

- Etant donne un item, quand il s'affiche, alors il porte sur un effet de l'activite physique sur
  le corps (echauffement, essoufflement, recuperation, hydratation pendant l'effort) ou sur une
  regle de securite qui entoure la pratique, jamais sur le sommeil, les repas ou l'hygiene du
  corps hors effort (frontiere avec les sciences, section "Recouvrement a ne pas
  double-compter").
- Etant donne un item, quand on verifie sa bonne reponse, alors aucune performance chiffree n'y
  est presentee comme une norme et aucun item ne compare les corps, les performances ou les
  capacites de deux enfants (`EPS-2`).
- Etant donne un item, quand on relit sa bonne reponse, alors elle ne fait porter aucune attitude
  ni conduite morale (jamais une reponse qui juge l'enfant "sage" ou "prudent") (`EPS-3`).
- Etant donne un item, quand on relit son enonce, alors il n'interroge jamais l'enfant sur sa
  propre pratique physique ni sur son propre corps (`BIA-4`).
- Etant donne un item qui s'appuie sur une regle (par exemple une regle de securite liee a
  l'effort), quand on verifie l'enonce, alors la regle est rappelee dans l'enonce ou
  universellement scolaire, jamais supposee connue sans etre donnee (`EPS-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs effets de
  l'activite physique et plusieurs regles de securite du libelle sont chacun en bonne reponse au
  moins une fois.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EPS-CM1-10` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  qu'aucun item ne derive vers le sommeil, les repas ou l'hygiene du corps, et que la matrice
  porte la mention exigee d'avance par le referentiel : *"connaissances qui entourent la pratique
  physique seules ; aucune activite physique, aucun geste et aucune performance ne sont servis
  par ce produit"*.
- **`EPS-9` (ajout du 2026-09-25), opposable au meme titre que les criteres ci-dessus** : les
  trois taches du paquet (relier deux effets a une cause unique ; distinguer les effets selon le
  type d'effort ; reconnaitre un signal d'alerte du corps et la conduite qui y repond) portent
  ensemble au moins deux tiers des items et aucune n'en porte moins de trois ; aucun item ne
  reprend une tache deja servie au CP (clause 2) ; aucune bonne reponse d'un item du CP ne devient
  un distracteur d'un item du CM1, et reciproquement (clause 3, `CYC-1`).

## Ce que cette story ne fait pas

Elle ne fait jamais executer, mesurer ni observer un geste physique de l'enfant - nature
irreversible du reste de la matiere, hors de portee du produit ; elle ne derive jamais vers le
sommeil, les repas ou l'hygiene du corps - competence deja portee par sciences et technologie
CM1 (`SCI-CM1-10`), hors perimetre de cette story ; elle ne fixe aucun contenu factuel precis
(quels effets, quelles regles retenues) - reste a l'auteur du paquet puis a l'audit de
`nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
