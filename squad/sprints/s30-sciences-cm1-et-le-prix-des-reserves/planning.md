# [Sprint 30] Planning - 2026-09-06

- **Horodatage de debut** : 2026-09-06 17:45
- **Anime par** : sofia-sm
- **Participants** : sofia-sm (SM et dev), marc-dev, lea-dev, theo-archi (avis de faisabilite deja
  rendu, `ADR-0084` deja ecrit et livre ; synthetise ci-dessous, section 6), nadia-prof (arbitrage
  prealable deja rendu, deux instances a partir de ce sprint comme a s29 ; merge ci-dessous),
  camille-po (`DEC-0079` deja rendue ; merge ci-dessous)

## 0. Ce qui est deja entre, avant l'ouverture

Rien ne dort sur une branche separee au moment ou j'ecris : les quatre lots amont ont ete verifies
chacun dans son propre worktree (`recreo-s30-nadia`, `recreo-s30-theo`, `recreo-s30-camille`),
lignee confirmee par `outils/verifier-lignee.sh` sur les trois, puis assembles par moi dans mon
propre worktree dedie (`recreo-s30-sofia`, branche `s30-sofia-planning`, lignee verifiee de la
meme facon avant ma premiere ecriture). Les quatre branches partaient toutes du meme commit de
`round-two` (`a5f2812`) et ne touchent aucune ligne du meme fichier entre elles - verifie par
intersection des listes de fichiers modifies avant fusion, aucun recouvrement trouve. Assemblage
par cherry-pick dans l'ordre chronologique reel des commits (et non par branche entiere), pour que
l'histoire du depot refleche l'ordre ou le travail a ete ecrit :

| Commit | Heure | Agent | Contenu |
|---|---|---|---|
| `339ba75` | 16:52 | `theo-archi` | `ADR-0084` - le verdict d'une mecanique servie porte l'enonce de la manche qu'il ferme ; garde-fou `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` (3 tests, 2 morsures). **Traite `REV-3`** |
| `f194fe1` | 17:01 | `nadia-prof` | arbitrage prealable de s30 - perimetre de sciences CM1, prix des quinze reserves, `PAR-9`, composition des deux instances, plafond a quatre lignes |
| `89ff207` | 17:09 | `theo-archi` | avis de faisabilite - 18 lignes de sciences sur 19 faisables sans code, prerequis des codes de referentiel, `CONS-1` a cout technique nul, deux pieges nommes |
| `5338794` | 17:27 | `camille-po` | `DEC-0079` - perimetre retenu, vingt codes `SCI-CM1-01` a `-20` alloues, deux libelles corriges, section "Perimetre de s30" de la matrice, onze stories `S30-1` a `S30-11` du backlog |
| `10ae519` | - | `sofia-sm` | repertoire de sprint renomme `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/` (`theo-archi` l'avait ecrit sans slug, en le nommant explicitement comme du a moi), trois references corrigees en consequence (`docs/backlog/backlog.md`, `DEC-0079`, le fichier deplace lui-meme) |

**Mesure d'entree technique**, sur l'arbre assemble (commit `339ba75`..`5338794` inclus, aucune
ecriture de ma part au moment de la mesure) : `bash outils/mvn-jdk21.sh -B clean verify` sur le
reacteur complet (racine, **pas de `-pl`**). **BUILD SUCCESS**, 1 min 47 s, JDK 21.0.10 rapporte par
la JVM des tests. Comptage independant des rapports XML de tous les modules du reacteur (175
fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports`, somme des attributs `tests` /
`skipped` / `failures` / `errors` de la balise `<testsuite>`, methode de l'amendement s11) :
**1289 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre confirme exactement celui annonce par
`theo-archi` dans son avis de faisabilite (section 0) - mesure de nouveau par moi sur l'arbre
assemble plutot que relayee, conformement a la lecon de s29 (un chiffre annonce se reverifie).

**Une correction que je fais a mon tour, trouvee en verifiant plutot qu'en relayant** (section 2.2
de l'avis de `theo-archi`) : sa phrase de synthese annonce *"sept referentiels portent des
codes... onze n'en portent aucun"*, mais sa propre enumeration entre parentheses liste **neuf**
referentiels (questionner le monde CP, histoire-geographie CM1, EMC CP, EMC CM1, francais CP,
francais CM1, mathematiques CM1, mathematiques CP, langues vivantes CM1) - "sept" et "onze" ne se
recoupent ni avec les neuf enumeres, ni entre eux (7 + 11 = 18, mais l'enumeration en compte neuf,
donc dix-huit moins neuf fait neuf, pas onze). Recompte direct par moi, `grep -c "^- \["` sur les
dix-huit fichiers `squad/programme/*/competences-*.md` : **neuf referentiels portaient des codes
avant l'ouverture de s30 (le compte que l'enumeration de `theo-archi` donne, correctement, terme
a terme), neuf n'en portaient aucun** - pas onze. **Apres l'allocation des vingt codes
`SCI-CM1-01` a `-20` par `DEC-0079` (dans le meme geste que ce planning), l'etat courant de
`round-two` est : dix referentiels sur dix-huit portent des codes, huit n'en portent toujours
aucun** (`arts-plastiques` CP et CM1, `education-musicale` CP et CM1,
`education-physique-et-sportive` CP et CM1, `histoire-des-arts` CM1, `langues-vivantes` CP).
**C'est ce chiffre - huit sur dix-huit, pas onze - qui est la dette de depot a retenir a l'ouverture
de s30** (voir section 7). Route a `theo-archi` : corriger "sept" et "onze" en "neuf" et "neuf"
dans son avis, pour que le fichier ne reste pas faux sur un point qu'il a lui-meme mesure.

## 1. Objectif du sprint

**Ouvrir sciences et technologie CM1 comme perimetre principal (deux lots, quatre lignes au plus),
payer treize des quinze reserves ouvertes de s29 en six blocs, fermer `CONS-1` (les dix consignes
generiques restantes) et rejouer `REV-3` (deja tranchee et livree techniquement par `theo-archi`
avant l'ouverture du sprint), sous deux instances de `nadia-prof` en parallele - une troisieme fois,
avec la discipline de mesure que la retro de s29 avait laissee ouverte desormais tranchee
(section 4).**

Perimetre arbitre par l'utilisatrice le 2026-09-06 (quatre points, enregistres dans `DEC-0079` et
non rouverts par ce planning) : objectif nomme par elle, **la meilleure couverture programme
possible**.

1. Perimetre principal : **sciences et technologie CM1 en bloc.**
2. **Deux instances de `nadia-prof`** en parallele, comme a s29.
3. **Les quinze reserves ouvertes de s29 entrent dans s30.**
4. **`CONS-1` et `REV-3` entrent tous les deux dans s30.**

**Ce que ce planning ne fait pas** : il ne rouvre aucun des quatre points ci-dessus, ne recalcule
aucun chiffre de `nadia-prof` ou de `camille-po`, et ne tranche aucune question de produit ou
d'architecture deja rendue par `DEC-0079` ou `ADR-0084`.

## 2. Taux d'entree du sprint

**52 / 138 = 37,7 %.** Recompte independant de `camille-po` (`DEC-0079`, section 2) sur l'etat de
`squad/programme/MATRICE-couverture.md` apres la cloture de s29 : 52 `couvert-valide`, 7 `en
cours`, 79 `non couvert`, 71 `non couvrable en l'etat`, 209 lignes, 138 au denominateur atteignable.
Converge exactement avec le chiffre ecrit dans la matrice a la cloture de s29 - aucun ecart trouve.

## 3. Le perimetre, sa borne et le budget opposable par instance

**Borne du sprint : 8,0**, decote de 1,0 maintenue contre elle-meme (`nadia-prof`, section 1.4 de
son arbitrage) : sa condition de levee exigeait un second sprint parallele sans defaut de couture
**et** le registre de `PAR-5` ecrit - aucune des deux n'est acquise, s29 ayant eu un defaut de
couture (l'ecart de comptage trouve par `camille-po` seule) et `PAR-5` n'existant toujours pas.

**`PAR-9`, clause neuve de `nadia-prof` du 2026-09-06, corrigeant `PAR-8`** : le budget opposable
est desormais **4,0 par instance, marge de rejeu comprise**, dont la somme vaut exactement la
borne - `PAR-8` laissait deux plafonds individuels de 5,0 sommer a 10,0 sans jamais tenir leur
somme, ce qui a laisse s29 couter 9,50 sur 8,0 en respectant chacun des deux plafonds pris
separement. Le plafond dur de 5,0 par instance demeure, mais cesse d'etre le chiffre de
planification.

**Composition retenue** (`nadia-prof`, section 5.2 de son arbitrage ; reprise a l'identique par
`DEC-0079`, section 6.2) :

| | **Instance A** - porte l'accumulation | **Instance B** - le terrain sans accumulation |
|---|---|---|
| **Lots de contenu** | aucun | sciences et technologie CM1, lot 1 (1,5) et lot 2 (1,5) |
| **Reprises** | bloc A verbatim (0,25), bloc B objet-technique (0,5), bloc C histoire-geographie (0,5), bloc E fait-opinion (0,5), bloc F droit-devoir (0,5) - **2,25** | bloc D division-posee, `POS-R2` (0,5) |
| **Portee depot** | passe transverse (0,5), `CONS-1` (0,5), `REV-3` (0,25) - **1,25** | - |
| **Planifie** | **3,50** | **3,50** |
| **Marge de rejeu** | 0,5 | 0,5 |
| **Total** | **4,0 / 4,0** | **4,0 / 4,0** |
| **Differe a s31** | blocs G (`REG-R1`) et H (`PER-R1`), motif ecrit par `nadia-prof` (section 5.3 de son arbitrage) | - |

**Total planifie du sprint : 7,00. Marge nommee et non allouee : 1,00. Borne : 8,00.**

**Le chiffre que l'utilisatrice a demande a voir sans qu'il soit embelli** (`DEC-0079`, section
6.3) : **62,5 % de la borne d'audit (5,00 sur 8,00 : les treize reprises, `CONS-1`, `REV-3` et la
passe transverse) ne ferment aucune ligne neuve.** Seuls les deux lots de sciences font bouger la
matrice. **Plafond arithmetique : 4 lignes, 52 + 4 = 56 / 138 = 40,6 %. Attente raisonnable de
`nadia-prof` : 3 a 4 lignes, 55 a 56 / 138, soit 39,9 % a 40,6 %** (ce plafond suppose zero marge de
rejeu sur les lots de sciences eux-memes ; avec la marge de 1,0 tenue pour necessaire, l'attente se
resserre a 3-4 plutot qu'a 4 pile).

**`S30-10` (`REV-3`) arrive deja livree, et ce fait n'est pas tu.** `theo-archi` a tranche la forme
technique et l'a implementee - garde-fou compris - **avant l'ouverture de ce sprint** (`ADR-0084`,
commit `339ba75`). Le backlog affectait deja `REV-3` a `theo-archi` pour le choix technique et
l'implementation ; ce qui merite d'etre nomme ici est que ce choix et cette implementation sont
arrives **avant** le gel de l'objectif, et non pendant le sprint comme le reste du perimetre. Ce
qui reste du au sprint sur cette story est uniquement le rejeu de `nadia-prof` sur la forme livree
(0,25 attendu, deja compte dans le budget de l'Instance A ci-dessus, sujet a doubler si elle
constate qu'un ecran neuf en nait).

## 4. Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S30-1` | Premier lot de sciences CM1 : signal et information du quotidien, chaine alimentaire | `SCI-CM1-05`, `SCI-CM1-17` | `DEC-0074`, `DEC-0079` | `lea-dev` (dev), Instance B (audit) |
| `S30-2` | Second lot de sciences CM1 : melanges homogenes/heterogenes, objet technique | `SCI-CM1-02`, `SCI-CM1-12` | `DEC-0074`, `DEC-0079` | `lea-dev` (dev), Instance B (audit) |
| `S30-3` | Reprise, bloc A : deux corrections mot pour mot sur questionner le monde CP (`CYV-R1`, `VIV-R2`) | `QLM-CP-01`, `QLM-CP-02` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `lea-dev` (dev), Instance A (audit) |
| `S30-4` | Reprise, bloc B : titre et rejeu borne sur l'objet technique CP (`OBJ-R1`, `OBJ-R2`) | `QLM-CP-07` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `lea-dev` (dev), Instance A (audit) |
| `S30-5` | Reprise, bloc C : rejeu borne et erreur de fait sur l'histoire-geographie CM1 (`HG-R6`, `HG-R7`) | `HG-CM1-14`, `HG-CM1-07` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `marc-dev` (dev), Instance A (audit) |
| `S30-6` | Reprise, bloc D : rejeu borne sur la division posee CM1 (`POS-R2`) | `MAT-CM1-05` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `marc-dev` (dev), Instance B (audit) |
| `S30-7` | Reprise, bloc E : trois corrections sur "fait ou opinion" CM1 (`FOP-R1`, `-R2`, `-R3`) | `EMC-CM1-11` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `marc-dev` (dev), Instance A (audit) |
| `S30-8` | Reprise, bloc F : trois corrections sur "droit ou devoir" CM1 (`DRO-R1`, `-R2`, `-R3`) | `EMC-CM1-07` (deja `couvert-valide`, non bloquant) | `DEC-0079` | `marc-dev` (dev), Instance A (audit) |
| `S30-9` | `CONS-1` - dix consignes generiques reecrites | transverse, aucune ligne ne bouge | `DEC-0078`, `DEC-0079` | `lea-dev` (dev), Instance A (audit) |
| `S30-10` | `REV-3` - deja tranchee et livree par `ADR-0084`, rejeu seul restant | transverse, aucune ligne ne bouge | `ADR-0084`, `DEC-0079` | `nadia-prof`, Instance A, seule (rejeu) - aucun developpeur |
| `S30-11` | Passe transverse de l'instance d'accumulation sur les proprietes de portee "depot servi entier" | transverse, aucune ligne ne bouge | `DEC-0079` (section 3.3 de l'arbitrage de `nadia-prof`) | `nadia-prof`, Instance A, seule |

## 5. Affectations nominatives des deux developpeurs, et mon motif

### 5.1 Le fichier partage impose un seul ecrivain, comme a s29

**`catalogue.yaml` n'accepte qu'une seule ecriture a la fois** (meme motif qu'a s29, section 5.3 de
mon planning precedent) : deux paquets neufs de sciences (`S30-1`, `S30-2`) y ajoutent quatre
entrees, la correction du titre de l'objet technique (`S30-4`) en modifie une, et `CONS-1`
(`S30-9`) en modifie dix. **Ces quatre stories sont les seules du sprint qui ecrivent dans
`catalogue.yaml`** ; je les regroupe toutes chez `lea-dev`, sequencees entre elles, pour qu'aucune
minute du sprint ne voie deux developpeurs y ecrire en meme temps.

### 5.2 Continuite de matiere, ou elle s'applique sans ambiguite

- **`lea-dev`** a ecrit les trois premiers paquets de questionner le monde CP depuis s27/s28 et le
  paquet de l'objet technique lui-meme (`S29-1`, commit `d6e554f`) : elle reprend **`S30-3`** (bloc
  A, memes paquets) et **`S30-4`** (bloc B, meme paquet qu'elle a ecrit). Elle a aussi ecrit les
  deux paquets d'EMC CM1 concernes par les blocs E et F (`S29-5`, commit `ee3ef70`).
- **`marc-dev`** a ecrit le paquet d'histoire-geographie CM1 (`S28-2`/`S29-2`, commits `ec73c51` et
  `63882e0`) et le paquet de division posee (`S28-4`/`S29-6`, commits `21f3d1d` et `8644932`) : il
  reprend **`S30-5`** (bloc C) et **`S30-6`** (bloc D), ni l'un ni l'autre ne touchant
  `catalogue.yaml`, donc entierement paralleles au reste du sprint.

### 5.3 Ou je m'ecarte de la continuite, et pourquoi

**`S30-7` et `S30-8` (blocs E et F, fait-opinion et droit-devoir CM1) vont a `marc-dev`, pas a
`lea-dev` qui les a ecrits.** Deux raisons, pas une seule :

- **Aucune des deux ne touche `catalogue.yaml`** : rien n'oblige a les regrouper avec `S30-1`,
  `S30-2`, `S30-4` et `S30-9`, contrairement au bloc B qui reste chez `lea-dev` pour cette raison
  precise.
- **La charge.** Si je suivais la continuite a la lettre, `lea-dev` porterait sept stories
  (`S30-1`, `-2`, `-3`, `-4`, `-7`, `-8`, `-9`) contre deux pour `marc-dev` (`S30-5`, `-6`) - un
  ecart que rien dans ce sprint ne justifie, les deux lots de sciences etant deja le poste le plus
  lourd du sprint chez `lea-dev`. Deplacer les blocs E et F chez `marc-dev` ramene la repartition a
  cinq stories (`S30-1`, `-2`, `-3`, `-4`, `-9`) contre quatre (`S30-5`, `-6`, `-7`, `-8`) - un
  ecart qui reste, parce que les deux lots de sciences pesent plus que quatre reprises de reserve,
  mais qui n'est plus disproportionne. Corriger un item de classement ou un indice binaire dans un
  paquet YAML ne demande pas d'en avoir ecrit le premier jet : les criteres d'acceptation de
  `S30-7` et `S30-8` sont explicites au mot pres sur ce qui doit changer.

### 5.4 Ordre de sortie, cote developpeurs

**`lea-dev`, strictement en serie sur `catalogue.yaml`** : `S30-1` (premier lot de sciences,
ouvre le perimetre principal du sprint), puis `S30-2` (second lot), puis `S30-4` (titre de l'objet
technique), puis `S30-9` (`CONS-1`, ferme la dette transverse en dernier). **`S30-3`** ne touche
pas `catalogue.yaml` et peut courir a tout moment de cette serie, en parallele, sans attendre son
tour.

**`marc-dev`, sans aucune contrainte de serie** : `S30-5`, `S30-6`, `S30-7`, `S30-8` portent sur
quatre fichiers YAML distincts, aucun partage avec un autre lot du sprint ; il les livre dans
l'ordre qu'il juge le plus efficace.

### 5.5 Les deux instances d'audit, rappel de ce que `nadia-prof` a deja tranche

**Instance B** audite `S30-1`, `S30-2` (les deux lots de sciences, terrain ou personne n'a
d'accumulation - `PAR-7`) et `S30-6` (bloc D, le seul rejeu qui lui est confie, `PAR-10`).
**Instance A** audite tout le reste : `S30-3`, `S30-4`, `S30-5`, `S30-7`, `S30-8` (reprises), `S30-9`
(`CONS-1`), `S30-10` (rejeu de `REV-3`) et conduit seule `S30-11` (passe transverse). Cette
repartition n'est pas la mienne a arbitrer : elle decoule de `PAR-1`, `PAR-2`, `PAR-3` et `PAR-7`,
deja verifiees clause par clause par `nadia-prof` elle-meme (section 5.2 de son arbitrage). Comme a
s29, **aucune des deux instances ne relit les lots de l'autre** : ce ne sont pas deux paires
d'yeux en double controle, ce sont deux paires d'yeux sur des lots disjoints.

## 6. Discipline de worktree - je generalise la mesure, comme la retro de s29 l'annoncait

**Ce que la retro de s29 laissait ouvert, et que je tranche ici plutot que de le laisser courir un
sprint de plus** : *"la coordination de deux flux de travail paralleles n'a pas ete traitee comme
une discipline explicite valable pour tous les agents... pour s30, si un sprint reprend cette
forme, la regle de worktree dedie doit etre generalisee a l'ouverture du planning plutot que
laissee a l'initiative de chacun."* Ce sprint reprend cette forme (deux instances de `nadia-prof`
en parallele, comme a s29) : la regle est due maintenant.

**Regle, valable pour tout le sprint, pour tout agent** : **toute mesure du reacteur - complete ou
partielle, un `mvn verify` de controle comme un comptage independant de rapports XML - se lance
depuis le worktree dedie de l'agent qui la lance, jamais depuis le worktree principal, jamais
depuis celui d'un autre agent, y compris pour une verification ponctuelle qu'on croit trop courte
pour justifier l'ouverture d'un worktree.** Elle etend la regle deja ecrite pour la **premiere
ecriture** d'un lot (section 9 du `README`, amendee quatre fois depuis s06) a **toute mesure**,
premiere ou non : le mode de defaillance que s29 a rencontre trois fois (mesures concurrentes sur
un arbre partage rendant des rouges intermittents et des chiffres non fiables ; une consigne de
mesure partielle ayant laisse passer un rouge que seul le reacteur complet voyait ; du travail d'un
agent embarque dans le commit d'un autre) est un mode de collision entre deux flux paralleles, pas
seulement entre une premiere ecriture et le reste. `yanis-qa` pratiquait deja cette discipline de
son propre chef depuis s28 (cree, mesure, detruit, sans jamais toucher au worktree principal) :
cette regle generalise sa pratique a tous, plutot que de la laisser dependre de qui y a deja pense.

Consequence concrete pour ce sprint : `marc-dev` et `lea-dev` mesurent chacun depuis son propre
worktree dedie (`git worktree add ../recreo-s30-lea -b s30-lea round-two`,
`git worktree add ../recreo-s30-marc -b s30-marc round-two`) ; les deux instances de `nadia-prof`
font de meme si elles ont besoin de relancer une mesure technique en cours d'audit ; et je m'y
tiens moi-meme, comme je l'ai fait pour ecrire ce document (`recreo-s30-sofia`). **Cette regle est
notee ici pour prendre effet immediatement ; elle sera portee en amendement formel de
`squad/README.md` section 9 a la retro de ce sprint** (le canal ou les amendements au `README`
s'ecrivent), pour ne pas rester une phrase de planning qui ne survit pas a la cloture.

## 7. Ce que je route, pas ce que je tranche

- **L'erreur de synthese dans l'avis de `theo-archi`** (section 0 ci-dessus) : "sept" et "onze"
  doivent devenir "neuf" et "neuf" dans `squad/sprints/s30-sciences-cm1-et-le-prix-des-
  reserves/avis-faisabilite-theo.md`, section 2.2. Je ne l'edite pas moi-meme dans son fichier :
  je le signale, il corrige.
- **La dette des codes de referentiel manquants reste ouverte, et son chiffre est desormais huit
  sur dix-huit, pas onze** (section 0 ci-dessus) : `arts-plastiques` (CP et CM1),
  `education-musicale` (CP et CM1), `education-physique-et-sportive` (CP et CM1),
  `histoire-des-arts` (CM1), `langues-vivantes` (CP). **Ce n'est pas une tache de ce sprint** -
  aucune story de s30 ne construit de contenu sur ces huit referentiels - mais c'est une dette de
  depot qui grandit d'un sprint a l'autre a mesure que de nouvelles matieres entrent : je la note
  pour qu'elle ne se decouvre pas seulement le jour ou un paquet de l'une d'elles doit entrer en
  production, comme cela vient de faillir sur les sciences (`ADR-0081` refuse deja tout paquet dont
  le referentiel n'a pas de code). Route a `camille-po` pour priorisation, `theo-archi` pour la
  forme si un dispositif de detection plus tot est voulu.
- **Le piege `indicesNommentLaReponse`** (`theo-archi`, section 2.1 de son avis) : un paquet de
  sciences qui poserait ce drapeau a `true` rendrait `SCI-1` silencieusement non tenue, sans
  qu'aucun garde-fou ne le dise. Ce n'est pas un obstacle technique a router : c'est une consigne
  d'ecriture que je transmets telle quelle a `marc-dev` et `lea-dev` pour `S30-1` et `S30-2` - ne
  jamais poser ce drapeau sur un paquet de sciences.
- **L'ordre du cliquet de `CONS-1`** (`theo-archi`, section 2.3 de son avis) : si un garde-fou de
  comptage est voulu sur les consignes generiques, il doit etre ecrit **apres** `S30-9`, avec le
  compte mesure a ce moment-la, jamais avec 10 (le compte d'avant correctif). Aucune story de s30
  ne l'ecrit ; je le rappelle pour que personne ne le construise dans le mauvais ordre s'il est
  demande en cours de sprint.
- **La ligne "extraire une information d'un document scientifique"** (19e ligne du referentiel,
  non retenue par `DEC-0079`) : `theo-archi` chiffre deux issues (servir le texte seul, cout nul ;
  servir aussi le tableau, plusieurs couts d'architecture et un arbitrage d'accessibilite qui
  n'existe pas). Le choix reste a `camille-po` et `nadia-prof` pour un sprint ulterieur ; aucune
  story de s30 ne la sert sous aucune forme.
- **L'intitule de la question rappelee en fin de partie** (*"La question :"* ou rien, `theo-archi`
  au titre de `M12`) et **son poids visuel** : routes respectivement a `nadia-prof` (pedagogique) et
  `ines-design` (propose sans trancher), deja nommes ainsi par `theo-archi` et par `DEC-0079`. Je ne
  les tranche pas non plus.
- **Le garde-fou de coherence entre un bilan de matiere et le compte reel de ses lignes** : deja
  route par `camille-po` (`DEC-0079`, section 7) a elle-meme (normalisation des bilans) puis a
  `theo-archi` (garde-fou). Non engage a s30, je ne l'ajoute a aucune story.

## 8. Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise par moi dans ce planning : je
reprends du travail deja tranche par ses auteurs (`nadia-prof`, `theo-archi`, `camille-po`).

Ce que ce document tranche, qui releve de mon role de Scrum Master et non d'un autre :

1. **Affectation nominative des onze stories** entre `marc-dev`, `lea-dev` et `nadia-prof`
   (sections 4 et 5), avec l'ecart assume a la continuite de matiere sur `S30-7`/`S30-8` et son
   motif ecrit.
2. **Ordonnancement des deux developpeurs** autour de l'ecriture partagee de `catalogue.yaml`
   (section 5).
3. **Generalisation de la discipline de worktree dedie a toute mesure du reacteur**, pas seulement
   a la premiere ecriture d'un lot, pour tout agent et pour toute la duree de s30 (section 6) -
   tranche laissee ouverte par la retro de s29, close ici, a porter en amendement formel de
   `squad/README.md` a la retro de ce sprint.
4. **Renommage du repertoire de sprint** en `squad/sprints/s30-sciences-cm1-et-le-prix-des-
   reserves/` et correction des references qui pointaient vers l'ancien chemin sans slug
   (section 0).

## 9. Prochaines etapes

- `lea-dev` : `S30-1`, puis `S30-2`, puis `S30-4`, puis `S30-9` (strictement en serie, seule
  ecriture dans `catalogue.yaml` de tout le sprint), et `S30-3` en parallele a tout moment de cette
  serie. Ouvre `../recreo-s30-lea` (`git worktree add ../recreo-s30-lea -b s30-lea round-two`),
  verifie par `outils/verifier-lignee.sh` avant le premier commit, et mesure toute execution de
  `mvn` depuis ce meme worktree pour toute la duree du sprint (section 6).
- `marc-dev` : `S30-5`, `S30-6`, `S30-7`, `S30-8`, dans l'ordre de son choix, aucun des quatre ne
  touchant `catalogue.yaml`. Meme protocole de worktree (`../recreo-s30-marc`), meme discipline de
  mesure.
- `nadia-prof`, **Instance A** : audite `S30-3`, `S30-4`, `S30-5`, `S30-7`, `S30-8` des leur
  livraison ; conduit `S30-9` (`CONS-1`) des que `lea-dev` le livre ; conduit `S30-11` (passe
  transverse) a tout moment du sprint ; rejoue `S30-10` (`REV-3`, deja livree) sur les six paquets
  de sa reverification de `REV-1`.
- `nadia-prof`, **Instance B** : audite `S30-1` puis `S30-2` des leur livraison ; rejoue `S30-6`
  (`POS-R2`) des que la correction est livree.
- `theo-archi` : corrige "sept"/"onze" en "neuf"/"neuf" dans son avis de faisabilite (section 0) ;
  instruit, s'il le juge necessaire, la forme d'un dispositif de detection des referentiels sans
  code (section 7).
- `camille-po` : priorise, si elle le juge utile, le paiement de la dette des huit referentiels
  encore sans code (section 7) ; suit tout arbitrage de perimetre si un lot de s30 est refuse en
  cours de sprint.
