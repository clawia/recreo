# Planning - s35 - fermer ce qui n'a pas recu le go pedagogique

- **Horodatage de debut** : 2026-09-11 14:20 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : repris tel quel de `s35-theo` (`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/`,
  choisi par `theo-archi` a l'ecriture de son avis) - il decrit exactement l'objectif gele
  ci-dessous, aucun renommage n'est justifie.
- **Prealable, deja fait avant l'ouverture de ce planning** : les six mouvements de matrice vers
  `couvert-valide` (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11`)
  sont integres depuis le commit `60e1c836` sur `round-two`, confirmes par `camille-po`
  (`DEC-0087`).

## 1. Lignee et fusion des deux branches de preparation

Verifie avec `outils/verifier-lignee.sh` avant toute ecriture, sur chacun des trois worktrees :

- `s35-camille` (`C:\perso\recreo-s35-camille`, commit `eaa0552e`) : descend de `round-two`.
- `s35-theo` (`C:\perso\recreo-s35-theo`, commit `c64d8b57`) : descend de `round-two`.
- `s35-sofia` (`C:\perso\recreo-s35-sofia`, ouvert pour ce planning depuis `round-two` a
  `60e1c836`) : descend de `round-two`.

Les deux branches ne touchent aucun fichier en commun (`s35-camille` : `docs/backlog/backlog.md`,
`DEC-0088`, `DEC-index.md` ; `s35-theo` : `ADR-0091`, `ADR-0092`, `ADR-index.md`,
`avis-faisabilite-theo.md`) : fusionnees sans conflit dans le premier commit de s35, sur
`s35-sofia` :

- `66747dc7` - fusion de `s35-camille` (six stories de cloture, `DEC-0088`).
- `d12b4369` - fusion de `s35-theo` (avis de faisabilite, `ADR-0091`, `ADR-0092`).

## 2. Objectif gele

**Repris de `docs/backlog/backlog.md`, section "Backlog ouvert pour s35" (`camille-po`), qui
l'ecrit elle-meme comme arbitre par l'utilisatrice** : s35 n'ouvre **aucune ligne neuve** de
`squad/programme/MATRICE-couverture.md`. Les six stories retenues cloturent des refus
pedagogiques bloquants ou des reserves et routages non soldes a la cloture de s34. Aucune ne porte
une competence neuve.

Ce gel est coherent avec `DEC-0087` section 5 : la fenetre du Plan 2 (`DEC-0083`) s'est refermee a
s34 sans qu'une strategie neuve de couverture ne soit tranchee au-dela ; `DEC-0087` prevoyait
qu'a defaut de reponse avant la cloture de ce planning, s35 se limiterait aux stories de reprise
routees en section 6 de ce meme document - c'est exactement le perimetre que l'objectif fixe par
l'utilisatrice recouvre.

## 3. Les six stories, competence visee et affectation nominative

| Story | Competence visee | Ce qu'elle ferme | Verdict de `theo-archi` |
|---|---|---|---|
| `S35-SCO1-ECRAN-FIN` | Transverse, aucune ligne de matrice | `SCO-R1` (refus de `S34-SCO1-REMEDE`) | Faisable en l'etat, aucun `ADR` |
| `S35-EXC-INDICES` | EMC CP, `EMC-CP-03` (deja `couvert-valide`) | `EXC-R10` (refus de `S34-EXC-TACHEB`), `EXC-R11`, `EXC-R12` | Faisable en l'etat, contenu seul |
| `S35-QLM14-FRISE` | Questionner le monde CP, `QLM-CP-14` (`non couvert`, refusee) | `FRI-R1`, `FRI-R2` (refus de `QLM-CP-14`) | Faisable moyennant `ADR-0091` (`Propose`) |
| `S35-QLM11-PLANCLASSE` | Questionner le monde CP, `QLM-CP-11` (`non couvert`, jamais livree) | `ZON-15`, `ZON-16` | Faisable avec reserve, `ADR-0092` (`Propose`) |
| `S35-ANG-R3` | Anglais CM1, `LVE-CM1-07` (deja `couvert-valide`) | Reserve restante de `S34-ANG-CORR` (`ANG-R3`, `ANG-R7`) | Faisable en l'etat, aucune regle neuve |
| `S35-CONS-4` | Transverse, aucune ligne de matrice | Scission de `bandit-manchot-histoire-geographie-cm1` en quatre entrees, non engagee depuis s33 | Infaisable sans `DEC-0088` - levee, engageable |

### Affectations nominatives

Camille nomme, story par story, un agent pressenti (`docs/backlog/backlog.md`) ; ou plusieurs
noms sont proposes en alternative (`marc-dev` ou `lea-dev`), Sofia tranche ici, nominativement,
pour que `PAR-16` soit verifiable :

- **`S35-SCO1-ECRAN-FIN`** : `sofia-sm` (casquette dev) pour l'implementation (le fragment
  `retour.html` et le garde-fou `LEcranDeFinDePartiePorteLesDeuxNombresTest` dont `theo-archi`
  donne deja la forme, section 1.4 de son avis) ; `nadia-prof` pour le Done pedagogique du texte
  (texte fourni par elle, pas par le developpeur, section 1.3).
- **`S35-EXC-INDICES`** : `sofia-sm` (casquette dev) pour la reecriture des cinq `indice` et des
  trois `bonneReponse`/`distracteurs` de `cp-emc-exclusion.yaml`, sous la contrainte de ligature
  Latin-1 nommee par `theo-archi` (section 2.3) ; `nadia-prof` pour l'audit.
- **`S35-QLM14-FRISE`** : `lea-dev` (auteure du paquet `cp-frise-de-la-journee`) pour les deux
  textes alternatifs, l'indice de l'item 1 et les deux garde-fous de contenu d'`ADR-0091` ;
  `ines-design` pour le pictogramme du lever (`FRI-R2`) ; `nadia-prof` pour l'audit.
- **`S35-QLM11-PLANCLASSE`** : `ines-design` pour la geometrie des deux cadres (armoire, porte) ;
  `lea-dev` pour le cote code du lot - les deux mecanismes d'`ADR-0092` (le garde-fou de
  `bootstrap` derive de la derniere clause d'`Accepte` d'`ADR-0080`, deja due depuis s34, et le
  cliquet a egalite sur le nombre de paquets de designation servis) ; `nadia-prof` pour les six
  libelles (`ZON-16`) et pour l'audit, y compris le plancher de lisibilite (`ZON-15`) qui ne se
  ferme qu'a l'oeil, sur l'ecran servi.
- **`S35-ANG-R3`** : `sofia-sm` (casquette dev) pour l'item de la statue de la Liberte et
  l'explication du mot "imperiale" ; `nadia-prof` pour le quatrieme passage sur cet item.
- **`S35-CONS-4`** : `marc-dev` pour la scission en quatre paquets `yaml`, les quatre entrees de
  catalogue et les cinq gestes mecaniques nommes par `theo-archi` (section 6.3 de son avis :
  partage de `HG-CM1-07`, retrait de `ligneDeReferentiel` sur "Lire un document", descripteurs de
  lot inchanges, recompte du motif d'exemption de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
  scission de `PaquetCm1HistoireGeographieTest`) ; `nadia-prof` pour l'audit texte devant items sur
  les quatre paquets.

**Repartition de charge, dite explicitement** : `marc-dev` porte seul la story la plus lourde
techniquement (`theo-archi` la nomme "le gros du travail technique de cette story" a propos du
seul decoupage du test de paquet) ; `lea-dev` porte les deux stories de la famille designation
(`QLM-CP-14`, `QLM-CP-11`), en continuite avec son role d'auteure du paquet frise ; `sofia-sm`
porte les trois stories les plus legeres (texte de gabarit, contenu seul, deux items). Aucune
story ne reste "non engage, budget libere" : les six sont couvertes.

`theo-archi` a deja rendu ce qu'il devait pour ce planning (avis de faisabilite ecrit sur les six
stories, deux `ADR` ouverts) ; il reste du pour la porte technique : confirmer `ADR-0091` et
`ADR-0092` `Accepte` quand leurs regles sont dans l'arbre (section 8 de son avis).

## 4. Avis de faisabilite de `theo-archi`

Integral dans
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/avis-faisabilite-theo.md`
(fusionne depuis `s35-theo`, commit `c64d8b57`). Deux points a retenir au planning, au-dela de la
table de la section 3 :

- **Prealable leve** : le blocage arithmetique de `S35-CONS-4` (`DEC-0064` contre `ADR-0023` sur
  l'entree "Lire un document", 10 items) est leve par `DEC-0088` (`nombreDeManches: 7`, marge de
  2). Sans cette derogation, la story produisait un rouge garanti a l'execution ; elle est
  desormais engageable.
- **Charge non chiffree ailleurs** : le lot de `S35-QLM11-PLANCLASSE` porte, en plus de ses deux
  criteres d'acceptation, la derniere clause d'`Accepte` d'`ADR-0080` - due depuis s34, glissee une
  fois deja. Elle est explicitement chiffree dans l'affectation de `lea-dev` ci-dessus pour ne pas
  glisser une seconde fois.

## 5. Obstacle transverse connu, non absorbe par une story

**Le recouvrement des boutons de zone sur le dessin qu'ils designent** (une zone de designation
est un `<button>` pose sur la boite de l'image, fond translucide, libelle a l'interieur -
`ADR-0080` decision 4). Route a `ines-design` et `theo-archi` a la cloture de s34, il reste
transverse a tout paquet de designation present et futur et n'est ferme par aucune des deux
stories `QLM-CP-14` / `QLM-CP-11` prises separement.

`ADR-0092` (fusionne, `Propose`) le borne sans le trancher :

- la propriete reste tenue **a l'oeil** par `nadia-prof`, paquet par paquet - `ADR-0025` a deja
  decide qu'une mesure de rendu qui depend d'un navigateur n'est pas un garde-fou de CI ;
  aucune arithmetique ne peut la remplacer, la largeur d'un libelle a `1,25rem` dependant de la
  police de l'appareil (aucun `font-family` ni `@font-face` servi, pile systeme
  `--bs-font-sans-serif`) ;
- l'alternative de forme qui supprimerait vraiment le recouvrement - `(d)` dans les "Alternatives
  considerees" d'`ADR-0092`, **accepter qu'un libelle deborde de son cadre** (`min-width`/
  `min-height` plutot que `width`/`height` fixes) - est ecrite avec son cout : elle contredirait la
  seconde moitie de `ZON-15` ("sans deborder ni toucher une zone voisine"), donc elle demanderait a
  `nadia-prof` de renverser sa propre condition. `ADR-0092` ne la retient pas, il la rend visible.

**A retenir explicitement pour ce sprint** : le choix de forme - ou vit le libelle d'une zone, ce
qu'un bouton montre du dessin qu'il designe - reste a `ines-design` (qui propose) et `nadia-prof`
(qui tranche), pendant l'execution du sprint, sur les deux paquets de designation concernes. Ce
n'est pas une story a part : c'est un risque connu qui pese sur l'audit de `S35-QLM14-FRISE` et de
`S35-QLM11-PLANCLASSE`.

## 6. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
pour s35** : `DEC-0086` portait la composition de s34, et `DEC-0087` (section 5) referme la
fenetre du Plan 2 sans qu'une strategie neuve de composition ne soit tranchee au-dela - la
question est explicitement routee a l'utilisatrice, sans reponse en section 5, sans decision de
`camille-po` a sa place. Il n'y a donc, a l'ouverture de s35, aucun poste finance par une table de
composition contre lequel verifier une story.

**Ce que `PAR-16` verifie a la place, sur ce sprint** : chaque story retenue porte-t-elle une
affectation nominative ? Verifie sur les six :

| Story | Affectation nominative | Issue |
|---|---|---|
| `S35-SCO1-ECRAN-FIN` | `sofia-sm` (dev), `nadia-prof` | Engagee |
| `S35-EXC-INDICES` | `sofia-sm` (dev), `nadia-prof` | Engagee |
| `S35-QLM14-FRISE` | `lea-dev`, `ines-design`, `nadia-prof` | Engagee |
| `S35-QLM11-PLANCLASSE` | `lea-dev`, `ines-design`, `nadia-prof` | Engagee |
| `S35-ANG-R3` | `sofia-sm` (dev), `nadia-prof` | Engagee |
| `S35-CONS-4` | `marc-dev`, `nadia-prof` | Engagee (prealable `DEC-0088` leve) |

**Aucune des six stories n'est "non engage, budget libere"** : les six portent une affectation
nominative complete a l'ouverture de ce planning.

## 7. Ce que ce planning ne fait pas

- Il ne tranche aucune question pedagogique : les criteres d'acceptation restent ceux de
  `camille-po`, les jugements de forme du texte et des libelles restent ceux de `nadia-prof`.
- Il ne tranche pas la strategie de couverture au-dela du Plan 2, routee par `DEC-0087` a
  l'utilisatrice : ce planning se limite au perimetre que l'objectif fixe couvre deja.
- Il ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide` :
  ce mouvement suit le Done pedagogique de chaque story, a la review.
- Il ne choisit pas la forme du recouvrement des boutons de zone (section 5) : ce choix reste a
  `ines-design` et `nadia-prof` pendant le sprint.

## Decisions prises

**Aucune decision de sprint neuve n'est prise a ce planning, au-dela de ce qui est deja fusionne**
depuis les deux branches de preparation :

- `DEC-0088` - derogation `nombreDeManches: 7` pour l'entree "Lire un document" issue de la
  scission de `bandit-manchot-histoire-geographie-cm1` (`camille-po`, 2026-09-11) - **Acceptee**.
- `ADR-0091` - le texte alternatif d'un item de designation ne nomme aucune de ses zones, et son
  indice ne porte aucun jeton distinctif du libelle de sa bonne zone (`theo-archi`, 2026-09-11) -
  **Propose**, ses deux regles dues avec le lot de `S35-QLM14-FRISE`.
- `ADR-0092` - le libelle d'une zone n'est pas gardable par une arithmetique, la police servie
  etant celle de l'appareil (`theo-archi`, 2026-09-11) - **Propose**, ses deux mecanismes dus avec
  le lot de `S35-QLM11-PLANCLASSE`.

Les seules decisions prises par `sofia-sm` dans ce document sont des affectations nominatives
(section 3) et un routage de risque connu (section 5), aucune des deux n'etant une `DEC` ni un
`ADR`.
