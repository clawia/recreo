# [Sprint 31] Planning - 2026-09-07

- **Horodatage de debut** : 2026-09-07 14:45
- **Anime par** : sofia-sm
- **Participants** : sofia-sm (SM et dev), marc-dev, lea-dev, theo-archi (`PAR-5` deja paye,
  `ADR-0085` et `ADR-0086` deja ecrits, avis de faisabilite synthetise ci-dessous), nadia-prof
  (arbitrage prealable deja rendu, deux instances en parallele comme a s30), camille-po (`DEC-0081`
  deja rendue)

## 0. Ce qui est deja entre, avant l'ouverture

Trois branches ouvertes depuis `2ef583ba`, chacune verifiee descendante de `round-two` par
`outils/verifier-lignee.sh` avant sa premiere ecriture, aucun recouvrement de fichier trouve entre
elles. Assemblees par moi dans mon propre worktree dedie (`recreo-s31-sofia`, branche
`s31-sofia-planning`, lignee verifiee avant ma premiere ecriture), par cherry-pick dans l'ordre
chronologique reel :

| Commit | Heure | Agent | Contenu |
|---|---|---|---|
| `e48dd1aa` | 12:08 | `nadia-prof` | arbitrage prealable de s31 - `CAT-1` trouvee en jouant, quatre clauses neuves (`FAB-1`, `PLA-1`, `BIN-1`, `CAT-1`), deux compositions chiffrees, borne 8,0 |
| `84198564` | 12:31 | `theo-archi` | `PAR-5` paye (`ADR-0085`, registre de 523 identifiants), `ADR-0086` (deux garde-fous de fabrication), avis de faisabilite, confirmation ArchUnit (1332 tests) |
| `c22fc450` | 14:22 | `camille-po` | `DEC-0081` - composition 1 a quatre lots retenue par l'utilisatrice, codes alloues sur huit referentiels vierges, `CAT-1` routee a s32 |

**Un defaut de couture trouve et ferme avant l'ouverture du sprint, et je le nomme comme tel plutot
que de le corriger en silence.** L'assemblage des trois branches a fait rougir
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : `nadia-prof` a nomme quatre identifiants
(`CAT-1`, `FAB-1`, `PLA-1`, `BIN-1`) le meme jour, dans une branche que le worktree dedie de
`theo-archi` ne pouvait pas voir au moment ou il a construit le registre - la discipline de worktree
dedie que j'ai generalisee a la retro de s30 empeche precisement de balayer le travail d'un agent
voisin non fusionne. C'est la forme (c) d'un defaut de couture telle que `nadia-prof` l'a definie
elle-meme a s30 (deux instances ecrivant dans une ressource partagee sans le savoir - ici,
l'ensemble des identifiants du depot). **Corrige par moi dans le meme geste que l'assemblage**
(commit `94ede7b8`) : allocation mecanique des quatre lignes manquantes, meme forme que le registre,
aucun sens ni forme de condition modifie - le meme geste que l'allocation d'un code de referentiel.
**Ce n'est pas une occurrence a compter contre s31** : elle est survenue et a ete fermee avant
qu'aucune story de s31 ne soit ouverte, dans l'assemblage des livrables pre-sprint, exactement
comme les corrections de bilans de matiere que `camille-po` fait avant chaque ouverture. Je la
route neanmoins a `theo-archi`, qui l'a deja notee dans le registre : le registre, tel que construit,
ne peut pas voir une allocation faite dans une branche soeur non fusionnee, ce qui est une limite
structurelle de `PAR-5` sous la discipline de worktree dedie.

**J'execute aussi, dans ce meme geste, le routage explicite de `DEC-0081` section 7** (commit
`0d2ec633`) : `squad/programme/MATRICE-couverture.md` porte desormais, sur la ligne `EMC-CP-04`,
l'annotation que `nadia-prof` demande - aucun lot de s31 n'y depense d'item, motif `EMC-4` (une
seule tache possible, `FER-3` non tenable). Aucun statut ne bouge.

**Mesure d'entree technique**, sur l'arbre assemble (`94ede7b8`, apres mes deux corrections
ci-dessus) : `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (racine, pas de
`-pl`), depuis mon propre worktree dedie. **BUILD SUCCESS.** Comptage independant des rapports XML
apres `clean` (methode de l'amendement s11) : **181 fichiers `TEST-*.xml`, 1332 tests, 0 echec, 0
erreur, 0 ignore.** Confirme exactement le chiffre annonce par `theo-archi` (1321 + 4 + 7 = 1332),
mesure de nouveau par moi sur l'arbre assemble plutot que relayee.

## 1. Objectif du sprint

**Ouvrir quatre lignes de plus - deux lots d'EMC CP restreints aux lignes 1 a 7 (`EMC-CP-01`,
`EMC-CP-03`, `EMC-CP-05`, `EMC-CP-07`, sans remplacant possible), deux lots de sciences et
technologie CM1 en second passage (`SCI-CM1-01`, `SCI-CM1-07`, `SCI-CM1-11`, `SCI-CM1-08`) - et
payer deux reprises de reserves qui enseignaient du faux (`MEL-R1/R2/R3`, `OBT-R1/R2`), sous quatre
clauses neuves de `nadia-prof` (`FAB-1`, `PLA-1`, `BIN-1`, `CAT-1`) et deux garde-fous neufs
(`ADR-0086`), sans payer la dette du catechisme civique deja revendique sur `EMC-CP-02` et
`EMC-CP-06`, routee a s32.**

Arbitre par l'utilisatrice le 2026-09-07 : **composition 1** de `nadia-prof` (quatre lots), retenue
contre sa **composition 2** recommandee (trois lots, deux lignes de moins, la passe transverse et
les deux reprises d'EMC financees a la place). **Le +1,0 de borne que `nadia-prof` avait calcule en
corrigeant sa propre double soustraction n'est pas pris : la borne de s31 reste 8,0.**

**Le fait qui n'est pas masque** : la composition retenue ouvre quatre lignes d'EMC de plus sur une
matiere dont les deux seules lignes deja revendiquees se sont averees, le jour meme, servies par des
paquets qui se gagnent au ton plutot qu'a la competence (`CAT-1`, section 4 ci-dessous). `nadia-prof`
recommandait de reparer d'abord ; l'utilisatrice a choisi d'ouvrir davantage.

## 2. Taux d'entree du sprint

**56 / 138 = 40,6 %.** Etat de `squad/programme/MATRICE-couverture.md` a la cloture de s30, repris
sans recalcul (`DEC-0081`, section 1).

## 3. La borne, la composition retenue et son plafond honnete

**Borne : 8,0**, empirique et non derivee, tenue sur trois sprints mesures (s28 : 8,25 ; s29 : 9,50 ;
s30 : 8,50). **Budget opposable par instance : 4,0, marge de rejeu comprise** (`PAR-9`). Plafond dur
individuel de 5,0 inchange (`PAR-8`).

**Marge de rejeu, `PAR-11` appliquee** (0,5 par lot neuf audite, par instance) :

| Instance | Postes | Marge due | Total |
|---|---|---|---|
| **A** | 2 lots EMC CP (1,0 + 1,0) + bloc `melanges` (0,5) + bloc `objet-technique` (0,5) | 1,0 | **4,0** |
| **B** | 2 lots sciences CM1 (1,5 + 1,5) | 1,0 | **4,0** |
| | | | **8,0** |

**8,0 planifies sur 8,0 : aucune marge residuelle au-dela de ce tableau.** C'est le point qui
gouverne la decision sur `SAI-R1` ci-dessous (section 10) et qui rend tout depassement plus probable
qu'aux sprints precedents.

**Plafond arithmetique : 8 lignes, 56 + 8 = 64/138 = 46,4 %.** **Attente raisonnable de
`nadia-prof` : 5 a 7 lignes, 61 a 63/138, soit 44,2 % a 45,7 %** - une attente resserree par
construction arithmetique (les quatre lignes d'EMC viennent d'un groupe de quatre sans remplacant,
sur une matiere dont le taux de refus au premier passage mesure est de quatre paquets sur cinq),
non par prudence : `nadia-prof` ecrit elle-meme s'etre trompee deux fois de suite dans le sens de la
sous-estimation (s29, s30) et ne monte pourtant pas son attente au plafond.

**Ce que la composition 1 finance, et ce qu'elle laisse impaye** (`DEC-0081`, section 3) : elle
finance le plafond maximal et le groupe des deux reserves qui enseignaient du faux ; elle ne
finance ni la passe transverse, ni `REG-R1`/`PER-R1` (differees une troisieme fois), ni donc
`CAT-1`, qui voyageait gratuitement avec elles.

## 4. `CAT-1` - le point le plus serieux du sprint, a lire avant d'ecrire un paquet d'EMC

`nadia-prof` a trouve, en jouant le 2026-09-07, que **les deux seules lignes d'EMC CP que le produit
revendique** (`EMC-CP-02`, `EMC-CP-06`, fermees a s29) **sont servies par des paquets dont la tache
principale se gagne au ton** : sur les six items qu'elle a relus a l'ecran, la bonne reponse est
systematiquement la seule proposition qui sonne comme l'ecole, les distracteurs les seules qui
sonnent comme la faute. Un enfant qui n'a jamais entendu la regle gagne en reconnaissant le
registre attendu - `CON-1`, exactement le **catechisme civique** qu'elle avait signale par ecrit le
2026-09-03, quatre jours avant qu'un seul item d'EMC n'existe. **Son propre audit de s29, qui a
fermee ces deux lignes, ne l'a pas vu.**

Elle ne rouvre ni l'une ni l'autre ligne. Elle ne l'ajoute pas aux dix-sept reserves de s30.
**`CAT-1` entre au backlog de s32** (`S32-CAT1`), pas de s31, et voyage avec `REG-R1`/`PER-R1` dans
le meme rejeu (`PAR-10`) le jour ou elles seront payees.

**`CAT-1` est opposable des ce sprint aux quatre paquets neufs d'EMC CP** (`S31-1`, `S31-2`), au
meme titre que `FAB-1`, `PLA-1` et `BIN-1` (section 5). **Que ce soit lu par qui ecrira les paquets
d'EMC** : le produit ouvre quatre lignes de plus sur une fondation dont un defaut connu n'est pas
paye, et `nadia-prof` nomme elle-meme un effet de contagion previsible - l'instance qui ecrira ses
paquets neufs lira les paquets existants de la matiere, qui sont aujourd'hui le modele du defaut.

## 5. Les quatre clauses neuves, datees du 2026-09-07, opposables aux paquets neufs

> **`FAB-1`** - une regle de fabrication ne se repete pas a l'interieur d'une tache : (a) deux items
> d'une meme tache ne portent jamais la meme bonne reponse mot pour mot ; (b) les distracteurs ne
> sont pas pris systematiquement dans le vivier des bonnes reponses des autres items de la meme
> tache ; (c) la bonne reponse ne se reconnait pas a la forme de son texte sur plus d'un tiers des
> items de la tache. Un classement a categories fermees reste licite des lors que la bonne reponse
> varie (`FER-1` l'exige de toute facon).

> **`PLA-1`** - une proposition est un candidat, jamais un remplissage : chaque distracteur est une
> reponse qu'un enfant du niveau vise peut serieusement envisager. Complete `SCI-4` sans l'abroger.

> **`BIN-1`** - une partie ne se joue pas majoritairement a pile ou face : sur une ligne dont la
> tache principale est un classement a deux categories, au plus la moitie des items d'un paquet
> porte deux propositions.

> **`CAT-1`** - sur toute ligne d'EMC, la bonne reponse ne se reconnait pas a son ton (section 4).

## 6. Quinze refus ecrits d'avance par `nadia-prof`, trois nommes comme les plus probables

Repris integralement dans `docs/backlog/backlog.md`, section "Sprint 31", et dans les criteres
d'acceptation des six stories - je ne les recopie pas ici un a un. **Les trois que `nadia-prof`
signale elle-meme comme les plus probables**, pour qu'ils soient evitables plutot que constates :

1. **Tout item sur `EMC-CP-04`** - troisieme refus de cette ligne. Annotee sur la matrice (section
   0) pour qu'aucun lot ne s'y engage par erreur de lecture.
2. **Toute tache de sciences a bonne reponse invariante** (`FAB-1a`) - **y compris les trois couples
   de taches que `nadia-prof` avait elle-meme proposes a s30** sur la ligne 13 : elle le signale
   contre elle-meme, pour qu'on ne la relise pas comme une garantie.
3. **Toute reponse portee par une couleur percue sur les symboles** (`EMC-CP-08`, drapeau) - on
   demande le nom, jamais la couleur, motif `ART-1` deja pose.

## 7. Ce que je tranche sur `catalogue.yaml`, explicitement plutot qu'a la decouverte

**Quatre lots neufs, c'est huit entrees neuves de `catalogue.yaml`** (un paquet par ligne, comme a
s30) : `S31-1` et `S31-2` (EMC CP) en ecrivent quatre, `S31-3` et `S31-4` (sciences CM1) en ecrivent
quatre autres. **A s30, j'avais fait de `lea-dev` la seule ecrivaine de ce fichier - juste dans son
principe, mais je ne l'avais pas vu s'appliquer a une story de `marc-dev`, ce qui a produit un
reroutage en cours de sprint (`FOP-R1`).** Avec quatre lots neufs cette fois, un seul ecrivain
concentrerait les quatre stories de contenu chez une seule developpeuse : je ne reproduis pas ce
desequilibre sans le nommer.

**Ma decision : je decoupe `catalogue.yaml` par matiere, un ecrivain par bloc, et je sequence les
deux blocs plutot que de les laisser se croiser.**

- **`marc-dev` ecrit le bloc EMC CP en premier** (`S31-1` puis `S31-2`, strictement en serie sur ce
  fichier). Il n'a aucune autre story ce sprint : le faire passer en premier evite de le laisser
  sans rien a faire pendant qu'il attend.
- **`lea-dev` ecrit le bloc sciences CM1** (`S31-3` puis `S31-4`) **seulement une fois les deux
  commits de `marc-dev` sur `catalogue.yaml` visibles dans son propre worktree** - elle rebase ou
  attend la fusion avant d'y toucher. Elle n'est pas bloquee en attendant : ses deux reprises
  (`S31-5`, `S31-6`) ne touchent pas ce fichier et peuvent courir en parallele des le debut du
  sprint.

**Ce que cela donne comme charge, et je ne l'equilibre pas artificiellement** : `lea-dev` porte
quatre stories (`S31-3`, `S31-4`, `S31-5`, `S31-6`), `marc-dev` en porte deux (`S31-1`, `S31-2`).
L'ecart est assume : l'EMC CP est la matiere au taux de refus le plus eleve de ce depot (quatre
paquets sur cinq a s29) et porte `CAT-1` en fond, ce qui rend les deux lots de `marc-dev` plus
couteux en temps d'audit et de reprise que leur nombre ne le laisse paraitre ; les deux reprises de
`lea-dev` sont des corrections d'indice ponctuelles, deja specifiees mot pour mot par `nadia-prof`
sur un point (`MEL-R1`) et par propriete sur l'autre (`OBT-R1`, `OBT-R2`).

## 8. Stories retenues

| Story | Titre | Competence programme visee | Dev | Instance (audit) |
|---|---|---|---|---|
| `S31-1` | EMC CP, lot 1 : emotions d'autrui, formules de politesse | `EMC-CP-01`, `EMC-CP-05` | `marc-dev` | A |
| `S31-2` | EMC CP, lot 2 : exclusion, regle/souhait/loi | `EMC-CP-03`, `EMC-CP-07` | `marc-dev` | A |
| `S31-3` | Sciences CM1, second passage, lot 1 : etats de la matiere, regimes alimentaires | `SCI-CM1-01`, `SCI-CM1-07` | `lea-dev` | B |
| `S31-4` | Sciences CM1, second passage, lot 2 : familles de materiaux, etapes du developpement | `SCI-CM1-11`, `SCI-CM1-08` | `lea-dev` | B |
| `S31-5` | Reprise : l'indice du sel ne fait plus disparaitre l'eau (`MEL-R1/R2/R3`) | `SCI-CM1-02` (deja `couvert-valide`, non bloquant) | `lea-dev` | A |
| `S31-6` | Reprise : l'indice du velo ne dement plus sa propre bonne reponse (`OBT-R1/R2`) | `SCI-CM1-12` (deja `couvert-valide`, non bloquant) | `lea-dev` | A |

**Point de sequencement interne au sprint, porte par le backlog et non par moi** : `nadia-prof`
refuse d'avance tout item disant que l'eau disparait sur la ligne 1 des sciences (`SCI-CM1-01`,
`S31-3`) - si cette ligne ouvre sans que `MEL-R1` (`S31-5`) soit egalement rendue, le produit se
contredit sur deux paquets de la meme matiere et du meme niveau, dans le meme sprint. **L'Instance
B ne ferme pas `SCI-CM1-01` avant que `S31-5` ait ete livree et rejouee**, meme si `S31-5` est
auditee par l'Instance A et non par elle-meme : c'est une verification croisee explicite, nommee
pour ne pas se perdre entre deux instances qui ne se relisent pas l'une l'autre par ailleurs.

## 9. La retenue de 1,0 sur la borne - enjeu chiffre de ce sprint, pas une note de bas de page

`nadia-prof` a pose, le 2026-09-05, deux conditions cumulatives pour lever sa retenue de defiance
sur la parallelisation : **un second sprint parallele sans defaut de couture, ET le registre
d'identifiants de `PAR-5` ecrit. Les deux, pas l'une des deux.**

**Etat au 2026-09-07** : `PAR-5` est paye (`ADR-0085`, section 0 ci-dessus) - **une seule des deux
conditions est acquise**. Ecrire le registre ne rachete pas la couture rompue a s30 (`VIV-R2` contre
`CONS-1`, puis trois reecritures de la consigne d'histoire-geographie CM1). **Si s31 clot sans
defaut de couture, les deux conditions sont remplies a sa cloture, et la retenue tombe pour s32 : sa
borne passe a 9,0** - de l'ordre de deux lignes de programme de plus a chaque sprint suivant. **Si
un defaut de couture survient, la retenue reste**, quel que soit l'etat du registre. C'est la
premiere fois que la proprete d'un sprint a un prix chiffre en couverture future, et je le porte ici
comme point de vigilance de sprint plutot que comme fait acquis d'avance.

**Ce qu'est un defaut de couture, repris mot pour mot pour que la condition se constate a la
cloture sans redemander a `nadia-prof` ce qu'elle voulait dire** - un defaut produit a la jointure
de deux travaux ou de deux instances, invisible de l'interieur de chacune des deux moities :

- **(a)** une story qui modifie un paquet et une story qui modifie sa consigne dans le meme sprint,
  laissant la consigne fausse sur le paquet livre (s30, deux fois) ;
- **(b)** un chiffre de sprint que ni l'une ni l'autre instance ne peut verifier depuis sa moitie,
  et qu'un tiers trouve faux en recomptant (s29) ;
- **(c)** deux instances ecrivant dans une meme ressource partagee sans le savoir - identifiants,
  fichier, scratchpad (s29 et s30 ; et une quatrieme occurrence trouvee et fermee **avant**
  l'ouverture de s31 elle-meme, section 0 ci-dessus, sur le registre `PAR-5` - qui ne compte pas
  contre s31 mais illustre le risque en vigueur).

**Une seule occurrence de l'une des trois formes suffit a maintenir la retenue.** Ce point sera
tranche a la cloture de s31 par `nadia-prof`, sur constat et non sur promesse.

## 10. Avis de faisabilite - theo-archi (renvoi)

Synthese complete dans
`squad/sprints/s31-second-passage-de-sciences-et-emc-cp/avis-faisabilite-theo.md`. Points a
connaitre avant d'ecrire :

- **Les deux lots sont faisables sans une ligne de code hors contenu.** `EMC-CP-08` (symboles de la
  Republique) n'entre dans aucun lot de s31 - elle demanderait une illustration ou l'arbitrage vocal
  de la Marseillaise, ni l'un ni l'autre construit ce sprint.
- **Deux garde-fous neufs aideront les developpeuses, un troisieme n'existera pas** : `ADR-0086`
  encode l'item servi plusieurs fois sous plusieurs noms (clause 1) et la contrefacon par l'amorce
  (clause 2, `FOP-R4`). **La famille `OBJ-R2` reste non encodable** (mesuree, pas supposee : elle
  blanchirait le paquet refuse et accuserait seize paquets valides) et reste sous la seule garde de
  `nadia-prof`.
- **`SAI-R1`** (un point final ajoute a la saisie de l'enfant, doublant sa propre ponctuation) - cause
  trouvee, correctif de douze caracteres specifie, **n'entre pas dans le perimetre de s31**
  (`DEC-0081`, section 5) : la composition sature exactement la borne de 8,0, et la marge `PAR-11`
  est deja jugee sous-dimensionnee par sa propre auditrice pour l'exposition reelle de l'Instance A.
  Reste au backlog, chiffree 0,25, prete des qu'un sprint dispose d'une marge non consommee.
- **Huit referentiels recoivent leurs codes de ligne** (arts plastiques CP/CM1, education musicale
  CP/CM1, EPS CP/CM1, histoire des arts CM1, langues vivantes CP) - geste de `camille-po`, zero
  ligne de code, zero Done pedagogique attache, aucune de ces matieres n'est ouverte a s31 ni a s32
  par ce geste.
- **Dette ouverte par `theo-archi` sur son propre fichier** : `ADR-index.md` portait deux `ADR` sur
  la meme ligne physique depuis huit sprints (defaut trouve en le mettant a jour, corrige dans son
  commit) ; aucun garde-fou ne verifie qu'un `ADR` figure a l'index une fois et une seule - dette
  nommee, non payee ce sprint.

## 11. Ce que je route, pas ce que je tranche

- **La limite structurelle de `PAR-5`** face a la discipline de worktree dedie (section 0) - route a
  `theo-archi`, deja note dans le registre lui-meme.
- **Un garde-fou de coherence entre le bilan chiffre d'un referentiel et le compte reel de ses
  puces** - sixieme ecart de ce type trouve a la main en trois sprints (`camille-po`, section 6 de
  `DEC-0081`, sur `education-musicale-cm1`). Route a `theo-archi` pour instruction, non engage a
  s31.
- **`SAI-R1`**, chiffree et specifiee, non prise ce sprint faute de marge (section 10).
- **`CAT-1`, `REG-R1`, `PER-R1`** - routees a s32, story `S32-CAT1` deja ecrite au backlog.

## 12. Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise par moi dans ce planning : je
reprends du travail deja tranche par `nadia-prof`, `theo-archi` et `camille-po`. Ce que ce document
tranche, qui releve de mon role :

1. **Affectation nominative des six stories** entre `marc-dev` et `lea-dev` (section 8), avec
   l'ecart de charge assume et son motif ecrit (section 7).
2. **Decoupage et sequencement de `catalogue.yaml` par matiere** entre les deux developpeurs
   (section 7), tranche explicitement plutot que decouvert en cours de sprint comme a s30.
3. **Fermeture d'un defaut de couture trouve a l'assemblage** (registre `PAR-5` incomplet, section
   0) - allocation mecanique, sans decision d'architecture.
4. **Execution du routage de `DEC-0081` section 7** : annotation de `EMC-CP-04` sur la matrice
   (section 0).

## 13. Prochaines etapes

- `marc-dev` : `S31-1` puis `S31-2`, strictement en serie sur `catalogue.yaml` - seule ecriture de
  ce fichier avant celle de `lea-dev`. Ouvre `../recreo-s31-marc` (`git worktree add
  ../recreo-s31-marc -b s31-marc round-two`), verifie par `outils/verifier-lignee.sh` avant le
  premier commit, mesure toute execution de `mvn` depuis ce meme worktree pour toute la duree du
  sprint.
- `lea-dev` : `S31-5` et `S31-6` des l'ouverture, en parallele du travail de `marc-dev` (aucun des
  deux ne touche `catalogue.yaml`) ; puis `S31-3` et `S31-4` une fois les deux commits de
  `catalogue.yaml` de `marc-dev` visibles dans son worktree. Meme protocole de worktree dedie
  (`../recreo-s31-lea`), meme discipline de mesure.
- `nadia-prof`, **Instance A** : audite `S31-1`, `S31-2` des leur livraison ; audite `S31-5`,
  `S31-6` des qu'ils sont livres, en parallele.
- `nadia-prof`, **Instance B** : audite `S31-3` puis `S31-4` des leur livraison ; **ne ferme pas
  `SCI-CM1-01` avant que `S31-5` ait ete livree et rejouee** (section 8).
- `theo-archi` : instruit, s'il le juge necessaire, le garde-fou de coherence des bilans de
  referentiel et la limite structurelle de `PAR-5` face au worktree dedie (section 11) ; aucune des
  deux n'est requise pour qu'une story de s31 ferme.
- `camille-po` : suit tout arbitrage de perimetre si un lot de s31 est refuse en cours de sprint,
  sachant qu'aucune marge n'est disponible au-dela de la composition planifiee (section 3).
