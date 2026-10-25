# s57 - palier 5 : questionner le monde CE1 dans s57 (V3 de l'utilisatrice), ce que sa reponse depasse et a quelles conditions j'audite

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-25, 11:13 (UTC+01:00), lu par `date`.
- **Arbre** : `round-two` a `c5d07a4a`, branche `s57-nadia-p5-c`, arbre propre avant ecriture.
- **Type** : arbitrage sur une reponse de l'utilisatrice. **Aucun Done n'est prononce**, aucune fiche n'est modifiee. Ce
  document ne touche ni code, ni paquet, ni catalogue, ni `DEC`, ni matrice, ni registre. **Aucun identifiant de condition
  neuf** : les codes `C1` a `C7`, `R1` a `R5` ci-dessous sont des reperes de lecture de ce fichier, pas des conditions
  allouables.
- **Je n'ai rien joue, et ce n'est pas un audit degrade** : aucun paquet de questionner le monde CE1 n'existe (section 0).
  Il n'y a rien a jouer ; ce que j'ecris est la condition de l'audit a venir.
- **Lien** : `arbitrage-nadia-preparation-palier-3-ce1.md` 1.3 ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`
  (sections 2, 3, 5, 6) ; `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` (4.2, 11.3) ;
  `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 1.1 ; `arbitrage-nadia-palier-5-lp7-d5.md` (2.3, 6.3, 8) ; `DEC-0120`
  A.184, A.204, A.206, A.207, et A.214 a A.219 (non fusionnes, section 0) ; `proposition-camille-palier-5-ce1.md` (F3, 5, 13).

## Resume

| # | Question | Ma reponse | Section |
|---|---|---|---|
| Q1 | La reponse vaut-elle depassement de ma regle ? | **Oui : depassement de ma regle pedagogique** (1.3 de la preparation du palier 3, sous ma regle de s55), pas d'une simple regle de produit. Elle est la sienne, ecrite comme telle. Je l'admets aujourd'hui, a une condition de consignation : le texte de V3 ecrit dans `DEC-0120` a cote de ses mots avant l'ouverture du premier lot | 2 |
| Q1 bis | A quelles conditions j'audite | une instance, pas B ; au plus tot le palier 6 ; **un seul lot de questionner le monde au premier palier, puis deux au plus par palier** ; une ligne par lot ; `QLM-CE1-01` d'abord ; `-09` et `-11` refusees sans materiau ; `-07` recevable sur sa moitie textuelle, apres les neuf | 3 |
| Q2 | Risque, cousinage | une cousine servie existe pour chacune des neuf lignes ; le risque est la charge d'un palier qui porte deux lots illustres de B et des lots dont l'exactitude n'a que moi pour garde | 4 |
| Q3 | `FRA-CE1-08`, `-09` | **non couvertes** par sa reponse ; elles restent hors de s57 | 5 |
| Q4 | Lecture avant `verify` | applicable a chaque paquet de questionner le monde CE1 ; ce que je lis et quand, en 6 | 6 |
| Q5 | Bareme et charge | **1,0 par lot, lecture comprise** ; un relevement ne change pas le nombre de lots que je tiens : **quatre par palier** quand B y porte deux lots illustres | 7 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Ses mots | message de l'agent lanceur, mot pour mot (forme 2 de `DEC-0062`, non lus de sa main) | *"on part sur V3 et si besoin au releve pour absorber le depassement"* |
| Le texte de V3 | `git grep -n "\bV3\b"` sur `round-two`, puis sur les branches `s57-*` (dossier du sprint) | **aucun V3 sur questionner le monde dans le depot.** Les V3 trouves sont d'autres objets : ma barre de manipulation du palier 1 de s25, ma reserve du veto sur le pli. Je lis V3 tel que l'agent lanceur me le transmet : *"un depassement ecrit de l'utilisatrice qui fait entrer questionner le monde CE1 dans s57, comme pour l'horloge"* |
| `DEC-0120` A.214 a A.219, section 13 de la proposition | `grep "^### A\."` sur `round-two` : dernier amendement A.209 ; `git show s57-camille-p5-dix-paliers:...` | **absents de `round-two` a `c5d07a4a`** ; ils vivent sur la branche `s57-camille-p5-dix-paliers` (`f2bd783e`, non fusionnee). Lus par `git show` ; je les cite avec cette provenance |
| Stories de questionner le monde CE1 | `grep -c "^### .*QLM-CE1"` sur `docs/backlog/backlog.md` | **0** |
| Paquets | `ls` du repertoire de contenu | aucun `ce1-qlm-*` ; 13 paquets `cp-qlm-*` |
| Matrice | commande de la *Methode du compte*, filtree sur la matiere et le niveau | questionner le monde CP : 16 lignes, 12 `couvert-valide`, 4 autres ; questionner le monde CE1 : 12 `non couvert` |
| Cousines servies | `grep -c "paquet: <paquet>$"` sur le catalogue | `cp-qlm-etats-eau`, `cp-qlm-hygiene`, `cp-qlm-matiere`, `cp-qlm-autrefois-aujourdhui`, `cp-qlm-cycle-de-vie`, `cp-frise-de-la-journee`, `cm1-sciences-etapes-developpement`, `cm1-sciences-regimes-alimentaires`, `cm1-sciences-melanges`, `cm1-sciences-etats-matiere`, `cp-eps-regles-de-securite`, `cm1-histoire-geographie-nommer-en-geographie` : 1 chacun. `cp-qlm-situer-la-france` : 0 |
| `NRC-CE1-1` sur du contenu reel | `git grep -c "NRC-CE1-1" -- squad/conformite` | quatre fiches d'EMC CE1 (`regle-sanction` 13, `interet-commun` 11, `biens-communs` 3, `droit-devoir` 3) et une de francais |
| Ma dette `TRV-CE1-1` | `grep -c "2026-10-2"` sur `ARBITRAGE-PREALABLE-s48-francais-ce1.md` | **0** : toujours non ecrite (section 9) |

---

## 1. Deux corrections de mes propres constats, avant d'arbitrer

### 1.1 L'objet de ma regle 1.3 n'est pas un lot sans cousine

J'ai ecrit le 2026-10-13 (instruction, 5.2) : *"Un premier lot de questionner le monde CE1 releve de ma regle de s55 sans
l'amender : il ne prend pas le sprint ou une autre instance audite un paquet sans cousine."* Ma regle de s55 (1.1) est ecrite
pour **un lot sans cousine**. Dans le meme document, section 5.1, j'ai juge les neuf lignes textuelles et la moitie textuelle
de `-07` **non neuves**, cousines servies nommees. La proposition de `camille-po` (F3) lit *"un premier lot sans cousine"* :
c'est la lecture que ma phrase invitait, et ma propre table la dement.

**Ce que la regle visait reellement** : le premier lot d'une matiere et d'un niveau jamais audites, dont les conditions neuves
n'ont rencontre aucun contenu de cette matiere. Pas un lot sans cousine.

**Ce que la correction ne fait pas** : elle ne leve pas la regle, et je ne m'en sers pas pour la lever. Une regle que son
auteure relit a la baisse le jour ou on la conteste est le caprice que je m'interdis. Elle change seulement le poids du motif,
et je m'en sers deux fois plus bas : en 4, pour le cousinage ; en 5, ou `FRA-CE1-08` et `-09` sont, elles, sans cousine.

### 1.2 `NRC-CE1-1` a rencontre du contenu

Le 2026-10-13, `NRC-CE1-1` n'avait mordu aucun paquet. Depuis, elle a ete jugee sur les quatre paquets d'EMC CE1 servis, et elle
a trouve un defaut sur l'un d'eux (`FICHE-bandit-manchot-regle-sanction-ce1.md`, point `M1`, partie `NRC-CE1-1`). Ce n'est plus
une condition sans contenu. **Aucun garde-fou ne la tient pour autant** : je reste seule a comparer deux paquets.

---

## 2. Q1 : sa reponse vaut depassement de ma regle pedagogique

**Verdict : oui.** Motifs :

1. **La regle est la mienne, par son auteure et par son motif** : elle protege l'attention de ma porte dans un sprint qui a deja
   eu son terrain sans cousine. Ce n'est pas une regle de produit comme `PAR-8`, une borne de palier ou un nombre de paliers.
2. **Son objet est la composition, pas une condition de Done.** Elle dit *quand* un lot s'audite, pas *ce qu'il doit tenir*. Pour
   cette famille de regles, j'ai ecrit deux fois d'avance que l'ecrit de l'utilisatrice la depasse et que le depassement est le
   sien : pour l'horloge (*"c'est un depassement de `PAR-13` qui est le sien"*, `arbitrage-nadia-palier-5-lp7-d5.md` 2.3) et pour
   la borne des paliers 4 et 5 (`arbitrage-nadia-palier-4.md` 2.3, 8.4). Refuser aujourd'hui la meme forme pour questionner le
   monde serait deplacer mon poteau dans l'autre sens.
3. **Ce n'est pas `PAR-13`.** Les lignes textuelles de questionner le monde CE1 ne sont pas un terrain neuf (instruction, 5.1).
   Le depassement porte sur ma regle 1.3. Celui d'A.206 ne vaut que pour `MAT-CE1-11` ; il ne couvre pas questionner le monde.
   V3 le couvre.
4. **Ce que sa reponse ne depasse pas, et ne pretend pas depasser** : les conditions de Done de chaque ligne (instruction,
   sections 2 et 3 : `NRC-CE1-1`, `QMT-CE1-1`, `QMT-CE1-2`, les conditions par ligne, `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`,
   `-2`, `-5`, `FER-1` a `FER-3`, `CYC-1`, `BIA-4`), les plafonds, et mon verdict. J'audite chaque lot comme un lot de s58.

**Ce que j'exige pour l'admettre, et une seule chose** : que `camille-po` consigne dans `DEC-0120`, a cote de ses mots et avant
l'ouverture du premier lot, **le texte de V3 tel qu'il lui a ete presente**. Ce n'est pas une forme : *"V3"* ne renvoie a aucun
texte du depot (section 0), et c'est ce texte qui dit quelles lignes et quelle regle elle depasse. Une reference qui ne pointe
vers rien ne fonde pas une entree. C'est ce qui a ete fait pour l'horloge (A.206). **Je ne lui demande pas de reecrire sa
reponse**, et j'admets le depassement des aujourd'hui sur le texte que l'agent lanceur m'a transmis.

---

## 3. Q1 bis : les conditions de mon audit

| # | Condition | Motif |
|---|---|---|
| C1 | **Une seule instance porte tous les lots de questionner le monde CE1 de s57**, et ce n'est pas B. A, A' ou une instance C : le choix est a `camille-po`, avec un developpeur distinct de `lea-dev` | `PAR-2` : questionner le monde CE1 compte avec questionner le monde CP (instruction 5.3). B porte les mathematiques CE1 seule et deux lots illustres par palier a 4,0 : un lot de plus la met a 5,0 au bareme avec trois lots, au-dessus des le premier surcout. La clause *"pas tant qu'A' existe"* ne mord pas : elle vise les terrains neufs |
| C2 | **Au plus tot le palier 6.** Pas le palier 5 | la composition du palier 5 est arretee ; aucune story, aucun paquet ; la lecture avant `verify` exige un premier jet |
| C3 | **Le premier lot de questionner le monde est seul dans son palier pour son instance**, et jamais dans le palier ou `MAT-CE1-11` est audite | c'est l'objet exact de ma regle (1.1) : je vois les conditions neuves de la matiere sur un seul paquet avant d'en prendre deux. C'est aussi le premier prix mesure de la matiere (instruction 6.2). L'horloge est un terrain sans cousine : je ne l'ajoute pas au premier lot d'une matiere neuve |
| C4 | **Ensuite, deux lots au plus par palier.** Si le premier lot est REFUSE, le palier suivant porte son rejeu et au plus un lot neuf | 7.3 : c'est ce que je tiens a cote de deux lots illustres de B. `PAR-8` en permettrait trois ; je n'en prends que deux, par le motif de charge, pas par une regle nouvelle |
| C5 | **Une ligne par lot**, comme pour tous les lots de s57 | un verdict, un plafond et une comparaison `NRC-CE1-1` par ligne |
| C6 | **Prealables avant l'ouverture du premier lot** : V3 consigne (section 2) ; une story par ligne, mes conditions de ligne reprises en criteres, sans les traduire ; le premier jet du paquet ; mon arbitrage prealable de l'instance pour ce palier (0,5, dans le fixe) | aucune story n'existe (section 0) |
| C7 | **Ordre** : `QLM-CE1-01` seule d'abord, puis l'ordre de `camille-po` : `-02`, `-03` ; `-04`, `-05` ; `-06`, `-08` ; `-10`, `-12` | `-01` a une liste fermee (`DVP-CE1-1`), une comparaison `NRC-CE1-1` reelle avec `cp-qlm-cycle-de-vie` et `CYC-1` avec `cm1-sciences-etapes-developpement`, et un risque d'exactitude bas. Si `NRC-CE1-1` ou `QMT-CE1-2` mordent sur cette matiere, je le sais avant les lignes ou l'exactitude pese le plus (`-03` a `-06`) |

**Calendrier qui en resulte**, cadence de B nominale d'A.215 :

| Palier | B (`D5`) | Instance de questionner le monde | `couvert-valide` de s57 au plus |
|---|---|---|---|
| 6 | `MAT-CE1-01`, `-02` | `QLM-CE1-01` | 43 + 1 |
| 7 | `MAT-CE1-09`, `-10` | `QLM-CE1-02`, `-03` | 43 + 3 |
| 8 | `MAT-CE1-11` | `QLM-CE1-04`, `-05` | 43 + 5 |
| 9 | aucun lot | `QLM-CE1-06`, `-08` | 43 + 7 |
| 10 | aucun lot | `QLM-CE1-10`, `-12` | **52 sur 64 = 81,3 %** |

[calcul, suppose : chaque lot obtient son Done au premier audit ou dans la marge, et la cadence de `D5` tient]. **Aucune marge de
calendrier** : un glissement pousse `-12` a s58. **Repli de `D5`** (A.215) : `MAT-CE1-11` passe au palier 9, a cote de `-06` et
`-08`, ce que C3 admet, ce ne sont plus des premiers lots. **Si les prealables manquent au palier 6**, le premier lot ouvre au
palier *p* et s57 sert 1 + 2 x (10 - *p*) lignes : 7 au palier 7, 5 au palier 8, 3 au palier 9, 1 au palier 10. Les paliers 9 et
10 seuls ne donnent donc que trois lignes.

**Ce calendrier est un plafond, pas un engagement de ma part.** Un lot refuse glisse ; il ne recoit pas un verdict plus doux pour
tenir le palier 10.

**Les trois lignes en attente d'un materiau** :

| Ligne | Sans materiau | Motif |
|---|---|---|
| `QLM-CE1-09` | **refusee** | aucune moitie textuelle : lire une legende sans plan est une contrefacon (`CON-1`, instruction 3.9) ; la forme degradee exige elle aussi un plan dessine (11.3) |
| `QLM-CE1-11` | **refusee** | aucune moitie textuelle (`CON-1`, instruction 3.11) |
| `QLM-CE1-07`, moitie dessinee | **refusee** | `CIR-CE1-2` : un circuit dessine, jamais un circuit decrit par des mots |
| `QLM-CE1-07`, moitie textuelle | **recevable, non refusee** | `CIR-CE1-1`, plafond `en cours` avec sa mention (instruction 3.7) : +1 au compteur, 0 en `couvert-valide`. Elle vient apres les neuf ; le calendrier ci-dessus n'a plus de palier pour elle, donc s58 sauf palier libere |

Les dessins de ces trois lignes sont hors du perimetre rouvert par `D5` (mathematiques seules, `DEC-0123` 3), et chacun est un
terrain neuf (`PAR-13`) que ni V3, tel que transmis, ni A.206 ne couvrent.

---

## 4. Q2 : le risque, et ce que je compte comme cousine

### 4.1 Ce qui est une cousine

Une cousine est **une condition qui a deja mordu un paquet servi**, jugee condition par condition (instruction, section 5). Le taux
de couverture d'une matiere n'en est pas une : les 12 lignes `couvert-valide` sur 16 de questionner le monde CP ne comptent que
par les paquets servis dont les conditions sont cousines des miennes.

| Ligne de CE1 | Cousine servie (catalogue : 1) |
|---|---|
| `-01` | `cm1-sciences-etapes-developpement` ; `cp-qlm-cycle-de-vie` |
| `-02` | `cm1-sciences-regimes-alimentaires` |
| `-03` | `cp-qlm-hygiene` |
| `-04` | `cp-qlm-matiere` (classer), `cp-qlm-hygiene` |
| `-05` | `cp-qlm-etats-eau`, `cm1-sciences-etats-matiere` |
| `-06` | `cp-qlm-matiere`, `cm1-sciences-melanges` |
| `-08` | `cp-eps-regles-de-securite` |
| `-10` | `cm1-histoire-geographie-nommer-en-geographie` |
| `-12` | `cp-qlm-autrefois-aujourdhui`, `cp-frise-de-la-journee` |
| transverses | `TRV-CE1-*` et `LON-CE1-*` (francais, mathematiques, EMC CE1) ; `NRC-CE1-1` (EMC CE1, 1.2) ; `BIA-4`, `FER-1` a `FER-3` (questionner le monde CP) |

**Reponse** : une cousine servie existe pour chacune des neuf lignes ; le premier lot n'est pas un lot sans cousine (1.1). **Ce
qui n'a aucune cousine** : `QMT-CE1-1`, liste fermee de mots exclus, jamais rencontree (encodable, non encodee) ; et la verification
d'un fait discutable de sciences de CE1 contre une source ecrite en commentaire (`QMT-CE1-2` : ses cousines `HIS-3` et `EMC-7` ont
mordu de l'histoire et de l'EMC, jamais un fait de sciences au CE1).

### 4.2 Le risque, dans le meme sprint que `D5`, `LP7`, `LP8`, `LP9`

| # | Risque | Ce qui le tient |
|---|---|---|
| R1 | **Charge d'un palier mixte.** B y porte deux lots illustres, chacun avec sa lecture avant `verify`, sa garde M et des conditions *"ce que le dessin montre"* que je tiens seule a 176 px ; l'instance de questionner le monde y porte des faits que je tiens seule. Deux natures de verification dans une meme fenetre. Fait mesure : au palier 4, six lots, chacun refuse ou valide sous reserve au premier passage (A.200). Je n'en tire aucune causalite ; c'est la seule mesure d'un palier au-dessus de 8,0 | C3, C4 ; quatre lots au plus par palier (7.3) |
| R2 | **Exactitude.** Une erreur factuelle est un refus a elle seule, et c'est sur cette matiere qu'elle passe le plus facilement : le metal que l'aimant n'attire pas, la buee qui n'est pas de la vapeur, la pomme de terre rangee avec les feculents, le regime d'un animal | je lis chaque fait contre sa source **des la lecture avant `verify`**, pas seulement a l'audit (6) |
| R3 | **`NRC-CE1-1`** : chaque lot ajoute un a trois paquets voisins a lire | la table de 6.2 dit lesquels, ligne par ligne |
| R4 | **`MAT-CE1-11`**, terrain sans cousine, au palier 8 (9 en repli) a cote de lots de questionner le monde | admis : ce ne sont plus des premiers lots (C3) |
| R5 | **Aucune marge de calendrier** (3) : la tentation de valider pour tenir le palier 10 | un refus glisse a s58 ; c'est ecrit d'avance |

`LP7`, `LP8`, `LP9` sont au palier 5, ou aucun lot de questionner le monde n'entre (C2) : aucun cumul avec eux, sauf un rejeu qui
glisse au palier 6, compte alors dans les quatre lots de ce palier.

---

## 5. Q3 : `FRA-CE1-08` et `-09` ne sont pas couvertes

**Ce que je retiens : elles restent hors de s57.** Motifs :

1. **Sa reponse ne les nomme pas**, et V3, tel que transmis, ne parle que de questionner le monde CE1. Je lis un depassement a la
   portee de ses mots, comme A.216 lit celui de l'horloge (*"ne vaut que pour `-11`"*). L'etendre serait ma lecture, pas sa decision.
2. **Le motif n'est pas le meme** (1.1) : `-08` et `-09` sont reellement sans cousine (`OMP`, `RLP`, s55 1.1), et la condition 4
   est aussi une condition de mon accord sur `PAR-2` pour le francais CE1 a deux instances (criteres, 4.2).

**Si elle les veut dans s57**, elle l'ecrit en les nommant, et le depassement est le sien. Je tiendrais alors : `-09` d'abord, apres
`-15` (`RLP-CE1-1`) ; **une seule des deux dans s57** (au plus un lot sans cousine par sprint sur A, s55 1.1, que sa reponse ne
toucherait pas) ; jamais dans le palier de `MAT-CE1-11`, ni dans celui du premier lot de questionner le monde. Ce n'est pas une
question pour elle aujourd'hui.

---

## 6. Q4 : la lecture avant `verify` sur les paquets de questionner le monde

### 6.1 Portee et moment

**Applicable a chaque paquet** : ce sont des paquets ecrits dans un lot (A.184, portee de `D4` ; A.204). **Quand** : le developpeur
commite le premier jet complet sur la branche du lot et l'ecrit dans `planning.md`, **avant tout `verify` complet** ; je lis dans le
palier ou le lot est engage. Un paquet ecrit plus tot est lu a l'ouverture de son lot, tel qu'il est alors : une lecture faite avant
n'apporte aucun credit (1.3 de la preparation du palier 3). **Ecrit produit** : `lecture-nadia-premier-jet-<paquet>.md`, les items
que je refuserais et leur motif, sans verdict, aucune fiche modifiee, aucun identifiant neuf.

### 6.2 Ce que je lis

1. **Chaque item** : enonce, trois propositions, bonne reponse, indice, explication ; contre les conditions de la ligne et
   `TRV-CE1-2` (familles de distracteurs nommees a cote de l'item, bonne reponse la plus longue dans la moitie des items au plus),
   `TRV-CE1-5`, `TRV-CE1-6`, `LON-CE1-1`, `-2`, `-5`, `QMT-CE1-1`, `FER-1` a `FER-3`.
2. **Le commentaire du paquet** : la liste fermee de la ligne et la source de chaque fait discutable (`QMT-CE1-2`). **Je verifie
   chaque fait contre sa source**, pas seulement sa presence.
3. **Les paquets voisins**, pour `NRC-CE1-1` et `CYC-1` :

| Ligne | Paquets que je relis |
|---|---|
| `-01` | `cp-qlm-cycle-de-vie`, `cm1-sciences-etapes-developpement` |
| `-02` | `cm1-sciences-regimes-alimentaires` |
| `-03` | `cp-qlm-hygiene` ; le paquet de `-01` (germination, `BPL-CE1-2` et `DVP-CE1-2`) |
| `-04` | `cp-qlm-hygiene`, `cp-qlm-matiere` |
| `-05` | `cp-qlm-etats-eau`, `cm1-sciences-etats-matiere` |
| `-06` | `cp-qlm-matiere`, `cm1-sciences-melanges` |
| `-08` | `cp-eps-regles-de-securite` ; la frontiere avec `-07` (aucune pile) |
| `-10` | `cm1-histoire-geographie-nommer-en-geographie` |
| `-12` | `cp-frise-de-la-journee`, `cp-qlm-autrefois-aujourdhui` |

**Ce que la lecture ne remplace pas** : l'ecran, le pli et la partie jouee, que je vois a l'audit sur le commit joue, jar servi.

---

## 7. Q5 : bareme, relevement, plafond de ma charge

### 7.1 Ce que je chiffre pour un lot de questionner le monde

**1,0 par lot au bareme, la lecture avant `verify` et la correction qui la suit comprises** (meme regle qu'A.204 pour les lots a
1,5). Une seconde lecture va sur la marge ; un refus apres le `verify` se facture comme avant. **Fixe : 1,0 par instance et par
palier** (arbitrage 0,5, marge 0,5). **Aucune mesure n'existe** : le premier prix mesure sera celui de `QLM-CE1-01`. Je n'invente
pas de supplement pour un premier lot.

| Palier | Instance de questionner le monde, bareme | a 0,10 par lot (palier 3) | a 0,67 par lot (palier 4) | Palier avec B, bareme | Palier avec B, a 0,67 |
|---|---|---|---|---|---|
| 6 (1 lot) | 2,00 | 2,10 | 2,67 | 6,00 | 8,00 |
| 7 (2 lots) | 3,00 | 3,20 | 4,33 | 7,00 | 9,67 |
| 8 (2 lots) | 3,00 | 3,20 | 4,33 | 5,50 | 7,50 |
| 9, 10 (2 lots chacun) | 3,00 | 3,20 | 4,33 | 3,00 | 4,33 |
| **Neuf lots** | **14,00** | **14,90** | **20,03** | | |

[calcul ; les surcouts sont ceux des registres des paliers 3 et 4, pas une mesure de cette matiere ; B a 4,00 et 5,33, puis 2,50
et 3,17, d'apres A.207].

### 7.2 Ce que *"on releve pour absorber le depassement"* change pour moi

**Je ne decide aucun relevement** : c'est a l'utilisatrice et a `camille-po`. Ce que j'en lis :

- **5,0 par instance (`PAR-8`)** : l'instance de questionner le monde, a deux lots, reste sous 5,0 meme a 0,67 (4,33). **Aucun
  relevement n'est necessaire pour elle.** Il ne servirait qu'a lui donner un troisieme lot par palier, que je ne tiens pas (7.3).
  B depasse 5,0 a 0,67 aux paliers 6 et 7 (5,33) : c'est A.207, inchange, et son relevement serait le sien.
- **Deux lots illustres par palier en B** : sa cadence, inchangee.
- **`PAR-2`** n'est pas un nombre : rien a relever. *"On releve"* ne partage pas questionner le monde CE1 entre deux instances, et
  n'envoie aucun de ses lots a B.
- **Borne du palier** : ma fenetre est 8,0 (criteres, 11.3) ; le palier 7 la depasse a 0,67 (9,67). La borne des paliers 6 a 10
  n'est pas posee (A.219) ; si elle la releve, c'est le sien, comme aux paliers 4 et 5.

### 7.3 Le plafond reel de ma charge par palier

**Par mes regles ecrites** : 8,0 par palier a deux ou trois instances (criteres, 11.3 ; la borne mesure une fenetre d'attention, pas
une duree) ; par instance, 5,0 et trois lots (`PAR-8`).

**En lots, ce que je tiens** : dans un palier ou B porte deux lots illustres, **quatre lots au premier audit, deux illustres et deux
textuels**, soit 7,0 au bareme, les rejeux dans les deux marges. **Un cinquieme lot dans ce palier** (un troisieme de questionner le
monde, ou un lot de A ou de A') **depasse ce que je tiens** : s'il est ecrit par l'utilisatrice, je l'audite et j'ecris le
depassement comme le sien, comme aux paliers 4 et 5. **Un relevement change ce qui est engage, pas ce que je peux verifier dans une
fenetre.**

Les stories de reserve d'A' ne sont pas des lots (A.205), mais elles ouvrent une instance (1,0 de fixe) : a cote de deux lots de B
et de deux de questionner le monde, elles porteraient le palier a 8,75 a 9,50 au bareme. Elles vont dans un palier ou questionner le
monde n'a qu'un lot, ou aux paliers 9 et 10, ou B n'a aucun lot.

---

## 8. Ce dont je reste la seule garde, ajoute

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| `NRC-CE1-1` sur chaque paquet de questionner le monde CE1 | aucun garde-fou ne compare deux paquets |
| L'exactitude de chaque fait contre sa source (`QMT-CE1-2`, `AIM`, `ETE`, `FAL`, `RAL`, `BPL`, `DVP`, `DEL`, `PAY`, `GEN`) | aucun test ne lit une source |
| `QMT-CE1-1` : les mots exclus | encodable, non encode |
| `AIM-CE1-2` : aucune phrase ne dit que l'aimant attire les metaux | encodable, non encode |
| `BIA-4` sur `-04` et `-12`, `GEN-CE1-2`, `DEL-CE1-2` | jugement de sens |

---

## 9. Ce que je suppose

1. Le texte de V3 est celui que l'agent lanceur me transmet ; le depot ne le porte pas.
2. *"Au releve"* se lit *"on releve"* : un relevement d'un plafond de capacite, sans que ses mots disent lequel.
3. A.214 a A.219 et la section 13 de la proposition, lus sur `s57-camille-p5-dix-paliers` (`f2bd783e`), deviennent le texte de
   `round-two` sans changement.
4. La cadence de `D5` d'A.215, nominale ou en repli, et `MAT-CE1-11` au palier 8.
5. A et A' n'ont pas d'autre poste aux paliers 6 a 10 que ceux qui glissent.
6. Les surcouts de 0,10 et 0,67 se repetent ; ce ne sont pas des mesures de questionner le monde.
7. Le cousinage de 4.1 est mon jugement ; aucun outil ne le mesure.

**Ma dette, toujours ouverte** : l'annotation de `TRV-CE1-1` pour `FRA-CE1-27` et `-29` n'est pas ecrite (`grep -c "2026-10-2"`
rend 0). Ce commit ne la porte pas, faute d'etre dans la demande : deuxieme report a ma charge, apres celui que j'ai ecrit le
2026-10-24.

## 10. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | consigner V3 mot pour mot dans `DEC-0120` avant le premier lot (2) ; choisir l'instance (C1) ; une story par ligne avec mes conditions en criteres (C6) ; composer les paliers 6 a 10 sous C2 a C4 et 7.3 |
| `sofia-sm` | `planning.md` : une instance de questionner le monde a partir du palier 6 au plus tot ; le declencheur de la lecture avant `verify` (6.1) |
| l'utilisatrice, par `camille-po` | aucune question. Si elle veut `FRA-CE1-08` ou `-09` dans s57 : ses mots, en les nommant (5) ; un relevement : le sien (7.2) |

## 11. Ce que ce document ne fait pas

Il ne prononce aucun Done, ne fixe aucun prix au registre, n'engage aucun lot, n'ecrit aucune story, ne releve aucun plafond, ne
modifie ni `DEC`, ni matrice, ni registre d'identifiants, ni paquet, ni code. Il n'alloue aucun identifiant.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-25, 11:13.
