# Planning - s36 - ouverture du perimetre CE1, mathematiques seule (CM2 differe)

- **Horodatage de debut** : 2026-09-21 16:40 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : choisi ici par `sofia-sm`, ni `camille-po` ni `theo-archi` n'en ont propose un dans
  leur branche de preparation. Il reprend le titre de `DEC-0089`, seule decision qui gele ce
  sprint.
- **Prealable, deja fait avant l'ouverture de ce planning** : `DEC-0089` (ouverture du perimetre
  CE1, CM2 differe, lot s36 borne a mathematiques) est **Acceptee**, confirmee le 2026-09-21 par
  l'utilisatrice sous forme citable (forme 2, `squad/README.md` section 2 bis) : *"oui et tu peux
  lancer le sprint quand c'est bon."*

## 1. Lignee et fusion des trois worktrees

Verifie avec `outils/verifier-lignee.sh` sur chacun des trois worktrees, avant toute ecriture :

- `s36-camille` (`../recreo-s36-camille`, commits `2c3b9518` puis `89eefcae`) : descend de
  `round-two` a `a8fcb000`.
- `s36-theo` (`../recreo-s36-theo`, commit `35c10f22`) : descend de `round-two` a `a8fcb000`.
- `s36-sofia` (`../recreo-s36-sofia`, ouvert pour ce planning depuis `round-two` a `a8fcb000`) :
  descend de `round-two`.

Les deux branches de preparation ne touchent aucun fichier en commun (`s36-camille` :
`docs/backlog/backlog.md`, `DEC-0089-ouverture-du-perimetre-ce1-...md`,
`squad/decisions/produit/DEC-index.md`, `squad/programme/MATRICE-couverture.md` ; `s36-theo` :
`ADR-0093-le-nom-accessible-d-une-zone-...md`,
`ADR-0080-une-designation-sur-image-...md` (amendement date), `squad/decisions/adr/ADR-index.md`)
: fusionnees sans conflit sur `s36-sofia` :

- integration de `s36-camille` jusqu'a `89eefcae` (avance rapide, aucun commit de fusion cree :
  `s36-sofia` etait encore a `round-two`).
- `b9ccd95b` - fusion de `s36-theo` (`ADR-0093`, amendement date sur `ADR-0080`).

Diff complet de `s36-sofia` contre `round-two` apres les deux fusions : 7 fichiers, 539 insertions,
0 suppression - somme exacte des deux diffs mesures separement sur chaque branche avant fusion
(269 + 270), confirmant qu'aucun contenu ne s'est perdu ni duplique.

## 2. Objectif gele

**Ouverture du perimetre CE1 (mathematiques seule), CM2 explicitement differe**, tel que pose par
`DEC-0089`. Ce sprint **n'engage aucune story de developpement de contenu CE1** : le referentiel
de competences mathematiques CE1 (`squad/programme/mathematiques/competences-ce1.md`) n'existe pas
encore, et `DEC-0089` (section 3) interdit explicitement d'en fabriquer un hors de `nadia-prof`.

**Ce que ce sprint mesure**, tel que `DEC-0089` section 2 le pose : le temps et le nombre
d'echanges necessaires a `nadia-prof` pour produire ce premier referentiel d'un niveau neuf,
compare au cout deja mesure d'un arbitrage de premiere matiere neuve sur un niveau existant
(`DEC-0071`, section 6). C'est ce chiffre qui dira si un second referentiel CE1 (francais) est
engageable des le sprint suivant ou doit attendre davantage.

Un second lot, disjoint du premier, poursuit un travail deja engage sur un niveau existant :
`S36-QLM11-PLANCLASSE`, cinquieme reprise de la ligne `QLM-CP-11` (CP), refusee a s35 sur un motif
de geometrie desormais route a un `ADR`.

## 3. Les stories, competence visee et avis de faisabilite de `theo-archi`

| Story / lot | Competence visee | Ce qu'il ferme ou ouvre | Verdict de `theo-archi` |
|---|---|---|---|
| Referentiel de competences mathematiques CE1 | Transverse - aucune ligne de matrice avant l'ecriture du referentiel | Route par `DEC-0089`, section 3 | Hors perimetre de son avis (pedagogique, pas technique) |
| `S36-QLM11-PLANCLASSE` | Questionner le monde, CP (cycle 2), `QLM-CP-11` (`non couvert`, refusee a s35) | `ZON-15` seule reste a tenir (`ZON-16` deja tenue) | Faisable moyennant `ADR-0093` (`Propose`) |
| Reserve - francais CE1 et les six autres matieres CE1 | Aucune | Hors perimetre de s36 (`DEC-0089`, section 4 ; `docs/backlog/backlog.md`, section "Sprint 36") | Non instruit, aucune story n'y est ecrite |

### Avis de faisabilite de `theo-archi`, integral

**Sur l'ouverture du niveau CE1 elle-meme** : faisable en l'etat, **aucun changement de code,
aucun `ADR` neuf necessaire** pour la seule ouverture de niveau. `ContentDescriptor` porte le
niveau comme `String` et jamais comme une enumeration, et une mecanique n'a explicitement pas le
droit de lire la matiere, le niveau ou la competence - deux regles d'`ADR-0004` (section 3, clause
d'`ArchRule` 4, plutot que d'`ADR-0006` comme le premier releve de `theo-archi` l'avait cite : erreur
corrigee ici par `sofia-sm` en verifiant la source, `ADR-0006` portant sur la configuration de
difficulte et non sur le typage du niveau). Verifie par `sofia-sm` a ce planning, sur l'arbre
`s36-sofia` : `grep -rn '"CP"\|"CM1"' --include="*.java" domain application adapters bootstrap`,
hors dossiers `/test/`, ne trouve aucun litteral de niveau dans les sources de production.

**Quatre conditions** a transmettre a `nadia-prof`/`camille-po` pour qu'un futur paquet CE1 entre
vert :

- **C1** - un fichier nomme `squad/programme/mathematiques/competences-ce1.md` ;
- **C2** - des codes de competence de la forme `MAT-CE1-nn` ;
- **C3** - un descripteur de paquet portant `niveau: "CE1"` exact, et un comptage de lignes de
  referentiel egal entre le paquet et les items qu'il declare ;
- **C4** - aucun titre de jeu ne porte le niveau de son paquet (`ADR-0078`, deja en vigueur pour
  CP et CM1, s'etend a CE1 sans amendement).

**Cinq constats, non bloquants pour l'ouverture du niveau** :

- **F1** - l'ordre des puces de niveau affichees depend de l'ordre d'apparition dans le catalogue :
  question produit, routee a `camille-po`.
- **F2** - le repli de la barre de niveaux a trois puces sous 360 px n'a jamais ete mesure a
  quatre : routee a `ines-design` et `nadia-prof`.
- **F3** - aucune borne de longueur d'enonce ne couvre CE1 : `ADR-0089` (*"au CP un enonce compte
  au plus quinze mots"*) est explicitement borne au CP par son propre titre, verifie par
  `sofia-sm` sur le fichier. Decision pedagogique, routee a `nadia-prof`.
- **F4** - le fait que "niveau = chaine, jamais enum" (`ADR-0004`) n'est lui-meme garde par aucun
  test qui l'enonce comme regle transverse (la clause 4 d'`ADR-0004` garde l'ignorance du niveau
  par une mecanique, pas l'absence de toute enumeration Java du niveau ailleurs dans le code) :
  nomme, aucune action requise a ce planning.
- **F5** - **resolu**. Au moment de sa mesure, `theo-archi` avait lu `Statut : Proposee` dans
  `DEC-0089`, en contradiction avec la citation directe de l'utilisatrice que le meme document
  porte. Verifie par `sofia-sm` sur le fichier tel qu'il est a l'ouverture de ce planning :
  `DEC-0089` porte `Statut : Acceptee`, confirmee le 2026-09-21. L'ecart releve de `theo-archi`
  etait un etat perime au moment de sa mesure - vraisemblablement un effet de la concurrence entre
  son audit et le second commit de `camille-po` (`89eefcae`, qui fait exactement ce changement de
  statut) - et non un defaut actuel du document. Ce n'est pas porte comme un obstacle ouvert.

**Sur `S36-QLM11-PLANCLASSE`** : faisable moyennant `ADR-0093` (`squad/decisions/adr/
ADR-0093-le-nom-accessible-d-une-zone-est-resolu-sur-le-document-servi-et-porte-le-libelle-que-le-paquet-declare.md`,
`Propose` le 2026-09-21). La sortie pedagogique acceptee par `nadia-prof` (le libelle sort de la
boite du bouton, via `aria-label` ou `aria-labelledby`) est aujourd'hui rejetee a tort par deux
garde-fous qui verifient `bouton.text().isBlank()` au lieu du nom accessible reel -
`ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` et
`LEcranDeMancheDuJeuDeDesignationReelEstServiTest`. `ADR-0093` corrige ce critere par deux clauses :
le nom accessible est resolu sur le document servi (texte, sinon `aria-label`, sinon
`aria-labelledby` dont chaque identifiant resout vers un texte non vide) ; ce nom doit contenir le
libelle que le paquet declare pour la zone. `ADR-0080` recoit une note datee du 2026-09-21 disant
que la propriete de sa table ne change pas, seul son juge cesse de promettre plus qu'il ne
verifie. Mesure de faisabilite faite par `theo-archi` sur son worktree dedie : `bash outils/mvn-
jdk21.sh -B clean verify` complet, BUILD SUCCESS, 6 modules sur 6, 1481 tests, 0 echec, JDK
21.0.10 - et le job `chemins` de la CI lance sur les fichiers de son diff, aucun chemin de poste
de travail ni nom de compte trouve.

## 4. Affectations nominatives

- **Redaction du referentiel de competences mathematiques CE1** : `nadia-prof`, seule (`DEC-0089`,
  section 3). Aucun autre agent n'ecrit de competence CE1.
- **F1** (ordre des puces de niveau) : routee a `camille-po`.
- **F2** (repli de la barre a 3 puces sous 360 px) : routee a `ines-design`, avec `nadia-prof` pour
  le jugement pedagogique de la forme retenue.
- **F3** (borne de longueur d'enonce CE1) : routee a `nadia-prof`.
- **F4** (absence de garde-fou transverse sur "niveau = chaine") : nommee, aucune action requise a
  ce planning.
- **`S36-QLM11-PLANCLASSE`** : `ines-design` pour la geometrie des six cadres selon l'une des deux
  sorties acceptees par `nadia-prof` ; `lea-dev` pour le cote code du lot - les deux clauses
  d'`ADR-0093` dans les deux garde-fous nommes, et l'implementation de la sortie choisie dans
  `fragments/designation.html` - continuite de son affectation sur ce meme lot depuis s35 ;
  `nadia-prof` pour l'audit, y compris le plancher de lisibilite (`ZON-15`) qui ne se ferme qu'a
  l'oeil, sur l'ecran servi.

**Sur le statut d'`ADR-0093`** : il reste **`Propose`** a ce planning, exactement comme
`ADR-0091` et `ADR-0092` l'etaient encore au planning de s35 pour le meme lot. `ADR-0093` porte
lui-meme sa propre condition de passage a `Accepte` (*"quand ses deux clauses seront dans l'arbre,
dans les deux fichiers nommes, avec leurs quatre morsures"*) : c'est une condition de livraison,
pas une condition d'engagement du developpement. `sofia-sm` ne l'accepte pas par avance pour
debloquer le lot - ce n'est pas son role de trancher l'architecture (`squad/README.md`, section
10) - et l'engagement du developpement n'en a pas besoin : la mesure de faisabilite de
`theo-archi` (porte technique verte sur son propre diff, 1481 tests) est deja la preuve que la
regle proposee est batie et non hypothetique. Le passage a `Accepte` se constate a la review,
comme pour `ADR-0091`/`ADR-0092` a s35.

## 5. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
pour s36**, pour le meme motif deja constate a s35 : `DEC-0087` (section 5) a referme la fenetre
du Plan 2 (`DEC-0083`) sans qu'une strategie neuve de composition ne soit tranchee au-dela, et
`DEC-0089` n'en pose pas une non plus - elle ouvre un perimetre, elle ne finance pas des postes.
Il n'y a donc, a l'ouverture de s36, aucun poste finance par une table de composition contre
lequel verifier une story.

**Ce que `PAR-16` verifie a la place, sur ce sprint**, meme fallback qu'a s35 : chaque element
retenu porte-t-il une affectation nominative ?

| Element | Affectation nominative | Issue |
|---|---|---|
| Referentiel de competences mathematiques CE1 | `nadia-prof` | Engagee (routee par `DEC-0089`, pas une story testable a l'avance - motif ecrit section 2) |
| `S36-QLM11-PLANCLASSE` | `ines-design`, `lea-dev`, `nadia-prof` | Engagee |
| `F1` | `camille-po` | Routee |
| `F2` | `ines-design`, `nadia-prof` | Routee |
| `F3` | `nadia-prof` | Routee |
| `F4` | aucun - nommee sans action | Non engage, aucune action requise |

Aucun element n'est "non engage, budget libere" au sens de `PAR-16` : chacun porte soit une
affectation nominative, soit un motif explicite de non-action (`F4`).

## 6. Ce que ce planning ne fait pas

- Il ne fixe aucune competence CE1, pour aucune matiere : ce travail reste a `nadia-prof`.
- Il n'ouvre pas CM2 : `DEC-0089` le differe explicitement, ce planning ne le rouvre pas.
- Il ne cree aucune ligne de `squad/programme/MATRICE-couverture.md` : aucun referentiel CE1
  n'existe encore.
- Il ne fait passer `ADR-0093` a `Accepte` : ce passage se constate a la review, une fois ses deux
  clauses dans l'arbre avec leurs quatre morsures.
- Il ne choisit pas la forme retenue pour faire sortir le libelle de la boite du bouton sur
  `S36-QLM11-PLANCLASSE` : ce choix reste a `ines-design` (propose) et `nadia-prof` (tranche).
- Il ne prononce aucun Done, technique ou pedagogique.

## Decisions prises

**Aucune decision de sprint neuve n'est prise a ce planning**, au-dela de ce qui est deja fusionne
depuis les deux branches de preparation :

- `DEC-0089` - ouverture du perimetre CE1, un troisieme niveau, CM2 explicitement differe,
  perimetre de s36 borne a mathematiques seule (`camille-po`, 2026-09-21) - **Acceptee**.
- `ADR-0093` - le nom accessible d'un bouton de zone est resolu sur le document servi et porte le
  libelle que le paquet declare (`theo-archi`, 2026-09-21) - **Propose**, ses deux clauses dues
  avec le lot de `S36-QLM11-PLANCLASSE`, reste `Propose` a ce planning (section 4 ci-dessus).
- Amendement du 2026-09-21 a `ADR-0080` (`theo-archi`) - note datee : la clause de nom accessible
  du garde-fou 1 est corrigee par `ADR-0093`, la propriete de la table ne change pas.

Les seules decisions prises par `sofia-sm` dans ce document sont des affectations nominatives
(section 4), le choix de laisser `ADR-0093` en `Propose` plutot que de l'accepter par avance
(section 4), et la correction d'une reference fautive dans l'avis de `theo-archi` (`ADR-0006` ->
`ADR-0004`, section 3) : aucune des trois n'est une `DEC` ni un `ADR`.
