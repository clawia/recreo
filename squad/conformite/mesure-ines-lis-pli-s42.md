# Mesure - le texteSupport, l'enonce et les trois propositions de `S42-FR-LECTURE-SILENCIEUSE`,
# au plafond haut de `LIS-2`, tiennent-ils au-dessus du pli de reference ?

- **Auteur** : `ines-design`.
- **Date** : 2026-09-24.
- **Branche** : `round-two`, commit de depart `982c4a83`.
- **Motif** : reserve pedagogique de `nadia-prof` ouverte depuis s25
  (`ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md`,
  section 9.1), jamais levee, citee comme condition bloquante dans le critere d'acceptation de
  `S42-FR-LECTURE-SILENCIEUSE` (`docs/backlog/backlog.md`, section "Sprint 42") : *"le paquet
  n'est ecrit qu'apres cette mesure ; si elle montre que le texte ne tient pas, la ligne ne
  s'ouvre pas a ce sprint."* Cette mesure est due avant tout premier commit de contenu sur cette
  ligne, pas apres.

## Ce que ce fichier est, et ce qu'il n'est pas

**Ce qu'il est** : un fait mesure sur le rendu reel de l'application, avec sa methode.

**Ce qu'il n'est pas** :

- **Ce n'est pas un Done pedagogique.** Je ne juge ni l'exactitude ni la qualite du texte
  factice que j'ai ecrit pour cette mesure ; il n'est pas destine a devenir un item du paquet.
  `nadia-prof` reste seule a juger `LIS-1` a `LIS-10` le jour ou un paquet reel existe.
- **Ce n'est pas une levee ou un durcissement de la reserve.** Je reponds a la question posee
  (le texte tient-il au-dessus du pli), je ne me prononce pas sur si la ligne doit s'ouvrir.
- **Ce n'est pas une proposition de forme.** Je ne recommande aucun raccourcissement, aucun
  changement de gabarit ni de feuille de style pour faire tenir ce contenu : `CLAUDE.md` me
  demande de remonter un obstacle de cette nature a `sofia-sm`, pas de le resoudre moi-meme par
  un compromis de mon cru.

## 1. La reference contre laquelle je mesure

`DEC-0040`, amendement du 2026-08-22 (second), fixe la reference unique du produit a
**744 x 551 px** (largeur : iPad mini, pire cas de largeur de la cible ordinateur/tablette de
`DEC-0040` ; hauteur : dalle d'ordinateur 720 px moins la chrome de navigateur avec barre de
favoris, 169 px, mesuree sur poste reel par `nadia-prof`,
`squad/conformite/mesure-hauteur-utile-nouvelle-cible.md`). C'est la reference unique retenue
depuis cette date, non amendee depuis par aucune decision trouvee dans `squad/decisions/produit/`.
Elle reste la reference **de mesure**, y compris depuis que `DEC-0053` (2026-08-28) a retire le
motif "hors de vue sans defiler" de la porte pedagogique **generale** de `nadia-prof` : cette
sortie de porte generale ne s'applique pas au critere d'acceptation explicite de
`S42-FR-LECTURE-SILENCIEUSE`, qui fait de cette mesure une condition d'ouverture de la ligne,
ecrite noir sur blanc dans le backlog et non retiree depuis `DEC-0053`. Je mesure donc contre
744 x 551 px, sans arbitrer si ce choix de porter la reserve malgre `DEC-0053` est le bon - ce
jugement n'est pas le mien.

**Plafond mesure, sur le cas le plus defavorable exige par `LIS-2`/`LIS-3`** : `texteSupport` a
70 mots et 5 phrases (le plafond haut de `LIS-2`, pas les 45 mots plus anciens de `COMP-5`),
`enonce` a 12 mots (le plafond de `LIS-3`), trois propositions.

## 2. Methode

Jar reconstruit sur le JDK de la toolchain 21 (`bash outils/mvn-jdk21.sh -B -pl bootstrap -am
clean package -DskipTests`), application servie localement. Une page de mesure reproduit au
caractere pres le balisage de `fragments/manche.html` pour l'etat "item pose, aucune reponse
donnee" (le seul etat que la question posee nomme - `texteSupport`, `enonce`, les propositions ;
le bloc `.message-jeu`, qui n'existe qu'apres une reponse, est hors de cette question et n'est pas
mesure ici) : meme en-tete (`.entete`, `.entete-contenu`), meme conteneur (`main#jeu.container
.zone-jeu`), memes classes (`.ecran-de-jeu`, `.score`, `.consigne`, `.texte-support`, `.enonce`,
`.cabinet`, `.cabinet-bandeau`, `.cabinet-visage`, `.propositions`, `.proposition`,
`.cabinet-levier`, `.cabinet-socle`). Aucun `.bloc-adulte` (cette ligne ne porte pas de contenu a
voix, `CLAUDE.md` reserve ce dispositif aux lignes ou la voix est la competence visee) ni
`.illustration` (aucun dessin declare par `LIS-1` a `LIS-10`). Les deux feuilles de style
(`/vendor/bootstrap.min.css`, `/css/recreo.css`) sont chargees depuis l'application en cours
d'execution, jamais une copie.

**Texte factice, au plafond exact, verifie par un compteur programmatique avant toute mesure** (et
non par un compte a l'oeil) :

> Léa et Karim visitaient le grenier de leur grand-père presque tous les dimanches après-midi. Ils
> y découvraient souvent des objets anciens rangés dans de vieilles caisses en bois. Ce jour-là,
> ils trouvèrent une boîte remplie de lettres jaunies et de vieilles photographies. Karim reconnut
> sur une image un petit village de montagne que son grand-père aimait. Léa proposa alors de
> ranger chaque photographie dans un carnet pour toute la famille.

**70 mots, 5 phrases** (compte verifie par script : `70`, nombre de points : `5`).

Enonce : "De quoi parle surtout ce texte que tu viens juste de lire ?" - **12 mots** (compte
verifie par script, hors point d'interrogation), dans la forme que `LIS-1` demande ("de quoi
parle le texte").

Trois propositions, dans la forme phrase courte que `LIS-1` autorise ("une phrase qui le
resume") : "Une famille qui range un grenier rempli de souvenirs" ; "Un enfant qui décrit un
village de montagne inconnu" ; "Deux enfants qui découvrent de vieilles photos de famille".

**Verifie et non suppose : substituer les caracteres accentues par leur forme non accentuee ne
change aucune des cotes mesurees** (mesure faite deux fois, avec et sans accents ; les deux jeux
de coordonnees sont identiques au pixel). Aucune reserve de police n'est donc necessaire sur ce
point, contrairement a la reserve que `nadia-prof` avait du poser sur `SYN-5` (Segoe UI mesuree,
San Francisco non verifiee) : les glyphes accentues n'elargissent pas la boite rendue sur ce
poste.

**Lecture par le protocole de deverminage du navigateur** (Chrome, profil isole, sans extension),
viewport impose a 744 x 551 px par `Emulation.setDeviceMetricsOverride`, `deviceScaleFactor` a 1,
mode non mobile - meme methode que `mesure-hauteur-utile-nouvelle-cible.md` section 1.1.
`getBoundingClientRect()` sur chaque bloc, apres l'evenement `Page.loadEventFired` (donc apres
chargement complet des deux feuilles de style) plus une marge de mise en page. `scrollY` verifie a
0 avant chaque lecture (aucune valeur non nulle observee).

**Invariance a la hauteur du viewport, verifiee et non supposee** : les memes cotes (`top`/`bottom`
de `.enonce`, de `.cabinet` et de chaque `.proposition`) sont mesurees a l'identique au pixel pres
a 744 x 551 et a 744 x 1200 - aucune regle de `recreo.css` ne fixe de hauteur relative au viewport
sur ce gabarit, confirmant ce que `nadia-prof` avait deja etabli sur `cm1-synonymes`
(`mesure-hauteur-utile-nouvelle-cible.md`, section 2.2).

Releve : `squad/conformite/releves-mesure-lis-s42/744x551-plafond-70mots-3propositions.png`.

## 3. Chiffres

Cotes verticales lues sur le rendu, origine en haut du viewport (`y = 0`), pli a `y = 551` :

| Bloc | `top` (px) | `bottom` (px) | Au-dessus du pli ? |
|---|---|---|---|
| `.entete` | 0 | 82 | oui |
| `.consigne` | 152 | 182 | oui |
| `.texte-support` (70 mots, 5 phrases, replie sur 6 lignes) | 186 | 366 | oui, marge 185 px |
| `.enonce` (12 mots, replie sur 2 lignes) | 370 | 466 | oui, marge 85 px |
| `.cabinet` (bandeau + les trois propositions + socle) | 470 | 908 | **non**, deborde de 357 px |
| 1re proposition ("Une famille qui range...") | 510 | 624 | **partiellement** : 41 px visibles sur 114 (36 %), le bas du bouton et son dernier mot ne sont pas visibles |
| 2e proposition ("Un enfant qui decrit...") | 640 | 754 | **non**, entierement sous le pli |
| 3e proposition ("Deux enfants qui decouvrent...") | 770 | 884 | **non**, entierement sous le pli |

Largeur de rangee mesuree : 696 px pour `.texte-support`/`.enonce` (conteneur `.container` a
720 px moins le rembourrage lateral de Bootstrap, bande 720-767,98 px, `ADR-0054`/regle `SYN-11`
deja en vigueur dans `recreo.css` a ce commit), 680 px pour `.propositions` (696 px moins la
bordure du cabinet, 8 px de chaque cote). Chacune des trois propositions occupe seule sa rangee
(680 px de large, une par ligne) : leur longueur en mots depasse ce que la rangee peut recevoir
cote a cote, un comportement deja observe par `nadia-prof` sur des propositions plus courtes
(`FICHE-bandit-manchot-comprehension-cm1.md`, "3 propositions de deux ou trois mots : 3 rangees").

## 4. Verdict

**Non, la ligne ne tient pas au-dessus du pli au plafond haut de `LIS-2`/`LIS-3`.**

Le `texteSupport` (70 mots) et l'`enonce` (12 mots) tiennent tous les deux, avec des marges
confortables (185 px et 85 px). **Ce qui ne tient pas, ce sont les trois propositions exigees par
`LIS-1`** : la premiere n'est visible qu'au tiers, les deux autres ne le sont pas du tout, sans
faire defiler la page. Un enfant de CM1, a ce plafond, lit le texte et la question en entier, puis
ne voit aucune des trois reponses completes sans defiler.

Ce n'est pas un ecart de quelques pixels comme les cas deja mesures ailleurs dans ce depot
(`SYN-5`, item a 1,51 px de marge) : le cabinet entier deborde de 357 px, et deux propositions sur
trois sont integralement hors champ. Le budget vertical qu'ouvraient `texteSupport` et `enonce`
n'a jamais ete la contrainte qui manque ; c'est le bloc des trois propositions, structurellement,
qui ne trouve plus de place une fois le texte et la question rendus a leur plafond.

## 5. Ce que je ne conclus pas

- **Je ne conclus pas que la ligne doit rester fermee, ni qu'elle doit s'ouvrir.** Le critere
  d'acceptation de `S42-FR-LECTURE-SILENCIEUSE` dit ce que ce constat entraine ("si elle montre
  que le texte ne tient pas, la ligne ne s'ouvre pas a ce sprint") ; je rends la mesure qu'il
  demande, je n'arbitre pas sa consequence sur le perimetre du sprint, qui revient a
  `theo-archi`/`camille-po`.
- **Je ne conclus rien sur un texte plus court.** Je n'ai mesure que le plafond haut exige par la
  question posee (70 mots). Le plancher de `LIS-2` (45 mots) ou une longueur intermediaire n'ont
  pas ete mesures ici.
- **Je ne conclus rien sur une autre forme de proposition.** J'ai mesure des propositions en
  phrase courte, une des trois formes que `LIS-1` autorise ("un titre, une phrase qui le resume,
  ce qu'on en retiendrait"). Un titre de deux ou trois mots occuperait moins de hauteur ; je ne
  l'ai pas mesure et je ne propose pas cette piste comme un compromis de ma part.
- **Je ne propose aucune correction de forme** (raccourcir le texte, changer le gabarit,
  agrandir le budget vertical) : c'est exactement le geste que `CLAUDE.md` m'interdit de faire
  moi-meme quand une mesure revele un obstacle qui deborde de mon mandat de forme pure.

## Ce que je route

- **`sofia-sm`** : le verdict de la section 4, pour la consequence qu'il entraine sur le
  perimetre de `S42-FR-LECTURE-SILENCIEUSE`, telle que le critere d'acceptation de la story la
  nomme.
- **`nadia-prof`** : ce fichier repond a la mesure qu'elle demandait en s25 avant l'ecriture du
  paquet ; le jugement sur ce que ce constat signifie pour sa reserve reste le sien.

## Impact

- `squad/conformite/mesure-ines-lis-pli-s42.md` (ce fichier) : cree.
- `squad/conformite/releves-mesure-lis-s42/744x551-plafond-70mots-3propositions.png` : cree.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie par ce
  fichier.
