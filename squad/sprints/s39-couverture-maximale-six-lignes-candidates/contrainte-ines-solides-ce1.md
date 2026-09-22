# S39-SOLIDES - contraintes visuelles pour les cinq dessins de solides

- **Auteure** : `ines-design`. **Date** : 2026-09-22. **Sprint** : s39.
- **Ce qui m'est pose** : `theo-archi`, avis de faisabilite s39, section 14, risque R5 - juger si le
  plafond de 176 px (`.image-item`, `max-height: 11rem`, arbitre le 2026-08-31,
  `squad/sprints/s25-le-premier-palier-visuel/contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md`,
  ci-apres "le document `DES`") suffit a lire un cube, une boule, un cylindre, un cone et un pave
  droit, **avant** que les quinze dessins soient traces.
- **Methode** : mesure sur rendu reel, pas sur le fichier seul (`outils/mesurer-rendu.sh`, Chrome
  headless, cinq maquettes SVG a 176x176 px, `viewBox 0 0 100 100`, meme trait que les figures
  planes). Deux passes : une geometrie prudente d'abord, une geometrie corrigee ensuite, parce que
  la premiere a trouve un defaut que je n'aurais pas vu sans rendre l'image.
- **Ce que je ne dessine pas** : ces cinq SVG restent des maquettes de mesure, jetables. Les dessins
  livres au paquet sont a `lea-dev` ou `sofia-sm` selon l'affectation de `sofia-sm` - c'est la
  convention deja en usage sur ce depot : les douze figures planes de s25 sont commitees par
  `lea-dev` (`git log`, `figure-carre-droit.svg` etc.), les dessins de monnaie par `sofia-sm`,
  tous deux sur mes contraintes ecrites, jamais l'inverse. Je ne tranche donc pas qui dessine les
  solides, seulement ce que le dessin doit respecter.

---

## 1. Verdict sur le plafond : 176 px suffit, a deux conditions nouvelles

Les cinq solides, avec le trait et la couleur deja en vigueur (`DES-1` a `DES-7` du document `DES`,
inchangees, reconduites telles quelles), restent lisibles a 176 px. Mais deux points, absents des
figures planes parce qu'aucune n'en avait besoin, doivent etre poses avant de dessiner, sous peine
de livrer un cube et un pave droit indiscernables, ou un cylindre dont l'arete cachee se lit comme
un defaut de trait plutot que comme un signal :

### 1.1 Cube / pave droit - l'ecart de proportion doit etre marque, pas realiste

Une premiere maquette, aux proportions plausibles pour un vrai pave droit (boite legerement
allongee, rapport hauteur/largeur de la boite englobante 1,18 contre 1,14 pour le cube - 3 % de
difference relative), rend **deux dessins visuellement identiques** a 176 px : l'oeil ne separe
pas 3 % sur une boite de 176 px de cote. Une seconde maquette, dont le pave droit porte un rapport
de 1,96 contre 1,14 pour le cube (72 % de difference relative), se distingue sans ambiguite.

> **Contrainte** : le rapport hauteur/largeur de la boite englobante du pave droit doit differer de
> celui du cube d'**au moins 50 % en relatif** (ex. cube ~1,1-1,2 ; pave droit ~1,7 ou plus, ou
> l'inverse sur l'axe qu'on allonge). Un pave droit "realiste" mais proche du cube n'est pas une
> proportion plus fidele, c'est une proportion illisible a cette taille - `SOL-CE1-4`
> (`nadia-prof`) demande "un rapport d'aretes visiblement distinct", et "visiblement" a 176 px se
> mesure a ce seuil, pas a l'oeil de qui dessine sur un grand ecran.

### 1.2 Arete cachee (cylindre) - le tirete n'est lisible qu'au-dessus d'un pas minimal

Un premier essai de tirete (`stroke-dasharray="4 3"` sur un trait de meme epaisseur que le contour,
6 unites) rend, une fois capture a 176 px, un arc qui **se lit comme un trait plein legerement
irregulier** - le signal "cache" disparait. Un second essai (trait du tirete reduit a 3 unites,
motif `"6 5"`) rend un tirete net et sans ambiguite.

> **Contrainte, si le tirete est retenu pour l'arete cachee du cylindre** : le trait du tirete est
> **plus fin que le contour** (3 unites de `viewBox` contre 6 pour le contour, soit 5,28 px rendus
> contre 10,56 px - le premier reste au-dessus du plancher de `DES-3`, 2 px), et chaque segment du
> motif mesure **au moins 6 unites de `viewBox`** (10,56 px rendus). En dessous, le tirete se lit
> comme un trait plein degrade, ce qui est pire qu'utile : un enfant y verrait un defaut, pas un
> sens.
> **Alternative, plus sure et que je recommande par defaut** : ne pas tenter le tirete du tout et
> tracer l'arc arriere du cylindre en trait plein, comme un dessin d'enfant ou un cylindre de
> manuel scolaire le montre deja - la boucle refermee (deux ellipses, deux generatrices) suffit a
> lire "cylindre" sans qu'aucune arete n'ait besoin d'etre marquee "cachee". Ce choix reste un choix
> de forme, donc le mien ; il ne change aucune clause de `nadia-prof`.

## 2. Ce qui ne change pas

- **Trait** : une seule couleur, `#1c2733` (`DES-5`), aucun remplissage (`FIG-3`/`COU-2`), epaisseur
  de contour 6 unites de `viewBox` sur 100 (rendu 10,56 px a 176 px), au-dessus du plancher de
  `DES-3` (2 px). Identique aux figures planes.
- **Taille** : `viewBox` et `width`/`height` en unites utilisateur, meme rapport, hauteur declaree
  au plus 176 px (`DES-1` a `DES-3`), aucun `vector-effect: non-scaling-stroke` (`DES-4`), aucun
  texte trace (`DES-7`).
- **Boule** : un cercle seul serait un cercle (`ADR-0077`, deja cite par `theo-archi`). Cercle +
  ellipse d'equateur aplatie (rayon vertical environ un tiers du rayon horizontal) rend un dessin
  sans ambiguite avec un cercle nu, verifie sur le rendu. La seule question qui reste ouverte - une
  boule variant sans orientation possible - est celle que `theo-archi` route a `nadia-prof`
  (`R6`) : ce n'est pas une question de forme mais de contenu (que compte comme une "variation"),
  je ne la tranche pas.

## 3. Ce que ce document ne fait pas

- Il ne dessine aucun des quinze SVG finaux ; les deux maquettes qui l'ont produit sont jetables et
  ne sont pas commitees dans `adapters/`.
- Il ne juge pas si une projection donnee "ressemble" a un cube ou un pave droit au sens
  geometrique - `theo-archi` l'a deja nomme (`R7`) comme une question de main, hors d'un garde-fou
  automatique, et je ne la reprends pas.
- Il ne tranche pas la question de l'orientation de la boule (`R6`) : route a `nadia-prof`.
- Il ne prononce aucun Done, pedagogique ou technique.
