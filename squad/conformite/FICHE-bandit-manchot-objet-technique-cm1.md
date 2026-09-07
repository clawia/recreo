# Fiche de conformite - bandit-manchot-objet-technique-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-objet-technique.yaml`.
- **Entree de catalogue** : `bandit-manchot-objet-technique-cm1`, titre servi *"Comment ca marche ?"*,
  consigne servie *"Lis bien, puis dis a quoi sert l'objet, ou nomme sa piece."*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, instance B, **au second audit**, apres avoir joue la reprise `a682cb52`).
  **`SCI-CM1-12` ferme** : `non couvert` -> **`couvert-valide`**. Deux reserves neuves et datees,
  `OBT-R1` et `OBT-R2`, aucune bloquante.
- **Histoire des verdicts, aucun efface** : **2026-09-06, REFUSE** - les quatre items de fonction
  reproduisaient `OBJ-R2` et demandaient moins que le niveau du CP ; le bloc entier de ce refus,
  avec son tableau des quatre items, reste lisible ci-dessous. **2026-09-06, VALIDE SOUS RESERVE**
  apres reprise, second bloc en fin de fiche. **Un refus efface n'a aucune valeur de preuve.**
- **Ne pas confondre avec `FICHE-bandit-manchot-objet-technique-cp.md`**, qui porte sur `QLM-CP-07`,
  une autre ligne, un autre niveau, un autre paquet. Les deux fiches se citent l'une l'autre.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  section 2.2 ligne 12 - dont la colonne *conditions particulieres* porte, mot pour mot,
  **"Attention a `OBJ-R2`"**.
- **Commits audites** : `5824e752` (`[LEA] s30: S30-2 - second lot de sciences CM1, melanges et
  objet technique`) le 2026-09-06, puis `a682cb52` (`[LEA] s30: SCI-CM1-12 - la tache A de
  objet-technique-cm1 cesse de reproduire OBJ-R2`) le meme jour, sur l'arbre assemble `a91070f8`.
- **Derniere mise a jour** : 2026-09-07 (reaudit de la reprise `S31-6`, section finale).
- **Reaudit du 2026-09-07** (`nadia-prof`, instance A, s31) : **`OBT-R1` et `OBT-R2` sont levees**,
  aucune reserve neuve. `SCI-CM1-12` reste **`couvert-valide`**, statut inchange. **Aucun verdict
  anterieur n'est efface** : le REFUSE du 2026-09-06 et sa reprise restent lisibles tels quels.

## Competence visee

`Identifier la fonction d'un objet technique, nommer ses composants et décrire son fonctionnement.`
(`SCI-CM1-12`, sciences et technologie, CM1, *Materiaux et objets techniques*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `dbbfe129`, jar reconstruit depuis mon propre worktree et lance
sur le port 8081, **partie complete de dix manches par appels HTTP reels, avec une erreur volontaire
sur chacune des dix manches**, ecran de fin atteint, score 10. **Et j'ai lu les onze items.** **J'ai
aussi relu le paquet de CP de la meme famille** (`cp-qlm-objet-technique.yaml`), item par item, ce
que `CYC-1` m'impose et ce que le motif du refus exige. **Ce n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.

### Ce qui a ete verifie, et ce qui tient

- **Exactitude du contenu** : **aucune erreur de fait.** Chaine, guidon, pile, interrupteur,
  ampoule, lame, pales, moteur : tout est exact et correctement apparie. **Cette partie du travail
  n'est pas a refaire.**
- **L'item 11 est le meilleur item des quatre paquets de sciences de s30**, et je le dis parce
  qu'une equipe doit savoir ce qu'elle a reussi. *"Comment fonctionne une lampe de poche quand on
  appuie sur l'interrupteur ?"*, bonne reponse *"la pile envoie du courant a l'ampoule, qui
  s'allume"*, distracteur *"l'ampoule cree sa propre energie toute seule"* : **c'est une vraie
  conception fausse d'enfant**, et l'indice la tue sans nommer la reponse. C'est exactement ce que
  `SCI-1` demande, et c'est le seul endroit du lot ou je l'ai trouve pleinement realise.
- **La tache B (items 5 a 10) est bonne** : six pieces reelles, distracteurs pris **sur le meme
  objet**, indices qui decrivent la fonction de la piece sans la nommer. **Elle n'est pas concernee
  par ce refus.**
- **Lisibilite, charge cognitive, traitement de l'erreur** : conformes. Un objet, une piece, une
  question. L'indice s'affiche des la premiere erreur, la revelation debloque.
- **Biais et ton** : aucune personne, aucun foyer, aucun stereotype.
- **`SCI-3`** : **tenue, mesuree sur le lot** - zero item numerique sur les 22 du lot 2.
- **`ADR-0081` clause 5** : le code `SCI-CM1-12` est alloue, declare, et le libelle du descripteur
  est copie conforme. **Ce n'est pas ce qui bloque.**
- **`CYC-1`** : **aucune contradiction** avec `cp-qlm-objet-technique.yaml`. Le CP sert la selle, le
  guidon et la pedale du velo ; ce paquet ajoute la chaine et le mecanisme. Sur ce point, la
  declaration du paquet est exacte. **Mais l'absence de contradiction n'est pas la presence d'une
  progression**, et c'est la seconde moitie du motif ci-dessous.

### Motif du refus - les quatre items de fonction reproduisent, un jour apres, le defaut que j'ai ecrit sous le nom `OBJ-R2`

**Ce que j'ai ecrit le 2026-09-06 sur `cm1-objet-technique-cp`** (`FICHE-bandit-manchot-objet-technique-cp.md`,
section `OBJ-R2`), mot pour mot :

> *"Sur les cinq items de la tache *fonction*, **les deux distracteurs sont toujours les fonctions
> des autres objets du paquet** : *"A quoi sert un stylo ? - a ecrire / a couper / a balayer."* Un
> enfant qui ne saurait pas ce qu'est un stylo eliminerait quand meme les deux autres, parce
> qu'aucune n'a le moindre rapport avec l'objet nomme."*

**Ce que ce paquet sert, verifie sur les quatre items et joue :**

| Item | Question | Les deux distracteurs |
|---|---|---|
| 1 | *"A quoi sert un velo ?"* | *"a eclairer dans le noir"*, *"a tailler un crayon"* - fonctions de la lampe et du taille-crayon |
| 2 | *"A quoi sert une lampe de poche ?"* | *"a se deplacer"*, *"a brasser l'air"* - fonctions du velo et du ventilateur |
| 3 | *"A quoi sert un taille-crayon ?"* | *"a eclairer dans le noir"*, *"a se deplacer"* - fonctions de la lampe et du velo |
| 4 | *"A quoi sert un ventilateur ?"* | *"a tailler un crayon"*, *"a eclairer dans le noir"* - fonctions du taille-crayon et de la lampe |

**Quatre items sur quatre, la meme regle de fabrication.** Un enfant qui ignore totalement ce qu'est
un ventilateur elimine *"tailler un crayon"* et *"eclairer dans le noir"* sans rien savoir de
l'objet nomme : aucune des deux n'a le moindre rapport avec lui. **La tache A ne demande aucune
connaissance.**

**Ce refus ne deplace aucun poteau.** `OBJ-R2` a ete ecrite le **2026-09-06**, la veille de ce lot,
sur le paquet jumeau de CP ; mon arbitrage prealable de s30 porte, en face de la ligne 12,
**"Attention a `OBJ-R2`"** ; et le regime de reprise inscrit au backlog pour `OBJ-R2` est *"rejeu
borne (0,5)"*. La condition etait ecrite, datee, nommee dans la ligne meme que ce paquet sert, et
publiee avant que le lot existe.

**Et le paquet est en dessous du niveau du CP sur cette tache, pas au-dessus.** Au CP, `OBJ-R2`
portait sur *parapluie, ciseaux, stylo, balai, brosse a dents*. Ici, au CM1, deux ans plus tard :
*velo, lampe de poche, taille-crayon, ventilateur*. **Meme tache, meme fabrication, objets aussi
familiers.** La ligne de CM1 ne demande pas *"a quoi sert un velo"* : elle demande d'identifier **la
fonction d'un objet technique**, ce qui suppose que la fonction ne soit pas evidente au premier
regard.

**L'item 6 est un item de CP re-servi.** *"Dans un velo, quelle piece sert a diriger la roue avant ?
- le guidon"* contre le CP : *"Quelle partie du velo sert a tourner ? - le guidon"*. Meme objet,
meme piece, meme reponse, deux annees d'ecart. `CYC-1` n'est pas violee - il n'y a pas de
contradiction - mais **ce n'est pas une progression non plus**, et le commentaire du paquet
revendique une progression que cet item ne fait pas.

### L'arithmetique, qui est ce qui empeche la ligne de fermer

Les quatre items de la tache A ne demandent pas la competence de la ligne. Ils ne comptent donc pas
parmi les items qui la servent.

| Condition | Compte reel | Verdict |
|---|---|---|
| `FER-2` - au moins **10** items servent la ligne | 6 (tache B) + 1 (item 11) = **7** | **echec** |
| `FER-1` - *"la fonction"* en position de bonne reponse | portee par la seule tache A | **echec par la meme cause** |
| `FER-3` - deux taches, la moins servie a **3** items | tache A : 4 items, mais aucun ne demande la competence | **echec** |

**La ligne ne ferme pas.**

### Le jugement que `lea-dev` me laisse explicitement - l'item 11 et `FER-3`

Le commentaire du paquet pose la question et ne la tranche pas : l'item 11, dedie au
**fonctionnement**, n'entre dans aucune des deux taches de `FER-3`. **`lea-dev` a eu raison de me la
laisser, et voici ma reponse, qui lui donne raison sur le fond :**

> **`FER-1` et `FER-3` sont deux conditions independantes et ne se comptent pas ensemble.** `FER-1`
> compte des **elements du libelle** en position de bonne reponse ; `FER-3` compte des **taches
> mentales**. Un item peut servir `FER-1` sans appartenir a l'une des deux taches nommees par
> `FER-3`, du moment que `FER-3` est satisfaite par ailleurs. **L'item 11 est donc legitime, et il
> est meme obligatoire** : *"le fonctionnement"* est le troisieme element du libelle et rien d'autre
> ne le porte. Ma table de l'arbitrage prealable donnait un couple de taches en precisant que c'etait
> *"un exemple qui ferme, pas une prescription"* ; un troisieme item hors du couple ne contrevient a
> rien.

**Cette reponse ne sauve pas le paquet**, parce que ce qui manque n'est pas l'item 11 : c'est la
tache A. Mais elle vaut pour la reprise, et pour tous les paquets de sciences a venir : **ne
supprimez pas l'item 11, il est ce qui rend la ligne complete.**

### Ce qu'il faut corriger pour que je reprononce - propriete a tenir, formulations a `lea-dev`

> **Sur les quatre items de fonction, au moins deux portent un distracteur qui est une fonction
> **plausible pour l'objet nomme**, et non la fonction d'un autre objet du paquet.** C'est mot pour
> mot le critere que j'ai ecrit dans `OBJ-R2` le 2026-09-06 ; je ne fournis pas les textes parce que
> le choix d'une fonction plausible mais fausse est precisement le jugement qu'il faut faire item par
> item, et que je le rejouerai.
>
> **Et, pour cette ligne-ci qui est de cycle 3 : au moins deux des quatre items portent sur un objet
> dont la fonction n'est pas evidente pour un enfant de neuf ans**, ou sur une fonction que l'objet
> remplit sans qu'on y pense. *"A quoi sert un velo"* n'apprend rien a un CM1 - il le savait a
> quatre ans.
>
> **L'item 6 (le guidon du velo) est deja servi a l'identique au CP.** Il ne contredit rien et peut
> rester ; mais il ne peut pas etre l'un des items sur lesquels la progression du CM1 repose. **La
> chaine (item 5) est le bon modele** : elle relie deux pieces et demande de comprendre une
> transmission, ce que le CP ne fait pas.

**La tache B (items 5 a 10) et l'item 11 ne sont pas concernes par ce refus et ne sont pas a
refaire.**

### Un point que je nomme sans en faire une condition du refus

**Le mot *"piece"* designe deux choses differentes dans le meme jeu.** La consigne servie dit *"nomme
sa piece"* - un composant. L'indice de l'item 4 dit *"Il deplace l'air **d'une piece** sans jamais le
refroidir vraiment"* - une salle. Un enfant de CM1 qui vient de lire six fois *"quelle piece"* au
sens de composant lit ici un troisieme sens sans transition. **C'est du fond, donc de mon ressort**,
et non de l'orthographe. **A corriger dans le meme geste que le refus, sans rejeu supplementaire** :
je le verifierai dans le reaudit deja paye, il ne cree pas de dette. Le contenu de l'indice, lui,
est exact et bon : un ventilateur ne refroidit pas l'air, il le brasse, et c'est une conception
fausse repandue qu'il est heureux de corriger.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. Et pour memoire, mon arbitrage (section 2.4) n'exigeait
aucune mention sur `SCI-CM1-12`.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS RESERVE`.
**Un refus n'est pas un Done.**

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque piece et de chaque fonction** | **aucun** |
| **`OBJ-R2`** - qu'un distracteur soit plausible **pour l'objet nomme** | **aucun.** C'est un jugement sur le rapport entre un objet et une fonction, et c'est le meme defaut qui a traverse deux paquets et deux sprints sans qu'aucun test le voie |
| **Le niveau reel d'un item** - qu'un item de CM1 demande plus qu'un item de CP | **aucun.** Aucun test ne compare la difficulte de deux paquets de deux niveaux |
| **`CYC-1`** et la redite d'un item de cycle 2 | **aucun.** Elle compare deux fichiers que rien ne relie. Une **redite** est pourtant textuelle : deux items de deux paquets portant la meme bonne reponse sur le meme objet. **Route a `theo-archi`** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Refus sur `OBJ-R2` reproduite et sur le niveau de la tache A, quatre items nommes (1 a 4)** | `camille-po`, via `sofia-sm` | En faire du backlog. **Le paquet n'est pas a jeter** : sept items sur onze tiennent, dont le meilleur item de sciences du sprint |
| **`SCI-CM1-12` reste `non couvert`** | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **`OBJ-R2` a produit le meme defaut deux fois en deux jours, sur deux niveaux** | `camille-po`, `sofia-sm` | Ce n'est plus une reserve de paquet, c'est un reflexe de fabrication. A traiter comme tel dans la reprise, pas item par item |
| **La redite d'un item de cycle 2 dans un paquet de cycle 3 est une propriete textuelle** | `theo-archi` | Je dis la propriete, jamais la forme |
| **Le cout de mon reaudit** | `sofia-sm`, `camille-po` | **Rejeu borne, 0,5.** Ma marge de s30 est de **0,5 pour une instance** : je peux reauditer **une** des deux corrections de sciences dans ce sprint, pas les deux |

---

# Done pedagogique - 2026-09-06, second audit apres reprise (s30, instance B) - VALIDE SOUS RESERVE

**Ce bloc s'ajoute. Il n'efface rien.** Le refus du 2026-09-06 reste lisible en entier au-dessus,
avec son tableau des quatre items et son arithmetique. **Un refus efface n'a aucune valeur de
preuve.**

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**
- **Commit audite** : `a682cb52` (`[LEA] s30: SCI-CM1-12 - la tache A de objet-technique-cm1 cesse
  de reproduire OBJ-R2`), sur l'arbre assemble `a91070f8`.
- **`SCI-CM1-12` ferme** : `non couvert` -> **`couvert-valide`**. Deux reserves neuves et datees,
  `OBT-R1` et `OBT-R2`, aucune bloquante.
- **Le motif du refus du 2026-09-06 est leve**, sur les proprietes exactes que j'avais ecrites.

## 1. Conditions du second audit

**J'ai joue.** Arbre `round-two` a `a91070f8`, worktree dedie, jar reconstruit depuis ce worktree,
port 8081. **Trois parties completes de dix manches par appels HTTP reels**, avec une puis deux
erreurs volontaires par manche - trois parties parce que la tache A ne compte que quatre items sur
onze et que je voulais voir **les quatre a l'ecran**, ce que j'ai obtenu. **Et j'ai relu les onze
items.**

## 2. Les trois proprietes que j'avais ecrites, verifiees une par une

| Propriete a tenir, ecrite le 2026-09-06 | Etat verifie le 2026-09-06 |
|---|---|
| *"Sur les quatre items de fonction, **au moins deux** portent un distracteur qui est une fonction **plausible pour l'objet nomme**, et non la fonction d'un autre objet du paquet."* | **tenue, et depassee : les quatre le font.** Le velo compare a la marche et a la voiture ; la lampe qui rechaufferait les mains ou rechargerait un telephone ; le taille-crayon qui effacerait ou rangerait ; le ventilateur qui refroidirait ou purifierait. **Aucun distracteur n'est plus emprunte a un autre objet du paquet** |
| *"**au moins deux** des quatre items portent sur un objet dont la fonction n'est pas evidente pour un enfant de neuf ans, ou sur une fonction que l'objet remplit sans qu'on y pense"* | **tenue.** Item 1 : *"A quoi sert un velo, **plutot qu'a marcher**, pour un long trajet ?"* - c'est un comparatif, pas une devinette. Item 4 : *"A quoi sert un ventilateur, **alors qu'il donne une impression de fraicheur** ?"* - c'est la conception fausse la plus repandue sur cet objet, et la bonne reponse la nomme |
| *"L'item 6 (le guidon du velo) [...] **peut rester** ; mais il ne peut pas etre l'un des items sur lesquels la progression du CM1 repose."* | **tenue, et je reponds a la question qui m'est posee : l'item 6 n'est plus en cause.** Voir section 3 |

**Les trois sont atteintes. Je leve le motif du refus.** `OBJ-R2`, qui etait la moitie de ce motif,
n'est plus reproduite sur ce paquet.

## 3. L'item 6 n'est plus en cause, et voici sur quoi je le fonde

**La question m'est posee directement, et c'est la bonne question a poser.** L'item 6 - *"Dans un
velo, quelle piece sert a diriger la roue avant ? - le guidon"* - est inchange, et il reste ce qu'il
etait : le meme objet, la meme piece et la meme reponse qu'au CP (*"Quelle partie du velo sert a
tourner ?"*, `cp-qlm-objet-technique.yaml`).

**Ce que j'avais ecrit n'etait pas *"il est faux"*, c'etait *"il ne peut pas etre l'un des items sur
lesquels la progression du CM1 repose"*.** C'etait une condition **sur le paquet**, pas sur l'item.
Elle est desormais satisfaite, et je le verifie plutot que je ne le suppose : **quatre items portent
la progression de cycle 3 sans lui** -

- **item 1**, le velo compare a la marche : un raisonnement comparatif, absent du CP ;
- **item 4**, le ventilateur qui ne refroidit pas : une conception fausse a defaire, absente du CP ;
- **item 5**, la chaine qui relie les pedales a la roue arriere : une **transmission**, c'est-a-dire
  une relation entre deux pieces, la ou le CP ne nomme que des parties isolees ;
- **item 11**, le fonctionnement de la lampe de poche : une chaine causale entiere.

**L'item 6 est desormais porte par le paquet, il ne le porte plus.** C'est exactement la situation
que ma formulation autorisait, et je ne la durcis pas apres coup. **Il reste, et il est bon qu'il
reste** : une piece nommee au CP et reprise au CM1 dans un paquet ou la chaine et le mecanisme
l'entourent n'est pas une redite, c'est un point d'appui.

## 4. Le compte, refait

| Condition | Compte reel | Verdict |
|---|---|---|
| `FER-1` - trois elements en position de bonne reponse | *"la fonction"* par les 4 items de la tache A, **qui demandent desormais la competence** ; *"un composant"* par les 6 items de la tache B ; *"le fonctionnement"* par l'item 11 | **tenue** |
| `FER-2` - au moins **10** items servent la ligne | **11** | **tenue** |
| `FER-3` - deux taches, la moins servie a **3** items | 4 et 6 | **tenue** |

**Et le test qui avait fait tomber le paquet, reapplique** : *un enfant qui ignore totalement l'objet
nomme gagne-t-il l'item ?* **Non, sur les quatre.** Pour ecarter *"a rechauffer les mains"* il faut
savoir ce que fait une lampe de poche ; pour ecarter *"a refroidir reellement l'air"* il faut savoir
ce que fait un ventilateur - et c'est precisement ce que la plupart des enfants **ne** savent pas.

## 5. `OBT-R1` - l'indice de l'item du velo dement la bonne reponse de son propre item

**Ouverte le 2026-09-06, non bloquante.**

**Je commence par corriger ma propre premiere lecture, avant de m'en servir.** En lisant le fichier,
j'ai d'abord pris cet indice pour une **erreur de fait**, et je m'appretais a l'instruire comme
telle. **Verification faite, il n'en est pas une**, et je le dis parce qu'un motif faux est pire
qu'un motif absent.

Item 1 : *"A quoi sert un velo, plutot qu'a marcher, pour un long trajet ?"* - bonne reponse **"a se
deplacer plus vite et avec moins d'effort"**, indice **"En pedalant, on avance plus vite qu'a pied,
pour une fatigue proche."**

**Les deux enonces sont vrais, mais dans deux cadres differents, et le paquet les met cote a cote :**

- **a distance egale** - le cadre que l'enonce fixe lui-meme avec *"pour un long trajet"* - le velo
  demande **moins** d'effort que la marche. **C'est la bonne reponse, et elle est juste.**
- **a effort egal**, le velo va plus loin et plus vite pour une fatigue comparable. **C'est
  l'indice, et il est juste aussi.**

**Le defaut n'est donc pas une erreur, c'est une incoherence** : l'enfant qui se trompe une fois lit
*"pour une fatigue proche"* et en conclut raisonnablement que *"avec moins d'effort"* n'est pas la
reponse. **L'indice pousse hors de la bonne reponse**, et il rapproche le distracteur *"a se
deplacer sans aucun effort"*. C'est le seul indice du depot que j'aie vu argumenter contre la reponse
de son propre item.

> **Propriete a tenir** : l'indice de cet item se place dans **le meme cadre que l'enonce** - le
> trajet, donc la distance - et n'introduit pas la comparaison a effort egal, qui est une autre
> facon vraie de dire la meme chose et qui, ici, dement la bonne reponse. La formulation est a
> `lea-dev`.

**Pourquoi ce n'est pas bloquant** : l'enonce est juste, la bonne reponse est juste, et l'indice ne
s'affiche qu'apres une erreur. **J'ai envisage le refus et je ne le prononce pas**, pour la meme
raison exactement que sur `MEL-R1` le meme jour : le defaut est dans un indice, pas dans ce que
l'enfant apprend comme vrai quand il repond juste. **Si je refusais ici et pas la, ma porte
dependrait de mon humeur.**

## 6. `OBT-R2` - deux indices neufs eliminent les deux distracteurs a la fois, donc revelent la reponse

**Ouverte le 2026-09-06, non bloquante. Defaut **introduit par la reprise**, et c'est ce que le
rejeu existe pour trouver.**

En rendant les distracteurs plausibles - ce que j'avais demande - deux indices ont pris l'habitude de
les nommer pour les exclure :

- item 2 : *"On l'allume quand il n'y a pas assez de lumiere, **jamais pour se rechauffer ni
  recharger un appareil**."*
- item 3 : *"On l'utilise quand la pointe d'un crayon est devenue trop usee, **jamais pour effacer ni
  pour ranger**."*

**Sur un item a trois propositions, ecarter nommement les deux distracteurs ne laisse que la bonne.**
C'est une revelation deguisee, que `M21` refuse, et elle tombe **des la premiere erreur** - deux
tentatives avant l'ecran de revelation, qui est le moment ou le produit a decide de donner la
reponse.

**L'item 4 montre la bonne mesure et sert de modele** : *"Il deplace l'air d'une salle ; il ne le rend
jamais plus froid."* Il tue **une** conception fausse, laisse *"purifier l'air"* debout, et l'enfant
choisit encore.

> **Propriete a tenir** : un indice ecarte **au plus un** distracteur nomme. Il donne le critere ou
> tue la conception fausse ; il ne fait pas le tour de la liste. Les formulations sont a `lea-dev`.

## 7. Ce que la reprise corrige et que je n'ai pas eu a redemander

**Le double sens du mot *"piece"* est reglé.** L'indice de l'item 4 disait *"l'air d'une piece"* -
une salle - juste apres six items ou *"quelle piece"* designe un composant, et apres une consigne
qui dit *"nomme sa piece"*. Il dit desormais **"salle"**, aux trois endroits de l'item. **C'etait la
correction a cout nul que j'avais nommee dans mon refus ; elle est faite, et je la verifie ici dans
le rejeu deja paye, comme annonce.**

## 8. Mentions de couverture partielle

**Aucune n'est due sur cette ligne.** Mon arbitrage prealable (section 2.4) enumere les trois lignes
de sciences qui en exigent une - 6, 14 et 20 - et `SCI-CM1-12` n'en fait pas partie. Les trois
elements du libelle sont servis en position de bonne reponse. **La ligne ferme sans reserve de
perimetre.**

## 9. Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

## 10. Ce dont je reste la seule garde, mis a jour au second audit

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque piece, de chaque fonction et des deux comparatifs neufs** | **aucun.** Que le velo demande moins d'effort que la marche a distance egale, et qu'un ventilateur ne refroidisse pas l'air, c'est moi qui le dis |
| **`OBJ-R2`** - qu'un distracteur soit plausible **pour l'objet nomme** | **aucun**, toujours. La reprise n'a ajoute aucun garde-fou sur cette propriete : elle a corrige le contenu et mis a jour un commentaire d'exclusion dans un test existant. **Je le dis pour que l'equipe ne croie pas couvert ce qui ne l'est pas** |
| **`OBT-R1`** - qu'un indice ne dise pas le contraire de sa bonne reponse | **aucun** |
| **`OBT-R2`** - qu'un indice n'ecarte pas tous les distracteurs a la fois | **aucun**, et c'est un defaut que la correction d'un autre defaut a fabrique. C'est le motif meme du rejeu |
| **Le niveau reel d'un item** | **aucun.** Aucun test ne compare la difficulte de deux paquets de deux niveaux |

## 11. Etat des verdicts et des reserves de ce jeu au 2026-09-06

- **Verdict du 2026-09-06, REFUSE** : **non efface**, motif et tableau lisibles au-dessus. **Les
  proprietes qu'il exigeait sont atteintes et son motif est leve.**
- **Verdict du 2026-09-06, second audit : VALIDE SOUS RESERVE.** `SCI-CM1-12` ferme.
- **`OBT-R1`** et **`OBT-R2`** : **ouvertes le 2026-09-06**, non bloquantes, motifs et proprietes en
  sections 5 et 6. **Regime commun : rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). **Aucune
  ne ferme de ligne.**

---

## Reaudit de la reprise `S31-6` - 2026-09-07, `nadia-prof` instance A

- **Verdict de la reprise** : **VALIDE SOUS RESERVE** - **`OBT-R1` et `OBT-R2` sont levees.** Aucune
  reserve neuve.
- **Statut de la ligne** : `SCI-CM1-12` reste **`couvert-valide`**. Cette reprise ne rouvre rien.
- **Commit audite** : `548121d9` (`[LEA] s31: S31-6 - l'indice du velo ne dement plus sa propre
  bonne reponse`).
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree `s31-nadia-A` a `3181b5e6`, port
  8082. **Une partie complete de dix manches avec erreurs volontaires**, puis **trois parties
  supplementaires forcees jusqu'a voir l'item du velo a l'ecran avec son indice**, parce que le
  tirage ne me l'avait pas servi la premiere fois. Les trois items vises par les deux reserves ont
  ete lus a l'ecran apres erreur. **Audit degrade sur un point** : lecture du HTML servi, pas de la
  page rendue ; aucun jugement visuel n'entre dans ce reaudit.
- **Histoire des verdicts, aucun efface** : le REFUSE du 2026-09-06 et le VALIDE SOUS RESERVE qui
  l'a suivi restent lisibles au-dessus, tels quels.

### `OBT-R1` - **levee**

**La propriete que j'avais ecrite** : *"l'indice de cet item se place dans le meme cadre que
l'enonce - le trajet, donc la distance - et n'introduit pas la comparaison a effort egal, qui est
une autre facon vraie de dire la meme chose et qui, ici, dement la bonne reponse."*

**Ce qui est servi, lu a l'ecran** : *"Pour un meme trajet, pedaler fatigue moins que marcher a
pied."*

- Le cadre est celui de l'enonce, nomme explicitement (*"pour un meme trajet"*) : **oui**.
- La comparaison a effort egal a disparu : **oui**.
- L'indice ne dement plus la bonne reponse de son propre item : **oui**, et il la soutient.
- Le fait est exact : a distance egale, le velo coute moins d'effort que la marche. **Oui.**

**C'etait le seul indice du depot que j'avais vu argumenter contre la reponse de son propre item ; il
ne le fait plus.**

### `OBT-R2` - **levee**, et je confirme ma barre plutot que celle du backlog

**La propriete que j'avais ecrite, mot pour mot** : *"un indice ecarte **au plus un** distracteur
nomme. Il donne le critere ou tue la conception fausse ; il ne fait pas le tour de la liste."*

**Ce qui est servi, lu a l'ecran** :

- item 2 : *"On l'allume quand il n'y a pas assez de lumiere ; elle ne sert jamais a rechauffer les
  mains."* - **un** distracteur nomme.
- item 3 : *"On l'utilise quand la pointe d'un crayon est devenue trop usee ; il ne sert jamais a
  effacer ce qu'on a ecrit."* - **un** distracteur nomme.

**Tenue sur les deux items.**

#### Le point que `yanis-qa` a remonte, et ma reponse

`yanis-qa` a trouve un ecart entre ma fiche et le critere de la story `S31-6` : le backlog exige
qu'un indice *"ne nomme **aucun** des distracteurs de son item, plausibles ou non"*, la ou cette
fiche dit *"au plus un"*. Le contenu livre suit la fiche.

**C'est bien ma barre, et c'est *"au plus un"*.** Trois raisons, et la troisieme est decisive :

1. **La fiche est la source, le backlog en est une derivation.** `OBT-R2` a ete ecrite ici le
   2026-09-06 ; la story la reprend. Quand les deux different, c'est la reformulation qui a derive.
2. **Le motif de la reserve etait la revelation, pas la mention.** Ce que je reprochais est
   *"ecarter nommement les **deux** distracteurs ne laisse que la bonne"*. Un seul distracteur
   ecarte laisse encore un choix, ce qui est tout ce que je demandais.
3. **Et surtout : *"aucun"* aurait disqualifie mon propre modele.** J'avais ecrit, dans la meme
   reserve, que *"l'item 4 montre la bonne mesure et sert de modele"*, en citant *"Il deplace l'air
   d'une salle ; il ne le rend jamais plus froid."* - qui **nomme** le distracteur *"a refroidir
   reellement l'air d'une salle"* pour l'ecarter, et laisse *"purifier l'air"* debout. **Une barre
   qui refuse l'exemple que j'ai moi-meme donne comme modele n'est pas ma barre.**

**Je confirme donc *"au plus un"*, et je route la correction du critere de story plutot que de la
laisser vivre** : un critere plus strict que la reserve dont il derive fait porter au developpeur
une exigence que personne n'a arbitree.

**Merci a `yanis-qa` de l'avoir remonte plutot que de l'avoir tranche.** C'est exactement la forme
de derive - une reformulation qui durcit en recopiant - que ce depot a deja sanctionnee plusieurs
fois, et elle a ete prise avant de couter quoi que ce soit.

### Un point que je ne transforme pas en reserve, et qui est de mon fait

L'indice corrige du velo, *"pedaler fatigue moins que marcher a pied"*, **dit en d'autres mots la
moitie de sa bonne reponse** (*"avec moins d'effort"*), ce qui est le voisinage de `M21`.

**Je ne l'ouvre pas en reserve, et le motif est que c'est ma propre propriete qui l'a produit.**
J'avais exige que l'indice reste *"dans le meme cadre que l'enonce - le trajet, donc la distance"* ;
dans ce cadre, le seul enonce vrai disponible **est** que le velo coute moins d'effort. **Je ne peux
pas commander une contrainte et reprocher son unique solution.**

Et le fait scolaire tient : l'indice distingue *"fatigue moins"* de *"sans aucun effort"*, qui est
precisement le distracteur restant. **Il aide, il ne revele pas.**

**Je l'ecris ici plutot que nulle part** : si une reserve doit naitre un jour sur ce point, elle
naitra datee, comme reserve neuve, et pas comme un durcissement de `OBT-R1`.

### Ce que ce reaudit ne fait pas

- Il ne rouvre pas `SCI-CM1-12`, qui reste `couvert-valide`.
- Il n'efface aucun verdict : le REFUSE du 2026-09-06, sa reprise et les deux reserves restent
  lisibles au-dessus, avec la date de leur levee ici.
- Il ne touche pas a la matrice.

### Ce que je route apres ce reaudit

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`OBT-R1` et `OBT-R2` levees** | `camille-po` | Elles sortent des dix-sept reserves ; le tri du 2026-09-07 les comptait deja comme soldees par le groupe 1 |
| **Le critere de `S31-6` dit *"aucun distracteur nomme"* la ou `OBT-R2` dit *"au plus un"*** | `camille-po`, via `sofia-sm` | Corriger le critere de story pour qu'il cite la reserve, pas une version durcie d'elle. **Rien a refaire dans le contenu** : le contenu suit la fiche, qui fait foi |
| **Aucun garde-fou n'existe sur `OBT-R1` ni `OBT-R2`** | `theo-archi` | Constat du 2026-09-06 **maintenu** : les deux corrections ont ete faites a la main, et rien n'empeche leur reapparition |
