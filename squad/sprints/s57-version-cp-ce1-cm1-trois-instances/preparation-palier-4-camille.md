# s57 - preparation du palier 4 : prix du palier 3, lots candidats, composition proposee (`camille-po`)

- **Auteure** : `camille-po`, 2026-10-21 17:07 (UTC+02:00), lu par `date`. Arbre : `round-two` a `b52c4ac5`, `git status --short` vide avant ecriture (puis les fichiers de ce geste, plus deux fichiers non suivis d'autres agents, `confirmation-archunit-palier-3.md` et `rapport-qualite-palier-3.md`, que je n'ai pas lus).
- **Ce que ce fichier est** : (A) le registre des prix du palier 3 et ses ecarts (section 1) ; (B) un renvoi vers l'amendement de `DEC-0120`, sections A.117 a A.125, qui consigne ce que le palier 3 a decide et change (section 2) ; (C) la proposition du palier 4 (sections 3 a 9). **Ce qu'il n'est pas** : ni la composition de `sofia-sm`, ni l'arbitrage prealable de `nadia-prof`, ni un amendement de `planning.md`. **Aucun poste n'est engage, aucun prix n'est fixe par moi** : les prix et les instances sont ceux de `nadia-prof` (`DEC-0120` A.33, A.36), l'engagement est a `sofia-sm`. Le palier 3 n'est pas clos : `cloture-palier-3.md` n'existe pas, `sofia-sm` l'ecrit apres.
- **Sources** : `composition-palier-3.md` (sections 2, 4, 12) ; `planning.md` (amendements du 2026-10-19 23:09 et 23:44) ; `verdict-nadia-palier-3-ce1.md` ; `arbitrage-nadia-q05-q10.md` ; `arbitrage-nadia-preparation-palier-3-ce1.md` ; `preparation-palier-3-ce1-camille.md` ; `DEC-0123`, `DEC-0124` ; `rapport-sofia-dev-palier-3.md` ; `cloture-palier-2.md` (sections 6, 13, 14) ; `registre-prix-palier-2.txt` (modele).
- **Mesure de la matrice**, commande de la "Methode du compte" de `squad/programme/MATRICE-couverture.md`, lancee ce jour, sortie lue : `cv 152`, `ec 5`, `nc 43`, `ncb 77`, `total 277`. **152 / 200 = 76,00 %.** Compteur de version (`DEC-0120` A.32) : 152 + 5 = 157 / 200 = 78,50 % par la formule ; son critere 2 (CI verte sur l'arbre) n'est pas lu par moi.

## Resume

| Code | Point | Valeur |
|---|---|---|
| PR1 | Prix du palier 3 | **8,00** compose, **6,50** facture, **1,50** de marge (registre commite) ; la verification de `composition-palier-3.md` section 12 tient ; **aucun prix consomme n'est ecrit par `nadia-prof`**, le rejeu de `-07` n'a pas de prix |
| PR2 | Reports du palier 3 | premiers reports de `MAT-CE1-01` et d'`EMC-CE1-01` **soldes** (engages a 23:44, verdicts le 2026-10-21), aucun second ; `RL5` a `RL7` livres, pas de second glissement ; trois stories hors `PAR` a leur **deuxieme ecartement** |
| PR2b | Consigne de l'utilisatrice du 2026-10-21 | *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"* (relayee, non lue de sa main) : **la borne de 8,0 est a `nadia-prof`** ; je mesure, je ne la change pas (section 0) |
| PR3 | Composition proposee du palier 4 | **9,0 pour six lots prets** : A 2,0 (1 lot), B 3,0 (2), A' 4,0 (3) ; **11,5 pour huit lots** sous deux conditions ; **repli a 8,0 pour cinq lots** si la borne est maintenue. **Le neuvieme lot n'existe pas.** Gain du 9,0 : `couvert-valide` 152 a 156 (78,00 %), compteur 157 a 163 |
| PR4 | Prets | `FRA-CE1-21`, `MAT-CE1-10`, `MAT-CE1-11`, `EMC-CE1-02`, `EMC-CE1-03`, `EMC-CE1-04` : story ecrite (fin de `backlog.md`), paquet existant ou ecrit dans le lot, conditions de `nadia-prof` ecrites |
| PR5 | Exigent une reponse de l'utilisatrice | confirmer la lecture de la consigne (section 0) ; `QP6` (developpeurs de B et d'A', **bloquante a l'ouverture**) ; `QP7` (s57 se clot-il apres le palier 4 ou 5 ? tout le reste en depend) ; `QP5` (le maximum de la version passe de 200 a 202) ; `QP3` (*"infos"*, pour `MAT-CE1-14`) |
| PR6 | A ne pas engager | `-08`, `-09`, `-27`, `-29`, `ART-CE1-01`, `-02`, tout questionner le monde CE1, `MAT-CE1-14`, `MAT-CE1-15`, `FRA-CE1-03` : motifs section 4 |

## 0. La consigne du 2026-10-21 : lecture, borne, ce qu'elle change

Mots relayes par l'agent lanceur, **non lus de sa main** : *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*. **Lecture a confirmer par l'utilisatrice** : le seuil est la borne de 8,0 par palier (`PAR-9`, `O1` de `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 4.3, `DEC-0120` A.28) ; les instances sont A, A' et B, qui lisent en parallele ; *"jusqu'a 3"* est l'etat des paliers 1 a 3.

- `PAR-8` : *"8,0 a deux ou trois instances, 5,0 a une ; au plus 5,0 et trois lots par instance"*. Le plafond par instance ne change pas : trois instances donnent au plus 15,0 et neuf lots.
- `PAR-9` : *"la somme des budgets egale la borne"*. **La borne est a `nadia-prof`** : *"La borne de s57 est 8,0, pour trois instances. O2 n'est pas derivable [...] Je ne pose aucun chiffre au-dela de 8,0"*. Au-dela, trois conditions cumulatives (4.3) : (i) non montree (`LP1` seule reste dehors de deux instances pleines : 1,0, ou 2,0 avec les lots conditionnels ; il faut 4,0), (ii) non verifiee par moi, (iii) tenue. Mesure complete : `DEC-0120` A.127.
- **Je ne relevais pas la borne et je ne l'ai pas relevee.** Je presente trois compositions (section 5 : repli a 8,0, 9,0 pret, 11,5 conditionnel) et je demande a `nadia-prof` d'ecrire `O2` et la capacite de chaque instance, ou de maintenir 8,0. Amendement de `DEC-0120` : A.126 a A.131 (qui amende quoi : A.129).

## 1. Prix du palier 3 (A)

### 1.1 Registre

Fichier commite : `registre-prix-palier-3.txt`, onze lignes (`instance|poste|prix|etat`), sur le modele de `registre-prix-palier-2.txt` (seize lignes). `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre-prix-palier-3.txt | sort`, sortie lue : `A 4.00`, `Ap 2.00`, `B 2.00`, **`total 8.00`**. Par etat : `facture 6.50`, `marge 1.50`. Lots : 5.

| Instance | Composition | Facture | Detail | Marge |
|---|---|---|---|---|
| A (groupe X) | 4,00 | **3,50** | arbitrage 0,5 ; `-06`, `-07`, `-11` 1,0 chacun | 0,50 |
| A' | 2,00 | **1,50** | arbitrage 0,5 ; `EMC-CE1-01` 1,0 | 0,50 |
| B | 2,00 | **1,50** | arbitrage 0,5 ; `MAT-CE1-01` 1,0 | 0,50 |
| **total** | **8,00** | **6,50** | | **1,50** |

### 1.2 Verification des chiffres de `composition-palier-3.md` section 12

*"6,50 engage et 1,50 de marge"* (12.2) : 3 x 0,5 d'arbitrage + 5 x 1,0 de lot = 6,5 ; 3 x 0,5 de marge = 1,5 ; 6,5 + 1,5 = 8,0. **Juste.** Le planning du 23:09 ecrivait *"engage 4,50, non engage 2,0"* (avant `AD1`) et celui de 23:44 *"engage 6,50"* : pas un ecart, deux etats. `preparation-palier-3-ce1-camille.md` 8.2 (A 4,0, B 2,0, A' 2,0) et `reponses-nadia-questions-stories-palier-3.md` 7.2 donnent les memes sommes. Bornes : 8,0 (`PAR-9`), 5,0 par instance (A est a 4,0), trois lots au plus (A en porte trois) : tenues.

### 1.3 Consomme, et ce qui n'est pas ecrit

`nadia-prof` n'a ecrit **aucun prix consomme** pour le palier 3 : ni champ *"Tarif consomme"*, ni ligne de routage de prix, dans ses cinq fichiers de palier ni dans les quatre fiches ouvertes. Je ne reconstruis donc pas de consommation : les cinq lots sont inscrits `facture` a leur prix de composition (arbitrage 0,5 par instance livre, lot a 1,0 avec verdict ecrit). **Une seule ligne peut changer le fichier : le rejeu de `-07`** (refus du 2026-10-21, correction `ac986aca`, rejeu, releve a l'ecran, verdict `c17c3f9e`). `nadia-prof` route *"les rejeux sur la marge de chaque instance"* (`lecture-nadia-palier-3.md`, section 8) et n'ecrit aucun prix ; au palier 2 un rejeu apres refus valait 0,5 (`DEC-0120` A.105). S'il vaut 0,5 ici, la marge de A est consommee en entier : A reste a 4,00, la repartition passe a 7,00 facture et 1,00 de marge. **Le total ne change pas, le fichier se corrige d'une ligne, et la ligne est a elle.**

Les marges de B et d'A' (0,5 chacune) : aucun rejeu ne les a touchees. **Non affecte du palier : 0.** Il n'y a rien qui tombe a la cloture.

### 1.4 Glissements et premiers reports

| Poste | Etat | Sort |
|---|---|---|
| `MAT-CE1-01` (B) | premier report, ecrit a 23:44 (`planning.md`, section 3) | engage, audite dans le palier : **VALIDE**, plafond `en cours`. **Report solde, aucun second** (`A.66`, `A.83`) |
| `EMC-CE1-01` (A') | premier report, meme date | engage, audite : **VALIDE SOUS RESERVE**. **Solde, aucun second** |
| `RL5` a `RL7` | premier glissement (palier 2) | livres le 2026-10-20 (`4bf3c359`, fusion `20470501`). **Pas de second glissement** |
| `-06`, `-07`, `-11` | deuxieme ecartement | entres, `-07` refuse puis valide. **Pas de troisieme** |

Detail et references : `DEC-0120` A.118.

### 1.5 Annotations de `planning.md` de ma competence

Une annotation datee en fin de `planning.md` (*"Annotation de `camille-po`"*, 2026-10-21) porte : le registre des prix du palier 3 et son recompte, le sort des reports ci-dessus, la mise a jour des points `AD3`, `AD5`, `AD6`, `AD7` (decides chez moi : stories, ordre, composition) et l'ecart au rapport de developpeuse. **Elle ne nomme aucune story du palier 4** : `K1 (a)` de ces stories demande que `planning.md` porte un amendement du palier 4 qui les nomme, et `grep -c` sur le fichier doit rendre 0 tant que `sofia-sm` ne l'a pas ecrit. Elle ne modifie aucune ligne de `sofia-sm`.

## 2. Amendement de `DEC-0120` (B)

Sections **A.117 a A.125** (dix-neuvieme amendement, 2026-10-21) : l'ordre `Z1`/`Z2`, `RL5` a `RL7`, lots (propose, confirme, non decide, constate tenu par `git merge-base`) ; reports ; prix ; les reponses `Q-05` a `Q-10` et ce qu'en a fait le palier ; les ecarts ; **aucune regle de procedure neuve** (`E5` reste ouverte, troisieme palier) ; le reclassement annonce de `FRA-CE1-27` et `-29` ; routage. Index mis a jour. `DEC-0124` est amendee : la decision est appliquee (`5a0b55f3`, `03de0f6d`), son entete *"Non appliquee"* etait perime.

## 3. Lots candidats

Chaque ligne : la ligne de matrice, le prix de `nadia-prof` (bareme ou indicatif), le developpeur **tel que `RG1` a `RG6` de `composition-palier-3.md` le donnent**, ce qui manque. `RG1` : `lea-dev` n'a aucun lot de francais CE1 ; `RG2` : des developpeurs distincts sur des instances distinctes ; `RG4` : un worktree vivant a la fois.

| Code | Ligne | Prix, plafond | Instance | Developpeur | Etat |
|---|---|---|---|---|---|
| LP1 | `FRA-CE1-21`, `ce1-mots-invariables` (24 items, a passer en saisie) | 1,0, `couvert-valide` | A (ma proposition ; a `nadia-prof`) | `marc-dev` (`RG1`, auteur du paquet) | **pret**, sous `QP1` |
| LP2 | `MAT-CE1-10`, `ce1-masses-contenances` (21) | 1,0, `en cours` | B | `lea-dev` ou `sofia-sm` (`QP6`) | **pret** |
| LP3 | `MAT-CE1-11`, `ce1-calendrier-dates` (25) | 1,0, `en cours` | B | idem | **pret** |
| LP4 | `EMC-CE1-02`, regle et sanction (paquet a ecrire) | 1,0, `couvert-valide` | A' (`PAR-2`) | idem | **pret** |
| LP5 | `EMC-CE1-03`, droits et devoirs (a ecrire) | 1,0, `couvert-valide` | A' | idem | **pret** |
| LP6 | `EMC-CE1-04`, interet d'un seul et de tous (a ecrire) | 1,0, `couvert-valide` | A' | idem | **pret** ; hors du repli a 8,0 ; ordre interne de `nadia-prof` : dernier |
| LP7 | `MAT-CE1-15` illustre, `ce1-figures-planes` (20, a reecrire avec ses figures) | 1,5, `en cours` | B | idem | **conditionnel** : recevable illustre, **story ecrite ce jour**, mais le paquet est a reecrire et la borne de 8,0 ne la porte pas ; sa forme textuelle est refusee d'avance |
| LP8 | `FRA-CE1-03`, `ce1-mots-irreguliers` (20) | 1,0, `en cours` | a dire (hors X et Y) | `marc-dev` (`RG1`) | **conditionnel, pas de story** : non recevable tant que `nadia-prof` n'a pas juge le cousinage du paquet (*"Je le ferai si `camille-po` me le demande"*) ; je le lui demande ici, la story s'ecrit apres sa reponse |
| LP9 | `MAT-CE1-14`, grille montree | (a) 1,5 `en cours` ; (b) 3,0, ou 1,5 si un seul paquet, `couvert-valide` | B | a designer | non : avis de `theo-archi` (`A3`, un ou deux paquets, cliquet de poids des dessins) et `QP3` |
| LP10 | `FRA-CE1-09` (20, explications a ecrire) | 1,0, `couvert-valide` | francais CE1 | `marc-dev` | **s58 au plus tot** (condition 4) |
| LP11 | `FRA-CE1-08` (20) | 1,0, `couvert-valide` | francais CE1 | `marc-dev` | **s58 au plus tot, apres `-09`** |
| LP12 | `FRA-CE1-29`, mots sous la dictee (a ecrire) | 1,0, `en cours` | a dire | `marc-dev` | s58 au plus tot ; `TRV-CE1-1` a annoter |
| LP13 | `FRA-CE1-27`, ecouter un texte lu (a ecrire) | 1,0, `en cours` | a dire | `marc-dev` | terrain neuf, pas tant qu'A' existe |
| LP14 | `ART-CE1-01` (a ecrire) | 1,5, `couvert-valide` | a dire | a designer | corpus absent, terrain neuf |
| LP15 | `ART-CE1-02` (a ecrire) | 1,5, `couvert-valide` | a dire | a designer | idem, apres `-01` |
| LP16 | questionner le monde CE1 : `QLM-CE1-01` a `-06`, `-08`, `-10`, `-12` (9, `couvert-valide`), `QLM-CE1-07` moitie textuelle (`en cours`) | 1,0 chacune | a dire | a designer | paquets ecrivables, **aucun audit a s57** (`arbitrage-nadia-preparation-palier-3-ce1.md` 1.3) |

Sources des prix : `arbitrage-nadia-preparation-palier-3-ce1.md` section 2 (lot neuf textuel 1,0, lot illustre 1,5) ; `arbitrage-nadia-q05-q10.md` 1.4, 3, 4.3, 5.3 pour `LP1`, `LP9`, `LP12` a `LP15`. **Ce sont des prix indicatifs**, confirmes a son arbitrage prealable du palier 4.

## 4. Ce qu'il ne faut pas engager au palier 4, et pourquoi

| Ligne | Motif, avec ses mots quand ils sont a `nadia-prof` |
|---|---|
| `FRA-CE1-08`, `-09` | *"La condition 4 tient pour tout s57"* (`arbitrage-nadia-q05-q10.md` 2) : relire sa condition apres le rejeu d'`ART-CP-10` serait *"relever ma barre d'un cote et la baisser de l'autre au gre du calendrier"*. A s58, un seul des deux par sprint, `-09` d'abord. La stories sont ecrites, **non engagees, sans palier** |
| `FRA-CE1-29` | ouvrable *"au plus tot au sprint qui suit la levee de `R6`"* (3.2) ; `R6` est leve dans s57. `TRV-CE1-1` non annote, aucun paquet |
| `FRA-CE1-27` | terrain neuf (`PAR-13`) : *"il ne s'ouvre pas tant qu'A' existe"* (3.1) |
| `ART-CE1-01`, `-02` | terrains neufs, pas tant qu'A' existe ; **le corpus manque** (12 reproductions, ni dessin ni photographie) ; la double preuve d'une photographie n'est pas lue par `theo-archi` |
| questionner le monde CE1 | *"il ne prend pas le sprint ou une autre instance audite un paquet sans cousine"* ; s57 est ce sprint (`ART-CP-10`, instance B, palier 1). Paquets ecrivables, aucun audit |
| `MAT-CE1-14` | prealables de `theo-archi` ouverts (`A3`, cliquet de poids) et lecture de *"infos"* non confirmee |
| `MAT-CE1-15` | au repli de 8,0 : forme illustree 1,5, B a 3,5 + A 2,0 + A' 3,0 = 8,5. Conditionnel a 11,5 (section 5bis) |
| `FRA-CE1-03` | non recevable tant que le cousinage n'est pas juge ; conditionnel a 11,5 |
| dessins (`QLM-CE1-09`, `-11`, moitie dessinee de `QLM-CE1-07`, `MAT-CE1-02`, `-09`) | **sortis du perimetre par la reponse de l'utilisatrice** (`DEC-0123` 3) ; condition de retour : une nouvelle reponse |
| `FRA-CE1-28`, `-30`, `-31`, `EMC-CE1-05` | `non couvrable en l'etat`, irreversibles |
| `LVE-CM1-06` | `R6` n'est plus son motif de refus, mais la ligne est de CM1, hors du perimetre CE1 du 2026-10-19 ; `nadia-prof` relit l'instruction de s45 quand une story le demande. Aucune story ecrite |

## 5. Composition de repli, 8,0 (si `nadia-prof` maintient la borne)

**C'est une proposition pour la composition, pas la composition** : `sofia-sm` la fait, `nadia-prof` prix et affecte (`DEC-0120` A.33, A.36).

| Instance | Poste | Prix | Nature |
|---|---|---|---|
| A (francais CE1) | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste |
| | `LP1`, `FRA-CE1-21` | 1,0 | lot |
| | **Total A** | **2,0** | 1 lot |
| B (mathematiques CE1) | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe |
| | `LP2`, `MAT-CE1-10` | 1,0 | lot |
| | `LP3`, `MAT-CE1-11` | 1,0 | lot |
| | **Total B** | **3,0** | 2 lots |
| A' (EMC) | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe |
| | `LP4`, `EMC-CE1-02` | 1,0 | lot, paquet ecrit dans le lot |
| | `LP5`, `EMC-CE1-03` | 1,0 | lot, paquet ecrit dans le lot |
| | **Total A'** | **3,0** | 2 lots |
| **Palier** | | **8,0** | 5 lots |

Arithmetique, sur onze lignes `instance|poste|prix|etat` de la proposition (`awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}'`) :

```
A|arbitrage prealable|0.50|engage
A|marge de rejeu|0.50|marge
A|FRA-CE1-21 lot|1.00|engage
Ap|arbitrage prealable|0.50|engage
Ap|marge de rejeu|0.50|marge
Ap|EMC-CE1-02 lot|1.00|engage
Ap|EMC-CE1-03 lot|1.00|engage
B|arbitrage prealable|0.50|engage
B|marge de rejeu|0.50|marge
B|MAT-CE1-10 lot|1.00|engage
B|MAT-CE1-11 lot|1.00|engage
```

Sortie lue a l'ecriture : voir 5.3.

### 5.1 Bornes

| Regle | Cette proposition |
|---|---|
| 8,0 par palier (`PAR-9`) | 8,0 ; aucun non affecte |
| 5,0 et trois lots au plus par instance (`PAR-8`) | A 2,0 et 1 lot ; B 3,0 et 2 lots ; A' 3,0 et 2 lots |
| Une matiere et un niveau jamais partages (`PAR-2`) | francais CE1 a A, mathematiques CE1 a B, EMC a A' (instance qui le porte pour le reste de s57) |
| Aucun terrain neuf tant qu'A' existe (`PAR-13`) | aucun lot de questionner le monde, d'arts plastiques ni de `FRA-CE1-27` ; `nadia-prof` ne classe pas `FRA-CE1-21` terrain neuf (sa cousine nommee est `FRA-CP-09`, `arbitrage-nadia-q05-q10.md` 4.3) : **a confirmer a son arbitrage** |
| Aucun prix alloue a un poste sans story | cinq stories ecrites, fin de `backlog.md` |
| Ordre de `DEC-0120` A.35 (mathematiques 4a, francais 4b, puis EMC) | tenu : tous les lots de rang 4 **recevables et prets** entrent (`LP1`, `LP2`, `LP3`) ; `LP7` ne tient pas (8,5), `LP8` n'est pas recevable, `LP9` n'est pas pret. **A.35 n'est pas amende** |

### 5.2 Ce que le repli donne, si les verdicts tombent (le 9,0 : `couvert-valide` 156, compteur 163 ; le 11,5 : compteur 165)

| Mesure | Avant | Apres |
|---|---|---|
| `couvert-valide` (`FRA-CE1-21`, `EMC-CE1-02`, `EMC-CE1-03`) | 152 / 200 = 76,00 % | **155 / 200 = 77,50 %** |
| `en cours` (`MAT-CE1-10`, `MAT-CE1-11`) | 5 | 7 |
| compteur de version (A.32) | 157 / 200 = 78,50 % | **162 / 200 = 81,00 %** |

Si `FRA-CE1-27` et `-29` sont reclassees (`DEC-0120` A.123), le denominateur est 202 : 155 / 202 = 76,73 % et 162 / 202 = 80,20 %, a numerateur egal. Aucun de ces chiffres n'est un engagement : un lot refuse ne ferme rien.

### 5.3 Les lignes de registre ne sont pas encore un registre

La sortie de l'`awk` du bloc ci-dessus (onze lignes) : `A 2.00`, `Ap 3.00`, `B 3.00`, `total 8.00`, lue a l'ecriture. **Le registre du palier 4 n'est pas commite** : il l'est a la cloture du palier 4, avec les chiffres de `nadia-prof`.

## 5bis. Composition maximale pour trois instances en parallele

Lue sur douze puis quatorze lignes (`awk` lance ce jour, sortie lue) : **9,0** = `A 2.00`, `Ap 4.00`, `B 3.00`, 6 lots ; **11,5** = `A 3.00`, `Ap 4.00`, `B 4.50`, 8 lots (`engage 7.50`, `marge 1.50`, `conditionnel 2.50`). Plafond par instance respecte : 5,0 et trois lots (B, la plus chargee, est a 4,5 et trois lots).

| Instance | Poste | 9,0 pret | 11,5 conditionnel |
|---|---|---|---|
| A | arbitrage 0,5 ; marge 0,5 | 1,0 | 1,0 |
| | `LP1` `FRA-CE1-21` | 1,0 | 1,0 |
| | `LP8` `FRA-CE1-03` | - | 1,0 (cousinage a juger, pas de story) |
| | **A** | **2,0** | **3,0** |
| B | arbitrage 0,5 ; marge 0,5 | 1,0 | 1,0 |
| | `LP2`, `LP3` | 2,0 | 2,0 |
| | `LP7` `MAT-CE1-15` illustre | - | 1,5 (story ecrite, paquet a reecrire) |
| | **B** | **3,0** | **4,5** |
| A' | arbitrage 0,5 ; marge 0,5 | 1,0 | 1,0 |
| | `LP4`, `LP5`, `LP6` | 3,0 | 3,0 |
| | **A'** | **4,0** | **4,0** |
| **Palier** | | **9,0**, 6 lots | **11,5**, 8 lots |

**Pourquoi pas neuf lots.** A n'a que deux lignes de francais CE1 recevables ; `-08`, `-09`, `-29` sont a s58 et `-27` est un terrain neuf. **Pret** = `LP1` a `LP6` : story, paquet ou ecriture dans le lot, conditions ecrites, aucune reponse de l'utilisatrice. **Pas pret** = `LP7` (paquet a reecrire, developpeur), `LP8` (jugement de `nadia-prof`), `LP9` (`theo-archi`, `QP3`).

**Developpeurs par `RG1` a `RG6`** : A, `marc-dev` (`LP1`, puis `LP8`, un worktree a la fois, `RG4`) ; B et A', `lea-dev` et `sofia-sm`, **sens non deduit** (`QP6`). Chacun porte jusqu'a trois lots l'un apres l'autre : le parallele est entre instances. **Ce qui change avec six ou huit lots est a `sofia-sm`** (`DEC-0120` A.130) : si `RG4` tient, B et A' livrent leurs lots en sequence ; la table d'isolation garde une ligne par agent et par instance, sans ligne neuve, tant qu'aucun developpeur n'a deux worktrees vivants.

**Risques du parallele** (mesures du palier 3, `DEC-0120` A.130) : contention de build (un `verify` de 9 min 30 s a 12 min 40 s sur worktree propre ; 7 h 26 au mur sous quatre agents, **chiffre relaye, non lu dans un fichier**) ; conflits de `catalogue.yaml` et de l'inventaire de `TexteAccentueExposeParLePortEntrantTest` a chaque fusion (4 sur 5 au palier 3, sept a huit attendus ; huit `verify` entiers a 12 min = 96 min) ; un seul registre d'identifiants (aucun identifiant neuf) ; matrice et `DEC` editees a un seul endroit, par moi, apres chaque lot de verdicts.

## 6. Hors `PAR`, proposes au palier 4, prix 0

`PAR-16` ne demande pas de prix pour une story hors `PAR`, mais une story par poste engage. **Ce sont les trois ecartements a compter** (`DEC-0120` A.118) : un troisieme, au palier 4, est une direction que personne n'a choisie.

| Story | Etat | Ce qui manque |
|---|---|---|
| `S57-GARDE-NOMBRE-DE-MANCHES` | **prete** (liste fermee a huit entrees, amendee le 2026-10-19) ; deuxieme ecartement | un developpeur (`theo-archi` pour la forme, `yanis-qa` pour les morsures) |
| `S57-TYP1-TEXTES-DE-GABARIT` | deuxieme ecartement ; `Q-T12` ouverte chez `theo-archi` | sa reponse |
| `S57-MESSAGE-PARTIE-PERDUE` | deuxieme ecartement ; mesure prealable (nombre de lignes a 744 et a 1200) non faite | qui mesure (`sofia-sm`) |
| `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` | **ecrite ce jour**, premiere inscription | l'avis de `nadia-prof` sur la section 3 de `decision-theo-garde-ce1-e3.md` (`CA4`) : sans lui elle n'entre pas en sprint |
| reserves du palier 3 (pli sous la reponse attendue, verdict et indice de `-06` 14, mot a trait d'union coupe, item 17 d'EMC) | **non ecrites en stories ici** (`verdict-nadia-palier-3-ce1.md`, section 7 me les demande) ; premiere inscription | je les ecris a l'ouverture si l'utilisatrice veut leur correction : non bloquantes, deja portees par les cellules de matrice |

Developpeurs : a designer par `sofia-sm` ; ni `RG1` a `RG6` ni moi ne les deduisons.

## 7. Ce qui est pret, ce qui exige une reponse

### 7.1 Pret

`LP1` a `LP6` et le hors `PAR` de la section 6 sauf `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` et la garde CE1. Ce qui reste a faire avant l'ouverture, par ordre : `cloture-palier-3.md` (`sofia-sm`) ; l'arbitrage prealable du palier 4, par instance (`nadia-prof`), avec instance et prix de `LP1` ; la composition (`sofia-sm`) et son amendement de `planning.md` ; la table d'isolation (`D2`, quatre conditions).

### 7.2 Exige une reponse de l'utilisatrice

| Code | Question | Ce qu'elle debloque | Sans reponse |
|---|---|---|---|
| `QP6` | **Developpeurs de B et d'A'** : `RG2` demande des developpeurs distincts, `RG1` exclut `lea-dev` du francais CE1, `marc-dev` est a A ; B et A' se reduisent a `lea-dev` et `sofia-sm`, **sens non deduit** (meme cas qu'`AD1` au palier 3, ou elle a repondu *"ok"* a la recommandation de `sofia-sm` : `lea-dev` sur les deux, ecart a `RG2` dit). Faits : `lea-dev` a ecrit `ce1-emc-biens-communs` et ses corrections ; `sofia-sm` n'a aucun lot au palier 4 | l'engagement de `LP2` a `LP5` a l'ouverture | **premier report pour chacun des quatre lots retenus et non engages a l'ouverture** (`DEC-0120` A.66), comme `MAT-CE1-01` et `EMC-CE1-01` au palier 3. **C'est la seule question qui coute un report** |
| `QP7` | **s57 se clot-il apres le palier 4 (ou un palier 5 de `LP6`, `LP7`, `LP9`) ?** Tout ce que `nadia-prof` renvoie a "s58" ne s'ouvre pas dans s57 *"quelle que soit sa duree"* : `-09` puis `-08`, `FRA-CE1-29`, les neuf lots de questionner le monde, puis les terrains neufs (`FRA-CE1-27`, arts plastiques) si A' n'existe plus. **16 lignes sur 43 `non couvert`** attendent ce sprint. `P4'` de `duree-et-paliers.md` : le sprint s'arrete *"quand l'utilisatrice l'arrete"* | l'ordre de tout le reste | s57 se prolonge sans pouvoir rien ouvrir de plus que `LP6`, `LP7`, `LP9` : c'est la forme exacte du report repete que `DEC-0007` a nomme |
| `QP5` | **Le maximum de la version passe de 200 a 202** si `FRA-CE1-27` et `-29` sont reclassees (`DEC-0120` A.123). Vous l'avez fixe a 200 (`Q-U10`). Le taux baisse de 0,75 point sans qu'aucune ligne ne bouge. **Une information, avec une confirmation** | le reclassement | je ne reclasse pas ; les deux lignes restent hors du denominateur et `nadia-prof` ne peut pas les auditer sous un statut qui dit qu'elles ne le sont pas |
| `QP3` | **`MAT-CE1-14`, *"ajouter une grille pour infos a l'enfant"*** : voulez-vous un quadrillage montre sur lequel l'enfant se repere, se deplace et appuie sur la case, ou un tableau d'informations sans cases designables ? Le premier lit *"infos"* comme je l'ai ecrit (`DEC-0123` 2.1) ; le second ne leve pas `DEP-CE1-2` | `LP9`, forme (b) | `LP9` reste hors palier |

### 7.3 Ce qui n'est pas a l'utilisatrice, et qui conditionne

`QP1` (`theo-archi`, `nadia-prof`) : l'apostrophe de *"Aujourd'hui"* en saisie. `QP2` (`theo-archi`) : les gardes de `texteAVoixHaute` d'`ADR-0075` et un texte de cinq phrases. `QP4` (`theo-archi`) : un attribut qui supprime les mots proposes au-dessus du clavier existe-t-il ? Instance de `LP1`, confirmation des prix, jugement de cousinage de `LP8` (`nadia-prof`). **Quatre decisions de `cloture-palier-2.md` section 13** (crochet du harnais, `setup-node` et `setup-python` dans `ci.yml`, `TMPDIR`, retro) : aucune reponse dans le depot (`ci.yml` n'a ni `setup-node` ni `setup-python`) ; je les signale, elles ne sont pas a moi.

## 8. Reports comptes de cette proposition

| Poste | Compte |
|---|---|
| `LP1` a `LP5` | premier ecartement au palier 3 : **soldes par l'entree** au palier 4 (repli, 9,0 et 11,5) |
| `LP6` (`EMC-CE1-04`) | premier ecartement au palier 3 ; **solde au 9,0**, deuxieme ecartement au repli de 8,0 |
| `LP7`, `LP8`, `LP9` | premier ecartement au palier 3 ; **deuxieme** s'ils ne sont pas engages au palier 4. **Un troisieme demande une `DEC` avec son motif** |
| `LP10`, `LP11`, `LP12`, `LP13`, `LP14`, `LP15`, `LP16` | **pas des reports de mon fait** : une condition de `nadia-prof` les renvoie a s58 ou a la fin d'A' ; ils ne sont inscrits a aucune composition |
| trois stories hors `PAR` | deuxieme ecartement, retenues ici (section 6) |
| `E5` | ouverte depuis `cloture-palier-1.md` ; troisieme palier |

## 9. Ce que ce fichier ne fait pas

N'engage aucun poste, ne fixe aucun prix, n'affecte aucune instance. Ne prononce aucun Done, ne juge aucun contenu. Ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, test, ni `composition-palier-3.md`, `cloture-palier-3.md`, ni la matrice (152 / 200, aucune cellule ne change). N'alloue **aucun identifiant de condition ou de reserve** : tous ceux qui sont cites existent deja ; les reperes `PR1` a `PR6`, `LP1` a `LP16` et `QP1` a `QP7` n'ont pas de tiret et n'appartiennent qu'a ce fichier et aux stories qui les citent. Non commite.

## 10. Jobs de `ci.yml`

Lances depuis le checkout, avec tous les fichiers de ce geste presents dans l'arbre, avant tout commit (je n'en fais pas) : voir le message de livraison. Je ne deduis aucun job que je n'ai pas lance ; le Done technique n'est pas prononce par moi.

## Amendement du 2026-10-21, 18:45 (`camille-po`, ecart 9 du releve de cloture du palier 3) - provenance de la consigne du palier 4

**Rien au-dessus n'est reecrit.** Les mots de la consigne (*"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*), cites en sections 0 et PR2b comme *"relayee, non lue de sa main"*, ont ete confirmes mot pour mot le 2026-10-21 (`DEC-0120` A.132 ; `DEC-0062`, amendement E) : forme 2 de `DEC-0062`. **Ce qui ne l'est pas** : ma lecture (le seuil est la borne de 8,0 de `PAR-9`, les instances sont A, A' et B), toujours *a confirmer* ; la confirmation est elle-meme arrivee par l'agent lanceur (`DEC-0062`, amendement F, premier point). Le chiffre *"7 h 26 au mur"* de la section 5bis reste *"relaye, non lu dans un fichier"* : il est de forme 3 et ne fonde aucun choix de ce fichier. `E5` reste ouvert.
