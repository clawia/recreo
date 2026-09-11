# Fiche de conformite - Le plan de la classe (`designation-plan-de-la-classe`)

- **Mecanique** : `designation` (`ADR-0080`, `ADR-0088`, `ADR-0092`)
- **Contenu(s)** : `cp-plan-de-la-classe` (6 items, un dessin, `nombreDeManches: 5`)
- **Statut global** : **REFUSE** (premier passage, 2026-09-11, s35)
- **Motif en une phrase** : **les six libelles de zone debordent tous de leur cadre**, de 17 a 95 px
  selon la zone, se recouvrent entre voisins et couvrent les traits du dessin qu'ils designent -
  `ZON-15` n'est tenue sur aucune des six zones. **Les six libelles eux-memes sont bons** : `ZON-16`
  est tenue sur les six, et je les confirme tels quels.
- **Derniere mise a jour** : 2026-09-11 (s35)

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

**Etat de la ligne apres cet audit** : `non couvert`. **Quatrieme sprint sur cette ligne, et premier
ou elle est reellement servie et donc reellement auditable** - ce qui est un progres, et je le dis
avant le refus.

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
- [ ] **A la taille servie, chaque libelle tient entierement dans son cadre, sans deborder ni
      toucher une zone voisine, et ne recouvre aucun trait du dessin que sa zone designe
      (`ZON-15`)** - **non tenue, sur les six zones**
- [ ] **La cible se trouve sur le dessin seul, zones masquees (`ZON-9`)** - **non verifiable :
      le dessin est couvert par les libelles qui debordent**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

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
