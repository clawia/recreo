# s57 - questionner le monde CE1, neuf lots dans un palier : mon arbitrage prealable de l'instance, les sondes, `FER-1` sur `-05`, le plafond de 20 items, les rejeux, `-07`, l'etat de mes prealables

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-25. Heure non lue : le shell etait indisponible a l'ecriture.
- **Arbre** : branche `s57-p5-nadia-instance`, base `570599ce` annoncee par l'agent lanceur, **non verifiee par `git`** (shell
  indisponible).
- **Type** : arbitrage prealable de l'instance de questionner le monde CE1 pour le palier des neuf lots (point 7 de 5.4 de
  `arbitrage-nadia-palier-5-qlm-9-lots.md`), et reponses routees a moi par `DEC-0120` A.255. **Aucun Done**, aucune condition de
  Done neuve de ligne, **aucun identifiant neuf** : `K1` a `K5`, `M1` a `M6`, `P0` a `P3` renvoient a
  `arbitrage-nadia-palier-5-qlm-9-lots.md`, `QC1` a `QC9` a l'annotation du backlog. Ni code, ni paquet, ni catalogue, ni `DEC`,
  ni matrice, ni registre, ni backlog.
- **Je n'ai rien joue** : aucun paquet `ce1-qlm-*` n'existe (Glob du repertoire de contenu, 0 fichier, annotation du backlog).
- **Lu** : `arbitrage-nadia-palier-5-qlm-v3.md` ; `arbitrage-nadia-palier-5-qlm-9-lots.md` ; `reponse-nadia-palier-5-qlm-items-et-a229.md`
  ; `DEC-0120` A.224, A.225, A.237 a A.255 ; `docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme (les neuf stories et
  `QC1` a `QC9`) ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (2, 3.1 a 3.12) ;
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` (1, 2, 3.1) ; paquets `cp-qlm-etats-eau` et
  `ce1-emc-interet-commun` (en-tete).

## Resume

| # | Question | Reponse |
|---|---|---|
| 1 | Sondes *proposee* | **17 sondes : 16 validees, 1 remplacee (`-12` c2), 0 refusee** (section 1) |
| 2 | `FER-1` sur `-05` | **La lecture de `camille-po` est la mienne** : cinq elements, libelle entier. La temperature n'est pas un element, c'est la relation qu'`ETE-CE1-1` impose (section 2) |
| 3 | Plafond de 20 items (A.250) | **Je ne le leve pas et je n'en fais pas une condition de Done.** C'est une borne de bareme, compatible avec mes conditions (section 3) |
| 4 | Instance | **Le choix de la lettre n'est pas a moi.** Toute instance qui n'est pas B, avec un developpeur distinct de `lea-dev`, seule dans son palier, me convient ; mes conditions de l'instance sont en section 4 |
| 5 | Rejeux | **Condition de ma part.** Palier des neuf = palier 9 : un rejeu dans la marge du palier 9, les autres au palier 10 ; un refus au palier 10 va a s58 (section 5) |
| 6 | Moitie textuelle de `-07` | **Confirme : hors des neuf.** Rien ne change, sauf qu'au palier 10 elle passe apres les rejeux et les lots glisses (section 6) |
| 7 | Mes six prealables | **Quatre remplis, deux restent**, tous deux a d'autres agents (section 7) |

---

## 1. Les sondes marquees *proposee*

Je juge chaque sonde sur deux questions : refuse-t-elle bien ce que ma condition interdit ; une garde qui la refuse risque-t-elle de
refuser un item juste. Les exemples sont ecrits sans accents ; le test lit les formes accentuees. **La forme de chaque garde reste a
`theo-archi`** ; si une forme n'est pas possible, *"non garde : <condition>"* et je lis (`QC9`).

| Story, critere | Sonde | Verdict | Motif |
|---|---|---|---|
| bloc commun, `QC6` | `NRC-CE1-1` partie textuelle : un item copie mot pour mot d'un paquet de CP voisin est refuse | **validee** | Elle ne tient que la copie. Le sujet de l'item (meme question reformulee, meme bonne reponse a la meme tache) reste a moi. Pour `-02` et `-10`, ma table de 6.2 de V3 ne nomme aucun voisin de CP : la sonde n'y a rien a comparer, `NRC-CE1-1` y reste a ma lecture |
| `-01`, c2 | une espece hors liste, *la tortue* | **validee** | Plausible pour un enfant (elle pond des oeufs), hors de la liste fermee de `DVP-CE1-1` : c'est le bon piege |
| `-01`, c6 | *"Pour germer, la graine a besoin de lumiere."* | **validee** | C'est la phrase que `BPL-CE1-2` interdit. Les paraphrases restent a moi |
| `-03`, c3 | *"De quoi la graine a-t-elle besoin pour germer ?"*, bonne reponse *"de la lumiere"* | **validee** | La question est juste et doit rester possible avec *l'eau* ou *la chaleur* en bonne reponse (`BPL-CE1-2`) : la garde porte sur la bonne reponse, pas sur la question |
| `-03`, c3 | un indice qui dit que la graine a besoin de lumiere pour germer | **validee** | idem `-01` c6 |
| `-04`, c2 | la pomme de terre rangee avec les fruits et legumes est refusee, le paquet declarant la famille de chaque aliment avec sa source | **validee** | C'est le piege de `FAL-CE1-1`. La declaration par aliment rend la sonde lisible ; l'exactitude de chaque declaration contre sa source reste a moi |
| `-04`, c6 | *"Quel menu est mauvais pour la sante ?"* | **validee** | `FAL-CE1-2` : aucun menu qualifie de mauvais. Les variantes (*le moins bon*, *a eviter*) restent a moi |
| `-04`, c7 | *"Qu'as-tu mange ce midi ?"* | **validee** | `BIA-4` : elle demande a l'enfant ce qu'il mange et suppose un repas pris |
| `-05`, c3 | *"La glace est-elle solide ou liquide ?"* | **validee** | Paraphrase de *"La glace, est-ce solide ou liquide ?"*, item servi de `cp-qlm-etats-eau`. La copie exacte releve de `QC6` ; la paraphrase est ce que `ETE-CE1-1` doit tenir en plus |
| `-05`, c3 | *"Comment s'appelle le passage de la glace a l'eau ?"* | **validee** | Paraphrase de l'item servi *"Comment appelle-t-on le passage de la glace a l'eau liquide ?"* (bonne reponse *fondre*). Meme motif |
| `-05`, c5 | *"L'eau ne devient de la vapeur que quand elle bout."* | **validee** | C'est la regle fausse d'`ETE-CE1-2` (le linge seche sans bouillir) |
| `-08`, c4 | *"Lina s'est electrocutee en touchant la prise."* | **validee** | `DEL-CE1-2` : personnage nomme blesse et accident mis en scene |
| `-10`, c2 | *"On voit une foret et une riviere."* | **validee** | Deux elements partages de ma liste de `PAY-CE1-1`, aucun qui tranche |
| `-10`, c5 | *"Dans quel paysage habites-tu ?"* | **validee** | `PAY-CE1-2` et `EMC-6` |
| `-12`, c2 | un item qui ordonne *le petit-dejeuner*, *l'ecole*, *le diner* | **remplacee** | *L'ecole* est aussi un evenement d'une vie que `GEN-CE1-1` demande (*entree a l'ecole*) : une garde construite sur cette sonde risque de refuser un item juste. **Remplacement** : doit etre refuse un item qui ordonne *le reveil*, *le dejeuner*, *le coucher* ; **doit passer** un item qui ordonne *la naissance*, *l'entree a l'ecole*, *le premier emploi* (mes mots de `GEN-CE1-1`). Clause verte aujourd'hui, elle mord le jour ou une garde trop large est ecrite |
| `-12`, c3 | *"Qui vit avec Lina ?"* | **validee** | `GEN-CE1-2`, a la lettre |
| `-12`, c4 | *"Qui vit avec toi a la maison ?"* | **validee** | `GEN-CE1-2`, `BIA-4`, et `TRV-CE1-6` (elle suppose un logement) |

**Ce que je ne rejuge pas ici** : les entrees non marquees *proposee* (*"Que mange le chat ?"*, *"de l'air"*, *"La buee sur la vitre,
c'est de la vapeur d'eau."*, *100 degres*, les listes d'`AIM-CE1-1`, de `FAL-CE1-1`, de `PAY-CE1-2`). Je les ai lues : chacune reprend
ma condition ou l'illustre sans erreur.

---

## 2. `FER-1` sur `-05`

**Ma lecture : celle de `camille-po`, le libelle entier.** `FER-1` dit *"Chaque element enumere par le libelle de la competence, et
notamment par sa parenthese"* : la parenthese est un cas, pas la limite. Je l'ai deja lu ainsi sur la ligne voisine de CP (s28, 3.1 :
*"les deux etats et les deux changements sont la"*). Les cinq elements de `QLM-CE1-05` : **glace, eau liquide, vapeur d'eau, la fonte,
le gel**, chacun en bonne reponse d'au moins un item. La marque *[suppose]* de la story peut etre levee.

**Ce que la lecture ne compte pas** : *la temperature*. Elle n'est pas un element a donner en reponse ; c'est la relation qu'`ETE-CE1-1`
impose a chaque item qui ne porte pas la vapeur d'eau.

**Comment `FER-1` et `ETE-CE1-1` tiennent ensemble** (aucune condition neuve, c'est l'application des deux) :

- *La fonte* et *le gel* sont en bonne reponse dans un item ou la temperature est la donnee (il fait tres froid, on chauffe la glace),
  sous forme de nom ou de verbe (*elle fond*, *elle gele*). **Un item qui demande seulement le nom du changement ne compte pas** : c'est
  l'item servi de `cp-qlm-etats-eau`, deux fois (`ETE-CE1-1`, `NRC-CE1-1`).
- *La glace* et *l'eau liquide* en bonne reponse : de meme, dans un item ou un changement de temperature produit cet etat, jamais
  *"solide ou liquide ?"* seul.
- Au plancher : 4 items a la vapeur d'eau (un tiers de 10) plus un item pour chacun des quatre autres elements font 8 sur 10 [calcul].
  La ligne tient dans `FER-2`.
- **Qu'un item donne depasse ce que le paquet de CP a appris** (son indice de *geler* dit deja *"quand il fait tres froid"*) se juge a
  ma lecture avant `verify`, item par item. Aucune garde ne le tient.

La meme lecture du libelle entier est deja celle de `-12`, critere 1 (generations de la parenthese et evenements d'une vie) : je la
confirme.

---

## 3. Le plafond de 20 items (A.250)

**Je ne le leve pas, et je n'en fais pas une condition de Done.** C'est une decision de produit de `camille-po`, la borne de mon
bareme ; je ne refuserais pas un paquet de 21 items pour ce motif, mais il sortirait du bareme et demanderait une decision (A.250).

**Motif pedagogique** : rien ne demande plus de 20 items. Mes conditions de ligne tiennent dans 10 (le plus serre, `-04`, en demande 9,
reponse du jour, section 2). Au-dela du plancher, des items de plus donnent de la variete a un enfant qui rejoue, mais **chacun est un
fait de plus** sur une matiere ou l'erreur de fait passe facilement, et sur un paquet joue en dix manches une partie n'en montre que dix
(s28, 1 : *"la moitie des items n'est jamais vue en jouant"* a 20 items). Je les lis donc tous, sans que le jeu m'en montre la moitie.

**Charge de lecture** : 90 items au plancher, 180 a la borne, chacun contre sa source (`M2`) [calcul, deja ecrit]. Le plafond borne
cette charge ; il ne la reduit pas. **Ce qu'il ne change pas** : `M5`. Un lot que je n'ai pas lu et audite a la cloture glisse.

---

## 4. Mon arbitrage prealable de l'instance

### 4.1 Quelle instance

**La lettre n'est pas a moi** : C1 la donne a `camille-po`, A.255 route le palier et l'instance a l'utilisatrice. Sous `K2`, l'instance
est seule a me soumettre un lot dans ce palier : A, A' ou une instance neuve sont pour moi equivalentes **a trois exigences, deja
ecrites** : une seule instance porte les neuf lots (`K1`), ce n'est pas B (C1), son developpeur est distinct de `lea-dev` (C1, `RG2`).
La levee de `PAR-2` (A.243) ne me fait pas accepter un partage : `K1` tient, une ligne de voisinage (`NRC-CE1-1`, `CYC-1`) se lit
mieux quand un seul developpeur a ecrit les neuf paquets.

### 4.2 Ce que je tiens comme conditions de l'instance, pour ce palier

1. **Palier** : seule instance qui me soumet un lot (`K2`, non nomme par l'utilisatrice, donc tenu) ; pas le dernier de s57 (`K3`) ; au
   nominal, le palier 9, ou B n'a aucun lot (`K4` ; A.225, table : palier 9 *"aucun lot de B au nominal"*). Au repli d'A.215, `K5` reste
   une question a l'utilisatrice. **Les stories de reserve d'A'**, si elles restent ouvertes, ne sont pas des lots (A.205) et `K2` tel
   que je l'ai ecrit ne les nomme pas : je ne l'etends pas. Si elles sont mises dans ce palier, je les audite apres les neuf, et la
   charge est consignee comme telle.
2. **Ordre** (`M3`, `P0` a `P2`) : premier jet de `-01` commite seul ; ma lecture `lecture-nadia-premier-jet-<paquet>.md` avant les
   huit autres premiers jets ; puis `-02`, `-03` ; `-04`, `-05` ; `-06`, `-08` ; `-10`, `-12` ; audits joues dans le meme ordre, jar
   servi, commit joue nomme.
3. **Declencheur** : chaque premier jet est inscrit dans `planning.md` (lot, commit, heure) avant tout `verify` complet du paquet
   (6.1 de V3, `QC7`).
4. **Catalogue** : aucune entree `ce1-qlm-*` sur `round-two` avant mon Done (`QC3`). Un enfant ne joue pas un contenu refuse.
5. **Prix** : bareme 10,00 (fixe 1,0, dont cet arbitrage 0,5 ; 1,0 par lot, lecture avant `verify` comprise) [deja ecrit, 2.1 de mon
   arbitrage des neuf lots]. La valeur du relevement de `PAR-8` et la marge a neuf lots ne sont pas a moi (A.244).
6. **Verdicts** : neuf, un par ligne (`M1`) ; aucun adouci pour tenir le palier (`M5`). Plafond arithmetique 52 sur 64, pas un
   engagement.

### 4.3 Ce dont je reste la seule garde dans ce palier

Inchange (6 de mon arbitrage des neuf lots, `QC9`) : l'exactitude de chaque fait contre sa source ; `NRC-CE1-1` sur le sujet des items
et `CYC-1` ; `AIM-CE1-2` sur les paraphrases ; `BIA-4` sur `-04` et `-12`, `GEN-CE1-2`, `DEL-CE1-2` ; `TRV-CE1-6` ; l'operation
mentale de `FER-3` ; et, section 2, le depassement du CP item par item sur `-05`. **Aucune n'est couverte par un test.**

---

## 5. Les rejeux

**C'est une condition de ma part** (2.4 et `P3` de mon arbitrage des neuf lots), pas une decision de produit (A.246 point 6 le dit).

- **Palier des neuf = palier 9** : un rejeu dans la marge du palier 9, **le premier lot refuse dans l'ordre de `P2`**, parce que ce
  qu'il corrige peut valoir pour les suivants. Les autres rejeux vont au **palier 10** (`K4`).
- **Un lot non audite a la cloture du palier 9** n'est pas un rejeu : il glisse aussi au palier 10, et y passe avant tout lot neuf.
- **Au palier 10**, un rejeu refuse va a s58, ecrit d'avance (`K3`, `R5` de V3).
- **La marge** : son dimensionnement a neuf lots est a `camille-po` et a l'utilisatrice (A.244, A.249 point 4). Je ne la fixe pas ; je
  tiens seulement qu'un rejeu qui la depasse n'est pas comprime dans le palier 9.

---

## 6. La moitie textuelle de `-07`

**Confirme : elle reste hors des neuf** (5.2 de mon arbitrage des neuf lots, A.249 point 3). Elle reste recevable, seule dans son lot,
`CIR-CE1-1`, plafond `en cours` avec sa mention, 0 en `couvert-valide`. **Ce qui se precise** : au palier 10, elle passe apres les
rejeux et les lots glisses du palier 9 (section 5) ; sans place, s58. Dans le palier 9, je lis la frontiere de `-08` (aucune pile,
`DEL-CE1-1`). **Si l'utilisatrice la nomme parmi les neuf**, le palier porte dix lots, 11,00 au bareme, et elle passe en dernier dans
l'ordre de `P1` et `P2`.

---

## 7. Mes prealables avant l'ouverture (5.4 de mon arbitrage des neuf lots)

| # | Prealable | Etat | A qui |
|---|---|---|---|
| 1 | Ses mots consignes, et les regles qu'elle depasse | **rempli** : A.237, A.242, A.247 | - |
| 2 | Mon constat sur A.229 | **rempli sous reserve** : `reponse-nadia-palier-5-qlm-items-et-a229.md` 1, consigne en A.252 ; la reserve tient si l'utilisatrice corrige la reconstitution | - |
| 3 | Neuf stories, mes conditions mot pour mot, chaque critere negatif avec sa sonde | **rempli** : A.254 ; j'ai compare les conditions citees a 3.1 a 3.12 de l'instruction, aucun ecart trouve ; les sondes sont tranchees en section 1, la story `-12` critere 2 prend le remplacement | `camille-po` reporte le remplacement de la section 1 et leve le *[suppose]* de `-05` (section 2), par annotation datee |
| 4 | Ma reponse sur les items et les mots d'`AIM-CE1-2` | **rempli** : `reponse-nadia-palier-5-qlm-items-et-a229.md` 2 et 3, A.250, A.253 | - |
| 5 | Un developpeur designe, distinct de `lea-dev`, et le declencheur dans `planning.md` | **reste** | `sofia-sm` |
| 6 | Le premier jet de `-01` commite seul | **reste** ; il suppose le prealable 5 | le developpeur designe |
| 7 | Mon arbitrage prealable de l'instance | **ecrit ici** ; la lettre de l'instance et le palier restent a choisir | l'utilisatrice, par `sofia-sm` (A.255) ; `camille-po` compose |

**Ce qui n'est pas un de mes prealables et que je nomme pour qu'on ne le croie pas couvert** : la forme des gardes (`theo-archi`,
A.255). Une garde absente s'ecrit *"non garde"* et je lis ; elle ne bloque pas l'ouverture.

---

## 8. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | reporter la sonde remplacee de `-12` c2 et lever le *[suppose]* de `-05` c2, par annotation datee ; composer le palier sous `K1` a `K5` apres le choix de l'utilisatrice |
| `sofia-sm` | designer le developpeur, distinct de `lea-dev` ; `planning.md` : le declencheur de `P0` |
| `theo-archi` | la forme des gardes, dont la clause *doit passer* de `-12` c2 |
| l'utilisatrice, par `sofia-sm` | le palier et l'instance ; la marge et la valeur du relevement ; `-07` si elle la veut parmi les neuf |

## 9. Ce que ce document ne fait pas

Il ne prononce aucun Done, n'ecrit aucune condition de Done de ligne, n'alloue aucun identifiant, ne choisit ni le palier ni la lettre
de l'instance, ne fixe ni marge ni relevement, ne modifie ni `DEC`, ni matrice, ni registre, ni backlog, ni paquet, ni code.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-25.
