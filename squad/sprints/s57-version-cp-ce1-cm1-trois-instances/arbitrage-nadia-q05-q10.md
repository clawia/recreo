# s57 - arbitrage de `nadia-prof` sur les reponses de l'utilisatrice a `Q-05`, `Q-07` a `Q-10`

- **Auteure** : `nadia-prof`, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-21, 11:13 (UTC+02:00), lu par `date`. **Arbre** : `round-two` a `6927d926`. Non suivis ou modifies avant
  ecriture : `preparation-palier-3-ce1-camille.md` (modifie), `DEC-0123-...` (non suivi, en cours d'ecriture par `camille-po`).
- **Reponses de l'utilisatrice, 2026-10-21, relayees par l'agent lanceur** : *"Q5 - ajouter une grille pour infos a l'enfant.
  Q7 - demander a nadia prof. Q8 - oui, premisse etendue. Q9 - la saisie clavier ne corrige pas le mot donc plus de refus.
  Q10 - ouverture instruction."* `Q-06` (*"hors contexte pour le moment"*) n'est pas a moi ; j'en tire une consequence en 1.4.
- **Audit degrade, declare** : rien n'est joue. Ce document arbitre des formes et ecrit des conditions ; il ne prononce aucun
  Done, ne modifie aucun paquet, ne touche ni la matrice, ni `DEC-0123`, ni le registre. Il n'est pas commite.
- **Identifiants** : **aucun identifiant de condition ou de reserve neuf**. Je ne cite que des identifiants deja ecrits
  (`DEP-CE1-1` a `-4`, `R6`, `VOI-1`, `TRV-CE1-1`, `ART-1` a `-5`, `CON-1`, `NRC-CE1-1`, `PAR-2`, `PAR-13`, `SAI-1`...). Les
  conditions de Done ci-dessous sont des listes numerotees de ce document, pas des identifiants.
- **Lu pour ce document** : mon arbitrage du 2026-10-19 (sections 1, 3.2, 4) ; `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`
  sections 0 et 1 ; `ce1-deplacements-quadrillage.yaml` et `cp-deplacements-quadrillage.yaml` en entier ; `ADR-0080` (passage du
  quadrillage au palier 1, section 6 "A3 pour s52") ; `saisie.html` lignes 60 a 90 ; `ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`,
  troisieme amendement du 2026-08-25 et note du 2026-10-01 ; `DEC-0047` section 2 ; `ADR-0058` section 5 ; `DEC-0068` amendement
  A.1 et A.2 ; `cp-mot-dicte.yaml` en-tete ; `competences-ce1.md` d'arts plastiques ; en-tete de `squad/corpus/oeuvres-domaine-public.yaml` ;
  criteres de s57, 4.2 et 11.3 ; `composition-palier-3.md` en-tete.

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| N1 | `Q-05`, `MAT-CE1-14` avec grille montree | **Le refus d'avance du paquet textuel tombe des que la grille est montree. `DEP-CE1-2` n'est leve en entier que par une designation sur la grille.** Grille d'information et choix textuel : plafond `en cours`. Designation, plus codage illustre : plafond `couvert-valide` | 1 |
| N2 | `Q-07`, condition 4 | **Tient pour tout s57.** `-09` puis `-08`, un par sprint, a partir de s58, sur l'instance du francais CE1 | 2 |
| N3 | `Q-08`, premisse etendue | `-27` *ecouter* : servable, plafond `en cours`, terrain neuf. `-29` : la premisse fournit la voix, `R6` bloque encore l'ecriture | 3 |
| N4 | `Q-09`, saisie au CE1 | **Mon refus tient aujourd'hui. Il tombe sous une condition** : que le fait de l'utilisatrice devienne vrai du produit. Une question lui est a poser | 4 |
| N5 | `Q-10`, arts plastiques CE1 | **Instruction ouverte.** Ecrit ici : forme, corpus acceptable, conditions d'une oeuvre montree, droits. Il manque le corpus | 5 |
| N6 | Palier | **Rien de ce que j'ouvre n'entre au palier 3**, compose a 8,0 sur 8,0 (`composition-palier-3.md`, `CO1`) | chaque section |

---

## 1. `Q-05` - `MAT-CE1-14` avec un quadrillage montre a l'enfant

### 1.1 Avant d'arbitrer : je verifie ce que j'ai moi-meme ecrit

- **Ma citation de `DEP-CE1-2` le 2026-10-19 est exacte** (s43 lot 3, section 1) : *"Le quadrillage est montre des qu'un item demande
  de se reperer ou de se deplacer. Designation sur dessin, grille d'au moins 4 x 4 et d'au plus 6 x 6 cases, toutes de meme taille et
  toutes designables."*
- **Le paquet, lui, attribue a `DEP-CE1-2` un autre contenu** : son en-tete ecrit *"DEP-CE1-2 : toute case citee [...] est dans le
  quadrillage de 4 x 4"*. C'est une partie de `DEP-CE1-4` (*"chacun menant a une case reelle"*). L'en-tete se declare donc conforme a
  une clause qu'il ne tient pas. A corriger par le developpeur qui reecrit le paquet, dans le meme commit.
- **Ma propre barre sur une grille sans designation existe deja**, citee par `ADR-0080` : *"Un enfant qui repond "A3" en lisant trois
  etiquettes n'a pas montre ou il est ; un enfant qui appuie sur une case l'a montre."* Je ne la releve pas et je ne la baisse pas.

### 1.2 L'obstacle `DEP-CE1-2` est-il leve ?

Deux lectures de *"une grille pour infos a l'enfant"*. `camille-po` les nomme aussi (`DEC-0123`, 2.1). Je tranche pour les deux :

| Forme | `DEP-CE1-2` | Plafond | Pourquoi |
|---|---|---|---|
| (a) Grille montree comme illustration, reponse au choix parmi trois textes (`D3`, `C4`, `D1`) | **leve en partie** : le quadrillage est montre, la designation manque | `en cours` | l'enfant voit enfin la grille : le refus d'avance du paquet textuel (2026-10-19, 3.2) ne vaut plus. Mais il repond par une etiquette, ce que ma phrase citee en 1.1 refuse comme preuve de reperage |
| (b) Grille montree, l'enfant **appuie sur la case d'arrivee** (designation, mecanique servie) ; les items de codage gardent trois codes en texte sous la grille | **leve en entier** | `couvert-valide`, avec la mention de s43 mot pour mot | c'est la forme de s43, table *"se reperer, se deplacer : designation sur un quadrillage dessine ; coder : choix textuel illustre"* |

Le choix entre (a) et (b) est a `camille-po` (story), le moyen a `theo-archi`. (b) a un precedent valide : `designation-symetrie-ce1`,
grille carree, `couvert-valide` le 2026-10-06.

**Fait route a `theo-archi`, sans le trancher** : `DEC-0120` A.35 (et `DEC-0112` 5.3) conditionnent `MAT-CE1-14` a `A3` d'`ADR-0080`.
La section 6 de cet `ADR` ecrit qu'`A3` ne bloque pas `MAT-CE1-17`, parce que ses dessins sont carres a 352 px. Une grille de
4 x 4 a 6 x 6 est carree. Si le meme raisonnement vaut, la dependance a `A3` est perimee pour cette ligne. C'est a lui de le dire.

**Consequence de `Q-06`** : la grille est un dessin. `DEC-0123` (3.2, point 4) la tient hors du retrait des dessins ; le cliquet de
poids des dessins, plein selon `preparation-palier-3-ce1-camille.md` (`Q-06`), est a `theo-archi`.

### 1.3 Conditions de Done d'un paquet de deplacement avec grille

1. `DEP-CE1-1` a `-4`, tels qu'ecrits en s43, sans changement. La grille fait de 4 x 4 a 6 x 6 cases de meme taille.
2. **La case de depart est marquee sur le dessin**, et l'enonce ne la nomme pas par sa coordonnee seule quand la grille porte ses
   etiquettes : sinon la tache redevient une lecture de libelle (`DEP-CE1-3`).
3. **Forme (b)** : chaque case est une zone designable ; aucune zone ne porte de libelle qui nomme la cible (`ZON-10`) ; zones
   declarees dans l'ordre de lecture de l'image (`ZON-11`, que je garde seule).
4. **Le codage** : la grille est montree sur chaque item de codage, avec le depart et l'arrivee marques ; trois codes de meme longueur
   (`DEP-CE1-4`).
5. **Les axes** : les etiquettes de colonnes et de lignes sont dessinees sur la grille, colonnes de gauche a droite, lignes de bas en
   haut, la meme convention sur tous les items. Les indices qui disent *"Dessine le quadrillage"* disparaissent : il est montre.
6. Communes : `LON-CE1-1`, `-2`, `TRV-CE1-1` a `-7`, lecture complete avant verdict (2026-10-19, 4.1), distracteurs verifies pas a pas.
7. **Mention** : (b) celle de s43, mot pour mot ; (a) la meme, suivie de *"; se reperer et se deplacer servis sans designation, la
   ligne reste en cours"*.
8. **Refus ecrits d'avance** : un item de deplacement sans grille ; une grille plus grande que 6 x 6 ; un distracteur hors de la grille.

### 1.4 Prix indicatif et palier

- **(a)** un lot illustre : **1,5**. **(b)** deux paquets (designation, codage illustre) : **1,5 + 1,5 = 3,0**, ou un seul lot de 1,5
  si un seul paquet porte les deux moities, ce que le contrat de contenu decide (`theo-archi`). Application de mon bareme, pas mesure.
- Pas un terrain neuf au sens de `PAR-13` : la designation sur grille a rencontre du contenu reel (`designation-symetrie-ce1`).
- **Palier** : pas le palier 3 (plein). Un palier suivant ou s58, sur l'instance qui porte les mathematiques CE1 (`PAR-2`).

---

## 2. `Q-07` - la condition 4 sur `FRA-CE1-08` et `-09`

L'utilisatrice me renvoie la question. **La condition 4 tient pour tout s57**, pour le motif ecrit le 2026-10-19 (3.2) : elle dit *"jamais
dans le sprint ou une autre instance audite le paquet illustre d'`ART-CP-10`"*, et je l'ai confirmee le 2026-10-13 *"quelle que soit
sa duree"* (criteres, 11.3, condition 4). Le rejeu a eu lieu dans s57. La lever parce qu'il est fini, ce serait deplacer mon poteau.

**Quand un lot s'ouvre** : a s58, **au plus un des deux par sprint** (ma regle de s55, 1.1 : un lot sans cousine par sprint), sur
l'instance qui porte le francais CE1, et pas dans un sprint ou une autre instance audite un paquet sans cousine. **`-09` d'abord.**

**Conditions de Done, deja ecrites, rappelees sans changement** :

1. `-09` : `RLP-CE1-1` a `-3` et la decision `E4` (2026-10-19, 3.1) : une explication sur chacun des 20 items, qui redonne la phrase
   juste entiere entre chevrons ; l'en-tete `DEGRADE` corrige dans le meme commit. Plafond `couvert-valide`.
2. `-08` : `OMP-CE1-1` a `-3`, choix parmi trois phrases. Plafond `couvert-valide`.
3. Communes : 4.1 du 2026-10-19.

---

## 3. `Q-08` - la premisse de l'adulte etendue au CE1, pour `FRA-CE1-27` et `FRA-CE1-29`

**La borne que je respecte** (`DEC-0068`, A.1, utilisatrice, 2026-09-01) : l'adulte lecteur est reserve aux exercices ou la voix est
la competence. Les deux lignes la remplissent : `-27` dit *"un texte lu par l'adulte"*, `-29` *"sous la dictee"*. **L'extension ne
vaut pour aucune autre ligne de CE1**, ni pour la lisibilite d'aucun paquet.

**Ce que je modifie, a la date de ce document** : `TRV-CE1-1` (*"aucun champ a voix haute au CE1"*) admet desormais le champ
`texteAVoixHaute` **sur `FRA-CE1-27` et `FRA-CE1-29` seules**. Partout ailleurs au CE1, il reste refuse. L'annotation dans le fichier
qui porte `TRV-CE1-1` est a faire par moi, dans un commit a moi.

### 3.1 `FRA-CE1-27`, *ecouter et comprendre un texte lu*

- **Ce qui devient servable** : l'adulte lit un texte court que l'enfant ne voit pas ; l'enfant lit seul une question et trois
  propositions et repond au choix.
- **Ce que le produit dit a l'adulte**, dans la zone ou il lit son texte : le texte a lire ; *"lisez le texte, deux fois si l'enfant le
  demande"* ; *"ne lisez pas la question ni les reponses, ne montrez pas la bonne"*. `VOI-1` : l'adulte prononce le stimulus, jamais une
  proposition.
- **Ce qui reste non couvrable** : *s'exprimer a l'oral*. Aucun dispositif ne verifie ce que dit l'enfant, et je n'en demande aucun.
- **Conditions de Done** :
  1. Le texte lu n'est jamais affiche a l'enfant, ni avant ni apres sa reponse ; la question ne se repond pas sans l'avoir entendu (je
     joue chaque item texte masque, comme pour `-07`).
  2. Le texte a lire tient en cinq phrases au plus, vocabulaire de CE1 ; l'adulte le lit sans preparation.
  3. Question et propositions tiennent `LON-CE1-1` et `-2` : c'est l'enfant qui les lit.
  4. `NRC-CE1-1` contre `FRA-CE1-04`, `-06`, `-07` : la meme question posee sur un texte ecrit sert la lecture, pas l'ecoute. Je lis ces
     paquets a l'audit.
  5. Mention : *"ecouter et comprendre un texte lu par l'adulte, reponse au choix ; 's'exprimer a l'oral' reste non couvert"*.
- **Plafond `en cours`. Prix 1,0** (lot textuel). **Terrain neuf** (`PAR-13`) : un texte entendu n'a jamais rencontre mes conditions,
  le CP ne fait entendre que des mots. Il ne s'ouvre pas tant qu'A' existe (criteres, 11.3, condition 5) : **pas a s57**.

### 3.2 `FRA-CE1-29`, *ecrire sous la dictee des mots et des phrases courtes*

- **Ce que la premisse donne** : la voix. L'adulte dit le mot, sous `VOI-1`, comme pour `cp-mot-dicte`.
- **Ce qu'elle ne donne pas** : l'ecriture passe par la saisie, et `R6` s'y applique (section 4). **La ligne reste non servable tant
  que `R6` tient.**
- **Si `R6` tombe** (section 4) : servable pour **les mots seuls**. Les *phrases courtes* demandent un mode strict sur les accents et les
  accords. Or `SAI-1` tolere l'accent, et le cout d'un accent sur un clavier de tablette reste une question ouverte (`DEC-0046`,
  section 3) : elles restent non couvertes.
- **Conditions de Done** (si `R6` tombe) : les conditions `VOI-9` de `cp-mot-dicte`, transposees aux mots du CE1 ; aucun mot dont
  l'orthographe juste tient a un accent tant que `SAI-1` le tolere ; aucun item ne reprend un mot de `cp-mot-dicte` (`NRC-CE1-1`) ;
  mention *"des mots sous la dictee ; les phrases courtes restent non couvertes"*.
- **Plafond `en cours`. Prix 1,0.** Ouvrable au plus tot au sprint qui suit la levee de `R6`.

---

## 4. `Q-09` - la saisie alphabetique au CE1 pour `FRA-CE1-21`

### 4.1 Le fait, verifie dans le depot

- `DEC-0047`, section 2 (2026-08-25) : l'utilisatrice accepte *"un clavier virtuel qui corrige et complete"* sur les deux appareils
  de `DEC-0040`. *"Les deux options qui l'auraient evitee (neutraliser la correction, limiter la saisie au clavier physique) sont
  ecartees."*
- `saisie.html`, lignes 71 a 74 : *"autocomplete="off" n'est PAS une neutralisation de la correction automatique du clavier, que
  l'utilisatrice a acceptee le 2026-08-25."* Le champ, ligne 87, ne porte aucun autre attribut sur la correction.
- `ADR-0058`, section 5 : *"Je ne reintroduis aucune neutralisation de la correction automatique, sous aucun nom."* Et : le serveur ne
  connait que la chaine qu'il recoit.

**Le produit ne garantit donc pas aujourd'hui que le clavier ne corrige pas le mot.** C'est vrai sur un ordinateur a clavier
physique. Sur la tablette visee, c'est le contraire de ce que `DEC-0047` a accepte.

### 4.2 Mon refus : il tient aujourd'hui, et il tombe sous condition

`R6` (2026-08-25) refuse une ligne qui fait produire les lettres d'un mot *"tant que les deux appareils sont vises avec la correction
acceptee"*. Je l'ai ecrit levable : *"que la correction automatique cesse d'etre acceptee, ou que cette ligne ne soit servie que sur un
appareil ou elle ne s'applique pas"*.

**Enchainement** :

1. Si la phrase de l'utilisatrice decrit le produit tel qu'il est, elle ne leve pas `R6` : le fait est faux sur la tablette (4.1).
2. Si elle dit une decision, revenir sur `DEC-0047` section 2 pour que la saisie ne soit plus corrigee, alors `R6` tombe. Ce n'est
   pas moi qui baisse ma barre, c'est la condition que j'ai ecrite qui est remplie.
3. Une neutralisation ne vaut que si elle est **mesuree**. Un attribut de champ est une demande faite au navigateur et au clavier,
   pas une garantie : un clavier virtuel, surtout s'il est installe a part, peut l'ignorer, et le serveur ne voit pas ce qu'a fait
   l'appareil (`ADR-0058`, section 5). Quels attributs poser, et ce qu'ils obtiennent sur les deux appareils de `DEC-0040`, est a
   `theo-archi`. Il le dit avec une mesure faite sur les appareils, pas avec la specification seule.
4. La levee vaut **pour toute la saisie**, pas pour `-21` seule : `FRA-CE1-29` (3.2), `LVE-CM1-06` (refusee a s45) et l'omission de
   `FRA-CP-09`, servie sans examen de `R6` (note du 2026-10-01). Elle ferme cette omission.

**La question a poser a l'utilisatrice** (`camille-po`, `DEC-0123` section 7) : *"Le 2026-08-25, vous avez accepte que le clavier de
la tablette corrige les mots tapes, et refuse qu'on l'en empeche (`DEC-0047`, section 2). Votre reponse veut-elle dire que vous revenez
sur ce choix, pour que le champ de saisie demande au clavier de ne plus corriger, en sachant que certains claviers de tablette peuvent
ne pas obeir ? Ou bien que la saisie au CE1 ne se fera que sur un ordinateur a clavier physique ?"* Un oui a l'une des deux leve
`R6`, a la condition du point 3 pour la premiere.

### 4.3 Conditions de Done de `FRA-CE1-21` en saisie, une fois `R6` leve

`-21` n'est pas une ligne ou la voix est la competence : **elle ne prend pas l'adulte lecteur** (borne du 2026-09-01).

1. Une phrase a trou dont le sens n'admet qu'un mot invariable ; le mot n'est ecrit nulle part a l'ecran avant la reponse (*sans
   modele*).
2. Aucun mot dont l'orthographe juste tient a un accent (*tres*, *deja*, *la*, *ou*...) tant que `SAI-1` le tolere : sinon l'accent
   n'est pas evalue.
3. Les formes acceptees ne contiennent aucune faute d'orthographe du mot vise (`SAI-6`, `SAI-7`). Je les relis toutes.
4. Le paquet `ce1-mots-invariables` est reecrit en type `saisie`, et son en-tete `DEGRADE` est retire dans le meme commit.
5. Communes : 4.1 du 2026-10-19, `NRC-CE1-1` contre `FRA-CP-09`.

**Plafond `couvert-valide`**, avec la mention *"mots sans accent seuls tant que l'accent est tolere"*. **Prix 1,0.** Pas au palier 3 ;
hors des groupes X et Y (2026-10-19, 1.1), elle demande une instance qui la porte, ce que la composition decide.

---

## 5. `Q-10` - instruction d'`ART-CE1-01` et `ART-CE1-02`, ouverte

### 5.1 Ce que j'ecris des maintenant

**Les lignes**, mot pour mot (`competences-ce1.md` d'arts plastiques) :

- `ART-CE1-01` : *"Reconnaitre, devant une oeuvre montree, comment elle a ete faite (peinte, dessinee, photographiee)."*
- `ART-CE1-02` : *"Comparer deux oeuvres montrees ensemble qui representent le meme sujet, et dire ce qui les distingue (la facon dont
  chacune est faite, ce que chacune montre du sujet)."*

**La forme** : `-01`, une reproduction par item, la forme d'`ART-CP-09` (`couvert-valide`). `-02`, deux reproductions par item, la
forme d'`ART-CP-10`, **VALIDE** au rejeu du 2026-10-16. Aucune mecanique neuve. Aucun son (`DEC-0120` A.27).

**Le corpus acceptable** :

1. `-01` : les trois facons sont representees, **au moins quatre oeuvres par facon**, pour que chaque facon soit bonne reponse
   plusieurs fois sans revenir a la meme image.
2. `ART-1` sur la facon de faire : **aucune facon ne se reconnait a sa seule teinte** sur l'ensemble du corpus. Au moins un dessin et
   une photographie monochromes, de ton voisin, que seul le trait, l'aplat ou le grain separent. Un corpus ou toutes les photographies
   sont sepia et aucun dessin ne l'est est refuse.
3. `-02` : **au moins trois paires** d'un meme sujet faites de deux facons (par exemple un paysage peint et un paysage photographie),
   aucune paire reprise d'`ART-CP-10` (`NRC-CE1-1`).
4. `ART-5` : plusieurs epoques et plusieurs aires, au moins une artiste femme.

**Les conditions d'une oeuvre montree** :

1. `ART-4` / `CON-1` : propositions masquees, l'item ne se repond pas ; l'enonce ne decrit jamais la facon de faire.
2. La reproduction laisse voir le trait ou le grain a la taille rendue. Je juge sur l'ecran servi, pas sur le fichier source.
3. Propositions de `-01` : *peinte*, *dessinee*, *photographiee*, rien d'autre. Un item dont la reponse est *statue* sert le CP.
4. `-02` : la bonne reponse dit une difference visible sur les deux images ; jamais une question de gout (`ART-2`).

**Les droits, `ART-3`** : chaque oeuvre identifiee par titre, auteur, date et lieu de conservation, dans le registre
`oeuvres-domaine-public.yaml` (`ADR-0082`), avec ses deux preuves : oeuvre libre (auteur mort avant 1900, ou anonyme anterieure a
1900) et reproduction en CC0. **Une photographie dont l'auteur est mort avant 1900 entre dans la premiere preuve.** Pour une oeuvre
qui est elle-meme une photographie, savoir si la double preuve s'applique telle quelle est a `theo-archi` (son avis du 2026-10-19, 1.5, la nomme sans la trancher).
L'exactitude de chaque attribution, je la verifie moi-meme.

**Mention exigee d'avance**, inchangee : *"reception d'oeuvres seule ; aucune pratique plastique n'est servie par ce produit"*.

### 5.2 Ce qui manque

- **Le corpus.** Les douze reproductions du registre ne contiennent ni dessin ni photographie, ni deux oeuvres d'un meme sujet faites
  de deux facons. Le choisir est un travail a part, sur le modele de `CHOIX-OEUVRES-s56-art-cp-10.md`, puis la double preuve pour
  chaque fichier.
- **La reponse de `theo-archi`** sur la double preuve d'une photographie, avant toute photographie dans le corpus.
- `Q-06` retire les dessins *produits par le produit* : il ne touche pas des reproductions d'oeuvres. Je le note pour que personne
  n'applique le retrait ici.

### 5.3 Plafond, prix, palier

- **Plafond `couvert-valide`** pour chacune, avec la mention de 5.1.
- **Prix** : un lot illustre, **1,5** chacun ; `-02` a deux images par item, je le maintiens a 1,5, comme `ART-CP-10`.
- **Terrains neufs** (`PAR-13`) : pas tant qu'A' existe, **pas a s57**. Au plus une des deux par sprint. `-01` d'abord : `-02` emploie
  les mots de `-01` (*"dite avec les mots d'`ART-CE1-01`"*).

---

## 6. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Le choix (a) ou (b) pour `MAT-CE1-14` (1.2) ; la question de 4.2 a l'utilisatrice ; les stories, quand un palier les recoit |
| `theo-archi` | La dependance de `MAT-CE1-14` a `A3`, peut-etre perimee pour une grille carree (1.2) ; un ou deux paquets pour (b) (1.4) ; le cliquet de poids des dessins ; la mesure d'une neutralisation de la correction sur les deux appareils, si l'utilisatrice la decide (4.2) ; la double preuve d'une photographie (5.1) |
| un developpeur | L'en-tete de `ce1-deplacements-quadrillage`, qui attribue a `DEP-CE1-2` un contenu qui n'est pas le sien (1.1), quand le paquet sera reecrit |
| moi | Annoter `TRV-CE1-1` pour `-27` et `-29` (section 3) ; annoter le complement de s55 sur `R6` (deja route le 2026-10-19) ; le choix du corpus d'arts plastiques CE1, quand une story le demande |

**Ce dont je reste la seule garde, ajoute ici** : que le texte de `-27` ne soit jamais affiche a l'enfant ; qu'une neutralisation du
clavier, si elle est decidee, tienne sur l'appareil de l'enfant, ce qu'aucun test serveur ne voit ; `ART-1` sur la facon de faire.

**Signe** : `nadia-prof`, s57, 2026-10-21.
