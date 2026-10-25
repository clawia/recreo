# s57 - palier 5 : neuf lots de questionner le monde CE1 dans un palier, ce que cela change pour mon audit

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-25. Heure non lue : le shell etait indisponible a l'ecriture.
- **Arbre** : `round-two` a `caff515a`, branche `s57-p5-nadia-9lots`, arbre propre avant ecriture.
- **Type** : arbitrage sur une reponse de l'utilisatrice. **Aucun Done n'est prononce**, aucune fiche n'est modifiee, aucune
  condition de Done de ligne n'est ecrite. Ce document ne touche ni code, ni paquet, ni catalogue, ni `DEC`, ni matrice, ni
  registre. **Aucun identifiant de condition neuf** : `L1` a `L4`, `M1` a `M6`, `K1` a `K8`, `P0` a `P3` sont des reperes de
  lecture de ce fichier, pas des conditions allouables. `C1` a `C7`, `R1` et 7.3 renvoient a
  `arbitrage-nadia-palier-5-qlm-v3.md`.
- **Je n'ai rien joue** : aucun paquet de questionner le monde CE1 n'existe. Ce que j'ecris est la condition de l'audit a venir.
- **Lu** : `arbitrage-nadia-palier-5-qlm-v3.md` (0 a 11, surtout 3, 6, 7.1, 7.3) ; `DEC-0120` A.200, A.204, A.220 a A.236 ;
  `cloture-palier-4.md` 1.

## Resume

1. **Ses mots** : *"6 lots au total dans un palier, mais j'aimerais faire passer tout questionner le monde dans un palier"*, puis
   *"on garde 9 lots + les 3 refus de nadia"*. Relayes mot pour mot par l'agent lanceur, non lus de sa main. Je lis : les neuf
   lignes textuelles (`QLM-CE1-01` a `-06`, `-08`, `-10`, `-12`) dans un seul palier, neuf lots ; `-09`, `-11` et la moitie
   dessinee de `-07` restent refusees. Elle ne nomme aucune de mes regles, ni le palier, ni l'instance, ni la moitie textuelle de
   `-07`.
2. **Ce qu'elle peut lever en les nommant** (section 3) : C3, C4, 7.3 et `R1`, la borne de 8,0 de ma fenetre. Ce sont des
   regles de composition et de charge ; leur depassement serait le sien, et j'audite.
3. **Ce que je maintiens meme si elle les nomme** (section 4) : une ligne par lot ; la lecture avant `verify` de chaque paquet,
   fait par fait contre sa source ; `-01` lu, corrige et audite avant les huit autres ; les conditions et plafonds de chaque
   ligne et mon verdict ; les prealables ; aucun verdict adouci pour tenir le palier.
4. **Ce qui ne tient pas** (section 2) : je ne promets pas neuf Done dans le palier. Aucune mesure ne dit que je tiens neuf lots
   dans une fenetre ; un lot non audite ou refuse a la cloture glisse.
5. **Conditions** (section 5) : un palier ou aucune autre instance ne me soumet de lot, qui n'est pas le dernier de s57 ; au
   nominal d'A.215, le palier 9.

---

## 1. Une correction de mon propre constat, avant d'arbitrer

J'ai ecrit dans `arbitrage-nadia-palier-5-qlm-v3.md` 4.2, `R1` : *"Fait mesure : au palier 4, six lots, chacun refuse ou valide
sous reserve au premier passage (A.200)."* **C'est inexact sur deux points.** (1) A.200 ne porte pas ce fait : il recompte le
surcout du palier 4 (4,00 pour 6 lots, 0,67 par lot). (2) `cloture-palier-4.md` 1 donne les verdicts du palier 4 : `LP2` VALIDE,
`LP1`, `LP3`, `LP4`, `LP5`, `LP6` VALIDE SOUS RESERVE ; `LP3` a ete refuse puis repris (meme fichier, section 2), `LP6` a eu deux
rejeux (A.199). Le fait juste : **six lots, un VALIDE, cinq VALIDE SOUS RESERVE, deux qui ont demande au moins un rejeu.** Je ne
sais pas, par ces fichiers, si `LP2` a ete valide au premier passage. Le registre du palier 4 est a 13,00 sur une borne de 20,0
(A.199) : c'est la seule mesure d'un palier au-dessus de 8,0. Je n'en tire toujours aucune causalite.

---

## 2. Ma charge reelle a neuf lots

### 2.1 Au bareme

Bareme inchange (7.1) : 1,0 par lot, lecture avant `verify` et correction qui la suit comprises ; fixe 1,0 par instance et par
palier (arbitrage 0,5, marge 0,5).

| Poste | Bareme | a 0,10 par lot (palier 3) | a 0,42 par lot (A.200, borne basse) | a 0,67 par lot (A.200, borne haute) |
|---|---|---|---|---|
| Une instance, neuf lots | **10,00** | 10,90 | 13,78 | 16,03 |
| Si la moitie textuelle de `-07` s'y ajoute (dix lots) | 11,00 | 12,00 | 15,20 | 17,70 |

[calcul ; les surcouts sont ceux des paliers 3 et 4, aucun n'est une mesure de questionner le monde.]

**Face a mes regles ecrites** : la borne de ma fenetre, 8,0 (criteres 11.3), est depassee **au bareme** (10,00), avant tout
surcout. 7.3 (quatre lots au premier audit dans un palier ou B porte deux lots illustres) est depasse de cinq lots si l'instance
est seule dans le palier, de sept si B y porte ses deux lots. C4 (deux lots par palier apres le premier) est depasse de sept.

### 2.2 Les lectures avant `verify`

Neuf lectures, une par paquet (6.1). Paquets voisins a relire pour `NRC-CE1-1` et `CYC-1`, d'apres ma table de 6.2 : **13 paquets
distincts**, 12 servis et le paquet de `-01` (relu pour `-03`) ; `cp-qlm-hygiene` sert `-03` et `-04`, `cp-qlm-matiere` sert
`-04` et `-06`. A chaque lecture, chaque fait discutable est verifie contre la source ecrite en commentaire (`QMT-CE1-2`), et la
liste fermee de mots exclus (`QMT-CE1-1`) est lue item par item. **Ce sont les deux conditions qu'aucune cousine n'a jamais
mordues sur un fait de sciences de CE1** (4.1). Le nombre d'items par paquet reste ouvert (A.227, point 4) : quel qu'il soit, il
est multiplie par neuf dans le meme palier.

### 2.3 Ce que je peux tenir

- **Neuf lectures avant `verify`, echelonnees** : `-01` seul d'abord, les huit autres ensuite (`P0`, `P1`, section 5.3). Le prix
  reste 1,0 par lot ; l'echelonnement est un ordre, pas un supplement.
- **Neuf audits joues, a la suite, dans l'ordre de C7**, chacun avec son verdict, son plafond et sa comparaison `NRC-CE1-1`.
- **L'exactitude de chaque fait**, puisque j'en suis la seule garde (section 6) : je la tiens a la lecture, pas seulement a
  l'audit.

### 2.4 Ce qui ne tient pas

- **Neuf Done dans le palier.** Aucune mesure ne dit que je tiens neuf lots dans une fenetre ; la seule mesure au-dessus de 8,0
  (palier 4, six lots, section 1) a donne cinq reserves et deux rejeux. Un lot que je n'ai pas audite a la cloture du palier
  **n'est pas valide** : il glisse.
- **La marge de 0,5.** Elle est taillee pour une instance de trois lots au plus (`PAR-8`) et paie un rejeu. A neuf lots, je ne la
  multiplie pas et je ne la fixe pas : son dimensionnement est a `camille-po` et a l'utilisatrice. Ce que je tiens : un lot
  refuse dont le rejeu depasse la marge est rejoue **au palier suivant**, pas comprime dans le meme.
- **Un relevement ne change pas ce que je verifie dans une fenetre** (7.3) : il change ce qui est engage.

---

## 3. Ce que ses mots levent, si elle les nomme

Ses mots du 2026-10-25 ne nomment aucune de ces regles. **Je ne les lis pas comme leves** tant qu'elle ne les a pas nommes, comme
A.231 et A.236 ; c'est aussi ma lecture. Si elle les nomme, ce sont des regles de composition et de charge : **le depassement est
le sien, je l'audite et je l'ecris comme le sien**, comme je l'ai ecrit d'avance en 7.3 et comme aux paliers 4 et 5.

| # | Regle | Si elle la nomme | Ce que je garde de son motif |
|---|---|---|---|
| L1 | **C3**, premier lot seul dans son palier, et jamais avec `MAT-CE1-11` | levee | le motif de C3 (voir les conditions neuves sur un seul paquet avant d'en prendre d'autres) passe dans l'ordre interne du palier : `M3`. Je demande que l'horloge ne soit pas dans ce palier (`K2`) ; si elle l'y met en le nommant, j'audite |
| L2 | **C4**, deux lots au plus par palier ensuite | levee | rien d'autre que l'ordre de C7 (`M3`) |
| L3 | **7.3 et `R1`**, quatre lots au premier audit dans un palier ou B porte deux lots illustres | levee | je demande un palier sans autre lot (`K2`) ; si elle y met B en le nommant, j'audite onze lots et le depassement est le sien |
| L4 | **La borne de 8,0 de ma fenetre** (criteres 11.3) | levee pour ce palier | la valeur de la borne du palier est la sienne (A.219) ; je ne la fixe pas |

**Ce qui n'est pas a moi et que je ne leve ni ne maintiens** : `PAR-8` (trois lots et 5,0 par instance : neuf lots sur une instance
le depassent de six lots et, au bareme, de 5,00) ; `PAR-2` (questionner le monde CE1 compte avec questionner le monde CP : une
seule instance, aucun partage pour passer sous `PAR-8`) ; *"6 lots au total"* d'A.235, que *"on garde 9 lots"* depasse dans ce
palier ; C1, C2 en tant que numero de palier ; le choix du palier et de l'instance. **A nommer par elle, a consigner par
`camille-po`.**

---

## 4. Ce que je maintiens meme si elle les nomme

Ces regles ne disent pas *quand* un lot s'audite ni *combien* : elles disent *ce qu'un verdict vaut*. Les lever ne changerait pas
la charge, cela changerait ce que mon Done certifie.

| # | Ce que je maintiens | Motif pedagogique |
|---|---|---|
| M1 | **Une ligne par lot** (C5). Neuf lots, neuf verdicts, jamais un lot groupe | une competence par verdict : un REFUS groupe bloquerait des lignes justes, un VALIDE groupe couvrirait une ligne fausse. Ses mots disent *"9 lots"* : ils sont conformes a `M1` |
| M2 | **La lecture avant `verify` de chaque paquet, chaque fait contre sa source** (6.1, 6.2 ; A.184, A.204) | une erreur factuelle dans un contenu scolaire est un refus a elle seule, et sur cette matiere elle passe facilement (le metal que l'aimant n'attire pas, la buee, la pomme de terre, le regime d'un animal). Aucun test ne lit une source |
| M3 | **`-01` lu, corrige, puis audite en premier ; les huit autres ensuite, dans l'ordre de C7** | `-01` a une liste fermee, une comparaison `NRC-CE1-1` reelle et un risque d'exactitude bas. Si `QMT-CE1-1` ou `QMT-CE1-2` mordent sur cette matiere, je dois le savoir **avant** que le meme defaut soit ecrit dans huit paquets : sinon une correction devient huit refus. C'est le motif de C3, garde sans la composition |
| M4 | **Les conditions de chaque ligne, ses plafonds, mon verdict** (V3, 2.4) | inchanges par tout depassement de composition. J'audite chaque lot comme un lot de s58 |
| M5 | **Aucun verdict adouci pour tenir le palier** (3, `R5`) | un lot refuse ou non audite glisse ; il ne recoit pas un VALIDE parce que les huit autres l'ont |
| M6 | **Les prealables** (C6, section 5.4) | sans story, sans premier jet, il n'y a rien a lire ni a jouer : aucun mot ne rend auditable ce qui n'existe pas |

Les trois refus (`-09`, `-11`, moitie dessinee de `-07`) tiennent ; elle les garde elle-meme.

---

## 5. Les conditions de mon audit

### 5.1 Palier

| # | Condition | Motif |
|---|---|---|
| K1 | **Une seule instance porte les neuf lots** (C1, `PAR-2`) | `PAR-2` ; le depassement de `PAR-8` est a elle (3) |
| K2 | **Aucune autre instance ne me soumet de lot dans ce palier** : ni B, ni A, ni A', ni `MAT-CE1-11` | neuf lots dont l'exactitude n'a que moi pour garde remplissent ma fenetre ; un lot illustre ou un terrain sans cousine a cote serait audite apres neuf autres. Levable par elle en le nommant (`L1`, `L3`) |
| K3 | **Ce palier n'est pas le dernier de s57** | le palier suivant porte les rejeux (2.4) et la moitie textuelle de `-07` (5.2). Au dernier palier, tout refus va a s58 |
| K4 | **Au nominal d'A.215, le palier 9** : B n'a aucun lot aux paliers 9 et 10 ; le palier 10 recoit les rejeux | `K2`, `K3`. Plafond de `couvert-valide` de s57 : 43 + 9 = **52 sur 64** [calcul, suppose : chaque lot obtient son Done], le meme qu'en 3 de V3 |
| K5 | **Au repli d'A.215** (`MAT-CE1-11` au palier 9) : les neuf lots vont au palier 10, et un refus y va a s58, ecrit d'avance ; ou elle met l'horloge avec eux en le nommant | `K2`, `K3` ne tiennent pas ensemble au repli ; je ne choisis pas pour elle |

Un palier plus tot (6, 7, 8) met les neuf lots a cote des lots illustres de B : `K2` tombe, et ce serait a elle de le nommer.

### 5.2 Materiau de `-07`

Ses mots disent neuf lots et ne nomment pas le dixieme. **La moitie textuelle de `-07` n'entre pas dans le palier des neuf.** Elle
reste recevable, seule dans son lot : `CIR-CE1-1`, aucun dessin, aucun circuit, la mention de couverture partielle, plafond
`en cours`, 0 en `couvert-valide` (3 de V3). Elle va au palier qui suit les neuf, s'il a une place apres les rejeux, sinon a s58.
Dans le palier des neuf, je lis la frontiere de `-08` avec `-07` : aucune pile, aucun circuit (6.2). **Si elle veut `-07` parmi
les neuf, elle la nomme** ; le palier porte alors dix lots, 11,00 au bareme (2.1).

### 5.3 Passes

| Passe | Ce que je fais | Sur quoi |
|---|---|---|
| P0 | lecture avant `verify` de `-01` seul ; le developpeur corrige ; mon ecrit `lecture-nadia-premier-jet-<paquet>.md` part avant que les huit autres premiers jets soient commites | premier jet de `-01`, avec ses deux voisins |
| P1 | lecture avant `verify` des huit autres, par paires dans l'ordre de C7 : `-02`, `-03` ; `-04`, `-05` ; `-06`, `-08` ; `-10`, `-12` | premiers jets, ecrits apres `P0`, avec leurs voisins |
| P2 | audits joues, jar servi, commit joue nomme, dans le meme ordre : `-01` d'abord | chaque lot apres son `verify` |
| P3 | rejeux : un dans la marge, les autres au palier suivant (2.4) | lots refuses a `P2` |

Une seconde lecture avant `verify` va sur la marge (7.1). **Trois passes au moins avant le premier verdict de la serie** (`P0`,
`P1`, `P2`), sans compter les rejeux.

### 5.4 Ce que je veux avant l'ouverture du palier

1. **Ses mots de ce jour consignes** dans `DEC-0120` mot pour mot, avec la provenance, et, si elle les nomme, les regles qu'elle
   depasse (`L1` a `L4`, `PAR-8`) ; sinon le palier se compose sous mes regles ecrites et le depassement est consigne comme tel
   (A.225).
2. **Mon constat sur A.229** (le texte de V3 *"tel qu'il lui a ete presente"*) : il n'est pas ecrit ici, faute d'etre dans la
   demande. Il le sera avant l'ouverture, dans mon arbitrage prealable de l'instance.
3. **Neuf stories**, une par ligne, mes conditions reprises mot pour mot (A.227), chaque critere negatif avec sa sonde.
4. **Ma reponse sur le nombre d'items par paquet et les mots de quantite d'`AIM-CE1-2`** (A.227, points 2 et 4), avant que les
   stories soient ecrites. Elle n'est pas donnee ici.
5. **Un developpeur designe**, distinct de `lea-dev` (C1, `RG2`), et le declencheur de la lecture avant `verify` dans
   `planning.md`.
6. **Le premier jet de `-01` commite seul**, avant les huit autres (`P0`).
7. **Mon arbitrage prealable de l'instance pour ce palier** (0,5, dans le fixe).

---

## 6. Ce dont je reste la seule garde

Inchange par rapport a 8 de V3, et multiplie par neuf dans un palier : `NRC-CE1-1` sur chaque paquet (aucun garde-fou ne compare
deux paquets) ; l'exactitude de chaque fait contre sa source ; `QMT-CE1-1` et `AIM-CE1-2` (encodables, non encodes) ; `BIA-4` sur
`-04` et `-12`, `GEN-CE1-2`, `DEL-CE1-2` (jugement de sens). **Neuf lots dans un palier ne rendent aucune de ces proprietes
couverte par un test.**

## 7. Routage

| A qui | Quoi |
|---|---|
| l'utilisatrice, par `sofia-sm` | nommer, si elle le veut, les regles de `L1` a `L4` et `PAR-8` ; le palier (`K4`, `K5`) ; la moitie textuelle de `-07` (5.2) |
| `camille-po` | consigner ses mots de ce jour ; les stories apres ma reponse de 5.4 point 4 ; composer le palier sous `K1` a `K5` |
| `sofia-sm` | designer le developpeur ; `planning.md` : le declencheur de `P0` |

## 8. Ce que ce document ne fait pas

Il ne prononce aucun Done, ne fixe aucun prix au registre, n'engage aucun lot, n'ecrit aucune story, ne releve aucun plafond, ne
choisit ni le palier ni l'instance, ne modifie ni `DEC`, ni matrice, ni registre d'identifiants, ni paquet, ni code. Il n'alloue
aucun identifiant.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-25.
