# Arbitrage préalable du 2026-10-04 - s50, `S50-ART` : décrire une œuvre, rapprocher deux œuvres

- **Auteur** : `nadia-prof`, instance B (`DEC-0109` section 3). **Date** : 2026-10-04. **Sprint** : s50.
- **Ce qui m'est posé** : Q1, Q2, Q4 et Q5 de `squad/rapports/avis-faisabilite-s50-art-cp.md`
  (section 8, `theo-archi`, commit `4ea72382`), et les points 3, 4 et 5 que `ADR-0082` m'a routés le
  2026-09-05 (section "Ce que je route, sans le décider"), repris par `DEC-0109` section 4.
- **Ce document ne prononce aucun Done**, n'écrit ni backlog ni garde-fou, et **ne crée aucun
  identifiant de condition**. Il applique des conditions déjà écrites (`ART-1`, `ART-2`, `ART-4`,
  `CON-1`, `CON-5`, `REC-1` à `REC-4`) et mes critères de choix du vivier
  (`squad/corpus/CHOIX-oeuvres-candidates-T5.md`, sections 3 et 5). Aucun paquet n'existe : aucune
  barre n'est relevée.
- **Je n'ai rien joué : il n'y a rien à jouer.** J'ai regardé les onze fichiers du vivier sous
  `adapters/adapter-web/src/main/resources/static/oeuvres/`, à `df135abd` (après le
  redimensionnement A1 de `theo-archi`), dont `oeuvre-grande-vague.jpg` à 176 px, la taille de rendu.
  J'ai relu `competences-cp.md`, `competences-cm1.md` (`ART-3` à `ART-5`), `CON-1` à `CON-5`
  (arbitrage de s25), `REC-1` à `REC-4` (arbitrage de s27), `DEC-0109` avec son amendement du jour
  (section 9, `75be883c`), la story `S50-ART` et la fiche de `ART-CP-05`.

---

## Q1 - `ART-CP-10` : une désignation en texte ne suffit pas

### Décision

**Rapprocher deux œuvres demande que l'enfant voie les deux en même temps.** Un item qui montre une
œuvre et propose les autres en texte (*"le tableau de la chambre"*) est refusé. **`ART-CP-10` ne
s'écrit pas dans `T6`.** Le paquet `S50-ART` ne porte que `ART-CP-09`.

### Motif

1. **Les trois paires ont été choisies pour une propriété qui ne se voit qu'en regardant.** Je l'ai
   écrit le 2026-10-03 (`CHOIX-oeuvres-candidates-T5.md`, section 5) : *"une paire qu'un enfant de
   CP peut rapprocher en regardant"* - la même touche (chambre, champ de blé), la même manière de
   dessiner l'eau (vague, averse), la même lumière de fenêtre (femme au luth, jeune femme à
   l'aiguière). Une étiquette écrite évoque un sujet, jamais une touche.
2. **En texte, chaque paire tombe dans l'un de deux défauts.** Chambre et champ de blé n'ont aucun
   sujet commun : l'enfant devine. Vague et averse, luth et aiguière partagent un sujet (de l'eau,
   une dame près d'une fenêtre) : l'enfant rapproche par le sujet, c'est-à-dire qu'il refait
   `ART-CP-09`, et la ligne serait comptée sur un geste qu'une autre ligne compte déjà.
3. **`CON-1` à l'envers.** En effaçant la seconde image, il ne reste qu'une description de l'œuvre à
   rapprocher : c'est la contrefaçon par description que mon jugement de faisabilité refuse sur
   cette ligne même (`competences-cp.md`, ligne 10 du tableau).

### Correction de mon propre constat

Le 2026-09-03, j'ai classé la ligne 10 *"`non couvert` - même motif que la ligne 9"*, c'est-à-dire
*image d'œuvre requise*. **C'était inexact** : il manque à la ligne 10 l'image d'une œuvre **et** la
vue simultanée de deux œuvres. `ADR-0082` (décision 5) et `DEC-0109` (sections 1 et 6) ont engagé les
deux lignes dans un seul paquet sur la foi de cette phrase. L'erreur est la mienne. Une note datée
est ajoutée sous mon jugement de faisabilité dans `competences-cp.md`.

Ce n'est pas une barre relevée : *"en regardant"* est écrit depuis le 2026-10-03, avant tout paquet.

### Ce que j'exige de la forme qui servira la ligne, et ce que je n'exige pas

- **Exigé** : les deux œuvres visibles ensemble, à une taille où la propriété rapprochée se voit.
  Point de vigilance que je nomme d'avance : j'ai écrit le 2026-10-03 (section 6, Q3 du document de
  choix) qu'une partie de la touche de Van Gogh ne se voit pas à 352 px. La paire chambre - champ de
  blé est à juger à la taille réellement rendue, et peut sortir si la touche ne s'y voit pas.
- **Non exigé** : le moyen. Propositions en image, deux images par item ou autre chose : c'est à
  `theo-archi` (forme d'item neuve, `ADR` avant tout code, hors du tarif de `T6`).

### Ce que cela change pour `T6`

- `ART-CP-10` reste `non couvert` (constructible), **jamais** `non couvrable en l'etat`.
- **Les trois œuvres propres à `ART-CP-10`** (`oeuvre-champ-de-ble`, `oeuvre-averse-sur-le-pont`,
  `oeuvre-jeune-femme-aiguiere`) dormiraient sous O1 si aucun item ne les déclare. **Je les admets au
  vivier de `ART-CP-09`** : chacune a un sujet qu'un enfant de six ans nomme avec ses mots (un champ
  et de grands arbres, des gens sur un pont sous la pluie, une dame près d'une fenêtre). Les garder
  dans le paquet ou les retirer avec les dix-sept autres est le choix de `camille-po` ; les deux sont
  recevables pour moi.
- Le tarif de l'instance B reste **2,0** : le prix est celui du lot, pas de la ligne
  (`ARBITRAGE-PREALABLE-s28`, section 4.1). Je ne me déclare pas source des arts plastiques.

---

## Q5 - "déjà rencontrées" : aucun item ne dépend d'un autre item de la partie

### Décision

**Aucun item ne demande d'avoir vu une œuvre plus tôt dans la partie ou dans une session
précédente.** Le critère de la story *"l'une des deux œuvres de chaque paire est aussi celle d'un
item de `ART-CP-09` dans le même paquet"* décrit le paquet, pas ce que l'enfant a vu. Je ne le
compte pas comme *"déjà rencontrée"*.

### Motif

Une partie tire ses items au hasard parmi ceux qui ne sont pas encore sortis, sur environ dix manches
pour jusqu'à vingt items, et rien ne persiste entre deux sessions (`DEC-0028`). Un item qui suppose
une rencontre antérieure est donc, pour une partie des enfants, **impossible à réussir par
construction**. Un enfant qui échoue faute d'avoir tiré le bon item avant apprend qu'il s'est trompé
alors qu'il n'avait aucun moyen de savoir. Je le refuserais à la porte ; je le dis avant.

### Ce que la ligne peut fermer, écrit maintenant pour ne pas le découvrir au Done

- Avec la vue simultanée de Q1, **"rapprocher" ne dépend plus d'aucune mémoire** : la moitié se joue
  dans l'item lui-même.
- **"Reconnaître des œuvres déjà rencontrées" n'est servi par aucune forme existante.** Le programme
  pense aux œuvres vues en classe au fil de l'année ; le produit ne peut pas savoir lesquelles, et
  l'adulte qui accompagne l'enfant de CP (`DEC-0046`) ne remplace pas une rencontre qu'il n'a pas
  vue. Seule une garantie d'ordre à l'intérieur d'une partie (le partenaire sort avant l'item qui le
  rappelle) la servirait. Elle toucherait `ContentSource` dans `domain` : c'est à `theo-archi` d'en
  dire le prix si `camille-po` le demande. **Je ne la demande pas.**
- **Sans cette garantie, `ART-CP-10` fermera au mieux avec la mention**, sur le précédent des
  demi-lignes de la matrice : *"moitié 'rapprocher deux œuvres montrées ensemble' seule ; la moitié
  'reconnaître des œuvres déjà rencontrées' reste non couverte"*. Je ne réécris pas la ligne du
  référentiel pour qu'elle tienne dans le produit : la mention porte l'écart.

---

## Q4 - `ART-CP-09` sans les couleurs

### Décision

**`ART-CP-09` se sert sur le sujet, les formes et les matériaux.** Aucune couleur n'apparaît dans un
énoncé, une proposition, un indice ou un texte alternatif du paquet. L'intitulé du référentiel ne
change pas. Si la ligne est validée, elle l'est avec la mention : *"sujet, formes et matériaux seuls
; les couleurs d'une œuvre ne sont pas servies (`ART-1`)"*. Elle ne sera pas déclarée ligne entière.

### Motif

1. **La méthode de `ART-CP-05` ne se transpose pas.** Là-bas, la compétence était une connaissance :
   le nom d'une couleur, la règle d'un mélange, la couleur habituelle d'un objet connu. Elle se
   répondait sans rien percevoir. Ici, la compétence est de dire ce qu'on voit : la couleur d'une
   œuvre ne s'obtient qu'en la percevant, ce qu'`ART-1` exclut.
2. **La version "connaissance" est une contrefaçon.** *"De quelle couleur est le blé mûr ?"* devant
   les moissonneurs se répond sans regarder l'image : `CON-1` et `ART-4`.
3. **Ce n'est pas une règle nouvelle.** `ART-2` a énuméré le 2026-09-03 ce que les items observent :
   *"sujet, formes, matériaux"*. Les couleurs n'y étaient déjà pas.

### Règles pour le développeur

1. **Aucune proposition ne se distingue des autres par un mot de couleur.** *"Un hippopotame bleu"*
   est refusé, en bonne réponse comme en distracteur.
2. **Le texte alternatif est le même sur tous les items et ne nomme rien** (`CON-5`) : pour une ligne
   dont la compétence est de dire ce qu'on voit, nommer le sujet dans le texte alternatif donne la
   réponse. Précédent : `ce1-solides`. C'est ma réponse au piège 2 de l'avis.
3. **Matériaux, au niveau du CP** : la distinction recevable est celle qui se voit, un objet qu'on
   peut tenir ou poser (l'hippopotame) contre une image peinte. *"Faïence"*, *"toile"*, *"estampe"*
   ne sont pas du vocabulaire de CP et sont refusés en bonne réponse.

---

## Q2 et point 4 d'`ADR-0082` - le texte visible dans une reproduction

### Constat, sur les fichiers

- `oeuvre-grande-vague.jpg` : un cartouche d'écriture japonaise verticale en haut à gauche, et une
  colonne de signature à côté. À 176 px, on voit un bloc écrit, aucun signe ne se distingue.
- `oeuvre-averse-sur-le-pont.jpg` : un cartouche de titre en haut à droite, une signature avec cachet
  en bas à gauche, en japonais.
- **Un troisième cas que l'avis ne nomme pas** : `oeuvre-rue-sous-la-pluie.jpg` porte, en bas à
  gauche, une trace claire à l'emplacement de la signature du peintre, en alphabet latin. Elle est
  illisible à 352 px, donc à 176.
- `oeuvre-femme-au-luth` et `oeuvre-jeune-femme-aiguiere` : une carte murale, rien de lisible.

### Décision

**Recevable au CP, pour les trois.** Conditions, qui appliquent `CON-1`, `CON-5` et `ART-4` :

1. **Aucun énoncé, aucune proposition, aucun indice, aucun texte alternatif ne parle de ce texte**, et
   aucun item ne demande de le lire.
2. **Le fichier n'est pas recadré pour l'effacer.** Le cartouche fait partie de l'estampe ; le retirer
   serait servir une autre image que l'œuvre.

### Motif

Un enfant de CP ne lit pas le japonais, et la signature de Caillebotte ne se lit pas à la taille
servie : aucun de ces textes ne porte la réponse d'un item sur le sujet, les formes ou les
matériaux. Ce n'est pas non plus une confusion : une écriture qu'on ne sait pas lire est une chose
qu'on voit, et l'adulte présent peut dire *"c'est de l'écriture japonaise"*.

### Règle générale, pour le point 4 d'`ADR-0082` (CP et CM1)

Un texte visible dans une reproduction est recevable **sauf s'il donne la réponse d'un item**. Le
test est celui d'`ART-4` : **en ne lisant que le texte de l'image, la réponse est-elle trouvable ?**
Si oui, l'item est refusé. Au CP s'ajoute mon critère de choix du 2026-10-03 : aucun mot en alphabet
latin lisible à la taille de rendu. Au CM1, le test mordra sur les lignes d'histoire des arts dont la
réponse est un auteur ou une date (`HDA-CM1-04`, `HDA-CM1-06`) : une signature ou une date lisible y
est une fuite, jugée item par item à l'audit de `T7` et `T8`.

---

## Point 3 d'`ADR-0082` - `REC-1` à `REC-4` devant une œuvre

| Condition | Transposition à une reproduction |
|---|---|
| **`REC-1`** | **Pas dans sa lettre.** Devant un tableau, *"nommer l'image"* n'a pas de réponse unique. Transposée : propositions masquées, je réponds à la question de l'énoncé en regardant l'œuvre à 176 px ; si ma première réponse n'est pas la réponse attendue, l'item est refusé. **En plus : chaque distracteur est absent de l'image.** Un tableau contient beaucoup de choses, et un distracteur présent dans un coin (*"un arbre"* devant les moissonneurs) fait une seconde réponse défendable |
| **`REC-2`** | **Oui, transformée.** Ce que l'item demande se reconnaît sans hésiter à 176 px : le sujet principal, jamais un petit personnage ni un détail (moissonneurs, marché aux chevaux, visage d'Arcimboldo à vérifier à cette taille). Une action est recevable si elle est la seule de l'image : *"que font les gens ?"* devant les moissonneurs a deux réponses (couper le blé, se reposer), donc refusé |
| **`REC-3`** | **Oui, telle quelle.** C'est `ART-1`, voir Q4 |
| **`REC-4`** | **Oui, telle quelle.** Aucun item ne fait compter (*"combien de chevaux ?"*) |

Le cadre doré visible autour du marché aux chevaux (piège 6 de l'avis) ne fait pas partie de l'œuvre
mais ne trompe pas l'enfant : il est recevable tant qu'aucun item ne porte dessus.

---

## Point 5 d'`ADR-0082` - le prix de la vérification d'attribution

**Zéro pour `T6`.** Aucun item de CP ne porte sur l'auteur, le titre, la date ou le lieu (ma ligne 10
refuse *"qui a peint ?"*, `ART-4` refuse le titre), et le registre ne s'affiche pas (`ADR-0082`
décision 8). Une erreur d'attribution est donc invisible pour l'enfant de CP. Ce que je vérifie à
l'audit est la **fidélité** - l'image servie est bien l'œuvre, ni recadrée ni retournée -, et c'est
dans le passage de `REC-1` transposée, déjà payé par le supplément de 0,5. La question devient réelle
à `T8`, où l'époque et la civilisation sont la réponse : elle sera chiffrée alors.

---

## Ce qui bloque encore, et à qui

| # | Point | À qui |
|---|---|---|
| 1 | Réduire `S50-ART` à `ART-CP-09` : retirer le critère des trois paires et le besoin de `ART-CP-10`, retirer *"les couleurs"* du besoin de `ART-CP-09` | `camille-po` |
| 2 | Décider du sort des trois œuvres propres à `ART-CP-10` : dans le paquet de `ART-CP-09` (admises ici) ou retirées avec les dix-sept autres sous O1 | `camille-po`, puis le développeur |
| 3 | Motif de `ART-CP-10` dans la matrice : *"deux œuvres montrées ensemble requises"*, plus seulement *"image d'œuvre requise"* | `camille-po` |
| 4 | Si `camille-po` engage `ART-CP-10` : une forme qui montre deux œuvres ensemble, `ADR` avant tout code ; la garantie d'ordre de Q5 seulement si elle est demandée | `theo-archi` |

**Rien de pédagogique ne bloque l'écriture du paquet `ART-CP-09` une fois les points 1 et 2
tranchés.**

## Ce dont je reste la seule garde sur ce paquet

Aucun test ne vérifie : qu'aucune couleur n'apparaît dans un texte du paquet (Q4), que chaque
distracteur est absent de l'image et que ce qui est demandé se voit à 176 px (`REC-1` et `REC-2`
transposées), qu'aucun texte ne parle de l'écriture visible dans une estampe (Q2), et la fidélité de
chaque reproduction. Je les vérifierai en jouant, item par item, au Done pédagogique.
