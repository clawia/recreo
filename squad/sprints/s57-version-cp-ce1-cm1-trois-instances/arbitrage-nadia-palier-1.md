# s57 - arbitrage prealable du palier 1 : instances A, A' et B

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-14, 14:40 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `a69baf0c`, branche `s57-nadia-p1`, worktree dedie, arbre propre avant ecriture.
- **Type** : arbitrage prealable du palier 1 (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 11.3
  condition 3), pour les trois instances. Il tranche ce que `DEC-0120` A.57 me route : les affectations proposees par
  `composition-palier-1.md`, `Q-P1` et `Q-P2`.
- **Ce qu'il n'est pas** : `planning.md`. s57 n'est pas ouvert. Aucun Done, aucun verdict de jeu.
- **Audit degrade, declare** : aucun lot n'est branche, je n'ai rien joue. J'ai lu les cinq paquets candidats de Y et
  le paquet de X que je retiens (section 0) ; une lecture de fichier n'est pas un audit.

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| D1 | `Q-P1` : marge de rejeu de B | **B garde sa marge de 0,5.** Ma condition 1 de 11.3 etait plus etroite que son motif ; corrigee ci-dessous | 2 |
| D2 | `Q-P2`, groupe X | **`FRA-CE1-04`, `ce1-texte-narratif`**, a A | 3.1 |
| D3 | `Q-P2`, groupe Y | **`FRA-CE1-10`, `ce1-phrases-types`, et `FRA-CE1-15`, `ce1-accord-groupe-nominal`**, a A'. `FRA-CE1-24` attend le palier 2 | 3.2 |
| D4 | Affectations et prix | **Toutes confirmees**, aux prix proposes. Aucun lot refuse ni reduit | 4 |
| D5 | Bornes | **Tenues** : 8,0 ; 5,0 par instance ; trois lots au plus ; 0,5 de marge par instance | 5 |
| D6 | Points d'audit annonces | Trois, ecrits avant les stories pour que `camille-po` les verifie contre l'existant | 6 |

Le choix de D2 et D3 coincide avec celui de `camille-po`. **Le motif, lui, est neuf** : elle a pris l'ordre du
referentiel et l'a dit ; je donne en section 3 un motif pedagogique que le depot permet de verifier. La coincidence n'est
pas un motif.

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Arbre | `git rev-parse --short HEAD` | `a69baf0c` |
| Stories des lots | `grep -c '^### S57-BR' docs/backlog/backlog.md` | 0 : inchange depuis `composition-palier-1.md` section 4 |
| Paquets candidats | `grep -c -- '- enonce:'` sur chaque fichier de `contenu/` | `ce1-texte-narratif` 20, `ce1-pronoms` 20, `ce1-inference` 20, `ce1-texte-documentaire` 20, `ce1-negation` 24, `ce1-phrases-types` 24, `ce1-accord-groupe-nominal` 24, `ce1-familles-de-mots` 24 |
| Paquets au catalogue | `grep -c "paquet: <nom>$"` sur `catalogue.yaml` | `ce1-texte-narratif`, `ce1-phrases-types`, `ce1-accord-groupe-nominal` : 0 chacun ; leurs cousins `cp-texte-narratif`, `cm1-reperes`, `cp-pluriel`, `ce1-synonymes-contraires` : 1 chacun |
| Garde-fou de la borne du texte support au CE1 | `grep -rl 'LON-CE1-3' --include=*.java .` ; meme commande pour `LON-CE1-5` | 0 fichier ; 14 fichiers |
| Arithmetique du palier | `awk` sur les postes de la section 4 | A 2,50 ; A' 3,00 ; B 1,75 affectes et 0,75 non affectes ; total 8,00 |
| Lu en entier | `ce1-texte-narratif.yaml`, `ce1-phrases-types.yaml`, `ce1-accord-groupe-nominal.yaml`, `ce1-familles-de-mots.yaml` ; `ARBITRAGE-PREALABLE-s48-francais-ce1.md` sections 1 a 7 ; `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 1.1 ; `DEC-0120` section 1, A.33 a A.36, A.53 a A.57 ; `composition-palier-1.md` ; `duree-et-paliers.md` | - |

Non lance : aucun jar, aucun jeu, aucun test Maven de contenu.

---

## 1. Ce que le palier 1 doit tenir, rappele de ma main

Ma decision du 2026-10-13 (11.3), confirmee par l'utilisatrice (`DEC-0120` A.53, *"oui"*, forme 2, relaye) : borne de
**8,0** pour le palier, somme des budgets des instances (`PAR-9`) ; **5,0** et **trois lots** au plus par instance
(`PAR-8`) ; marge de rejeu de **0,5**, nommee a l'ouverture, allouee a aucun lot ; arbitrage prealable de **0,5** par
instance avant le premier lot ; **aucun chiffre au-dela de 8,0** dans ce palier (11.4). Les six conditions de mon accord
sur `PAR-2` (4.2) valent sans changement : `-08` et `-09` ne sont pas des lots de s57 ; aucun lot ne change de groupe ; un
sous-repertoire de scratchpad et un port par agent, par instance et par palier.

**Ce que la condition 3 demande et qui n'existe pas ici** : porter la cloture du palier precedent. Le palier 1 n'en a
pas.

---

## 2. `Q-P1` : B garde sa marge de 0,5

**Correction de mon propre constat, avant d'arbitrer.** J'ai ecrit en 11.3, condition 1 : *"une marge de rejeu de 0,5
par instance qui porte un lot"*. En 4.3, le meme jour, je donnais une marge a B, qui ne portait aucun lot. Les deux ne
tiennent pas ensemble. **C'est la condition 1 qui est trop etroite** : la marge existe pour qu'*"un refus, sa correction et
mon rejeu tiennent"* dans l'instance (4.4, point 3). Ce qui la rend due n'est pas un lot, c'est **un poste qui finit par un
verdict pouvant etre un refus**. La phrase de 11.3 reste ecrite ; elle se lit desormais *"par instance qui porte un lot
ou un rejeu"*. Aucune equipe n'a ete jugee contre l'ancienne formule : ce n'est pas un poteau deplace.

**B porte deux postes qui finissent par un verdict** :

| Poste | Ce qu'un refus y couterait | Source |
|---|---|---|
| Rejeu d'`ART-CP-10` (0,5) | la ligne la plus chere du depot : quatre reports (s50 a s53) et trois engagements sans fermeture (s54 a s56). Un second refus sans marge irait droit au report | `DEC-0120` 4.2 ; `cloture-nadia.md` de s56 |
| Rejeu de `S54-OBS-SCI-CONSIGNE` (0,25) | un refus sans rejeu possible dans s57 vaut abandon a la cloture : un troisieme report est interdit | `DEC-0120` A.34 |

> **Decision `Q-P1` : B garde sa marge de 0,5, nommee a l'ouverture du palier, allouee a aucun poste.** Elle couvre un
> second rejeu d'`ART-CP-10` (0,5) ou un second rejeu de `S54-OBS-SCI-CONSIGNE` (0,25).
>
> **Les 0,75 non affectes de B restent non affectes**, avec une seule exception que j'ouvre ici : si les deux rejeux de
> B sont refuses dans ce palier, le second rejeu de `S54-OBS-SCI-CONSIGNE` (0,25) se prend sur eux. Un rejeu n'est pas
> un lot ; aucun lot n'y entre (10.2 (b), inchange). B reste a 2,5, la somme a 8,0.

---

## 3. `Q-P2` : quels lots, et pourquoi

### 3.1 Groupe X : `FRA-CE1-04`, `ce1-texte-narratif`

Disponibles : `-04`, `-05`, `-06`, `-07`, `-11` (`DEC-0120` section 1). **Je prends `-04`, pour trois motifs.**

1. **Les frontieres de `-05` et de `-06` se jugent contre `-04`.** Ma table de s48 (section 7) les ecrit contre lui :
   `-04` / `-06`, *"reponse ecrite / reponse non ecrite"* (`RCT-CE1-2`, `INF-CE1-2`) ; `-05` / `-04`, *"la question cite le
   pronom ; sinon c'est une question de personnage"* (`PRN-CE1-1`). Une frontiere se juge sur le second paquet, contre le
   premier deja servi : c'est l'ordre que j'ai tenu a s55 pour `-12` avant `-13` (1.2). Auditer `-05` ou `-06` avant `-04`
   m'obligerait a juger une frontiere contre un paquet que l'enfant ne joue pas.
2. **C'est la progression de la classe.** On fait prelever ce qui est ecrit (`-04`) avant de faire deduire ce qui ne l'est
   pas (`-06`) et avant de faire suivre une reprise pronominale (`-05`). Un enfant de CE1 qui echoue a une inference ne
   sait pas s'il a mal lu ou mal deduit ; un enfant qui a deja reussi le prelevement litteral, si.
3. **Le texte support au CE1 rencontre son premier contenu sur la ligne la plus proche d'une ligne servie.** Aucun
   texte support n'est servi au CE1 (`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, 1.1) et la borne `LON-CE1-3` n'a
   **aucun garde-fou** (section 0 : 0 fichier). `RCT` a pour cousine directe `NAR` (`cp-texte-narratif`, servi) : memes
   questions *qui*, *ou*, plus *quand* et l'ordre. Sur `-04`, ce qui est neuf est presque seulement la longueur du texte ; je
   peux la juger isolee. Sur `-07`, je jugerais en meme temps la longueur et l'exactitude documentaire (`DOC-CE1-3`).

**Pourquoi pas `-11`** (negation, sans texte support) : rien ne depend de lui dans le referentiel, et il porte par
construction le biais de longueur de `TRV-CE1-2`. Il n'a aucun motif d'aller avant `-04`.

### 3.2 Groupe Y : `FRA-CE1-10` et `FRA-CE1-15`

Disponibles a s57 : `-10`, `-15`, `-24` (`-08` et `-09` exclus, condition 4). **Je prends `-10` et `-15` ; `-24` attend le
palier 2.**

1. **`-10` et `-15` sont ce sur quoi les deux lignes restantes de Y s'appuient.** `RLP-CE1-1` (`-09`) fait reperer une
   majuscule ou un point absents et un accord de groupe nominal oublie : le premier est l'objet de `-10` (`TPH-CE1-2`), le
   second celui de `-15` (`GNA-CE1-1`). La frontiere `-08` / `-09` / `-10` se lit sur `TPH-CE1-2` (s48, section 7).
   **`-08` et `-09` sont les deux seules lignes de francais CE1 sans cousine servie** (s55, 1.1). Servir `-10` et `-15` leur
   en donne une, pour s58. `DEC-0120` (1.2) l'ecrit deja pour `-15` : *"`relire-une-phrase` apres `accord-groupe-nominal`"*.
2. **`-24` ne porte rien de tel** : rien dans le referentiel ne s'appuie sur les familles de mots, et sa cousine `SYC`
   (`ce1-synonymes-contraires`) est servie. Le retarder d'un palier ne retarde aucune autre ligne.
3. **Si l'utilisatrice arrete s57 apres le palier 1** (`duree-et-paliers.md` P4'), ce qui est servi est ce dont la suite
   depend. C'est le seul cas ou l'ordre entre paliers change quelque chose : sinon les trois lots de Y passent dans s57.

**Ce que je ne fais pas** : je ne retarde pas `-24` parce qu'un de ses items me parait fautif (6.3). Ce constat est une
lecture de fichier, pas un verdict, et il ne serait pas un motif d'ordre : il se corrige avant le branchement.

---

## 4. Affectations et prix, confirmes

| Instance | Poste | Prix | Nature | Decision |
|---|---|---|---|---|
| **A (X)** | arbitrage prealable | 0,5 | fixe | **confirme** |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | **confirme** |
| | `VSJ-CE1-R1` (`S56-VSJ-CE1-R1`, `FRA-CE1-13`) | 0,5 | reserve, pas un lot ; reste a A (condition 3 de 4.2) | **confirme** |
| | `FRA-CE1-04`, `ce1-texte-narratif` | 1,0 | lot | **confirme**, motif 3.1 |
| | **Total A** | **2,5** | 1 lot | |
| **A' (Y)** | arbitrage prealable | 0,5 | fixe | **confirme** |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | **confirme** |
| | `FRA-CE1-10`, `ce1-phrases-types` | 1,0 | lot | **confirme**, motif 3.2 |
| | `FRA-CE1-15`, `ce1-accord-groupe-nominal` | 1,0 | lot | **confirme**, motif 3.2 |
| | **Total A'** | **3,0** | 2 lots | |
| **B** | arbitrage prealable | 0,5 | fixe | **confirme** |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | **confirme**, `Q-P1` (section 2) |
| | rejeu d'`ART-CP-10` | 0,5 | rejeu, pas un lot (`PAR-10`) | **confirme** |
| | `S54-OBS-SCI-CONSIGNE`, `lea-dev` | 0,25 | rejeu, pas un lot | **confirme** |
| | non affectes | 0,75 | seule exception : section 2 | - |
| | **Total B** | **2,5** | 0 lot | |

**Sources des prix** : mes tarifs de 4.3 (lot 1,0 ; `VSJ-CE1-R1` 0,5 ; rejeu d'`ART-CP-10` 0,5) et de la section 3
(`S54-OBS-SCI-CONSIGNE` 0,25). **Je ne refuse ni ne reduis aucun lot.** Aucun des trois n'a de motif de refus avant d'etre
joue, et un tarif plus bas qu'1,0 pour un lot neuf n'a aucune mesure : je ne l'ecris pas.

**Ce que coute l'ecriture de ce document** : il ouvre les trois arbitrages prealables du palier ; il est pris sur leurs
0,5. La lecture complete des items de chaque lot reste due par l'instance qui l'audite, avant son premier lot, sur ce
meme poste. Je n'ecris pas de prix pour ce document seul : je n'en ai pas de mesure.

**Le glissement d'un poste entre paliers** (`composition-palier-1.md` section 4, question a `sofia-sm`) : pour ma part,
c'est ma condition 2 de 11.3, *"un lot non audite a la cloture d'un palier glisse au suivant, ecrit comme tel"*, et je
l'applique aux rejeux comme aux lots. Ce n'est pas un report **au sens de ma porte**. Le compte des reports de
`S54-OBS-SCI-CONSIGNE` (`DEC-0112`, `DEC-0120` A.34) est a `camille-po` et `sofia-sm`.

---

## 5. Bornes, verifiees

| Regle | Valeur au palier 1 | Tenue |
|---|---|---|
| Somme des budgets = 8,0 (`PAR-9`) | 2,5 + 3,0 + 2,5 = **8,0** (`awk`, section 0) | oui |
| Plafond dur de 5,0 par instance (`PAR-8`) | A 2,5 ; A' 3,0 ; B 2,5 | oui |
| Trois lots au plus par instance (`PAR-8`) | A 1 ; A' 2 ; B 0 | oui |
| Marge de rejeu de 0,5 par instance | A, A', B : 0,5 chacune, allouee a aucun poste | oui (section 2 pour B) |
| Arbitrage prealable de 0,5 par instance, avant le premier lot | ce document, avant tout lot ; lecture des items avant le premier lot de chaque instance | oui, si les instances le tiennent |
| Aucun chiffre au-dela de 8,0 | aucun | oui |
| `-08` et `-09` hors s57 ; aucun lot ne change de groupe | `-04` en X ; `-10`, `-15` en Y | oui |
| `PAR-13` | A et A' sur le francais CE1, qui n'est pas un terrain neuf ; aucun des trois lots n'est sans cousine (3.1, 3.2) ; B sur deux rejeux de matieres servies | oui |

**Porte avant le premier worktree d'un lot de A'**, rappelee et non tranchee ici : `git merge-base --is-ancestor 729b0d8a
HEAD`, sortie 0, citee par le developpeur (`duree-et-paliers.md` section 5 ; ma condition 2 de 4.2).

---

## 6. Points d'audit annonces, avant les stories

Lus dans les fichiers, pas joues. Je les ecris **avant** que `camille-po` ecrive les stories (`DEC-0120` A.56), pour
qu'elle les verifie contre l'existant et que personne ne les decouvre a mon refus. **Aucun n'est une condition neuve ni un
identifiant neuf** : chacun renvoie a une condition deja ecrite.

### 6.1 `ce1-texte-narratif` (A)

1. **La majuscule designe la bonne reponse sur deux items d'ordre.** Items *"Que fait Sara avant de couper la pomme ?"* et
   *"Que fait Nour en premier ?"* : la bonne reponse (*"Sara pèle une pomme"*, *"Nour range ses jouets"*) est la seule
   proposition a majuscule ; les deux autres commencent par *"elle"*. C'est la propriete que j'ai exigee de
   `ce1-verbe-sujet` a s56 (`ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, 2.2). Sur les quatre autres items d'ordre
   ou une proposition porte une majuscule, elle designe un distracteur : le biais n'est pas systematique, mais un enfant
   qui l'a vu deux fois peut s'en servir. Je le jugerai en jouant.
2. **Un indice renvoie au monde, pas au texte.** *"Pense à ce qu'on fait avant de couper un fruit."* (item de Sara) :
   `RCT-CE1-1` veut une reponse *"ecrite dans le texte : aucune inference"*, et `TRV-CE1-5` un indice qui *"dit ou regarder"*.
   Cet indice fait deduire au lieu de relire.
3. **`LON-CE1-3` et `LON-CE1-4` n'ont aucun garde-fou** (section 0). Je compte les vingt textes a l'audit.

### 6.2 `ce1-phrases-types` et `ce1-accord-groupe-nominal` (A')

Rien a signaler a la lecture. Les proportions de `TPH-CE1-1` et de `GNA-CE1-1` annoncees en tete des deux fichiers sont a
recompter a l'audit, pas a croire.

### 6.3 `ce1-familles-de-mots` (palier 2, pour information)

**L'item *"livre"* attend *"librairie"***. `FAM-CE1-2` exclut *"aucune famille savante ou discutable"* : *livre* et
*librairie* viennent tous deux du latin *liber*, le premier par la voie populaire, le second par la voie savante ; c'est
une famille savante. Un enfant de CE1 n'y voit pas le radical, et le distracteur *livrer*, qui le porte a
l'oeil, est celui que l'indice du paquet lui apprend a choisir (*"Un mot de la même famille garde un morceau du mot."*).
A corriger avant le branchement du palier 2, ou ce sera un motif de refus.

---

## 7. Ce dont je reste la seule garde au palier 1

| Propriete | Qui la tient |
|---|---|
| La longueur des vingt textes de `ce1-texte-narratif` (`LON-CE1-3`, `LON-CE1-4`) | **moi seule** : aucun test ne la lit |
| Qu'une question d'ordre se repond par le texte et non par la majuscule ou le monde (6.1) | moi seule, en jouant |
| L'exactitude des marques d'accord et des familles (`GNA-CE1-2`, `FAM-CE1-2`) | moi seule |
| Qu'un palier ne s'ouvre pas avant que le precedent soit clos (condition 2) | moi, dans l'arbitrage du palier 2 ; `sofia-sm` au planning |
| La forme (c) sur un scratchpad ou un port, par palier | moi seule, en lisant les transcriptions a la cloture |

## 8. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Les trois stories (`DEC-0120` A.56), avec les points de la section 6 verifies contre l'existant ; acter `Q-P1` et `Q-P2` dans `DEC-0120` ; la correction de `ce1-familles-de-mots` (6.3) avant le palier 2 |
| `sofia-sm` | `planning.md` du palier 1 sur la section 4 ; la marge de B nommee a l'ouverture ; la lecture du glissement de `S54-OBS-SCI-CONSIGNE` (section 4, dernier paragraphe) |
| Utilisatrice | Aucune question neuve |

## 9. Ce que ce document ne fait pas

- Il n'ouvre pas s57, n'ecrit pas `planning.md`, n'engage aucune ligne, ne compose pas le palier 2.
- Il ne prononce aucun Done, ne joue aucun jeu, ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code.
- Il n'ecrit et n'alloue **aucun identifiant de condition ou de reserve**. Les codes `D1` a `D6` sont des reperes de ce
  document ; `Q-P1` et `Q-P2` sont ceux de `composition-palier-1.md`.
- Il ne lit pas la condition (i) (`DEC-0120` A.51) : le palier 1 ne demande aucune derivation au-dela de 8,0.

**Signe** : `nadia-prof`, s57, palier 1, 2026-10-14.
