# `max-height` tranchee, et ce qu'un dessin servi doit declarer - `DES-1` a `DES-7`

- **Auteure** : `ines-design`. **Date** : 2026-08-31. **Sprint** : s25.
- **Branche** : `s25-ines`, worktree dedie.
- **Destinataires directs** : `lea-dev` (figures planes) et `sofia-sm` (monnaie), qui produisent des
  dessins **en ce moment**. `marc-dev` pour la valeur qu'il me rend, `nadia-prof` pour ce qui reste
  a elle.
- **Pourquoi ce document est court** : deux paires de dessins attendent, et une contrainte qui
  arrive apres eux ne sert a rien.

---

## 1. `max-height: 11rem` - je confirme, et le raisonnement de `marc-dev` est juste

**Sa raison est la bonne, et c'est la decisive.** Retenir 53 px aurait fait tomber le cadran
(123 px, mesure) **par une declaration de feuille de style**, la ou l'utilisatrice vient d'arbitrer
que le pli n'est pas une borne opposable et que le palier 1 se livre entier. **Une valeur de CSS qui
rend une ligne impossible est une decision de produit prise par un fichier de style.** Il a eu
raison de refuser cela, et il a eu raison de me la rendre plutot que de la trancher en silence.

**J'ajoute la raison que je n'avais pas quand j'ecrivais "un emplacement, pas une proposition"**, et
c'est elle qui fixe la valeur plutot que de la subir :

| Repere mesure | Valeur |
|---|---|
| Plancher de lisibilite du dessin le plus exigeant du palier 1 (cadran a douze nombres) | **123 px** |
| **`max-height: 11rem`** | **176 px** |
| Niveau de confort - encre de chiffre a la taille de `.enonce` | 202 px |

**176 px passe le plancher du cas le plus exigeant avec 53 px de marge, et reste sous le niveau de
confort.** C'est une valeur defendable, pas un chiffre rond : elle est la seule des trois qui ne
ferme aucune ligne du palier 1 et ne gonfle aucun dessin au-dela de ce que sa lecture demande.

**La valeur tient donc, et elle n'est plus "a trancher".** `nadia-prof` garde son veto ; je ne
prononce rien de pedagogique.

### 1.1 Mais 11rem n'est correct qu'a une condition, et c'est le point qui vaut ce document

**`max-height` est un plafond, jamais une taille.** Il ne mord que sur un fichier qui se declare
plus grand. **Un fichier qui ne declare aucune taille sort donc exactement au plafond, quel qu'il
soit** - et c'est ce que j'ai mesure :

**Mesure, methode : les quatre declarations reellement servies par `marc-dev` (`max-height: 11rem`,
`max-width: 100%`, `height: auto`, `width: auto`), zone de 696 px, un `img` par cas, navigateur
reel, viewport lu.**

| Ce que le fichier declare | Boite rendue | Qui a decide de la taille |
|---|---|---|
| `viewBox` seul, rapport 1:1 | **176 x 176** | **la feuille**, pas le fichier |
| `viewBox` seul, rapport 3:1 | **528 x 176** | **la feuille** |
| `viewBox` + `width`/`height` en pourcentage | **176 x 176** | **la feuille** |
| `viewBox` + `width="80" height="80"` | **80 x 80** | **le fichier** |
| `viewBox` + `width="480" height="480"` | 176 x 176 | la feuille : le fichier demandait plus que le plafond |

**Consequence a lire avant de dessiner** : le SVG de contre-exemple d'`ADR-0073`
(`figure-triangle.svg`) ne porte qu'un `viewBox`. **Un dessin ecrit sur ce modele sortira a 176 px
de haut, qu'il soit une figure plane ou une rangee de pieces** - et une rangee de pieces n'a besoin
que de **43 px** pour etre lisible. Elle occuperait **133 px de plus que necessaire**, pris sur ce
que voit un enfant bloque.

**C'est pour cela que `DV-1` cesse d'etre une dependance et devient une condition** : 11rem est la
bonne valeur **si et seulement si** les dessins declarent leur taille. Sinon 11rem *est* la taille
de tout, et c'est faux pour la moitie du palier 1.

---

## 2. `DES-1` a `DES-7` - ce qu'un dessin servi doit declarer

Ce sont des **contraintes de forme**, mon perimetre. Ce que le dessin represente, son exactitude et
son texte alternatif restent a `nadia-prof` (`FIG-1` a `FIG-4`, `MON-1` a `MON-5`, `CON-5`).

- **`DES-1` - Un `viewBox` et un `width`/`height` en unites utilisateur.** Jamais de pourcentage,
  jamais d'unite. Section 1.1 : sans eux, le fichier n'a aucune voix sur sa propre taille.
- **`DES-2` - `width`/`height` dans le meme rapport que le `viewBox`.** Un ecart y ferait declarer
  au fichier un rapport qu'il ne rend pas, et `FIG-1` cesserait d'etre verifiable autrement que par
  l'oeil - ce qu'elle demande explicitement d'eviter.
- **`DES-3` - La hauteur declaree tient entre deux bornes mesurees.**
  - **Plafond : 176 px.** Au-dela, la feuille rogne ; declarer plus est sans effet et trompeur.
  - **Plancher : le trait rend au moins 2 px** - la plus fine ligne que `recreo` dessine deja en lui
    faisant porter un sens (bordure de `0.125rem` de `.champ-reponse` et des deux sorties).
  - **Plancher supplementaire des qu'un chiffre est trace : son encre rend au moins 14 px**, la
    hauteur d'encre d'un chiffre a `1.25rem`, le plancher typographique du produit.
  - Valeurs deja mesurees, a reprendre plutot qu'a recalculer : **une piece portant un chiffre
    demande 43 px** de hauteur d'image, **un cadran a douze nombres en demande 123 px**. Une figure
    plane sans chiffre n'est tenue que par le plancher de trait.
- **`DES-4` - Le trait est en unites utilisateur, et jamais `vector-effect: non-scaling-stroke`.**
  Il decouplerait l'epaisseur de l'echelle et ferait mentir le plancher de `DES-3`.
- **`DES-5` - La teinte du trait est ecrite en clair, et c'est `#1c2733`, la valeur de
  `--recreo-texte`. Ni `currentColor`, ni une variable CSS.** *Mesure du 2026-08-31 : un SVG charge
  par un element image est un document isole - la couleur de la page n'y entre pas. Un
  `currentColor` y rend du noir (`#000000`, verifie au pixel), et une variable maison n'y est pas
  definie du tout.* Une teinte non declaree est donc une teinte que personne ne gouverne.
  *Contraste sur le fond de page : `#1c2733` donne 14,09:1, le noir 19,54:1, le seuil d'un graphique
  non textuel est 3:1 - aucun des deux n'est un defaut d'accessibilite, mais un seul est un choix.*
- **`DES-6` - Un chiffre n'est jamais redessine deux fois.** Dix glyphes definis une fois, autant
  d'instances qu'il en faut. Mesure : les douze nombres d'un cadran sont **dix glyphes et quinze
  instances**, et les glyphes d'une piece sont **les memes** que ceux d'un cadran.
- **`DES-7` - Aucun element de texte dans un dessin.** `ADR-0073` clause 4, deja gardee par un
  test ; je la reconduis ici pour que la liste soit complete sous les yeux de qui dessine.

**Deux fichiers commites servent de patron** et non de contenu :
`demonstration-chiffres-traces.svg` (cadran, `DES-1` a `DES-7` tenues) et
`demonstration-pieces-tracees.svg` (pieces, memes glyphes). **Ils sont copiables ; leurs formes de
chiffres, elles, restent a l'arbitrage de `nadia-prof`.**

*Correction que je porte contre moi : mes deux demonstrations declaraient `currentColor` avant la
mesure de `DES-5`. Elles rendaient donc du noir, pas la teinte que j'annoncais. Corrigees dans ce
commit - et c'est bien pourquoi elles doivent etre justes avant d'etre copiees.*

---

## 3. Le cliquet de poids : ma hauteur n'y change rien, et voici le chiffre

`sofia-sm` pose que le cliquet de poids des images ne monte pas silencieusement au second paquet
illustre. **Ma reponse est courte : la hauteur que je fixe ne change pas le poids d'un fichier.**

Un dessin pese ce que pesent ses commandes de trace. `width`, `height` et `max-height` sont deux
attributs et une declaration : **quelques octets, et ils ne varient pas avec la valeur**. Passer de
`width="80"` a `width="480"` coute **deux caracteres**.

**Ce qui fait le poids, et ce que mes contraintes en font** :

| Ce qui pese | Effet de mes contraintes |
|---|---|
| Le nombre de traces | **`DES-6` le reduit** : dix glyphes reutilises au lieu d'un dessin par chiffre |
| Les commentaires | **a la hausse**, et je l'assume : ce depot commente ses fichiers |
| Les dimensions declarees | **nul** |

**Chiffres de reference, mesures sur mes fichiers de ce sprint** - a lire comme un ordre de grandeur,
pas comme un seuil, que je ne propose pas :

| Fichier | Avec commentaires | Trace seul |
|---|---|---|
| Cadran, douze nombres, dix glyphes | 6 325 o | **3 053 o** |
| Trois pieces, dix glyphes | 3 492 o | **1 569 o** |
| Une figure plane nue | 1 084 o | **275 o** |

**Le cliquet tombe sur la premiere paire visuelle fusionnee, et il n'a pas a compter ma valeur de
hauteur.** S'il porte sur le fichier commite, commentaires compris, il faut qu'il le dise : un
dessin de 275 octets de trace en pese 1 084 une fois commente comme ce depot commente, et un seuil
pose sur l'un ne veut rien dire sur l'autre.

---

## 4. Ce que je ne tranche pas

- **La forme d'un chiffre scolaire**, l'exactitude d'un dessin, le texte alternatif : `nadia-prof`.
- **Le cliquet de poids lui-meme** - sa valeur et son perimetre sont a `sofia-sm` ; je ne donne que
  ce qu'il doit savoir pour ne pas mesurer la mauvaise chose.
- **L'identifiant de session dans l'URL d'illustration**, que `marc-dev` remonte. Un seul fait de
  mon perimetre, sans avis : une URL qui change a chaque session **fait retelecharger le dessin a
  chaque partie**, la ou un enonce ne coute rien. C'est la premiere fois que ce comportement porte
  sur autre chose que du texte. Route a la review, comme `sofia-sm` l'a decide.
- **Le pli.** Il est hors de la porte pedagogique et hors de la mienne. Mes 53 px restent une mesure
  vraie et cessent d'etre une borne : **je ne les oppose a personne.**
