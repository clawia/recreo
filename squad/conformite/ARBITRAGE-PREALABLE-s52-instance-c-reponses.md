# Arbitrage prealable de s52, instance C (mathematiques CE1) - reponses ecrites (U3) : la densite du
# reseau est admise jusqu'a 10 x 10, l'esquisse du quadrillage suffit a engager la completion

- **Auteure** : `nadia-prof`, instance C. **Date** : 2026-10-06. **Sprint** : s52, consigne B5, phase 1.
- **Arbre** : branche `s52-nadia-instance-c`, ouverte depuis `round-two` a `5b199825`
  (`bash outils/verifier-lignee.sh` : vert).
- **Ce qui m'est pose** : Q4 de l'avis de faisabilite de s52 (section 5) ; les quatre questions *"Pour
  `nadia-prof`"* de la section 5 des contraintes d'`ines-design`
  (`squad/design/contrainte-ines-s52-dessins-de-l-instance-c-mathematiques-ce1.md`, lu a `round-two` par
  `git show`, commit `7350fb07`, section 4 encore en esquisse) ; le plancher de zone d'`ADR-0080` A2bis.
- **Ce que ce document n'est pas** : un Done. Aucun paquet de C n'existe a cette heure ; je n'ai rien joue
  du jar. L'audit (phase 2) se fera sur le jar construit au sha que `lea-dev` livrera.

---

## 1. Densite du reseau de points (`MAT-CE1-06`, `MUL-CE1-5`) - la seule reponse bloquante

**Reponse : admise.** Disque plein de 10 px, ecart de 6 px, pas de 16 px, rendu 1:1, **jusqu'a 10 x 10**.
`lea-dev` peut passer du 5 x 7 au 10 x 10.

**Ce que j'ai regarde, et sa limite.** J'ai rendu dans mon scratchpad cinq reseaux generes a la geometrie
de la section 3.1 et 3.2 d'`ines-design` (3 x 5, 5 x 7, 6 x 8, 8 x 9, 10 x 10), sur le fond `#f4f7fb`, a
360 px de fenetre (`CHROME=<chrome> bash outils/mesurer-rendu.sh 360 <page locale> <png> 900`). Chaque point
se separe de ses voisins sans zoom, y compris sur le 10 x 10 (160 x 160 px) ; j'ai compte les rangees et
les colonnes du 10 x 10 sans me perdre. C'est un rendu de modele, pas le dessin servi : la verification
qui compte reste celle de l'audit, dessin par dessin, sur le jar.

**Ma barre est celle que j'ai ecrite a s43, et je ne la deplace pas.** `MUL-CE1-5` demande des elements
identiques, un reseau regulier, *"au plus dix rangees et dix colonnes"*, chaque element *"distinguable de
son voisin a la taille reelle de rendu"*. La geometrie proposee la tient. Je n'y ajoute aucune condition.

**Ce que je verifierai a l'audit, et pourquoi c'est ici que ca se joue.** Les distracteurs de `MUL-CE1-6`
(*une rangee oubliee*, *une rangee de trop*, *une colonne de trop*) sont exactement les erreurs de
comptage d'un enfant qui perd sa place dans une rangee. Un reseau ou l'on se perd ferait donc mesurer
la precision du comptage, pas le sens de la multiplication. A l'audit, je compte moi-meme chaque reseau a
sa taille servie, avant de lire les propositions. C'est l'application de *"distinguable"* et de
l'exactitude, pas une barre nouvelle.

**Hors de mon ressort** : le choix entre un `<circle>` par point et la variante `<path>` (poids, `FIG-4`)
est a `theo-archi`. Le rendu doit etre identique a l'oeil ; si ce n'est pas le cas, je le verrai en jouant.

---

## 2. Q4 de l'avis - le quadrillage 6 x 6 en esquisse suffit-il a engager les items de completion ?

**Reponse : oui, pour la partie pedagogique.** Les proprietes qui decident de `SYM-CE1-3` se lisent sur
l'esquisse de la section 4 :

1. **L'axe reste visible.** Axe plein de 2,4 unites sur la ligne de grille centrale, cases pleines
   retrecies a 12 unites : la maquette a montre le defaut (axe efface entre deux cases pleines) et la forme
   qui le corrige. C'etait la condition pour que l'enfant voie *a travers quoi* il reflechit la case.
2. **Une case = une zone de 16 unites** (47,4 px a 296), sur une grille dans les bornes de `DEP-CE1-2`.
3. **Les cases pleines separees par 4 unites** se lisent comme des carreaux, pas comme une silhouette
   continue. Je l'admets : l'enfant raisonne case par case, et c'est ce que la tache demande.

**Correction de lecture avant d'arbitrer la section 3.** `DEP-CE1-2` dit *"toutes de meme taille et toutes
designables"*. `SYM-CE1-3` n'en reprend que *"les bornes de grille"*. *"Toutes designables"* vaut donc pour
`MAT-CE1-14` (se reperer), pas pour `MAT-CE1-17`. La proposition d'`ines-design` (seules les cases
candidates sont des zones) ne contredit aucune de mes conditions.

**Ce qui n'est pas a moi** : que la section 4 soit terminee avant le premier dessin est une question de
dessin, a `sofia-sm` et `ines-design`. Je n'en fais pas une condition d'engagement.

---

## 3. Leurre loyal, et cases candidates du quadrillage

### 3.1 Leurre loyal sur les figures a lignes candidates (2.5 point 4)

- **Le leurre de `SYM-CE1-2` reste la diagonale** (deux parties de meme aire sans symetrie), sur au moins
  la moitie des items. C'est lui qui fait de l'item un exercice de symetrie et non de partage.
- **La parallele decalee est loyale si le decalage se voit sans mesurer.** Elle teste *"la ligne est-elle
  au milieu ?"*, ce qu'un enfant doit verifier. Un decalage qui ne se voit qu'a la regle teste l'acuite,
  pas la symetrie : c'est un piege. Je retiens la borne d'`ines-design` (6 unites au moins, 17,8 px a 296)
  comme plancher ; le modele a 8 unites est loyal.
- **Le chiffre ne suffit pas, et je le juge en jouant** : zones masquees (`ZON-9`), si j'hesite a la
  taille servie entre le leurre et l'axe, l'item est refuse.
- **La position de l'onglet de l'axe varie d'un item a l'autre** (haut, bas, gauche, droite). Un axe dont
  l'onglet est toujours en haut se trouve sans regarder la figure.

### 3.2 Cases candidates du quadrillage

**Un sous-ensemble, comme le propose `ines-design`.** Toutes les cases vides en zones donneraient une
trentaine d'arrets de tabulation pour un seul geste, sans gain pour le raisonnement si les candidates
sont bien choisies. Un sous-ensemble est un choix parmi des propositions, comme le bandit manchot.
Il est loyal si chaque candidate est une erreur qu'un enfant fait reellement :

1. **Au moins trois candidates** (`ZON-8`).
2. **La case obtenue par glissement** (la case copiee de l'autre cote sans etre retournee), quand elle est
   distincte de la bonne. C'est la confusion entre symetrie et translation, celle que la competence existe
   pour corriger.
3. **Au moins une voisine de la bonne case**, a une case pres de la distance a l'axe. Sinon la bonne se
   trouve *"a peu pres"*, sans compter. C'est ma regle de la ligne graduee (s51, 6.4 point 3), appliquee ici.
4. **La bonne case n'est jamais la seule candidate qui touche la figure** : c'est `SYM-CE1-3` (*"elle ne se
   trouve pas par voisinage"*) lu sur les zones et non plus seulement sur les cases vides.

Ce ne sont pas des identifiants de condition neufs. Ce sont mes regles du distracteur plausible et
`SYM-CE1-3`, appliquees a la question posee. Je les verifierai a l'audit, item par item.

---

## 4. Onglet au bout de la ligne, ou zone sur la ligne (2.4)

**L'onglet au bout de la ligne est admis.** Il garde le numero hors du trait (`ZON-15` (b)) et la
bordure du bouton lisible ; une zone posee sur le trait tombe a 2,00:1. Mon arbitrage de s51 (6.3 point 3)
admettait la zone sur la partie exterieure de la ligne ; l'onglet en est un cas.

**Ce que ca demande au contenu, et que je verifierai** :

- **La consigne nomme le geste sur le numero**, par exemple *"Touche le numéro de l'axe de symétrie."*
  Sans cela, l'enfant de CE1, qui joue sans adulte, touche le pointille, rien ne se passe, et il croit
  que le jeu est casse.
- **Chaque onglet appartient a une seule ligne, a l'oeil** : la ligne entre dans son onglet, et aucune
  autre ligne ne passe plus pres de cet onglet que la sienne. Je le juge en jouant (`ZON-9`). C'est la
  propriete que je suis seule a garder : A2 ne juge que le non-chevauchement des cadres.

---

## 5. Texte alternatif (`th:alt`)

Il ne nomme ni l'axe, ni aucune zone (`ADR-0091`), ni le nombre de rangees ou de colonnes (`MUL-CE1-5`),
ni le total. Il dit ce que l'image montre, sans donner la reponse. Un seul texte par type de dessin suffit :

| Dessin | Texte propose |
|---|---|
| figure a lignes candidates | *"Une figure traversée par des lignes en pointillés."* |
| quadrillage de completion | *"Une figure en cases noires sur un quadrillage, avec un trait épais au milieu."* |
| reseau de points | *"Des points rangés en lignes et en colonnes."* |

L'axe peut etre decrit sur le quadrillage (*"un trait épais"*) : il y est donne, ce n'est pas lui que
l'enfant cherche. Ces textes ne rendent pas le jeu jouable sans voir l'image (`ADR-0080`, 7.2) : ils
evitent seulement de souffler la reponse. Leur orthographe est au `relecteur-pedagogique` (`DEC-0002`).

---

## 6. Le plancher de zone : 16 ou 15 unites

**Je ne retiens pas 16 comme exigence pedagogique.** Mon exigence est un rendu d'au moins 44 px a la
largeur servie la plus etroite (296 px a 320 px de fenetre). Sur un dessin carre de 100 unites, 15 unites
font 44,4 px : la barre est tenue. Les 16 unites d'`ines-design` sont une marge de dessin, et `theo-archi`
l'ecrit ainsi dans A2bis (*"une contrainte de dessin avec sa marge, pas un plancher d'accessibilite"*). Je
n'ai pas d'objection a ce que les dessins de C la suivent.

**Deux motifs de ne pas la faire mienne.**

1. **Ce serait deplacer mon poteau sur des paquets deja juges.** `theo-archi` mesure 48 cadres a 15 unites
   dans les deux paquets servis (A2bis, point 2), dont six sur `cp-plan-de-la-classe` (carre, 44,4 px a
   296). Une exigence pedagogique a 16 les rendrait non conformes apres coup, sans qu'aucun enfant n'y ait
   rien rate.
2. **Une regle a l'unite pres n'est pas ce que je verifie.** Je joue chaque zone a sa taille servie
   (`ZON-9`). Une zone de 15 unites qui se touche bien passe ; une zone qui se rate au doigt est refusee,
   quel que soit son chiffre.

**Correction d'une attribution.** La section 1.3 d'`ines-design` cite le plancher de 44 px comme une regle
de mon arbitrage de s51, section 6.4. Je l'ai relue : ma section 6.4 ne donne aucun chiffre en pixels et
renvoie le plafond a `theo-archi` et `ines-design`. Les 44 px sont ceux du garde-fou 3 d'`ADR-0080` et de
`TailleDesTextesEtBoutonsTest`. Je les reprends ici comme ma barre a l'audit, pour que sa source soit
ecrite.

---

## 7. Ce dont je reste la seule garde, pour C

| Propriete | Qui la tient |
|---|---|
| Chaque reseau compte sans se perdre a sa taille servie (`MUL-CE1-5`) | moi seule, en jouant |
| Le leurre parallele se distingue de l'axe sans mesurer ; l'onglet de l'axe ne se predit pas par sa position | moi seule, en jouant |
| Chaque onglet appartient a sa seule ligne, aucune zone ne contient un segment d'une autre ligne (`ZON-9`) | moi seule, en jouant ; A2 ne juge que le non-chevauchement des cadres |
| Les candidates du quadrillage sont des erreurs plausibles (section 3.2) | moi seule, a la lecture et en jouant |
| Toute zone fait au moins 44 px a 296 px de large, sur les items autres que la premiere manche | moi seule jusqu'a A3 (`ADR-0080`, A2bis section 6) |

---

## 8. Ce que ce document ne fait pas

- Il ne prononce aucun Done et ne touche ni la matrice, ni le backlog, ni aucune fiche de jeu.
- Il ne cree aucun identifiant de condition ou de reserve et n'ecrit rien dans
  `squad/decisions/REGISTRE-identifiants.md`.
- Il ne modifie ni les contraintes d'`ines-design`, ni `ADR-0080`, ni aucun fichier de `lea-dev`.
- Il ne tranche pas la variante `<path>` des reseaux ni l'achevement de la section 4 des contraintes : ce
  sont des questions de dessin et d'architecture.
