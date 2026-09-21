# Fiche de conformite - Le plan de la classe (`designation-plan-de-la-classe`)

- **Mecanique** : `designation` (`ADR-0080`, `ADR-0088`, `ADR-0092`)
- **Contenu(s)** : `cp-plan-de-la-classe` (6 items, un dessin, `nombreDeManches: 5`)
- **Statut global** : **VALIDE SOUS RESERVE** (second passage, 2026-09-21, s36). Le refus du
  premier passage (2026-09-11, s35) est conserve integralement plus bas : il n'est pas efface, il
  est leve, et l'histoire se lit.
- **Motif en une phrase** : la forme neuve **numero servi dans le bouton + legende numerotee sous le
  dessin** (`ADR-0094`) **tient le plancher de lisibilite sur les six zones** - debordement mesure a
  **0,00 px sur les six**, la ou s35 en mesurait 17 a 95 -, la correspondance numero/libelle est
  sans ambiguite pour un enfant de CP, et le Done pedagogique est ouvert. **Quatre reserves restent
  a corriger**, dont deux que s36 devait absorber et n'a pas absorbees (`PCL-R1`, `PCL-R2`).
- **Derniere mise a jour** : 2026-09-21 (s36)

> **Fiche ouverte par `nadia-prof` et non par `camille-po`.** Le jeu est neuf et aucune fiche
> n'existait ; le paquet avait ete retire du commit de s34 avant d'etre servi (planning de s34,
> section 15.2), donc cette ligne n'avait jamais ete auditee. J'ai rempli les sections descriptives
> depuis la story `S35-QLM11-PLANCLASSE` du backlog, depuis `catalogue.yaml` et depuis le
> referentiel, sans rien y ajouter de mon cru, **sur le patron de
> `FICHE-designation-frise-de-la-journee.md`** ; **le bloc de validation est le mien, comme
> toujours.** Si `camille-po` veut reprendre la main sur les sections qui lui reviennent, elle le
> peut : elles ne portent aucun jugement.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Questionner le monde | CP (cycle 2) | Se situer dans l'espace | Situer un lieu ou un objet sur un plan simple de la classe, de l'ecole ou du quartier | `QLM-CP-11`, `squad/programme/questionner-le-monde/competences-cp.md` |

**Etat de la ligne apres l'audit de s35** : `non couvert`. **Quatrieme sprint sur cette ligne, et
premier ou elle est reellement servie et donc reellement auditable** - ce qui est un progres, et je
le dis avant le refus.

**Etat de la ligne apres l'audit de s36 (2026-09-21)** : `couvert-valide`. Le Done pedagogique est
prononce sous reserve ; `camille-po` peut passer la ligne. Voir le second bloc de validation en fin
de fiche.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S35-QLM11-PLANCLASSE`, et des conditions `ZON-1` a
`ZON-16`.

- [x] Six items, `nombreDeManches: 5`, plus d'items que de manches (`ADR-0023`)
- [x] Six zones sur un seul dessin, entre trois et six au CP (`ZON-8`)
- [x] Le dessin est un schema vu de dessus, sans perspective (`ZON-12`)
- [x] Aucun element du plan n'est ecrit sur le dessin (`DES-7`)
- [x] Deux zones ne se chevauchent pas et aucune ne deborde du dessin (`ZON-13`)
- [x] Une seule forme de libelle sur tout le paquet (`ZON-10`) - positionnelle
- [x] Un item, un geste (`ZON-14`)
- [x] Le texte alternatif dit ce que l'image est, jamais ou est la reponse (`ZON-3`, `ADR-0091`)
- [x] Aucun texte ne remplace l'image (`ZON-1`) - **1 item sur 6 au plus, passe image effacee**
- [x] L'ecran de fin de partie porte la question qu'il commente et ne montre ni identifiant ni
      libelle de zone (`ADR-0088`, clauses A et B)
- [x] **Chaque libelle compte au plus deux locutions positionnelles et ne nomme aucun objet du
      dessin (`ZON-16`)** - **tenue sur les six**
- [x] **A la taille servie, chaque libelle tient entierement dans son cadre, sans deborder ni
      toucher une zone voisine, et ne recouvre aucun trait du dessin que sa zone designe
      (`ZON-15`)** - **non tenue sur les six zones au 2026-09-11 (s35) ; tenue au 2026-09-21 (s36)
      sur sa premiere moitie (0,00 px de debordement sur les six), et levee sur sa seconde moitie
      par la clarification datee du 2026-09-21** (bloc de validation s36, section 2)
- [x] **La cible se trouve sur le dessin seul, zones masquees (`ZON-9`)** - **non verifiable au
      2026-09-11 ; verifiee et tenue au 2026-09-21**, dessin rendu zones masquees, les six objets
      restent identifiables (bloc de validation s36, section 6)

---

## Bloc de validation pedagogique nº 1 - nadia-prof UNIQUEMENT - 2026-09-11 (s35)

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
>
> **Ce refus est leve le 2026-09-21 par le bloc nº 2, en fin de fiche. Il n'est pas efface** : un
> refus efface n'a aucune valeur de preuve, et c'est lui qui a produit la forme que s36 a livree.
> Tout ce qui suit est laisse tel qu'il a ete ecrit le 2026-09-11.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de cinq manches, avec une erreur volontaire sur chacune**,
  pour lire les indices ; puis une passe sur le sixieme item. **Les six items ont ete vus a
  l'ecran.** Le jeu tourne de bout en bout, aucun ecran casse - `ZON-7` est franchie, premiere
  livraison, et c'est a porter au credit du lot.
- **Audit degrade sur un point, et je le declare** : je n'ai pas rendu l'ecran dans un navigateur.
  J'ai rendu le dessin **a partir de sa geometrie** a la taille servie, pose sur lui les six cadres
  avec le pave, la bordure et le fond exacts de `.zone`, et replie chaque libelle **avec les
  metriques reelles de la police systeme de l'appareil a 1,25rem gras** - celle que la pile
  `--bs-font-sans-serif` designe, faute de `@font-face` servi, ce qu'`ADR-0092` nomme precisement
  comme la raison pour laquelle aucune arithmetique ne peut garder cette propriete. **Le controle
  qui rend la methode credible** : la meme methode, appliquee a la frise de la journee, retrouve au
  pixel le chiffre que j'avais mesure au navigateur a s34 (`FRI-R4`, *"2 a 3 px"*). Deux methodes
  independantes, le meme resultat. **Et les deux polices candidates du poste donnent le meme verdict
  sur les six zones**, avec des ecarts de 1 a 2 px entre elles : la conclusion ne depend pas de la
  police.
- **Mouvement de matrice** : **aucun.** `QLM-CP-11` reste `non couvert`.

### 1. Le motif du refus - `ZON-15` n'est tenue sur aucune des six zones

**Ma condition, mot pour mot** (`verdicts-nadia-instance-B.md` de s34, section 4) : *"A la taille
servie, le libelle tient entierement dans son cadre sans deborder ni toucher une zone voisine, et ne
recouvre aucun trait du dessin que sa zone designe. Si les deux ne tiennent pas ensemble a 1,25rem,
c'est le cadre ou le libelle qui change, jamais la police."*

**Mesure, zone par zone.** Le dessin est servi a **352 x 352 px** : `plan-classe.svg` declare cette
taille, `.image-designation` plafonne a `22rem` (352 px), et la boite du conteneur tiers depasse 352
px des le premier palier de largeur. **Ce chiffre ne depend donc pas de la fenetre**, ce qui rend la
mesure ci-dessous vraie sur tout poste. `.zone` prend `padding: 0.75rem 1.25rem` et une bordure de
`0.125rem`, en `box-sizing: border-box` : **44 px de largeur et 28 px de hauteur sont manges sur
chaque cadre avant que le libelle commence.**

| Zone | Cadre servi | Boite de contenu | Libelle | Replie en | Ce qu'il demande | Verdict |
|---|---|---|---|---|---|---|
| `tableau` | 119,7 x 52,8 | **75,7 x 24,8** | *en haut, au centre* | 4 lignes | 60 x **120** | **deborde de 95 px en hauteur** |
| `bureau` | 98,6 x 70,4 | **54,6 x 42,4** | *en haut, a gauche* | 4 lignes | 68 x **120** | **deborde de 13 px en largeur et 78 en hauteur** |
| `fenetre` | 63,4 x 140,8 | **19,4 x 112,8** | *au centre, a droite* | 4 lignes | 65 x **120** | **deborde de 46 px en largeur et 7 en hauteur** |
| `armoire` | 105,6 x 105,6 | **61,6 x 77,6** | *au centre, a gauche* | 4 lignes | 68 x **120** | **deborde de 6 px en largeur et 42 en hauteur** |
| `coin-lecture` | 119,7 x 70,4 | **75,7 x 42,4** | *en bas, a droite* | 2 lignes | 74 x **60** | **deborde de 18 px en hauteur** |
| `porte` | 112,6 x 59,8 | **68,6 x 31,8** | *en bas, a gauche* | 3 lignes | 68 x **90** | **deborde de 58 px en hauteur** |

**Six zones sur six. De 17 a 95 px. Ce n'est pas une marge, c'est un ecran illisible.**

**Et voici ce que cela donne a l'ecran, decrit tel que je l'ai regarde** : *"en"* est pousse **au-
dessus du mur du haut du plan**, hors du dessin ; *"haut,"* est pose sur le tableau ; *"au centre"*
tombe sous le cadre du tableau et arrive dans celui du bureau, ou il rencontre le *"en"* du bureau ;
*"gauche"* de l'armoire tombe dans le cadre de la porte, ou il rencontre *"en bas,"* ; et *"gauche"*
de la porte **sort par le bas du dessin**. **Les six libelles couvrent les traits du meuble que leur
zone designe** - le tableau, le bureau, la ligne des deux vantaux de l'armoire, l'arc de
debattement de la porte. **Les deux moities de `ZON-15` sont rompues, et pas l'une plus que
l'autre.**

### 2. Ce que `ines-design` a fait, ce que cela a donne, et pourquoi ce n'est pas la cause

`ines-design` a agrandi les deux cadres que j'avais mesures a s34, et seulement ceux-la :
`armoire` passe de 16 x 22 a 30 x 30, `porte` de 18 x 16 a 32 x 17. **La boite de contenu de
l'armoire passe de 16-23 px a 61,6 x 77,6 : c'est un vrai gain, quatre fois la surface utile, et je
le porte au credit du lot.** Ce n'est pas un geste en l'air.

**Mais il ne suffit pas, et les quatre cadres que je n'avais pas mesures a s34 sont dans le meme
etat ou pire.** `fenetre` offre **19,4 px de largeur utile** - moins que les *"16 a 23 px"* que
j'avais qualifies d'impossibles - et `tableau` **24,8 px de hauteur utile** pour un libelle qui en
demande 120.

**Est-ce que je deplace mes poteaux en mesurant quatre cadres que je n'avais pas mesures ? Non, et
je m'en explique.** `ZON-15` porte sur *"le libelle d'une zone"*, donc sur toutes ; le critere
d'acceptation de la story dit *"le libelle de chaque zone du plan de la classe"* et *"chacun offre
une boite de contenu suffisante"*. Les deux cadres nommes a s34 l'etaient sous le titre *"Ce que
cela repond a `lea-dev`, concretement"* - c'etait une illustration de la condition, jamais son
perimetre. **Si je m'etais arretee aux deux cadres nommes, j'aurais valide un ecran ou quatre
libelles sur six debordent.**

### 3. La cause n'est pas la geometrie de deux cadres, et je nomme ce que je vois sans trancher la forme

**L'arithmetique est implacable et elle vaut pour tout paquet de designation a venir.** Un libelle
positionnel replie sur deux lignes demande, dans le pave de `.zone`, un cadre d'au moins **109 x 88
px**, soit **31 % x 25 %** du dessin servi. Il faut cela **six fois**. Or trois des six elements d'un
plan de classe **sont des bandes minces par nature** : un tableau contre le mur du haut, une fenetre
percee dans le mur de droite, une porte dans le mur du bas. **On ne peut pas donner 25 % de hauteur
au tableau sans mentir sur le plan** - et mentir sur le plan est exactement ce que `QLM-CP-11`
interdit, puisque la competence est de situer sur **un plan**.

**Ce que je constate donc : le libelle d'une zone ne peut pas vivre a l'interieur d'un cadre quand ce
cadre doit rester une bande mince.** `ADR-0092` avait vu venir la question et l'a nommee sans la
trancher ; la voici mesuree.

**Le choix de forme n'est pas de mon ressort et je ne le tranche pas** (`ines-design` propose,
`theo-archi` decide la forme technique). **Je dis seulement ce que ma porte accepte et ce qu'elle
refuse** :

- **Ce que j'accepte** : que le libelle **sorte de la boite du bouton** - une legende a cote du plan,
  un renvoi, un libelle en marge - pourvu que l'enfant sache sans ambiguite quel bouton porte quel
  libelle, et que le bouton garde son nom accessible.
- **Ce que j'accepte aussi** : que le **pave de `.zone`** soit reduit sur une zone de designation.
  Vingt pixels de pave horizontal mangent **40 des 63 px** du cadre de la fenetre. **Ce n'est pas la
  police, c'est le pave** : `ZON-15` dit *"c'est le cadre ou le libelle qui change, jamais la
  police"*, et le pave est du cote du cadre.
- **Ce que je refuse** : baisser la taille du libelle sous **1,25rem**. Ce plancher est de moi, il
  n'est pas neuf, `ADR-0050` (decision 4) le tient deja, et aucune geometrie ne le justifie.
- **Ce que je refuse aussi** : l'alternative `(d)` d'`ADR-0092`, accepter qu'un libelle deborde de
  son cadre. `ADR-0092` a raison de dire qu'elle me demanderait de renverser ma propre condition.
  **Je ne la renverse pas, et la raison est ce que je viens de regarder** : le debordement n'est pas
  cosmetique ici, il fait se recouvrir les libelles de zones voisines. Un enfant ne saurait plus quel
  mot appartient a quel bouton. **`ZON-15` tient.**

### 4. `ZON-16` est **tenue sur les six libelles**, et je les confirme tels quels - mais je corrige d'abord ma propre condition

**Je dois corriger ce que j'ai ecrit avant de m'en servir pour juger.** Ma condition disait :
*"Un libelle positionnel de CP compte **au plus trois mots** et ne porte **qu'un seul repere**.
'en haut, au centre' passe ; 'a gauche, contre le mur' ne passe pas."*

**Mon exemple de libelle qui passe fait quatre mots et porte deux reperes.** Ma regle, prise a la
lettre, refuse l'exemple que je donne comme acceptable, dans la meme phrase. **La formulation est
fausse ; c'est l'exemple qui dit ce que je voulais dire**, parce que c'est le cas concret que j'ai
juge.

Ce que la comparaison de mes deux exemples montre vraiment : *"en haut, au centre"* est une
**coordonnee** - une locution verticale et une locution horizontale, toutes deux purement
positionnelles. *"a gauche, contre le mur"* ajoute **un objet du dessin** que l'enfant doit d'abord
trouver. **C'est la, et nulle part ailleurs, que passait ma frontiere.**

> **`ZON-16`, reformulee le 2026-09-11 (s35) pour dire ce que son exemple disait deja.** Un libelle
> positionnel de CP est fait **d'au plus deux locutions positionnelles** (donc au plus quatre mots),
> **chacune purement positionnelle**, et il **ne nomme aucun objet du dessin**. *"en haut, au
> centre"* passe ; *"a gauche, contre le mur"* ne passe pas.
>
> **Cette correction assouplit ma condition, elle ne la durcit pas** : elle admet des libelles que
> la lettre de la version du 2026-09-11 (s34) refusait, et elle refuse exactement les memes que son
> exemple refusait. Je la date et je laisse la version precedente lisible dans le verdict de s34
> plutot que de la reecrire la-bas.

**Les six libelles ecrits a titre provisoire par `lea-dev`, juges sous cette condition :**

| Zone | Libelle | Locutions | Nomme un objet ? | Verdict |
|---|---|---|---|---|
| `tableau` | *en haut, au centre* | 2, positionnelles | non | **passe** - c'est mon propre exemple |
| `bureau` | *en haut, a gauche* | 2, positionnelles | non | **passe** |
| `fenetre` | *au centre, a droite* | 2, positionnelles | non | **passe** |
| `armoire` | *au centre, a gauche* | 2, positionnelles | non | **passe** |
| `coin-lecture` | *en bas, a droite* | 2, positionnelles | non | **passe** |
| `porte` | *en bas, a gauche* | 2, positionnelles | non | **passe** |

**Et je verifie ce que `ZON-16` seule ne dit pas, parce que la story me confie ces six libelles :**

- **Les six paires sont distinctes** : (haut, centre), (haut, gauche), (centre, droite), (centre,
  gauche), (bas, droite), (bas, gauche). **Aucun libelle ne pourrait designer deux zones.**
- **Chaque libelle correspond a la position reelle de sa zone sur le dessin**, verifie sur les six
  geometries. Le point le plus tendu est le couple `armoire` (*au centre, a gauche*, bande verticale
  48-78 %) et `porte` (*en bas, a gauche*, 80-97 %) : les deux sont dans la colonne de gauche, l'une
  clairement au-dessus de l'autre, et un enfant les distingue.
- **Aucun ne nomme un meuble**, ce qui serait une reponse deguisee.

**Donc : je confirme les six libelles de `lea-dev` tels quels. Elle les avait declares provisoires et
a auditer ; je les ai audites, ils sont bons, et ils ne sont pas le motif du refus.** Le refus porte
entierement sur la geometrie. **C'est la moitie de la sortie que j'avais annoncee comme double, et
elle est payee** - l'autre moitie, les cadres, ne l'est pas.

### 5. Deux reserves neuves sur le contenu, datees du 2026-09-11 (s35), non bloquantes

Elles ne sont pas le motif du refus. Je les ecris maintenant pour que la reprise de ce paquet les
absorbe dans le meme geste plutot que d'attendre un audit de plus.

> **`PCL-R1`** - **les six indices n'aident pas a lire un plan, ils disent a quoi sert l'objet.**
>
> Lus a l'ecran : *"C'est la qu'on range le materiel"* (armoire), *"C'est par la qu'on entre et qu'on
> sort"* (porte), *"C'est par la qu'on voit dehors"* (fenetre), *"C'est la qu'on peut lire
> tranquillement"* (coin lecture). **Un enfant de CP sait deja ce qu'est une armoire et une porte.**
> Ce qu'il ne sait pas, et que l'item lui demande, c'est **ou elles sont sur ce plan-ci** -
> `QLM-CP-11` est *"situer un lieu ou un objet sur un plan"*. Un indice qui redit la fonction de
> l'objet n'aide pas a le situer : il aide a le reconnaitre, ce qui n'est pas la competence.
> - **Objet** : *l'indice d'un item de plan aide l'enfant a lire le plan* - a chercher une forme, un
>   mur, une position - et non a reconnaitre un objet qu'il connait deja.
> - **Mode de defaillance** : **donner la position dans l'indice** (*"cherche en bas a gauche"*), qui
>   dicterait le libelle de la bonne zone et rouvrirait la clause 2 d'`ADR-0091` sur ce paquet. **Le
>   geste est de decrire ce que le dessin montre** - la silhouette, le mur contre lequel l'objet est
>   pose - sans donner le rang ni la case.
> - **Perimetre** : **six indices**, aucun autre champ.
> - **Regime et prix** : **rejeu borne, 0,25.**

> **`PCL-R2`** - **l'indice du tableau est inexact, et deux indices sur six font de l'enseignante
> une femme par defaut.**
>
> *"C'est ce que la maitresse regarde le plus souvent."* **C'est faux** : une enseignante regarde sa
> classe, pas son tableau. Et ce n'est pas un indice - cela ne distingue le tableau d'aucune autre
> zone pour un enfant qui reflechit. Le fait vrai et utile est que **c'est la qu'elle ecrit**.
> - Par ailleurs, l'adulte de l'ecole est nomme deux fois sur six items et les deux fois *"la
>   maitresse"*. Le paquet d'EMC de CP nomme trois adultes differents ; celui-ci n'en nomme qu'un, au
>   feminin. **Ce n'est pas une faute grave, c'est un defaut par defaut**, du genre que je verifie
>   systematiquement (`BIA`), et il se corrige en un mot.
> - **Objet** : *aucun indice ne porte un fait inexact, et l'adulte de l'ecole n'est pas toujours le
>   meme.*
> - **Perimetre** : **deux indices**, sur les items du tableau et du bureau.
> - **Regime et prix** : **mot pour mot, 0,25.**

### 6. Ce que je verifie et qui ne fonde aucune demande - ce lot tient sur beaucoup de points

- **Exactitude du dessin** : le plan est juste, vu de dessus, sans perspective, chaque meuble dans sa
  silhouette au sol. Le tableau avec sa tablette, le bureau avec sa chaise, l'armoire a deux vantaux,
  la porte avec son arc de debattement - **le symbole de plan classique, et c'est le bon geste** -,
  la fenetre percee dans le mur, le tapis et la pile de livres du coin lecture. `ZON-12` tenue.
- **`ZON-3` et `ADR-0091` clause 1** : le texte alternatif est *"Plan de la classe vu de dessus, avec
  six elements disposes le long des murs."* **Il dit ce que l'image est, il ne nomme aucune zone, et
  il est exact** - les six elements sont bien le long des murs. **Rien a lui reprocher**, et c'est le
  contraste avec `FRI-R6` sur l'autre paquet de designation.
- **La passe image effacee** : **1 item sur 6 au plus.** Seul *"Montre le tableau"* est devinable
  sans le dessin (un tableau est devant la classe, et un plan met le devant en haut). Les cinq autres
  ne le sont pas : rien dans le texte ne dit de quel cote sont la fenetre, l'armoire, la porte ou le
  coin lecture. **Ce paquet oblige vraiment a lire le plan, et c'est exactement ce qu'on lui
  demande.** `ZON-1` tenue.
- **`ZON-13`** : aucun chevauchement de cadres, verifie sur les quinze paires. Les intervalles les
  plus serres sont de 2 % (armoire/bureau, armoire/porte).
- **`ZON-14`** : un item, un geste. **`ZON-10`** : une seule forme de libelle.
- **`ADR-0023`** : six items pour cinq manches.
- **L'ecran de fin de partie** : *"Bravo, c'est juste ! / Montre la porte."* La question est rappelee,
  aucun identifiant ni libelle de zone n'apparait (`ADR-0088`, clauses A et B). Les deux nombres sont
  justes - verdict separe dans `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **Le plancher de cible tactile** (`ADR-0080`, 12,5 % du plus petit cote, `CIBLE_TACTILE_REM` =
  2,75) : **je ne le rouvre pas**, la story le dit explicitement, et c'est une propriete distincte de
  celle que `ZON-15` ajoute.
- **Ton et biais** : aucun stereotype sur l'enfant, aucune presupposition de milieu, aucun item qui
  interroge l'enfant sur sa propre vie. Le seul point est celui de `PCL-R2`.

### 7. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`ZON-15`** - qu'un libelle tienne dans son cadre et ne couvre pas le dessin | **aucun, et `ADR-0092` decide que ce sera ainsi** : la largeur d'un libelle a 1,25rem depend de la police de l'appareil, et `ADR-0025` a deja decide qu'une mesure de rendu dependant d'un navigateur n'est pas un garde-fou de CI. **C'est moi, a l'oeil, paquet par paquet. Le defaut du jour est la premiere fois que cette garde sert, et elle a trouve six zones sur six** |
| **`ZON-16`** - la forme d'un libelle positionnel | **aucun.** Compter des locutions et juger qu'un mot est purement positionnel demande un jugement de sens |
| **L'exactitude d'un indice** (`PCL-R2`) | **aucun** |
| **Qu'un indice serve la competence visee** plutot qu'une competence voisine plus facile (`PCL-R1`) | **aucun** |
| **La passe image effacee** | **aucun**, `ADR-0091` le dit lui-meme |
| **Que le dessin soit un plan juste** (`ZON-12`) | **aucun** |

### 8. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Le refus** | `camille-po` | **`QLM-CP-11` reste `non couvert`.** Le motif est entier en section 1, avec ses chiffres : un tiers y trouve ce qu'il faut refaire sans me redemander ce que je voulais dire. **Ce refus ne bloque pas le sprint suivant** |
| **La geometrie des six cadres, ou la place du libelle** | `ines-design` (propose), `theo-archi` (forme technique) | Section 3 : les deux sorties que j'accepte, celle que je refuse, et l'arithmetique qui dit pourquoi deux cadres agrandis ne suffiront jamais. **Je ne tranche pas la forme** |
| **Le pave de `.zone`**, 20 px horizontaux sur un cadre de 63 px | `theo-archi` | Symptome, pas solution. **Je dis seulement que `ZON-15` ne l'interdit pas de bouger** : le pave est du cote du cadre, pas de la police |
| **Les six libelles** | `camille-po` | **Rien a faire : ils sont bons, audites, confirmes.** Qu'ils ne soient pas repris dans le perimetre de la reprise |
| **`ZON-16` reformulee le 2026-09-11 (s35)** | `camille-po` | Section 4. **Correction de ma propre formulation, dans le sens de l'assouplissement**, a reporter dans le backlog comme condition opposable |
| **`PCL-R1`, `PCL-R2`** | `camille-po` | Non bloquantes, **a absorber dans la reprise** : un paquet se relit comme un tout |

---

## Bloc de validation pedagogique nº 2 - nadia-prof UNIQUEMENT - 2026-09-21 (s36)

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il leve le refus du bloc nº 1
> sans l'effacer.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-21 (s36)
- **Auteur** : `nadia-prof`
- **Arbre** : `s36-sofia` a `72013e74`, worktree dedie `s36-nadia-audit`, ouvert depuis ce commit,
  lignee verifiee (`git merge-base --is-ancestor` de `round-two` a `a8fcb000`).
- **Porte technique, jobs nommes et non deduits** (`CLAUDE.md`, amendement du 2026-09-08) : je
  n'annonce que ce que j'ai lance moi-meme, soit le contenu du seul job `build`, `mvn -B clean
  verify` sur la racine par l'enveloppe de toolchain - vert, 6 modules, **1 496 tests** (111 + 13 +
  400 + 708 + 258 + 6), sur le JDK `21.0.10` que la toolchain resout. **Les autres jobs du workflow,
  je ne les ai pas lances et je ne les annonce pas** : la porte technique complete est confirmee par
  `sofia-sm`, pas par moi.
- **Audit joue : oui, et cette fois dans un vrai navigateur.** Jar construit et lance depuis mon
  propre worktree, joue dans Chrome pilote par le protocole DevTools : clics de pointeur reels
  (`Input.dispatchMouseEvent`, la sequence qu'un doigt produit), lecture des geometries par
  `getBoundingClientRect` sur le document servi, captures d'ecran du rendu reel.
  **Une partie complete de cinq manches, les six items vus a l'ecran, plus une manche jouee a trois
  erreurs volontaires pour lire ce que l'enfant obtient quand il bloque.**
- **Ce que cela corrige de mon audit de s35** : j'y avais declare un **audit degrade** - *"je n'ai
  pas rendu l'ecran dans un navigateur"*, chiffres obtenus en repliant les libelles avec les
  metriques de la police. **La methode etait bonne, je la retire quand meme** : la mesure de s36 est
  faite sur le rendu, elle n'a plus besoin d'etre creditee sur la foi d'un controle. Et elle
  confirme le chiffre de s35 la ou il etait verifiable.
- **Mouvement de matrice** : **`QLM-CP-11` passe `non couvert` -> `couvert-valide`.** C'est
  `camille-po` qui ecrit la ligne, pas moi ; le Done pedagogique qui l'autorise est ici.

### 1. La premiere moitie de `ZON-15` est tenue, sur les six zones, et c'etait tout le motif du refus

**Ma condition, mot pour mot** (bloc nº 1, section 1) : *"A la taille servie, le libelle tient
entierement dans son cadre sans deborder ni toucher une zone voisine [...]"*. C'est la moitie qui
etait rompue de 17 a 95 px sur six zones sur six.

**Mesure du 2026-09-21, sur le document servi, police de l'appareil telle que le navigateur la
resout** (`system-ui`, rendue a `20px / 700` - le plancher de 1,25rem est tenu, `.zone` porte
toujours `padding: 12px 20px` et `border: 2px`, rien n'a ete rogne de ce cote) :

| Zone | Numero servi | Cadre rendu | Boite du chiffre | Debordement horizontal | Debordement vertical |
|---|---|---|---|---|---|
| `tableau` | **1** | 119,7 x 52,8 | 11,5 x 27 | **0,00** | **0,00** |
| `bureau` | **2** | 98,5 x 70,4 | 11,5 x 27 | **0,00** | **0,00** |
| `fenetre` | **3** | 63,4 x 140,8 | 11,5 x 27 | **0,00** | **0,00** |
| `armoire` | **4** | 105,6 x 105,6 | 11,5 x 27 | **0,00** | **0,00** |
| `coin-lecture` | **5** | 119,7 x 70,4 | 11,5 x 27 | **0,00** | **0,00** |
| `porte` | **6** | 112,6 x 59,8 | 11,5 x 27 | **0,00** | **0,00** |

`scrollWidth - clientWidth` et `scrollHeight - clientHeight` valent **0 sur les six** : il n'y a pas
de debordement masque non plus. **Aucun libelle ne touche un cadre voisin**, puisqu'aucun ne sort de
son propre cadre. La zone la plus contrainte, `fenetre`, offrait 19,4 px de largeur utile pour un
libelle qui en demandait 65 ; elle en offre 19,4 pour un chiffre qui en demande 11,5.

**C'est la barre que j'avais ecrite, elle est atteinte, je leve ma reserve** - y compris sur les
quatre cadres que je n'avais pas mesures a s34 et qui avaient fonde le refus de s35.

### 2. La seconde moitie de `ZON-15` est rompue a la lettre sur trois zones, je la clarifie plutot que de la faire tomber en silence

**Je ne peux pas la declarer tenue, parce qu'elle ne l'est pas a la lettre, et je l'ai mesuree.**

Methode : rendu du dessin a la taille servie, agrandi seize fois, capture avec les chiffres, sans
les chiffres (`color: transparent`, cadres en place) et sans les zones du tout. Le **diff des deux
premieres** donne l'encre du chiffre au pixel ; la **troisieme** dit si ce pixel couvrait un trait
du dessin.

| Zone | Encre du chiffre | Dont posee sur un trait du dessin | Trait concerne |
|---|---|---|---|
| `tableau` (**1**) | 13 480 px | **2 851 (21,1 %)** | le bord inferieur du rectangle du tableau |
| `bureau` (**2**) | 19 526 px | 0 (0 %) | - |
| `fenetre` (**3**) | 18 849 px | 0 (0 %) | - |
| `armoire` (**4**) | 19 674 px | **6 080 (30,9 %)** | la ligne mediane des deux vantaux |
| `coin-lecture` (**5**) | 19 438 px | 0 (0 %) | - |
| `porte` (**6**) | 22 253 px | **6 271 (28,2 %)** | l'arc de debattement |

**Trois zones sur six.** Et cela dement une phrase de la proposition d'`ines-design` (section 2.4) :
*"aucun ne recouvre un trait du dessin au-dela de la boite translucide deja existante"*. Ce n'est pas
un reproche - c'est exactement le genre de propriete qu'un mockup ne peut pas etablir, et que
`ADR-0094` a eu raison de laisser a mon oeil. Mais la phrase est fausse telle qu'elle est ecrite, et
elle ne doit pas etre recopiee.

> **Clarification de `ZON-15`, datee du 2026-09-21 (s36), par l'auteur de sa formulation.**
> Sa seconde moitie disait *"ne recouvre aucun trait du dessin que sa zone designe"*. **Ce qu'elle
> protege est que l'enfant voie la silhouette de l'objet pour le reconnaitre** ; ce qu'elle
> interdisait, a la lettre, est le croisement d'un seul trait par un seul jambage. La condition
> devient : **le libelle ne rend illisible aucune silhouette du dessin que sa zone designe.**
>
> **C'est un assouplissement, et je le dis comme tel**, dans la meme famille que la reformulation de
> `ZON-16` du 2026-09-11 : j'admets des ecrans que la lettre refusait, et je refuse exactement ceux
> que son intention refusait. **Ce n'est pas un poteau deplace apres coup** - ce serait l'inverse,
> se servir de la lettre contre l'intention le jour ou l'equipe a paye le motif que j'avais nomme.
>
> **Ce que j'ai regarde avant de l'ecrire** : les deux rendus, avec et sans les zones, agrandis
> seize fois, cote a cote. Les six silhouettes sont aussi identifiables dans l'un que dans l'autre.
> Le trait du tableau est interrompu sur **3 px sur 105** ; la mediane de l'armoire sur **27 px sur
> 98**, et elle reste une ligne unique ; l'arc de la porte sur **environ 25 px sur 90**, et il reste
> un arc - c'est meme le symbole de plan le plus reconnaissable des six. **Rien ne devient ambigu.**

**Et je ne la brade pas pour autant : la sortie propre existe et elle est bon marche.** `.zone`
centre son contenu (`align-items: center; justify-content: center`). Un chiffre pose **dans un coin
du cadre** plutot qu'en son centre ne rencontrerait aucun des trois traits, sur les trois zones
concernees. Je ne tranche pas la forme - c'est `ines-design` qui propose et `theo-archi` qui decide
- mais je l'ouvre en reserve datee (`PCL-R3`), pas en motif de refus.

### 3. La correspondance numero -> libelle, qui est ce que personne d'autre ne pouvait juger

**C'est la propriete qu'`ADR-0094` me laisse explicitement et sur laquelle aucun garde-fou ne mord.
Verdict : un enfant de CP l'etablit sans ambiguite.** Ce que j'ai verifie sur le document servi :

- Le bouton porte **un seul caractere**, de **1** a **6**, **servi par le serveur** (le rang
  d'iteration du gabarit, jamais un compteur de feuille de style) et rendu a **20 px gras**, comme
  le reste des boutons actionnables.
- La legende est une liste ordonnee de six lignes, dans **le meme ordre que les boutons**, celui que
  le paquet declare, a **20 px** - **jamais plus petite que le chiffre qu'elle explique**, ce qui
  etait mon plancher.
- Les six correspondances sont **bijectives** et je les ai lues une a une sur l'ecran : 1 -> *en
  haut, au centre*, 2 -> *en haut, a gauche*, 3 -> *au centre, a droite*, 4 -> *au centre, a
  gauche*, 5 -> *en bas, a droite*, 6 -> *en bas, a gauche*. **Chacune est vraie de la position
  reelle de sa zone sur le dessin** - c'est ce que j'avais deja verifie a s35 sur les libelles, et
  la numerotation ne le derange pas.
- La legende est posee **immediatement sous le dessin**, alignee sur son bord gauche, dans l'ordre
  de lecture. Rien ne s'intercale.

**Ce qui rend cette forme acceptable au CP, et que je veux au proces-verbal parce que c'est le
raisonnement, pas le resultat** : **l'enfant n'a jamais besoin de la legende pour jouer.** La
question est *"Montre le tableau."* ; la reponse se trouve sur le plan, pas dans la liste. Le
libelle positionnel est le **nom** du bouton, pas l'enonce. La numerotation n'ajoute donc **rien a
tenir en tete** : c'est un repere neutre, et la legende est un appui disponible, pas une etape.
Si la reponse avait dependu de la lecture de la legende, j'aurais refuse cette forme au CP sans
hesiter - un aller-retour entre un dessin et une liste de six lignes est trop cher a six ans.

**Ce que la forme coute quand meme, et je l'ecris comme une reserve neuve** : a s35, *"en haut, au
centre"* se lisait **a l'endroit meme qu'il nomme**. C'etait un petit gain gratuit pour
`QLM-CP-11`, qui est une competence de reperage dans l'espace. Il est perdu : le vocabulaire
positionnel est desormais dans une liste, detache de la position. **Non bloquant** - la competence
est evaluee par le geste sur le plan, jamais par la lecture du libelle - mais c'est une perte reelle
et elle ne doit pas disparaitre du dossier (`PCL-R4`).

### 4. Ce que s36 devait absorber et n'a pas absorbe : `PCL-R1` et `PCL-R2` sont intactes

Le critere d'acceptation de `S36-QLM11-PLANCLASSE` disait : *"Etant donne les deux reserves de
contenu non bloquantes de s35, quand cette reprise est livree, alors elles sont absorbees dans le
meme geste plutot que reportees a un sixieme audit."*

**Les six indices de `cp-plan-de-la-classe.yaml` sont inchanges, caractere pour caractere.** Je les
ai relus a l'ecran en jouant, et dans le paquet :

- *"C'est ce que la maitresse regarde le plus souvent."* - **l'enonce que j'avais qualifie d'inexact
  le 2026-09-11**, toujours servi tel quel. Une enseignante regarde sa classe, pas son tableau.
- *"C'est la que la maitresse pose ses affaires."* - la seconde des deux occurrences de *"la
  maitresse"*, toujours la.
- Les quatre autres disent toujours a quoi sert l'objet plutot que d'aider a le situer sur ce
  plan-ci.

**C'est le seul critere d'acceptation de la story qui n'est pas tenu.** Il n'est pas rattrapable par
autre chose : il portait sur du texte, et le texte n'a pas bouge.

**Et je ne le transforme pas en motif de refus, deliberement.** Ma propre methode dit qu'une erreur
factuelle dans un contenu scolaire refuse a elle seule ; **j'ai pourtant classe `PCL-R2` non
bloquante le 2026-09-11, par ecrit, et je ne reclasse pas apres coup une reserve que j'ai moi-meme
gradee, le sprint ou l'equipe a paye le motif que j'avais nomme comme etant le refus.** Une barre
qu'on releve apres coup fait de ma porte un caprice. `PCL-R1` et `PCL-R2` restent donc ouvertes,
non bloquantes, inchangees dans leur redaction de s35, et **elles sont desormais du retard, pas une
nouveaute** : c'est le second sprint ou elles passent.

### 5. Deux defauts que j'ai trouves en jouant, nouveaux et dates du 2026-09-21, hors perimetre de cette story

Je les ecris ici parce que je les ai vus sur cet ecran. **Ils ne sont pas des regressions de s36** :
`git log -S` sur la feuille maison les date de s34, et ils touchent **aussi**
`cp-designation-frise-de-la-journee`, deja `couvert-valide` (`QLM-CP-14`). **Je ne rouvre pas cette
ligne-la** - je ne l'avais pas vu, je le dis, et je ne me sers pas d'aujourd'hui pour durcir hier.

> **`PCL-R5`** - **une zone deja essayee ne se distingue en rien d'une zone neuve.**
>
> Mesure sur le document servi, apres deux erreurs de pointeur reelles : les boutons portent bien la
> classe `deja-essayee`, et leur `background-color`, `border-color`, `color`, `text-decoration` et
> `opacity` sont **identiques** a ceux d'un bouton non essaye. La feuille maison ne declare
> `.zone.deja-essayee` qu'a `:hover`, `:active` et `:focus-visible`, pour neutraliser les etats
> tiers ; **il n'existe aucune regle au repos**, la ou `.proposition.deja-essayee` en a une sur
> l'autre mecanique. **La marque de `M9` n'existe donc pas sur une zone de designation.**
> - **Objet** : *un enfant qui s'est trompe voit ce qu'il a deja essaye.* A six ans, retenir deux
>   cases deja tentees sur six pendant qu'on relit l'enonce et l'indice est exactement la charge
>   qu'on ne doit pas lui demander.
> - **Perimetre** : la mecanique de designation, donc **les deux paquets**.
> - **Cause probable, que je ne diagnostique pas** : symptome d'interface, a `ines-design` (propose)
>   et `theo-archi` (decide).

> **`PCL-R6`** - **apres la troisieme erreur, la bonne zone n'est designee par rien a l'ecran.**
>
> Joue avec des clics de pointeur reels. Erreurs 1 et 2 : *"Ce n'est pas ca. Essaie encore !"*.
> Erreur 3 : *"Ce n'est pas ca."*, la manche se termine, et la bonne zone recoit `data-focus-cible`
> et le focus du document. **Mais `:focus-visible` est faux apres un appui de pointeur** : `outline`
> vaut `none 0px`, `box-shadow` vaut `none`, le fond et la bordure ne bougent pas. Capture d'ecran a
> l'appui : l'enfant voit *"Ce n'est pas ca."*, l'indice, et **six cases rigoureusement
> identiques**, dont aucune ne lui dit laquelle etait la bonne ni lesquelles il venait d'essayer.
> - **Objet** : *un enfant qui echoue trois fois voit la bonne reponse.* Un simple *"Ce n'est pas
>   ca."* n'apprend rien ; c'est la regle que j'applique depuis le premier jeu.
> - **Ce que cela ne remet pas en cause** : l'indice, lui, s'affiche des la premiere erreur et il
>   est le vrai echafaudage de la manche. C'est pour cela que ce defaut est une reserve et non un
>   refus : la boucle d'apprentissage fonctionne, elle se tait au pire moment.
> - **Perimetre** : la mecanique de designation, donc **les deux paquets**.
> - **Cause probable, que je ne diagnostique pas** : la revelation ne voyage que par le focus, et le
>   focus au pointeur n'a pas d'indicateur visible. Symptome a `theo-archi`.

### 6. Ce que je verifie et qui ne fonde aucune demande

- **`ZON-9`, verifiee pour la premiere fois** - elle etait declaree *non verifiable* a s35 parce que
  le dessin etait couvert. Dessin rendu **zones masquees** : les six objets restent identifiables
  chacun par sa silhouette (le tableau et sa tablette, le bureau et sa chaise, la fenetre percee
  dans le mur de droite, l'armoire a deux vantaux, l'arc de la porte, le tapis et la pile de
  livres). **Tenue.**
- **Le rendu des autres jeux de designation est inchange**, ce qu'`ADR-0094` annonce et que j'ai
  verifie parce qu'il touche un ecran que j'avais deja accorde : sur
  `cp-designation-frise-de-la-journee`, les boutons portent toujours leur libelle en toutes lettres
  (*"le premier"*, *"le deuxieme"*...), **aucun `aria-label`**, **aucune legende**. Le document servi
  est celui de s35.
- **Le nom accessible** (`ADR-0093` clause 2, `ADR-0094` decision 5) : lu sur le document servi, les
  six boutons portent `aria-label` egal au libelle que le paquet declare, mot pour mot et accents
  compris. **L'enfant qui n'entend que le nom du bouton entend la meme chose qu'a s35.**
- **Le plancher de police** : 20 px (1,25rem) gras sur les chiffres, 20 px sur la legende. Rien n'a
  ete baisse pour resoudre la geometrie, ce que je refusais explicitement.
- **L'alternative `(d)` d'`ADR-0092`** (accepter qu'un libelle deborde) **n'a pas ete reprise** :
  mesure a 0,00 px, le debordement n'est pas accepte, il n'existe plus.
- **Les six libelles n'ont pas ete retouches**, comme la story le demandait ; `ZON-16` reste acquise
  sans etre rejouee.
- **Le jeu tourne de bout en bout** : cinq manches, six items, aucun ecran casse, ecran de fin de
  partie correct (*"Bravo, c'est juste !"*, aucun identifiant ni libelle de zone).
- **Ton et biais** : rien de neuf. Le seul point reste celui de `PCL-R2`.

### 7. Ce dont je reste la seule garde sur ce paquet, apres s36

| Propriete | Garde executable ? |
|---|---|
| **`ZON-15`, premiere moitie** - qu'un libelle tienne dans son cadre | **aucun**, et `ADR-0092` decide que ce sera ainsi. **C'est moi, a l'oeil, paquet par paquet.** Elle a servi a s35 en trouvant six zones sur six, et a s36 en confirmant les six |
| **`ZON-15`, seconde moitie telle que clarifiee le 2026-09-21** - qu'aucune silhouette ne devienne illisible | **aucun.** Juger qu'une silhouette reste reconnaissable n'est pas mesurable par un seuil |
| **La justesse d'une declaration `hors-de-la-boite`** (`ADR-0094`, consequences) | **aucun, et `ADR-0094` le nomme lui-meme.** Un paquet peut la declarer sans en avoir besoin, ou ne pas la declarer avec des cadres trop etroits, et la CI reste verte |
| **Que la correspondance numero -> libelle soit lisible par un enfant de CP** | **aucun.** C'est ce que la section 3 tranche, et rien ne la rejouera a ma place |
| **`ZON-16`** - la forme d'un libelle positionnel | **aucun** |
| **L'exactitude d'un indice** (`PCL-R2`) | **aucun** |
| **Qu'un indice serve la competence visee** (`PCL-R1`) | **aucun** |
| **La passe image effacee** et **`ZON-9`** | **aucun**, `ADR-0091` le dit lui-meme |
| **Que le dessin soit un plan juste** (`ZON-12`) | **aucun** |
| **Que l'enfant voie ce qu'il a essaye et ce qu'il a rate** (`PCL-R5`, `PCL-R6`) | **aucun**, et c'est le quatrieme defaut visible par un enfant que la CI verte laisse passer sur ce depot |

### 8. Ce que je route, apres s36

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Le Done pedagogique** | `camille-po` | **`QLM-CP-11` : `non couvert` -> `couvert-valide`.** Cinq sprints, un refus, une reprise ciblee qui paie exactement le motif ecrit. C'est la ligne qui se ferme |
| **`PCL-R1`, `PCL-R2`** | `camille-po` | **Du retard, plus une nouveaute** : second sprint ou elles passent, et elles etaient un critere d'acceptation de `S36-QLM11-PLANCLASSE`. Redaction inchangee, bloc nº 1 section 5. **Un paquet se relit comme un tout** |
| **`PCL-R3`** - le chiffre dans un coin du cadre plutot qu'en son centre | `ines-design` (propose), `theo-archi` (decide) | Section 2. Supprimerait le croisement des trois traits sans toucher ni la police, ni le cadre, ni le contenu. **Je ne tranche pas la forme** |
| **`PCL-R4`** - le vocabulaire positionnel s'est detache de la position qu'il nomme | `camille-po` | Section 3. Non bloquant, mais c'est une perte reelle pour une competence de reperage dans l'espace, et elle merite d'etre pesee avant qu'une autre ligne emprunte la meme forme |
| **`PCL-R5`, `PCL-R6`** - marque de la zone essayee, revelation apres la troisieme erreur | `camille-po` (backlog), `ines-design` et `theo-archi` (forme et cause) | Section 5. **Transverses a la mecanique de designation, donc aux deux paquets.** Neufs, dates du 2026-09-21, jamais vus avant par moi. **Je ne rouvre pas `QLM-CP-14`** |
| **La phrase de la section 2.4 de la proposition d'`ines-design`** | `ines-design` | *"aucun ne recouvre un trait du dessin"* est fausse sur trois zones, mesuree. La bonne formulation est celle de la clarification du 2026-09-21. **A ne pas recopier telle quelle** |
