# Fiche de conformite - bandit-manchot-hygiene-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-hygiene.yaml`
  (15 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-hygiene-cp`, titre servi *"Les regles d'hygiene de
  vie"*, consigne servie *"Lis bien, puis dis quelle regle suivre, ou pourquoi elle est bonne pour
  la sante."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-04`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-QLM-HYGIENE` - Connaitre quelques regles d'hygiene de vie et leur effet sur la
  sante (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-04`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette
  fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, ligne 370) : *"moyenne, sous `BIA-4`. Terrain sur si aucun item n'interroge l'enfant
  sur sa propre vie."* Precise par `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`,
  ligne 368 : *"Piege de biais pose d'avance : 'que manges-tu au petit dejeuner ?' presuppose
  qu'il y en a un. `BIA-4`."*
- **Adjacence de contenu signalee par le paquet lui-meme** (avis de faisabilite de `theo-archi`,
  s41, section 6.4) : `cp-eps-corps-effort.yaml` (`EPS-CP-10`, `EPS-CP-11`) sert deja l'eau et le
  repos, borne au seul contexte de l'effort physique. Aucun item de ce paquet ne rapporte le
  sommeil, l'alimentation ou l'hygiene du corps a un effort physique.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-QLM-HYGIENE`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les trois domaines du
  libelle (sommeil, alimentation, hygiene du corps) sont chacun en bonne reponse au moins une
  fois.
- Etant donne un item, quand la question s'affiche, alors elle porte sur une regle generale et son
  effet sur la sante (par exemple "pourquoi se laver les mains avant de manger ?"), jamais sur ce
  que l'enfant fait, mange ou vit lui-meme (`BIA-4`) - aucune formulation du type "que manges-tu
  au petit dejeuner ?" qui presuppose une reponse personnelle.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres comportements reels lies a
  l'hygiene de vie, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-04` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item n'interroge l'enfant sur sa propre vie et que les trois domaines du
  libelle sont chacun servis.

## Ce que cette story ne fait pas

Elle n'interroge jamais l'enfant sur sa propre vie, ses propres habitudes ou son propre foyer
(`BIA-4`) ; elle ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit
de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
