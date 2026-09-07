# DEC-0081 - Strategie de s31 : composition 1 a quatre lots (EMC CP restreint, sciences CM1 second passage), la borne reste a 8,0, `CAT-1` trouvee en jouant reste impayee sur les deux lignes d'EMC deja revendiquees

- **Type** : arbitrage de l'utilisatrice, enregistre et non rouvert ; decisions de perimetre et de
  chiffrage de `camille-po` qui en decoulent.
- **Date** : 2026-09-07
- **Auteur** : `camille-po`
- **Sprint** : s31 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision arbitre
  un perimetre, une repartition de sprint et une allocation de codes de referentiel ; les
  competences ligne a ligne visees par les quatre lots sont nommees dans `docs/backlog/backlog.md`,
  stories `S31-1` a `S31-4`, et chiffrees par `nadia-prof` dans son arbitrage prealable.
- **Statut** : Acceptee.
- **Lien** :
  `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`
  (`nadia-prof`, arbitrage prealable complet - chiffres et conditions repris sans etre recalcules),
  `squad/sprints/s31-second-passage-de-sciences-et-emc-cp/avis-faisabilite-theo.md` (`theo-archi`,
  avis de faisabilite technique), `DEC-0074` (`nadia-prof` ecrit les referentiels absents dont EMC
  CP et sciences CM1), `DEC-0077` (`PAR-9`, budget opposable par instance), `DEC-0079` et `DEC-0080`
  (strategie et cloture de s30, `PAR-10`, `PAR-11`), `ADR-0081` (codes de ligne de referentiel),
  `ADR-0085` (`PAR-5`, registre des identifiants de condition), `squad/programme/MATRICE-couverture.md`,
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`,
  `squad/programme/sciences-et-technologie/competences-cm1.md`, les huit referentiels sans code cites
  en section 6, `docs/backlog/backlog.md` (section "Sprint 31").

---

## 1. L'arbitrage de l'utilisatrice, rendu le 2026-09-07

Objectif nomme par elle : **la meilleure couverture programme possible.** Elle retient la
**composition 1** de `nadia-prof` (quatre lots), et non la composition 2 que `nadia-prof`
recommandait (trois lots, six lignes, la passe transverse financee). Elle **ne prend pas** le +1,0
de borne que `nadia-prof` a calcule en corrigeant sa propre double soustraction et refuse pour
elle-meme (son arbitrage, section 1.1) : **la borne de s31 reste a 8,0**, chiffre empirique et non
derive, tenu par trois sprints mesures (s28 : 8,25 ; s29 : 9,50 ; s30 : 8,50).

**Composition 1, telle que chiffree par `nadia-prof` (section 7.3 de son arbitrage)** :

| Instance | Postes | Total |
|---|---|---|
| **A** | 2 lots EMC CP tous deux restreints aux lignes 1 a 7 (1,0 + 1,0) + marge `PAR-11` (1,0) + bloc `melanges` (0,5) + bloc `objet-technique` (0,5) | **4,0** |
| **B** | 2 lots sciences CM1 (1,5 + 1,5) + marge `PAR-11` (1,0) | **4,0** |
| | | **8,0** |

Le budget opposable par instance reste 4,0, marge de rejeu comprise (`PAR-9`), plafond dur
individuel de 5,0 inchange (`PAR-8`). La composition sature exactement la borne : **aucune marge
n'est disponible au-dela des 8,0 pour un geste qui n'y figure pas** - point qui gouverne la decision
sur `SAI-R1` en section 5.

## 2. Le plafond arithmetique et le taux cible

Trois lignes du dispositif de mesure, reprises sans etre recalculees (`FER-2`, `ADR-0081` clause 5,
`DEC-0064`) : un lot de 22 items ferme deux lignes au plus. Composition 1 porte quatre lots, donc
**huit lignes au plus**.

> **Plafond arithmetique de s31 : 8 lignes. Base 56 / 138 (etat a la cloture de s30) + 8 = 64 / 138
> = 46,4 %.**
>
> **Taux cible que porte ce document : l'attente raisonnable de `nadia-prof`, 5 a 7 lignes, soit 61
> a 63 / 138 = 44,2 % a 45,7 %.** Elle ecrit elle-meme s'etre trompee deux fois de suite dans le
> sens de la sous-estimation (s29, s30) et ne monte pourtant pas son attente au plafond : le motif
> est arithmetique, pas prudentiel - les quatre lignes d'EMC de la composition 1 doivent toutes
> venir d'un groupe de quatre sans remplacant (section 4), dans une matiere dont le taux de refus au
> premier passage mesure est de quatre paquets sur cinq (s29).

## 3. Ce que la composition finance, et ce qu'elle laisse impaye

**Ce qu'elle achete** : le plafond maximal de lignes atteignable ce sprint, et le paiement integral
du groupe 1 du tri des dix-sept reserves de s30 - les deux seules qui enseignent quelque chose de
faux (`MEL-R1`, `OBT-R1`, section 6.1 de l'arbitrage de `nadia-prof`, 1,0, qui solde par `PAR-10`
cinq reserves en tout : `MEL-R1`, `MEL-R2`, `MEL-R3`, `OBT-R1`, `OBT-R2`).

**Ce qu'elle ne finance pas, et cela doit rester ecrit sans etre adouci :**

- **La passe transverse** (`PAR-4` en aurait fait une tache payee ; il n'y a pas 0,5 pour elle dans
  cette composition).
- **`REG-R1` et `PER-R1`**, differees une troisieme fois - s29 vers s30, puis s30 vers s31 - sur les
  deux seules lignes d'EMC CP que le produit revendique aujourd'hui (`EMC-CP-02`, `EMC-CP-06`,
  toutes deux `couvert-valide`). Chiffrees 0,5 chacune par `nadia-prof`, 1,0 au total.
- **`CAT-1`**, qui voyageait gratuitement avec `REG-R1` et `PER-R1` (`PAR-10`) et reste donc, elle
  aussi, impayee. Voir section 4 : c'est le point le plus serieux de ce sprint.
- **La branche des dix reserves qui restent gagnables sans la competence sur des lignes deja
  `couvert-valide`** (groupe 2a du tri de `nadia-prof`, section 6.2 de son arbitrage) : `SIG-R1`,
  `SIG-R2`, `MEL-R2`, `MEL-R3`, `POS-R3`, `CHA-R1`, `OBT-R2`, `HG-R8`, `FOP-R4`, `DRO-R4`. **Chiffree
  3,0 par `nadia-prof`, dont 1,5 solde gratuitement des lors que le groupe 1 est paye** (`MEL-R2`,
  `MEL-R3` et `OBT-R2` voyagent avec `MEL-R1` et `OBT-R1` dans le meme rejeu) - le residu reel non
  couvert par cette composition est donc de l'ordre de 1,5, porte par `SIG-R1`, `SIG-R2`, `POS-R3`,
  `CHA-R1`, `HG-R8`, `FOP-R4` et `DRO-R4`. **Je l'ecris ainsi pour que cette branche soit ecartee de
  s31 en connaissance de cause, comme `nadia-prof` le demande, et non par omission.**
- **Le groupe 2b** (lisibilite, consigne, gabarit : reliquat de `CONS-1`, `CONS-2`, `CONS-3`,
  `SAI-R1`, `ERR-R1`), 1,0 chiffre plus `CONS-3` non chiffree. Voir section 5 pour `SAI-R1`
  specifiquement.

**Dette reelle a l'ouverture de s31, telle que `nadia-prof` la totalise** : au moins 6,0 sur une
borne de 8,0 (5,0 des dix-sept reserves de s30, plus 1,0 pour `REG-R1`/`PER-R1`, plus `CONS-3` non
chiffree). La composition 1 n'en paye qu'une fraction (1,0, le groupe 1) dans le meme geste
qu'elle ouvre quatre lignes neuves.

## 4. `CAT-1` - le point le plus serieux de ce sprint, et ce qu'il devient

**Le fait, tel que `nadia-prof` l'a trouve en jouant le 2026-09-07** (section 2 de son arbitrage) :
**les deux seules lignes d'EMC CP que le produit revendique aujourd'hui** (`EMC-CP-02`,
`Distinguer ce qui est permis de ce qui est interdit...`, et `EMC-CP-06`, `Connaitre les regles de
vie...`) **sont servies par des paquets dont la tache principale se gagne au ton** : sur les six
items qu'elle a relus a l'ecran, la bonne reponse est systematiquement la seule proposition qui
sonne comme l'ecole (*"en marchant"*, *"pour ne pas lui faire mal"*), et les distracteurs les seules
qui sonnent comme la faute. Un enfant qui n'a jamais entendu la regle gagne en reconnaissant le
registre attendu - c'est `CON-1` (l'item se gagne sans la competence visee), et c'est exactement le
**catechisme civique** qu'elle avait signale par ecrit le 2026-09-03, quatre jours avant qu'un seul
item d'EMC n'existe. **Son propre audit de s29, qui a ferme ces deux lignes, ne l'a pas vu.**

`nadia-prof` **ne rouvre ni l'une ni l'autre ligne** : elles restent `couvert-valide`, le poteau
(`CAT-1`) est neuf et date d'aujourd'hui, il ne se retourne pas contre une barre franchie il y a
deux jours. Elle ne l'ajoute pas non plus aux dix-sept reserves de s30, dont il n'est pas issu.

**Ce que cette decision en fait, sans l'adoucir :**

1. **Le produit ouvre quatre lignes d'EMC de plus ce sprint sur une fondation dont un defaut connu
   n'est pas paye.** `REG-R1` et `PER-R1` restent differees (section 3) et `CAT-1` voyage avec elles
   sans etre financee. Ce n'est pas une omission : c'est le cout explicite de la composition 1, et
   il est ecrit ici pour que personne ne le decouvre a la cloture.
2. **`CAT-1` entre au backlog comme story pour s32**, et non pour s31 : voir `docs/backlog/backlog.md`,
   section "Backlog ouvert pour s32", story `S32-CAT1`. Elle porte le reaudit de `EMC-CP-02` et
   `EMC-CP-06` contre la clause `CAT-1` telle que `nadia-prof` l'a redigee (section 2 de son
   arbitrage), en meme temps que `REG-R1` et `PER-R1` puisque les trois portent sur les deux memes
   paquets et se paient dans le meme rejeu (`PAR-10`).
3. **`CAT-1` est opposable des ce sprint aux quatre paquets neufs d'EMC CP** (`S31-1`, `S31-2`),
   au meme titre que trois autres clauses datees du 2026-09-07 par `nadia-prof` : `FAB-1` (une regle
   de fabrication ne se repete pas a l'interieur d'une tache), `PLA-1` (une proposition est un
   candidat, jamais un remplissage) et `BIN-1` (une partie ne se joue pas majoritairement a pile ou
   face). Les quatre sont reprises dans les criteres d'acceptation des stories concernees.

## 5. `SAI-R1` : la story n'entre pas dans le perimetre de s31

**Ce que `theo-archi` a instruit** (section 3.3 de son avis) : la cause est trouvee
(`fragments/saisie.html:120`, un point final ajoute a une chaine dont le gabarit ignore la forme),
le correctif est de douze caracteres, le rayon est mesure sur cinq concatenations de gabarit dont
une seule est fautive, et deux assertions de `EcranDeSaisieTest` sont a mettre a jour. Le prix
publie d'avance par `nadia-prof` est 0,25 (mot pour mot une fois le correctif livre).

**Decision : `SAI-R1` n'entre pas dans le perimetre de s31.** Deux motifs, tous deux tenus contre le
chiffrage de la section 1 :

1. **La composition 1 sature exactement la borne de 8,0** : les deux instances planifient 4,0/4,0
   sans aucune marge residuelle. Ajouter 0,25 discretionnaires n'est finançable qu'en depassant la
   borne, exactement le geste qui a produit chacun des trois depassements consecutifs precedents
   (s28, s29, s30).
2. **La marge `PAR-11` de cette composition (1,0 par instance) est deja jugee sous-dimensionnee par
   sa propre auditrice** pour l'exposition reelle de l'Instance A : `nadia-prof` ecrit (section 7.1
   de son arbitrage) qu'au taux de refus au premier passage mesure sur l'EMC a s29 (quatre paquets
   sur cinq), il faudrait 1,5 a 2,0 de reaudit contre 1,0 de marge disponible, et que la meme marge
   couvre a la fois les reaudits des lots neufs et la reverification d'un correctif transverse
   comme `SAI-R1`. Empiler une consommation discretionnaire de plus sur une marge deja signalee
   comme insuffisante par celle qui doit l'auditer est le geste que je refuse de reproduire.

`SAI-R1` reste au backlog, chiffree 0,25, prete a etre prise sans travail supplementaire des
qu'un sprint dispose d'une marge non consommee ou a l'ouverture de s32. Elle n'est pas reportee une
seconde fois au sens de la gouvernance de ce depot (elle est entree au backlog a la cloture de s30 -
`docs/backlog/backlog.md`, entree `SAI-R1` - et c'est la premiere fois qu'elle est examinee pour un
perimetre de sprint) : ce n'est donc pas un troisieme report a compter, seulement un premier arbitrage
de planification, ecrit pour ne pas se transformer en report silencieux si elle revenait a s32 sans
etre prise.

## 6. Allocation des codes de ligne des huit referentiels qui n'en portaient aucun

**Fait, execute dans le meme geste que la presente decision**, comme `DEC-0079` l'avait fait pour
les libelles de sciences CM1 avant l'ouverture de s30 : une allocation de code n'est pas un jugement
pedagogique, c'est une ecriture de referencement qui rend une ligne citable par un paquet
(`ADR-0081`). Elle ne cree aucun paquet, ne prononce aucun Done pedagogique et ne fait bouger aucun
statut de `squad/programme/MATRICE-couverture.md`.

**Le geste est pris**, pour le motif que `theo-archi` donne : cout d'audit nul (zero ligne de code,
zero `ADR`), et huit matieres qui restent aujourd'hui **fermees par le code, pas par la
pedagogie** - `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` refuserait tout paquet
ecrit sur l'une de leurs lignes tant qu'aucun code n'existe.

**Une correction au chiffre transmis, trouvee en lisant les huit fichiers avant d'y ecrire** :
`theo-archi` mesure 81 lignes sans code par un comptage brut de toute puce commencant par `- `
(section 4 de son avis). **Le chiffre reel est 79.** L'ecart de deux vient de
`squad/programme/education-musicale/competences-cm1.md`, ou deux puces d'une note de bas de page
("**reversible** - une decision..." / "**irreversible** - meme avec le son...") sont formatees en
liste mais ne sont pas des lignes de competence : ce fichier porte dix lignes de referentiel, pas
douze, comme son propre bilan chiffre le dit explicitement ("Lignes du referentiel : 10"). Les sept
autres fichiers sont conformes au compte de `theo-archi` (verifie un par un, aucun autre ecart).

**Ce que ce sixieme ecart dit du procede, pas seulement du chiffre.** `sofia-sm` le signale : c'est le sixieme comptage a la main corrige dans ce depot en trois sprints. Il n'a pas ete trouve par le comptage mecanique de `theo-archi` (un grep sur toute puce commencant par `- `, sans distinguer une ligne de competence d'une puce de prose) : il a ete trouve **en executant le geste d'allocation**, qui oblige a regarder chaque puce une a une pour lui donner un code, la ou un comptage brut ne le fait pas. `theo-archi` en tire une consequence outillable, rapportee par `sofia-sm` et non encore ecrite dans un fichier que je peux citer a la date de cette decision : un garde-fou de coherence des bilans chiffres - qui comparerait le compte que chaque referentiel affiche lui-meme a celui de ses propres puces de competence - est encodable sous condition de normalisation, et il aurait attrape cet ecart-la sans qu'un agent ait besoin de le trouver a la main. Je le route pour memoire ; ce n'est pas mon geste de l'encoder.

**Codes alloues, par referentiel, dans l'ordre de lecture de chaque fichier** :

| Referentiel | Prefixe retenu | Codes | Motif du prefixe |
|---|---|---|---|
| Arts plastiques CP | `ART` | `ART-CP-01` a `ART-CP-11` | Deja utilise comme prefixe de condition (`ART-1` a `ART-5`) ; pas de collision, un code de ligne a trois segments et une condition en a deux (`theo-archi`, section 4) |
| Arts plastiques CM1 | `ART` | `ART-CM1-01` a `ART-CM1-10` | Idem |
| Education musicale CP | `MUS` | `MUS-CP-01` a `MUS-CP-11` | Idem, deja prefixe de condition (`MUS-1`, `MUS-2`) |
| Education musicale CM1 | `MUS` | `MUS-CM1-01` a `MUS-CM1-10` | Idem. Dix codes, non douze (correction ci-dessus) |
| Education physique et sportive CP | `EPS` | `EPS-CP-01` a `EPS-CP-11` | Idem, deja prefixe de condition (`EPS-1` a `EPS-3`) |
| Education physique et sportive CM1 | `EPS` | `EPS-CM1-01` a `EPS-CM1-10` | Idem |
| Histoire des arts CM1 | `HDA` | `HDA-CM1-01` a `HDA-CM1-10` | Prefixe libre, verifie contre le registre `PAR-5` (`ADR-0085`) et contre l'ensemble des prefixes de ligne deja alloues (`EMC`, `FRA`, `HG`, `LVE`, `MAT`, `QLM`, `SCI`) : aucune collision |
| Langues vivantes (anglais) CP | `LVE` | `LVE-CP-01` a `LVE-CP-06` | Le prefixe `LVE` sert deja les sept lignes de `langues-vivantes/competences-cm1.md`. Retenu par coherence plutot qu'un prefixe `ANG`, qui prejugerait d'une langue que le fichier lui-meme laisse non tranchee (voir sa note "Point de nommage non tranche") |

**Verifie contre le garde-fou** : `bash outils/mvn-jdk21.sh -pl bootstrap -am
-Dtest=ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest
-Dsurefire.failIfNoSpecifiedTests=false test`, execute depuis mon propre worktree
(`s31-camille`, `2ef583ba`) : 12 tests verts, aucune faute de forme, aucun code declare deux fois,
aucun ecart de niveau entre un code et le fichier qui le porte.

**Ce que ce geste n'est pas** : une decision d'ouvrir l'une de ces huit matieres a s31 ni a s32, ni
un jugement sur la valeur des 79 lignes desormais citables (22 atteignables selon `theo-archi`, dont
7 en histoire des arts). Cette priorisation reste entierement a `camille-po` et `sofia-sm`, pour un
sprint futur.

**Trace hors story, dans le meme geste que la presente decision** : `squad/programme/arts-plastiques/competences-cp.md`,
`competences-cm1.md`, `squad/programme/education-musicale/competences-cp.md`, `competences-cm1.md`,
`squad/programme/education-physique-et-sportive/competences-cp.md`, `competences-cm1.md`,
`squad/programme/histoire-des-arts/competences-cm1.md`, `squad/programme/langues-vivantes/competences-cp.md`.

## 7. `EMC-CP-04` : l'annotation que la matrice doit porter, non executee ici

`nadia-prof` refuse d'avance (section 10, refus n°1 de son arbitrage) *"tout item sur `EMC-CP-04`"* et nomme
le risque : *"un developpeur composant depuis la matrice, ou la ligne est `non couvert` sans
annotation. Route a `camille-po` : l'annoter, ou vingt items partiront dedans."* **Cette decision ne
touche a aucun statut ni aucune annotation de `squad/programme/MATRICE-couverture.md`**, par
instruction expresse pour ce document. L'annotation - une note disant que la ligne 4 n'est
volontairement servie par aucun lot de s31, motif `EMC-4` (aucune tache seconde ne peut se poser
sans enumerer des differences entre categories de personnes) - reste due avant que quiconque
compose du contenu depuis la matrice, et je la route a `sofia-sm` pour execution au moment ou elle
gele le perimetre.

## 8. Contrainte dure sur les quatre lignes d'EMC CP, opposable a l'affectation

`nadia-prof` le pose sans marge de negociation (section 3.2 de son arbitrage) : le groupe de lignes
tarife a 1,0 (lignes 1 a 7 du referentiel) **offre exactement quatre lignes fermables** -
`EMC-CP-01`, `EMC-CP-03`, `EMC-CP-05`, `EMC-CP-07` - la ligne 4 etant exclue (section 7). **Il n'y a
aucun remplacant.** Les deux lots d'EMC de la composition 1 doivent couvrir ces quatre lignes et
aucune autre, sans quoi le tarif du lot passe a 1,5 et la composition ne tient plus dans 4,0.
`EMC-CP-05` est une demi-ligne (connaissance seule, l'emploi en situation n'est pas servi -
mention obligatoire a la fermeture) et `EMC-CP-01` est, de l'aveu de sa propre source, la plus
risquee des quatre.

## 9. `PAR-5` et la retenue de 1,0 sur la borne - enjeu de ce sprint, pas une note

`nadia-prof` a pose, le 2026-09-05, deux conditions cumulatives pour lever sa retenue de defiance de
1,0 sur la parallelisation : **un second sprint parallele sans defaut de couture, ET le registre
d'identifiants de `PAR-5` ecrit. Les deux, pas l'un des deux.**

**Etat au 2026-09-07** : `theo-archi` a ecrit le registre (`ADR-0085`,
`squad/decisions/REGISTRE-identifiants.md`, 523 identifiants sur 83 prefixes, quatre tests verts).
`nadia-prof` le confirme et **ne prononce pas elle-meme la consequence** : la seconde condition est
techniquement remplie, la premiere ne peut se constater qu'a la cloture de s31, jamais a son
ouverture (deux defauts de couture ont ete trouves a s30 - `VIV-R2` contre `CONS-1`, puis la
consigne d'histoire-geographie CM1).

**Ce que cela signifie pour s31, ecrit ici parce que c'est un enjeu du sprint et non un
detail de gouvernance** :

1. **La borne de s31 reste 8,0.** Une condition ne se paie pas d'avance sur un resultat non observe.
2. **Si s31 clot sans defaut de couture** - tel que `nadia-prof` le definit : un defaut produit a la
   jointure de deux travaux ou de deux instances, invisible de l'interieur de chacune des deux
   moities - **les deux conditions sont remplies a la cloture de s31, et la retenue tombe : la borne
   de s32 passe a 9,0.**
3. **Si un defaut de couture survient a s31, la retenue reste**, quel que soit l'etat du registre.

Ce point sera tranche a la cloture de s31, par `nadia-prof`, sur constat et non sur promesse.

## 10. Ce que cette decision ne fait pas

- Elle ne gele aucun objectif de sprint et n'affecte personne aux instances : c'est `sofia-sm`.
- Elle ne fait bouger aucun statut ni aucune annotation de `squad/programme/MATRICE-couverture.md`.
- Elle ne prononce aucun Done pedagogique.
- Elle ne rouvre ni `EMC-CP-02` ni `EMC-CP-06`, conformement au refus explicite de `nadia-prof`.
- Elle ne tranche pas si les huit matieres nouvellement codees seront ouvertes un jour, ni dans
  quel ordre.
