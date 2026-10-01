# Planning - s45 - addition avec retenue CE1, education musicale CP, roles d'EPS CM1 (instance A) et regles d'usage numerique CM1 (instance B)

- **Horodatage de debut** : 2026-10-01 10:58 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s45-addition-musique-eps-et-numerique-cm1`, dossier ouvert par ce planning. Aucun des
  documents prealables (`DEC-0101`, l'avis de faisabilite de `theo-archi`, l'arbitrage de
  `nadia-prof`) ne l'avait ouvert - tous le disent explicitement : l'ouverture du dossier de sprint
  est routee a `sofia-sm` (`DEC-0101`, section 8).

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`bash outils/verifier-lignee.sh round-two`) : `s45-sofia-planning` descend de `round-two` a
`5ef0ac7f` (dernier commit sur `round-two` au moment de l'ouverture, fusion du retrait de
`S45-LVE-ECRITURE`), sans aucun autre ecart.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0101`, Acceptee, amendee section 10 le 2026-10-01) : fermer quatre des cinq
lignes initialement engagees - `MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09` (instance A) et
`SCI-CM1-14` (instance B) - sur un dispositif a **deux instances paralleles de `nadia-prof`**,
**total reel du sprint 5,5/8,0**, pas 7,0/8,0 : la cinquieme ligne retenue par `DEC-0101` a sa
composition (`LVE-CM1-06`, `S45-LVE-ECRITURE`) a ete retiree le meme jour par amendement, au motif
`R6` (refus de la saisie sous correction automatique de clavier, arbitrage de `nadia-prof`,
`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 5). Si les quatre lignes ferment
integralement, la matrice de couverture passe de 104/156 (66,7 %, etat de sortie de s44) a 108/156
(69,2 %).

**Ce que ce planning ne rouvre pas** : `DEC-0101` elle-meme, ses tarifs, sa composition par
instance, ni le retrait de `S45-LVE-ECRITURE`. Les quatre lignes sont deja verifiees
`non couvert` dans `MATRICE-couverture.md` (verification repetee par `camille-po` dans `DEC-0101`,
section 2, et reconfirmee par l'avis de faisabilite de `theo-archi`) et resolues ligne par ligne,
citation comprise, par l'avis de faisabilite et par l'arbitrage prealable.

## 3. Deux instances non chevauchantes, `PAR-2` verifie explicitement pour ce planning

`DEC-0101`, section 4, et le backlog (`docs/backlog/backlog.md`, section "Sprint 45") verifient
deja `PAR-2` sur l'ensemble du perimetre engage. Je le reverifie ici sur le perimetre reel, apres
retrait de `S45-LVE-ECRITURE`, avant d'affecter un seul developpeur :

| Instance | Lots | Matiere / niveau |
|---|---|---|
| A | `S45-MAT-ADDITION`, `S45-MUS-INSTRUMENTS`, `S45-EPS-ROLES` | mathematiques CE1, education musicale CP, EPS CM1 |
| B | `S45-SCI-NUMERIQUE` | sciences et technologie CM1 |

Quatre couples matiere/niveau distincts, aucun partage entre les deux instances : **`PAR-2` tenu**.
Aucune contrainte de collision ne pese donc sur l'affectation nominative des developpeurs
(section 5) : `PAR-2` gouverne le partage entre instances de `nadia-prof`, pas la charge d'un
developpeur, et rien n'empeche un meme developpeur de coder un lot de chaque instance - verifie
explicitement parce que la commande de ce planning le demandait, pas parce qu'un doute existait.

## 4. Stories deja instruites, avis de faisabilite et arbitrage prealable deja rendus - rien n'est bloquant

Les quatre stories sont ecrites avec criteres d'acceptation dans `docs/backlog/backlog.md`,
section "Sprint 45". La chaine complete, deja fusionnee sur `round-two` avant ce planning :

| Ordre | Fichier | Ce qu'il leve |
|---|---|---|
| 1 | `DEC-0101` (`14237f23`/`c15a6c4d`) | perimetre et tarif des cinq lignes initiales |
| 2 | Avis de faisabilite `theo-archi` (`19a17590`/`c6dcba81`) | verdicts et conditions encodables des cinq lots, questions Q1 a Q8 routees |
| 3 | Corrections `camille-po` (`98573783`/`66930b93`) | Q2 (`MAT`), Q6 (`LVE`), Q7 (`SCI`) : les trois stories concernees sont corrigees contre leurs sources dans le backlog |
| 4 | `ADR-0103` `theo-archi` (`ccfe7df1`/`7c2b091f`) | restreint le perimetre du garde-fou `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` aux paquets qui declarent `MAT-CE1-03`, condition de faisabilite de `S45-MAT-ADDITION` |
| 5 | Arbitrage prealable `nadia-prof` (`a56b981a`/`e79204c4`) | Q1 (definition des trois erreurs d'`ADP-CE1-5`), Q3 (forme d'enonce interdite sur `MUS-CP-05`), Q4 (`EPS-10`, discriminant CM1/CP), Q5 (`LVE-CM1-06` refusee) |
| 6 | Retrait `camille-po` (`5b8ca0f8`/`5ef0ac7f`) | `S45-LVE-ECRITURE` sort du perimetre ; decouverte retroactive routee a part (section 7) |

**Consequence pour ce planning : les quatre lots restants n'ont plus aucun prealable bloquant.**
Chaque question qui conditionnait un verdict (Q1, Q4, Q5) ou le texte d'une story (Q2, Q6, Q7) a
ete levee et fusionnee avant l'ouverture de ce dossier. Ce n'est pas le patron de s44
(`S44-SCI-OBJET-TECHNIQUE` affectee avec premier commit differe) : ici, rien ne differe le premier
commit d'aucun des quatre lots.

| Lot | Ligne | Instance | Tarif | Verdict `theo-archi` | Source |
|---|---|---|---|---|---|
| `S45-MAT-ADDITION` | `MAT-CE1-04` | A | 1,0 | **Faisable**, `ADR-0103` leve la seule condition bloquante | avis, section 4 ; `ADR-0103` |
| `S45-MUS-INSTRUMENTS` | `MUS-CP-05` | A | 1,5 | **Faisable en l'etat**, basse complexite | avis, section 5 |
| `S45-EPS-ROLES` | `EPS-CM1-09` | A | 1,5 | **Faisable**, condition de verdict (discriminant `EPS-10`) levee par `nadia-prof` | avis, section 6 ; arbitrage, section 4 |
| `S45-SCI-NUMERIQUE` | `SCI-CM1-14` | B | 1,5 | **Faisable**, story corrigee par `camille-po` | avis, section 8 ; backlog corrige |

Porte technique : aucune mesure `mvn verify` n'est due a ce planning sur ces quatre lots, aucun
code de production n'a encore ete touche par aucun d'eux. L'avis de faisabilite rapporte en
revanche sa propre porte technique sur son diff (section 14) : `bash outils/mvn-jdk21.sh -B clean
verify`, `BUILD SUCCESS`, job `chemins` vert en local, `prefixes` du par la CI sur chaque commit.

## 5. Pieges de fabrication nommes par `theo-archi`, a transmettre au developpeur affecte

Repris tels quels de l'avis de faisabilite (section 10) et de l'arbitrage prealable, pour que
chaque developpeur ne les redecouvre pas a l'audit :

### `S45-MAT-ADDITION` (`MAT-CE1-04`)

- **La definition de la retenue oubliee, tranchee par `nadia-prof`** (arbitrage, section 2.2) :
  `48 + 26 -> 64` est la lecture juste (chaque colonne additionnee separement, le chiffre des
  dizaines de la somme d'une colonne est perdu). `614` est une erreur de redaction de
  `nadia-prof` elle-meme, exclue de toute facon par `ADP-CE1-4` (elle change le nombre de
  chiffres). Les trois formes (retenue oubliee, reportee deux fois, mauvaise colonne - toujours
  celle des centaines, jamais celle des dizaines) sont definies colonne par colonne, section 2.2
  de l'arbitrage, avec un tableau d'exemples chiffres a reprendre tel quel.
- **Le garde-fou `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` ne rougit plus** une
  fois `ADR-0103` applique, **a condition que le paquet declare `MAT-CE1-04` dans
  `lignesDeReferentiel` et non `MAT-CE1-03`**. Un paquet qui redeclarerait `MAT-CE1-03` entrerait
  dans la population du garde-fou et y serait rouge par construction (somme toujours superieure a
  20, aucune somme de 20 sans retenue possible sous `ADP-CE1-2`).
- **`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`** exige une dixieme entree dans
  `SousPartieDeMathematiquesCe1` portant `MAT-CE1-04`, dans le meme diff que le paquet (avis,
  section 4.2).
- **Le garde-fou de rang** (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) contraint
  le choix des distracteurs : sur un item a deux chiffres, la bonne reponse est toujours au rang
  du milieu ; il faut au moins deux items (sur 10 %) ou la bonne reponse est la plus grande ou la
  plus petite, ce qui demande des items a trois chiffres et deux retenues (avis, section 4.4 ;
  arbitrage, section 2.4).
- **Collision verifiee par `theo-archi`** : `ce1-additions-jusqu-a-100` (`MAT-CE1-03`) ne sert
  aucun couple a deux chiffres identique a ceux du paquet neuf (avis, section 4.5), aucune
  verification supplementaire n'est due.

### `S45-MUS-INSTRUMENTS` (`MUS-CP-05`)

- **Ne pas recopier le lexique interdit de s44.** `MOTS_INTERDITS_SOUS_CHAINE` contient `frapp` et
  `taper` sur le paquet de s44 (`MUS-CP-11`) : une percussion se definissant comme un instrument
  qu'on frappe, recopier ces interdits rendrait tout enonce sur les percussions faussement rouge
  (avis, section 5, risque R4). Garder `adulte`, `fredonn` ; retirer `frapp` et `taper` du lexique
  interdit de ce paquet-ci.
- **La forme "cordes ou vent ?" n'est pas admise** (`nadia-prof`, arbitrage, section 3, en reponse
  a Q3) : elle nomme deux des trois propositions dans l'enonce, la troisieme (percussions)
  s'ecartant a la lecture - c'est la famille de `MUS-CP-R1`. L'exemple du referentiel est deja
  remplace par `nadia-prof` par *"De quelle famille d'instruments est la guitare ?"*.
- Collision verifiee nulle sur les 106 paquets servis (avis, section 5) ; attention a l'adjacence
  de sens nommee : `"un triangle"` designe une figure plane au CP ailleurs dans le catalogue,
  distincte de l'instrument de percussion si le paquet neuf l'emploie.

### `S45-EPS-ROLES` (`EPS-CM1-09`)

- **Le discriminant `EPS-10`, pose par `nadia-prof`** (arbitrage, section 4.2), quatre exigences
  opposables a l'audit :
  1. aucun item ne porte sur un des six jeux du paquet de CP (`cp-eps-regles-jeu-collectif`) ;
     necessaire, pas suffisant, c'est la tache qui compte, pas la phrase ;
  2. **la bonne reponse est l'acte du role, jamais ce que devient un joueur** - arbitre : une
     decision qui porte sur les equipes ; observateur : ce qu'il releve selon un critere donne,
     jamais une appreciation ni une comparaison entre enfants ; marqueur : a quelle equipe va le
     point ou le score apres une seule action, la difficulte etant la regle, jamais le calcul ;
  3. chacun des trois roles porte au moins trois items dont la bonne reponse est son acte ;
  4. `CYC-1` dans les deux sens : aucune bonne reponse du paquet de CP n'est un distracteur du
     paquet de CM1, et inversement.
- **`FER-1` se lit different de `MUS-CP-11`** : le role est **porte par l'enonce**, ce qui est en
  position de bonne reponse c'est **l'acte du role**. Un item dont la bonne reponse est le nom du
  role (*"Qui compte les points ? - le marqueur"*) ne compte pour aucun role au titre de `FER-1`.
- Trois exemples qui tiennent `EPS-10` sont donnes par `nadia-prof` (arbitrage, section 4.4,
  table), a titre d'exemples, pas de textes imposes.

### `S45-SCI-NUMERIQUE` (`SCI-CM1-14`)

- La story est deja corrigee dans le backlog (Q7 levee par `camille-po`) : `SCI-1` vise
  l'`indice` et non l'`explication`, `SCI-4` (trois propositions au moins sur un item a conception
  fausse), `FER-1` a `FER-3`, `HIS-3`, `CON-1` sont tous cites. **Ne pas refaire l'erreur de s44**
  en remplissant `explication` plutot que l'`indice`.
- **`SCI-3`** (valeur numerique restituee sur au plus un item sur six) est deja plus tendu que sur
  les lots precedents : un mot de passe ou un age d'acces a un service sont des valeurs a
  restituer, a ne pas sur-representer (avis, section 8.2).
- **Le piege `HIS-1` de s44 vaut toujours** : zero marqueur `# HIS-1:` sur les 13 paquets de
  sciences CM1 servis. Un marqueur recopie de l'histoire-geographie soumettrait tout le lot a
  `ADR-0079` (avis, section 8.2).
- `SCI-2` est appliquee par extension (la source la borne aux lignes 16 et 18, pas a la ligne 14) :
  defendable, le backlog le dit explicitement, reste a confirmer par `nadia-prof` a l'audit.

### Pieges communs aux quatre lots

- `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : les trois paquets textuels (`MUS`, `EPS`, `SCI`) y
  entrent, jamais le paquet de `MAT` (avis, section 10).
- `TexteAccentueExposeParLePortEntrantTest` : toute chaine de descripteur neuve doit y passer.
- `AucunTitreDeJeuNePorteSonNiveauTest` : aucun titre ne porte `CP`, `CE1` ni `CM1` ; le titre
  "Les grandes additions" est deja pris par `MAT-CE1-03`.
- Le test de chaque paquet est ici la seule garde automatique des conditions listees ci-dessus :
  aucune `ArchRule` neuve n'est due au-dela de celles deja fusionnees avec `ADR-0103`.

## 6. Affectations nominatives

`docs/backlog/backlog.md` (section "Sprint 45") propose la repartition du developpement par
`camille-po`, affectation nominative finale par `sofia-sm` (`DEC-0101`, section 8). Decision de
capacite : quatre lots, trois developpeurs - un developpeur en prend deux. Je retiens `MAT`
(complexite moyenne : un `ADR` deja leve, un test de paquet calculable) et `EPS` (moyenne sur le
test, discriminant `EPS-10` a quatre exigences) pour un developpeur chacun, et je regroupe les
deux lots de basse complexite mecanique (`MUS`, `SCI`) sur le mien, sous ma casquette dev, l'un
par instance.

| Lot | Ligne | Instance | Developpeur | Peut demarrer l'ecriture de code des maintenant ? |
|---|---|---|---|---|
| `S45-MAT-ADDITION` | `MAT-CE1-04` | A | `marc-dev` | **Oui** |
| `S45-EPS-ROLES` | `EPS-CM1-09` | A | `lea-dev` | **Oui** |
| `S45-MUS-INSTRUMENTS` | `MUS-CP-05` | A | `sofia-sm` (casquette dev) | **Oui** |
| `S45-SCI-NUMERIQUE` | `SCI-CM1-14` | B | `sofia-sm` (casquette dev) | **Oui** |

**Pourquoi `sofia-sm` prend les deux lots de basse complexite mecanique.** `MUS` (avis, section 5 :
"basse") et `SCI` (avis, section 8 : "basse sur le mecanisme") sont tous deux de simples paquets
de choix textuel, sans code de production attendu, sur le meme patron que
`PaquetCpMusiqueVocabulaireTest` et `PaquetCm1SciencesEvolutionObjetTechniqueTest`. Aucun des deux
ne porte de prealable restant, contrairement a `MAT` qui vient de recevoir son `ADR` et depend du
tableau de rang de la section 5. `marc-dev` et `lea-dev` prennent chacun un seul lot de complexite
moyenne sur le test, sans dependance externe a leur premier commit.

**Aucune collision entre les deux lots de `sofia-sm`** : `MUS-CP-05` (instance A) et
`SCI-CM1-14` (instance B) sont deux couples matiere/niveau distincts, verifies sans objet partage
a la section 4.1 de `DEC-0101` (reverifie ici, section 3). Les deux Done pedagogiques restent
portes par deux instances differentes de `nadia-prof`, qui n'auditent chacune que leurs lots.

## 7. Deux instances de `nadia-prof` - Done pedagogique en fin de sprint, chacune sur son perimetre seul

**Instance A** audite `S45-MAT-ADDITION`, `S45-MUS-INSTRUMENTS` et `S45-EPS-ROLES`. Elle porte,
avant d'auditer, les reponses qu'elle a elle-meme donnees aux conditions de verdict de ces trois
lots (Q1, Q3, Q4, section 4). Budget individuel 5,0, cout retenu 4,0, marge 1,0.

**Instance B** audite `S45-SCI-NUMERIQUE` seule. Budget individuel 5,0, cout retenu 1,5, marge
3,5. **Instance A n'audite jamais un lot de l'instance B, et reciproquement** - c'est la regle que
`DEC-0098` pose pour tout dispositif a deux instances, et qu'aucun document de s45 ne touche.

Budget de sprint opposable 8,0, cout retenu 5,5, marge de sprint 1,0 non allouee + 2,5 liberes sur
l'instance B par le retrait de `S45-LVE-ECRITURE`, avant la borne de sprint (`DEC-0101`, section
10 ; backlog, section "Sprint 45").

## 8. Decouverte retroactive signalee, hors perimetre de ce planning

`S46-VERIF-SAISIE-MOT-DICTE-CP` (priorite haute, `docs/backlog/backlog.md`, section "Backlog
ouvert pour s46 et au-dela") est deja ouverte : `nadia-prof` a signale, en verifiant si `R6`
s'appliquait a `LVE-CM1-06`, que le motif n'avait jamais ete examine sur `saisie-mot-dicte-cp`,
`couvert-valide` depuis s26. Je la cite ici comme obstacle ouvert hors perimetre, sans la traiter
dans ce planning : aucun lot de s45 ne la porte, et ce n'est pas `sofia-sm` qui statue sur un Done
pedagogique deja prononce - seule `nadia-prof` le fait, dans un sprint distinct.

## 9. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S45-MAT-ADDITION` (`MAT-CE1-04`) | `marc-dev`, puis `nadia-prof`, instance A | Engagee |
| `S45-MUS-INSTRUMENTS` (`MUS-CP-05`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance A | Engagee |
| `S45-EPS-ROLES` (`EPS-CM1-09`) | `lea-dev`, puis `nadia-prof`, instance A | Engagee |
| `S45-SCI-NUMERIQUE` (`SCI-CM1-14`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance B | Engagee |

Aucun element n'est "non engage, budget libere" : les quatre lots retenus apres le retrait de
`S45-LVE-ECRITURE` sont tous affectes. La marge de 2,5 sur l'instance B (section 7) n'est engagee
sur aucun lot nouveau par ce planning.

## 10. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : `ADR-0103` est deja ecrit et fusionne
  avant ce planning (section 4), rien d'autre n'est du sur ce perimetre selon l'avis de
  faisabilite (section 9 de l'avis).
- Il ne corrige aucun texte de story : les trois corrections dues (`MAT`, `SCI`, et celle, devenue
  sans objet, de `LVE`) sont deja fusionnees par `camille-po` avant ce planning.
- Il ne pose aucun discriminant pedagogique : `EPS-10` et la definition des trois erreurs
  d'`ADP-CE1-5` sont deja poses par `nadia-prof` (section 4, section 5).
- Il ne rouvre pas `DEC-0101`, ni son amendement de retrait.
- Il ne traite pas `S46-VERIF-SAISIE-MOT-DICTE-CP` (section 8).
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.

## Decisions prises

Aucune `DEC` ni `ADR` n'est cree ou amende par ce planning. Deux decisions m'appartiennent en
propre et sont ecrites ici : la decision de capacite de la section 6 (deux lots de basse
complexite mecanique regroupes sur `sofia-sm`, casquette dev, un par instance) et l'affectation
nominative qui en decoule. `DEC-0101` (objectif, perimetre et retrait), l'avis de `theo-archi`,
`ADR-0103` et l'arbitrage prealable de `nadia-prof` existent deja avant ce planning et ne sont pas
amendes par lui.
