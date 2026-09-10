# Recalcul du registre de dette a l'ouverture de s34

- **Type** : document de travail de `camille-po`, pas une `DEC`. Il etablit un chiffre et sa
  methode ; la strategie de s34 qui s'appuie dessus se decide ailleurs, dans une `DEC` a venir.
- **Date** : 2026-09-10.
- **Auteur** : `camille-po`.
- **Commande** : la review de s33 (`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/review.md`,
  section "Le registre de dette a la cloture, contre les 9,0 de l'ouverture") a etabli que la
  descente projetee a 2,5 (`DEC-0085`, section 7) ne tient pas, sans recalculer le chiffre, et a
  route ce recalcul a `camille-po`.
- **Sources** : `DEC-0085` (section 3, composition ; section 4, registre a 9,0 ; section 7,
  projection) ; l'arbitrage prealable de s33, sections 4.2, 4.3, 5, 7.3 ; `verdicts-nadia-instance-
  A.md`, `-B.md`, `-C.md` ; `planning.md` de s33, sections 9 et 10 ; `review.md` de s33.
- **Ce que ce document ne fait pas** : il ne deplace aucun statut de
  `squad/programme/MATRICE-couverture.md`, ne modifie pas `docs/backlog/backlog.md`, ne touche pas
  au registre d'identifiants ni a l'arbitrage prealable de s34 que `nadia-prof` ecrit en ce moment.
  Il ne prononce aucun Done pedagogique et ne tranche aucune question technique.

---

## 1. Methode

Le registre de dette de `nadia-prof` compte en unites de `PAR-10` : **un paquet, pas un code**. Un
paquet porte un ou plusieurs codes de reserve ; le prix (0,5 le plus souvent, 1,0 ou 1,5 pour un lot
sous `PAR-14`) paie la reprise du paquet entier, quel que soit le nombre de codes qu'il porte.

**Un paquet est PAYE quand la totalite des codes que la table de `DEC-0085`, section 4.3, lui
attribue sont leves, et que l'increment qui les leve n'est pas refuse.** Cette seconde clause n'est
pas une invention de ce document : c'est ce que la commande de ce recalcul demande de verifier en
premier, et le cas exact existe dans ce sprint (`S33-EXC-R5` ci-dessous).

**Un paquet ou une partie seulement de ses codes est levee reste compte comme NON PAYE dans ce
document.** Aucun texte de `nadia-prof` ni de `sofia-sm` ne fixe de regle de conversion fractionnaire
(un paquet a moitie leve ne vaut pas 0,25) - c'est exactement le trou que la review nomme sans le
combler. **Je choisis le binaire par paquet plutot que d'inventer une fraction**, et je le dis pour
qu'on puisse le contester : deux paquets (`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`) ont
regle une part reelle de leur dette (`CAT-1`) sans que ce document leur en credite quoi que ce soit,
faute d'une unite plus fine que le paquet.

Ce document distingue cette methode du **budget reellement depense** (`review.md`, section "Le
budget reellement depense contre la borne de 12,0"), qui mesure l'effort d'audit consomme sur la
borne de 12,0. Les deux chiffres divergent deliberement : un paquet peut couter cher a auditer
(`EPS-CP-04` a coute 1,0 et non 0,5 prevu, `PAR-14` s'appliquant a une reprise, section 4.4 du
verdict C) sans que cela change ce que son registre de dette doit encore a l'equipe.

---

## 2. Ce que chaque poste de la dette de 9,0 recouvrait a l'ouverture de s33

Reprise exacte de `DEC-0085`, section 4.3 (elle-meme reprise de l'arbitrage prealable de s33,
section 4.3), sans recalcul a ce stade :

| Paquet ou portee | Codes ouverts a l'ouverture de s33 | Prix | Ligne de matrice en jeu |
|---|---|---|---|
| `cm1-histoire-geographie` | `HG-R8` a `HG-R12`, reliquat de `CONS-1` | 0,5 | `HG-CM1-02`, `HG-CM1-04` |
| `cp-emc-regle-souhait-loi` | motif du troisieme refus (`EMC-CP-07`) | 0,5 | `EMC-CP-07` |
| `cp-eps-regles-de-securite` | `SEC-R1`, `SEC-R2`, `SEC-R3` | 0,5 | `EPS-CP-04` |
| `cp-eps-regles-jeu-collectif` | `JEU-R1` a `JEU-R8` | 1,0 (`PAR-14`) | `EPS-CP-08` |
| `cp-emc-regles-de-vie` | `CAT-1`, `REG-R2`, `REG-R3` | 0,5 | - |
| `cp-emc-permis-interdit` | `CAT-1`, `PER-R2` | 0,5 | - |
| `cp-emc-emotions-autrui` | `EMO-R3` | 0,5 | - |
| `cp-emc-formules-de-politesse` | `POL-R3`, `POL-R4` | 0,5 | - |
| `cp-emc-exclusion` | `EXC-R1`, `EXC-R5`, `EXC-R6` | 0,5 | - |
| `cm1-anglais` | `ANG-R3` a `ANG-R6` | 0,5 | - |
| `cm1-sciences-signal-information` | `SIG-R1`, `SIG-R2` | 0,5 | - |
| `cm1-sciences-chaine-alimentaire` | `CHA-R1` | 0,5 | - |
| `cm1-division-posee` | `POS-R3` | 0,5 | - |
| `cm1-emc-fait-opinion` | `FOP-R4` | 0,5 | - |
| `cm1-emc-droit-devoir` | `DRO-R4` | 0,5 | - |
| `cm1-sciences-melanges` | `MEL-R4` | 0,0 (ne justifie pas un rejeu seule) | - |
| portee D | groupe 2b hors histoire-geographie : `CONS-2`, `SAI-R1`, `ERR-R1` | 1,0 | - |
| portee D | `CONS-3` | non chiffree | - |
| portee D | `CONS-4` | non chiffree, decision de grain due | - |
| portee D | `SCO-1`, verification `D7` | 0,0 (payee gratuitement) | - |
| | **Total** | **9,0**, plus `CONS-3` et `CONS-4` | +5 lignes potentielles |

Quatre paquets portent une ligne de matrice fermable (2,5 sur les 9,0) ; les treize autres unites
(6,5) ne dependent d'aucune fermeture de ligne - c'est la phrase que l'arbitrage prealable de s33
(section 7.3) et la review de s33 reprennent, et que ce document verifie maintenant poste par poste.

---

## 3. Ce que s33 a reellement paye, paquet par paquet

### 3.1 Payes - dette retiree du registre : 2,0

| Paquet | Ce qui etait du | Verifie dans le verdict | Etat |
|---|---|---|---|
| `cm1-histoire-geographie` | `HG-R8` a `HG-R12` | Les cinq codes leves (`verdicts-nadia-instance-A.md`, sections 2.1, 2.2, 3.1, 3.2, 10.2). L'increment (`S33-HG-02`, `S33-HG-04`) n'est pas refuse | **PAYE, 0,5** |
| `cp-eps-regles-de-securite` | `SEC-R1`, `SEC-R2`, `SEC-R3` | Les trois leves (`verdicts-nadia-instance-C.md`, section 5.1). L'increment n'est pas refuse sur ces trois codes precisement : le refus de la ligne (`SEC-R4`) porte sur un defaut trouve en reauditant, pas sur ce qui etait du | **PAYE, 0,5** |
| `cp-eps-regles-jeu-collectif` | `JEU-R1` a `JEU-R8` | Les huit leves un par un (`verdicts-nadia-instance-C.md`, section 6.2). Meme structure : le refus (`JEU-R9`) porte sur un item trouve en reauditant | **PAYE, 1,0** |

**Caveat sur le premier paquet, nomme plutot qu'efface** : le reliquat de `CONS-1` que ce meme prix
portait reste **non leve** sur cette entree (`verdicts-nadia-instance-A.md`, section 6 : *"`CONS-1`
reste non levee sur cette entree, et elle reste non bloquante"*). `CONS-1` n'a jamais recu de prix
propre dans aucun document de ce depot - elle vit, comme `CONS-3` et `CONS-4`, dans la categorie
non chiffree. Je ne cree pas de prix pour elle ici : je compte le 0,5 comme paye parce que les cinq
codes qui portaient un chiffre (`HG-R8` a `HG-R12`) le sont, et je nomme le reliquat de `CONS-1` a
cote plutot que de le faire disparaitre.

### 3.2 Non payes malgre un increment livre et non refuse - dette qui reste : 1,0

| Paquet | Ce qui etait du | Ce que le sprint a livre | Ce qui reste ouvert |
|---|---|---|---|
| `cp-emc-regles-de-vie` | `CAT-1`, `REG-R2`, `REG-R3` | `CAT-1` payee, `REG-R2` levee au passage (`verdicts-nadia-instance-B.md`, section 4.6) | `REG-R3` reste ouverte, a un chiffre corrige (section 2.1 du meme verdict) |
| `cp-emc-permis-interdit` | `CAT-1`, `PER-R2` | `CAT-1` payee (section 5.2) | `PER-R2` reste ouverte, non touchee par la story (section 5.4) |

**C'est le cas `S33-CAT1` que la commande de ce recalcul nomme comme l'inverse d'`S33-EXC-R5`** :
l'increment n'est pas refuse et une part reelle de la dette est reglee (`CAT-1`, sur les deux
paquets, au cinquieme sprint de report). Mais aucun des deux paquets ne solde entierement son
registre : chacun porte un second code que la story ne visait pas. Sous la methode de la section 1,
les deux 0,5 restent dus en entier. **C'est le point ou ma methode binaire coute le plus a
l'equipe** : `CAT-1` est un vrai acquis que ce chiffre ne recompense pas.

### 3.3 Touches, increment livre puis refuse - dette qui reste : 1,0

**`cp-emc-exclusion`** (`EXC-R1`, `EXC-R5`, `EXC-R6`) : `EXC-R5` est **levee** - la reserve,
executee dans les termes exacts ou `nadia-prof` l'a ecrite, ne fait plus d'un adulte reel une
reponse fausse (`verdicts-nadia-instance-B.md`, section 6.1). **Mais l'increment `S33-EXC-R5` est
REFUSE** : la formule qui leve `EXC-R5` casse trois autres proprietes du meme paquet (`EXC-R7` a
`EXC-R9`, nouvelles). `EXC-R6` n'a jamais ete engagee. `EXC-R1` a ete corrigee a la lettre par
`S33-EXC-R1`, mais la tache qu'elle nomme a disparu du paquet dans le meme commit ; `nadia-prof`
l'ecrit sans detour : *"le defaut a change de cote, il n'a pas ete paye"* (section 6, `EXC-R9`).
**C'est le cas exact que la commande de ce recalcul demande de verifier en premier : un increment
refuse ne paie pas la dette meme quand un de ses codes est techniquement leve.** Reste du : 0,5.

**`cp-emc-regle-souhait-loi`** (`EMC-CP-07`) : le point 2 du troisieme refus (`PLA-1`, item 11) est
paye, mais le point 1 - `EMC-11`, le motif du troisieme refus lui-meme, qui est ce que le registre
de dette facture - n'est pas engage, et l'item 4 marque toujours fausse une proposition vraie
(`EMC-5`). **L'increment est REFUSE une quatrieme fois** (`verdicts-nadia-instance-B.md`, section
7.3). Reste du : 0,5.

Les deux paquets restent **NON PAYES, 1,0** au total.

### 3.4 Jamais touches - dette qui reste : 5,0

`cp-emc-emotions-autrui` (0,5), `cp-emc-formules-de-politesse` (0,5) et `cm1-anglais` (0,5) n'ont
recu aucune story a s33 - verifie sur le diff par l'instance A elle-meme pour l'anglais (*"aucun
fichier d'anglais"*, section 8) et par l'instance B pour les deux paquets d'EMC
(*"je n'ai pas rejoue `emotions-autrui` ni `formules-de-politesse` ce sprint"*, section 1 du
verdict). Le sort de `cm1-anglais` avait ete nomme d'avance par `sofia-sm` au planning (section 10 :
*"trois postes finances sans story ecrite"*) ; celui des deux paquets d'EMC ne l'avait pas ete - il
n'apparait dans aucune liste d'obstacle avant la review.

Le terrain de sciences CM1 et d'EMC CM1 - `cm1-sciences-signal-information`,
`cm1-sciences-chaine-alimentaire`, `cm1-division-posee`, `cm1-emc-fait-opinion`,
`cm1-emc-droit-devoir` (0,5 chacun) - et la portee D du groupe 2b (1,0) n'ont ete confies a aucune
des trois instances ce sprint : c'est une consequence structurelle du choix de terrain par instance
(`PAR-2`, `PAR-7`, `PAR-12`), pas un oubli de sprint.

Total de la section : (0,5 + 0,5 + 0,5) + (0,5 x 5) + 1,0 = 1,5 + 2,5 + 1,0 = **5,0**.

### 3.5 Recapitulatif de la section 3

| | Montant |
|---|---|
| Paye (section 3.1) | **2,0** |
| Non paye malgre increment non refuse (section 3.2) | 1,0 |
| Non paye, increment refuse (section 3.3) | 1,0 |
| Jamais touche (section 3.4) | 5,0 |
| **Total non paye, reporte** | **7,0** |
| **Total** (paye + non paye) | **9,0** |

Le total retrouve exactement les 9,0 d'ouverture : aucun poste de la table de la section 2 n'est
compte deux fois ni omis.

---

## 4. Ce que s33 a ajoute

Toutes les reserves neuves ne coutent pas le meme prix, et la raison tient a l'etat du paquet qui
les porte.

| Reserves neuves | Paquet | Etat de la dette ancienne du paquet | Prix a s34 |
|---|---|---|---|
| `HG-R13` a `HG-R18` (six codes) | `cm1-histoire-geographie` | **payee** ce sprint (section 3.1) | **0,5** - chiffre par `nadia-prof` elle-meme, pas une estimation de ce document (`verdicts-nadia-instance-A.md`, section 10.2 : *"les six reserves neuves vivent dans un seul paquet et se soldent ensemble : 0,5 en tout"*) |
| `SEC-R4` (bloquante), `SEC-R5` | `cp-eps-regles-de-securite` | **payee** ce sprint (section 3.1) | **non chiffree** - aucun document ne fixe le tarif d'un troisieme passage sur ce paquet |
| `JEU-R9` (bloquante), `JEU-R10` a `JEU-R12` | `cp-eps-regles-jeu-collectif` | **payee** ce sprint (section 3.1) | **non chiffree** - meme motif |
| `REG-R4`, `REG-R5` | `cp-emc-regles-de-vie` | **non payee** (section 3.2) | **0,0 marginal** - le paquet est deja compte non paye pour son propre code restant (`REG-R3`) ; sous `PAR-10`, ces deux codes voyagent avec la meme reprise a venir |
| `PER-R3` | `cp-emc-permis-interdit` | **non payee** (section 3.2) | **0,0 marginal**, meme motif (`PER-R2` deja ouvert) |
| `EXC-R7` a `EXC-R9` | `cp-emc-exclusion` | **non payee** (section 3.3) | **0,0 marginal**, meme motif |
| `EPS-8` | aucun - condition de methode, opposable au prochain lot d'indices | sans objet | **0,0** - ce n'est pas une dette sur un paquet existant, c'est une regle pour l'avenir, de la meme famille que `FER-1` ou `BIN-1` |

**Pourquoi `HG-R13` a `HG-R18` coutent 0,5 quand les six reserves d'EMC CP n'ajoutent rien** : la
regle de `PAR-10` ne facture pas au code, mais elle facture **une fois par paquet dont la dette
vient d'etre remise a zero**. L'histoire-geographie et les deux paquets d'EPS CP sont dans ce cas :
leur dette ancienne est soldee, et les reserves neuves ouvrent une dette neuve, complete, sur le
meme paquet. Les paquets d'EMC CP restants sont deja comptes non payes pour un autre code : leurs
reserves neuves ne font que grossir un dossier deja ouvert, elles ne creent pas de second paquet a
facturer.

**Ce que je ne fais pas** : je n'invente pas de tarif pour `SEC-R4`/`SEC-R5` et `JEU-R9` a
`JEU-R12`. Le prix le plus proche connu de ce depot pour une reprise ordinaire est 0,5 (`PAR-10`
standard) ; celui d'un lot sous `PAR-14` est 1,0 ou 1,5 selon la source du motif. Rien n'indique
lequel s'applique a un troisieme passage sur ces deux paquets, et je ne le decide pas a la place de
`nadia-prof`.

---

## 5. Le chiffre resultant

### 5.1 Chiffrable avec certitude, meme methode que `nadia-prof`

| | Montant |
|---|---|
| Dette reportee, non payee (section 3.5) | 7,0 |
| ~~Ancienne dette payee~~ | 0,0 (retiree) |
| Dette neuve certaine (`HG-R13` a `HG-R18`) | 0,5 |
| **Registre chiffrable a l'ouverture de s34** | **7,5** |

Detail du 7,0 reporte (section 3.5, hors le 1,0 deja recompte separement pour la dette neuve de
l'histoire-geographie) : `cp-emc-regle-souhait-loi` 0,5, `cp-emc-regles-de-vie` 0,5,
`cp-emc-permis-interdit` 0,5, `cp-emc-emotions-autrui` 0,5, `cp-emc-formules-de-politesse` 0,5,
`cp-emc-exclusion` 0,5, `cm1-anglais` 0,5, `cm1-sciences-signal-information` 0,5,
`cm1-sciences-chaine-alimentaire` 0,5, `cm1-division-posee` 0,5, `cm1-emc-fait-opinion` 0,5,
`cm1-emc-droit-devoir` 0,5 (douze postes a 0,5 = 6,0), plus le groupe 2b hors histoire-geographie
(1,0) = **7,0**. `cm1-sciences-melanges` et `SCO-1` (`D7`) restent a 0,0, inchanges.

### 5.2 Ce qui manque pour un chiffre complet, nomme plutot qu'estime

- **Le prix du troisieme passage sur `cp-eps-regles-de-securite` et sur
  `cp-eps-regles-jeu-collectif`** (section 4) : non chiffre par `nadia-prof`, non invente ici. Si
  le tarif standard de `PAR-10` s'appliquait par simple analogie (0,5 chacun, ce que ce document ne
  decide pas), le registre monterait a **8,5**.
- **`CONS-3`** : non chiffree depuis plusieurs sprints, inchangee.
- **`CONS-4`** : non chiffree, decision de grain toujours due entre `camille-po` et `nadia-prof` -
  trois questions precises routees par l'arbitrage produit de s33 restent sans reponse a la cloture
  (`review.md`, section "Les trois questions de grain").
- **Le reliquat de `CONS-1`** sur `cm1-histoire-geographie` : non leve, jamais chiffre
  separement dans aucun document de ce depot, traite ici comme le reste de la famille `CONS-1` -
  `CONS-3` - `CONS-4`, c'est-a-dire nomme et non quantifie.

**Le registre a l'ouverture de s34 est donc au minimum 7,5, plausiblement 8,5 une fois les deux
paquets d'EPS CP tarifes, plus une famille de dette non chiffree (`CONS-1`, `CONS-3`, `CONS-4`) qui
ne grossit ni ne retrecit ce sprint faute de decision.**

### 5.3 L'ecart avec la projection

`DEC-0085` projetait **2,5** si s33 se comportait comme s32 (3 lignes fermees - exactement le cas
survenu). Le chiffre etabli ici est **au moins 7,5**, soit un ecart d'**au moins 5,0**, pouvant
atteindre 6,0 une fois les deux paquets d'EPS CP tarifes.

**La cause de l'ecart n'est pas une seule chose, ce sont deux mecanismes distincts** :

1. **Le vivier suppose engage ne l'a pas ete.** Sur les sept paquets qui composaient la partie
   "independante de la fermeture d'une ligne" de la dette (6,5 sur 9,0), trois n'ont jamais recu de
   story (`cm1-anglais`, `cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`) et deux autres,
   bien qu'engages et menes a un increment non refuse, n'ont regle qu'une partie de leur registre
   (`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, section 3.2). Sur ces cinq postes, la
   projection supposait un paiement integral de 2,5 ; le paiement reel, sous la methode de ce
   document, est de 0,0.
2. **Le mecanisme qui a fait grossir la dette de s32 a s33 s'est reproduit a l'interieur meme de
   s33.** `nadia-prof` avait nomme, a l'ouverture de s33, que sa regle ne dit rien du reaudit d'un
   paquet deja ferme, et que c'est precisement la population qui avait fait grossir le registre
   entre s32 et s33. La meme chose vient de se produire **dans le sprint cense la corriger** : les
   trois paquets dont la dette ancienne a ete integralement payee (`cm1-histoire-geographie`,
   `cp-eps-regles-de-securite`, `cp-eps-regles-jeu-collectif`) ont chacun genere une dette neuve au
   moment meme ou l'ancienne se soldait - six reserves sur le premier, deux a quatre sur chacun des
   deux autres. Sur les trois paquets ou la dette ancienne a ete integralement soldee, aucun n'a
   fini le sprint a 0,0.

---

## 6. Ce que la methode de projection de `DEC-0085` a rate

**Premier defaut, deja identifie par `nadia-prof` elle-meme a l'ouverture de s33, et qui s'est
reproduit malgre le constat** : sa regle du Plan 2 solde les reserves qu'un lot neuf ouvre, mais ne
dit rien du reaudit d'un paquet ancien. Elle l'avait ecrit pour expliquer la croissance de s32 a
s33 ; rien dans `DEC-0085` n'en a tire de consequence pour la projection de s33 elle-meme, alors
que la mecanique s'applique de la meme facon a l'interieur d'un seul sprint. **Un projet qui
compte un paquet reaudite comme "solde" des que ses codes connus sont leves surestime
systematiquement la descente, parce que ce depot n'a pas encore reaudite un paquet sans y trouver
au moins une reserve neuve** - le fait se verifie a nouveau sur les trois paquets integralement
payes de ce sprint.

**Second defaut, propre a s33** : la projection (`DEC-0085`, section 7.3) a traite l'allocation
budgetaire de la composition (section 3) comme une garantie d'engagement. Le meme jour, une
ceremonie plus tardive - le planning - a deja mesure que trois postes finances n'avaient recu
aucune story, dont un poste directement compte dans les "6,5 independants de la fermeture d'une
ligne" (`cm1-anglais`). Ce signal a ete route comme obstacle de planning, jamais reconnecte a la
projection de dette qu'il menacait. Deux autres postes du meme groupe (`cp-emc-emotions-autrui`,
`cp-emc-formules-de-politesse`) n'ont meme pas ete detectes comme risque au planning - ils
n'apparaissent dans aucune liste d'obstacle avant la review. **Aucune etape du sprint ne revient
verifier, une fois la liste de stories arretee, si les postes que la strategie compte comme
acquis ont effectivement une story qui les porte.**

**Ce que je recommande de ne pas reconduire tel quel** : une projection de descente de dette qui se
fixe a l'ouverture du sprint, sur la seule foi d'une composition budgetaire, sans point de controle
au moment ou la liste de stories est arretee (le planning) ni de marge pour la dette qu'un reaudit
reussi produit lui-meme. Je n'impose pas de methode de remplacement - une regle de gouvernance sur
le traitement du reaudit d'un paquet ferme reste, comme `nadia-prof` l'a ecrit, une question qui
depasse une decision produit de sprint. Je nomme le second defaut, qui est nouveau par rapport a ce
qu'elle avait deja nomme, et je le route par ce document plutot que de le laisser dans une seule
phrase de review.

---

## 7. Ce que ce document ne fait pas

- Il ne fixe pas de strategie pour s34 : c'est l'objet d'une `DEC` a venir, qui pourra s'appuyer sur
  le chiffre et la methode ci-dessus.
- Il ne tarife pas les deux paquets d'EPS CP rouverts (`cp-eps-regles-de-securite`,
  `cp-eps-regles-jeu-collectif`) : ce tarif appartient a `nadia-prof`.
- Il ne tranche pas la decision de grain de `CONS-4`, ni ne chiffre `CONS-1` ou `CONS-3`.
- Il ne propose pas de regle de gouvernance sur le reaudit d'un paquet ferme : il nomme le second
  mecanisme qui aggrave ce trou deja identifie, sans le combler.
- Il ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et ne modifie pas
  `docs/backlog/backlog.md`.
