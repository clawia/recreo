# F2 - la barre a trois puces, mesuree sur le rendu Thymeleaf servi

- **Auteur** : `theo-archi`. **Date** : 2026-09-21. **Sprint** : s36. **Lot** : `F2`
  (`planning.md`, section 3 ; routee a `ines-design` et `nadia-prof`, section 4).
- **Route par** : la section 1.4 de
  `proposition-ines-s36-barre-trois-puces-et-libelle-externe-qlm11.md` - *"La confirmation du
  mecanisme de repli imbrique sur le gabarit Thymeleaf reel, avant tout Done technique, reste a
  `theo-archi`/`lea-dev`."*
- **Ce que ce document est** : une mesure, et rien d'autre. Il ne choisit aucune forme. Accepter
  deux rangs a trois puces, ou en refuser le prix, appartient a `nadia-prof`.
- **Verdict en une ligne** : le mecanisme propose en section 1.3 est **infirme**. Le conteneur
  imbrique ne recupere le rang unique a aucune des quatre largeurs ; il coute un troisieme rang a
  **deux** largeurs et non une, et il ne change **rien** a 470 px.

---

## 1. Methode

Worktree `s36-theo`, ouvert depuis `s36-sofia` a `364ce0dd`, lignee verifiee
(`outils/verifier-lignee.sh`). Machine virtuelle : la toolchain `jdk` `21` declaree dans
`~/.m2/toolchains.xml`, qui **rapporte** `21.0.10` (`outils/mvn-jdk21.sh`, `ADR-0071` - la version
d'une mesure est celle que la machine virtuelle rapporte, jamais celle qu'une entree de toolchain
declare).

Le reacteur est construit **depuis la racine**, jamais par un `-pl` partiel
(`bash outils/mvn-jdk21.sh -B -DskipTests package`), puis l'application est servie par
`java -jar bootstrap/target/recreo.jar`. **Tout ce qui est mesure ci-dessous est un document rendu
par Thymeleaf et servi par ce jar**, jamais un mockup.

Captures : `outils/mesurer-rendu.sh` (`ADR-0025`), Chrome headless, forme portable de la commande :

```
CHROME=<un Chrome ou Chromium installe, jamais son emplacement dans un fichier commite> \
outils/mesurer-rendu.sh <largeur> http://localhost:8080/ <png> <hauteur>
```

**Trois modifications temporaires de l'arbre, toutes annulees avant ce commit** (`git status`
propre) :

1. **un paquet de contenu de niveau CE1 et son entree de catalogue.** Sans lui, aucune reponse du
   serveur ne porte une troisieme puce, et il n'y a rien a mesurer - c'est le motif exact pour
   lequel `ines-design` avait du passer par un mockup. Le paquet est une sonde, jamais un contenu :
   il n'a pas ete relu, il ne ferme aucune ligne de matrice, et il n'est pas dans ce commit ;
2. **pour la seconde serie seulement**, le `<div class="groupe-niveaux">` d'`accueil.html` et la
   regle `.groupe-niveaux` de `recreo.css`, **recopies au caractere** de la section 1.3 de la
   proposition d'`ines-design` ;
3. **une page de sonde servie par l'application elle-meme**, qui lit `getBoundingClientRect` dans
   une iframe dont la largeur CSS est le viewport mesure. Elle est de **meme origine** que la page
   mesuree, ce qui est la seule facon d'obtenir des nombres exacts sous le plancher de fenetre de
   516 px que `mesurer-rendu.sh` documente. Les captures et les nombres viennent du meme arbre et
   du meme jar.

**Ordre des puces** : `CP`, `CM1`, `CE1` - l'ordre du catalogue (`F1`), et le meme ordre que le
mockup d'`ines-design`, pour que les deux series se comparent.

**Comment un rang est compte** : la hauteur de la bande d'en-tete est mesuree sur le PNG (nombre de
lignes de pixels portant `--recreo-entete-fond`, `#1d4b6b`, a `x = 2`), et l'arrangement est lu sur
l'image. Les deux methodes concordent a chaque largeur avec les rectangles que Chrome rapporte.

---

## 2. Les largeurs mesurees, identiques dans les deux series

Valeurs rendues par Chrome sur le document servi, au centieme :

| Element | Largeur |
|---|---|
| `.icone-entete` | 44,00 px |
| `.nom-site` (porte `margin-right: auto`) | 98,80 px |
| puce `CP` | 56,38 px |
| puce `CM1` | 73,91 px |
| puce `CE1` | 66,06 px |
| `.lien-accueil` (porte `margin-left: auto`) | 108,83 px |

`gap` 16 px, `row-gap` 8 px, rembourrage lateral de `.container` 12 px de chaque cote. La largeur
de rangee offerte vaut donc **`V - 24`** sous 576 px.

---

## 3. Serie 1 - trois puces, aucun changement de gabarit ni de feuille

| Largeur | Rangs | Bande d'en-tete | Ce que porte chaque rang |
|---|---|---|---|
| 320 px | 2 | 140 px | [icone, titre, CP] / [CM1, CE1, "Les jeux"] |
| 360 px | 2 | 140 px | [icone, titre, CP, CM1] / [CE1, "Les jeux"] |
| 400 px | 2 | 140 px | identique a 360 px |
| 470 px | 2 | 140 px | [icone, titre, CP, CM1, CE1] / ["Les jeux"] |

**La section 1.2 de la proposition d'`ines-design` est confirmee aux quatre largeurs, arrangement
par arrangement**, y compris le fait qui motive son lot : le rang unique accorde a deux puces
n'existe plus a 470 px avec trois.

Captures : `squad/conformite/releves-s36/theo-f2-servi-sans-changement-<largeur>px.png`.

### 3.1 Une correction a la section 1.2, mesuree

La proposition ecrit : *"Entre 470 px et 650 px (verifie aussi a 500, 520, 540, 576, 650 px [...])
rien ne change : le rang reste a deux du debut a la fin de cette plage."*

**C'est faux sur la bande 552 - 575,98 px**, sans aucun changement de gabarit ni de feuille. Mesure
sur le rendu servi : **551 px -> 140 px (deux rangs)**, **552 px, 560 px, 575 px -> 82 px (rang
unique)**, **576 px -> 140 px (deux rangs)**.

La cause est celle qu'elle nomme correctement ailleurs : a partir de 576 px la feuille tierce
plafonne `.container` a 540 px (`ADR-0054`), ce qui n'offre plus que **516 px** de rangee la ou le
rang unique en demande **528,00 px** - il manque **12,00 px**, et pas un de plus, sur toute la bande
576 - 719,98 px. Ses cinq largeurs de controle (500, 520, 540, 576, 650) encadrent la bande sans y
tomber.

Ce chiffre de 12,00 px est le seul de ce document qui serve a choisir une forme, et **je ne le
depense pas** : le plafond de `.container` est gouverne par `ADR-0056`/`SYN-14`, et le toucher est
un arbitrage de forme.

---

## 4. Serie 2 - le candidat `groupe-niveaux`

| Largeur | Rangs | Bande d'en-tete | Ce que porte chaque rang | Ecart avec la serie 1 |
|---|---|---|---|---|
| 320 px | **3** | 192 px | [icone, titre] / [CP, CM1, CE1] / ["Les jeux"] | **+52 px** |
| 360 px | **3** | 192 px | identique a 320 px | **+52 px** |
| 400 px | 2 | 134 px | [icone, titre] / [CP, CM1, CE1, "Les jeux"] | -6 px |
| 470 px | 2 | 140 px | [icone, titre, CP, CM1, CE1] / ["Les jeux"] | **aucun** |

Captures : `squad/conformite/releves-s36/theo-f2-servi-groupe-niveaux-<largeur>px.png`.

**Le rang unique n'est recupere a aucune des quatre largeurs.** Les 6 px gagnes a 400 px ne sont pas
un rang : le premier rang y retombe de la hauteur d'une puce (50 px) a celle de l'icone (44 px),
parce que les puces l'ont quitte.

**A 470 px, le document rendu par le candidat est le meme que celui de la serie 1**, arrangement et
hauteur de bande compris.

---

## 5. Le mecanisme, et pourquoi l'hypothese de la section 1.3 est l'inverse du fait

L'hypothese ecrite par `ines-design`, qu'elle demandait explicitement de verifier : *"un conteneur
flex imbrique et lui-meme `flex-wrap: wrap` contribue a l'algorithme de repli du conteneur parent
par une taille de type fit-content (bornee par son propre contenu minimal, la puce la plus large,
plutot que par la somme de ses trois enfants)"*.

**Le fait mesure est l'inverse.** Le conteneur imbrique entre dans le repli du parent par sa taille
**max-content**, c'est-a-dire **la somme de ses trois enfants et de leurs deux `gap`** :

```
56,38 + 16 + 73,91 + 16 + 66,06 = 228,34 px
```

Chrome rapporte `228,344 px` pour `.groupe-niveaux` aux quatre largeurs, y compris celles ou il
n'occupe qu'une partie de la rangee. Le `flex-wrap: wrap` pose **sur l'enfant** ne le rend pas plus
etroit aux yeux du parent : il ne fait que l'autoriser a se replier **a l'interieur** de la largeur
que le parent lui a deja donnee, apres le repli du parent. Le repli d'un conteneur flex place les
elements ligne par ligne selon leur **taille principale hypothetique** ; `flex-shrink` n'agit
qu'**a l'interieur** d'une ligne deja formee, et ne permet donc jamais a un element de rejoindre
une ligne ou il ne tient pas.

Le groupe est donc un **atome trois fois plus large que la plus large des puces** (228,34 px contre
73,91 px). Il se replie **plus tot**, jamais plus tard, et il ne peut pas rendre un rang que les
puces nues n'avaient pas deja.

### 5.1 Les seuils, calcules puis verifies au pixel sur le rendu servi

Sommes cumulees, `gap` de 16 px compris :

| | Serie 1 (six elements) | Serie 2 (quatre elements) |
|---|---|---|
| icone | 44,00 | 44,00 |
| + titre | 158,80 | 158,80 |
| + CP | 231,18 | - |
| + CM1 | 321,08 | - |
| + les trois puces | 403,14 | **403,14** (le groupe entier) |
| + "Les jeux" | 528,00 | 528,00 |

| Seuil | Predit par l'arithmetique | Mesure |
|---|---|---|
| Serie 1, rang unique (`528,00 <= V - 24`) | `V >= 552` | 551 px -> 140 px, **552 px -> 82 px** |
| Serie 2, rang unique | `V >= 552` (le meme) | 551 px -> 140 px, **552 px -> 82 px** |
| Serie 2, le groupe rejoint le rang du titre (`403,14 <= V - 24`) | `V >= 428` | 427 px -> 134 px, **428 px -> 140 px** |
| Serie 2, "Les jeux" rejoint le rang du groupe (`228,34 + 16 + 108,83 = 353,17 <= V - 24`) | `V >= 378` | 377 px -> 192 px (trois rangs), **378 px -> 134 px** |

Les quatre seuils tombent au pixel pres. `403,14` est **identique dans les deux series** : le
candidat ne change pas la largeur a partir de laquelle les trois puces tiennent sur le rang du
titre. Ce qu'il change, c'est qu'en dessous de 428 px elles ne peuvent plus se separer, donc elles
partent **toutes les trois**, et "Les jeux" est alors expulse sur un troisieme rang tant que
`V < 378`.

Captures de seuil :
`theo-f2-servi-sans-changement-551px.png`, `theo-f2-servi-sans-changement-552px.png`,
`theo-f2-servi-groupe-niveaux-377px.png`, `theo-f2-servi-groupe-niveaux-428px.png`.

---

## 6. La troisieme ligne a 320 px : reelle, et ce n'est pas la seule

La question posee etait : *"dis si la troisieme ligne a 320 px est un cout reel ou un artefact de sa
maquette."*

**Cout reel, mesure sur le rendu servi : 192 px contre 140 px, soit +52 px de bande d'en-tete.** Et
le cout est **plus large que la proposition ne l'annonce** : le troisieme rang apparait aussi a
**360 px**, la largeur de reference du depot, que la section 1.3 decrit comme un cas a deux rangs.

Le budget de pli en est diminue d'autant sur les deux largeurs les plus contraintes - celles-la
memes ou `S15-7`, `M4` et `SYN-11` ont deja montre que le pli se paie. Je ne dis pas que ce prix est
inacceptable : ce jugement est celui de `nadia-prof`.

---

## 7. Ou est le defaut, et ou il n'est pas

**Le mockup d'`ines-design` n'est pas en cause.** Ses quatre captures du candidat, lues dans son
propre worktree, montrent **exactement** les memes arrangements que le rendu servi :

| Capture d'`ines-design` | Ce que l'image montre | Ce que la section 1.3 en dit |
|---|---|---|
| `f2-3puces-groupe-niveaux-320px.png` | trois rangs | *"Trois rangs"* - **concordant** |
| `f2-3puces-groupe-niveaux-360px.png` | **trois rangs** | *"Deux rangs : titre seul, les trois puces et Les jeux ensemble"* - **discordant** |
| `f2-3puces-groupe-niveaux-400px.png` | **deux rangs** (titre seul, puis les puces et "Les jeux") | *"Rang unique"* - **discordant** |
| `f2-3puces-groupe-niveaux-470px.png` | **deux rangs** (titre et puces, puis "Les jeux" seul) | *"Rang unique, confirme"* - **discordant** |

Son instrument de mesure etait donc bon, et sa limite assumee - *"un mockup statique n'est pas
l'application reelle"* - n'est pas ce qui l'a trompee. **L'ecart est entre ses images et la lecture
qu'elle en a ecrite**, sur trois lignes de son tableau sur quatre. Sa section 1.2 (l'etat sans
changement), elle, est exacte aux quatre largeurs.

Je nomme le fait et je m'arrete la : je n'ai pas de quoi diagnostiquer la cause de l'ecart de
lecture, et une cause supposee vaudrait moins que le fait mesure. La lecon que ce depot en tire est
la sienne, pas la mienne a prononcer - sauf sur un point qui me concerne directement : **le tableau
discordant est celui qui part vers une porte pedagogique**. `nadia-prof` aurait arbitre sur
*"le rang unique est conserve de 360 a 470 px"* quand aucune des deux formes ne le conserve nulle
part dans cette plage.

---

## 8. Ce que cette mesure ne tranche pas

- **Accepter deux rangs a trois puces, ou refuser le prix du troisieme** : `nadia-prof`, comme la
  section 1.4 le route deja.
- **Une autre forme que le regroupement** : `ines-design`. Le seul element chiffre que je lui
  transmets est celui de la section 3.1 - il manque **12,00 px** de rangee pour tenir le rang unique
  sur la bande 576 - 719,98 px, et le levier qui les porterait est le plafond de `.container` que
  `ADR-0056`/`SYN-14` gouvernent.
- **L'ordre des puces** (`F1`) : question produit, deja routee a `camille-po`. Il n'est pas neutre
  pour cette mesure - avec trois puces, l'ordre decide laquelle se separe de ses soeurs - mais ce
  n'est pas a moi de le fixer.
- **Aucun Done, technique ou pedagogique** : aucun fichier de production n'est modifie par ce
  commit.
