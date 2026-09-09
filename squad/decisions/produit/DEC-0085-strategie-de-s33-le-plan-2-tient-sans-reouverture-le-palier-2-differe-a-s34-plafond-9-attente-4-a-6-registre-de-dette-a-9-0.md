# DEC-0085 - Strategie de s33 : le Plan 2 tient sans reouverture, le palier 2 differe a s34,
# plafond 9 lignes et attente raisonnable 4 a 6, registre de dette porte de 7,5 a 9,0

- **Type** : execution du Plan 2 (`DEC-0083`) appliquee a s33 ; deux arbitrages de l'utilisatrice
  rendus le 2026-09-09 ; decisions de perimetre, de chiffrage et de routage de `camille-po` qui en
  decoulent.
- **Date** : 2026-09-09.
- **Auteur** : `camille-po`.
- **Sprint** : s33 (ouverture).
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision de
  strategie ne ferme et ne rouvre aucune ligne elle-meme ; les competences ligne a ligne sont
  nommees dans `docs/backlog/backlog.md` et dans `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee.
- **Lien** :
  `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md`
  (`nadia-prof`, branche `s33-nadia`, commit `a9ab05ec`, **non fusionnee dans `round-two` a la date
  de cette decision** - `sofia-sm` la fusionnera a l'ouverture du sprint ; chiffres, clauses et
  constats repris sans etre recalcules), `DEC-0083` (Plan 2, borne a trois instances, palier 2),
  `DEC-0084` (cloture de s32, dix stories routees), `DEC-0078` (`CONS-1` non bloquante), `DEC-0061`
  (`SCO-1`), `ADR-0081` (codes de ligne, decision de grain confiee a `camille-po` et `nadia-prof`
  ensemble), `ADR-0085` (registre d'identifiants), `ADR-0088` et `ADR-0089` (references de
  contenu), `squad/programme/MATRICE-couverture.md`, `docs/backlog/backlog.md` (sections "Backlog
  ouvert pour s33 (suite)", "Backlog ouvert pour s33 (remplissage)", "Backlog ouvert pour s34").

---

## 1. Les deux arbitrages de l'utilisatrice, rendus le 2026-09-09

1. **s33 suit le Plan 2**, celui que `DEC-0083` a retenu pour s32 a s34. **`DEC-0083` n'est pas
   amendee et le Plan 2 n'est pas rouvert.** L'objectif de meilleure couverture que l'utilisatrice a
   nomme se cherche **a l'interieur** du Plan 2, pas contre lui. Une confusion a eu lieu du fait de
   `camille-po` - la demande avait ete presentee comme un amendement possible - et l'utilisatrice a
   tranche : le plan tient pour les trois sprints. Le point est ecrit une fois, sans en faire un
   debat.
2. **Le palier 2 est reporte a s34.** Motif chiffre par `nadia-prof` : le cablage du chemin de jeu,
   son lot `L-c`, **prend un developpeur pour le sprint entier**, et le plafond de 9 lignes qu'elle
   chiffre pour s33 (section 3 ci-dessous) **suppose trois producteurs de contenu** pour trois
   instances d'audit - il n'en resterait que deux si `L-c` etait engage a s33. Les trois stories deja
   ecrites - `S33-PAL2-TROU-A`, `S33-PAL2-TROU-B`, `S33-PAL2-1` - glissent a s34, sous les memes
   identifiants (aucun renommage, pour ne pas casser les references deja posees par `DEC-0083`).
   Elle a vu et n'a pas retenu : engager `L-c` avec un plafond a rechiffrer a la baisse, et payer les
   deux trous de contrat seuls. **Le financement lui-meme n'est pas annule** : l'entree minimale
   (`QLM-CP-11` et `QLM-CP-14`, 2,0 pour deux lignes) reste arbitree (`DEC-0083`, section 13), seul
   le sprint qui la recoit change.

**Ce que ce report coute, dit sans l'adoucir.** `nadia-prof` ecrit que le vivier de s34 est deja, par
construction du Plan 2, ce que s33 n'aura pas ferme : au plus pauvre si s33 atteint son plafond de 9
lignes (aucune ligne `en cours` ne resterait alors dans le depot, section 8.1 de son arbitrage), a 3
a 5 lignes si s33 se comporte comme s32 (section 8.2). **Engager `L-c` a s34 y retire un troisieme
producteur de contenu au moment ou ce vivier est le plus bas de tout le Plan 2.** Ce n'est pas un
chiffre que `nadia-prof` pose elle-meme comme sien - c'est une consequence arithmetique qu'elle nomme
sans la chiffrer, et que cette decision ne chiffre pas non plus : c'est a `sofia-sm` de dire si le
plafond de s34 tient sous cette hypothese.

**Un report se compte, et celui-ci est le premier de ce lot precis.** Les trois stories du palier 2
avaient ete arbitrees pour s33 le 2026-09-08 (`DEC-0083`, section 13, meme jour que leur ecriture) ;
leur report a s34 aujourd'hui en est le premier. Ce n'est pas un second report de l'ouverture du
palier 2 elle-meme : cette question-la avait ete **routee** a l'instruction de `theo-archi`
(`DEC-0083`, section 11), pas reportee - une instruction en cours n'est pas un engagement qu'on
diffère.

## 2. Un fait trouve en verifiant les citations avant de les reprendre, corrige dans le meme geste

En reprenant les citations de `DEC-0083` vers l'avis de faisabilite de `theo-archi`, deux references
etaient perimees dans `docs/backlog/backlog.md` (jamais corrigees a l'ouverture de s33, alors que
`DEC-0084` avait deja corrige la meme erreur dans `DEC-0083` lui-meme) : le commit `dcbcd65a`, orphelin
et introuvable dans l'historique, aurait du etre `96c8efbe` ([THEO] s32 : avis de faisabilite de
l'achevement du palier 2) ; et la branche `s32-theo` qui le porte **est fusionnee** dans `round-two`
depuis le 2026-09-08 (commit `cb0b191e`), pas "non fusionnee" comme le texte le disait encore. Les
deux references sont corrigees dans les trois stories deplacees a s34 (`docs/backlog/backlog.md`,
section "Backlog ouvert pour s34"). Cette correction ne change aucun perimetre : `ADR-0088`, qui
vit sur cette branche, tranche deja les deux trous de contrat du palier 2 et le contrat de `domain`
porte deja `enonce` sur `DesignationVerdict` (verifie sur le fichier servi) ; ce qui manque encore,
et que `nadia-prof` confirme en jouant le 2026-09-09, est le rendu (`L-c`, `L-d`), pas l'ADR. Les deux
stories `S33-PAL2-TROU-A` et `S33-PAL2-TROU-B` sont mises a jour en consequence : leur prealable
n'est plus "l'ADR n'est pas ecrite", il est "le rendu n'existe pas encore".

## 3. La borne de s33 : 12,0, reprise et non rediscutee, composition instance par instance

**`DEC-0084` a deja tranche que la retenue de couture ne tombe pas : la borne de s33 reste 12,0**
(trois instances a 4,0, `PAR-9` ; plafond individuel dur 5,0, `PAR-8`). Cette decision ne rouvre pas
ce point.

Composition chiffree par `nadia-prof` (section 6 de son arbitrage), reprise sans recalcul :

| Instance | Postes | Total | Lignes |
|---|---|---|---|
| **A** | Remplissage `HG-CM1-08`/`-12` (2,0) ; reprise du meme paquet, `HG-R8` a `HG-R12` et le reliquat de `CONS-1` (0,5) ; `cm1-anglais`, `ANG-R3` a `R6` (0,5) ; passe transverse (0,5) ; marge `PAR-11` (0,5) | **4,0** | **+4** |
| **B** | `EMC-CP-07`, quatrieme passage (0,5) ; six paquets d'EMC CP portant les treize reserves neuves de s32 (3,0, section 5) ; marge non allouee (0,5) | **3,5** | **+1** |
| **C** | Reprise `EPS-CP-04` (0,5) ; reprise `EPS-CP-08` au tarif de lot, `PAR-14` (1,0) ; lot de l'effort et du corps, `EPS-CP-10`/`-11` (1,5) ; reserves du lot soldees dans le sprint (0,5) ; marge `PAR-11` (0,5) | **4,0** | **+4** |
| **Total** | | **11,5 sur 12,0** | **+9** |

**Terrains inchanges depuis s32** : A porte la portee D, l'histoire-geographie CM1 et l'anglais CM1
(instance d'accumulation, `PAR-12`) ; C porte l'education physique et sportive. **B ne retrouve pas
son terrain permanent de sciences CM1** ce sprint : elle en detient deux images (sciences CM1 depuis
s30-s31, EMC CP depuis s32), `PAR-7` lui permet les deux mais 4,0 n'en paient qu'une, et finir l'EMC
CP (`CAT-1` a son cinquieme sprint de report, `EXC-R5` a son deuxieme, un stereotype de genre
aggrave sur `REG-R3`) l'emporte sur toute ligne de sciences que `nadia-prof` nomme.

**`0,5` que je n'alloue pas** : la marge non allouee de l'instance B (section 6.3 de l'arbitrage)
peut acheter un paquet du residu du groupe 2a sur les sciences, ou un transfert ecrit vers A ou C
(`PAR-9`) ; elle n'achete aucune ligne. Ce choix revient a `sofia-sm` au planning, pas a cette
decision.

## 4. Plafond et attente, cote a cote, contre l'etat d'entree

**Etat d'entree de s33 : 66 / 138 = 47,8 %** (66 `couvert-valide`, 4 `en cours`, denominateur
atteignable 138 - compte de `DEC-0084`, repris et non recalcule).

| | Lignes | Taux |
|---|---|---|
| Plafond arithmetique de s33 | 66 + 9 = 75 / 138 | **54,3 %** |
| Attente raisonnable de `nadia-prof` | 66 + 4 a 6 = 70 a 72 / 138 | **50,7 % a 52,2 %** |

**Motif de l'attente, ligne par ligne (section 7 de l'arbitrage)** : sur les neuf lignes du plafond,
deux portent une attente de 1 et non un intervalle (`HG-CM1-02`, `HG-CM1-04` - la formulation juste
existe deja mot pour mot dans le meme fichier, ou les deux directions de reecriture sont nommees) ;
les sept autres portent une attente de 0 a 1 chacune. Cinq des neuf lignes sont des reprises dont
`nadia-prof` a deja ecrit le motif item par item ; quatre sont des premiers passages (deux
remplissages, deux lignes du corps). Si s33 se comporte comme s32 - resultat sous la borne basse de
l'attente - le sprint ferme 3 lignes et la couverture reste a 69/138 = 50,0 %, mais **le registre de
dette baisse quand meme a 2,5** (section 6) : c'est ce que le Plan 2 garantit independamment du
nombre de lignes fermees.

## 5. Cinq corrections que `nadia-prof` apporte a ses propres constats, tenues telles quelles

`nadia-prof` verifie ses propres constats avant de s'en servir, et deux d'entre eux sont structurels.

1. **"Le terrain sans paquet neuf est epuise fin s33" (ecrit le 2026-09-08) est faux.** Un lot neuf
   refuse au premier passage cree son paquet puis laisse sa ligne ouverte : la ligne quitte la
   colonne "exige un paquet neuf" pour entrer dans la colonne "le paquet existe deja". C'est
   exactement ce qui est arrive a `EPS-CP-04` et `EPS-CP-08`, refusees a s32. **Le terrain compte 7
   lignes fermables sans paquet neuf, et non 5 : mais 4 de ces 7 ont deja ete payees une fois** (leur
   premier passage a coute son ecriture, son audit, sa reecriture et son reaudit, pour la meme
   ligne). La lecture "le vivier de s33 est inhabituellement favorable" est vraie sur le prix
   marginal (facteur 2 sur le budget d'audit, mesure en section 5.7 de l'arbitrage) et fausse sur le
   prix total du depot.
2. **Le plafond de 6 lignes que `DEC-0083` avait indique pour s33 est perime, a la hausse.** Il
   supposait que s32 ferme les 8 lignes qu'il visait ; s32 en a ferme 3. Le plafond reel, chiffre en
   section 3 ci-dessus, est de 9.
3. **Le refus d'avance numero 6 de s32 ("`CONS-1` refuse toute ligne d'histoire-geographie fermee
   sans que la consigne nomme la tache de ses items") est retire, date du 2026-09-09.** `CONS-1` est
   **non bloquante depuis `DEC-0078`**, et `nadia-prof` n'avait pas le pouvoir d'en faire un motif de
   refus par une ligne de tableau sans le dire et sans le dater - fait qu'elle constate elle-meme
   apres que son instance A a ferme `HG-CM1-03` en ecrivant, dans le meme document, que `CONS-1` y
   est "non tenue". `CONS-1` ne refusera donc ni `HG-CM1-02`, ni `-04`, ni `-08`, ni `-12` a s33. A la
   place : la decision de grain de `CONS-4` (`S33-CONS-4`, deja au backlog) reste due **avant** le
   remplissage de `HG-CM1-08`/`-12`, pas apres - motif d'ordre, pas motif de refus.
4. **Son 0,5 sur `EMC-CP-07` est maintenu, pour un motif entierement different.** `FAB-1c` est paye ;
   le troisieme refus porte sur un defaut structurel des categories de la tache A (section 6
   ci-dessous), pas sur ce que le prix visait initialement.
5. **Le residu du groupe 2a vaut 2,5 payables, et non 3,0** comme ecrit le 2026-09-08 : `HG-R8`
   voyage gratuitement avec la reprise du meme paquet sous `PAR-10`. Correction en faveur de
   l'equipe, a la difference des deux precedentes sur ce meme poste.

## 6. Trois faits trouves en jouant, absents de toute liste de dette avant ce document

- **La cellule "se loger dans un espace urbain" de `HG-CM1-12` est deja depensee.** L'item 25 de
  `HG-CM1-11`, ligne fermee `couvert-valide`, sert deja cette connaissance ; un item ne compte que
  sur une ligne (`ADR-0081`, clause 5). Il reste 7 cellules libres sur 8 pour les 9 items neufs de
  `HG-CM1-12`, avec l'interdiction de retomber sur la cellule prise. `HG-CM1-11` n'est pas rouverte.
  Cette frontiere, `HIS-5`, gouverne les items neufs de `S33-HG-08-12` (`docs/backlog/backlog.md`).
- **Les trois categories de la tache A de `EMC-CP-07` ne sont pas disjointes, et le libelle n'en
  demande que deux.** Le ternaire "une regle qui vaut pour tous / un simple souhait / une loi" traite
  une loi comme distincte d'une regle qui vaut pour tous, alors que le libelle de la ligne enseigne
  precisement qu'une loi **est** une regle qui vaut pour tous. Le troisieme refus ne nommait que
  l'item 4 ; le meme defaut dort, non nomme, dans les items 5 et 6. La sortie **retire** une
  exigence : `EMC-11` demande que "une loi" cesse d'etre une troisieme proposition sur les items 4, 5
  et 6, pas qu'on en ajoute une. Portee dans `S33-EMCCP07`.
- **Les quatre indices de `SEC-R3` portent le repere lexical que la reprise doit oter des
  propositions.** Reecrire les huit distracteurs des items 8 a 11 sans toucher leurs indices ne
  corrige rien : l'indice de l'item 9, par exemple, dit "danger" et designerait toujours la bonne
  reponse une fois les trois propositions reecrites autour de ce mot. `EPS-7` nomme ce perimetre -
  huit distracteurs et quatre indices, pas huit distracteurs seuls - au meme prix. Portee dans
  `S33-EPS-SEC`.

## 7. Le registre de dette : de 7,5 a l'ouverture de s32 a 9,0 a l'ouverture de s33

**Le registre a grossi, et `nadia-prof` l'ecrit sans se menager : s32 a ferme trois lignes et laisse
la dette plus grosse qu'il ne l'a trouvee, sous le plan qu'elle avait elle-meme recommande pour
eviter cela.**

Le fait, chiffre : 7,5 (ouverture s32) -> **9,0 (ouverture s33)**, plus `CONS-3` et `CONS-4` toujours
non chiffrees. `nadia-prof` situe elle-meme le trou dans sa propre regle : le Plan 2 solde *les
reserves qu'un lot neuf ouvre*, et la croissance de s32 ne vient pas de la. Elle vient (a) de
**quatre refus**, qui ne sont pas des reserves de lot mais des lignes bloquees, et (b) de **sept
reserves neuves trouvees en reauditant des paquets deja fermes**. **Sa regle ne dit rien du reaudit
d'un paquet ancien**, et c'est exactement la population qui a grossi.

**Elle refuse de combler ce trou par une clause dans son propre document**, et le motif qu'elle donne
est mesure : **cinq des sept reserves neuves viennent de ses propres comptes incomplets** - `EMO-R3`,
`EXC-R6` et `POL-R3` sont des enonces que ses barres `EMO-R1`, `EXC-R4` et `POL-R1` n'avaient pas
comptes ; `POL-R4` est le defaut de `PER-R1` qu'elle n'avait pas vu sur le paquet voisin ; `REG-R2`
sont deux distracteurs que `REG-R1` n'avait pas comptes. Une clause qui ferait payer a l'equipe le
recomptage de ses propres barres serait le durcissement qu'elle s'interdit. **Elle nomme le trou et
le route a `camille-po` ; cette decision le nomme a son tour et ne le comble pas non plus** : le
combler supposerait une regle de gouvernance sur le traitement du reaudit d'un paquet ferme, question
qui depasse le perimetre d'une strategie de sprint et que je ne tranche pas ici sans instruction
supplementaire.

**Composition des treize reserves neuves de s32, payees a s33 (0,5 par paquet touche, `PAR-10`, et
non 0,5 par code)** : `cm1-anglais` (`ANG-R3` a `R6`), `cp-emc-emotions-autrui` (`EMO-R3`),
`cp-emc-exclusion` (`EXC-R6`, plus `EXC-R1` et `EXC-R5` deja impayees), `cp-emc-formules-de-politesse`
(`POL-R3`, `POL-R4`), `cp-emc-permis-interdit` (`PER-R2`, plus `CAT-1` non payee),
`cp-emc-regles-de-vie` (`REG-R2`, `REG-R3`, plus `CAT-1` non payee) - `cm1-histoire-geographie`
(`HG-R9`, `HG-R10`) voyage gratuitement avec la reprise du meme paquet. **`REG-R3` merite d'etre
nommee a part** : l'adulte de l'ecole est une femme 7 fois sur 7 sur ce paquet, 17 contre 1 sur l'EMC
CP entier - un stereotype de genre que `nadia-prof` avait signale le 2026-09-05 pour que le prochain
lot ne l'aggrave pas, et qu'il a aggrave.

**Projection a la cloture de s33, si le plafond de 9 lignes est atteint : 9,0 - 6,5 payes = 2,5**,
plus `CONS-3` et `CONS-4` non chiffrees - **le registre baisse pour la premiere fois depuis s30**.

## 8. Clause neuve `PAR-14`, et les identifiants alloues dans le meme diff

**`PAR-14`, nouvelle, non retroactive.** Une reprise dont les corrections touchent plus de la moitie
des items d'un paquet se paie au tarif d'un lot - 1,0 quand `nadia-prof` est la source des motifs,
1,5 sinon - et non au tarif d'un rejeu (0,5). Mesuree sur `EPS-CP-08` (six items sur
11) : quand la majorite des items change, l'audit precedent ne couvre plus rien, et `nadia-prof` doit
rebalayer l'integralite des conditions du paquet comme pour un premier passage. La clause ne releve
la barre d'aucun increment ; elle est une regle de son propre budget d'audit. Portee dans
`S33-EPS-JEU`.

**Quatre identifiants alloues dans le meme diff, tous sur des prefixes deja alloues** : `PAR-14`,
`EMC-11`, `EPS-7`, `HIS-5`. Registre `squad/decisions/REGISTRE-identifiants.md` porte de 578/97 a
**582/97** (`nadia-prof`, methode de comptage a cote du chiffre, section 13 de son arbitrage) - ce
compte n'est pas encore visible sur `round-two` tant que `s33-nadia` n'est pas fusionnee ; `sofia-sm`
le verifiera au merge.

## 9. `D7` (verification de `SCO-1`) ramenee a 0,0 - une liberation de budget, pas une aggravation

`DEC-0084` portait `D7` a 0,5, planifiee a s33. `nadia-prof` a rejoue `SCO-1` une troisieme fois en
trois jours, gratuitement, dans des parties qu'elle jouait de toute facon : cinq parties completes,
cinq scores de 10/10, pour 18 a 22 appuis de trop. **`D7` n'est plus due pour l'observation** ; elle
redeviendra due, a 0,5, le jour ou il y aura un remede a verifier - et il n'y en a pas encore. Le
remede (`SCO-1` a `SCO-7`) reste au ressort de `theo-archi`, non chiffre par `nadia-prof`. `S33-SCO-1`
reste au backlog comme preuve, sans cout d'audit associe.

## 10. Ce que la borne de 12,0 ne finance pas a s33, sans l'adoucir

- **Aucun lot de sciences CM1**, matiere pour laquelle le produit dispose deja de quatre lignes
  fermees et d'une instance qui en a l'image. `nadia-prof` l'ecrit sans detour : *"la dette la plus
  ancienne du depot et le terrain le plus fourni appartiennent a la meme instance, et 4,0 en paie
  une."* Ce n'est pas un arbitrage de terrain, c'est de l'arithmetique sous `PAR-2` et `PAR-8`
  (section 3 ci-dessus).
- **L'instruction des onze lignes sans annotation de jouabilite** (portee a `sofia-sm` pour s34 dans
  le perimetre indicatif de `DEC-0083`).
- **Le groupe 2b** (`CONS-2`, `SAI-R1`, `ERR-R1`, reliquat de `CONS-1` hors des lignes ouvertes a
  s33).
- **`CONS-3`**, toujours non chiffree.
- **1,5 du residu du groupe 2a** (sur 2,5 payables, `POS-R3`, `FOP-R4`, `DRO-R4` sur les sciences et
  l'EMC CM1, sans lettre d'instance nommee - `sofia-sm` tranche l'affectation au planning, cette
  decision ne la presume pas).

## 11. Ce que je decide

1. **`DEC-0083` reste Acceptee, telle qu'ecrite et amendee. Le Plan 2 n'est pas rouvert.**
2. **Le palier 2 (`S33-PAL2-TROU-A`, `S33-PAL2-TROU-B`, `S33-PAL2-1`) est reporte a s34**, sous les
   memes identifiants, motif et cout nommes en section 1 - `docs/backlog/backlog.md`, section
   "Backlog ouvert pour s34".
3. **La borne de s33 reste 12,0**, composition reprise en section 3 ; plafond 9 lignes, attente 4 a
   6 (section 4).
4. **Une story neuve est ouverte** : `S33-HG-08-12` (remplissage de `HG-CM1-08` et `HG-CM1-12`),
   necessaire pour porter `HIS-5` et le fait de la cellule deja depensee (section 6) - sans cette
   story, ce que la composition de la section 3 finance pour l'instance A n'aurait pas de story pour
   le porter, en contradiction avec le principe que "une story qui n'est pas dans `docs/backlog/`
   n'existe pas."
5. **Les conditions `PAR-14`, `EMC-11`, `EPS-7`, `HIS-5` et les trois faits de la section 6 sont
   inscrits dans les criteres d'acceptation des stories concernees** : `S33-EPS-JEU` (`PAR-14`),
   `S33-EMCCP07` (`EMC-11`), `S33-EPS-SEC` (`EPS-7`), `S33-HG-08-12` (`HIS-5`).
6. **Deux references perimees, corrigees** dans les trois stories du palier 2 (section 2) : le
   commit orphelin `dcbcd65a` remplace par `96c8efbe`, et le statut de fusion de `s32-theo` mis a
   jour (fusionnee depuis le 2026-09-08, `cb0b191e`) ; le prealable d'ADR des deux trous de contrat
   est marque leve (`ADR-0088`), le prealable de rendu reste ouvert.
7. **Le trou dans la regle du Plan 2** (le reaudit d'un paquet ferme n'est couvert par aucune clause
   de solde) est nomme (section 7) et **n'est pas comble ici** : il reste une question ouverte, sans
   proprietaire de code puisqu'aucun changement de code n'en decoule - c'est une question de regle de
   gouvernance, que je ne tranche pas seule dans une decision de strategie de sprint.

## 12. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et n'en ferme aucune
  ligne : les statuts ne bougent qu'au verdict de `nadia-prof`.
- Elle ne prononce aucun Done pedagogique.
- Elle ne recalcule aucun chiffre de `nadia-prof` : la borne, le plafond, l'attente, le registre de
  dette et le prix de chaque poste sont repris tels qu'elle les chiffre dans son arbitrage prealable
  de s33 (branche `s33-nadia`, commit `a9ab05ec`, non fusionnee).
- Elle ne fusionne pas la branche `s33-nadia` : `sofia-sm` le fait a l'ouverture du sprint.
- Elle ne fixe l'affectation nominative d'aucune instance ni d'aucun developpeur, y compris pour
  `S33-HG-08-12` et pour les deux paquets sans lettre nommee du residu du groupe 2a : c'est le travail
  du planning de s33, par `sofia-sm`.
- Elle ne tranche pas la decision de grain de `S33-CONS-4` ni celle, nommee par `nadia-prof` (section
  5.6 de son arbitrage), sur le nombre de paquets du lot de l'effort et du corps : les deux restent
  dues, confiees a `camille-po` et `nadia-prof` ensemble par `ADR-0081`.
- Elle ne comble pas le trou de la regle du Plan 2 sur le reaudit des paquets fermes (section 7) :
  elle le nomme et le laisse ouvert.
- Elle ne decide d'aucune question technique ou d'architecture : le remede de `SCO-1` a `SCO-7`, le
  chemin de jeu du palier 2 (`L-a` a `L-e`) et l'`ADR` des deux trous de contrat restent au ressort de
  `theo-archi`.
