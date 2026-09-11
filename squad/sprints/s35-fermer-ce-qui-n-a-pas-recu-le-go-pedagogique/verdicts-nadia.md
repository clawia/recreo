# Verdicts pedagogiques de s35 - `nadia-prof`

**Auteur** : `nadia-prof`. **Une seule instance ce sprint.**
**Date** : 2026-09-11.
**Arbre** : `s35-integration` a `c1b326d4`, worktree dedie `s35-nadia`, ouvert depuis
`s35-integration`.
**Ou vivent les verdicts qui font foi** : dans les fiches de conformite, pas ici. Ce document les
reprend pour la review ; **en cas d'ecart, la fiche gagne.**

---

## 0. Conditions de l'audit, declarees avant les verdicts

**J'ai joue.** Jar reconstruit depuis mon propre worktree (`mvn -pl bootstrap -am clean package`,
enveloppe de toolchain), lance sur le JDK `21.0.10` que cette toolchain resout, port 8080. Parties
jouees par requetes HTTP reelles, donc sur le HTML reellement servi, avec la feuille maison et la
feuille tierce telles que l'application les sert.

**Ce que j'ai joue, en tout** : **treize parties completes**, sur **onze entrees de catalogue** et
**les trois mecaniques du produit**. Tous les items des paquets audites ont ete vus a l'ecran :
11 items d'EMC de CP, 11 items de la frise, 6 items du plan de la classe, les 25 items d'anglais de
CM1, et une partie complete sur chacun des quatre paquets d'histoire-geographie issus de la
scission.

**Audit degrade sur un point, et je le declare parce que la moitie de mes verdicts en depend.** Je
n'ai pas rendu les ecrans dans un navigateur, comme je l'avais fait a s34. J'ai rendu les dessins
**a partir de leur geometrie** a la taille exacte a laquelle ils sont servis, pose sur eux les cadres
de zone avec le pave, la bordure et le fond exacts de `.zone`, et replie chaque libelle **avec les
metriques reelles de la police systeme de l'appareil a 1,25rem gras** - celle que la pile
`--bs-font-sans-serif` designe, faute de `@font-face` servi, ce qu'`ADR-0092` nomme justement comme
la raison pour laquelle aucune arithmetique ne peut garder cette propriete.

**Le controle qui rend cette methode credible, et sans lequel je n'en tirerais rien** : appliquee a
la frise de la journee, elle retrouve **au pixel** le chiffre que j'avais mesure au navigateur a s34
- `FRI-R4`, *"le quatrieme"* et *"le cinquieme"* debordent de **2 a 3 px**. Deux methodes
independantes, le meme resultat. Et sur le plan de la classe, **les deux polices candidates du poste
donnent le meme verdict sur les six zones**, a 1 ou 2 px pres : la conclusion ne depend pas de la
police.

---

## 1. Les six verdicts, en une ligne chacun

| Story | Verdict | Ce qui se ferme | Ce qui s'ouvre | Ou il est ecrit |
|---|---|---|---|---|
| `S35-SCO1-ECRAN-FIN` | **VALIDE SOUS RESERVE** | **`SCO-R1` levee** | `SCO-R4` (non bloquante) | `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md` |
| `S35-EXC-INDICES` | **VALIDE SOUS RESERVE** | **`EXC-R10` levee, `EXC-R11` soldee** | `EXC-R13`, `EXC-R14` (non bloquantes) | `FICHE-bandit-manchot-exclusion-cp.md` |
| `S35-QLM14-FRISE` | **VALIDE SOUS RESERVE** | **`FRI-R1` et `FRI-R2` levees** | `FRI-R6`, `FRI-R7` (non bloquantes) | `FICHE-designation-frise-de-la-journee.md` |
| `S35-QLM11-PLANCLASSE` | **REFUSE** | rien | `PCL-R1`, `PCL-R2` (non bloquantes) | `FICHE-designation-plan-de-la-classe.md` (**fiche neuve**) |
| `S35-ANG-R3` | **VALIDE** | **`ANG-R3` levee sur ses deux clauses, `ANG-R7` levee** | `ANG-R8` (non bloquante) | `FICHE-bandit-manchot-anglais-cm1.md` |
| `S35-CONS-4` | **VALIDE** | **`CONS-4` levee** | `CONS-5` (non bloquante) | `FICHE-bandit-manchot-histoire-geographie-cm1.md` + **quatre fiches neuves** |

**Cinq `VALIDE` ou `VALIDE SOUS RESERVE`, un `REFUSE`. Quatre refus de s34 sont payes** - `SCO-R1`,
`EXC-R10`, `FRI-R1`, `FRI-R2` - **et le seul refus de ce sprint porte sur la seule story qui livrait
une ligne neuve.**

### Mouvements de matrice que ces verdicts autorisent - a `camille-po`

| Ligne | Avant | Apres | Condition |
|---|---|---|---|
| **`QLM-CP-14`** | `non couvert` (refusee a s34) | **`couvert-valide`** | **et la mention de couverture partielle tombe** - `FER-1` deja verifiee tenue sur les trois relations |
| `QLM-CP-11` | `non couvert` | **`non couvert`** | **refus.** Quatrieme sprint sur cette ligne |
| `EMC-CP-03`, `LVE-CM1-07`, et les onze lignes d'histoire-geographie | inchangees | **inchangees** | corrections et scission, aucune fermeture |

**Un seul mouvement de matrice sur tout le sprint**, ce qui est exactement ce que l'objectif gele
prevoyait : s35 ferme des refus, il n'ouvre pas de couverture.

---

## 2. `S35-QLM11-PLANCLASSE` - le seul refus, et son motif en entier

**Le motif, chiffre.** Le dessin est servi a **352 x 352 px** - taille fixe, independante de la
fenetre au-dessus du premier palier de largeur, donc ce refus vaut sur tout poste. `.zone` mange
**44 px de largeur et 28 px de hauteur** sur chaque cadre avant que le libelle commence.

| Zone | Boite de contenu offerte | Ce que le libelle demande | Debordement |
|---|---|---|---|
| `tableau` | 75,7 x **24,8** | 60 x **120** | **95 px en hauteur** |
| `bureau` | **54,6** x 42,4 | **68** x **120** | 13 px en largeur, 78 en hauteur |
| `fenetre` | **19,4** x 112,8 | **65** x **120** | 46 px en largeur, 7 en hauteur |
| `armoire` | 61,6 x 77,6 | 68 x **120** | 6 px en largeur, 42 en hauteur |
| `coin-lecture` | 75,7 x 42,4 | 74 x **60** | 18 px en hauteur |
| `porte` | 68,6 x **31,8** | 68 x **90** | **58 px en hauteur** |

**Six zones sur six. De 17 a 95 px.** A l'ecran : *"en"* est pousse hors du dessin au-dessus du mur
du haut, *"au centre"* du tableau tombe dans le cadre du bureau, *"gauche"* de l'armoire tombe dans
celui de la porte, *"gauche"* de la porte sort par le bas de l'image, et **les six libelles couvrent
les traits du meuble que leur zone designe**. `ZON-15` est rompue sur ses deux moities.

**Ce que `ines-design` a fait est reel et je le porte au credit du lot** : la boite de contenu de
l'armoire passe de 16-23 px a 61,6 x 77,6, quatre fois la surface utile. **Ce n'est pas suffisant, et
les quatre cadres que je n'avais pas mesures a s34 sont dans le meme etat ou pire** - `fenetre`
offre 19,4 px de largeur utile, moins que les *"16 a 23 px"* que j'avais qualifies d'impossibles.

**Je n'ai pas deplace mes poteaux en mesurant quatre cadres de plus, et je m'en explique dans la
fiche** : `ZON-15` porte sur *"le libelle d'une zone"* et le critere d'acceptation de la story dit
*"chaque zone du plan de la classe"*. Les deux cadres nommes a s34 l'etaient sous le titre *"Ce que
cela repond a `lea-dev`, concretement"* - une illustration, jamais un perimetre.

**La cause, mesuree, et qui vaut pour tout paquet de designation a venir** : un libelle positionnel
replie sur deux lignes demande, dans le pave de `.zone`, un cadre d'au moins **109 x 88 px**, soit
**31 % x 25 %** du dessin. Il en faut six. Or **trois des six elements d'un plan de classe sont des
bandes minces par nature** - un tableau contre un mur, une fenetre percee dans un mur, une porte. On
ne peut pas donner 25 % de hauteur au tableau sans mentir sur le plan, et mentir sur le plan est ce
que `QLM-CP-11` interdit. **Le libelle d'une zone ne peut pas vivre a l'interieur de son cadre quand
ce cadre doit rester une bande mince.**

**Je ne tranche pas la forme** (`ines-design` propose, `theo-archi` decide). **Ma porte accepte** que
le libelle sorte de la boite du bouton, et que le **pave** de `.zone` soit reduit sur une zone de
designation - 20 px de pave horizontal mangent 40 des 63 px du cadre de la fenetre, et le pave est du
cote du cadre, pas de la police. **Ma porte refuse** de baisser sous 1,25rem, et refuse l'alternative
`(d)` d'`ADR-0092` : le debordement n'est pas cosmetique ici, **il fait se recouvrir les libelles de
zones voisines**, et un enfant ne saurait plus quel mot appartient a quel bouton.

**Et la moitie de la sortie qui m'incombait est payee** : `ZON-16` est tenue sur les six libelles de
`lea-dev`, je les ai audites un par un et **je les confirme tels quels**. Elle les avait declares
provisoires ; ils sont bons, et **ils ne sont pas le motif du refus**. Qu'ils ne soient pas repris
dans le perimetre de la reprise.

---

## 3. Deux corrections que je fais sur mes propres ecrits, avant de m'appuyer dessus

### 3.1 `ZON-16` telle que je l'avais ecrite refuse l'exemple que je donne comme acceptable

Ma condition du 2026-09-11 (s34) disait : *"Un libelle positionnel de CP compte **au plus trois
mots** et ne porte **qu'un seul repere**. 'en haut, au centre' passe ; 'a gauche, contre le mur' ne
passe pas."*

**"en haut, au centre" fait quatre mots et porte deux reperes.** Ma regle, prise a la lettre, refuse
mon propre exemple d'acceptable, dans la meme phrase. **C'est l'exemple qui dit ce que je voulais
dire**, parce que c'est le cas concret que j'ai juge : la frontiere passe entre une **coordonnee**
faite de deux locutions purement positionnelles, et un libelle qui **nomme un objet du dessin** que
l'enfant doit d'abord trouver.

> **`ZON-16`, reformulee le 2026-09-11 (s35).** Un libelle positionnel de CP est fait **d'au plus
> deux locutions positionnelles** (donc au plus quatre mots), **chacune purement positionnelle**, et
> il **ne nomme aucun objet du dessin**. *"en haut, au centre"* passe ; *"a gauche, contre le mur"*
> ne passe pas.

**Cette correction assouplit ma condition, elle ne la durcit pas** : elle admet des libelles que la
lettre de la version precedente refusait, et refuse exactement les memes que son exemple refusait.
Je la date et **je laisse la version precedente lisible dans le verdict de s34** plutot que de la
reecrire la-bas.

### 3.2 Deux longueurs que ma propre condition a produites, et que je ne reproche a personne

- **`EXC-R14`** : les cinq indices de la tache B d'EMC font desormais **19 a 24 mots**, contre 10 a
  12 sur la tache A. J'ai demande que l'indice dise pourquoi la conduite ecartee ne marche pas, et
  **le moteur ne sert qu'un seul indice par item** - verifie en jouant, j'ai presse les deux
  distracteurs de chaque item et obtenu le meme indice. L'auteur devait refuter deux conduites dans
  une phrase. **Ma condition a produit cette longueur.**
- **`ANG-R8`** : les gloses *(sous terre)* et *(une voiture)* ecartent les deux distracteurs a la
  lecture. J'ai demande que *imperiale* soit explique dans la proposition ; une fois la bonne reponse
  glosee, gloser les deux autres devient necessaire, sinon **la glose marque la reponse**. **Le lot a
  pris la seule sortie qui ne cassait pas ma demande, et elle a ce cout.**

---

## 4. Ce que j'ai regarde et sur quoi je ne demande rien, la ou j'aurais pu

Trois points ou refuser ou exiger m'etait ouvert, et ou je ne le fais pas. Je les ecris pour qu'on
puisse me les reprocher.

1. **`ZON-15` sur la frise de la journee.** Les libelles y recouvrent des traits des pictogrammes
   qu'ils designent - **je l'ai vu**. Je l'ai ecrite le 2026-09-11 (s34) en declarant *"elles ne
   valent pas contre le lot d'aujourd'hui"*, et la story de s35 la reserve au plan de la classe.
   **Je ne l'oppose pas a ce lot.** Elle sera opposable au prochain lot de frise.
2. **L'item de Big Ben.** Son enonce est *"Quel monument celebre, **situe a Londres**, sert aussi
   d'horloge ?"* contre *"le Colisee"* et *"la statue de la Liberte"* - **la forme exacte du defaut
   que je viens de faire corriger sur l'item de la statue**, et il est maintenant le seul item de la
   ligne ou l'enonce ecarte ses deux distracteurs tout seul. **J'ai ecrit le 2026-09-09 que les cinq
   items de s28 sont hors de la portee de `PLA-1` et que je ne les lui soumets pas.** Big Ben en est
   un. Le constat est ecrit dans la fiche, sans demande.
3. **`FRI-R6`, et je le dis sans detour.** Le texte alternatif de la frise du soir annonce *"quatre
   moments de gauche a droite"* pour un dessin qui porte **quatre moments sur trois places**, deux
   d'entre eux empiles - et **l'ecart tombe exactement sur la relation *en meme temps* que les items
   10 et 11 testent.** Un enfant qui travaille sur ce texte s'entend decrire une succession la ou
   l'exercice porte sur la simultaneite. **Si cette phrase etait un enonce et non un texte
   alternatif, elle serait un refus a elle seule**, parce qu'une erreur factuelle dans un contenu
   scolaire en est un. Je ne refuse pas : la dishonnetete que `FRI-R1` nommait - la table de
   correspondance - a bien disparu, mes deux motifs bloquants sont payes, et refuser laisserait
   `QLM-CP-14` non couverte pour un cinquieme sprint. **Mais c'est la plus lourde des reserves de ce
   sprint, et elle est a payer avant que la formule ne soit recopiee sur un paquet de designation de
   plus.**

---

## 5. La question que `theo-archi` m'a routee, et ma reponse

Il signale que l'item 9 de la frise porte l'indice *"C'est le tout dernier moment de la journee"*
alors que le libelle de sa bonne zone est *"le troisieme"*, et que le garde-fou d'`ADR-0091` laisse
passer ce cas. **Je l'ai joue. Oui, c'est un probleme reel ; non, il n'est pas bloquant ; et ce n'est
pas celui qu'il soupconnait.**

Le probleme n'est pas la paraphrase qui donne la reponse. C'est que **l'indice nomme un rang dans une
echelle que le dessin n'offre pas** : la frise du soir porte quatre moments et **trois places**. Un
enfant qui vient de compter quatre dessins s'entend dire *"le tout dernier"* et cherche un quatrieme
bouton ; il n'en existe pas. **Deux systemes de comptage se croisent sur le meme ecran.** Non
bloquant parce que l'indice n'arrive qu'apres une erreur, que la bonne zone est atteignable et que
l'accroc est une hesitation, pas une reponse fausse. `FRI-R7`, datee, non bloquante, un indice a
reecrire.

---

## 6. Ce dont je reste la seule garde apres ce sprint - dit explicitement, pour que rien ne paraisse couvert sans l'etre

**Le fait qui justifie que je joue** : quatre defauts visibles par un enfant ont traverse une CI
entierement verte a s34. **Ce sprint en ajoute un cinquieme, et il est de la meme famille.**

| Propriete | Garde executable ? |
|---|---|
| **La taille des deux libelles de l'ecran de fin** (`SCO-R4`) | **aucun, et le trou est nomme.** `TailleDesTextesEtBoutonsTest` verifie *"aucun texte ne passe sous la taille de la consigne"* sur **une liste de sept selecteurs ecrits a la main**, et son autre methode ne juge que les **tailles declarees**. Les deux libelles neufs ne declarent aucune taille : **ils sont passes par ce trou avec quatre jobs verts.** Symptome remonte a `theo-archi`, forme du garde-fou pas de mon ressort |
| **Le sens d'un indice** (`EXC-R10`) | **aucun**, `yanis-qa` le mesure et le dit (son rapport, section 6). **Un indice qu'un lot futur recopierait d'une autre tache repasserait sans un rouge** - le defaut exact de s34 |
| **La famille du bouton au rabais** sur EMC | **aucun, faux vert demontre par `yanis-qa`** (son rapport, section 4 : un distracteur reel remplace par *"ne rien dire du tout"*, garde-fou vert 7/7). Seule la forme litterale *"a personne"* est gardee |
| **`ZON-15`** - qu'un libelle tienne dans son cadre | **aucun, et `ADR-0092` decide que ce sera ainsi.** C'est moi, a l'oeil, paquet par paquet. **Premiere fois que cette garde sert : six zones sur six.** |
| **`ZON-16`** - la forme d'un libelle positionnel | **aucun** |
| **La paraphrase** qu'`ADR-0091` clause 2 ne voit pas (*"avant tout le reste"* pour *"le premier"*, *"le tout dernier"* pour *"le troisieme"*) | **aucun.** `ADR-0091` le dit lui-meme ; je demande qu'on ne l'ecrive nulle part autrement |
| **L'exactitude d'un texte alternatif contre son dessin** (`FRI-R6`) | **aucun.** Aucun test ne lit un dessin |
| **La passe image effacee** | **aucun.** Mes chiffres de ce sprint : **5 sur 11** sur la frise, **1 sur 6** sur le plan |
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun**, et la scission n'en cree pas |
| **La fidelite d'une scission de paquet** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main sur 110 items ce jour ; **la prochaine scission n'aurait personne pour le dire** |
| **Deux garde-fous qui ne sont plus a moi seule, et je le porte au credit du lot** | `ADR-0091` clauses 1 et 2, et l'ecran de fin : **mis a l'epreuve par mutation du fichier de production**, pas d'une fixture (`yanis-qa`, sections 3.1 et 3.2). Et `EXC-R11`/`EXC-R12` ont recu leurs garde-fous de regression apres le defaut de tracabilite que `yanis-qa` a remonte |

---

## 7. Ce que je route, et ce qui devrait rouvrir du travail avant la cloture

### Rien ne bloque la cloture de s35

**Le refus de `S35-QLM11-PLANCLASSE` ne bloque pas le sprint** : il renvoie l'increment a
`camille-po`, qui en fait du backlog. Les cinq autres stories ont leur Done pedagogique.

### Ce qui doit etre traite avant la cloture, parce que ce sont des ecritures et non du code

| Point | Destinataire | Pourquoi maintenant |
|---|---|---|
| **Le mouvement `QLM-CP-14` -> `couvert-valide`, et la chute de la mention de couverture partielle** | `camille-po` | C'est le seul mouvement de matrice du sprint, et il est autorise |
| **`ZON-16` reformulee** (section 3.1) | `camille-po` | La condition opposable au prochain lot doit etre celle qui est juste, pas celle qui refuse son propre exemple |
| **Les huit reserves neuves en stories** : `SCO-R4`, `EXC-R13`, `EXC-R14`, `FRI-R6`, `FRI-R7`, `PCL-R1`, `PCL-R2`, `ANG-R8`, `CONS-5` | `camille-po` | Ce sont des motifs ; j'en ai donne l'objet, le mode de defaillance, le perimetre et le prix. **`FRI-R6` est la plus lourde des neuf** |

### Ce qui est route pour la suite, sans urgence de cloture

| Point | Destinataire |
|---|---|
| **La geometrie des six cadres du plan, ou la place du libelle** - avec les deux sorties que j'accepte et celle que je refuse | `ines-design` (propose), `theo-archi` (forme technique) |
| **Le pave de `.zone`**, 20 px horizontaux sur un cadre de 63 px - symptome, pas solution | `theo-archi` |
| **Le trou de `TailleDesTextesEtBoutonsTest`** : une liste de sept selecteurs ecrits a la main, et aucune vue sur un texte qui ne declare pas sa taille | `theo-archi` |
| **Qu'aucun test ne compare un paquet a son ancetre** apres une scission | `theo-archi` |
| **Qu'on cesse d'ecrire que la clause *"a personne"* couvre la famille du bouton au rabais** : elle couvre un incident | `theo-archi` |
| **`SCO-R2`, `SCO-R3`, `FRI-R3`, `FRI-R4`, `FRI-R5`, `ANG-R1`, `ANG-R2`, `CONS-1`, et les `HG-R*` ouvertes** | `camille-po` - inchangees, ouvertes, non bloquantes, hors perimetre de ce sprint |

---

## 8. Fichiers que j'ai ecrits ou modifies

| Fichier | Etat |
|---|---|
| `squad/conformite/PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md` | **neuf** - verdict de `S35-SCO1-ECRAN-FIN` |
| `squad/conformite/FICHE-designation-plan-de-la-classe.md` | **neuf** - premiere fiche de ce jeu, verdict `REFUSE` |
| `squad/conformite/FICHE-bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1.md` | **neuf** |
| `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md` | **neuf** |
| `squad/conformite/FICHE-bandit-manchot-histoire-geographie-nommer-en-geographie-cm1.md` | **neuf** |
| `squad/conformite/FICHE-bandit-manchot-histoire-geographie-lire-un-document-cm1.md` | **neuf** |
| `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` | **bloc ajoute**, troisieme passage. Aucune ligne effacee |
| `squad/conformite/FICHE-designation-frise-de-la-journee.md` | **bloc ajoute**, second passage, **premier `VALIDE` de cette fiche**. Le refus de s34 reste lisible |
| `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` | **bloc ajoute**, quatrieme passage |
| `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` | **bloc ajoute** - devient la fiche de l'ancetre, renvoie aux quatre. **Les cinq verdicts et les vingt-et-une reserves restent lisibles et opposables** |
| `squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/verdicts-nadia.md` | **ce fichier** |

**`review.md` de s35 n'existe pas encore** : c'est la ceremonie de `sofia-sm`, et je ne l'ecris pas a
sa place. La section 1 de ce document est la conclusion a y reprendre.

**Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'a ete modifie.** Je les
ai lus - `Score`, les trois sessions, `retour.html`, `manche.html`, `designation.html`, `recreo.css`,
`TailleDesTextesEtBoutonsTest`, les paquets `yaml`, les trois dessins - pour verifier des contenus et
des tailles, jamais pour les changer.
