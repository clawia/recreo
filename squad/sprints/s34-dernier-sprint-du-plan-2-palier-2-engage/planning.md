# [Sprint s34] Planning - 2026-09-10

- **Horodatage de debut** : 2026-09-10
- **Anime par** : sofia-sm
- **Participants** : camille-po, nadia-prof, theo-archi (avis rendu), marc-dev, lea-dev, sofia-sm,
  ines-design (trois dessins du palier 2, a la demande)

## 0. Ce qui entre a l'ouverture - integration des prealables ecrits mais non commites

s33 est clos et commite (`48635ba0`). Tous les prealables de s34 etaient ecrits sur le disque a
l'ouverture de ce planning, aucun n'etait commite : l'arbitrage prealable de `nadia-prof`, l'avis de
faisabilite de `theo-archi` (a la racine de `squad/sprints/`, faute de repertoire de sprint), la
strategie `DEC-0086` de `camille-po` et son recalcul de dette. Je les integre ici, casquette dev,
avant d'ouvrir la composition du sprint - le depot est la seule memoire partagee, et rien de ce qui
suit ne peut s'appuyer sur un chiffre qui ne serait pas dans un commit.

**Deplacement.** Je cree ce repertoire de sprint et j'y deplace l'avis de `theo-archi`
(`squad/sprints/avis-faisabilite-theo-s34.md` -> `avis-faisabilite-theo.md`), comme il me l'a
demande faute de pouvoir le faire lui-meme avant l'ouverture du repertoire. Les quatre references a
l'ancien chemin dans `docs/backlog/backlog.md` et dans `DEC-0086` sont corrigees dans le meme geste.

**Ordre de commit, et il est contraint par une mesure de `theo-archi`, pas par une preference.** Son
avis (section 5.1) mesure que les trois memes fichiers rendent un verdict rouge sur un arbre detache
qui ne porte que `48635ba0` plus son propre diff, et vert sur le checkout partage ou le diff non
commite de `nadia-prof` est present : trois identifiants qu'il cite (`RES-1`, `RES-2`, `COH-1`) ne
sont alloues que dans l'arbitrage de `nadia-prof`. **Son document ne peut donc pas entrer dans un
commit anterieur a celui qui porte l'arbitrage de `nadia-prof` et ses sept allocations.** J'integre
dans cet ordre :

1. **L'arbitrage prealable de `nadia-prof`** (`squad/conformite/ARBITRAGE-PREALABLE-s34-...md`),
   `squad/decisions/REGISTRE-identifiants.md` (sept identifiants, `RES-1` a `RES-4`, `COH-1`,
   `PAR-15`, `PAR-16`, ligne d'etat portee de 601/97 a 608/99) et
   `squad/programme/education-physique-et-sportive/competences-cp.md` (correction de l'exemple de la
   trottinette et du toboggan, meme diff qu'elle).
2. **L'avis de faisabilite de `theo-archi`**, deplace dans ce repertoire, plus ses deux dettes
   payees dans le meme diff que son avis : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
   (cinquieme clause, cinq motifs d'exemption perimes corriges) et
   `PaquetCm1HistoireGeographieTest` (litteral mort de l'accent sur "evenement").
3. **`DEC-0086` (amendee section 12), `RECALCUL-registre-de-dette-ouverture-s34.md`,
   `squad/decisions/produit/DEC-index.md` et les sept stories de `docs/backlog/backlog.md`** que
   cette decision compose.
4. **Ce fichier**, et l'amendement que je fais dans le meme geste a `squad/README.md` (sections 4 et
   9 - voir section 12 ci-dessous) : le point de controle poste/story que `DEC-0086` me route, et la
   regle de poste sur le registre d'identifiants que `theo-archi` route a `squad/README.md`.

**Ce que je ne fais pas ici** : je n'ecris pas la reprise des deux tests d'`EMC-CP-07` que
`theo-archi` a redigee en prose dans son avis mais pas dans l'arbre - ce n'est pas un rattrapage a
faire dans ce meme geste, c'est un prealable distinct, sequence en section 8 ci-dessous, avant
l'ouverture de la branche qui le concerne.

**Quatre jobs de la CI, lances sur l'arbre assemble apres l'ensemble de ces integrations et de ce
fichier, nommes un par un en section 13.**

## Objectif du sprint

Fermer autant que possible des huit lignes du vivier prioritaire de `DEC-0086` - les six reprises
que `nadia-prof` a reexprimees au format `RES-1` (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`,
`EPS-CP-08`, la refonte de la tache B de `cp-emc-exclusion`, le lot de l'effort et du corps) et les
deux lignes du palier 2 engagees a son entree minimale par l'utilisatrice (`QLM-CP-11`,
`QLM-CP-14`) - sans rouvrir la strategie du Plan 2, dont s34 est le **dernier sprint de la
fenetre**, et sans reproduire les trois classes de defaut que la retro de s33 a nommees (section 11
ci-dessous). Gele : il ne change plus pendant le sprint.

## 1. Taux d'entree du sprint

**69 / 138 = 50,0 %** (69 `couvert-valide`, 1 `en cours`, 68 `non couvert`, 71 `non couvrable en
l'etat`, denominateur atteignable 138 - compte repris de `DEC-0086`, section 1, lui-meme non
recalcule sur celui de la review de s33).

## 2. La borne de s34 : 12,0, composition reprise sans recalcul (`DEC-0086`, sections 3 et 12)

**La borne reste 12,0** (trois instances a 4,0, `PAR-9` ; plafond individuel dur 5,0, `PAR-8`),
non rediscutee.

**Sous-total engage : 9,0, pour +8 lignes.** Sous-total non affecte : 3,5. Total nominal 12,5 - un
depassement nominal de 0,5 au-dessus de la borne, **preexistant a ce planning et non recalcule** :
`DEC-0086` l'a arbitre comme un plafond et non comme une obligation de tout depenser, avant meme
l'engagement du palier 2, et l'engagement ne l'aggrave pas (section 12.5 de `DEC-0086`).

**Plafond arithmetique : 6 lignes a l'origine, porte a +8 par l'engagement du palier 2** (75/138 =
54,3 % sans le palier 2, **77/138 = 55,8 % avec**).

**Attente raisonnable de `nadia-prof` : 2 a 4 lignes**, portee par le vivier prioritaire de six
reprises (71 a 73/138 = 51,4 % a 52,9 %) - `nadia-prof` refuse expressement d'etendre cette
fourchette aux deux lignes du palier 2 (`DEC-0086`, section 12.2) : leur fermeture depend d'abord
d'une condition technique (les quatre lots `L-a` a `L-d` livres) puis d'une premiere epreuve reelle
des huit conditions `ZON-*` neuves, jamais mordues sur du contenu reel avant ce lot. Le palier 2
porte donc sa propre condition de fermeture, pas un chiffre d'attente.

## 3. Composition et verification poste/story (`PAR-16`, `DEC-0086` section 5)

**Methode.** Pour chaque poste de la composition de `DEC-0086` (sections 3 et 12.2), je verifie
qu'il porte une story dans `docs/backlog/backlog.md` **et** une affectation nominative. Un poste qui
n'a pas les deux est ecrit "non engage, budget libere" avec le montant et sa destination -
c'est la regle que je viens d'inscrire dans `squad/README.md`, section 4 (voir section 12).

### 3.1 Sous-total engage : 9,0, +8 lignes - tous les postes ont une story et une affectation

| Poste | Story | Prix | Ligne | Instance | Developpeur | Verdict `PAR-16` |
|---|---|---|---|---|---|---|
| `HG-CM1-12`, reprise | `S34-HG-12` | 0,5 | +1 | A | `marc-dev` | engage |
| `cm1-anglais`, quatre reserves | `S34-ANG-CORR` | 0,5 | 0 | A | `marc-dev` | engage |
| Passe transverse de l'instance d'accumulation | `S34-TRANSVERSE-1` | 0,5 | 0 | A | aucun (audit seul) | engage |
| Decoupage de `S33-CONS-4` en quatre entrees | `S34-CONS-4` | 1,0 | 0 | A | `marc-dev` (execution) ; `theo-archi` (forme technique) | engage |
| `EMC-CP-07`, cinquieme passage | `S34-EMCCP07` | 0,5 | +1, conditionnel | B | `marc-dev` | engage |
| `cp-emc-exclusion`, tache B refondue | `S34-EXC-TACHEB` | 1,0 | 0 | B | `marc-dev` | engage |
| `EPS-CP-04`, reprise | `S34-EPS-SEC` | 0,5 | +1 | C | `sofia-sm` (dev) | engage |
| `EPS-CP-08`, reprise | `S34-EPS-JEU` | 0,5 | +1 | C | `sofia-sm` (dev) | engage |
| Lot de l'effort et du corps | `S34-EPS-CORPS-EFFORT` | 1,5 | +2 | C | `sofia-sm` (dev) | engage |
| Verification du remede de `SCO-1` (instruction 1 de `DEC-0061`) | `S34-SCO1-REMEDE` | 0,5 | 0 | A | `theo-archi` (ADR + regle de non-regression) ; `sofia-sm` (dev, implementation des trois sessions) | engage |
| Lot de designation du palier 2 (`QLM-CP-11`, `QLM-CP-14`) | `S33-PAL2-1`, plus les prealables `S33-PAL2-TROU-A`, `S33-PAL2-TROU-B` | 2,0 | +2 | B (marge, voir section 5) | `lea-dev` (chemin de jeu complet, `L-a` a `L-e`) ; `ines-design` (trois dessins) | engage |
| **Total** | | **9,0** | **+8** | | | |

Instance A : 3,0/4,0 engage (`S34-HG-12` 0,5 + `S34-ANG-CORR` 0,5 + `S34-TRANSVERSE-1` 0,5 +
`S34-CONS-4` 1,0 + `S34-SCO1-REMEDE` 0,5). Instance C : 2,5/4,0 engage. Instance B, hors palier 2 :
1,5/4,0 ; avec le palier 2 (section 5) : 3,5/4,0. Aucune instance ne depasse 4,0.

### 3.2 Sous-total non affecte : nominalement 5,5, compte a 3,5 par `DEC-0086` - aucun poste n'a de
### story

| Poste | Prix nomme | Verdict `PAR-16` | Ou va le budget libere |
|---|---|---|---|
| `cp-emc-regles-de-vie` (`REG-R3` a `R5`) | 0,5 | **non engage, budget libere** | reste disponible pour l'instance B en cours de sprint, si `nadia-prof` lui ecrit un objet au format `RES-1` (`RES-4`) et que `camille-po` ouvre une story |
| `cp-emc-permis-interdit` (`PER-R2`, `R3`) | 0,5 | **non engage, budget libere** | idem |
| `cp-emc-emotions-autrui` (`EMO-R3`) | 0,5 | **non engage, budget libere** | idem |
| `cp-emc-formules-de-politesse` (`POL-R3`, `R4`) | 0,5 | **non engage, budget libere** | idem |
| Residu du groupe 2a (cinq paquets, sciences et EMC CM1) | 2,5 | **non engage, budget libere** | reste disponible ; `nadia-prof` l'a nomme premier poste a sacrifier si un arbitrage contre le palier 2 devenait necessaire (arbitrage prealable, section 5.7) - devenu sans objet, le palier 2 etant deja engage |
| Groupe 2b (`CONS-2`, `SAI-R1`, `ERR-R1`) | 1,0 | **non engage, budget libere**, et **deconseille par `nadia-prof`** | reste disponible mais non a reprendre avant que le decoupage de `S34-CONS-4` ne stabilise sa forme (`DEC-0086`, section 8) |

**Ecart nomme, pas resolu ici.** La somme des six postes ci-dessus vaut 5,5, alors que `DEC-0086`
(section 12.2) porte le sous-total non affecte a 3,5 apres avoir preleve les 2,0 du palier 2 "sur le
total du sous-total", **sans les attribuer nommement a aucune des six lignes** - `camille-po` le dit
elle-meme et je ne le comble pas a sa place : les six postes ci-dessus restent chacun a son prix
nomme, et le pool qu'ils forment collectivement est compte pour 3,5 par la strategie en vigueur.
Aucun des six n'a de story ni d'affectation a la cloture de ce planning : le point de controle
`PAR-16` rend donc "non engage, budget libere" pour chacun, ce qui est conforme a `DEC-0086`,
section 6 - `RES-4` les rend non engageables tant qu'ils ne portent pas un objet ecrit par
`nadia-prof`, a son propre cout.

**Aucun poste de la composition de `DEC-0086` n'est laisse ambigu a la cloture de ce planning.**

## 4. Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S34-HG-12` | Les distracteurs de `HG-CM1-12` deviennent des reponses serieuses | Histoire-geographie, CM1 - `HG-CM1-12` | Arbitrage prealable de s34, section 5.1 | `marc-dev`, instance A |
| `S34-ANG-CORR` | Lever les quatre reserves non bloquantes de `cm1-anglais` sur `LVE-CM1-07` | Anglais, CM1 - `LVE-CM1-07` (deja `couvert-valide`) | `DEC-0085`, section 10 ; verdicts-nadia-instance-A.md (s32) | `marc-dev`, instance A |
| `S34-TRANSVERSE-1` | Passe transverse de l'instance d'accumulation | Transverse | `DEC-0083` ; `DEC-0085`, section 10 | `nadia-prof`, instance A |
| `S34-CONS-4` | Scinder `bandit-manchot-histoire-geographie-cm1` en quatre entrees, une tache par entree | Transverse (110 items existants, aucune ligne fermee par la story elle-meme) | Arbitrage prealable de s34, section 4.2 ; `ADR-0081` | `marc-dev` (execution, y compris la redaction des quatre consignes sur le gabarit du catalogue et la tache que `nadia-prof` a nommee), `theo-archi` (forme technique), `nadia-prof` (audit, `RES-3`), instance A. **Correction du 2026-09-11, section 15.1** : la formulation initiale faisait porter a `nadia-prof` la redaction des quatre consignes, en contradiction avec son arbitrage (section 4.2, *"je n'ecris aucune des quatre consignes"*) |
| `S34-EMCCP07` | Cinquieme passage sur `bandit-manchot-regle-souhait-loi-cp` | EMC, CP - `EMC-CP-07` | Arbitrage prealable de s34, section 5.2 ; avis de `theo-archi`, sections 1 et 1.6 | `marc-dev`, instance B |
| `S34-EXC-TACHEB` | Refondre la tache B de `bandit-manchot-exclusion-cp` | EMC, CP - `EMC-CP-03` (deja `couvert-valide`, correction non bloquante) | Arbitrage prealable de s34, sections 4.4 et 5.5 ; `PAR-15` | `marc-dev`, instance B |
| `S34-EPS-SEC` | Reprise de `EPS-CP-04` | EPS, CP - `EPS-CP-04` | Arbitrage prealable de s34, section 5.3 | `sofia-sm` (dev), instance C |
| `S34-EPS-JEU` | Reprise de `EPS-CP-08` | EPS, CP - `EPS-CP-08` | Arbitrage prealable de s34, section 5.4 | `sofia-sm` (dev), instance C |
| `S34-EPS-CORPS-EFFORT` | Second lot d'EPS CP : effets de l'effort, echauffement, hydratation, recuperation | EPS, CP - `EPS-CP-10`, `EPS-CP-11` | `DEC-0083`, sections 6 et 9.2 ; arbitrage prealable de s34, section 5.6 | `sofia-sm` (dev), instance C |
| `S34-SCO1-REMEDE` | Trois essais quand l'elimination est possible (instruction 1 de `DEC-0061`, rendue) | Transverse (mecanique `bandit-manchot`) | Arbitrage prealable de s34, section 4.1 ; avis de `theo-archi`, sections 2 et 2.6 | `theo-archi` (ADR et regle) ; `sofia-sm` (dev, implementation), instance A |
| `S33-PAL2-TROU-A` | L'ecran de fin de partie d'une designation porte la question qu'il commente | Transverse (mecanique de designation) | `DEC-0083`, section 13.3 ; `ADR-0088` | `lea-dev` |
| `S33-PAL2-TROU-B` | L'ecran de fin de partie d'une designation ne montre ni un identifiant ni un libelle | Transverse (mecanique de designation) | `DEC-0083`, section 13.3 ; `ADR-0088` | `lea-dev` |
| `S33-PAL2-1` | Premier lot de designation : plan de classe et frise chronologique | Questionner le monde, CP - `QLM-CP-11`, `QLM-CP-14` | `DEC-0083`, section 13 ; `DEC-0086`, section 12 | `lea-dev` (developpement), `ines-design` (trois dessins), `nadia-prof` (instance B, audit) |

## 5. Affectation des trois instances de `nadia-prof`

| Instance | Stories auditees | Charge | `PAR-13` |
|---|---|---|---|
| **A** (`PAR-12`, instance d'accumulation) | `S34-HG-12`, `S34-ANG-CORR`, `S34-TRANSVERSE-1`, `S34-CONS-4`, `S34-SCO1-REMEDE` | 3,0/4,0 | Terrains deja rencontres (histoire-geographie et anglais CM1) |
| **B** (`PAR-7`, deux images tenues a la fois, une seule payee jusqu'ici) | `S34-EMCCP07`, `S34-EXC-TACHEB`, plus **le palier 2** (`S33-PAL2-1` et ses deux prealables) | 3,5/4,0 | EMC CP deja rencontre ; **questionner le monde CP est un terrain neuf pour cette instance**, seconde image que `PAR-7` autorise explicitement |
| **C** (`PAR-2`, matiere reservee - EPS) | `S34-EPS-SEC`, `S34-EPS-JEU`, `S34-EPS-CORPS-EFFORT` | 2,5/4,0 | EPS deja rencontre a s32 et s33 ; `PAR-2` la reserve a une seule matiere, ce qui l'exclut du palier 2 (questionner le monde) |

**Pourquoi le palier 2 va a l'instance B, et pas C** : `PAR-2` reserve l'instance C a une seule
matiere (l'EPS), ce qui l'exclut d'emblee de questionner le monde CP. `PAR-7` autorise
explicitement l'instance B a tenir deux images a la fois, dont une seule payee jusqu'ici (EMC CP,
1,5/4,0 engage) - elle a la marge necessaire pour absorber les 2,0 du palier 2 sans depasser 4,0, et
c'est la seule des trois instances qui l'a.

## 6. Affectation nominative des trois developpeurs, et le risque de capacite

**Ce que j'arbitre moi-meme, casquette Scrum Master, categorie capacite/charge.** L'engagement du
palier 2 par l'utilisatrice retire un troisieme producteur de contenu au moment ou `nadia-prof`
l'a nomme comme le point ou son plafond de 6 lignes (porte a 8 avec le palier 2) suppose trois
producteurs de contenu pour trois instances d'audit. Sous `L-c` (troisieme branche de
`PartieController`, indivisible, "un developpeur, un sprint entier" - avis de `theo-archi` de s32),
il n'en reste que deux pour le reste du vivier. Je ne peux pas faire autrement qu'accepter cette
consequence arithmetique : c'est l'utilisatrice qui a tranche d'engager le palier 2 a s34
(`DEC-0086`, section 12.1), pas moi, et je n'ai pas de quatrieme developpeur a affecter.

| Developpeur | Stories | Worktree | Motif |
|---|---|---|---|
| **`lea-dev`** | `S33-PAL2-TROU-A`, `S33-PAL2-TROU-B`, `S33-PAL2-1` (chemin de jeu complet, `L-a` a `L-e`) | dedie, palier 2 | **Le risque principal du sprint** (section 8). Terrain neuf pour tout le monde - je n'ai donc pas de continuite a preserver en la lui donnant plutot qu'a un autre, ce qui laisse `marc-dev` et `sofia-sm` continuer sur les terrains qu'ils tiennent deja |
| **`marc-dev`** | `S34-HG-12`, `S34-CONS-4`, `S34-ANG-CORR` (terrain A, continuite avec s32-s33) ; `S34-EMCCP07`, `S34-EXC-TACHEB` (terrain B, **rupture de continuite** : `lea-dev` tenait ce terrain a s33) | dedie, terrains A et B combines | Faute d'un troisieme developpeur disponible pour le terrain B pendant que `lea-dev` est sur le palier 2, je regroupe A et B dans un seul worktree plutot que de laisser B sans producteur - un seul developpeur ne cree pas la collision d'ecriture concurrente sur `catalogue.yaml` que la regle de s30-s31 visait, puisqu'il n'y a qu'un seul point d'ecriture |
| **`sofia-sm`** (casquette dev) | `S34-EPS-SEC`, `S34-EPS-JEU`, `S34-EPS-CORPS-EFFORT` (terrain C, continuite avec s33) ; `S34-SCO1-REMEDE` (implementation des trois sessions du domaine, aux cotes de `theo-archi` qui ecrit l'`ADR` et la regle de non-regression) | dedie, terrain C plus le remede transverse | Le poste le plus petit des trois en volume de contenu (theo le chiffre a une demi-journee), ajoute a une charge deja portee a s33 sur ce meme terrain |

**Ce que cette repartition coute, nomme plutot que tu** : `marc-dev` porte cinq stories dans un seul
worktree (terrains A et B), la charge la plus lourde en nombre de paquets distincts touches ce
sprint. Si `S34-EMCCP07` ou `S34-EXC-TACHEB` prend plus de temps que prevu du fait de la rupture de
continuite sur le terrain B, je le route a moi-meme en cours de sprint (reaffectation ecrite au
moment ou elle est faite), pas a `camille-po` ni a `nadia-prof`.

## 7. Points de couture entre branches, nommes d'avance

| Fichier | Touche par | Ce qui est attendu |
|---|---|---|
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | `sofia-sm` (nouvelle entree, lot de l'effort et du corps) ; `marc-dev` (quatre entrees neuves remplacant `bandit-manchot-histoire-geographie-cm1`, `S34-CONS-4`) ; `lea-dev` (une ou deux entrees neuves de designation, `S33-PAL2-1`) | Trois worktrees ajoutent chacun des blocs disjoints. Je fusionne dans l'ordre de la section 9, et je verifie apres chaque fusion que les blocs coexistent intacts plutot que de supposer une resolution automatique silencieuse - meme geste qu'a s33 |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (motifs d'exemption et leurs comptes) | `theo-archi` (deja corrige dans le diff integre en section 0, cinq motifs recomptes) ; `marc-dev` (le decoupage de `S34-CONS-4` fait disparaitre l'entree `cm1-histoire-geographie` du tableau, potentiellement remplacee par jusqu'a quatre entrees si les nouveaux paquets restent hors du domaine numerique) ; `sofia-sm` (nouvelle entree pour le lot de l'effort et du corps, si ses propositions ne sont pas numeriques) | `marc-dev` et `sofia-sm` forkent tous deux d'un arbre qui porte deja la correction de `theo-archi` (integree en section 0, avant l'ouverture d'aucune branche) : aucun des deux ne doit reintroduire un motif perime |
| `PaquetCm1HistoireGeographieTest` | `marc-dev` seul (reecriture des distracteurs de `S34-HG-12`, puis restructuration en jusqu'a quatre classes de test par `S34-CONS-4`, dans le meme worktree) | Aucune coupure inter-branches : un seul developpeur, pas de risque de collision |
| `PaquetCpEmcRegleSouhaitLoiTest` | `theo-archi` (reprise des deux tests de paquet, prealable a committer sur `round-two` avant l'ouverture de la branche de `marc-dev`, section 8) ; puis `marc-dev` (mouvement d'item de la sortie retenue par `nadia-prof`) | Sequence stricte : le prealable de `theo-archi` doit etre le commit dont `marc-dev` forke, jamais un commit posterieur a l'ouverture de sa branche |
| `domain/mechanics` (`BanditManchotSession`, `DesignationSession`, `SaisieSession`) et `DeuxCoupsSimultanesTest` | `sofia-sm` (implementation du remede, `S34-SCO1-REMEDE`) | `theo-archi` n'ecrit que l'`ADR` et une classe de test neuve pour la regle de non-regression, sans toucher aux trois sessions ni a `DeuxCoupsSimultanesTest` : pas de fichier partage entre son travail et celui de `sofia-sm` sur ce poste |
| `squad/decisions/REGISTRE-identifiants.md` | aucune instance de `nadia-prof` n'ecrit en parallele a l'ouverture de ce planning (elle a travaille seule sur son arbitrage prealable, section 13 de son document) | Le risque nomme par `theo-archi` (section 3.3 de son avis) redevient reel des que les trois instances rouvrent des reserves en cours de sprint. La regle de poste que j'ecris en section 12 (`squad/README.md`, section 9) s'applique des la premiere ecriture |

## 8. Ordre des prealables techniques, contraignant

1. **La reprise des deux tests de `EMC-CP-07`** (`fer1LesDeuxCategoriesDuClassementSontChacuneEnBonneReponse`,
   `fer1LaSecondeMoitieDuLibelleEstServieParUneTacheQuiNeClassePas`, `bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires`
   - texte donne par `theo-archi`, avis section 1.2-1.3) **doit etre commitee sur `round-two`, par
   `theo-archi`, avant que `marc-dev` n'ouvre son worktree sur `S34-EMCCP07`.** L'attente de
   fermeture de `nadia-prof` est explicitement conditionnee (arbitrage prealable, section 5.2) : "1
   si le prealable technique est obtenu avant l'ouverture de la branche, 0 sinon" - zero sur quatre
   passages jusqu'ici. Je route cette action a `theo-archi` en premiere etape ("Prochaines etapes"
   ci-dessous), avant toute autre ouverture de branche sur le terrain B.
2. **Le choix de sortie (a) ou (b) sur `EMC-CP-07`** (deplacer un item en tache B, ou en ajouter un
   douzieme) **appartient a `nadia-prof`, pas a `theo-archi`** - correction que `DEC-0086`
   (section 12.4.3) et la story elle-meme font deja. `theo-archi` a chiffre l'ecart (section 1.6 de
   son avis) et penche techniquement pour (a) sans l'imposer. Ce choix doit etre rendu avant que
   `marc-dev` ne touche aux items 4, 5 et 6 - je le route a `nadia-prof` (section 10), pas de delai
   fixe au-dela de "avant que le mouvement d'item ne soit fait".
3. **Le palier 2 (`S33-PAL2-1` et ses deux prealables)** est le seul poste de ce sprint qui touche
   du code de production a travers trois couches (`domain`, `adapter-web`, `adapter-content`) -
   tout le reste de la composition est du contenu et des tests. Cinq lots (`L-a` a `L-e`, avis de
   `theo-archi` de s32, section 7), `ADR-0080` toujours *Propose*, trois dessins requis, huit
   conditions `ZON-*` jamais mordues sur du contenu reel. **Je le traite comme le risque principal
   du sprint.** Ce qui se passe s'il deborde : `lea-dev` lui consacre le sprint entier sur
   instruction de `theo-archi` (`L-c` indivisible) ; si `L-c` n'est pas livre a la cloture, `L-e`
   (le contenu, `S33-PAL2-1`) ne peut pas s'ouvrir - ses prealables ne sont pas presumes acquis par
   la story elle-meme - et les 2,0 de son poste restent non depenses. Sous la methode de projection
   abandonnee par `DEC-0086` (section 4), ce non-depense **n'est plus compte comme une descente
   promise** : il est simplement mesure a la cloture, comme n'importe quel autre poste engage et
   non livre. Les deux lignes (`QLM-CP-11`, `QLM-CP-14`) resteraient alors `non couvert` pour un
   quatrieme sprint (financees a s33, reportees une fois a s34, engagees mais non livrees), et ce
   fait serait ecrit tel quel a la review, pas attenue.

## 9. Fusion des branches, ordre attendu

Sur le patron de s33 : `sofia-sm` fusionne casquette dev, dans l'ordre ou les branches arrivent,
sauf contrainte de sequencement nommee ci-dessus. Ordre attendu, sous reserve de l'ordre reel
d'arrivee : le prealable de `theo-archi` sur `EMC-CP-07` (avant toute ouverture de branche, pas une
fusion) ; puis `sofia-sm` (terrain C, le plus autonome) ; puis `marc-dev` (terrains A et B
combines) ; puis `lea-dev` (palier 2), en dernier parce que c'est la branche la plus susceptible de
deborder le sprint et donc de ne pas etre prete a la meme date que les deux autres.

## 10. Ce que je route, pas ce que je tranche

| Obstacle | Categorie | Route a | Attendu |
|---|---|---|---|
| Choix de sortie (a)/(b) sur `EMC-CP-07` | Pedagogie | `nadia-prof` | Decision avant le mouvement d'item par `marc-dev` (section 8, point 2) |
| Les deux declarations manquantes de `RES-1` (une ligne `Objet` normee dans une fiche de conformite) | Pedagogie (adoption de la forme) puis technique (regle qui la verifie) | `nadia-prof` (adoption, a son propre cout) puis `theo-archi` (regle, une fois la forme adoptee) | `DEC-0086`, section 12.4.1 : ni l'un ni l'autre n'est une condition de ce sprint |
| Six postes du residu sans story (`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`, residu du groupe 2a, groupe 2b) | Pedagogie puis produit | `nadia-prof` (objet au format `RES-1`, `RES-4`) puis `camille-po` (story) | Aucun n'est engage ce sprint (section 3.2) ; si un objet est ecrit en cours de sprint, je peux encore leur ouvrir une story et une affectation sans rouvrir la borne |
| Le remede structurel du registre d'identifiants | Technique, regle de poste non encodable | resolu directement dans `squad/README.md`, section 9 (section 12 ci-dessous) - je ne le route pas, je l'applique | Rien n'est du en retour ; je le nomme pour que la squad sache ou la regle vit |
| Hygiene de poste (worktree dedie, arret par identifiant de processus) | Qualite | deja route par `yanis-qa` a s33, rappele ici | Chaque developpeur et chaque instance de `nadia-prof` verifie sa propre pratique avant sa premiere ecriture, comme `nadia-prof` l'a deja fait dans son arbitrage prealable |
| Capacite reduite a deux producteurs de contenu sous `L-c` | Capacite / charge | moi-meme | Reaffectation ecrite en cours de sprint si `S34-EMCCP07` ou `S34-EXC-TACHEB` deborde (section 6) |

## 11. Ce que la retro de s33 a nomme, et qui mord ici

- **La correction executee a la lettre de la reserve, pas a son objet.** `nadia-prof` y repond par
  `RES-1` a `RES-4` (arbitrage prealable, section 3) : chaque poste du vivier prioritaire porte
  desormais un objet, un mode de defaillance nomme d'avance et un perimetre mesure sur l'etat
  d'arrivee, pas seulement un texte a recopier. **La part executable de ce controle est refusee par
  `theo-archi`** : sur les 69 fiches de conformite, aucun bloc de reserve ne porte de partie
  etiquetee `Objet`, une regle ecrite aujourd'hui serait rouge sur une population entierement
  exemptee, donc decorative (`ADR-0051`). **Ce qui reste comme controle reel** : les trois lectures
  de `RES-3` (relire l'objet, recoller le texte a sa place, relire le paquet entier), faites par
  `nadia-prof` a l'audit et par chaque developpeur avant de commiter - une personne, pas un
  garde-fou.
- **Le correctif qui reproduit la classe de defaut qu'il paie, non evite trois sprints de suite**
  (`CAT-1` a s32, `EMC-11` a s33, `JEU-R9` sur le meme paquet a s33). Chaque developpeur relit le
  paquet assemble contre l'objet de la reserve, pas seulement contre son texte, avant de commiter -
  c'est desormais nomme par `RES-3` cote pedagogique et je le rappelle cote developpement.
- **Deux stories du meme commit qui se defont l'une l'autre** (`S33-EXC-R1`/`S33-EXC-R5`). C'est
  contre ce defaut que j'ai amende `squad/README.md`, section 4, a la retro de s33 : un commit qui
  paie plusieurs stories touchant le meme paquet relit le paquet comme un tout avant de commiter.
  `S34-EXC-TACHEB` et `S34-CONS-4` sont chacune seules sur leur paquet ce sprint, mais `marc-dev`
  porte cinq stories dans un seul worktree (section 6) : je lui rappelle explicitement cette regle,
  paquet par paquet, avant chacun de ses commits.

## 12. Amendements a `squad/README.md`, faits dans ce meme geste

Deux amendements, tous deux routes par un autre agent et executes par moi plutot que reportes a la
retro, parce que le risque qu'ils couvrent est actif des ce planning et pas seulement a la cloture :

1. **Section 4** : le tableau des ceremonies porte desormais "verification poste/story (`PAR-16`,
   `DEC-0086` section 5)" dans le contenu obligatoire de `planning.md`, avec un paragraphe qui en
   fixe la procedure - c'est la section 3 de ce fichier qui l'applique pour la premiere fois.
2. **Section 9** : une instance de `nadia-prof` qui ecrit dans
   `squad/decisions/REGISTRE-identifiants.md` le fait depuis son propre worktree, jamais dans le
   checkout partage - regle de poste routee par `theo-archi` (avis de s34, section 3.3), qui ferme
   les deux modes du defaut structurel qu'il a mesure (le faux rouge transitoire, deja rencontre
   deux fois a s33, et le faux vert emprunte, mesure pour la premiere fois sur son propre document
   a l'ouverture de s34). Aucun test ne peut la verifier ; elle est ecrite pour que la squad la
   suive, pas pour qu'un garde-fou la fasse respecter.

## 13. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

Lances sur l'arbre assemble apres l'integration de la section 0, l'ecriture de ce fichier et les
deux amendements de la section 12, avant tout commit.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis mes quatre messages de commit prevus passes a `verifier-prefixes.sh` | vert - *"Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif."*, *"Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu."*, puis *"Tous les commits portent un prefixe d'agent connu."* sur les quatre messages `[SOF]` |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files` (suivis) plus `git ls-files --others --exclude-standard` (non suivis) passes a `verifier-chemins.sh` | vert - *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."* puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sur l'union suivis + non suivis, y compris ce fichier et les deux amendements de `squad/README.md` |
| `outils` | la boucle du job sur ses trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert - **9 juges purs executes**, aucun motif vide, aucun echec |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | vert - `BUILD SUCCESS`, six modules `SUCCESS` (`domain` 15 s, `application` 5 s, `adapter-web` 40 s, `adapter-content` 25 s, `bootstrap` 1 min 42), JDK 21.0.10 rapporte par la machine virtuelle des tests (`c.r.b.catalogue.CatalogueChargeableIT`, log Spring Boot). **1446 tests, 0 echec, 0 erreur, 0 ignore** - somme de l'attribut `tests` sur les **193** fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports` de l'arbre entier, mesuree sur le disque apres ce `clean verify`. Identique au chiffre que `theo-archi` a mesure sur son propre diff (section 5 de son avis) : je ne modifie aucun fichier Java dans ce planning, les deux fichiers de test qu'il a corriges etant deja integres en section 0 |

## 14. Fusion des quatre branches livrees, casquette dev (2026-09-11)

**Un cinquieme diff, non commite dans le checkout partage a l'ouverture de ce geste** :
`squad/README.md` portait un amendement de process ecrit par l'orchestrateur (section 9, une
defaillance d'infrastructure - `marc-dev` arrete en pleine tache par une erreur d'API,
`invalid_request`, sans rapport avec la qualite du contenu qu'il editait). Je l'ai juge et
corrige avant de le commiter : il s'auto-attribuait a *"retro de s34"*, une ceremonie qui n'a pas
encore eu lieu (nous sommes en fusion, avant review et retro) - contradiction directe avec la
regle qui le precede dans le meme fichier (*"une regle de conduite trouvee en cours de sprint est
ecrite... pas seulement gardee en memoire d'agent"*). Reetiquete *"ecrit pendant la fusion, avant
la review et la retro"* et commite (`0b4017b9`).

**Lignee verifiee avant chaque fusion** (`git merge-base`) : `s34-sofia` (`8ee750f0`) fourche de
`8f37bc6b` (le commit qui ouvre ce planning) ; `s34-theo` (`2c3463b3`) fourche de `f705d62a`
(son propre prealable EMC-CP-07, lui-meme enfant de `8f37bc6b`) ; `s34-marc` (`76d43606`) et
`s34-lea` (`605038a1`) fourchent toutes deux de `d2b13635`, la tete de `round-two` a l'ouverture
des branches (apres integration du prealable de `theo-archi`, de son avis sur les quatre tests
`cp-emc-exclusion`, de la decision de sortie de `nadia-prof` et des trois dessins d'`ines-design`).
Les quatre lignees remontent a `round-two`, aucune n'est orpheline.

**Ordre de fusion, avec la contrainte non prevue au planning** : `s34-sofia` en premier, puis
`s34-theo` immediatement apres et avant toute mesure - `ADR-0090` est rouge par construction tant
que le remede des trois sessions (`S34-SCO1-REMEDE`, port par `s34-sofia`) n'est pas present, ce
que `theo-archi` a ecrit dans son `ADR`, dans l'index et dans son message de commit. **Le rouge se
referme exactement a cette fusion** : `UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest`
mesure 3/3 vert des que `s34-theo` est fusionnee sur l'arbre qui porte deja le remede de
`s34-sofia`, avant meme la fusion de `marc-dev` et de `lea-dev`. Puis `s34-marc`, puis `s34-lea`
en dernier, sur le patron du planning (section 9).

**Trois conflits, tous nommes d'avance au planning (section 7), aucune resolution automatique
silencieuse supposee - verifie apres chaque fusion que les blocs coexistent intacts** :

- `catalogue.yaml` : `s34-sofia` (bloc `bandit-manchot-corps-effort-eps-cp`) et `s34-lea` (bloc
  `designation-frise-de-la-journee`) ajoutaient chacune une entree a la meme position en fin de
  fichier - deux blocs disjoints concatenes, aucun perdu.
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : meme motif, deux entrees
  d'exemption disjointes (`cp-eps-corps-effort` et `cp-frise-de-la-journee`) sur la meme fermeture
  de `Map.ofEntries`, concatenees.
- `TexteAccentueExposeParLePortEntrantTest` : cinq zones de conflit, les listes de chaines
  attendues (titre, consigne, domaine, competence) de `s34-sofia` et de `s34-lea` entrelacees dans
  l'ordre d'arrivee des paquets, sans qu'aucune des deux ne soit perdue.

`marc-dev` n'a touche `catalogue.yaml` ni aucun des deux fichiers de test ci-dessus (`S34-CONS-4`
non commencee, voir section 15). Les deux autres points de couture nommes se sont fondus sans
conflit Git : `PaquetCpEmcRegleSouhaitLoiTest` (`theo-archi` en prealable, `f705d62a`, puis
`marc-dev`, `cefb15aa` - sequence stricte respectee, verifiee sur l'historique du fichier) et
`domain/mechanics` plus `DeuxCoupsSimultanesTest` (`s34-sofia` seule, `theo-archi` n'y touchant
pas). Le temoin de remplacement ecrit d'avance par `theo-archi` dans son `ADR`
(`score.tentatives()` vaut 1 apres deux coups simultanes) est bien celui qu'implemente
`DeuxCoupsSimultanesTest` apres fusion.

**Quatre jobs de la CI mesures sur l'arbre fusionne, nommes un par un** :

| Job | Ce que j'ai lance | Resultat |
|---|---|---|
| `prefixes` | `.github/scripts/verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 48635ba0..HEAD` passe a `verifier-prefixes.sh` | vert - tous les cas passent, tous les commits de s34 portent un prefixe d'agent connu |
| `chemins` | `.github/scripts/verifier-chemins-test.sh`, puis l'union de `git ls-files` et `git ls-files --others --exclude-standard` passee a `verifier-chemins.sh` | vert - aucun chemin de poste de travail ni nom de compte |
| `outils` | la boucle du job sur ses trois motifs | vert - **9 juges purs executes**, aucun motif vide, aucun echec |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | vert - `BUILD SUCCESS`, JDK 21.0.10 rapporte par la machine virtuelle des tests |

**Compte de tests sur l'arbre fusionne : 1464, sur 196 fichiers `TEST-*.xml`** (`domain` 111,
`application` 13, `adapter-web` 397, `adapter-content` 699, `bootstrap` 244). Deux methodes
independantes convergent sur ce chiffre : la somme de l'attribut `tests` des fichiers
`surefire-reports`/`failsafe-reports` sur le disque apres `mvn -B clean verify`, et les totaux
"Tests run" que Maven annonce par module dans un second `mvn -B verify` (sans `clean`, memes
resultats, 0 echec). **Ce n'est le chiffre annonce par aucune branche individuelle**, et ce n'est
pas cense l'etre : `lea-dev` mesure 1458 tests sur son propre arbre (base `d2b13635` a 1447,
`+11` sur son lot, avant fusion avec `s34-marc`, `s34-sofia` et `s34-theo`) ; `marc-dev` mesure un
delta net de zero sur l'un de ses commits, pas sur l'ensemble des quatre. Le chiffre qui compte
est celui-ci, mesure sur l'arbre fusionne apres la derniere fusion, pas la somme des annonces.

**Etat reel des stories du sprint apres cette fusion.** Payees : `S34-HG-12`, `S34-ANG-CORR`,
`S34-EMCCP07`, `S34-EXC-TACHEB` (`marc-dev`) ; `S34-EPS-SEC`, `S34-EPS-JEU`,
`S34-EPS-CORPS-EFFORT`, `S34-SCO1-REMEDE` (`sofia-sm`, avec l'`ADR` de `theo-archi`) ;
`S33-PAL2-TROU-A`, `S33-PAL2-TROU-B` et la moitie de `S33-PAL2-1` portant `QLM-CP-14` (`lea-dev`,
avec les trois dessins d'`ines-design`). **Non commencee** : `S34-CONS-4` (route en section 15).
**Non livree** : la moitie de `S33-PAL2-1` portant `QLM-CP-11` (route en section 15).
`S34-TRANSVERSE-1` est un poste d'audit seul, sans developpeur affecte - rien a fusionner. Aucun
mouvement de `squad/programme/MATRICE-couverture.md` n'est fait ici et aucun verdict n'est rendu :
les trois instances de `nadia-prof` n'ont pas encore audite ce sprint.

Rien n'est pousse.

## 15. Deux obstacles routes, casquette Scrum Master (2026-09-11)

### 15.1 `S34-CONS-4` - qui ecrit les quatre consignes, et le sprint qui la porte

**La contradiction est dans mon propre planning, pas dans l'arbitrage de `nadia-prof`, et c'est
moi qui la corrige.** La section 4 de ce fichier affecte `S34-CONS-4` a *"`marc-dev` (execution),
`theo-archi` (forme technique), `nadia-prof` (quatre consignes, audit)"* - une formulation qui
fait porter a `nadia-prof` la redaction des quatre textes. Son arbitrage prealable (section 4.2)
dit exactement l'inverse, et l'a dit avant que ce planning ne soit ecrit : *"Ce que je ne fais
pas, et c'est `RES-2` appliquee tout de suite : je n'ecris aucune des quatre consignes. Je nomme
la tache de chaque entree ; le texte revient devant moi quand il est ecrit, item devant lui."*
`marc-dev` a lu l'arbitrage plutot que la ligne fautive de ma propre table, a refuse d'inventer un
texte pedagogique destine a un enfant sans autorite pour le faire, et a escalade - c'est
exactement le comportement attendu, pas un blocage de sa part.

**Ce que je tranche, parce que c'est une affectation et non un jugement pedagogique** : `nadia-prof`
a deja fait le jugement pedagogique qui comptait - elle a nomme la tache de chacune des quatre
entrees (situer dans le temps, nommer en histoire, nommer en geographie, lire un document) et le
motif du decoupage. Ecrire la phrase de consigne qui porte ce nom, sur le gabarit deja en usage
dans tout le catalogue (*"Lis bien, puis dis..."*), est le meme geste d'execution que `marc-dev`
fait deja pour chacune des autres entrees qu'il livre ce sprint - il ne invente pas un jugement
pedagogique neuf, il met en mots une tache que `nadia-prof` a deja nommee. **`marc-dev` ecrit les
quatre consignes, sur le gabarit du catalogue et la tache que `nadia-prof` a nommee ; `nadia-prof`
les audite ensuite, texte devant items, comme `RES-3` l'exige pour toute reprise.** Je corrige la
section 4 de ce fichier dans le meme geste que ce routage.

**Cette story ne rentre plus dans ce sprint.** Les quatre branches sont fusionnees, la fenetre de
developpement de s34 est fermee : rouvrir un worktree pour `S34-CONS-4` maintenant reviendrait a
commencer un travail neuf apres la fusion, ce que je ne fais pas dans ce geste. Elle part a s35,
avec l'affectation corrigee ci-dessus.

### 15.2 `QLM-CP-11` (plan de la classe) - geometrie et plancher de lisibilite, pas un defaut de `lea-dev`

**Fait mesure par `lea-dev`, en jouant le jar (JDK 21.0.10), Chrome headless, gabarit et CSS
reels** : sur les six zones de `plan-classe.svg`, trois (`tableau`, `armoire`, `porte`) rendent un
libelle qui deborde de son bouton a l'ecran de manche et chevauche les zones voisines. Les cadres
en cause (`armoire`, 56 x 77 px ; `porte`, 63 x 56 px) sont des rectangles de meuble etroits ; le
plancher de police et le pave de `.zone` (`recreo.css`) ont ete penses pour des zones proches d'un
quadrillage, pas pour cette geometrie-la. `lea-dev` a retire `cp-plan-de-la-classe.yaml` et
`plan-classe.svg` de son commit plutot que de corriger une geometrie et un plancher qui ne sont pas
de son ressort - comportement attendu, pas un renoncement. Les deux fichiers restent sur le disque
de son worktree, non commites ; la table id/libelle/x/y/largeur/hauteur d'`ines-design`
(`proposition-ines-palier2-designation.md`, section 4) suffit a les reconstruire.

**Route, en deux temps disjoints** :

- **La geometrie** (les six rectangles de `plan-classe.svg`, en particulier `armoire` et `porte`,
  et les libelles positionnels qui doivent y tenir) : `ines-design`, autrice du dessin et du
  format technique de la classe `.image-designation`. Elle propose, elle ne tranche pas.
- **Le plancher de lisibilite** (la taille de police minimale et le pave de `.zone` en dessous
  duquel un libelle cesse d'etre lisible par un enfant de CP) : `nadia-prof`. Ce n'est pas une
  question de CSS mais de ce qui reste lisible pour l'age vise - la meme distinction que
  `CLAUDE.md` fait deja pour tout le contenu de CP (*"elle ne dispense d'aucune exigence de
  lisibilite"*).

**Ce que le poste du palier 2 devient.** `S33-PAL2-1` etait chiffre a 2,0 pour deux lignes
(`QLM-CP-11`, `QLM-CP-14`). Une seule est livree ce sprint. `DEC-0086` ne definit aucune methode
de proration d'un poste engage et partiellement livre - le meme principe que la section 3.2 de ce
fichier applique deja ailleurs (*"`camille-po` le dit elle-meme et je ne le comble pas a sa
place"*) : je ne divise pas les 2,0 en 1,0 paye plus 1,0 non depense, je constate le fait tel
qu'il est. `QLM-CP-14` est techniquement livree et attend son audit pedagogique ; `QLM-CP-11`
reste `non couvert`, engagee mais non livree, bloquee par un obstacle route ci-dessus et non par
un choix de `lea-dev`. Le mouvement de `squad/programme/MATRICE-couverture.md` et le compte final
du poste appartiennent a la review, pas a ce geste.

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise pendant ce planning : la
strategie (`DEC-0086`) est geree en amont par `camille-po`, l'arbitrage prealable en amont par
`nadia-prof`, et ce planning les applique sans les rouvrir. Deux amendements de process sont faits a
`squad/README.md` (section 12 ci-dessus, sections 4 et 9) ; ce ne sont ni des `ADR` ni des `DEC`.

## Prochaines etapes

- `theo-archi` commite la reprise des deux tests de `PaquetCpEmcRegleSouhaitLoiTest` sur
  `round-two`, avant que quiconque n'ouvre une branche sur `S34-EMCCP07` (section 8, point 1).
- `nadia-prof` rend son choix entre les sorties (a) et (b) sur `EMC-CP-07` (section 8, point 2),
  avant que `marc-dev` ne touche aux items 4, 5 et 6.
- `marc-dev` ouvre un worktree unique pour les terrains A et B : `S34-HG-12`, `S34-CONS-4`,
  `S34-ANG-CORR`, puis `S34-EMCCP07` (apres le prealable de `theo-archi` et le choix de
  `nadia-prof`), puis `S34-EXC-TACHEB`.
- `sofia-sm`, casquette dev, ouvre un worktree pour `S34-EPS-SEC`, `S34-EPS-JEU`,
  `S34-EPS-CORPS-EFFORT`, et pour la part d'implementation de `S34-SCO1-REMEDE` (les trois sessions
  du domaine et `DeuxCoupsSimultanesTest`), coordonnee avec l'`ADR` de `theo-archi`.
- `lea-dev` ouvre un worktree pour le chemin de jeu du palier 2 (`S33-PAL2-TROU-A`,
  `S33-PAL2-TROU-B`, `S33-PAL2-1`), avec `ines-design` pour les trois dessins.
- `theo-archi` ecrit l'`ADR` et la regle de non-regression de `S34-SCO1-REMEDE`, sans toucher aux
  trois sessions du domaine ni a `DeuxCoupsSimultanesTest` (section 7).
- Le daily suivant collecte l'avancement des quatre worktrees et l'etat des routages de la
  section 10 ; il n'est pas journalise.
